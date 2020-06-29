/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaUserGroup.java
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

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.object.model.OmcSchemaTabLayoutVO;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaUserGroup
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({"rawtypes","unused"})
public class OmcSchemaUserGroup extends OmcSchemaUserCommon{

    /**
     * @param vo
     */
    public OmcSchemaUserGroup(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }

    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        OmcSchemaUserCommon.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pkinds_str = #{funVariable_00001}");
        variableParameter.setFunVariable_00001("Group");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        OmcSchemaUserCommon.getCommonSelectSql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1 ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSUSER_KIND_Group));
    }
    public static List<OmcSchemaUserVO> getInactiveList(){
        return(OmcSchemaServiceUtils.getInActiveGroupListForUpload());
    }
    public static List<OmcSchemaUserVO> getUploadList(){
        return(OmcSchemaServiceUtils.getGroupListForUpload());
    }
    @Override
    protected void setClassKind(){
        OmcSchemaUserVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_Group);
        this.setVo(thisVO);
        
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
    protected final void setKinds(){
        this.getVo().setKinds(Bit.or(OmcSystemConstants.SYSUSER_KIND_Default,OmcSystemConstants.SYSUSER_KIND_Group));
    }
    public void addGroup(String group){
        OmcSchemaUserVO vo = OmcSchemaServiceUtils.getUserCommonWithName(group);
        if(!Bit.isInclude(vo.getKinds(),OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("'" + group + "' is not Group");
        OmcSchemaRelationVO sysRelVO = new OmcSchemaRelationVO(this.getVo().getObid(),vo.getObid());
        OmcSchemaRelationGroup2Group relDom = new OmcSchemaRelationGroup2Group(sysRelVO);
        relDom.createObject(new HashMap<String,Object>());
    }
    public void removeGroup(String group){
        List<OmcSchemaRelationVO> relList = OmcSchemaServiceUtils.getUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_GroupGroup,this.getVo().getNames(),group);
        for(OmcSchemaRelationVO vo : relList){
            OmcSchemaRelationGroup2Group dom = new OmcSchemaRelationGroup2Group(vo);
            dom.deleteObject(new HashMap<String,Object>());
        }
    }
    public void addRole(String role){
        OmcSchemaUserVO vo = OmcSchemaServiceUtils.getUserCommonWithName(role);
        if(!Bit.isInclude(vo.getKinds(),OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("'" + role + "' is not Role");
        OmcSchemaRelationVO sysRelVO = new OmcSchemaRelationVO(this.getVo().getObid(),vo.getObid());
        OmcSchemaRelationGroup2Role relDom = new OmcSchemaRelationGroup2Role(sysRelVO);
        relDom.createObject(new HashMap<String,Object>());
    }
    public void removeRole(String group){
        List<OmcSchemaRelationVO> relList = OmcSchemaServiceUtils.getUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_GroupRole,group,this.getVo().getNames());
        for(OmcSchemaRelationVO vo : relList){
            OmcSchemaRelationGroup2Role dom = new OmcSchemaRelationGroup2Role(vo);
            dom.deleteObject(new HashMap<String,Object>());
        }
    }
}
