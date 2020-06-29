/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DivisionPeriodVO.java
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
public class DivisionPeriodVO extends OrganizationsVO {
    private String        divisionCode                                      ;
    private String        departmentCode                                    ;
    private String        businessUnitCode                                  ;
    private String        startYyyy                                         ;
    private String        endYyyy                                           ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setBusinessUnitCode(String businessUnitCode){
        this.businessUnitCode = businessUnitCode;
    }
    public void    setStartYyyy(String startYyyy){
        this.startYyyy = startYyyy;
    }
    public void    setEndYyyy(String endYyyy){
        this.endYyyy = endYyyy;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getBusinessUnitCode(){
        return businessUnitCode;
    }
    public String getStartYyyy(){
        return startYyyy;
    }
    public String getEndYyyy(){
        return endYyyy;
    }
}

