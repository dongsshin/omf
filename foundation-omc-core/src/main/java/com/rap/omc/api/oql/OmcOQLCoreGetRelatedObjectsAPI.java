package com.rap.omc.api.oql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.oql.model.OmcGroupByParamVO;
import com.rap.omc.api.oql.model.OmcOQLApiRelatedLogVO;
import com.rap.omc.api.oql.model.OmcOQLCasheForQueryVO;
import com.rap.omc.api.oql.model.OmcOQLGetRelatedSqlPattern;
import com.rap.omc.api.oql.model.OmcOQLPatternClass;
import com.rap.omc.api.oql.model.OmcOQLPatternFrom;
import com.rap.omc.api.oql.model.OmcOQLPatternSQueryResult;
import com.rap.omc.api.oql.model.OmcOQLRelatedClassVO;
import com.rap.omc.api.oql.model.OmcOQLSelectClauseVO;
import com.rap.omc.api.oql.model.OmcOQLSelectForQuery;
import com.rap.omc.api.oql.model.OmcOQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.oql.utility.OmcUtility;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.CacheUtil;
import com.rap.omc.api.util.omc.HashUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcDBMSConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
/**
 * 
 * <pre>
 * Class : OmcOQLCoreGetRelatedObjectsAPI
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLCoreGetRelatedObjectsAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcOQLCoreGetRelatedObjectsAPI.class);
    public static final <T> List<T>_getRelatedObjects(List<ObjectRootVO> objVoList,
                                                      String   filterPattern      ,
                                                      String   relationPatternIn  , 
                                                      String   filterPatternIn    ,
                                                      String   fromToDirectionIn  , 
                                                      String   selectPatternIn    , 
                                                      String   wherePatternIn     , 
                                                      String   parameterPatternIn ,
                                                      List<OmcGroupByParamVO> groupByParamList,
                                                      int      objectLimit        ,
                                                      OmcOQLApiRelatedLogVO apiLogVOIn,
                                                      ArrayList<OmcOQLVariableParameter> variableParameterListIn,
                                                      boolean  isFirstCalled      ,
                                                      int      processLevel       ,
                                                      boolean  testMode           ,
                                                      PagingEntity pagingEntity   ,
                                                      RowBounds paramRowBounds    ){
        if(!NullUtil.isNone(groupByParamList)) selectPatternIn = OmcUtility.makeSelectStrForGroupByPattern(groupByParamList);
        return _getRelatedObjectsCore(objVoList,filterPattern,relationPatternIn,filterPatternIn,fromToDirectionIn,selectPatternIn,wherePatternIn,parameterPatternIn,groupByParamList,objectLimit,apiLogVOIn,variableParameterListIn,paramRowBounds,isFirstCalled,processLevel,testMode,pagingEntity);
    }
    @SuppressWarnings("unchecked")
    public static final <T> List<T>_getRelatedObjectsCore(List<ObjectRootVO> objVoList,
                                                      String   filterPattern      ,
                                                      String   relationPatternIn  , 
                                                      String   filterPatternIn    ,
                                                      String   fromToDirectionIn  , 
                                                      String   selectPatternIn    , 
                                                      String   wherePatternIn     , 
                                                      String   parameterPatternIn ,
                                                      List<OmcGroupByParamVO> groupByParamList,
                                                      int      objectLimit        ,
                                                      OmcOQLApiRelatedLogVO apiLogVOIn,
                                                      ArrayList<OmcOQLVariableParameter> variableParameterListIn,
                                                      RowBounds paramRowBounds    ,
                                                      boolean  isFirstCalled      ,
                                                      int      processLevel       ,
                                                      boolean  testMode           ,
                                                      PagingEntity pagingEntity){
        
        if(!StrUtil.in(fromToDirectionIn, GlobalConstants.FLAG_TYPE_FROM,GlobalConstants.FLAG_TYPE_TO,GlobalConstants.FLAG_TYPE_ALL))  {
            LOGGER.debug("[Foundation-OmcOQLCoreGetRelatedObjectsAPI][Critical Error]Direction(" + fromToDirectionIn + ") invalid");
            throw new FoundationException("[Foundation-OmcOQLCoreGetRelatedObjectsAPI][Critical Error]Direction(" + fromToDirectionIn + ") invalid");
        }
        if(objVoList.size() > OmcDBMSConstants.DBMS_IN_CLAUSE_MAX) throw new FoundationException("api.object.error.getrelated.object.maxsize",objVoList.size(),OmcSystemConstants.OBJECT_SET_CMD_UNIT);
        boolean isNew = true;boolean isAdvanced = false;
        if(!NullUtil.isNone(groupByParamList)) isAdvanced = true;

        ArrayList<OmcOQLVariableParameter> variableParameterList = new ArrayList<OmcOQLVariableParameter>();
        Map<String,String> attrMap        = new HashMap<String,String>();
        StringBuffer sqlTotalSelectStrBuf = new StringBuffer();
        StringBuffer whereClauseBuf   = new StringBuffer();
        String sqlSelectAddClause     = null;
        String sqlSelectAddClauseLast = null;
        String sqlSubQuerySelect      = null;
        String sqlSelectThisLast      = null;
        String sqlSelectRelLast       = null;
        String sqlOrderByClause       = null;
        
        Set<String> classVoListSet = new HashSet<String>();
        List<String> obidList = new ArrayList<String>();
        
        for(ObjectRootVO vo : objVoList){
            obidList.add(vo.getObid()); classVoListSet.add(vo.getClassName());
        }
        SortedSet<String> tempStrSet=new TreeSet<String>();
        for(String str : classVoListSet){
            tempStrSet.add(str);
        }
        String classListStr = StrUtil.convertList2Str(new ArrayList<String>(tempStrSet));
    
        String keyValue = OmcUtility.makeRelatedAPIKeyValue(relationPatternIn,fromToDirectionIn,filterPatternIn,selectPatternIn,
                                                            wherePatternIn,parameterPatternIn,classListStr,processLevel + 1,testMode);
        
        String hashedKey = HashUtil.makeHashKeyValue(keyValue);
        OmcOQLCasheForQueryVO casheForQueryVO = (OmcOQLCasheForQueryVO)CacheUtil.getCacheValue(OmcSystemConstants.OMC_API_CACHE,hashedKey);
        
        if(NullUtil.isNull(casheForQueryVO)){
            OmcOQLGetRelatedSqlPattern sqlPattern = OmcUtility.createSqlPatternForGetRelatd(classVoListSet    ,
                                                                                            obidList          ,
                                                                                            relationPatternIn ,
                                                                                            filterPatternIn   , 
                                                                                            fromToDirectionIn ,
                                                                                            selectPatternIn   ,
                                                                                            wherePatternIn    ,
                                                                                            parameterPatternIn,
                                                                                            false             ,
                                                                                            processLevel+1    ,
                                                                                            testMode          );    

            if(NullUtil.isNone(sqlPattern.getRelatedClassInfoList())) return OmcUtility.makeEmptyList(pagingEntity);
            
            /*************************************************************************Sub Query Start*************************************************/
            ArrayList<OmcOQLPatternSQueryResult> sQueryRsltList = new ArrayList<OmcOQLPatternSQueryResult>();
            ArrayList<OmcOQLPatternSQueryResult> sQueryRsltWList = new ArrayList<OmcOQLPatternSQueryResult>();
            
            String sqlSubQueryWhere  = "";
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
            /*************************************************************************Create Select For Query*************************************************/
            OmcOQLSelectForQuery relSelectForQuery  = OmcUtility.getSelectForQuery(sqlPattern.getPatternRelationshipStr(), OmcFoundationConstant.OQL_TARGET_REL_ALIAS_REL, processLevel, testMode);
            OmcOQLSelectForQuery thisSelectForQuery = OmcUtility.getSelectForQuery(sqlPattern.getPatternClassFilterStr(), OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this, processLevel, testMode);
            
            /*************************************************************************Create Attribute Map Start*************************************************/
            attrMap = relSelectForQuery.getAliasAttrColumn();
            Map<String,String> relAttrMap = thisSelectForQuery.getAliasAttrColumn();
            for(String keyStr : relAttrMap.keySet()){
                attrMap.put(keyStr, relAttrMap.get(keyStr));
            }

            HashMap<String, Object> fromMap = OmcUtility.getParsedOQLPatternFromInfo(sqlPattern.getPatternFrom(),processLevel+1,testMode);
            ArrayList<OmcOQLPatternFrom> fromList = (ArrayList<OmcOQLPatternFrom> )fromMap.get(OmcFoundationConstant.OQL_MAPPARM_PARSED_FROM);
            
            /*************************************************************************Create Where*************************************************/
            ArrayList<OmcOQLPatternClass> classList = OmcUtility.getFromClassSet(fromList, processLevel+1, testMode);
            String sqlWhereWhere = OmcUtility.createWhereClause(classList, sqlPattern.getPatternWhere(), sqlPattern.getPatternParameter(), "", false, processLevel + 1, testMode);
            
            /*************************************************************************Create Select*************************************************/
            if (!StrUtil.isEmpty(sqlPattern.getPatternSelect())){
                OmcOQLSelectClauseVO selectClauseVO = OmcUtility.createSelectClause(classList,sqlPattern.getPatternSelect(),false,true,processLevel,testMode);
                sqlSelectAddClauseLast = selectClauseVO.getLastSelectStr();
                sqlSelectAddClause     = selectClauseVO.getFirstSelectStr();
            }
            sqlOrderByClause  = OmcUtility.createOrderByClause(classList, sqlPattern.getPatternSortBy(), processLevel+1, testMode);
            
            /*************************************/
            /**    Cleansed Data Check ***********/
            /*************************************/
            String parsingDataCheck = "";
            if(!NullUtil.isNone(sqlWhereWhere)){
                parsingDataCheck = StrUtil.clearStringData(sqlWhereWhere);
                if(StrUtil.inStr(parsingDataCheck, OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator) > 0){
                    throw new FoundationException("api.object.error.getrelated.parameter.in.where",sqlWhereWhere,wherePatternIn);
                }
            }
            if(!NullUtil.isNone(sqlSelectAddClause)){
                parsingDataCheck = StrUtil.clearStringData(sqlSelectAddClause);
                if(StrUtil.inStr(parsingDataCheck, OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator) > 0){
                    throw new FoundationException("api.object.error.getrelated.parameter.in.select",sqlSelectAddClause,selectPatternIn);
                }    
            }
            if(!NullUtil.isNone(sqlOrderByClause)){
                parsingDataCheck = StrUtil.clearStringData(sqlOrderByClause);
                if(StrUtil.inStr(parsingDataCheck, OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator) > 0){
                    throw new FoundationException("api.object.error.getrelated.parameter.in.select",sqlOrderByClause,sqlOrderByClause);
                } 
            }

            boolean isFirst = true;
            //==================================================================================================================================
            for(OmcOQLRelatedClassVO relatedClassInfo : sqlPattern.getRelatedClassInfoList()){
                int    classCount = 0;
                for (int j = 0;j < thisSelectForQuery.getDbmsTableAndClassList().size(); j++){
                    if(thisSelectForQuery.getDbmsTableAndClassList().get(j).getDbmsTable().equals(relatedClassInfo.getTargetTableName())) classCount++;
                }
                String fromClause =  relatedClassInfo.getRelattionTableName() + " " + relSelectForQuery.getAlias() + "," + relatedClassInfo.getTargetTableName() + " " + thisSelectForQuery.getAlias();
                whereClauseBuf.setLength(0);
                
                String strFromTo = "";
                if(relatedClassInfo.isFrom()){
                    whereClauseBuf.append(OmcFoundationConstant.OQL_RELATED_JOIN_PARM_TO);
                    whereClauseBuf.append(OmcFoundationConstant.newline).append("and ").append(thisSelectForQuery.getAlias()).append(".pclass_name in(").append(StrUtil.makeWhereInStr(relatedClassInfo.getTargetClassSet())).append(")");
                    whereClauseBuf.append(OmcFoundationConstant.newline).append("and ").append(relSelectForQuery.getAlias()).append(".pclass_name in(").append(StrUtil.makeWhereInStr(relatedClassInfo.getRelattionSet())).append(")");
                    strFromTo = "From";
                }else{
                    whereClauseBuf.append(OmcFoundationConstant.OQL_RELATED_JOIN_PARM_FROM);
                    whereClauseBuf.append(OmcFoundationConstant.newline).append("and ").append(thisSelectForQuery.getAlias()).append(".pclass_name in(").append(StrUtil.makeWhereInStr(relatedClassInfo.getTargetClassSet())).append(")");
                    whereClauseBuf.append(OmcFoundationConstant.newline).append("and ").append(relSelectForQuery.getAlias()).append(".pclass_name in(").append(StrUtil.makeWhereInStr(relatedClassInfo.getRelattionSet())).append(")");
                    strFromTo = "To";
                }
                int relCount = 0;
                for (int j = 0;j < relSelectForQuery.getDbmsTableAndClassList().size(); j++){
                    if(relSelectForQuery.getDbmsTableAndClassList().get(j).getDbmsTable().equals(relatedClassInfo.getRelattionTableName())) relCount++;
                }
                StringBuffer sqlSubSelectStrBuf = new StringBuffer();
                if(isAdvanced){
                    if(!NullUtil.isNone(sqlSelectAddClause)) sqlSubSelectStrBuf.append("select ").append(sqlSelectAddClause);
                }else{
                    String sqlRelSelectTableStr = relSelectForQuery.getSQLSelectStrAll(relatedClassInfo.getRelattionTableName(), relCount, "Attribute", processLevel+1, testMode);
                    String sqlSelectTableStr = thisSelectForQuery.getSQLSelectStrAll(relatedClassInfo.getTargetTableName(), classCount, "DBMS Column", processLevel+1, testMode);
                    sqlSubSelectStrBuf.append("select '").append(strFromTo).append("' as ").append(OmcSystemConstants.OMC_API_dataFindingDirection);
                    sqlSubSelectStrBuf.append(OmcFoundationConstant.newline).append(",").append(sqlSelectTableStr);
                    sqlSubSelectStrBuf.append(OmcFoundationConstant.newline).append(",").append(sqlRelSelectTableStr);
                    if(!NullUtil.isNone(sqlSelectAddClause)) sqlSubSelectStrBuf.append(OmcFoundationConstant.newline).append(",").append(sqlSelectAddClause);
                }
                
                if(!NullUtil.isNone(fromClause)) sqlSubSelectStrBuf.append(OmcFoundationConstant.newline).append("from ").append(fromClause);
                if(!NullUtil.isNone(whereClauseBuf.toString())) sqlSubSelectStrBuf.append(OmcFoundationConstant.newline).append("where ").append(whereClauseBuf);
                
                if(!NullUtil.isNone(sqlWhereWhere)) sqlSubSelectStrBuf.append(OmcFoundationConstant.newline).append("and ").append(sqlWhereWhere);
                if(!NullUtil.isNone(sqlSubQueryWhere)) sqlSubSelectStrBuf.append(OmcFoundationConstant.newline).append("and ").append(sqlSubQueryWhere);
                
                if(!isFirst) sqlTotalSelectStrBuf.append(OmcFoundationConstant.newline).append("union all");
                sqlTotalSelectStrBuf.append(OmcFoundationConstant.newline).append(sqlSubSelectStrBuf);
                isFirst = false;
            }
            sqlTotalSelectStrBuf = new StringBuffer("select * from (").append(sqlTotalSelectStrBuf).append(") a");
            
            StringBuffer execSqlAddedAllSelectStrBuf = new StringBuffer();
            if(!isAdvanced) {
                sqlSelectThisLast = thisSelectForQuery.getSQLSelectLastAll(processLevel + 1, testMode);
                sqlSelectRelLast  = relSelectForQuery.getSQLSelectLastAll(processLevel + 1, testMode);
                sqlSelectThisLast = sqlSelectThisLast + OmcFoundationConstant.newline + ",\"" + OmcSystemConstants.OMC_API_dataFindingDirection + "\" as " + OmcSystemConstants.OMC_API_dataFindingDirection;
                execSqlAddedAllSelectStrBuf.append(sqlSelectThisLast);
                execSqlAddedAllSelectStrBuf.append(OmcFoundationConstant.newline).append(",").append(sqlSelectRelLast);
            }
            StringBuffer execSqlTotalSelectStrBuf = new StringBuffer();
            execSqlTotalSelectStrBuf = new StringBuffer(sqlTotalSelectStrBuf.toString().replace(OmcFoundationConstant.OQL_RELATED_JOIN_PARM_FROM, OmcFoundationConstant.OQL_RELATED_CLASS_FROM_STR));
            execSqlTotalSelectStrBuf = new StringBuffer(execSqlTotalSelectStrBuf.toString().replace(OmcFoundationConstant.OQL_RELATED_JOIN_PARM_TO, OmcFoundationConstant.OQL_RELATED_CLASS_TO_STR));
            if(!NullUtil.isNone(sqlSelectAddClauseLast)) {
                if(!StrUtil.isEmpty(execSqlAddedAllSelectStrBuf)) execSqlAddedAllSelectStrBuf.append(OmcFoundationConstant.newline).append(",");
                execSqlAddedAllSelectStrBuf.append(sqlSelectAddClauseLast);
                if(isAdvanced) execSqlAddedAllSelectStrBuf.append(",").append("count(1)").append(" ").append(OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY_COUNT);
            }else{
            }
            if(!NullUtil.isNone(sqlSubQuerySelect)) {
                if(!StrUtil.isEmpty(execSqlAddedAllSelectStrBuf)) execSqlAddedAllSelectStrBuf.append(OmcFoundationConstant.newline).append(",");
                execSqlAddedAllSelectStrBuf.append(sqlSubQuerySelect);
            }
            if(!isAdvanced){
                String selectBasicForThis = OmcUtility.createBasicDispalyedName(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this);
                String selectBasicForRel  = OmcUtility.createBasicDispalyedName(OmcFoundationConstant.OQL_TARGET_REL_ALIAS_REL);
                execSqlAddedAllSelectStrBuf.append(OmcFoundationConstant.newline).append(selectBasicForThis);
                execSqlAddedAllSelectStrBuf.append(OmcFoundationConstant.newline).append(selectBasicForRel);
            }
            variableParameterList = OmcUtility.convertVariableParameterList(sqlPattern.getPatternWhere(), sqlPattern.getPatternParameter(),processLevel+1,testMode);
            
            if(!NullUtil.isNone(variableParameterListSubQuery)) variableParameterList.addAll(variableParameterListSubQuery);
            casheForQueryVO = new OmcOQLCasheForQueryVO(execSqlTotalSelectStrBuf.toString(),execSqlAddedAllSelectStrBuf.toString(),sqlOrderByClause,attrMap,variableParameterList);
        }else
        {
            StringBuffer patternWhereBuf               = new StringBuffer();
            StringBuffer patternParameterBuf           = new StringBuffer();
            StringBuffer patternSubQueryWhere          = new StringBuffer();
            StringBuffer patternSubQueryWhereParameter = new StringBuffer();
            OmcUtility.seperateParameterAndWhere(wherePatternIn                   ,
                                                 parameterPatternIn               ,
                                                 patternWhereBuf                  ,
                                                 patternParameterBuf              ,
                                                 patternSubQueryWhere             ,
                                                 patternSubQueryWhereParameter    ,
                                                 processLevel+1                   ,
                                                 testMode                         );
            ArrayList<OmcOQLVariableParameter> tempVariableParameterList = OmcUtility.convertVariableParameterList(patternWhereBuf.toString(), patternParameterBuf.toString(),processLevel+1,testMode);
            if (!StrUtil.isEmpty(patternSubQueryWhere)){
                ArrayList<OmcOQLVariableParameter> sVariableParameterList = OmcUtility.convertVariableParameterList(patternSubQueryWhere.toString(), patternSubQueryWhereParameter.toString(),processLevel+1,testMode);
                tempVariableParameterList.addAll(sVariableParameterList);
            }
            variableParameterList = casheForQueryVO.getVariableParmList();
            if(tempVariableParameterList.size() != variableParameterList.size()){
                LOGGER.debug("[Foundation-OmcOQLCoreGetRelatedObjectsAPI]Critical Error Parameter is incorrect({},{})!.",tempVariableParameterList.size(),variableParameterList.size());
                throw new FoundationException("api.object.error.getrelated.parameter.count",tempVariableParameterList.size(),variableParameterList.size());
            };
            int idx = 0;
            for(OmcOQLVariableParameter parm : variableParameterList){
                parm.setParameterValue(tempVariableParameterList.get(idx++).getParameterValue());
            }
            casheForQueryVO.setVariableParmList(variableParameterList);
            isNew = false;
        }
        ArrayList<OmcOQLVariableParameter> newVariableParameterList = new ArrayList<OmcOQLVariableParameter>();
        newVariableParameterList.addAll(casheForQueryVO.getVariableParmList());
        newVariableParameterList.add(new OmcOQLVariableParameter(OmcFoundationConstant.OQL_OBID_VARIABLE,StrUtil.makeWhereInStr(obidList),true));

        List<T> result = OmcUtility._executeSQL(isAdvanced,keyValue,casheForQueryVO.getSqlStrInner(),casheForQueryVO.getSqlStrOuter(),casheForQueryVO.getSqlOrderByClause(),casheForQueryVO.getAttrMap(),objectLimit,pagingEntity,newVariableParameterList,paramRowBounds,false,processLevel+1,testMode);
        if(isNew) CacheUtil.putCache(OmcSystemConstants.OMC_API_CACHE,hashedKey,casheForQueryVO);
        return result;
    }
}
