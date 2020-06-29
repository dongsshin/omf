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

import com.rap.omc.util.NullUtil;
import com.rap.omc.schema.util.OmcApplicationConstants;

/**
 * <pre>
 * Class : FcsLocationVO
 * Description : TODO
 * </pre>
 *
 * @author jongjung.kwon
 */
public class FcsLocationVO {

    private String userId;
    private String storeObid;
    private String storeName;
    private String locationObid;
    private String locationName;
    private String serviceDomain;
    private String servicePort;
    private String serviceUrl;
    private String server;
    private String filePath;
    private String fileServer;
    private String ftpUser;
    private String ftpPassword;
    private String serverProtocol;
    private String storeType;
    
    public boolean isStore(){
        if(NullUtil.isNone(this.storeType)) return false;
        if(this.storeType.equals(OmcApplicationConstants.FILE_STORE_LOCATION_STORE)) return true;
        return false;
    }
    public boolean isLocation(){
        if(NullUtil.isNone(this.storeType)) return false;
        if(this.storeType.equals(OmcApplicationConstants.FILE_STORE_LOCATION_LOCATION)) return true;
        return false;
    }
    public String getUserId(){
        return userId;
    }
    
    public void setUserId(String userId){
        this.userId = userId;
    }
    
    public String getStoreObid(){
        return storeObid;
    }
    
    public void setStoreObid(String storeObid){
        this.storeObid = storeObid;
    }
    
    public String getStoreName(){
        return storeName;
    }
    
    public void setStoreName(String storeName){
        this.storeName = storeName;
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
    
    public String getServiceDomain(){
        return serviceDomain;
    }
    
    public void setServiceDomain(String serviceDomain){
        this.serviceDomain = serviceDomain;
    }
    
    public String getServicePort(){
        return servicePort;
    }
    
    public void setServicePort(String servicePort){
        this.servicePort = servicePort;
    }
    
    public String getServiceUrl(){
        return serviceUrl;
    }
    
    public void setServiceUrl(String serviceUrl){
        this.serviceUrl = serviceUrl;
    }
    
    public String getServer(){
        return server;
    }
    
    public void setServer(String server){
        this.server = server;
    }
    
    public String getFilePath(){
        return filePath;
    }
    
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
    
    public String getFileServer(){
        return fileServer;
    }
    
    public void setFileServer(String fileServer){
        this.fileServer = fileServer;
    }
    
    public String getFtpUser(){
        return ftpUser;
    }
    
    public void setFtpUser(String ftpUser){
        this.ftpUser = ftpUser;
    }
    
    public String getFtpPassword(){
        return ftpPassword;
    }
    
    public void setFtpPassword(String ftpPassword){
        this.ftpPassword = ftpPassword;
    }
    
    public String getServerProtocol(){
        return serverProtocol;
    }
    
    public void setServerProtocol(String serverProtocol){
        this.serverProtocol = serverProtocol;
    }
    
    public String getStoreType(){
        return storeType;
    }
    
    public void setStoreType(String storeType){
        this.storeType = storeType;
    }
    

}