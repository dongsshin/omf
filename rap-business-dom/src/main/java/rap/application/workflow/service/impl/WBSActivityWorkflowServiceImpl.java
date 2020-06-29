/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSWorkflowServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 7.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.classes.service.TimeService;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.schema.object.temp.model.ActivityValidationResultVO;
import com.rap.omc.util.NullUtil;

import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.dom.ProjectPerson;
import rap.api.object.project.dom.ProjectSchedule;
import rap.api.object.project.dom.WBSItems;
import rap.api.object.project.model.ProjectPersonVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.relation.dom.WorkflowWBSActivityTask;
import rap.api.object.relation.model.WorkflowWBSActivityTaskVO;
import rap.api.object.workflow.dom.WorkflowInboxTask;
import rap.api.object.workflow.dom.WorkflowRoute;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.api.object.workflow.model.WorkflowRouteVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.WorkflowConstants;
import rap.application.workflow.model.ApprovalVO;
import rap.application.workflow.model.EPApprovalVO;
import rap.application.workflow.model.ReassignVO;
import rap.application.workflow.model.WBSActivityInboxTaskVO;
import rap.application.workflow.model.WBSActivityWorkflowVO;
import rap.application.workflow.service.WBSActivityWorkflowService;
import rap.application.workflow.service.WorkflowService;
import rap.application.workflow.util.WorkflowChainedComparator;
import rap.application.workflow.util.WorkflowRouteComparator;
import rap.application.workflow.util.WorkflowRouteNodeComparator;
import rap.application.workflow.util.WorkflowRouteStepComparator;


/**
 * <pre>
 * Class : WBSActivityWorkflowServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
@Service("wbsActivityWorkflowService")
public class WBSActivityWorkflowServiceImpl implements WBSActivityWorkflowService{
    static final Logger LOGGER = LoggerFactory.getLogger(WBSActivityWorkflowServiceImpl.class);


    @Resource(name = "timeService")
    private TimeService timeService;

    @Resource(name="messageSourceAccessor")
    MessageSourceAccessor messageSourceAccessor;

//    @Resource(name="propertiesService")
//    private ConfigService propertiesService;
    
    @Resource(name = "workflowService")
    private WorkflowService workflowService;
    
//    @Resource(name = "wbsService")
//    private WBSService wbsService;
    /**
     * 
     * @param wBSActivityForInboxVO
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#txnBuildWBSActivityWorkflow(lgcns.rnd.application.workflow.model.WBSActivityWorkflowVO)
     */
    @Override
    public void txnBuildWBSActivityWorkflow(WBSActivityWorkflowVO wbsActivityForInboxVO){

        validateForBuildWBSActivityWorkflow(wbsActivityForInboxVO);
        WorkflowRoute workflowRoute = createWorkflowRoute(wbsActivityForInboxVO);
        createWorkflowInboxTaskList(wbsActivityForInboxVO, workflowRoute);
        
    }
    private void validateForBuildWBSActivityWorkflow(WBSActivityWorkflowVO wbsActivityForInboxVO){
        if(wbsActivityForInboxVO.getProjectMemberList().size() != wbsActivityForInboxVO.getWbsActivityInboxTaskVOList().size()) 
            throw new ApplicationException("The size of mebmer and inbox task are not the same");
        List<WorkflowRouteVO> routeList = wbsActivityForInboxVO.getWbsActivity().getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE, ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE, GlobalConstants.FLAG_TYPE_TO);
        if(!NullUtil.isNone(routeList)) throw new ApplicationException("Activity(" + wbsActivityForInboxVO.getWbsActivity().getCommonTitlesForDisplay() + ") is already started. Cannot create To-Do list(Inbox Task).<BR>Please contact System Administator.");
    }
    private WorkflowRoute createWorkflowRoute(WBSActivityWorkflowVO wbsActivityForInboxVO) {
        //SELECT * FROM PTROUTE
        WorkflowRouteVO workflowRouteVO = new WorkflowRouteVO();
        workflowRouteVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_ROUTENAME));
        workflowRouteVO.setRouteCompletionAction(wbsActivityForInboxVO.getRouteCompletionAction());
        workflowRouteVO.setDescriptions(wbsActivityForInboxVO.getWbsActivity().getObid()+"_"+wbsActivityForInboxVO.getWbsActivity().getNames()+"_"+wbsActivityForInboxVO.getRouteStatus());
        workflowRouteVO.setRestartUpOnTaskRejection(wbsActivityForInboxVO.getRestartUpOnTaskRejection());
        workflowRouteVO.setAutoStopOnRejection(wbsActivityForInboxVO.getAutoStopOnRejection()); //Immediate , Deferred
        workflowRouteVO.setRouteBasePurpose(wbsActivityForInboxVO.getRouteBasePurpose());
        workflowRouteVO.setStates(WorkflowConstants.STATES_TYPE_DEFINE);
        workflowRouteVO.setTitles(workflowRouteVO.getNames());
        //dss workflowRouteVO.setOrganizations(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.divisionUnit, "None"));
        workflowRouteVO.setProcessTimestamp(wbsActivityForInboxVO.getProcessTimestamp());
        workflowRouteVO.setActivityUrl(wbsActivityForInboxVO.getActivityURL());
        WorkflowRoute workflowRoute = DomUtil.toDom(workflowRouteVO);
        workflowRoute.createObject();

        //Create WorkflowObjectRoute
        Map<String, Object> attributeMap = new HashMap<String, Object>();
        attributeMap.put("routePurpose", wbsActivityForInboxVO.getRoutePurpose());
        attributeMap.put("routeLifeCycle", wbsActivityForInboxVO.getRouteLifeCycle());
        attributeMap.put("routeState", wbsActivityForInboxVO.getRouteStatus());
        wbsActivityForInboxVO.getWbsActivity().addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE, workflowRoute.getVo(), attributeMap);
        workflowRoute.start();
        return workflowRoute;
    }
    
    /**
     * 
     *
     * @param wbsActivityForInboxVO
     * @param workflowRoute
     */
    @Override
    public void createWorkflowInboxTaskList(WBSActivityWorkflowVO wbsActivityForInboxVO, WorkflowRoute workflowRoute) {
        @SuppressWarnings("unchecked")
        List<BusinessObjectRootVO> toCreateInboxTaskVOList = (List<BusinessObjectRootVO>)wbsActivityForInboxVO.getProjectMemberList();
        for(int idx = 0; idx < toCreateInboxTaskVOList.size(); idx ++) {
            BusinessObjectRootVO projectMember = wbsActivityForInboxVO.getProjectMemberList().get(idx);
            WBSActivityInboxTaskVO wbsActivityInboxTaskVO = wbsActivityForInboxVO.getWbsActivityInboxTaskVOList().get(idx);
            wbsActivityInboxTaskVO.setStep("1");
            wbsActivityInboxTaskVO.setProcessTimestamp(workflowRoute.getVo().getProcessTimestamp());
            wbsActivityInboxTaskVO.setSequences(idx);
            wbsActivityInboxTaskVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_INBOXTASKNAME));
            wbsActivityInboxTaskVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_NONE);
            wbsActivityInboxTaskVO.setRouteNodeObid("");
            wbsActivityInboxTaskVO.setRevision("1");
            wbsActivityInboxTaskVO.setStates(WorkflowConstants.STATES_TYPE_ASSIGNED);//Assigned,Review,Complete
