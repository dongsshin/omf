/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSTemplateServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 6. 20.  heonhyung.lee   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rap.mail.model.MailVO;
import com.rap.mail.service.MailService;
import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.object.dom.BusinessObjectMaster;
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
import com.rap.omc.framework.exception.FoundationBaseException;
import com.rap.omc.intf.IfPlmDataByTriggerVO;
import com.rap.omc.intf.TriggerUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.TimeServiceUtil;
import com.rap.projectbase.wbs.model.ProjectActivityDocumentTemplateSearchVO;
import com.rap.projectbase.wbs.service.WBSTemplateService;
import com.rap.projectbase.wbs.util.WBSTemplateUtil;
import com.rap.projectbase.wbs.util.model.DHTMLGanttLinkVO;
import com.rap.projectbase.wbs.util.model.DHTMLGanttTemplateActivityVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import rap.api.object.document.dom.ProjectActivityDocumentTemplate;
import rap.api.object.document.model.ProjectActivityDocumentTemplateVO;
import rap.api.object.organization.dom.DivisionUnit;
import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.dom.ActivityTemplateMaster;
import rap.api.object.project.dom.JobActivityTemplate;
import rap.api.object.project.dom.ProjectGrade;
import rap.api.object.project.dom.ProjectLifeCycle;
import rap.api.object.project.dom.ProjectSchedule;
import rap.api.object.project.dom.WBSActivityTemplate;
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
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.WBSActivityTemplateVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSPhaseTemplateVO;
import rap.api.object.project.model.WBSSubProjectTemplateVO;
import rap.api.object.project.model.WBSTemplateMasterVO;
import rap.api.object.relation.dom.HasActivityTemplate;
import rap.api.object.relation.dom.HasJobActivityTemplate;
import rap.api.object.relation.dom.RecommendedDocumentTemplate;
import rap.api.object.relation.dom.RelatedTemplateMaster;
import rap.api.object.relation.dom.TemplateDependency;
import rap.api.object.relation.dom.WBSManagedBy;
import rap.api.object.relation.model.HasActivityTemplateVO;
import rap.api.object.relation.model.HasJobActivityTemplateVO;
import rap.api.object.relation.model.RecommendedDocumentTemplateVO;
import rap.api.object.relation.model.TemplateDependencyVO;
import rap.api.object.relation.model.WBSDependencyVO;
import rap.api.object.relation.model.WBSManagedByVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;


/**
 * <pre>
 * Class : WBSTemplateServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author heonhyung.lee
 */
@Service("wbsTemplateService")
public class WBSTemplateServiceImpl implements WBSTemplateService {
    
    @Autowired
    private UserSession userSession;
    
    @Resource(name = "mailService")
    private MailService mailService;
    
