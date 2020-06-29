/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : Users.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.config.web.security.UserSessionMappinKey;
import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.api.util.general.RelationShip;
import com.rap.omc.api.util.general.UserManagementUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.UserPropertyConstants;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.foundation.user.model.SysUserVO;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.schema.util.OmcUniqueIDGenerator;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.TimeServiceUtil;

import rap.api.object.bizplan.model.ManagementGroupListVO;
import rap.api.object.common.dom.DesktopWidget;
import rap.api.object.common.model.AddressBookVO;
import rap.api.object.common.model.AuthorizationGroupVO;
import rap.api.object.common.model.AuthorizationMenuVO;
import rap.api.object.common.model.DesktopTileVO;
import rap.api.object.common.model.DesktopWidgetVO;
import rap.api.object.common.model.LegacyLinkVO;
import rap.api.object.organization.model.AbstractUsersVO;
import rap.api.object.organization.model.ChangeAuthVO;
import rap.api.object.organization.model.DepartmentVO;
import rap.api.object.organization.model.DivisionPeriodVO;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.organization.model.PlantUnitVO;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.pdr.model.PDRPropertyVO;
import rap.api.object.project.model.ChangeProcessVO;
import rap.api.object.project.model.ModelDevelopmentGeneralProjectVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.relation.model.ProjectUserActualMMVO;
import rap.api.object.relation.model.Users2DesktopTileVO;
import rap.api.object.workflow.dom.InBoxTask;
import rap.api.object.workflow.dom.WorkflowInboxTask;
import rap.api.object.workflow.model.ApprovalLineVO;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.WorkflowConstants;

public class Users extends AbstractUsers {
    public Users(String obid){
        super(obid);
    }
    public Users(UsersVO vo){
        super(vo);
    }
    @Override
    public UsersVO getVo(){
        return (UsersVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeUsers();
    }
    public void initializeUsers(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "Users[toString()=" + super.toString() + "]";
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
        /*����������� �ٲ�� Session�� Refresh�ϱ� ���� System User�� Time Stamp�� �ٲپ��ش�.*/
        String timeStamp = OmcUniqueIDGenerator.getObid();
        UserManagementUtil.setTimeStampSystemUser(this.getVo().getNames(), timeStamp);
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
    public List<PlantUnitVO> retrievePlantUnitList(){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_MEMBERS,ApplicationSchemaConstants.BIZCLASS_PLANTUNIT, GlobalConstants.FLAG_TYPE_ALL,
                "SortBy@this.[titles]",wherePattern.toString(),paramPattern.toString(),
                false,false,0,1);
    }
    public List<DivisionUnitVO> retrieveDivisionUnitList(String yyyy) {
        return retrieveDivisionUnitList(yyyy,"", "");
    }
    
    public List<DivisionUnitVO> retrieveDivisionUnitListDataRange(String yyyy, String dataRange, String businessUnit, UserSession userSession) {
        if ( "C".equals(dataRange) ) {
            businessUnit = userSession.getBusinessUnitCode();
        }
        return retrieveDivisionUnitList(yyyy, businessUnit, dataRange);
    }
    
    public List<DivisionUnitVO> retrieveDivisionUnitListDataRange(String yyyy, String dataRange, String businessUnit, UserSession userSession, String reportingType, String divisionCodes) {
        if ( "C".equals(dataRange) ) {
            businessUnit = userSession.getBusinessUnitCode();
        }
        return retrieveDivisionUnitList(yyyy, businessUnit, dataRange, reportingType, divisionCodes);
    }
    public List<DivisionUnitVO> retrieveDivisionUnitList(String yyyy, String businessUnit, String dataRange){
        return retrieveDivisionUnitList(yyyy, businessUnit, dataRange, null, null);
    }
    
    public List<DivisionUnitVO> retrieveDivisionUnitList(String yyyy, String businessUnit, String dataRange, String reportingType, String divisionCodes){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringBuffer selectPattern = new StringBuffer();
        
        List<DivisionUnitVO> result = new ArrayList<DivisionUnitVO>();
        if ( "C".equals(dataRange) ) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[businessUnitCode]", GlobalConstants.OQL_OPERATOR_EQUAL, businessUnit);
            if(!NullUtil.isNone(reportingType)){
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[reportingType]", GlobalConstants.OQL_OPERATOR_EQUAL, reportingType);
            }
            if(!NullUtil.isNone(divisionCodes) && !",".equals(divisionCodes)){
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_IN, divisionCodes);
            }
            result = ObjectRoot.findObjects(
                    ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT,
                    GlobalConstants.FLAG_TYPE_ALL, 
                    GlobalConstants.FLAG_TYPE_ALL, 
                    selectPattern.toString(),
                    wherePattern.toString(), 
                    paramPattern.toString(), 
                    false, 
                    0);
        } else {
            if(!NullUtil.isNone(reportingType)){
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[reportingType]", GlobalConstants.OQL_OPERATOR_EQUAL, reportingType);
            }
            if(!NullUtil.isNone(divisionCodes) && !",".equals(divisionCodes)){
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_IN, divisionCodes);
            }
            
