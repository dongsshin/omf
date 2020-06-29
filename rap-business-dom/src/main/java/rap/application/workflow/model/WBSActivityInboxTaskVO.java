/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSActivityInboxTaskVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 7.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import com.rap.omc.schema.util.OmcUniqueIDGenerator;

import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.application.constants.WorkflowConstants;

/**
 * <pre>
 * Class : WBSActivityInboxTaskVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class WBSActivityInboxTaskVO extends WorkflowInboxTaskVO{
    
    public WBSActivityInboxTaskVO() {
        super();
        this.setRouteAction(WorkflowConstants.ROUTE_ACTIONS_APPROVE);
        this.setInboxTaskType(WorkflowConstants.WBS_INBOX_TASK_TYPE);
        this.setApproversResponsibility(WorkflowConstants.WBS_APPROVERS_RESPONSIBILITY);
        this.setTaskRequirement(WorkflowConstants.WBS_TASK_REQUIREMENT);
        this.setDueDateOffset(0);
        this.setDateOffsetFrom(WorkflowConstants.WBS_DATE_OFFSET_FROM);
        this.setActionComments(WorkflowConstants.WBS_ACTION_COMMENTS);
        this.setReviewCommentsNeeded(false);
        this.setNotifyEmail(false);
    }
    
    public WBSActivityInboxTaskVO(String routeAction, String inboxTaskType, String approversResponsibility, 
            String taskRequirement, Integer dueDateOffset, String dateOffsetFrom, String actionComments,
            Boolean reviewCommentsNeeds, String processTimestamp, Boolean notifyEmail) {
        super();
        this.setRouteAction(null == routeAction ? WorkflowConstants.ROUTE_ACTIONS_APPROVE: routeAction);
        this.setInboxTaskType(null ==inboxTaskType ? WorkflowConstants.WBS_INBOX_TASK_TYPE : inboxTaskType);
        this.setApproversResponsibility(null == approversResponsibility ? WorkflowConstants.WBS_APPROVERS_RESPONSIBILITY : approversResponsibility);
        this.setTaskRequirement(null == taskRequirement ? WorkflowConstants.WBS_TASK_REQUIREMENT : taskRequirement);
        this.setDueDateOffset(null == dueDateOffset ? 0 : dueDateOffset);
        this.setDateOffsetFrom(null == dateOffsetFrom ? WorkflowConstants.WBS_DATE_OFFSET_FROM : dateOffsetFrom);
        this.setActionComments(null == actionComments ? WorkflowConstants.WBS_ACTION_COMMENTS : actionComments);
        this.setReviewCommentsNeeded(null == reviewCommentsNeeds ? false : reviewCommentsNeeds);
        this.setProcessTimestamp(null == processTimestamp ? OmcUniqueIDGenerator.getObid() : processTimestamp);
        this.setNotifyEmail(null == notifyEmail ? false : notifyEmail);
    }
}
