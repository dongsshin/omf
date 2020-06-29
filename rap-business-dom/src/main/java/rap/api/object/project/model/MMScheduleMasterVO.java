/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MMScheduleMasterVO.java
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
public class MMScheduleMasterVO extends BusinessObjectMasterVO {
    private String        yyyyMm                                            ;
    private Date          inputStartDate                                    ;
    private Date          inputEndDate                                      ;
    private Date          checkStartDate                                    ;
    private Date          checkEndDate                                      ;
    private String        monthCloseYn                                       = "N";


    public void    setYyyyMm(String yyyyMm){
        this.yyyyMm = yyyyMm;
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
    public void    setCheckStartDate(Date checkStartDate){
        this.checkStartDate = checkStartDate;
    }
    public void    setCheckStartDate(String    checkStartDate){
        this.checkStartDate = this.omcConvertStr2Date(checkStartDate);
    }
    public void    setCheckEndDate(Date checkEndDate){
        this.checkEndDate = checkEndDate;
    }
    public void    setCheckEndDate(String    checkEndDate){
        this.checkEndDate = this.omcConvertStr2Date(checkEndDate);
    }
    public void    setMonthCloseYn(String monthCloseYn){
        this.monthCloseYn = monthCloseYn;
    }
    public String getYyyyMm(){
        return yyyyMm;
    }
    public Date getInputStartDate(){
        return inputStartDate;
    }
    public Date getInputEndDate(){
        return inputEndDate;
    }
    public Date getCheckStartDate(){
        return checkStartDate;
    }
    public Date getCheckEndDate(){
        return checkEndDate;
    }
    public String getMonthCloseYn(){
        return monthCloseYn;
    }
}

