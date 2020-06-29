/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BizPlanProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BizPlanProjectVO extends BusinessObjectVO {
    private String        planProjectCode                                   ;
    private Date          startDate                                         ;
    private Date          endDate                                           ;
    private String        leaderEmployeeNo                                  ;
    private String        editableFlag                                      ;
    private String        newYn                                             ;
    private String        gradeName                                         ;
    private String        developTypeName                                   ;
    private String        productName                                       ;
    private Date          mpDate                                            ;
    private String        modelName                                         ;
    private String        projectCode                                       ;
    private String        divisionCode                                      ;
    private String        acctDivisionName                                  ;
    private String        marketGradeName                                   ;
    private String        salesRegionName                                   ;
    private String        productionSiteName                                ;
    private String        developSiteName                                   ;
    private String        projectTypeCode                                   ;
    private String        eventCode                                         ;
    private String        processTypeCode                                   ;
    private String        projectGradeCode                                  ;
    private String        developTypeCode                                   ;
    private String        prmTrmApplyCode                                   ;
    private String        techProjectTypeCode                               ;
    private String        techDevelopTypeCode                               ;
    private String        counterpartPlEmpNo                                ;
    private String        transferQuarter                                   ;
    private String        commercializeQuarter                              ;
    private String        overview                                          ;
    private String        needs                                             ;
    private String        approach                                          ;
    private String        benefit                                           ;
    private String        competition                                       ;
    private String        requirement                                       ;
    private String        techCategoryCode                                  ;
    private String        continuityCode                                    ;
    private String        portfolioCode                                     ;
    private String        portfolioDetail                                   ;
    private String        royaltyTypeCode                                   ;
    private String        initialProjectFlag                                ;
    private String        techProjectStageCode                              ;
    private String        planProjectTypeCode                               ;
    private String        techBusinessUnitCode                              ;
    private String        innovationItemType                                ;
    private String        keyItemType                                       ;
    private String        shortTermPlan                                     ;
    private String        longTermPlan                                      ;
    private String        repProjectName                                    ;


    public void    setPlanProjectCode(String planProjectCode){
        this.planProjectCode = planProjectCode;
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
    public void    setLeaderEmployeeNo(String leaderEmployeeNo){
        this.leaderEmployeeNo = leaderEmployeeNo;
    }
    public void    setEditableFlag(String editableFlag){
        this.editableFlag = editableFlag;
    }
    public void    setNewYn(String newYn){
        this.newYn = newYn;
    }
    public void    setGradeName(String gradeName){
        this.gradeName = gradeName;
    }
    public void    setDevelopTypeName(String developTypeName){
        this.developTypeName = developTypeName;
    }
    public void    setProductName(String productName){
        this.productName = productName;
    }
    public void    setMpDate(Date mpDate){
        this.mpDate = mpDate;
    }
    public void    setMpDate(String    mpDate){
        this.mpDate = this.omcConvertStr2Date(mpDate);
    }
    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAcctDivisionName(String acctDivisionName){
        this.acctDivisionName = acctDivisionName;
    }
    public void    setMarketGradeName(String marketGradeName){
        this.marketGradeName = marketGradeName;
    }
    public void    setSalesRegionName(String salesRegionName){
        this.salesRegionName = salesRegionName;
    }
    public void    setProductionSiteName(String productionSiteName){
        this.productionSiteName = productionSiteName;
    }
    public void    setDevelopSiteName(String developSiteName){
        this.developSiteName = developSiteName;
    }
    public void    setProjectTypeCode(String projectTypeCode){
        this.projectTypeCode = projectTypeCode;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setProcessTypeCode(String processTypeCode){
        this.processTypeCode = processTypeCode;
    }
    public void    setProjectGradeCode(String projectGradeCode){
        this.projectGradeCode = projectGradeCode;
    }
    public void    setDevelopTypeCode(String developTypeCode){
        this.developTypeCode = developTypeCode;
    }
    public void    setPrmTrmApplyCode(String prmTrmApplyCode){
        this.prmTrmApplyCode = prmTrmApplyCode;
    }
    public void    setTechProjectTypeCode(String techProjectTypeCode){
        this.techProjectTypeCode = techProjectTypeCode;
    }
    public void    setTechDevelopTypeCode(String techDevelopTypeCode){
        this.techDevelopTypeCode = techDevelopTypeCode;
    }
    public void    setCounterpartPlEmpNo(String counterpartPlEmpNo){
        this.counterpartPlEmpNo = counterpartPlEmpNo;
    }
    public void    setTransferQuarter(String transferQuarter){
        this.transferQuarter = transferQuarter;
    }
    public void    setCommercializeQuarter(String commercializeQuarter){
        this.commercializeQuarter = commercializeQuarter;
    }
    public void    setOverview(String overview){
        this.overview = overview;
    }
    public void    setNeeds(String needs){
        this.needs = needs;
    }
    public void    setApproach(String approach){
        this.approach = approach;
    }
    public void    setBenefit(String benefit){
        this.benefit = benefit;
    }
    public void    setCompetition(String competition){
        this.competition = competition;
    }
    public void    setRequirement(String requirement){
        this.requirement = requirement;
    }
    public void    setTechCategoryCode(String techCategoryCode){
        this.techCategoryCode = techCategoryCode;
    }
    public void    setContinuityCode(String continuityCode){
        this.continuityCode = continuityCode;
    }
    public void    setPortfolioCode(String portfolioCode){
        this.portfolioCode = portfolioCode;
    }
    public void    setPortfolioDetail(String portfolioDetail){
        this.portfolioDetail = portfolioDetail;
    }
    public void    setRoyaltyTypeCode(String royaltyTypeCode){
        this.royaltyTypeCode = royaltyTypeCode;
    }
    public void    setInitialProjectFlag(String initialProjectFlag){
        this.initialProjectFlag = initialProjectFlag;
    }
    public void    setTechProjectStageCode(String techProjectStageCode){
        this.techProjectStageCode = techProjectStageCode;
    }
    public void    setPlanProjectTypeCode(String planProjectTypeCode){
        this.planProjectTypeCode = planProjectTypeCode;
    }
    public void    setTechBusinessUnitCode(String techBusinessUnitCode){
        this.techBusinessUnitCode = techBusinessUnitCode;
    }
    public void    setInnovationItemType(String innovationItemType){
        this.innovationItemType = innovationItemType;
    }
    public void    setKeyItemType(String keyItemType){
        this.keyItemType = keyItemType;
    }
    public void    setShortTermPlan(String shortTermPlan){
        this.shortTermPlan = shortTermPlan;
    }
    public void    setLongTermPlan(String longTermPlan){
        this.longTermPlan = longTermPlan;
    }
    public void    setRepProjectName(String repProjectName){
        this.repProjectName = repProjectName;
    }
    public String getPlanProjectCode(){
        return planProjectCode;
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public String getLeaderEmployeeNo(){
        return leaderEmployeeNo;
    }
    public String getEditableFlag(){
        return editableFlag;
    }
    public String getNewYn(){
        return newYn;
    }
    public String getGradeName(){
        return gradeName;
    }
    public String getDevelopTypeName(){
        return developTypeName;
    }
    public String getProductName(){
        return productName;
    }
    public Date getMpDate(){
        return mpDate;
    }
    public String getModelName(){
        return modelName;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAcctDivisionName(){
        return acctDivisionName;
    }
    public String getMarketGradeName(){
        return marketGradeName;
    }
    public String getSalesRegionName(){
        return salesRegionName;
    }
    public String getProductionSiteName(){
        return productionSiteName;
    }
    public String getDevelopSiteName(){
        return developSiteName;
    }
    public String getProjectTypeCode(){
        return projectTypeCode;
    }
    public String getEventCode(){
        return eventCode;
    }
    public String getProcessTypeCode(){
        return processTypeCode;
    }
    public String getProjectGradeCode(){
        return projectGradeCode;
    }
    public String getDevelopTypeCode(){
        return developTypeCode;
    }
    public String getPrmTrmApplyCode(){
        return prmTrmApplyCode;
    }
    public String getTechProjectTypeCode(){
        return techProjectTypeCode;
    }
    public String getTechDevelopTypeCode(){
        return techDevelopTypeCode;
    }
    public String getCounterpartPlEmpNo(){
        return counterpartPlEmpNo;
    }
    public String getTransferQuarter(){
        return transferQuarter;
    }
    public String getCommercializeQuarter(){
        return commercializeQuarter;
    }
    public String getOverview(){
        return overview;
    }
    public String getNeeds(){
        return needs;
    }
    public String getApproach(){
        return approach;
    }
    public String getBenefit(){
        return benefit;
    }
    public String getCompetition(){
        return competition;
    }
    public String getRequirement(){
        return requirement;
    }
    public String getTechCategoryCode(){
        return techCategoryCode;
    }
    public String getContinuityCode(){
        return continuityCode;
    }
    public String getPortfolioCode(){
        return portfolioCode;
    }
    public String getPortfolioDetail(){
        return portfolioDetail;
    }
    public String getRoyaltyTypeCode(){
        return royaltyTypeCode;
    }
    public String getInitialProjectFlag(){
        return initialProjectFlag;
    }
    public String getTechProjectStageCode(){
        return techProjectStageCode;
    }
    public String getPlanProjectTypeCode(){
        return planProjectTypeCode;
    }
    public String getTechBusinessUnitCode(){
        return techBusinessUnitCode;
    }
    public String getInnovationItemType(){
        return innovationItemType;
    }
    public String getKeyItemType(){
        return keyItemType;
    }
    public String getShortTermPlan(){
        return shortTermPlan;
    }
    public String getLongTermPlan(){
        return longTermPlan;
    }
    public String getRepProjectName(){
        return repProjectName;
    }
}

