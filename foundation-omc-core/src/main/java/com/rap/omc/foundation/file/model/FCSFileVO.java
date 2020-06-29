package com.rap.omc.foundation.file.model;
public class FCSFileVO {
    private String  recordMode  ;
    private String  fromObid    ;
    private String  lifeCycle   ;
    private String  obid        ;
    private String  fileFormat  ;
    private String  assignedType;
    private String  descriptions;
    private String  attribute01 ;
    private String  attribute02 ;
    private String  attribute03 ;
    private String  attribute04 ;
    private String  attribute05 ;
    private String  attribute06 ;
    private String  attribute07 ;
    private String  attribute08 ;
    private String  attribute09 ;
    private String  attribute10 ;
    private String  attribute11 ;
    private String  attribute12 ;
    private String  attribute13 ;
    private String  attribute14 ;
    private String  attribute15 ;
    private String  fileUniqueId;
    private String  policy;

    private Float   sizes        = 0f;
    private String  filePath    ;
    private String  sysFileName ;
    private String  userFileName;
    private String  fileStore   ;
    private String  fileLocation;
    private String  locationFilePath;
    private String  oldObid;
    private String  errorDescription;
    
    

    
    public String getPolicy(){
        return policy;
    }

    
    public void setPolicy(String policy){
        this.policy = policy;
    }

    private String  bizRecordMode = "";
   
    public String getFileUniqueId(){
        return fileUniqueId;
    }
    
    public void setFileUniqueId(String fileUniqueId){
        this.fileUniqueId = fileUniqueId;
    }
    
    public String getRecordMode(){
        return recordMode;
    }
    
    public void setRecordMode(String recordMode){
        this.recordMode = recordMode;
    }
    
    public String getDescriptions(){
        return descriptions;
    }
    
    public void setDescriptions(String descriptions){
        this.descriptions = descriptions;
    }
    
    public String getLifeCycle(){
        return lifeCycle;
    }
    
    public void setLifeCycle(String lifeCycle){
        this.lifeCycle = lifeCycle;
    }
    
    public String getObid(){
        return obid;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public String getFromObid(){
        return fromObid;
    }
    
    public void setFromObid(String fromObid){
        this.fromObid = fromObid;
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
    
    public String getAttribute01(){
        return attribute01;
    }
    
    public void setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }
    
    public String getAttribute02(){
        return attribute02;
    }
    
    public void setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }
    
    public String getAttribute03(){
        return attribute03;
    }
    