            result = this.getRelatedObjects(
                    ApplicationSchemaConstants.RELCLASS_MEMBERS,
                    ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT,
                    GlobalConstants.FLAG_TYPE_FROM,
                    selectPattern.toString(),
                    wherePattern.toString(), 
                    paramPattern.toString(), 
                    false,
                    false,
                    0,
                    1
            );
        }
        Map<String, ObjectRootVO> divisionMapDB = ObjectRoot.makeVoDB(result, "names");
        wherePattern.setLength(0);paramPattern.setLength(0);
        Set<String> divisionSet = ObjectRoot.getDistinctValueSet(result, "names", true);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[divisionCode]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(divisionSet));
        if( !StrUtil.isEmpty(yyyy) ){           
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[startYyyy]", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, yyyy);
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[endYyyy]",GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, yyyy);
        }
        List<DivisionPeriodVO> divisionPeriodVOList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_DIVISIONPERIOD, "", wherePattern.toString(), paramPattern.toString());
        List<DivisionUnitVO> divisionResult = new ArrayList<DivisionUnitVO>();
        for(DivisionPeriodVO vo : divisionPeriodVOList){
            DivisionUnitVO tempVO = ((DivisionUnitVO)divisionMapDB.get(vo.getDivisionCode()));
            tempVO.setOutDataAttributeValue("hrDepartmentCode", vo.getDepartmentCode());
            divisionResult.add(tempVO);
        }
        
        if(NullUtil.isNone(reportingType)){
            SortUtil.sort(divisionResult, "titles",false);
        }else{
            SortUtil.sort(divisionResult, "sequences",false);
        }
        
        return divisionResult;
    }
       
    /**
     * retrieve All DivisionUnit To User
     *
     * @return
     */
    public List<DivisionUnitVO> retrieveDivisionUnitList(){
        //���� �⵵�� Division List
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return retrieveDivisionUnitList(String.valueOf(year), "", "");        

    }
    
    public List<ManagementGroupListVO> retrieveManagementGroupList(){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringBuffer selectPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        
        return getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_MANAGEMENTGROUP2USERS,
                ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUPLIST,
                GlobalConstants.FLAG_TYPE_FROM,
                "SortBy@this.[titles]",
                wherePattern.toString(),
                paramPattern.toString(),
                false,
                false,
                0,
                1
        );           
    }

    public List<WorkflowInboxTaskVO> retrieveApprovalList(){
        return retrieveApprovalList(this.getVo().getNames());
    }

    /**
     * ���� ��� ��ȸ With Paging
     *
     * @param searchInfo
     * @return
     */
    public List<WorkflowInboxTaskVO> retrieveApprovalList(WorkflowInboxTaskVO searchInfo){
        return retrieveApprovalList(searchInfo, this.getVo().getNames());
    }

    //INBOX_TASK_TYPE_SAVEDRAFT

    /**
     * ���� ��� ��ȸ
     *
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrieveApprovalList(String userId){
        return getInboxTaskList(userId, WorkflowConstants.INBOX_TASK_TYPE_APPROVAL);
    }

    /**
     * ���� ��� ��ȸ With Paging
     *
     * @param searchInfo
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrieveApprovalList(WorkflowInboxTaskVO searchInfo, String userId){
        return getInboxTaskList(searchInfo, userId, WorkflowConstants.INBOX_TASK_TYPE_APPROVAL,new HashMap<String,Object>());
    }
    /**
     * Save Draft ��ȸ
     *
     * @return
     */
    public List<WorkflowInboxTaskVO> retrieveSaveDraftList(){
        return retrieveSaveDraftList(this.getVo().getNames());
    }

    /**
     * Save Draft With Paging
     *
     * @param searchInfo
     * @return
     */
    public List<WorkflowInboxTaskVO> retrieveSaveDraftList(WorkflowInboxTaskVO searchInfo){
        return retrieveSaveDraftList(searchInfo, this.getVo().getNames());
    }

    /**
     * Save Draft ��ȸ
     *
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrieveSaveDraftList(String userId){
        return getInboxTaskList(userId, WorkflowConstants.INBOX_TASK_TYPE_SAVEDRAFT);
    }

    /**
     * Save Draft ��ȸ With Paging
     *
     * @param searchInfo
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrieveSaveDraftList(WorkflowInboxTaskVO searchInfo, String userId){
        return getInboxTaskList(searchInfo, userId, WorkflowConstants.INBOX_TASK_TYPE_SAVEDRAFT,new HashMap<String,Object>());
    }
    /**
     * ���� ��� ��ȸ
     *
     * @return
     */
    public List<WorkflowInboxTaskVO> retrieveDistributionList(){
        return retrieveDistributionList(this.getVo().getNames());
    }

    /**
     * ���� ��� ��ȸ With Paging
     *
     * @param searchInfo
     * @return
     */
    public List<WorkflowInboxTaskVO> retrieveDistributionList(WorkflowInboxTaskVO searchInfo){
        return retrieveDistributionList(searchInfo, this.getVo().getNames());
    }

    /**
     * ���� ��� ��ȸ
     *
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrieveDistributionList(String userId){
        return getInboxTaskList(userId, WorkflowConstants.INBOX_TASK_TYPE_DISTRIBUTION);
    }

    /**
     * ���� ��� ��ȸ With Paging
     *
     * @param searchInfo
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrieveDistributionList(WorkflowInboxTaskVO searchInfo, String userId){
        return getInboxTaskList(searchInfo, userId, WorkflowConstants.INBOX_TASK_TYPE_DISTRIBUTION,new HashMap<String,Object>());
    }

    /**
     * �����û ��� ��ȸ
     *
     * @return
     */
    public List<WorkflowInboxTaskVO> retrieveRequestedList(){
        return retrieveRequestedList(this.getVo().getNames());
    }

    /**
     * �����û ��� ��ȸ With Paging
     *
     * @param searchInfo
     * @return
     */
    public List<WorkflowInboxTaskVO> retrieveRequestedList(WorkflowInboxTaskVO searchInfo){
        return retrieveRequestedList(searchInfo, this.getVo().getNames());
    }

    /**
     * Pending Change ��� ��ȸ With Paging
     *
     * @param searchInfo
     * @return
     */
    public List<WorkflowInboxTaskVO> retrievePendingChangeList(WorkflowInboxTaskVO searchInfo){
        return retrievePendingChangeList(searchInfo, this.getVo().getNames());
    }

    /**
     * Pending Change Mailing ��� ��ȸ
     *
     * @param searchInfo
     * @return
     */
    public static List<WorkflowInboxTaskVO> retrievePendingChangeMailingList(){
        return getInboxTaskList(new WorkflowInboxTaskVO(), null, WorkflowConstants.INBOX_TASK_TYPE_PENDING_CHANGE,new HashMap<String,Object>());
    }

    /**
     * �����û ��� ��ȸ
     *
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrieveRequestedList(String userId){
        return getInboxTaskList(userId, WorkflowConstants.INBOX_TASK_TYPE_REQUESTED);
    }

    /**
     * ���û ��� ��ȸ With Paging
     *
     * @param searchInfo
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrieveRequestedList(WorkflowInboxTaskVO searchInfo, String userId){
        return getInboxTaskList(searchInfo, userId, WorkflowConstants.INBOX_TASK_TYPE_REQUESTED,new HashMap<String,Object>());
    }

    /**
     * Pending Change ��� ��ȸ With Paging
     *
     * @param searchInfo
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrievePendingChangeList(WorkflowInboxTaskVO searchInfo, String userId){
        return getInboxTaskList(searchInfo, userId, WorkflowConstants.INBOX_TASK_TYPE_PENDING_CHANGE,new HashMap<String,Object>());
    }

    /**
     * ���οϷ� ��� ��ȸ
     *
     * @return
     */
    public List<WorkflowInboxTaskVO> retrieveApprovedList(){
        return retrieveApprovedList(this.getVo().getNames());
    }

    /**
     * ���οϷ� ��� ��ȸ With Paging
     *
     * @param searchInfo
     * @return
     */
    public List<WorkflowInboxTaskVO> retrieveApprovedList(WorkflowInboxTaskVO searchInfo){
        return retrieveApprovedList(searchInfo, this.getVo().getNames());
    }

    /**
     * ���οϷ� ��� ��ȸ
     *
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrieveApprovedList(String userId){
        return getInboxTaskList(userId, WorkflowConstants.INBOX_TASK_TYPE_APPROVED);
    }

    /**
     * ���οϷ� ��� ��ȸ With Paging
     *
     * @param searchInfo
     * @param userId
     * @return
     */
    public static final List<WorkflowInboxTaskVO> retrieveApprovedList(WorkflowInboxTaskVO searchInfo, String userId){
        return getInboxTaskList(searchInfo, userId, WorkflowConstants.INBOX_TASK_TYPE_APPROVED,new HashMap<String,Object>());
    }

    /**
     * ����� ID �� taskType �������� InboxTask ��� ��ȸ
     *
     * @param userId
     * @param taskType
     * @return
     */
    private static List<WorkflowInboxTaskVO> getInboxTaskList(WorkflowInboxTaskVO searchInfo, String userId, String taskType, Map<String,Object> parmMap){
        boolean isActivity = false;
        if(ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY.equals((String)parmMap.get("callBackType"))) isActivity = true;
        List<WorkflowInboxTaskVO> result = InBoxTask.getInboxListForUser(userId, (String)parmMap.get("startDate"), (String)parmMap.get("endDate"), taskType, isActivity, searchInfo);
        if(result != null && result.size() > 0) {
            String obid = null;
            BusinessObjectRoot obj = null;
            for( int inx = result.size() - 1; inx >= 0; inx-- ){
                obid = (String)result.get(inx).getOutDataAttributeValue("targetObid");
                try{
                    obj = DomUtil.toDom((String)result.get(inx).getOutDataAttributeValue("targetObid"));
                    if(!NullUtil.isNull(obj)){
                        result.get(inx).setOutDataAttributeValue("names", obj.getVo().getNames());
                        result.get(inx).setOutDataAttributeValue("states", obj.getVo().getStates());
                        result.get(inx).setOutDataAttributeValue("titles", obj.getCommonTitlesForDisplay());
                        result.get(inx).setOutDataAttributeValue("lifeCycle", obj.getVo().getLifeCycle());
                        result.get(inx).setOutDataAttributeValue("targetClassDesc", obj.getVo().getOutDataAttributeValue("this_displayedClassName"));                     
                        if( obj.getVo() instanceof WBSItemsVO){
                            Map<String,String> parameterMap =  obj.getParameterForWorkflow(new WorkflowInboxTask(result.get(inx).getObid()).getVo());
                            for( Map.Entry<String, String> element : parameterMap.entrySet() ){
                                result.get(inx).setOutDataAttributeValue(element.getKey(), element.getValue());
                            }
                        }
                        else{
                            if ( !NullUtil.isNull(obj.getVo().getCreator()) ) {
                                Users creator = Users.getUsers(obj.getVo().getCreator());
                                String targetCreator = "";
                                if ( !NullUtil.isNull(creator) ) {
                                    targetCreator = creator.getVo().getTitles();
                                    if ( ApplicationSchemaConstants.BIZCLASS_CHANGEPROCESS.equals(obj.getVo().getClassName()) ) {
                                        ChangeProcessVO changeVO = (ChangeProcessVO)obj.getVo();
                                        if ( !NullUtil.isNone(changeVO.getRequesterEmpNo()) ) {
                                            targetCreator = Users.getUsers(changeVO.getRequesterEmpNo()).getTitles();
                                        }
                                    }else if ( ApplicationSchemaConstants.BIZCLASS_PRODPLANPRMSHEET.equals(obj.getVo().getClassName()) ){
                                        targetCreator = result.get(inx).getOutDataAttributeValue("this_creatorName");
                                    }
                                    result.get(inx).setOutDataAttributeValue("targetCreator", targetCreator);
                                }
                            }
                        }
                        // 2018.02.14 youngmi.won Obsolete PDR�� ��� Approval List���� �������� �ʵ��� ó��
                        if( ApplicationSchemaConstants.BIZCLASS_PDRPROPERTY.equals(obj.getVo().getClassName()) ){
                            PDRPropertyVO pdrVo = (PDRPropertyVO)obj.getVo();
                            if( "Y".equals(pdrVo.getHoldFlag()) ){
                                result.remove(inx);
                                if ( result instanceof PagingList ) {
                                    ((PagingList<WorkflowInboxTaskVO>)result).setRows(((PagingList<WorkflowInboxTaskVO>)result).getRows() - 1);
                                }
                            }
                        }
                    }
                    
                }
                catch(Exception e){
                    e.printStackTrace();
                    result.get(inx).setOutDataAttributeValue("names", "-");
                    result.get(inx).setOutDataAttributeValue("states", "-");
                    result.get(inx).setOutDataAttributeValue("titles", obid);
                    result.get(inx).setOutDataAttributeValue("lifeCycle", "-");
                    result.get(inx).setOutDataAttributeValue("targetClassDesc", "Deleted");
                }
            }
        }
        return result;
    }
    
    
    /**
     * ����� ID �� taskType �������� InboxTask ��� ��ȸ
     *
     * @param userId
     * @param taskType
     * @return
     */
    private static List<WorkflowInboxTaskVO> getInboxTaskList(String userId, String taskType){
        return getInboxTaskList(new WorkflowInboxTaskVO(), userId, taskType,null);
    }

    /**
     * My Approval Line ��� ��ȸ
     *
     * @return
     */
    public List<ApprovalLineVO> retrieveApprovalLineList(String policyName){
        return retrieveApprovalLineList(this.getVo().getNames(), policyName);
    }

    /**
     * My Approval Line ��� ��ȸ
     *
     * @param userId
     * @return
     */
    public static final List<ApprovalLineVO> retrieveApprovalLineList(String userId, String policyName){
        UsersVO usersVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, userId, false);
        Users usersDom = new Users(usersVO.getObid());

        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();

        // Policy Name ���� ����
        if(!StrUtil.isEmpty(policyName) ){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[appliedPolicy]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, policyName);
        }

        List<ApprovalLineVO> result = usersDom.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_USERS2APPROVALLINE,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_TO,
                "SortBy@this.[titles]",
                wherePattern.toString(),
                paramPattern.toString(),
                false,
                false,
                0,
                1
        );

        // ClassDisplayName ����
        if( result != null && result.size() > 0 ){
            String className = "";
            for( int inx = 0; inx < result.size(); inx++ ){
                className = result.get(inx).getAppliedType();
                if( !StrUtil.isEmpty(className) ){
                   // result.get(inx).getOutData().put( "appliedTypeDesc", ClassInfoUtil.retrieveClassDisplayName(className) );
                    result.get(inx).getOutData().put( "appliedTypeDesc", "appliedTypeDesc" );
                }
            }
        }

        return result;
    }

    /**
     * get Users by EmpNo
     *
     * @param userName
     * @return
     */
    public static final Users getUsers(String names){
        if(NullUtil.isNull(names) ) { return null; };
        
        UsersVO usersVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_USERS, names);
        if(!NullUtil.isNull(usersVO) ){ return DomUtil.toDom(usersVO); }
        return null;
    }
    /**
     * get Users by EmpNo
     *
     * @param userName
     * @return
     */
    public static final List<UsersVO> getUserListById( String idList ){
        if( NullUtil.isNull(idList) ){
            return null;
        };
        
        List<UsersVO> voUserList = BusinessObjectMaster.findObjects( ApplicationSchemaConstants.BIZCLASS_USERS, idList );
        return voUserList;
    }

    /**
     * get Users by user Mail Id
     *
     * @param userName
     * @return
     */
    public static final Users getUsersByMailId(String mailId){
        return getUsersByMailId(mailId, null);
    }

    /**
     * get Users by user Mail Id
     *
     * @param userName
     * @return
     */
    public static final Users getUsersByMailId(String mailId, String inOffiStatFlag){

        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[mailId]",
                GlobalConstants.OQL_OPERATOR_EQUAL, mailId);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(NULLIF(@this.[inOffiStatFlag],''),'C')",
                GlobalConstants.OQL_OPERATOR_EQUAL, "C");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]",
                GlobalConstants.OQL_OPERATOR_EQUAL, "Active");
        
        UsersVO usersVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS
                , "", false, "", wherePattern.toString(), paramPattern.toString(), false);
        if( !NullUtil.isNull(usersVO) ){ return DomUtil.toDom(usersVO); }
        return null;
    }
    
    /**
     * ������ OBID ��ȸ
     * : ���糯¥�� ���ӱⰣ�� ���Ե� ��쿡�� ������ OBID ��ȯ
     * @param fromUserObid
     * @return
     */
    public static final String getDelegatorObid(String fromUserObid){
        String toUserObid = fromUserObid;
        Users userDom = new Users( fromUserObid );
        if( userDom != null ){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = simpleDateFormat.format( userDom.getVo().getAbsenceStartDate() );
            String endDate = simpleDateFormat.format( userDom.getVo().getAbsenceEndDate() );
            String currDate = simpleDateFormat.format( new Date( System.currentTimeMillis() ) );

            if( (startDate.compareTo(currDate) == 0 || startDate.compareTo(currDate) < 0)
                    && (endDate.compareTo(currDate) == 0 || endDate.compareTo(currDate) > 0) ){
                UsersVO usersVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, userDom.getVo().getAbsenceDelegate(), false);
                if( usersVO != null ){
                    toUserObid = usersVO.getObid();
                }
            }
        }

        return toUserObid;
    }


    /**
     * ������ UserVO ��ȸ , ������ ������ �ڱ� �ڽ� ��ȯ
     *
     * @return
     */
    public final UsersVO getDelegatorUsersVO(){
        if( !NullUtil.isNull(getVo().getAbsenceStartDate()) && !NullUtil.isNull(getVo().getAbsenceEndDate()) && !NullUtil.isNull(getVo().getAbsenceDelegate()) ){
            Date today = TimeServiceUtil.getDBLocalTime();
            if( today.after(getVo().getAbsenceStartDate()) && today.before(getVo().getAbsenceEndDate()) ){
                UsersVO usersVo = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_USERS, getVo().getAbsenceDelegate());
                if( !NullUtil.isNull(usersVo) ){
                   return usersVo;
                }
            }
        }
        return getVo();
    }

    /**
     * DesktopTile ����� ��ȯ
     *
     * @return
     */
    public final List<DesktopTileVO> retrieveChildDesktopTileListByType( String tileType, String targetObid ){

        StringBuffer sbWhere = new StringBuffer();
        StringBuffer sbParam = new StringBuffer();

//        if( tileType.equals( DesktopTile.TILE_TYPE_OBJECT ) ){
//            StringUtil.constructWherePattern(
//                sbWhere,
//                sbParam,
//                "@this.[targetObid]",
//                GlobalConstants.OQL_OPERATOR_EQUAL,
//                targetObid );
//        }
        return this.retrieveChildDesktopTileList( sbWhere.toString(), sbParam.toString() );
    }

    public final List<DesktopTileVO> retrieveChildDesktopTileList(){
        return this.retrieveChildDesktopTileList( null, null );
    }

    public final List<DesktopTileVO> retrieveChildDesktopTileList( String wherePattern, String paramPattern ){

        StringBuffer sbSelect = new StringBuffer();
        List<DesktopTileVO> voTileList;

        voTileList = this.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_USERS2DESKTOPTILE,
                ApplicationSchemaConstants.BIZCLASS_DESKTOPTILE,
                GlobalConstants.FLAG_TYPE_TO,
                sbSelect.toString(),
                wherePattern,
                paramPattern,
                false,
                false,
                0,
                1
        );
        return voTileList;
    }

    public final DesktopTileVO retrieveChildDesktopTile( String childObid ){

        StringBuffer sbWhere = new StringBuffer();
        StringBuffer sbParam = new StringBuffer();

        StringUtil.constructWherePattern(
            sbWhere,
            sbParam,
            "@this.[obid]",
            GlobalConstants.OQL_OPERATOR_EQUAL,
            childObid );

        StringBuffer sbSelect = new StringBuffer();
        List<DesktopTileVO> voTileList;

        voTileList = this.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_USERS2DESKTOPTILE,
                ApplicationSchemaConstants.BIZCLASS_DESKTOPTILE,
                GlobalConstants.FLAG_TYPE_TO,
                sbSelect.toString(),
                sbWhere.toString(),
                sbParam.toString(),
                false,
                false,
                0,
                1
        );
        DesktopTileVO voTile = null;
        if( voTileList.size() > 0 ){
            voTile = voTileList.get( 0 );
        }
        return voTile;
    }

    public int getMaxDesktopSequenceAmongChildren(){

        String relationName = ApplicationSchemaConstants.RELCLASS_USERS2DESKTOPTILE;
        String className    = ApplicationSchemaConstants.BIZCLASS_DESKTOPTILE;
        String direction    = GlobalConstants.FLAG_TYPE_TO;

        List<BusinessRelationObjectVO> relationList = this.getRelationship( relationName, className, direction );

        int sequences = 0;
        Users2DesktopTileVO  voRelation;

        for( int index = 0; index < relationList.size(); index ++ ){
             voRelation = (Users2DesktopTileVO)relationList.get( index );
             if( voRelation.getSequences() != null ){
                 sequences = Math.max( sequences, voRelation.getSequences() );
             }
        }
        return sequences;
    }

    /**
     * DesktopWidget ����� ��ȯ
     *
     * @return
     */

    public final List<DesktopWidgetVO> retrieveDesktopWidgetList(){
        return this.retrieveDesktopWidgetList( "PMS" );
    }
    
    public final List<DesktopWidgetVO> retrieveDesktopWidgetList( String targetModule ){

        List<DesktopWidgetVO> voWidgetList;

        StringBuffer sbWhere = new StringBuffer();
        StringBuffer sbParam = new StringBuffer();

        StringUtil.constructWherePattern(
             sbWhere,
             sbParam,
             "@this.[targetModule]",
             GlobalConstants.OQL_OPERATOR_EQUAL,
             targetModule
        );

        voWidgetList = this.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_USERS2DESKTOPWIDGET,
                ApplicationSchemaConstants.BIZCLASS_DESKTOPWIDGET,
                GlobalConstants.FLAG_TYPE_TO,
                null,
                sbWhere.toString(),
                sbParam.toString(),
                false,
                false,
                0,
                1
        );
        return voWidgetList;
    }

    public void saveDesktopWidgetList( String targetModule, List<DesktopWidgetVO> voNewWidgetList ){

        List<DesktopWidgetVO> voCurWidgetList = this.retrieveDesktopWidgetList( targetModule );

        DesktopWidget domWidget;
        for( DesktopWidgetVO voCurWidget : voCurWidgetList ){
             domWidget = DomUtil.toDom( voCurWidget );
             domWidget.deleteObject();
        }

        String userObid = this.getVo().getObid();
        for( DesktopWidgetVO voNewWidget : voNewWidgetList ){
            domWidget = DomUtil.toDom( voNewWidget );
            //voNewWidget.setNames(NameGeneratorUtil.generateUniqueName(ApplicationSchemaConstants.BIZCLASS_DESKTOPWIDGET));
            voNewWidget.setClassName(ApplicationSchemaConstants.BIZCLASS_DESKTOPWIDGET);
            voNewWidget.setStates(ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS);
            voNewWidget.setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WITHOUT_STATE);
            voNewWidget.setTargetModule(targetModule);
            domWidget.createObject();

            RelationShip.connect( ApplicationSchemaConstants.RELCLASS_USERS2DESKTOPWIDGET, userObid, domWidget.getVo().getObid(), null );
        }
    }

    /**
     * ������� LegacyLink �����ȸ (For Context Menu in Desktop)
     * @param userId
     * @return
     */
    public static List<LegacyLinkVO> retrieveLegacyLinkList(String userId){
        UsersVO usersVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, userId, false);
        Users userDom = new Users( usersVO.getObid() );
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();

        StringUtil.constructWherePattern(
                wherePattern,
                paramPattern,
                "@this.[descriptions]",
                GlobalConstants.OQL_OPERATOR_NOT_EQUAL,
                "DesktopTile.TILE_DESC_PREFIX_LEGACYLINK" + GlobalConstants.FLAG_TYPE_ALL
        );

        List<DesktopTileVO> tileList = userDom.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_USERS2DESKTOPTILE,
                ApplicationSchemaConstants.BIZCLASS_DESKTOPTILE,
                GlobalConstants.FLAG_TYPE_TO,
                null,
                wherePattern.toString(),
                paramPattern.toString(),
                false, false, 0, 0
        );

