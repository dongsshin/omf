/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClProjectGradeMMDetailVO.java
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
public class ClProjectGradeMMDetailVO extends BusinessObjectMasterVO {
    private String        managementGroup                                   ;
    private String        userId                                            ;
    private String        subsidiary                                        ;
    private String        closeYm                                           ;
    private String        accountingUnitCode                                ;
    private String        departmentCode                                    ;
    private String        projectCode                                       ;
    private String        jobGrade                                          ;
    private Float         manMonth                                          ;


    public void    setManagementGroup(String managementGroup){
        this.managementGroup = managementGroup;
    }
    public void    setUserId(String userId){
        this.userId = userId;
    }
    public void    setSubsidiary(String subsidiary){
        this.subsidiary = subsidiary;
    }
    public void    setCloseYm(String closeYm){
        this.closeYm = closeYm;
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
    public void    setJobGrade(String jobGrade){
        this.jobGrade = jobGrade;
    }
    public void    setManMonth(Float manMonth){
        this.manMonth = manMonth;
    }
    public String getManagementGroup(){
        return managementGroup;
    }
    public String getUserId(){
        return userId;
    }
    public String getSubsidiary(){
        return subsidiary;
    }
    public String getCloseYm(){
        return closeYm;
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
    public String getJobGrade(){
        return jobGrade;
    }
    public Float getManMonth(){
        return manMonth;
    }
}

