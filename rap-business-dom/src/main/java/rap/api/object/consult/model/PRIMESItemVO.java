/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PRIMESItemVO.java
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
public class PRIMESItemVO extends BusinessObjectMasterVO {
    private String        purReqNum                                         ;
    private String        purReqSeq                                         ;
    private String        pjtCd                                             ;
    private String        acctCd                                            ;
    private String        consAcctCd                                        ;
    private String        itemNm                                            ;
    private String        spec                                              ;
    private String        modelNm                                           ;
    private String        maker                                             ;
    private String        merterial                                         ;
    private String        process                                           ;
    private String        planMh                                            ;
    private String        fileLink                                          ;
    private String        reqVendor                                         ;
    private String        insFlag                                           ;
    private String        reqDate                                           ;
    private String        ypgoLocation                                      ;
    private String        provisionGoodsYn                                  ;
    private String        prevPurReqSeq                                     ;
    private String        nextPurReqSeq                                     ;
    private String        nowPurReqSeq                                      ;
    private String        provisionGoodsInfo                                ;
    private String        currCd                                            ;
    private Float         unitCost                                          ;
    private Integer       qty                                               ;
    private BigDecimal    amt                                                = new BigDecimal(0);
    private String        scomment                                          ;
    private String        consCd                                            ;
    private String        progStatFg                                        ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
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


    public void    setPurReqNum(String purReqNum){
        this.purReqNum = purReqNum;
    }
    public void    setPurReqSeq(String purReqSeq){
        this.purReqSeq = purReqSeq;
    }
    public void    setPjtCd(String pjtCd){
        this.pjtCd = pjtCd;
    }
    public void    setAcctCd(String acctCd){
        this.acctCd = acctCd;
    }
    public void    setConsAcctCd(String consAcctCd){
        this.consAcctCd = consAcctCd;
    }
    public void    setItemNm(String itemNm){
        this.itemNm = itemNm;
    }
    public void    setSpec(String spec){
        this.spec = spec;
    }
    public void    setModelNm(String modelNm){
        this.modelNm = modelNm;
    }
    public void    setMaker(String maker){
        this.maker = maker;
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
    public void    setInsFlag(String insFlag){
        this.insFlag = insFlag;
    }
    public void    setReqDate(String reqDate){
        this.reqDate = reqDate;
    }
    public void    setYpgoLocation(String ypgoLocation){
        this.ypgoLocation = ypgoLocation;
    }
    public void    setProvisionGoodsYn(String provisionGoodsYn){
        this.provisionGoodsYn = provisionGoodsYn;
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
    public void    setProvisionGoodsInfo(String provisionGoodsInfo){
        this.provisionGoodsInfo = provisionGoodsInfo;
    }
    public void    setCurrCd(String currCd){
        this.currCd = currCd;
    }
    public void    setUnitCost(Float unitCost){
        this.unitCost = unitCost;
    }
    public void    setQty(Integer qty){
        this.qty = qty;
    }
    public void    setAmt(BigDecimal amt){
        this.amt = amt;
    }
    public void    setScomment(String scomment){
        this.scomment = scomment;
    }
    public void    setConsCd(String consCd){
        this.consCd = consCd;
    }
    public void    setProgStatFg(String progStatFg){
        this.progStatFg = progStatFg;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public void    setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
    public void    setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
    public void    setAttribute5(String attribute5){
        this.attribute5 = attribute5;
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
    public String getPurReqNum(){
        return purReqNum;
    }
    public String getPurReqSeq(){
        return purReqSeq;
    }
    public String getPjtCd(){
        return pjtCd;
    }
    public String getAcctCd(){
        return acctCd;
    }
    public String getConsAcctCd(){
        return consAcctCd;
    }
    public String getItemNm(){
        return itemNm;
    }
    public String getSpec(){
        return spec;
    }
    public String getModelNm(){
        return modelNm;
    }
    public String getMaker(){
        return maker;
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
    public String getInsFlag(){
        return insFlag;
    }
    public String getReqDate(){
        return reqDate;
    }
    public String getYpgoLocation(){
        return ypgoLocation;
    }
    public String getProvisionGoodsYn(){
        return provisionGoodsYn;
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
    public String getProvisionGoodsInfo(){
        return provisionGoodsInfo;
    }
    public String getCurrCd(){
        return currCd;
    }
    public Float getUnitCost(){
        return unitCost;
    }
    public Integer getQty(){
        return qty;
    }
    public BigDecimal getAmt(){
        return amt;
    }
    public String getScomment(){
        return scomment;
    }
    public String getConsCd(){
        return consCd;
    }
    public String getProgStatFg(){
        return progStatFg;
    }
    public String getAttribute1(){
        return attribute1;
    }
    public String getAttribute2(){
        return attribute2;
    }
    public String getAttribute3(){
        return attribute3;
    }
    public String getAttribute4(){
        return attribute4;
    }
    public String getAttribute5(){
        return attribute5;
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
}

