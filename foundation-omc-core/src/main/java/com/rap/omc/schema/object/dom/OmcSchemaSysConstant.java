/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysConstant.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.object.model.OmcSchemaSysConstantVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaSysConstant
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({"rawtypes"})
public class OmcSchemaSysConstant extends OmcSchemaSysRoot {
    /**
     * @param vo
     */
    public OmcSchemaSysConstant(OmcSchemaSysRootVO vo) {
        super(vo);
    }
    public OmcSchemaSysConstant(String obid) {
        super(OmcSchemaServiceUtils.getSystemConstantsWithObid(obid));
    }
    @Override
    public OmcSchemaSysConstantVO getVo(){
        return (OmcSchemaSysConstantVO)this.vo;
    }
    public void create(Map map){
        OmcSchemaServiceUtils.createSystemConstants(this.getVo());
    }
    public void delete( Map map){
        //OmcSchemaServiceUtils.deleteSystemConstants(this.getVo());
    }
    public void modify(Map map){
        OmcSchemaServiceUtils.modifySystemConstants(this.getVo());
    }
    public void inActiviate(Map map){
        OmcSchemaServiceUtils.inactivateSystemConstants(this.getVo());
    }
    public OmcSchemaSysRootVO getObjectInfoByName(String Names){
        return(OmcSchemaServiceUtils.getSystemConstantsWithObid(Names));
    }
    @Override
    protected void validateForCreate(Map map){
        super.validateForCreate(map);
    }
    @Override
    protected void preProcessForCreate(Map map){
        super.preProcessForCreate(map);
    }
    @Override
    protected void postProcessForCreate(Map map){
        super.postProcessForCreate(map);
        //this.setProperties(map);
    }
    @Override
    protected void validateForInActiviate(Map map){
        super.validateForInActiviate(map);
    }
    @Override
    protected void preProcessForInActiviate(Map map){
        super.preProcessForInActiviate(map);
    }
    @Override
    protected void postProcessForInActiviate(Map map){
        super.postProcessForInActiviate(map);
    }
    @Override
    protected void validateForDelete(Map map){
        super.validateForDelete(map);
    }
    @Override
    protected void preProcessForDelete(Map map){
        super.preProcessForDelete(map);
    }
    @Override
    protected void postProcessForDelete(Map map){
        super.postProcessForDelete(map);
    }
    @Override
    protected void validateForModify(Map map){
        super.validateForModify(map);
        this.setFlags();
    }
    @Override
    protected void preProcessForModify(Map map){
        super.preProcessForModify(map);
    }
    @Override
    protected void postProcessForModify(Map map){
        super.postProcessForModify(map);
        //this.setProperties(map);
    }
    
    protected void setFlags(){
        OmcSchemaSysConstantVO constantsVO = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSCONSTANTS_FLAG_Default,OmcSystemConstants.SYSCONSTANTS_FLAG_Active);
        constantsVO.setFlags(flags);
        this.setVo(constantsVO);
    }
    protected void setClassKind(){
        OmcSchemaSysConstantVO constantsVO = this.getVo();
        constantsVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_SystemConstants);
        this.setVo(constantsVO);
    }
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select b.obid                 as obid                 ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences           as sequences            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments     as change_comments      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkinds               as kinds                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkind_desc           as kind_desc            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames               as names                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pconstant_values     as constant_values      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.phex_converting_flag as hex_converting_flag   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysconstants a left outer join psysconstants b"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("on   a.pnames = b.pnames"                      );
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append(                                      "select a.obid                         as   obid                  ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags                       as   flags                 ,");  
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames                       as   names                 ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pconstant_values             as   constant_values       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.phex_converting_flag         as   hex_converting_flag   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkinds                       as   kinds                 ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkind_desc                   as   kind_desc             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences                   as   sequences             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments             as   change_comments       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator                     as   creator               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated                     as   created               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier                    as   modifier              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified                    as   modified               ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysconstants a                                                ");
    }
    private void setProperties(Map map){
        String propertyName = "SYSTEM" + this.getVo().getNames().substring(2);
        OmcSchemaServiceUtils.setBizClassProperty(this.getVo(), propertyName, this.getVo().getConstantValues());
    }
    public static List<OmcSchemaSysConstantVO> getInactiveListForUpload(){
        return(OmcSchemaServiceUtils.getInactiveConstantsListForUpload());
    }
    public static List<OmcSchemaSysConstantVO> getAllTemporaryListForUpload(){
        return(OmcSchemaServiceUtils.getAllTemporaryListForUpload());
    }
    public static void uploadTemporaryList(List<OmcSchemaSysConstantVO> contList){
        OmcSchemaServiceUtils.uploadTemporaryConstants(contList);
    }
}
