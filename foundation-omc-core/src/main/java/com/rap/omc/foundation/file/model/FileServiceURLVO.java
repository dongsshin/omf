/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : FcsLocationVO.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2015. 2. 4.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.foundation.file.model;


/**
 * <pre>
 * Class : FcsLocationVO
 * Description : TODO
 * </pre>
 *
 * @author jongjung.kwon
 */
public class FileServiceURLVO {

    private String schemaObid;
    
    private String fileRootPath;
    private String serviceUrl;
    
    private String serviceCheckOutUrl;
    private String serviceCheckInUrl;
    
    private String serviceCheckOutFromServerUrl;
    private String serviceCheckInFromServerUrl;
    
    private String serviceCheckOutAllUrl;
    private String serviceCopyFileInServerUrl;
    private String serviceMoveFileForDeleteServerUrl;
       
    
    
    public String getServiceMoveFileForDeleteServerUrl(){
        return serviceMoveFileForDeleteServerUrl;
    }




    
    public void setServiceMoveFileForDeleteServerUrl(String serviceMoveFileForDeleteServerUrl){
        this.serviceMoveFileForDeleteServerUrl = serviceMoveFileForDeleteServerUrl;
    }




    public String getSchemaObid(){
        return schemaObid;
    }



    
    public void setSchemaObid(String schemaObid){
        this.schemaObid = schemaObid;
    }

    public String getFileRootPath(){
        return fileRootPath;
    }

    
    public void setFileRootPath(String fileRootPath){
        this.fileRootPath = fileRootPath;
    }

    
    public String getServiceUrl(){
        return serviceUrl;
    }

    
    public void setServiceUrl(String serviceUrl){
        this.serviceUrl = serviceUrl;
    }

    
    public String getServiceCheckOutUrl(){
        return serviceCheckOutUrl;
    }

    
    public void setServiceCheckOutUrl(String serviceCheckOutUrl){
        this.serviceCheckOutUrl = serviceCheckOutUrl;
    }

    
    public String getServiceCheckInUrl(){
        return serviceCheckInUrl;
    }

    
    public void setServiceCheckInUrl(String serviceCheckInUrl){
        this.serviceCheckInUrl = serviceCheckInUrl;
    }

    
    public String getServiceCheckOutFromServerUrl(){
        return serviceCheckOutFromServerUrl;
    }

    
    public void setServiceCheckOutFromServerUrl(String serviceCheckOutFromServerUrl){
        this.serviceCheckOutFromServerUrl = serviceCheckOutFromServerUrl;
    }

    
    public String getServiceCheckInFromServerUrl(){
        return serviceCheckInFromServerUrl;
    }

    
    public void setServiceCheckInFromServerUrl(String serviceCheckInFromServerUrl){
        this.serviceCheckInFromServerUrl = serviceCheckInFromServerUrl;
    }

    
    public String getServiceCheckOutAllUrl(){
        return serviceCheckOutAllUrl;
    }

    
    public void setServiceCheckOutAllUrl(String serviceCheckOutAllUrl){
        this.serviceCheckOutAllUrl = serviceCheckOutAllUrl;
    }

    
    public String getServiceCopyFileInServerUrl(){
        return serviceCopyFileInServerUrl;
    }

    
    public void setServiceCopyFileInServerUrl(String serviceCopyFileInServerUrl){
        this.serviceCopyFileInServerUrl = serviceCopyFileInServerUrl;
    }

}