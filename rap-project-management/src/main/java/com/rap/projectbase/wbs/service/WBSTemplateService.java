/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSTemplateService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 6. 20.  heonhyung.lee   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.service;


import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rap.mail.model.MailVO;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.foundation.ui.model.CheckboxItemVO;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.projectbase.wbs.model.ProjectActivityDocumentTemplateSearchVO;
import com.rap.projectbase.wbs.util.model.DHTMLGanttLinkVO;
import com.rap.projectbase.wbs.util.model.DHTMLGanttTemplateActivityVO;

import rap.api.object.document.model.ProjectActivityDocumentTemplateVO;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.dom.WBSItemTemplates;
import rap.api.object.project.dom.WBSPhaseTemplate;
import rap.api.object.project.dom.WBSTemplateMaster;
import rap.api.object.project.model.ActivityTemplateMasterVO;
import rap.api.object.project.model.JobActivityTemplateVO;
import rap.api.object.project.model.ProjectDevelopmentTypeVO;
import rap.api.object.project.model.ProjectGradeVO;
import rap.api.object.project.model.ProjectLifeCycleVO;
import rap.api.object.project.model.ProjectPhaseVO;
import rap.api.object.project.model.ProjectRoleVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSPhaseTemplateVO;
import rap.api.object.project.model.WBSTemplateMasterVO;
import rap.api.object.relation.model.HasActivityTemplateVO;
import rap.api.object.relation.model.RecommendedDocumentTemplateVO;
import rap.api.object.relation.model.TemplateDependencyVO;


/**
 * <pre>
 * Class : WBSTemplateService
 * Description : TODO
 * </pre>
 * 
 * @author heonhyung.lee
 */
public interface WBSTemplateService {
	public List<ProjectDevelopmentTypeVO> getUsingProjectDevelopmentType(String divisionUnit);
    public List<ProjectDevelopmentTypeVO> getUsingProjectDevelopmentType(String divisionUnit, String className);
    public List<ProjectDevelopmentTypeVO> getUsingProjectDevelopmentType(String divisionUnit, String classNames,boolean isAllState);
    public List<ProjectPhaseVO> getUsingProjectPhases(String divisionUnit, String projectLifeCycle);
    public List<ProjectLifeCycleVO> getUsingProjectLifeCycleList(String divisionUnit);
    public List<ProjectGradeVO> getUsingProjecGrade(String divisionUnit);
    public List<ProjectGradeVO> getUsingProjectGradeByDevelopType(String divisionUnit, String developmentType, boolean activeOnly);
	
