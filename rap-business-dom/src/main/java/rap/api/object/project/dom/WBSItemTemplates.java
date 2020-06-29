/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSItemTemplates.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.document.model.ProjectActivityDocumentTemplateVO;
import rap.api.object.organization.dom.Users;
import rap.api.object.project.model.ActivityTemplateMasterVO;
import rap.api.object.project.model.ProjectRoleVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSTemplateMasterVO;
import rap.api.object.relation.dom.AllocatedRoleToWBSTemplateItems;
import rap.api.object.relation.dom.HasActivityTemplate;
import rap.api.object.relation.dom.TemplateDependency;
import rap.api.object.relation.dom.WBSManagedBy;
import rap.api.object.relation.model.RecommendedDocumentTemplateVO;
import rap.api.object.relation.model.WBSManagedByVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;


public class WBSItemTemplates extends BusinessObject {
    public WBSItemTemplates(String obid){
        super(obid);
    }
    public WBSItemTemplates(WBSItemTemplatesVO vo){
        super(vo);
    }
     @Override
    public WBSItemTemplatesVO getVo(){
        return (WBSItemTemplatesVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeWBSItemTemplates();
    }
    public void initializeWBSItemTemplates(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "WBSItemTemplates[toString()=" + super.toString() + "]";
    }


     @Override
    protected void validateForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.validateForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

     @Override
    protected void preProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.preProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

     @Override
    protected void postProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.postProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

    @Override
    protected void validateForCreate(Map<String, Object> map){
        super.validateForCreate(map);
        /*code below*/
        validateMandatoryAttributes();
        
        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster((String)map.get("wbsTemplateMasterObid"));
        if(!wbsTemplateMaster.canBeAdded(getVo())){
            throw new ApplicationException(getDisplayNameAsLoc() + " can not be added.");
        }
    }

    @Override
    protected void preProcessForCreate(Map<String, Object> map){
        super.preProcessForCreate(map);
        /*code below*/
        resetAttributeValue();

    }
    private void resetAttributeValue(){
        if(StrUtil.isEmpty(this.getVo().getIsMilestone())) this.getVo().setIsMilestone("N");
        if(this.getVo().getIsMilestone().equals("Y")) this.getVo().setStandardDuration(0);
    }
    @Override
    protected void postProcessForCreate(Map<String, Object> map){
        super.postProcessForCreate(map);
        /*code below*/
        String addingPosition = (String)map.get("addingPosition");
        Integer targetSequences =  (Integer)map.get("targetSequences");
        Map<String, Object> attrMap = new HashMap<String, Object>();
        if("previous".equals(addingPosition)){
            targetSequences = HasActivityTemplate.getPrevSequences((String)map.get("parentObid"), targetSequences);
        }else if("next".equals(addingPosition)){
            targetSequences = HasActivityTemplate.getNextSequences((String)map.get("parentObid"), targetSequences);
        }
        
        if(NullUtil.isNull(targetSequences)){
            targetSequences = HasActivityTemplate.getLatestSequences((String)map.get("parentObid"));  
        }
        
        attrMap.put("sequences", targetSequences);
        
        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster((String)map.get("wbsTemplateMasterObid"));
        addFromObject(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, DomUtil.toDom((String)map.get("parentObid")).getVo(), attrMap);
        addToObject(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, wbsTemplateMaster.getVo(), null);
    }

    @Override
    protected void validateForDelete(Map<String, Object> map){
        super.validateForDelete(map);
        /*code below*/
        if(!canBeTempateCUD()) throw new FoundationException("Cannot CUD.");
    }

    @Override
    protected void preProcessForDelete(Map<String, Object> map){
        super.preProcessForDelete(map);
        /*code below*/

    }

    @Override
    protected void postProcessForDelete(Map<String, Object> map){
        super.postProcessForDelete(map);
        /*code below*/

    }

    @Override
    protected void validateForModify(Map<String, Object> map){
        super.validateForModify(map);
        /*code below*/

    }

    @Override
    protected void preProcessForModify(Map<String, Object> map){
        super.preProcessForModify(map);
        /*code below*/

    }

    @Override
    protected void postProcessForModify(Map<String, Object> map){
        super.postProcessForModify(map);
        /*code below*/

    }

    @Override
    protected void validateForWithdraw(Map<String, Object> map){
        super.validateForWithdraw(map);
        /*code below*/

    }

    @Override
    protected void preProcessForWithdraw(Map<String, Object> map){
        super.preProcessForWithdraw(map);
        /*code below*/

    }

    @Override
    protected void postProcessForWithdraw(Map<String, Object> map){
        super.postProcessForWithdraw(map);
        /*code below*/

    }

    @Override
    protected void validateForDemote(Map<String, Object> map){
        super.validateForDemote(map);
        /*code below*/

    }

    @Override
    protected void preProcessForDemote(Map<String, Object> map){
        super.preProcessForDemote(map);
        /*code below*/

    }

    @Override
    protected void postProcessForDemote(Map<String, Object> map){
        super.postProcessForDemote(map);
        /*code below*/

    }

    @Override
    protected void validateForPromote(Map<String, Object> map){
        super.validateForPromote(map);
        /*code below*/

    }

    @Override
    protected void preProcessForPromote(Map<String, Object> map){
        super.preProcessForPromote(map);
        /*code below*/

    }

    @Override
    protected void postProcessForPromote(Map<String, Object> map){
        super.postProcessForPromote(map);
        /*code below*/

    }

    @Override
    protected void validateForClone(Map<String, Object> map){
        super.validateForClone(map);
        /*code below*/

    }

    @Override
    protected void preProcessForClone(Map<String, Object> map){
        super.preProcessForClone(map);
        /*code below*/

    }

    @Override
    protected void postProcessForClone(Map<String, Object> map){
        super.postProcessForClone(map);
        /*code below*/

    }

    @Override
    protected void validateForRevise(Map<String, Object> map){
        super.validateForRevise(map);
        /*code below*/
        if(this.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_WORKING)) throw new FoundationException("Cannot revise. current state is " + this.getStates());
    }

