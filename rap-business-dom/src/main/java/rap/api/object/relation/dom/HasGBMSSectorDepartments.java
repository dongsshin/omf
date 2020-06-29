/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasGBMSSectorDepartments.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.dom;


import java.util.Map;
import com.rap.omc.api.object.model.ObjectRootVO;
import rap.api.object.relation.model.HasGBMSSectorDepartmentsVO;
import com.rap.omc.api.object.dom.BusinessRelationObject;


public class HasGBMSSectorDepartments extends BusinessRelationObject {
    public HasGBMSSectorDepartments(String obid){
        super(obid);
    }
    public HasGBMSSectorDepartments(HasGBMSSectorDepartmentsVO vo){
        super(vo);
    }
    @Override
    public HasGBMSSectorDepartmentsVO getVo(){
        return (HasGBMSSectorDepartmentsVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeHasGBMSSectorDepartments();
    }
    public void initializeHasGBMSSectorDepartments(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "HasGBMSSectorDepartments[toString()=" + super.toString() + "]";
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

