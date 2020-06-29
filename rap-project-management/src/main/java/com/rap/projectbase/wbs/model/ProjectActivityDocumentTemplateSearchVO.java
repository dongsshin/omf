/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ProjectActivityDocumentTemplateSearchVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 19.  heonhyung.lee   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.model;

import rap.api.object.document.model.ProjectActivityDocumentTemplateVO;

/**
 * <pre>
 * Class : ProjectActivityDocumentTemplateSearchVO
 * Description : TODO
 * </pre>
 * 
 * @author heonhyung.lee
 */
public class ProjectActivityDocumentTemplateSearchVO extends ProjectActivityDocumentTemplateVO{
    private String        isMandatory                                                   ;
    private String        skipGradeList                                                 ;
    private Boolean       isPaging = false                                              ;
    private String        activityTemplateObid                                          ;
    
    private String        divisionUnit                                                  ;
    private String        developmentType                                               ;
    private String        phaseName                                                     ;
    private Boolean       fromFDR = false                                               ;
    private String        documentName                                                  ;
    
    private int           totalCount = 0                                                ;
    private Boolean       onlyLatest = false                                            ;
    
    
    
    
    /**
     * 
     * 
     * @return the onlyLatest
     */
    public Boolean getOnlyLatest(){
        return onlyLatest;
    }





    
    /**
     * 
     * 
     * @param onlyLatest the onlyLatest to set
     */
    public void setOnlyLatest(Boolean onlyLatest){
        this.onlyLatest = onlyLatest;
    }





    /**
     * 
     * 
     * @return the totalCount
     */
    public int getTotalCount(){
        return totalCount;
    }




    
    /**
     * 
     * 
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }




    /**
     * 
     * 
     * @return the documentName
     */
    public String getDocumentName(){
        return documentName;
    }



    
    /**
     * 
     * 
     * @param documentName the documentName to set
     */
    public void setDocumentName(String documentName){
        this.documentName = documentName;
    }



    /**
     * 
     * 
     * @return the fromFDR
     */
    public Boolean getFromFDR(){
        return fromFDR;
    }


    
    /**
     * 
     * 
     * @param fromFDR the fromFDR to set
     */
    public void setFromFDR(Boolean fromFDR){
        this.fromFDR = fromFDR;
    }


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
     * @return the phaseName
     */
    public String getPhaseName(){
        return phaseName;
    }

    
    /**
     * 
     * 
     * @param phaseName the phaseName to set
     */
    public void setPhaseName(String phaseName){
        this.phaseName = phaseName;
    }

    /**
     * 
     * 
     * @return the isMandatory
     */
    public String getIsMandatory(){
        return isMandatory;
    }
    
    /**
     * 
     * 
     * @param isMandatory the isMandatory to set
     */
    public void setIsMandatory(String isMandatory){
        this.isMandatory = isMandatory;
    }
    
    /**
     * 
     * 
     * @return the skipGradeList
     */
    public String getSkipGradeList(){
        return skipGradeList;
    }
    
    /**
     * 
     * 
     * @param skipGradeList the skipGradeList to set
     */
    public void setSkipGradeList(String skipGradeList){
        this.skipGradeList = skipGradeList;
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

    
    /**
     * 
     * 
     * @return the activityTemplateObid
     */
    public String getActivityTemplateObid(){
        return activityTemplateObid;
    }

    
    /**
     * 
     * 
     * @param activityTemplateObid the activityTemplateObid to set
     */
    public void setActivityTemplateObid(String activityTemplateObid){
        this.activityTemplateObid = activityTemplateObid;
    }
    
}
