/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BranchInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 4. 8. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.model;

import java.io.Serializable;

import com.rap.omc.util.NullUtil;


/**
 * <pre>
 * Class : BranchInfo
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class BranchInfo implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1953151872269230119L;

    private long flags;
    
    private long kinds;
    
    private String toObject;
        
    private String branchName;

    private String conditionExpression;

    private String states;

    private String statesName;
    
    public long getFlags(){
        return flags;
    }

    
    public void setFlags(long flags){
        this.flags = flags;
    }

    
    public long getKinds(){
        return kinds;
    }

    
    public void setKinds(long kinds){
        this.kinds = kinds;
    }

    
    public String getToObject(){
        return toObject;
    }

    
    public void setToObject(String toObject){
        this.toObject = toObject;
    }

    /**
     * 
     * 
     * @return the branchName
     */
    public String getBranchName(){
        return branchName;
    }

    /**
     * 
     * 
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName){
        this.branchName = branchName;
    }

    /**
     * 
     * 
     * @return the conditionExpression
     */
    public String getConditionExpression(){
        return conditionExpression;
    }

    /**
     * 
     * 
     * @param conditionExpression the conditionExpression to set
     */
    public void setConditionExpression(String conditionExpression){
        this.conditionExpression = conditionExpression;
    }

    /**
     * 
     * 
     * @return the states
     */
    public String getStates(){
        return states;
    }

    /**
     * 
     * 
     * @param states the states to set
     */
    public void setStates(String states){
        this.states = states;
    }

    /**
     * 
     * 
     * @return the statesName
     */
    public String getStatesName(){
        return statesName;
    }

    /**
     * 
     * 
     * @param statesName the statesName to set
     */
    public void setStatesName(String statesName){
        this.statesName = statesName;
    }
    public boolean equals(BranchInfo branhInfo){
        if(NullUtil.isNull(branhInfo)) return false;
        if(this.branchName.equals(branhInfo.getBranchName()) && this.getStatesName().equals(branhInfo.getStatesName())) return true;
        return false;
    }
    
}