    @Override
    protected void preProcessForRevise(Map<String, Object> map){
        super.preProcessForRevise(map);
        /*code below*/

    }

    @Override
    protected void postProcessForRevise(Map<String, Object> map){
        super.postProcessForRevise(map);
        /*code below*/
        WBSTemplateMaster wbsTemplateMasterDom = (WBSTemplateMaster)map.get(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom);
        if(NullUtil.isNull(wbsTemplateMasterDom)) throw new FoundationException("Template Objedt is null.");
        WBSItemTemplatesVO revisedItemTemplatesVO = (WBSItemTemplatesVO)map.get(OmcApplicationConstants.MAP_API_REVISE_NEW_OBJECT);
        WBSItemTemplates revisedItemTemplatesDom = DomUtil.toDom(revisedItemTemplatesVO);

        StringBuffer selectPattern    = new StringBuffer();
        StringBuffer wherePattern    = new StringBuffer();
        StringBuffer parameterPattern    = new StringBuffer();
      
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, wbsTemplateMasterDom.getObid());
        
        List<WBSManagedByVO> managedByList = this.getRelationship(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO, 
                selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        for(WBSManagedByVO vo : managedByList){
            WBSManagedBy managedByDom = new WBSManagedBy(vo);
            managedByDom.deleteObject();
        }
        revisedItemTemplatesDom.addToObject(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, wbsTemplateMasterDom.getVo(), null);
        TemplateDependency.updateWBSTemplateItmesDependency(wbsTemplateMasterDom,revisedItemTemplatesVO.getObid());
    }
    @Override
    public String getCommonTitlesForDisplay(){
        String userLocale = Users.getUserLocale(ThreadLocalUtil.getUserId());
        return getCommonTitlesForDisplaySub(userLocale);
    }
    @Override
    public String getCommonTitlesForDisplay(String locale){
        return getCommonTitlesForDisplaySub(locale);
    }
    private String getCommonTitlesForDisplaySub(String locale){
        String displayedTitles = "";
        if(StrUtil.isEmpty(locale)) locale = ApplicationBizConstants.DEFAULT_LANG;
        if(!StrUtil.in(locale, ApplicationBizConstants.LANG_VALID_SET)) locale = ApplicationBizConstants.DEFAULT_LANG;
        if(locale.equals(ApplicationBizConstants.LANG_EN)) displayedTitles = this.getVo().getActivityNameEng();
        if(locale.equals(ApplicationBizConstants.LANG_KO)) displayedTitles = this.getVo().getActivityNameKor();
        if(locale.equals(ApplicationBizConstants.LANG_CH)) displayedTitles = this.getVo().getActivityNameChi();
        
        if(StrUtil.isEmpty(displayedTitles) && !StrUtil.isEmpty(this.getVo().getActivityNameEng())) displayedTitles = this.getVo().getActivityNameEng();
        if(StrUtil.isEmpty(displayedTitles) && !StrUtil.isEmpty(this.getVo().getActivityNameKor())) displayedTitles = this.getVo().getActivityNameKor();
        if(StrUtil.isEmpty(displayedTitles) && !StrUtil.isEmpty(this.getVo().getActivityNameChi())) displayedTitles = this.getVo().getActivityNameChi();
        if(StrUtil.isEmpty(displayedTitles)) displayedTitles = this.getTitles();
        return displayedTitles;
    }
    private void validateMandatoryAttributes(){
        if(StrUtil.isEmpty(getVo().getActivityNameEng())){
            throw new ApplicationException("Activity Name Eng can not be empty");
        }
    }
    
