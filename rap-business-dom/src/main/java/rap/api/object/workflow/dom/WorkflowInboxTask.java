/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowInboxTask.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.dom;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.lifecycle.model.StateInfo;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.model.UsersVO;
import rap.api.object.relation.dom.WorkflowRouteTask;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.api.object.workflow.model.WorkflowRouteVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.WorkflowConstants;


public class WorkflowInboxTask extends InBoxTask {
    public WorkflowInboxTask(String obid){
        super(obid);
    }
    public WorkflowInboxTask(WorkflowInboxTaskVO vo){
        super(vo);
    }
    @Override
    public WorkflowInboxTaskVO getVo(){
        return (WorkflowInboxTaskVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeWorkflowInboxTask();
    }
    public void initializeWorkflowInboxTask(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "WorkflowInboxTask[toString()=" + super.toString() + "]";
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
    *
    * @return
    */
   public List<BusinessRelationObjectVO> getWorkflowRouteTaskVOList() {
       return getRelationship(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK, ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE, GlobalConstants.FLAG_TYPE_FROM);
   }
   
   /**
    * 
    *
    * @return
    */
   public List<BusinessRelationObjectVO> getWorkflowRouteTaskHistoryVOList() {
       return getRelationship(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASKHISTORY, ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE, GlobalConstants.FLAG_TYPE_FROM);
   }
   
   /**
    * 
    *
    * @return
    */
   public UsersVO getUsersVO() {
       BusinessObjectVO businessObjectVO = this.getLastRevision();
       WorkflowInboxTask lastesWorkflowInboxTask = DomUtil.toDom(businessObjectVO.getObid(), false);
       return lastesWorkflowInboxTask.getRelatedObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWPROJECTTASK, ApplicationSchemaConstants.BIZCLASS_USERS, GlobalConstants.FLAG_TYPE_FROM);
   }
   
   /**
    * 
    */
   public void deleteInboxTask(WorkflowRoute workflowRoute) {
       String originTaskOwner = getVo().getOriginTaskOwner();
       String step = getVo().getStep();
       // 해당 inbox task 삭제
       List<BusinessRelationObjectVO> workflowRouteTaskHistoryVOList = getWorkflowRouteTaskHistoryVOList();
       if(NullUtil.isNone(workflowRouteTaskHistoryVOList)) { //no history
           deleteObject();
       }else{
           List<BusinessRelationObjectVO> workflowRouteTaskVOList = getWorkflowRouteTaskVOList();
           if(!NullUtil.isNone(workflowRouteTaskVOList)) {
               WorkflowRouteTask workflowRouteTask = DomUtil.toDom( workflowRouteTaskVOList.get(0));
               workflowRouteTask.deleteObject();
           }
       }
       
       
       //위임건 삭제
       if(!NullUtil.isNone(getVo().getOriginTaskOwner()) && getVo().getOriginTaskOwner().equals(getVo().getTaskOwner())) {    
           List<WorkflowInboxTaskVO> workflowInboxTaskVOList = workflowRoute.getWorkflowInboxTaskVOList();
           for(WorkflowInboxTaskVO workflowInboxTaskVO : workflowInboxTaskVOList) {
               if(!NullUtil.isNone(workflowInboxTaskVO.getOriginTaskOwner())
                     && originTaskOwner.equals(workflowInboxTaskVO.getOriginTaskOwner())
                     && step.equals(workflowInboxTaskVO.getStep())) {
               WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO.getObid(), false);
               workflowInboxTask.deleteObject();
               }
           }
       }
   }
   
   public void reset(String processTimestamp, Date dbLocalTime){
       getVo().setActualCompletionDate((Date)null);
       getVo().setComments(null);
       getVo().setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_NONE);
       getVo().setDelegatedFrom(null);
       getVo().setDelegatedTo(null);
       getVo().setProcessTimestamp(processTimestamp);

       if("Task Create Date".equals(getVo().getDateOffsetFrom())
             && getVo().getDueDateOffset() > 0) {
           Calendar c = Calendar.getInstance();
           c.setTime(dbLocalTime);
           c.add(Calendar.DATE, getVo().getDueDateOffset());
           getVo().setScheduledCompletionDate(c.getTime());
       }
       modifyObject();
       
       StateInfo firstState = this.getFirstState();
       while(!firstState.getStateName().equals(getVo().getStates())) {
           demote();
       }
   }
   
   public WorkflowRoute getWorkflowRoute() {
       WorkflowRoute workflowRoute = null;
       StringBuffer relationPatternSb = new StringBuffer();
       relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK);

       StringBuffer filterPatternSb = new StringBuffer();
       filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE);

       List<ObjectRootVO> rtnObjectRootVOList =
               this.getRelatedObjects(
                                           relationPatternSb.toString(),
                                           filterPatternSb.toString(),
                                           GlobalConstants.FLAG_TYPE_FROM,
                                           null,           // selectPattern
                                           null,           // wherePattern
                                           null,           // parameterPattern
                                           false,          // bInclude
                                           false,          // bResultUnique
                                           0,              // objectLimit
                                           20);            // findDepth
       if(!NullUtil.isNone(rtnObjectRootVOList)) {
           WorkflowRouteVO workflowRouteVO = (WorkflowRouteVO)rtnObjectRootVOList.get(0);
           workflowRoute = DomUtil.toDom(workflowRouteVO);
       }
       return workflowRoute;
   }
   
   /**
    * WorkflowRoute 및 BusinessObjectRoot 가져 오기
    *
    * @return
    */
   public final List<ObjectRootVO> getWorkflowRouteVOAndBusinessObjectRootVO() {
       List<ObjectRootVO> rtnObjectRootVOList = new ArrayList<ObjectRootVO>();
       
       WorkflowRoute workflowRoute = getWorkflowRoute();
       rtnObjectRootVOList.add(workflowRoute.getVo());
       
       BusinessObjectRoot businessObjectRoot = workflowRoute.getBusinessObjectRoot();
       rtnObjectRootVOList.add(businessObjectRoot.getVo());
       return rtnObjectRootVOList;
   }
   
   public final static WorkflowInboxTaskVO getWorkflowInboxTaskVO(String routeNodeObid) {
       if(NullUtil.isNone(routeNodeObid) ) throw new IllegalArgumentException();
       StringBuffer selectPatternBuf = new StringBuffer();
    // Where 조건 설정
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[routeNodeObid]",
               GlobalConstants.OQL_OPERATOR_EQUAL, routeNodeObid);
       
       return ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK, GlobalConstants.FLAG_TYPE_ALL, true, selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(), false);
       
   }
}

