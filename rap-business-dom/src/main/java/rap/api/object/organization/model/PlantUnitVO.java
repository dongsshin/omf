/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PlantUnitVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.model;


import rap.api.object.organization.model.OrganizationsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PlantUnitVO extends OrganizationsVO {
    private String        businessUnitCode                                  ;
    private String        divisionCode                                      ;
    private String        affiliateCode                                     ;


    public void    setBusinessUnitCode(String businessUnitCode){
        this.businessUnitCode = businessUnitCode;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public String getBusinessUnitCode(){
        return businessUnitCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
}

