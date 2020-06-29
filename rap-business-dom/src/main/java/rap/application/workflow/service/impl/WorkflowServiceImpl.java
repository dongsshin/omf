/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : WorkflowServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2015. 2. 12.  kwanghyui.choi   Initial
 * */
package rap.application.workflow.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.service.TimeService;
import com.rap.omc.foundation.lifecycle.model.StateInfo;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.CommonDaoUtil;
import com.rap.omc.util.foundation.LifeCycleUtil;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.pdr.dom.PCRProperty;
import rap.api.object.relation.dom.WorkflowObjectRoute;
import rap.api.object.relation.dom.WorkflowRouteStep;
import rap.api.object.relation.dom.WorkflowStepNodeUser;
import rap.api.object.relation.dom.WorkflowSubStep;
import rap.api.object.relation.model.WorkflowStepNodeUserVO;
import rap.api.object.workflow.dom.ApprovalLine;
import rap.api.object.workflow.dom.ApprovalLineState;
import rap.api.object.workflow.dom.EssentialApprover;
import rap.api.object.workflow.dom.EssentialApproverLog;
import rap.api.object.workflow.dom.WorkflowInboxTask;
import rap.api.object.workflow.dom.WorkflowRoute;
import rap.api.object.workflow.dom.WorkflowStep;
import rap.api.object.workflow.model.ApprovalLineStateVO;
import rap.api.object.workflow.model.ApprovalLineVO;
import rap.api.object.workflow.model.EssentialApproverLogVO;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.api.object.workflow.model.WorkflowRouteVO;
import rap.api.object.workflow.model.WorkflowStepVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.WorkflowConstants;
import rap.application.workflow.model.ApprovalHistoryVO;
import rap.application.workflow.model.ApprovalVO;
import rap.application.workflow.model.EPApprovalVO;
import rap.application.workflow.model.MailSendVO;
import rap.application.workflow.model.PendingJobByRetirementSearchVO;
import rap.application.workflow.model.PendingJobByRetirementVO;
import rap.application.workflow.model.ReassignVO;
import rap.application.workflow.service.WorkflowService;
import rap.application.workflow.util.WorkflowChainedComparator;
import rap.application.workflow.util.WorkflowRouteComparator;
import rap.application.workflow.util.WorkflowRouteNodeComparator;
import rap.application.workflow.util.WorkflowRouteStepComparator;
import rap.application.workflow.util.WorkflowUtil;



/**
 * <pre>
 * Class : WorkflowServiceImpl
 * Description : TODO
 * </pre>
 *
 *
 *
 * @author kwanghyui.choi
 */

@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService{
    static final Logger LOGGER = LoggerFactory.getLogger(WorkflowServiceImpl.class);

    @Resource(name = "timeService")
    private TimeService timeService;

    @Resource(name="messageSourceAccessor")
    MessageSourceAccessor messageSourceAccessor;

//    @Resource(name="propertiesService")
//    private ConfigService propertiesService;
//
//    @Resource(name="mailService")
//    MailService mailService;
//    
//    @Resource(name = "pdrEmailService")
//    private PdrEmailService pdrEmailService;
//    
//    @Resource(name = "adjustAwardEmailService")
//    private AdjustAwardEmailService adjustAwardEmailService;
//    
//    @Resource(name = "prodPlanSpecService")
//    private ProdPlanSpecService ppSpecService;
//
//    @Resource(name="prodPlanPrmMgmtService")
//    private ProdPlanPrmMgmtService ppPrmService;
    
    @Autowired
    UserSession userSession;

    /*****************************************************************************************
     *  Public Method
     *****************************************************************************************/
    
    /**
     * 
     * @param businessObjectRoot
     * @param approvalVOList
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnBuildWorkflow1(omc.api.object.dom.BusinessObjectRoot, java.util.List)
     */
    @Override
    public void txnBuildWorkflow(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList){
        List<WorkflowRouteVO> rtnExistsObjectRootVOList = businessObjectRoot.getWorkflowRouteVOList();
        if(!NullUtil.isNone(rtnExistsObjectRootVOList)) {
            businessObjectRoot.getVo().getOutData().put("processTimestamp", rtnExistsObjectRootVOList.get(0).getProcessTimestamp());
        }
        //State(Working, Processing...)별
        Map<String, List<ApprovalVO>> approvalVOListMapByRouteState = new HashMap<String, List<ApprovalVO>>();
        classifyApprovalVOListByRouteState(approvalVOListMapByRouteState, approvalVOList);

        for (Map.Entry<String, List<ApprovalVO>> entry : approvalVOListMapByRouteState.entrySet()) {
            StateInfo stateInfo = LifeCycleUtil.getLifieCycleStateByStateName(businessObjectRoot.getVo().getLifeCycle(),entry.getKey());

            businessObjectRoot.getVo().getOutData().put("stateInfo", stateInfo);
            Map<String, Object> approvalVOListMapByRecodeMode = splitApprovalVOByRecodeMode(approvalVOListMapByRouteState.get(entry.getKey()));

            List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(entry.getKey());
            if(!NullUtil.isNone(rtnObjectRootVOList)) {//해당 Route가 존재 하면
                WorkflowRoute existsWorkflowRoute = DomUtil.toDom(rtnObjectRootVOList.get(0).getObid(), false);
                updateWorkflow( businessObjectRoot, existsWorkflowRoute, approvalVOListMapByRecodeMode);
            }else{//해당 Route가 존재 하지 않으면
                createWorkflowRoute(businessObjectRoot, approvalVOListMapByRecodeMode);
            }
        }//clause while (Working, Processing...
    }
    
    /**
     * 
     * @param businessObjectRoot
     * @param approvalVOList
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnBuildWorkflowAndStartFirstState(omc.api.object.dom.BusinessObjectRoot, java.util.List)
     */
    @Override
    public void txnBuildWorkflowAndStartFirstState(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList){
        StateInfo firstStateInfo = businessObjectRoot.getFirstState();
        validateFirstWorkflowRouteForStart(businessObjectRoot, firstStateInfo);
        
        txnBuildWorkflow(businessObjectRoot, approvalVOList);
        StateInfo stateInfo = businessObjectRoot.getFirstState();
        txnStartRoute(businessObjectRoot, stateInfo.getStateName());
    }
    
    private void validateFirstWorkflowRouteForStart(BusinessObjectRoot businessObjectRoot, StateInfo firstStateInfo) {
        if(!businessObjectRoot.getVo().getStates().equals(firstStateInfo.getStateName())) throw new ApplicationException("api.object.error.workflow.onlyAllowedFirstState");
        
        List<ObjectRootVO> objectRootVOList = businessObjectRoot.getWorkflowRouteVOList();
        for(ObjectRootVO objectRootVO: objectRootVOList) {
            WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO.getObid(), false);
            if(!WorkflowConstants.STATES_TYPE_DEFINE.equals(workflowRoute.getVo().getStates())) throw new ApplicationException("api.object.error.workflow.noBuildAndStart");
        }
    }
    
    /**
     * 
     * @param businessObjectRoot
     * @param approvalVOList
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnBuildWorkflowAndPromote(omc.api.object.dom.BusinessObjectRoot, java.util.List)
     */
    @Override
    public void txnBuildWorkflowAndPromote(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList){
        StateInfo firstStateInfo = businessObjectRoot.getFirstState();
        validateFirstWorkflowRouteForStart(businessObjectRoot, firstStateInfo); 
        txnBuildWorkflow(businessObjectRoot, approvalVOList);
        
        WorkflowRoute firstWorkflowRoute = null;
        ObjectRootVO rtnObjectRootVO = businessObjectRoot.getFirstWorkflowRouteVOList();
        if(NullUtil.isNull(rtnObjectRootVO)) {//첫번째 state의 route만 만들어 줌.
        
            WorkflowRouteVO firstWorkflowRouteVO = new WorkflowRouteVO();
            firstWorkflowRouteVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_ROUTENAME));
            firstWorkflowRouteVO.setRouteCompletionAction(firstStateInfo.getRouteCompleteAction());
            firstWorkflowRouteVO.setDescriptions(businessObjectRoot.getVo().getObid()+"_"+businessObjectRoot.getVo().getNames()+"_"+firstStateInfo.getStateName());
            firstWorkflowRouteVO.setRestartUpOnTaskRejection((WorkflowConstants.TRUE.equals(firstStateInfo.getRouteAutoStartOnReject())?true:false));
            firstWorkflowRouteVO.setAutoStopOnRejection(firstStateInfo.getRouteHowToOnReject()); //Immediate , Deferred
            firstWorkflowRouteVO.setRouteBasePurpose(firstStateInfo.getDefaultRoutePurpose());
            firstWorkflowRouteVO.setStates(WorkflowConstants.STATES_TYPE_DEFINE);
            firstWorkflowRouteVO.setTitles(firstWorkflowRouteVO.getNames());
            firstWorkflowRouteVO.setOrganizations(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.plantUnit, "None"));
            firstWorkflowRoute = DomUtil.toDom(firstWorkflowRouteVO);
            firstWorkflowRoute.createObject();

            //Create WorkflowObjectRoute
            Map<String, Object> attributeMap = new HashMap<String, Object>();
            attributeMap.put("routePurpose", firstStateInfo.getDefaultRoutePurpose());
            attributeMap.put("routeLifeCycle", businessObjectRoot.getVo().getLifeCycle());
            attributeMap.put("routeState", firstStateInfo.getStateName());
            firstWorkflowRoute.createRelationWorkflowObjectRoute(businessObjectRoot, attributeMap);
            firstWorkflowRoute = DomUtil.toDom(businessObjectRoot.getFirstWorkflowRouteVOList());
        }else{
            firstWorkflowRoute = DomUtil.toDom(rtnObjectRootVO);
        }
        
        WorkflowStepVO firstWorkflowStepVO = firstWorkflowRoute.getFirstStep();
        if(!NullUtil.isNull(firstWorkflowStepVO)) {
            WorkflowStep firstWorkflowStep = DomUtil.toDom(firstWorkflowStepVO);
            List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList = firstWorkflowStep.getWorkflowStepNodeUserVOList();
            if(!NullUtil.isNone(rtnBusinessRelationObjectVOList)) throw new ApplicationException("api.object.error.workflow.noAutoPromote"); //결재 대상이 존재 하면 auto promote 안되게 해야 함.
        }
        
        List<ApprovalVO> creatorApprovalVOList = new ArrayList<ApprovalVO>();
        ApprovalVO toBeCreateApprovalVO = new ApprovalVO();
        toBeCreateApprovalVO.setAssigneeObid(Users.getUsers(businessObjectRoot.getVo().getCreator()).getVo().getObid());
        toBeCreateApprovalVO.setState(firstStateInfo.getStateName());
        toBeCreateApprovalVO.setParallelNodeProcessionRule(WorkflowConstants.ROUTE_ACTION_ALL);
        toBeCreateApprovalVO.setStep(1);
        toBeCreateApprovalVO.setSequence(0);
        toBeCreateApprovalVO.setRecordMode("C");
        toBeCreateApprovalVO.setEssential("Y");
        creatorApprovalVOList.add(toBeCreateApprovalVO);
        
        txnBuildWorkflow(businessObjectRoot, creatorApprovalVOList); //Creator inbox task 생성 
        txnStartRoute(businessObjectRoot, firstStateInfo.getStateName());
        
        ApprovalVO autoApproveVO = new ApprovalVO();
        List<WorkflowInboxTaskVO> createdWorkflowInboxTaskVOList = firstWorkflowRoute.getWorkflowInboxTaskVOList();
        for(WorkflowInboxTaskVO workflowInboxTaskVO : createdWorkflowInboxTaskVOList) {
            if(!workflowInboxTaskVO.getStep().equals("1")) continue;
            
            if(workflowInboxTaskVO.getStates().equals(WorkflowConstants.STATES_TYPE_ASSIGNED)) {
                WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
                if(toBeCreateApprovalVO.getAssigneeObid().equals(workflowInboxTask.getUsersVO().getObid())) {
                    autoApproveVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_APPROVE);
                    autoApproveVO.setComments("Automatically approved.");
                    autoApproveVO.setWorkflowInboxTaskObid(workflowInboxTaskVO.getObid());
                    autoApproveVO.setWorkflowStepNodeUserObid(workflowInboxTaskVO.getRouteNodeObid());
                    autoApproveVO.setWorkflowRouteObid(firstWorkflowRoute.getVo().getObid());
                    autoApproveVO.setStep(Integer.parseInt(workflowInboxTaskVO.getStep()));
                    autoApproveVO.setParallelNodeProcessionRule(workflowInboxTaskVO.getParallelNodeProcessionRule());
                    txnSubmitApproval(businessObjectRoot, autoApproveVO);
                    break;
                }
            }
        }
//        throw new ApplicationException("");
    }
    
    /**
     * 
     * @param businessObjectRoot
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#isStartedFirstState(omc.api.object.dom.BusinessObjectRoot)
     */
    @Override
    public boolean isStartedFirstState(BusinessObjectRoot businessObjectRoot){
        boolean isStarted = false;
        StateInfo firstState = businessObjectRoot.getFirstState();
        List<ObjectRootVO> objectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(firstState.getStateName());
        if(!NullUtil.isNone(objectRootVOList)) {
            WorkflowRoute firstWorkflowRoute = DomUtil.toDom(objectRootVOList.get(0).getObid(), false);
            if(WorkflowConstants.STATES_TYPE_INPROCESS.equals( firstWorkflowRoute.getVo().getStates())) isStarted = true;
        }
        return isStarted;
    }
    
    @Override
    public boolean isInProcessingWorkflow(BusinessObjectRoot businessObjectRoot){
        boolean isInProcessing = false;
        List<ObjectRootVO> objectRootVOList = businessObjectRoot.getWorkflowRouteVOList();
        
        if(NullUtil.isNone(objectRootVOList)) return isInProcessing;
        for(ObjectRootVO objectRootVO:  objectRootVOList) {  
            WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO.getObid(), false);
            if(WorkflowConstants.STATES_TYPE_INPROCESS.equals( workflowRoute.getVo().getStates())) {
                isInProcessing = true;
                break;
            }
        }
        return isInProcessing;
    }
    
//    private void txnSubmitSelfApprove(BusinessObjectRoot businessObjectRoot) { 
//        String state = businessObjectRoot.getVo().getStates();
//        List<ObjectRootVO> objectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(state);
//        
//        WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVOList.get(0));
//        List<WorkflowInboxTaskVO> workflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
//        
//        UsersVO creatorVO = Users.getUsers(businessObjectRoot.getVo().getCreator()).getVo();
//        
//        for(WorkflowInboxTaskVO workflowInboxTaskVO : workflowInboxTaskVOList) {
//            if(workflowInboxTaskVO.getStates().equals(WorkflowConstants.STATES_TYPE_ASSIGNED)) {
//                WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
//                if(workflowInboxTask.getUsersVO().getObid().equals(creatorVO.getObid())) {
//                    throw new ApplicationException("api.object.error.workflow.sameApprover", new Object[] {creatorVO.getTitles()});
//                }
//            }
//        }
//        List<ApprovalVO> approvalVOList = new ArrayList<ApprovalVO>();
//        ApprovalVO toBeCreateApprovalVO = new ApprovalVO();
//        toBeCreateApprovalVO.setAssigneeObid(creatorVO.getObid());
//        toBeCreateApprovalVO.setState(businessObjectRoot.getVo().getStates());
//        toBeCreateApprovalVO.setParallelNodeProcessionRule(WorkflowConstants.ROUTE_ACTION_ALL);
//        toBeCreateApprovalVO.setStep(1);
//        toBeCreateApprovalVO.setSequence(0);
//        toBeCreateApprovalVO.setRouteInstructions("A");
//        toBeCreateApprovalVO.setRecordMode("C");
//        approvalVOList.add(toBeCreateApprovalVO);
//        
//        txnUpdateWorkflow(businessObjectRoot, approvalVOList); //Creator inbox task 생성
//        
//        ApprovalVO approveVO = new ApprovalVO();
//        workflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
//        for(WorkflowInboxTaskVO workflowInboxTaskVO : workflowInboxTaskVOList) {
//            if(workflowInboxTaskVO.getStates().equals(WorkflowConstants.STATES_TYPE_ASSIGNED)) {
//                WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
//                if(toBeCreateApprovalVO.getAssigneeObid().equals(workflowInboxTask.getUsersVO().getObid())) {
//                    approveVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_REJECT);
//                    approveVO.setComments("Automatically approve");
//                    approveVO.setWorkflowInboxTaskObid(workflowInboxTaskVO.getObid());
//                    approveVO.setWorkflowStepNodeUserObid(workflowInboxTaskVO.getRouteNodeObid());
//                    approveVO.setWorkflowRouteObid(workflowRoute.getVo().getObid());
//                    approveVO.setStep(Integer.parseInt(workflowInboxTaskVO.getStep()));
//                    approveVO.setParallelNodeProcessionRule(workflowInboxTaskVO.getParallelNodeProcessionRule());
//                    txnSubmitApproval(businessObjectRoot, approveVO);
//                    break;
//                }
//            }
//        }
//    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param approvalVOList
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnUpdateWorkflow(omc.api.object.dom.BusinessObjectRoot, java.util.List)
     */
    @Override
    public void txnUpdateWorkflow(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList) {
        List<ObjectRootVO> rtnExistsObjectRootVOList = businessObjectRoot.getWorkflowRouteVOList();
        if(!NullUtil.isNone(rtnExistsObjectRootVOList)) {
            WorkflowRoute workflowRoute = DomUtil.toDom(rtnExistsObjectRootVOList.get(0).getObid(), false);
            businessObjectRoot.getVo().getOutData().put("processTimestamp", workflowRoute.getVo().getProcessTimestamp());
        }
        //State(Working, Processing...)별
        Map<String, List<ApprovalVO>> approvalVOListMapByRouteState = new HashMap<String, List<ApprovalVO>>();
        classifyApprovalVOListByRouteState(approvalVOListMapByRouteState, approvalVOList);

        for (Map.Entry<String, List<ApprovalVO>> entry : approvalVOListMapByRouteState.entrySet()) {
            StateInfo stateInfo =
                    LifeCycleUtil.getLifieCycleStateByStateName(businessObjectRoot.getVo().getLifeCycle(), entry.getKey());

            businessObjectRoot.getVo().getOutData().put("stateInfo", stateInfo);
            Map<String, Object> approvalVOListMapByRecodeMode = splitApprovalVOByRecodeMode(approvalVOListMapByRouteState.get(entry.getKey()));

            List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(entry.getKey());
            if(rtnObjectRootVOList.size() > 0) {
                WorkflowRoute existsWorkflowRoute = DomUtil.toDom(rtnObjectRootVOList.get(0).getObid(), false);
                updateWorkflow( businessObjectRoot, existsWorkflowRoute, approvalVOListMapByRecodeMode);
            }else{
                createWorkflowRoute(businessObjectRoot, approvalVOListMapByRecodeMode);
            }
        }//clause while (Working, Processing...
        
        rebuildStepNodeUserAndInboxTaskWithDelegated(businessObjectRoot); //routePurpose가 Standard 및 Distribution을 위해
    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param toBeStartRouteStates
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnStartRoute(omc.api.object.dom.BusinessObjectRoot, java.lang.String)
     */
    @Override
    public void txnStartRoute(BusinessObjectRoot businessObjectRoot, String toBeStartState) {
        StateInfo stateInfo = LifeCycleUtil.getLifieCycleStateByStateName(businessObjectRoot.getVo().getLifeCycle(), toBeStartState);
        if(NullUtil.isNull(stateInfo)) throw new ApplicationException("api.object.error.workflow.noDefineRoute",  new Object[] { toBeStartState });
        
        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(toBeStartState);
        if(WorkflowConstants.ROUTE_PURPOSE_DISTRIBUTION.equals(stateInfo.getDefaultRoutePurpose())) {
            if(NullUtil.isNone(rtnObjectRootVOList)) return;
        }else{
            if(NullUtil.isNone(rtnObjectRootVOList)) throw new ApplicationException("api.object.error.workflow.routeNotExist",  new Object[] { toBeStartState });
        }
            
        if(rtnObjectRootVOList.size() > 1) throw new ApplicationException("api.object.error.workflow.routeGreaterThenOne");
        
        /**
         * First Route가 시작 될 경우 timestamp를 구해서 입력 해 줌.  
         */
        if(businessObjectRoot.getFirstState().getStateName().equals(toBeStartState)) {
            String processTimestamp = WorkflowUtil.getRandomTimestamp();
            List<ObjectRootVO> rtnExistsObjectRootVOList = businessObjectRoot.getWorkflowRouteVOList();
            for(ObjectRootVO objectRootVO : rtnExistsObjectRootVOList){
                WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO.getObid(), false);
                workflowRoute.getVo().setProcessTimestamp(processTimestamp);
                workflowRoute.modifyObject();
            }
        }

        WorkflowRoute toBeStartworkflowRoute = DomUtil.toDom(rtnObjectRootVOList.get(0).getObid());
        startRoute(businessObjectRoot, toBeStartworkflowRoute);
    }
    

    /**
     *
     *
     * @param businessObjectRoot
     * @param approvalVOList
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnAddDistribution(omc.api.object.dom.BusinessObjectRoot, java.util.List)
     */
    @Override
    public void txnAddDistribution(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList) {
        if(NullUtil.isNull(businessObjectRoot) || NullUtil.isNone(approvalVOList)) throw new IllegalArgumentException();
        List<ApprovalVO> toSendMailApprovalList = new ArrayList<ApprovalVO>();
        List<String> toUserIdList = new ArrayList<String>();
        for(ApprovalVO approvalVO : approvalVOList) {
            if(NullUtil.isNone(approvalVO.getAssigneeObid())){
                throw new IllegalArgumentException();
            }
            if(NullUtil.isNone(approvalVO.getRouteInstructions())){
                throw new IllegalArgumentException();
            }
            if(NullUtil.isNull(approvalVO.getStep())){
                throw new IllegalArgumentException();
            }
            if(NullUtil.isNone(approvalVO.getState())){
                throw new IllegalArgumentException();
            }
            if(NullUtil.isNone(approvalVO.getParallelNodeProcessionRule())){
                throw new IllegalArgumentException();
            }
            approvalVO.setRecordMode(GlobalConstants.CREATE_RECORD_MODE);
            toUserIdList.add( approvalVO.getAssigneeNames() );
            toSendMailApprovalList.add(approvalVO);
        }
        WorkflowRoute workflowRoute = null;
        List<WorkflowStepNodeUser> redistributeList = new ArrayList<WorkflowStepNodeUser>();
        List<ObjectRootVO> rtnObjectVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(approvalVOList.get(0).getState());
        if(!NullUtil.isNone(rtnObjectVOList)) {
            workflowRoute = DomUtil.toDom(rtnObjectVOList.get(0).getObid(), false);
            WorkflowStepVO rtnWorkflowStepVO = workflowRoute.getFirstWorkflowStepVO();
            if(!NullUtil.isNull(rtnWorkflowStepVO)) {
                WorkflowStep workflowStep = DomUtil.toDom(rtnWorkflowStepVO);
                List<BusinessRelationObjectVO> rntBusinessRelationObjectVOList = workflowStep.getWorkflowStepNodeUserVOList();
                for(BusinessRelationObjectVO businessRelationObjectVO : rntBusinessRelationObjectVOList) {
                    for(int idx = approvalVOList.size() - 1; idx >=  0; idx --) {
                        if(businessRelationObjectVO.getToObid().equals(approvalVOList.get(idx).getAssigneeObid())) {
                            WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
                            redistributeList.add(workflowStepNodeUser);
                            approvalVOList.remove(idx);
                        }
                    }
                }
            }
        }

        if(!NullUtil.isNone(approvalVOList)) {
            txnUpdateWorkflow(businessObjectRoot, approvalVOList);
        }

        for(WorkflowStepNodeUser workflowStepNodeUser : redistributeList) {
            WorkflowInboxTaskVO workflowInboxTaskVO =
                    workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUserVO(workflowStepNodeUser.getVo());
            if(!NullUtil.isNull(workflowInboxTaskVO) && WorkflowConstants.STATES_TYPE_INPROCESS.equals(workflowRoute.getVo().getStates())) {
                WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
                if(WorkflowConstants.STATES_TYPE_ASSIGNED.equals(workflowInboxTaskVO.getStates())) {
                    workflowInboxTask.getVo().setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_ACKNOWLEDGE);
                    workflowInboxTask.getVo().setComments("Auto Acknowledge by System for distribute.");
                    workflowInboxTask.getVo().setActualCompletionDate(timeService.getDBLocalTime());
                    workflowInboxTask.modifyObject();

                    workflowInboxTask.promote();
                    workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO.getObid());
                    workflowInboxTask.promote();
                    workflowRoute.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASKHISTORY, workflowInboxTaskVO, new HashMap<String, Object>());

//                    createSubmitApprovalInfoToEP(businessObjectRoot, workflowRoute, workflowStepNodeUser.getVo(), WorkflowConstants.APPROVAL_STATUS_APPROVE);
                }
                BusinessObjectVO revisedBusinessObjectVO = workflowInboxTask.revise();
                //TODO revised 된 Inbox task 초기화 시켜야 함. WorkflowStepNodeUser의 정보도 반영 되어야 함.
                WorkflowInboxTask revisedWorkflowInboxTask = DomUtil.toDom(revisedBusinessObjectVO.getObid(), false);
                
                revisedWorkflowInboxTask.reset(workflowRoute.getVo().getProcessTimestamp(), timeService.getDBLocalTime());
