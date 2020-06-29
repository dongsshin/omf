/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectActivityDocument.java
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

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.dom.CodeDetail;
import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.document.model.ProjectActivityDocumentTemplateVO;
import rap.api.object.document.model.ProjectActivityDocumentVO;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.dom.ChangeProcess;
import rap.api.object.project.dom.WBSActivities;
import rap.api.object.project.dom.WBSItems;
import rap.api.object.project.model.ChangeProcessVO;
import rap.api.object.project.model.ModelDevelopmentGeneralProjectVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSActivitiesVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.relation.dom.ActivityDeliverables;
import rap.api.object.workflow.dom.ApprovalLineMaster;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.workflow.model.ApprovalVO;


public class ProjectActivityDocument extends ProjectDocuments {
    public ProjectActivityDocument(String obid){
        super(obid);
    }
    public ProjectActivityDocument(ProjectActivityDocumentVO vo){
        super(vo);
    }
     @Override
    public ProjectActivityDocumentVO getVo(){
        return (ProjectActivityDocumentVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeProjectActivityDocument();
    }
    public void initializeProjectActivityDocument(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "ProjectActivityDocument[toString()=" + super.toString() + "]";
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
        String templateObid = (String)map.get("templateObid");
        String activityObid = (String)map.get("activityObid");
        
        Map<String, Object> activityDeliverablesAttr = new HashMap<String, Object>();
        
        if(StrUtil.isNotEmpty(templateObid)){
            ProjectActivityDocumentTemplateVO documentTemplateVO = new ProjectActivityDocumentTemplate(templateObid).getVo();
            addFromObject(ApplicationSchemaConstants.RELCLASS_SOURCEDOCUMENTTEMPLATE, documentTemplateVO, null);
            activityDeliverablesAttr.put("documentTemplateName", documentTemplateVO.getNames());
        }
        addFromObject(ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES, DomUtil.toDom(activityObid).getVo(), activityDeliverablesAttr);
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
        if("1".equals(this.getVo().getPreviousObid())) {
            ChangeProcessVO retrieveChangeProcess = retrieveChangeProcess(ApplicationSchemaConstants.STATE_DOCUMENT_CHANGE_WORKING);
            if(retrieveChangeProcess != null){
                ChangeProcess changeProcess = DomUtil.toDom(retrieveChangeProcess);
                changeProcess.deleteObject();
            }
        }
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
    public List<ProjectActivityDocumentTemplateVO> getRelatedTemplate(){
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_SOURCEDOCUMENTTEMPLATE,
                ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE, GlobalConstants.FLAG_TYPE_FROM);
   }
   public WBSActivitiesVO getRelatedActivity(String activityObid){
       
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       if(StrUtil.isNotEmpty(activityObid)){
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, activityObid);
       }
       List<WBSActivitiesVO> relatedObjects = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES,
               ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES, GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(),
               wherePattern.toString(), paramPattern.toString(), false, true, 0, 1);
       if(relatedObjects.size() == 1){
           return relatedObjects.get(0);
       }
       if(relatedObjects.size() > 1){
           for (WBSActivitiesVO wbsActivitiesVO : relatedObjects) {
               if( !ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SAVEDRAFT.equals(wbsActivitiesVO.getStates()) 
                       && !ApplicationSchemaConstants.STATE_WBS_ACTIVITY_APPROVALPROCESSING.equals(wbsActivitiesVO.getStates())){
                   return wbsActivitiesVO;
               }
           }
       }
       return null;
   }
   public List<WBSItemsVO> getRelatedActivityList(String excludeActivityObid){
       return getRelatedActivityList(excludeActivityObid, false);
   }
   public List<WBSItemsVO> getRelatedActivityList(String excludeActivityObid, boolean getCreatedActivity){
       return getRelatedActivityList(excludeActivityObid, null, false);
   }
   public List<WBSItemsVO> getRelatedActivityList(String excludeActivityObid, String excludeActivityNames, boolean getCreatedActivity){
       
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringUtil.addSortByPattern(selectPattern, "@REL.[created] asc");
       if(StrUtil.isNotEmpty(excludeActivityObid)){
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_NOT_IN, excludeActivityObid);
       }
       if(StrUtil.isNotEmpty(excludeActivityNames)){
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_NOT_IN, excludeActivityNames);
       }
       if(getCreatedActivity){
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[newYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
       }
       return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES,
               ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(),
               wherePattern.toString(), paramPattern.toString(), false, true, 0, 1);
   }
   public boolean hasActivityByActivityName( String excludeActivityNames ){
       return getRelatedActivityList(null, excludeActivityNames, false).size() > 0;
   }
   public boolean hasActivity( String excludeActivityObid ){
       return getRelatedActivityList(excludeActivityObid).size() > 0;
   }

    public void deleteActivityDeliverables(String activityObid){
        if (StrUtil.isNotEmpty(activityObid)) {
            StringBuffer paramPattern = new StringBuffer();
            StringBuffer wherePattern = new StringBuffer();
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, activityObid);
            List<WBSItemsVO> relatedObjects = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES,
                    ApplicationSchemaConstants.BIZCLASS_WBSITEMS, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(),
                    paramPattern.toString(), 1);
            for (WBSItemsVO relatedVO : relatedObjects) {
//                System.err.println((String)relatedVO.getOutData().get("rel_obid"));
                 new ActivityDeliverables((String)relatedVO.getOutData().get("rel_obid")).deleteObject();
            }
        }
    }
    public ChangeProcessVO retrieveChangeProcess(){
        return retrieveChangeProcess(null);
    }
    public ChangeProcessVO retrieveChangeProcess(String changeProcessStates){

        ChangeProcessVO changeProcessVO = null;        
        
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.addSortByPattern(selectPattern, "@this.[created] desc");
        
        if(StrUtil.isNotEmpty(changeProcessStates)){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, changeProcessStates);
        }
        
        List<ChangeProcessVO> changeProcessList = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CHANGEDITEMS, ApplicationSchemaConstants.BIZCLASS_CHANGEPROCESS,
                GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(), wherePattern.toString(),
                paramPattern.toString(), false, true, 0, 1);
        
        //changeProcess �� ������ �����ϸ� ���� �ֱٿ� ��ϵ� ���� �����´�.
        if(changeProcessList.size() > 0){
            changeProcessVO =  changeProcessList.get(0);
        }
        return changeProcessVO;
    }
    public String getChangeProcessTitles(){
//        ProjectsVO relatedProjectsVO = getRelatedProjects();
//        Projects relatedProjects = DomUtil.toDom(relatedProjectsVO);
//        
//        String rtn = relatedProjects.getTitles();
//        List<ModelMasterVO> relatedModelList = relatedProjects.getRelatedModelList(true);
//        if(relatedModelList.size() > 0){
//            rtn += " / "+relatedModelList.get(0).getNames();
//        }
//        rtn += "_" + getVo().getTitles();
//        return rtn;
    	return "";
    }
