/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : WorkflowService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 12.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.service;

import java.util.HashMap;
import java.util.List;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.FilesVO;

import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.relation.model.WorkflowStepNodeUserVO;
import rap.api.object.workflow.dom.WorkflowInboxTask;
import rap.api.object.workflow.dom.WorkflowRoute;
import rap.api.object.workflow.model.ApprovalLineStateVO;
import rap.api.object.workflow.model.ApprovalLineVO;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.application.workflow.model.ApprovalHistoryVO;
import rap.application.workflow.model.ApprovalVO;
import rap.application.workflow.model.MailSendVO;
import rap.application.workflow.model.PendingJobByRetirementSearchVO;
import rap.application.workflow.model.PendingJobByRetirementVO;
import rap.application.workflow.model.ReassignVO;


/**
 * <pre>
 * Class : WorkflowService
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public interface WorkflowService {
    /**
     * 결재라인 정보 임시 저장 메소드 
     * 결재자들이 결재를 진행 할 수는 없고 결재라인 정보만 저장 함.
     * @param businessObjectRoot
     * @param approvalVOList
     */
    void txnBuildWorkflow(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList);
    
    /**
     * 결재라인 정보를 저장 한 후 첫번째 state(ex:Working)를 start 해서 결재자들이 결재를 진행 할 수 있는 상태
     * 단 첫번째 state에 결재자가 한명이상 없으면 Exception 발생 
     * @param businessObjectRoot
     * @param approvalVOList
     */
    void txnBuildWorkflowAndStartFirstState(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList);
    
    /**
     * 결재라인 정보를 저장 한 후 첫번째 state(ex:Working)에서 next target state(스키마 branch 정보에 따라) auto promote 시켜서 next target state의 결재자들이 결재를 할 수 상태
     * 단 첫번째 state에 결재자가 존재 하면 Exception 발생  
     *
     * @param businessObjectRoot
     * @param approvalVOList
     */
    void txnBuildWorkflowAndPromote(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList);
    /**
     * 결재가 시작 된 이후의 결재자 추가, 수정 및 삭제하기 위해
     *
     * @param businessObjectRoot
     * @param approvalVOList
     */
    void txnUpdateWorkflow(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList);
    boolean isStartedFirstState(BusinessObjectRoot businessObjectRoot);
    boolean isInProcessingWorkflow(BusinessObjectRoot businessObjectRoot);
    List<ApprovalVO> retrieveWorkflow(BusinessObjectRoot businessObjectRoot);
    List<ApprovalVO> retrieveWorkflowForMobile(BusinessObjectRoot businessObjectRoot);
    void txnStartRoute(BusinessObjectRoot businessObjectRoot, String toBeStartState);
    List<CodeDetailVO> retrieveApprovalStateList(String lifeCycleName);
    List<CodeDetailVO> retrieveAllApprovalStateList(String lifeCycleName);
    List<CodeDetailVO> retrieveApprovalStateList(String lifeCycleName, boolean isIncludeWorking);
    String retrieveDistributionState(String lifeCycleName);
    void txnSubmitApproval(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO);
    void txnSubmitApprovalBySimple(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO, List<ApprovalVO> toUpdateApprovalList);
    void txnAddDistribution(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList);
    void txnSubmitSelfReject(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO);
    List<ApprovalHistoryVO> retrieveApprovalHistoryList(BusinessObjectRootVO businessObjectRootVO);
    List<ApprovalHistoryVO> retrievePreviousRejectedHistory(BusinessObjectRootVO businessObjectRootVO);
//    void txnDoCancelWorkflow(BusinessObjectRoot businessObjectRoot);
    /**
     * source인 Business Object의 현재 approval process를 target인 Business Object의 approval process로 복사.
     * Validation: 1) source와 target의 life cycle일 동일 해야 함, 2) target Business Object가 결재 진행중이지 않아야 함.
     * @param obid
     * @return
     */
    void txnCopyWorkflow(BusinessObjectRoot source, BusinessObjectRoot target, String workingState);
    /**
     * 다음 차수 결재자가 동일인일 경우 자동 승인 결재 함.
     *
     * @param businessObjectRoot
     */
    void rescursiveApproveBySystem(BusinessObjectRoot businessObjectRoot);
    
    /**
     *  routeState(ex:Working, Processing...)에 마지막 승인자의 Inbox task 구하기
     *
     * @param businessObjectRoot
     * @param routeState
     * @return
     */
    WorkflowInboxTaskVO retrieveFinalApprove(BusinessObjectRoot businessObjectRoot, String routeState);
    List<BusinessObjectRootVO> retrieveApprovedList(BusinessObjectRoot businessObjectRoot);
    List<WorkflowInboxTaskVO> retrieveWorkflowInboxTaskVONUsersVOList(BusinessObjectRoot businessObjectRoot, String routeStates);
    List<WorkflowStepNodeUserVO> retrieveWorkflowStepNodeUserVONUsersList(BusinessObjectRoot businessObjectRoot, String routeStates);
    List<WorkflowStepNodeUserVO> retrieveWorkflowStepNodeUserVONUsersListByStep(BusinessObjectRoot businessObjectRoot, String states, int step);
    boolean isExistWorkflowStepNodeUser(BusinessObjectRoot businessObjectRoot, String states, int step, String userName);
    void txnReject(BusinessObjectRoot businessObjectRoot, String routeState);
//    void txnWithdraw(BusinessObjectRoot businessObjectRoot, DemoteVO demoteVO);
    boolean isLastApprovalInWorking(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO);
    boolean isLastApprovalInState(BusinessObjectRoot businessObjectRoot, String state);