    /**
     * 
     * @param divisionUnit
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveWBSTemplateMasterList(java.lang.String)
     */
    @Override
    public List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String divisionUnit, boolean includeInactive, boolean latestOnly){
        return WBSTemplateMaster.retrieveWBSTemplateMasterList(divisionUnit, includeInactive, latestOnly);
    }
    /**
     * 
     * @param states
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveWBSTemplateMasterList(java.lang.String[])
     */
    @Override
    public List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList(String[] states){
        return WBSTemplateMaster.retrieveWBSTemplateMasterList(states);
    }
    /**
     * 
     * @param namesSet
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#findWBSTemplateMasterList(java.util.Set)
     */
    @Override
    public List<ActivityTemplateMasterVO> findActivityTemplateMasters(Set<String> namesSet){
        return BusinessObjectMaster.getBusinessObjectMasters(ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER, namesSet);
    }
    /**
     * 
     * @param divisionUnit
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#getLatestReleasedWBSTemplateMasterList(java.lang.String)
     */
    @Override
    public List<WBSTemplateMasterVO> getLatestReleasedWBSTemplateMasterList(String divisionUnit){
        return WBSTemplateMaster.getLatestReleasedWBSTemplateMasterList(divisionUnit);
    }
    
    /**
     * 
     * @param divisionUnit
     * @param developmentType
     * @param projectLifeCycle
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveWBSTemplateMaster(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public WBSTemplateMasterVO retrieveWBSTemplateMaster(String divisionUnit, String developmentType, String projectLifeCycle){
        WBSTemplateMasterVO result = null;
        List<WBSTemplateMasterVO> retrieveWBSTemplateMasterList = WBSTemplateMaster.retrieveWBSTemplateMasterList(divisionUnit, developmentType, projectLifeCycle);
        if(!retrieveWBSTemplateMasterList.isEmpty() && retrieveWBSTemplateMasterList.size() == 1){
            result = retrieveWBSTemplateMasterList.get(0);
        }
        return result;
    }

    /** 
     * 
     * @param wbsTemplateMasterVO
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCreateWBSTemplateMaster(lge.plm.api.object.project.model.WBSTemplateMasterVO, java.util.List)
     */
    @Override
    public void txnCreateWBSTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO, List<ProjectPhaseVO> projectPhases){
        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(wbsTemplateMasterVO);
        wbsTemplateMaster.setDefault(true);
        wbsTemplateMaster.createObject();
    }
    
    /**
     * 
     * @param wbsTemplateMasterVO
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnUpdateWBSTemplateMaster(lge.plm.api.object.project.model.WBSTemplateMasterVO, java.util.List)
     */
    @Override
    public void txnUpdateWBSTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO, List<ProjectPhaseVO> projectPhases){
        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(wbsTemplateMasterVO);
        wbsTemplateMaster.modifyObject();
    }


    /**
     * 
     * @param wbsTemplateMaster
     * @param projectPhases
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnUpdateProjectPhase(lge.plm.api.object.project.dom.WBSTemplateMaster, java.util.List)
     */
    @Override
    public void txnUpdateProjectPhase(WBSTemplateMaster wbsTemplateMaster, List<ProjectPhaseVO> projectPhases){
        //wbsTemplateMaster.updatePhasesTemplateList(makeWBSPhaseTemplateVOList(projectPhases));        
    }
    
    public List<WBSPhaseTemplateVO> makeWBSPhaseTemplateVOList(List<ProjectPhaseVO> projectPhases){
        List<WBSPhaseTemplateVO> wbsPhaseTemplateVOList = new ArrayList<WBSPhaseTemplateVO>();
        HashMap<String, Object> outData = null;
        for(ProjectPhaseVO projectPhaseVO : projectPhases){
            
            outData = projectPhaseVO.getOutData();
            WBSPhaseTemplateVO wbsPhaseTemplateVO = new WBSPhaseTemplateVO(); 
            wbsPhaseTemplateVO.setActivityMasterName(projectPhaseVO.getNames());
            wbsPhaseTemplateVO.setProjectPhaseName(projectPhaseVO.getNames());
            wbsPhaseTemplateVO.setTitles((String)outData.get("displayedName"));
            wbsPhaseTemplateVO.setActivityNameKor((String)outData.get("displayedName"));
            wbsPhaseTemplateVO.setActivityNameEng((String)outData.get("displayedName"));
            wbsPhaseTemplateVO.setActivityNameChi((String)outData.get("displayedName"));
            wbsPhaseTemplateVO.setDescriptions((String)outData.get("displayedCode"));
            wbsPhaseTemplateVO.getOutData().put("targetSequences",(Integer)outData.get("rel_sequences"));
            
            wbsPhaseTemplateVOList.add(wbsPhaseTemplateVO);
        }
        return wbsPhaseTemplateVOList;
    }
    
    /**
     * 
     * @param wbsTemplateMaster
     * @param projectPhases
     * @see lge.plm.project.wbs.service.WBSTemplateService#txncreateProjectPhase(lge.plm.api.object.project.dom.WBSTemplateMaster, java.util.List)
     */
    @Override
    public List<WBSPhaseTemplate> txncreateProjectPhase(WBSTemplateMaster wbsTemplateMaster, List<ProjectPhaseVO> projectPhases){
        return wbsTemplateMaster.createPhasesTemplateList(makeWBSPhaseTemplateVOList(projectPhases));        
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveUsingProjectGradeByDevelopType(java.lang.String)
     */
    @Override
    public List<ProjectGradeVO> retrieveUsingProjectGradeByDevelopType(String wbsTemplateMasterObid,boolean activeOnly){
        return new WBSTemplateMaster(wbsTemplateMasterObid).retrieveUsingProjectGradeByDevelopType(activeOnly);
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @return List<WBSItemTemplatesVO>
     * @see lge.plm.project.wbs.service.WBSTemplateService#getWBSItemTemplatesStructure(java.lang.String)
     */
    @Override
    public List<WBSItemTemplatesVO> getWBSItemTemplatesStructure(String wbsTemplateMasterObid){
        return new WBSTemplateMaster(wbsTemplateMasterObid).getWBSItemTemplatesStructure();
    }

    /**
     * 
     * @param wbsTemplateMasterObid
     * @param withJobActivity
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#getWBSItemTemplatesStructure(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getWBSItemTemplatesStructure(String wbsTemplateMasterObid, String withJobActivity){
        List<WBSItemTemplatesVO> wbsItemTemplatesStructure = getWBSItemTemplatesStructure(wbsTemplateMasterObid);
        List<TemplateDependencyVO> dependencyList = WBSTemplateMaster.getTemplateDependencyAll(wbsTemplateMasterObid, wbsItemTemplatesStructure);
        WBSTemplateMaster.makePreviousActivityList(wbsItemTemplatesStructure,dependencyList);
        List<BusinessObjectRootVO> list = new ArrayList<BusinessObjectRootVO>();
        
        if("Y".equals(withJobActivity)){
            if(NullUtil.isNone(wbsItemTemplatesStructure)) return new ArrayList<T>();
            StringBuffer jobSelectPatternBuf = new StringBuffer();
            String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
            if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameKor]");
            }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameChi]");
            }else{
                StringUtil.addSortByPattern(jobSelectPatternBuf, "@this.[activityNameEng]");
            }
            list = ObjectRoot.getRelatedObjectSet(wbsItemTemplatesStructure, 
                    ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, 
                    ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE, GlobalConstants.FLAG_TYPE_TO,jobSelectPatternBuf.toString(),"","",true,true);
        }else{
            list.addAll(wbsItemTemplatesStructure);
        }
        SortUtil.sort(list, "uniqueString", false);
        return (List<T>)list;
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @return List<WBSItemTemplatesVO>
     * @see lge.plm.project.wbs.service.WBSTemplateService#getWBSItemTemplatesStructure(java.lang.String)
     */
    @Override
    public List<WBSItemTemplatesVO> getWBSItemTemplatesStructure(String wbsTemplateMasterObid, int findDepth){
        return new WBSTemplateMaster(wbsTemplateMasterObid).getWBSItemTemplatesStructure(findDepth);
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#getWBSPhaseTemplateList(java.lang.String)
     */
    @Override
    public List<WBSPhaseTemplateVO> getWBSPhaseTemplateList(String wbsTemplateMasterObid){
        return new WBSTemplateMaster(wbsTemplateMasterObid).getWBSPhaseTemplateList();
    }

    /**
     * 
     * @param wbsTemplateMasterObid
     * @param wbstemplateObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#getSubWBSItemTemplate(java.lang.String, java.lang.String)
     */
    @Override
    public List<WBSItemTemplatesVO> getSubWBSItemTemplate(String wbsTemplateMasterObid, String wbstemplateObid){
        return new WBSItemTemplates(wbstemplateObid).getSubWBSItemTemplate(wbsTemplateMasterObid, false, 1);
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#getProjectPhasesListForCreate(java.lang.String)
     */
    @Override
    public List<ProjectPhaseVO> getProjectPhasesListForCreate(String wbsTemplateMasterObid, boolean activeOnly){
        return new WBSTemplateMaster(wbsTemplateMasterObid).getNotAppliedProjectInfoList(activeOnly);
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#getAppliedPhaseInfoList(java.lang.String)
     */
    @Override
    public List<ProjectPhaseVO> getAppliedPhaseInfoList(String wbsTemplateMasterObid, boolean activeOnly){
        return new WBSTemplateMaster(wbsTemplateMasterObid).getAppliedPhaseInfoList(activeOnly);
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @param targetStates
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCompleteWBSTemplateMaster(java.lang.String)
     */
    @Override
    public void txnCompleteWBSTemplateMaster(String wbsTemplateMasterObid){

        WBSTemplateMaster wbstemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);

        ThreadLocalUtil.set(ThreadLocalUtil.KEY.contextBizObject, wbstemplateMasterDom.getVo());
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,true);
        try{
            wbstemplateMasterDom.completeWBSTemplateMaster();    
        }finally{
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,false);
        }
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @param targetStates
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnInActiveWBSTemplateMaster(java.lang.String, java.lang.String)
     */
    @Override
    public void txnInActiveWBSTemplateMaster(String wbsTemplateMasterObid){
        WBSTemplateMaster wbstemplateMaster = new WBSTemplateMaster(wbsTemplateMasterObid);
        if(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_WORKING.equals((String)wbstemplateMaster.getStates())){
            throw new ApplicationException("Can not be inactivated template, try cancel revise.");
        }else if(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE.equals((String)wbstemplateMaster.getStates())){
            if("N".equals(wbstemplateMaster.getVo().getIsPublished()) && !wbstemplateMaster.isFirst()){
                throw new ApplicationException("Can not be inactivated template, Please contact System Administrator.");
                // 이전 Revision 확인 필요.
            }
            wbstemplateMaster.promote();
        }else{
            throw new ApplicationException("TemplateMaster states error.");
        }
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @param parentObid
     * @param wbsItemTemplateVO
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCreateWBSItemTemplate(java.lang.String, java.lang.String, lge.plm.api.object.project.model.WBSItemTemplatesVO)
     */
    @Override
    public WBSItemTemplates txnCreateWBSItemTemplate(String wbsTemplateMasterObid, String parentObid, WBSItemTemplatesVO wbsItemTemplateVO){
        return txnCreateWBSItemTemplate(wbsTemplateMasterObid, parentObid, wbsItemTemplateVO, null);
    }

    /**
     * 
     * @param wbsTemplateMasterObid
     * @param parentObid
     * @param wbsItemTemplateVO
     * @param targetSequences
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCreateWBSItemTemplate(java.lang.String, java.lang.String, lge.plm.api.object.project.model.WBSItemTemplatesVO, Integer)
     */
    @Override
    public WBSItemTemplates txnCreateWBSItemTemplate(String wbsTemplateMasterObid, String parentObid, WBSItemTemplatesVO wbsItemTemplateVO, Integer targetSequences){
        return txnCreateWBSItemTemplate(wbsTemplateMasterObid, parentObid, wbsItemTemplateVO, null, null, null, targetSequences);
    }

    /**
     * 
     * @param wbsTemplateMasterObid
     * @param parentObid
     * @param wbsActivityTemplateVO
     * @param documentTemplateMaster
     * @param templateDependency
     * @param addingPosition
     * @param targetSequence
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCreateWBSActivityTemplate(java.lang.String, java.lang.String, lge.plm.api.object.project.model.WBSItemTemplatesVO, java.util.List, java.lang.String Integer)
     */
    @Override
    public WBSItemTemplates txnCreateWBSItemTemplate(String wbsTemplateMasterObid, String parentObid, WBSItemTemplatesVO wbsItemTemplatesVO, String activityMasterObid, 
                                         List<TemplateDependencyVO> templateDependency, String addingPosition, Integer targetSequence){
        
        WBSTemplateMaster wbsTemplateMasterDom  = new WBSTemplateMaster(wbsTemplateMasterObid);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wbsTemplateMasterObid", wbsTemplateMasterObid);
        map.put("parentObid", parentObid);
        map.put("activityMasterObid", activityMasterObid);
        map.put("addingPosition", addingPosition);
        map.put("targetSequences", targetSequence);
        map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, wbsTemplateMasterDom);
        
        WBSItemTemplates activity = DomUtil.toDom(wbsItemTemplatesVO);
        activity.setDefault(true);
        activity.createObject(map);

        if(wbsItemTemplatesVO instanceof WBSActivityTemplateVO){
            WBSActivityTemplate wbsActivityTemplate = new WBSActivityTemplate((WBSActivityTemplateVO)wbsItemTemplatesVO);
            wbsActivityTemplate.updateProjectRole(wbsTemplateMasterDom, (String)wbsItemTemplatesVO.getOutData().get("roleCodeList"));
            if(!NullUtil.isNone(templateDependency)){
                for(TemplateDependencyVO templateDependencyVO : templateDependency){
                    templateDependencyVO.setFromObid(wbsItemTemplatesVO.getObid());
                    Map<String, Object> dependencyMap = new HashMap<String, Object>();
                    dependencyMap.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, wbsTemplateMasterDom);
                    new TemplateDependency(templateDependencyVO).createObject(dependencyMap);
                }
            }
        }
        return activity;
        
    }
    /**
     * 
     * 
     * @param wbsTemplateMasterObid
     * @param sourceWBSTemplateMasterObid
     * @param copiedWBSItemTemplatesVOList
     * @param sourceTopWBSActivityTemplatesVO
     * @param targetWBSItemTemplatesVO
     * @param isSynchronize
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCopyWBSActivityTemplates(java.lang.String, java.lang.String, java.util.List, lge.plm.api.object.project.model.WBSItemTemplatesVO, lge.plm.api.object.project.model.WBSItemTemplatesVO, boolean)
     */
    @Override
    public void txnCopyWBSActivityTemplates(String wbsTemplateMasterObid, String sourceWBSTemplateMasterObid, List<WBSItemTemplatesVO> copiedWBSItemTemplatesVOList, WBSItemTemplatesVO sourceTopWBSActivityTemplatesVO, WBSItemTemplatesVO targetWBSItemTemplatesVO, boolean isSynchronize){
        WBSTemplateMaster wbsTemplateMasterDom  = new WBSTemplateMaster(wbsTemplateMasterObid);
        WBSTemplateMaster sourceWBSTemplateMasterDom  = new WBSTemplateMaster(sourceWBSTemplateMasterObid);
        WBSItemTemplates targetWBSItemTemplatesDom = null;
        WBSItemTemplates sourceWBSItemTemplatesDom = new WBSItemTemplates(sourceTopWBSActivityTemplatesVO.getObid());
        if(!StrUtil.isEmpty(targetWBSItemTemplatesVO.getObid())){
            targetWBSItemTemplatesDom = new WBSItemTemplates(targetWBSItemTemplatesVO.getObid());
        }
        Map<String,WBSItemTemplatesVO> objectMap = new HashMap<String,WBSItemTemplatesVO>();

        DivisionUnitVO divisionUnitVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, wbsTemplateMasterDom.getVo().getDivisionUnit());
        List<WBSItemTemplatesVO> createdWBSItemTemplatesVOList = new ArrayList<WBSItemTemplatesVO>();
        List<WBSItemTemplatesVO> leafList = new ArrayList<WBSItemTemplatesVO>();
        boolean isFirst = true;
        for(WBSItemTemplatesVO wbsItemTemplatesVO : copiedWBSItemTemplatesVOList){
            WBSItemTemplates wbsItemTemplatesDom = new WBSItemTemplates(wbsItemTemplatesVO.getObid());
            WBSItemTemplatesVO createdWBSItemTemplatesVO = null;
            if(wbsItemTemplatesDom.getVo() instanceof WBSActivityTemplateVO){
                ActivityTemplateMasterVO activityTemplateMasterVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER, wbsItemTemplatesDom.getVo().getActivityMasterName());
                String activityMasterObid = null;
                if(!NullUtil.isNull(activityTemplateMasterVO)) activityMasterObid = activityTemplateMasterVO.getObid();
                String parentObid = "";
                if(isFirst || isSynchronize){
                    parentObid = targetWBSItemTemplatesDom.getObid();
                }else{
                    String oldParentObid = wbsItemTemplatesVO.getOutDataAttributeValue("rel_fromObid");
                    parentObid = objectMap.get(oldParentObid).getObid();
                }
                // Role Code List
                List<ProjectRoleVO> roleList = wbsItemTemplatesDom.getAllocatedRole(sourceWBSTemplateMasterDom);
                StringBuffer roleBuf = new StringBuffer();
                for(ProjectRoleVO projectRoleVO : roleList){
                    if(!StrUtil.isEmpty(roleBuf)) roleBuf.append(",");
                    roleBuf.append(projectRoleVO.getNames());
                }
                wbsItemTemplatesDom.getVo().setOutDataAttributeValue("roleCodeList", roleBuf.toString());
                // Recommend Document Template List
                List<BusinessRelationObjectVO> recommendedDocumentTemplatesList = wbsItemTemplatesDom.retrieveRecomendedDocumentTemplate(sourceWBSTemplateMasterObid);
                wbsItemTemplatesVO.setOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE_RecommendedDocumentTemplatesList, recommendedDocumentTemplatesList);

                WBSItemTemplates createdWBSItemTemplates = txnCreateWBSItemTemplate(wbsTemplateMasterObid,parentObid,(WBSItemTemplatesVO)DomUtil.copy(wbsItemTemplatesDom.getVo()),activityMasterObid,null,null,null);
                createdWBSItemTemplatesVO = createdWBSItemTemplates.getVo();
                // Dependency List
                if(wbsItemTemplatesDom.isLeaf()){
                    leafList.add(wbsItemTemplatesVO);
                    List<BusinessRelationObjectVO> dependencyListVO= wbsItemTemplatesDom.getTemplateDependency(sourceWBSTemplateMasterObid);
                    wbsItemTemplatesVO.setOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE_DependencyList, dependencyListVO);
                }
            }else if(wbsItemTemplatesDom.getVo() instanceof WBSPhaseTemplateVO){
                List<ProjectPhaseVO> phaseList = ProjectLifeCycle.getProjectPhaseList(wbsTemplateMasterDom.getVo().getProjectLifeCycle(), divisionUnitVO, null, false);
                boolean canBeApplied = false;
                List<ProjectPhaseVO> projectPhases = new ArrayList<ProjectPhaseVO>();
                for(ProjectPhaseVO vo : phaseList){
                    if(vo.getNames().equals(wbsItemTemplatesDom.getVo().getActivityMasterName())) {
                        canBeApplied = true; 
                        vo.setOutDataAttributeValue("targetSequences", 10000);
                        projectPhases.add(vo);
                        break;
                    }
                }
                if(!canBeApplied) throw new ApplicationException("'" + wbsItemTemplatesDom.getVo().getActivityMasterName() + "' can be copied.");
                List<ProjectPhaseVO> notAppliedPhaseList = this.getProjectPhasesListForCreate(wbsTemplateMasterObid,false);
                boolean alreadyApplied = true;
                for(ProjectPhaseVO vo : notAppliedPhaseList){
                    if(vo.getNames().equals(wbsItemTemplatesDom.getVo().getActivityMasterName())) {
                        alreadyApplied = false; 
                        break;
                    }
                }
                if(alreadyApplied) throw new ApplicationException("'" + wbsItemTemplatesDom.getVo().getActivityNameEng() + "' already exists.");
                List<WBSPhaseTemplate> createdWBSPhaseTemplateDomList = txncreateProjectPhase(wbsTemplateMasterDom, projectPhases);
                createdWBSItemTemplatesVO = createdWBSPhaseTemplateDomList.get(0).getVo();
            }
            else if(wbsItemTemplatesDom.getVo() instanceof WBSSubProjectTemplateVO){
                // Sub Project인 경우 Parent는 Template Master 임
                WBSItemTemplates createdSubProjectTemplateDom = txnCreateWBSItemTemplate(wbsTemplateMasterObid, wbsTemplateMasterObid,wbsItemTemplatesDom.getVo());
                createdWBSItemTemplatesVO = createdSubProjectTemplateDom.getVo();
            }
            if(!NullUtil.isNull(createdWBSItemTemplatesVO)) {
                objectMap.put(createdWBSItemTemplatesVO.getObid(), wbsItemTemplatesVO);
                objectMap.put(wbsItemTemplatesVO.getObid(), createdWBSItemTemplatesVO);
                createdWBSItemTemplatesVOList.add(createdWBSItemTemplatesVO);
            }
            isFirst = false;
        }
        cleanDependencyList(copiedWBSItemTemplatesVOList,objectMap);
        copyRelationForCopy(wbsTemplateMasterDom,createdWBSItemTemplatesVOList,objectMap);
        copyJobActivity(wbsTemplateMasterObid,leafList,objectMap);
        if(isSynchronize) synchronizeSequence(wbsTemplateMasterDom,sourceWBSTemplateMasterDom,sourceWBSItemTemplatesDom,targetWBSItemTemplatesDom);
    }
    /**
     * 
     *
     * @param wbsTemplateMasterDom
     * @param sourceWBSTemplateMasterDom
     * @param sourceWBSItemTemplatesDom
     * @param targetWBSItemTemplatesDom
     */
    private void synchronizeSequence(WBSTemplateMaster wbsTemplateMasterDom,WBSTemplateMaster sourceWBSTemplateMasterDom,WBSItemTemplates sourceWBSItemTemplatesDom, WBSItemTemplates targetWBSItemTemplatesDom){
        List<WBSItemTemplatesVO> targetList = targetWBSItemTemplatesDom.getSubWBSItemTemplate(wbsTemplateMasterDom.getObid(), false, 1);
        List<WBSItemTemplatesVO> sourceList = sourceWBSItemTemplatesDom.getSubWBSItemTemplate(sourceWBSTemplateMasterDom.getObid(), false, 1);
        for(WBSItemTemplatesVO vo : targetList){
            boolean isExists = false;
            Integer sequences = 0;
            for(WBSItemTemplatesVO sourceVO : sourceList){
                if(vo.getActivityMasterName().equals(sourceVO.getActivityMasterName())){
                    isExists = true;
                    sequences = sourceVO.getOutDataAttributeValue("rel_sequences");
                    break;
                }
            }
            if(isExists){
                HasActivityTemplate hasActivityTemplateDom = new HasActivityTemplate((String)vo.getOutDataAttributeValue("rel_obid"));
                hasActivityTemplateDom.getVo().setSequences(sequences);
                hasActivityTemplateDom.modifyObject();
            }
        }
    }
    /**
     * 
     *
     * @param wbsTemplateMasterObid
     * @param leafList
     * @param objectMap
     */
    private void copyJobActivity(String wbsTemplateMasterObid, List<WBSItemTemplatesVO> leafList,Map<String,WBSItemTemplatesVO> objectMap){
        if(!NullUtil.isNone(leafList)){
            List<JobActivityTemplateVO> jobActivityList = ObjectRoot.getRelatedObjectSet(leafList, ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE, GlobalConstants.FLAG_TYPE_TO,"","","",true,false);
            for(JobActivityTemplateVO vo : jobActivityList){
                String sourceActivityTemplateObid = vo.getOutDataAttributeValue("rel_fromObid");
                String activityTemplateObid = objectMap.get(sourceActivityTemplateObid).getObid();
                txnCreateJobActivityTemplate(wbsTemplateMasterObid,activityTemplateObid,vo,null,null);
            }
        }
    }
    /**
     * 
     *
     * @param copiedWBSItemTemplatesVOList
     * @param objectMap
     */
    private void cleanDependencyList(List<WBSItemTemplatesVO> copiedWBSItemTemplatesVOList,Map<String,WBSItemTemplatesVO> objectMap){
        for(WBSItemTemplatesVO wbsItemTemplatesVO : copiedWBSItemTemplatesVOList){
            List<BusinessRelationObjectVO> dependencyListVO = wbsItemTemplatesVO.getOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE_DependencyList);
            List<TemplateDependencyVO> newDependencyList = new ArrayList<TemplateDependencyVO>();
            if(!NullUtil.isNone(dependencyListVO)){
                for(BusinessRelationObjectVO vo : dependencyListVO){
                    if(objectMap.get(vo.getToObid()) != null) newDependencyList.add((TemplateDependencyVO)vo);
                }
            }
            wbsItemTemplatesVO.setOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE_DependencyList, newDependencyList);
        }
    }
    /**
     * 
     *
     * @param wbsTemplateMasterDom
     * @param createdWBSItemTemplatesVOList
     * @param objectMap
     */
    private void copyRelationForCopy(WBSTemplateMaster wbsTemplateMasterDom,List<WBSItemTemplatesVO> createdWBSItemTemplatesVOList,Map<String,WBSItemTemplatesVO> objectMap){
        for(WBSItemTemplatesVO createdVO : createdWBSItemTemplatesVOList){
            WBSItemTemplatesVO sourceWBSItemTemplatesVO = objectMap.get(createdVO.getObid());
            List<TemplateDependencyVO> newDependencyList = sourceWBSItemTemplatesVO.getOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE_DependencyList);
            if(!NullUtil.isNone(newDependencyList)){
                for(TemplateDependencyVO templateDependencyVO : newDependencyList){
                    templateDependencyVO.setFromObid(createdVO.getObid());
                    templateDependencyVO.setFromClass(createdVO.getClassName());
                    WBSItemTemplatesVO createdToWBSItemTemplatesVO = objectMap.get(templateDependencyVO.getToObid());
                    if(NullUtil.isNull(createdToWBSItemTemplatesVO)) throw new ApplicationException("Not Created.......");
                    templateDependencyVO.setToObid(createdToWBSItemTemplatesVO.getObid());
                    templateDependencyVO.setToClass(createdToWBSItemTemplatesVO.getClassName());
                }
                txnCreateWBSTemplateDependency(wbsTemplateMasterDom.getObid(),newDependencyList);
            }
            List<BusinessRelationObjectVO> recommendedDocumentTemplatesList = sourceWBSItemTemplatesVO.getOutDataAttributeValue(ProjectConstants.WBS_TEMPLATE_RecommendedDocumentTemplatesList);
            if(!NullUtil.isNone(recommendedDocumentTemplatesList)){
                for(BusinessRelationObjectVO recommendedDocumentTemplateVO : recommendedDocumentTemplatesList){
                    if(recommendedDocumentTemplateVO instanceof RecommendedDocumentTemplateVO){
                        recommendedDocumentTemplateVO.setFromObid(createdVO.getObid());
                        recommendedDocumentTemplateVO.setFromClass(createdVO.getClassName());
                        RecommendedDocumentTemplate recommendedDocumentTemplateDom = new RecommendedDocumentTemplate((RecommendedDocumentTemplateVO)recommendedDocumentTemplateVO);
                        recommendedDocumentTemplateDom.createObject();
                    }
                }
            }
        }
    }
    /**
     * 
     * @param wbsTemplateMasterObid
     * @param wbsItemTemplateVO
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnUpdateWBSItemTemplate(java.lang.String, lge.plm.api.object.project.model.WBSItemTemplatesVO)
     */
    @Override
    public void txnUpdateWBSItemTemplate(String wbsTemplateMasterObid, String parentObid, WBSItemTemplatesVO wbsItemTemplateVO){
        txnUpdateWBSItemTemplate(wbsTemplateMasterObid, parentObid, wbsItemTemplateVO, null, null, null, null);
    }
    /**
     * 
     * @param wbsTemplateMasterObid
     * @param wbsItemTemplateVO
     * @param createList
     * @param updateList
     * @param deteList
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnUpdateWBSActivityTemplate(java.lang.String, lge.plm.api.object.project.model.wbsItemTemplateVO, java.util.List, java.util.List, java.util.List)
     */
    @Override
    public void txnUpdateWBSItemTemplate(String wbsTemplateMasterObid, String parentObid, WBSItemTemplatesVO wbsItemTemplateVO, String activityMasterObid, List<TemplateDependencyVO> dependencyCreateList, List<TemplateDependencyVO> dependencyUpdateList, List<TemplateDependencyVO> dependencyDeleteList){
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);
        Map<String,Object> templateMap = new HashMap<String,Object>();
        templateMap.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, wbsTemplateMasterDom);
        if(wbsItemTemplateVO instanceof WBSPhaseTemplateVO){
            WBSItemTemplates wbsItemTemplates = new WBSItemTemplates(wbsItemTemplateVO.getObid());
            wbsItemTemplates.getVo().setCompleteValidationMethod(wbsItemTemplateVO.getCompleteValidationMethod());
            wbsItemTemplates.getVo().setPostExecutionMethod(wbsItemTemplateVO.getPostExecutionMethod());
            wbsItemTemplates.getVo().setStartValidationMethod(wbsItemTemplateVO.getStartValidationMethod());
            wbsItemTemplates.getVo().setStartExecutionMethod(wbsItemTemplateVO.getStartExecutionMethod());
            wbsItemTemplates.getVo().setSkipInfo(wbsItemTemplateVO.getSkipInfo());
            wbsItemTemplates.getVo().setActivityNameChi(wbsItemTemplateVO.getActivityNameChi());
            wbsItemTemplates.getVo().setActivityNameEng(wbsItemTemplateVO.getActivityNameEng());
            wbsItemTemplates.getVo().setActivityNameKor(wbsItemTemplateVO.getActivityNameKor());
            wbsItemTemplates.modifyObject(templateMap);
        }else{
            WBSItemTemplates wbsItemTemplates = new WBSItemTemplates(wbsItemTemplateVO);
            wbsItemTemplates.modifyObject(templateMap);
            if(wbsItemTemplateVO instanceof WBSActivityTemplateVO){
                WBSActivityTemplate wbsActivityTemplate = new WBSActivityTemplate((WBSActivityTemplateVO)wbsItemTemplateVO);
                wbsActivityTemplate.updateProjectRole(wbsTemplateMasterDom, (String)wbsItemTemplateVO.getOutData().get("roleCodeList"));
                txnUpdateTemplateDependency(wbsTemplateMasterDom,wbsItemTemplateVO.getObid(), dependencyCreateList, dependencyUpdateList, dependencyDeleteList);
                
                ActivityTemplateMasterVO oldActivityTemplateMasterVO = wbsItemTemplates.getRelatedTemplateMaster();
                boolean isChanged = true;
                if(!NullUtil.isNull(oldActivityTemplateMasterVO)){
                    if(!oldActivityTemplateMasterVO.getNames().equals(wbsItemTemplateVO.getActivityMasterName())){
                        new RelatedTemplateMaster((String)oldActivityTemplateMasterVO.getOutData().get("rel_obid")).deleteObject();
                    }else{
                        isChanged = false;
                    }
                }
                if(!NullUtil.isNone(activityMasterObid) && !"null".equals(activityMasterObid) && isChanged){
                    ActivityTemplateMasterVO newActivityTemplateMasterVO = new ActivityTemplateMaster(activityMasterObid).getVo();
                    wbsActivityTemplate.addToObject(ApplicationSchemaConstants.RELCLASS_RELATEDTEMPLATEMASTER, newActivityTemplateMasterVO, null);
                }
            }
        }
    }
    
    /**
     * 
     * @param hasActivityTemplateVOList
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnUpateTemplateSequences(java.util.List)
     */
    @Override
    public void txnUpateTemplateSequences(List<HasActivityTemplateVO> hasActivityTemplateVOList){
        SortUtil.sort(hasActivityTemplateVOList, "sequences", false);
        int defaultIndex = 1;
        for(HasActivityTemplateVO hasActivityTemplateVO : hasActivityTemplateVOList){
            hasActivityTemplateVO.setSequences(hasActivityTemplateVO.getSequences() * defaultIndex);
            new HasActivityTemplate(hasActivityTemplateVO).modifyObject();
        }
    }
    
    /**
     * 
     * @param templateObid
     * @param documentCreateList
     * @param documentUpdateList
     * @param documentDeleteList
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnUpdateProjectActivityDocumentTemplate(java.lang.String, java.util.List, java.util.List, java.util.List)
     */
    @Override
    public void txnUpdateProjectActivityDocumentTemplate(String wbsTemplateMasterObid, String templateObid, List<RecommendedDocumentTemplateVO> documentCreateList, List<RecommendedDocumentTemplateVO> documentUpdateList, List<RecommendedDocumentTemplateVO> documentDeleteList){
        
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);
        WBSTemplateMasterVO wbsTemplateMasterVO = wbsTemplateMasterDom.getVo();
        Map<String,Object> templateMap = new HashMap<String,Object>();
        templateMap.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, wbsTemplateMasterDom);
        
        if(!NullUtil.isNone(documentDeleteList)){
            for(RecommendedDocumentTemplateVO recommendedDocumentTemplateVO : documentDeleteList){
                new RecommendedDocumentTemplate(recommendedDocumentTemplateVO).deleteObject(templateMap);
            }
        }
        
        if(!NullUtil.isNone(documentCreateList)){
            WBSActivityTemplate wbsActivityTemplate = new WBSActivityTemplate(templateObid);
            WBSItemTemplatesVO wbsItemTemplatesVO = wbsActivityTemplate.getWBSPhase(wbsTemplateMasterObid, false);
            for(RecommendedDocumentTemplateVO recommendedDocumentTemplateVO : documentCreateList){
                recommendedDocumentTemplateVO.setFromObid(templateObid);
                if(wbsItemTemplatesVO != null){
                    if(ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE.equals(wbsItemTemplatesVO.getClassName())){
                        recommendedDocumentTemplateVO.setPhaseName(((WBSPhaseTemplateVO)wbsItemTemplatesVO).getProjectPhaseName());
                    }else if (ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTTEMPLATE.equals(wbsItemTemplatesVO.getClassName())){
                        recommendedDocumentTemplateVO.setPhaseName(wbsItemTemplatesVO.getActivityNameEng());
                    }
                }
                new RecommendedDocumentTemplate(recommendedDocumentTemplateVO).createObject(templateMap);
                
                // Monitor: GMZ, PC: PCZ, LCD TV: GLZ
                //PTDOCTEMPLATEMASTER[ProjectActivityDocumentTemplate] 의 Obid 필요. HE:TV, 모니터, PC    
                if( "GMZ".equals(wbsTemplateMasterVO.getDivisionUnit()) || "PCZ".equals(wbsTemplateMasterVO.getDivisionUnit()) || "GLZ".equals(wbsTemplateMasterVO.getDivisionUnit()) ){
                    IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO((BusinessObjectRootVO)DomUtil.toDom(recommendedDocumentTemplateVO.getToObid()).getVo(), ApplicationBizConstants.DOCTEMP_CREATE_TRIGGER_LIST[0][0], ApplicationBizConstants.DOCTEMP_CREATE_TRIGGER_LIST[0][1]);
                    TriggerUtil.createDataByTrigger(triggerVO);
                }
            }
            
        }

        if(!NullUtil.isNone(documentUpdateList)){
            for(RecommendedDocumentTemplateVO recommendedDocumentTemplateVO : documentUpdateList){
                new RecommendedDocumentTemplate(recommendedDocumentTemplateVO).modifyObject(templateMap);
                
                // Monitor: GMZ, PC: PCZ, LCD TV: GLZ
                //PTDOCTEMPLATEMASTER[ProjectActivityDocumentTemplate] 의 Obid 필요. HE:TV, 모니터, PC    
                if( "GMZ".equals(wbsTemplateMasterVO.getDivisionUnit()) || "PCZ".equals(wbsTemplateMasterVO.getDivisionUnit()) || "GLZ".equals(wbsTemplateMasterVO.getDivisionUnit()) ){
                    IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO((BusinessObjectRootVO)DomUtil.toDom(recommendedDocumentTemplateVO.getToObid()).getVo(), ApplicationBizConstants.DOCTEMP_CREATE_TRIGGER_LIST[0][0], ApplicationBizConstants.DOCTEMP_CREATE_TRIGGER_LIST[0][1]);
                    TriggerUtil.createDataByTrigger(triggerVO);
                }
            }
        }
        
    }

    /**
     * 
     * @param wbsTemplateMasterObid
     * @param createList
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCreateWBSTemplateDependency(java.lang.String, java.util.List)
     */
    @Override
    public void txnCreateWBSTemplateDependency(String wbsTemplateMasterObid, List<TemplateDependencyVO> createList){
        
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, wbsTemplateMasterDom);
        for(TemplateDependencyVO templateDependencyVO : createList){
            new TemplateDependency(templateDependencyVO).createObject(map);
        }
    }    
    /**
     * 
     * @param templateObid
     * @param dependencyCreateList
     * @param dependencyUpdateList
     * @param dependencyDeleteList
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnUpdateTemplateDependency(java.lang.String, java.util.List, java.util.List, java.util.List)
     */
    @Override
    public void txnUpdateTemplateDependency(WBSTemplateMaster wbsTemplateMasterDom, String templateObid, List<TemplateDependencyVO> dependencyCreateList, List<TemplateDependencyVO> dependencyUpdateList, List<TemplateDependencyVO> dependencyDeleteList){
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, wbsTemplateMasterDom);
        
        if(!NullUtil.isNone(dependencyCreateList)){
            for(TemplateDependencyVO templateDependencyVO : dependencyCreateList){
                templateDependencyVO.setFromObid(templateObid);
                new TemplateDependency(templateDependencyVO).createObject(map);
            }
        }
        
        if(!NullUtil.isNone(dependencyUpdateList)){
            for(TemplateDependencyVO templateDependencyVO : dependencyUpdateList){
                new TemplateDependency(templateDependencyVO).modifyObject(map);
            }
        }

        if(!NullUtil.isNone(dependencyDeleteList)){
            for(TemplateDependencyVO templateDependencyVO : dependencyDeleteList){
                new TemplateDependency(templateDependencyVO).deleteObject(map);
            }
        }
        
    }
    
    /**
     * 
     * @param obid
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnDeleteWBSActivityTemplate(java.lang.String)
     */
    @Override
    public void txnDeleteWBSItemTemplate(WBSTemplateMaster wbsTemplateMasterDom, String obidList){
        String[] obidArray = obidList.split(",");
        for(int i = 0; i < obidArray.length; i++){
            String obid = obidArray[i];
            Map<String,Object> map = new HashMap<String,Object>();
            map.put(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom, wbsTemplateMasterDom);
            //.isKindOf(ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES
            BusinessObjectRoot bizRootDom = new BusinessObjectRoot(obid);
            if(bizRootDom.getVo() instanceof WBSItemTemplatesVO){
                WBSItemTemplates wbsItemTemplatesDom = new WBSItemTemplates(obid);
                List<WBSItemTemplatesVO> subItemList = wbsItemTemplatesDom.getSubWBSItemTemplate(wbsTemplateMasterDom.getObid(), false, 1);
                for(WBSItemTemplatesVO subItemVO : subItemList){
                    txnDeleteWBSItemTemplate(wbsTemplateMasterDom,subItemVO.getObid());
                }
                if(wbsItemTemplatesDom.isConnectedOtherContext(wbsTemplateMasterDom.getObid())){
                    WBSManagedByVO wbsManagedByVO = wbsItemTemplatesDom.getWBSManagedBy(wbsTemplateMasterDom.getObid());
                    WBSManagedBy wbsManagedByDom = new WBSManagedBy(wbsManagedByVO);
                    wbsManagedByDom.deleteObject(map);
                }else{
                    wbsItemTemplatesDom.deleteObject(map);
                }
            }else if(bizRootDom.getVo() instanceof JobActivityTemplateVO){
                JobActivityTemplate jobActivityTemplateDom = new JobActivityTemplate(obid);
                if(!jobActivityTemplateDom.isConnectedOtherContext(wbsTemplateMasterDom.getObid())) {
                    jobActivityTemplateDom.deleteObject(map);
                }else{
                    WBSActivityTemplateVO wbsActivityTemplateVO = jobActivityTemplateDom.getWBSActivityTemplate(wbsTemplateMasterDom.getObid());
                    WBSActivityTemplate wbsActivityTemplateDom = new WBSActivityTemplate(wbsActivityTemplateVO);
                    if(jobActivityTemplateDom.isConnectedOtherActivity(wbsActivityTemplateVO.getObid())){
                        HasJobActivityTemplateVO hasJobActivityTemplateVO = jobActivityTemplateDom.getHasJobActivityTemplate(wbsActivityTemplateVO.getObid());
                        HasJobActivityTemplate hasJobActivityTemplateDOM = new HasJobActivityTemplate(hasJobActivityTemplateVO);
                        hasJobActivityTemplateDOM.deleteObject(map);
                    }else{
                        throw new ApplicationException("Can not delete job activity. try it after revise activity ("+ wbsActivityTemplateDom.getDisplayNameAsLoc() + " ).");
                    }
                }
            }else{
                throw new ApplicationException(bizRootDom.getClassName() + " is not supported.");
            }
        }
    }

    /**
     * 
     * @param wbsTemplateMasterObid
     * @param obid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnReviseWBSItemTemplate(java.lang.String, java.lang.String)
     */
    @Override
    public WBSItemTemplatesVO txnReviseWBSItemTemplate(String wbsTemplateMasterObid, String obidList){
        
        String[] obidArray = obidList.split(",");
        for(int i = 0; i < obidArray.length; i++){
            WBSItemTemplates wbsTemplateDom = DomUtil.toDom(obidArray[i]);
            WBSTemplateMaster wbsTemplateMasterDom = DomUtil.toDom(wbsTemplateMasterObid);

            WBSItemTemplatesVO wbsItemTemplatesVO = null;
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,true);
            try{
                wbsItemTemplatesVO = wbsTemplateDom.reviseTemplate(wbsTemplateMasterDom);
            }finally{
                ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,false);
            } 
        }
        return null;
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @param obid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnReviseWBSItemTemplate(java.lang.String)
     */
    @Override
    public void txnAllReviseWBSItemTemplate(String wbsTemplateMasterObid){
        WBSTemplateMaster wbsTemplateMasterDom = DomUtil.toDom(wbsTemplateMasterObid);
        
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,true);
        try{
            List<WBSItemTemplatesVO> workingWBSItemTemplateList = wbsTemplateMasterDom.getWBSItemTemplates(false);
            WBSItemTemplates wbsTemplateDom = null;
            for(WBSItemTemplatesVO vo : workingWBSItemTemplateList){
                if(vo.getPreviousObid().equals("1") && vo.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_ACTIVE)){
                    wbsTemplateDom = new WBSItemTemplates(vo);
                    wbsTemplateDom.reviseTemplate(wbsTemplateMasterDom);
                }
            }
        }finally{
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,false);
        }
    }

    /**
     * 
     * @param wbsTemplateMasterObid
     * @param obid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCancelReviseWBSItemTemplate(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unused")
    @Override
    public WBSItemTemplatesVO txnCancelReviseWBSItemTemplate(String wbsTemplateMasterObid, String obidList){
        
        String[] obidArray = obidList.split(",");
        for(int i = 0; i < obidArray.length; i++){
            String obid = obidArray[i];
            WBSItemTemplates wbsTemplateDom = new WBSItemTemplates(obidArray[i]);
            WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);
            WBSItemTemplatesVO wbsItemTemplatesVO = wbsTemplateDom.cancelReviseTemplate(wbsTemplateMasterDom);
        }
        return null;
    }

    /**
     * 
     * @param wbsTemplateMasterObid
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCreateSpareWBSItemsList(java.lang.String)
     */
    @Override
    public void txnCreateSpareWBSItemsList(String wbsTemplateMasterObid){
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);
        ProjectScheduleVO projectScheduleVO = new ProjectScheduleVO();
        projectScheduleVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_PROJECT_SCHEDULE));
        projectScheduleVO.setTitles(wbsTemplateMasterDom.getNames());
        projectScheduleVO.setRevision("1");
        ProjectSchedule projectSchedule = new ProjectSchedule(projectScheduleVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ProjectConstants.PROJECT_OMAP_KEY_TEMPLATE_NASTER_VO, wbsTemplateMasterDom.getVo());
        map.put("forSpare", true);
        projectSchedule.createObject(map);
        wbsTemplateMasterDom.createSpareWBSItemsList(projectScheduleVO);
    }
    
    /**
     * 
     * @param activityTemplateObid
     * @param jobActivityTemplateVO
     * @param addingPosition
     * @param targetSequence
     * @see lge.plm.project.wbs.service.txnCreateJobActivityTemplate#txnCreateJobActivityTemplate(java.lang.String, lge.plm.api.object.project.model.JobActivityTemplateVO, java.lang.String, java.lang.Integer)
     */
    @Override
    public void txnCreateJobActivityTemplate(String wbsTemplateMasterObid, String activityTemplateObid, JobActivityTemplateVO jobActivityTemplateVO, String addingPosition, Integer targetSequence){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wbsTemplateMasterObid", wbsTemplateMasterObid);
        map.put("activityTemplateObid", activityTemplateObid);
        JobActivityTemplate jobActivityTemplate = new JobActivityTemplate(jobActivityTemplateVO);
        jobActivityTemplate.setDefault(true);
        jobActivityTemplate.createObject(map);
    }

    /**
     * 
     * @param jobActivityTemplateVO
     * @see lge.plm.project.wbs.service.txnUpdateJobActivityTemplates#txnUpdateJobActivityTemplate(lge.plm.api.object.project.model.JobActivityTemplateVO)
     */
    @Override
    public void txnUpdateJobActivityTemplate(JobActivityTemplateVO jobActivityTemplateVO){
        new JobActivityTemplate(jobActivityTemplateVO).modifyObject();
    }

    /**
     * 
     * @param activityTemplateMasterVO
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCreateActivityTemplateMaster(lge.plm.api.object.project.model.ActivityTemplateMasterVO)
     */
    @Override
    public void txnCreateActivityTemplateMaster(ActivityTemplateMasterVO activityTemplateMasterVO){
        activityTemplateMasterVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_ACTIVITY_TEMPLATE_MASTER_NAME));
        activityTemplateMasterVO.setClassName(ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER);
        activityTemplateMasterVO.setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_ACTIVITY_TEMPLATE_MASTER);
        activityTemplateMasterVO.setStates(ApplicationSchemaConstants.STATE_ACTIVITY_TEMPLATE_MASTER_WORKING);
        activityTemplateMasterVO.setTitles(activityTemplateMasterVO.getActivityNameEng());
        ActivityTemplateMaster activityTemplateMaster = new ActivityTemplateMaster(activityTemplateMasterVO);
        Map<String, Object> map = new HashMap<String, Object>();
        activityTemplateMaster.createObject(map);
        
        String defaultRoleListObid = (String)activityTemplateMasterVO.getOutData().get("defaultRoleListObid");
        activityTemplateMaster.updateProjectRole(defaultRoleListObid);

    }

    /**
     * 
     * @param activityTemplateMasterVO
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnUpdateActivityTemplateMaster(lge.plm.api.object.project.model.ActivityTemplateMasterVO)
     */
    @Override
    public void txnUpdateActivityTemplateMaster(ActivityTemplateMasterVO activityTemplateMasterVO){
        ActivityTemplateMaster activityTemplateMaster = new ActivityTemplateMaster(activityTemplateMasterVO);
        activityTemplateMaster.modifyObject();
        String defaultRoleListObid = (String)activityTemplateMasterVO.getOutData().get("defaultRoleListObid");
        activityTemplateMaster.updateProjectRole(defaultRoleListObid);
    }

    /**
     * 
     * @param wbsItemTemplatesObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveTemplateDependency(java.lang.String)
     */
    @Override
    public List<BusinessRelationObjectVO> getTemplateDependency(String wbsTemplateMasterObid,String wbsItemTemplatesObid){
        return new WBSItemTemplates( wbsItemTemplatesObid ).getTemplateDependency(wbsTemplateMasterObid);
    }

    /**
     * 
     * @param wbsItemTemplatesObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveProjectActivityDocumentTemplate(java.lang.String)
     */
    @Override
    public List<BusinessRelationObjectVO> retrieveRecomendedDocumentTemplate(String wbsTemplateMasterObid,String wbsItemTemplatesObid){
        return new WBSItemTemplates( wbsItemTemplatesObid ).retrieveRecomendedDocumentTemplate(wbsTemplateMasterObid);
    }
    
    /**
     * 
     * @param wbsTemplateMasterObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveUsedRoleAtWBSTemplate(java.lang.String)
     */
    @Override
    public List<ProjectRoleVO> retrieveUsedRoleAtWBSTemplate(String wbsTemplateMasterObid){
        return new ActivityTemplateMaster(wbsTemplateMasterObid).retrieveUsedRoleAtWBSTemplate();
    }


    /**
     * 
     * @param wbsItemTemplateObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveAllocatedRoleToWBSTemplateItems(java.lang.String)
     */
    @Override
    public List<ProjectRoleVO> retrieveAllocatedRoleToWBSTemplateItems(String wbsItemTemplateObid){
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsItemTemplateObid);
        return new WBSActivityTemplate( wbsItemTemplateObid ).getAllocatedRole(wbsTemplateMasterDom);
    }

    
    /**
     * 
     * @param searchInfo
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveActivityTemplateMasterList(lge.plm.project.wbs.model.ActivityTemplateMasterSearchVO)
     */
    @Override
    public List<ActivityTemplateMasterVO> retrieveActivityTemplateMasterList(ActivityTemplateMasterVO searchInfo){
        return ActivityTemplateMaster.retrieveActivityTemplateMasterList(searchInfo,"",false);
    }

    /**
     * 
     * @param searchInfo
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveProjectActivityDocumentTemplateList(lge.plm.api.object.project.model.ProjectActivityDocumentTemplateSearchVO)
     */
    @Override
    public List<ProjectActivityDocumentTemplateVO> retrieveProjectActivityDocumentTemplateList(ProjectActivityDocumentTemplateSearchVO searchInfo){
//        if(searchInfo.getFromFDR()){
//            return ProjectActivityDocumentTemplate.retrieveProjectActivityDocumentTemplateListForFDR(searchInfo);
//        }
//        return ProjectActivityDocumentTemplate.retrieveProjectActivityDocumentTemplateList(searchInfo);
        return null;
    }

    /**
     * 
     * @param fromMasterObid
     * @param targetMasterVO
     * @see lge.plm.project.wbs.service.WBSTemplateService#cloneWbsTemplateMaster(java.lang.String, lge.plm.api.object.project.model.WBSTemplateMasterVO)
     */
    @Override
    public void txnCloneWbsTemplateMaster(String fromMasterObid, WBSTemplateMasterVO targetMasterVO){
        WBSTemplateMaster targetMaster = new WBSTemplateMaster(targetMasterVO);
        targetMaster.setDefault(true);
        targetMaster.createObject();
        new WBSTemplateMaster(fromMasterObid).cloneWbsTemplateMaster(targetMasterVO);
    }

    /**
     * 
     * @param wbsTemplateMasterVO
     * @param ganttActivityList
     * @param linkList
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#retrieveWBSTemplateMasterList(lge.plm.api.object.project.model.WBSTemplateMasterVO, java.util.List, java.util.List)
     */
    @Override
    public void getWBSTemplateListForGantt(WBSTemplateMasterVO wbsTemplateMasterVO, List<DHTMLGanttTemplateActivityVO> ganttActivityList, List<DHTMLGanttLinkVO> linkList){
        WBSTemplateMaster templateMasterDom = DomUtil.toDom(wbsTemplateMasterVO);
        List<WBSItemTemplatesVO> list = templateMasterDom.getWBSItemTemplatesStructure(10);
        List<TemplateDependencyVO> dependencyList = WBSTemplateMaster.getWBSTemplatePreviousDependency(list,templateMasterDom.getObid());
        SortUtil.sort(list, "uniqueString", false);
        int sortOrder = 1;
        
        for(WBSItemTemplatesVO vo : list ){
            vo.setOutDataAttributeValue(ProjectConstants.GANTT_SORT_ORDER_KEY, sortOrder++);
            if(vo.getIsMilestone().equals("Y")){
                vo.setStandardDuration(0);
            }else{
                vo.setStandardDuration(1);
            }
        }
        while(true){
            List<WBSItemTemplatesVO> independList = WBSTemplateUtil.getIndependentActivityList(list,dependencyList,ganttActivityList);
            if(independList.size() == 0) break;
            for(WBSItemTemplatesVO templateVO : independList){
                calculateStartDate(templateVO,list,dependencyList,ganttActivityList,linkList);
            }
            for(TemplateDependencyVO dependencyVO : dependencyList){
                String dependencyType = dependencyVO.getDependencyType();
                if(!dependencyVO.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER)){
                    linkList.add(new DHTMLGanttLinkVO(dependencyVO.getObid(), dependencyVO.getToObid(), dependencyVO.getFromObid(), dependencyType));
                }
            }
        }
    }
    private void calculateStartDate(WBSItemTemplatesVO                 templateVO, 
                                    List<WBSItemTemplatesVO>           list,
                                    List<TemplateDependencyVO>         dependencyList,
                                    List<DHTMLGanttTemplateActivityVO> calculatedList,
                                    List<DHTMLGanttLinkVO>             linkList){
        Integer duration = templateVO.getStandardDuration();
        //if(templateVO.getIsMilestone().equals("Y")) duration = 1;
        Date startDate = new Date();
        Date endDate   = new Date();
        Date tempStartDate = new Date();
        for(TemplateDependencyVO dependencyVO : dependencyList){
            if(dependencyVO.getFromObid().equals(templateVO.getObid())){
                DHTMLGanttTemplateActivityVO tempTemplateVO = getGanttActivity(dependencyVO.getToObid(),calculatedList);
                String dependencyType = dependencyVO.getDependencyType();
                Date dependencyDate = tempTemplateVO.getEndDate();
                if(dependencyType.equals("FS")){
                    tempStartDate = dependencyDate;
                }else if(dependencyType.equals("SS")){
                    tempStartDate = dependencyDate;
                }
                if(startDate.compareTo(tempStartDate) > 0){
                    tempStartDate = startDate;
                }
            }
        }
        startDate = tempStartDate;
        endDate = TimeServiceUtil.addDays(startDate,duration);
        DHTMLGanttTemplateActivityVO addedVO = WBSTemplateUtil.convertToDHTMLGanttActivityVO(templateVO,list,startDate,endDate,duration,true);
        calculatedList.add(addedVO);
    }
    /**
     * 선행작업 결과를 기준으로 Plan Date Setting
     *
     * @param wbsItemsVO
     * @param list
     * @param dependencyList
     * @param calculatedList
     */
    @SuppressWarnings("unused")
    private void calculateWBSPlanDate( WBSItemsVO                 wbsItemsVO    , 
                                       List<WBSItemsVO>           list          ,
                                       List<WBSDependencyVO>      dependencyList,
                                       List<WBSItemsVO>           calculatedList){
        Integer duration = wbsItemsVO.getPlanDuration();
        Date planStartDate = new Date();
        Date planEndDate   = new Date();
        Date tempStartDate = new Date();
       for(WBSDependencyVO dependencyVO : dependencyList){
           if(dependencyVO.getFromObid().equals(wbsItemsVO.getObid())){
               WBSItemsVO calculatedTemplateVO = getCalculatedWBSItems(dependencyVO.getToObid(),calculatedList);
               String dependencyType = dependencyVO.getDependencyType();
               Date dependencyDate = calculatedTemplateVO.getPlanEndDate();
               if(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START.equals(dependencyType)){
                   tempStartDate = dependencyDate;
               }else if(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START.equals(dependencyType)){
                   tempStartDate = dependencyDate;
               }
               if(planStartDate.compareTo(tempStartDate) > 0){
                   tempStartDate = planStartDate;
               }
           }
       }
       planStartDate = tempStartDate;
       planEndDate   = TimeServiceUtil.addDays(planStartDate,duration);
       wbsItemsVO.setPlanStartDate(planStartDate);
       wbsItemsVO.setPlanEndDate(planEndDate);
       calculatedList.add(wbsItemsVO);
    }
    
    private WBSItemsVO getCalculatedWBSItems(String obid, List<WBSItemsVO> calculatedList){
        for(WBSItemsVO vo :  calculatedList){
            if(vo.getObid().equals(obid)) return vo;
        }
        return null;
    }
    
    private DHTMLGanttTemplateActivityVO getGanttActivity(String obid, List<DHTMLGanttTemplateActivityVO> calculatedList){
        for(DHTMLGanttTemplateActivityVO vo :  calculatedList){
            if(vo.getId().equals(obid)) return vo;
        }
        return null;
    }
    /**
     * 
     * @param obid
     * @param includeDoc
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#exportWBSTemplateToExcel(java.lang.String, boolean)
     */
