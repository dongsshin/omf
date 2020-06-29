/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaAttributeVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 10.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaAttributeVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaAttributeVO extends OmcSchemaSysRootVO{
    private String displayedNames;
    private String displayedNamesKr;
    
    public String getDisplayedNames(){
        return displayedNames;
    }
    
    public String getDisplayedNamesKr(){
        return displayedNamesKr;
    }
    
    public void setDisplayedNames(String displayedNames){
        this.displayedNames = displayedNames;
    }
    
    public void setDisplayedNamesKr(String displayedNamesKr){
        this.displayedNamesKr = displayedNamesKr;
    }
    
}
