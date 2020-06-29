/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessObjectRoot.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 23. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.model;

/**
 * <pre>
 * Class : BusinessObjectRoot
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@SuppressWarnings("serial")
public class BusinessObjectRootVO extends ObjectRootVO {

    private String names;

    private String titles;

    private String descriptions;

    private String lifeCycle;

    private String states;
    
    private String branchTo = "None";

    // // biz 처리를 위한 추가 attribute 추가
    private String actType = "";

    
    public String getBranchTo(){
        return branchTo;
    }

    
    public void setBranchTo(String branchTo){
        this.branchTo = branchTo;
    }

    /**
     * 
     * 
     * @return the names
     */
    public String getNames(){
        return names;
    }

    /**
     * 
     * 
     * @param names the names to set
     */
    public void setNames(String names){
        this.names = names;
    }

    /**
     * 
     * 
     * @return the titles
     */
    public String getTitles(){
        return titles;
    }

    /**
     * 
     * 
     * @param titles the titles to set
     */
    public void setTitles(String titles){
        this.titles = titles;
    }

    /**
     * 
     * 
     * @return the descriptions
     */
    public String getDescriptions(){
        return descriptions;
    }

    /**
     * 
     * 
     * @param descriptions the descriptions to set
     */
    public void setDescriptions(String descriptions){
        this.descriptions = descriptions;
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
     * @return the actType
     */
    public String getActType(){
        return actType;
    }

    /**
     * 
     * 
     * @param actType the actType to set
     */
    public void setActType(String actType){
        this.actType = actType;
    }

}
