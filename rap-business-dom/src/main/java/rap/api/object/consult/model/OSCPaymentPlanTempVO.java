/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OSCPaymentPlanTempVO.java
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
public class OSCPaymentPlanTempVO extends BusinessObjectMasterVO {
    private String        oscSeq                                            ;
    private Integer       costDgr                                           ;
    private String        costYmd                                           ;
    private String        currency                                          ;
    private BigDecimal    planCostAmt                                        = new BigDecimal(0);
    private BigDecimal    planCurrAppAmt                                     = new BigDecimal(0);
    private BigDecimal    planInclPaymentAmount                              = new BigDecimal(0);
    private BigDecimal    planInclCurrAppAmt                                 = new BigDecimal(0);
    private String        withholdingYn                                     ;
    private String        withholdingSeq                                    ;
    private BigDecimal    withholdingTaxRate                                 = new BigDecimal(0);
    private BigDecimal    withholdingTax                                     = new BigDecimal(0);
    private String        costConsCd                                        ;
    private BigDecimal    residenceTax                                       = new BigDecimal(0);
    private BigDecimal    withholdingTaxAmt                                  = new BigDecimal(0);
    private BigDecimal    residenceTaxAmt                                    = new BigDecimal(0);
    private Integer       changerequestdgr                                  ;
    private String        changehistoryflag                                 ;
    private String        contractNo                                        ;
    private String        contractName                                      ;
    private String        editableFlag                                      ;


    public void    setOscSeq(String oscSeq){
        this.oscSeq = oscSeq;
    }
    public void    setCostDgr(Integer costDgr){
        this.costDgr = costDgr;
    }
    public void    setCostYmd(String costYmd){
        this.costYmd = costYmd;
    }
    public void    setCurrency(String currency){
        this.currency = currency;
    }
    public void    setPlanCostAmt(BigDecimal planCostAmt){
        this.planCostAmt = planCostAmt;
    }
    public void    setPlanCurrAppAmt(BigDecimal planCurrAppAmt){
        this.planCurrAppAmt = planCurrAppAmt;
    }
    public void    setPlanInclPaymentAmount(BigDecimal planInclPaymentAmount){
        this.planInclPaymentAmount = planInclPaymentAmount;
    }
    public void    setPlanInclCurrAppAmt(BigDecimal planInclCurrAppAmt){
        this.planInclCurrAppAmt = planInclCurrAppAmt;
    }
    public void    setWithholdingYn(String withholdingYn){
        this.withholdingYn = withholdingYn;
    }
    public void    setWithholdingSeq(String withholdingSeq){
        this.withholdingSeq = withholdingSeq;
    }
    public void    setWithholdingTaxRate(BigDecimal withholdingTaxRate){
        this.withholdingTaxRate = withholdingTaxRate;
    }
    public void    setWithholdingTax(BigDecimal withholdingTax){
        this.withholdingTax = withholdingTax;
    }
    public void    setCostConsCd(String costConsCd){
        this.costConsCd = costConsCd;
    }
    public void    setResidenceTax(BigDecimal residenceTax){
        this.residenceTax = residenceTax;
    }
    public void    setWithholdingTaxAmt(BigDecimal withholdingTaxAmt){
        this.withholdingTaxAmt = withholdingTaxAmt;
    }
    public void    setResidenceTaxAmt(BigDecimal residenceTaxAmt){
        this.residenceTaxAmt = residenceTaxAmt;
    }
    public void    setChangerequestdgr(Integer changerequestdgr){
        this.changerequestdgr = changerequestdgr;
    }
    public void    setChangehistoryflag(String changehistoryflag){
        this.changehistoryflag = changehistoryflag;
    }
    public void    setContractNo(String contractNo){
        this.contractNo = contractNo;
    }
    public void    setContractName(String contractName){
        this.contractName = contractName;
    }
    public void    setEditableFlag(String editableFlag){
        this.editableFlag = editableFlag;
    }
    public String getOscSeq(){
        return oscSeq;
    }
    public Integer getCostDgr(){
        return costDgr;
    }
    public String getCostYmd(){
        return costYmd;
    }
    public String getCurrency(){
        return currency;
    }
    public BigDecimal getPlanCostAmt(){
        return planCostAmt;
    }
    public BigDecimal getPlanCurrAppAmt(){
        return planCurrAppAmt;
    }
    public BigDecimal getPlanInclPaymentAmount(){
        return planInclPaymentAmount;
    }
    public BigDecimal getPlanInclCurrAppAmt(){
        return planInclCurrAppAmt;
    }
    public String getWithholdingYn(){
        return withholdingYn;
    }
    public String getWithholdingSeq(){
        return withholdingSeq;
    }
    public BigDecimal getWithholdingTaxRate(){
        return withholdingTaxRate;
    }
    public BigDecimal getWithholdingTax(){
        return withholdingTax;
    }
    public String getCostConsCd(){
        return costConsCd;
    }
    public BigDecimal getResidenceTax(){
        return residenceTax;
    }
    public BigDecimal getWithholdingTaxAmt(){
        return withholdingTaxAmt;
    }
    public BigDecimal getResidenceTaxAmt(){
        return residenceTaxAmt;
    }
    public Integer getChangerequestdgr(){
        return changerequestdgr;
    }
    public String getChangehistoryflag(){
        return changehistoryflag;
    }
    public String getContractNo(){
        return contractNo;
    }
    public String getContractName(){
        return contractName;
    }
    public String getEditableFlag(){
        return editableFlag;
    }
}

