/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.consult.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ConsultVO extends BusinessObjectMasterVO {
    private String        acctCd                                            ;
    private Integer       fgCd                                              ;
    private String        deptCd                                            ;
    private String        execPjtCd                                         ;
    private String        fiYm                                              ;
    private String        transFgCd                                         ;
    private String        transCd                                           ;
    private String        bgtReflYn                                         ;
    private String        befConsCd                                         ;
    private String        apprEndYn                                         ;
    private Date          apprEndDate                                       ;
    private Date          apprReqDate                                       ;
    private String        apprStepFg                                        ;
    private String        contents                                          ;
    private String        comments                                          ;
    private BigDecimal    originExpeneAmt                                    = new BigDecimal(0);
    private String        modiYn                                            ;
    private String        frnYn                                             ;
    private BigDecimal    wonTotAmt                                          = new BigDecimal(0);
    private BigDecimal    realExpenAmt                                       = new BigDecimal(0);
    private String        calcYmd                                           ;
    private BigDecimal    calcAmt                                            = new BigDecimal(0);
    private String        progStatFg                                        ;
    private String        repairReqYmd                                      ;
    private String        repairLoc                                         ;
    private String        prodFg                                            ;
    private String        eduCrsNm                                          ;
    private Integer       eduComplTime                                      ;
    private String        eduFromYmd                                        ;
    private String        eduEndYmd                                         ;
    private String        eduLocFg                                          ;
    private String        eduInstCd                                         ;
    private String        eduInstNm                                         ;
    private String        extAttendant                                      ;
    private Integer       extAttendantCnt                                   ;
    private String        saveMediYn                                        ;
    private String        techSvcYn                                         ;
    private String        takePlaceNm                                       ;
    private String        takePlaceChgrNm                                   ;
    private String        takePlaceTelNo                                    ;
    private String        circularFg                                        ;
    private String        carryFg                                           ;
    private String        carryAppointYmd                                   ;
    private Integer       totStaff                                          ;
    private BigDecimal    hotelCost                                          = new BigDecimal(0);
    private BigDecimal    foodCost                                           = new BigDecimal(0);
    private BigDecimal    orgActiCost                                        = new BigDecimal(0);
    private BigDecimal    transCost                                          = new BigDecimal(0);
    private String        hotelCostDet                                      ;
    private String        foodCostDet                                       ;
    private String        orgActiCostDet                                    ;
    private String        transCostDet                                      ;
    private String        carryOutYmd                                       ;
    private String        carryInYmd                                        ;
    private String        circularStatFg                                    ;
    private String        itemReflYn                                        ;
    private String        nonReflRson                                       ;
    private String        alterItem                                         ;
    private String        consAlterCnts                                     ;
    private String        draftConsYn                                       ;
    private String        buyChgrEmpNo                                      ;
    private String        buyChgrOrgCd                                      ;
    private String        bgtTransYm                                        ;
    private BigDecimal    originFrnAmt                                       = new BigDecimal(0);
    private String        currency                                          ;
    private BigDecimal    exchRate                                           = new BigDecimal(0);
    private BigDecimal    frnReqAmt                                          = new BigDecimal(0);
    private BigDecimal    alterFrnAmt                                        = new BigDecimal(0);
    private BigDecimal    calcFrnAmt                                         = new BigDecimal(0);
    private Integer       pjtDgr                                            ;
    private Integer       consGroupId                                       ;
    private String        reqCnts                                           ;
    private String        mobileCnts                                         = "None";
    private String        designStatus                                      ;
    private String        designReqDate                                     ;
    private String        designReqReceiveDate                              ;
    private String        designRealReceiveDate                             ;
    private String        designDataReceiveDate                             ;
    private Integer       designQuantity                                    ;
    private String        alterCurrency                                     ;
    private BigDecimal    alterExchRate                                      = new BigDecimal(0);
    private String        contractType                                      ;
    private String        devFromYmd                                        ;
    private String        devEndYmd                                         ;
    private String        requestor                                         ;
    private String        supplierSelectMethod                              ;
    private String        inAdvanceInputYn                                  ;
    private String        inAdvanceInputReason                              ;
    private String        specialRelationYn                                 ;
    private String        specialRelSelReason                               ;
    private String        rschRspsrNm                                       ;
    private String        supplierSelectYn                                  ;
    private String        contractNo                                        ;
    private String        contractName                                      ;
    private String        contractTotCurrCode                               ;
    private BigDecimal    contractTotAmt                                     = new BigDecimal(0);
    private BigDecimal    contractTotWonAmt                                  = new BigDecimal(0);
    private String        mesConsCd                                         ;
    private String        subjectContents                                   ;
    private String        gpApplyYn                                         ;


    public void    setAcctCd(String acctCd){
        this.acctCd = acctCd;
    }
    public void    setFgCd(Integer fgCd){
        this.fgCd = fgCd;
    }
    public void    setDeptCd(String deptCd){
        this.deptCd = deptCd;
    }
    public void    setExecPjtCd(String execPjtCd){
        this.execPjtCd = execPjtCd;
    }
    public void    setFiYm(String fiYm){
        this.fiYm = fiYm;
    }
    public void    setTransFgCd(String transFgCd){
        this.transFgCd = transFgCd;
    }
    public void    setTransCd(String transCd){
        this.transCd = transCd;
    }
    public void    setBgtReflYn(String bgtReflYn){
        this.bgtReflYn = bgtReflYn;
    }
    public void    setBefConsCd(String befConsCd){
        this.befConsCd = befConsCd;
    }
    public void    setApprEndYn(String apprEndYn){
        this.apprEndYn = apprEndYn;
    }
    public void    setApprEndDate(Date apprEndDate){
        this.apprEndDate = apprEndDate;
    }
    public void    setApprEndDate(String    apprEndDate){
        this.apprEndDate = this.omcConvertStr2Date(apprEndDate);
    }
    public void    setApprReqDate(Date apprReqDate){
        this.apprReqDate = apprReqDate;
    }
    public void    setApprReqDate(String    apprReqDate){
        this.apprReqDate = this.omcConvertStr2Date(apprReqDate);
    }
    public void    setApprStepFg(String apprStepFg){
        this.apprStepFg = apprStepFg;
    }
    public void    setContents(String contents){
        this.contents = contents;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public void    setOriginExpeneAmt(BigDecimal originExpeneAmt){
        this.originExpeneAmt = originExpeneAmt;
    }
    public void    setModiYn(String modiYn){
        this.modiYn = modiYn;
    }
    public void    setFrnYn(String frnYn){
        this.frnYn = frnYn;
    }
    public void    setWonTotAmt(BigDecimal wonTotAmt){
        this.wonTotAmt = wonTotAmt;
    }
    public void    setRealExpenAmt(BigDecimal realExpenAmt){
        this.realExpenAmt = realExpenAmt;
    }
    public void    setCalcYmd(String calcYmd){
        this.calcYmd = calcYmd;
    }
    public void    setCalcAmt(BigDecimal calcAmt){
        this.calcAmt = calcAmt;
    }
    public void    setProgStatFg(String progStatFg){
        this.progStatFg = progStatFg;
    }
    public void    setRepairReqYmd(String repairReqYmd){
        this.repairReqYmd = repairReqYmd;
    }
    public void    setRepairLoc(String repairLoc){
        this.repairLoc = repairLoc;
    }
    public void    setProdFg(String prodFg){
        this.prodFg = prodFg;
    }
    public void    setEduCrsNm(String eduCrsNm){
        this.eduCrsNm = eduCrsNm;
    }
    public void    setEduComplTime(Integer eduComplTime){
        this.eduComplTime = eduComplTime;
    }
    public void    setEduFromYmd(String eduFromYmd){
        this.eduFromYmd = eduFromYmd;
    }
    public void    setEduEndYmd(String eduEndYmd){
        this.eduEndYmd = eduEndYmd;
    }
    public void    setEduLocFg(String eduLocFg){
        this.eduLocFg = eduLocFg;
    }
    public void    setEduInstCd(String eduInstCd){
        this.eduInstCd = eduInstCd;
    }
    public void    setEduInstNm(String eduInstNm){
        this.eduInstNm = eduInstNm;
    }
    public void    setExtAttendant(String extAttendant){
        this.extAttendant = extAttendant;
    }
    public void    setExtAttendantCnt(Integer extAttendantCnt){
        this.extAttendantCnt = extAttendantCnt;
    }
    public void    setSaveMediYn(String saveMediYn){
        this.saveMediYn = saveMediYn;
    }
    public void    setTechSvcYn(String techSvcYn){
        this.techSvcYn = techSvcYn;
    }
    public void    setTakePlaceNm(String takePlaceNm){
        this.takePlaceNm = takePlaceNm;
    }
    public void    setTakePlaceChgrNm(String takePlaceChgrNm){
        this.takePlaceChgrNm = takePlaceChgrNm;
    }
    public void    setTakePlaceTelNo(String takePlaceTelNo){
        this.takePlaceTelNo = takePlaceTelNo;
    }
    public void    setCircularFg(String circularFg){
        this.circularFg = circularFg;
    }
    public void    setCarryFg(String carryFg){
        this.carryFg = carryFg;
    }
    public void    setCarryAppointYmd(String carryAppointYmd){
        this.carryAppointYmd = carryAppointYmd;
    }
    public void    setTotStaff(Integer totStaff){
        this.totStaff = totStaff;
    }
    public void    setHotelCost(BigDecimal hotelCost){
        this.hotelCost = hotelCost;
    }
    public void    setFoodCost(BigDecimal foodCost){
        this.foodCost = foodCost;
    }
    public void    setOrgActiCost(BigDecimal orgActiCost){
        this.orgActiCost = orgActiCost;
    }
    public void    setTransCost(BigDecimal transCost){
        this.transCost = transCost;
    }
    public void    setHotelCostDet(String hotelCostDet){
        this.hotelCostDet = hotelCostDet;
    }
    public void    setFoodCostDet(String foodCostDet){
        this.foodCostDet = foodCostDet;
    }
    public void    setOrgActiCostDet(String orgActiCostDet){
        this.orgActiCostDet = orgActiCostDet;
    }
    public void    setTransCostDet(String transCostDet){
        this.transCostDet = transCostDet;
    }
    public void    setCarryOutYmd(String carryOutYmd){
        this.carryOutYmd = carryOutYmd;
    }
    public void    setCarryInYmd(String carryInYmd){
        this.carryInYmd = carryInYmd;
    }
    public void    setCircularStatFg(String circularStatFg){
        this.circularStatFg = circularStatFg;
    }
    public void    setItemReflYn(String itemReflYn){
        this.itemReflYn = itemReflYn;
    }
    public void    setNonReflRson(String nonReflRson){
        this.nonReflRson = nonReflRson;
    }
    public void    setAlterItem(String alterItem){
        this.alterItem = alterItem;
    }
    public void    setConsAlterCnts(String consAlterCnts){
        this.consAlterCnts = consAlterCnts;
    }
    public void    setDraftConsYn(String draftConsYn){
        this.draftConsYn = draftConsYn;
    }
    public void    setBuyChgrEmpNo(String buyChgrEmpNo){
        this.buyChgrEmpNo = buyChgrEmpNo;
    }
    public void    setBuyChgrOrgCd(String buyChgrOrgCd){
        this.buyChgrOrgCd = buyChgrOrgCd;
    }
    public void    setBgtTransYm(String bgtTransYm){
        this.bgtTransYm = bgtTransYm;
    }
    public void    setOriginFrnAmt(BigDecimal originFrnAmt){
        this.originFrnAmt = originFrnAmt;
    }
    public void    setCurrency(String currency){
        this.currency = currency;
    }
    public void    setExchRate(BigDecimal exchRate){
        this.exchRate = exchRate;
    }
    public void    setFrnReqAmt(BigDecimal frnReqAmt){
        this.frnReqAmt = frnReqAmt;
    }
    public void    setAlterFrnAmt(BigDecimal alterFrnAmt){
        this.alterFrnAmt = alterFrnAmt;
    }
    public void    setCalcFrnAmt(BigDecimal calcFrnAmt){
        this.calcFrnAmt = calcFrnAmt;
    }
    public void    setPjtDgr(Integer pjtDgr){
        this.pjtDgr = pjtDgr;
    }
    public void    setConsGroupId(Integer consGroupId){
        this.consGroupId = consGroupId;
    }
    public void    setReqCnts(String reqCnts){
        this.reqCnts = reqCnts;
    }
    public void    setMobileCnts(String mobileCnts){
        this.mobileCnts = mobileCnts;
    }
    public void    setDesignStatus(String designStatus){
        this.designStatus = designStatus;
    }
    public void    setDesignReqDate(String designReqDate){
        this.designReqDate = designReqDate;
    }
    public void    setDesignReqReceiveDate(String designReqReceiveDate){
        this.designReqReceiveDate = designReqReceiveDate;
    }
    public void    setDesignRealReceiveDate(String designRealReceiveDate){
        this.designRealReceiveDate = designRealReceiveDate;
    }
    public void    setDesignDataReceiveDate(String designDataReceiveDate){
        this.designDataReceiveDate = designDataReceiveDate;
    }
    public void    setDesignQuantity(Integer designQuantity){
        this.designQuantity = designQuantity;
    }
    public void    setAlterCurrency(String alterCurrency){
        this.alterCurrency = alterCurrency;
    }
    public void    setAlterExchRate(BigDecimal alterExchRate){
        this.alterExchRate = alterExchRate;
    }
    public void    setContractType(String contractType){
        this.contractType = contractType;
    }
    public void    setDevFromYmd(String devFromYmd){
        this.devFromYmd = devFromYmd;
    }
    public void    setDevEndYmd(String devEndYmd){
        this.devEndYmd = devEndYmd;
    }
    public void    setRequestor(String requestor){
        this.requestor = requestor;
    }
    public void    setSupplierSelectMethod(String supplierSelectMethod){
        this.supplierSelectMethod = supplierSelectMethod;
    }
    public void    setInAdvanceInputYn(String inAdvanceInputYn){
        this.inAdvanceInputYn = inAdvanceInputYn;
    }
    public void    setInAdvanceInputReason(String inAdvanceInputReason){
        this.inAdvanceInputReason = inAdvanceInputReason;
    }
    public void    setSpecialRelationYn(String specialRelationYn){
        this.specialRelationYn = specialRelationYn;
    }
    public void    setSpecialRelSelReason(String specialRelSelReason){
        this.specialRelSelReason = specialRelSelReason;
    }
    public void    setRschRspsrNm(String rschRspsrNm){
        this.rschRspsrNm = rschRspsrNm;
    }
    public void    setSupplierSelectYn(String supplierSelectYn){
        this.supplierSelectYn = supplierSelectYn;
    }
    public void    setContractNo(String contractNo){
        this.contractNo = contractNo;
    }
    public void    setContractName(String contractName){
        this.contractName = contractName;
    }
    public void    setContractTotCurrCode(String contractTotCurrCode){
        this.contractTotCurrCode = contractTotCurrCode;
    }
    public void    setContractTotAmt(BigDecimal contractTotAmt){
        this.contractTotAmt = contractTotAmt;
    }
    public void    setContractTotWonAmt(BigDecimal contractTotWonAmt){
        this.contractTotWonAmt = contractTotWonAmt;
    }
    public void    setMesConsCd(String mesConsCd){
        this.mesConsCd = mesConsCd;
    }
    public void    setSubjectContents(String subjectContents){
        this.subjectContents = subjectContents;
    }
    public void    setGpApplyYn(String gpApplyYn){
        this.gpApplyYn = gpApplyYn;
    }
    public String getAcctCd(){
        return acctCd;
    }
    public Integer getFgCd(){
        return fgCd;
    }
    public String getDeptCd(){
        return deptCd;
    }
    public String getExecPjtCd(){
        return execPjtCd;
    }
    public String getFiYm(){
        return fiYm;
    }
    public String getTransFgCd(){
        return transFgCd;
    }
    public String getTransCd(){
        return transCd;
    }
    public String getBgtReflYn(){
        return bgtReflYn;
    }
    public String getBefConsCd(){
        return befConsCd;
    }
    public String getApprEndYn(){
        return apprEndYn;
    }
    public Date getApprEndDate(){
        return apprEndDate;
    }
    public Date getApprReqDate(){
        return apprReqDate;
    }
    public String getApprStepFg(){
        return apprStepFg;
    }
    public String getContents(){
        return contents;
    }
    public String getComments(){
        return comments;
    }
    public BigDecimal getOriginExpeneAmt(){
        return originExpeneAmt;
    }
    public String getModiYn(){
        return modiYn;
    }
    public String getFrnYn(){
        return frnYn;
    }
    public BigDecimal getWonTotAmt(){
        return wonTotAmt;
    }
    public BigDecimal getRealExpenAmt(){
        return realExpenAmt;
    }
    public String getCalcYmd(){
        return calcYmd;
    }
    public BigDecimal getCalcAmt(){
        return calcAmt;
    }
    public String getProgStatFg(){
        return progStatFg;
    }
    public String getRepairReqYmd(){
        return repairReqYmd;
    }
    public String getRepairLoc(){
        return repairLoc;
    }
    public String getProdFg(){
        return prodFg;
    }
    public String getEduCrsNm(){
        return eduCrsNm;
    }
    public Integer getEduComplTime(){
        return eduComplTime;
    }
    public String getEduFromYmd(){
        return eduFromYmd;
    }
    public String getEduEndYmd(){
        return eduEndYmd;
    }
    public String getEduLocFg(){
        return eduLocFg;
    }
    public String getEduInstCd(){
        return eduInstCd;
    }
    public String getEduInstNm(){
        return eduInstNm;
    }
    public String getExtAttendant(){
        return extAttendant;
    }
    public Integer getExtAttendantCnt(){
        return extAttendantCnt;
    }
    public String getSaveMediYn(){
        return saveMediYn;
    }
    public String getTechSvcYn(){
        return techSvcYn;
    }
    public String getTakePlaceNm(){
        return takePlaceNm;
    }
    public String getTakePlaceChgrNm(){
        return takePlaceChgrNm;
    }
    public String getTakePlaceTelNo(){
        return takePlaceTelNo;
    }
    public String getCircularFg(){
        return circularFg;
    }
    public String getCarryFg(){
        return carryFg;
    }
    public String getCarryAppointYmd(){
        return carryAppointYmd;
    }
    public Integer getTotStaff(){
        return totStaff;
    }
    public BigDecimal getHotelCost(){
        return hotelCost;
    }
    public BigDecimal getFoodCost(){
        return foodCost;
    }
    public BigDecimal getOrgActiCost(){
        return orgActiCost;
    }
    public BigDecimal getTransCost(){
        return transCost;
    }
    public String getHotelCostDet(){
        return hotelCostDet;
    }
    public String getFoodCostDet(){
        return foodCostDet;
    }
    public String getOrgActiCostDet(){
        return orgActiCostDet;
    }
    public String getTransCostDet(){
        return transCostDet;
    }
    public String getCarryOutYmd(){
        return carryOutYmd;
    }
    public String getCarryInYmd(){
        return carryInYmd;
    }
    public String getCircularStatFg(){
        return circularStatFg;
    }
    public String getItemReflYn(){
        return itemReflYn;
    }
    public String getNonReflRson(){
        return nonReflRson;
    }
    public String getAlterItem(){
        return alterItem;
    }
    public String getConsAlterCnts(){
        return consAlterCnts;
    }
    public String getDraftConsYn(){
        return draftConsYn;
    }
    public String getBuyChgrEmpNo(){
        return buyChgrEmpNo;
    }
    public String getBuyChgrOrgCd(){
        return buyChgrOrgCd;
    }
    public String getBgtTransYm(){
        return bgtTransYm;
    }
    public BigDecimal getOriginFrnAmt(){
        return originFrnAmt;
    }
    public String getCurrency(){
        return currency;
    }
    public BigDecimal getExchRate(){
        return exchRate;
    }
    public BigDecimal getFrnReqAmt(){
        return frnReqAmt;
    }
    public BigDecimal getAlterFrnAmt(){
        return alterFrnAmt;
    }
    public BigDecimal getCalcFrnAmt(){
        return calcFrnAmt;
    }
    public Integer getPjtDgr(){
        return pjtDgr;
    }
    public Integer getConsGroupId(){
        return consGroupId;
    }
    public String getReqCnts(){
        return reqCnts;
    }
    public String getMobileCnts(){
        return mobileCnts;
    }
    public String getDesignStatus(){
        return designStatus;
    }
    public String getDesignReqDate(){
        return designReqDate;
    }
    public String getDesignReqReceiveDate(){
        return designReqReceiveDate;
    }
    public String getDesignRealReceiveDate(){
        return designRealReceiveDate;
    }
    public String getDesignDataReceiveDate(){
        return designDataReceiveDate;
    }
    public Integer getDesignQuantity(){
        return designQuantity;
    }
    public String getAlterCurrency(){
        return alterCurrency;
    }
    public BigDecimal getAlterExchRate(){
        return alterExchRate;
    }
    public String getContractType(){
        return contractType;
    }
    public String getDevFromYmd(){
        return devFromYmd;
    }
    public String getDevEndYmd(){
        return devEndYmd;
    }
    public String getRequestor(){
        return requestor;
    }
    public String getSupplierSelectMethod(){
        return supplierSelectMethod;
    }
    public String getInAdvanceInputYn(){
        return inAdvanceInputYn;
    }
    public String getInAdvanceInputReason(){
        return inAdvanceInputReason;
    }
    public String getSpecialRelationYn(){
        return specialRelationYn;
    }
    public String getSpecialRelSelReason(){
        return specialRelSelReason;
    }
    public String getRschRspsrNm(){
        return rschRspsrNm;
    }
    public String getSupplierSelectYn(){
        return supplierSelectYn;
    }
    public String getContractNo(){
        return contractNo;
    }
    public String getContractName(){
        return contractName;
    }
    public String getContractTotCurrCode(){
        return contractTotCurrCode;
    }
    public BigDecimal getContractTotAmt(){
        return contractTotAmt;
    }
    public BigDecimal getContractTotWonAmt(){
        return contractTotWonAmt;
    }
    public String getMesConsCd(){
        return mesConsCd;
    }
    public String getSubjectContents(){
        return subjectContents;
    }
    public String getGpApplyYn(){
        return gpApplyYn;
    }
}

