/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : NptPlanExchangeRateVO.java
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
public class NptPlanExchangeRateVO extends BusinessObjectMasterVO {
    private String        planMonth                                         ;
    private String        fromCurrencyCode                                  ;
    private String        toCurrencyCode                                    ;
    private Float         exchangeRate                                      ;


    public void    setPlanMonth(String planMonth){
        this.planMonth = planMonth;
    }
    public void    setFromCurrencyCode(String fromCurrencyCode){
        this.fromCurrencyCode = fromCurrencyCode;
    }
    public void    setToCurrencyCode(String toCurrencyCode){
        this.toCurrencyCode = toCurrencyCode;
    }
    public void    setExchangeRate(Float exchangeRate){
        this.exchangeRate = exchangeRate;
    }
    public String getPlanMonth(){
        return planMonth;
    }
    public String getFromCurrencyCode(){
        return fromCurrencyCode;
    }
    public String getToCurrencyCode(){
        return toCurrencyCode;
    }
    public Float getExchangeRate(){
        return exchangeRate;
    }
}

