/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : GripEventCheckVO.java
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
public class GripEventCheckVO extends BusinessObjectMasterVO {
    private String        modelName                                         ;
    private String        eventCode                                         ;
    private String        activityCode                                      ;
    private String        userId                                            ;
    private String        requestedDate                                     ;
    private String        interfaceCode                                     ;


    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setActivityCode(String activityCode){
        this.activityCode = activityCode;
    }
    public void    setUserId(String userId){
        this.userId = userId;
    }
    public void    setRequestedDate(String requestedDate){
        this.requestedDate = requestedDate;
    }
    public void    setInterfaceCode(String interfaceCode){
        this.interfaceCode = interfaceCode;
    }
    public String getModelName(){
        return modelName;
    }
    public String getEventCode(){
        return eventCode;
    }
    public String getActivityCode(){
        return activityCode;
    }
    public String getUserId(){
        return userId;
    }
    public String getRequestedDate(){
        return requestedDate;
    }
    public String getInterfaceCode(){
        return interfaceCode;
    }
}

