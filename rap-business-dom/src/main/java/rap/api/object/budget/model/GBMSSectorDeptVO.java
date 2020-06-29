/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : GBMSSectorDeptVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class GBMSSectorDeptVO extends BusinessObjectMasterVO {
    private String        periodYyyy                                        ;
    private String        legalEntityName                                   ;
    private String        accountingUnitCode                                ;
    private String        investSectorCode                                  ;
    private String        investSectorEngName                               ;
    private String        investSectorLocalName                             ;
    private String        departmentCode                                    ;
    private String        enabledFlag                                       ;
    private Date          startDateActive                                   ;
    private Date          endDateActive                                     ;


    public void    setPeriodYyyy(String periodYyyy){
        this.periodYyyy = periodYyyy;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public void    setInvestSectorCode(String investSectorCode){
        this.investSectorCode = investSectorCode;
    }
    public void    setInvestSectorEngName(String investSectorEngName){
        this.investSectorEngName = investSectorEngName;
    }
    public void    setInvestSectorLocalName(String investSectorLocalName){
        this.investSectorLocalName = investSectorLocalName;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setEnabledFlag(String enabledFlag){
        this.enabledFlag = enabledFlag;
    }
    public void    setStartDateActive(Date startDateActive){
        this.startDateActive = startDateActive;
    }
    public void    setStartDateActive(String    startDateActive){
        this.startDateActive = this.omcConvertStr2Date(startDateActive);
    }
    public void    setEndDateActive(Date endDateActive){
        this.endDateActive = endDateActive;
    }
    public void    setEndDateActive(String    endDateActive){
        this.endDateActive = this.omcConvertStr2Date(endDateActive);
    }
    public String getPeriodYyyy(){
        return periodYyyy;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
    public String getInvestSectorCode(){
        return investSectorCode;
    }
    public String getInvestSectorEngName(){
        return investSectorEngName;
    }
    public String getInvestSectorLocalName(){
        return investSectorLocalName;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getEnabledFlag(){
        return enabledFlag;
    }
    public Date getStartDateActive(){
        return startDateActive;
    }
    public Date getEndDateActive(){
        return endDateActive;
    }
}

