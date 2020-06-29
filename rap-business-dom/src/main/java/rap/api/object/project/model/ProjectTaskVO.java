/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectTaskVO.java
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
public class ProjectTaskVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        taskType                                          ;
    private String        lastPerformance                                   ;
    private String        lastPerformanceContents                           ;
    private String        thisTarget                                        ;
    private String        thisTargetContents                                ;
    private String        lastTarget                                        ;
    private String        lastTargetContents                                ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setTaskType(String taskType){
        this.taskType = taskType;
    }
    public void    setLastPerformance(String lastPerformance){
        this.lastPerformance = lastPerformance;
    }
    public void    setLastPerformanceContents(String lastPerformanceContents){
        this.lastPerformanceContents = lastPerformanceContents;
    }
    public void    setThisTarget(String thisTarget){
        this.thisTarget = thisTarget;
    }
    public void    setThisTargetContents(String thisTargetContents){
        this.thisTargetContents = thisTargetContents;
    }
    public void    setLastTarget(String lastTarget){
        this.lastTarget = lastTarget;
    }
    public void    setLastTargetContents(String lastTargetContents){
        this.lastTargetContents = lastTargetContents;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getTaskType(){
        return taskType;
    }
    public String getLastPerformance(){
        return lastPerformance;
    }
    public String getLastPerformanceContents(){
        return lastPerformanceContents;
    }
    public String getThisTarget(){
        return thisTarget;
    }
    public String getThisTargetContents(){
        return thisTargetContents;
    }
    public String getLastTarget(){
        return lastTarget;
    }
    public String getLastTargetContents(){
        return lastTargetContents;
    }
}

