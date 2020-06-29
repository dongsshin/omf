package com.rap.omc.api.oql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcGroupByParamVO;
import com.rap.omc.api.oql.model.OmcOQLCasheForQueryVO;
import com.rap.omc.api.oql.model.OmcOQLPatternClass;
import com.rap.omc.api.oql.model.OmcOQLPatternFrom;
import com.rap.omc.api.oql.model.OmcOQLPatternSQueryResult;
import com.rap.omc.api.oql.model.OmcOQLSelectClauseVO;
import com.rap.omc.api.oql.model.OmcOQLSelectForQuery;
import com.rap.omc.api.oql.model.OmcOQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcComUtility;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.oql.utility.OmcUtility;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.CacheUtil;
import com.rap.omc.api.util.omc.HashUtil;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.NullUtil;
/**
 * 
 * <pre>
 * Class : OmcOQLCoreFindObjectsAPI
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLCoreFindObjectsAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcOQLCoreFindObjectsAPI.class);
    /**
     * 
     *
     * @param className
     * @param namePattern
     * @param revisionPattern
     * @param lifeCyclePattern
     * @param statePattern
     * @param creatorPattern
     * @param modifierPattern
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param patternSelectIn
     * @param patternWhereIn
     * @param patternParameterIn
     * @param groupByParamList
     * @param expandType
     * @param objectLimit
     * @param processLevel
     * @param testMode
     * @return
     */
    public static final <T> List<T> _findObjects(String    className            ,
                                                 String    namePattern          ,
                                                 String    revisionPattern      ,
                                                 String    lifeCyclePattern     ,
                                                 String    statePattern         ,
                                                 String    creatorPattern       ,
                                                 String    modifierPattern      ,
                                                 String    ownerPattern         ,
                                                 String    checkouterPattern    ,
                                                 String    lockerPattern        ,
                                                 boolean   firstOnly            ,
                                                 boolean   latestOnly           ,
                                                 boolean   lockedOnly           ,
                                                 boolean   checkoutedOnly       ,
                                                 String    patternSelectIn      ,
                                                 String    patternWhereIn       ,
                                                 String    patternParameterIn   ,
                                                 List<OmcGroupByParamVO> groupByParamList,
                                                 boolean   expandType           ,
                                                 RowBounds paramRowBounds       ,
                                                 int       objectLimit          ,
                                                 int       processLevel         ,
                                                 boolean   testMode             ){
        if(!NullUtil.isNone(groupByParamList)) patternSelectIn = OmcUtility.makeSelectStrForGroupByPattern(groupByParamList);
        return(_findObjectPagingListCore(className            ,
                                         namePattern          ,
                                         revisionPattern      ,
                                         lifeCyclePattern     ,
                                         statePattern         ,
                                         creatorPattern       ,
                                         modifierPattern      ,
                                         ownerPattern         ,
                                         checkouterPattern    ,
                                         lockerPattern        ,
                                         firstOnly            ,
                                         latestOnly           ,
                                         lockedOnly           ,
                                         checkoutedOnly       ,
                                         patternSelectIn      ,
                                         patternWhereIn       ,
                                         patternParameterIn   ,
                                         groupByParamList     ,
                                         expandType           ,
                                         null                 ,
                                         paramRowBounds       ,
                                         objectLimit          ,
                                         processLevel         ,
                                         testMode             )); 
    }
    /**
     * 
     *
     * @param className
     * @param namePattern
     * @param revisionPattern
     * @param lifeCyclePattern
     * @param statePattern
     * @param creatorPattern
     * @param modifierPattern
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param patternSelectIn
     * @param patternWhereIn
     * @param patternParameterIn
     * @param expandType
     * @param pagingEntity
     * @param objectLimit
     * @param processLevel
     * @param testMode
     * @return
     */
    public static final <T> List<T> _findObjectPagingList(String               className            ,
                                                          String               namePattern          ,
                                                          String               revisionPattern      ,
                                                          String               lifeCyclePattern     ,
                                                          String               statePattern         ,
                                                          String               creatorPattern       ,
                                                          String               modifierPattern      ,
                                                          String               ownerPattern         ,
                                                          String               checkouterPattern    ,
                                                          String               lockerPattern        ,
                                                          boolean              firstOnly            ,
                                                          boolean              latestOnly           ,
                                                          boolean              lockedOnly           ,
                                                          boolean              checkoutedOnly       ,
                                                          String               patternSelectIn      ,
                                                          String               patternWhereIn       ,
                                                          String               patternParameterIn   ,
                                                          boolean              expandType           ,
                                                          PagingEntity         pagingEntity         ,
                                                          RowBounds            paramRowBounds       ,
                                                          int                  objectLimit          ,
                                                          int                  processLevel         ,
                                                          boolean              testMode             )
    {
            return _findObjectPagingListCore(className,namePattern,revisionPattern,lifeCyclePattern,statePattern,creatorPattern,modifierPattern,ownerPattern,checkouterPattern,lockerPattern,firstOnly,latestOnly,lockedOnly,checkoutedOnly,patternSelectIn,patternWhereIn,patternParameterIn,null,expandType,pagingEntity,paramRowBounds,objectLimit,processLevel,testMode);
    }
    /**
     * 
     *
     * @param className
     * @param namePattern
     * @param revisionPattern
     * @param lifeCyclePattern
     * @param statePattern
     * @param creatorPattern
     * @param modifierPattern
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param patternSelectIn
     * @param patternWhereIn
     * @param patternParameterIn
     * @param groupByParamList
     * @param expandType
     * @param pagingEntity
     * @param objectLimit
     * @param processLevel
     * @param testMode
     * @return
     */
    private static final <T> List<T> _findObjectPagingListCore(String               className            ,
        			                                           String               namePattern          ,
        			                                           String               revisionPattern      ,
        			                                           String               lifeCyclePattern     ,
        			                                           String               statePattern         ,
        			                                           String               creatorPattern       ,
        			                                           String               modifierPattern      ,
        			                                           String               ownerPattern         ,
        			                                           String               checkouterPattern    ,
        			                                           String               lockerPattern        ,
        			                                           boolean              firstOnly            ,
        			                                           boolean              latestOnly           ,
        			                                           boolean              lockedOnly           ,
        			                                           boolean              checkoutedOnly       ,
        			                                           String               patternSelectIn      ,
        			                                           String               patternWhereIn       ,
        			                                           String               patternParameterIn   ,
        			                                           List<OmcGroupByParamVO> groupByParamList  ,
        			                                           boolean              expandType           ,
        			                                           PagingEntity         pagingEntity         ,
        			                                           RowBounds            paramRowBounds       ,
        			                                           int                  objectLimit          ,
        			                                           int                  processLevel         ,
        			                                           boolean              testMode             )
	{
	    StringBuffer patternSelect     = new StringBuffer();
	    StringBuffer patternFrom       = new StringBuffer();
		StringBuffer patternWhere      = new StringBuffer();
		StringBuffer patternParameter  = new StringBuffer();
		StringBuffer patternSubQuery   = new StringBuffer();
		StringBuffer patternSortBy     = new StringBuffer();
		Map<String,String> attrMap         = new HashMap<String,String>();
		
		boolean isNew = true;boolean isAdvanced = false;
		if(!NullUtil.isNone(groupByParamList)) isAdvanced = true;
		
		ArrayList<OmcOQLVariableParameter> variableParameterList = new ArrayList<OmcOQLVariableParameter>();
		
		ArrayList<OmcOQLPatternSQueryResult> sQueryRsltList = new ArrayList<OmcOQLPatternSQueryResult>();
		ArrayList<OmcOQLPatternSQueryResult> sQueryRsltWList = new ArrayList<OmcOQLPatternSQueryResult>();
		
		String sqlSubQuerySelect = null;
		String sqlSubQueryWhere  = null;
		String sqlOrderByClause  = null;

		if (patternSelectIn.equals   (OmcFoundationConstant.OQL_ALL))  patternSelectIn    = "";
		if (patternWhereIn.equals    (OmcFoundationConstant.OQL_ALL))  patternWhereIn     = "";
		if (patternParameterIn.equals(OmcFoundationConstant.OQL_ALL))  patternParameterIn = "";
		
		patternFrom.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT).append(OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT).append(className).append(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT);
		patternFrom.append(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator).append(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this).append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT);

	    HashMap<String,Object> seperatedPattern = OmcUtility.seperateSelectionPattern(patternSelectIn, processLevel, testMode);
	    if(!NullUtil.isNone(seperatedPattern)){
	        patternSelect.append((String)seperatedPattern.get(OmcFoundationConstant.OQL_MAPPARM_SELECT_STR));
	        patternSubQuery.append((String)seperatedPattern.get(OmcFoundationConstant.OQL_MAPPARM_SUBQUERY_STR));	         
	    }

		OmcUtility.getCleanedIputFromPattern(patternFrom.toString(), patternSelect.toString(), className, patternFrom,patternSelect,patternSortBy,OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this,processLevel, testMode);

		List<String> classList = ClassInfoUtil.getInstantiableChildList(className);
		String classNameList = StrUtil.convertList2Str(classList);

		OmcOQLSelectForQuery selectForQuery = OmcUtility.getSelectForQuery(classNameList, OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this, processLevel, testMode);
		
		if(NullUtil.isNone(selectForQuery.getDbmsTableList()) || selectForQuery.getDbmsTableList().size() == 0) return OmcUtility.makeEmptyList(pagingEntity);
		
		OmcUtility.createParmeterPattern(namePattern, revisionPattern, lifeCyclePattern, statePattern, 
		                                 creatorPattern, modifierPattern, ownerPattern, checkouterPattern, lockerPattern, 
		                                 firstOnly, latestOnly, lockedOnly, checkoutedOnly, 
		                                 OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this, 
		                                 patternWhereIn, patternParameterIn, 
		                                 patternWhere,patternParameter,processLevel, testMode);

		String keyValue = OmcUtility.makeFindObjectAPIKeyValue("findObjects" + className, patternSelect.toString() + patternSubQuery.toString() + OmcFoundationConstant.OQL_ORDERBY_OrderBy + patternSortBy, patternWhere.toString(), patternParameter.toString(), processLevel, testMode);
		String hashedKey = HashUtil.makeHashKeyValue(keyValue);
		OmcOQLCasheForQueryVO casheForQueryVO = (OmcOQLCasheForQueryVO)CacheUtil.getCacheValue(OmcSystemConstants.OMC_API_CACHE,hashedKey);
		
		if(NullUtil.isNull(casheForQueryVO)){
            StringBuffer patternWhereSubQuery      = new StringBuffer();
            StringBuffer patternParameterSubQuery  = new StringBuffer();
            OmcUtility.seperateParameterAndWhere(patternWhere.toString(), patternParameter.toString(), 
                                                 patternWhere           ,patternParameter            ,
                                                 patternWhereSubQuery   ,patternParameterSubQuery    ,
                                                 processLevel+1         ,testMode                    );
    
    		ArrayList<OmcOQLPatternFrom>  patternFromList  = OmcUtility.getOQLPatternFromList(patternFrom.toString(), processLevel, testMode);
    		ArrayList<OmcOQLPatternClass> patternClassList = OmcUtility.getFromClassSet(patternFromList, processLevel, testMode);
    		
    		String sqlWhereClause = OmcUtility.createWhereClause(patternClassList, patternWhere.toString(), patternParameter.toString(), "", false, processLevel + 1, testMode);
    		
    		sqlWhereClause = StrUtil.replace(sqlWhereClause, "@this.[nextObid]", "1");
    		sqlWhereClause = StrUtil.replace(sqlWhereClause, "@this.[previousObid]", "1");
    		
    		String parsingDataCheck = StrUtil.clearStringData(sqlWhereClause);
    		
    		if(StrUtil.inStr(parsingDataCheck, OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator) > 0){
    		    OmcComUtility.error("[Foundation.omcAPI.findObjects]Not Mfapped attribute(" + sqlWhereClause + ")  exists.");
    		}
    		if (!NullUtil.isNone(patternSubQuery.toString())){
    		    sQueryRsltList    = OmcUtility.getOQLPatternSQueryRsltList(patternSubQuery.toString(), "select", null, processLevel+1, testMode);
    		    sqlSubQuerySelect = OmcUtility.createSubQuerySelectClause(sQueryRsltList,processLevel + 1, testMode);
    		}
    		ArrayList<OmcOQLVariableParameter> variableParameterListSubQuery = new ArrayList<OmcOQLVariableParameter>();
    		if (!NullUtil.isNone(patternWhereSubQuery.toString())){
    		    sQueryRsltWList  = OmcUtility.getWhereSQueryRsltSet(patternWhereSubQuery.toString(), patternParameterSubQuery.toString(), processLevel+1, testMode);
    		    sqlSubQueryWhere = OmcUtility.createSubQueryWhereClause(sQueryRsltWList,processLevel+1, testMode);
    		    variableParameterListSubQuery = OmcUtility.convertVariableParameterList(patternWhereSubQuery.toString(), patternParameterSubQuery.toString(),processLevel+1,testMode);
    		}
    		
    		String sqlSelectClause     = "";
    		String sqlSelectClauseLast = "";
    
    		if (!NullUtil.isNone(patternSelect.toString())){
    		    OmcOQLSelectClauseVO selectClauseVO = OmcUtility.createSelectClause(patternClassList,patternSelect.toString(),false,true, processLevel,testMode);
    		    sqlSelectClauseLast = selectClauseVO.getLastSelectStr();
                sqlSelectClause     = selectClauseVO.getFirstSelectStr();
    		}
    		sqlOrderByClause  = OmcUtility.createOrderByClause(patternClassList, patternSortBy.toString(), processLevel+1, testMode);
    
    		String sqlSelectThisLast = selectForQuery.getSQLSelectLastAll(processLevel + 1, testMode);
    		attrMap = selectForQuery.getAliasAttrColumn();
    		
            StringBuffer  sqlSelectForCnt        = new StringBuffer();
            StringBuffer  sqlSelectTotal         = new StringBuffer();
    		for (int i =0; i < selectForQuery.getDbmsTableList().size(); i++){
    			String sqlTable   = selectForQuery.getDbmsTableList().get(i);
    			
    			int    classCount = 0;
    			
    			for (int j = 0;j < selectForQuery.getDbmsTableAndClassList().size(); j++){
    			    if(selectForQuery.getDbmsTableAndClassList().get(j).getDbmsTable().equals(sqlTable)) classCount++;
    			}
    			StringBuffer sqlClassListStr   = (new StringBuffer()).append(selectForQuery.getClassListStrForTable(sqlTable, processLevel+1, testMode));
    			StringBuffer sqlSelectTableStr = null;
    			if(isAdvanced){
    			    sqlSelectTableStr = (new StringBuffer("select ")).append(sqlSelectClause);
    			}else{
    			    sqlSelectTableStr = (new StringBuffer("select ")).append(selectForQuery.getSQLSelectStrAll(sqlTable, classCount, "DBMS Column", processLevel+1, testMode));
    			    if (!StrUtil.isEmpty(sqlSelectClause)) sqlSelectTableStr.append(",").append(sqlSelectClause);
    			}
    
    			if (!StrUtil.isEmpty(sqlTable))         sqlSelectTableStr.append(OmcFoundationConstant.newline).append("from ").append(sqlTable).append(" ").append(selectForQuery.getAlias());
    			if (!StrUtil.isEmpty(sqlClassListStr))  sqlSelectTableStr.append(OmcFoundationConstant.newline).append("where ").append(selectForQuery.getAlias()).append(".pclass_name in(").append(sqlClassListStr).append(")");
    			if (!StrUtil.isEmpty(sqlWhereClause))   sqlSelectTableStr.append(OmcFoundationConstant.newline).append("and ").append(sqlWhereClause);
    			if (!StrUtil.isEmpty(sqlSubQueryWhere)) sqlSelectTableStr.append(OmcFoundationConstant.newline).append("and ").append(sqlSubQueryWhere);
    			
    			if (!StrUtil.isEmpty(sqlSelectForCnt))  sqlSelectForCnt.append  (OmcFoundationConstant.newline).append("union all").append(OmcFoundationConstant.newline);
    			sqlSelectForCnt.append(sqlSelectTableStr.toString());
    		}
    		sqlSelectForCnt = (new StringBuffer()).append(sqlSelectForCnt).append("");
            if(isAdvanced){
                if (!StrUtil.isEmpty(sqlSelectClauseLast)) sqlSelectTotal.append(sqlSelectClauseLast);
                sqlSelectTotal.append(",").append("count(1)").append(" ").append(OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY_COUNT);
            }else{
                sqlSelectTotal = (new StringBuffer()).append(sqlSelectThisLast).append(OmcFoundationConstant.newline).append(OmcUtility.createBasicDispalyedName("this"));
                if (!StrUtil.isEmpty(sqlSelectClauseLast)) sqlSelectTotal.append(",").append(OmcFoundationConstant.newline).append(sqlSelectClauseLast);
                if (!StrUtil.isEmpty(sqlSubQuerySelect))   sqlSelectTotal.append(",").append(OmcFoundationConstant.newline).append(sqlSubQuerySelect);
            }
    		variableParameterList = OmcUtility.convertVariableParameterList(patternWhere.toString(), patternParameter.toString(), processLevel+1, testMode);
    		if(!NullUtil.isNone(variableParameterListSubQuery)) variableParameterList.addAll(variableParameterListSubQuery);
    		casheForQueryVO = new OmcOQLCasheForQueryVO(sqlSelectForCnt.toString(),sqlSelectTotal.toString(),sqlOrderByClause,attrMap,variableParameterList);
		}else{
            StringBuffer patternSubQueryWhere      = new StringBuffer();
            StringBuffer patternParameterSubQuery  = new StringBuffer();
            OmcUtility.seperateParameterAndWhere(patternWhere.toString(), patternParameter.toString(), 
                                                 patternWhere ,patternParameter            ,
                                                 patternSubQueryWhere   ,patternParameterSubQuery    ,
                                                 processLevel+1         ,testMode                    );
            ArrayList<OmcOQLVariableParameter> mainVariableParameterList = OmcUtility.convertVariableParameterList(patternWhere.toString(), patternParameter.toString(), processLevel+1, testMode);
            if (!StrUtil.isEmpty(patternSubQueryWhere)){
                ArrayList<OmcOQLVariableParameter> sVariableParameterList = OmcUtility.convertVariableParameterList(patternSubQueryWhere.toString(), patternParameterSubQuery.toString(),processLevel+1,testMode);
                mainVariableParameterList.addAll(sVariableParameterList);
            }
            variableParameterList = casheForQueryVO.getVariableParmList();
            if(mainVariableParameterList.size() != variableParameterList.size()){
                LOGGER.debug("[Foundation-OmcOQLCoreFindObjectsAPI]Critical Error Parameter is incorrect({},{})!.",mainVariableParameterList.size(),variableParameterList.size());
                throw new FoundationException("[Foundation-OmcOQLCoreSearchAPI]Critical Error Parameter is incorrect({},{})!.",mainVariableParameterList.size(),variableParameterList.size());
            };
            int idx = 0;
            for(OmcOQLVariableParameter parm : variableParameterList){
                parm.setParameterValue(mainVariableParameterList.get(idx++).getParameterValue());
            }
            casheForQueryVO.setVariableParmList(variableParameterList);
            isNew = false;
	    }
        ArrayList<OmcOQLVariableParameter> newVariableParameterList = new ArrayList<OmcOQLVariableParameter>();
        newVariableParameterList.addAll(casheForQueryVO.getVariableParmList());
		List<T> result = OmcUtility._executeSQL(isAdvanced,keyValue,casheForQueryVO.getSqlStrInner(),casheForQueryVO.getSqlStrOuter(),casheForQueryVO.getSqlOrderByClause(),casheForQueryVO.getAttrMap(),objectLimit,pagingEntity,newVariableParameterList,paramRowBounds,false,processLevel+1,testMode);
		if(isNew) CacheUtil.putCache(OmcSystemConstants.OMC_API_CACHE,hashedKey,casheForQueryVO);
		return(result);
    }
}
