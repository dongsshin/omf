/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowRelationSample3.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.dom;


import java.util.Map;
import com.rap.omc.api.object.model.ObjectRootVO;
import rap.api.object.relation.model.WorkflowRelationSample3VO;
import com.rap.omc.api.object.dom.BusinessRelationObject;


public class WorkflowRelationSample3 extends BusinessRelationObject {
    public WorkflowRelationSample3(String obid){
        super(obid);
    }
    public WorkflowRelationSample3(WorkflowRelationSample3VO vo){
        super(vo);
    }
    @Override
    public WorkflowRelationSample3VO getVo(){
        return (WorkflowRelationSample3VO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeWorkflowRelationSample3();
    }
    public void initializeWorkflowRelationSample3(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "WorkflowRelationSample3[toString()=" + super.toString() + "]";
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
}