//            wbsActivityInboxTaskVO.setTaskOwner(projectMember.getNames()); // wbsActivityInboxTaskVO에서 이미 set 되어 옴.
            wbsActivityInboxTaskVO.setDescriptions("");
            wbsActivityInboxTaskVO.setTitles(wbsActivityForInboxVO.getWbsActivity().getCommonTitlesForDisplay());
            
            WorkflowInboxTaskVO workflowInboxTaskVO = new WorkflowInboxTaskVO();
            BeanUtils.copyProperties(wbsActivityInboxTaskVO, workflowInboxTaskVO);
            WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
            workflowInboxTask.createObject();
           /*
            *  Create WorkflowRouteTask Relation From:WorkflowRoute To:WorkflowInboxTask)
            *  select * from PTKEYTABLE a where a.pclass_name = 'WorkflowRouteTask'
            */
            workflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK, workflowRoute.getVo(), new HashMap<String, Object>());
            
            workflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWWBSACTIVITYTASK, projectMember, new HashMap<String, Object>());
           
            //createApprovalInfoToEP(wbsActivityForInboxVO.getWbsActivity(), workflowRoute, workflowInboxTask);
            if(workflowInboxTask.getVo().getNotifyEmail()) {
                workflowService.notifyMail(wbsActivityForInboxVO.getWbsActivity(), workflowRoute, workflowInboxTask.getVo().getTaskOwner());
            }
            buildDelegatedInboxTask(wbsActivityForInboxVO.getWbsActivity(), workflowRoute, workflowInboxTask);
        }
    }
    
    /**
     * 위임건의 EP에 별도로 TODO notify 하지 않음. EP내에서 위임건에 대해 TODO 설정 함.
     *
     * @param businessObjectRoot
     * @param workflowRoute
     * @param originWorkflowInboxTask
     */
    private void buildDelegatedInboxTask(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, WorkflowInboxTask originWorkflowInboxTask) {
        Users assginee = null; // dss Users.getUsers(originWorkflowInboxTask.getVo().getTaskOwner());
        String originTaskOwner =  assginee.getNames();
        for(int idx = 0;; idx ++) {
            UsersVO delegateToAssigneeVO = assginee.getDelegatorUsersVO();
            if(NullUtil.isNull(delegateToAssigneeVO) || delegateToAssigneeVO.getObid().equals(assginee.getVo().getObid())) { //위임 설정이 없을 경우
                break;
            }
            
            if(idx == 0) {
                originWorkflowInboxTask.getVo().setOriginTaskOwner(originTaskOwner);
                originWorkflowInboxTask.modifyObject();
            }
            
            /*
             * Create WorkflowInboxTask for delegate
             */
            WorkflowInboxTaskVO delegateWorkflowInboxTaskVO = new WorkflowInboxTaskVO();
            delegateWorkflowInboxTaskVO.setOriginTaskOwner(originTaskOwner);
            delegateWorkflowInboxTaskVO.setStep(originWorkflowInboxTask.getVo().getStep());
            delegateWorkflowInboxTaskVO.setProcessTimestamp(workflowRoute.getVo().getProcessTimestamp());
            delegateWorkflowInboxTaskVO.setSequences(originWorkflowInboxTask.getVo().getSequences());
            delegateWorkflowInboxTaskVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_INBOXTASKNAME));
            delegateWorkflowInboxTaskVO.setRouteAction(originWorkflowInboxTask.getVo().getRouteAction());
            delegateWorkflowInboxTaskVO.setRouteInstructions(originWorkflowInboxTask.getVo().getRouteInstructions());
            delegateWorkflowInboxTaskVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_NONE);
            delegateWorkflowInboxTaskVO.setApproversResponsibility(originWorkflowInboxTask.getVo().getApproversResponsibility());
            delegateWorkflowInboxTaskVO.setTaskRequirement(originWorkflowInboxTask.getVo().getTaskRequirement());
            delegateWorkflowInboxTaskVO.setRouteNodeObid("Delegated");
            delegateWorkflowInboxTaskVO.setReviewTask(originWorkflowInboxTask.getVo().getReviewTask());
            delegateWorkflowInboxTaskVO.setReviewersComments(originWorkflowInboxTask.getVo().getReviewersComments());
            delegateWorkflowInboxTaskVO.setReviewCommentsNeeded(originWorkflowInboxTask.getVo().getReviewCommentsNeeded());
            delegateWorkflowInboxTaskVO.setDueDateOffset(originWorkflowInboxTask.getVo().getDueDateOffset());
            delegateWorkflowInboxTaskVO.setDateOffsetFrom(originWorkflowInboxTask.getVo().getDateOffsetFrom());
            delegateWorkflowInboxTaskVO.setAssigneeSetDueDate(originWorkflowInboxTask.getVo().getAssigneeSetDueDate());
            delegateWorkflowInboxTaskVO.setAllowDelegation(originWorkflowInboxTask.getVo().getAllowDelegation());
            delegateWorkflowInboxTaskVO.setIsEssential(originWorkflowInboxTask.getVo().getIsEssential());
            delegateWorkflowInboxTaskVO.setParallelNodeProcessionRule(originWorkflowInboxTask.getVo().getParallelNodeProcessionRule());
            delegateWorkflowInboxTaskVO.setTitles(businessObjectRoot.getCommonTitlesForDisplay());
            delegateWorkflowInboxTaskVO.setRevision("1");
            delegateWorkflowInboxTaskVO.setStates(WorkflowConstants.STATES_TYPE_ASSIGNED);//Assigned,Review,Complete
            delegateWorkflowInboxTaskVO.setActionComments(originWorkflowInboxTask.getVo().getActionComments());
            delegateWorkflowInboxTaskVO.setTaskOwner(delegateToAssigneeVO.getNames());
            delegateWorkflowInboxTaskVO.setDescriptions("");
            delegateWorkflowInboxTaskVO.setNotifyEmail(originWorkflowInboxTask.getVo().getNotifyEmail());
            delegateWorkflowInboxTaskVO.setProcessRole(originWorkflowInboxTask.getVo().getProcessRole());
            delegateWorkflowInboxTaskVO.setDelegatedFrom(assginee.getObid());
            delegateWorkflowInboxTaskVO.setInboxTaskType(originWorkflowInboxTask.getVo().getInboxTaskType());

            if("Task Create Date".equals(originWorkflowInboxTask.getVo().getDateOffsetFrom())
                     && originWorkflowInboxTask.getVo().getDueDateOffset() > 0) {
                Calendar c = Calendar.getInstance();
                c.setTime(timeService.getDBLocalTime());
                c.add(Calendar.DATE, originWorkflowInboxTask.getVo().getDueDateOffset());
                delegateWorkflowInboxTaskVO.setScheduledCompletionDate(c.getTime());
            }

            WorkflowInboxTask delegateWorkflowInboxTask = DomUtil.toDom(delegateWorkflowInboxTaskVO);
            delegateWorkflowInboxTask.createObject();
            /*
             *  Create WorkflowRouteTask Relation From:WorkflowRoute To:WorkflowInboxTask)
             *  select * from PTKEYTABLE a where a.pclass_name = 'WorkflowRouteTask'
             */
            delegateWorkflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK, workflowRoute.getVo(), new HashMap<String, Object>());
            
            if(delegateWorkflowInboxTaskVO.getNotifyEmail()) {
                workflowService.notifyMail(businessObjectRoot, workflowRoute, delegateToAssigneeVO.getNames());
            }
            assginee = DomUtil.toDom(delegateToAssigneeVO);
        }
    }

    /**
     * 
     * @param businessObjectRoot
     * @return
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#retrieveWBSActivityWorkflow(omc.api.object.dom.BusinessObjectRoot)
     */
    @Override
    public List<ApprovalVO> retrieveWBSActivityWorkflow(BusinessObjectRoot businessObjectRoot){
        if(NullUtil.isNull(businessObjectRoot)) throw new IllegalArgumentException();
        WorkflowRouteVO workflowRouteVO =  businessObjectRoot.getRelatedObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE, ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE, GlobalConstants.FLAG_TYPE_TO);
        
        if(NullUtil.isNull(workflowRouteVO)) return new ArrayList<ApprovalVO>();
        WorkflowRoute workflowRoute = DomUtil.toDom(workflowRouteVO);
        List<WorkflowInboxTaskVO> workflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
        
        List<ApprovalVO> rtnApprovalVOList = new ArrayList<ApprovalVO>();
        
        String loginUser = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, null);
        for (WorkflowInboxTaskVO workflowInboxTaskVO : workflowInboxTaskVOList) {
            ApprovalVO approvalVO = new ApprovalVO();
            approvalVO.setRouteAction(workflowInboxTaskVO.getRouteAction());
            approvalVO.setRouteInstructions(workflowInboxTaskVO.getRouteInstructions());
            approvalVO.setNotifyEmail(workflowInboxTaskVO.getNotifyEmail() );
            approvalVO.setProcessRole(workflowInboxTaskVO.getProcessRole());
            approvalVO.setWorkflowInboxTaskObid(workflowInboxTaskVO.getObid());
            approvalVO.setReviewApproveComments(workflowInboxTaskVO.getReviewersComments());
            approvalVO.setComments(workflowInboxTaskVO.getComments());
            approvalVO.setActualCompletionDate(workflowInboxTaskVO.getActualCompletionDate());
            approvalVO.setApprovalStatus(NullUtil.isNull(workflowInboxTaskVO.getApprovalStatus()) ? "" : workflowInboxTaskVO.getApprovalStatus());
            approvalVO.setInboxTaskState(workflowInboxTaskVO.getStates());
            approvalVO.setOriginTaskOwner(workflowInboxTaskVO.getOriginTaskOwner());
            approvalVO.setTaskOwner(workflowInboxTaskVO.getTaskOwner());
            switch(workflowInboxTaskVO.getApprovalStatus()) {
                case WorkflowConstants.APPROVAL_STATUS_NONE :
                    approvalVO.setAction(WorkflowConstants.ACTION_TYPE_AWAITING_APPROVAL);
                    break;

                case WorkflowConstants.APPROVAL_STATUS_APPROVE:
                    approvalVO.setAction(WorkflowConstants.ACTION_TYPE_APPROVED);
                    break;

                case WorkflowConstants.APPROVAL_STATUS_REJECT:
                    approvalVO.setAction(WorkflowConstants.ACTION_TYPE_REJECTED);
                    break;

                default:
                    approvalVO.setAction(WorkflowConstants.ACTION_TYPE_PENDING);
                    break;
            }
            approvalVO.setWorkflowRouteObid(workflowRoute.getVo().getObid());
            approvalVO.setRoutePurpose(workflowRoute.getVo().getRouteBasePurpose());
            Users assignee = Users.getUsers(workflowInboxTaskVO.getTaskOwner());
            approvalVO.setRouteStateSequence(1);
            approvalVO.setTitles(new WorkflowInboxTask(workflowInboxTaskVO).getCommonTitlesForDisplay());
            approvalVO.setDepartment(assignee.getVo().getHrDepartmentKor());
            approvalVO.setAssignee(assignee.getVo().getTitles());
            approvalVO.setAssigneeObid(assignee.getObid());
            approvalVO.setAssigneeNames(assignee.getNames());
            //Q20181106_03967
            if(assignee.getNames().equals(loginUser)) {
                approvalVO.setAvailableToApproval(true);
            }
            approvalVO.setState((String)workflowRoute.getVo().getOutData().get("rel_routeState"));
            approvalVO.setDummyStep(workflowInboxTaskVO.getStep());
            
            approvalVO.setStep(Integer.parseInt(workflowInboxTaskVO.getStep()));
            approvalVO.setEssential((workflowInboxTaskVO.getIsEssential()) ? "Y" : "");
            approvalVO.setParallelNodeProcessionRule(workflowInboxTaskVO.getParallelNodeProcessionRule());
            approvalVO.setActionCommnets(workflowInboxTaskVO.getActionComments());
            approvalVO.setSequence(workflowInboxTaskVO.getSequences());
            approvalVO.setInboxTaskType(workflowInboxTaskVO.getInboxTaskType());
            rtnApprovalVOList.add(approvalVO);
        }
        
        Collections.sort(rtnApprovalVOList, new WorkflowChainedComparator(
                new WorkflowRouteComparator(),
                new WorkflowRouteStepComparator(),
                new WorkflowRouteNodeComparator())
        );
        return rtnApprovalVOList;
    }

    /**
     * 
     * @param businessObjectRoot
     * @param approvalVO
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#txnSubmitApproval(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.application.workflow.model.ApprovalVO)
     */
    @Override
    public void txnSubmitApproval(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO, Boolean skipWarning){
        WorkflowRoute workflowRoute = DomUtil.toDom(approvalVO.getWorkflowRouteObid());
        /*
         * 결재 대상 inboxtask
         */
        WorkflowInboxTask originWorkflowInboxTask = null;
        WorkflowInboxTask toBeApprovalWorkflowInboxTask = DomUtil.toDom(approvalVO.getWorkflowInboxTaskObid(), false);
        originWorkflowInboxTask = toBeApprovalWorkflowInboxTask;
        if(NullUtil.isNull(toBeApprovalWorkflowInboxTask.getVo().getOriginTaskOwner())) {//결재 대상이 위임건이 아닌 경우 일반 결재 형식으로 하고 다만 Reject 일 경우 위임으로 인해 생성 된 inboxtask는 삭제 되어야 한다. 
            if(!WorkflowConstants.STATES_TYPE_ASSIGNED.equals(toBeApprovalWorkflowInboxTask.getVo().getStates())){
                throw new ApplicationException("Not subject to approval.["+toBeApprovalWorkflowInboxTask.getVo().getStates()+"]");
            }
            toBeApprovalWorkflowInboxTask.getVo().setActualCompletionDate(timeService.getDBLocalTime());
            toBeApprovalWorkflowInboxTask.getVo().setComments(approvalVO.getComments());
            toBeApprovalWorkflowInboxTask.getVo().setApprovalStatus(approvalVO.getApprovalStatus());
            toBeApprovalWorkflowInboxTask.getVo().setMobileApproval(approvalVO.getMobileApproval());
            toBeApprovalWorkflowInboxTask.modifyObject();
            toBeApprovalWorkflowInboxTask.promote();
            toBeApprovalWorkflowInboxTask.promote();

            //Create workflowRouteTaskHistory
            workflowRoute.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASKHISTORY, toBeApprovalWorkflowInboxTask.getVo(), new HashMap<String, Object>());
        }else{//위임에 의해 생성 된 inbox task도 처리
            String orgineTaskOwner = toBeApprovalWorkflowInboxTask.getVo().getOriginTaskOwner();
            String step = toBeApprovalWorkflowInboxTask.getVo().getStep();
            List<WorkflowInboxTaskVO> workflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
            for(WorkflowInboxTaskVO inboxTaskVO : workflowInboxTaskVOList) {
                if(NullUtil.isNull(inboxTaskVO.getOriginTaskOwner())
                   || !orgineTaskOwner.equals(inboxTaskVO.getOriginTaskOwner())
                   || !step.equals(inboxTaskVO.getStep())
                   || !WorkflowConstants.STATES_TYPE_ASSIGNED.equals(inboxTaskVO.getStates())) continue;
                
                if(inboxTaskVO.getOriginTaskOwner().equals(inboxTaskVO.getTaskOwner())) { //origin inboxTask
                    UsersVO usersVo = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_USERS, ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, ""));
                    
                    if(!WorkflowConstants.STATES_TYPE_ASSIGNED.equals(inboxTaskVO.getStates())) throw new ApplicationException("Not subject to approval.");
                    inboxTaskVO.setOriginTaskOwner(null);
                    inboxTaskVO.setActualCompletionDate(timeService.getDBLocalTime());
                    inboxTaskVO.setComments(approvalVO.getComments() + " by "+  usersVo.getTitles()+" [Delegate]");
                    inboxTaskVO.setApprovalStatus(approvalVO.getApprovalStatus());
                    inboxTaskVO.setMobileApproval(approvalVO.getMobileApproval());
                    originWorkflowInboxTask = DomUtil.toDom(inboxTaskVO);
                    originWorkflowInboxTask.modifyObject();
                    originWorkflowInboxTask.promote();
                    originWorkflowInboxTask.promote();

                    //Create workflowRouteTaskHistory
                    workflowRoute.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASKHISTORY, inboxTaskVO, new HashMap<String, Object>());
                }else{
                    WorkflowInboxTask inboxTask = DomUtil.toDom(inboxTaskVO);
                    inboxTask.deleteObject();
                }
            }
        }
        
        if(WorkflowConstants.APPROVAL_STATUS_APPROVE.equals(approvalVO.getApprovalStatus())) {
            submitApprove(businessObjectRoot, workflowRoute, originWorkflowInboxTask, approvalVO, skipWarning);
        }else{
            throw new ApplicationException("Not allowed approval status["+approvalVO.getApprovalStatus()+"]");
        }
    }
    private void submitApprove(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, WorkflowInboxTask workflowInboxTask, ApprovalVO approvalVO, Boolean skipWarning) {
        workflowService.createSubmitApprovalInfoToEP(businessObjectRoot, workflowRoute, workflowInboxTask, WorkflowConstants.APPROVAL_STATUS_APPROVE);
        boolean goToTheNext = false;
        if(WorkflowConstants.ROUTE_ACTION_ANY.equals(workflowInboxTask.getVo().getParallelNodeProcessionRule())) {
            throw new ApplicationException("Not allowed route action[Any]");
        }else if(WorkflowConstants.ROUTE_ACTION_ALL.equals(workflowInboxTask.getVo().getParallelNodeProcessionRule()) && workflowRoute.isAllApproved()) {
            goToTheNext = true;
        }
        if(goToTheNext) { 
            workflowRoute.promote();
            if(approvalVO.getCallCompleteWBSActivity()) businessObjectRoot.completeWBSActivity(workflowInboxTask.getVo(), skipWarning);
        }
    }

    /**
     * 
     * 
     * @param wbsActivityForInboxVOList
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#txnBuildWBSActivityWorkflowList(java.util.List)
     */
    @Override
    public void txnBuildWBSActivityWorkflowList(List<WBSActivityWorkflowVO> wbsActivityForInboxVOList){
        for(WBSActivityWorkflowVO wbsActivityWorkflowVO : wbsActivityForInboxVOList) {
            txnBuildWBSActivityWorkflow(wbsActivityWorkflowVO);
        }
    }
    @Override
    public void txnReassignApprover(BusinessObjectRoot businessObjectRoot, ReassignVO reassignVO, boolean changeActivityOwner){
    	//수정반영되어야 함.dss
        if(changeActivityOwner){
            WBSItems wbsActivities = (WBSItems) businessObjectRoot;
            List<UsersVO> activityOwnerUserList = wbsActivities.getActivityOwnerUserList();
            WorkflowInboxTask workflowInboxTask = DomUtil.toDom(reassignVO.getWorkflowInboxTaskObid());
            String scheduleObid = workflowInboxTask.getVo().getProcessTimestamp();
            ProjectSchedule projectSchedule = DomUtil.toDom(scheduleObid);
            ProjectsVO relatedProject = projectSchedule.getRelatedProject();
            List<UsersVO> activityOwnerDeleteList = new ArrayList<UsersVO>();
            List<UsersVO> activityOwnerCreateList = new ArrayList<UsersVO>();
            UsersVO deleteUsersVO = Users.getUsers(workflowInboxTask.getVo().getTaskOwner()).getVo();
            UsersVO createUsersVO = (UsersVO)DomUtil.toDom(reassignVO.getToAssigneeObid()).getVo(); 
            boolean canDeleteOwner = false;
            for(UsersVO usersVO : activityOwnerUserList){
                if(createUsersVO.getObid().equals(usersVO.getObid())){
                    throw new ApplicationException(createUsersVO.getTitles() + " is already assigned to this Activity.<br> Please contact to 'Project Leader' for owner change.");
                }
                if(deleteUsersVO.getObid().equals(usersVO.getObid())){
                    canDeleteOwner = true;
                }
            }
            if(canDeleteOwner){
                activityOwnerDeleteList.add(deleteUsersVO);
            }
            activityOwnerCreateList.add(createUsersVO);
//            wbsService.txnUpdateActivityOwner(relatedProject.getObid(), scheduleObid, (WBSActivitiesVO)businessObjectRoot.getVo(), activityOwnerCreateList, null, activityOwnerDeleteList);
        }
        txnReassignApprover(businessObjectRoot, reassignVO);
    }
    /**
     * 
     * @param businessObjectRoot
     * @param reassignVO
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#txnReassignApprover(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.application.workflow.model.ReassignVO)
     */
    @Override
    public void txnReassignApprover(BusinessObjectRoot businessObjectRoot, ReassignVO reassignVO){
        WorkflowRoute workflowRoute = DomUtil.toDom(reassignVO.getWorkflowRouteObid(), false);
        WorkflowInboxTask fromWorkflowInboxTask = DomUtil.toDom(reassignVO.getWorkflowInboxTaskObid(), false);
        
        if(WorkflowConstants.APPROVAL_STATUS_COMPLETE.equals(fromWorkflowInboxTask.getVo().getStates())){
            throw new ApplicationException("It cannot be reassigned.[Reason: complete approval]");
        }
        
        Users toAssignUser = DomUtil.toDom(reassignVO.getToAssigneeObid(), false);
        if(fromWorkflowInboxTask.getVo().getTaskOwner().equals(toAssignUser.getNames())){
            throw new ApplicationException("It cannot be reassigned to the same person.");
        }

        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
        for(WorkflowInboxTaskVO workflowInboxTaskVO : rtnWorkflowInboxTaskVOList) {
            if(toAssignUser.getNames().equals(workflowInboxTaskVO.getTaskOwner())) {
                throw new ApplicationException("Person to reassign already exists in state.");
            }
        }
        fromWorkflowInboxTask.getVo().setDelegatedFrom(null);
        fromWorkflowInboxTask.getVo().setDelegatedTo(reassignVO.getToAssigneeObid());
        fromWorkflowInboxTask.getVo().setApprovalStatus(WorkflowConstants.WORKFLOW_REASSIGN);
        fromWorkflowInboxTask.getVo().setComments(WorkflowConstants.WORKFLOW_REASSIGN +" "+ toAssignUser.getVo().getTitles());
        fromWorkflowInboxTask.getVo().setActionComments(reassignVO.getComments());
        fromWorkflowInboxTask.getVo().setActualCompletionDate(timeService.getDBLocalTime());
        fromWorkflowInboxTask.modifyObject();
        workflowRoute.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASKHISTORY, fromWorkflowInboxTask.getVo(), new HashMap<String, Object>());
        
        fromWorkflowInboxTask.promote();
        fromWorkflowInboxTask.promote();
        
        List<WorkflowWBSActivityTaskVO> workflowWBSActivityTaskVOList = fromWorkflowInboxTask.getRelationship(ApplicationSchemaConstants.RELCLASS_WORKFLOWWBSACTIVITYTASK);
        WorkflowWBSActivityTaskVO workflowWBSActivityTaskVO = workflowWBSActivityTaskVOList.get(0);
        String workflowWBSActivityTaskVOFromObid = workflowWBSActivityTaskVO.getFromObid();
        WorkflowWBSActivityTask toDeleteWorkflowWBSActivityTask = DomUtil.toDom(workflowWBSActivityTaskVO);
        
        toDeleteWorkflowWBSActivityTask.deleteObject();
