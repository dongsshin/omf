/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaFileFormat.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 23.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.object.model.OmcSchemaFileFormatVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedFormatVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaFileFormat
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes")
public class OmcSchemaFileFormat extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaFileFormat.class);
    @Override
    public OmcSchemaFileFormatVO getVo(){
        return (OmcSchemaFileFormatVO)vo;
    }
    public OmcSchemaFileFormat(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    public OmcSchemaFileFormat(String obid) {
        super(OmcSchemaServiceUtils.getSystemFileFormatWithObid(obid));
    }
    
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.createSystemFileFormat(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactivateSystemFileFormat(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSystemFileFormat(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySystemFileFormat(this.getVo());
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
        OmcSchemaFileFormatVO thisVO = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSFORMAT_FLAG_Default,OmcSystemConstants.SYSFORMAT_FLAG_Active);
        thisVO.setFlags(flags);
        this.setVo(thisVO);
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        OmcSchemaFileFormatVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_Format);
        this.setVo(thisVO);
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
        sqlStrBuff.append                                      ("select a.psequences          as sequences       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments    as change_comments ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames              as names           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pallowed_suffix     as allowed_suffix  ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_names    as displayed_names ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners             as owners          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.obid from psysformat x where x.pnames = a.pnames) as obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysformat a");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid                as obid           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames              as names          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags              as flags          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_names    as displayed_names,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pallowed_suffix     as allowed_suffix ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator            as creator        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated            as created        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier           as modifier       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified           as modified       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysformat a");
    }
    public static void uploadTemporaryList(List<OmcSchemaFileFormatVO> list){
        OmcSchemaServiceUtils.uploadTemporaryFileFormat(list);
    }
    public static List<OmcSchemaFileFormatVO> getUploadList(){
        return(OmcSchemaServiceUtils.getFileFormatListForUpload());
    }
    public static List<OmcSchemaFileFormatVO> getInactiveList(){
        return(OmcSchemaServiceUtils.getInActiveFileFormatListForUpload());
    }
    public static List<OmcSchemaFileFormatVO> getFileFormatListWithList(List<OmcSchemaFileFormatVO> list){
        return(OmcSchemaServiceUtils.getFileFormatListWithList(list));
    }
    public static List<OmcSchemaFileFormatVO> getFileFormatListWithSeperatedList(List<OmcSchemaSeperatedFormatVO> seperatedFormatList){
        return(OmcSchemaServiceUtils.getFileFormatListWithSeperatedList(seperatedFormatList));
    }
}