//                createApprovalInfoToEP(businessObjectRoot, workflowRoute, revisedWorkflowInboxTask);
            }
        }

        // ManualDistribution의 경우 대상 Object가 배포 상태일 경우 메일발송
        if( toSendMailApprovalList.get(0).getRouteInstructions().equals( WorkflowConstants.INSTRUCTION_TYPE_MANUAL_DISTRIBUTION ) ){
            BusinessObjectRootVO businessObjectVO = businessObjectRoot.getVo();
            if( businessObjectVO.getStates().equals( toSendMailApprovalList.get(0).getState() ) ){
                MailSendVO mailSendVO = new MailSendVO();
                mailSendVO.setSendType( WorkflowConstants.MAIL_TYPE_DISTRIBUTION );
                mailSendVO.setObid( businessObjectVO.getObid() );
                mailSendVO.setClassName( businessObjectVO.getClassName() );
                mailSendVO.setNames( businessObjectVO.getNames() );
                mailSendVO.setCurrentStatus( businessObjectVO.getStates() );
                mailSendVO.setFromUserId( userSession.getUserId() );
                mailSendVO.setToUserIdList( toUserIdList );

                txnCreateMailSendInfo( mailSendVO );
            }
        }
    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param approvalVO
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnSubmitApproval(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.application.workflow.model.ApprovalVO)
     */
    @Override
    public void txnSubmitApproval(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO) {
        Map<String, String> inputParams = new HashMap<String, String>();
        inputParams.put("approvalStatus", approvalVO.getApprovalStatus());
        inputParams.put("obidOfWorkflowInboxTask", approvalVO.getWorkflowInboxTaskObid());
        inputParams.put("comments", approvalVO.getComments());
        inputParams.put("mobileApproval", approvalVO.getMobileApproval());
        inputParams.put("rejectCode", approvalVO.getRejectCode());
        inputParams.put("signal", approvalVO.getSignal());
        businessObjectRoot.preProcessApproval(inputParams);
       
        if(WorkflowConstants.APPROVAL_STATUS_REJECT.equals(approvalVO.getApprovalStatus()) && businessObjectRoot.getBranchTo().equals(WorkflowConstants.APPROVAL_STATUS_NONE)) {
            Class<?>[] paramType = new Class<?>[1];
            paramType[0] = String.class;
            try {
                Method method = businessObjectRoot.getVo().getClass().getMethod("setBranchTo", paramType);
                method.invoke(businessObjectRoot.getVo(), approvalVO.getApprovalStatus());
            } catch (NoSuchMethodException e) {
                throw new ApplicationException("api.object.error.workflow.noSuchMethod");
            } catch (SecurityException e) {
                throw new ApplicationException("api.object.error.workflow.security");
            } catch (IllegalAccessException e) {
                throw new ApplicationException("api.object.error.workflow.illegalAccess");
            } catch (IllegalArgumentException e) {
                throw new ApplicationException("api.object.error.workflow.illegalArgument");
            } catch (InvocationTargetException e) {
                throw new ApplicationException("api.object.error.workflow.invocationTarget");
            }
        }
        
        WorkflowRoute workflowRoute = DomUtil.toDom(approvalVO.getWorkflowRouteObid());
        /*
         * 결재 대상 nodeuser 및 inboxtask
         */
        WorkflowStepNodeUser toBeApprovalWorkflowStepNodeUser = null;
        WorkflowInboxTask toBeApprovalWorkflowInboxTask = DomUtil.toDom(approvalVO.getWorkflowInboxTaskObid(), false);
        
        if(NullUtil.isNone(toBeApprovalWorkflowInboxTask.getVo().getOriginTaskOwner())) {//결재 대상이 위임건이 아닌 경우 일반 결재 형식으로 하고 다만 Reject 일 경우 위임으로 인해 생성 된 inboxtask는 삭제 되어야 한다. 
            if(!WorkflowConstants.STATES_TYPE_ASSIGNED.equals(toBeApprovalWorkflowInboxTask.getVo().getStates())) throw new ApplicationException("No subject to approval.");
            toBeApprovalWorkflowStepNodeUser = DomUtil.toDom(toBeApprovalWorkflowInboxTask.getVo().getRouteNodeObid(), false);
            toBeApprovalWorkflowStepNodeUser.getVo().setComments(approvalVO.getComments());
            toBeApprovalWorkflowStepNodeUser.getVo().setApprovalStatus(approvalVO.getApprovalStatus());
            toBeApprovalWorkflowStepNodeUser.getVo().setActualCompletionDate(timeService.getDBLocalTime());
            toBeApprovalWorkflowStepNodeUser.modifyObject();
            
            toBeApprovalWorkflowInboxTask.getVo().setActualCompletionDate(toBeApprovalWorkflowStepNodeUser.getVo().getActualCompletionDate());
            toBeApprovalWorkflowInboxTask.getVo().setComments(toBeApprovalWorkflowStepNodeUser.getVo().getComments());
            toBeApprovalWorkflowInboxTask.getVo().setApprovalStatus(toBeApprovalWorkflowStepNodeUser.getVo().getApprovalStatus());
            toBeApprovalWorkflowInboxTask.getVo().setMobileApproval(approvalVO.getMobileApproval());
            toBeApprovalWorkflowInboxTask.modifyObject();
            toBeApprovalWorkflowInboxTask.promote();
            toBeApprovalWorkflowInboxTask.promote();

            if(approvalVO.getApprovalStatus().equals(WorkflowConstants.APPROVAL_STATUS_REJECT)) {
                List<WorkflowInboxTaskVO> workflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
                for(WorkflowInboxTaskVO inboxTaskVO : workflowInboxTaskVOList) {
                    if(NullUtil.isNone(inboxTaskVO.getOriginTaskOwner())) continue;
                    
                    if(WorkflowConstants.STATES_TYPE_ASSIGNED.equals(inboxTaskVO.getStates()) && toBeApprovalWorkflowInboxTask.getVo().getStep().equals(inboxTaskVO.getStep())) {
                        if(inboxTaskVO.getOriginTaskOwner().equals(inboxTaskVO.getTaskOwner())) { //origin inboxTask
                            inboxTaskVO.setOriginTaskOwner(null);
                            WorkflowInboxTask inboxTask = DomUtil.toDom(inboxTaskVO) ;
                            inboxTask.modifyObject();
                        }else{
                            WorkflowInboxTask inboxTask = DomUtil.toDom(inboxTaskVO);
                            inboxTask.deleteInboxTask(workflowRoute);
                        }  
                    }
                }
            }
                
            //Create workflowRouteTaskHistory
            workflowRoute.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASKHISTORY, toBeApprovalWorkflowInboxTask.getVo(), new HashMap<String, Object>());
        }else{//위임에 의해 생성 된 inbox task도 처리
            String orgineTaskOwner = toBeApprovalWorkflowInboxTask.getVo().getOriginTaskOwner();
            String step = toBeApprovalWorkflowInboxTask.getVo().getStep();
            List<WorkflowInboxTaskVO> workflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
            for(WorkflowInboxTaskVO inboxTaskVO : workflowInboxTaskVOList) {
                if(NullUtil.isNone(inboxTaskVO.getOriginTaskOwner())
                   || !orgineTaskOwner.equals(inboxTaskVO.getOriginTaskOwner())
                   || !step.equals(inboxTaskVO.getStep())
                   || !WorkflowConstants.STATES_TYPE_ASSIGNED.equals(inboxTaskVO.getStates())) continue;
                
                if(inboxTaskVO.getOriginTaskOwner().equals(inboxTaskVO.getTaskOwner())) { //origin inboxTask
                    toBeApprovalWorkflowStepNodeUser = DomUtil.toDom(inboxTaskVO.getRouteNodeObid(), false);
                    UsersVO usersVo = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_USERS, ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, ""));
                    toBeApprovalWorkflowStepNodeUser.getVo().setComments(approvalVO.getComments() + " by "+  usersVo.getTitles()+" [Delegate]" );
                    toBeApprovalWorkflowStepNodeUser.getVo().setApprovalStatus(approvalVO.getApprovalStatus());
                    toBeApprovalWorkflowStepNodeUser.getVo().setActualCompletionDate(timeService.getDBLocalTime());
                    toBeApprovalWorkflowStepNodeUser.modifyObject();
                    
                    if(!WorkflowConstants.STATES_TYPE_ASSIGNED.equals(inboxTaskVO.getStates())) throw new ApplicationException("No subject to approval.");
                    inboxTaskVO.setOriginTaskOwner(null);
                    inboxTaskVO.setActualCompletionDate(toBeApprovalWorkflowStepNodeUser.getVo().getActualCompletionDate());
                    inboxTaskVO.setComments(toBeApprovalWorkflowStepNodeUser.getVo().getComments());
                    inboxTaskVO.setApprovalStatus(toBeApprovalWorkflowStepNodeUser.getVo().getApprovalStatus());
                    inboxTaskVO.setMobileApproval(approvalVO.getMobileApproval());
                    WorkflowInboxTask originWorkflowInboxTask = DomUtil.toDom(inboxTaskVO);
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
            submitApprove(businessObjectRoot, workflowRoute, toBeApprovalWorkflowStepNodeUser.getVo());
        }else if(WorkflowConstants.APPROVAL_STATUS_REJECT.equals(approvalVO.getApprovalStatus())) {
            submitReject(businessObjectRoot, workflowRoute, toBeApprovalWorkflowStepNodeUser.getVo());
        }else if(WorkflowConstants.APPROVAL_STATUS_ACKNOWLEDGE.equals(approvalVO.getApprovalStatus())) {
            submitAcknowledge(businessObjectRoot, workflowRoute, toBeApprovalWorkflowStepNodeUser.getVo());
        }else{
            throw new ApplicationException("Unknown approval status");  
        }
    }
    
    /**
     * 
     * 
     * @param businessObjectRoot
     * @param approvalVO
     * @param toUpdateApprovalList
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnSubmitApprovalBySimple(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.application.workflow.model.ApprovalVO, java.util.List)
     */
    @Override
    public void txnSubmitApprovalBySimple(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO, List<ApprovalVO> toUpdateApprovalList) {
        if(!NullUtil.isNone(toUpdateApprovalList)) txnUpdateWorkflow(businessObjectRoot, toUpdateApprovalList);
        txnSubmitApproval(businessObjectRoot, approvalVO);
//        throw new ApplicationException("");
    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param routeState
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnDoReject(omc.api.object.dom.BusinessObjectRoot, java.lang.String)
     */
    @Override
    public void txnReject(BusinessObjectRoot businessObjectRoot, String routeState) {
        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(routeState);
        WorkflowRoute workflowRoute = null;
        if(!NullUtil.isNone(rtnObjectRootVOList)) {
            workflowRoute = DomUtil.toDom(rtnObjectRootVOList.get(0).getObid(), false);
        }
        submitReject(businessObjectRoot, workflowRoute, null);
    }
    
    /**
     *
     *
     * @param businessObjectRoot
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveWorkflow(omc.api.object.dom.BusinessObjectRoot)
     */
    @Override
    public List<ApprovalVO> retrieveWorkflow(BusinessObjectRoot businessObjectRoot) {
        if(NullUtil.isNull(businessObjectRoot)) throw new IllegalArgumentException();
        List<ApprovalVO> rtnApprovalVOList = new ArrayList<ApprovalVO>();
        List<String> routeStateList = LifeCycleUtil.getLifeCycleStateStringListByName(businessObjectRoot.getVo().getLifeCycle());
        StringBuffer relationPatternSb = new StringBuffer();
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE);
        relationPatternSb.append(",");
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP);
        relationPatternSb.append(",");
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWSUBSTEP);
        relationPatternSb.append(",");
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWSTEPNODEUSER);

        StringBuffer filterPatternSb = new StringBuffer();
        filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE);
        filterPatternSb.append(",");
        filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_WORKFLOWSTEP);
        filterPatternSb.append(",");
        filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_USERS);

        List<ObjectRootVO> rtnObjectRootVOList =
                businessObjectRoot.getRelatedObjects(
                                            relationPatternSb.toString(),
                                            filterPatternSb.toString(),
                                            GlobalConstants.FLAG_TYPE_TO,
                                            null,           // selectPattern
                                            null,           // wherePattern
                                            null,           // parameterPattern
                                            false,          // bInclude
                                            false,          // bResultUnique
                                            0,              // objectLimit
                                            50);            // findDepth
        
        /*
         * 
         */
        Map<String, WorkflowRoute> workflowRouteVOMap = new HashMap<String, WorkflowRoute>();
        Map<String, WorkflowStepVO> workflowStepVOMap = new HashMap<String, WorkflowStepVO>();
        List<UsersVO> usersVOList = new ArrayList<UsersVO>();
        for(ObjectRootVO objectRootVO : rtnObjectRootVOList) {
            switch(objectRootVO.getClassName()) {
                case "WorkflowRoute":
                    WorkflowRouteVO workflowRouteVO = (WorkflowRouteVO)objectRootVO;
                    WorkflowRoute workflowRoute = DomUtil.toDom(workflowRouteVO);
                    workflowRouteVOMap.put(workflowRouteVO.getUniqueString(), workflowRoute);
                    break;
                case "WorkflowStep":
                    WorkflowStepVO workflowStepVO = (WorkflowStepVO)objectRootVO;
                    workflowStepVOMap.put(workflowStepVO.getUniqueString(), workflowStepVO);
                    break;
                case "Users":
                    usersVOList.add((UsersVO)objectRootVO);
                    break;
            }
        }
        
        String loginUser = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, null);
        for (UsersVO usersVO : usersVOList) {
            ApprovalVO approvalVO = new ApprovalVO();
            Map<String, Object> usersOutDataMap = usersVO.getOutData();
            WorkflowStepVO workflowStepVO = workflowStepVOMap.get(usersVO.getUniqueStringParent());//UsersVO의 uniqueStringParent로 WorkflowSetpVO를 찾음
            WorkflowRoute workflowRoute = null;
            if(workflowStepVO.getOutData().get("rel_fromClass").equals(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE)) { //WorkflowStepVO의 uniqueStringParent로 WorkflowRoute를 찾음
                workflowRoute = workflowRouteVOMap.get(workflowStepVO.getUniqueStringParent()); 
            }else{
                WorkflowStepVO tragetWorkflowStepVO = workflowStepVO;
                boolean bFlag = true;
                while(bFlag) {
                    tragetWorkflowStepVO = workflowStepVOMap.get(tragetWorkflowStepVO.getUniqueStringParent());
                    if(tragetWorkflowStepVO.getOutData().get("rel_fromClass").equals(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE)) {
                        workflowRoute = workflowRouteVOMap.get(tragetWorkflowStepVO.getUniqueStringParent());  
                        bFlag = false;
                    }
                }
            }
            
           WorkflowInboxTaskVO workflowInboxTaskVO =
                   workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUser((String)usersOutDataMap.get("rel_routeNodeObid"));
            
            approvalVO.setRouteAction((String)usersOutDataMap.get("rel_routeAction"));
            approvalVO.setRouteInstructions((String)usersOutDataMap.get("rel_routeInstructions"));
            approvalVO.setNotifyEmail(WorkflowConstants.TRUE.equals(usersOutDataMap.get("rel_notifyEmail")) ? true : false );
            approvalVO.setProcessRole(NullUtil.isNull(usersOutDataMap.get("rel_processRole")) ? "" : (String)usersOutDataMap.get("rel_processRole") );
            
            if(NullUtil.isNull(workflowInboxTaskVO)) {
                approvalVO.setReviewApproveComments((String)usersOutDataMap.get("rel_reviewersComments"));
                approvalVO.setComments((String)usersOutDataMap.get("rel_comments"));
                String rel_approvalStatus = (String)usersOutDataMap.get("rel_approvalStatus");
                approvalVO.setApprovalStatus(rel_approvalStatus.equals(WorkflowConstants.APPROVAL_STATUS_NONE) ? "" : rel_approvalStatus);
                
                if(StringUtils.isEmpty(approvalVO.getApprovalStatus()) && WorkflowConstants.APPROVAL_STATUS_APPROVE.equals(approvalVO.getApprovalStatus())) {
                    try {
                        approvalVO.setActualCompletionDate((String)usersOutDataMap.get("rel_actualCompletionDate"));
                    } catch (ParseException e) {}
                }
            }else{
                List<FilesVO> filesVOList = new WorkflowInboxTask(workflowInboxTaskVO).getReleatedFiles();
                approvalVO.setCheckFilesExist(NullUtil.isNone(filesVOList) ? false : true);
                approvalVO.setWorkflowInboxTaskObid(workflowInboxTaskVO.getObid());
                approvalVO.setReviewApproveComments(workflowInboxTaskVO.getReviewersComments());
                approvalVO.setComments(workflowInboxTaskVO.getComments());
                approvalVO.setActualCompletionDate(workflowInboxTaskVO.getActualCompletionDate());
                approvalVO.setApprovalStatus(StringUtils.isEmpty(workflowInboxTaskVO.getApprovalStatus()) ? "" : workflowInboxTaskVO.getApprovalStatus());
                approvalVO.setInboxTaskState(workflowInboxTaskVO.getStates());
                approvalVO.setOriginTaskOwner(workflowInboxTaskVO.getOriginTaskOwner());
                approvalVO.setTaskOwner(workflowInboxTaskVO.getTaskOwner());
            }
            if(WorkflowConstants.ROUTE_ACTIONS_COMMENT.equals(approvalVO.getRouteAction())) {//Distribution
                setActionForDistributionState(approvalVO);
            }else{
                setAction(approvalVO);
            }
            approvalVO.setWorkflowRouteObid(workflowRoute.getVo().getObid());
            approvalVO.setRoutePurpose(workflowRoute.getVo().getRouteBasePurpose());
            approvalVO.setRouteStateSequence(routeStateList.indexOf((String)workflowRoute.getVo().getOutData().get("rel_routeState")));
            approvalVO.setTitles((String)usersOutDataMap.get("rel_titles"));
            approvalVO.setUserStatus(usersVO.getStates());
            approvalVO.setDepartment(usersVO.getHrDepartmentKor());
            approvalVO.setAssignee(usersVO.getTitles());
            approvalVO.setAssigneeEmailAddress(usersVO.getEmailAddress());
            String titleName = StringUtils.isEmpty(usersVO.getTitleName()) ? "" : usersVO.getTitleName();
            approvalVO.setApproverInfo(usersVO.getDescriptions() + " "+ titleName + " (" + usersVO.getMailId() + ") "+ usersVO.getHrDepartmentKor());
            approvalVO.setAssigneeObid(usersVO.getObid());
            approvalVO.setAssigneeNames(usersVO.getNames());
            if(loginUser.equals(usersVO.getNames())) {
                approvalVO.setAvailableToApproval(true);
            }
            approvalVO.setState((String)workflowRoute.getVo().getOutData().get("rel_routeState"));
            approvalVO.setStateOfWorkflowStep(workflowStepVO.getStates());
            
            if(WorkflowConstants.INSTRUCTION_TYPE_STANDARD.equals(approvalVO.getRoutePurpose())) {//Standard
                approvalVO.setDummyStep("");
            }else if(WorkflowConstants.INSTRUCTION_TYPE_DISTRIBUTION.equals(approvalVO.getRoutePurpose())) {//Distribution
                approvalVO.setDummyStep(approvalVO.getRoutePurpose());
            }else{
                approvalVO.setDummyStep(Integer.toString(workflowStepVO.getSequences()));
            }
            
            approvalVO.setStep(workflowStepVO.getSequences());
            approvalVO.setDueDate((String)usersOutDataMap.get("rel_assigneeSetDueDate"));
            approvalVO.setIsEssential(("FALSE".equals((String)usersOutDataMap.get("rel_isEssential")) ? false : true));
            approvalVO.setEssential((approvalVO.getIsEssential()) ? "Y" : "");
            approvalVO.setParallelNodeProcessionRule((String)usersOutDataMap.get("rel_parallelNodeProcessionRule"));
            approvalVO.setActionCommnets((String)usersOutDataMap.get("rel_actionComments"));
            approvalVO.setSequence((Integer)usersOutDataMap.get("rel_sequences"));//rel_sequences
            approvalVO.setWorkflowStepNodeUserObid((String)usersOutDataMap.get("rel_routeNodeObid"));
            rtnApprovalVOList.add(approvalVO);
            
            if(!NullUtil.isNull(workflowInboxTaskVO)) {
                List<WorkflowInboxTaskVO> delegatedWorkflowInboxTaskVOList = workflowRoute.getDelegatedWorkflowInboxtTaskList(workflowInboxTaskVO);
                for(WorkflowInboxTaskVO delegatedWorkflowInboxTaskVO : delegatedWorkflowInboxTaskVOList) {
                    ApprovalVO delegatedApprovalVO = new ApprovalVO();
                    delegatedApprovalVO.setRouteAction(delegatedWorkflowInboxTaskVO.getRouteAction());
                    delegatedApprovalVO.setRouteInstructions(delegatedWorkflowInboxTaskVO.getRouteInstructions());
                    delegatedApprovalVO.setNotifyEmail(delegatedWorkflowInboxTaskVO.getNotifyEmail());
                    delegatedApprovalVO.setProcessRole(delegatedWorkflowInboxTaskVO.getProcessRole() );
                    delegatedApprovalVO.setWorkflowInboxTaskObid(delegatedWorkflowInboxTaskVO.getObid());
                    delegatedApprovalVO.setReviewApproveComments(delegatedWorkflowInboxTaskVO.getReviewersComments());
                    delegatedApprovalVO.setComments(delegatedWorkflowInboxTaskVO.getComments());
                    delegatedApprovalVO.setActualCompletionDate(delegatedWorkflowInboxTaskVO.getActualCompletionDate());
                    delegatedApprovalVO.setApprovalStatus(StringUtils.isEmpty(delegatedWorkflowInboxTaskVO.getApprovalStatus()) ? "" : delegatedWorkflowInboxTaskVO.getApprovalStatus());
                    delegatedApprovalVO.setInboxTaskState(delegatedWorkflowInboxTaskVO.getStates());
                    delegatedApprovalVO.setOriginTaskOwner(delegatedWorkflowInboxTaskVO.getOriginTaskOwner());
                    delegatedApprovalVO.setTaskOwner(delegatedWorkflowInboxTaskVO.getTaskOwner());
                    if(WorkflowConstants.ROUTE_ACTIONS_COMMENT.equals(delegatedApprovalVO.getRouteAction())) {//Distribution
                        setActionForDistributionState(delegatedApprovalVO);
                    }else{
                        setAction(delegatedApprovalVO);
                    }
                    
                    delegatedApprovalVO.setWorkflowRouteObid(workflowRoute.getVo().getObid());
                    delegatedApprovalVO.setRoutePurpose(workflowRoute.getVo().getRouteBasePurpose());
                    delegatedApprovalVO.setRouteStateSequence(routeStateList.indexOf((String)workflowRoute.getVo().getOutData().get("rel_routeState")));
                    delegatedApprovalVO.setTitles(delegatedWorkflowInboxTaskVO.getTitles());
                    
                    WorkflowInboxTask delegatedWorkflowInboxTask = DomUtil.toDom(delegatedWorkflowInboxTaskVO);
                    UsersVO delegatedUsersVO = delegatedWorkflowInboxTask.getUsersVO();
                    delegatedApprovalVO.setUserStatus(delegatedUsersVO.getStates());
                    delegatedApprovalVO.setDepartment(delegatedUsersVO.getHrDepartmentKor());
                    delegatedApprovalVO.setAssignee(delegatedUsersVO.getTitles());
                    delegatedApprovalVO.setAssigneeObid(delegatedUsersVO.getObid());
                    delegatedApprovalVO.setAssigneeNames(delegatedUsersVO.getNames());
                    titleName = StringUtils.isEmpty(delegatedUsersVO.getTitleName()) ? "" : delegatedUsersVO.getTitleName();
                    delegatedApprovalVO.setApproverInfo(delegatedUsersVO.getDescriptions() + " "+ titleName + " "+ delegatedUsersVO.getHrDepartmentKor());
                    if(loginUser.equals(delegatedUsersVO.getNames())) {
                        delegatedApprovalVO.setAvailableToApproval(true);
                    }
                    delegatedApprovalVO.setState((String)workflowRoute.getVo().getOutData().get("rel_routeState"));
                    delegatedApprovalVO.setStateOfWorkflowStep(workflowStepVO.getStates());
                    if(WorkflowConstants.INSTRUCTION_TYPE_STANDARD.equals(delegatedApprovalVO.getRoutePurpose())) {//Standard
                        delegatedApprovalVO.setDummyStep("");
                    }else if(WorkflowConstants.INSTRUCTION_TYPE_DISTRIBUTION.equals(delegatedApprovalVO.getRoutePurpose())) {//Distribution
                        delegatedApprovalVO.setDummyStep(delegatedApprovalVO.getRoutePurpose());
                    }else{
                        delegatedApprovalVO.setDummyStep(Integer.toString(workflowStepVO.getSequences()));
                    }
                    
                    delegatedApprovalVO.setStep(workflowStepVO.getSequences());
                    delegatedApprovalVO.setSequence(delegatedWorkflowInboxTaskVO.getSequences());
                    delegatedApprovalVO.setIsEssential(delegatedWorkflowInboxTaskVO.getIsEssential());
                    delegatedApprovalVO.setEssential((delegatedWorkflowInboxTaskVO.getIsEssential()) ? "Y" : "");
                    delegatedApprovalVO.setParallelNodeProcessionRule(delegatedWorkflowInboxTaskVO.getParallelNodeProcessionRule());
                    delegatedApprovalVO.setActionCommnets(delegatedWorkflowInboxTaskVO.getActionComments());
                    rtnApprovalVOList.add(delegatedApprovalVO);
                }
            }
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
     * 
     * @param businessObjectRoot
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveWorkflowForMobile(omc.api.object.dom.BusinessObjectRoot)
     */
    @Override
    public List<ApprovalVO> retrieveWorkflowForMobile(BusinessObjectRoot businessObjectRoot) {
       if(NullUtil.isNull(businessObjectRoot)) throw new IllegalArgumentException();
       List<ApprovalVO> rtnApprovalVOList = new ArrayList<ApprovalVO>();
       List<String> routeStateList = LifeCycleUtil.getLifeCycleStateStringListByName(businessObjectRoot.getVo().getLifeCycle());
       StringBuffer relationPatternSb = new StringBuffer();
       relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE);
       relationPatternSb.append(",");
       relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP);
       relationPatternSb.append(",");
       relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWSUBSTEP);
       relationPatternSb.append(",");
       relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWSTEPNODEUSER);

       StringBuffer filterPatternSb = new StringBuffer();
       filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE);
       filterPatternSb.append(",");
       filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_WORKFLOWSTEP);
       filterPatternSb.append(",");
       filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_USERS);

       List<ObjectRootVO> rtnObjectRootVOList =
               businessObjectRoot.getRelatedObjects(
                                           relationPatternSb.toString(),
                                           filterPatternSb.toString(),
                                           GlobalConstants.FLAG_TYPE_TO,
                                           null,           // selectPattern
                                           null,           // wherePattern
                                           null,           // parameterPattern
                                           false,          // bInclude
                                           false,          // bResultUnique
                                           0,              // objectLimit
                                           50);            // findDepth
       
       /*
        * 
        */
       Map<String, WorkflowRoute> workflowRouteVOMap = new HashMap<String, WorkflowRoute>();
       Map<String, WorkflowStepVO> workflowStepVOMap = new HashMap<String, WorkflowStepVO>();
       List<UsersVO> usersVOList = new ArrayList<UsersVO>();
       for(ObjectRootVO objectRootVO : rtnObjectRootVOList) {
           switch(objectRootVO.getClassName()) {
               case "WorkflowRoute":
                   WorkflowRouteVO workflowRouteVO = (WorkflowRouteVO)objectRootVO;
                   WorkflowRoute workflowRoute = DomUtil.toDom(workflowRouteVO);
                   workflowRouteVOMap.put(workflowRouteVO.getUniqueString(), workflowRoute);
                   break;
               case "WorkflowStep":
                   WorkflowStepVO workflowStepVO = (WorkflowStepVO)objectRootVO;
                   workflowStepVOMap.put(workflowStepVO.getUniqueString(), workflowStepVO);
                   break;
               case "Users":
                   usersVOList.add((UsersVO)objectRootVO);
                   break;
           }
       }
       
       String loginUser = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, null);
       for (UsersVO usersVO : usersVOList) {
           Map<String, Object> usersOutDataMap = usersVO.getOutData();
           ApprovalVO approvalVO = new ApprovalVO();
           WorkflowStepVO workflowStepVO = workflowStepVOMap.get(usersVO.getUniqueStringParent());//UsersVO의 uniqueStringParent로 WorkflowSetpVO를 찾음
           WorkflowRoute workflowRoute = null;
           if(workflowStepVO.getOutData().get("rel_fromClass").equals(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE)) { //WorkflowStepVO의 uniqueStringParent로 WorkflowRoute를 찾음
               workflowRoute = workflowRouteVOMap.get(workflowStepVO.getUniqueStringParent());
               
               if(WorkflowConstants.ROUTE_PURPOSE_STANDARD.equals(workflowRoute.getVo().getRouteBasePurpose())) {
                   continue;
               }else if(WorkflowConstants.ROUTE_PURPOSE_DISTRIBUTION.equals(workflowRoute.getVo().getRouteBasePurpose())) {
                   continue;
               }
           }else{
               WorkflowStepVO tragetWorkflowStepVO = workflowStepVO;
               boolean bFlag = true;
               while(bFlag) {
                   tragetWorkflowStepVO = workflowStepVOMap.get(tragetWorkflowStepVO.getUniqueStringParent());
                   if(tragetWorkflowStepVO.getOutData().get("rel_fromClass").equals(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE)) {
                       workflowRoute = workflowRouteVOMap.get(tragetWorkflowStepVO.getUniqueStringParent());  
                       bFlag = false;
                   }
               }
           }
           
           WorkflowInboxTaskVO workflowInboxTaskVO =
                   workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUser((String)usersOutDataMap.get("rel_routeNodeObid"));
                   
           approvalVO.setRouteAction((String)usersOutDataMap.get("rel_routeAction"));
           approvalVO.setRouteInstructions((String)usersOutDataMap.get("rel_routeInstructions"));
           approvalVO.setNotifyEmail(WorkflowConstants.TRUE.equals(usersOutDataMap.get("rel_notifyEmail")) ? true : false );
           approvalVO.setProcessRole(NullUtil.isNull(usersOutDataMap.get("rel_processRole")) ? "" : (String)usersOutDataMap.get("rel_processRole") );
           
           if(NullUtil.isNull(workflowInboxTaskVO)) {
               approvalVO.setReviewApproveComments((String)usersOutDataMap.get("rel_reviewersComments"));
               approvalVO.setComments((String)usersOutDataMap.get("rel_comments"));
               String rel_approvalStatus = (String)usersOutDataMap.get("rel_approvalStatus");
               approvalVO.setApprovalStatus(rel_approvalStatus.equals(WorkflowConstants.APPROVAL_STATUS_NONE)? "" : rel_approvalStatus);
               
               if(null != approvalVO.getApprovalStatus() && WorkflowConstants.APPROVAL_STATUS_APPROVE.equals(approvalVO.getApprovalStatus())) {
                   try {
                       approvalVO.setActualCompletionDate((String)usersOutDataMap.get("rel_actualCompletionDate"));
                   } catch (ParseException e) {}
               }
           }else{
               List<FilesVO> filesVOList = new WorkflowInboxTask(workflowInboxTaskVO).getReleatedFiles();
               approvalVO.setCheckFilesExist(NullUtil.isNone(filesVOList) ? false : true);
               approvalVO.setWorkflowInboxTaskObid(workflowInboxTaskVO.getObid());
               approvalVO.setReviewApproveComments(workflowInboxTaskVO.getReviewersComments());
               approvalVO.setComments(workflowInboxTaskVO.getComments());
               approvalVO.setActualCompletionDate(workflowInboxTaskVO.getActualCompletionDate());
               approvalVO.setApprovalStatus(StringUtils.isEmpty(workflowInboxTaskVO.getApprovalStatus()) ? "" : workflowInboxTaskVO.getApprovalStatus());
               approvalVO.setInboxTaskState(workflowInboxTaskVO.getStates());
               approvalVO.setOriginTaskOwner(workflowInboxTaskVO.getOriginTaskOwner());
               approvalVO.setTaskOwner(workflowInboxTaskVO.getTaskOwner());
           }
           
           setAction(approvalVO);
           approvalVO.setWorkflowRouteObid(workflowRoute.getVo().getObid());
           approvalVO.setRoutePurpose(workflowRoute.getVo().getRouteBasePurpose());
           approvalVO.setRouteStateSequence(routeStateList.indexOf((String)workflowRoute.getVo().getOutData().get("rel_routeState")));
           approvalVO.setTitles((String)usersOutDataMap.get("rel_titles"));
           approvalVO.setUserStatus(usersVO.getStates());
           approvalVO.setDepartment(usersVO.getHrDepartmentKor());
           approvalVO.setAssignee(usersVO.getTitles());
           approvalVO.setAssigneeEmailAddress(usersVO.getEmailAddress());
           String titleName = StringUtils.isEmpty(usersVO.getTitleName()) ? "" : usersVO.getTitleName();
           approvalVO.setApproverInfo(usersVO.getDescriptions() + " "+ titleName + " (" + usersVO.getMailId() + ") "+ usersVO.getHrDepartmentKor());
           approvalVO.setAssigneeObid(usersVO.getObid());
           approvalVO.setAssigneeNames(usersVO.getNames());
           if(loginUser.equals(usersVO.getNames())) {
               approvalVO.setAvailableToApproval(true);
           }
           approvalVO.setState((String)workflowRoute.getVo().getOutData().get("rel_routeState"));
           approvalVO.setStateOfWorkflowStep(workflowStepVO.getStates());
           
           if(WorkflowConstants.INSTRUCTION_TYPE_STANDARD.equals(approvalVO.getRoutePurpose())) {//Standard
               approvalVO.setDummyStep("");
           }else if(WorkflowConstants.INSTRUCTION_TYPE_DISTRIBUTION.equals(approvalVO.getRoutePurpose())) {//Distribution
               approvalVO.setDummyStep(approvalVO.getRoutePurpose());
           }else{
               approvalVO.setDummyStep(Integer.toString(workflowStepVO.getSequences()));
           }
           
           approvalVO.setStep(workflowStepVO.getSequences());
           approvalVO.setDueDate((String)usersOutDataMap.get("rel_assigneeSetDueDate"));
           approvalVO.setIsEssential(("FALSE".equals((String)usersOutDataMap.get("rel_isEssential")) ? false : true));
           approvalVO.setEssential((approvalVO.getIsEssential()) ? "Y" : "");
           approvalVO.setParallelNodeProcessionRule((String)usersOutDataMap.get("rel_parallelNodeProcessionRule"));
           approvalVO.setActionCommnets((String)usersOutDataMap.get("rel_actionComments"));
           approvalVO.setSequence((Integer)usersOutDataMap.get("rel_sequences"));//rel_sequences
           approvalVO.setWorkflowStepNodeUserObid((String)usersOutDataMap.get("rel_routeNodeObid"));
           rtnApprovalVOList.add(approvalVO);
           
           if(!NullUtil.isNull(workflowInboxTaskVO)) {
               List<WorkflowInboxTaskVO> delegatedWorkflowInboxTaskVOList = workflowRoute.getDelegatedWorkflowInboxtTaskList(workflowInboxTaskVO);
               for(WorkflowInboxTaskVO delegatedWorkflowInboxTaskVO : delegatedWorkflowInboxTaskVOList) {
                   ApprovalVO delegatedApprovalVO = new ApprovalVO();
                   delegatedApprovalVO.setRouteAction(delegatedWorkflowInboxTaskVO.getRouteAction());
                   delegatedApprovalVO.setRouteInstructions(delegatedWorkflowInboxTaskVO.getRouteInstructions());
                   delegatedApprovalVO.setNotifyEmail(delegatedWorkflowInboxTaskVO.getNotifyEmail());
                   delegatedApprovalVO.setProcessRole(delegatedWorkflowInboxTaskVO.getProcessRole() );
                   delegatedApprovalVO.setWorkflowInboxTaskObid(delegatedWorkflowInboxTaskVO.getObid());
                   delegatedApprovalVO.setReviewApproveComments(delegatedWorkflowInboxTaskVO.getReviewersComments());
                   delegatedApprovalVO.setComments(delegatedWorkflowInboxTaskVO.getComments());
                   delegatedApprovalVO.setActualCompletionDate(delegatedWorkflowInboxTaskVO.getActualCompletionDate());
                   delegatedApprovalVO.setApprovalStatus(StringUtils.isEmpty(delegatedWorkflowInboxTaskVO.getApprovalStatus()) ? "" : delegatedWorkflowInboxTaskVO.getApprovalStatus());
                   delegatedApprovalVO.setInboxTaskState(delegatedWorkflowInboxTaskVO.getStates());
                   delegatedApprovalVO.setOriginTaskOwner(delegatedWorkflowInboxTaskVO.getOriginTaskOwner());
                   delegatedApprovalVO.setTaskOwner(delegatedWorkflowInboxTaskVO.getTaskOwner());
                   if(WorkflowConstants.ROUTE_ACTIONS_COMMENT.equals(delegatedApprovalVO.getRouteAction())) {//Distribution
                       setActionForDistributionState(delegatedApprovalVO);
                   }else{
                       setAction(delegatedApprovalVO);
                   }
                   
                   delegatedApprovalVO.setWorkflowRouteObid(workflowRoute.getVo().getObid());
                   delegatedApprovalVO.setRoutePurpose(workflowRoute.getVo().getRouteBasePurpose());
                   delegatedApprovalVO.setRouteStateSequence(routeStateList.indexOf((String)workflowRoute.getVo().getOutData().get("rel_routeState")));
                   delegatedApprovalVO.setTitles(delegatedWorkflowInboxTaskVO.getTitles());
                   
                   WorkflowInboxTask delegatedWorkflowInboxTask = DomUtil.toDom(delegatedWorkflowInboxTaskVO);
                   UsersVO delegatedUsersVO = delegatedWorkflowInboxTask.getUsersVO();
                   delegatedApprovalVO.setUserStatus(delegatedUsersVO.getStates());
                   delegatedApprovalVO.setDepartment(delegatedUsersVO.getHrDepartmentKor());
                   delegatedApprovalVO.setAssignee(delegatedUsersVO.getTitles());
                   delegatedApprovalVO.setAssigneeObid(delegatedUsersVO.getObid());
                   delegatedApprovalVO.setAssigneeNames(delegatedUsersVO.getNames());
                   if(loginUser.equals(delegatedUsersVO.getNames())) {
                       delegatedApprovalVO.setAvailableToApproval(true);
                   }
                   delegatedApprovalVO.setState((String)workflowRoute.getVo().getOutData().get("rel_routeState"));
                   delegatedApprovalVO.setStateOfWorkflowStep(workflowStepVO.getStates());
                   if(WorkflowConstants.INSTRUCTION_TYPE_STANDARD.equals(delegatedApprovalVO.getRoutePurpose())) {//Standard
                       delegatedApprovalVO.setDummyStep("");
                   }else if(WorkflowConstants.INSTRUCTION_TYPE_DISTRIBUTION.equals(delegatedApprovalVO.getRoutePurpose())) {//Distribution
                       delegatedApprovalVO.setDummyStep(delegatedApprovalVO.getRoutePurpose());
                   }else{
                       delegatedApprovalVO.setDummyStep(Integer.toString(workflowStepVO.getSequences()));
                   }
                   
                   delegatedApprovalVO.setStep(workflowStepVO.getSequences());
                   delegatedApprovalVO.setSequence(delegatedWorkflowInboxTaskVO.getSequences());
                   delegatedApprovalVO.setIsEssential(delegatedWorkflowInboxTaskVO.getIsEssential());
                   delegatedApprovalVO.setEssential((delegatedWorkflowInboxTaskVO.getIsEssential()) ? "Y" : "");
                   delegatedApprovalVO.setParallelNodeProcessionRule(delegatedWorkflowInboxTaskVO.getParallelNodeProcessionRule());
                   delegatedApprovalVO.setActionCommnets(delegatedWorkflowInboxTaskVO.getActionComments());
                   rtnApprovalVOList.add(delegatedApprovalVO);
               }
           }
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
     *
     * @param businessObjectRoot
     * @param routeStates
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveWorkflowStepNodeUserVONUsersList(omc.api.object.dom.BusinessObjectRoot, java.lang.String)
     */
    @Override
    public List<WorkflowStepNodeUserVO> retrieveWorkflowStepNodeUserVONUsersList(BusinessObjectRoot businessObjectRoot, String routeStates) {
        List<WorkflowStepNodeUserVO> rtnWorkflowStepNodeUserVOList = new ArrayList<WorkflowStepNodeUserVO>();
        if(NullUtil.isNull(businessObjectRoot)){
            throw new IllegalArgumentException();
        }
        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(routeStates);
        if(!NullUtil.isNone(rtnObjectRootVOList)) {
            ObjectRootVO objectRootVO = rtnObjectRootVOList.get(0);
            WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO);
            List<WorkflowStepVO> rtnWorkflowStepVOList = workflowRoute.getAllWorkflowStepVOList();
            for(WorkflowStepVO workflowStepVO : rtnWorkflowStepVOList) {
               WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
               List<BusinessRelationObjectVO> rtnBusinessRealtionObjectVOList = workflowStep.getWorkflowStepNodeUserVOList();
               for(BusinessRelationObjectVO businessRelationObjectVO : rtnBusinessRealtionObjectVOList) {
                   WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO);
                   rtnWorkflowStepNodeUserVOList.add(workflowStepNodeUser.getVo());
               }
            }
        }
        return rtnWorkflowStepNodeUserVOList;
    }

    @Override
    public List<WorkflowStepNodeUserVO> retrieveWorkflowStepNodeUserVONUsersListByStep(
                                    BusinessObjectRoot businessObjectRoot, String states, int step) {
        List<WorkflowStepNodeUserVO> rtnWorkflowStepNodeUserVOList = new ArrayList<WorkflowStepNodeUserVO>();
        if(NullUtil.isNull(businessObjectRoot)){
            throw new IllegalArgumentException();
        }
        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(states);
        if(!NullUtil.isNone(rtnObjectRootVOList)) {
            ObjectRootVO objectRootVO = rtnObjectRootVOList.get(0);
            WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO);
            List<WorkflowStepVO> rtnWorkflowStepVOList = workflowRoute.getAllWorkflowStepVOList();
            for(WorkflowStepVO workflowStepVO : rtnWorkflowStepVOList) {
               if((int)workflowStepVO.getSequences() == step){
                   WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
                   List<BusinessRelationObjectVO> rtnBusinessRealtionObjectVOList = workflowStep.getWorkflowStepNodeUserVOList();
                   for(BusinessRelationObjectVO businessRelationObjectVO : rtnBusinessRealtionObjectVOList) {
                       WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO);
                       rtnWorkflowStepNodeUserVOList.add(workflowStepNodeUser.getVo());
                   }
               }
            }
        }
        return rtnWorkflowStepNodeUserVOList;
    }

    @Override
    public boolean isExistWorkflowStepNodeUser(BusinessObjectRoot businessObjectRoot, String states, int step, String userName){
        List<WorkflowStepNodeUserVO> nodeUser = retrieveWorkflowStepNodeUserVONUsersListByStep(businessObjectRoot, states,step);

        String userObid = Users.getUsers(userName).getVo().getObid();

        for(WorkflowStepNodeUserVO workflowStepNodeUserVO : nodeUser){
            if(workflowStepNodeUserVO.getToObid().equals(userObid)){
                return true;
            }
        }
        return false;
    }
    /**
     *
     *
     * @param businessObjectRoot
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnDoCancelWorkflow(omc.api.object.dom.BusinessObjectRoot)
     */
