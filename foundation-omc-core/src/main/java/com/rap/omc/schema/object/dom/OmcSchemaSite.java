/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSite.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 5.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.object.model.OmcSchemaSiteVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;

/**
 * <pre>
 * Class : OmcSchemaSite
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes")
public class OmcSchemaSite extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaSite.class);
    public OmcSchemaSite(String obid) {
        super(OmcSchemaServiceUtils.getSiteWithObid(obid));
    }
    @Override
    public OmcSchemaSiteVO getVo(){
        return (OmcSchemaSiteVO)vo;
    }
    /**
     * @param vo
     */
    
    public OmcSchemaSite(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.createSystemSite(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactivateSystemSite(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSystemSite(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySystemSite(this.getVo());
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
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setFlags()
     */
    @Override
    protected void setFlags(){
        // TODO Auto-generated method stub
        OmcSchemaSiteVO thisVo = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSSITE_FLAG_Default,OmcSystemConstants.SYSSITE_FLAG_Active);
        thisVo.setFlags(flags);
        this.setVo(thisVo);
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        // TODO Auto-generated method stub
        OmcSchemaSiteVO thisVo = this.getVo();
        thisVo.setClassKinds(OmcSystemConstants.SYSKEY_KIND_Site);
        this.setVo(thisVo);
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
    }
    @Override
    protected void preProcessForModify(Map map){
        super.preProcessForModify(map);
    }
    @Override
    protected void postProcessForModify(Map map){
        super.postProcessForModify(map);
    }
    
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.pnames                   as names              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_names         as displayed_names     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.obid from  psyssite x where a.pnames  = x.pnames)  as obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsyssite a");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid                       as obid                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags                     as flags               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames                     as names               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator                   as creator             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated                   as created             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier                  as modifier            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified                  as modified            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psyssite a ");
    }
    public static void uploadTemporaryList(List<OmcSchemaSiteVO> list){
        OmcSchemaServiceUtils.uploadTemporarySite(list);
    }
    public static List<OmcSchemaSiteVO> getInactiveList(){
        return(OmcSchemaServiceUtils.getInActiveSiteListForUpload());
    }
    public static List<OmcSchemaSiteVO> getUploadList(){
        return(OmcSchemaServiceUtils.getSiteListForUpload());
    }
}
