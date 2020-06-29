/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanDepreAllocFactorVO.java
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
public class HasPlanDepreAllocFactorVO extends BusinessRelationObjectVO {
    private String        currencyCode                                      ;
    private BigDecimal    depreAmount                                        = new BigDecimal(0);
    private BigDecimal    thisYearInvestAmount                               = new BigDecimal(0);
    private BigDecimal    bizPlanInvestAmount                                = new BigDecimal(0);
    private BigDecimal    totDepreAmount                                     = new BigDecimal(0);
    private BigDecimal    adjDepreAmount                                     = new BigDecimal(0);
    private BigDecimal    adjThisYearInvestAmount                            = new BigDecimal(0);
    private BigDecimal    adjBizPlanInvestAmount                             = new BigDecimal(0);
    private BigDecimal    totAdjDepreAmount                                  = new BigDecimal(0);
    private String        allocFactorCode                                   ;
    private String        versionObid                                       ;


    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setDepreAmount(BigDecimal depreAmount){
        this.depreAmount = depreAmount;
    }
    public void    setThisYearInvestAmount(BigDecimal thisYearInvestAmount){
        this.thisYearInvestAmount = thisYearInvestAmount;
    }
    public void    setBizPlanInvestAmount(BigDecimal bizPlanInvestAmount){
        this.bizPlanInvestAmount = bizPlanInvestAmount;
    }
    public void    setTotDepreAmount(BigDecimal totDepreAmount){
        this.totDepreAmount = totDepreAmount;
    }
    public void    setAdjDepreAmount(BigDecimal adjDepreAmount){
        this.adjDepreAmount = adjDepreAmount;
    }
    public void    setAdjThisYearInvestAmount(BigDecimal adjThisYearInvestAmount){
        this.adjThisYearInvestAmount = adjThisYearInvestAmount;
    }
    public void    setAdjBizPlanInvestAmount(BigDecimal adjBizPlanInvestAmount){
        this.adjBizPlanInvestAmount = adjBizPlanInvestAmount;
    }
    public void    setTotAdjDepreAmount(BigDecimal totAdjDepreAmount){
        this.totAdjDepreAmount = totAdjDepreAmount;
    }
    public void    setAllocFactorCode(String allocFactorCode){
        this.allocFactorCode = allocFactorCode;
    }
    public void    setVersionObid(String versionObid){
        this.versionObid = versionObid;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public BigDecimal getDepreAmount(){
        return depreAmount;
    }
    public BigDecimal getThisYearInvestAmount(){
        return thisYearInvestAmount;
    }
    public BigDecimal getBizPlanInvestAmount(){
        return bizPlanInvestAmount;
    }
    public BigDecimal getTotDepreAmount(){
        return totDepreAmount;
    }
    public BigDecimal getAdjDepreAmount(){
        return adjDepreAmount;
    }
    public BigDecimal getAdjThisYearInvestAmount(){
        return adjThisYearInvestAmount;
    }
    public BigDecimal getAdjBizPlanInvestAmount(){
        return adjBizPlanInvestAmount;
    }
    public BigDecimal getTotAdjDepreAmount(){
        return totAdjDepreAmount;
    }
    public String getAllocFactorCode(){
        return allocFactorCode;
    }
    public String getVersionObid(){
        return versionObid;
    }
}

