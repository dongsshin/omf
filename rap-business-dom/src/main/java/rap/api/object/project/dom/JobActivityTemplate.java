/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : JobActivityTemplate.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.project.model.JobActivityTemplateVO;
import rap.api.object.project.model.WBSActivityTemplateVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSTemplateMasterVO;
import rap.api.object.relation.model.HasJobActivityTemplateVO;
import rap.application.constants.ApplicationSchemaConstants;


public class JobActivityTemplate extends BusinessObjectMaster {
    public JobActivityTemplate(String obid){
        super(obid);
    }
    public JobActivityTemplate(JobActivityTemplateVO vo){
        super(vo);
    }
     @Override
    public JobActivityTemplateVO getVo(){
        return (JobActivityTemplateVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeJobActivityTemplate();
    }
    public void initializeJobActivityTemplate(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "JobActivityTemplate[toString()=" + super.toString() + "]";
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
        BusinessObjectRoot object = new BusinessObjectRoot((String)map.get("activityTemplateObid"));
        if(!(object.isKindOf(ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE))){
            throw new ApplicationException("can not add Job Activity for " + object.getVo().getNames());
        }
    }

    @Override
    protected void preProcessForCreate(Map<String, Object> map){
        super.preProcessForCreate(map);
        /*code below*/
        this.resetAttributeValue();
    }
    private void resetAttributeValue(){
        this.getVo().setActivityNameChi(this.getVo().getActivityNameEng());
    }
    @Override
    protected void postProcessForCreate(Map<String, Object> map){
        super.postProcessForCreate(map);
        /*code below*/
        addFromObject(ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, new WBSActivityTemplate((String)map.get("activityTemplateObid")).getVo(), null);

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

    public void setDefault(boolean generateName){
        if(generateName){
            getVo().setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_JOB_ACTIVITY_TEMPLATE));
        }
        getVo().setTitles(getVo().getActivityNameEng());
        getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WITHOUT_STATE);
        getVo().setStates(ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS);
    }
    
    public final boolean isConnectedOtherContext(WBSTemplateMasterVO masterVO){
        return isConnectedOtherContext(masterVO.getObid());
    }
    public final boolean isConnectedOtherContext(String masterObid){
        
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY + "].toObid",GlobalConstants.OQL_OPERATOR_NOT_EQUAL, masterObid);
        List<WBSItemTemplatesVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, 
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        if(NullUtil.isNone(list)) return false;
        return true;
    }
    public WBSActivityTemplateVO getWBSActivityTemplate(String masterObid){
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY + "].toObid",GlobalConstants.OQL_OPERATOR_EQUAL, masterObid);
        List<WBSActivityTemplateVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, 
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        if(NullUtil.isNone(list)) return null;
        if(list.size() > 1) throw new FoundationException("WBSManagedBy is invalid.");
        return  list.get(0);
    }
    public final boolean isConnectedOtherActivity(String activityObid){
        
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[obid]",GlobalConstants.OQL_OPERATOR_NOT_EQUAL, activityObid);
        List<WBSItemTemplatesVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE, 
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        if(NullUtil.isNone(list)) return false;
        return true;
    }
    public HasJobActivityTemplateVO getHasJobActivityTemplate(String activityObid){
        
        StringBuffer selectPattern     = new StringBuffer();
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[fromObid]",GlobalConstants.OQL_OPERATOR_EQUAL, activityObid);
        List<HasJobActivityTemplateVO> list = this.getRelationship(ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE, 
                GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        if(NullUtil.isNone(list)) return null;
        if(list.size() > 1) throw new FoundationException("HasJobActivityTemplate is invalid.");
        return  list.get(0);
    }
}

