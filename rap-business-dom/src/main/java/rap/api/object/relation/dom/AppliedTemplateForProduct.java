/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AppliedTemplateForProduct.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.dom;


import java.util.Map;
import com.rap.omc.api.object.model.ObjectRootVO;
import rap.api.object.relation.model.AppliedTemplateForProductVO;
import com.rap.omc.api.object.dom.BusinessRelationObject;


public class AppliedTemplateForProduct extends BusinessRelationObject {
    public AppliedTemplateForProduct(String obid){
        super(obid);
    }
    public AppliedTemplateForProduct(AppliedTemplateForProductVO vo){
        super(vo);
    }
    @Override
    public AppliedTemplateForProductVO getVo(){
        return (AppliedTemplateForProductVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeAppliedTemplateForProduct();
    }
    public void initializeAppliedTemplateForProduct(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "AppliedTemplateForProduct[toString()=" + super.toString() + "]";
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

