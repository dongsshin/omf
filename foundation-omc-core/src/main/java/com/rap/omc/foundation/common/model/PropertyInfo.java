/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : VariableInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 3. 3. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.common.model;

/**
 * <pre>
 * Class : PropertyInfo
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class PropertyInfo {

    private String convertingFlag;

    private String propertyName;

    private String propertyValue;

    private String constantScope;

    /**
     * 
     * 
     * @return the convertingFlag
     */
    public String getConvertingFlag(){
        return convertingFlag;
    }

    /**
     * 
     * 
     * @param convertingFlag the convertingFlag to set
     */
    public void setConvertingFlag(String convertingFlag){
        this.convertingFlag = convertingFlag;
    }

    /**
     * 
     * 
     * @return the properyName
     */
    public String getPropertyName(){
        return propertyName;
    }

    /**
     * 
     * 
     * @param properyName the properyName to set
     */
    public void setPropertyName(String properyName){
        this.propertyName = properyName;
    }

    /**
     * 
     * 
     * @return the properyValue
     */
    public String getPropertyValue(){
        return propertyValue;
    }

    /**
     * 
     * 
     * @param properyValue the properyValue to set
     */
    public void setPropertyValue(String properyValue){
        this.propertyValue = properyValue;
    }

    /**
     * 
     * 
     * @return the constantScope
     */
    public String getConstantScope(){
        return constantScope;
    }

    /**
     * 
     * 
     * @param constantScope the constantScope to set
     */
    public void setConstantScope(String constantScope){
        this.constantScope = constantScope;
    }

    /**
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "PropertyInfo [convertingFlag=" + convertingFlag + ", propertyName=" + propertyName + ", propertyValue="
                + propertyValue + ", constantScope=" + constantScope + "]";
    }

}
