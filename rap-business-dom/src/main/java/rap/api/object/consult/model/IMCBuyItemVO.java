/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : IMCBuyItemVO.java
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
public class IMCBuyItemVO extends BusinessObjectMasterVO {
    private String        receiptNo                                         ;
    private String        receiptItemCd                                     ;
    private BigDecimal    divReqAmt                                          = new BigDecimal(0);
    private BigDecimal    ordAmt                                             = new BigDecimal(0);
    private String        ordYmd                                            ;
    private BigDecimal    calcAmt                                            = new BigDecimal(0);
    private String        calcYmd                                           ;
    private String        transYmd                                          ;
    private String        ordCancelYmd                                      ;
    private String        status                                            ;
    private String        orderSupplierCode                                 ;
    private String        supplierBizNo                                     ;
    private String        supplierName                                      ;


    public void    setReceiptNo(String receiptNo){
        this.receiptNo = receiptNo;
    }
    public void    setReceiptItemCd(String receiptItemCd){
        this.receiptItemCd = receiptItemCd;
    }
    public void    setDivReqAmt(BigDecimal divReqAmt){
        this.divReqAmt = divReqAmt;
    }
    public void    setOrdAmt(BigDecimal ordAmt){
        this.ordAmt = ordAmt;
    }
    public void    setOrdYmd(String ordYmd){
        this.ordYmd = ordYmd;
    }
    public void    setCalcAmt(BigDecimal calcAmt){
        this.calcAmt = calcAmt;
    }
    public void    setCalcYmd(String calcYmd){
        this.calcYmd = calcYmd;
    }
    public void    setTransYmd(String transYmd){
        this.transYmd = transYmd;
    }
    public void    setOrdCancelYmd(String ordCancelYmd){
        this.ordCancelYmd = ordCancelYmd;
    }
    public void    setStatus(String status){
        this.status = status;
    }
    public void    setOrderSupplierCode(String orderSupplierCode){
        this.orderSupplierCode = orderSupplierCode;
    }
    public void    setSupplierBizNo(String supplierBizNo){
        this.supplierBizNo = supplierBizNo;
    }
    public void    setSupplierName(String supplierName){
        this.supplierName = supplierName;
    }
    public String getReceiptNo(){
        return receiptNo;
    }
    public String getReceiptItemCd(){
        return receiptItemCd;
    }
    public BigDecimal getDivReqAmt(){
        return divReqAmt;
    }
    public BigDecimal getOrdAmt(){
        return ordAmt;
    }
    public String getOrdYmd(){
        return ordYmd;
    }
    public BigDecimal getCalcAmt(){
        return calcAmt;
    }
    public String getCalcYmd(){
        return calcYmd;
    }
    public String getTransYmd(){
        return transYmd;
    }
    public String getOrdCancelYmd(){
        return ordCancelYmd;
    }
    public String getStatus(){
        return status;
    }
    public String getOrderSupplierCode(){
        return orderSupplierCode;
    }
    public String getSupplierBizNo(){
        return supplierBizNo;
    }
    public String getSupplierName(){
        return supplierName;
    }
}

