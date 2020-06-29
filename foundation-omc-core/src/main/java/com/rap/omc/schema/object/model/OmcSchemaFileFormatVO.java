/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaFileFormat.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaFileFormat
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaFileFormatVO extends OmcSchemaSysRootVO{
    private String  displayedNames                     ;
    private String  allowedSuffix                      ;
    
    public String getDisplayedNames(){
        return displayedNames;
    }
    
    public String getAllowedSuffix(){
        return allowedSuffix;
    }
    
    public void setDisplayedNames(String displayedNames){
        this.displayedNames = displayedNames;
    }
    
    public void setAllowedSuffix(String allowedSuffix){
        this.allowedSuffix = allowedSuffix;
    }

    
}
