/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSActivityTemplate.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.util.StringUtil;

import rap.api.object.project.model.WBSActivityTemplateVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;


public class WBSActivityTemplate extends WBSItemTemplates {
    public WBSActivityTemplate(String obid){
        super(obid);
    }
    public WBSActivityTemplate(WBSActivityTemplateVO vo){
        super(vo);
    }
     @Override
    public WBSActivityTemplateVO getVo(){
        return (WBSActivityTemplateVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeWBSActivityTemplate();
    }
    public void initializeWBSActivityTemplate(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "WBSActivityTemplate[toString()=" + super.toString() + "]";
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
        if(StrUtil.isNotEmpty((String)map.get("activityMasterObid")) && !"null".equals((String)map.get("activityMasterObid"))){
            addToObject(ApplicationSchemaConstants.RELCLASS_RELATEDTEMPLATEMASTER, new ActivityTemplateMaster((String)map.get("activityMasterObid")).getVo(), null);
        }
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

    }

    @Override
    public void setDefault(boolean generateName){
        super.setDefault(generateName);
        getVo().setTitles(getVo().getActivityNameEng());
    }

    public WBSItemTemplatesVO getWBSPhase(String templateMasterObid){
        return getWBSPhase(templateMasterObid, true);
    }
    public WBSItemTemplatesVO getWBSPhase(String templateMasterObid, boolean throwException){
        
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", 
                GlobalConstants.OQL_OPERATOR_EQUAL, templateMasterObid );

        List<WBSItemTemplatesVO> relatedObjects = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, 
                ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, 
                GlobalConstants.FLAG_TYPE_FROM, selectPatternBuf.toString(),
                wherePatternBuf.toString(), paramPatternBuf.toString(), 
                false, true, 0, ProjectConstants.WBS_TEMPLATE_MAX_LEVEL);
        List<WBSItemTemplatesVO> wbsPhaseTemplateList = new ArrayList<WBSItemTemplatesVO>();
        for(WBSItemTemplatesVO wbsItemTemplatesVO : relatedObjects){
            if(ApplicationSchemaConstants.BIZCLASS_WBSPHASETEMPLATE.equals(wbsItemTemplatesVO.getClassName())
                    || ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTTEMPLATE.equals(wbsItemTemplatesVO.getClassName())){
                wbsPhaseTemplateList.add(wbsItemTemplatesVO);
            }
        }
        if(wbsPhaseTemplateList.isEmpty() || wbsPhaseTemplateList.size() > 1){
            if(throwException){
                throw new ApplicationException("WBS Phase Template error.");
            }else{
                return null;
            }
        }
        return wbsPhaseTemplateList.get(0);
    }
}

