/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AbstractHrDepartmentVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.model;


import rap.api.object.organization.model.AbstractDepartmentVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class AbstractHrDepartmentVO extends AbstractDepartmentVO {
    private String        divisionCode                                      ;
    private String        affiliateCode                                     ;
    private String        accountingUnitCode                                ;
    private String        departmentCode                                    ;
    private String        leaderEmployeeNo                                  ;
    private String        organizationLevel                                 ;
    private String        hrDeptCode                                        ;
    private String        hrUpperDeptCode                                   ;
    private String        dateFrom                                          ;
    private String        dateTo                                            ;
    private String        mmMngGubun                                        ;
    private String        hrDeptYn                                          ;
    private String        lowestDeptYn                                       = "N";
    private String        hrAccessDeptCode                                   = "N";


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setLeaderEmployeeNo(String leaderEmployeeNo){
        this.leaderEmployeeNo = leaderEmployeeNo;
    }
    public void    setOrganizationLevel(String organizationLevel){
        this.organizationLevel = organizationLevel;
    }
    public void    setHrDeptCode(String hrDeptCode){
        this.hrDeptCode = hrDeptCode;
    }
    public void    setHrUpperDeptCode(String hrUpperDeptCode){
        this.hrUpperDeptCode = hrUpperDeptCode;
    }
    public void    setDateFrom(String dateFrom){
        this.dateFrom = dateFrom;
    }
    public void    setDateTo(String dateTo){
        this.dateTo = dateTo;
    }
    public void    setMmMngGubun(String mmMngGubun){
        this.mmMngGubun = mmMngGubun;
    }
    public void    setHrDeptYn(String hrDeptYn){
        this.hrDeptYn = hrDeptYn;
    }
    public void    setLowestDeptYn(String lowestDeptYn){
        this.lowestDeptYn = lowestDeptYn;
    }
    public void    setHrAccessDeptCode(String hrAccessDeptCode){
        this.hrAccessDeptCode = hrAccessDeptCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getLeaderEmployeeNo(){
        return leaderEmployeeNo;
    }
    public String getOrganizationLevel(){
        return organizationLevel;
    }
    public String getHrDeptCode(){
        return hrDeptCode;
    }
    public String getHrUpperDeptCode(){
        return hrUpperDeptCode;
    }
    public String getDateFrom(){
        return dateFrom;
    }
    public String getDateTo(){
        return dateTo;
    }
    public String getMmMngGubun(){
        return mmMngGubun;
    }
    public String getHrDeptYn(){
        return hrDeptYn;
    }
    public String getLowestDeptYn(){
        return lowestDeptYn;
    }
    public String getHrAccessDeptCode(){
        return hrAccessDeptCode;
    }
}