//    @Override
//    public ExcelDownloadView exportWBSTemplateToExcel(String wbsTemplateMasterObid, boolean includeDoc,boolean activeOnly) throws Exception{
//        
//        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(wbsTemplateMasterObid);
//        WBSTemplateMasterVO wbsTemplateMasterVO = wbsTemplateMaster.getVo();
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        String yyyymmdd = format.format(new Date());
//        String fileName = "WBSList_"+wbsTemplateMasterVO.getDivisionUnit()+"_"+wbsTemplateMasterVO.getDevelopmentType()+"("+yyyymmdd+(includeDoc ? "_includeDoc" : "")+")";
//        fileName = WorkbookUtil.createSafeSheetName(fileName);
//        List<ProjectGradeVO> validGradeListForTemplateMaster = getValidGradeListForTemplateMaster(wbsTemplateMasterObid,activeOnly);
//        JSONArray fields = getExportWBSTemplateToExcelFields(includeDoc, validGradeListForTemplateMaster);
//        JSONArray records   = getExportWBSTemplateToExcelRecords(wbsTemplateMasterObid, includeDoc, validGradeListForTemplateMaster, true);
//        JSONObject workbook = getExportWBSTemplateToExcelWorkbook(fileName, fields, records);
//        
//        POIManager poiManager = new POIManager();
//        poiManager.writeWorkbook(workbook);
//        ExcelDownloadView excelDownloadView = new ExcelDownloadView( poiManager.getWorkbook(), poiManager.getDownloadFileName() );
//        return excelDownloadView;
//        
//    }
    /**
     * 
     * @param obid
     * @param includeDoc
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#exportWBSTemplateToExcel(java.lang.String, boolean)
     */
