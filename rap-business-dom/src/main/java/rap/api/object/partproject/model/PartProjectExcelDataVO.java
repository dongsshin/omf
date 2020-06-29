/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectExcelDataVO.java
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
public class PartProjectExcelDataVO extends BusinessObjectMasterVO {
    private String        xlsxRowNo                                         ;
    private String        bmsProjectCode                                    ;
    private String        partDevelopmentType                               ;
    private String        prevPartProjectCode                               ;
    private String        partNo                                            ;
    private String        partDescription                                   ;
    private String        partSpec                                          ;
    private String        basisPartNo                                       ;
    private Float         partQty                                           ;
    private String        changePoint                                       ;
    private String        changeReason                                      ;
    private String        partStandardYn                                    ;
    private String        nonStandardReason                                 ;
    private String        longDeliveryYn                                    ;
    private String        partDevGrade                                      ;
    private String        fourNewYn                                         ;
    private String        yieldYn                                           ;
    private String        apqpYn                                            ;
    private String        accelerationYn                                    ;
    private String        textMaker                                         ;
    private String        drbfmDesignYn                                     ;
    private String        drbfmProcessYn                                    ;
    private String        drbfmNoGoingReason                                ;
    private String        pApprQualLimitSampleYn                            ;
    private String        pApprFirstInspectYn                               ;
    private String        pApprNoGoingReason                                ;
    private String        pApprAuditYn                                      ;
    private String        pApprProcessInspectYn                             ;
    private String        pApprReliabilityYn                                ;
    private String        errorType                                         ;
    private String        errorMessage                                      ;
    private String        makeToolYn                                        ;
    private String        partInHouseYn                                     ;


    public void    setXlsxRowNo(String xlsxRowNo){
        this.xlsxRowNo = xlsxRowNo;
    }
    public void    setBmsProjectCode(String bmsProjectCode){
        this.bmsProjectCode = bmsProjectCode;
    }
    public void    setPartDevelopmentType(String partDevelopmentType){
        this.partDevelopmentType = partDevelopmentType;
    }
    public void    setPrevPartProjectCode(String prevPartProjectCode){
        this.prevPartProjectCode = prevPartProjectCode;
    }
    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setPartDescription(String partDescription){
        this.partDescription = partDescription;
    }
    public void    setPartSpec(String partSpec){
        this.partSpec = partSpec;
    }
    public void    setBasisPartNo(String basisPartNo){
        this.basisPartNo = basisPartNo;
    }
    public void    setPartQty(Float partQty){
        this.partQty = partQty;
    }
    public void    setChangePoint(String changePoint){
        this.changePoint = changePoint;
    }
    public void    setChangeReason(String changeReason){
        this.changeReason = changeReason;
    }
    public void    setPartStandardYn(String partStandardYn){
        this.partStandardYn = partStandardYn;
    }
    public void    setNonStandardReason(String nonStandardReason){
        this.nonStandardReason = nonStandardReason;
    }
    public void    setLongDeliveryYn(String longDeliveryYn){
        this.longDeliveryYn = longDeliveryYn;
    }
    public void    setPartDevGrade(String partDevGrade){
        this.partDevGrade = partDevGrade;
    }
    public void    setFourNewYn(String fourNewYn){
        this.fourNewYn = fourNewYn;
    }
    public void    setYieldYn(String yieldYn){
        this.yieldYn = yieldYn;
    }
    public void    setApqpYn(String apqpYn){
        this.apqpYn = apqpYn;
    }
    public void    setAccelerationYn(String accelerationYn){
        this.accelerationYn = accelerationYn;
    }
    public void    setTextMaker(String textMaker){
        this.textMaker = textMaker;
    }
    public void    setDrbfmDesignYn(String drbfmDesignYn){
        this.drbfmDesignYn = drbfmDesignYn;
    }
    public void    setDrbfmProcessYn(String drbfmProcessYn){
        this.drbfmProcessYn = drbfmProcessYn;
    }
    public void    setDrbfmNoGoingReason(String drbfmNoGoingReason){
        this.drbfmNoGoingReason = drbfmNoGoingReason;
    }
    public void    setPApprQualLimitSampleYn(String pApprQualLimitSampleYn){
        this.pApprQualLimitSampleYn = pApprQualLimitSampleYn;
    }
    public void    setPApprFirstInspectYn(String pApprFirstInspectYn){
        this.pApprFirstInspectYn = pApprFirstInspectYn;
    }
    public void    setPApprNoGoingReason(String pApprNoGoingReason){
        this.pApprNoGoingReason = pApprNoGoingReason;
    }
    public void    setPApprAuditYn(String pApprAuditYn){
        this.pApprAuditYn = pApprAuditYn;
    }
    public void    setPApprProcessInspectYn(String pApprProcessInspectYn){
        this.pApprProcessInspectYn = pApprProcessInspectYn;
    }
    public void    setPApprReliabilityYn(String pApprReliabilityYn){
        this.pApprReliabilityYn = pApprReliabilityYn;
    }
    public void    setErrorType(String errorType){
        this.errorType = errorType;
    }
    public void    setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public void    setMakeToolYn(String makeToolYn){
        this.makeToolYn = makeToolYn;
    }
    public void    setPartInHouseYn(String partInHouseYn){
        this.partInHouseYn = partInHouseYn;
    }
    public String getXlsxRowNo(){
        return xlsxRowNo;
    }
    public String getBmsProjectCode(){
        return bmsProjectCode;
    }
    public String getPartDevelopmentType(){
        return partDevelopmentType;
    }
    public String getPrevPartProjectCode(){
        return prevPartProjectCode;
    }
    public String getPartNo(){
        return partNo;
    }
    public String getPartDescription(){
        return partDescription;
    }
    public String getPartSpec(){
        return partSpec;
    }
    public String getBasisPartNo(){
        return basisPartNo;
    }
    public Float getPartQty(){
        return partQty;
    }
    public String getChangePoint(){
        return changePoint;
    }
    public String getChangeReason(){
        return changeReason;
    }
    public String getPartStandardYn(){
        return partStandardYn;
    }
    public String getNonStandardReason(){
        return nonStandardReason;
    }
    public String getLongDeliveryYn(){
        return longDeliveryYn;
    }
    public String getPartDevGrade(){
        return partDevGrade;
    }
    public String getFourNewYn(){
        return fourNewYn;
    }
    public String getYieldYn(){
        return yieldYn;
    }
    public String getApqpYn(){
        return apqpYn;
    }
    public String getAccelerationYn(){
        return accelerationYn;
    }
    public String getTextMaker(){
        return textMaker;
    }
    public String getDrbfmDesignYn(){
        return drbfmDesignYn;
    }
    public String getDrbfmProcessYn(){
        return drbfmProcessYn;
    }
    public String getDrbfmNoGoingReason(){
        return drbfmNoGoingReason;
    }
    public String getPApprQualLimitSampleYn(){
        return pApprQualLimitSampleYn;
    }
    public String getPApprFirstInspectYn(){
        return pApprFirstInspectYn;
    }
    public String getPApprNoGoingReason(){
        return pApprNoGoingReason;
    }
    public String getPApprAuditYn(){
        return pApprAuditYn;
    }
    public String getPApprProcessInspectYn(){
        return pApprProcessInspectYn;
    }
    public String getPApprReliabilityYn(){
        return pApprReliabilityYn;
    }
    public String getErrorType(){
        return errorType;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
    public String getMakeToolYn(){
        return makeToolYn;
    }
    public String getPartInHouseYn(){
        return partInHouseYn;
    }
}

