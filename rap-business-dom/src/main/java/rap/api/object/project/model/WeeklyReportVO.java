/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WeeklyReportVO.java
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
public class WeeklyReportVO extends BusinessObjectMasterVO {
    private Date          startDate                                         ;
    private Date          endDate                                           ;
    private String        startYyyymmdd                                     ;
    private String        endYyyymmdd                                       ;
    private String        reportStatus                                       = "Draft";
    private String        reportType                                         = "Project";
    private String        projectSignal                                     ;
    private String        summary                                           ;
    private String        divisionCode                                      ;


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
    public void    setStartYyyymmdd(String startYyyymmdd){
        this.startYyyymmdd = startYyyymmdd;
    }
    public void    setEndYyyymmdd(String endYyyymmdd){
        this.endYyyymmdd = endYyyymmdd;
    }
    public void    setReportStatus(String reportStatus){
        this.reportStatus = reportStatus;
    }
    public void    setReportType(String reportType){
        this.reportType = reportType;
    }
    public void    setProjectSignal(String projectSignal){
        this.projectSignal = projectSignal;
    }
    public void    setSummary(String summary){
        this.summary = summary;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public String getStartYyyymmdd(){
        return startYyyymmdd;
    }
    public String getEndYyyymmdd(){
        return endYyyymmdd;
    }
    public String getReportStatus(){
        return reportStatus;
    }
    public String getReportType(){
        return reportType;
    }
    public String getProjectSignal(){
        return projectSignal;
    }
    public String getSummary(){
        return summary;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
}

