/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasCustomerSuffixByDivisionVO.java
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
public class HasCustomerSuffixByDivisionVO extends BusinessRelationObjectVO {
    private String        customerName                                      ;
    private String        customerSuffixCode                                ;
    private String        countryName                                       ;
    private String        valueCode                                         ;
    private String        useFlag                                           ;
    private String        regionName                                        ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        divisionCode                                      ;


    public void    setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public void    setCustomerSuffixCode(String customerSuffixCode){
        this.customerSuffixCode = customerSuffixCode;
    }
    public void    setCountryName(String countryName){
        this.countryName = countryName;
    }
    public void    setValueCode(String valueCode){
        this.valueCode = valueCode;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setRegionName(String regionName){
        this.regionName = regionName;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public void    setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
    public void    setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
    public void    setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getCustomerSuffixCode(){
        return customerSuffixCode;
    }
    public String getCountryName(){
        return countryName;
    }
    public String getValueCode(){
        return valueCode;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getRegionName(){
        return regionName;
    }
    public String getAttribute1(){
        return attribute1;
    }
    public String getAttribute2(){
        return attribute2;
    }
    public String getAttribute3(){
        return attribute3;
    }
    public String getAttribute4(){
        return attribute4;
    }
    public String getAttribute5(){
        return attribute5;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
}

