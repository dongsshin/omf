/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : GBMSInvestPjtVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class GBMSInvestPjtVO extends BusinessObjectMasterVO {
    private String        periodYyyy                                        ;
    private String        projectCode                                       ;
    private String        projectLocalName                                  ;
    private String        projectEngName                                    ;
    private String        investCode                                        ;
    private Integer       investSeqNo                                       ;
    private String        assetTypeCode                                     ;
    private String        assetTypeEngName                                  ;
    private String        assetTypeLocalName                                ;
    private String        orderYyyymm                                       ;
    private String        receivingYyyymm                                   ;
    private String        additionalFlag                                    ;
    private String        selfObjectiveCode                                 ;
    private String        investObjectiveCode                               ;
    private String        enabledFlag                                       ;
    private String        expProjectCode                                    ;
    private String        invTypeFg                                         ;
    private String        legalEntityName                                   ;
    private String        executionDepartmentCode                           ;
    private String        investSectorCode                                  ;
    private String        projectGroupCode                                  ;
    private String        projectSubGroupCode                               ;
    private String        majorCategoryCode                                 ;
    private String        minorCategoryCode                                 ;
    private String        accountingUnitCode                                ;


    public void    setPeriodYyyy(String periodYyyy){
        this.periodYyyy = periodYyyy;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setProjectLocalName(String projectLocalName){
        this.projectLocalName = projectLocalName;
    }
    public void    setProjectEngName(String projectEngName){
        this.projectEngName = projectEngName;
    }
    public void    setInvestCode(String investCode){
        this.investCode = investCode;
    }
    public void    setInvestSeqNo(Integer investSeqNo){
        this.investSeqNo = investSeqNo;
    }
    public void    setAssetTypeCode(String assetTypeCode){
        this.assetTypeCode = assetTypeCode;
    }
    public void    setAssetTypeEngName(String assetTypeEngName){
        this.assetTypeEngName = assetTypeEngName;
    }
    public void    setAssetTypeLocalName(String assetTypeLocalName){
        this.assetTypeLocalName = assetTypeLocalName;
    }
    public void    setOrderYyyymm(String orderYyyymm){
        this.orderYyyymm = orderYyyymm;
    }
    public void    setReceivingYyyymm(String receivingYyyymm){
        this.receivingYyyymm = receivingYyyymm;
    }
    public void    setAdditionalFlag(String additionalFlag){
        this.additionalFlag = additionalFlag;
    }
    public void    setSelfObjectiveCode(String selfObjectiveCode){
        this.selfObjectiveCode = selfObjectiveCode;
    }
    public void    setInvestObjectiveCode(String investObjectiveCode){
        this.investObjectiveCode = investObjectiveCode;
    }
    public void    setEnabledFlag(String enabledFlag){
        this.enabledFlag = enabledFlag;
    }
    public void    setExpProjectCode(String expProjectCode){
        this.expProjectCode = expProjectCode;
    }
    public void    setInvTypeFg(String invTypeFg){
        this.invTypeFg = invTypeFg;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setExecutionDepartmentCode(String executionDepartmentCode){
        this.executionDepartmentCode = executionDepartmentCode;
    }
    public void    setInvestSectorCode(String investSectorCode){
        this.investSectorCode = investSectorCode;
    }
    public void    setProjectGroupCode(String projectGroupCode){
        this.projectGroupCode = projectGroupCode;
    }
    public void    setProjectSubGroupCode(String projectSubGroupCode){
        this.projectSubGroupCode = projectSubGroupCode;
    }
    public void    setMajorCategoryCode(String majorCategoryCode){
        this.majorCategoryCode = majorCategoryCode;
    }
    public void    setMinorCategoryCode(String minorCategoryCode){
        this.minorCategoryCode = minorCategoryCode;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public String getPeriodYyyy(){
        return periodYyyy;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getProjectLocalName(){
        return projectLocalName;
    }
    public String getProjectEngName(){
        return projectEngName;
    }
    public String getInvestCode(){
        return investCode;
    }
    public Integer getInvestSeqNo(){
        return investSeqNo;
    }
    public String getAssetTypeCode(){
        return assetTypeCode;
    }
    public String getAssetTypeEngName(){
        return assetTypeEngName;
    }
    public String getAssetTypeLocalName(){
        return assetTypeLocalName;
    }
    public String getOrderYyyymm(){
        return orderYyyymm;
    }
    public String getReceivingYyyymm(){
        return receivingYyyymm;
    }
    public String getAdditionalFlag(){
        return additionalFlag;
    }
    public String getSelfObjectiveCode(){
        return selfObjectiveCode;
    }
    public String getInvestObjectiveCode(){
        return investObjectiveCode;
    }
    public String getEnabledFlag(){
        return enabledFlag;
    }
    public String getExpProjectCode(){
        return expProjectCode;
    }
    public String getInvTypeFg(){
        return invTypeFg;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getExecutionDepartmentCode(){
        return executionDepartmentCode;
    }
    public String getInvestSectorCode(){
        return investSectorCode;
    }
    public String getProjectGroupCode(){
        return projectGroupCode;
    }
    public String getProjectSubGroupCode(){
        return projectSubGroupCode;
    }
    public String getMajorCategoryCode(){
        return majorCategoryCode;
    }
    public String getMinorCategoryCode(){
        return minorCategoryCode;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
}

