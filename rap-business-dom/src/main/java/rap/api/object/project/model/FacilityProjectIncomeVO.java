/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : FacilityProjectIncomeVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class FacilityProjectIncomeVO extends BusinessObjectMasterVO {
    private Integer       dgr                                               ;
    private String        fromObid                                          ;
    private Integer       sno                                               ;
    private String        incomePlanYmd                                     ;
    private String        incomeResultYmd                                   ;
    private BigDecimal    incomePlanCost                                     = new BigDecimal(0);
    private BigDecimal    incomeResultCost                                   = new BigDecimal(0);
    private BigDecimal    manPlanCost                                        = new BigDecimal(0);
    private BigDecimal    manResultCost                                      = new BigDecimal(0);
    private BigDecimal    expPlanCost                                        = new BigDecimal(0);
    private BigDecimal    expResultCost                                      = new BigDecimal(0);
    private BigDecimal    depPlanCost                                        = new BigDecimal(0);
    private BigDecimal    depResultCost                                      = new BigDecimal(0);
    private BigDecimal    resultSettleCost                                   = new BigDecimal(0);
    private BigDecimal    resultPriceCost                                    = new BigDecimal(0);
    private BigDecimal    resultIndirCost                                    = new BigDecimal(0);
    private String        resultGlNumber                                    ;
    private BigDecimal    settleManCost                                      = new BigDecimal(0);
    private BigDecimal    settleExpCost                                      = new BigDecimal(0);
    private BigDecimal    settleDepCost                                      = new BigDecimal(0);
    private String        comments                                          ;
    private String        settleInfoNum                                     ;
    private BigDecimal    resultSettleAmt1                                   = new BigDecimal(0);
    private BigDecimal    resultSettleAmt2                                   = new BigDecimal(0);
    private BigDecimal    resultSettleAmt3                                   = new BigDecimal(0);


    public void    setDgr(Integer dgr){
        this.dgr = dgr;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setSno(Integer sno){
        this.sno = sno;
    }
    public void    setIncomePlanYmd(String incomePlanYmd){
        this.incomePlanYmd = incomePlanYmd;
    }
    public void    setIncomeResultYmd(String incomeResultYmd){
        this.incomeResultYmd = incomeResultYmd;
    }
    public void    setIncomePlanCost(BigDecimal incomePlanCost){
        this.incomePlanCost = incomePlanCost;
    }
    public void    setIncomeResultCost(BigDecimal incomeResultCost){
        this.incomeResultCost = incomeResultCost;
    }
    public void    setManPlanCost(BigDecimal manPlanCost){
        this.manPlanCost = manPlanCost;
    }
    public void    setManResultCost(BigDecimal manResultCost){
        this.manResultCost = manResultCost;
    }
    public void    setExpPlanCost(BigDecimal expPlanCost){
        this.expPlanCost = expPlanCost;
    }
    public void    setExpResultCost(BigDecimal expResultCost){
        this.expResultCost = expResultCost;
    }
    public void    setDepPlanCost(BigDecimal depPlanCost){
        this.depPlanCost = depPlanCost;
    }
    public void    setDepResultCost(BigDecimal depResultCost){
        this.depResultCost = depResultCost;
    }
    public void    setResultSettleCost(BigDecimal resultSettleCost){
        this.resultSettleCost = resultSettleCost;
    }
    public void    setResultPriceCost(BigDecimal resultPriceCost){
        this.resultPriceCost = resultPriceCost;
    }
    public void    setResultIndirCost(BigDecimal resultIndirCost){
        this.resultIndirCost = resultIndirCost;
    }
    public void    setResultGlNumber(String resultGlNumber){
        this.resultGlNumber = resultGlNumber;
    }
    public void    setSettleManCost(BigDecimal settleManCost){
        this.settleManCost = settleManCost;
    }
    public void    setSettleExpCost(BigDecimal settleExpCost){
        this.settleExpCost = settleExpCost;
    }
    public void    setSettleDepCost(BigDecimal settleDepCost){
        this.settleDepCost = settleDepCost;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public void    setSettleInfoNum(String settleInfoNum){
        this.settleInfoNum = settleInfoNum;
    }
    public void    setResultSettleAmt1(BigDecimal resultSettleAmt1){
        this.resultSettleAmt1 = resultSettleAmt1;
    }
    public void    setResultSettleAmt2(BigDecimal resultSettleAmt2){
        this.resultSettleAmt2 = resultSettleAmt2;
    }
    public void    setResultSettleAmt3(BigDecimal resultSettleAmt3){
        this.resultSettleAmt3 = resultSettleAmt3;
    }
    public Integer getDgr(){
        return dgr;
    }
    public String getFromObid(){
        return fromObid;
    }
    public Integer getSno(){
        return sno;
    }
    public String getIncomePlanYmd(){
        return incomePlanYmd;
    }
    public String getIncomeResultYmd(){
        return incomeResultYmd;
    }
    public BigDecimal getIncomePlanCost(){
        return incomePlanCost;
    }
    public BigDecimal getIncomeResultCost(){
        return incomeResultCost;
    }
    public BigDecimal getManPlanCost(){
        return manPlanCost;
    }
    public BigDecimal getManResultCost(){
        return manResultCost;
    }
    public BigDecimal getExpPlanCost(){
        return expPlanCost;
    }
    public BigDecimal getExpResultCost(){
        return expResultCost;
    }
    public BigDecimal getDepPlanCost(){
        return depPlanCost;
    }
    public BigDecimal getDepResultCost(){
        return depResultCost;
    }
    public BigDecimal getResultSettleCost(){
        return resultSettleCost;
    }
    public BigDecimal getResultPriceCost(){
        return resultPriceCost;
    }
    public BigDecimal getResultIndirCost(){
        return resultIndirCost;
    }
    public String getResultGlNumber(){
        return resultGlNumber;
    }
    public BigDecimal getSettleManCost(){
        return settleManCost;
    }
    public BigDecimal getSettleExpCost(){
        return settleExpCost;
    }
    public BigDecimal getSettleDepCost(){
        return settleDepCost;
    }
    public String getComments(){
        return comments;
    }
    public String getSettleInfoNum(){
        return settleInfoNum;
    }
    public BigDecimal getResultSettleAmt1(){
        return resultSettleAmt1;
    }
    public BigDecimal getResultSettleAmt2(){
        return resultSettleAmt2;
    }
    public BigDecimal getResultSettleAmt3(){
        return resultSettleAmt3;
    }
}

