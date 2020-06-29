/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanPRMSheetVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanPRMSheetVO extends BusinessObjectVO {
    private String        prmSheetId                                        ;
    private String        prmSheetGroupId                                   ;
    private String        isLastVersion                                     ;
    private String        isLastReleased                                    ;
    private String        scope                                             ;
    private String        regionCode                                        ;
    private String        countryCode                                       ;
    private Integer       globalVersion                                     ;
    private Integer       regionVersion                                     ;
    private Integer       countryVersion                                    ;
    private String        currency1                                         ;
    private String        currency2                                         ;
    private String        currency3                                         ;
    private String        finalPptFileId                                    ;
    private Integer       subVersion                                        ;
    private String        brandName                                         ;
    private String        nationName                                        ;
    private String        checkoutedPrmSheetId                              ;
    private String        distributedPrmSheetId                             ;


    public void    setPrmSheetId(String prmSheetId){
        this.prmSheetId = prmSheetId;
    }
    public void    setPrmSheetGroupId(String prmSheetGroupId){
        this.prmSheetGroupId = prmSheetGroupId;
    }
    public void    setIsLastVersion(String isLastVersion){
        this.isLastVersion = isLastVersion;
    }
    public void    setIsLastReleased(String isLastReleased){
        this.isLastReleased = isLastReleased;
    }
    public void    setScope(String scope){
        this.scope = scope;
    }
    public void    setRegionCode(String regionCode){
        this.regionCode = regionCode;
    }
    public void    setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public void    setGlobalVersion(Integer globalVersion){
        this.globalVersion = globalVersion;
    }
    public void    setRegionVersion(Integer regionVersion){
        this.regionVersion = regionVersion;
    }
    public void    setCountryVersion(Integer countryVersion){
        this.countryVersion = countryVersion;
    }
    public void    setCurrency1(String currency1){
        this.currency1 = currency1;
    }
    public void    setCurrency2(String currency2){
        this.currency2 = currency2;
    }
    public void    setCurrency3(String currency3){
        this.currency3 = currency3;
    }
    public void    setFinalPptFileId(String finalPptFileId){
        this.finalPptFileId = finalPptFileId;
    }
    public void    setSubVersion(Integer subVersion){
        this.subVersion = subVersion;
    }
    public void    setBrandName(String brandName){
        this.brandName = brandName;
    }
    public void    setNationName(String nationName){
        this.nationName = nationName;
    }
    public void    setCheckoutedPrmSheetId(String checkoutedPrmSheetId){
        this.checkoutedPrmSheetId = checkoutedPrmSheetId;
    }
    public void    setDistributedPrmSheetId(String distributedPrmSheetId){
        this.distributedPrmSheetId = distributedPrmSheetId;
    }
    public String getPrmSheetId(){
        return prmSheetId;
    }
    public String getPrmSheetGroupId(){
        return prmSheetGroupId;
    }
    public String getIsLastVersion(){
        return isLastVersion;
    }
    public String getIsLastReleased(){
        return isLastReleased;
    }
    public String getScope(){
        return scope;
    }
    public String getRegionCode(){
        return regionCode;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public Integer getGlobalVersion(){
        return globalVersion;
    }
    public Integer getRegionVersion(){
        return regionVersion;
    }
    public Integer getCountryVersion(){
        return countryVersion;
    }
    public String getCurrency1(){
        return currency1;
    }
    public String getCurrency2(){
        return currency2;
    }
    public String getCurrency3(){
        return currency3;
    }
    public String getFinalPptFileId(){
        return finalPptFileId;
    }
    public Integer getSubVersion(){
        return subVersion;
    }
    public String getBrandName(){
        return brandName;
    }
    public String getNationName(){
        return nationName;
    }
    public String getCheckoutedPrmSheetId(){
        return checkoutedPrmSheetId;
    }
    public String getDistributedPrmSheetId(){
        return distributedPrmSheetId;
    }
}

