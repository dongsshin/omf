/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : UsersProfileVO.java
 * ===========================================
 * Modify Date Modifier Description
 * 2015. 3. 17. youngmi.won Initial
 * ===========================================
 */
package com.rap.user.model;

import rap.api.object.organization.model.UsersVO;

@SuppressWarnings("serial")
public class UsersProfileVO extends UsersVO {
    private String division;
    private String managementGroup;
    private Boolean daylightSavings;
    private String timeZone;
    private String affiliate;
    /**
     * 
     * 
     * @return the division
     */
    public String getDivision(){
        return division;
    }
    /**
     * 
     * 
     * @param division the division to set
     */
    public void setDivision(String division){
        this.division = division;
    }
    /**
     * 
     * 
     * @return the managementGroup
     */
    public String getManagementGroup(){
        return managementGroup;
    }
    /**
     * 
     * 
     * @param managementGroup the managementGroup to set
     */
    public void setManagementGroup(String managementGroup){
        this.managementGroup = managementGroup;
    }
    /**
     * 
     * 
     * @return the daylightSavings
     */
    public Boolean getDaylightSavings(){
        return daylightSavings;
    }
    /**
     * 
     * 
     * @param daylightSavings the daylightSavings to set
     */
    public void setDaylightSavings(Boolean daylightSavings){
        this.daylightSavings = daylightSavings;
    }
    /**
     * 
     * 
     * @return the timeZone
     */
    public String getTimeZone(){
        return timeZone;
    }
    /**
     * 
     * 
     * @param timeZone the timeZone to set
     */
    public void setTimeZone(String timeZone){
        this.timeZone = timeZone;
    }
    /**
     * 
     * 
     * @return the affiliate
     */
    public String getAffiliate(){
        return affiliate;
    }
    /**
     * 
     * 
     * @param affiliate the affiliate to set
     */
    public void setAffiliate(String affiliate){
        this.affiliate = affiliate;
    }
    
    
    
}