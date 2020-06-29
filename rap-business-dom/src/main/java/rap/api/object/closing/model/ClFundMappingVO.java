/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClFundMappingVO.java
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
public class ClFundMappingVO extends BusinessObjectMasterVO {
    private String        closingYm                                         ;
    private String        managementGroup                                   ;
    private String        legalEntityName                                   ;
    private String        accountingUnitCode                                ;
    private String        departmentCode                                    ;
    private String        projectCode                                       ;
    private Integer       mappingSerialNo                                   ;
    private String        ctoExpenseTypeCd                                  ;
    private String        fundExpenseTypeCd                                 ;
    private String        bizTypeCode                                       ;
    private BigDecimal    distributionRate                                   = new BigDecimal(0);
    private String        useYn                                             ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        attribute6                                        ;
    private String        attribute7                                        ;
    private String        attribute8                                        ;
    private String        attribute9                                        ;
    private String        attribute10                                       ;


    public void    setClosingYm(String closingYm){
        this.closingYm = closingYm;
    }
    public void    setManagementGroup(String managementGroup){
        this.managementGroup = managementGroup;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setMappingSerialNo(Integer mappingSerialNo){
        this.mappingSerialNo = mappingSerialNo;
    }
    public void    setCtoExpenseTypeCd(String ctoExpenseTypeCd){
        this.ctoExpenseTypeCd = ctoExpenseTypeCd;
    }
    public void    setFundExpenseTypeCd(String fundExpenseTypeCd){
        this.fundExpenseTypeCd = fundExpenseTypeCd;
    }
    public void    setBizTypeCode(String bizTypeCode){
        this.bizTypeCode = bizTypeCode;
    }
    public void    setDistributionRate(BigDecimal distributionRate){
        this.distributionRate = distributionRate;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
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
    public void    setAttribute6(String attribute6){
        this.attribute6 = attribute6;
    }
    public void    setAttribute7(String attribute7){
        this.attribute7 = attribute7;
    }
    public void    setAttribute8(String attribute8){
        this.attribute8 = attribute8;
    }
    public void    setAttribute9(String attribute9){
        this.attribute9 = attribute9;
    }
    public void    setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    public String getClosingYm(){
        return closingYm;
    }
    public String getManagementGroup(){
        return managementGroup;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public Integer getMappingSerialNo(){
        return mappingSerialNo;
    }
    public String getCtoExpenseTypeCd(){
        return ctoExpenseTypeCd;
    }
    public String getFundExpenseTypeCd(){
        return fundExpenseTypeCd;
    }
    public String getBizTypeCode(){
        return bizTypeCode;
    }
    public BigDecimal getDistributionRate(){
        return distributionRate;
    }
    public String getUseYn(){
        return useYn;
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
    public String getAttribute6(){
        return attribute6;
    }
    public String getAttribute7(){
        return attribute7;
    }
    public String getAttribute8(){
        return attribute8;
    }
    public String getAttribute9(){
        return attribute9;
    }
    public String getAttribute10(){
        return attribute10;
    }
}

