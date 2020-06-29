/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECMSResearchVO.java
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
public class ECMSResearchVO extends BusinessObjectMasterVO {
    private String        companyName                                       ;
    private String        scenarioCode                                      ;
    private String        serviceWork                                       ;
    private String        detailedAccount                                   ;
    private String        contractPeriodFrom                                ;
    private String        contractPeriodTo                                  ;
    private String        yyyy                                              ;
    private Float         totalMember                                       ;
    private Float         special                                           ;
    private Float         high                                              ;
    private Float         middle                                            ;
    private Float         beginner                                          ;
    private String        ecmsresCurrency                                   ;
    private Float         expectedUnitPrice                                 ;
    private Float         calculationAmount                                 ;
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


    public void    setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public void    setScenarioCode(String scenarioCode){
        this.scenarioCode = scenarioCode;
    }
    public void    setServiceWork(String serviceWork){
        this.serviceWork = serviceWork;
    }
    public void    setDetailedAccount(String detailedAccount){
        this.detailedAccount = detailedAccount;
    }
    public void    setContractPeriodFrom(String contractPeriodFrom){
        this.contractPeriodFrom = contractPeriodFrom;
    }
    public void    setContractPeriodTo(String contractPeriodTo){
        this.contractPeriodTo = contractPeriodTo;
    }
    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setTotalMember(Float totalMember){
        this.totalMember = totalMember;
    }
    public void    setSpecial(Float special){
        this.special = special;
    }
    public void    setHigh(Float high){
        this.high = high;
    }
    public void    setMiddle(Float middle){
        this.middle = middle;
    }
    public void    setBeginner(Float beginner){
        this.beginner = beginner;
    }
    public void    setEcmsresCurrency(String ecmsresCurrency){
        this.ecmsresCurrency = ecmsresCurrency;
    }
    public void    setExpectedUnitPrice(Float expectedUnitPrice){
        this.expectedUnitPrice = expectedUnitPrice;
    }
    public void    setCalculationAmount(Float calculationAmount){
        this.calculationAmount = calculationAmount;
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
    public String getCompanyName(){
        return companyName;
    }
    public String getScenarioCode(){
        return scenarioCode;
    }
    public String getServiceWork(){
        return serviceWork;
    }
    public String getDetailedAccount(){
        return detailedAccount;
    }
    public String getContractPeriodFrom(){
        return contractPeriodFrom;
    }
    public String getContractPeriodTo(){
        return contractPeriodTo;
    }
    public String getYyyy(){
        return yyyy;
    }
    public Float getTotalMember(){
        return totalMember;
    }
    public Float getSpecial(){
        return special;
    }
    public Float getHigh(){
        return high;
    }
    public Float getMiddle(){
        return middle;
    }
    public Float getBeginner(){
        return beginner;
    }
    public String getEcmsresCurrency(){
        return ecmsresCurrency;
    }
    public Float getExpectedUnitPrice(){
        return expectedUnitPrice;
    }
    public Float getCalculationAmount(){
        return calculationAmount;
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

