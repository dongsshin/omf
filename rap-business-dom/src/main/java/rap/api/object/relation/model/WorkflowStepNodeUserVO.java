/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowStepNodeUserVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class WorkflowStepNodeUserVO extends BusinessRelationObjectVO {
    private String        routeAction                                       ;
    private String        routeInstructions                                 ;
    private String        comments                                          ;
    private String        approvalStatus                                     = "None";
    private Date          scheduledCompletionDate                           ;
    private Date          actualCompletionDate                              ;
    private String        approversResponsibility                            = "Engineering";
    private String        taskRequirement                                    = "Optional";
    private String        routeNodeObid                                     ;
    private Boolean       reviewTask                                         = (Boolean)false;
    private String        reviewersComments                                 ;
    private Boolean       reviewCommentsNeeded                               = (Boolean)false;
    private Integer       dueDateOffset                                      = 2;
    private String        dateOffsetFrom                                     = "Task Create Date";
    private Date          assigneeSetDueDate                                ;
    private Boolean       allowDelegation                                    = (Boolean)true;
    private Boolean       isEssential                                        = (Boolean)false;
    private String        parallelNodeProcessionRule                         = "All";
    private String        titles                                            ;
    private Integer       sequences                                         ;
    private String        actionComments                                    ;
    private Boolean       notifyEmail                                        = (Boolean)true;
    private String        processRole                                       ;
    private Boolean       selfReject                                         = (Boolean)false;


    public void    setRouteAction(String routeAction){
        this.routeAction = routeAction;
    }
    public void    setRouteInstructions(String routeInstructions){
        this.routeInstructions = routeInstructions;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public void    setApprovalStatus(String approvalStatus){
        this.approvalStatus = approvalStatus;
    }
    public void    setScheduledCompletionDate(Date scheduledCompletionDate){
        this.scheduledCompletionDate = scheduledCompletionDate;
    }
    public void    setScheduledCompletionDate(String    scheduledCompletionDate){
        this.scheduledCompletionDate = this.omcConvertStr2Date(scheduledCompletionDate);
    }
    public void    setActualCompletionDate(Date actualCompletionDate){
        this.actualCompletionDate = actualCompletionDate;
    }
    public void    setActualCompletionDate(String    actualCompletionDate){
        this.actualCompletionDate = this.omcConvertStr2Date(actualCompletionDate);
    }
    public void    setApproversResponsibility(String approversResponsibility){
        this.approversResponsibility = approversResponsibility;
    }
    public void    setTaskRequirement(String taskRequirement){
        this.taskRequirement = taskRequirement;
    }
    public void    setRouteNodeObid(String routeNodeObid){
        this.routeNodeObid = routeNodeObid;
    }
    public void    setReviewTask(Boolean reviewTask){
        this.reviewTask = reviewTask;
    }
    public void    setReviewersComments(String reviewersComments){
        this.reviewersComments = reviewersComments;
    }
    public void    setReviewCommentsNeeded(Boolean reviewCommentsNeeded){
        this.reviewCommentsNeeded = reviewCommentsNeeded;
    }
    public void    setDueDateOffset(Integer dueDateOffset){
        this.dueDateOffset = dueDateOffset;
    }
    public void    setDateOffsetFrom(String dateOffsetFrom){
        this.dateOffsetFrom = dateOffsetFrom;
    }
    public void    setAssigneeSetDueDate(Date assigneeSetDueDate){
        this.assigneeSetDueDate = assigneeSetDueDate;
    }
    public void    setAssigneeSetDueDate(String    assigneeSetDueDate){
        this.assigneeSetDueDate = this.omcConvertStr2Date(assigneeSetDueDate);
    }
    public void    setAllowDelegation(Boolean allowDelegation){
        this.allowDelegation = allowDelegation;
    }
    public void    setIsEssential(Boolean isEssential){
        this.isEssential = isEssential;
    }
    public void    setParallelNodeProcessionRule(String parallelNodeProcessionRule){
        this.parallelNodeProcessionRule = parallelNodeProcessionRule;
    }
    public void    setTitles(String titles){
        this.titles = titles;
    }
    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setActionComments(String actionComments){
        this.actionComments = actionComments;
    }
    public void    setNotifyEmail(Boolean notifyEmail){
        this.notifyEmail = notifyEmail;
    }
    public void    setProcessRole(String processRole){
        this.processRole = processRole;
    }
    public void    setSelfReject(Boolean selfReject){
        this.selfReject = selfReject;
    }
    public String getRouteAction(){
        return routeAction;
    }
    public String getRouteInstructions(){
        return routeInstructions;
    }
    public String getComments(){
        return comments;
    }
    public String getApprovalStatus(){
        return approvalStatus;
    }
    public Date getScheduledCompletionDate(){
        return scheduledCompletionDate;
    }
    public Date getActualCompletionDate(){
        return actualCompletionDate;
    }
    public String getApproversResponsibility(){
        return approversResponsibility;
    }
    public String getTaskRequirement(){
        return taskRequirement;
    }
    public String getRouteNodeObid(){
        return routeNodeObid;
    }
    public Boolean getReviewTask(){
        return reviewTask;
    }
    public String getReviewersComments(){
        return reviewersComments;
    }
    public Boolean getReviewCommentsNeeded(){
        return reviewCommentsNeeded;
    }
    public Integer getDueDateOffset(){
        return dueDateOffset;
    }
    public String getDateOffsetFrom(){
        return dateOffsetFrom;
    }
    public Date getAssigneeSetDueDate(){
        return assigneeSetDueDate;
    }
    public Boolean getAllowDelegation(){
        return allowDelegation;
    }
    public Boolean getIsEssential(){
        return isEssential;
    }
    public String getParallelNodeProcessionRule(){
        return parallelNodeProcessionRule;
    }
    public String getTitles(){
        return titles;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getActionComments(){
        return actionComments;
    }
    public Boolean getNotifyEmail(){
        return notifyEmail;
    }
    public String getProcessRole(){
        return processRole;
    }
    public Boolean getSelfReject(){
        return selfReject;
    }
}

