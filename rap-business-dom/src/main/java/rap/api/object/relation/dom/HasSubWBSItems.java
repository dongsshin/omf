/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasSubWBSItems.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.StringUtil;

import rap.api.object.relation.model.HasSubWBSItemsVO;
import rap.application.constants.ApplicationSchemaConstants;


public class HasSubWBSItems extends BusinessRelationObject {
    public HasSubWBSItems(String obid){
        super(obid);
    }
    public HasSubWBSItems(HasSubWBSItemsVO vo){
        super(vo);
    }
     @Override
    public HasSubWBSItemsVO getVo(){
        return (HasSubWBSItemsVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeHasSubWBSItems();
    }
    public void initializeHasSubWBSItems(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "HasSubWBSItems[toString()=" + super.toString() + "]";
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
        Boolean isWBSAttrRefreshOff = (Boolean)ThreadLocalUtil.get(ThreadLocalUtil.KEY.isWBSAttrRefreshOff);
        if(isWBSAttrRefreshOff == null) isWBSAttrRefreshOff = false;
        if(isWBSAttrRefreshOff) return;
        if(isExistsSequence(fromObject.getObid(), getVo().getSequences())){
            getVo().setSequences(getLatestSequences(fromObject.getObid()));
        }
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
    private boolean isExistsSequence(String fromObid, Integer targetSequences){

        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
            
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fromObid);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[sequences]", GlobalConstants.OQL_OPERATOR_EQUAL, targetSequences.toString());
        
        StringBuffer sql = new StringBuffer();

        StringUtil.constructSelectPattern(sql,"SortBy@this.[sequences] desc");
        
        String selectPattern = sql.toString();
        HasSubWBSItemsVO hasSubWBSItemsVOList = findObject(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, 
                                                                      GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                                                                      selectPattern, wherePatternBuf.toString(), paramPatternBuf.toString(), false);
        return hasSubWBSItemsVOList == null ? false : true;
    }
    
    public Integer getLatestSequences(String fromObid){

        Integer result = 100;
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringBuffer selectPatternBuf = new StringBuffer();

        StringUtil.constructSelectPattern(selectPatternBuf,"SortBy@this.[sequences] desc");
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fromObid);
        
        List<HasSubWBSItemsVO> hasSubWBSItemsVOList = findObjects(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, 
                                                                      GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                                                                      selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(), false, 0);
        
        if(!hasSubWBSItemsVOList.isEmpty()){
            result = hasSubWBSItemsVOList.get(0).getSequences() + 100;
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
        List<HasSubWBSItemsVO> hasSubWBSItemsVOList = findObjects(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, 
                                                                      GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                                                                      selectPattern, wherePatternBuf.toString(), paramPatternBuf.toString(), false, 0);
        
        if(!hasSubWBSItemsVOList.isEmpty()){
            Integer nextSequences = hasSubWBSItemsVOList.get(0).getSequences();
            result = (int)Math.ceil((nextSequences - targetSequences) / 2) + targetSequences;
        }
        
        if(result == targetSequences){
            resequencingActivity(fromObid, targetSequences);
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
        List<HasSubWBSItemsVO> hasSubWBSItemsVOList = findObjects(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, 
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                selectPattern, wherePatternBuf.toString(), paramPatternBuf.toString(), false, 0);
        
        if(!hasSubWBSItemsVOList.isEmpty()){
            Integer prevSequences = hasSubWBSItemsVOList.get(0).getSequences();
            result = (int)Math.ceil((targetSequences - prevSequences ) / 2) + prevSequences;
        }
        
        if(result == targetSequences){
            resequencingActivity(fromObid, targetSequences);
        }
        
        return result;
    }
    
    public static void resequencingActivity(String fromObid, Integer targetSequences){
        
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fromObid);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[sequences]", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, targetSequences.toString());
        
        StringBuffer selectPatternBuf = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPatternBuf,"SortBy@this.[sequences]");
        
        List<HasSubWBSItemsVO> hasSubWBSItemsVOList = findObjects(ApplicationSchemaConstants.RELCLASS_HASSUBWBSITEMS, 
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, 
                selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(), false, 0);
        
        HasSubWBSItems hasSubWBSItems = null;
        for(HasSubWBSItemsVO hasSubWBSItemsVO : hasSubWBSItemsVOList){
            hasSubWBSItemsVO.setSequences(hasSubWBSItemsVO.getSequences() + 100);
            hasSubWBSItems = new HasSubWBSItems(hasSubWBSItemsVO);
            hasSubWBSItems.modifyObject();
        }
    }
}

