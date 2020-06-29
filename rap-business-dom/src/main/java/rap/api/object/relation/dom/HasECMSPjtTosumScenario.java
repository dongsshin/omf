/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasECMSPjtTosumScenario.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.dom;


import java.util.Map;
import com.rap.omc.api.object.model.ObjectRootVO;
import rap.api.object.relation.model.HasECMSPjtTosumScenarioVO;
import com.rap.omc.api.object.dom.BusinessRelationObject;


public class HasECMSPjtTosumScenario extends BusinessRelationObject {
    public HasECMSPjtTosumScenario(String obid){
        super(obid);
    }
    public HasECMSPjtTosumScenario(HasECMSPjtTosumScenarioVO vo){
        super(vo);
    }
    @Override
    public HasECMSPjtTosumScenarioVO getVo(){
        return (HasECMSPjtTosumScenarioVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeHasECMSPjtTosumScenario();
    }
    public void initializeHasECMSPjtTosumScenario(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "HasECMSPjtTosumScenario[toString()=" + super.toString() + "]";
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

