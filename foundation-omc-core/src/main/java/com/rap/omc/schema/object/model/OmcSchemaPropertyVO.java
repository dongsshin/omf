/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaPropertyVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaPropertyVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaPropertyVO extends OmcSchemaSysRootVO{
    private String  sysObject                          ;
    private long    kinds                              ;
    private String  propertyName                       ;
    private String  propertyValue                      ;
    
    public String getSysObject(){
        return sysObject;
    }
    
    public long getKinds(){
        return kinds;
    }
    
    public String getPropertyName(){
        return propertyName;
    }
    
    public String getPropertyValue(){
        return propertyValue;
    }
    
    public void setSysObject(String sysObject){
        this.sysObject = sysObject;
    }
    
    public void setKinds(long kinds){
        this.kinds = kinds;
    }
    
    public void setPropertyName(String propertyName){
        this.propertyName = propertyName;
    }
    
    public void setPropertyValue(String propertyValue){
        this.propertyValue = propertyValue;
    }
}
