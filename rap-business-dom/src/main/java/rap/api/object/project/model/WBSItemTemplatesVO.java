/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSItemTemplatesVO.java
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
public class WBSItemTemplatesVO extends BusinessObjectVO {
    private String        activityMasterName                                ;
    private String        activityMasterNameUpper                           ;
    private String        activityNameEng                                   ;
    private String        activityNameKor                                   ;
    private String        activityNameChi                                   ;
    private String        skipInfo                                          ;
    private String        roleList                                           = "None";
    private Integer       standardDuration                                   = 0;
    private Integer       actualDuration                                     = 0;
    private String        isCriticalPathItem                                 = "N";
    private String        instruction                                        = "-";
    private String        canBeSkipped                                       = "Y";
    private String        completeType                                       = "Need Approval";
    private String        isMilestone                                        = "N";
    private String        isCheckListItem                                    = "N";
    private String        isSystemOwnerItem                                  = "Not Defined";
    private String        previousActivityList                               = "None";
    private String        activityType                                       = "General";
    private String        activityCategory                                   = "None";
    private String        activityOwner                                      = "None";
    private String        activityUrl                                        = "None";
    private String        isKeyActivity                                      = "N";
    private String        hasChildActivity                                   = "N";
    private String        isCommon                                           = "N";
    private String        isMannuallyAdded                                   = "N";
    private String        startValidationMethod                              = "None";
    private String        startExecutionMethod                               = "None";
    private String        completeValidationMethod                           = "None";
    private String        postExecutionMethod                                = "None";
    private String        inboxCompletionType                                = "All";
    private String        isAutoComplete                                     = "N";


    public void    setActivityMasterName(String activityMasterName){
        this.activityMasterName = activityMasterName;
    }
    public void    setActivityMasterNameUpper(String activityMasterNameUpper){
        this.activityMasterNameUpper = activityMasterNameUpper;
    }
    public void    setActivityNameEng(String activityNameEng){
        this.activityNameEng = activityNameEng;
    }
    public void    setActivityNameKor(String activityNameKor){
        this.activityNameKor = activityNameKor;
    }
    public void    setActivityNameChi(String activityNameChi){
        this.activityNameChi = activityNameChi;
    }
    public void    setSkipInfo(String skipInfo){
        this.skipInfo = skipInfo;
    }
    public void    setRoleList(String roleList){
        this.roleList = roleList;
    }
    public void    setStandardDuration(Integer standardDuration){
        this.standardDuration = standardDuration;
    }
    public void    setActualDuration(Integer actualDuration){
        this.actualDuration = actualDuration;
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
    public void    setIsMilestone(String isMilestone){
        this.isMilestone = isMilestone;
    }
    public void    setIsCheckListItem(String isCheckListItem){
        this.isCheckListItem = isCheckListItem;
    }
    public void    setIsSystemOwnerItem(String isSystemOwnerItem){
        this.isSystemOwnerItem = isSystemOwnerItem;
    }
    public void    setPreviousActivityList(String previousActivityList){
        this.previousActivityList = previousActivityList;
    }
    public void    setActivityType(String activityType){
        this.activityType = activityType;
    }
    public void    setActivityCategory(String activityCategory){
        this.activityCategory = activityCategory;
    }
    public void    setActivityOwner(String activityOwner){
        this.activityOwner = activityOwner;
    }
    public void    setActivityUrl(String activityUrl){
        this.activityUrl = activityUrl;
    }
    public void    setIsKeyActivity(String isKeyActivity){
        this.isKeyActivity = isKeyActivity;
    }
    public void    setHasChildActivity(String hasChildActivity){
        this.hasChildActivity = hasChildActivity;
    }
    public void    setIsCommon(String isCommon){
        this.isCommon = isCommon;
    }
    public void    setIsMannuallyAdded(String isMannuallyAdded){
        this.isMannuallyAdded = isMannuallyAdded;
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
    public String getActivityMasterName(){
        return activityMasterName;
    }
    public String getActivityMasterNameUpper(){
        return activityMasterNameUpper;
    }
    public String getActivityNameEng(){
        return activityNameEng;
    }
    public String getActivityNameKor(){
        return activityNameKor;
    }
    public String getActivityNameChi(){
        return activityNameChi;
    }
    public String getSkipInfo(){
        return skipInfo;
    }
    public String getRoleList(){
        return roleList;
    }
    public Integer getStandardDuration(){
        return standardDuration;
    }
    public Integer getActualDuration(){
        return actualDuration;
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
    public String getIsMilestone(){
        return isMilestone;
    }
    public String getIsCheckListItem(){
        return isCheckListItem;
    }
    public String getIsSystemOwnerItem(){
        return isSystemOwnerItem;
    }
    public String getPreviousActivityList(){
        return previousActivityList;
    }
    public String getActivityType(){
        return activityType;
    }
    public String getActivityCategory(){
        return activityCategory;
    }
    public String getActivityOwner(){
        return activityOwner;
    }
    public String getActivityUrl(){
        return activityUrl;
    }
    public String getIsKeyActivity(){
        return isKeyActivity;
    }
    public String getHasChildActivity(){
        return hasChildActivity;
    }
    public String getIsCommon(){
        return isCommon;
    }
    public String getIsMannuallyAdded(){
        return isMannuallyAdded;
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

