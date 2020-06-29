/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : TestInfoVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class TestInfoVO extends BusinessObjectMasterVO {
    private String        reqNo                                             ;
    private String        divisionCode                                      ;
    private String        affiliateCode                                     ;
    private String        modelName                                         ;
    private String        partNo                                            ;
    private String        testType                                          ;
    private String        testTypeName                                      ;
    private String        vendorSiteId                                      ;
    private String        vendorSiteCode                                    ;
    private Float         moldNo                                            ;
    private Integer       testSeq                                           ;
    private String        reqStatus                                         ;
    private String        reqStatusName                                     ;
    private Date          testReqDate                                       ;
    private String        reqUser                                           ;
    private Date          lastApprovalDate                                  ;
    private String        judgment                                          ;
    private String        testUser                                          ;


    public void    setReqNo(String reqNo){
        this.reqNo = reqNo;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setTestType(String testType){
        this.testType = testType;
    }
    public void    setTestTypeName(String testTypeName){
        this.testTypeName = testTypeName;
    }
    public void    setVendorSiteId(String vendorSiteId){
        this.vendorSiteId = vendorSiteId;
    }
    public void    setVendorSiteCode(String vendorSiteCode){
        this.vendorSiteCode = vendorSiteCode;
    }
    public void    setMoldNo(Float moldNo){
        this.moldNo = moldNo;
    }
    public void    setTestSeq(Integer testSeq){
        this.testSeq = testSeq;
    }
    public void    setReqStatus(String reqStatus){
        this.reqStatus = reqStatus;
    }
    public void    setReqStatusName(String reqStatusName){
        this.reqStatusName = reqStatusName;
    }
    public void    setTestReqDate(Date testReqDate){
        this.testReqDate = testReqDate;
    }
    public void    setTestReqDate(String    testReqDate){
        this.testReqDate = this.omcConvertStr2Date(testReqDate);
    }
    public void    setReqUser(String reqUser){
        this.reqUser = reqUser;
    }
    public void    setLastApprovalDate(Date lastApprovalDate){
        this.lastApprovalDate = lastApprovalDate;
    }
    public void    setLastApprovalDate(String    lastApprovalDate){
        this.lastApprovalDate = this.omcConvertStr2Date(lastApprovalDate);
    }
    public void    setJudgment(String judgment){
        this.judgment = judgment;
    }
    public void    setTestUser(String testUser){
        this.testUser = testUser;
    }
    public String getReqNo(){
        return reqNo;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public String getModelName(){
        return modelName;
    }
    public String getPartNo(){
        return partNo;
    }
    public String getTestType(){
        return testType;
    }
    public String getTestTypeName(){
        return testTypeName;
    }
    public String getVendorSiteId(){
        return vendorSiteId;
    }
    public String getVendorSiteCode(){
        return vendorSiteCode;
    }
    public Float getMoldNo(){
        return moldNo;
    }
    public Integer getTestSeq(){
        return testSeq;
    }
    public String getReqStatus(){
        return reqStatus;
    }
    public String getReqStatusName(){
        return reqStatusName;
    }
    public Date getTestReqDate(){
        return testReqDate;
    }
    public String getReqUser(){
        return reqUser;
    }
    public Date getLastApprovalDate(){
        return lastApprovalDate;
    }
    public String getJudgment(){
        return judgment;
    }
    public String getTestUser(){
        return testUser;
    }
}

