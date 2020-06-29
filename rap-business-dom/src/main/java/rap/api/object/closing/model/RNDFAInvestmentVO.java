/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : RNDFAInvestmentVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.closing.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class RNDFAInvestmentVO extends BusinessObjectMasterVO {
    private String        closingYm                                         ;
    private String        legalEntityName                                   ;
    private String        pAccountingUnitCode                               ;
    private String        pDepartmentCode                                   ;
    private String        faTAccountingUnitCode                             ;
    private String        faTDepartmentCode                                 ;
    private String        faAccountingUnitCode                              ;
    private String        faDepartmentCode                                  ;
    private String        feederSystemName                                  ;
    private Integer       massAdditionId                                    ;
    private String        assetRegisterTypeCode                             ;
    private String        bookTypeCode                                      ;
    private Date          accountingDate                                    ;
    private String        acquisitionTypeCode                               ;
    private String        assetTypeCode                                     ;
    private String        serialNo                                          ;
    private String        tagNo                                             ;
    private String        assetNo                                           ;
    private String        assetName                                         ;
    private Integer       assetQty                                          ;
    private BigDecimal    fixedAssetsCost                                    = new BigDecimal(0);
    private String        majorCategoryCode                                 ;
    private String        projectCode                                       ;
    private String        comments                                          ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        moveFg                                             = "N";
    private String        delYn                                              = "N";
    private Integer       invHeaderId                                       ;
    private Integer       invLineId                                         ;


    public void    setClosingYm(String closingYm){
        this.closingYm = closingYm;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setPAccountingUnitCode(String pAccountingUnitCode){
        this.pAccountingUnitCode = pAccountingUnitCode;
    }
    public void    setPDepartmentCode(String pDepartmentCode){
        this.pDepartmentCode = pDepartmentCode;
    }
    public void    setFaTAccountingUnitCode(String faTAccountingUnitCode){
        this.faTAccountingUnitCode = faTAccountingUnitCode;
    }
    public void    setFaTDepartmentCode(String faTDepartmentCode){
        this.faTDepartmentCode = faTDepartmentCode;
    }
    public void    setFaAccountingUnitCode(String faAccountingUnitCode){
        this.faAccountingUnitCode = faAccountingUnitCode;
    }
    public void    setFaDepartmentCode(String faDepartmentCode){
        this.faDepartmentCode = faDepartmentCode;
    }
    public void    setFeederSystemName(String feederSystemName){
        this.feederSystemName = feederSystemName;
    }
    public void    setMassAdditionId(Integer massAdditionId){
        this.massAdditionId = massAdditionId;
    }
    public void    setAssetRegisterTypeCode(String assetRegisterTypeCode){
        this.assetRegisterTypeCode = assetRegisterTypeCode;
    }
    public void    setBookTypeCode(String bookTypeCode){
        this.bookTypeCode = bookTypeCode;
    }
    public void    setAccountingDate(Date accountingDate){
        this.accountingDate = accountingDate;
    }
    public void    setAccountingDate(String    accountingDate){
        this.accountingDate = this.omcConvertStr2Date(accountingDate);
    }
    public void    setAcquisitionTypeCode(String acquisitionTypeCode){
        this.acquisitionTypeCode = acquisitionTypeCode;
    }
    public void    setAssetTypeCode(String assetTypeCode){
        this.assetTypeCode = assetTypeCode;
    }
    public void    setSerialNo(String serialNo){
        this.serialNo = serialNo;
    }
    public void    setTagNo(String tagNo){
        this.tagNo = tagNo;
    }
    public void    setAssetNo(String assetNo){
        this.assetNo = assetNo;
    }
    public void    setAssetName(String assetName){
        this.assetName = assetName;
    }
    public void    setAssetQty(Integer assetQty){
        this.assetQty = assetQty;
    }
    public void    setFixedAssetsCost(BigDecimal fixedAssetsCost){
        this.fixedAssetsCost = fixedAssetsCost;
    }
    public void    setMajorCategoryCode(String majorCategoryCode){
        this.majorCategoryCode = majorCategoryCode;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public void    setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
    public void    setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
    public void    setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }
    public void    setMoveFg(String moveFg){
        this.moveFg = moveFg;
    }
    public void    setDelYn(String delYn){
        this.delYn = delYn;
    }
    public void    setInvHeaderId(Integer invHeaderId){
        this.invHeaderId = invHeaderId;
    }
    public void    setInvLineId(Integer invLineId){
        this.invLineId = invLineId;
    }
    public String getClosingYm(){
        return closingYm;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getPAccountingUnitCode(){
        return pAccountingUnitCode;
    }
    public String getPDepartmentCode(){
        return pDepartmentCode;
    }
    public String getFaTAccountingUnitCode(){
        return faTAccountingUnitCode;
    }
    public String getFaTDepartmentCode(){
        return faTDepartmentCode;
    }
    public String getFaAccountingUnitCode(){
        return faAccountingUnitCode;
    }
    public String getFaDepartmentCode(){
        return faDepartmentCode;
    }
    public String getFeederSystemName(){
        return feederSystemName;
    }
    public Integer getMassAdditionId(){
        return massAdditionId;
    }
    public String getAssetRegisterTypeCode(){
        return assetRegisterTypeCode;
    }
    public String getBookTypeCode(){
        return bookTypeCode;
    }
    public Date getAccountingDate(){
        return accountingDate;
    }
    public String getAcquisitionTypeCode(){
        return acquisitionTypeCode;
    }
    public String getAssetTypeCode(){
        return assetTypeCode;
    }
    public String getSerialNo(){
        return serialNo;
    }
    public String getTagNo(){
        return tagNo;
    }
    public String getAssetNo(){
        return assetNo;
    }
    public String getAssetName(){
        return assetName;
    }
    public Integer getAssetQty(){
        return assetQty;
    }
    public BigDecimal getFixedAssetsCost(){
        return fixedAssetsCost;
    }
    public String getMajorCategoryCode(){
        return majorCategoryCode;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getComments(){
        return comments;
    }
    public String getAttribute1(){
        return attribute1;
    }
    public String getAttribute2(){
        return attribute2;
    }
    public String getAttribute3(){
        return attribute3;
    }
    public String getAttribute4(){
        return attribute4;
    }
    public String getAttribute5(){
        return attribute5;
    }
    public String getMoveFg(){
        return moveFg;
    }
    public String getDelYn(){
        return delYn;
    }
    public Integer getInvHeaderId(){
        return invHeaderId;
    }
    public Integer getInvLineId(){
        return invLineId;
    }
}

