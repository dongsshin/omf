/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : TechProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.ProjectsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class TechProjectVO extends ProjectsVO {
    private String        prmTrmApplyCode                                   ;
    private String        techProjectStageCode                              ;
    private String        techDevelopTypeCode                               ;
    private String        counterpartPlEmpNo                                ;
    private String        transferQuarter                                   ;
    private String        commercializeQuarter                              ;
    private String        needs                                             ;
    private String        approach                                          ;
    private String        benefit                                           ;
    private String        competition                                       ;
    private String        requirement                                       ;
    private String        techCategoryCode                                  ;
    private String        continuityCode                                    ;
    private String        portfolioCode                                     ;
    private String        repProjectName                                    ;
    private String        royaltyTypeCode                                   ;
    private String        techBusinessUnitCode                              ;
    private String        innovationItemType                                ;
    private String        keyItemType                                       ;
    private String        shortTermPlan                                     ;
    private String        longTermPlan                                      ;
    private String        applyTargetModelType                              ;


    public void    setPrmTrmApplyCode(String prmTrmApplyCode){
        this.prmTrmApplyCode = prmTrmApplyCode;
    }
    public void    setTechProjectStageCode(String techProjectStageCode){
        this.techProjectStageCode = techProjectStageCode;
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
    public void    setRepProjectName(String repProjectName){
        this.repProjectName = repProjectName;
    }
    public void    setRoyaltyTypeCode(String royaltyTypeCode){
        this.royaltyTypeCode = royaltyTypeCode;
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
    public void    setApplyTargetModelType(String applyTargetModelType){
        this.applyTargetModelType = applyTargetModelType;
    }
    public String getPrmTrmApplyCode(){
        return prmTrmApplyCode;
    }
    public String getTechProjectStageCode(){
        return techProjectStageCode;
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
    public String getRepProjectName(){
        return repProjectName;
    }
    public String getRoyaltyTypeCode(){
        return royaltyTypeCode;
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
    public String getApplyTargetModelType(){
        return applyTargetModelType;
    }
}

