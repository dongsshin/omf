/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PMSProjects2B2BVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PMSProjects2B2BVO extends BusinessObjectMasterVO {
    private String        projectsObid                                      ;
    private String        awardb2bProjectCodeObid                           ;
    private Float         developCost                                       ;
    private Float         developMm                                         ;
    private String        ecmsProjectName                                   ;
    private String        ecmsProjectCode                                   ;
    private String        version                                           ;
    private String        beforeVersion                                     ;
    private Date          versionCreateDate                                 ;
    private Date          versionEndDate                                    ;
    private String        versionStatus                                     ;
    private String        refYearlyExchangeVersion                          ;
    private String        devEvent                                          ;
    private String        auCode                                            ;
    private String        auName                                            ;
    private String        activityStatus                                    ;
    private String        supStartYyyymm                                    ;
    private String        supEndYyyymm                                      ;
    private String        devStartYyyymm                                    ;
    private String        devEndYyyymm                                      ;
    private String        personInChargeMain                                ;
    private String        personInChargeSub                                 ;
    private String        personInCharge3rd                                 ;
    private String        personInCharge4th                                 ;
    private String        customerCode                                      ;
    private String        customerName                                      ;
    private String        fileExistYn                                       ;


    public void    setProjectsObid(String projectsObid){
        this.projectsObid = projectsObid;
    }
    public void    setAwardb2bProjectCodeObid(String awardb2bProjectCodeObid){
        this.awardb2bProjectCodeObid = awardb2bProjectCodeObid;
    }
    public void    setDevelopCost(Float developCost){
        this.developCost = developCost;
    }
    public void    setDevelopMm(Float developMm){
        this.developMm = developMm;
    }
    public void    setEcmsProjectName(String ecmsProjectName){
        this.ecmsProjectName = ecmsProjectName;
    }
    public void    setEcmsProjectCode(String ecmsProjectCode){
        this.ecmsProjectCode = ecmsProjectCode;
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
    public void    setVersionEndDate(Date versionEndDate){
        this.versionEndDate = versionEndDate;
    }
    public void    setVersionEndDate(String    versionEndDate){
        this.versionEndDate = this.omcConvertStr2Date(versionEndDate);
    }
    public void    setVersionStatus(String versionStatus){
        this.versionStatus = versionStatus;
    }
    public void    setRefYearlyExchangeVersion(String refYearlyExchangeVersion){
        this.refYearlyExchangeVersion = refYearlyExchangeVersion;
    }
    public void    setDevEvent(String devEvent){
        this.devEvent = devEvent;
    }
    public void    setAuCode(String auCode){
        this.auCode = auCode;
    }
    public void    setAuName(String auName){
        this.auName = auName;
    }
    public void    setActivityStatus(String activityStatus){
        this.activityStatus = activityStatus;
    }
    public void    setSupStartYyyymm(String supStartYyyymm){
        this.supStartYyyymm = supStartYyyymm;
    }
    public void    setSupEndYyyymm(String supEndYyyymm){
        this.supEndYyyymm = supEndYyyymm;
    }
    public void    setDevStartYyyymm(String devStartYyyymm){
        this.devStartYyyymm = devStartYyyymm;
    }
    public void    setDevEndYyyymm(String devEndYyyymm){
        this.devEndYyyymm = devEndYyyymm;
    }
    public void    setPersonInChargeMain(String personInChargeMain){
        this.personInChargeMain = personInChargeMain;
    }
    public void    setPersonInChargeSub(String personInChargeSub){
        this.personInChargeSub = personInChargeSub;
    }
    public void    setPersonInCharge3rd(String personInCharge3rd){
        this.personInCharge3rd = personInCharge3rd;
    }
    public void    setPersonInCharge4th(String personInCharge4th){
        this.personInCharge4th = personInCharge4th;
    }
    public void    setCustomerCode(String customerCode){
        this.customerCode = customerCode;
    }
    public void    setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public void    setFileExistYn(String fileExistYn){
        this.fileExistYn = fileExistYn;
    }
    public String getProjectsObid(){
        return projectsObid;
    }
    public String getAwardb2bProjectCodeObid(){
        return awardb2bProjectCodeObid;
    }
    public Float getDevelopCost(){
        return developCost;
    }
    public Float getDevelopMm(){
        return developMm;
    }
    public String getEcmsProjectName(){
        return ecmsProjectName;
    }
    public String getEcmsProjectCode(){
        return ecmsProjectCode;
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
    public Date getVersionEndDate(){
        return versionEndDate;
    }
    public String getVersionStatus(){
        return versionStatus;
    }
    public String getRefYearlyExchangeVersion(){
        return refYearlyExchangeVersion;
    }
    public String getDevEvent(){
        return devEvent;
    }
    public String getAuCode(){
        return auCode;
    }
    public String getAuName(){
        return auName;
    }
    public String getActivityStatus(){
        return activityStatus;
    }
    public String getSupStartYyyymm(){
        return supStartYyyymm;
    }
    public String getSupEndYyyymm(){
        return supEndYyyymm;
    }
    public String getDevStartYyyymm(){
        return devStartYyyymm;
    }
    public String getDevEndYyyymm(){
        return devEndYyyymm;
    }
    public String getPersonInChargeMain(){
        return personInChargeMain;
    }
    public String getPersonInChargeSub(){
        return personInChargeSub;
    }
    public String getPersonInCharge3rd(){
        return personInCharge3rd;
    }
    public String getPersonInCharge4th(){
        return personInCharge4th;
    }
    public String getCustomerCode(){
        return customerCode;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getFileExistYn(){
        return fileExistYn;
    }
}

