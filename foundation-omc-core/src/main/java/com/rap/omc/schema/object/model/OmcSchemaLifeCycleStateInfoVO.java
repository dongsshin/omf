/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcLifeCycleStateInfoVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 3.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import com.rap.omc.api.object.model.BaseModel;


/**
 * <pre>
 * Class : OmcLifeCycleStateInfoVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaLifeCycleStateInfoVO extends BaseModel {
    private int     sequences                          ;
    private String  changeComments                     ;
    private String  lifeCycle                          ;
    private String  states                             ;
    private String  routeCompleteAction                ;
    private String  defaultRoutePurpose                ;
    private String  routeAutoStartOnReject             ;
    private String  routeHowToOnReject                 ;
    private String  inboxTaskAutoComplete              ;
    private int     dateOffsetDay                      ;
    private String  parallelProcessRule                ;
    
    public int getSequences(){
        return sequences;
    }
    
    public String getChangeComments(){
        return changeComments;
    }
    
    public String getLifeCycle(){
        return lifeCycle;
    }
    
    public String getStates(){
        return states;
    }
    
    public String getRouteCompleteAction(){
        return routeCompleteAction;
    }
    
    public String getDefaultRoutePurpose(){
        return defaultRoutePurpose;
    }
    
    public String getRouteAutoStartOnReject(){
        return routeAutoStartOnReject;
    }
    
    public String getRouteHowToOnReject(){
        return routeHowToOnReject;
    }
    
    public String getInboxTaskAutoComplete(){
        return inboxTaskAutoComplete;
    }
    
    public int getDateOffsetDay(){
        return dateOffsetDay;
    }
    
    public String getParallelProcessRule(){
        return parallelProcessRule;
    }
    
    public void setSequences(int sequences){
        this.sequences = sequences;
    }
    
    public void setChangeComments(String changeComments){
        this.changeComments = changeComments;
    }
    
    public void setLifeCycle(String lifeCycle){
        this.lifeCycle = lifeCycle;
    }
    
    public void setStates(String states){
        this.states = states;
    }
    
    public void setRouteCompleteAction(String routeCompleteAction){
        this.routeCompleteAction = routeCompleteAction;
    }
    
    public void setDefaultRoutePurpose(String defaultRoutePurpose){
        this.defaultRoutePurpose = defaultRoutePurpose;
    }
    
    public void setRouteAutoStartOnReject(String routeAutoStartOnReject){
        this.routeAutoStartOnReject = routeAutoStartOnReject;
    }
    
    public void setRouteHowToOnReject(String routeHowToOnReject){
        this.routeHowToOnReject = routeHowToOnReject;
    }
    
    public void setInboxTaskAutoComplete(String inboxTaskAutoComplete){
        this.inboxTaskAutoComplete = inboxTaskAutoComplete;
    }
    
    public void setDateOffsetDay(int dateOffsetDay){
        this.dateOffsetDay = dateOffsetDay;
    }
    
    public void setParallelProcessRule(String parallelProcessRule){
        this.parallelProcessRule = parallelProcessRule;
    }
    
}
