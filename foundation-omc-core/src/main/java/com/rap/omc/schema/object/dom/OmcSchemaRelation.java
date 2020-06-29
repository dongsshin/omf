/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaRelation.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 27.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaRelation
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes")
public abstract class OmcSchemaRelation extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaRelation.class);
    public OmcSchemaRelation(OmcSchemaSysRootVO vo) {
        super(vo);
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    public OmcSchemaRelationVO getVo(){
        return (OmcSchemaRelationVO)vo;
    }
    @Override
    protected void create(Map map){
        OmcSchemaServiceUtils.createSchemaRelation(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        OmcSchemaServiceUtils.inactiviateSchemaRelation(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        OmcSchemaServiceUtils.deleteSchemaRelation(this.getVo());
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        OmcSchemaServiceUtils.modifySchemaRelation(this.getVo());
    }

    /**
     * 
     * @param Names
     * @return
     * @see omc.schema.object.dom.OmcSchemaSysRoot#getObjectInfoByName(java.lang.String)
     */
    @Override
    protected OmcSchemaSysRootVO getObjectInfoByName(String Names){
        return null;
    }
    @Override
    protected void validateForCreate(Map map){
        LOGGER.debug("OmcSchemaSysBizClass.validateForCreate Start");
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
        this.setFlags();
    }
    @Override
    protected void preProcessForModify(Map map){
        super.preProcessForModify(map);
    }
    @Override
    protected void postProcessForModify(Map map){
        super.postProcessForModify(map);
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        OmcSchemaRelationVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_SysRelation);
        this.setVo(thisVO);
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append(OmcFoundationConstant.newline).append("select a.obid                                               as obid                             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags                                             as flags                            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pfrom_obid                                         as from_obid                        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pto_obid                                           as to_obid                          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pschema_kind                                       as schema_kind                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psorting                                           as sorting                          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute01                                       as attribute01                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute02                                       as attribute02                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute03                                       as attribute03                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute04                                       as attribute04                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute05                                       as attribute05                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute06                                       as attribute06                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute07                                       as attribute07                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute08                                       as attribute08                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute09                                       as attribute09                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute10                                       as attribute10                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute11                                       as attribute11                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute12                                       as attribute12                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute13                                       as attribute13                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute14                                       as attribute14                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute15                                       as attribute15                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute16                                       as attribute16                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute17                                       as attribute17                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute18                                       as attribute18                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute19                                       as attribute19                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute20                                       as attribute20                      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator                                           as creator                          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated                                           as created                          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier                                          as modifier                         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified                                          as modified                         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysrelationinfo a ");
    }
}
