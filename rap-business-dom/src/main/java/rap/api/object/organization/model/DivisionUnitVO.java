/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DivisionUnitVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.model;


import rap.api.object.organization.model.OrganizationsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DivisionUnitVO extends OrganizationsVO {
    private String        businessUnitCode                                  ;
    private String        divisionCode                                      ;
    private String        reportingType                                      = "N";
    private Integer       sequences                                          = 1;
    private String        interfaceTarget                                   ;
    private String        mmCheckFlag                                        = "N";
    private String        mmMailFlag                                         = "N";
    private String        mdmsDivisionCode                                  ;
    private String        mmInputType                                        = "MM";
    private String        subsidiaryCode                                    ;
    private String        dashboardReportType                                = "N";
    private Integer       orderValue                                        ;
    private String        variUseType                                       ;
    private String        useFlag                                           ;
    private String        urlAddr                                           ;
    private String        emergencyRequestFlag                              ;
    private String        customerSuffixUseFlag                             ;
    private String        reviewListUseFlag                                 ;
    private String        platformUseFlag                                   ;
    private String        pdrCopyUseFlag                                    ;
    private String        approvalLineAutoSetFlag                           ;
    private String        rejectFlag                                        ;
    private String        pcoApplyDateUseFlag                               ;
    private String        approvalButtonDisplayFlag                         ;
    private String        reviewButtonDisplayFlag                           ;
    private String        customerNameMandFlag                              ;
    private String        selectItemValidationFlag                          ;
    private String        actionButtonDisplayFlag                           ;
    private Integer       delayAlertDuration                                ;
    private String        firstProdDateMandFlag                             ;
    private String        barcodeMandatoryFlag                              ;
    private String        excelUploadUseFlag                                ;
    private String        excelUploadUseType                                ;
    private String        excelUploadSampleUseFlag                          ;
    private String        pdrSearchItemCondFlag                             ;
    private String        pdmUseFlag                                        ;
    private Integer       pdrSearchExcelDownCnt                             ;
    private String        pdmPvWsCheckFlag                                  ;
    private String        productionTypeSetupFlag                           ;
    private String        pdrSearchCustsfxUseFlag                           ;
    private String        budgetYn                                          ;
    private String        gscpUseFlag                                       ;
    private String        partDmsUseFlag                                    ;


    public void    setBusinessUnitCode(String businessUnitCode){
        this.businessUnitCode = businessUnitCode;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setReportingType(String reportingType){
        this.reportingType = reportingType;
    }
    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setInterfaceTarget(String interfaceTarget){
        this.interfaceTarget = interfaceTarget;
    }
    public void    setMmCheckFlag(String mmCheckFlag){
        this.mmCheckFlag = mmCheckFlag;
    }
    public void    setMmMailFlag(String mmMailFlag){
        this.mmMailFlag = mmMailFlag;
    }
    public void    setMdmsDivisionCode(String mdmsDivisionCode){
        this.mdmsDivisionCode = mdmsDivisionCode;
    }
    public void    setMmInputType(String mmInputType){
        this.mmInputType = mmInputType;
    }
    public void    setSubsidiaryCode(String subsidiaryCode){
        this.subsidiaryCode = subsidiaryCode;
    }
    public void    setDashboardReportType(String dashboardReportType){
        this.dashboardReportType = dashboardReportType;
    }
    public void    setOrderValue(Integer orderValue){
        this.orderValue = orderValue;
    }
    public void    setVariUseType(String variUseType){
        this.variUseType = variUseType;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setUrlAddr(String urlAddr){
        this.urlAddr = urlAddr;
    }
    public void    setEmergencyRequestFlag(String emergencyRequestFlag){
        this.emergencyRequestFlag = emergencyRequestFlag;
    }
    public void    setCustomerSuffixUseFlag(String customerSuffixUseFlag){
        this.customerSuffixUseFlag = customerSuffixUseFlag;
    }
    public void    setReviewListUseFlag(String reviewListUseFlag){
        this.reviewListUseFlag = reviewListUseFlag;
    }
    public void    setPlatformUseFlag(String platformUseFlag){
        this.platformUseFlag = platformUseFlag;
    }
    public void    setPdrCopyUseFlag(String pdrCopyUseFlag){
        this.pdrCopyUseFlag = pdrCopyUseFlag;
    }
    public void    setApprovalLineAutoSetFlag(String approvalLineAutoSetFlag){
        this.approvalLineAutoSetFlag = approvalLineAutoSetFlag;
    }
    public void    setRejectFlag(String rejectFlag){
        this.rejectFlag = rejectFlag;
    }
    public void    setPcoApplyDateUseFlag(String pcoApplyDateUseFlag){
        this.pcoApplyDateUseFlag = pcoApplyDateUseFlag;
    }
    public void    setApprovalButtonDisplayFlag(String approvalButtonDisplayFlag){
        this.approvalButtonDisplayFlag = approvalButtonDisplayFlag;
    }
    public void    setReviewButtonDisplayFlag(String reviewButtonDisplayFlag){
        this.reviewButtonDisplayFlag = reviewButtonDisplayFlag;
    }
    public void    setCustomerNameMandFlag(String customerNameMandFlag){
        this.customerNameMandFlag = customerNameMandFlag;
    }
    public void    setSelectItemValidationFlag(String selectItemValidationFlag){
        this.selectItemValidationFlag = selectItemValidationFlag;
    }
    public void    setActionButtonDisplayFlag(String actionButtonDisplayFlag){
        this.actionButtonDisplayFlag = actionButtonDisplayFlag;
    }
    public void    setDelayAlertDuration(Integer delayAlertDuration){
        this.delayAlertDuration = delayAlertDuration;
    }
    public void    setFirstProdDateMandFlag(String firstProdDateMandFlag){
        this.firstProdDateMandFlag = firstProdDateMandFlag;
    }
    public void    setBarcodeMandatoryFlag(String barcodeMandatoryFlag){
        this.barcodeMandatoryFlag = barcodeMandatoryFlag;
    }
    public void    setExcelUploadUseFlag(String excelUploadUseFlag){
        this.excelUploadUseFlag = excelUploadUseFlag;
    }
    public void    setExcelUploadUseType(String excelUploadUseType){
        this.excelUploadUseType = excelUploadUseType;
    }
    public void    setExcelUploadSampleUseFlag(String excelUploadSampleUseFlag){
        this.excelUploadSampleUseFlag = excelUploadSampleUseFlag;
    }
    public void    setPdrSearchItemCondFlag(String pdrSearchItemCondFlag){
        this.pdrSearchItemCondFlag = pdrSearchItemCondFlag;
    }
    public void    setPdmUseFlag(String pdmUseFlag){
        this.pdmUseFlag = pdmUseFlag;
    }
    public void    setPdrSearchExcelDownCnt(Integer pdrSearchExcelDownCnt){
        this.pdrSearchExcelDownCnt = pdrSearchExcelDownCnt;
    }
    public void    setPdmPvWsCheckFlag(String pdmPvWsCheckFlag){
        this.pdmPvWsCheckFlag = pdmPvWsCheckFlag;
    }
    public void    setProductionTypeSetupFlag(String productionTypeSetupFlag){
        this.productionTypeSetupFlag = productionTypeSetupFlag;
    }
    public void    setPdrSearchCustsfxUseFlag(String pdrSearchCustsfxUseFlag){
        this.pdrSearchCustsfxUseFlag = pdrSearchCustsfxUseFlag;
    }
    public void    setBudgetYn(String budgetYn){
        this.budgetYn = budgetYn;
    }
    public void    setGscpUseFlag(String gscpUseFlag){
        this.gscpUseFlag = gscpUseFlag;
    }
    public void    setPartDmsUseFlag(String partDmsUseFlag){
        this.partDmsUseFlag = partDmsUseFlag;
    }
    public String getBusinessUnitCode(){
        return businessUnitCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getReportingType(){
        return reportingType;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getInterfaceTarget(){
        return interfaceTarget;
    }
    public String getMmCheckFlag(){
        return mmCheckFlag;
    }
    public String getMmMailFlag(){
        return mmMailFlag;
    }
    public String getMdmsDivisionCode(){
        return mdmsDivisionCode;
    }
    public String getMmInputType(){
        return mmInputType;
    }
    public String getSubsidiaryCode(){
        return subsidiaryCode;
    }
    public String getDashboardReportType(){
        return dashboardReportType;
    }
    public Integer getOrderValue(){
        return orderValue;
    }
    public String getVariUseType(){
        return variUseType;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getUrlAddr(){
        return urlAddr;
    }
    public String getEmergencyRequestFlag(){
        return emergencyRequestFlag;
    }
    public String getCustomerSuffixUseFlag(){
        return customerSuffixUseFlag;
    }
    public String getReviewListUseFlag(){
        return reviewListUseFlag;
    }
    public String getPlatformUseFlag(){
        return platformUseFlag;
    }
    public String getPdrCopyUseFlag(){
        return pdrCopyUseFlag;
    }
    public String getApprovalLineAutoSetFlag(){
        return approvalLineAutoSetFlag;
    }
    public String getRejectFlag(){
        return rejectFlag;
    }
    public String getPcoApplyDateUseFlag(){
        return pcoApplyDateUseFlag;
    }
    public String getApprovalButtonDisplayFlag(){
        return approvalButtonDisplayFlag;
    }
    public String getReviewButtonDisplayFlag(){
        return reviewButtonDisplayFlag;
    }
    public String getCustomerNameMandFlag(){
        return customerNameMandFlag;
    }
    public String getSelectItemValidationFlag(){
        return selectItemValidationFlag;
    }
    public String getActionButtonDisplayFlag(){
        return actionButtonDisplayFlag;
    }
    public Integer getDelayAlertDuration(){
        return delayAlertDuration;
    }
    public String getFirstProdDateMandFlag(){
        return firstProdDateMandFlag;
    }
    public String getBarcodeMandatoryFlag(){
        return barcodeMandatoryFlag;
    }
    public String getExcelUploadUseFlag(){
        return excelUploadUseFlag;
    }
    public String getExcelUploadUseType(){
        return excelUploadUseType;
    }
    public String getExcelUploadSampleUseFlag(){
        return excelUploadSampleUseFlag;
    }
    public String getPdrSearchItemCondFlag(){
        return pdrSearchItemCondFlag;
    }
    public String getPdmUseFlag(){
        return pdmUseFlag;
    }
    public Integer getPdrSearchExcelDownCnt(){
        return pdrSearchExcelDownCnt;
    }
    public String getPdmPvWsCheckFlag(){
        return pdmPvWsCheckFlag;
    }
    public String getProductionTypeSetupFlag(){
        return productionTypeSetupFlag;
    }
    public String getPdrSearchCustsfxUseFlag(){
        return pdrSearchCustsfxUseFlag;
    }
    public String getBudgetYn(){
        return budgetYn;
    }
    public String getGscpUseFlag(){
        return gscpUseFlag;
    }
    public String getPartDmsUseFlag(){
        return partDmsUseFlag;
    }
}

