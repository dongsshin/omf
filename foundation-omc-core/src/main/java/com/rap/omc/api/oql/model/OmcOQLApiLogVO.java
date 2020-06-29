/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLApiLogVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 20.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model;

import java.util.Date;


/**
 * <pre>
 * Class : OmcOQLApiLogVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLApiLogVO {

    private String  keyValue                          ;
    private String  patternWhere                      ;
    private String  patternParameter                  ;
    private String  sqlTotalCnt                        ;
    private String  sqlTotalSelect                     ;
    private String  sqlOrderbyClause                   ;
    private String  creator                           ;
    private Date    created                           ;
    private String  modifier                          ;
    private Date    modified                          ;
    
    public String getKeyValue(){
        return keyValue;
    }
    
    public String getPatternWhere(){
        return patternWhere;
    }
    
    public String getPatternParameter(){
        return patternParameter;
    }
    
    public String getSqlTotalCnt(){
        return sqlTotalCnt;
    }
    
    public String getSqlTotalSelect(){
        return sqlTotalSelect;
    }
    
    public String getSqlOrderbyClause(){
        return sqlOrderbyClause;
    }
    
    public String getCreator(){
        return creator;
    }
    
    public Date getCreated(){
        return created;
    }
    
    public String getModifier(){
        return modifier;
    }
    
    public Date getModified(){
        return modified;
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
    
    public void setSqlTotalCnt(String sqlTotalCnt){
        this.sqlTotalCnt = sqlTotalCnt;
    }
    
    public void setSqlTotalSelect(String sqlTotalSelect){
        this.sqlTotalSelect = sqlTotalSelect;
    }
    
    public void setSqlOrderbyClause(String sqlOrderbyClause){
        this.sqlOrderbyClause = sqlOrderbyClause;
    }
    
    public void setCreator(String creator){
        this.creator = creator;
    }
    
    public void setCreated(Date created){
        this.created = created;
    }
    
    public void setModifier(String modifier){
        this.modifier = modifier;
    }
    
    public void setModified(Date modified){
        this.modified = modified;
    }
    
    
    }
