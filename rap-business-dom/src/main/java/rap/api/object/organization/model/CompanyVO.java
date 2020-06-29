/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CompanyVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.model;


import rap.api.object.organization.model.OrganizationsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class CompanyVO extends OrganizationsVO {
    private String        companyInspectionFlag                              = "false";
    private String        countryCode                                       ;
    private String        headQuarterFlag                                   ;
    private String        makerCode                                         ;
    private String        makerLatentNo                                     ;
    private String        makerNameEng                                      ;
    private String        makerNameKor                                      ;
    private String        makerNo                                           ;
    private String        makerRegStatus                                    ;
    private String        makerType                                         ;
    private String        pimsMakerFlag                                      = "N";
    private String        pimsMakerId                                       ;
    private String        preMakerCode                                      ;
    private String        qualityContractFlag                                = "false";
    private String        representativeNameEng                             ;
    private String        representativeNameKor                             ;
    private String        dunsNumber                                        ;
    private String        webSite                                           ;
    private String        phoneNumber                                       ;
    private String        faxNumber                                         ;
    private String        organizationId                                    ;
    private String        address                                           ;
    private String        city                                              ;
    private String        country                                           ;
    private String        postalCode                                        ;
    private String        division                                          ;
    private String        email                                             ;


    public void    setCompanyInspectionFlag(String companyInspectionFlag){
        this.companyInspectionFlag = companyInspectionFlag;
    }
    public void    setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public void    setHeadQuarterFlag(String headQuarterFlag){
        this.headQuarterFlag = headQuarterFlag;
    }
    public void    setMakerCode(String makerCode){
        this.makerCode = makerCode;
    }
    public void    setMakerLatentNo(String makerLatentNo){
        this.makerLatentNo = makerLatentNo;
    }
    public void    setMakerNameEng(String makerNameEng){
        this.makerNameEng = makerNameEng;
    }
    public void    setMakerNameKor(String makerNameKor){
        this.makerNameKor = makerNameKor;
    }
    public void    setMakerNo(String makerNo){
        this.makerNo = makerNo;
    }
    public void    setMakerRegStatus(String makerRegStatus){
        this.makerRegStatus = makerRegStatus;
    }
    public void    setMakerType(String makerType){
        this.makerType = makerType;
    }
    public void    setPimsMakerFlag(String pimsMakerFlag){
        this.pimsMakerFlag = pimsMakerFlag;
    }
    public void    setPimsMakerId(String pimsMakerId){
        this.pimsMakerId = pimsMakerId;
    }
    public void    setPreMakerCode(String preMakerCode){
        this.preMakerCode = preMakerCode;
    }
    public void    setQualityContractFlag(String qualityContractFlag){
        this.qualityContractFlag = qualityContractFlag;
    }
    public void    setRepresentativeNameEng(String representativeNameEng){
        this.representativeNameEng = representativeNameEng;
    }
    public void    setRepresentativeNameKor(String representativeNameKor){
        this.representativeNameKor = representativeNameKor;
    }
    public void    setDunsNumber(String dunsNumber){
        this.dunsNumber = dunsNumber;
    }
    public void    setWebSite(String webSite){
        this.webSite = webSite;
    }
    public void    setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void    setFaxNumber(String faxNumber){
        this.faxNumber = faxNumber;
    }
    public void    setOrganizationId(String organizationId){
        this.organizationId = organizationId;
    }
    public void    setAddress(String address){
        this.address = address;
    }
    public void    setCity(String city){
        this.city = city;
    }
    public void    setCountry(String country){
        this.country = country;
    }
    public void    setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public void    setDivision(String division){
        this.division = division;
    }
    public void    setEmail(String email){
        this.email = email;
    }
    public String getCompanyInspectionFlag(){
        return companyInspectionFlag;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public String getHeadQuarterFlag(){
        return headQuarterFlag;
    }
    public String getMakerCode(){
        return makerCode;
    }
    public String getMakerLatentNo(){
        return makerLatentNo;
    }
    public String getMakerNameEng(){
        return makerNameEng;
    }
    public String getMakerNameKor(){
        return makerNameKor;
    }
    public String getMakerNo(){
        return makerNo;
    }
    public String getMakerRegStatus(){
        return makerRegStatus;
    }
    public String getMakerType(){
        return makerType;
    }
    public String getPimsMakerFlag(){
        return pimsMakerFlag;
    }
    public String getPimsMakerId(){
        return pimsMakerId;
    }
    public String getPreMakerCode(){
        return preMakerCode;
    }
    public String getQualityContractFlag(){
        return qualityContractFlag;
    }
    public String getRepresentativeNameEng(){
        return representativeNameEng;
    }
    public String getRepresentativeNameKor(){
        return representativeNameKor;
    }
    public String getDunsNumber(){
        return dunsNumber;
    }
    public String getWebSite(){
        return webSite;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getFaxNumber(){
        return faxNumber;
    }
    public String getOrganizationId(){
        return organizationId;
    }
    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }
    public String getCountry(){
        return country;
    }
    public String getPostalCode(){
        return postalCode;
    }
    public String getDivision(){
        return division;
    }
    public String getEmail(){
        return email;
    }
}

