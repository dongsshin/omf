/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : TemplateDependency.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.project.dom.WBSItemTemplates;
import rap.api.object.project.dom.WBSTemplateMaster;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.relation.model.TemplateDependencyVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;


public class TemplateDependency extends BusinessRelationObject {
    public TemplateDependency(String obid){
        super(obid);
    }
    public TemplateDependency(TemplateDependencyVO vo){
        super(vo);
    }
     @Override
    public TemplateDependencyVO getVo(){
        return (TemplateDependencyVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeTemplateDependency();
    }
    public void initializeTemplateDependency(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "TemplateDependency[toString()=" + super.toString() + "]";
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
        WBSTemplateMaster wbsTemplateMasterDom = (WBSTemplateMaster)map.get(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom);
        if(NullUtil.isNull(wbsTemplateMasterDom)) throw new FoundationException("Template Objedt is null.");
        updateWBSTemplateItmesDependency(wbsTemplateMasterDom,this.getFromObid());
    }

    @Override
    protected void validateForCreate(ObjectRootVO fromObject, ObjectRootVO toObject, Map<String,Object> map){
        super.validateForCreate(fromObject,toObject,map);
        /*code below*/
        WBSTemplateMaster wbsTemplateMasterDom = (WBSTemplateMaster)map.get(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom);
        if(NullUtil.isNull(wbsTemplateMasterDom)) throw new FoundationException("Template Objedt is null.");
        
        ObjectRoot fromObjectDom = new ObjectRoot(fromObject);
        
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        
        WBSItemTemplatesVO sourceObjectVO = (WBSItemTemplatesVO)fromObject;
        WBSItemTemplates sourceObjectDom = new WBSItemTemplates(sourceObjectVO);
        
        WBSItemTemplatesVO addedObjectVO = (WBSItemTemplatesVO)toObject;
        WBSItemTemplates addedObjectDom = new WBSItemTemplates(addedObjectVO);
       
        String userLocale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, ApplicationBizConstants.LANG_KO);
        String addedDisplayedTitles = ApplicationBizConstants.LANG_KO.equals(userLocale) ? addedObjectVO.getActivityNameKor() 
                : ( ApplicationBizConstants.LANG_CH.equals(userLocale) ? addedObjectVO.getActivityNameChi() 
                        : addedObjectVO.getActivityNameEng() );
        
        String sourceDisplayedTitles = ApplicationBizConstants.LANG_KO.equals(userLocale) ? sourceObjectVO.getActivityNameKor() 
                : ( ApplicationBizConstants.LANG_CH.equals(userLocale) ? sourceObjectVO.getActivityNameChi() 
                        : sourceObjectVO.getActivityNameEng() );
        
        if(!sourceObjectVO.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE)){
            throw new ApplicationException("Activity('" + sourceDisplayedTitles + "') cannot have Dependency!!!");
        }
        
        if(!sourceObjectDom.isLeaf()){
            throw new ApplicationException("Only Leaf Activity can have Dependecny!!! Activity('" + sourceDisplayedTitles + "') is not Leaf Actiity.");
        }
        
        
        if(!addedObjectVO.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSACTIVITYTEMPLATE)){
            throw new ApplicationException("Activity('" + addedDisplayedTitles + "') cannot be Previous Activity!!!");
        }
        if(!addedObjectDom.isLeaf()){
            throw new ApplicationException("Only Leaf Activity be Previous Activity!!! Selected Activity('" + addedDisplayedTitles + "') is not Leaf Actiity.");
        }
        if(fromObject.getObid().equals(toObject.getObid())) throw new ApplicationException("Previous Activity('" + addedDisplayedTitles + "') is same!!!");
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "From["+ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL,  wbsTemplateMasterDom.getObid());
        
        List<WBSItemTemplatesVO> fromTemplateDependencyList = fromObjectDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, 
                                                                              ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, 
                                                                              GlobalConstants.FLAG_TYPE_FROM, 
                                                                              selectPatternBuf.toString(), 
                                                                              wherePatternBuf.toString(), 
                                                                              paramPatternBuf.toString(), false, true, 0, 5);
        
        for(WBSItemTemplatesVO vo : fromTemplateDependencyList){
            if(vo.getObid().equals(toObject.getObid())){
                throw new ApplicationException("Infinite loop occur because '" + addedDisplayedTitles + "' is added!!!");
            }
        }
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
        WBSTemplateMaster wbsTemplateMasterDom = (WBSTemplateMaster)map.get(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom);
        if(NullUtil.isNull(wbsTemplateMasterDom)) throw new FoundationException("Template Objedt is null.");
        
        updateWBSTemplateItmesDependency(wbsTemplateMasterDom,this.getFromObid());
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
        WBSTemplateMaster wbsTemplateMasterDom = (WBSTemplateMaster)map.get(ProjectConstants.TEMPLATE_MAP_wbsTemplateMasterDom);
        if(NullUtil.isNull(wbsTemplateMasterDom)) throw new FoundationException("Template Objedt is null.");
        updateWBSTemplateItmesDependency(wbsTemplateMasterDom,getVo().getFromObid());
    }
    public static void updateWBSTemplateItmesDependency(WBSTemplateMaster wbsTemplateMasterDom, String obid){
        
        Boolean isWBSAttrRefreshOff = (Boolean)ThreadLocalUtil.get(ThreadLocalUtil.KEY.isWBSAttrRefreshOff);
        if(isWBSAttrRefreshOff == null) isWBSAttrRefreshOff = false;
        if(isWBSAttrRefreshOff) return;

        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        StringBuffer strBuf = new StringBuffer();
        WBSItemTemplates wbsItemTemplatesDom = new WBSItemTemplates(obid);
        if(NullUtil.isNull(wbsTemplateMasterDom)) throw new FoundationException("Context Object is null.");
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From[" + ApplicationSchemaConstants.RELCLASS_WBSMANAGEDBY + "].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, wbsTemplateMasterDom.getObid());
        List<WBSItemTemplatesVO > result = wbsItemTemplatesDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_TO, wherePattern.toString(), parameterPattern.toString(), 1);
        strBuf.setLength(0);
        if(NullUtil.isNone(result)){
            if(strBuf.length() == 0) strBuf.append("^~^").append("None");            
        }else{
            for(WBSItemTemplatesVO itemVO : result){
                //strBuf.append("^~^").append(itemVO.getActivityNameEng()).append("^+^").append(itemVO.getActivityNameKor()).append("^+^").append(itemVO.getActivityNameChi()).append("^+^").append(itemVO.getOutData().get("rel_dependencyType"));
                strBuf.append("^~^").append(itemVO.getActivityNameEng()).append("^+^").append(itemVO.getActivityNameKor()).append("^+^").append("-").append("^+^").append(itemVO.getOutData().get("rel_dependencyType"));
            }
        }
        wbsItemTemplatesDom.getVo().setPreviousActivityList(strBuf.substring(3));
        wbsItemTemplatesDom.modifyObject();
    }
}

