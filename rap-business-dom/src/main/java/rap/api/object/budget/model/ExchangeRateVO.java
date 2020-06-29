/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ExchangeRateVO.java
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
public class ExchangeRateVO extends BusinessObjectMasterVO {
    private String        rateDate                                          ;
    private String        fromCurrency                                      ;
    private String        toCurrency                                        ;
    private String        userConvertTypeCd                                 ;
    private String        convertTypeCd                                     ;
    private BigDecimal    convertRate                                        = new BigDecimal(0);


    public void    setRateDate(String rateDate){
        this.rateDate = rateDate;
    }
    public void    setFromCurrency(String fromCurrency){
        this.fromCurrency = fromCurrency;
    }
    public void    setToCurrency(String toCurrency){
        this.toCurrency = toCurrency;
    }
    public void    setUserConvertTypeCd(String userConvertTypeCd){
        this.userConvertTypeCd = userConvertTypeCd;
    }
    public void    setConvertTypeCd(String convertTypeCd){
        this.convertTypeCd = convertTypeCd;
    }
    public void    setConvertRate(BigDecimal convertRate){
        this.convertRate = convertRate;
    }
    public String getRateDate(){
        return rateDate;
    }
    public String getFromCurrency(){
        return fromCurrency;
    }
    public String getToCurrency(){
        return toCurrency;
    }
    public String getUserConvertTypeCd(){
        return userConvertTypeCd;
    }
    public String getConvertTypeCd(){
        return convertTypeCd;
    }
    public BigDecimal getConvertRate(){
        return convertRate;
    }
}

