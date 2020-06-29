/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MWScheduleMasterVO.java
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
public class MWScheduleMasterVO extends BusinessObjectMasterVO {
    private String        yyyyMm                                            ;
    private String        week                                              ;
    private Date          weekStartDate                                     ;
    private Date          weekEndDate                                       ;
    private Integer       weekdays                                           = 0;
    private Date          inputStartDate                                    ;
    private Date          inputEndDate                                      ;
    private String        weekCloseYn                                        = "N";
    private String        manageYyyymm                                      ;


    public void    setYyyyMm(String yyyyMm){
        this.yyyyMm = yyyyMm;
    }
    public void    setWeek(String week){
        this.week = week;
    }
    public void    setWeekStartDate(Date weekStartDate){
        this.weekStartDate = weekStartDate;
    }
    public void    setWeekStartDate(String    weekStartDate){
        this.weekStartDate = this.omcConvertStr2Date(weekStartDate);
    }
    public void    setWeekEndDate(Date weekEndDate){
        this.weekEndDate = weekEndDate;
    }
    public void    setWeekEndDate(String    weekEndDate){
        this.weekEndDate = this.omcConvertStr2Date(weekEndDate);
    }
    public void    setWeekdays(Integer weekdays){
        this.weekdays = weekdays;
    }
    public void    setInputStartDate(Date inputStartDate){
        this.inputStartDate = inputStartDate;
    }
    public void    setInputStartDate(String    inputStartDate){
        this.inputStartDate = this.omcConvertStr2Date(inputStartDate);
    }
    public void    setInputEndDate(Date inputEndDate){
        this.inputEndDate = inputEndDate;
    }
    public void    setInputEndDate(String    inputEndDate){
        this.inputEndDate = this.omcConvertStr2Date(inputEndDate);
    }
    public void    setWeekCloseYn(String weekCloseYn){
        this.weekCloseYn = weekCloseYn;
    }
    public void    setManageYyyymm(String manageYyyymm){
        this.manageYyyymm = manageYyyymm;
    }
    public String getYyyyMm(){
        return yyyyMm;
    }
    public String getWeek(){
        return week;
    }
    public Date getWeekStartDate(){
        return weekStartDate;
    }
    public Date getWeekEndDate(){
        return weekEndDate;
    }
    public Integer getWeekdays(){
        return weekdays;
    }
    public Date getInputStartDate(){
        return inputStartDate;
    }
    public Date getInputEndDate(){
        return inputEndDate;
    }
    public String getWeekCloseYn(){
        return weekCloseYn;
    }
    public String getManageYyyymm(){
        return manageYyyymm;
    }
}

