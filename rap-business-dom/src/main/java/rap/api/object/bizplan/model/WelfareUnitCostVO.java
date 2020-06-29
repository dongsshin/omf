/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WelfareUnitCostVO.java
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
public class WelfareUnitCostVO extends BusinessObjectMasterVO {
    private String        jobPositionCode                                   ;
    private String        accountCode                                       ;
    private String        currencyCode                                      ;
    private BigDecimal    amount                                             = new BigDecimal(0);


    public void    setJobPositionCode(String jobPositionCode){
        this.jobPositionCode = jobPositionCode;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setAmount(BigDecimal amount){
        this.amount = amount;
    }
    public String getJobPositionCode(){
        return jobPositionCode;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public BigDecimal getAmount(){
        return amount;
    }
}

