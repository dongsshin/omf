/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECMSExRateDetailVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ECMSExRateDetailVO extends BusinessObjectMasterVO {
    private String        exrateYear                                        ;
    private String        fromCurrencyName                                  ;
    private String        toCurrencyName                                    ;
    private Float         exrateRate                                        ;


    public void    setExrateYear(String exrateYear){
        this.exrateYear = exrateYear;
    }
    public void    setFromCurrencyName(String fromCurrencyName){
        this.fromCurrencyName = fromCurrencyName;
    }
    public void    setToCurrencyName(String toCurrencyName){
        this.toCurrencyName = toCurrencyName;
    }
    public void    setExrateRate(Float exrateRate){
        this.exrateRate = exrateRate;
    }
    public String getExrateYear(){
        return exrateYear;
    }
    public String getFromCurrencyName(){
        return fromCurrencyName;
    }
    public String getToCurrencyName(){
        return toCurrencyName;
    }
    public Float getExrateRate(){
        return exrateRate;
    }
}

