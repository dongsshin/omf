/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectSchedule.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.intf.IfPlmDataByTriggerVO;
import com.rap.omc.intf.TriggerUtil;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.TimeServiceUtil;
import com.rap.omc.util.core.DateUtil;

import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.organization.dom.DivisionUnit;
import rap.api.object.project.model.ChangeProcessVO;
import rap.api.object.project.model.ProjectDefinedRoleVO;
import rap.api.object.project.model.ProjectPersonVO;
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSActivitiesVO;
import rap.api.object.project.model.WBSGeneralActivityVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.project.model.WBSPhasesVO;
import rap.api.object.project.model.WBSTemplateMasterVO;
import rap.api.object.relation.dom.AssignedToActivity;
import rap.api.object.relation.dom.WorkflowObjectRoute;
import rap.api.object.relation.model.AssignedToActivityVO;
import rap.api.object.relation.model.ControlledByProjectScheduleContextVO;
import rap.api.object.relation.model.HasSubWBSItemsVO;
import rap.api.object.relation.model.WBSDependencyVO;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.api.object.workflow.model.WorkflowRouteVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;
import rap.application.workflow.model.ActivitySimpleVO;
import rap.application.workflow.model.DependencyVO;
import rap.application.workflow.model.WBSActivityWorkflowVO;
import rap.application.workflow.util.WBSUtil;
import rap.application.workflow.util.WBSWorkflowServiceUtil;


public class ProjectSchedule extends BusinessObject {
    public ProjectSchedule(String obid){
        super(obid);
    }
    public ProjectSchedule(ProjectScheduleVO vo){
        super(vo);
    }
     @Override
    public ProjectScheduleVO getVo(){
        return (ProjectScheduleVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeProjectSchedule();
    }
    public void initializeProjectSchedule(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "ProjectSchedule[toString()=" + super.toString() + "]";
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
        ProjectsVO projectVO = (ProjectsVO)map.get(ProjectConstants.PROJECT_OMAP_KEY_PROJECT_VO);
        addFromObject(ApplicationSchemaConstants.RELCLASS_REVISEDSCHEDULE, projectVO, null);
        WBSTemplateMasterVO wbsTemplateMasterVO = (WBSTemplateMasterVO)map.get(ProjectConstants.PROJECT_OMAP_KEY_TEMPLATE_NASTER_VO);
        addToObject(ApplicationSchemaConstants.RELCLASS_APPLIEDWBSTEMPLATE, wbsTemplateMasterVO, null);
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
    public boolean canBeProcessed(ProjectsVO pojectVO){
        if(!isReleased()) return false;
        if(this.isLast()) return true;
        Projects pojectDom = new Projects(pojectVO);
        ProjectScheduleVO latestReleasedVO = pojectDom.getProjectLatestReleaseSchedule();
        if(NullUtil.isNull(latestReleasedVO)) return false;
        if(latestReleasedVO.getObid().equals(this.getObid())) return true;
        return false;
    }
    public boolean isReleased(){
        if(StrUtil.in(this.getStates(), ProjectConstants.WBS_HEADER_RELEASED_SET)) return true;
        return false;
    }
    public boolean isExistPhase(String wbsPhaseName, boolean includeSkip){
        WBSPhasesVO wbsPhaseVO = getWBSPhase(wbsPhaseName);
        if(NullUtil.isNull(wbsPhaseVO)){return false;}
        if(!includeSkip && "Y".equals(wbsPhaseVO.getIsSkipped())){return false;}
        return true;
    }
    public WBSPhasesVO getWBSPhase(String wbsPhaseName){
        List<WBSPhasesVO> wbsPhaseList = getWBSPhaseList();
        for(WBSPhasesVO vo : wbsPhaseList){
            if(wbsPhaseName.equals(vo.getPhaseName())){
                return vo;
            }
        }
        return null;
    }
    public List<WBSPhasesVO> getWBSPhaseList(){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();

        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT,
                ApplicationSchemaConstants.BIZCLASS_WBSPHASES,
                GlobalConstants.FLAG_TYPE_FROM, selectPatternBuf.toString(),
                wherePatternBuf.toString(), paramPatternBuf.toString(),
                false, true, 0, 1);
    }
    public boolean isFirstPhase(String phaseName, boolean excludeSkipped){
        WBSPhasesVO firstPhase = getFirstPhase(excludeSkipped);
        if(!NullUtil.isNull(firstPhase) && firstPhase.getPhaseName().equals(phaseName)){return true;}
        return false;
    }
    public WBSPhasesVO getFirstPhase(boolean excludeSkipped){
        WBSPhasesVO nextPhaseVO = null;
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();

        StringUtil.addSortByPattern(selectPatternBuf, "@REL.[sequences]");

        if(excludeSkipped) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[isSkipped]",  GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "Y" );
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, this.getObid() );

        List<WBSPhasesVO> wbsPhasesVOList = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, ApplicationSchemaConstants.BIZCLASS_WBSPHASES, GlobalConstants.FLAG_TYPE_TO,
                                                             selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(), false, true, 0, 1);

        if(!NullUtil.isNone(wbsPhasesVOList)){
            nextPhaseVO = wbsPhasesVOList.get(0);
        }
        return nextPhaseVO;
    }
    public WBSPhasesVO getNextPhase(String phaseName, boolean excludeSkipped){
        WBSPhasesVO nextPhaseVO = null;
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();

        StringUtil.addSortByPattern(selectPatternBuf, "@REL.[sequences]");

        if(excludeSkipped) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[isSkipped]",  GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "Y" );
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, this.getObid() );

        List<WBSPhasesVO> wbsPhasesVOList = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, ApplicationSchemaConstants.BIZCLASS_WBSPHASES, GlobalConstants.FLAG_TYPE_TO,
                selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(), false, true, 0, 1);

        if(!NullUtil.isNone(wbsPhasesVOList)){
            int nextIndex = 0;
            for(int i = 0; i < wbsPhasesVOList.size(); i++){
                if(wbsPhasesVOList.get(i).getPhaseName().equals(phaseName)){ nextIndex = i +1; break;}
            }
            if(wbsPhasesVOList.size() > nextIndex && 0 < nextIndex){nextPhaseVO = wbsPhasesVOList.get(nextIndex);}
        }
        return nextPhaseVO;
    }
    public <T> List<T> getWBSItemAll(){
        return getWBSItemAll(false);
    }
    @SuppressWarnings("unchecked")
    public <T> List<T> getWBSItemAll(boolean excludeSkipped){
        List<WBSItemsVO> list = getWBSItemsSub(false,false,false,false,null);
        if(!excludeSkipped) return (List<T>)list;
        List<T> rtnList = new ArrayList<T>();
        for(WBSItemsVO vo : list){
            if(!vo.getIsSkipped().equals("Y")){
                rtnList.add((T)vo);
            }
        }
        return rtnList;
    }
    public <T> List<T> getWBSItemAllWithJobActivity(){
        return getWBSItemsSub(false,true,false,false,null);
    }
    public <T> List<T> getWBSItems(){
        return getWBSItemsSub(false,true,false,false,null);
    }
    public <T> List<T> getWorkingWBSItemList(){
        Set<String> statesSet = new HashSet<String>();
        statesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT);
        return getWBSItemsSub(false,false,false,false,statesSet);
    }
    public <T> List<T> getStartedWBSItemList(){
        Set<String> statesSet = new HashSet<String>();
        statesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED);
        return getWBSItemsSub(false,false,true,false,statesSet);
    }
    public <T> List<T> getInApprovalProcessingWBSItemList(){
        Set<String> statesSet = new HashSet<String>();
        statesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_APPROVALPROCESSING);
        return getWBSItemsSub(false,false,false,false,statesSet);
    }
    public <T> List<T> getScheduledWBSItemList(){
        Set<String> statesSet = new HashSet<String>();
        statesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED);
        return getWBSItemsSub(false,false,false,false,statesSet);
    }
    public List<WBSItemsVO> getWBSItemsActivityOnly(){
        return getWBSItemsActivityOnly(false);
    }
    public List<WBSItemsVO> getWBSItemsActivityOnly(Set<String> statesSet){
        return getWBSItemsSub(false, false, true, false, statesSet);
    }
    public <T> List<T> getWBSItemsActivityOnly(boolean withJobActivity){
        return getWBSItemsSub(false,withJobActivity,true,false,null);
    }

    public List<WBSItemsVO> getWBSItemsDelayedActivityOnly(boolean onlyPrevActivityComplete, boolean isIncludingScheduled){
        Set<String> activityStateSet = new HashSet<String>();
        if ( isIncludingScheduled ) {
            activityStateSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED);
        }
        activityStateSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED);

        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringBuffer selectPatternBuf = new StringBuffer();

        StringUtil.constructSelectPattern(selectPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid " + ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(activityStateSet));

        Date baseDate = TimeServiceUtil.getTruncatedDBLocalDate();
        String toDay = DateUtil.converDateFormat(baseDate, ApplicationBizConstants.DATE_TYPE_JAVA);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[planEndDate]", GlobalConstants.OQL_OPERATOR_LESS_THAN, "TO_DATE:" +toDay);

        if(onlyPrevActivityComplete){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!From["+ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY+"].To.states", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED);
        }

        List<WBSItemsVO> relatedObjects = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT,
                                                            ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES,
                                                            GlobalConstants.FLAG_TYPE_FROM,
                                                            selectPatternBuf.toString(),
                                                            wherePatternBuf.toString(),
                                                            paramPatternBuf.toString(),
                                                            false, true, 0, 1);

        for(WBSItemsVO vo : relatedObjects){
            Integer delayedDays = WBSItems.getDelayedDays(vo, baseDate, false);
            if(delayedDays > 0){
                vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_delayedDays, delayedDays);
            }
        }
        return relatedObjects;
    }
    public List<WBSItemsVO> getStartableWBSItemList(){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();

        Set<String> preStatesConditionSet = new HashSet<String>();
        preStatesConditionSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED);

        Set<String> findStatesSet = new HashSet<String>();
        findStatesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED);
        findStatesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED);

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY+"].To.states", GlobalConstants.OQL_OPERATOR_NOT_IN, StrUtil.convertSet2Str(preStatesConditionSet));
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(findStatesSet));

        List<WBSItemsVO> relatedObjects = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY,
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        return relatedObjects;
    }

    public List<WBSItemsVO> getRescheduleableFirstWBSItemList(){

        List<WBSItemsVO>  firstWBSItemList= new ArrayList<WBSItemsVO>();
        firstWBSItemList = getEditableFirstWBSItemList();
        if(!NullUtil.isNone(firstWBSItemList)) return firstWBSItemList;

        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();

        Set<String> preStatesSet = new HashSet<String>();
        preStatesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED);

        Set<String> statesSet = new HashSet<String>();

        statesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED);
        statesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED);
        statesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT);

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY+"].To.states", GlobalConstants.OQL_OPERATOR_NOT_IN, StrUtil.convertSet2Str(preStatesSet));

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(statesSet));
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[isReDoActivity]", GlobalConstants.OQL_OPERATOR_EQUAL, "N");

        firstWBSItemList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY,
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        return firstWBSItemList;
    }

    public List<WBSItemsVO> getEditableFirstWBSItemList(){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();

        Set<String> preStatesSet = new HashSet<String>();
        preStatesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED);
        preStatesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED);
        preStatesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED);

        Set<String> statesSet = new HashSet<String>();

        statesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT);

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY+"].To.states", GlobalConstants.OQL_OPERATOR_NOT_IN, StrUtil.convertSet2Str(preStatesSet));

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(statesSet));
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[isReDoActivity]", GlobalConstants.OQL_OPERATOR_EQUAL, "N");

        List<WBSItemsVO> relatedObjects = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY,
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        return relatedObjects;
    }

    public List<WBSItemsVO> getNotAssignedActivities(){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY+"{[toClass]=='ProjectPerson'}].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " ");
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT);
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[isSkipped]", GlobalConstants.OQL_OPERATOR_EQUAL, "N");
        return  this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY,
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
    }

    public List<WBSItemsVO> getActivitiesAsCode(String activityCode){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[originActivityCode]", GlobalConstants.OQL_OPERATOR_EQUAL, activityCode);
        return  this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY,
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> getWBSItemsSub(boolean onlyMilestone, boolean withJobActivity, boolean isActivityOnly,boolean delayedOnly, Set<String> statesSet){
        StringBuffer classPattern = new StringBuffer();

        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringBuffer selectPatternBuf = new StringBuffer();

        if(onlyMilestone){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[isMilestone]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y" );
        }
        StringUtil.constructSelectPattern(selectPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid " + ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR);
        if(isActivityOnly){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
            classPattern.append(ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES);
        }else{
            classPattern.append(ApplicationSchemaConstants.BIZCLASS_WBSITEMS);
        }
        if(statesSet != null && statesSet.size() > 0){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(statesSet));
        }
        List<T> resultList = new ArrayList<T>();
        List<WBSItemsVO> relatedObjects = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT,classPattern.toString(),GlobalConstants.FLAG_TYPE_FROM, selectPatternBuf.toString(),wherePatternBuf.toString(), paramPatternBuf.toString(),
                                                        false, true, 0, 1);
        if(delayedOnly){
            List<WBSItemsVO> delayedOnlyObjects = new ArrayList<WBSItemsVO>();
            Date baseDate = TimeServiceUtil.getDBLocalTime();
            for(WBSItemsVO vo : relatedObjects){
                Integer delayedDays = WBSItems.getDelayedDays(vo, baseDate, false);
                if(delayedDays > 0){
                    vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_delayedDays, delayedDays);
                    delayedOnlyObjects.add(vo);
                }
            }
            relatedObjects = delayedOnlyObjects;
        }
        if(withJobActivity){
            relatedObjects = ObjectRoot.getRelatedObjectSet(relatedObjects,
                    ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER,
                    ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY,
                    GlobalConstants.FLAG_TYPE_TO,
                    "","","", null,  false,0, true, false, 0, 1);
        }
        resultList.addAll((ArrayList<T>)relatedObjects);

        return resultList;
    }
    public <T> List<T> getWBSItemsStructureList(boolean excludeSkipped,boolean includeJobActivity, boolean isActivityOnly){
        return getWBSItemsStructureSub(includeJobActivity,isActivityOnly,excludeSkipped,ProjectConstants.WBS_MAX_LEVEL);
    }
    @SuppressWarnings("unchecked")
    private <T> List<T> getWBSItemsStructureSub(boolean withJobActivity, boolean isActivityOnly, boolean excludeSkipped, int findDepth){

        StringBuffer selectPatternBuf = new StringBuffer();

        StringUtil.constructSelectPattern(selectPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid " + ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR);
        StringUtil.constructSelectPattern(selectPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES+"].obid hasDeliverable");
        StringUtil.addSortByPattern(selectPatternBuf, "@REL.[sequences]");

        StringBuffer classPattern = new StringBuffer();
        StringBuffer relationPattern = new StringBuffer();
        classPattern.append(ApplicationSchemaConstants.BIZCLASS_WBSITEMS);
        relationPattern.append(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS);

        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        if(excludeSkipped) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[isSkipped]",  GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "Y" );
        if(isActivityOnly){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        }
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, this.getObid() );

        List<T> resultList = new ArrayList<T>();
        List<WBSItemsVO> relatedObjects = getRelatedObjects(relationPattern.toString(), classPattern.toString(), GlobalConstants.FLAG_TYPE_TO,
                                 selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(),
                                 false, true, 0, findDepth);

        if(withJobActivity){
            StringBuffer jobSelectPatternBuf = new StringBuffer();
            String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);

            if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameKor]");
            }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameChi]");
            }else{
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameEng]");
            }
            relatedObjects = ObjectRoot.getRelatedObjectSet(relatedObjects,
                    ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER,
                    ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY,
                    GlobalConstants.FLAG_TYPE_TO,
                    jobSelectPatternBuf.toString(),"","", null,  false,0, true, false, 0, 1);
        }

        resultList.addAll((ArrayList<T>)relatedObjects);
        return resultList;
    }
    public WBSTemplateMasterVO getLatestWBSTemplateMaster(){
        WBSTemplateMasterVO wbsTemplateMasterVO = getRelatedObject(ApplicationSchemaConstants.RELCLASS_APPLIEDWBSTEMPLATE, ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO);
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterVO);
        WBSTemplateMasterVO latestWBSTemplateMasterVO = wbsTemplateMasterDom.getLastRevision();
        return latestWBSTemplateMasterVO;
    }
    public WBSTemplateMasterVO getWBSTemplateMaster(){
        return getRelatedObject(ApplicationSchemaConstants.RELCLASS_APPLIEDWBSTEMPLATE, ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO);
    }

    public ProjectsVO getRelatedProject(){
        return getRelatedObject(ApplicationSchemaConstants.RELCLASS_REVISEDSCHEDULE, ApplicationSchemaConstants.BIZCLASS_PROJECTS, GlobalConstants.FLAG_TYPE_FROM);
    }
    public static void makeWBSDB(List<WBSItemsVO> structureList,List<WBSDependencyVO> dependencyList,Map<String,WBSItemsVO> activityObjectDB){
        for(WBSItemsVO objVO : structureList){
            activityObjectDB.put(objVO.getObid(), objVO);
        }
        for(String key : activityObjectDB.keySet()){
            List<DependencyVO> previousList = new ArrayList<DependencyVO>();
            List<DependencyVO> nextList     = new ArrayList<DependencyVO>();
            for(WBSDependencyVO depVO : dependencyList){
                if(key.equals(depVO.getFromObid())) {
                    DependencyVO depenVO = new DependencyVO(depVO.getDependencyType(),activityObjectDB.get(depVO.getToObid()));
                    previousList.add(depenVO);
                }
                if(key.equals(depVO.getToObid())) {
                    DependencyVO depenVO = new DependencyVO(depVO.getDependencyType(),activityObjectDB.get(depVO.getFromObid()));
                    nextList.add(depenVO);
                }
            }
            activityObjectDB.get(key).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_PREVIOUS_LIST, previousList);
            activityObjectDB.get(key).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_NEXT_LIST, nextList);
        }
        for(WBSItemsVO objVO : structureList){
            List<WBSItemsVO> parentList = new ArrayList<WBSItemsVO>();
            List<WBSItemsVO> childList  = new ArrayList<WBSItemsVO>();
            for(WBSItemsVO subVO : structureList){
                if(objVO.getObid().equals(subVO.getOutDataAttributeValue("rel_fromObid"))) childList.add(activityObjectDB.get(subVO.getObid()));
                if(objVO.getObid().equals(subVO.getOutDataAttributeValue("rel_toObid"))) parentList.add(activityObjectDB.get(subVO.getOutDataAttributeValue("rel_fromObid")));
            }
            activityObjectDB.get(objVO.getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_CHILD_LIST, childList);
            if(NullUtil.isNone(parentList)){
                activityObjectDB.get(objVO.getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_PARENT, null);
            }else{
                activityObjectDB.get(objVO.getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_PARENT, parentList.get(0));
            }
        }
    }
    public List<WBSItemsVO> getStructuredWBSScheduleList(){
        return getStructuredWBSScheduleList(false,true);
    }
    public List<WBSItemsVO> getStructuredWBSScheduleList(boolean forSimulation, boolean clearDependency){
        return getStructuredWBSScheduleListSub(forSimulation,clearDependency,null);
    }
    public List<WBSItemsVO> getStructuredWBSScheduleList(boolean forSimulation, boolean clearDependency,Map<String,ActivitySimpleVO> simpleActivityList){
        return getStructuredWBSScheduleListSub(forSimulation,clearDependency,simpleActivityList);
    }
    
    private List<WBSItemsVO> getStructuredWBSScheduleListSub(boolean forSimulation, boolean clearDependency,Map<String,ActivitySimpleVO> simpleActivityList){
        Date currentdDate = TimeServiceUtil.getDBLocalTime();
        currentdDate = TimeServiceUtil.truncate(currentdDate);

        List<WBSItemsVO> structuredActivityList = new ArrayList<WBSItemsVO>();
        List<WBSDependencyVO> dependencyList = new ArrayList<WBSDependencyVO>();

        Map<String,WBSItemsVO> activityDB = getWBSDB(structuredActivityList, dependencyList);

        if(!NullUtil.isNull(simpleActivityList)){
            Map<String,ActivitySimpleVO> simpleActivityListTTemp =  WBSUtil.convertToSimpleActivity(structuredActivityList, activityDB);
           for(WBSItemsVO vo : structuredActivityList){
               if ((simpleActivityListTTemp.get(vo.getObid()) != null) &&  (simpleActivityListTTemp.get(vo.getObid()).getIsCriticalPathItem().equals("Y"))){
                   ;//vo.setIsCriticalPathItem("Y");
               }
           }
           for(String key : simpleActivityListTTemp.keySet()){
               simpleActivityList.put(key, simpleActivityListTTemp.get(key));
           }
        }
        if(activityDB.isEmpty()) return structuredActivityList;
        SortUtil.sort(structuredActivityList, "uniqueString", false);
        this.setIsMyActivity(structuredActivityList);
        List<WBSItemsVO> rescheduleableList = getRescheduleableFirstWBSItemList();
        for(WBSItemsVO vo : structuredActivityList){
            vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_delayedDays, WBSItems.getDelayedDays(vo,currentdDate,false));
            vo.setOutDataAttributeValue("rescheduleable", false);
            vo.setBasedDaysForDelay(calculateBasedDaysForDelay(vo.getPlanDuration()));
            for(WBSItemsVO subVO : rescheduleableList){
                if(vo.getObid().equals(subVO.getObid())){
                    vo.setOutDataAttributeValue("rescheduleable", true);
                }
            }
        }
        setActivityForcastedDateMain(structuredActivityList, activityDB);
        if(clearDependency) WBSUtil.clearDependencyInfo(structuredActivityList);
        return structuredActivityList;
    }
    private void setIsMyActivity(List<WBSItemsVO> list){
        String currentUserId = ThreadLocalUtil.getUserId();
        for(WBSItemsVO vo : list){
            if(StrUtil.isEmpty(vo.getActivityOwnerList())){
                vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_IS_MY_ACTIVITY, "N");
            }else{
                if(vo.getActivityOwnerList().indexOf("^+^" + currentUserId) == -1){
                    vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_IS_MY_ACTIVITY, "N");
                }else{
                    vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_IS_MY_ACTIVITY, "Y");
                }
            }
        }
    }
    private void setDBOriginPlanStartDate(List<WBSItemsVO> wbsItemsList){
        for(WBSItemsVO vo:wbsItemsList){
            vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_originPlanStartDate, vo.getPlanStartDate());
            vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_originPlanEndDate, vo.getPlanEndDate());
        }
    }
    private void setPreviousActivity(Map<String,WBSItemsVO> activityObjectDB,List<WBSItemsVO> structuredActivityList){
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
        for(WBSItemsVO vo : structuredActivityList){
            vo.setPreviousActivityList("");
            if(isLeaf(vo)){
                List<DependencyVO> dependencyList = new ArrayList<DependencyVO>();
                makePreviousActivityListDB(vo, activityObjectDB,dependencyList);
                if(!NullUtil.isNone(dependencyList)) {
                    List<WBSItemsVO> tempList = new ArrayList<WBSItemsVO>();
                    for(DependencyVO dependencyVO : dependencyList){
                        dependencyVO.getDependentVO().setOutDataAttributeValue("makePreviousActivityDisplayName", dependencyVO.getDependencyType());
                        tempList.add(dependencyVO.getDependentVO());
                    }
                    activityObjectDB.get(vo.getObid()).setPreviousActivityList(makePreviousActivityDisplayName(tempList));
                    activityObjectDB.get(vo.getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_PREVIOUS_MAP_ATTR, 
                                                                                WBSUtil.getDisplayActivityNameAsLoc(tempList, locale));
                }
            }
        }
    }
    private String makePreviousActivityDisplayName(List<WBSItemsVO> list){
        StringBuffer strBuf = new StringBuffer();
        if(NullUtil.isNone(list)) return "";
        for(WBSItemsVO vo : list){
            String dependencyType = vo.getOutDataAttributeValue("makePreviousActivityDisplayName");
            strBuf.append("^~^").append(vo.getActivityNameEng()).append("^+^").append(vo.getActivityNameKor()).append("^+^").append(vo.getActivityNameChi()).append("^+^").append(dependencyType);
        }
        return strBuf.substring(3);
    }
