/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectBudgetOperPlanVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.brm.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectBudgetOperPlanVO extends BusinessObjectMasterVO {
    private String        budgetGubun                                       ;
    private String        pmsProjectCode                                    ;
    private String        periodYyyymm                                      ;
    private String        reportGroup                                       ;
    private String        accountDepartment                                 ;
    private String        rfqHrTitleName                                    ;
    private Date          creationDate                                      ;
    private String        accountCode                                       ;
    private String        yyyy                                              ;
    private String        pcurrencyCode                                     ;
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
    private String        attribute1                                         = "DUMMY";
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;


    public void    setBudgetGubun(String budgetGubun){
        this.budgetGubun = budgetGubun;
    }
    public void    setPmsProjectCode(String pmsProjectCode){
        this.pmsProjectCode = pmsProjectCode;
    }
    public void    setPeriodYyyymm(String periodYyyymm){
        this.periodYyyymm = periodYyyymm;
    }
    public void    setReportGroup(String reportGroup){
        this.reportGroup = reportGroup;
    }
    public void    setAccountDepartment(String accountDepartment){
        this.accountDepartment = accountDepartment;
    }
    public void    setRfqHrTitleName(String rfqHrTitleName){
        this.rfqHrTitleName = rfqHrTitleName;
    }
    public void    setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }
    public void    setCreationDate(String    creationDate){
        this.creationDate = this.omcConvertStr2Date(creationDate);
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setPcurrencyCode(String pcurrencyCode){
        this.pcurrencyCode = pcurrencyCode;
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
    public String getBudgetGubun(){
        return budgetGubun;
    }
    public String getPmsProjectCode(){
        return pmsProjectCode;
    }
    public String getPeriodYyyymm(){
        return periodYyyymm;
    }
    public String getReportGroup(){
        return reportGroup;
    }
    public String getAccountDepartment(){
        return accountDepartment;
    }
    public String getRfqHrTitleName(){
        return rfqHrTitleName;
    }
    public Date getCreationDate(){
        return creationDate;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public String getYyyy(){
        return yyyy;
    }
    public String getPcurrencyCode(){
        return pcurrencyCode;
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
}

