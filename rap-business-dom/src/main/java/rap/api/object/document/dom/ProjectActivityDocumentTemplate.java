/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectActivityDocumentTemplate.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.document.dom;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.StringUtil;

import rap.api.object.document.model.ProjectActivityDocumentTemplateVO;
import rap.api.object.document.model.ProjectActivityDocumentVO;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.project.dom.Projects;
import rap.api.object.project.model.ActivityTemplateMasterVO;
import rap.api.object.project.model.ChangeProcessVO;
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.WBSActivityTemplateVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;


public class ProjectActivityDocumentTemplate extends ProjectDocumentTemplate {
    public ProjectActivityDocumentTemplate(String obid){
        super(obid);
    }
    public ProjectActivityDocumentTemplate(ProjectActivityDocumentTemplateVO vo){
        super(vo);
    }
    @Override
    public ProjectActivityDocumentTemplateVO getVo(){
        return (ProjectActivityDocumentTemplateVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeProjectActivityDocumentTemplate();
    }
    public void initializeProjectActivityDocumentTemplate(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "ProjectActivityDocumentTemplate[toString()=" + super.toString() + "]";
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

/**
 *
 * @return
 */
    public ProjectActivityDocumentTemplateVO getDetailVo(){
        ProjectActivityDocumentTemplateVO detailVO = getVo();
        String defaultActivityCode = detailVO.getDefaultActivityCode();
        if(StrUtil.isNotEmpty(defaultActivityCode)){
            ActivityTemplateMasterVO activityTemplateMasterVO = findObject(ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER, defaultActivityCode, false);
            String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale,ApplicationBizConstants.LANG_KO);
            String defaultActivityCodeDisplay = null;
            if(ApplicationBizConstants.LANG_KO.equals(locale)){
                defaultActivityCodeDisplay = activityTemplateMasterVO.getActivityNameKor();
            }else if (ApplicationBizConstants.LANG_EN.equals(locale)){
                defaultActivityCodeDisplay = activityTemplateMasterVO.getActivityNameEng(); 
            }else if (ApplicationBizConstants.LANG_CH.equals(locale)){
                defaultActivityCodeDisplay = activityTemplateMasterVO.getActivityNameChi(); 
            }
            detailVO.getOutData().put("defaultActivityCodeDisplay", defaultActivityCodeDisplay);
        }
        return detailVO;
    }

    public static String getDocumentNameDisplay(ProjectActivityDocumentTemplateVO projectActivityDocumentTemplateVO, String locale){
        if(StrUtil.isEmpty(locale)){
            locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale,ApplicationBizConstants.LANG_KO);
        }
        String documentNameDisplay = null;
        if(ApplicationBizConstants.LANG_KO.equals(locale)){
            documentNameDisplay = projectActivityDocumentTemplateVO.getDocumentNameKor();
        }else if (ApplicationBizConstants.LANG_EN.equals(locale)){
            documentNameDisplay = projectActivityDocumentTemplateVO.getDocumentNameEng();
        }else if (ApplicationBizConstants.LANG_CH.equals(locale)){
            documentNameDisplay = projectActivityDocumentTemplateVO.getDocumentNameChi();
        }
        return documentNameDisplay;
    }
    public String getDocumentNameDisplay(String locale){
        return getDocumentNameDisplay(this.getVo(), locale);
    }
    public FilesVO getFile(String assignedType){
        assignedType = StrUtil.defaultString(assignedType);
        List<FilesVO> releatedFiles = getReleatedFiles(true);
        FilesVO file = null;
        FilesVO defaultFile = null;
        for(FilesVO filesVO : releatedFiles){
            if("default".equals(filesVO.getAssignedType())){
                defaultFile = filesVO;
            }else if (assignedType.equals(filesVO.getAssignedType())){
                file = filesVO;
            }
        }
        if("Y".equals(this.getVo().getIsCommon()) || file == null ){
            file = defaultFile;
        }
        return file;
    }
    public static Map<String, String> getFileNamesForExcel(String docTemplateObids, String assignedType){
        if(StrUtil.isNotEmpty(docTemplateObids)){
            Map<String, String> rtnMap = new HashMap<String, String>();
            assignedType = StrUtil.defaultString(assignedType);
            StringBuffer selectPattern = new StringBuffer();
            StringBuffer wherePattern = new StringBuffer();
            StringBuffer paramPattern = new StringBuffer();
//            StringUtil.constructSelectPattern(selectPattern, "@this.fromObid.isCommon isCommon");
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[assignedType]", GlobalConstants.OQL_OPERATOR_IN, "default,"+assignedType);
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_IN, docTemplateObids);
            List<FilesVO> findObjects = findObjects(ApplicationSchemaConstants.BIZCLASS_FILES, selectPattern.toString(), wherePattern.toString(), paramPattern.toString());
            Map<String, List<FilesVO>> docMap = new HashMap<String, List<FilesVO>>();
            for (FilesVO filesVO : findObjects) {
//                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ : " +filesVO.getOutData().get("isCommon"));
                List<FilesVO> docFiles = docMap.get(filesVO.getFromObid());
                if(docFiles == null){
                    docFiles = new ArrayList<FilesVO>();   
                    docMap.put(filesVO.getFromObid(), docFiles); 
                }
                docFiles.add(filesVO);
            }
            Set<String> keySet = docMap.keySet();
            for (String docTemplateObid : keySet) {
                List<FilesVO> docFiles = docMap.get(docTemplateObid);
                StringBuffer fileNames = new StringBuffer();
                String defaultFileName = "";
                for(FilesVO filesVO : docFiles){
                    if("default".equals(filesVO.getAssignedType())){
                        defaultFileName = filesVO.getUserFileName();
                    }else if (assignedType.equals(filesVO.getAssignedType())){
                        if(fileNames.length() > 0){
                            fileNames.append(",");
                        }
                        fileNames.append(filesVO.getUserFileName());
                    }
                }
                if("Y".equals(((ProjectActivityDocumentTemplate)DomUtil.toDom(docTemplateObid)).getVo().getIsCommon()) || fileNames.length() == 0 ){
                    fileNames.setLength(0);
                    fileNames.append(defaultFileName);
                }
                rtnMap.put(docTemplateObid, fileNames.toString());
            }
            return rtnMap;
        }
        return null;
    }
    public String getFileNames(String assignedType){
        assignedType = StrUtil.defaultString(assignedType);
        List<FilesVO> releatedFiles = getReleatedFiles("", "default,"+assignedType);
        StringBuffer fileNames = new StringBuffer();
        String defaultFileName = "";
        for(FilesVO filesVO : releatedFiles){
            if("default".equals(filesVO.getAssignedType())){
                defaultFileName = filesVO.getUserFileName();
            }else if (assignedType.equals(filesVO.getAssignedType())){
                if(fileNames.length() > 0){
                    fileNames.append(",");
                }
                fileNames.append(filesVO.getUserFileName());
            }
        }
        if("Y".equals(this.getVo().getIsCommon()) || fileNames.length() == 0 ){
            fileNames.setLength(0);
            fileNames.append(defaultFileName);
        }
        return fileNames.toString();
    }
    public String getFileNames(){
        List<FilesVO> releatedFiles = getReleatedFiles();
        StringBuffer fileNames = new StringBuffer();
        for(FilesVO filesVO : releatedFiles){
            if(fileNames.length() > 0){
                fileNames.append(",");
            }
            fileNames.append(filesVO.getUserFileName());
        }
        return fileNames.toString();
    }
    
    public List<ProjectActivityDocumentVO> retrieveProjectActivityDocumentForFDR(String projectObid, String eventCode){
        List<ProjectActivityDocumentVO> retrieveProjectActivityDocumentForFDR = new ArrayList<ProjectActivityDocumentVO>();
        String names = getVo().getNames();
        //String defaultActivityCode = getVo().getDefaultActivityCode();
        //if(StrUtil.isNotEmpty(defaultActivityCode)){
            
            Projects projects = DomUtil.toDom(projectObid);
            ProjectScheduleVO projectLatestReleaseScheduleVO = projects.getProjectLatestReleaseSchedule();
        
            StringBuffer selectPattern = new StringBuffer();
            StringBuffer fromPattern = new StringBuffer();
            StringBuffer wherePattern = new StringBuffer();
            StringBuffer paramPattern = new StringBuffer();
            
            StringUtil.constructSelectPattern(selectPattern, "@this.[*]");
            StringUtil.constructSelectPattern(selectPattern, "@ACTIVITY.[obid] activityObid");
            StringUtil.constructSelectPattern(selectPattern, "@ACTIVITY.[activityOwnerList] activityOwnerList");
            StringUtil.constructSelectPattern(selectPattern, "@ACTIVITY.[isSkipped] isSkipped");
            
            fromPattern.append("<this>ThisConnectedWithTo<["+ApplicationSchemaConstants.RELCLASS_PROJECTDELIVERABLES+"]@PROJECTDELIVERABLES>+");
            fromPattern.append("<["+ApplicationSchemaConstants.RELCLASS_PROJECTDELIVERABLES+"]@PROJECTDELIVERABLES>FromConnectedWithThis<["+ApplicationSchemaConstants.BIZCLASS_PROJECTS+"]@PROJECT>+");
            fromPattern.append("<this>ThisConnectedWithTo<["+ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES+"]@ACTIVITYDELIVERABLES>+");
            fromPattern.append("<["+ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES+"]@ACTIVITYDELIVERABLES>FromConnectedWithThis<["+ApplicationSchemaConstants.BIZCLASS_WBSITEMS+"]@ACTIVITY>+");
            fromPattern.append("<["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"]@CONTROLLEDBYPROJECTSCHEDULECONTEXT>FromConnectedWithThis<["+ApplicationSchemaConstants.BIZCLASS_WBSITEMS+"]@ACTIVITY>+");
            fromPattern.append("<this>ThisConnectedWithTo<["+ApplicationSchemaConstants.RELCLASS_SOURCEDOCUMENTTEMPLATE+"]@SOURCEDOCUMENTTEMPLATE>+");
            fromPattern.append("<["+ApplicationSchemaConstants.RELCLASS_SOURCEDOCUMENTTEMPLATE+"]@SOURCEDOCUMENTTEMPLATE>FromConnectedWithThis<["+ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE+"]@TEMPLATE>+");
            
//            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_PROJECT_ACTIVITY_DOCUMENT_APPROVED);
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@ACTIVITYDELIVERABLES.[isLatest]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
            
            // PLM-1500 ���� event ���� ����
//            StringUtil.constructWherePattern(wherePattern, paramPattern, "@ACTIVITY.[phaseName]", GlobalConstants.OQL_OPERATOR_EQUAL, eventCode);
            
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@PROJECT.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, projectObid);
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@TEMPLATE.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, names);
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@CONTROLLEDBYPROJECTSCHEDULECONTEXT.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, projectLatestReleaseScheduleVO.getObid());
            //StringUtil.constructWherePattern(wherePattern, paramPattern, "@ACTIVITY.[originActivityCode]", GlobalConstants.OQL_OPERATOR_EQUAL, defaultActivityCode);
            retrieveProjectActivityDocumentForFDR = searchObjects(ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENT, 
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL, 
                    selectPattern.toString(), 
                    fromPattern.toString(),
                    wherePattern.toString(),
                    paramPattern.toString(),
                    false,
                    0);
            if(retrieveProjectActivityDocumentForFDR.size() > 0){
                selectPattern.setLength(0);
                wherePattern.setLength(0);
                paramPattern.setLength(0);
                StringUtil.addSortByPattern(selectPattern, "@this.[created] desc");
                List<ChangeProcessVO> changeProcessList = getRelatedObjectSet(retrieveProjectActivityDocumentForFDR,
                        ApplicationSchemaConstants.RELCLASS_CHANGEDITEMS, ApplicationSchemaConstants.BIZCLASS_CHANGEPROCESS,
                        GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(), wherePattern.toString(),
                        paramPattern.toString());
                for(ProjectActivityDocumentVO projectActivityDocumentVO : retrieveProjectActivityDocumentForFDR){
                    for(ChangeProcessVO changeProcessVO: changeProcessList){
                        if(projectActivityDocumentVO.getUniqueString().equals(changeProcessVO.getUniqueStringParent())){
                            projectActivityDocumentVO.getOutData().put("changeProcessObid", changeProcessVO.getObid());
                            // workflowService.retrieveApprovalHistoryList
                            break;
                        }
                    }
                }
            }
        //}
        return retrieveProjectActivityDocumentForFDR;
    }
    
    public List<WBSActivityTemplateVO> getRelatedWBSTemplateForRevise(){
        List<ProjectActivityDocumentTemplateVO> revisions = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE, getVo().getNames());
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringUtil.constructSelectPattern(selectPattern, "From[WBSManagedBy].To.divisionUnit divisionCode");
        StringUtil.constructSelectPattern(selectPattern, "From[WBSManagedBy].To.titles templateMasterTitles");
        StringUtil.constructSelectPattern(selectPattern, "From[WBSManagedBy].To.revision templateMasterRevision");
        StringUtil.constructSelectPattern(selectPattern, "From[WBSManagedBy].To.names templateMasterNames");
        StringUtil.constructSelectPattern(selectPattern, "From["+ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE+"{[obid] == rel_obid}].To.revision documentRevision");
        StringUtil.constructSelectPattern(selectPattern, "From["+ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE+"{[obid] == rel_obid}].To.documentNameEng documentNameEng");
        StringUtil.constructSelectPattern(selectPattern, "From["+ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE+"{[obid] == rel_obid}].To.documentNameKor documentNameKor");
        StringUtil.constructSelectPattern(selectPattern, "From["+ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE+"{[obid] == rel_obid}].To.documentNameChi documentNameChi");
        StringUtil.addSortByPattern(selectPattern, "divisionCode, templateMasterNames desc, documentNameEng");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[previousObid]", GlobalConstants.OQL_OPERATOR_EQUAL, "1");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "From[WBSManagedBy].To.previousObid", GlobalConstants.OQL_OPERATOR_EQUAL, "1");
