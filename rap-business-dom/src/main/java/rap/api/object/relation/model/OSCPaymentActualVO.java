/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OSCPaymentActualVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class OSCPaymentActualVO extends BusinessRelationObjectVO {
    private Integer       costDgr                                           ;
    private String        costYmd                                           ;
    private String        currency                                          ;
    private BigDecimal    costAmt                                            = new BigDecimal(0);
    private BigDecimal    currAppAmt                                         = new BigDecimal(0);
    private String        contractSystem                                    ;
    private String        contractNo                                        ;
    private String        contractName                                      ;
    private String        withholdingYn                                     ;
    private String        withholdingSeq                                    ;
    private BigDecimal    withholdingTaxRate                                 = new BigDecimal(0);
    private BigDecimal    withholdingTax                                     = new BigDecimal(0);
    private BigDecimal    withholdingTaxAmt                                  = new BigDecimal(0);
    private BigDecimal    residenceTax                                       = new BigDecimal(0);
    private BigDecimal    residenceTaxAmt                                    = new BigDecimal(0);


    public void    setCostDgr(Integer costDgr){
        this.costDgr = costDgr;
    }
    public void    setCostYmd(String costYmd){
        this.costYmd = costYmd;
    }
    public void    setCurrency(String currency){
        this.currency = currency;
    }
    public void    setCostAmt(BigDecimal costAmt){
        this.costAmt = costAmt;
    }
    public void    setCurrAppAmt(BigDecimal currAppAmt){
        this.currAppAmt = currAppAmt;
    }
    public void    setContractSystem(String contractSystem){
        this.contractSystem = contractSystem;
    }
    public void    setContractNo(String contractNo){
        this.contractNo = contractNo;
    }
    public void    setContractName(String contractName){
        this.contractName = contractName;
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
    public void    setWithholdingTaxAmt(BigDecimal withholdingTaxAmt){
        this.withholdingTaxAmt = withholdingTaxAmt;
    }
    public void    setResidenceTax(BigDecimal residenceTax){
        this.residenceTax = residenceTax;
    }
    public void    setResidenceTaxAmt(BigDecimal residenceTaxAmt){
        this.residenceTaxAmt = residenceTaxAmt;
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
    public BigDecimal getCostAmt(){
        return costAmt;
    }
    public BigDecimal getCurrAppAmt(){
        return currAppAmt;
    }
    public String getContractSystem(){
        return contractSystem;
    }
    public String getContractNo(){
        return contractNo;
    }
    public String getContractName(){
        return contractName;
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
    public BigDecimal getWithholdingTaxAmt(){
        return withholdingTaxAmt;
    }
    public BigDecimal getResidenceTax(){
        return residenceTax;
    }
    public BigDecimal getResidenceTaxAmt(){
        return residenceTaxAmt;
    }
}

