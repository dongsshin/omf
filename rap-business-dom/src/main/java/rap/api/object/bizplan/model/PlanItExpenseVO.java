/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PlanItExpenseVO.java
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
public class PlanItExpenseVO extends BusinessObjectMasterVO {
    private Integer       ifSeq                                             ;
    private Integer       seqNo                                             ;
    private String        versionObid                                       ;
    private String        fromObid                                          ;
    private String        planYear                                          ;
    private String        projectCode                                       ;
    private String        projectName                                       ;
    private String        accountingUnitCode                                ;
    private String        departmentCode                                    ;
    private String        accountCode                                       ;
    private Integer       activityId                                        ;
    private String        calcContents                                      ;
    private String        definition                                        ;
    private String        currencyCode                                      ;
    private BigDecimal    totAmount                                          = new BigDecimal(0);
    private BigDecimal    month01Amount                                      = new BigDecimal(0);
    private BigDecimal    month02Amount                                      = new BigDecimal(0);
    private BigDecimal    month03Amount                                      = new BigDecimal(0);
    private BigDecimal    month04Amount                                      = new BigDecimal(0);
    private BigDecimal    month05Amount                                      = new BigDecimal(0);
    private BigDecimal    month06Amount                                      = new BigDecimal(0);
    private BigDecimal    month07Amount                                      = new BigDecimal(0);
    private BigDecimal    month08Amount                                      = new BigDecimal(0);
    private BigDecimal    month09Amount                                      = new BigDecimal(0);
    private BigDecimal    month10Amount                                      = new BigDecimal(0);
    private BigDecimal    month11Amount                                      = new BigDecimal(0);
    private BigDecimal    month12Amount                                      = new BigDecimal(0);
    private String        registDepartmentCode                              ;
    private String        registEmployeeNo                                  ;
    private Date          registDate                                        ;
    private String        ifYn                                              ;
    private String        errorYn                                           ;
    private String        errorMsg                                          ;
    private String        itmsProjectCode                                   ;
    private String        itDepartmentCode                                  ;
    private Integer       ifVersionNo                                       ;
    private String        budgetVersionObid                                 ;
    private String        budgetObid                                        ;
    private String        centerCode                                        ;


