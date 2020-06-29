/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CodeMaster.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.dom;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.common.model.CodeMasterVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.CodeConstants;


public class CodeMaster extends BusinessObjectMaster {
    public CodeMaster(String obid){
        super(obid);
    }
    public CodeMaster(CodeMasterVO vo){
        super(vo);
    }
    @Override
    public CodeMasterVO getVo(){
        return (CodeMasterVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeCodeMaster();
    }
    public void initializeCodeMaster(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "CodeMaster[toString()=" + super.toString() + "]";
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
    public static final List<CodeMasterVO> getCodeMasterList(String namePattern, boolean isNameSort) {
        List<CodeMasterVO> result = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_CODEMASTER, namePattern, "", false);
        if(!NullUtil.isNone(result) && isNameSort) SortUtil.sort(result, "names",false);
        return result;
    }
    public static final List<CodeMasterVO> getCodeMasterList(String namePattern) {
        return getCodeMasterList(namePattern,false);
    }
   
    public static List<CodeDetailVO> getCodeList(String codeMasterNames){
    	CodeMasterVO codeMasterVO = findCodeMaster(codeMasterNames);
    	if(NullUtil.isNull(codeMasterVO)) return new ArrayList<CodeDetailVO>();
    	CodeMaster codeMasterDom = new CodeMaster(codeMasterVO);
    	return codeMasterDom.getCodeDetailList();
    }
    public static CodeMasterVO findCodeMaster(String codeMasterNames) {
        return BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_CODEMASTER, codeMasterNames);
    }
    public static List<CodeDetailVO> getCodeListByScope(String codeValue, String codeMasterNames, String codeScope) {
        List<CodeDetailVO> codeDetailList = new ArrayList<CodeDetailVO>();
        CodeMasterVO codeMasterVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_CODEMASTER, codeMasterNames);
    
        if(NullUtil.isNull(codeMasterVO)) return codeDetailList;
        if(StrUtil.isEmpty(codeValue))    return codeDetailList;
    
