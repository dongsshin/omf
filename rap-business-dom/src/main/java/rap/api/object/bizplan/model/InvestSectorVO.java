/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : InvestSectorVO.java
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
public class InvestSectorVO extends BusinessObjectMasterVO {
    private String        periodYyyy                                        ;
    private String        legalEntityName                                   ;
    private String        accountingUnitCode                                ;
    private String        investSectorCode                                  ;
    private String        investSectorEngName                               ;
    private String        investSectorLocalName                             ;
    private String        enabledFlag                                       ;
    private Date          startDate                                         ;
    private Date          endDate                                           ;


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
    public void    setEnabledFlag(String enabledFlag){
        this.enabledFlag = enabledFlag;
    }
    public void    setStartDate(Date startDate){
        this.startDate = startDate;
    }
    public void    setStartDate(String    startDate){
        this.startDate = this.omcConvertStr2Date(startDate);
    }
    public void    setEndDate(Date endDate){
        this.endDate = endDate;
    }
    public void    setEndDate(String    endDate){
        this.endDate = this.omcConvertStr2Date(endDate);
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
    public String getEnabledFlag(){
        return enabledFlag;
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
}

