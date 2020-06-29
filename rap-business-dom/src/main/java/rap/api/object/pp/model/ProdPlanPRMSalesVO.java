/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanPRMSalesVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanPRMSalesVO extends BusinessObjectMasterVO {
    private String        prmCode                                           ;
    private String        year                                              ;
    private String        scope                                             ;
    private String        regionCode                                        ;
    private String        countryCodeType                                   ;
    private String        countryCode                                       ;
    private BigDecimal    accumSalesQty                                      = new BigDecimal(0);
    private BigDecimal    accumGrossSalesAmt                                 = new BigDecimal(0);
    private BigDecimal    accumNsalesAmt                                     = new BigDecimal(0);
    private BigDecimal    accumOiAmt                                         = new BigDecimal(0);
    private Float         prmOpRate                                         ;


    public void    setPrmCode(String prmCode){
        this.prmCode = prmCode;
    }
    public void    setYear(String year){
        this.year = year;
    }
    public void    setScope(String scope){
        this.scope = scope;
    }
    public void    setRegionCode(String regionCode){
        this.regionCode = regionCode;
    }
    public void    setCountryCodeType(String countryCodeType){
        this.countryCodeType = countryCodeType;
    }
    public void    setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public void    setAccumSalesQty(BigDecimal accumSalesQty){
        this.accumSalesQty = accumSalesQty;
    }
    public void    setAccumGrossSalesAmt(BigDecimal accumGrossSalesAmt){
        this.accumGrossSalesAmt = accumGrossSalesAmt;
    }
    public void    setAccumNsalesAmt(BigDecimal accumNsalesAmt){
        this.accumNsalesAmt = accumNsalesAmt;
    }
    public void    setAccumOiAmt(BigDecimal accumOiAmt){
        this.accumOiAmt = accumOiAmt;
    }
    public void    setPrmOpRate(Float prmOpRate){
        this.prmOpRate = prmOpRate;
    }
    public String getPrmCode(){
        return prmCode;
    }
    public String getYear(){
        return year;
    }
    public String getScope(){
        return scope;
    }
    public String getRegionCode(){
        return regionCode;
    }
    public String getCountryCodeType(){
        return countryCodeType;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public BigDecimal getAccumSalesQty(){
        return accumSalesQty;
    }
    public BigDecimal getAccumGrossSalesAmt(){
        return accumGrossSalesAmt;
    }
    public BigDecimal getAccumNsalesAmt(){
        return accumNsalesAmt;
    }
    public BigDecimal getAccumOiAmt(){
        return accumOiAmt;
    }
    public Float getPrmOpRate(){
        return prmOpRate;
    }
}

