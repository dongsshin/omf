/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : RevisedProjectsVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class RevisedProjectsVO extends BusinessObjectVO {
    private String        designProjectClass                                ;
    private String        projectGradeCode                                  ;
    private Date          projectStartDate                                  ;
    private String        requestor                                         ;
    private String        newPlanPredictionYmd                              ;
    private String        designProjectCategory                             ;
    private Date          projectEndDate                                    ;
    private String        leaderEmployeeNo                                  ;
    private String        designItem                                        ;
    private String        teamCode                                          ;
    private String        designProjectTypeCode                             ;
    private String        pcSwYn                                            ;
    private String        designProjectGradeCode                            ;
    private String        processTypeCode                                   ;
    private String        currentEventCode                                  ;
    private String        upItemCode                                        ;
    private String        designPrecedeTypeCode                             ;
    private String        projectStatusCode                                 ;
    private String        b2bProjectCode                                    ;
    private String        modelName                                         ;
    private String        itemCode                                          ;
    private String        designOutsourcingCode                             ;
    private String        overview                                          ;
    private String        refProjectActivity                                ;
    private String        drawingTransferFlag                               ;
    private String        techProjectTypeCode                               ;
    private String        techDevelopTypeCode                               ;
    private String        commentConcept                                    ;
    private String        commentRendering                                  ;
    private String        transferQuarter                                   ;
    private String        commentMockup                                     ;
    private String        commercializeQuarter                              ;
    private String        needs                                             ;
    private String        commentFollowup                                   ;
    private String        coworkListCode                                    ;
    private String        approach                                          ;
    private String        benefit                                           ;
    private String        commonProjectCode                                 ;
    private String        competition                                       ;
    private String        researchDeptCode                                  ;
    private String        requirement                                       ;
    private String        techCategoryCode                                  ;
    private String        innovationItemType                                ;
    private String        continuityCode                                    ;
    private String        portfolioCode                                     ;
    private String        keyItemType                                       ;
    private String        repProjectName                                    ;
    private String        shortTermPlan                                     ;
    private String        royaltyTypeCode                                   ;
    private String        longTermPlan                                      ;
    private String        prmTrmApplyCode                                   ;
    private String        counterpartPlEmpNo                                ;
    private String        techProjectStageCode                              ;
    private String        techBusinessUnitCode                              ;
    private String        initialProjectFlag                                 = "N";
    private String        lgPriCode                                         ;
    private String        companySalesType                                  ;
    private String        countrySalesType                                  ;
    private String        b2bBillToName                                     ;
    private String        b2bCurrency                                       ;
    private String        poNo                                              ;
    private String        poPartNo                                          ;
    private String        poExpenseType                                     ;
    private String        mesProjectCode                                    ;
    private String        developAmount                                     ;
    private String        involvedOrgCode                                   ;
    private String        changeType                                        ;


    public void    setDesignProjectClass(String designProjectClass){
        this.designProjectClass = designProjectClass;
    }
    public void    setProjectGradeCode(String projectGradeCode){
        this.projectGradeCode = projectGradeCode;
    }
    public void    setProjectStartDate(Date projectStartDate){
        this.projectStartDate = projectStartDate;
    }
    public void    setProjectStartDate(String    projectStartDate){
        this.projectStartDate = this.omcConvertStr2Date(projectStartDate);
    }
    public void    setRequestor(String requestor){
        this.requestor = requestor;
    }
    public void    setNewPlanPredictionYmd(String newPlanPredictionYmd){
        this.newPlanPredictionYmd = newPlanPredictionYmd;
    }
    public void    setDesignProjectCategory(String designProjectCategory){
        this.designProjectCategory = designProjectCategory;
    }
    public void    setProjectEndDate(Date projectEndDate){
        this.projectEndDate = projectEndDate;
    }
    public void    setProjectEndDate(String    projectEndDate){
        this.projectEndDate = this.omcConvertStr2Date(projectEndDate);
    }
    public void    setLeaderEmployeeNo(String leaderEmployeeNo){
        this.leaderEmployeeNo = leaderEmployeeNo;
    }
    public void    setDesignItem(String designItem){
        this.designItem = designItem;
    }
    public void    setTeamCode(String teamCode){
        this.teamCode = teamCode;
    }
    public void    setDesignProjectTypeCode(String designProjectTypeCode){
        this.designProjectTypeCode = designProjectTypeCode;
    }
    public void    setPcSwYn(String pcSwYn){
        this.pcSwYn = pcSwYn;
    }
    public void    setDesignProjectGradeCode(String designProjectGradeCode){
        this.designProjectGradeCode = designProjectGradeCode;
    }
    public void    setProcessTypeCode(String processTypeCode){
        this.processTypeCode = processTypeCode;
    }
    public void    setCurrentEventCode(String currentEventCode){
        this.currentEventCode = currentEventCode;
    }
    public void    setUpItemCode(String upItemCode){
        this.upItemCode = upItemCode;
    }
    public void    setDesignPrecedeTypeCode(String designPrecedeTypeCode){
        this.designPrecedeTypeCode = designPrecedeTypeCode;
    }
    public void    setProjectStatusCode(String projectStatusCode){
        this.projectStatusCode = projectStatusCode;
    }
    public void    setB2bProjectCode(String b2bProjectCode){
        this.b2bProjectCode = b2bProjectCode;
    }
    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setItemCode(String itemCode){
        this.itemCode = itemCode;
    }
    public void    setDesignOutsourcingCode(String designOutsourcingCode){
        this.designOutsourcingCode = designOutsourcingCode;
    }
    public void    setOverview(String overview){
        this.overview = overview;
    }
    public void    setRefProjectActivity(String refProjectActivity){
        this.refProjectActivity = refProjectActivity;
    }
    public void    setDrawingTransferFlag(String drawingTransferFlag){
        this.drawingTransferFlag = drawingTransferFlag;
    }
    public void    setTechProjectTypeCode(String techProjectTypeCode){
        this.techProjectTypeCode = techProjectTypeCode;
    }
    public void    setTechDevelopTypeCode(String techDevelopTypeCode){
        this.techDevelopTypeCode = techDevelopTypeCode;
    }
    public void    setCommentConcept(String commentConcept){
        this.commentConcept = commentConcept;
    }
    public void    setCommentRendering(String commentRendering){
        this.commentRendering = commentRendering;
    }
    public void    setTransferQuarter(String transferQuarter){
        this.transferQuarter = transferQuarter;
    }
    public void    setCommentMockup(String commentMockup){
        this.commentMockup = commentMockup;
    }
    public void    setCommercializeQuarter(String commercializeQuarter){
        this.commercializeQuarter = commercializeQuarter;
    }
    public void    setNeeds(String needs){
        this.needs = needs;
    }
    public void    setCommentFollowup(String commentFollowup){
        this.commentFollowup = commentFollowup;
    }
    public void    setCoworkListCode(String coworkListCode){
        this.coworkListCode = coworkListCode;
    }
    public void    setApproach(String approach){
        this.approach = approach;
    }
    public void    setBenefit(String benefit){
        this.benefit = benefit;
    }
    public void    setCommonProjectCode(String commonProjectCode){
        this.commonProjectCode = commonProjectCode;
    }
    public void    setCompetition(String competition){
        this.competition = competition;
    }
    public void    setResearchDeptCode(String researchDeptCode){
        this.researchDeptCode = researchDeptCode;
    }
    public void    setRequirement(String requirement){
        this.requirement = requirement;
    }
    public void    setTechCategoryCode(String techCategoryCode){
        this.techCategoryCode = techCategoryCode;
    }
    public void    setInnovationItemType(String innovationItemType){
        this.innovationItemType = innovationItemType;
    }
    public void    setContinuityCode(String continuityCode){
        this.continuityCode = continuityCode;
    }
    public void    setPortfolioCode(String portfolioCode){
        this.portfolioCode = portfolioCode;
    }
    public void    setKeyItemType(String keyItemType){
        this.keyItemType = keyItemType;
    }
    public void    setRepProjectName(String repProjectName){
        this.repProjectName = repProjectName;
    }
    public void    setShortTermPlan(String shortTermPlan){
        this.shortTermPlan = shortTermPlan;
    }
    public void    setRoyaltyTypeCode(String royaltyTypeCode){
        this.royaltyTypeCode = royaltyTypeCode;
    }
    public void    setLongTermPlan(String longTermPlan){
        this.longTermPlan = longTermPlan;
    }
    public void    setPrmTrmApplyCode(String prmTrmApplyCode){
        this.prmTrmApplyCode = prmTrmApplyCode;
    }
    public void    setCounterpartPlEmpNo(String counterpartPlEmpNo){
        this.counterpartPlEmpNo = counterpartPlEmpNo;
    }
    public void    setTechProjectStageCode(String techProjectStageCode){
        this.techProjectStageCode = techProjectStageCode;
    }
    public void    setTechBusinessUnitCode(String techBusinessUnitCode){
        this.techBusinessUnitCode = techBusinessUnitCode;
    }
    public void    setInitialProjectFlag(String initialProjectFlag){
        this.initialProjectFlag = initialProjectFlag;
    }
    public void    setLgPriCode(String lgPriCode){
        this.lgPriCode = lgPriCode;
    }
    public void    setCompanySalesType(String companySalesType){
        this.companySalesType = companySalesType;
    }
    public void    setCountrySalesType(String countrySalesType){
        this.countrySalesType = countrySalesType;
    }
    public void    setB2bBillToName(String b2bBillToName){
        this.b2bBillToName = b2bBillToName;
    }
    public void    setB2bCurrency(String b2bCurrency){
        this.b2bCurrency = b2bCurrency;
    }
    public void    setPoNo(String poNo){
        this.poNo = poNo;
    }
    public void    setPoPartNo(String poPartNo){
        this.poPartNo = poPartNo;
    }
    public void    setPoExpenseType(String poExpenseType){
        this.poExpenseType = poExpenseType;
    }
    public void    setMesProjectCode(String mesProjectCode){
        this.mesProjectCode = mesProjectCode;
    }
    public void    setDevelopAmount(String developAmount){
        this.developAmount = developAmount;
    }
    public void    setInvolvedOrgCode(String involvedOrgCode){
        this.involvedOrgCode = involvedOrgCode;
    }
    public void    setChangeType(String changeType){
        this.changeType = changeType;
    }
    public String getDesignProjectClass(){
        return designProjectClass;
    }
    public String getProjectGradeCode(){
        return projectGradeCode;
    }
    public Date getProjectStartDate(){
        return projectStartDate;
    }
    public String getRequestor(){
        return requestor;
    }
    public String getNewPlanPredictionYmd(){
        return newPlanPredictionYmd;
    }
    public String getDesignProjectCategory(){
        return designProjectCategory;
    }
    public Date getProjectEndDate(){
        return projectEndDate;
    }
    public String getLeaderEmployeeNo(){
        return leaderEmployeeNo;
    }
    public String getDesignItem(){
        return designItem;
    }
    public String getTeamCode(){
        return teamCode;
    }
    public String getDesignProjectTypeCode(){
        return designProjectTypeCode;
    }
    public String getPcSwYn(){
        return pcSwYn;
    }
    public String getDesignProjectGradeCode(){
        return designProjectGradeCode;
    }
    public String getProcessTypeCode(){
        return processTypeCode;
    }
    public String getCurrentEventCode(){
        return currentEventCode;
    }
    public String getUpItemCode(){
        return upItemCode;
    }
    public String getDesignPrecedeTypeCode(){
        return designPrecedeTypeCode;
    }
    public String getProjectStatusCode(){
        return projectStatusCode;
    }
    public String getB2bProjectCode(){
        return b2bProjectCode;
    }
    public String getModelName(){
        return modelName;
    }
    public String getItemCode(){
        return itemCode;
    }
    public String getDesignOutsourcingCode(){
        return designOutsourcingCode;
    }
    public String getOverview(){
        return overview;
    }
    public String getRefProjectActivity(){
        return refProjectActivity;
    }
    public String getDrawingTransferFlag(){
        return drawingTransferFlag;
    }
    public String getTechProjectTypeCode(){
        return techProjectTypeCode;
    }
    public String getTechDevelopTypeCode(){
        return techDevelopTypeCode;
    }
    public String getCommentConcept(){
        return commentConcept;
    }
    public String getCommentRendering(){
        return commentRendering;
    }
    public String getTransferQuarter(){
        return transferQuarter;
    }
    public String getCommentMockup(){
        return commentMockup;
    }
    public String getCommercializeQuarter(){
        return commercializeQuarter;
    }
    public String getNeeds(){
        return needs;
    }
    public String getCommentFollowup(){
        return commentFollowup;
    }
    public String getCoworkListCode(){
        return coworkListCode;
    }
    public String getApproach(){
        return approach;
    }
    public String getBenefit(){
        return benefit;
    }
    public String getCommonProjectCode(){
        return commonProjectCode;
    }
    public String getCompetition(){
        return competition;
    }
    public String getResearchDeptCode(){
        return researchDeptCode;
    }
    public String getRequirement(){
        return requirement;
    }
    public String getTechCategoryCode(){
        return techCategoryCode;
    }
    public String getInnovationItemType(){
        return innovationItemType;
    }
    public String getContinuityCode(){
        return continuityCode;
    }
    public String getPortfolioCode(){
        return portfolioCode;
    }
    public String getKeyItemType(){
        return keyItemType;
    }
    public String getRepProjectName(){
        return repProjectName;
    }
    public String getShortTermPlan(){
        return shortTermPlan;
    }
    public String getRoyaltyTypeCode(){
        return royaltyTypeCode;
    }
    public String getLongTermPlan(){
        return longTermPlan;
    }
    public String getPrmTrmApplyCode(){
        return prmTrmApplyCode;
    }
    public String getCounterpartPlEmpNo(){
        return counterpartPlEmpNo;
    }
    public String getTechProjectStageCode(){
        return techProjectStageCode;
    }
    public String getTechBusinessUnitCode(){
        return techBusinessUnitCode;
    }
    public String getInitialProjectFlag(){
        return initialProjectFlag;
    }
    public String getLgPriCode(){
        return lgPriCode;
    }
    public String getCompanySalesType(){
        return companySalesType;
    }
    public String getCountrySalesType(){
        return countrySalesType;
    }
    public String getB2bBillToName(){
        return b2bBillToName;
    }
    public String getB2bCurrency(){
        return b2bCurrency;
    }
    public String getPoNo(){
        return poNo;
    }
    public String getPoPartNo(){
        return poPartNo;
    }
    public String getPoExpenseType(){
        return poExpenseType;
    }
    public String getMesProjectCode(){
        return mesProjectCode;
    }
    public String getDevelopAmount(){
        return developAmount;
    }
    public String getInvolvedOrgCode(){
        return involvedOrgCode;
    }
    public String getChangeType(){
        return changeType;
    }
}

