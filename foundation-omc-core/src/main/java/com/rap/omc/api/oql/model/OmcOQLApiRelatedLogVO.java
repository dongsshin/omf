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
public class OmcOQLApiRelatedLogVO {

    private String  keyValue                          ;
    private String  patternWhere                      ;
    private String  patternParameter                  ;
    private String  sqlTotalSelect                    ;
    private String  sqlThisLastSelect                 ;
    private String  sqlRelLastSelect                  ;
    private String  sqlSubQuerySelect                 ;
    private String  sqlAddedSelect                    ;
    
    public String getKeyValue(){
        return keyValue;
    }
    
    public void setKeyValue(String keyValue){
        this.keyValue = keyValue;
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
    
    public String getSqlTotalSelect(){
        return sqlTotalSelect;
    }
    
    public void setSqlTotalSelect(String sqlTotalSelect){
        this.sqlTotalSelect = sqlTotalSelect;
    }
    
    public String getSqlThisLastSelect(){
        return sqlThisLastSelect;
    }
    
    public void setSqlThisLastSelect(String sqlThisLastSelect){
        this.sqlThisLastSelect = sqlThisLastSelect;
    }
    
    public String getSqlRelLastSelect(){
        return sqlRelLastSelect;
    }
    
    public void setSqlRelLastSelect(String sqlRelLastSelect){
        this.sqlRelLastSelect = sqlRelLastSelect;
    }
    
    public String getSqlSubQuerySelect(){
        return sqlSubQuerySelect;
    }
    
    public void setSqlSubQuerySelect(String sqlSubQuerySelect){
        this.sqlSubQuerySelect = sqlSubQuerySelect;
    }
    
    public String getSqlAddedSelect(){
        return sqlAddedSelect;
    }
    
    public void setSqlAddedSelect(String sqlAddedSelect){
        this.sqlAddedSelect = sqlAddedSelect;
    }
    
    
}
