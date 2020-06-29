/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : KeyInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 29. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.common.model;

/**
 * <pre>
 * Class : ObjectTableKeyVO
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class ObjectTableKeyVO {

    private String obid;
    private String className;
    private long flags;
    
    public String getObid(){
        return obid;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public String getClassName(){
        return className;
    }
    
    public void setClassName(String className){
        this.className = className;
    }
    
    public long getFlags(){
        return flags;
    }
    
    public void setFlags(long flags){
        this.flags = flags;
    }

    
}
