/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysRelClass.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.model.OmcSchemaSysClassAttrInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRelClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;

/**
 * <pre>
 * Class : OmcSchemaSysRelClass
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OmcSchemaSysRelClass extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaSysRelClass.class);
    /**
     * @param vo
     */
    public OmcSchemaSysRelClass(OmcSchemaSysRootVO vo) {
        super(vo);
    }
    public OmcSchemaSysRelClassVO getVo(){
        return (OmcSchemaSysRelClassVO)vo;
    }
    public OmcSchemaSysRelClass(String obid) {
        super(OmcSchemaServiceUtils.getSystemRelClassWithObid(obid));
    }
    public void create(Map map){
        OmcSchemaServiceUtils.createSystemRelClass((OmcSchemaSysRelClassVO)this.getVo());
    }
    public void delete(Map map){
        OmcSchemaServiceUtils.deleteSystemRelClass((OmcSchemaSysRelClassVO)this.getVo());
    }
    public void modify(Map map){
        OmcSchemaServiceUtils.modifySystemRelClass((OmcSchemaSysRelClassVO)this.getVo());
    }
    public void inActiviate(Map map){
        OmcSchemaServiceUtils.inactivateSystemRelClass((OmcSchemaSysRelClassVO)this.getVo());
    }
    public OmcSchemaSysRootVO getObjectInfoByName(String Names){
        return(OmcSchemaServiceUtils.getSystemRelClassWithObid(Names));
    }
    
    protected void setFlags(){
        OmcSchemaSysRelClassVO thisVO = this.getVo();
        long flags = Bit.or(OmcSystemConstants.RELATION_FLAG_Default,OmcSystemConstants.RELATION_FLAG_Active);
        if(thisVO.getAllowDuplicate()) flags = flags | OmcSystemConstants.RELATION_FLAG_AllowDuplicate;
        if(thisVO.getIsInstantiable()) flags = flags | OmcSystemConstants.RELATION_FLAG_Instantiable;
        thisVO.setFlags(flags);
        this.setVo(thisVO);
    }
    protected void setClassKind(){
        OmcSchemaSysRelClassVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_ROClass);
        this.setVo(thisVO);
    }
    @Override
    protected void validateForCreate(Map map){
        LOGGER.debug("OmcSchemaSysBizClass.validateForCreate Start");
        super.validateForCreate(map);
        this.valueValidate(map);
        if(!this.isExistsParent(map)) throw new FoundationException("omc.schema.relclass.notfound");//Bisiness Class is Duplicated;
        if(!isNew(map)) throw new FoundationException("omc.schema.relclass.alreadyexists");//Bisiness Class is Duplicated
    }
    @Override
    protected void preProcessForCreate(Map map){
        super.preProcessForCreate(map);
        this.setParentClassObid(map);
    }
    @Override
    protected void postProcessForCreate(Map map){
        super.postProcessForCreate(map);
        this.setProperties(map);
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
        this.setParentClassObid(map);
    }
    @Override
    protected void postProcessForModify(Map map){
        super.postProcessForModify(map);
        this.setProperties(map);
    }
    
    public static void refreshAllTempRelObid(){
        OmcSchemaServiceUtils.refreshAllTempRelObid();
    }
    public static List<OmcSchemaSysRelClassVO> getNotFoundParntRelClassList(){
        return(OmcSchemaServiceUtils.getNotFoundParntRelClassList());
    }
    public static List<OmcSchemaSysRelClassVO> getDuplicatedRelClassList(){
        return(OmcSchemaServiceUtils.getDuplicatedRelClassList());
    }
    public static List<OmcSchemaSysRelClassVO> getAllRelTemp(){
        return(OmcSchemaServiceUtils.getAllRelTemp());
    }
    public static List<OmcSchemaSysRelClassVO> getChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel){
        return(OmcSchemaServiceUtils.getChildSystemRelClassList(firstInclude, rslt, className, wantedLevel));
    }
    public static List<OmcSchemaSysRelClassVO> getTemporaryChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className,int wantedLevel){
        return(OmcSchemaServiceUtils.getTemporaryChildSystemRelClassList(firstInclude, rslt, className, wantedLevel));
    }
    public static List<OmcSchemaSysRelClassVO> getParentClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel){
        return(OmcSchemaServiceUtils.getParentSystemRelClassList(firstInclude, rslt, className, wantedLevel));
    }
    public static void refreshAllowedClass(){
        
    }
    public static List<OmcSchemaSysRelClassVO> getAll(boolean activeOnly){
        return(OmcSchemaServiceUtils.getAllRel(activeOnly));
    }
    public static boolean checkClassHeiarchy(){
        List<OmcSchemaSysRelClassVO> allList = getAllRelTemp();
        List<OmcSchemaSysRelClassVO> allChildList = getTemporaryChildSystemRelClassList(true, null, OmcSystemConstants.OBJECT_ROOT, 100);
        if(allList.size() != allChildList.size()) return false;
        return true;
    }
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select b.obid                      as obid                  ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames                    as names                 ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames_parent             as names_parent          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_table               as dbms_table            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name           as displayed_name        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name_kr        as displayed_name_kr     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pallow_duplicate_str      as allow_duplicate_str   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pis_instantiable_str      as is_instantiable_str   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pfrom_class               as from_class            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pto_class                 as to_class              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pfrom_relationship        as from_relationship     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pto_relationship          as to_relationship       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.prelation_from_meaning    as relation_from_meaning ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.prelation_to_meaning      as relation_to_meaning   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcardinality_from_str     as cardinality_from_str  ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcardinality_to_str       as cardinality_to_str    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.prevision_rule_from_str   as revision_rule_from_str,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.prevision_rule_to_str     as revision_rule_to_str  ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pjava_package             as java_package          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodule_name              as module_name           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks                  as remarks               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners                   as owners                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences                as sequences             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments          as change_comments       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysrelobjectclassinfo a left outer join psysrelobjectclassinfo b");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("on a.pnames = b.pnames");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid                      as obid                 ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags                    as flags                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames                    as names                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.pnames from  psysrelobjectclassinfo x where a.pparent_obid = x.obid)      as names_parent   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_table               as dbms_table           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name           as displayed_name       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name_kr        as displayed_name_kr    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pfrom_class               as from_class           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pto_class                 as to_class             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pfrom_relationship        as from_relationship    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pto_relationship          as to_relationship      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pparent_obid              as parent_obid          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.prelation_from_meaning    as relation_from_meaning ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.prelation_to_meaning      as relation_to_meaning   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcardinality_from         as cardinality_from     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcardinality_to           as cardinality_to       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.prevision_rule_from       as revision_rule_from   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.prevision_rule_to         as revision_rule_to     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pjava_package             as java_package         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodule_name              as module_name          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks                  as remarks              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners                   as owners               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences                as sequences            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments          as change_comments      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator                  as creator              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated                  as created              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier                 as modifier             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified                 as modified             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       exists(select * from psysallowedclassforrel x where a.obid = x.pclass_obid)  as is_referenced");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysrelobjectclassinfo a");
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Instantiable));
    }
    public static List<OmcSchemaSysRelClassVO> getInactiveRelListForUpload(){
        return(OmcSchemaServiceUtils.getInactiveRelListForUpload());
    }
    
    private void setProperties(Map map){
        OmcSchemaSysRelClassVO dataBaseVO = null;
        if(map != null) dataBaseVO = (OmcSchemaSysRelClassVO)map.get("dataBaseVO");
        //if (dataBaseVO == null) {
            OmcSchemaServiceUtils.setRelClassProperty(this.getVo(), "RELCLASS_" + this.getVo().getNames().toUpperCase(), this.getVo().getNames());          
        //}    
    }
    public static void refreshAllowedClassBatch(){
        OmcSchemaServiceUtils.refreshAllowedClassBatch();
    }
    public boolean isEqual(OmcSchemaSysRelClassVO vo){
        OmcSchemaSysRelClassVO thisVo = this.getVo();
        if(!StrUtil.isEqual(thisVo.getObid(), vo.getObid())) return false;
        if(!StrUtil.isEqual(thisVo.getNames(), vo.getNames())) return false;
        if(!StrUtil.isEqual(thisVo.getParentObid(), vo.getParentObid())) return false;
        if(!StrUtil.isEqual(thisVo.getDisplayedName(), vo.getDisplayedName())) return false;
        if(!StrUtil.isEqual(thisVo.getJavaPackage(), vo.getJavaPackage())) return false;
        if(!StrUtil.isEqual(thisVo.getNamesParent(), vo.getNamesParent())) return false;
        if(thisVo.getIsInstantiable() != vo.getIsInstantiable()) return false;

        if(!StrUtil.isEqual(thisVo.getFromClass(), vo.getFromClass())) return false;
        if(!StrUtil.isEqual(thisVo.getToClass(), vo.getToClass())) return false;
        if(!StrUtil.isEqual(thisVo.getFromRelationship(), vo.getFromRelationship())) return false;
        if(!StrUtil.isEqual(thisVo.getToRelationship(), vo.getToRelationship())) return false;
        if(thisVo.getAllowDuplicate() != vo.getAllowDuplicate()) return false;
        if(thisVo.getCardinalityFrom() != vo.getCardinalityFrom()) return false;
        if(thisVo.getCardinalityTo() != vo.getCardinalityTo()) return false;
        
        if(thisVo.getRevisionRuleFrom() != vo.getRevisionRuleFrom()) return false;
        if(thisVo.getRevisionRuleTo() != vo.getRevisionRuleTo()) return false;
        
        if(!StrUtil.isEqual(String.valueOf(thisVo.getSequences()), String.valueOf(vo.getSequences()))) return false;
        if(!StrUtil.isEqual(thisVo.getModuleName(), vo.getModuleName())) return false;
        if(!StrUtil.isEqual(thisVo.getOwners(), vo.getOwners())) return false;
        if(!StrUtil.isEqual(thisVo.getRemarks(), vo.getRemarks())) return false;
        if(!StrUtil.isEqual(thisVo.getChangeComments(), vo.getChangeComments())) return false;
        return true;
    }
    
    private boolean isNew(Map map){
        OmcSchemaSysRelClassVO relVO = OmcSchemaServiceUtils.getSystemRelClassWithNames(this.getVo().getNames());
        if (relVO == null) return true;
        map.put("savedVO", relVO);
        return false;
    }
    private boolean isExistsParent(Map map){
        if (!OmcSystemConstants.OBJECT_ROOT.equals(this.getVo().getNames())){
            OmcSchemaSysRelClassVO relVO = OmcSchemaServiceUtils.getSystemRelClassWithNames(this.getVo().getNamesParent());
            if (relVO != null) {
                map.put("parentClassVO", relVO);
                return true;
            }
            return false;
        }
        return true;
    }
    private void valueValidate(Map map){
        OmcSchemaSysRelClassVO thisVO = this.getVo();
        if (StrUtil.isEmpty(thisVO.getNamesParent())) throw new FoundationException("omc.schema.relclass.alreadyexists");
        if (StrUtil.isEmpty(thisVO.getNames())) throw new FoundationException("omc.schema.relclass.alreadyexists");
    }
    private void setParentClassObid(Map map){
        OmcSchemaSysRelClassVO thisVO = this.getVo();
        if(thisVO.getNames().equals(OmcSystemConstants.OBJECT_ROOT)) {
            thisVO.setParentObid("1");
        }
        else{
            OmcSchemaSysRelClassVO parentClassVO = (OmcSchemaSysRelClassVO)map.get("parentClassVO");
            if (NullUtil.isNull(parentClassVO)){
                parentClassVO = OmcSchemaServiceUtils.getSystemRelClassWithNames(thisVO.getNamesParent());
            }
            thisVO.setParentObid(parentClassVO.getObid());
        }
        this.setVo(thisVO);
    }
    public List<OmcSchemaSysClassAttrInfoVO> getAttribute(){
        return(OmcSchemaServiceUtils.getAttributeByClass(true,this.getVo().getNames()));
    }
    public static void uploadTemporaryList(List<OmcSchemaSysRelClassVO> list){
        OmcSchemaServiceUtils.uploadTemporaryRelClass(list);
    }
    public static void setInstantiableFlagsForRelClass(){
        OmcSchemaServiceUtils.setInstantiableFlagsForRelClass();
    }
}
