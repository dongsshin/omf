/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaStoreLocation.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaStoreLocation
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaStoreLocationVO extends OmcSchemaSysRootVO{
    private String  kindsStr                          ;
    private String  names                             ;
    private String  serverNames                       ;
    private String  serverPath                        ;
    private String  ftpUser                           ;
    private String  ftpPassword                       ;
    private String  protocol                          ;
    private String  port                              ;
    private String  serviceDomain                     ;
    private String  servicePort                       ;
    private String  serviceUrl                        ;
    
    public String getKindsStr(){
        return kindsStr;
    }
    
    public String getNames(){
        return names;
    }
    
    public String getServerNames(){
        return serverNames;
    }
    
    public String getServerPath(){
        return serverPath;
    }
    
    public String getFtpUser(){
        return ftpUser;
    }
    
    public String getFtpPassword(){
        return ftpPassword;
    }
    
    public String getProtocol(){
        return protocol;
    }
    
    public String getPort(){
        return port;
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
    
    public void setKindsStr(String kindsStr){
        this.kindsStr = kindsStr;
    }
    
    public void setNames(String names){
        this.names = names;
    }
    
    public void setServerNames(String serverNames){
        this.serverNames = serverNames;
    }
    
    public void setServerPath(String serverPath){
        this.serverPath = serverPath;
    }
    
    public void setFtpUser(String ftpUser){
        this.ftpUser = ftpUser;
    }
    
    public void setFtpPassword(String ftpPassword){
        this.ftpPassword = ftpPassword;
    }
    
    public void setProtocol(String protocol){
        this.protocol = protocol;
    }
    
    public void setPort(String port){
        this.port = port;
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
