/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BudgetActivityVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BudgetActivityVO extends BusinessObjectMasterVO {
    private Integer       activityId                                        ;
    private String        bmsIfAccountCode                                  ;
    private String        corporationCode                                   ;
    private String        activityCode                                      ;
    private String        activityEngName                                   ;
    private String        activityLocName                                   ;
    private String        priorityCode                                      ;
    private String        useYn                                             ;
    private String        useFromYyyymmdd                                   ;
    private String        useEndYyyymmdd                                    ;


    public void    setActivityId(Integer activityId){
        this.activityId = activityId;
    }
    public void    setBmsIfAccountCode(String bmsIfAccountCode){
        this.bmsIfAccountCode = bmsIfAccountCode;
    }
    public void    setCorporationCode(String corporationCode){
        this.corporationCode = corporationCode;
    }
    public void    setActivityCode(String activityCode){
        this.activityCode = activityCode;
    }
    public void    setActivityEngName(String activityEngName){
        this.activityEngName = activityEngName;
    }
    public void    setActivityLocName(String activityLocName){
        this.activityLocName = activityLocName;
    }
    public void    setPriorityCode(String priorityCode){
        this.priorityCode = priorityCode;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setUseFromYyyymmdd(String useFromYyyymmdd){
        this.useFromYyyymmdd = useFromYyyymmdd;
    }
    public void    setUseEndYyyymmdd(String useEndYyyymmdd){
        this.useEndYyyymmdd = useEndYyyymmdd;
    }
    public Integer getActivityId(){
        return activityId;
    }
    public String getBmsIfAccountCode(){
        return bmsIfAccountCode;
    }
    public String getCorporationCode(){
        return corporationCode;
    }
    public String getActivityCode(){
        return activityCode;
    }
    public String getActivityEngName(){
        return activityEngName;
    }
    public String getActivityLocName(){
        return activityLocName;
    }
    public String getPriorityCode(){
        return priorityCode;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getUseFromYyyymmdd(){
        return useFromYyyymmdd;
    }
    public String getUseEndYyyymmdd(){
        return useEndYyyymmdd;
    }
}

