/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesignRequestsVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.requests.model;


import rap.api.object.requests.model.DesignProcessVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesignRequestsVO extends DesignProcessVO {
    private String        requestStatus                                     ;
    private String        approvalStatus                                    ;
    private String        approvalCode                                      ;
    private String        labCode                                           ;
    private String        requestCategoryCode                               ;
    private String        requestItemCode                                   ;
    private String        upItemCode                                        ;
    private String        itemCode                                          ;
    private String        recipientOrgCode                                  ;
    private String        recipientEmployeeNo                               ;
    private String        pmsProjectCode                                    ;
    private String        pmsProjectName                                    ;
    private String        productName                                       ;
    private String        productCode                                       ;
    private String        baseModel                                         ;
    private String        competitionModel                                  ;
    private String        competitionRefModel                               ;
    private String        goalAmount                                        ;
    private String        uiBaseModel                                       ;
    private String        developmentLevel                                  ;
    private String        targetCost                                        ;
    private String        goalUnitAmount                                    ;
    private String        goalPackageAmount                                 ;
    private String        bankCode                                          ;
    private String        developmentGoal                                   ;
    private String        targetMarket                                      ;
    private String        targetGrade                                       ;
    private String        targetCustomer                                    ;
    private String        requestType                                       ;
    private String        majorComment                                      ;
    private String        contribution                                      ;


    public void    setRequestStatus(String requestStatus){
        this.requestStatus = requestStatus;
    }
    public void    setApprovalStatus(String approvalStatus){
        this.approvalStatus = approvalStatus;
    }
    public void    setApprovalCode(String approvalCode){
        this.approvalCode = approvalCode;
    }
    public void    setLabCode(String labCode){
        this.labCode = labCode;
    }
    public void    setRequestCategoryCode(String requestCategoryCode){
        this.requestCategoryCode = requestCategoryCode;
    }
    public void    setRequestItemCode(String requestItemCode){
        this.requestItemCode = requestItemCode;
    }
    public void    setUpItemCode(String upItemCode){
        this.upItemCode = upItemCode;
    }
    public void    setItemCode(String itemCode){
        this.itemCode = itemCode;
    }
    public void    setRecipientOrgCode(String recipientOrgCode){
        this.recipientOrgCode = recipientOrgCode;
    }
    public void    setRecipientEmployeeNo(String recipientEmployeeNo){
        this.recipientEmployeeNo = recipientEmployeeNo;
    }
    public void    setPmsProjectCode(String pmsProjectCode){
        this.pmsProjectCode = pmsProjectCode;
    }
    public void    setPmsProjectName(String pmsProjectName){
        this.pmsProjectName = pmsProjectName;
    }
    public void    setProductName(String productName){
        this.productName = productName;
    }
    public void    setProductCode(String productCode){
        this.productCode = productCode;
    }
    public void    setBaseModel(String baseModel){
        this.baseModel = baseModel;
    }
    public void    setCompetitionModel(String competitionModel){
        this.competitionModel = competitionModel;
    }
    public void    setCompetitionRefModel(String competitionRefModel){
        this.competitionRefModel = competitionRefModel;
    }
    public void    setGoalAmount(String goalAmount){
        this.goalAmount = goalAmount;
    }
    public void    setUiBaseModel(String uiBaseModel){
        this.uiBaseModel = uiBaseModel;
    }
    public void    setDevelopmentLevel(String developmentLevel){
        this.developmentLevel = developmentLevel;
    }
    public void    setTargetCost(String targetCost){
        this.targetCost = targetCost;
    }
    public void    setGoalUnitAmount(String goalUnitAmount){
        this.goalUnitAmount = goalUnitAmount;
    }
    public void    setGoalPackageAmount(String goalPackageAmount){
        this.goalPackageAmount = goalPackageAmount;
    }
    public void    setBankCode(String bankCode){
        this.bankCode = bankCode;
    }
    public void    setDevelopmentGoal(String developmentGoal){
        this.developmentGoal = developmentGoal;
    }
    public void    setTargetMarket(String targetMarket){
        this.targetMarket = targetMarket;
    }
    public void    setTargetGrade(String targetGrade){
        this.targetGrade = targetGrade;
    }
    public void    setTargetCustomer(String targetCustomer){
        this.targetCustomer = targetCustomer;
    }
    public void    setRequestType(String requestType){
        this.requestType = requestType;
    }
    public void    setMajorComment(String majorComment){
        this.majorComment = majorComment;
    }
    public void    setContribution(String contribution){
        this.contribution = contribution;
    }
    public String getRequestStatus(){
        return requestStatus;
    }
    public String getApprovalStatus(){
        return approvalStatus;
    }
    public String getApprovalCode(){
        return approvalCode;
    }
    public String getLabCode(){
        return labCode;
    }
    public String getRequestCategoryCode(){
        return requestCategoryCode;
    }
    public String getRequestItemCode(){
        return requestItemCode;
    }
    public String getUpItemCode(){
        return upItemCode;
    }
    public String getItemCode(){
        return itemCode;
    }
    public String getRecipientOrgCode(){
        return recipientOrgCode;
    }
    public String getRecipientEmployeeNo(){
        return recipientEmployeeNo;
    }
    public String getPmsProjectCode(){
        return pmsProjectCode;
    }
    public String getPmsProjectName(){
        return pmsProjectName;
    }
    public String getProductName(){
        return productName;
    }
    public String getProductCode(){
        return productCode;
    }
    public String getBaseModel(){
        return baseModel;
    }
    public String getCompetitionModel(){
        return competitionModel;
    }
    public String getCompetitionRefModel(){
        return competitionRefModel;
    }
    public String getGoalAmount(){
        return goalAmount;
    }
    public String getUiBaseModel(){
        return uiBaseModel;
    }
    public String getDevelopmentLevel(){
        return developmentLevel;
    }
    public String getTargetCost(){
        return targetCost;
    }
    public String getGoalUnitAmount(){
        return goalUnitAmount;
    }
    public String getGoalPackageAmount(){
        return goalPackageAmount;
    }
    public String getBankCode(){
        return bankCode;
    }
    public String getDevelopmentGoal(){
        return developmentGoal;
    }
    public String getTargetMarket(){
        return targetMarket;
    }
    public String getTargetGrade(){
        return targetGrade;
    }
    public String getTargetCustomer(){
        return targetCustomer;
    }
    public String getRequestType(){
        return requestType;
    }
    public String getMajorComment(){
        return majorComment;
    }
    public String getContribution(){
        return contribution;
    }
}

