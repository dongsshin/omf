/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 24.  heonhyung.lee   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.foundation.ui.model.CheckboxItemVO;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.projectbase.wbs.util.WBSTemplateUtil.GANTT_VIEWING_TYPE;
import com.rap.projectbase.wbs.util.model.DHTMLGanttLinkVO;
import com.rap.projectbase.wbs.util.model.DHTMLGanttWBSActivityVO;

import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.dom.Projects;
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSActivitiesVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.project.model.WBSPhasesVO;
import rap.api.object.relation.model.WBSDependencyVO;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.application.workflow.model.ActivitySimpleVO;
import rap.application.workflow.model.ReassignVO;

/**
 * <pre>
 * Class : WBSService
 * Description : TODO
 * </pre>
 * 
 * @author heonhyung.lee
 */
public interface WBSService {
    public void getWBSTemplateListForGantt(String projectScheduleObid, List<DHTMLGanttWBSActivityVO> ganttActivityList, List<DHTMLGanttLinkVO> linkList, GANTT_VIEWING_TYPE viewingType);
    public boolean isExistsProjectSchedule(String projectObid);
    public ProjectScheduleVO txnCreateProjectSchedule(Projects projectDom);
    public void txnInitialLoadFromWBSTemplates(String projectObid, String projectScheduleObid);
    public List<WBSPhasesVO> getWBSProjectPhasesBasedOnLatest(String projectObid);
    public List<WBSItemsVO> getWBSItemsStructure(String projectObid);
    public <T> List<T> getWBSItemsStructureForEdit(String projectObid, String projectScheduleObid, String forSimulation, String withJobActivity, String includeSkip);
    public <T> List<T> getWBSItemsStructureForEdit(String projectObid, String projectScheduleObid, String forSimulation, String withJobActivity, String includeSkip,Map<String,ActivitySimpleVO> simpleActivityList);
    public <T> List<T> getWBSItemsStructureForView(String projectObid, String projectScheduleObid, String withJobActivity,boolean includeScheduleVO,boolean includeSkip);
    public void txnCreateWBSItem(String projectScheduleObid, String parentObid, WBSItemsVO wbsItemVO);
    public void txnCreateWBSItem(String projectScheduleObid, String parentObid, WBSItemsVO wbsItemVO, Integer sequence);
    public void txnCreateWBSItem(String projectScheduleObid, String parentObid, WBSItemsVO wbsItemVO, 
                                 List<UsersVO> activityOwnerCreateList, List<WBSDependencyVO> wbsDependency, String addingPosition, Integer targetSequence);
    public void txnUpdateWBSItem(String projectScheduleObid, WBSItemsVO wbsItemVO, 
                                 List<UsersVO> activityOwnerCreateList, List<UsersVO> activityOwnerUpdateList, List<UsersVO> activityOwnerDeleteList, 
                                 List<WBSDependencyVO> dependencyCreateList, List<WBSDependencyVO> dependencyUpdateList, List<WBSDependencyVO> dependencyDeleteList);
    public void tnxUpdateProjectRole(String projectObid, String projectScheduleObid, WBSActivitiesVO wbsActivitiesVO, String roleCodeList);
//    public void txnEditStartedActivityOwner(String projectObid, String projectScheduleObid, WBSActivitiesVO wbsActivitiesVO, List<UsersVO> activityOwnerCreateList, List<UsersVO> activityOwnerUpdateList, List<UsersVO> activityOwnerDeleteList);
    public void txnUpdateActivityOwner(String projectObid, String projectScheduleObid, WBSActivitiesVO wbsActivitiesVO, List<UsersVO> activityOwnerCreateList, List<UsersVO> activityOwnerUpdateList, List<UsersVO> activityOwnerDeleteList);
    public void txnUpdateWBSDependency(ProjectScheduleVO projectScheduleVO, String wbsObid, List<WBSDependencyVO> dependencyCreateList, List<WBSDependencyVO> dependencyUpdateList, List<WBSDependencyVO> dependencyDeleteList);
    public void txnEditInstruction(String projectScheduleObid, WBSActivitiesVO wbsActivitiesVO);
    public void txnDeleteWBSItem(String projectScheduleObid, String obidList);
    public void txnCreateJobActivity(String projectScheduleObid, String activityObid, WBSJobActivityVO wbsJobActivityVO, String addingPosition, Integer targetSequence);
    public void txnUpdateJobActivity(String projectScheduleObid, WBSJobActivityVO wbsJobActivityVO);
    public void txnUpdateWeeklyProgress(String yearWeek, List<WBSItemsVO> activityList, List<WBSJobActivityVO> jobActivityList);
    public void txnUpdateProjectMpDate(List<ProjectsVO> projectList);
    
