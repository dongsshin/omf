/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultModifyOrderVO.java
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
public class ConsultModifyOrderVO extends BusinessObjectMasterVO {
    private Integer       modiSeq                                           ;
    private String        refConsObid                                       ;
    private String        remark                                            ;
    private String        refItemObid                                       ;
    private String        modiConsObid                                      ;
    private String        status                                            ;
    private String        modiVendorCode                                    ;
    private String        modiVendorNm                                      ;
    private String        gubun                                             ;
    private String        vendorCode                                        ;
    private String        refConsCode                                       ;
    private String        refItemCode                                       ;
    private String        designGb                                          ;
    private String        designPortion                                     ;
    private String        designDegree                                      ;
    private String        attatchFileCd                                     ;
    private String        attatchFilePath                                   ;
    private String        jepmClassL                                        ;
    private String        jepmClassM                                        ;
    private String        jepmClassS                                        ;
    private String        modiConsCd                                        ;
    private String        modiOutType                                       ;
    private String        modiOutVendorCode                                 ;
    private String        modiOutVendorName                                 ;
    private String        estimateNo                                        ;
    private String        voucherNo                                         ;
    private String        overlapException                                  ;
    private String        overlapReason                                     ;
    private String        deliveryTime                                      ;
    private String        consignLevel                                      ;
    private String        consignCategory                                   ;
    private String        consignVendor                                     ;
    private String        consignVendorName                                 ;
    private String        modiOutReason                                     ;
    private String        modiOutReasonText                                 ;
    private String        changeVendorReason                                ;
    private String        designRequestor                                   ;
    private Integer       refModiSeq                                        ;
    private String        vendorName                                        ;
    private Integer       refDtlSeq                                         ;