//    boolean isLastApproval(BusinessObjectRoot businessObjectRoot);
//    void txnDeleteWorkflow(BusinessObjectRoot businessObjectRoot);
//    void txnDeleteWorkflowByState(BusinessObjectRoot businessObjectRoot, String state);
    void txnReassignApprover(BusinessObjectRoot businessObjectRoot, ReassignVO reassignVO);
    boolean isExistsAssigneeTargetStates(BusinessObjectRoot businessObjectRoot, String routeStates);
    List<BusinessRelationObjectVO> retrieveAssigneeTargetStates(BusinessObjectRoot businessObjectRoot, String routeStates);
    ApprovalVO retrieveInProcessWorkflowRouteNStep(BusinessObjectRoot businessObjectRoot);
    void txnDoReassignList(List<ReassignVO> reassignVOList);
    List<ApprovalVO> retrieveSendBackToList(ApprovalVO approvalVO);
    void txnChangeOriginator(BusinessObjectRoot businessObjectRoot, ReassignVO reassignVO);
    void createApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute  workflowRoute, WorkflowInboxTask workflowInboxTask);
    void createSubmitApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute  workflowRoute, WorkflowInboxTask workflowInboxTask, String approvalStatus);
    void createReassignApprovalInfoToEP(BusinessObjectRoot businessObjectRoot, WorkflowRoute  workflowRoute, WorkflowInboxTask fromWorkflowInboxTask, WorkflowInboxTask toWorkflowInboxTask);
    void notifyMail(BusinessObjectRoot businessObjectRoot, WorkflowRoute  workflowRoute, BusinessObjectRootVO usersVO);
    void notifyMail(BusinessObjectRoot businessObjectRoot, WorkflowRoute  workflowRoute, String usersNames/*사번*/);
    void txnCreateDeleteApprovalInfoToEP(String workflowRouteObid, String workflowInboxTaskObid);
//    void txnRevoke(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO);
//    int txnCreateApprovalLine(String userObid, ApprovalLineVO approvalLineVO, List<ApprovalLineStateVO> approvalLineStateList);
//    void txnUpdateApprovalLine(String userObid, String approvalLineObid, ApprovalLineVO approvalLineVO);
    void txnUpdateApprovalLineList(String userObid, List<ApprovalLineVO> approvalLineList);
    int txnUpdateApprovalLineStateList(String approvalLineObid, List<ApprovalLineStateVO> createList, List<ApprovalLineStateVO> updateList, List<ApprovalLineStateVO> deleteList, Boolean isEssential, String relationName);
    void txnUpdateDefaultApprovalLine(String userObid, String approvalLineObid);
    void txnDeleteApprovalLine(String approvalLineObid);
    List<ApprovalLineStateVO> txnChangeParticipantUser(String beforeUserObid, String afterUserObid, String targetEssentialApproverObid);
    ApprovalLineVO retrieveApprovalLine(String policyName, String plantName);
    List<ApprovalLineStateVO> retrieveApprovalLineStateList(String policyName, String plantName);
    List<WorkflowInboxTaskVO> retrieveDistributionHistoryList(String obid) throws Exception;
    void txnCreateEssentialApproverLog(String opType, String targetObid, String targetUserObid);
//    List<String> checkExistEssentialApprover(List<ApprovalVO> approvalUserList, String className, String site, String policyName, boolean isFromWorkflow);
    void txnCreateMailSendInfo(MailSendVO mailSendVO);  // E-mail 발송용 데이터 생성
//    void txnCreateMailSendInfo(String obid);
    List<ApprovalVO> txnSubmitMassApproval(List<ApprovalVO> targetObjectList);
    void txnDoMassAcknowledge(List<ApprovalVO> targetObjectList);
//    HashMap<String, Object> excelImportApprovalLine(List<ApprovalLineExcelImportVO> dataList, boolean isForRequest);
    List<ApprovalLineStateVO> retrieveApprovalLineState(String approvalLineObid, ApprovalLineStateVO approvalLineStateVO);
//
    void txnManualApprove(WorkflowInboxTask workflowInboxTask, String approvalStatus, String comments, boolean isFromWebService);
    void txnManualApprove(WorkflowInboxTask workflowInboxTask, String approvalStatus, String comments, boolean isFromWebService, String webServiceFlag);
    WorkflowInboxTask retrieveUserApproveInfo(String taskObid, String userId);
    List<WorkflowInboxTaskVO> retrieveNotificationFromRoute(BusinessObjectRoot businessObjectRoot);
    List<WorkflowInboxTaskVO> pendingJobByRetirementList(PendingJobByRetirementVO searchInfo);
    List<PendingJobByRetirementSearchVO> pendingJobByRetirementListTechnicalDoc( PendingJobByRetirementSearchVO searchInfo );
    String getMobileApprovalDetail( BusinessObjectRootVO targetVo );
    List<FilesVO> getMobileFileDetail( BusinessObjectRootVO targetVo );
//    /* Test ..................... Deletion*/
    List<HashMap<String, Object>> retrieveApprovalHistoryMig(String obid);
    List<ApprovalVO> retrieveAssigneeList(BusinessObjectRoot businessObjectRoot);
    List<FilesVO> retrieveFilesVOList(WorkflowInboxTask workflowInboxTask);
    void txnManualDistribute(List<UsersVO> assigneeList, List<BusinessObjectRootVO> targetObjectList, String comments);
}
