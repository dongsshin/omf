/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSWorkflowServiceUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 12.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.workflow.util;

import java.util.List;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.spring.SpringFactoryUtil;

import rap.api.object.project.dom.WBSItems;
import rap.api.object.workflow.dom.WorkflowInboxTask;
import rap.application.workflow.model.ApprovalVO;
import rap.application.workflow.model.ReassignVO;
import rap.application.workflow.model.WBSActivityWorkflowVO;
import rap.application.workflow.service.WBSActivityWorkflowService;

/**
 * <pre>
 * Class : WBSWorkflowServiceUtil
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class WBSWorkflowServiceUtil {
    private WBSActivityWorkflowService wbsActivityWorkflowService;

    private static WBSWorkflowServiceUtil sInstance;
    private synchronized static WBSWorkflowServiceUtil getInstance(){
        if (sInstance == null) {
            sInstance = new WBSWorkflowServiceUtil();
            sInstance.wbsActivityWorkflowService    = (WBSActivityWorkflowService)SpringFactoryUtil.getBean("wbsActivityWorkflowService");
        }
        return sInstance;
    }
    public static List<ApprovalVO> retrieveWBSActivityWorkflow(BusinessObjectRoot businessObjectRoot){
        return getInstance().wbsActivityWorkflowService.retrieveWBSActivityWorkflow(businessObjectRoot);
    }
    public static void txnBuildWBSActivityWorkflow(WBSActivityWorkflowVO wbsActivityForInboxVO){
        getInstance().wbsActivityWorkflowService.txnBuildWBSActivityWorkflow(wbsActivityForInboxVO);
    }
    public static void txnBuildWBSActivityWorkflowList(List<WBSActivityWorkflowVO> wbsActivityForInboxVOList){
        getInstance().wbsActivityWorkflowService.txnBuildWBSActivityWorkflowList(wbsActivityForInboxVOList);
    }
    public static void txnSubmitApproval(BusinessObjectRoot businessObjectRoot, ApprovalVO approvalVO){
        getInstance().wbsActivityWorkflowService.txnSubmitApproval(businessObjectRoot,approvalVO,false);
    }
    public static void txnSubmitApproval(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOList){
        for(ApprovalVO approvalVO : approvalVOList){
            getInstance().wbsActivityWorkflowService.txnSubmitApproval(businessObjectRoot,approvalVO,false);
        }
    }
    public static void txnDeleteApproval(List<ReassignVO> reassignVOList){
        for(ReassignVO reassignVO : reassignVOList){
            WorkflowInboxTask workflowInboxTask = DomUtil.toDom(reassignVO.getWorkflowInboxTaskObid());
            getInstance().wbsActivityWorkflowService.txnDeleteWorkflowInboxTaskNStepNodeUser(workflowInboxTask);
        }
    }
    public static void txnReassignApprover(BusinessObjectRoot businessObjectRoot, List<ReassignVO> reassignVOList){
        for (ReassignVO reassignVO : reassignVOList) {
            getInstance().wbsActivityWorkflowService.txnReassignApprover(businessObjectRoot, reassignVO);
        }
    }
    public static void txnAddApprover(WBSItems wbsItems, String projectScheduleObid, List<ReassignVO> reassignVOList){
        getInstance().wbsActivityWorkflowService.txnAddApprover(wbsItems, projectScheduleObid, reassignVOList);
    }
    
    public static void txnDeleteWorkflowAndCreateDeleteApprovalInfoToEP(String activityObid){
        getInstance().wbsActivityWorkflowService.txnDeleteWorkflowAndCreateDeleteApprovalInfoToEP(activityObid);
    }
}
