/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysBizClass.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaSysBizClass
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class OmcSchemaSysBizClass extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObject.class);
    /**
     * @param vo
     */
    public OmcSchemaSysBizClass(OmcSchemaSysRootVO vo) {
        super(vo);
    }
    public OmcSchemaSysBizClass(String obid) {
        super(OmcSchemaServiceUtils.getSystemBizClassWithObid(obid));
    }
    @Override
    public OmcSchemaSysBizClassVO getVo(){
        return (OmcSchemaSysBizClassVO)vo;
    }
    protected void create(Map map){
        OmcSchemaServiceUtils.createSystemBizClass(this.getVo());
    }
    protected void delete(Map map){
        OmcSchemaServiceUtils.deleteSystemBizClass(this.getVo());
    }
    protected void modify(Map map){
        OmcSchemaServiceUtils.modifySystemBizClass(this.getVo());
    }
    protected void inActiviate(Map map){
        OmcSchemaServiceUtils.inactivateSystemBizClass(this.getVo());
    }
    public OmcSchemaSysRootVO getObjectInfoByName(String Names){
        return(OmcSchemaServiceUtils.getSystemBizClassWithObid(Names));
    }
    //Called by Root
    protected void setFlags(){
        
        OmcSchemaSysBizClassVO bizClassVO = this.getVo();
        long flags = Bit.or(OmcSystemConstants.BUSINESS_FLAG_Default,OmcSystemConstants.BUSINESS_FLAG_Active);
        if(bizClassVO.getIsInstantiable()) {
            flags = flags | OmcSystemConstants.BUSINESS_FLAG_Instantiable;
        }
        if(bizClassVO.getApplyWorkflow()) flags = flags | OmcSystemConstants.BUSINESS_FLAG_Workflow;
        if(bizClassVO.getComboDisplay()) flags = flags | OmcSystemConstants.BUSINESS_FLAG_ComboDisplay;
        String classType = this.getTempBizClassType(this.getVo().getNames());
        if(classType.equals(OmcSystemConstants.BUSINESS_OBJECT)) flags = flags | OmcSystemConstants.BUSINESS_FLAG_Revisible;
        bizClassVO.setFlags(flags);
        this.setVo(bizClassVO);
    }
    private long getTempFlags(){
        OmcSchemaSysBizClassVO bizClassVO = this.getVo();
        long flags = Bit.or(OmcSystemConstants.BUSINESS_FLAG_Default,OmcSystemConstants.BUSINESS_FLAG_Active);
        if(bizClassVO.getIsInstantiable()) {
            flags = flags | OmcSystemConstants.BUSINESS_FLAG_Instantiable;
        }
        if(bizClassVO.getApplyWorkflow()) flags = flags | OmcSystemConstants.BUSINESS_FLAG_Workflow;
        if(bizClassVO.getComboDisplay()) flags = flags | OmcSystemConstants.BUSINESS_FLAG_ComboDisplay;
        String classType = this.getTempBizClassType(this.getVo().getNames());
        if(classType.equals(OmcSystemConstants.BUSINESS_OBJECT)) flags = flags | OmcSystemConstants.BUSINESS_FLAG_Revisible;
        return flags;
    }
  //Called by Root
    protected void setClassKind(){
        OmcSchemaSysBizClassVO bizClassVO = this.getVo();
        bizClassVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_BOClass);
        this.setVo(bizClassVO);
    }
    @Override
    protected void validateForCreate(Map map){
        LOGGER.debug("OmcSchemaSysBizClass.validateForCreate Start");
        super.validateForCreate(map);
        this.valueValidate(map);
        if(!this.isExistsParent(map)) throw new FoundationException("omc.schema.bizclass.notfound");//Bisiness Class is Duplicated;
        if(!isNew(map)) throw new FoundationException("omc.schema.bizclass.alreadyexists");//Bisiness Class is Duplicated
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
        this.setFlags();
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
    private boolean isNew(Map map){
        OmcSchemaSysBizClassVO bizClassVO = OmcSchemaServiceUtils.getSystemBizClassWithObid(this.getVo().getObid());
        if (bizClassVO == null) return true;
        return false;
    }
    
    private boolean isExistsParent(Map map){
        if (!OmcSystemConstants.OBJECT_ROOT.equals(this.getVo().getNames())){
            OmcSchemaSysBizClassVO parentClassVO = OmcSchemaServiceUtils.getSystemBizClassWithNames(this.getVo().getNamesParent());
            if (parentClassVO != null) {
                map.put("parentClassVO", parentClassVO);
                return true;
            }
            return false;
        }
        return true;

    }
    private void valueValidate(Map map){
        OmcSchemaSysBizClassVO thisVO = this.getVo();
        if (StrUtil.isEmpty(thisVO.getNamesParent())) throw new FoundationException("omc.schema.bizclass.alreadyexists");
        if (StrUtil.isEmpty(thisVO.getNames())) throw new FoundationException("omc.schema.bizclass.alreadyexists");
    }
    private void setParentClassObid(Map map){
        OmcSchemaSysBizClassVO bizClassVO = this.getVo();
        if(bizClassVO.getNames().equals(OmcSystemConstants.OBJECT_ROOT)) {
            bizClassVO.setParentObid("1");
        }
        else{
            OmcSchemaSysBizClassVO parentClassVO = (OmcSchemaSysBizClassVO)map.get("parentClassVO");
            if (NullUtil.isNull(parentClassVO)){
                parentClassVO = OmcSchemaServiceUtils.getSystemBizClassWithNames(bizClassVO.getNamesParent());
            }
            bizClassVO.setParentObid(parentClassVO.getObid());
        }
        this.setVo(bizClassVO);
    }
    private String getBizClassType(String bizClassName){
        OmcSchemaSysBizClassVO bizlassVO = OmcSchemaServiceUtils.getSystemBizClassWithNames(bizClassName);
        return(getBizClassType(bizlassVO));
    }
    private String getTempBizClassType(String bizClassName){
        OmcSchemaSysBizClassVO bizlassVO = OmcSchemaServiceUtils.getTemporarySystemBizClassWithNames(bizClassName);
        return(getTempBizClassType(bizlassVO));
    }
    private String getTempBizClassType(OmcSchemaSysBizClassVO bizClassVO){
        if(bizClassVO.getNames().equals(OmcSystemConstants.OBJECT_ROOT)) return OmcSystemConstants.OBJECT_ROOT;
        if(bizClassVO.getNamesParent().equals(OmcSystemConstants.OBJECT_ROOT) || bizClassVO.getNamesParent().equals(OmcSystemConstants.BUSINESS_OBJECT) || bizClassVO.getNamesParent().equals(OmcSystemConstants.BUSINESS_OBJECT_MASTER)) return bizClassVO.getNamesParent();
        return getTempBizClassType(bizClassVO.getNamesParent());
    }
    private String getBizClassType(OmcSchemaSysBizClassVO bizClassVO){
        if(bizClassVO.getNames().equals(OmcSystemConstants.OBJECT_ROOT)) return OmcSystemConstants.OBJECT_ROOT;
        if(bizClassVO.getNamesParent().equals(OmcSystemConstants.OBJECT_ROOT) || bizClassVO.getNamesParent().equals(OmcSystemConstants.BUSINESS_OBJECT) || bizClassVO.getNamesParent().equals(OmcSystemConstants.BUSINESS_OBJECT_MASTER)) return bizClassVO.getNamesParent();
        return getBizClassType(bizClassVO.getNamesParent());
    }
    public static void refreshAllTempBizObid(){
        OmcSchemaServiceUtils.refreshAllTempBizObid();
    }
    public static List<OmcSchemaSysBizClassVO> getNotFoundParntBizClassList(){
        return(OmcSchemaServiceUtils.getNotFoundParntBizClassList());
    }
    public static List<OmcSchemaSysBizClassVO> getDuplicatedBizClassList(){
        return(OmcSchemaServiceUtils.getDuplicatedBizClassList());
    }
    public static List<OmcSchemaSysBizClassVO> getAllBizTemp(){
        return(OmcSchemaServiceUtils.getAllBizTemp());
    }
    public static List<OmcSchemaSysBizClassVO> getChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel){
        return(OmcSchemaServiceUtils.getChildSystemBizClassList(firstInclude, rslt, className, wantedLevel));
    }
    public static List<OmcSchemaSysBizClassVO> getTemporaryChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className,int wantedLevel){
        return(OmcSchemaServiceUtils.getTemporaryChildSystemBizClassList(firstInclude, rslt, className, wantedLevel));
    }
    
    public static List<OmcSchemaSysBizClassVO> getParentClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel){
        return(OmcSchemaServiceUtils.getParentSystemBizClassList(firstInclude, rslt, className, wantedLevel));
    }
    
    public static boolean checkClassHeiarchy(){
        List<OmcSchemaSysBizClassVO> allTempBizClassList = getAllBizTemp();
        List<OmcSchemaSysBizClassVO> allChildBizClassList = getTemporaryChildSystemBizClassList(true, null, OmcSystemConstants.OBJECT_ROOT, 100);
        if(allTempBizClassList.size() != allChildBizClassList.size()) return false;
        return true;
    }
    public static List<OmcSchemaSysBizClassVO> getInactiveListForUpload(){
        return(OmcSchemaServiceUtils.getInactiveBizListForUpload());
    }
    public static List<OmcSchemaSysBizClassVO> getAll(boolean activeOnly){
        return(OmcSchemaServiceUtils.getAllBiz(activeOnly));
    }

    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.pnames             as names               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames_parent      as names_parent        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_policy    as default_policy      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_table        as dbms_table          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pis_instantiable   as is_instantiable_str ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pjava_package      as java_package        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name    as displayed_name      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name_kr as displayed_name_kr   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcombo_display     as combo_display_str   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.papply_workflow    as apply_workflow_str  ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pworkflow_url      as workflow_url        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodule_name       as module_name         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks           as remarks             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_icon        as class_icon          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_icon_small  as class_icon_small    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners            as owners              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences         as sequences           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments   as change_comments     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.obid from  psysbizobjectclassinfo x where a.pnames        = x.pnames)  as obid ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.obid from  psysbizobjectclassinfo x where a.pnames_parent = x.pnames)  as parent_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysbizobjectclassinfo a"                      );
    }

    
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.pnames               as names               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags               as flags               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.pnames from  psysbizobjectclassinfo x where a.pparent_obid = x.obid)      as names_parent   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_policy      as default_policy      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_table          as dbms_table          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pjava_package        as java_package        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name      as displayed_name      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name_kr   as displayed_name_kr  ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pworkflow_url        as workflow_url        ,");
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences           as sequences           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments     as change_comments     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodule_name         as module_name         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks             as remarks             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_icon          as class_icon          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_icon_small    as class_icon_small    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.powners              as owners              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator             as creator             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated             as created             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier            as modifier            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified            as modified            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.obid                 as obid                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       exists(select * from psysallowedclassforrel x where a.obid = x.pclass_obid)  as is_referenced ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pparent_obid         as parent_obid"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysbizobjectclassinfo a ");
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Instantiable));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_ComboDisplay));
        variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Workflow));
        variableParameter.setFunVariable_00005(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Revisible));
        variableParameter.setFunVariable_00006(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
    }
    private void setProperties(Map map){
        OmcSchemaSysBizClassVO dataBaseVO = null;// = new OmcSchemaSysBizClassVO();
        if(map != null) dataBaseVO = (OmcSchemaSysBizClassVO)map.get("dataBaseVO");
        OmcSchemaServiceUtils.setBizClassProperty(this.getVo(), "BIZCLASS_" + this.getVo().getNames().toUpperCase(), this.getVo().getNames());
    }
    public boolean isEqual(OmcSchemaSysBizClassVO vo){
        OmcSchemaSysBizClassVO thisVo = this.getVo();
        if(vo.getFlags() != this.getTempFlags()) return false;
        if(!StrUtil.isEqual(thisVo.getObid(), vo.getObid())) return false;
        if(!StrUtil.isEqual(thisVo.getNames(), vo.getNames())) return false;
        if(!StrUtil.isEqual(thisVo.getParentObid(), vo.getParentObid())) return false;
        if(!StrUtil.isEqual(thisVo.getDisplayedName(), vo.getDisplayedName())) return false;
        if(!StrUtil.isEqual(thisVo.getJavaPackage(), vo.getJavaPackage())) return false;
        if(!StrUtil.isEqual(thisVo.getNamesParent(), vo.getNamesParent())) return false;
        if(!StrUtil.isEqual(thisVo.getDefaultPolicy(), vo.getDefaultPolicy())) return false;
        if(!StrUtil.isEqual(thisVo.getWorkflowUrl(), vo.getWorkflowUrl())) return false;
        if(thisVo.getApplyWorkflow() != vo.getApplyWorkflow()) return false;
        if(thisVo.getComboDisplay() != vo.getComboDisplay()) return false;
        if(thisVo.getIsInstantiable() != vo.getIsInstantiable()) return false;
        
        if(!StrUtil.isEqual(String.valueOf(thisVo.getSequences()), String.valueOf(vo.getSequences()))) return false;
        if(!StrUtil.isEqual(thisVo.getModuleName(), vo.getModuleName())) return false;
        if(!StrUtil.isEqual(thisVo.getClassIcon(), vo.getClassIcon())) return false;
        if(!StrUtil.isEqual(thisVo.getClassIconSmall(), vo.getClassIconSmall())) return false;
        if(!StrUtil.isEqual(thisVo.getOwners(), vo.getOwners())) return false;
        if(!StrUtil.isEqual(thisVo.getRemarks(), vo.getRemarks())) return false;
        if(!StrUtil.isEqual(thisVo.getChangeComments(), vo.getChangeComments())) return false;
        
        return true;
    }
    public void makeRevisible(){
        OmcSchemaServiceUtils.makeBizClassWorkflowable(this.vo.getObid());
    }
    public void makeWorkflowable(){
        OmcSchemaServiceUtils.makeBizClassWorkflowable(this.vo.getObid());
    }
    public static HashMap<String, OmcSchemaSysBizClassVO> getWorkflowableList(){
        return(OmcSchemaServiceUtils.getWorkflowableList());
    }
    public static void getCommonClassInfoSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid                    as obid             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags                  as flags            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames                  as names            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdbms_table             as dbms_table       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags_class            as flags_class      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name         as displayed_name   ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_obid             as class_obid       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pclass_parent_obid      as class_parent_obid,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator                as creator          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated                as created          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier               as modifier         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified               as modified         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysclassinfo a ");
    }
    public static void refreshClassInfo(){
        OmcSchemaServiceUtils.refreshClassInfo();
    }
    public List<OmcSchemaSysClassAttrInfoVO> getAttribute(){
        return(OmcSchemaServiceUtils.getAttributeByClass(false,this.getVo().getNames()));
    }
    public static void uploadTemporaryList(List<OmcSchemaSysBizClassVO> list){
        OmcSchemaServiceUtils.uploadTemporaryBizClass(list);
    }
    public static void setInstantiableFlagsForBizClass(){
        OmcSchemaServiceUtils.setInstantiableFlagsForBizClass();
    }
}
