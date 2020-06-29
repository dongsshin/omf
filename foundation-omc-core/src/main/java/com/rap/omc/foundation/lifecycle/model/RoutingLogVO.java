/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : RoutingLogVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 5. 7.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.model;

import java.util.Date;


/**
 * <pre>
 * Class : RoutingLogVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class RoutingLogVO {
    private String obid;
    private String lifeCycle;
    private String fromStates;
    private String toStates;
    private String actionMethod;
    private String actionedBy;
    private Date   actioned;
    private String comments;
    private String creator;
    private Date   created;
    private String modifier;
    private Date   modified;
    
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
     * @return the lifeCycle
     */
    public String getLifeCycle(){
        return lifeCycle;
    }
    
    /**
     * 
     * 
     * @param lifeCycle the lifeCycle to set
     */
    public void setLifeCycle(String lifeCycle){
        this.lifeCycle = lifeCycle;
    }
    
    /**
     * 
     * 
     * @return the fromStates
     */
    public String getFromStates(){
        return fromStates;
    }
    
    /**
     * 
     * 
     * @param fromStates the fromStates to set
     */
    public void setFromStates(String fromStates){
        this.fromStates = fromStates;
    }
    
    /**
     * 
     * 
     * @return the toStates
     */
    public String getToStates(){
        return toStates;
    }
    
    /**
     * 
     * 
     * @param toStates the toStates to set
     */
    public void setToStates(String toStates){
        this.toStates = toStates;
    }
    
    /**
     * 
     * 
     * @return the actionMethod
     */
    public String getActionMethod(){
        return actionMethod;
    }
    
    /**
     * 
     * 
     * @param actionMethod the actionMethod to set
     */
    public void setActionMethod(String actionMethod){
        this.actionMethod = actionMethod;
    }
    
    /**
     * 
     * 
     * @return the actionedBy
     */
    public String getActionedBy(){
        return actionedBy;
    }
    
    /**
     * 
     * 
     * @param actionedBy the actionedBy to set
     */
    public void setActionedBy(String actionedBy){
        this.actionedBy = actionedBy;
    }
    
    /**
     * 
     * 
     * @return the actioned
     */
    public Date getActioned(){
        return actioned;
    }
    
    /**
     * 
     * 
     * @param actioned the actioned to set
     */
    public void setActioned(Date actioned){
        this.actioned = actioned;
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
     * @return the creator
     */
    public String getCreator(){
        return creator;
    }
    
    /**
     * 
     * 
     * @param creator the creator to set
     */
    public void setCreator(String creator){
        this.creator = creator;
    }
    
    /**
     * 
     * 
     * @return the created
     */
    public Date getCreated(){
        return created;
    }
    
    /**
     * 
     * 
     * @param created the created to set
     */
    public void setCreated(Date created){
        this.created = created;
    }
    
    /**
     * 
     * 
     * @return the modifier
     */
    public String getModifier(){
        return modifier;
    }
    
    /**
     * 
     * 
     * @param modifier the modifier to set
     */
    public void setModifier(String modifier){
        this.modifier = modifier;
    }
    
    /**
     * 
     * 
     * @return the modified
     */
    public Date getModified(){
        return modified;
    }
    
    /**
     * 
     * 
     * @param modified the modified to set
     */
    public void setModified(Date modified){
        this.modified = modified;
    }
    
    
}
