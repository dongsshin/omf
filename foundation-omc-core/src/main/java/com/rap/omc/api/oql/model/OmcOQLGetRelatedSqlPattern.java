package com.rap.omc.api.oql.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;

public class OmcOQLGetRelatedSqlPattern {
    private String patternSelect     = "";
    private String patternFrom       = "";
    private String patternWhere      = "";
    private String patternParameter  = "";
    private String relationPattern   = "";
    private String filterPattern     = "";
    private String fromToDirection   = "";
    
    private String classVoListStr     = "";
    List<String> obidList ;
    Set<String>  classVoListSet ;

    Set<String> classFilterSet       = new HashSet<String>();
    String patternClassFilterStr     = "";
    String patternRelationshipStr    =" ";
    List<OmcOQLRelatedClassVO> relatedClassInfoList;
    
    
    private String patternSubQuery   = "";
    private String patternSortBy     = "";
    
    private String patternSubQueryWhere          = "";
    private String patternSubQueryWhereParameter = "";
    
    public Set<String> getClassFilterSet(){
        return classFilterSet;
    }

    
    public void setClassFilterSet(Set<String> classFilterSet){
        this.classFilterSet = classFilterSet;
        this.patternClassFilterStr = StrUtil.convertSet2Str(classFilterSet);
    }

    
    public String getPatternClassFilterStr(){
        return patternClassFilterStr;
    }

    
    public void setPatternClassFilterStr(String patternClassFilterStr){
        this.patternClassFilterStr = patternClassFilterStr;
    }

    
    public String getPatternRelationshipStr(){
        return patternRelationshipStr;
    }

    
    public void setPatternRelationshipStr(String patternRelationshipStr){
        this.patternRelationshipStr = patternRelationshipStr;
    }

    
    public List<OmcOQLRelatedClassVO> getRelatedClassInfoList(){
        return relatedClassInfoList;
    }

    
    public void setRelatedClassInfoList(List<OmcOQLRelatedClassVO> relatedClassInfoList){
        this.relatedClassInfoList = relatedClassInfoList;
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
    
    
    
    public String getFromToDirection(){
        return fromToDirection;
    }

    
    public void setFromToDirection(String fromToDirection){
        this.fromToDirection = fromToDirection;
    }

    public String getFilterPattern(){
        return filterPattern;
    }

    
    public void setFilterPattern(String filterPattern){
        this.filterPattern = filterPattern;
    }

    public List<String> getObidList(){
        return obidList;
    }
    
    public void setObidList(List<String> obidList){
        this.obidList = obidList;
    }
    
    public Set<String> getClassVoListSet(){
        return classVoListSet;
    }
    
    public void setClassVoListSet(Set<String> classVoListSet){
        this.classVoListSet = classVoListSet;
    }

    
    public String getClassVoListStr(){
        return classVoListStr;
    }

    
    public void setClassVoListStr(String classVoListStr){
        this.classVoListStr = classVoListStr;
    }

    
    public String getRelationPattern(){
        return relationPattern;
    }

    
    public void setRelationPattern(String relationPattern){
        this.relationPattern = relationPattern;
    }

    public OmcOQLGetRelatedSqlPattern(String patternSelect, String patternFrom, String patternWhere,
            String patternParameter) {
        super();
        this.patternSelect = patternSelect;
        this.patternFrom = patternFrom;
        this.patternWhere = patternWhere;
        this.patternParameter = patternParameter;
        this.patternSubQuery   = "";
        this.patternSortBy     = "";
        this.patternSubQueryWhere          = "";
        this.patternSubQueryWhereParameter = "";
        this.classVoListStr = "";
        this.obidList = new  ArrayList<String>();
        this.classVoListSet  = new HashSet<String>()  ;
    }

    public OmcOQLGetRelatedSqlPattern() {
        super();
        
        this.patternSelect     = "";
        this.patternFrom       = "";
        this.patternWhere      = "";
        this.patternParameter  = "";
        this.patternSubQuery   = "";
        this.patternSortBy     = "";
        this.patternSubQueryWhere          = "";
        this.patternSubQueryWhereParameter = "";
        this.classVoListStr = "";
        this.obidList = new  ArrayList<String>();
        this.classVoListSet  = new HashSet<String>()  ;
    }
    
}
