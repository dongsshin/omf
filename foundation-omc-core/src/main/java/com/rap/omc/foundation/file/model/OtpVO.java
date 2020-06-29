/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OtpVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 1. 22.  yongsik1.kim   Initial
 * ===========================================
 */
package com.rap.omc.foundation.file.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * <pre>
 * Class : OtpVO
 * Description : TODO
 * </pre>
 * 
 * @author yongsik1.kim
 */
public class OtpVO {
    
    private String nonce;
    
    private String remoteAddr;
    
    private int usedCount;
    
    private Date usedTime;
    
    private String sessionInfo;
        
    public String getSessionInfo(){
        return sessionInfo;
    }
    
    public void setSessionInfo(String sessionInfo){
        this.sessionInfo = sessionInfo;
    }
    public OtpVO(String nonce,String remoteAddr) {
        super();
        this.nonce = nonce;
        this.remoteAddr = remoteAddr;
    }
    public OtpVO(String nonce) {
        super();
        this.nonce = nonce;
    }
    public OtpVO() {
        super();
    }


    private int overCount;
    
    private String overCase;
    
    private String creator;
    
    @DateTimeFormat(pattern="MM/dd/yyyy HH:mm:ss")
    private Date created;  
    
    private String modifier;
    
    @DateTimeFormat(pattern="MM/dd/yyyy HH:mm:ss")
    private Date modified;  
    

    /**
     * 
     * 
     * @return the nonce
     */
    public String getNonce(){
        return nonce;
    }

    
    
    /**
     * 
     * 
     * @return the overCase
     */
    public String getOverCase(){
        return overCase;
    }


    
    /**
     * 
     * 
     * @param overCase the overCase to set
     */
    public void setOverCase(String overCase){
        this.overCase = overCase;
    }


    /**
     * 
     * 
     * @param nonce the nonce to set
     */
    public void setNonce(String nonce){
        this.nonce = nonce;
    }

    
   
    
    /**
     * 
     * 
     * @return the usedCount
     */
    public int getUsedCount(){
        return usedCount;
    }

    
    /**
     * 
     * 
     * @param usedCount the usedCount to set
     */
    public void setUsedCount(int usedCount){
        this.usedCount = usedCount;
    }

  
    
    /**
     * 
     * 
     * @return the usedTime
     */
    public Date getUsedTime(){
        return usedTime;
    }

    
    /**
     * 
     * 
     * @param usedTime the usedTime to set
     */
    public void setUsedTime(Date usedTime){
        this.usedTime = usedTime;
    }



    /**
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "OtpVO [nonce=" + nonce + ", created=" + getCreated() + ", usedCount=" + usedCount + ", remoteAddr=" + remoteAddr
                + ", usedTime=" + usedTime + ", overCase=" + overCase + ", toString()=" + super.toString() + "]";
    }



    /**
     * 
     * 
     * @return the remoteAddr
     */
    public String getRemoteAddr(){
        return remoteAddr;
    }



    /**
     * 
     * 
     * @param remoteAddr the remoteAddr to set
     */
    public void setRemoteAddr(String remoteAddr){
        this.remoteAddr = remoteAddr;
    }



    /**
     * 
     * 
     * @return the overCount
     */
    public int getOverCount(){
        return overCount;
    }



    /**
     * 
     * 
     * @param overCount the overCount to set
     */
    public void setOverCount(int overCount){
        this.overCount = overCount;
    }



    /**
     * 
     * 
     * @return the creator
     */
    public String getCreator(){
        return creator;
    }



    /**
     * 
     * 
     * @param creator the creator to set
     */
    public void setCreator(String creator){
        this.creator = creator;
    }



    /**
     * 
     * 
     * @return the modifier
     */
    public String getModifier(){
        return modifier;
    }



    /**
     * 
     * 
     * @param modifier the modifier to set
     */
    public void setModifier(String modifier){
        this.modifier = modifier;
    }



    /**
     * 
     * 
     * @return the created
     */
    public Date getCreated(){
        return created;
    }



    /**
     * 
     * 
     * @param created the created to set
     */
    public void setCreated(Date created){
        this.created = created;
    }



    /**
     * 
     * 
     * @return the modified
     */
    public Date getModified(){
        return modified;
    }



    /**
     * 
     * 
     * @param modified the modified to set
     */
    public void setModified(Date modified){
        this.modified = modified;
    }



    


}
