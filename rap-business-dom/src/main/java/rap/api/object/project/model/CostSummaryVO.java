/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CostSummaryVO.java
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
public class CostSummaryVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        eventCode                                         ;
    private Float         salesProfitPct                                    ;
    private Float         salesProfitAchpct                                 ;
    private Float         targetCostPct                                     ;
    private Float         targetCostAchpct                                  ;
    private String        modelCode                                         ;
    private String        mainYn                                             = "N";
    private Integer       quantity                                          ;
    private BigDecimal    fob                                                = new BigDecimal(0);
    private String        modelName                                         ;
    private Float         marginalProfitPct                                 ;
    private Float         exchangeRate                                      ;
    private Date          analyzeDate                                       ;
    private String        baseModel                                         ;
    private BigDecimal    salesPrice                                         = new BigDecimal(0);
    private String        fobUs                                             ;
    private BigDecimal    fobKrw                                             = new BigDecimal(0);
    private String        fobKrwCc                                          ;
    private String        attribute6                                        ;
    private String        attribute1                                        ;
    private String        divCd                                             ;
    private String        attribute2                                        ;
    private String        attribute7                                        ;
    private String        divName                                           ;
    private String        attribute3                                        ;
    private String        attribute8                                        ;
    private String        prodLvl4Cd                                        ;
    private String        prodLvl4Name                                      ;
    private String        attribute4                                        ;
    private String        attribute9                                        ;
    private String        attribute10                                       ;
    private BigDecimal    toUsdGrossSalesPrice                               = new BigDecimal(0);
    private String        attribute5                                        ;
    private BigDecimal    grossSalesPrice                                    = new BigDecimal(0);
    private BigDecimal    cogsRate                                           = new BigDecimal(0);
    private BigDecimal    convCostRate                                       = new BigDecimal(0);
    private BigDecimal    sgnaRate                                           = new BigDecimal(0);
    private Date          resultStartDate                                   ;
    private Date          resultEndDate                                     ;
    private String        tgInitCurrencyCd                                  ;
    private BigDecimal    tgInitSalesQty                                     = new BigDecimal(0);
    private BigDecimal    tgInitGrossSalesPrice                              = new BigDecimal(0);
    private BigDecimal    tgInitNsalesPrice                                  = new BigDecimal(0);
    private BigDecimal    tgInitCogs                                         = new BigDecimal(0);
    private BigDecimal    tgInitMtlCost                                      = new BigDecimal(0);
    private BigDecimal    tgInitConvCost                                     = new BigDecimal(0);
    private BigDecimal    tgInitSgna                                         = new BigDecimal(0);
    private BigDecimal    tgInitOperatingIncome                              = new BigDecimal(0);
    private Float         tgInitOperatingIncomeRate                         ;
    private String        tgFinalCurrencyCd                                 ;
    private BigDecimal    tgFinalSalesQty                                    = new BigDecimal(0);
    private BigDecimal    tgFinalGrossSalesPrice                             = new BigDecimal(0);
    private BigDecimal    tgFinalNsalesPrice                                 = new BigDecimal(0);
    private BigDecimal    tgFinalCogs                                        = new BigDecimal(0);
    private BigDecimal    tgFinalMtlCost                                     = new BigDecimal(0);
    private BigDecimal    tgFinalConvCost                                    = new BigDecimal(0);
    private BigDecimal    tgFinalSgna                                        = new BigDecimal(0);
    private BigDecimal    tgFinalOperatingIncome                             = new BigDecimal(0);
    private Float         tgFinalOperatingRate                              ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setSalesProfitPct(Float salesProfitPct){
        this.salesProfitPct = salesProfitPct;
    }
    public void    setSalesProfitAchpct(Float salesProfitAchpct){
        this.salesProfitAchpct = salesProfitAchpct;
    }
    public void    setTargetCostPct(Float targetCostPct){
        this.targetCostPct = targetCostPct;
    }
    public void    setTargetCostAchpct(Float targetCostAchpct){
        this.targetCostAchpct = targetCostAchpct;
    }
    public void    setModelCode(String modelCode){
        this.modelCode = modelCode;
    }
    public void    setMainYn(String mainYn){
        this.mainYn = mainYn;
    }
    public void    setQuantity(Integer quantity){
        this.quantity = quantity;
    }
    public void    setFob(BigDecimal fob){
        this.fob = fob;
    }
    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setMarginalProfitPct(Float marginalProfitPct){
        this.marginalProfitPct = marginalProfitPct;
    }
    public void    setExchangeRate(Float exchangeRate){
        this.exchangeRate = exchangeRate;
    }
    public void    setAnalyzeDate(Date analyzeDate){
        this.analyzeDate = analyzeDate;
    }
    public void    setAnalyzeDate(String    analyzeDate){
        this.analyzeDate = this.omcConvertStr2Date(analyzeDate);
    }
    public void    setBaseModel(String baseModel){
        this.baseModel = baseModel;
    }
    public void    setSalesPrice(BigDecimal salesPrice){
        this.salesPrice = salesPrice;
    }
    public void    setFobUs(String fobUs){
        this.fobUs = fobUs;
    }
    public void    setFobKrw(BigDecimal fobKrw){
        this.fobKrw = fobKrw;
    }
    public void    setFobKrwCc(String fobKrwCc){
        this.fobKrwCc = fobKrwCc;
    }
    public void    setAttribute6(String attribute6){
        this.attribute6 = attribute6;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public void    setDivCd(String divCd){
        this.divCd = divCd;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public void    setAttribute7(String attribute7){
        this.attribute7 = attribute7;
    }
    public void    setDivName(String divName){
        this.divName = divName;
    }
    public void    setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
    public void    setAttribute8(String attribute8){
        this.attribute8 = attribute8;
    }
    public void    setProdLvl4Cd(String prodLvl4Cd){
        this.prodLvl4Cd = prodLvl4Cd;
    }
    public void    setProdLvl4Name(String prodLvl4Name){
        this.prodLvl4Name = prodLvl4Name;
    }
    public void    setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
    public void    setAttribute9(String attribute9){
        this.attribute9 = attribute9;
    }
    public void    setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    public void    setToUsdGrossSalesPrice(BigDecimal toUsdGrossSalesPrice){
        this.toUsdGrossSalesPrice = toUsdGrossSalesPrice;
    }
    public void    setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }
    public void    setGrossSalesPrice(BigDecimal grossSalesPrice){
        this.grossSalesPrice = grossSalesPrice;
    }
    public void    setCogsRate(BigDecimal cogsRate){
        this.cogsRate = cogsRate;
    }
    public void    setConvCostRate(BigDecimal convCostRate){
        this.convCostRate = convCostRate;
    }
    public void    setSgnaRate(BigDecimal sgnaRate){
        this.sgnaRate = sgnaRate;
    }
    public void    setResultStartDate(Date resultStartDate){
        this.resultStartDate = resultStartDate;
    }
    public void    setResultStartDate(String    resultStartDate){
        this.resultStartDate = this.omcConvertStr2Date(resultStartDate);
    }
    public void    setResultEndDate(Date resultEndDate){
        this.resultEndDate = resultEndDate;
    }
    public void    setResultEndDate(String    resultEndDate){
        this.resultEndDate = this.omcConvertStr2Date(resultEndDate);
    }
    public void    setTgInitCurrencyCd(String tgInitCurrencyCd){
        this.tgInitCurrencyCd = tgInitCurrencyCd;
    }
    public void    setTgInitSalesQty(BigDecimal tgInitSalesQty){
        this.tgInitSalesQty = tgInitSalesQty;
    }
    public void    setTgInitGrossSalesPrice(BigDecimal tgInitGrossSalesPrice){
        this.tgInitGrossSalesPrice = tgInitGrossSalesPrice;
    }
    public void    setTgInitNsalesPrice(BigDecimal tgInitNsalesPrice){
        this.tgInitNsalesPrice = tgInitNsalesPrice;
    }
    public void    setTgInitCogs(BigDecimal tgInitCogs){
        this.tgInitCogs = tgInitCogs;
    }
    public void    setTgInitMtlCost(BigDecimal tgInitMtlCost){
        this.tgInitMtlCost = tgInitMtlCost;
    }
    public void    setTgInitConvCost(BigDecimal tgInitConvCost){
        this.tgInitConvCost = tgInitConvCost;
    }
    public void    setTgInitSgna(BigDecimal tgInitSgna){
        this.tgInitSgna = tgInitSgna;
    }
    public void    setTgInitOperatingIncome(BigDecimal tgInitOperatingIncome){
        this.tgInitOperatingIncome = tgInitOperatingIncome;
    }
    public void    setTgInitOperatingIncomeRate(Float tgInitOperatingIncomeRate){
        this.tgInitOperatingIncomeRate = tgInitOperatingIncomeRate;
    }
    public void    setTgFinalCurrencyCd(String tgFinalCurrencyCd){
        this.tgFinalCurrencyCd = tgFinalCurrencyCd;
    }
    public void    setTgFinalSalesQty(BigDecimal tgFinalSalesQty){
        this.tgFinalSalesQty = tgFinalSalesQty;
    }
    public void    setTgFinalGrossSalesPrice(BigDecimal tgFinalGrossSalesPrice){
        this.tgFinalGrossSalesPrice = tgFinalGrossSalesPrice;
    }
    public void    setTgFinalNsalesPrice(BigDecimal tgFinalNsalesPrice){
        this.tgFinalNsalesPrice = tgFinalNsalesPrice;
    }
    public void    setTgFinalCogs(BigDecimal tgFinalCogs){
        this.tgFinalCogs = tgFinalCogs;
    }
    public void    setTgFinalMtlCost(BigDecimal tgFinalMtlCost){
        this.tgFinalMtlCost = tgFinalMtlCost;
    }
    public void    setTgFinalConvCost(BigDecimal tgFinalConvCost){
        this.tgFinalConvCost = tgFinalConvCost;
    }
    public void    setTgFinalSgna(BigDecimal tgFinalSgna){
        this.tgFinalSgna = tgFinalSgna;
    }
    public void    setTgFinalOperatingIncome(BigDecimal tgFinalOperatingIncome){
        this.tgFinalOperatingIncome = tgFinalOperatingIncome;
    }
    public void    setTgFinalOperatingRate(Float tgFinalOperatingRate){
        this.tgFinalOperatingRate = tgFinalOperatingRate;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getEventCode(){
        return eventCode;
    }
    public Float getSalesProfitPct(){
        return salesProfitPct;
    }
    public Float getSalesProfitAchpct(){
        return salesProfitAchpct;
    }
    public Float getTargetCostPct(){
        return targetCostPct;
    }
    public Float getTargetCostAchpct(){
        return targetCostAchpct;
    }
    public String getModelCode(){
        return modelCode;
    }
    public String getMainYn(){
        return mainYn;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public BigDecimal getFob(){
        return fob;
    }
    public String getModelName(){
        return modelName;
    }
    public Float getMarginalProfitPct(){
        return marginalProfitPct;
    }
    public Float getExchangeRate(){
        return exchangeRate;
    }
    public Date getAnalyzeDate(){
        return analyzeDate;
    }
    public String getBaseModel(){
        return baseModel;
    }
    public BigDecimal getSalesPrice(){
        return salesPrice;
    }
    public String getFobUs(){
        return fobUs;
    }
    public BigDecimal getFobKrw(){
        return fobKrw;
    }
    public String getFobKrwCc(){
        return fobKrwCc;
    }
    public String getAttribute6(){
        return attribute6;
    }
    public String getAttribute1(){
        return attribute1;
    }
    public String getDivCd(){
        return divCd;
    }
    public String getAttribute2(){
        return attribute2;
    }
    public String getAttribute7(){
        return attribute7;
    }
    public String getDivName(){
        return divName;
    }
    public String getAttribute3(){
        return attribute3;
    }
    public String getAttribute8(){
        return attribute8;
    }
    public String getProdLvl4Cd(){
        return prodLvl4Cd;
    }
    public String getProdLvl4Name(){
        return prodLvl4Name;
    }
    public String getAttribute4(){
        return attribute4;
    }
    public String getAttribute9(){
        return attribute9;
    }
    public String getAttribute10(){
        return attribute10;
    }
    public BigDecimal getToUsdGrossSalesPrice(){
        return toUsdGrossSalesPrice;
    }
    public String getAttribute5(){
        return attribute5;
    }
    public BigDecimal getGrossSalesPrice(){
        return grossSalesPrice;
    }
    public BigDecimal getCogsRate(){
        return cogsRate;
    }
    public BigDecimal getConvCostRate(){
        return convCostRate;
    }
    public BigDecimal getSgnaRate(){
        return sgnaRate;
    }
    public Date getResultStartDate(){
        return resultStartDate;
    }
    public Date getResultEndDate(){
        return resultEndDate;
    }
    public String getTgInitCurrencyCd(){
        return tgInitCurrencyCd;
    }
    public BigDecimal getTgInitSalesQty(){
        return tgInitSalesQty;
    }
    public BigDecimal getTgInitGrossSalesPrice(){
        return tgInitGrossSalesPrice;
    }
    public BigDecimal getTgInitNsalesPrice(){
        return tgInitNsalesPrice;
    }
    public BigDecimal getTgInitCogs(){
        return tgInitCogs;
    }
    public BigDecimal getTgInitMtlCost(){
        return tgInitMtlCost;
    }
    public BigDecimal getTgInitConvCost(){
        return tgInitConvCost;
    }
    public BigDecimal getTgInitSgna(){
        return tgInitSgna;
    }
    public BigDecimal getTgInitOperatingIncome(){
        return tgInitOperatingIncome;
    }
    public Float getTgInitOperatingIncomeRate(){
        return tgInitOperatingIncomeRate;
    }
    public String getTgFinalCurrencyCd(){
        return tgFinalCurrencyCd;
    }
    public BigDecimal getTgFinalSalesQty(){
        return tgFinalSalesQty;
    }
    public BigDecimal getTgFinalGrossSalesPrice(){
        return tgFinalGrossSalesPrice;
    }
    public BigDecimal getTgFinalNsalesPrice(){
        return tgFinalNsalesPrice;
    }
    public BigDecimal getTgFinalCogs(){
        return tgFinalCogs;
    }
    public BigDecimal getTgFinalMtlCost(){
        return tgFinalMtlCost;
    }
    public BigDecimal getTgFinalConvCost(){
        return tgFinalConvCost;
    }
    public BigDecimal getTgFinalSgna(){
        return tgFinalSgna;
    }
    public BigDecimal getTgFinalOperatingIncome(){
        return tgFinalOperatingIncome;
    }
    public Float getTgFinalOperatingRate(){
        return tgFinalOperatingRate;
    }
}

