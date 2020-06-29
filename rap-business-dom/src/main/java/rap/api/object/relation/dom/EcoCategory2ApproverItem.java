/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : EcoCategory2ApproverItem.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.dom;


import java.util.Map;
import com.rap.omc.api.object.model.ObjectRootVO;
import rap.api.object.relation.model.EcoCategory2ApproverItemVO;
import com.rap.omc.api.object.dom.BusinessRelationObject;


public class EcoCategory2ApproverItem extends BusinessRelationObject {
    public EcoCategory2ApproverItem(String obid){
        super(obid);
    }
    public EcoCategory2ApproverItem(EcoCategory2ApproverItemVO vo){
        super(vo);
    }
    @Override
    public EcoCategory2ApproverItemVO getVo(){
        return (EcoCategory2ApproverItemVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeEcoCategory2ApproverItem();
    }
    public void initializeEcoCategory2ApproverItem(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "EcoCategory2ApproverItem[toString()=" + super.toString() + "]";
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