//    public void makeDependencyMap(WBSItemsVO startActivityVO, Map<String,WBSItemsVO> activityObjectDB, Map<String,WBSItemsVO> nextDependencyMap, boolean isPrevious){
//        List<DependencyVO> dependencyList = new ArrayList<DependencyVO>();
//        makeDependencyActivityListDB(startActivityVO, activityObjectDB, dependencyList, isPrevious);
//        
//        for(DependencyVO dependencyVO : dependencyList){
//            if(!nextDependencyMap.containsKey(nextDependencyMap.get(dependencyVO.getDependentVO().getObid()))
//                    && !StrUtil.in(dependencyVO.getDependentVO().getStates(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED,ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT)){
//                makeDependencyMap(activityObjectDB.get(dependencyVO.getDependentVO().getObid()), activityObjectDB, nextDependencyMap, isPrevious);
//            }
//        }
//        nextDependencyMap.put(startActivityVO.getObid(), startActivityVO);
//    }
    /**
     * ���õǾ��� Activity�������� �ļ� Activity�� ���� Plan Date�� �� ����ϰ� �ļ����� �ɸ� Parent Activity�� ���� Plan Date�� Refresh��.
     *
     * @param activityVO
     * @return
     */
    public void rescheduleSuccessiveActivity(WBSActivitiesVO activityVO){
        Date currentDate = TimeServiceUtil.getDBLocalTime();
        saveSimulatedScheduleResult(activityVO,currentDate);
    }
    private Date getMaxEndDate(WBSItemsVO wbsActivitiesVO,List<WBSItemsVO> workWBSItemsList, boolean independentActivity,Map<String,WBSItemsVO> lastActivityMap){
        Date date = wbsActivitiesVO.getPlanStartDate();
        boolean isFirst = true;
        for(WBSItemsVO vo : workWBSItemsList){
            //Skip�Ǿ��� �͵� �ݵ�� Check�ؾ� ��.(���� �߿�)
            if(vo.getPlanEndDate() != null){
                if(!independentActivity){
                    if(isFirst){
                        date = vo.getPlanEndDate();
                    }
                }
                if(vo.getPlanEndDate().compareTo(date) > 0) {
                    date = vo.getPlanEndDate();
                    lastActivityMap.put("Last Activity", vo);
                }
                isFirst = false;
            }
        }
        return date;
    }
    private Integer calculateBasedDaysForDelay(Integer duration){
        if(duration == null) duration = 0;
        float fcalculated = (float)duration * 0.12f;
        int calculated = (int)fcalculated;
        if(calculated == 0) calculated = 1;
        //if(calculated > 5) calculated = 5;
        return calculated;
    }

    /**
     * ���� Reject�� Call�Ǿ����� ������ ���� ������ ���·� �ٲ�.
     */
    public void rejectApprovalProcess(){
        List<WBSItemsVO> workingList = this.getInApprovalProcessingWBSItemList();
        validateForRejectApprovalProcess(workingList);
        preProcessForRejectApprovalProcess(workingList);
        rejectApprovalProcessCore(workingList);
        postProcessForRejectApprovalProcess(workingList);
    }
    private final void validateForRejectApprovalProcess(List<WBSItemsVO> workingList){
        if(!ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_APPROVALPROCESSING.equals(this.getStates())) {
        	throw new FoundationException("Only " + ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_APPROVALPROCESSING + "can be reject.Current state is " + this.getStates());
        }
    }
    private final void preProcessForRejectApprovalProcess(List<WBSItemsVO> workingList){;}
    private final void postProcessForRejectApprovalProcess(List<WBSItemsVO> workingList){;}
    private final void rejectApprovalProcessCore(List<WBSItemsVO> workingList){
        for(WBSItemsVO wbsItemsVO : workingList){
            WBSItems wbsItemsDom = new WBSItems(wbsItemsVO);
            wbsItemsDom.demote();
        }
        this.demote();
    }
    /**
     * ���� ��û�� Call �Ǿ����� ������ ���� ������ ���·� ����Ǿ���.
     */
    public void startApprovalProcessForScheduleComplete(){
        List<WBSItemsVO> workingList = this.getWorkingWBSItemList();
        validateForStartApprovalProcessForScheduleComplete(workingList);
        preProcessForStartApprovalProcessForScheduleComplete(workingList);
        startApprovalProcessForScheduleComplete(workingList);
        postProcessForStartApprovalProcessForScheduleComplete(workingList);
    }
    protected void validateForStartApprovalProcessForScheduleComplete(List<WBSItemsVO> workingList){
        if(!ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_SAVEDRAFT.equals(this.getStates())) throw new FoundationException("Only " + ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_SAVEDRAFT + " can be Approval stated.Current state is " + this.getStates());
        ProjectsVO relatedProjectVO = this.getRelatedProject();
        if ( ! ApplicationSchemaConstants.BIZCLASS_DESIGNPROJECT.equals(relatedProjectVO.getClassName()) ) { // 2018-07-09 Design ������Ʈ ����/��� ���� ��û �и��� ���� ���� ���� validation üũ�� ���ϴ� ������ ����(Ȳ��ȯ å��)
            this.validateScheduleChanged(workingList);
        }
        this.validateActivityOwner(workingList);
        this.validateSkippedAllActivities(workingList);
        //this.validateCPUnitProfitablilty(workingList);
    }
    private void validateScheduleChanged(List<WBSItemsVO> workingList){
        if(NullUtil.isNone(workingList)){
            boolean canSkipNotChangedValidation = false;
            List<ChangeProcessVO> changeProcessCurrentList = getChangeProcessCurrentList();
            if(changeProcessCurrentList.size() > 0){
                ChangeProcess changeProcess = DomUtil.toDom(changeProcessCurrentList.get(0));
//                List<RevisedProjectsVO> revisedProjects = changeProcess.getRevisedProjects();
//                if(revisedProjects.size() > 0){
//                    // ����ǰ�ǿ��� ��ǰ�Ƿ� ����� Schedule ������ ���� ���� ����.
//                    if(!"1".equals(revisedProjects.get(0).getNextObid()) && "0".equals(revisedProjects.get(0).getRevision())){
//                        canSkipNotChangedValidation = true;
//                    }
//                }
            }
            if(!canSkipNotChangedValidation){
                throw new ApplicationException("WBS Schedule has not changed, Please check the WBS schedule.");
            }
        }else{
            if(!this.isFirst()){
                WBSItems workingItem = null;
                WBSItemsVO previousItemVO = null;
                for(WBSItemsVO vo : workingList){
                    workingItem = new WBSItems(vo);
                    previousItemVO = workingItem.getPreviousRevision();                    
                    if ( NullUtil.isNull(previousItemVO) ) return; // previous revision�� ���� activity �߰� �� �Ʒ� ���� null point ����
                    if(!previousItemVO.getPlanStartDate().equals(vo.getPlanStartDate())
                            || !previousItemVO.getPlanEndDate().equals(vo.getPlanEndDate())){
                        return;
                    }
                }
                throw new ApplicationException("WBS Schedule has not changed, Please check the WBS schedule.");
            }
        }
    }
    private void validateActivityOwner(List<WBSItemsVO> workingList){
        List<WBSItemsVO> errorList = getNotAssignedActivities();
        SortUtil.sort(errorList, "phaseName", false);
        StringBuffer errorListBuffer = new StringBuffer();
        for(WBSItemsVO vo : errorList){
            errorListBuffer.append(",<br>").append("[").append(vo.getPhaseTitles()).append("] ").append(new WBSItems(vo).getDisplayNameAsLoc());
        }
        if(errorListBuffer.length() > 1){
            throw new ApplicationException("Activity owner is not assigned. <br>" + errorListBuffer.substring(5));
        }
    }

    private void validateSkippedAllActivities(List<WBSItemsVO> workingList){
        List<WBSItemsVO> activitiyVOList = this.getWBSItemsActivityOnly();
        for(WBSItemsVO vo : activitiyVOList){
            if(StrUtil.in(this.getStates(),ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT,ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED) && "N".equals(vo.getIsSkipped())){ return; }
        }
        throw new FoundationException("plm.wbs.validation.skippedAllActivities");
    }
