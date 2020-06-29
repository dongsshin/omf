/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SupplierMasterVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class SupplierMasterVO extends BusinessObjectMasterVO {
    private String        supplierCode                                      ;
    private String        bizRegisterNo                                     ;
    private Date          startDateActive                                   ;
    private Date          endDateActive                                     ;
    private String        oldBizRegisterNo                                  ;
    private String        isoDigit2CountryCode                              ;
    private String        isoDigit3CountryCode                              ;
    private String        corporationNo                                     ;
    private String        supplierEnglishName                               ;
    private String        supplierLocalName                                 ;
    private String        sourcingTypeCode                                  ;
    private String        firstRegAffiliate                                 ;
    private String        firstRegAccountUnit                               ;


    public void    setSupplierCode(String supplierCode){
        this.supplierCode = supplierCode;
    }
    public void    setBizRegisterNo(String bizRegisterNo){
        this.bizRegisterNo = bizRegisterNo;
    }
    public void    setStartDateActive(Date startDateActive){
        this.startDateActive = startDateActive;
    }
    public void    setStartDateActive(String    startDateActive){
        this.startDateActive = this.omcConvertStr2Date(startDateActive);
    }
    public void    setEndDateActive(Date endDateActive){
        this.endDateActive = endDateActive;
    }
    public void    setEndDateActive(String    endDateActive){
        this.endDateActive = this.omcConvertStr2Date(endDateActive);
    }
    public void    setOldBizRegisterNo(String oldBizRegisterNo){
        this.oldBizRegisterNo = oldBizRegisterNo;
    }
    public void    setIsoDigit2CountryCode(String isoDigit2CountryCode){
        this.isoDigit2CountryCode = isoDigit2CountryCode;
    }
    public void    setIsoDigit3CountryCode(String isoDigit3CountryCode){
        this.isoDigit3CountryCode = isoDigit3CountryCode;
    }
    public void    setCorporationNo(String corporationNo){
        this.corporationNo = corporationNo;
    }
    public void    setSupplierEnglishName(String supplierEnglishName){
        this.supplierEnglishName = supplierEnglishName;
    }
    public void    setSupplierLocalName(String supplierLocalName){
        this.supplierLocalName = supplierLocalName;
    }
    public void    setSourcingTypeCode(String sourcingTypeCode){
        this.sourcingTypeCode = sourcingTypeCode;
    }
    public void    setFirstRegAffiliate(String firstRegAffiliate){
        this.firstRegAffiliate = firstRegAffiliate;
    }
    public void    setFirstRegAccountUnit(String firstRegAccountUnit){
        this.firstRegAccountUnit = firstRegAccountUnit;
    }
    public String getSupplierCode(){
        return supplierCode;
    }
    public String getBizRegisterNo(){
        return bizRegisterNo;
    }
    public Date getStartDateActive(){
        return startDateActive;
    }
    public Date getEndDateActive(){
        return endDateActive;
    }
    public String getOldBizRegisterNo(){
        return oldBizRegisterNo;
    }
    public String getIsoDigit2CountryCode(){
        return isoDigit2CountryCode;
    }
    public String getIsoDigit3CountryCode(){
        return isoDigit3CountryCode;
    }
    public String getCorporationNo(){
        return corporationNo;
    }
    public String getSupplierEnglishName(){
        return supplierEnglishName;
    }
    public String getSupplierLocalName(){
        return supplierLocalName;
    }
    public String getSourcingTypeCode(){
        return sourcingTypeCode;
    }
    public String getFirstRegAffiliate(){
        return firstRegAffiliate;
    }
    public String getFirstRegAccountUnit(){
        return firstRegAccountUnit;
    }
}

