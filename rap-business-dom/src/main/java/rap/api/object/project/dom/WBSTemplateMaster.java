/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSTemplateMaster.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.foundation.LifeCycleUtil;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.dom.DivisionUnit;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.project.model.ActivityTemplateMasterVO;
import rap.api.object.project.model.JobActivityTemplateVO;
import rap.api.object.project.model.ProjectDefinedRoleVO;
import rap.api.object.project.model.ProjectDevelopmentTypeVO;
import rap.api.object.project.model.ProjectGradeVO;
import rap.api.object.project.model.ProjectLifeCycleVO;
import rap.api.object.project.model.ProjectPersonVO;
import rap.api.object.project.model.ProjectPhaseVO;
import rap.api.object.project.model.ProjectRoleVO;
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSActivityTemplateVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.project.model.WBSPhaseTemplateVO;
import rap.api.object.project.model.WBSTemplateMasterVO;
import rap.api.object.relation.dom.AppliedWBSTemplate;
import rap.api.object.relation.dom.UsedRoleAtWBSTemplate;
import rap.api.object.relation.model.AllocatedRoleToWBSTemplateItemsVO;
import rap.api.object.relation.model.AllocatedToMemberVO;
import rap.api.object.relation.model.AssignedToActivityVO;
import rap.api.object.relation.model.ControlledByProjectScheduleContextVO;
import rap.api.object.relation.model.HasActivityTemplateVO;
import rap.api.object.relation.model.HasJobActivityTemplateVO;
import rap.api.object.relation.model.HasSubWBSItemsVO;
import rap.api.object.relation.model.RecommendedDocumentTemplateVO;
import rap.api.object.relation.model.RelatedTemplateMasterVO;
import rap.api.object.relation.model.TemplateDependencyVO;
import rap.api.object.relation.model.UsedRoleAtWBSTemplateVO;
import rap.api.object.relation.model.WBSDependencyVO;
import rap.api.object.relation.model.WBSManagedByVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;
import rap.application.workflow.model.ConvertedPhaseVO;


