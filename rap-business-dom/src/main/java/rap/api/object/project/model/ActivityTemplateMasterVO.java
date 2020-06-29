/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ActivityTemplateMasterVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ActivityTemplateMasterVO extends BusinessObjectMasterVO {
    private String        activityNameKor                                   ;
    private String        activityNameEng                                   ;
    private String        activityNameChi                                   ;
    private String        isCommon                                           = "N";
    private String        defaultActivityType                                = "General";
    private String        isKeyActivity                                      = "N";
    private String        defaultRoleList                                    = "None";
    private String        defaultIsChecklistItem                             = "N";
    private String        isOnlyOnePerTemplate                               = "Y";
    private String        isSystemOwnerItem                                  = "Not Defined";
    private String        appliedScope                                       = "Common";
    private String        activityUrl                                        = "None";
    private String        activityCategory                                   = "None";
    private String        appliedOrganizationList                            = "None";
    private String        startValidationMethod                              = "None";
    private String        startExecutionMethod                               = "None";
    private String        completeValidationMethod                           = "None";
    private String        postExecutionMethod                                = "None";
    private String        inboxCompletionType                                = "All";
    private String        isAutoComplete                                     = "N";


    public void    setActivityNameKor(String activityNameKor){
        this.activityNameKor = activityNameKor;
    }
    public void    setActivityNameEng(String activityNameEng){
        this.activityNameEng = activityNameEng;
    }
    public void    setActivityNameChi(String activityNameChi){
        this.activityNameChi = activityNameChi;
    }
    public void    setIsCommon(String isCommon){
        this.isCommon = isCommon;
    }
    public void    setDefaultActivityType(String defaultActivityType){
        this.defaultActivityType = defaultActivityType;
    }
    public void    setIsKeyActivity(String isKeyActivity){
        this.isKeyActivity = isKeyActivity;
    }
    public void    setDefaultRoleList(String defaultRoleList){
        this.defaultRoleList = defaultRoleList;
    }
    public void    setDefaultIsChecklistItem(String defaultIsChecklistItem){
        this.defaultIsChecklistItem = defaultIsChecklistItem;
    }
    public void    setIsOnlyOnePerTemplate(String isOnlyOnePerTemplate){
        this.isOnlyOnePerTemplate = isOnlyOnePerTemplate;
    }
    public void    setIsSystemOwnerItem(String isSystemOwnerItem){
        this.isSystemOwnerItem = isSystemOwnerItem;
    }
    public void    setAppliedScope(String appliedScope){
        this.appliedScope = appliedScope;
    }
    public void    setActivityUrl(String activityUrl){
        this.activityUrl = activityUrl;
    }
    public void    setActivityCategory(String activityCategory){
        this.activityCategory = activityCategory;
    }
    public void    setAppliedOrganizationList(String appliedOrganizationList){
        this.appliedOrganizationList = appliedOrganizationList;
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
    public String getActivityNameKor(){
        return activityNameKor;
    }
    public String getActivityNameEng(){
        return activityNameEng;
    }
    public String getActivityNameChi(){
        return activityNameChi;
    }
    public String getIsCommon(){
        return isCommon;
    }
    public String getDefaultActivityType(){
        return defaultActivityType;
    }
    public String getIsKeyActivity(){
        return isKeyActivity;
    }
    public String getDefaultRoleList(){
        return defaultRoleList;
    }
    public String getDefaultIsChecklistItem(){
        return defaultIsChecklistItem;
    }
    public String getIsOnlyOnePerTemplate(){
        return isOnlyOnePerTemplate;
    }
    public String getIsSystemOwnerItem(){
        return isSystemOwnerItem;
    }
    public String getAppliedScope(){
        return appliedScope;
    }
    public String getActivityUrl(){
        return activityUrl;
    }
    public String getActivityCategory(){
        return activityCategory;
    }
    public String getAppliedOrganizationList(){
        return appliedOrganizationList;
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