//    
//    public boolean isReviewTargetDocument(){
//        List<ProjectActivityDocumentTemplateVO> relatedTemplate = getRelatedTemplate();
//        if(relatedTemplate.size() > 0){
//            List<WBSItemsVO> relatedActivityList = getRelatedActivityList(null, true);
//            if(relatedActivityList.size() > 0){
//                WBSItemsVO wbsItemsVO = relatedActivityList.get(0);
//                WBSItems wbsItems = DomUtil.toDom(wbsItemsVO);
//                ProjectsVO projectsVO = wbsItems.getProject();
//                Projects projects = DomUtil.toDom( projectsVO );
//                return projects.isReviewTargetDocument(relatedTemplate.get(0).getObid(), (String)wbsItemsVO.getOutData().get("rel_eventCode"));
//            }
//        }
//        return false;
//    }
    public WBSItemTemplatesVO getRelatedWBSItemTemplatesVO(String activityObid){
//        WBSActivitiesVO relatedActivityVO = getRelatedActivity(activityObid);
        WBSActivities relatedActivity = DomUtil.toDom(activityObid);
        return relatedActivity.getRelatedTemplate();
    }
    
    public static String getDocumentType(boolean hasTemplate, String projectGradeCode, String isMandantory, String skipGradeList){
        String documentType = "ADD";
        if(hasTemplate){
            if(StrUtil.isEmpty(isMandantory)){
                documentType = "REF";
            }else{
                if("Y".equals(isMandantory)){
                    documentType = "REQ";
                }else if ("N".equals(isMandantory)){
                    documentType = "DOC";
                }
                if(StrUtil.isNotEmpty(skipGradeList)){
                    String[] split = skipGradeList.split(",");
                    for(String grade : split){
                        if(grade.equals(projectGradeCode)){
                            documentType = "REF";
                            break;
                        }
                    }
                }
            }
        }

//        String documentType = "ADD";
//        if(hasTemplate){
//            if("Y".equals(isMandantory)){
//                documentType = "REQ";
//                if(StrUtil.isNotEmpty(skipGradeList)){
//                    String[] split = skipGradeList.split(",");
//                    for(String grade : split){
//                        if(grade.equals(projectGradeCode)){
//                            documentType = "REF";
//                            break;
//                        }
//                    }
//                }
//            }else if ("N".equals(isMandantory)){
//                documentType = "DOC";
//            }else{
//                documentType = "REF";
//            }
//        }
    
        return documentType;
    }
