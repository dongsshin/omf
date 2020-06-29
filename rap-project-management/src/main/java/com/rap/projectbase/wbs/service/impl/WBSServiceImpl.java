/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 24.  heonhyung.lee   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.ui.model.CheckboxItemVO;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.intf.IfPlmDataByTriggerVO;
import com.rap.omc.intf.TriggerUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.TimeServiceUtil;
import com.rap.projectbase.wbs.service.WBSService;
import com.rap.projectbase.wbs.util.WBSTemplateUtil;
import com.rap.projectbase.wbs.util.WBSTemplateUtil.GANTT_VIEWING_TYPE;
import com.rap.projectbase.wbs.util.model.DHTMLGanttLinkVO;
import com.rap.projectbase.wbs.util.model.DHTMLGanttWBSActivityVO;

import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.dom.ProjectSchedule;
import rap.api.object.project.dom.Projects;
import rap.api.object.project.dom.RevisedProjects;
import rap.api.object.project.dom.WBSActivities;
import rap.api.object.project.dom.WBSItems;
import rap.api.object.project.dom.WBSJobActivity;
import rap.api.object.project.dom.WBSPhases;
import rap.api.object.project.dom.WBSTemplateMaster;
import rap.api.object.project.model.ProjectPersonVO;
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSActivitiesVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.project.model.WBSPhasesVO;
import rap.api.object.project.model.WBSRegularActivityVO;
import rap.api.object.project.model.WBSTemplateMasterVO;
import rap.api.object.relation.dom.AllocatedToMember;
import rap.api.object.relation.dom.ControlledByProjectScheduleContext;
import rap.api.object.relation.dom.WBSDependency;
import rap.api.object.relation.model.AllocatedToMemberVO;
import rap.api.object.relation.model.AssignedToActivityVO;
import rap.api.object.relation.model.ControlledByProjectScheduleContextVO;
import rap.api.object.relation.model.HasSubWBSItemsVO;
import rap.api.object.relation.model.WBSDependencyVO;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;
import rap.application.workflow.model.ActivitySimpleVO;
import rap.application.workflow.model.ReassignVO;
import rap.application.workflow.util.WBSUtil;
import rap.application.workflow.util.WBSWorkflowServiceUtil;


/**
 * <pre>
 * Class : WBSServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author heonhyung.lee
 */
@Service("wbsService")
public class WBSServiceImpl implements WBSService{
    static final Logger LOGGER = LoggerFactory.getLogger(WBSServiceImpl.class);       
    /**
     * 
     * @param projectObid
     * @return
     * @see lge.plm.project.common.service.ProjectCommonService#isExistsProjectSchedule(java.lang.String)
     */
    @Override
    public boolean isExistsProjectSchedule(String projectObid){
        return new Projects(projectObid).isExistsProjectSchedule();
    }

