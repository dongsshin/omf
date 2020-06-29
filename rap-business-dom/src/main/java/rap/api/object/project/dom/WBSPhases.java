/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSPhases.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.intf.IfPlmDataByTriggerVO;
import com.rap.omc.intf.TriggerUtil;
import com.rap.omc.util.TimeServiceUtil;

import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSPhasesVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;


public class WBSPhases extends WBSItems {
    public WBSPhases(String obid){
        super(obid);
    }
    public WBSPhases(WBSPhasesVO vo){
        super(vo);
    }
     @Override
    public WBSPhasesVO getVo(){
        return (WBSPhasesVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeWBSPhases();
    }
    public void initializeWBSPhases(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "WBSPhases[toString()=" + super.toString() + "]";
    }


     @Override
    protected void validateForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.validateForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

     @Override
    protected void preProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.preProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

     @Override
    protected void postProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.postProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

    @Override
    protected void validateForCreate(Map<String, Object> map){
        super.validateForCreate(map);
        /*code below*/

    }

    @Override
    protected void preProcessForCreate(Map<String, Object> map){
        super.preProcessForCreate(map);
        /*code below*/

    }

    @Override
    protected void postProcessForCreate(Map<String, Object> map){
        super.postProcessForCreate(map);
        /*code below*/

    }

    @Override
    protected void validateForDelete(Map<String, Object> map){
        super.validateForDelete(map);
        /*code below*/

    }

    @Override
    protected void preProcessForDelete(Map<String, Object> map){
        super.preProcessForDelete(map);
        /*code below*/

    }

    @Override
    protected void postProcessForDelete(Map<String, Object> map){
        super.postProcessForDelete(map);
        /*code below*/

    }

    @Override
    protected void validateForModify(Map<String, Object> map){
        super.validateForModify(map);
        /*code below*/

    }

    @Override
    protected void preProcessForModify(Map<String, Object> map){
        super.preProcessForModify(map);
        /*code below*/

    }

    @Override
    protected void postProcessForModify(Map<String, Object> map){
        super.postProcessForModify(map);
        /*code below*/

    }

    @Override
    protected void validateForWithdraw(Map<String, Object> map){
        super.validateForWithdraw(map);
        /*code below*/

    }

    @Override
    protected void preProcessForWithdraw(Map<String, Object> map){
        super.preProcessForWithdraw(map);
        /*code below*/

    }

    @Override
    protected void postProcessForWithdraw(Map<String, Object> map){
        super.postProcessForWithdraw(map);
        /*code below*/

    }

    @Override
    protected void validateForDemote(Map<String, Object> map){
        super.validateForDemote(map);
        /*code below*/

    }

    @Override
    protected void preProcessForDemote(Map<String, Object> map){
        super.preProcessForDemote(map);
        /*code below*/

    }

    @Override
    protected void postProcessForDemote(Map<String, Object> map){
        super.postProcessForDemote(map);
        /*code below*/

    }

    @Override
    protected void validateForPromote(Map<String, Object> map){
        super.validateForPromote(map);
        /*code below*/

    }

    @Override
    protected void preProcessForPromote(Map<String, Object> map){
        super.preProcessForPromote(map);
        /*code below*/

    }

    @Override
    protected void postProcessForPromote(Map<String, Object> map){
        super.postProcessForPromote(map);
        /*code below*/

    }

    @Override
    protected void validateForClone(Map<String, Object> map){
        super.validateForClone(map);
        /*code below*/

    }

    @Override
    protected void preProcessForClone(Map<String, Object> map){
        super.preProcessForClone(map);
        /*code below*/

    }

    @Override
    protected void postProcessForClone(Map<String, Object> map){
        super.postProcessForClone(map);
        /*code below*/

    }

    @Override
    protected void validateForRevise(Map<String, Object> map){
        super.validateForRevise(map);
        /*code below*/

    }

    @Override
    protected void preProcessForRevise(Map<String, Object> map){
        super.preProcessForRevise(map);
        /*code below*/

    }

    @Override
    protected void postProcessForRevise(Map<String, Object> map){
        super.postProcessForRevise(map);
        /*code below*/

    }
    @Override
    public void completeWBSActivity(BusinessObjectRootVO inboxTaskVO){
        this.completeWBSActivity(inboxTaskVO,false);
    }
    @Override
    public void completeWBSActivity(BusinessObjectRootVO inboxTaskVO, boolean skipWarning){
        super.completeWBSActivity(inboxTaskVO,skipWarning);
    }
   /**
     * 
     * @param generateName
     * @see lge.plm.api.object.project.dom.WBSItems#setDefault(boolean)
     */
    @Override
    public void setDefault(boolean generateName){
        super.setDefault(generateName);
        getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WBS_PHASE);
    }
    
    public void skipWBSPhase(String projectScheduleObid){
        List<WBSItemsVO> childWBSItemList = getChildWBSItemListActivityOnly(projectScheduleObid, ProjectConstants.WBS_MAX_LEVEL);
    }
    
    /**
     * SCM Interface ( Plan End, Actual End 차이 Interface )
     * @param projectVO
     */
    public void interfaceEndDateDifferToSCM(ProjectsVO projectVO){
       Projects projects = DomUtil.toDom(projectVO);
       if(projects.isKindOf(ApplicationSchemaConstants.BIZCLASS_MODELDEVELOPMENTPROJECTS) || projects.isKindOf(ApplicationSchemaConstants.BIZCLASS_MODULEDEVELOPMENTPROJECTS)){
           Date planEndDate = this.getVo().getPlanEndDate();
           Date actualEndDate = TimeServiceUtil.getDBLocalTime();
           long differInMillis = getDifferMillis(actualEndDate, planEndDate, true);
           int differDay = (int)(differInMillis / 1000 / 60 / 60 / 24);
           IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO(projects.getVo(), ApplicationBizConstants.TR_ID_PROJECT_COM_OUT, ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_COMMON_ALL);

           //7일 이상인 경우에만 보내도록 함
           if(differDay > 7){
               triggerVO.setAttribute1(differDay+"");
               triggerVO.setAttribute2("GSCP");
               triggerVO.setAttribute3("SCM");
               TriggerUtil.createDataByTrigger(triggerVO);
           } else {
               triggerVO.setAttribute1("0");
               triggerVO.setAttribute2("GSCP");
               triggerVO.setAttribute3("SCM");
               TriggerUtil.createDataByTrigger(triggerVO);
           }
       }
    }
    public static long getDifferMillis(Date fromDate, Date toDate, boolean clearTimeOfDay){
        Calendar fromDateCalendar = Calendar.getInstance();
        fromDateCalendar.setTime(fromDate);
        Calendar toDateCalendar = Calendar.getInstance();
        toDateCalendar.setTime(toDate);
        if(clearTimeOfDay){
            fromDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
            fromDateCalendar.set(Calendar.MINUTE, 0);
            fromDateCalendar.set(Calendar.SECOND, 0);
            fromDateCalendar.set(Calendar.MILLISECOND, 0);
            
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
            toDateCalendar.set(Calendar.MINUTE, 0);
            toDateCalendar.set(Calendar.SECOND, 0);
            toDateCalendar.set(Calendar.MILLISECOND, 0);
        }
        long fromTimeInMillis = fromDateCalendar.getTimeInMillis();
        long toTimeInMillis = toDateCalendar.getTimeInMillis();
        return fromTimeInMillis - toTimeInMillis;
    }
}