//    private void validateCPUnitProfitablilty(List<WBSItemsVO> activitiyVOList){
//        ProjectsVO relatedProjectVO = this.getRelatedProject();
//        Projects projectDom = new Projects(relatedProjectVO);
//        if(!projectDom.isKindOf(ApplicationSchemaConstants.BIZCLASS_MODELDEVELOPMENTPROJECTS)){
//            return;
//        }
//        if(!ApplicationSchemaConstants.STATE_PROJECT_STARTUP.equals(relatedProjectVO.getStates())){
//            return;
//        }
////        if(!projectDom.isNeedCheckCPUnitActivity()){
////            return;
////        }
//        boolean isInitialProject = projectDom.isInitialProject();
//        CodeDetailVO codeInfo = CommonUtil.getCodeInfo(relatedProjectVO.getDivisionCode(), "DIV_CPUNIT", ApplicationBizConstants.COMPANY_LGE);// CP Unit ��� �����
//        String cpUnitActivityCode = ApplicationBizConstants.CP_UNIT_PROFIT_ACTIVITY;
//        if(codeInfo != null && "VC".equals(codeInfo.getAttribute01())){
//            if( StrUtil.defaultString(relatedProjectVO.getProjectGradeCode()).startsWith("R") ){
//                return;
//            }
//            cpUnitActivityCode = ApplicationBizConstants.CP_UNIT_PROFIT_ACTIVITY_VC;
//        }
//        Map<String, WBSActivitiesVO> cpUnitActivityMap = new HashMap<String, WBSActivitiesVO>();
//        for(WBSItemsVO vo : activitiyVOList){
//            if(vo instanceof WBSActivitiesVO){
//                if( StrUtil.isNotEmpty(vo.getOriginActivityCode()) 
//                    && cpUnitActivityCode.contains( vo.getOriginActivityCode() ) && !"Y".equals(vo.getIsSkipped())){
//                    cpUnitActivityMap.put(vo.getOriginActivityCode(), (WBSActivitiesVO)vo);
//                }
//            }
//        }
//        boolean hasCPUnitActivity = false;
//        if(ApplicationBizConstants.CP_UNIT_PROFIT_ACTIVITY_VC.equals(cpUnitActivityCode)){
//            if(cpUnitActivityMap.get(cpUnitActivityCode) != null){
//                hasCPUnitActivity = true;
//            }
//        }else if (ApplicationBizConstants.CP_UNIT_PROFIT_ACTIVITY.equals(cpUnitActivityCode)){
//            // ACT0000000702 �ʼ�, ACT0000000008,ATM-00000000867,ATM-00000000868 �� �� �ϳ� 
//            if(// 702 Validation ���� cpUnitActivityMap.get("ACT0000000702") != null && 
//                    (cpUnitActivityMap.get("ACT0000000008") != null
//                    || cpUnitActivityMap.get("ATM-00000000867") != null
//                    || cpUnitActivityMap.get("ATM-00000000868") != null
//                            )){
//                hasCPUnitActivity = true;
//            }
//        }
//        if( relatedProjectVO.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_MODELDEVELOPMENTGENERALPROJECT) ){
//            if(codeInfo != null){
//                if(isInitialProject){
//                    if(!hasCPUnitActivity){
//                        throw new ApplicationException("plm.wbs.validation.cpUnit.initialProject");
//                    }
//                }else{
//                    if(hasCPUnitActivity){
//                        throw new ApplicationException("plm.wbs.validation.cpUnit.project");
//                    }
//                }
//            }else{
//                if(hasCPUnitActivity){
//                    throw new ApplicationException("plm.wbs.validation.cpUnit.skipActivity");
//                }
//            }
//        }
//    }
    protected void preProcessForStartApprovalProcessForScheduleComplete(List<WBSItemsVO> workingList){;}
    protected void postProcessForStartApprovalProcessForScheduleComplete(List<WBSItemsVO> workingList){
        ;//this.setMPDate(workingList);
    }
