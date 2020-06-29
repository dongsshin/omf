/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSiteVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 5.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaSiteVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSiteVO extends OmcSchemaSysRootVO{
    private String displayedNames;

    public String getDisplayedNames(){
        return displayedNames;
    }
    public void setDisplayedNames(String displayedNames){
        this.displayedNames = displayedNames;
    }
}