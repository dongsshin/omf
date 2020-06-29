/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectBudgetPlanScheduleVO.java
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
public class ProjectBudgetPlanScheduleVO extends BusinessObjectMasterVO {
    private String        yyyyMm                                            ;
    private String        requestEmployeeNo                                 ;
    private Date          jobRequestDate                                    ;
    private Date          completedDate                                     ;
    private String        completedFlag                                      = "N";
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;


    public void    setYyyyMm(String yyyyMm){
        this.yyyyMm = yyyyMm;
    }
    public void    setRequestEmployeeNo(String requestEmployeeNo){
        this.requestEmployeeNo = requestEmployeeNo;
    }
    public void    setJobRequestDate(Date jobRequestDate){
        this.jobRequestDate = jobRequestDate;
    }
    public void    setJobRequestDate(String    jobRequestDate){
        this.jobRequestDate = this.omcConvertStr2Date(jobRequestDate);
    }
    public void    setCompletedDate(Date completedDate){
        this.completedDate = completedDate;
    }
    public void    setCompletedDate(String    completedDate){
        this.completedDate = this.omcConvertStr2Date(completedDate);
    }
    public void    setCompletedFlag(String completedFlag){
        this.completedFlag = completedFlag;
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
    public String getYyyyMm(){
        return yyyyMm;
    }
    public String getRequestEmployeeNo(){
        return requestEmployeeNo;
    }
    public Date getJobRequestDate(){
        return jobRequestDate;
    }
    public Date getCompletedDate(){
        return completedDate;
    }
    public String getCompletedFlag(){
        return completedFlag;
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