//        StringUtil.constructWherePattern(wherePattern, paramPattern, "From[WBSManagedBy].To.states", GlobalConstants.OQL_OPERATOR_EQUAL, "Active");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "From[WBSManagedBy].To.states", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, ApplicationSchemaConstants.STATE_ACTIVITY_TEMPLATE_MASTER_INACTIVE);
        List<WBSActivityTemplateVO> rtn = getRelatedObjectSet(revisions, ApplicationSchemaConstants.RELCLASS_RECOMMENDEDDOCUMENTTEMPLATE, ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE,
                GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(), wherePattern.toString(), paramPattern.toString(), false, false);
        
        List<String> divisionCodeList = new ArrayList<String>();
        for (WBSActivityTemplateVO wbsActivityTemplateVO : rtn) {
            String divisionCode = wbsActivityTemplateVO.getOutDataAttributeValue("divisionCode");
            if(divisionCodeList.indexOf(divisionCode) > -1){
                divisionCodeList.add(divisionCode);
            }
        }
        String divisionCodes = "";
        for (String divisionCode : divisionCodeList) {
            if(divisionCodes.length() > 0){
                divisionCodes += ",";
            }
            divisionCodes += divisionCode;
        }
        List<DivisionUnitVO> divisionList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, divisionCodes);
        for (WBSActivityTemplateVO wbsActivityTemplateVO : rtn) {
            String divisionCode = wbsActivityTemplateVO.getOutDataAttributeValue("divisionCode");
            for (DivisionUnitVO divisionUnitVO : divisionList) {
                if(divisionCode.equals(divisionUnitVO.getNames())){
                    wbsActivityTemplateVO.setOutDataAttributeValue("divisionDisplay", divisionUnitVO.getTitles());   
                }
            }
        }
        return rtn;
    }
    
}

