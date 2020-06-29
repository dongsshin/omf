/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClDeprAllocPjtRateVO.java
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
public class ClDeprAllocPjtRateVO extends BusinessObjectMasterVO {
    private String        closingYm                                         ;
    private String        managementGroup                                   ;
    private String        legalEntityName                                   ;
    private String        accountingUnitCode                                ;
    private String        departmentCode                                    ;
    private String        projectCode                                       ;
    private BigDecimal    distRate                                           = new BigDecimal(0);


    public void    setClosingYm(String closingYm){
        this.closingYm = closingYm;
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
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setDistRate(BigDecimal distRate){
        this.distRate = distRate;
    }
    public String getClosingYm(){
        return closingYm;
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
    public String getProjectCode(){
        return projectCode;
    }
    public BigDecimal getDistRate(){
        return distRate;
    }
}