//    @Override
//    public ExcelDownloadView exportWBSTemplateForImport(String wbsTemplateMasterObid, boolean includeDoc,boolean activeOnly) throws Exception{
//        
//        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(wbsTemplateMasterObid);
//        WBSTemplateMasterVO wbsTemplateMasterVO = wbsTemplateMaster.getVo();
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        String yyyymmdd = format.format(new Date());
//        String fileName = "WBSImport_"+wbsTemplateMasterVO.getDivisionUnit()+"_"+wbsTemplateMasterVO.getDevelopmentType()+"("+yyyymmdd+(includeDoc ? "_includeDoc" : "")+")";
//        fileName = WorkbookUtil.createSafeSheetName(fileName);
//        List<ProjectGradeVO> validGradeListForTemplateMaster = getValidGradeListForTemplateMaster(wbsTemplateMasterObid,activeOnly);
//        JSONArray fields = getExportWBSTemplateToImportFields(includeDoc, validGradeListForTemplateMaster);
//        JSONArray records   = getExportWBSTemplateToExcelRecords(wbsTemplateMasterObid, includeDoc, validGradeListForTemplateMaster, false);
//        JSONObject workbook = getExportWBSTemplateToExcelWorkbook(fileName, fields, records);
//        
//        POIManager poiManager = new POIManager();
//        poiManager.writeWorkbook(workbook);
//        ExcelDownloadView excelDownloadView = new ExcelDownloadView( poiManager.getWorkbook(), poiManager.getDownloadFileName() );
//        return excelDownloadView;
//    }

