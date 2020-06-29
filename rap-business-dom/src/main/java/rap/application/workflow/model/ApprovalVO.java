/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ApprovalLineVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 13.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.rap.omc.api.object.model.BaseModel;




/**
 * <pre>
 * Class : ApprovalLineVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class ApprovalVO extends BaseModel {
    private String workflowRouteObid;   // EP System PK
    private String workflowStepNodeUserObid;    // WorkflowInboxTaskVO.routeNodeObid
    private String workflowInboxTaskObid;   // EP Task ID
    private String state;
    private Integer step;
    private Integer sequence;
    private String titles;
    private String parallelNodeProcessionRule;
    private Boolean isEssential = (Boolean)false;
    
    private String r;
    private String approverInfo;
    private String assignee;
    private String assigneeNames;
    private String assigneeObid;
    private String action;
    private String userStatus;
    private String reviewApproveComments;
    private Date actualCompletionDate;
    private String department;
    private String dueDate;
    private String actionCommnets;
    private String etc;
    private String comments;
    private Integer routeStateSequence;
    private String approvalStatus;
    private String dummyStep;
    private String routeAction;
    private String routeInstructions;
    private String obid;
    private String essential;
    private Boolean availableToApproval = (Boolean)false;
    private List<ApprovalVO> sendBackToList;
    private String stateOfWorkflowStep;
    private Boolean availableToRevoke;

    private String mode;
    private String processId;
    private String changeName;
    private String resultCnt;
    private String mobileApproval;
    private String inboxTaskState;
    private String routePurpose;
    private Boolean notifyEmail         = (Boolean)true;
    private String processRole;
    private Boolean maintainInboxTask   = (Boolean)true;
    private String originTaskOwner;
    private String taskOwner;
    private HashMap<String, Object> outData = new HashMap<String, Object>();
    private Boolean checkFilesExist     = (Boolean)false;
    private String assigneeEmailAddress;
    private String inboxTaskType;
    private Boolean selfReject         = (Boolean)false;
    private String signal; //For Document(Change Process)
    //private String approverEmail;
    private String rejectCode;
    private Boolean callCompleteWBSActivity = (Boolean)true;
    private Boolean remainSelfRejectionInboxTask  = (Boolean)true;
    
    
    
    /**
     * 
     * 
     * @return the remainSelfRejectionInboxTask
     */
    public Boolean getRemainSelfRejectionInboxTask(){
        return remainSelfRejectionInboxTask;
    }


    
    /**
     * 
     * 
     * @param remainSelfRejectionInboxTask the remainSelfRejectionInboxTask to set
     */
    public void setRemainSelfRejectionInboxTask(Boolean remainSelfRejectionInboxTask){
        this.remainSelfRejectionInboxTask = remainSelfRejectionInboxTask;
    }


    /**
     * 
     * 
     * @return the callCompleteWBSActivity
     */
    public Boolean getCallCompleteWBSActivity(){
        return callCompleteWBSActivity;
    }

    
    /**
     * 
     * 
     * @param callCompleteWBSActivity the callCompleteWBSActivity to set
     */
    public void setCallCompleteWBSActivity(Boolean callCompleteWBSActivity){
        this.callCompleteWBSActivity = callCompleteWBSActivity;
    }

    /**
     * 
     *
     * @return
     */
    public boolean canBeSubmittable(){
        boolean bRtnValue = false;
        if(this.approvalStatus.equals("None") &&  this.inboxTaskState.equals("Assigned")) bRtnValue = true;
        return bRtnValue;
    }
    
    /**
     * 
     * 
     * @return the rejectCode
     */
    public String getRejectCode(){
        return rejectCode;
    }
    
    /**
     * 
     * 
     * @param rejectCode the rejectCode to set
     */
    public void setRejectCode(String rejectCode){
        this.rejectCode = rejectCode;
    }


    public String getApproverEmail() {
        return assigneeEmailAddress;
    }
    /**
     * 
     * 
     * @return the approverInfo
     */
    public String getApproverInfo(){
        return approverInfo;
    }


    
    /**
     * 
     * 
     * @param approverInfo the approverInfo to set
     */
    public void setApproverInfo(String approverInfo){
        this.approverInfo = approverInfo;
    }


    /**
     * 
     * 
     * @return the workflowStepNodeUserObid
     */
    public String getWorkflowStepNodeUserObid(){
        return workflowStepNodeUserObid;
    }

    
    /**
     * 
     * 
     * @param workflowStepNodeUserObid the workflowStepNodeUserObid to set
     */
    public void setWorkflowStepNodeUserObid(String workflowStepNodeUserObid){
        this.workflowStepNodeUserObid = workflowStepNodeUserObid;
    }

    /**
     * 
     * 
     * @return the state
     */
    public String getState(){
        return state;
    }
    
    /**
     * 
     * 
     * @param state the state to set
     */
    public void setState(String state){
        this.state = state;
    }
    
    /**
     * 
     * 
     * @return the step
     */
    public Integer getStep(){
        return step;
    }
    
    /**
     * 
     * 
     * @param step the step to set
     */
    public void setStep(Integer step){
        this.step = step;
    }
    
    /**
     * 
     * 
     * @return the sequence
     */
    public Integer getSequence(){
        return sequence;
    }

    
    /**
     * 
     * 
     * @param sequence the sequence to set
     */
    public void setSequence(Integer sequence){
        this.sequence = sequence;
    }

    /**
     * 
     * 
     * @return the displayStepName
     */
    public String getTitles(){
        return titles;
    }

    
    /**
     * 
     * 
     * @param displayStepName the displayStepName to set
     */
    public void setTitles(String titles){
        this.titles = titles;
    }

    /**
     * 
     * 
     * @return the assigneeObid
     */
    public String getAssigneeObid(){
        return assigneeObid;
    }
    
    /**
     * 
     * 
     * @param assigneeObid the assigneeObid to set
     */
    public void setAssigneeObid(String assigneeObid){
        this.assigneeObid = assigneeObid;
    }
    
    /**
     * 
     * 
     * @return the parallelNodeProcessionRule
     */
    public String getParallelNodeProcessionRule(){
        return parallelNodeProcessionRule;
    }
    
    /**
     * 
     * 
     * @param parallelNodeProcessionRule the parallelNodeProcessionRule to set
     */
    public void setParallelNodeProcessionRule(String parallelNodeProcessionRule){
        this.parallelNodeProcessionRule = parallelNodeProcessionRule;
    }
    
    /**
     * 
     * 
     * @param isEssential the isEssential to set
     */
    public void setIsEssential(Boolean isEssential){
        this.isEssential = isEssential;
    }
    
    
    /**
     * 
     * 
     * @return the isEssentail
     */
    public Boolean getIsEssential(){
        return isEssential;
    }
    
    /**
     * 
     * 
     * @return the r
     */
    public String getR(){
        return r;
    }


    
    /**
     * 
     * 
     * @param r the r to set
     */
    public void setR(String r){
        this.r = r;
    }


    
    /**
     * 
     * 
     * @return the assignee
     */
    public String getAssignee(){
        return assignee;
    }


    
    /**
     * 
     * 
     * @param assignee the assignee to set
     */
    public void setAssignee(String assignee){
        this.assignee = assignee;
    }


    
    /**
     * 
     * 
     * @return the action
     */
    public String getAction(){
        return action;
    }


    
    /**
     * 
     * 
     * @param action the action to set
     */
    public void setAction(String action){
        this.action = action;
    }


    
    /**
     * 
     * 
     * @return the userStatus
     */
    public String getUserStatus(){
        return userStatus;
    }


    
    /**
     * 
     * 
     * @param userStatus the userStatus to set
     */
    public void setUserStatus(String userStatus){
        this.userStatus = userStatus;
    }


    
    /**
     * 
     * 
     * @return the reviewApproveComments
     */
    public String getReviewApproveComments(){
        return reviewApproveComments;
    }


    
    /**
     * 
     * 
     * @param reviewApproveComments the reviewApproveComments to set
     */
    public void setReviewApproveComments(String reviewApproveComments){
        this.reviewApproveComments = reviewApproveComments;
    }


    
    /**
     * 
     * 
     * @return the completedDate
     */
    public Date getActualCompletionDate(){
        return actualCompletionDate;
    }

    /**
     * 
     * 
     * @param completedDate the completedDate to set
     */
    public void setActualCompletionDate(Date actualCompletionDate){
        this.actualCompletionDate = actualCompletionDate;
    }
    
    public void setActualCompletionDate(Long actualCompletionDate){
        this.actualCompletionDate = new Date(actualCompletionDate);
    }
    
    
    /**
     * 
     * 
     * @return the department
     */
    public String getDepartment(){
        return department;
    }


    
    /**
     * 
     * 
     * @param department the department to set
     */
    public void setDepartment(String department){
        this.department = department;
    }


    
    /**
     * 
     * 
     * @return the dueDate
     */
    public String getDueDate(){
        return dueDate;
    }


    
    /**
     * 
     * 
     * @param dueDate the dueDate to set
     */
    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
    }


    
    /**
     * 
     * 
     * @return the actionCommnets
     */
    public String getActionCommnets(){
        return actionCommnets;
    }


    
    /**
     * 
     * 
     * @param actionCommnets the actionCommnets to set
     */
    public void setActionCommnets(String actionCommnets){
        this.actionCommnets = actionCommnets;
    }


    
    /**
     * 
     * 
     * @return the etc
     */
    public String getEtc(){
        return etc;
    }


    
    /**
     * 
     * 
     * @param etc the etc to set
     */
    public void setEtc(String etc){
        this.etc = etc;
    }
    
    /**
     * 
     * 
     * @return the comments
     */
    public String getComments(){
        return comments;
    }
    
    /**
     * 
     * 
     * @param comments the comments to set
     */
    public void setComments(String comments){
        this.comments = comments;
    }

    
    
    /**
     * 
     * 
     * @return the routeStateSequence
     */
    public Integer getRouteStateSequence(){
        return routeStateSequence;
    }


    
    /**
     * 
     * 
     * @param routeStateSequence the routeStateSequence to set
     */
    public void setRouteStateSequence(Integer routeStateSequence){
        this.routeStateSequence = routeStateSequence;
    }

    

    
    /**
     * 
     * 
     * @return the dummyStep
     */
    public String getDummyStep(){
        return dummyStep;
    }


    
    /**
     * 
     * 
     * @param dummyStep the dummyStep to set
     */
    public void setDummyStep(String dummyStep){
        this.dummyStep = dummyStep;
    }

    /**
     * 
     * 
     * @return the approvalStatus
     */
    public String getApprovalStatus(){
        return approvalStatus;
    }

    
    /**
     * 
     * 
     * @param approvalStatus the approvalStatus to set
     */
    public void setApprovalStatus(String approvalStatus){
        this.approvalStatus = approvalStatus;
    }
    
    /**
     * 
     * 
     * @return the workflowRouteObid
     */
    public String getWorkflowRouteObid(){
        return workflowRouteObid;
    }
    
    /**
     * 
     * 
     * @param workflowRouteObid the workflowRouteObid to set
     */
    public void setWorkflowRouteObid(String workflowRouteObid){
        this.workflowRouteObid = workflowRouteObid;
    }
    
    /**
     * 
     * 
     * @return the routeAction
     */
    public String getRouteAction(){
        return routeAction;
    }
    
    /**
     * 
     * 
     * @param routeAction the routeAction to set
     */
    public void setRouteAction(String routeAction){
        this.routeAction = routeAction;
    }


    
    /**
     * 
     * 
     * @return the routeInstructions
     */
    public String getRouteInstructions(){
        return routeInstructions;
    }


    
    /**
     * 
     * 
     * @param routeInstructions the routeInstructions to set
     */
    public void setRouteInstructions(String routeInstructions){
        this.routeInstructions = routeInstructions;
    }
    
    /**
     * 
     * 
     * @return the obid
     */
    public String getObid(){
        return obid;
    }
    
    /**
     * 
     * 
     * @param obid the obid to set
     */
    public void setObid(String obid){
        this.obid = obid;
    }
    
    /**
     * 
     * 
     * @return the essential
     */
    public String getEssential(){
        return essential;
    }
    
    /**
     * 
     * 
     * @param essential the essential to set
     */
    public void setEssential(String essential){
        this.essential = essential;
        if("Y".equals(this.essential)) {
            setIsEssential(true);
        }
    }
    

    
    /**
     * 
     * 
     * @return the availableToApproval
     */
    public Boolean getAvailableToApproval(){
        return availableToApproval;
    }


    
    /**
     * 
     * 
     * @param availableToApproval the availableToApproval to set
     */
    public void setAvailableToApproval(Boolean availableToApproval){
        this.availableToApproval = availableToApproval;
    }
    
    
    /**
     * 
     * 
     * @return the sendBackToList
     */
    public List<ApprovalVO> getSendBackToList(){
        return sendBackToList;
    }


    
    /**
     * 
     * 
     * @param sendBackToList the sendBackToList to set
     */
    public void setSendBackToList(List<ApprovalVO> sendBackToList){
        this.sendBackToList = sendBackToList;
    }
    
    /**
     * 
     * 
     * @return the stateOfWorkflowStep
     */
    public String getStateOfWorkflowStep(){
        return stateOfWorkflowStep;
    }


    
    /**
     * 
     * 
     * @param stateOfWorkflowStep the stateOfWorkflowStep to set
     */
    public void setStateOfWorkflowStep(String stateOfWorkflowStep){
        this.stateOfWorkflowStep = stateOfWorkflowStep;
    }
    
    /**
     * 
     * 
     * @return the workflowInboxTaskObid
     */
    public String getWorkflowInboxTaskObid(){
        return workflowInboxTaskObid;
    }


    
    /**
     * 
     * 
     * @param workflowInboxTaskObid the workflowInboxTaskObid to set
     */
    public void setWorkflowInboxTaskObid(String workflowInboxTaskObid){
        this.workflowInboxTaskObid = workflowInboxTaskObid;
    }

    
    /**
     * 
     * 
     * @return the assigneeNames
     */
    public String getAssigneeNames(){
        return assigneeNames;
    }


    
    /**
     * 
     * 
     * @param assigneeId the assigneeId to set
     */
    public void setAssigneeNames(String assigneeNames){
        this.assigneeNames = assigneeNames;
    }
    

    
    /**
     * 
     * 
     * @return the availableToRevoke
     */
    public Boolean getAvailableToRevoke(){
        return availableToRevoke;
    }



    
    /**
     * 
     * 
     * @param availableToRevoke the availableToRevoke to set
     */
    public void setAvailableToRevoke(Boolean availableToRevoke){
        this.availableToRevoke = availableToRevoke;
    }
    

    /**
     * 
     * 
     * @return the processId
     */
    public String getProcessId(){
        return processId;
    }



    /**
     * 
     * 
     * @param processId the processId to set
     */
    public void setProcessId(String processId){
        this.processId = processId;
    }



    /**
     * 
     * 
     * @return the mode
     */
    public String getMode(){
        return mode;
    }



    /**
     * 
     * 
     * @param mode the mode to set
     */
    public void setMode(String mode){
        this.mode = mode;
    }



    /**
     * 
     * 
     * @return the changeName
     */
    public String getChangeName(){
        return changeName;
    }



    /**
     * 
     * 
     * @param changeName the changeName to set
     */
    public void setChangeName(String changeName){
        this.changeName = changeName;
    }



    /**
     * 
     * 
     * @return the resultCnt
     */
    public String getResultCnt(){
        return resultCnt;
    }



    /**
     * 
     * 
     * @param resultCnt the resultCnt to set
     */
    public void setResultCnt(String resultCnt){
        this.resultCnt = resultCnt;
    }
    
    public void setActualCompletionDate(String actualCompletionDate) throws ParseException { StringBuffer format = new StringBuffer("yyyy-MM-dd"); if(actualCompletionDate.length() == 19){ format.append(" HH:mm:ss"); }else if(actualCompletionDate.length() == 16){ format.append(" HH:mm"); } SimpleDateFormat transFormat = new SimpleDateFormat(format.toString()); this.actualCompletionDate= transFormat.parse(actualCompletionDate);}

    
    /**
     * 
     * 
     * @return the mobileApproval
     */
    public String getMobileApproval(){
        return mobileApproval;
    }
    
    /**
     * 
     * 
     * @param mobileApproval the mobileApproval to set
     */
    public void setMobileApproval(String mobileApproval){
        this.mobileApproval = mobileApproval;
    }
    
    /**
     * 
     * 
     * @return the inboxTaskState
     */
    public String getInboxTaskState(){
        return inboxTaskState;
    }

    /**
     * 
     * 
     * @param inboxTaskState the inboxTaskState to set
     */
    public void setInboxTaskState(String inboxTaskState){
        this.inboxTaskState = inboxTaskState;
    }
    
    
    
    /**
     * 
     * 
     * @return the routePurpose
     */
    public String getRoutePurpose(){
        return routePurpose;
    }


    
    /**
     * 
     * 
     * @param routePurpose the routePurpose to set
     */
    public void setRoutePurpose(String routePurpose){
        this.routePurpose = routePurpose;
    }
    
    /**
     * 
     * 
     * @return the notifyEmail
     */
    public Boolean getNotifyEmail(){
        return notifyEmail;
    }
    
    /**
     * 
     * 
     * @param notifyEmail the notifyEmail to set
     */
    public void setNotifyEmail(Boolean notifyEmail){
        this.notifyEmail = notifyEmail;
    }
    /**
     * 
     * 
     * @return the processRole
     */
    public String getProcessRole(){
        return processRole;
    }
    
    /**
     * 
     * 
     * @param processRole the processRole to set
     */
    public void setProcessRole(String processRole){
        this.processRole = processRole;
    }
    
    /**
     * 
     * 
     * @return the maintainInboxTask
     */
    public Boolean getMaintainInboxTask(){
        return maintainInboxTask;
    }
    
    /**
     * 
     * 
     * @param maintainInboxTask the maintainInboxTask to set
     */
    public void setMaintainInboxTask(Boolean maintainInboxTask){
        this.maintainInboxTask = maintainInboxTask;
    }
    
    /**
     * 
     * 
     * @return the outData
     */
    public HashMap<String, Object> getOutData(){
        return outData;
    }

    /**
     * 
     * 
     * @param outData the outData to set
     */
    public void setOutData(HashMap<String, Object> outData){
        this.outData = outData;
    }
    
    /**
     * 
     * 
     * @return the originTaskOwner
     */
    public String getOriginTaskOwner(){
        return originTaskOwner;
    }
    
    /**
     * 
     * 
     * @param originTaskOwner the originTaskOwner to set
     */
    public void setOriginTaskOwner(String originTaskOwner){
        this.originTaskOwner = originTaskOwner;
    }
    
    /**
     * 
     * 
     * @return the taskOwner
     */
    public String getTaskOwner(){
        return taskOwner;
    }
    
    /**
     * 
     * 
     * @param taskOwner the taskOwner to set
     */
    public void setTaskOwner(String taskOwner){
        this.taskOwner = taskOwner;
    }
    /**
     * 
     * 
     * @return the checkFilesExist
     */
    public Boolean getCheckFilesExist(){
        return checkFilesExist;
    }
    /**
     * 
     * 
     * @param checkFilesExist the checkFilesExist to set
     */
    public void setCheckFilesExist(Boolean checkFilesExist){
        this.checkFilesExist = checkFilesExist;
    }
    
    /**
     * 
     * 
     * @return the assigneeEmail
     */
    public String getAssigneeEmail(){
        return assigneeEmailAddress;
    }
    
    /**
     * 
     * 
     * @param assigneeEmail the assigneeEmail to set
     */
    public void setAssigneeEmailAddress(String assigneeEmailAddress){
        this.assigneeEmailAddress = assigneeEmailAddress;
    }
    
    /**
     * 
     * 
     * @return the inboxTaskType
     */
    public String getInboxTaskType(){
        return inboxTaskType;
    }
    
    /**
     * 
     * 
     * @param inboxTaskType the inboxTaskType to set
     */
    public void setInboxTaskType(String inboxTaskType){
        this.inboxTaskType = inboxTaskType;
    }

    /**
     * 
     * 
     * @return the selfReject
     */
    public Boolean getSelfReject(){
        return selfReject;
    }
    
    /**
     * 
     * 
     * @param selfReject the selfReject to set
     */
    public void setSelfReject(Boolean selfReject){
        this.selfReject = selfReject;
    }
    
    /**
     * 
     * 
     * @return the signal
     */
    public String getSignal(){
        return signal;
    }
    
    /**
     * 
     * 
     * @param signal the signal to set
     */
    public void setSignal(String signal){
        this.signal = signal;
    }

    /**
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "ApprovalVO [workflowRouteObid=" + workflowRouteObid + ", workflowStepNodeUserObid="
                + workflowStepNodeUserObid + ", workflowInboxTaskObid=" + workflowInboxTaskObid + ", state=" + state
                + ", step=" + step + ", sequence=" + sequence + ", titles=" + titles + ", parallelNodeProcessionRule="
                + parallelNodeProcessionRule + ", isEssential=" + isEssential + ", r=" + r + ", assignee=" + assignee
                + ", assigneeNames=" + assigneeNames + ", assigneeObid=" + assigneeObid + ", action=" + action
                + ", userStatus=" + userStatus + ", reviewApproveComments=" + reviewApproveComments
                + ", actualCompletionDate=" + actualCompletionDate + ", department=" + department + ", dueDate="
                + dueDate + ", actionCommnets=" + actionCommnets + ", etc=" + etc + ", comments=" + comments
                + ", routeStateSequence=" + routeStateSequence + ", approvalStatus=" + approvalStatus + ", dummyStep="
                + dummyStep + ", routeAction=" + routeAction + ", routeInstructions=" + routeInstructions + ", obid="
                + obid + ", essential=" + essential + ", availableToApproval=" + availableToApproval
                + ", sendBackToList=" + sendBackToList + ", stateOfWorkflowStep=" + stateOfWorkflowStep
                + ", availableToRevoke=" + availableToRevoke + ", mode=" + mode + ", processId=" + processId
                + ", changeName=" + changeName + ", resultCnt=" + resultCnt + ", mobileApproval=" + mobileApproval
                + ", inboxTaskState=" + inboxTaskState + ", routePurpose=" + routePurpose + ", notifyEmail="
                + notifyEmail + ", processRole=" + processRole + ", maintainInboxTask=" + maintainInboxTask
                + ", originTaskOwner=" + originTaskOwner + ", taskOwner=" + taskOwner + ", outData=" + outData
                + ", checkFilesExist=" + checkFilesExist + ", assigneeEmailAddress=" + assigneeEmailAddress
                + ", inboxTaskType=" + inboxTaskType + ", selfReject=" + selfReject + ", signal=" + signal + "]";
    }
}
