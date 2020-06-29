/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSActivityForInboxVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 7.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import java.util.List;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;


/**
 * <pre>
 * Class : WBSActivityForInboxVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class WBSActivityWorkflowVO {
    private BusinessObjectRoot       wbsActivity     ;
    private List<? extends BusinessObjectRootVO> projectMemberList;
    private List<WBSActivityInboxTaskVO> wbsActivityInboxTaskVOList;
    private String        routePurpose  ;
    private String        routeLifeCycle;
    private String        routeLifeState;
    
    private String        activityURL   ;
    
    public String getActivityURL(){
        return activityURL;
    }

    
    public void setActivityURL(String activityURL){
        this.activityURL = activityURL;
    }
    private String        routeCompletionAction                              = "None";
    private Boolean       subRouteVisibility                                 = (Boolean)false;
    private String        routeBasePurpose                                   = "Standard";
    private Boolean       restartUpOnTaskRejection                           = (Boolean)true;
    private String        autoStopOnRejection                                = "Immediate";
    private String        organizations                                     ;
    private String        routeStatus                                        = "Started";
    private String        processTimestamp                                  ;
    
    public BusinessObjectRoot getWbsActivity(){
        return wbsActivity;
    }
    
    public void setWbsActivity(BusinessObjectRoot wbsActivity){
        this.wbsActivity = wbsActivity;
    }
    
    public List<? extends BusinessObjectRootVO> getProjectMemberList(){
        return projectMemberList;
    }
    
    public void setProjectMemberList(List<BusinessObjectRootVO> projectMemberList){
        this.projectMemberList = projectMemberList;
    }
    
    public String getRoutePurpose(){
        return routePurpose;
    }
    
    public void setRoutePurpose(String routePurpose){
        this.routePurpose = routePurpose;
    }
    
    public String getRouteLifeCycle(){
        return routeLifeCycle;
    }
    
    public void setRouteLifeCycle(String routeLifeCycle){
        this.routeLifeCycle = routeLifeCycle;
    }
    
    public String getRouteLifeState(){
        return routeLifeState;
    }
    
    public void setRouteLifeState(String routeLifeState){
        this.routeLifeState = routeLifeState;
    }
    
    public String getRouteCompletionAction(){
        return routeCompletionAction;
    }
    
    public void setRouteCompletionAction(String routeCompletionAction){
        this.routeCompletionAction = routeCompletionAction;
    }
    
    public Boolean getSubRouteVisibility(){
        return subRouteVisibility;
    }
    
    public void setSubRouteVisibility(Boolean subRouteVisibility){
        this.subRouteVisibility = subRouteVisibility;
    }
    
    public String getRouteBasePurpose(){
        return routeBasePurpose;
    }
    
    public void setRouteBasePurpose(String routeBasePurpose){
        this.routeBasePurpose = routeBasePurpose;
    }
    
    public Boolean getRestartUpOnTaskRejection(){
        return restartUpOnTaskRejection;
    }
    
    public void setRestartUpOnTaskRejection(Boolean restartUpOnTaskRejection){
        this.restartUpOnTaskRejection = restartUpOnTaskRejection;
    }
    
    public String getAutoStopOnRejection(){
        return autoStopOnRejection;
    }
    
    public void setAutoStopOnRejection(String autoStopOnRejection){
        this.autoStopOnRejection = autoStopOnRejection;
    }
    
    public String getOrganizations(){
        return organizations;
    }
    
    public void setOrganizations(String organizations){
        this.organizations = organizations;
    }
    
    public String getRouteStatus(){
        return routeStatus;
    }
    public void setRouteStatus(String routeStatus){
        this.routeStatus = routeStatus;
    }
    
    public String getProcessTimestamp(){
        return processTimestamp;
    }
    
    public void setProcessTimestamp(String processTimestamp){
        this.processTimestamp = processTimestamp;
    }
    
    public List<WBSActivityInboxTaskVO> getWbsActivityInboxTaskVOList(){
        return wbsActivityInboxTaskVOList;
    }

    public void setWbsActivityInboxTaskVOList(List<WBSActivityInboxTaskVO> wbsActivityInboxTaskVOList){
        this.wbsActivityInboxTaskVOList = wbsActivityInboxTaskVOList;
    }

    public WBSActivityWorkflowVO(BusinessObjectRoot wbsActivityVO, List<? extends BusinessObjectRootVO> projectMemberList, List<WBSActivityInboxTaskVO> wbsActivityInboxTaskVOList, String routePurpose, String routeLifeCycle, String routeLifeState,String activityURL) {
        super();
        this.wbsActivity     = wbsActivityVO;
        this.projectMemberList = projectMemberList;
        this.wbsActivityInboxTaskVOList = wbsActivityInboxTaskVOList;
        this.routePurpose      = routePurpose;
        this.routeLifeCycle    = routeLifeCycle;
        this.routeLifeState    = routeLifeState;
        this.activityURL       = activityURL;
    }
}
