/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaUserRole.java
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
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaUserRole
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({"rawtypes"})
public class OmcSchemaUserRole extends OmcSchemaUserCommon{
    /**
     * @param vo
     */
    public OmcSchemaUserRole(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }

    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        OmcSchemaUserCommon.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pkinds_str = #{funVariable_00001}");
        variableParameter.setFunVariable_00001("Role");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        OmcSchemaUserCommon.getCommonSelectSql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1 ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSUSER_KIND_Role));
    }
    public static List<OmcSchemaUserVO> getInactiveList(){
        return(OmcSchemaServiceUtils.getInActiveRoleListForUpload());
    }
    public static List<OmcSchemaUserVO> getUploadList(){
        return(OmcSchemaServiceUtils.getRoleListForUpload());
    }
    @Override
    protected void setClassKind(){
        OmcSchemaUserVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_Role);
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
        this.getVo().setKinds(Bit.or(OmcSystemConstants.SYSUSER_KIND_Default,OmcSystemConstants.SYSUSER_KIND_Role));
    }
    public void addRole(String role){
        OmcSchemaUserVO roleVO = OmcSchemaServiceUtils.getUserCommonWithName(role);
        if(!Bit.isInclude(roleVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("'" + roleVO + "' is not Role");
        OmcSchemaRelationVO sysRelVO = new OmcSchemaRelationVO(this.getVo().getObid(),roleVO.getObid());
        OmcSchemaRelationRole2Role group2GroupDom = new OmcSchemaRelationRole2Role(sysRelVO);
        group2GroupDom.createObject(new HashMap<String,Object>());
    }
    public void removeRole(String role){
        List<OmcSchemaRelationVO> relList = OmcSchemaServiceUtils.getUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_RoleRole,role,this.getVo().getNames());
        for(OmcSchemaRelationVO vo : relList){
            OmcSchemaRelationRole2Role dom = new OmcSchemaRelationRole2Role(vo);
            dom.deleteObject(new HashMap<String,Object>());
        }
    }
}
