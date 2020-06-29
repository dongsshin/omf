/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaRoleGroup.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaRoleGroup
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaRoleGroupVO extends OmcSchemaSysRootVO{
    private String  kindsStr                           ;
    private String  descriptions                       ;
    
    public String getKindsStr(){
        return kindsStr;
    }
    
    public String getDescriptions(){
        return descriptions;
    }
    
    public void setKindsStr(String kindsStr){
        this.kindsStr = kindsStr;
    }
    
    public void setDescriptions(String descriptions){
        this.descriptions = descriptions;
    }
    
}
