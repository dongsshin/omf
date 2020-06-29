/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BizPlanExchangeRateVO.java
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
public class BizPlanExchangeRateVO extends BusinessObjectMasterVO {
    private String        planYear                                          ;
    private String        currencyCode                                      ;
    private String        planMonth                                         ;
    private Float         exchangeRate                                      ;
    private String        useFlag                                           ;


    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setPlanMonth(String planMonth){
        this.planMonth = planMonth;
    }
    public void    setExchangeRate(Float exchangeRate){
        this.exchangeRate = exchangeRate;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public String getPlanYear(){
        return planYear;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public String getPlanMonth(){
        return planMonth;
    }
    public Float getExchangeRate(){
        return exchangeRate;
    }
    public String getUseFlag(){
        return useFlag;
    }
}

