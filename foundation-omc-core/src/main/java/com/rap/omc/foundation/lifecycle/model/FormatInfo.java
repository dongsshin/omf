/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : FileFormatInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 11.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.model;


/**
 * <pre>
 * Class : FileFormatInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class FormatInfo {
    private String obid;
    private String flags;
    private String names;
    private String allowedSuffix;
    
    public String getObid(){
        return obid;
    }
    
    public String getFlags(){
        return flags;
    }
    
    public String getNames(){
        return names;
    }
    
    public String getAllowedSuffix(){
        return allowedSuffix;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public void setFlags(String flags){
        this.flags = flags;
    }
    
    public void setNames(String names){
        this.names = names;
    }
    
    public void setAllowedSuffix(String allowedSuffix){
        this.allowedSuffix = allowedSuffix;
    }
    
}
