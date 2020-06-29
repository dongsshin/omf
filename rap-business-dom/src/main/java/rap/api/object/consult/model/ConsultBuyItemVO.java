/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultBuyItemVO.java
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
public class ConsultBuyItemVO extends BusinessObjectMasterVO {
    private String        cancelItemCd                                      ;
    private String        assFg                                             ;
    private String        assCd                                             ;
    private String        capExpAssCd                                       ;
    private String        ownerEmpNo                                        ;
    private String        largeClassCd                                      ;
    private String        midClassCd                                        ;
    private String        smallClassCd                                      ;
    private String        goodsNm                                           ;
    private String        standard                                          ;
    private String        modelNm                                           ;
    private String        makerNm                                           ;
    private Integer       qty                                               ;
    private String        unitCd                                             = "EA";
    private BigDecimal    unitAmt                                            = new BigDecimal(0);
    private BigDecimal    amt                                                = new BigDecimal(0);
    private String        currCd                                            ;
    private BigDecimal    exchRate                                           = new BigDecimal(0);
    private BigDecimal    wonUnitAmt                                         = new BigDecimal(0);
    private BigDecimal    wonAmt                                             = new BigDecimal(0);
    private String        stkReqYmd                                         ;
    private String        stkEndYn                                          ;
    private String        deadlineYmd                                       ;
    private String        provisionGoodsYn                                  ;
    private String        menufFg                                           ;
    private String        fab                                               ;
    private String        appendFileCd                                      ;
    private String        comments                                          ;
    private String        custNm                                            ;
    private Integer       dataSeqNo                                         ;
    private Integer       revisionNo                                        ;
    private String        custChgrNm                                        ;
    private String        custTelNo                                         ;
    private String        standardAmtYn                                      = "N";
    private String        enterCustCd                                       ;
    private String        enterCustNm                                       ;
    private Integer       orderAmt                                          ;
    private String        attr1                                             ;
    private String        examYn                                            ;
    private String        itemStatus                                        ;
    private String        imcStatusFg                                        = "0";
    private BigDecimal    calcAmtBuyItem                                     = new BigDecimal(0);
    private String        standardItemCd                                    ;
    private String        orderYmd                                          ;
    private String        famsFlag                                          ;
    private String        famsEcnCd                                         ;
    private String        famsParNo                                         ;
    private String        famsChilNo                                        ;
    private String        famsPumbun                                        ;
    private String        surplusCd                                         ;
    private String        mesOrdNm                                          ;
    private String        mesOrdSeq                                         ;
    private String        constructionType                                  ;
    private String        constructionKind                                  ;
    private String        constructionArea                                  ;
    private String        constructionDetail                                ;
    private String        merterial                                         ;
    private String        process                                           ;
    private String        planMh                                            ;
    private String        fileLink                                          ;
    private String        reqVendor                                         ;
    private String        provisionGoodsInfo                                ;
    private String        purReqNum                                         ;
    private String        purReqSeq                                         ;
    private String        prevPurReqSeq                                     ;
    private String        nextPurReqSeq                                     ;
    private String        nowPurReqSeq                                      ;
    private String        epsTransFg                                        ;
    private String        prodCode                                          ;
    private String        partNum                                           ;
    private String        materialSpec                                      ;
    private String        materialWeight                                    ;
    private String        cottonSquare                                      ;
    private String        grindingSquare                                    ;
    private String        grindingSquareNum                                 ;
    private String        materialCost                                      ;
    private String        cottonCost                                        ;
    private String        grindingCost                                      ;
    private String        withholdingYn                                     ;
    private String        withholdingSeq                                    ;
    private BigDecimal    withholdingTax                                     = new BigDecimal(0);
    private BigDecimal    residenceTax                                       = new BigDecimal(0);
    private String        sampleUseCode                                     ;
    private String        partNo                                            ;
    private BigDecimal    expenseRate                                        = new BigDecimal(0);
    private BigDecimal    consignMd                                          = new BigDecimal(0);
    private String        buyTypeOne                                        ;
    private String        buyTypeTwo                                        ;
    private String        buyTypeEtc                                        ;
    private String        appendFileName                                    ;
    private BigDecimal    deliveryExpense                                    = new BigDecimal(0);


