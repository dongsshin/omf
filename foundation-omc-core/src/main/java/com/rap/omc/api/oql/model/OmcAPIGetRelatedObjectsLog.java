/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcAPIGetRelatedObjectsLog.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 8. 10.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;


/**
 * <pre>
 * Class : OmcAPIGetRelatedObjectsLog
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcAPIGetRelatedObjectsLog extends OmcOQLRoot{
    private String keyValue;
    private String patternWhere;
    private String patternParameter;
    private String joinClauseTo;
    private String joinClauseFrom;
    private String sqlSelectTotal;
    private String sqlSelectThisLast;
    private String sqlSelectClauseLast;
    private String sqlSelectSubQuery;
    private String sqlSelectRelLast;
    /**
     * @param keyValue
     * @param patternWhere
     * @param patternParameter
     * @param joinClauseTo
     * @param joinClauseFrom
     * @param sqlSelectTotal
     * @param sqlSelectThisLast
     * @param sqlSelectClauseLast
     * @param sqlSelectSubQuery
     * @param sqlSelectRelLast
     */
    public OmcAPIGetRelatedObjectsLog(String keyValue, String patternWhere, String patternParameter,
            String joinClauseTo, String joinClauseFrom, String sqlSelectTotal, String sqlSelectThisLast,
            String sqlSelectClauseLast, String sqlSelectSubQuery, String sqlSelectRelLast) {
        super();
        this.keyValue = keyValue;
        this.patternWhere = patternWhere;
        this.patternParameter = patternParameter;
        this.joinClauseTo = joinClauseTo;
        this.joinClauseFrom = joinClauseFrom;
        this.sqlSelectTotal = sqlSelectTotal;
        this.sqlSelectThisLast = sqlSelectThisLast;
        this.sqlSelectClauseLast = sqlSelectClauseLast;
        this.sqlSelectSubQuery = sqlSelectSubQuery;
        this.sqlSelectRelLast = sqlSelectRelLast;
    }
    
    public String getKeyValue(){
        return keyValue;
    }
    
    public String getPatternWhere(){
        return patternWhere;
    }
    
    public String getPatternParameter(){
        return patternParameter;
    }
    
    public String getJoinClauseTo(){
        return joinClauseTo;
    }
    
    public String getJoinClauseFrom(){
        return joinClauseFrom;
    }
    
    public String getSqlSelectTotal(){
        return sqlSelectTotal;
    }
    
    public String getSqlSelectThisLast(){
        return sqlSelectThisLast;
    }
    
    public String getSqlSelectClauseLast(){
        return sqlSelectClauseLast;
    }
    
    public String getSqlSelectSubQuery(){
        return sqlSelectSubQuery;
    }
    
    public String getSqlSelectRelLast(){
        return sqlSelectRelLast;
    }
    
    public void setKeyValue(String keyValue){
        this.keyValue = keyValue;
    }
    
    public void setPatternWhere(String patternWhere){
        this.patternWhere = patternWhere;
    }
    
    public void setPatternParameter(String patternParameter){
        this.patternParameter = patternParameter;
    }
    
    public void setJoinClauseTo(String joinClauseTo){
        this.joinClauseTo = joinClauseTo;
    }
    
    public void setJoinClauseFrom(String joinClauseFrom){
        this.joinClauseFrom = joinClauseFrom;
    }
    
    public void setSqlSelectTotal(String sqlSelectTotal){
        this.sqlSelectTotal = sqlSelectTotal;
    }
    
    public void setSqlSelectThisLast(String sqlSelectThisLast){
        this.sqlSelectThisLast = sqlSelectThisLast;
    }
    
    public void setSqlSelectClauseLast(String sqlSelectClauseLast){
        this.sqlSelectClauseLast = sqlSelectClauseLast;
    }
    
    public void setSqlSelectSubQuery(String sqlSelectSubQuery){
        this.sqlSelectSubQuery = sqlSelectSubQuery;
    }
    
    public void setSqlSelectRelLast(String sqlSelectRelLast){
        this.sqlSelectRelLast = sqlSelectRelLast;
    }
}
