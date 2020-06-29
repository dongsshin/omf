package com.rap.omc.foundation.file.model;

import com.rap.omc.api.object.model.FilesVO;

public class FCSParameterVO {
    private String communicationUniqueId;
    private String locationObid;
    private String locationName;
    private String sourceRootPath;
    private String sourceFilePath;
    private String targetRootPath;
    private String targetFilePath;
    private String targetFileName;
    private Float  targetFileSize;
    private String fileObid;
    private String businessObjectObid;
    private String businessObjectLifeCycle;
    private FilesVO fileVO;
    private String assignedType;
    private String fileFormat  ;
    private String userFileName  ;
    /**
     * 
     * 
     * @return the fileVO
     */
    public FilesVO getFileVO(){
        return fileVO;
    }
    /**
     * 
     * 
     * @param fileVO the fileVO to set
     */
    public void setFileVO(FilesVO fileVO){
        this.fileVO = fileVO;
    }
    public String getUserFileName(){
        return userFileName;
    }
    public void setUserFileName(String userFileName){
        this.userFileName = userFileName;
    }
    public String getFileFormat(){
        return fileFormat;
    }
    public void setFileFormat(String fileFormat){
        this.fileFormat = fileFormat;
    }
    public String getAssignedType(){
        return assignedType;
    }
    public void setAssignedType(String assignedType){
        this.assignedType = assignedType;
    }
    public Float getTargetFileSize(){
        return targetFileSize;
    }
    public void setTargetFileSize(Float targetFileSize){
        this.targetFileSize = targetFileSize;
    }
    public String getTargetFileName(){
        return targetFileName;
    }
    public void setTargetFileName(String targetFileName){
        this.targetFileName = targetFileName;
    }

    public String getTargetFilePath(){
        return targetFilePath;
    }

    
    public void setTargetFilePath(String targetFilePath){
        this.targetFilePath = targetFilePath;
    }

    public String getCommunicationUniqueId(){
        return communicationUniqueId;
    }
    
    public void setCommunicationUniqueId(String communicationUniqueId){
        this.communicationUniqueId = communicationUniqueId;
    }
    
    public String getLocationObid(){
        return locationObid;
    }
    
    public void setLocationObid(String locationObid){
        this.locationObid = locationObid;
    }
    
    public String getLocationName(){
        return locationName;
    }
    
    public void setLocationName(String locationName){
        this.locationName = locationName;
    }
    
    public String getSourceRootPath(){
        return sourceRootPath;
    }
    
    public void setSourceRootPath(String sourceRootPath){
        this.sourceRootPath = sourceRootPath;
    }
    
    public String getSourceFilePath(){
        return sourceFilePath;
    }
    
    public void setSourceFilePath(String sourceFilePath){
        this.sourceFilePath = sourceFilePath;
    }
    
    public String getTargetRootPath(){
        return targetRootPath;
    }
    
    public void setTargetRootPath(String targetRootPath){
        this.targetRootPath = targetRootPath;
    }
    
    public String getFileObid(){
        return fileObid;
    }
    
    public void setFileObid(String fileObid){
        this.fileObid = fileObid;
    }
    
    public String getBusinessObjectObid(){
        return businessObjectObid;
    }
    
    public void setBusinessObjectObid(String businessObjectObid){
        this.businessObjectObid = businessObjectObid;
    }
    
    public String getBusinessObjectLifeCycle(){
        return businessObjectLifeCycle;
    }
    
    public void setBusinessObjectLifeCycle(String businessObjectLifeCycle){
        this.businessObjectLifeCycle = businessObjectLifeCycle;
    }
}