//    private JSONObject getExportWBSTemplateToExcelWorkbook(String fileName, JSONArray fields, JSONArray records){
//        JSONObject tabularData = new JSONObject();
//        JSONObject extraData = new JSONObject();
//
//        JSONArray header = new JSONArray();
//        header.add( fields );
//        tabularData.put("header", header);
//
//        tabularData.put("fields", fields);
//        tabularData.put("records", records);
//        JSONObject worksheet = new JSONObject();
//        worksheet.put("tabularData", tabularData);
//        worksheet.put("extraData", extraData);
//        worksheet.put("title", fileName);
//        JSONArray worksheets = new JSONArray();
//        worksheets.add(worksheet);
//        JSONObject workbook = new JSONObject();
//        workbook.put("worksheets", worksheets);
//        workbook.put("fileName", fileName);
//        workbook.put("version", POIManager.VERSION_2007);
//        return workbook;
//    }
    private JSONArray getExportWBSTemplateToExcelRecords(String wbsTemplateMasterObid, boolean includeDoc, List<ProjectGradeVO> validGradeListForTemplateMaster, boolean includeJob){
        String userLocale = userSession.getUserLocale();
        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(wbsTemplateMasterObid);
        WBSTemplateMasterVO wbsTemplateMasterVO = wbsTemplateMaster.getVo();
        String divisionUnitCode = wbsTemplateMasterVO.getDivisionUnit();
        List<BusinessObjectRootVO> wbsItemTemplatesStructure = wbsTemplateMaster.getWBSItemTemplatesStructureWithJobActivity();
        SortUtil.sort(wbsItemTemplatesStructure, "uniqueString", false);
        List<BusinessObjectRootVO> wbsActivityTemplateVOList = new ArrayList<BusinessObjectRootVO>();
        for(int i=0; i<wbsItemTemplatesStructure.size(); i++){
            BusinessObjectRootVO wbsItemTemplatesVO = wbsItemTemplatesStructure.get(i);
            if (ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE.equals(wbsItemTemplatesVO.getClassName())){
                wbsActivityTemplateVOList.add((WBSItemTemplatesVO)DomUtil.copy( wbsItemTemplatesVO ));
            }
        }
        List<BusinessObjectRootVO> allRelatedDocNRole = null; 
        if(wbsActivityTemplateVOList.size() > 0){
            allRelatedDocNRole = BusinessObject.getRelatedObjectSet( wbsActivityTemplateVOList, 
                                                                ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE+","+ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLETOWBSTEMPLATEITEMS,
                                                                ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+","+ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, 
                                                                GlobalConstants.FLAG_TYPE_TO, true, false);
        }
        List<String> docTemplateObidList = new ArrayList<String>();
        Map<String, List<BusinessObjectRootVO>> allRelatedDoc = new HashMap<String, List<BusinessObjectRootVO>>();
        Map<String, List<BusinessObjectRootVO>> allRelatedRole = new HashMap<String, List<BusinessObjectRootVO>>();
        for(int j=allRelatedDocNRole.size()-1; j>=0; j--){
            String uniqueStringParent = allRelatedDocNRole.get(j).getUniqueStringParent();
                if(ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE.equals(allRelatedDocNRole.get(j).getClassName())){
                    docTemplateObidList.add(allRelatedDocNRole.get(j).getObid());
                    List<BusinessObjectRootVO> relatedDocList = allRelatedDoc.get(uniqueStringParent);
                    if(relatedDocList == null){
                        relatedDocList = new ArrayList<BusinessObjectRootVO>();
                        allRelatedDoc.put(uniqueStringParent, relatedDocList);
                    }
                    relatedDocList.add(allRelatedDocNRole.get(j));
                } else if(ApplicationSchemaConstants.BIZCLASS_PROJECTROLE.equals(allRelatedDocNRole.get(j).getClassName())){
                    List<BusinessObjectRootVO> relatedRoleList = allRelatedRole.get(uniqueStringParent);
                    if(relatedRoleList == null){
                        relatedRoleList = new ArrayList<BusinessObjectRootVO>();
                        allRelatedRole.put(uniqueStringParent, relatedRoleList);
                    }
                    relatedRoleList.add(allRelatedDocNRole.get(j));
                }
        }
        Map<String, String> fileNamesForExcel = ProjectActivityDocumentTemplate.getFileNamesForExcel(StrUtil.convertList2Str(docTemplateObidList), divisionUnitCode);
        JSONArray records = new JSONArray();
        int activityTemplateDepth = 0;
        for(int i=0; i<wbsItemTemplatesStructure.size(); i++){
            if(ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE.equals(wbsItemTemplatesStructure.get(i).getClassName())
                    || ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE.equals(wbsItemTemplatesStructure.get(i).getClassName())
                    || ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTTEMPLATE.equals(wbsItemTemplatesStructure.get(i).getClassName())){
                WBSItemTemplatesVO wbsItemTemplatesVO = (WBSItemTemplatesVO)wbsItemTemplatesStructure.get(i);
                
                String uniqueString = wbsItemTemplatesVO.getUniqueString();
                List<BusinessObjectRootVO> relatedDocList = allRelatedDoc.get(uniqueString);
                List<BusinessObjectRootVO> relatedRoleList = allRelatedRole.get(uniqueString);
                String relatedRoleNames = "";
                String relatedRoleTitles = "";
                if(relatedRoleList != null){
                    for (BusinessObjectRootVO relatedRoleVO : relatedRoleList) {
                        if(StrUtil.isNotEmpty(relatedRoleNames)){
                            relatedRoleNames += ",";
                            relatedRoleTitles += ",";
                        }
                        ProjectRoleVO projectRoleVO = (ProjectRoleVO)relatedRoleVO;
                        relatedRoleNames += projectRoleVO.getNames();
                        relatedRoleTitles += ApplicationBizConstants.LANG_KO.equals(userLocale) ? projectRoleVO.getRoleNameKor() 
                                : ( ApplicationBizConstants.LANG_CH.equals(userLocale) ? projectRoleVO.getRoleNameChi()
                                        :  projectRoleVO.getRoleNameEng() );
                    }
                }
                
                String DIVISION =           wbsTemplateMasterVO.getDivisionUnit();
                String WBS_GUBUN =          wbsTemplateMasterVO.getDevelopmentType();
                int LEV =                   wbsItemTemplatesVO.getExplodedDepth()-1;
                String ACTIVITY_CODE =      wbsItemTemplatesVO.getActivityMasterName();
                String ACTIVITY_NAME =      ApplicationBizConstants.LANG_KO.equals(userLocale) ? wbsItemTemplatesVO.getActivityNameKor() 
                        : ( ApplicationBizConstants.LANG_CH.equals(userLocale) ? wbsItemTemplatesVO.getActivityNameChi() 
                                : wbsItemTemplatesVO.getActivityNameEng() );
                String ACTIVITY_NAME_ENG =  wbsItemTemplatesVO.getActivityNameEng();
                int SORTING =               (int)wbsItemTemplatesVO.getOutData().get("rel_sequence");
                String UPPER_ACTIVITY =     wbsItemTemplatesVO.getActivityMasterNameUpper();
                String MILESTONE_YN =       wbsItemTemplatesVO.getIsMilestone();
                int BASE_ST =               wbsItemTemplatesVO.getStandardDuration();
                String ROLE_CODE =          relatedRoleNames;
                String ROLE_NAME =          relatedRoleTitles;
                String INSTRUCTION =        wbsItemTemplatesVO.getInstruction();
                String skipInfo =           wbsItemTemplatesVO.getSkipInfo();
                String wbsItemType =        "";
                
                if(ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE.equals(wbsItemTemplatesVO.getClassName())){
                    wbsItemType = "Event";
                }else if(ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTTEMPLATE.equals(wbsItemTemplatesVO.getClassName())){
                    wbsItemType = "Sub Event";
                }else{
                    wbsItemType = "Activity";
                }
                activityTemplateDepth = wbsItemTemplatesStructure.get(i).getExplodedDepth()-1;
                
                JSONObject record = new JSONObject();
                record.put("DIVISION",          DIVISION);
                record.put("WBS_GUBUN",         WBS_GUBUN);
                record.put("LEV",               LEV);
                record.put("ACTIVITY_OBID",     wbsItemTemplatesVO.getObid());
                record.put("TYPE",              wbsItemType);
                record.put("ACTIVITY_CODE",     ACTIVITY_CODE);
                record.put("ACTIVITY_NAME",     StrUtil.LPAD(ACTIVITY_NAME, ACTIVITY_NAME.length() + (activityTemplateDepth)*6, " "));
                record.put("ACTIVITY_NAME_ENG", StrUtil.LPAD(ACTIVITY_NAME_ENG, ACTIVITY_NAME_ENG.length() + (activityTemplateDepth)*6, " "));
                record.put("SORTING",           SORTING);
                record.put("UPPER_ACTIVITY",    UPPER_ACTIVITY);
                record.put("MILESTONE_YN",      MILESTONE_YN);
                record.put("BASE_ST",           BASE_ST);
                record.put("ROLE_CODE",         ROLE_CODE);
                record.put("ROLE_NAME",         ROLE_NAME);
                record.put("INSTRUCTION",       INSTRUCTION);
                if(skipInfo != null){
                    String[] splitSkipInfo = skipInfo.split(",");
                    for(int j=0; j<validGradeListForTemplateMaster.size(); j++){
                        String gradeNames = validGradeListForTemplateMaster.get(j).getNames();
                        for(int k=0; k<splitSkipInfo.length; k++){
                            String[] splitGradeData = splitSkipInfo[k].split(":");
                            if(gradeNames.equals(splitGradeData[0])){
                                record.put(gradeNames,     splitGradeData[1]);
                                break;
                            }
                        }
                    }
                }
                records.add(record);
                if(includeDoc && StrUtil.isNotEmpty((String)wbsItemTemplatesVO.getOutData().get("hasChildDoc"))){
//                    List<String> docTemplateObidList = new ArrayList<String>();
//                    for(int j=0; j<relatedDocList.size(); j++){
//                        docTemplateObidList.add(relatedDocList.get(j).getObid());
//                    }
//                    Map<String, String> fileNamesForExcel = ProjectActivityDocumentTemplate.getFileNamesForExcel(StrUtil.convertList2Str(docTemplateObidList), divisionUnitCode);
                    for(int j=0; j<relatedDocList.size(); j++){
//                        record = JSONObject.fromObject(record);
                        JSONObject docRecord = new JSONObject();
                        docRecord.put("DIVISION",          DIVISION);
                        docRecord.put("WBS_GUBUN",         WBS_GUBUN);
                        ProjectActivityDocumentTemplateVO documentTemplateVO = (ProjectActivityDocumentTemplateVO)relatedDocList.get(j);
                        ProjectActivityDocumentTemplate documentTemplate = new ProjectActivityDocumentTemplate(documentTemplateVO);
                        String RECODOCU_NAME        = documentTemplate.getDocumentNameDisplay(userLocale);
                        String RECODOCU_NAME_ENG    = documentTemplateVO.getDocumentNameEng();
                        String MANDANTORY_YN        = (String)documentTemplateVO.getOutData().get("rel_isMandantory");
                        String skipGradeList        = (String)documentTemplateVO.getOutData().get("rel_skipGradeList");
                        if("Y".equals(MANDANTORY_YN)){
                            MANDANTORY_YN = "REQ";
                        }else if ("N".equals(MANDANTORY_YN)){
                            MANDANTORY_YN = "DOC";
                        }else{
                            MANDANTORY_YN = "REF";
                        }
//                        String DOCUMENT_NAME        = documentTemplate.getFileNames( divisionUnitCode );
                        String DOCUMENT_NAME        = fileNamesForExcel.get(documentTemplateVO.getObid());
                        docRecord.put("TYPE",              "Document");
                        docRecord.put("LEV",               "D");
                        docRecord.put("ACTIVITY_OBID",     wbsItemTemplatesVO.getObid());
                        docRecord.put("ACTIVITY_CODE",     ACTIVITY_CODE);
                        docRecord.put("ACTIVITY_NAME",     StrUtil.LPAD(ACTIVITY_NAME, ACTIVITY_NAME.length() + (activityTemplateDepth)*6, " "));
                        docRecord.put("RECODOCU_OBID",     documentTemplateVO.getObid());
                        docRecord.put("RECODOCU_NAME",     RECODOCU_NAME);
                        docRecord.put("RECODOCU_NAME_ENG", RECODOCU_NAME_ENG);
                        docRecord.put("MANDANTORY_YN",     MANDANTORY_YN);
                        docRecord.put("FILE_NAME",         DOCUMENT_NAME);
                        if(StrUtil.isNotEmpty(skipGradeList) && !"None".equals(skipGradeList)){
                            String[] splitSkipInfo = skipGradeList.split(",");
                            for(int k=0; k<validGradeListForTemplateMaster.size(); k++){
                                String gradeNames = validGradeListForTemplateMaster.get(k).getNames();
                                for(int l=0; l<splitSkipInfo.length; l++){
                                    if(gradeNames.equals(splitSkipInfo[l].trim())){
                                        docRecord.put(gradeNames,     "Y");
                                        break;
                                    }
                                }
                                if(docRecord.get(gradeNames) == null){
                                    docRecord.put(gradeNames,     "N");
                                }
                            }
                        }else{
                            for(int k=0; k<validGradeListForTemplateMaster.size(); k++){
                                String gradeNames = validGradeListForTemplateMaster.get(k).getNames();
                                docRecord.put(gradeNames,     "N");
                            }
                        }
                        records.add(docRecord);
                    }
                }
            }else if(ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE.equals(wbsItemTemplatesStructure.get(i).getClassName()) && includeJob){
                JobActivityTemplateVO jobActivityTemplateVO = (JobActivityTemplateVO)wbsItemTemplatesStructure.get(i);
                String DIVISION =           wbsTemplateMasterVO.getDivisionUnit();
                String WBS_GUBUN =          wbsTemplateMasterVO.getDevelopmentType();
                String ACTIVITY_NAME =      ApplicationBizConstants.LANG_KO.equals(userLocale) ? jobActivityTemplateVO.getActivityNameKor() 
                        : ( ApplicationBizConstants.LANG_CH.equals(userLocale) ? jobActivityTemplateVO.getActivityNameChi() 
                                : jobActivityTemplateVO.getActivityNameEng() );
                String ACTIVITY_NAME_ENG =  jobActivityTemplateVO.getActivityNameEng();
                JSONObject record = new JSONObject();
                record.put("DIVISION",          DIVISION);
                record.put("WBS_GUBUN",         WBS_GUBUN);
                record.put("LEV",               "J");
                record.put("TYPE",              "Job");
                record.put("ACTIVITY_OBID",     jobActivityTemplateVO.getObid());
                record.put("ACTIVITY_NAME",     StrUtil.LPAD(ACTIVITY_NAME, ACTIVITY_NAME.length() + (activityTemplateDepth + 1)*6, " "));
                record.put("ACTIVITY_NAME_ENG", StrUtil.LPAD(ACTIVITY_NAME_ENG, ACTIVITY_NAME_ENG.length() + (activityTemplateDepth + 1)*6, " "));
                records.add(record);
            }
        }
        return records;
    }
    private JSONArray getExportWBSTemplateToExcelFields(boolean includeDoc, List<ProjectGradeVO> validGradeListForTemplateMaster){
        String[][] fields = {   {"DIVISION",          "DIVISION",           "text"},
                                {"WBS_GUBUN",         "WBS_GUBUN",          "text"},
                                {"LEV",               "LEV",                "text"},
//                                {"ACTIVITY_OBID",     "ACTIVITY_OBID",      "text"},
                                {"TYPE",              "TYPE",               "text"},
                                {"ACTIVITY_CODE",     "ACTIVITY_CODE",      "text"},
                                {"ACTIVITY_NAME",     "ACTIVITY_NAME",      "text"},
                                {"ACTIVITY_NAME_ENG", "ACTIVITY_NAME_ENG",  "text"},
                                {"SORTING",           "SORTING",            "integer"},
                                {"UPPER_ACTIVITY",    "UPPER_ACTIVITY",     "text"},
                                {"MILESTONE_YN",      "MILESTONE_YN",       "text"},
                                {"BASE_ST",           "BASE_ST",            "integer"},
                                {"ROLE_CODE",         "ROLE_CODE",          "text"},
                                {"ROLE_NAME",         "ROLE_NAME",          "text"},
                                {"INSTRUCTION",       "INSTRUCTION",        "text"}
                            };
        String[][] docFields = {
//                                 { "RECODOCU_OBID",     "DOCUMENT_OBID",        "text"},
                                 { "RECODOCU_NAME",     "DOCUMENT_NAME",        "text"},
                                 { "RECODOCU_NAME_ENG", "DOCUMENT_NAME_ENG",    "text"},
                                 { "MANDANTORY_YN",     "MANDANTORY",            "text"},
                                 { "FILE_NAME",         "FILE_NAME",            "text"}
                             };
        JSONArray fieldList = new JSONArray();
        
        for(int i=0; i<fields.length; i++){
            JSONObject fieldMap = new JSONObject();
            fieldMap.put("name",    fields[i][0]);
            fieldMap.put("title",   fields[i][1]);
            fieldMap.put("type",    fields[i][2]);
            fieldList.add(fieldMap);
        }
        if(includeDoc){
            for(int i=0; i<docFields.length; i++){
                JSONObject fieldMap = new JSONObject();
                fieldMap.put("name",    docFields[i][0]);
                fieldMap.put("title",   docFields[i][1]);
                fieldMap.put("type",    docFields[i][2]);
                fieldList.add(fieldMap);
            }
        }
        if(validGradeListForTemplateMaster != null && validGradeListForTemplateMaster.size() > 0){
            for(int i=0; i<validGradeListForTemplateMaster.size(); i++){
                ProjectGradeVO projectGradeVO = validGradeListForTemplateMaster.get(i);
                JSONObject fieldMap = new JSONObject();
                fieldMap.put("type", "text");
                fieldMap.put("name", projectGradeVO.getNames());
                fieldMap.put("title", projectGradeVO.getTitles());
                fieldList.add(fieldMap);
            }
        }
        return fieldList;
    }

    private JSONArray getExportWBSTemplateToImportFields(boolean includeDoc, List<ProjectGradeVO> validGradeListForTemplateMaster){
        String[][] fields = {   {"DIVISION",          "DIVISION",           "text"},
                                {"WBS_GUBUN",         "WBS_GUBUN",          "text"},
                                {"LEV",               "LEV",                "text"},
                                {"TYPE",              "TYPE",               "text"},
                                {"ACTIVITY_CODE",     "ACTIVITY_CODE",      "text"},
                                {"ACTIVITY_NAME",     "ACTIVITY_NAME",      "text"},
                                {"ACTIVITY_OBID",     "ACTIVITY_OBID",      "text"}
                            };
        String[][] docFields = {
                                 { "RECODOCU_NAME",     "DOCUMENT_NAME",        "text"},
                                 { "MANDANTORY_YN",     "MANDANTORY",            "text"},
                                 { "FILE_NAME",         "FILE_NAME",            "text"},
                                 { "RECODOCU_OBID",     "DOCUMENT_OBID",        "text"}
                             };
        JSONArray fieldList = new JSONArray();
        
        for(int i=0; i<fields.length; i++){
            JSONObject fieldMap = new JSONObject();
            fieldMap.put("name",    fields[i][0]);
            fieldMap.put("title",   fields[i][1]);
            fieldMap.put("type",    fields[i][2]);
            fieldList.add(fieldMap);
        }
        if(includeDoc){
            for(int i=0; i<docFields.length; i++){
                JSONObject fieldMap = new JSONObject();
                fieldMap.put("name",    docFields[i][0]);
                fieldMap.put("title",   docFields[i][1]);
                fieldMap.put("type",    docFields[i][2]);
                fieldList.add(fieldMap);
            }
        }
        if(validGradeListForTemplateMaster != null && validGradeListForTemplateMaster.size() > 0){
            for(int i=0; i<validGradeListForTemplateMaster.size(); i++){
                ProjectGradeVO projectGradeVO = validGradeListForTemplateMaster.get(i);
                JSONObject fieldMap = new JSONObject();
                fieldMap.put("type", "text");
                fieldMap.put("name", projectGradeVO.getNames());
                fieldMap.put("title", projectGradeVO.getTitles());
                fieldList.add(fieldMap);
            }
        }
        return fieldList;
    }
    
    /**
     * UPDATE를 위한 WBS Activity List를 Return함.
     * @param activityTemplateMasterObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#createTemplateMasterExcelDownloadForUpate(java.lang.String)
     */
    @Override
    public XSSFWorkbook getTemplateMasterExcelDownloadForUpate(String activityTemplateMasterObid){
        XSSFWorkbook workbook = new XSSFWorkbook();
        createMainScheet(activityTemplateMasterObid,workbook);
        //createRoleListSheet(workbook);
        //createGuideSheet(workbook);
        return workbook;
    }
    private void createMainScheet(String activityTemplateMasterObid,XSSFWorkbook workbook){
        XSSFSheet mainSheet     = workbook.createSheet("WBS Template");
        createExcelHederForUpate(workbook,mainSheet);
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(activityTemplateMasterObid);
        List<WBSItemTemplatesVO> wbsItemTemplatesList = wbsTemplateMasterDom.getWBSItemTemplatesStructure(10);
        List<TemplateDependencyVO> dependencyList = WBSTemplateMaster.getTemplateDependencyAll(activityTemplateMasterObid, wbsItemTemplatesList);
        
        Map<String, HashMap<String, Object>> objectMap = new HashMap<String, HashMap<String, Object>>();
        for(int i = 0; i < wbsItemTemplatesList.size(); i++){
            HashMap<String, Object> subMap = new HashMap<String, Object>();
            subMap.put("ID", i);
            subMap.put("OBJECT", wbsItemTemplatesList.get(i));
            List<TemplateDependencyVO> dependList = getPreviousDependList(wbsItemTemplatesList.get(i),dependencyList);
            subMap.put("DEPENDENCY", dependList);
            objectMap.put(wbsItemTemplatesList.get(i).getObid(), subMap);
        }
        int dataRow = 4;
        for(WBSItemTemplatesVO vo : wbsItemTemplatesList){
            mainScheetRow(activityTemplateMasterObid,workbook,mainSheet,dataRow++,vo,objectMap);
        }
    }
    private List<TemplateDependencyVO> getPreviousDependList(WBSItemTemplatesVO wbsItemTemplatesVO, List<TemplateDependencyVO> dependencyList){
        List<TemplateDependencyVO> returnList = new ArrayList<TemplateDependencyVO>();
        for(TemplateDependencyVO vo : dependencyList){
            if(vo.getFromObid().equals(wbsItemTemplatesVO.getObid())){
                returnList.add(vo);
            }
        }
        return returnList;
    }
    private void mainScheetRow(String activityTemplateMasterObid,XSSFWorkbook workbook,XSSFSheet mainSheet, int rownum, WBSItemTemplatesVO wbsItemTemplatesVO,Map<String, HashMap<String, Object>> objectMap){
        XSSFRow row = mainSheet.createRow(rownum);
        Cell cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_TEMPATE_OBID);
        cell.setCellStyle(getDarkGrayCellStyle(workbook));
        cell.setCellValue(activityTemplateMasterObid);

        cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_OBID);
        cell.setCellStyle(getDarkGrayCellStyle(workbook));
        cell.setCellValue(wbsItemTemplatesVO.getObid());

        cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_CODE);
        cell.setCellStyle(getThinGrayCellStyle(workbook));
        cell.setCellValue(wbsItemTemplatesVO.getActivityMasterName()); 

        cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_TYPE);
        cell.setCellStyle(getThinGrayCellStyle(workbook));
        cell.setCellValue(wbsItemTemplatesVO.getClassName()); 
        
        cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_NAME);
        cell.setCellStyle(getThinGrayCellStyle(workbook));
        cell.setCellValue(wbsItemTemplatesVO.getActivityNameEng()); 
        
        cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_LEVEL);
        cell.setCellStyle(getThinGrayCellStyle(workbook));
        cell.setCellValue(wbsItemTemplatesVO.getExplodedDepth()); 
        
        cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_ID);
        cell.setCellStyle(getThinGrayCellStyle(workbook));
        cell.setCellValue(rownum-3); 
        
        if(wbsItemTemplatesVO.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_PROJECTPHASE) || 
                wbsItemTemplatesVO.getHasChildActivity().equals("Y")){
            cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_PRE_LSIT);
            String dependList = dependencyListForExcelDown(wbsItemTemplatesVO,objectMap);
            cell.setCellValue(dependList); 
            
            cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_DURATION);
            cell.setCellStyle(getDarkGrayCellStyle(workbook));
            cell.setCellValue(wbsItemTemplatesVO.getStandardDuration());        
            
            cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_ROLE);
            cell.setCellStyle(getDarkGrayCellStyle(workbook));
            cell.setCellValue(wbsItemTemplatesVO.getRoleList());    
        }else{
            cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_PRE_LSIT);
            cell.setCellStyle(getDefaultCellStyle(workbook));
            String dependList = dependencyListForExcelDown(wbsItemTemplatesVO,objectMap);
            cell.setCellValue(dependList); 
            
            cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_DURATION);
            cell.setCellStyle(getDefaultCellStyle(workbook));
            cell.setCellValue(wbsItemTemplatesVO.getStandardDuration());        
            
            cell = row.createCell(ProjectConstants.WBS_TEMPLATE_EXCEL_ACTIVITY_ROLE);
            cell.setCellStyle(getDefaultCellStyle(workbook));
            cell.setCellValue(wbsItemTemplatesVO.getRoleList());    
        }
    }
    @SuppressWarnings("unchecked")
    private String dependencyListForExcelDown(WBSItemTemplatesVO wbsItemTemplatesVO,Map<String, HashMap<String, Object>> objectMap){
        StringBuffer strBuf = new StringBuffer();
        HashMap<String, Object> eachMap = objectMap.get(wbsItemTemplatesVO.getObid());
        List<TemplateDependencyVO> dependList = (List<TemplateDependencyVO>)eachMap.get("DEPENDENCY");
        for(TemplateDependencyVO vo : dependList){
            HashMap<String, Object> dependObjMap = objectMap.get(vo.getToObid());
            int ID = (int)dependObjMap.get("ID");
            strBuf.append(ID).append(vo.getDependencyType()).append(",");
        }
        return strBuf.substring(0,strBuf.lastIndexOf(",")-1);
    }
    private CellStyle getDefaultCellStyle(XSSFWorkbook workbook){
        CellStyle violetStyle = workbook.createCellStyle();
//        violetStyle.setFillForegroundColor(HSSFColor.WHITE.index);
//        violetStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        violetStyle.setAlignment(CellStyle.ALIGN_CENTER);
//        setStyle(violetStyle,CellStyle.BORDER_THIN);
        return violetStyle;
    }
    private CellStyle getDarkGrayCellStyle(XSSFWorkbook workbook){
        CellStyle violetStyle = workbook.createCellStyle();
        violetStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_80_PERCENT.getIndex());
