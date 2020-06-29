/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectPropertyVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectPropertyVO extends BusinessObjectVO {
    private String        partClass1                                        ;
    private String        partClass2                                        ;
    private String        partClass3                                        ;
    private String        basisPartNo                                       ;
    private String        partNo                                            ;
    private String        partDescription                                   ;
    private String        partSpec                                          ;
    private String        partDevGrade                                      ;
    private String        partDevRequestNo                                  ;
    private String        makerCode                                         ;
    private String        supplierCode                                      ;
    private Float         partQuantity                                      ;
    private Date          partApprovalPlanDate                              ;
    private String        partStandardYn                                    ;
    private String        nonStandardReason                                 ;
    private String        longDeliveryYn                                    ;
    private String        fourNewYn                                         ;
    private String        apqpYn                                            ;
    private String        accelerationYn                                    ;
    private String        drbfmDesignYn                                     ;
    private String        drbfmProcessYn                                    ;
    private String        drbfmNoGoingReason                                ;
    private String        pApprQualLimitSampleYn                            ;
    private String        pApprFirstInspectYn                               ;
    private String        pApprAuditYn                                      ;
    private String        pApprProcessInspectYn                             ;
    private String        pApprReliabilityYn                                ;
    private String        yieldYn                                           ;
    private String        pApprSpecialYn                                    ;
    private String        yieldManagementYn                                 ;
    private String        textMaker                                         ;
    private String        textSupplier                                      ;
    private String        pApprNoGoingReason                                ;
    private String        makeToolYn                                        ;
    private String        partInHouseYn                                     ;


    public void    setPartClass1(String partClass1){
        this.partClass1 = partClass1;
    }
    public void    setPartClass2(String partClass2){
        this.partClass2 = partClass2;
    }
    public void    setPartClass3(String partClass3){
        this.partClass3 = partClass3;
    }
    public void    setBasisPartNo(String basisPartNo){
        this.basisPartNo = basisPartNo;
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
    public void    setPartDevGrade(String partDevGrade){
        this.partDevGrade = partDevGrade;
    }
    public void    setPartDevRequestNo(String partDevRequestNo){
        this.partDevRequestNo = partDevRequestNo;
    }
    public void    setMakerCode(String makerCode){
        this.makerCode = makerCode;
    }
    public void    setSupplierCode(String supplierCode){
        this.supplierCode = supplierCode;
    }
    public void    setPartQuantity(Float partQuantity){
        this.partQuantity = partQuantity;
    }
    public void    setPartApprovalPlanDate(Date partApprovalPlanDate){
        this.partApprovalPlanDate = partApprovalPlanDate;
    }
    public void    setPartApprovalPlanDate(String    partApprovalPlanDate){
        this.partApprovalPlanDate = this.omcConvertStr2Date(partApprovalPlanDate);
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
    public void    setFourNewYn(String fourNewYn){
        this.fourNewYn = fourNewYn;
    }
    public void    setApqpYn(String apqpYn){
        this.apqpYn = apqpYn;
    }
    public void    setAccelerationYn(String accelerationYn){
        this.accelerationYn = accelerationYn;
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
    public void    setPApprAuditYn(String pApprAuditYn){
        this.pApprAuditYn = pApprAuditYn;
    }
    public void    setPApprProcessInspectYn(String pApprProcessInspectYn){
        this.pApprProcessInspectYn = pApprProcessInspectYn;
    }
    public void    setPApprReliabilityYn(String pApprReliabilityYn){
        this.pApprReliabilityYn = pApprReliabilityYn;
    }
    public void    setYieldYn(String yieldYn){
        this.yieldYn = yieldYn;
    }
    public void    setPApprSpecialYn(String pApprSpecialYn){
        this.pApprSpecialYn = pApprSpecialYn;
    }
    public void    setYieldManagementYn(String yieldManagementYn){
        this.yieldManagementYn = yieldManagementYn;
    }
    public void    setTextMaker(String textMaker){
        this.textMaker = textMaker;
    }
    public void    setTextSupplier(String textSupplier){
        this.textSupplier = textSupplier;
    }
    public void    setPApprNoGoingReason(String pApprNoGoingReason){
        this.pApprNoGoingReason = pApprNoGoingReason;
    }
    public void    setMakeToolYn(String makeToolYn){
        this.makeToolYn = makeToolYn;
    }
    public void    setPartInHouseYn(String partInHouseYn){
        this.partInHouseYn = partInHouseYn;
    }
    public String getPartClass1(){
        return partClass1;
    }
    public String getPartClass2(){
        return partClass2;
    }
    public String getPartClass3(){
        return partClass3;
    }
    public String getBasisPartNo(){
        return basisPartNo;
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
    public String getPartDevGrade(){
        return partDevGrade;
    }
    public String getPartDevRequestNo(){
        return partDevRequestNo;
    }
    public String getMakerCode(){
        return makerCode;
    }
    public String getSupplierCode(){
        return supplierCode;
    }
    public Float getPartQuantity(){
        return partQuantity;
    }
    public Date getPartApprovalPlanDate(){
        return partApprovalPlanDate;
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
    public String getFourNewYn(){
        return fourNewYn;
    }
    public String getApqpYn(){
        return apqpYn;
    }
    public String getAccelerationYn(){
        return accelerationYn;
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
    public String getPApprAuditYn(){
        return pApprAuditYn;
    }
    public String getPApprProcessInspectYn(){
        return pApprProcessInspectYn;
    }
    public String getPApprReliabilityYn(){
        return pApprReliabilityYn;
    }
    public String getYieldYn(){
        return yieldYn;
    }
    public String getPApprSpecialYn(){
        return pApprSpecialYn;
    }
    public String getYieldManagementYn(){
        return yieldManagementYn;
    }
    public String getTextMaker(){
        return textMaker;
    }
    public String getTextSupplier(){
        return textSupplier;
    }
    public String getPApprNoGoingReason(){
        return pApprNoGoingReason;
    }
    public String getMakeToolYn(){
        return makeToolYn;
    }
    public String getPartInHouseYn(){
        return partInHouseYn;
    }
}

