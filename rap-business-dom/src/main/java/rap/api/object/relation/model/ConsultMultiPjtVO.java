/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultMultiPjtVO.java
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
public class ConsultMultiPjtVO extends BusinessRelationObjectVO {
    private BigDecimal    rate                                               = new BigDecimal(0);
    private BigDecimal    amt                                                = new BigDecimal(0);
    private BigDecimal    calcAmt                                            = new BigDecimal(0);
    private BigDecimal    realAmt                                            = new BigDecimal(0);
    private String        departmentCode                                    ;
    private String        currency                                          ;
    private BigDecimal    exchRate                                           = new BigDecimal(0);
    private BigDecimal    frnReqAmt                                          = new BigDecimal(0);
    private String        alterCurrency                                     ;
    private BigDecimal    alterExchRate                                      = new BigDecimal(0);
    private BigDecimal    alterFrnReqAmt                                     = new BigDecimal(0);
    private String        calcYmd                                           ;


    public void    setRate(BigDecimal rate){
        this.rate = rate;
    }
    public void    setAmt(BigDecimal amt){
        this.amt = amt;
    }
    public void    setCalcAmt(BigDecimal calcAmt){
        this.calcAmt = calcAmt;
    }
    public void    setRealAmt(BigDecimal realAmt){
        this.realAmt = realAmt;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setCurrency(String currency){
        this.currency = currency;
    }
    public void    setExchRate(BigDecimal exchRate){
        this.exchRate = exchRate;
    }
    public void    setFrnReqAmt(BigDecimal frnReqAmt){
        this.frnReqAmt = frnReqAmt;
    }
    public void    setAlterCurrency(String alterCurrency){
        this.alterCurrency = alterCurrency;
    }
    public void    setAlterExchRate(BigDecimal alterExchRate){
        this.alterExchRate = alterExchRate;
    }
    public void    setAlterFrnReqAmt(BigDecimal alterFrnReqAmt){
        this.alterFrnReqAmt = alterFrnReqAmt;
    }
    public void    setCalcYmd(String calcYmd){
        this.calcYmd = calcYmd;
    }
    public BigDecimal getRate(){
        return rate;
    }
    public BigDecimal getAmt(){
        return amt;
    }
    public BigDecimal getCalcAmt(){
        return calcAmt;
    }
    public BigDecimal getRealAmt(){
        return realAmt;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getCurrency(){
        return currency;
    }
    public BigDecimal getExchRate(){
        return exchRate;
    }
    public BigDecimal getFrnReqAmt(){
        return frnReqAmt;
    }
    public String getAlterCurrency(){
        return alterCurrency;
    }
    public BigDecimal getAlterExchRate(){
        return alterExchRate;
    }
    public BigDecimal getAlterFrnReqAmt(){
        return alterFrnReqAmt;
    }
    public String getCalcYmd(){
        return calcYmd;
    }
}