    public void    setIfSeq(Integer ifSeq){
        this.ifSeq = ifSeq;
    }
    public void    setSeqNo(Integer seqNo){
        this.seqNo = seqNo;
    }
    public void    setVersionObid(String versionObid){
        this.versionObid = versionObid;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setProjectName(String projectName){
        this.projectName = projectName;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setActivityId(Integer activityId){
        this.activityId = activityId;
    }
    public void    setCalcContents(String calcContents){
        this.calcContents = calcContents;
    }
    public void    setDefinition(String definition){
        this.definition = definition;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setTotAmount(BigDecimal totAmount){
        this.totAmount = totAmount;
    }
    public void    setMonth01Amount(BigDecimal month01Amount){
        this.month01Amount = month01Amount;
    }
    public void    setMonth02Amount(BigDecimal month02Amount){
        this.month02Amount = month02Amount;
    }
    public void    setMonth03Amount(BigDecimal month03Amount){
        this.month03Amount = month03Amount;
    }
    public void    setMonth04Amount(BigDecimal month04Amount){
        this.month04Amount = month04Amount;
    }
    public void    setMonth05Amount(BigDecimal month05Amount){
        this.month05Amount = month05Amount;
    }
    public void    setMonth06Amount(BigDecimal month06Amount){
        this.month06Amount = month06Amount;
    }
    public void    setMonth07Amount(BigDecimal month07Amount){
        this.month07Amount = month07Amount;
    }
    public void    setMonth08Amount(BigDecimal month08Amount){
        this.month08Amount = month08Amount;
    }
    public void    setMonth09Amount(BigDecimal month09Amount){
        this.month09Amount = month09Amount;
    }
    public void    setMonth10Amount(BigDecimal month10Amount){
        this.month10Amount = month10Amount;
    }
    public void    setMonth11Amount(BigDecimal month11Amount){
        this.month11Amount = month11Amount;
    }
    public void    setMonth12Amount(BigDecimal month12Amount){
        this.month12Amount = month12Amount;
    }
    public void    setRegistDepartmentCode(String registDepartmentCode){
        this.registDepartmentCode = registDepartmentCode;
    }
    public void    setRegistEmployeeNo(String registEmployeeNo){
        this.registEmployeeNo = registEmployeeNo;
    }
    public void    setRegistDate(Date registDate){
        this.registDate = registDate;
    }
    public void    setRegistDate(String    registDate){
        this.registDate = this.omcConvertStr2Date(registDate);
    }
    public void    setIfYn(String ifYn){
        this.ifYn = ifYn;
    }
    public void    setErrorYn(String errorYn){
        this.errorYn = errorYn;
    }
    public void    setErrorMsg(String errorMsg){
        this.errorMsg = errorMsg;
    }
    public void    setItmsProjectCode(String itmsProjectCode){
        this.itmsProjectCode = itmsProjectCode;
    }
    public void    setItDepartmentCode(String itDepartmentCode){
        this.itDepartmentCode = itDepartmentCode;
    }
    public void    setIfVersionNo(Integer ifVersionNo){
        this.ifVersionNo = ifVersionNo;
    }
    public void    setBudgetVersionObid(String budgetVersionObid){
        this.budgetVersionObid = budgetVersionObid;
    }
    public void    setBudgetObid(String budgetObid){
        this.budgetObid = budgetObid;
    }
    public void    setCenterCode(String centerCode){
        this.centerCode = centerCode;
    }
    public Integer getIfSeq(){
        return ifSeq;
    }
    public Integer getSeqNo(){
        return seqNo;
    }
    public String getVersionObid(){
        return versionObid;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getPlanYear(){
        return planYear;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getProjectName(){
        return projectName;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public Integer getActivityId(){
        return activityId;
    }
    public String getCalcContents(){
        return calcContents;
    }
    public String getDefinition(){
        return definition;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public BigDecimal getTotAmount(){
        return totAmount;
    }
    public BigDecimal getMonth01Amount(){
        return month01Amount;
    }
    public BigDecimal getMonth02Amount(){
        return month02Amount;
    }
    public BigDecimal getMonth03Amount(){
        return month03Amount;
    }
    public BigDecimal getMonth04Amount(){
        return month04Amount;
    }
    public BigDecimal getMonth05Amount(){
        return month05Amount;
    }
    public BigDecimal getMonth06Amount(){
        return month06Amount;
    }
    public BigDecimal getMonth07Amount(){
        return month07Amount;
    }
    public BigDecimal getMonth08Amount(){
        return month08Amount;
    }
    public BigDecimal getMonth09Amount(){
        return month09Amount;
    }
    public BigDecimal getMonth10Amount(){
        return month10Amount;
    }
    public BigDecimal getMonth11Amount(){
        return month11Amount;
    }
    public BigDecimal getMonth12Amount(){
        return month12Amount;
    }
    public String getRegistDepartmentCode(){
        return registDepartmentCode;
    }
    public String getRegistEmployeeNo(){
        return registEmployeeNo;
    }
    public Date getRegistDate(){
        return registDate;
    }
    public String getIfYn(){
        return ifYn;
    }
    public String getErrorYn(){
        return errorYn;
    }
    public String getErrorMsg(){
        return errorMsg;
    }
    public String getItmsProjectCode(){
        return itmsProjectCode;
    }
    public String getItDepartmentCode(){
        return itDepartmentCode;
    }
    public Integer getIfVersionNo(){
        return ifVersionNo;
    }
    public String getBudgetVersionObid(){
        return budgetVersionObid;
    }
    public String getBudgetObid(){
        return budgetObid;
    }
    public String getCenterCode(){
        return centerCode;
    }
}