//    /**
//     *
//     * @return
//     */
//    public String getMobileApprovalDetail(ChangeProcessVO changeProcessVO){
//
//        ProjectActivityDocumentVO vo = getVo();
//        String category = "DOCUMENT";
//        String overview = ChangeProcess.getDescriptionsForMobile(changeProcessVO.getDescriptions());
//        String requestedDate = DateUtil.converDateFormat( changeProcessVO.getModified(),ApplicationBizConstants.DEFAULT_DATE_FORMAT); //TODO
//        String requestedBy = Users.getUsers(changeProcessVO.getCreator()).getVo().getTitles();
//        if ( !NullUtil.isNone(changeProcessVO.getRequesterEmpNo()) ) {
//            requestedBy = Users.getUsers(changeProcessVO.getRequesterEmpNo()).getVo().getTitles();
//        }
//        
//        String activityDoc = vo.getTitles();
//        WBSActivitiesVO relatedActivity = getRelatedActivity(null);
//        if(relatedActivity != null){
//            activityDoc = relatedActivity.getTitles() + "/" + activityDoc;
//        }
//        
//        WBSItems wbsItems = DomUtil.toDom(relatedActivity);
//        ProjectsVO projectsVO = wbsItems.getProject();
//        Projects projects = DomUtil.toDom(projectsVO);
//        List<ModelMasterVO> relatedModelList = projects.getRelatedModelList(false);
//        String modelSuffix = "";
//        for (ModelMasterVO modelMasterVO : relatedModelList) {
//            modelSuffix += modelMasterVO.getNames() + ( "Y".equals(modelMasterVO.getOutData().get("rel_representativeModelFlag")) ? " [Rep Model]" : "") + "<br>";
//        }
//        
//        ChangeProcess changeProcess = DomUtil.toDom(changeProcessVO);
//        List<UsersVO> distributionList = changeProcess.retrieveDistributionUsers();
//        
//        StringBuffer detailContents = new StringBuffer();
//        // �⺻���� ����
//        detailContents.append( "<div class='subject'>[ Approval Info. ]</div><hr><table><tbody class='tableText'>" );
//        detailContents.append( "<tr>" );
//        detailContents.append( "<th width='40%'>Category</th><td>").append( category ).append( "</td>" );
//        detailContents.append( "</tr>" );
//        //detailContents.append( "<tr>" );
//        //detailContents.append( "<th width='15%'>Overview</th><td width='60%'>").append( overview ).append( "</td>" );
//        //detailContents.append( "</tr>" );
//        detailContents.append( "<tr>" );
//        detailContents.append( "<th width='40%'>Requested Date</th><td>").append( requestedDate ).append( "</td>" );
//        detailContents.append( "</tr>" );
//        detailContents.append( "<tr>" );
//        detailContents.append( "<th width='40%'>Requested By</th><td>").append( requestedBy ).append( "</td>" );
//        detailContents.append( "</tr>" );
//        detailContents.append( "<tr>" );
//        detailContents.append( "<th width='40%'>Activity/Doc</th><td>").append( activityDoc ).append( "</td>" );
//        detailContents.append( "</tr>" );
//        detailContents.append( "<tr>" );
//        detailContents.append( "<th width='40%'>Model.Suffix</th><td>").append( modelSuffix ).append( "</td>" );
//        detailContents.append( "</tr>" );
//        detailContents.append( "</tbody></table>" );
//
//        detailContents.append( "<br>" );
//        detailContents.append( "<div class='subject'>[ OverView ]</div><hr><table><tbody class='tableText'>" );
//        detailContents.append( "<tr>" );
//        detailContents.append( "<td>").append( overview ).append( "</td>" );
//        detailContents.append( "</tr>" );
//        detailContents.append( "</tbody></table>" );
//        
//        detailContents.append( "<br>" );
//        detailContents.append( "<div class='subject'>[ Distribution ]</div><hr><table><tbody class='tableText'>" );
//        detailContents.append( "<tr>" );
//        detailContents.append( "<td>").append( "Distributee" ).append( "</td>" );
//        detailContents.append( "<td>").append( "E-Mail" ).append( "</td>" );
//        detailContents.append( "</tr>" );
//        for(UsersVO usersVO : distributionList){
//            detailContents.append( "<tr>" );
//            detailContents.append( "<td>").append( StrUtil.defaultString(usersVO.getDescriptions()) )
//                .append(" ").append(StrUtil.defaultString(usersVO.getTitleName()))
//                .append(" ").append(StrUtil.defaultString(usersVO.getHrDepartmentKor()))
//                .append( "</td>" );
//            detailContents.append( "<td>").append( StrUtil.defaultString(usersVO.getEmailAddress()) ).append( "</td>" );
//            detailContents.append( "</tr>" );
//        }
//        detailContents.append( "</tbody></table>" );
//        
//        // [START] C20181010_13233 ��ǰ����/��� ���� �� ��� Final DR Signal ���� ���� �߰�
//        String projectType = projectsVO.getClassName();
//        if ( ApplicationSchemaConstants.BIZCLASS_MODELDEVELOPMENTGENERALPROJECT.equals(projectType) || ApplicationSchemaConstants.BIZCLASS_MODULEDEVELOPMENTPROJECTS.equals(projectType) ) {
//            detailContents.append( "<br>" );
//            detailContents.append( "<div class='subject'>" + MessageUtil.getMessage("plm.project.info.activitydocument.signal") + "</div>" );
//        }
//        // [END] C20181010_13233
//        
//        return detailContents.toString();
//    }

    /**
     *
     * @param names
     * @return
     */
    public boolean hasProject(String names){
        return getRelatedProjects(names).size() > 0;
    }
    public List<ProjectsVO> getRelatedProjects(String names){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        if(StrUtil.isNotEmpty(names)){
            StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, names);
        }
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_PROJECTDELIVERABLES, ApplicationSchemaConstants.BIZCLASS_PROJECTS,
                GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(), wherePattern.toString(),
                parameterPattern.toString(), false, true, 0, 1);
    }
    
    public List<ApprovalVO> getDefaultApprovalLine(){
        List<ApprovalVO> defaultApprovalLine = new ArrayList<ApprovalVO>();
        ProjectsVO relatedProjects = getRelatedProjects();
        CodeDetailVO codeDetailVO =  CodeDetail.getCodeDetail("PROJECT_REQUEST_TYPE",   "DOC");
        String requestType          = "P";
        String organizationType     = this.getVo().getCreationOrganization();
        String requestSubType       = codeDetailVO.getObid();
        String projectType          = relatedProjects.getClassName();
        String productGroupCode     = relatedProjects.getProductCode();
        String processType          = relatedProjects.getProcessTypeCode();
        String gradeCode            = relatedProjects.getProjectGradeCode(); 
        String devSiteCode          = "";
        List<ProjectActivityDocumentTemplateVO> relatedTemplate = getRelatedTemplate();
        if(relatedTemplate.size() > 0){
            String activityDocument     = relatedTemplate.get(0).getNames();
            String projectObid          = relatedProjects.getObid();
            
            if(relatedProjects.getClassName().equals("ModelDevelopmentGeneralProject")){
                ModelDevelopmentGeneralProjectVO  modelDevelopmentGeneralProjectVO = (ModelDevelopmentGeneralProjectVO)relatedProjects;
                devSiteCode         = modelDevelopmentGeneralProjectVO.getDevSite1Code();         
            }
//        organizationType = "PGZ";
//        productGroupCode = null;
//        String activityDocument = "REC2017091800000000000040";
            
            defaultApprovalLine = ApprovalLineMaster.getProjectApprovalLine(requestType, organizationType, requestSubType, projectType, productGroupCode, processType, gradeCode, devSiteCode, activityDocument, projectObid);
        }
        
        // Activity�� Owner�� �����ڷ� ����
        
        WBSActivitiesVO relatedActivity = getRelatedActivity(null);
        if(relatedActivity != null){
            WBSItems wbsItems = DomUtil.toDom(relatedActivity);
            List<UsersVO> activityOwnerUserList = wbsItems.getActivityOwnerUserList();
            for (UsersVO usersVO : activityOwnerUserList) {
                ApprovalVO approvalVO = new ApprovalVO();
                approvalVO.setAssigneeObid(usersVO.getObid());
                approvalVO.setAssigneeNames(usersVO.getNames());
                approvalVO.setAssignee(usersVO.getTitles());
                approvalVO.setAssigneeEmailAddress(usersVO.getEmailAddress());
                approvalVO.setUserStatus(usersVO.getStates());
                approvalVO.setDepartment(usersVO.getHrDepartmentKor());
                approvalVO.setStep(1);
                approvalVO.setSequence(1); // ??
                approvalVO.setRouteStateSequence(1);
                approvalVO.setRouteAction(ApplicationBizConstants.ROUTE_ACTIONS_COMMENT);
//                approvalVO.setRouteInstructions("����");
//                approvalVO.setProcessRole(processRole);
                approvalVO.setState(ApplicationSchemaConstants.STATE_DOCUMENT_CHANGE_APPROVED);
                approvalVO.setParallelNodeProcessionRule(ApplicationBizConstants.ROUTE_ACTION_ALL);
                approvalVO.setRecordMode("C");
                defaultApprovalLine.add(approvalVO);
            }
        }
        
        return defaultApprovalLine;
    }
    /**
     *
     * @param obid
     * @return
     */
    public String getDefaultChangeProcessRequestTitle(){
        String requestTitle = "";
        ProjectsVO relatedProjects = getRelatedProjects();
        WBSActivitiesVO relatedActivity = getRelatedActivity(null);
        String activityName = relatedActivity.getActivityNameEng();
        String documentName = getVo().getDocumentNameEng();
        String userLocale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, ApplicationBizConstants.LANG_KO); 
        if(ApplicationBizConstants.LANG_CH.equals(userLocale)){
            activityName = relatedActivity.getActivityNameChi();
            documentName = getVo().getDocumentNameChi();
        }else if (ApplicationBizConstants.LANG_KO.equals(userLocale)){
            activityName = relatedActivity.getActivityNameKor();
            documentName = getVo().getDocumentNameKor();
        }
        requestTitle += "["+relatedProjects.getTitles()+"]["+activityName+"]"+documentName;
        return requestTitle;
    }
    /**
     *
     * @param activityObid
     * @return
     */
    public boolean canDeleteProjectActivityDocument(String activityObid){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        if(ApplicationBizConstants.ADMIN_USER.equals(userId)) return true;
        // ������, PL, Activity Owner
        if(ApplicationSchemaConstants.STATE_DOCUMENT_CHANGE_WORKING.equals(getVo().getStates())){
            if(getVo().getCreator().equals(userId)){
                return true;
            }
            if(StrUtil.isNotEmpty(activityObid)){
                WBSItems wbsItems = DomUtil.toDom(activityObid);
                ProjectsVO projectsVO = wbsItems.getProject();
                if(projectsVO != null && userId.equals(projectsVO.getProjectLeader())){
                    return true;
                }
                List<UsersVO> activityOwnerUserList = wbsItems.getActivityOwnerUserList();
                for (UsersVO usersVO : activityOwnerUserList) {
                    if(userId.equals(usersVO.getNames())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     *
     * @param activityObid
     * @return
     */
    public boolean canModifyProjectActivityDocument(String activityObid){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        if(ApplicationBizConstants.ADMIN_USER.equals(userId)) return true;
        // ������, PL, Activity Owner
        if(!ApplicationSchemaConstants.STATE_PROJECT_ACTIVITY_DOCUMENT_APPROVING.equals(getVo().getStates()) 
                && !ApplicationSchemaConstants.STATE_PROJECT_ACTIVITY_DOCUMENT_APPROVED.equals(getVo().getStates())){
            if(getVo().getCreator().equals(userId)){
                return true;
            }
            if(StrUtil.isNotEmpty(activityObid)){
                WBSItems wbsItems = DomUtil.toDom(activityObid);
                ProjectsVO projectsVO = wbsItems.getProject();
                if(projectsVO != null && userId.equals(projectsVO.getProjectLeader())){
                    return true;
                }
                List<UsersVO> activityOwnerUserList = wbsItems.getActivityOwnerUserList();
                for (UsersVO usersVO : activityOwnerUserList) {
                    if(userId.equals(usersVO.getNames())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
//    public List<ProjectActivityDocumentTemplateVO> getRelatedTemplate(){
//        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_SOURCEDOCUMENTTEMPLATE,
//                ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENTTEMPLATE, GlobalConstants.FLAG_TYPE_FROM);
//   }
}