//    @Override
//    public void txnDoCancelWorkflow(BusinessObjectRoot businessObjectRoot) {
//        if(NullUtil.isNull(businessObjectRoot)){
//            throw new IllegalArgumentException();
//        }
//        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(businessObjectRoot.getVo().getStates());
//        if(NullUtil.isNone(rtnObjectRootVOList)){
//            return;
//        }
//
//        ObjectRootVO objectRootVO = rtnObjectRootVOList.get(0);
//        WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO.getObid(), false);
//        if(!WorkflowConstants.STATES_TYPE_INPROCESS.equals(workflowRoute.getVo().getStates()))
//            throw new ApplicationException("api.object.error.workflow.notInProcess", new Object[] { businessObjectRoot.getVo().getStates() });
//        
//        workflowRoute.reset();
//        workflowRoute.getVo().setRouteStatus(WorkflowConstants.ROUTESTATUS_TYPE_STOPPED);
//        workflowRoute.modifyObject();
//        workflowRoute.resetStepList();
//
//        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
//        for(WorkflowInboxTaskVO workflowInboxTaskVO : rtnWorkflowInboxTaskVOList) {
//            if(WorkflowConstants.STATES_TYPE_ASSIGNED.equals(workflowInboxTaskVO.getStates())) {
//                WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
//                workflowInboxTask.deleteInboxTask();
//            }
//        }

//        WorkflowObjectRoute workflowObjectRoute = null;
//        List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList =
//                workflowRoute.getRelationship(
//                                                ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE,
//                                                businessObjectRoot.getVo().getClassName(),
//                                                GlobalConstants.FLAG_TYPE_FROM
//                                              );
//        if(!NullUtil.isNone(rtnBusinessRelationObjectVOList)) {
//            workflowObjectRoute = DomUtil.toDom(rtnBusinessRelationObjectVOList.get(0).getObid(), false);
//        }

//            sendDeleteOutLGEPApprovalInfo(businessObjectRoot, workflowRoute);
//        }

//        if(businessObjectRoot.isKindOf(ApplicationSchemaConstants.BIZCLASS_SOFTWAREREQUESTS) && businessObjectRoot.getVo().getStates().equals("Working") ){
//            sendDeleteOutLGEPApprovalInfo(businessObjectRoot, workflowRoute);
//        }
//    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param routeStates
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveWorkflowInboxTaskVONUsersVOList(omc.api.object.dom.BusinessObjectRoot, java.lang.String)
     */
    @Override
    public List<WorkflowInboxTaskVO> retrieveWorkflowInboxTaskVONUsersVOList(BusinessObjectRoot businessObjectRoot, String routeState) {
        if(NullUtil.isNull(businessObjectRoot)) throw new IllegalArgumentException();
        
        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(routeState);
        if(NullUtil.isNone(rtnObjectRootVOList)) throw new ApplicationException("api.object.error.workflow.routeNotExist",  new Object[] { routeState });
        
        ObjectRootVO objectRootVO = rtnObjectRootVOList.get(0);
        WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO);
        return workflowRoute.getWorkflowInboxTaskVONUsersVOList();
    }

    /**
     *
     *
     * @param businessObjectRootVO
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveApprovalHistoryList(omc.api.object.model.BusinessObjectRootVO)
     */
    @Override
    public List<ApprovalHistoryVO> retrieveApprovalHistoryList(BusinessObjectRootVO businessObjectRootVO){
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
//        List<String> routeStateList = LifeCycleControl.retrieveUserInputLifiecycleStateStringListByName(businessObjectRoot.getVo().getLifeCycle());

        StringBuffer sql = new StringBuffer();
        sql.append("@WOR.[*]+@WFR.[*]+@WTH.[*]+@WIT.[*]+From[Files].obid wit_fileExistYn+SortBy@WTH.[created] asc ");

        String selectPattern = sql.toString();
        sql.setLength(0);   // string buffer 초기화
        sql.append("<this>ThisConnectedWithFrom<[WorkflowObjectRoute]@WOR>+");
        sql.append("<[WorkflowObjectRoute]@WOR>ToConnectedWithThis<[WorkflowRoute]@WFR>+");
        sql.append("<[WorkflowRoute]@WFR>ThisConnectedWithFrom<[WorkflowRouteTaskHistory]@WTH>+");
        sql.append("<[WorkflowRouteTaskHistory]@WTH>ToConnectedWithThis<[WorkflowInboxTask]@WIT>+");

        String fromPattern = sql.toString();
        sql.setLength(0);   // string buffer 초기화

        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, businessObjectRootVO.getObid() );

        String wherePattern = wherePatternBuf.toString();
        String parameterPattern = paramPatternBuf.toString();

        List<ObjectRootVO> rtnWorkflowHistoryList = ObjectRoot.searchObjects( businessObjectRoot.getVo().getClassName(),
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                selectPattern, fromPattern, wherePattern, parameterPattern, true, 0
        );
        
        List<ApprovalHistoryVO> rtnApprovalHistoryVOList = new ArrayList<ApprovalHistoryVO>();
        for(ObjectRootVO objectRootVO : rtnWorkflowHistoryList) {
            Map<String, Object> outData = (Map<String, Object>)objectRootVO.getOutData();
            ApprovalHistoryVO approvalHistoryVO = new ApprovalHistoryVO();
            approvalHistoryVO.setAssignee(NullUtil.isNull(outData.get("witTaskownerUserTitles"))? "" : (String)outData.get("witTaskownerUserTitles"));
            approvalHistoryVO.setAssigneeObid(NullUtil.isNull(outData.get("witTaskOwnerUserObid"))? "" : (String)outData.get("witTaskOwnerUserObid"));
            Users users = Users.getUsers((String)outData.get("wit_taskOwner"));
            String titleName = StringUtils.isEmpty(users.getVo().getTitleName()) ? "" : users.getVo().getTitleName();
            approvalHistoryVO.setApproverInfo(users.getVo().getDescriptions() + " "+ titleName + " "+ users.getVo().getHrDepartmentKor());
            approvalHistoryVO.setSite((String)outData.get("wfr_site"));
            approvalHistoryVO.setState((String)outData.get("wor_routeState"));
            approvalHistoryVO.setApprovalStatus((String)outData.get("wit_approvalStatus"));
            setActionForApprovalHistory(approvalHistoryVO);
            approvalHistoryVO.setComments((String)outData.get("wit_comments"));
            String actualCompletionDate = (NullUtil.isNull(outData.get("wit_actualCompletionDate"))? "" : (String)outData.get("wit_actualCompletionDate"));
            approvalHistoryVO.setActualCompletionDate(actualCompletionDate);
            approvalHistoryVO.setRouteInstructions((String)outData.get("wit_routeInstructions"));
            approvalHistoryVO.setStep((String)outData.get("wit_step"));
            approvalHistoryVO.setActionComments((String)outData.get("wit_actionComments"));
            approvalHistoryVO.setTaskName((String)outData.get("wit_names"));
            approvalHistoryVO.setRev((String)outData.get("wit_revision"));
            approvalHistoryVO.setTaskObid((String)outData.get("wit_obid"));
            approvalHistoryVO.setFileExistYn((String)outData.get("wit_fileExistYn"));
            approvalHistoryVO.setProcessRole((String)outData.get("wit_processRole"));
            rtnApprovalHistoryVOList.add(approvalHistoryVO);
        }
        return rtnApprovalHistoryVOList;
    }
    
    /**
    *
    *
    * @param businessObjectRootVO
    * @return
    * @see lgcns.rnd.application.workflow.service.WorkflowService#retrievePreviousApprovalHistoryList(omc.api.object.model.BusinessObjectRootVO)
    */
   @Override
   public List<ApprovalHistoryVO> retrievePreviousRejectedHistory(BusinessObjectRootVO businessObjectRootVO){
       BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
       List<ApprovalHistoryVO> rtnApprovalHistoryVOList = new ArrayList<ApprovalHistoryVO>();
       
//       if("ChangeProcess".equals(businessObjectRoot.getClassName())){
//           ChangeProcess changeProcessDom = (ChangeProcess) businessObjectRoot;
//           StringBuffer selectPattern = new StringBuffer();
//           StringBuffer wherePattern = new StringBuffer();
//           StringBuffer paramPattern = new StringBuffer();
//           
//           SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstants.DEFAULT_DATE_FORMAT);
//           
//           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, businessObjectRootVO.getObid());
//           StringUtil.constructWherePattern(wherePattern, paramPattern, "DATE_FORMAT(@this.[modified], '%Y-%m-%d')", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, sdf.format(changeProcessDom.getVo().getModified()));
//           StringUtil.addSortByPattern(selectPattern, "@this.[modified] desc");
//           
//           // 문서 결재
//           if(changeProcessDom.isProjectActivityDocumentChangeProcess()){
//               ProjectActivityDocumentVO docVO = businessObjectRoot.getRelatedObject(ApplicationSchemaConstants.RELCLASS_CHANGEDITEMS
//                       , ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENT
//                       , GlobalConstants.FLAG_TYPE_TO
//               );
//               
//               if(null != docVO){
//                   ProjectActivityDocument docDom = (ProjectActivityDocument) DomUtil.toDom(docVO);
//                   List<ChangeProcessVO> changeProcessList = docDom.getRelatedObjects(
//                           ApplicationSchemaConstants.RELCLASS_CHANGEDITEMS,
//                           ApplicationSchemaConstants.BIZCLASS_CHANGEPROCESS,
//                           GlobalConstants.FLAG_TYPE_FROM,
//                           selectPattern.toString(),
//                           wherePattern.toString(),
//                           paramPattern.toString(),
//                           false,
//                           false,
//                           0,
//                           1
//                   );
//                   
//                   if(changeProcessList.size() > 0 && "Rejected".equals(changeProcessList.get(0).getStates())){
//                       List<ApprovalHistoryVO> approvalHistoryList = this.retrieveApprovalHistoryList(changeProcessList.get(0));
//                       rtnApprovalHistoryVOList.add(approvalHistoryList.get(approvalHistoryList.size()-1));
//                   }
//               }
//               
//           // 프로젝트 결재
//           }else{
//               List<ProjectsVO> projectList = businessObjectRoot.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASCHANGEPROCESS
//                       , ApplicationSchemaConstants.BIZCLASS_PROJECTS
//                       , GlobalConstants.FLAG_TYPE_FROM
//               );
//               if(!NullUtil.isNone(projectList)){
//                   Projects projectsDom = (Projects)DomUtil.toDom(projectList.get(0).getObid());
//                   List<ChangeProcessVO> changeProcessList = projectsDom.getRelatedObjects(
//                           ApplicationSchemaConstants.RELCLASS_HASCHANGEPROCESS,
//                           ApplicationSchemaConstants.BIZCLASS_CHANGEPROCESS,
//                           GlobalConstants.FLAG_TYPE_TO,
//                           selectPattern.toString(),
//                           wherePattern.toString(),
//                           paramPattern.toString(),
//                           false,
//                           false,
//                           0,
//                           1
//                   );
//                   
//                   if(changeProcessList.size() > 0 && "Rejected".equals(changeProcessList.get(0).getStates())){
//                       ChangeProcess currentChangeProcessDom = (ChangeProcess) DomUtil.toDom(businessObjectRoot.getObid());
//                       
//                       String currentProjectChangeItem = NullUtil.nvl(currentChangeProcessDom.getVo().getProjectChangeItem(), "");
//                       String lastProjectChangeItem = NullUtil.nvl(changeProcessList.get(0).getProjectChangeItem(), "");
//                       
//                       if(currentProjectChangeItem.equals(lastProjectChangeItem)){
//                           List<ApprovalHistoryVO> approvalHistoryList = this.retrieveApprovalHistoryList(changeProcessList.get(0));
//                           rtnApprovalHistoryVOList.add(approvalHistoryList.get(approvalHistoryList.size()-1));
//                       }
//                   }
//               }
//           }
//  
//       }else{
//           rtnApprovalHistoryVOList = this.retrieveApprovalHistoryList(businessObjectRootVO);
//       }

       return rtnApprovalHistoryVOList;
   }
    
    /**
     * 
     * 
     * @param businessObjectRoot
     * @param approvalVO
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnSubmitSelfReject(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.application.workflow.model.ApprovalVO)
     */
    public void txnSubmitSelfReject(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO) {
        // 2018.02.22 youngmi.won PRI PCO인 경우 Self Reject 불가하도록 처리
        if( ApplicationSchemaConstants.BIZCLASS_PRIPROPERTY.equals(businessObjectRoot.getVo().getClassName()) ){
            throw new ApplicationException( "Not allowed self reject for PRI PCO." );
        }
        
        //현재 Route가 Standard이거나 Distribution이면 self reject이 안되게 Exception 발생
        StateInfo stateInfo = LifeCycleUtil.getLifieCycleStateByStateName(businessObjectRoot.getVo().getLifeCycle(), businessObjectRoot.getVo().getStates());
        if(!WorkflowConstants.ROUTE_PURPOSE_APPROVAL.equals(stateInfo.getDefaultRoutePurpose()) ) throw new ApplicationException("Not allowed self reject in "+businessObjectRoot.getVo().getStates());
        
        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByStates(WorkflowConstants.STATES_TYPE_INPROCESS);
//        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(businessObjectRoot.getStates());
        if(NullUtil.isNone(rtnObjectRootVOList)) throw new ApplicationException("api.object.error.workflow.noStateInProcess");
        
        WorkflowRoute inProcessWorkflowRoute = DomUtil.toDom(rtnObjectRootVOList.get(0).getObid(), false);
        
        List<WorkflowStepVO> rtnWorkflowStepVOList = inProcessWorkflowRoute.getWorkflowStepVOList();
        WorkflowStepVO inProcessWorkflowStepVO = null;
        for(WorkflowStepVO workflowStepVO : rtnWorkflowStepVOList) {
            if(workflowStepVO.getStates().equals(WorkflowConstants.STATES_TYPE_INPROCESS)) {
                inProcessWorkflowStepVO = workflowStepVO;
                break;
            }
        }
        
        if(NullUtil.isNull(inProcessWorkflowStepVO)) throw new ApplicationException("api.object.error.workflow.noStepInProcess");
        
        WorkflowStep inProceeWorkflowStep = DomUtil.toDom(inProcessWorkflowStepVO);
        Users users = Users.getUsers(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, null));
        List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList = inProceeWorkflowStep.getWorkflowStepNodeUserVOList();
        
        ApprovalVO selfRejectVO = null;
        for(BusinessRelationObjectVO businessRelationObjectVO : rtnBusinessRelationObjectVOList) {
            WorkflowStepNodeUserVO workflowStepNodeUserVO = (WorkflowStepNodeUserVO)businessRelationObjectVO;
            WorkflowInboxTaskVO workflowInboxTaskVO = inProcessWorkflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUserVO(workflowStepNodeUserVO);
            if(workflowInboxTaskVO.getStates().equals(WorkflowConstants.STATES_TYPE_ASSIGNED) && workflowInboxTaskVO.getTaskOwner().equals(users.getNames())) {
                selfRejectVO = new ApprovalVO();
                selfRejectVO.setState(businessObjectRoot.getVo().getStates());
                selfRejectVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_REJECT);
                selfRejectVO.setComments(null != approvalVO.getComments() ? approvalVO.getComments()+" [Self Rejection]": "Self Rejection.");
                selfRejectVO.setWorkflowInboxTaskObid(workflowInboxTaskVO.getObid());
                selfRejectVO.setWorkflowStepNodeUserObid(workflowInboxTaskVO.getRouteNodeObid());
                selfRejectVO.setWorkflowRouteObid(inProcessWorkflowRoute.getObid());
                selfRejectVO.setStep(Integer.parseInt(workflowInboxTaskVO.getStep()));
                selfRejectVO.setParallelNodeProcessionRule(workflowInboxTaskVO.getParallelNodeProcessionRule());
                break;
            }
        }
        
        if(NullUtil.isNull(selfRejectVO)) {
            List<ApprovalVO> approvalVOList = new ArrayList<ApprovalVO>();
            ApprovalVO toCreateApprovalVO = new ApprovalVO();
            toCreateApprovalVO.setAssigneeObid(users.getObid());
            toCreateApprovalVO.setState(businessObjectRoot.getStates());
            toCreateApprovalVO.setParallelNodeProcessionRule(WorkflowConstants.ROUTE_ACTION_ALL);
            toCreateApprovalVO.setStep(inProcessWorkflowStepVO.getSequences());
            toCreateApprovalVO.setSequence(1);
            toCreateApprovalVO.setRecordMode("C");
            toCreateApprovalVO.setSelfReject(true);
            approvalVOList.add(toCreateApprovalVO);
          
            txnUpdateWorkflow(businessObjectRoot, approvalVOList);
            
            for(WorkflowInboxTaskVO workflowInboxTaskVO : inProcessWorkflowRoute.getWorkflowInboxTaskVOList()) {
                if(workflowInboxTaskVO.getStates().equals(WorkflowConstants.STATES_TYPE_ASSIGNED) && workflowInboxTaskVO.getTaskOwner().equals(users.getNames())) {
                    selfRejectVO = new ApprovalVO();
                      
                    selfRejectVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_REJECT);
                    selfRejectVO.setComments(!NullUtil.isNone(approvalVO.getComments()) ? approvalVO.getComments()+" [Self Rejection]": "Self Rejection.");
                    selfRejectVO.setWorkflowInboxTaskObid(workflowInboxTaskVO.getObid());
                    selfRejectVO.setWorkflowStepNodeUserObid(workflowInboxTaskVO.getRouteNodeObid());
                    selfRejectVO.setWorkflowRouteObid(inProcessWorkflowRoute.getObid());
                    selfRejectVO.setStep(Integer.parseInt(workflowInboxTaskVO.getStep()));
                    selfRejectVO.setParallelNodeProcessionRule(workflowInboxTaskVO.getParallelNodeProcessionRule());
                    txnSubmitApproval(businessObjectRoot, selfRejectVO);
                    if(approvalVO.getRemainSelfRejectionInboxTask() == false) {
                        WorkflowInboxTask toDeleteWorkflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
                        WorkflowStepNodeUser toDeleteWorkflowStepNodeUser = DomUtil.toDom(workflowInboxTaskVO.getRouteNodeObid(), false);
                        toDeleteWorkflowStepNodeUser.deleteObject();
                        toDeleteWorkflowInboxTask.deleteInboxTask(inProcessWorkflowRoute);
                    }
                    break;
                }
            }
        }else{
            txnSubmitApproval(businessObjectRoot, selfRejectVO);
        }
    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param demoteVO
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnWithdraw(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.application.workflow.model.DemoteVO)
     */
//    public void txnWithdraw(BusinessObjectRoot businessObjectRoot, DemoteVO demoteVO) {
//        String fromStates = businessObjectRoot.getVo().getStates();
//        StateInfo toStates = LifeCycleUtil.getTargetState(businessObjectRoot.getVo(), "Withdrawal");
//
//        List<ObjectRootVO> fromStatesObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(fromStates);
//        if(NullUtil.isNone(fromStatesObjectRootVOList)){
//            throw new ApplicationException("Not allowed demote");
//        }
//
//        WorkflowRoute fromStatesWorkflowRoute = DomUtil.toDom(fromStatesObjectRootVOList.get(0));
//        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList = fromStatesWorkflowRoute.getWorkflowInboxTaskVOList();
//        if(NullUtil.isNone(rtnWorkflowInboxTaskVOList)){
//            throw new ApplicationException("Not allowed demote");
//        }
//
//        String processTimestamp = null;
//        for(WorkflowInboxTaskVO workflowInboxTaskVO: rtnWorkflowInboxTaskVOList) {
//            if(WorkflowConstants.STATES_TYPE_ASSIGNED.equals(workflowInboxTaskVO.getStates())) {
//                processTimestamp = workflowInboxTaskVO.getProcessTimestamp();
//                break;
//            }
//        }
//
//        List<ObjectRootVO> rtnExistsObjectRootVOList = businessObjectRoot.getWorkflowRouteVOList();
//        List<ObjectRootVO> toStatesObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(toStates.getStateName());
//        ObjectRootVO toStatesObjectRootVO = toStatesObjectRootVOList.get(0);
//        for(ObjectRootVO objectRootVO : rtnExistsObjectRootVOList) {
//            if(!toStatesObjectRootVO.getObid().equals(objectRootVO.getObid())) {
//                WorkflowRoute existsWorkflowRoute = DomUtil.toDom(objectRootVO.getObid(), false);
//                List<WorkflowInboxTaskVO> rtnExistsWorkflowInboxTaskVOList = existsWorkflowRoute.getWorkflowInboxTaskVOList();
//                for(WorkflowInboxTaskVO workflowInboxTaskVO: rtnExistsWorkflowInboxTaskVOList) {
//                    //TODO Working은 제외 해야 한다.
//                    if(WorkflowConstants.APPROVAL_STATUS_COMPLETE.equals(workflowInboxTaskVO.getStates()) && processTimestamp.equals(workflowInboxTaskVO.getProcessTimestamp())) {
//                        throw new ApplicationException("Not allowed demote");
//                    }
//                }
//            }
//        }
////        throw new GpdmException("Withdraw Success");
//        fromStatesWorkflowRoute.deleteWorkflowInboxTaskForDemote();
//        fromStatesWorkflowRoute.reset();
//        fromStatesWorkflowRoute.resetStepList();
//
//        WorkflowRoute toStatesWorkflowRoute = DomUtil.toDom(toStatesObjectRootVO.getObid(), false);
//        toStatesWorkflowRoute.reset();
//        toStatesWorkflowRoute.resetStepList();
//
//        if("Promote Connected Object".equals(toStatesWorkflowRoute.getVo().getRouteCompletionAction())) {
//            txnStartRoute(businessObjectRoot, toStates.getStateName());
//        }
//        businessObjectRoot.withdraw();
//    }
    
    

    /**
     *
     *
     * @param businessObjectRoot
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnDeleteWorkflow(omc.api.object.dom.BusinessObjectRoot)
     */
//    @Override
//    public void txnDeleteWorkflow(BusinessObjectRoot businessObjectRoot) {
//        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOList();
//        for(ObjectRootVO objectRootVO : rtnObjectRootVOList) {
//            //Delete a WorkflowInboxTask include Revision
//            WorkflowRoute toBeDeleteWorkflowRoute = DomUtil.toDom(objectRootVO);
//            createDeleteApprovalInfoToEP(businessObjectRoot, toBeDeleteWorkflowRoute);
//            List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList = toBeDeleteWorkflowRoute.getWorkflowInboxTaskVOList();
//            for(WorkflowInboxTaskVO workflowInboxTaskVO: rtnWorkflowInboxTaskVOList) {
//                WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
//
//                List<BusinessObjectVO> rtnBusinessObjectRootVOList = workflowInboxTask.getRevisions();
//                for(BusinessObjectVO businessObjectVO : rtnBusinessObjectRootVOList) {
//                    WorkflowInboxTask revisedWorkflowInboxTask = DomUtil.toDom(businessObjectVO);
//                    revisedWorkflowInboxTask.deleteObject();
//                }
//            }
//            //Delete WorkflowStep
//            List<WorkflowStepVO> rtnWorkflowStepVOList = toBeDeleteWorkflowRoute.getAllWorkflowStepVOList();
//            for(WorkflowStepVO workflowStepVO : rtnWorkflowStepVOList) {
//                WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
//                workflowStep.deleteObject();
//            }
//            toBeDeleteWorkflowRoute.deleteObject();
//        }
//    }

    /**
     *
     * @param businessObjectRoot
     * @param state
     *
     */
//    @Override
//    public void txnDeleteWorkflowByState(BusinessObjectRoot businessObjectRoot, String state) {
//        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(state);
//        if(rtnObjectRootVOList != null && !rtnObjectRootVOList.isEmpty()){
//            //Delete a WorkflowInboxTask include Revision
//            WorkflowRoute toBeDeleteWorkflowRoute = DomUtil.toDom(rtnObjectRootVOList.get(0));
//            createDeleteApprovalInfoToEP(businessObjectRoot, toBeDeleteWorkflowRoute);
//            List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList = toBeDeleteWorkflowRoute.getWorkflowInboxTaskVOList();
//            for(WorkflowInboxTaskVO workflowInboxTaskVO: rtnWorkflowInboxTaskVOList) {
//                WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
//
//                List<BusinessObjectVO> rtnBusinessObjectRootVOList = workflowInboxTask.getRevisions();
//                for(BusinessObjectVO businessObjectVO : rtnBusinessObjectRootVOList) {
//                    WorkflowInboxTask revisedWorkflowInboxTask = DomUtil.toDom(businessObjectVO);
//                    revisedWorkflowInboxTask.deleteObject();
//                }
//            }
//            //Delete WorkflowStep
//            List<WorkflowStepVO> rtnWorkflowStepVOList = toBeDeleteWorkflowRoute.getAllWorkflowStepVOList();
//            for(WorkflowStepVO workflowStepVO : rtnWorkflowStepVOList) {
//                WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
//                workflowStep.deleteObject();
//            }
//            toBeDeleteWorkflowRoute.deleteObject();
//        }
//    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param approvalVO
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#isLastApprovalInWorking(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.application.workflow.model.ApprovalVO)
     */
    @Override
    public boolean isLastApprovalInWorking(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO) {
        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(WorkflowConstants.STATE_WORKING);
        if(NullUtil.isNone(rtnObjectRootVOList)){
            throw new ApplicationException(WorkflowConstants.STATE_WORKING +" Route does not exists.");
        }
        ObjectRootVO objectRootVO = rtnObjectRootVOList.get(0);
        WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO.getObid(), false);
        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
        int approvedCnt = 0;
        for(WorkflowInboxTaskVO workflowInboxTaskVO : rtnWorkflowInboxTaskVOList) {
            if(WorkflowConstants.APPROVAL_STATUS_COMPLETE.equals(workflowInboxTaskVO.getStates()) && WorkflowConstants.ACTION_TYPE_END_WORKING.equals(workflowInboxTaskVO.getRouteAction())
                    && WorkflowConstants.APPROVAL_STATUS_APPROVE.equals(workflowInboxTaskVO.getApprovalStatus())) {
                approvedCnt ++;
            }
        }
        if(rtnWorkflowInboxTaskVOList.size() == approvedCnt){
            throw new ApplicationException("The "+WorkflowConstants.STATE_WORKING+" state was completed.");
        }
        boolean bResult = (rtnWorkflowInboxTaskVOList.size() == approvedCnt + 1) ? true : false ;
        return bResult;
    }

    @Override
    public boolean isLastApprovalInState(BusinessObjectRoot businessObjectRoot, String state){
        boolean isLastApprovalInState = false;
        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(state);

        if(NullUtil.isNone(rtnObjectRootVOList)){
            return false;
        }
        ObjectRootVO objectRootVO = rtnObjectRootVOList.get(0);
        WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO.getObid(), false);
        List<WorkflowStepVO> workflowStepVOList = workflowRoute.getWorkflowStepVOList();
        if(NullUtil.isNone(workflowStepVOList)){
            return false;
        }
        int awaitingApproval = 0;
        for(WorkflowStepVO workflowStepVO : workflowStepVOList){
            if(WorkflowConstants.STATES_TYPE_COMPLETE.equals(workflowStepVO.getStates())){
                continue;
            }
            
            WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
            if(WorkflowConstants.STATES_TYPE_INPROCESS.equals(workflowStepVO.getStates())){
                List<BusinessRelationObjectVO> workflowStepNodeUserVOList = workflowStep.getWorkflowStepNodeUserVOList();
                for(BusinessRelationObjectVO vo : workflowStepNodeUserVOList){
                    WorkflowStepNodeUserVO workflowStepNodeUserVO = (WorkflowStepNodeUserVO)vo;
                    if(NullUtil.isNull(workflowStepNodeUserVO.getActualCompletionDate())
                            && WorkflowConstants.APPROVAL_STATUS_NONE.equals(workflowStepNodeUserVO.getApprovalStatus())) {
                        if(WorkflowConstants.ROUTE_ACTION_ANY.equals(workflowStepNodeUserVO.getParallelNodeProcessionRule())){
                            awaitingApproval = 1;
                        }else if(WorkflowConstants.ROUTE_ACTION_ALL.equals(workflowStepNodeUserVO.getParallelNodeProcessionRule())){
                            awaitingApproval ++;
                        }
                    }
                }
            }else if(WorkflowConstants.STATES_TYPE_DEFINE.equals(workflowStepVO.getStates())){
                List<BusinessRelationObjectVO> workflowStepNodeUserVOList = workflowStep.getWorkflowStepNodeUserVOList();
                if(!NullUtil.isNone(workflowStepNodeUserVOList)) return false;
            }
        }
            
        if(awaitingApproval == 1) isLastApprovalInState = true;
        return isLastApprovalInState;
    }

