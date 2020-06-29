/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectScheduleVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectScheduleVO extends BusinessObjectVO {
    private String        isNew                                              = "Y";
    private String        appliedWbsLifeCycle                                = "None";
    private String        currentPhase                                       = "Y";
    private Date          baselinedStartDate                                ;
    private Date          baselinedEndDate                                  ;
    private Date          planStartDate                                     ;
    private Date          planEndDate                                       ;
    private Date          actualStartDate                                   ;
    private Date          actualEndDate                                     ;


    public void    setIsNew(String isNew){
        this.isNew = isNew;
    }
    public void    setAppliedWbsLifeCycle(String appliedWbsLifeCycle){
        this.appliedWbsLifeCycle = appliedWbsLifeCycle;
    }
    public void    setCurrentPhase(String currentPhase){
        this.currentPhase = currentPhase;
    }
    public void    setBaselinedStartDate(Date baselinedStartDate){
        this.baselinedStartDate = baselinedStartDate;
    }
    public void    setBaselinedStartDate(String    baselinedStartDate){
        this.baselinedStartDate = this.omcConvertStr2Date(baselinedStartDate);
    }
    public void    setBaselinedEndDate(Date baselinedEndDate){
        this.baselinedEndDate = baselinedEndDate;
    }
    public void    setBaselinedEndDate(String    baselinedEndDate){
        this.baselinedEndDate = this.omcConvertStr2Date(baselinedEndDate);
    }
    public void    setPlanStartDate(Date planStartDate){
        this.planStartDate = planStartDate;
    }
    public void    setPlanStartDate(String    planStartDate){
        this.planStartDate = this.omcConvertStr2Date(planStartDate);
    }
    public void    setPlanEndDate(Date planEndDate){
        this.planEndDate = planEndDate;
    }
    public void    setPlanEndDate(String    planEndDate){
        this.planEndDate = this.omcConvertStr2Date(planEndDate);
    }
    public void    setActualStartDate(Date actualStartDate){
        this.actualStartDate = actualStartDate;
    }
    public void    setActualStartDate(String    actualStartDate){
        this.actualStartDate = this.omcConvertStr2Date(actualStartDate);
    }
    public void    setActualEndDate(Date actualEndDate){
        this.actualEndDate = actualEndDate;
    }
    public void    setActualEndDate(String    actualEndDate){
        this.actualEndDate = this.omcConvertStr2Date(actualEndDate);
    }
    public String getIsNew(){
        return isNew;
    }
    public String getAppliedWbsLifeCycle(){
        return appliedWbsLifeCycle;
    }
    public String getCurrentPhase(){
        return currentPhase;
    }
    public Date getBaselinedStartDate(){
        return baselinedStartDate;
    }
    public Date getBaselinedEndDate(){
        return baselinedEndDate;
    }
    public Date getPlanStartDate(){
        return planStartDate;
    }
    public Date getPlanEndDate(){
        return planEndDate;
    }
    public Date getActualStartDate(){
        return actualStartDate;
    }
    public Date getActualEndDate(){
        return actualEndDate;
    }
}