    public void    setCancelItemCd(String cancelItemCd){
        this.cancelItemCd = cancelItemCd;
    }
    public void    setAssFg(String assFg){
        this.assFg = assFg;
    }
    public void    setAssCd(String assCd){
        this.assCd = assCd;
    }
    public void    setCapExpAssCd(String capExpAssCd){
        this.capExpAssCd = capExpAssCd;
    }
    public void    setOwnerEmpNo(String ownerEmpNo){
        this.ownerEmpNo = ownerEmpNo;
    }
    public void    setLargeClassCd(String largeClassCd){
        this.largeClassCd = largeClassCd;
    }
    public void    setMidClassCd(String midClassCd){
        this.midClassCd = midClassCd;
    }
    public void    setSmallClassCd(String smallClassCd){
        this.smallClassCd = smallClassCd;
    }
    public void    setGoodsNm(String goodsNm){
        this.goodsNm = goodsNm;
    }
    public void    setStandard(String standard){
        this.standard = standard;
    }
    public void    setModelNm(String modelNm){
        this.modelNm = modelNm;
    }
    public void    setMakerNm(String makerNm){
        this.makerNm = makerNm;
    }
    public void    setQty(Integer qty){
        this.qty = qty;
    }
    public void    setUnitCd(String unitCd){
        this.unitCd = unitCd;
    }
    public void    setUnitAmt(BigDecimal unitAmt){
        this.unitAmt = unitAmt;
    }
    public void    setAmt(BigDecimal amt){
        this.amt = amt;
    }
    public void    setCurrCd(String currCd){
        this.currCd = currCd;
    }
    public void    setExchRate(BigDecimal exchRate){
        this.exchRate = exchRate;
    }
    public void    setWonUnitAmt(BigDecimal wonUnitAmt){
        this.wonUnitAmt = wonUnitAmt;
    }
    public void    setWonAmt(BigDecimal wonAmt){
        this.wonAmt = wonAmt;
    }
    public void    setStkReqYmd(String stkReqYmd){
        this.stkReqYmd = stkReqYmd;
    }
    public void    setStkEndYn(String stkEndYn){
        this.stkEndYn = stkEndYn;
    }
    public void    setDeadlineYmd(String deadlineYmd){
        this.deadlineYmd = deadlineYmd;
    }
    public void    setProvisionGoodsYn(String provisionGoodsYn){
        this.provisionGoodsYn = provisionGoodsYn;
    }
    public void    setMenufFg(String menufFg){
        this.menufFg = menufFg;
    }
    public void    setFab(String fab){
        this.fab = fab;
    }
    public void    setAppendFileCd(String appendFileCd){
        this.appendFileCd = appendFileCd;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public void    setCustNm(String custNm){
        this.custNm = custNm;
    }
    public void    setDataSeqNo(Integer dataSeqNo){
        this.dataSeqNo = dataSeqNo;
    }
    public void    setRevisionNo(Integer revisionNo){
        this.revisionNo = revisionNo;
    }
    public void    setCustChgrNm(String custChgrNm){
        this.custChgrNm = custChgrNm;
    }
    public void    setCustTelNo(String custTelNo){
        this.custTelNo = custTelNo;
    }
    public void    setStandardAmtYn(String standardAmtYn){
        this.standardAmtYn = standardAmtYn;
    }
    public void    setEnterCustCd(String enterCustCd){
        this.enterCustCd = enterCustCd;
    }
    public void    setEnterCustNm(String enterCustNm){
        this.enterCustNm = enterCustNm;
    }
    public void    setOrderAmt(Integer orderAmt){
        this.orderAmt = orderAmt;
    }
    public void    setAttr1(String attr1){
        this.attr1 = attr1;
    }
    public void    setExamYn(String examYn){
        this.examYn = examYn;
    }
    public void    setItemStatus(String itemStatus){
        this.itemStatus = itemStatus;
    }
    public void    setImcStatusFg(String imcStatusFg){
        this.imcStatusFg = imcStatusFg;
    }
    public void    setCalcAmtBuyItem(BigDecimal calcAmtBuyItem){
        this.calcAmtBuyItem = calcAmtBuyItem;
    }
    public void    setStandardItemCd(String standardItemCd){
        this.standardItemCd = standardItemCd;
    }
    public void    setOrderYmd(String orderYmd){
        this.orderYmd = orderYmd;
    }
    public void    setFamsFlag(String famsFlag){
        this.famsFlag = famsFlag;
    }
    public void    setFamsEcnCd(String famsEcnCd){
        this.famsEcnCd = famsEcnCd;
    }
    public void    setFamsParNo(String famsParNo){
        this.famsParNo = famsParNo;
    }
    public void    setFamsChilNo(String famsChilNo){
        this.famsChilNo = famsChilNo;
    }
    public void    setFamsPumbun(String famsPumbun){
        this.famsPumbun = famsPumbun;
    }
    public void    setSurplusCd(String surplusCd){
        this.surplusCd = surplusCd;
    }
    public void    setMesOrdNm(String mesOrdNm){
        this.mesOrdNm = mesOrdNm;
    }
    public void    setMesOrdSeq(String mesOrdSeq){
        this.mesOrdSeq = mesOrdSeq;
    }
    public void    setConstructionType(String constructionType){
        this.constructionType = constructionType;
    }
    public void    setConstructionKind(String constructionKind){
        this.constructionKind = constructionKind;
    }
    public void    setConstructionArea(String constructionArea){
        this.constructionArea = constructionArea;
    }
    public void    setConstructionDetail(String constructionDetail){
        this.constructionDetail = constructionDetail;
    }
    public void    setMerterial(String merterial){
        this.merterial = merterial;
    }
    public void    setProcess(String process){
        this.process = process;
    }
    public void    setPlanMh(String planMh){
        this.planMh = planMh;
    }
    public void    setFileLink(String fileLink){
        this.fileLink = fileLink;
    }
    public void    setReqVendor(String reqVendor){
        this.reqVendor = reqVendor;
    }
    public void    setProvisionGoodsInfo(String provisionGoodsInfo){
        this.provisionGoodsInfo = provisionGoodsInfo;
    }
    public void    setPurReqNum(String purReqNum){
        this.purReqNum = purReqNum;
    }
    public void    setPurReqSeq(String purReqSeq){
        this.purReqSeq = purReqSeq;
    }
    public void    setPrevPurReqSeq(String prevPurReqSeq){
        this.prevPurReqSeq = prevPurReqSeq;
    }
    public void    setNextPurReqSeq(String nextPurReqSeq){
        this.nextPurReqSeq = nextPurReqSeq;
    }
    public void    setNowPurReqSeq(String nowPurReqSeq){
        this.nowPurReqSeq = nowPurReqSeq;
    }
    public void    setEpsTransFg(String epsTransFg){
        this.epsTransFg = epsTransFg;
    }
    public void    setProdCode(String prodCode){
        this.prodCode = prodCode;
    }
    public void    setPartNum(String partNum){
        this.partNum = partNum;
    }
    public void    setMaterialSpec(String materialSpec){
        this.materialSpec = materialSpec;
    }
    public void    setMaterialWeight(String materialWeight){
        this.materialWeight = materialWeight;
    }
    public void    setCottonSquare(String cottonSquare){
        this.cottonSquare = cottonSquare;
    }
    public void    setGrindingSquare(String grindingSquare){
        this.grindingSquare = grindingSquare;
    }
    public void    setGrindingSquareNum(String grindingSquareNum){
        this.grindingSquareNum = grindingSquareNum;
    }
    public void    setMaterialCost(String materialCost){
        this.materialCost = materialCost;
    }
    public void    setCottonCost(String cottonCost){
        this.cottonCost = cottonCost;
    }
    public void    setGrindingCost(String grindingCost){
        this.grindingCost = grindingCost;
    }
    public void    setWithholdingYn(String withholdingYn){
        this.withholdingYn = withholdingYn;
    }
    public void    setWithholdingSeq(String withholdingSeq){
        this.withholdingSeq = withholdingSeq;
    }
    public void    setWithholdingTax(BigDecimal withholdingTax){
        this.withholdingTax = withholdingTax;
    }
    public void    setResidenceTax(BigDecimal residenceTax){
        this.residenceTax = residenceTax;
    }
    public void    setSampleUseCode(String sampleUseCode){
        this.sampleUseCode = sampleUseCode;
    }
    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setExpenseRate(BigDecimal expenseRate){
        this.expenseRate = expenseRate;
    }
    public void    setConsignMd(BigDecimal consignMd){
        this.consignMd = consignMd;
    }
    public void    setBuyTypeOne(String buyTypeOne){
        this.buyTypeOne = buyTypeOne;
    }
    public void    setBuyTypeTwo(String buyTypeTwo){
        this.buyTypeTwo = buyTypeTwo;
    }
    public void    setBuyTypeEtc(String buyTypeEtc){
        this.buyTypeEtc = buyTypeEtc;
    }
    public void    setAppendFileName(String appendFileName){
        this.appendFileName = appendFileName;
    }
    public void    setDeliveryExpense(BigDecimal deliveryExpense){
        this.deliveryExpense = deliveryExpense;
    }
    public String getCancelItemCd(){
        return cancelItemCd;
    }
    public String getAssFg(){
        return assFg;
    }
    public String getAssCd(){
        return assCd;
    }
    public String getCapExpAssCd(){
        return capExpAssCd;
    }
    public String getOwnerEmpNo(){
        return ownerEmpNo;
    }
    public String getLargeClassCd(){
        return largeClassCd;
    }
    public String getMidClassCd(){
        return midClassCd;
    }
    public String getSmallClassCd(){
        return smallClassCd;
    }
    public String getGoodsNm(){
        return goodsNm;
    }
    public String getStandard(){
        return standard;
    }
    public String getModelNm(){
        return modelNm;
    }
    public String getMakerNm(){
        return makerNm;
    }
    public Integer getQty(){
        return qty;
    }
    public String getUnitCd(){
        return unitCd;
    }
    public BigDecimal getUnitAmt(){
        return unitAmt;
    }
    public BigDecimal getAmt(){
        return amt;
    }
    public String getCurrCd(){
        return currCd;
    }
    public BigDecimal getExchRate(){
        return exchRate;
    }
    public BigDecimal getWonUnitAmt(){
        return wonUnitAmt;
    }
    public BigDecimal getWonAmt(){
        return wonAmt;
    }
    public String getStkReqYmd(){
        return stkReqYmd;
    }
    public String getStkEndYn(){
        return stkEndYn;
    }
    public String getDeadlineYmd(){
        return deadlineYmd;
    }
    public String getProvisionGoodsYn(){
        return provisionGoodsYn;
    }
    public String getMenufFg(){
        return menufFg;
    }
    public String getFab(){
        return fab;
    }
    public String getAppendFileCd(){
        return appendFileCd;
    }
    public String getComments(){
        return comments;
    }
    public String getCustNm(){
        return custNm;
    }
    public Integer getDataSeqNo(){
        return dataSeqNo;
    }
    public Integer getRevisionNo(){
        return revisionNo;
    }
    public String getCustChgrNm(){
        return custChgrNm;
    }
    public String getCustTelNo(){
        return custTelNo;
    }
    public String getStandardAmtYn(){
        return standardAmtYn;
    }
    public String getEnterCustCd(){
        return enterCustCd;
    }
    public String getEnterCustNm(){
        return enterCustNm;
    }
    public Integer getOrderAmt(){
        return orderAmt;
    }
    public String getAttr1(){
        return attr1;
    }
    public String getExamYn(){
        return examYn;
    }
    public String getItemStatus(){
        return itemStatus;
    }
    public String getImcStatusFg(){
        return imcStatusFg;
    }
    public BigDecimal getCalcAmtBuyItem(){
        return calcAmtBuyItem;
    }
    public String getStandardItemCd(){
        return standardItemCd;
    }
    public String getOrderYmd(){
        return orderYmd;
    }
    public String getFamsFlag(){
        return famsFlag;
    }
    public String getFamsEcnCd(){
        return famsEcnCd;
    }
    public String getFamsParNo(){
        return famsParNo;
    }
    public String getFamsChilNo(){
        return famsChilNo;
    }
    public String getFamsPumbun(){
        return famsPumbun;
    }
    public String getSurplusCd(){
        return surplusCd;
    }
    public String getMesOrdNm(){
        return mesOrdNm;
    }
    public String getMesOrdSeq(){
        return mesOrdSeq;
    }
    public String getConstructionType(){
        return constructionType;
    }
    public String getConstructionKind(){
        return constructionKind;
    }
    public String getConstructionArea(){
        return constructionArea;
    }
    public String getConstructionDetail(){
        return constructionDetail;
    }
    public String getMerterial(){
        return merterial;
    }
    public String getProcess(){
        return process;
    }
    public String getPlanMh(){
        return planMh;
    }
    public String getFileLink(){
        return fileLink;
    }
    public String getReqVendor(){
        return reqVendor;
    }
    public String getProvisionGoodsInfo(){
        return provisionGoodsInfo;
    }
    public String getPurReqNum(){
        return purReqNum;
    }
    public String getPurReqSeq(){
        return purReqSeq;
    }
    public String getPrevPurReqSeq(){
        return prevPurReqSeq;
    }
    public String getNextPurReqSeq(){
        return nextPurReqSeq;
    }
    public String getNowPurReqSeq(){
        return nowPurReqSeq;
    }
    public String getEpsTransFg(){
        return epsTransFg;
    }
    public String getProdCode(){
        return prodCode;
    }
    public String getPartNum(){
        return partNum;
    }
    public String getMaterialSpec(){
        return materialSpec;
    }
    public String getMaterialWeight(){
        return materialWeight;
    }
    public String getCottonSquare(){
        return cottonSquare;
    }
    public String getGrindingSquare(){
        return grindingSquare;
    }
    public String getGrindingSquareNum(){
        return grindingSquareNum;
    }
    public String getMaterialCost(){
        return materialCost;
    }
    public String getCottonCost(){
        return cottonCost;
    }
    public String getGrindingCost(){
        return grindingCost;
    }
    public String getWithholdingYn(){
        return withholdingYn;
    }
    public String getWithholdingSeq(){
        return withholdingSeq;
    }
    public BigDecimal getWithholdingTax(){
        return withholdingTax;
    }
    public BigDecimal getResidenceTax(){
        return residenceTax;
    }
    public String getSampleUseCode(){
        return sampleUseCode;
    }
    public String getPartNo(){
        return partNo;
    }
    public BigDecimal getExpenseRate(){
        return expenseRate;
    }
    public BigDecimal getConsignMd(){
        return consignMd;
    }
    public String getBuyTypeOne(){
        return buyTypeOne;
    }
    public String getBuyTypeTwo(){
        return buyTypeTwo;
    }
    public String getBuyTypeEtc(){
        return buyTypeEtc;
    }
    public String getAppendFileName(){
        return appendFileName;
    }
    public BigDecimal getDeliveryExpense(){
        return deliveryExpense;
    }
}

