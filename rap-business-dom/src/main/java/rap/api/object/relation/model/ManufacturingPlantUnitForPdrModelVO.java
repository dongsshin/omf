/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ManufacturingPlantUnitForPdrModelVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import rap.api.object.relation.model.ManufacturingPlantUnitRootVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ManufacturingPlantUnitForPdrModelVO extends ManufacturingPlantUnitRootVO {
    private String        cpUnitCode                                        ;
    private String        masterId                                          ;
    private String        legacyProductModelName                            ;
    private String        legacyProductSuffixCode                           ;
    private String        marketCode                                        ;
    private String        billToCustomerCode                                ;
    private String        shipToCustomerCode                                ;
    private String        customerModelName                                 ;
    private String        brandTypeName                                     ;
    private String        salesModelName                                    ;
    private String        salesModelSuffixCode                              ;
    private String        typeCode                                          ;
    private String        kanEanUpcCode                                     ;
    private String        productCode                                       ;
    private String        productLineCode                                   ;
    private String        setPartNo                                         ;
    private String        firstSuffixCode                                   ;
    private Date          suffixConfirmDate                                 ;
    private String        productTypeCode                                   ;
    private Date          disabledDate                                      ;
    private String        disabledCode                                      ;
    private String        useFlag                                           ;
    private Date          bomCreationDate                                   ;
    private Date          erpInterfaceDate                                  ;
    private Date          bomProdCreationDate                               ;
    private String        erpProductionYyyymmdd                             ;
    private String        modelDesc                                         ;
    private String        previousModelCode                                 ;
    private String        previousSuffixCode                                ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        attribute6                                        ;
    private String        attribute7                                        ;
    private String        attribute8                                        ;
    private String        attribute9                                        ;
    private String        attribute10                                       ;
    private String        salesModelFlag                                    ;


    public void    setCpUnitCode(String cpUnitCode){
        this.cpUnitCode = cpUnitCode;
    }
    public void    setMasterId(String masterId){
        this.masterId = masterId;
    }
    public void    setLegacyProductModelName(String legacyProductModelName){
        this.legacyProductModelName = legacyProductModelName;
    }
    public void    setLegacyProductSuffixCode(String legacyProductSuffixCode){
        this.legacyProductSuffixCode = legacyProductSuffixCode;
    }
    public void    setMarketCode(String marketCode){
        this.marketCode = marketCode;
    }
    public void    setBillToCustomerCode(String billToCustomerCode){
        this.billToCustomerCode = billToCustomerCode;
    }
    public void    setShipToCustomerCode(String shipToCustomerCode){
        this.shipToCustomerCode = shipToCustomerCode;
    }
    public void    setCustomerModelName(String customerModelName){
        this.customerModelName = customerModelName;
    }
    public void    setBrandTypeName(String brandTypeName){
        this.brandTypeName = brandTypeName;
    }
    public void    setSalesModelName(String salesModelName){
        this.salesModelName = salesModelName;
    }
    public void    setSalesModelSuffixCode(String salesModelSuffixCode){
        this.salesModelSuffixCode = salesModelSuffixCode;
    }
    public void    setTypeCode(String typeCode){
        this.typeCode = typeCode;
    }
    public void    setKanEanUpcCode(String kanEanUpcCode){
        this.kanEanUpcCode = kanEanUpcCode;
    }
    public void    setProductCode(String productCode){
        this.productCode = productCode;
    }
    public void    setProductLineCode(String productLineCode){
        this.productLineCode = productLineCode;
    }
    public void    setSetPartNo(String setPartNo){
        this.setPartNo = setPartNo;
    }
    public void    setFirstSuffixCode(String firstSuffixCode){
        this.firstSuffixCode = firstSuffixCode;
    }
    public void    setSuffixConfirmDate(Date suffixConfirmDate){
        this.suffixConfirmDate = suffixConfirmDate;
    }
    public void    setSuffixConfirmDate(String    suffixConfirmDate){
        this.suffixConfirmDate = this.omcConvertStr2Date(suffixConfirmDate);
    }
    public void    setProductTypeCode(String productTypeCode){
        this.productTypeCode = productTypeCode;
    }
    public void    setDisabledDate(Date disabledDate){
        this.disabledDate = disabledDate;
    }
    public void    setDisabledDate(String    disabledDate){
        this.disabledDate = this.omcConvertStr2Date(disabledDate);
    }
    public void    setDisabledCode(String disabledCode){
        this.disabledCode = disabledCode;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setBomCreationDate(Date bomCreationDate){
        this.bomCreationDate = bomCreationDate;
    }
    public void    setBomCreationDate(String    bomCreationDate){
        this.bomCreationDate = this.omcConvertStr2Date(bomCreationDate);
    }
    public void    setErpInterfaceDate(Date erpInterfaceDate){
        this.erpInterfaceDate = erpInterfaceDate;
    }
    public void    setErpInterfaceDate(String    erpInterfaceDate){
        this.erpInterfaceDate = this.omcConvertStr2Date(erpInterfaceDate);
    }
    public void    setBomProdCreationDate(Date bomProdCreationDate){
        this.bomProdCreationDate = bomProdCreationDate;
    }
    public void    setBomProdCreationDate(String    bomProdCreationDate){
        this.bomProdCreationDate = this.omcConvertStr2Date(bomProdCreationDate);
    }
    public void    setErpProductionYyyymmdd(String erpProductionYyyymmdd){
        this.erpProductionYyyymmdd = erpProductionYyyymmdd;
    }
    public void    setModelDesc(String modelDesc){
        this.modelDesc = modelDesc;
    }
    public void    setPreviousModelCode(String previousModelCode){
        this.previousModelCode = previousModelCode;
    }
    public void    setPreviousSuffixCode(String previousSuffixCode){
        this.previousSuffixCode = previousSuffixCode;
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
    public void    setAttribute6(String attribute6){
        this.attribute6 = attribute6;
    }
    public void    setAttribute7(String attribute7){
        this.attribute7 = attribute7;
    }
    public void    setAttribute8(String attribute8){
        this.attribute8 = attribute8;
    }
    public void    setAttribute9(String attribute9){
        this.attribute9 = attribute9;
    }
    public void    setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    public void    setSalesModelFlag(String salesModelFlag){
        this.salesModelFlag = salesModelFlag;
    }
    public String getCpUnitCode(){
        return cpUnitCode;
    }
    public String getMasterId(){
        return masterId;
    }
    public String getLegacyProductModelName(){
        return legacyProductModelName;
    }
    public String getLegacyProductSuffixCode(){
        return legacyProductSuffixCode;
    }
    public String getMarketCode(){
        return marketCode;
    }
    public String getBillToCustomerCode(){
        return billToCustomerCode;
    }
    public String getShipToCustomerCode(){
        return shipToCustomerCode;
    }
    public String getCustomerModelName(){
        return customerModelName;
    }
    public String getBrandTypeName(){
        return brandTypeName;
    }
    public String getSalesModelName(){
        return salesModelName;
    }
    public String getSalesModelSuffixCode(){
        return salesModelSuffixCode;
    }
    public String getTypeCode(){
        return typeCode;
    }
    public String getKanEanUpcCode(){
        return kanEanUpcCode;
    }
    public String getProductCode(){
        return productCode;
    }
    public String getProductLineCode(){
        return productLineCode;
    }
    public String getSetPartNo(){
        return setPartNo;
    }
    public String getFirstSuffixCode(){
        return firstSuffixCode;
    }
    public Date getSuffixConfirmDate(){
        return suffixConfirmDate;
    }
    public String getProductTypeCode(){
        return productTypeCode;
    }
    public Date getDisabledDate(){
        return disabledDate;
    }
    public String getDisabledCode(){
        return disabledCode;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public Date getBomCreationDate(){
        return bomCreationDate;
    }
    public Date getErpInterfaceDate(){
        return erpInterfaceDate;
    }
    public Date getBomProdCreationDate(){
        return bomProdCreationDate;
    }
    public String getErpProductionYyyymmdd(){
        return erpProductionYyyymmdd;
    }
    public String getModelDesc(){
        return modelDesc;
    }
    public String getPreviousModelCode(){
        return previousModelCode;
    }
    public String getPreviousSuffixCode(){
        return previousSuffixCode;
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
    public String getAttribute6(){
        return attribute6;
    }
    public String getAttribute7(){
        return attribute7;
    }
    public String getAttribute8(){
        return attribute8;
    }
    public String getAttribute9(){
        return attribute9;
    }
    public String getAttribute10(){
        return attribute10;
    }
    public String getSalesModelFlag(){
        return salesModelFlag;
    }
}