//    @Override
//    public boolean isLastApproval(BusinessObjectRoot businessObjectRoot){
//        boolean isLastApproval = false;
//        // VC SW 기능 개선 Oem Approval 이 Y 인 경우 2nd Processing 으로 Promote 시 결재자가 추가 되므로 Last 가 아님.
////        if(businessObjectRoot.isKindOf(ApplicationSchemaConstants.BIZCLASS_CHANGES)){
////            ChangesVO changeVO = (ChangesVO)businessObjectRoot.getVo();
////            if( changeVO.getStates().equals(ApplicationSchemaConstants.STATE_SW_PRODUCTION_TEST_ECO_1STPROCESSING)
////                    && "Y".equals(changeVO.getNeedOemApproval())){
////                return false;
////            }
////        }
//
//        List<ObjectRootVO> routeList = businessObjectRoot.getWorkflowRouteVOList();
//
//        for(ObjectRootVO routeVO : routeList){
//            WorkflowRouteVO workflowRouteVO = (WorkflowRouteVO)routeVO;
//            if( WorkflowConstants.ROUTE_PURPOSE_APPROVAL.equals(workflowRouteVO.getRouteBasePurpose())
//                    || (
//                            WorkflowConstants.ROUTE_PURPOSE_CONFIRMATION.equals(workflowRouteVO.getRouteBasePurpose())) ){
//                if(WorkflowConstants.STATES_TYPE_DEFINE.equals(workflowRouteVO.getStates())){
//
//                    WorkflowRoute route = DomUtil.toDom(workflowRouteVO);
//                    List<WorkflowStepVO> allWorkflowStepVOList = route.getAllWorkflowStepVOList();
//                    if(!allWorkflowStepVOList.isEmpty()){
//                        return false;
//                    }
//                }
//                else if(WorkflowConstants.STATES_TYPE_INPROCESS.equals(workflowRouteVO.getStates())){
//                    isLastApproval = isLastApprovalInState(businessObjectRoot,
//                            (String)workflowRouteVO.getOutData().get("rel_routeState"));
//                }
//            }
//        }
//
//        return isLastApproval;
//    }
    /**
     *
     *
     * @param reassignVOList
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnDoReassignList(java.util.List)
     */
    @Override
    public void txnDoReassignList(List<ReassignVO> reassignVOList) {
        for(ReassignVO reassignVO : reassignVOList) {
            BusinessObjectRoot businessObjectRoot = DomUtil.toDom(reassignVO.getObid());
            txnReassignApprover(businessObjectRoot, reassignVO);
        }
    }

    /**
     * inbox task가 활성화 되었을 경우에만 reassign이 허용 됨.
     *
     * @param businessObjectRoot
     * @param reassignVO
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnReassignApprover(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.application.workflow.model.ReassignVO)
     */
    @Override
    public void txnReassignApprover(BusinessObjectRoot businessObjectRoot, ReassignVO reassignVO) {
        WorkflowRoute workflowRoute = DomUtil.toDom(reassignVO.getWorkflowRouteObid(), false);
        WorkflowStepNodeUser beforeReassignWorkflowStepNodeUser = DomUtil.toDom(reassignVO.getWorkflowStepNodeUserObid(), false);
        validateForReassign(businessObjectRoot, workflowRoute, reassignVO, beforeReassignWorkflowStepNodeUser);

        WorkflowInboxTaskVO beforeReassignWorkflowInboxTaskVO = workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUserVO(beforeReassignWorkflowStepNodeUser.getVo());
        WorkflowInboxTask beforeReassignWorkflowInboxTask = DomUtil.toDom(beforeReassignWorkflowInboxTaskVO);
        if(WorkflowConstants.APPROVAL_STATUS_COMPLETE.equals(beforeReassignWorkflowInboxTask.getVo().getStates())){
            throw new ApplicationException("It cannot be reassigned.[Reason: complete approval]");
        }
        UsersVO fromUsersVO = beforeReassignWorkflowInboxTask.getUsersVO();
        beforeReassignWorkflowInboxTask.promote();
        beforeReassignWorkflowInboxTask.promote();

        Users toUsers = DomUtil.toDom(reassignVO.getToAssigneeObid(), false);
        beforeReassignWorkflowInboxTask.getVo().setDelegatedFrom(null);
        beforeReassignWorkflowInboxTask.getVo().setDelegatedTo(reassignVO.getToAssigneeObid());
        beforeReassignWorkflowInboxTask.getVo().setApprovalStatus(WorkflowConstants.WORKFLOW_REASSIGN);
        beforeReassignWorkflowInboxTask.getVo().setComments(WorkflowConstants.WORKFLOW_REASSIGN +" "+ toUsers.getVo().getTitles());
        beforeReassignWorkflowInboxTask.getVo().setActionComments(reassignVO.getComments());
        beforeReassignWorkflowInboxTask.getVo().setActualCompletionDate(timeService.getDBLocalTime());
        beforeReassignWorkflowInboxTask.modifyObject();
        workflowRoute.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASKHISTORY, beforeReassignWorkflowInboxTask.getVo(), new HashMap<String, Object>());
        beforeReassignWorkflowInboxTask.deleteInboxTask(workflowRoute);

        Map<String, Object> attributeMap = new HashMap<String, Object>();
        attributeMap.put("routeAction", beforeReassignWorkflowStepNodeUser.getVo().getRouteAction());
        attributeMap.put("routeInstructions", beforeReassignWorkflowStepNodeUser.getVo().getRouteInstructions());
        attributeMap.put("dueDateOffset", beforeReassignWorkflowStepNodeUser.getVo().getDueDateOffset());
        attributeMap.put("isEssential",  beforeReassignWorkflowStepNodeUser.getVo().getIsEssential()); //결재자가 필수 결재자 인지 아닌지 여부
        attributeMap.put("parallelNodeProcessionRule", beforeReassignWorkflowStepNodeUser.getVo().getParallelNodeProcessionRule());
        attributeMap.put("sequences", beforeReassignWorkflowStepNodeUser.getVo().getSequences());
        attributeMap.put("titles", beforeReassignWorkflowStepNodeUser.getVo().getTitles());
        attributeMap.put("actionComments", beforeReassignWorkflowStepNodeUser.getVo().getActionComments());
        attributeMap.put("notifyEmail", beforeReassignWorkflowStepNodeUser.getVo().getNotifyEmail());
        attributeMap.put("processRole", beforeReassignWorkflowStepNodeUser.getVo().getProcessRole());
        WorkflowStep workflowStep = DomUtil.toDom(beforeReassignWorkflowStepNodeUser.getVo().getFromObid(), false);
        BusinessRelationObjectVO  businessRelationObjectVO = workflowStep.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWSTEPNODEUSER, toUsers.getVo(), attributeMap);
        //modify RouteNodeObid
        WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
        workflowStepNodeUser.getVo().setRouteNodeObid(businessRelationObjectVO.getObid());
        workflowStepNodeUser.modifyObject();

        beforeReassignWorkflowStepNodeUser.deleteObject();
        createWorkflowInboxTaskNRelationsForReassign(businessObjectRoot, workflowRoute, workflowStep, workflowStepNodeUser, fromUsersVO);
        createReassignApprovalInfoToEP(businessObjectRoot, workflowRoute, beforeReassignWorkflowInboxTask, workflowStepNodeUser);
        reassignChangeOwner(businessObjectRoot, toUsers);
    }

    /**
     * Working의 특정 Object Reassign시 change owner 수행
     * @param businessObjectRoot
     * @param toUsers
     */
    private void reassignChangeOwner(BusinessObjectRoot businessObjectRoot, Users toUsers) {
        if( businessObjectRoot.getVo().getClassName().equals(ApplicationSchemaConstants.BIZCLASS_PDRPROPERTY)
                || businessObjectRoot.getVo().getClassName().equals(ApplicationSchemaConstants.BIZCLASS_PCOPROPERTY)
                || businessObjectRoot.getVo().getClassName().equals(ApplicationSchemaConstants.BIZCLASS_PCRPROPERTY)
                || businessObjectRoot.getVo().getClassName().equals(ApplicationSchemaConstants.BIZCLASS_PRIPROPERTY)
                || businessObjectRoot.getVo().getClassName().equals(ApplicationSchemaConstants.BIZCLASS_PROJECTSPEC) ){
            BusinessObjectRoot targetDom = DomUtil.toDom( businessObjectRoot.getVo().getObid() );
            if( businessObjectRoot.getVo().getStates().equals(WorkflowConstants.STATE_WORKING) ){
                targetDom.changeOwner( toUsers.getVo().getNames() );
            }
            // 2018.03.12 youngmi.won PCO Requested 상태에서 Reassign 시 pcoContactEmployee도 변경되도록 로직 수정
            else if( businessObjectRoot.getVo().getClassName().equals(ApplicationSchemaConstants.BIZCLASS_PCRPROPERTY)
                    && businessObjectRoot.getVo().getStates().equals(ApplicationSchemaConstants.STATE_PCR_PCOREQUESTED) ){
                PCRProperty pcrDom = DomUtil.toDom( businessObjectRoot.getVo().getObid() );
                pcrDom.getVo().setPcoContactEmployee( toUsers.getVo().getNames() );
                pcrDom.modifyObject();
            }
            else{
                if( !StringUtils.isEmpty(targetDom.getVo().getCheckouter()) && !"1".equals(targetDom.getVo().getCheckouter()) ){
//                    if( UserSessionUtil.hasRole( WorkflowConstants.ROLE_NAME_SYSTEM_ADMIN ) ){
//                        throw new ApplicationException( "You have to check-in for this item. (Checkouter : " + targetDom.getVo().getCheckouter() + ")" );
//                    }
//                    else{
//                        if( targetDom.getVo().getCheckouter().equals(userSession.getUserId()) ){
//                            targetDom.checkIn();
//                        }
//                    }
                }
            }
        }
    }

    /**
     *
     *
     * @param businessObjectRoot
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveInProcessWorkflowRouteNStep(omc.api.object.dom.BusinessObjectRoot)
     */
    @Override
    public ApprovalVO retrieveInProcessWorkflowRouteNStep(BusinessObjectRoot businessObjectRoot) {
        ApprovalVO rtnApprovalVO = new ApprovalVO();
        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByStates(WorkflowConstants.STATES_TYPE_INPROCESS);
        if(!NullUtil.isNone(rtnObjectRootVOList)) {
            ObjectRootVO objectRootVO = rtnObjectRootVOList.get(0);
            WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO);
            List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList =
                    workflowRoute.getRelationship(
                            ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE,
                            businessObjectRoot.getVo().getClassName(),
                            GlobalConstants.FLAG_TYPE_FROM);
            if(!NullUtil.isNone(rtnBusinessRelationObjectVOList)) {
                WorkflowObjectRoute workflowObjectRoute = DomUtil.toDom(rtnBusinessRelationObjectVOList.get(0).getObid(), false);
                rtnApprovalVO.setState(workflowObjectRoute.getVo().getRouteState());
            }
            WorkflowStepVO rtnWorkflowStepVO = workflowRoute.getWorkflowStepVOListByStates(WorkflowConstants.STATES_TYPE_INPROCESS);
            if(!NullUtil.isNull(rtnWorkflowStepVO)){
                rtnApprovalVO.setStep(rtnWorkflowStepVO.getSequences());
            }
        }
        return rtnApprovalVO;
    }
    
    /**
     * 
     * 
     * @param businessObjectRoot
     * @param routeState
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveFinalApprove(omc.api.object.dom.BusinessObjectRoot, java.lang.String)
     */
    public WorkflowInboxTaskVO retrieveFinalApprove(BusinessObjectRoot businessObjectRoot, String routeState) {
        WorkflowInboxTaskVO rtnWorkflowInboxTaskVO = null;
        List<ObjectRootVO> rtnWorkflowRouteList = businessObjectRoot.getWorkflowRouteVOListByRouteState(routeState);
        if(NullUtil.isNone(rtnWorkflowRouteList)) throw new ApplicationException("api.object.error.workflow.routeNotExist",  new Object[] { routeState });
        
        WorkflowRouteVO workflowRouteVO = (WorkflowRouteVO)rtnWorkflowRouteList.get(0);
        if(!workflowRouteVO.getStates().equals(WorkflowConstants.STATES_TYPE_COMPLETE)) new ApplicationException("api.object.errro.workflow.notCompleteState");
        
        WorkflowRoute workflowRoute = DomUtil.toDom(workflowRouteVO);
        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList =  workflowRoute.getWorkflowInboxTaskVOListByStates(WorkflowConstants.STATES_TYPE_COMPLETE, "actualCompletionDate", "desc");
            
        if(!NullUtil.isNone(rtnWorkflowInboxTaskVOList)) rtnWorkflowInboxTaskVO = rtnWorkflowInboxTaskVOList.get(0);
        return rtnWorkflowInboxTaskVO;
    }

    /**
     *
     *
     * @param businessObjectRoot
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveSendBackToList(omc.api.object.dom.BusinessObjectRoot)
     */
    @Override
    public List<ApprovalVO> retrieveSendBackToList(ApprovalVO approvalVO) {
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(approvalVO.getObid());
        Class<?>[] paramType = new Class<?>[1];
        if(WorkflowConstants.APPROVAL_STATUS_REJECT.equals(approvalVO.getApprovalStatus())) {
            paramType[0] = String.class;
            try {
                Method method = businessObjectRoot.getVo().getClass().getMethod("setBranchTo", paramType);
                method.invoke(businessObjectRoot.getVo(), approvalVO.getApprovalStatus());
            } catch (NoSuchMethodException e) {
                throw new ApplicationException("api.object.error.workflow.noSuchMethod");
            } catch (SecurityException e) {
                throw new ApplicationException("api.object.error.workflow.security");
            } catch (IllegalAccessException e) {
                throw new ApplicationException("api.object.error.workflow.illegalAccess");
            } catch (IllegalArgumentException e) {
                throw new ApplicationException("api.object.error.workflow.illegalArgument");
            } catch (InvocationTargetException e) {
                throw new ApplicationException("api.object.error.workflow.invocationTarget");
            }
        }
        
        List<ApprovalVO> rtnWorkflowInboxTaskVOList = new ArrayList<ApprovalVO>();
        StateInfo toStates = LifeCycleUtil.getTargetState(businessObjectRoot.getVo(), GlobalConstants.WORKFLOW_TYPE_PROMOTE);
        if(NullUtil.isNull(toStates)){
            throw new ApplicationException("Target state is null");
        }

        List<ApprovalVO> rtnApprovalVOList = retrieveWorkflow(businessObjectRoot);
        //Step에 대한 구분이 있어야 함.
        for(ApprovalVO approval : rtnApprovalVOList) {
            if(toStates.getStateName().equals(approval.getState())) {
                rtnWorkflowInboxTaskVOList.add(approval);
            }
        }
        
        return rtnWorkflowInboxTaskVOList;
    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param reassignVO
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnChangeOriginator(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.application.workflow.model.ReassignVO)
     */
    @Override
    public void txnChangeOriginator(BusinessObjectRoot businessObjectRoot, ReassignVO reassignVO) {
        if(NullUtil.isNull(businessObjectRoot) || StringUtils.isEmpty(reassignVO.getFromAssigneeObid()) ||
                StringUtils.isEmpty(reassignVO.getToAssigneeObid()) ){
            throw new IllegalArgumentException();
        }
        reassignVO.setObid(businessObjectRoot.getVo().getObid());

        List<ObjectRootVO> rtnObjectRootVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(WorkflowConstants.STATE_WORKING);
        if(NullUtil.isNone(rtnObjectRootVOList)){
            throw new ApplicationException("Working route does not exists.");
        }
        ObjectRootVO objectRootVO = rtnObjectRootVOList.get(0);
        WorkflowRoute workflowRoute = DomUtil.toDom(objectRootVO);
        reassignVO.setWorkflowRouteObid(objectRootVO.getObid());

        WorkflowStepVO rtnWorkflowStepVO = workflowRoute.getFirstWorkflowStepVO();
        if(NullUtil.isNull(rtnWorkflowStepVO)){
            throw new ApplicationException("The first step of Working state does not exists.");
        }
        WorkflowStep workflowStep = DomUtil.toDom(rtnWorkflowStepVO);
        List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList = workflowStep.getWorkflowStepNodeUserVOList();
        for(BusinessRelationObjectVO businessRelationObjectVO : rtnBusinessRelationObjectVOList) {
            WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
            if(WorkflowConstants.INSTRUCTION_TYPE_ORIGINATOR.equals(workflowStepNodeUser.getVo().getRouteInstructions())
                    && WorkflowConstants.ACTION_TYPE_END_WORKING.equals(workflowStepNodeUser.getVo().getRouteAction())) {
                reassignVO.setWorkflowStepNodeUserObid(workflowStepNodeUser.getVo().getObid());
                break;
            }
        }

        WorkflowInboxTaskVO workflowInboxTaskVO = workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUser(reassignVO.getWorkflowStepNodeUserObid());
        if(WorkflowConstants.APPROVAL_STATUS_COMPLETE.equals(workflowInboxTaskVO.getStates())){
            return;
        }
        txnReassignApprover(businessObjectRoot, reassignVO);
    }

    /**
     *
     * @param businessObjectRoot
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveApprovalStateList(omc.api.object.dom.BusinessObjectRoot)
     */
    @Override
    public List<CodeDetailVO> retrieveApprovalStateList(String lifeCycleName){
        return retrieveApprovalStateList(lifeCycleName, true);
    }

    /**
     *
     *
     * @param lifeCycleName
     * @param isIncludeWorking
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveApprovalStateList(java.lang.String, boolean)
     */
    @Override
    public List<CodeDetailVO> retrieveApprovalStateList(String lifeCycleName, boolean isIncludeWorking) {
        List<CodeDetailVO> rtnSteteList = new ArrayList<CodeDetailVO>();
        List<StateInfo> rtnLifeCycleVOList = LifeCycleUtil.getUserInputLifieCycleStateListByName(lifeCycleName);

        int idx = 0;
        for( StateInfo stateInfo : rtnLifeCycleVOList ){
            if( isIncludeWorking || !WorkflowConstants.STATE_WORKING.equals(stateInfo.getStateName())){
                CodeDetailVO codeDetailVO = new CodeDetailVO();
                codeDetailVO.setSequences(idx++);
                codeDetailVO.setNames(stateInfo.getStateName());
                codeDetailVO.setTitles(("Y".equals(stateInfo.getUserInputMandantory())? "**"+stateInfo.getStateName(): stateInfo.getStateName()));
                codeDetailVO.setAttribute01( stateInfo.getProcessRule() );
                codeDetailVO.setAttribute02( stateInfo.getDefaultRoutePurpose() );
                rtnSteteList.add(codeDetailVO);
            }
        }

        return rtnSteteList;
    }

    /**
     *
     *
     * @param lifeCycleName
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveAllApprovalStateList(java.lang.String)
     */
    @Override
    public List<CodeDetailVO> retrieveAllApprovalStateList(String lifeCycleName) {
        List<CodeDetailVO> rtnSteteList = new ArrayList<CodeDetailVO>();
        List<StateInfo> rtnLifeCycleVOList = LifeCycleUtil.getLifieCycleStateListByName(lifeCycleName);
        int idx = 0;
        for( StateInfo stateInfo : rtnLifeCycleVOList ){
            if(!"Cancelled".equals(stateInfo.getStateName())) {
                CodeDetailVO codeDetailVO = new CodeDetailVO();
                codeDetailVO.setSequences(idx);
                codeDetailVO.setNames(stateInfo.getStateName());
                codeDetailVO.setTitles(("Y".equals(stateInfo.getUserInputMandantory())? "**"+stateInfo.getStateName(): stateInfo.getStateName()));
                codeDetailVO.setAttribute01( stateInfo.getProcessRule());
                rtnSteteList.add(codeDetailVO);
                idx++;
            }
        }
        return rtnSteteList;
    }


    /**
     *
     * @param lifeCycleName
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveDistributionState(java.lang.String)
     */
    @Override
    public String retrieveDistributionState(String lifeCycleName){
        String rtnStates = null;
        List<StateInfo> stateInfoList = LifeCycleUtil.getLifieCycleStateListByName(lifeCycleName);
        for(StateInfo stateInfo : stateInfoList) {
            if("Distribution".equals(stateInfo.getDefaultRoutePurpose())) {
                rtnStates =  stateInfo.getStateName();
                break;
            }
        }
        return rtnStates;
    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param routeStates
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#isExistsAssigneeTargetStates(omc.api.object.dom.BusinessObjectRoot, java.lang.String)
     */
    @Override
    public boolean isExistsAssigneeTargetStates(BusinessObjectRoot businessObjectRoot, String routeStates){
        boolean bResult = false;
        List<ObjectRootVO> rtnWorkflowRouteVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(routeStates);
        if(!NullUtil.isNone(rtnWorkflowRouteVOList)) {
            WorkflowRoute workflowRoute = DomUtil.toDom(rtnWorkflowRouteVOList.get(0).getObid(), false);
            WorkflowStepVO firstWorkflowStepVO = workflowRoute.getFirstWorkflowStepVO();
            if(!NullUtil.isNull(firstWorkflowStepVO)) {
                WorkflowStep workflowStep = DomUtil.toDom(firstWorkflowStepVO.getObid(), false);
                List<BusinessRelationObjectVO> rtnWorkflowStepNodeUserVOList = workflowStep.getWorkflowStepNodeUserVOList();
                if(rtnWorkflowStepNodeUserVOList.size() > 0){
                    bResult = true;
                }
            }
        }
        return bResult;
    }

    /**
     * 
     * @param businessObjectRoot
     * @param routeStates
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveAssigneeTargetStates(omc.api.object.dom.BusinessObjectRoot, java.lang.String)
     */
    @Override
    public List<BusinessRelationObjectVO> retrieveAssigneeTargetStates(BusinessObjectRoot businessObjectRoot, String routeStates){
        List<BusinessRelationObjectVO> rtnWorkflowStepNodeUserVOList = null;
        List<ObjectRootVO> rtnWorkflowRouteVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(routeStates);
        if(!NullUtil.isNone(rtnWorkflowRouteVOList)) {
            WorkflowRoute workflowRoute = DomUtil.toDom(rtnWorkflowRouteVOList.get(0).getObid(), false);
            WorkflowStepVO firstWorkflowStepVO = workflowRoute.getFirstWorkflowStepVO();
            if(!NullUtil.isNull(firstWorkflowStepVO)) {
                WorkflowStep workflowStep = DomUtil.toDom(firstWorkflowStepVO.getObid(), false);
                rtnWorkflowStepNodeUserVOList = workflowStep.getWorkflowStepNodeUserVOList();
            }
        }
        
        return rtnWorkflowStepNodeUserVOList;
    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param approvalVO
     * @see lgcns.rnd.application.workflow.service.WorkflowService#tnxDoRevoke(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.application.workflow.model.ApprovalVO)
     */
//    @Override
//    public void txnRevoke(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO) {
//      //Modify WorkflowStepNodeUser
//        WorkflowStepNodeUser toBeRevokeWorkflowStepNodeUser = DomUtil.toDom(approvalVO.getWorkflowStepNodeUserObid(), false);
//        if(!WorkflowConstants.STATE_WORKING.equals(businessObjectRoot.getVo().getStates())){
//            throw new ApplicationException("Unavaiable revoke.");
//        }
//        if(!WorkflowConstants.APPROVAL_STATUS_APPROVE.equals(toBeRevokeWorkflowStepNodeUser.getVo().getApprovalStatus())){
//            throw new ApplicationException("Unavaiable revoke.");
//        }
//
//        WorkflowStepNodeUserVO toBeRevokeWorkflowStepNodeUserVO = toBeRevokeWorkflowStepNodeUser.getVo();
//        toBeRevokeWorkflowStepNodeUserVO.setComments(null);
//        toBeRevokeWorkflowStepNodeUserVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_NONE);
//        toBeRevokeWorkflowStepNodeUserVO.setActualCompletionDate(nullDate);
//        toBeRevokeWorkflowStepNodeUser.modifyObject();
//
//        //Modify WorkflowInboxTask & Create WorkflowRouteTaskHistory
//        WorkflowRoute workflowRoute = DomUtil.toDom(approvalVO.getWorkflowRouteObid(), false);
//        WorkflowInboxTaskVO toBeRevokeWorkflowInboxTaskVO = workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUserVO(toBeRevokeWorkflowStepNodeUserVO);
//        toBeRevokeWorkflowInboxTaskVO.setComments(null);
//        toBeRevokeWorkflowInboxTaskVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_NONE);
//        toBeRevokeWorkflowInboxTaskVO.setActualCompletionDate(nullDate);
//        WorkflowInboxTask toBeRevokeWorkflowInboxTask = DomUtil.toDom(toBeRevokeWorkflowInboxTaskVO);
//        toBeRevokeWorkflowInboxTask.modifyObject();
//        toBeRevokeWorkflowInboxTask.demote();
//        toBeRevokeWorkflowInboxTask.demote();
//
//        List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList =
//                toBeRevokeWorkflowInboxTask.getRelationship(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASKHISTORY, ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE, GlobalConstants.FLAG_TYPE_FROM);
//
//        if(!NullUtil.isNone(rtnBusinessRelationObjectVOList)) {
//            BusinessRelationObjectVO businessRelationObjectVO = rtnBusinessRelationObjectVOList.get(0);
//            WorkflowRouteTaskHistory workflowRouteTaskHistory = DomUtil.toDom(businessRelationObjectVO);
//            workflowRouteTaskHistory.deleteObject();
//        }
//    }



    /************************************************************************************************
     *  Private Method
     ************************************************************************************************/


    /**
     *
     *
     * @param approvalVO
     */
    private void setAction(ApprovalVO approvalVO) {
        switch(approvalVO.getApprovalStatus()) {
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
    }

    private void setActionForDistributionState(ApprovalVO approvalVO) {
        switch(approvalVO.getApprovalStatus()) {
            case WorkflowConstants.APPROVAL_STATUS_NONE :
                approvalVO.setAction(WorkflowConstants.ACTION_TYPE_AWAITING_ACKNOWLEDGE);
                break;

            case WorkflowConstants.APPROVAL_STATUS_ACKNOWLEDGE :
                approvalVO.setAction(WorkflowConstants.ACTION_TYPE_ACKNOWLEDGED);
                break;

            default:
                approvalVO.setAction(WorkflowConstants.ACTION_TYPE_PENDING);
                break;
        }
    }

    private void setActionForApprovalHistory(ApprovalHistoryVO approvalHistoryVO) {
        switch(approvalHistoryVO.getApprovalStatus()) {
            case WorkflowConstants.APPROVAL_STATUS_APPROVE:
                approvalHistoryVO.setAction(WorkflowConstants.ACTION_TYPE_APPROVED);
                break;

            case WorkflowConstants.APPROVAL_STATUS_REJECT:
                approvalHistoryVO.setAction(WorkflowConstants.ACTION_TYPE_REJECTED);
                break;

            case WorkflowConstants.APPROVAL_STATUS_ACKNOWLEDGE:
                approvalHistoryVO.setAction(WorkflowConstants.ACTION_TYPE_ACKNOWLEDGED);
                break;

            case WorkflowConstants.WORKFLOW_REASSIGN:
                approvalHistoryVO.setAction(WorkflowConstants.ACTION_TYPE_REASSIGNED);
                break;

            default:
                approvalHistoryVO.setAction("Not Defined");
                break;
        }
    }

    private WorkflowRoute createWorkflowRoute(BusinessObjectRoot businessObjectRoot) {
        //SELECT * FROM PTROUTE
        StateInfo stateInfo = (StateInfo)businessObjectRoot.getVo().getOutData().get("stateInfo");

        List<ObjectRootVO> rtnObjectRootVO = businessObjectRoot.getWorkflowRouteVOListByRouteState(stateInfo.getStateName());
        if(!NullUtil.isNone(rtnObjectRootVO))
            throw new ApplicationException("api.object.error.workflow.duplicateRoute",  new Object[] { stateInfo.getStateName() });

        WorkflowRouteVO workflowRouteVO = new WorkflowRouteVO();
        workflowRouteVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_ROUTENAME));
        workflowRouteVO.setRouteCompletionAction(stateInfo.getRouteCompleteAction());
        workflowRouteVO.setDescriptions(businessObjectRoot.getVo().getObid()+"_"+businessObjectRoot.getVo().getNames()+"_"+stateInfo.getStateName());
        workflowRouteVO.setRestartUpOnTaskRejection((WorkflowConstants.TRUE.equals(stateInfo.getRouteAutoStartOnReject())?true:false));
        workflowRouteVO.setAutoStopOnRejection(stateInfo.getRouteHowToOnReject()); //Immediate , Deferred
        workflowRouteVO.setRouteBasePurpose(stateInfo.getDefaultRoutePurpose());
        workflowRouteVO.setStates(WorkflowConstants.STATES_TYPE_DEFINE);
        workflowRouteVO.setTitles(workflowRouteVO.getNames());
        workflowRouteVO.setOrganizations(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.divisionUnit, "None"));
        
        if(!NullUtil.isNull(businessObjectRoot.getVo().getOutData().get("processTimestamp"))) {
            workflowRouteVO.setProcessTimestamp((String)businessObjectRoot.getVo().getOutData().get("processTimestamp"));
        }

        WorkflowRoute workflowRoute = DomUtil.toDom(workflowRouteVO);
        workflowRoute.createObject();

        //Create WorkflowObjectRoute
        Map<String, Object> attributeMap = new HashMap<String, Object>();
        attributeMap.put("routePurpose", stateInfo.getDefaultRoutePurpose());
        attributeMap.put("routeLifeCycle", businessObjectRoot.getVo().getLifeCycle());
        attributeMap.put("routeState", stateInfo.getStateName());
        workflowRoute.createRelationWorkflowObjectRoute(businessObjectRoot, attributeMap);
        return workflowRoute;
    }
    
    private void rebuildStepNodeUserAndInboxTaskWithDelegated(BusinessObjectRoot businessObjectRoot) {
        List<ObjectRootVO> rtnWorkflowRouteVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(businessObjectRoot.getVo().getStates());
        if(NullUtil.isNone(rtnWorkflowRouteVOList)) return;

        WorkflowRoute workflowRoute = DomUtil.toDom(rtnWorkflowRouteVOList.get(0).getObid(), false);
        if(WorkflowConstants.STATES_TYPE_DEFINE.equals(workflowRoute.getVo().getStates())) {
            workflowRoute.promote();
        }
        
        if(!WorkflowConstants.STATES_TYPE_INPROCESS.equals(workflowRoute.getVo().getStates())) {
            return;
        }
        
        //In Process인 WorkflowStep이 있는지 확인
        WorkflowStepVO rtnWorkflowStepVO = workflowRoute.getWorkflowStepVOListByStates(WorkflowConstants.STATES_TYPE_INPROCESS);
        WorkflowStep workflowStep = null;
        if(!NullUtil.isNull(rtnWorkflowStepVO)) {
            workflowStep = DomUtil.toDom(rtnWorkflowStepVO);
            
        }else{//진행 중인 Step이 없을 경우
            WorkflowStepVO workflowStepVO = workflowRoute.getFirstWorkflowStepVO();
            if(NullUtil.isNull(workflowStepVO)) return;
            
            workflowStep = DomUtil.toDom(workflowStepVO);
            if(WorkflowConstants.STATES_TYPE_DEFINE.equals(workflowStepVO.getStates())){
                workflowStep.promote();
            }
        }
        
        List<BusinessRelationObjectVO> rtnWorkflowStepNodeUserVOList = workflowStep.getWorkflowStepNodeUserVOList();
        for(BusinessRelationObjectVO businessRelationObjectVO : rtnWorkflowStepNodeUserVOList) {
            WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
            WorkflowInboxTaskVO workflowInboxTaskVO = workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUser(businessRelationObjectVO.getObid());

            if(NullUtil.isNull(workflowInboxTaskVO)) { //In Process인 WorkflowStep에서 신규건일 경우
                createWorkflowInboxTaskNRelations(businessObjectRoot, workflowRoute, workflowStepNodeUser);
                buildDelegatedInboxTask(businessObjectRoot, workflowRoute, workflowStep, workflowStepNodeUser);
            }else{ //Working 일 경우만 해당
                WorkflowInboxTask toBeUpdateWorkflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
                updateWorkflowInboxTask(toBeUpdateWorkflowInboxTask, workflowStepNodeUser);
            }
        }
    }

    /**
     *
     *
     * @param approvalVOListMap
     * @param approvalVOList
     */
    private void classifyApprovalVOListByRouteState(Map<String, List<ApprovalVO>> approvalVOListMap, List<ApprovalVO> approvalVOList)  {
        for(ApprovalVO approvalVO: approvalVOList) {
            List<ApprovalVO> approvalVOListByRouteState = approvalVOListMap.get(approvalVO.getState());
            if (NullUtil.isNone( approvalVOListByRouteState)) {
                approvalVOListByRouteState = new ArrayList<ApprovalVO>();
                approvalVOListMap.put(approvalVO.getState(), approvalVOListByRouteState);
            }
            approvalVOListByRouteState.add(approvalVO);
        }
    }

    private void updateWorkflowInboxTask(WorkflowInboxTask toBeUpdateWorkflowInboxTask, WorkflowStepNodeUser workflowNodeUser) {
        toBeUpdateWorkflowInboxTask.getVo().setRouteInstructions(workflowNodeUser.getVo().getRouteInstructions());
        toBeUpdateWorkflowInboxTask.getVo().setProcessRole(workflowNodeUser.getVo().getProcessRole());
        toBeUpdateWorkflowInboxTask.getVo().setParallelNodeProcessionRule(workflowNodeUser.getVo().getParallelNodeProcessionRule());
        toBeUpdateWorkflowInboxTask.getVo().setSequences(workflowNodeUser.getVo().getSequences());
        toBeUpdateWorkflowInboxTask.getVo().setIsEssential(workflowNodeUser.getVo().getIsEssential());
        toBeUpdateWorkflowInboxTask.getVo().setNotifyEmail(workflowNodeUser.getVo().getNotifyEmail());
        toBeUpdateWorkflowInboxTask.modifyObject();
    }

    /**
     * TODO
     * 1) 해당 Route에 Assignee가 한명도 없는 경우라도 자동으로 다음 State로 promote되는 로직은 존재 하지 않음.
     *
     * @param workflowRoute
     */
    private void startRoute(BusinessObjectRoot businessObjectRoot, WorkflowRoute toBeStartWorkflowRoute) {
        toBeStartWorkflowRoute.reset();
        toBeStartWorkflowRoute.start();

        WorkflowStepVO firstWorkflowStepVO = toBeStartWorkflowRoute.getFirstStep();
        if(NullUtil.isNull(firstWorkflowStepVO)) {
            LOGGER.debug("To be start workflow step does not exists.");
            return;
        }
        WorkflowStep firstWorkflowStep = DomUtil.toDom(firstWorkflowStepVO.getObid(), false);
        firstWorkflowStep.start();
        buildStepNodeUserAndInboxTaskWithDelegated(businessObjectRoot, toBeStartWorkflowRoute, firstWorkflowStep);
    }
    
    private void buildStepNodeUserAndInboxTaskWithDelegated(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, WorkflowStep toBeStartWorkflowStep) {
        List<BusinessRelationObjectVO> businessRationObjectVOList = null;
        Object object = businessObjectRoot.getVo().getOutData().get("sendBackToList");
        if( !NullUtil.isNull(object) ) {
            @SuppressWarnings("unchecked")
            List<ApprovalVO> sendBackToList = (List<ApprovalVO>)object;
            businessRationObjectVOList = new ArrayList<BusinessRelationObjectVO>();
            for( ApprovalVO approvalVO : sendBackToList ){
                WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(approvalVO.getWorkflowStepNodeUserObid(), false);
                businessRationObjectVOList.add( workflowStepNodeUser.getVo() );
            }
        }
        else{
            businessRationObjectVOList = toBeStartWorkflowStep.getWorkflowStepNodeUserVOList();
        }
        
        for( BusinessRelationObjectVO businessRelationObjectVO : businessRationObjectVOList ){
            WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
            WorkflowInboxTaskVO workflowInboxTaskVO = workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUserVO(workflowStepNodeUser.getVo());
            
            if( !NullUtil.isNull(workflowInboxTaskVO) ){//inboxtask 존재하면 workflowstepnodeuser를 초기화 시킴
                WorkflowInboxTask toBeRevisedWorkflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
                if(workflowStepNodeUser.getVo().getSelfReject()) {
                    toBeRevisedWorkflowInboxTask.deleteInboxTask(workflowRoute);
                    workflowStepNodeUser.deleteObject();
                    continue;
                }
                workflowStepNodeUser.getVo().setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_NONE);
                workflowStepNodeUser.getVo().setComments(null);
                workflowStepNodeUser.getVo().setActualCompletionDate((Date)null);
                workflowStepNodeUser.modifyObject();
                
                BusinessObjectVO revisedBusinessObjectVO = toBeRevisedWorkflowInboxTask.revise();
                //TODO revised 된 Inbox task 초기화 시켜야 함. WorkflowStepNodeUser의 정보도 반영 되어야 함.
                WorkflowInboxTask revisedWorkflowInboxTask = DomUtil.toDom(revisedBusinessObjectVO.getObid(), false);
                revisedWorkflowInboxTask.getVo().setParallelNodeProcessionRule(workflowStepNodeUser.getVo().getParallelNodeProcessionRule());
                revisedWorkflowInboxTask.getVo().setRouteInstructions(workflowStepNodeUser.getVo().getRouteInstructions());
                revisedWorkflowInboxTask.getVo().setSequences(workflowStepNodeUser.getVo().getSequences());
                revisedWorkflowInboxTask.getVo().setNotifyEmail(workflowStepNodeUser.getVo().getNotifyEmail());
                revisedWorkflowInboxTask.getVo().setProcessRole(workflowStepNodeUser.getVo().getProcessRole());
                revisedWorkflowInboxTask.getVo().setStep(String.valueOf(toBeStartWorkflowStep.getVo().getSequences()));
                revisedWorkflowInboxTask.modifyObject();

                revisedWorkflowInboxTask.reset(workflowRoute.getVo().getProcessTimestamp(), timeService.getDBLocalTime());
                createApprovalInfoToEP(businessObjectRoot, workflowRoute, revisedWorkflowInboxTask);
                if(!workflowRoute.getVo().getRouteBasePurpose().equals(WorkflowConstants.ROUTE_PURPOSE_STANDARD) && revisedWorkflowInboxTask.getVo().getNotifyEmail()) {
                    notifyMail(businessObjectRoot, workflowRoute, revisedWorkflowInboxTask.getUsersVO());
                }
            }
            else{
                createWorkflowInboxTaskNRelations(businessObjectRoot, workflowRoute, workflowStepNodeUser);
            }
            
            buildDelegatedInboxTask(businessObjectRoot, workflowRoute, toBeStartWorkflowStep, workflowStepNodeUser);
        }
    }
    
    /**
     * 위임건의 EP에 별도로 TODO notify 하지 않음. EP내에서 위임건에 대해 TODO 설정 함.
     *
     * @param businessObjectRoot
     * @param workflowRoute
     * @param workflowStep
     * @param workflowStepNodeUser
     */
    private void buildDelegatedInboxTask(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, WorkflowStep workflowStep, WorkflowStepNodeUser workflowStepNodeUser) {
        Users assginee = DomUtil.toDom(workflowStepNodeUser.getUsersVO());
        String originTaskOwner =  assginee.getVo().getNames();
        for(int idx = 0;; idx ++) {
            if(workflowRoute.getVo().getRouteBasePurpose().equals(WorkflowConstants.ROUTE_PURPOSE_DISTRIBUTION)) break;
            UsersVO delegateToAssigneeVO = assginee.getDelegatorUsersVO();
            if(NullUtil.isNull(delegateToAssigneeVO) || delegateToAssigneeVO.getObid().equals(assginee.getVo().getObid())) { //위임 설정이 없을 경우
                break;
            }
            
            if(idx == 0) {
                WorkflowInboxTaskVO originWorkflowInboxTaskVO =  workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUser(workflowStepNodeUser.getVo().getObid());
                originWorkflowInboxTaskVO.setOriginTaskOwner(originTaskOwner);
                WorkflowInboxTask originWorkflowInboxTask = DomUtil.toDom(originWorkflowInboxTaskVO);
                originWorkflowInboxTask.modifyObject();
            }
            
            /*
             * Create WorkflowInboxTask for delegate
             */
            WorkflowInboxTaskVO delegateWorkflowInboxTaskVO = new WorkflowInboxTaskVO();
            delegateWorkflowInboxTaskVO.setOriginTaskOwner(originTaskOwner);
            delegateWorkflowInboxTaskVO.setStep(String.valueOf(workflowStep.getVo().getSequences()));
            delegateWorkflowInboxTaskVO.setProcessTimestamp(workflowRoute.getVo().getProcessTimestamp());
            delegateWorkflowInboxTaskVO.setSequences(workflowStepNodeUser.getVo().getSequences());
            delegateWorkflowInboxTaskVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_INBOXTASKNAME));
            delegateWorkflowInboxTaskVO.setRouteAction(workflowStepNodeUser.getVo().getRouteAction());
            delegateWorkflowInboxTaskVO.setRouteInstructions(workflowStepNodeUser.getVo().getRouteInstructions());
            delegateWorkflowInboxTaskVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_NONE);
            delegateWorkflowInboxTaskVO.setApproversResponsibility(workflowStepNodeUser.getVo().getApproversResponsibility());
            delegateWorkflowInboxTaskVO.setTaskRequirement(workflowStepNodeUser.getVo().getTaskRequirement());
            delegateWorkflowInboxTaskVO.setRouteNodeObid("Delegated");
            delegateWorkflowInboxTaskVO.setReviewTask(workflowStepNodeUser.getVo().getReviewTask());
            delegateWorkflowInboxTaskVO.setReviewersComments(workflowStepNodeUser.getVo().getReviewersComments());
            delegateWorkflowInboxTaskVO.setReviewCommentsNeeded(workflowStepNodeUser.getVo().getReviewCommentsNeeded());
            delegateWorkflowInboxTaskVO.setDueDateOffset(workflowStepNodeUser.getVo().getDueDateOffset());
            delegateWorkflowInboxTaskVO.setDateOffsetFrom(workflowStepNodeUser.getVo().getDateOffsetFrom());
            delegateWorkflowInboxTaskVO.setAssigneeSetDueDate(workflowStepNodeUser.getVo().getAssigneeSetDueDate());
            delegateWorkflowInboxTaskVO.setAllowDelegation(workflowStepNodeUser.getVo().getAllowDelegation());
            delegateWorkflowInboxTaskVO.setIsEssential(workflowStepNodeUser.getVo().getIsEssential());
            delegateWorkflowInboxTaskVO.setParallelNodeProcessionRule(workflowStepNodeUser.getVo().getParallelNodeProcessionRule());
            delegateWorkflowInboxTaskVO.setTitles(businessObjectRoot.getCommonTitlesForDisplay());
            delegateWorkflowInboxTaskVO.setRevision("1");
            delegateWorkflowInboxTaskVO.setStates(WorkflowConstants.STATES_TYPE_ASSIGNED);//Assigned,Review,Complete
            delegateWorkflowInboxTaskVO.setActionComments(workflowStepNodeUser.getVo().getActionComments());
            delegateWorkflowInboxTaskVO.setTaskOwner(delegateToAssigneeVO.getNames());
            delegateWorkflowInboxTaskVO.setDescriptions(workflowRoute.getVo().getDescriptions()+"_"+workflowStep.getVo().getTitles());
            delegateWorkflowInboxTaskVO.setNotifyEmail(workflowStepNodeUser.getVo().getNotifyEmail());
            delegateWorkflowInboxTaskVO.setProcessRole(workflowStepNodeUser.getVo().getProcessRole());
            delegateWorkflowInboxTaskVO.setDelegatedFrom(assginee.getVo().getObid());

            if("Task Create Date".equals(workflowStepNodeUser.getVo().getDateOffsetFrom())
                     && workflowStepNodeUser.getVo().getDueDateOffset() > 0) {
                Calendar c = Calendar.getInstance();
                c.setTime(timeService.getDBLocalTime());
                c.add(Calendar.DATE, workflowStepNodeUser.getVo().getDueDateOffset());
                delegateWorkflowInboxTaskVO.setScheduledCompletionDate(c.getTime());
            }

            WorkflowInboxTask delegateWorkflowInboxTask = DomUtil.toDom(delegateWorkflowInboxTaskVO);
            delegateWorkflowInboxTask.createObject();
            /*
             *  Create WorkflowRouteTask Relation From:WorkflowRoute To:WorkflowInboxTask)
             *  select * from PTKEYTABLE a where a.pclass_name = 'WorkflowRouteTask'
             */
            delegateWorkflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK, workflowRoute.getVo(), new HashMap<String, Object>());
            /*
             * Create WorkflowProjectTask Relation From: Users To:WorkflowInboxTask
             *  select * from PTKEYTABLE a where a.pclass_name = 'WorkflowProjectTask'
             */
            delegateWorkflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWPROJECTTASK, delegateToAssigneeVO, new HashMap<String, Object>());
             
            if(!workflowRoute.getVo().getRouteBasePurpose().equals(WorkflowConstants.ROUTE_PURPOSE_STANDARD)) { //Standard (Working)을 제외하고 메일 발송
                notifyMail(businessObjectRoot, workflowRoute, delegateToAssigneeVO);
            }
            assginee = DomUtil.toDom(delegateToAssigneeVO);
        }
    }
    
    @SuppressWarnings("unchecked")
    private void createWorkflowRoute(BusinessObjectRoot businessObjectRoot, Map<String, Object> approvalVOListMapByRecodeMode) {
        List<ApprovalVO> toBeCreateApprovalVOList = (List<ApprovalVO>)approvalVOListMapByRecodeMode.get(GlobalConstants.CREATE_RECORD_MODE); //RouteState별 RecordMode가 [C]인 Approval List
        if(NullUtil.isNone(toBeCreateApprovalVOList)){
            return;
        }
        Map<Integer, List<ApprovalVO>> toBeCreateApprovalVOMapByStep = filterApprovalVOListByStep(toBeCreateApprovalVOList);
        WorkflowRoute createdWorkflowRoute = createWorkflowRoute(businessObjectRoot);
        Map<Integer, WorkflowStep> createdWorkflowStepMap = new HashMap<Integer, WorkflowStep>();
        createWorkflowStepListNSubStepList(businessObjectRoot, createdWorkflowStepMap, toBeCreateApprovalVOMapByStep);
        /*
         * Create WorkflowRouteStep Relation (From: WorkflowRoute, To: WorkflowStep)
         * select * from PRWORKFLOWRELATIONSHIP a where a.pclass_name = 'WorkflowRouteStep'
         * 각 State(Working, 1st Processing) 마다 첫번째 step(통상적으로 step 1일 경우)만 relation을 가짐.
         */

        TreeMap<Integer, WorkflowStep> sortedCreatedWorkflowStepTreeMap = new TreeMap<Integer, WorkflowStep>();
        sortedCreatedWorkflowStepTreeMap.putAll(createdWorkflowStepMap);

        createdWorkflowRoute.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP, sortedCreatedWorkflowStepTreeMap.firstEntry().getValue().getVo(), new HashMap<String, Object>());
        //Create WirkflowStepNodeUser & WorkflowInboxTask & WokrflowProjectTask
        createWorkflowStepNodeUserList(businessObjectRoot, createdWorkflowRoute, createdWorkflowStepMap, toBeCreateApprovalVOMapByStep);
    }

    /**
     *
     *
     * @param businessObjectRoot
     * @param workflowRoute
     * @param approvalVOListMapByRecodeMode
     */
    @SuppressWarnings("unchecked")
    private void updateWorkflow(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, Map<String, Object> approvalVOListMapByRecodeMode) {

        List<ApprovalVO> toBeCreateApprovalVOListByRecordMode = (List<ApprovalVO>)approvalVOListMapByRecodeMode.get(GlobalConstants.CREATE_RECORD_MODE); //RouteState별 RecordMode가 [C]인 Approval List
        List<ApprovalVO> toBeUpdateApprovalVOListByRecordMode = (List<ApprovalVO>)approvalVOListMapByRecodeMode.get(GlobalConstants.UPDATE_RECORD_MODE); //RouteState별 RecordMode가 [U]인 Approval List
        Set<String> toBeDeleteApprovalVOSetByRecordMode = (Set<String>)approvalVOListMapByRecodeMode.get(GlobalConstants.DELETE_RECORD_MODE); //RouteState별 RecordMode가 [D]인 Approval List

        Map<String, Object> existsWorkflowStepListMapNSubStepListMap = workflowRoute.getWorkflowStepNSubStepList();
        TreeMap<Integer, WorkflowStep> existsWorkflowStepListMap =
        (TreeMap<Integer, WorkflowStep>)existsWorkflowStepListMapNSubStepListMap.get("workflowStepListMap");
        List<WorkflowSubStep> workflowSubStepList =
                                (List<WorkflowSubStep>)existsWorkflowStepListMapNSubStepListMap.get("workflowSubStepList");
        if(toBeDeleteApprovalVOSetByRecordMode.size() > 0) {
            deleteWorkflowStepNodeUserList(existsWorkflowStepListMap, toBeDeleteApprovalVOSetByRecordMode);
            deleteWorkflowInboxTaskList(workflowRoute, toBeDeleteApprovalVOSetByRecordMode);
        }
        
        if(!NullUtil.isNone(toBeCreateApprovalVOListByRecordMode)) {
            Map<Integer, List<ApprovalVO>> addApprovalVOMap = filterApprovalVOListByStep(toBeCreateApprovalVOListByRecordMode);
            addWorkflowStepList(businessObjectRoot, existsWorkflowStepListMap, addApprovalVOMap);
            addWorkflowStepNodeUserList(businessObjectRoot, workflowRoute, existsWorkflowStepListMap, addApprovalVOMap);
        }

        if(!NullUtil.isNone(toBeUpdateApprovalVOListByRecordMode)){
            Map<Integer, List<ApprovalVO>> modifyApprovalVOMap = filterApprovalVOListByStep(toBeUpdateApprovalVOListByRecordMode);
            addWorkflowStepList(businessObjectRoot, existsWorkflowStepListMap, modifyApprovalVOMap);
            updateWorkflowStepNodeUserList(existsWorkflowStepListMap, toBeUpdateApprovalVOListByRecordMode);
        }
        
        
        //Step Node User가 없는 step 삭제
        for(Iterator<Map.Entry<Integer, WorkflowStep>> it = existsWorkflowStepListMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer, WorkflowStep> entry = it.next();
            WorkflowStep workflowStep = existsWorkflowStepListMap.get(entry.getKey());
            List<BusinessRelationObjectVO> workflowStepNodeUserVOList = workflowStep.getWorkflowStepNodeUserVOList();
            if(NullUtil.isNone(workflowStepNodeUserVOList)) {
                entry.getValue().deleteObject();
                it.remove();
            }
        }

        rebuildWorkflowRouteStepNWorkflowSubStep(workflowRoute, existsWorkflowStepListMap, workflowSubStepList);
    }

    private void rebuildWorkflowRouteStepNWorkflowSubStep(WorkflowRoute workflowRoute, Map<Integer, WorkflowStep> workflowStepListMap,
            List<WorkflowSubStep> workflowSubStepList) {
        for(WorkflowSubStep workflowSubStep : workflowSubStepList) {
            workflowSubStep.deleteObject();
        }

        List<BusinessRelationObjectVO> workflowRouteStepVOList = workflowRoute.getWorkflowRouteStepVOList();
        for(BusinessRelationObjectVO businessRelationObjectVO : workflowRouteStepVOList) {
            WorkflowRouteStep workflowRouteStep = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
            workflowRouteStep.deleteObject();
        }

        if(workflowStepListMap.size() > 0) {
            TreeMap<Integer, WorkflowStep> sortedWorkflowStepListTreeMap = new TreeMap<Integer, WorkflowStep>();
            sortedWorkflowStepListTreeMap.putAll(workflowStepListMap);
            workflowRoute.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP, sortedWorkflowStepListTreeMap.firstEntry().getValue().getVo(), new HashMap<String, Object>());
        }
        createWorkflowSubStep(workflowStepListMap);
    }

    /**
     * 
     * 위임으로 생성 된 inbox task는 대상에 포함되지 않음(단, origin inbox task는 포함)
     * @param workflowRoute
     * @param deletedWorkflowStepNodeUserObidSet
     */
    private void deleteWorkflowInboxTaskList(WorkflowRoute workflowRoute, Set<String> deletedWorkflowStepNodeUserObidSet) {
        if(NullUtil.isNull(workflowRoute) || deletedWorkflowStepNodeUserObidSet.size() == 0) return;

        List<WorkflowInboxTaskVO> workflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
        for(WorkflowInboxTaskVO workflowInboxTaskVO: workflowInboxTaskVOList) {
            if(deletedWorkflowStepNodeUserObidSet.contains(workflowInboxTaskVO.getRouteNodeObid())) {//삭제 대상
                if(StringUtils.isEmpty(workflowInboxTaskVO.getOriginTaskOwner())) { //일반적인 inbox task일 경우
                    WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
                    if(workflowInboxTask.getVo().getStates().equals(WorkflowConstants.STATES_TYPE_ASSIGNED))
                        createDeleteApprovalInfoToEP(null, workflowRoute, workflowInboxTask);
                    
                    workflowInboxTask.deleteInboxTask(workflowRoute);
                }else{
                    if(workflowInboxTaskVO.getOriginTaskOwner().equals(workflowInboxTaskVO.getTaskOwner())) { //origin inbox task일 경우 
                        WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
                        if(workflowInboxTask.getVo().getStates().equals(WorkflowConstants.STATES_TYPE_ASSIGNED))
                            createDeleteApprovalInfoToEP(null, workflowRoute, workflowInboxTask);
                        
                        workflowInboxTask.deleteInboxTask(workflowRoute);//위임건도 다 삭제 되어 짐.
                    }
                }
            }
        }
    }

    private void addWorkflowStepList(BusinessObjectRoot businessObjectRoot, Map<Integer, WorkflowStep> workflowStepListMap, Map<Integer, List<ApprovalVO>>  approvalVOListByStep) {
        for (Map.Entry<Integer, List<ApprovalVO>> entry : approvalVOListByStep.entrySet()) {
            if(NullUtil.isNull(workflowStepListMap.get(entry.getKey()))){
                workflowStepListMap.put(entry.getKey(), createWorkflowStep(businessObjectRoot, entry.getKey()));
            }
        }
    }

    private Map<Integer, List<ApprovalVO>> filterApprovalVOListByStep(List<ApprovalVO> approvalVOList) {
        List<ApprovalVO> safeInputList = NullUtil.isNull(approvalVOList) ? Collections.<ApprovalVO>emptyList() : approvalVOList;
        Map<Integer, List<ApprovalVO>> rtnApprovalVOStepList = new TreeMap<Integer, List<ApprovalVO>>();
        for(ApprovalVO approvalVO: safeInputList) {
            List<ApprovalVO> approvalList = rtnApprovalVOStepList.get(approvalVO.getStep());
            if (NullUtil.isNone(approvalList)) {
                approvalList = new ArrayList<ApprovalVO>();
                rtnApprovalVOStepList.put(approvalVO.getStep(), approvalList);
            }
            approvalList.add(approvalVO);
        }
        return rtnApprovalVOStepList;
    }

    private void createWorkflowStepNodeUserList(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute,
            Map<Integer, WorkflowStep> workflowStepMap, Map<Integer, List<ApprovalVO>> approvalVOListMap) {
        for (Map.Entry<Integer, WorkflowStep> entry : workflowStepMap.entrySet()) {
            List<ApprovalVO> approvalVOList = approvalVOListMap.get(entry.getKey());
            if(!NullUtil.isNone(approvalVOList)){
                createWorkflowStepNodeUserList(businessObjectRoot, workflowStepMap.get(entry.getKey()), approvalVOList);
            }
        }
    }

    private void addWorkflowStepNodeUserList(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute,
            Map<Integer, WorkflowStep> workflowStepMap, Map<Integer, List<ApprovalVO>> approvalVOListMap) {
        for (Map.Entry<Integer, List<ApprovalVO>> entry : approvalVOListMap.entrySet()) {
            List<ApprovalVO> approvalVOListByStep = approvalVOListMap.get(entry.getKey());
            if(!NullUtil.isNone(approvalVOListByStep)) {
                WorkflowStep workflowStep = workflowStepMap.get(entry.getKey());
//                if(GpdmConstants.APPROVAL_STATUS_COMPLETE.equals(workflowStep.getVo().getStates())) throw new GpdmException("You can\'t create the step was completed.");
                createWorkflowStepNodeUserList(businessObjectRoot, workflowStep, approvalVOListByStep);
            }
        }
    }

    private void createWorkflowInboxTaskNRelations(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, WorkflowStepNodeUser workflowStepNodeUser) {
        //select * from PTINBOXTASK
        WorkflowInboxTaskVO workflowInboxTaskVO = new WorkflowInboxTaskVO();
        WorkflowStep workflowStep = DomUtil.toDom(workflowStepNodeUser.getVo().getFromObid(), false);
        workflowInboxTaskVO.setStep(String.valueOf(workflowStep.getVo().getSequences()));
        workflowInboxTaskVO.setProcessTimestamp(workflowRoute.getVo().getProcessTimestamp());
        workflowInboxTaskVO.setSequences(workflowStepNodeUser.getVo().getSequences());
        workflowInboxTaskVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_INBOXTASKNAME));
        workflowInboxTaskVO.setRouteAction(workflowStepNodeUser.getVo().getRouteAction());
        workflowInboxTaskVO.setRouteInstructions(workflowStepNodeUser.getVo().getRouteInstructions());
        workflowInboxTaskVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_NONE);
        workflowInboxTaskVO.setApproversResponsibility(workflowStepNodeUser.getVo().getApproversResponsibility());
        workflowInboxTaskVO.setTaskRequirement(workflowStepNodeUser.getVo().getTaskRequirement());
        workflowInboxTaskVO.setRouteNodeObid(workflowStepNodeUser.getVo().getRouteNodeObid());
        workflowInboxTaskVO.setReviewTask(workflowStepNodeUser.getVo().getReviewTask());
        workflowInboxTaskVO.setReviewersComments(workflowStepNodeUser.getVo().getReviewersComments());
        workflowInboxTaskVO.setReviewCommentsNeeded(workflowStepNodeUser.getVo().getReviewCommentsNeeded());
        workflowInboxTaskVO.setDueDateOffset(workflowStepNodeUser.getVo().getDueDateOffset());
        workflowInboxTaskVO.setDateOffsetFrom(workflowStepNodeUser.getVo().getDateOffsetFrom());
        workflowInboxTaskVO.setAssigneeSetDueDate(workflowStepNodeUser.getVo().getAssigneeSetDueDate());
        workflowInboxTaskVO.setAllowDelegation(workflowStepNodeUser.getVo().getAllowDelegation());
        workflowInboxTaskVO.setIsEssential(workflowStepNodeUser.getVo().getIsEssential());
        workflowInboxTaskVO.setParallelNodeProcessionRule(workflowStepNodeUser.getVo().getParallelNodeProcessionRule());
        workflowInboxTaskVO.setTitles(businessObjectRoot.getCommonTitlesForDisplay());
        workflowInboxTaskVO.setRevision("1");
        workflowInboxTaskVO.setStates(WorkflowConstants.STATES_TYPE_ASSIGNED);//Assigned,Review,Complete
        workflowInboxTaskVO.setActionComments(workflowStepNodeUser.getVo().getActionComments());
        workflowInboxTaskVO.setTaskOwner(workflowStepNodeUser.getUsersVO().getNames());
        workflowInboxTaskVO.setDescriptions(workflowRoute.getVo().getDescriptions()+"_"+workflowStep.getVo().getTitles());
        workflowInboxTaskVO.setNotifyEmail(workflowStepNodeUser.getVo().getNotifyEmail());
        workflowInboxTaskVO.setProcessRole(workflowStepNodeUser.getVo().getProcessRole());

        if("Task Create Date".equals(workflowStepNodeUser.getVo().getDateOffsetFrom())
                && workflowStepNodeUser.getVo().getDueDateOffset() > 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(timeService.getDBLocalTime());
            c.add(Calendar.DATE, workflowStepNodeUser.getVo().getDueDateOffset());
            workflowInboxTaskVO.setScheduledCompletionDate(c.getTime());
        }

        WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
        workflowInboxTask.createObject();
       /*
        *  Create WorkflowRouteTask Relation From:WorkflowRoute To:WorkflowInboxTask)
        *  select * from PTKEYTABLE a where a.pclass_name = 'WorkflowRouteTask'
        */
        workflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK, workflowRoute.getVo(), new HashMap<String, Object>());
       /*
        * Create WorkflowProjectTask Relation From: Users To:WorkflowInboxTask
        *  select * from PTKEYTABLE a where a.pclass_name = 'WorkflowProjectTask'
        */
        workflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWPROJECTTASK, workflowStepNodeUser.getUsersVO(), new HashMap<String, Object>());
        
        createApprovalInfoToEP(businessObjectRoot, workflowRoute, workflowInboxTask);
        //if(!workflowRoute.getVo().getRouteBasePurpose().equals(WorkflowConstants.ROUTE_PURPOSE_STANDARD)) {
        if(!workflowRoute.getVo().getRouteBasePurpose().equals(WorkflowConstants.ROUTE_PURPOSE_STANDARD) && workflowInboxTask.getVo().getNotifyEmail()) {
            notifyMail(businessObjectRoot, workflowRoute, workflowInboxTask.getUsersVO());
        }
    }

    private void updateWorkflowStepNodeUserList(TreeMap<Integer, WorkflowStep> workflowStepListMap,  List<ApprovalVO> approvalVOList) {
        for(ApprovalVO approvalVO: approvalVOList) {
            WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(approvalVO.getWorkflowStepNodeUserObid(), false);
            WorkflowStepNodeUserVO workflowStepNodeUserVO = workflowStepNodeUser.getVo();
            WorkflowStep workflowStep = workflowStepListMap.get(approvalVO.getStep());
            if(!workflowStepNodeUserVO.getToObid().equals(approvalVO.getAssigneeObid())) { //결재자를 수정 한 경우
                Map<String, Object> attributeMap = new HashMap<String, Object>();
                attributeMap.put("routeAction", workflowStepNodeUser.getVo().getRouteAction());
                attributeMap.put("routeInstructions", workflowStepNodeUser.getVo().getRouteInstructions());
                attributeMap.put("dueDateOffset", workflowStepNodeUser.getVo().getDueDateOffset());
                attributeMap.put("isEssential",  workflowStepNodeUser.getVo().getIsEssential()); //결재자가 필수 결재자 인지 아닌지 여부
                attributeMap.put("parallelNodeProcessionRule", workflowStepNodeUser.getVo().getParallelNodeProcessionRule());
                attributeMap.put("sequences", workflowStepNodeUser.getVo().getSequences());
                attributeMap.put("titles", workflowStepNodeUser.getVo().getTitles());
                attributeMap.put("notifyEmail", workflowStepNodeUser.getVo().getNotifyEmail());
                attributeMap.put("processRole", workflowStepNodeUser.getVo().getProcessRole());
                
                Users users = DomUtil.toDom(approvalVO.getAssigneeObid(), false);
                BusinessRelationObjectVO  businessRelationObjectVO = workflowStep.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWSTEPNODEUSER, users.getVo(), attributeMap);
                //modify RouteNodeObid
                WorkflowStepNodeUser toModifiedWorkflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
                toModifiedWorkflowStepNodeUser.getVo().setRouteNodeObid(businessRelationObjectVO.getObid());
                toModifiedWorkflowStepNodeUser.modifyObject();
                workflowStepNodeUser.deleteObject();
            }else{
                workflowStepNodeUserVO.setParallelNodeProcessionRule(approvalVO.getParallelNodeProcessionRule());
                workflowStepNodeUserVO.setIsEssential(("Y".equals(approvalVO.getEssential()))? true : false); //결재자가 필수 결재자 인지 아닌지 여부);
                workflowStepNodeUserVO.setSequences(approvalVO.getSequence());
                workflowStepNodeUserVO.setRouteInstructions(approvalVO.getRouteInstructions());
                workflowStepNodeUserVO.setNotifyEmail(approvalVO.getNotifyEmail());
                workflowStepNodeUserVO.setProcessRole(approvalVO.getProcessRole());
                workflowStepNodeUser.modifyObject();
                
                if(!workflowStepNodeUserVO.getFromObid().equals(workflowStep.getVo().getObid())) workflowStepNodeUser.changeFromObject(workflowStep.getVo());
            }
        }
    }

    private void deleteWorkflowStepNodeUserList(TreeMap<Integer, WorkflowStep> workflowStepListMap, Set<String> approvalVOSet) {
        Iterator<String> it = approvalVOSet.iterator();
        while(it.hasNext()) {
            WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(it.next(), false);
            //TODO 삭제 전 Validation Check
            WorkflowStep workflowStep = DomUtil.toDom(workflowStepNodeUser.getVo().getFromObid(), false);
            if(WorkflowConstants.APPROVAL_STATUS_COMPLETE.equals(workflowStep.getVo().getStates())){
                new ApplicationException("You can\'t delete the step was completed.");
            }
            workflowStepNodeUser.deleteObject();
        }
    }

    private void createWorkflowStepNodeUserList(BusinessObjectRoot businessObjectRoot, WorkflowStep workflowStep,
            List<ApprovalVO> approvalVOList) {
        for(ApprovalVO approvalVO: approvalVOList) {
            createWorkflowStepNodeUser(businessObjectRoot, workflowStep, approvalVO);
        }
    }

    private void createWorkflowStepNodeUser( BusinessObjectRoot businessObjectRoot, WorkflowStep workflowStep,
            ApprovalVO approvalVO) {
        //select * from PRSTEPNODEUSER
        StateInfo stateInfo = (StateInfo)businessObjectRoot.getVo().getOutData().get("stateInfo");
        Users assignee = DomUtil.toDom(approvalVO.getAssigneeObid(), false);
        Map<String, Object> attributeMap = new HashMap<String, Object>();

        if(WorkflowConstants.ROUTE_PURPOSE_STANDARD.equals(stateInfo.getDefaultRoutePurpose())) {
            attributeMap.put("routeAction", WorkflowConstants.ACTION_TYPE_END_WORKING);
            attributeMap.put("actionComments", WorkflowConstants.ACTION_COMMENTS_APPROVE);
        }else if(WorkflowConstants.ROUTE_PURPOSE_DISTRIBUTION.equals(stateInfo.getDefaultRoutePurpose())) {
            attributeMap.put("routeAction", WorkflowConstants.ROUTE_ACTIONS_COMMENT);
            attributeMap.put("actionComments", WorkflowConstants.ACTION_COMMENTS_ACKNOWLEDGE);
        }else if(WorkflowConstants.ROUTE_PURPOSE_CONFIRMATION.equals(stateInfo.getDefaultRoutePurpose())) {
            attributeMap.put("routeAction", WorkflowConstants.ROUTE_ACTIONS_CONFIRM);
            attributeMap.put("actionComments", WorkflowConstants.ACTION_COMMENTS_CONFIRM);
        }else if(WorkflowConstants.ROUTE_PURPOSE_APPROVAL.equals(stateInfo.getDefaultRoutePurpose())) {
            attributeMap.put("routeAction", WorkflowConstants.ROUTE_ACTIONS_APPROVE);
            attributeMap.put("actionComments", WorkflowConstants.ACTION_COMMENTS_APPROVE);
        }
//        else if(WorkflowConstants.ROUTE_PURPOSE_REVIEW.equals(stateInfo.getDefaultRoutePurpose())) {
//            attributeMap.put("actionComments", WorkflowConstants.ACTION_COMMENTS_APPROVE);
//        }
//
        if(WorkflowConstants.TRUE.equals(stateInfo.getInboxTaskAutoComplete())) {
            attributeMap.put("dueDateOffset", stateInfo.getDateOffsetDay());
        }else{
            attributeMap.put("dueDateOffset", 0);
        }

        attributeMap.put("isEssential",  ("Y".equals(approvalVO.getEssential()))? true : false); //결재자가 필수 결재자 인지 아닌지 여부
        attributeMap.put("parallelNodeProcessionRule", approvalVO.getParallelNodeProcessionRule());
        if(NullUtil.isNull(approvalVO.getSequence())){
            approvalVO.setSequence(workflowStep.getNextSequenceOfWorkflowStepNodeUser());
        }
        attributeMap.put("sequences", approvalVO.getSequence());
        attributeMap.put("titles", businessObjectRoot.getCommonTitlesForDisplay());
        attributeMap.put("selfReject", approvalVO.getSelfReject());
        attributeMap.put("routeInstructions", approvalVO.getRouteInstructions());
        attributeMap.put("notifyEmail", approvalVO.getNotifyEmail());
        attributeMap.put("processRole", approvalVO.getProcessRole());

        BusinessRelationObjectVO  businessRelationObjectVO = workflowStep.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWSTEPNODEUSER, assignee.getVo(), attributeMap);

        //modify RouteNodeObid
        WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
        workflowStepNodeUser.getVo().setRouteNodeObid(businessRelationObjectVO.getObid());
        workflowStepNodeUser.modifyObject();
    }

    private Map<Integer, WorkflowStep> createWorkflowStepListNSubStepList(BusinessObjectRoot businessObjectRoot, Map<Integer, WorkflowStep> workflowStepMap,
            Map<Integer, List<ApprovalVO>> toBeCreateApprovalVOMapByStep) {
            //select * from PRSTEPNODEUSER
        for (Map.Entry<Integer, List<ApprovalVO>> entry : toBeCreateApprovalVOMapByStep.entrySet()) {
            if(NullUtil.isNull(entry.getKey()) ){
                for(ApprovalVO approvalVO : entry.getValue()) {
                    LOGGER.debug("=============> State:"+approvalVO.getState() + "Step:" + approvalVO.getStep());
                }
                throw new ApplicationException("createWorkflowStepListNSubStepList Error");
            }

            workflowStepMap.put(entry.getKey(), createWorkflowStep(businessObjectRoot, entry.getKey()));
        }
       createWorkflowSubStep(workflowStepMap);
       return workflowStepMap;
    }

    private WorkflowStep createWorkflowStep(BusinessObjectRoot businessObjectRoot, Integer step) {
        //select * from PTSTEP
       StateInfo stateInfo = (StateInfo)businessObjectRoot.getVo().getOutData().get("stateInfo");
       WorkflowStepVO workflowStepVO = new WorkflowStepVO();
       workflowStepVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_STEPNAME));
       workflowStepVO.setSequences(step);
       workflowStepVO.setStates(WorkflowConstants.STATES_TYPE_DEFINE);
       workflowStepVO.setDescriptions(businessObjectRoot.getVo().getObid()+"_"+stateInfo.getStateName());
       workflowStepVO.setTitles("Step "+ step);

       WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
       workflowStep.createObject();
       return workflowStep;
    }

   /**
    *
    *
    * @param workflowStepLinkedList
    */
    private void createWorkflowSubStep(Map<Integer, WorkflowStep> workflowStepMap) {
        if(workflowStepMap.size() == 0){
            return;
        }
        WorkflowStep previousWorkflowStep = null;

        TreeMap<Integer, WorkflowStep> sortedWorkflowStepTreeMap = new TreeMap<Integer, WorkflowStep>();
        sortedWorkflowStepTreeMap.putAll(workflowStepMap);
        for (Map.Entry<Integer, WorkflowStep> entry : sortedWorkflowStepTreeMap.entrySet()) {
            WorkflowStep workflowStep = workflowStepMap.get(entry.getKey());
            if(!NullUtil.isNull(previousWorkflowStep)) {
                workflowStep.addFromObject( ApplicationSchemaConstants.RELCLASS_WORKFLOWSUBSTEP, previousWorkflowStep.getVo(), new HashMap<String, Object>());
            }
            previousWorkflowStep = workflowStep;
        }
    }

    private Map<String, Object> splitApprovalVOByRecodeMode(List<ApprovalVO> approvalVOList) {
        Map<String, Object> approvalVOMapByRecodeMode = new HashMap<String, Object>();
        if(null == approvalVOList){
            return approvalVOMapByRecodeMode;
        }

        List<ApprovalVO> approvalVOListForCreate = new ArrayList<ApprovalVO>();
        List<ApprovalVO> approvalVOListForUpdate = new ArrayList<ApprovalVO>();
        Set<String> approvalVOSetForDelete = new HashSet<String>();

        for(ApprovalVO approvalVO: approvalVOList) {
            if(GlobalConstants.CREATE_RECORD_MODE.equals(approvalVO.getRecordMode())) {
                approvalVOListForCreate.add(approvalVO);
            }else if(GlobalConstants.UPDATE_RECORD_MODE.equals(approvalVO.getRecordMode())) {
                approvalVOListForUpdate.add(approvalVO);
            }else if(GlobalConstants.DELETE_RECORD_MODE.equals(approvalVO.getRecordMode())) {
                if(!StringUtils.isEmpty(approvalVO.getWorkflowStepNodeUserObid()))
                    approvalVOSetForDelete.add(approvalVO.getWorkflowStepNodeUserObid());
            }
        }
        approvalVOMapByRecodeMode.put(GlobalConstants.CREATE_RECORD_MODE, approvalVOListForCreate);
        approvalVOMapByRecodeMode.put(GlobalConstants.UPDATE_RECORD_MODE, approvalVOListForUpdate);
        approvalVOMapByRecodeMode.put(GlobalConstants.DELETE_RECORD_MODE, approvalVOSetForDelete);
        return approvalVOMapByRecodeMode;
    }

    private void submitAcknowledge(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, WorkflowStepNodeUserVO workflowStepNodeUserVO) {
        createSubmitApprovalInfoToEP(businessObjectRoot, workflowRoute, workflowStepNodeUserVO, WorkflowConstants.APPROVAL_STATUS_APPROVE);
        WorkflowStep workflowStep = DomUtil.toDom(workflowStepNodeUserVO.getFromObid());
        if(workflowStep.isAllApproved(workflowRoute)) {
            workflowStep.promote();
            workflowRoute.promote();
            if("Promote Connected Object".equals( workflowRoute.getVo().getRouteCompletionAction())) {
                //Next Route로 Inbox Task 생성 해야 함.
                StateInfo rtnLifyCycleVO = LifeCycleUtil.getTargetState(businessObjectRoot.getVo(), GlobalConstants.WORKFLOW_TYPE_PROMOTE);
                businessObjectRoot.promote();
                txnStartRoute(businessObjectRoot, rtnLifyCycleVO.getStateName());
          }
        }
    }

    /**
     * 
     *
     * @param businessObjectRoot
     * @param workflowRoute
     * @param workflowStepNodeUserVO
     */
    private void submitApprove(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, WorkflowStepNodeUserVO workflowStepNodeUserVO) {
        createSubmitApprovalInfoToEP(businessObjectRoot, workflowRoute, workflowStepNodeUserVO, WorkflowConstants.APPROVAL_STATUS_APPROVE);
        WorkflowStep workflowStep = DomUtil.toDom(workflowStepNodeUserVO.getFromObid());
        /*
         * Any일 경우에는 해당 Step의 모든 결재를 승인으로 한 후
         */
        boolean goToTheNext = false;
        if(WorkflowConstants.ROUTE_ACTION_ANY.equals(workflowStepNodeUserVO.getParallelNodeProcessionRule())) {//Next Step 또는 Branch 정보로 Next Route로 이동
            /*
             *  1) 현재 Step의 결재 중 승인 건 외에는 모드 삭제 함.(InboxTask, Route Node)
             *  2) 다음 Stpe이 존재 하는지 체크하여
             *          Next Step(O)일 경우에는 다음 Step의 결재의 Inbox task를 생성
             *          Next Step(X) Final일 경우에는 Branch 정보로 Next Route로 이동 후 start route 시킴
             */
            //현 Step의 다른 Any type의 Inbox Task 삭제
//            deleteWorkflowInboxTaskNRouteNodeExceptThis(workflowRoute, workflowStep, workflowStepNodeUserVO);
            deleteWorkflowInboxTaskExceptThis(workflowRoute, workflowStep, workflowStepNodeUserVO);
            goToTheNext = true;
        }else if(WorkflowConstants.ROUTE_ACTION_ALL.equals(workflowStepNodeUserVO.getParallelNodeProcessionRule()) && workflowStep.isAllApproved(workflowRoute)) {
            goToTheNext = true;
        }

        if(goToTheNext) { //현재 Step의 모든 결재가 완료 된 상태이면
            workflowStep.promote();
            WorkflowStepVO nextWorkflowStepVO = workflowRoute.getNextWorkflowStepVO(workflowStep.getVo().getSequences());
            String routeCompletionAction = workflowRoute.getVo().getRouteCompletionAction();

            if(NullUtil.isNull(nextWorkflowStepVO)) { //final Step이면
                workflowRoute.promote();
                if("Promote Connected Object".equals(routeCompletionAction)) {
                    //Next Route로 Inbox Task 생성 해야 함.
                    StateInfo stateInfo = businessObjectRoot.getTargetState(GlobalConstants.WORKFLOW_TYPE_PROMOTE);
                    businessObjectRoot.promote();
                    txnStartRoute(businessObjectRoot, stateInfo.getStateName());
                }
            }else{
                WorkflowStep nextWorkflowStep = DomUtil.toDom(nextWorkflowStepVO.getObid(), false);
                nextWorkflowStep.start();
                buildStepNodeUserAndInboxTaskWithDelegated(businessObjectRoot, workflowRoute, nextWorkflowStep);
            }
        }
    }

    /**
     * 
     *
     * @param workflowRoute
     * @param workflowStep
     * @param workflowStepNodeUserVO
     */
    private void deleteWorkflowInboxTaskExceptThis(WorkflowRoute workflowRoute, WorkflowStep workflowStep,
            WorkflowStepNodeUserVO workflowStepNodeUserVO) {
        List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList = workflowStep.getWorkflowStepNodeUserVOList();
        Set<String> deletedWorkflowStepNodeUserObidSet = new HashSet<String>();
        for(BusinessRelationObjectVO businessRelationObjectVO : rtnBusinessRelationObjectVOList) {
            WorkflowStepNodeUser toBeDeleteWorkflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid());
            if(!workflowStepNodeUserVO.getObid().equals(businessRelationObjectVO.getObid())
                ||  !WorkflowConstants.APPROVAL_STATUS_APPROVE.equals(toBeDeleteWorkflowStepNodeUser.getVo().getApprovalStatus())) {
                deletedWorkflowStepNodeUserObidSet.add(businessRelationObjectVO.getObid());
                toBeDeleteWorkflowStepNodeUser.getVo().setComments("Automatically skiped by system.");
                toBeDeleteWorkflowStepNodeUser.getVo().setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_APPROVE);
                toBeDeleteWorkflowStepNodeUser.getVo().setActualCompletionDate(timeService.getDBLocalTime());
                toBeDeleteWorkflowStepNodeUser.modifyObject();
            }
        }
        deleteWorkflowInboxTaskList(workflowRoute, deletedWorkflowStepNodeUserObidSet);
    }

    /**
     * 
     *
     * @param businessObjectRoot
     * @param workflowRoute
     * @param workflowStepNodeUserVO
     */
    private void submitReject(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, WorkflowStepNodeUserVO workflowStepNodeUserVO) {
        if(!NullUtil.isNull(workflowStepNodeUserVO))
            createSubmitApprovalInfoToEP(businessObjectRoot, workflowRoute, workflowStepNodeUserVO, WorkflowConstants.APPROVAL_STATUS_REJECT);

        //같은 Step에 결재 요청중인 InboxTask 삭제
        List<WorkflowInboxTaskVO> toBeDeleteWorkflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
        for(WorkflowInboxTaskVO workflowInboxTaskVO : toBeDeleteWorkflowInboxTaskVOList) {
            if(!WorkflowConstants.STATES_TYPE_ASSIGNED.equals(workflowInboxTaskVO.getStates())) continue;
            WorkflowInboxTask toBeDeleteWorkflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
            toBeDeleteWorkflowInboxTask.deleteInboxTask(workflowRoute);
            createDeleteApprovalInfoToEP(businessObjectRoot, workflowRoute, toBeDeleteWorkflowInboxTask);
        }
        
        List<String> rtnStateList = businessObjectRoot.getStateListByStateName();
        int currentRouteStatesIndex = rtnStateList.indexOf(businessObjectRoot.getVo().getStates());
        businessObjectRoot.promote();
        BusinessObjectRoot rejectedBusinessObjectRoot = DomUtil.toDom(businessObjectRoot.getVo().getObid(), false);
        int rejectTargetRouteStatesIndex = rtnStateList.indexOf(rejectedBusinessObjectRoot.getVo().getStates());
        for(int idx = currentRouteStatesIndex ; idx >= rejectTargetRouteStatesIndex; idx --) {
            List<ObjectRootVO> rtnWorkflowRouteVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(rtnStateList.get(idx));
            if(!NullUtil.isNone(rtnWorkflowRouteVOList)) {
                WorkflowRoute toBeResetWorkflowRoute = DomUtil.toDom(rtnWorkflowRouteVOList.get(0));
                toBeResetWorkflowRoute.reset();
                toBeResetWorkflowRoute.resetStepList();
            }
        }
        /*
         * Restart WorkflowRoute
         */
        StateInfo restartState =
                businessObjectRoot.getStateByStateName(rejectedBusinessObjectRoot.getVo().getStates());
        if(WorkflowConstants.TRUE.equals(restartState.getRouteAutoStartOnReject())) {
            businessObjectRoot.getVo().getOutData().put("approvalStatus", WorkflowConstants.APPROVAL_STATUS_REJECT);
            txnStartRoute(businessObjectRoot, businessObjectRoot.getVo().getStates());
        }
        
        //Send Mail For Reject (For creator)
        MailSendVO mailSendVO = new MailSendVO();
        mailSendVO.setSendType(WorkflowConstants.MAIL_TYPE_REJECT);
        mailSendVO.setObid(businessObjectRoot.getVo().getObid());
        mailSendVO.setClassName(businessObjectRoot.getVo().getClassName());
        mailSendVO.setNames(businessObjectRoot.getVo().getNames());
        mailSendVO.setCurrentStatus( businessObjectRoot.getVo().getStates() );
        mailSendVO.setFromUserId(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID));
        List<String> toUserIdList = new ArrayList<String>();
        
        
        toUserIdList.add(rejectedBusinessObjectRoot.getVo().getCreator());

        // 2018.04.04 kangkil  PRM 반려의 경우 승인 요청자에게 반려 메일 발송
        if(businessObjectRoot.getVo().getClassName().equals("ProdPlanPRMSheet")){
            List<BusinessRelationObjectVO> stepNodeUserList = retrieveAssigneeTargetStates(businessObjectRoot, ApplicationSchemaConstants.STATE_PRODUCTPLAN_PRM_WORKING);
            if( !NullUtil.isNone(stepNodeUserList) ){
                Users userDom = null;
                for(BusinessRelationObjectVO ro: stepNodeUserList ){
                    if(ro.getAttribute("comments")==null){
                        userDom = DomUtil.toDom( ro.getToObid() );
                        toUserIdList.add(userDom.getNames());
                    }
                }
                
            }
        } 
        
        mailSendVO.setToUserIdList(toUserIdList);
        txnCreateMailSendInfo(mailSendVO);
    }
    

    private void validateForReassign(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, ReassignVO reassignVO, WorkflowStepNodeUser beforeReassignWorkflowStepNodeUser) {
        if(beforeReassignWorkflowStepNodeUser.getVo().getToObid().equals(reassignVO.getToAssigneeObid())){
            throw new ApplicationException("It cannot be reassigned to the same person.");
        }
      //C20150918_76875 필수결재자 Reassign 가능하도록 설정 변경 요청 건
      //  if(beforeReassignWorkflowStepNodeUser.getVo().getIsEssential())
      //      throw new GpdmException("It cannot be reassigned a essential approver");
        
        List<WorkflowStepVO> rtnWorkflowStepVOList = workflowRoute.getAllWorkflowStepVOList();
        for(WorkflowStepVO workflowStepVO : rtnWorkflowStepVOList) {
            // 2017.12.19 youngmi.won 동은 State 다른 Step 에는 동일 결재자 추가할 수 있도록 로직 수정
            if(!beforeReassignWorkflowStepNodeUser.getVo().getFromObid().equals(workflowStepVO.getObid())) continue;
            
            WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
            List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList = workflowStep.getWorkflowStepNodeUserVOList();
            for(BusinessRelationObjectVO businessRelationObjectVO : rtnBusinessRelationObjectVOList) {
                WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO);
                if(reassignVO.getToAssigneeObid().equals(workflowStepNodeUser.getUsersVO().getObid())) {
                    throw new ApplicationException("Person to reassign already exists in state.");
                }
            }
        }
        
