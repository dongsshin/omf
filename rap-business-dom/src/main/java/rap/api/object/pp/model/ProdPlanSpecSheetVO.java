/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSpecSheetVO.java
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
public class ProdPlanSpecSheetVO extends BusinessObjectVO {
    private String        specSheetId                                       ;
    private String        specSheetGroupId                                  ;
    private String        scope                                             ;
    private String        regionCode                                        ;
    private String        countryCode                                       ;
    private Integer       globalVersion                                     ;
    private Integer       regionVersion                                     ;
    private Integer       countryVersion                                    ;
    private String        finalExcelFileId                                  ;
    private Integer       subVersion                                        ;
    private String        checkoutedSpecSheetId                             ;
    private String        distributedSpecSheetId                            ;


    public void    setSpecSheetId(String specSheetId){
        this.specSheetId = specSheetId;
    }
    public void    setSpecSheetGroupId(String specSheetGroupId){
        this.specSheetGroupId = specSheetGroupId;
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
    public void    setFinalExcelFileId(String finalExcelFileId){
        this.finalExcelFileId = finalExcelFileId;
    }
    public void    setSubVersion(Integer subVersion){
        this.subVersion = subVersion;
    }
    public void    setCheckoutedSpecSheetId(String checkoutedSpecSheetId){
        this.checkoutedSpecSheetId = checkoutedSpecSheetId;
    }
    public void    setDistributedSpecSheetId(String distributedSpecSheetId){
        this.distributedSpecSheetId = distributedSpecSheetId;
    }
    public String getSpecSheetId(){
        return specSheetId;
    }
    public String getSpecSheetGroupId(){
        return specSheetGroupId;
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
    public String getFinalExcelFileId(){
        return finalExcelFileId;
    }
    public Integer getSubVersion(){
        return subVersion;
    }
    public String getCheckoutedSpecSheetId(){
        return checkoutedSpecSheetId;
    }
    public String getDistributedSpecSheetId(){
        return distributedSpecSheetId;
    }
}

