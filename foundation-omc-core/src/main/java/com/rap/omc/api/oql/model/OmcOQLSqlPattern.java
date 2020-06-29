package com.rap.omc.api.oql.model;


public class OmcOQLSqlPattern {
    private String patternSelect     = "";
    private String patternFrom       = "";
    private String patternWhere      = "";
    private String patternParameter  = "";
    private String patternSubQuery   = "";
    private String patternSortBy     = "";
    
    private String patternWhereForKey      = "";
    private String patternParameterForKey  = "";

    private String patternSubQueryWhere          = "";
    private String patternSubQueryWhereParameter = "";
    


    public String getPatternWhereForKey(){
        return patternWhereForKey;
    }


    
    public void setPatternWhereForKey(String patternWhereForKey){
        this.patternWhereForKey = patternWhereForKey;
    }


    
    public String getPatternParameterForKey(){
        return patternParameterForKey;
    }


    
    public void setPatternParameterForKey(String patternParameterForKey){
        this.patternParameterForKey = patternParameterForKey;
    }


    public String getPatternSubQueryWhere(){
        return patternSubQueryWhere;
    }

    
    public void setPatternSubQueryWhere(String patternSubQueryWhere){
        this.patternSubQueryWhere = patternSubQueryWhere;
    }

    
    public String getPatternSubQueryWhereParameter(){
        return patternSubQueryWhereParameter;
    }

    
    public void setPatternSubQueryWhereParameter(String patternSubQueryWhereParameter){
        this.patternSubQueryWhereParameter = patternSubQueryWhereParameter;
    }

    public String getPatternSelect(){
        return patternSelect;
    }
    
    public void setPatternSelect(String patternSelect){
        this.patternSelect = patternSelect;
    }
    
    public String getPatternFrom(){
        return patternFrom;
    }
    
    public void setPatternFrom(String patternFrom){
        this.patternFrom = patternFrom;
    }
    
    public String getPatternWhere(){
        return patternWhere;
    }
    
    public void setPatternWhere(String patternWhere){
        this.patternWhere = patternWhere;
    }
    
    public String getPatternParameter(){
        return patternParameter;
    }
    
    public void setPatternParameter(String patternParameter){
        this.patternParameter = patternParameter;
    }
    
    public String getPatternSubQuery(){
        return patternSubQuery;
    }
    
    public void setPatternSubQuery(String patternSubQuery){
        this.patternSubQuery = patternSubQuery;
    }
    
    public String getPatternSortBy(){
        return patternSortBy;
    }
    
    public void setPatternSortBy(String patternSortBy){
        this.patternSortBy = patternSortBy;
    }

    public OmcOQLSqlPattern(String patternSelect, String patternFrom, String patternWhere, String patternParameter,
            String patternSubQuery, String patternSortBy) {
        super();
        this.patternSelect = patternSelect;
        this.patternFrom = patternFrom;
        this.patternWhere = patternWhere;
        this.patternParameter = patternParameter;
        this.patternSubQuery = patternSubQuery;
        this.patternSortBy = patternSortBy;
    }
    public OmcOQLSqlPattern(String patternSelect, String patternFrom, String patternWhere, String patternParameter) {
        super();
        this.patternSelect = patternSelect;
        this.patternFrom = patternFrom;
        this.patternWhere = patternWhere;
        this.patternParameter = patternParameter;
        this.patternSubQuery = "";
        this.patternSortBy = "";
    }
    public OmcOQLSqlPattern() {
        super();
     }
}