//        // 2018.02.19 youngmi.won PRI PCO : 검사기술인원인 경우 Reassign 불가하도록 Validation 추가
//        if( ApplicationSchemaConstants.BIZCLASS_PRIPROPERTY.equals(businessObjectRoot.getClassName())
//                && ApplicationSchemaConstants.STATE_PRI_APPROVING.equals(businessObjectRoot.getStates()) ){
//            List<UsersVO> gpinApproverList = PDRApproverGroup.getGripApproverList();
//            for( int inx = 0; inx < gpinApproverList.size(); inx++ ){
//                if( gpinApproverList.get(inx).getObid().equals(beforeReassignWorkflowStepNodeUser.getVo().getToObid()) ){
//                    throw new ApplicationException("Cannot reassign from GPIN approver to another user.<br>[PRI PCO No : " + businessObjectRoot.getVo().getTitles()  + "]");
//                }
//            }
//        }
    }

    private void createWorkflowInboxTaskNRelationsForReassign(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, WorkflowStep workflowStep, 
            WorkflowStepNodeUser toWorkflowStepNodeUser, UsersVO fromAssigneeVO) {
        WorkflowInboxTaskVO toWorkflowInboxTaskVO = new WorkflowInboxTaskVO();
        toWorkflowInboxTaskVO.setStep(workflowStep.getVo().getSequences().toString());
        toWorkflowInboxTaskVO.setProcessTimestamp(workflowRoute.getVo().getProcessTimestamp());
        toWorkflowInboxTaskVO.setSequences(toWorkflowStepNodeUser.getVo().getSequences());
        toWorkflowInboxTaskVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_INBOXTASKNAME));
        toWorkflowInboxTaskVO.setRouteAction(toWorkflowStepNodeUser.getVo().getRouteAction());
        toWorkflowInboxTaskVO.setRouteInstructions(toWorkflowStepNodeUser.getVo().getRouteInstructions());
        toWorkflowInboxTaskVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_NONE);
        toWorkflowInboxTaskVO.setApproversResponsibility(toWorkflowStepNodeUser.getVo().getApproversResponsibility());
        toWorkflowInboxTaskVO.setTaskRequirement(toWorkflowStepNodeUser.getVo().getTaskRequirement());
        toWorkflowInboxTaskVO.setRouteNodeObid(toWorkflowStepNodeUser.getVo().getRouteNodeObid());
        toWorkflowInboxTaskVO.setReviewTask(toWorkflowStepNodeUser.getVo().getReviewTask());
        toWorkflowInboxTaskVO.setReviewersComments(toWorkflowStepNodeUser.getVo().getReviewersComments());
        toWorkflowInboxTaskVO.setReviewCommentsNeeded(toWorkflowStepNodeUser.getVo().getReviewCommentsNeeded());
        toWorkflowInboxTaskVO.setDueDateOffset(toWorkflowStepNodeUser.getVo().getDueDateOffset());
        toWorkflowInboxTaskVO.setDateOffsetFrom(toWorkflowStepNodeUser.getVo().getDateOffsetFrom());
        toWorkflowInboxTaskVO.setAssigneeSetDueDate(toWorkflowStepNodeUser.getVo().getAssigneeSetDueDate());
        toWorkflowInboxTaskVO.setAllowDelegation(toWorkflowStepNodeUser.getVo().getAllowDelegation());
        toWorkflowInboxTaskVO.setIsEssential(toWorkflowStepNodeUser.getVo().getIsEssential());
        toWorkflowInboxTaskVO.setParallelNodeProcessionRule(toWorkflowStepNodeUser.getVo().getParallelNodeProcessionRule());
        toWorkflowInboxTaskVO.setTitles(businessObjectRoot.getCommonTitlesForDisplay());
        toWorkflowInboxTaskVO.setRevision("1");
        toWorkflowInboxTaskVO.setStates(WorkflowConstants.STATES_TYPE_ASSIGNED);//Assigned,Review,Complete
        toWorkflowInboxTaskVO.setActionComments(toWorkflowStepNodeUser.getVo().getActionComments());
        toWorkflowInboxTaskVO.setTaskOwner(toWorkflowStepNodeUser.getUsersVO().getNames());
        toWorkflowInboxTaskVO.setDescriptions(workflowRoute.getVo().getDescriptions()+"_"+workflowStep.getVo().getTitles());
        toWorkflowInboxTaskVO.setNotifyEmail(toWorkflowStepNodeUser.getVo().getNotifyEmail());
        toWorkflowInboxTaskVO.setProcessRole(toWorkflowStepNodeUser.getVo().getProcessRole());

        if("Task Create Date".equals(toWorkflowStepNodeUser.getVo().getDateOffsetFrom())
                && toWorkflowStepNodeUser.getVo().getDueDateOffset() > 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(timeService.getDBLocalTime());
            c.add(Calendar.DATE, toWorkflowStepNodeUser.getVo().getDueDateOffset());
            toWorkflowInboxTaskVO.setScheduledCompletionDate(c.getTime());
        }

        WorkflowInboxTask workflowInboxTask = DomUtil.toDom(toWorkflowInboxTaskVO);
        workflowInboxTask.createObject();
       /*
        *  Create WorkflowRouteTask Relation From:WorkflowRoute To:WorkflowInboxTask)
        *  select * from PTKEYTABLE a where a.pclass_name = 'WorkflowRouteTask'
        */
        workflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK, workflowRoute.getVo(), new HashMap<String, Object>());
       /*
        * Create WorkflowProjectTask Relation From: Users To:WorkflowInboxTask
        *  select * from PTKEYTABLE a where a.pclass_name = 'WorkflowProjectTask'
        */
        workflowInboxTask.addFromObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWPROJECTTASK, toWorkflowStepNodeUser.getUsersVO(), new HashMap<String, Object>());

        //if(workflowInboxTask.getVo().getNotifyEmail() /* || workflowRoute.getVo().getRouteBasePurpose().equals(WorkflowConstants.ROUTE_PURPOSE_DISTRIBUTION) */ ) {
        if(workflowInboxTask.getVo().getNotifyEmail() ) {
            notifyMail(businessObjectRoot, workflowRoute, workflowInboxTask.getUsersVO());
        }
        
        buildDelegatedInboxTask(businessObjectRoot, workflowRoute, workflowStep, toWorkflowStepNodeUser);
    }
    
    /**
     * 
     * 
     * @param businessObjectRoot
     * @param workflowRoute
     * @param workflowInboxTask
     * @see lgcns.rnd.application.workflow.service.WorkflowService#createApprovalInfoToEP(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.api.object.workflow.dom.WorkflowRoute, lgcns.rnd.api.object.workflow.dom.WorkflowInboxTask)
     */
    public void createApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute  workflowRoute, WorkflowInboxTask workflowInboxTask) { 
        if(!validateApprovalInfoToEP(businessObjectRoot, workflowRoute) && !workflowInboxTask.getVo().getInboxTaskType().equals(WorkflowConstants.INBOXTASK_TYPE_WBSACTIVITY)) return;
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(businessObjectRoot.getVo().getClassName());

        //Build ARD Info
        EPApprovalVO epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("ARD"); //system_id,system_pk에 해당하는 결재 정보를 모두 지운다.
        epApprovalVO.setSystemPk(workflowInboxTask.getVo().getObid());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
//        commonDao.insert("WORKFLOW.deleteEPApprovalInfoBySystemPk", epApprovalVO, WorkflowConstants.ITF_DATA_SOURCE);
        //commonDao.insert("WORKFLOW.deleteEPApprovalInfoBySystemPk", epApprovalVO);
        CommonDaoUtil.insert("WORKFLOW.insertARDEPApprovalInfo", epApprovalVO);
        
        //Build ARI Info
        epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("ARI"); //기안자가 결재를 요청했을 때 필요한 값
        epApprovalVO.setSystemPk(workflowInboxTask.getVo().getObid());
        epApprovalVO.setC1("01");
        epApprovalVO.setC2(classInfo.getDisplayedName());
//        int businessObjectRootNameLength = businessObjectRoot.getVo().getNames().getBytes().length;
//        epApprovalVO.setC3(businessObjectRoot.getVo().getNames()+" ("+ WorkflowUtil.getMaxByteString(businessObjectRoot.getVo().getTitles(), 48 - businessObjectRootNameLength) +")");
        epApprovalVO.setC3(WorkflowUtil.getMaxByteString(businessObjectRoot.getCommonTitlesForDisplay(), 100));
        epApprovalVO.setC4(mobileApproveCheck(businessObjectRoot,classInfo,  workflowInboxTask));
        epApprovalVO.setC5(classInfo.getDisplayedName());
        epApprovalVO.setSabun1(businessObjectRoot.getVo().getCreator());
        //epApprovalVO.setUrl1(propertiesService.getString("was.homeUrl") + "/epApprove.do?obid=" + businessObjectRoot.getVo().getObid());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
        CommonDaoUtil.insert("WORKFLOW.insertARIEPApprovalInfo", epApprovalVO);
        
        //Build ABY Info
        epApprovalVO =  new EPApprovalVO();
        epApprovalVO.setCommand("ABY"); //결재요청 또는 결재 처리후 다음 차수에 결재할 결재자 정보를 넣는다.
        epApprovalVO.setSystemPk(workflowInboxTask.getVo().getObid());
        epApprovalVO.setC1("01");
        epApprovalVO.setC2(WorkflowConstants.APPROVAL_STATUS_APPROVE);
        epApprovalVO.setSabun1(workflowInboxTask.getVo().getTaskOwner());
        //epApprovalVO.setUrl1(propertiesService.getString("was.homeUrl") + "/epApprove.do?obid=" + businessObjectRoot.getVo().getObid());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
        CommonDaoUtil.insert("WORKFLOW.insertABYEPApprovalInfo", epApprovalVO);
    }
    
    
    private void createSubmitApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute  workflowRoute, WorkflowStepNodeUserVO workflowStepNodeUserVO, String approvalStatus) {
        WorkflowInboxTaskVO workflowInboxTaskVO = workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUserVO(workflowStepNodeUserVO);
        if(!validateApprovalInfoToEP(businessObjectRoot, workflowRoute) && !workflowInboxTaskVO.getInboxTaskType().equals(WorkflowConstants.INBOXTASK_TYPE_WBSACTIVITY)) return;
        WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
        EPApprovalVO epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("AAI");//결재자가 결재을 했을 때 결재상태를 넘겨주는 기능
        epApprovalVO.setSystemPk(workflowInboxTaskVO.getObid());
        epApprovalVO.setC1("02");
        epApprovalVO.setC2(approvalStatus);
        epApprovalVO.setSabun1(workflowInboxTask.getVo().getTaskOwner());
        //epApprovalVO.setUrl1(propertiesService.getString("was.homeUrl") + "/epApprove.do?obid=" + businessObjectRoot.getVo().getObid());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
        CommonDaoUtil.insert("WORKFLOW.insertAAIEPApprovalInfo", epApprovalVO);
        
        epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("AAF"); //apr_status가 03 즉 최종 완료되었을 때 호출하는기능
        epApprovalVO.setSystemPk(workflowInboxTask.getVo().getObid());
        epApprovalVO.setSabun1(businessObjectRoot.getVo().getCreator());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
        //epApprovalVO.setUrl1(propertiesService.getString("was.homeUrl") + "/epApprove.do?obid=" + businessObjectRoot.getVo().getObid());
        CommonDaoUtil.insert("WORKFLOW.insertAAFEPApprovalInfo", epApprovalVO);
    }
    
    /**
     * 
     * 
     * @param businessObjectRoot
     * @param workflowRoute
     * @param workflowInboxTask
     * @param approvalStatus
     * @see lgcns.rnd.application.workflow.service.WorkflowService#createSubmitApprovalInfoToEP(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.api.object.workflow.dom.WorkflowRoute, lgcns.rnd.api.object.workflow.dom.WorkflowInboxTask, java.lang.String)
     */
    public void createSubmitApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute  workflowRoute, WorkflowInboxTask workflowInboxTask, String approvalStatus) {
        if(!validateApprovalInfoToEP(businessObjectRoot, workflowRoute) && !workflowInboxTask.getVo().getInboxTaskType().equals(WorkflowConstants.INBOXTASK_TYPE_WBSACTIVITY)) return;
        EPApprovalVO epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("AAI");//결재자가 결재을 했을 때 결재상태를 넘겨주는 기능
        epApprovalVO.setSystemPk(workflowInboxTask.getObid());
        epApprovalVO.setC1("02");
        epApprovalVO.setC2(approvalStatus);
        epApprovalVO.setSabun1(workflowInboxTask.getVo().getTaskOwner());
        //epApprovalVO.setUrl1(propertiesService.getString("was.homeUrl") + "/epApprove.do?obid=" + businessObjectRoot.getVo().getObid());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
        CommonDaoUtil.insert("WORKFLOW.insertAAIEPApprovalInfo", epApprovalVO);
        
        epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("AAF"); //apr_status가 03 즉 최종 완료되었을 때 호출하는기능
        epApprovalVO.setSystemPk(workflowInboxTask.getVo().getObid());
        epApprovalVO.setSabun1(businessObjectRoot.getVo().getCreator());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
        //epApprovalVO.setUrl1(propertiesService.getString("was.homeUrl") + "/epApprove.do?obid=" + businessObjectRoot.getVo().getObid());
        CommonDaoUtil.insert("WORKFLOW.insertAAFEPApprovalInfo", epApprovalVO);
    }
    
    /**
     * createReassignApprovalInfoToEP
     *
     * @param businessObjectRoot
     * @param workflowRoute
     * @param fromWorkflowInboxTask
     * @param toWorkflowStepNodeUser
     */
    private void createReassignApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute,
            WorkflowInboxTask fromWorkflowInboxTask, WorkflowStepNodeUser toWorkflowStepNodeUser) {
        if(!validateApprovalInfoToEP(businessObjectRoot, workflowRoute) && !fromWorkflowInboxTask.getVo().getInboxTaskType().equals(WorkflowConstants.INBOXTASK_TYPE_WBSACTIVITY)) return;

        EPApprovalVO epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("ARD"); //system_id,system_pk에 해당하는 결재 정보를 모두 지운다.
        epApprovalVO.setSystemPk(fromWorkflowInboxTask.getVo().getObid());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
        CommonDaoUtil.insert("WORKFLOW.insertARDEPApprovalInfo", epApprovalVO);

        WorkflowInboxTaskVO toWorkflowInboxTaskVO = workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUserVO(toWorkflowStepNodeUser.getVo());
        WorkflowInboxTask toWorkflowInboxTask = DomUtil.toDom(toWorkflowInboxTaskVO);
        createApprovalInfoToEP(businessObjectRoot, workflowRoute, toWorkflowInboxTask);
    }
    
    /**
     * 
     * 
     * @param businessObjectRoot
     * @param workflowRoute
     * @param fromWorkflowInboxTask
     * @param toWorkflowInboxTask
     * @see lgcns.rnd.application.workflow.service.WorkflowService#createReassignApprovalInfoToEP(omc.api.object.dom.BusinessObjectRoot, lgcns.rnd.api.object.workflow.dom.WorkflowRoute, lgcns.rnd.api.object.workflow.dom.WorkflowInboxTask, lgcns.rnd.api.object.workflow.dom.WorkflowInboxTask)
     */
    public void createReassignApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute,
            WorkflowInboxTask fromWorkflowInboxTask, WorkflowInboxTask toWorkflowInboxTask) {
        if(!validateApprovalInfoToEP(businessObjectRoot, workflowRoute) && !toWorkflowInboxTask.getVo().getInboxTaskType().equals(WorkflowConstants.INBOXTASK_TYPE_WBSACTIVITY)) return;

        EPApprovalVO epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("ARD"); //system_id,system_pk에 해당하는 결재 정보를 모두 지운다.
        epApprovalVO.setSystemPk(fromWorkflowInboxTask.getVo().getObid());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
        CommonDaoUtil.insert("WORKFLOW.insertARDEPApprovalInfo", epApprovalVO);

        createApprovalInfoToEP(businessObjectRoot, workflowRoute, toWorkflowInboxTask);
    }
    
    /**
     * 다음 결재자가 동일 결재자이면 자동 결재 해 줌.
     * 
     * @param businessObjectRoot
     * @see lgcns.rnd.application.workflow.service.WorkflowService#rescursiveApproveBySystem(omc.api.object.dom.BusinessObjectRoot)
     */
    public void rescursiveApproveBySystem(BusinessObjectRoot businessObjectRoot) {
        List<ApprovalVO> rtnApprovalList = this.retrieveWorkflow(businessObjectRoot);
        for(ApprovalVO approvalVO: rtnApprovalList) {
            if(approvalVO.getAvailableToApproval() && approvalVO.getApprovalStatus().equals(WorkflowConstants.APPROVAL_STATUS_NONE)
                   && approvalVO.getInboxTaskState().equals(WorkflowConstants.STATES_TYPE_ASSIGNED)  && approvalVO.getRoutePurpose().equals(WorkflowConstants.ROUTE_PURPOSE_APPROVAL)   ) {
                approvalVO.setComments("Automatically approved.");
                approvalVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_APPROVE);
                txnSubmitApproval(businessObjectRoot, approvalVO);
                rescursiveApproveBySystem(businessObjectRoot);
                break;
            }
        }
    }
    
    /**
     * 
     * 
     * @param workflowRouteObid
     * @param workflowInboxTaskObid
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnCreateDeleteApprovalInfoToEP(java.lang.String, java.lang.String)
     */
    public void txnCreateDeleteApprovalInfoToEP(String workflowRouteObid, String workflowInboxTaskObid) {
        EPApprovalVO epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("ARD");//system_id,system_pk에 해당하는 결재 정보를 모두 지운다.
        epApprovalVO.setSystemPk(workflowInboxTaskObid);
        epApprovalVO.setTaskId(workflowRouteObid);
        CommonDaoUtil.insert("WORKFLOW.insertARDEPApprovalInfo", epApprovalVO);
    }
    
    /**
     * 
     *
     * @param businessObjectRoot
     * @param workflowRoute
     * @param workflowInboxTask
     */
    private void createDeleteApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, 
    		WorkflowInboxTask workflowInboxTask) {
        EPApprovalVO epApprovalVO = new EPApprovalVO();
        epApprovalVO.setCommand("ARD");//system_id,system_pk에 해당하는 결재 정보를 모두 지운다.
        epApprovalVO.setSystemPk(workflowInboxTask.getVo().getObid());
        epApprovalVO.setTaskId(workflowRoute.getVo().getObid());
        CommonDaoUtil.insert("WORKFLOW.insertARDEPApprovalInfo", epApprovalVO);

    }

    private boolean validateApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute) {
        boolean bResult = true;
//        String[] classNames ={};

//        if(WorkflowConstants.ROUTE_PURPOSE_STANDARD.equals(workflowRoute.getVo().getRouteBasePurpose())
//                && Arrays.asList(classNames).indexOf(businessObjectRoot.getVo().getClassName()) > -1) {
//            bResult = false;
//        }
        
        if(WorkflowConstants.ROUTE_PURPOSE_STANDARD.equals(workflowRoute.getVo().getRouteBasePurpose())) {
            bResult = false;
        }

        if(WorkflowConstants.ROUTE_PURPOSE_DISTRIBUTION.equals(workflowRoute.getVo().getRouteBasePurpose())) {
            bResult = false;
        }

        return bResult;
    }
    
    /**
     * 
     * 
     * @param businessObjectRoot
     * @param usersVO
     * @see lgcns.rnd.application.workflow.service.WorkflowService#notifyMail(omc.api.object.dom.BusinessObjectRoot, omc.api.object.model.BusinessObjectRootVO)
     */
    public void notifyMail(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, BusinessObjectRootVO usersVO) {
        if( usersVO != null ){
            MailSendVO mailSendVO = new MailSendVO();
            if(workflowRoute.getVo().getRouteBasePurpose().equals(WorkflowConstants.ROUTE_PURPOSE_DISTRIBUTION)) {
                mailSendVO.setSendType(WorkflowConstants.MAIL_TYPE_DISTRIBUTION);
            }else{
                mailSendVO.setSendType(WorkflowConstants.MAIL_TYPE_APPROVAL);
            }
            mailSendVO.setObid(businessObjectRoot.getVo().getObid());
            mailSendVO.setClassName(businessObjectRoot.getVo().getClassName());
            mailSendVO.setNames(businessObjectRoot.getVo().getNames());
            mailSendVO.setCurrentStatus(businessObjectRoot.getVo().getStates());
            mailSendVO.setFromUserId(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID));
            List<String> toUserIdList = new ArrayList<String>();
            toUserIdList.add(usersVO.getNames());
            mailSendVO.setToUserIdList(toUserIdList);
            txnCreateMailSendInfo(mailSendVO);
        }
    }
    
    /**
     * 
     * 
     * @param businessObjectRoot
     * @param usersNames
     * @see lgcns.rnd.application.workflow.service.WorkflowService#notifyMail(omc.api.object.dom.BusinessObjectRoot, java.lang.String)
     */
    public void notifyMail(BusinessObjectRoot businessObjectRoot, WorkflowRoute workflowRoute, String usersNames) {
        MailSendVO mailSendVO = new MailSendVO();
        if(workflowRoute.getVo().getRouteBasePurpose().equals(WorkflowConstants.ROUTE_PURPOSE_DISTRIBUTION)) {
            mailSendVO.setSendType(WorkflowConstants.MAIL_TYPE_DISTRIBUTION);
        }else{
            mailSendVO.setSendType(WorkflowConstants.MAIL_TYPE_APPROVAL);
        }
        mailSendVO.setObid(businessObjectRoot.getVo().getObid());
        mailSendVO.setClassName(businessObjectRoot.getVo().getClassName());
        mailSendVO.setNames(businessObjectRoot.getVo().getNames());
        mailSendVO.setCurrentStatus(businessObjectRoot.getVo().getStates());
        mailSendVO.setFromUserId(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID));
        List<String> toUserIdList = new ArrayList<String>();
        toUserIdList.add(usersNames);
        mailSendVO.setToUserIdList(toUserIdList);
        txnCreateMailSendInfo(mailSendVO);
    }
   

    /*********************************************************************************************************************************************************
     *  khchoi
     ********************************************************************************************************************************************************/

    
    /**
     *  Apploval Uesr List 와 Flag로 조건 처리 후 Flag값을 Approval user 수에 맞게  결과값을 Return
     *
     * @param strApproverInfo, StrUserflag, targetUserId
     * @return
     */
    public String returnUserFlag(String strApproverInfo, String StrUserflag, String targetUserId){

        String strApproverInfoArr[]      = null;
        String StrUserflagArr[]          = null;
        int strApproverInfoArrLength    = 0;
        int StrUserflagArrLength        = 0;
        String currentUserId            = "";
        StringBuffer strUserFlagBuffer = new StringBuffer();

        int findNumber = 0;

        if(strApproverInfo != null) {
            strApproverInfoArr = strApproverInfo.split(WorkflowConstants.DELIM_2_S);
            strApproverInfoArrLength = strApproverInfoArr.length;
        }
        if(StrUserflag != null) {
            StrUserflagArr     = StrUserflag.split(WorkflowConstants.DELIM_2);
            StrUserflagArrLength = StrUserflagArr.length;
        }

        for (int j = 0; j < strApproverInfoArrLength; j++) {
            currentUserId = strApproverInfoArr[j];
            if (currentUserId.equals(targetUserId)) {
                findNumber = j;
            }
        }

        if(StrUserflagArrLength == strApproverInfoArrLength) {
            int appendCnt = 0;
            for (int j = 0; j < StrUserflagArrLength; j++) {
                if(j != findNumber) {
                    if(appendCnt == 0) {
                        strUserFlagBuffer.append(StrUserflagArr[j]);
                    } else {
                        strUserFlagBuffer.append(",");
                        strUserFlagBuffer.append(StrUserflagArr[j]);
                    }
                    appendCnt++;
                }
            }
        } else {
            for (int j = 0; j < strApproverInfoArrLength-1; j++) {
                if(j == 0) {
                    strUserFlagBuffer.append("Y");
                } else {
                    strUserFlagBuffer.append(",Y");
                }
            }
        }
        return strUserFlagBuffer.toString();
    }

    
    /**
     * Approval Line multiple 수정
     * @param userObid
     * @param approvalLineList
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnUpdateApprovalLineList(java.lang.String, java.util.List)
     */
    @Override
    public void txnUpdateApprovalLineList(String userObid, List<ApprovalLineVO> approvalLineList) {
        ApprovalLine updateDom = null;
        if( approvalLineList != null && approvalLineList.size() > 0 ){
            for( int inx = 0; inx < approvalLineList.size(); inx++ ){
                updateDom = new ApprovalLine( approvalLineList.get(inx).getObid() );
                if( updateDom != null ){
                    updateDom.getVo().setTitles(approvalLineList.get(inx).getTitles());
                    updateDom.getVo().setDescriptions(approvalLineList.get(inx).getDescriptions());
                    updateDom.modifyObject();
                }
            }
        }
    }

    /**
     * Business Object 기준으로 배표이력 조회
     * @param obid
     * @return
     * @throws ParseException
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveDistributionHistoryList(java.lang.String)
     */
    @Override
    public List<WorkflowInboxTaskVO> retrieveDistributionHistoryList(String obid) throws Exception {
        // Select 설정
        StringBuffer selectPattern = new StringBuffer();
        selectPattern.append("@U.[titles] targetUserName");
        selectPattern.append("+SortBy@this.[sequences]");

        // From 설정
        StringBuffer fromPattern = new StringBuffer();
        fromPattern.append("<this>ThisConnectedWithTo<[WorkflowRouteTask]@I2R>+");
        fromPattern.append("<[WorkflowRouteTask]@I2R>FromConnectedWithThis<[WorkflowRoute]@WR>+");
        fromPattern.append("<[WorkflowRoute]@WR>ThisConnectedWithTo<[WorkflowObjectRoute]@W2O>+");
        fromPattern.append("<this>ThisConnectedWithTo<[WorkflowProjectTask]@I2U>+");
        fromPattern.append("<[WorkflowProjectTask]@I2U>FromConnectedWithThis<[Users]@U>+");

        // Where 조건 설정
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[routeInstructions]",
                GlobalConstants.OQL_OPERATOR_IN, WorkflowConstants.INSTRUCTION_TYPE_DISTRIBUTION + "," + WorkflowConstants.INSTRUCTION_TYPE_MANUAL_DISTRIBUTION);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@W2O.[fromObid]",
                GlobalConstants.OQL_OPERATOR_EQUAL, obid);

        List<WorkflowInboxTaskVO> result = ObjectRoot.searchObjects(
                ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                false,
                false,
                false,
                false,
                selectPattern.toString(),
                fromPattern.toString(),
                wherePattern.toString(),
                paramPattern.toString(),
                true,
                0
        );
        return result;
    }


    /**
     * 필수결재자 일괄변경 (Before > After)
     * @param beforeUserObid
     * @param afterUserObid
     * @param targetEssentialApproverObid
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnChangeParticipantUser(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<ApprovalLineStateVO> txnChangeParticipantUser(String beforeUserObid, String afterUserObid, String targetEssentialApproverObid) {
        EssentialApprover masterDom = null;
        ApprovalLineState stateDom = null;
        List<ApprovalLineStateVO> stateList = null;
        ApprovalLineStateVO stateVO = null;
        ApprovalLineStateVO existStateVO = null;
        String[] targetObidArr = targetEssentialApproverObid.split(WorkflowConstants.DELIM_2_S);
        boolean isExist = false;
        ApprovalLineStateVO failVO = null;
        List<ApprovalLineStateVO> failList = new ArrayList<ApprovalLineStateVO>();
        String routeStateName = "";

        if( targetObidArr != null && targetObidArr.length > 0 ){
            for( int inx = 0; inx < targetObidArr.length; inx++ ){
                masterDom = new EssentialApprover( targetObidArr[inx] );
                stateList = masterDom.getRelatedObjects( ApplicationSchemaConstants.RELCLASS_ESSENTIALAPPROVER2STATE );
                if( stateList != null && stateList.size() > 0 ){
                    for( int jnx = 0; jnx < stateList.size(); jnx++ ){
                        stateVO = stateList.get(jnx);
                        if( stateVO != null && beforeUserObid.equals( stateVO.getAssigneeObid() ) ){
                            // 변경하고자 하는 사용자가 동일 routeState에 존재하는지 확인
                            routeStateName = stateVO.getRouteState();
                            isExist = false;
                            for( int knx = 0; knx < stateList.size(); knx++ ){
                                existStateVO = stateList.get(knx);
                                if( existStateVO != null && routeStateName.equals(existStateVO.getRouteState()) && afterUserObid.equals(existStateVO.getAssigneeObid()) ){
                                    isExist = true;
                                    failVO = new ApprovalLineStateVO();
                                    failVO.setNames( masterDom.getVo().getNames() );
                                    failVO.setTitles( masterDom.getVo().getTitles() );
                                    failVO.setRouteState( routeStateName );
                                    failList.add( failVO );
                                    break;
                                }
                            }

                            // 동일 routeState에 변경하고자하는 사용자가 존재하지 않을 경우에만 변경
                            if( !isExist ){
                                stateDom = new ApprovalLineState( stateVO.getObid() );
                                stateDom.getVo().setAssigneeObid( afterUserObid );
                                stateDom.modifyObject();

                                // 필수결재자 변경이력 저장
//                                this.txnCreateEssentialApproverLog(
//                                        WorkflowConstants.ACT_TYPE_EXCHANGE,
//                                        targetEssentialApproverObid,
//                                        beforeUserObid + ">" + afterUserObid
//                                );
                            }
                        }
                    }
                }
            }
        }

        return failList;
    }

    /**
     * 필수결재자 변경이력 저장
     * @param opType
     * @param targetObid
     * @param targetUserObid
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnCreateEssentialApproverLog(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void txnCreateEssentialApproverLog(String opType, String targetObid, String targetUserObid) {
        EssentialApproverLogVO logVO = new EssentialApproverLogVO();
        logVO.setClassName( ApplicationSchemaConstants.BIZCLASS_ESSENTIALAPPROVERLOG );
        logVO.setLifeCycle( ApplicationSchemaConstants.LIFECYCLE_WITHOUT_STATE );
        logVO.setStates( ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS );
        logVO.setNames( NameGeneratorUtil.generateUniqueName(ApplicationSchemaConstants.BIZCLASS_ESSENTIALAPPROVERLOG) );
        logVO.setTitles( "[" + opType + "] " + targetObid + " : " + targetUserObid );
        logVO.setOpType( opType );
        logVO.setTargetObid( targetObid );
        logVO.setTargetUserObid( targetUserObid );

        EssentialApproverLog createDom = (EssentialApproverLog)DomUtil.toDom( logVO );
        createDom.createObject();
    }

    /**
     * @param
     * @return mobileUseFlag
     */
    private String mobileApproveCheck(BusinessObjectRoot businessObjectRoot, ClassInfo classInfo, WorkflowInboxTask workflowInboxTask){
        String mobileUseFlag = "01";

//        if(workflowInboxTask.getVo().getInboxTaskType().equals(WorkflowConstants.INBOXTASK_TYPE_WBSACTIVITY)) {
//            mobileUseFlag = null;
//        }else{
//            if(classInfo.getClassName().equals("ChangeProcess")) {
//                ChangeProcess changeProcess = (ChangeProcess)businessObjectRoot;
//                if(!changeProcess.isMobileApproval()) mobileUseFlag = null;  
//            }
//        }

        return mobileUseFlag;
    }
    

    /**
     * E-mail 발송용 데이터 생성 (GPDMIF_DATA_BY_TRIGGER)
     * @param mailSendVO
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnCreateMailSendInfo(lgcns.rnd.application.workflow.model.MailSendVO)
     */
    @Override
    public void txnCreateMailSendInfo(MailSendVO mailSendVO) {
//        IfPlmDataByTriggerVO dataVO = new IfPlmDataByTriggerVO();
//
//        // Default Value Setting
//        dataVO.setObjectRevision( "-" );
//        dataVO.setTargetSystem( WorkflowConstants.INF_TARGET_SYSTEM_MAIL_SEND );
//        dataVO.setInterfaceId( WorkflowConstants.INF_TARGET_SYSTEM_MAIL_SEND_MAIL001 );

        // Target Value Setting
//        dataVO.setObjectId( mailSendVO.getObid() );
//        dataVO.setObjectType( mailSendVO.getClassName() );
//        dataVO.setObjectName( mailSendVO.getNames() );
//        dataVO.setUserId( mailSendVO.getFromUserId() );
//        dataVO.setCurrentStatus( mailSendVO.getCurrentStatus() );
//
//        StringBuffer sendTypeUserInfo = new StringBuffer();
//        sendTypeUserInfo.append( mailSendVO.getSendType() + ":" );
//        List<String> toUserIdList = mailSendVO.getToUserIdList();
//        if( toUserIdList != null && toUserIdList.size() > 0 ){
//            for( int inx = 0; inx < toUserIdList.size(); inx++ ){
//                sendTypeUserInfo.append( toUserIdList.get(inx) );
//                if( inx < toUserIdList.size() - 1 ){
//                    sendTypeUserInfo.append( "," );
//                }
//            }
//        }
//        dataVO.setAttribute1( sendTypeUserInfo.toString() );
//        CommonDaoUtil.insert("Integration.insertDataByTrigger", dataVO);
    }

    /**
     * E-mail 발송용 데이터 생성 (GPDMIF_DATA_BY_TRIGGER)
     * -obid 기준(Trigger에서 호출 시 사용)
     * @param obid
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnCreateMailSendInfo(java.lang.String)
     */
