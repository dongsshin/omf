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
import com.rap.omc.api.oql.model.OmcOQLCasheForQueryVO;
import com.rap.omc.api.oql.model.OmcOQLGetRelatedSqlPattern;
import com.rap.omc.api.oql.model.OmcOQLPatternClass;
import com.rap.omc.api.oql.model.OmcOQLPatternFrom;
import com.rap.omc.api.oql.model.OmcOQLPatternSQueryResult;
import com.rap.omc.api.oql.model.OmcOQLRelatedClassVO;
import com.rap.omc.api.oql.model.OmcOQLSelectClauseVO;
import com.rap.omc.api.oql.model.OmcOQLSelectForQuery;
import com.rap.omc.api.oql.model.OmcOQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcComUtility;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.oql.utility.OmcUtility;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.CacheUtil;
import com.rap.omc.api.util.omc.HashUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
/**
 * 
 * <pre>
 * Class : OmcOQLCoreGetRelationShipAPI
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLCoreGetRelationShipAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcOQLCoreGetRelationShipAPI.class);
    public static final <T> List<T>_getRelattionShip(List<? extends ObjectRootVO> objVoList,
                                                     String   relationPatternIn  , 
                                                     String   fromToDirectionIn  , 
                                                     String   filterPatternIn    ,
                                                     String   selectPatternIn    , 
                                                     String   wherePatternIn     , 
                                                     String   parameterPatternIn ,
                                                     List<OmcGroupByParamVO> groupByParamList,
                                                     int      objectLimit        ,
                                                     PagingEntity pagingEntity   ,
                                                     RowBounds paramRowBounds    ,
                                                     int      processLevel       ,
                                                     boolean  testMode           ){
        if(!NullUtil.isNone(groupByParamList)) selectPatternIn = OmcUtility.makeSelectStrForGroupByPattern(groupByParamList);
        return _getRelattionShipCore(objVoList,relationPatternIn,fromToDirectionIn,filterPatternIn,selectPatternIn,wherePatternIn, parameterPatternIn,groupByParamList,objectLimit,pagingEntity,paramRowBounds,processLevel,testMode);
    }
    @SuppressWarnings("unchecked")
    private static final <T> List<T>_getRelattionShipCore(List<? extends ObjectRootVO> objVoList,
                                                          String   relationPatternIn  , 
                                                          String   fromToDirectionIn  , 
                                                          String   filterPatternIn    ,
                                                          String   selectPatternIn    , 
                                                          String   wherePatternIn     , 
                                                          String   parameterPatternIn ,
                                                          List<OmcGroupByParamVO> groupByParamList,
                                                          int      objectLimit        ,
                                                          PagingEntity pagingEntity   ,
                                                          RowBounds paramRowBounds    ,
                                                          int      processLevel       ,
                                                          boolean  testMode           ){
        
        if(!StrUtil.in(fromToDirectionIn, GlobalConstants.FLAG_TYPE_FROM,GlobalConstants.FLAG_TYPE_TO,GlobalConstants.FLAG_TYPE_ALL))  OmcComUtility.error("Direction(" + fromToDirectionIn + ") invalid");
        if(objVoList.size() > OmcSystemConstants.OBJECT_SET_CMD_UNIT && paramRowBounds == null) {
            LOGGER.debug("[Foundation-OmcOQLCoreGetRelationShipAPI]Critical Error To Many Object(Size:{}). Max size is {}.",objVoList.size(),OmcSystemConstants.OBJECT_SET_CMD_UNIT);
            throw new FoundationException("[Foundation-OmcOQLCoreGetRelationShipAPI]Critical Error To Many Object(Size:{}). Max size is {}.",objVoList.size(),OmcSystemConstants.OBJECT_SET_CMD_UNIT);
        }
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
        
        String keyValue = OmcUtility.makeRelationAPIKeyValue(relationPatternIn,fromToDirectionIn,filterPatternIn,selectPatternIn,
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
                                                                                            true              ,
                                                                                            processLevel+1    ,
                                                                                            testMode          );
                         
            if(NullUtil.isNone(sqlPattern.getRelatedClassInfoList())) return OmcUtility.makeEmptyList(pagingEntity);
                    
            ArrayList<OmcOQLPatternSQueryResult> sQueryRsltList = new ArrayList<OmcOQLPatternSQueryResult>();
            ArrayList<OmcOQLPatternSQueryResult> sQueryRsltWList = new ArrayList<OmcOQLPatternSQueryResult>();
            
            /*************************************************************************Sub Query Start*************************************************/
            String sqlSubQueryWhere  = null;
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
            OmcOQLSelectForQuery thisSelectForQuery = OmcUtility.getSelectForQuery(sqlPattern.getPatternRelationshipStr(), OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this, processLevel, testMode);
            attrMap = thisSelectForQuery.getAliasAttrColumn();
            
            HashMap<String, Object> fromMap = OmcUtility.getParsedOQLPatternFromInfo(sqlPattern.getPatternFrom(),processLevel+1,testMode);
            ArrayList<OmcOQLPatternFrom> fromList = (ArrayList<OmcOQLPatternFrom> )fromMap.get(OmcFoundationConstant.OQL_MAPPARM_PARSED_FROM);
            
            /*************************************************************************Create Where*************************************************/
            ArrayList<OmcOQLPatternClass> classList = OmcUtility.getFromClassSet(fromList, processLevel+1, testMode);
            String sqlWhereWhere = OmcUtility.createWhereClause(classList, sqlPattern.getPatternWhere(), sqlPattern.getPatternParameter(), "", false, processLevel + 1, testMode);
            
            
            /*************************************************************************Create Select*************************************************/
            if (!StrUtil.isEmpty(sqlPattern.getPatternSelect())){
                OmcOQLSelectClauseVO selectClauseVO = OmcUtility.createSelectClause(classList,sqlPattern.getPatternSelect(),false,true, processLevel,testMode);
                sqlSelectAddClauseLast = selectClauseVO.getLastSelectStr();
                sqlSelectAddClause     = selectClauseVO.getFirstSelectStr();
            }
            sqlOrderByClause  = OmcUtility.createOrderByClause(classList, sqlPattern.getPatternSortBy(), processLevel+1, testMode);
    
            
    
            boolean isFirst = true;
            //==================================================================================================================================
            for(OmcOQLRelatedClassVO relatedClassInfo : sqlPattern.getRelatedClassInfoList()){
                int    classCount = 0;
                for (int j = 0;j < thisSelectForQuery.getDbmsTableAndClassList().size(); j++){
                    if(thisSelectForQuery.getDbmsTableAndClassList().get(j).getDbmsTable().equals(relatedClassInfo.getRelattionTableName())) classCount++;
                }
                String fromClause =  relatedClassInfo.getRelattionTableName() + " " + thisSelectForQuery.getAlias() ;
                whereClauseBuf.setLength(0);
                
                String strFromTo = "";
                if(relatedClassInfo.isFrom()){
                    whereClauseBuf.append(OmcFoundationConstant.OQL_RELATED_JOIN_PARM_TO);
                    whereClauseBuf.append(OmcFoundationConstant.newline).append("and ").append(thisSelectForQuery.getAlias()).append(".pclass_name in(").append(StrUtil.makeWhereInStr(relatedClassInfo.getRelattionSet())).append(")");
                    whereClauseBuf.append(OmcFoundationConstant.newline).append("and ").append(thisSelectForQuery.getAlias()).append(".pfrom_class in(").append(StrUtil.makeWhereInStr(relatedClassInfo.getTargetClassSet())).append(")");
                    strFromTo = OmcFoundationConstant.OQL_DIRECTION_From;
                }else{
                    whereClauseBuf.append(OmcFoundationConstant.OQL_RELATED_JOIN_PARM_FROM);
                    whereClauseBuf.append(OmcFoundationConstant.newline).append("and ").append(thisSelectForQuery.getAlias()).append(".pclass_name in(").append(StrUtil.makeWhereInStr(relatedClassInfo.getRelattionSet())).append(")");
                    whereClauseBuf.append(OmcFoundationConstant.newline).append("and ").append(thisSelectForQuery.getAlias()).append(".pto_class   in(").append(StrUtil.makeWhereInStr(relatedClassInfo.getTargetClassSet())).append(")");
                    strFromTo = OmcFoundationConstant.OQL_DIRECTION_To;
                }
                StringBuffer sqlSubSelectStrBuf = new StringBuffer();
                if(isAdvanced){
                    if(!NullUtil.isNone(sqlSelectAddClause)) sqlSubSelectStrBuf.append("select ").append(sqlSelectAddClause);
                }else{
                    String sqlSelectTableStr = thisSelectForQuery.getSQLSelectStrAll(relatedClassInfo.getRelattionTableName(), classCount, "DBMS Column", processLevel+1, testMode);
                    
                    sqlSubSelectStrBuf.append("select '").append(strFromTo).append("' as ").append(OmcSystemConstants.OMC_API_dataFindingDirection);
                    sqlSubSelectStrBuf.append(OmcFoundationConstant.newline).append(",").append(sqlSelectTableStr);
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
            if(!isAdvanced){
                sqlSelectThisLast = thisSelectForQuery.getSQLSelectLastAll(processLevel + 1, testMode);
                sqlSelectThisLast = sqlSelectThisLast + OmcFoundationConstant.newline + ",\"" + OmcSystemConstants.OMC_API_dataFindingDirection + "\" as " + OmcSystemConstants.OMC_API_dataFindingDirection;
            }
            
            StringBuffer execSqlTotalSelectStrBuf = new StringBuffer();
            execSqlTotalSelectStrBuf = new StringBuffer(sqlTotalSelectStrBuf.toString().replace(OmcFoundationConstant.OQL_RELATED_JOIN_PARM_FROM, OmcFoundationConstant.OQL_RELATIONSHIP_CLASS_FROM_STR));
            execSqlTotalSelectStrBuf = new StringBuffer(execSqlTotalSelectStrBuf.toString().replace(OmcFoundationConstant.OQL_RELATED_JOIN_PARM_TO, OmcFoundationConstant.OQL_RELATIONSHIP_CLASS_TO_STR));

            StringBuffer execSqlAddedAllSelectStrBuf = new StringBuffer();
            if(isAdvanced){
                execSqlAddedAllSelectStrBuf.append(sqlSelectAddClauseLast);
                execSqlAddedAllSelectStrBuf.append(",").append("count(1)").append(" ").append(OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY_COUNT);
            }else{
                String selectBasicForThis = OmcUtility.createBasicDispalyedName(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this);
                execSqlAddedAllSelectStrBuf.append(sqlSelectThisLast);
                if(!NullUtil.isNone(sqlSelectAddClauseLast)) execSqlAddedAllSelectStrBuf.append(OmcFoundationConstant.newline).append(",").append(sqlSelectAddClauseLast);
                if(!NullUtil.isNone(sqlSubQuerySelect)) execSqlAddedAllSelectStrBuf.append(OmcFoundationConstant.newline).append(",").append(sqlSubQuerySelect);
                execSqlAddedAllSelectStrBuf.append(OmcFoundationConstant.newline).append(selectBasicForThis);
            }
            
            variableParameterList = OmcUtility.convertVariableParameterList(wherePatternIn, sqlPattern.getPatternParameter(),processLevel+1,testMode);
            if(!NullUtil.isNone(variableParameterListSubQuery)) variableParameterList.addAll(variableParameterListSubQuery);
            casheForQueryVO = new OmcOQLCasheForQueryVO(execSqlTotalSelectStrBuf.toString(),execSqlAddedAllSelectStrBuf.toString(),sqlOrderByClause,attrMap,variableParameterList);
        }
        else
        {
            OmcOQLGetRelatedSqlPattern sqlPattern = OmcUtility.createSqlPatternForGetRelatd(classVoListSet    ,
                                                                                            obidList          ,
                                                                                            relationPatternIn ,
                                                                                            filterPatternIn   , 
                                                                                            fromToDirectionIn ,
                                                                                            selectPatternIn   ,
                                                                                            wherePatternIn    ,
                                                                                            parameterPatternIn,
                                                                                            true              ,
                                                                                            processLevel+1    ,
                                                                                            testMode          );
            
            ArrayList<OmcOQLVariableParameter> tempVariableParameterList = OmcUtility.convertVariableParameterList(wherePatternIn, sqlPattern.getPatternParameter(),processLevel+1,testMode);
            if (!StrUtil.isEmpty(sqlPattern.getPatternSubQueryWhere())){
                ArrayList<OmcOQLVariableParameter> sVariableParameterList = OmcUtility.convertVariableParameterList(sqlPattern.getPatternSubQueryWhere(), sqlPattern.getPatternSubQueryWhereParameter(),processLevel+1,testMode);
                tempVariableParameterList.addAll(sVariableParameterList);
            }
            variableParameterList = casheForQueryVO.getVariableParmList();
            if(tempVariableParameterList.size() != variableParameterList.size()){
                LOGGER.debug("[Foundation-OmcOQLCoreGetRelationShipAPI]Critical Error Parameter is incorrect({},{})!.",tempVariableParameterList.size(),variableParameterList.size());
                throw new FoundationException("[Foundation-OmcOQLCoreGetRelationShipAPI]Critical Error Parameter is incorrect({},{})!.",tempVariableParameterList.size(),variableParameterList.size());
            };
            int idx = 0;
            for(OmcOQLVariableParameter parm : variableParameterList){
                parm.setParameterValue(tempVariableParameterList.get(idx++).getParameterValue());
            }
            casheForQueryVO.setVariableParmList(variableParameterList);
            isNew = false;
        }
        ArrayList<OmcOQLVariableParameter> newNariableParameterList = new ArrayList<OmcOQLVariableParameter>();
        newNariableParameterList.addAll(casheForQueryVO.getVariableParmList());
        newNariableParameterList.add(new OmcOQLVariableParameter(OmcFoundationConstant.OQL_OBID_VARIABLE,StrUtil.makeWhereInStr(obidList),true));
        
        List<T> result = OmcUtility._executeSQL(isAdvanced,keyValue,casheForQueryVO.getSqlStrInner(),casheForQueryVO.getSqlStrOuter(),casheForQueryVO.getSqlOrderByClause(),casheForQueryVO.getAttrMap(),objectLimit,pagingEntity,newNariableParameterList,paramRowBounds,false,processLevel+1,testMode);
        if(isNew) CacheUtil.putCache(OmcSystemConstants.OMC_API_CACHE,hashedKey,casheForQueryVO);
        return result;
    }
}
