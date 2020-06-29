package com.rap.projectbase.wbs.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rap.code.service.CodeService;
import com.rap.mail.model.MailVO;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.foundation.ui.service.UIService;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.framework.annotation.SCRequestDataset;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.framework.file.download.ExcelDownloadView;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.ResponseMapper;
import com.rap.projectbase.wbs.model.ActivityTemplateMasterSearchVO;
import com.rap.projectbase.wbs.model.ProjectActivityDocumentTemplateSearchVO;
import com.rap.projectbase.wbs.service.WBSTemplateService;
import com.rap.projectbase.wbs.util.WBSTemplateUtil;
import com.rap.projectbase.wbs.util.model.DHTMLGanttLinkVO;
import com.rap.projectbase.wbs.util.model.DHTMLGanttTemplateActivityVO;

import rap.api.object.document.model.ProjectActivityDocumentTemplateVO;
import rap.api.object.organization.dom.Department;
import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.dom.ActivityTemplateMaster;
import rap.api.object.project.dom.JobActivityTemplate;
import rap.api.object.project.dom.ProjectGrade;
import rap.api.object.project.dom.ProjectRole;
import rap.api.object.project.dom.WBSItemTemplates;
import rap.api.object.project.dom.WBSPhaseTemplate;
import rap.api.object.project.dom.WBSSubProjectTemplate;
import rap.api.object.project.dom.WBSTemplateMaster;
import rap.api.object.project.model.ActivityTemplateMasterVO;
import rap.api.object.project.model.JobActivityTemplateVO;
import rap.api.object.project.model.ProjectPhaseVO;
import rap.api.object.project.model.WBSActivityTemplateVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSPhaseTemplateVO;
import rap.api.object.project.model.WBSSubProjectTemplateVO;
import rap.api.object.project.model.WBSTemplateMasterVO;
import rap.api.object.relation.model.HasActivityTemplateVO;
import rap.api.object.relation.model.TemplateDependencyVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;

/**
 * <pre>
 * Class : WBSTemplateComtroller
 * Description : TODO
 * </pre>
 * 
 * @author heonhyung.lee
 */
@Controller
public class WBSTemplateController {
    static final Logger LOGGER = LoggerFactory.getLogger(WBSTemplateController.class);
    
    @Autowired
    private UserSession userSession;
    
    @Resource(name = "uiService")
    private UIService uiService;

    @Resource(name = "wbsTemplateService")
    private WBSTemplateService wbsTemplateService;
    
    @Resource(name = "codeService")
    private CodeService codeService;
    
    @RequestMapping("/wbsTemplate/initWBSTemplateMasterList.do")
    public String initWBSTemplateMasterManagement(ModelMap map){
        ResponseMapper rm = new ResponseMapper();

        rm.setData("divisionUnit", userSession.getDivisionUnitCode());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }

