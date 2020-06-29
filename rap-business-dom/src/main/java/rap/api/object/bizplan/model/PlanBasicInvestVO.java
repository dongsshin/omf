/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PlanBasicInvestVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PlanBasicInvestVO extends BusinessObjectMasterVO {
    private Integer       ifSeq                                             ;
    private Integer       seqNo                                             ;
    private String        lineTypeCode                                      ;
    private String        versionObid                                       ;
    private String        fromObid                                          ;
    private String        planProjectCode                                   ;
    private String        projectCode                                       ;
    private String        projectName                                       ;
    private Integer       sequences                                         ;
    private String        planYear                                          ;
    private String        accountingUnitCode                                ;
    private String        investSectorCode                                  ;
    private String        investObjectCode                                  ;
    private String        investSelfObjectCode                              ;
    private String        investProjectCode                                 ;
    private String        goodsName                                         ;
    private String        assetType                                         ;
    private String        payDepartmentCode                                 ;
    private String        setupDepartmentCode                               ;
    private String        marketTypeCode                                    ;
    private String        orderMonth                                        ;
    private String        stockMonth                                        ;
    private String        investPurpose                                     ;
    private String        calcContents                                      ;
    private String        currencyCode                                      ;
    private BigDecimal    currencyAmount                                     = new BigDecimal(0);
    private String        transactionCurrencyCode                           ;
    private BigDecimal    transactionAmount                                  = new BigDecimal(0);
    private Float         accomRate                                         ;
    private String        contents                                          ;
    private String        investMajorCode                                   ;
    private String        investMinorCode                                   ;
    private String        investType                                        ;
    private String        investAllocation                                  ;
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
    private String        assetMajorCode                                    ;
    private String        assetMinorCode                                    ;
    private String        departmentCode                                    ;
    private String        plmDepartmentCode                                 ;
    private String        itDepartmentCode                                  ;
    private String        itmsProjectCode                                   ;
    private String        assetItFlag                                       ;
    private String        accountCode                                       ;
    private String        preCarryForwardFlag                               ;
    private String        preInvestProjectCode                              ;
    private Integer       transId                                           ;
    private String        registAccountingUnitCode                          ;
    private String        registDepartmentCode                              ;
    private String        registEmployeeNo                                  ;
    private Date          registDate                                        ;
    private String        ifYn                                              ;
    private String        errorYn                                           ;
    private String        errorMsg                                          ;
    private Integer       gbmsVersionNo                                     ;
    private Integer       ifVersionNo                                       ;
    private String        supervisionDepartmentCode                         ;


    public void    setIfSeq(Integer ifSeq){
        this.ifSeq = ifSeq;
    }
    public void    setSeqNo(Integer seqNo){
        this.seqNo = seqNo;
    }
    public void    setLineTypeCode(String lineTypeCode){
        this.lineTypeCode = lineTypeCode;
    }
    public void    setVersionObid(String versionObid){
        this.versionObid = versionObid;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setPlanProjectCode(String planProjectCode){
        this.planProjectCode = planProjectCode;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setProjectName(String projectName){
        this.projectName = projectName;
    }
    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
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
    public void    setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }
    public void    setAssetType(String assetType){
        this.assetType = assetType;
    }
    public void    setPayDepartmentCode(String payDepartmentCode){
        this.payDepartmentCode = payDepartmentCode;
    }
    public void    setSetupDepartmentCode(String setupDepartmentCode){
        this.setupDepartmentCode = setupDepartmentCode;
    }
    public void    setMarketTypeCode(String marketTypeCode){
        this.marketTypeCode = marketTypeCode;
    }
    public void    setOrderMonth(String orderMonth){
        this.orderMonth = orderMonth;
    }
    public void    setStockMonth(String stockMonth){
        this.stockMonth = stockMonth;
    }
    public void    setInvestPurpose(String investPurpose){
        this.investPurpose = investPurpose;
    }
    public void    setCalcContents(String calcContents){
        this.calcContents = calcContents;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setCurrencyAmount(BigDecimal currencyAmount){
        this.currencyAmount = currencyAmount;
    }
    public void    setTransactionCurrencyCode(String transactionCurrencyCode){
        this.transactionCurrencyCode = transactionCurrencyCode;
    }
    public void    setTransactionAmount(BigDecimal transactionAmount){
        this.transactionAmount = transactionAmount;
    }
    public void    setAccomRate(Float accomRate){
        this.accomRate = accomRate;
    }
    public void    setContents(String contents){
        this.contents = contents;
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
    public void    setAssetMajorCode(String assetMajorCode){
        this.assetMajorCode = assetMajorCode;
    }
    public void    setAssetMinorCode(String assetMinorCode){
        this.assetMinorCode = assetMinorCode;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setPlmDepartmentCode(String plmDepartmentCode){
        this.plmDepartmentCode = plmDepartmentCode;
    }
    public void    setItDepartmentCode(String itDepartmentCode){
        this.itDepartmentCode = itDepartmentCode;
    }
    public void    setItmsProjectCode(String itmsProjectCode){
        this.itmsProjectCode = itmsProjectCode;
    }
    public void    setAssetItFlag(String assetItFlag){
        this.assetItFlag = assetItFlag;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setPreCarryForwardFlag(String preCarryForwardFlag){
        this.preCarryForwardFlag = preCarryForwardFlag;
    }
    public void    setPreInvestProjectCode(String preInvestProjectCode){
        this.preInvestProjectCode = preInvestProjectCode;
    }
    public void    setTransId(Integer transId){
        this.transId = transId;
    }
    public void    setRegistAccountingUnitCode(String registAccountingUnitCode){
        this.registAccountingUnitCode = registAccountingUnitCode;
    }
    public void    setRegistDepartmentCode(String registDepartmentCode){
        this.registDepartmentCode = registDepartmentCode;
    }
    public void    setRegistEmployeeNo(String registEmployeeNo){
        this.registEmployeeNo = registEmployeeNo;
    }
    public void    setRegistDate(Date registDate){
        this.registDate = registDate;
    }
    public void    setRegistDate(String    registDate){
        this.registDate = this.omcConvertStr2Date(registDate);
    }
    public void    setIfYn(String ifYn){
        this.ifYn = ifYn;
    }
    public void    setErrorYn(String errorYn){
        this.errorYn = errorYn;
    }
    public void    setErrorMsg(String errorMsg){
        this.errorMsg = errorMsg;
    }
    public void    setGbmsVersionNo(Integer gbmsVersionNo){
        this.gbmsVersionNo = gbmsVersionNo;
    }
    public void    setIfVersionNo(Integer ifVersionNo){
        this.ifVersionNo = ifVersionNo;
    }
    public void    setSupervisionDepartmentCode(String supervisionDepartmentCode){
        this.supervisionDepartmentCode = supervisionDepartmentCode;
    }
    public Integer getIfSeq(){
        return ifSeq;
    }
    public Integer getSeqNo(){
        return seqNo;
    }
    public String getLineTypeCode(){
        return lineTypeCode;
    }
    public String getVersionObid(){
        return versionObid;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getPlanProjectCode(){
        return planProjectCode;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getProjectName(){
        return projectName;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getPlanYear(){
        return planYear;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
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
    public String getGoodsName(){
        return goodsName;
    }
    public String getAssetType(){
        return assetType;
    }
    public String getPayDepartmentCode(){
        return payDepartmentCode;
    }
    public String getSetupDepartmentCode(){
        return setupDepartmentCode;
    }
    public String getMarketTypeCode(){
        return marketTypeCode;
    }
    public String getOrderMonth(){
        return orderMonth;
    }
    public String getStockMonth(){
        return stockMonth;
    }
    public String getInvestPurpose(){
        return investPurpose;
    }
    public String getCalcContents(){
        return calcContents;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public BigDecimal getCurrencyAmount(){
        return currencyAmount;
    }
    public String getTransactionCurrencyCode(){
        return transactionCurrencyCode;
    }
    public BigDecimal getTransactionAmount(){
        return transactionAmount;
    }
    public Float getAccomRate(){
        return accomRate;
    }
    public String getContents(){
        return contents;
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
    public String getAssetMajorCode(){
        return assetMajorCode;
    }
    public String getAssetMinorCode(){
        return assetMinorCode;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getPlmDepartmentCode(){
        return plmDepartmentCode;
    }
    public String getItDepartmentCode(){
        return itDepartmentCode;
    }
    public String getItmsProjectCode(){
        return itmsProjectCode;
    }
    public String getAssetItFlag(){
        return assetItFlag;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public String getPreCarryForwardFlag(){
        return preCarryForwardFlag;
    }
    public String getPreInvestProjectCode(){
        return preInvestProjectCode;
    }
    public Integer getTransId(){
        return transId;
    }
    public String getRegistAccountingUnitCode(){
        return registAccountingUnitCode;
    }
    public String getRegistDepartmentCode(){
        return registDepartmentCode;
    }
    public String getRegistEmployeeNo(){
        return registEmployeeNo;
    }
    public Date getRegistDate(){
        return registDate;
    }
    public String getIfYn(){
        return ifYn;
    }
    public String getErrorYn(){
        return errorYn;
    }
    public String getErrorMsg(){
        return errorMsg;
    }
    public Integer getGbmsVersionNo(){
        return gbmsVersionNo;
    }
    public Integer getIfVersionNo(){
        return ifVersionNo;
    }
    public String getSupervisionDepartmentCode(){
        return supervisionDepartmentCode;
    }
}

