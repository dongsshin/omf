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
public class UserPropertyVO extends ModelRootVO{

    private String userNames;

    private String propertyName;

    private String propertyValue;

    private String searchDate;
    
    private long searchUserActiveFlag = OmcSystemConstants.SYSUSER_FLAG_Active;
    
    private long searchPropertyKind = OmcSystemConstants.SYSPTY_KIND_User;
    
    private long searchPropertyFlag = OmcSystemConstants.SYSPTY_FLAG_Active;
    
    public long getSearchUserActiveFlag(){
        return searchUserActiveFlag;
    }

    
    public long getSearchPropertyKind(){
        return searchPropertyKind;
    }

    
    public long getSearchPropertyFlag(){
        return searchPropertyFlag;
    }

    
    public void setSearchUserActiveFlag(long searchUserActiveFlag){
        this.searchUserActiveFlag = searchUserActiveFlag;
    }

    
    public void setSearchPropertyKind(long searchPropertyKind){
        this.searchPropertyKind = searchPropertyKind;
    }

    
    public void setSearchPropertyFlag(long searchPropertyFlag){
        this.searchPropertyFlag = searchPropertyFlag;
    }

    
    /**
     * 
     * 
     * @return the userNames
     */
    public String getUserNames(){
        return userNames;
    }

    /**
     * 
     * 
     * @param userNames the userNames to set
     */
    public void setUserNames(String userNames){
        this.userNames = userNames;
    }

    /**
     * 
     * 
     * @return the propertyName
     */
    public String getPropertyName(){
        return propertyName;
    }

    /**
     * 
     * 
     * @param propertyName the propertyName to set
     */
    public void setPropertyName(String propertyName){
        this.propertyName = propertyName;
    }

    /**
     * 
     * 
     * @return the propertyValue
     */
    public String getPropertyValue(){
        return propertyValue;
    }

    /**
     * 
     * 
     * @param propertyValue the propertyValue to set
     */
    public void setPropertyValue(String propertyValue){
        this.propertyValue = propertyValue;
    }

    /**
     * 
     * 
     * @return the searchDate
     */
    public String getSearchDate(){
        return searchDate;
    }

    /**
     * 
     * 
     * @param searchDate the searchDate to set
     */
    public void setSearchDate(String searchDate){
        this.searchDate = searchDate;
    }

}
