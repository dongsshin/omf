/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : IfGpdmifInMasterVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 18.  youngmi.won   Initial
 * ===========================================
 */
package com.rap.omc.intf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * Class : IfGpdmDataByTriggerVO
 * Description : TODO
 * </pre>
 * 
 * @author youngmi.won
 */
public class IfPlmDataByTriggerVO {
    private String objectId;
    private Integer seq;
    private String objectType;
    private String objectName;
    private String objectRevision;
    private String currentStatus;
    private String userId;
    private Date insertDate;
    private String targetSystem;
    private String interfaceId;
    private String processYn = "N";
    private Date processStartDate;
    private Date processEndDate;
    private String result;
    private String resultMsg;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectRevision() {
        return objectRevision;
    }

    public void setObjectRevision(String objectRevision) {
        this.objectRevision = objectRevision;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getTargetSystem() {
        return targetSystem;
    }

    public void setTargetSystem(String targetSystem) {
        this.targetSystem = targetSystem;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getProcessYn() {
        return processYn;
    }

    public void setProcessYn(String processYn) {
        this.processYn = processYn;
    }

    public Date getProcessStartDate() {
        return processStartDate;
    }

    public void setProcessStartDate(Date processStartDate) {
        this.processStartDate = processStartDate;
    }

    public Date getProcessEndDate() {
        return processEndDate;
    }

    public void setProcessEndDate(Date processEndDate) {
        this.processEndDate = processEndDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }
    
    
    public void setInsertDate(String insertDate) throws ParseException { StringBuffer format = new StringBuffer("yyyy-MM-dd"); if(insertDate.length() == 19){ format.append(" HH:mm:ss"); }else if(insertDate.length() == 16){ format.append(" HH:mm"); } SimpleDateFormat transFormat = new SimpleDateFormat(format.toString()); this.insertDate= transFormat.parse(insertDate);}
    public void setProcessStartDate(String processStartDate) throws ParseException { StringBuffer format = new StringBuffer("yyyy-MM-dd"); if(processStartDate.length() == 19){ format.append(" HH:mm:ss"); }else if(processStartDate.length() == 16){ format.append(" HH:mm"); } SimpleDateFormat transFormat = new SimpleDateFormat(format.toString()); this.processStartDate= transFormat.parse(processStartDate);}
    public void setProcessEndDate(String processEndDate) throws ParseException { StringBuffer format = new StringBuffer("yyyy-MM-dd"); if(processEndDate.length() == 19){ format.append(" HH:mm:ss"); }else if(processEndDate.length() == 16){ format.append(" HH:mm"); } SimpleDateFormat transFormat = new SimpleDateFormat(format.toString()); this.processEndDate= transFormat.parse(processEndDate);}

}