//    
//    private void setMPDate(List<WBSItemsVO> workingList){
//        List<CodeDetailVO> codeListAsAttribute = CommonUtil.getCodeListAsAttribute(null, "SYSTEM_ACTIVITY", ApplicationBizConstants.COMPANY_LGE, "PROJECT_COMPLETE", null, null);
//        
//        Set<String> activityNameSet = new HashSet<String>();
//        for(CodeDetailVO codeDetailVO : codeListAsAttribute){
//            activityNameSet.add(codeDetailVO.getNames());
//        }
//        Date mpDate = null;
//        for(WBSItemsVO wbsItemsVO : workingList){
//            if("Y".equals(wbsItemsVO.getIsSkipped())){continue;}
//            if(wbsItemsVO instanceof WBSPhasesVO){
//                if("PHASE6".equals(wbsItemsVO.getPhaseName())){
//                    mpDate = wbsItemsVO.getPlanEndDate();
//                }
//            }else if(wbsItemsVO instanceof WBSActivitiesVO){
//                if(activityNameSet.contains(wbsItemsVO.getOriginActivityCode())){
//                    mpDate = wbsItemsVO.getPlanEndDate(); break;
//                }
//            }
//        }
//        if(!NullUtil.isNull(mpDate)){
//            Projects projectsDom = new Projects(this.getRelatedProject());
//            projectsDom.getVo().setMpDate(mpDate);
//            projectsDom.modifyObject();
//        }
//    }
//    
    private void startApprovalProcessForScheduleComplete(List<WBSItemsVO> workingList){
        //���� ��û�� First Flag �� Last Flag Setting��.
        this.setFirstAndLastActivity();
        for(WBSItemsVO wbsItemsVO : workingList){
            WBSItems wbsItemsDom = new WBSItems(wbsItemsVO);
            wbsItemsDom.startApprovalProcess(new HashMap<String,Object>());
        }
        this.promote();
    }
    /**
     * ������Ʈ ������ ���� Ȯ���Ѵ�.
    */
    public void completeProjectWBSSchedule(){
        List<WBSItemsVO> workingList = this.getInApprovalProcessingWBSItemList();
        validateForCompleteProjectWBSSchedule(workingList);
        preProcessForCompleteProjectWBSSchedule(workingList);
        completeProjectWBSScheduleCore(workingList);
        postProcessForCompleteProjectWBSSchedule(workingList);
    }
    private final void validateForCompleteProjectWBSSchedule(List<WBSItemsVO> workingList){
        if(!ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_APPROVALPROCESSING.equals(this.getStates())) 
        	throw new FoundationException("Only " + ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_APPROVALPROCESSING + " can be Approval Completed.Current state is " + this.getStates());
    }
    private final void preProcessForCompleteProjectWBSSchedule(List<WBSItemsVO> workingList){;}
    private final void postProcessForCompleteProjectWBSSchedule(List<WBSItemsVO> workingList){
        //���� Revision���� �Ϸ� �Ǿ��� ���� ������ �����;� ��.
        ProjectScheduleVO previousScheduleVO = this.getPreviousRevision();
        if(!this.isFirst()){
            ProjectSchedule previousScheduleDom = new ProjectSchedule(previousScheduleVO);
            List<WBSItemsVO> previousList = previousScheduleDom.getWBSItemAll();
            for(WBSItemsVO wbsItemsVO : previousList){
                if(wbsItemsVO.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED)){
                    if(!StrUtil.isEmpty((String)wbsItemsVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))){
                        for(WBSItemsVO workItemsVO : workingList){
                            if(wbsItemsVO.getNames().equals(workItemsVO.getNames()) && !workItemsVO.getIsReDoActivity().equals("Y")){
                                WBSItems workItemsDom = new WBSItems(workItemsVO);
                                workItemsDom.changePolicyAndState(workItemsDom.getLifeCycle(), wbsItemsVO.getStates());
                            }
                        }
                    }
                }
            }
        }
        if(!this.isFirst()) createIfTriggerDataForScheduleChange();
    }
    public void createIfTriggerDataForScheduleChange(){
        IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO(this.getVo(), ApplicationBizConstants.TR_ID_PROJECT_COM_OUT, ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_WBS_HEADER_EACH);
        TriggerUtil.createDataByTrigger(triggerVO);
    }
    /**
     * Activity�� First and Last Flag�� Setting��.
     * Skipped�� �����ϰ� Setting��.
     */
    public void setFirstAndLastActivity(){
        Set<String> attributes = new HashSet<String>();
        attributes.add("isFirstActivity");attributes.add("isLastActivity");
        List<WBSItemsVO> allList = this.getWBSItemAll(false);
        for(WBSItemsVO vo : allList){
            vo.setIsLastActivity("N");
            vo.setIsFirstActivity("N");
        }
        ObjectRoot.modifyObjectSetBatch(allList, attributes);
        attributes = new HashSet<String>();

        attributes.add("isFirstActivity");attributes.add("isLastActivity");
        List<WBSItemsVO> tempList = this.getWBSItemsStructureList(true,false,false);
        WBSUtil.setFirstAndLastFlag(tempList,false);
        ObjectRoot.modifyObjectSetBatch(tempList, attributes);
    }
    public final ProjectScheduleVO reviseSchedule(ProjectsVO projectVO, Map<String, Object> map){
        validateForReviseSchedule(projectVO,map);
        preProcessForReviseSchedule(projectVO,map);
        ProjectScheduleVO revisedProjectScheduleVO = reviseScheduleCore(projectVO,map);
        postProcessForReviseSchedule(projectVO,map);
        return revisedProjectScheduleVO;
    }
    private final ProjectScheduleVO reviseScheduleCore(ProjectsVO projectVO, Map<String, Object> map){

        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();

        Map<String, Object> reviseMp = new HashMap<String, Object>();
        reviseMp.put(ProjectConstants.REVISE_SCHEDULE_MAP_PROJECTVO, projectVO);

        List<ControlledByProjectScheduleContextVO> createdContextList = this.getRelationship(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT,
                ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_FROM,
                selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());

        List<BusinessRelationObjectVO> allRelationList = new ArrayList<BusinessRelationObjectVO>();
        ProjectScheduleVO revisedVO = this.revise(reviseMp);
        for(ControlledByProjectScheduleContextVO vo : createdContextList){
            vo.setToObid(revisedVO.getObid());
        }
        allRelationList.addAll(createdContextList);
        selectPattern.setLength(0);wherePattern.setLength(0);parameterPattern.setLength(0);
        List<HasSubWBSItemsVO> hasSubWBSItemsList = this.getRelationship(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS,
                ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_TO,
                selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        for(HasSubWBSItemsVO vo : hasSubWBSItemsList){
            vo.setFromObid(revisedVO.getObid());
        }
        allRelationList.addAll(hasSubWBSItemsList);
        ObjectRoot.createObjectSetBatch(allRelationList);
        return revisedVO;
    }
    public final void validateForReviseSchedule(ProjectsVO projectVO, Map<String, Object> map){
        if(!StrUtil.in(this.getStates(),ProjectConstants.SCHEDULE_REVISIBLE_STATE_SET)) throw 
        new FoundationException("Only " + ProjectConstants.SCHEDULE_REVISIBLE_STATE_SET + " can be revised.Current state is this.getStates()");
    }
    public final void preProcessForReviseSchedule(ProjectsVO projectVO, Map<String, Object> map){
    }
    public final void postProcessForReviseSchedule(ProjectsVO projectVO, Map<String, Object> map){
    }
    /**
     * ���� ������Ʈ�� ������ �°� Start��.(Activity�� ���ؼ� �Ҵ�� ����ڿ��� To-Do�� ������Ŵ
     */
    public void startProjectExecution(Map<String,WBSItemsVO> activityObjectDB,Date actionDate){
        List<WBSItemsVO> completedList = new ArrayList<WBSItemsVO>();
        validateForStartProjectExecution(activityObjectDB,actionDate);
        List<WBSActivityWorkflowVO> activityListForInboxList = new ArrayList<WBSActivityWorkflowVO>();
        List<WBSItemsVO> tempStartableList = this.getStartableWBSItemList();
        List<WBSItemsVO> startableList = new ArrayList<WBSItemsVO>();
        for(WBSItemsVO vo : tempStartableList){
            startableList.add(activityObjectDB.get(vo.getObid()));
        }
        preProcessForStartProjectExecution(activityObjectDB,actionDate,startableList);
        startProjectExecutionCore(activityObjectDB,actionDate,startableList,activityListForInboxList,completedList);
        postProcessForStartProjectExecution(activityObjectDB,actionDate,startableList,activityListForInboxList,completedList);
    }

    public final void validateForStartProjectExecution(Date actionDate){
        if(!StrUtil.in(this.getStates(),ProjectConstants.SCHEDULE_STARTABLE_STATE_SET)) throw new FoundationException("Only " + StrUtil.convertArrayToList(ProjectConstants.SCHEDULE_STARTABLE_STATE_SET) + "can be startable.Current state is " + this.getStates() + ".");
    }
    public final void validateForStartProjectExecution(Map<String,WBSItemsVO> activityObjectDB,Date actionDate){
        if(!StrUtil.in(this.getStates(),ProjectConstants.SCHEDULE_STARTABLE_STATE_SET)) throw new FoundationException("Only " + StrUtil.convertArrayToList(ProjectConstants.SCHEDULE_STARTABLE_STATE_SET) + "can be startable.Current state is " + this.getStates() + ".");
    }

    public final void preProcessForStartProjectExecution(Date actionDate,List<WBSItemsVO> startableList){
    }
    public final void preProcessForStartProjectExecution(Map<String,WBSItemsVO> activityObjectDB, Date actionDate,List<WBSItemsVO> startableList){
    }

    public final void postProcessForStartProjectExecution(Date actionDate,List<WBSItemsVO> startableList, List<WBSActivityWorkflowVO> activityListForInboxList,List<WBSItemsVO> completedList){
        WBSItems.createIfTriggerDataForCompleteActivity(completedList);
    }
    public final void postProcessForStartProjectExecution(Map<String,WBSItemsVO> activityObjectDB,Date actionDate,List<WBSItemsVO> startableList, List<WBSActivityWorkflowVO> activityListForInboxList,List<WBSItemsVO> completedList){
        WBSItems.createIfTriggerDataForCompleteActivity(completedList);
    }
    private void startProjectExecutionCore(Map<String,WBSItemsVO> activityObjectDB,Date actionDate,List<WBSItemsVO> startableList,List<WBSActivityWorkflowVO> activityListForInboxList, List<WBSItemsVO> completedList){
        for(WBSItemsVO wbsItemsVO : startableList){
            WBSItems wbsItemsDom = new WBSItems(wbsItemsVO);
            wbsItemsDom.startActivityForScheduleStart(activityObjectDB,actionDate,this.getVo(),activityListForInboxList,completedList,new HashMap<String,Object>(),1);
        }
        if(this.getVo().getActualStartDate() == null){
            this.getVo().setActualStartDate(actionDate);
            Set<String> attributes = new HashSet<String>();
            attributes.add("actualStartDate");
            ObjectRoot.modifyObjectSetBatch(this.getVo(), attributes);
        }
        this.promote();
        if(!this.isFirst()){
            ProjectSchedule previousSchedule = new ProjectSchedule((ProjectScheduleVO)this.getPreviousRevision());
            previousSchedule.changePolicyAndState(this.getLifeCycle(), ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_INACTIVE);
        }
        WBSWorkflowServiceUtil.txnBuildWBSActivityWorkflowList(activityListForInboxList);
    }
    /**
     * Project Member ������ ������ Activity Assigned ������ Refresh��.
     */
    public void refreshActivityAssigned(){
        Set<String> statesSet = new HashSet<String>();
        statesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT);
        statesSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED);
        List<WBSItemsVO> wbsItemsList = this.getWBSItemsActivityOnly(statesSet);
        List<ProjectPersonVO>      activityPersonList = WBSItems.getAssignedProjectPersonList(wbsItemsList);
        List<ProjectDefinedRoleVO> activityDefinedRoleList = WBSItems.getAssignedProjectDefinedRoleList(wbsItemsList);

        Projects projectsDom = new Projects(this.getRelatedProject());
        List<ProjectDefinedRoleVO> pjtDefinedRoleList = projectsDom.getProjectRoles();
        Map<String,Object> roleMap = new HashMap<String,Object>();
        for(ProjectDefinedRoleVO pjtDefinedRoleVO : pjtDefinedRoleList){
            ProjectDefinedRole pjtDefinedRoleDom = new ProjectDefinedRole(pjtDefinedRoleVO);
            List<ProjectPersonVO> pjtPersonList = pjtDefinedRoleDom.getMemberList(null,"Y");
            pjtDefinedRoleVO.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MEMBER_MAP_PersonList, pjtPersonList);
            roleMap.put(pjtDefinedRoleVO.getObid(), pjtDefinedRoleVO);
        }
        for(WBSItemsVO wbsItemsVO : wbsItemsList){
            List<ProjectPersonVO> newPersonList = getNewAddedProjectPersonList(wbsItemsVO.getObid(),activityPersonList,activityDefinedRoleList,roleMap);
            List<AssignedToActivityVO> assignedToActivityList = new ArrayList<AssignedToActivityVO>();
            if(!NullUtil.isNone(newPersonList)){
                WBSItems wbsItemsDom = new WBSItems(wbsItemsVO);
                for(ProjectPersonVO personVO : newPersonList){
                    AssignedToActivityVO assignedToActivityVO = new AssignedToActivityVO();
                    assignedToActivityVO.setClassName(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY);
                    assignedToActivityVO.setFromClass(wbsItemsDom.getClassName());
                    assignedToActivityVO.setFromObid(wbsItemsDom.getObid());
                    assignedToActivityVO.setToClass(personVO.getClassName());
                    assignedToActivityVO.setToObid(personVO.getObid());
                    assignedToActivityVO.setIsMainMember("Y");
                    assignedToActivityVO.setActionUserId("None");
                    assignedToActivityList.add(assignedToActivityVO);
                }
            }
            if(!NullUtil.isNone(assignedToActivityList)){
                ObjectRoot.createObjectSetBatch(assignedToActivityList);
            }
            if(!NullUtil.isNull(findActivityDefinedRole(wbsItemsVO.getObid(),activityDefinedRoleList))){
                removeAssignedProjectPersonAsRole(wbsItemsVO.getObid(),activityPersonList,activityDefinedRoleList,roleMap);
            }
        }
        this.refreshActivityOwnerListAll();
    }

    private void removeAssignedProjectPersonAsRole(String activityObid, List<ProjectPersonVO> activityPersonList,List<ProjectDefinedRoleVO> activityDefinedRoleList,Map<String,Object> roleMap){
        Map<String,ProjectPersonVO> personMap = findNewProjectPersonList(activityObid,activityDefinedRoleList,roleMap);
        Set<String> projectPersonObidSet = personMap.keySet();
        List<ProjectPersonVO> projectPersonList = findAlreadyProjectPersonList(activityObid, activityPersonList);
        for(ProjectPersonVO vo : projectPersonList){
            if(!projectPersonObidSet.contains(vo.getObid())){
                new AssignedToActivity((String)vo.getOutDataAttributeValue("rel_obid")).deleteObject();
            }
        }
    }

    /**
     * Leaf Levle�� ��� Activity�� ���ؼ� Ower List�� �ϰ� Refresh��.
     */
    public void refreshActivityOwnerListAll(){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();

        List<WBSItemsVO> activityOnlyList = this.getWBSItemsActivityOnly();

        List<ProjectPersonVO> projectPersonList = ObjectRoot.getRelatedObjectSet(activityOnlyList, ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, ApplicationSchemaConstants.BIZCLASS_PROJECTPERSON,
                GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        for(WBSItemsVO vo : activityOnlyList){
            vo.setActivityOwnerList(findActivityOwnerList(vo.getObid(),projectPersonList));
        }
        Set<String> attributes = new HashSet<String>();
        attributes.add("activityOwnerList");
        ObjectRoot.modifyObjectSetBatch(activityOnlyList, attributes);
    }
    /**
     * �� Activity�� ���� Owner List�� Concate�ؼ� Return��.
     *
     * @param activityObid
     * @param projectPersonList
     * @return
     */
    private String findActivityOwnerList(String activityObid, List<ProjectPersonVO> projectPersonList){
        StringBuffer ownerList = new StringBuffer();
        for(ProjectPersonVO vo : projectPersonList){
            if(activityObid.equals(vo.getOutDataAttributeValue("rel_fromObid"))){
                ownerList.append("^~^").append(vo.getTitles()).append("^+^").append(vo.getUserId());
            }
        }
        if(StrUtil.isEmpty(ownerList)) return "None";
        return ownerList.substring(3);
    }
    /**
     * �߰� ���� ���� ������Ʈ Member������ Return��.
     *
     * @param activityObid
     * @param activityPersonList
     * @param activityDefinedRoleList
     * @param roleMap
     * @return
     */
    private List<ProjectPersonVO> getNewAddedProjectPersonList(String activityObid, List<ProjectPersonVO> activityPersonList,List<ProjectDefinedRoleVO> activityDefinedRoleList,Map<String,Object> roleMap){

        List<ProjectPersonVO> newPersonList = new ArrayList<ProjectPersonVO>();
        Set<String> alreadySet = findAlreadyProjectPersonObidSet(activityObid,activityPersonList);
        Map<String,ProjectPersonVO> personMap = findNewProjectPersonList(activityObid,activityDefinedRoleList,roleMap);
        for(String obid : personMap.keySet()){
           if(!StrUtil.in(obid, alreadySet)) newPersonList.add(personMap.get(obid));
        }
        return newPersonList;
    }
    /**
     * �̹� �߰� �Ǿ��� ������Ʈ Member������ Return��.
     *
     * @param activityObid
     * @param activityPersonList
     * @return
     */
    private Set<String> findAlreadyProjectPersonObidSet(String activityObid, List<ProjectPersonVO> activityPersonList){
        Set<String> activityPersonObidSet = new HashSet<String>();
        for(ProjectPersonVO vo : activityPersonList){
            if(activityObid.equals(vo.getOutDataAttributeValue("rel_fromObid"))){
                activityPersonObidSet.add(vo.getObid());
            }
        }
        return activityPersonObidSet;
    }
    private List<ProjectPersonVO> findAlreadyProjectPersonList(String activityObid, List<ProjectPersonVO> activityPersonList){
        List<ProjectPersonVO> personList = new ArrayList<ProjectPersonVO>();
        for(ProjectPersonVO vo : activityPersonList){
            if(activityObid.equals(vo.getOutDataAttributeValue("rel_fromObid"))){
                personList.add(vo);
            }
        }
        return personList;
    }
    private Map<String,ProjectPersonVO> findNewProjectPersonList(String activityObid, List<ProjectDefinedRoleVO> activityDefinedRoleList, Map<String,Object> roleMap){
        Map<String,ProjectPersonVO> personMap = new HashMap<String,ProjectPersonVO>();
        for(ProjectDefinedRoleVO definedRoleVO : activityDefinedRoleList){
            String temp = definedRoleVO.getOutDataAttributeValue("rel_fromObid");
            if(activityObid.equals(temp)){
                ProjectDefinedRoleVO pjtDefinedRoleVO = (ProjectDefinedRoleVO)roleMap.get(definedRoleVO.getObid());
                List<ProjectPersonVO> pjtPersonList = pjtDefinedRoleVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MEMBER_MAP_PersonList);
                for(ProjectPersonVO vo : pjtPersonList){
                    personMap.put(vo.getObid(), vo);
                }
            }
        }
        return personMap;
    }
    private ProjectDefinedRoleVO findActivityDefinedRole(String activityObid, List<ProjectDefinedRoleVO> activityDefinedRoleList){
        for(ProjectDefinedRoleVO definedRoleVO : activityDefinedRoleList){
            if(activityObid.equals(definedRoleVO.getOutDataAttributeValue("rel_fromObid"))){
                return definedRoleVO;
            }
        }
        return null;
    }
    public void refreshSkipInfo(String projectObid){
        
    }
    public void refreshSkipInfoAsGrade(String wbsTemplateObid, String gradeCode){
        this.refreshSkipInfoAsGrade(wbsTemplateObid, gradeCode, new HashSet<String>());
    }
    public void refreshSkipInfoAsGrade(String wbsTemplateObid, String gradeCode, Set<String> skippedPhaseSet){
        List<WBSItemsVO> structureList = this.getWBSItemsStructureList(false, false, false);
        WBSTemplateMaster templateMaster = new WBSTemplateMaster(wbsTemplateObid);
        List<WBSItemTemplatesVO> wbsItemTemplatesActivityOnly = templateMaster.getWBSItemTemplates(false);
        Map<String, WBSItemTemplatesVO> templateMap = new HashMap<String, WBSItemTemplatesVO>(); 
        for(WBSItemTemplatesVO vo : wbsItemTemplatesActivityOnly){templateMap.put(vo.getNames(), vo);}
        String skipInfo = "";
        String canBeSkipped = "";
        WBSItemTemplatesVO templateVO = null;
        for(WBSItemsVO wbsItemsVO : structureList){
            if(!wbsItemsVO.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT)){continue;}
            templateVO = templateMap.get(wbsItemsVO.getTemplateName());
            if(!NullUtil.isNull(templateVO)){
                if(skippedPhaseSet.contains(wbsItemsVO.getPhaseName())){
                    canBeSkipped = "Y";
                    skipInfo     = "Y";
                    wbsItemsVO.setIsSkipped("Y");
                }else{
                    skipInfo = templateMaster.getSkipInfo(gradeCode, templateVO.getSkipInfo());
                    if(StrUtil.isEmpty(skipInfo)) skipInfo = "Y";
                    if(ProjectConstants.NA.equals(skipInfo)){
                        wbsItemsVO.setIsSkipped("Y");
                        canBeSkipped = "Y";
                    }else{
                        wbsItemsVO.setIsSkipped("N");
                        if(skipInfo.equals("R")) canBeSkipped = "N";
                        if(skipInfo.equals("Y")) canBeSkipped = "Y";
                    }
                }
                wbsItemsVO.setCanBeSkipped(canBeSkipped);
            }
            wbsItemsVO.setBaselinedStartDate((Date)null);
            wbsItemsVO.setBaselinedEndDate((Date)null);
        }
        Map<String, WBSItemsVO> wbsdb = this.getWBSDB(structureList);
        setParentSkipFlags(structureList,wbsdb);
        WBSUtil.setFirstAndLastFlag(structureList,true);
        Set<String> attributes = new HashSet<String>();
        attributes.add("canBeSkipped");attributes.add("isSkipped");
        attributes.add("isFirstActivity");attributes.add("isLastActivity");
        attributes.add("baselinedStartDate");attributes.add("baselinedEndDate");
        ObjectRoot.modifyObjectSetBatch(structureList, attributes);
        Date currentDate = TimeServiceUtil.getTruncatedDBLocalDate();
        saveSimulatedScheduleResult(currentDate, structureList, wbsdb);
    }

    /**
     * ������Ʈ ������ ���� Ȯ���Ѵ�.
    */
    private void completeProjectWBSScheduleCore(List<WBSItemsVO> workingList){
        cleansingGarbageRelationForComplete(workingList);
        for(WBSItemsVO wbsItemsVO : workingList){
            if(wbsItemsVO.getBaselinedStartDate() == null){
                wbsItemsVO.setBaselinedStartDate(wbsItemsVO.getPlanStartDate());
                wbsItemsVO.setBaselinedEndDate(wbsItemsVO.getPlanEndDate());
            }
            WBSItems wbsItemsDom = new WBSItems(wbsItemsVO);
            wbsItemsDom.completeProjectWBSSchedule(new HashMap<String,Object>());
        }
        Set<String> attributes = new HashSet<String>();
        if(this.isFirst()){
            attributes.add("baselinedStartDate");attributes.add("baselinedEndDate");
            this.getVo().setBaselinedStartDate(this.getVo().getPlanStartDate());
            this.getVo().setBaselinedEndDate(this.getVo().getPlanEndDate());
            ObjectRoot.modifyObjectSetBatch(this.getVo(), attributes);
        }
        attributes = new HashSet<String>();
        attributes.add("baselinedStartDate");attributes.add("baselinedEndDate");
        ObjectRoot.modifyObjectSetBatch(workingList, attributes);
        this.refreshWorkflowObjects(workingList);
        this.promote();
    }
    /**
     * ���� �������� ���� Activity�� ���ؼ� �ϰ� ���ο� Activity�� �����ϰ� �ش� Workflow Object�� ���ؼ� Process Timestamp(Schedule Obid)�� ���� �ű� Schedule Obid�� Update��.
     *
     * @param workingList
     */
    private final void refreshWorkflowObjects(List<WBSItemsVO> workingList){
        List<WBSItemsVO> previousWBSItemsList = new ArrayList<WBSItemsVO>();
        List<WBSItems> previousWBSItemsDomList = new ArrayList<WBSItems>();
        Map<String,String> objMappingMap = new HashMap<String,String>();

        if(!this.isFirst()){
            ProjectScheduleVO previousScheduleVO = this.getPreviousRevision();
            ProjectSchedule previousScheduleDom = new ProjectSchedule(previousScheduleVO);
            Set<String> strStateSet = new HashSet<String>();
            strStateSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED);
            
            List<WBSItemsVO> previousStartedActivityListAll = previousScheduleDom.getWBSItemsActivityOnly(strStateSet);
            List<WBSItemsVO> notRevisedpreviousStartedActivityList  = new ArrayList<WBSItemsVO>();
            
            for(WBSItemsVO statedPreVo : previousStartedActivityListAll){
                boolean isRevised = false;
                for(WBSItemsVO revisedVO : workingList){
                    if(statedPreVo.getNames().equals(revisedVO.getNames())){isRevised = true;break;}
                }
                if(!isRevised) notRevisedpreviousStartedActivityList.add(statedPreVo);
            }
            //Revise���� ���� Start�Ǿ��� Activity�� Schedule Oibd�� �ֽ� Schedule Obid�� Refresh�ؾ� ��.(���: Route, Inbox Task)
            for(WBSItemsVO statedPreVo : notRevisedpreviousStartedActivityList){
                WBSItems statedPreDom = new WBSItems(statedPreVo);
                List<BusinessObjectRootVO> voList = statedPreDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE + "," + ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK,
                        ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE + "," + ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK, GlobalConstants.FLAG_TYPE_TO, 2);
                for(BusinessObjectRootVO vo : voList){
                    if(vo instanceof WorkflowRouteVO){
                        ((WorkflowRouteVO)vo).setProcessTimestamp(this.getObid());
                    }else if(vo instanceof WorkflowInboxTaskVO){
                        ((WorkflowInboxTaskVO)vo).setProcessTimestamp(this.getObid());
                    }else{
                        throw new FoundationException("Workflow data is incorrect.");
                    }
                }
                Set<String> attributes = new HashSet<String>();
                attributes.add("processTimestamp");
                ObjectRoot.modifyObjectSetBatch(voList, attributes);
            }
        }

        for(WBSItemsVO wbsItemsVO : workingList){
            //N�� ������(����� ����̹Ƿ� ���ο� �������� �ʱ� �۾�ó�� �̷������ ��)�� ���ؼ��� �ݿ��ؼ��� �ȵ�.
            if((!wbsItemsVO.getIsReDoActivity().equals("Y")) && !wbsItemsVO.getNextObid().equals("1")){
                objMappingMap.put(wbsItemsVO.getNextObid(), wbsItemsVO.getObid());
                objMappingMap.put(wbsItemsVO.getObid(), wbsItemsVO.getNextObid());
                WBSItems previousWBSItemsDom = new WBSItems(wbsItemsVO.getNextObid());
                previousWBSItemsList.add(previousWBSItemsDom.getVo());
                previousWBSItemsDomList.add(previousWBSItemsDom);
            }
        }

        for(WBSItems wbsItemsDom : previousWBSItemsDomList){
            List<BusinessObjectRootVO> voList = wbsItemsDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE + "," + ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK,
                    ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE + "," + ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK, GlobalConstants.FLAG_TYPE_TO, 2);
            //Inbox ���� Start�Ǿ��� ���� ���� ���¸� Started�� �ٲ���� ��.
            if(NullUtil.isNone(voList)){
                if(wbsItemsDom.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED)){
                    WBSItemsVO nextVO = wbsItemsDom.getNextRevision();
                    WBSItems   nextDom = new WBSItems(nextVO);
                    if(!wbsItemsDom.isLast() && nextDom.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED)){
                        nextDom.changePolicyAndState(nextDom.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED);
                    }
                }
            }else{
                for(BusinessObjectRootVO vo : voList){
                    if(vo instanceof WorkflowRouteVO){
                        ((WorkflowRouteVO)vo).setProcessTimestamp(this.getObid());
                        WorkflowObjectRoute workflowObjectRouteDom = new WorkflowObjectRoute((String)vo.getOutDataAttributeValue("rel_obid"));
                        WBSItems newItemsDom = new WBSItems(objMappingMap.get(vo.getOutDataAttributeValue("rel_fromObid")));
                        workflowObjectRouteDom.changeFromObject(newItemsDom.getVo());
                        //�̹� �����ִ� ���� ������� �����ϹǷ� ���¸� Started�� �ٱž� �Ѵ�.
                        newItemsDom.changePolicyAndState(newItemsDom.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED);
                    }else if(vo instanceof WorkflowInboxTaskVO){
                        ((WorkflowInboxTaskVO)vo).setProcessTimestamp(this.getObid());
                    }else{
                        throw new FoundationException("Workflow data is incorrect.");
                    }
                }
                Set<String> attributes = new HashSet<String>();
                attributes.add("processTimestamp");
                ObjectRoot.modifyObjectSetBatch(voList, attributes);
            }
        }
    }
    /**
     * ���� ���� Complete�� Garbage Relation����, ���� ��û�� �۾��ϸ� �ȵ�.
     *
     * @param workingList
     */

    private void  cleansingGarbageRelationForComplete(List<WBSItemsVO> workingList){
        String relationPattern = ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS + "," + (ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY);
        for(WBSItemsVO wbsItemsVO : workingList){
            WBSItems wbsItemsDom = new  WBSItems(wbsItemsVO);
            List<WBSItemsVO> wbsItemsList = wbsItemsDom.getRelatedObjects(relationPattern, ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_ALL);
            for(WBSItemsVO relWBSItemsVO : wbsItemsList){
                if(!relWBSItemsVO.getPreviousObid().equals("1")){
                    BusinessRelationObject relDom = new BusinessRelationObject((String)relWBSItemsVO.getOutDataAttributeValue("rel_obid"),false);
                    relDom.deleteObject();
                }
            }
        }
    }

    /**
     * ���� Simmulation�� Group Activity�� ���ؼ� ������ ��� ��.
     *
     * @param calculatedList
     */
    public static void setWBSScheduleForGroupAcivity(List<WBSItemsVO> calculatedList, boolean forcastOnly,boolean skipForcated){
        List<WBSItemsVO> groupActivityList = getGroupAcivity(calculatedList);
        SortUtil.sort(groupActivityList, "uniqueString", true);
        for(WBSItemsVO wbsItemsVO : groupActivityList){
            setGroupAcivityPlanDate(wbsItemsVO,calculatedList,forcastOnly,skipForcated);
        }
    }
    public void setForcastedDateForGroupAcivity(List<WBSItemsVO> structuredList,Map<String,WBSItemsVO> activityObjectDB){
        List<WBSItemsVO> groupActivityList = getGroupAcivity(structuredList, activityObjectDB);
        SortUtil.sort(groupActivityList, "explodedDepth", true);
        for(WBSItemsVO wbsItemsVO : groupActivityList){
            setGroupAcivityForcastedDate(wbsItemsVO,activityObjectDB);
        }
    }
    private List<WBSItemsVO> getGroupAcivity(List<? extends WBSItemsVO> rescheduledActivityList, Map<String,WBSItemsVO> activityObjectDB){
        List<WBSItemsVO> groupActivityList = new ArrayList<WBSItemsVO>();
        Set<String> strSet = new HashSet<String>();
        for(WBSItemsVO wbsItemsVO : rescheduledActivityList){
            List<WBSItemsVO> tempActivityList = new ArrayList<WBSItemsVO>();
            getParentWBSItemist(wbsItemsVO,activityObjectDB,tempActivityList);
            for(WBSItemsVO groupVO : tempActivityList){
                strSet.add(groupVO.getObid());
            }
        }
        for(String obid : strSet){
            groupActivityList.add(activityObjectDB.get(obid));
        }
        return groupActivityList;
    }

    private void getParentWBSItemist(WBSItemsVO wbsItemsVO,Map<String,WBSItemsVO> activityObjectDB,List<WBSItemsVO> groupActivityList){
        WBSItemsVO parentWBSItemsVO = activityObjectDB.get(wbsItemsVO.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_PARENT);
        if(NullUtil.isNull(parentWBSItemsVO)) return;
        groupActivityList.add(parentWBSItemsVO);
        this.getParentWBSItemist(parentWBSItemsVO,activityObjectDB,groupActivityList);
    }
    /**
     * Group Activity�� ���� Duration �� Plant Start, End Date�� Setting��.
     *
     * @param planningItemsVO
     * @param calculatedList
     */
    private static void setGroupAcivityPlanDate(WBSItemsVO planningItemsVO, List<WBSItemsVO> calculatedList, boolean forcastOnly, boolean skipForcated){
        Date planStartDate    = new Date();
        Date planEndDate      = new Date();
        Date forcastedEndDate = new Date();
        boolean isFirst = true;
        for(WBSItemsVO wbsItemsVO : calculatedList){
            if(!wbsItemsVO.getObid().equals(planningItemsVO.getObid())){
                if(wbsItemsVO.getUniqueString().startsWith(planningItemsVO.getUniqueString()) && wbsItemsVO.getIsSkipped().equals("N")){
                    if(!skipForcated){
                        if(isFirst) forcastedEndDate = wbsItemsVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate);
                        if(forcastedEndDate == null) wbsItemsVO.getPlanEndDate();
                    }
                    if(isFirst) planStartDate = wbsItemsVO.getPlanStartDate();
                    if(isFirst) planEndDate   = wbsItemsVO.getPlanEndDate();

                    Date tempForcastedEndDate = wbsItemsVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate);
                    if(tempForcastedEndDate == null) tempForcastedEndDate = wbsItemsVO.getPlanStartDate();
                    if(planStartDate.compareTo(wbsItemsVO.getPlanStartDate()) > 0) planStartDate = wbsItemsVO.getPlanStartDate();
                    if(planEndDate.compareTo(wbsItemsVO.getPlanEndDate()) < 0) planEndDate = wbsItemsVO.getPlanEndDate();
                    if(!skipForcated) if(forcastedEndDate.compareTo(tempForcastedEndDate) < 0) forcastedEndDate = tempForcastedEndDate;
                    isFirst = false;
                }
            }
        }
        if(!forcastOnly){
            planningItemsVO.setPlanStartDate(planStartDate);
            planningItemsVO.setPlanEndDate(planEndDate);
            int diffInDays = (int)( (planEndDate.getTime() - planStartDate.getTime()) / (1000 * 60 * 60 * 24) );
            planningItemsVO.setPlanDuration(diffInDays);
        }
        if(!skipForcated) planningItemsVO.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate, forcastedEndDate);
    }
    private static void setGroupAcivityForcastedDate(WBSItemsVO groupActivityVO, Map<String,WBSItemsVO> activityObjectDB){
        List<WBSItemsVO> childList = activityObjectDB.get(groupActivityVO.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_CHILD_LIST);
        boolean isFirst = true;
        Date forcastedStartDate = new Date();
        Date forcastedEndDate   = new Date();
        for(WBSItemsVO wbsItemsVO : childList){
            Date tempForcastedStartDate = activityObjectDB.get(wbsItemsVO.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedStartDate);
            Date tempForcastedEndDate = activityObjectDB.get(wbsItemsVO.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate);
            if(isFirst) {forcastedStartDate = tempForcastedStartDate;forcastedEndDate = tempForcastedEndDate;}
            if(tempForcastedStartDate.compareTo(forcastedStartDate) < 0) forcastedStartDate = tempForcastedStartDate;
            if(tempForcastedEndDate.compareTo(forcastedEndDate) > 0) forcastedEndDate = tempForcastedStartDate;
            isFirst = false;
        }
        activityObjectDB.get(groupActivityVO.getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedStartDate, forcastedStartDate);
        activityObjectDB.get(groupActivityVO.getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate, forcastedEndDate);
    }
    /**
     * Group Activity List�� Return��.
     *
     * @param calculatedList
     * @return
     */
    static private List<WBSItemsVO> getGroupAcivity(List<WBSItemsVO> calculatedList){
        List<WBSItemsVO> groupActivityList = new ArrayList<WBSItemsVO>();
        for(WBSItemsVO wbsItemsVO : calculatedList){
            String hasChildActivity = wbsItemsVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR);
            if(!StrUtil.isEmpty(hasChildActivity)){
                groupActivityList.add(wbsItemsVO);
            }
        }
        return groupActivityList;
    }

    /**
     * ��ü ����Ʈ �߿��� ���� Simulation�� ���� ������ �߿� �� ������ VO List�� Return��.
     *
     * @param workWBSItemsList
     * @param dependencyList
     * @param calculatedList
     * @return
     */
    public static List<WBSItemsVO> getIndependentWBSItemList(List<WBSItemsVO> workWBSItemsList, List<WBSDependencyVO> dependencyList, List<WBSItemsVO> calculatedList){
        List<WBSItemsVO> rslt = new ArrayList<WBSItemsVO>();
        for(WBSItemsVO wbsItemsVO : workWBSItemsList){
            boolean isExists  = false;
            for(WBSDependencyVO dependencyVO : dependencyList){
                if(dependencyVO.getFromObid().equals(wbsItemsVO.getObid())){
                    if(!isAlreadyCalculated(dependencyVO.getToObid(), calculatedList)){
                        isExists = true; break;
                    }
                }
            }
            if(!isExists) {
                boolean canBeAdd = true;
                for(WBSItemsVO vo : calculatedList){
                    if(vo.getObid().equals(wbsItemsVO.getObid())) {canBeAdd = false; break;}
                }
                if(canBeAdd) {
                    rslt.add((WBSItemsVO)DomUtil.copy(wbsItemsVO));
                }
            }
        }
        return rslt;
    }
    /**
     * �����۾� ����� �������� Plan Date Setting
     *
     * @param wbsItemsVO
     * @param list
     * @param dependencyList
     * @param calculatedList
     */
    public static void calculateWBSPlanDate( WBSItemsVO                 wbsItemsVO    ,
                                             List<WBSItemsVO>           list          ,
                                             List<WBSDependencyVO>      dependencyList,
                                             List<WBSItemsVO>           calculatedList,
                                             Date                       currentDate   ,
                                             boolean                    forcastOnly   ){

        Date planStartDate = currentDate;
        Date planEndDate   = currentDate;
        Date tempStartDate  = currentDate;
        Date forcastedStartDate = currentDate;
        Date forcastedEndDate = currentDate;
        Date tempForcastedStartDate = currentDate;
        if(wbsItemsVO.getPlanStartDate() != null){
            planStartDate = wbsItemsVO.getPlanStartDate();
            planEndDate   = wbsItemsVO.getPlanStartDate();
            tempStartDate  = wbsItemsVO.getPlanStartDate();
            forcastedStartDate = wbsItemsVO.getPlanStartDate();
            forcastedEndDate = wbsItemsVO.getPlanStartDate();
            tempForcastedStartDate = wbsItemsVO.getPlanStartDate();
        }
        wbsItemsVO.setPlanDuration(getDuration(wbsItemsVO));
        boolean isFirst = true;
        for(WBSDependencyVO dependencyVO : dependencyList){
            if(dependencyVO.getFromObid().equals(wbsItemsVO.getObid())){
                WBSItemsVO calculatedTemplateVO = getCalculatedWBSItems(dependencyVO.getToObid(),calculatedList);
                if(!NullUtil.isNull(calculatedTemplateVO) && isFirst){
                    if(calculatedTemplateVO.getPlanStartDate() != null){
                        planStartDate = calculatedTemplateVO.getPlanStartDate();
                        planEndDate   = calculatedTemplateVO.getPlanStartDate();
                        tempStartDate  = calculatedTemplateVO.getPlanStartDate();
                        forcastedStartDate = calculatedTemplateVO.getPlanStartDate();
                        forcastedEndDate = calculatedTemplateVO.getPlanStartDate();
                        tempForcastedStartDate = calculatedTemplateVO.getPlanStartDate();
                    }
                }
                String dependencyType = dependencyVO.getDependencyType();
                Date dependencyDate   = calculatedTemplateVO.getPlanEndDate();
                Date forcastedDependencyDate   = calculatedTemplateVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate);

                if(calculatedTemplateVO.getActualEndDate() != null) {
                    forcastedDependencyDate = calculatedTemplateVO.getActualEndDate();
                }else{
                    if(calculatedTemplateVO.getActualStartDate() != null) {
                        forcastedDependencyDate = TimeServiceUtil.addDays(calculatedTemplateVO.getActualStartDate(),getDuration(calculatedTemplateVO));
                    }
                }
                if(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START.equals(dependencyType)){
                    tempStartDate = dependencyDate;
                    tempForcastedStartDate = forcastedDependencyDate;
                }else if(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START.equals(dependencyType)){
                    tempStartDate = dependencyDate;
                    tempForcastedStartDate = forcastedDependencyDate;
                }
                if(planStartDate.compareTo(tempStartDate) > 0){
                    tempStartDate = planStartDate;
                }
                if(forcastedStartDate.compareTo(tempForcastedStartDate) > 0){
                    tempForcastedStartDate = forcastedStartDate;
                }
                isFirst = false;
            }
        }
        planStartDate      = tempStartDate;
        forcastedStartDate = tempForcastedStartDate;

        planEndDate      = TimeServiceUtil.addDays(planStartDate,wbsItemsVO.getPlanDuration());
        if(wbsItemsVO.getActualEndDate() != null) {
            forcastedEndDate = wbsItemsVO.getActualEndDate();
        }else{
            forcastedEndDate = TimeServiceUtil.addDays(forcastedStartDate,wbsItemsVO.getPlanDuration());
            if(currentDate.compareTo(forcastedEndDate) > 0) {
                forcastedEndDate = currentDate;
            }
        }
        wbsItemsVO.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate, forcastedEndDate);
        if(!forcastOnly){
            wbsItemsVO.setPlanStartDate(planStartDate);
            wbsItemsVO.setPlanEndDate(planEndDate);
        }
        calculatedList.add(wbsItemsVO);
    }
    private static Integer getDuration(WBSItemsVO wbsItemsVO){
        if("Y".equals(wbsItemsVO.getIsSkipped())) return 0;
        if(wbsItemsVO.getIsMilestone().equals("Y")) return 1;
        return wbsItemsVO.getPlanDuration();
    }
    /**
     * ���Ǿ��� ����Ʈ���� VO�� ã�� Return��.
     *
     * @param obid
     * @param calculatedList
     * @return
     */
    private static WBSItemsVO getCalculatedWBSItems(String obid, List<WBSItemsVO> calculatedList){
        for(WBSItemsVO vo :  calculatedList){
            if(vo.getObid().equals(obid)) return vo;
        }
        return null;
    }
    /**
     * Ư�� Schedule �� ����Ʈ�� ���ؼ� Dependency List�� Return��,
     *
     * @param sheduleObid
     * @param wbsItemsVOlist
     * @return
     */
    public static List<WBSDependencyVO> getWBSDependencyList(String sheduleObid, List<? extends WBSItemsVO> wbsItemsVOlist){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, sheduleObid );
        List<WBSDependencyVO> wbsDependencyRelList = ObjectRoot.getRelationshipSet(wbsItemsVOlist, ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        return wbsDependencyRelList;
    }
    /**
     * �̹� ���� Simulation�� �Ǿ����� Check��.
     *
     * @param toObid
     * @param calculatedList
     * @return
     */
    private static boolean isAlreadyCalculated(String toObid, List<WBSItemsVO> calculatedList){
        for(WBSItemsVO wbsItemsVO : calculatedList){
            if(toObid.equals(wbsItemsVO.getObid())){
                return true;
            }
        }
        return false;
    }
    /**
     * ������ ��� �����̰� Revised�Ǿ��� ���� Cancel Revise�Ǿ���.
     *
     * @param porjectScheduleVO
     */
    public void cancelRevise(){
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,true);
        try{
            StringBuffer wherePattern     = new StringBuffer();
            StringBuffer parameterPattern = new StringBuffer();
            StringBuffer selectPattern    = new StringBuffer();

            if(!this.isLast()) throw new ApplicationException("Selected Schedule is not Latest. only Latest can be Deleted.");
            if(!ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT.equals(this.getStates())) throw new ApplicationException("Only Working can be Cancelled.");

            StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid",GlobalConstants.OQL_OPERATOR_NOT_EQUAL, this.getObid());
            List<WBSItemsVO> wbsItemsList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);

            if(NullUtil.isNone(wbsItemsList)){
                this.deleteObject();
            }else{
                Set<String> strSet = new HashSet<String>();

                for(WBSItemsVO vo : wbsItemsList){strSet.add(vo.getObid());}

                wherePattern.setLength(0);parameterPattern.setLength(0);selectPattern.setLength(0);
                StringUtil.constructWherePattern(wherePattern, parameterPattern, "!To["+ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER+"].fromObid",GlobalConstants.OQL_OPERATOR_NOT_IN, StrUtil.convertSet2Str(strSet));

                List<WBSJobActivityVO> wbsJobActivityList = ObjectRoot.getRelatedObjectSet(wbsItemsList, ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY,
                        GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());

                for(WBSItemsVO vo : wbsItemsList){
                    WBSItems wbsItemsDom = new WBSItems(vo);
                    wbsItemsDom.deleteObject();
                }
                for(WBSJobActivityVO vo : wbsJobActivityList){
                    WBSJobActivity wbsJobActivityDom = new WBSJobActivity(vo);
                    wbsJobActivityDom.deleteObject();
                }
                this.deleteObject();
            }
        }finally{
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff, false);
        }
    }