//        List<LegacyLinkVO> legacyLinkList = LegacyLink.retrieveLegacyLinkForLoginUser( userId );
//        if( legacyLinkList != null && legacyLinkList.size() > 0 ){
//            HashMap<String, Object> outData = null;
//            boolean isExist = false;
//            for( int inx = 0; inx < legacyLinkList.size(); inx++ ){
//                if( tileList != null && tileList.size() > 0 ){
//                    isExist = false;
//                    for( int jnx = 0; jnx < tileList.size(); jnx++ ){
//                        if( tileList.get(jnx).getDescriptions().indexOf( legacyLinkList.get(inx).getObid() ) > 0 ){
//                            isExist = true;
//                            break;
//                        }
//                    }
//                }
//                else{
//                    isExist = false;
//                }
//
//                outData = legacyLinkList.get(inx).getOutData();
//                outData.put( "isExist", isExist );
//                legacyLinkList.get(inx).setOutData( outData );
//            }
//        }

        //return legacyLinkList;
        return null;
    }
       
    public static DepartmentVO getUserDepartment(String userId){
        UsersVO usersVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, userId, false);
        DepartmentVO deptVO =  ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_DEPARTMENT, usersVO.getHrDepartmentCode(), false);
        
//        if ( !NullUtil.isNull(deptVO) ) {
//            Department dept = DomUtil.toDom(deptVO.getObid());
//            if ( !NullUtil.isNull(dept) ) {
//                HashMap<String, Object> outData = deptVO.getOutData();
//                outData.put("accountingDeptObid", dept.getAccountingDepartmentObid());
//            }
//        }
        return deptVO;
    }
    public DepartmentVO getDepartment(){
        //if(StrUtil.isEmpty(this.getHRDepartmentCode())) return null;
        return ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_DEPARTMENT, this.getVo().getHrDepartmentCode(), false);
    }
    

    public static List<ModelDevelopmentGeneralProjectVO> retrieveMyHESuffixPaging(ProjectsVO searchVO){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();
        StringBuffer selectPattern = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectStartDate]), '%Y-%m-%d') projectStartDate");
        StringUtil.constructSelectPattern(selectPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectEndDate]), '%Y-%m-%d') projectEndDate");
        StringUtil.constructSelectPattern(selectPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[mpDate]), '%Y-%m-%d') projectMpDate");
        StringUtil.constructSelectPattern(selectPattern, "IFNULL(FN_GET_PROJECT_EVENT(@this.[currentEventCode],@this.[obid]),' ') currentEventCodeName");
        StringUtil.constructSelectPattern(selectPattern, "IFNULL(FN_GET_PROJECT_STATUS(@this.[obid]),' ') approval");
        StringUtil.constructSelectPattern(selectPattern, "@this.[obid] projectObid");
        StringUtil.constructSelectPattern(selectPattern, "getUserInfo(@this.[projectLeader],'T') plName");
        StringUtil.constructSelectPattern(selectPattern, "From[ProjectModel{[representativeModelFlag] == 'Y'}].To.names modelSuffix");

        StringUtil.constructSelectPattern(selectPattern, "IFNULL(FN_GET_CODEDETAIL_L(@this.[deriMainCode],'DERIVATION_MAIN',@this.[divisionCode],'" + "searchVO.getUserLocale()" + "'),' ') deriMainCode"); 
        StringUtil.constructSelectPattern(selectPattern, "IFNULL(FN_GET_CODEDETAIL_L(@this.[deriSubCode],'DERIVATION_SUB_GROUP',@this.[divisionCode],'" + "searchVO.getUserLocale()" + "'),' ') deriSubCode"); 
        
        StringUtil.constructSelectPattern(selectPattern, "IFNULL(FN_GET_PROJECT_HESUFFIX(@this.[obid]),' ') activityStates");
        
        StringUtil.addSortByPattern(selectPattern, "@this.[created] desc, @this.[projectName]"); 
        
        if(!StrUtil.isEmpty("searchVO.getUserId()")){        
            StringUtil.constructWherePattern(wherePattern, paramPattern, "From[DerivationCheckListUser].Self.userId", 
                    GlobalConstants.OQL_OPERATOR_EQUAL, "searchVO.getUserId()");
        }

        if(!StrUtil.isEmpty(searchVO.getStates())){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, searchVO.getStates());
        }

        
        if(!StrUtil.isEmpty(searchVO.getTitles())){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "UPPER(@this.[titles])", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + searchVO.getTitles().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL);
        }
        
        if(!StrUtil.isEmpty(searchVO.getBmsProjectCode())){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[bmsProjectCode]", GlobalConstants.OQL_OPERATOR_LIKE, searchVO.getBmsProjectCode().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL);
        }
        
