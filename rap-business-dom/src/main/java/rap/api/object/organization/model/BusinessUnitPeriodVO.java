/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BusinessUnitPeriodVO.java
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
public class BusinessUnitPeriodVO extends OrganizationsVO {
    private String        startYyyy                                         ;
    private String        endYyyy                                           ;
    private String        businessUnitCode                                  ;


    public void    setStartYyyy(String startYyyy){
        this.startYyyy = startYyyy;
    }
    public void    setEndYyyy(String endYyyy){
        this.endYyyy = endYyyy;
    }
    public void    setBusinessUnitCode(String businessUnitCode){
        this.businessUnitCode = businessUnitCode;
    }
    public String getStartYyyy(){
        return startYyyy;
    }
    public String getEndYyyy(){
        return endYyyy;
    }
    public String getBusinessUnitCode(){
        return businessUnitCode;
    }
}

