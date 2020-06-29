/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaConstantsVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaConstantsVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSysConstantVO extends OmcSchemaSysRootVO{
    private String kinds;
    private String kindDesc;
    private String constantValues;
    private String hexConvertingFlag;
    
    
    public String getKindDesc(){
        return kindDesc;
    }
   
    public void setKindDesc(String kindDesc){
        this.kindDesc = kindDesc;
    }


    public String getKinds(){
        return kinds;
    }
    
    
    public void setKinds(String kinds){
        this.kinds = kinds;
    }

    public String getConstantValues(){
        return constantValues;
    }
    public String getHexConvertingFlag(){
        return hexConvertingFlag;
    }
    
    
    public void setConstantValues(String constantValues){
        this.constantValues = constantValues;
    }
   
    public void setHexConvertingFlag(String hexConvertingFlag){
        this.hexConvertingFlag = hexConvertingFlag;
    }
}
