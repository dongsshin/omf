/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSActivityTemplateMasterSearchVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 14.  heonhyung.lee   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.model;

import rap.api.object.project.model.ActivityTemplateMasterVO;

/**
 * <pre>
 * Class : WBSActivityTemplateMasterSearchVO
 * Description : TODO
 * </pre>
 * 
 * @author heonhyung.lee
 */
public class ActivityTemplateMasterSearchVO extends ActivityTemplateMasterVO{
    private String        divisionUnit                                                  ;
    private String        developmentType                                               ;
    private String        activityAppliedOrganization                                   ;
    private String        defaultOwner                                                  ;
    private Boolean       isPaging = false                                              ;
    
    /**
     * 
     * 
     * @return the divisionUnit
     */
    public String getDivisionUnit(){
        return divisionUnit;
    }
    
    /**
     * 
     * 
     * @param divisionUnit the divisionUnit to set
     */
    public void setDivisionUnit(String divisionUnit){
        this.divisionUnit = divisionUnit;
    }

    /**
     * 
     * 
     * @return the developmentType
     */
    public String getDevelopmentType(){
        return developmentType;
    }
    
    /**
     * 
     * 
     * @param developmentType the developmentType to set
     */
    public void setDevelopmentType(String developmentType){
        this.developmentType = developmentType;
    }

    /**
     * 
     * 
     * @return the activityAppliedOrganization
     */
    public String getActivityAppliedOrganization(){
        return activityAppliedOrganization;
    }
    
    /**
     * 
     * 
     * @param activityAppliedOrganization the activityAppliedOrganization to set
     */
    public void setActivityAppliedOrganization(String activityAppliedOrganization){
        this.activityAppliedOrganization = activityAppliedOrganization;
    }
    
    /**
     * 
     * 
     * @return the defaultOwner
     */
    public String getDefaultOwner(){
        return defaultOwner;
    }
    
    /**
     * 
     * 
     * @param defaultOwner the defaultOwner to set
     */
    public void setDefaultOwner(String defaultOwner){
        this.defaultOwner = defaultOwner;
    }
    
    /**
     * 
     * 
     * @return the isPaging
     */
    public Boolean getIsPaging(){
        return isPaging;
    }
    
    /**
     * 
     * 
     * @param isPaging the isPaging to set
     */
    public void setIsPaging(Boolean isPaging){
        this.isPaging = isPaging;
    }
    
}