    @RequestMapping("/wbsTemplate/getWBSTemplateMasterList.do")
    public String getWBSTemplateMasterList(@SCRequestDataset("divisionUnit") String divisionUnit,
                                           @SCRequestDataset("latestOnly") String latestOnly,
                                           ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        boolean isLatestOnly = "Y".equals(latestOnly);
        rm.setData("templateMaster", wbsTemplateService.retrieveWBSTemplateMasterList(divisionUnit, true, isLatestOnly));
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbsTemplate/getWBSTemplateMasterListAll.do")
    public String getWBSTemplateMasterListAll(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,@SCRequestDataset("divisionUnit") String divisionUnit,
                                           ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        List<WBSTemplateMasterVO> list = new ArrayList<WBSTemplateMasterVO>();
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);
        if(divisionUnit.equals("Library")){
            list = wbsTemplateService.retrieveWBSTemplateMasterList(ProjectConstants.PROJECT_BASIS_COMMON_DIVISION, true, true);       
        }else{
            String[] states = {ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE,ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_WORKING};
            list = wbsTemplateService.retrieveWBSTemplateMasterList(states);
        }
        for(int i = list.size()-1;i >= 0 ; i-- ){
            if(wbsTemplateMasterDom.getNames().equals(list.get(i).getNames())) list.remove(i);
        }
        SortUtil.sort(list, "divisionUnit:developmentType:projectLifeCycle",false);
        rm.setData("templateMaster", list);     
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
//    @RequestMapping("/wbsTemplate/getHrDepartmentLeader.do")
//    public String getHrDepartmentLeader(ModelMap map){
//        ResponseMapper rm = new ResponseMapper();
//        
//        if(!NullUtil.isNone(userSession.getHrDepartmentObid())){
//            Department deptDom = (Department)DomUtil.toDom(userSession.getHrDepartmentObid());
//            if(null != deptDom && !NullUtil.isNone(deptDom.getVo().getLeaderEmployeeNo())){
//                Users userDom = Users.getUsers(deptDom.getVo().getLeaderEmployeeNo());
//                rm.setData("departmentLeader", userDom.getVo());
//            }
//        }
//        
//        rm.setModelMap(map);        
//        return GlobalConstants.AJAX_VIEW;
//    }
//    
    @RequestMapping("/wbsTemplate/getLatestReleasedWBSTemplateMasterList.do")
    public String getLatestReleasedWBSTemplateMasterList(@SCRequestDataset("divisionUnit") String divisionUnit, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        rm.setData("templateMasterList", wbsTemplateService.getLatestReleasedWBSTemplateMasterList(divisionUnit));
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/initCreateWBSTemplateMaster.do")
    public String initCreateWBSTemplateMaster(@SCRequestDataset("divisionUnit") String divisionUnit,
                                              @SCRequestDataset("obid") String obid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        if(!StringUtils.isEmpty(obid)){
            WBSTemplateMaster wbsTemplateMaster = DomUtil.toDom(obid);
            WBSTemplateMasterVO wbsTemplateMasterVO = wbsTemplateMaster.getVo();
            rm.setData("wbsTemplateMasterVO", wbsTemplateMaster.getVo());
            rm.setData("projectPhases", wbsTemplateService.getAppliedPhaseInfoList(obid,true));
            rm.setData("wbsPhaseTemplate", wbsTemplateService.getWBSPhaseTemplateList(obid));
            rm.setData("definedProjectGrade", ProjectGrade.getUsingProjectGradeByDevelopType(wbsTemplateMasterVO.getDivisionUnit(), wbsTemplateMasterVO.getDevelopmentType(),false));
        }
        
        rm.setData("developmentType", wbsTemplateService.getUsingProjectDevelopmentType(divisionUnit));
        rm.setData("projectLifeCycle", wbsTemplateService.getUsingProjectLifeCycleList(divisionUnit));
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/createWBSTemplateMaster.do")
    public String createWBSTemplateMaster(@SCRequestDataset("wbsTemplateMasterVO") WBSTemplateMasterVO wbsTemplateMasterVO, 
                                          @SCRequestDataset("projectPhases") List<ProjectPhaseVO> projectPhases,
                                          ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnCreateWBSTemplateMaster(wbsTemplateMasterVO, projectPhases);
        rm.setData("wbsTemplateMasterObid", wbsTemplateMasterVO.getObid());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/updateWBSTemplateMaster.do")
    public String updateWBSTemplateMaster(@SCRequestDataset("wbsTemplateMasterVO") WBSTemplateMasterVO wbsTemplateMasterVO,
                                          @SCRequestDataset("projectPhases") List<ProjectPhaseVO> projectPhases,
                                          ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnUpdateWBSTemplateMaster(wbsTemplateMasterVO, projectPhases);
        rm.setData("wbsTemplateMasterObid", wbsTemplateMasterVO.getObid());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/retrieveWBSTemplateMaster.do")
    public String retrieveWBSTemplateMaster(@SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        WBSTemplateMaster wbsTemplateMaster = DomUtil.toDom(obid);
        WBSTemplateMasterVO wbsTemplateMasterVO = wbsTemplateMaster.getVo();
        rm.setData("wbsTemplateMasterVO", wbsTemplateMasterVO);
        rm.setData("projectPhases", wbsTemplateService.getAppliedPhaseInfoList(obid,false));
        rm.setData("definedProjectGrade", wbsTemplateService.getUsingProjectGradeByDevelopType(wbsTemplateMasterVO.getDivisionUnit(), wbsTemplateMasterVO.getDevelopmentType(),false));
        rm.setModelMap(map);
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/reviseWBSTemplateMaster.do")
    public String reviseWBSTemplateMaster(@SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(obid);
        if(!wbsTemplateMaster.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE)){
            throw new FoundationException("Cannot Revise.Current state is" + wbsTemplateMaster.getStates() + ".");
        }
        WBSTemplateMasterVO newWBSTemplateMasterVO = wbsTemplateService.txnReviseWBSTemplateMaster(wbsTemplateMaster.getVo());
        rm.setData("newWBSTemplateMasterVO", newWBSTemplateMasterVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbsTemplate/cancelReviseWBSTemplateMaster.do")
    public String cancelReviseWBSTemplateMaster(@SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(obid);
        rm.setData("previousMasterVO", wbsTemplateMaster.getPreviousRevision());
        WBSTemplateMasterVO wbsTemplateMasterVO = wbsTemplateMaster.getVo();
        wbsTemplateService.txnCancelReviseWBSTemplateMaster(wbsTemplateMasterVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbsTemplate/validateWBSTemplateMaster.do")
    public String validateWBSTemplateMaster(@SCRequestDataset("obid") String obid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(obid);
        wbsTemplateMaster.validateWBSTemplateMaster();
        // TODO 
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbsTemplate/publishWBSTemplateMaster.do")
    public String publishWBSTemplateMaster(@SCRequestDataset("obid") String obid, @SCRequestDataset("mailVO") MailVO mailVO, @SCRequestDataset("recipientList") List<UsersVO> recipientList, ModelMap map){
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        
        try {
            WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(obid);
            WBSTemplateMasterVO wbsTemplateMasterVO = wbsTemplateMaster.getVo();
            wbsTemplateService.txnPublishTemplateMaster(wbsTemplateMasterVO, mailVO, recipientList);
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
    
    @RequestMapping("/wbsTemplate/sendMailWBSTemplateMaster.do")
    public String sendMailWBSTemplateMaster(@SCRequestDataset("obid") String obid, @SCRequestDataset("mailVO") MailVO mailVO, @SCRequestDataset("recipientList") List<UsersVO> recipientList, ModelMap map){
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        
        try {
            WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(obid);
            WBSTemplateMasterVO wbsTemplateMasterVO = wbsTemplateMaster.getVo();
            wbsTemplateService.txnSendMailTemplateMaster(wbsTemplateMasterVO, mailVO, recipientList);
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
    
    @RequestMapping("/wbsTemplate/initWBSItemTemplateList.do")
    public String initWBSItemTemplateManagement(ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
//        rm.setData("divisionUnit", userSession.getDivisionUnitCode());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/getWBSItemTemplatesStructure.do")
    public String getWBSItemTemplatesStructure(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                               @SCRequestDataset("withJobActivity") String withJobActivity, 
                                               @SCRequestDataset("openedList") String openedList, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        String[] openedArray = openedList.split(",");
        List<BusinessObjectRootVO> wbsItemTemplateList = wbsTemplateService.getWBSItemTemplatesStructure(wbsTemplateMasterObid, withJobActivity);
        Set<String> namesSet = new HashSet<String>();
        for(BusinessObjectRootVO vo : wbsItemTemplateList){
            if(vo instanceof WBSItemTemplatesVO){
                boolean isOpen = false;
                if(!StrUtil.isEmpty(((WBSItemTemplatesVO)vo).getActivityMasterName())) namesSet.add(((WBSItemTemplatesVO)vo).getActivityMasterName());
                for(int i = 0; i < openedArray.length; i++){
                    if(openedArray[i].equals(vo.getNames())){isOpen = true;break;}
                }
                vo.setOutDataAttributeValue("isOpen", isOpen);
            }
        }
        List<ActivityTemplateMasterVO> activityTemplateMasterList = wbsTemplateService.findActivityTemplateMasters(namesSet);
        rm.setData("wbsItemTemplates", wbsItemTemplateList);
        rm.setData("activityTemplateMasters", activityTemplateMasterList);
        rm.setData("projectGrade", wbsTemplateService.getValidGradeListForTemplateMaster(wbsTemplateMasterObid,false));
        rm.setData("wbsTemplateMasterObid", wbsTemplateMasterObid);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/completeWBSTemplateMaster.do")
    public String completeWBSTemplateMaster(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid, ModelMap map){
        
        wbsTemplateService.txnCompleteWBSTemplateMaster(wbsTemplateMasterObid);
        ResponseMapper rm = new ResponseMapper();
        rm.setData("wbsTemplateMasterObid", wbsTemplateMasterObid);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/inActiveWBSTemplateMaster.do")
    public String inActiveWBSTemplateMaster(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid, ModelMap map){
        
        wbsTemplateService.txnInActiveWBSTemplateMaster(wbsTemplateMasterObid);
        ResponseMapper rm = new ResponseMapper();
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/initCreateProjectPhase.do")
    public String initCreateProjectPhase(@SCRequestDataset("obid") String wbsTemplateMasterObid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        
        WBSTemplateMaster wbsTemplateMaster = new WBSTemplateMaster(wbsTemplateMasterObid);
        
        rm.setData("wbsTemplateMasterVO", wbsTemplateMaster.getVo());
        rm.setData("projectPhasesListForCreate", wbsTemplateService.getProjectPhasesListForCreate(wbsTemplateMasterObid,true));
        
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/createProjectPhase.do")
    public String createProjectPhase(@SCRequestDataset("obid") String wbsTemplateMasterObid,
                                     @SCRequestDataset("projectPhases") List<ProjectPhaseVO> projectPhases,
                                     ModelMap map){
        
        wbsTemplateService.txncreateProjectPhase(new WBSTemplateMaster(wbsTemplateMasterObid), projectPhases);
        ResponseMapper rm = new ResponseMapper();
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/initWBSActivityTemplate.do")
    public String initWBSActivityTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                          @SCRequestDataset("obid") String obid, 
                                          ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);
        
        if(!StringUtils.isEmpty(obid)){
            BusinessObjectRoot bizObj = new BusinessObjectRoot(obid);
            if(bizObj.getVo() instanceof WBSItemTemplatesVO){
                WBSItemTemplates wbsActivityTemplate = (WBSItemTemplates)DomUtil.toDom(obid);
                WBSItemTemplatesVO wbsActivityTemplateVO = wbsActivityTemplate.getVo();
                rm.setData("wbsActivityTemplateVO", wbsActivityTemplateVO);
                rm.setData("projectGrade", wbsTemplateService.getUsingProjecGrade(wbsTemplateMasterDom.getVo().getDivisionUnit()));
                if(!"None".equals(wbsActivityTemplateVO.getRoleList())){
                    rm.setData("roleVOList", wbsActivityTemplate.getAllocatedRole(wbsTemplateMasterDom));
                }
                
                rm.setData("templateDependency", wbsTemplateService.getTemplateDependency(wbsTemplateMasterObid,obid));
                rm.setData("documentTemplate", wbsTemplateService.retrieveRecomendedDocumentTemplate(wbsTemplateMasterObid,obid));                
            }
        }
        
        rm.setData("completeTypeRangeList", codeService.getRangeList("WBSItemTemplates.completeType"));
        rm.setData("activityCategoryRangeList", codeService.getRangeList("WBSItemTemplates.activityCategory"));
        
        rm.setData("startValidationMethodList", WBSTemplateUtil.getActivityStandardStartValidationMethodList());
        rm.setData("startExecutionMethodList", WBSTemplateUtil.getActivityStandardStartMethodList());
        rm.setData("completeValidationMethodList", WBSTemplateUtil.getActivityStandardCompleteValidationMethodList());
        rm.setData("postExecutionMethodList", WBSTemplateUtil.getActivityStandardPostMethodList());
        
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/retrieveWBSActivityTemplate.do")
    public String retrieveWBSActivityTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
            @SCRequestDataset("obid") String obid, 
            ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);
        
        BusinessObjectRoot bizObj = new BusinessObjectRoot(obid);
        if(bizObj.getVo() instanceof WBSItemTemplatesVO){
            WBSItemTemplates wbsActivityTemplate = (WBSItemTemplates)DomUtil.toDom(obid);
            WBSItemTemplatesVO wbsActivityTemplateVO = wbsActivityTemplate.getVo();
            rm.setData("wbsActivityTemplateVO", wbsActivityTemplateVO);
            rm.setData("projectGrade", wbsTemplateService.getUsingProjecGrade(wbsTemplateMasterDom.getVo().getDivisionUnit()));
            if(!"None".equals(wbsActivityTemplateVO.getRoleList())){
                rm.setData("roleVOList", wbsActivityTemplate.getAllocatedRole(wbsTemplateMasterDom));
            }
            
            wbsActivityTemplate.setOutDataAttributeValue("startValidationMethodDesc", WBSTemplateUtil.getActivityStandardStartValidationMethodDescription(wbsActivityTemplateVO.getStartValidationMethod()));
            wbsActivityTemplate.setOutDataAttributeValue("startExecutionMethodDesc", WBSTemplateUtil.getActivityStandardStartMethodDescription(wbsActivityTemplateVO.getStartExecutionMethod()));
            wbsActivityTemplate.setOutDataAttributeValue("completeValidationMethodDesc", WBSTemplateUtil.getActivityStandardCompleteValidationMethodDescription(wbsActivityTemplateVO.getCompleteValidationMethod()));
            wbsActivityTemplate.setOutDataAttributeValue("postExecutionMethodDesc", WBSTemplateUtil.getActivityStandardPostMethodDescription(wbsActivityTemplateVO.getPostExecutionMethod()));
            
            rm.setData("templateDependency", wbsTemplateService.getTemplateDependency(wbsTemplateMasterObid,obid));
            rm.setData("documentTemplate", wbsTemplateService.retrieveRecomendedDocumentTemplate(wbsTemplateMasterObid,obid));                
        }
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/createWBSActivityTemplate.do")
    public String createWBSActivityTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                            @SCRequestDataset("parentObid") String parentObid,
                                            @SCRequestDataset("wbsActivityTemplatesVO") WBSActivityTemplateVO wbsActivityTemplatesVO,
                                            @SCRequestDataset("activityMasterObid") String activityMasterObid,
                                            @SCRequestDataset("addingPosition") String addingPosition,
                                            @SCRequestDataset("targetSequences") String targetSequences,
                                            @SCRequestDataset("dependencyCreateList") List<TemplateDependencyVO> dependencyCreateList,
                                            ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnCreateWBSItemTemplate(wbsTemplateMasterObid, parentObid, wbsActivityTemplatesVO, activityMasterObid, dependencyCreateList, addingPosition, !"null".equals(targetSequences) ? Integer.parseInt(targetSequences) : null );
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/copyWBSActivityTemplates.do")
    public String copyWBSActivityTemplates(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                           @SCRequestDataset("sourceWBSTemplateMasterObid") String sourceWBSTemplateMasterObid,
                                           @SCRequestDataset("copiedWBSActivityTemplatesList") List<WBSItemTemplatesVO> copiedWBSItemTemplatesVOList,
                                           @SCRequestDataset("sourceTopWBSActivityTemplates") WBSItemTemplatesVO sourceTopWBSActivityTemplatesVO,
                                           @SCRequestDataset("targetWBSActivityTemplates") WBSItemTemplatesVO targetWBSItemTemplatesVO,
                                           @SCRequestDataset("isSynchronize") String isSynchronize,
                                            ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnCopyWBSActivityTemplates(wbsTemplateMasterObid, sourceWBSTemplateMasterObid, copiedWBSItemTemplatesVOList, sourceTopWBSActivityTemplatesVO, targetWBSItemTemplatesVO,"Y".equals(isSynchronize));
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/updateWBSActivityTemplate.do")
    public String updateWBSActivityTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                            @SCRequestDataset("parentObid") String parentObid,
                                            @SCRequestDataset("wbsActivityTemplatesVO") WBSActivityTemplateVO wbsActivityTemplatesVO,
                                            @SCRequestDataset("activityMasterObid") String activityMasterObid,
                                            @SCRequestDataset("dependencyCreateList") List<TemplateDependencyVO> dependencyCreateList,
                                            @SCRequestDataset("dependencyUpdateList") List<TemplateDependencyVO> dependencyUpdateList,
                                            @SCRequestDataset("dependencyDeleteList") List<TemplateDependencyVO> dependencyDeleteList,
                                            ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnUpdateWBSItemTemplate(wbsTemplateMasterObid, parentObid, wbsActivityTemplatesVO, activityMasterObid, dependencyCreateList, dependencyUpdateList, dependencyDeleteList);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/deleteWBSItemTemplate.do")
    public String deleteWBSItemTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                            @SCRequestDataset("obid") String obid, 
                                            ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        try{
            WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.contextBizObject, wbsTemplateMasterDom.getVo());
            wbsTemplateService.txnDeleteWBSItemTemplate(wbsTemplateMasterDom, obid);
        }finally{
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.isWBSAttrRefreshOff,false);
        }
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/reviseWBSItemTemplate.do")
    public String reviseWBSItemTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                              @SCRequestDataset("obid") String obid, 
                                              ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnReviseWBSItemTemplate(wbsTemplateMasterObid, obid);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/reviseAllWBSItemTemplate.do")
    public String reviseAllWBSItemTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnAllReviseWBSItemTemplate(wbsTemplateMasterObid);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/cancelReviseWBSItemTemplate.do")
    public String cancelReviseWBSItemTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
            @SCRequestDataset("obid") String obid, 
            ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnCancelReviseWBSItemTemplate(wbsTemplateMasterObid, obid);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/retrieveWBSSubProjectTemplate.do")
    public String retrieveWBSSubProjectTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid, @SCRequestDataset("obid") String obid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        
        if(!StringUtils.isEmpty(obid)){
            WBSSubProjectTemplate wbsSubProjectTemplate = (WBSSubProjectTemplate)DomUtil.toDom(obid);
            rm.setData("wbsSubProjectTemplateVO", wbsSubProjectTemplate.getVo());
        }
        
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/retrieveWBSPhaseTemplate.do")
    public String retrieveWBSPhaseTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid, @SCRequestDataset("obid") String obid, ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        
        if(!StringUtils.isEmpty(obid)){
            WBSPhaseTemplate wbsPhaseemplate = (WBSPhaseTemplate)DomUtil.toDom(obid);
            rm.setData("wbsPhaseTemplateVO", wbsPhaseemplate.getVo());
        }
        rm.setData("startValidationMethodList", WBSTemplateUtil.getActivityStandardStartValidationMethodList());
        rm.setData("startExecutionMethodList", WBSTemplateUtil.getActivityStandardStartMethodList());
        rm.setData("completeValidationMethodList", WBSTemplateUtil.getActivityStandardCompleteValidationMethodList());
        rm.setData("postExecutionMethodList", WBSTemplateUtil.getActivityStandardPostMethodList());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    
    @RequestMapping("/wbsTemplate/createWBSSubProjectTemplate.do")
    public String createWBSSubProjectTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                              @SCRequestDataset("wbsSubProjectTemplateVO") WBSSubProjectTemplateVO wbsItemTemplatesVO,
                                              ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnCreateWBSItemTemplate(wbsTemplateMasterObid, wbsTemplateMasterObid, wbsItemTemplatesVO );
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }

    @RequestMapping("/wbsTemplate/updateWBSSubProjectTemplate.do")
    public String updateWBSSubProjectTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                              @SCRequestDataset("wbsSubProjectTemplateVO") WBSSubProjectTemplateVO wbsSubProjectTemplateVO,
                                              ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnUpdateWBSItemTemplate(wbsTemplateMasterObid, wbsTemplateMasterObid, wbsSubProjectTemplateVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbsTemplate/updateWBSPhaseTemplate.do")
    public String updateWBSPhaseTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                              @SCRequestDataset("wbsPhaseTemplateVO") WBSPhaseTemplateVO wbsPhaseTemplateVO,
                                              ModelMap map){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnUpdateWBSItemTemplate(wbsTemplateMasterObid, wbsTemplateMasterObid, wbsPhaseTemplateVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/createJobActivityTemplate.do")
    public String createJobActivityTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                            @SCRequestDataset("parentObid") String parentObid,
                                            @SCRequestDataset("jobActivityTemplateVO") JobActivityTemplateVO jobActivityTemplateVO,
                                            @SCRequestDataset("addingPosition") String addingPosition,
                                            @SCRequestDataset("targetSequences") String targetSequences,
                                            ModelMap map){
        
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnCreateJobActivityTemplate(wbsTemplateMasterObid, parentObid, jobActivityTemplateVO, addingPosition, !"null".equals(targetSequences) ? Integer.parseInt(targetSequences) : null);
        
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbs/getJobActivityTemplate.do")
    public String getJobActivityTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                         @SCRequestDataset("obid") String obid,
                                         ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        if(!NullUtil.isNone(obid)){
            rm.setData("jobActivityTemplateVO", new JobActivityTemplate(obid).getVo());
        }
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/updateJobActivityTemplate.do")
    public String updateJobActivityTemplate( @SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                             @SCRequestDataset("jobActivityTemplateVO") JobActivityTemplateVO jobActivityTemplateVO, 
                                             ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnUpdateJobActivityTemplate(jobActivityTemplateVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/initCreateActivityTemplateMaster.do")
    public String initCreateActivityTemplateMaster(ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        rm.setData("developmentType", wbsTemplateService.getUsingProjectDevelopmentType(userSession.getDivisionUnitCode()));
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/createActivityTemplateMaster.do")
    public String createActivityTemplateMaster(@SCRequestDataset("activityTemplateMasterVO") ActivityTemplateMasterVO activityTemplateMasterVO, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnCreateActivityTemplateMaster(activityTemplateMasterVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/initDataActivityTemplateMaster.do")
    public String initDataActivityTemplateMaster(@SCRequestDataset("activityTemplateMasterObid") String activityTemplateMasterObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        
        if(!StringUtils.isEmpty(activityTemplateMasterObid)){
            ActivityTemplateMaster activityTemplateMaster = DomUtil.toDom(activityTemplateMasterObid);
            rm.setData("activityTemplateMasteVO", activityTemplateMaster.getVo());
            if(!"None".equals(activityTemplateMaster.getVo().getDefaultRoleList())){
                rm.setData("roleVOList", activityTemplateMaster.retrieveAllocatedRoleATActivityTemplateItems());
            }
        }
        rm.setData("appliedScopeRangeList", codeService.getRangeList("ActivityTemplateMaster.appliedScope"));
        rm.setData("defaultActivityTypeRangeList", codeService.getRangeList("ActivityTemplateMaster.defaultActivityType"));
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/retrieveActivityTemplateMaster.do")
    public String retrieveActivityTemplateMaster(@SCRequestDataset("activityTemplateMasterObid") String activityTemplateMasterObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        rm.setData("activityTemplateMasteVO", DomUtil.toDom(activityTemplateMasterObid).getVo());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/updateActivityTemplateMaster.do")
    public String updateActivityTemplateMaster(@SCRequestDataset("activityTemplateMasterVO") ActivityTemplateMasterVO activityTemplateMasterVO, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnUpdateActivityTemplateMaster(activityTemplateMasterVO);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/retrieveUsedRoleAtWBSTemplate.do")
    public String retrieveUsedRoleAtWBSTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        rm.setData("projectRole", wbsTemplateService.retrieveUsedRoleAtWBSTemplate(wbsTemplateMasterObid));
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/retrieveActivityTemplateMasterList.do")
    public String retrieveActivityTemplateMasterList(ActivityTemplateMasterSearchVO searchInfo, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        List<ActivityTemplateMasterVO> result = wbsTemplateService.retrieveActivityTemplateMasterList(searchInfo);
        
        int totalRow = 0;
        int currentPage = 0;
        if( result instanceof PagingList ){
            PagingList<ActivityTemplateMasterVO> pageResult = (PagingList<ActivityTemplateMasterVO>)result;
            totalRow = pageResult.getRows();
            currentPage = pageResult.getCurrentPage();
        }
        
        rm.setData( "searchInfo" , searchInfo);
        rm.setData( "result" , result);
        rm.setData( "currentPage", currentPage );
        rm.setData( "totRecCount", totalRow );
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/retrieveProjectActivityDocumentTemplateList.do")
    public String retrieveProjectActivityDocumentTemplateList(ProjectActivityDocumentTemplateSearchVO searchInfo, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        List<ProjectActivityDocumentTemplateVO> result = wbsTemplateService.retrieveProjectActivityDocumentTemplateList(searchInfo);
        
        int totalRow = 0;
        int currentPage = 0;
        if( result instanceof PagingList ){
            PagingList<ProjectActivityDocumentTemplateVO> pageResult = (PagingList<ProjectActivityDocumentTemplateVO>)result;
            totalRow = pageResult.getRows();
            currentPage = pageResult.getCurrentPage();
        }else{
            totalRow = searchInfo.getTotalCount();
        }
        
        rm.setData( "searchInfo" , searchInfo);
        rm.setData( "result" , result);
        rm.setData( "currentPage", currentPage );
        rm.setData( "totRecCount", totalRow );
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/initCloneWbsTemplateMaster.do")
    public String initCloneWbsTemplateMaster(@SCRequestDataset("fromMasterObid") String fromMasterObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        
        WBSTemplateMaster fromTemplateMaster = new WBSTemplateMaster(fromMasterObid);
        rm.setData( ApplicationBizConstants.USER_DIVISION_LIST, new Users( userSession.getUserBizObid()).retrieveDivisionUnitList() );
        rm.setData("projectPhases", wbsTemplateService.getAppliedPhaseInfoList(fromMasterObid,false));
        rm.setData("definedProjectGrade", wbsTemplateService.getUsingProjectGradeByDevelopType(fromTemplateMaster.getVo().getDivisionUnit(), fromTemplateMaster.getVo().getDevelopmentType(),false));
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/cloneWbsTemplateMaster.do")
    public String cloneWbsTemplateMaster(@SCRequestDataset("fromMasterObid") String fromMasterObid,
            @SCRequestDataset("targetMasterVO") WBSTemplateMasterVO targetMasterVO,
            ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnCloneWbsTemplateMaster(fromMasterObid, targetMasterVO);
        
        rm.setData("clonedMasterObid", targetMasterVO.getObid());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbsTemplate/initCloneWbsTemplateMasterFromOtherDvision.do")
    public String initCloneWbsTemplateMasterFromOtherDvision(ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        
        Users curUser = new Users( userSession.getUserBizObid());
        rm.setData(ApplicationBizConstants.USER_DIVISION_LIST, curUser.retrieveDivisionUnitList());
        rm.setData("userDivision", userSession.getDivisionUnitCode());
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbsTemplate/cloneWbsTemplateMasterFromOtherDvision.do")
    public String cloneWbsTemplateMasterFromOtherDvision(@SCRequestDataset("toDivision") String toDivision,
                                                         @SCRequestDataset("wbsTemplateMasterList") List<WBSTemplateMasterVO> wbsTemplateMasterList, 
                                                         ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        List<WBSTemplateMasterVO> clonedList = wbsTemplateService.txnCloneWbsTemplateMasterFromOtherDivision(wbsTemplateMasterList, toDivision);
        rm.setData("clonedList", clonedList);
        rm.setModelMap(map);        
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping("/wbsTemplate/viewGantt.do")
    public String viewGantt(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        // gantt test
        List<DHTMLGanttTemplateActivityVO> ganttActivityList = new ArrayList<DHTMLGanttTemplateActivityVO>();
        List<DHTMLGanttLinkVO> linkList = new ArrayList<DHTMLGanttLinkVO>();
        wbsTemplateService.getWBSTemplateListForGantt(new WBSTemplateMaster(wbsTemplateMasterObid).getVo(), ganttActivityList, linkList);
        
        rm.setData( "ganttActivityList", ganttActivityList );
        rm.setData( "linkList", linkList );
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/getProjectRoleVOList.do")
    public String getProjectRoleVOList(@SCRequestDataset("projectRoleCodeList") String projectRoleCodeList, ModelMap map){    
        ResponseMapper rm = new ResponseMapper();
        rm.setData( "projectRoleVOList", ProjectRole.getProjectRoleVOList(projectRoleCodeList) );
        rm.setModelMap(map);     
        return GlobalConstants.AJAX_VIEW;
    }
    @RequestMapping( "/wbsTemplate/excelDownloadTemplateListForUpdate.do" )
    public ExcelDownloadView excelDownloadTemplateListForUpdate(@SCRequestDataset("activityTemplateMasterObid") String activityTemplateMasterObid, ModelMap map){
        XSSFWorkbook workbook = wbsTemplateService.getTemplateMasterExcelDownloadForUpate(activityTemplateMasterObid);
        return new ExcelDownloadView(workbook, "WBS Template" + ".xlsx");
    }

//    @RequestMapping("/wbsTemplate/exportWBSTemplateToExcel.do")
//    public ExcelDownloadView exportWBSTemplateToExcel(String wbsTemplateMasterObid, boolean includeDoc) throws Exception{
//        ExcelDownloadView excelDownloadView = wbsTemplateService.exportWBSTemplateToExcel(wbsTemplateMasterObid, includeDoc,false);
//        return excelDownloadView;
//    }
//
//    @RequestMapping("/wbsTemplate/exportWBSTemplateForImport.do")
//    public ExcelDownloadView exportWBSTemplateForImport(String wbsTemplateMasterObid, boolean includeDoc) throws Exception{
//        ExcelDownloadView excelDownloadView = wbsTemplateService.exportWBSTemplateForImport(wbsTemplateMasterObid, includeDoc,false);
//        return excelDownloadView;
//    }

    @RequestMapping("/wbsTemplate/createWBSTemplateDependency.do")
    public String createWBSTemplateDependency(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                              @SCRequestDataset("createList") List<TemplateDependencyVO> createList,
                                              ModelMap model){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnCreateWBSTemplateDependency(wbsTemplateMasterObid, createList);
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/getSubWBSItemTemplate.do")
    public String getSubWBSItemTemplate(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid,
                                        @SCRequestDataset("wbstemplateObid") String wbstemplateObid,
                                        ModelMap model){
        ResponseMapper rm = new ResponseMapper();
        rm.setData( "wbsTemplateList", wbsTemplateService.getSubWBSItemTemplate(wbsTemplateMasterObid, wbstemplateObid));
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/getTopLevelWBSItemTemplateList.do")
    public String getWBSPhaseTemplateList(@SCRequestDataset("wbsTemplateMasterObid") String wbsTemplateMasterObid, ModelMap model){
        ResponseMapper rm = new ResponseMapper();
        rm.setData( "wbsTemplateList", wbsTemplateService.getWBSItemTemplatesStructure(wbsTemplateMasterObid, 1));
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/upateTemplateSequences.do")
    public String upateTemplateSequences(@SCRequestDataset("hasActivityTemplateList") List<HasActivityTemplateVO> hasActivityTemplateList, ModelMap model){
        ResponseMapper rm = new ResponseMapper();
        wbsTemplateService.txnUpateTemplateSequences(hasActivityTemplateList);
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/wbsTemplate/wbsTemplateMasterDivisionFilter.do")
    public String wbsTemplateMasterDivisionFilter(String obid, ModelMap model){
        ResponseMapper rm = new ResponseMapper();
        rm.setData(wbsTemplateService.getDivisionCombo());
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    
    /**
     * Activity Template Master 추가
     *
     * @param files
     * @param uploadId
     * @param uploadType
     * @param model
     * @return
     */
//    @RequestMapping("/wbsTemplate/excelImportActivityTemplateMaster.do")
//    public String excelImportActivityTemplateMaster(@RequestParam MultipartFile files,
//            @RequestParam String uploadId,
//            @RequestParam String uploadType,
//            Model model){
//        long startTime = System.currentTimeMillis();
//        List<ActivityTemplateMasterVO> dataList = new ArrayList<ActivityTemplateMasterVO>();
//        HashMap<String, Object> detailData = new HashMap<String, Object>();
//        HashMap<String, Object> result = null;
//        int totalCount = 0;
//  
//        try{
//            if( !files.isEmpty() ){
//                InputStream in = files.getInputStream();
//                HSSFWorkbook workbook = new HSSFWorkbook(in);
//                HSSFSheet sheet = workbook.getSheetAt(0);
//                ActivityTemplateMasterVO dataRow = null;
//                HSSFRow row = null;
//                for( int inx = 1; inx <= sheet.getLastRowNum(); inx++ ){
//                    row = sheet.getRow(inx);
//                    dataRow = new ActivityTemplateMasterVO();
//                    dataRow.setNames( CommonUtil.getExcelCellValue(row.getCell( 0 )) );
//                    dataRow.setTitles( CommonUtil.getExcelCellValue(row.getCell( 1 )) );
//                    dataRow.setDescriptions( CommonUtil.getExcelCellValue(row.getCell( 2 )) );
//                    dataRow.setStates( CommonUtil.getExcelCellValue(row.getCell(3)) );
//                    dataRow.setActivityNameKor( CommonUtil.getExcelCellValue(row.getCell( 4 )) );
//                    dataRow.setActivityNameEng( CommonUtil.getExcelCellValue(row.getCell( 5 )) );
//                    dataRow.setActivityNameChi( CommonUtil.getExcelCellValue(row.getCell( 6 )) );
//                    dataRow.setIsCommon( CommonUtil.getExcelCellValue(row.getCell( 7 )) );
//                    dataRow.setDefaultActivityType( CommonUtil.getExcelCellValue(row.getCell( 8 )) );
//                    dataRow.setIsKeyActivity( CommonUtil.getExcelCellValue(row.getCell( 9 )) );
//                    dataRow.setDefaultRoleList( CommonUtil.getExcelCellValue(row.getCell( 10 )) );
//                    dataRow.setDefaultIsChecklistItem( CommonUtil.getExcelCellValue(row.getCell( 11 )) );
//                    dataRow.setIsOnlyOnePerTemplate( CommonUtil.getExcelCellValue(row.getCell( 12 )) );
//                    dataRow.setIsSystemOwnerItem( CommonUtil.getExcelCellValue(row.getCell( 13 )) );
//                    dataRow.setAppliedScope( CommonUtil.getExcelCellValue(row.getCell( 14 )) );
//                    dataRow.setActivityUrl( CommonUtil.getExcelCellValue(row.getCell( 15 )) );
//                    dataRow.setActivityCategory( CommonUtil.getExcelCellValue(row.getCell( 16 )) );
//                    dataRow.setAppliedOrganizationList( CommonUtil.getExcelCellValue(row.getCell( 17 )) );
//                    dataRow.setStartValidationMethod( CommonUtil.getExcelCellValue(row.getCell( 18 )) );
//                    dataRow.setStartExecutionMethod( CommonUtil.getExcelCellValue(row.getCell( 19 )) );
//                    dataRow.setCompleteValidationMethod( CommonUtil.getExcelCellValue(row.getCell( 20 )) );
//                    dataRow.setPostExecutionMethod( CommonUtil.getExcelCellValue(row.getCell( 21 )) );
//                    dataRow.setInboxCompletionType( CommonUtil.getExcelCellValue(row.getCell( 22 )) );
//                    dataRow.setIsAutoComplete( CommonUtil.getExcelCellValue(row.getCell( 23 )) );
//                    
//                    dataList.add( dataRow );
//                }
//                
//                totalCount = dataList.size();
//                
//                result = wbsTemplateService.createActivityTemplateMaster( dataList );
//                workbook.close();
//                in.close();
//            }
//  
//            result.put( "totalCount", totalCount );
//  
//            // 소요시간 표시
//            long endTime = System.currentTimeMillis();
//            String processingTime = Math.round((endTime - startTime) / 1000.0f) + " seconds";
//            result.put( "message", result.get( "message" ) + "\nProcessing Time : " + processingTime);
//        }
//        catch( Exception e ){
//            e.printStackTrace();
//            result = new HashMap<String, Object>();
//            result.put( "message", e.getClass() );
//        }
//        finally{
//            detailData.put( GlobalConstants.D_UPLOAD_ID, uploadId ); // 지우지 말 것.
//            detailData.put( "dataList", dataList ); // 지우지 말 것.
//            detailData.put( "result", result ); // 지우지 말 것.
//  
//            HashMap<String, Object> resultData = new HashMap<String, Object>();
//            resultData.put( GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_SUCCESS );
//            resultData.put( GlobalConstants.M_MESSAGE, "" );
//            resultData.put( GlobalConstants.M_DATA, detailData );
//  
//            JSONObject js = JSONObject.fromObject( resultData );
//            model.addAttribute( "result", js );
//        }
//
//        return "common/file/uploadResponse";
//    }
    
//    /**
//    *
//    * @param files
//    * @param uploadId
//    * @param uploadType
//    * @param model
//    * @return
//    */
//   @RequestMapping("/wbsTemplate/excelImportDocMandantoryInfo.do")
//   public String excelImportDocMandantoryInfo(@RequestParam MultipartFile files,
//           @RequestParam String uploadId,
//           @RequestParam String uploadType,
//           @RequestParam String wbsTemplateMasterObid,
//           Model model){
//       long startTime = System.currentTimeMillis();
//       List<WBSActivityTemplateVO> dataList = new ArrayList<WBSActivityTemplateVO>();
//       HashMap<String, Object> detailData = new HashMap<String, Object>();
//       HashMap<String, Object> result = null;
//       HashMap<String, Object> propertyParams = new HashMap<String, Object>();
//       int totalCount = 0;
//       int successCount = 0;
//       int failCount = 0;
//       
//       try {
//           if ( !files.isEmpty() ) {
//               InputStream in = files.getInputStream();
//               XSSFWorkbook workbook = new XSSFWorkbook(in);
//               XSSFSheet sheet = workbook.getSheetAt(0);
//               XSSFRow row = null;
//               WBSActivityTemplateVO dataRow = null;
//               
//               XSSFRow titleRow = sheet.getRow(0);
//               String skipInfo = "";
//               String mandantory = "";
//               String type = "";
//               String gradeSkipInfo = "";
//               
//               for ( int inx = 1; inx <= sheet.getLastRowNum(); inx++ ) {
//                   row = sheet.getRow(inx);
//                   dataRow = new WBSActivityTemplateVO();
//                   dataRow.setNames( CommonUtil.getExcelCellValue( row.getCell(0)) );
//                   
//                   propertyParams.put("no", CommonUtil.getExcelCellValue( row.getCell(0)) );
//                   propertyParams.put("division", CommonUtil.getExcelCellValue( row.getCell(1)) );
//                   propertyParams.put("wbsGubun", CommonUtil.getExcelCellValue( row.getCell(2)) );
//                   propertyParams.put("level", CommonUtil.getExcelCellValue( row.getCell(3)) );
//                   
//                   type = CommonUtil.getExcelCellValue( row.getCell(4));
//                   propertyParams.put("type", type );
//                   
//                   propertyParams.put("activityCode", CommonUtil.getExcelCellValue( row.getCell(5)) );
//                   propertyParams.put("activityName", CommonUtil.getExcelCellValue( row.getCell(6)) );
//                   propertyParams.put("activityObid", CommonUtil.getExcelCellValue( row.getCell(7)) );
//                   propertyParams.put("documentName", CommonUtil.getExcelCellValue( row.getCell(8)) );
//                   
//                   mandantory = CommonUtil.getExcelCellValue( row.getCell(9));
//                   if("REQ".equals(mandantory)){
//                       propertyParams.put("mandantory", "Y");
//                   } else if("DOC".equals(mandantory)){
//                       propertyParams.put("mandantory", "N");
//                   } else {
//                       propertyParams.put("mandantory", "");
//                   }
//                   
//                   propertyParams.put("fileName", CommonUtil.getExcelCellValue( row.getCell(10)) );
//                   propertyParams.put("documentObid", CommonUtil.getExcelCellValue( row.getCell(11)) );               
//                   
//                   skipInfo = "";
//                   
//                   if("Document".equals(type)) {
//                       gradeSkipInfo = CommonUtil.getExcelCellValue( row.getCell(8) );
//                       
//                       if("Y".equals(gradeSkipInfo)){
//                           skipInfo = gradeSkipInfo;
//                       }
//                       
//                       for ( int jnx = 9 ; jnx < titleRow.getLastCellNum(); jnx++ ) {                           
//                           gradeSkipInfo = CommonUtil.getExcelCellValue( row.getCell(jnx) );
//                           
//                           if("Y".equals(gradeSkipInfo)){
//                               skipInfo =  skipInfo + "," + gradeSkipInfo;
//                           }
//                       }                       
//                   } else {
//                       gradeSkipInfo = CommonUtil.getExcelCellValue( row.getCell(8) );
//                       if(!NullUtil.isNone(gradeSkipInfo)){
//                           skipInfo =  CommonUtil.getExcelCellValue( titleRow.getCell(8)) + ":" + gradeSkipInfo;
//                       }
//                       for ( int jnx = 9 ; jnx < titleRow.getLastCellNum(); jnx++ ) {
//                           gradeSkipInfo = CommonUtil.getExcelCellValue( row.getCell(jnx) );
//                                                      
//                           if(!NullUtil.isNone(gradeSkipInfo)){
//                               skipInfo =  skipInfo + "," + CommonUtil.getExcelCellValue( titleRow.getCell(jnx)) + ":" + gradeSkipInfo;
//                           }
//                       }
//                   }
//                   propertyParams.put("skipInfo",  skipInfo );
//                   
//                   dataList.add( dataRow );
//                   
//                   result = wbsTemplateService.updateSkipInfo( propertyParams );
//                   if ( org.springframework.util.StringUtils.isEmpty(result.get("message")) ) {
//                       successCount++;
//                   } else {
//                       failCount++;
//                   }
//                   
//               }
//               
//               totalCount = dataList.size();
//               
//               workbook.close();
//               in.close();
//           }
//           
//           result.put( "totalCount", totalCount );
//           result.put( "successCount", successCount);
//           result.put( "failCount", failCount);
//           
//           if ( successCount > 0 ) {
//               result.put("message", "Success!");
//           }
//           // 소요시간 표시
//           long endTime = System.currentTimeMillis();
//           String processingTime = Math.round((endTime - startTime) / 1000.0f) + " seconds";
//           result.put( "processingTime", processingTime);
//       } catch ( Exception e ) {
//           e.printStackTrace();
//           result = new HashMap<String, Object>();
//           result.put( "message", e.getClass() );
//       } finally {
//           detailData.put( GlobalConstants.D_UPLOAD_ID, uploadId ); // 지우지 말 것.
//           detailData.put( "dataList", dataList ); // 지우지 말 것.
//           detailData.put( "result", result ); // 지우지 말 것.
// 
//           HashMap<String, Object> resultData = new HashMap<String, Object>();
//           resultData.put( GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_SUCCESS );
//           resultData.put( GlobalConstants.M_MESSAGE, "" );
//           resultData.put( GlobalConstants.M_DATA, detailData );
// 
//           JSONObject js = JSONObject.fromObject( resultData );
//           model.addAttribute( "result", js );
//       }
//
//       return "common/file/uploadResponse";
//   }
   
   /**
   *
   * @param files
   * @param uploadId
   * @param uploadType
   * @param model
   * @return
   */
//  @RequestMapping("/wbsTemplate/excelImporWbsSkipInfo.do")
//  public String excelImporWbsSkipInfo(@RequestParam MultipartFile files,
//          @RequestParam String uploadId,
//          @RequestParam String uploadType,
//          @RequestParam String wbsTemplateMasterObid,
//          Model model){
//      long startTime = System.currentTimeMillis();
//      List<WBSActivityTemplateVO> dataList = new ArrayList<WBSActivityTemplateVO>();
//      HashMap<String, Object> detailData = new HashMap<String, Object>();
//      HashMap<String, Object> result = null;
//      HashMap<String, Object> propertyParams = new HashMap<String, Object>();
//      int totalCount = 0;
//      int successCount = 0;
//      int failCount = 0;
//      
//      try {
//          if ( !files.isEmpty() ) {
//              InputStream in = files.getInputStream();
//              XSSFWorkbook workbook = new XSSFWorkbook(in);
//              XSSFSheet sheet = workbook.getSheetAt(0);
//              XSSFRow row = null;
//              WBSActivityTemplateVO dataRow = null;
//              
//              XSSFRow titleRow = sheet.getRow(0);
//              String skipInfo = "";
//              
//              for ( int inx = 1; inx <= sheet.getLastRowNum(); inx++ ) {
//                  row = sheet.getRow(inx);
//                  dataRow = new WBSActivityTemplateVO();
//                  dataRow.setNames( CommonUtil.getExcelCellValue( row.getCell(0)) );
//                  
//                  propertyParams.put("no", CommonUtil.getExcelCellValue( row.getCell(0)) );
//                  propertyParams.put("division", CommonUtil.getExcelCellValue( row.getCell(1)) );
//                  propertyParams.put("wbsGubun", CommonUtil.getExcelCellValue( row.getCell(2)) );
//                  propertyParams.put("level", CommonUtil.getExcelCellValue( row.getCell(3)) );
//                  propertyParams.put("type", CommonUtil.getExcelCellValue( row.getCell(4)) );
//                  propertyParams.put("activityCode", CommonUtil.getExcelCellValue( row.getCell(5)) );
//                  propertyParams.put("activityName", CommonUtil.getExcelCellValue( row.getCell(6)) );
//                  propertyParams.put("activityObid", CommonUtil.getExcelCellValue( row.getCell(7)) );
//                  
//                  skipInfo = "";
//                  if(!NullUtil.isNone(CommonUtil.getExcelCellValue( row.getCell(8)) )) {
//                      skipInfo =  CommonUtil.getExcelCellValue( titleRow.getCell(8)) + ":" + CommonUtil.getExcelCellValue( row.getCell(8));
//                  }
//                  for ( int jnx = 9 ; jnx < titleRow.getLastCellNum(); jnx++ ) {
//                      if(!NullUtil.isNone(CommonUtil.getExcelCellValue( row.getCell(jnx)))){
//                          skipInfo =  skipInfo + "," + CommonUtil.getExcelCellValue( titleRow.getCell(jnx)) + ":" + CommonUtil.getExcelCellValue( row.getCell(jnx));
//                      }
//                  }
//                  propertyParams.put("skipInfo",  skipInfo );
//                  
//                  dataList.add( dataRow );
//                  
//                  result = wbsTemplateService.updateSkipInfo( propertyParams );
//                  if ( org.springframework.util.StringUtils.isEmpty(result.get("message")) ) {
//                      successCount++;
//                  } else {
//                      failCount++;
//                  }
//              }
//              
//              totalCount = dataList.size();
//              
//              workbook.close();
//              in.close();
//          }
//          
//          result.put( "totalCount", totalCount );
//          result.put( "successCount", successCount);
//          result.put( "failCount", failCount);
//          
//          if ( successCount > 0 ) {
//              result.put("message", "Success!");
//          }
//          // 소요시간 표시
//          long endTime = System.currentTimeMillis();
//          String processingTime = Math.round((endTime - startTime) / 1000.0f) + " seconds";
//          result.put( "processingTime", processingTime);
//      } catch ( Exception e ) {
//          e.printStackTrace();
//          result = new HashMap<String, Object>();
//          result.put( "message", e.getClass() );
//      } finally {
//          detailData.put( GlobalConstants.D_UPLOAD_ID, uploadId ); // 지우지 말 것.
//          detailData.put( "dataList", dataList ); // 지우지 말 것.
//          detailData.put( "result", result ); // 지우지 말 것.
//
//          HashMap<String, Object> resultData = new HashMap<String, Object>();
//          resultData.put( GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_SUCCESS );
//          resultData.put( GlobalConstants.M_MESSAGE, "" );
//          resultData.put( GlobalConstants.M_DATA, detailData );
//
//          JSONObject js = JSONObject.fromObject( resultData );
//          model.addAttribute( "result", js );
//      }
//
//      return "common/file/uploadResponse";
//  }
}
