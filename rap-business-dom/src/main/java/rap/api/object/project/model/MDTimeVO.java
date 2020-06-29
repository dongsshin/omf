/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MDTimeVO.java
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
public class MDTimeVO extends BusinessObjectMasterVO {
    private String        topDeptCode                                       ;
    private String        inputStartHour                                    ;
    private String        inputStartMin                                     ;
    private String        apprFromDay                                       ;
    private String        apprFromHour                                      ;
    private String        apprFromMin                                       ;
    private String        apprToDay                                         ;
    private String        apprToHour                                        ;
    private String        apprToMin                                         ;
    private String        apprAutoDay                                       ;
    private String        apprAutoHour                                      ;
    private String        apprAutoMin                                       ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;


    public void    setTopDeptCode(String topDeptCode){
        this.topDeptCode = topDeptCode;
    }
    public void    setInputStartHour(String inputStartHour){
        this.inputStartHour = inputStartHour;
    }
    public void    setInputStartMin(String inputStartMin){
        this.inputStartMin = inputStartMin;
    }
    public void    setApprFromDay(String apprFromDay){
        this.apprFromDay = apprFromDay;
    }
    public void    setApprFromHour(String apprFromHour){
        this.apprFromHour = apprFromHour;
    }
    public void    setApprFromMin(String apprFromMin){
        this.apprFromMin = apprFromMin;
    }
    public void    setApprToDay(String apprToDay){
        this.apprToDay = apprToDay;
    }
    public void    setApprToHour(String apprToHour){
        this.apprToHour = apprToHour;
    }
    public void    setApprToMin(String apprToMin){
        this.apprToMin = apprToMin;
    }
    public void    setApprAutoDay(String apprAutoDay){
        this.apprAutoDay = apprAutoDay;
    }
    public void    setApprAutoHour(String apprAutoHour){
        this.apprAutoHour = apprAutoHour;
    }
    public void    setApprAutoMin(String apprAutoMin){
        this.apprAutoMin = apprAutoMin;
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
    public String getTopDeptCode(){
        return topDeptCode;
    }
    public String getInputStartHour(){
        return inputStartHour;
    }
    public String getInputStartMin(){
        return inputStartMin;
    }
    public String getApprFromDay(){
        return apprFromDay;
    }
    public String getApprFromHour(){
        return apprFromHour;
    }
    public String getApprFromMin(){
        return apprFromMin;
    }
    public String getApprToDay(){
        return apprToDay;
    }
    public String getApprToHour(){
        return apprToHour;
    }
    public String getApprToMin(){
        return apprToMin;
    }
    public String getApprAutoDay(){
        return apprAutoDay;
    }
    public String getApprAutoHour(){
        return apprAutoHour;
    }
    public String getApprAutoMin(){
        return apprAutoMin;
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

