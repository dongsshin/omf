/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowRouteVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class WorkflowRouteVO extends BusinessObjectMasterVO {
    private String        routeCompletionAction                              = "None";
    private Boolean       subRouteVisibility                                 = (Boolean)false;
    private String        routeBasePurpose                                   = "Standard";
    private Boolean       restartUpOnTaskRejection                           = (Boolean)true;
    private String        autoStopOnRejection                                = "Immediate";
    private String        organizations                                     ;
    private String        routeStatus                                        = "Not Started";
    private String        processTimestamp                                  ;
    private String        activityUrl                                        = "None";


    public void    setRouteCompletionAction(String routeCompletionAction){
        this.routeCompletionAction = routeCompletionAction;
    }
    public void    setSubRouteVisibility(Boolean subRouteVisibility){
        this.subRouteVisibility = subRouteVisibility;
    }
    public void    setRouteBasePurpose(String routeBasePurpose){
        this.routeBasePurpose = routeBasePurpose;
    }
    public void    setRestartUpOnTaskRejection(Boolean restartUpOnTaskRejection){
        this.restartUpOnTaskRejection = restartUpOnTaskRejection;
    }
    public void    setAutoStopOnRejection(String autoStopOnRejection){
        this.autoStopOnRejection = autoStopOnRejection;
    }
    public void    setOrganizations(String organizations){
        this.organizations = organizations;
    }
    public void    setRouteStatus(String routeStatus){
        this.routeStatus = routeStatus;
    }
    public void    setProcessTimestamp(String processTimestamp){
        this.processTimestamp = processTimestamp;
    }
    public void    setActivityUrl(String activityUrl){
        this.activityUrl = activityUrl;
    }
    public String getRouteCompletionAction(){
        return routeCompletionAction;
    }
    public Boolean getSubRouteVisibility(){
        return subRouteVisibility;
    }
    public String getRouteBasePurpose(){
        return routeBasePurpose;
    }
    public Boolean getRestartUpOnTaskRejection(){
        return restartUpOnTaskRejection;
    }
    public String getAutoStopOnRejection(){
        return autoStopOnRejection;
    }
    public String getOrganizations(){
        return organizations;
    }
    public String getRouteStatus(){
        return routeStatus;
    }
    public String getProcessTimestamp(){
        return processTimestamp;
    }
    public String getActivityUrl(){
        return activityUrl;
    }
}

