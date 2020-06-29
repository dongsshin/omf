/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PDRSalesTargetVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PDRSalesTargetVO extends BusinessObjectMasterVO {
    private String        salesModelName                                    ;
    private String        salesModelSuffixCode                              ;
    private String        countryCode                                       ;
    private String        sellInDate                                        ;
    private String        currency                                          ;
    private String        unitType                                          ;
    private BigDecimal    salesInMonth1                                      = new BigDecimal(0);
    private BigDecimal    salesInMonth2                                      = new BigDecimal(0);
    private BigDecimal    salesInMonth3                                      = new BigDecimal(0);
    private BigDecimal    salesInMonth4                                      = new BigDecimal(0);
    private BigDecimal    salesInMonth5                                      = new BigDecimal(0);
    private BigDecimal    salesInMonth6                                      = new BigDecimal(0);
    private BigDecimal    salesInMonth7                                      = new BigDecimal(0);
    private BigDecimal    salesInMonth8                                      = new BigDecimal(0);
    private BigDecimal    salesInMonth9                                      = new BigDecimal(0);
    private BigDecimal    salesInMonth10                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth11                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth12                                     = new BigDecimal(0);
    private BigDecimal    plc                                                = new BigDecimal(0);
    private String        transferFlag                                      ;
    private Date          transferDate                                      ;
    private String        affiliateCode                                     ;
    private Integer       salesTargetSeq                                    ;
    private String        divisionCode                                      ;
    private BigDecimal    salesInMonth13                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth14                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth15                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth16                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth17                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth18                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth19                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth20                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth21                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth22                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth23                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth24                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth25                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth26                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth27                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth28                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth29                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth30                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth31                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth32                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth33                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth34                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth35                                     = new BigDecimal(0);
    private BigDecimal    salesInMonth36                                     = new BigDecimal(0);


    public void    setSalesModelName(String salesModelName){
        this.salesModelName = salesModelName;
    }
    public void    setSalesModelSuffixCode(String salesModelSuffixCode){
        this.salesModelSuffixCode = salesModelSuffixCode;
    }
    public void    setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public void    setSellInDate(String sellInDate){
        this.sellInDate = sellInDate;
    }
    public void    setCurrency(String currency){
        this.currency = currency;
    }
    public void    setUnitType(String unitType){
        this.unitType = unitType;
    }
    public void    setSalesInMonth1(BigDecimal salesInMonth1){
        this.salesInMonth1 = salesInMonth1;
    }
    public void    setSalesInMonth2(BigDecimal salesInMonth2){
        this.salesInMonth2 = salesInMonth2;
    }
    public void    setSalesInMonth3(BigDecimal salesInMonth3){
        this.salesInMonth3 = salesInMonth3;
    }
    public void    setSalesInMonth4(BigDecimal salesInMonth4){
        this.salesInMonth4 = salesInMonth4;
    }
    public void    setSalesInMonth5(BigDecimal salesInMonth5){
        this.salesInMonth5 = salesInMonth5;
    }
    public void    setSalesInMonth6(BigDecimal salesInMonth6){
        this.salesInMonth6 = salesInMonth6;
    }
    public void    setSalesInMonth7(BigDecimal salesInMonth7){
        this.salesInMonth7 = salesInMonth7;
    }
    public void    setSalesInMonth8(BigDecimal salesInMonth8){
        this.salesInMonth8 = salesInMonth8;
    }
    public void    setSalesInMonth9(BigDecimal salesInMonth9){
        this.salesInMonth9 = salesInMonth9;
    }
    public void    setSalesInMonth10(BigDecimal salesInMonth10){
        this.salesInMonth10 = salesInMonth10;
    }
    public void    setSalesInMonth11(BigDecimal salesInMonth11){
        this.salesInMonth11 = salesInMonth11;
    }
    public void    setSalesInMonth12(BigDecimal salesInMonth12){
        this.salesInMonth12 = salesInMonth12;
    }
    public void    setPlc(BigDecimal plc){
        this.plc = plc;
    }
    public void    setTransferFlag(String transferFlag){
        this.transferFlag = transferFlag;
    }
    public void    setTransferDate(Date transferDate){
        this.transferDate = transferDate;
    }
    public void    setTransferDate(String    transferDate){
        this.transferDate = this.omcConvertStr2Date(transferDate);
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setSalesTargetSeq(Integer salesTargetSeq){
        this.salesTargetSeq = salesTargetSeq;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setSalesInMonth13(BigDecimal salesInMonth13){
        this.salesInMonth13 = salesInMonth13;
    }
    public void    setSalesInMonth14(BigDecimal salesInMonth14){
        this.salesInMonth14 = salesInMonth14;
    }
    public void    setSalesInMonth15(BigDecimal salesInMonth15){
        this.salesInMonth15 = salesInMonth15;
    }
    public void    setSalesInMonth16(BigDecimal salesInMonth16){
        this.salesInMonth16 = salesInMonth16;
    }
    public void    setSalesInMonth17(BigDecimal salesInMonth17){
        this.salesInMonth17 = salesInMonth17;
    }
    public void    setSalesInMonth18(BigDecimal salesInMonth18){
        this.salesInMonth18 = salesInMonth18;
    }
    public void    setSalesInMonth19(BigDecimal salesInMonth19){
        this.salesInMonth19 = salesInMonth19;
    }
    public void    setSalesInMonth20(BigDecimal salesInMonth20){
        this.salesInMonth20 = salesInMonth20;
    }
    public void    setSalesInMonth21(BigDecimal salesInMonth21){
        this.salesInMonth21 = salesInMonth21;
    }
    public void    setSalesInMonth22(BigDecimal salesInMonth22){
        this.salesInMonth22 = salesInMonth22;
    }
    public void    setSalesInMonth23(BigDecimal salesInMonth23){
        this.salesInMonth23 = salesInMonth23;
    }
    public void    setSalesInMonth24(BigDecimal salesInMonth24){
        this.salesInMonth24 = salesInMonth24;
    }
    public void    setSalesInMonth25(BigDecimal salesInMonth25){
        this.salesInMonth25 = salesInMonth25;
    }
    public void    setSalesInMonth26(BigDecimal salesInMonth26){
        this.salesInMonth26 = salesInMonth26;
    }
    public void    setSalesInMonth27(BigDecimal salesInMonth27){
        this.salesInMonth27 = salesInMonth27;
    }
    public void    setSalesInMonth28(BigDecimal salesInMonth28){
        this.salesInMonth28 = salesInMonth28;
    }
    public void    setSalesInMonth29(BigDecimal salesInMonth29){
        this.salesInMonth29 = salesInMonth29;
    }
    public void    setSalesInMonth30(BigDecimal salesInMonth30){
        this.salesInMonth30 = salesInMonth30;
    }
    public void    setSalesInMonth31(BigDecimal salesInMonth31){
        this.salesInMonth31 = salesInMonth31;
    }
    public void    setSalesInMonth32(BigDecimal salesInMonth32){
        this.salesInMonth32 = salesInMonth32;
    }
    public void    setSalesInMonth33(BigDecimal salesInMonth33){
        this.salesInMonth33 = salesInMonth33;
    }
    public void    setSalesInMonth34(BigDecimal salesInMonth34){
        this.salesInMonth34 = salesInMonth34;
    }
    public void    setSalesInMonth35(BigDecimal salesInMonth35){
        this.salesInMonth35 = salesInMonth35;
    }
    public void    setSalesInMonth36(BigDecimal salesInMonth36){
        this.salesInMonth36 = salesInMonth36;
    }
    public String getSalesModelName(){
        return salesModelName;
    }
    public String getSalesModelSuffixCode(){
        return salesModelSuffixCode;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public String getSellInDate(){
        return sellInDate;
    }
    public String getCurrency(){
        return currency;
    }
    public String getUnitType(){
        return unitType;
    }
    public BigDecimal getSalesInMonth1(){
        return salesInMonth1;
    }
    public BigDecimal getSalesInMonth2(){
        return salesInMonth2;
    }
    public BigDecimal getSalesInMonth3(){
        return salesInMonth3;
    }
    public BigDecimal getSalesInMonth4(){
        return salesInMonth4;
    }
    public BigDecimal getSalesInMonth5(){
        return salesInMonth5;
    }
    public BigDecimal getSalesInMonth6(){
        return salesInMonth6;
    }
    public BigDecimal getSalesInMonth7(){
        return salesInMonth7;
    }
    public BigDecimal getSalesInMonth8(){
        return salesInMonth8;
    }
    public BigDecimal getSalesInMonth9(){
        return salesInMonth9;
    }
    public BigDecimal getSalesInMonth10(){
        return salesInMonth10;
    }
    public BigDecimal getSalesInMonth11(){
        return salesInMonth11;
    }
    public BigDecimal getSalesInMonth12(){
        return salesInMonth12;
    }
    public BigDecimal getPlc(){
        return plc;
    }
    public String getTransferFlag(){
        return transferFlag;
    }
    public Date getTransferDate(){
        return transferDate;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public Integer getSalesTargetSeq(){
        return salesTargetSeq;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public BigDecimal getSalesInMonth13(){
        return salesInMonth13;
    }
    public BigDecimal getSalesInMonth14(){
        return salesInMonth14;
    }
    public BigDecimal getSalesInMonth15(){
        return salesInMonth15;
    }
    public BigDecimal getSalesInMonth16(){
        return salesInMonth16;
    }
    public BigDecimal getSalesInMonth17(){
        return salesInMonth17;
    }
    public BigDecimal getSalesInMonth18(){
        return salesInMonth18;
    }
    public BigDecimal getSalesInMonth19(){
        return salesInMonth19;
    }
    public BigDecimal getSalesInMonth20(){
        return salesInMonth20;
    }
    public BigDecimal getSalesInMonth21(){
        return salesInMonth21;
    }
    public BigDecimal getSalesInMonth22(){
        return salesInMonth22;
    }
    public BigDecimal getSalesInMonth23(){
        return salesInMonth23;
    }
    public BigDecimal getSalesInMonth24(){
        return salesInMonth24;
    }
    public BigDecimal getSalesInMonth25(){
        return salesInMonth25;
    }
    public BigDecimal getSalesInMonth26(){
        return salesInMonth26;
    }
    public BigDecimal getSalesInMonth27(){
        return salesInMonth27;
    }
    public BigDecimal getSalesInMonth28(){
        return salesInMonth28;
    }
    public BigDecimal getSalesInMonth29(){
        return salesInMonth29;
    }
    public BigDecimal getSalesInMonth30(){
        return salesInMonth30;
    }
    public BigDecimal getSalesInMonth31(){
        return salesInMonth31;
    }
    public BigDecimal getSalesInMonth32(){
        return salesInMonth32;
    }
    public BigDecimal getSalesInMonth33(){
        return salesInMonth33;
    }
    public BigDecimal getSalesInMonth34(){
        return salesInMonth34;
    }
    public BigDecimal getSalesInMonth35(){
        return salesInMonth35;
    }
    public BigDecimal getSalesInMonth36(){
        return salesInMonth36;
    }
}

