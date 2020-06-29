/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MakerContactPointVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class MakerContactPointVO extends BusinessObjectMasterVO {
    private String        address                                           ;
    private String        addressEtc                                        ;
    private String        businessDeveloperFlag                             ;
    private String        businessPurchaserFlag                             ;
    private String        businessQualityFlag                               ;
    private String        cpcRegistrationFlag                               ;
    private String        cpcUserId                                         ;
    private String        contactPointType                                  ;
    private String        email                                             ;
    private String        makerContactNameEng                               ;
    private String        makerContactNameKor                               ;
    private String        makerContactPointStatus                           ;
    private String        makerDescription                                  ;
    private String        puspoUserId                                       ;
    private String        personalFax                                       ;
    private String        personalHandphone                                 ;
    private String        personalTelephone                                 ;
    private String        position                                          ;
    private String        preferenceDivision                                ;
    private String        representativeFlag                                ;
    private String        ssoId                                             ;


    public void    setAddress(String address){
        this.address = address;
    }
    public void    setAddressEtc(String addressEtc){
        this.addressEtc = addressEtc;
    }
    public void    setBusinessDeveloperFlag(String businessDeveloperFlag){
        this.businessDeveloperFlag = businessDeveloperFlag;
    }
    public void    setBusinessPurchaserFlag(String businessPurchaserFlag){
        this.businessPurchaserFlag = businessPurchaserFlag;
    }
    public void    setBusinessQualityFlag(String businessQualityFlag){
        this.businessQualityFlag = businessQualityFlag;
    }
    public void    setCpcRegistrationFlag(String cpcRegistrationFlag){
        this.cpcRegistrationFlag = cpcRegistrationFlag;
    }
    public void    setCpcUserId(String cpcUserId){
        this.cpcUserId = cpcUserId;
    }
    public void    setContactPointType(String contactPointType){
        this.contactPointType = contactPointType;
    }
    public void    setEmail(String email){
        this.email = email;
    }
    public void    setMakerContactNameEng(String makerContactNameEng){
        this.makerContactNameEng = makerContactNameEng;
    }
    public void    setMakerContactNameKor(String makerContactNameKor){
        this.makerContactNameKor = makerContactNameKor;
    }
    public void    setMakerContactPointStatus(String makerContactPointStatus){
        this.makerContactPointStatus = makerContactPointStatus;
    }
    public void    setMakerDescription(String makerDescription){
        this.makerDescription = makerDescription;
    }
    public void    setPuspoUserId(String puspoUserId){
        this.puspoUserId = puspoUserId;
    }
    public void    setPersonalFax(String personalFax){
        this.personalFax = personalFax;
    }
    public void    setPersonalHandphone(String personalHandphone){
        this.personalHandphone = personalHandphone;
    }
    public void    setPersonalTelephone(String personalTelephone){
        this.personalTelephone = personalTelephone;
    }
    public void    setPosition(String position){
        this.position = position;
    }
    public void    setPreferenceDivision(String preferenceDivision){
        this.preferenceDivision = preferenceDivision;
    }
    public void    setRepresentativeFlag(String representativeFlag){
        this.representativeFlag = representativeFlag;
    }
    public void    setSsoId(String ssoId){
        this.ssoId = ssoId;
    }
    public String getAddress(){
        return address;
    }
    public String getAddressEtc(){
        return addressEtc;
    }
    public String getBusinessDeveloperFlag(){
        return businessDeveloperFlag;
    }
    public String getBusinessPurchaserFlag(){
        return businessPurchaserFlag;
    }
    public String getBusinessQualityFlag(){
        return businessQualityFlag;
    }
    public String getCpcRegistrationFlag(){
        return cpcRegistrationFlag;
    }
    public String getCpcUserId(){
        return cpcUserId;
    }
    public String getContactPointType(){
        return contactPointType;
    }
    public String getEmail(){
        return email;
    }
    public String getMakerContactNameEng(){
        return makerContactNameEng;
    }
    public String getMakerContactNameKor(){
        return makerContactNameKor;
    }
    public String getMakerContactPointStatus(){
        return makerContactPointStatus;
    }
    public String getMakerDescription(){
        return makerDescription;
    }
    public String getPuspoUserId(){
        return puspoUserId;
    }
    public String getPersonalFax(){
        return personalFax;
    }
    public String getPersonalHandphone(){
        return personalHandphone;
    }
    public String getPersonalTelephone(){
        return personalTelephone;
    }
    public String getPosition(){
        return position;
    }
    public String getPreferenceDivision(){
        return preferenceDivision;
    }
    public String getRepresentativeFlag(){
        return representativeFlag;
    }
    public String getSsoId(){
        return ssoId;
    }
}