//    @Override
//    public void txnCreateMailSendInfo(String obid) {
//        IfGpdmDataByTriggerVO dataVO = new IfGpdmDataByTriggerVO();
//        BusinessObjectRoot boDom = new BusinessObjectRoot( obid );
//        if( boDom != null ){
//            BusinessObjectRootVO boVO = boDom.getVo();
//
//            // Default Value Setting
//            dataVO.setObjectRevision( "-" );
//            dataVO.setTargetSystem( WorkflowConstants.INF_TARGET_SYSTEM_MAIL_SEND );
//            dataVO.setInterfaceId( "MAIL001" );
//            dataVO.setObjectId( obid );
//            dataVO.setObjectType( boVO.getClassName() );
//            dataVO.setObjectName( boVO.getNames() );
//            dataVO.setUserId( boVO.getCreator() );
//
//            StringBuffer sendTypeUserInfo = new StringBuffer();
////            sendTypeUserInfo.append( GpdmConstants.MAIL_TYPE_DISTRIBUTION + ":" );
//
//            String distributionState = retrieveDistributionState( boVO.getLifeCycle() );
//            List<ApprovalVO> approvalList = retrieveWorkflow( boDom );
//            if( approvalList != null && approvalList.size() > 0 ){
//                for( int inx = 0; inx < approvalList.size(); inx++ ){
//                    // Distribution 상태에 할당된 사용자들에게만 메일발송
//                    if( distributionState.equals(approvalList.get(inx).getState()) ){
//                        sendTypeUserInfo.append( approvalList.get(inx).getAssigneeNames() + "," );
//                    }
//                }
//                if(sendTypeUserInfo.toString().length() > 0)
//                {
////                    sendTypeUserInfo.append( GpdmConstants.MAIL_TYPE_DISTRIBUTION + ":" );
//                    dataVO.setAttribute1(WorkflowConstants.MAIL_TYPE_DISTRIBUTION + ":" + (sendTypeUserInfo.toString().substring( 0, sendTypeUserInfo.length() - 1 ) ));  // 마지막 구분자 삭제
//                    commonDao.insert("Integration.insertDataByTrigger", dataVO);
//                }
//
//            }
//        }
//    }

    /**
     * Mass approval (Approve / Reject)
     * @param targetObjectList
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnSubmitMassApproval(java.util.List)
     */
    @Override
    public List<ApprovalVO> txnSubmitMassApproval(List<ApprovalVO> targetObjectList) {
        ApprovalVO approvalVO = null;
        BusinessObjectRoot boDom = null;
        Map<String, String> inputParams = null;
        List<ApprovalVO> returnMsg = new ArrayList<ApprovalVO>();

        for( int inx = 0; inx < targetObjectList.size(); inx++ ){
            approvalVO = targetObjectList.get(inx);
            ApprovalVO newVO = new ApprovalVO();

            try {
                if( StringUtils.isEmpty(approvalVO.getObid()) ||
                    StringUtils.isEmpty(approvalVO.getApprovalStatus()) ||
                    StringUtils.isEmpty(approvalVO.getComments()) ||
                    StringUtils.isEmpty(approvalVO.getWorkflowStepNodeUserObid()) ||
                    StringUtils.isEmpty(approvalVO.getWorkflowRouteObid()) ||
                    NullUtil.isNull(approvalVO.getStep()) ||
                    StringUtils.isEmpty(approvalVO.getParallelNodeProcessionRule()) ){
                    throw new IllegalArgumentException();
                }

                boDom = DomUtil.toDom( approvalVO.getObid(), true );
                newVO.setObid(approvalVO.getObid());
                newVO.setState(boDom.getVo().getClassName());
                newVO.setTitles(boDom.getVo().getNames());
                newVO.setComments(GlobalConstants.THREAD_LOG_SUCCESS);
                inputParams = new HashMap<String, String>();
                inputParams.put( "approvalStatus", approvalVO.getApprovalStatus() );
                inputParams.put( "obidOfworkflowStepNodeUser", approvalVO.getWorkflowStepNodeUserObid() );
                boDom.preProcessApproval(inputParams);

                this.txnSubmitApproval(boDom, approvalVO);
            } catch (Exception e) {
                newVO.setComments(GlobalConstants.THREAD_LOG_ERROR +" : "+ e.toString().substring(0,100));
            } finally{
                returnMsg.add(newVO);
            }
        }
        return returnMsg;
    }

    /**
     * Mass Acknowledge
     * @param targetObjectList
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnDoMassAcknowledge(java.util.List)
     */
    @Override
    public void txnDoMassAcknowledge(List<ApprovalVO> targetObjectList) {
        ApprovalVO approvalVO = null;
        BusinessObjectRoot boDom = null;
        Map<String, String> inputParams = null;

        for( int inx = 0; inx < targetObjectList.size(); inx++ ){
            approvalVO = targetObjectList.get(inx);

            if( StringUtils.isEmpty(approvalVO.getObid()) ||
                StringUtils.isEmpty(approvalVO.getApprovalStatus()) ||
                StringUtils.isEmpty(approvalVO.getComments()) ||
                StringUtils.isEmpty(approvalVO.getWorkflowStepNodeUserObid()) ||
                StringUtils.isEmpty(approvalVO.getWorkflowRouteObid()) ||
                NullUtil.isNull(approvalVO.getStep()) ||
                StringUtils.isEmpty(approvalVO.getParallelNodeProcessionRule()) ){
                throw new IllegalArgumentException();
            }

            boDom = DomUtil.toDom( approvalVO.getObid(), true );
            inputParams = new HashMap<String, String>();
            inputParams.put( "approvalStatus", approvalVO.getApprovalStatus() );
            inputParams.put( "obidOfworkflowStepNodeUser", approvalVO.getWorkflowStepNodeUserObid() );
            boDom.preProcessApproval(inputParams);

            this.txnSubmitApproval(boDom, approvalVO);
        }
    }

    

    /**
     * Approval Line State 중복체크용 조회
     * @param approvalLineObid
     * @param approvalLineStateVO
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveApprovalLineState(java.lang.String, lge.gpdm.api.object.workflow.model.ApprovalLineStateVO)
     */
    @Override
    public List<ApprovalLineStateVO> retrieveApprovalLineState(String approvalLineObid, ApprovalLineStateVO approvalLineStateVO) {
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();

        ApprovalLine masterDom = new ApprovalLine( approvalLineObid );

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[routeState]", GlobalConstants.OQL_OPERATOR_EQUAL, approvalLineStateVO.getRouteState());
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[step]", GlobalConstants.OQL_OPERATOR_EQUAL, approvalLineStateVO.getStep());
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[assigneeObid]", GlobalConstants.OQL_OPERATOR_EQUAL, approvalLineStateVO.getAssigneeObid());

        List<ApprovalLineStateVO> result = masterDom.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_APPROVALLINE2STATE,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_TO,
                null,
                wherePattern.toString(),
                paramPattern.toString(),
                false,
                false,
                0,
                1
        );

        return result;
    }

    /**
     * inputTaskObid를 가지고 Approve/Reject 처리
     *
     * @param inboxTaskObid
     * @param approvalStatus
     * @param comments
     * @param isFromWebService : 웹서비스호출여부
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnManualApprove(java.lang.String, java.lang.String, java.lang.String, boolean)
     */
    @Override
    public void txnManualApprove(WorkflowInboxTask workflowInboxTask, String approvalStatus, String comments, boolean isFromWebService) {
        this.txnManualApprove( workflowInboxTask, approvalStatus, comments, isFromWebService, null );
    }

    /**
     * inputTaskObid를 가지고 Approve/Reject 처리
     *
     * @param inboxTaskObid
     * @param approvalStatus
     * @param comments
     * @param isFromWebService
     * @param webServiceFlag
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnManualApprove(java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String)
     */
    @Override
    public void txnManualApprove(WorkflowInboxTask workflowInboxTask, String approvalStatus, String comments, boolean isFromWebService, String webServiceFlag) {
        // Null Check
        if(NullUtil.isNull(workflowInboxTask) || StringUtils.isEmpty(approvalStatus) || StringUtils.isEmpty(comments)){
            throw new IllegalArgumentException();
        }
        
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
//            if( ApplicationSchemaConstants.STATE_PRI_GPINAPPROVING.equals(businessObjectRoot.getVo().getStates()) ){
//                throw new ApplicationException("This approval information will be completed automatically when the related PRI PCO is completed in GPIN System.");
//            }
            approvalVO.setMobileApproval( webServiceFlag );
        }

        if(StringUtils.isEmpty(approvalVO.getWorkflowInboxTaskObid()) ||
                StringUtils.isEmpty(approvalVO.getWorkflowRouteObid()) ||
                StringUtils.isEmpty(approvalVO.getWorkflowStepNodeUserObid()) ||
                StringUtils.isEmpty(approvalVO.getObid()) ||
                NullUtil.isNull(approvalVO.getStep()) ||
                StringUtils.isEmpty(approvalVO.getParallelNodeProcessionRule()) ||
                StringUtils.isEmpty(approvalVO.getApprovalStatus()) ||
                StringUtils.isEmpty(approvalVO.getComments())){
            throw new IllegalArgumentException();
        }

        txnSubmitApproval(businessObjectRoot, approvalVO);
        
        String[] continueApprovalClass = {"Requests", "Projects", "Consult", "RevisedProjects", "DesignRequests", "DesignChange", "DesignDrop", "ChangeProcess", "ProjectActivityDocument"};
        
        if(approvalVO.getApprovalStatus().equals(WorkflowConstants.APPROVAL_STATUS_APPROVE) && Arrays.asList(continueApprovalClass).contains(businessObjectRoot.getClassName())) {
            rescursiveApproveBySystem(businessObjectRoot);
        }

    }

    /**
     * inboxTaskObid, empNo로 User의 결재에 필요한 정보조회
     * @param businessObjectRoot
     */
    @Override
    public WorkflowInboxTask retrieveUserApproveInfo(String searchObid, String userEmpNo) {

        if(StringUtils.isEmpty(searchObid) ) throw new IllegalArgumentException("");
        
        WorkflowInboxTask workflowInboxTask = DomUtil.toDom(searchObid);
        
        if(NullUtil.isNull(workflowInboxTask)) return workflowInboxTask;
        
        if(!StringUtils.isEmpty(userEmpNo)) {
            UsersVO usersVO = workflowInboxTask.getUsersVO();
          if(NullUtil.isNull(usersVO)) throw new ApplicationException("Task owner is empty]");
          if(!( userEmpNo.equals(usersVO.getNames()) || userEmpNo.equals(usersVO.getAbsenceDelegate()) )) 
              throw new ApplicationException("Emp no does not match[usersVO: "+usersVO.getNames()+" != empNo: "+userEmpNo+"]") ;
        }
        return workflowInboxTask;
    }

    /**
     * inboxTaskObid, empNo로 User의 결재에 필요한 정보조회
     * @param businessObjectRoot
     */
    @Override
    public List<WorkflowInboxTaskVO> retrieveNotificationFromRoute(BusinessObjectRoot businessObjectRoot) {

        List<WorkflowInboxTaskVO> existList = null;

        StringBuffer selectPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();

        selectPattern.append("@WOR.[toObid] workflowRouteObid");
        selectPattern.append("+ @WOR.[routeState] routeState");
        selectPattern.append("+ @WOR.[fromObid] docId");
        selectPattern.append("+ @WR.[creator] routeCreator");
        selectPattern.append("+ @WR.[obid] routeObid");
        //selectPattern.append("+\"getObjectInfoWithName\"('Users',@WR.[creator],'-','titles') routeCreatorName");
        StringUtil.addSortByPattern(selectPattern, "@this.[sequences]");

        fromPattern.append("<this>                         ThisConnectedWithTo       <[WorkflowRouteTask]@WRT>+");
        fromPattern.append("<[WorkflowRouteTask]@WRT>         FromConnectedWithThis       <[WorkflowRoute]@WR>+");
        fromPattern.append("<[WorkflowRoute]@WR>         ThisConnectedWithTo       <[WorkflowObjectRoute]@WOR>+");

        // Where 조건 설정
        String distributionInCondition = WorkflowConstants.INSTRUCTION_TYPE_ORIGINATOR
                + "," + WorkflowConstants.INSTRUCTION_TYPE_DISTRIBUTION;

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@WOR.[fromObid]",
                GlobalConstants.OQL_OPERATOR_EQUAL, businessObjectRoot.getVo().getObid() );

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[routeInstructions]",
                GlobalConstants.OQL_OPERATOR_NOT_IN, distributionInCondition); // Task Title != ‘Originator’ && Task Title != ‘Distribution’

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[approvalStatus]",
                GlobalConstants.OQL_OPERATOR_EQUAL, WorkflowConstants.APPROVAL_STATUS_NONE); // Action = ‘Awaiting Approval’

        existList = ObjectRoot.searchObjects(
                ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                false, false, false, false,
                selectPattern.toString(),
                fromPattern.toString(),
                wherePattern.toString(),
                paramPattern.toString(),
                false,
                0);
        for(int i=0; i<existList.size(); i++){
            ApprovalVO approvalVO = new ApprovalVO();
            approvalVO.setApprovalStatus(existList.get(i).getApprovalStatus());
            setAction(approvalVO);
            UsersVO usersVO = ObjectRoot.findObject( ApplicationSchemaConstants.BIZCLASS_USERS, existList.get(i).getTaskOwner(), true );
            existList.get(i).setReviewersComments(usersVO.getTitles()); // Assignee(결재자정보)
            existList.get(i).setApprovalStatus(approvalVO.getAction()); // Action(Awaiting Approval)
            existList.get(i).setActionComments(usersVO.getHrDepartmentKor()); // Department(부서정보)
        }
        return existList;
    }

    /**
     *
     * @param obid
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveApprovalHistoryMig(java.lang.String)
     */
    @Override
    public List<HashMap<String, Object>> retrieveApprovalHistoryMig(String obid){
        List<HashMap<String, Object>> selectList = CommonDaoUtil.selectList("Workflow.retrieveApprovalHistory", obid);
        for (HashMap<String, Object> hashMap : selectList) {
            if(hashMap.get("approvalComments") != null){
                StringBuffer sbf = new StringBuffer();
                char[] buf = new char[1024];
                String approvalComments = (String)hashMap.get("approvalComments");
//                hashMap.put("approvalComments", approvalComments);
//                try {
//                    Reader characterStream = approvalComments.getCharacterStream();
//                    int readcnt;
//                    while((readcnt = characterStream.read(buf, 0, 1024)) != -1){
//                        sbf.append(buf, 0, readcnt);
//                    }
//                    hashMap.put("approvalComments", sbf.toString());
//                } catch (Exception e ) {
//                    hashMap.put("approvalComments", null);
//                }
            }
        }
        return selectList;
    }

    /**
     * 
     * @param approvalLineObid
     * @param createList
     * @param updateList
     * @param deleteList
     * @param isEssential
     * @param relationName
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnUpdateApprovalLineStateList(java.lang.String, java.util.List, java.util.List, java.util.List, java.lang.Boolean, java.lang.String)
     */
    @Override
    public int txnUpdateApprovalLineStateList(String approvalLineObid, List<ApprovalLineStateVO> createList,
            List<ApprovalLineStateVO> updateList, List<ApprovalLineStateVO> deleteList, Boolean isEssential,
            String relationName){
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * 
     * @param userObid
     * @param approvalLineObid
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnUpdateDefaultApprovalLine(java.lang.String, java.lang.String)
     */
    @Override
    public void txnUpdateDefaultApprovalLine(String userObid, String approvalLineObid){
        // TODO Auto-generated method stub
        
    }

    /**
     * 
     * @param approvalLineObid
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnDeleteApprovalLine(java.lang.String)
     */
    @Override
    public void txnDeleteApprovalLine(String approvalLineObid){
        // TODO Auto-generated method stub
        
    }

    /**
     * 
     * @param policyName
     * @param plantName
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveApprovalLineStateList(java.lang.String, java.lang.String)
     */
    @Override
    public List<ApprovalLineStateVO> retrieveApprovalLineStateList(String policyName, String plantName){
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     * @param searchInfo
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#pendingJobByRetirementList(lgcns.rnd.application.workflow.model.PendingJobByRetirementVO)
     */
    @Override
    public List<WorkflowInboxTaskVO> pendingJobByRetirementList(PendingJobByRetirementVO searchInfo){
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     * @param searchInfo
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#pendingJobByRetirementListTechnicalDoc(lgcns.rnd.application.workflow.model.PendingJobByRetirementSearchVO)
     */
    @Override
    public List<PendingJobByRetirementSearchVO> pendingJobByRetirementListTechnicalDoc(
            PendingJobByRetirementSearchVO searchInfo){
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     * @param policyName
     * @param plantName
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveApprovalLine(java.lang.String, java.lang.String)
     */
    @Override
    public ApprovalLineVO retrieveApprovalLine(String policyName, String plantName){
        // TODO Auto-generated method stub
        return null;
    }


//    /**
//     * inboxTaskObid, empNo로 User의 결재에 필요한 정보조회
//     * @param businessObjectRoot
//     */
//    @Override
//    public List<WorkflowInboxTaskVO> pendingJobByRetirementList(PendingJobByRetirementVO searchInfo) {
//        StringBuffer selectPattern = new StringBuffer();
//        StringBuffer fromPattern = new StringBuffer();
//        StringBuffer wherePattern = new StringBuffer();
//        StringBuffer paramPattern = new StringBuffer();
//
//        String classInCondition = "";
//        String businessUnitCode = "";
//        if( StringUtils.isEmpty(searchInfo.getTargetUserId()) ){
//            businessUnitCode = BusinessUnitUtil.getBusinessUnitSystemProperty().toUpperCase();
//        }
//        else{
//            businessUnitCode = UserManagementUtil.getUserProperty( searchInfo.getTargetUserId(), WorkflowConstants.USER_PROP_BUSINESS_UNIT );
//        }
//
//        // Select 설정
//        StringUtil.constructSelectPattern( selectPattern, "@W2O.[fromClass] targetClassName+@W2O.[fromObid] targetObid" );
//        StringUtil.constructSelectPattern( selectPattern, "@WR.[obid] routeObid" );
//        StringUtil.constructSelectPattern( selectPattern, "FN_GET_RELATED_PART_NO(@W2O.[fromObid]) partNames" );
//        StringUtil.constructSelectPattern( selectPattern, "FN_GET_RELATED_DOC_NO(@W2O.[fromObid]) docNames" );
//        StringUtil.constructSelectPattern( selectPattern, "\"getObjectInfo\"(@W2O.[fromObid],'representativeModel') representativeModel" );
//        StringUtil.constructSelectPattern( selectPattern, "\"getObjectInfo\"(@W2O.[fromObid],'names,states,titles,lifeCycle,creationPlantUnit,requestSite,displayedClassName') objInfo" );
//        StringUtil.constructSelectPattern( selectPattern, "\"getObjectInfoWithName\"('Users',@WR.[creator],'-','titles') routeCreatorName" );
//        StringUtil.constructSelectPattern( selectPattern, "FN_GET_CODEDETAIL(@this.[routeInstructions],'" + WorkflowConstants.CODE_NAME_ITEM_WORK_GROUP + "','" + businessUnitCode + "') routeInstructionsDesc" );
//        StringUtil.constructSelectPattern( selectPattern, "\"getObjectInfoWithName\"('Users',@this.[taskOwner],'-','titles') approver" );
//        StringUtil.constructSelectPattern( selectPattern, "\"getObjectInfoWithName\"('Users',@this.[taskOwner],'-','departmentKor') department" );
//        StringUtil.constructSelectPattern( selectPattern, "SortBy@this.[created] desc" );
//        //StringUtil.constructSelectPattern(selectPattern, "DECODE(\"getObjectInfo\"(@W2O.[fromObid],'states'), 'Working', DECODE(\"getObjectInfo\"(@W2O.[fromObid],'isRejectedObject'), 'TRUE', 'Rejected', ''), '') isRejected");
//
//        // From 설정
//        fromPattern.append("<this>ThisConnectedWithTo<[WorkflowRouteTask]@I2R>+");
//        fromPattern.append("<this>ThisConnectedWithTo<[WorkflowProjectTask]@WFPT>+");
//        fromPattern.append("<[WorkflowRouteTask]@I2R>FromConnectedWithThis<[WorkflowRoute]@WR>+");
//        fromPattern.append("<[WorkflowProjectTask]@WFPT>FromConnectedWithThis<[Users]@USR>+");
//        fromPattern.append("<[WorkflowRoute]@WR>ThisConnectedWithTo<[WorkflowObjectRoute]@W2O>+");
//
//        // Where 조건 설정
//        String distributionInCondition = WorkflowConstants.INSTRUCTION_TYPE_DISTRIBUTION
//                + "," + WorkflowConstants.INSTRUCTION_TYPE_MANUAL_DISTRIBUTION
//                + "," + WorkflowConstants.INSTRUCTION_TYPE_SITE_ECO_SCOWORK;
//
//        if(searchInfo.getTarget().equals("C")){
//            fromPattern.append("<[WorkflowObjectRoute]@W2O>FromConnectedWithThis<[Changes,DocumentAuthTransfer]@CHG>+");
//            classInCondition = ApplicationSchemaConstants.BIZCLASS_ECO
//                    + "," + ApplicationSchemaConstants.BIZCLASS_RELEASE
//                    + "," + ApplicationSchemaConstants.BIZCLASS_ECR
//                    + "," + ApplicationSchemaConstants.BIZCLASS_DOCUMENTAUTHTRANSFER;
//        }else if(searchInfo.getTarget().equals("R")){
//            fromPattern.append("<[WorkflowObjectRoute]@W2O>FromConnectedWithThis<[Requests]@CHG>+");
//        }else if(searchInfo.getTarget().equals("T")){
//            fromPattern.append("<[WorkflowObjectRoute]@W2O>FromConnectedWithThis<[EtcDocument]@CHG>+");
//            classInCondition = ApplicationSchemaConstants.BIZCLASS_ETCDOCUMENT;
//        }
//
//        if( !StringUtils.isEmpty(searchInfo.getTargetUserId()) ){
//            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[taskOwner]",
//                    GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getTargetUserId());
//        }
//        StringUtil.constructWherePattern(wherePattern, paramPattern, "@USR.[states]",
//                GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_INACTIVE);
//        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[approvalStatus]",
//                GlobalConstants.OQL_OPERATOR_EQUAL, WorkflowConstants.APPROVAL_STATUS_NONE);
//        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[routeInstructions]",
//                GlobalConstants.OQL_OPERATOR_NOT_IN, distributionInCondition);
//        StringUtil.constructWherePattern(wherePattern, paramPattern, "to_char(@USR.[retiredDate],'yyyy')",
//                GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getYear());
//        StringUtil.constructWherePattern(wherePattern, paramPattern, "to_char(@USR.[retiredDate],'mm')",
//                GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getMm());
//        if( !StringUtils.isEmpty(classInCondition)){
//            StringUtil.constructWherePattern(wherePattern, paramPattern, "@W2O.[fromClass]",
//                    GlobalConstants.OQL_OPERATOR_IN, classInCondition);
//        }
//
//        List<WorkflowInboxTaskVO> result = null;
//        result = ObjectRoot.searchObjectPagingList(ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK,
//                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
//                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
//                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
//                false, false, false, selectPattern.toString(), fromPattern.toString(), wherePattern.toString(),
//                paramPattern.toString(), true, searchInfo.getTargetRow(),
//                searchInfo.getRowSize());
//
//        if(result != null && result.size() > 0) {
//            HashMap<String, Object> outData = null;
//            String objSplitInfo[] = null;
//            String site = "";
//            for (int inx = 0; inx < result.size(); inx++) {
//                outData = result.get(inx).getOutData();
//                if(outData.get("representativeModel") == null){
//                    String swrModel = softwareRequestModel(outData.get("targetObid").toString());
//                    outData.put("representativeModel", swrModel);
//                }
//                objSplitInfo = StringUtil.split(outData.get("objInfo").toString(), "^");
//                outData.put("names", objSplitInfo[0]);
//                outData.put("states", objSplitInfo[1]);
//                outData.put("titles", objSplitInfo[2]);
//                outData.put("lifeCycle", objSplitInfo[3]);
//                site = objSplitInfo[4];
//                if( StringUtils.isEmpty(site) ){
//                    site = objSplitInfo[5];
//                }
//                outData.put("site", site);
//                outData.put("targetClassDesc", objSplitInfo[6]);
//            }
//        }
//        return result;
//    }
    
    /**
     * 통합결재 요약 보기 HTML 조회
     * @param targetVo
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#getMobileApprovalDetail(omc.api.object.model.BusinessObjectRootVO)
     */
    @Override
    public String getMobileApprovalDetail( BusinessObjectRootVO targetVo ){
        String htmlSummary = "";
//                
//        switch( targetVo.getClassName() ){
//            case "PDRProperty":
//                PDRProperty pdrPropertyDom = new PDRProperty( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( pdrEmailService.getMobilePdrApprovalDetail(pdrPropertyDom) );
//                break;
//            case "AwardB2BProjectCode":
//                AwardB2BProjectCode awardB2BProjectCodeDom = new AwardB2BProjectCode( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( adjustAwardEmailService.getMobilePdrApprovalDetail(awardB2BProjectCodeDom) );
//                break;
//            case "PCOProperty":
//                PCOProperty pcoPropertyDom = new PCOProperty( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( pcoPropertyDom.getMobileApprovalDetail() );
//                break;
//            case "PRIProperty":
//                PRIProperty priPropertyDom = new PRIProperty( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( priPropertyDom.getMobileApprovalDetail() );
//                break;
//            case "PCRProperty":
//                PCRProperty pcrPropertyDom = new PCRProperty( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( pcrPropertyDom.getMobileApprovalDetail() );
//                break;
//            case "Consult":
//                Consult cDom = new Consult( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( cDom.getWorkflowHtml() );
//                break;
//            case "ChangeProcess":
//                ChangeProcess changeProcessDom = new ChangeProcess( targetVo.getObid() );
//                if(changeProcessDom.isProjectActivityDocumentChangeProcess()){
//                    List<ObjectRootVO> retrieveChangedItems = changeProcessDom.retrieveChangedItems();
//                    ProjectActivityDocument projectActivityDocument = DomUtil.toDom(retrieveChangedItems.get(0));
//                    htmlSummary = mailService.getMobileApprovalDetail( projectActivityDocument.getMobileApprovalDetail(changeProcessDom.getVo()) );
//                }else{
//                    htmlSummary = mailService.getMobileApprovalDetail( changeProcessDom.getMobileApprovalDetail() );
//                }
//                break;
//            case "DesignRequests":
//                DesignRequests designRequests = new DesignRequests( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( designRequests.getMobileApprovalDetail() );
//                break;
//            case "DesignChange":
//                DesignChangeDrop designChange = new DesignChangeDrop( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( designChange.getMobileApprovalDetail() );
//                break;
//            case "DesignDrop":
//                DesignChangeDrop designDrop = new DesignChangeDrop( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( designDrop.getMobileApprovalDetail() );
//                break;
//            case "DesignSpec":
//                DesignSpec designSpec = new DesignSpec( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( designSpec.getMobileApprovalDetail() );
//                break;
//            case "ProdPlanSpecSheetLifecycle":
//                ProdPlanSpecSheetLifecycle ppSpecSheetLifecycle = new ProdPlanSpecSheetLifecycle( targetVo.getObid() );
//                ProdPlanSpecSheetExVO ppSpecSheetExVo = ppSpecService.retrieveSpecSheetBySpecSheetId(ppSpecSheetLifecycle.getVo().getSpecSheetId());
//                htmlSummary = mailService.getMobileApprovalDetail( ppSpecSheetLifecycle.getMobileApprovalDetail(ppSpecSheetExVo) );
//                break;
//            case "ProdPlanPRMSheet":
//                ProdPlanPRMSheet prmSheetDom = new ProdPlanPRMSheet( targetVo.getObid() );
//                ProdPlanPrmParamVO paramVo = new ProdPlanPrmParamVO();
//                paramVo.setObid(prmSheetDom.getObid());
//                ProdPlanPrmSheetExtVO detailVo = ppPrmService.getPRMDataInfoAjax(paramVo);
//                
//                htmlSummary = mailService.getMobileApprovalDetail( prmSheetDom.getMobileApprovalDetail(detailVo) );
//                break;
//            case "ProdPlanSheetDistribute":
//                try {
//                    ProdPlanSheetDistribute sheetDom = new ProdPlanSheetDistribute( targetVo.getObid() );
//                    HashMap<String,Object> data = new HashMap<String, Object>();
//                    
//                    if( sheetDom.getVo() != null ){
//                        
//                        if ("SPEC".equals(sheetDom.getVo().getModule())) {
//                            ProdPlanSpecSheetExVO specSheet = ppSpecService.retrieveSpecSheetBySpecSheetId(sheetDom.getVo().getSheetId());
//                            data.put("title", specSheet.getTitles());
//                            data.put("divisionCode", specSheet.getDivisionCode());
//                            data.put("year", specSheet.getYear());
//                            data.put("version", specSheet.getVersion());
//                            data.put("subVersion", specSheet.getSubVersion());
//                            data.put("modifierName", specSheet.getModifierName());
//                            data.put("modified", specSheet.getModified());
//                        } else if ("PRM".equals(sheetDom.getVo().getModule())) {
//                            ProdPlanPrmParamVO prmParamVo = new ProdPlanPrmParamVO();
//                            prmParamVo.setPrmSheetId(sheetDom.getVo().getSheetId());
//                            ProdPlanPrmSheetExtVO prmSheet = ppPrmService.getPRMDataInfoAjax(prmParamVo);
//                            data.put("title", prmSheet.getTitles());
//                            data.put("divisionCode", prmSheet.getDivisionCode());
//                            data.put("year", prmSheet.getYear());
//                            data.put("version", prmSheet.getVersion());
//                            data.put("subVersion", prmSheet.getSubVersion());
//                            data.put("modifierName", prmSheet.getModifierName());
//                            data.put("modified", prmSheet.getModified());
//                        }
//
//                        data.put("module", sheetDom.getVo().getModule());
//                        htmlSummary = mailService.getMobileApprovalDetail( sheetDom.getMobileApprovalDetail(data) );
//                        
//                    }
//                } catch (Exception e) {
//                    // TODO: handle exception
//                }
//                
//                break;
//            case "PartProjectApproval":  // 2018.0.15 부품개발 프로젝트 결재
//                PartProjectApproval partApproval = new PartProjectApproval( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( partApproval.getMobileApprovalDetail() );
//                
//                break;
//            case "ReviewMeeting":
//                ReviewMeeting reviewMeeting = new ReviewMeeting( targetVo.getObid() );
//                htmlSummary = mailService.getMobileApprovalDetail( reviewMeeting.getMobileApprovalDetail() );
//                break;
//            default:
//                break;
//        }
        
        return htmlSummary;
    }

    /**
     * 통합결재 요약 보기 HTML 조회
     * @param targetVo
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#getMobileApprovalDetail(omc.api.object.model.BusinessObjectRootVO)
     */
    @Override
    public List<FilesVO> getMobileFileDetail( BusinessObjectRootVO targetVo ){
        List<FilesVO> file = new ArrayList<FilesVO>();
        BusinessObjectRoot dom = new BusinessObjectRoot(targetVo);
        return dom.getReleatedFiles(true);
    }
    
    /**
     * 
     * @param source
     * @param target
     * @see lgcns.rnd.application.workflow.service.WorkflowService#copyWorkflowList(omc.api.object.dom.BusinessObjectRoot, omc.api.object.dom.BusinessObjectRoot)
     */
    @Override
    public void txnCopyWorkflow(BusinessObjectRoot source, BusinessObjectRoot target, String workingState){
        if(NullUtil.isNull(source) || NullUtil.isNull(target)) throw new IllegalArgumentException();
        if(!source.getVo().getLifeCycle().equals(target.getVo().getLifeCycle())) throw new ApplicationException("Life Cycle is different.");
        if(isInProcessingWorkflow(target)) throw new ApplicationException("Copy is not possible because workflow is already in progress.");
        
        List<ApprovalVO> rtnApprovalVOList = new ArrayList<ApprovalVO>();
        
        StringBuffer relationPatternSb = new StringBuffer();
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE);
        relationPatternSb.append(",");
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP);
        relationPatternSb.append(",");
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWSUBSTEP);
        relationPatternSb.append(",");
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWSTEPNODEUSER);

        StringBuffer filterPatternSb = new StringBuffer();
        filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE);
        filterPatternSb.append(",");
        filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_WORKFLOWSTEP);
        filterPatternSb.append(",");
        filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_USERS);

        List<ObjectRootVO> rtnObjectRootVOList =
                source.getRelatedObjects(
                                            relationPatternSb.toString(),
                                            filterPatternSb.toString(),
                                            GlobalConstants.FLAG_TYPE_TO,
                                            null,           // selectPattern
                                            null,           // wherePattern
                                            null,           // parameterPattern
                                            false,          // bInclude
                                            false,          // bResultUnique
                                            0,              // objectLimit
                                            50);            // findDepth
        
        /*
         * 
         */
        Map<String, WorkflowRoute> workflowRouteVOMap = new HashMap<String, WorkflowRoute>();
        Map<String, WorkflowStepVO> workflowStepVOMap = new HashMap<String, WorkflowStepVO>();
        List<UsersVO> usersVOList = new ArrayList<UsersVO>();
        for(ObjectRootVO objectRootVO : rtnObjectRootVOList) {
            switch(objectRootVO.getClassName()) {
                case "WorkflowRoute":
                    WorkflowRouteVO workflowRouteVO = (WorkflowRouteVO)objectRootVO;
                    WorkflowRoute workflowRoute = DomUtil.toDom(workflowRouteVO);
                    workflowRouteVOMap.put(workflowRouteVO.getUniqueString(), workflowRoute);
                    break;
                case "WorkflowStep":
                    WorkflowStepVO workflowStepVO = (WorkflowStepVO)objectRootVO;
                    workflowStepVOMap.put(workflowStepVO.getUniqueString(), workflowStepVO);
                    break;
                case "Users":
                    UsersVO usersVO = (UsersVO)objectRootVO;
                    usersVOList.add(usersVO);
                    break;
            }
        }
        
        for (UsersVO usersVO : usersVOList) {
            ApprovalVO approvalVO = new ApprovalVO();
            Map<String, Object> usersOutDataMap = usersVO.getOutData();
            WorkflowStepVO workflowStepVO = workflowStepVOMap.get(usersVO.getUniqueStringParent());//UsersVO의 uniqueStringParent로 WorkflowSetpVO를 찾음
            WorkflowRoute workflowRoute = null;
            if(workflowStepVO.getOutData().get("rel_fromClass").equals(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE)) { //WorkflowStepVO의 uniqueStringParent로 WorkflowRoute를 찾음
                workflowRoute = workflowRouteVOMap.get(workflowStepVO.getUniqueStringParent()); 
            }else{
                WorkflowStepVO tragetWorkflowStepVO = workflowStepVO;
                boolean bFlag = true;
                while(bFlag) {
                    tragetWorkflowStepVO = workflowStepVOMap.get(tragetWorkflowStepVO.getUniqueStringParent());
                    if(tragetWorkflowStepVO.getOutData().get("rel_fromClass").equals(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE)) {
                        workflowRoute = workflowRouteVOMap.get(tragetWorkflowStepVO.getUniqueStringParent());  
                        bFlag = false;
                    }
                }
            }

            approvalVO.setState((String)workflowRoute.getVo().getOutData().get("rel_routeState"));
            
            if(!workingState.equals(approvalVO.getState()) && ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE.equals(usersVO.getStates())){
                approvalVO.setRecordMode("C");
                approvalVO.setState((String)workflowRoute.getVo().getOutData().get("rel_routeState"));
                approvalVO.setRouteInstructions((String)usersOutDataMap.get("rel_routeInstructions"));
                approvalVO.setProcessRole(NullUtil.isNull(usersOutDataMap.get("rel_processRole")) ? "" : (String)usersOutDataMap.get("rel_processRole") );
                approvalVO.setStep(workflowStepVO.getSequences());
                approvalVO.setSequence((Integer)usersOutDataMap.get("rel_sequences"));//rel_sequences
                approvalVO.setIsEssential(("FALSE".equals((String)usersOutDataMap.get("rel_isEssential")) ? false : true));
                approvalVO.setEssential((approvalVO.getIsEssential()) ? "Y" : "");
                approvalVO.setParallelNodeProcessionRule((String)usersOutDataMap.get("rel_parallelNodeProcessionRule"));
                approvalVO.setNotifyEmail(WorkflowConstants.TRUE.equals(usersOutDataMap.get("rel_notifyEmail")) ? true : false );
                approvalVO.setRoutePurpose(workflowRoute.getVo().getRouteBasePurpose());
                approvalVO.setAssignee(usersVO.getTitles());
                approvalVO.setAssigneeObid(usersVO.getObid());
                approvalVO.setUserStatus(usersVO.getStates());
        	    rtnApprovalVOList.add(approvalVO);
            }
        }
        if(NullUtil.isNone(rtnApprovalVOList)) throw new ApplicationException("The approval list to copy does not exist.");
        this.txnBuildWorkflow(target, rtnApprovalVOList);
    }

    /**
     * 
     * @param businessObjectRoot
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveApprovedList(omc.api.object.dom.BusinessObjectRoot)
     */
    @Override
    public List<BusinessObjectRootVO> retrieveApprovedList(BusinessObjectRoot businessObjectRoot){
        
        List<ObjectRootVO> workflowRouteVOList = businessObjectRoot.getWorkflowRouteVOListByRouteState(businessObjectRoot.getFirstState().getStateName());
        if(NullUtil.isNone(workflowRouteVOList)) throw new ApplicationException("The route does not exists.");
        
        WorkflowRoute firstWorkflowRoute = DomUtil.toDom(workflowRouteVOList.get(0).getObid(), false);
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer fromPatternBuf = new StringBuffer();
        
        fromPatternBuf.append("<this>                                                            ThisConnectedWithFrom   <["+ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE+"]@WOR>+");
        fromPatternBuf.append("<["+ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE+"]@WOR>     ToConnectedWithThis     <["+ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE+"]@WR>+");
        fromPatternBuf.append("<["+ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE+"]@WR>            ThisConnectedWithFrom   <["+ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK+"]@WRT>+");
        fromPatternBuf.append("<["+ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK+"]@WRT>       ToConnectedWithThis     <["+ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK+"]@WIT>+");
        fromPatternBuf.append("<["+ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK+"]@WIT>       ThisConnectedWithTo     <["+ApplicationSchemaConstants.RELCLASS_WORKFLOWPROJECTTASK+"]@WPT>+");
        fromPatternBuf.append("<["+ApplicationSchemaConstants.RELCLASS_WORKFLOWPROJECTTASK+"]@WPT>     FromConnectedWithThis   <["+ApplicationSchemaConstants.BIZCLASS_USERS+"]@USR>+");
 
        StringUtil.constructSelectPattern(selectPatternBuf, "@WIT.[*]+@WOR.[routeState]+@USR.[*]");
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, businessObjectRoot.getObid());
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@WIT.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, WorkflowConstants.APPROVAL_STATUS_COMPLETE);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@WIT.[processTimestamp]", GlobalConstants.OQL_OPERATOR_EQUAL, firstWorkflowRoute.getVo().getProcessTimestamp());
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@WIT.[approvalStatus]", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, WorkflowConstants.APPROVAL_STATUS_REJECT);

        List<BusinessObjectRootVO> result =  ObjectRoot.searchObjects( businessObjectRoot.getVo().getClassName(), 
                                         GlobalConstants.FLAG_TYPE_ALL, 
                                         GlobalConstants.FLAG_TYPE_ALL, 
                                         selectPatternBuf.toString(), 
                                         fromPatternBuf.toString(),
                                         wherePatternBuf.toString(), 
                                         paramPatternBuf.toString(), 
                                         false, 0);
        return result;
    }
    
    /**
     * 
     * @param businessObjectRoot
     * @param includeReject
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveAssigneeList(omc.api.object.dom.BusinessObjectRoot, boolean)
     */
    @Override
    public List<ApprovalVO> retrieveAssigneeList(BusinessObjectRoot businessObjectRoot){
        if(NullUtil.isNull(businessObjectRoot)) throw new IllegalArgumentException();
        List<String> routeStateList = LifeCycleUtil.getLifeCycleStateStringListByName(businessObjectRoot.getVo().getLifeCycle());
        List<ApprovalVO> rtnApprovalVOList = new ArrayList<ApprovalVO>();
        StringBuffer relationPatternSb = new StringBuffer();
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE);
        relationPatternSb.append(",");
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP);
        relationPatternSb.append(",");
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWSUBSTEP);
        relationPatternSb.append(",");
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWSTEPNODEUSER);

        StringBuffer filterPatternSb = new StringBuffer();
        filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE);
        filterPatternSb.append(",");
        filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_WORKFLOWSTEP);
        filterPatternSb.append(",");
        filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_USERS);

        List<ObjectRootVO> rtnObjectRootVOList =
                businessObjectRoot.getRelatedObjects(
                                            relationPatternSb.toString(),
                                            filterPatternSb.toString(),
                                            GlobalConstants.FLAG_TYPE_TO,
                                            null,           // selectPattern
                                            null,           // wherePattern
                                            null,           // parameterPattern
                                            false,          // bInclude
                                            false,          // bResultUnique
                                            0,              // objectLimit
                                            50);            // findDepth
        
        /*
         * 
         */
        Map<String, WorkflowRoute> workflowRouteVOMap = new HashMap<String, WorkflowRoute>();
        Map<String, WorkflowStepVO> workflowStepVOMap = new HashMap<String, WorkflowStepVO>();
        List<UsersVO> usersVOList = new ArrayList<UsersVO>();
        for(ObjectRootVO objectRootVO : rtnObjectRootVOList) {
            switch(objectRootVO.getClassName()) {
                case "WorkflowRoute":
                    WorkflowRouteVO workflowRouteVO = (WorkflowRouteVO)objectRootVO;
                    WorkflowRoute workflowRoute = DomUtil.toDom(workflowRouteVO);
                    workflowRouteVOMap.put(workflowRouteVO.getUniqueString(), workflowRoute);
                    break;
                case "WorkflowStep":
                    WorkflowStepVO workflowStepVO = (WorkflowStepVO)objectRootVO;
                    workflowStepVOMap.put(workflowStepVO.getUniqueString(), workflowStepVO);
                    break;
                case "Users":
                    UsersVO usersVO = (UsersVO)objectRootVO;
                    usersVOList.add(usersVO);
                    break;
            }
        }
        
        for (UsersVO usersVO : usersVOList) {
            ApprovalVO approvalVO = new ApprovalVO();
            Map<String, Object> usersOutDataMap = usersVO.getOutData();
            WorkflowStepVO workflowStepVO = workflowStepVOMap.get(usersVO.getUniqueStringParent());//UsersVO의 uniqueStringParent로 WorkflowSetpVO를 찾음
            WorkflowRoute workflowRoute = null;
            if(workflowStepVO.getOutData().get("rel_fromClass").equals(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE)) { //WorkflowStepVO의 uniqueStringParent로 WorkflowRoute를 찾음
                workflowRoute = workflowRouteVOMap.get(workflowStepVO.getUniqueStringParent()); 
            }else{
                WorkflowStepVO tragetWorkflowStepVO = workflowStepVO;
                boolean bFlag = true;
                while(bFlag) {
                    tragetWorkflowStepVO = workflowStepVOMap.get(tragetWorkflowStepVO.getUniqueStringParent());
                    if(tragetWorkflowStepVO.getOutData().get("rel_fromClass").equals(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE)) {
                        workflowRoute = workflowRouteVOMap.get(tragetWorkflowStepVO.getUniqueStringParent());  
                        bFlag = false;
                    }
                }
            }
            approvalVO.setState((String)workflowRoute.getVo().getOutData().get("rel_routeState"));
            approvalVO.setRouteStateSequence(routeStateList.indexOf((String)workflowRoute.getVo().getOutData().get("rel_routeState")));
            approvalVO.setUserStatus(usersVO.getStates());
            approvalVO.setDepartment(usersVO.getHrDepartmentKor());
            approvalVO.setAssignee(usersVO.getTitles());
            approvalVO.setAssigneeObid(usersVO.getObid());
            approvalVO.setAssigneeNames(usersVO.getNames());
            approvalVO.setAssigneeEmailAddress(usersVO.getEmailAddress());
            approvalVO.setStep(workflowStepVO.getSequences());
            approvalVO.setSequence((Integer)usersOutDataMap.get("rel_sequences"));//rel_sequences
            approvalVO.setRouteAction((String)usersOutDataMap.get("rel_routeAction"));
            approvalVO.setRouteInstructions((String)usersOutDataMap.get("rel_routeInstructions"));
            approvalVO.setNotifyEmail(WorkflowConstants.TRUE.equals(usersOutDataMap.get("rel_notifyEmail")) ? true : false );
            approvalVO.setProcessRole(NullUtil.isNull(usersOutDataMap.get("rel_processRole")) ? "" : (String)usersOutDataMap.get("rel_processRole") );
            approvalVO.setSelfReject(WorkflowConstants.TRUE.equals(usersOutDataMap.get("rel_selfReject")) ? true : false );
            approvalVO.setIsEssential(("FALSE".equals((String)usersOutDataMap.get("rel_isEssential")) ? false : true));
            approvalVO.setEssential("TRUE".equals((String)usersOutDataMap.get("rel_isEssential")) ? "Y" : "");
            rtnApprovalVOList.add(approvalVO);
        }            
        
        Collections.sort(rtnApprovalVOList, new WorkflowChainedComparator(
                new WorkflowRouteComparator(),
                new WorkflowRouteStepComparator())
        );
        return rtnApprovalVOList;
    }

    /**
     * 
     * @param workflowInboxTask
     * @return
     * @see lgcns.rnd.application.workflow.service.WorkflowService#retrieveFilesVOList(lgcns.rnd.api.object.workflow.dom.WorkflowInboxTask)
     */
    @Override
    public List<FilesVO> retrieveFilesVOList(WorkflowInboxTask workflowInboxTask){
        return workflowInboxTask.getReleatedFiles(true);
    }

    /**
     * 사용자 수동 배포
     * @param assigneeList
     * @param targetObjectList
     * @param comments
     * @see lgcns.rnd.application.workflow.service.WorkflowService#txnManualDistribute(java.util.List, java.util.List, java.lang.String)
     */
    @Override
    public void txnManualDistribute(List<UsersVO> assigneeList, List<BusinessObjectRootVO> targetObjectList, String comments){
        WorkflowRouteVO workflowRouteVO = new WorkflowRouteVO();
        workflowRouteVO.setRouteBasePurpose(WorkflowConstants.ROUTE_PURPOSE_DISTRIBUTION);
        WorkflowRoute workflowRoute = new WorkflowRoute(workflowRouteVO);
        
        for ( int idx = 0 ; idx < targetObjectList.size() ; idx++ ) {
            BusinessObjectRoot businessObjectRoot = DomUtil.toDom(targetObjectList.get(idx).getObid());
            if ( !NullUtil.isNull(businessObjectRoot) ) {
                for ( UsersVO assignee : assigneeList ) {
                    notifyMail(businessObjectRoot, workflowRoute, assignee);
                }
            }
        }
    }
}
    
