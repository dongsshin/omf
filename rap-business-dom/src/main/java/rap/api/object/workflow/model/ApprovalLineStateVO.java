/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ApprovalLineStateVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ApprovalLineStateVO extends BusinessObjectVO {
    private String        routeState                                        ;
    private String        step                                              ;
    private Integer       sequences                                         ;
    private String        assigneeObid                                      ;
    private String        requiredAction                                    ;
    private Boolean       isEssential                                        = (Boolean)false;


    public void    setRouteState(String routeState){
        this.routeState = routeState;
    }
    public void    setStep(String step){
        this.step = step;
    }
    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setAssigneeObid(String assigneeObid){
        this.assigneeObid = assigneeObid;
    }
    public void    setRequiredAction(String requiredAction){
        this.requiredAction = requiredAction;
    }
    public void    setIsEssential(Boolean isEssential){
        this.isEssential = isEssential;
    }
    public String getRouteState(){
        return routeState;
    }
    public String getStep(){
        return step;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getAssigneeObid(){
        return assigneeObid;
    }
    public String getRequiredAction(){
        return requiredAction;
    }
    public Boolean getIsEssential(){
        return isEssential;
    }
}

