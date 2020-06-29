/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysAllowedClassForRelationVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 8.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaSysAllowedClassForRelationVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSysAllowedClsForRelVO extends OmcSchemaSysRootVO {
    private String  relationshipObid                   ;
    private String  classObid                          ;
    
    public String getRelationshipObid(){
        return relationshipObid;
    }
    
    public String getClassObid(){
        return classObid;
    }
    
    public void setRelationshipObid(String relationshipObid){
        this.relationshipObid = relationshipObid;
    }
    
    public void setClassObid(String classObid){
        this.classObid = classObid;
    }
}
