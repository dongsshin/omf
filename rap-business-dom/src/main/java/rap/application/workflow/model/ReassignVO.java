/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ReassignVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 5. 18.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.model;


/**
 * <pre>
 * Class : ReassignVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class ReassignVO {
    private String obid;    // businessObjectObid
    private String workflowRouteObid; // routeObid ==> State
    private String fromAssigneeObid;    // originalUserObid
    private String toAssigneeObid;      // targetUserObid
    private String workflowInboxTaskObid;
    private String workflowStepNodeUserObid;    // routeNodeObid : in inboxTask
    private String comments;
    
    
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
     * @return the fromAssigneeObid
     */
    public String getFromAssigneeObid(){
        return fromAssigneeObid;
    }
    
    /**
     * 
     * 
     * @param fromAssigneeObid the fromAssigneeObid to set
     */
    public void setFromAssigneeObid(String fromAssigneeObid){
        this.fromAssigneeObid = fromAssigneeObid;
    }
    
    /**
     * 
     * 
     * @return the toAssigneeObid
     */
    public String getToAssigneeObid(){
        return toAssigneeObid;
    }
    
    /**
     * 
     * 
     * @param toAssigneeObid the toAssigneeObid to set
     */
    public void setToAssigneeObid(String toAssigneeObid){
        this.toAssigneeObid = toAssigneeObid;
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
}
