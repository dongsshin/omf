/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectTaskExecuteVO.java
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
public class ProjectTaskExecuteVO extends BusinessObjectMasterVO {
    private String        yyyyMm                                            ;
    private String        taskSignal                                        ;
    private String        fromObid                                          ;


    public void    setYyyyMm(String yyyyMm){
        this.yyyyMm = yyyyMm;
    }
    public void    setTaskSignal(String taskSignal){
        this.taskSignal = taskSignal;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public String getYyyyMm(){
        return yyyyMm;
    }
    public String getTaskSignal(){
        return taskSignal;
    }
    public String getFromObid(){
        return fromObid;
    }
}

