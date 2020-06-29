/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanPRMCodeMasterVO.java
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
public class ProdPlanPRMCodeMasterVO extends BusinessObjectMasterVO {
    private String        prmCode                                           ;
    private String        prmSheetGroupId                                   ;
    private String        referenceKey                                      ;
    private String        scope                                             ;
    private String        regionCode                                        ;
    private String        countryCode                                       ;
    private String        parentPrmCode                                     ;
    private String        prevYearPrmCode                                   ;
    private String        startPrmSheetId                                   ;
    private String        useYn                                             ;


    public void    setPrmCode(String prmCode){
        this.prmCode = prmCode;
    }
    public void    setPrmSheetGroupId(String prmSheetGroupId){
        this.prmSheetGroupId = prmSheetGroupId;
    }
    public void    setReferenceKey(String referenceKey){
        this.referenceKey = referenceKey;
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
    public void    setParentPrmCode(String parentPrmCode){
        this.parentPrmCode = parentPrmCode;
    }
    public void    setPrevYearPrmCode(String prevYearPrmCode){
        this.prevYearPrmCode = prevYearPrmCode;
    }
    public void    setStartPrmSheetId(String startPrmSheetId){
        this.startPrmSheetId = startPrmSheetId;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getPrmCode(){
        return prmCode;
    }
    public String getPrmSheetGroupId(){
        return prmSheetGroupId;
    }
    public String getReferenceKey(){
        return referenceKey;
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
    public String getParentPrmCode(){
        return parentPrmCode;
    }
    public String getPrevYearPrmCode(){
        return prevYearPrmCode;
    }
    public String getStartPrmSheetId(){
        return startPrmSheetId;
    }
    public String getUseYn(){
        return useYn;
    }
}

