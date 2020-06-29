/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MakerMasterVO.java
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
public class MakerMasterVO extends BusinessObjectMasterVO {
    private Date          transferDate                                      ;
    private String        makerCode                                         ;
    private String        makerNameLocal                                    ;
    private String        makerNameEng                                      ;
    private String        makerBizRegisterNo                                ;
    private String        makerCountryCode                                  ;
    private String        makerCity                                         ;
    private String        makerAddress                                      ;
    private String        makerRemark                                       ;
    private String        makerStatusCode                                   ;
    private String        useFlag                                           ;
    private String        repMakerCode                                      ;
    private String        makerRequesterId                                  ;
    private String        makerRequesterDept                                ;
    private String        makerRequestDate                                  ;
    private String        makerApprovalId                                   ;
    private Date          makerApprovalDate                                 ;


    public void    setTransferDate(Date transferDate){
        this.transferDate = transferDate;
    }
    public void    setTransferDate(String    transferDate){
        this.transferDate = this.omcConvertStr2Date(transferDate);
    }
    public void    setMakerCode(String makerCode){
        this.makerCode = makerCode;
    }
    public void    setMakerNameLocal(String makerNameLocal){
        this.makerNameLocal = makerNameLocal;
    }
    public void    setMakerNameEng(String makerNameEng){
        this.makerNameEng = makerNameEng;
    }
    public void    setMakerBizRegisterNo(String makerBizRegisterNo){
        this.makerBizRegisterNo = makerBizRegisterNo;
    }
    public void    setMakerCountryCode(String makerCountryCode){
        this.makerCountryCode = makerCountryCode;
    }
    public void    setMakerCity(String makerCity){
        this.makerCity = makerCity;
    }
    public void    setMakerAddress(String makerAddress){
        this.makerAddress = makerAddress;
    }
    public void    setMakerRemark(String makerRemark){
        this.makerRemark = makerRemark;
    }
    public void    setMakerStatusCode(String makerStatusCode){
        this.makerStatusCode = makerStatusCode;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setRepMakerCode(String repMakerCode){
        this.repMakerCode = repMakerCode;
    }
    public void    setMakerRequesterId(String makerRequesterId){
        this.makerRequesterId = makerRequesterId;
    }
    public void    setMakerRequesterDept(String makerRequesterDept){
        this.makerRequesterDept = makerRequesterDept;
    }
    public void    setMakerRequestDate(String makerRequestDate){
        this.makerRequestDate = makerRequestDate;
    }
    public void    setMakerApprovalId(String makerApprovalId){
        this.makerApprovalId = makerApprovalId;
    }
    public void    setMakerApprovalDate(Date makerApprovalDate){
        this.makerApprovalDate = makerApprovalDate;
    }
    public void    setMakerApprovalDate(String    makerApprovalDate){
        this.makerApprovalDate = this.omcConvertStr2Date(makerApprovalDate);
    }
    public Date getTransferDate(){
        return transferDate;
    }
    public String getMakerCode(){
        return makerCode;
    }
    public String getMakerNameLocal(){
        return makerNameLocal;
    }
    public String getMakerNameEng(){
        return makerNameEng;
    }
    public String getMakerBizRegisterNo(){
        return makerBizRegisterNo;
    }
    public String getMakerCountryCode(){
        return makerCountryCode;
    }
    public String getMakerCity(){
        return makerCity;
    }
    public String getMakerAddress(){
        return makerAddress;
    }
    public String getMakerRemark(){
        return makerRemark;
    }
    public String getMakerStatusCode(){
        return makerStatusCode;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getRepMakerCode(){
        return repMakerCode;
    }
    public String getMakerRequesterId(){
        return makerRequesterId;
    }
    public String getMakerRequesterDept(){
        return makerRequesterDept;
    }
    public String getMakerRequestDate(){
        return makerRequestDate;
    }
    public String getMakerApprovalId(){
        return makerApprovalId;
    }
    public Date getMakerApprovalDate(){
        return makerApprovalDate;
    }
}

