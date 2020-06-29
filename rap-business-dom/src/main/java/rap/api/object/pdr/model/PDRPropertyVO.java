/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PDRPropertyVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import rap.api.object.pdr.model.PDRMastersVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PDRPropertyVO extends PDRMastersVO {
    private String        prTypeCode                                        ;
    private String        productionTypeCode                                ;
    private String        productionTypeName                                ;
    private String        urgencyFlag                                       ;
    private String        gradeCode                                         ;
    private String        countryName                                       ;
    private String        brandTypeName                                     ;
    private String        shipToCustomerCode                                ;
    private String        shipToCustomerName                                ;
    private String        billToCustomerCode                                ;
    private String        billToCustomerName                                ;
    private String        kanEanUpcCode                                     ;
    private String        prFirstProductionYyyymmdd                         ;
    private String        prShipmentYyyymmdd                                ;
    private String        productionRegionCode                              ;
    private String        subsidiaryName                                    ;
    private String        registerEmployeeNo                                ;
    private Date          registerDate                                      ;
    private String        delayFlag                                         ;
    private String        delayReasonCode                                   ;
    private String        delayDesc                                         ;
    private String        registerPossibleYyyymmdd                          ;
    private String        approvalRequestEmployeeNo                         ;
    private Date          prEndDate                                         ;
    private String        statusCode                                        ;
    private String        holdFlag                                          ;
    private String        holdReasonCode                                    ;
    private String        holdComment                                       ;
    private Date          holdDate                                          ;
    private String        holdEmployeeNo                                    ;
    private String        latestFlag                                        ;
    private String        deleteFlag                                        ;
    private Date          bomCreationDate                                   ;
    private Date          erpInterfaceDate                                  ;
    private String        erpProductionYyyymmdd                             ;
    private String        attachFileId                                      ;
    private String        migrationFlag                                     ;
    private String        legacyPrCode                                      ;
    private String        toolName                                          ;
    private String        applyDate                                         ;
    private String        productionSchdYyyymmdd                            ;
    private String        modelDesc                                         ;
    private String        previousModelCode                                 ;
    private String        previousSuffixCode                                ;
    private String        copyPdrId                                         ;
    private String        countryCode                                       ;
    private String        disabledCode                                      ;
    private String        customerSuffixValueCode                           ;
    private String        customerSuffixValueName                           ;
    private String        modelValueCode                                    ;
    private String        productionRegionValueCode                         ;
    private String        toolValueCode                                     ;
    private String        pdmIfFlag                                         ;
    private String        priPcoFlag                                        ;
    private String        gscpIfFlag                                        ;
    private String        projectCode                                       ;
    private String        salesModelName                                    ;
    private String        salesModelSuffixCode                              ;
    private String        requestReviewDate                                 ;
    private String        preMp                                             ;
    private String        subsidiaryCode                                    ;
    private String        standardflag                                      ;
    private String        developmentrequest                                ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        attribute6                                        ;
    private String        attribute7                                        ;
    private String        attribute8                                        ;
    private String        attribute9                                        ;
    private String        attribute10                                       ;
    private String        temporarypdrflag                                   = "N";
    private Integer       pdrId                                             ;
    private String        modelCode                                         ;
    private String        suffixCode                                        ;
    private Date          gripExpireDate                                    ;
    private String        gripValidFlag                                     ;
    private String        recoveryComment                                   ;
    private String        customerModelName                                 ;
    private Date          lastApprovalDate                                  ;
    private String        productionRegionName                              ;
    private String        rejectFlag                                        ;
    private String        customerSuffixCode                                ;
    private Integer       salesTargetMonth                                   = 12;
    private String        divisionCode                                      ;
    private String        fromObid                                          ;
    private String        prmCodeNew                                        ;
    private String        prmSheetId                                        ;


    public void    setPrTypeCode(String prTypeCode){
        this.prTypeCode = prTypeCode;
    }
    public void    setProductionTypeCode(String productionTypeCode){
        this.productionTypeCode = productionTypeCode;
    }
    public void    setProductionTypeName(String productionTypeName){
        this.productionTypeName = productionTypeName;
    }
    public void    setUrgencyFlag(String urgencyFlag){
        this.urgencyFlag = urgencyFlag;
    }
    public void    setGradeCode(String gradeCode){
        this.gradeCode = gradeCode;
    }
    public void    setCountryName(String countryName){
        this.countryName = countryName;
    }
    public void    setBrandTypeName(String brandTypeName){
        this.brandTypeName = brandTypeName;
    }
    public void    setShipToCustomerCode(String shipToCustomerCode){
        this.shipToCustomerCode = shipToCustomerCode;
    }
    public void    setShipToCustomerName(String shipToCustomerName){
        this.shipToCustomerName = shipToCustomerName;
    }
    public void    setBillToCustomerCode(String billToCustomerCode){
        this.billToCustomerCode = billToCustomerCode;
    }
    public void    setBillToCustomerName(String billToCustomerName){
        this.billToCustomerName = billToCustomerName;
    }
    public void    setKanEanUpcCode(String kanEanUpcCode){
        this.kanEanUpcCode = kanEanUpcCode;
    }
    public void    setPrFirstProductionYyyymmdd(String prFirstProductionYyyymmdd){
        this.prFirstProductionYyyymmdd = prFirstProductionYyyymmdd;
    }
    public void    setPrShipmentYyyymmdd(String prShipmentYyyymmdd){
        this.prShipmentYyyymmdd = prShipmentYyyymmdd;
    }
    public void    setProductionRegionCode(String productionRegionCode){
        this.productionRegionCode = productionRegionCode;
    }
    public void    setSubsidiaryName(String subsidiaryName){
        this.subsidiaryName = subsidiaryName;
    }
    public void    setRegisterEmployeeNo(String registerEmployeeNo){
        this.registerEmployeeNo = registerEmployeeNo;
    }
    public void    setRegisterDate(Date registerDate){
        this.registerDate = registerDate;
    }
    public void    setRegisterDate(String    registerDate){
        this.registerDate = this.omcConvertStr2Date(registerDate);
    }
    public void    setDelayFlag(String delayFlag){
        this.delayFlag = delayFlag;
    }
    public void    setDelayReasonCode(String delayReasonCode){
        this.delayReasonCode = delayReasonCode;
    }
    public void    setDelayDesc(String delayDesc){
        this.delayDesc = delayDesc;
    }
    public void    setRegisterPossibleYyyymmdd(String registerPossibleYyyymmdd){
        this.registerPossibleYyyymmdd = registerPossibleYyyymmdd;
    }
    public void    setApprovalRequestEmployeeNo(String approvalRequestEmployeeNo){
        this.approvalRequestEmployeeNo = approvalRequestEmployeeNo;
    }
    public void    setPrEndDate(Date prEndDate){
        this.prEndDate = prEndDate;
    }
    public void    setPrEndDate(String    prEndDate){
        this.prEndDate = this.omcConvertStr2Date(prEndDate);
    }
    public void    setStatusCode(String statusCode){
        this.statusCode = statusCode;
    }
    public void    setHoldFlag(String holdFlag){
        this.holdFlag = holdFlag;
    }
    public void    setHoldReasonCode(String holdReasonCode){
        this.holdReasonCode = holdReasonCode;
    }
    public void    setHoldComment(String holdComment){
        this.holdComment = holdComment;
    }
    public void    setHoldDate(Date holdDate){
        this.holdDate = holdDate;
    }
    public void    setHoldDate(String    holdDate){
        this.holdDate = this.omcConvertStr2Date(holdDate);
    }
    public void    setHoldEmployeeNo(String holdEmployeeNo){
        this.holdEmployeeNo = holdEmployeeNo;
    }
    public void    setLatestFlag(String latestFlag){
        this.latestFlag = latestFlag;
    }
    public void    setDeleteFlag(String deleteFlag){
        this.deleteFlag = deleteFlag;
    }
    public void    setBomCreationDate(Date bomCreationDate){
        this.bomCreationDate = bomCreationDate;
    }
    public void    setBomCreationDate(String    bomCreationDate){
        this.bomCreationDate = this.omcConvertStr2Date(bomCreationDate);
    }
    public void    setErpInterfaceDate(Date erpInterfaceDate){
        this.erpInterfaceDate = erpInterfaceDate;
    }
    public void    setErpInterfaceDate(String    erpInterfaceDate){
        this.erpInterfaceDate = this.omcConvertStr2Date(erpInterfaceDate);
    }
    public void    setErpProductionYyyymmdd(String erpProductionYyyymmdd){
        this.erpProductionYyyymmdd = erpProductionYyyymmdd;
    }
    public void    setAttachFileId(String attachFileId){
        this.attachFileId = attachFileId;
    }
    public void    setMigrationFlag(String migrationFlag){
        this.migrationFlag = migrationFlag;
    }
    public void    setLegacyPrCode(String legacyPrCode){
        this.legacyPrCode = legacyPrCode;
    }
    public void    setToolName(String toolName){
        this.toolName = toolName;
    }
    public void    setApplyDate(String applyDate){
        this.applyDate = applyDate;
    }
    public void    setProductionSchdYyyymmdd(String productionSchdYyyymmdd){
        this.productionSchdYyyymmdd = productionSchdYyyymmdd;
    }
    public void    setModelDesc(String modelDesc){
        this.modelDesc = modelDesc;
    }
    public void    setPreviousModelCode(String previousModelCode){
        this.previousModelCode = previousModelCode;
    }
    public void    setPreviousSuffixCode(String previousSuffixCode){
        this.previousSuffixCode = previousSuffixCode;
    }
    public void    setCopyPdrId(String copyPdrId){
        this.copyPdrId = copyPdrId;
    }
    public void    setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public void    setDisabledCode(String disabledCode){
        this.disabledCode = disabledCode;
    }
    public void    setCustomerSuffixValueCode(String customerSuffixValueCode){
        this.customerSuffixValueCode = customerSuffixValueCode;
    }
    public void    setCustomerSuffixValueName(String customerSuffixValueName){
        this.customerSuffixValueName = customerSuffixValueName;
    }
    public void    setModelValueCode(String modelValueCode){
        this.modelValueCode = modelValueCode;
    }
    public void    setProductionRegionValueCode(String productionRegionValueCode){
        this.productionRegionValueCode = productionRegionValueCode;
    }
    public void    setToolValueCode(String toolValueCode){
        this.toolValueCode = toolValueCode;
    }
    public void    setPdmIfFlag(String pdmIfFlag){
        this.pdmIfFlag = pdmIfFlag;
    }
    public void    setPriPcoFlag(String priPcoFlag){
        this.priPcoFlag = priPcoFlag;
    }
    public void    setGscpIfFlag(String gscpIfFlag){
        this.gscpIfFlag = gscpIfFlag;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setSalesModelName(String salesModelName){
        this.salesModelName = salesModelName;
    }
    public void    setSalesModelSuffixCode(String salesModelSuffixCode){
        this.salesModelSuffixCode = salesModelSuffixCode;
    }
    public void    setRequestReviewDate(String requestReviewDate){
        this.requestReviewDate = requestReviewDate;
    }
    public void    setPreMp(String preMp){
        this.preMp = preMp;
    }
    public void    setSubsidiaryCode(String subsidiaryCode){
        this.subsidiaryCode = subsidiaryCode;
    }
    public void    setStandardflag(String standardflag){
        this.standardflag = standardflag;
    }
    public void    setDevelopmentrequest(String developmentrequest){
        this.developmentrequest = developmentrequest;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public void    setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
    public void    setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
    public void    setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }
    public void    setAttribute6(String attribute6){
        this.attribute6 = attribute6;
    }
    public void    setAttribute7(String attribute7){
        this.attribute7 = attribute7;
    }
    public void    setAttribute8(String attribute8){
        this.attribute8 = attribute8;
    }
    public void    setAttribute9(String attribute9){
        this.attribute9 = attribute9;
    }
    public void    setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    public void    setTemporarypdrflag(String temporarypdrflag){
        this.temporarypdrflag = temporarypdrflag;
    }
    public void    setPdrId(Integer pdrId){
        this.pdrId = pdrId;
    }
    public void    setModelCode(String modelCode){
        this.modelCode = modelCode;
    }
    public void    setSuffixCode(String suffixCode){
        this.suffixCode = suffixCode;
    }
    public void    setGripExpireDate(Date gripExpireDate){
        this.gripExpireDate = gripExpireDate;
    }
    public void    setGripExpireDate(String    gripExpireDate){
        this.gripExpireDate = this.omcConvertStr2Date(gripExpireDate);
    }
    public void    setGripValidFlag(String gripValidFlag){
        this.gripValidFlag = gripValidFlag;
    }
    public void    setRecoveryComment(String recoveryComment){
        this.recoveryComment = recoveryComment;
    }
    public void    setCustomerModelName(String customerModelName){
        this.customerModelName = customerModelName;
    }
    public void    setLastApprovalDate(Date lastApprovalDate){
        this.lastApprovalDate = lastApprovalDate;
    }
    public void    setLastApprovalDate(String    lastApprovalDate){
        this.lastApprovalDate = this.omcConvertStr2Date(lastApprovalDate);
    }
    public void    setProductionRegionName(String productionRegionName){
        this.productionRegionName = productionRegionName;
    }
    public void    setRejectFlag(String rejectFlag){
        this.rejectFlag = rejectFlag;
    }
    public void    setCustomerSuffixCode(String customerSuffixCode){
        this.customerSuffixCode = customerSuffixCode;
    }
    public void    setSalesTargetMonth(Integer salesTargetMonth){
        this.salesTargetMonth = salesTargetMonth;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setPrmCodeNew(String prmCodeNew){
        this.prmCodeNew = prmCodeNew;
    }
    public void    setPrmSheetId(String prmSheetId){
        this.prmSheetId = prmSheetId;
    }
    public String getPrTypeCode(){
        return prTypeCode;
    }
    public String getProductionTypeCode(){
        return productionTypeCode;
    }
    public String getProductionTypeName(){
        return productionTypeName;
    }
    public String getUrgencyFlag(){
        return urgencyFlag;
    }
    public String getGradeCode(){
        return gradeCode;
    }
    public String getCountryName(){
        return countryName;
    }
    public String getBrandTypeName(){
        return brandTypeName;
    }
    public String getShipToCustomerCode(){
        return shipToCustomerCode;
    }
    public String getShipToCustomerName(){
        return shipToCustomerName;
    }
    public String getBillToCustomerCode(){
        return billToCustomerCode;
    }
    public String getBillToCustomerName(){
        return billToCustomerName;
    }
    public String getKanEanUpcCode(){
        return kanEanUpcCode;
    }
    public String getPrFirstProductionYyyymmdd(){
        return prFirstProductionYyyymmdd;
    }
    public String getPrShipmentYyyymmdd(){
        return prShipmentYyyymmdd;
    }
    public String getProductionRegionCode(){
        return productionRegionCode;
    }
    public String getSubsidiaryName(){
        return subsidiaryName;
    }
    public String getRegisterEmployeeNo(){
        return registerEmployeeNo;
    }
    public Date getRegisterDate(){
        return registerDate;
    }
    public String getDelayFlag(){
        return delayFlag;
    }
    public String getDelayReasonCode(){
        return delayReasonCode;
    }
    public String getDelayDesc(){
        return delayDesc;
    }
    public String getRegisterPossibleYyyymmdd(){
        return registerPossibleYyyymmdd;
    }
    public String getApprovalRequestEmployeeNo(){
        return approvalRequestEmployeeNo;
    }
    public Date getPrEndDate(){
        return prEndDate;
    }
    public String getStatusCode(){
        return statusCode;
    }
    public String getHoldFlag(){
        return holdFlag;
    }
    public String getHoldReasonCode(){
        return holdReasonCode;
    }
    public String getHoldComment(){
        return holdComment;
    }
    public Date getHoldDate(){
        return holdDate;
    }
    public String getHoldEmployeeNo(){
        return holdEmployeeNo;
    }
    public String getLatestFlag(){
        return latestFlag;
    }
    public String getDeleteFlag(){
        return deleteFlag;
    }
    public Date getBomCreationDate(){
        return bomCreationDate;
    }
    public Date getErpInterfaceDate(){
        return erpInterfaceDate;
    }
    public String getErpProductionYyyymmdd(){
        return erpProductionYyyymmdd;
    }
    public String getAttachFileId(){
        return attachFileId;
    }
    public String getMigrationFlag(){
        return migrationFlag;
    }
    public String getLegacyPrCode(){
        return legacyPrCode;
    }
    public String getToolName(){
        return toolName;
    }
    public String getApplyDate(){
        return applyDate;
    }
    public String getProductionSchdYyyymmdd(){
        return productionSchdYyyymmdd;
    }
    public String getModelDesc(){
        return modelDesc;
    }
    public String getPreviousModelCode(){
        return previousModelCode;
    }
    public String getPreviousSuffixCode(){
        return previousSuffixCode;
    }
    public String getCopyPdrId(){
        return copyPdrId;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public String getDisabledCode(){
        return disabledCode;
    }
    public String getCustomerSuffixValueCode(){
        return customerSuffixValueCode;
    }
    public String getCustomerSuffixValueName(){
        return customerSuffixValueName;
    }
    public String getModelValueCode(){
        return modelValueCode;
    }
    public String getProductionRegionValueCode(){
        return productionRegionValueCode;
    }
    public String getToolValueCode(){
        return toolValueCode;
    }
    public String getPdmIfFlag(){
        return pdmIfFlag;
    }
    public String getPriPcoFlag(){
        return priPcoFlag;
    }
    public String getGscpIfFlag(){
        return gscpIfFlag;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getSalesModelName(){
        return salesModelName;
    }
    public String getSalesModelSuffixCode(){
        return salesModelSuffixCode;
    }
    public String getRequestReviewDate(){
        return requestReviewDate;
    }
    public String getPreMp(){
        return preMp;
    }
    public String getSubsidiaryCode(){
        return subsidiaryCode;
    }
    public String getStandardflag(){
        return standardflag;
    }
    public String getDevelopmentrequest(){
        return developmentrequest;
    }
    public String getAttribute1(){
        return attribute1;
    }
    public String getAttribute2(){
        return attribute2;
    }
    public String getAttribute3(){
        return attribute3;
    }
    public String getAttribute4(){
        return attribute4;
    }
    public String getAttribute5(){
        return attribute5;
    }
    public String getAttribute6(){
        return attribute6;
    }
    public String getAttribute7(){
        return attribute7;
    }
    public String getAttribute8(){
        return attribute8;
    }
    public String getAttribute9(){
        return attribute9;
    }
    public String getAttribute10(){
        return attribute10;
    }
    public String getTemporarypdrflag(){
        return temporarypdrflag;
    }
    public Integer getPdrId(){
        return pdrId;
    }
    public String getModelCode(){
        return modelCode;
    }
    public String getSuffixCode(){
        return suffixCode;
    }
    public Date getGripExpireDate(){
        return gripExpireDate;
    }
    public String getGripValidFlag(){
        return gripValidFlag;
    }
    public String getRecoveryComment(){
        return recoveryComment;
    }
    public String getCustomerModelName(){
        return customerModelName;
    }
    public Date getLastApprovalDate(){
        return lastApprovalDate;
    }
    public String getProductionRegionName(){
        return productionRegionName;
    }
    public String getRejectFlag(){
        return rejectFlag;
    }
    public String getCustomerSuffixCode(){
        return customerSuffixCode;
    }
    public Integer getSalesTargetMonth(){
        return salesTargetMonth;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getPrmCodeNew(){
        return prmCodeNew;
    }
    public String getPrmSheetId(){
        return prmSheetId;
    }
}

