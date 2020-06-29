/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : GscpProductionVO.java
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
public class GscpProductionVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        planName                                          ;
    private Integer       seq                                               ;
    private String        planType                                          ;
    private String        fromAffiliateBranchCode                           ;
    private String        fromSite                                          ;
    private String        modelSuffix                                       ;
    private String        salesModelSuffix                                  ;
    private String        ptoModel                                          ;
    private String        ptoSuffix                                         ;
    private String        firstProductionPlanWeek                           ;
    private BigDecimal    firstProductionPlanQty                             = new BigDecimal(0);
    private String        firstProductionResultWeek                         ;
    private BigDecimal    firstProductionResultQty                           = new BigDecimal(0);
    private String        firstShipmentResultWeek                           ;
    private BigDecimal    firstShipmentResultQty                             = new BigDecimal(0);
    private String        attribute01                                       ;
    private String        attribute02                                       ;
    private String        attribute03                                       ;
    private String        attribute04                                       ;
    private String        attribute05                                       ;
    private String        attribute06                                       ;
    private String        attribute07                                       ;
    private String        attribute08                                       ;
    private String        attribute09                                       ;
    private String        attribute10                                       ;
    private Date          appliedDate                                       ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setPlanName(String planName){
        this.planName = planName;
    }
    public void    setSeq(Integer seq){
        this.seq = seq;
    }
    public void    setPlanType(String planType){
        this.planType = planType;
    }
    public void    setFromAffiliateBranchCode(String fromAffiliateBranchCode){
        this.fromAffiliateBranchCode = fromAffiliateBranchCode;
    }
    public void    setFromSite(String fromSite){
        this.fromSite = fromSite;
    }
    public void    setModelSuffix(String modelSuffix){
        this.modelSuffix = modelSuffix;
    }
    public void    setSalesModelSuffix(String salesModelSuffix){
        this.salesModelSuffix = salesModelSuffix;
    }
    public void    setPtoModel(String ptoModel){
        this.ptoModel = ptoModel;
    }
    public void    setPtoSuffix(String ptoSuffix){
        this.ptoSuffix = ptoSuffix;
    }
    public void    setFirstProductionPlanWeek(String firstProductionPlanWeek){
        this.firstProductionPlanWeek = firstProductionPlanWeek;
    }
    public void    setFirstProductionPlanQty(BigDecimal firstProductionPlanQty){
        this.firstProductionPlanQty = firstProductionPlanQty;
    }
    public void    setFirstProductionResultWeek(String firstProductionResultWeek){
        this.firstProductionResultWeek = firstProductionResultWeek;
    }
    public void    setFirstProductionResultQty(BigDecimal firstProductionResultQty){
        this.firstProductionResultQty = firstProductionResultQty;
    }
    public void    setFirstShipmentResultWeek(String firstShipmentResultWeek){
        this.firstShipmentResultWeek = firstShipmentResultWeek;
    }
    public void    setFirstShipmentResultQty(BigDecimal firstShipmentResultQty){
        this.firstShipmentResultQty = firstShipmentResultQty;
    }
    public void    setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }
    public void    setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }
    public void    setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }
    public void    setAttribute04(String attribute04){
        this.attribute04 = attribute04;
    }
    public void    setAttribute05(String attribute05){
        this.attribute05 = attribute05;
    }
    public void    setAttribute06(String attribute06){
        this.attribute06 = attribute06;
    }
    public void    setAttribute07(String attribute07){
        this.attribute07 = attribute07;
    }
    public void    setAttribute08(String attribute08){
        this.attribute08 = attribute08;
    }
    public void    setAttribute09(String attribute09){
        this.attribute09 = attribute09;
    }
    public void    setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    public void    setAppliedDate(Date appliedDate){
        this.appliedDate = appliedDate;
    }
    public void    setAppliedDate(String    appliedDate){
        this.appliedDate = this.omcConvertStr2Date(appliedDate);
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getPlanName(){
        return planName;
    }
    public Integer getSeq(){
        return seq;
    }
    public String getPlanType(){
        return planType;
    }
    public String getFromAffiliateBranchCode(){
        return fromAffiliateBranchCode;
    }
    public String getFromSite(){
        return fromSite;
    }
    public String getModelSuffix(){
        return modelSuffix;
    }
    public String getSalesModelSuffix(){
        return salesModelSuffix;
    }
    public String getPtoModel(){
        return ptoModel;
    }
    public String getPtoSuffix(){
        return ptoSuffix;
    }
    public String getFirstProductionPlanWeek(){
        return firstProductionPlanWeek;
    }
    public BigDecimal getFirstProductionPlanQty(){
        return firstProductionPlanQty;
    }
    public String getFirstProductionResultWeek(){
        return firstProductionResultWeek;
    }
    public BigDecimal getFirstProductionResultQty(){
        return firstProductionResultQty;
    }
    public String getFirstShipmentResultWeek(){
        return firstShipmentResultWeek;
    }
    public BigDecimal getFirstShipmentResultQty(){
        return firstShipmentResultQty;
    }
    public String getAttribute01(){
        return attribute01;
    }
    public String getAttribute02(){
        return attribute02;
    }
    public String getAttribute03(){
        return attribute03;
    }
    public String getAttribute04(){
        return attribute04;
    }
    public String getAttribute05(){
        return attribute05;
    }
    public String getAttribute06(){
        return attribute06;
    }
    public String getAttribute07(){
        return attribute07;
    }
    public String getAttribute08(){
        return attribute08;
    }
    public String getAttribute09(){
        return attribute09;
    }
    public String getAttribute10(){
        return attribute10;
    }
    public Date getAppliedDate(){
        return appliedDate;
    }
}

