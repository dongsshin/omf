/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysTimeZoneVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaSysTimeZoneVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSysTimeZoneVO extends OmcSchemaSysRootVO{
    private String displayedName;

    
    public String getDisplayedName(){
        return displayedName;
    }

    
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }
    
}
