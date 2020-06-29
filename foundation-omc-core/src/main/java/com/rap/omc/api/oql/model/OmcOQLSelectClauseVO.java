package com.rap.omc.api.oql.model;


public class OmcOQLSelectClauseVO {
    private String firstSelectStr;
    private String lastSelectStr;
    
    public OmcOQLSelectClauseVO(String firstSelectStr, String lastSelectStr) {
        super();
        this.firstSelectStr = firstSelectStr;
        this.lastSelectStr = lastSelectStr;
    }

    public String getFirstSelectStr(){
        return firstSelectStr;
    }
    
    public void setFirstSelectStr(String firstSelectStr){
        this.firstSelectStr = firstSelectStr;
    }
    
    public String getLastSelectStr(){
        return lastSelectStr;
    }
    
    public void setLastSelectStr(String lastSelectStr){
        this.lastSelectStr = lastSelectStr;
    }
    
}
