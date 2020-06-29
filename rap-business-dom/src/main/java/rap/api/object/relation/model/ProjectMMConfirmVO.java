/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectMMConfirmVO.java
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
public class ProjectMMConfirmVO extends BusinessRelationObjectVO {
    private String        divisionCode                                      ;
    private String        yyyyMm                                            ;
    private String        eventCode                                         ;
    private String        activityCode                                      ;
    private String        jobClass                                          ;
    private String        status                                            ;
    private String        inputComment                                      ;


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
    public void    setStatus(String status){
        this.status = status;
    }
    public void    setInputComment(String inputComment){
        this.inputComment = inputComment;
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
    public String getStatus(){
        return status;
    }
    public String getInputComment(){
        return inputComment;
    }
}

