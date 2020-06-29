/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaBranch.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 28.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.object.model.OmcSchemaBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaBranch
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes") 
public class OmcSchemaBranch extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaBranch.class);
    public OmcSchemaBranch(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    public OmcSchemaBranchVO getVo(){
        return (OmcSchemaBranchVO)vo;
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.createSchemaLifeCycleBranch(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactiviateSchemaLifeCycleBranch(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSchemaLifeCycleBranch(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySchemaLifeCycleBranch(this.getVo());
    }

    /**
     * 
     * @param Names
     * @return
     * @see omc.schema.object.dom.OmcSchemaSysRoot#getObjectInfoByName(java.lang.String)
     */
    @Override
    protected OmcSchemaSysRootVO getObjectInfoByName(String Names){
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setFlags(){
        // TODO Auto-generated method stub
        OmcSchemaBranchVO vo = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSBRANCH_FLAG_Default,OmcSystemConstants.SYSBRANCH_FLAG_Active);
        vo.setFlags(flags);
        this.setVo(vo);
    }
    protected void setClassKind(){
        // TODO Auto-generated method stub
        OmcSchemaBranchVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_Branch);
        this.setVo(thisVO);
    }

    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setFlags()
     */
    @Override
    protected void validateForCreate(Map map){
        LOGGER.debug("OmcSchemaBranch.validateForCreate Start");
        super.validateForCreate(map);
    }
    @Override
    protected void preProcessForCreate(Map map){
        super.preProcessForCreate(map);
    }
    @Override
    protected void postProcessForCreate(Map map){
        super.postProcessForCreate(map);
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
    }
    @Override
    protected void preProcessForModify(Map map){
        super.preProcessForModify(map);
    }
    @Override
    protected void postProcessForModify(Map map){
        super.postProcessForModify(map);
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid            as obid           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags          as flags          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames          as names          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkinds          as kinds          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pstates         as state          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pto_object      as to_object      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcondition_rule as condition_rule ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator        as creator        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated        as created        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier       as modifier       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified       as modified       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysbranch a ");
    }
    public static List<OmcSchemaBranchVO> getInactiveBranchList(){
        return(OmcSchemaServiceUtils.getInactiveBranchList());
    }
    public static List<OmcSchemaBranchVO> getUploadBranchList(){
        return(OmcSchemaServiceUtils.getUploadBranchList());
    }
}
