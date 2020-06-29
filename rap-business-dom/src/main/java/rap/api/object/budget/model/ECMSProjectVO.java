/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECMSProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ECMSProjectVO extends BusinessObjectMasterVO {
    private String        ecmsProjectCode                                   ;
    private String        ecmsProjectName                                   ;
    private String        version                                           ;
    private String        beforeVersion                                     ;
    private Date          versionCreateDate                                 ;
    private Date          versionEndDate                                    ;
    private String        versionStatus                                     ;
    private String        refYearlyExchangeVersion                          ;
    private String        approveComment                                    ;
    private String        devEvent                                          ;
    private String        auCode                                            ;
    private String        auName                                            ;
    private String        activityStatus                                    ;
    private String        supStartYyyymm                                    ;
    private String        supEndYyyymm                                      ;
    private String        devStartYyyymm                                    ;
    private String        devEndYyyymm                                      ;
    private String        personInChargeMain                                ;
    private String        personInChargeSub                                 ;
    private String        personInCharge3rd                                 ;
    private String        personInCharge4th                                 ;
    private String        reviewerMain                                      ;
    private String        reviewerSub                                       ;
    private String        reviewer3rd                                       ;
    private String        reviewer4th                                       ;
    private String        rfqPmsProjectObid                                 ;
    private String        customerName                                      ;
    private String        customerCode                                      ;


    public void    setEcmsProjectCode(String ecmsProjectCode){
        this.ecmsProjectCode = ecmsProjectCode;
    }
    public void    setEcmsProjectName(String ecmsProjectName){
        this.ecmsProjectName = ecmsProjectName;
    }
    public void    setVersion(String version){
        this.version = version;
    }
    public void    setBeforeVersion(String beforeVersion){
        this.beforeVersion = beforeVersion;
    }
    public void    setVersionCreateDate(Date versionCreateDate){
        this.versionCreateDate = versionCreateDate;
    }
    public void    setVersionCreateDate(String    versionCreateDate){
        this.versionCreateDate = this.omcConvertStr2Date(versionCreateDate);
    }
    public void    setVersionEndDate(Date versionEndDate){
        this.versionEndDate = versionEndDate;
    }
    public void    setVersionEndDate(String    versionEndDate){
        this.versionEndDate = this.omcConvertStr2Date(versionEndDate);
    }
    public void    setVersionStatus(String versionStatus){
        this.versionStatus = versionStatus;
    }
    public void    setRefYearlyExchangeVersion(String refYearlyExchangeVersion){
        this.refYearlyExchangeVersion = refYearlyExchangeVersion;
    }
    public void    setApproveComment(String approveComment){
        this.approveComment = approveComment;
    }
    public void    setDevEvent(String devEvent){
        this.devEvent = devEvent;
    }
    public void    setAuCode(String auCode){
        this.auCode = auCode;
    }
    public void    setAuName(String auName){
        this.auName = auName;
    }
    public void    setActivityStatus(String activityStatus){
        this.activityStatus = activityStatus;
    }
    public void    setSupStartYyyymm(String supStartYyyymm){
        this.supStartYyyymm = supStartYyyymm;
    }
    public void    setSupEndYyyymm(String supEndYyyymm){
        this.supEndYyyymm = supEndYyyymm;
    }
    public void    setDevStartYyyymm(String devStartYyyymm){
        this.devStartYyyymm = devStartYyyymm;
    }
    public void    setDevEndYyyymm(String devEndYyyymm){
        this.devEndYyyymm = devEndYyyymm;
    }
    public void    setPersonInChargeMain(String personInChargeMain){
        this.personInChargeMain = personInChargeMain;
    }
    public void    setPersonInChargeSub(String personInChargeSub){
        this.personInChargeSub = personInChargeSub;
    }
    public void    setPersonInCharge3rd(String personInCharge3rd){
        this.personInCharge3rd = personInCharge3rd;
    }
    public void    setPersonInCharge4th(String personInCharge4th){
        this.personInCharge4th = personInCharge4th;
    }
    public void    setReviewerMain(String reviewerMain){
        this.reviewerMain = reviewerMain;
    }
    public void    setReviewerSub(String reviewerSub){
        this.reviewerSub = reviewerSub;
    }
    public void    setReviewer3rd(String reviewer3rd){
        this.reviewer3rd = reviewer3rd;
    }
    public void    setReviewer4th(String reviewer4th){
        this.reviewer4th = reviewer4th;
    }
    public void    setRfqPmsProjectObid(String rfqPmsProjectObid){
        this.rfqPmsProjectObid = rfqPmsProjectObid;
    }
    public void    setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public void    setCustomerCode(String customerCode){
        this.customerCode = customerCode;
    }
    public String getEcmsProjectCode(){
        return ecmsProjectCode;
    }
    public String getEcmsProjectName(){
        return ecmsProjectName;
    }
    public String getVersion(){
        return version;
    }
    public String getBeforeVersion(){
        return beforeVersion;
    }
    public Date getVersionCreateDate(){
        return versionCreateDate;
    }
    public Date getVersionEndDate(){
        return versionEndDate;
    }
    public String getVersionStatus(){
        return versionStatus;
    }
    public String getRefYearlyExchangeVersion(){
        return refYearlyExchangeVersion;
    }
    public String getApproveComment(){
        return approveComment;
    }
    public String getDevEvent(){
        return devEvent;
    }
    public String getAuCode(){
        return auCode;
    }
    public String getAuName(){
        return auName;
    }
    public String getActivityStatus(){
        return activityStatus;
    }
    public String getSupStartYyyymm(){
        return supStartYyyymm;
    }
    public String getSupEndYyyymm(){
        return supEndYyyymm;
    }
    public String getDevStartYyyymm(){
        return devStartYyyymm;
    }
    public String getDevEndYyyymm(){
        return devEndYyyymm;
    }
    public String getPersonInChargeMain(){
        return personInChargeMain;
    }
    public String getPersonInChargeSub(){
        return personInChargeSub;
    }
    public String getPersonInCharge3rd(){
        return personInCharge3rd;
    }
    public String getPersonInCharge4th(){
        return personInCharge4th;
    }
    public String getReviewerMain(){
        return reviewerMain;
    }
    public String getReviewerSub(){
        return reviewerSub;
    }
    public String getReviewer3rd(){
        return reviewer3rd;
    }
    public String getReviewer4th(){
        return reviewer4th;
    }
    public String getRfqPmsProjectObid(){
        return rfqPmsProjectObid;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getCustomerCode(){
        return customerCode;
    }
}

