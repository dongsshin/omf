/**
 * ===========================================
  * System Name : LGE PLM Project
 * Program ID : WBSController.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 24.  heonhyung.lee   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rap.code.service.CodeService;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.foundation.ui.service.UIService;
import com.rap.omc.framework.annotation.SCRequestDataset;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.ResponseMapper;
import com.rap.projectbase.wbs.service.WBSService;
import com.rap.projectbase.wbs.service.WBSTemplateService;
import com.rap.projectbase.wbs.util.WBSTemplateUtil;
import com.rap.projectbase.wbs.util.model.DHTMLGanttLinkVO;
import com.rap.projectbase.wbs.util.model.DHTMLGanttWBSActivityVO;
import com.rap.user.service.UsersService;

import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.dom.ProjectSchedule;
import rap.api.object.project.dom.Projects;
import rap.api.object.project.dom.WBSActivities;
import rap.api.object.project.dom.WBSItems;
import rap.api.object.project.dom.WBSJobActivity;
import rap.api.object.project.dom.WBSSubProjects;
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSActivitiesVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.project.model.WBSPhasesVO;
import rap.api.object.project.model.WBSSubProjectsVO;
import rap.api.object.project.model.WBSTemplateMasterVO;
import rap.api.object.relation.model.WBSDependencyVO;
import rap.api.object.workflow.model.WorkflowRouteVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.workflow.model.ActivitySimpleVO;
import rap.application.workflow.model.ReassignVO;
import rap.application.workflow.util.WBSUtil;


/**
 * <pre>
 * Class : WBSController
 * Description : TODO
 * </pre>
 * 
 * @author heonhyung.lee
 */
@Controller
public class WBSController {
    static final Logger LOGGER = LoggerFactory.getLogger(WBSTemplateController.class);
    
    @Resource(name = "uiService")
    private UIService uiService;
    
    @Resource(name = "wbsService")
    private WBSService wbsService;

    @Resource(name = "usersService")
    private UsersService usersService;
    
    @Resource(name = "codeService")
    private CodeService codeService;
    
//   
//    @Resource(name = "projectModelService")
//    private ProjectModelService projectModelService;
    
    @Resource(name = "wbsTemplateService")
    private WBSTemplateService wbsTemplateService;
        