    public XSSFWorkbook getTemplateMasterExcelDownloadForUpate(String activityTemplateMasterObid);
    public List<ActivityTemplateMasterVO> findActivityTemplateMasters(Set<String> namesSet);
    public List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String divisionUnit, boolean includeInactive, boolean latestOnly);
    public List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String[] states);
    public List<WBSTemplateMasterVO> getLatestReleasedWBSTemplateMasterList(String divisionUnit);
    public WBSTemplateMasterVO retrieveWBSTemplateMaster(String divisionUnit, String developmentType, String projectLifeCycle);
    public void txnCreateWBSTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO, List<ProjectPhaseVO> projectPhases);
    public void txnUpdateWBSTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO, List<ProjectPhaseVO> projectPhases);
    public void txnUpdateProjectPhase(WBSTemplateMaster wbsTemplateMaster, List<ProjectPhaseVO> projectPhases);
    public List<WBSPhaseTemplate> txncreateProjectPhase(WBSTemplateMaster wbsTemplateMaster, List<ProjectPhaseVO> projectPhases);
    public WBSTemplateMasterVO txnReviseWBSTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO);
    public void txnCancelReviseWBSTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO);
    public void txnCreateSpareWBSItemsList(String wbsTemplateMasterObid);
    public List<ProjectGradeVO> retrieveUsingProjectGradeByDevelopType(String wbsTemplateMasterObid,boolean activeOnly);
    public List<ProjectGradeVO> getValidGradeListForTemplateMaster(String wbsTemplateMasterObid,boolean activeOnly);
    
    public void txnPublishTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO, MailVO mailVO, List<UsersVO> recipientList);
    public void txnSendMailTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO, MailVO mailVO, List<UsersVO> recipientList);

    public List<WBSItemTemplatesVO> getWBSItemTemplatesStructure(String wbsTemplateMasterObid);
    public <T> List<T> getWBSItemTemplatesStructure(String wbsTemplateMasterObid, String withJobActivity);
    public List<WBSItemTemplatesVO> getWBSItemTemplatesStructure(String wbsTemplateMasterObid, int findDepth);
    public List<BusinessObjectRootVO> getWBSItemTemplatesStructureWithJobActivity(String wbsTemplateMasterObid);
    public List<WBSPhaseTemplateVO> getWBSPhaseTemplateList(String wbsTemplateMasterObid);
    public List<WBSItemTemplatesVO> getSubWBSItemTemplate(String wbsTemplateMasterObid, String wbstemplateObid);
    public List<ProjectPhaseVO> getProjectPhasesListForCreate(String wbsTemplateMasterObid, boolean activeOnly);
    public List<ProjectPhaseVO> getAppliedPhaseInfoList(String wbsTemplateMasterObid, boolean activeOnly);
    public void txnCompleteWBSTemplateMaster(String wbsTemplateMasterObid);
    public void txnInActiveWBSTemplateMaster(String wbsTemplateMasterObid);
    public WBSItemTemplates txnCreateWBSItemTemplate(String wbsTemplateMasterObid, String parentObid, WBSItemTemplatesVO wbsItemTemplateVO);
    public WBSItemTemplates txnCreateWBSItemTemplate(String wbsTemplateMasterObid, String parentObid, WBSItemTemplatesVO wbsItemTemplateVO, Integer sequence);
    public WBSItemTemplates txnCreateWBSItemTemplate(String wbsTemplateMasterObid, String parentObid, WBSItemTemplatesVO wbsItemTemplatesVO, String activityMasterObid, List<TemplateDependencyVO> templateDependency, String addingPosition, Integer targetSequence);
    public void txnUpdateWBSItemTemplate(String wbsTemplateMasterObid, String parentObid, WBSItemTemplatesVO wbsItemTemplateVO);
    public void txnCopyWBSActivityTemplates(String wbsTemplateMasterObid, String sourceWBSTemplateMasterObid,List<WBSItemTemplatesVO> copiedWBSItemTemplatesVOList, WBSItemTemplatesVO sourceTopWBSActivityTemplatesVO, WBSItemTemplatesVO targetWBSItemTemplatesVO, boolean isSynchronize);
    public void txnUpdateWBSItemTemplate(String wbsTemplateMasterObid, String parentObid, WBSItemTemplatesVO wbsItemTemplateVO, String activityMasterObid, List<TemplateDependencyVO> dependencyCreateList, List<TemplateDependencyVO> dependencyUpdateList, List<TemplateDependencyVO> dependencyDeleteList);
    public void txnUpateTemplateSequences(List<HasActivityTemplateVO> hasActivityTemplateVOList);
    public void txnUpdateProjectActivityDocumentTemplate(String wbsTemplateMasterObid, String templateObid, List<RecommendedDocumentTemplateVO> documentCreateList, List<RecommendedDocumentTemplateVO> documentUpdateList, List<RecommendedDocumentTemplateVO> documentDeleteList);
    public void txnCreateWBSTemplateDependency(String wbsTemplateMasterObid, List<TemplateDependencyVO> createList);
    public void txnUpdateTemplateDependency(WBSTemplateMaster wbsTemplateMasterDom,String templateObid, List<TemplateDependencyVO> dependencyCreateList, List<TemplateDependencyVO> dependencyUpdateList, List<TemplateDependencyVO> dependencyDeleteList);
    public void txnDeleteWBSItemTemplate(WBSTemplateMaster wbsTemplateMasterDom, String obid);

    public WBSItemTemplatesVO txnReviseWBSItemTemplate(String wbsTemplateMasterObid,String obid);
    public void txnAllReviseWBSItemTemplate(String wbsTemplateMasterObid);
    public WBSItemTemplatesVO txnCancelReviseWBSItemTemplate(String wbsTemplateMasterObid,String obid);
    
    public void txnCreateJobActivityTemplate(String wbsTemplateMasterObid, String activityTemplateObid, JobActivityTemplateVO jobActivityTemplateVO, String addingPosition, Integer targetSequence);
    public void txnUpdateJobActivityTemplate(JobActivityTemplateVO jobActivityTemplateVO);
    
    public void txnCreateActivityTemplateMaster(ActivityTemplateMasterVO activityTemplateMasterVO);
    public void txnUpdateActivityTemplateMaster(ActivityTemplateMasterVO activityTemplateMasterVO);
//    public List<WBSTemplateMasterVO> retrieveManagedTemplateMaster();
//    public WBSTemplateMasterVO retrieveLatestManagedTemplateMaster();
    
    public List<BusinessRelationObjectVO> getTemplateDependency(String wbsTemplateMasterObid,String wbsItemTemplatesObid);
    public List<BusinessRelationObjectVO> retrieveRecomendedDocumentTemplate(String wbsTemplateMasterObid,String wbsItemTemplatesObid);

    public List<ProjectRoleVO> retrieveUsedRoleAtWBSTemplate(String wbsTemplateMasterObid);
    public List<ProjectRoleVO> retrieveAllocatedRoleToWBSTemplateItems(String wbsItemTemplateObid);
    
    public List<ActivityTemplateMasterVO> retrieveActivityTemplateMasterList(ActivityTemplateMasterVO searchInfo);
    public List<ProjectActivityDocumentTemplateVO> retrieveProjectActivityDocumentTemplateList(ProjectActivityDocumentTemplateSearchVO searchInfo);

    public void txnCloneWbsTemplateMaster(String fromMasterObid, WBSTemplateMasterVO targetMasterVO);
    public List<WBSTemplateMasterVO> txnCloneWbsTemplateMasterFromOtherDivision(List<WBSTemplateMasterVO> wbsTemplateMasterList, String targetDivisionUnit);
    public void getWBSTemplateListForGantt(WBSTemplateMasterVO wbsTemplateMasterVO, List<DHTMLGanttTemplateActivityVO> ganttActivityList,List<DHTMLGanttLinkVO> linkList);
//    public ExcelDownloadView exportWBSTemplateToExcel(String wbsTemplateMasterObid, boolean includeDoc,boolean activeOnly) throws Exception;
//    public ExcelDownloadView exportWBSTemplateForImport(String wbsTemplateMasterObid, boolean includeDoc,boolean activeOnly) throws Exception;

    public List<HashMap<String, String>> getDivisionCombo();
    public List<CheckboxItemVO> getRevisionOptionGroup(String obid, String obidContext, UserSession userSession, String dummy );
    
    public HashMap<String, Object> updateSkipInfo(HashMap<String, Object> propertyParams);
    
    // Migration 용도
    public HashMap<String, Object> createActivityTemplateMaster(List<ActivityTemplateMasterVO> dataList);
}
