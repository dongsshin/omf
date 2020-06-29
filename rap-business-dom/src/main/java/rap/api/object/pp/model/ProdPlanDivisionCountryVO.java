/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanDivisionCountryVO.java
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
public class ProdPlanDivisionCountryVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        productType                                       ;
    private String        countryCode                                       ;
    private String        countryCodeType                                   ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProductType(String productType){
        this.productType = productType;
    }
    public void    setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public void    setCountryCodeType(String countryCodeType){
        this.countryCodeType = countryCodeType;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProductType(){
        return productType;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public String getCountryCodeType(){
        return countryCodeType;
    }
}

