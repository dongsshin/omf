/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MoldInfoVO.java
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
public class MoldInfoVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        affiliateCode                                     ;
    private String        partNo                                            ;
    private Integer       seq                                               ;
    private String        itemType                                          ;
    private String        cavQty                                            ;
    private String        orderSupplierCode                                 ;
    private String        orderSupplierName                                 ;
    private BigDecimal    orderAmount                                        = new BigDecimal(0);
    private String        currency                                          ;
    private String        orderRequester                                    ;
    private String        orderDate                                         ;
    private Date          createDate                                        ;
    private Date          transferDate                                      ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setSeq(Integer seq){
        this.seq = seq;
    }
    public void    setItemType(String itemType){
        this.itemType = itemType;
    }
    public void    setCavQty(String cavQty){
        this.cavQty = cavQty;
    }
    public void    setOrderSupplierCode(String orderSupplierCode){
        this.orderSupplierCode = orderSupplierCode;
    }
    public void    setOrderSupplierName(String orderSupplierName){
        this.orderSupplierName = orderSupplierName;
    }
    public void    setOrderAmount(BigDecimal orderAmount){
        this.orderAmount = orderAmount;
    }
    public void    setCurrency(String currency){
        this.currency = currency;
    }
    public void    setOrderRequester(String orderRequester){
        this.orderRequester = orderRequester;
    }
    public void    setOrderDate(String orderDate){
        this.orderDate = orderDate;
    }
    public void    setCreateDate(Date createDate){
        this.createDate = createDate;
    }
    public void    setCreateDate(String    createDate){
        this.createDate = this.omcConvertStr2Date(createDate);
    }
    public void    setTransferDate(Date transferDate){
        this.transferDate = transferDate;
    }
    public void    setTransferDate(String    transferDate){
        this.transferDate = this.omcConvertStr2Date(transferDate);
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public String getPartNo(){
        return partNo;
    }
    public Integer getSeq(){
        return seq;
    }
    public String getItemType(){
        return itemType;
    }
    public String getCavQty(){
        return cavQty;
    }
    public String getOrderSupplierCode(){
        return orderSupplierCode;
    }
    public String getOrderSupplierName(){
        return orderSupplierName;
    }
    public BigDecimal getOrderAmount(){
        return orderAmount;
    }
    public String getCurrency(){
        return currency;
    }
    public String getOrderRequester(){
        return orderRequester;
    }
    public String getOrderDate(){
        return orderDate;
    }
    public Date getCreateDate(){
        return createDate;
    }
    public Date getTransferDate(){
        return transferDate;
    }
}

