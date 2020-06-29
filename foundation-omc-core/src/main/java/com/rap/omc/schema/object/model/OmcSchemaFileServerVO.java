/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaFileServerVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 31.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaFileServerVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaFileServerVO extends OmcSchemaSysRootVO{
    private String  kindsStr                          ;
    private String  fileServer                         ;
    private String  filePath                           ;
    private String  ftpUser                            ;
    private String  ftpPassword                        ;
    private String  serverProtocol                     ;
    private String  serverPort                         ;
    private String  serviceDomain                      ;
    private String  servicePort                        ;
    private String  serviceUrl                         ;
    
    
    public String getKindsStr(){
        return kindsStr;
    }

    
    public void setKindsStr(String kindsStr){
        this.kindsStr = kindsStr;
    }

    public String getFileServer(){
        return fileServer;
    }
    
    public String getFilePath(){
        return filePath;
    }
    
    public String getFtpUser(){
        return ftpUser;
    }
    
    public String getFtpPassword(){
        return ftpPassword;
    }
    
    public String getServerProtocol(){
        return serverProtocol;
    }
    
    public String getServerPort(){
        return serverPort;
    }
    
    public String getServiceDomain(){
        return serviceDomain;
    }
    
    public String getServicePort(){
        return servicePort;
    }
    
    public String getServiceUrl(){
        return serviceUrl;
    }
    
    public void setFileServer(String fileServer){
        this.fileServer = fileServer;
    }
    
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
    
    public void setFtpUser(String ftpUser){
        this.ftpUser = ftpUser;
    }
    
    public void setFtpPassword(String ftpPassword){
        this.ftpPassword = ftpPassword;
    }
    
    public void setServerProtocol(String serverProtocol){
        this.serverProtocol = serverProtocol;
    }
    
    public void setServerPort(String serverPort){
        this.serverPort = serverPort;
    }
    
    public void setServiceDomain(String serviceDomain){
        this.serviceDomain = serviceDomain;
    }
    
    public void setServicePort(String servicePort){
        this.servicePort = servicePort;
    }
    
    public void setServiceUrl(String serviceUrl){
        this.serviceUrl = serviceUrl;
    }
}
