/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaLifeCycleRelationVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 29.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaLifeCycleRelationVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaLifeCycleRelationVO extends OmcSchemaSysRootVO{
    private long    kinds                              ;
    private String  fromObid                           ;
    private String  toObid                             ;
    
    public long getKinds(){
        return kinds;
    }
    
    public String getFromObid(){
        return fromObid;
    }
    
    public String getToObid(){
        return toObid;
    }
    
    public void setKinds(long kinds){
        this.kinds = kinds;
    }
    
    public void setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    
    public void setToObid(String toObid){
        this.toObid = toObid;
    }
    
}
