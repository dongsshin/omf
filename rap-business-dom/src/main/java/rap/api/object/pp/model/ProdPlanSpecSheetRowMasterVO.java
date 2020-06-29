/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSpecSheetRowMasterVO.java
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
public class ProdPlanSpecSheetRowMasterVO extends BusinessObjectMasterVO {
    private String        specRowId                                         ;
    private String        specSheetGroupId                                  ;
    private String        scope                                             ;
    private String        regionCode                                        ;
    private String        countryCode                                       ;
    private String        parentRowId                                       ;
    private String        startSpecSheetId                                  ;
    private String        useYn                                             ;


    public void    setSpecRowId(String specRowId){
        this.specRowId = specRowId;
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
    public void    setParentRowId(String parentRowId){
        this.parentRowId = parentRowId;
    }
    public void    setStartSpecSheetId(String startSpecSheetId){
        this.startSpecSheetId = startSpecSheetId;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getSpecRowId(){
        return specRowId;
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
    public String getParentRowId(){
        return parentRowId;
    }
    public String getStartSpecSheetId(){
        return startSpecSheetId;
    }
    public String getUseYn(){
        return useYn;
    }
}

