/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowStep.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.dom;


import java.util.List;
import java.util.Map;

import rap.api.object.relation.dom.WorkflowStepNodeUser;
import rap.api.object.relation.model.WorkflowStepNodeUserVO;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.api.object.workflow.model.WorkflowStepVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.WorkflowConstants;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.lifecycle.model.StateInfo;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.util.StringUtil;


public class WorkflowStep extends BusinessObjectMaster {
    public WorkflowStep(String obid){
        super(obid);
    }
    public WorkflowStep(WorkflowStepVO vo){
        super(vo);
    }
    @Override
    public WorkflowStepVO getVo(){
        return (WorkflowStepVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeWorkflowStep();
    }
    public void initializeWorkflowStep(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "WorkflowStep[toString()=" + super.toString() + "]";
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
   /**
   *
   * @return
   */
  public List<BusinessRelationObjectVO> getWorkflowStepNodeUserVOList(){
      return this.getRelationship( ApplicationSchemaConstants.RELCLASS_WORKFLOWSTEPNODEUSER, 
              ApplicationSchemaConstants.BIZCLASS_USERS,
              GlobalConstants.FLAG_TYPE_TO);
  }
  /**
   *
   * @return
   */
  public Integer getNextSequenceOfWorkflowStepNodeUser(){
      Integer rtnSequence = 1;
      StringBuffer sql = new StringBuffer();

      sql.append("@REL.[sequences]");
      sql.append("+SortBy@REL.[sequences] desc ");
      
      String selectPattern = sql.toString();
      sql.setLength(0);   // string buffer 초기화

      sql.append("<this>ThisConnectedWithTo<[WorkflowStepNodeUser]@REL>+");
      sql.append("<[WorkflowStepNodeUser]@REL>FromConnectedWithThis<[WorkflowStep]@WFS>+");
      String fromPattern = sql.toString();
      sql.setLength(0);  // string buffer 초기화

      StringBuffer wherePatternBuf = new StringBuffer();
      StringBuffer paramPatternBuf = new StringBuffer();
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@WFS.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, getVo().getObid());
      String wherePattern = wherePatternBuf.toString();
      String parameterPattern = paramPatternBuf.toString();

      List<ObjectRootVO> searchObjectList = ObjectRoot.searchObjects( ApplicationSchemaConstants.BIZCLASS_USERS, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, selectPattern, fromPattern, wherePattern, parameterPattern, true, 0);
      
      if(searchObjectList.size() > 0) {
          ObjectRootVO objectRootVO = searchObjectList.get(0);
          Map<String, Object> rtnOutData = objectRootVO.getOutData();
          rtnSequence = (Integer)rtnOutData.get("rel_sequences") + 1;
      }
      return rtnSequence;
  }
  
  /**
   * 
   *
   * @return
   */
  public boolean isAllApproved(WorkflowRoute workflowRoute){
      boolean bResult = true;
      List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList = this.getWorkflowStepNodeUserVOList();
      for(BusinessRelationObjectVO businessRelationObjectVO : rtnBusinessRelationObjectVOList) {
          WorkflowInboxTaskVO workflowInboxTaskVO = workflowRoute.getWorkflowInboxTaskVOByWorkflowStepNodeUserVO((WorkflowStepNodeUserVO)businessRelationObjectVO);
          if(!WorkflowConstants.STATES_TYPE_COMPLETE.equals(workflowInboxTaskVO.getStates()) &&
                  !WorkflowConstants.APPROVAL_STATUS_APPROVE.equals( workflowInboxTaskVO.getApprovalStatus())    ){
              bResult = false;
              break;
          }
      }
      return bResult;
  }
  
  /**
   * 
   *
   * @param usersObid
   * @return
   */
  public WorkflowStepNodeUserVO getWorkflowStepNodeUserVOByUsersObid(String usersObid) {
      WorkflowStepNodeUserVO rtnWorkflowStepNodeUserVO = null;
      List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList = getWorkflowStepNodeUserVOList();
      for(BusinessRelationObjectVO businessRelationObjectVO : rtnBusinessRelationObjectVOList) {
          WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
          if(usersObid.equals(workflowStepNodeUser.getVo().getToObid())) {
              rtnWorkflowStepNodeUserVO = workflowStepNodeUser.getVo();
              break;
          }
      }
      return rtnWorkflowStepNodeUserVO;
  }
  
  
  
  public final void start() {
      if(!WorkflowConstants.STATES_TYPE_DEFINE.equals(this.getVo().getStates()))
          throw new ApplicationException("The states of workflow step must be Define.");

      this.promote();
  }
  
  public final void reset() {
      StateInfo firstState = getFirstState();
      while(!firstState.getStateName().equals(getVo().getStates())) {
          demote();
      }
  }
}

