/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MonthlyExchangeRateVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.closing.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class MonthlyExchangeRateVO extends BusinessObjectMasterVO {
    private String        month                                             ;
    private String        legalEntityName                                   ;
    private String        fromCurrencyCode                                  ;
    private String        toCurrencyCode                                    ;
    private String        userConvertTypeCd                                 ;
    private BigDecimal    averageRate                                        = new BigDecimal(0);
    private BigDecimal    endingRate                                         = new BigDecimal(0);


    public void    setMonth(String month){
        this.month = month;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setFromCurrencyCode(String fromCurrencyCode){
        this.fromCurrencyCode = fromCurrencyCode;
    }
    public void    setToCurrencyCode(String toCurrencyCode){
        this.toCurrencyCode = toCurrencyCode;
    }
    public void    setUserConvertTypeCd(String userConvertTypeCd){
        this.userConvertTypeCd = userConvertTypeCd;
    }
    public void    setAverageRate(BigDecimal averageRate){
        this.averageRate = averageRate;
    }
    public void    setEndingRate(BigDecimal endingRate){
        this.endingRate = endingRate;
    }
    public String getMonth(){
        return month;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getFromCurrencyCode(){
        return fromCurrencyCode;
    }
    public String getToCurrencyCode(){
        return toCurrencyCode;
    }
    public String getUserConvertTypeCd(){
        return userConvertTypeCd;
    }
    public BigDecimal getAverageRate(){
        return averageRate;
    }
    public BigDecimal getEndingRate(){
        return endingRate;
    }
}

