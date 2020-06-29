/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectApprovalVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectApprovalVO extends BusinessObjectMasterVO {
    private Date          changedTime                                       ;
    private String        changedType                                       ;
    private String        changedItems                                      ;
    private String        beforeValue                                       ;
    private String        afterValue                                        ;
    private String        requesterUserId                                   ;
    private String        changedContents                                   ;


    public void    setChangedTime(Date changedTime){
        this.changedTime = changedTime;
    }
    public void    setChangedTime(String    changedTime){
        this.changedTime = this.omcConvertStr2Date(changedTime);
    }
    public void    setChangedType(String changedType){
        this.changedType = changedType;
    }
    public void    setChangedItems(String changedItems){
        this.changedItems = changedItems;
    }
    public void    setBeforeValue(String beforeValue){
        this.beforeValue = beforeValue;
    }
    public void    setAfterValue(String afterValue){
        this.afterValue = afterValue;
    }
    public void    setRequesterUserId(String requesterUserId){
        this.requesterUserId = requesterUserId;
    }
    public void    setChangedContents(String changedContents){
        this.changedContents = changedContents;
    }
    public Date getChangedTime(){
        return changedTime;
    }
    public String getChangedType(){
        return changedType;
    }
    public String getChangedItems(){
        return changedItems;
    }
    public String getBeforeValue(){
        return beforeValue;
    }
    public String getAfterValue(){
        return afterValue;
    }
    public String getRequesterUserId(){
        return requesterUserId;
    }
    public String getChangedContents(){
        return changedContents;
    }
}