//        List<WorkflowWBSActivityTaskVO> rtnWorkflowWBSActivityTaskVOList = fromWorkflowInboxTask.getRelationship(ApplicationSchemaConstants.RELCLASS_WORKFLOWWBSACTIVITYTASK);
//        if(!NullUtil.isNone(rtnWorkflowWBSActivityTaskVOList) ) {
//            WorkflowWBSActivityTaskVO toDeleteWorkflowWBSActivityTaskVO = rtnWorkflowWBSActivityTaskVOList.get(0);
//            WorkflowWBSActivityTask toDeleteWorkflowWBSActivityTask = DomUtil.toDom(toDeleteWorkflowWBSActivityTaskVO);
//            toDeleteWorkflowWBSActivityTask.deleteObject();
//        }
        
        fromWorkflowInboxTask.deleteInboxTask(workflowRoute);

        WorkflowInboxTaskVO toWorkflowInboxTaskVO = new WorkflowInboxTaskVO();
        toWorkflowInboxTaskVO.setStep(fromWorkflowInboxTask.getVo().getStep());
        toWorkflowInboxTaskVO.setProcessTimestamp(workflowRoute.getVo().getProcessTimestamp());
        toWorkflowInboxTaskVO.setSequences(fromWorkflowInboxTask.getVo().getSequences());
        toWorkflowInboxTaskVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_INBOXTASKNAME));
        toWorkflowInboxTaskVO.setRouteAction(fromWorkflowInboxTask.getVo().getRouteAction());
        toWorkflowInboxTaskVO.setRouteInstructions(fromWorkflowInboxTask.getVo().getRouteInstructions());
        toWorkflowInboxTaskVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_NONE);
        toWorkflowInboxTaskVO.setApproversResponsibility(fromWorkflowInboxTask.getVo().getApproversResponsibility());
        toWorkflowInboxTaskVO.setTaskRequirement(fromWorkflowInboxTask.getVo().getTaskRequirement());
        toWorkflowInboxTaskVO.setRouteNodeObid(fromWorkflowInboxTask.getVo().getRouteNodeObid());
        toWorkflowInboxTaskVO.setReviewTask(fromWorkflowInboxTask.getVo().getReviewTask());
        toWorkflowInboxTaskVO.setReviewersComments(fromWorkflowInboxTask.getVo().getReviewersComments());
        toWorkflowInboxTaskVO.setReviewCommentsNeeded(fromWorkflowInboxTask.getVo().getReviewCommentsNeeded());
        toWorkflowInboxTaskVO.setDueDateOffset(fromWorkflowInboxTask.getVo().getDueDateOffset());
        toWorkflowInboxTaskVO.setDateOffsetFrom(fromWorkflowInboxTask.getVo().getDateOffsetFrom());
        toWorkflowInboxTaskVO.setAssigneeSetDueDate(fromWorkflowInboxTask.getVo().getAssigneeSetDueDate());
        toWorkflowInboxTaskVO.setAllowDelegation(fromWorkflowInboxTask.getVo().getAllowDelegation());
        toWorkflowInboxTaskVO.setIsEssential(fromWorkflowInboxTask.getVo().getIsEssential());
        toWorkflowInboxTaskVO.setParallelNodeProcessionRule(fromWorkflowInboxTask.getVo().getParallelNodeProcessionRule());
        toWorkflowInboxTaskVO.setTitles(businessObjectRoot.getCommonTitlesForDisplay());
        toWorkflowInboxTaskVO.setRevision("1");
        toWorkflowInboxTaskVO.setStates(WorkflowConstants.STATES_TYPE_ASSIGNED);//Assigned,Review,Complete
        toWorkflowInboxTaskVO.setTaskOwner(toAssignUser.getNames());
        toWorkflowInboxTaskVO.setDescriptions("");
        toWorkflowInboxTaskVO.setNotifyEmail(fromWorkflowInboxTask.getVo().getNotifyEmail());
        toWorkflowInboxTaskVO.setProcessRole(fromWorkflowInboxTask.getVo().getProcessRole());
        toWorkflowInboxTaskVO.setInboxTaskType(fromWorkflowInboxTask.getVo().getInboxTaskType());

        if(WorkflowConstants.WBS_DATE_OFFSET_FROM.equals(toWorkflowInboxTaskVO.getDateOffsetFrom())
                && toWorkflowInboxTaskVO.getDueDateOffset() > 0) {
           Calendar c = Calendar.getInstance();
           c.setTime(timeService.getDBLocalTime());
           c.add(Calendar.DATE, toWorkflowInboxTaskVO.getDueDateOffset());
           toWorkflowInboxTaskVO.setScheduledCompletionDate(c.getTime());
        }

        WorkflowInboxTask toWorkflowInboxTask = DomUtil.toDom(toWorkflowInboxTaskVO);
        toWorkflowInboxTask.createObject();
       /*
        *  Create WorkflowRouteTask Relation From:WorkflowRoute To:WorkflowInboxTask)
        *  select * from PTKEYTABLE a where a.pclass_name = 'WorkflowRouteTask'
        */
        toWorkflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK, workflowRoute.getVo(), new HashMap<String, Object>());
      
        toWorkflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWWBSACTIVITYTASK, new ProjectPerson(workflowWBSActivityTaskVOFromObid).getVo(), new HashMap<String, Object>());
        
        buildDelegatedInboxTask(businessObjectRoot, workflowRoute, toWorkflowInboxTask);

        //createReassignApprovalInfoToEP(businessObjectRoot, workflowRoute, fromWorkflowInboxTask, toWorkflowInboxTask);