    @RequestMapping("/wbs/initWBSItemsEdit.do")
    public String initWBSItemsEdit(@SCRequestDataset("projectObid") String projectObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        Projects projectDom = new Projects(projectObid);
        ProjectScheduleVO projectScheduleVO = wbsService.txnCreateProjectSchedule(projectDom);
        rm.setData("projectsVO", projectDom.getVo());
        rm.setData("projectScheduleVO", projectScheduleVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/initialLoadFromWBSTemplates.do")
    public String initialLoadFromWBSTemplates(@SCRequestDataset("projectObid") String projectObid, 
                                 @SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        if( !uiService.checkMenuAccess(ApplicationBizConstants.cmdWBSScheduleInitialLoad, projectScheduleObid) ){
            throw new ApplicationException( "plm.common.error.menu.access" );
        }
        wbsService.txnInitialLoadFromWBSTemplates(projectObid, projectScheduleObid);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/getWBSItemsStructureForEdit.do")
    public String getWBSItemsStructureForEdit(@SCRequestDataset("projectObid") String projectObid, 
                                              @SCRequestDataset("projectScheduleObid") String projectScheduleObid, 
                                              @SCRequestDataset("forSimulation") String forSimulation, 
                                              @SCRequestDataset("withJobActivity") String withJobActivity,
                                              @SCRequestDataset("includeSkip") String includeSkip, 
                                              @SCRequestDataset("openNodeList") String openNodeList, ModelMap map){

        ResponseMapper rm = new ResponseMapper();
        Map<String,ActivitySimpleVO> simpleActivityList= new HashMap<String,ActivitySimpleVO>();
        List<BusinessObjectRootVO> list = wbsService.getWBSItemsStructureForEdit(projectObid, projectScheduleObid, forSimulation, withJobActivity ,includeSkip ,simpleActivityList);
        Set<String> openedSet = StrUtil.convertArrayToSet(openNodeList.split(","));
        for(BusinessObjectRootVO vo : list){
            vo.setOutDataAttributeValue("isOpen", openedSet.contains(vo.getNames()));
        }
        rm.setData("wbsItems",list);
        rm.setData("projectScheduleVO",DomUtil.toDom(projectScheduleObid).getVo());
        rm.setData("simpleWBSItems",simpleActivityList);
        rm.setData("simpleWBSItemsOriginal",simpleActivityList);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbs/rescheduleSuccessiveActivity.do")
    public String rescheduleSuccessiveActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, 
                                              @SCRequestDataset("activityVO") BusinessObjectRootVO activityVO, 
                                              @SCRequestDataset("wbsActivityList") List<BusinessObjectRootVO> wbsActivityList, 
                                              ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        if(!(activityVO instanceof WBSActivitiesVO)) throw new FoundationException("System Error. Invalid Oject Class(" + activityVO.getClassName() + "). Should be Activity");
        List<WBSItemsVO> wbsItemsList = new ArrayList<WBSItemsVO>();
        for(BusinessObjectRootVO vo: wbsActivityList){
            if(vo instanceof WBSItemsVO){
                wbsItemsList.add((WBSItemsVO)vo);                    
            }
        }
        List<WBSItemsVO> scheduleErrorActivityList = wbsService.txnRescheduleSuccessiveActivity(projectScheduleObid, (WBSActivitiesVO)activityVO, wbsItemsList);
        rm.setData("scheduleErrorActivityList", scheduleErrorActivityList);
        
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbs/saveActivityManually.do")
    public String saveActivityManually(@SCRequestDataset("projectObid") String projectObid, 
                                @SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                @SCRequestDataset("wbsActivityList") List<BusinessObjectRootVO> wbsActivityList,
                                ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        ObjectRoot obj = new ObjectRoot(projectObid);
        ProjectScheduleVO projectScheduleVO = (ProjectScheduleVO)(wbsActivityList.size() > 0 && wbsActivityList.get(0) instanceof ProjectScheduleVO ? wbsActivityList.get(0) : null);
        List<WBSItemsVO> scheduleErrorActivityList = wbsService.txnSaveActivityManually(projectObid, projectScheduleObid, projectScheduleVO, wbsActivityList);
        WBSUtil.clearDependencyInfo(scheduleErrorActivityList);
        rm.setData("scheduleErrorActivityList", scheduleErrorActivityList);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbs/saveActivityManuallyNew.do")
    public String saveActivityManuallyNew(@SCRequestDataset("projectObid") String projectObid, 
                                @SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                @SCRequestDataset("wbsActivityList") List<BusinessObjectRootVO> wbsActivityList,
                                @SCRequestDataset("simpleWBSItems") List<ActivitySimpleVO> simpleWBSItems,
                                ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        ProjectScheduleVO projectScheduleVO = (ProjectScheduleVO)(wbsActivityList.size() > 0 && wbsActivityList.get(0) instanceof ProjectScheduleVO ? wbsActivityList.get(0) : null);
        List<WBSItemsVO> scheduleErrorActivityList = wbsService.txnSaveActivityManually(projectObid, projectScheduleObid, projectScheduleVO, wbsActivityList,simpleWBSItems);
        
        WBSUtil.clearDependencyInfo(scheduleErrorActivityList);
        rm.setData("scheduleErrorActivityList", scheduleErrorActivityList);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbs/saveSimulationResult.do")
    public String saveSimulationResult(@SCRequestDataset("projectObid") String projectObid,
                                       @SCRequestDataset("projectScheduleObid") String projectScheduleObid, 
                                       @SCRequestDataset("wbsActivityList") List<BusinessObjectRootVO> wbsActivityList, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        List<WBSItemsVO> wbsItemsList = new ArrayList<WBSItemsVO>();
        ProjectScheduleVO projectScheduleVO = null;
        for(BusinessObjectRootVO vo: wbsActivityList){
            if(vo instanceof WBSItemsVO){
                if(((WBSItemsVO)vo).getIsSkipped().equals("Y")){
                    ((WBSItemsVO)vo).setPlanDuration(0);
                }
                ((WBSItemsVO)vo).setPlanStartDate(((WBSItemsVO)vo).getPlanStartDate());
                ((WBSItemsVO)vo).setPlanEndDate(((WBSItemsVO)vo).getPlanEndDate());
                wbsItemsList.add((WBSItemsVO)vo);                    
            }else if(vo instanceof ProjectScheduleVO){
                projectScheduleVO = (ProjectScheduleVO)vo;
            }
        }
        List<WBSItemsVO> scheduleErrorActivityList = wbsService.txnSaveSimulationResult(projectObid, projectScheduleVO, wbsItemsList);
        rm.setData("scheduleErrorActivityList", scheduleErrorActivityList);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbs/initWBSItems.do")
    public String initWBSItems(@SCRequestDataset("projectObid") String projectObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        Projects projectDom = new Projects(projectObid);
        ProjectScheduleVO projectScheduleVO = projectDom.getProjectLatestReleaseSchedule();
        rm.setData("projectScheduleVO", projectScheduleVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/getWBSItemsStructure.do")
    public String getWBSItemsStructure(@SCRequestDataset("projectObid") String projectObid,
                                       @SCRequestDataset("projectScheduleObid") String projectScheduleObid, 
                                       @SCRequestDataset("withJobActivity") String withJobActivity,
                                       @SCRequestDataset("includeSkip") String includeSkip, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        
        rm.setData("wbsItems",wbsService.getWBSItemsStructureForView(projectObid, projectScheduleObid, withJobActivity,true,"Y".equals(includeSkip)));
        rm.setData("projectScheduleVO", projectScheduleDom.getVo());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/initWBSActivity.do")
    public String initWBSActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                  @SCRequestDataset("obid") String obid, 
                                  ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        if(!StringUtils.isEmpty(obid)){
            WBSItems wbsActivities = (WBSItems)DomUtil.toDom(obid);
            
            if(!"None".equals(wbsActivities.getVo().getRoleList())){
                rm.setData("roleVOList", wbsActivities.getAssignedProjectRoleList());
            }
            if(!"None".equals(wbsActivities.getVo().getActivityOwnerList())){
                rm.setData("activityOwnerList", wbsActivities.getAssignedProjectUserList());
            }
            rm.setData("wbsActivityVO", wbsActivities.getVo());
            if(NullUtil.notNone(projectScheduleObid)){
                rm.setData("dependency", wbsService.getWBSDependencyList(projectScheduleObid, obid));
            }
        }
        
        rm.setData("startValidationMethodList", WBSTemplateUtil.getActivityStandardStartValidationMethodList());
        rm.setData("startExecutionMethodList", WBSTemplateUtil.getActivityStandardStartMethodList());
        rm.setData("completeValidationMethodList", WBSTemplateUtil.getActivityStandardCompleteValidationMethodList());
        rm.setData("postExecutionMethodList", WBSTemplateUtil.getActivityStandardPostMethodList());
        
        rm.setData("completeTypeRangeList", codeService.getRangeList("WBSItems.completeType"));
        rm.setData("activityCategoryRangeList", codeService.getRangeList("WBSItems.activityCategory"));
        
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/retrieveWBSActivity.do")
    public String retrieveWBSActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                      @SCRequestDataset("obid") String obid, 
                                      ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        WBSItems wbsActivities = (WBSItems)DomUtil.toDom(obid);
        
        if(!"None".equals(wbsActivities.getVo().getRoleList())){
            rm.setData("roleVOList", wbsActivities.getAssignedProjectRoleList());
        }
        if(!"None".equals(wbsActivities.getVo().getActivityOwnerList())){
            rm.setData("activityOwnerList", wbsActivities.getAssignedProjectUserList());
        }
        rm.setData("wbsActivityVO", wbsActivities.getVo());
        if(NullUtil.notNone(projectScheduleObid)){
            rm.setData("dependency", wbsService.getWBSDependencyList(projectScheduleObid, obid));
        }
        
        wbsActivities.setOutDataAttributeValue("startValidationMethodDesc", WBSTemplateUtil.getActivityStandardStartValidationMethodDescription(wbsActivities.getVo().getStartValidationMethod()));
        wbsActivities.setOutDataAttributeValue("startExecutionMethodDesc", WBSTemplateUtil.getActivityStandardStartMethodDescription(wbsActivities.getVo().getStartExecutionMethod()));
        wbsActivities.setOutDataAttributeValue("completeValidationMethodDesc", WBSTemplateUtil.getActivityStandardCompleteValidationMethodDescription(wbsActivities.getVo().getCompleteValidationMethod()));
        wbsActivities.setOutDataAttributeValue("postExecutionMethodDesc", WBSTemplateUtil.getActivityStandardPostMethodDescription(wbsActivities.getVo().getPostExecutionMethod()));
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/createWBSActivity.do")
    public String createWBSActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                    @SCRequestDataset("parentObid") String parentObid,
                                    @SCRequestDataset("wbsActivitiesVO") WBSActivitiesVO wbsActivitiesVO,
                                    @SCRequestDataset("addingPosition") String addingPosition,
                                    @SCRequestDataset("targetSequences") String targetSequences,
                                    @SCRequestDataset("activityOwnerCreateList") List<UsersVO> activityOwnerCreateList,
                                    @SCRequestDataset("dependencyCreateList") List<WBSDependencyVO> dependencyCreateList,
                                    ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnCreateWBSItem(projectScheduleObid, parentObid, wbsActivitiesVO, activityOwnerCreateList,
                                    dependencyCreateList, addingPosition, !"null".equals(targetSequences) ? Integer.parseInt(targetSequences) : null);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/getWBSItemTemplatesStructureForSchedule.do")
    public String getWBSItemTemplatesStructure(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        ProjectSchedule scheduleDom = new ProjectSchedule(projectScheduleObid);
        WBSTemplateMasterVO wbsTemplateMasterVO = scheduleDom.getLatestWBSTemplateMaster();
        rm.setData("wbsItemTemplates", wbsTemplateService.getWBSItemTemplatesStructure(wbsTemplateMasterVO.getObid(), "N"));
        rm.setModelMap(map); 
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/updateWBSActivity.do")
    public String updateWBSActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                    @SCRequestDataset("parentObid") String parentObid,
                                    @SCRequestDataset("wbsActivitiesVO") WBSActivitiesVO wbsActivitiesVO,
                                    @SCRequestDataset("activityOwnerCreateList") List<UsersVO> activityOwnerCreateList,
                                    @SCRequestDataset("activityOwnerUpdateList") List<UsersVO> activityOwnerUpdateList,
                                    @SCRequestDataset("activityOwnerDeleteList") List<UsersVO> activityOwnerDeleteList,
                                    @SCRequestDataset("dependencyCreateList") List<WBSDependencyVO> dependencyCreateList,
                                    @SCRequestDataset("dependencyUpdateList") List<WBSDependencyVO> dependencyUpdateList,
                                    @SCRequestDataset("dependencyDeleteList") List<WBSDependencyVO> dependencyDeleteList,
                                    ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnUpdateWBSItem(projectScheduleObid, wbsActivitiesVO, activityOwnerCreateList, activityOwnerUpdateList, activityOwnerDeleteList, dependencyCreateList, dependencyUpdateList, dependencyDeleteList);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/initEditActivityOwner.do")
    public String initEditActivityOwner(@SCRequestDataset("obid") String obid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        WBSActivities wbsActivities = new WBSActivities(obid);      
        if(!"None".equals(wbsActivities.getVo().getActivityOwnerList())){
            rm.setData("activityOwnerList", wbsActivities.getAssignedProjectUserList());
        }
        rm.setData("wbsActivitiesVO", wbsActivities.getVo());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/editActivityOwner.do")
    public String editActivityOwner(@SCRequestDataset("projectObid") String projectObid,
                                    @SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                    @SCRequestDataset("obid") String obid, 
                                    @SCRequestDataset("activityOwnerCreateList") List<UsersVO> activityOwnerCreateList,
                                    @SCRequestDataset("activityOwnerUpdateList") List<UsersVO> activityOwnerUpdateList,
                                    @SCRequestDataset("activityOwnerDeleteList") List<UsersVO> activityOwnerDeleteList,
                                    ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        WBSActivities wbsActivities = new WBSActivities(obid);        
        wbsService.txnUpdateActivityOwner(projectObid, projectScheduleObid, wbsActivities.getVo(), activityOwnerCreateList, activityOwnerUpdateList, activityOwnerDeleteList);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    
//    @RequestMapping("/wbs/initEditStartedActivityOwner.do")
//    public String initEditStartedActivityOwner(@SCRequestDataset("obid") String obid, ModelMap map){
//        ResponseMapper rm = new ResponseMapper();
//        WBSActivities wbsActivities = new WBSActivities(obid);      
//        List<ApprovalVO> approvalList = wbsActivityWorkflowService.retrieveWBSActivityWorkflow(wbsActivities);
//        rm.setData("approvalList", approvalList);
//        rm.setModelMap(map);        
//        return GlobalConstants.AJAX_VIEW;
//    }
    
    @RequestMapping("/wbs/editStartedActivityOwner.do")
    public String editStartedActivityOwner(@SCRequestDataset("obid") String activityObid,
                                           @SCRequestDataset("projectObid") String projectObid,
                                           @SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                           @SCRequestDataset("reassignVOCreateList") List<ReassignVO> reassignVOCreateList, 
                                           @SCRequestDataset("reassignVOUpdateList") List<ReassignVO> reassignVOUpdateList, 
                                           @SCRequestDataset("reassignVODeleteList") List<ReassignVO> reassignVODeleteList, 
                                           ModelMap map){
        wbsService.txnEditStartedActivityOwner(activityObid, projectObid, projectScheduleObid, reassignVOCreateList, reassignVOUpdateList, reassignVODeleteList);
        ResponseMapper rm = new ResponseMapper();
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/initEditInstruction.do")
    public String initEditInstruction(@SCRequestDataset("obid") String obid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        WBSItems wbsItems = new WBSItems(obid);      
        rm.setData("wbsItemsVO", wbsItems.getVo());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/editInstruction.do")
    public String editInstruction(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                    @SCRequestDataset("wbsActivitiesVO") WBSActivitiesVO wbsActivitiesVO, 
                                    ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnEditInstruction(projectScheduleObid, wbsActivitiesVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/initJobActivity.do")
    public String initJobActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                    @SCRequestDataset("obid") String obid,
                                    ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        if(!NullUtil.isNone(obid)){
            rm.setData("wbsJobActivityVO", new WBSJobActivity(obid).getVo());
        }
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/createJobActivity.do")
    public String createJobActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
            @SCRequestDataset("parentObid") String parentObid,
            @SCRequestDataset("wbsJobActivityVO") WBSJobActivityVO wbsJobActivityVO,
            @SCRequestDataset("addingPosition") String addingPosition,
            @SCRequestDataset("targetSequences") String targetSequences,
            ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnCreateJobActivity(projectScheduleObid, parentObid, wbsJobActivityVO, addingPosition, !"null".equals(targetSequences) ? Integer.parseInt(targetSequences) : null);
        
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/updateJobActivity.do")
    public String updateJobActivity( @SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                     @SCRequestDataset("wbsJobActivityVO") WBSJobActivityVO wbsJobActivityVO, 
                                     ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnUpdateJobActivity(projectScheduleObid, wbsJobActivityVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/initWBSPhase.do")
    public String initWBSPhase(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                               @SCRequestDataset("obid") String obid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        WBSSubProjects wbsSubProjects = (WBSSubProjects)DomUtil.toDom(obid);
        rm.setData("wbsPhaseVO", wbsSubProjects.getVo());
        rm.setData("startValidationMethodList", WBSTemplateUtil.getActivityStandardStartValidationMethodList());
        rm.setData("startExecutionMethodList", WBSTemplateUtil.getActivityStandardStartMethodList());
        rm.setData("completeValidationMethodList", WBSTemplateUtil.getActivityStandardCompleteValidationMethodList());
        rm.setData("postExecutionMethodList", WBSTemplateUtil.getActivityStandardPostMethodList());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/updateWBSPhase.do")
    public String updateWBSPhase(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                 @SCRequestDataset("wbsPhaseVO") WBSPhasesVO wbsPhaseVO, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        DomUtil.toDom(wbsPhaseVO).modifyObject();
        wbsService.txnUpdateWBSItem(projectScheduleObid, wbsPhaseVO, null, null, null, null, null, null);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/initWBSSubProject.do")
    public String initWBSSubProject(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                        @SCRequestDataset("obid") String obid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        WBSSubProjects wbsSubProjects = (WBSSubProjects)DomUtil.toDom(obid);
        rm.setData("wbsSubProjectsVO", wbsSubProjects.getVo());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/createWBSSubProject.do")
    public String createWBSSubProject(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                      @SCRequestDataset("wbsItemsVO") WBSSubProjectsVO wbsItemsVO,
                                      ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnCreateWBSItem(projectScheduleObid, projectScheduleObid, wbsItemsVO, null, null, null, null );
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }

    @RequestMapping("/wbs/deleteWBSActivity.do")
    public String deleteWBSActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
                                    @SCRequestDataset("obid") String obid, 
                                    ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnDeleteWBSItem(projectScheduleObid, obid);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/wbsItemsEventFilter.do")
    public String wbsItemsEventFilter(String obid, ModelMap model){
        ResponseMapper rm = new ResponseMapper();
        rm.setData(new ProjectSchedule(obid).getProjectPhaseFilterList());
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }    
    @RequestMapping("/wbs/wbsItemsLevelFilter.do")
    public String wbsItemslevelFilter(String obid, ModelMap model){
        ResponseMapper rm = new ResponseMapper();
        List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
        for(int i=1; i<4; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("names", i+"");
            map.put("titles", i+"");
            resultList.add(map);
        }
        rm.setData(resultList);
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }    
    @RequestMapping("/wbs/wbsScheduleRevisionFilter.do")
    public String wbsScheduleRevisionFilter(String obid, ModelMap model){
        ResponseMapper rm = new ResponseMapper();
        rm.setData(wbsService.getWBSScheduleRevisionCombo(obid));
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping( "/wbs/weeklyProgress/retrieveMyActivityWeeklyProgressList.do" )
    public String retrieveMyActivityWeeklyProgressList( ModelMap map ) {
        Set<String> classFilter = new HashSet<String>();
        
        ResponseMapper rm = new ResponseMapper();
        rm.setData( "npiActivityList", usersService.getMyActivities(classFilter, true, true) );
        rm.setData( "jobActivityList", usersService.getMyJobActivities(true) );
        rm.setModelMap( map );
        
        return GlobalConstants.AJAX_VIEW;
        
    }
    
    @RequestMapping("/wbs/weeklyProgress/saveMyActivityWeeklyProgress.do")
    public String saveMyActivityWeeklyProgress(
            @SCRequestDataset("yearWeek") String yearWeek,
            @SCRequestDataset("activityList") List<WBSItemsVO> activityList,
            ModelMap map) {
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        try {
            wbsService.txnUpdateWeeklyProgress(yearWeek, activityList, null);
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap(map);
        }

        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/weeklyProgress/saveMyJobActivityWeeklyProgress.do")
    public String saveMyJobActivityWeeklyProgress(
            @SCRequestDataset("yearWeek") String yearWeek,
            @SCRequestDataset("jobActivityList") List<WBSJobActivityVO> jobActivityList,
            ModelMap map) {
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        try {
            wbsService.txnUpdateWeeklyProgress(yearWeek, null, jobActivityList);
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap(map);
        }

        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/weeklyProgress/saveExpectedMpdate.do")
    public String saveExpectedMpdate(
            @SCRequestDataset("projectList") List<ProjectsVO> projectList,
            ModelMap map) {
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        try {
            wbsService.txnUpdateProjectMpDate(projectList);
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap(map);
        }

        return GlobalConstants.AJAX_VIEW;
    }

    @RequestMapping("/wbs/viewGanttPopup.do")
    public String retrieveLayout(String layout, String obid, ModelMap model ){
        model.addAttribute("layout", layout);
        model.addAttribute("obid", obid);
        return "/wbs/wbsGantt";
    }
    
    @RequestMapping("/wbs/viewGantt.do")
    public String viewGantt(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, 
                            @SCRequestDataset("viewingType") String viewingType, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        List<DHTMLGanttWBSActivityVO> ganttActivityList = new ArrayList<DHTMLGanttWBSActivityVO>();
        List<DHTMLGanttLinkVO> linkList = new ArrayList<DHTMLGanttLinkVO>();

        wbsService.getWBSTemplateListForGantt(projectScheduleObid, 
        		ganttActivityList, 
        		linkList, 
        		WBSTemplateUtil.GANTT_VIEWING_TYPE.valueOf(viewingType));
        rm.setData( "ganttActivityList", ganttActivityList );
        rm.setData( "linkList", linkList );
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/memberRefresh.do")
    public String memberRefresh(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        if( !uiService.checkMenuAccess(ApplicationBizConstants.cmdWBSScheduleMemberRefresh, projectScheduleObid) ){
            throw new ApplicationException( "plm.common.error.menu.access" );
        }
        wbsService.txnRefreshProjectMember2Assigned(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/skipInfoRefresh.do")
    public String skipInfoRefresh(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnRefreshSkipInfoAsGrade(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }

    @RequestMapping("/wbs/assignActivityOwnerToPL.do")
    public String assignActivityOwnerToPL(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnAssignActivityOwnerToPL(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }

    @RequestMapping("/wbs/scheduleValidate.do")
    public String scheduleValidate(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnValidateSchedule(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/scheduleApprovalRequest.do")
    public String scheduleApprovalRequest(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnRequestScheduleApproval(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbs/scheduleApprovalReject.do")
    public String scheduleApprovalReject(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnRejectScheduleApproval(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbs/scheduleComplete.do")
    public String scheduleComplete(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnCompleteScheduleApproval(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/scheduleStart.do")
    public String scheduleStart(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnStartScheduleExcution(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/scheduleRevise.do")
    public String scheduleRevise(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnReviseSchedule(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/scheduleCancelRevise.do")
    public String scheduleCancelRevise(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnCancelReviseSchedule(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/initChangeOwnerSchedule.do")
    public String initChangeOwnerSchedule(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleObid);
        rm.setData("projectScheduleVO", projectSchedule.getVo());
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/changeOwnerSchedule.do")
    public String changeOwnerSchedule(@SCRequestDataset("projectScheduleObid") String projectScheduleObid,
            @SCRequestDataset("newOwner") String newOwner,ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnChangeOwnerSchedule( projectScheduleObid, newOwner);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/completeActivity.do")
    public String completeActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, @SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnForceCompleteWBSActivityFromWBS(projectScheduleObid,obid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/skipActivity.do")
    public String skipActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, @SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnForceSkipWBSActivityFromWBS(projectScheduleObid,obid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/forceStartActivity.do")
    public String forceStartActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, @SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnForceStartActivity(projectScheduleObid,obid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/completeMyToDoActivity.do")
    public String completeMyToDoActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, @SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnCompleteMyToDoActivity(projectScheduleObid,obid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/reviseWBSItem.do")
    public String reviseWBSItem(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, @SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnReviseWBSItem(projectScheduleObid, obid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/reviseActivityFor2ndPerfrom.do")
    public String reviseActivityFor2ndPerfrom(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, @SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnReviseActivityFor2ndPerform(projectScheduleObid, obid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }

    @RequestMapping("/wbs/cancelReviseWBSItem.do")
    public String cancelReviseWBSItem(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, @SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnCancelReviseWBSItem(projectScheduleObid, obid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/cancelReviseAllWBSItem.do")
    public String cancelReviseAllWBSItem(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnCancelReviseAllWBSItem(projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbs/initActivityApproval.do")
    public String initActivityApproval(@SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        WBSActivities wbsActivities = (WBSActivities)DomUtil.toDom(obid);
        
        List<ObjectRootVO> workflowRouteVOList = wbsActivities.getWorkflowRouteVOList();
        String projectScheduleObid = ((WorkflowRouteVO)workflowRouteVOList.get(0)).getProcessTimestamp();
        if(!"None".equals(wbsActivities.getVo().getRoleList())){
            rm.setData("roleVOList", wbsActivities.getAssignedProjectRoleList());
        }
        if(!"None".equals(wbsActivities.getVo().getActivityOwnerList())){
            rm.setData("activityOwnerList", wbsActivities.getAssignedProjectUserList());
        }
        rm.setData("wbsActivityVO", wbsActivities.getVo());
        rm.setData("jobActivityList", wbsService.getJobActivityList(obid));
        rm.setData("projectScheduleObid", projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/canBeCompleteMyToDoActivity.do")
    public String canBeCompleteMyToDoActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, @SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        
        rm.setData("canBeCompleteMyToDoActivity", wbsService.canBeCompleteMyToDoActivity(projectScheduleObid, obid));
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/getInboxTaskObjectForMyToDoActivity.do")
    public String getInboxTaskObjectForMyToDoActivity(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, @SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        
        rm.setData("workflowInboxTaskVO", wbsService.getInboxTaskObjectForMyToDoActivity(projectScheduleObid, obid));
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }

    @RequestMapping("/wbs/skipWBSPhase.do")
    public String skipWBSPhase(@SCRequestDataset("projectScheduleObid") String projectScheduleObid, @SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsService.txnSkipWBSPhase(projectScheduleObid, obid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/unlockHolingProject.do")
    public String unlockHolingProject(@SCRequestDataset("projectObid") String projectObid, 
                                      @SCRequestDataset("projectScheduleObid") String projectScheduleObid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsService.unlockHolingProject(projectObid, projectScheduleObid);
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/getActivityDelayStatusSummary.do")
    public String getActivityDelayStatusSummary(@SCRequestDataset("projectObid") String projectObid, ModelMap map){
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        
        Map<String, List<WBSItemsVO>> result = new HashMap<String,List<WBSItemsVO>>();
        try {
            if ( NullUtil.isNone(projectObid) ) {
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Error occurred.";
            } else {
                result = wbsService.getActivityDelayStatus(projectObid);
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap(map);
            rm.setData("npiStartedActivities", result.get("npiStartedActivities"));
            rm.setData("swStartedActivities", result.get("swStartedActivities"));
            rm.setData("npiScheduledActivities", result.get("npiScheduledActivities"));
        }
        return GlobalConstants.AJAX_VIEW;
    }
//    
//    /**
//     * ECMS 수익성 어쩌구 완료
//     * @return
//     */
//    @RequestMapping("/wbs/ecmsCheckProjectRegister.do")
//    public String ecmsCheckProjectRegister( String vSource, String vActCode, String vPjtCode ){
//        
//        ProjectsVO projectVo = BusinessObjectMaster.findBusinessObjectMaster("Projects", vPjtCode);  // PTPROJECT
//
//        if ( projectVo == null || NullUtil.isNone(projectVo.getObid()) ) {
//            throw new ApplicationException("Not exist project. 프로젝트가 없습니다.["+vPjtCode+ "]");
//        } else {
//            // ECMS 수주 수익성 분석 요청 을 완료한다. ATM-00000000968
//            String[] autoCompleteCode = {"ATM-00000000968"};  // 수주 수익성 분석 요청
//            if ( PdmsConstants.PRODUCT_PROJECT_TYPE_MODEL.equals(projectVo.getClassName()) ) {
//                ModelDevelopmentGeneralProject modelDevDom = DomUtil.toDom(projectVo.getObid());
//                try {
//                    if ( autoCompleteCode != null && autoCompleteCode.length > 0 ) {
//                        for ( String prodAct : autoCompleteCode ) {
//                            modelDevDom.completeActivity(prodAct);      
//                        }
//                    }
//                } catch(Exception e) {
//                    e.printStackTrace(); // No Activity(ATM-00000000968).(No Activity(ATM-00000000968).)
//                }
//            }
//            
//        }
//        return "wbs/ecmsCheckProjectRegister";
//    }
}
