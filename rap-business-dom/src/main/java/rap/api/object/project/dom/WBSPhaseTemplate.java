/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSPhaseTemplate.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSPhaseTemplateVO;
import rap.api.object.relation.dom.TemplateDependency;
import rap.api.object.relation.dom.WBSManagedBy;
import rap.api.object.relation.model.WBSManagedByVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;


public class WBSPhaseTemplate extends WBSItemTemplates {
    public WBSPhaseTemplate(String obid){
        super(obid);
    }
    public WBSPhaseTemplate(WBSPhaseTemplateVO vo){
        super(vo);
    }
     @Override
    public WBSPhaseTemplateVO getVo(){
        return (WBSPhaseTemplateVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeWBSPhaseTemplate();
    }
    public void initializeWBSPhaseTemplate(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "WBSPhaseTemplate[toString()=" + super.toString() + "]";
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
        if(this.getStates().equals(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_WORKING)) throw new FoundationException("Cannot revise. current state is " + this.getStates());

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
        WBSTemplateMaster wbsTemplateMasterDom = (WBSTemplateMaster)map.get(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom);
        if(NullUtil.isNull(wbsTemplateMasterDom)) throw new FoundationException("Template Objedt is null.");
        WBSItemTemplatesVO revisedItemTemplatesVO = (WBSItemTemplatesVO)map.get(OmcApplicationConstants.MAP_API_REVISE_NEW_OBJECT);
        WBSItemTemplates revisedItemTemplatesDom = DomUtil.toDom(revisedItemTemplatesVO);

        StringBuffer selectPattern    = new StringBuffer();
        StringBuffer wherePattern    = new StringBuffer();
        StringBuffer parameterPattern    = new StringBuffer();
      
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, wbsTemplateMasterDom.getObid());
        
        List<WBSManagedByVO> managedByList = this.getRelationship(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO, 
                selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        for(WBSManagedByVO vo : managedByList){
            WBSManagedBy managedByDom = new WBSManagedBy(vo);
            managedByDom.deleteObject();
        }
        revisedItemTemplatesDom.addToObject(ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY, wbsTemplateMasterDom.getVo(), null);
        TemplateDependency.updateWBSTemplateItmesDependency(wbsTemplateMasterDom,revisedItemTemplatesVO.getObid());
    }
    public void setDefault(boolean generateName){
        if(generateName){
            getVo().setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WBS_ITEM_TEMPLATES));
        }
        if(StrUtil.isEmpty(getVo().getTitles())){
            getVo().setTitles(".");
        }
        getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WBS_TEMPLATE_ITEM);
        getVo().setStates(ApplicationSchemaConstants.STATE_WBS_TEMPLATE_ITEM_WORKING);
        getVo().setRevision("1");
        getVo().setClassName(ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE);
    }
}

