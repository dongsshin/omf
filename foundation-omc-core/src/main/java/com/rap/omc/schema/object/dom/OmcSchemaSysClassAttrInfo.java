/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysCclassAttrInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 10.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.model.OmcSchemaSysBizClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSysClassAttrInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcDBMSConstants;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaSysCclassAttrInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({"rawtypes"})
public class OmcSchemaSysClassAttrInfo extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObject.class);
    /**
     * @param vo
     */
    public OmcSchemaSysClassAttrInfo(OmcSchemaSysRootVO vo) {
        super(vo);
    }
    public OmcSchemaSysClassAttrInfo(String obid, boolean isTemporary) {
        OmcSchemaSysClassAttrInfoVO vo;
        if (isTemporary){
            vo = OmcSchemaServiceUtils.getTemporarySystemAttrWithObid(obid);
        }else
        {
            vo = OmcSchemaServiceUtils.getSystemAttrWithObid(obid);
        }
        this.vo = vo;
    }
    @Override
    public OmcSchemaSysClassAttrInfoVO getVo(){
        return (OmcSchemaSysClassAttrInfoVO)vo;
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].create Start");
        OmcSchemaServiceUtils.createSystemClassAttr(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].inActiviate Start");
        OmcSchemaServiceUtils.inactivateSystemClassAttr(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].delete Start");
        OmcSchemaServiceUtils.deleteSystemClassAttr(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].modify Start");
        OmcSchemaServiceUtils.modifySystemClassAttr(this.getVo());
    }

    /**
     * 
     * @param Names
     * @return
     * @see omc.schema.object.dom.OmcSchemaSysRoot#getObjectInfoByName(java.lang.String)
     */
    @SuppressWarnings("unused")
	@Override
    protected OmcSchemaSysRootVO getObjectInfoByName(String Names){
        if(true) throw new FoundationException("Does not support.");
        return null;
    }

    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setFlags()
     */
    @Override
    protected void setFlags(){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].setFlags Start");
        OmcSchemaSysClassAttrInfoVO thisVO = this.getVo();

        long flags = Bit.or(OmcSystemConstants.SYSCLASSATTR_FLAG_Default,OmcSystemConstants.SYSCLASSATTR_FLAG_Active);
        long kinds = OmcSystemConstants.SYSCLASSATTR_KIND_Default;
        if(thisVO.isNullAble()) flags  = Bit.or(flags,OmcSystemConstants.SYSCLASSATTR_FLAG_Nullable);
        if(thisVO.isLongString()) flags  = Bit.or(flags,OmcSystemConstants.SYSCLASSATTR_FLAG_Clob);
        if(isNameAttribute()) flags  = Bit.or(flags,OmcSystemConstants.SYSCLASSATTR_FLAG_IsNameAttribute);

        if(thisVO.getClassType().equals("BO")) kinds = Bit.or(kinds,OmcSystemConstants.SYSCLASSATTR_KIND_BO);
        if(thisVO.getClassType().equals("RO")) kinds = Bit.or(kinds,OmcSystemConstants.SYSCLASSATTR_KIND_RO);
        
        thisVO.setKinds(kinds);
        
        thisVO.setFlags(flags);
        this.setVo(thisVO);
    }
    private boolean isNameAttribute(){
        String str = this.getVo().getValueList();
        int dataType = this.getVo().getDataType();
        if(NullUtil.isNone(str)) return false;
        str = str.trim();
        if(!str.startsWith("{Class:")) return false;
        if(!str.endsWith("}")) return false;
        if(str.indexOf(" ") != -1) return false;
        if(!(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_STRING || dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_USERID)) return false;
        return true;
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].setClassKind Start");
        OmcSchemaSysClassAttrInfoVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_ClassAttr);
        this.setVo(thisVO);
    }
    @Override
    protected void validateForCreate(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].validateForCreate Start");
        super.validateForCreate(map);
        this.valueValidate(map);
        if(!isNew(map)) throw new FoundationException("omc.schema.bizclass.alreadyexists");//Bisiness Class is Duplicated
    }
    private void valueValidate(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].valueValidate Start");
        OmcSchemaSysClassAttrInfoVO thisVO = this.getVo();
        if (StrUtil.isEmpty(thisVO.getAttributeName())) throw new FoundationException("omc.schema.classattr.notvalid");
        if (StrUtil.isEmpty(thisVO.getClassName())) throw new FoundationException("omc.schema.classattr.notvalid");
    }
    private boolean isNew(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].isNew Start");
        OmcSchemaSysBizClassVO bizClassVO = OmcSchemaServiceUtils.getSystemBizClassWithObid(this.getVo().getObid());
        if (bizClassVO == null) return true;
        return false;
    }
    @Override
    protected void preProcessForCreate(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].preProcessForCreate Start");
        super.preProcessForCreate(map);
    }
    @Override
    protected void postProcessForCreate(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].postProcessForCreate Start");
        super.postProcessForCreate(map);
        this.setProperties(map);
    }
    @Override
    protected void validateForInActiviate(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].validateForInActiviate Start");
        super.validateForInActiviate(map);
    }
    @Override
    protected void preProcessForInActiviate(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].preProcessForInActiviate Start");
        super.preProcessForInActiviate(map);
    }
    @Override
    protected void postProcessForInActiviate(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].postProcessForInActiviate Start");
        super.postProcessForInActiviate(map);
    }
    @Override
    protected void validateForDelete(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].validateForDelete Start");
        super.validateForDelete(map);
    }
    @Override
    protected void preProcessForDelete(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].preProcessForDelete Start");
        super.preProcessForDelete(map);
    }
    @Override
    protected void postProcessForDelete(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].postProcessForDelete Start");
        super.postProcessForDelete(map);
    }
    @Override
    protected void validateForModify(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].validateForModify Start");
        super.validateForModify(map);
        this.setFlags();
    }
    @Override
    protected void preProcessForModify(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].preProcessForModify Start");
        super.preProcessForModify(map);
    }
    @Override
    protected void postProcessForModify(Map map){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].postProcessForModify Start");
        super.postProcessForModify(map);
        this.setProperties(map);
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getAllAttribute(boolean activeOnly){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].getAllAttribute Start");
        return(OmcSchemaServiceUtils.getAllAttribute(false, activeOnly));
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getAllTemporayAttribute(){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].getAllTemporayAttribute Start");
        return(OmcSchemaServiceUtils.getAllAttribute(true, false));
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getDuplicatedAttrTypeList(){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].getDuplicatedAttrTypeList Start");
        return(OmcSchemaServiceUtils.getDuplicatedAttrTypeList());
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getNotFefinedClassForAttr(){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].getNotFefinedClassForAttr Start");
        return(OmcSchemaServiceUtils.getNotFefinedClassForAttr());
    }
    public static void initializeAttribute(){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].initializeAttribute Start");
        OmcSchemaServiceUtils.initializeAttribute();
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getDuplicatdAttr(){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].getDuplicatdAttr Start");
        return(OmcSchemaServiceUtils.getDuplicatdAttr());
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getInactiveListForAttr(){
        LOGGER.debug("[OmcSchemaSysClassAttrInfo].getInactiveListForAttr Start");
        return(OmcSchemaServiceUtils.getInactiveListForAttr());
    }
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append(OmcFoundationConstant.newline).append("select a.psequences               as sequences            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments         as change_comments      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_type              as class_type           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_name              as class_name           ,");
        //sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute_name          as attribute_name       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name          as displayed_name       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_column             as dbms_column          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcolumn_alias            as column_alias         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psortings                as sortings             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdata_type_str           as data_type_str        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.plengths                 as lengths              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnull_able_str           as null_able_str        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_value           as default_value        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pvalue_list              as value_list           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks                 as remarks              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners                  as owners               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_obid              as class_obid           ,"); 
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.obid                     as obid                 "); 
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from (");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("select a.psequences               as psequences            ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments         as pchange_comments      ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_type              as pclass_type           ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_name              as pclass_name           ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute_name          as pattribute_name       ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name          as pdisplayed_name       ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_column             as pdbms_column          ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcolumn_alias            as pcolumn_alias         ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psortings                as psortings             ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdata_type_str           as pdata_type_str        ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.plengths                 as plengths              ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnull_able_str           as pnull_able_str        ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_value           as pdefault_value        ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pvalue_list              as pvalue_list           ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks                 as premarks              ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners                  as powners               ,"); 
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select z.obid from psysbizobjectclassinfo z where z.pnames = a.pclass_name) as pclass_obid,"); 
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       b.obid                     as obid                 ");   
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysclassattrinfo a left join (select y.pnames,x.* from psysclassattrinfo x inner join psysbizobjectclassinfo y on x.pclass_obid = y.obid) b");   
        sqlStrBuff.append(OmcFoundationConstant.newline).append("on a.pcolumn_alias  = b.pcolumn_alias");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pdbms_column   = b.pdbms_column"); 
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pclass_name = b.pnames");   
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pclass_type = 'BO'");   
        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all");   
        sqlStrBuff.append(OmcFoundationConstant.newline).append("select a.psequences               as psequences            ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments         as pchange_comments      ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_type              as pclass_type           ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_name              as pclass_name           ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute_name          as pattribute_name       ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name          as pdisplayed_name       ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_column             as pdbms_column          ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcolumn_alias            as pcolumn_alias         ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psortings                as psortings             ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdata_type_str           as pdata_type_str        ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.plengths                 as plengths              ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnull_able_str           as pnull_able_str        ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_value           as pdefault_value        ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pvalue_list              as pvalue_list           ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks                 as premarks              ,");     
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners                  as powners               ,"); 
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select z.obid from psysrelobjectclassinfo z where z.pnames = a.pclass_name) as pclass_obid,"); 
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       b.obid                     as obid                 ");    
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysclassattrinfo a left join (select y.pnames,x.* from psysclassattrinfo x inner join psysrelobjectclassinfo y on x.pclass_obid = y.obid) b");   
        sqlStrBuff.append(OmcFoundationConstant.newline).append("on a.pcolumn_alias  = b.pcolumn_alias");  
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pdbms_column   = b.pdbms_column");   
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pclass_name   = b.pnames");  
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pclass_type = 'RO'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(") a"); 
    }