//        if(!StrUtil.isEmpty(searchVO.getYyyy())){
//            StringBuffer wherePatternNull = new StringBuffer();
//            StringBuffer wherePatternStartNull = new StringBuffer();
//            StringBuffer wherePatternEndNull = new StringBuffer();
//            StringBuffer wherePatternNotNull = new StringBuffer();
//            
//            StringUtil.constructWherePattern(wherePatternNull, paramPattern, "IFNULL(@this.[projectStartDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
//            StringUtil.constructWherePattern(wherePatternNull, paramPattern, "IFNULL(@this.[projectEndDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
//            
//            StringUtil.constructWherePattern(wherePatternStartNull, paramPattern, "IFNULL(@this.[projectStartDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
//            StringUtil.constructWherePattern(wherePatternStartNull, paramPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectEndDate]), '%Y')", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, "searchVO.getYyyy()");
//            
//            StringUtil.constructWherePattern(wherePatternEndNull, paramPattern, "IFNULL(@this.[projectEndDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
//            StringUtil.constructWherePattern(wherePatternEndNull, paramPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectStartDate]), '%Y')", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, "searchVO.getYyyy()");
//            
//            StringUtil.constructWherePattern(wherePatternNotNull, paramPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectStartDate]), '%Y')", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, "searchVO.getYyyy()");
//            StringUtil.constructWherePattern(wherePatternNotNull, paramPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectEndDate]), '%Y')", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, "searchVO.getYyyy()");
//                        
//            StringBuffer dateWherePattern = new StringBuffer(StringUtil.makeOrPattern( wherePatternNull.toString()
//                                                                                      ,StringUtil.makeOrPattern(
//                                                                                                              StringUtil.makeOrPattern(wherePatternStartNull.toString(), wherePatternEndNull.toString())
//                                                                                                              , wherePatternNotNull.toString()
//                                                                                                              )
//                                                                                      )
//                                                             );
//            StringUtil.constructOrWherePattern(wherePattern, dateWherePattern.toString());
//        }

        List<ModelDevelopmentGeneralProjectVO> projectList = ObjectRoot.searchObjectPagingList(ApplicationSchemaConstants.BIZCLASS_MODELDEVELOPMENTGENERALPROJECT,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, 
                selectPattern.toString(), 
                fromPattern.toString(), 
                wherePattern.toString(), 
                paramPattern.toString(),
                false, 
                searchVO);
        
        /*
        for(ProjectsVO vo : projectList){
            Projects dom = (Projects)DomUtil.toDom(vo);
            vo.getOutData().put("approval", dom.getCurrentProcess());
        }
        */

        return projectList;
    }

    public static List<ProjectsVO> retrieveMyProjectPaging(ProjectsVO searchVO){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();
        StringBuffer selectPattern = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectStartDate]), '%Y-%m-%d') projectStartDate");
        StringUtil.constructSelectPattern(selectPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectEndDate]), '%Y-%m-%d') projectEndDate");
        StringUtil.constructSelectPattern(selectPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[mpDate]), '%Y-%m-%d') projectMpDate");
        StringUtil.constructSelectPattern(selectPattern, "IFNULL(FN_GET_PROJECT_EVENT(@this.[currentEventCode],@this.[obid]),' ') currentEventCodeName");
        StringUtil.constructSelectPattern(selectPattern, "IFNULL(FN_GET_PROJECT_STATUS(@this.[obid]),' ') approval");
        StringUtil.constructSelectPattern(selectPattern, "@this.[obid] projectObid");
        StringUtil.constructSelectPattern(selectPattern, "getUserInfo(@this.[projectLeader],'T') plName");
        StringUtil.constructSelectPattern(selectPattern, "From[ProjectModel{[representativeModelFlag] == 'Y'}].To.names modelSuffix");

        StringUtil.addSortByPattern(selectPattern, "@this.[created] desc, @this.[projectName]"); 

        fromPattern.append("<this>ThisConnectedWithFrom<[AssignedToProject]@AP>+");
        fromPattern.append("<[AssignedToProject]@AP>ToConnectedWithThis<[ProjectPerson]@PP>+");        

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@PP.obid.From[AllocatedMyRole].toClass",GlobalConstants.OQL_OPERATOR_EQUAL, "ProjectDefinedRole");

        
        if(!StrUtil.isEmpty(searchVO.getStates())){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, searchVO.getStates());
        }
        if(!StrUtil.isEmpty(searchVO.getTitles())){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "UPPER(@this.[titles])", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + searchVO.getTitles().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL);
        }
        
        if(!StrUtil.isEmpty(searchVO.getBmsProjectCode())){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[bmsProjectCode]", GlobalConstants.OQL_OPERATOR_LIKE, searchVO.getBmsProjectCode().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL);
        }
        
        List<ProjectsVO> projectList = ObjectRoot.searchObjectPagingList(ApplicationSchemaConstants.BIZCLASS_PROJECTS,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, 
                selectPattern.toString(), 
                fromPattern.toString(), 
                wherePattern.toString(), 
                paramPattern.toString(),
                false, 
                searchVO);
        
        return projectList;
    }

    public static List<ProjectsVO> retrieveProject(String userId){
        return retrieveProject(userId, ApplicationSchemaConstants.BIZCLASS_PROJECTS, null, ApplicationSchemaConstants.STATE_PROJECT_RUNNING);
    }
    
    public static List<ProjectsVO> retrieveProjectForBudget(String userId, String yyyyMM){
        return retrieveProject(userId, ApplicationSchemaConstants.BIZCLASS_PROJECTS, yyyyMM, null);
    }
    
    /**
     * 
     *
     * @param userId
     * @param projectClass
     * @param yyyyMM : null�� �ƴ� ��� ���� ��뿩��, ���� ��� �Ⱓ ���� �ɾ�� �� 
     * @param status : null�� �ƴ� ��� ���·� ����
     * @return
     */
    public static List<ProjectsVO> retrieveProject(String userId, String projectClass, String yyyyMM, String status){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPattern, "@PP.[obid] projectPersonObid");
        StringUtil.addSortByPattern(selectPattern, "@this.[titles]");
        
        fromPattern.append("<this>ThisConnectedWithFrom<[AssignedToProject]@REL>+");
        fromPattern.append("<[AssignedToProject]@REL>ToConnectedWithThis<[ProjectPerson]@PP>+");
        //fromPattern.append("<[ProjectPerson]@PP>ThisConnectedWithFrom<[AllocatedMyRole]@ALLC2ROLE>+");
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@PP.[userId]", GlobalConstants.OQL_OPERATOR_EQUAL, userId);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@PP.obid.From[AllocatedMyRole].toClass",GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.BIZCLASS_PROJECTDEFINEDROLE);
        
        String todayYMD = TimeServiceUtil.getDBLocalTimeStr().substring(0,10).replaceAll("-", "");
        String todayYM = TimeServiceUtil.getDBLocalTimeStr().substring(0,7).replaceAll("-", "");
        String todayYY = TimeServiceUtil.getDBLocalTimeStr().substring(0,5).replaceAll("-", "");
       

        if ( !StrUtil.isEmpty(yyyyMM) ) {
            String listyyyy = yyyyMM.substring(0,4);
            
            if ( todayYY.compareTo(listyyyy) == 0 ) {
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[budgetUseFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
            }else{
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[budgetUseFlag]", GlobalConstants.OQL_OPERATOR_IN, "Y,N");
            }
                
//            if ( todayYM.compareTo(yyyyMM) == 0 ) {
//                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[budgetStartDate]", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, todayYMD);
//                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[budgetEndDate]", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, todayYMD);
//            } else if ( todayYM.compareTo(yyyyMM) > 0 ) {
//                StringUtil.constructWherePattern(wherePattern, paramPattern, "SUBSTR(@this.[budgetStartDate],1,6)", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, yyyyMM);
//                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[budgetEndDate]", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, todayYMD);
//            } else {
//                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[budgetStartDate]", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, todayYMD);
//                StringUtil.constructWherePattern(wherePattern, paramPattern, "SUBSTR(@this.[budgetEndDate],1,6)", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, yyyyMM);
//            }
            
            StringBuffer wherePatternNull = new StringBuffer();
            StringBuffer wherePatternStartNull = new StringBuffer();
            StringBuffer wherePatternEndNull = new StringBuffer();
            StringBuffer wherePatternNotNull = new StringBuffer();
            
            StringUtil.constructWherePattern(wherePatternNull, paramPattern, "IFNULL(@this.[budgetStartDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
            StringUtil.constructWherePattern(wherePatternNull, paramPattern, "IFNULL(@this.[budgetEndDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
            
            StringUtil.constructWherePattern(wherePatternStartNull, paramPattern, "IFNULL(@this.[budgetStartDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
            StringUtil.constructWherePattern(wherePatternEndNull, paramPattern, "IFNULL(@this.[budgetEndDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
            if ( todayYM.compareTo(yyyyMM) == 0 ) {
                StringUtil.constructWherePattern(wherePatternStartNull, paramPattern, "SUBSTR(@this.[budgetEndDate], 1, 4)", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, listyyyy);
                
                StringUtil.constructWherePattern(wherePatternEndNull, paramPattern, "SUBSTR(@this.[budgetStartDate], 1, 4)", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, listyyyy);
                
                StringUtil.constructWherePattern(wherePatternNotNull, paramPattern, "SUBSTR(@this.[budgetStartDate], 1, 4)", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, listyyyy);
                StringUtil.constructWherePattern(wherePatternNotNull, paramPattern, "SUBSTR(@this.[budgetEndDate], 1, 4)", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, listyyyy);
            } else if ( todayYM.compareTo(yyyyMM) > 0 ) {
                StringUtil.constructWherePattern(wherePatternStartNull, paramPattern, "SUBSTR(@this.[budgetEndDate], 1, 4)", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, listyyyy);
                
                StringUtil.constructWherePattern(wherePatternEndNull, paramPattern, "SUBSTR(@this.[budgetStartDate], 1, 4)", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, listyyyy);
                
                StringUtil.constructWherePattern(wherePatternNotNull, paramPattern, "SUBSTR(@this.[budgetStartDate], 1, 4)", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, listyyyy);
                StringUtil.constructWherePattern(wherePatternNotNull, paramPattern, "SUBSTR(@this.[budgetEndDate], 1, 4)", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, listyyyy);
            } else {
                StringUtil.constructWherePattern(wherePatternStartNull, paramPattern, "SUBSTR(@this.[budgetEndDate], 1, 6)", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, yyyyMM);
                
                StringUtil.constructWherePattern(wherePatternEndNull, paramPattern, "@this.[budgetStartDate]", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, todayYMD);
                
                StringUtil.constructWherePattern(wherePatternNotNull, paramPattern, "@this.[budgetStartDate]", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, todayYMD);
                StringUtil.constructWherePattern(wherePatternNotNull, paramPattern, "SUBSTR(@this.[budgetEndDate], 1, 6)", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, yyyyMM);
            }
//            StringBuffer dateWherePattern = new StringBuffer(StringUtil.makeOrPattern( wherePatternNull.toString()
//                                                                                      ,StringUtil.makeOrPattern(
//                                                                                                              StringUtil.makeOrPattern(wherePatternStartNull.toString(), wherePatternEndNull.toString())
//                                                                                                              , wherePatternNotNull.toString()
//                                                                                                              )
//                                                                                      )
//                                                             );
            StringBuffer dateWherePattern = new StringBuffer(StringUtil.makeOrPattern(wherePatternNull.toString(), wherePatternStartNull.toString(), 
                    wherePatternEndNull.toString(), wherePatternNotNull.toString()));
            StringUtil.constructOrWherePattern(wherePattern, dateWherePattern.toString());
        }

        if ( !StrUtil.isEmpty(status) ) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, status);
        }
        
        List<ProjectsVO> projectList = ObjectRoot.searchObjects(
                projectClass, 
                GlobalConstants.FLAG_TYPE_ALL, 
                GlobalConstants.FLAG_TYPE_ALL, 
                selectPattern.toString(), 
                fromPattern.toString(), 
                wherePattern.toString(), 
                paramPattern.toString(), 
                true, 
                0);

        List<ProjectsVO> budgetProjectList = new ArrayList<ProjectsVO>();
        
        if ( !StrUtil.isEmpty(yyyyMM) ) {
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.MONTH, -1);

            SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
            String currentYyyyMm = format1.format(cal.getTime()); ;
            
            for( ProjectsVO projectVO : projectList){
                if("Y".equals(projectVO.getMmInputYn())){
                    StringBuffer selectPatternBudget = new StringBuffer();
                    StringBuffer fromPatternBudget = new StringBuffer();
                    StringBuffer wherePatternBudget = new StringBuffer();
                    StringBuffer paramPatternBudget = new StringBuffer();
                    
                    StringUtil.addSortByPattern(selectPatternBudget, "@this.[fromObid]");
                    
                    //���� ������ ���� ������Ʈ�� ǰ�Ǽ� �ۼ��� �� �� ����
                    StringUtil.constructWherePattern(wherePatternBudget, paramPatternBudget, "@this.[fromObid]",    GlobalConstants.OQL_OPERATOR_EQUAL, projectVO.getObid());
                    StringUtil.constructWherePattern(wherePatternBudget, paramPatternBudget, "@this.[planMonth]",    GlobalConstants.OQL_OPERATOR_EQUAL, currentYyyyMm);
                    StringUtil.constructWherePattern(wherePatternBudget, paramPatternBudget, "@this.[actualManMonth]",    GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "0");

                    List<ProjectUserActualMMVO> actualList = ObjectRoot.searchObjects(ApplicationSchemaConstants.RELCLASS_PROJECTUSERACTUALMM,
                            GlobalConstants.FLAG_TYPE_ALL,
                            GlobalConstants.FLAG_TYPE_ALL,
                            selectPatternBudget.toString(),   
                            fromPatternBudget.toString(),
                            wherePatternBudget.toString(),
                            paramPatternBudget.toString(),
                            false,
                            0);
                    
                    if(!NullUtil.isNull(actualList) && actualList.size() != 0) {
                        budgetProjectList.add(projectVO);
                    }
                } else {
                    budgetProjectList.add(projectVO);
                }
            }
        } else {
            budgetProjectList = projectList;
        }
        
        return budgetProjectList;
    }
    
    /**
     * 
     *
     * @param userId
     * @param projectClass
     * @param yyyyMM : null�� �ƴ� ��� ���� ��뿩��, ���� ��� �Ⱓ ���� �ɾ�� �� 
     * @param status : null�� �ƴ� ��� ���·� ����
     * @return
     */
    public static List<ProjectsVO> retrieveProjectForYear(String userId, String projectClass, String yyyy, String status){
        StringBuffer fromPattern = new StringBuffer();
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPattern, "@PP.[obid] projectPersonObid");
        StringUtil.addSortByPattern(selectPattern, "@this.[titles]");
        
        fromPattern.append("<this>ThisConnectedWithFrom<[AssignedToProject]@REL>+");
        fromPattern.append("<[AssignedToProject]@REL>ToConnectedWithThis<[ProjectPerson]@PP>+");
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@PP.[userId]", GlobalConstants.OQL_OPERATOR_EQUAL, userId);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@PP.obid.From[AllocatedMyRole].toClass",GlobalConstants.OQL_OPERATOR_EQUAL, "ProjectDefinedRole");

        if ( !StrUtil.isEmpty(yyyy) ) {
            StringBuffer wherePatternNull = new StringBuffer();
            StringBuffer wherePatternStartNull = new StringBuffer();
            StringBuffer wherePatternEndNull = new StringBuffer();
            StringBuffer wherePatternNotNull = new StringBuffer();
            
            StringUtil.constructWherePattern(wherePatternNull, paramPattern, "IFNULL(@this.[projectStartDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
            StringUtil.constructWherePattern(wherePatternNull, paramPattern, "IFNULL(@this.[projectEndDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
            
            StringUtil.constructWherePattern(wherePatternStartNull, paramPattern, "IFNULL(@this.[projectStartDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
            StringUtil.constructWherePattern(wherePatternStartNull, paramPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectEndDate]), '%Y')", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, yyyy);
            
            StringUtil.constructWherePattern(wherePatternEndNull, paramPattern, "IFNULL(@this.[projectEndDate],'null')", GlobalConstants.OQL_OPERATOR_EQUAL, "null");
            StringUtil.constructWherePattern(wherePatternEndNull, paramPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectStartDate]), '%Y')", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, yyyy);
            
            StringUtil.constructWherePattern(wherePatternNotNull, paramPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectStartDate]), '%Y')", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, yyyy);
            StringUtil.constructWherePattern(wherePatternNotNull, paramPattern, "DATE_FORMAT(omcConvertUtcToLocal(@this.[projectEndDate]), '%Y')", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, yyyy);
                        
            StringBuffer dateWherePattern = new StringBuffer(StringUtil.makeOrPattern( wherePatternNull.toString()
                                                                                      ,StringUtil.makeOrPattern(
                                                                                                              StringUtil.makeOrPattern(wherePatternStartNull.toString(), wherePatternEndNull.toString())
                                                                                                              , wherePatternNotNull.toString()
                                                                                                              )
                                                                                      )
                                                             );
            StringUtil.constructOrWherePattern(wherePattern, dateWherePattern.toString());
        }
        if ( !StrUtil.isEmpty(status) ) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, status);
        }
        
        List<ProjectsVO> projectList = ObjectRoot.searchObjects(
                projectClass,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                false,
                false,
                false,
                false,
                selectPattern.toString(),
                fromPattern.toString(),
                wherePattern.toString(),
                paramPattern.toString(),
                true,
                0
        );

        return projectList;
    }

    /**
     * 
     *
     * @param userId
     * @param projectClass
     * @param yyyyMM : null�� �ƴ� ��� ���� ��뿩��, ���� ��� �Ⱓ ���� �ɾ�� �� 
     * @param status : null�� �ƴ� ��� ���·� ����
     * @return
     */
    public static List<ProjectsVO> retrieveProjectOcs(String userId, String projectClass, String yyyy, String status){
        StringBuffer fromPattern = new StringBuffer();
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPattern, "@PP.[obid] projectPersonObid");
        StringUtil.addSortByPattern(selectPattern, "@this.[titles]");
        
        fromPattern.append("<this>ThisConnectedWithFrom<[AssignedToProject]@REL>+");
        fromPattern.append("<[AssignedToProject]@REL>ToConnectedWithThis<[ProjectPerson]@PP>+");
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@PP.[userId]", GlobalConstants.OQL_OPERATOR_EQUAL, userId);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@PP.obid.From[AllocatedMyRole].toClass",GlobalConstants.OQL_OPERATOR_EQUAL, "ProjectDefinedRole");
         int Year2 = Integer.parseInt(yyyy) - 1;
        String Syear   = Integer.toString(Year2);
        
        if ( !StrUtil.isEmpty(yyyy) ) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[budgetUseFlag]", GlobalConstants.OQL_OPERATOR_IN, "Y,N");
            StringBuffer wherePatternfrom = new StringBuffer();
            StringBuffer wherePatternto = new StringBuffer();
            String dateWherePattern = "";
            StringUtil.constructWherePattern(wherePatternfrom, paramPattern, "IFNULL(DATE_FORMAT(omcConvertUtcToLocal(@this.[projectStartDate]), '%Y'), '"+yyyy+"')",GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, yyyy);
            StringUtil.constructWherePattern(wherePatternfrom, paramPattern, "IFNULL(DATE_FORMAT(omcConvertUtcToLocal(@this.[projectEndDate]), '%Y'), '"+yyyy+"')",GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, yyyy);
            
            StringUtil.constructWherePattern(wherePatternto, paramPattern, "IFNULL(DATE_FORMAT(omcConvertUtcToLocal(@this.[projectStartDate]), '%Y'), '"+Syear+"')",GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, Syear);
            StringUtil.constructWherePattern(wherePatternto, paramPattern, "IFNULL(DATE_FORMAT(omcConvertUtcToLocal(@this.[projectEndDate]), '%Y'), '"+Syear+"')",GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, Syear);
            
            dateWherePattern = StringUtil.makeOrPattern( wherePatternfrom.toString()  ,wherePatternto.toString());
            StringUtil.constructOrWherePattern(wherePattern, dateWherePattern);
        }
        if ( !StrUtil.isEmpty(status) ) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, status);
        }
        
      
       
        List<ProjectsVO> projectList = ObjectRoot.searchObjects(
                projectClass, 
                GlobalConstants.FLAG_TYPE_ALL, 
                GlobalConstants.FLAG_TYPE_ALL, 
                selectPattern.toString(), 
                fromPattern.toString(), 
                wherePattern.toString(), 
                paramPattern.toString(), 
                true, 
                0);

        return projectList;
    }
    
    public static List<AddressBookVO> retrieveAddressBook(String userId){
        UsersVO usersVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, userId, false);
        Users userDom = new Users( usersVO.getObid() );
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        List<AddressBookVO> projectList = userDom.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_ADDRESSBOOKUSER,
                ApplicationSchemaConstants.BIZCLASS_ADDRESSBOOK,
                GlobalConstants.FLAG_TYPE_FROM,
                null,
                wherePattern.toString(),
                paramPattern.toString(),
                false, false, 0, 0
        );

        return projectList;
    }
    
    public static List<UsersVO> retrieveRecentUser(String userId){
        UsersVO usersVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, userId, false);
        Users userDom = new Users( usersVO.getObid() );
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringBuffer selectPattern = new StringBuffer();
        
        StringUtil.addSortByPattern(selectPattern, "@this.[modified] desc");

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, "Active");
        
        List<UsersVO> userList = userDom.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_SEARCHRECENTUSER,
                ApplicationSchemaConstants.BIZCLASS_USERS,
                GlobalConstants.FLAG_TYPE_TO,
                selectPattern.toString(),
                wherePattern.toString(),
                paramPattern.toString(),
                false, false, 0, 0
        );

        return userList;
    }

    public static List<UsersVO> retrieveTeamUser(String userId){
        UsersVO usersVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, userId, false);
        
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "(@this.[hrDepartmentCode])",
                GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + usersVO.getHrDepartmentCode());
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, "Active");
        
        List<UsersVO> userList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_USERS,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, selectPattern.toString(),
                wherePattern.toString(), paramPattern.toString(), false, 0);
        return userList;
    }
    /**
     * �μ��� ����� ����Ʈ ��ȸ(�����μ�, ����μ�)
     *
     * @param deptCode
     * @param type
     * @return
     */
    public static List<AbstractUsersVO> retrieveTeamUserList(String deptCode, String type){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        if ( !StrUtil.isEmpty(deptCode) ) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[hrDepartmentCode]", GlobalConstants.OQL_OPERATOR_EQUAL, deptCode);
        }
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_PERSON_ACTIVE);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[inOffiStatFlag]", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "T");
        
        String classType = "";
        if ( "ALL".equals(type) ) {
            classType = ApplicationSchemaConstants.BIZCLASS_USERS + "," + ApplicationSchemaConstants.BIZCLASS_ABSTRACTUSERS;
        } else {
            classType = ApplicationSchemaConstants.BIZCLASS_USERS;
        }
        
        List<AbstractUsersVO> userList = ObjectRoot.findObjects(
                classType,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, 
                selectPattern.toString(),
                wherePattern.toString(), 
                paramPattern.toString(), 
                false, 
                0);

        return userList;
    }
    /**
     * Vesion�� ����� ����Ʈ ��ȸ
     *
     * @param versionObid
     * @return
     */
    public static List<UsersVO> retrieveTeamUserList(String versionObid){
        
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();
        
        fromPattern.append("<this>ThisConnectedWithTo<[HasVersionMember]@REL>+");
        fromPattern.append("<[HasVersionMember]@REL>FromConnectedWithThis<[HasVersionDepartment]@DET>+");
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@DET.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, versionObid);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(NULLIF(@this.[gradeCode],''),'null')", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "null");
        
        StringUtil.constructSelectPattern(selectPattern, "@this.[*]");
        
        List<UsersVO> usersList = ObjectRoot.searchObjects(                
                ApplicationSchemaConstants.BIZCLASS_USERS,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                false,
                false,
                false,
                false,
                selectPattern.toString(),
                fromPattern.toString(),
                wherePattern.toString(),
                paramPattern.toString(),
                true,
                0);

        return usersList;
    }
      
    public AuthorizationGroupVO retrieveAuthorizationGroup( ){
        List<AuthorizationGroupVO> authList = this.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_HASAUTHORIZATIONGROUP,
                ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONGROUP,
                GlobalConstants.FLAG_TYPE_FROM
        );

        if(authList.size() > 0) {
            return authList.get(0);
        }
        
        return null;
    }
    
    public AuthorizationGroupVO retrieveAuthorizationGroup( String module ){
        StringBuffer wherePatternBuf  = new StringBuffer();
        StringBuffer paramPatternBuf  = new StringBuffer();
        
        if( !NullUtil.isNone( module ) ){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[module]", GlobalConstants.OQL_OPERATOR_EQUAL, module);
        }
        
        List<AuthorizationGroupVO> authList =  this.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_HASAUTHORIZATIONGROUP,
                ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONGROUP,
                GlobalConstants.FLAG_TYPE_FROM,
                wherePatternBuf.toString(), 
                paramPatternBuf.toString(),
                0 );

        if(authList.size() > 0) {
            return authList.get(0);
        }
        
        return null;
    }
    
    public AuthorizationMenuVO retrieveAuthorizationMenu( String mnuName, String module ){
        AuthorizationMenuVO authMenuVO = new AuthorizationMenuVO();
        
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf  = new StringBuffer();
        StringBuffer paramPatternBuf  = new StringBuffer();
        
        if( !NullUtil.isNone( module ) ){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[module]", GlobalConstants.OQL_OPERATOR_EQUAL, module);
        }
        
        List<AuthorizationGroupVO> authGroupList =  this.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_HASAUTHORIZATIONGROUP,
                ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONGROUP,
                GlobalConstants.FLAG_TYPE_FROM,
                wherePatternBuf.toString(), 
                paramPatternBuf.toString(),
                0 );
        
        if ( !NullUtil.isNone(authGroupList) && authGroupList.size() > 0 ) {
            String fromObid = "";
            for ( AuthorizationGroupVO authGroup : authGroupList ) {
                if ( StrUtil.isEmpty(fromObid) ) fromObid = authGroup.getObid();
                else fromObid += "," + authGroup.getObid();
            }
            
            wherePatternBuf.setLength(0);
            paramPatternBuf.setLength(0);
            StringUtil.addSortByPattern(selectPatternBuf, "@this.[orgRange] desc");
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_IN, fromObid);
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]", GlobalConstants.OQL_OPERATOR_LIKE, mnuName + GlobalConstants.FLAG_TYPE_ALL);
            
            List<AuthorizationMenuVO> authMenuList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONMENU, 
                    selectPatternBuf.toString(), 
                    wherePatternBuf.toString(), 
                    paramPatternBuf.toString());
            
            if ( !NullUtil.isNone(authMenuList) && authMenuList.size() > 0 ) {
                authMenuVO = authMenuList.get(0);
            }
        }
        
        return authMenuVO;
    }
    /**
     * User �� �Ҵ�� Job Activity List �� Return ��.
     *
     * @param notCompletedOnly
     * @return
     */
    public List<WBSJobActivityVO> getMyJobActivityList(boolean notCompletedOnly){          
          StringBuffer selectPattern = new StringBuffer();
          StringBuffer wherePattern = new StringBuffer();
          StringBuffer paramPattern = new StringBuffer();
          StringBuffer fromPattern = new StringBuffer();
          StringUtil.constructFromPattern(fromPattern,OmcApplicationConstants.CONN_TO_THIS_ALIAS            ,"this",OmcApplicationConstants.CONN_THIS_CONNECTED_FROM ,ApplicationSchemaConstants.RELCLASS_ASSIGNEDPROJECTPERSON,"AP");
          StringUtil.constructFromPattern(fromPattern,ApplicationSchemaConstants.RELCLASS_ASSIGNEDPROJECTPERSON   ,"AP" ,OmcApplicationConstants.CONN_TO_CONNECTED_THIS    ,ApplicationSchemaConstants.BIZCLASS_USERS    ,"USERS");
          StringUtil.constructFromPattern(fromPattern,OmcApplicationConstants.CONN_TO_THIS_ALIAS            ,"this",OmcApplicationConstants.CONN_THIS_CONNECTED_TO   ,ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER,"ATM");
          StringUtil.constructFromPattern(fromPattern,ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER       ,"ATM" ,OmcApplicationConstants.CONN_FROM_CONNECTED_THIS ,ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES    ,"ACT");
          StringUtil.constructSelectPattern(selectPattern, "@USERS.[names]");
          StringUtil.constructSelectPattern(selectPattern, "@ACT.[*]");
          
          
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@USERS.[names]" , GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getNames());
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@ACT.[states]" , GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED);
          
          if(notCompletedOnly){
              StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[progress]", GlobalConstants.OQL_OPERATOR_LESS_THAN, "100");
          }
          
          return ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY,
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
       * User�� �Ҵ� �Ǿ��� Activity List�� Return��.
       *
       * @param classFilter
       * @param isDelayedOnly
       * @return
     */
//    public List<WBSActivitiesVO> getMyRunningActivityList(Set<String> classFilter, boolean isDelayedOnly){
//        return getMyRunningActivityList(classFilter, isDelayedOnly, false);
//    }
//    public List<WBSActivitiesVO> getMyRunningActivityList(Set<String> classFilter, boolean isDelayedOnly, boolean onlyPrevActivityComplete){
//        
//        StringBuffer selectPattern = new StringBuffer();
//        StringBuffer wherePattern = new StringBuffer();
//        StringBuffer paramPattern = new StringBuffer();
//        StringBuffer fromPattern = new StringBuffer();
//        
//        StringUtil.constructFromPattern(fromPattern,OmcApplicationConstants.CONN_TO_THIS_ALIAS                      ,"this",OmcApplicationConstants.CONN_THIS_CONNECTED_FROM ,ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY,"ATA");
//        StringUtil.constructFromPattern(fromPattern,ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY                ,"ATA" ,OmcApplicationConstants.CONN_TO_CONNECTED_THIS   ,ApplicationSchemaConstants.BIZCLASS_PROJECTPERSON    ,"PER");
//        
//        StringUtil.constructSelectPattern(selectPattern, "@PER.[userId]");
//        
//        StringUtil.constructSelectPattern(selectPattern, "From["+ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES+"].obid hasDeliverable");
//        
//        StringUtil.constructWherePattern(wherePattern, paramPattern, "@PER.[userId]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getNames());
//        StringUtil.constructWherePattern(wherePattern, paramPattern, "From[ControlledByProjectScheduleContext].To.states", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_STARTED);
//        StringUtil.constructWherePattern(wherePattern, paramPattern, "@PER.obid.To[AssignedToProject].From.states", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_PROJECT_RUNNING);
//
//        if(!classFilter.isEmpty()){
//            StringUtil.constructWherePattern(wherePattern, paramPattern, "@PER.obid.To[AssignedToProject].From.[className]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(classFilter));
//        }
//        
//        if(onlyPrevActivityComplete){
////            StringUtil.constructWherePattern(wherePattern, paramPattern, "!From["+ApplicationSchemaConstants.RELCLASS_WBSDEPENDENCY+"].To.states", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED);
//        }
//        Date baseDate = TimeServiceUtil.getTruncatedDBLocalDate();
//        StringUtil.constructWherePattern(wherePattern, paramPattern, "concat(@this.[states],'')", GlobalConstants.OQL_OPERATOR_IN, ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED);
//        if(isDelayedOnly){
//            String toDay = CommonUtil.converDateFormat(baseDate, GlobalConstants.DEFAULT_DATE_FORMAT);
//            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[planEndDate]", GlobalConstants.OQL_OPERATOR_LESS_THAN, "TO_DATE:" +toDay);
//        }
////        
//        StringUtil.addSortByPattern(selectPattern, "@this.[planStartDate] desc, @this.[projectName], @this.[activityNameKor]");
//        
//        List<WBSActivitiesVO> wbsActivities = ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES,
//                                                                       GlobalConstants.FLAG_TYPE_ALL,
//                                                                       GlobalConstants.FLAG_TYPE_ALL,
//                                                                       selectPattern.toString(),
//                                                                       fromPattern.toString(),
//                                                                       wherePattern.toString(),
//                                                                       paramPattern.toString(),
//                                                                       true,
//                                                                       0
//                                                                       );
////        
//        for(WBSItemsVO vo : wbsActivities){
//            Integer delayedDays = TimeServiceUtil.getDateDiff(((WBSItemsVO)vo).getPlanEndDate(),baseDate);
//            if(delayedDays > 0){
//                vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_SIMULATION_MAP_delayedDays, delayedDays);
//            }
//        }
//        
//        return wbsActivities;
//    }
//    public <T extends ProjectsVO> List<T>getMyRunningDelayedProjectList(Set<String> classFilter, boolean includePending){
//        return(getMyProjectListSub(classFilter,includePending,false,false));   
//    }
//    public <T extends ProjectsVO> List<T>getMyRunningProjectList(Set<String> classFilter, boolean includePending){
//        return(getMyProjectListSub(classFilter,includePending,false,false));   
//    }
//    public <T extends ProjectsVO> List<T>getMyProjectList(Set<String> classFilter, boolean includePending,boolean includeCompleted, boolean includePlanned){
//        return(getMyProjectListSub(classFilter,includePending,includeCompleted,includePlanned));   
//    }
    
    /**
     * 
     *
     * @param userId
     * @return
     */
    public static Set<String> getMyManagementRoleSet(String userId){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        Set<String> roleSet = new HashSet<String>();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[person]", GlobalConstants.OQL_OPERATOR_IN, userId);
        List<ChangeAuthVO> list = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_CHANGEAUTH, wherePattern.toString(), parameterPattern.toString());
        for(ChangeAuthVO vo : list){
            if(!StrUtil.isEmpty(vo.getDivisionName()) && !StrUtil.isEmpty(vo.getAuthName())){
                roleSet.add(vo.getDivisionName() + "." + vo.getAuthName());
            }
        }
        return roleSet;
    }
    /**
     * 
     *
     * @return
     */
    public Set<String> getMyManagementRoleSet(){
        return Users.getMyManagementRoleSet(this.getNames());
    }
    
    
   /**
    * ����� Titles ���� ����
    *
    * @return
    */
   private String getMakeTitles(){
       String titles = "";
       if(!StrUtil.isEmpty(this.getVo().getMailId())){
           titles = this.getVo().getDescriptions() + "(" + this.getVo().getMailId() + ")";
       }
       else{
           titles = this.getVo().getDescriptions();
       }
       return titles;
   }
   
   public Map<String,Object> getUserSessionInfo(){
	   Map<String,Object> userSessionMap = new HashMap<String,Object>();
	   SysUserVO sysUserVO =  UserManagementUtil.getUserInfo(this.getVo().getNames());
	   Map<String,String> propertyMap = sysUserVO.getPropertyList();
       
       String divisionUnitCode         = propertyMap.get(UserPropertyConstants.USER_PROPERTY_DR);
       String businessUnitCode = propertyMap.get(UserPropertyConstants.USER_PROP_BUSINESS_UNIT);
       String plantUnitCode = propertyMap.get(UserPropertyConstants.USER_PROPERTY_MR);
       
       String locale = propertyMap.get(UserPropertyConstants.USER_PROPERTY_LOCALE);
       if(StrUtil.isEmpty(locale)) locale = GlobalConstants.DEFAULT_LANG;

       String timeZone = propertyMap.get(UserPropertyConstants.USER_PROPERTY_TIME_ZONE);
       if(StrUtil.isEmpty(timeZone)) timeZone = "Asia/Seoul";
    
	   userSessionMap.put(UserSessionMappinKey.accountingDepartmentCode, getAccountingDepartmentCode());
	   userSessionMap.put(UserSessionMappinKey.accountingDepartmentCodeDesc, getAccountingDepartmentCodeDesc());
	   
	   userSessionMap.put(UserSessionMappinKey.companyCode, "LG");
	   userSessionMap.put(UserSessionMappinKey.companyCodeDesc, "LG");
	   
	   userSessionMap.put(UserSessionMappinKey.businessUnitCode, plantUnitCode);
	   userSessionMap.put(UserSessionMappinKey.businessUnitCodeDesc, businessUnitCode);
	   
	   userSessionMap.put(UserSessionMappinKey.divisionUnitCode, divisionUnitCode);
	   userSessionMap.put(UserSessionMappinKey.divisionUnitCodeDesc, divisionUnitCode);
	   
	   userSessionMap.put(UserSessionMappinKey.plantUnitCode, plantUnitCode);
	   userSessionMap.put(UserSessionMappinKey.plantUnitCodeDesc, plantUnitCode);
	   
	   userSessionMap.put(UserSessionMappinKey.daylightSavings, propertyMap.get(UserPropertyConstants.USER_PROPERTY_DAYLIGHT_SAVINGS));

	   userSessionMap.put(UserSessionMappinKey.departmentCode,this.getVo().getHrDepartmentCode());
	   userSessionMap.put(UserSessionMappinKey.departmentDesc, this.getVo().getHrDepartmentEng());
	   
	   userSessionMap.put(UserSessionMappinKey.email,  this.getVo().getEmailAddress());
	   userSessionMap.put(UserSessionMappinKey.leaderUserId, this.getVo().getLeaderEmployeeNo());
	   userSessionMap.put(UserSessionMappinKey.loginUrl, UserPropertyConstants.USER_PROPERTY_LOGIN_URL);
	   userSessionMap.put(UserSessionMappinKey.mainModule, getMainModule());
	   //userSessionMap.put(UserSessionMappinKey.managementRoleSet, getMyManagementRoleSet());

	   userSessionMap.put(UserSessionMappinKey.privateFolder, propertyMap.get(UserPropertyConstants.USER_PROPERTY_PRIVATE_FOLDER));
	   userSessionMap.put(UserSessionMappinKey.defaultPrivateFolder, propertyMap.get(UserPropertyConstants.USER_PROPERTY_DEFAULT_PRIVATE_FOLDER));
	   userSessionMap.put(UserSessionMappinKey.defaultProject, propertyMap.get(UserPropertyConstants.USER_PROPERTY_DEFAULT_PROJECT));
	   userSessionMap.put(UserSessionMappinKey.defaultProjectFolder, propertyMap.get(UserPropertyConstants.USER_PROPERTY_DEFAULT_PROJECT_FOLDER));
	   
	   userSessionMap.put(UserSessionMappinKey.telephone, "Not Defined");
	   userSessionMap.put(UserSessionMappinKey.timeStamp, sysUserVO.getTimeStamp());
	   userSessionMap.put(UserSessionMappinKey.userBizObid, this.getVo().getObid());
	   userSessionMap.put(UserSessionMappinKey.userLocale, locale);
	   userSessionMap.put(UserSessionMappinKey.userLoginSite, sysUserVO.getSite());
	   userSessionMap.put(UserSessionMappinKey.userNameEng, this.getVo().getNameEng());
	   userSessionMap.put(UserSessionMappinKey.userNameKor, this.getVo().getDescriptions());
	   userSessionMap.put(UserSessionMappinKey.userTimeZone, timeZone);
	   return userSessionMap;
   }
   
   private String getAccountingDepartmentCode() {
	   return "getAccountingDepartmentCode Not Defined";
   }
   private String getAccountingDepartmentCodeDesc() {
	   return "getAccountingDepartmentCodeDesc Not Defined";
   }

   private String getMainModule(){
	   return "getMainModule";
   }
   public static String getUserLocale(String userName){
       return UserManagementUtil.getUserProperty(userName,UserPropertyConstants.USER_PROPERTY_LOCALE);
   }
}

