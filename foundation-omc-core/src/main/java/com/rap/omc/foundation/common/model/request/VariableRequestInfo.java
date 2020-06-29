/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ValidatePolicyRequestInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 26. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.common.model.request;

/**
 * <pre>
 * Class : VariableRequestInfo
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class VariableRequestInfo {

    private String className;

    private String dynamicAttributeGroup;

    /**
     * 
     */
    public VariableRequestInfo() {

    }

    /**
     * 
     * 
     * @return the className
     */
    public String getClassName(){
        return className;
    }

    /**
     * 
     * 
     * @param className the className to set
     */
    public void setClassName(String className){
        this.className = className;
    }

    /**
     * 
     * 
     * @return the dynamicAttributeGroup
     */
    public String getDynamicAttributeGroup(){
        return dynamicAttributeGroup;
    }

    /**
     * 
     * 
     * @param dynamicAttributeGroup the dynamicAttributeGroup to set
     */
    public void setDynamicAttributeGroup(String dynamicAttributeGroup){
        this.dynamicAttributeGroup = dynamicAttributeGroup;
    }

}
