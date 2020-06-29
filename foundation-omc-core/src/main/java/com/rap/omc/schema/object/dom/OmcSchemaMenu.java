/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaMenu.java
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
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.object.model.OmcSchemaTabLayoutVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;

/**
 * <pre>
 * Class : OmcSchemaMenu
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes")
public class OmcSchemaMenu extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaMenu.class);
    public OmcSchemaMenu(OmcSchemaSysRootVO vo) {
        super(vo);
    }
    @Override
    public OmcSchemaMenuVO getVo(){
        return (OmcSchemaMenuVO)vo;
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    public OmcSchemaMenu(String obid) {
        super(OmcSchemaServiceUtils.getSystemMenuWithObid(obid));
    }
    @Override
    protected void create(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.createSystemMenu(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactivateSystemMenu(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSystemMenu(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySystemMenu(this.getVo());
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
        OmcSchemaMenuVO vo = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSMNU_FLAG_Default,OmcSystemConstants.SYSMNU_FLAG_Active);
        if("Y".equals(vo.getIsAccessControlObject())) flags = Bit.or(flags,OmcSystemConstants.SYSMNU_FLAG_IsAccessControlObject);
        if("Y".equals(vo.getIsHidden())) flags = Bit.or(flags,OmcSystemConstants.SYSMNU_FLAG_Hidden);
        if("Smart Client Function".equals(vo.getCallingType())) flags = Bit.or(flags,OmcSystemConstants.SYSMNU_FLAG_SCFunction);
        if("Java Script Function".equals(vo.getCallingType())) flags = Bit.or(flags,OmcSystemConstants.SYSMNU_FLAG_JSFunction);
        if("Contents Replace".equals(vo.getCallingType())) flags = Bit.or(flags,OmcSystemConstants.SYSMNU_FLAG_ContentsRepl);
        if("Smart Client Window Open".equals(vo.getCallingType())) flags = Bit.or(flags,OmcSystemConstants.SYSMNU_FLAG_SCWindow);
        if("Java Method Call".equals(vo.getCallingType())) flags = Bit.or(flags,OmcSystemConstants.SYSMNU_FLAG_CheckGrpMethod);
        if("PLSQL Function".equals(vo.getCallingType())) flags = Bit.or(flags,OmcSystemConstants.SYSMNU_FLAG_PLSQLFunction);
        if("Checkbox Group".equals(vo.getKindsStr()) && vo.getNames().substring(0, 9).equals("chkURLCall")) flags = Bit.or(flags,OmcSystemConstants.SYSMNU_FLAG_CheckGrpFunCall);

        vo.setFlags(flags);
        this.setVo(vo);
    }

    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        OmcSchemaMenuVO vo = this.getVo();
        vo.setClassKinds(OmcSystemConstants.SYSKEY_KIND_Menu);
        this.setVo(vo);
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
    
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){

        sqlStrBuff.append(                                      "select a.psequences         as sequences         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pchange_comments   as change_comments   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pis_sub            as is_sub            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodule_name       as module_name       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pis_access_control_object as is_access_control_object");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pnames             as names             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pkinds_str         as kinds_str         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.psortings          as sortings          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.psub_names         as sub_names         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pis_hidden         as is_hidden         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plabels            as labels            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plabels_kr         as labels_kr         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcalling_type      as calling_type      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plink_href         as link_href         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plink_alt          as link_alt          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pimages            as images            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.paccess_expression as access_expression ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pdescriptions      as descriptions      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.powners            as owners            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,(select x.obid from  psysmenu x where a.pnames  = x.pnames)  as obid ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysmenu a"                      );
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append(                                      "select a.obid                as obid             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pflags              as flags            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pobject_kind        as object_kind      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pnames              as names            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pdescriptions       as descriptions     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plabels             as labels           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plabels_kr          as labels_kr        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plink_herf          as link_herf        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plink_alt           as link_alt         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pimages             as images           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.paccess_expression  as access_expression");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodule_name        as module_name      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pstring01           as string01         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pstring02           as string02         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pstring03           as string03         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pstring04           as string04         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pstring05           as string05         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreator            as creator          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreated            as created          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodifier           as modifier         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodified           as modified         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysmenu a" );
    }
    public boolean isEqual(OmcSchemaMenuVO vo){
        
        OmcSchemaMenuVO thisVo = this.getVo();
        if(!StrUtil.isEqual(thisVo.getObid(), vo.getObid())) return false;
        if(!StrUtil.isEqual(thisVo.getNames(), vo.getNames())) return false;
        if(!StrUtil.isEqual(thisVo.getKindsStr(), vo.getKindsStr())) return false;
        //if(!StrUtil.isEqual(thisVo.getSortings(), vo.getSortings())) return false;
        if(!StrUtil.isEqual(thisVo.getIsHidden(), vo.getIsHidden())) return false;
        if(!StrUtil.isEqual(thisVo.getLabels(), vo.getLabels())) return false;
        if(!StrUtil.isEqual(thisVo.getCallingType(), vo.getCallingType())) return false;
        if(!StrUtil.isEqual(thisVo.getLinkAlt(), vo.getLinkAlt())) return false;
        if(!StrUtil.isEqual(thisVo.getLinkHref(), vo.getLinkHref())) return false;
        if(!StrUtil.isEqual(thisVo.getImages(), vo.getImages())) return false;
        if(!StrUtil.isEqual(thisVo.getAccessExpression(), vo.getAccessExpression())) return false;
        if(!StrUtil.isEqual(thisVo.getDescriptions(), vo.getDescriptions())) return false;
        if(!StrUtil.isEqual(String.valueOf(thisVo.getSequences()), String.valueOf(vo.getSequences()))) return false;
        if(!StrUtil.isEqual(thisVo.getModuleName(), vo.getModuleName())) return false;
        if(!StrUtil.isEqual(thisVo.getOwners(), vo.getOwners())) return false;
        if(!StrUtil.isEqual(thisVo.getRemarks(), vo.getRemarks())) return false;
        if(!StrUtil.isEqual(thisVo.getChangeComments(), vo.getChangeComments())) return false;
        //return true;
        return false;
    }
    public static void uploadTemporaryList(List<OmcSchemaMenuVO> list){
        OmcSchemaServiceUtils.uploadTemporaryMenuList(list);
    }
    public static void uploadTemporaryTabLayoutList(List<OmcSchemaTabLayoutVO> list){
        OmcSchemaServiceUtils.uploadTemporaryTabLayoutList(list);
    }
    public static Map<String,Object> getErrorListForForUploadMenuList(){
        return(OmcSchemaServiceUtils.getErrorListForForUploadMenuList());
    }
    public static List<OmcSchemaMenuVO> getInactiveListForUpload(){
        return(OmcSchemaServiceUtils.getInactiveMenuListForUpload());
    }
    public static List<OmcSchemaRelationVO> getSubMenuCreateListForUpload(){
        return(OmcSchemaServiceUtils.getSubMenuCreateListForUpload());
    }
    public static List<OmcSchemaRelationVO> getSubMenuInactiveListForUpload(){
        return(OmcSchemaServiceUtils.getSubMenuInactiveListForUpload());
    }
    public static List<OmcSchemaMenuVO> getMenuListForUpload(){
        return(OmcSchemaServiceUtils.getMenuListForUpload());
    }
}
