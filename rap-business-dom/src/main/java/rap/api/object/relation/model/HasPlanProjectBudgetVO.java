/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanProjectBudgetVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class HasPlanProjectBudgetVO extends BusinessRelationObjectVO {
    private Integer       sequences                                         ;
    private Integer       activityId                                        ;
    private String        contents                                          ;
    private String        calcContents                                      ;
    private String        goodsName                                         ;
    private Integer       qty                                                = 0;
    private String        investPurpose                                     ;
    private String        orderMonth                                        ;
    private String        stockMonth                                        ;
    private String        assetType                                         ;
    private Float         accomRate                                         ;
    private String        introContryName                                   ;
    private String        customerName                                      ;
    private String        transactionCurrencyCode                           ;
    private BigDecimal    transactionAmount                                  = new BigDecimal(0);
    private Float         exchangeRate                                      ;
    private BigDecimal    krwAmount                                          = new BigDecimal(0);
    private String        contractTotCurrencyCode                           ;
    private BigDecimal    contractTotAmount                                  = new BigDecimal(0);
    private BigDecimal    contractTotKrwAmount                               = new BigDecimal(0);
    private String        spec                                              ;
    private String        svcType                                           ;
    private String        svcCategoryName                                   ;
    private String        subjectName                                       ;
    private String        abstractContents                                  ;
    private String        localYn                                           ;
    private String        countryName                                       ;
    private String        institutionCode                                   ;
    private String        institutionName                                   ;
    private String        developFromYyyymmdd                               ;
    private String        developEndYyyymmdd                                ;
    private String        rschRspsrName                                     ;
    private String        classificationName                                ;
    private String        mainUseDescription                                ;
    private String        bizTripPlaceCode                                  ;
    private String        bizTripPurpose                                    ;
    private Integer       bizTripDays                                       ;
    private Integer       bizTripCount                                      ;
    private Integer       bizTripEmployeeCount                              ;
    private Integer       bizTripExecutiveCount                             ;
    private String        bizTripMonth                                      ;
    private BigDecimal    roundTransExpense                                  = new BigDecimal(0);
    private BigDecimal    lodgingExpense                                     = new BigDecimal(0);
    private BigDecimal    activityExpense                                    = new BigDecimal(0);
    private BigDecimal    etcCost                                            = new BigDecimal(0);
    private String        eduType                                           ;
    private String        eduInstName                                       ;
    private String        eduFromYyyymmdd                                   ;
    private String        eduEndYyyymmdd                                    ;
    private String        donorName                                         ;
    private Float         currencyExchangeRate                              ;
    private String        assetTypeUpFg                                     ;
    private String        investObuCode                                     ;
    private String        investSectorCode                                  ;
    private String        investObjectCode                                  ;
    private String        investSelfObjectCode                              ;
    private String        investProjectCode                                 ;
    private String        itmsProjectCode                                   ;
    private String        investMajorCode                                   ;
    private String        investMinorCode                                   ;
    private String        investType                                        ;
    private String        investAllocation                                  ;
    private String        preCarryForwardFlag                               ;
    private String        preInvestProjectCode                              ;
    private BigDecimal    finalCurrencyAmount                                = new BigDecimal(0);
    private String        assetMajorCode                                    ;
    private String        assetMinorCode                                    ;
    private String        planYear                                          ;
    private BigDecimal    currencyAmount                                     = new BigDecimal(0);
    private String        currencyCode                                      ;
    private BigDecimal    month01Amount                                      = new BigDecimal(0);
    private BigDecimal    month02Amount                                      = new BigDecimal(0);
    private BigDecimal    month03Amount                                      = new BigDecimal(0);
    private BigDecimal    month04Amount                                      = new BigDecimal(0);
    private BigDecimal    month05Amount                                      = new BigDecimal(0);
    private BigDecimal    month06Amount                                      = new BigDecimal(0);
    private BigDecimal    month07Amount                                      = new BigDecimal(0);
    private BigDecimal    month08Amount                                      = new BigDecimal(0);
    private BigDecimal    month09Amount                                      = new BigDecimal(0);
    private BigDecimal    month10Amount                                      = new BigDecimal(0);
    private BigDecimal    month11Amount                                      = new BigDecimal(0);
    private BigDecimal    month12Amount                                      = new BigDecimal(0);
    private BigDecimal    month01StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month02StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month03StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month04StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month05StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month06StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month07StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month08StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month09StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month10StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month11StockAmount                                 = new BigDecimal(0);
    private BigDecimal    month12StockAmount                                 = new BigDecimal(0);
    private String        eventCode                                         ;
    private String        accountCode                                       ;


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setActivityId(Integer activityId){
        this.activityId = activityId;
    }
    public void    setContents(String contents){
        this.contents = contents;
    }
    public void    setCalcContents(String calcContents){
        this.calcContents = calcContents;
    }
    public void    setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }
    public void    setQty(Integer qty){
        this.qty = qty;
    }
    public void    setInvestPurpose(String investPurpose){
        this.investPurpose = investPurpose;
    }
    public void    setOrderMonth(String orderMonth){
        this.orderMonth = orderMonth;
    }
    public void    setStockMonth(String stockMonth){
        this.stockMonth = stockMonth;
    }
    public void    setAssetType(String assetType){
        this.assetType = assetType;
    }
    public void    setAccomRate(Float accomRate){
        this.accomRate = accomRate;
    }
    public void    setIntroContryName(String introContryName){
        this.introContryName = introContryName;
    }
    public void    setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public void    setTransactionCurrencyCode(String transactionCurrencyCode){
        this.transactionCurrencyCode = transactionCurrencyCode;
    }
    public void    setTransactionAmount(BigDecimal transactionAmount){
        this.transactionAmount = transactionAmount;
    }
    public void    setExchangeRate(Float exchangeRate){
        this.exchangeRate = exchangeRate;
    }
    public void    setKrwAmount(BigDecimal krwAmount){
        this.krwAmount = krwAmount;
    }
    public void    setContractTotCurrencyCode(String contractTotCurrencyCode){
        this.contractTotCurrencyCode = contractTotCurrencyCode;
    }
    public void    setContractTotAmount(BigDecimal contractTotAmount){
        this.contractTotAmount = contractTotAmount;
    }
    public void    setContractTotKrwAmount(BigDecimal contractTotKrwAmount){
        this.contractTotKrwAmount = contractTotKrwAmount;
    }
    public void    setSpec(String spec){
        this.spec = spec;
    }
    public void    setSvcType(String svcType){
        this.svcType = svcType;
    }
    public void    setSvcCategoryName(String svcCategoryName){
        this.svcCategoryName = svcCategoryName;
    }
    public void    setSubjectName(String subjectName){
        this.subjectName = subjectName;
    }
    public void    setAbstractContents(String abstractContents){
        this.abstractContents = abstractContents;
    }
    public void    setLocalYn(String localYn){
        this.localYn = localYn;
    }
    public void    setCountryName(String countryName){
        this.countryName = countryName;
    }
    public void    setInstitutionCode(String institutionCode){
        this.institutionCode = institutionCode;
    }
    public void    setInstitutionName(String institutionName){
        this.institutionName = institutionName;
    }
    public void    setDevelopFromYyyymmdd(String developFromYyyymmdd){
        this.developFromYyyymmdd = developFromYyyymmdd;
    }
    public void    setDevelopEndYyyymmdd(String developEndYyyymmdd){
        this.developEndYyyymmdd = developEndYyyymmdd;
    }
    public void    setRschRspsrName(String rschRspsrName){
        this.rschRspsrName = rschRspsrName;
    }
    public void    setClassificationName(String classificationName){
        this.classificationName = classificationName;
    }
    public void    setMainUseDescription(String mainUseDescription){
        this.mainUseDescription = mainUseDescription;
    }
    public void    setBizTripPlaceCode(String bizTripPlaceCode){
        this.bizTripPlaceCode = bizTripPlaceCode;
    }
    public void    setBizTripPurpose(String bizTripPurpose){
        this.bizTripPurpose = bizTripPurpose;
    }
    public void    setBizTripDays(Integer bizTripDays){
        this.bizTripDays = bizTripDays;
    }
    public void    setBizTripCount(Integer bizTripCount){
        this.bizTripCount = bizTripCount;
    }
    public void    setBizTripEmployeeCount(Integer bizTripEmployeeCount){
        this.bizTripEmployeeCount = bizTripEmployeeCount;
    }
    public void    setBizTripExecutiveCount(Integer bizTripExecutiveCount){
        this.bizTripExecutiveCount = bizTripExecutiveCount;
    }
    public void    setBizTripMonth(String bizTripMonth){
        this.bizTripMonth = bizTripMonth;
    }
    public void    setRoundTransExpense(BigDecimal roundTransExpense){
        this.roundTransExpense = roundTransExpense;
    }
    public void    setLodgingExpense(BigDecimal lodgingExpense){
        this.lodgingExpense = lodgingExpense;
    }
    public void    setActivityExpense(BigDecimal activityExpense){
        this.activityExpense = activityExpense;
    }
    public void    setEtcCost(BigDecimal etcCost){
        this.etcCost = etcCost;
    }
    public void    setEduType(String eduType){
        this.eduType = eduType;
    }
    public void    setEduInstName(String eduInstName){
        this.eduInstName = eduInstName;
    }
    public void    setEduFromYyyymmdd(String eduFromYyyymmdd){
        this.eduFromYyyymmdd = eduFromYyyymmdd;
    }
    public void    setEduEndYyyymmdd(String eduEndYyyymmdd){
        this.eduEndYyyymmdd = eduEndYyyymmdd;
    }
    public void    setDonorName(String donorName){
        this.donorName = donorName;
    }
    public void    setCurrencyExchangeRate(Float currencyExchangeRate){
        this.currencyExchangeRate = currencyExchangeRate;
    }
    public void    setAssetTypeUpFg(String assetTypeUpFg){
        this.assetTypeUpFg = assetTypeUpFg;
    }
    public void    setInvestObuCode(String investObuCode){
        this.investObuCode = investObuCode;
    }
    public void    setInvestSectorCode(String investSectorCode){
        this.investSectorCode = investSectorCode;
    }
    public void    setInvestObjectCode(String investObjectCode){
        this.investObjectCode = investObjectCode;
    }
    public void    setInvestSelfObjectCode(String investSelfObjectCode){
        this.investSelfObjectCode = investSelfObjectCode;
    }
    public void    setInvestProjectCode(String investProjectCode){
        this.investProjectCode = investProjectCode;
    }
    public void    setItmsProjectCode(String itmsProjectCode){
        this.itmsProjectCode = itmsProjectCode;
    }
    public void    setInvestMajorCode(String investMajorCode){
        this.investMajorCode = investMajorCode;
    }
    public void    setInvestMinorCode(String investMinorCode){
        this.investMinorCode = investMinorCode;
    }
    public void    setInvestType(String investType){
        this.investType = investType;
    }
    public void    setInvestAllocation(String investAllocation){
        this.investAllocation = investAllocation;
    }
    public void    setPreCarryForwardFlag(String preCarryForwardFlag){
        this.preCarryForwardFlag = preCarryForwardFlag;
    }
    public void    setPreInvestProjectCode(String preInvestProjectCode){
        this.preInvestProjectCode = preInvestProjectCode;
    }
    public void    setFinalCurrencyAmount(BigDecimal finalCurrencyAmount){
        this.finalCurrencyAmount = finalCurrencyAmount;
    }
    public void    setAssetMajorCode(String assetMajorCode){
        this.assetMajorCode = assetMajorCode;
    }
    public void    setAssetMinorCode(String assetMinorCode){
        this.assetMinorCode = assetMinorCode;
    }
    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setCurrencyAmount(BigDecimal currencyAmount){
        this.currencyAmount = currencyAmount;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setMonth01Amount(BigDecimal month01Amount){
        this.month01Amount = month01Amount;
    }
    public void    setMonth02Amount(BigDecimal month02Amount){
        this.month02Amount = month02Amount;
    }
    public void    setMonth03Amount(BigDecimal month03Amount){
        this.month03Amount = month03Amount;
    }
    public void    setMonth04Amount(BigDecimal month04Amount){
        this.month04Amount = month04Amount;
    }
    public void    setMonth05Amount(BigDecimal month05Amount){
        this.month05Amount = month05Amount;
    }
    public void    setMonth06Amount(BigDecimal month06Amount){
        this.month06Amount = month06Amount;
    }
    public void    setMonth07Amount(BigDecimal month07Amount){
        this.month07Amount = month07Amount;
    }
    public void    setMonth08Amount(BigDecimal month08Amount){
        this.month08Amount = month08Amount;
    }
    public void    setMonth09Amount(BigDecimal month09Amount){
        this.month09Amount = month09Amount;
    }
    public void    setMonth10Amount(BigDecimal month10Amount){
        this.month10Amount = month10Amount;
    }
    public void    setMonth11Amount(BigDecimal month11Amount){
        this.month11Amount = month11Amount;
    }
    public void    setMonth12Amount(BigDecimal month12Amount){
        this.month12Amount = month12Amount;
    }
    public void    setMonth01StockAmount(BigDecimal month01StockAmount){
        this.month01StockAmount = month01StockAmount;
    }
    public void    setMonth02StockAmount(BigDecimal month02StockAmount){
        this.month02StockAmount = month02StockAmount;
    }
    public void    setMonth03StockAmount(BigDecimal month03StockAmount){
        this.month03StockAmount = month03StockAmount;
    }
    public void    setMonth04StockAmount(BigDecimal month04StockAmount){
        this.month04StockAmount = month04StockAmount;
    }
    public void    setMonth05StockAmount(BigDecimal month05StockAmount){
        this.month05StockAmount = month05StockAmount;
    }
    public void    setMonth06StockAmount(BigDecimal month06StockAmount){
        this.month06StockAmount = month06StockAmount;
    }
    public void    setMonth07StockAmount(BigDecimal month07StockAmount){
        this.month07StockAmount = month07StockAmount;
    }
    public void    setMonth08StockAmount(BigDecimal month08StockAmount){
        this.month08StockAmount = month08StockAmount;
    }
    public void    setMonth09StockAmount(BigDecimal month09StockAmount){
        this.month09StockAmount = month09StockAmount;
    }
    public void    setMonth10StockAmount(BigDecimal month10StockAmount){
        this.month10StockAmount = month10StockAmount;
    }
    public void    setMonth11StockAmount(BigDecimal month11StockAmount){
        this.month11StockAmount = month11StockAmount;
    }
    public void    setMonth12StockAmount(BigDecimal month12StockAmount){
        this.month12StockAmount = month12StockAmount;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public Integer getSequences(){
        return sequences;
    }
    public Integer getActivityId(){
        return activityId;
    }
    public String getContents(){
        return contents;
    }
    public String getCalcContents(){
        return calcContents;
    }
    public String getGoodsName(){
        return goodsName;
    }
    public Integer getQty(){
        return qty;
    }
    public String getInvestPurpose(){
        return investPurpose;
    }
    public String getOrderMonth(){
        return orderMonth;
    }
    public String getStockMonth(){
        return stockMonth;
    }
    public String getAssetType(){
        return assetType;
    }
    public Float getAccomRate(){
        return accomRate;
    }
    public String getIntroContryName(){
        return introContryName;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getTransactionCurrencyCode(){
        return transactionCurrencyCode;
    }
    public BigDecimal getTransactionAmount(){
        return transactionAmount;
    }
    public Float getExchangeRate(){
        return exchangeRate;
    }
    public BigDecimal getKrwAmount(){
        return krwAmount;
    }
    public String getContractTotCurrencyCode(){
        return contractTotCurrencyCode;
    }
    public BigDecimal getContractTotAmount(){
        return contractTotAmount;
    }
    public BigDecimal getContractTotKrwAmount(){
        return contractTotKrwAmount;
    }
    public String getSpec(){
        return spec;
    }
    public String getSvcType(){
        return svcType;
    }
    public String getSvcCategoryName(){
        return svcCategoryName;
    }
    public String getSubjectName(){
        return subjectName;
    }
    public String getAbstractContents(){
        return abstractContents;
    }
    public String getLocalYn(){
        return localYn;
    }
    public String getCountryName(){
        return countryName;
    }
    public String getInstitutionCode(){
        return institutionCode;
    }
    public String getInstitutionName(){
        return institutionName;
    }
    public String getDevelopFromYyyymmdd(){
        return developFromYyyymmdd;
    }
    public String getDevelopEndYyyymmdd(){
        return developEndYyyymmdd;
    }
    public String getRschRspsrName(){
        return rschRspsrName;
    }
    public String getClassificationName(){
        return classificationName;
    }
    public String getMainUseDescription(){
        return mainUseDescription;
    }
    public String getBizTripPlaceCode(){
        return bizTripPlaceCode;
    }
    public String getBizTripPurpose(){
        return bizTripPurpose;
    }
    public Integer getBizTripDays(){
        return bizTripDays;
    }
    public Integer getBizTripCount(){
        return bizTripCount;
    }
    public Integer getBizTripEmployeeCount(){
        return bizTripEmployeeCount;
    }
    public Integer getBizTripExecutiveCount(){
        return bizTripExecutiveCount;
    }
    public String getBizTripMonth(){
        return bizTripMonth;
    }
    public BigDecimal getRoundTransExpense(){
        return roundTransExpense;
    }
    public BigDecimal getLodgingExpense(){
        return lodgingExpense;
    }
    public BigDecimal getActivityExpense(){
        return activityExpense;
    }
    public BigDecimal getEtcCost(){
        return etcCost;
    }
    public String getEduType(){
        return eduType;
    }
    public String getEduInstName(){
        return eduInstName;
    }
    public String getEduFromYyyymmdd(){
        return eduFromYyyymmdd;
    }
    public String getEduEndYyyymmdd(){
        return eduEndYyyymmdd;
    }
    public String getDonorName(){
        return donorName;
    }
    public Float getCurrencyExchangeRate(){
        return currencyExchangeRate;
    }
    public String getAssetTypeUpFg(){
        return assetTypeUpFg;
    }
    public String getInvestObuCode(){
        return investObuCode;
    }
    public String getInvestSectorCode(){
        return investSectorCode;
    }
    public String getInvestObjectCode(){
        return investObjectCode;
    }
    public String getInvestSelfObjectCode(){
        return investSelfObjectCode;
    }
    public String getInvestProjectCode(){
        return investProjectCode;
    }
    public String getItmsProjectCode(){
        return itmsProjectCode;
    }
    public String getInvestMajorCode(){
        return investMajorCode;
    }
    public String getInvestMinorCode(){
        return investMinorCode;
    }
    public String getInvestType(){
        return investType;
    }
    public String getInvestAllocation(){
        return investAllocation;
    }
    public String getPreCarryForwardFlag(){
        return preCarryForwardFlag;
    }
    public String getPreInvestProjectCode(){
        return preInvestProjectCode;
    }
    public BigDecimal getFinalCurrencyAmount(){
        return finalCurrencyAmount;
    }
    public String getAssetMajorCode(){
        return assetMajorCode;
    }
    public String getAssetMinorCode(){
        return assetMinorCode;
    }
    public String getPlanYear(){
        return planYear;
    }
    public BigDecimal getCurrencyAmount(){
        return currencyAmount;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public BigDecimal getMonth01Amount(){
        return month01Amount;
    }
    public BigDecimal getMonth02Amount(){
        return month02Amount;
    }
    public BigDecimal getMonth03Amount(){
        return month03Amount;
    }
    public BigDecimal getMonth04Amount(){
        return month04Amount;
    }
    public BigDecimal getMonth05Amount(){
        return month05Amount;
    }
    public BigDecimal getMonth06Amount(){
        return month06Amount;
    }
    public BigDecimal getMonth07Amount(){
        return month07Amount;
    }
    public BigDecimal getMonth08Amount(){
        return month08Amount;
    }
    public BigDecimal getMonth09Amount(){
        return month09Amount;
    }
    public BigDecimal getMonth10Amount(){
        return month10Amount;
    }
    public BigDecimal getMonth11Amount(){
        return month11Amount;
    }
    public BigDecimal getMonth12Amount(){
        return month12Amount;
    }
    public BigDecimal getMonth01StockAmount(){
        return month01StockAmount;
    }
    public BigDecimal getMonth02StockAmount(){
        return month02StockAmount;
    }
    public BigDecimal getMonth03StockAmount(){
        return month03StockAmount;
    }
    public BigDecimal getMonth04StockAmount(){
        return month04StockAmount;
    }
    public BigDecimal getMonth05StockAmount(){
        return month05StockAmount;
    }
    public BigDecimal getMonth06StockAmount(){
        return month06StockAmount;
    }
    public BigDecimal getMonth07StockAmount(){
        return month07StockAmount;
    }
    public BigDecimal getMonth08StockAmount(){
        return month08StockAmount;
    }
    public BigDecimal getMonth09StockAmount(){
        return month09StockAmount;
    }
    public BigDecimal getMonth10StockAmount(){
        return month10StockAmount;
    }
    public BigDecimal getMonth11StockAmount(){
        return month11StockAmount;
    }
    public BigDecimal getMonth12StockAmount(){
        return month12StockAmount;
    }
    public String getEventCode(){
        return eventCode;
    }
    public String getAccountCode(){
        return accountCode;
    }
}

