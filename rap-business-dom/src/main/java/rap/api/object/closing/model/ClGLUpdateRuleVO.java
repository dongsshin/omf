/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClGLUpdateRuleVO.java
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
public class ClGLUpdateRuleVO extends BusinessObjectMasterVO {
    private String        closingYm                                         ;
    private String        managementGroup                                   ;
    private Integer       priority                                          ;
    private String        legalEntityName                                   ;
    private String        accountingUnitCode                                ;
    private String        departmentCode                                    ;
    private String        projectCode                                       ;
    private String        accountCode                                       ;
    private String        toAccountingUnitCode                              ;
    private String        toDepartmentCode                                  ;
    private String        toProjectCode                                     ;
    private String        toAccountCode                                     ;
    private String        comments                                          ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        useYn                                             ;


    public void    setClosingYm(String closingYm){
        this.closingYm = closingYm;
    }
    public void    setManagementGroup(String managementGroup){
        this.managementGroup = managementGroup;
    }
    public void    setPriority(Integer priority){
        this.priority = priority;
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
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setToAccountingUnitCode(String toAccountingUnitCode){
        this.toAccountingUnitCode = toAccountingUnitCode;
    }
    public void    setToDepartmentCode(String toDepartmentCode){
        this.toDepartmentCode = toDepartmentCode;
    }
    public void    setToProjectCode(String toProjectCode){
        this.toProjectCode = toProjectCode;
    }
    public void    setToAccountCode(String toAccountCode){
        this.toAccountCode = toAccountCode;
    }
    public void    setComments(String comments){
        this.comments = comments;
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
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getClosingYm(){
        return closingYm;
    }
    public String getManagementGroup(){
        return managementGroup;
    }
    public Integer getPriority(){
        return priority;
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
    public String getAccountCode(){
        return accountCode;
    }
    public String getToAccountingUnitCode(){
        return toAccountingUnitCode;
    }
    public String getToDepartmentCode(){
        return toDepartmentCode;
    }
    public String getToProjectCode(){
        return toProjectCode;
    }
    public String getToAccountCode(){
        return toAccountCode;
    }
    public String getComments(){
        return comments;
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
    public String getUseYn(){
        return useYn;
    }
}

