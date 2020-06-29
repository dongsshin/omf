/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowRoute.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.dom;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.lifecycle.model.StateInfo;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.model.UsersVO;
import rap.api.object.relation.dom.WorkflowStepNodeUser;
import rap.api.object.relation.dom.WorkflowSubStep;
import rap.api.object.relation.model.WorkflowObjectRouteVO;
import rap.api.object.relation.model.WorkflowStepNodeUserVO;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.api.object.workflow.model.WorkflowRouteVO;
import rap.api.object.workflow.model.WorkflowStepVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.WorkflowConstants;
import rap.application.workflow.model.ApprovalVO;


public class WorkflowRoute extends BusinessObjectMaster {
    public WorkflowRoute(String obid){
        super(obid);
    }
    public WorkflowRoute(WorkflowRouteVO vo){
        super(vo);
    }
    @Override
    public WorkflowRouteVO getVo(){
        return (WorkflowRouteVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeWorkflowRoute();
    }
    public void initializeWorkflowRoute(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "WorkflowRoute[toString()=" + super.toString() + "]";
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
   public List<WorkflowInboxTaskVO> getWorkflowInboxTaskVOList(){
       return this.getRelatedObjects( 
               ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK, 
               ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK, 
               GlobalConstants.FLAG_TYPE_TO);
   }
   
   /**
    * Route�� inboxt task vo list by states(ex: Define, In Process, Complete) 
    *
    * @param states
    * @param sortField
    * @param sortDirection
    * @return
    */
   public List<WorkflowInboxTaskVO> getWorkflowInboxTaskVOListByStates(String states, String sortField, String sortDirection){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       if(!NullUtil.isNone(sortField) && !NullUtil.isNone(sortDirection))
           StringUtil.constructSelectPattern(selectPattern,"SortBy@this.["+sortField+"] "+sortDirection);
       
       if(!NullUtil.isNone(states))
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]",
                   GlobalConstants.OQL_OPERATOR_EQUAL, states);
       
       return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK, 
               ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK, 
               GlobalConstants.FLAG_TYPE_TO,
               selectPattern.toString(), 
               wherePatternBuf.toString(), 
               paramPatternBuf.toString(), 
               false,
               false, 
               0, 
               1);
   }
   
   
   
    /**
     *
     * @return
     */
    public List<WorkflowStepVO> getAllWorkflowStepVOList(){
        StringBuffer relationPatternSb = new StringBuffer();
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP);
        relationPatternSb.append(",");
        relationPatternSb.append(ApplicationSchemaConstants.RELCLASS_WORKFLOWSUBSTEP);
        
        StringBuffer filterPatternSb = new StringBuffer();
        filterPatternSb.append(ApplicationSchemaConstants.BIZCLASS_WORKFLOWSTEP);
        
        return this.getRelatedObjects(
                                            relationPatternSb.toString(),
                                            filterPatternSb.toString(), 
                                            GlobalConstants.FLAG_TYPE_TO, 
                                            null,           // selectPattern
                                            null,           // wherePattern
                                            null,           // parameterPattern
                                            false,          // bInclude
                                            false,          // bResultUnique
                                            0,              // objectLimit
                                            30);            // findDepth
    }
    /**
     *
     * @param sequences
     * @return
     */
    public WorkflowStepVO getNextWorkflowStepVO(Integer sequences){
        WorkflowStepVO rtnWorkflowStepVO = null;
        List<WorkflowStepVO> rtnWorkflowStepVOList  = getWorkflowStepVOList();
        Collections.sort(rtnWorkflowStepVOList, new SequenceComparator());
        
        for(WorkflowStepVO workflowStepVO : rtnWorkflowStepVOList) {
            //if(sequence < workflowStepVO.getSequences() && "Define".equals(workflowStepVO.getStates())) {
            if(sequences < workflowStepVO.getSequences()) {
                if(!WorkflowConstants.STATES_TYPE_DEFINE.equals(workflowStepVO.getStates())) throw new ApplicationException("The state of next step is incorrect["+workflowStepVO.getStates()+"]");
                rtnWorkflowStepVO =  workflowStepVO;
                break;
            }
        }
        return rtnWorkflowStepVO;
    }
    /**
     *
     * @param vo
     * @return
     */
    public WorkflowInboxTaskVO getWorkflowInboxTaskVOByWorkflowStepNodeUserVO(WorkflowStepNodeUserVO vo){
        if(NullUtil.isNull(vo.getObid())) throw new IllegalArgumentException();
        WorkflowInboxTaskVO rtnWorkflowInboxTaskVO = null;
        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList = getWorkflowInboxTaskVOList();
        for(WorkflowInboxTaskVO workflowInboxTaskVO : rtnWorkflowInboxTaskVOList) {
            if(vo.getObid().equals(workflowInboxTaskVO.getRouteNodeObid())) {
                rtnWorkflowInboxTaskVO =  workflowInboxTaskVO;
                break;
            }
        }
        return rtnWorkflowInboxTaskVO;
    }
    /**
     *
     * @param statesTypeInprocess
     * @return
     */
    public WorkflowStepVO getWorkflowStepVOListByStates(String statesTypeInprocess){
        if(NullUtil.isNone(statesTypeInprocess)) throw new IllegalArgumentException();
        WorkflowStepVO rtnWorkflowStepVO = null;
        List<WorkflowStepVO> rtnWorkflowStepVOList = getAllWorkflowStepVOList();
        for(WorkflowStepVO workflowStepVO: rtnWorkflowStepVOList) {
            if(statesTypeInprocess.equals(workflowStepVO.getStates())) {
                rtnWorkflowStepVO = workflowStepVO;
                break;
            }
        }
        return rtnWorkflowStepVO;
    }
    /**
     *
     * @return
     */
    public WorkflowStepVO getFirstWorkflowStepVO(){
        return this.getRelatedObject( ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP, 
                ApplicationSchemaConstants.BIZCLASS_WORKFLOWSTEP, 
                GlobalConstants.FLAG_TYPE_TO);
    }
    /**
     *
     * @param workflowStepNodeUserObid
     * @return
     */
    public WorkflowInboxTaskVO getWorkflowInboxTaskVOByWorkflowStepNodeUser(String workflowStepNodeUserObid){
        if(NullUtil.isNone(workflowStepNodeUserObid)) throw new IllegalArgumentException(" step node user obid is null");
        WorkflowInboxTaskVO rtnWorkflowInboxTaskVO = null;
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[routeNodeObid]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, workflowStepNodeUserObid);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[previousObid]",
                GlobalConstants.OQL_OPERATOR_EQUAL, "1");
        
        
        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList =  this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTETASK, 
                ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK, 
                GlobalConstants.FLAG_TYPE_TO,
                selectPattern.toString(), 
                wherePatternBuf.toString(), 
                paramPatternBuf.toString(), 
                false,
                false, 
                0, 
                1);
        
        if(!NullUtil.isNone(rtnWorkflowInboxTaskVOList))  rtnWorkflowInboxTaskVO = rtnWorkflowInboxTaskVOList.get(0);
        return rtnWorkflowInboxTaskVO;
    }
    /**
     *
     * @return
     */
    public List<BusinessRelationObjectVO> getWorkflowRouteStepVOList(){
        return this.getRelationship( 
                ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP, 
                ApplicationSchemaConstants.BIZCLASS_WORKFLOWSTEP, 
                GlobalConstants.FLAG_TYPE_TO);
    }
    /**
     *
     * @return
     */
    public List<WorkflowStepVO> getWorkflowStepVOList(){
        List<WorkflowStepVO> rtnWorkflowStepVOList =  this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP+","+ ApplicationSchemaConstants.RELCLASS_WORKFLOWSUBSTEP,
                ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE+","+ApplicationSchemaConstants.BIZCLASS_WORKFLOWSTEP,
                GlobalConstants.FLAG_TYPE_TO, 
                null, 
                null, 
                null, 
                false, 
                false, 
                0, 
                30);
        Collections.sort(rtnWorkflowStepVOList, new SequenceComparator());
        return rtnWorkflowStepVOList;
    }
    /**
     *
     * @return
     */
    public Map<String, Object> getWorkflowStepNSubStepList(){
        Map<String, Object> rtnWorkflowStepNSubStepMap = new HashMap<String, Object>();
        List<WorkflowStepVO> rtnWorkflowStepVOList = this.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP+","+ ApplicationSchemaConstants.RELCLASS_WORKFLOWSUBSTEP,
                ApplicationSchemaConstants.BIZCLASS_WORKFLOWROUTE+","+ApplicationSchemaConstants.BIZCLASS_WORKFLOWSTEP,
                GlobalConstants.FLAG_TYPE_TO, null, null, null, false, false, 0, 30);
        Map<Integer, WorkflowStep> workflowStepListMap = new TreeMap<Integer, WorkflowStep>();
        
        List<WorkflowSubStep> workflowSubStepList = new ArrayList<WorkflowSubStep>();
        for(WorkflowStepVO workflowStepVO : rtnWorkflowStepVOList) {
            WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO.getObid(), false);
            Map<String, Object> outDataMap = workflowStepVO.getOutData();
            workflowStepListMap.put(workflowStepVO.getSequences(), workflowStep);
            if(ApplicationSchemaConstants.RELCLASS_WORKFLOWSUBSTEP.equals((String)outDataMap.get("rel_className"))) {
                WorkflowSubStep workflowSubStep = DomUtil.toDom((String)outDataMap.get("rel_obid"), false);
                workflowSubStepList.add(workflowSubStep);
            }
        }
        
        rtnWorkflowStepNSubStepMap.put("workflowStepListMap", workflowStepListMap);
        rtnWorkflowStepNSubStepMap.put("workflowSubStepList", workflowSubStepList);
        return rtnWorkflowStepNSubStepMap;
    }
    /**
     *
     */
    public void deleteWorkflowInboxTaskForDemote(){
        List<WorkflowInboxTaskVO> toBeDeleteWorkflowInboxTaskVOList = getWorkflowInboxTaskVOList();
        for(WorkflowInboxTaskVO workflowInboxTaskVO : toBeDeleteWorkflowInboxTaskVOList) {
            if("Assigned".equals(workflowInboxTaskVO.getStates()) && "None".equals(workflowInboxTaskVO.getApprovalStatus())) {
                WorkflowInboxTask toBeDeleteWorkflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
                toBeDeleteWorkflowInboxTask.deleteInboxTask(this);
                
                //EPApprovalVO inputParams = new EPApprovalVO();
                //inputParams.setSystemPk(toBeDeleteWorkflowInboxTask.getVo().getObid());
                //inputParams.setSabun1(toBeDeleteWorkflowInboxTask.getVo().getTaskOwner());
                //inputParams.setTaskId(getVo().getObid());
                //CommonDaoUtil.insert("Workflow.insertABROutLGEPApprovalInfo", inputParams);
                //CommonDaoUtil.insert("Workflow.insertARDOutLGEPApprovalInfo", inputParams);
            }
        }
    }
    /**
     *
     * @return
     */
    public List<WorkflowInboxTaskVO> getWorkflowInboxTaskVONUsersVOList(){
        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVONUserVOList = new ArrayList<WorkflowInboxTaskVO>();
        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList = this.getWorkflowInboxTaskVOList();
        for(WorkflowInboxTaskVO workflowInboxTaskVO : rtnWorkflowInboxTaskVOList) {
            WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
            UsersVO rtnUsersVO = workflowInboxTask.getUsersVO();
            if(!NullUtil.isNull(rtnUsersVO)) {
                workflowInboxTaskVO.getOutData().put("UserVO", rtnUsersVO);
                rtnWorkflowInboxTaskVONUserVOList.add(workflowInboxTaskVO);
            }
        }
        return rtnWorkflowInboxTaskVOList;
    }
    
    public WorkflowStepNodeUserVO getWorkflowStepNodeUserVOByWorkflowInboxTaskVO(WorkflowInboxTaskVO workflowInboxTaskVO) {
        if(NullUtil.isNull(workflowInboxTaskVO.getObid()) || NullUtil.isNull(workflowInboxTaskVO.getRouteNodeObid())) throw new IllegalArgumentException();
        WorkflowStepNodeUserVO rtnWorkflowStepNodeUserVO = null;
        List<WorkflowStepVO> rtnWorkflowStepVOList = getAllWorkflowStepVOList();
        for(WorkflowStepVO workflowStepVO : rtnWorkflowStepVOList) {
            WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
            List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList = workflowStep.getWorkflowStepNodeUserVOList();
            for(BusinessRelationObjectVO businessRelationObjectVO : rtnBusinessRelationObjectVOList) {
                if(workflowInboxTaskVO.getRouteNodeObid().equals(businessRelationObjectVO.getObid())) {
                    WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
                    rtnWorkflowStepNodeUserVO = workflowStepNodeUser.getVo();
                    break;
                }
            }
        }
        return rtnWorkflowStepNodeUserVO;
    }
    
    public void getWorkflowNotExistStepNodeUserList(Set<String> checkUsersObidList) {
        List<String> rtnUsersObidList = new ArrayList<String>();
        List<WorkflowStepVO> rtnWorkflowStepVOList = getAllWorkflowStepVOList();
        for(WorkflowStepVO workflowStepVO: rtnWorkflowStepVOList) {
            WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
            List<BusinessRelationObjectVO> rtnBusinessRelationObjectVOList =  workflowStep.getWorkflowStepNodeUserVOList();
            for(BusinessRelationObjectVO businessRelationObjectVO : rtnBusinessRelationObjectVOList) {
                WorkflowStepNodeUser workflowStepNodeUser = DomUtil.toDom(businessRelationObjectVO.getObid(), false);
                rtnUsersObidList.add(workflowStepNodeUser.getUsersVO().getObid());
            }
        }
        
        Iterator<String> it = checkUsersObidList.iterator();
        while(it.hasNext()) {
            String toCompareUsersObid = it.next();
            if(rtnUsersObidList.contains(toCompareUsersObid)) {
                it.remove();
            }
        }
    }
    
    private static class SequenceComparator implements Comparator<WorkflowStepVO> {
        @Override
        public int compare(WorkflowStepVO s, WorkflowStepVO t) {
           return s.getSequences().compareTo(t.getSequences());
        }
    }
    
    public final void start() {
        if(!WorkflowConstants.STATES_TYPE_DEFINE.equals(this.getVo().getStates())) throw new ApplicationException("api.object.error.workflow.noStartRoute" );
        this.promote();
    }
    
    public final WorkflowStepVO getFirstStep() {
        return this.getRelatedObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP, ApplicationSchemaConstants.BIZCLASS_WORKFLOWSTEP, GlobalConstants.FLAG_TYPE_TO);
    }
    
    public final void reset() {
        StateInfo firstState = getFirstState();
        while(!firstState.getStateName().equals(getVo().getStates())) {
            demote();
        }
    }
    
    public final void resetStepList() {
        List<WorkflowStepVO> workflowStepVOList = getWorkflowStepVOList();
        for(WorkflowStepVO workflowStepVO : workflowStepVOList) {
            if(!WorkflowConstants.STATES_TYPE_DEFINE.equals(workflowStepVO.getStates())) {
                WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
                workflowStep.reset();
            }
        }
    }
    
    /**
     * 
     *
     * @param businessObjectRoot
     * @param attributeMap
     */
    public final void createRelationWorkflowObjectRoute(BusinessObjectRoot businessObjectRoot, Map<String, Object> attributeMap) {
        businessObjectRoot.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE, getVo(), attributeMap);
    }
    
    public final TreeMap<Integer, WorkflowStep> createWorkflowStep(BusinessObjectRoot businessObjectRoot, List<ApprovalVO> approvalVOListByState) {
        TreeMap<Integer, WorkflowStep> createdWorkflowStepTreeMap = new TreeMap<Integer, WorkflowStep>();
        Map<Integer, List<ApprovalVO>> approvalVOListByStep = new TreeMap<Integer, List<ApprovalVO>>();
        if(!NullUtil.isNone(approvalVOListByState)){
            for(ApprovalVO approvalVO: approvalVOListByState) {
                List<ApprovalVO> approvalList = approvalVOListByStep.get(approvalVO.getStep());
                if (NullUtil.isNone(approvalList)) {
                    approvalList = new ArrayList<ApprovalVO>();
                    approvalVOListByStep.put(approvalVO.getStep(), approvalList);
                }
                approvalList.add(approvalVO);
            }
        }
        
        for (Map.Entry<Integer, List<ApprovalVO>> entry : approvalVOListByStep.entrySet()) {
            if(NullUtil.isNull(entry.getKey()) ){
                for(ApprovalVO approvalVO : entry.getValue()) {
                    //LOGGER.debug("=============> State:"+approvalVO.getState() + "Step:" + approvalVO.getStep());
                }
                throw new ApplicationException("createWorkflowStepListNSubStepList Error");
            }
            
            //select * from PTSTEP
            StateInfo stateInfo = (StateInfo)businessObjectRoot.getVo().getOutData().get("stateInfo");
            WorkflowStepVO workflowStepVO = new WorkflowStepVO();
            workflowStepVO.setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WF_STEPNAME));
            workflowStepVO.setSequences(entry.getKey());
            workflowStepVO.setStates(WorkflowConstants.STATES_TYPE_DEFINE);
            workflowStepVO.setDescriptions(businessObjectRoot.getVo().getObid()+"_"+stateInfo.getStateName());
            workflowStepVO.setTitles("Step "+ entry.getKey());

            WorkflowStep workflowStep = DomUtil.toDom(workflowStepVO);
            workflowStep.createObject();

            createdWorkflowStepTreeMap.put(entry.getKey(), workflowStep);
        }
        
        WorkflowStep previousWorkflowStep = null;

        for (Map.Entry<Integer, WorkflowStep> entry : createdWorkflowStepTreeMap.entrySet()) {
            WorkflowStep workflowStep = createdWorkflowStepTreeMap.get(entry.getKey());
            if(!NullUtil.isNull(previousWorkflowStep)) {
                workflowStep.addFromObject( ApplicationSchemaConstants.RELCLASS_WORKFLOWSUBSTEP, previousWorkflowStep.getVo(), new HashMap<String, Object>());
            }
            previousWorkflowStep = workflowStep;
        }
        return createdWorkflowStepTreeMap;
    }
    
    public final void createRelationWorkflowRouteStep(WorkflowStep workflowStep, Map<String, Object> attributeMap) {
        this.addToObject(ApplicationSchemaConstants.RELCLASS_WORKFLOWROUTESTEP, workflowStep.getVo(), attributeMap);
    }
    
    /**
     * ���ӹ��� inboxtask ���
     *
     * @param originWorkflowInboxTaskVO
     * @return
     */
    public final List<WorkflowInboxTaskVO> getDelegatedWorkflowInboxtTaskList(WorkflowInboxTaskVO originWorkflowInboxTaskVO) {
        if(NullUtil.isNone(originWorkflowInboxTaskVO.getOriginTaskOwner())) return Collections.<WorkflowInboxTaskVO>emptyList();
        List<WorkflowInboxTaskVO> workflowInboxTaskVOList = getWorkflowInboxTaskVOList();
        if(NullUtil.isNone(workflowInboxTaskVOList)) return Collections.<WorkflowInboxTaskVO>emptyList();
        
        List<WorkflowInboxTaskVO> rtnWorkflowInboxTaskVOList = new ArrayList<WorkflowInboxTaskVO>();
        for(WorkflowInboxTaskVO workflowInboxTaskVO : workflowInboxTaskVOList) {
            if(NullUtil.isNone(workflowInboxTaskVO.getOriginTaskOwner())) continue;
            if(originWorkflowInboxTaskVO.getOriginTaskOwner().equals(workflowInboxTaskVO.getOriginTaskOwner())
               && originWorkflowInboxTaskVO.getStep().equals(workflowInboxTaskVO.getStep())
               && WorkflowConstants.STATES_TYPE_ASSIGNED.equals(workflowInboxTaskVO.getStates())
               && !workflowInboxTaskVO.getOriginTaskOwner().equals(workflowInboxTaskVO.getTaskOwner()) ) {
                rtnWorkflowInboxTaskVOList.add(workflowInboxTaskVO);
            }
        }
        return rtnWorkflowInboxTaskVOList;
    }
    
    public final boolean isAllApproved(){
        boolean bResult = true;
        List<WorkflowInboxTaskVO> workflowInboxTaskVOList = this.getWorkflowInboxTaskVOList();
        for(WorkflowInboxTaskVO workflowInboxTaskVO : workflowInboxTaskVOList) {
            if(!WorkflowConstants.STATES_TYPE_COMPLETE.equals(workflowInboxTaskVO.getStates()) &&
                    !WorkflowConstants.APPROVAL_STATUS_APPROVE.equals( workflowInboxTaskVO.getApprovalStatus())    ){
                bResult = false;
                break;
            }
        }
        return bResult;
    }
    
    public final BusinessObjectRoot getBusinessObjectRoot() {
        List<WorkflowObjectRouteVO> rtnWorkflowObjectRouteVOList = getRelationship(ApplicationSchemaConstants.RELCLASS_WORKFLOWOBJECTROUTE);
        
        if(NullUtil.isNone(rtnWorkflowObjectRouteVOList)) throw new ApplicationException("The business object does not exists.");
        
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(rtnWorkflowObjectRouteVOList.get(0).getFromObid(), false);
        return businessObjectRoot;
    }
}

