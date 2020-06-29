/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECMSDevCostRecoveryVO.java
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
public class ECMSDevCostRecoveryVO extends BusinessObjectMasterVO {
    private String        ecmsDevelopment                                   ;
    private String        scenarioCode                                      ;
    private String        ecmsDevCostRecovery                               ;
    private String        yyyy                                              ;
    private String        ecmsresCurrency                                   ;
    private Float         expectedAmount                                    ;
    private Float         expectedAmountKrw                                 ;
    private Float         sumByYear                                         ;
    private Float         ecmsresJan                                        ;
    private Float         ecmsresFeb                                        ;
    private Float         ecmsresMar                                        ;
    private Float         ecmsresApr                                        ;
    private Float         ecmsresMay                                        ;
    private Float         ecmsresJun                                        ;
    private Float         ecmsresJul                                        ;
    private Float         ecmsresAug                                        ;
    private Float         ecmsresSep                                        ;
    private Float         ecmsresOct                                        ;
    private Float         ecmsresNov                                        ;
    private Float         ecmsresDec                                        ;
    private String        ecmsresComment                                    ;
    private String        departmentName                                    ;
    private String        departmentCode                                    ;


    public void    setEcmsDevelopment(String ecmsDevelopment){
        this.ecmsDevelopment = ecmsDevelopment;
    }
    public void    setScenarioCode(String scenarioCode){
        this.scenarioCode = scenarioCode;
    }
    public void    setEcmsDevCostRecovery(String ecmsDevCostRecovery){
        this.ecmsDevCostRecovery = ecmsDevCostRecovery;
    }
    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setEcmsresCurrency(String ecmsresCurrency){
        this.ecmsresCurrency = ecmsresCurrency;
    }
    public void    setExpectedAmount(Float expectedAmount){
        this.expectedAmount = expectedAmount;
    }
    public void    setExpectedAmountKrw(Float expectedAmountKrw){
        this.expectedAmountKrw = expectedAmountKrw;
    }
    public void    setSumByYear(Float sumByYear){
        this.sumByYear = sumByYear;
    }
    public void    setEcmsresJan(Float ecmsresJan){
        this.ecmsresJan = ecmsresJan;
    }
    public void    setEcmsresFeb(Float ecmsresFeb){
        this.ecmsresFeb = ecmsresFeb;
    }
    public void    setEcmsresMar(Float ecmsresMar){
        this.ecmsresMar = ecmsresMar;
    }
    public void    setEcmsresApr(Float ecmsresApr){
        this.ecmsresApr = ecmsresApr;
    }
    public void    setEcmsresMay(Float ecmsresMay){
        this.ecmsresMay = ecmsresMay;
    }
    public void    setEcmsresJun(Float ecmsresJun){
        this.ecmsresJun = ecmsresJun;
    }
    public void    setEcmsresJul(Float ecmsresJul){
        this.ecmsresJul = ecmsresJul;
    }
    public void    setEcmsresAug(Float ecmsresAug){
        this.ecmsresAug = ecmsresAug;
    }
    public void    setEcmsresSep(Float ecmsresSep){
        this.ecmsresSep = ecmsresSep;
    }
    public void    setEcmsresOct(Float ecmsresOct){
        this.ecmsresOct = ecmsresOct;
    }
    public void    setEcmsresNov(Float ecmsresNov){
        this.ecmsresNov = ecmsresNov;
    }
    public void    setEcmsresDec(Float ecmsresDec){
        this.ecmsresDec = ecmsresDec;
    }
    public void    setEcmsresComment(String ecmsresComment){
        this.ecmsresComment = ecmsresComment;
    }
    public void    setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public String getEcmsDevelopment(){
        return ecmsDevelopment;
    }
    public String getScenarioCode(){
        return scenarioCode;
    }
    public String getEcmsDevCostRecovery(){
        return ecmsDevCostRecovery;
    }
    public String getYyyy(){
        return yyyy;
    }
    public String getEcmsresCurrency(){
        return ecmsresCurrency;
    }
    public Float getExpectedAmount(){
        return expectedAmount;
    }
    public Float getExpectedAmountKrw(){
        return expectedAmountKrw;
    }
    public Float getSumByYear(){
        return sumByYear;
    }
    public Float getEcmsresJan(){
        return ecmsresJan;
    }
    public Float getEcmsresFeb(){
        return ecmsresFeb;
    }
    public Float getEcmsresMar(){
        return ecmsresMar;
    }
    public Float getEcmsresApr(){
        return ecmsresApr;
    }
    public Float getEcmsresMay(){
        return ecmsresMay;
    }
    public Float getEcmsresJun(){
        return ecmsresJun;
    }
    public Float getEcmsresJul(){
        return ecmsresJul;
    }
    public Float getEcmsresAug(){
        return ecmsresAug;
    }
    public Float getEcmsresSep(){
        return ecmsresSep;
    }
    public Float getEcmsresOct(){
        return ecmsresOct;
    }
    public Float getEcmsresNov(){
        return ecmsresNov;
    }
    public Float getEcmsresDec(){
        return ecmsresDec;
    }
    public String getEcmsresComment(){
        return ecmsresComment;
    }
    public String getDepartmentName(){
        return departmentName;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
}

