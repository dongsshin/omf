/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanDepreAllocResultVO.java
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
public class HasPlanDepreAllocResultVO extends BusinessRelationObjectVO {
    private String        versionObid                                       ;
    private String        accountCode                                       ;
    private String        planYear                                          ;
    private String        currencyCode                                      ;
    private BigDecimal    month01Amount                                      = new BigDecimal(0);
    private BigDecimal    month02Amount                                      = new BigDecimal(0);
    private BigDecimal    month03Amount                                      = new BigDecimal(0);
    private BigDecimal    month04Amount                                      = new BigDecimal(0);
    private BigDecimal    month05Amount                                      = new BigDecimal(0);
    private BigDecimal    month06Amount                                      = new BigDecimal(0);
    private BigDecimal    month07Amount                                      = new BigDecimal(0);
    private BigDecimal    month08Amount                                      = new BigDecimal(0);
    private BigDecimal    month09Amount                                      = new BigDecimal(0);
    private BigDecimal    month10Amount                                      = new BigDecimal(0);
    private BigDecimal    month11Amount                                      = new BigDecimal(0);
    private BigDecimal    month12Amount                                      = new BigDecimal(0);
    private BigDecimal    totAmount                                          = new BigDecimal(0);


    public void    setVersionObid(String versionObid){
        this.versionObid = versionObid;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setMonth01Amount(BigDecimal month01Amount){
        this.month01Amount = month01Amount;
    }
    public void    setMonth02Amount(BigDecimal month02Amount){
        this.month02Amount = month02Amount;
    }
    public void    setMonth03Amount(BigDecimal month03Amount){
        this.month03Amount = month03Amount;
    }
    public void    setMonth04Amount(BigDecimal month04Amount){
        this.month04Amount = month04Amount;
    }
    public void    setMonth05Amount(BigDecimal month05Amount){
        this.month05Amount = month05Amount;
    }
    public void    setMonth06Amount(BigDecimal month06Amount){
        this.month06Amount = month06Amount;
    }
    public void    setMonth07Amount(BigDecimal month07Amount){
        this.month07Amount = month07Amount;
    }
    public void    setMonth08Amount(BigDecimal month08Amount){
        this.month08Amount = month08Amount;
    }
    public void    setMonth09Amount(BigDecimal month09Amount){
        this.month09Amount = month09Amount;
    }
    public void    setMonth10Amount(BigDecimal month10Amount){
        this.month10Amount = month10Amount;
    }
    public void    setMonth11Amount(BigDecimal month11Amount){
        this.month11Amount = month11Amount;
    }
    public void    setMonth12Amount(BigDecimal month12Amount){
        this.month12Amount = month12Amount;
    }
    public void    setTotAmount(BigDecimal totAmount){
        this.totAmount = totAmount;
    }
    public String getVersionObid(){
        return versionObid;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public String getPlanYear(){
        return planYear;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public BigDecimal getMonth01Amount(){
        return month01Amount;
    }
    public BigDecimal getMonth02Amount(){
        return month02Amount;
    }
    public BigDecimal getMonth03Amount(){
        return month03Amount;
    }
    public BigDecimal getMonth04Amount(){
        return month04Amount;
    }
    public BigDecimal getMonth05Amount(){
        return month05Amount;
    }
    public BigDecimal getMonth06Amount(){
        return month06Amount;
    }
    public BigDecimal getMonth07Amount(){
        return month07Amount;
    }
    public BigDecimal getMonth08Amount(){
        return month08Amount;
    }
    public BigDecimal getMonth09Amount(){
        return month09Amount;
    }
    public BigDecimal getMonth10Amount(){
        return month10Amount;
    }
    public BigDecimal getMonth11Amount(){
        return month11Amount;
    }
    public BigDecimal getMonth12Amount(){
        return month12Amount;
    }
    public BigDecimal getTotAmount(){
        return totAmount;
    }
}

