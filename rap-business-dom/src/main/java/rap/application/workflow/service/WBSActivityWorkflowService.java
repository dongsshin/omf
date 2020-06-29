/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSWorkflowService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 7.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.service;

import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.schema.object.temp.model.ActivityValidationResultVO;

import rap.api.object.project.dom.WBSItems;
import rap.api.object.workflow.dom.WorkflowInboxTask;
import rap.api.object.workflow.dom.WorkflowRoute;
import rap.application.workflow.model.ApprovalVO;
import rap.application.workflow.model.ReassignVO;
import rap.application.workflow.model.WBSActivityWorkflowVO;


/**
 * <pre>
 * Class : WBSActivityWorkflowService
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public interface WBSActivityWorkflowService {
    List<ApprovalVO> retrieveWBSActivityWorkflow(BusinessObjectRoot businessObjectRoot);
    void txnBuildWBSActivityWorkflow(WBSActivityWorkflowVO wbsActivityForInboxVO);
    void txnBuildWBSActivityWorkflowList(List<WBSActivityWorkflowVO> wbsActivityForInboxVOList);
    void txnSubmitApproval(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO, Boolean skipWarning);
    void txnReassignApprover(BusinessObjectRoot businessObjectRoot, ReassignVO reassignVO, boolean changeActivityOwner);
    void txnReassignApprover(BusinessObjectRoot businessObjectRoot, ReassignVO reassignVO);
    Map<String, String> retrieveWBSActivityApprovalProcessInformation(WorkflowInboxTask workflowInboxTask);
    List<ActivityValidationResultVO> validateCompleteWBSActivity(BusinessObjectRoot businessObjectRoot, WorkflowInboxTask workflowInboxTask);
    Boolean isLastApproval(BusinessObjectRoot businessObjectRoot, WorkflowInboxTask workflowInboxTask);
    void txnManualApprove(WorkflowInboxTask workflowInboxTask, String approvalStatus, String comments, boolean isFromWebService, String webServiceFlag);
    void txnReassignApproverList(List<ReassignVO> reassignVOList);
    void txnDeleteWorkflowInboxTaskNStepNodeUser(WorkflowInboxTask workflowInboxTask);
    public void createWorkflowInboxTaskList(WBSActivityWorkflowVO wbsActivityForInboxVO, WorkflowRoute workflowRoute);
    public void txnAddApprover(WBSItems wbsItems, String projectScheduleObid, List<ReassignVO> reassignVOList);
    public void txnDeleteWorkflowAndCreateDeleteApprovalInfoToEP(String activityObid);
}
