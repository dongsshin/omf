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

import rap.api.object.project.model.WBSItemTemplatesVO;

/**
 * <pre>
 * Class : DHTMLGanttActivityVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class DHTMLGanttTemplateActivityVO extends DHTMLGanttCommonActivityVO{
    public WBSItemTemplatesVO wbsItemTemplatesVO;
    
    public DHTMLGanttTemplateActivityVO(WBSItemTemplatesVO wbsItemTemplatesVO) {
        super();
        this.wbsItemTemplatesVO = wbsItemTemplatesVO;
    }
    public String getActivityMasterName(){
        return wbsItemTemplatesVO.getActivityMasterName();
    }
    public String getActivityMasterNameUpper(){
        return wbsItemTemplatesVO.getActivityMasterNameUpper();
    }
    public String getActivityNameEng(){
        return wbsItemTemplatesVO.getActivityNameEng();
    }
    public String getActivityNameKor(){
        return wbsItemTemplatesVO.getActivityNameKor();
    }
    public String getActivityNameChi(){
        return wbsItemTemplatesVO.getActivityNameChi();
    }
    public String getSkipInfo(){
        return wbsItemTemplatesVO.getSkipInfo();
    }
    public String getRoleList(){
        return this.convertToDisplayedRoleNameList(wbsItemTemplatesVO.getRoleList());
    }
    public Integer getStandardDuration(){
        return wbsItemTemplatesVO.getStandardDuration();
    }
    public String getIsCriticalPathItem(){
        return wbsItemTemplatesVO.getIsCriticalPathItem();
    }
    public String getInstruction(){
        return wbsItemTemplatesVO.getInstruction();
    }
    public String getCanBeSkipped(){
        return wbsItemTemplatesVO.getCanBeSkipped();
    }
    public String getCompleteType(){
        return wbsItemTemplatesVO.getCompleteType();
    }
    public String getIsMilestone(){
        return wbsItemTemplatesVO.getIsMilestone();
    }
    public String getIsCheckListItem(){
        return wbsItemTemplatesVO.getIsCheckListItem();
    }
    public String getPreviousActivityList(){
        return this.convertToDisplayedNameList(this.convertToActivityNameList(wbsItemTemplatesVO.getPreviousActivityList()));
    }
    public String getActivityType(){
        return wbsItemTemplatesVO.getActivityType();
    }
    public String getObid(){
        return wbsItemTemplatesVO.getObid();
    }
    public String getNames(){
        return wbsItemTemplatesVO.getNames();
    }
    public String getRevision(){
        return wbsItemTemplatesVO.getRevision();
    }
    public String getIsLatest(){
        if(wbsItemTemplatesVO.getPreviousObid().equals("1")) return "Y";
        return "N";
    }
    public String getIsFirst(){
        if(wbsItemTemplatesVO.getNextObid().equals("1")) return "Y";
        return "N";
    }
    public String getStates(){
        return wbsItemTemplatesVO.getStates();
    }
    public String getClassName(){
        return wbsItemTemplatesVO.getClassName();
    }
    public String getParentClassName(){
        return (String)wbsItemTemplatesVO.getOutData().get("rel_fromClass");
    }
    public String getParentObid(){
        return (String)wbsItemTemplatesVO.getOutData().get("rel_fromObid");
    }
}
