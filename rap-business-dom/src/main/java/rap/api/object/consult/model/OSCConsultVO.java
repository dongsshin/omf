/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OSCConsultVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.consult.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class OSCConsultVO extends BusinessObjectMasterVO {
    private String        contractType                                      ;
    private String        devFromYmd                                        ;
    private String        devEndYmd                                         ;
    private String        requestor                                         ;
    private String        contractTotCurrCode                               ;
    private BigDecimal    contractTotAmt                                     = new BigDecimal(0);
    private BigDecimal    contractTotWonAmt                                  = new BigDecimal(0);
    private String        firstContractCurrCode                             ;
    private BigDecimal    firstContractAmt                                   = new BigDecimal(0);
    private BigDecimal    actualPaymentAmt                                   = new BigDecimal(0);
    private BigDecimal    actualPaymentWonAmt                                = new BigDecimal(0);
    private BigDecimal    firstContractWonAmt                                = new BigDecimal(0);
    private String        status                                            ;
    private Integer       dgr                                               ;
    private Integer       paymentPlanDegree                                 ;
    private Integer       paymentActualDegree                               ;
    private String        supplierSelectMethod                              ;
    private String        inAdvanceInputYn                                  ;
    private String        inAdvanceInputReason                              ;
    private String        specialRelationYn                                 ;
    private String        specialRelSelReason                               ;
    private String        rschRspsrNm                                       ;
    private String        supplierSelectYn                                  ;
    private String        contractNo                                        ;
    private String        contractName                                      ;
    private String        mdmsSupplierYn                                    ;
    private String        subjectContents                                   ;
    private String        contentsText                                      ;
    private String        gpItemCode                                        ;
    private String        gpUserId                                          ;
    private String        gpItemName                                        ;
    private String        gpUserName                                        ;
    private String        annualContractYn                                  ;
    private String        prRejectYn                                        ;


    public void    setContractType(String contractType){
        this.contractType = contractType;
    }
    public void    setDevFromYmd(String devFromYmd){
        this.devFromYmd = devFromYmd;
    }
    public void    setDevEndYmd(String devEndYmd){
        this.devEndYmd = devEndYmd;
    }
    public void    setRequestor(String requestor){
        this.requestor = requestor;
    }
    public void    setContractTotCurrCode(String contractTotCurrCode){
        this.contractTotCurrCode = contractTotCurrCode;
    }
    public void    setContractTotAmt(BigDecimal contractTotAmt){
        this.contractTotAmt = contractTotAmt;
    }
    public void    setContractTotWonAmt(BigDecimal contractTotWonAmt){
        this.contractTotWonAmt = contractTotWonAmt;
    }
    public void    setFirstContractCurrCode(String firstContractCurrCode){
        this.firstContractCurrCode = firstContractCurrCode;
    }
    public void    setFirstContractAmt(BigDecimal firstContractAmt){
        this.firstContractAmt = firstContractAmt;
    }
    public void    setActualPaymentAmt(BigDecimal actualPaymentAmt){
        this.actualPaymentAmt = actualPaymentAmt;
    }
    public void    setActualPaymentWonAmt(BigDecimal actualPaymentWonAmt){
        this.actualPaymentWonAmt = actualPaymentWonAmt;
    }
    public void    setFirstContractWonAmt(BigDecimal firstContractWonAmt){
        this.firstContractWonAmt = firstContractWonAmt;
    }
    public void    setStatus(String status){
        this.status = status;
    }
    public void    setDgr(Integer dgr){
        this.dgr = dgr;
    }
    public void    setPaymentPlanDegree(Integer paymentPlanDegree){
        this.paymentPlanDegree = paymentPlanDegree;
    }
    public void    setPaymentActualDegree(Integer paymentActualDegree){
        this.paymentActualDegree = paymentActualDegree;
    }
    public void    setSupplierSelectMethod(String supplierSelectMethod){
        this.supplierSelectMethod = supplierSelectMethod;
    }
    public void    setInAdvanceInputYn(String inAdvanceInputYn){
        this.inAdvanceInputYn = inAdvanceInputYn;
    }
    public void    setInAdvanceInputReason(String inAdvanceInputReason){
        this.inAdvanceInputReason = inAdvanceInputReason;
    }
    public void    setSpecialRelationYn(String specialRelationYn){
        this.specialRelationYn = specialRelationYn;
    }
    public void    setSpecialRelSelReason(String specialRelSelReason){
        this.specialRelSelReason = specialRelSelReason;
    }
    public void    setRschRspsrNm(String rschRspsrNm){
        this.rschRspsrNm = rschRspsrNm;
    }
    public void    setSupplierSelectYn(String supplierSelectYn){
        this.supplierSelectYn = supplierSelectYn;
    }
    public void    setContractNo(String contractNo){
        this.contractNo = contractNo;
    }
    public void    setContractName(String contractName){
        this.contractName = contractName;
    }
    public void    setMdmsSupplierYn(String mdmsSupplierYn){
        this.mdmsSupplierYn = mdmsSupplierYn;
    }
    public void    setSubjectContents(String subjectContents){
        this.subjectContents = subjectContents;
    }
    public void    setContentsText(String contentsText){
        this.contentsText = contentsText;
    }
    public void    setGpItemCode(String gpItemCode){
        this.gpItemCode = gpItemCode;
    }
    public void    setGpUserId(String gpUserId){
        this.gpUserId = gpUserId;
    }
    public void    setGpItemName(String gpItemName){
        this.gpItemName = gpItemName;
    }
    public void    setGpUserName(String gpUserName){
        this.gpUserName = gpUserName;
    }
    public void    setAnnualContractYn(String annualContractYn){
        this.annualContractYn = annualContractYn;
    }
    public void    setPrRejectYn(String prRejectYn){
        this.prRejectYn = prRejectYn;
    }
    public String getContractType(){
        return contractType;
    }
    public String getDevFromYmd(){
        return devFromYmd;
    }
    public String getDevEndYmd(){
        return devEndYmd;
    }
    public String getRequestor(){
        return requestor;
    }
    public String getContractTotCurrCode(){
        return contractTotCurrCode;
    }
    public BigDecimal getContractTotAmt(){
        return contractTotAmt;
    }
    public BigDecimal getContractTotWonAmt(){
        return contractTotWonAmt;
    }
    public String getFirstContractCurrCode(){
        return firstContractCurrCode;
    }
    public BigDecimal getFirstContractAmt(){
        return firstContractAmt;
    }
    public BigDecimal getActualPaymentAmt(){
        return actualPaymentAmt;
    }
    public BigDecimal getActualPaymentWonAmt(){
        return actualPaymentWonAmt;
    }
    public BigDecimal getFirstContractWonAmt(){
        return firstContractWonAmt;
    }
    public String getStatus(){
        return status;
    }
    public Integer getDgr(){
        return dgr;
    }
    public Integer getPaymentPlanDegree(){
        return paymentPlanDegree;
    }
    public Integer getPaymentActualDegree(){
        return paymentActualDegree;
    }
    public String getSupplierSelectMethod(){
        return supplierSelectMethod;
    }
    public String getInAdvanceInputYn(){
        return inAdvanceInputYn;
    }
    public String getInAdvanceInputReason(){
        return inAdvanceInputReason;
    }
    public String getSpecialRelationYn(){
        return specialRelationYn;
    }
    public String getSpecialRelSelReason(){
        return specialRelSelReason;
    }
    public String getRschRspsrNm(){
        return rschRspsrNm;
    }
    public String getSupplierSelectYn(){
        return supplierSelectYn;
    }
    public String getContractNo(){
        return contractNo;
    }
    public String getContractName(){
        return contractName;
    }
    public String getMdmsSupplierYn(){
        return mdmsSupplierYn;
    }
    public String getSubjectContents(){
        return subjectContents;
    }
    public String getContentsText(){
        return contentsText;
    }
    public String getGpItemCode(){
        return gpItemCode;
    }
    public String getGpUserId(){
        return gpUserId;
    }
    public String getGpItemName(){
        return gpItemName;
    }
    public String getGpUserName(){
        return gpUserName;
    }
    public String getAnnualContractYn(){
        return annualContractYn;
    }
    public String getPrRejectYn(){
        return prRejectYn;
    }
}