//        throw new ApplicationException("Testing..............");
    }
    
    /**
     * 
     * 
     * @param workflowInboxTask
     * @param approvalStatus
     * @param comments
     * @param isFromWebService
     * @param webServiceFlag
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#txnManualApprove(lgcns.rnd.api.object.workflow.dom.WorkflowInboxTask, java.lang.String, java.lang.String, boolean, java.lang.String)
     */
    public void txnManualApprove(WorkflowInboxTask workflowInboxTask, String approvalStatus, String comments, boolean isFromWebService, String webServiceFlag) {
        // Null Check
        if(NullUtil.isNull(workflowInboxTask) || NullUtil.isNull(approvalStatus) || NullUtil.isNull(comments)){
            throw new IllegalArgumentException();
        }
        
        if(!WorkflowConstants.APPROVAL_STATUS_APPROVE.equals(approvalStatus)) throw new ApplicationException("Only allow approve.");
        
        List<ObjectRootVO> rtnObjectRootVOList = workflowInboxTask.getWorkflowRouteVOAndBusinessObjectRootVO();
        WorkflowRouteVO workflowRouteVO = null;
        ObjectRootVO businessObjectRootVO = null;
        for(ObjectRootVO objectRootVO : rtnObjectRootVOList) {
            switch(objectRootVO.getClassName()) {
                case "WorkflowRoute":
                    workflowRouteVO = (WorkflowRouteVO)objectRootVO;
                    break;
                default:
                    businessObjectRootVO = objectRootVO;
                    break;
            }
        }
        ApprovalVO approvalVO = new ApprovalVO();
        approvalVO.setWorkflowInboxTaskObid(workflowInboxTask.getVo().getObid());                                     // workflowInboxTaskObid      -- returnValue(obid)
        approvalVO.setWorkflowRouteObid(workflowRouteVO.getObid());
        approvalVO.setWorkflowStepNodeUserObid(workflowInboxTask.getVo().getRouteNodeObid());                         // workflowStepNodeUserObid   -- returnValue(routeNodeObid)
        approvalVO.setObid(businessObjectRootVO.getObid());                                                                  // change obid                -- returnValue(outData_docId)
        approvalVO.setStep(Integer.parseInt(workflowInboxTask.getVo().getStep()));                                    // step                       -- returnValue(step)
        approvalVO.setParallelNodeProcessionRule(workflowInboxTask.getVo().getParallelNodeProcessionRule());          // parallelNodeProcessionRule -- returnValue(parallelNodeProcessionRule)
        approvalVO.setApprovalStatus(approvalStatus);
        approvalVO.setComments(comments);

        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
        if( isFromWebService ){
            // Working ???? ???? ??
            if( WorkflowConstants.STATE_WORKING.equals(businessObjectRoot.getVo().getStates()) ){
                // Working? ?? ????? ??
                throw new ApplicationException("Working object.");
            }
            approvalVO.setMobileApproval( webServiceFlag );
        }

        if(NullUtil.isNull(approvalVO.getWorkflowInboxTaskObid()) ||
                NullUtil.isNull(approvalVO.getWorkflowRouteObid()) ||
                NullUtil.isNull(approvalVO.getWorkflowStepNodeUserObid()) ||
                NullUtil.isNull(approvalVO.getObid()) ||
                NullUtil.isNull(approvalVO.getStep()) ||
                NullUtil.isNull(approvalVO.getParallelNodeProcessionRule()) ||
                NullUtil.isNull(approvalVO.getApprovalStatus()) ||
                NullUtil.isNull(approvalVO.getComments())){
            throw new IllegalArgumentException();
        }

        txnSubmitApproval(businessObjectRoot, approvalVO,false);
    }
    