    public void setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }
    
    public String getAttribute04(){
        return attribute04;
    }
    
    public void setAttribute04(String attribute04){
        this.attribute04 = attribute04;
    }
    
    public String getAttribute05(){
        return attribute05;
    }
    
    public void setAttribute05(String attribute05){
        this.attribute05 = attribute05;
    }
    
    public String getAttribute06(){
        return attribute06;
    }
    
    public void setAttribute06(String attribute06){
        this.attribute06 = attribute06;
    }
    
    public String getAttribute07(){
        return attribute07;
    }
    
    public void setAttribute07(String attribute07){
        this.attribute07 = attribute07;
    }
    
    public String getAttribute08(){
        return attribute08;
    }
    
    public void setAttribute08(String attribute08){
        this.attribute08 = attribute08;
    }
    
    public String getAttribute09(){
        return attribute09;
    }
    
    public void setAttribute09(String attribute09){
        this.attribute09 = attribute09;
    }
    
    public String getAttribute10(){
        return attribute10;
    }
    
    public void setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    
    public String getAttribute11(){
        return attribute11;
    }
    
    public void setAttribute11(String attribute11){
        this.attribute11 = attribute11;
    }
    
    public String getAttribute12(){
        return attribute12;
    }
    
    public void setAttribute12(String attribute12){
        this.attribute12 = attribute12;
    }
    
    public String getAttribute13(){
        return attribute13;
    }
    
    public void setAttribute13(String attribute13){
        this.attribute13 = attribute13;
    }
    
    public String getAttribute14(){
        return attribute14;
    }
    
    public void setAttribute14(String attribute14){
        this.attribute14 = attribute14;
    }
    
    public String getAttribute15(){
        return attribute15;
    }
    
    public void setAttribute15(String attribute15){
        this.attribute15 = attribute15;
    }

    
    public String getFileStore(){
        return fileStore;
    }

    
    public void setFileStore(String fileStore){
        this.fileStore = fileStore;
    }

    
    public Float getSizes(){
        return sizes;
    }

    
    public void setSizes(Float sizes){
        this.sizes = sizes;
    }

    
    public String getFilePath(){
        return filePath;
    }

    
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    
    public String getSysFileName(){
        return sysFileName;
    }

    
    public void setSysFileName(String sysFileName){
        this.sysFileName = sysFileName;
    }

    
    public String getFileLocation(){
        return fileLocation;
    }

    
    public void setFileLocation(String fileLocation){
        this.fileLocation = fileLocation;
    }

    
    public String getLocationFilePath(){
        return locationFilePath;
    }

    
    public void setLocationFilePath(String locationFilePath){
        this.locationFilePath = locationFilePath;
    }

    
    public String getUserFileName(){
        return userFileName;
    }

    
    public void setUserFileName(String userFileName){
        this.userFileName = userFileName;
    }

    
    public String getBizRecordMode(){
        return bizRecordMode;
    }

    
    public void setBizRecordMode(String bizRecordMode){
        this.bizRecordMode = bizRecordMode;
    }

    
    public String getOldObid(){
        return oldObid;
    }

    
    public void setOldObid(String oldObid){
        this.oldObid = oldObid;
    }

    
    public String getErrorDescription(){
        return errorDescription;
    }

    
    public void setErrorDescription(String errorDescription){
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString(){
        return "FCSFileVO [recordMode=" + recordMode + ", fromObid=" + fromObid + ", lifeCycle=" + lifeCycle + ", obid="
                + obid + ", fileFormat=" + fileFormat + ", assignedType=" + assignedType + ", descriptions="
                + descriptions + ", attribute01=" + attribute01 + ", attribute02=" + attribute02 + ", attribute03="
                + attribute03 + ", attribute04=" + attribute04 + ", attribute05=" + attribute05 + ", attribute06="
                + attribute06 + ", attribute07=" + attribute07 + ", attribute08=" + attribute08 + ", attribute09="
                + attribute09 + ", attribute10=" + attribute10 + ", attribute11=" + attribute11 + ", attribute12="
                + attribute12 + ", attribute13=" + attribute13 + ", attribute14=" + attribute14 + ", attribute15="
                + attribute15 + ", fileUniqueId=" + fileUniqueId + ", fileStore=" + fileStore + ", sizes=" + sizes
                + ", filePath=" + filePath + ", sysFileName=" + sysFileName + ", userFileName=" + userFileName
                + ", fileLocation=" + fileLocation + ", locationFilePath=" + locationFilePath + ", oldObid=" + oldObid
                + ", errorDescription=" + errorDescription + ", bizRecordMode=" + bizRecordMode + ", getFileUniqueId()="
                + getFileUniqueId() + ", getRecordMode()=" + getRecordMode() + ", getDescriptions()="
                + getDescriptions() + ", getLifeCycle()=" + getLifeCycle() + ", getObid()=" + getObid()
                + ", getFromObid()=" + getFromObid() + ", getFileFormat()=" + getFileFormat() + ", getAssignedType()="
                + getAssignedType() + ", getAttribute01()=" + getAttribute01() + ", getAttribute02()="
                + getAttribute02() + ", getAttribute03()=" + getAttribute03() + ", getAttribute04()=" + getAttribute04()
                + ", getAttribute05()=" + getAttribute05() + ", getAttribute06()=" + getAttribute06()
                + ", getAttribute07()=" + getAttribute07() + ", getAttribute08()=" + getAttribute08()
                + ", getAttribute09()=" + getAttribute09() + ", getAttribute10()=" + getAttribute10()
                + ", getAttribute11()=" + getAttribute11() + ", getAttribute12()=" + getAttribute12()
                + ", getAttribute13()=" + getAttribute13() + ", getAttribute14()=" + getAttribute14()
                + ", getAttribute15()=" + getAttribute15() + ", getFileStore()=" + getFileStore() + ", getSizes()="
                + getSizes() + ", getFilePath()=" + getFilePath() + ", getSysFileName()=" + getSysFileName()
                + ", getFileLocation()=" + getFileLocation() + ", getLocationFilePath()=" + getLocationFilePath()
                + ", getUserFileName()=" + getUserFileName() + ", getBizRecordMode()=" + getBizRecordMode()
                + ", getOldObid()=" + getOldObid() + ", getErrorDescription()=" + getErrorDescription()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }
}