//    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("select a.obid                 as obid               ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags               as flags              ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkinds               as kinds              ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_obid          as class_obid         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_name          as class_name         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_type          as class_type         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute_name      as attribute_name     ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_column         as dbms_column        ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcolumn_alias        as column_alias       ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdata_type           as data_type          ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.plengths             as lengths            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_value       as default_value      ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psortings            as sortings           ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners              as owners             ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pvalue_list          as value_list         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name      as displayed_name     ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks             as remarks            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences           as sequences          ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments     as change_comments    ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator             as creator            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated             as created            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier            as modifier           ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified            as modified           ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("from (");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("select a.obid                 as obid                ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags               as pflags              ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkinds               as pkinds              ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_obid          as pclass_obid         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.pnames from psysbizobjectclassinfo x where a.pclass_obid = x.obid)  as pclass_name         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       'BO'                   as pclass_type         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute_name      as pattribute_name     ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_column         as pdbms_column        ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcolumn_alias        as pcolumn_alias       ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdata_type           as pdata_type          ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.plengths             as plengths            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_value       as pdefault_value      ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psortings            as psortings           ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners              as powners             ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pvalue_list          as pvalue_list         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name      as pdisplayed_name     ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks             as premarks            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences           as psequences          ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments     as pchange_comments    ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator             as pcreator            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated             as pcreated            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier            as pmodifier           ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified            as pmodified           ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysclassattrinfo a");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSCLASSATTR_KIND_BO,OmcSystemConstants.SYSCLASSATTR_KIND_BO));
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("select a.obid                 as obid                ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags               as pflags              ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkinds               as pkinds              ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_obid          as pclass_obid         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.pnames from psysrelobjectclassinfo x where a.pclass_obid = x.obid)  as pclass_name         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       'RO'                   as pclass_type         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute_name      as pattribute_name     ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_column         as pdbms_column        ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcolumn_alias        as pcolumn_alias       ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdata_type           as pdata_type          ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.plengths             as plengths            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_value       as pdefault_value      ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psortings            as psortings           ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners              as powners             ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pvalue_list          as pvalue_list         ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name      as pdisplayed_name     ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks             as premarks            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences           as psequences          ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments     as pchange_comments    ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator             as pcreator            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated             as pcreated            ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier            as pmodifier           ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified            as pmodified           ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysclassattrinfo a");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSCLASSATTR_KIND_RO,OmcSystemConstants.SYSCLASSATTR_KIND_RO));
//        sqlStrBuff.append(OmcFoundationConstant.newline).append(") a");
//    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter, String obid){
        sqlStrBuff.append(OmcFoundationConstant.newline).append("select a.obid                 as obid               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags               as flags              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkinds               as kinds              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_obid          as class_obid         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_name          as class_name         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_type          as class_type         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute_name      as attribute_name     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_column         as dbms_column        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcolumn_alias        as column_alias       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdata_type           as data_type          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.plengths             as lengths            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_value       as default_value      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psortings            as sortings           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners              as owners             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pvalue_list          as value_list         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name      as displayed_name     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks             as remarks            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences           as sequences          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments     as change_comments    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator             as creator            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated             as created            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier            as modifier           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified            as modified           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from (");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("select a.obid                 as obid                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags               as pflags              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkinds               as pkinds              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_obid          as pclass_obid         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.pnames from psysbizobjectclassinfo x where a.pclass_obid = x.obid)  as pclass_name         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       'BO'                   as pclass_type         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute_name      as pattribute_name     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_column         as pdbms_column        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcolumn_alias        as pcolumn_alias       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdata_type           as pdata_type          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.plengths             as plengths            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_value       as pdefault_value      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psortings            as psortings           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners              as powners             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pvalue_list          as pvalue_list         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name      as pdisplayed_name     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks             as premarks            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences           as psequences          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments     as pchange_comments    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator             as pcreator            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated             as pcreated            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier            as pmodifier           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified            as pmodified           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysclassattrinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        if(!NullUtil.isNone(obid)) sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = #{funVariable_00100}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSCLASSATTR_KIND_BO,OmcSystemConstants.SYSCLASSATTR_KIND_BO));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("select a.obid                 as obid                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags               as pflags              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkinds               as pkinds              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_obid          as pclass_obid         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.pnames from psysrelobjectclassinfo x where a.pclass_obid = x.obid)  as pclass_name         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       'RO'                   as pclass_type         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pattribute_name      as pattribute_name     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_column         as pdbms_column        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcolumn_alias        as pcolumn_alias       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdata_type           as pdata_type          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.plengths             as plengths            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_value       as pdefault_value      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psortings            as psortings           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners              as powners             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pvalue_list          as pvalue_list         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name      as pdisplayed_name     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks             as premarks            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences           as psequences          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments     as pchange_comments    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator             as pcreator            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated             as pcreated            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier            as pmodifier           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified            as pmodified           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysclassattrinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        if(!NullUtil.isNone(obid)) sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = #{funVariable_00100}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSCLASSATTR_KIND_RO,OmcSystemConstants.SYSCLASSATTR_KIND_RO));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(") a");
        if(!NullUtil.isNone(obid)) variableParameter.setFunVariable_00100(obid);
    }
    
    public boolean isEqual(OmcSchemaSysClassAttrInfoVO vo){
        OmcSchemaSysClassAttrInfoVO thisVo = this.getVo();
        if(!StrUtil.isEqual(thisVo.getObid(), vo.getObid())) return false;
        if(!StrUtil.isEqual(thisVo.getAttributeName(), vo.getAttributeName())) return false;
        if(!StrUtil.isEqual(thisVo.getClassObid(), vo.getClassObid())) return false;
        if(!StrUtil.isEqual(thisVo.getDbmsColumn(), vo.getDbmsColumn())) return false;
        if(!StrUtil.isEqual(thisVo.getAttributeName(), vo.getAttributeName())) return false;
        if(!StrUtil.isEqual(thisVo.getColumnAlias(), vo.getColumnAlias())) return false;
        if(thisVo.getDataType() != vo.getDataType()) return false;
        if(thisVo.getLengths() != vo.getLengths()) return false;
        if(!StrUtil.isEqual(thisVo.getDefaultValue(), vo.getDefaultValue())) return false;
        if(!StrUtil.isEqual(thisVo.getValueList(), vo.getValueList())) return false;
        if(thisVo.getSortings() != vo.getSortings()) return false;
        if(thisVo.getSequences() != vo.getSequences()) return false;
        if(!StrUtil.isEqual(thisVo.getDisplayedName(), vo.getDisplayedName())) return false;
        if(!StrUtil.isEqual(thisVo.getOwners(), vo.getOwners())) return false;
        if(!StrUtil.isEqual(thisVo.getRemarks(), vo.getRemarks())) return false;
        if(!StrUtil.isEqual(thisVo.getChangeComments(), vo.getChangeComments())) return false;
        
        return true;
    }
    private void setProperties(Map map){
        
        OmcSchemaSysClassAttrInfoVO dataBaseVO = null;// = new OmcSchemaSysBizClassVO();
        if(map != null) dataBaseVO = (OmcSchemaSysClassAttrInfoVO)map.get("dataBaseVO");

        if (dataBaseVO == null) {
            ;           
        }
        else{
            ;
        }
    }
    public static void refreshClassAttrInfo(){
        OmcSchemaServiceUtils.refreshClassAttrInfo();
    }
    public static void uploadTemporaryList(List<OmcSchemaSysClassAttrInfoVO> list){
        OmcSchemaServiceUtils.uploadTemporaryClassAttr(list);
    }
}
