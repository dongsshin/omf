/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ProjectActivityDocumentTemplateSearchVO.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2017. 7. 19. heonhyung.lee Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.model;

import rap.api.object.document.model.ProjectActivityDocumentVO;

/**
 * <pre>
 * Class : ProjectActivityDocumentTemplateSearchVO
 * Description : TODO
 * </pre>
 * 
 * @author heonhyung.lee
 */
public class ProjectActivityDocumentSearchVO extends ProjectActivityDocumentVO {

    private String documentName;

    private String documentType;

    private String divisionUnitCode;

    private String project;

    private String model;

    private String eventCode;

    private String createdFromDate;

    private String createdToDate;

    private Boolean isPaging = false;

    private String templateName;

    private String projectObid;

    private String projectCode;
    
    private String fileName;

    private Boolean revisionCheck;

    private String dataRange;

    
    /**
     * 
     * 
     * @return the projectCode
     */
    public String getProjectCode(){
        return projectCode;
    }

    
    /**
     * 
     * 
     * @param projectCode the projectCode to set
     */
    public void setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }

    /**
     * 
     * 
     * @return the dataRange
     */
    public String getDataRange(){
        return dataRange;
    }

    /**
     * 
     * 
     * @param dataRange the dataRange to set
     */
    public void setDataRange(String dataRange){
        this.dataRange = dataRange;
    }

    private String teamObid;

    /**
     * 
     * 
     * @return the teamObid
     */
    public String getTeamObid(){
        return teamObid;
    }

    /**
     * 
     * 
     * @param teamObid the teamObid to set
     */
    public void setTeamObid(String teamObid){
        this.teamObid = teamObid;
    }

    /**
     * 
     * 
     * @return the revisionCheck
     */
    public Boolean getRevisionCheck(){
        return revisionCheck;
    }

    /**
     * 
     * 
     * @param revisionCheck the revisionCheck to set
     */
    public void setRevisionCheck(Boolean revisionCheck){
        this.revisionCheck = revisionCheck;
    }

    /**
     * 
     * 
     * @return the fileName
     */
    public String getFileName(){
        return fileName;
    }

    /**
     * 
     * 
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    /**
     * 
     * 
     * @return the projectObid
     */
    public String getProjectObid(){
        return projectObid;
    }

    /**
     * 
     * 
     * @param projectObid the projectObid to set
     */
    public void setProjectObid(String projectObid){
        this.projectObid = projectObid;
    }

    /**
     * 
     * 
     * @return the templateName
     */
    public String getTemplateName(){
        return templateName;
    }

    /**
     * 
     * 
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName){
        this.templateName = templateName;
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
     * @return the documentType
     */
    public String getDocumentType(){
        return documentType;
    }

    /**
     * 
     * 
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType){
        this.documentType = documentType;
    }

    /**
     * 
     * 
     * @return the divisionUnitCode
     */
    public String getDivisionUnitCode(){
        return divisionUnitCode;
    }

    /**
     * 
     * 
     * @param divisionUnitCode the divisionUnitCode to set
     */
    public void setDivisionUnitCode(String divisionUnitCode){
        this.divisionUnitCode = divisionUnitCode;
    }

    /**
     * 
     * 
     * @return the project
     */
    public String getProject(){
        return project;
    }

    /**
     * 
     * 
     * @param project the project to set
     */
    public void setProject(String project){
        this.project = project;
    }

    /**
     * 
     * 
     * @return the model
     */
    public String getModel(){
        return model;
    }

    /**
     * 
     * 
     * @param model the model to set
     */
    public void setModel(String model){
        this.model = model;
    }

    /**
     * 
     * 
     * @return the eventCode
     */
    public String getEventCode(){
        return eventCode;
    }

    /**
     * 
     * 
     * @param eventCode the eventCode to set
     */
    public void setEventCode(String eventCode){
        this.eventCode = eventCode;
    }

    /**
     * 
     * 
     * @return the createdFromDate
     */
    public String getCreatedFromDate(){
        return createdFromDate;
    }

    /**
     * 
     * 
     * @param createdFromDate the createdFromDate to set
     */
    public void setCreatedFromDate(String createdFromDate){
        this.createdFromDate = createdFromDate;
    }

    /**
     * 
     * 
     * @return the createdToDate
     */
    public String getCreatedToDate(){
        return createdToDate;
    }

    /**
     * 
     * 
     * @param createdToDate the createdToDate to set
     */
    public void setCreatedToDate(String createdToDate){
        this.createdToDate = createdToDate;
    }

}
