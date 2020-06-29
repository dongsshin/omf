/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ActualProjectMDVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ActualProjectMDVO extends BusinessRelationObjectVO {
    private String        divisionCode                                      ;
    private String        yyyyMm                                            ;
    private String        eventCode                                         ;
    private String        activityCode                                      ;
    private String        jobClass                                          ;
    private Float         mm                                                ;
    private String        status                                            ;
    private String        checkYn                                           ;
    private String        mailComment                                       ;
    private String        mainCompetency                                    ;
    private String        middleCompetency                                  ;
    private String        subCompetency                                     ;
    private String        yyyy                                              ;
    private String        week                                              ;
    private Date          dd                                                ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setYyyyMm(String yyyyMm){
        this.yyyyMm = yyyyMm;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setActivityCode(String activityCode){
        this.activityCode = activityCode;
    }
    public void    setJobClass(String jobClass){
        this.jobClass = jobClass;
    }
    public void    setMm(Float mm){
        this.mm = mm;
    }
    public void    setStatus(String status){
        this.status = status;
    }
    public void    setCheckYn(String checkYn){
        this.checkYn = checkYn;
    }
    public void    setMailComment(String mailComment){
        this.mailComment = mailComment;
    }
    public void    setMainCompetency(String mainCompetency){
        this.mainCompetency = mainCompetency;
    }
    public void    setMiddleCompetency(String middleCompetency){
        this.middleCompetency = middleCompetency;
    }
    public void    setSubCompetency(String subCompetency){
        this.subCompetency = subCompetency;
    }
    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setWeek(String week){
        this.week = week;
    }
    public void    setDd(Date dd){
        this.dd = dd;
    }
    public void    setDd(String    dd){
        this.dd = this.omcConvertStr2Date(dd);
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getYyyyMm(){
        return yyyyMm;
    }
    public String getEventCode(){
        return eventCode;
    }
    public String getActivityCode(){
        return activityCode;
    }
    public String getJobClass(){
        return jobClass;
    }
    public Float getMm(){
        return mm;
    }
    public String getStatus(){
        return status;
    }
    public String getCheckYn(){
        return checkYn;
    }
    public String getMailComment(){
        return mailComment;
    }
    public String getMainCompetency(){
        return mainCompetency;
    }
    public String getMiddleCompetency(){
        return middleCompetency;
    }
    public String getSubCompetency(){
        return subCompetency;
    }
    public String getYyyy(){
        return yyyy;
    }
    public String getWeek(){
        return week;
    }
    public Date getDd(){
        return dd;
    }
}

