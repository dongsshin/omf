/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : AllowedClassInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 12.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.model;


/**
 * <pre>
 * Class : AllowedClassInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class AllowedClassInfo {
    private long   flags;
    private String classObid;
    private String className;
    private String relationName;
        
    public String getClassName(){
        return className;
    }

    
    public void setClassName(String className){
        this.className = className;
    }

    
    public String getRelationName(){
        return relationName;
    }

    
    public void setRelationName(String relationName){
        this.relationName = relationName;
    }

    public long getFlags(){
        return flags;
    }
    
    public String getClassObid(){
        return classObid;
    }
    
    public void setFlags(long flags){
        this.flags = flags;
    }
    
    public void setClassObid(String classObid){
        this.classObid = classObid;
    }
}
