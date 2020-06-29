/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcAPIFindObjectsLog.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 8. 8.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;


/**
 * <pre>
 * Class : OmcAPIFindObjectsLog
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcAPIFindAndSearchObjectsLog extends OmcOQLRoot{
    private String keyValue;
    private String patternWhere;
    private String patternParameter;
    private String sqlQueryForCount;
    private String sqlQueryForSelect;
    private String sqlOrderByClause;
    /**
     * @param keyValue
     * @param patternWhere
     * @param patternParameter
     * @param sqlQueryForCount
     * @param sqlQueryForSelect
     * @param sqlOrderByClause
     */
    public OmcAPIFindAndSearchObjectsLog(String keyValue, String patternWhere, String patternParameter, String sqlQueryForCount,
            String sqlQueryForSelect, String sqlOrderByClause) {
        super();
        this.keyValue = keyValue;
        this.patternWhere = patternWhere;
        this.patternParameter = patternParameter;
        this.sqlQueryForCount = sqlQueryForCount;
        this.sqlQueryForSelect = sqlQueryForSelect;
        this.sqlOrderByClause = sqlOrderByClause;
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
    
    public String getSqlQueryForCount(){
        return sqlQueryForCount;
    }
    
    public String getSqlQueryForSelect(){
        return sqlQueryForSelect;
    }
    
    public String getSqlOrderByClause(){
        return sqlOrderByClause;
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
    
    public void setSqlQueryForCount(String sqlQueryForCount){
        this.sqlQueryForCount = sqlQueryForCount;
    }
    
    public void setSqlQueryForSelect(String sqlQueryForSelect){
        this.sqlQueryForSelect = sqlQueryForSelect;
    }
    
    public void setSqlOrderByClause(String sqlOrderByClause){
        this.sqlOrderByClause = sqlOrderByClause;
    }

    @Override
    public String toString(){
        return "OmcAPIFindObjectsLog [keyValue=" + keyValue + ", patternWhere=" + patternWhere + ", patternParameter="
                + patternParameter + ", sqlQueryForCount=" + sqlQueryForCount + ", sqlQueryForSelect="
                + sqlQueryForSelect + ", sqlOrderByClause=" + sqlOrderByClause + "]";
    }
    
    
    
}
