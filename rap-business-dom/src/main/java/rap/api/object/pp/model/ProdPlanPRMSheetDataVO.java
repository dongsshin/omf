/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanPRMSheetDataVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanPRMSheetDataVO extends BusinessObjectMasterVO {
    private String        prmSheetId                                        ;
    private String        prmCode                                           ;
    private Integer       rowNo                                             ;
    private String        productType1                                      ;
    private String        productType2                                      ;
    private String        platform                                          ;
    private String        globalSeries                                      ;
    private String        regionSeries                                      ;
    private String        countrySeries                                     ;
    private String        tier                                              ;
    private Float         price1                                            ;
    private Float         price2                                            ;
    private Float         price3                                            ;
    private String        launchBefore2year                                 ;
    private Integer       launchYear                                        ;
    private Integer       launchMonth                                       ;
    private Integer       launchWeek                                        ;
    private String        launchAfter2year                                  ;
    private String        launchChange1                                     ;
    private String        launchChange2                                     ;
    private String        launchChange3                                     ;
    private Float         priceChange1stPrice1                              ;
    private Float         priceChange1stPrice2                              ;
    private Float         priceChange1stPrice3                              ;
    private Integer       priceChange1stYear                                ;
    private Integer       priceChange1stMonth                               ;
    private Float         priceChange2ndPrice1                              ;
    private Float         priceChange2ndPrice2                              ;
    private Float         priceChange2ndPrice3                              ;
    private Integer       priceChange2ndYear                                ;
    private Integer       priceChange2ndMonth                               ;
    private Float         priceChange3rdPrice1                              ;
    private Float         priceChange3rdPrice2                              ;
    private Float         priceChange3rdPrice3                              ;
    private Integer       priceChange3rdYear                                ;
    private Integer       priceChange3rdMonth                               ;
    private Float         priceChange4thPrice1                              ;
    private Float         priceChange4thPrice2                              ;
    private Float         priceChange4thPrice3                              ;
    private Integer       priceChange4thYear                                ;
    private Integer       priceChange4thMonth                               ;
    private String        mainItem1                                         ;
    private String        mainItem2                                         ;
    private String        mainItem3                                         ;
    private String        mainItem4                                         ;
    private String        subItem1                                          ;
    private String        subItem2                                          ;
    private String        subItem3                                          ;
    private String        subItem4                                          ;
    private String        productMain                                       ;
    private String        productSub1                                       ;
    private String        productSub2                                       ;
    private String        productSub3                                       ;
    private String        productSub4                                       ;
    private String        pmg                                               ;
    private String        referenceKey                                      ;
    private String        parentReferenceKey                                ;
    private String        remark                                            ;
    private Integer       endOfProductYear                                  ;
    private Integer       endOfProductMonth                                 ;
    private String        linkPrmCode                                       ;


    public void    setPrmSheetId(String prmSheetId){
        this.prmSheetId = prmSheetId;
    }
    public void    setPrmCode(String prmCode){
        this.prmCode = prmCode;
    }
    public void    setRowNo(Integer rowNo){
        this.rowNo = rowNo;
    }
    public void    setProductType1(String productType1){
        this.productType1 = productType1;
    }
    public void    setProductType2(String productType2){
        this.productType2 = productType2;
    }
    public void    setPlatform(String platform){
        this.platform = platform;
    }
    public void    setGlobalSeries(String globalSeries){
        this.globalSeries = globalSeries;
    }
    public void    setRegionSeries(String regionSeries){
        this.regionSeries = regionSeries;
    }
    public void    setCountrySeries(String countrySeries){
        this.countrySeries = countrySeries;
    }
    public void    setTier(String tier){
        this.tier = tier;
    }
    public void    setPrice1(Float price1){
        this.price1 = price1;
    }
    public void    setPrice2(Float price2){
        this.price2 = price2;
    }
    public void    setPrice3(Float price3){
        this.price3 = price3;
    }
    public void    setLaunchBefore2year(String launchBefore2year){
        this.launchBefore2year = launchBefore2year;
    }
    public void    setLaunchYear(Integer launchYear){
        this.launchYear = launchYear;
    }
    public void    setLaunchMonth(Integer launchMonth){
        this.launchMonth = launchMonth;
    }
    public void    setLaunchWeek(Integer launchWeek){
        this.launchWeek = launchWeek;
    }
    public void    setLaunchAfter2year(String launchAfter2year){
        this.launchAfter2year = launchAfter2year;
    }
    public void    setLaunchChange1(String launchChange1){
        this.launchChange1 = launchChange1;
    }
    public void    setLaunchChange2(String launchChange2){
        this.launchChange2 = launchChange2;
    }
    public void    setLaunchChange3(String launchChange3){
        this.launchChange3 = launchChange3;
    }
    public void    setPriceChange1stPrice1(Float priceChange1stPrice1){
        this.priceChange1stPrice1 = priceChange1stPrice1;
    }
    public void    setPriceChange1stPrice2(Float priceChange1stPrice2){
        this.priceChange1stPrice2 = priceChange1stPrice2;
    }
    public void    setPriceChange1stPrice3(Float priceChange1stPrice3){
        this.priceChange1stPrice3 = priceChange1stPrice3;
    }
    public void    setPriceChange1stYear(Integer priceChange1stYear){
        this.priceChange1stYear = priceChange1stYear;
    }
    public void    setPriceChange1stMonth(Integer priceChange1stMonth){
        this.priceChange1stMonth = priceChange1stMonth;
    }
    public void    setPriceChange2ndPrice1(Float priceChange2ndPrice1){
        this.priceChange2ndPrice1 = priceChange2ndPrice1;
    }
    public void    setPriceChange2ndPrice2(Float priceChange2ndPrice2){
        this.priceChange2ndPrice2 = priceChange2ndPrice2;
    }
    public void    setPriceChange2ndPrice3(Float priceChange2ndPrice3){
        this.priceChange2ndPrice3 = priceChange2ndPrice3;
    }
    public void    setPriceChange2ndYear(Integer priceChange2ndYear){
        this.priceChange2ndYear = priceChange2ndYear;
    }
    public void    setPriceChange2ndMonth(Integer priceChange2ndMonth){
        this.priceChange2ndMonth = priceChange2ndMonth;
    }
    public void    setPriceChange3rdPrice1(Float priceChange3rdPrice1){
        this.priceChange3rdPrice1 = priceChange3rdPrice1;
    }
    public void    setPriceChange3rdPrice2(Float priceChange3rdPrice2){
        this.priceChange3rdPrice2 = priceChange3rdPrice2;
    }
    public void    setPriceChange3rdPrice3(Float priceChange3rdPrice3){
        this.priceChange3rdPrice3 = priceChange3rdPrice3;
    }
    public void    setPriceChange3rdYear(Integer priceChange3rdYear){
        this.priceChange3rdYear = priceChange3rdYear;
    }
    public void    setPriceChange3rdMonth(Integer priceChange3rdMonth){
        this.priceChange3rdMonth = priceChange3rdMonth;
    }
    public void    setPriceChange4thPrice1(Float priceChange4thPrice1){
        this.priceChange4thPrice1 = priceChange4thPrice1;
    }
    public void    setPriceChange4thPrice2(Float priceChange4thPrice2){
        this.priceChange4thPrice2 = priceChange4thPrice2;
    }
    public void    setPriceChange4thPrice3(Float priceChange4thPrice3){
        this.priceChange4thPrice3 = priceChange4thPrice3;
    }
    public void    setPriceChange4thYear(Integer priceChange4thYear){
        this.priceChange4thYear = priceChange4thYear;
    }
    public void    setPriceChange4thMonth(Integer priceChange4thMonth){
        this.priceChange4thMonth = priceChange4thMonth;
    }
    public void    setMainItem1(String mainItem1){
        this.mainItem1 = mainItem1;
    }
    public void    setMainItem2(String mainItem2){
        this.mainItem2 = mainItem2;
    }
    public void    setMainItem3(String mainItem3){
        this.mainItem3 = mainItem3;
    }
    public void    setMainItem4(String mainItem4){
        this.mainItem4 = mainItem4;
    }
    public void    setSubItem1(String subItem1){
        this.subItem1 = subItem1;
    }
    public void    setSubItem2(String subItem2){
        this.subItem2 = subItem2;
    }
    public void    setSubItem3(String subItem3){
        this.subItem3 = subItem3;
    }
    public void    setSubItem4(String subItem4){
        this.subItem4 = subItem4;
    }
    public void    setProductMain(String productMain){
        this.productMain = productMain;
    }
    public void    setProductSub1(String productSub1){
        this.productSub1 = productSub1;
    }
    public void    setProductSub2(String productSub2){
        this.productSub2 = productSub2;
    }
    public void    setProductSub3(String productSub3){
        this.productSub3 = productSub3;
    }
    public void    setProductSub4(String productSub4){
        this.productSub4 = productSub4;
    }
    public void    setPmg(String pmg){
        this.pmg = pmg;
    }
    public void    setReferenceKey(String referenceKey){
        this.referenceKey = referenceKey;
    }
    public void    setParentReferenceKey(String parentReferenceKey){
        this.parentReferenceKey = parentReferenceKey;
    }
    public void    setRemark(String remark){
        this.remark = remark;
    }
    public void    setEndOfProductYear(Integer endOfProductYear){
        this.endOfProductYear = endOfProductYear;
    }
    public void    setEndOfProductMonth(Integer endOfProductMonth){
        this.endOfProductMonth = endOfProductMonth;
    }
    public void    setLinkPrmCode(String linkPrmCode){
        this.linkPrmCode = linkPrmCode;
    }
    public String getPrmSheetId(){
        return prmSheetId;
    }
    public String getPrmCode(){
        return prmCode;
    }
    public Integer getRowNo(){
        return rowNo;
    }
    public String getProductType1(){
        return productType1;
    }
    public String getProductType2(){
        return productType2;
    }
    public String getPlatform(){
        return platform;
    }
    public String getGlobalSeries(){
        return globalSeries;
    }
    public String getRegionSeries(){
        return regionSeries;
    }
    public String getCountrySeries(){
        return countrySeries;
    }
    public String getTier(){
        return tier;
    }
    public Float getPrice1(){
        return price1;
    }
    public Float getPrice2(){
        return price2;
    }
    public Float getPrice3(){
        return price3;
    }
    public String getLaunchBefore2year(){
        return launchBefore2year;
    }
    public Integer getLaunchYear(){
        return launchYear;
    }
    public Integer getLaunchMonth(){
        return launchMonth;
    }
    public Integer getLaunchWeek(){
        return launchWeek;
    }
    public String getLaunchAfter2year(){
        return launchAfter2year;
    }
    public String getLaunchChange1(){
        return launchChange1;
    }
    public String getLaunchChange2(){
        return launchChange2;
    }
    public String getLaunchChange3(){
        return launchChange3;
    }
    public Float getPriceChange1stPrice1(){
        return priceChange1stPrice1;
    }
    public Float getPriceChange1stPrice2(){
        return priceChange1stPrice2;
    }
    public Float getPriceChange1stPrice3(){
        return priceChange1stPrice3;
    }
    public Integer getPriceChange1stYear(){
        return priceChange1stYear;
    }
    public Integer getPriceChange1stMonth(){
        return priceChange1stMonth;
    }
    public Float getPriceChange2ndPrice1(){
        return priceChange2ndPrice1;
    }
    public Float getPriceChange2ndPrice2(){
        return priceChange2ndPrice2;
    }
    public Float getPriceChange2ndPrice3(){
        return priceChange2ndPrice3;
    }
    public Integer getPriceChange2ndYear(){
        return priceChange2ndYear;
    }
    public Integer getPriceChange2ndMonth(){
        return priceChange2ndMonth;
    }
    public Float getPriceChange3rdPrice1(){
        return priceChange3rdPrice1;
    }
    public Float getPriceChange3rdPrice2(){
        return priceChange3rdPrice2;
    }
    public Float getPriceChange3rdPrice3(){
        return priceChange3rdPrice3;
    }
    public Integer getPriceChange3rdYear(){
        return priceChange3rdYear;
    }
    public Integer getPriceChange3rdMonth(){
        return priceChange3rdMonth;
    }
    public Float getPriceChange4thPrice1(){
        return priceChange4thPrice1;
    }
    public Float getPriceChange4thPrice2(){
        return priceChange4thPrice2;
    }
    public Float getPriceChange4thPrice3(){
        return priceChange4thPrice3;
    }
    public Integer getPriceChange4thYear(){
        return priceChange4thYear;
    }
    public Integer getPriceChange4thMonth(){
        return priceChange4thMonth;
    }
    public String getMainItem1(){
        return mainItem1;
    }
    public String getMainItem2(){
        return mainItem2;
    }
    public String getMainItem3(){
        return mainItem3;
    }
    public String getMainItem4(){
        return mainItem4;
    }
    public String getSubItem1(){
        return subItem1;
    }
    public String getSubItem2(){
        return subItem2;
    }
    public String getSubItem3(){
        return subItem3;
    }
    public String getSubItem4(){
        return subItem4;
    }
    public String getProductMain(){
        return productMain;
    }
    public String getProductSub1(){
        return productSub1;
    }
    public String getProductSub2(){
        return productSub2;
    }
    public String getProductSub3(){
        return productSub3;
    }
    public String getProductSub4(){
        return productSub4;
    }
    public String getPmg(){
        return pmg;
    }
    public String getReferenceKey(){
        return referenceKey;
    }
    public String getParentReferenceKey(){
        return parentReferenceKey;
    }
    public String getRemark(){
        return remark;
    }
    public Integer getEndOfProductYear(){
        return endOfProductYear;
    }
    public Integer getEndOfProductMonth(){
        return endOfProductMonth;
    }
    public String getLinkPrmCode(){
        return linkPrmCode;
    }
}

