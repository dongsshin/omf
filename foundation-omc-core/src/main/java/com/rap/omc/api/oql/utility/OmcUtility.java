package com.rap.omc.api.oql.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcAPIFindAndSearchObjectsLog;
import com.rap.omc.api.oql.model.OmcForDynamicLoop;
import com.rap.omc.api.oql.model.OmcGroupByParamVO;
import com.rap.omc.api.oql.model.OmcOQLApiRelatedLogVO;
import com.rap.omc.api.oql.model.OmcOQLAttribute;
import com.rap.omc.api.oql.model.OmcOQLConvertedParmValue;
import com.rap.omc.api.oql.model.OmcOQLGetRelatedSqlPattern;
import com.rap.omc.api.oql.model.OmcOQLParsedWhereSQuery;
import com.rap.omc.api.oql.model.OmcOQLPatternAlias;
import com.rap.omc.api.oql.model.OmcOQLPatternClass;
import com.rap.omc.api.oql.model.OmcOQLPatternFrom;
import com.rap.omc.api.oql.model.OmcOQLPatternSQueryCondition;
import com.rap.omc.api.oql.model.OmcOQLPatternSQueryResult;
import com.rap.omc.api.oql.model.OmcOQLPatternSQueryTable;
import com.rap.omc.api.oql.model.OmcOQLPatternSelection;
import com.rap.omc.api.oql.model.OmcOQLPatternSplitedFrom;
import com.rap.omc.api.oql.model.OmcOQLRelatedClassTempVO;
import com.rap.omc.api.oql.model.OmcOQLRelatedClassVO;
import com.rap.omc.api.oql.model.OmcOQLSearchParsingResult;
import com.rap.omc.api.oql.model.OmcOQLSelectClauseVO;
import com.rap.omc.api.oql.model.OmcOQLSelectForQuery;
import com.rap.omc.api.oql.model.OmcOQLSqlPattern;
import com.rap.omc.api.oql.model.OmcOQLSubQueryJoin;
import com.rap.omc.api.oql.model.OmcOQLVariableParameter;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.model.schema.OmcOQLClassAttribute;
import com.rap.omc.api.oql.utility.OmcSortUtil.OmcOQLComparator;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.EtcUtil;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.foundation.classes.model.AllowedClassInfo;
import com.rap.omc.foundation.classes.model.ClassDbmsTableVO;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ColumnInfo;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcDBMSConstants;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
/**
 * 
 * <pre>
 * Class : OmcUtility
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcUtility {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcUtility.class);
    private static final String[] SQL_STRING = {" delete "," insert "," update "," truncate "," or "," and "," <> "," like "," = ", " from ", " set ", " case ", " order ", " group "};

    public static <T> List<T> _executeSQL(boolean isAdvanced, String logObid,String sqlStrInner,String sqlStrOuter, String sqlOrderByClause,Map<String,String> attrMap,int objectLimit,
            PagingEntity searchInfo,ArrayList<OmcOQLVariableParameter> variableParmList,RowBounds paramRowBounds ,boolean logWrite,int processLevel,boolean testMode){
            return _executeSQL(isAdvanced,logObid,sqlStrInner,sqlStrOuter,sqlOrderByClause,attrMap,objectLimit,null,searchInfo,variableParmList,paramRowBounds,logWrite,processLevel,testMode);
    }
    @SuppressWarnings("unchecked")
    public static <T> List<T> _executeSQL(boolean       isAdvanced        ,
                                          String        logObid           ,
                                          String        sqlStrInner       ,
                                          String        sqlStrOuter       ,
                                          String        sqlOrderByClause  ,
                                          Map<String,String> attrMap      ,
                                          int           objectLimit       ,
                                          String        searchHint        ,
                                          PagingEntity  searchInfo        ,
                                          ArrayList<OmcOQLVariableParameter> variableParmList,
                                          RowBounds     paramRowBounds    ,
                                          boolean       logWrite          ,
                                          int           processLevel      ,
                                          boolean       testMode          ){
        int     pos       = 0;
        Integer funcationVariableSeq = 1;
        int     variableSeq = 1;
        ArrayList<OmcOQLVariableParameter> mainVariableParmList = new ArrayList<OmcOQLVariableParameter>();
        for(int i = 0; i < variableParmList.size();i++){
            pos = StrUtil.inStr(sqlStrInner, OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT + variableParmList.get(i).getParameterName()+OmcFoundationConstant.OQL_PARAMETER_VALUE_RIGHT);
            if(pos == 0) pos = StrUtil.inStr(sqlStrOuter, OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT + variableParmList.get(i).getParameterName()+OmcFoundationConstant.OQL_PARAMETER_VALUE_RIGHT);
            while(pos > 0){
                if(variableParmList.get(i).isFunction()){
                    HashMap<String,Object> rtnMap = OmcUtility.getFunctionParameter(variableParmList.get(i).getParameterValue(),funcationVariableSeq,processLevel+1,testMode);
                    ArrayList<OmcOQLVariableParameter> funcVariableParmList = (ArrayList<OmcOQLVariableParameter>)rtnMap.get(OmcFoundationConstant.OQL_MAPPARM_VAR_LIST);
                    String functionStr = (String)rtnMap.get(OmcFoundationConstant.OQL_MAPPARM_CONVERTED_STR);
                    String oldParm = OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT + variableParmList.get(i).getParameterName() + OmcFoundationConstant.OQL_PARAMETER_VALUE_RIGHT;
                    
                    sqlStrInner = sqlStrInner.replace(oldParm, functionStr);
                    sqlStrOuter = sqlStrOuter.replace(oldParm, functionStr);
                    for(int j = 0; j < funcVariableParmList.size(); j++)
                    {
                        mainVariableParmList.add(funcVariableParmList.get(j));
                        funcationVariableSeq++;
                    }
                }
                else{
                    StringBuffer newVariable = new StringBuffer("omcVariable_a").append(StrUtil.LPAD(variableSeq, 5, "0"));
                    String value = variableParmList.get(i).getParameterValue().replace("*", "%");
                    mainVariableParmList.add(new OmcOQLVariableParameter(newVariable.toString(),value,false));
                    
                    String bindVariable = "#{" + newVariable + "}";
                    String oldParm = OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT + variableParmList.get(i).getParameterName()+OmcFoundationConstant.OQL_PARAMETER_VALUE_RIGHT;
                    sqlStrInner = sqlStrInner.replace(oldParm,bindVariable);
                    sqlStrOuter = sqlStrOuter.replace(oldParm,bindVariable);
                    variableSeq++;
                }
                pos = StrUtil.inStr(sqlStrInner, OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT + variableParmList.get(i).getParameterName()+OmcFoundationConstant.OQL_PARAMETER_VALUE_RIGHT);
                if(pos == 0) pos = StrUtil.inStr(sqlStrOuter, OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT + variableParmList.get(i).getParameterName()+OmcFoundationConstant.OQL_PARAMETER_VALUE_RIGHT);
                
            }
        }
        for(OmcOQLVariableParameter mainVariableParm : mainVariableParmList){
            if(mainVariableParm.isRightArribute()){
                String alias    = mainVariableParm.getParameterValue().substring(1,mainVariableParm.getParameterValue().indexOf('.'));
                String atribute = mainVariableParm.getParameterValue().substring(mainVariableParm.getParameterValue().indexOf('[')+1,mainVariableParm.getParameterValue().indexOf(']'));
                String dbColmun = attrMap.get(alias + ":" + atribute);
                mainVariableParm.setParameterValue(mainVariableParm.getParameterValue().replace("@"+alias+".[" + atribute + "]", alias + "." + dbColmun));
                sqlStrInner = sqlStrInner.replace("#{" + mainVariableParm.getParameterName() + "}", mainVariableParm.getParameterValue());
                sqlStrOuter = sqlStrOuter.replace("#{" + mainVariableParm.getParameterName() + "}", mainVariableParm.getParameterValue());
            }
            if(!NullUtil.isNone(mainVariableParm.getParameterValue()) && mainVariableParm.getParameterValue().indexOf(OmcSystemConstants.OMC_DATE_CONVERT_PREFIX) == 1){
                mainVariableParm.setDateConvert(true);
                String convertVariable = OmcSchemaUtil.getConvertLocalCharToUtcDateStr(mainVariableParm.getParameterName());
                //String convertVariable = OmcSchemaUtil.getConvertLocalCharToUtcDateStr() +"(#{" + mainVariableParm.getParameterName() + "},'" + OmcSystemConstants.OMC_DBMS_DATE_FORMAT + "')";
                sqlStrInner = sqlStrInner.replace("#{" + mainVariableParm.getParameterName() + "}", convertVariable);
                sqlStrOuter = sqlStrOuter.replace("#{" + mainVariableParm.getParameterName() + "}", convertVariable);
            }
        }
        OmcSQLVariableParameter sqlVariableParameter = OmcUtility._getSQLVariableParameter(mainVariableParmList, processLevel+1, testMode);
        
        StringBuffer executingSqlBuf = new StringBuffer();
        String countSqlQuery = "";
        String executingSql  = "";
        boolean isPaging = false;
        executingSqlBuf.append("select ");
        if(!StrUtil.isEmpty(searchHint) && OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)) executingSqlBuf.append(searchHint).append(" ");
        executingSqlBuf.append(sqlStrOuter).append(" from (").append(sqlStrInner).append(") a");
        if(!StrUtil.isEmpty(sqlOrderByClause)) {
            if(isAdvanced){
                executingSqlBuf.append(OmcFoundationConstant.newline).append("group by ").append(sqlOrderByClause);
            }else{
                executingSqlBuf.append(OmcFoundationConstant.newline).append("order by ").append(sqlOrderByClause);
            }
        }
        if(searchInfo != null){
            isPaging = true;
            countSqlQuery = OmcPagingQueryUtil.getCountSql(sqlStrInner);
            DomUtil.copyPagingEntity(searchInfo,sqlVariableParameter);
            executingSql = OmcPagingQueryUtil.getPagingSql(executingSqlBuf.toString(), sqlVariableParameter.getTargetRow(), sqlVariableParameter.getTargetRow() + sqlVariableParameter.getRowSize(), sqlVariableParameter.getRowSize());
        }else{
            executingSql = executingSqlBuf.toString();
        }
        LOGGER.debug("*************************Executing Sql*************************");
        LOGGER.debug("{}",executingSql);
        LOGGER.debug("*************************Executing Sql*************************");
        List<T> dataList   = OmcOQLServiceUtil.getObjects(sqlVariableParameter,countSqlQuery,executingSql,isPaging,paramRowBounds);
        if(isPaging) DomUtil.copyPagingEntity(sqlVariableParameter,searchInfo);
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(dataList);
    }
    
    @SuppressWarnings("unchecked")
    public static OmcOQLGetRelatedSqlPattern createSqlPatternForGetRelatd(  
            Set<String> classVoListSet,
            List<String> obidList,
                                                                            String   relationPatternIn  , 
                                                                            String   filterPatternIn    ,
                                                                            String   fromToDirectionIn  , 
                                                                            String   selectPatternIn    , 
                                                                            String   wherePatternIn     , 
                                                                            String   parameterPatternIn ,
                                                                            boolean  isRelationShip     ,
                                                                            int      processLevel       ,
                                                                            boolean  testMode           ){
        
        OmcOQLGetRelatedSqlPattern sqlPattern = new OmcOQLGetRelatedSqlPattern();
        
        String filterPattern    = OmcFoundationConstant.OQL_ALL;
        String relationPattern  = OmcFoundationConstant.OQL_ALL;
        String fromToDirection  = fromToDirectionIn;
        String patternSelect    = "";
        String patternWhere     = "";
        String patternParameter = "";
        
        if (!StrUtil.isEmpty(relationPatternIn) && !relationPatternIn.trim().equals (OmcFoundationConstant.OQL_ALL))  relationPattern  = relationPatternIn.trim();
        if (!StrUtil.isEmpty(filterPatternIn)   && !filterPatternIn.trim().equals   (OmcFoundationConstant.OQL_ALL))  filterPattern    = filterPatternIn.trim();
        if (!StrUtil.isEmpty(selectPatternIn)   && !selectPatternIn.trim().equals   (OmcFoundationConstant.OQL_ALL))  patternSelect    = selectPatternIn.trim();
        if (!StrUtil.isEmpty(wherePatternIn)    && !wherePatternIn.trim().equals    (OmcFoundationConstant.OQL_ALL))  patternWhere     = wherePatternIn.trim();
        if (!StrUtil.isEmpty(parameterPatternIn)&& !parameterPatternIn.trim().equals(OmcFoundationConstant.OQL_ALL))  patternParameter = parameterPatternIn.trim();
        
        sqlPattern.setRelationPattern(relationPattern);
        sqlPattern.setFilterPattern(filterPattern);
        sqlPattern.setPatternSelect(patternSelect);
        sqlPattern.setPatternWhere(patternWhere);
        sqlPattern.setPatternParameter(patternParameter);
        sqlPattern.setFromToDirection(fromToDirection);

        sqlPattern.setObidList(obidList);
        sqlPattern.setClassVoListSet(classVoListSet);

        sqlPattern.setClassVoListStr(StrUtil.convertSet2Str(sqlPattern.getClassVoListSet()));
        /*************************************************************************Get Related Class Info List*************************************************/
        Map<String,Object> map = OmcUtility.getRelatedClassInfoSet(sqlPattern.getFromToDirection(),
                                                                   sqlPattern.getClassVoListStr(),
                                                                   sqlPattern.getRelationPattern(),
                                                                   sqlPattern.getFilterPattern(),
                                                                   processLevel+1,testMode);
        
        Set<String> classFilterSet                      = (Set<String>)map.get(OmcFoundationConstant.OQL_RELATED_MAPPARM_CLASS_FILTER);
        String patternRelationshipStr                   = StrUtil.convertSet2Str((Set<String>)map.get(OmcFoundationConstant.OQL_RELATED_MAPPARM_PATTERN_REL));
        List<OmcOQLRelatedClassVO> relatedClassInfoList = (List<OmcOQLRelatedClassVO>)map.get(OmcFoundationConstant.OQL_RELATED_MAPPARM_CLASS_LIST);
        
        sqlPattern.setClassFilterSet(classFilterSet);
        sqlPattern.setPatternRelationshipStr(patternRelationshipStr);
        sqlPattern.setRelatedClassInfoList(relatedClassInfoList);
        /*************************************************************************Seperate Select Pattern Start*************************************************/
        HashMap<String,Object> seperatedPattern = OmcUtility.seperateSelectionPattern(sqlPattern.getPatternSelect(), processLevel, testMode);

        if(!NullUtil.isNone(seperatedPattern)){
            String patternSelectStr   = (String)seperatedPattern.get(OmcFoundationConstant.OQL_MAPPARM_SELECT_STR);
            String patternSubQueryStr = (String)seperatedPattern.get(OmcFoundationConstant.OQL_MAPPARM_SUBQUERY_STR);
            sqlPattern.setPatternSubQuery(patternSubQueryStr);
        
            StringBuffer patternFromBuf   = new StringBuffer();
            StringBuffer patternSelectBuf = new StringBuffer();
            StringBuffer patternSortByBuf = new StringBuffer();
            
            OmcUtility.getCleanedIputFromPattern("", patternSelectStr, "", patternFromBuf,patternSelectBuf,patternSortByBuf,OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this,processLevel, testMode);
            
            sqlPattern.setPatternSelect(patternSelectBuf.toString());
            sqlPattern.setPatternSortBy(patternSortByBuf.toString());
        }
        /*****************************************************************Seperate Parameter And Where ********************************************************/
        StringBuffer patternWhereBuf               = new StringBuffer();
        StringBuffer patternParameterBuf           = new StringBuffer();
        StringBuffer patternSubQueryWhere          = new StringBuffer();
        StringBuffer patternSubQueryWhereParameter = new StringBuffer();
        
        OmcUtility.seperateParameterAndWhere(sqlPattern.getPatternWhere()     ,
                                             sqlPattern.getPatternParameter() , 
                                             patternWhereBuf                  ,
                                             patternParameterBuf              ,
                                             patternSubQueryWhere             ,
                                             patternSubQueryWhereParameter    ,
                                             processLevel+1                   ,
                                             testMode                         );
                                
        sqlPattern.setPatternWhere(patternWhereBuf.toString());
        sqlPattern.setPatternParameter(patternParameterBuf.toString());
        sqlPattern.setPatternSubQueryWhere(patternSubQueryWhere.toString());
        sqlPattern.setPatternSubQueryWhereParameter(patternSubQueryWhereParameter.toString());
        /*************************************************************************Create From Pattern********************************************************/
        StringBuffer fromPatternBuf = new StringBuffer();
        if(isRelationShip){
            fromPatternBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT).append(OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT).append(sqlPattern.getPatternRelationshipStr());
            fromPatternBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT).append(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator).append(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this).append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT);
        }else{
            if(fromToDirectionIn.equals(OmcFoundationConstant.OQL_DIRECTION_From)){
                fromPatternBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT).append(OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT).append(sqlPattern.getPatternClassFilterStr());
                fromPatternBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT).append(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator).append(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this).append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT);
                fromPatternBuf.append("ThisConnectedWithFrom").append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT).append(OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT).append(sqlPattern.getPatternRelationshipStr());
                fromPatternBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT).append(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator).append(OmcFoundationConstant.OQL_TARGET_REL_ALIAS_REL).append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT);
            }else{
                fromPatternBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT).append(OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT).append(sqlPattern.getPatternClassFilterStr());
                fromPatternBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT).append(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator).append(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this).append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT);
                fromPatternBuf.append("ThisConnectedWithTo").append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT).append(OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT).append(sqlPattern.getPatternRelationshipStr());
                fromPatternBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT).append(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator).append(OmcFoundationConstant.OQL_TARGET_REL_ALIAS_REL).append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT);
            }            
        }
        sqlPattern.setPatternFrom(fromPatternBuf.toString());
        return sqlPattern;
    }
    
    public static void setForInputParameter(OmcOQLApiRelatedLogVO apiLogVOIn, OmcOQLApiRelatedLogVO trmpRelatedLogVO){
        apiLogVOIn.setKeyValue(trmpRelatedLogVO.getKeyValue());
        apiLogVOIn.setPatternParameter(trmpRelatedLogVO.getPatternParameter());
        apiLogVOIn.setPatternWhere(trmpRelatedLogVO.getPatternWhere());
        apiLogVOIn.setSqlAddedSelect(trmpRelatedLogVO.getSqlAddedSelect());
        apiLogVOIn.setSqlRelLastSelect(trmpRelatedLogVO.getSqlRelLastSelect());
        apiLogVOIn.setSqlSubQuerySelect(trmpRelatedLogVO.getSqlSubQuerySelect());
        apiLogVOIn.setSqlThisLastSelect(trmpRelatedLogVO.getSqlThisLastSelect());
        apiLogVOIn.setSqlTotalSelect(trmpRelatedLogVO.getSqlTotalSelect());
    }
    public static void getFinalSelectStatementForSearch( OmcOQLSqlPattern         sqlPattern                 ,
                                                         OmcOQLSearchParsingResult parsingResult             ,
                                                         String                   sqlSubQuerySelect          ,
                                                         String                   sqlSubQueryWhere           ,
                                                         StringBuffer             sqlSelectAllCount          ,
                                                         StringBuffer             sqlSelectAllSelect         ,
                                                         final boolean            isAdvanced                 ,
                                                         int                      processLevel               ,
                                                         boolean                  testMode                   ){
        int idx = 0;
        sqlSelectAllCount.setLength(0);sqlSelectAllSelect.setLength(0);;
        
        StringBuffer sqlSubTotalCountTemp = new StringBuffer();
        while(idx < parsingResult.getFromClauseSet().size()){
            StringBuffer sqlSubTotalCount = new StringBuffer();
            if(isAdvanced){
                sqlSubTotalCount.append("select ").append(parsingResult.getSqlSelectClause());
            }else{
                sqlSubTotalCount.append("select ").append(parsingResult.getThisSelectFirstSet().get(idx));
                if(!NullUtil.isNone(parsingResult.getSqlSelectClause())) sqlSubTotalCount.append(",").append(OmcFoundationConstant.newline).append(parsingResult.getSqlSelectClause());
            }
            if(!NullUtil.isNone(parsingResult.getFromClauseSet().get(idx))) sqlSubTotalCount.append(OmcFoundationConstant.newline).append("from ").append(parsingResult.getFromClauseSet().get(idx));
            String str = parsingResult.getJoinClauseSet().get(idx);
            if(!NullUtil.isNone(str)) sqlSubTotalCount.append(OmcFoundationConstant.newline).append("where ").append(str);
            if(!NullUtil.isNone(parsingResult.getWhereClause())) sqlSubTotalCount.append(OmcFoundationConstant.newline).append("and ").append(parsingResult.getWhereClause());
            if(!NullUtil.isNone(sqlSubQueryWhere)) sqlSubTotalCount.append(OmcFoundationConstant.newline).append("and ").append(sqlSubQueryWhere);
            
            if(!NullUtil.isNone(sqlSubTotalCountTemp.toString())) sqlSubTotalCountTemp.append(OmcFoundationConstant.newline).append("union all");
            sqlSubTotalCountTemp.append(OmcFoundationConstant.newline).append(sqlSubTotalCount);
            idx++;
        }
        sqlSelectAllCount.append(sqlSubTotalCountTemp);
        if(isAdvanced){
            if(!NullUtil.isNone(parsingResult.getSqlSelectClauseLast())) sqlSelectAllSelect.append(parsingResult.getSqlSelectClauseLast());
            sqlSelectAllSelect.append(",").append("count(1)").append(" ").append(OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY_COUNT);
        }else{
            sqlSelectAllSelect.append(parsingResult.getSqlSelectThisLast()).append(OmcFoundationConstant.newline);
            sqlSelectAllSelect.append(OmcUtility.createBasicDispalyedName("this"));
            if(!NullUtil.isNone(parsingResult.getSqlSelectClauseLast())) sqlSelectAllSelect.append(",").append(OmcFoundationConstant.newline).append(parsingResult.getSqlSelectClauseLast());
            if(!StrUtil.isEmpty(sqlSubQuerySelect)) sqlSelectAllSelect.append(",").append(OmcFoundationConstant.newline).append(sqlSubQuerySelect);
        }
    }
    
    public static OmcOQLSqlPattern createSqlPattern(String     patternSelectIn      , 
                                                    String     patternFromIn        , 
                                                    String     wherePatternIn       ,
                                                    String     parameterPatternIn   ,
                                                    String     className            ,
                                                    int        processLevel         ,
                                                    boolean    testMode             ){

        OmcOQLSqlPattern sqlPattern = new OmcOQLSqlPattern();
        
        if(!NullUtil.isNone(patternSelectIn)    && !patternSelectIn.trim().equals(OmcFoundationConstant.OQL_ALL))    sqlPattern.setPatternSelect(patternSelectIn.trim());
        if(!NullUtil.isNone(patternFromIn)      && !patternFromIn.trim().equals(OmcFoundationConstant.OQL_ALL))      sqlPattern.setPatternFrom(patternFromIn.trim());
        if(!NullUtil.isNone(wherePatternIn)     && !wherePatternIn.trim().equals(OmcFoundationConstant.OQL_ALL))     sqlPattern.setPatternWhere(wherePatternIn.trim());
        if(!NullUtil.isNone(parameterPatternIn) && !parameterPatternIn.trim().equals(OmcFoundationConstant.OQL_ALL)) sqlPattern.setPatternParameter(parameterPatternIn.trim());
        
        HashMap<String,Object> seperatedPattern = OmcUtility.seperateSelectionPattern(sqlPattern.getPatternSelect(), processLevel, testMode);
        sqlPattern.setPatternSelect((String)seperatedPattern.get(OmcFoundationConstant.OQL_MAPPARM_SELECT_STR));
        sqlPattern.setPatternSubQuery((String)seperatedPattern.get(OmcFoundationConstant.OQL_MAPPARM_SUBQUERY_STR));
        
        StringBuffer patternFrom   = new StringBuffer();
        StringBuffer patternSelect = new StringBuffer();
        StringBuffer patternSortBy = new StringBuffer();
        
        OmcUtility.getCleanedIputFromPattern(sqlPattern.getPatternFrom(), sqlPattern.getPatternSelect(), className, patternFrom,patternSelect,patternSortBy,OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this,processLevel, testMode);
        
        sqlPattern.setPatternFrom(patternFrom.toString());
        sqlPattern.setPatternSelect(patternSelect.toString());
        sqlPattern.setPatternSortBy(patternSortBy.toString());
        return sqlPattern;
    }
    public static OmcOQLSqlPattern createSqlPatternForSearch(final String     className           , 
                                                             final String     namePattern         , 
                                                             final String     revisionPattern     ,
                                                             final String     lifeCylePattern     , 
                                                             final String     statePattern        , 
                                                             final String     creatorPattern      , 
                                                             final String     modifierPattern     ,
                                                             final String     ownerPattern        , 
                                                             final String     checkouterPattern   , 
                                                             final String     lockerPattern       , 
                                                             final boolean    firstOnly           , 
                                                             final boolean    latestOnly          ,
                                                             final boolean    lockedOnly          , 
                                                             final boolean    checkoutedOnly      ,
                                                             final String     patternSelectIn     , 
                                                             final String     patternFromIn       , 
                                                             final String     wherePatternIn      ,
                                                             final String     parameterPatternIn  ,
                                                             int        processLevel        ,
                                                             boolean    testMode            ){
        OmcOQLSqlPattern sqlPattern = createSqlPattern( patternSelectIn      , 
                                                        patternFromIn        , 
                                                        wherePatternIn       ,
                                                        parameterPatternIn   ,
                                                        className            ,
                                                        processLevel+1       ,
                                                        testMode             );
        StringBuffer patternWhere             = new StringBuffer();
        StringBuffer patternParameter         = new StringBuffer();
        StringBuffer patternSubQueryWhere     = new StringBuffer();
        StringBuffer patternSubQueryWhereParameter = new StringBuffer();

        
        OmcUtility.createParmeterPattern(namePattern, 
                                         revisionPattern, 
                                         lifeCylePattern, 
                                         statePattern, 
                                         creatorPattern, 
                                         modifierPattern, 
                                         ownerPattern, 
                                         checkouterPattern, 
                                         lockerPattern, 
                                         firstOnly, 
                                         latestOnly, 
                                         lockedOnly, 
                                         checkoutedOnly, 
                                         OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this, 
                                         sqlPattern.getPatternWhere(), 
                                         sqlPattern.getPatternParameter(), 
                                         patternWhere,
                                         patternParameter,
                                         processLevel+1, 
                                         testMode);
    
        sqlPattern.setPatternWhere(patternWhere.toString());
        sqlPattern.setPatternParameter(patternParameter.toString());
        
        sqlPattern.setPatternWhereForKey(patternWhere.toString());
        sqlPattern.setPatternParameterForKey(patternParameter.toString());
        
        OmcUtility.seperateParameterAndWhere(sqlPattern.getPatternWhere()     ,
                                             sqlPattern.getPatternParameter() , 
                                             patternWhere                     ,
                                             patternParameter                 ,
                                             patternSubQueryWhere             ,
                                             patternSubQueryWhereParameter         ,
                                             processLevel+1                   ,
                                             testMode                         );
        sqlPattern.setPatternWhere(patternWhere.toString());
        sqlPattern.setPatternParameter(patternParameter.toString());
        sqlPattern.setPatternSubQueryWhere(patternSubQueryWhere.toString());
        sqlPattern.setPatternSubQueryWhereParameter(patternSubQueryWhereParameter.toString());
        return sqlPattern;
    }
    public static Map<String,String> getParameterValue(String parmStr){
        Map<String,String> map = new HashMap<String,String>();
        if(StrUtil.isEmpty(parmStr.trim())) return map;
        OmcOQLConvertedParmValue convertedParmValue = setGetConvertedParmValue(new OmcOQLConvertedParmValue(parmStr.trim(),parmStr.trim(),"subquery_select","♣","'","'",0,new HashMap<String,String>(),"All"),parmStr.trim(),"subquery_select",1,false);
        
        String wrkStr = convertedParmValue.getStrConverted();
        
        String[] parmArray = wrkStr.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME));
        for(int i = 0; i < parmArray.length; i++){
            String[] eachParm = parmArray[i].split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE));
            if(eachParm.length != 2) OmcComUtility.error("Parameter Pattern (" + parmStr + ") Error");
            map.put(eachParm[0], revokeConvertedParmValue(eachParm[1],convertedParmValue));
        }
        return map;
    }
    
    @SuppressWarnings("unchecked")
    public static void makrAliasAttrColumnInfo(ArrayList<OmcOQLPatternSplitedFrom> splitedFromList,
                                               Map<String,String>     attrMap,
                                               int        processLevel         ,
                                               boolean    testMode             ){

        Set<String> aliasSet = new HashSet<String>();
        for(OmcOQLPatternSplitedFrom splitedFrom : splitedFromList){
            aliasSet.add(splitedFrom.getLeftAlias());
            aliasSet.add(splitedFrom.getRightAlias());
        }
        Map<String,Object> aliasMap = new HashMap<String,Object>();
        for(String alias : aliasSet){
            Set<String> classSet = new HashSet<String>();
            for(OmcOQLPatternSplitedFrom splitedFrom : splitedFromList){
                if(splitedFrom.getLeftAlias().equals(alias)){
                    String classList = splitedFrom.getLeftClassList();
                    if(!NullUtil.isNone(classList)){
                        String[] strArray = classList.split(",");
                        for(int i = 0; i< strArray.length; i++){
                            classSet.add(strArray[i]);
                        }
                    }
                }
                if(splitedFrom.getRightAlias().equals(alias)){
                    String classList = splitedFrom.getRightClassList();
                    if(!NullUtil.isNone(classList)){
                        String[] strArray = classList.split(",");
                        for(int i = 0; i< strArray.length; i++){
                            classSet.add(strArray[i]);
                        }
                    }
    
                }
                if(classSet != null && classSet.size() > 0) aliasMap.put(alias, classSet);
            }
        }
        for(String alias : aliasMap.keySet()){
            Set<String> classSet = (Set<String>)aliasMap.get(alias);
            for(String className : classSet){
                ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
                for(ColumnInfo column : classInfo.getColumnList()){
                    attrMap.put(alias + ":" + column.getAttributeName(), column.getDbmsColumn());
                }
            }
        }
    }
    
    public static Map<String,Object> getRelatedClassInfoSet(String     fromToDirectionIn             ,
                                                            String     classNameListStr,
                                                            String     relationNameListStr, 
                                                            String     filterStr,
                                                            int        processLevel         ,
                                                            boolean    testMode             ){
        Map<String,Object> map = new HashMap<String,Object>();
        List<String> classNameList;
        List<String> realtionNameList;
        List<String> filterList;
        if(NullUtil.isNone(classNameListStr) || classNameListStr.equals(GlobalConstants.FLAG_TYPE_ALL) || classNameListStr.indexOf(GlobalConstants.FLAG_TYPE_ALL) != -1){
            classNameList = ClassInfoUtil.getInstantiableChildList(OmcSystemConstants.OBJECT_ROOT);
        }else{
            classNameList = ClassInfoUtil.getInstantiableChildList(classNameListStr);
        }
        if(NullUtil.isNone(relationNameListStr) || relationNameListStr.equals(GlobalConstants.FLAG_TYPE_ALL) || relationNameListStr.indexOf(GlobalConstants.FLAG_TYPE_ALL) != -1){
            realtionNameList = ClassInfoUtil.getInstantiableChildList(OmcSystemConstants.RELATION_OBJECT_ROOT);
        }else{
            realtionNameList = ClassInfoUtil.getInstantiableChildList(relationNameListStr);
        }
        if(NullUtil.isNone(filterStr) || filterStr.equals(GlobalConstants.FLAG_TYPE_ALL)){
            filterList = ClassInfoUtil.getInstantiableChildList(OmcSystemConstants.OBJECT_ROOT);
        }else{
            filterList = ClassInfoUtil.getInstantiableChildList(filterStr);
        }
        Set<String>  filterAllSet     = new HashSet<String>();
        Set<String>  relationAllSet   = new HashSet<String>();
        
        Set<String> classNameSet     = StrUtil.convertListToSet(classNameList);
        Set<String> realtionNameSet  = StrUtil.convertListToSet(realtionNameList);
        Set<String> filterSet        = StrUtil.convertListToSet(filterList);
        
        List<OmcOQLRelatedClassVO> relatedClassInfoList = createRelatedClassInfo(fromToDirectionIn, 
                                                                                classNameSet,
                                                                                realtionNameSet,
                                                                                filterSet,
                                                                                filterAllSet,
                                                                                relationAllSet,
                                                                                processLevel+1,
                                                                                testMode);
        map.put(OmcFoundationConstant.OQL_RELATED_MAPPARM_PATTERN_REL, relationAllSet);
        map.put(OmcFoundationConstant.OQL_RELATED_MAPPARM_CLASS_FILTER, filterAllSet);
        map.put(OmcFoundationConstant.OQL_RELATED_MAPPARM_CLASS_LIST, relatedClassInfoList);
        return map;
    }
    private static List<OmcOQLRelatedClassVO> makeRelatedClassInfo(List<OmcOQLRelatedClassTempVO> relatedClassTempVO,int processLevel,boolean testMode){
        Set<String> relattionSet = new HashSet<String>();
        Set<String> targetClassSet = new HashSet<String>();
        List<OmcOQLRelatedClassVO> result = new ArrayList<OmcOQLRelatedClassVO>();
        Set<String> keySet = new HashSet<String>();
        
        for(OmcOQLRelatedClassTempVO tempVo : relatedClassTempVO){
            keySet.add(tempVo.getRelattionTableName() + ":" + tempVo.getTargetTableName() + ":" + tempVo.getIsTagetClassFrom());
        }
        for(String key : keySet){
            String[] strArray = key.split(":");
            targetClassSet.clear();relattionSet.clear();
            for(OmcOQLRelatedClassTempVO tempVo : relatedClassTempVO){
                if(strArray[0].equals(tempVo.getRelattionTableName()) && strArray[1].equals(tempVo.getTargetTableName()) && strArray[2].equals(tempVo.getIsTagetClassFrom())){
                    relattionSet.add(tempVo.getRelattion());
                    targetClassSet.add(tempVo.getTargetClass());
                }
            }
            result.add(new OmcOQLRelatedClassVO(new HashSet<String>(relattionSet),strArray[0],strArray[2],new HashSet<String>(targetClassSet),strArray[1]));
        }
        return result;
    }
    public static Map<String,Object> resloveParameterRelationShip(String  keyValue,
                                                                  String  patternWhere,
                                                                  String  patternParameter,
                                                                  String  sqlTotalSelect    ,
                                                                  String  sqlThisLastSelect ,
                                                                  String  sqlSubQuerySelect ,
                                                                  String  sqlAddedSelect    ,
                                                                  int     processLevel,
                                                                  boolean testMode){
        Map<String,Object> map = new HashMap<String,Object>();
        OmcOQLApiRelatedLogVO apiLogVO = new OmcOQLApiRelatedLogVO();
        apiLogVO.setKeyValue           (keyValue);
        apiLogVO.setPatternWhere       (patternWhere);
        apiLogVO.setPatternParameter   (patternParameter);
        apiLogVO.setSqlTotalSelect     (sqlTotalSelect);
        apiLogVO.setSqlThisLastSelect  (sqlThisLastSelect);
        apiLogVO.setSqlSubQuerySelect  (sqlSubQuerySelect);
        apiLogVO.setSqlAddedSelect     (sqlAddedSelect);
        map.put("apiLogVO", apiLogVO);
        ArrayList<OmcOQLVariableParameter> variableParameter = convertVariableParameterList(patternWhere, patternParameter,processLevel+1,testMode);
        map.put("variableParameter", variableParameter);
        return map;
    }
    public static Map<String,Object> resloveParameterRelated(   String  keyValue,
                                                                String  patternWhere,
                                                                String  patternParameter,
                                                                String  sqlTotalSelect    ,
                                                                String  sqlThisLastSelect ,
                                                                String  sqlRelLastSelect  ,
                                                                String  sqlSubQuerySelect ,
                                                                String  sqlAddedSelect    ,
                                                                int     processLevel,
                                                                boolean testMode){
        Map<String,Object> map = new HashMap<String,Object>();
        OmcOQLApiRelatedLogVO apiLogVO = new OmcOQLApiRelatedLogVO();
        apiLogVO.setKeyValue           (keyValue);
        apiLogVO.setPatternWhere       (patternWhere);
        apiLogVO.setPatternParameter   (patternParameter);
        apiLogVO.setSqlTotalSelect     (sqlTotalSelect);
        apiLogVO.setSqlThisLastSelect  (sqlThisLastSelect);
        apiLogVO.setSqlRelLastSelect   (sqlRelLastSelect);
        apiLogVO.setSqlSubQuerySelect  (sqlSubQuerySelect);
        apiLogVO.setSqlAddedSelect     (sqlAddedSelect);
        map.put("apiLogVO", apiLogVO);
        ArrayList<OmcOQLVariableParameter> variableParameter = convertVariableParameterList(patternWhere, patternParameter,processLevel+1,testMode);
        map.put("variableParameter", variableParameter);
        return map;
    }

    private static List<OmcOQLRelatedClassVO> createRelatedClassInfo(String      fromToDirectionIn, 
                                                                     Set<String> classNameSet,
                                                                     Set<String> realtionNameSet,
                                                                     Set<String> filterSet,
                                                                     Set<String>  filterAllSet,
                                                                     Set<String>  relationAllSet,
                                                                     int         processLevel         ,
                                                                     boolean     testMode             ){

        List<OmcOQLRelatedClassTempVO> tempResult = new ArrayList<OmcOQLRelatedClassTempVO>();
        int direction = 0;
        if(fromToDirectionIn.equals(GlobalConstants.FLAG_TYPE_FROM)) direction = OmcFoundationConstant.OQL_DIRECTION_FROM_;
        if(fromToDirectionIn.equals(GlobalConstants.FLAG_TYPE_TO))   direction = OmcFoundationConstant.OQL_DIRECTION_TO_;
        if(fromToDirectionIn.equals(GlobalConstants.FLAG_TYPE_ALL))  direction = OmcFoundationConstant.OQL_DIRECTION_ALL_;

        for(String realtionName : realtionNameSet){
            ClassInfo relationClassInfo     = ClassInfoUtil.getClassInfo(realtionName);
            Set<String> allowedFromClassSet = relationClassInfo.getAllowedFromClassSet();
            Set<String> allowedToClassSet   = relationClassInfo.getAllowedToClassSet();
            
            if(allowedFromClassSet != null && allowedFromClassSet.size() > 0 && allowedToClassSet != null && allowedToClassSet.size() > 0 ){
                switch(direction){
                  case OmcFoundationConstant.OQL_DIRECTION_FROM_ :{
                      createRelatedClassInfoSub(  relationClassInfo ,false,classNameSet,filterSet,filterAllSet,relationAllSet,tempResult,processLevel + 1,testMode);
                      break;
                  }
                  case OmcFoundationConstant.OQL_DIRECTION_TO_ :{
                      createRelatedClassInfoSub(  relationClassInfo ,true,classNameSet,filterSet,filterAllSet,relationAllSet,tempResult,processLevel + 1,testMode);
                      break;
                  }
                  case OmcFoundationConstant.OQL_DIRECTION_ALL_:{
                      createRelatedClassInfoSub(  relationClassInfo ,true ,classNameSet,filterSet,filterAllSet,relationAllSet,tempResult,processLevel + 1,testMode);
                      createRelatedClassInfoSub(  relationClassInfo ,false,classNameSet,filterSet,filterAllSet,relationAllSet,tempResult,processLevel + 1,testMode);
                      break;
                  }
                  default:{
                      OmcComUtility.error("Direction(" + fromToDirectionIn + ") Error");
                  }
               }
            }
        }
        List<OmcOQLRelatedClassVO> result = makeRelatedClassInfo(tempResult,processLevel+1,testMode);
        return result;
    }
    
    private static void createRelatedClassInfoSub(  ClassInfo   relationClassInfo, 
                                                    boolean     isFrom           ,
                                                    Set<String> classNameSet     ,
                                                    Set<String> filterSet        ,
                                                    Set<String> filterAllSet     ,
                                                    Set<String> relationAllSet   ,
                                                    List<OmcOQLRelatedClassTempVO> result,
                                                    int         processLevel         ,
                                                    boolean     testMode             ){
        
        Set<String> allowedFromClassSet = relationClassInfo.getAllowedFromClassSet();
        Set<String> allowedToClassSet   = relationClassInfo.getAllowedToClassSet();
        String[] classArray;
        List<AllowedClassInfo> alowedClassInfoList = relationClassInfo.getAllowedClassInfo();
        for(AllowedClassInfo alowedClassInfo : alowedClassInfoList){
            if(isFrom && Bit.isInclude(alowedClassInfo.getFlags(),OmcSystemConstants.FLAG_ALLOWEDRELATION_TO)){
                classArray = alowedClassInfo.getClassName().split(Pattern.quote("."));
                if(StrUtil.in(classNameSet, allowedFromClassSet) && filterSet.contains(classArray[0])){
                    result.add(new OmcOQLRelatedClassTempVO(relationClassInfo.getClassName(),relationClassInfo.getDbmsTable(),"N",classArray[0],classArray[1])); 
                    filterAllSet.add(classArray[0]);relationAllSet.add(relationClassInfo.getClassName());
                }
            }else if(!isFrom && Bit.isInclude(alowedClassInfo.getFlags(),OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM)){
                classArray = alowedClassInfo.getClassName().split(Pattern.quote("."));
                if(StrUtil.in(classNameSet, allowedToClassSet) && filterSet.contains(classArray[0])){
                    result.add(new OmcOQLRelatedClassTempVO(relationClassInfo.getClassName(),relationClassInfo.getDbmsTable(),"Y",classArray[0],classArray[1])); 
                    filterAllSet.add(classArray[0]);relationAllSet.add(relationClassInfo.getClassName());
                }
            }
        }
    }
    public static String getJoinClauseForPatternFrom(String uniquePath, List<OmcOQLPatternSplitedFrom> splitedFromList,int processLevel,boolean testMode){
        String strPathArray[] = uniquePath.split(Pattern.quote("."));
        StringBuffer strBuf = new StringBuffer();
        for(int i = 0; i < strPathArray.length; i++){
            for(OmcOQLPatternSplitedFrom splitedFrom : splitedFromList){
                if(splitedFrom.getUniqueString().equals(strPathArray[i])){
                    String joinStr = splitedFrom.createJoinClause(processLevel+1, testMode);
                    if(!StrUtil.isEmpty(strBuf)) strBuf.append(OmcFoundationConstant.newline).append("and ");
                    strBuf.append(joinStr);
                    break;
                }
            }
        }
        return strBuf.toString();
    }
    public static String getFromClauseForPatternFrom(String uniquePath, List<OmcOQLPatternSplitedFrom> splitedFromList,int processLevel,boolean testMode){
        String strPathArray[] = uniquePath.split(Pattern.quote("."));
        LinkedHashMap<String,String> lm = new LinkedHashMap<String,String>();
        for(int i = 0; i < strPathArray.length; i++){
            for(OmcOQLPatternSplitedFrom splitedFrom : splitedFromList){
                if(splitedFrom.getUniqueString().equals(strPathArray[i])){
                    lm.put(splitedFrom.getLeftAlias() , splitedFrom.getLeftTable());
                    lm.put(splitedFrom.getRightAlias(), splitedFrom.getRightTable());
                    break;
                }
            }
        }
        StringBuffer resultStrBuf = new StringBuffer();
        for(String str : lm.keySet()){
            resultStrBuf.append(",").append(lm.get(str)).append(" ").append(str);
        }
        return(resultStrBuf.substring(1).toString());
    }
    public static String getMainDbmsTable(String queryPath, ArrayList<OmcOQLPatternSplitedFrom> splitedFromList,int processLevel,boolean testMode){
        String uniqueStr = "";
        if(queryPath.indexOf(".") != -1){
            uniqueStr = queryPath.substring(0, queryPath.indexOf("."));
        }else{
            uniqueStr = queryPath;
        }
        for(OmcOQLPatternSplitedFrom splitedFrom : splitedFromList){
            if(splitedFrom.getUniqueString().equals(uniqueStr)) return splitedFrom.getLeftTable();
        }
        return "";
    }
    public static void checkSqlStatement(String str, int processLevel,boolean testMode){
        String chkStr = str.toLowerCase();
        for(int i = 0; i < SQL_STRING.length; i++){
            if (chkStr.indexOf(SQL_STRING[i]) >= 0) OmcComUtility.error("[Foundation.OmcUtility.checkSqlStatement]Origin Query Patttern[" + str + "] has Invalid string[\"" + SQL_STRING[i] + "].\"");
        }
    }
    
    
    
    
    public static boolean isSubQueryPattern(String strIn,int processLevel,boolean testMode){
	    OmcComUtility.logWriteStart(processLevel, testMode);
		strIn = StrUtil.clearStringData(strIn);
		return isSubQueryFormat(strIn);
	}
	public static boolean isDefinedRelationClass(String className,int processLevel, boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    List<Class<?>> classes = OmcComUtility.find("omc.api.oql.model");
	    for (Class<?> foundClass : classes) {
	        if (className.equals(foundClass.getName())) return true;
	    }
	    OmcComUtility.logWriteEnd(processLevel, testMode);
	    return(true);
	}
	public static HashMap<String,Object>seperateSelectionPattern(String selectPatternIn, int processLevel, boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    HashMap<String,Object> hashMap = new HashMap<String,Object>();
		if(NullUtil.isNone(selectPatternIn)) return hashMap;
	    StringBuffer patternSelectAttr     = new StringBuffer();
		StringBuffer patternSelectSubQuery = new StringBuffer();
		
		String[] splitedPattern = selectPatternIn.split(Pattern.quote(OmcFoundationConstant.ATTRIBUTE_DELIMINATOR_VALUE));

		for (String str : splitedPattern){
		    if(!StrUtil.isEmpty(str.trim())){
    			String[] splitedSelect = str.split(Pattern.quote(OmcFoundationConstant.OQL_SELECTION_Seperator));
    			for(String subStr : splitedSelect){
    			    if(!StrUtil.isEmpty(subStr.trim())){
        				if (isSubQueryPattern(subStr,processLevel+1,testMode)){
        				    if (!StrUtil.isEmpty(patternSelectSubQuery)) patternSelectSubQuery.append(OmcFoundationConstant.ATTRIBUTE_DELIMINATOR_VALUE);
        				    patternSelectSubQuery.append(subStr);
        				}
        				else{
        				    if (!StrUtil.isEmpty(patternSelectAttr)) patternSelectAttr.append(OmcFoundationConstant.OQL_SELECTION_Seperator);
        				    patternSelectAttr.append(subStr);
        				}
    			    }
    			}
		    }
		}
		hashMap.put(OmcFoundationConstant.OQL_MAPPARM_SELECT_STR, patternSelectAttr.toString());
		hashMap.put(OmcFoundationConstant.OQL_MAPPARM_SUBQUERY_STR, patternSelectSubQuery.toString());
		OmcComUtility.logWriteEnd(processLevel, testMode);
		return hashMap;
	}
	
    public static HashMap<String,Object>seperateSelectionPatternNew(String selectPatternIn, int processLevel, boolean testMode){
        OmcComUtility.logWriteStart(processLevel, testMode);
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        if(NullUtil.isNone(selectPatternIn.trim())) return hashMap;
        selectPatternIn = selectPatternIn.trim();
        
        String[] splitedPattern = selectPatternIn.split(Pattern.quote(OmcFoundationConstant.ATTRIBUTE_DELIMINATOR_VALUE));
        
        List<String> selectAttrList     = new ArrayList<String>();
        List<String> selectSubQueryList = new ArrayList<String>();
        
        for (String str : splitedPattern){
            if(!StrUtil.isEmpty(str.trim())){
                String[] splitedSelect = str.split(Pattern.quote(OmcFoundationConstant.OQL_SELECTION_Seperator));
                for(String subStr : splitedSelect){
                    if(!StrUtil.isEmpty(subStr.trim())){
                        if (isSubQueryPattern(subStr,processLevel+1,testMode)){
                            selectSubQueryList.add(subStr);
                        }
                        else{
                            selectAttrList.add(subStr);
                        }
                    }
                }
            }
        }
        hashMap.put(OmcFoundationConstant.OQL_MAPPARM_SELECT_STR  , selectAttrList);
        hashMap.put(OmcFoundationConstant.OQL_MAPPARM_SUBQUERY_STR, selectSubQueryList);
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return hashMap;
	}
	
	public static String makeFromPattern(String className, String alias){
	    StringBuffer strBuf = new  StringBuffer();
	    strBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT);
	    strBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT).append(className.trim()).append(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT);
	    strBuf.append(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator).append(alias.trim());
	    strBuf.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT);
	    return(strBuf.toString());
	}
	public static String getCleanedIputFromPattern(final String patternFromIn, final String className,String alias){
	    String workingFromPattern = patternFromIn.trim().replace(" ", "");
	    String fromPattern = makeFromPattern(className,alias);
	    if(!StrUtil.isEmpty(workingFromPattern)){
            fromPattern = workingFromPattern.replace(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT + OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this + OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT, fromPattern);
	    }
	    return fromPattern;
    }
	public static void getCleanedIputFromPattern(final String patternFromIn, final String patternSelectIn, final String className, StringBuffer patternFromOut, StringBuffer patternSelectOut, StringBuffer patternSortByOut, final String alias,int processLevel, boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    patternFromOut.setLength(0);patternSelectOut.setLength(0);patternSortByOut.setLength(0);
        String patternFrom = patternFromIn.replace(" ", "");
		if (StrUtil.isEmpty(patternFrom)){
		    if(!StrUtil.isEmpty(className)){
	            patternFromOut.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT);
	            patternFromOut.append(OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT).append(className.trim()).append(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT);
	            patternFromOut.append(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator).append(alias.trim());
	            patternFromOut.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT);		        
		    }
		}
		else{
		    if(patternFrom.charAt(patternFrom.length()-1) == '+') patternFrom = patternFrom.substring(0, patternFrom.length()-1);
			StringBuffer tempFrom  = new StringBuffer();
			tempFrom.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT).append(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this).append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT);
			
			StringBuffer tempTo  = new StringBuffer();
			
			tempTo.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT);
			tempTo.append(OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT).append(className.trim()).append(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT);
			tempTo.append(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator).append(alias.trim());
			tempTo.append(OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT);
			
			patternFromOut.append(StrUtil.replaceAll(patternFrom, tempFrom.toString(),tempTo.toString()));
		}

		StringBuffer patternSelectTemp =  new StringBuffer(patternSelectIn);
		OmcOQLConvertedParmValue convertedParmValue = setGetConvertedParmValue(new OmcOQLConvertedParmValue(patternSelectTemp.toString(),patternSelectTemp.toString(),"subquery_select","%","'","'",0,new HashMap<String,String>(),"All"),patternSelectTemp.toString(),"subquery_select",processLevel+1,testMode);
		patternSelectTemp.setLength(0);patternSelectTemp.append(convertedParmValue.getStrConverted());
		
		if (StrUtil.inStr(patternSelectTemp.toString(), OmcFoundationConstant.OQL_ORDERBY_OrderBy) > 0){
		    String[] strArray = patternSelectTemp.toString().trim().split(OmcFoundationConstant.OQL_ORDERBY_OrderBy);
		    if(strArray.length > 2) OmcComUtility.error("Select Pattern error.(" + patternSelectTemp + ")");
		    patternSortByOut.append(strArray[1].trim());
		    if(!NullUtil.isNone(strArray[0])){
	            String temp = strArray[0].trim();
	            if(!NullUtil.isNone(temp)){
	                temp = temp.substring(0, temp.length()-1);
	                if(!NullUtil.isNone(temp)) patternSelectOut.append(temp);   	                
	            }
		    }
		}
		else{
			patternSortByOut.append("");
			patternSelectOut.append(patternSelectTemp.toString().trim());
		}
		revokeConvertedParmValue(patternSelectOut.toString(),convertedParmValue,patternSelectOut,processLevel+1,testMode);
		revokeConvertedParmValue(patternSortByOut.toString(),convertedParmValue,patternSortByOut,processLevel+1,testMode);
		OmcComUtility.logWriteEnd(processLevel, testMode);
	}
	
	public static void revokeConvertedParmValue(final String strIn, final OmcOQLConvertedParmValue convertedParmValue,StringBuffer outStrBuf, int processLevel,boolean testMode){
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    String rtnStr = strIn; 
	    StringBuffer fromStr = new StringBuffer();
	    for(String key : convertedParmValue.getParm().keySet())
	    {
	        String value = convertedParmValue.getFrom() + (String)convertedParmValue.getParm().get(key) + convertedParmValue.getTo();
	        fromStr.append(convertedParmValue.getAddedChar()).append(key).append(convertedParmValue.getAddedChar());
	        rtnStr = rtnStr.replace(fromStr.toString(), value);
	        fromStr.setLength(0);
	    }
	    outStrBuf.setLength(0);
	    outStrBuf.append(rtnStr);
	    OmcComUtility.logWriteEnd(processLevel, testMode);
	}
    public static String revokeConvertedParmValue(final String strIn, final OmcOQLConvertedParmValue convertedParmValue)
    {
        String rtnStr = strIn; 
        for(String key : convertedParmValue.getParm().keySet())
        {
            String value = (String)convertedParmValue.getParm().get(key);
            value = convertedParmValue.getFrom() + value +  convertedParmValue.getTo();
            key = convertedParmValue.getAddedChar() + key + convertedParmValue.getAddedChar();
            rtnStr = rtnStr.replace(key, value);
        }
        return(rtnStr);
    }
    public static String revokeConvertedParmValue(final String strIn, final OmcOQLConvertedParmValue convertedParmValue, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        String rtnStr = strIn; 
        for(String key : convertedParmValue.getParm().keySet())
        {
            String value = (String)convertedParmValue.getParm().get(key);
            value = convertedParmValue.getFrom() + value +  convertedParmValue.getTo();
            key = convertedParmValue.getAddedChar() + key + convertedParmValue.getAddedChar();
            rtnStr = rtnStr.replace(key, value);
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(rtnStr);
    }
	public static OmcOQLConvertedParmValue setGetConvertedParmValue( final OmcOQLConvertedParmValue convertedParmValue,
                                                                     String strCurrent,
                                                                     String dataKey, 
                                                                     int processLevel, 
                                                                     boolean testMode){
	    OmcOQLConvertedParmValue convertedParm = convertedParmValue;
	    String from          = convertedParm.getFrom();
        String to            = convertedParm.getTo();
        String attachedChar  = convertedParmValue.getAddedChar();
        String patternStr    = strCurrent;
	    
        
        int fromIdx = patternStr.indexOf(from);
        if(fromIdx == -1) return convertedParmValue;
        int toIdx = patternStr.indexOf(to, fromIdx+from.length());
        if(toIdx == -1) return convertedParmValue;
        String value = patternStr.substring(fromIdx+from.length(),toIdx);
        
        convertedParm.setConvertedCount(convertedParm.getConvertedCount()+1);
        String parm  = StrUtil.getRandomString(40);
        String prefix = patternStr.substring(0,fromIdx);
        String suffix = patternStr.substring(toIdx+to.length());
        String replacedStr = prefix + attachedChar + parm + attachedChar + suffix;
        
        convertedParm.setStrConverted(replacedStr);
        
        HashMap<String,String> parmMap =  convertedParm.getParm();
        if (parmMap == null) parmMap = new HashMap<String,String>();
        parmMap.put(parm, value);
        convertedParm.setParm(parmMap);
        convertedParm = setGetConvertedParmValue(convertedParm,replacedStr,dataKey,processLevel+1,testMode);
        OmcComUtility.logWriteEnd(processLevel, testMode);
	    return convertedParm;
	}
	public static void setGetConvertedParmValue(final String strIn, 
                                              String from, 
                                              String to,
                                              String attachedChar,
                                              String dataKey, 
                                              OmcOQLConvertedParmValue parmValueIn, 
                                              String attachedType, 
                                              int length, 
                                              String prefix, 
                                              Integer sequence,
                                              HashMap<String,Object> rtnMap,
                                              int processLevel, 
                                              boolean testMode)
    {
        //OmcComUtility.logWriteStart(processLevel, testMode);
        OmcOQLConvertedParmValue parmValue = new OmcOQLConvertedParmValue(strIn, strIn, dataKey, attachedChar,from, to, 0, new HashMap<String,String>(), attachedType);
        if (parmValueIn != null) parmValue = parmValueIn ;
        int totalLenngth = 0;
        int idx = 1;
        int cnt = 0;
        int firstPos = 0;
        int endPos = 0;
        String str = strIn;
        int charLength = from.length();
        
        if(from.equals(to))
        {
            firstPos = StrUtil.inStr(str, from,1,1);
            endPos   = StrUtil.inStr(str, from,1,2);
        }
        else
        {
            totalLenngth = str.length();
            while(idx <= totalLenngth)
            {
                if(StrUtil.subStr(str, idx,charLength).equals(from))
                {
                    cnt++;
                    if (firstPos == 0) firstPos = idx;
                }
                if(StrUtil.subStr(str, idx,charLength).equals(to) && firstPos > 0 && idx > firstPos) cnt--;
                if(cnt == 0 && firstPos > 0 && idx > firstPos) endPos = idx;
                idx = idx + charLength;
            }
            
        }
        if (endPos == 0) return; 
        String parmStr = prefix + StrUtil.LPAD(sequence.toString(), 5, "0") + "}";
        String value = StrUtil.subStr(str, firstPos,endPos-firstPos+charLength);
        if(value.equals("'") && StrUtil.subStr(str, -1).equals("'")) {value = StrUtil.subStr(value,2);value = StrUtil.subStr(value,1,value.length()-1);}
        if(attachedType.equals("Left"))
        {
            str = StrUtil.subStr(str, 1, firstPos -1) + attachedChar + parmStr + StrUtil.subStr(str, endPos + charLength);
        }else if (attachedType.equals("Right"))
        {
            str = StrUtil.subStr(str, 1, firstPos -1) + parmStr + attachedChar + StrUtil.subStr(str, endPos + charLength);
        }
        else
        {
            str = StrUtil.subStr(str, 1, firstPos -1) + attachedChar + parmStr + attachedChar + StrUtil.subStr(str, endPos + charLength);
        }
        HashMap<String,String> parm  = parmValue.getParm();
        parm.put(parmStr, value);
        parmValue.setParm(parm);
        rtnMap.put(OmcFoundationConstant.OQL_MAPPARM_CONVERTED_STR, str);
        rtnMap.put(OmcFoundationConstant.OQL_MAPPARM_SEQUENCE, sequence);
        rtnMap.put(OmcFoundationConstant.OQL_MAPPARM_PARMVALUE, parmValue);
        
        OmcComUtility.logWriteEnd(processLevel, testMode);
        sequence++;
        try{
            setGetConvertedParmValue(str,from,to,attachedChar,dataKey,parmValue,attachedType,length,prefix,sequence,rtnMap,processLevel + 1,testMode);
        }catch(Exception e){
            e.printStackTrace();
            throw new FoundationException("Foundation Error Occur" + str);
        }
        
    }
	public static OmcOQLSelectForQuery getSelectForQuery(String childClasses, String alias,int processLevel, boolean testMode){
	    OmcComUtility.logWriteStart(processLevel, testMode);

	    List<ClassDbmsTableVO> tableAndClassList = ClassInfoUtil.getClassAndDbmsTableList(childClasses);
	    
	    ArrayList<String> classList = new ArrayList<String>();
	    ArrayList<String> tableList = new ArrayList<String>();
	    for( int i = 0; i < tableAndClassList.size(); i++){
	        StrUtil.addList(classList, tableAndClassList.get(i).getClassName());
	        StrUtil.addList(tableList, tableAndClassList.get(i).getDbmsTable());
	    }
	    List<OmcOQLClassAttribute> classAttributeList = OmcOQLCacheDBUtility.getAttributeList(StrUtil.convertList2Str(classList), true,"className:dbmsTable:attributeName:dbmsColumnName",processLevel+1,testMode);
	    List<OmcOQLAttribute> attributeList           = getAttributeList(classAttributeList, processLevel+1,testMode);
		return(new OmcOQLSelectForQuery(classList,tableList,tableAndClassList,classAttributeList,attributeList,alias));
	}
	private static List<OmcOQLAttribute> getAttributeList(final List<OmcOQLClassAttribute> classAttributeList,int processLevel, boolean testMode){
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    ArrayList<OmcOQLAttribute> attributeList = new ArrayList<OmcOQLAttribute>();
	    int attrCnt = 0;
	    for(OmcOQLClassAttribute classAttr:classAttributeList){
	        boolean isExist = false;attrCnt = attributeList.size();
	        for(int i = 0; i < attrCnt; i++){
	            if (classAttr.getAttributeName().equals(attributeList.get(i).getAttributeName())){
	                isExist = true;break;
	            }
	        }
	        if(!isExist) attributeList.add(new OmcOQLAttribute(classAttr.getAttributeName(),classAttr.getDbmsColumnAliasName(),classAttr.getDataType(),classAttr.getFlags(),classAttr.getValueSettingInfo()));
	    }
	    OmcComUtility.logWriteEnd(processLevel, testMode);
	    return(attributeList);
	}

	public static List<OmcOQLClassAttribute> getClassAttributeList(final String classStr, final boolean isInstantialOnly,int processLevel, boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    String[] splitedClass = classStr.split(",");
	    ArrayList<String> classList = new ArrayList<String>();
	    for(String str : splitedClass){
	        classList.add(str);
	    }
	    List<OmcOQLClassAttribute>  dataList  = getClassAttributeList(classList,isInstantialOnly,processLevel+1,testMode);
	    OmcComUtility.logWriteEnd(processLevel, testMode);
	    return(dataList);
	}
	public static List<OmcOQLClassAttribute> getClassAttributeList(final ArrayList<String> classList, final boolean isInstantialOnly,int processLevel, boolean testMode){
        OmcComUtility.logWriteStart(processLevel, testMode);
        List<OmcOQLClassAttribute>  dataList = getAllClassAttributeList(classList, new ArrayList<String>(), "*",processLevel+1,testMode);
        OmcSortUtil.sort(dataList, "dbmsTable:dbmsColumnName", processLevel+1, testMode);
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(dataList);
	}
		
	public static void createParmeterPattern(String               namePattern          ,
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
								            String               alias                ,
								            String               patternWhereIn       ,
								            String               patternParameterIn   ,
								            StringBuffer         patternWhereOut      ,
								            StringBuffer         patternParameterOut  ,
								            int                  processLevel         ,
								            boolean              testMode             )
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    patternWhereOut.setLength(0);patternParameterOut.setLength(0);
	    String oqlOperator = null;
	    if (!StrUtil.isEmpty(patternParameterIn) && !patternParameterIn.equals(OmcFoundationConstant.OQL_ALL)) patternParameterOut.append(patternParameterIn);
	    if (!StrUtil.isEmpty(patternWhereIn    ) && !patternWhereIn.equals    (OmcFoundationConstant.OQL_ALL)) patternWhereOut.append    (patternWhereIn    );
	    
	    StringBuffer aliasNew = (new StringBuffer(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator)).append(alias);
	    if (!StrUtil.isEmpty(namePattern       ) && !namePattern.equals       (OmcFoundationConstant.OQL_ALL)) createInnerParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[names]").toString(),namePattern,processLevel+1,testMode);

	    if (!StrUtil.isEmpty(revisionPattern   ) && !revisionPattern.equals   (OmcFoundationConstant.OQL_ALL)){
	        oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Equal; 
	        if (StrUtil.inStr(revisionPattern, OmcFoundationConstant.OQL_ALL) > 0) oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Like; 
	        createParameterForOql(patternWhereOut,patternParameterOut, (new StringBuffer(aliasNew)).append(".[revision]").toString(),oqlOperator,revisionPattern,processLevel+1,testMode);
	    }
        if (!StrUtil.isEmpty(lifeCyclePattern   ) && !lifeCyclePattern.equals   (OmcFoundationConstant.OQL_ALL)){
            oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Equal; 
            if (StrUtil.inStr(lifeCyclePattern, OmcFoundationConstant.OQL_ALL) > 0) oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Like; 
            createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[lifeCycle]").toString(),oqlOperator,lifeCyclePattern,processLevel+1,testMode);
        }
        if (!StrUtil.isEmpty(statePattern   ) && !statePattern.equals   (OmcFoundationConstant.OQL_ALL)){
            oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Equal; 
            if (StrUtil.inStr(statePattern, OmcFoundationConstant.OQL_ALL) > 0) oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Like; 
            createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[states]").toString(),oqlOperator,statePattern,processLevel+1,testMode);
        }
        
        if (!StrUtil.isEmpty(creatorPattern   ) && !creatorPattern.equals   (OmcFoundationConstant.OQL_ALL)){
            oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Equal; 
            if (StrUtil.inStr(creatorPattern, OmcFoundationConstant.OQL_ALL) > 0) oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Like; 
            createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[creator]").toString(),oqlOperator,creatorPattern,processLevel+1,testMode);
        }
        
        if (!StrUtil.isEmpty(modifierPattern   ) && !modifierPattern.equals   (OmcFoundationConstant.OQL_ALL)){
            oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Equal; 
            if (StrUtil.inStr(modifierPattern, OmcFoundationConstant.OQL_ALL) > 0) oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Like; 
            createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[modifier]").toString(),oqlOperator,modifierPattern,processLevel+1,testMode);
        }
        
        if (!StrUtil.isEmpty(ownerPattern   ) && !ownerPattern.equals   (OmcFoundationConstant.OQL_ALL)){
            oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Equal; 
            if (StrUtil.inStr(ownerPattern, OmcFoundationConstant.OQL_ALL) > 0) oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Like; 
            createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[owner]").toString(),oqlOperator,ownerPattern,processLevel+1,testMode);
        }
        
        if (!StrUtil.isEmpty(checkouterPattern   ) && !checkouterPattern.equals   (OmcFoundationConstant.OQL_ALL)){
            oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Equal; 
            if (StrUtil.inStr(checkouterPattern, OmcFoundationConstant.OQL_ALL) > 0) oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Like; 
            createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[checkouter]").toString(),oqlOperator,checkouterPattern,processLevel+1,testMode);
        }
        
        if (!StrUtil.isEmpty(lockerPattern   ) && !lockerPattern.equals   (OmcFoundationConstant.OQL_ALL)){
            oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Equal; 
            if (StrUtil.inStr(lockerPattern, OmcFoundationConstant.OQL_ALL) > 0) oqlOperator = OmcFoundationConstant.OQL_OPERATOR_Like; 
            createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[locker]").toString(),oqlOperator,lockerPattern,processLevel+1,testMode);
        }
        if(firstOnly     ) createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[nextObid]").toString()      ,OmcFoundationConstant.OQL_OPERATOR_Equal     ,"1",processLevel+1,testMode);
        if(latestOnly    ) createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[previousObid]").toString()  ,OmcFoundationConstant.OQL_OPERATOR_Equal     ,"1",processLevel+1,testMode);
        if(lockedOnly    ) createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[locker]").toString()        ,OmcFoundationConstant.OQL_OPERATOR_NotEqual  ,"1",processLevel+1,testMode);
        if(checkoutedOnly) createParameterForOql(patternWhereOut,patternParameterOut,(new StringBuffer(aliasNew)).append(".[checkouter]").toString()    ,OmcFoundationConstant.OQL_OPERATOR_NotEqual  ,"1",processLevel+1,testMode);
        
        OmcComUtility.logWriteEnd(processLevel, testMode);
	}
    private static void createParameterForOql(StringBuffer wherePattern, StringBuffer parameterList, String attributeName, String oqlOperator, String attributeValue, int processLevel, boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        String strVariable = EtcUtil.getParamter();
        
        if(!StrUtil.isEmpty(wherePattern)) wherePattern.append(OmcFoundationConstant.ATTRIBUTE_DELIMINATOR_NAME);
        wherePattern.append(attributeName).append(oqlOperator).append(OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT).append(strVariable).append(OmcFoundationConstant.OQL_PARAMETER_VALUE_RIGHT);
        StringBuffer tempValue = new StringBuffer();
        
        if(oqlOperator.equals(OmcFoundationConstant.OQL_OPERATOR_In) || oqlOperator.equals(OmcFoundationConstant.OQL_OPERATOR_NotIn)){
            tempValue.append("('").append(StrUtil.replaceAll(attributeValue,",","','")).append("')");
        }
        else{
            tempValue.append("'").append(attributeValue).append("'");
        }
        addAttribute(parameterList,strVariable,tempValue.toString(),processLevel+1,testMode);
        OmcComUtility.logWriteEnd(processLevel, testMode);
    }
	private static void createInnerParameterForOql(StringBuffer wherePattern, StringBuffer parameterList, String attributeName, String attributeValue, int processLevel, boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    String[] value = attributeValue.split(",");
	    StringBuffer wherePatternTemp = new StringBuffer();
	    StringBuffer oqlOperator      = new StringBuffer();
	    String strVariable      = "";
	    for(String str : value)
	    {  
	        if(!StrUtil.isEmpty(str))
	        {
	            strVariable = EtcUtil.getParamter();
	            if(!StrUtil.isEmpty(wherePatternTemp)) wherePatternTemp.append(OmcFoundationConstant.OQL_INNER_SEPERATOR);
	            oqlOperator.setLength(0);
	            if(StrUtil.inStr(str, OmcFoundationConstant.OQL_ALL) > 0) oqlOperator.append(OmcFoundationConstant.OQL_OPERATOR_Like); else oqlOperator.append(OmcFoundationConstant.OQL_OPERATOR_Equal);
                wherePatternTemp.append(attributeName).append(oqlOperator).append(OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT).append(strVariable).append(OmcFoundationConstant.OQL_PARAMETER_VALUE_RIGHT);
                
                addAttribute(parameterList,strVariable.toString(),(new StringBuffer()).append("'").append(str).append("'").toString(),processLevel+1,testMode);
	        }
	    }
	    if(!StrUtil.isEmpty(wherePatternTemp))
	    {
	        if(!StrUtil.isEmpty(wherePattern)) wherePattern.append(OmcFoundationConstant.ATTRIBUTE_DELIMINATOR_NAME);
	        wherePattern.append("(").append(wherePatternTemp).append(")");
	    }
	    OmcComUtility.logWriteEnd(processLevel, testMode);
	}
    private static void addAttribute(StringBuffer attributeList, String attributeName, String attributeValue, int processLevel, boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        if(!StrUtil.isEmpty(attributeList)) attributeList.append(OmcFoundationConstant.ATTRIBUTE_DELIMINATOR_NAME);
        
        attributeList.append(attributeName).append(OmcFoundationConstant.ATTRIBUTE_DELIMINATOR_VALUE).append(attributeValue);
        OmcComUtility.logWriteEnd(processLevel, testMode);
    }
    public static String makeRelatedAPIKeyValue(String patternRelationshipIn ,String patternDirentionIn, String patternClassFilterIn, String patternSelectIn, String patternWhereIn,String patternParameterIn,String classListIn, int processLevel, boolean testMode)
    
    {
        return(getRelationAPIKeyValueSub("RelatedObject",patternRelationshipIn,patternDirentionIn,patternClassFilterIn,patternSelectIn,patternWhereIn,patternParameterIn,classListIn,processLevel+1,testMode));
    }
    public static String makeFindObjectAPIKeyValue(String className ,String patternSelectIn,String patternWhereIn,String patternParameterIn,int processLevel, boolean testMode)
	{
        if(className == null) className = "";
        if(patternSelectIn == null) patternSelectIn = "";
        if(patternWhereIn == null) patternWhereIn = "";
        if(patternParameterIn == null) patternParameterIn = "";
	    String whereSelectFormat = getAPIWhereSelectFormatKey(patternSelectIn,patternWhereIn,patternParameterIn,processLevel+1,testMode);
	    StringBuffer keyValue = new StringBuffer();
	    keyValue.append(className.trim()).append(OmcFoundationConstant.OQL_KEY_VALUE_Seperator);
	    if(!StrUtil.isEmpty(whereSelectFormat)) keyValue.append(OmcFoundationConstant.OQL_KEY_VALUE_Seperator).append(whereSelectFormat);
	    OmcComUtility.logWriteEnd(processLevel, testMode);
		return(keyValue.toString());
	}
    public static String makeRelationAPIKeyValue(final String patternRelationshipIn ,final String patternDirentionIn, final String patternClassFilterIn, final String patternSelectIn, final String patternWhereIn,final String patternParameterIn,final String classListIn,int processLevel, boolean testMode)
    {
        return(getRelationAPIKeyValueSub("RelationShip",patternRelationshipIn,patternDirentionIn,patternClassFilterIn,patternSelectIn,patternWhereIn,patternParameterIn,classListIn,processLevel+1,testMode));
    }  
    private static String getRelationAPIKeyValueSub(String apiType,String patternRelationshipIn ,String patternDirentionIn, String patternClassFilterIn, 
            String patternSelectIn, String patternWhereIn,String patternParameterIn,String classListIn,int processLevel, boolean testMode)
    {
        StringBuffer keyValue = new StringBuffer(apiType);
        if(patternRelationshipIn == null) patternRelationshipIn = "";
        if(patternDirentionIn == null) patternDirentionIn = "";
        if(patternClassFilterIn == null) patternClassFilterIn = "";
        if(patternSelectIn == null) patternSelectIn = "";
        if(patternWhereIn == null) patternWhereIn = "";
        if(patternParameterIn == null) patternParameterIn = "";
        if(classListIn == null) classListIn = "";
        
        String whereSelectFormat = getAPIWhereSelectFormatKey(patternSelectIn,patternWhereIn,patternParameterIn,processLevel+1,testMode);
        keyValue.append(patternRelationshipIn.trim());
        keyValue.append(OmcFoundationConstant.OQL_KEY_VALUE_Seperator).append(patternDirentionIn.trim());
        keyValue.append(OmcFoundationConstant.OQL_KEY_VALUE_Seperator).append(patternClassFilterIn.trim());
        keyValue.append(OmcFoundationConstant.OQL_KEY_VALUE_Seperator).append(classListIn.trim());
        if(!StrUtil.isEmpty(whereSelectFormat)) keyValue.append(OmcFoundationConstant.OQL_KEY_VALUE_Seperator).append(whereSelectFormat);
        return(keyValue.toString());
    }   
    public static String makeSearchAPIKeyValue(final String className ,final String patternSelectIn,final String patternFromIn,final String patternWhereIn,final String patternParameterIn,int processLevel, boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);

        String patternSelect = patternSelectIn;
        String patternFrom = patternFromIn;
        String patternWhere = patternWhereIn;
        String patternParameter = patternParameterIn;
        
        if(patternSelect == null) patternSelect = "";
        if(patternFrom == null) patternFrom = "";
        if(patternWhere == null) patternWhere = "";
        if(patternParameter == null) patternParameter = "";
        String whereSelectFormat = getAPIWhereSelectFormatKey(patternParameter,patternWhere,patternParameter,processLevel+1,testMode);
        StringBuffer keyValue = new StringBuffer();
        keyValue.append(className.trim()).append(OmcFoundationConstant.OQL_KEY_VALUE_Seperator).append(patternSelect.trim());
        keyValue.append(OmcFoundationConstant.OQL_KEY_VALUE_Seperator).append(patternFromIn.trim());
        if(!StrUtil.isEmpty(whereSelectFormat)) keyValue.append(OmcFoundationConstant.OQL_KEY_VALUE_Seperator).append(whereSelectFormat);
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(keyValue.toString());
    }
    private static String getAPIWhereSelectFormatKey(final String patternSelectIn, final String patternWhereIn, final String patternParameterIn, int processLevel, boolean testMode)
    {
        String key = EtcUtil.getKeyValueForWhereSelect(patternSelectIn,patternWhereIn,patternParameterIn);
        return key;
    }
	public static void seperateParameterAndWhere(final String patternWhereIn         ,final String patternParameterIn, 
                                                 StringBuffer patternWhereOut        , StringBuffer patternParameterOut,
                                                 StringBuffer patternWhereSubQueryOut, StringBuffer patternParameterSubQueryOut,
                                                 int processLevel, boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    patternWhereOut.setLength(0);patternParameterOut.setLength(0);patternWhereSubQueryOut.setLength(0);patternParameterSubQueryOut.setLength(0);
	    if(NullUtil.isNone(patternWhereIn)) return;
	    String patternWhere           = patternWhereIn.replace(OmcFoundationConstant.OQL_INNER_SEPERATOR, OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
	    String[] splitedpatternWhere  = patternWhere.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME));
        String[] splitedpatternParameter  = patternParameterIn.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME));
        
	    for(int i =0; i < splitedpatternWhere.length; i++)
	    {
	        if(!NullUtil.isNone(splitedpatternWhere[i])){
	            if(OmcUtility.isSubQueryFormat(splitedpatternWhere[i])){
	                if(!StrUtil.isEmpty(patternWhereSubQueryOut)){
	                    patternWhereSubQueryOut.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
	                    patternParameterSubQueryOut.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
	                 }
	                patternWhereSubQueryOut.append(splitedpatternWhere[i]);
	                patternParameterSubQueryOut.append(splitedpatternParameter[i]);             
	            }	            
	        }
	    }
	    splitedpatternWhere  = patternWhereIn.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME));
	    for(int i =0; i < splitedpatternWhere.length; i++)
	    {
	        if(!NullUtil.isNone(splitedpatternWhere[i]) && !splitedpatternWhere[i].startsWith("omcDummyParameter")){
	            if(!OmcUtility.isSubQueryFormat(splitedpatternWhere[i])){
	                if(!StrUtil.isEmpty(patternWhereOut)) patternWhereOut.append(OmcFoundationConstant.OQL_OPERATOR_And);
	                String[]  innerpatternWhere  = splitedpatternWhere[i].split(Pattern.quote(OmcFoundationConstant.OQL_INNER_SEPERATOR));
	                StringBuffer inBuf = new StringBuffer();
	                for(int j = 0; j < innerpatternWhere.length; j++){
	                    if(j > 0) inBuf.append(OmcFoundationConstant.OQL_OPERATOR_Or);
	                    inBuf.append(innerpatternWhere[j]);
	                }
	                patternWhereOut.append(inBuf);
	            } 
	        }
	    }
	    patternParameterOut.append(patternParameterIn);
	    OmcComUtility.logWriteEnd(processLevel, testMode);
	}
	
	public static ArrayList<OmcOQLPatternFrom> getOQLPatternFromList(String patternFromIn,int processLevel, boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
		ArrayList<OmcOQLPatternFrom> patternFromList = new ArrayList<OmcOQLPatternFrom>();
		OmcOQLPatternClass patternClassFrom = null;
		OmcOQLPatternClass patternClassTo   = null;
		String connectedType = null;
		if (StrUtil.inStr(patternFromIn, "+") == 0 && StrUtil.isEmpty(StrUtil.getStringForFromTo(patternFromIn, 1, ">", "<")))
		{
			connectedType = "Only One";
			patternClassFrom = convertStr2FromPattern(StrUtil.replaceAll(StrUtil.replaceAll(patternFromIn,"<",""),">",""),processLevel+1,testMode);
			patternFromList.add(new OmcOQLPatternFrom(patternClassFrom,connectedType,patternClassFrom));
		}
		else
		{
			String[] splitedFromPattern = patternFromIn.split(Pattern.quote(OmcFoundationConstant.OQL_SELECTION_Seperator));
			for(int i = 0; i < splitedFromPattern.length;i++)
			{
				connectedType = StrUtil.getStringForFromTo(splitedFromPattern[i], 1, OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_RIGHT, OmcFoundationConstant.OQL_SUBQUERY_CLSATTR_LEFT);
				if (StrUtil.isEmpty(connectedType)) OmcComUtility.error("[Foundation.omcUtility.getOQLPatternFromSet]Invalid Usage for connection.");
				String[] splitedFromTo = splitedFromPattern[i].split(Pattern.quote(connectedType));
				if (splitedFromTo.length != 2) OmcComUtility.error("[Foundation.omcUtility.getOQLPatternFromSet]Invalid Conneceted Type.");
				patternClassFrom = convertStr2FromPattern(StrUtil.replaceAll(StrUtil.replaceAll(splitedFromTo[0],"<",""),">",""),processLevel+1,testMode);
				patternClassTo   = convertStr2FromPattern(StrUtil.replaceAll(StrUtil.replaceAll(splitedFromTo[1],"<",""),">",""),processLevel+1,testMode);
				patternFromList.add(new OmcOQLPatternFrom(patternClassFrom,connectedType,patternClassTo));
			}
		}
		OmcComUtility.logWriteEnd(processLevel, testMode);
		return(patternFromList);
	}
	private static OmcOQLPatternClass convertStr2FromPattern(String patternFromIn,int processLevel, boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
		String className   = StrUtil.getStringForFromTo(patternFromIn, 1, OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT, OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT).trim();
		String patternFrom = StrUtil.replaceAll(patternFromIn, OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT + className + OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT, "").trim();
		String alias       = null;
		int pos = StrUtil.inStr(patternFrom, OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator);
		if (pos > 0)
		{
			alias = StrUtil.subStr(patternFrom, pos+OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator.length()).trim();
		}
		else
		{
			alias = "";
		}
		List<String> classList = ClassInfoUtil.getInstantiableChildList(className);
		List<String> dbmsTableList = ClassInfoUtil.getDbmsTableList(className);
		
		OmcComUtility.logWriteEnd(processLevel, testMode);
		return(new OmcOQLPatternClass(classList,dbmsTableList,alias));
	}

	public static ArrayList<OmcOQLPatternClass> getFromClassSet(ArrayList<OmcOQLPatternFrom> patternFromList,int processLevel,boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
		ArrayList<OmcOQLPatternClass> list = new ArrayList<OmcOQLPatternClass>();
		Map<String,Object> map = new HashMap<String,Object>();
		for(OmcOQLPatternFrom patternFrom:patternFromList){
		    map.put(patternFrom.getFromClassPattern().getAlias(), patternFrom.getFromClassPattern());
		    map.put(patternFrom.getToClassPattern().getAlias(), patternFrom.getToClassPattern());
		}
		for(String key : map.keySet()){
		    list.add((OmcOQLPatternClass)map.get(key));
		}
		OmcComUtility.logWriteEnd(processLevel, testMode);
	    return(list);
	}
	public static String createWhereClause(final ArrayList<OmcOQLPatternClass> patternClassList,String patternWhereIn,String patternWParameterIn, String specialFunction, boolean parameterReplace,int processLevel,boolean testMode){
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    
	    if(NullUtil.isNone(patternWhereIn)) return patternWhereIn;
	    ArrayList<OmcOQLPatternAlias> patternAliasList = getOQLPatternAliasSet(patternWhereIn,processLevel+1,testMode);
	    
	    String       returnWhere = patternWhereIn;
	    StringBuffer tempFrom = new StringBuffer();
	    StringBuffer tempTo   = new StringBuffer();
        
	    StringBuffer buff = new StringBuffer();
	    String dbmsColumn = "..";
	    
	    for (OmcOQLPatternClass patternClass:patternClassList){
            buff.setLength(0);buff.append(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator).append(patternClass.getAlias());
	        for(OmcOQLPatternAlias patternAlias:patternAliasList){
	            if(patternAlias.getClassAlias().equals(buff.toString().trim())){
	                dbmsColumn = getDbmsColumnName(patternClass.getClassNameList(),patternClass.getDbmsTableList(),patternAlias.getClassAttribute(),patternClass.getAlias(),processLevel+1,testMode);
                    if(!StrUtil.isEmpty(dbmsColumn)){
                        String[] strArray = dbmsColumn.split(Pattern.quote(OmcFoundationConstant.OQL_DBMS_COLUMN_INFO_SEPERATOR));
                        dbmsColumn = strArray[0];
                        dbmsColumn = StrUtil.replace(patternAlias.getClassAlias(), OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator, "").trim() + "." + dbmsColumn.trim();
                        tempFrom.setLength(0);tempTo.setLength(0);
                        tempFrom.append(patternAlias.getClassAlias()).append(".").append("[").append(patternAlias.getClassAttribute()).append("]");
                        if(!StrUtil.isEmpty(specialFunction)){
                            tempTo.append  (specialFunction).append("(").append(dbmsColumn).append(")");
                        }
                        else{
                            tempTo.append  (dbmsColumn);
                        }
                        returnWhere = returnWhere.replace(tempFrom.toString(),tempTo.toString());
                    }
	            }
	        }
	    }
	    returnWhere = replaceOperator(returnWhere);
        if(parameterReplace){
            ArrayList<OmcOQLVariableParameter> parmValueList = convertStr2ParameterTable(patternWParameterIn,OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME,OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE,processLevel+1,testMode); 
            for(OmcOQLVariableParameter parm: parmValueList){
                String tempStr = OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT + parm.getParameterName() + OmcFoundationConstant.OQL_PARAMETER_VALUE_RIGHT;
                returnWhere = returnWhere.replace(tempStr, parm.getParameterValue());
            }
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
	    return(returnWhere);
	}
	public static String getDbmsColumnName(List<String> classNameList, List<String> tableNameList, String attributeName, String classAlias, int processLevel,boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    
	    List<OmcOQLClassAttribute> classAttributeList = getAllClassAttributeList(classNameList,tableNameList,attributeName,processLevel+1,testMode);
	    
	    Map<String,Object> map = new HashMap<String,Object>();
	    boolean isAllAttr = false;
	    if(attributeName.equals("*")) isAllAttr = true;
	    for(OmcOQLClassAttribute classAttribute : classAttributeList){
	        if(isAllAttr || classAttribute.getAttributeName().equals(attributeName))
	        {
	            map.put(classAttribute.getAttributeName(), classAttribute);
	        }
	    }
	    List<OmcOQLClassAttribute>  dataList = new ArrayList<OmcOQLClassAttribute>();
	    for(String strKey : map.keySet()){
	        dataList.add((OmcOQLClassAttribute)map.get(strKey));
	    }
        
        StringBuffer rtnBuf1 = new StringBuffer();
        StringBuffer rtnBuf2 = new StringBuffer();
        if(attributeName.equals(OmcFoundationConstant.OQL_ATTRIBUTE_All))
        {
            String aliasTemp = classAlias.replace(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator, "").toLowerCase();
            int dataListSize = dataList.size();
            StringBuffer sqlAlias = new StringBuffer();
            for (int i = 0; i < dataListSize; i++)
            {
                sqlAlias.setLength(0);
                if(i > 0) {rtnBuf1.append(",");rtnBuf2.append(",");}
                sqlAlias.append(aliasTemp).append(OmcFoundationConstant.OQL_SELPATTERN_Seperator).append(dataList.get(i).getAttributeName());
                rtnBuf1.append(aliasTemp).append(".").append(dataList.get(i).getDbmsColumnName()).append(" as \"").append(sqlAlias).append("\"");
                switch(dataList.get(i).getDataType())
                {
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_STRING:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_INTEGER:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_FLOAT:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_DATE:
                        rtnBuf2.append(OmcDBMSConstants.DBMS_UTC2LOCAL_FUNCTION_CHAR).append("(\"").append(sqlAlias).append("\",'").append(OmcSystemConstants.OMC_DBMS_DATE_FORMAT).append("') as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN:
                        rtnBuf2.append("case when ").append("\"").append(sqlAlias).append("\" = 1 then 'TRUE' else 'FALSE' end as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_LONG:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_DOUBLE:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_BIGDECIMAL:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_ATTRIBUTESET:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_ARRAY:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_FILE:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        break;
                    case OmcSystemConstants.SCHEMA_DATA_TYPE_USERID:
                        rtnBuf2.append("\"").append(sqlAlias).append("\" as \"").append(sqlAlias).append("\"");
                        rtnBuf2.append(",");
                        rtnBuf2.append("omcGetUserTitles(\"").append(sqlAlias).append("\") as \"").append(BaseFoundationUtil.convert2SemiCamelCase(sqlAlias.toString())).append("UserTitles\"");
                        rtnBuf2.append(",");
                        rtnBuf2.append("omcGetUserObid(\"").append(sqlAlias).append("\") as \"").append(BaseFoundationUtil.convert2SemiCamelCase(sqlAlias.toString())).append("UserObid\"");
                        break;
                    default:
                        OmcComUtility.error("[Foundation]Not Defined Data Type(" + dataList.get(i).getDataType() + ").");
                }
                if(Bit.isInclude(dataList.get(i).getFlags(),OmcSystemConstants.SYSCLASSATTR_FLAG_IsNameAttribute)){
                    String nameQuerystr = getSelectStrForNameAttr(dataList.get(i).getValueSettingInfo(),sqlAlias.toString(),sqlAlias.toString());
                    if(!NullUtil.isNone(nameQuerystr)){
                        if(!StrUtil.isEmpty(rtnBuf2)) rtnBuf2.append(",");
                        rtnBuf2.append(nameQuerystr.replace("#{omcDbColumn}", "PTITLES").replace("#omcAttr", "Titles"));
                        if(!StrUtil.isEmpty(rtnBuf2)) rtnBuf2.append(",");
                        rtnBuf2.append(nameQuerystr.replace("#{omcDbColumn}", "OBID").replace("#omcAttr", "Obid"));
                    }
                }
            }
            rtnBuf1.append(OmcFoundationConstant.OQL_FISRT_LAST_SQL_SEPERATOR).append(rtnBuf2);
        }
        else
        {
            if (dataList.size() > 0)
            {
                rtnBuf1.append(dataList.get(0).getDbmsColumnName());
                rtnBuf1.append(OmcFoundationConstant.OQL_DBMS_COLUMN_INFO_SEPERATOR).append(dataList.get(0).getDataType());
                rtnBuf1.append(OmcFoundationConstant.OQL_DBMS_COLUMN_INFO_SEPERATOR).append(dataList.get(0).getFlags());
                rtnBuf1.append(OmcFoundationConstant.OQL_DBMS_COLUMN_INFO_SEPERATOR).append(dataList.get(0).getValueSettingInfo());
            }
        }
	    return(rtnBuf1.toString());
	}
	public static String getSelectStrForNameAttr(String valueSettingInfo, String dbmsColumn, String alias ){
	    String strClass = valueSettingInfo;
	    StringBuffer strBuf = new StringBuffer();
        if(!NullUtil.isNone(strClass)){
            if(strClass.length() > 8){
                strClass = strClass.substring(7, strClass.length()-1);
                ClassInfo classInfo = ClassInfoUtil.getClassInfo(strClass);
                if(!NullUtil.isNull(classInfo)){
                    strBuf.append("(select xxx.#{omcDbColumn} from ").append(classInfo.getDbmsTable()).append( " xxx ");
                    strBuf.append("where xxx.pnames = a.\"").append(dbmsColumn).append("\" ");
                    strBuf.append("and xxx.pclass_name in (").append(StrUtil.makeWhereInStr(classInfo.getLowerClassList())).append(")");
                    if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Business) && 
                            Bit.isInclude(classInfo.getFlags(), OmcSystemConstants.BUSINESS_FLAG_Revisible)){
                        strBuf.append("and xxx.pprevious_obid = '1'");
                    }
                    if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                        strBuf.append(" limit 1");
                    }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                        strBuf.append(" and rownum < 2");
                    }
                    strBuf.append(") as \"").append(alias).append("#omcAttr\"");
                }                
            }
        }
        return strBuf.toString();
	}
    public static List<OmcOQLClassAttribute> getAllClassAttributeList(String className, String tableName, String attributeName,int processLevel,boolean testMode)
    {
        List<String> classNameList = new ArrayList<String>();
        classNameList.add(className);
        List<String> tableNameList = new ArrayList<String>();
        tableNameList.add(tableName);
        return(getAllClassAttributeList(classNameList,tableNameList,attributeName,processLevel+1,testMode));
    }
    public static List<OmcOQLClassAttribute> getAllClassAttributeList(String className, List<String> tableNameList, String attributeName,int processLevel,boolean testMode)
    {
        List<String> classNameList = new ArrayList<String>();
        classNameList.add(className);
        return(getAllClassAttributeList(classNameList,tableNameList,attributeName,processLevel+1,testMode));
    }
	public static List<OmcOQLClassAttribute> getAllClassAttributeList(List<String> classNameList, String tableName, String attributeName,int processLevel,boolean testMode)
    {
	    List<String> tableNameList = new ArrayList<String>();
	    tableNameList.add(tableName);
	    return(getAllClassAttributeList(classNameList,tableNameList,attributeName,processLevel+1,testMode));
    }
	public static List<OmcOQLClassAttribute> getAllClassAttributeList(List<String> classNameList, List<String> tableNameList, String attributeName,int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        List<OmcOQLClassAttribute> result = new ArrayList<OmcOQLClassAttribute>();
        Set<String> classNameSet = StrUtil.convertListToSet(classNameList);
        Set<String> tableNameSet = StrUtil.convertListToSet(tableNameList);

        boolean isAll = false;
        if(attributeName.equals("*")) isAll = true;
        for(String className : classNameSet){
            ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
            if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Instantiable)){
                if(tableNameSet != null && tableNameSet.size() > 0){
                    if(tableNameSet.contains(classInfo.getDbmsTable())){
                        List<ColumnInfo> columnList = classInfo.getColumnList();
                        for(ColumnInfo colInfo : columnList){
                            if(isAll || colInfo.getAttributeName().equals(attributeName)){
                                result.add(new OmcOQLClassAttribute(className, classInfo.getDbmsTable(), colInfo.getAttributeName(), colInfo.getDbmsColumn(),colInfo.getColumnAlias(),colInfo.getDataType(),colInfo.getFlags(),colInfo.getValueSettingInfo()));
                            }
                        }
                    }                        
                }else{
                    List<ColumnInfo> columnList = classInfo.getColumnList();
                    for(ColumnInfo colInfo : columnList){
                        if(isAll || colInfo.getAttributeName().equals(attributeName)){
                            result.add(new OmcOQLClassAttribute(className, classInfo.getDbmsTable(), colInfo.getAttributeName(), colInfo.getDbmsColumn(),colInfo.getColumnAlias(),colInfo.getDataType(),colInfo.getFlags(),colInfo.getValueSettingInfo()));
                        }
                    }
                }
            }
        }
        return result;
    }
	public static List<OmcOQLClassAttribute> getClassAttributeType(List<String> classNameList, List<String> tableNameList, String attributeName, String classAlias, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        List<OmcOQLClassAttribute> classAttributeList = getAllClassAttributeList (classNameList,tableNameList,attributeName,processLevel+1,testMode);
        List<OmcOQLClassAttribute>  result = new ArrayList<OmcOQLClassAttribute>();
        for(OmcOQLClassAttribute classAttribute : classAttributeList){
            if(classAttribute.getAttributeName().equals(attributeName)){
                result.add(classAttribute);
                break;
            }
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(result);
    }
	private static ArrayList<OmcOQLVariableParameter> convertStr2ParameterTable(String strIn,String del1, String del2, int processLevel,boolean testMode)
	{
	    ArrayList<OmcOQLVariableParameter> parmValueList = new ArrayList<OmcOQLVariableParameter>();
	    OmcOQLConvertedParmValue convertedParmValue = setGetConvertedParmValue(new OmcOQLConvertedParmValue(strIn,strIn,"parmValue","%","'","'",0,new HashMap<String,String>(),"All"),strIn,"parmValue",processLevel+1,testMode);
        strIn = convertedParmValue.getStrConverted();
        
        String[] parmAndValueArray = strIn.split(Pattern.quote(del1));
        for(int i = 0; i < parmAndValueArray.length; i++){
            String[] splitVariable = parmAndValueArray[i].split(Pattern.quote(del2));
            if(splitVariable.length == 2){
                String convertedValue = revokeConvertedParmValue(splitVariable[1],convertedParmValue,processLevel+1,testMode);
                parmValueList.add(new OmcOQLVariableParameter(splitVariable[0],convertedValue,false));                
            }
        }
        return(parmValueList);
	}
	private static ArrayList<OmcOQLPatternAlias> getOQLPatternAliasSet(String strIn,int processLevel,boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    ArrayList<OmcOQLPatternAlias> patternAliasList = new ArrayList<OmcOQLPatternAlias>();
	    
	    OmcOQLConvertedParmValue convertedParmValue = setGetConvertedParmValue(new OmcOQLConvertedParmValue(strIn,strIn,"where","%","'","'",0,new HashMap<String,String>(),"All"),strIn,"where",processLevel+1,testMode);
	    strIn = convertedParmValue.getStrConverted();
	    
        int pos1 = StrUtil.inStr(strIn, OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator);
        int pos2 = 0;
        String alias = null;
        String attribute = null;
        
        while(pos1 > 0)
        {
            pos2 = StrUtil.inStr(strIn, ".", pos1,1);
            if (pos2 == 0) break;
            alias = StrUtil.subStr(strIn, pos1, pos2-pos1);
            attribute = StrUtil.getStringForFromTo(strIn, pos2+1, "[", "]");
            strIn = StrUtil.subStr(strIn, pos2+1);
            patternAliasList.add(new OmcOQLPatternAlias(alias,attribute,-1));
            pos1 = StrUtil.inStr(strIn, OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator);
        }
	    OmcComUtility.logWriteEnd(processLevel, testMode);
	    return(patternAliasList);
	}
	public static ArrayList<OmcOQLVariableParameter> convertVariableParameterList(final String patternWhereIn, final String patternParameterInNew,int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);

        if(NullUtil.isNone(patternWhereIn) && NullUtil.isNone(patternParameterInNew)) return new ArrayList<OmcOQLVariableParameter>();

        Map<String,String> parmMap = convertParmMap(patternParameterInNew);
        String patternWhere = patternWhereIn;
        patternWhere = patternWhere.replace(OmcFoundationConstant.OQL_OPERATOR_Or , OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
        patternWhere = patternWhere.replace(OmcFoundationConstant.OQL_OPERATOR_And, OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
        String[] splitedWhereNew = splitStr2Table4000For2Del(patternWhere,OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME,OmcFoundationConstant.OQL_INNER_SEPERATOR,processLevel+1, testMode);        
            
        ArrayList<OmcOQLVariableParameter> variableParamList = new ArrayList<OmcOQLVariableParameter>();

        for (int i = 0; i < splitedWhereNew.length; i++){
            if(!NullUtil.isNone(splitedWhereNew[i])){
                boolean isFunction = false;
                if(splitedWhereNew[i].indexOf(OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT + OmcSystemConstants.PARAMETER_PREFIX,1) != -1){
                    String operator = getOperator(splitedWhereNew[i]);
                    if(StrUtil.in(operator, OmcFoundationConstant.OQL_OPERATOR_ALL_Set)){
                        if(OmcFoundationConstant.OQL_OPERATOR_In.equals(operator) || OmcFoundationConstant.OQL_OPERATOR_NotIn.equals(operator)){
                            isFunction = true;
                        }
                        variableParamList.add(getParamterValue(splitedWhereNew[i],parmMap,isFunction));
                    }else{
                        OmcComUtility.error("[Foundation]Where Pattern operator Error");
                    }
                }else{
                    LOGGER.debug("Not Where Pattern{}",splitedWhereNew[i]);
                }
            }
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(variableParamList);
    }
	
	private static Map<String,String> convertParmMap(final String patternParameter){
	    Map<String,String> parmMap = new HashMap<String,String>();
	    if(NullUtil.isNone(patternParameter)) return parmMap;
	    String[] splitedParm  = patternParameter.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME));
	    for(int i = 0; i < splitedParm.length; i++){
	        String[] parmNewArray = splitedParm[i].split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE));
	        parmMap.put(parmNewArray[0], parmNewArray[1]);
	    }
	    return parmMap;
	}
	private static OmcOQLVariableParameter getParamterValue(final String patternWhere, Map<String,String> parmValueMap, boolean isFunction){
	    if(NullUtil.isNone(patternWhere)) throw new FoundationException("[Foundation]Where Pattern is empty!!");
        for(String key: parmValueMap.keySet()){
            if(patternWhere.indexOf(key) != -1){
                return new OmcOQLVariableParameter(key,parmValueMap.get(key),isFunction);
            }
        }
        throw new FoundationException("[Foundation]No Parameter value for Where Pattern(" + patternWhere + ").");
    }
	public static ArrayList<OmcOQLVariableParameter> makeParameterList(final String patternParameterIn)
    {
        if(StrUtil.isEmpty(patternParameterIn)) return new ArrayList<OmcOQLVariableParameter>();
        String[] splitedParm  = patternParameterIn.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME));
        ArrayList<OmcOQLVariableParameter> variableParamList = new ArrayList<OmcOQLVariableParameter>();
            for (int i = 0; i < splitedParm.length; i++){
                String[] parArray = splitedParm[i].split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE));
                variableParamList.add(new OmcOQLVariableParameter(parArray[0],parArray[1],false));
            }
        return(variableParamList);
    }
	private static String getOperator(final String strIn){
        int pos = strIn.indexOf(OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT + OmcSystemConstants.PARAMETER_PREFIX,1);
        String  str = strIn.substring(pos-2, pos);
        return str;
    }
	private static String[] splitStr2Table4000For2Del(final String strIn,final String del1, final String del2, int processLevel,boolean testMode )
	{
	    String strTemp = strIn;
	    if(strTemp == null) strTemp = "";
	    String[] splitedStr = strTemp.split(Pattern.quote(del1));
	    ArrayList<String> rnt =  new ArrayList<String>(); 
	    for(String str : splitedStr)
	    {
	        if(StrUtil.inStr(str, del2) > 0)
	        {
	            String[] splitedTempStr = str.split(Pattern.quote(del2));
	            for(String tempStr : splitedTempStr)
	            {
	                rnt.add(tempStr);
	            }
	        }
	        else
	        {
	            rnt.add(str);
	        }
	    }
        String[] str = new String[rnt.size()];
        str = rnt.toArray(str);
        return(str);
	}
	
	public static HashMap<String, Object> getParsedOQLPatternFromInfo(String patternFromStrIn, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<OmcOQLPatternSplitedFrom> splitedFromList   = new ArrayList<OmcOQLPatternSplitedFrom>();
        List<OmcOQLPatternFrom>        fromList          = new ArrayList<OmcOQLPatternFrom>();
        List<String>                   resultPathList    = new ArrayList<String>();
        
        fromList  = makeOQLPatternFromSet(patternFromStrIn,processLevel+1,testMode);
        int idxMain = 0;
        String uniqueStr = null;String uniqueStrParent = null;
        List<OmcForDynamicLoop> forDynamicLoopList = new ArrayList<OmcForDynamicLoop>();
        
        for (OmcOQLPatternFrom patternFrom : fromList)
        {
            List<String> tableLeftList = patternFrom.getFromClassPattern().getDbmsTableList();
            int idxLeft = 0;int idxRight = 0;
            List<String> uniqueList= new ArrayList<String>();
            
            uniqueStrParent = StrUtil.LPAD(Integer.toString(idxMain+1), 5, "0");
            for( idxLeft = 0 ;  idxLeft < tableLeftList.size() ; idxLeft++)
            {
                if(patternFrom.getConnectedType().equals("Only One"))
                {
                    uniqueStr = uniqueStrParent + StrUtil.LPAD(Integer.toString(idxLeft+1), 5, "0") + StrUtil.LPAD(Integer.toString(idxRight+1), 5, "0");
                    OmcOQLPatternSplitedFrom splitedFrom  = new OmcOQLPatternSplitedFrom(uniqueStr, //String uniqueString,
                                                                                         uniqueStrParent,//String uniqueStringParent, 
                                                                                         patternFrom.getFromClassPattern().getDbmsTableList().get(idxLeft),
                                                                                         StrUtil.convertList2Str(patternFrom.getFromClassPattern().getClassNameList()),//String leftClassList,
                                                                                         patternFrom.getFromClassPattern().getAlias(),//String leftAlias, 
                                                                                         patternFrom.getConnectedType(),//String connectedType, 
                                                                                         patternFrom.getFromClassPattern().getDbmsTableList().get(idxLeft),
                                                                                         StrUtil.convertList2Str(patternFrom.getFromClassPattern().getClassNameList()),//String rightClassList, 
                                                                                         patternFrom.getFromClassPattern().getAlias());//String rightAlias) {);
                    splitedFromList.add(splitedFrom);
                    uniqueList.add(uniqueStr);
                }
                else
                {
                    List<String> tableRightList = patternFrom.getToClassPattern().getDbmsTableList();
                    for( idxRight = 0 ;  idxRight < tableRightList.size() ; idxRight++)
                    {
                        uniqueStr = uniqueStrParent + StrUtil.LPAD(Integer.toString(idxLeft+1), 5, "0") + StrUtil.LPAD(Integer.toString(idxRight+1), 5, "0");
                        OmcOQLPatternSplitedFrom splitedFrom  = new OmcOQLPatternSplitedFrom(uniqueStr, //String uniqueString,
                                                                                             uniqueStrParent,//String uniqueStringParent, 
                                                                                             patternFrom.getFromClassPattern().getDbmsTableList().get(idxLeft),
                                                                                             StrUtil.convertList2Str(patternFrom.getFromClassPattern().getClassNameList()),//String leftClassList,
                                                                                             patternFrom.getFromClassPattern().getAlias(),//String leftAlias, 
                                                                                             patternFrom.getConnectedType(),//String connectedType, 
                                                                                             patternFrom.getToClassPattern().getDbmsTableList().get(idxRight),
                                                                                             StrUtil.convertList2Str(patternFrom.getToClassPattern().getClassNameList()),//String rightClassList, 
                                                                                             patternFrom.getToClassPattern().getAlias());//String rightAlias) {);
                        splitedFromList.add(splitedFrom);
                        uniqueList.add(uniqueStr);
                    }
                }
            }
            forDynamicLoopList.add(new OmcForDynamicLoop(uniqueStrParent,uniqueList));
            idxMain++;
        }
        String[] values = new String[forDynamicLoopList.size()];
        List<String>    queryPathList     = new ArrayList<String>();
        CreateCombinations(forDynamicLoopList, 0, values,queryPathList);
        resultPathList.addAll(queryPathList);
        
        map.put(OmcFoundationConstant.OQL_MAPPARM_PARSED_SPLITED_FROM, splitedFromList);
        map.put(OmcFoundationConstant.OQL_MAPPARM_PARSED_FROM, fromList);
        map.put(OmcFoundationConstant.OQL_MAPPARM_PARSED_QUERY_PATH, resultPathList);
        return(map);
    }
    private static void CreateCombinations(List<OmcForDynamicLoop> forDynamicLoopList, int index, String[] values, List<String> pathList) {
        OmcForDynamicLoop forDynamicLoop = forDynamicLoopList.get(index);
        for (String v : forDynamicLoop.getValues()) {
            values[index] = v;
            if (index < forDynamicLoopList.size() - 1) {
                CreateCombinations(forDynamicLoopList, index+1, values,pathList);
            } else {
                StringBuilder sb = new StringBuilder(values[0]);
                for (int i = 1; i < values.length; ++i) {
                    sb.append(".").append(values[i]);
                }
                pathList.add(sb.toString());
            }
        }
    }
	private static ArrayList<OmcOQLPatternFrom> makeOQLPatternFromSet(final String patternFromIn,int processLevel,boolean testMode)
	{
	    String fromPattern = patternFromIn;
	    if(fromPattern == null) fromPattern = "";
	    fromPattern = fromPattern.replace(" ", "");
	    if(StrUtil.subStr(fromPattern, -1).equals("+")) fromPattern = StrUtil.subStr(fromPattern,1,fromPattern.length()-1);
	    return(getOQLPatternFromList(fromPattern,processLevel+1,testMode));
	}
    public static ArrayList<OmcOQLPatternSQueryResult> getOQLPatternSQueryRsltList(String strIn, String subQueryType, OmcOQLParsedWhereSQuery parsedWhereSubQuery,int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        String whereParameter = "";
        String whereOperator  = "";
        String whereValue     = "";

        ArrayList<OmcOQLPatternSQueryResult> subQueryResultList = new ArrayList<OmcOQLPatternSQueryResult>();
        if (parsedWhereSubQuery != null) 
        {
            whereParameter = parsedWhereSubQuery.getWhereParameter();
            whereOperator  = parsedWhereSubQuery.getWhereOperator();
            whereValue     = parsedWhereSubQuery.getWhereValue();
        }

        String[] splitedStr = strIn.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE));

        for(String str : splitedStr)
        {
            OmcOQLSubQueryJoin subQueryJoin = OmcUtility.resolveSubQueryJoin(str,processLevel+1,testMode);
                        
            String thisJoinPattern = subQueryJoin.getThisJoinPattern();
            if(thisJoinPattern.indexOf("fromObid"              ) >= 0) thisJoinPattern = StrUtil.replace(thisJoinPattern.toString(), "fromObid"              ,"from_obid");
            if(thisJoinPattern.indexOf("toObid"                ) >= 0) thisJoinPattern = StrUtil.replace(thisJoinPattern.toString(), "toObid"                ,"to_obid");
            
            OmcOQLPatternSQueryResult patternSQueryResult = getPatternSQueryResult(subQueryResultList,subQueryJoin.getRelationship(),processLevel+2,testMode);
            
            if(!NullUtil.isNull(patternSQueryResult))
            {
                patternSQueryResult.setOriginQueryPattern(subQueryJoin.getSubQuery());
                patternSQueryResult.setAttributeName(subQueryJoin.getAttributeName());
                patternSQueryResult.setSelectAlias(subQueryJoin.getSpecialAlias());
                patternSQueryResult.createSelectClause(processLevel+1, testMode);
                OmcOQLSelectClauseVO selectClauseVO = OmcUtility.createSelectClause(patternSQueryResult.getPatternClassList(),
                                                                         patternSQueryResult.getSelectStr(),
                                                                         false,
                                                                         false,
                                                                         processLevel+1,testMode);
                
                patternSQueryResult.setSelectStr(selectClauseVO.getFirstSelectStr());
                patternSQueryResult.createSqlAll(processLevel+1,testMode);
                subQueryResultList.add(patternSQueryResult);
            }
            else
            {
                ArrayList<OmcOQLPatternSQueryTable> patternSQuerytableList = getOQLPatternSQueryTableList(subQueryJoin.getSubQuery(),processLevel+1,testMode);
                patternSQueryResult = new OmcOQLPatternSQueryResult (   subQueryType                    ,//subQueryType;
                                                                        subQueryJoin.getSubQuery()      ,//originQueryPattern;
                                                                        thisJoinPattern                 ,//String thisJoinPattern;
                                                                        subQueryJoin.getRelationship()  ,//String relationShip;
                                                                        subQueryJoin.getAttributeName() ,//String attributeName;
                                                                        subQueryJoin.getSpecialAlias()  ,//String selectAlias;
                                                                        whereParameter.toString()       ,//String parameterForWhere;
                                                                        whereOperator.toString()        ,//String operatorForWhere;
                                                                        whereValue.toString()           ,//String parameterValueForWhere;
                                                                        patternSQuerytableList          ,//ArrayList<OmcOQLPatternSQueryTable> patternSQueryTableList;
                                                                        null                            ,//ArrayList<OmcOQLPatternClass> patternClassList,
                                                                        ""                              ,//String fromPatternStr, 
                                                                        ""                              ,//String lastAlias, 
                                                                        ""                              ,//String selectStr, 
                                                                        ""                              ,//String fromStr, 
                                                                        ""                              ,//String joinStr, 
                                                                        ""                              ,//String whereStr,
                                                                        ""                              ,//String whereParameterStr, 
                                                                        ""                              ,//String whereInnerStr, 
                                                                        ""                              ,//String whereInnerParameterStr, 
                                                                        subQueryJoin.getIsNot()         ,//boolean isNot
                                                                        ""                              ,//String whereResolvedStr, 
                                                                        ""                               //String sqlSelectStrAll
                                                                        );
                patternSQueryResult.createSelectClause(processLevel+1, testMode);
                patternSQueryResult.createFromClause  (processLevel+1, testMode);
                patternSQueryResult.createFromPattern (processLevel+1, testMode);
                patternSQueryResult.createJoinClause  (processLevel+1, testMode);
                patternSQueryResult.createParmClause  (processLevel+1, testMode);
                patternSQueryResult.createWhereClause (processLevel+1, testMode);
                patternSQueryResult.resolveWhereClause(processLevel+1, testMode);
                patternSQueryResult.createSqlAll      (processLevel+1, testMode);
                subQueryResultList.add(patternSQueryResult);
            }
        }
        return(subQueryResultList);
    }

    public static ArrayList<OmcOQLPatternSQueryTable> getOQLPatternSQueryTableList(String strIn, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        String str = strIn;
        ArrayList<OmcOQLPatternSQueryTable> patternSQuerytableList = new ArrayList<OmcOQLPatternSQueryTable>();
        
        OmcOQLConvertedParmValue convertedParmValueStr  = OmcUtility.setGetConvertedParmValue(new OmcOQLConvertedParmValue(str,str,"subquery_select","%","'","'",0,new HashMap<String,String>(),"All"),str,"subquery_select",processLevel+1,testMode);
        str = convertedParmValueStr.getStrConverted();
        OmcOQLConvertedParmValue convertedParmValueCond = OmcUtility.setGetConvertedParmValue(new OmcOQLConvertedParmValue(str,str,"subquery_select","%","{","}",0,new HashMap<String,String>(),"All"),str,"subquery_select",processLevel+1,testMode);
        str = convertedParmValueCond.getStrConverted();
        
        List<String> classSpecList = OmcUtility.getSelectQuerySpec(str,processLevel+1,testMode);
        
        String lastAlias = null;
        String lastAttribute = null;
        String firstAttribute = null;
        String querySpec = null;
        String className = null;
        String directionRightEnd = null;
        String directionLeft = null;
        String dbmsTableListStr = null;
        String directionRight = null;
        List<String> classFileterRight = null;
        List<String> classFileterLeft  = null;
        String alias = null;
        int idx = 0;
        String rightConditionStr = "";
        for (idx = 0; idx < classSpecList.size(); idx ++)
        {
            classFileterRight = new ArrayList<String>();
            classFileterLeft  = new ArrayList<String>();
            alias = "s" + StrUtil.LPAD(Integer.toString(idx+1), 3, "0");
            if(classSpecList.size() == idx + 1) 
            {
                lastAlias = alias;
                lastAttribute = OmcUtility.revokeConvertedParmValue(classSpecList.get(idx),convertedParmValueStr ,processLevel+1,testMode);
                break;
            }
            rightConditionStr = "";
            querySpec = classSpecList.get(idx);
            className = StrUtil.getStringForFromTo(querySpec, 1, OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT, OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT);
            className = StrUtil.removeFromToStr(className,"%","%");
            String workStr = className;
            
            className = StrUtil.removeFromToStr(className, "<", ">");
            String filterLeft = StrUtil.subStr(workStr, 1, StrUtil.inStr(workStr, className)-1);

            filterLeft = StrUtil.replaceAll(StrUtil.replaceAll(filterLeft, "<", ""),">","");

            String filterRight = StrUtil.subStr(workStr, StrUtil.inStr(workStr, className) + className.length());

            filterRight = StrUtil.replaceAll(StrUtil.replaceAll(filterRight, "<", ""),">","");
            if(StrUtil.isEmpty(filterLeft )) filterLeft  = "*";
            if(StrUtil.isEmpty(filterRight)) filterRight = "*";
            
            directionLeft = StrUtil.subStr(querySpec, 1, StrUtil.inStr(querySpec, OmcFoundationConstant.OQL_SUBQUERY_CLS_LEFT)-1);
            if(idx == 0) firstAttribute = "this.obid";
            if(StrUtil.inStr(directionLeft, ".") > 0)
            {
                firstAttribute = StrUtil.subStr(directionLeft,1,StrUtil.inStr(directionLeft, ".",-1)-1);
                directionLeft  = StrUtil.subStr(directionLeft,StrUtil.inStr(directionLeft, ".",-1)+1);
            }
            //******************************************************************************************

            
            if(StrUtil.isEmpty(directionRight)) directionRight = "end";
            directionRightEnd = directionRight;
            
            querySpec = OmcUtility.revokeConvertedParmValue(querySpec,convertedParmValueCond ,processLevel+1,testMode);
            String condition = StrUtil.getStringForFromTo(querySpec, 1, OmcFoundationConstant.OQL_SUBQUERY_CON_LEFT, OmcFoundationConstant.OQL_SUBQUERY_CON_RIGHT);
            querySpec = querySpec.replace(OmcFoundationConstant.OQL_SUBQUERY_CON_LEFT + condition + OmcFoundationConstant.OQL_SUBQUERY_CON_RIGHT, "").trim();
            condition = OmcUtility.revokeConvertedParmValue(condition,convertedParmValueStr ,processLevel+1,testMode);

            if(querySpec.indexOf(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT + OmcFoundationConstant.OQL_SUBQUERY_SEPERATOR) != -1){
                rightConditionStr = StrUtil.getStringForFromTo(querySpec, 1, OmcFoundationConstant.OQL_SUBQUERY_CON_LEFT, OmcFoundationConstant.OQL_SUBQUERY_CON_RIGHT);
                
                directionRight = querySpec.substring(querySpec.indexOf(OmcFoundationConstant.OQL_SUBQUERY_CLS_RIGHT)+2);
                directionRight = directionRight.replace(OmcFoundationConstant.OQL_SUBQUERY_CON_LEFT + rightConditionStr + OmcFoundationConstant.OQL_SUBQUERY_CON_RIGHT, "");
                if(!NullUtil.isNone(rightConditionStr)) {
//                    rightConditionStr = "%" + rightConditionStr + "%";
                    rightConditionStr = OmcUtility.revokeConvertedParmValue(rightConditionStr,convertedParmValueCond ,processLevel+1,testMode);
                    rightConditionStr = OmcUtility.revokeConvertedParmValue(rightConditionStr,convertedParmValueStr  ,processLevel+1,testMode);
                }
                directionRight = StrUtil.removeFromToStr(directionRight,"%","%");
                directionRightEnd = directionRight;
            }else{
                directionRight = "";
            }
            
            List<String> classList = ClassInfoUtil.getChildClassList(className);
            if(classList.size() == 0) throw new FoundationException("Cannot Find for class for '" + className + "'");
            String classListStr = StrUtil.convertList2Str(classList);
            List<String> dbmsTableList = ClassInfoUtil.getDbmsTableList(classListStr);//getDbmsTableList(StrUtil.convertList2Str(classList),processLevel+1,testMode);
            if(dbmsTableList.size() == 0) throw new FoundationException("Cannot Find Oracle Table for class(" + classList + ")");
            if(dbmsTableList.size() > 1) throw new FoundationException("Does not support multi table(" + dbmsTableList + ") in subquery");
            dbmsTableListStr = StrUtil.convertList2Str(dbmsTableList);

            for(String strClass : classList){
                ClassInfo classInfo = ClassInfoUtil.getClassInfo(strClass);
                List<AllowedClassInfo> allowedClassList = classInfo.getAllowedClassInfo();
                if(!NullUtil.isNone(allowedClassList)){
                    for(AllowedClassInfo allowedClass : allowedClassList){
                        String[] strArray = allowedClass.getClassName().split(Pattern.quote("."));
                        if(Bit.isInclude(allowedClass.getFlags(),OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM)){
                            classFileterLeft.add(strArray[0]);
                        }
                        if(Bit.isInclude(allowedClass.getFlags(),OmcSystemConstants.FLAG_ALLOWEDRELATION_TO)){
                            classFileterRight.add(strArray[0]);
                        }
                    }
                }
            }
            OmcOQLPatternSQueryCondition patternSQueryCondition  = OmcUtility.createConditionParameter(condition,alias,classList,processLevel+1,testMode);
            patternSQuerytableList.add(new OmcOQLPatternSQueryTable(directionLeft                             ,
                                                                    directionRight                            ,
                                                                    classList                                 ,
                                                                    classListStr                              ,
                                                                    classFileterLeft                          ,
                                                                    StrUtil.convertList2Str(classFileterLeft) ,
                                                                    classFileterRight                         ,
                                                                    StrUtil.convertList2Str(classFileterRight),
                                                                    dbmsTableList                             ,
                                                                    dbmsTableListStr                          ,
                                                                    alias                                     ,
                                                                    condition                                 ,
                                                                    firstAttribute                            ,
                                                                    ""                                        ,
                                                                    patternSQueryCondition                    ));
            if((idx < classSpecList.size() - 2) && !NullUtil.isNone(rightConditionStr)){
                OmcOQLPatternSQueryTable fromToSQueryTable = getFromToSQueryTable(patternSQuerytableList.get(patternSQuerytableList.size()-1),
                                                                                  rightConditionStr,
                                                                                  processLevel+1,
                                                                                  testMode);
                if(!NullUtil.isNull(fromToSQueryTable)){
                    patternSQuerytableList.add(fromToSQueryTable);
                }
            }
        }
        
        
        if(idx > 0) patternSQuerytableList.get(patternSQuerytableList.size()-1).setLastAttribute(lastAttribute);
        if(directionRightEnd.equals(OmcFoundationConstant.OQL_DIRECTION_From) || directionRightEnd.equals(OmcFoundationConstant.OQL_DIRECTION_To))
        {
            if(OmcFoundationConstant.OQL_DIRECTION_From.equals(directionRightEnd))
            {
                classFileterRight = patternSQuerytableList.get(patternSQuerytableList.size()-1).getClassNameListFrom(); 
            }
            else
            {
                classFileterRight = patternSQuerytableList.get(patternSQuerytableList.size()-1).getClassNameListTo(); 
            }
            List<String> relClassList = patternSQuerytableList.get(patternSQuerytableList.size()-1).getClassNameList();
            
            List<String> classList = new ArrayList<String>();
            for(String relClass : relClassList){
                ClassInfo relClassInfo = ClassInfoUtil.getClassInfo(relClass);
                List<AllowedClassInfo> allowedClassList = relClassInfo.getAllowedClassInfo();
                for(AllowedClassInfo allowedClass : allowedClassList){
                    String[] strArray = allowedClass.getClassName().split(Pattern.quote("."));
                    if(OmcFoundationConstant.OQL_DIRECTION_From.equals(directionRightEnd)){
                        if(Bit.isInclude(allowedClass.getFlags(),OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM)){
                            if(StrUtil.in(strArray[0], classFileterRight)){
                                classList.add(strArray[0]);
                            }
                        }
                    }else if("To".equals(directionRightEnd)){
                        if(Bit.isInclude(allowedClass.getFlags(),OmcSystemConstants.FLAG_ALLOWEDRELATION_TO)){
                            if(StrUtil.in(strArray[0], classFileterRight)){
                                classList.add(strArray[0]);
                            }
                        }
                    }
                }
            }
            
//            
//            classList = getClassListForRelationShip(relClassList,
//                                                                 directionRightEnd,
//                                                                 classFileterRight,
//                                                                 processLevel+1,
//                                                                 testMode);
//            
            if(classList.size() == 0) throw new FoundationException("Cannot Find for class for '" + patternSQuerytableList.get(patternSQuerytableList.size()-1).getClassNameList() + "'");
            
            List<String> dbmsTableList = ClassInfoUtil.getDbmsTableList(StrUtil.convertList2Str(classList));//getDbmsTableList(StrUtil.convertList2Str(classList),processLevel+1,testMode);
            
            //List<String> dbmsTableList = getDbmsTableList(StrUtil.convertList2Str(classList),processLevel+1,testMode);
            if(dbmsTableList.size() == 0) throw new FoundationException("Cannot Find Oracle Table for class(" + classList + ")");
            
            if(dbmsTableList.size() == 0) throw new FoundationException("Cannot Find Oracle Table for class(" + classList + ")");
            if(dbmsTableList.size() > 1) throw new FoundationException("Does not support multi table(" + dbmsTableList + ") in subquery");
            
            directionLeft = "self";directionRight = "end";
            String conditionTemp = "";
            if(!NullUtil.isNone(rightConditionStr)){
                conditionTemp = rightConditionStr;
                //conditionTemp = rightConditionStr.substring(1, rightConditionStr.length()-1);
            }
            OmcOQLPatternSQueryCondition patternSQueryCondition  = OmcUtility.createConditionParameter(conditionTemp,lastAlias,classList,processLevel+1,testMode);

            patternSQuerytableList.add(new OmcOQLPatternSQueryTable(directionLeft                             ,
                                                                    directionRight                            ,
                                                                    classList                                 ,
                                                                    StrUtil.convertList2Str(classList)        ,
                                                                    null                                      ,
                                                                    ""                                        ,
                                                                    null                                      ,
                                                                    ""                                        ,
                                                                    dbmsTableList                             ,
                                                                    StrUtil.convertList2Str(dbmsTableList)    ,
                                                                    lastAlias                                 ,
                                                                    rightConditionStr                         ,
                                                                    firstAttribute                            ,
                                                                    ""                                        ,
                                                                    patternSQueryCondition                    ));
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(patternSQuerytableList);
    }
    private static OmcOQLPatternSQueryTable getFromToSQueryTable(OmcOQLPatternSQueryTable relSubQueryTable,String conditionStr,int processLevel,boolean testMode){
        String alias = "b" + relSubQueryTable.getAlias();
        List<String> classList = null;
        String condition = conditionStr.substring(1, conditionStr.length()-1);
        OmcOQLPatternSQueryCondition sQueryCondition = null;
        OmcOQLPatternSQueryTable     result = null;
        if(OmcFoundationConstant.OQL_DIRECTION_From.equals(relSubQueryTable.getDirectionRight())){
            classList = relSubQueryTable.getClassNameListFrom();
        }else if(OmcFoundationConstant.OQL_DIRECTION_To.equals(relSubQueryTable.getDirectionRight())){
            classList = relSubQueryTable.getClassNameListTo();
        }
        if(!NullUtil.isNone(classList)){
            String classListStr = StrUtil.convertList2Str(classList);
            List<String> dbmsTableList = ClassInfoUtil.getDbmsTableList(classListStr);
            if(dbmsTableList.size() == 0) throw new FoundationException("Cannot Find Oracle Table for class(" + classList + ")");
            
            if(dbmsTableList.size() == 0) throw new FoundationException("Cannot Find Oracle Table for class(" + classList + ")");
            if(dbmsTableList.size() > 1) throw new FoundationException("Does not support multi table(" + dbmsTableList + ") in subquery");
            
            sQueryCondition  = OmcUtility.createConditionParameter(condition,alias,classList,processLevel+1,testMode);
            result = new OmcOQLPatternSQueryTable("self",
                                                  "self"                                    ,
                                                  classList                                 ,
                                                  classListStr                              ,
                                                  new ArrayList<String>()                   ,
                                                  ""                                        ,
                                                  new ArrayList<String>()                   ,
                                                  ""                                        ,
                                                  dbmsTableList                             ,
                                                  StrUtil.convertList2Str(dbmsTableList)    ,
                                                  alias                                     ,
                                                  conditionStr                              ,
                                                  ""                                        ,
                                                  ""                                        ,
                                                  sQueryCondition                           );
        }
        return result;
    }
    public static OmcOQLPatternSQueryCondition createConditionParameter(String conditionStrIn, String alias, List<String> classList, int processLevel,boolean testMode)
    {
        String conditionStr = conditionStrIn;
        OmcOQLConvertedParmValue convertedParmValueStr  = setGetConvertedParmValue(new OmcOQLConvertedParmValue(conditionStr,conditionStr,"subquery_select","%","'","'",0,new HashMap<String,String>(),"All"),conditionStr,"subquery_select",processLevel+1,testMode);
        conditionStr = convertedParmValueStr.getStrConverted();
        StringBuffer whereParmstrOut = new StringBuffer();
        StringBuffer whereConditionOut = new StringBuffer();
        List<String> sentenceList = getSplitLogicSentence(conditionStr,processLevel+1,testMode);
        for(String str: sentenceList)
        {
            String separedtedStr = null;
            if(!str.equals("[") && !str.equals("]"))
            {
                if(isParameterSelectPattern(str.trim())) {
                    str = str.trim();
                }
                if(StrUtil.in(str,OmcFoundationConstant.OQL_OPERATOR_ALL_Set) || str.equals("(") || str.equals(")") || (isParameterSelectPattern(str))){
                    separedtedStr = str;
                }
                else
                {
                    List<OmcOQLClassAttribute> classAttrList = getClassAttributeType(classList, new ArrayList<String>(), str.trim(), "", processLevel+1,testMode);
                    if(classAttrList.size() > 0 )
                    {
                        separedtedStr = "@" + alias + "." + "[" + str.trim() + "]";
                    }
                    else
                    {
                        String tempParameter = EtcUtil.getParamter();
                        separedtedStr = "<%" + tempParameter + "%>";
                        if(!StrUtil.isEmpty(whereParmstrOut)) whereParmstrOut.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
                        whereParmstrOut.append(tempParameter).append("^:^").append(revokeConvertedParmValue(str,convertedParmValueStr ,processLevel+1,testMode));
                    }
                }
                whereConditionOut.append(separedtedStr);
            }
        }
        OmcOQLPatternSQueryCondition patternSQueryCondition  = new OmcOQLPatternSQueryCondition(conditionStr,whereParmstrOut.toString(),whereConditionOut.toString(),"");
        return(patternSQueryCondition);
    }
    public static String findFirstIdex(String strIn){
        char[] chrArray = new char[]{'(',')','[',']'};
        char[] inCharArray = strIn.toCharArray();
        int len = inCharArray.length;
        int idx = 0;
        while(idx < len){
            if(StrUtil.in(inCharArray[idx], chrArray)){
                return String.valueOf(idx) + "." + "1";
            }else{
                if(idx+1 < len){
                    String str = String.valueOf(inCharArray[idx]) + String.valueOf(inCharArray[idx+1]);
                    if(StrUtil.in(str, OmcFoundationConstant.OQL_OPERATOR_ALL_Set)){
                        return String.valueOf(idx) + "." + "2";
                    }
                }
            }
            idx++;
        }
        return "-1";
    }
    
    public static List<String> getSplitLogicSentence(String strIn,int processLevel,boolean testMode)
    {
        String str = strIn;
        List<String> strList = new ArrayList<String>();
        int idx = 0;
        int len = 0;

        String rtn = findFirstIdex(str);
        if(rtn.equals("-1")) {
            if(!NullUtil.isNone(str.trim())) strList.add(str.trim());
        }else{
            String[] strAray = rtn.split(Pattern.quote("."));
            idx  = Integer.parseInt(strAray[0]);
            len  = Integer.parseInt(strAray[1]);
            String left = "";
            while(idx != -1){
                if(idx == 0){
                    left = str.substring(0, idx+len);
                    str = str.substring(idx+len);
                    strList.add(left);
                }else
                {
                    left = str.substring(0, idx);
                    strList.add(left);
                    left = str.substring(idx,idx+len);
                    strList.add(left);
                    str = str.substring(idx+len);
                }
                rtn = findFirstIdex(str);
                if(rtn.equals("-1")) {
                    if(!NullUtil.isNone(str.trim())) strList.add(str);
                    break;
                }
                strAray = rtn.split(Pattern.quote("."));
                idx  = Integer.parseInt(strAray[0]);
                len  = Integer.parseInt(strAray[1]);
            }
        }
        return(strList);
    }
    public static List<String> getSelectQuerySpec(String selectListIn,int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        String selectList = selectListIn;
        List<String> classList = new ArrayList<String>() ;
        OmcOQLConvertedParmValue convertedParmValueStr  = setGetConvertedParmValue(new OmcOQLConvertedParmValue(selectList,selectList,"subquery_select","%","'","'",0,new HashMap<String,String>(),"All"),selectList,"subquery_select",processLevel+1,testMode);
        OmcOQLConvertedParmValue convertedParmValueCond = setGetConvertedParmValue(new OmcOQLConvertedParmValue(selectList,selectList,"subquery_select","%","{","}",0,new HashMap<String,String>(),"All"),selectList,"subquery_select",processLevel+1,testMode);
        selectList = convertedParmValueCond.getStrConverted();
        int pos = StrUtil.inStr(selectList, "]");
        int pos1 = 0;
        while(pos > 0)
        {
            String className = StrUtil.subStr(selectList, 1, pos);
            selectList = StrUtil.subStr(selectList, pos+2);
            pos1 = StrUtil.inStr(selectList, ".");
            if(pos1 > 0)
            {
                className = className + "." + StrUtil.subStr(selectList, 1,pos1-1);
                selectList = StrUtil.subStr(selectList, pos1+1);
            }
            className    = revokeConvertedParmValue(className   ,convertedParmValueStr ,processLevel+1,testMode);
            className    = revokeConvertedParmValue(className   ,convertedParmValueCond,processLevel+1,testMode);
            classList.add(className);
            pos = StrUtil.inStr(selectList, "]");
        }
        selectList    = revokeConvertedParmValue(selectList   ,convertedParmValueStr ,processLevel+1,testMode);
        selectList    = revokeConvertedParmValue(selectList   ,convertedParmValueCond,processLevel+1,testMode);
        classList.add(selectList);
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(classList);
    }
    
    private static OmcOQLPatternSQueryResult getPatternSQueryResult(final ArrayList<OmcOQLPatternSQueryResult> subQueryResultList, String relationship, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        for(OmcOQLPatternSQueryResult subQuery : subQueryResultList)
        {
            if (subQuery.getRelationShip().equals(relationship)) return(new OmcOQLPatternSQueryResult(subQuery.getSubQueryType(), subQuery.getOriginQueryPattern(), subQuery.getThisJoinPattern(),
                    subQuery.getRelationShip(), subQuery.getAttributeName(), subQuery.getSelectAlias(), subQuery.getParameterForWhere(),
                    subQuery.getOperatorForWhere(), subQuery.getParameterValueForWhere(),
                    subQuery.getPatternSQueryTableList(), subQuery.getPatternClassList(),
                    subQuery.getFromPatternStr(), subQuery.getLastAlias(), subQuery.getSelectStr(), subQuery.getFromStr(), subQuery.getJoinStr(), subQuery.getWhereStr(),
                    subQuery.getWhereParameterStr(), subQuery.getWhereInnerStr(), subQuery.getWhereInnerParameterStr(), subQuery.isNot(),
                    subQuery.getWhereResolvedStr(), subQuery.getSqlSelectStrAll()));
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(null);
     }
    
    private static OmcOQLSubQueryJoin resolveSubQueryJoin(final String strIn, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        boolean isNot = false;
        String str = strIn;
        if(str.substring(0, 1).equals("!")){isNot = true;str = str.substring(1);}
        OmcOQLConvertedParmValue convertedParmValue = setGetConvertedParmValue(new OmcOQLConvertedParmValue(str,str,"subquery_select","%","'","'",0,new HashMap<String,String>(),"All"),str,"subquery_select",processLevel+1,testMode);
        str = convertedParmValue.getStrConverted();
        
        str = StrUtil.replaceCharFromToCondition(str, "(", ")", OmcFoundationConstant.OQL_SUBQUERY_SEPERATOR, OmcFoundationConstant.OQL_SUBQUERY_CVT_CHAR);

        String specialAlias = getUserDefinedAlias(str);
        
        if(!StrUtil.isEmpty(specialAlias)) str = str.substring(0,str.lastIndexOf(' ')).trim();

        String subQuery      = str;
        String attributeName = str.substring(str.lastIndexOf(".")+1);
        str = str.substring(0,str.lastIndexOf("."));
        String relationship  = str.replace("@", "");
        String leftStr       = str.substring(0,str.indexOf("["));
        String thisJoinPattern = null;
        if(StrUtil.countCharInStr(leftStr,'.') < 2)
        {
            thisJoinPattern = "this.obid";
        }
        else
        {
            thisJoinPattern = StrUtil.replaceAll(StrUtil.subStr(strIn, 1,StrUtil.inStr(leftStr, ".", 1, 2)-1),"@","");
            subQuery        = StrUtil.subStr(subQuery,StrUtil.inStr(subQuery,".",1,2)+1);
            relationship    = StrUtil.subStr(strIn,StrUtil.inStr(strIn,".",1,2)+1);
        }
        attributeName = attributeName.replace(OmcFoundationConstant.OQL_SUBQUERY_CVT_CHAR,OmcFoundationConstant.OQL_SUBQUERY_SEPERATOR);
        subQuery      = subQuery.replace(OmcFoundationConstant.OQL_SUBQUERY_CVT_CHAR,OmcFoundationConstant.OQL_SUBQUERY_SEPERATOR);
        specialAlias    = revokeConvertedParmValue(specialAlias   ,convertedParmValue,processLevel+1,testMode);
        attributeName   = revokeConvertedParmValue(attributeName  ,convertedParmValue,processLevel+1,testMode);
        relationship    = revokeConvertedParmValue(relationship   ,convertedParmValue,processLevel+1,testMode);
        subQuery        = revokeConvertedParmValue(subQuery       ,convertedParmValue,processLevel+1,testMode);
        thisJoinPattern = revokeConvertedParmValue(thisJoinPattern,convertedParmValue,processLevel+1,testMode);
        OmcOQLSubQueryJoin subQueryJoin = new OmcOQLSubQueryJoin(subQuery,thisJoinPattern,relationship,attributeName,specialAlias,isNot);
        
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(subQueryJoin);
    }
    public static String getUserDefinedAlias(String strIn)
    {
        String str = StrUtil.clearStringData(strIn);
        if(str.indexOf(' ') == -1) return("");
        str = str.substring(str.lastIndexOf(' ')).trim();
        return(str);
    }
    public static String createSubQuerySelectClause(ArrayList<OmcOQLPatternSQueryResult> subQueryRsltList,int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        StringBuffer patternSelectSQuery = new StringBuffer();
        StringBuffer thisAlias           = new StringBuffer();
        StringBuffer thisAttribute       = new StringBuffer();
        StringBuffer thisAliasTemp       = new StringBuffer();
        int idx = 1;
        for (OmcOQLPatternSQueryResult patternSQuery : subQueryRsltList)
        {
            if(!StrUtil.isEmpty(patternSelectSQuery)) patternSelectSQuery.append(",").append(OmcFoundationConstant.newline);
            thisAlias.setLength(0);thisAttribute.setLength(0);thisAliasTemp.setLength(0);
            
            thisAlias.append    (StrUtil.subStr(patternSQuery.getThisJoinPattern(), 1,StrUtil.inStr(patternSQuery.getThisJoinPattern(), ".")-1));
            thisAttribute.append(StrUtil.subStr(patternSQuery.getThisJoinPattern(), StrUtil.inStr(patternSQuery.getThisJoinPattern(), ".")+1));

            if(thisAlias.toString().equals("this")){
                thisAliasTemp.append("a.").append("\"").append(thisAttribute.toString().toUpperCase()).append("\"");
            }
            else{
                thisAliasTemp.append("a.").append("\"").append(thisAlias.toString().toLowerCase()).append("_").append(thisAttribute.toString()).append("\"");
            }
            if(!StrUtil.isEmpty(patternSQuery.getSelectAlias())){
                patternSelectSQuery.append("(").append(patternSQuery.getSqlSelectStrAll().replace(OmcFoundationConstant.newline, " ").replace("v_obid", thisAliasTemp.toString())).append(" LIMIT 1)").append(" as \"").append(patternSQuery.getSelectAlias()).append("\"");
            }
            else{
                patternSelectSQuery.append("(").append(patternSQuery.getSqlSelectStrAll().replace(OmcFoundationConstant.newline, " ").replace("v_obid", thisAliasTemp.toString())).append(" LIMIT 1)").append(" as \"").append("s").append(idx).append("\"");
                idx++;
            }
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
    	return(patternSelectSQuery.toString());
    }
    
    public static ArrayList<OmcOQLPatternSQueryResult> getWhereSQueryRsltSet(String strIn, String parmIn, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);

        String[] splitedwhere = strIn.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME));
        String[] splitedparm  = parmIn.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME));
        
        if(splitedwhere.length != splitedparm.length) throw new FoundationException("Where pattern(" + strIn + ") is not matched with Parameter Pattern(" + parmIn + ")");

        int splitedCnt = splitedwhere.length;
        ArrayList<OmcOQLPatternSQueryResult> rtnPatternSQueryRsltList = new ArrayList<OmcOQLPatternSQueryResult>();
        for(int idx = 0;idx < splitedCnt; idx++)
        {
            OmcOQLParsedWhereSQuery parsedWhereSQuery = OmcUtility.getParsedSQueryForWhere(splitedwhere[idx],splitedparm[idx],processLevel+1,testMode);
            ArrayList<OmcOQLPatternSQueryResult> patternSQueryRsltList = OmcUtility.getOQLPatternSQueryRsltList(parsedWhereSQuery.getWhereSelect(), "condition", parsedWhereSQuery, processLevel+1, testMode);
            rtnPatternSQueryRsltList.addAll(patternSQueryRsltList);
        }
    	OmcComUtility.logWriteEnd(processLevel, testMode);
    	return(rtnPatternSQueryRsltList);
    }
    private static OmcOQLParsedWhereSQuery getParsedSQueryForWhere(String selectStr, String parmStr, int processLevel,boolean testMode)
    {
        OmcOQLParsedWhereSQuery parsedWhereSQuery = null;
        try{
        StringBuffer parm = new StringBuffer();
        parm.append(OmcFoundationConstant.OQL_PARAMETER_VALUE_LEFT).append(StrUtil.subStr(parmStr, 1, StrUtil.inStr(parmStr, OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE)-1)).append(OmcFoundationConstant.OQL_PARAMETER_VALUE_RIGHT);
        int pos = StrUtil.inStr(selectStr, parm.toString());
        String operator = StrUtil.subStr(selectStr, pos-2, 2);
        String select   = StrUtil.subStr(selectStr, 1, pos-3);
        String parmVaue   = StrUtil.subStr(parmStr, StrUtil.inStr(parmStr, OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE)+3);
        parsedWhereSQuery = new OmcOQLParsedWhereSQuery(select,parm.toString(),operator,parmVaue);
        }catch(Exception e){
            e.printStackTrace();
            throw new FoundationException("[Foundation]Parameter Pattern Error!" + "Pattern:" + selectStr + ",Parameter:" + parmStr);
        }
        return(parsedWhereSQuery);
    }
    public static String createSubQueryWhereClause(ArrayList<OmcOQLPatternSQueryResult> subQueryRsltList, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        StringBuffer patternWhereSubQuery = new StringBuffer();
        StringBuffer thisAlias = new StringBuffer();
        for(OmcOQLPatternSQueryResult patternSQueryResult:subQueryRsltList){
            if(!StrUtil.isEmpty(patternWhereSubQuery)) patternWhereSubQuery.append(" and ").append(OmcFoundationConstant.newline);
            int pos = StrUtil.inStr(patternSQueryResult.getThisJoinPattern(), ".");
            thisAlias.setLength(0);
            thisAlias.append(StrUtil.subStr(patternSQueryResult.getThisJoinPattern(), 1, pos-1));
            String thisAttribute = StrUtil.subStr(patternSQueryResult.getThisJoinPattern(), pos+1);
            
            String aliasTemp = thisAlias.toString();
            if("TO_OBID".equals(thisAttribute.toUpperCase())){
                aliasTemp = aliasTemp + "." + "pto_obid";
            }else if("FROM_OBID".equals(thisAttribute.toUpperCase())){
                aliasTemp = aliasTemp + "." + "pfrom_obid";
            }else{
                aliasTemp = thisAlias.append(".").append(thisAttribute).toString();
            }
            if(!StrUtil.isEmpty(patternSQueryResult.getSqlSelectStrAll())){
                if(patternSQueryResult.isNot()){
                    patternWhereSubQuery.append("not exists(").append(StrUtil.replaceAll(patternSQueryResult.getSqlSelectStrAll(), "v_obid", aliasTemp)).append(")");
                }
                else{
                    patternWhereSubQuery.append("exists(").append(StrUtil.replaceAll(patternSQueryResult.getSqlSelectStrAll(), "v_obid", aliasTemp)).append(")");
                }
            }
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
    	return(patternWhereSubQuery.toString());
    }
    public static OmcOQLSelectClauseVO createSelectClause(ArrayList<OmcOQLPatternClass> classList,String selectionStr,boolean isForSubQuery, boolean includeAddedSelect, int processLevel,boolean testMode)
	{
        OmcComUtility.logWriteStart(processLevel, testMode);
        
        ArrayList<OmcOQLPatternSelection> patternSelectionList = getOQLPatternAttributeList(selectionStr,processLevel+1,testMode);
        ArrayList<OmcOQLComparator> comparator = new ArrayList<OmcOQLComparator>();
        
        for(int i = 0; i < patternSelectionList.size(); i++){
            if(isForSubQuery){
                patternSelectionList.get(i).setOutputPatternForSQuery(classList, processLevel+1, testMode);
            }
            else{
                patternSelectionList.get(i).setOutputPattern(classList, processLevel+1, testMode);
            }
        }
        comparator.add(new OmcOQLComparator("outputAlias",false));
        OmcSortUtil.sort(patternSelectionList, comparator);
        String aliasSave = "....";
        int seq = 0;
        for(int i = 0; i < patternSelectionList.size(); i++){
            if(!patternSelectionList.get(i).getOutputAlias().equals(".")){
                if (aliasSave.equals(patternSelectionList.get(i).getOutputAlias())){
                    patternSelectionList.get(i).setOutputAlias(patternSelectionList.get(i).getOutputAlias() + (seq+1));
                    seq ++;
                }
                else{
                    seq = 1;
                }
            }
            else{
                seq = 1; 
            }
            aliasSave = patternSelectionList.get(i).getOutputAlias();
        }
        StringBuffer rtnFirst = new StringBuffer();
        StringBuffer rtnLast  = new StringBuffer();
        for(int i = 0; i < patternSelectionList.size(); i++){
            if(!StrUtil.isEmpty(rtnFirst)){
                rtnFirst.append(",").append(OmcFoundationConstant.newline);
                rtnLast.append(",").append(OmcFoundationConstant.newline);
            }
            if(patternSelectionList.get(i).getOutputAlias().equals(".")){
                rtnFirst.append(patternSelectionList.get(i).getOutputSelectPattern());
                rtnLast.append(patternSelectionList.get(i).getOutputSelectLast());
                if(includeAddedSelect && !NullUtil.isNone(patternSelectionList.get(i).getOutputSelectNameAttrAdded())){
                    rtnLast.append(",").append(patternSelectionList.get(i).getOutputSelectNameAttrAdded());
                }
            }
            else{
                if(patternSelectionList.get(i).isUniqueNeed()){
                    rtnFirst.append(patternSelectionList.get(i).getOutputSelectPattern());
                    rtnLast.append(patternSelectionList.get(i).getOutputSelectLast()).append(" as \"").append(patternSelectionList.get(i).getOutputAlias()).append("\"");
                }
                else{
                    rtnFirst.append(patternSelectionList.get(i).getOutputSelectPattern()).append(" as \"").append(patternSelectionList.get(i).getOutputAlias()).append("\"");
                    rtnLast.append(patternSelectionList.get(i).getOutputSelectLast()).append(" as \"").append(patternSelectionList.get(i).getOutputAlias()).append("\"");
                    if(includeAddedSelect && !NullUtil.isNone(patternSelectionList.get(i).getOutputSelectNameAttrAdded())){
                        rtnLast.append(",").append(patternSelectionList.get(i).getOutputSelectNameAttrAdded());
                    }
                }
            }
        }
        OmcOQLSelectClauseVO selectClauseVO = new OmcOQLSelectClauseVO("","");
        if(StrUtil.isEmpty(rtnFirst)) return(selectClauseVO);
        selectClauseVO.setFirstSelectStr(rtnFirst.toString());selectClauseVO.setLastSelectStr(rtnLast.toString());
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(selectClauseVO);
	}
    
    private static ArrayList<OmcOQLPatternSelection> getOQLPatternAttributeList(String strIn ,int processLevel,boolean testMode)
    {
        OmcOQLConvertedParmValue convertedParmValue = setGetConvertedParmValue(new OmcOQLConvertedParmValue(strIn,strIn,"attribute","%","'","'",0,new HashMap<String,String>(),"All"),strIn,"attribute",processLevel+1,testMode);
        strIn = convertedParmValue.getStrConverted();
        String[] splitedPattern = strIn.split(Pattern.quote(OmcFoundationConstant.OQL_SELECTION_Seperator));
        ArrayList<OmcOQLPatternSelection> patternSelectionList = new ArrayList<OmcOQLPatternSelection>();
        boolean isUniqueNeed  = false;
        for(String str:splitedPattern){
            isUniqueNeed = false;
            if(!StrUtil.isEmpty(str.trim())){
                if(str.indexOf("(") >= 0 && str.indexOf(")") >= 0 && str.indexOf("@") >= 0 && str.indexOf("[") >= 0 && str.indexOf("]") >= 0) isUniqueNeed = true;
                ArrayList<OmcOQLPatternAlias> patternAliasList = getParsingSelectPatternList(str,processLevel+1,testMode);
                patternSelectionList.add(new OmcOQLPatternSelection(revokeConvertedParmValue(str,convertedParmValue,processLevel+1,testMode),
                                                                    ".",
                                                                    revokeConvertedParmValue(str,convertedParmValue,processLevel+1,testMode),
                                                                    "NULL",
                                                                    -1,
                                                                    isUniqueNeed,
                                                                    "",
                                                                    patternAliasList));
            }
        }
        return(patternSelectionList);
    }
    
    private static ArrayList<OmcOQLPatternAlias> getParsingSelectPatternList(String strIn,int processLevel,boolean testMode)
    {
        ArrayList<OmcOQLPatternAlias> patternAliasList  = new  ArrayList<OmcOQLPatternAlias>();
        
        OmcOQLConvertedParmValue convertedParmValue = setGetConvertedParmValue(new OmcOQLConvertedParmValue(strIn,strIn,"select","%","'","'",0,new HashMap<String,String>(),"All"),strIn,"attribute",processLevel+1,testMode);
        strIn = convertedParmValue.getStrConverted();

        if(isParameterSelectPattern(strIn)){
            String classAlias = strIn.substring(strIn.indexOf(" ")).trim();
            String classAttribute = strIn.substring(0,strIn.indexOf(" ")).trim();
            patternAliasList.add(new OmcOQLPatternAlias(classAlias,classAttribute,-1));
        }else{
            int pos1 =  StrUtil.inStr(strIn,OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator);
            int pos2 = 0;
            while(pos1 > 0)
            {
                pos2 = StrUtil.inStr(strIn,".",pos1);
                if(pos2 == 0) break;
                String alias      = StrUtil.subStr(strIn, pos1, pos2-pos1);
                String attribute  = StrUtil.getStringForFromTo(strIn, pos2+1, "[", "]");
                strIn             = StrUtil.subStr(strIn, pos2+1);
                patternAliasList.add(new OmcOQLPatternAlias(alias,attribute,-1));
                pos1 = StrUtil.inStr(strIn,OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator);
            }
            HashMap<String,String> temMap = new HashMap<String,String>();
            StringBuffer tempKey = new StringBuffer();
            for(OmcOQLPatternAlias patternAlias: patternAliasList)
            {
                tempKey.setLength(0);
                tempKey.append(patternAlias.getClassAlias()).append("^^:^^").append(patternAlias.getClassAttribute());
                temMap.put(tempKey.toString(), tempKey.toString());
            }
            patternAliasList = new  ArrayList<OmcOQLPatternAlias>();
            for(String key : temMap.keySet())
            {
                String classAlias = StrUtil.subStr(key, 1, StrUtil.inStr(key, "^^:^^")-1);
                String classAttribute = StrUtil.subStr(key,StrUtil.inStr(key, "^^:^^")+5);
                patternAliasList.add(new OmcOQLPatternAlias(classAlias,classAttribute,-1));
            }
        }
        return(patternAliasList);
    }
    public static String createOrderByClause(ArrayList<OmcOQLPatternClass> classList,String patternSortBy,int processLevel,boolean testMode)
	{
        OmcComUtility.logWriteStart(processLevel, testMode);
        StringBuffer orderByClause = new StringBuffer();
        ArrayList<OmcOQLPatternSelection> patternSelectionList= OmcUtility.getOQLPatternAttributeList(patternSortBy, processLevel+1, testMode);
        
        for (int i = 0; i < patternSelectionList.size(); i++)
        {
            patternSelectionList.get(i).setOutputSortBy(classList, processLevel+1, testMode);
            if(!StrUtil.isEmpty(orderByClause)) orderByClause.append(",");
            orderByClause.append(patternSelectionList.get(i).getOutputSelectPattern());
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
	    return(orderByClause.toString());	
	}
    public static HashMap<String,Object> resolveParameter(String keyValue,
    		                                              String patternWhereIn, 
    		                                              String patternParameterIn,
    		                                              String sqlTotalCnt, 
    		                                              String sqlTotalSelect, 
    		                                              String sqlOrdeByClause, 
    		                                              int processLevel,boolean testMode){
        
        OmcComUtility.logWriteStart(processLevel, testMode);
        
        
        ArrayList<OmcOQLVariableParameter> variableParameter = OmcUtility.convertVariableParameterList(patternWhereIn, patternParameterIn, processLevel+1, testMode);
        OmcAPIFindAndSearchObjectsLog findObjectsLog = new OmcAPIFindAndSearchObjectsLog(keyValue, patternWhereIn, patternParameterIn, sqlTotalCnt,sqlTotalSelect, sqlOrdeByClause);
        
        HashMap<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put("VariableParameter", variableParameter);
        rtnMap.put("FindObjectsLog"   , findObjectsLog   );
        OmcComUtility.logWriteEnd(processLevel, testMode);
    	return(rtnMap);
    }
    public static String createBasicDispalyedName(String alias)
    {
        StringBuffer r          = new StringBuffer();
        r.append(",").append(createLockerDispalyedName    (alias)).append(OmcFoundationConstant.newline);
        r.append(",").append(createCheckouterDispalyedName(alias)).append(OmcFoundationConstant.newline);
        r.append(",").append(createOwnerDispalyedName     (alias)).append(OmcFoundationConstant.newline);
        r.append(",").append(createCreatorDispalyedName   (alias)).append(OmcFoundationConstant.newline);
        r.append(",").append(createModifierDispalyedName  (alias)).append(OmcFoundationConstant.newline);
        r.append(",").append(createClassDispalyedName     (alias));
        return(r.toString());
    }
    private static String createLockerDispalyedName(String alias)
    {
        StringBuffer dbmsColumn = new StringBuffer();
        StringBuffer dbmsAlias  = new StringBuffer();
        StringBuffer r          = new StringBuffer();
        if(alias.equals(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this)){
            dbmsColumn.append("\"").append("LOCKER").append("\"");
        }
        else{
            dbmsColumn.append("\"").append(alias.toLowerCase()).append("_locker").append("\"");
        }
        dbmsAlias.append(alias).append("_lockerName\"");
        r.append("omcGetUserTitles(").append(dbmsColumn).append(") as \"").append(dbmsAlias);
        return(r.toString());
    }
    private static String createCheckouterDispalyedName(String alias)
    {
        StringBuffer dbmsColumn = new StringBuffer();
        StringBuffer dbmsAlias  = new StringBuffer();
        StringBuffer r          = new StringBuffer();
        if(alias.equals(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this)){
            dbmsColumn.append("\"").append("CHECKOUTER").append("\"");
        }
        else{
            dbmsColumn.append("\"").append(alias.toLowerCase()).append("_checkouter").append("\"");
        }
        dbmsAlias.append(alias).append("_checkouterName\"");
        r.append("omcGetUserTitles(").append(dbmsColumn).append(") as \"").append(dbmsAlias);
        return(r.toString());
    }
    
    private static String createOwnerDispalyedName(String alias)
    {
        StringBuffer dbmsColumn = new StringBuffer();
        StringBuffer dbmsAlias  = new StringBuffer();
        StringBuffer r          = new StringBuffer();
        if(alias.equals(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this)){
            dbmsColumn.append("\"").append("OWNER").append("\"");
        }
        else{
            dbmsColumn.append("\"").append(alias.toLowerCase()).append("_owner").append("\"");
        }
        dbmsAlias.append(alias).append("_ownerName\"");
        r.append("omcGetUserTitles(").append(dbmsColumn).append(") as \"").append(dbmsAlias);
        return(r.toString());
    }
    
    private static String createCreatorDispalyedName(String alias)
    {
        StringBuffer dbmsColumn = new StringBuffer();
        StringBuffer dbmsAlias  = new StringBuffer();
        StringBuffer r          = new StringBuffer();
        if(alias.equals(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this)){
            dbmsColumn.append("\"").append("CREATOR").append("\"");
        }
        else{
            dbmsColumn.append("\"").append(alias.toLowerCase()).append("_creator").append("\"");
        }
        dbmsAlias.append(alias).append("_creatorName\"");
        r.append("omcGetUserTitles(").append(dbmsColumn).append(") as \"").append(dbmsAlias);
        return(r.toString());
    }
    
    private static String createModifierDispalyedName(String alias)
    {
        StringBuffer dbmsColumn = new StringBuffer();
        StringBuffer dbmsAlias  = new StringBuffer();
        StringBuffer r          = new StringBuffer();
        if(alias.equals(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this)){
            dbmsColumn.append("\"").append("MODIFIER").append("\"");
        }
        else
        {
            dbmsColumn.append("\"").append(alias.toLowerCase()).append("_modifier").append("\"");
        }
        dbmsAlias.append(alias).append("_modifierName\"");
        r.append("omcGetUserTitles(").append(dbmsColumn).append(") as \"").append(dbmsAlias);
        return(r.toString());
    }

    private static String createClassDispalyedName(String alias)
    {
        StringBuffer dbmsColumn = new StringBuffer();
        StringBuffer dbmsAlias  = new StringBuffer();
        StringBuffer r          = new StringBuffer();
        if(alias.equals(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this))
        {
            dbmsColumn.append("\"").append("CLASS_NAME").append("\"");
        }
        else
        {
            dbmsColumn.append("\"").append(alias.toLowerCase()).append("_className").append("\"");
        }
        dbmsAlias.append(alias).append("_displayedClassName\"");
        r.append(OmcSchemaUtil.getNullFunction()).append("((select x.pdisplayed_name from psysclassinfo x where x.pclass_name  = ").append(dbmsColumn).append("),").append(dbmsColumn).append(") as \"").append(dbmsAlias);
        dbmsAlias.setLength(0);dbmsAlias.append(alias).append("_displayedClassNameKr\"");
        r.append(OmcFoundationConstant.newline).append(",").append(OmcSchemaUtil.getNullFunction()).append("((select x.pdisplayed_name_kr from psysclassinfo x where x.pclass_name  = ").append(dbmsColumn).append("),").append(dbmsColumn).append(") as \"").append(dbmsAlias);
        return(r.toString());
    }
    public static void checkSqlStatement(String selectPattern, String fromPattern, String wherePattern,int processLevel,boolean testMode){
        OmcUtility.checkSqlStatement(selectPattern,processLevel+1,testMode);
        OmcUtility.checkSqlStatement(fromPattern,processLevel+1,testMode);
        OmcUtility.checkSqlStatement(wherePattern,processLevel+1,testMode);
    }
    public static OmcSQLVariableParameter  _getSQLVariableParameter(ArrayList<OmcOQLVariableParameter> mainVariableParmList,int processLevel,boolean testMode) {
        OmcComUtility.logWriteStart(processLevel, testMode);
        OmcSQLVariableParameter sqlVariableParameter = new OmcSQLVariableParameter();
        String methodName = "";String attribute = "";
        try{
            for (int i = 0;i < mainVariableParmList.size(); i++){
                attribute = mainVariableParmList.get(i).getParameterName().replace("}", "");
                methodName = "set" + attribute.substring(0,1).toUpperCase() + attribute.substring(1);
                //Method methods = sqlVariableParameter.getClass().getDeclaredMethod(methodName, String.class);
                String value = mainVariableParmList.get(i).getParameterValue();
                if(!NullUtil.isNone(value)){
                    if(value.length() > 1){
                        if(value.startsWith("'")) value = value.substring(1);
                    }
                    if(value.length() > 1){
                        if(value.endsWith("'")) value = value.substring(0,value.length()-1);
                    }
                }
                if(mainVariableParmList.get(i).isDateConvert() && !NullUtil.isNone(value)){
                    value = value.replace(OmcSystemConstants.OMC_DATE_CONVERT_PREFIX, "");
                }
                //methods.invoke(sqlVariableParameter, value);
                sqlVariableParameter.setAttributeValue(attribute, value);
            }           
        }catch (Exception e) {
            LOGGER.debug(methodName + e.getMessage());
            OmcComUtility.error("_getSQLVariableParameter" + methodName + e.getMessage());
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return sqlVariableParameter;
    }
    public static HashMap<String,Object>  getFunctionParameter(String strIn, int funcationVariableSeq,int processLevel,boolean testMode){
        HashMap<String,Object> rtnMap = new HashMap<String,Object>(); 
        OmcComUtility.logWriteStart(processLevel, testMode);
        OmcUtility.setGetConvertedParmValue(strIn, "'", "'", "", "variable", null, "Left", 5, "#{" + OmcFoundationConstant.OQL_FUNCTION_PARM_PREFIX, funcationVariableSeq, rtnMap,processLevel+1, testMode);
        ArrayList<OmcOQLVariableParameter> funcVariableParmList = new ArrayList<OmcOQLVariableParameter>();
        OmcOQLConvertedParmValue parmValue  = (OmcOQLConvertedParmValue)rtnMap.get(OmcFoundationConstant.OQL_MAPPARM_PARMVALUE); 
        HashMap<String,String>   parm       = parmValue.getParm();
        for(String key : parm.keySet())
        {
            funcVariableParmList.add(new OmcOQLVariableParameter(key.replace("#{", "").replace("}", ""),parm.get(key),false));
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
        rtnMap.put(OmcFoundationConstant.OQL_MAPPARM_VAR_LIST, funcVariableParmList);
        return(rtnMap);
    }
    public static boolean isArributeFormat(String str) {
        if(StrUtil.isEmpty(str.trim())) return false;
        return Pattern.matches(".*@[0-9a-zA-Z]*\\.\\[[0-9a-zA-Z]*\\].*",str.trim());
    }
    public static boolean isSubQueryFormat(String str) {
        if(StrUtil.isEmpty(str.trim())) return false;
        if(Pattern.matches(".*From\\[.*\\]\\..*",str.trim())) return true;
        if(Pattern.matches(".*To\\[.*\\]\\..*",str.trim())) return true;
        if(Pattern.matches(".*self\\[.*\\]\\..*",str.trim())) return true;
        return false;
    }
    public static boolean isParameterSelectPattern(String str) {
        if(StrUtil.isEmpty(str.trim())) return false;
        if(Pattern.matches("<\\%[0-9a-zA-Z]*\\%>.*",str.trim())) return true;
        return false;
    }
    private static String replaceOperator(String returnWhere){
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_Equal        ," = "      );
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_NotEqual     ," <> "       );
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_Like         ," like "    );
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_NotLike      ," not like ");
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_GreaterThan  ," > "       );
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_LessTan      ," < "       );
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_GreaterEqTan ," >= "      );
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_LessEqTan    ," <= "      );
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_Or           ," or "      );
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_And          ," and "     );
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_In           ," in "      );
        returnWhere = returnWhere.replace(OmcFoundationConstant.OQL_OPERATOR_NotIn        ," not in "  );
        return returnWhere;
    }
    public static String makeUserTitles(String dbmsColumn, String columnAlias,boolean convert2CamelCase){
        String columnAliasNew = columnAlias.replace("\"", "");
        dbmsColumn = dbmsColumn.replace("\"", "");
        if(convert2CamelCase) columnAliasNew = BaseFoundationUtil.convert2CamelCase(columnAlias) + "UserTitles";
        String str = OmcDBMSConstants.OMC_USER_TITLE_STR.replace(OmcDBMSConstants.OMC_USER_DBMS_COLUMN, dbmsColumn);
        str = str.replace(OmcDBMSConstants.OMC_USER_DBMS_ALIAS, columnAliasNew);
        return str;
    }
    public static String makeUserObid(String dbmsColumn, String columnAlias, boolean convert2CamelCase){
        String columnAliasNew = columnAlias.replace("\"", "");
        dbmsColumn = dbmsColumn.replace("\"", "");
        if(convert2CamelCase) columnAliasNew = BaseFoundationUtil.convert2CamelCase(columnAlias) + "UserObid";
        String str = OmcDBMSConstants.OMC_USER_OBID_STR.replace(OmcDBMSConstants.OMC_USER_DBMS_COLUMN, dbmsColumn);
        str = str.replace(OmcDBMSConstants.OMC_USER_DBMS_ALIAS, columnAliasNew);
        return str;
    }
    public static <T> List<T> makeEmptyList(PagingEntity pagingEntity){
        List<T> emptyList = Collections.<T>emptyList();
        if(NullUtil.isNull(pagingEntity)) return emptyList;
        PagingList<T> pagedList = new PagingList<T>();
        pagedList.addAll(emptyList);
        pagedList.setRowSize(pagingEntity.getRowSize());
        pagedList.setTargetRow(pagingEntity.getTargetRow());
        pagedList.setCurrentPage(pagingEntity.getTargetRow(), pagingEntity.getRowSize());
        pagedList.setRows(0);
        return pagedList;
    }
    public static String makeSelectStrForGroupByPattern(List<OmcGroupByParamVO> groupByParamList){
        StringBuffer strBuf = new StringBuffer();
        StringUtil.constructSelectPattern(strBuf,makeSelectPatternStr(groupByParamList));
        return strBuf.toString();
    }
    private static String makeSelectPatternStr(List<OmcGroupByParamVO> groupByParamList){
        StringBuffer strBuf  =new StringBuffer("'" + OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY_VALUE + "' " + OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY);
        StringBuffer groupByStrBuf  =new StringBuffer();
        boolean isFirst = true;
        for(OmcGroupByParamVO vo : groupByParamList) {
            isFirst = false; 
            StringUtil.constructSelectPattern(strBuf,vo.makeSelectPattern());
        }
        isFirst = true;
        for(OmcGroupByParamVO vo : groupByParamList) {
            if(vo.getType().equals(OmcGroupByParamVO.KEY.groubBy)) {
                if(!isFirst) groupByStrBuf.append(",");
                isFirst = false; 
                groupByStrBuf.append("\"" + vo.makeGroupByPattern() + "\"");
            }
        }
        groupByStrBuf.append(",").append("\"" + OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY + "\"");
        StringUtil.addSortByPattern(strBuf, groupByStrBuf.toString());
        return strBuf.toString();
    }
}