    /**
     * 
     * @param projectObid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#txnCreateProjectSchedule(java.lang.String)
     */
    @Override
    public ProjectScheduleVO txnCreateProjectSchedule(Projects projectDom){
       
        ProjectScheduleVO projectScheduleVO = projectDom.getProjectLatestSchedule();
        if(NullUtil.isNull(projectScheduleVO)){
            WBSTemplateMasterVO wbsTemplateMasterVo = projectDom.getLatestReleasedAppliedProjectTemplate();
            
            if(NullUtil.isNull(wbsTemplateMasterVo)) throw new ApplicationException("WBS Template does not exists, Please create WBS Template first.");
            
            WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(wbsTemplateMasterVo);
            projectScheduleVO = new ProjectScheduleVO();
            projectScheduleVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_PROJECT_SCHEDULE));
            projectScheduleVO.setTitles(wbsTemplateMaster.getNames());
            projectScheduleVO.setRevision("1");
            ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleVO);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ProjectConstants.PROJECT_OMAP_KEY_TEMPLATE_NASTER_VO, wbsTemplateMasterVo);
            map.put(ProjectConstants.PROJECT_OMAP_KEY_PROJECT_VO, projectDom.getVo());
            projectSchedule.createObject(map);
            projectScheduleVO = projectSchedule.getVo();
        }
        
        return projectScheduleVO;
    }

    /**
     * 
     * @param projectObid
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnInitialLoadFromWBSTemplates(java.lang.String, java.lang.String)
     */
    @Override
    public void txnInitialLoadFromWBSTemplates(String projectObid, String projectScheduleObid){
        Projects projectDom = new Projects(projectObid);
        Set<String> skippedPhaseSet = projectDom.getSkippedPhaseSet();
                
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        /***********************************Convert Template To WBSItems**************************************************/

        WBSTemplateMasterVO wbsTemplateMasterVo = projectScheduleDom.getWBSTemplateMaster();
        if(NullUtil.isNull(wbsTemplateMasterVo)) throw new ApplicationException("WBS Template does not exists, Please member Add first.");
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterVo);
        
        wbsTemplateMasterDom.convertTemplateToWBSItemsList(projectDom.getVo(), projectScheduleDom.getVo(),skippedPhaseSet);
        
        /*****************************************************************************************************************/
        Date currentDate = TimeServiceUtil.getTruncatedDBLocalDate();
        List<WBSItemsVO> structureList = new ArrayList<WBSItemsVO>();
        Map<String,WBSItemsVO> activityObjectDB = new HashMap<String,WBSItemsVO>();
        
        projectScheduleDom.saveSimulatedScheduleResult(currentDate, structureList, activityObjectDB);
        WBSTemplateUtil.setFirstAndLastFlag(structureList,true);
        projectScheduleDom.setParentSkipFlags(structureList,activityObjectDB);
        Set<String> attributes = new HashSet<String>();
        
        attributes.add("planDuration");attributes.add("planStartDate");attributes.add("planEndDate");attributes.add("isFirstActivity");attributes.add("isLastActivity");attributes.add("isSkipped");
        ObjectRoot.modifyObjectSetBatch(structureList, attributes);
    }

    /**
     * 
     * @param projectScheduleObid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getWBSPhaseList(java.lang.String)
     */
    @Override
    public List<WBSPhasesVO> getWBSProjectPhasesBasedOnLatest(String projectObid){
        return new Projects(projectObid).getWBSProjectPhasesBasedOnLatest(); 
    }

    /**
     * 
     * @param projectObid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getWBSItemsStructure(java.lang.String)
     */
    @Override
    public List<WBSItemsVO> getWBSItemsStructure(String projectObid){
        Projects project = new Projects(projectObid);
        return project.getWBSItemsStructureBasedOnLatest();
    }

    /**
     * 
     * @param projectObid
     * @param projectScheduleObid
     * @param forSimulation
     * @param withJobActivity
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getWBSItemsStructureForEdit(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getWBSItemsStructureForEdit(String projectObid, String projectScheduleObid, String forSimulation, String withJobActivity, String includeSkip){
        return getWBSItemsStructureForEditSub(projectObid,projectScheduleObid,forSimulation,withJobActivity,includeSkip,null);
    }
    public <T> List<T> getWBSItemsStructureForEdit(String projectObid, String projectScheduleObid, String forSimulation, String withJobActivity, String includeSkip,Map<String,ActivitySimpleVO> simpleActivityList){
        return getWBSItemsStructureForEditSub(projectObid,projectScheduleObid,forSimulation,withJobActivity,includeSkip,simpleActivityList);
    }
    private <T> List<T> getWBSItemsStructureForEditSub(String projectObid, String projectScheduleObid, String forSimulation, String withJobActivity, String includeSkip,Map<String,ActivitySimpleVO> simpleActivityList){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        Projects projectDom = new Projects(projectObid);
        List<WBSItemsVO> wbsSItems = new ArrayList<WBSItemsVO>();

        if("Y".equals(forSimulation)){
            wbsSItems = projectScheduleDom.getStructuredWBSScheduleList(true,false,simpleActivityList);
        }else{
            wbsSItems = projectScheduleDom.getStructuredWBSScheduleList(false,false,simpleActivityList);
        }
        List<WBSItemsVO> copiedWBSItemList = new ArrayList<WBSItemsVO>();
        if(!"Y".equals(includeSkip)){
            for(WBSItemsVO vo : wbsSItems){
                if("N".equals(vo.getIsSkipped())){
                    copiedWBSItemList.add(vo);
                }
            }
        }else{
            copiedWBSItemList.addAll(wbsSItems);
        }
        ProjectScheduleVO projectScheduleVO = projectScheduleDom.getPlanDate(projectDom,copiedWBSItemList);
        WBSUtil.clearDependencyInfo(copiedWBSItemList);
        List<BusinessObjectRootVO> list = new ArrayList<BusinessObjectRootVO>();
        if(withJobActivity.equals("Y")){
            StringBuffer jobSelectPatternBuf = new StringBuffer();
            String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
            if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameKor]");
            }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameChi]");
            }else{
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameEng]");
            }
            list = ObjectRoot.getRelatedObjectSet(copiedWBSItemList, ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY, GlobalConstants.FLAG_TYPE_TO,jobSelectPatternBuf.toString(),"","", true, true);
        }else{
            list.addAll(copiedWBSItemList);
        }
        list.add(projectScheduleVO);
        ProjectSchedule.setDelayDays(list);
        WBSUtil.setDisplayValues(list);
        SortUtil.sort(list, "uniqueString", false);
        return (List<T>)list;
    }
    
    
    
    /**
     * 
     * @param projectObid
     * @param projectScheduleObid
     * @param withJobActivity
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getWBSItemsStructureForView(java.lang.String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getWBSItemsStructureForView(String projectObid, String projectScheduleObid, String withJobActivity, boolean includeScheduleVO, boolean includeSkip){
        Projects projectDom = new Projects(projectObid);
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        ProjectScheduleVO projectScheduleVO = projectScheduleDom.getVo();
        List<BusinessObjectRootVO> list = new ArrayList<BusinessObjectRootVO>();
        List<WBSItemsVO> wbsItems = projectScheduleDom.getStructuredWBSScheduleList();
        List<WBSItemsVO> copiedWBSItemList = new ArrayList<WBSItemsVO>();
        if(!includeSkip){
            for(WBSItemsVO vo : wbsItems){
                if(!vo.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED) && !vo.getIsSkipped().equals("Y")){
                    copiedWBSItemList.add(vo);
                }
            }
        }else{
            copiedWBSItemList.addAll(wbsItems);
        }

        if(withJobActivity.equals("Y")){
            StringBuffer jobSelectPatternBuf = new StringBuffer();
            String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
            if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameKor]");
            }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameChi]");
            }else{
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameEng]");
            }
            list = ObjectRoot.getRelatedObjectSet(copiedWBSItemList, ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY, GlobalConstants.FLAG_TYPE_TO, jobSelectPatternBuf.toString(),"","",true, true);
        }else{
            list.addAll(copiedWBSItemList);
        }
        if(includeScheduleVO){
            projectScheduleVO = projectScheduleDom.getPlanDate(projectDom,wbsItems);
            list.add(0,projectScheduleVO);
        }
        ProjectSchedule.setDelayDays(list);
        WBSUtil.setDisplayValues(list);
        return (List<T>)list;
    }
    /**
     * 
     * @param projectScheduleObid
     * @param parentObid
     * @param wbsItemVO
     * @see lge.plm.project.wbs.service.WBSService#txnCreateWBSItem(java.lang.String, java.lang.String, lge.plm.api.object.project.model.WBSItemsVO)
     */
    @Override
    public void txnCreateWBSItem(String projectScheduleObid, String parentObid, WBSItemsVO wbsItemVO){
        txnCreateWBSItem(projectScheduleObid, parentObid, wbsItemVO, null);
    }

    /**
     * 
     * @param projectScheduleObid
     * @param parentObid
     * @param wbsItemVO
     * @param sequence
     * @see lge.plm.project.wbs.service.WBSService#txnCreateWBSItem(java.lang.String, java.lang.String, lge.plm.api.object.project.model.WBSItemsVO, java.lang.Integer)
     */
    @Override
    public void txnCreateWBSItem(String projectScheduleObid, String parentObid, WBSItemsVO wbsItemVO, Integer sequence){
        txnCreateWBSItem(projectScheduleObid, parentObid, wbsItemVO, null, null, null, sequence);
    }

    /**
     * 
     * @param projectScheduleObid
     * @param parentObid
     * @param wbsItemVO
     * @param recommendedDocumentTemplate
     * @param wbsDependency
     * @param addingPosition
     * @param targetSequence
     * @see lge.plm.project.wbs.service.WBSService#txnCreateWBSItem(java.lang.String, java.lang.String, lge.plm.api.object.project.model.WBSItemsVO, java.util.List, java.util.List, java.lang.String, java.lang.Integer)
     */
    @Override
    public void txnCreateWBSItem(String projectScheduleObid, String parentObid, WBSItemsVO wbsItemVO,
                                 List<UsersVO> activityOwnerCreateList, List<WBSDependencyVO> wbsDependency, 
                                 String addingPosition, Integer targetSequence){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectScheduleObid", projectScheduleObid);
        map.put("parentObid", parentObid);
        map.put("wbsDependency", wbsDependency);
        map.put("addingPosition", addingPosition);
        map.put("targetSequences", targetSequence);
        
        WBSItems parentWBSItems = new WBSItems(parentObid);
        WBSItemsVO parentWBSItemsVO = parentWBSItems.getVo();
        
        wbsItemVO.setProjectName(parentWBSItemsVO.getProjectName());
        wbsItemVO.setTemplateName(ProjectConstants.NONE);
        wbsItemVO.setPhaseName(parentWBSItemsVO.getPhaseName());
        wbsItemVO.setPhaseTitles(parentWBSItemsVO.getPhaseTitles());
        wbsItemVO.setPhaseNameSystem(parentWBSItemsVO.getPhaseNameSystem());
        wbsItemVO.setPhaseTitlesSystem(parentWBSItemsVO.getPhaseTitlesSystem());
        
        WBSItems wbsItems = DomUtil.toDom(wbsItemVO);
        wbsItems.setDefault(true);
        wbsItems.createObject(map);
        if(wbsItemVO instanceof WBSActivitiesVO){
            ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
            ProjectsVO projectsVO = projectScheduleDom.getRelatedProject();
            Projects projectsDom = new Projects(projectsVO);
            
            WBSActivities wbsActivities = new WBSActivities((WBSActivitiesVO)wbsItemVO);
            wbsActivities.updateProjectRole(projectsVO.getObid(), (String)wbsItemVO.getOutData().get("roleCodeList"));
            wbsActivities.updateProjectPerson(projectsDom, projectScheduleDom,activityOwnerCreateList, null, null);
            txnUpdateWBSDependency(projectScheduleDom.getVo(), wbsItemVO.getObid(), wbsDependency, null, null);
        }
    }

    /**
     * 
     * @param wbsItemVO
     * @param documentCreateList
     * @param documentUpdateList
     * @param documentDeleteList
     * @param dependencyCreateList
     * @param dependencyUpdateList
     * @param dependencyDeleteList
     * @see lge.plm.project.wbs.service.WBSService#txnUpdateWBSItem(java.lang.String, lge.plm.api.object.project.model.WBSItemsVO, java.util.List, java.util.List, java.util.List)
     */
    @Override
    public void txnUpdateWBSItem(String projectScheduleObid, WBSItemsVO wbsItemVO, 
                                 List<UsersVO> activityOwnerCreateList, List<UsersVO> activityOwnerUpdateList, List<UsersVO> activityOwnerDeleteList,
                                 List<WBSDependencyVO> dependencyCreateList, List<WBSDependencyVO> dependencyUpdateList, List<WBSDependencyVO> dependencyDeleteList){
        DomUtil.toDom(wbsItemVO).modifyObject();

        ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleObid);
        if(wbsItemVO instanceof WBSActivitiesVO){
            ProjectsVO projectsVO = projectSchedule.getRelatedProject();
            WBSActivitiesVO wbsActivitiesVO = (WBSActivitiesVO)wbsItemVO;
            tnxUpdateProjectRole(projectsVO.getObid(), projectScheduleObid, wbsActivitiesVO, (String)wbsActivitiesVO.getOutData().get("roleCodeList"));
            txnUpdateActivityOwner(projectsVO.getObid(), projectScheduleObid, wbsActivitiesVO, activityOwnerCreateList, activityOwnerUpdateList, activityOwnerDeleteList);
            txnUpdateWBSDependency(projectSchedule.getVo(), wbsItemVO.getObid(), dependencyCreateList, dependencyUpdateList, dependencyDeleteList);
        }
    }
    
    /**
     * 
     * @param projectsVO
     * @param wbsActivitiesVO
     * @param wbsObid
     * @param roleCodeList
     * @see lge.plm.project.wbs.service.WBSService#updateProjectRole(java.lang.String, java.lang.String, lge.plm.api.object.project.model.WBSActivitiesVO, java.lang.String)
     */
    @Override
    public void tnxUpdateProjectRole(String projectObid,String projectScheduleObid, WBSActivitiesVO wbsActivitiesVO, String roleCodeList){
        WBSActivities wbsActivities = new WBSActivities(wbsActivitiesVO);        
        wbsActivities.updateProjectRole(projectObid, roleCodeList);
    }

    /**
     * 
     * @param projectsVO
     * @param wbsActivitiesVO
     * @param activityOwnerCreateList
     * @param activityOwnerDeleteList
     * @see lge.plm.project.wbs.service.WBSService#txnUpdateActivityOwner(java.lang.String, java.lang.String, lge.plm.api.object.project.model.WBSActivitiesVO, java.util.List, java.util.List)
     */
    @Override
    public void txnUpdateActivityOwner(String projectObid, String projectScheduleObid, WBSActivitiesVO wbsActivitiesVO, List<UsersVO> activityOwnerCreateList, List<UsersVO> activityOwnerUpdateList, List<UsersVO> activityOwnerDeleteList){
        WBSActivities wbsActivities = new WBSActivities(wbsActivitiesVO);
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        Projects projectDom = new Projects(projectObid);
        wbsActivities.updateProjectPerson(projectDom, projectScheduleDom,activityOwnerCreateList, activityOwnerUpdateList, activityOwnerDeleteList);
    }
    
    /**
     * 
     * @param wbsObid
     * @param dependencyCreateList
     * @param dependencyUpdateList
     * @param dependencyDeleteList
     * @see lge.plm.project.wbs.service.WBSService#txnUpdateTemplateDependency(java.lang.String, java.util.List, java.util.List, java.util.List)
     */
    @Override
    public void txnUpdateWBSDependency(ProjectScheduleVO projectScheduleVO, String wbsObid, List<WBSDependencyVO> dependencyCreateList,
            List<WBSDependencyVO> dependencyUpdateList, List<WBSDependencyVO> dependencyDeleteList){
        
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.contextBizObject, projectScheduleVO);
        
        if(!NullUtil.isNone(dependencyCreateList)){
            for(WBSDependencyVO wbsDependencyVO : dependencyCreateList){
                wbsDependencyVO.setFromObid(wbsObid);
                new WBSDependency(wbsDependencyVO).createObject();
            }
        }
        
        if(!NullUtil.isNone(dependencyUpdateList)){
            for(WBSDependencyVO wbsDependencyVO : dependencyUpdateList){
                new WBSDependency(wbsDependencyVO).modifyObject();
            }
        }
               
        if(!NullUtil.isNone(dependencyDeleteList)){
            for(WBSDependencyVO wbsDependencyVO : dependencyDeleteList){
                if("Y".equals(wbsDependencyVO.getIsMandantory())){
                    throw new ApplicationException("dependency can not be deleted.");
                }
                new WBSDependency(wbsDependencyVO).deleteObject();
            }
        }
    }

    /**
     * 
     * @param projectScheduleObid
     * @param wbsActivitiesVO
     * @see lge.plm.project.wbs.service.WBSService#txnEditInstruction(java.lang.String, lge.plm.api.object.project.model.WBSActivitiesVO)
     */
    @Override
    public void txnEditInstruction(String projectScheduleObid, WBSActivitiesVO wbsActivitiesVO){
        Set<String> attribute = new HashSet<String>();
        attribute.add("instruction");
        ObjectRoot.modifyObjectSetBatch(wbsActivitiesVO, attribute);
    }

    
    /**
     * 
     * @param obid
     * @see lge.plm.project.wbs.service.WBSService#txnDeleteWBSItem(java.lang.String)
     */
    @Override
    public void txnDeleteWBSItem(String projectScheduleObid, String obidList){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        String[] wbsItemObidArray = obidList.split(",");
        for(int i = 0; i < wbsItemObidArray.length; i++){
            String obid = wbsItemObidArray[i];
            BusinessObjectRoot bizRootDom = new BusinessObjectRoot(obid,false);
            if(bizRootDom.isKindOf(ApplicationSchemaConstants.BIZCLASS_WBSITEMS)){
                ThreadLocalUtil.set(ThreadLocalUtil.KEY.contextBizObject, projectScheduleDom.getVo());
                WBSItems wbsItemsDom = new WBSItems(obid);
                wbsItemsDom.deleteWBSItems(projectScheduleDom.getVo());
            }else if(bizRootDom.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY)){
                WBSJobActivity wbsJobActivityDom = new WBSJobActivity(obid);
                WBSActivitiesVO wbsActivityVO = wbsJobActivityDom.getWBSActivity(projectScheduleObid);
                if(wbsJobActivityDom.isConnectedOtherActivity(wbsActivityVO.getObid())){
                    AllocatedToMemberVO allocatedToMemberVO = wbsJobActivityDom.getAllocatedToMember(wbsActivityVO.getObid());
                    AllocatedToMember AllocatedToMemberDOM = new AllocatedToMember(allocatedToMemberVO);
                    AllocatedToMemberDOM.deleteObject();
                }else{
                    wbsJobActivityDom.deleteObject();
                }
            }else{
                throw new ApplicationException(bizRootDom.getClassName() + " is not supported.");
            }
        }
    }
    /**
     * 
     * @param activityObid
     * @param wbsJobActivityVO
     * @param addingPosition
     * @param targetSequence
     * @see lge.plm.project.wbs.service.WBSService#txnCreateJobActivity(java.lang.String, lge.plm.api.object.project.model.WBSJobActivityVO, java.lang.String, java.lang.Integer)
     */
    @Override
    public void txnCreateJobActivity(String projectScheduleObid, String activityObid, WBSJobActivityVO wbsJobActivityVO, String addingPosition, Integer targetSequence){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("activityObid", activityObid);
        map.put("projectScheduleObid", projectScheduleObid);
        
        WBSJobActivity wbsJobActivity = new WBSJobActivity(wbsJobActivityVO);
        wbsJobActivity.setDefault(true);
        wbsJobActivity.createObject(map);
    }

    /**
     * 
     * @param wbsJobActivityVO
     * @see lge.plm.project.wbs.service.WBSService#txnUpdateJobActivity(lge.plm.api.object.project.model.WBSJobActivityVO)
     */
    @Override
    public void txnUpdateJobActivity(String projectScheduleObid, WBSJobActivityVO wbsJobActivityVO){
        new WBSJobActivity(wbsJobActivityVO).modifyObject();
    }

    @Override
    public void txnUpdateWeeklyProgress(String yearWeek, List<WBSItemsVO> activityList, List<WBSJobActivityVO> jobActivityList){
        if(!NullUtil.isNone(activityList)){
            for(WBSItemsVO itemVO : activityList){
                WBSItems dom = (WBSItems)DomUtil.toDom(itemVO);
                dom.modifyIssueContents(itemVO.getIssueContents());
            }
        }
        
        if(!NullUtil.isNone(jobActivityList)){
            for(WBSJobActivityVO jobActVO : jobActivityList){
                WBSJobActivity dom = (WBSJobActivity)DomUtil.toDom(jobActVO.getObid());
                dom.updateProgress(yearWeek, jobActVO.getDescriptions(), jobActVO.getProgress());
            }
        }
    }
    @Override
    public void txnUpdateProjectMpDate(List<ProjectsVO> projectList){
        for(ProjectsVO vo : projectList){
            DomUtil.toDom(vo).modifyObject();
        }
    }
    /**
     * 
     * @param obid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#retrieveWBSDependency(java.lang.String)
     */
    @Override
    public List<WBSDependencyVO> getWBSDependencyList(String obidContext, String obid){
        return new WBSItems(obid).getWBSDependencyList(obidContext);
    }

    /**
     * 
     * @param projectScheduleObid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#retrieveDocument(java.lang.String)
     */
    @Override
    public List<BusinessRelationObjectVO> retrieveDocument(String projectScheduleObid){
        // TODO Auto-generated method stub
        return null;
    }

    public List<CheckboxItemVO> retrieveWBSItemsCheckboxOptionGroup(String obid, String obidContext, UserSession userSession, String dummy ){
        List<CheckboxItemVO> resultList = new ArrayList<CheckboxItemVO>();
        CheckboxItemVO vo = new CheckboxItemVO();
        vo.setObjectName("Milestone");
        vo.setObjectLabel("Milestone");
        vo.setObjectValue("Milestone");
        vo.setObjectChecked(false);
        resultList.add ( vo );
        
        vo = new CheckboxItemVO();
        vo.setObjectName("All Activity");
        vo.setObjectLabel("All Activity");
        vo.setObjectValue("All Activity");
        vo.setObjectChecked(false);
        resultList.add ( vo );

        vo = new CheckboxItemVO();
        vo.setObjectName("Group View");
        vo.setObjectLabel("Group View");
        vo.setObjectValue("Group View");
        vo.setObjectChecked(false);
        resultList.add(vo);
        return resultList;
    }
    /**
     * 
     * 
     * @param obid
     * @param obidContext
     * @param userSession
     * @param dummy
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getSimulationOptionGroup(java.lang.String, java.lang.String, lgcns.rnd.application.login.model.UserSession, java.lang.String)
     */
    @Override
    public List<CheckboxItemVO> getSimulationOptionGroup(String obid, String obidContext, UserSession userSession, String dummy ){
        List<CheckboxItemVO> resultList = new ArrayList<CheckboxItemVO>();
        CheckboxItemVO vo = new CheckboxItemVO();
        vo.setObjectName("For Simulation");
        vo.setObjectLabel("For Simulation");
        vo.setObjectValue("For Simulation");
        vo.setObjectChecked(false);
        resultList.add ( vo );
        return resultList;
    }
    /**
     * 
     *
     * @param obid
     * @param obidContext
     * @param userSession
     * @param dummy
     * @return
     */
    public List<CheckboxItemVO> retrieveWBSItemsIncludeDocExcelCheckbox(String obid, String obidContext, UserSession userSession , String dummy ){
        List<CheckboxItemVO> resultList = new ArrayList<CheckboxItemVO>();
        CheckboxItemVO vo = new CheckboxItemVO();
        vo.setObjectName("Include Doc");
        vo.setObjectLabel("Include Doc");
        vo.setObjectValue("Include Doc");
        vo.setObjectChecked(false);
        resultList.add ( vo );
        return resultList;
    }
    /**
     * 
     * 
     * @param projectScheduleObid
     * @param wbsActivityList
     * @return
     * @see lge.plm.project.wbs.service.WBSService#txnSaveActivityManually(java.lang.String, java.util.List)
     */
    @Override
    public List<WBSItemsVO> txnSaveActivityManually(String projectObid, String projectScheduleObid, ProjectScheduleVO projectScheduleVO, List<BusinessObjectRootVO> wbsItemsList){
        List<WBSItemsVO> scheduleErrorActivityList = new ArrayList<WBSItemsVO>();
        List<WBSItemsVO> wbsActivityList = getFilterredLeafActivityList(wbsItemsList);
        Date currentDate = TimeServiceUtil.getTruncatedDBLocalDate();
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        scheduleErrorActivityList = projectScheduleDom.getSkipInfoErrorList(wbsActivityList);
        if(!NullUtil.isNone(scheduleErrorActivityList)){return scheduleErrorActivityList;}
        List<WBSItemsVO> structureList = new ArrayList<WBSItemsVO>();
        Map<String,WBSItemsVO> activityObjectDB = projectScheduleDom.getWBSDB(structureList);
        Projects projectDom = (Projects)DomUtil.toDom(projectObid);
        refreshDBWithEditValue(activityObjectDB,wbsActivityList, currentDate);
        setStartActivityPlanStartDate(projectDom, projectScheduleDom, projectScheduleVO, activityObjectDB, true);
        scheduleErrorActivityList = projectScheduleDom.saveActivityManually(projectDom, structureList,activityObjectDB, currentDate);
        return scheduleErrorActivityList;
    }
    @Override
    public List<WBSItemsVO> txnSaveActivityManually(String projectObid, 
    		String projectScheduleObid, 
    		ProjectScheduleVO projectScheduleVO, 
    		List<BusinessObjectRootVO> wbsItemsList, 
    		List<ActivitySimpleVO> simpleWBSItems){
        
        Map<String, ActivitySimpleVO> simpleWBSItemsMap = this.convertSimpleActivityListToMap(simpleWBSItems);
        Map<String,WBSItemsVO> pureWBSItemsMap = new  HashMap<String,WBSItemsVO>();
        List<WBSItemsVO> pureWBSItemsList = this.filterWBSItemsList(wbsItemsList,pureWBSItemsMap);
        List<WBSItemsVO> notDisplayedList = this.getNotDisplayedList(simpleWBSItemsMap,pureWBSItemsMap);
        String ddd = "";
        mergeWBSItems(pureWBSItemsMap,pureWBSItemsList,notDisplayedList);
        List<WBSItemsVO> scheduleErrorActivityList = new ArrayList<WBSItemsVO>();
        this.modifyWEBSimulatedSchedule(pureWBSItemsList,simpleWBSItemsMap);
        return scheduleErrorActivityList;
    }
    
    private void modifyWEBSimulatedSchedule(List<WBSItemsVO> pureWBSItemsList,Map<String, ActivitySimpleVO> simpleWBSItemsMap){
        List<WBSItemsVO> updateWBSItemsList = new ArrayList<WBSItemsVO>();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        for(WBSItemsVO vo : pureWBSItemsList){
            ActivitySimpleVO simpleVO = simpleWBSItemsMap.get(vo.getObid());
            if(!NullUtil.isNull(simpleVO)){
                vo.setPlanDuration(simpleVO.getDuration());
                
                //vo.setPlanStartDate(df.format(simpleVO.getEarlyStartDate()));
                //vo.setPlanEndDate(df.format(simpleVO.getEarlyFinishDate()));
                vo.setPlanStartDate(simpleVO.getEarlyStartDate());
                vo.setPlanEndDate(simpleVO.getEarlyFinishDate());
                vo.setIsCriticalPathItem(simpleVO.getIsCriticalPathItem());
                updateWBSItemsList.add(vo);
            }
        }
        Set<String> attributes = new HashSet<String>();
        attributes.add("planDuration");attributes.add("planStartDate");attributes.add("planEndDate");//attributes.add("isSkipped");
        ObjectRoot.modifyObjectSetBatch(updateWBSItemsList, attributes);
    }
    
    private List<WBSItemsVO> filterWBSItemsList(List<BusinessObjectRootVO> wbsItemsList,Map<String,WBSItemsVO> pureWBSItemsMap){
        List<WBSItemsVO> list = new ArrayList<WBSItemsVO>();
        for(BusinessObjectRootVO vo : wbsItemsList){
            //if(ObjectRoot.isKindOf(vo.getClassName(), ApplicationSchemaConstants.BIZCLASS_WBSITEMS)){
            if(vo instanceof WBSItemsVO){
                list.add((WBSItemsVO)vo);
                pureWBSItemsMap.put(vo.getObid(),(WBSItemsVO)vo);
            }
        }
        return list;
    }
    private List<WBSItemsVO> getNotDisplayedList(Map<String, ActivitySimpleVO> simpleWBSItemsMap, Map<String,WBSItemsVO> pureWBSItemsMap){
        List<WBSItemsVO> list  = new ArrayList<WBSItemsVO>();
        Set<String> obidSet = new HashSet<String>();
        for(String key : simpleWBSItemsMap.keySet()){
            if(NullUtil.isNull(pureWBSItemsMap.get(key))){
                obidSet.add(key);
            }
        }
        if(obidSet.size() > 0){
            StringBuffer wherePattern = new StringBuffer();
            StringBuffer paramPattern = new StringBuffer();
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(obidSet));
            list = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_WBSITEMS, "",  wherePattern.toString(), paramPattern.toString());
        }
        return list;        
    }
    private void mergeWBSItems(Map<String,WBSItemsVO> pureWBSItemsMap, List<WBSItemsVO> pureWBSItemsList,List<WBSItemsVO> notDisplayedList){
        for(WBSItemsVO vo : notDisplayedList){
            pureWBSItemsMap.put(vo.getObid(), vo);
            pureWBSItemsList.add(vo);
        }
    }
    private Map<String, ActivitySimpleVO> convertSimpleActivityListToMap(List<ActivitySimpleVO> simpleWBSItems){
        Map<String, ActivitySimpleVO> map = new HashMap<String, ActivitySimpleVO>();
        for(ActivitySimpleVO vo : simpleWBSItems){
            map.put(vo.getActivityObid(), vo);
        }
        return map;
    }
    
    private List<WBSItemsVO> getFilterredLeafActivityList(List<BusinessObjectRootVO> wbsActivityList){
        List<WBSItemsVO> list = new ArrayList<WBSItemsVO>();
        for(BusinessObjectRootVO vo: wbsActivityList){
            if(vo instanceof WBSRegularActivityVO){
                if(StrUtil.isEmpty((String)vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))){
                    list.add((WBSItemsVO)vo);
                }
            }
        }
        return list;
    }
    /**
     * 
     * 
     * @param projectScheduleObid
     * @param wbsItemObid
     * @see lge.plm.project.wbs.service.WBSService#txnReviseWBSItem(java.lang.String, java.lang.String)
     */
    @Override
    public void txnReviseWBSItem(String projectScheduleObid, String wbsItemObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,true);
        try{
            Map<String,Object> map = getWillBeRevisedWBSItemList(projectScheduleDom,wbsItemObid);
            getWillBeCreatedRelationObjectList(projectScheduleDom,wbsItemObid,map);
            reviseWBSItemsSub(projectScheduleDom,wbsItemObid,map);
            createRelationAllBatch(projectScheduleDom,wbsItemObid,map);
        }catch(Exception e){
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,false);
            throw e;
        }
    }
    @SuppressWarnings({ "unchecked", "unused" })
    private void createRelationAllBatch(ProjectSchedule projectScheduleDom,String wbsItemObid,Map<String,Object> map){
        List<WBSItemsVO>       targetObjectList  = (List<WBSItemsVO>)map.get(ProjectConstants.WBS_REVISE_MAP_TARGET_LIST);
        Map<String,WBSItemsVO> targetObjectMap   = (Map<String,WBSItemsVO>)map.get(ProjectConstants.WBS_REVISE_MAP_TARGET_MAP);
        List<WBSItemsVO>       revisedObjectList = (List<WBSItemsVO>)map.get(ProjectConstants.WBS_REVISE_MAP_REVISED_LIST);
        Map<String,WBSItemsVO> reisedObjectMap   = (Map<String,WBSItemsVO>)map.get(ProjectConstants.WBS_REVISE_MAP_REVISED_MAP);
        Map<String,String>     objectMappingMap  = (Map<String,String>)map.get(ProjectConstants.WBS_REVISE_MAP_OBJECT_MAPPING_MAP);

        List<ControlledByProjectScheduleContextVO> createdContextList   = (List<ControlledByProjectScheduleContextVO>)map.get(ProjectConstants.WBS_REVISE_MAP_createdContextList);
        List<ControlledByProjectScheduleContextVO> deletedContextList   = (List<ControlledByProjectScheduleContextVO>)map.get(ProjectConstants.WBS_REVISE_MAP_deletedContextList);
        
        List<HasSubWBSItemsVO>     hasSubWBSItemsList     = (List<HasSubWBSItemsVO>)map.get(ProjectConstants.WBS_REVISE_MAP_hasSubWBSItemsList);
        List<WBSDependencyVO>      wbsDependencyList      = (List<WBSDependencyVO>)map.get(ProjectConstants.WBS_REVISE_MAP_wbsDependencyList);
        List<AssignedToActivityVO> assignedToActivityList = (List<AssignedToActivityVO>)map.get(ProjectConstants.WBS_REVISE_MAP_assignedToActivityList);
        List<AllocatedToMemberVO>  allocatedToMemberList  = (List<AllocatedToMemberVO>)map.get(ProjectConstants.WBS_REVISE_MAP_allocatedToMemberList);

        List<BusinessRelationObjectVO> allList = new ArrayList<BusinessRelationObjectVO>();
        
        /*************************************************HasSubWBSItems*****************************************************/
        for(HasSubWBSItemsVO vo : hasSubWBSItemsList){
            boolean fromRevised = false;
            boolean toRevised   = false;
            HasSubWBSItemsVO copiedVO1 = (HasSubWBSItemsVO)DomUtil.copy(vo);
            if(!StrUtil.isEmpty(objectMappingMap.get(copiedVO1.getFromObid()))){
                copiedVO1.setFromObid(objectMappingMap.get(copiedVO1.getFromObid()));
                allList.add(copiedVO1);
                fromRevised = true;
            }
            HasSubWBSItemsVO copiedVO2 = (HasSubWBSItemsVO)DomUtil.copy(vo);
            if(!StrUtil.isEmpty(objectMappingMap.get(copiedVO2.getToObid()))){
                copiedVO2.setToObid(objectMappingMap.get(copiedVO2.getToObid()));
                allList.add(copiedVO2);
                toRevised = true;
            }
            if(fromRevised && toRevised){
                HasSubWBSItemsVO copiedVO3 = (HasSubWBSItemsVO)DomUtil.copy(vo);
                copiedVO3.setFromObid(objectMappingMap.get(copiedVO3.getFromObid()));
                copiedVO3.setToObid(objectMappingMap.get(copiedVO3.getToObid()));
                allList.add(copiedVO3);
            }
        }
        /*************************************************WBSDependency*****************************************************/
        for(WBSDependencyVO vo : wbsDependencyList){
            boolean fromRevised = false;
            boolean toRevised   = false;
            WBSDependencyVO copiedVO1 = (WBSDependencyVO)DomUtil.copy(vo);
            if(!StrUtil.isEmpty(objectMappingMap.get(copiedVO1.getFromObid()))){
                copiedVO1.setFromObid(objectMappingMap.get(copiedVO1.getFromObid()));
                allList.add(copiedVO1);
                fromRevised = true;
            }
            WBSDependencyVO copiedVO2 = (WBSDependencyVO)DomUtil.copy(vo);
            if(!StrUtil.isEmpty(objectMappingMap.get(copiedVO2.getToObid()))){
                copiedVO2.setToObid(objectMappingMap.get(copiedVO2.getToObid()));
                allList.add(copiedVO2);
                toRevised = true;
            }
            if(fromRevised && toRevised){
                WBSDependencyVO copiedVO3 = (WBSDependencyVO)DomUtil.copy(vo);
                copiedVO3.setFromObid(objectMappingMap.get(copiedVO3.getFromObid()));
                copiedVO3.setToObid(objectMappingMap.get(copiedVO3.getToObid()));
                allList.add(copiedVO3);
            }
        }
        /*************************************************AssignedToActivity*****************************************************/
        for(AssignedToActivityVO vo : assignedToActivityList){
            AssignedToActivityVO copiedVO = (AssignedToActivityVO)DomUtil.copy(vo);
            if(!StrUtil.isEmpty(objectMappingMap.get(copiedVO.getFromObid()))){
                copiedVO.setFromObid(objectMappingMap.get(copiedVO.getFromObid()));
                allList.add(copiedVO);
            }
        }
        /*************************************************AllocatedToMember*****************************************************/
        for(AllocatedToMemberVO vo : allocatedToMemberList){
            AllocatedToMemberVO copiedVO = (AllocatedToMemberVO)DomUtil.copy(vo);
            if(!StrUtil.isEmpty(objectMappingMap.get(copiedVO.getFromObid()))){
                copiedVO.setFromObid(objectMappingMap.get(copiedVO.getFromObid()));
                allList.add(copiedVO);
            }
        }
        allList.addAll(createdContextList);
        for(BusinessRelationObjectVO vo : allList){
            vo.setFlags(0l);
        }
        ObjectRoot.createObjectSetBatch(allList);
        ObjectRoot.deleteObjectSetBatch(deletedContextList);
        
        StringBuffer selectPattern    = new StringBuffer();
        StringBuffer wherePattern    = new StringBuffer();
        StringBuffer parameterPattern    = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, projectScheduleDom.getObid() );
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[previousObid]", GlobalConstants.OQL_OPERATOR_NOT_IN, "1" );
        
        List<WBSItemsVO> list = projectScheduleDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_WBSITEMS, 
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);

        for(WBSItemsVO vo : list){
            ControlledByProjectScheduleContext controlledByProjectScheduleContextDom = new ControlledByProjectScheduleContext((String)vo.getOutDataAttributeValue("rel_obid"));
            controlledByProjectScheduleContextDom.deleteObject();
        }
    }
    @SuppressWarnings("unchecked")
    private void reviseWBSItemsSub(ProjectSchedule projectScheduleDom,String wbsItemObid,Map<String,Object> map){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        List<WBSItemsVO> targetList = (List<WBSItemsVO>)map.get(ProjectConstants.WBS_REVISE_MAP_TARGET_LIST);
        List<WBSItemsVO> newRevisedList = new ArrayList<WBSItemsVO>();
        Map<String,String> objectMapingMap = new HashMap<String,String>();
        for(WBSItemsVO wbsItemsVO : targetList){
            WBSItems   wbsItemsDom = new WBSItems(wbsItemsVO.getObid());
            if(!wbsItemsDom.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT)){
                WBSItemsVO revisedWBSItemsVO = wbsItemsDom.reviseWBSItems(projectScheduleDom);
                newRevisedList.add(revisedWBSItemsVO);
                objectMapingMap.put(wbsItemsVO.getObid(), revisedWBSItemsVO.getObid());
                objectMapingMap.put(revisedWBSItemsVO.getObid(), wbsItemsVO.getObid());                
            }
        }
        List<ControlledByProjectScheduleContextVO> controlledByProjectScheduleContextList = new ArrayList<ControlledByProjectScheduleContextVO>();
        for(WBSItemsVO vo : newRevisedList){
            ControlledByProjectScheduleContextVO controlledByProjectScheduleContextVO = new ControlledByProjectScheduleContextVO();
            controlledByProjectScheduleContextVO.setClassName(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT);
            controlledByProjectScheduleContextVO.setFromClass(vo.getClassName());
            controlledByProjectScheduleContextVO.setFromObid(vo.getObid());
            controlledByProjectScheduleContextVO.setToClass(projectScheduleDom.getClassName());
            controlledByProjectScheduleContextVO.setToObid(projectScheduleDom.getObid());
            controlledByProjectScheduleContextList.add(controlledByProjectScheduleContextVO);
        }
        map.put(ProjectConstants.WBS_REVISE_MAP_REVISED_LIST, newRevisedList);
        map.put(ProjectConstants.WBS_REVISE_MAP_OBJECT_MAPPING_MAP, objectMapingMap);
        map.put(ProjectConstants.WBS_REVISE_MAP_createdContextList, controlledByProjectScheduleContextList);
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, projectScheduleDom.getObid() );
        List<ControlledByProjectScheduleContextVO> deletedContextList = ObjectRoot.getRelationshipSet(targetList, ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT, ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE, 
                GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        map.put(ProjectConstants.WBS_REVISE_MAP_deletedContextList, deletedContextList);
    }
    @SuppressWarnings("unchecked")
    private void getWillBeCreatedRelationObjectList(ProjectSchedule projectScheduleDom,String wbsItemObid,Map<String,Object> map){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        List<WBSItemsVO> targetList = (List<WBSItemsVO>)map.get(ProjectConstants.WBS_REVISE_MAP_TARGET_LIST);
        
        /*************************************************HasSubWBSItems*****************************************************/
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, projectScheduleDom.getObid() );
        List<HasSubWBSItemsVO> hasSubWBSItemsList = ObjectRoot.getRelationshipSet(targetList, ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, ApplicationSchemaConstants.BIZCLASS_WBSITEMS, 
                GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());

        selectPattern.setLength(0);wherePattern.setLength(0);parameterPattern.setLength(0);
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.fromObid.From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, projectScheduleDom.getObid() );
        List<HasSubWBSItemsVO> tempHasSubWBSItemsList = ObjectRoot.getRelationshipSet(targetList, ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, 
                ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_FROM, 
                selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        hasSubWBSItemsList.addAll(tempHasSubWBSItemsList);
        
        selectPattern.setLength(0);wherePattern.setLength(0);parameterPattern.setLength(0);
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, projectScheduleDom.getObid() );
        List<HasSubWBSItemsVO> temp1HasSubWBSItemsList = ObjectRoot.getRelationshipSet(targetList, ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, 
                ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE, GlobalConstants.FLAG_TYPE_FROM, 
                selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        hasSubWBSItemsList.addAll(temp1HasSubWBSItemsList);
        
        hasSubWBSItemsList = SortUtil.uniquized(hasSubWBSItemsList, "fromObid:toObid");
        
        /*************************************************WBSDependency*****************************************************/
        selectPattern.setLength(0);wherePattern.setLength(0);parameterPattern.setLength(0);
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.toObid.From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, projectScheduleDom.getObid() );
        List<WBSDependencyVO> wbsDependencyList = ObjectRoot.getRelationshipSet(targetList, ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY, 
                ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_TO, 
                selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());

        selectPattern.setLength(0);wherePattern.setLength(0);parameterPattern.setLength(0);
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "this.fromObid.From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, projectScheduleDom.getObid() );
        
        List<WBSDependencyVO> tempWBSDependencyList = ObjectRoot.getRelationshipSet(targetList, ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY, 
                ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_FROM, 
                selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        wbsDependencyList.addAll(tempWBSDependencyList);
        wbsDependencyList = SortUtil.uniquized(wbsDependencyList, "fromObid:toObid");
        
        /*************************************************AssignedToActivity*****************************************************/
        selectPattern.setLength(0);wherePattern.setLength(0);parameterPattern.setLength(0);
        List<AssignedToActivityVO> assignedToActivityList = ObjectRoot.getRelationshipSet(targetList, ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, 
                ApplicationSchemaConstants.BIZCLASS_PROJECTMEMBERS, GlobalConstants.FLAG_TYPE_TO, 
                selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        
        /*************************************************AllocatedToMember*****************************************************/
        selectPattern.setLength(0);wherePattern.setLength(0);parameterPattern.setLength(0);
        List<AssignedToActivityVO> allocatedToMemberList = ObjectRoot.getRelationshipSet(targetList, ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, 
                ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY, GlobalConstants.FLAG_TYPE_TO, 
                selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());

        map.put(ProjectConstants.WBS_REVISE_MAP_hasSubWBSItemsList, hasSubWBSItemsList);
        map.put(ProjectConstants.WBS_REVISE_MAP_wbsDependencyList, wbsDependencyList);
        map.put(ProjectConstants.WBS_REVISE_MAP_assignedToActivityList, assignedToActivityList);
        map.put(ProjectConstants.WBS_REVISE_MAP_allocatedToMemberList, allocatedToMemberList);
    }
    /**
     *  Revise되어져야 할 리스트를 Return함.
     *
     * @param projectScheduleDom
     * @param wbsItemObid
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String,Object> getWillBeRevisedWBSItemList(ProjectSchedule projectScheduleDom,String wbsItemObidList){
        
        Map<String,Object> returnMap = new HashMap<String,Object>();
        List<WBSItemsVO> list = projectScheduleDom.getWBSItemsStructureList(false,false,false);
        List<WBSItemsVO> revisedList = new ArrayList<WBSItemsVO>();
        SortUtil.sort(list, "uniqueString", false);
        List<WBSItemsVO> originaAllList = (List<WBSItemsVO>)DomUtil.copy(list);
        Map<String, WBSItemsVO> wbsdb = projectScheduleDom.getWBSDB(list);
        
        Map<String,WBSItemsVO> allMap = new HashMap<String,WBSItemsVO>();
        String[] wbsItemObidArray = wbsItemObidList.split(",");
        for(int i = 0; i < wbsItemObidArray.length; i++){
            Map<String,WBSItemsVO> map = new HashMap<String,WBSItemsVO>();
            WBSItems selectedWBSItemDom = new WBSItems(wbsItemObidArray[i]);
            projectScheduleDom.makeNextActivityList(selectedWBSItemDom.getVo(), wbsdb, map, StrUtil.convertArrayToSet(ProjectConstants.WBSITEM_REVISIBLE_STATE_SET_GENERAL));
            for(String key : map.keySet()) allMap.put(key, map.get(key));
        }
        
        for(String obid : allMap.keySet()){
            revisedList.add((WBSItemsVO)allMap.get(obid));
        }
        List<WBSItemsVO> parentList = getParentItemsList(originaAllList,revisedList);
        for(WBSItemsVO vo : parentList){
            if(!allMap.containsKey(vo.getObid()) && !StrUtil.in(vo.getStates(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED,ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT)) {
                allMap.put(vo.getObid(), vo);
            }
        }
        returnMap.put(ProjectConstants.WBS_REVISE_MAP_TARGET_MAP, allMap);
        List<WBSItemsVO> tempRevisedList = new ArrayList<WBSItemsVO>();
        for(String obid : allMap.keySet()){
            tempRevisedList.add(allMap.get(obid));
        }
        returnMap.put(ProjectConstants.WBS_REVISE_MAP_TARGET_LIST, tempRevisedList);
        return returnMap;
    }
    
    private List<WBSItemsVO> getParentItemsList(List<WBSItemsVO> allList, List<WBSItemsVO> selectedList){
        List<WBSItemsVO> parentList = new ArrayList<WBSItemsVO>();
        for(WBSItemsVO vo:selectedList){
            parentList.addAll(getParentItemsList(allList,vo));
        }
        return parentList;
    }
    private List<WBSItemsVO> getParentItemsList(List<WBSItemsVO> allList, WBSItemsVO wbsItemsVO){
        List<WBSItemsVO> parentList = new ArrayList<WBSItemsVO>();
        String uniqueString = "";
        for(WBSItemsVO vo : allList){
            if(vo.getObid().equals(wbsItemsVO.getObid())) {uniqueString = vo.getUniqueString();break;}
        }
        for(WBSItemsVO vo : allList){
            if(uniqueString.startsWith(vo.getUniqueString()) && !uniqueString.equals(vo.getUniqueString())) parentList.add(vo);
        }
        return parentList;
    }
    /**
     * 
     * @param projectScheduleObid
     * @param wbsItemObid
     * @see lge.plm.project.wbs.service.WBSService#txnReviseActivityFor2ndPerform(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void txnReviseActivityFor2ndPerform(String projectScheduleObid, String wbsItemObid){
        
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        WBSItems wbsItemsDom = new WBSItems(wbsItemObid);
        List<WBSItemsVO> targetList = wbsItemsDom.getTargetListForRePerformList(projectScheduleDom);
        
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,true);
        List<WBSItemsVO> revisedNewList = new ArrayList<WBSItemsVO>();
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put(ProjectConstants.WBS_REVISE_MAP_TARGET_LIST, targetList);
            getWillBeCreatedRelationObjectList(projectScheduleDom,wbsItemObid,map);
            reviseWBSItemsSub(projectScheduleDom,wbsItemObid,map);
            revisedNewList = (List<WBSItemsVO>)map.get(ProjectConstants.WBS_REVISE_MAP_REVISED_LIST);
            createRelationAllBatch(projectScheduleDom,wbsItemObid,map);
        }finally{
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,false);
        }
        
        Date nullDate = null;
        for(WBSItemsVO childItemsVO : revisedNewList){
            childItemsVO.setIsReDoActivity("Y");
            childItemsVO.setActualStartDate(nullDate);
            childItemsVO.setActualEndDate(nullDate);
            WBSItems evisedItemsDom = new WBSItems(childItemsVO);
            evisedItemsDom.changeToReDoActivity();
        }
        Set<String> attributes = new HashSet<String>();
        attributes.add("isReDoActivity");attributes.add("actualStartDate");attributes.add("actualEndDate");
        ObjectRoot.modifyObjectSetBatch(revisedNewList, attributes);
    }
    
    /**
     * 
     * @param projectScheduleObid
     * @param wbsItemObid
     * @see lge.plm.project.wbs.service.WBSService#txnCancelReviseWBSItem(java.lang.String, java.lang.String)
     */
    @Override
    public void txnCancelReviseWBSItem(String projectScheduleObid, String wbsItemObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        String[] obidArray = wbsItemObid.split(",");
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,true);
        try{
            for(int i = 0; i < obidArray.length; i++){
                WBSItems wbsItemsDom = new WBSItems(obidArray[i]);
                wbsItemsDom.cancelReviseWBSItem(projectScheduleDom);                
            }
        }finally{
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,false);
        }
    }
    
    /**
     * 
     * @param projectScheduleObid
     * @param wbsItemObid
     * @see lge.plm.project.wbs.service.WBSService#txnCancelReviseAllWBSItem(java.lang.String)
     */
    @Override
    public void txnCancelReviseAllWBSItem(String projectScheduleObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,true);
        try{
            List<WBSItemsVO> workingWBSItemList = projectScheduleDom.getWorkingWBSItemList();
            Set<String> strSet = new HashSet<String>();
            for(WBSItemsVO vo : workingWBSItemList){strSet.add(vo.getObid());}
            StringBuffer selectPattern = new StringBuffer();
            StringBuffer wherePattern = new StringBuffer();
            StringBuffer paramPattern = new StringBuffer();
            StringUtil.constructWherePattern(wherePattern, paramPattern, "!To["+ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER+"].fromObid",GlobalConstants.OQL_OPERATOR_NOT_IN, StrUtil.convertSet2Str(strSet));
            List<WBSJobActivityVO> wbsJobActivityList = ObjectRoot.getRelatedObjectSet(workingWBSItemList, ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY,
                                                                                       GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), paramPattern.toString());
            for(WBSJobActivityVO vo : wbsJobActivityList){
                new WBSJobActivity(vo).deleteObject();
            }
            List<ControlledByProjectScheduleContextVO> contextVOList = new ArrayList<ControlledByProjectScheduleContextVO>();
            ControlledByProjectScheduleContextVO controlledByProjectScheduleContextVO = null;
            for(WBSItemsVO vo : workingWBSItemList){
                if(!vo.getNextObid().equals("1")){
                    controlledByProjectScheduleContextVO = new ControlledByProjectScheduleContextVO();
                    controlledByProjectScheduleContextVO.setClassName(ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT);
                    controlledByProjectScheduleContextVO.setToObid(projectScheduleObid);
                    controlledByProjectScheduleContextVO.setToClass(ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE);
                    controlledByProjectScheduleContextVO.setFromObid(vo.getNextObid());
                    controlledByProjectScheduleContextVO.setFromClass(vo.getClassName());
                    contextVOList.add(controlledByProjectScheduleContextVO);
                }
                new WBSItems(vo).deleteObject();
            }
            if(!NullUtil.isNone(contextVOList)){
                ObjectRoot.createObjectSetBatch(contextVOList);
            }
        }finally{
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,false);
        }
    }
    /**
     * 
     * @param wbsActivityList
     * @see lge.plm.project.wbs.service.WBSService#txnSaveSimulationResult(java.util.List)
     */
    @Override
    public List<WBSItemsVO> txnSaveSimulationResult(String projectObid, ProjectScheduleVO projectScheduleVO, List<WBSItemsVO> wbsActivityList){
        List<WBSItemsVO> errorList = new ArrayList<WBSItemsVO>();
        Date currentDate = TimeServiceUtil.getTruncatedDBLocalDate();
        Projects projectsDom = new Projects(projectObid);
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleVO.getObid());
        errorList = projectScheduleDom.getSkipInfoErrorList(wbsActivityList);
        if(!NullUtil.isNone(errorList)){return errorList;}
        List<WBSItemsVO> structureList = new ArrayList<WBSItemsVO>();
        Map<String,WBSItemsVO> activityObjectDB = projectScheduleDom.getWBSDB(structureList);
        refreshDBWithEditValue(activityObjectDB,wbsActivityList, currentDate);
        projectScheduleDom.setParentSkipFlags(structureList,activityObjectDB);
        setStartActivityPlanStartDate(projectsDom, projectScheduleDom, projectScheduleVO, activityObjectDB, false);
        projectScheduleDom.saveSimulatedScheduleResult(currentDate,structureList,activityObjectDB);
        return errorList;
    }
    private WBSItemsVO setStartActivityPlanStartDate(Projects projectsDom, ProjectSchedule projectScheduleDom, ProjectScheduleVO projectScheduleVO, Map<String,WBSItemsVO> activityObjectDB, boolean onlySkippedStartActivity){
        if(projectsDom.getStates().equals(ApplicationSchemaConstants.STATE_PROJECT_STARTUP) 
                && projectScheduleVO != null ){
            List<WBSItemsVO> startActivityVOList = projectScheduleDom.findFirstActivity(activityObjectDB);
            if(NullUtil.isNone(startActivityVOList) || startActivityVOList.size() > 1) throw new FoundationException("Process Start point is duplocated. Please contact System Administrator.");
            WBSItemsVO startActivityVO = startActivityVOList.get(0);
            if(!onlySkippedStartActivity || "Y".equals(startActivityVO.getIsSkipped())){
                startActivityVO.setPlanStartDate(projectScheduleVO.getPlanStartDate());
                if("Y".equals(startActivityVO.getIsSkipped())){
                    startActivityVO.setPlanEndDate(projectScheduleVO.getPlanStartDate());
                }
            }
            return startActivityVO;
        }
        return null;
    }
    /**
     * 화면에서 입력되어진 정보를 반영하고 Skip여부 및 Miliesstone에 대해서는 Duration을 재설정함.
     *
     * @param activityObjectDB
     * @param editedWBSActivityList
     */
    private void refreshDBWithEditValue(Map<String,WBSItemsVO> activityObjectDB, List<WBSItemsVO> editedWBSActivityList, Date currentDate){
        for(WBSItemsVO vo : editedWBSActivityList){
            activityObjectDB.get(vo.getObid()).setPlanStartDate(vo.getPlanStartDate());
            activityObjectDB.get(vo.getObid()).setPlanEndDate(vo.getPlanEndDate());
            activityObjectDB.get(vo.getObid()).setIsSkipped(vo.getIsSkipped());
            activityObjectDB.get(vo.getObid()).setPlanDuration(vo.getPlanDuration());
            if(ProjectSchedule.isLeaf(activityObjectDB.get(vo.getObid()))){
                if("Y".equals(activityObjectDB.get(vo.getObid()).getIsSkipped())){
                    activityObjectDB.get(vo.getObid()).setPlanDuration(0);
                }else{
                    if("Y".equals(activityObjectDB.get(vo.getObid()).getIsMilestone())){
                        activityObjectDB.get(vo.getObid()).setPlanDuration(1);
                    }
                }
            }
        }
        
        for(String key : activityObjectDB.keySet()){
            if(activityObjectDB.get(key).getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT) 
                    && "Y".equals(activityObjectDB.get(key).getIsSkipped()) && ProjectSchedule.isLeaf(activityObjectDB.get(key))){
                
                activityObjectDB.get(key).setPlanStartDate(currentDate);
                activityObjectDB.get(key).setPlanEndDate(currentDate);
            }
        }
    }

    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnStartScheduleExcution(java.lang.String)
     */
    @Override
    public void txnStartScheduleExcution(String projectScheduleObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        
        Map<String,WBSItemsVO> activityObjectDB = projectScheduleDom.getWBSDB();

        Date actionDate = TimeServiceUtil.getDBLocalTime();
        projectScheduleDom.startProjectExecution(activityObjectDB,actionDate);
    }

    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnRefreshProjectMember2Assigned(java.lang.String)
     */
    @Override
    public void txnRefreshProjectMember2Assigned(String projectScheduleObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        projectScheduleDom.refreshActivityAssigned();
    }

    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnRefreshSkipInfoAsGrade(java.lang.String)
     */
    @Override
    public void txnRefreshSkipInfoAsGrade(String projectScheduleObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        ProjectsVO relatedProjectVO = projectScheduleDom.getRelatedProject();
        Projects projectDom = DomUtil.toDom(relatedProjectVO);
        RevisedProjects revisedProjectDom = projectDom.getRevisedProjects();
        String toBeGrade = revisedProjectDom.getVo().getProjectGradeCode();
        WBSTemplateMasterVO wbsTemplateMasterVo = projectScheduleDom.getWBSTemplateMaster();
        if(NullUtil.isNull(wbsTemplateMasterVo)){
            List<WBSTemplateMasterVO> templateMasterList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER, relatedProjectVO.getWbsTempleteCode());
            SortUtil.sort(templateMasterList, "created", true);
            for(WBSTemplateMasterVO vo : templateMasterList){
                if("Y".equals(vo.getIsPublished())){
                    wbsTemplateMasterVo = vo;
                }
            }
            if(NullUtil.isNull(wbsTemplateMasterVo)){
                throw new ApplicationException("WBS Template does not exist, Please, contact system administrator.");
            }
        }
        projectScheduleDom.refreshSkipInfoAsGrade(wbsTemplateMasterVo.getObid(), toBeGrade);
    }
    
    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnAssignActivityOwnerToPL(java.lang.String)
     */
    @Override
    public void txnAssignActivityOwnerToPL(String projectScheduleObid){
        ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleObid);
        List<WBSItemsVO> notAssignedActivities = projectSchedule.getNotAssignedActivities();
        if(!NullUtil.isNone(notAssignedActivities)){
            ProjectsVO projectsVO = projectSchedule.getRelatedProject();
            Projects project = new Projects(projectsVO);    
            ProjectPersonVO projectLeader = project.getProjectLeader();
            
            List<AssignedToActivityVO> assignedToActivityList = new ArrayList<AssignedToActivityVO>();
            for(WBSItemsVO wbsItemsVO : notAssignedActivities){
                AssignedToActivityVO assignedToActivityVO = new AssignedToActivityVO();
                assignedToActivityVO.setClassName(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY);
                assignedToActivityVO.setFromClass(wbsItemsVO.getClassName());
                assignedToActivityVO.setFromObid(wbsItemsVO.getObid());
                assignedToActivityVO.setToClass(projectLeader.getClassName());
                assignedToActivityVO.setToObid(projectLeader.getObid());
                assignedToActivityVO.setIsMainMember("N");
                assignedToActivityVO.setActionUserId("None");
                assignedToActivityVO.setIsMainMember("Y");
                assignedToActivityList.add(assignedToActivityVO);
            }
            if(!NullUtil.isNone(assignedToActivityList)){
                ObjectRoot.createObjectSetBatch(assignedToActivityList);
                projectSchedule.refreshActivityOwnerListAll();
            }
        }
    }
    
    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnValidateSchedule(java.lang.String)
     */
    @Override
    public void txnValidateSchedule(String projectScheduleObid){
        throw new ApplicationException("Not Implemented.");
    }

    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnRequestScheduleApproval(java.lang.String)
     */
    @Override
    public void txnRequestScheduleApproval(String projectScheduleObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        projectScheduleDom.startApprovalProcessForScheduleComplete();
    }

    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnCompleteScheduleApproval(java.lang.String)
     */
    @Override
    public void txnCompleteScheduleApproval(String projectScheduleObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,true);
        try{
            projectScheduleDom.completeProjectWBSSchedule();  
        }finally{
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,false);
        }
        Date actionDate = TimeServiceUtil.getDBLocalTime();
        Map<String,WBSItemsVO> activityObjectDB = projectScheduleDom.getWBSDB();
        projectScheduleDom.startProjectExecution(activityObjectDB,actionDate);
    }
    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnReviseSchedule(java.lang.String)
     */
    @Override
    public ProjectScheduleVO txnReviseSchedule(String projectScheduleObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        ProjectsVO projectVO = projectScheduleDom.getRelatedProject();
        if(NullUtil.isNull(projectVO)) throw new FoundationException("Project Not found!");
        ProjectScheduleVO revisedProjectScheduleVO = projectScheduleDom.reviseSchedule(projectVO,new HashMap<String,Object>());
        return revisedProjectScheduleVO;
    }

    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnRejectScheduleApproval(java.lang.String)
     */
    @Override
    public void txnRejectScheduleApproval(String projectScheduleObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        projectScheduleDom.rejectApprovalProcess();
    }
    /**
     * 
     * @param projectScheduleObid
     * @param wbsActivityObid
     * @see lge.plm.project.wbs.service.WBSService#txnCompleteWBSActivity(java.lang.String, java.lang.String)
     */
    @Override
    public void txnForceCompleteWBSActivityFromWBS(String projectScheduleObid, String wbsActivityObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        WBSItems wbsItemsDom = new WBSItems(wbsActivityObid);
        wbsItemsDom.forceCompleteWBSActivityFromWBS(projectScheduleDom.getVo(), "If threre is issue, please let me know that." , false);
    }
    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnCancelReviseSchedule(java.lang.String)
     */
    @Override
    public void txnCancelReviseSchedule(String projectScheduleObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        projectScheduleDom.cancelRevise();
    }

    /**
     * 
     * @param projectScheduleObid
     * @param userId
     * @see lge.plm.project.wbs.service.WBSService#txnChangeOwnerSchedule(java.lang.String, java.lang.String)
     */
    @Override
    public void txnChangeOwnerSchedule(String projectScheduleObid, String userId){
        ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleObid);
        projectSchedule.changeOwner(userId);
    }
    
    /**
     * 
     * @param projectScheduleObid
     * @param ganttActivityList
     * @param linkList
     * @see lge.plm.project.wbs.service.WBSService#getWBSTemplateListForGantt(java.lang.String, java.util.List, java.util.List)
     */
    @Override
    public void getWBSTemplateListForGantt(String projectScheduleObid, List<DHTMLGanttWBSActivityVO> ganttActivityList,List<DHTMLGanttLinkVO> linkList, GANTT_VIEWING_TYPE viewingType){
       
        ProjectSchedule  projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        ProjectsVO projectVO = projectScheduleDom.getRelatedProject();
        Projects projectDom = new Projects(projectVO);
        
        List<WBSItemsVO> list = getWBSItemsStructureForView(projectDom.getObid(), projectScheduleObid, "N",false, true);
        List<WBSDependencyVO> dependencyList = ProjectSchedule.getWBSDependencyList(projectScheduleObid, list);
        SortUtil.sort(list, "uniqueString", false);
        int sortOrder = 1;
        for(WBSItemsVO wbsItemsVO : list ){
            wbsItemsVO.setOutDataAttributeValue(ProjectConstants.GANTT_SORT_ORDER_KEY, sortOrder++);
            Date forcastedEndDate = (Date)wbsItemsVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate);
            if(wbsItemsVO.getActualEndDate() == null) {
                wbsItemsVO.setActualEndDate(forcastedEndDate);
            }
            if(wbsItemsVO.getActualStartDate() == null) {
                Integer duration = wbsItemsVO.getPlanDuration();
                Date startDate = TimeServiceUtil.addDays(wbsItemsVO.getActualEndDate(), -1*duration);
                wbsItemsVO.setActualStartDate(startDate);
            }
            DHTMLGanttWBSActivityVO addedVO = WBSTemplateUtil.convertToDHTMLGanttActivityVO(wbsItemsVO,list,viewingType);
            ganttActivityList.add(addedVO);
        }
        for(WBSDependencyVO dependencyVO : dependencyList){
            String dependencyType = dependencyVO.getDependencyType();
            linkList.add(new DHTMLGanttLinkVO(dependencyVO.getObid(), dependencyVO.getToObid(), dependencyVO.getFromObid(), dependencyType));
        }
    }
    

    /**
     * 
     * @param obid
     * @param obidContext
     * @param userSession
     * @param dummy
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getJobActivityFilter(java.lang.String, java.lang.String, lgcns.rnd.application.login.model.UserSession, java.lang.String)
     */
    @Override
    public List<CheckboxItemVO> getJobActivityFilter(String obid, String obidContext, UserSession userSession, String dummy){
        List<CheckboxItemVO> resultList = new ArrayList<CheckboxItemVO>();
        CheckboxItemVO vo = new CheckboxItemVO();
        vo.setObjectName("Job Activity");
        vo.setObjectLabel("Job Activity");
        vo.setObjectValue("Job Activity");
        vo.setObjectChecked(false);
        resultList.add ( vo );
        return resultList;
    }

    /**
     * 
     * @param obid
     * @param obidContext
     * @param userSession
     * @param dummy
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getSkipActivityFilter(java.lang.String, java.lang.String, lgcns.rnd.application.login.model.UserSession, java.lang.String)
     */
    @Override
    public List<CheckboxItemVO> getSkipActivityFilter(String obid, String obidContext, UserSession userSession, String dummy){
        List<CheckboxItemVO> resultList = new ArrayList<CheckboxItemVO>();
        CheckboxItemVO vo = new CheckboxItemVO();
        vo.setObjectName("Skipped Activity");
        vo.setObjectLabel("Skipped Activity");
        vo.setObjectValue("Skipped Activity");
        vo.setObjectChecked(false);
        resultList.add ( vo );
        return resultList;
    }
    
    /**
     * 
     * @param projectScheduleObid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getWBSScheduleRevisionCombo(java.lang.String)
     */
    @Override
    public List<HashMap<String, String>> getWBSScheduleRevisionCombo(String projectScheduleObid){
        ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleObid);
        List<ProjectScheduleVO> revisions = projectSchedule.getRevisions();
        SortUtil.sort(revisions, "created", true);
        List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
        ProjectSchedule scheduleRevision = null;
        for(ProjectScheduleVO vo : revisions){
            scheduleRevision =  new ProjectSchedule(vo);
            if(!scheduleRevision.isReleased() && !projectScheduleObid.equals(vo.getObid())
                    && !vo.getStates().equals(ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_INACTIVE)){continue;}
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("names", vo.getObid());
            map.put("titles", vo.getRevision());
            resultList.add(map);
        }
        return resultList;
    }
    /**
     * 
     * @param projectScheduleObid
     * @param wbsActivityObid
     * @see lge.plm.project.wbs.service.WBSService#txnCompleteMyToDoActivity(java.lang.String, java.lang.String)
     */
    @Override
    public void txnCompleteMyToDoActivity(String projectScheduleObid, String wbsActivityObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        WBSItems wbsItemsDom = new WBSItems(wbsActivityObid);
        wbsItemsDom.completeMyToDoActivity(projectScheduleDom.getVo(), "Complete From WBS Schedule." , false);
    }

    /**
     * 
     * @param projectScheduleObid
     * @param wbsActivityObid
     * @see lge.plm.project.wbs.service.WBSService#txnForceStartActivity(java.lang.String, java.lang.String)
     */
    @Override
    public void txnForceStartActivity(String projectScheduleObid, String wbsActivityObid){
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        WBSItems wbsItemsDom = new WBSItems(wbsActivityObid);

        Map<String,WBSItemsVO> activityObjectDB = projectScheduleDom.getWBSDB();
        //wbsItemsDom.forceStartActivity(projectScheduleDom.getVo());
        wbsItemsDom.forceStartActivity(activityObjectDB,projectScheduleDom.getVo());
    }
    
    /**
     * 
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#txnCompleteActivitiesForDropProject(java.lang.String, java.lang.String)
     */
    @Override
    public void txnCompleteActivitiesForDropProject(String projectScheduleObid){
        new ProjectSchedule(projectScheduleObid).completeActivitiesForDropProject();
    }
    
    /**
     * 
     * @param projectScheduleObid
     * @param obid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#canBeCompleteMyToDoActivity(java.lang.String, java.lang.String)
     */
    @Override
    public boolean canBeCompleteMyToDoActivity(String projectScheduleObid, String obid){
        WBSActivities wbsActivity = new WBSActivities(obid);
        ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleObid);
        return wbsActivity.canBeCompleteMyToDoActivity(projectSchedule.getVo());
    }

    /**
     * 
     * @param projectScheduleObid
     * @param obid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getInboxTaskObjectForMyToDoActivity(java.lang.String, java.lang.String)
     */
    @Override
    public WorkflowInboxTaskVO getInboxTaskObjectForMyToDoActivity(String projectScheduleObid, String obid){
        WBSItems wbsItemsDom = new WBSItems(obid);
        ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleObid);
        return wbsItemsDom.getInboxTaskObjectForMyToDoActivity(projectSchedule.getVo());
    }
    /**
     * 
     * @param projectScheduleObid
     * @param wbsItemsVO
     * @see lge.plm.project.wbs.service.WBSService#txnRescheduleSuccessiveActivity(java.lang.String, lge.plm.api.object.project.model.WBSItemsVO)
     */
    @Override
    public List<WBSItemsVO> txnRescheduleSuccessiveActivity(String projectScheduleObid, WBSActivitiesVO activityVO, List<WBSItemsVO> wbsActivityList){
        List<WBSItemsVO> errorList = new ArrayList<WBSItemsVO>();
        Date currentDate = TimeServiceUtil.getTruncatedDBLocalDate();
        ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleObid);
        List<WBSItemsVO> structureList = new ArrayList<WBSItemsVO>();
        ProjectSchedule projectScheduleDom = new ProjectSchedule(projectScheduleObid);
        errorList = projectScheduleDom.getSkipInfoErrorList(wbsActivityList);
        if(!NullUtil.isNone(errorList)){return errorList;}
        Map<String,WBSItemsVO> activityObjectDB = projectScheduleDom.getWBSDB(structureList);
        refreshDBWithEditValue(activityObjectDB,wbsActivityList, currentDate);
        projectScheduleDom.setParentSkipFlags(structureList,activityObjectDB);
        projectSchedule.saveSimulatedScheduleResult(activityVO,currentDate,structureList,activityObjectDB);
        Set<String> attributes = new HashSet<String>();
        attributes.add("planDuration");attributes.add("planStartDate");attributes.add("planEndDate");attributes.add("isSkipped");
        ObjectRoot.modifyObjectSetBatch(activityVO, attributes);
        return errorList;
    }
    /**
     * 
     * @param projectScheduleObid
     * @param obid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#txnSkipWBSPhase(java.lang.String, java.lang.String)
     */
    @Override
    public void txnSkipWBSPhase(String projectScheduleObid, String obid){
        WBSPhases wbsPhase = new WBSPhases(obid);
        wbsPhase.skipWBSPhase(projectScheduleObid);
    }

    /**
     * 
     * @param activityObid
     * @param projectObid
     * @param projectScheduleObid
     * @param reassignVOList
     * @see lge.plm.project.wbs.service.WBSService#txnEditStartedActivityOwner(java.lang.String, java.lang.String, java.lang.String, java.util.List)
     */
    @Override
    public void txnEditStartedActivityOwner(String activityObid, String projectObid, String projectScheduleObid,
            List<ReassignVO> reassignVOCreateList, List<ReassignVO> reassignVOUpdateList, List<ReassignVO> reassignVODeleteList){
        WBSActivities wbsActivities = new WBSActivities(activityObid);        
        List<UsersVO> activityOwnerCreateList = new ArrayList<UsersVO>();
        List<UsersVO> activityOwnerDeleteList = new ArrayList<UsersVO>();
        if(reassignVOCreateList.size() > 0){
            for(int i=reassignVOCreateList.size()-1; i>=0; i--){
                ReassignVO reassignVO = reassignVOCreateList.get(i);
                String toAssigneeObid = reassignVO.getToAssigneeObid();
                if(StringUtils.isEmpty(toAssigneeObid)){
                    reassignVOCreateList.remove(i);
                    continue;
                }
                Users toUser = DomUtil.toDom(toAssigneeObid);
                activityOwnerCreateList.add(toUser.getVo());
            }
        }
        if(reassignVOUpdateList.size() > 0){
            for(int i=reassignVOUpdateList.size()-1; i>=0; i--){
                ReassignVO reassignVO = reassignVOUpdateList.get(i);
                reassignVO.setComments("Activity Owner Forward");
                String fromAssigneeObid = reassignVO.getFromAssigneeObid();
                String toAssigneeObid = reassignVO.getToAssigneeObid();
                if(StringUtils.isEmpty(toAssigneeObid)){
                    reassignVOUpdateList.remove(i);
                    continue;
                }
                Users fromUser = DomUtil.toDom(fromAssigneeObid);
                Users toUser = DomUtil.toDom(toAssigneeObid);
                activityOwnerCreateList.add(toUser.getVo());
                activityOwnerDeleteList.add(fromUser.getVo());
            }
        }
        if(reassignVODeleteList.size() > 0){
            for(ReassignVO reassignVO : reassignVODeleteList){
                String fromAssigneeObid = reassignVO.getFromAssigneeObid();
                Users fromUser = DomUtil.toDom(fromAssigneeObid);
                activityOwnerDeleteList.add(fromUser.getVo());
            }
        }
        txnUpdateActivityOwner(projectObid, projectScheduleObid, wbsActivities.getVo(), activityOwnerCreateList, null, activityOwnerDeleteList);
        WBSWorkflowServiceUtil.txnDeleteApproval(reassignVODeleteList);
        WBSWorkflowServiceUtil.txnAddApprover(wbsActivities, projectScheduleObid, reassignVOCreateList);
        WBSWorkflowServiceUtil.txnReassignApprover(wbsActivities, reassignVOUpdateList);
    }

    /**
     * 
     * @param projectScheduleObid
     * @param obid
     * @see lge.plm.project.wbs.service.WBSService#txnForceSkipWBSActivityFromWBS(java.lang.String, java.lang.String)
     */
    @Override
    public void txnForceSkipWBSActivityFromWBS(String projectScheduleObid, String obid){
        WBSItems wbsItems = DomUtil.toDom(obid);
        WBSItemsVO wbsItemsVO = wbsItems.getVo();
        wbsItemsVO.setIsSkipped("Y");
        Set<String> attributes = new HashSet<String>();
        attributes.add("isSkipped");
        ObjectRoot.modifyObjectSetBatch(wbsItemsVO, attributes);
        
        WBSWorkflowServiceUtil.txnDeleteWorkflowAndCreateDeleteApprovalInfoToEP(obid);
        wbsItems.change(wbsItemsVO.getClassName(), wbsItemsVO.getNames(), wbsItemsVO.getLifeCycle(), ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED);
        IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO(wbsItemsVO, ApplicationBizConstants.TR_ID_PROJECT_COM_OUT, ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_WBS_DETAIL_EACH);
        TriggerUtil.createDataByTrigger(triggerVO);
    }

    /**
     * 
     * @param obid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getJobActivityList(java.lang.String)
     */
    @Override
    public List<WBSJobActivityVO> getJobActivityList(String obid){
        WBSActivities wbsActivity = new WBSActivities(obid);
        return wbsActivity.getJobActivity();
    }

    /**
     * 
     * @param projectObid
     * @return
     * @see lge.plm.project.wbs.service.WBSService#getActivityDelayStatus(java.lang.String)
     */
    @Override
    public Map<String, List<WBSItemsVO>> getActivityDelayStatus(String projectObid){
        Projects projectDom = new Projects(projectObid);
        ProjectScheduleVO projectScheduleVO = projectDom.getProjectLatestReleaseSchedule();
        ProjectSchedule ProjectScheduleDom = new ProjectSchedule(projectScheduleVO);
        Map<String,List<WBSItemsVO>> result = new HashMap<String,List<WBSItemsVO>>();
        List<WBSItemsVO> wbsItemsDelayedActivityOnly = ProjectScheduleDom.getWBSItemsDelayedActivityOnly(false, true);
        SortUtil.sort(wbsItemsDelayedActivityOnly, "out.delayedDays", true);
        List<WBSItemsVO> npiStartedActivities = new ArrayList<WBSItemsVO>();
        List<WBSItemsVO> swStartedActivities = new ArrayList<WBSItemsVO>();
        List<WBSItemsVO> npiScheduledActivities = new ArrayList<WBSItemsVO>();
        for(WBSItemsVO vo : wbsItemsDelayedActivityOnly){
            vo.setTitles(new WBSItems(vo).getDisplayNameAsLoc());
            if(vo.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED)){
                if(ProjectConstants.WBS_PROJECTPHASE_NAME_SUBPROJECT.equals(vo.getPhaseNameSystem())){
                    if(swStartedActivities.size() < 2){swStartedActivities.add(vo);}
                }else{
                    if(npiStartedActivities.size() < 5){npiStartedActivities.add(vo);}
                }
            }else if(vo.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED)){
                if(!ProjectConstants.WBS_PROJECTPHASE_NAME_SUBPROJECT.equals(vo.getPhaseNameSystem())){
                    if(npiScheduledActivities.size() < 2){npiScheduledActivities.add(vo);}
                }
            }
        }
        result.put("npiStartedActivities", npiStartedActivities);
        result.put("swStartedActivities", swStartedActivities);
        result.put("npiScheduledActivities", npiScheduledActivities);
        return result;
    }

    /**
     * 
     * @param projectObid
     * @param projectScheduleObid
     * @see lge.plm.project.wbs.service.WBSService#unlockHolingProject(java.lang.String, java.lang.String)
     */
    @Override
    public void unlockHolingProject(String projectObid, String projectScheduleObid){
        Projects projectDom = new Projects(projectObid);
        ProjectsVO projectVO = projectDom.getVo();
        if(!"Y".equals(projectVO.getIsHolding())){return;}
        ProjectSchedule scheduleDom = new ProjectSchedule(projectScheduleObid);
        List<WBSItemsVO> wbsItemsDelayedActivityOnly = scheduleDom.getWBSItemsDelayedActivityOnly(false, false);
        SortUtil.sort(wbsItemsDelayedActivityOnly, "out.delayedDays", true);
        if(!NullUtil.isNone(wbsItemsDelayedActivityOnly)){
            WBSItemsVO wbsItemsVO = wbsItemsDelayedActivityOnly.get(0);
            if((Integer)wbsItemsVO.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_delayedDays) >= 30){
                throw new ApplicationException("Please, request the project leader to change the schedule through the change process without any delay in the schedule.");
            }
        }
        projectVO.setIsHolding("N");
        projectDom.modifyObject();
    }
}
