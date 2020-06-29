/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : VariableAttribute.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 8. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.model;

/**
 * <pre>
 * Class : VariableAttribute
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class VariableAttribute {

    private String attributeName;

    private String attributeType;

    private String attributeValue;

    /**
     * 
     * 
     * @return the attributeName
     */
    public String getAttributeName(){
        return attributeName;
    }

    /**
     * 
     * 
     * @param attributeName the attributeName to set
     */
    public void setAttributeName(String attributeName){
        this.attributeName = attributeName;
    }

    /**
     * 
     * 
     * @return the attributeType
     */
    public String getAttributeType(){
        return attributeType;
    }

    /**
     * 
     * 
     * @param attributeType the attributeType to set
     */
    public void setAttributeType(String attributeType){
        this.attributeType = attributeType;
    }

    /**
     * 
     * 
     * @return the attributeValue
     */
    public String getAttributeValue(){
        return attributeValue;
    }

    /**
     * 
     * 
     * @param attributeValue the attributeValue to set
     */
    public void setAttributeValue(String attributeValue){
        this.attributeValue = attributeValue;
    }

    /**
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "VariableAttribute [attributeName=" + attributeName + ", attributeType=" + attributeType
                + ", attributeValue=" + attributeValue + "]";
    }

}
