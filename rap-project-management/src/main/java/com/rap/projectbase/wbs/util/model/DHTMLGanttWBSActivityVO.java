/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : DHTMLGanttActivityVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 24.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.util.model;

import java.util.Date;

import rap.api.object.project.model.WBSItemsVO;

/**
 * <pre>
 * Class : DHTMLGanttCommonActivityVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class DHTMLGanttWBSActivityVO extends DHTMLGanttCommonActivityVO{
    public WBSItemsVO wbsItemsVO;
    public DHTMLGanttWBSActivityVO(WBSItemsVO wbsItemsVO) {
        super();
        this.wbsItemsVO = wbsItemsVO;
    }
    public String getOriginActivityCode(){
        return wbsItemsVO.getOriginActivityCode();
    }
    public Date getBaselinedStartDate(){
        return  wbsItemsVO.getBaselinedStartDate();
    }
    public Date getBaselinedEndDate(){
        return  wbsItemsVO.getBaselinedEndDate();
    }
    public Date getPlanStartDate(){
        return wbsItemsVO.getPlanStartDate();
    }
    public Date getPlanEndDate(){
        return wbsItemsVO.getPlanEndDate();
    }
    public Date getActualStartDate(){
        return wbsItemsVO.getActualStartDate();
    }
    public Date getActualEndDate(){
        return wbsItemsVO.getActualEndDate();
    }
    public Integer getBasedDaysForDelay(){
        return wbsItemsVO.getBasedDaysForDelay();
    }
    public Integer getStandardDuration(){
        return wbsItemsVO.getStandardDuration();
    }
    public Integer getPlanDuration(){
        return wbsItemsVO.getPlanDuration();
    }
    public String getIsCriticalPathItem(){
        return wbsItemsVO.getIsCriticalPathItem();
    }
    public String getInstruction(){
        return wbsItemsVO.getInstruction();
    }
    public String getCanBeSkipped(){
        return wbsItemsVO.getCanBeSkipped();
    }
    public String getCompleteType(){
        return wbsItemsVO.getCompleteType();
    }
    public String getIsHolding(){
        return wbsItemsVO.getIsHolding();
    }
    public String getIsMilestone(){
        return wbsItemsVO.getIsMilestone();
    }
    public String getIsSkipped(){
        return wbsItemsVO.getIsSkipped();
    }
    public String getRoleList(){
        return this.convertToDisplayedRoleNameList(wbsItemsVO.getRoleList());
    }
    public String getActivityOwnerList(){
        return this.convertToDisplayedActivityOwnerList(wbsItemsVO.getActivityOwnerList());
    }
    public String getIsForceStartable(){
        return wbsItemsVO.getIsForceStartable();
    }
    public String getIsMannuallyStarted(){
        return wbsItemsVO.getIsMannuallyStarted();
    }
    public String getIsMannuallyAdded(){
        return wbsItemsVO.getIsMannuallyAdded();
    }
    public String getIsCommon(){
        return wbsItemsVO.getIsCommon();
    }
    public String getIsSystemOwnerItem(){
        return wbsItemsVO.getIsSystemOwnerItem();
    }
    public String getIsReDoActivity(){
        return wbsItemsVO.getIsReDoActivity();
    }
    public String getActivityCategory(){
        return wbsItemsVO.getActivityCategory();
    }
    public String getActivityUrl(){
        return wbsItemsVO.getActivityUrl();
    }
    public String getIsKeyActivity(){
        return wbsItemsVO.getIsKeyActivity();
    }
    public String getIssueContents(){
        return wbsItemsVO.getIssueContents();
    }
    public String getProjectName(){
        return wbsItemsVO.getProjectName();
    }
    public String getTemplateName(){
        return wbsItemsVO.getTemplateName();
    }
    public String getPhaseName(){
        return wbsItemsVO.getPhaseName();
    }
    public String getPhaseTitles(){
        return wbsItemsVO.getPhaseTitles();
    }
    public String getPhaseNameSystem(){
        return wbsItemsVO.getPhaseNameSystem();
    }
    public String getPhaseTitlesSystem(){
        return wbsItemsVO.getPhaseTitlesSystem();
    }
    public String getIsFirstActivity(){
        return wbsItemsVO.getIsFirstActivity();
    }
    public String getIsLastActivity(){
        return wbsItemsVO.getIsLastActivity();
    }
    public String getStartValidationMethod(){
        return wbsItemsVO.getStartValidationMethod();
    }
    public String getStartExecutionMethod(){
        return wbsItemsVO.getStartExecutionMethod();
    }
    public String getCompleteValidationMethod(){
        return wbsItemsVO.getCompleteValidationMethod();
    }
    public String getPostExecutionMethod(){
        return wbsItemsVO.getPostExecutionMethod();
    }
    public String getInboxCompletionType(){
        return wbsItemsVO.getInboxCompletionType();
    }
    public String getIsAutoComplete(){
        return wbsItemsVO.getIsAutoComplete();
    }
    public String getActivityNameEng(){
        return wbsItemsVO.getActivityNameEng();
    }
    public String getActivityNameKor(){
        return wbsItemsVO.getActivityNameKor();
    }
    public String getActivityNameChi(){
        return wbsItemsVO.getActivityNameChi();
    }
    
    public String getPreviousActivityList(){
        return this.convertToDisplayedNameList(this.convertToActivityNameList(wbsItemsVO.getPreviousActivityList()));
    }
    public String getObid(){
        return wbsItemsVO.getObid();
    }
    public String getNames(){
        return wbsItemsVO.getNames();
    }
    public String getRevision(){
        return wbsItemsVO.getRevision();
    }
    public String getIsLatest(){
        if(wbsItemsVO.getPreviousObid().equals("1")) return "Y";
        return "N";
    }
    public String getIsFirst(){
        if(wbsItemsVO.getNextObid().equals("1")) return "Y";
        return "N";
    }
    public String getStates(){
        return wbsItemsVO.getStates();
    }
    public String getClassName(){
        return wbsItemsVO.getClassName();
    }
    public String getParentClassName(){
        return (String)wbsItemsVO.getOutData().get("rel_fromClass");
    }
    public String getParentObid(){
        return (String)wbsItemsVO.getOutData().get("rel_fromObid");
    }
    
}
