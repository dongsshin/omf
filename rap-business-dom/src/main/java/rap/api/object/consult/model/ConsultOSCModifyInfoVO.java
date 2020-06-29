/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultOSCModifyInfoVO.java
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
public class ConsultOSCModifyInfoVO extends BusinessObjectMasterVO {
    private Integer       dtlSeq                                            ;
    private String        itemName                                          ;
    private Integer       qty                                               ;
    private BigDecimal    unitAmt                                            = new BigDecimal(0);
    private BigDecimal    amt                                                = new BigDecimal(0);
    private String        attatchFileCd                                     ;
    private String        attatchFilePath                                   ;
    private String        deliveryDate                                      ;
    private String        modelNm                                           ;
    private String        spec                                              ;
    private String        remark                                            ;
    private String        status                                            ;
    private String        modiGb                                            ;
    private String        othVendorCode                                     ;
    private String        othVendorName                                     ;
    private String        othVendorMail                                     ;
    private String        othVendorTel                                      ;
    private String        othVendorEmp                                      ;
    private String        othVendorStatus                                   ;
    private String        othVendorComment                                  ;
    private String        ifSendFlag                                        ;
    private Date          ifSendDate                                        ;
    private String        newItemCode                                       ;
    private String        modiOutType                                       ;
    private String        modiOutVendorCode                                 ;
    private String        modiOutVendorName                                 ;
    private String        estimateNo                                        ;
    private String        voucherNo                                         ;
    private String        overlapException                                  ;
    private String        overlapReason                                     ;
    private String        modiOutReason                                     ;
    private String        modiOutReasonText                                 ;


    public void    setDtlSeq(Integer dtlSeq){
        this.dtlSeq = dtlSeq;
    }
    public void    setItemName(String itemName){
        this.itemName = itemName;
    }
    public void    setQty(Integer qty){
        this.qty = qty;
    }
    public void    setUnitAmt(BigDecimal unitAmt){
        this.unitAmt = unitAmt;
    }
    public void    setAmt(BigDecimal amt){
        this.amt = amt;
    }
    public void    setAttatchFileCd(String attatchFileCd){
        this.attatchFileCd = attatchFileCd;
    }
    public void    setAttatchFilePath(String attatchFilePath){
        this.attatchFilePath = attatchFilePath;
    }
    public void    setDeliveryDate(String deliveryDate){
        this.deliveryDate = deliveryDate;
    }
    public void    setModelNm(String modelNm){
        this.modelNm = modelNm;
    }
    public void    setSpec(String spec){
        this.spec = spec;
    }
    public void    setRemark(String remark){
        this.remark = remark;
    }
    public void    setStatus(String status){
        this.status = status;
    }
    public void    setModiGb(String modiGb){
        this.modiGb = modiGb;
    }
    public void    setOthVendorCode(String othVendorCode){
        this.othVendorCode = othVendorCode;
    }
    public void    setOthVendorName(String othVendorName){
        this.othVendorName = othVendorName;
    }
    public void    setOthVendorMail(String othVendorMail){
        this.othVendorMail = othVendorMail;
    }
    public void    setOthVendorTel(String othVendorTel){
        this.othVendorTel = othVendorTel;
    }
    public void    setOthVendorEmp(String othVendorEmp){
        this.othVendorEmp = othVendorEmp;
    }
    public void    setOthVendorStatus(String othVendorStatus){
        this.othVendorStatus = othVendorStatus;
    }
    public void    setOthVendorComment(String othVendorComment){
        this.othVendorComment = othVendorComment;
    }
    public void    setIfSendFlag(String ifSendFlag){
        this.ifSendFlag = ifSendFlag;
    }
    public void    setIfSendDate(Date ifSendDate){
        this.ifSendDate = ifSendDate;
    }
    public void    setIfSendDate(String    ifSendDate){
        this.ifSendDate = this.omcConvertStr2Date(ifSendDate);
    }
    public void    setNewItemCode(String newItemCode){
        this.newItemCode = newItemCode;
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
    public void    setModiOutReason(String modiOutReason){
        this.modiOutReason = modiOutReason;
    }
    public void    setModiOutReasonText(String modiOutReasonText){
        this.modiOutReasonText = modiOutReasonText;
    }
    public Integer getDtlSeq(){
        return dtlSeq;
    }
    public String getItemName(){
        return itemName;
    }
    public Integer getQty(){
        return qty;
    }
    public BigDecimal getUnitAmt(){
        return unitAmt;
    }
    public BigDecimal getAmt(){
        return amt;
    }
    public String getAttatchFileCd(){
        return attatchFileCd;
    }
    public String getAttatchFilePath(){
        return attatchFilePath;
    }
    public String getDeliveryDate(){
        return deliveryDate;
    }
    public String getModelNm(){
        return modelNm;
    }
    public String getSpec(){
        return spec;
    }
    public String getRemark(){
        return remark;
    }
    public String getStatus(){
        return status;
    }
    public String getModiGb(){
        return modiGb;
    }
    public String getOthVendorCode(){
        return othVendorCode;
    }
    public String getOthVendorName(){
        return othVendorName;
    }
    public String getOthVendorMail(){
        return othVendorMail;
    }
    public String getOthVendorTel(){
        return othVendorTel;
    }
    public String getOthVendorEmp(){
        return othVendorEmp;
    }
    public String getOthVendorStatus(){
        return othVendorStatus;
    }
    public String getOthVendorComment(){
        return othVendorComment;
    }
    public String getIfSendFlag(){
        return ifSendFlag;
    }
    public Date getIfSendDate(){
        return ifSendDate;
    }
    public String getNewItemCode(){
        return newItemCode;
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
    public String getModiOutReason(){
        return modiOutReason;
    }
    public String getModiOutReasonText(){
        return modiOutReasonText;
    }
}