//    /**
//     * 
//     *
//     * @param businessObjectRoot
//     * @param workflowRoute
//     * @param workflowInboxTask
//     */
//    private void createApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute  workflowRoute, WorkflowInboxTask workflowInboxTask) { 
//        ClassInfo classInfo = ClassInfoUtil.getClassInfo(businessObjectRoot.getVo().getClassName());
//        //if(true)return; //TODO migration data 에 creator 가 mail id 로 들어가 있어 일단 주석. 
//        //Build ARD Info
//        EPApprovalVO epApprovalVO = new EPApprovalVO();
//        epApprovalVO.setCommand("ARD"); //system_id,system_pk에 해당하는 결재 정보를 모두 지운다.
//        epApprovalVO.setSystemPk(workflowInboxTask.getVo().getObid());
//        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
//        //commonDao.insert("WORKFLOW.deleteEPApprovalInfoBySystemPk", epApprovalVO);
//        commonDao.insert("WORKFLOW.insertARDEPApprovalInfo", epApprovalVO);
//        
//        //Build ARI Info
//        epApprovalVO = new EPApprovalVO();
//        epApprovalVO.setCommand("ARI"); //기안자가 결재를 요청했을 때 필요한 값
//        epApprovalVO.setSystemPk(workflowInboxTask.getVo().getObid());
//        epApprovalVO.setC1("01");
//        epApprovalVO.setC2(classInfo.getDisplayedName());
////        int businessObjectRootNameLength = businessObjectRoot.getVo().getNames().getBytes().length;
////        epApprovalVO.setC3(businessObjectRoot.getVo().getNames()+" ("+ WorkflowUtil.getMaxByteString(businessObjectRoot.getVo().getTitles(), 48 - businessObjectRootNameLength) +")");
//        epApprovalVO.setC3(WorkflowUtil.getMaxByteString(businessObjectRoot.getCommonTitlesForDisplay(Users.getUserLocale(workflowInboxTask.getVo().getTaskOwner())), 100));
//        epApprovalVO.setC4(null);
//        epApprovalVO.setC5(classInfo.getDisplayedName());
//        
//        
//        /* Migration WBS를 위한 임시 코드 */
//        Users user = Users.getUsersByMailId(businessObjectRoot.getVo().getCreator(),"C");
//        
//        if(user == null ){
//            epApprovalVO.setSabun1(businessObjectRoot.getVo().getCreator());
//        } else {
//            epApprovalVO.setSabun1(user.getVo().getNames());
//        }
//        epApprovalVO.setUrl1(propertiesService.getString("was.homeUrl") + "/epWBSActivityApproval.do?inboxTaskObid=" + workflowInboxTask.getObid());
//        epApprovalVO.setTaskId(workflowRoute.getObid());
//        commonDao.insert("WORKFLOW.insertARIEPApprovalInfo", epApprovalVO);
//        
//        //Build ABY Info
//        epApprovalVO =  new EPApprovalVO();
//        epApprovalVO.setCommand("ABY"); //결재요청 또는 결재 처리후 다음 차수에 결재할 결재자 정보를 넣는다.
//        epApprovalVO.setSystemPk(workflowInboxTask.getObid());
//        epApprovalVO.setC1("01");
//        epApprovalVO.setC2(WorkflowConstants.APPROVAL_STATUS_APPROVE);
//        epApprovalVO.setSabun1(workflowInboxTask.getVo().getTaskOwner());
//        epApprovalVO.setUrl1(propertiesService.getString("was.homeUrl") + "/epWBSActivityApproval.do?inboxTaskObid=" + workflowInboxTask.getObid());
//        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
//        commonDao.insert("WORKFLOW.insertABYEPApprovalInfo", epApprovalVO);
//    }
    