/**
 * Design Project ManMonth ����� ���� ��ȸ ������.
 *
 * @return
 */
    public List<WBSItemsVO> getDesignProjectWBSSchedule(boolean forcastOnly){
        List<WBSItemsVO> workWBSItemsList = this.getWBSItemsStructureList(true,false,false);
        return workWBSItemsList;
    }
    public ProjectScheduleVO getPlanDate(Projects projectDom,List<WBSItemsVO> retrieveWBSItems){
        ProjectScheduleVO projectScheduleVO = (ProjectScheduleVO)DomUtil.copy(this.getVo());
        Date planStartDate = null;
        Date planEndDate = null;
        Date forcastedDate = null;
        for(WBSItemsVO vo : retrieveWBSItems){
            if(vo instanceof WBSPhasesVO){
                if(planStartDate == null) planStartDate = vo.getPlanStartDate();
                if(planEndDate   == null) planEndDate = vo.getPlanEndDate();
                Long dateLong = 0l;
                Date tempForcastedDate = null;
                try{
                    dateLong = (Long)vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate);
                    tempForcastedDate = new Date(dateLong);
                }catch(Exception e){
                    tempForcastedDate = vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate);
                }
                if(forcastedDate   == null) forcastedDate = tempForcastedDate;

                if(vo.getPlanStartDate() != null && planStartDate != null){
                    if(planStartDate.compareTo(vo.getPlanStartDate()) > 0){
                        planStartDate = vo.getPlanStartDate();
                    }
                }
                if(vo.getPlanEndDate() != null && planEndDate != null){
                    if(planEndDate.compareTo(vo.getPlanEndDate()) < 0){
                        planEndDate = vo.getPlanEndDate();
                    }
                }
                if(tempForcastedDate != null && forcastedDate != null){
                    if(forcastedDate.compareTo(tempForcastedDate) < 0){
                        forcastedDate = tempForcastedDate;
                    }
                }
            }
        }
        projectScheduleVO.setPlanStartDate(planStartDate);
        projectScheduleVO.setPlanEndDate(planEndDate);
        projectScheduleVO.setUniqueString("00000");
        projectScheduleVO.setUniqueStringParent(null);

        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(this.getWBSTemplateMaster());;
        projectScheduleVO.setTitles(projectDom.getTitles());
        projectScheduleVO.setDescriptions(wbsTemplateMasterDom.getCommonTitlesForDisplay());
        Integer projectDuration = TimeServiceUtil.getDateDiff(planStartDate,planEndDate);
        projectScheduleVO.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_projectDuration, projectDuration);
        projectScheduleVO.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedStartDate, planStartDate);
        projectScheduleVO.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate, forcastedDate);
        return projectScheduleVO;
    }
    public static void setDelayDays(List<? extends BusinessObjectRootVO> list){
        Date currentDate = TimeServiceUtil.getDBLocalTime();
        for(BusinessObjectRootVO vo : list)    {
            if(vo instanceof WBSItemsVO){
                if(((WBSItemsVO)vo).getActualEndDate() == null && ((WBSItemsVO)vo).getPlanEndDate() != null){
                    if(currentDate.compareTo(((WBSItemsVO)vo).getPlanEndDate()) > 0){
                        Integer delayDays = TimeServiceUtil.getDateDiff(((WBSItemsVO)vo).getPlanEndDate(),currentDate);
                        if(delayDays > 0) vo.setOutDataAttributeValue("delayDays", delayDays);
                    }
                }
            }else if(vo instanceof ProjectScheduleVO){
                if(((ProjectScheduleVO)vo).getActualEndDate() == null && ((ProjectScheduleVO)vo).getPlanEndDate() != null){
                    if(currentDate.compareTo(((ProjectScheduleVO)vo).getPlanEndDate()) > 0){
                        Integer delayDays = TimeServiceUtil.getDateDiff(((ProjectScheduleVO)vo).getPlanEndDate(),currentDate);
                        if(delayDays > 0) vo.setOutDataAttributeValue("delayDays", delayDays);
                    }
                }
            }
        }
    }
    public List<WBSItemsVO> searchActivityWithProjectPerson(){
        return searchActivityWithProjectPerson(null);
    }
    public List<WBSItemsVO> searchActivityWithProjectPerson(Set<String> stateFilter){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();
        StringUtil.constructFromPattern(fromPattern,OmcApplicationConstants.CONN_TO_THIS_ALIAS                       ,"this",OmcApplicationConstants.CONN_THIS_CONNECTED_FROM  ,ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY ,"ATA");
        StringUtil.constructFromPattern(fromPattern,ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY                 ,"ATA" ,OmcApplicationConstants.CONN_TO_CONNECTED_THIS    ,ApplicationSchemaConstants.BIZCLASS_PROJECTPERSON      ,"PERSON");
        StringUtil.constructFromPattern(fromPattern,OmcApplicationConstants.CONN_TO_THIS_ALIAS                       ,"this",OmcApplicationConstants.CONN_THIS_CONNECTED_FROM  ,ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT ,"CTX");
        StringUtil.constructFromPattern(fromPattern,ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT ,"CTX" ,OmcApplicationConstants.CONN_TO_CONNECTED_THIS    ,ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE    ,"SCH");

        StringUtil.constructSelectPattern(selectPattern, "@SCH.[obid]");
        StringUtil.constructSelectPattern(selectPattern, "@PERSON.[userId]");

        StringUtil.addSortByPattern(selectPattern, "@PERSON.[userId]");

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@SCH.[obid]" , GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[isSkipped]", GlobalConstants.OQL_OPERATOR_EQUAL, "N");

        if( stateFilter != null && stateFilter.size() > 0){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(stateFilter));
        }
        return ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_WBSITEMS,
                                        GlobalConstants.FLAG_TYPE_ALL,
                                        GlobalConstants.FLAG_TYPE_ALL,
                                        selectPattern.toString(),
                                        fromPattern.toString(),
                                        wherePattern.toString(),
                                        paramPattern.toString(),
                                        true,
                                        0
                                        );
    }
    /**
     * Ư�� Activity�� ���� Complete�Ѵ�. Complete ������ �������� ������ Validation ���� �߻�.
     *
     * @param activityCode
     */
    public void completeActivity(String activityCode){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[originActivityCode]", GlobalConstants.OQL_OPERATOR_EQUAL, activityCode);
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, "Started");

        List<WBSGeneralActivityVO> activityList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        if(NullUtil.isNone(activityList)) throw new FoundationException("No Activity(" + activityCode + ").");
        if(activityList.size() > 1) throw new FoundationException("Duplicate Activity(" + activityCode + ").");

        WBSGeneralActivity wbsGeneralActivityDom = new WBSGeneralActivity(activityList.get(0));
        wbsGeneralActivityDom.forceCompleteWBSActivityFromWBS(this.getVo(), "Force complete By System", true);
    }
    public Map<String,WBSItemsVO> getWBSDB(List<WBSItemsVO> structureList){
        List<WBSDependencyVO> dependencyList = new ArrayList<WBSDependencyVO>();
        return getWBSDB(structureList,dependencyList);
    }
    public Map<String,WBSItemsVO> getWBSDB(List<WBSItemsVO> structureList, List<WBSDependencyVO> dependencyList){
        if(NullUtil.isNone(structureList)){
            List<WBSItemsVO> tempSructureList = this.getWBSItemsStructureList(false, false, false);
            structureList.addAll(tempSructureList);
        }
        List<WBSDependencyVO> tempDependencyList = ProjectSchedule.getWBSDependencyList(this.getObid(),structureList);
        dependencyList.addAll(tempDependencyList);

        Map<String,WBSItemsVO> activityObjectDB = new HashMap<String,WBSItemsVO>();
        ProjectSchedule.makeWBSDB(structureList,dependencyList,activityObjectDB);

        setDBOriginPlanStartDate(structureList);

        setPreviousActivity(activityObjectDB,structureList);

        return activityObjectDB;
    }

    private void makePath(List<WBSItemsVO> structureList,List<String> rslt, Map<String,WBSItemsVO> activityObjectDB){
        Set<String> startActivitySet = new HashSet<String>();
        Set<String> leafActivitySet = new HashSet<String>();
        for(WBSItemsVO vo : structureList){
            List<DependencyVO> previousList = new ArrayList<DependencyVO>();
            List<DependencyVO> nextList     = new ArrayList<DependencyVO>();
            if(isLeaf(vo)){
                previousList = vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_PREVIOUS_LIST);
                nextList = vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_NEXT_LIST);
                if(NullUtil.isNone(nextList)) leafActivitySet.add(vo.getObid());
                if(NullUtil.isNone(previousList)) startActivitySet.add(vo.getObid());
            }
        }
        int i = 1;
        int mxsimum = 0;
        Map<String,Integer> max = new HashMap<String,Integer>();
        max.put("e", 0);
        for(String obid : startActivitySet){
            makePath(max,structureList,
                    activityObjectDB,
                    rslt,
                    activityObjectDB.get(obid),
                    activityObjectDB.get(obid).getPlanDuration(),
                    obid,
                    leafActivitySet);
        }
        for(String str : rslt){
            System.out.println(str);
        }
        
    }
    private void makePath(Map<String,Integer> max, List<WBSItemsVO> structureList,Map<String,WBSItemsVO> activityObjectDB, List<String> rslt, WBSItemsVO vo,  Integer durationSum, String path, Set<String> leafActivitySet){
        if(leafActivitySet.contains(vo.getObid())){
            System.out.println(vo.getActivityNameKor() + ":" + path);
            //if(durationSum > max.get("m")){
                //max.put("m", durationSum);
                //rslt.set(0, durationSum + ":" + path);
 
                //System.out.println(vo.getActivityNameKor());
                //rslt.add(durationSum + ":" + path);
            //}
            ;
        }else{
            List<DependencyVO> nextList     = vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_NEXT_LIST);
            int i = 1;
            for(DependencyVO dependencyVO : nextList){
                makePath(max,structureList,activityObjectDB,rslt,dependencyVO.getDependentVO(),durationSum + dependencyVO.getDependentVO().getPlanDuration(),path + "." + vo.getObid(),leafActivitySet);
            }
            
        }
    }
    public Map<String,WBSItemsVO> getWBSDB(){
        List<WBSItemsVO> structureList = new ArrayList<WBSItemsVO>();
        return getWBSDB(structureList);
    }
    /**
     * ���õǾ��� ������ ������ �� ������.
     *
     * @param startActivityVO
     * @param currentDate
     */
    public void saveSimulatedScheduleResult(WBSItemsVO startActivityVO, Date currentDate){
        List<WBSItemsVO> structureList = new ArrayList<WBSItemsVO>();
        Map<String,WBSItemsVO> activityObjectDB = new HashMap<String,WBSItemsVO>();
        saveSimulatedScheduleResult(startActivityVO,currentDate,structureList,activityObjectDB);
    }
    /**
     * ������Ʈ ��ü�� ���ؼ� �� ó�� ���۵Ǵ� Activity�� ã�� ��ü�� �ڵ� Scheduling��.
     *
     * @param currentDate
     * @param structureList
     * @param activityObjectDB
     */
    public void saveSimulatedScheduleResult(Date currentDate, List<WBSItemsVO> structureList, Map<String,WBSItemsVO> activityObjectDB){
        if(activityObjectDB.isEmpty()){
            Map<String,WBSItemsVO> tempActivityObjectDB = this.getWBSDB(structureList);
            for(String key : tempActivityObjectDB.keySet()){
                activityObjectDB.put(key, tempActivityObjectDB.get(key));
            }
        }
        for(String key : activityObjectDB.keySet()){
            if(activityObjectDB.get(key).getPlanStartDate() == null){
                activityObjectDB.get(key).setPlanStartDate(currentDate);
            }
            if(activityObjectDB.get(key).getPlanEndDate() == null){
                activityObjectDB.get(key).setPlanEndDate(currentDate);
            }
        }
        List<WBSItemsVO> startActivityVOList = findFirstActivity(activityObjectDB);
        if(NullUtil.isNone(startActivityVOList) || startActivityVOList.size() > 1) throw new FoundationException("Process Start point is duplicated. Please contact System Administrator.");
        WBSItemsVO startActivityVO = startActivityVOList.get(0);
        Date endDate = TimeServiceUtil.addDays(startActivityVO.getPlanStartDate(), getDuration(startActivityVO));
        startActivityVO.setPlanEndDate(endDate);
        this.saveSimulatedScheduleResult(startActivityVO,currentDate,structureList,activityObjectDB);
        Set<String> attributes = new HashSet<String>();
        // 2018-09-12 previousActivityList ������ - ���� ����
        //attributes.add("previousActivityList");
        attributes.add("planDuration");attributes.add("planStartDate");attributes.add("planEndDate");attributes.add("isFirstActivity");attributes.add("isLastActivity");attributes.add("isSkipped");
        ObjectRoot.modifyObjectSetBatch(startActivityVO, attributes);
    }
    public void setActivityForcastedDateMain(List<WBSItemsVO> structuredActivityList, Map<String,WBSItemsVO> activityObjectDB){
        Date currentDate = TimeServiceUtil.getDBLocalTime();
        List<WBSItemsVO> startActivityVOList = findFirstActivity(activityObjectDB);
        
        if(NullUtil.isNone(startActivityVOList) || startActivityVOList.size() > 1 ) return;
        WBSItemsVO startActivityVO = startActivityVOList.get(0);
        Set<String> completedObidSet = new HashSet<String>();

        List<WBSItemsVO> wantedActivityList = new ArrayList<WBSItemsVO>();
        wantedActivityList.add(startActivityVO);
        Set<String> completedSet = new HashSet<String>();
        int loopCnt = 0;
        while(true){
            if(loopCnt > ProjectConstants.WBS_MAX_DEPENDENCY_LEVEL) throw new FoundationException("Dependency occurs infinite loop. Please System Administrator.");
            completedSet = setLeafActivityForcastedDate1(wantedActivityList,activityObjectDB,currentDate,completedObidSet,1);
            if((completedSet.size() == 0)) break;
            wantedActivityList = new ArrayList<WBSItemsVO>();
            Set<String> workSet = new HashSet<String>();
            for(String obid : completedSet){
                List<DependencyVO> dependencyList = activityObjectDB.get(obid).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_NEXT_LIST);
                for(DependencyVO dependencyVO : dependencyList){
                    workSet.add(dependencyVO.getDependentVO().getObid());
                }
            }
            for(String obid : workSet){
                wantedActivityList.add(activityObjectDB.get(obid));
            }
            loopCnt++;
        }
        setForcastedDateForGroupAcivity(structuredActivityList,activityObjectDB);
    }
    /**
     * ���������� �Ǿ��� ���� Parent�� ���ؼ��� ���� ������ ��.
     *
     * @param rescheduledActivityList
     * @param activityObjectDB
     */
    public List<WBSItemsVO> getRescheduledPlanDateForGroupAcivity(List<WBSItemsVO> rescheduledActivityList, Map<String,WBSItemsVO> activityObjectDB){
        Date currentDate = TimeServiceUtil.getDBLocalTime();
        List<WBSItemsVO> groupActivityList = getRescheduledPlanDateForGroupAcivity(rescheduledActivityList,activityObjectDB,currentDate);
        return groupActivityList;
    }
    private List<WBSItemsVO> getRescheduledPlanDateForGroupAcivity(List<WBSItemsVO> rescheduledActivityList,Map<String,WBSItemsVO> activityObjectDB, Date currentDate){
        List<WBSItemsVO> groupActivityList = getGroupAcivity(rescheduledActivityList, activityObjectDB);
        SortUtil.sort(groupActivityList, "explodedDepth", true);
        for(WBSItemsVO wbsItemsVO : groupActivityList){
            setGroupActivityPlanDate(wbsItemsVO,activityObjectDB,currentDate);
            wbsItemsVO.setPlanDuration(activityObjectDB.get(wbsItemsVO.getObid()).getPlanDuration());
            wbsItemsVO.setPlanStartDate(activityObjectDB.get(wbsItemsVO.getObid()).getPlanStartDate());
            wbsItemsVO.setPlanEndDate(activityObjectDB.get(wbsItemsVO.getObid()).getPlanEndDate());
        }
        return groupActivityList;
    }
    private Set<String> setLeafActivityForcastedDate1(List<WBSItemsVO> wantedActivityList, Map<String,WBSItemsVO> activityObjectDB, Date currentDate, Set<String> completedObidSet,int calledDepth){
        Set<String> completedSet = new HashSet<String>();
        for(WBSItemsVO vo : wantedActivityList){
            if(setForcastedEndDate1(vo,activityObjectDB,currentDate,completedObidSet)) completedSet.add(vo.getObid());
        }
        return completedSet;
    }


    public void setParentSkipFlags(List<WBSItemsVO> calculatedList,Map<String,WBSItemsVO> activityObjectDB){
        List<WBSItemsVO> parentList = new ArrayList<WBSItemsVO>();
        for(WBSItemsVO wbsItemsVO : calculatedList){
            String hasChildActivity = wbsItemsVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR);
            if(!StrUtil.isEmpty(hasChildActivity)){
                parentList.add(wbsItemsVO);
            }
        }
        if(!NullUtil.isNone(parentList)){
            SortUtil.sort(parentList, "explodedDepth", true);
            for(WBSItemsVO wbsItemsVO : parentList){
                boolean allSkipped = isChildAllSkipped(calculatedList,wbsItemsVO);
                if(allSkipped){
                    wbsItemsVO.setIsSkipped("Y");
                }else{
                    wbsItemsVO.setIsSkipped("N");
                }
                activityObjectDB.get(wbsItemsVO.getObid()).setIsSkipped(wbsItemsVO.getIsSkipped());
            }
        }
    }
    private boolean isChildAllSkipped(List<WBSItemsVO> calculatedList, WBSItemsVO parentItemsVO){
        boolean allSkipped = true;
        for(WBSItemsVO wbsItemsVO : calculatedList){
            if((parentItemsVO.getExplodedDepth() == wbsItemsVO.getExplodedDepth()-1) && (wbsItemsVO.getUniqueString().startsWith(parentItemsVO.getUniqueString()))){
               if(wbsItemsVO.getIsSkipped().equals("N")) {allSkipped = false;break;}
            }
        }
        return allSkipped;
    }
    /**
     * ��ü ������ �� ó�� ���۵Ǵ� Activity�� ã����.
     *
     * @param activityObjectDB
     * @return
     */
    public List<WBSItemsVO> findFirstActivity(Map<String,WBSItemsVO> activityObjectDB){
        List<WBSItemsVO> list = new ArrayList<WBSItemsVO>();
        for(String key : activityObjectDB.keySet()){
            if(isLeaf(activityObjectDB.get(key))){
                List<DependencyVO> dependencyList = new ArrayList<DependencyVO>();
                makePreviousActivityListDB(activityObjectDB.get(key),activityObjectDB,dependencyList);
                if(NullUtil.isNone(dependencyList)) list.add(activityObjectDB.get(key));
            }
        }
        return list;
    }
    /**
     * ���õǾ��� Activity(startActivityVO)������ Activity�� ���� ������ �ٽ� ������.
     *
     * @param startActivityVO
     * @param currentDate
     * @param structureList
     * @param activityObjectDB
     */
    public void saveSimulatedScheduleResult(WBSItemsVO startActivityVO, Date currentDate, List<WBSItemsVO> structureList, Map<String,WBSItemsVO> activityObjectDB){

        /******************************************* Map Database Creation *******************************************/
        if(activityObjectDB.isEmpty()){
            Map<String,WBSItemsVO> tempActivityObjectDB = this.getWBSDB(structureList);
            for(String key : tempActivityObjectDB.keySet()){
                activityObjectDB.put(key, tempActivityObjectDB.get(key));
            }
        }
        /******************************************* Validation Start*******************************************/
        if(!isLeaf(activityObjectDB.get(startActivityVO.getObid()))) throw new FoundationException((new WBSItems(startActivityVO)).getCommonTitlesForDisplay() + "is not activity.");
        
        ProjectsVO relatedProject = this.getRelatedProject();
        Projects projectDom = new Projects(relatedProject);
        if(!"VC".equals(DivisionUnit.getVOByName(projectDom.getVo().getDivisionCode()).getBusinessUnitCode())){
            List<DependencyVO> dependencyList = activityObjectDB.get(startActivityVO.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_PREVIOUS_LIST);
            List<WBSItemsVO> selfWBSActivitiyList = new ArrayList<WBSItemsVO>();
            for(DependencyVO vo : dependencyList){
                selfWBSActivitiyList.add(vo.getDependentVO());
            }
            Map<String,WBSItemsVO> lastActivityMap = new HashMap<String,WBSItemsVO>();
            Date selfStartDate = getMaxEndDate(startActivityVO, selfWBSActivitiyList,true,lastActivityMap);
            if(selfStartDate.compareTo(startActivityVO.getPlanStartDate()) > 0) {
                WBSItemsVO lastActivityVO = lastActivityMap.get("Last Activity");
                WBSItems lastActivityDom = new WBSItems(lastActivityVO);
                if(NullUtil.isNull(lastActivityVO)){
                    throw new FoundationException("Start Date is smaller than prevous Activity's End Dates. Previous activity's last End Date is " + DateUtil.converDateFormat(selfStartDate));
                }else{
                    throw new FoundationException("Start Date is smaller than prevous Activity's End Dates. Previous activity's last End Date is " + DateUtil.converDateFormat(selfStartDate) + ". Previous Activity is '" + lastActivityDom.getCommonTitlesForDisplay());
                }
            }
        }
        /******************************************* Leaf Activity Rescheduling*******************************************/
        reSchedulingActivity(activityObjectDB.get(startActivityVO.getObid()),activityObjectDB,currentDate);
        List<WBSItemsVO> rescheduledList = new ArrayList<WBSItemsVO>();

        for(WBSItemsVO vo: structureList){
            vo.setPreviousActivityList("");
            if("Y".equals(vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_isRescheduled))){
                rescheduledList.add(vo);
            }
        }
        /******************************************* Parent Activity Rescheduling*******************************************/
        List<WBSItemsVO> rescheduledGroupActivityList = getRescheduledPlanDateForGroupAcivity(rescheduledList, activityObjectDB);
        /******************************************* Database Update *******************************************/
        rescheduledList.addAll(rescheduledGroupActivityList);
        Set<String> attributes = new HashSet<String>();
        attributes = new HashSet<String>();
        attributes.add("planStartDate");
        attributes.add("planEndDate");
        attributes.add("planDuration");
        attributes.add("isSkipped");
        ObjectRoot.modifyObjectSetBatch(rescheduledList, attributes);
    }
    private void reSchedulingActivity(WBSItemsVO startActivityVO, Map<String,WBSItemsVO> activityObjectDB, Date currentDate){
        List<DependencyVO> nextDependencyList = new ArrayList<DependencyVO>();
        makeNextActivityListDB(startActivityVO,activityObjectDB,nextDependencyList);
        for(int i = nextDependencyList.size() -1 ; i >= 0; i--){
            Date planStartDate = getStartDate(activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()),activityObjectDB);
            if("Y".equals(activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_isRescheduled))){
                if(planStartDate.compareTo(nextDependencyList.get(i).getDependentVO().getPlanStartDate()) < 1){
                    nextDependencyList.remove(i); continue;
                }else if(planStartDate.compareTo(nextDependencyList.get(i).getDependentVO().getPlanStartDate()) == 1){
                    reSchedulingActivity(activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()),activityObjectDB,currentDate);
                }
            }
            if(activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()).getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT)){
                Date endDate = TimeServiceUtil.addDays(planStartDate, getDuration(activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid())));
                activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()).setPlanStartDate(planStartDate);
                activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()).setPlanEndDate(endDate);
            }
            activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_isRescheduled, "Y");
        }
        for(DependencyVO vo : nextDependencyList){
            reSchedulingActivity(activityObjectDB.get(vo.getDependentVO().getObid()),activityObjectDB,currentDate);
        }
    }
    private void manualSchedulingSkippedActivity(WBSItemsVO startActivityVO, Map<String,WBSItemsVO> activityObjectDB, Date currentDate){
        List<DependencyVO> nextDependencyList = new ArrayList<DependencyVO>();
        makeNextActivityListDB(startActivityVO,activityObjectDB,nextDependencyList);
        for(int i = nextDependencyList.size() -1 ; i >= 0; i--){
            if("Y".equals(activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_isRescheduled))){
                Date compareFromDate = startActivityVO.getPlanEndDate();
                if(nextDependencyList.get(i).getDependencyType().equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START)){
                    compareFromDate = startActivityVO.getPlanStartDate();
                }
                if(compareFromDate.compareTo(nextDependencyList.get(i).getDependentVO().getPlanStartDate()) < 1){
                    nextDependencyList.remove(i); continue;
                }else{
                    
                }
            }
            if("Y".equals(nextDependencyList.get(i).getDependentVO().getIsSkipped())){
                Date planStartDate = getStartDate(activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()),activityObjectDB);
                Date endDate = TimeServiceUtil.addDays(planStartDate, getDuration(activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid())));
                activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()).setPlanStartDate(planStartDate);
                activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()).setPlanEndDate(endDate);
            }
            activityObjectDB.get(nextDependencyList.get(i).getDependentVO().getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_isRescheduled, "Y");
        }
        for(DependencyVO vo : nextDependencyList){
            manualSchedulingSkippedActivity(activityObjectDB.get(vo.getDependentVO().getObid()),activityObjectDB,currentDate);
        }
    }
    public static boolean isLeaf(WBSItemsVO activityVO){
        if(StrUtil.isEmpty((String)activityVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))) return true;
        return false;
    }
    private void makePreviousActivityListDB(WBSItemsVO startActivityVO, Map<String,WBSItemsVO> activityObjectDB,List<DependencyVO> dependencyList){
        makeDependencyActivityListDB(startActivityVO,activityObjectDB,dependencyList,true);
    }
    private void makeNextActivityListDB(WBSItemsVO startActivityVO, Map<String,WBSItemsVO> activityObjectDB,List<DependencyVO> dependencyList){
        makeDependencyActivityListDB(startActivityVO,activityObjectDB,dependencyList,false);
    }
    @SuppressWarnings("unchecked")
    private void makeDependencyActivityListDB(WBSItemsVO startActivityVO, Map<String,WBSItemsVO> activityObjectDB,List<DependencyVO> dependencyList, boolean isPrevious){
        if(isPrevious){
            dependencyList.addAll((List<DependencyVO>)activityObjectDB.get(startActivityVO.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_PREVIOUS_LIST));
        }else{
            dependencyList.addAll((List<DependencyVO>)activityObjectDB.get(startActivityVO.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_NEXT_LIST));
        }
    }
    /**
     * Schedule �� ��ü ���� Activity list �� ����.
     */
    public void makeNextActivityList(WBSItemsVO startActivityVO, Map<String,WBSItemsVO> activityObjectDB, Map<String,WBSItemsVO> nextActivityMap, Set<String> stateSet){
        List<DependencyVO> nextDependencyList = new ArrayList<DependencyVO>();
        makeNextActivityListDB(startActivityVO,activityObjectDB,nextDependencyList);
        for(DependencyVO dependencyVO : nextDependencyList){
            if(!nextActivityMap.containsKey(dependencyVO.getDependentVO().getObid())
                    && stateSet.contains(dependencyVO.getDependentVO().getStates())){
                makeNextActivityList(activityObjectDB.get(dependencyVO.getDependentVO().getObid()), activityObjectDB, nextActivityMap, stateSet);
            }
        }
        nextActivityMap.put(startActivityVO.getObid(), startActivityVO);
    }
    private Date getStartDate(WBSItemsVO wantedActivityVO, Map<String,WBSItemsVO> activityObjectDB){
        List<DependencyVO> dependencyList = new ArrayList<DependencyVO>();
        makePreviousActivityListDB(wantedActivityVO,activityObjectDB,dependencyList);
        Date maxDate = null;
        boolean isFirst = true;
        for(DependencyVO vo : dependencyList){
            if(!vo.getDependentVO().getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT)){continue;}
            if(isFirst){
                if(vo.getDependencyType().equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START)){
                    maxDate = activityObjectDB.get(vo.getDependentVO().getObid()).getPlanStartDate();
                }else if(vo.getDependencyType().equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START)){
                    maxDate = activityObjectDB.get(vo.getDependentVO().getObid()).getPlanEndDate();
                }else{
                    ;
                }
            }
            if(vo.getDependencyType().equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START)){
                if(activityObjectDB.get(vo.getDependentVO().getObid()).getPlanStartDate().compareTo(maxDate) > 0) maxDate = activityObjectDB.get(vo.getDependentVO().getObid()).getPlanStartDate();
            }else if(vo.getDependencyType().equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START)){
                if(activityObjectDB.get(vo.getDependentVO().getObid()).getPlanEndDate().compareTo(maxDate) > 0) maxDate = activityObjectDB.get(vo.getDependentVO().getObid()).getPlanEndDate();
            }else{
                ;
            }
            isFirst = false;
        }
        if(NullUtil.isNull(maxDate)){
            maxDate = wantedActivityVO.getPlanStartDate();
        }
        return maxDate;
    }
    private void setGroupActivityPlanDate(WBSItemsVO wantedActivityVO, Map<String,WBSItemsVO> activityObjectDB, Date currentDate){
        List<WBSItemsVO> childList = activityObjectDB.get(wantedActivityVO.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_CHILD_LIST);
        boolean isFirst = true;
        Date planStartDate = new Date();
        Date planEndDate = new Date();
        for(WBSItemsVO wbsItemsVO : childList){
            Date tempplanStartDate = activityObjectDB.get(wbsItemsVO.getObid()).getPlanStartDate();
            Date tempplanEndDate = activityObjectDB.get(wbsItemsVO.getObid()).getPlanEndDate();
            if(isFirst) {planStartDate = tempplanStartDate;planEndDate = tempplanEndDate;}
            if(!"Y".equals(wbsItemsVO.getIsSkipped()) && tempplanStartDate.compareTo(planStartDate) < 0) planStartDate = tempplanStartDate;
            if(tempplanEndDate.compareTo(planEndDate) > 0) planEndDate = tempplanEndDate;
            isFirst = false;
        }
        Integer planDuration = TimeServiceUtil.getDateDiff(planStartDate,planEndDate);
        activityObjectDB.get(wantedActivityVO.getObid()).setPlanStartDate(planStartDate);
        activityObjectDB.get(wantedActivityVO.getObid()).setPlanEndDate(planEndDate);
        activityObjectDB.get(wantedActivityVO.getObid()).setPlanDuration(planDuration);
    }
    private boolean setForcastedEndDate1(WBSItemsVO wantedActivityVO, Map<String,WBSItemsVO> activityObjectDB, Date currentDate,Set<String> completedObidSet){
        List<DependencyVO> dependencyList = new ArrayList<DependencyVO>();
        makePreviousActivityListDB(wantedActivityVO,activityObjectDB,dependencyList);
        Date forcastedStartDate = currentDate;
        Date forcastedEndDate = null;
        if(NullUtil.isNone(dependencyList)){
            if(wantedActivityVO.getActualStartDate() != null){
                forcastedStartDate = wantedActivityVO.getActualStartDate();
            }else{
                forcastedStartDate = wantedActivityVO.getPlanStartDate();
            }
            if(wantedActivityVO.getActualEndDate() != null){
                forcastedEndDate = wantedActivityVO.getActualEndDate();
            }else{
                forcastedEndDate = TimeServiceUtil.addDays(forcastedStartDate, getDuration(wantedActivityVO));
            }
        }else{
//            if(!isPreviousAllCompleted(completedObidSet,dependencyList)) return false;
            for(DependencyVO dependencyVO : dependencyList){
                Date forcastedStartDateEach = null;
                Date forcastedDependencyStartDate   = activityObjectDB.get(dependencyVO.getDependentVO().getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedStartDate);
                Date forcastedDependencyEndDate     = activityObjectDB.get(dependencyVO.getDependentVO().getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate);
                if(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START.equals(dependencyVO.getDependencyType())){
                    if(dependencyVO.getDependentVO().getActualStartDate() != null) {
                        if(dependencyVO.getDependentVO().getActualEndDate() != null){
                            forcastedStartDateEach = dependencyVO.getDependentVO().getActualEndDate();
                        }else{
                            forcastedStartDateEach = TimeServiceUtil.addDays(dependencyVO.getDependentVO().getActualStartDate(), getDuration(dependencyVO.getDependentVO()));
                        }
                    }else{
                        if(forcastedDependencyEndDate == null){
                            forcastedStartDateEach = dependencyVO.getDependentVO().getPlanStartDate();
                        }else{
                            forcastedStartDateEach = forcastedDependencyEndDate;
                        }
                    }
                }else if(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START.equals(dependencyVO.getDependencyType())){
                    if(dependencyVO.getDependentVO().getActualStartDate() != null) {
                        forcastedStartDateEach = dependencyVO.getDependentVO().getActualStartDate();
                    }else{
                        if(forcastedDependencyStartDate == null){
                            forcastedStartDateEach = dependencyVO.getDependentVO().getPlanStartDate();
                        }else{
                            forcastedStartDateEach = forcastedDependencyStartDate;
                        }

                    }
                }
                if(forcastedStartDateEach.compareTo(forcastedStartDate) > 0){
                    forcastedStartDate = forcastedStartDateEach;
                }
            }
            forcastedEndDate = TimeServiceUtil.addDays(forcastedStartDate,getDuration(wantedActivityVO));
        }
        activityObjectDB.get(wantedActivityVO.getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedStartDate, forcastedStartDate);
        activityObjectDB.get(wantedActivityVO.getObid()).setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate, forcastedEndDate);
        completedObidSet.add(wantedActivityVO.getObid());
        return true;
    }
    /**
     *  Input�Ǿ��� Activity�� �ݿ��Ǵ� ��� ���� Valid�� �������� Ȯ���� �� �����Ѵ�.
     *
     * @param wbsActivityList
     */
    
    public List<WBSItemsVO> saveActivityManually(Projects projectsDom, List<WBSItemsVO> structureList, Map<String,WBSItemsVO> activityObjectDB, Date currentDate){
        List<WBSItemsVO> startActivityVOList = findFirstActivity(activityObjectDB);
        if(NullUtil.isNone(startActivityVOList) || startActivityVOList.size() > 1) throw new FoundationException("Process Start point is duplicated. Please contact System Administrator.");
        WBSItemsVO startActivityVO = startActivityVOList.get(0);
        List<WBSItemsVO> scheduleErrorActivityList = new ArrayList<WBSItemsVO>();
        manualSchedulingSkippedActivity(startActivityVO, activityObjectDB, currentDate);
        
        if(!"VC".equals(DivisionUnit.getVOByName(projectsDom.getVo().getDivisionCode()).getBusinessUnitCode())){
            scheduleErrorActivityList = getScheduleErrorList(activityObjectDB);
        }
        if(NullUtil.isNone(scheduleErrorActivityList)){
            setParentSkipFlags(structureList, activityObjectDB);
            getRescheduledPlanDateForGroupAcivity(structureList, activityObjectDB);
            Set<String> attributeSet = new HashSet<String>();
            attributeSet.add("isSkipped");attributeSet.add("planDuration");attributeSet.add("planStartDate");attributeSet.add("planEndDate");
            ObjectRoot.modifyObjectSetBatch(structureList, attributeSet);
        }
        return scheduleErrorActivityList;
    }

    /**
     * ������ ���ؼ� Validation ����� Return��.
     *
     * @param activityObjectDB
     * @return
     */
    public List<WBSItemsVO> getScheduleErrorList(Map<String,WBSItemsVO> activityObjectDB){
        List<WBSItemsVO> scheduleErrorActivityList = new ArrayList<WBSItemsVO>();
        for(String key : activityObjectDB.keySet()){
            if(activityObjectDB.get(key).getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT) &&
                    isLeaf(activityObjectDB.get(key))){
                Date startDate = this.getStartDate(activityObjectDB.get(key),activityObjectDB);
                if(startDate.compareTo(activityObjectDB.get(key).getPlanStartDate()) > 0){
                    activityObjectDB.get(key).setOutDataAttributeValue("validatedDependentActivityDate", startDate);
                    scheduleErrorActivityList.add(activityObjectDB.get(key));
                }
            }
        }
        return scheduleErrorActivityList;
    }
    public List<WBSItemsVO> getScheduleErrorList(){
        List<WBSItemsVO> structureList = new ArrayList<WBSItemsVO>();
        Map<String,WBSItemsVO> activityObjectDB = this.getWBSDB(structureList);
        return getScheduleErrorList(activityObjectDB);
    }
    
    public List<WBSItemsVO> getSkipInfoErrorList(List<WBSItemsVO> editedWBSActivityList){
        List<WBSItemsVO> scheduleErrorActivityList = new ArrayList<WBSItemsVO>();
        if(!this.isFirst()){
            ProjectScheduleVO previousScheduleVO = this.getPreviousRevision();
            ProjectSchedule previousScheduleDom = new ProjectSchedule(previousScheduleVO);
            Set<String> strStateSet = new HashSet<String>();
            strStateSet.add(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED);
            List<WBSItemsVO> previousStartedActivityListAll = previousScheduleDom.getWBSItemsActivityOnly(strStateSet);
            for(WBSItemsVO statedPreVo : previousStartedActivityListAll){
                for(WBSItemsVO editedVO : editedWBSActivityList){
                    if(statedPreVo.getNames().equals(editedVO.getNames()) && "Y".equals(editedVO.getIsSkipped())){
                        editedVO.setOutDataAttributeValue("validatedActivityStarted", "Started Activity can not be Skipped. ");
                        scheduleErrorActivityList.add(editedVO);
                    }
                }
            }
        }
        return scheduleErrorActivityList;
    }

    /**
     * Ư�� Activity �����쿡 ���ԵǾ� �ִ��� ���θ� Ȯ��
     *
     * @param activityCode
     */
    public boolean isIncludedActivity(String activityCode){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[originActivityCode]", GlobalConstants.OQL_OPERATOR_EQUAL, activityCode);

        List<WBSGeneralActivityVO> activityList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        if(NullUtil.isNone(activityList)) return false;
        if(activityList.size() > 1) return false;

        WBSGeneralActivity wbsGeneralActivityDom = new WBSGeneralActivity(activityList.get(0));
        if(wbsGeneralActivityDom.getVo().getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED)){
            return true;
        }
        return false;
    }
    /**
     * Ư�� Activity �����쿡 ���ԵǾ� �ִ��� ���θ� Ȯ��
     *
     * @param activityCode
     */
    public List<WBSGeneralActivityVO> isIncludedActivity(List<CodeDetailVO> drCodeList){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();

        String activityCode = "";

        for( int inx = 0 ; inx < drCodeList.size() ; inx++){
            activityCode += drCodeList.get(inx).getNames() + ",";
        }
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "!From["+ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS+"].obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " " );
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[originActivityCode]", GlobalConstants.OQL_OPERATOR_IN, activityCode);
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_NOT_IN, ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED);
        
        List<WBSGeneralActivityVO> activityList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);

        return activityList;
    }
    public WBSItemsVO getActivity(String activityCode, String states){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[originActivityCode]", GlobalConstants.OQL_OPERATOR_IN, activityCode);
        if(StrUtil.isNotEmpty(states)){
            StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, states);
        }
        List<WBSItemsVO> relatedObjects = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        if(relatedObjects.size() > 0){
            return relatedObjects.get(0);
        }
        return null;
    }
    public List<ChangeProcessVO> getChangeProcessListAll(){
        return getChangeProcessListSub(null);
    }
    public List<ChangeProcessVO> getChangeProcessCurrentList(){
        Set<String> stateSet = new HashSet<String>();
        stateSet.add(ApplicationSchemaConstants.STATE_PROJECT_CHANGE_WORKING);
        stateSet.add(ApplicationSchemaConstants.STATE_PROJECT_CHANGE_PROCESSING);
        return getChangeProcessListSub(stateSet);
    }
    private List<ChangeProcessVO> getChangeProcessListSub(Set<String> stateSet){
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        if(stateSet != null && stateSet.size() > 0) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]",  GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(stateSet));
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CHANGEDITEMS, ApplicationSchemaConstants.BIZCLASS_CHANGEPROCESS, GlobalConstants.FLAG_TYPE_FROM, wherePatternBuf.toString(), paramPatternBuf.toString(),0);
    }

    public void completeActivitiesForDropProject(){
        List<WBSItemsVO> startedWBSItemList = getStartedWBSItemList();
        WBSItems wbsItems = null;
        for(WBSItemsVO vo : startedWBSItemList){
            wbsItems = new WBSItems(vo);
            WBSWorkflowServiceUtil.txnDeleteWorkflowAndCreateDeleteApprovalInfoToEP(vo.getObid());
            wbsItems.changePolicyAndState(wbsItems.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED);
        }
        this.changePolicyAndState(this.getLifeCycle(), ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_INACTIVE);
    }

