/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaMethodSetVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaMethodSetVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaMethodSetVO extends OmcSchemaSysRootVO{
        private String  accessConstant                    ;
        private String  kindsStr                          ;
        
        public String getAccessConstant(){
            return accessConstant;
        }
        
        public String getKindsStr(){
            return kindsStr;
        }
        
        public void setAccessConstant(String accessConstant){
            this.accessConstant = accessConstant;
        }
        
        public void setKindsStr(String kindsStr){
            this.kindsStr = kindsStr;
        }
        
}
