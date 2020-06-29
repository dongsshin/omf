/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasActivityTemplate.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.dom;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.project.dom.WBSTemplateMaster;
import rap.api.object.relation.model.HasActivityTemplateVO;
import rap.application.constants.ApplicationSchemaConstants;


public class HasActivityTemplate extends BusinessRelationObject {
    public HasActivityTemplate(String obid){
        super(obid);
    }
    public HasActivityTemplate(HasActivityTemplateVO vo){
        super(vo);
    }
     @Override
    public HasActivityTemplateVO getVo(){
        return (HasActivityTemplateVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeHasActivityTemplate();
    }
    public void initializeHasActivityTemplate(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "HasActivityTemplate[toString()=" + super.toString() + "]";
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
    public boolean checkChildDuplicationAsActivityMasterName(String parentObid, String activityMasterName){
        ObjectRoot parent = new ObjectRoot(parentObid);
        List<ObjectRootVO> result = new ArrayList<ObjectRootVO>();
        if(parent instanceof WBSTemplateMaster){
        }else{
        }
        
        return false;
    }
    private boolean isExistsSequence(String fromObid, Integer targetSequences){

        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
            
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fromObid);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[sequences]", GlobalConstants.OQL_OPERATOR_EQUAL, targetSequences.toString());
        
        StringBuffer sql = new StringBuffer();

        StringUtil.constructSelectPattern(sql,"SortBy@this.[sequences] desc");
        
        String selectPattern = sql.toString();
        HasActivityTemplateVO hasActivityTemplateVOVOList = findObject(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, 
                                                                      GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                                                                      selectPattern, wherePatternBuf.toString(), paramPatternBuf.toString(), false);
        return hasActivityTemplateVOVOList == null ? false : true;
    }
    
    public static Integer getLatestSequences(String fromObid){
        Integer result = 100;
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringBuffer selectPatternBuf = new StringBuffer();

        StringUtil.constructSelectPattern(selectPatternBuf,"SortBy@this.[sequences] desc");
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fromObid);
        
        List<HasActivityTemplateVO> hasActivityTemplateVOList = findObjects(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, 
                                                                      GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                                                                      selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(), false, 0);
        if(!hasActivityTemplateVOList.isEmpty()){
            result = hasActivityTemplateVOList.get(0).getSequences() + 100;
        }
        
        return result;
    }
    
    public static Integer getNextSequences(String fromObid, Integer targetSequences){
        Integer result = targetSequences + 100;
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
            
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fromObid);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[sequences]", GlobalConstants.OQL_OPERATOR_GREATER_THAN, targetSequences.toString());
        
        StringBuffer sql = new StringBuffer();

        StringUtil.constructSelectPattern(sql,"SortBy@this.[sequences] asc");
        
        String selectPattern = sql.toString();
        List<HasActivityTemplateVO> hasActivityTemplateVOList = findObjects(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, 
                                                                      GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                                                                      selectPattern, wherePatternBuf.toString(), paramPatternBuf.toString(), false, 0);
        
        if(!NullUtil.isNone(hasActivityTemplateVOList)){
            Integer nextSequences = hasActivityTemplateVOList.get(0).getSequences();
            result = (int)Math.ceil((nextSequences - targetSequences) / 2) + targetSequences;
        }
        
        if(result == targetSequences){
            resequencingActivityTemplate(fromObid, targetSequences);
        }
        
        return result;
    }
    
    public static Integer getPrevSequences(String fromObid, Integer targetSequences){
        Integer result = targetSequences - 100;
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fromObid);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[sequences]", GlobalConstants.OQL_OPERATOR_LESS_THAN, targetSequences.toString());
        
        StringBuffer sql = new StringBuffer();
        
        StringUtil.constructSelectPattern(sql,"SortBy@this.[sequences] desc");
        
        String selectPattern = sql.toString();
        List<HasActivityTemplateVO> hasActivityTemplateVOVOList = findObjects(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, 
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                selectPattern, wherePatternBuf.toString(), paramPatternBuf.toString(), false, 0);
        
        if(!hasActivityTemplateVOVOList.isEmpty()){
            Integer prevSequences = hasActivityTemplateVOVOList.get(0).getSequences();
            result = (int)Math.ceil((targetSequences - prevSequences ) / 2) + prevSequences;
        }
        
        if(result == targetSequences){
            resequencingActivityTemplate(fromObid, targetSequences);
        }
        
        return result;
    }
    
    public static void resequencingActivityTemplate(String fromObid, Integer targetSequences){
        
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fromObid);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[sequences]", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, targetSequences.toString());
        
        StringBuffer selectPatternBuf = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPatternBuf,"SortBy@this.[sequences]");
        
        List<HasActivityTemplateVO> hasActivityTemplateVOVOList = findObjects(ApplicationSchemaConstants.RELCLASS_HASACTIVITYTEMPLATE, 
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(), false, 0);
        
        HasActivityTemplate hasActivityTemplate = null;
        for(HasActivityTemplateVO hasActivityTemplateVO : hasActivityTemplateVOVOList){
            hasActivityTemplateVO.setSequences(hasActivityTemplateVO.getSequences() + 100);
            hasActivityTemplate = new HasActivityTemplate(hasActivityTemplateVO);
            hasActivityTemplate.modifyObject();
        }
    }
}

