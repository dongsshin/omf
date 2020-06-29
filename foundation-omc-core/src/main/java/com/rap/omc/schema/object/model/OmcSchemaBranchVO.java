/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaBranchVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 28.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaBranchVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaBranchVO  extends OmcSchemaSysRootVO{
    private long    kinds                              ;
    private String  states                             ;
    private String  toObject                           ;
    private String  conditionRule                      ;
    
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
    
    public void setKinds(long kinds){
        this.kinds = kinds;
    }
    
    public void setStates(String states){
        this.states = states;
    }
    
    public void setToObject(String toObject){
        this.toObject = toObject;
    }
    
    public void setConditionRule(String conditionRule){
        this.conditionRule = conditionRule;
    }
    
}