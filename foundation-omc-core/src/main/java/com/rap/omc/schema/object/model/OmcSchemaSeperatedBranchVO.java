/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSeperatedBranch.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 28.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import com.rap.omc.api.object.model.BaseModel;
/**
 * <pre>
 * Class : OmcSchemaSeperatedBranch
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSeperatedBranchVO extends BaseModel{
    private String  lifeCycle                          ;
    private long    flags                              ;
    private String  names                              ;
    private long    kinds                              ;
    private String  states                             ;
    private String  toObject                           ;
    private String  conditionRule                      ;
    
    /**
     * @param lifeCycle
     * @param flags
     * @param names
     * @param kinds
     * @param state
     * @param toObject
     * @param conditionRule
     */
    public OmcSchemaSeperatedBranchVO(String lifeCycle, long flags, String names, long kinds, String states,
            String toObject, String conditionRule) {
        super();
        this.lifeCycle = lifeCycle;
        this.flags = flags;
        this.names = names;
        this.kinds = kinds;
        this.states = states;
        this.toObject = toObject;
        this.conditionRule = conditionRule;
    }

    public String getLifeCycle(){
        return lifeCycle;
    }
    
    public long getFlags(){
        return flags;
    }
    
    public String getNames(){
        return names;
    }
    
    public long getKinds(){
        return kinds;
    }
    
    public String getStates(){
        return states;
    }
    
    public String getToObject(){
        return toObject;
    }
    
    public String getConditionRule(){
        return conditionRule;
    }
    
    public void setLifeCycle(String lifeCycle){
        this.lifeCycle = lifeCycle;
    }
    
    public void setFlags(long flags){
        this.flags = flags;
    }
    
    public void setNames(String names){
        this.names = names;
    }
    
    public void setKinds(long kinds){
        this.kinds = kinds;
    }
    
    public void setState(String states){
        this.states = states;
    }
    
    public void setToObject(String toObject){
        this.toObject = toObject;
    }
    
    public void setConditionRule(String conditionRule){
        this.conditionRule = conditionRule;
    }
    
}
