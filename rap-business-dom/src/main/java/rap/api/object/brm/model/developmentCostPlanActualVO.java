/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : developmentCostPlanActualVO.java
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
public class developmentCostPlanActualVO extends BusinessObjectMasterVO {
    private String        ecmsProjectCode                                   ;
    private String        ecmsProjectName                                   ;
    private String        opNo                                              ;
    private String        version                                           ;
    private String        beforeVersion                                     ;
    private Date          versionCreateDate                                 ;
    private String        versionStatus                                     ;
    private Date          versionComplteDate                                ;
    private String        activityStatus                                    ;
    private String        scenarioCode                                      ;
    private String        scenarioName                                      ;
    private String        developType                                       ;
    private String        scenarioConfirmYn                                 ;
    private String        pmsProjectCode                                    ;
    private String        periodYyyymm                                      ;
    private String        yyyymm                                            ;
    private String        accountCode                                       ;
    private BigDecimal    amount                                             = new BigDecimal(0);


    public void    setEcmsProjectCode(String ecmsProjectCode){
        this.ecmsProjectCode = ecmsProjectCode;
    }
    public void    setEcmsProjectName(String ecmsProjectName){
        this.ecmsProjectName = ecmsProjectName;
    }
    public void    setOpNo(String opNo){
        this.opNo = opNo;
    }
    public void    setVersion(String version){
        this.version = version;
    }
    public void    setBeforeVersion(String beforeVersion){
        this.beforeVersion = beforeVersion;
    }
    public void    setVersionCreateDate(Date versionCreateDate){
        this.versionCreateDate = versionCreateDate;
    }
    public void    setVersionCreateDate(String    versionCreateDate){
        this.versionCreateDate = this.omcConvertStr2Date(versionCreateDate);
    }
    public void    setVersionStatus(String versionStatus){
        this.versionStatus = versionStatus;
    }
    public void    setVersionComplteDate(Date versionComplteDate){
        this.versionComplteDate = versionComplteDate;
    }
    public void    setVersionComplteDate(String    versionComplteDate){
        this.versionComplteDate = this.omcConvertStr2Date(versionComplteDate);
    }
    public void    setActivityStatus(String activityStatus){
        this.activityStatus = activityStatus;
    }
    public void    setScenarioCode(String scenarioCode){
        this.scenarioCode = scenarioCode;
    }
    public void    setScenarioName(String scenarioName){
        this.scenarioName = scenarioName;
    }
    public void    setDevelopType(String developType){
        this.developType = developType;
    }
    public void    setScenarioConfirmYn(String scenarioConfirmYn){
        this.scenarioConfirmYn = scenarioConfirmYn;
    }
    public void    setPmsProjectCode(String pmsProjectCode){
        this.pmsProjectCode = pmsProjectCode;
    }
    public void    setPeriodYyyymm(String periodYyyymm){
        this.periodYyyymm = periodYyyymm;
    }
    public void    setYyyymm(String yyyymm){
        this.yyyymm = yyyymm;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setAmount(BigDecimal amount){
        this.amount = amount;
    }
    public String getEcmsProjectCode(){
        return ecmsProjectCode;
    }
    public String getEcmsProjectName(){
        return ecmsProjectName;
    }
    public String getOpNo(){
        return opNo;
    }
    public String getVersion(){
        return version;
    }
    public String getBeforeVersion(){
        return beforeVersion;
    }
    public Date getVersionCreateDate(){
        return versionCreateDate;
    }
    public String getVersionStatus(){
        return versionStatus;
    }
    public Date getVersionComplteDate(){
        return versionComplteDate;
    }
    public String getActivityStatus(){
        return activityStatus;
    }
    public String getScenarioCode(){
        return scenarioCode;
    }
    public String getScenarioName(){
        return scenarioName;
    }
    public String getDevelopType(){
        return developType;
    }
    public String getScenarioConfirmYn(){
        return scenarioConfirmYn;
    }
    public String getPmsProjectCode(){
        return pmsProjectCode;
    }
    public String getPeriodYyyymm(){
        return periodYyyymm;
    }
    public String getYyyymm(){
        return yyyymm;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public BigDecimal getAmount(){
        return amount;
    }
}

