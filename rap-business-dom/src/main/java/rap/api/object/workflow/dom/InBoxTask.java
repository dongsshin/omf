/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : InBoxTask.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.dom;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.workflow.model.InBoxTaskVO;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.WorkflowConstants;


public class InBoxTask extends BusinessObject {
    public InBoxTask(String obid){
        super(obid);
    }
    public InBoxTask(InBoxTaskVO vo){
        super(vo);
    }
    @Override
    public InBoxTaskVO getVo(){
        return (InBoxTaskVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeInBoxTask();
    }
    public void initializeInBoxTask(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "InBoxTask[toString()=" + super.toString() + "]";
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
   public static int getActivityTaskCountForUser(String userId){
       Set<String> routeActionSet = new HashSet<String>();
       Set<String> stateSet = new HashSet<String>();
       Set<String> inboxTaskTypeSet = new HashSet<String>();
       Set<String> approvalStatusSet = new HashSet<String>();
       
       routeActionSet.addAll(StrUtil.convertArrayToList(WorkflowConstants.ROUTE_ACTIONS_NOTCOMMENT_ALL));        
       stateSet.add(ApplicationSchemaConstants.STATE_INBOX_TASK_ASSIGNED);
       inboxTaskTypeSet.add(WorkflowConstants.INBOX_TASK_TYPE_WBSActivity);
       approvalStatusSet.add(WorkflowConstants.APPROVAL_STATUS_NONE);
       
       return getInboxCountForUserSub(userId,routeActionSet,stateSet,inboxTaskTypeSet,approvalStatusSet);
   }
   public static int getInboxTaskCountForUser(String userId){
       Set<String> routeActionSet = new HashSet<String>();
       Set<String> stateSet = new HashSet<String>();
       Set<String> inboxTaskTypeSet = new HashSet<String>();
       Set<String> approvalStatusSet = new HashSet<String>();
       
       routeActionSet.addAll(StrUtil.convertArrayToList(WorkflowConstants.ROUTE_ACTIONS_NOTCOMMENT_ALL));
       stateSet.add(ApplicationSchemaConstants.STATE_INBOX_TASK_ASSIGNED);
       inboxTaskTypeSet.add(WorkflowConstants.INBOX_TASK_TYPE_Workflow);
       approvalStatusSet.add(WorkflowConstants.APPROVAL_STATUS_NONE);
       
       return getInboxCountForUserSub(userId,routeActionSet,stateSet,inboxTaskTypeSet,approvalStatusSet);
   }
   private static int getInboxCountForUserSub( String userId, 
                                               Set<String> routeActionSet, 
                                               Set<String> stateSet, 
                                               Set<String> inboxTaskTypeSet,  
                                               Set<String> approvalStatusSet){

       PagingEntity searcInfo = new PagingEntity();
       searcInfo.setRowSize(1);
       List<WorkflowInboxTaskVO> result = getInboxListForUserCore(userId,routeActionSet,stateSet,inboxTaskTypeSet,approvalStatusSet,null,null,searcInfo,true);
       return ((PagingList<WorkflowInboxTaskVO>)result).getRows();
   }
   public static List<WorkflowInboxTaskVO> getInboxListForUser(String       userId, 
                                                               String       startDate,
                                                               String       endDate,
                                                               String       taskType,
                                                               boolean      isActivity,
                                                               PagingEntity searcInfo){
       Set<String> routeActionSet = new HashSet<String>();
       Set<String> stateSet = new HashSet<String>();
       Set<String> inboxTaskTypeSet = new HashSet<String>();
       Set<String> approvalStatusSet = new HashSet<String>();
       String       startDateNew = "";
       String       endDateNew = "";
       /*
       create index "ix_ptinboxtask_99" on ptinboxtask("ptask_owner","pstates","proute_action","papproval_status");
        */
       if( WorkflowConstants.INBOX_TASK_TYPE_APPROVAL.equals(taskType) ){
           stateSet.add(WorkflowConstants.STATES_TYPE_ASSIGNED);
           routeActionSet.addAll(StrUtil.convertArrayToList(WorkflowConstants.ROUTE_ACTIONS_NOTCOMMENT_ALL));
           if(isActivity){
               inboxTaskTypeSet.add(WorkflowConstants.INBOX_TASK_TYPE_WBSActivity);
           }else{
               inboxTaskTypeSet.add(WorkflowConstants.INBOX_TASK_TYPE_Workflow);
           }
       }
       if( WorkflowConstants.INBOX_TASK_TYPE_SAVEDRAFT.equals(taskType) ){
           stateSet.add(ApplicationSchemaConstants.STATE_INBOX_TASK_ASSIGNED);
           stateSet.add(ApplicationSchemaConstants.STATE_INBOX_TASK_REVIEW);
           routeActionSet.add(WorkflowConstants.ACTION_TYPE_END_WORKING);
       }
       if( WorkflowConstants.INBOX_TASK_TYPE_DISTRIBUTION.equals(taskType) ){
           stateSet.add(ApplicationSchemaConstants.STATE_INBOX_TASK_ASSIGNED);
           routeActionSet.add(WorkflowConstants.ROUTE_ACTIONS_COMMENT);
       }
       if( WorkflowConstants.INBOX_TASK_TYPE_REQUESTED.equals(taskType) ){
           stateSet.add(ApplicationSchemaConstants.STATE_INBOX_TASK_COMPLETE);
           routeActionSet.add(WorkflowConstants.ACTION_TYPE_END_WORKING);
       }
       if( WorkflowConstants.INBOX_TASK_TYPE_APPROVED.equals(taskType) ){
           stateSet.addAll(StrUtil.convertArrayToList(WorkflowConstants.INBOX_STATES_ALL));
           routeActionSet.add(WorkflowConstants.ROUTE_ACTIONS_APPROVE);routeActionSet.add(WorkflowConstants.ROUTE_ACTIONS_CONFIRM);routeActionSet.add(WorkflowConstants.ROUTE_ACTIONS_COMMENT);
           approvalStatusSet.addAll(StrUtil.convertArrayToList(WorkflowConstants.APPROVAL_STATUS_SET_FORAPPROVED));
           startDateNew = startDate;endDateNew = endDate;
       }
       return getInboxListForUserCore(userId,routeActionSet,stateSet,inboxTaskTypeSet,approvalStatusSet,startDateNew,endDateNew,searcInfo,false);
   }
   private static List<WorkflowInboxTaskVO> getInboxListForUserCore(String      userId, 
                                                                   Set<String> routeActionSet, 
                                                                   Set<String> stateSet, 
                                                                   Set<String> inboxTaskTypeSet,  
                                                                   Set<String>  approvalStatusSet,
                                                                   String       startDate,
                                                                   String       endDate,
                                                                   PagingEntity searcInfo,
                                                                   boolean      forCountOnly){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       if(!forCountOnly)
       {
           StringUtil.constructSelectPattern( selectPattern, "To[" + ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK + "].From.To[" + ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE + "].Self.fromObid targetObid");
           StringUtil.constructSelectPattern( selectPattern, "To[" + ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK + "].From.To[" + ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE + "].Self.fromClass targetClassName");
           StringUtil.constructSelectPattern( selectPattern, "To[" + ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK + "].fromObid routeObid");
           StringUtil.constructSelectPattern( selectPattern, "To[" + ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK + "].From.activityUrl activityUrl");
           StringUtil.constructSelectPattern(selectPattern, "getUserInfo(@this.[creator], 'T') creatorName");
           StringUtil.addSortByPattern(selectPattern, "@this.[created] desc");
       }
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[taskOwner]", GlobalConstants.OQL_OPERATOR_EQUAL, userId);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[previousObid]", GlobalConstants.OQL_OPERATOR_EQUAL, "1");
       StringUtil.constructWherePattern(wherePattern, paramPattern, "To[" + ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK + "].From.To[" + ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE + "].Self.obid", GlobalConstants.OQL_OPERATOR_GREATER_THAN, " ");
       
       if(!NullUtil.isNone(routeActionSet)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[routeAction]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(routeActionSet));
       if(!NullUtil.isNone(stateSet)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(stateSet));
       if(!NullUtil.isNone(inboxTaskTypeSet)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[inboxTaskType]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(inboxTaskTypeSet));
       if(!NullUtil.isNone(approvalStatusSet)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[approvalStatus]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(approvalStatusSet));
       if(!StrUtil.isEmpty(startDate)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[created]",GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, "TO_DATE:" + startDate + " 00:00:00");
       if(!StrUtil.isEmpty(endDate)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[created]",GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, "TO_DATE:" + endDate + " 23:59:59");
       List<WorkflowInboxTaskVO> result = null;
       if(!NullUtil.isNull(searcInfo)){
           result = ObjectRoot.findObjectPagingList(ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, selectPattern.toString(), wherePattern.toString(), paramPattern.toString(), true, searcInfo);
       }
       else{
           result = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK, selectPattern.toString(), wherePattern.toString(), paramPattern.toString());
       }
       return result;
   }
}

