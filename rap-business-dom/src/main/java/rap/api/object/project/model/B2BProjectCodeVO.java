/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : B2BProjectCodeVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class B2BProjectCodeVO extends BusinessObjectMasterVO {
    private String        areaCode                                          ;
    private String        b2bProjectCode                                    ;
    private String        b2bProjectNameKor                                 ;
    private String        b2bProjectNameEng                                 ;
    private Date          startDate                                         ;
    private Date          endDate                                           ;
    private String        accountUnit                                       ;
    private String        divisionCode                                      ;
    private String        useYn                                             ;
    private String        costProfitType                                    ;
    private String        b2bYn                                             ;
    private String        projectStatus                                     ;
    private String        b2bType                                           ;
    private String        b2bBillToName                                     ;
    private String        b2bCurrency                                       ;
    private String        lgPriCode                                         ;
    private String        developAmount                                     ;
    private String        projectLeader                                     ;
    private String        salesOwner                                        ;
    private String        hrDeptCode                                        ;
    private String        awardYymmdd                                       ;
    private String        pjtAwardType                                      ;
    private String        modelName                                         ;
    private String        ctTypeMainCd                                      ;
    private String        ctTypeSubCd                                       ;
    private String        contractYn                                         = "N";
    private String        contractYyyymm                                    ;


    public void    setAreaCode(String areaCode){
        this.areaCode = areaCode;
    }
    public void    setB2bProjectCode(String b2bProjectCode){
        this.b2bProjectCode = b2bProjectCode;
    }
    public void    setB2bProjectNameKor(String b2bProjectNameKor){
        this.b2bProjectNameKor = b2bProjectNameKor;
    }
    public void    setB2bProjectNameEng(String b2bProjectNameEng){
        this.b2bProjectNameEng = b2bProjectNameEng;
    }
    public void    setStartDate(Date startDate){
        this.startDate = startDate;
    }
    public void    setStartDate(String    startDate){
        this.startDate = this.omcConvertStr2Date(startDate);
    }
    public void    setEndDate(Date endDate){
        this.endDate = endDate;
    }
    public void    setEndDate(String    endDate){
        this.endDate = this.omcConvertStr2Date(endDate);
    }
    public void    setAccountUnit(String accountUnit){
        this.accountUnit = accountUnit;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setCostProfitType(String costProfitType){
        this.costProfitType = costProfitType;
    }
    public void    setB2bYn(String b2bYn){
        this.b2bYn = b2bYn;
    }
    public void    setProjectStatus(String projectStatus){
        this.projectStatus = projectStatus;
    }
    public void    setB2bType(String b2bType){
        this.b2bType = b2bType;
    }
    public void    setB2bBillToName(String b2bBillToName){
        this.b2bBillToName = b2bBillToName;
    }
    public void    setB2bCurrency(String b2bCurrency){
        this.b2bCurrency = b2bCurrency;
    }
    public void    setLgPriCode(String lgPriCode){
        this.lgPriCode = lgPriCode;
    }
    public void    setDevelopAmount(String developAmount){
        this.developAmount = developAmount;
    }
    public void    setProjectLeader(String projectLeader){
        this.projectLeader = projectLeader;
    }
    public void    setSalesOwner(String salesOwner){
        this.salesOwner = salesOwner;
    }
    public void    setHrDeptCode(String hrDeptCode){
        this.hrDeptCode = hrDeptCode;
    }
    public void    setAwardYymmdd(String awardYymmdd){
        this.awardYymmdd = awardYymmdd;
    }
    public void    setPjtAwardType(String pjtAwardType){
        this.pjtAwardType = pjtAwardType;
    }
    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setCtTypeMainCd(String ctTypeMainCd){
        this.ctTypeMainCd = ctTypeMainCd;
    }
    public void    setCtTypeSubCd(String ctTypeSubCd){
        this.ctTypeSubCd = ctTypeSubCd;
    }
    public void    setContractYn(String contractYn){
        this.contractYn = contractYn;
    }
    public void    setContractYyyymm(String contractYyyymm){
        this.contractYyyymm = contractYyyymm;
    }
    public String getAreaCode(){
        return areaCode;
    }
    public String getB2bProjectCode(){
        return b2bProjectCode;
    }
    public String getB2bProjectNameKor(){
        return b2bProjectNameKor;
    }
    public String getB2bProjectNameEng(){
        return b2bProjectNameEng;
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public String getAccountUnit(){
        return accountUnit;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getCostProfitType(){
        return costProfitType;
    }
    public String getB2bYn(){
        return b2bYn;
    }
    public String getProjectStatus(){
        return projectStatus;
    }
    public String getB2bType(){
        return b2bType;
    }
    public String getB2bBillToName(){
        return b2bBillToName;
    }
    public String getB2bCurrency(){
        return b2bCurrency;
    }
    public String getLgPriCode(){
        return lgPriCode;
    }
    public String getDevelopAmount(){
        return developAmount;
    }
    public String getProjectLeader(){
        return projectLeader;
    }
    public String getSalesOwner(){
        return salesOwner;
    }
    public String getHrDeptCode(){
        return hrDeptCode;
    }
    public String getAwardYymmdd(){
        return awardYymmdd;
    }
    public String getPjtAwardType(){
        return pjtAwardType;
    }
    public String getModelName(){
        return modelName;
    }
    public String getCtTypeMainCd(){
        return ctTypeMainCd;
    }
    public String getCtTypeSubCd(){
        return ctTypeSubCd;
    }
    public String getContractYn(){
        return contractYn;
    }
    public String getContractYyyymm(){
        return contractYyyymm;
    }
}

