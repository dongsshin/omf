/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSItems.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.intf.IfPlmDataByTriggerVO;
import com.rap.omc.intf.TriggerUtil;
import com.rap.omc.schema.object.temp.model.ActivityValidationResultVO;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.TimeServiceUtil;

import rap.api.object.document.dom.ProjectActivityDocument;
import rap.api.object.document.dom.ProjectActivityDocumentTemplate;
import rap.api.object.document.model.ProjectActivityDocumentTemplateVO;
import rap.api.object.document.model.ProjectActivityDocumentVO;
import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.model.ChangeProcessVO;
import rap.api.object.project.model.ProjectDefinedRoleVO;
import rap.api.object.project.model.ProjectMembersVO;
import rap.api.object.project.model.ProjectPersonVO;
import rap.api.object.project.model.ProjectRoleVO;
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSActivitiesVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.project.model.WBSPhasesVO;
import rap.api.object.project.model.WBSSubProjectsVO;
import rap.api.object.relation.dom.ActivityDeliverables;
import rap.api.object.relation.dom.AssignedToActivity;
import rap.api.object.relation.dom.ControlledByProjectScheduleContext;
import rap.api.object.relation.dom.HasSubWBSItems;
import rap.api.object.relation.model.ControlledByProjectScheduleContextVO;
import rap.api.object.relation.model.WBSDependencyVO;
import rap.api.object.workflow.dom.WorkflowInboxTask;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;
import rap.application.workflow.model.ActivityMethodParameterVO;
import rap.application.workflow.model.ApprovalVO;
import rap.application.workflow.model.DependencyVO;
import rap.application.workflow.model.WBSActivityInboxTaskVO;
import rap.application.workflow.model.WBSActivityWorkflowVO;
import rap.application.workflow.util.WBSUtil;
import rap.application.workflow.util.WBSWorkflowServiceUtil;


