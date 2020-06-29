/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanProjectBudgetYearVO.java
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
public class HasPlanProjectBudgetYearVO extends BusinessRelationObjectVO {
    private String        planYear                                          ;
    private String        currencyCode                                      ;
    private BigDecimal    amount                                             = new BigDecimal(0);
    private String        accountCode                                       ;


    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setAmount(BigDecimal amount){
        this.amount = amount;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public String getPlanYear(){
        return planYear;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public BigDecimal getAmount(){
        return amount;
    }
    public String getAccountCode(){
        return accountCode;
    }
}

