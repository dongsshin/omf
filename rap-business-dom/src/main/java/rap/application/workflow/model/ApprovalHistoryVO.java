/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ApprovalHistoryVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 4. 24.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import com.rap.omc.api.object.model.BaseModel;




/**
 * <pre>
 * Class : ApprovalHistoryVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class ApprovalHistoryVO extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String site;
    private String state;
    private String step;
    private String assigneeObid;
    
    private String assignee;
    private String action;
    private String comments;
    private String routeInstructions;
    private String actionComments;
    private String actualCompletionDate;
    private String approvalStatus;
    private String routeAction;
    private String taskName;
    private String rev;
    private String fileExistYn;
    private String taskObid;
    private String approverInfo;
    private String processRole;

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
     * @return the taskObid
     */
    public String getTaskObid(){
        return taskObid;
    }
    
    /**
     * 
     * 
     * @param taskObid the taskObid to set
     */
    public void setTaskObid(String taskObid){
        this.taskObid = taskObid;
    }
    
    
    /**
     * 
     * 
     * @return the fileExistYn
     */
    public String getFileExistYn(){
        return fileExistYn;
    }


    
    /**
     * 
     * 
     * @param fileExistYn the fileExistYn to set
     */
    public void setFileExistYn(String fileExistYn){
        this.fileExistYn = fileExistYn;
    }


    /**
     * 
     * 
     * @return the taskName
     */
    public String getTaskName(){
        return taskName;
    }

    
    /**
     * 
     * 
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    /**
     * 
     * 
     * @return the site
     */
    public String getSite(){
        return site;
    }
    
    /**
     * 
     * 
     * @param site the site to set
     */
    public void setSite(String site){
        this.site = site;
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
    public String getStep(){
        return step;
    }
    
    /**
     * 
     * 
     * @param step the step to set
     */
    public void setStep(String step){
        this.step = step;
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
     * @return the actionComments
     */
    public String getActionComments(){
        return actionComments;
    }
    
    /**
     * 
     * 
     * @param actionComments the actionComments to set
     */
    public void setActionComments(String actionComments){
        this.actionComments = actionComments;
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
     * @return the rev
     */
    public String getRev(){
        return rev;
    }
    
    /**
     * 
     * 
     * @param rev the rev to set
     */
    public void setRev(String rev){
        this.rev = rev;
    }
    
    /**
     * 
     * 
     * @return the actualCompletionDate
     */
    public String getActualCompletionDate(){
        return actualCompletionDate;
    }


    
    /**
     * 
     * 
     * @param actualCompletionDate the actualCompletionDate to set
     */
    public void setActualCompletionDate(String actualCompletionDate){
        this.actualCompletionDate = actualCompletionDate;
    }


    /**
     * 
     * 
     * @return the approverInfo
     */
    public String getProcessRole(){
        return this.processRole;
    }
    
    /**
     * 
     * 
     * @param approverInfo the approverInfo to set
     */
    public void setProcessRole(String processRole){
        this.processRole = processRole;
    }
}
