/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectsVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.common.model.ProjectWorkplaceVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectsVO extends ProjectWorkplaceVO {
    private String        projectName                                       ;
    private String        divisionCode                                      ;
    private String        acctDivisionCode                                  ;
    private String        areaCode                                          ;
    private String        teamCode                                          ;
    private String        processTypeCode                                   ;
    private String        projectGradeCode                                  ;
    private String        productCode                                       ;
    private String        developTypeCode                                   ;
    private Date          mpDate                                            ;
    private Date          projectStartDate                                  ;
    private Date          projectEndDate                                    ;
    private String        currentEventCode                                  ;
    private String        projectStatusCode                                 ;
    private String        bmsProjectCode                                    ;
    private String        bmsProjectCodePms                                 ;
    private String        initialProjectFlag                                 = "N";
    private String        overview                                          ;
    private String        isHolding                                          = "N";
    private String        holdingType                                       ;
    private String        budgetUseFlag                                     ;
    private String        budgetStartDate                                   ;
    private String        budgetEndDate                                     ;
    private String        userDefinedAttribute01                            ;
    private String        userDefinedAttribute02                            ;
    private String        userDefinedAttribute03                            ;
    private String        userDefinedAttribute04                            ;
    private String        userDefinedAttribute05                            ;
    private String        projectLeader                                     ;
    private String        securityLevel                                     ;
    private Date          projectCompletedDate                              ;
    private String        swGovernanceEvaluation                            ;
    private String        jiraProjectKey                                    ;
    private String        unlockYyyymm                                      ;
    private String        mmInputYn                                          = "N";
    private String        wbsTempleteCode                                   ;
    private String        techProjectTypeCode                               ;
    private String        setTemplateYn                                      = "N";
    private String        lastApprovedYyyymmdd                              ;
    private String        changeType                                        ;
    private String        transferYyyymmdd                                  ;
    private String        transferBy                                        ;
    private String        activity1CompYn                                    = "N";
    private String        activity2CompYn                                    = "N";
    private String        activity3CompYn                                    = "N";
    private String        activity4CompYn                                    = "N";
    private String        activity5CompYn                                    = "N";
    private String        mmTotal                                           ;
    private String        budgetTotal                                       ;
    private String        reportBatchYn                                      = "N";
    private String        bmsProjectCodeErr                                 ;
    private String        projectPm                                         ;
    private String        bizPlanTransferYn                                  = "N";
    private String        groupProject                                      ;
    private String        compulsoryApprovalYn                               = "N";
    private String        pdmsCompleteCheck                                 ;


    public void    setProjectName(String projectName){
        this.projectName = projectName;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAcctDivisionCode(String acctDivisionCode){
        this.acctDivisionCode = acctDivisionCode;
    }
    public void    setAreaCode(String areaCode){
        this.areaCode = areaCode;
    }
    public void    setTeamCode(String teamCode){
        this.teamCode = teamCode;
    }
    public void    setProcessTypeCode(String processTypeCode){
        this.processTypeCode = processTypeCode;
    }
    public void    setProjectGradeCode(String projectGradeCode){
        this.projectGradeCode = projectGradeCode;
    }
    public void    setProductCode(String productCode){
        this.productCode = productCode;
    }
    public void    setDevelopTypeCode(String developTypeCode){
        this.developTypeCode = developTypeCode;
    }
    public void    setMpDate(Date mpDate){
        this.mpDate = mpDate;
    }
    public void    setMpDate(String    mpDate){
        this.mpDate = this.omcConvertStr2Date(mpDate);
    }
    public void    setProjectStartDate(Date projectStartDate){
        this.projectStartDate = projectStartDate;
    }
    public void    setProjectStartDate(String    projectStartDate){
        this.projectStartDate = this.omcConvertStr2Date(projectStartDate);
    }
    public void    setProjectEndDate(Date projectEndDate){
        this.projectEndDate = projectEndDate;
    }
    public void    setProjectEndDate(String    projectEndDate){
        this.projectEndDate = this.omcConvertStr2Date(projectEndDate);
    }
    public void    setCurrentEventCode(String currentEventCode){
        this.currentEventCode = currentEventCode;
    }
    public void    setProjectStatusCode(String projectStatusCode){
        this.projectStatusCode = projectStatusCode;
    }
    public void    setBmsProjectCode(String bmsProjectCode){
        this.bmsProjectCode = bmsProjectCode;
    }
    public void    setBmsProjectCodePms(String bmsProjectCodePms){
        this.bmsProjectCodePms = bmsProjectCodePms;
    }
    public void    setInitialProjectFlag(String initialProjectFlag){
        this.initialProjectFlag = initialProjectFlag;
    }
    public void    setOverview(String overview){
        this.overview = overview;
    }
    public void    setIsHolding(String isHolding){
        this.isHolding = isHolding;
    }
    public void    setHoldingType(String holdingType){
        this.holdingType = holdingType;
    }
    public void    setBudgetUseFlag(String budgetUseFlag){
        this.budgetUseFlag = budgetUseFlag;
    }
    public void    setBudgetStartDate(String budgetStartDate){
        this.budgetStartDate = budgetStartDate;
    }
    public void    setBudgetEndDate(String budgetEndDate){
        this.budgetEndDate = budgetEndDate;
    }
    public void    setUserDefinedAttribute01(String userDefinedAttribute01){
        this.userDefinedAttribute01 = userDefinedAttribute01;
    }
    public void    setUserDefinedAttribute02(String userDefinedAttribute02){
        this.userDefinedAttribute02 = userDefinedAttribute02;
    }
    public void    setUserDefinedAttribute03(String userDefinedAttribute03){
        this.userDefinedAttribute03 = userDefinedAttribute03;
    }
    public void    setUserDefinedAttribute04(String userDefinedAttribute04){
        this.userDefinedAttribute04 = userDefinedAttribute04;
    }
    public void    setUserDefinedAttribute05(String userDefinedAttribute05){
        this.userDefinedAttribute05 = userDefinedAttribute05;
    }
    public void    setProjectLeader(String projectLeader){
        this.projectLeader = projectLeader;
    }
    public void    setSecurityLevel(String securityLevel){
        this.securityLevel = securityLevel;
    }
    public void    setProjectCompletedDate(Date projectCompletedDate){
        this.projectCompletedDate = projectCompletedDate;
    }
    public void    setProjectCompletedDate(String    projectCompletedDate){
        this.projectCompletedDate = this.omcConvertStr2Date(projectCompletedDate);
    }
    public void    setSwGovernanceEvaluation(String swGovernanceEvaluation){
        this.swGovernanceEvaluation = swGovernanceEvaluation;
    }
    public void    setJiraProjectKey(String jiraProjectKey){
        this.jiraProjectKey = jiraProjectKey;
    }
    public void    setUnlockYyyymm(String unlockYyyymm){
        this.unlockYyyymm = unlockYyyymm;
    }
    public void    setMmInputYn(String mmInputYn){
        this.mmInputYn = mmInputYn;
    }
    public void    setWbsTempleteCode(String wbsTempleteCode){
        this.wbsTempleteCode = wbsTempleteCode;
    }
    public void    setTechProjectTypeCode(String techProjectTypeCode){
        this.techProjectTypeCode = techProjectTypeCode;
    }
    public void    setSetTemplateYn(String setTemplateYn){
        this.setTemplateYn = setTemplateYn;
    }
    public void    setLastApprovedYyyymmdd(String lastApprovedYyyymmdd){
        this.lastApprovedYyyymmdd = lastApprovedYyyymmdd;
    }
    public void    setChangeType(String changeType){
        this.changeType = changeType;
    }
    public void    setTransferYyyymmdd(String transferYyyymmdd){
        this.transferYyyymmdd = transferYyyymmdd;
    }
    public void    setTransferBy(String transferBy){
        this.transferBy = transferBy;
    }
    public void    setActivity1CompYn(String activity1CompYn){
        this.activity1CompYn = activity1CompYn;
    }
    public void    setActivity2CompYn(String activity2CompYn){
        this.activity2CompYn = activity2CompYn;
    }
    public void    setActivity3CompYn(String activity3CompYn){
        this.activity3CompYn = activity3CompYn;
    }
    public void    setActivity4CompYn(String activity4CompYn){
        this.activity4CompYn = activity4CompYn;
    }
    public void    setActivity5CompYn(String activity5CompYn){
        this.activity5CompYn = activity5CompYn;
    }
    public void    setMmTotal(String mmTotal){
        this.mmTotal = mmTotal;
    }
    public void    setBudgetTotal(String budgetTotal){
        this.budgetTotal = budgetTotal;
    }
    public void    setReportBatchYn(String reportBatchYn){
        this.reportBatchYn = reportBatchYn;
    }
    public void    setBmsProjectCodeErr(String bmsProjectCodeErr){
        this.bmsProjectCodeErr = bmsProjectCodeErr;
    }
    public void    setProjectPm(String projectPm){
        this.projectPm = projectPm;
    }
    public void    setBizPlanTransferYn(String bizPlanTransferYn){
        this.bizPlanTransferYn = bizPlanTransferYn;
    }
    public void    setGroupProject(String groupProject){
        this.groupProject = groupProject;
    }
    public void    setCompulsoryApprovalYn(String compulsoryApprovalYn){
        this.compulsoryApprovalYn = compulsoryApprovalYn;
    }
    public void    setPdmsCompleteCheck(String pdmsCompleteCheck){
        this.pdmsCompleteCheck = pdmsCompleteCheck;
    }
    public String getProjectName(){
        return projectName;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAcctDivisionCode(){
        return acctDivisionCode;
    }
    public String getAreaCode(){
        return areaCode;
    }
    public String getTeamCode(){
        return teamCode;
    }
    public String getProcessTypeCode(){
        return processTypeCode;
    }
    public String getProjectGradeCode(){
        return projectGradeCode;
    }
    public String getProductCode(){
        return productCode;
    }
    public String getDevelopTypeCode(){
        return developTypeCode;
    }
    public Date getMpDate(){
        return mpDate;
    }
    public Date getProjectStartDate(){
        return projectStartDate;
    }
    public Date getProjectEndDate(){
        return projectEndDate;
    }
    public String getCurrentEventCode(){
        return currentEventCode;
    }
    public String getProjectStatusCode(){
        return projectStatusCode;
    }
    public String getBmsProjectCode(){
        return bmsProjectCode;
    }
    public String getBmsProjectCodePms(){
        return bmsProjectCodePms;
    }
    public String getInitialProjectFlag(){
        return initialProjectFlag;
    }
    public String getOverview(){
        return overview;
    }
    public String getIsHolding(){
        return isHolding;
    }
    public String getHoldingType(){
        return holdingType;
    }
    public String getBudgetUseFlag(){
        return budgetUseFlag;
    }
    public String getBudgetStartDate(){
        return budgetStartDate;
    }
    public String getBudgetEndDate(){
        return budgetEndDate;
    }
    public String getUserDefinedAttribute01(){
        return userDefinedAttribute01;
    }
    public String getUserDefinedAttribute02(){
        return userDefinedAttribute02;
    }
    public String getUserDefinedAttribute03(){
        return userDefinedAttribute03;
    }
    public String getUserDefinedAttribute04(){
        return userDefinedAttribute04;
    }
    public String getUserDefinedAttribute05(){
        return userDefinedAttribute05;
    }
    public String getProjectLeader(){
        return projectLeader;
    }
    public String getSecurityLevel(){
        return securityLevel;
    }
    public Date getProjectCompletedDate(){
        return projectCompletedDate;
    }
    public String getSwGovernanceEvaluation(){
        return swGovernanceEvaluation;
    }
    public String getJiraProjectKey(){
        return jiraProjectKey;
    }
    public String getUnlockYyyymm(){
        return unlockYyyymm;
    }
    public String getMmInputYn(){
        return mmInputYn;
    }
    public String getWbsTempleteCode(){
        return wbsTempleteCode;
    }
    public String getTechProjectTypeCode(){
        return techProjectTypeCode;
    }
    public String getSetTemplateYn(){
        return setTemplateYn;
    }
    public String getLastApprovedYyyymmdd(){
        return lastApprovedYyyymmdd;
    }
    public String getChangeType(){
        return changeType;
    }
    public String getTransferYyyymmdd(){
        return transferYyyymmdd;
    }
    public String getTransferBy(){
        return transferBy;
    }
    public String getActivity1CompYn(){
        return activity1CompYn;
    }
    public String getActivity2CompYn(){
        return activity2CompYn;
    }
    public String getActivity3CompYn(){
        return activity3CompYn;
    }
    public String getActivity4CompYn(){
        return activity4CompYn;
    }
    public String getActivity5CompYn(){
        return activity5CompYn;
    }
    public String getMmTotal(){
        return mmTotal;
    }
    public String getBudgetTotal(){
        return budgetTotal;
    }
    public String getReportBatchYn(){
        return reportBatchYn;
    }
    public String getBmsProjectCodeErr(){
        return bmsProjectCodeErr;
    }
    public String getProjectPm(){
        return projectPm;
    }
    public String getBizPlanTransferYn(){
        return bizPlanTransferYn;
    }
    public String getGroupProject(){
        return groupProject;
    }
    public String getCompulsoryApprovalYn(){
        return compulsoryApprovalYn;
    }
    public String getPdmsCompleteCheck(){
        return pdmsCompleteCheck;
    }
}

