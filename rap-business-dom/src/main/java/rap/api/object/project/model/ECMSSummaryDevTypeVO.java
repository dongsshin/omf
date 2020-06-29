/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECMSSummaryDevTypeVO.java
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
public class ECMSSummaryDevTypeVO extends BusinessObjectMasterVO {
    private String        ecmsSenarioCode                                   ;
    private String        ecmsDevtypeCode                                   ;
    private String        ecmsAccountCategory                               ;
    private String        yyyy                                              ;
    private Float         ecmsAmount                                        ;
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


    public void    setEcmsSenarioCode(String ecmsSenarioCode){
        this.ecmsSenarioCode = ecmsSenarioCode;
    }
    public void    setEcmsDevtypeCode(String ecmsDevtypeCode){
        this.ecmsDevtypeCode = ecmsDevtypeCode;
    }
    public void    setEcmsAccountCategory(String ecmsAccountCategory){
        this.ecmsAccountCategory = ecmsAccountCategory;
    }
    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setEcmsAmount(Float ecmsAmount){
        this.ecmsAmount = ecmsAmount;
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
    public String getEcmsSenarioCode(){
        return ecmsSenarioCode;
    }
    public String getEcmsDevtypeCode(){
        return ecmsDevtypeCode;
    }
    public String getEcmsAccountCategory(){
        return ecmsAccountCategory;
    }
    public String getYyyy(){
        return yyyy;
    }
    public Float getEcmsAmount(){
        return ecmsAmount;
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
}

