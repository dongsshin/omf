/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : UserPropertyVO.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 3. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.user.model;

import com.rap.omc.foundation.common.model.ModelRootVO;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : UserPropertyVO
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class User2UserSearchVO extends ModelRootVO{
    
    private String userNames;
    private long searchUserFromKind      ;
    private long searchUserRelSchemaKind ;
    private long searchUserToKind        ;
    private long searchUserFromFlags     = OmcSystemConstants.SYSUSER_FLAG_Active;
    private long searchUserRelFlags      = OmcSystemConstants.SYSREL_FLAG_Active;
    private long searchUserToFlags       = OmcSystemConstants.SYSUSER_FLAG_Active;
    
    public String getUserNames(){
        return userNames;
    }
    
    public void setUserNames(String userNames){
        this.userNames = userNames;
    }
    
    public long getSearchUserFromKind(){
        return searchUserFromKind;
    }
    
    public void setSearchUserFromKind(long searchUserFromKind){
        this.searchUserFromKind = searchUserFromKind;
    }
    
    public long getSearchUserFromFlags(){
        return searchUserFromFlags;
    }
    
    public void setSearchUserFromFlags(long searchUserFromFlags){
        this.searchUserFromFlags = searchUserFromFlags;
    }
    
    public long getSearchUserRelFlags(){
        return searchUserRelFlags;
    }
    
    public void setSearchUserRelFlags(long searchUserRelFlags){
        this.searchUserRelFlags = searchUserRelFlags;
    }
    
    public long getSearchUserRelSchemaKind(){
        return searchUserRelSchemaKind;
    }
    
    public void setSearchUserRelSchemaKind(long searchUserRelSchemaKind){
        this.searchUserRelSchemaKind = searchUserRelSchemaKind;
    }
    
    public long getSearchUserToKind(){
        return searchUserToKind;
    }
    
    public void setSearchUserToKind(long searchUserToKind){
        this.searchUserToKind = searchUserToKind;
    }
    
    public long getSearchUserToFlags(){
        return searchUserToFlags;
    }
    
    public void setSearchUserToFlags(long searchUserToFlags){
        this.searchUserToFlags = searchUserToFlags;
    }
    
}
