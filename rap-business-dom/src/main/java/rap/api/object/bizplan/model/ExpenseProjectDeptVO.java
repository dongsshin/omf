/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ExpenseProjectDeptVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ExpenseProjectDeptVO extends BusinessObjectMasterVO {
    private String        projectCode                                       ;
    private String        departmentCode                                    ;
    private String        accountingUnitCode                                ;
    private String        legalEntityName                                   ;
    private String        enabledFlag                                       ;


    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setEnabledFlag(String enabledFlag){
        this.enabledFlag = enabledFlag;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getEnabledFlag(){
        return enabledFlag;
    }
}