    public boolean hasSubActivity(String templateMasterObid){

        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", 
                GlobalConstants.OQL_OPERATOR_EQUAL, templateMasterObid );
        
        List<WBSItemTemplatesVO> list =  this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_TO, wherePatternBuf.toString(), paramPatternBuf.toString(), 1);
        if(NullUtil.isNone(list)) return false;
        return true;
    }
   
    public List<WBSItemTemplatesVO> getSubWBSItemTemplate(String templateMasterObid, boolean isActivityOnly, int findDepth){
    
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", 
                                         GlobalConstants.OQL_OPERATOR_EQUAL, templateMasterObid );
        
        if(isActivityOnly){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBACTIVITY+"].obid", 
                    GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        }
        
        StringUtil.addSortByPattern(selectPatternBuf, "@REL.[sequences] asc");
        
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, 
                                 ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, 
                                 GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(),
                                 wherePatternBuf.toString(), paramPatternBuf.toString(), 
                                 false, true, 0, findDepth);
    }
    
    public void setDefault(boolean generateName){
        if(generateName){
            getVo().setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WBS_ITEM_TEMPLATES));
        }
        if(StrUtil.isEmpty(getVo().getTitles())){
            getVo().setTitles(".");
        }
        getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WBS_TEMPLATE_ITEM);
        getVo().setStates(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_WORKING);
        getVo().setRevision("1");
    }
    
    public ActivityTemplateMasterVO getRelatedTemplateMaster(){
        return getRelatedObject(ApplicationSchemaConstants.RELCLASS_RELATEDTEMPLATEMASTER, ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO);
    }

    public List<BusinessRelationObjectVO> getTemplateDependency(String templateMasterObid){
        
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, templateMasterObid );
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
        if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
            StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES+"].activityNameKor activityName");
        }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
            StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES+"].activityNameChi activityName");
        }else{
            StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES+"].activityNameEng activityName");
        }
        return getRelationship(ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, 
                ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, 
                GlobalConstants.FLAG_TYPE_TO,
                selectPatternBuf.toString(), 
                wherePatternBuf.toString(), 
                paramPatternBuf.toString());
    }
    
    public List<ProjectActivityDocumentTemplateVO> retrieveProjectActivityDocumentTemplate(){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE,
                ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE,
                GlobalConstants.FLAG_TYPE_TO,
                selectPatternBuf.toString(), 
                wherePatternBuf.toString(), 
                paramPatternBuf.toString(), 
                false, 
                true, 
                0, 
                1);
    }
    public RecommendedDocumentTemplateVO getRelatedRecomendedDocumentTemplateByName(String projectActivityDocumentTemplateNames){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].documentNameEng documentNameEng");
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].documentNameKor documentNameKor");
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].documentNameChi documentNameChi");
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].states documentStates");
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].names", GlobalConstants.OQL_OPERATOR_EQUAL, projectActivityDocumentTemplateNames);   
        List<RecommendedDocumentTemplateVO> relationship = getRelationship(ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE, 
                ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE, 
                GlobalConstants.FLAG_TYPE_TO,
                selectPatternBuf.toString(), 
                wherePatternBuf.toString(), 
                paramPatternBuf.toString());
        if(relationship.size() > 0){
            return relationship.get(0);
        }
        return null;
    }
    public List<BusinessRelationObjectVO> retrieveRecomendedDocumentTemplate(String wbsTemplateMasterObid){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].names documentNames");
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].documentNameEng documentNameEng");
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].documentNameKor documentNameKor");
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].documentNameChi documentNameChi");
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].states documentStates");
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].revision documentRevision");
        StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"].previousObid documentPreviousObid");
