/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CustomerMasterForPDRVO.java
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
public class CustomerMasterForPDRVO extends BusinessObjectMasterVO {
    private String        customerCode                                      ;
    private String        customerShortName                                 ;
    private String        fullName                                          ;
    private String        customerAddress1                                  ;
    private String        customerAddress2                                  ;
    private String        customerAddress3                                  ;
    private String        regionCode                                        ;
    private String        regionName                                        ;
    private String        countryCode                                       ;
    private String        countryName                                       ;
    private String        currencyCode                                      ;
    private String        customerBrandName                                 ;
    private String        subsidiaryCode                                    ;
    private String        useFlag                                           ;


    public void    setCustomerCode(String customerCode){
        this.customerCode = customerCode;
    }
    public void    setCustomerShortName(String customerShortName){
        this.customerShortName = customerShortName;
    }
    public void    setFullName(String fullName){
        this.fullName = fullName;
    }
    public void    setCustomerAddress1(String customerAddress1){
        this.customerAddress1 = customerAddress1;
    }
    public void    setCustomerAddress2(String customerAddress2){
        this.customerAddress2 = customerAddress2;
    }
    public void    setCustomerAddress3(String customerAddress3){
        this.customerAddress3 = customerAddress3;
    }
    public void    setRegionCode(String regionCode){
        this.regionCode = regionCode;
    }
    public void    setRegionName(String regionName){
        this.regionName = regionName;
    }
    public void    setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public void    setCountryName(String countryName){
        this.countryName = countryName;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setCustomerBrandName(String customerBrandName){
        this.customerBrandName = customerBrandName;
    }
    public void    setSubsidiaryCode(String subsidiaryCode){
        this.subsidiaryCode = subsidiaryCode;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public String getCustomerCode(){
        return customerCode;
    }
    public String getCustomerShortName(){
        return customerShortName;
    }
    public String getFullName(){
        return fullName;
    }
    public String getCustomerAddress1(){
        return customerAddress1;
    }
    public String getCustomerAddress2(){
        return customerAddress2;
    }
    public String getCustomerAddress3(){
        return customerAddress3;
    }
    public String getRegionCode(){
        return regionCode;
    }
    public String getRegionName(){
        return regionName;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public String getCountryName(){
        return countryName;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public String getCustomerBrandName(){
        return customerBrandName;
    }
    public String getSubsidiaryCode(){
        return subsidiaryCode;
    }
    public String getUseFlag(){
        return useFlag;
    }
}