        StringBuffer fromPattern = new StringBuffer();
        fromPattern.append("<this>                   ThisConnectedWithTo  <[CodeMaster2Code  ]@M2D>+");
        fromPattern.append("<[CodeMaster2Code  ]@M2D>FromConnectedWithThis<[CodeMaster       ]@MASTER>+");
        fromPattern.append("<this>                   ThisConnectedWithFrom<[Code2Organization]@D2O>+");
        fromPattern.append("<[Code2Organization]@D2O>ToConnectedWithThis  <[" + codeMasterVO.getCodeMasterScope() +"]@ORG>+");
    
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@MASTER.[obid]",GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getObid());
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[names]",GlobalConstants.OQL_OPERATOR_EQUAL, codeScope);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[className]",GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getCodeMasterScope());
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",GlobalConstants.OQL_OPERATOR_IN, codeValue);
        
        return ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_CODEDETAIL, "SortBy@this.[sequences]", fromPattern.toString(),wherePatternBuf.toString(),paramPatternBuf.toString());
    }   

    public List<CodeDetailVO> getCodeListByScope(String codeValue, String codeScope){
       return getCodeListByScope(codeValue,this.getNames(),codeScope);
    }
    public List<CodeDetailVO> getCodeDetailList(){
        List<CodeDetailVO> codeDetailList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CODEMASTER2CODE, ApplicationSchemaConstants.BIZCLASS_CODEDETAIL, GlobalConstants.FLAG_TYPE_TO);
        return(codeDetailList);
    }

    public static final List<CodeDetailVO> getCodeDetailList(String codeMasterObid, String names, String usingOrganizationList, String states, String locale) {
    	CodeMaster codeMasterDom = new CodeMaster(codeMasterObid);
        Map<String,String> parmMap = new HashMap<String,String>();
    	parmMap.put(CodeConstants.CODE_DETAIL_PARM_names, names);
    	parmMap.put(CodeConstants.CODE_DETAIL_PARM_organizationList, usingOrganizationList);
    	parmMap.put(CodeConstants.CODE_DETAIL_PARM_states, states);
    	return getCodeDetailListSub(codeMasterDom.getVo(), parmMap, locale, false, false, null);
    }
    public static final List<CodeDetailVO> getCodeDetailWithNames(String codeMasterName, String codeNames){
    	Map<String,String> parmMap = new HashMap<String,String>();
    	parmMap.put(CodeConstants.CODE_DETAIL_PARM_names, codeNames);
    	return getCodeDetailList(codeMasterName,parmMap, GlobalConstants.LANG_KO, false, false, null);
    	
    }
    private static final List<CodeDetailVO> getCodeDetailListSub(CodeMasterVO codeMasterVO, Map<String,String> parmMap, String locale, boolean sort, boolean isPaging, PagingEntity pagingEntity){
    	CodeMaster codeMasterDom = new CodeMaster(codeMasterVO);
        String detaileNames = parmMap.get(CodeConstants.CODE_DETAIL_PARM_names);
        String states = parmMap.get(CodeConstants.CODE_DETAIL_PARM_states);
        String organizationList = parmMap.get(CodeConstants.CODE_DETAIL_PARM_organizationList);
        String attribute01 = parmMap.get(CodeConstants.CODE_DETAIL_PARM_attribute01);
        String attribute02 = parmMap.get(CodeConstants.CODE_DETAIL_PARM_attribute02);
        String attribute03 = parmMap.get(CodeConstants.CODE_DETAIL_PARM_attribute03);
        String titles = parmMap.get(CodeConstants.CODE_DETAIL_PARM_titles);
        
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        if(sort) StringUtil.addSortByPattern(selectPattern, "@this.[sequences]");
        
        if (!StrUtil.isEmpty(detaileNames)){
        	StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, detaileNames);
        }
        if (!StrUtil.isEmpty(states)){
        	StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, states);
        }
        if (!StrUtil.isEmpty(titles)){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "UPPER(@this.[titles])", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + titles + GlobalConstants.FLAG_TYPE_ALL);
        }
        if (!StrUtil.isEmpty(attribute01)){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[attribute01]", GlobalConstants.OQL_OPERATOR_IN, attribute01);
        }

        if (!StrUtil.isEmpty(attribute02)){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[attribute02]", GlobalConstants.OQL_OPERATOR_IN, attribute02);
        }

        if (!StrUtil.isEmpty(attribute03)){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[attribute03]", GlobalConstants.OQL_OPERATOR_EQUAL, attribute03);
        }
        if (!StrUtil.isEmpty(organizationList)){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@ORG.[usingOrganizationList]", GlobalConstants.OQL_OPERATOR_LIKE, organizationList);
        }
        List<CodeDetailVO> result = null;
        if(isPaging) {
        	result = codeMasterDom.getRelatedObjectsPaging(ApplicationSchemaConstants.RELCLASS_CODEMASTER2CODE, ApplicationSchemaConstants.BIZCLASS_CODEDETAIL, GlobalConstants.FLAG_TYPE_TO,
        			selectPattern.toString(), wherePattern.toString(), paramPattern.toString(), pagingEntity);
        }else {
        	result = codeMasterDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CODEMASTER2CODE, ApplicationSchemaConstants.BIZCLASS_CODEDETAIL, GlobalConstants.FLAG_TYPE_TO, 
        			selectPattern.toString(),wherePattern.toString(), paramPattern.toString(), 1);
        }
        setDisplayNameKr(result,locale);
        return result;
    }
    
    public static final List<CodeDetailVO> getCodeDetailList(String codeMasterName, Map<String,String> parmMap, String locale, boolean sort, boolean isPaging, PagingEntity pagingEntity){
    	CodeMasterVO codeMasterVO = CodeMaster.findCodeMaster(codeMasterName);
    	return getCodeDetailListSub(codeMasterVO,parmMap,locale,sort,isPaging,pagingEntity);
    }
    public static final CodeDetailVO getCodeDetail(String codeMasterObid, String names) {
        CodeMaster codeMaster  = new CodeMaster(codeMasterObid);
        Map<String,String> parmMap = new HashMap<String,String>();
    	parmMap.put(CodeConstants.CODE_DETAIL_PARM_names, names);
    	List<CodeDetailVO> result = getCodeDetailListSub(codeMaster.getVo(), parmMap, GlobalConstants.LANG_KO, false, false, null);
        return result == null || result.isEmpty() ? null : result.get(0);
    }
    public static final List<CodeMasterVO> getCodeMasterByAssignedAuthType(String authName) {
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringUtil.addSortByPattern(selectPatternBuf, "@this.[names]");
        if( !StrUtil.isEmpty(authName)){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "UPPER(@this.[authType])", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + authName.toUpperCase() + GlobalConstants.FLAG_TYPE_ALL);
        }
        return ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_CODEMASTER, selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString());
    }
    private static void setDisplayNameKr(List<CodeDetailVO> result, String locale) {
    	if(NullUtil.isNone(result)) return;
        if(result != null && result.size() > 0){
            for( int inx = 0; inx < result.size(); inx++ ){
                if( GlobalConstants.LANG_KO.equals(locale)){
                    if(!StrUtil.isEmpty(result.get(inx).getDisplayNameKr())) {
                        result.get(inx).setTitles(result.get(inx).getDisplayNameKr());
                    }
                }
            }
        }
    }
    
    public static boolean isFinalDR(String activityCode){
        if(activityCode.equals(getFinalDRActivity(activityCode))){
            return true;
        } else {
            return false;
        }
    }
    public static List<CodeDetailVO> getDRActivity(){
        Map<String,String> parmMap = new HashMap<String,String>();
    	parmMap.put(CodeConstants.CODE_DETAIL_PARM_attribute01, CodeConstants.CODE_DR_ACTIVITY_LIST);
    	return getCodeDetailList(CodeConstants.CODE_MASTER_NAME_SYSTEM_ACTIVITY, parmMap, GlobalConstants.LANG_KO, false, false, null);
    }
    
    public static String getFinalDRActivity(String activityCode){
        Map<String,String> parmMap = new HashMap<String,String>();
    	parmMap.put(CodeConstants.CODE_DETAIL_PARM_attribute01, CodeConstants.CODE_DR_ACTIVITY_FINAL_DR);
    	parmMap.put(CodeConstants.CODE_DETAIL_PARM_attribute02, activityCode);
    	List<CodeDetailVO> list = getCodeDetailList(CodeConstants.CODE_MASTER_NAME_SYSTEM_ACTIVITY, parmMap, GlobalConstants.LANG_KO, false, false, null);
        if( list != null && list.size() > 0 ){
            return list.get(0).getNames();
        }
        return activityCode;
    }
    
}