//        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From[" + ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY + "].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, wbsTemplateMasterObid);   
        return getRelationship(ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE, 
                               ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE, 
                               GlobalConstants.FLAG_TYPE_TO,
                               selectPatternBuf.toString(), 
                               wherePatternBuf.toString(), 
                               paramPatternBuf.toString());
    }
    
    public List<ProjectRoleVO> retrieveRecommentedDocumentTemplates(){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE, 
                                ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE, 
                                GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(),
                                wherePatternBuf.toString(), paramPatternBuf.toString(), 
                                false, true, 0, 1);
    }
    
    public List<ProjectRoleVO> getAllocatedRole(WBSTemplateMaster wbsTemplateMasterDom){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLETOWBSTEMPLATEITEMS, 
                ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, 
                GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(),
                wherePatternBuf.toString(), paramPatternBuf.toString(), 
                false, true, 0, 1);
    }
    
    public void updateProjectRole(WBSTemplateMaster wbsTemplateMasterDom, String roleCodeList){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, wbsTemplateMasterDom);
        Set<String> projectRoleCodeList =  new HashSet<String>();
        if(StrUtil.isNotEmpty(roleCodeList)){
            projectRoleCodeList = StrUtil.convertArrayToSet(roleCodeList.split(","));
        }
        List<ProjectRoleVO> allocatedRoleVOList = getAllocatedRole(wbsTemplateMasterDom);
        for(ProjectRoleVO allocatedProjectRoleVO : allocatedRoleVOList){
            boolean isExists = false;
            for(String rolecode : projectRoleCodeList){
                if(allocatedProjectRoleVO.getNames().equals(rolecode)){
                    projectRoleCodeList.remove(rolecode);
                    isExists = true; break;
                }
            }
            if(!isExists){
                new AllocatedRoleToWBSTemplateItems((String)allocatedProjectRoleVO.getOutData().get("rel_obid")).deleteObject(map);
            }
        }
        
        ProjectRoleVO projectRoleVO = null;
        for(String roleCode : projectRoleCodeList){
            projectRoleVO = ProjectRole.getProjectRoleVO(roleCode);
            addToObject(ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLETOWBSTEMPLATEITEMS, projectRoleVO, null,map);
            wbsTemplateMasterDom.addUsedRole(projectRoleVO.getNames());
        }
    }
    
    public final List<WBSTemplateMasterVO> getTemplateMasterList(){
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO);
    }
    
    public final boolean isConnectedOtherContext(WBSTemplateMasterVO masterVO){
        return isConnectedOtherContext(masterVO.getObid());
    }
    public final boolean isConnectedOtherContext(String masterObid){
        List<WBSTemplateMasterVO> list = this.getTemplateMasterList();
        for(WBSTemplateMasterVO vo : list){
            if(!masterObid.equals(vo.getObid())) return true;
        }
        return false;
    }
    public final WBSManagedByVO getWBSManagedBy(String masterObid){
        StringBuffer selectPattern = new StringBuffer (); 
        StringBuffer wherePattern  = new StringBuffer (); 
        StringBuffer paramPattern  = new StringBuffer (); 
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, masterObid);
        List<WBSManagedByVO> list = this.getRelationship(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), paramPattern.toString());
        if(NullUtil.isNone(list)) return null;
        if(list.size() > 1) throw new FoundationException("WBSManagedByVO is invalid.");
        return  list.get(0);
    }
    private boolean canBeTempateCUD(){
        if(this.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_WORKING)) return true;
        if(this.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_INACTIVE)) return true;
        return false;
    }
    public boolean isLeaf(){
        StringBuffer selectPattern = new StringBuffer (); 
        StringBuffer wherePattern  = new StringBuffer (); 
        StringBuffer paramPattern  = new StringBuffer (); 
        StringUtil.constructWherePattern(wherePattern, paramPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE+"].obid",GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        List<BusinessRelationObjectVO> relVOList = this.getRelationship(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), paramPattern.toString());
        if(NullUtil.isNone(relVOList)) return true;
        return false;
    }
    
    public String getDisplayNameAsLoc(){
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
        
        if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
            return getVo().getActivityNameKor();
        }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
            return getVo().getActivityNameChi();
        }else{
            return getVo().getActivityNameEng();
        }
    }
    
    public WBSItemTemplatesVO reviseTemplate(WBSTemplateMaster wbsTemplateMasterDom){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, wbsTemplateMasterDom);
        return (WBSItemTemplatesVO)revise(map);
    }
    /**
     * Cancel Revise�� ��� ���� Object���� Revision�� Context�� ������.
     *
     * @param wbsTemplateMasterDom
     * @return
     */
    public WBSItemTemplatesVO cancelReviseTemplate(WBSTemplateMaster wbsTemplateMasterDom){
        WBSItemTemplatesVO nextVO = null;
        if(!StrUtil.in(this.getStates(), ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_WORKING)) throw new FoundationException("Ony state({}) can be revisible. current state is " + this.getStates(),ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_WORKING);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, wbsTemplateMasterDom);
        this.deleteObject(map);
        if(!this.isFirst()){
            WBSItemTemplates wbsItemTemplatesDom = new WBSItemTemplates(this.getNextObid());
            nextVO = wbsItemTemplatesDom.getVo();
            wbsItemTemplatesDom.addToObject(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, wbsTemplateMasterDom.getVo(), null, map);
            StringBuffer wherePatternBuf = new StringBuffer();
            StringBuffer paramPatternBuf = new StringBuffer();
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, wbsTemplateMasterDom.getObid() );
            List<BusinessRelationObjectVO> nextDependency = wbsItemTemplatesDom.getRelationship(ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_FROM, "", wherePatternBuf.toString(), paramPatternBuf.toString());
            for(BusinessRelationObjectVO relationVO : nextDependency){
                TemplateDependency.updateWBSTemplateItmesDependency(wbsTemplateMasterDom, relationVO.getFromObid());
            }
        }
        return nextVO;
    }
}