public class WBSTemplateMaster extends BusinessObject {
	//private static final Logger LOGGER = LoggerFactory.getLogger(WBSTemplateMaster.class);
    public WBSTemplateMaster(String obid){
        super(obid);
    }
    public WBSTemplateMaster(WBSTemplateMasterVO vo){
        super(vo);
    }
     @Override
    public WBSTemplateMasterVO getVo(){
        return (WBSTemplateMasterVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeWBSTemplateMaster();
    }
    public void initializeWBSTemplateMaster(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "WBSTemplateMaster[toString()=" + super.toString() + "]";
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

    }

    @Override
    protected void preProcessForCreate(Map<String, Object> map){
        super.preProcessForCreate(map);
        /*code below*/
        refreshTitles();

    }

    @Override
    protected void postProcessForCreate(Map<String, Object> map){
        super.postProcessForCreate(map);
        /*code below*/

    }

    @Override
    protected void validateForDelete(Map<String, Object> map){
        super.validateForDelete(map);
        /*code below*/

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
        if(!ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE.equals( getVo().getStates() )){
            throw new FoundationException("Current is " + getVo().getStates() + ".");
        }
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
        WBSTemplateMasterVO newVO = (WBSTemplateMasterVO)map.get(OmcApplicationConstants.MAP_API_REVISE_NEW_OBJECT);
        newVO.setIsPublished("N");
        new WBSTemplateMaster(newVO).modifyObject();
    }
    
    public WBSTemplateMasterVO reviseTemplateMaster(){
        return (WBSTemplateMasterVO)this.revise();
    }
    public void cancelReviseTemplateMaster(){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringBuffer selectPattern    = new StringBuffer();
        
        if(!this.isLast()) throw new FoundationException("Selected Template is not Latest. only Latest can be Deleted.");
        if(!ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_WORKING.equals(this.getStates())) throw new FoundationException("Only Working can be Cancelled.");
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid",GlobalConstants.OQL_OPERATOR_NOT_EQUAL, this.getObid());
        List<WBSItemTemplatesVO> wbsItemTemplatesList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        
        if(NullUtil.isNone(wbsItemTemplatesList)){ 
            this.deleteObject();
            return;
        }
        Set<String> strSet = new HashSet<String>();
        
        for(WBSItemTemplatesVO vo : wbsItemTemplatesList){strSet.add(vo.getObid());}
        
        wherePattern.setLength(0);parameterPattern.setLength(0);selectPattern.setLength(0);
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!To["+ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE+"].fromObid",GlobalConstants.OQL_OPERATOR_NOT_IN, StrUtil.convertSet2Str(strSet));
         
        List<JobActivityTemplateVO> jobActivityList = ObjectRoot.getRelatedObjectSet(wbsItemTemplatesList, ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE, 
                GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, this);
        
        for(WBSItemTemplatesVO vo : wbsItemTemplatesList){
            WBSItemTemplates wbsItemTemplatesDom = new WBSItemTemplates(vo);
            wbsItemTemplatesDom.deleteObject(map);
        }
        for(JobActivityTemplateVO vo : jobActivityList){
            JobActivityTemplate jobActivityTemplateDom = new JobActivityTemplate(vo);
            jobActivityTemplateDom.deleteObject(map);
        }
        this.deleteObject();
    }
    
    private void refreshTitles(){
        StringBuffer strBuf = new StringBuffer();
        ProjectLifeCycleVO       lifeCycleVO       = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, this.getVo().getProjectLifeCycle());
        if(NullUtil.isNull(lifeCycleVO)) throw new FoundationException("Life Cycle(" + this.getVo().getProjectLifeCycle() + ") does not exist!!!");
        ProjectDevelopmentTypeVO developmentTypeVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTDEVELOPMENTTYPE, this.getVo().getDevelopmentType());
        if(NullUtil.isNull(developmentTypeVO)) throw new FoundationException("Development Type(" + this.getVo().getDevelopmentType() + ") does not exist!!!");
        strBuf.append(developmentTypeVO.getTitles()).append("/");
        if(!StrUtil.isEmpty(this.getVo().getAppliedGradeList())) strBuf.append("(").append(this.getVo().getAppliedGradeList()).append(")");
        strBuf.append("/").append(this.getDescriptions()).append("/").append(lifeCycleVO.getTitles());
        this.getVo().setTitles(strBuf.toString());
    }
    public void activiate(){
        validateForActiviate();
        activiateCore();
    }
    public void validateForActiviate(){
        if(this.getVo().getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_INACTIVE)) throw new FoundationException("Cannot activiate because disable template.");
    }
    private void activiateCore(){
        List<WBSItemTemplatesVO> wbsItemTemplateList = this.getWBSItemTemplates();
        for(WBSItemTemplatesVO templateVO : wbsItemTemplateList){
            WBSItemTemplates wbsItemTemplatesDom = DomUtil.toDom(templateVO);
            wbsItemTemplatesDom.changePolicyAndState(wbsItemTemplatesDom.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_ACTIVE);
        }
        this.changePolicyAndState(this.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE);
    }
    /**
     * Tempate�� Activiate��.
     */
    public void inActiviate(){
        List<WBSItemTemplatesVO> wbsItemTemplateList = this.getWBSItemTemplates();
        for(WBSItemTemplatesVO templateVO : wbsItemTemplateList){
            WBSItemTemplates wbsItemTemplatesDom = DomUtil.toDom(templateVO);
            wbsItemTemplatesDom.changePolicyAndState(wbsItemTemplatesDom.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_INACTIVE);
        }
        this.changePolicyAndState(this.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_INACTIVE);
    }
    /**
     * Clone Template Master
     *
     * @param targetMasterVO Target Template Master Value Object
     */
    @SuppressWarnings({"unchecked","unused"})
    public void cloneWbsTemplateMaster(WBSTemplateMasterVO targetMasterVO){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);

        //LOGGER.debug("/****************** Template ���� Activity ��ü Get Start**************************/");
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringBuffer selectPattern    = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, getVo().getObid() );

        List<BusinessObjectRootVO> relatedObjectList = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, 
                                                                         ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, 
                                                                         GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString(), false, true, 0, 10);

        relatedObjectList = ObjectRoot.getRelatedObjectSet(relatedObjectList, ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE,GlobalConstants.FLAG_TYPE_TO, true, true);
        
        List<BusinessObjectRootVO> originalObjectList = new ArrayList<BusinessObjectRootVO>();
        for(BusinessObjectRootVO vo: relatedObjectList){
            BusinessObjectRootVO copiedVo = (BusinessObjectRootVO)DomUtil.copy(vo);
            originalObjectList.add(copiedVo);
        }
        
        if(NullUtil.isNone(relatedObjectList)) throw new FoundationException("No Data to Copy!");
        //LOGGER.debug("/****************** Object ������ Name/Revision/States Setting Start **************************/");
        convertNewWBSItemTemplatesList(relatedObjectList);

        //LOGGER.debug("/****************** WBSItemTemplates/JobActivityTemplate Object Create **************************/");
        Map<String,Object> createObjectsMap = ObjectRoot.createObjectSetBatch(relatedObjectList);
        
        List<BusinessObjectVO>         bizObjList       = (List<BusinessObjectVO>)createObjectsMap.get(GlobalConstants.OBJECT_CREATE_SET_BIZ);
        List<BusinessObjectMasterVO>   bizMasterObjList = (List<BusinessObjectMasterVO>)createObjectsMap.get(GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR);
        List<BusinessRelationObjectVO> relObjList       = (List<BusinessRelationObjectVO>)createObjectsMap.get(GlobalConstants.OBJECT_CREATE_SET_REL);

        Map<String,ObjectRootVO> objectFactory = new HashMap<String,ObjectRootVO>();
        for(BusinessObjectVO vo : bizObjList){objectFactory.put(vo.getObid(), vo);}
        for(BusinessObjectMasterVO vo : bizMasterObjList){objectFactory.put(vo.getObid(), vo);}
        for(BusinessRelationObjectVO vo : relObjList){objectFactory.put(vo.getObid(), vo);}
        
        Map<String,String> obidMap = makeObjectMap(targetMasterVO,bizObjList,bizMasterObjList);
        
        //LOGGER.debug("/****************** HasActivityTemplate/HasJobActivityTemplate Relation Create *******************************************/");
        Map<String,Object> createRelObjectsMap = createHasActivityTemplateRelationSet(relatedObjectList,obidMap,objectFactory,userId);
        //LOGGER.debug("/****************** TemplateDependency Relation Create *******************************************/");
        
        selectPattern.setLength(0); wherePattern.setLength(0); parameterPattern.setLength(0);
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", 
                GlobalConstants.OQL_OPERATOR_EQUAL, this.getObid() );
        
        List<BusinessRelationObjectVO> templateDependencyRelList = ObjectRoot.getRelationshipSet(originalObjectList, ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        makeTemplateDependencyVOList(templateDependencyRelList,obidMap,userId);
        if(!NullUtil.isNone(templateDependencyRelList)){
            Map<String,Object> templateDependencyObjectsMap = ObjectRoot.createObjectSetBatch(templateDependencyRelList);
        }
        //LOGGER.debug("/****************** Role Related Relation(UsedRoleAtWBSTemplate,AllocatedRoleToWBSTemplateItems) Create *******************************************/");
        Map<String,Object> roleRelatedObjectsMap = createRoleRalatedRelation(targetMasterVO,originalObjectList,obidMap);
        
        //LOGGER.debug("/****************** Document Template Create *******************************************/");
        Map<String,Object> copiedDocTemplateObjMap = createRecommendedDocTemplate(targetMasterVO,originalObjectList,obidMap);

        //LOGGER.debug("/****************** ManagedBy Relation(WBSManagedBy) Create *******************************************/");
        Map<String,Object> copiedManagedBYObjMap = createWBSManagedBy(targetMasterVO,obidMap);
        
        //LOGGER.debug("/****************** RelatedTemplateMaster Relation(RelatedTemplateMaster) Create **********************/");
        Map<String,Object> copiedActivityMasterRelMap = createRelatedActivityTemplateMaster(targetMasterVO,originalObjectList,obidMap);
    }
    private Map<String,Object> createRelatedActivityTemplateMaster(WBSTemplateMasterVO targetMasterVO,List<BusinessObjectRootVO> relatedObjectList,Map<String,String> obidMap){
        
        List<RelatedTemplateMasterVO> relatedActivityMasterRelList = ObjectRoot.getRelationshipSet(relatedObjectList, ApplicationSchemaConstants.RELCLASS_RELATEDTEMPLATEMASTER, ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO, "", "", "");
        List<RelatedTemplateMasterVO> copiedActivityMasterRelList = new ArrayList<RelatedTemplateMasterVO>();
        for(RelatedTemplateMasterVO relVO :  relatedActivityMasterRelList){
            RelatedTemplateMasterVO copiedRelVO = (RelatedTemplateMasterVO)DomUtil.copy(relVO);
            copiedRelVO.setFromObid(obidMap.get(relVO.getFromObid()));
            copiedActivityMasterRelList.add(copiedRelVO);
        }
        Map<String,Object> copiedActivityMasterRelMap = ObjectRoot.createObjectSetBatch(copiedActivityMasterRelList);
        return copiedActivityMasterRelMap;
    }
    private Map<String,Object> createRecommendedDocTemplate(WBSTemplateMasterVO targetMasterVO,List<BusinessObjectRootVO> relatedObjectList,Map<String,String> obidMap){
        List<RecommendedDocumentTemplateVO> relatedDocTemplateList = ObjectRoot.getRelationshipSet(relatedObjectList, ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE, ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE, GlobalConstants.FLAG_TYPE_TO);
        List<RecommendedDocumentTemplateVO> copiedDocTemplateList = new ArrayList<RecommendedDocumentTemplateVO>();
        for(RecommendedDocumentTemplateVO relVO :  relatedDocTemplateList){
            RecommendedDocumentTemplateVO copiedRelVO = (RecommendedDocumentTemplateVO)DomUtil.copy(relVO);
            copiedRelVO.setFromObid(obidMap.get(relVO.getFromObid()));
            copiedDocTemplateList.add(copiedRelVO);
        }
        Map<String,Object> copiedDocTemplateObjMap = ObjectRoot.createObjectSetBatch(copiedDocTemplateList);
        return copiedDocTemplateObjMap;
    }
    private Map<String,Object> createWBSManagedBy(WBSTemplateMasterVO targetMasterVO,Map<String,String> obidMap){
        List<WBSManagedByVO> wbsManagedByList = this.getRelationship(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_FROM);
        List<WBSManagedByVO> copiedWBSManagedByList = new ArrayList<WBSManagedByVO>();
        for(WBSManagedByVO relVO :  wbsManagedByList){
            WBSManagedByVO copiedRelVO = (WBSManagedByVO)DomUtil.copy(relVO);
            copiedRelVO.setToObid(targetMasterVO.getObid());
            copiedRelVO.setFromObid(obidMap.get(relVO.getFromObid()));
            copiedWBSManagedByList.add(copiedRelVO);
        }
        Map<String,Object> copiedManagedBYObjMap = ObjectRoot.createObjectSetBatch(copiedWBSManagedByList);
        return copiedManagedBYObjMap;
    }
    private Map<String,Object> createRoleRalatedRelation(WBSTemplateMasterVO targetMasterVO,List<BusinessObjectRootVO> relatedObjectList,Map<String,String> obidMap){
        List<UsedRoleAtWBSTemplateVO> usedRoleRelationListVO = this.getRelationship(ApplicationSchemaConstants.RELCLASS_USEDROLEATWBSTEMPLATE, ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, GlobalConstants.FLAG_TYPE_FROM);
        List<UsedRoleAtWBSTemplateVO> copiedUsedRoleRelationListVO = new ArrayList<UsedRoleAtWBSTemplateVO>();
        for(UsedRoleAtWBSTemplateVO relVO :  usedRoleRelationListVO){
            UsedRoleAtWBSTemplateVO copiedRelVO = (UsedRoleAtWBSTemplateVO)DomUtil.copy(relVO);
            copiedRelVO.setToObid(targetMasterVO.getObid());
            copiedUsedRoleRelationListVO.add(copiedRelVO);
        }
        List<AllocatedRoleToWBSTemplateItemsVO> allocatedRoleListVO = ObjectRoot.getRelationshipSet(relatedObjectList, ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLETOWBSTEMPLATEITEMS, ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, GlobalConstants.FLAG_TYPE_TO, "","","");
        List<AllocatedRoleToWBSTemplateItemsVO> copiedAllocatedRoleListVO = new ArrayList<AllocatedRoleToWBSTemplateItemsVO>();
        for(AllocatedRoleToWBSTemplateItemsVO relVO :  allocatedRoleListVO){
            AllocatedRoleToWBSTemplateItemsVO copiedRelVO = (AllocatedRoleToWBSTemplateItemsVO)DomUtil.copy(relVO);
            copiedRelVO.setFromObid(obidMap.get(relVO.getFromObid()));
            copiedAllocatedRoleListVO.add(copiedRelVO);
        }
        List<BusinessRelationObjectVO> allList = new ArrayList<BusinessRelationObjectVO>();
        allList.addAll(copiedUsedRoleRelationListVO);
        allList.addAll(copiedAllocatedRoleListVO);
        Map<String,Object> roleRelatedObjectsMap = ObjectRoot.createObjectSetBatch(allList);
        return roleRelatedObjectsMap;
    }
    private void convertNewWBSItemTemplatesList(List<BusinessObjectRootVO> relatedObjectList){
        String lifeCycle = "";
        String states = "";
        String revision = "";
        boolean isFirst = true;
        for(BusinessObjectRootVO vo : relatedObjectList){
            if(isFirst) {
                lifeCycle = vo.getLifeCycle(); 
                states = LifeCycleUtil.getLifeCycleInfo(vo.getLifeCycle()).getFirstState().getStateName();
            }else{
                if(!lifeCycle.equals(vo.getLifeCycle())){
                    lifeCycle = vo.getLifeCycle();
                    states = LifeCycleUtil.getLifeCycleInfo(lifeCycle).getFirstState().getStateName();
                    if(vo instanceof WBSItemTemplatesVO){
                        revision = "1";
                    }
                }
            }
            vo.setStates(states);
            if(vo instanceof WBSItemTemplatesVO){
                vo.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WBS_ITEM_TEMPLATES));
                ((WBSItemTemplatesVO)vo).setRevision(revision);
                ((WBSItemTemplatesVO)vo).setPreviousObid("1");
                ((WBSItemTemplatesVO)vo).setNextObid("1");
            }else if(vo instanceof JobActivityTemplateVO){
                vo.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_JOB_ACTIVITY_TEMPLATE));
            }else{
                throw new FoundationException("Not Supported Object.");
            }
            vo.getOutData().put("olObid", vo.getObid());
            lifeCycle = vo.getLifeCycle();
            isFirst = false;
        }
    }
    public void clearActivityInfo(){
        
    }
    private Map<String,String> makeObjectMap(BusinessObjectVO targetVO,List<BusinessObjectVO> bizObjList,List<BusinessObjectMasterVO> bizMasterObjList){
        Map<String,String> obidMap = new HashMap<String,String>();
        obidMap.put(this.getVo().getObid(), targetVO.getObid());
        obidMap.put(targetVO.getObid(), this.getVo().getObid());
        for(BusinessObjectVO vo : bizObjList){
            obidMap.put((String)vo.getOutData().get("olObid"), vo.getObid());
            obidMap.put(vo.getObid(),(String)vo.getOutData().get("olObid"));
        }
        for(BusinessObjectMasterVO vo : bizMasterObjList){
            obidMap.put((String)vo.getOutData().get("olObid"), vo.getObid());
            obidMap.put(vo.getObid(),(String)vo.getOutData().get("olObid"));
        }
        return obidMap;
    }
    /**
     * 
     *
     * @param templateDependencyRelList
     * @param obidMap
     */
    
    private void makeTemplateDependencyVOList(List<BusinessRelationObjectVO> templateDependencyRelList,Map<String,String> obidMap,String userId){
        
        Date date = null;
        for(BusinessRelationObjectVO relVO : templateDependencyRelList){
            relVO.setFromObid(obidMap.get(relVO.getFromObid()));
            relVO.setToObid(obidMap.get(relVO.getToObid()));
            relVO.setLocker("1");
            relVO.setCheckouter("1");
            relVO.setOwner("1");
            relVO.setCreator(userId);
            relVO.setModifier(userId);
            relVO.setCheckouted(date);
        }
    }
    /**
     * 
     *
     * @param relatedObjectList
     * @param obidMap
     */
    private Map<String,Object> createHasActivityTemplateRelationSet(List<BusinessObjectRootVO> relatedObjectList, Map<String,String> obidMap,Map<String,ObjectRootVO> objectFactory, String userId){
        Map<String,Object> createRelObjectsMap = new HashMap<String,Object>();
        List<BusinessRelationObjectVO> willBeCreatedRelList = new ArrayList<BusinessRelationObjectVO>();
        for(BusinessObjectRootVO vo : relatedObjectList){
            //HasActivityTemplate
            HashMap<String, Object> outData = vo.getOutData();
            String className  = (String)outData.get("rel_className");
            String fromClass  = (String)outData.get("rel_fromClass");
            String fromObid   = (String)outData.get("rel_fromObid");
            String toClass    = (String)outData.get("rel_toClass");
            String toObid     = (String)outData.get("rel_toObid");
            Integer sequences = (Integer)outData.get("rel_sequences");
            if(className.equals(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE)){
                HasActivityTemplateVO hasActivityTemplateVO = makeHasActivityTemplateVO(obidMap,fromClass,fromObid,toClass,toObid,sequences,userId);
                willBeCreatedRelList.add(hasActivityTemplateVO);
            }else if(className.equals(ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE)){
                HasJobActivityTemplateVO hasJobActivityTemplateVO = makeHasJobActivityTemplateVO(obidMap,fromClass,fromObid,toClass,toObid,sequences,userId);
                willBeCreatedRelList.add(hasJobActivityTemplateVO);
            }
        }
        if(!NullUtil.isNone(willBeCreatedRelList)){
            createRelObjectsMap = ObjectRoot.createObjectSetBatch(willBeCreatedRelList);
        }
        return createRelObjectsMap;
    }
    /** 
     * 
     *
     * @param obidMap   : Old/New obid Mapping Map
     * @param fromClass : From Class Name
     * @param fromObid  : From Obid
     * @param toClass   : To Class Name
     * @param toObid    : To Obid
     * @param sequences : Sequences(Sort ����)
     * @return
     */
    private HasActivityTemplateVO makeHasActivityTemplateVO(Map<String,String> obidMap, String fromClass, String fromObid, String toClass, String toObid, Integer sequences,String userId){
        HasActivityTemplateVO hasActivityTemplateVO = new HasActivityTemplateVO();
        hasActivityTemplateVO.setClassName(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE);
        
        hasActivityTemplateVO.setFromClass(fromClass);
        hasActivityTemplateVO.setFromObid(obidMap.get(fromObid));
        
        hasActivityTemplateVO.setToClass(toClass);
        hasActivityTemplateVO.setToObid(obidMap.get(toObid));
        
        hasActivityTemplateVO.setSequences(sequences);
        return hasActivityTemplateVO;
    }
    private HasJobActivityTemplateVO makeHasJobActivityTemplateVO(Map<String,String> obidMap, String fromClass, String fromObid, String toClass, String toObid, Integer sequences,String userId){
        HasJobActivityTemplateVO hasActivityTemplateVO = new HasJobActivityTemplateVO();
        hasActivityTemplateVO.setClassName(ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE);
        
        hasActivityTemplateVO.setFromClass(fromClass);
        hasActivityTemplateVO.setFromObid(obidMap.get(fromObid));
        
        hasActivityTemplateVO.setToClass(toClass);
        hasActivityTemplateVO.setToObid(obidMap.get(toObid));
        return hasActivityTemplateVO;
    }
    
    public void setDefault(boolean generateName){
        if(generateName){
            getVo().setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WBS_TEMPLATE_MASTER_NAME));
        }
        getVo().setClassName(ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER);
        getVo().setTitles(".");
        getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WBS_TEMPLATE_MASTER);
        getVo().setStates(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_WORKING);
        getVo().setRevision("1.0");
    }
    
    public static List<WBSTemplateMasterVO> getLatestReleasedWBSTemplateMasterList(String divisionUnit){
        String[] states = {ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE};
        return retrieveWBSTemplateMasterList(divisionUnit, null, null, true, states, false);
    }

    public static List<WBSTemplateMasterVO> getLatestReleasedWBSTemplateMasterList(String divisionUnit, String developmentType, String projectLifeCycle){
        String[] states = {ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE};
        return retrieveWBSTemplateMasterList(divisionUnit, developmentType, projectLifeCycle, true, states, false);
    }
    
    public static List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String divisionUnit){
        return retrieveWBSTemplateMasterList(divisionUnit, null, null, true, null, false);
    }

    public static List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String divisionUnit,boolean includeInactive){
        return retrieveWBSTemplateMasterList(divisionUnit, null, null,  includeInactive, null, false);
    }
    
    public static List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String divisionUnit, boolean includeInactive, boolean latestOnly){
        return retrieveWBSTemplateMasterList(divisionUnit, null, null, includeInactive, null, latestOnly);
    }
    
    public static List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String divisionUnit, String developmentType, String projectLifeCycle){
        return retrieveWBSTemplateMasterList(divisionUnit, developmentType, projectLifeCycle, false, null, false);
    }
    public static List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String divisionUnit, String developmentType, boolean includeInactive){
        return retrieveWBSTemplateMasterList(divisionUnit, developmentType, "", includeInactive, null, false);
    }
    public static List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String[] states){
        return retrieveWBSTemplateMasterList("", "", "", false, states, true);
    }
    private static List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String divisionUnit, String developmentType, String projectLifeCycle, boolean includeInactive, String[] states, boolean latestOnly){
        
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        if(!NullUtil.isNone(divisionUnit)) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[divisionUnit]", GlobalConstants.OQL_OPERATOR_EQUAL, divisionUnit );
        }else{
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[divisionUnit]", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, ProjectConstants.PROJECT_BASIS_COMMON_DIVISION );
        }
        if(!NullUtil.isNone(developmentType)) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[developmentType]", GlobalConstants.OQL_OPERATOR_EQUAL, developmentType );
        if(!NullUtil.isNone(projectLifeCycle)) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[projectLifeCycle]", GlobalConstants.OQL_OPERATOR_EQUAL, projectLifeCycle );
        if(states != null && states.length > 0 ) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertArrayToString(states));
        if(!includeInactive) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE +","+ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_WORKING );
        if(latestOnly) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[previousObid]", GlobalConstants.OQL_OPERATOR_EQUAL, "1" );
        
        StringUtil.addSortByPattern(selectPatternBuf, "@this.[modified] desc");
        return findObjects(ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                selectPatternBuf.toString(),  wherePatternBuf.toString(), paramPatternBuf.toString(), false, 0);
    }
    private void cleansingGarbageRelationForComplete(List<WBSItemTemplatesVO> workingWBSItemTemplatesList){
        String relationPattern = ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE + "," + (ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY);
        for(WBSItemTemplatesVO templateItemVO : workingWBSItemTemplatesList){
            WBSItemTemplates templateItemDom = new  WBSItemTemplates(templateItemVO);
            List<WBSItemTemplatesVO> relWBSItemTemplatesList = templateItemDom.getRelatedObjects(relationPattern, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_ALL);
            for(WBSItemTemplatesVO relWBSItemTemplatesLVO : relWBSItemTemplatesList){
                if(!relWBSItemTemplatesLVO.getPreviousObid().equals("1")){
                    BusinessRelationObject relDom = new BusinessRelationObject((String)relWBSItemTemplatesLVO.getOutDataAttributeValue("rel_obid"));
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, this);
                    relDom.deleteObject(map);
                }
            }
        }
    }
    public List<WBSPhaseTemplateVO> getWBSPhaseTemplateList(){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.addSortByPattern(selectPattern, "@REL.[sequences]");
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", 
                GlobalConstants.OQL_OPERATOR_EQUAL, this.getObid() );
        
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, 
                                 ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE, 
                                 GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), 
                                 wherePattern.toString(), parameterPattern.toString(), 
                                 false, true, 0, 1);
    }

    public WBSPhaseTemplateVO getWBSPhaseTemplate(String phaseName){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[projectPhaseName]", GlobalConstants.OQL_OPERATOR_EQUAL, phaseName );
        List<WBSPhaseTemplateVO> relatedObjects = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, 
                                                                    ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE, 
                                                                    GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(), 
                                                                    wherePattern.toString(), parameterPattern.toString(), 
                                                                    false, true, 0, 1);
        if(NullUtil.isNone(relatedObjects)){return null;}
        if(relatedObjects.size() > 1){throw new FoundationException("Phase(" + phaseName + ") duplicated.");}
        return relatedObjects.get(0);
    }
    
    public void validateMasterGradeUniqueAsDevelopmentType(){
        List<WBSTemplateMasterVO> wbsTemplateMasterList = getLatestReleasedWBSTemplateMasterList(this.getVo().getDivisionUnit(), this.getVo().getDevelopmentType(), this.getVo().getProjectLifeCycle());
        String newAppliedList = this.getVo().getAppliedGradeList();
        Set<String> newGradeSet = StrUtil.convertArrayToSet(newAppliedList.split(","));
        Set<String> errorSet = new HashSet<String>();
        for(WBSTemplateMasterVO vo : wbsTemplateMasterList){
            if(vo.getNames().equals(this.getVo().getNames())){continue;}
            String oldAppliedList = vo.getAppliedGradeList();
            if(!NullUtil.isNone(oldAppliedList)){
                
                Set<String> oldGradeSet = StrUtil.convertArrayToSet(oldAppliedList.split(","));
                for(String grade : newGradeSet){
                    if(oldGradeSet.contains(grade)){
                        errorSet.add(grade);
                    }
                }
            }
        }
        if(errorSet.size() > 0){
            throw new FoundationException("Grade(" + StrUtil.convertSet2Str(errorSet) + ") is not unique for Development Type ("+this.getVo().getDevelopmentType()+").");
        }
    }
    
    public void validateWBSTemplateMaster(){
        ProjectLifeCycleVO       projectLifeCycleVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, this.getVo().getProjectLifeCycle());
        ProjectDevelopmentTypeVO developmentTypeVO  = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTDEVELOPMENTTYPE, this.getVo().getDevelopmentType());
        DivisionUnitVO divisionUnitVO  = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, this.getVo().getDivisionUnit());
        if(NullUtil.isNull(projectLifeCycleVO)) throw new FoundationException("Process Type(" + this.getVo().getProjectLifeCycle() + ") is not found.");
        if(NullUtil.isNull(developmentTypeVO)) throw new FoundationException("Development Type(" + this.getVo().getDevelopmentType() + ") is not found.");
        if(NullUtil.isNull(divisionUnitVO)) throw new FoundationException("Division(" + this.getVo().getDivisionUnit() + ") is not found.");
        
        ProjectLifeCycle       projectLifeCycleDom = new ProjectLifeCycle(projectLifeCycleVO);
        ProjectDevelopmentType developmentTypeDom = new ProjectDevelopmentType(developmentTypeVO);
        DivisionUnit divisionUnitDom = new DivisionUnit(divisionUnitVO);
        validateMasterGradeUniqueAsDevelopmentType();
        List<WBSItemTemplatesVO> structuredList = this.getWBSItemTemplatesStructure();
        SortUtil.sort(structuredList, "uniqueString", false);
        validateForCompleteWBSTemplateMaster(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
    }
    
    /**
     * WBS Template�� ���� Ȯ���Ѵ�.
     */
    public void completeWBSTemplateMaster(){
        if(!this.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_WORKING)) throw new FoundationException("Only " +  ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_WORKING + " Sate can be Completed. Current State is " + this.getStates());
        ProjectLifeCycleVO       projectLifeCycleVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, this.getVo().getProjectLifeCycle());
        ProjectDevelopmentTypeVO developmentTypeVO  = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTDEVELOPMENTTYPE, this.getVo().getDevelopmentType());
        DivisionUnitVO divisionUnitVO  = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, this.getVo().getDivisionUnit());
        if(NullUtil.isNull(projectLifeCycleVO)) throw new FoundationException("Process Type(" + this.getVo().getProjectLifeCycle() + ") is not found.");
        if(NullUtil.isNull(developmentTypeVO)) throw new FoundationException("Development Type(" + this.getVo().getDevelopmentType() + ") is not found.");
        if(NullUtil.isNull(divisionUnitVO)) throw new FoundationException("Division(" + this.getVo().getDivisionUnit() + ") is not found.");

        ProjectLifeCycle       projectLifeCycleDom = new ProjectLifeCycle(projectLifeCycleVO);
        ProjectDevelopmentType developmentTypeDom = new ProjectDevelopmentType(developmentTypeVO);
        DivisionUnit divisionUnitDom = new DivisionUnit(divisionUnitVO);
        validateMasterGradeUniqueAsDevelopmentType();
        List<WBSItemTemplatesVO> structuredList = this.getWBSItemTemplatesStructure();
        SortUtil.sort(structuredList, "uniqueString", false);
        validateForCompleteWBSTemplateMaster(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
        preProcessForCompleteWBSTemplateMaster(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
        completeWBSTemplateMasterCore(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
        postProcessForCompleteWBSTemplateMaster(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
    }
    /**
     * 
     */
    public void publishWBSTemplateMaster(){
        if(!this.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE)) throw new FoundationException("Only " +  ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE + " Sate can be Completed. Current State is " + this.getStates());
        validateWBSTemplateMaster();
        if(!this.isFirst()){
            WBSTemplateMasterVO previousTemplateMasterVO= this.getPreviousRevision();
            WBSTemplateMaster previousTemplateMasterDom = new WBSTemplateMaster(previousTemplateMasterVO);
            disablePreviousTemplateMaster(previousTemplateMasterDom);
        }
        this.getVo().setIsPublished("Y");
        this.modifyObject();
    }
    private void disablePreviousTemplateMaster(WBSTemplateMaster templateMasterDom){
        templateMasterDom.changePolicyAndState(templateMasterDom.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_INACTIVE);
        templateMasterDom.getVo().setIsPublished("N");
        templateMasterDom.modifyObject();
        if(!templateMasterDom.isFirst()){
            WBSTemplateMasterVO previousTemplateMasterVO= templateMasterDom.getPreviousRevision();
            WBSTemplateMaster previousTemplateMasterDom = new WBSTemplateMaster(previousTemplateMasterVO);
            disablePreviousTemplateMaster(previousTemplateMasterDom);
        }
    }
    private void validateForCompleteWBSTemplateMaster(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom, List<WBSItemTemplatesVO> structuredList){
        List<ActivityTemplateMasterVO> activityTemplateMasterVOList = ObjectRoot.getRelatedObjectSet(structuredList, ApplicationSchemaConstants.RELCLASS_RELATEDTEMPLATEMASTER, 
                                                                                                     ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO);
        Map<String, ObjectRootVO> activityMasterMap = ObjectRoot.makeVoDB(activityTemplateMasterVOList, "names");
        validateTemplateAttribueCheck(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
        validateAppliedLifeCycleCheck(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
        validateAppliedPhaseCheck(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
        validateNoDependencyCheck(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
        validateInfiniteLoopCheck(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
        validateDuplicateActivity(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList,activityMasterMap);
        validatePhaseMethod(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList);
        validateActivityMethod(divisionUnitDom,projectLifeCycleDom,developmentTypeDom,structuredList,activityMasterMap);
    }
    private void preProcessForCompleteWBSTemplateMaster(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList){
        
    }
    private void completeWBSTemplateMasterCore(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList){
        List<WBSItemTemplatesVO> wokringList = this.getWorkingWBSItemTemplateList();
        cleansingGarbageRelationForComplete(wokringList);
        for(WBSItemTemplatesVO vo : wokringList){
            WBSItemTemplates WBSItemTemplatesDom = new WBSItemTemplates(vo);
            WBSItemTemplatesDom.promote();
        }
        this.promote();
    }
    private void postProcessForCompleteWBSTemplateMaster(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList){
        
    }
    private void validateTemplateAttribueCheck(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList){
        StringBuffer nameEngBuf = new StringBuffer();
        StringBuffer nameChiBuf = new StringBuffer();
        StringBuffer nameKorBuf = new StringBuffer();
        StringBuffer allBuf = new StringBuffer();
        for(WBSItemTemplatesVO vo : structuredList){
            if(StrUtil.isEmpty(vo.getActivityNameEng())) nameEngBuf.append(",").append(vo.getNames());
            if(StrUtil.isEmpty(vo.getActivityNameKor())) nameKorBuf.append(",").append(vo.getNames());
            if(StrUtil.isEmpty(vo.getActivityNameChi())) nameChiBuf.append(",").append(vo.getNames());
        }
        if(nameEngBuf.length() > 1) allBuf.append("English Name Empty : ").append(nameEngBuf.substring(1));
        if(nameKorBuf.length() > 1) allBuf.append("Korean Name Empty : ").append(nameKorBuf.substring(1));
        if(nameChiBuf.length() > 1) allBuf.append("Chinese Name Empty : ").append(nameChiBuf.substring(1));
        
        if(allBuf.length() > 1) throw new FoundationException(allBuf.toString());
    }
    private void validateAppliedLifeCycleCheck(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList){
        if(!projectLifeCycleDom.isApplied(divisionUnitDom.getVo())) throw new FoundationException("Process(" + projectLifeCycleDom.getTitles() + ") can not be applied to " + divisionUnitDom.getNames() + ".");
    }
    /**
     * ����Ǿ�
     *
     * @param divisionUnitDom
     * @param projectLifeCycleDom
     * @param developmentTypeDom
     * @param structuredList
     */
    private void validateAppliedPhaseCheck(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList){
        Set<String> projectPhaseCodeSet = new HashSet<String>();
        List<WBSPhaseTemplateVO> phaseList = new ArrayList<WBSPhaseTemplateVO>();
        for(WBSItemTemplatesVO vo : structuredList ){
            if(vo instanceof WBSPhaseTemplateVO){
                if(StrUtil.isEmpty(((WBSPhaseTemplateVO)vo).getProjectPhaseName())) throw new FoundationException(vo.getActivityNameEng() + "'s Event is empty.");
                phaseList.add((WBSPhaseTemplateVO)vo);
                projectPhaseCodeSet.add(((WBSPhaseTemplateVO)vo).getProjectPhaseName());
            }
        }
        boolean isError = false;
        StringBuffer strBuf = new StringBuffer();
        List<ConvertedPhaseVO> validList = projectLifeCycleDom.getConvertedPhaseList(divisionUnitDom.getVo(), projectPhaseCodeSet, true);
        for(WBSPhaseTemplateVO vo :  phaseList){
            boolean isExists = false;
            for(ConvertedPhaseVO subVo : validList){
                if(vo.getProjectPhaseName().equals(subVo.getNames())){
                    isExists = true;break;
                }
            }
            if(!isExists){ 
                isError = true;
                strBuf.append(",").append("[").append(vo.getProjectPhaseName()).append("]").append(vo.getActivityNameEng());
            }
        }
        if(isError) throw new FoundationException(strBuf.substring(1) + ": Not " + divisionUnitDom.getNames() + "'s Event.");
    }
    private void validateNoDependencyCheck(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList){
        List<TemplateDependencyVO> templateDependencyAll = getTemplateDependencyAll(this.getObid(), structuredList);
        List<WBSActivityTemplateVO> independentActivityList = new ArrayList<WBSActivityTemplateVO>();
        List<WBSActivityTemplateVO> errorGroupActivityList = new ArrayList<WBSActivityTemplateVO>();
        List<WBSActivityTemplateVO> errorActivityList = new ArrayList<WBSActivityTemplateVO>();
        SortUtil.sort(structuredList, "uniqueString", false);
        for(WBSItemTemplatesVO vo :  structuredList){
            if(vo instanceof WBSActivityTemplateVO){
                boolean isIndependentActivity = true;
                if(!StrUtil.isEmpty((String)vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))){
                    isIndependentActivity = false;
                }
                for(TemplateDependencyVO dependencyVO : templateDependencyAll){
                    if(dependencyVO.getFromObid().equals(vo.getObid())){
                        isIndependentActivity = false;
                        if(StrUtil.isEmpty((String)vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))){
                            if(!StrUtil.isEmpty((String)dependencyVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))){
                                errorActivityList.add((WBSActivityTemplateVO)vo);
                            }
                        }else{
                            errorGroupActivityList.add((WBSActivityTemplateVO)vo); break;
                        }
                    }
                }
                if(isIndependentActivity){independentActivityList.add((WBSActivityTemplateVO)vo);}
            }
        }
        if(independentActivityList.size() == 0) throw new FoundationException("[Error]Cannot find First Activity.");
        StringBuffer errrActivityBuf = new StringBuffer();
        WBSItemTemplatesVO firstActivityVO = independentActivityList.get(0);
        for(WBSActivityTemplateVO vo : independentActivityList){
            if(!firstActivityVO.getObid().equals(vo.getObid())) errrActivityBuf.append(",").append("<br>").append(getDisplayNameAsLoc(vo));
        }
        if(errrActivityBuf.length() > 1) throw new FoundationException("[Error]Activity list without Previous Activity : " + errrActivityBuf.substring(1));
        errrActivityBuf.setLength(0);
        for(WBSActivityTemplateVO vo : errorGroupActivityList){
            errrActivityBuf.append(",").append("<br>").append(getDisplayNameAsLoc(vo));
        }
        if(errrActivityBuf.length() > 1) throw new FoundationException("[Error]Group Activity list has dependency : " + errrActivityBuf.substring(1));
        errrActivityBuf.setLength(0);
        for(WBSActivityTemplateVO vo : errorActivityList){
            errrActivityBuf.append(",").append("<br>").append(getDisplayNameAsLoc(vo));
        }
        if(errrActivityBuf.length() > 1) throw new FoundationException("[Error]Activity list has dependency with group activity: " + errrActivityBuf.substring(1));
    }
    
    private static String getDisplayNameAsLoc(WBSActivityTemplateVO wbsActivityTemplateVO){
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
        
        if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
            return wbsActivityTemplateVO.getActivityNameKor();
        }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
            return wbsActivityTemplateVO.getActivityNameChi();
        }else{
            return wbsActivityTemplateVO.getActivityNameEng();
        }
    }
    private void validateDuplicateActivity(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList, Map<String, ObjectRootVO> activityMasterMap){
        List<WBSItemTemplatesVO> workStructuredList = new ArrayList<WBSItemTemplatesVO>();
        ActivityTemplateMasterVO masterVO = null;
        for(WBSItemTemplatesVO vo : structuredList){
            if(vo instanceof WBSActivityTemplateVO){
                masterVO = ((ActivityTemplateMasterVO)activityMasterMap.get(vo.getActivityMasterName()));
                if(masterVO != null && "Y".equals(((ActivityTemplateMasterVO)activityMasterMap.get(vo.getActivityMasterName())).getIsOnlyOnePerTemplate())){
                    workStructuredList.add(vo);
                }
            } 
        }
        StringBuffer strBuf = new StringBuffer();
        boolean isError = false;
        if(!NullUtil.isNone(workStructuredList)){
            workStructuredList = SortUtil.groupBy(workStructuredList, "activityMasterName", "", "", "");
            for(WBSItemTemplatesVO vo : workStructuredList){
                Integer cnt = (Integer)vo.getOutDataAttributeValue(GlobalConstants.OMC_GROUP_BY_COUNT);
                if(cnt > 1){
                    isError = true;
                    WBSItemTemplates dom = new WBSItemTemplates(vo);
                    strBuf.append(",<BR>").append(dom.getCommonTitlesForDisplay()).append("(").append(cnt).append(")");
                }
            }
        }
        if(isError) throw new FoundationException("Dulpicate Activity List: " + strBuf.substring(1));
    }
    private void validatePhaseMethod(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList){
        StringBuffer strBuf = new StringBuffer();
        if("TechProject".equals(developmentTypeDom.getVo().getAppliedClassList())) return;
        for(WBSItemTemplatesVO vo : structuredList){
            if( vo instanceof WBSPhaseTemplateVO
                    && ( ProjectConstants.NONE.equals(vo.getStartValidationMethod()) 
                         || ProjectConstants.NONE.equals(vo.getStartExecutionMethod())
                         || ProjectConstants.NONE.equals(vo.getCompleteValidationMethod())
                         || ProjectConstants.NONE.equals(vo.getPostExecutionMethod()) )){
                WBSItemTemplates dom = new WBSItemTemplates(vo);
                strBuf.append(",").append(dom.getCommonTitlesForDisplay());
            }
        }
        if(strBuf.length() > 0) throw new FoundationException("Event needs check system functions. please contact system administrator. <br> event : " + strBuf.substring(1));
    }
    private void validateActivityMethod(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList, Map<String, ObjectRootVO> activityMasterMap){
        StringBuffer strBuf = new StringBuffer();
        ActivityTemplateMasterVO masterVO = null;
        for(WBSItemTemplatesVO vo : structuredList){
            if(vo instanceof WBSActivityTemplateVO){
                masterVO = ((ActivityTemplateMasterVO)activityMasterMap.get(vo.getActivityMasterName()));
                if(masterVO != null){
                    if(!vo.getStartValidationMethod().equals(masterVO.getStartValidationMethod())
                            || !vo.getStartExecutionMethod().equals(masterVO.getStartExecutionMethod())
                            || !vo.getCompleteValidationMethod().equals(masterVO.getCompleteValidationMethod())
                            || !vo.getPostExecutionMethod().equals(masterVO.getPostExecutionMethod())
                            ){
                        WBSItemTemplates dom = new WBSItemTemplates(vo);
                        strBuf.append(",").append(dom.getCommonTitlesForDisplay());
                    }
                }
            }
        }
        if(strBuf.length() > 0) throw new FoundationException("Activity needs check system functions. please contact system administrator. \n" + strBuf.substring(1));
    }
    private void validateInfiniteLoopCheck(DivisionUnit divisionUnitDom,ProjectLifeCycle projectLifeCycleDom,ProjectDevelopmentType developmentTypeDom,List<WBSItemTemplatesVO> structuredList){
        ;
    }
    public boolean isAllocatedWBSItemTemplate(String projectRoleName){
        return getWBSItemTemplatesAsRole(projectRoleName).isEmpty() ? false : true;
    }
    public <T> List<T> getWBSItemTemplates(){
        return getWBSItemTemplatesSub(false,true,false,null,false);
    }
    public <T> List<T> getWBSItemTemplates(boolean withJobActivity){
        return getWBSItemTemplatesSub(false,withJobActivity,false,null,false);
    }
    public List<WBSItemTemplatesVO> getWorkingWBSItemTemplateList(){
        return getWBSItemTemplatesSub(false,false,false,null,true);
    }
    public List<WBSItemTemplatesVO> getWBSItemTemplatesActivityOnly(){
        return getWBSItemTemplatesSub(false,false,true,null,false);
    }
    public List<WBSItemTemplatesVO> getWBSItemTemplatesAsRole(String projectRoleName){
        return getWBSItemTemplatesSub(false,false,false,projectRoleName,false);
    }
    @SuppressWarnings("unchecked")
    private <T> List<T> getWBSItemTemplatesSub(boolean onlyMilestone, boolean withJobActivity, boolean isActivityOnly, String projectRoleName, boolean isWorkingOnly){
        
        StringBuffer classPattern = new StringBuffer();
        classPattern.append(ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES);
        
        StringBuffer selectPatternBuf = new StringBuffer();
        StringUtil.constructSelectPattern(selectPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE+"].obid " + ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR);
        
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        if(isWorkingOnly){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]", 
                    GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_WORKING);
        }
        if(onlyMilestone){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[isMilestone]", 
                    GlobalConstants.OQL_OPERATOR_EQUAL, "Y" );
        }
        if(isActivityOnly){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!From["+ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE+"].obid", 
                    GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        }
        if(StrUtil.isNotEmpty(projectRoleName)){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLETOWBSTEMPLATEITEMS+"].To.names", 
                    GlobalConstants.OQL_OPERATOR_EQUAL, projectRoleName );
        }
        
        List<T> resultList = new ArrayList<T>();
        resultList = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY,
                                            classPattern.toString(), 
                                            GlobalConstants.FLAG_TYPE_FROM, "",
                                            wherePatternBuf.toString(), paramPatternBuf.toString(), 
                                            false, true, 0, 1);
        
        
        if(withJobActivity){
            return ObjectRoot.getRelatedObjectSet((List<? extends ObjectRootVO>)resultList, 
                    ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, 
                    ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE, GlobalConstants.FLAG_TYPE_TO,true,true);
        }
        
        return resultList;
    }
    
    public List<WBSItemTemplatesVO> getWBSItemTemplatesStructure(){
        return getWBSItemTemplatesStructure(ProjectConstants.WBS_TEMPLATE_MAX_LEVEL);
    }
    /**
     * Activity �� ���� ���� ��� �����(Job Activity�� �������� ����)
     *
     * @param findDepth
     * @return
     */
    public List<WBSItemTemplatesVO> getWBSItemTemplatesStructure(int findDepth){
        return getWBSItemTemplatesStructure(false, findDepth);
    }
    /**
     * Activity �� ���� ���� ��� �����(Job Activity�� �������� ����)
     *
     * @param onlyMilestone
     * @param findDepth
     * @return
     */
    public List<WBSItemTemplatesVO> getWBSItemTemplatesStructure(boolean onlyMilestone, int findDepth){
        return getWBSItemTemplatesStructureSub(onlyMilestone,false,false,findDepth);
    }
    /**
     * Activity �� Job Activity�� ���� ���� ��� �����
     *
     * @return
     */
    public List<BusinessObjectRootVO> getWBSItemTemplatesStructureWithJobActivity(){
        List<BusinessObjectRootVO> list  = getWBSItemTemplatesStructureSub(false, true, false,ProjectConstants.WBS_TEMPLATE_MAX_LEVEL);
        return list;
    }
    private <T> List<T> getWBSItemTemplatesStructureSub(boolean onlyMilestone, boolean withJobActivityTemplate, boolean isActivityTemplateOnly, int findDepth){
        
        StringBuffer selectPatternBuf = new StringBuffer();
        StringUtil.constructSelectPattern(selectPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE+"].obid " + ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR);
        StringUtil.constructSelectPattern(selectPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE+"].obid hasChildDoc");
        StringUtil.constructSelectPattern(selectPatternBuf, "'" + this.getObid() + "' wbsTemplateMasterObid");
        
        StringUtil.constructSelectPattern(selectPatternBuf, "@REL.[sequences] rel_sequence");
        StringUtil.addSortByPattern(selectPatternBuf, "@REL.[sequences]");

        StringBuffer classPattern = new StringBuffer();
        StringBuffer relationPattern = new StringBuffer();
        classPattern.append(ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES);
        relationPattern.append(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE);
        
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();

        if(onlyMilestone){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[isMilestone]", 
                    GlobalConstants.OQL_OPERATOR_EQUAL, "Y" );
        }
        if(isActivityTemplateOnly){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!From["+ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE+"].obid", 
                    GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        }

        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", 
                GlobalConstants.OQL_OPERATOR_EQUAL, getVo().getObid() );


        if(withJobActivityTemplate){
            List<WBSItemTemplatesVO> activityList = getRelatedObjects(relationPattern.toString(), 
                    classPattern.toString(), 
                    GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(),
                    wherePatternBuf.toString(), paramPatternBuf.toString(), 
                    false, false, 0, findDepth);
            if(NullUtil.isNone(activityList)) return new ArrayList<T>();
            StringBuffer jobSelectPatternBuf = new StringBuffer();
            String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
            if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameKor]");
            }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameChi]");
            }else{
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameEng]");
            }
            return ObjectRoot.getRelatedObjectSet(activityList, 
                    ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, 
                    ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE, GlobalConstants.FLAG_TYPE_TO,jobSelectPatternBuf.toString(),"","",true,true);
        }else{
            return getRelatedObjects(relationPattern.toString(), 
                    classPattern.toString(), 
                    GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(),
                    wherePatternBuf.toString(), paramPatternBuf.toString(), 
                    false, false, 0, findDepth);
        }
    }   
    public List<BusinessObjectVO> getWBSItemTemplatesStructureForExportExcel(boolean includeDoc){

        StringBuffer selectPatternBuf = new StringBuffer();
        StringUtil.constructSelectPattern(selectPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE+"].obid " + ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR);
        if(includeDoc){
            StringUtil.constructSelectPattern(selectPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE+"].obid hasChildDoc");
        }
        StringUtil.addSortByPattern(selectPatternBuf, "@REL.[sequences]");
        
        StringBuffer classPattern = new StringBuffer();
        StringBuffer relationPattern = new StringBuffer();
        classPattern.append(ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES);
        relationPattern.append(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE);
        
        classPattern.append(",").append(ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE);
        relationPattern.append(",").append(ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE);
        
        if(includeDoc){
            classPattern.append(",").append(ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE);
            relationPattern.append(",").append(ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE);
        }
        
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();

        return getRelatedObjects(relationPattern.toString(), 
                                 classPattern.toString(), 
                                 GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(),
                                 wherePatternBuf.toString(), paramPatternBuf.toString(), 
                                 false, true, 0, 10);

    }
    
    public void updatePhasesTemplateList(List<WBSPhaseTemplateVO> newPhaseList){
        List<WBSPhaseTemplateVO> wbsPhaseTemplateList = getWBSPhaseTemplateList();
        List<WBSPhaseTemplateVO> createList =  new ArrayList<WBSPhaseTemplateVO>();
        boolean isExists;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, this);
        for(WBSPhaseTemplateVO newPhaseTemplateVO : newPhaseList){
            isExists = false;
            for(WBSPhaseTemplateVO oldPhaseTemplateVO : wbsPhaseTemplateList){
                if(oldPhaseTemplateVO.getTitles().equals(newPhaseTemplateVO.getTitles())){
                    isExists = true;
                    wbsPhaseTemplateList.remove(oldPhaseTemplateVO);
                    break;
                }
            }
            if(!isExists){
                createList.add(newPhaseTemplateVO);
            }
        }
        
        for(WBSPhaseTemplateVO WBSPhaseTemplateVO : wbsPhaseTemplateList){
            new WBSPhaseTemplate(WBSPhaseTemplateVO).deleteObject(map);
        }
    }
    
    public List<WBSPhaseTemplate> createPhasesTemplateList(List<WBSPhaseTemplateVO> createList){
        List<WBSPhaseTemplate> createdWBSPhaseTemplateDomList = new ArrayList<WBSPhaseTemplate>();
        for(WBSPhaseTemplateVO newPhaseTemplateVO : createList){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, this);
            WBSPhaseTemplate wbsPhaseTemplate = new WBSPhaseTemplate(newPhaseTemplateVO);
            wbsPhaseTemplate.setDefault(true);
            map.put("targetSequences", newPhaseTemplateVO.getOutData().get("targetSequences"));
            map.put("wbsTemplateMasterObid", getVo().getObid());
            map.put("parentObid", getVo().getObid());
            wbsPhaseTemplate.createObject(map);
            createdWBSPhaseTemplateDomList.add(wbsPhaseTemplate);
        }
        return createdWBSPhaseTemplateDomList;
    }
    public DivisionUnitVO getDivisionUnitVO(){
        return DivisionUnit.getVOByName(getVo().getDivisionUnit());
    }
    
    public List<ProjectGradeVO> retrieveUsingProjectGradeByDevelopType(boolean activeOnly){
        return ProjectGrade.getUsingProjectGradeByDevelopType(getVo().getDivisionUnit(), getVo().getDevelopmentType(),activeOnly);
    }
    
    public List<ProjectGradeVO> getValidGradeListForTemplateMaster(boolean activeOnly){
        List<ProjectGradeVO> returnList = new ArrayList<ProjectGradeVO>();
        List<ProjectGradeVO> list = ProjectGrade.getUsingProjectGradeByDevelopType(getVo().getDivisionUnit(), getVo().getDevelopmentType(),activeOnly);
        if(NullUtil.isNone(list)) return returnList;
        
        String appliedGradeList = this.getVo().getAppliedGradeList();
        if(StrUtil.isEmpty(appliedGradeList)) return returnList;
        
        String[] appliedGradeArray = appliedGradeList.split(",");
        Set<String> strSet = StrUtil.convertArrayToSet(appliedGradeArray);
        if(strSet == null || strSet.size() == 0) return returnList;
        for(ProjectGradeVO vo : list){
            if(strSet.contains(vo.getNames())){
                returnList.add(vo);
            }
        }
        return returnList;
    }
    
    public static List<TemplateDependencyVO> getWBSTemplatePreviousDependency(List<WBSItemTemplatesVO> list, String templateMasterObid){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", 
                GlobalConstants.OQL_OPERATOR_EQUAL,  templateMasterObid);
        List<TemplateDependencyVO> dependencyList = ObjectRoot.getRelationshipSet(list, ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        return dependencyList;
    }
    public static List<TemplateDependencyVO> getWBSTemplateNextDependency(List<WBSItemTemplatesVO> list,String templateMasterObid){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", 
                GlobalConstants.OQL_OPERATOR_EQUAL,  templateMasterObid);
        List<TemplateDependencyVO> dependencyList = ObjectRoot.getRelationshipSet(list, ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        return dependencyList;
    }
    public List<TemplateDependencyVO> getWBSTemplatePreviousDependency(WBSItemTemplatesVO templatesVO){
        List<WBSItemTemplatesVO> list = new ArrayList<WBSItemTemplatesVO>();
        list.add(templatesVO);
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", 
                GlobalConstants.OQL_OPERATOR_EQUAL,  this.getObid());
        List<TemplateDependencyVO> dependencyList = ObjectRoot.getRelationshipSet(list, ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        return dependencyList;
    }
    public List<TemplateDependencyVO> getWBSTemplateNextDependency(WBSItemTemplatesVO templatesVO){
        List<WBSItemTemplatesVO> list = new ArrayList<WBSItemTemplatesVO>();
        list.add(templatesVO);
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", 
                GlobalConstants.OQL_OPERATOR_EQUAL,  this.getObid());
        List<TemplateDependencyVO> dependencyList = ObjectRoot.getRelationshipSet(list, ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_FROM,  selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        return dependencyList;
    }
    public void addUsedRole(String projectRoleName){
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, this);
        
        if(!isUsedRoleAtWbsTemplate(projectRoleName)){
            addFromObject(ApplicationSchemaConstants.RELCLASS_USEDROLEATWBSTEMPLATE, ProjectRole.getProjectRoleVO(projectRoleName), null,map);
        }
    }
    public void removeUsedRole( String projectRoleName){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, this);
        ProjectRoleVO usedRoleVO = getUsedRole(projectRoleName);
        if(usedRoleVO == null) return;
        new UsedRoleAtWBSTemplate((String)usedRoleVO.getOutData().get("rel_obid")).deleteObject(map);
    }
    public ProjectRoleVO getUsedRole(String projectRoleName){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]", 
                                     GlobalConstants.OQL_OPERATOR_EQUAL, projectRoleName );
        List<ProjectRoleVO> projectRoleList = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USEDROLEATWBSTEMPLATE, 
                                                                ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, 
                                                                GlobalConstants.FLAG_TYPE_FROM, selectPatternBuf.toString(), 
                                                                wherePatternBuf.toString(), paramPatternBuf.toString(), 
                                                                false, true, 0, 1);
        if(projectRoleList.isEmpty()){
            return null;
        }else if (projectRoleList.size() != 1){
            throw new FoundationException("Project Role duplicate.");   
        }
        return projectRoleList.get(0);
    }
    public List<ProjectRoleVO> getUsedRoleList(){
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USEDROLEATWBSTEMPLATE, 
                                 ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, 
                                 GlobalConstants.FLAG_TYPE_FROM);
    }
    public boolean isUsedRoleAtWbsTemplate(String projectRoleName){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]", 
                                     GlobalConstants.OQL_OPERATOR_EQUAL, projectRoleName );
        
        List<ProjectRoleVO> projectRoleList = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USEDROLEATWBSTEMPLATE, 
                                                                ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, 
                                                                GlobalConstants.FLAG_TYPE_FROM, selectPatternBuf.toString(), 
                                                                wherePatternBuf.toString(), paramPatternBuf.toString(), 
                                                                false, true, 0, 1);
        
        return !projectRoleList.isEmpty();
    }
    public List<WBSItemTemplatesVO> getWBSItemTemplateList(){
        
        List<WBSItemTemplatesVO> wbsItemTemplatesList = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, 
                ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, 
                GlobalConstants.FLAG_TYPE_FROM);
        return wbsItemTemplatesList;
    }
    /**
     * Template�� ���� �ʼ� Role Set�� Return��.
     *
     * @param projectDom
     * @param skippedPhaseSet
     * @return
     */
    public Set<String> getRequiredRoleSet(Projects projectDom){
        return getRequiredRoleSet(projectDom, projectDom.getVo().getProjectGradeCode());
        
    }
    public Set<String> getRequiredRoleSet(Projects projectDom, String projectGrade){
        Set<String> roleSet = new HashSet<String>();
        if(StrUtil.isEmpty(projectGrade)) return roleSet;
        Set<String> skippedPhaseSet = projectDom.getSkippedPhaseSet();
        List<WBSItemTemplatesVO> wbsItemTemplatesVOlist = this.getWBSItemTemplatesStructure(ProjectConstants.WBS_TEMPLATE_MAX_LEVEL);
        Map<String,ConvertedPhaseVO> convertedPhaseMap = new HashMap<String,ConvertedPhaseVO>();
        SortUtil.sort(wbsItemTemplatesVOlist, "uniqueString", false);
        
        DivisionUnitVO      divisionVO         = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, this.getVo().getDivisionUnit());
        ProjectLifeCycleVO  projectLifeCycleVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, this.getVo().getProjectLifeCycle());
        setProjectPhaseInfo(null,divisionVO, projectLifeCycleVO,wbsItemTemplatesVOlist,convertedPhaseMap);
        List<WBSItemTemplatesVO> requiredTemplate = new ArrayList<WBSItemTemplatesVO>();
        for(WBSItemTemplatesVO templateVO : wbsItemTemplatesVOlist){
            String phaseName = "";
            if((Integer)templateVO.getOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_setProjectPhaseNameTarget) == 1){
                String projectPhaseName = (String)templateVO.getOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_projectPhaseNameConverted);
                ConvertedPhaseVO convertedPhaseVO = convertedPhaseMap.get(projectPhaseName);
                phaseName = convertedPhaseVO.getNames();
            }else{
                phaseName = ProjectConstants.WBS_PROJECTPHASE_NAME_SUBPROJECT;
            }
            if(!skippedPhaseSet.contains(phaseName)){
                String skipInfo = getSkipInfo(projectGrade, templateVO.getSkipInfo());
                if("R".equals(skipInfo)){
                    requiredTemplate.add(templateVO);
                }
            }
        }
        if(!NullUtil.isNone(requiredTemplate)){
            List<ProjectRoleVO> templateRoleList = ObjectRoot.getRelatedObjectSet(requiredTemplate, ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLETOWBSTEMPLATEITEMS, ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, GlobalConstants.FLAG_TYPE_TO);
            for(ProjectRoleVO vo : templateRoleList){roleSet.add(vo.getNames());}
        }
        return roleSet;
    }
    
    public Set<String> getRequiredRoleSet(String wbsPhaseName, String projectGrade){
        HashSet<String> wbsPhaseNameSet = new HashSet<String>();
        wbsPhaseNameSet.add(wbsPhaseName);
        return getRequiredRoleSet(wbsPhaseNameSet, projectGrade);
    }
    
    public Set<String> getRequiredRoleSet(Set<String> wbsPhaseNameSet, String projectGrade){
        Set<String> roleSet = new HashSet<String>();
        if(StrUtil.isEmpty(projectGrade)) return roleSet;
        List<WBSItemTemplatesVO> wbsItemTemplatesVOlist = this.getWBSItemTemplatesStructure(ProjectConstants.WBS_TEMPLATE_MAX_LEVEL);
        Map<String,ConvertedPhaseVO> convertedPhaseMap = new HashMap<String,ConvertedPhaseVO>();
        SortUtil.sort(wbsItemTemplatesVOlist, "uniqueString", false);
        
        DivisionUnitVO      divisionVO         = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, this.getVo().getDivisionUnit());
        ProjectLifeCycleVO  projectLifeCycleVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, this.getVo().getProjectLifeCycle());
        setProjectPhaseInfo(null,divisionVO, projectLifeCycleVO,wbsItemTemplatesVOlist,convertedPhaseMap);
        List<WBSItemTemplatesVO> requiredTemplate = new ArrayList<WBSItemTemplatesVO>();
        for(WBSItemTemplatesVO templateVO : wbsItemTemplatesVOlist){
            String phaseName = "";
            if((Integer)templateVO.getOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_setProjectPhaseNameTarget) == 1){
                String projectPhaseName = (String)templateVO.getOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_projectPhaseNameConverted);
                ConvertedPhaseVO convertedPhaseVO = convertedPhaseMap.get(projectPhaseName);
                phaseName = convertedPhaseVO.getNames();
            }else{
                phaseName = ProjectConstants.WBS_PROJECTPHASE_NAME_SUBPROJECT;
            }
            if(wbsPhaseNameSet.contains(phaseName)){
                String skipInfo = getSkipInfo(projectGrade, templateVO.getSkipInfo());
                if("R".equals(skipInfo)){
                    requiredTemplate.add(templateVO);
                }
            }
        }
        if(!NullUtil.isNone(requiredTemplate)){
            List<ProjectRoleVO> templateRoleList = ObjectRoot.getRelatedObjectSet(requiredTemplate, ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLETOWBSTEMPLATEITEMS, ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, GlobalConstants.FLAG_TYPE_TO);
            for(ProjectRoleVO vo : templateRoleList){roleSet.add(vo.getNames());}
        }
        return roleSet;
    }
    public void createSpareWBSItemsList(ProjectScheduleVO projectScheduleVO){
        convertTemplateToWBSItemsList(null, projectScheduleVO, new HashSet<String>());
    }
    public synchronized ProjectScheduleVO getSpareProjectSchedule(ProjectsVO projectVO){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@REL.[forSpare]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        List<ProjectScheduleVO> relatedObjects = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_APPLIEDWBSTEMPLATE, 
                                                                   ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE, 
                                                                   GlobalConstants.FLAG_TYPE_FROM, 
                                                                   wherePattern.toString(), 
                                                                   parameterPattern.toString(), 1);
        if(!NullUtil.isNone(relatedObjects)){
            ProjectScheduleVO projectScheduleVO = relatedObjects.get(0);
            AppliedWBSTemplate appliedWBSTemplate = new AppliedWBSTemplate((String)projectScheduleVO.getOutDataAttributeValue("rel_obid"));
            // TODO
            appliedWBSTemplate.modifyObject();
            return projectScheduleVO;
        }
        return null;
    }
    public void getSpareWBSItemsList(ProjectsVO projectVO, ProjectScheduleVO projectScheduleVO, Set<String> skippedPhaseSet){
        ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleVO);
        Projects project = new Projects(projectVO);
        projectSchedule.refreshSkipInfoAsGrade(this.getObid(), projectVO.getProjectGradeCode(), project.getSkippedPhaseSet());
        projectSchedule.refreshActivityAssigned();
        addFromObject(ApplicationSchemaConstants.RELCLASS_REVISEDSCHEDULE, projectVO, null);
    }
    public void convertTemplateToWBSItemsList(String projectObid, ProjectScheduleVO projectScheduleVO,WBSTemplateMasterVO wbsTemplateMasterVo, Set<String> skippedPhaseSet){
        Projects project = new Projects(projectObid);
        convertTemplateToWBSItemsList(project.getVo(),projectScheduleVO,skippedPhaseSet, false);
    }
    public void convertTemplateToWBSItemsList(ProjectsVO projectVO, ProjectScheduleVO projectScheduleVO, Set<String> skippedPhaseSet){
        convertTemplateToWBSItemsList(projectVO, projectScheduleVO, skippedPhaseSet, false);
    }
    @SuppressWarnings({"unchecked","unused"})
    public void convertTemplateToWBSItemsList(ProjectsVO projectVO, ProjectScheduleVO projectScheduleVO, Set<String> skippedPhaseSet, boolean forSpare){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        Projects project = new Projects(projectVO);
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleVO);
        //Template�������� �ؾ� ��.
        DivisionUnitVO      divisionVO         = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, this.getVo().getDivisionUnit());
        ProjectLifeCycleVO  projectLifeCycleVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, this.getVo().getProjectLifeCycle());
        
        Map<String,ConvertedPhaseVO> convertedPhaseMap = new HashMap<String,ConvertedPhaseVO>();
        
        /****************** Template ���� Activity ��ü Get **************************/
        List<WBSItemTemplatesVO> wbsItemTemplatesVOlist = this.getWBSItemTemplatesStructure(ProjectConstants.WBS_TEMPLATE_MAX_LEVEL);
        SortUtil.sort(wbsItemTemplatesVOlist, "uniqueString", false);
        setProjectPhaseInfo(projectScheduleVO,divisionVO, projectLifeCycleVO,wbsItemTemplatesVOlist,convertedPhaseMap);

        if(NullUtil.isNone(wbsItemTemplatesVOlist)) throw new FoundationException("No Data to Copy!");
        
        List<WBSItemTemplatesVO> originWBSItemTemplatesVOlist = new ArrayList<WBSItemTemplatesVO>();
        for(WBSItemTemplatesVO vo : wbsItemTemplatesVOlist){
            WBSItemTemplatesVO copiedVO = (WBSItemTemplatesVO)DomUtil.copy(vo);
            originWBSItemTemplatesVOlist.add(copiedVO);
        }
        List<JobActivityTemplateVO> jobActivityTemplateList = ObjectRoot.getRelatedObjectSet(originWBSItemTemplatesVOlist, ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE,GlobalConstants.FLAG_TYPE_TO);
        List<JobActivityTemplateVO> originJobActivityTemplateList = new ArrayList<JobActivityTemplateVO>();
        for(JobActivityTemplateVO vo : jobActivityTemplateList){
            JobActivityTemplateVO copiedVO = (JobActivityTemplateVO)DomUtil.copy(vo);
            originJobActivityTemplateList.add(copiedVO);
        }
        List<BusinessObjectRootVO> allBizObjList = new ArrayList<BusinessObjectRootVO>();
        allBizObjList.addAll(wbsItemTemplatesVOlist);
        allBizObjList.addAll(jobActivityTemplateList);
        /****************** Object ������ Name/Revision/States Setting **************************/ // 8
        List<BusinessObjectRootVO> wbsItemsVOList = convertTemplateVOListToWBSItemsVOList(divisionVO, projectVO, projectScheduleVO, skippedPhaseSet, allBizObjList, convertedPhaseMap, forSpare);

        /****************** WBSItems/WBSJobActivity Object Create **************************/ // 8
        Map<String,Object> createObjectsMap = ObjectRoot.createObjectSetBatch(wbsItemsVOList);
        List<BusinessObjectVO>         bizObjList       = (List<BusinessObjectVO>)createObjectsMap.get(GlobalConstants.OBJECT_CREATE_SET_BIZ);
        List<BusinessObjectMasterVO>   bizMasterObjList = (List<BusinessObjectMasterVO>)createObjectsMap.get(GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR);
        List<BusinessRelationObjectVO> relObjList       = (List<BusinessRelationObjectVO>)createObjectsMap.get(GlobalConstants.OBJECT_CREATE_SET_REL);
        
        Map<String,ObjectRootVO> objectFactory = new HashMap<String,ObjectRootVO>();
        for(BusinessObjectVO vo : bizObjList){objectFactory.put(vo.getObid(), vo);}
        for(BusinessObjectMasterVO vo : bizMasterObjList){objectFactory.put(vo.getObid(), vo);}
        for(BusinessRelationObjectVO vo : relObjList){objectFactory.put(vo.getObid(), vo);}
        Map<String,String> obidMap = makeObjectMap(projectScheduleVO,bizObjList,bizMasterObjList);
        
        /****************** HasSubWBSItems/AllocatedToMember Relation Create *******************************************/ // 34
        Map<String,Object> createRelObjectsMap = createWBSItemsRelationSet(allBizObjList,obidMap,userId);
        /****************** WBSDependency Relation Create *******************************************/ // 18
        List<WBSDependencyVO> wbsDependencyVOList = createWBSDependencyRelationSet(wbsItemTemplatesVOlist,originWBSItemTemplatesVOlist,obidMap,userId);
        /****************** AssignedToActivity Relation Create *******************************************/ // 18
        if(!forSpare){
            List<AssignedToActivityVO> assignedToActivityVOList = createAssignedToActivityRelationSet(project, projectScheduleDom,wbsItemTemplatesVOlist, originWBSItemTemplatesVOlist,obidMap,objectFactory);
        }
    }
    private void setProjectPhaseInfo(ProjectScheduleVO projectScheduleVO, DivisionUnitVO divisionVO,ProjectLifeCycleVO  
                                     projectLifeCycleVO,List<WBSItemTemplatesVO> wbsItemTemplatesVOlist,Map<String,ConvertedPhaseVO> convertedPhaseMap){
        Set<String> strSet = new HashSet<String>();
        Set<String> strPhaseSet = new HashSet<String>();
        ProjectLifeCycle projectLifeCycleDom = new ProjectLifeCycle(projectLifeCycleVO);
        int len = 0;
        for(WBSItemTemplatesVO vo : wbsItemTemplatesVOlist){
            if(vo instanceof WBSPhaseTemplateVO) {
                strPhaseSet.add(((WBSPhaseTemplateVO)vo).getProjectPhaseName());
                strSet.add(vo.getUniqueString());
                len = vo.getUniqueString().length();
            }
        }
        List<ConvertedPhaseVO> convertedPhaseList = projectLifeCycleDom.getConvertedPhaseList(divisionVO, strPhaseSet, false);
        for(ConvertedPhaseVO vo : convertedPhaseList){
            convertedPhaseMap.put(vo.getNames(), vo);
        }
        for(WBSItemTemplatesVO vo : wbsItemTemplatesVOlist){
            String tempStr =  vo.getUniqueString().substring(0,len);
            if(strSet.contains(tempStr)){
                vo.setOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_setProjectPhaseNameTarget, 1);
            }else{
                vo.setOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_setProjectPhaseNameTarget, 0);
            }
        }
        String projectPhaseName = "None";
        for(WBSItemTemplatesVO vo : wbsItemTemplatesVOlist){
            if(vo instanceof WBSPhaseTemplateVO) projectPhaseName = ((WBSPhaseTemplateVO)vo).getProjectPhaseName();
            if((Integer)vo.getOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_setProjectPhaseNameTarget) == 1){
                vo.setOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_projectPhaseNameConverted, projectPhaseName);
            }else{
                vo.setOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_projectPhaseNameConverted, ProjectConstants.WBS_PROJECTPHASE_NAME_SUBPROJECT);
            }
        }
    }
    private List<BusinessObjectRootVO> convertTemplateVOListToWBSItemsVOList(DivisionUnitVO divisionVO, ProjectsVO projectVO, ProjectScheduleVO projectScheduleVO, Set<String> skippedPhaseSet, List<BusinessObjectRootVO> willBeCreatedObjectList, Map<String,ConvertedPhaseVO> convertedPhaseMap, boolean forSpare){
        List<BusinessObjectRootVO> result = new ArrayList<BusinessObjectRootVO>();
        String lifeCycle = "";
        String states = "";
        boolean isFirst = true;
        for(BusinessObjectRootVO vo : willBeCreatedObjectList){
            if(isFirst) {
                lifeCycle = getMatchedLifeCycleNameAsTemplate(vo.getClassName()); 
                states = LifeCycleUtil.getLifeCycleInfo(lifeCycle).getFirstState().getStateName();
            }else{
                if(!lifeCycle.equals(getMatchedLifeCycleNameAsTemplate(vo.getClassName()))){
                    lifeCycle = getMatchedLifeCycleNameAsTemplate(vo.getClassName());
                    states = LifeCycleUtil.getLifeCycleInfo(lifeCycle).getFirstState().getStateName();
                }
            }
            vo.setStates(states);
            vo.setLifeCycle(lifeCycle);
            if(vo instanceof WBSItemTemplatesVO){
                ((WBSItemTemplatesVO)vo).setRevision("1.0");
                result.add(createWBSItemsVO(divisionVO,projectVO,projectScheduleVO,(WBSItemTemplatesVO)vo,skippedPhaseSet, convertedPhaseMap, forSpare));
            }else if(vo instanceof JobActivityTemplateVO){
                result.add(createWBSJobActivityVO((JobActivityTemplateVO)vo, forSpare));
            }else{
                throw new FoundationException("Not Supported Object.");
            }
            lifeCycle = getMatchedLifeCycleNameAsTemplate(vo.getClassName());
            isFirst = false;
        }
        return result;
    }
    /**
     * 
     *
     * @param relatedObjectList
     * @param obidMap
     * @param userId
     * @return
     */
    private Map<String,Object> createWBSItemsRelationSet(List<BusinessObjectRootVO> relatedObjectList, Map<String,String> obidMap,String userId){
        Map<String,Object> createRelObjectsMap = new HashMap<String,Object>();
        List<BusinessRelationObjectVO> willBeCreatedRelList = new ArrayList<BusinessRelationObjectVO>();
        for(BusinessObjectRootVO vo : relatedObjectList){
            HashMap<String, Object> outData = vo.getOutData();
            String className  = (String)outData.get("rel_className");
            String fromClass  = (String)outData.get("rel_fromClass");
            String fromObid   = (String)outData.get("rel_fromObid");
            String toClass    = (String)outData.get("rel_toClass");
            String toObid     = (String)outData.get("rel_toObid");
            if(className.equals(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE)){
                Integer sequences = (Integer)outData.get("rel_sequences");
                HasSubWBSItemsVO hasSubWBSItemsVO = makeHasSubWBSItemsVO(obidMap,fromClass,fromObid,toClass,toObid,sequences,userId);
                willBeCreatedRelList.add(hasSubWBSItemsVO);
                willBeCreatedRelList.add(makeControlledByProjectScheduleContextVO(obidMap,vo.getClassName(),vo.getObid(),userId));
            }else if(className.equals(ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE)){
                AllocatedToMemberVO allocatedToMemberVO = makeAllocatedToMemberVO(obidMap,fromClass,fromObid,toClass,toObid,userId);
                willBeCreatedRelList.add(allocatedToMemberVO);
            }
        }
        if(!NullUtil.isNone(willBeCreatedRelList)){
            createRelObjectsMap = ObjectRoot.createObjectSetBatch(willBeCreatedRelList);
        }
        return createRelObjectsMap;
    }
    /**
     * 
     *
     * @param templateClassName
     * @return
     */
    private String getMatchedClassNameAsTemplate(String templateClassName){
        String result = null;
        if(ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER.equals(templateClassName)){
            result = ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE;
        }else if(ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTTEMPLATE.equals(templateClassName)){
            result = ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTS;
        }else if(ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE.equals(templateClassName)){
            result = ApplicationSchemaConstants.BIZCLASS_WBSPHASES;
        }else if(ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE.equals(templateClassName)){
            result = ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY;
        }else if(ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE.equals(templateClassName)){
            result = ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY;
        }else if(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY.equals(templateClassName)){
            result = ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT;
        }else if(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE.equals(templateClassName)){
            result = ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS;
        }else if(ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY.equals(templateClassName)){
            result = ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY;
        }
        
        return result;
    }
    private String getMatchedLifeCycleNameAsTemplate(String templateClassName){
        String result = null;
        if(ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTTEMPLATE.equals(templateClassName)){
            result = ApplicationSchemaConstants.LIFECYCLE_WBS_SUBPROJECTS;
        }else if(ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE.equals(templateClassName)){
            result = ApplicationSchemaConstants.LIFECYCLE_WBS_PHASE;
        }else if(ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE.equals(templateClassName)){
            result = ApplicationSchemaConstants.LIFECYCLE_WBS_ACTIVITY;
        }else if(ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE.equals(templateClassName)){
            result = ApplicationSchemaConstants.LIFECYCLE_WBS_JOB_ACTIVITY;
        }
        return result;
    }
    /**
     * 
     *
     * @param project
     * @param projectScheduleDom
     * @param wbsItemTemplatesVOlist
     * @param originWBSItemTemplatesVOlist
     * @param obidMap
     * @param objectFactory
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<AssignedToActivityVO> createAssignedToActivityRelationSet(Projects project, 
                                                                           ProjectSchedule projectScheduleDom,
                                                                           List<WBSItemTemplatesVO> wbsItemTemplatesVOlist, 
                                                                           List<WBSItemTemplatesVO> originWBSItemTemplatesVOlist, 
                                                                           Map<String,String> obidMap,
                                                                           Map<String,ObjectRootVO> objectFactory){
        List<ProjectRoleVO> templateRoleList = ObjectRoot.getRelatedObjectSet(originWBSItemTemplatesVOlist, ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLETOWBSTEMPLATEITEMS, ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, GlobalConstants.FLAG_TYPE_TO);
        List<ProjectDefinedRoleVO> definedRoleList = project.getProjectRoles();
        List<AssignedToActivityVO> willAssignedToActivityRoleList = convertTemplateRoleToProjectDefinedRole(definedRoleList,templateRoleList,obidMap,objectFactory);
        List<AssignedToActivityVO> lastCreatedList =  SortUtil.groupBy(willAssignedToActivityRoleList, "fromObid:toObid", null, null, null);

        //STATE_PROJECT_SCHEDULE_STARTED,STATE_PROJECT_SCHEDULE_ACTIVE�ΰ�� ������ ���� Interface�� �ؾ� �ϹǷ� ObjectRoot.createObjectSet�� �̿��ؼ� �����ؾ� ��.
        if(StrUtil.in(projectScheduleDom.getStates(), ProjectConstants.SCHEDULE_INTERFACE_STATE_SET)){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put(ProjectConstants.WBS_MAP_SCHEDULE_scheduleVO, projectScheduleDom.getVo());
            List<AssignedToActivityVO> createdList = ObjectRoot.createObjectSet(lastCreatedList, map);
            return createdList;
        }
        Map<String,Object> createdMap = ObjectRoot.createObjectSetBatch(lastCreatedList);
        projectScheduleDom.refreshActivityOwnerListAll();
        return (List<AssignedToActivityVO>)createdMap.get(GlobalConstants.OBJECT_CREATE_SET_REL);
    }
    /**
     * Template�� �Ҵ�Ǿ��� Role �� Owner�� Activity�� �����ؼ� Assigned Role �� Person�� �Ҵ���.
     *
     * @param definedRoleList
     * @param templateRoleList
     * @param obidMap
     * @param objectFactory
     * @return
     */
    private List<AssignedToActivityVO> convertTemplateRoleToProjectDefinedRole(List<ProjectDefinedRoleVO> definedRoleList, List<ProjectRoleVO> templateRoleList, Map<String,String> obidMap,Map<String,ObjectRootVO> objectFactory){
        List<AssignedToActivityVO> willBeCreatedAssignedToActivityList = new ArrayList<AssignedToActivityVO>();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@REL.[isMainMember]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        List<ProjectPersonVO> projectPersonList = ObjectRoot.getRelatedObjectSet(definedRoleList, ApplicationSchemaConstants.RELCLASS_ALLOCATEDMYROLE, 
                                                                                 ApplicationSchemaConstants.BIZCLASS_PROJECTPERSON, GlobalConstants.FLAG_TYPE_FROM,
                                                                                 "", wherePattern.toString(), parameterPattern.toString());
        for(ProjectRoleVO templateRoleVO : templateRoleList){
            ProjectDefinedRoleVO foundDefinedRole = findProjectDefinedRoleWithTemplateRole(templateRoleVO.getNames(),definedRoleList);
            if(!NullUtil.isNull(foundDefinedRole)){
                AssignedToActivityVO vo = new AssignedToActivityVO();
                String fromObid = (String)obidMap.get((String)templateRoleVO.getOutDataAttributeValue("rel_fromObid"));
                WBSItemsVO wbsItemsVO = (WBSItemsVO)objectFactory.get(fromObid);
                vo.setFromClass(wbsItemsVO.getClassName());
                vo.setFromObid(fromObid);
                vo.setToClass(foundDefinedRole.getClassName());
                vo.setToObid(foundDefinedRole.getObid());
                vo.setClassName(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY);
                willBeCreatedAssignedToActivityList.add(vo);
                List<AssignedToActivityVO> madeAssignedToActivityList = makeAssignedToActivityForProjectPerson(foundDefinedRole,projectPersonList,wbsItemsVO.getClassName(),fromObid);
                willBeCreatedAssignedToActivityList.addAll(madeAssignedToActivityList);
            }
        }
        return willBeCreatedAssignedToActivityList;
    }
    /**
     * Role�����Ǿ����� Project Defined Role�� Find��.
     *
     * @param roleCode
     * @param definedRoleList
     * @return
     */
    private ProjectDefinedRoleVO findProjectDefinedRoleWithTemplateRole(String roleCode,List<ProjectDefinedRoleVO> definedRoleList){
        for(ProjectDefinedRoleVO roleVO : definedRoleList){
            if(roleCode.equals(roleVO.getRoleCode())) return roleVO;
        }
        return null;
    }
    private List<AssignedToActivityVO> makeAssignedToActivityForProjectPerson(ProjectDefinedRoleVO foundDefinedRole,List<ProjectPersonVO> projectPersonList, String fromClass, String fromObid){
        List<AssignedToActivityVO> madeAssignedToActivityList = new ArrayList<AssignedToActivityVO>();
        for(ProjectPersonVO personVO : projectPersonList){
            if(foundDefinedRole.getObid().equals((String)personVO.getOutDataAttributeValue("rel_toObid"))){
                AssignedToActivityVO vo = new AssignedToActivityVO();
                vo.setFromClass(fromClass);
                vo.setFromObid(fromObid);
                vo.setToClass(personVO.getClassName());
                vo.setToObid(personVO.getObid());
                vo.setClassName(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY);
                vo.setIsMainMember("Y");
                madeAssignedToActivityList.add(vo);
            }
        }
        return madeAssignedToActivityList;
    }
    /**
     * 
     *
     * @param wbsItemTemplatesVOlist
     * @param originWBSItemTemplatesVOlist
     * @param obidMap
     * @param userId
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<WBSDependencyVO> createWBSDependencyRelationSet(List<WBSItemTemplatesVO> wbsItemTemplatesVOlist,List<WBSItemTemplatesVO> originWBSItemTemplatesVOlist,Map<String,String> obidMap,String userId){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL,  this.getObid());
        List<TemplateDependencyVO> wbsTemplateDependencyRelList = ObjectRoot.getRelationshipSet(originWBSItemTemplatesVOlist, ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        List<WBSDependencyVO> createList = new ArrayList<WBSDependencyVO>();
        for(TemplateDependencyVO vo : wbsTemplateDependencyRelList){
            if(StrUtil.isEmpty(obidMap.get(vo.getToObid()))){continue;}
            WBSDependencyVO wbsDependencyVO = new WBSDependencyVO();
            wbsDependencyVO.setFromObid(obidMap.get(vo.getFromObid()));
            wbsDependencyVO.setFromClass(getMatchedClassNameAsTemplate(vo.getFromClass()));
            wbsDependencyVO.setToObid(obidMap.get(vo.getToObid()));
            wbsDependencyVO.setToClass(getMatchedClassNameAsTemplate(vo.getToClass()));
            wbsDependencyVO.setDependencyType(vo.getDependencyType());
            wbsDependencyVO.setIsMandantory("Y");
            wbsDependencyVO.setCreator(userId);
            wbsDependencyVO.setModifier(userId);
            createList.add(wbsDependencyVO);
        }
        Map<String,Object> map = ObjectRoot.createObjectSetBatch(createList);
        return (List<WBSDependencyVO>)map.get(GlobalConstants.OBJECT_CREATE_SET_REL);
    }

    private WBSItemsVO createWBSItemsVO(DivisionUnitVO divisionVO, ProjectsVO projectVO, ProjectScheduleVO projectScheduleVO, WBSItemTemplatesVO wbsItemTemplatesVO, Set<String> skippedPhaseSet, Map<String,ConvertedPhaseVO> convertedPhaseMap, boolean forSpare){

        WBSItemsVO wbsItemsVO = new WBSItemsVO();
        String hasChildActivity = "N";
        if(!StrUtil.isEmpty((String)wbsItemTemplatesVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))) hasChildActivity = "Y";
        
        String className = getMatchedClassNameAsTemplate(wbsItemTemplatesVO.getClassName());
        wbsItemsVO.setClassName(className);
        wbsItemsVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WBS_ITEMS));
        
        wbsItemsVO.setOriginActivityCode(wbsItemTemplatesVO.getActivityMasterName());

        wbsItemsVO.setTitles(wbsItemTemplatesVO.getTitles());
        wbsItemsVO.setLifeCycle(wbsItemTemplatesVO.getLifeCycle());
        wbsItemsVO.setStates(wbsItemTemplatesVO.getStates());
        wbsItemsVO.setRevision(wbsItemTemplatesVO.getRevision());
        
        wbsItemsVO.setActivityNameEng(wbsItemTemplatesVO.getActivityNameEng());
        wbsItemsVO.setActivityNameKor(wbsItemTemplatesVO.getActivityNameKor());
        wbsItemsVO.setActivityNameChi(wbsItemTemplatesVO.getActivityNameChi());
        
        /************************************************************************************************************/
        if(!forSpare){
            wbsItemsVO.setProjectName(projectVO.getNames());
        }
        wbsItemsVO.setTemplateName(wbsItemTemplatesVO.getNames());
        if((Integer)wbsItemTemplatesVO.getOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_setProjectPhaseNameTarget) == 1){
            String projectPhaseName = (String)wbsItemTemplatesVO.getOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE2WBS_MAP_projectPhaseNameConverted);
            ConvertedPhaseVO convertedPhaseVO = convertedPhaseMap.get(projectPhaseName);
            wbsItemsVO.setPhaseName(convertedPhaseVO.getCodeForDivision());
            wbsItemsVO.setPhaseTitles(convertedPhaseVO.getNameForDivision());
            wbsItemsVO.setPhaseNameSystem(convertedPhaseVO.getCodeForSystemControl());
            wbsItemsVO.setPhaseTitlesSystem(convertedPhaseVO.getNameForSystemControl());
        }else{
            wbsItemsVO.setPhaseName(ProjectConstants.WBS_PROJECTPHASE_NAME_SUBPROJECT);
            wbsItemsVO.setPhaseTitles(ProjectConstants.WBS_DISPLAY_TYPE_WBS_SUBPROJECT);
            wbsItemsVO.setPhaseNameSystem(ProjectConstants.WBS_PROJECTPHASE_NAME_SUBPROJECT);
            wbsItemsVO.setPhaseTitlesSystem(ProjectConstants.WBS_PROJECTPHASE_NAME_SUBPROJECT);
        }
        /************************************************************************************************************/
        Integer durlation   = 1;
        String canBeSkipped = "N";
        String skipInfo     = "R";
        /*****************************************canBeSkipped **********************************************/
        if(skippedPhaseSet.contains(wbsItemsVO.getPhaseName())){
            durlation    = 0;
            canBeSkipped = "Y";
            skipInfo     = "Y";
            wbsItemsVO.setIsSkipped("Y");
        }else{
            skipInfo = getSkipInfo(projectVO.getProjectGradeCode(), wbsItemTemplatesVO.getSkipInfo());
            if(StrUtil.isEmpty(skipInfo)) skipInfo = "Y";
            if(ProjectConstants.NA.equals(skipInfo)){
                wbsItemsVO.setIsSkipped("Y");
                canBeSkipped = "Y";
            }else{
                wbsItemsVO.setIsSkipped("N");
                if(skipInfo.equals("R")) canBeSkipped = "N";
                if(skipInfo.equals("Y")) canBeSkipped = "Y";
            }
        }
        wbsItemsVO.setCanBeSkipped(canBeSkipped);
        
        if(forSpare){
            wbsItemsVO.setIsSkipped("Y");
            wbsItemsVO.setCanBeSkipped("Y");
        }
        /*****************************************Duration**********************************************/
        
        String isMilesStone = wbsItemTemplatesVO.getIsMilestone();
        if(className.equals(ApplicationSchemaConstants.BIZCLASS_WBSPHASES) || "Y".equals(hasChildActivity)) isMilesStone = "N";
        if("Y".equals(isMilesStone)){
            durlation = 1;
        }else{
            durlation = wbsItemTemplatesVO.getStandardDuration();
            if(durlation < 1) durlation = 1;                
        }
        if(wbsItemsVO.getIsSkipped().equals("Y")) durlation = 0; 
        wbsItemsVO.setIsMilestone(isMilesStone);
        wbsItemsVO.setStandardDuration(wbsItemTemplatesVO.getStandardDuration());
        wbsItemsVO.setPlanDuration(durlation);
        wbsItemsVO.setBasedDaysForDelay(calculateBasedDaysForDelay(durlation));
        wbsItemsVO.setInstruction(wbsItemTemplatesVO.getInstruction());
        
        wbsItemsVO.setCompleteType(wbsItemTemplatesVO.getCompleteType());
        wbsItemsVO.setActivityCategory(wbsItemTemplatesVO.getActivityCategory());
        wbsItemsVO.setActivityUrl(wbsItemTemplatesVO.getActivityUrl());
        wbsItemsVO.setStartValidationMethod(wbsItemTemplatesVO.getStartValidationMethod());    
        wbsItemsVO.setStartExecutionMethod(wbsItemTemplatesVO.getStartExecutionMethod());    
        wbsItemsVO.setCompleteValidationMethod(wbsItemTemplatesVO.getCompleteValidationMethod()); 
        wbsItemsVO.setPostExecutionMethod(wbsItemTemplatesVO.getPostExecutionMethod());
        wbsItemsVO.setInboxCompletionType(wbsItemTemplatesVO.getInboxCompletionType());
        wbsItemsVO.setIsAutoComplete(wbsItemTemplatesVO.getIsAutoComplete());
        
        wbsItemsVO.setIsKeyActivity(wbsItemTemplatesVO.getIsKeyActivity());
        wbsItemsVO.setIsCommon("Y");
        wbsItemsVO.setIsMannuallyAdded("N");
        
        // 2018-09-12 ������ (���� ����)
        // wbsItemsVO.setPreviousActivityList(wbsItemTemplatesVO.getPreviousActivityList());
        wbsItemsVO.setPreviousActivityList("N/A ..)/");
        wbsItemsVO.setRoleList(wbsItemTemplatesVO.getRoleList());
        //OwnerList�� ���� ������ ���� Refresh�ǹǷ�.....
        wbsItemsVO.setActivityOwnerList(wbsItemTemplatesVO.getActivityOwner());
        wbsItemsVO.getOutData().put("olObid", wbsItemTemplatesVO.getObid());
        wbsItemsVO.setIsForceStartable("N");
        
        wbsItemsVO.setActivityUrl(wbsItemTemplatesVO.getActivityUrl());
        return wbsItemsVO;
    }
    
    private Integer calculateBasedDaysForDelay(Integer duration){
        if(duration == null) duration = 0;
        float fcalculated = (float)duration * 0.1f;
        int calculated = (int)fcalculated;
        if(calculated == 0) calculated = 1;
        return calculated;
    }
    
    public String getSkipInfo(String projectGradeCode, String templateSkipInfo){
        String result = ProjectConstants.NA;
        if(StrUtil.isEmpty(templateSkipInfo)) return result;
        templateSkipInfo = ',' + templateSkipInfo;
        String key = ',' + projectGradeCode + ':';
        if(templateSkipInfo.indexOf(key) > -1){
            result = templateSkipInfo.substring(templateSkipInfo.indexOf(key) + key.length()).substring(0,1);
        }
        return result;
    }
    
    private WBSJobActivityVO createWBSJobActivityVO(JobActivityTemplateVO jobActivityTemplateVO, boolean forSpare){
        WBSJobActivityVO wbsJobActivityVO = new WBSJobActivityVO();
        wbsJobActivityVO.setClassName(ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY);
        wbsJobActivityVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WBS_JOB_ACTIVITY));
        wbsJobActivityVO.setTitles(jobActivityTemplateVO.getTitles());
        wbsJobActivityVO.setLifeCycle(jobActivityTemplateVO.getLifeCycle());
        wbsJobActivityVO.setStates(jobActivityTemplateVO.getStates());
        wbsJobActivityVO.setActivityNameKor(jobActivityTemplateVO.getActivityNameKor());
        wbsJobActivityVO.setActivityNameEng(jobActivityTemplateVO.getActivityNameEng());
        wbsJobActivityVO.setActivityNameChi(jobActivityTemplateVO.getActivityNameChi());
        wbsJobActivityVO.setFromTemplate("Y");
        wbsJobActivityVO.getOutData().put("olObid", jobActivityTemplateVO.getObid());
        return wbsJobActivityVO;
    }
    
    private HasSubWBSItemsVO makeHasSubWBSItemsVO(Map<String,String> obidMap, String fromClass, String fromObid, String toClass, String toObid, Integer sequences,String userId){
        Date date = null;
        HasSubWBSItemsVO hasSubWBSItemsVO = new HasSubWBSItemsVO();
        hasSubWBSItemsVO.setClassName(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS);
        hasSubWBSItemsVO.setFromClass(getMatchedClassNameAsTemplate(fromClass));
        hasSubWBSItemsVO.setFromObid(obidMap.get(fromObid));
        hasSubWBSItemsVO.setToClass(getMatchedClassNameAsTemplate(toClass));
        hasSubWBSItemsVO.setToObid(obidMap.get(toObid));
        hasSubWBSItemsVO.setCheckouted(date);
        hasSubWBSItemsVO.setSequences(sequences);
        return hasSubWBSItemsVO;
    }
    
    private AllocatedToMemberVO makeAllocatedToMemberVO(Map<String,String> obidMap, String fromClass, String fromObid, String toClass, String toObid, String userId){
        Date date = null;
        AllocatedToMemberVO allocatedToMemberVO = new AllocatedToMemberVO();
        allocatedToMemberVO.setClassName(ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER);
        allocatedToMemberVO.setFromClass(getMatchedClassNameAsTemplate(fromClass));
        allocatedToMemberVO.setFromObid(obidMap.get(fromObid));
        allocatedToMemberVO.setToClass(getMatchedClassNameAsTemplate(toClass));
        allocatedToMemberVO.setToObid(obidMap.get(toObid));
        allocatedToMemberVO.setCheckouted(date);

        return allocatedToMemberVO;
    }
    private ControlledByProjectScheduleContextVO makeControlledByProjectScheduleContextVO(Map<String,String> obidMap, String fromClass, String fromObid, String userId){
        ControlledByProjectScheduleContextVO controlledByProjectScheduleContextVO = new ControlledByProjectScheduleContextVO();
        
        controlledByProjectScheduleContextVO.setClassName(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT);
        controlledByProjectScheduleContextVO.setToObid(obidMap.get(this.getVo().getObid()));
        controlledByProjectScheduleContextVO.setToClass(ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE);
        controlledByProjectScheduleContextVO.setFromObid(obidMap.get(fromObid));
        controlledByProjectScheduleContextVO.setFromClass(getMatchedClassNameAsTemplate(fromClass));
            
        return controlledByProjectScheduleContextVO;
    }
    public static List<TemplateDependencyVO> getTemplateDependencyAll(String wbsTemplateMasterObid, List<WBSItemTemplatesVO> list){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructSelectPattern(selectPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE+"].obid " + ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR);
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL,  wbsTemplateMasterObid);
        List<TemplateDependencyVO> dependencyList = ObjectRoot.getRelationshipSet(list, ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        return dependencyList;
    }
    public List<TemplateDependencyVO> getTemplateDependencyAll(){
        List<WBSItemTemplatesVO> list = this.getWBSItemTemplateList();
        return getTemplateDependencyAll(this.getObid(), list);
    }
    
    public static void makePreviousActivityList(List<WBSItemTemplatesVO> structureList,List<TemplateDependencyVO> dependencyList){
        Map<String,WBSItemTemplatesVO> activityObjectDB = new HashMap<String, WBSItemTemplatesVO>();
        for(WBSItemTemplatesVO objVO : structureList){
            activityObjectDB.put(objVO.getObid(), objVO);
        }
        for(WBSItemTemplatesVO vo : structureList){
            vo.setPreviousActivityList("");
            WBSItemTemplatesVO previousActivityVO = null;
            StringBuffer strBuf = new StringBuffer();
            for(TemplateDependencyVO depVO : dependencyList){
                if(vo.getObid().equals(depVO.getFromObid())) {
                    previousActivityVO = activityObjectDB.get(depVO.getToObid());
                    //strBuf.append("^~^").append(previousActivityVO.getActivityNameEng()).append("^+^").append(previousActivityVO.getActivityNameKor()).append("^+^").append(previousActivityVO.getActivityNameChi()).append("^+^").append(depVO.getDependencyType());
                    strBuf.append("^~^").append(previousActivityVO.getActivityNameEng()).append("^+^").append(previousActivityVO.getActivityNameKor()).append("^+^").append("-").append("^+^").append(depVO.getDependencyType());
                }
            }
            if(strBuf.length() > 3) {vo.setPreviousActivityList(strBuf.substring(3));}
        }
    }
    
    public boolean isEmpty(){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!To["+ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLETOWBSTEMPLATEITEMS+"].obid",GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        WBSTemplateMasterVO vo = BusinessObjectRoot.findObject(this.getClassName(), this.getNames(), this.getRevision(), selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(), false);
        if(NullUtil.isNull(vo)) return true;
        return false;
    }
    public boolean canBeAdded(BusinessObjectRootVO vo){
        
        boolean returnValue = true;
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        BusinessObjectRoot bizRootDom = new BusinessObjectRoot(vo);

        if(bizRootDom.isKindOf(ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE)){
            WBSPhaseTemplateVO addedVO = (WBSPhaseTemplateVO)vo;
            StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[projectPhaseName]",GlobalConstants.OQL_OPERATOR_EQUAL, addedVO.getProjectPhaseName());
            List<WBSActivityTemplateVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
            if(!NullUtil.isNone(list)) returnValue = false;
        }else if(bizRootDom.isKindOf(ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTTEMPLATE)){
            ;
        }else if(bizRootDom.isKindOf(ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE)){
            WBSActivityTemplateVO addedVO = (WBSActivityTemplateVO)vo;
            ActivityTemplateMasterVO masterVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER, addedVO.getActivityMasterName());
            if(!NullUtil.isNull(masterVO) && "Y".equals(masterVO.getIsOnlyOnePerTemplate())){
                StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[activityMasterName]",GlobalConstants.OQL_OPERATOR_EQUAL, addedVO.getActivityMasterName());
                List<WBSActivityTemplateVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
                if(!NullUtil.isNone(list)) returnValue = false;
            }
        }else if(bizRootDom.isKindOf(ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE)){
            ;   
        }
        return returnValue;
    }
    
    public List<ProjectPhaseVO> getAppliedPhaseInfoList(boolean activeOnly){
        List<WBSPhaseTemplateVO> list = this.getAppliedPhaseTemplateList();
        return(this.getAppliedPhaseList(list,false,activeOnly));
    }
    public List<WBSPhaseTemplateVO> getAppliedPhaseTemplateList(){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        List<WBSPhaseTemplateVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        return list;   
    }
    public List<ProjectPhaseVO> getAppliedPhaseList(List<WBSPhaseTemplateVO> phaseTemplateList, boolean isOnlyApplied, boolean activeOnly){
        
        DivisionUnitVO divisionUnitVO = this.getDivisionUnitVO();
        if(NullUtil.isNull(divisionUnitVO)) return null;
        
        Set<String> strSet = new HashSet<String>();
        if(isOnlyApplied){
            for(WBSPhaseTemplateVO vo : phaseTemplateList){
                strSet.add(vo.getProjectPhaseName());
            }
        }
        ProjectLifeCycle lifeCycleDom = new ProjectLifeCycle(this.getLifeCycleVO());
        List<ProjectPhaseVO> list = lifeCycleDom.getProjectPhaseList(divisionUnitVO,strSet,activeOnly);
        if(isOnlyApplied) return list;
        
        for(ProjectPhaseVO vo : list){
            boolean isExists = false;
            for(WBSPhaseTemplateVO subVO : phaseTemplateList){
                if(vo.getNames().equals(subVO.getProjectPhaseName())) {isExists = true; break;}
            }
            if(isExists) {
                vo.setOutDataAttributeValue("isPhaseExist","Y");
            }else{
                vo.setOutDataAttributeValue("isPhaseExist","N");
            }
        }
        return list;
    }
    
    public List<ProjectPhaseVO> getNotAppliedProjectInfoList(boolean activeOnly){
        List<WBSPhaseTemplateVO> list = this.getAppliedPhaseTemplateList();
        return(this.getNotAppliedProjectInfoList(list,activeOnly));
    }
    
    public List<ProjectPhaseVO> getNotAppliedProjectInfoList(List<WBSPhaseTemplateVO> phaseTemplateList, boolean activeOnly){
        ProjectLifeCycle lifeCycleDom = new ProjectLifeCycle(this.getLifeCycleVO());
        DivisionUnitVO divisionUnitVO = this.getDivisionUnitVO();
        if(NullUtil.isNull(divisionUnitVO)) return null;
        
        List<ProjectPhaseVO> list = lifeCycleDom.getProjectPhaseList(divisionUnitVO,null,activeOnly);
        
        List<ProjectPhaseVO> notAppliedList = new ArrayList<ProjectPhaseVO>();
        for(ProjectPhaseVO vo : list){
            boolean isExists = false;
            for(WBSPhaseTemplateVO subVO : phaseTemplateList){
                if(vo.getNames().equals(subVO.getProjectPhaseName())) {isExists = true; break;}
            }
            if(!isExists) {
                notAppliedList.add(vo);
            }
        }
        return notAppliedList;
    }
    
    public ProjectLifeCycleVO getLifeCycleVO(){
        return BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, this.getVo().getProjectLifeCycle());
    }
    public boolean canAppliedForGrade(String grade){
        String appliedList = this.getVo().getAppliedGradeList();
        if(StrUtil.isEmpty(appliedList)) return false;
        Set<String> strSet = StrUtil.convertArrayToSet(appliedList.split(","));
        return strSet.contains(grade);
    }
    public boolean isRequiredPhase(String wbsPhaseName, String projectGrade){
        WBSPhaseTemplateVO wbsPhaseTemplateVO = getWBSPhaseTemplate(wbsPhaseName);
        if(NullUtil.isNull(wbsPhaseTemplateVO)){return false;}
        return "R".equals(getSkipInfo(projectGrade, wbsPhaseTemplateVO.getSkipInfo()));
    }
}

