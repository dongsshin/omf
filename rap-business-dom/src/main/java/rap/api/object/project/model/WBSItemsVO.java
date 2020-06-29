/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSItemsVO.java
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
public class WBSItemsVO extends BusinessObjectVO {
    private String        originActivityCode                                 = "None";
    private Integer       executionCount                                     = 1;
    private String        activityNameKor                                   ;
    private String        activityNameEng                                   ;
    private String        activityNameChi                                   ;
    private Date          baselinedStartDate                                ;
    private Date          baselinedEndDate                                  ;
    private Date          planStartDate                                     ;
    private Date          planEndDate                                       ;
    private Date          actualStartDate                                   ;
    private Date          actualEndDate                                     ;
    private Integer       basedDaysForDelay                                  = 0;
    private Integer       standardDuration                                   = 0;
    private Integer       planDuration                                       = 1;
    private String        isCriticalPathItem                                 = "N";
    private String        instruction                                        = "-";
    private String        canBeSkipped                                       = "Y";
    private String        completeType                                       = "Need Approval";
    private String        isHolding                                          = "N";
    private String        isMilestone                                        = "N";
    private String        isSkipped                                          = "N";
    private String        roleList                                           = "None";
    private String        activityOwnerList                                  = "None";
    private String        isForceStartable                                   = "N";
    private String        isMannuallyStarted                                 = "N";
    private String        isMannuallyAdded                                   = "N";
    private String        isCommon                                           = "N";
    private String        isSystemOwnerItem                                  = "Not Defined";
    private String        isReDoActivity                                     = "N";
    private String        previousActivityList                               = "None";
    private String        activityCategory                                   = "None";
    private String        activityUrl                                        = "None";
    private String        isKeyActivity                                      = "N";
    private String        issueContents                                     ;
    private String        projectName                                       ;
    private String        templateName                                      ;
    private String        phaseName                                         ;
    private String        phaseTitles                                       ;
    private String        phaseNameSystem                                   ;
    private String        phaseTitlesSystem                                 ;
    private String        isFirstActivity                                    = "N";
    private String        isLastActivity                                     = "N";
    private String        startValidationMethod                              = "None";
    private String        startExecutionMethod                               = "None";
    private String        completeValidationMethod                           = "None";
    private String        postExecutionMethod                                = "None";
    private String        inboxCompletionType                                = "All";
    private String        isAutoComplete                                     = "N";


