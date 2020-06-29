/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : RollingForcastVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.closing.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class RollingForcastVO extends BusinessObjectMasterVO {
    private String        year                                              ;
    private String        managementGroup                                   ;
    private String        legalEntityName                                   ;
    private String        accountingUnitCode                                ;
    private String        departmentCode                                    ;
    private String        basicMonth                                        ;
    private String        projectCode                                       ;
    private String        accountCode                                       ;
    private BigDecimal    m1Amt                                              = new BigDecimal(0);
    private BigDecimal    m2Amt                                              = new BigDecimal(0);
    private BigDecimal    m3Amt                                              = new BigDecimal(0);
    private BigDecimal    m4Amt                                              = new BigDecimal(0);
    private BigDecimal    m5Amt                                              = new BigDecimal(0);
    private BigDecimal    m6Amt                                              = new BigDecimal(0);
    private BigDecimal    m7Amt                                              = new BigDecimal(0);
    private BigDecimal    m8Amt                                              = new BigDecimal(0);
    private BigDecimal    m9Amt                                              = new BigDecimal(0);
    private BigDecimal    m10Amt                                             = new BigDecimal(0);
    private BigDecimal    m11Amt                                             = new BigDecimal(0);
    private BigDecimal    m12Amt                                             = new BigDecimal(0);
    private BigDecimal    nextYearM1Amt                                      = new BigDecimal(0);
    private BigDecimal    nextYearM2Amt                                      = new BigDecimal(0);
    private BigDecimal    nextYearM3Amt                                      = new BigDecimal(0);
    private String        comments                                          ;
    private String        saveStatus                                        ;


    public void    setYear(String year){
        this.year = year;
    }
    public void    setManagementGroup(String managementGroup){
        this.managementGroup = managementGroup;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setBasicMonth(String basicMonth){
        this.basicMonth = basicMonth;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setM1Amt(BigDecimal m1Amt){
        this.m1Amt = m1Amt;
    }
    public void    setM2Amt(BigDecimal m2Amt){
        this.m2Amt = m2Amt;
    }
    public void    setM3Amt(BigDecimal m3Amt){
        this.m3Amt = m3Amt;
    }
    public void    setM4Amt(BigDecimal m4Amt){
        this.m4Amt = m4Amt;
    }
    public void    setM5Amt(BigDecimal m5Amt){
        this.m5Amt = m5Amt;
    }
    public void    setM6Amt(BigDecimal m6Amt){
        this.m6Amt = m6Amt;
    }
    public void    setM7Amt(BigDecimal m7Amt){
        this.m7Amt = m7Amt;
    }
    public void    setM8Amt(BigDecimal m8Amt){
        this.m8Amt = m8Amt;
    }
    public void    setM9Amt(BigDecimal m9Amt){
        this.m9Amt = m9Amt;
    }
    public void    setM10Amt(BigDecimal m10Amt){
        this.m10Amt = m10Amt;
    }
    public void    setM11Amt(BigDecimal m11Amt){
        this.m11Amt = m11Amt;
    }
    public void    setM12Amt(BigDecimal m12Amt){
        this.m12Amt = m12Amt;
    }
    public void    setNextYearM1Amt(BigDecimal nextYearM1Amt){
        this.nextYearM1Amt = nextYearM1Amt;
    }
    public void    setNextYearM2Amt(BigDecimal nextYearM2Amt){
        this.nextYearM2Amt = nextYearM2Amt;
    }
    public void    setNextYearM3Amt(BigDecimal nextYearM3Amt){
        this.nextYearM3Amt = nextYearM3Amt;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public void    setSaveStatus(String saveStatus){
        this.saveStatus = saveStatus;
    }
    public String getYear(){
        return year;
    }
    public String getManagementGroup(){
        return managementGroup;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getBasicMonth(){
        return basicMonth;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public BigDecimal getM1Amt(){
        return m1Amt;
    }
    public BigDecimal getM2Amt(){
        return m2Amt;
    }
    public BigDecimal getM3Amt(){
        return m3Amt;
    }
    public BigDecimal getM4Amt(){
        return m4Amt;
    }
    public BigDecimal getM5Amt(){
        return m5Amt;
    }
    public BigDecimal getM6Amt(){
        return m6Amt;
    }
    public BigDecimal getM7Amt(){
        return m7Amt;
    }
    public BigDecimal getM8Amt(){
        return m8Amt;
    }
    public BigDecimal getM9Amt(){
        return m9Amt;
    }
    public BigDecimal getM10Amt(){
        return m10Amt;
    }
    public BigDecimal getM11Amt(){
        return m11Amt;
    }
    public BigDecimal getM12Amt(){
        return m12Amt;
    }
    public BigDecimal getNextYearM1Amt(){
        return nextYearM1Amt;
    }
    public BigDecimal getNextYearM2Amt(){
        return nextYearM2Amt;
    }
    public BigDecimal getNextYearM3Amt(){
        return nextYearM3Amt;
    }
    public String getComments(){
        return comments;
    }
    public String getSaveStatus(){
        return saveStatus;
    }
}

