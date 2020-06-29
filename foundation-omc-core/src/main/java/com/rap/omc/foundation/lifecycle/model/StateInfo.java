/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : StateInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 11.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.model;

import java.util.List;

import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;



/**
 * <pre>
 * Class : StateInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class StateInfo {
    private String obid;
    private long   flags;
    private String stateName;
    private String lifeCycleObid;
    private String lifeCycleName;
    private int    sequence;
    private String userInputFlag;
    private String userInputMandantory;
    private String routeCompleteAction;
    private String defaultRoutePurpose;
    private String routeAutoStartOnReject;
    private String routeHowToOnReject;
    private String inboxTaskAutoComplete;
    private String processRule;
    private int    dateOffsetDay = 7;
    private List<BranchInfo> branchList;
    
    
    
    public List<BranchInfo> getBranchList(){
        return branchList;
    }


    
    public void setBranchList(List<BranchInfo> branchList){
        this.branchList = branchList;
    }


    public String getUserInputFlag(){
        return userInputFlag;
    }

    
    public void setUserInputFlag(String userInputFlag){
        this.userInputFlag = userInputFlag;
    }

    
    public String getUserInputMandantory(){
        return userInputMandantory;
    }

    
    public void setUserInputMandantory(String userInputMandantory){
        this.userInputMandantory = userInputMandantory;
    }

    
    public String getRouteCompleteAction(){
        return routeCompleteAction;
    }

    
    public void setRouteCompleteAction(String routeCompleteAction){
        this.routeCompleteAction = routeCompleteAction;
    }

    
    public String getDefaultRoutePurpose(){
        return defaultRoutePurpose;
    }

    
    public void setDefaultRoutePurpose(String defaultRoutePurpose){
        this.defaultRoutePurpose = defaultRoutePurpose;
    }

    
    public String getRouteAutoStartOnReject(){
        return routeAutoStartOnReject;
    }

    
    public void setRouteAutoStartOnReject(String routeAutoStartOnReject){
        this.routeAutoStartOnReject = routeAutoStartOnReject;
    }

    
    public String getRouteHowToOnReject(){
        return routeHowToOnReject;
    }

    
    public void setRouteHowToOnReject(String routeHowToOnReject){
        this.routeHowToOnReject = routeHowToOnReject;
    }

    
    public String getInboxTaskAutoComplete(){
        return inboxTaskAutoComplete;
    }

    
    public void setInboxTaskAutoComplete(String inboxTaskAutoComplete){
        this.inboxTaskAutoComplete = inboxTaskAutoComplete;
    }

    
    public String getProcessRule(){
        return processRule;
    }

    
    public void setProcessRule(String processRule){
        this.processRule = processRule;
    }

    
    public int getDateOffsetDay(){
        return dateOffsetDay;
    }

    
    public void setDateOffsetDay(int dateOffsetDay){
        this.dateOffsetDay = dateOffsetDay;
    }

    public String getObid(){
        return obid;
    }
    
    public long getFlags(){
        return flags;
    }
    
    public String getStateName(){
        return stateName;
    }
    
    public String getLifeCycleObid(){
        return lifeCycleObid;
    }
    
    public String getLifeCycleName(){
        return lifeCycleName;
    }
    
    public int getSequence(){
        return sequence;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public void setFlags(long flags){
        this.flags = flags;
        if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_UserInput)) {
            userInputFlag = "Y";
        }else{
            userInputFlag = "N";
        }
        if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_UserInputMust)) {
            userInputMandantory = "Y";
        }else{
            userInputMandantory = "N";
        }  
        if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_RCA_Promote)) {
            routeCompleteAction = "Promote Connected Object";
        }else if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_RCA_RONotify)){
            routeCompleteAction = "Notify Route Owner";
        }else if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_RCA_OONotify)){
            routeCompleteAction = "Notify Object Owner";
        }else{
            routeCompleteAction = "None";
        }
        if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_DRP_Standard)) {
            defaultRoutePurpose = "Standard";
        }else if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_DRP_Dist)){
            defaultRoutePurpose = "Distribution";
        }else if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_DRP_Approval)){
            defaultRoutePurpose = "Approval";
        }else if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_DRP_Review)){
            defaultRoutePurpose = "Review";
        }else if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_DRP_Confirm)){
            defaultRoutePurpose = "Confirmation";
        }else{
            defaultRoutePurpose = "None";
        }
        if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_RAS_True)) {
            routeAutoStartOnReject = "TRUE";
        }else if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_RAS_False)){
            routeAutoStartOnReject = "FALSE";
        }else{  
            routeAutoStartOnReject = "TRUE";
        }  
        if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_AS_Immediate)) {
            routeHowToOnReject = "Immediate";
        }else if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_AS_Defererred)){
            routeHowToOnReject = "Defererred";
        }else{  
            routeHowToOnReject = "Immediate";
        }  
        if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_RAC_True)) {
            inboxTaskAutoComplete = "TRUE";
        }else if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_RAC_False)){
            inboxTaskAutoComplete = "FALSE";
        }else{  
            inboxTaskAutoComplete = "TRUE";
        }  
        if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_PPR_All)) {
            processRule = "All";
        }else if(Bit.isInclude(flags, OmcSystemConstants.SYSSTATE_FLAG_PPR_Any)){
            processRule = "Any";
        }else{  
            processRule = "All";
        }  
    }
    public void setStateName(String stateName){
        this.stateName = stateName;
    }
    
    public void setLifeCycleObid(String lifeCycleObid){
        this.lifeCycleObid = lifeCycleObid;
    }
    
    public void setLifeCycleName(String lifeCycleName){
        this.lifeCycleName = lifeCycleName;
    }
    
    public void setSequence(int sequence){
        this.sequence = sequence;
    }
    
}
