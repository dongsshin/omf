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
import com.rap.omc.api.oql.model.OmcOQLPatternSplitedFrom;
import com.rap.omc.api.oql.model.OmcOQLSearchParsingResult;
import com.rap.omc.api.oql.model.OmcOQLSelectClauseVO;
import com.rap.omc.api.oql.model.OmcOQLSelectForQuery;
import com.rap.omc.api.oql.model.OmcOQLSqlPattern;
import com.rap.omc.api.oql.model.OmcOQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcComUtility;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.oql.utility.OmcUtility;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.CacheUtil;
import com.rap.omc.api.util.omc.HashUtil;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcDBMSConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.NullUtil;
/**
 * 
 * <pre>
 * Class : OmcOQLCoreSearchAPI
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLCoreSearchAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcOQLCoreSearchAPI.class);
    public static final <T> List<T>_searchObjects(String       className           , 
                                                  String       namePattern         , 
                                                  String       revisionPattern     ,
                                                  String       lifeCylePattern     , 
                                                  String       statePattern        , 
                                                  String       creatorPattern      , 
                                                  String       modifierPattern     ,
                                                  String       ownerPattern        , 
                                                  String       checkouterPattern   , 
                                                  String       lockerPattern       , 
                                                  boolean      firstOnly           , 
                                                  boolean      latestOnly          ,
                                                  boolean      lockedOnly          , 
                                                  boolean      checkoutedOnly      ,
                                                  String       patternSelectIn     , 
                                                  String       patternFromIn       , 
                                                  String       wherePatternIn      ,
                                                  String       parameterPatternIn  , 
                                                  List<OmcGroupByParamVO> groupByParamList,
                                                  boolean      expandType          , 
                                                  String       searchHint          , 
                                                  int          objectLimit         ,
                                                  RowBounds    paramRowBounds      ,
                                                  int          processLevel        ,
                                                  boolean      testMode            ){
        if(!NullUtil.isNone(groupByParamList)) patternSelectIn = OmcUtility.makeSelectStrForGroupByPattern(groupByParamList);
        return _searchObjectsCore(className,namePattern,revisionPattern,lifeCylePattern,statePattern,creatorPattern,modifierPattern,ownerPattern,checkouterPattern,lockerPattern,firstOnly,latestOnly,lockedOnly,checkoutedOnly,patternSelectIn,patternFromIn,wherePatternIn,parameterPatternIn,groupByParamList,expandType,searchHint,objectLimit,null,paramRowBounds,processLevel,testMode);
    }
    public static final <T> List<T>_searchObjectsPaging(String       className           , 
                                                        String       namePattern         , 
                                                        String       revisionPattern     ,
                                                        String       lifeCylePattern     , 
                                                        String       statePattern        , 
                                                        String       creatorPattern      , 
                                                        String       modifierPattern     ,
                                                        String       ownerPattern        , 
                                                        String       checkouterPattern   , 
                                                        String       lockerPattern       , 
                                                        boolean      firstOnly           , 
                                                        boolean      latestOnly          ,
                                                        boolean      lockedOnly          , 
                                                        boolean      checkoutedOnly      ,
                                                        String       patternSelectIn     , 
                                                        String       patternFromIn       , 
                                                        String       wherePatternIn      ,
                                                        String       parameterPatternIn  , 
                                                        boolean      expandType          , 
                                                        String       searchHint          , 
                                                        int          objectLimit         ,
                                                        PagingEntity pagingEntity        ,
                                                        RowBounds    paramRowBounds      ,
                                                        int          processLevel        ,
                                                        boolean      testMode            ){
        return _searchObjectsCore(className,namePattern,revisionPattern,lifeCylePattern,statePattern,creatorPattern,modifierPattern,ownerPattern,checkouterPattern,lockerPattern,firstOnly,latestOnly,lockedOnly,checkoutedOnly,patternSelectIn,patternFromIn,wherePatternIn,parameterPatternIn,null,expandType,searchHint,objectLimit,pagingEntity,paramRowBounds,processLevel,testMode);
    }
    public static final <T> List<T>_searchObjectsCore(  String       className           , 
                                                        String       namePattern         , 
                                                        String       revisionPattern     ,
                                                        String       lifeCylePattern     , 
                                                        String       statePattern        , 
                                                        String       creatorPattern      , 
                                                        String       modifierPattern     ,
                                                        String       ownerPattern        , 
                                                        String       checkouterPattern   , 
                                                        String       lockerPattern       , 
                                                        boolean      firstOnly           , 
                                                        boolean      latestOnly          ,
                                                        boolean      lockedOnly          , 
                                                        boolean      checkoutedOnly      ,
                                                        String       patternSelectIn     , 
                                                        String       patternFromIn       , 
                                                        String       wherePatternIn      ,
                                                        String       parameterPatternIn  ,
                                                        List<OmcGroupByParamVO> groupByParamList,
                                                        boolean      expandType          , 
                                                        String       searchHint          , 
                                                        int          objectLimit         ,
                                                        PagingEntity pagingEntity        ,
                                                        RowBounds    paramRowBounds      ,
                                                        int          processLevel        ,
                                                        boolean      testMode            ){
        if(StrUtil.isEmpty(patternSelectIn)) patternSelectIn = "@this.[*]";
        Map<String,String> attrMap      = new HashMap<String,String>();
        StringBuffer  sqlSelectAllCount = new StringBuffer();
        StringBuffer  sqlSelectAllSelect= new StringBuffer();
        if(StrUtil.isEmpty(searchHint)) searchHint = "";
        
        boolean isNew = true;boolean isAdvanced = false;
        if(!NullUtil.isNone(groupByParamList)) isAdvanced = true;
        
        ArrayList<OmcOQLVariableParameter> variableParameterList = new ArrayList<OmcOQLVariableParameter>();
        
        OmcOQLSqlPattern sqlPattern = OmcUtility.createSqlPatternForSearch(className,namePattern,revisionPattern,lifeCylePattern,statePattern,creatorPattern,modifierPattern,ownerPattern,checkouterPattern,lockerPattern,firstOnly,latestOnly,lockedOnly,checkoutedOnly,patternSelectIn,patternFromIn,wherePatternIn,parameterPatternIn,processLevel+1,testMode);

        String sqlSubQuerySelect = "";
        String sqlSubQueryWhere  = "";
        String keyValue = OmcUtility.makeSearchAPIKeyValue("searchObjects" + searchHint + className, sqlPattern.getPatternSelect() + sqlPattern.getPatternSubQuery() + OmcFoundationConstant.OQL_ORDERBY_OrderBy + sqlPattern.getPatternSortBy(), sqlPattern.getPatternFrom(), sqlPattern.getPatternWhereForKey(), sqlPattern.getPatternParameterForKey(), processLevel, testMode);
        String hashedKey = HashUtil.makeHashKeyValue(keyValue);
        OmcOQLCasheForQueryVO casheForQueryVO = (OmcOQLCasheForQueryVO)CacheUtil.getCacheValue(OmcSystemConstants.OMC_API_CACHE,hashedKey);
        if(NullUtil.isNull(casheForQueryVO)){
            List<String> classList = ClassInfoUtil.getInstantiableChildList(className);
            String classNameList   = StrUtil.convertList2Str(classList);
    
            OmcOQLSelectForQuery selectForQuery = OmcUtility.getSelectForQuery(classNameList, OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this, processLevel, testMode);
            
            attrMap = selectForQuery.getAliasAttrColumn();
            /*************************************************************************Sub Query Start  *************************************************/
            ArrayList<OmcOQLPatternSQueryResult> sQueryRsltList  = new ArrayList<OmcOQLPatternSQueryResult>();
            ArrayList<OmcOQLPatternSQueryResult> sQueryRsltWList = new ArrayList<OmcOQLPatternSQueryResult>();
            
            if (!StrUtil.isEmpty(sqlPattern.getPatternSubQuery())){
                sQueryRsltList    = OmcUtility.getOQLPatternSQueryRsltList(sqlPattern.getPatternSubQuery(), "select", null, processLevel+1, testMode);
                sqlSubQuerySelect = OmcUtility.createSubQuerySelectClause(sQueryRsltList,processLevel + 1, testMode);
            }
            
            ArrayList<OmcOQLVariableParameter> variableParameterListSubQuery = new ArrayList<OmcOQLVariableParameter>();
            if (!StrUtil.isEmpty(sqlPattern.getPatternSubQueryWhere())){
                sQueryRsltWList  = OmcUtility.getWhereSQueryRsltSet(sqlPattern.getPatternSubQueryWhere(), sqlPattern.getPatternSubQueryWhereParameter(), processLevel+1, testMode);
                sqlSubQueryWhere = OmcUtility.createSubQueryWhereClause(sQueryRsltWList,processLevel+1, testMode);
                variableParameterListSubQuery = OmcUtility.convertVariableParameterList(sqlPattern.getPatternSubQueryWhere(), sqlPattern.getPatternSubQueryWhereParameter(),processLevel+1,testMode);
            }
            /*************************************************************************Sub Query End  *************************************************/
            OmcOQLSearchParsingResult parsingResult = new OmcOQLSearchParsingResult();
            boolean isNothing = getSelectStatementForSearch(sqlPattern, selectForQuery, searchHint, parsingResult, attrMap, isAdvanced, processLevel,testMode);
            if(isNothing) {
                LOGGER.debug("[Foundation-OmcOQLCoreSearchAPI]Cannot resolving({0},{1})!.",sqlPattern,selectForQuery);
                return OmcUtility.makeEmptyList(pagingEntity);
            }
            OmcUtility.getFinalSelectStatementForSearch(sqlPattern,parsingResult,sqlSubQuerySelect,sqlSubQueryWhere ,sqlSelectAllCount,sqlSelectAllSelect,isAdvanced,processLevel,testMode);
            
            variableParameterList = OmcUtility.convertVariableParameterList(sqlPattern.getPatternWhere(), sqlPattern.getPatternParameter(), processLevel+1, testMode);
            if(!NullUtil.isNone(variableParameterListSubQuery)) variableParameterList.addAll(variableParameterListSubQuery);

            casheForQueryVO = new OmcOQLCasheForQueryVO(sqlSelectAllCount.toString(),sqlSelectAllSelect.toString(),parsingResult.getSqlOrderbyClause(),attrMap,variableParameterList);
        }else{
            ArrayList<OmcOQLVariableParameter> mainVariableParameterList     = OmcUtility.convertVariableParameterList(sqlPattern.getPatternWhere(), sqlPattern.getPatternParameter(), processLevel+1, testMode);
            if (!StrUtil.isEmpty(sqlPattern.getPatternSubQueryWhere())){
                ArrayList<OmcOQLVariableParameter> variableParameterListSubQuery = OmcUtility.convertVariableParameterList(sqlPattern.getPatternSubQueryWhere(), sqlPattern.getPatternSubQueryWhereParameter(),processLevel+1,testMode);
                mainVariableParameterList.addAll(variableParameterListSubQuery);
            }
            variableParameterList = casheForQueryVO.getVariableParmList();
            if(mainVariableParameterList.size() != variableParameterList.size()){
                LOGGER.debug("[Foundation-OmcOQLCoreFindObjectsAPI]Critical Error Parameter is incorrect({},{})!.",mainVariableParameterList.size(),variableParameterList.size());
                throw new FoundationException("[Foundation-OmcOQLCoreFindObjectsAPI]Critical Error Parameter is incorrect({},{})!.",mainVariableParameterList.size(),variableParameterList.size());
            };
            int idx = 0;
            for(OmcOQLVariableParameter parm : variableParameterList){
                parm.setParameterValue(mainVariableParameterList.get(idx++).getParameterValue());
            }
            casheForQueryVO.setVariableParmList(variableParameterList);
            isNew = false;
        }
        ArrayList<OmcOQLVariableParameter> newNariableParameterList = new ArrayList<OmcOQLVariableParameter>();
        newNariableParameterList.addAll(casheForQueryVO.getVariableParmList());

        List<T> result = OmcUtility._executeSQL(isAdvanced,keyValue,casheForQueryVO.getSqlStrInner(),casheForQueryVO.getSqlStrOuter(),casheForQueryVO.getSqlOrderByClause(),casheForQueryVO.getAttrMap(),objectLimit,searchHint, pagingEntity,newNariableParameterList,paramRowBounds,false,processLevel+1,testMode);
        if(isNew) CacheUtil.putCache(OmcSystemConstants.OMC_API_CACHE,hashedKey,casheForQueryVO);
        return(result);
    }
    
    @SuppressWarnings("unchecked")
    private static boolean getSelectStatementForSearch( OmcOQLSqlPattern     sqlPattern                 ,
                                                        OmcOQLSelectForQuery selectForQuery             ,
                                                        String               searchHint                 ,
                                                        OmcOQLSearchParsingResult parsingResult         ,
                                                        Map<String,String>   attrMap                    ,
                                                        final boolean        isAdvanced                 ,
                                                        int                  processLevel               ,
                                                        boolean              testMode                   ){

        StringBuffer         outBufWhereClause           = new StringBuffer();
        StringBuffer         outBufSqlSelectClause      = new StringBuffer();
        StringBuffer         outBufSqlSelectClauseLast  = new StringBuffer();
        StringBuffer         outBufSqlOrderbyClause     = new StringBuffer();
        StringBuffer         outBufSqlSelectThisLast    = new StringBuffer();
        List<String>         outThisSelectFirstSet      = new ArrayList<String>();
        List<String>         outFromClauseSet           = new ArrayList<String>();
        List<String>         outJoinClauseSet           = new ArrayList<String>();
        
        String   selectPatterns = sqlPattern.getPatternSelect()   ;
        String   fromPatterns   = sqlPattern.getPatternFrom()     ;
        String   wherePatterns  = sqlPattern.getPatternWhere()    ;
        String   parmStr        = sqlPattern.getPatternParameter();
        String   patternSortby  = sqlPattern.getPatternSortBy()   ;
    
        OmcUtility.checkSqlStatement(selectPatterns,fromPatterns,wherePatterns,processLevel+1,testMode);
        
        HashMap<String, Object> map = OmcUtility.getParsedOQLPatternFromInfo(fromPatterns,processLevel+1,testMode);
        
        ArrayList<OmcOQLPatternSplitedFrom> splitedFromList   = (ArrayList<OmcOQLPatternSplitedFrom>)map.get(OmcFoundationConstant.OQL_MAPPARM_PARSED_SPLITED_FROM);
        ArrayList<OmcOQLPatternFrom>        fromList          = (ArrayList<OmcOQLPatternFrom> )map.get(OmcFoundationConstant.OQL_MAPPARM_PARSED_FROM);
        ArrayList<String>                   queryPathList     = (ArrayList<String>)map.get(OmcFoundationConstant.OQL_MAPPARM_PARSED_QUERY_PATH);
        
        OmcUtility.makrAliasAttrColumnInfo(splitedFromList,attrMap,processLevel+1,testMode);
        
        if(NullUtil.isNone(queryPathList)) return true;
        
        ArrayList<OmcOQLPatternClass> classList = OmcUtility.getFromClassSet(fromList, processLevel+1, testMode);
        
        String temporaryWhere = OmcUtility.createWhereClause(classList, wherePatterns, parmStr, "", false, processLevel + 1, testMode);
        temporaryWhere = temporaryWhere.replace("@this.[nextObid]", "'1'");
        temporaryWhere = temporaryWhere.replace("@this.[previousObid]", "'1'");
        
        outBufWhereClause.append(temporaryWhere);
        
        outBufSqlSelectClauseLast.setLength(0);
        outBufSqlSelectClause.setLength(0);
    
        OmcOQLSelectClauseVO selectClauseVO = OmcUtility.createSelectClause(classList,selectPatterns,false,true,processLevel,testMode);
        outBufSqlSelectClauseLast.append(selectClauseVO.getLastSelectStr());
        outBufSqlSelectClause.append(selectClauseVO.getFirstSelectStr());
        
        String sqlOrderByClause  = OmcUtility.createOrderByClause(classList, patternSortby, processLevel+1, testMode);
        outBufSqlOrderbyClause.append(sqlOrderByClause);
        
        String parsingDataCheck = StrUtil.clearStringData(outBufWhereClause.toString());
        if(StrUtil.inStr(parsingDataCheck, OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator) > 0){
            OmcComUtility.error(new StringBuffer("[Foundation.omcAPI.getSelectStatementForSearch]Input SELECT Pattern(").append(outBufWhereClause).append(") is not mattacted with WHERE Pattern(").append(sqlPattern.getPatternWhere()).append(" ).").toString());
        }
        parsingDataCheck = StrUtil.clearStringData(outBufWhereClause.toString());
        if(StrUtil.inStr(parsingDataCheck, OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator) > 0){
            OmcComUtility.error(new StringBuffer("[Foundation.omcAPI.getSelectStatementForSearch]Input SELECT Pattern(").append(outBufSqlSelectClauseLast).append(") is not mattacted with SELECT Pattern(").append(sqlPattern.getPatternSelect()).append(" ).").toString());
        }
    
        String sqlSelectThisLast = selectForQuery.getSQLSelectLastAll(processLevel + 1, testMode);
        outBufSqlSelectThisLast.append(sqlSelectThisLast);
        
        int    classCount = 0;
        boolean isNothing = true;
        for(String queryPath : queryPathList){
            String sqlTable = OmcUtility.getMainDbmsTable(queryPath,splitedFromList,processLevel + 1, testMode);
            classCount = 0;
            for (int j = 0;j < selectForQuery.getDbmsTableAndClassList().size(); j++){
                if(selectForQuery.getDbmsTableAndClassList().get(j).getDbmsTable().equals(sqlTable)) classCount++;
            }
            StringBuffer sqlSelectTableBuf = new StringBuffer();
            if(!OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                if(!NullUtil.isNone(searchHint))  sqlSelectTableBuf.append(searchHint);
            }
            String sqlSelectTableStr = selectForQuery.getSQLSelectStrAll(sqlTable, classCount, "DBMS Column", processLevel+1, testMode);
            sqlSelectTableBuf.append(sqlSelectTableStr);                
            String sqlFromClause      = OmcUtility.getFromClauseForPatternFrom(queryPath, splitedFromList,processLevel+1,testMode);
            String sqlJoinWhereClause = OmcUtility.getJoinClauseForPatternFrom(queryPath, splitedFromList,processLevel+1,testMode);
            
            outThisSelectFirstSet.add(sqlSelectTableBuf.toString());
            outFromClauseSet.add(sqlFromClause);
            outJoinClauseSet.add(sqlJoinWhereClause);
            isNothing = false;
        }
        parsingResult.setWhereClause        (outBufWhereClause.toString());
        parsingResult.setSqlSelectClause    (outBufSqlSelectClause.toString());
        parsingResult.setSqlSelectClauseLast(outBufSqlSelectClauseLast.toString());
        parsingResult.setSqlOrderbyClause   (outBufSqlOrderbyClause.toString());
        parsingResult.setSqlSelectThisLast  (outBufSqlSelectThisLast.toString());
        parsingResult.setThisSelectFirstSet (outThisSelectFirstSet);
        parsingResult.setFromClauseSet      (outFromClauseSet);
        parsingResult.setJoinClauseSet      (outJoinClauseSet);
        
        return isNothing;
    }
}
