/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaDynamicAttrGrpVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaDynamicAttrGrpVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaDynamicAttrGrpVO extends OmcSchemaSysRootVO{
    private String  kindsStr                          ;
    private String  descriptions                      ;
    private String  parent                            ;
    private String  displayedNames                    ;
    
    
    public String getDisplayedNames(){
        return displayedNames;
    }

    
    public void setDisplayedNames(String displayedNames){
        this.displayedNames = displayedNames;
    }

    public String getKindsStr(){
        return kindsStr;
    }
    
    public String getDescriptions(){
        return descriptions;
    }
    
    public String getParent(){
        return parent;
    }
  
    public void setKindsStr(String kindsStr){
        this.kindsStr = kindsStr;
    }
    
    public void setDescriptions(String descriptions){
        this.descriptions = descriptions;
    }
    
    public void setParent(String parent){
        this.parent = parent;
    }
    

}