public class WBSItems extends BusinessObject {
    public WBSItems(String obid){
        super(obid);
    }
    public WBSItems(WBSItemsVO vo){
        super(vo);
    }
     @Override
    public WBSItemsVO getVo(){
        return (WBSItemsVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeWBSItems();
    }
    public void initializeWBSItems(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "WBSItems[toString()=" + super.toString() + "]";
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

    }

    @Override
    protected void postProcessForCreate(Map<String, Object> map){
        super.postProcessForCreate(map);
        /*code below*/
        Map<String, Object> attrMap = new HashMap<String, Object>();
        if(map.get("targetSequences") != null){
            Integer targetSequences =  (Integer)map.get("targetSequences");
            String addingPosition = (String)map.get("addingPosition");
            if(StrUtil.isEmpty(addingPosition)){
                if("previous".equals(addingPosition)){
                    targetSequences = HasSubWBSItems.getPrevSequences((String)map.get("parentObid"), targetSequences);
                }else if("next".equals(addingPosition)){
                    targetSequences = HasSubWBSItems.getNextSequences((String)map.get("parentObid"), targetSequences);
                }
            }
            attrMap.put("sequences", targetSequences);
        }
        BusinessObject parentObject = DomUtil.toDom((String)map.get("parentObid"));
        addToObject(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, new ProjectSchedule((String)map.get("projectScheduleObid")).getVo(), null);
        addFromObject(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, parentObject.getVo(), attrMap);
        this.createIfTriggerDataForAddActivity();
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
        this.createIfTriggerDataForDeleteActivity();
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
    /**
     * Activity�� ���� �̽��� ���/�����Ѵ�.
     *
     * @param issueContents
     */
    public void modifyIssueContents(String issueContents){
        modifyIssueContentsSub(issueContents);
    }
    /**
     * Recursive�ϰ� Update��.
     *
     * @param issueContents
     */
    private void modifyIssueContentsSub(String issueContents){
        if(!this.isLast()){
            WBSItemsVO wbsItemsVO = (WBSItemsVO)this.getNextRevision();
            WBSItems wbsItemsDom = new WBSItems(wbsItemsVO);
            wbsItemsDom.modifyIssueContents(issueContents);
        }
        Set<String> attributes = new HashSet<String>();
        attributes.add("issueContents");
        ObjectRoot.modifyObjectSetBatch(this.getVo(), attributes);
    }
    /**
     * 
     * 
     * @param inboxTaskVO
     * @return
     * @see omc.api.object.dom.BusinessObjectRoot#validateCompleteWBSActivity(omc.api.object.model.BusinessObjectRootVO)
     */
    @Override
    public List<ActivityValidationResultVO> validateCompleteWBSActivity(BusinessObjectRootVO inboxTaskVO){
        
        WorkflowInboxTaskVO cinboxTaskVO = (WorkflowInboxTaskVO)inboxTaskVO;
        String scheduleObid = cinboxTaskVO.getProcessTimestamp();
        ProjectSchedule scheduleDom = new ProjectSchedule(scheduleObid);
        
        Map<String,String> activityParameterMap = this.getParameterForWorkflow(scheduleDom.getVo());
        List<ActivityValidationResultVO> upperValidationList     = super.validateCompleteWBSActivity(inboxTaskVO);
        List<ActivityValidationResultVO> allValidationList       = new ArrayList<ActivityValidationResultVO>();
        List<ActivityValidationResultVO> essentiaaValidationList = validateForCompleteEssentialDoc(inboxTaskVO,activityParameterMap);
        List<ActivityValidationResultVO> bizLogicValidationList  = validateForCompleteActivityBiz(activityParameterMap);
        allValidationList.addAll(upperValidationList);
        allValidationList.addAll(essentiaaValidationList);
        allValidationList.addAll(bizLogicValidationList);
        return allValidationList;
    }
    /**
     * 
     *
     * @param inboxTaskVO
     * @param activityParameterMap
     * @return
     */
    private List<ActivityValidationResultVO> validateForCompleteEssentialDoc(BusinessObjectRootVO inboxTaskVO,Map<String,String> activityParameterMap){
        List<ActivityValidationResultVO> rtnList =  new ArrayList<ActivityValidationResultVO>();
        String projectObid = activityParameterMap.get(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_projectObid);
        
        Projects projects = DomUtil.toDom(projectObid);
        ProjectsVO projectsVO = projects.getVo();
        String projectGradeCode = projectsVO.getProjectGradeCode();
        WBSItemTemplatesVO relatedTemplate = this.getRelatedTemplate();
        if(relatedTemplate != null){
            WBSItemTemplates wbsItemTemplates = DomUtil.toDom(relatedTemplate);
            List<ProjectActivityDocumentTemplateVO> retrieveProjectActivityDocumentTemplate = wbsItemTemplates.retrieveProjectActivityDocumentTemplate();
            List<ProjectActivityDocumentVO> retrieveProjectActivityDocument = retrieveProjectActivityDocument(true, true);
            for(ProjectActivityDocumentTemplateVO projectActivityDocumentTemplateVO : retrieveProjectActivityDocumentTemplate){
                String templateNames = projectActivityDocumentTemplateVO.getNames();
                HashMap<String, Object> outData = projectActivityDocumentTemplateVO.getOutData();
                String isMandantory = (String)outData.get("rel_isMandantory");
                String skipGradeList = (String)outData.get("rel_skipGradeList");
                String documentType = ProjectActivityDocument.getDocumentType(true, projectGradeCode, isMandantory, skipGradeList);
                boolean hasDoc = false;
                boolean approvalCompleted = false;
                
                if("REQ".equals(documentType) || "DOC".equals(documentType)){
                    for(ProjectActivityDocumentVO projectActivityDocumentVO : retrieveProjectActivityDocument){
                        if(templateNames.equals(projectActivityDocumentVO.getOutData().get("templateNames")) || templateNames.equals(projectActivityDocumentVO.getOutData().get("rel_documentTemplateName"))){
                            hasDoc = true;
                            String changeProcessStates = (String)projectActivityDocumentVO.getOutData().get("changeProcessStates");
                            if( ApplicationSchemaConstants.STATE_PROJECT_ACTIVITY_DOCUMENT_APPROVED.equals(projectActivityDocumentVO.getStates()) 
                                    && ApplicationSchemaConstants.STATE_DOCUMENT_CHANGE_APPROVED.equals(changeProcessStates)){
                                approvalCompleted = true;
                            }
                            break;
                        }
                    }
                }
                ActivityValidationResultVO activityValidationResultForCompleteEssentialDoc = activityValidationResultForCompleteEssentialDoc(projectActivityDocumentTemplateVO, documentType, hasDoc, approvalCompleted);
                if(!ActivityValidationResultVO.RESULTCODE.success.equals(activityValidationResultForCompleteEssentialDoc.getErrorLevel())){
                    rtnList.add(activityValidationResultForCompleteEssentialDoc);
                }
            }
        }
        return rtnList;
    }
    /**
     *
     *
     * @param projectActivityDocumentTemplateVO
     * @param documentType
     * @param hasDoc
     * @param approvalCompleted
     * @return
     */
    private ActivityValidationResultVO activityValidationResultForCompleteEssentialDoc(ProjectActivityDocumentTemplateVO projectActivityDocumentTemplateVO, String documentType, boolean hasDoc, boolean approvalCompleted){
        String documentNameDisplay = ProjectActivityDocumentTemplate.getDocumentNameDisplay(projectActivityDocumentTemplateVO, null);
        if("DOC".equals(documentType) || "REQ".equals(documentType)){
            if(!hasDoc){
                return new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.error,
                        "Essential Document", 
                        "["+documentNameDisplay+"] Document does not exists.",
                        "Please register document.");
            }
            if("REQ".equals(documentType) && !approvalCompleted){
                return new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.error,
                        "Essential Document", 
                        "["+documentNameDisplay+"] Document not approved.",
                        "Please get approved.");
            }
        }
        return new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.success,
                "Essential Document", 
                "success",
                "success");
    }
    /**
     * �� Activity�� ���ǵǾ��� Validation Method�� ���� Check
     *
     * @param inboxTaskVO
     * @param activityParameterMap
     * @return
     */
    private List<ActivityValidationResultVO> validateForCompleteActivityBiz(Map<String,String> activityParameterMap){
        List<ActivityValidationResultVO> list = new ArrayList<ActivityValidationResultVO>();
        if("Y".equals(this.getVo().getIsReDoActivity())) return list;
        if(shouldBePerformedMethod(this.getVo().getCompleteValidationMethod())){
            ActivityMethodParameterVO activityMethodParameterVO = getMethodInfo(activityParameterMap,this.getVo().getCompleteValidationMethod());
            if(NullUtil.isNull(activityMethodParameterVO)){
                list.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.systemError,"Configuration",
                        "Activity System Configuration Error(Activity:" + this.getCommonTitlesForDisplay() + ",Program:" + this.getVo().getCompleteValidationMethod() + ")", "Please contact System Administrator."));
            }else{
                list = executeValidateMethod(activityMethodParameterVO);
            }
        }
        return list;
    }
    /**
     * 
     *
     * @param methodName
     * @return
     */
    private boolean shouldBePerformedMethod(String methodName){
        if(StrUtil.isEmpty(methodName)) return false;
        if(ProjectConstants.NONE.equals(methodName)) return false;
        if(methodName.indexOf(".") == -1) return false;
        return true;
    }
    /**
     * Activity���� Workflow���� ���Ǿ����� Parameter�� Return��.
     * 
     * @param businessObjVO
     * @return
     * @see omc.api.object.dom.BusinessObjectRoot#getParameterForWorkflow(omc.api.object.model.BusinessObjectRootVO)
     */
    @Override
    public Map<String,String> getParameterForWorkflow(BusinessObjectRootVO businessObjVO){
        Map<String,String> map = super.getParameterForWorkflow(businessObjVO);
        ProjectScheduleVO projectScheduleVO = null;
        if(businessObjVO instanceof ProjectScheduleVO){
            projectScheduleVO = (ProjectScheduleVO)businessObjVO;
        }else if(businessObjVO instanceof WorkflowInboxTaskVO){
            WorkflowInboxTaskVO inboxTaskVO = (WorkflowInboxTaskVO)businessObjVO;
            String scheduleObid = inboxTaskVO.getProcessTimestamp();
            if(StrUtil.isEmpty(scheduleObid)) throw new FoundationException("Worklow's Schedule Object Data is Invalid.(Empty)");
            ProjectSchedule projectScheduleDom = null;
            try{
                projectScheduleDom = new ProjectSchedule(scheduleObid);
            }catch(Exception e){
                throw new FoundationException("Worklow's Schedule Object Data is Invalid.(Object ID: " + scheduleObid + ")");
            }
            
            projectScheduleVO = projectScheduleDom.getVo();
        }else{
            throw new FoundationException("Invalid object(" + businessObjVO.getClassName() + "). Must be Schedule Object.");
        }
        if(StrUtil.isEmpty(this.getVo().getProjectName())) throw new FoundationException("Activity' project Name is empty.");
        ProjectsVO projectVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTS, this.getVo().getProjectName());
        if(NullUtil.isNull(projectVO)) throw new FoundationException("Activity(" + this.getVo().getActivityNameEng() + ")'s Project Info error!!!");
        map.put(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_projectObid, projectVO.getObid());
        map.put(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_projectName, projectVO.getNames());
        map.put(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_scheduleObid, projectScheduleVO.getObid());
        map.put(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_activityObid, this.getObid());
        map.put("projectLeader", (String)projectVO.getOutDataAttributeValue("projectLeaderUserTitles"));
        if(StrUtil.isEmpty(this.getVo().getPhaseName())) throw new FoundationException("Activity' Event Name(phaseName) is empty.");
        if(StrUtil.isEmpty(this.getVo().getPhaseTitles())) throw new FoundationException("Activity' Event Titles(phaseTitles) is empty.");
        if(StrUtil.isEmpty(this.getVo().getPhaseNameSystem())) throw new FoundationException("Activity' Event Name(phaseNameSystem) is empty.");
        if(StrUtil.isEmpty(this.getVo().getPhaseTitlesSystem())) throw new FoundationException("Activity' Event Name(phaseTitlesSystem) is empty.");
        map.put(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_phaseCode, this.getVo().getPhaseName());
        map.put(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_phaseTitles, this.getVo().getPhaseTitles());
        map.put(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_phaseCodeSystem, this.getVo().getPhaseNameSystem());
        map.put(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_phaseTitlesSystem, this.getVo().getPhaseTitlesSystem());
        return map;
    }
    /**
     * 
     *
     * @return
     */
    public final List<ProjectScheduleVO> getProjectScheduleList(){
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE, GlobalConstants.FLAG_TYPE_TO);
    }
    /**
     * Schedule Context�� ����Ǿ��� �ִ����� Return��.
     *
     * @param masterVO
     * @return
     */
    public final boolean isConnectedOtherContext(ProjectScheduleVO masterVO){
        return isConnectedOtherContext(masterVO.getObid());
    }
    /**
     * 
     *
     * @param masterObid
     * @return
     */
    public final boolean isConnectedOtherContext(String masterObid){
        List<ProjectScheduleVO> list = this.getProjectScheduleList();
        for(ProjectScheduleVO vo : list){
            if(!masterObid.equals(vo.getObid())) return true;
        }
        return false;
    }
    public final ControlledByProjectScheduleContextVO getControlledByProjectScheduleContext(String masterObid){
        StringBuffer selectPattern = new StringBuffer (); 
        StringBuffer wherePattern  = new StringBuffer (); 
        StringBuffer paramPattern  = new StringBuffer (); 
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, masterObid);
        List<ControlledByProjectScheduleContextVO> list = this.getRelationship(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), paramPattern.toString());
        if(NullUtil.isNone(list)) return null;
        if(list.size() > 1) throw new FoundationException("ControlledByProjectScheduleContextVO is invalid.");
        return  list.get(0);
    }
    public List<WBSItemsVO> getChildWBSItemList(String projectScheduleObid){
        return getChildWBSItemListSub(projectScheduleObid, false, 10);
    }
    public List<WBSItemsVO> getChildWBSItemList(String projectScheduleObid, int findDepth){
        ProjectScheduleVO projectScheduleVO = new ProjectSchedule(projectScheduleObid).getVo();
        return getChildWBSItemListSub(projectScheduleVO.getObid(), false, findDepth);
    }
    public List<WBSItemsVO> getChildWBSItemListActivityOnly(String projectScheduleObid, int findDepth){
        ProjectScheduleVO projectScheduleVO = new ProjectSchedule(projectScheduleObid).getVo();
        return getChildWBSItemListSub(projectScheduleVO.getObid(), true, findDepth);
    }
    private List<WBSItemsVO> getChildWBSItemListSub(String masterObid, boolean isActivityOnly, int findDepth){
        
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, masterObid );
        
        if(isActivityOnly){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        }
        
        StringUtil.addSortByPattern(selectPatternBuf, "@REL.[sequences] asc");
        
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, 
                                 ApplicationSchemaConstants.BIZCLASS_WBSITEMS, 
                                 GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(),
                                 wherePatternBuf.toString(), paramPatternBuf.toString(), 
                                 false, true, 0, findDepth);
    }

    @Override
    protected void validateForRevise(Map<String, Object> map){
        super.validateForRevise(map);
        /*code below*/
        if(NullUtil.isNull(map.get(ProjectConstants.CREATE_WBSITEM_SCHEDULE_MAP_PROJECTVO))) throw new FoundationException("Schedule Objects is null.");;
        if(!StrUtil.in(this.getStates(), ProjectConstants.WBSITEM_REVISIBLE_STATE_SET_ALL)) throw new FoundationException("Ony state() can be revisible. current state is " + this.getStates(),ProjectConstants.WBSITEM_REVISIBLE_STATE_SET_ALL);
    }
    @Override
    protected void preProcessForRevise(Map<String, Object> map){
        super.preProcessForRevise(map);
        /*code below*/
        ProjectScheduleVO projectScheduleVO = (ProjectScheduleVO)map.get(ProjectConstants.CREATE_WBSITEM_SCHEDULE_MAP_PROJECTVO);
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.contextBizObject, projectScheduleVO);
    }
    @Override
    protected void postProcessForRevise(Map<String, Object> map){
        super.postProcessForRevise(map);
    }
    /**
     * WBS Item�� Revise��.
     *
     * @param projectScheduleDom
     * @return
     */
    public WBSItemsVO reviseWBSItems(ProjectSchedule projectScheduleDom){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.CREATE_WBSITEM_SCHEDULE_MAP_PROJECTVO, projectScheduleDom.getVo());
        WBSItemsVO WBSItemsVO = (WBSItemsVO)this.revise(map);
        return WBSItemsVO;
    }
    /**
     * Cancel Revise
     *
     * @param projectScheduleDom
     * @return
     */
    public void cancelReviseWBSItem(ProjectSchedule projectScheduleDom){
        if(!StrUtil.in(this.getStates(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT)) throw new FoundationException("Ony state({}) can be revisible. current state is " + this.getStates(),ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT);
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, paramPattern, "!To["+ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER+"].fromObid",GlobalConstants.OQL_OPERATOR_NOT_EQUAL, this.getObid());
        List<WBSJobActivityVO> wbsJobActivityList = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY,
                                                                      GlobalConstants.FLAG_TYPE_TO, wherePattern.toString(), paramPattern.toString(), 1);
        for(WBSJobActivityVO vo : wbsJobActivityList){new WBSJobActivity(vo).deleteObject();}
        if(!this.isFirst()){
            WBSItems wbsItemsDom = new WBSItems(this.getNextObid());
            this.deleteObject();
            wbsItemsDom.addToObject(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, projectScheduleDom.getVo(), null);
        }else{
            this.deleteObject();
        }
    }
    /**
     * ������ Call�Ǿ����� Method
     *
     * @param generateName
     */
    public void setDefault(boolean generateName){
        if(generateName){
            getVo().setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WBS_ITEMS));
        }
        if(StrUtil.isEmpty(getVo().getTitles())){
            getVo().setTitles(".");
        }
        
        getVo().setStates(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT);
        getVo().setRevision("1.0");
    }
    public static Integer getDelayedDays(WBSItemsVO wbsItemsVO, Date baseDate, boolean includeForcastLogic){
        if(wbsItemsVO.getActualEndDate() != null) return 0;
        if(baseDate.compareTo(wbsItemsVO.getPlanEndDate()) > 0){
            return TimeServiceUtil.getDateDiff(wbsItemsVO.getPlanEndDate(), baseDate);
        }
        return 0;
    }
    public static Integer getDelayedDays(WBSItemsVO wbsItemsVO, boolean includeForcastLogic){
        Date baseDate = TimeServiceUtil.getDBLocalTime();
        return getDelayedDays(wbsItemsVO,baseDate,includeForcastLogic);
    }
    public Integer getDelayedDays(Date baseDate, boolean includeForcastLogic){
        if(this.getVo().getActualEndDate() != null) return 0;
        if(baseDate.compareTo(this.getVo().getPlanEndDate()) > 0){
            return TimeServiceUtil.getDateDiff(this.getVo().getPlanEndDate(), baseDate);
        }
        return 0;
    }
    public Integer getDelayedDays(boolean includeForcastLogic){
        Date baseDate = TimeServiceUtil.getDBLocalTime();
        return getDelayedDays(baseDate,includeForcastLogic);
    }
    /**
     * Role������ �ݿ���.(�߰��� ��)
     *
     * @param projectObid
     * @param roleCodeList
     */
    public void updateProjectRole(String projectObid, String roleCodeList){
        
        Set<String> projectRoleCodeList =  new HashSet<String>();
        if(StrUtil.isEmpty(roleCodeList)){
            projectRoleCodeList = StrUtil.convertArrayToSet(roleCodeList.split(","));
        }

        List<ProjectDefinedRoleVO> assignedProjectDefinedRole = getAssignedProjectDefinedRoleList();
        for(ProjectDefinedRoleVO projectDefinedRoleVO : assignedProjectDefinedRole){
            if(projectRoleCodeList.contains(projectDefinedRoleVO.getRoleCode())){
                projectRoleCodeList.remove(projectDefinedRoleVO.getRoleCode());
            }else{
                new AssignedToActivity((String)projectDefinedRoleVO.getOutData().get("rel_obid")).deleteObject();
            }
        }
        
        Projects projectsDom = new Projects(projectObid);
        List<ProjectDefinedRoleVO> assignedProjectRoleList = projectsDom.getProjectRoles();
        Map<String,Object> attributes = new HashMap<String,Object>();
        attributes.put("isMainMember", "N");
        attributes.put("actionUserId", "None");
        for(String projectRoleCode :projectRoleCodeList){
            addToObject(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, 
            		Projects.findProjectRole(projectRoleCode, assignedProjectRoleList), attributes);
        }
    }
    /**
     * Member������ �ݿ���(�߰��� ��)
     *
     * @param projectObid
     * @param activityOwnerCreateList
     * @param activityOwnerDeleteList
     */
    public void updateProjectPerson(Projects projectDom, ProjectSchedule projectScheduleDom, List<UsersVO> activityOwnerCreateList, List<UsersVO> activityOwnerUpdateList, List<UsersVO> activityOwnerDeleteList){
        List<ProjectPersonVO> assignedProjectPersonList = getAssignedProjectPersonList();
        List<ProjectPersonVO> lastActivityAssignedProjectPersonList = new ArrayList<ProjectPersonVO>();
        WBSItems latestRevision = null;
        ProjectSchedule latestRevisionSchedule = null;
        if(!this.isLast()){
            BusinessObjectVO lastRevisionVO = this.getLastRevision();
            latestRevision = DomUtil.toDom(lastRevisionVO);
            List<ProjectScheduleVO> projectScheduleList = latestRevision.getProjectScheduleList();
            latestRevisionSchedule = DomUtil.toDom( projectScheduleList.get(0) );
            lastActivityAssignedProjectPersonList = latestRevision.getAssignedProjectPersonList();
        }
        if(!NullUtil.isNone(activityOwnerDeleteList)){
            for(UsersVO usersVO : activityOwnerDeleteList){
                for(ProjectPersonVO projectPersonVO : assignedProjectPersonList){
                    if(projectPersonVO.getUserId().equals(usersVO.getNames())){
                        new AssignedToActivity((String)projectPersonVO.getOutData().get("rel_obid")).deleteObject();
                        assignedProjectPersonList.remove(projectPersonVO);
                        break;
                    }
                }
                for(ProjectPersonVO projectPersonVO : lastActivityAssignedProjectPersonList){
                    if(projectPersonVO.getUserId().equals(usersVO.getNames())){
                        new AssignedToActivity((String)projectPersonVO.getOutData().get("rel_obid")).deleteObject();
                        lastActivityAssignedProjectPersonList.remove(projectPersonVO);
                        break;
                    }
                }
            }
        }
        if(!NullUtil.isNone(activityOwnerUpdateList)){
            for(UsersVO usersVO : activityOwnerUpdateList){
                for(ProjectPersonVO projectPersonVO : assignedProjectPersonList){
                    if(projectPersonVO.getUserId().equals(usersVO.getNames())){
                        AssignedToActivity assignedToActivity = new AssignedToActivity((String)projectPersonVO.getOutData().get("rel_obid"));
                        assignedToActivity.getVo().setIsMainMember((String)usersVO.getOutData().get("editedMainMemberFlag"));
                        assignedToActivity.modifyObject();
                        assignedProjectPersonList.remove(projectPersonVO);
                        break;
                    }
                }
                for(ProjectPersonVO projectPersonVO : lastActivityAssignedProjectPersonList){
                    if(projectPersonVO.getUserId().equals(usersVO.getNames())){
                        AssignedToActivity assignedToActivity = new AssignedToActivity((String)projectPersonVO.getOutData().get("rel_obid"));
                        assignedToActivity.getVo().setIsMainMember((String)usersVO.getOutData().get("editedMainMemberFlag"));
                        assignedToActivity.modifyObject();
                        lastActivityAssignedProjectPersonList.remove(projectPersonVO);
                        break;
                    }
                }
            }
        }
        if(!NullUtil.isNone(activityOwnerCreateList)){
            Map<String,Object> attributes = new HashMap<String,Object>();
            attributes.put("actionUserId", "None");
            for(UsersVO usersVO : activityOwnerCreateList){
                ProjectPersonVO projectPersonVO = projectDom.findProjectPerson(usersVO.getNames());
                if(NullUtil.isNull(projectPersonVO)){
                    projectPersonVO = projectDom.addProjectMember(usersVO.getNames());
                }
                attributes.put("isMainMember", (String)usersVO.getOutData().get("editedMainMemberFlag"));
                Map<String,Object> map = new HashMap<String,Object>();
                map.put(ProjectConstants.WBS_MAP_SCHEDULE_scheduleVO, projectScheduleDom.getVo());
                addToObject(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, projectPersonVO, attributes, map);
                
                if(!isLast() && latestRevision != null && latestRevisionSchedule != null){
                    map.put(ProjectConstants.WBS_MAP_SCHEDULE_scheduleVO, latestRevisionSchedule.getVo());
                    latestRevision.addToObject(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, projectPersonVO, attributes, map);
                }
            }
        }
    }
    /**
     * Activity�� ���� Display ���� locale�� ���缭 Return��.(User Session�� Locale�̿�);
     * 
     * @return
     * @see omc.api.object.dom.BusinessObjectRoot#getCommonTitlesForDisplay()
     */
    @Override
    public String getCommonTitlesForDisplay(){
        String userLocale = Users.getUserLocale(ThreadLocalUtil.getUserId());
        return getCommonTitlesForDisplaySub(userLocale);
    }
    /**
     * Activity�� ���� Display ���� locale�� ���缭 Return��.
     * 
     * @param locale
     * @return
     * @see omc.api.object.dom.BusinessObjectRoot#getCommonTitlesForDisplay(java.lang.String)
     */
    @Override
    public String getCommonTitlesForDisplay(String locale){
        return getCommonTitlesForDisplaySub(locale);
    }
    /**
     * Activity�� ���� Display ���� locale�� ���缭 Return�ϴ� Sub Method.
     *
     * @param locale
     * @return
     */
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
        ProjectsVO projectVO = getProject();
        if(NullUtil.isNull(projectVO) || StrUtil.isEmpty(projectVO.getTitles())) return "[Activity]" + displayedTitles;
        return "[Activity]" + "[" + projectVO.getTitles() + "]" + displayedTitles;
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
    
    public Map<String, Object> getCommonInfo() {
        Map<String, Object> result = new HashMap<String, Object>();
        
        String locale = Users.getUserLocale(ThreadLocalUtil.getUserId());
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
        
        result.put("titles", displayedTitles);
        
        return result;
    }

    /**
     * ���� Validation���� Default�� Call�ϴ� Method
     * 
     * @param inboxTaskVO
     * @see omc.api.object.dom.BusinessObjectRoot#completeWBSActivity(omc.api.object.model.BusinessObjectRootVO)
     */
    @Override
    public void completeWBSActivity(BusinessObjectRootVO inboxTaskVO){
        this.completeWBSActivity(inboxTaskVO,false);
    }
    /**
     * Workflow���� Activity To Do List���� �۾��ϷḦ Call�Ҷ� ���Ǿ����� Method 
     * BusinessObjectRoot�� ���ǵǾ��� Method�� Override��.
     * 
     * @see omc.api.object.dom.BusinessObjectRoot#completeWBSActivity()
     */
    @Override
    public void completeWBSActivity(BusinessObjectRootVO inboxTaskVO,boolean skipWarning){
        super.completeWBSActivity(inboxTaskVO,skipWarning);
        
        if(!StrUtil.in(this.getStates(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED)) throw new FoundationException("[Activity Compete]WBS Activity state is invalid.Current state is '" + this.getStates() + "'");
        if(!StrUtil.in(this.getClassName(), ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY)) throw new FoundationException("[Activity Compete]Class '" +  ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY + "' can be forcely completed.");
        
        List<WBSItemsVO> completedList = new ArrayList<WBSItemsVO>();
        
        List<WBSActivityWorkflowVO> activityListForInboxList = new ArrayList<WBSActivityWorkflowVO>();
        Date actionDate = TimeServiceUtil.getDBLocalTime();
        WorkflowInboxTaskVO inboxVO = (WorkflowInboxTaskVO)inboxTaskVO;
        ProjectSchedule pjtScheduleDom = new ProjectSchedule(inboxVO.getProcessTimestamp());
        
        Map<String,WBSItemsVO> activityObjectDB = pjtScheduleDom.getWBSDB();
        
        if(pjtScheduleDom.isLast()){
            this.completeWBSActivitySub(activityObjectDB,actionDate,pjtScheduleDom.getVo(),activityListForInboxList,completedList,false);
            WBSWorkflowServiceUtil.txnBuildWBSActivityWorkflowList(activityListForInboxList);
        }else{
            ProjectScheduleVO lastScheduleVO = (ProjectScheduleVO)pjtScheduleDom.getLastRevision();
            ProjectSchedule lastScheduleDom = new ProjectSchedule(lastScheduleVO);
            //���������� Start�Ǿ�����......
            if(StrUtil.in(lastScheduleDom.getStates(),ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_STARTED)){
                WBSItemsVO latestWBSItemsVO = (WBSItemsVO)this.getLastRevision();
                WBSItems latestWBSItemsDom = new WBSItems(latestWBSItemsVO);
                activityObjectDB = lastScheduleDom.getWBSDB();
                latestWBSItemsDom.completeWBSActivitySub(activityObjectDB,actionDate,lastScheduleDom.getVo(),activityListForInboxList,completedList,false);
                WBSWorkflowServiceUtil.txnBuildWBSActivityWorkflowList(activityListForInboxList);     
                if(!this.isLast()) WBSItems.copyActivityOutputs(this,latestWBSItemsDom);
            }else{
                this.completeWBSActivitySub(activityObjectDB,actionDate,pjtScheduleDom.getVo(),activityListForInboxList,completedList,false);
                WBSWorkflowServiceUtil.txnBuildWBSActivityWorkflowList(activityListForInboxList);
                if(!this.isLast())
                {
                    //���� �������� �Ϸ� ���� �� ���¸� ������ ������.
                    WBSItemsVO latestWBSItemsVO = (WBSItemsVO)this.getLastRevision();
                    WBSItems latestWBSItemsDom = new WBSItems(latestWBSItemsVO);
                    latestWBSItemsDom.getVo().setActualEndDate(actionDate);
                    latestWBSItemsDom.modifyObject();
                    latestWBSItemsDom.changePolicyAndState(this.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED);
                    WBSItems.copyActivityOutputs(this,latestWBSItemsDom);
                }
            }
        }
        postProcessForCompleteWBSActivity(inboxTaskVO);
        if("Y".equals(this.getVo().getIsLastActivity()) && "N".equals(this.getVo().getIsReDoActivity())) processForPhaseEnd(pjtScheduleDom);
        WBSItems.createIfTriggerDataForCompleteActivity(completedList);
        
        for(WBSActivityWorkflowVO wbsActivityWorkflowVO : activityListForInboxList){
            if("Y".equals(wbsActivityWorkflowVO.getWbsActivity().getOutDataAttributeValue("autoCompleteActivity"))){
                WBSItems autoCompleteActivity = (WBSItems)wbsActivityWorkflowVO.getWbsActivity();
                autoCompleteActivity.forceCompleteWBSActivityFromWBS(pjtScheduleDom.getVo(), "Auto Complete." , true);
            }
        }
    }
    
    /**
     * To-Do�� ���� Activity�������� ���⹰�� �ֽ� Revision���� Copy��.
     *
     * @param fromActivityDom
     * @param toActivityDom
     */
    private static void copyActivityOutputs(WBSItems fromActivityDom, WBSItems toActivityDom){
        List<ProjectActivityDocumentVO> fromList = fromActivityDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES, ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENT, GlobalConstants.FLAG_TYPE_TO);
        List<ProjectActivityDocumentVO> toList = toActivityDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES, ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENT, GlobalConstants.FLAG_TYPE_TO);
        List<ProjectActivityDocumentVO> deletedList = new ArrayList<ProjectActivityDocumentVO>();
        List<ProjectActivityDocumentVO> addedList   = new ArrayList<ProjectActivityDocumentVO>();
        if(!NullUtil.isNone(fromList)){
            for(ProjectActivityDocumentVO fromVO :  fromList){
                boolean isExists = false;
                for(ProjectActivityDocumentVO toVO :  toList){
                    if(fromVO.getObid().equals(toVO.getObid())) {isExists = true;break;}
                }
                if(!isExists) addedList.add(fromVO);
            }
        }
        if(!NullUtil.isNone(toList)){
            for(ProjectActivityDocumentVO toVO :  toList){
                boolean isExists = false;
                for(ProjectActivityDocumentVO fromVO :  fromList){
                    if(fromVO.getObid().equals(toVO.getObid())) {isExists = true;break;}
                }
                if(!isExists) deletedList.add(toVO);
            }
        }
        for(ProjectActivityDocumentVO vo : deletedList){
            ActivityDeliverables  activityDeliverablesDom = new ActivityDeliverables((String)vo.getOutDataAttributeValue("rel_obid"));
            activityDeliverablesDom.deleteObject();
        }
        for(ProjectActivityDocumentVO vo : addedList){
            toActivityDom.addToObject(ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES, vo, null);
        }
    }
    /**
     * Activity Complete�� ����Ǿ���.
     *
     * @param inboxTaskVO
     */
    private void postProcessForCompleteWBSActivity(BusinessObjectRootVO inboxTaskVO){
        //postExecutionMethod�� �����ؾ� ��.
        WorkflowInboxTaskVO cinboxTaskVO = (WorkflowInboxTaskVO)inboxTaskVO;
        String scheduleObid = cinboxTaskVO.getProcessTimestamp();
        ProjectSchedule scheduleDom = new ProjectSchedule(scheduleObid);
        postProcessForCompleteWBSActivitySub(scheduleDom);
    }
    private void postProcessForCompleteWBSActivity(ProjectSchedule scheduleDom){
        //postExecutionMethod�� �����ؾ� ��.
        postProcessForCompleteWBSActivitySub(scheduleDom);
    }
    /**
     * 
     *
     * @param scheduleDom
     */
    private void postProcessForCompleteWBSActivitySub(ProjectSchedule scheduleDom){
        List<ActivityValidationResultVO> list = new ArrayList<ActivityValidationResultVO>();
        if("Y".equals(this.getVo().getIsReDoActivity())) return;
        if(shouldBePerformedMethod(this.getVo().getPostExecutionMethod())){
            Map<String,String> activityParameterMap = this.getParameterForWorkflow(scheduleDom.getVo());
            ActivityMethodParameterVO activityMethodParameterVO = getMethodInfo(activityParameterMap,this.getVo().getPostExecutionMethod());
            if(NullUtil.isNull(activityMethodParameterVO)) throw new FoundationException("Activity System Configuration Error.Please contact System Administrator.");
            list = executeExecutionMethod(activityMethodParameterVO);
        }
        BusinessObjectRoot.ariseError(list, true);
    }
    private ActivityMethodParameterVO getMethodInfo(Map<String,String> activityParameterMap, String programAndMethod){
        int lastIndex          = programAndMethod.lastIndexOf(".");
        String program         = programAndMethod.substring(0,lastIndex);
        String method          = programAndMethod.substring(lastIndex+1);
        String phaseCode = activityParameterMap.get(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_phaseCode);
        String phaseTitles = activityParameterMap.get(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_phaseTitles);
        String phaseCodeSystem = activityParameterMap.get(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_phaseCodeSystem);
        String phaseTitlesSystem = activityParameterMap.get(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_phaseTitlesSystem);
        Projects projectDom = new Projects(activityParameterMap.get(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_projectObid));
        ProjectSchedule projectScheduleDom = new ProjectSchedule(activityParameterMap.get(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_scheduleObid));
        Object objct = WBSUtil.getActivityRunClass(program,projectDom.getVo(),projectScheduleDom.getVo(),this.getVo(), phaseCode,phaseTitles,phaseCodeSystem,phaseTitlesSystem);
        Method[] methodArray = objct.getClass().getDeclaredMethods();
        for(int i = 0; i < methodArray.length; i++){
            if(methodArray[i].getName().equals(method))
            {
                ActivityMethodParameterVO activityMethodParameterVO = new ActivityMethodParameterVO(methodArray[i],objct,program,method);
                return activityMethodParameterVO;
            }
        }
        return null;
    }
    
    private List<ActivityValidationResultVO> executeExecutionMethod(ActivityMethodParameterVO activityMethodParameterVO){
        List<ActivityValidationResultVO> list = new ArrayList<ActivityValidationResultVO>();
        try {
            activityMethodParameterVO.getMethod().invoke(activityMethodParameterVO.getObjct());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            list.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.systemError,"Mehod Resolve2","System Error Occur(Program:" + activityMethodParameterVO.getProgram() + "," + "Method:" + activityMethodParameterVO.getMethodName() + ")." + "Error Detail:" + e.getMessage(), "Please contact System Administrator."));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            list.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.systemError,"Mehod Resolve3","System Error Occur(Program:" + activityMethodParameterVO.getProgram() + "," + "Method:" + activityMethodParameterVO.getMethodName() + ")." + "Error Detail:" + e.getMessage(), "Please contact System Administrator."));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            list.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.systemError,"Mehod Resolve4","System Error Occur(Program:" + activityMethodParameterVO.getProgram() + "," + "Method:" + activityMethodParameterVO.getMethodName() + ")." + "Error Detail:" + e.getMessage(), "Please contact System Administrator."));
        }
        return list;
    }
    @SuppressWarnings("unchecked")
    private List<ActivityValidationResultVO> executeValidateMethod(ActivityMethodParameterVO activityMethodParameterVO){
        List<ActivityValidationResultVO> list = new ArrayList<ActivityValidationResultVO>();
        Object obj = activityMethodParameterVO.getMethod().getReturnType();
        if(!obj.equals(List.class)){
            list.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.systemError,"Mehod Resolve1","[Foundation] Method(" + activityMethodParameterVO.getProgram() + ") return type is not List", "Please contact System Administrator."));
        }else{
            try {
                list = (List<ActivityValidationResultVO>)activityMethodParameterVO.getMethod().invoke(activityMethodParameterVO.getObjct());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                list.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.systemError,"Mehod Resolve2","System Error Occur(Program:" + activityMethodParameterVO.getProgram() + "," + "Method:" + activityMethodParameterVO.getMethodName() + ")." + "Error Detail:" + e.getMessage(), "Please contact System Administrator."));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                list.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.systemError,"Mehod Resolve3","System Error Occur(Program:" + activityMethodParameterVO.getProgram() + "," + "Method:" + activityMethodParameterVO.getMethodName() + ")." + "Error Detail:" + e.getMessage(), "Please contact System Administrator."));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                list.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.systemError,"Mehod Resolve4","System Error Occur(Program:" + activityMethodParameterVO.getProgram() + "," + "Method:" + activityMethodParameterVO.getMethodName() + ")." + "Error Detail:" + e.getMessage(), "Please contact System Administrator."));
            }
        }
        return list;
    }
    
    
    /**
     * Complete�� �������� Validation��.
     *
     * @param pjtScheduleVO
     */
    private void validateForceComplete(ProjectScheduleVO pjtScheduleVO){
        if(!ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED.equals(this.getStates())) throw new FoundationException("[Activity Compete]Only '" +  ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED + "' state can be forcely completed.");
        if(!StrUtil.in(this.getClassName(), ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY)) throw new FoundationException("[Activity Compete]Class '" +  ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY + "' can be forcely completed.");
        if(!this.isLeaf(pjtScheduleVO)) throw new FoundationException("[Activity Compete]Only Leaf Activity can be forcely completed.");
    }
    /**
     * Leaf Level���� Check��.
     *
     * @param pjtScheduleVO
     * @return
     */
    public boolean isLeaf(ProjectScheduleVO pjtScheduleVO){
        StringBuffer selectPattern = new StringBuffer (); 
        StringBuffer wherePattern  = new StringBuffer (); 
        StringBuffer paramPattern  = new StringBuffer (); 
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, pjtScheduleVO.getObid() );
        StringUtil.constructWherePattern(wherePattern, paramPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid",GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        List<BusinessRelationObjectVO> relVOList = this.getRelationship(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), paramPattern.toString());
        if(NullUtil.isNone(relVOList)) return true;
        return false;
    }
    /**
     * ���� �Ϸ�: To-Do�� ���� �ִ� To-Do�� ������ ���ν�Ŵ. ������ Activity�� Start(To-Do�� �����Ǿ���)�Ǿ���.
     *
     * @param pjtScheduleVO
     * @param comments
     * @param bySystem
     */
    public void forceCompleteWBSActivityFromWBS(ProjectScheduleVO pjtScheduleVO, String comments, boolean bySystem){
        this.validateForceComplete(pjtScheduleVO);
        List<WBSItemsVO> completedList = new ArrayList<WBSItemsVO>();
        List<ApprovalVO> submitApprovalList = new ArrayList<ApprovalVO>();
        List<ApprovalVO> approvalList = WBSWorkflowServiceUtil.retrieveWBSActivityWorkflow(this);
        for(ApprovalVO approvalVO : approvalList){
            if(approvalVO.canBeSubmittable()){
                UsersVO userVO = null;
                //Q20181106_03967
                if (GlobalConstants.NO_USER_ID.equals(ThreadLocalUtil.getUserId()) ) {
                    userVO = new UsersVO();
                    userVO.setTitles("System(noid)");
                } else {
                    userVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_USERS, ThreadLocalUtil.getUserId());
                }
                approvalVO.setComments("Force Approved By '" + userVO.getTitles() + "'. Comments : " +  comments);
                approvalVO.setApprovalStatus(ApplicationBizConstants.APPROVAL_STATUS_APPROVE);
                submitApprovalList.add(approvalVO);
            }
        }
        if(!NullUtil.isNone(submitApprovalList)){
            WBSWorkflowServiceUtil.txnSubmitApproval(this,submitApprovalList);
        }else{
            //������ ���� �Ϸ�ó����.
            List<WBSActivityWorkflowVO> activityListForInboxList = new ArrayList<WBSActivityWorkflowVO>();
            Date actionDate = TimeServiceUtil.getDBLocalTime();
            ProjectSchedule pjtScheduleDom = new ProjectSchedule(pjtScheduleVO);
            Map<String,WBSItemsVO> activityObjectDB = pjtScheduleDom.getWBSDB();
            completeWBSActivitySub(activityObjectDB,actionDate,pjtScheduleDom.getVo(),activityListForInboxList,completedList,false);
            if(!NullUtil.isNone(activityListForInboxList)){
                WBSWorkflowServiceUtil.txnBuildWBSActivityWorkflowList(activityListForInboxList);
            }
        }
        if("Y".equals(this.getVo().getIsLastActivity()) && "N".equals(this.getVo().getIsReDoActivity())) processForPhaseEnd(new ProjectSchedule(pjtScheduleVO));
        WBSItems.createIfTriggerDataForCompleteActivity(completedList);
    }
    /**
     * WBS���� �ڱ� �ڽſ� �Ҵ�Ǿ��� Activity�� ���ؼ� �Ϸ��ų�� ���Ǿ����� Method
     * �⺻ Logic�� canBeCompleteMyToDoActivity�� ������.����� ���� ����Ǿ����.
     *
     * @param pjtScheduleVO
     * @param comments
     * @param bySystem
     */
    public void completeMyToDoActivity(ProjectScheduleVO pjtScheduleVO, String comments, boolean bySystem){
        String currentUserId = ThreadLocalUtil.getUserId();
        List<ApprovalVO> submitApprovalList = new ArrayList<ApprovalVO>();
        List<ApprovalVO> approvalList = WBSWorkflowServiceUtil.retrieveWBSActivityWorkflow(this);
        for(ApprovalVO approvalVO : approvalList){
            if(currentUserId.equals(approvalVO.getTaskOwner())){
                if(approvalVO.canBeSubmittable()){
                    approvalVO.setComments(comments);
                    approvalVO.setApprovalStatus(ApplicationBizConstants.APPROVAL_STATUS_APPROVE);
                    submitApprovalList.add(approvalVO);
                }
            }
        }
        if(!NullUtil.isNone(submitApprovalList)){
            WBSWorkflowServiceUtil.txnSubmitApproval(this,submitApprovalList);
        }else{
            throw new FoundationException("There is no To-Do List to complete.");
        }
    }
    /**
     * WBS���� �ڱ� �ڽſ� �Ҵ�Ǿ��� Activity�� ���� Inbox�� Complete�� �� �ִ����� Check��.
     * �⺻ Logic�� completeMyToDoActivity�� ������.����� ���� ����Ǿ����.
     *
     * @param pjtScheduleVO
     * @return
     */
    public boolean canBeCompleteMyToDoActivity(ProjectScheduleVO pjtScheduleVO){
        String currentUserId = ThreadLocalUtil.getUserId();
        List<ApprovalVO> approvalList = WBSWorkflowServiceUtil.retrieveWBSActivityWorkflow(this);
        for(ApprovalVO approvalVO : approvalList){
            if(currentUserId.equals(approvalVO.getTaskOwner())){
                if(approvalVO.canBeSubmittable()){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Longin User�� Inbox Object��  Return��.
     *
     * @param pjtScheduleVO
     * @return
     */
    public WorkflowInboxTaskVO getInboxTaskObjectForMyToDoActivity(ProjectScheduleVO pjtScheduleVO){
        String currentUserId = ThreadLocalUtil.getUserId();
        List<ApprovalVO> approvalList = WBSWorkflowServiceUtil.retrieveWBSActivityWorkflow(this);
        for(ApprovalVO approvalVO : approvalList){
            if(currentUserId.equals(approvalVO.getTaskOwner())){
                if(approvalVO.canBeSubmittable()){
                    WorkflowInboxTask workflowInboxTaskDom = new WorkflowInboxTask(approvalVO.getWorkflowInboxTaskObid());
                    Map<String,String> parameterMap =  this.getParameterForWorkflow(pjtScheduleVO);
                    HashMap<String, Object> outData = this.getOutData();
                    for( Map.Entry<String, String> element : parameterMap.entrySet() ){
                        outData.put(element.getKey(), element.getValue());
                    }
                    return workflowInboxTaskDom.getVo();
                }
            }
        }
        return null;
    }
    
    /**
     * WBS���� ���������� Activity�� Start�� �� ���Ǿ����� Method(Scheduled �����϶� �� ����, Startable�ΰ�쿡�� ������.)
     *
     * @param pjtScheduleVO
     */
    public void forceStartActivity(Map<String,WBSItemsVO> activityObjectDB,ProjectScheduleVO pjtScheduleVO){
        Date actionDate = TimeServiceUtil.getDBLocalTime();
        List<WBSActivityWorkflowVO> activityListForInboxList = new ArrayList<WBSActivityWorkflowVO>();
        List<WBSItemsVO> completedList = new ArrayList<WBSItemsVO>();
        this.startActivityForScheduleStart(activityObjectDB,actionDate, pjtScheduleVO, activityListForInboxList, completedList, new HashMap<String,Object>(),1);
        WBSWorkflowServiceUtil.txnBuildWBSActivityWorkflowList(activityListForInboxList);
        WBSItems.createIfTriggerDataForCompleteActivity(completedList);
    }
    
    
    /**
     * Activity�� Complete�ϴ� Core Method
     *
     * @param actionDate
     */
    private void completeWBSActivitySubCore(Map<String,WBSItemsVO> activityObjectDB, Date actionDate,List<WBSItemsVO> completedList){
        WBSItems copiedDom = new WBSItems(this.getObid());
        this.getVo().setActualEndDate(actionDate);
        copiedDom.getVo().setActualEndDate(actionDate);
        activityObjectDB.get(this.getObid()).setActualEndDate(actionDate);
        
        copiedDom.modifyObject();
        copiedDom.promote();
        this.getVo().setStates(copiedDom.getStates());
        activityObjectDB.get(this.getObid()).setStates(copiedDom.getStates());
        
        this.getVo().setOutDataAttributeValue(ProjectConstants.WBS_IF_COMPLETE_MAP_ATTR, ProjectConstants.WBS_IF_COMPLETE_VALUE_COMPLETE);
        completedList.add(this.getVo());
        if(!this.isLast()){
            WBSItemsVO nextObjVO = this.getNextRevision();
            nextObjVO.setActualEndDate(actionDate);
            WBSItems nextObjDom = new WBSItems(nextObjVO);
            nextObjDom.modifyObject();
            nextObjDom.getVo().setOutDataAttributeValue(ProjectConstants.WBS_IF_COMPLETE_MAP_ATTR, ProjectConstants.WBS_IF_COMPLETE_VALUE_COMPLETE);
            nextObjDom.changePolicyAndState(nextObjDom.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED);
            completedList.add(nextObjDom.getVo());
        }
    } 
    
    /**
     * Activity�� Start�ϴ� Core Method
     *
     * @param actionDate
     */
    private void startWBSActivitySubCore(Map<String,WBSItemsVO> activityObjectDB,Date actionDate,List<WBSItemsVO> completedList){
        WBSItems copiedDom = new WBSItems(this.getObid());
        if(NullUtil.isNull(this.getVo().getActualStartDate())){
            this.getVo().setActualStartDate(actionDate);
            copiedDom.getVo().setActualStartDate(actionDate);
            activityObjectDB.get(this.getObid()).setActualStartDate(actionDate);
            copiedDom.modifyObject();
        }
        copiedDom.promote();
        
        this.getVo().setStates(copiedDom.getStates());
        activityObjectDB.get(this.getObid()).setStates(copiedDom.getStates());
        
        this.getVo().setOutDataAttributeValue(ProjectConstants.WBS_IF_COMPLETE_MAP_ATTR, ProjectConstants.WBS_IF_COMPLETE_VALUE_START);
        completedList.add(this.getVo());
        if(!this.isLast()){
            WBSItemsVO nextObjVO = this.getNextRevision();
            nextObjVO.setActualStartDate(this.getVo().getActualStartDate());
            WBSItems nextObjDom = new WBSItems(nextObjVO);
            nextObjDom.getVo().setOutDataAttributeValue(ProjectConstants.WBS_IF_COMPLETE_MAP_ATTR, ProjectConstants.WBS_IF_COMPLETE_VALUE_START);
            nextObjDom.modifyObject();
            completedList.add(nextObjDom.getVo());
        }
    }
    
    /**
     * Parent�� Complete��(���ο��� ���� Check��)
     *
     * @param actionDate
     * @param sheduleVO
     * @param activityListForInboxList
     */
    private void completeParentActivity(Map<String,WBSItemsVO> activityObjectDB,Date actionDate,ProjectScheduleVO sheduleVO,List<WBSActivityWorkflowVO> activityListForInboxList, List<WBSItemsVO> completedList){
        WBSItemsVO wbsParentItemsVO = (activityObjectDB.get(this.getObid())).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_PARENT);
        if(!NullUtil.isNull(wbsParentItemsVO)){
            WBSItems wbsParentItemsDom   = new WBSItems(wbsParentItemsVO);
            if(wbsParentItemsDom.isChiledAllCompleted(activityObjectDB, sheduleVO)){
                wbsParentItemsDom.completeWBSActivitySub(activityObjectDB,actionDate,sheduleVO,activityListForInboxList,completedList,true);
            }
        }        
    }
    
    /**
     * ���� Activity�� Complete�ϰ� ���� Item�� Start�ϴ� Method
     *
     * @param actionDate
     * @param sheduleVO
     * @param activityListForInboxList
     * @param isParentCompleteCall
     */
    private void completeWBSActivitySub(Map<String,WBSItemsVO> activityObjectDB,Date actionDate,ProjectScheduleVO sheduleVO,List<WBSActivityWorkflowVO> activityListForInboxList,List<WBSItemsVO> completedList, boolean isParentCompleteCall){
        if(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED.equals(this.getStates())) completeWBSActivitySubCore(activityObjectDB,actionDate,completedList);
        this.completeParentActivity(activityObjectDB, actionDate,sheduleVO,activityListForInboxList,completedList);
        if(!isParentCompleteCall) this.startChildActivityStart(activityObjectDB,sheduleVO,actionDate,activityListForInboxList,completedList,1);
    }
    /**
     * Parent Complete�� ���ؼ� Skip�� �ؾ��ϴ����� Check��.
     *
     * @return
     */
    private boolean isSkipForParentComplete(){
        if(StrUtil.in(this.getStates(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED,ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED)) return true; 
        return false;
    }
    /**
     * ���� Item��ü�� Complete�Ǿ������� Check��.
     *
     * @param sheduleVO
     * @return
     */
    private boolean isChiledAllCompleted(ProjectScheduleVO sheduleVO){
        List<WBSItemsVO> childItemList = this.getChildWBSItemList(sheduleVO,1);
        for(WBSItemsVO childVO : childItemList){
            WBSItems childDom = new WBSItems(childVO);
            if(!childDom.isSkipForParentComplete()) return false;
        }
        return true;
    }
    private boolean isChiledAllCompleted(Map<String,WBSItemsVO> activityObjectDB,ProjectScheduleVO sheduleVO){
        //List<WBSItemsVO> childItemList = this.getChildWBSItemList(sheduleVO,1);
        List<WBSItemsVO> childItemList = (activityObjectDB.get(this.getObid())).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_CHILD_LIST);
        for(WBSItemsVO childVO : childItemList){
            WBSItems childDom = new WBSItems(childVO);
            if(!childDom.isSkipForParentComplete()) return false;
        }
//        if(1==1) throw new FoundationException("dd");
        return true;
    }
    
    
    
    /**
     * ���� ���� ��û�� Call�Ǿ����� Method
     *
     * @param map
     */
    public final void startApprovalProcess(Map<String,Object> map){
        validateForStartApprovalProcess(map);
        preProcessForStartApprovalProcess(map);
        this.promote();
        postProcessForStartApprovalProcess(map);
    }
    private void validateForStartApprovalProcess(Map<String,Object> map){;}
    private void preProcessForStartApprovalProcess(Map<String,Object> map){;}
    private void postProcessForStartApprovalProcess(Map<String,Object> map){;}
    /**
     * ���� ���� Ȯ���� Call�Ǿ����� Method
     *
     * @param map
     */
    public void completeProjectWBSSchedule(Map<String,Object> map){
        validateForProjectWBSScheduleComplete(map);
        preProcessForProjectWBSScheduleComplete(map);
        if("Y".equals(this.getVo().getIsSkipped())){
            this.changePolicyAndState(this.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED);
        }else{
            this.promote();
        }
        postProcessForProjectWBSScheduleComplete(map);
    }
    /**
     * ���� ���� Ȯ���� ���Ǿ����� Validaiton
     *
     * @param map
     */
    private void validateForProjectWBSScheduleComplete(Map<String,Object> map){
        if(!this.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_APPROVALPROCESSING)) throw new FoundationException("Only '" + ApplicationSchemaConstants.STATE_WBS_ACTIVITY_APPROVALPROCESSING + "' can be Approval Completed!");
    }
    private void preProcessForProjectWBSScheduleComplete(Map<String,Object> map){;}
    private void postProcessForProjectWBSScheduleComplete(Map<String,Object> map){;}
    /**
     * Schedule Start�� ���Ǿ����� Start Activity Method
     *
     * @param actionDate
     * @param sheduleVO
     * @param activityListForInboxList
     * @param map
     * @param calledDepth
     */
    public final void startActivityForScheduleStart(Map<String,WBSItemsVO> activityObjectDB, Date actionDate,ProjectScheduleVO sheduleVO, List<WBSActivityWorkflowVO> activityListForInboxList, List<WBSItemsVO> completedList,Map<String,Object> map, int calledDepth){
        this.startActivity(activityObjectDB,actionDate,sheduleVO,activityListForInboxList,completedList,map,calledDepth);
    }
    /**
     * Activity�� Start��.
     *
     * @param actionDate
     * @param sheduleVO
     * @param activityListForInboxList
     * @param map
     * @param calledDepth
     */
    private final void startActivity(Map<String,WBSItemsVO> activityObjectDB,Date actionDate,ProjectScheduleVO sheduleVO, List<WBSActivityWorkflowVO> activityListForInboxList, List<WBSItemsVO> completedList, Map<String,Object> map, int calledDepth){
        //List<WBSActivitiesVO> previousWBSActivityList = getPreviousActivityList(sheduleVO);
        List<DependencyVO> previousWBSActivityList = (activityObjectDB.get(this.getObid())).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_PREVIOUS_LIST);
        validateForStartActivity(activityObjectDB,sheduleVO,activityListForInboxList,previousWBSActivityList,completedList,map);
        preProcessForStartActivity(activityObjectDB,sheduleVO,activityListForInboxList,previousWBSActivityList,completedList,map);
        startActivityCore(activityObjectDB,actionDate,sheduleVO,activityListForInboxList,previousWBSActivityList,completedList,map,calledDepth);
        postProcessForStartActivity(activityObjectDB,sheduleVO,activityListForInboxList,previousWBSActivityList,completedList,map);
    }
    
    /**
     * Schedule ������ ���� �ش� Activity �� Start ��Ű�� 
     * Skip �� �Ϸ�� ���� �ɸ��� �ش� ���� �״�� �ΰ� �ļ� Activity�� ���� Start�� Call�Ѵ�.
     *
     * @param actionDate
     * @param sheduleVO
     * @param activityListForInboxList
     * @param previousWBSActivityList
     * @param map
     * @param calledDepth
     */
    private void startActivityCore(Map<String,WBSItemsVO> activityObjectDB,Date actionDate,ProjectScheduleVO sheduleVO, List<WBSActivityWorkflowVO> activityListForInboxList, List<DependencyVO> previousWBSActivityList, List<WBSItemsVO> completedList, Map<String,Object> map,int calledDepth){
        String states = this.getStates();
        if(this.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED)){
            this.startWBSActivitySubCore(activityObjectDB,actionDate,completedList);  
            List<ProjectPersonVO> projectPersonList = this.getAssignedProjectPersonList();
            if(!NullUtil.isNone(projectPersonList)){
                List<WBSActivityInboxTaskVO> wbsActivityInboxTaskVOList = new ArrayList<WBSActivityInboxTaskVO>();
                for(ProjectPersonVO projectPersonVO : projectPersonList){
                    WBSActivityInboxTaskVO vo = new WBSActivityInboxTaskVO();
                    vo.setTaskOwner(projectPersonVO.getUserId());
                    wbsActivityInboxTaskVOList.add(vo);
                }
                WBSActivityWorkflowVO wbsActivityWorkflowVO = new WBSActivityWorkflowVO(this, projectPersonList, wbsActivityInboxTaskVOList, "Standard", this.getLifeCycle(), states, this.getVo().getActivityUrl());
                wbsActivityWorkflowVO.setProcessTimestamp(sheduleVO.getObid());
                activityListForInboxList.add(wbsActivityWorkflowVO);
            }
            this.startParentActivityStart(activityObjectDB,sheduleVO,actionDate,completedList);
            this.startChildActivityStart(activityObjectDB,sheduleVO,actionDate,activityListForInboxList,completedList,calledDepth);
            if("Y".equals(this.getVo().getIsFirstActivity()) && "N".equals(this.getVo().getIsReDoActivity())) this.processForPhaseStart(new ProjectSchedule(sheduleVO));
        }else if(this.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED) || this.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED)){
            this.startChildActivityStart(activityObjectDB,sheduleVO,actionDate,activityListForInboxList,completedList,calledDepth);
        }
    }
    /**
     * �ļ� Activity�� ã�Ƽ� Start�� �� �ִ� ������ �Ǹ� Recursive�ϰ� Start�Ѵ�.
     *
     * @param sheduleVO
     * @param actionDate
     * @param activityListForInboxList
     * @param calledDepth
     */
    private void startChildActivityStart(Map<String,WBSItemsVO> activityObjectDB,ProjectScheduleVO sheduleVO,Date actionDate,List<WBSActivityWorkflowVO> activityListForInboxList,List<WBSItemsVO> completedList, int calledDepth){
        List<DependencyVO> nextWBSItemList = (activityObjectDB.get(this.getObid())).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_NEXT_LIST);
        for(DependencyVO wbsItemsVO : nextWBSItemList){
            WBSItems wbsItemsDom = new WBSItems(wbsItemsVO.getDependentVO());
            if("Y".equals(activityObjectDB.get(wbsItemsDom.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_START_MAP_isStarted))){ continue;}
            List<DependencyVO> preiviousWBSItemList = (activityObjectDB.get(wbsItemsDom.getObid())).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_PREVIOUS_LIST);
            if(wbsItemsDom.canBeStartable(activityObjectDB,sheduleVO,preiviousWBSItemList)){
                wbsItemsDom.startActivity(activityObjectDB,actionDate,sheduleVO,activityListForInboxList,completedList, new HashMap<String,Object>(),calledDepth+1);
                activityObjectDB.get(wbsItemsDom.getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_START_MAP_isStarted, "Y");
            }
        }        
    }
    /**
     * Parent�� Start��.
     *
     * @param sheduleVO
     * @param actionDate
     */
    private void startParentActivityStart(Map<String,WBSItemsVO> activityObjectDB,ProjectScheduleVO sheduleVO,Date actionDate,List<WBSItemsVO> completedList){
        List<WBSItemsVO> tempNotStartedParentList = this.getNotStartedParentWBSItemList(sheduleVO,ProjectConstants.WBS_PARENT_START_LEVEL);
        List<WBSItemsVO> notStartedParentList = new ArrayList<WBSItemsVO>();
        for(WBSItemsVO vo : tempNotStartedParentList){
            notStartedParentList.add(activityObjectDB.get(vo.getObid()));
        }
        for(WBSItemsVO parentItemsVO : notStartedParentList){
            WBSItems parentItemsDom = new WBSItems(parentItemsVO);
            if(parentItemsDom.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED)){
                parentItemsDom.startWBSActivitySubCore(activityObjectDB,actionDate,completedList);
            }
            parentItemsDom.startParentActivityStart(activityObjectDB,sheduleVO,actionDate,completedList);
        }
    }
    private void validateForStartActivity(Map<String,WBSItemsVO> activityObjectDB,ProjectScheduleVO sheduleVO,List<WBSActivityWorkflowVO> activityListForInboxList,List<DependencyVO> previousWBSActivityList,List<WBSItemsVO> completedList, Map<String,Object> map){
        if(!this.canBeStartable(activityObjectDB,sheduleVO,previousWBSActivityList)) throw new FoundationException("'" + this.getCommonTitlesForDisplay() + "' is not startable. Please check previous Activity." );
        validateForStartActivitySub(sheduleVO);
    }
    private void validateForStartActivitySub(ProjectScheduleVO sheduleVO){
        List<ActivityValidationResultVO> list = new ArrayList<ActivityValidationResultVO>();
        if("Y".equals(this.getVo().getIsReDoActivity())) return;
        if(shouldBePerformedMethod(this.getVo().getStartValidationMethod())){
            Map<String,String> activityParameterMap = this.getParameterForWorkflow(sheduleVO);
            ActivityMethodParameterVO activityMethodParameterVO = getMethodInfo(activityParameterMap,this.getVo().getStartValidationMethod());
            if(NullUtil.isNull(activityMethodParameterVO)) throw new FoundationException("Activity System Configuration Error.Please contact System Administrator.");
            list = executeValidateMethod(activityMethodParameterVO);
        }
        BusinessObjectRoot.ariseError(list, true);        
    }
    
    private void preProcessForStartActivity(Map<String,WBSItemsVO> activityObjectDB,ProjectScheduleVO sheduleVO,List<WBSActivityWorkflowVO> activityListForInboxList,List<DependencyVO> previousWBSActivityList,List<WBSItemsVO> completedList, Map<String,Object> map){;}
    /**
     * Start �� ����Ǿ����� Method
     *
     * @param sheduleVO
     * @param activityListForInboxList
     * @param previousWBSActivityList
     * @param map
     */
    private void postProcessForStartActivity(Map<String,WBSItemsVO> activityObjectDB,ProjectScheduleVO sheduleVO,List<WBSActivityWorkflowVO> activityListForInboxList,List<DependencyVO> previousWBSActivityList,List<WBSItemsVO> completedList, Map<String,Object> map){
        this.postProcessForStartActivitySub(sheduleVO);
    }
    
    /**
     * 
     *
     * @param sheduleVO
     */
    private void postProcessForStartActivitySub(ProjectScheduleVO sheduleVO){
        List<ActivityValidationResultVO> list = new ArrayList<ActivityValidationResultVO>();
        if("Y".equals(this.getVo().getIsReDoActivity())) return;
        if( !this.getVo().getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED) || "Y".equals(this.getVo().getIsSkipped())) return;
        if(shouldBePerformedMethod(this.getVo().getStartExecutionMethod())){
            Map<String,String> activityParameterMap = this.getParameterForWorkflow(sheduleVO);
            ActivityMethodParameterVO activityMethodParameterVO = getMethodInfo(activityParameterMap,this.getVo().getStartExecutionMethod());
            if(NullUtil.isNull(activityMethodParameterVO)) throw new FoundationException("Activity System Configuration Error.Please contact System Administrator.");
            list = executeExecutionMethod(activityMethodParameterVO);
            BusinessObjectRoot.ariseError(list, true);
            
            // Start Execution Method �� �ѹ��� ����.
            this.getVo().setStartExecutionMethod(ProjectConstants.NONE);
            Set<String> attributes = new HashSet<String>();
            attributes.add("startExecutionMethod");
            ObjectRoot.modifyObjectSetBatch(this.getVo(), attributes);
            if(!this.isLast()){
                Set<String> attributesLast = new HashSet<String>();
                attributesLast.add("startExecutionMethod");
                WBSItemsVO nextVO = (WBSItemsVO)this.getNextRevision();
                nextVO.setStartExecutionMethod(ProjectConstants.NONE);
                ObjectRoot.modifyObjectSetBatch(nextVO, attributesLast);
            }
        }
    }
    public String getProjectRoleDisplayNameAsLocale(){
        
        String displayName = "";
        List<ProjectRoleVO> projectRole = getAssignedProjectRoleList();
        for(ProjectRoleVO projectRoleVO : projectRole){
            if(displayName.length() > 1){displayName+=",";}
            displayName += new ProjectRole(projectRoleVO).getDisplayNameAsLocale();
        }
        return displayName;
    }
    
    public List<ProjectRoleVO> getAssignedProjectRoleList(){
        List<ProjectDefinedRoleVO> ProjectDefinedRoleVOList = getAssignedProjectDefinedRoleList();
        List<String> roleCodeNameList = new ArrayList<String>();
        for(ProjectDefinedRoleVO projectDefinedRoleVO : ProjectDefinedRoleVOList){
            roleCodeNameList.add(projectDefinedRoleVO.getRoleCode());
        }
        
        if(NullUtil.isNone(roleCodeNameList)){ return new ArrayList<ProjectRoleVO>();}
        
        return ProjectRole.getProjectRoleVOList(roleCodeNameList);
    }
    /**
     * Activity�� ���� Onwer����Ʈ�� Return��
     *
     * @return
     */
    public List<UsersVO> getActivityOwnerUserList(){
        List<ProjectPersonVO> list = this.getAssignedProjectPersonList();
        List<UsersVO> userList = ObjectRoot.getRelatedObjectSet(list, ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOPROJECTMEMBER, ApplicationSchemaConstants.BIZCLASS_USERS, GlobalConstants.FLAG_TYPE_TO);
        for(UsersVO vo : userList){
            String roleList = "";
            for(ProjectPersonVO subVO : list){
                if(vo.getNames().equals(subVO.getUserId())){
                    roleList = (String)subVO.getOutData().get(ProjectConstants.PROJECT_OUTDATA_ROLE_LIST);
                }
            }
            vo.getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_LIST, roleList);
        }
        return userList;
    }
    /**
     * Activity�� �Ҵ�Ǿ��� ProjectPerson�� Return��.
     *
     * @return
     */
    public List<ProjectPersonVO> getAssignedProjectPersonList(){
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, ApplicationSchemaConstants.BIZCLASS_PROJECTPERSON, GlobalConstants.FLAG_TYPE_TO,wherePatternBuf.toString(), paramPatternBuf.toString(),1);
    }
    
    public ProjectPersonVO getAssignedProjectPerson(String userId){
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[userId]", GlobalConstants.OQL_OPERATOR_EQUAL, userId );
        List<ProjectPersonVO> list = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, ApplicationSchemaConstants.BIZCLASS_PROJECTPERSON, GlobalConstants.FLAG_TYPE_TO,wherePatternBuf.toString(), paramPatternBuf.toString(),1);
        if(NullUtil.isNone(list)) return null;
        if(list.size() > 1) throw new FoundationException("Allocate member is incorrect for" + this.getCommonTitlesForDisplay());
        return  list.get(0);
    }
    
    /**
     * Assigned�Ǿ��� Mmeber�� User Object��  Return��. 
     * outData �� main member ���ΰ� �߰� �Ǿ��� ����.
     * @return
     */
    public List<UsersVO> getAssignedProjectUserList(){
        
        List<ProjectPersonVO> assignedProjectPersonList = getAssignedProjectPersonList();
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        List<UsersVO> assignedUserList = ObjectRoot.getRelatedObjectSet(getAssignedProjectPersonList(), ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOPROJECTMEMBER, ApplicationSchemaConstants.BIZCLASS_USERS, 
                GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString());
        for(UsersVO assignrdUsersVO : assignedUserList){
            for(ProjectPersonVO projectPersonVO : assignedProjectPersonList){
                if(assignrdUsersVO.getNames().equals(projectPersonVO.getUserId())){
                    assignrdUsersVO.getOutData().put("isMainMember", projectPersonVO.getOutData().get("rel_isMainMember"));
                }
            }
        }
       return assignedUserList;
    }
    
    /**
     * Activity List�� �Ҵ�Ǿ��� ProjectPerson List�� Return��.
     *
     * @param wbsItemsVOList
     * @return
     */
    public static List<ProjectPersonVO> getAssignedProjectPersonList(List<WBSItemsVO> wbsItemsVOList){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        return ObjectRoot.getRelatedObjectSet(wbsItemsVOList, ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, ApplicationSchemaConstants.BIZCLASS_PROJECTPERSON, 
                GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString());
    }
    /**
     * Assign�Ǿ��� Member�� Return��.
     *
     * @return
     */
    public List<ProjectMembersVO> getAssignedProjectMemberList(){
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, ApplicationSchemaConstants.BIZCLASS_PROJECTMEMBERS, GlobalConstants.FLAG_TYPE_TO, wherePatternBuf.toString(), paramPatternBuf.toString(), 1);
    }
    /**
     * Activity�� �Ҵ�Ǿ��� Role����Ʈ�� Return��.
     *
     * @return
     */
    public List<ProjectDefinedRoleVO> getAssignedProjectDefinedRoleList(){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, ApplicationSchemaConstants.BIZCLASS_PROJECTDEFINEDROLE, 
                GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(), 
                false, true, 0, 1);
    }
    /**
     * Activity�� �Ҵ�Ǿ��� Role����Ʈ�� Return��.
     *
     * @param list ã���� �ϴ� ����Ʈ
     * @return
     */
    public static List<ProjectDefinedRoleVO> getAssignedProjectDefinedRoleList(List<WBSItemsVO> list){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        return ObjectRoot.getRelatedObjectSet(list, ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, ApplicationSchemaConstants.BIZCLASS_PROJECTDEFINEDROLE, 
                GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString());
    }
    /**
     * Depenency Relation(�����۾�)�� Return��.
     *
     * @param projectScheduleObid
     * @return
     */
    public List<WBSDependencyVO> getWBSDependencyList(String projectScheduleObid){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, projectScheduleObid );

        String locale = ThreadLocalUtil.getLocale();
        if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
            StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_WBSITEMS+"].activityNameKor activityName");
        }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
            StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_WBSITEMS+"].activityNameChi activityName");
        }else{
            StringUtil.constructSelectPattern(selectPatternBuf, "this.toObid.self["+ApplicationSchemaConstants.BIZCLASS_WBSITEMS+"].activityNameEng activityName");
        }
        return getRelationship(ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY, 
                ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES, 
                GlobalConstants.FLAG_TYPE_TO,
                selectPatternBuf.toString(), 
                wherePatternBuf.toString(), 
                paramPatternBuf.toString());
    }
    
    /**
     * Activity�� Start�������� Return��.(this�� previousWBSActivity�� outData�� Dependency Type�� �־�� ��.)
     *
     * @param sheduleVO
     * @param previousWBSActivity
     * @return
     */
    
    public boolean canBeStartable_ori(ProjectScheduleVO sheduleVO,List<WBSActivitiesVO> previousWBSActivityList){
        boolean isStartable = true;
//        for(WBSActivitiesVO wbsItemsVO : previousWBSActivityList){
        for(WBSActivitiesVO vo : previousWBSActivityList){
            String dependencyType = vo.getOutDataAttributeValue("rel_dependencyType");
                if(StrUtil.isEmpty(dependencyType)) throw new FoundationException("WBS(" + this.getCommonTitlesForDisplay(ThreadLocalUtil.getLocale()) + ")'s dependency is not corrrect.Valid type is " + StrUtil.convertArrayToList(ProjectConstants.WBS_DEPENDENCY_TYPE_VALID_SET) + "'. This is Empty.");
                if(dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START)){
                    if(!StrUtil.in(vo.getStates(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED,ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED)) return false;
                }else if(dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START)){
                    if(!StrUtil.in(vo.getStates(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED,ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED,ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED)) return false;
                }else{
                    throw new FoundationException("WBS(" + this.getCommonTitlesForDisplay(ThreadLocalUtil.getLocale()) + ")'s dependency is not corrrect.Valid type is " + StrUtil.convertArrayToList(ProjectConstants.WBS_DEPENDENCY_TYPE_VALID_SET) + "'. This is " + dependencyType + ".'");
                }                
        }
        return isStartable;
    }
    public boolean canBeStartable(Map<String,WBSItemsVO> activityObjectDB, ProjectScheduleVO sheduleVO,List<DependencyVO> previousWBSActivityList){
        boolean isStartable = true;
//        for(WBSActivitiesVO wbsItemsVO : previousWBSActivityList){
        for(DependencyVO vo : previousWBSActivityList){
            if(vo.getDependentVO() instanceof WBSActivitiesVO){
                String dependencyType = vo.getDependencyType();
                if(StrUtil.isEmpty(dependencyType)) throw new FoundationException("WBS(" + this.getCommonTitlesForDisplay(ThreadLocalUtil.getLocale()) + ")'s dependency is not corrrect.Valid type is " + StrUtil.convertArrayToList(ProjectConstants.WBS_DEPENDENCY_TYPE_VALID_SET) + "'. This is Empty.");
                if(activityObjectDB.get(vo.getDependentVO().getObid()).getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED)){
                    if("Y".equals(activityObjectDB.get(vo.getDependentVO().getObid()).getOutDataAttributeValue("isStartable"))){continue;}
                    List<DependencyVO> preiviousWBSItemList = (activityObjectDB.get(vo.getDependentVO().getObid())).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_PREVIOUS_LIST);
                    WBSItems prevoiusDom = new WBSItems(vo.getDependentVO());
                    if(!prevoiusDom.canBeStartable(activityObjectDB, sheduleVO, preiviousWBSItemList)){ return false;}
                    continue;
                }
                
                if(dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START)){
                    if(!StrUtil.in(vo.getDependentVO().getStates(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED)) return false;
                }else if(dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START)){
                    if(!StrUtil.in(vo.getDependentVO().getStates(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED,ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED)) return false;
                }else{
                    throw new FoundationException("WBS(" + this.getCommonTitlesForDisplay(ThreadLocalUtil.getLocale()) + ")'s dependency is not corrrect.Valid type is " + StrUtil.convertArrayToList(ProjectConstants.WBS_DEPENDENCY_TYPE_VALID_SET) + "'. This is " + dependencyType + ".'");
                }                
            }else{
                throw new FoundationException("WBS(" + this.getCommonTitlesForDisplay(ThreadLocalUtil.getLocale()) + ")'s dependency is not corrrect. Only Activity can have dependency.");
            }
        }
        activityObjectDB.get(this.getObid()).setOutDataAttributeValue("isStartable", "Y");
        return isStartable;
    }
    
    /**
     * Start���� ���� Parent List�� Return��.
     *
     * @param sheduleVO
     * @param findDepth
     * @return
     */
    private List<WBSItemsVO> getNotStartedParentWBSItemList(ProjectScheduleVO sheduleVO, int findDepth){
        List<WBSItemsVO> parentList = getParentWBSItemList(sheduleVO,findDepth);
        List<WBSItemsVO> notStartedParentList = new ArrayList<WBSItemsVO>();
        for(WBSItemsVO vo : parentList){
            if(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED.equals(vo.getStates())){
                notStartedParentList.add(vo);
            }
        }
        return notStartedParentList;
    }
    /**
     * Parent List�� Return��.
     *
     * @param sheduleVO
     * @param findDepth
     * @return
     */
    private List<WBSItemsVO> getParentWBSItemList(ProjectScheduleVO sheduleVO, int findDepth){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From[" + ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT + "].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, sheduleVO.getObid());   
        List<WBSItemsVO> parentList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_FROM, 
                wherePattern.toString(), parameterPattern.toString(), findDepth);
        return parentList;
    }
    /**
     * Leaf Level�� Activity(WBSActivitiesVO)�� ã�ƾ� ��.(�ſ� �߿�: �����ϸ� �ȵ�)
     *
     * @param sheduleVO
     * @return
     */
    public List<WBSActivitiesVO> getPreviousActivityList(ProjectScheduleVO sheduleVO){
        return getPreviousActivityList(sheduleVO,1);
    }
    public List<WBSActivitiesVO> getPreviousActivityList(ProjectScheduleVO sheduleVO, int depth){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From[" + ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT + "].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, sheduleVO.getObid());   
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES, GlobalConstants.FLAG_TYPE_TO, wherePattern.toString(), parameterPattern.toString(), depth);
    }

    /**
     * Leaf Level�� Activity(WBSActivitiesVO)�� ã�ƾ� ��.(�ſ� �߿�: �����ϸ� �ȵ�)
     *
     * @param sheduleVO
     * @return
     */
    public List<WBSActivitiesVO> getNextActivityList(ProjectScheduleVO sheduleVO){
        return getNextActivityList(sheduleVO,1);
    }
    /**
     * Leaf Level�� Activity(WBSActivitiesVO)�� ã�ƾ� ��.(�ſ� �߿�: �����ϸ� �ȵ�)
     *
     * @param sheduleVO
     * @return
     */
    public List<WBSActivitiesVO> getNextActivityList(ProjectScheduleVO sheduleVO, int depth){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From[" + ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT + "].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, sheduleVO.getObid());   
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), depth);
    }
    
    /**
     * �ٷ� ������ Parent�� Return��.
     *
     * @param sheduleVO
     * @return
     */
    public WBSItemsVO getParentWBSItem(ProjectScheduleVO sheduleVO){
        List<WBSItemsVO> parentList = getParentWBSItemList(sheduleVO,1);
        if(NullUtil.isNone(parentList)) return null;
        if(parentList.size() > 1) throw new FoundationException("Data Is Incorrect. Parent Object count is" + parentList.size());
        return parentList.get(0);
    }
    /**
     * Child ����Ʈ Return��
     *
     * @param sheduleVO
     * @param findDepth
     * @return
     */
    private List<WBSItemsVO> getChildWBSItemList(ProjectScheduleVO sheduleVO, int findDepth){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, sheduleVO.getObid() );
        List<WBSItemsVO> childList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_TO, 
                wherePattern.toString(), parameterPattern.toString(), findDepth);
        return childList;
    }
    /*******************************************************************Document ���� ���� Start****************************************************************/
    public WBSItemTemplatesVO getRelatedTemplate(){
        String templateName = getVo().getTemplateName();
        if(NullUtil.isNone(templateName) || "None".equals(templateName)){return null;}
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringUtil.addSortByPattern(selectPattern, "@this.[created] desc");
        String originActivityCode = getVo().getOriginActivityCode();
        StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(NULLIF(@this.[activityMasterName],''),'XXXX')", GlobalConstants.OQL_OPERATOR_EQUAL, StrUtil.defaultIfEmpty(originActivityCode, "XXXX"));
        List<WBSItemTemplatesVO> findObjects = findObjects(ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, templateName, GlobalConstants.FLAG_TYPE_ALL, selectPattern.toString(), wherePattern.toString(), paramPattern.toString(), false, 0);
        for (WBSItemTemplatesVO wbsItemTemplatesVO : findObjects) {
            if(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_ACTIVE.equals(wbsItemTemplatesVO.getStates())){
                return wbsItemTemplatesVO;
            }
        }
        return null;
    }
    public static List<WBSItemTemplatesVO> getRelatedTemplateList(String templateName){
        if(NullUtil.isNone(templateName) || "None".equals(templateName)){return new ArrayList<WBSItemTemplatesVO>();}
        List<WBSItemTemplatesVO> templateList = findObjects(ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES,
                templateName, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                // ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_ACTIVE
                GlobalConstants.FLAG_TYPE_ALL
                , false, true, false, false, "", "", "", false, 0);
        for(int i=templateList.size()-1; i>=0; i--){
            WBSItemTemplatesVO wbsItemTemplatesVO = templateList.get(i);
            if(!ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_ACTIVE.equals(wbsItemTemplatesVO.getStates())){
                templateList.remove(i);
                templateList.add( (WBSItemTemplatesVO)DomUtil.toDom(wbsItemTemplatesVO.getNextObid()).getVo() );
            }
        }
        return templateList;
    }
    public List<ProjectActivityDocumentVO> retrieveProjectActivityDocument(){
        return retrieveProjectActivityDocument(false);
    }
    public List<ProjectActivityDocumentVO> retrieveProjectActivityDocument( boolean onlyLatest ){
        return retrieveProjectActivityDocument(onlyLatest, false);
    }
    public List<ProjectActivityDocumentVO> retrieveProjectActivityDocument( boolean onlyLatest, boolean selectChangeProcesInfo ){
        return retrieveProjectActivityDocument(onlyLatest, selectChangeProcesInfo, null);
    }
    public List<ProjectActivityDocumentVO> retrieveProjectActivityDocument( boolean onlyLatest, boolean selectChangeProcesInfo, String documentObid ){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringUtil.constructSelectPattern(selectPatternBuf, "To["+ApplicationSchemaConstants.RELCLASS_SOURCEDOCUMENTTEMPLATE+"].From.obid templateObid");
        StringUtil.constructSelectPattern(selectPatternBuf, "To["+ApplicationSchemaConstants.RELCLASS_SOURCEDOCUMENTTEMPLATE+"].From.names templateNames");
        if(onlyLatest){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@REL.[isLatest]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        }
        if(StrUtil.isNotEmpty(documentObid)){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, documentObid);
        }
        List<ProjectActivityDocumentVO> relatedObjects = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES,
                ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENT,
                GlobalConstants.FLAG_TYPE_TO,
                selectPatternBuf.toString(), 
                wherePatternBuf.toString(), 
                paramPatternBuf.toString(), 
                false, 
                true, 
                0, 
                1);
        if(relatedObjects.size() > 0 && selectChangeProcesInfo){
            StringBuffer selectPattern = new StringBuffer();
            StringBuffer wherePattern = new StringBuffer();
            StringBuffer parameterPattern = new StringBuffer();
            StringUtil.addSortByPattern(selectPattern, "@this.[created] desc");
            List<ChangeProcessVO> relatedObjectSet = getRelatedObjectSet(relatedObjects,
                  ApplicationSchemaConstants.RELCLASS_CHANGEDITEMS, ApplicationSchemaConstants.BIZCLASS_CHANGEPROCESS,
                  GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
            for(int i=0; i<relatedObjects.size(); i++){
                String uniqueString = relatedObjects.get(i).getUniqueString();
                for(int j=0; j<relatedObjectSet.size(); j++){
                    if(uniqueString.equals(relatedObjectSet.get(j).getUniqueStringParent())){
                        relatedObjects.get(i).getOutData().put("changeProcessObid", relatedObjectSet.get(j).getObid());
                        relatedObjects.get(i).getOutData().put("changeProcessStates", relatedObjectSet.get(j).getStates());
                        break;
                    }
                }
            }
            
        }
        return relatedObjects;
    }
    /*******************************************************************Document ���� ���� End****************************************************************/
    /**
     * Phase�� start�ɶ� ����Ǿ����� �ϴ� ��� ����
     */
    private void processForPhaseStart(ProjectSchedule pjtScheduleDom){
        if(pjtScheduleDom.isFirstPhase(this.getVo().getPhaseName(),true)){
            this.changeProjectCurrentPhase(pjtScheduleDom, true);
        }
        this.executePhaseStart(pjtScheduleDom);
    }
    private void executePhaseStart(ProjectSchedule pjtScheduleDom){
        WBSItemsVO pahseVO = this.getPahse(pjtScheduleDom);
        WBSItems pahseDom = new WBSItems(pahseVO);
        //Phase�� Start Validation Method Call
        pahseDom.validateForStartActivitySub(pjtScheduleDom.getVo());;
        //Phase�� Start Execution Method Call
        pahseDom.postProcessForStartActivitySub(pjtScheduleDom.getVo());
    }
    /**
     * ������Ʈ�� Current Event�� �����Ѵ�.
     */    
    private void changeProjectCurrentPhase(ProjectSchedule pjtScheduleDom, boolean isFirstPhaseStart){
        if("Y".equals(this.getVo().getIsReDoActivity())) return;
        String activityPhaseName = this.getVo().getPhaseName();
        String currentPhaseName = ""; 
        if(isFirstPhaseStart){
            currentPhaseName = this.getVo().getPhaseNameSystem();
        }else{
            WBSPhasesVO nextPhaseVO = pjtScheduleDom.getNextPhase(activityPhaseName,true);
            if(!NullUtil.isNull(nextPhaseVO)){
                currentPhaseName = nextPhaseVO.getPhaseNameSystem();
            }
        }
        if(!NullUtil.isNone(currentPhaseName)){
            ProjectsVO projectVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTS, this.getVo().getProjectName());
            projectVO.setCurrentEventCode(currentPhaseName);
            Projects projectsDom = new Projects(projectVO);
            projectsDom.modifyObject();
            IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO(projectVO, ApplicationBizConstants.TR_ID_PROJECT_COM_OUT, ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_COMMON_ALL);
            triggerVO.setAttribute1("0");
            triggerVO.setAttribute2("GSCP");
            triggerVO.setAttribute3("Current Event");
            TriggerUtil.createDataByTriggerPjtCommonAll(triggerVO);
            
            triggerVO = TriggerUtil.createDataByTriggerVO(projectVO, ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_GCRC, ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_001);
            TriggerUtil.createDataByTrigger(triggerVO);
        }
    }
    /**
     * Phase�� End�ɶ� ����Ǿ����� �ϴ� ��� �����ؼ� ����.
     */
    private void processForPhaseEnd(ProjectSchedule pjtScheduleDom){
        //phase�� ã�Ƽ� ���־�� ��.
        this.postProcessForCompleteWBSActivity(pjtScheduleDom);
        this.executePhaseComplete(pjtScheduleDom);
        this.changeProjectCurrentPhase(pjtScheduleDom, false);
    }
    /**
     * Phase�� ������ Activity�� Complete�� �� �ش� Event�� Complete method�� ���� ����.
     *
     * @param pjtScheduleDom
     */
    private void executePhaseComplete(ProjectSchedule pjtScheduleDom){
        WBSItemsVO pahseVO = getPahse(pjtScheduleDom);
        WBSItems pahseDom = new WBSItems(pahseVO);
        //Phase�� Validation Method Call
        Map<String,String> activityParameterMap = this.getParameterForWorkflow(pjtScheduleDom.getVo());
        List<ActivityValidationResultVO> bizLogicValidationList  = pahseDom.validateForCompleteActivityBiz(activityParameterMap);
        BusinessObjectRoot.ariseError(bizLogicValidationList, true);
        //Phase�� Complettion Method Call
        pahseDom.postProcessForCompleteWBSActivity(pjtScheduleDom);
    }
    
    public <T> T getProject(){
        if(StrUtil.isEmpty(this.getVo().getProjectName())){return null;}
        return BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTS, this.getVo().getProjectName());
    }
    public void deleteWBSItems(ProjectScheduleVO projectScheduleVO){
        List<WBSItemsVO> subItemList = this.getChildWBSItemList(projectScheduleVO.getObid(), 1);
        for(WBSItemsVO subItemVO : subItemList){
            WBSItems wbsItemsDom = new WBSItems(subItemVO);
            wbsItemsDom.deleteWBSItems(projectScheduleVO);
        }
        if(this.isConnectedOtherContext(projectScheduleVO.getObid())){
            ControlledByProjectScheduleContextVO projectScheduleContextVO = this.getControlledByProjectScheduleContext(projectScheduleVO.getObid());
            ControlledByProjectScheduleContext projectScheduleContextDom = new ControlledByProjectScheduleContext(projectScheduleContextVO);
            projectScheduleContextDom.deleteObject();
        }else{
            this.deleteObject();
        }
    }
    private WBSItemsVO getPahse(ProjectSchedule pjtScheduleDom){
        int pahseCnt = 0;
        WBSItemsVO phaseItemsVO = null;
        List<WBSItemsVO> wbsItemsList = this.getParentWBSItemList(pjtScheduleDom.getVo(), 10);
        for(WBSItemsVO vo : wbsItemsList){
            if(vo instanceof WBSPhasesVO || vo instanceof WBSSubProjectsVO){
                phaseItemsVO = vo;
                pahseCnt++;
            }
        }
        if(pahseCnt == 0) throw new FoundationException("Cannot find Pahse or Sub Projets for '" + this.getCommonTitlesForDisplay() + "'");
        if(pahseCnt > 1) throw new FoundationException("Pahse or Sub Projets is duplicated for '" + this.getCommonTitlesForDisplay() + "'");
        return phaseItemsVO;
    }
    /**
     * N�� �������� ����Ʈ�� Return��.
     *
     * @param projectScheduleDom
     * @return
     */
    public List<WBSItemsVO> getTargetListForRePerformList(ProjectSchedule projectScheduleDom){
        List<WBSItemsVO> childList = this.getChildWBSItemList(projectScheduleDom.getObid());
        List<WBSItemsVO> targetList = new ArrayList<WBSItemsVO>();
        for(WBSItemsVO childItemsVO : childList){
            if(childItemsVO.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED)){
                targetList.add(childItemsVO);
            }
        }
        targetList.add(this.getVo());
        return targetList;
    }
    /**
     * N�� ������ ���� Revise�� ����Ǿ���ϴ� Method
     */
    public void changeToReDoActivity(){
        String revision = this.getVo().getRevision();
        String prefix = revision.substring(0,revision.indexOf("."));
        String suffix = revision.substring(revision.indexOf(".")+1);
        Integer mainVer = Integer.valueOf(prefix);
        mainVer++;
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(mainVer).append(".").append(suffix);
        this.getVo().setRevision(strBuf.toString());
        this.change(this.getClassName(), this.getNames(), this.getLifeCycle(), strBuf.toString(), this.getStates());;
    }
    private void createIfTriggerDataForAddActivity(){
        //Create
    }
    /**
     * Complete Or Start�ÿ� I/F������ ����(Schedule Start�ÿ��� Call�Ǿ���.)
     *
     * @param completedList
     */
    public static void createIfTriggerDataForCompleteActivity(List<WBSItemsVO> completedList){
        List<IfPlmDataByTriggerVO> list = new ArrayList<IfPlmDataByTriggerVO>();
        for(WBSItemsVO vo : completedList){
            // �۾� ����� Activity�� ���ؼ��� ���� i/f�� ���� �ʴ´�.(Detail������ i/f�ؾ� �ϳ�....�ٸ����� ���� �ʴµ�.....
            if(!"Y".equals(vo.getIsReDoActivity())){
                IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO(vo, ApplicationBizConstants.TR_ID_PROJECT_COM_OUT, ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_WBS_DETAIL_EACH);
                list.add(triggerVO);                
            }
        }
        TriggerUtil.createDataByTrigger(list);
    }
    private void createIfTriggerDataForDeleteActivity(){
        
        //���� PMS���� Delete�� ���ؼ� I/F���� ����.
    }
    public boolean isExecuter(String userId){
        if(NullUtil.isNull(getAssignedProjectPerson(userId))) return false;
        return true;
    }
    public boolean isMainExecuter(String userId){
        ProjectPersonVO projectPersonVO = getAssignedProjectPerson(userId);
        String isMainMember = projectPersonVO.getOutDataAttributeValue("isMainMember");
        if(StrUtil.isEmpty(isMainMember)) return false;
        if(isMainMember.equals("Y")) return true;
        return false;
        
    }
    /**
     * S~B��� DV/PV ǰ��ȸ �Ϸ�� �˸�
     */
    public void sendEvaluationReviewCompleteMail(){
        ProjectsVO projectsVO = getProject();
        String projectGradeCode = projectsVO.getProjectGradeCode();
        if(StrUtil.isNotEmpty(projectGradeCode)){
            if(projectGradeCode.startsWith("S") || projectGradeCode.startsWith("A") || projectGradeCode.startsWith("B") ){
                IfPlmDataByTriggerVO trVO = new IfPlmDataByTriggerVO();
                trVO.setObjectId(getVo().getObid());
                trVO.setObjectType(getVo().getClassName());
                trVO.setObjectName(getVo().getNames());
                trVO.setObjectRevision(getVo().getRevision());
                trVO.setCurrentStatus(getVo().getStates());
                trVO.setUserId(getVo().getCreator());
                trVO.setTargetSystem(ApplicationBizConstants.INF_TARGET_SYSTEM_MAIL_SEND);
                trVO.setInterfaceId(ApplicationBizConstants.INF_TARGET_SYSTEM_MAIL_SEND_MAIL025);
                trVO.setAttribute1("");
                trVO.setAttribute2("");
                trVO.setAttribute3("");
                TriggerUtil.createDataByTrigger(trVO);
            }
        }
        
    }
    /**
     *
     * @param templateNames
     * @return
     */
    public boolean hasProjectActivityDocumentByDocumentTemplateName(String templateNames){
        List<ProjectActivityDocumentVO> retrieveProjectActivityDocument = retrieveProjectActivityDocument();
        for (ProjectActivityDocumentVO projectActivityDocumentVO : retrieveProjectActivityDocument) {
            ProjectActivityDocument projectActivityDocument = DomUtil.toDom(projectActivityDocumentVO);
            List<ProjectActivityDocumentTemplateVO> relatedTemplate = projectActivityDocument.getRelatedTemplate();
            for (ProjectActivityDocumentTemplateVO projectActivityDocumentTemplateVO : relatedTemplate) {
                if(projectActivityDocumentTemplateVO.getNames().equals(templateNames)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 
     */
    public String getNptInterfaceBPMActivityOwnerMailId(){
        String activityOwnerMailId = null;
        List<ProjectPersonVO> assignedProjectPersonList = this.getAssignedProjectPersonList();
        if(assignedProjectPersonList.size() > 0){
            UsersVO activityOwner = null;
            for (ProjectPersonVO projectPersonVO : assignedProjectPersonList) {
                String isMainMember = (String)projectPersonVO.getOutData().get("rel_isMainMember");
                if("Y".equals(isMainMember)){
                    activityOwner = Users.getUsers(projectPersonVO.getUserId()).getVo();
                    break;
                }
            }
            if(activityOwner == null){
                activityOwner = Users.getUsers(assignedProjectPersonList.get(0).getUserId()).getVo();
            }
            activityOwnerMailId = activityOwner.getMailId();
        }
        return activityOwnerMailId; 
    }
    
    public List<ActivityValidationResultVO> checkAllDocumentComplete(){
        Map<String, String> activityParameterMap = new HashMap<String, String>();
        activityParameterMap.put(ApplicationBizConstants.WORKFLOW_ACTIVITY_MAP_KEY_projectObid, ((ProjectsVO)getProject()).getObid() );
        return this.validateForCompleteEssentialDoc(null , activityParameterMap);
    }
    /**
     *
     * @param onlyLatest
     * @param names
     * @return
     */
    public List<ProjectActivityDocumentVO> retrieveProjectActivityDocument(boolean onlyLatest, String documentTemplateNames){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        if(StrUtil.isNotEmpty(documentTemplateNames)){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "To["+ApplicationSchemaConstants.RELCLASS_SOURCEDOCUMENTTEMPLATE+"].From.names", GlobalConstants.OQL_OPERATOR_EQUAL, documentTemplateNames);
        }
        if(onlyLatest){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[isLatest]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        }
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES, ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENT, GlobalConstants.FLAG_TYPE_TO, wherePattern.toString(), paramPattern.toString(), 1);
    }
    /**
     *
     * @return
     */
    public WBSItemsVO retreveLatestReleasedActivity(){
        if(this.isLast()){
            return this.getVo();
        }
        WBSItemsVO checkRevisionVO = this.getLastRevision();
        WBSItems checkRevision = DomUtil.toDom(checkRevisionVO);
        while(true){
            List<ProjectScheduleVO> projectScheduleList = checkRevision.getProjectScheduleList();
            for (ProjectScheduleVO projectScheduleVO : projectScheduleList) {
                ProjectSchedule projectSchedule = DomUtil.toDom(projectScheduleVO);
                if(projectSchedule.isReleased()){
                    return checkRevision.getVo();
                }
            }
            if( StrUtil.isNotEmpty(checkRevision.getNextObid())){
                checkRevision = DomUtil.toDom(checkRevision.getNextObid());
            }else{
                break;
            }
        }
        return null;
    }
}

