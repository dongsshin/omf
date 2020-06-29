package rap.application.constants;

import rap.application.constants.ApplicationSchemaConstants;

public class WorkflowConstants {
	
	public static final String[] INBOX_STATES_ALL = {ApplicationSchemaConstants.STATE_INBOX_TASK_COMPLETE,ApplicationSchemaConstants.STATE_INBOX_TASK_ASSIGNED,ApplicationSchemaConstants.STATE_INBOX_TASK_REVIEW};

	public static final String TRUE = "TRUE";
	public static final String FALSE = "FALSE";
	public static final String STATE_WORKING = "Working";
	
    public static final String DELIM_1 = ":";
    public static final String DELIM_1_S = "\\:";
    public static final String DELIM_2 = ",";
    public static final String DELIM_2_S = "\\,";

    // Mail Type
    public static final String MAIL_TYPE_NAME_ECO_DELAY = "ECO Delay";
    public static final String MAIL_TYPE_NAME_DELEGATE = "Delegate Information";
    public static final String MAIL_TYPE_NAME_DELEGATE_FAIL = "Delegate Fail Information";
    public static final String MAIL_TYPE_NAME_DISTRIBUTION = "Distribution";
    public static final String MAIL_TYPE_NAME_SCO_WORK = "Site ECO Coordinator Work";
    public static final String MAIL_TYPE_NAME_MBRPS = "MODEL BOM Distribution";
    public static final String MAIL_TYPE_NAME_USER_LOGIN_ALERT = "User Login History Alert";
    public static final String MAIL_TYPE_NAME_PERMISSION_EXPIRATION = "Permission Expiration";
    public static final String MAIL_TYPE_NAME_PERMISSION_EXPIRATION_NOTIFIY = "Permission Expiration Notify";

    
    public static final String MAIL_SUBJECT_PREFIX = "PLM";
    public static final String MAIL_TYPE_REJECT = "Reject";
    public static final String MAIL_TYPE_DISTRIBUTION = "Distribution";
    public static final String MAIL_TYPE_APPROVAL = "Approval";
    public static final String MAIL_TYPE_REASSIGN = "Reassign";
    
    // Mail Send Type
    public static final String MAIL_SEND_TYPE_SYSTEM = "System";
    public static final String MAIL_SEND_TYPE_MANUAL = "Manual";

    public static final String APPROVAL_STATUS_NONE = "None";
    public static final String APPROVAL_STATUS_APPROVE = "Approve";
    public static final String APPROVAL_STATUS_REJECT = "Reject";
    public static final String APPROVAL_STATUS_ACKNOWLEDGE = "Acknowledge";
    public static final String APPROVAL_STATUS_COMPLETE = "Complete";
    public static final String[] APPROVAL_STATUS_ALL = {APPROVAL_STATUS_NONE,APPROVAL_STATUS_APPROVE,APPROVAL_STATUS_REJECT,APPROVAL_STATUS_ACKNOWLEDGE,APPROVAL_STATUS_COMPLETE};
    public static final String[] APPROVAL_STATUS_SET_FORAPPROVED = {APPROVAL_STATUS_APPROVE,APPROVAL_STATUS_REJECT,APPROVAL_STATUS_ACKNOWLEDGE,APPROVAL_STATUS_COMPLETE};

    public static final String INBOX_TASK_TYPE_APPROVAL = "Approval";
    public static final String INBOX_TASK_TYPE_SAVEDRAFT = "SaveDraft";
    public static final String INBOX_TASK_TYPE_MOBILE_APPROVAL = "MobileApproval";
    public static final String INBOX_TASK_TYPE_DISTRIBUTION = "Distribution";
    public static final String INBOX_TASK_TYPE_REQUESTED = "Requested";
    public static final String INBOX_TASK_TYPE_PENDING_CHANGE = "PendingChange";
    public static final String INBOX_TASK_TYPE_PENDING_JOB_BY_RETIREMENT = "pendingJobByRetirementList";
    public static final String INBOX_TASK_TYPE_APPROVED = "Approved";
    public static final String INSTRUCTION_TYPE_STANDARD = "Standard";
    public static final String INSTRUCTION_TYPE_ORIGINATOR = "Originator";
    public static final String INSTRUCTION_TYPE_APPROVAL = "Approval";
    public static final String INSTRUCTION_TYPE_DISTRIBUTION = "Distribution";
    public static final String INSTRUCTION_TYPE_MANUAL_DISTRIBUTION = "ManualDistribution";
    public static final String INSTRUCTION_TYPE_SITE_ECO_SCOWORK = "SCOWork";
    public static final String INSTRUCTION_TYPE_MODEL_BOM_DISTRIBUTION = "MODEL BOM Distribution";
    public static final String ACTION_TYPE_END_WORKING = "EndWorking";
    public static final String STATES_TYPE_DEFINE = "Define";
    public static final String STATES_TYPE_ASSIGNED = "Assigned";
    public static final String STATES_TYPE_INPROCESS = "In Process";
    public static final String STATES_TYPE_COMPLETE = "Complete";
    public static final String ROUTESTATUS_TYPE_STOPPED = "Stopped";
    public static final String WORKFLOW_REASSIGN = "Reassign";
    
