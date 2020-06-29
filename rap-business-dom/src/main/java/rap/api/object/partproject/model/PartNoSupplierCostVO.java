/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartNoSupplierCostVO.java
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
public class PartNoSupplierCostVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        affiliateCode                                     ;
    private String        partNo                                            ;
    private String        itemDescription                                   ;
    private String        itemSpec                                          ;
    private String        supplierCode                                      ;
    private String        supplierName                                      ;
    private String        vendorCode                                        ;
    private String        vendorSiteId                                      ;
    private String        poMarketCode                                      ;
    private Float         poUnitPrice                                       ;
    private String        poCurrency                                        ;
    private Date          poStartDate                                       ;
    private Date          poEndDate                                         ;
    private String        baMarketCode                                      ;
    private String        baCurrency                                        ;
    private Date          baseDate                                          ;
    private Float         basePrice                                         ;
    private String        baTypeCode                                        ;
    private String        baEnabledFlag                                     ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setItemDescription(String itemDescription){
        this.itemDescription = itemDescription;
    }
    public void    setItemSpec(String itemSpec){
        this.itemSpec = itemSpec;
    }
    public void    setSupplierCode(String supplierCode){
        this.supplierCode = supplierCode;
    }
    public void    setSupplierName(String supplierName){
        this.supplierName = supplierName;
    }
    public void    setVendorCode(String vendorCode){
        this.vendorCode = vendorCode;
    }
    public void    setVendorSiteId(String vendorSiteId){
        this.vendorSiteId = vendorSiteId;
    }
    public void    setPoMarketCode(String poMarketCode){
        this.poMarketCode = poMarketCode;
    }
    public void    setPoUnitPrice(Float poUnitPrice){
        this.poUnitPrice = poUnitPrice;
    }
    public void    setPoCurrency(String poCurrency){
        this.poCurrency = poCurrency;
    }
    public void    setPoStartDate(Date poStartDate){
        this.poStartDate = poStartDate;
    }
    public void    setPoStartDate(String    poStartDate){
        this.poStartDate = this.omcConvertStr2Date(poStartDate);
    }
    public void    setPoEndDate(Date poEndDate){
        this.poEndDate = poEndDate;
    }
    public void    setPoEndDate(String    poEndDate){
        this.poEndDate = this.omcConvertStr2Date(poEndDate);
    }
    public void    setBaMarketCode(String baMarketCode){
        this.baMarketCode = baMarketCode;
    }
    public void    setBaCurrency(String baCurrency){
        this.baCurrency = baCurrency;
    }
    public void    setBaseDate(Date baseDate){
        this.baseDate = baseDate;
    }
    public void    setBaseDate(String    baseDate){
        this.baseDate = this.omcConvertStr2Date(baseDate);
    }
    public void    setBasePrice(Float basePrice){
        this.basePrice = basePrice;
    }
    public void    setBaTypeCode(String baTypeCode){
        this.baTypeCode = baTypeCode;
    }
    public void    setBaEnabledFlag(String baEnabledFlag){
        this.baEnabledFlag = baEnabledFlag;
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
    public String getItemDescription(){
        return itemDescription;
    }
    public String getItemSpec(){
        return itemSpec;
    }
    public String getSupplierCode(){
        return supplierCode;
    }
    public String getSupplierName(){
        return supplierName;
    }
    public String getVendorCode(){
        return vendorCode;
    }
    public String getVendorSiteId(){
        return vendorSiteId;
    }
    public String getPoMarketCode(){
        return poMarketCode;
    }
    public Float getPoUnitPrice(){
        return poUnitPrice;
    }
    public String getPoCurrency(){
        return poCurrency;
    }
    public Date getPoStartDate(){
        return poStartDate;
    }
    public Date getPoEndDate(){
        return poEndDate;
    }
    public String getBaMarketCode(){
        return baMarketCode;
    }
    public String getBaCurrency(){
        return baCurrency;
    }
    public Date getBaseDate(){
        return baseDate;
    }
    public Float getBasePrice(){
        return basePrice;
    }
    public String getBaTypeCode(){
        return baTypeCode;
    }
    public String getBaEnabledFlag(){
        return baEnabledFlag;
    }
}

