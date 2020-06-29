/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowStepNodeUser.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.dom;


import java.util.Map;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.util.NullUtil;

import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.relation.model.WorkflowStepNodeUserVO;
import rap.api.object.workflow.dom.WorkflowStep;
import rap.api.object.workflow.model.WorkflowStepVO;

import com.rap.omc.api.object.dom.BusinessRelationObject;


public class WorkflowStepNodeUser extends BusinessRelationObject {
    public WorkflowStepNodeUser(String obid){
        super(obid);
    }
    public WorkflowStepNodeUser(WorkflowStepNodeUserVO vo){
        super(vo);
    }
    @Override
    public WorkflowStepNodeUserVO getVo(){
        return (WorkflowStepNodeUserVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeWorkflowStepNodeUser();
    }
    public void initializeWorkflowStepNodeUser(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "WorkflowStepNodeUser[toString()=" + super.toString() + "]";
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
    protected void validateForCreate(ObjectRootVO fromObject, ObjectRootVO toObject, Map<String,Object> map){
        super.validateForCreate(fromObject,toObject,map);
        /*code below*/

    }

   @Override
    protected void preProcessForCreate(ObjectRootVO fromObject, ObjectRootVO toObject, Map<String,Object> map){
        super.preProcessForCreate(fromObject,toObject,map);
        /*code below*/

    }

   @Override
    protected void postProcessForCreate(ObjectRootVO fromObject, ObjectRootVO toObject, Map<String,Object> map){
        super.postProcessForCreate(fromObject,toObject,map);
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
   public final UsersVO getUsersVO() {
       Users users = DomUtil.toDom(getVo().getToObid(), false);
       return users.getVo();
   }
   
   public final Users getUser() {
       Users users = DomUtil.toDom(getVo().getToObid(), false);
       return users;
   }
   
   
   
   public final WorkflowStepVO getWorkflowStepVO() {
       WorkflowStepVO rtnWorkflowStepVO = null;
       WorkflowStep workflowStep = DomUtil.toDom(getVo().getFromObid(), false);
       if(!NullUtil.isNull(workflowStep)) {
           rtnWorkflowStepVO = workflowStep.getVo();
       }
       return rtnWorkflowStepVO;
   }
}