    public List<WBSDependencyVO> getWBSDependencyList(String obidContext, String obid);
    public List<BusinessRelationObjectVO> retrieveDocument(String obid);

    public List<WBSItemsVO> txnSaveActivityManually(String projectObid, String projectScheduleObid, ProjectScheduleVO projectScheduleVO, List<BusinessObjectRootVO> wbsActivityList);
    public List<WBSItemsVO> txnSaveActivityManually(String projectObid, String projectScheduleObid, ProjectScheduleVO projectScheduleVO, List<BusinessObjectRootVO> wbsItemsList, List<ActivitySimpleVO> simpleWBSItems);
    
    public void txnReviseWBSItem(String projectScheduleObid, String wbsItemObid);
    public void txnReviseActivityFor2ndPerform(String projectScheduleObid, String wbsItemObid);
    public void txnCancelReviseWBSItem(String projectScheduleObid, String wbsItemObid);
    public void txnCancelReviseAllWBSItem(String projectScheduleObid);
    
    public List<WBSItemsVO> txnRescheduleSuccessiveActivity(String projectScheduleObid, WBSActivitiesVO activityVO, List<WBSItemsVO> wbsActivityList);
    public List<WBSItemsVO> txnSaveSimulationResult(String projectObid, ProjectScheduleVO projectScheduleVO, List<WBSItemsVO> wbsActivityList);
    
    public void txnRefreshProjectMember2Assigned(String projectScheduleObid);
    public void txnRefreshSkipInfoAsGrade(String projectScheduleObid);
    public void txnAssignActivityOwnerToPL(String projectScheduleObid);
    
    public void txnValidateSchedule(String projectScheduleObid);
    public void txnRequestScheduleApproval(String projectScheduleObid);
    public void txnRejectScheduleApproval(String projectScheduleObid);
    public void txnCompleteScheduleApproval(String projectScheduleObid);
    public void txnStartScheduleExcution(String projectScheduleObid);
    public ProjectScheduleVO txnReviseSchedule(String projectScheduleObid);
    public void txnCancelReviseSchedule(String projectScheduleObid);
    public void txnChangeOwnerSchedule(String projectScheduleObid, String userId);
    public void txnForceCompleteWBSActivityFromWBS(String projectScheduleObid, String wbsActivityObid);
    
    public void txnCompleteMyToDoActivity(String projectScheduleObid, String wbsActivityObid);
    public void txnForceStartActivity(String projectScheduleObid, String wbsActivityObid);

    public void txnCompleteActivitiesForDropProject(String projectScheduleObid);
    
    public List<CheckboxItemVO> getSimulationOptionGroup(String obid, String obidContext, UserSession userSession, String dummy );
    public List<CheckboxItemVO> getJobActivityFilter(String obid, String obidContext, UserSession userSession, String dummy );
    public List<CheckboxItemVO> getSkipActivityFilter(String obid, String obidContext, UserSession userSession, String dummy );
    public List<HashMap<String, String>> getWBSScheduleRevisionCombo(String projectScheduleObid);
    public boolean canBeCompleteMyToDoActivity(String projectScheduleObid, String obid);
    public WorkflowInboxTaskVO getInboxTaskObjectForMyToDoActivity(String projectScheduleObid, String obid);
    public void txnSkipWBSPhase(String projectScheduleObid, String obid);
    public void txnEditStartedActivityOwner(String activityObid, String projectObid, String projectScheduleObid, List<ReassignVO> reassignVOCreateList, List<ReassignVO> reassignVOUpdateList, List<ReassignVO> reassignVODeleteList);
    public void txnForceSkipWBSActivityFromWBS(String projectScheduleObid, String obid);
    public List<WBSJobActivityVO> getJobActivityList(String obid);
    public Map<String,List<WBSItemsVO>> getActivityDelayStatus(String projectObid);
    
    public void unlockHolingProject(String projectObid, String projectScheduleObid);
}
