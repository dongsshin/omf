/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : RelationTableInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 12.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

/**
 * <pre>
 * Class : RelationTableInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class RelationTableInfo extends OmcOQLRoot{
    private String obid;
    private String className;
    private String fromClass;
    private String fromObid;
    private String toClass;
    private String toObid;
    private long   flags;
    int isTo ;
        
    
    public int getIsTo(){
        return isTo;
    }


    
    public void setIsTo(int isTo){
        this.isTo = isTo;
    }


    public long getFlags(){
        return flags;
    }

    
    public void setFlags(long flags){
        this.flags = flags;
    }

    public String getObid(){
        return obid;
    }
    
    public String getClassName(){
        return className;
    }
    
    public String getFromClass(){
        return fromClass;
    }
    
    public String getFromObid(){
        return fromObid;
    }
    
    public String getToClass(){
        return toClass;
    }
    
    public String getToObid(){
        return toObid;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public void setClassName(String className){
        this.className = className;
    }
    
    public void setFromClass(String fromClass){
        this.fromClass = fromClass;
    }
    
    public void setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    
    public void setToClass(String toClass){
        this.toClass = toClass;
    }
    
    public void setToObid(String toObid){
        this.toObid = toObid;
    }
}
