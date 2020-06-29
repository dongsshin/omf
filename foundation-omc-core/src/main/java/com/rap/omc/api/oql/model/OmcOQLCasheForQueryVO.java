package com.rap.omc.api.oql.model;

import java.util.ArrayList;
import java.util.Map;

public class OmcOQLCasheForQueryVO {
    String        sqlStrInner       ;
    String        sqlStrOuter       ;
    String        sqlOrderByClause  ;
    Map<String,String> attrMap      ;
    ArrayList<OmcOQLVariableParameter> variableParmList;
    
    public String getSqlStrInner(){
        return sqlStrInner;
    }
    
    public void setSqlStrInner(String sqlStrInner){
        this.sqlStrInner = sqlStrInner;
    }
    
    public String getSqlStrOuter(){
        return sqlStrOuter;
    }
    
    public void setSqlStrOuter(String sqlStrOuter){
        this.sqlStrOuter = sqlStrOuter;
    }
    
    public String getSqlOrderByClause(){
        return sqlOrderByClause;
    }
    
    public void setSqlOrderByClause(String sqlOrderByClause){
        this.sqlOrderByClause = sqlOrderByClause;
    }
    
    public Map<String, String> getAttrMap(){
        return attrMap;
    }
    
    public void setAttrMap(Map<String, String> attrMap){
        this.attrMap = attrMap;
    }
    
    public ArrayList<OmcOQLVariableParameter> getVariableParmList(){
        return variableParmList;
    }
    
    public void setVariableParmList(ArrayList<OmcOQLVariableParameter> variableParmList){
        this.variableParmList = variableParmList;
    }

    public OmcOQLCasheForQueryVO(String sqlStrInner, String sqlStrOuter, String sqlOrderByClause,
            Map<String, String> attrMap, ArrayList<OmcOQLVariableParameter> variableParmList) {
        super();
        this.sqlStrInner = sqlStrInner;
        this.sqlStrOuter = sqlStrOuter;
        this.sqlOrderByClause = sqlOrderByClause;
        this.attrMap = attrMap;
        this.variableParmList = variableParmList;
    }
    
}