/* Man Week ��ȸ Project Schedule 1 Level ��ȸ
 *
*/
   public List<WBSItemsVO> getPlanManWeekWBSSchedule(){
       List<WBSItemsVO> workWBSItemsList = this.getWBSItemsStructureLevelList(true,false,false);
       return workWBSItemsList;
   }

   public <T> List<T> getWBSItemsStructureLevelList(boolean excludeSkipped,boolean includeJobActivity, boolean isActivityOnly){
       return getWBSItemsStructureSub(includeJobActivity,isActivityOnly,excludeSkipped, 1);
   }
   
   public List<HashMap<String, String>> getProjectPhaseFilterList(){
       List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
       List<WBSPhasesVO> wbsPhaseList = getWBSPhaseList();
       HashMap<String, String> map = null;
       for(WBSPhasesVO wbsPhasesVO : wbsPhaseList){
           if("N".equals(wbsPhasesVO.getIsSkipped()) && !ApplicationSchemaConstants.STATE_WBS_PHASE_SKIPPED.equals(wbsPhasesVO.getStates()) ){
               map = new HashMap<String, String>();
               String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
               if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                   map.put("titles", wbsPhasesVO.getActivityNameKor());
               }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
                   map.put("titles", wbsPhasesVO.getActivityNameChi());
               }else{
                   map.put("titles", wbsPhasesVO.getActivityNameEng());
               }
               map.put("names", wbsPhasesVO.getPhaseName());
               resultList.add(map);
           }
       }
       
       List<HashMap<String, String>> phaseFilterList = new ArrayList<HashMap<String, String>>();
       if(!NullUtil.isNone(resultList)){
           HashSet<HashMap<String, String>> phaseSet = new HashSet<HashMap<String, String>>();
           phaseSet.addAll(resultList);
           if(phaseSet.size() > 0){
               phaseFilterList.addAll(phaseSet);
               Collections.sort(phaseFilterList, mapComparator);
           }
       }
       return phaseFilterList;
   }
   
   public static Comparator<Map<String, String>> mapComparator = new Comparator<Map<String, String>>() {
       public int compare(Map<String, String> m1, Map<String, String> m2) {
           return m1.get("names").compareTo(m2.get("names"));
       }
   };

}