    public void    setOriginActivityCode(String originActivityCode){
        this.originActivityCode = originActivityCode;
    }
    public void    setExecutionCount(Integer executionCount){
        this.executionCount = executionCount;
    }
    public void    setActivityNameKor(String activityNameKor){
        this.activityNameKor = activityNameKor;
    }
    public void    setActivityNameEng(String activityNameEng){
        this.activityNameEng = activityNameEng;
    }
    public void    setActivityNameChi(String activityNameChi){
        this.activityNameChi = activityNameChi;
    }
    public void    setBaselinedStartDate(Date baselinedStartDate){
        this.baselinedStartDate = baselinedStartDate;
    }
    public void    setBaselinedStartDate(String    baselinedStartDate){
        this.baselinedStartDate = this.omcConvertStr2Date(baselinedStartDate);
    }
    public void    setBaselinedEndDate(Date baselinedEndDate){
        this.baselinedEndDate = baselinedEndDate;
    }
    public void    setBaselinedEndDate(String    baselinedEndDate){
        this.baselinedEndDate = this.omcConvertStr2Date(baselinedEndDate);
    }
    public void    setPlanStartDate(Date planStartDate){
        this.planStartDate = planStartDate;
    }
    public void    setPlanStartDate(String    planStartDate){
        this.planStartDate = this.omcConvertStr2Date(planStartDate);
    }
    public void    setPlanEndDate(Date planEndDate){
        this.planEndDate = planEndDate;
    }
    public void    setPlanEndDate(String    planEndDate){
        this.planEndDate = this.omcConvertStr2Date(planEndDate);
    }
    public void    setActualStartDate(Date actualStartDate){
        this.actualStartDate = actualStartDate;
    }
    public void    setActualStartDate(String    actualStartDate){
        this.actualStartDate = this.omcConvertStr2Date(actualStartDate);
    }
    public void    setActualEndDate(Date actualEndDate){
        this.actualEndDate = actualEndDate;
    }
    public void    setActualEndDate(String    actualEndDate){
        this.actualEndDate = this.omcConvertStr2Date(actualEndDate);
    }
    public void    setBasedDaysForDelay(Integer basedDaysForDelay){
        this.basedDaysForDelay = basedDaysForDelay;
    }
    public void    setStandardDuration(Integer standardDuration){
        this.standardDuration = standardDuration;
    }
    public void    setPlanDuration(Integer planDuration){
        this.planDuration = planDuration;
    }
    public void    setIsCriticalPathItem(String isCriticalPathItem){
        this.isCriticalPathItem = isCriticalPathItem;
    }
    public void    setInstruction(String instruction){
        this.instruction = instruction;
    }
    public void    setCanBeSkipped(String canBeSkipped){
        this.canBeSkipped = canBeSkipped;
    }
    public void    setCompleteType(String completeType){
        this.completeType = completeType;
    }
    public void    setIsHolding(String isHolding){
        this.isHolding = isHolding;
    }
    public void    setIsMilestone(String isMilestone){
        this.isMilestone = isMilestone;
    }
    public void    setIsSkipped(String isSkipped){
        this.isSkipped = isSkipped;
    }
    public void    setRoleList(String roleList){
        this.roleList = roleList;
    }
    public void    setActivityOwnerList(String activityOwnerList){
        this.activityOwnerList = activityOwnerList;
    }
    public void    setIsForceStartable(String isForceStartable){
        this.isForceStartable = isForceStartable;
    }
    public void    setIsMannuallyStarted(String isMannuallyStarted){
        this.isMannuallyStarted = isMannuallyStarted;
    }
    public void    setIsMannuallyAdded(String isMannuallyAdded){
        this.isMannuallyAdded = isMannuallyAdded;
    }
    public void    setIsCommon(String isCommon){
        this.isCommon = isCommon;
    }
    public void    setIsSystemOwnerItem(String isSystemOwnerItem){
        this.isSystemOwnerItem = isSystemOwnerItem;
    }
    public void    setIsReDoActivity(String isReDoActivity){
        this.isReDoActivity = isReDoActivity;
    }
    public void    setPreviousActivityList(String previousActivityList){
        this.previousActivityList = previousActivityList;
    }
    public void    setActivityCategory(String activityCategory){
        this.activityCategory = activityCategory;
    }
    public void    setActivityUrl(String activityUrl){
        this.activityUrl = activityUrl;
    }
    public void    setIsKeyActivity(String isKeyActivity){
        this.isKeyActivity = isKeyActivity;
    }
    public void    setIssueContents(String issueContents){
        this.issueContents = issueContents;
    }
    public void    setProjectName(String projectName){
        this.projectName = projectName;
    }
    public void    setTemplateName(String templateName){
        this.templateName = templateName;
    }
    public void    setPhaseName(String phaseName){
        this.phaseName = phaseName;
    }
    public void    setPhaseTitles(String phaseTitles){
        this.phaseTitles = phaseTitles;
    }
    public void    setPhaseNameSystem(String phaseNameSystem){
        this.phaseNameSystem = phaseNameSystem;
    }
    public void    setPhaseTitlesSystem(String phaseTitlesSystem){
        this.phaseTitlesSystem = phaseTitlesSystem;
    }
    public void    setIsFirstActivity(String isFirstActivity){
        this.isFirstActivity = isFirstActivity;
    }
    public void    setIsLastActivity(String isLastActivity){
        this.isLastActivity = isLastActivity;
    }
    public void    setStartValidationMethod(String startValidationMethod){
        this.startValidationMethod = startValidationMethod;
    }
    public void    setStartExecutionMethod(String startExecutionMethod){
        this.startExecutionMethod = startExecutionMethod;
    }
    public void    setCompleteValidationMethod(String completeValidationMethod){
        this.completeValidationMethod = completeValidationMethod;
    }
    public void    setPostExecutionMethod(String postExecutionMethod){
        this.postExecutionMethod = postExecutionMethod;
    }
    public void    setInboxCompletionType(String inboxCompletionType){
        this.inboxCompletionType = inboxCompletionType;
    }
    public void    setIsAutoComplete(String isAutoComplete){
        this.isAutoComplete = isAutoComplete;
    }
    public String getOriginActivityCode(){
        return originActivityCode;
    }
    public Integer getExecutionCount(){
        return executionCount;
    }
    public String getActivityNameKor(){
        return activityNameKor;
    }
    public String getActivityNameEng(){
        return activityNameEng;
    }
    public String getActivityNameChi(){
        return activityNameChi;
    }
    public Date getBaselinedStartDate(){
        return baselinedStartDate;
    }
    public Date getBaselinedEndDate(){
        return baselinedEndDate;
    }
    public Date getPlanStartDate(){
        return planStartDate;
    }
    public Date getPlanEndDate(){
        return planEndDate;
    }
    public Date getActualStartDate(){
        return actualStartDate;
    }
    public Date getActualEndDate(){
        return actualEndDate;
    }
    public Integer getBasedDaysForDelay(){
        return basedDaysForDelay;
    }
    public Integer getStandardDuration(){
        return standardDuration;
    }
    public Integer getPlanDuration(){
        return planDuration;
    }
    public String getIsCriticalPathItem(){
        return isCriticalPathItem;
    }
    public String getInstruction(){
        return instruction;
    }
    public String getCanBeSkipped(){
        return canBeSkipped;
    }
    public String getCompleteType(){
        return completeType;
    }
    public String getIsHolding(){
        return isHolding;
    }
    public String getIsMilestone(){
        return isMilestone;
    }
    public String getIsSkipped(){
        return isSkipped;
    }
    public String getRoleList(){
        return roleList;
    }
    public String getActivityOwnerList(){
        return activityOwnerList;
    }
    public String getIsForceStartable(){
        return isForceStartable;
    }
    public String getIsMannuallyStarted(){
        return isMannuallyStarted;
    }
    public String getIsMannuallyAdded(){
        return isMannuallyAdded;
    }
    public String getIsCommon(){
        return isCommon;
    }
    public String getIsSystemOwnerItem(){
        return isSystemOwnerItem;
    }
    public String getIsReDoActivity(){
        return isReDoActivity;
    }
    public String getPreviousActivityList(){
        return previousActivityList;
    }
    public String getActivityCategory(){
        return activityCategory;
    }
    public String getActivityUrl(){
        return activityUrl;
    }
    public String getIsKeyActivity(){
        return isKeyActivity;
    }
    public String getIssueContents(){
        return issueContents;
    }
    public String getProjectName(){
        return projectName;
    }
    public String getTemplateName(){
        return templateName;
    }
    public String getPhaseName(){
        return phaseName;
    }
    public String getPhaseTitles(){
        return phaseTitles;
    }
    public String getPhaseNameSystem(){
        return phaseNameSystem;
    }
    public String getPhaseTitlesSystem(){
        return phaseTitlesSystem;
    }
    public String getIsFirstActivity(){
        return isFirstActivity;
    }
    public String getIsLastActivity(){
        return isLastActivity;
    }
    public String getStartValidationMethod(){
        return startValidationMethod;
    }
    public String getStartExecutionMethod(){
        return startExecutionMethod;
    }
    public String getCompleteValidationMethod(){
        return completeValidationMethod;
    }
    public String getPostExecutionMethod(){
        return postExecutionMethod;
    }
    public String getInboxCompletionType(){
        return inboxCompletionType;
    }
    public String getIsAutoComplete(){
        return isAutoComplete;
    }
}

