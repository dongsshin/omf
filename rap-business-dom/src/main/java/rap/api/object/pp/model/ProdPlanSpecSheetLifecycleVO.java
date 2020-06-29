/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSpecSheetLifecycleVO.java
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
public class ProdPlanSpecSheetLifecycleVO extends BusinessObjectMasterVO {
    private String        specSheetGroupId                                  ;
    private String        scope                                             ;
    private String        regionCode                                        ;
    private String        countryCode                                       ;
    private Integer       globalVersion                                     ;
    private Integer       regionVersion                                     ;
    private Integer       countryVersion                                    ;
    private String        specSheetId                                       ;
    private String        checkoutSpecSheetId                               ;


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
    public void    setSpecSheetId(String specSheetId){
        this.specSheetId = specSheetId;
    }
    public void    setCheckoutSpecSheetId(String checkoutSpecSheetId){
        this.checkoutSpecSheetId = checkoutSpecSheetId;
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
    public String getSpecSheetId(){
        return specSheetId;
    }
    public String getCheckoutSpecSheetId(){
        return checkoutSpecSheetId;
    }
}