//        violetStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        violetStyle.setAlignment(CellStyle.ALIGN_CENTER);
//        setStyle(violetStyle,CellStyle.BORDER_THIN);
        return violetStyle;
    }
    private CellStyle getThinGrayCellStyle(XSSFWorkbook workbook){
        CellStyle violetStyle = workbook.createCellStyle();
//        violetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//        violetStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        violetStyle.setAlignment(CellStyle.ALIGN_CENTER);
//        setStyle(violetStyle,CellStyle.BORDER_THIN);
        return violetStyle;
    }
    private void setStyle(CellStyle style, BorderStyle border){
        style.setBorderBottom(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderTop(border);
    }
    private void createExcelHederForUpate(XSSFWorkbook workbook,XSSFSheet mainSheet){
        makeRemarks(workbook,mainSheet);
        makeHeader(workbook,mainSheet);
        setMainSheetColumnWidth(mainSheet);
    }
    private void setMainSheetColumnWidth(XSSFSheet mainSheet){
        int i = 0;
        mainSheet.setColumnWidth(i++, 500);//WBS Template Obid
        mainSheet.setColumnWidth(i++, 500);//Activity Template Obid
        mainSheet.setColumnWidth(i++, 5000);//Activity Code
        mainSheet.setColumnWidth(i++, 5000);//Type
        mainSheet.setColumnWidth(i++, 5000);//Activity Name
        mainSheet.setColumnWidth(i++, 5000);//Level
        mainSheet.setColumnWidth(i++, 5000);//ID
        mainSheet.setColumnWidth(i++, 5000);//Pre Activity List
        mainSheet.setColumnWidth(i++, 5000);//Duration(Days)
        mainSheet.setColumnWidth(i++, 5000);//Role
        mainSheet.setColumnWidth(i++, 5000);//Applied Grade
        mainSheet.setColumnWidth(i++, 5000);
        mainSheet.setColumnWidth(i++, 5000);
        mainSheet.setColumnWidth(i++, 5000);
    }
    private void makeRemarks(XSSFWorkbook workbook,XSSFSheet mainSheet){
        XSSFRow row = mainSheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellStyle(this.getDefaultCellStyle(workbook));
        cell.setCellValue("Gray: Cannot modify, Yellow: Can modify"); 
        for(int i = 1; i < 9; i++){
            cell = row.createCell(i);
        }
        CellRangeAddress region = new CellRangeAddress(1, 1, 0, 9);
        mainSheet.addMergedRegion(region);

        row = mainSheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellStyle(this.getDefaultCellStyle(workbook));
        cell.setCellValue("★★★★Cannot Add Row and Delete"); 
        for(int i = 1; i < 9; i++){
            cell = row.createCell(i);
        }
        region = new CellRangeAddress(1, 1, 0, 9);
        mainSheet.addMergedRegion(region);
    }
    private void makeHeader(XSSFWorkbook workbook,XSSFSheet mainSheet){
        XSSFRow row = mainSheet.createRow(2);
        int col = 0;
        Cell cell = row.createCell(col++);
        cell.setCellStyle(this.getDarkGrayCellStyle(workbook));
        cell.setCellValue("WBS Template Obid"); 
        cell = row.createCell(col++);
        cell.setCellStyle(this.getDarkGrayCellStyle(workbook));
        cell.setCellValue("Activity Template Obid"); 
        cell = row.createCell(col++);
        cell.setCellStyle(this.getDarkGrayCellStyle(workbook));
        cell.setCellValue("Activity Code"); 
        cell = row.createCell(col++);
        cell.setCellStyle(this.getDarkGrayCellStyle(workbook));
        cell.setCellValue("Type"); 
        cell = row.createCell(col++);
        cell.setCellStyle(this.getDarkGrayCellStyle(workbook));
        cell.setCellValue("Activity Name"); 
        cell = row.createCell(col++);
        cell.setCellStyle(this.getDarkGrayCellStyle(workbook));
        cell.setCellValue("Level"); 
        cell = row.createCell(col++);
        cell.setCellStyle(this.getDarkGrayCellStyle(workbook));
        cell.setCellValue("ID"); 
        cell = row.createCell(col++);
        cell.setCellStyle(this.getDarkGrayCellStyle(workbook));
        cell.setCellValue("Pre Activity List"); 
        cell = row.createCell(col++);
        cell.setCellStyle(this.getDarkGrayCellStyle(workbook));
        cell.setCellValue("Duration(Days)"); 
        cell = row.createCell(col++);
        cell.setCellStyle(this.getDarkGrayCellStyle(workbook));
        cell.setCellValue("Role"); 
        row = mainSheet.createRow(3);
        for(int i = 0; i < 9; i++){
            CellRangeAddress region = new CellRangeAddress(2, 3, i, i);
            mainSheet.addMergedRegion(region);
        }
    }

    /**
     * 
     * @param wbsTemplateMasterVO
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnReviseWBSTemplateMaster(lge.plm.api.object.project.model.WBSTemplateMasterVO)
     */
    @Override
    public WBSTemplateMasterVO txnReviseWBSTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO){
        WBSTemplateMaster wbsTemplateMasterDom = DomUtil.toDom(wbsTemplateMasterVO);
        return wbsTemplateMasterDom.reviseTemplateMaster();
    }

    /**
     * 
     * @param wbsTemplateMasterVO
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCancelReviseWBSTemplateMaster(lge.plm.api.object.project.model.WBSTemplateMasterVO)
     */
    @Override
    public void txnCancelReviseWBSTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO){
        WBSTemplateMaster wbsTemplateMasterDom = DomUtil.toDom(wbsTemplateMasterVO);
        wbsTemplateMasterDom.cancelReviseTemplateMaster();            
    }

    /**
     * 
     * @param wbsTemplateMasterObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#getWBSItemTemplatesStructureWithJobActivity(java.lang.String)
     */
    @Override
    public List<BusinessObjectRootVO> getWBSItemTemplatesStructureWithJobActivity(String wbsTemplateMasterObid){
        WBSTemplateMaster maserDom = new WBSTemplateMaster(wbsTemplateMasterObid);
        return maserDom.getWBSItemTemplatesStructureWithJobActivity();
    }
    /**
     * 
     * @param wbsTemplateMasterObid
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#getValidGradeForTemplateMaster(java.lang.String)
     */
    @Override
    public List<ProjectGradeVO> getValidGradeListForTemplateMaster(String wbsTemplateMasterObid,boolean activeOnly){
            return new WBSTemplateMaster(wbsTemplateMasterObid).getValidGradeListForTemplateMaster(activeOnly);
    }
    /**
     * 
     * @param fromMasterObid
     * @see lge.plm.project.wbs.service.WBSTemplateService#txnCloneWbsTemplateMasterFromOtherDivision(java.lang.String)
     */
    @Override
    public List<WBSTemplateMasterVO> txnCloneWbsTemplateMasterFromOtherDivision(List<WBSTemplateMasterVO> wbsTemplateMasterList, String targetDivisionUnit){
        DivisionUnitVO divisionUnitVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, targetDivisionUnit);
        validateForCloneTemplateMasterFromOtherDivision(wbsTemplateMasterList, divisionUnitVO);
        if(NullUtil.isNull(divisionUnitVO)) throw new ApplicationException("Your Division(" + targetDivisionUnit + ") is Invalid.");
        List<WBSTemplateMasterVO> createdList = new ArrayList<WBSTemplateMasterVO>();
        for(WBSTemplateMasterVO masterVO : wbsTemplateMasterList){
            WBSTemplateMasterVO newMasterVO = (WBSTemplateMasterVO)DomUtil.copy(masterVO);
            newMasterVO.setPreviousObid("1");
            newMasterVO.setNextObid("1");
            newMasterVO.setRevision("1.0");
            newMasterVO.setStates(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_WORKING);
            newMasterVO.setIsPublished("N");
            newMasterVO.setDivisionUnit(targetDivisionUnit);
            txnCloneWbsTemplateMaster(masterVO.getObid(), newMasterVO);
            createdList.add(newMasterVO);
        }
        return createdList;
    }

    private void validateForCloneTemplateMasterFromOtherDivision(List<WBSTemplateMasterVO> wbsTemplateMasterList, DivisionUnitVO divisionUnitVO){
        List<ActivityTemplateMasterVO> retrieveActivityTemplateMasterList = ActivityTemplateMaster.retrieveActivityTemplateMasterList(divisionUnitVO.getNames());
        Set<String> activityMasterSet = new HashSet<String>();
        for(ActivityTemplateMasterVO activityTemplateMasterVO : retrieveActivityTemplateMasterList){activityMasterSet.add(activityTemplateMasterVO.getNames());}

        StringBuffer errorMessage = new StringBuffer();
        for(WBSTemplateMasterVO masterVO : wbsTemplateMasterList){
            WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(masterVO);
            List<WBSItemTemplatesVO> wbsItemTemplatesActivityOnly = wbsTemplateMaster.getWBSItemTemplatesActivityOnly();
            for(WBSItemTemplatesVO wbsItemTemplatesVO : wbsItemTemplatesActivityOnly){
                if( !NullUtil.isNone(wbsItemTemplatesVO.getActivityMasterName()) && !activityMasterSet.contains(wbsItemTemplatesVO.getActivityMasterName())){
                    errorMessage.append("<br>").append(new WBSItemTemplates(wbsItemTemplatesVO).getCommonTitlesForDisplay()).append(wbsItemTemplatesVO.getActivityMasterName());
                }
            }
        }
        
        if(errorMessage.length() > 0){
            throw new ApplicationException("Activity does not exists for " + divisionUnitVO.getTitles() + ". please add activity masters below." + errorMessage.toString());
        }
    }
    
    /**
     * 
     * @param wbsTemplateMasterVO
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#publishTmplateMaster(lge.plm.api.object.project.model.WBSTemplateMasterVO)
     */
    @Override
    public void txnPublishTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO, MailVO mailVO, List<UsersVO> recipientList){
        // TODO Auto-generated method stub
        if(!wbsTemplateMasterVO.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE))  throw new FoundationBaseException("Current State is" + wbsTemplateMasterVO.getStates() + ". Only " + ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE + " can be published");
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterVO);
        wbsTemplateMasterDom.publishWBSTemplateMaster();
        
        UsersVO fromUserVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, ApplicationBizConstants.PMS_ADMIN_USER, false);
        for(UsersVO vo : recipientList){
            List<String> toEmailList = new ArrayList<String>();
            toEmailList.add(vo.getMailId()); 
            
            mailVO.setFromUserVO(fromUserVO);
            mailVO.setToEmailList(toEmailList);
            
            mailService.sendMailByUser(mailVO, "WBS Template Publish Mail", vo.getObid());
        }
    }
    
    /**
     * 
     * @param wbsTemplateMasterVO
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#publishTmplateMaster(lge.plm.api.object.project.model.WBSTemplateMasterVO)
     */
    @Override
    public void txnSendMailTemplateMaster(WBSTemplateMasterVO wbsTemplateMasterVO, MailVO mailVO, List<UsersVO> recipientList){
        UsersVO fromUserVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, ApplicationBizConstants.PMS_ADMIN_USER, false);
        for(UsersVO vo : recipientList){
            if(!NullUtil.isNull(vo.getMailId())){
                List<String> toEmailList = new ArrayList<String>();
                toEmailList.add(vo.getMailId()); 
                
                mailVO.setFromUserVO(fromUserVO);
                mailVO.setToEmailList(toEmailList);
                
                mailService.sendMailByUser(mailVO, "WBS Template Publish Mail", vo.getObid());
            }
        }
    }
    
    @Override
    public List<HashMap<String, String>> getDivisionCombo(){
        List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
        Users curUser = new Users( userSession.getUserBizObid() );
        List<DivisionUnitVO> retrieveDivisionUnitList = curUser.retrieveDivisionUnitList();
        
        List<DivisionUnitVO> libraryDivisionVO = DivisionUnit.findCommonDivisionForTemplateLibrary();
        retrieveDivisionUnitList.addAll(libraryDivisionVO);
        
        for(DivisionUnitVO divisionUnitVO : retrieveDivisionUnitList){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("names", divisionUnitVO.getNames());
            map.put("titles", divisionUnitVO.getTitles());
            resultList.add(map);
        }
        
        return resultList;
    }
    @Override
    public List<CheckboxItemVO> getRevisionOptionGroup(String obid, String obidContext, UserSession userSession, String dummy ){
        List<CheckboxItemVO> resultList = new ArrayList<CheckboxItemVO>();
        CheckboxItemVO vo = new CheckboxItemVO();
        vo.setObjectName("Latest");
        vo.setObjectLabel("Latest");
        vo.setObjectValue("true");
        vo.setObjectChecked(true);
        resultList.add ( vo );
        return resultList;
    }

    /**
     * Migration 용도
     * @param dataList
     * @return
     * @see lge.plm.project.wbs.service.WBSTemplateService#createActivityTemplateMaster(java.util.List)
     */
    @Override
    public HashMap<String, Object> createActivityTemplateMaster(List<ActivityTemplateMasterVO> dataList){
        HashMap<String, Object> mapResult = new HashMap<String, Object>();
        StringBuffer message = new StringBuffer();
        int successCount = 0;
        
        if( !NullUtil.isNone(dataList) ){
            ActivityTemplateMaster createDom = null;
            ActivityTemplateMasterVO createVo = null;
            for( int inx = 0; inx < dataList.size(); inx++ ){
                createVo = dataList.get( inx );
                createVo.setClassName( ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER );
                createVo.setLifeCycle( ApplicationSchemaConstants.LIFECYCLE_ACTIVITY_TEMPLATE_MASTER );
                
                createDom = DomUtil.toDom( createVo );
                createDom.createObject();
                
                successCount++;
            }
        }

        if (StrUtil.isEmpty(message.toString())) {
            message.append( "Success!" );
        }

        mapResult.put( "message", message.toString() );
        mapResult.put( "successCount", successCount );
        
        return mapResult;
    }
    
    public HashMap<String, Object> updateSkipInfo(HashMap<String, Object> propertyParams){
        HashMap< String, Object > mapResult = new HashMap< String, Object >();
        StringBuffer message = new StringBuffer();

        try {
            WBSItemTemplates wbsItems = DomUtil.toDom((String)propertyParams.get("activityObid"));
            
            if("Activity".equals(propertyParams.get("type")) || "Event".equals(propertyParams.get("type"))){
                WBSItemTemplatesVO wbsItemsVO = wbsItems.getVo();
                wbsItemsVO.setSkipInfo((String)propertyParams.get("skipInfo"));
                Set<String> attributes = new HashSet<String>();
                attributes.add("skipInfo");
                ObjectRoot.modifyObjectSetBatch(wbsItemsVO, attributes);
            } else if("Document".equals(propertyParams.get("type"))){
                StringBuffer selectPatternBuf = new StringBuffer();
                StringBuffer wherePatternBuf = new StringBuffer();
                StringBuffer paramPatternBuf = new StringBuffer();
                
                StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, (String)propertyParams.get("documentObid"));
               List<RecommendedDocumentTemplateVO> relationship = wbsItems.getRelationship(ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE, 
                                        ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE, 
                                        GlobalConstants.FLAG_TYPE_TO,
                                        selectPatternBuf.toString(), 
                                        wherePatternBuf.toString(), 
                                        paramPatternBuf.toString());

                
                if(relationship.size() > 0){
                    RecommendedDocumentTemplate rDocTemp = DomUtil.toDom(relationship.get(0).getObid());
                    
                    rDocTemp.getVo().setIsMandantory((String)propertyParams.get("mandantory"));
                    rDocTemp.getVo().setSkipGradeList((String)propertyParams.get("skipInfo"));
                    
                    Set<String> attributes = new HashSet<String>();
                    attributes.add("isMandantory");
                    attributes.add("skipInfo");
                    ObjectRoot.modifyObjectSetBatch(rDocTemp.getVo(), attributes);
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
    
            if ( !StrUtil.isEmpty( message.toString() ) ) {
                message.append( new StringBuffer( e.getMessage() ) );
            } else {
                message = new StringBuffer( e.getMessage() );
            }
        }

        mapResult.put( "message", message.toString() );
    
        return mapResult;
    }
	@Override
	public List<ProjectDevelopmentTypeVO> getUsingProjectDevelopmentType(String divisionUnit) {
		// TODO Auto-generated method stub
		DivisionUnit divisionUnitDom = new DivisionUnit(DivisionUnit.getVOByName(divisionUnit));
		return divisionUnitDom.getUsingProjectDevelopmentType("");
	}
	@Override
	public List<ProjectDevelopmentTypeVO> getUsingProjectDevelopmentType(String divisionUnit, String className) {
		// TODO Auto-generated method stub
		DivisionUnit divisionUnitDom = new DivisionUnit(DivisionUnit.getVOByName(divisionUnit));
		return divisionUnitDom.getUsingProjectDevelopmentType(className,ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
	}
	@Override
	public List<ProjectDevelopmentTypeVO> getUsingProjectDevelopmentType(String divisionUnit, String className,boolean isAllState) {
		// TODO Auto-generated method stub
		DivisionUnit divisionUnitDom = new DivisionUnit(DivisionUnit.getVOByName(divisionUnit));
		if(isAllState) {
			return divisionUnitDom.getUsingProjectDevelopmentType(className,"");
		}else {
			return divisionUnitDom.getUsingProjectDevelopmentType(className,ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
		}
	}
	@Override
	public List<ProjectPhaseVO> getUsingProjectPhases(String divisionUnit, String projectLifeCycle) {
		// TODO Auto-generated method stub
		DivisionUnit divisionUnitDom = new DivisionUnit(DivisionUnit.getVOByName(divisionUnit));
        ProjectLifeCycleVO usingProjectLifeCycleVO = divisionUnitDom.getUsingProjectLifeCycle(projectLifeCycle);
        ProjectLifeCycle projectLifeCycleDom = new ProjectLifeCycle(usingProjectLifeCycleVO);
        return projectLifeCycleDom.getProjectPhaseList(divisionUnitDom.getVo(),true);
	}
	@Override
	public List<ProjectLifeCycleVO> getUsingProjectLifeCycleList(String divisionUnit) {
		// TODO Auto-generated method stub
		DivisionUnit divisionUnitDom = new DivisionUnit(DivisionUnit.getVOByName(divisionUnit));
		return divisionUnitDom.getUsingProjectLifeCycleList();
	}
	@Override
	public List<ProjectGradeVO> getUsingProjecGrade(String divisionUnit) {
		// TODO Auto-generated method stub
		DivisionUnit divisionUnitDom = new DivisionUnit(DivisionUnit.getVOByName(divisionUnit));
		return divisionUnitDom.getUsingProjecGrade();
	}
	@Override
	public List<ProjectGradeVO> getUsingProjectGradeByDevelopType(String divisionUnit, String developmentType,
			boolean activeOnly) {
		// TODO Auto-generated method stub
		return ProjectGrade.getUsingProjectGradeByDevelopType(divisionUnit, developmentType, activeOnly);
	}
};
