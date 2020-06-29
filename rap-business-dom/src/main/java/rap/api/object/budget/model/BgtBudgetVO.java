/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BgtBudgetVO.java
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
public class BgtBudgetVO extends BusinessObjectMasterVO {
    private String        budgetYm                                          ;
    private String        bpPjtCd                                           ;
    private String        planFg                                            ;
    private BigDecimal    consBgtChkAmt                                      = new BigDecimal(0);
    private Integer       dgr                                               ;
    private BigDecimal    basicBgtAmt                                        = new BigDecimal(0);
    private BigDecimal    operateBgtAmt                                      = new BigDecimal(0);
    private BigDecimal    flexBgtAmt                                         = new BigDecimal(0);
    private BigDecimal    divertAmt                                          = new BigDecimal(0);
    private BigDecimal    execBgtAmt                                         = new BigDecimal(0);
    private BigDecimal    actualAmt                                          = new BigDecimal(0);
    private BigDecimal    notSettledReqAmt                                   = new BigDecimal(0);
    private BigDecimal    notApprDivertFromAmt                               = new BigDecimal(0);
    private BigDecimal    notApprDivertToAmt                                 = new BigDecimal(0);
    private BigDecimal    calcActualAmt                                      = new BigDecimal(0);
    private BigDecimal    glActualAmt                                        = new BigDecimal(0);
    private BigDecimal    consReqAmt                                         = new BigDecimal(0);
    private String        comments                                          ;
    private BigDecimal    adjustAmtCurr                                      = new BigDecimal(0);
    private BigDecimal    adjustAmtPre                                       = new BigDecimal(0);


    public void    setBudgetYm(String budgetYm){
        this.budgetYm = budgetYm;
    }
    public void    setBpPjtCd(String bpPjtCd){
        this.bpPjtCd = bpPjtCd;
    }
    public void    setPlanFg(String planFg){
        this.planFg = planFg;
    }
    public void    setConsBgtChkAmt(BigDecimal consBgtChkAmt){
        this.consBgtChkAmt = consBgtChkAmt;
    }
    public void    setDgr(Integer dgr){
        this.dgr = dgr;
    }
    public void    setBasicBgtAmt(BigDecimal basicBgtAmt){
        this.basicBgtAmt = basicBgtAmt;
    }
    public void    setOperateBgtAmt(BigDecimal operateBgtAmt){
        this.operateBgtAmt = operateBgtAmt;
    }
    public void    setFlexBgtAmt(BigDecimal flexBgtAmt){
        this.flexBgtAmt = flexBgtAmt;
    }
    public void    setDivertAmt(BigDecimal divertAmt){
        this.divertAmt = divertAmt;
    }
    public void    setExecBgtAmt(BigDecimal execBgtAmt){
        this.execBgtAmt = execBgtAmt;
    }
    public void    setActualAmt(BigDecimal actualAmt){
        this.actualAmt = actualAmt;
    }
    public void    setNotSettledReqAmt(BigDecimal notSettledReqAmt){
        this.notSettledReqAmt = notSettledReqAmt;
    }
    public void    setNotApprDivertFromAmt(BigDecimal notApprDivertFromAmt){
        this.notApprDivertFromAmt = notApprDivertFromAmt;
    }
    public void    setNotApprDivertToAmt(BigDecimal notApprDivertToAmt){
        this.notApprDivertToAmt = notApprDivertToAmt;
    }
    public void    setCalcActualAmt(BigDecimal calcActualAmt){
        this.calcActualAmt = calcActualAmt;
    }
    public void    setGlActualAmt(BigDecimal glActualAmt){
        this.glActualAmt = glActualAmt;
    }
    public void    setConsReqAmt(BigDecimal consReqAmt){
        this.consReqAmt = consReqAmt;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public void    setAdjustAmtCurr(BigDecimal adjustAmtCurr){
        this.adjustAmtCurr = adjustAmtCurr;
    }
    public void    setAdjustAmtPre(BigDecimal adjustAmtPre){
        this.adjustAmtPre = adjustAmtPre;
    }
    public String getBudgetYm(){
        return budgetYm;
    }
    public String getBpPjtCd(){
        return bpPjtCd;
    }
    public String getPlanFg(){
        return planFg;
    }
    public BigDecimal getConsBgtChkAmt(){
        return consBgtChkAmt;
    }
    public Integer getDgr(){
        return dgr;
    }
    public BigDecimal getBasicBgtAmt(){
        return basicBgtAmt;
    }
    public BigDecimal getOperateBgtAmt(){
        return operateBgtAmt;
    }
    public BigDecimal getFlexBgtAmt(){
        return flexBgtAmt;
    }
    public BigDecimal getDivertAmt(){
        return divertAmt;
    }
    public BigDecimal getExecBgtAmt(){
        return execBgtAmt;
    }
    public BigDecimal getActualAmt(){
        return actualAmt;
    }
    public BigDecimal getNotSettledReqAmt(){
        return notSettledReqAmt;
    }
    public BigDecimal getNotApprDivertFromAmt(){
        return notApprDivertFromAmt;
    }
    public BigDecimal getNotApprDivertToAmt(){
        return notApprDivertToAmt;
    }
    public BigDecimal getCalcActualAmt(){
        return calcActualAmt;
    }
    public BigDecimal getGlActualAmt(){
        return glActualAmt;
    }
    public BigDecimal getConsReqAmt(){
        return consReqAmt;
    }
    public String getComments(){
        return comments;
    }
    public BigDecimal getAdjustAmtCurr(){
        return adjustAmtCurr;
    }
    public BigDecimal getAdjustAmtPre(){
        return adjustAmtPre;
    }
}