    public static final String ROUTE_ACTIONS_APPROVE = "Approve";
    public static final String ROUTE_ACTIONS_CONFIRM = "Confirm";
    public static final String ROUTE_ACTIONS_COMMENT = "Comment";
    public static final String ROUTE_ACTIONS_ENDWORKING = "EndWorking";
    public static final String[] ROUTE_ACTIONS_ALL = {ROUTE_ACTIONS_APPROVE,ROUTE_ACTIONS_CONFIRM,ROUTE_ACTIONS_COMMENT,ROUTE_ACTIONS_ENDWORKING};
    public static final String[] ROUTE_ACTIONS_NOTCOMMENT_ALL = {ROUTE_ACTIONS_APPROVE,ROUTE_ACTIONS_CONFIRM,ROUTE_ACTIONS_ENDWORKING};
    
    public static final String INBOX_TASK_TYPE_Workflow = "Workflow";
    public static final String INBOX_TASK_TYPE_WBSActivity = "WBS Activity";
    
    public static final String ACTION_COMMENTS_APPROVE = "Approve";
    public static final String ACTION_COMMENTS_CONFIRM = "Confirm";
    public static final String ACTION_COMMENTS_COMMENT = "Comment";
    public static final String ACTION_COMMENTS_ACKNOWLEDGE = "Acknowledge";
    public static final String ROUTE_PURPOSE_STANDARD = "Standard";
    public static final String ROUTE_PURPOSE_DISTRIBUTION = "Distribution";
    public static final String ROUTE_PURPOSE_CONFIRMATION = "Confirmation";
    public static final String ROUTE_PURPOSE_APPROVAL = "Approval";
    public static final String ROUTE_PURPOSE_REVIEW = "Review";
    
    public static final String ROUTE_INSTRUCTIONS_APPROVE = "A";
    
    public static final String AUTO_ACKNOWLEDGE = "Auto Acknowledge";
    public static final String AUTO_PROMOTE = "Auto Promote";

    public static final String ACTION_TYPE_PENDING = "Pending";
    public static final String ACTION_TYPE_APPROVED = "Approved";
    public static final String ACTION_TYPE_REJECTED = "Rejected";
    public static final String ACTION_TYPE_ACKNOWLEDGED = "Acknowledged";
    public static final String ACTION_TYPE_AWAITING_APPROVAL = "Awaiting Approval";
    public static final String ACTION_TYPE_AWAITING_ACKNOWLEDGE = "Awaiting Acknowledge";
    public static final String ACTION_TYPE_REASSIGNED = "Reassigned";

    // Route Action
    public static final String ROUTE_ACTION_ALL = "All";
    public static final String ROUTE_ACTION_ANY = "Any";

    // WBS inbox task
    public static final String WBS_INBOX_TASK_TYPE = "WBS Activity";
    public static final String WBS_APPROVERS_RESPONSIBILITY = "Engineering";
    public static final String WBS_TASK_REQUIREMENT = "Optional";
    public static final String WBS_DATE_OFFSET_FROM = "Task Create Date";
    public static final String WBS_ACTION_COMMENTS = "Approve";
    public static final String INBOXTASK_TYPE_WORKFLOW = "Workflow";
    public static final String INBOXTASK_TYPE_WBSACTIVITY = "WBS Activity";
}
