/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectEventVO.java
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
public class ProjectEventVO extends BusinessObjectMasterVO {
    private String        eventType                                          = "Milestone";
    private Date          startDate                                         ;
    private Date          endDate                                           ;
    private Integer       noticeBeforeDays                                  ;
    private String        completeYn                                        ;


    public void    setEventType(String eventType){
        this.eventType = eventType;
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
    public void    setNoticeBeforeDays(Integer noticeBeforeDays){
        this.noticeBeforeDays = noticeBeforeDays;
    }
    public void    setCompleteYn(String completeYn){
        this.completeYn = completeYn;
    }
    public String getEventType(){
        return eventType;
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public Integer getNoticeBeforeDays(){
        return noticeBeforeDays;
    }
    public String getCompleteYn(){
        return completeYn;
    }
}