    public void    setModiSeq(Integer modiSeq){
        this.modiSeq = modiSeq;
    }
    public void    setRefConsObid(String refConsObid){
        this.refConsObid = refConsObid;
    }
    public void    setRemark(String remark){
        this.remark = remark;
    }
    public void    setRefItemObid(String refItemObid){
        this.refItemObid = refItemObid;
    }
    public void    setModiConsObid(String modiConsObid){
        this.modiConsObid = modiConsObid;
    }
    public void    setStatus(String status){
        this.status = status;
    }
    public void    setModiVendorCode(String modiVendorCode){
        this.modiVendorCode = modiVendorCode;
    }
    public void    setModiVendorNm(String modiVendorNm){
        this.modiVendorNm = modiVendorNm;
    }
    public void    setGubun(String gubun){
        this.gubun = gubun;
    }
    public void    setVendorCode(String vendorCode){
        this.vendorCode = vendorCode;
    }
    public void    setRefConsCode(String refConsCode){
        this.refConsCode = refConsCode;
    }
    public void    setRefItemCode(String refItemCode){
        this.refItemCode = refItemCode;
    }
    public void    setDesignGb(String designGb){
        this.designGb = designGb;
    }
    public void    setDesignPortion(String designPortion){
        this.designPortion = designPortion;
    }
    public void    setDesignDegree(String designDegree){
        this.designDegree = designDegree;
    }
    public void    setAttatchFileCd(String attatchFileCd){
        this.attatchFileCd = attatchFileCd;
    }
    public void    setAttatchFilePath(String attatchFilePath){
        this.attatchFilePath = attatchFilePath;
    }
    public void    setJepmClassL(String jepmClassL){
        this.jepmClassL = jepmClassL;
    }
    public void    setJepmClassM(String jepmClassM){
        this.jepmClassM = jepmClassM;
    }
    public void    setJepmClassS(String jepmClassS){
        this.jepmClassS = jepmClassS;
    }
    public void    setModiConsCd(String modiConsCd){
        this.modiConsCd = modiConsCd;
    }
    public void    setModiOutType(String modiOutType){
        this.modiOutType = modiOutType;
    }
    public void    setModiOutVendorCode(String modiOutVendorCode){
        this.modiOutVendorCode = modiOutVendorCode;
    }
    public void    setModiOutVendorName(String modiOutVendorName){
        this.modiOutVendorName = modiOutVendorName;
    }
    public void    setEstimateNo(String estimateNo){
        this.estimateNo = estimateNo;
    }
    public void    setVoucherNo(String voucherNo){
        this.voucherNo = voucherNo;
    }
    public void    setOverlapException(String overlapException){
        this.overlapException = overlapException;
    }
    public void    setOverlapReason(String overlapReason){
        this.overlapReason = overlapReason;
    }
    public void    setDeliveryTime(String deliveryTime){
        this.deliveryTime = deliveryTime;
    }
    public void    setConsignLevel(String consignLevel){
        this.consignLevel = consignLevel;
    }
    public void    setConsignCategory(String consignCategory){
        this.consignCategory = consignCategory;
    }
    public void    setConsignVendor(String consignVendor){
        this.consignVendor = consignVendor;
    }
    public void    setConsignVendorName(String consignVendorName){
        this.consignVendorName = consignVendorName;
    }
    public void    setModiOutReason(String modiOutReason){
        this.modiOutReason = modiOutReason;
    }
    public void    setModiOutReasonText(String modiOutReasonText){
        this.modiOutReasonText = modiOutReasonText;
    }
    public void    setChangeVendorReason(String changeVendorReason){
        this.changeVendorReason = changeVendorReason;
    }
    public void    setDesignRequestor(String designRequestor){
        this.designRequestor = designRequestor;
    }
    public void    setRefModiSeq(Integer refModiSeq){
        this.refModiSeq = refModiSeq;
    }
    public void    setVendorName(String vendorName){
        this.vendorName = vendorName;
    }
    public void    setRefDtlSeq(Integer refDtlSeq){
        this.refDtlSeq = refDtlSeq;
    }
    public Integer getModiSeq(){
        return modiSeq;
    }
    public String getRefConsObid(){
        return refConsObid;
    }
    public String getRemark(){
        return remark;
    }
    public String getRefItemObid(){
        return refItemObid;
    }
    public String getModiConsObid(){
        return modiConsObid;
    }
    public String getStatus(){
        return status;
    }
    public String getModiVendorCode(){
        return modiVendorCode;
    }
    public String getModiVendorNm(){
        return modiVendorNm;
    }
    public String getGubun(){
        return gubun;
    }
    public String getVendorCode(){
        return vendorCode;
    }
    public String getRefConsCode(){
        return refConsCode;
    }
    public String getRefItemCode(){
        return refItemCode;
    }
    public String getDesignGb(){
        return designGb;
    }
    public String getDesignPortion(){
        return designPortion;
    }
    public String getDesignDegree(){
        return designDegree;
    }
    public String getAttatchFileCd(){
        return attatchFileCd;
    }
    public String getAttatchFilePath(){
        return attatchFilePath;
    }
    public String getJepmClassL(){
        return jepmClassL;
    }
    public String getJepmClassM(){
        return jepmClassM;
    }
    public String getJepmClassS(){
        return jepmClassS;
    }
    public String getModiConsCd(){
        return modiConsCd;
    }
    public String getModiOutType(){
        return modiOutType;
    }
    public String getModiOutVendorCode(){
        return modiOutVendorCode;
    }
    public String getModiOutVendorName(){
        return modiOutVendorName;
    }
    public String getEstimateNo(){
        return estimateNo;
    }
    public String getVoucherNo(){
        return voucherNo;
    }
    public String getOverlapException(){
        return overlapException;
    }
    public String getOverlapReason(){
        return overlapReason;
    }
    public String getDeliveryTime(){
        return deliveryTime;
    }
    public String getConsignLevel(){
        return consignLevel;
    }
    public String getConsignCategory(){
        return consignCategory;
    }
    public String getConsignVendor(){
        return consignVendor;
    }
    public String getConsignVendorName(){
        return consignVendorName;
    }
    public String getModiOutReason(){
        return modiOutReason;
    }
    public String getModiOutReasonText(){
        return modiOutReasonText;
    }
    public String getChangeVendorReason(){
        return changeVendorReason;
    }
    public String getDesignRequestor(){
        return designRequestor;
    }
    public Integer getRefModiSeq(){
        return refModiSeq;
    }
    public String getVendorName(){
        return vendorName;
    }
    public Integer getRefDtlSeq(){
        return refDtlSeq;
    }
}

