/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSpecSheetColumnMasterVO.java
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
public class ProdPlanSpecSheetColumnMasterVO extends BusinessObjectMasterVO {
    private String        specColId                                         ;
    private String        specSheetGroupId                                  ;
    private String        scope                                             ;
    private String        regionCode                                        ;
    private String        countryCode                                       ;
    private String        parentColId                                       ;
    private String        startSpecSheetId                                  ;
    private String        useYn                                             ;
    private String        specColType                                       ;
    private String        specColName                                       ;


    public void    setSpecColId(String specColId){
        this.specColId = specColId;
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
    public void    setParentColId(String parentColId){
        this.parentColId = parentColId;
    }
    public void    setStartSpecSheetId(String startSpecSheetId){
        this.startSpecSheetId = startSpecSheetId;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setSpecColType(String specColType){
        this.specColType = specColType;
    }
    public void    setSpecColName(String specColName){
        this.specColName = specColName;
    }
    public String getSpecColId(){
        return specColId;
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
    public String getParentColId(){
        return parentColId;
    }
    public String getStartSpecSheetId(){
        return startSpecSheetId;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getSpecColType(){
        return specColType;
    }
    public String getSpecColName(){
        return specColName;
    }
}

