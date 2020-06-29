/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PdrRejectHistoryVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PdrRejectHistoryVO extends BusinessObjectMasterVO {
    private String        targetObid                                        ;
    private String        targetClass                                       ;
    private String        targetStates                                      ;
    private String        divisionCode                                      ;
    private Integer       referenceId                                       ;
    private String        referenceName                                     ;
    private String        registerUserId                                    ;
    private Date          registerDate                                      ;
    private String        rejectReasonCode                                  ;
    private String        workDelimiterCode                                 ;


    public void    setTargetObid(String targetObid){
        this.targetObid = targetObid;
    }
    public void    setTargetClass(String targetClass){
        this.targetClass = targetClass;
    }
    public void    setTargetStates(String targetStates){
        this.targetStates = targetStates;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setReferenceId(Integer referenceId){
        this.referenceId = referenceId;
    }
    public void    setReferenceName(String referenceName){
        this.referenceName = referenceName;
    }
    public void    setRegisterUserId(String registerUserId){
        this.registerUserId = registerUserId;
    }
    public void    setRegisterDate(Date registerDate){
        this.registerDate = registerDate;
    }
    public void    setRegisterDate(String    registerDate){
        this.registerDate = this.omcConvertStr2Date(registerDate);
    }
    public void    setRejectReasonCode(String rejectReasonCode){
        this.rejectReasonCode = rejectReasonCode;
    }
    public void    setWorkDelimiterCode(String workDelimiterCode){
        this.workDelimiterCode = workDelimiterCode;
    }
    public String getTargetObid(){
        return targetObid;
    }
    public String getTargetClass(){
        return targetClass;
    }
    public String getTargetStates(){
        return targetStates;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public Integer getReferenceId(){
        return referenceId;
    }
    public String getReferenceName(){
        return referenceName;
    }
    public String getRegisterUserId(){
        return registerUserId;
    }
    public Date getRegisterDate(){
        return registerDate;
    }
    public String getRejectReasonCode(){
        return rejectReasonCode;
    }
    public String getWorkDelimiterCode(){
        return workDelimiterCode;
    }
}

