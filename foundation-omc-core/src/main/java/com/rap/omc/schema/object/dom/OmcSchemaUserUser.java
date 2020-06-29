/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaUserUser.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 5.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSiteVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
/**
 * <pre>
 * Class : OmcSchemaUserUser
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes")
public class OmcSchemaUserUser extends OmcSchemaUserCommon{
    /**
     * @param vo
     */
    
    public OmcSchemaUserUser(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @param Names
     * @return
     * @see omc.schema.object.dom.OmcSchemaSysRoot#getObjectInfoByName(java.lang.String)
     */
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        OmcSchemaUserCommon.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pkinds_str = #{funVariable_00001}");
        variableParameter.setFunVariable_00001("User");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        OmcSchemaUserCommon.getCommonSelectSql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1 ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSUSER_KIND_User));
    }
    public static List<OmcSchemaUserVO> getInactiveList(){
        return(OmcSchemaServiceUtils.getInActiveUserListForUpload());
    }
    public static List<OmcSchemaUserVO> getUploadList(){
        return(OmcSchemaServiceUtils.getUserListForUpload());
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        OmcSchemaUserVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_User);
        this.setVo(thisVO);
        
    }
    @Override
    protected void preProcessForInActiviate(Map map){
        super.preProcessForInActiviate(map);
    }
    @Override
    protected void validateForDelete(Map map){
        super.validateForDelete(map);
    }
    @Override
    protected void preProcessForCreate(Map map){
        super.preProcessForCreate(map);
        this.processNullData(map);
    }
    private void processNullData(Map map){
    	Boolean isSetup = (Boolean)map.get("isSetup");
        OmcSchemaUserVO thisVo = this.getVo();
        if(StrUtil.isEmpty(thisVo.getDescriptions())) thisVo.setDescriptions(thisVo.getNames());
        if(StrUtil.isEmpty(thisVo.getPassword())) thisVo.setPassword(thisVo.getNames());
        OmcSchemaSiteVO schemaSiteVO = new OmcSchemaSiteVO();
        if(StrUtil.isEmpty(thisVo.getSite())) {
            schemaSiteVO = OmcSchemaServiceUtils.getSiteWithNames("LG");
        }else{
            schemaSiteVO = OmcSchemaServiceUtils.getSiteWithNames(thisVo.getSite());
        }
        if(NullUtil.isNull(schemaSiteVO)) throw new FoundationException("[Foundation.OmcSchemaUserUser.processNullData()]Invalid Site");
        thisVo.setSite(schemaSiteVO.getObid());
        this.vo = thisVo;
    }
    @Override
    protected void preProcessForDelete(Map map){
        super.preProcessForDelete(map);
    }
    @Override
    protected void validateForModify(Map map){
        super.validateForModify(map);
    }
    @Override
    protected void preProcessForModify(Map map){
        super.preProcessForModify(map);
    }
    
    @Override
    protected void postProcessForCreate(Map map){
        super.postProcessForCreate(map);
    }
    @Override
    protected void postProcessForModify(Map map){
        super.postProcessForModify(map);
    }
    @Override
    protected void postProcessForInActiviate(Map map){
        super.postProcessForInActiviate(map);
    }
    
    @Override
    protected void postProcessForDelete(Map map){
        super.postProcessForDelete(map);
    }
    
    public void addRoleToUser(String role){
        OmcSchemaUserVO roleVO = OmcSchemaServiceUtils.getUserCommonWithName(role);
        if(!Bit.isInclude(roleVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("'" + role + "' is not Role");
        OmcSchemaRelationVO sysRelVO = new OmcSchemaRelationVO(roleVO.getObid(),this.getVo().getObid());
        OmcSchemaRelationRole2User role2UserDom = new OmcSchemaRelationRole2User(sysRelVO);
        role2UserDom.createObject(new HashMap<String,Object>());
    }
    public void addRoleToUser(Set<String> roleList){
        for(String role : roleList){
            addRoleToUser(role);
        }
    }
    public void removeRoleToUser(String role){
        List<OmcSchemaRelationVO> relList = OmcSchemaServiceUtils.getUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_RoleUser,role,this.getVo().getNames());
        for(OmcSchemaRelationVO vo : relList){
            OmcSchemaRelationRole2User dom = new OmcSchemaRelationRole2User(vo);
            dom.deleteObject(new HashMap<String,Object>());
        }
    }
    public void removeRoleToUser(Set<String> roleList){
        for(String role : roleList){
            removeRoleToUser(role);
        }
    }
    //------------------- Group -------------------------------------
    public void addGroupToUser(String group){
        OmcSchemaUserVO vo = OmcSchemaServiceUtils.getUserCommonWithName(group);
        if(!Bit.isInclude(vo.getKinds(),OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("'" + group + "' is not Group");
        OmcSchemaRelationVO sysRelVO = new OmcSchemaRelationVO(vo.getObid(),this.getVo().getObid());
        OmcSchemaRelationGroup2User group2UserDom = new OmcSchemaRelationGroup2User(sysRelVO);
        group2UserDom.createObject(new HashMap<String,Object>());
    }
    public void addGroupToUser(Set<String> groupList){
        for(String group : groupList){
            addGroupToUser(group);
        }
    }
    public void removeGroupToUser(String group){
        List<OmcSchemaRelationVO> relList = OmcSchemaServiceUtils.getUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_GroupUser,group,this.getVo().getNames());
        for(OmcSchemaRelationVO vo : relList){
            OmcSchemaRelationGroup2User dom = new OmcSchemaRelationGroup2User(vo);
            dom.deleteObject(new HashMap<String,Object>());
        }
    }
    public void removeGroupToUser(Set<String> groupList){
        for(String group : groupList){
            removeGroupToUser(group);
        }
    }
    protected final void setKinds(){
        this.getVo().setKinds(Bit.or(OmcSystemConstants.SYSUSER_KIND_Default,OmcSystemConstants.SYSUSER_KIND_User));
    }
}