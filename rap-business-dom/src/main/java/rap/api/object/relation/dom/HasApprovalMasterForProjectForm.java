/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasApprovalMasterForProjectForm.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.dom;


import java.util.Map;
import com.rap.omc.api.object.model.ObjectRootVO;
import rap.api.object.relation.model.HasApprovalMasterForProjectFormVO;
import com.rap.omc.api.object.dom.BusinessRelationObject;


public class HasApprovalMasterForProjectForm extends BusinessRelationObject {
    public HasApprovalMasterForProjectForm(String obid){
        super(obid);
    }
    public HasApprovalMasterForProjectForm(HasApprovalMasterForProjectFormVO vo){
        super(vo);
    }
    @Override
    public HasApprovalMasterForProjectFormVO getVo(){
        return (HasApprovalMasterForProjectFormVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeHasApprovalMasterForProjectForm();
    }
    public void initializeHasApprovalMasterForProjectForm(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "HasApprovalMasterForProjectForm[toString()=" + super.toString() + "]";
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

