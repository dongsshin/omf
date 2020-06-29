/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : Projects.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.intf.IfPlmDataByTriggerVO;
import com.rap.omc.intf.TriggerUtil;
import com.rap.omc.schema.object.temp.model.ActivityValidationResultVO;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.TimeServiceUtil;

import rap.api.object.common.dom.ProjectWorkplace;
import rap.api.object.organization.dom.DivisionUnit;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.model.ModelDevelopmentGeneralProjectVO;
import rap.api.object.project.model.ModuleDevelopmentProjectsVO;
import rap.api.object.project.model.ProjectDefinedRoleVO;
import rap.api.object.project.model.ProjectLifeCycleVO;
import rap.api.object.project.model.ProjectPersonVO;
import rap.api.object.project.model.ProjectRoleVO;
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSPhasesVO;
import rap.api.object.project.model.WBSTemplateMasterVO;
import rap.api.object.relation.model.HasReferenceProjectVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;
import rap.application.workflow.model.ConvertedPhaseVO;


public class Projects extends ProjectWorkplace {
    public Projects(String obid){
        super(obid);
    }
    public Projects(ProjectsVO vo){
        super(vo);
    }
     @Override
    public ProjectsVO getVo(){
        return (ProjectsVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeProjects();
    }
    public void initializeProjects(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "Projects[toString()=" + super.toString() + "]";
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
    public Set<String> getSkippedPhaseSet(){
        Set<String> skippedSet = new HashSet<String>();
        String skippedValue = this.getSkipEventCode();
        if(StrUtil.isEmpty(skippedValue)) return skippedSet;
        WBSTemplateMasterVO wbsTemplateMasterVO = this.getLatestAppliedProjectTemplate();
        if(NullUtil.isNull(wbsTemplateMasterVO)) return skippedSet;
        if(StrUtil.isEmpty(wbsTemplateMasterVO.getLifeCycle())) return skippedSet;
        ProjectLifeCycleVO projectLifeCycleVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, wbsTemplateMasterVO.getProjectLifeCycle());
        if(NullUtil.isNull(projectLifeCycleVO)) return skippedSet;
        ProjectLifeCycle projectLifeCycleDom = new ProjectLifeCycle(projectLifeCycleVO);
        List<ConvertedPhaseVO> convertedPhaseList = projectLifeCycleDom.getConvertedPhaseList(wbsTemplateMasterVO.getDivisionUnit(), false);
        for(ConvertedPhaseVO vo : convertedPhaseList){
            skippedSet.add(vo.getNames());
            if(skippedValue.equals(vo.getNames())) break;
        }
        return skippedSet;
    }
    public String  getSkipEventCode(){
    	String skipEventCode = "";
    	List<HasReferenceProjectVO> referenceProjectList = this.getRelationship(ApplicationSchemaConstants.RELCLASS_HASREFERENCEPROJECT,GlobalConstants.FLAG_TYPE_TO);
    	if(referenceProjectList.size() > 0){
    		skipEventCode = referenceProjectList.get(0).getSkipEventCode();
    	}
    	return skipEventCode;
    }
    public WBSTemplateMasterVO getLatestAppliedProjectTemplate(){
        if(NullUtil.isNone(this.getVo().getWbsTempleteCode())){return null;}
    
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_NOT_IN, ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_INACTIVE);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[previousObid]", GlobalConstants.OQL_OPERATOR_EQUAL, "1");
        List<WBSTemplateMasterVO> list = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER, this.getVo().getWbsTempleteCode(), GlobalConstants.FLAG_TYPE_ALL, selectPattern.toString(), wherePattern.toString(), paramPattern.toString(), false, 0);
        if(NullUtil.isNone(list)) return null;
        if(list.size() > 1) throw new ApplicationException("WBS Template info is Incorrect. Please contact System Administrator!!!");
        return list.get(0);
    }
    public ProjectScheduleVO getProjectLatestReleaseSchedule(){
        return(this.getProjectScheduleSub(true));
    }
    private ProjectScheduleVO getProjectScheduleSub(boolean isReleased){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[previousObid]", GlobalConstants.OQL_OPERATOR_EQUAL, "1");
        List<ProjectScheduleVO> projectScheduleList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_REVISEDSCHEDULE,
                ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE, GlobalConstants.FLAG_TYPE_TO,wherePattern.toString(),parameterPattern.toString(),1);
        if(NullUtil.isNone(projectScheduleList)) return null;
        if(projectScheduleList.size() > 1) throw new FoundationException("[Critical Error]Project Schedule info invalid.");
        if(!isReleased) return projectScheduleList.get(0);
        if(this.getStates().equals(ApplicationSchemaConstants.STATE_PROJECT_DROPPED)) return projectScheduleList.get(0);
        ProjectSchedule scheduleDom = DomUtil.toDom(projectScheduleList.get(0));
        if(scheduleDom.isReleased()) return scheduleDom.getVo();
        if(scheduleDom.isFirst()) return null;
        scheduleDom = new ProjectSchedule(scheduleDom.getNextObid());
        return scheduleDom.getVo();
    }
    /**
     * ������Ʈ�� ����Ǿ��� Role����Ʈ�� Return��.
     *
     * @return
     */
    public List<ProjectDefinedRoleVO> getProjectRoles(){
        return(this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOPROJECT, ApplicationSchemaConstants.BIZCLASS_PROJECTDEFINEDROLE, GlobalConstants.FLAG_TYPE_TO));
    }
    /**
     * ������Ʈ�� ����Ǿ��� Role����Ʈ�� ProjectRoleVO �� Return��.
     *
     * @return
     */
    public List<ProjectRoleVO> getAssignedProjectRoleList(){
        return ProjectRole.getProjectRoleVOListAsDefinedRole(getProjectRoles());
    }
    /**
     * Project�� Member ��ü List�� UsersVO�������� Return��. OutData�� User�� ������ �ִ� Role������ �߰� �Ǿ��� ����.(Role�� Titles)
     *
     * @return
     */
    public List<UsersVO> getMemberUserList(){
        List<UsersVO> userList = new ArrayList<UsersVO>();
        List<ProjectPersonVO> list = this.getMemberProjectPersonList();
        if(list.size()>0){
            userList = ObjectRoot.getRelatedObjectSet(list, ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOPROJECTMEMBER, ApplicationSchemaConstants.BIZCLASS_USERS, GlobalConstants.FLAG_TYPE_TO);
            for(UsersVO vo : userList){
                String roleList = "";
                String roleCodeList = "";
                String isMainMember = "";
                for(ProjectPersonVO subVO : list){
                    if(vo.getNames().equals(subVO.getUserId())){
                        roleList = (String)subVO.getOutData().get(ProjectConstants.PROJECT_OUTDATA_ROLE_LIST);
                        roleCodeList = (String)subVO.getOutData().get(ProjectConstants.PROJECT_OUTDATA_ROLE_CODE_LIST);
                        isMainMember = (String)subVO.getOutData().get(ProjectConstants.PROJECT_OUTDATA_ROLE_IS_MAIN);
                    }
                }
                vo.getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_LIST, roleList);
                vo.getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_CODE_LIST, roleCodeList);
                vo.getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_IS_MAIN, isMainMember);
            }
        }
        return userList;
    }
    public List<ProjectPersonVO> getMemberProjectPersonList(){
        List<ProjectPersonVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOPROJECT, ApplicationSchemaConstants.BIZCLASS_PROJECTPERSON, GlobalConstants.FLAG_TYPE_TO);
        for(ProjectPersonVO vo : list){
            ProjectPerson dom = DomUtil.toDom(vo);
            
            /* 20181217 �Ʒ� �ΰ� �޼ҵ带 �ϳ��� ��ģ�� 
            String strRoleList = dom.getRoleListForDisplay();
            String strRoleCodeList = dom.getRoleCodeListForDisplay();
            
            vo.getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_LIST, strRoleList);
            vo.getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_CODE_LIST, strRoleCodeList);
            System.out.println("..)/ pis_main_member getRoleListForDisplay : strRoleList " + vo.getOutData().get(ProjectConstants.PROJECT_OUTDATA_ROLE_LIST));
            System.out.println("..)/ pis_main_member getRoleListForDisplay : strRoleCodeList " + vo.getOutData().get(ProjectConstants.PROJECT_OUTDATA_ROLE_CODE_LIST));
            */
            
            /* */
            dom.getRoleDefinedInfoListForDisplay();
            System.out.println("..)/ pis_main_member getRoleListForDisplay : isMainMember " + vo.getOutData().get("isMainMember"));
            System.out.println("..)/ pis_main_member getRoleListForDisplay : strRoleList " + vo.getOutData().get(ProjectConstants.PROJECT_OUTDATA_ROLE_LIST));
            System.out.println("..)/ pis_main_member getRoleListForDisplay : strRoleCodeList " + vo.getOutData().get(ProjectConstants.PROJECT_OUTDATA_ROLE_CODE_LIST));
                        
            // System.out.println("..)/ pis_main_member getRoleListForDisplay : isMainMember " + vo.getOutData().get("rel_isMainMember") + " / iaMainMember " + vo.getOutData().get("rel_isMainMember"));

        }
        return list;
    }
    /**
     * getRoleListForDisplay,getRoleCodeListForDisplay ��ħ
     */
    public void getRoleDefinedInfoListForDisplay(){
        List<ProjectDefinedRoleVO> list = this.getRoleListAll();
        SortUtil.sort(list, "titles",false);
        // �������� ó��??? role ����.. vo�� ����...
        StringBuffer titleBuf = new StringBuffer("");
        StringBuffer codeBuf = new StringBuffer("");
        StringBuffer isMainBuf = new StringBuffer("");
        for(ProjectDefinedRoleVO vo : list){
            titleBuf.append(vo.getTitles()).append(",");
            codeBuf.append(vo.getRoleCode()).append(",");
            
            isMainBuf.append(vo.getOutData().get("rel_isMainMember")).append(",");
        }
        
        this.getVo().getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_LIST, (StrUtil.isEmpty(titleBuf))?"":titleBuf.substring(0,titleBuf.lastIndexOf(",")) ) ;
        this.getVo().getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_CODE_LIST, (StrUtil.isEmpty(codeBuf))?"":codeBuf.substring(0,codeBuf.lastIndexOf(",")) ) ;
        this.getVo().getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_IS_MAIN, (StrUtil.isEmpty(isMainBuf))?"":isMainBuf.substring(0,isMainBuf.lastIndexOf(",")) ) ;
        
    }
    public List<ProjectDefinedRoleVO> getRoleListAll(){
        return this.getRoleList(null);
    }
    /**
     * �ش� ����ڰ� ������ �ִ� Role List(�ش� Role��)�� Return��.
     *
     * @param roleCode ã�����ϴ� Role Code
     * @return
     */
    public List<ProjectDefinedRoleVO> getRoleList(String roleCode){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        if(!StrUtil.isEmpty(roleCode))  StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[roleCode]", GlobalConstants.OQL_OPERATOR_EQUAL, roleCode);
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDMYROLE, ApplicationSchemaConstants.BIZCLASS_PROJECTDEFINEDROLE, GlobalConstants.FLAG_TYPE_TO, wherePattern.toString(), parameterPattern.toString(), 1);
    }
    public boolean isInitialProject(){
        // HasParentProject Relationship �� �ְ� parentProjectType���� Parent�� False
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[parentProjectType]", GlobalConstants.OQL_OPERATOR_EQUAL, "Parent");
        List<ProjectsVO> relatedObjects = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASPARENTPROJECT, ApplicationSchemaConstants.BIZCLASS_PROJECTS,
                GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(),
                paramPattern.toString(), false, true, 0, 1);
        if(relatedObjects.size() > 0){
            return false;
        }
        return true;
    }
    public boolean isExistsProjectSchedule(){
        return getProjectLatestSchedule() == null ?  false :  true;
    }
    public ProjectScheduleVO getProjectLatestSchedule(){
        return(this.getProjectScheduleSub(false));
    }
    public WBSTemplateMasterVO getLatestReleasedAppliedProjectTemplate(){
        WBSTemplateMasterVO masterVO = getLatestAppliedProjectTemplate();
        if(NullUtil.isNull(masterVO)) return null;
        if(masterVO.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE)) return masterVO;
        WBSTemplateMaster masterDom = new WBSTemplateMaster(masterVO);
        if(masterDom.isFirst()) return null;
        WBSTemplateMaster previousDom = new WBSTemplateMaster(masterVO.getNextObid());
        if(previousDom.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_MASTER_ACTIVE)) return previousDom.getVo();
        return null;
    }
    public List<WBSItemsVO> getWBSItemsStructureBasedOnLatest(){
        ProjectScheduleVO projectScheduleVO = getProjectLatestSchedule();
        if(NullUtil.isNull(projectScheduleVO)) return new ArrayList<WBSItemsVO>();
        return new ProjectSchedule(projectScheduleVO).getWBSItemsStructureList(true,false,false);
    }
    public List<WBSPhasesVO> getWBSProjectPhasesBasedOnLatest(){
        ProjectScheduleVO projectScheduleVO = getProjectLatestSchedule();
        if(NullUtil.isNull(projectScheduleVO)) return new ArrayList<WBSPhasesVO>();
        return new ProjectSchedule(projectScheduleVO).getWBSPhaseList();
    }
    public RevisedProjects getRevisedProjects(){

        String revisedObid = "";

        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();


        fromPattern.append("<this>ThisConnectedWithFrom<[HasProjectRevision]@P2R>+");
        fromPattern.append("<[HasProjectRevision]@P2R>ToConnectedWithThis<[RevisedProjects]@RP>+");

        StringUtil.constructSelectPattern(selectPattern, "@RP.[obid] revisedObid");

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@RP.[previousObid]", GlobalConstants.OQL_OPERATOR_EQUAL, "1");

        List<ProjectsVO> resultList = ObjectRoot.searchObjects( ApplicationSchemaConstants.BIZCLASS_PROJECTS,
                                                                GlobalConstants.FLAG_TYPE_ALL,
                                                                GlobalConstants.FLAG_TYPE_ALL,
                                                                selectPattern.toString(),
                                                                fromPattern.toString(),
                                                                wherePattern.toString(),
                                                                paramPattern.toString(),
                                                                false,
                                                                0);

        revisedObid = resultList.get(0).getOutData().get("revisedObid").toString();

        return DomUtil.toDom(revisedObid);
    }
    public ProjectPersonVO getProjectLeader(){
        ProjectDefinedRoleVO definedRoleVO  = this.findProjectLeaderRole();
        if(NullUtil.isNull(definedRoleVO)) throw new FoundationException("There is not Allocated Project Leader.");
        ProjectDefinedRole definedRole = DomUtil.toDom(definedRoleVO);
        List<ProjectPersonVO> memberList = definedRole.getMemberList();
        if(memberList.size() > 1){throw new FoundationException("There is "+memberList.size()+" Project Leaders.");}
        return memberList.get(0);
    }
    private ProjectDefinedRoleVO findProjectLeaderRole(){
        return findProjectRole(ProjectConstants.ROLE_PROJECT_LEADER);
    }
    private ProjectDefinedRoleVO findProjectRole(String roleCode){
        String projectRoleName = this.getVo().getNames() + "-" + roleCode;
        ProjectDefinedRoleVO roleVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTDEFINEDROLE, projectRoleName);
        return roleVO;
    }
    public ProjectPersonVO findProjectPerson(String UserId){
        String projectPersonName = this.getVo().getNames() + "-" + UserId;
        ProjectPersonVO personVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTPERSON, projectPersonName);
        return personVO;
    }
    public DivisionUnitVO getDivisionUnit(){
        String divisionUnit = this.getVo().getDivisionCode();
        return BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, divisionUnit);
    }
    public List<ActivityValidationResultVO> validateIsUnregistered(){
        ArrayList<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        String newPartStatus = null;
        if(isKindOf(ApplicationSchemaConstants.BIZCLASS_MODELDEVELOPMENTGENERALPROJECT)){
            newPartStatus = ((ModelDevelopmentGeneralProjectVO)getVo()).getNewPartStatus();
        }else if (isKindOf(ApplicationSchemaConstants.BIZCLASS_MODULEDEVELOPMENTPROJECTS)){
            newPartStatus = ((ModuleDevelopmentProjectsVO)getVo()).getNewPartStatus();
        }
        if("Unregistered".equals(newPartStatus)){
            result.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.error,
                    "Unregistered",
                    "Unregistered",
                    "Connect to Part DMS (Part Development Management System) to create new parts list and proceed with part development"));
        }
        return result;
    }
    public static ProjectDefinedRoleVO findProjectRole(String role, List<ProjectDefinedRoleVO> projectRoleVOList){
        for(ProjectDefinedRoleVO vo : projectRoleVOList){
            if(role.equals(vo.getRoleCode())) return vo;
        }
        return null;
    }
    public final void completeProject(){
        this.validateForCompleteProject();
        this.preProcessForCompleteProject();
        this.completeProjectCore();
        this.postProcessForCompleteProject();
    }
    protected void validateForCompleteProject(){
        ;
    }
    protected void preProcessForCompleteProject(){
        ;
    }
    protected void completeProjectCore(){
        getVo().setProjectCompletedDate(TimeServiceUtil.getDBLocalTimeStr());
        modifyObject();
        this.changePolicyAndState(this.getLifeCycle(), ApplicationSchemaConstants.STATE_PROJECT_COMPLETED);
    }
    protected void postProcessForCompleteProject(){
        this.completeSchedule();
        DivisionUnitVO divisionVo = DivisionUnit.getVOByName(this.getVo().getDivisionCode());
        String buCode = divisionVo.getBusinessUnitCode();
//        //MC, PCZ, PNZ �Ļ��� ���� ���� ó��
//        if(buCode.equals("MC") || ApplicationBizConstants.VARITYPE_SKU.equals(divisionVo.getVariUseType()) || ApplicationBizConstants.VARITYPE_COMPSKU.equals(divisionVo.getVariUseType()) || ApplicationBizConstants.VARITYPE_VCSKU.equals(divisionVo.getVariUseType())){
//            changeProjectModelStatusRelation("Complete");
//        }
//        updateNewPartStatus("COMPLETE");
        IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO(this.getVo(), ApplicationBizConstants.TR_ID_PROJECT_COM_OUT, ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_COMMON_ALL);
        triggerVO.setAttribute1("0");
        triggerVO.setAttribute2("GSCP");
        triggerVO.setAttribute3("CompleteProject");
        TriggerUtil.createDataByTrigger(triggerVO);
        
        triggerVO = TriggerUtil.createDataByTriggerVO(this.getVo(), ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_GCRC, ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_001);
        TriggerUtil.createDataByTrigger(triggerVO);
    }
    /**
     * Schedule�� Complete��Ų��.
     */
    private void completeSchedule(){
        ProjectScheduleVO scheduleVO = this.getProjectLatestReleaseSchedule();
        if(!NullUtil.isNull(scheduleVO)){
            ProjectSchedule scheduleDom = new ProjectSchedule(scheduleVO);
            Date date = TimeServiceUtil.getDBLocalTime();
            scheduleDom.getVo().setActualEndDate(date);
            scheduleDom.modifyObject();
            scheduleDom.changePolicyAndState(scheduleDom.getLifeCycle(), ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_COMPLETED);
        }
    }
    public boolean isAllPhaseEnd(){
        return isAllPhaseEnd(null);
    }
    public boolean isAllPhaseEnd( ProjectScheduleVO projectScheduleVO ){
        return isAllPhaseEnd(projectScheduleVO, null);
    }
    public boolean isAllPhaseEnd( ProjectScheduleVO projectScheduleVO, String completedPahseName ){
        if(projectScheduleVO == null){
            projectScheduleVO = getProjectLatestSchedule();
        }
        if(projectScheduleVO != null){
            ProjectSchedule projectSchedule = DomUtil.toDom(projectScheduleVO);
            if(projectSchedule != null){
                List<WBSPhasesVO> wbsPhaseList = projectSchedule.getWBSPhaseList();
                for(WBSPhasesVO wbsPhasesVO : wbsPhaseList){
                    if(!NullUtil.isNone(completedPahseName) && completedPahseName.equals(wbsPhasesVO.getNames())){continue;}
                    if(!wbsPhasesVO.getStates().equals(ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED) && wbsPhasesVO.getActualEndDate() == null){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * ��� Event �Ϸ�� Complete state�� ����Ǵ� Project���� check
     * @return
     */
    public boolean isAutoCompleteWhenAllPhaseEnd(){
        if( !isKindOf(ApplicationSchemaConstants.BIZCLASS_DESIGNPROJECT) 
            && !isKindOf(ApplicationSchemaConstants.BIZCLASS_TECHPROJECT) 
            && !isKindOf(ApplicationSchemaConstants.BIZCLASS_FACILITYPROJECT)){
            return true;
        }
        return false;
    }
    /**
     * ������Ʈ�� �ű� Role�� �߰���.
     *
     * @param roleVO �߰��Ǿ��� Project Role�� Return�Ǿ���.
     * @return
     */
    public ProjectDefinedRoleVO addProjectRole(ProjectRoleVO roleVO){
        return(createProjcetRole(roleVO));
    }
    /**
     * Project�� �ű� Member�� �߰���.
     *
     * @param userId User Id
     * @return
     */
    public ProjectPersonVO addProjectMember(String userId){
        UsersVO userVO  = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_USERS,userId);
        if(NullUtil.isNull(userVO)) throw new FoundationException("Cannot find User(" + userId + ")");
        return createProjcetPersonSub(userVO);
    }
    /**
     * Project�� �ش� Role�� �ű� Member�� �߰���.
     *
     * @param roleCode Role Code(ProjectRole�� ���ǵǾ��� Names)
     * @param userId User Id
     */
    public void allocateRoleForUser(String roleCode, String userId){
        this.assignUserToRoleSub(roleCode,userId, null);
    }
    /**
     * Project�� �ش� Role�� �ű� Member�� �߰���.
     *
     * @param roleCode Role Code(ProjectRole�� ���ǵǾ��� Names)
     * @param userId User Id
     */
    public void allocateRoleForUser(String roleCode, String userId, String isMainMember){
        this.assignUserToRoleSub(roleCode,userId,isMainMember, null);
    }
    /**
     * Project�� �ش� Role�� �ű� Member�� �߰���.
     *
     * @param roleCode Role Code(ProjectRole�� ���ǵǾ��� Names)
     * @param userId User Id
     * @param workingType ���/����
     */
    public void allocateRoleForUser(String roleCode, String userId, String isMainMember, String workingType){
        this.assignUserToRoleSub(roleCode,userId,isMainMember, workingType);
    }
    /**
     * Project�� �ش� Role���� Member�� Remove��. deleteProjectPerson�� ���� Ture�ΰ�� ������Ʈ ��ü Member���� �����Ǿ���.
     *
     * @param roleCode Role Code(ProjectRole�� ���ǵǾ��� Names)
     * @param userId User Id
     * @param deleteProjectPerson �ش� Role���� Member�� ������ ������ �ƴ� ������Ʈ ��ü Member���� �������� ����
     */
    public void removeMemberFromRole(String roleCode, String userId, boolean deleteProjectPerson){
        ProjectDefinedRoleVO projectDefinedRoleVO = this.findProjectRole(roleCode);
        if(NullUtil.isNull(projectDefinedRoleVO)) throw new FoundationException("Cannot find Project Person for(" + userId + ")");
        ProjectDefinedRole projectDefinedRoleDom = DomUtil.toDom(projectDefinedRoleVO);
        projectDefinedRoleDom.removeUserForRole(userId,deleteProjectPerson);
    }

    public boolean isExistsProjectRole(String roleCode){
        boolean isExist = false;
        List<ProjectDefinedRoleVO> projectRoles = getProjectRoles();
        for(ProjectDefinedRoleVO projectDefinedRoleVO : projectRoles){
            if(projectDefinedRoleVO.getRoleCode().equals(roleCode)){
                isExist = true;
                break;
            }
        }
        return isExist;
    }
    private void assignUserToRoleSub(String roleCode,String userId, String workingType){
        if(ProjectConstants.ROLE_PROJECT_LEADER.equals(roleCode)){
            assignUserToRoleSub(roleCode, userId, "Y", workingType);
        }else{
            assignUserToRoleSub(roleCode, userId, "N", workingType);
        }
    }
    private void assignUserToRoleSub(String roleCode,String userId, String isMainMember, String workingType){
        ProjectDefinedRoleVO definedRoleVO  = this.findProjectRole(roleCode);
        if(NullUtil.isNull(definedRoleVO)){
            ProjectRoleVO roleCodeVO  = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTROLE,roleCode);
            if(NullUtil.isNull(roleCodeVO)) throw new FoundationException("Cannot find role(" + roleCode + ")");
            definedRoleVO = this.createProjcetRole(roleCodeVO);
        }
        ProjectPersonVO personVO = findProjectPerson(userId);
        if(NullUtil.isNull(personVO)){
            UsersVO userVO  = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_USERS,userId);
            if(NullUtil.isNull(userVO)) throw new FoundationException("Cannot find User(" + userId + ")");
            personVO = this.createProjcetPersonSub(userVO);
        }
        if(!this.isExistsMember(roleCode, userId)){
            ProjectDefinedRole projectRoleDom = DomUtil.toDom(definedRoleVO);
            projectRoleDom.allocateRoleForUser(personVO, isMainMember, workingType);
        }
    }
    private void makeUserAndRoleSet(WBSTemplateMaster wbsTemplateMasterDom, List<WBSItemTemplatesVO> wbsTemplateList, Set<String> userSet,Set<String> roleSet,Map<String,Set<String>> userRoleMap){
        for(WBSItemTemplatesVO wbsTemplateVO : wbsTemplateList){
            Set<String> userTempSet = new HashSet<String>();
            List<ProjectRoleVO> projectRoleVOList = new WBSItemTemplates(wbsTemplateVO).getAllocatedRole(wbsTemplateMasterDom);
            String ownerStrList = wbsTemplateVO.getActivityOwner();
            if(!StrUtil.isEmpty(ownerStrList) && !ownerStrList.equals(ProjectConstants.NONE)){
                userTempSet = StrUtil.convertArrayToSet(ownerStrList.split(","));
            }
            for(ProjectRoleVO vo:projectRoleVOList){roleSet.add(vo.getNames());}
            for(String str:userTempSet){userSet.add(str);}
            for(ProjectRoleVO vo:projectRoleVOList){
                for(String user:userTempSet){
                    Set<String> tempUserSet = userRoleMap.get(vo.getNames());
                    if(tempUserSet == null) tempUserSet = new HashSet<String>();
                    tempUserSet.add(user);
                    userRoleMap.put(vo.getNames(), tempUserSet);
                }
            }
        }
    }
    private List<ProjectPersonVO> createProjcetPerson(Set<String> userSet){
        List<ProjectPersonVO> projectPersonVOList = new ArrayList<ProjectPersonVO>();
        if(userSet == null || userSet.isEmpty()){return projectPersonVOList;}
        List<String> userList = StrUtil.convertSetToList(userSet);
        List<UsersVO> userVOList = BusinessObjectMaster.getBusinessObjectMasters(ApplicationSchemaConstants.BIZCLASS_USERS,userList,true);
        for(UsersVO userVO : userVOList){
            ProjectPersonVO projectPersonVO = createProjcetPersonSub(userVO);
            projectPersonVOList.add(projectPersonVO);
        }
        return projectPersonVOList;
    }
    private ProjectDefinedRoleVO createProjcetRole(ProjectRoleVO roleVO){
        return createProjcetRole(roleVO, ProjectConstants.PROJECT_ROLE_TYPE_FROM_PROJECT);
    }
    private ProjectDefinedRoleVO createProjcetRole(ProjectRoleVO roleVO, String roleType){
        ProjectDefinedRoleVO projectRoleVO = new ProjectDefinedRoleVO();
        projectRoleVO.setDescriptions(roleVO.getDescriptions());
        projectRoleVO.setTitles(roleVO.getTitles());
        projectRoleVO.setRoleCode(roleVO.getNames());
        projectRoleVO.setRoleType(roleType);
        ProjectDefinedRole projectRoleDom = DomUtil.toDom(projectRoleVO);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.CREATE_CREATE_MAP_PROJECT, this.getVo());
        projectRoleDom.createObject(map);
        return projectRoleDom.getVo();
    }
    private ProjectPersonVO createProjcetPersonSub(UsersVO userVO){
        ProjectPersonVO projectPersonVO = new ProjectPersonVO();
        projectPersonVO.setDescriptions(userVO.getDescriptions());
        projectPersonVO.setTitles(userVO.getTitles());
        projectPersonVO.setUserId(userVO.getNames());
        projectPersonVO.setMemberType(ProjectConstants.PROJECT_USER_TYPE_REGULAR);
        ProjectPerson projectPersonDom = DomUtil.toDom(projectPersonVO);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ProjectConstants.CREATE_CREATE_MAP_PROJECT, this.getVo());
        map.put(ProjectConstants.CREATE_CREATE_MAP_USERS, userVO);
        projectPersonDom.createObject(map);
        return projectPersonDom.getVo();
    }
    /**
     * User�� ������Ʈ Member���� Return��.
     *
     * @param userId
     * @return
     */
    public boolean isExistsMember(String userId){
        return(!(findProjectPerson(userId) == null));
    }
    /**
     * User�� ������Ʈ Role�� Member���� Return��.
     *
     * @param roleCode
     * @param userId
     * @return
     */
    public boolean isExistsMember(String roleCode, String userId){
        ProjectPersonVO  personVO = findProjectPerson(userId);
        if(NullUtil.isNull(personVO)) return false;
        ProjectDefinedRoleVO projectRoleVO = findProjectRole(roleCode);
        if(NullUtil.isNull(projectRoleVO)) return false;

        ProjectDefinedRole projectRoleDom = DomUtil.toDom(projectRoleVO);
        List<ProjectPersonVO> list = projectRoleDom.getMemberList(userId);
        if(NullUtil.isNone(list)) return false;
        return true;
    }
}

