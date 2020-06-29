/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ManagementGroupVO.java
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
public class ManagementGroupVO extends BusinessObjectMasterVO {
    private String        managementDeptCode                                ;
    private String        planType                                          ;
    private String        diverYn                                           ;
    private String        currencyCode                                       = "KRW";
    private String        corporationCode                                   ;
    private String        faCorporationCode                                 ;
    private String        useYn                                             ;
    private String        fromObid                                          ;
    private String        autoCalculateYn                                   ;
    private String        commonProjectUseYn                                ;
    private String        planYear                                          ;
    private String        businessUnitCode                                  ;


    public void    setManagementDeptCode(String managementDeptCode){
        this.managementDeptCode = managementDeptCode;
    }
    public void    setPlanType(String planType){
        this.planType = planType;
    }
    public void    setDiverYn(String diverYn){
        this.diverYn = diverYn;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setCorporationCode(String corporationCode){
        this.corporationCode = corporationCode;
    }
    public void    setFaCorporationCode(String faCorporationCode){
        this.faCorporationCode = faCorporationCode;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setAutoCalculateYn(String autoCalculateYn){
        this.autoCalculateYn = autoCalculateYn;
    }
    public void    setCommonProjectUseYn(String commonProjectUseYn){
        this.commonProjectUseYn = commonProjectUseYn;
    }
    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setBusinessUnitCode(String businessUnitCode){
        this.businessUnitCode = businessUnitCode;
    }
    public String getManagementDeptCode(){
        return managementDeptCode;
    }
    public String getPlanType(){
        return planType;
    }
    public String getDiverYn(){
        return diverYn;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public String getCorporationCode(){
        return corporationCode;
    }
    public String getFaCorporationCode(){
        return faCorporationCode;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getAutoCalculateYn(){
        return autoCalculateYn;
    }
    public String getCommonProjectUseYn(){
        return commonProjectUseYn;
    }
    public String getPlanYear(){
        return planYear;
    }
    public String getBusinessUnitCode(){
        return businessUnitCode;
    }
}