//    /**
//     * 
//     *
//     * @param businessObjectRoot
//     * @param workflowRoute
//     * @param fromWorkflowInboxTask
//     * @param toWorkflowInboxTask
//     */
//    private void createReassignApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute,
//            WorkflowInboxTask fromWorkflowInboxTask, WorkflowInboxTask toWorkflowInboxTask) {
//
//        EPApprovalVO epApprovalVO = new EPApprovalVO();
//        epApprovalVO.setCommand("ARD"); //system_id,system_pk에 해당하는 결재 정보를 모두 지운다.
//        epApprovalVO.setSystemPk(fromWorkflowInboxTask.getVo().getObid());
//        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
//        commonDao.insert("WORKFLOW.insertARDEPApprovalInfo", epApprovalVO);
//
//        createApprovalInfoToEP(businessObjectRoot, workflowRoute, toWorkflowInboxTask);
//    }
    
   /**
    * 
    * 
    * @param workflowInboxTask
    * @return
    * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#retrieveWBSActivityApprovalProcessInformation(lgcns.rnd.api.object.workflow.dom.WorkflowInboxTask)
    */
    public Map<String, String> retrieveWBSActivityApprovalProcessInformation(WorkflowInboxTask workflowInboxTask) {
        List<ObjectRootVO> rtnObjectRootVOList = workflowInboxTask.getWorkflowRouteVOAndBusinessObjectRootVO();
        if(NullUtil.isNone(rtnObjectRootVOList)) throw new ApplicationException("WBS Activity does not exist.");
        Map<String, String> rtnMap = new HashMap<String, String>();
        WorkflowRouteVO workflowRouteVO = null;
        ObjectRootVO businessObjectRootVO = null;
        for(ObjectRootVO objectRootVO : rtnObjectRootVOList) {
            switch(objectRootVO.getClassName()) {
                case "WorkflowRoute":
                    workflowRouteVO = (WorkflowRouteVO)objectRootVO;
                    break;
                default:
                    businessObjectRootVO = objectRootVO;
                    break;
            }
        }
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO);
        if(!(NullUtil.isNone(workflowRouteVO.getActivityUrl()) || workflowRouteVO.getActivityUrl().equals("None"))) {
            Map<String,String> parameterMap = businessObjectRoot.getParameterForWorkflow(workflowInboxTask.getVo());
            for( Map.Entry<String, String> element : parameterMap.entrySet() ){
                rtnMap.put(element.getKey(), element.getValue());
            }
        }
        
        rtnMap.put("lifeCycle", businessObjectRoot.getLifeCycle());
        rtnMap.put("workflowRouteObid", workflowRouteVO.getObid());
        rtnMap.put("activityUrl", workflowRouteVO.getActivityUrl());
        rtnMap.put("wbsActivityObid", businessObjectRootVO.getObid());
        return rtnMap;
    }

    /**
     * 
     * 
     * @param businessObjectRoot
     * @param workflowInboxTask
     * @return
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#validateCompleteWBSActivity(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.api.object.workflow.dom.WorkflowInboxTask)
     */
    @Override
    public List<ActivityValidationResultVO> validateCompleteWBSActivity(BusinessObjectRoot businessObjectRoot, WorkflowInboxTask workflowInboxTask){
        return businessObjectRoot.validateCompleteWBSActivity(workflowInboxTask.getVo());
    }
    
    /**
     */
    public Boolean isLastApproval(BusinessObjectRoot businessObjectRoot, WorkflowInboxTask workflowInboxTask) {
        boolean bResult = false;
        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOList();
        if(NullUtil.isNone(rtnObjectRootVOList)) throw new ApplicationException("Does not exist route.");
        
        List<WorkflowInboxTaskVO> assignedWorkflowInboxTaskVOList = new ArrayList<WorkflowInboxTaskVO>();
        WorkflowRoute workflowRoute = DomUtil.toDom(rtnObjectRootVOList.get(0).getObid(), false);
        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
        for(WorkflowInboxTaskVO workflowInboxTaskVO : rtnWorkflowInboxTaskVOList) {
            if(!NullUtil.isNull(workflowInboxTaskVO.getOriginTaskOwner()) && workflowInboxTaskVO.getOriginTaskOwner().equals(workflowInboxTaskVO.getTaskOwner())) {//위임건 제외
                continue; 
            }
                
            if(workflowInboxTaskVO.getStates().equals(WorkflowConstants.STATES_TYPE_ASSIGNED)) {
                assignedWorkflowInboxTaskVOList.add(workflowInboxTaskVO);
            }
        }
        
        if(NullUtil.isNone(assignedWorkflowInboxTaskVOList)) throw new ApplicationException("No data approval");//결재건이 모두 종료이거나 없을 경우 발생
        
        if(!NullUtil.isNone(workflowInboxTask.getVo().getOriginTaskOwner()) && !workflowInboxTask.getVo().getOriginTaskOwner().equals(workflowInboxTask.getVo().getTaskOwner())) {//위임 결재건
            if(assignedWorkflowInboxTaskVOList.size() == 1 && assignedWorkflowInboxTaskVOList.get(0).getTaskOwner().equals(workflowInboxTask.getVo().getOriginTaskOwner())) {
                bResult = true;
            }else{
                bResult = false; 
            }
        }else{ //일반 결재 및 위임 original
            if(assignedWorkflowInboxTaskVOList.size() == 1 && assignedWorkflowInboxTaskVOList.get(0).getTaskOwner().equals(workflowInboxTask.getVo().getTaskOwner())) {
                bResult = true;
            }else{
                bResult = false; 
            }
        }
        return bResult;
    }
    
    /**
     * WBS Activity Reassign
     * @param reassignVOList
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#txnReassignApproverList(java.util.List)
     */
    public void txnReassignApproverList(List<ReassignVO> reassignVOList) {
        for ( ReassignVO reassignVO : reassignVOList ) {
            if ( NullUtil.isNone(reassignVO.getObid()) ) throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "Obid is null" });
            BusinessObjectRoot businessObjectRoot = DomUtil.toDom(reassignVO.getObid());
            
            txnReassignApprover(businessObjectRoot, reassignVO, true);
        }
    }
    
    /**
     * 해당 Workflow inbox task, 위임건, step node user 및 EP 결재 요청 건 삭제 
     * 
     * @param workflowInboxTask
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#txnDeleteWorkflowInboxTaskNStepNodeUser(lgcns.rnd.api.object.workflow.dom.WorkflowInboxTask)
     */
    public void txnDeleteWorkflowInboxTaskNStepNodeUser(WorkflowInboxTask workflowInboxTask) {
        if(NullUtil.isNull(workflowInboxTask)) throw new IllegalArgumentException("Input workflow inbox task is null");
        
        WorkflowRoute workflowRoute = workflowInboxTask.getWorkflowRoute();
        workflowInboxTask.deleteInboxTask(workflowRoute);
        
        EPApprovalVO epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("ARD"); //system_id,system_pk에 해당하는 결재 정보를 모두 지운다.
        epApprovalVO.setSystemPk(workflowInboxTask.getVo().getObid());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
        //commonDao.insert("WORKFLOW.insertARDEPApprovalInfo", epApprovalVO);
        
    }
    /**
     * 
     * @param wbsItems
     * @param projectScheduleObid
     * @param reassignVOList
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#txnAddApprover(lge.plm.api.object.project.dom.WBSItems, java.lang.String, java.util.List)
     */
    @Override
    public void txnAddApprover(WBSItems wbsItems, String projectScheduleObid, List<ReassignVO> reassignVOList){
        if(reassignVOList.size() > 0){
            List<WBSActivityInboxTaskVO> wbsActivityInboxTaskVOList = new ArrayList<WBSActivityInboxTaskVO>();
            List<BusinessObjectRootVO> projectPersonList = new ArrayList<BusinessObjectRootVO>();
            List<ProjectPersonVO> tempProjectPersonList = new ArrayList<ProjectPersonVO>();
            //List<ProjectPersonVO> tempProjectPersonList = wbsItems.getAssignedProjectPersonList();
            
            for (ReassignVO reassignVO : reassignVOList) {
                String toAssigneeObid = reassignVO.getToAssigneeObid();
                Users users = DomUtil.toDom(toAssigneeObid);
                for(ProjectPersonVO projectPersonVO : tempProjectPersonList){
                    if(projectPersonVO.getUserId().equals(users.getNames())){
                        projectPersonList.add(projectPersonVO);
                        WBSActivityInboxTaskVO vo = new WBSActivityInboxTaskVO();
                        vo.setTaskOwner(projectPersonVO.getUserId());
                        wbsActivityInboxTaskVOList.add(vo);
                        break;
                    }
                }
            }
            WBSActivityWorkflowVO wbsActivityWorkflowVO = new WBSActivityWorkflowVO(wbsItems, projectPersonList, wbsActivityInboxTaskVOList, "Standard", wbsItems.getLifeCycle(), wbsItems.getStates(), wbsItems.getVo().getActivityUrl());
            wbsActivityWorkflowVO.setProcessTimestamp(projectScheduleObid);
            List<ObjectRootVO> workflowRouteVOListByRouteState = wbsItems.getWorkflowRouteVOListByRouteState(wbsItems.getStates());
            WorkflowRoute workflowRoute = null;
            if(workflowRouteVOListByRouteState.size() > 1) throw new ApplicationException("api.object.error.workflow.routeGreaterThenOne");
            if(workflowRouteVOListByRouteState.size() == 1) workflowRoute = DomUtil.toDom(workflowRouteVOListByRouteState.get(0));
            if(workflowRoute == null){
                workflowRoute = createWorkflowRoute(wbsActivityWorkflowVO);
            }
            createWorkflowInboxTaskList(wbsActivityWorkflowVO, workflowRoute);
        }
    }
    /**
     * 
     * @param activityObid
     * @see lgcns.rnd.application.workflow.service.WBSActivityWorkflowService#txnDeleteWorkflowAndCreateDeleteApprovalInfoToEP(java.lang.String)
     */
    @Override
    public void txnDeleteWorkflowAndCreateDeleteApprovalInfoToEP(String activityObid){
        WBSItems wbsItems = DomUtil.toDom(activityObid);
        List<ObjectRootVO> workflowRouteVOList = wbsItems.getWorkflowRouteVOList();
        if(workflowRouteVOList.size() > 0){
            for(ObjectRootVO workflowRouteVO : workflowRouteVOList){
                WorkflowRoute workflowRoute = DomUtil.toDom(workflowRouteVO);
                List<WorkflowInboxTaskVO> workflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
                for (WorkflowInboxTaskVO workflowInboxTaskVO : workflowInboxTaskVOList) {
                    DomUtil.toDom(workflowInboxTaskVO).deleteObject();
                    workflowService.txnCreateDeleteApprovalInfoToEP(workflowRouteVO.getObid(), workflowInboxTaskVO.getObid());
                }
                workflowRoute.deleteObject();
            }
        }
    }
    
    
    
}
