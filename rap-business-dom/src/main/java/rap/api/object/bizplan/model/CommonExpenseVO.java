/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CommonExpenseVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class CommonExpenseVO extends BusinessObjectMasterVO {
    private String        accountGroupCode                                  ;
    private String        planYear                                          ;
    private String        nextYear                                          ;
    private String        currencyCode                                      ;
    private BigDecimal    amount                                             = new BigDecimal(0);


    public void    setAccountGroupCode(String accountGroupCode){
        this.accountGroupCode = accountGroupCode;
    }
    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setNextYear(String nextYear){
        this.nextYear = nextYear;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setAmount(BigDecimal amount){
        this.amount = amount;
    }
    public String getAccountGroupCode(){
        return accountGroupCode;
    }
    public String getPlanYear(){
        return planYear;
    }
    public String getNextYear(){
        return nextYear;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public BigDecimal getAmount(){
        return amount;
    }
}

