package com.rap.omc.api.oql.model;

import java.util.ArrayList;
import java.util.List;

public class OmcOQLSearchParsingResult {
    private String        whereClause            = "";
    private String        sqlSelectClause        = "";
    private String        sqlSelectClauseLast    = "";
    private String        sqlOrderbyClause       = "";
    private String        sqlSelectThisLast      = "";
    private List<String>  thisSelectFirstSet     = new ArrayList<String>();
    private List<String>  fromClauseSet          = new ArrayList<String>();
    private List<String>  joinClauseSet          = new ArrayList<String>();
    
    public String getWhereClause(){
        return whereClause;
    }
    
    public void setWhereClause(String whereClause){
        this.whereClause = whereClause;
    }
    
    public String getSqlSelectClause(){
        return sqlSelectClause;
    }
    
    public void setSqlSelectClause(String sqlSelectClause){
        this.sqlSelectClause = sqlSelectClause;
    }
    
    public String getSqlSelectClauseLast(){
        return sqlSelectClauseLast;
    }
    
    public void setSqlSelectClauseLast(String sqlSelectClauseLast){
        this.sqlSelectClauseLast = sqlSelectClauseLast;
    }
    
    public String getSqlOrderbyClause(){
        return sqlOrderbyClause;
    }
    
    public void setSqlOrderbyClause(String sqlOrderbyClause){
        this.sqlOrderbyClause = sqlOrderbyClause;
    }
    
    public String getSqlSelectThisLast(){
        return sqlSelectThisLast;
    }
    
    public void setSqlSelectThisLast(String sqlSelectThisLast){
        this.sqlSelectThisLast = sqlSelectThisLast;
    }
    
    public List<String> getThisSelectFirstSet(){
        return thisSelectFirstSet;
    }
    
    public void setThisSelectFirstSet(List<String> thisSelectFirstSet){
        this.thisSelectFirstSet = thisSelectFirstSet;
    }
    
    public List<String> getFromClauseSet(){
        return fromClauseSet;
    }
    
    public void setFromClauseSet(List<String> fromClauseSet){
        this.fromClauseSet = fromClauseSet;
    }
    
    public List<String> getJoinClauseSet(){
        return joinClauseSet;
    }
    
    public void setJoinClauseSet(List<String> joinClauseSet){
        this.joinClauseSet = joinClauseSet;
    }
    
}
