/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLSubQueryJoin.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 22.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;


/**
 * <pre>
 * Class : OmcOQLSubQueryJoin
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLSubQueryJoin extends OmcOQLRoot{
    private String subQuery;
    private String thisJoinPattern;
    private String relationship;
    private String attributeName;
    private String specialAlias;
    private boolean isNot;
    public OmcOQLSubQueryJoin() {
        super();
    }
    /**
     * @param subQuery
     * @param thisJoinPattern
     * @param relationship
     * @param attributeName
     * @param specialAlias
     * @param isNot
     */
    public OmcOQLSubQueryJoin(String subQuery, String thisJoinPattern, String relationship, String attributeName,
            String specialAlias, boolean isNot) {
        super();
        this.subQuery = subQuery;
        this.thisJoinPattern = thisJoinPattern;
        this.relationship = relationship;
        this.attributeName = attributeName;
        this.specialAlias = specialAlias;
        this.isNot = isNot;
    }
    
    public String getSubQuery(){
        return subQuery;
    }
    
    public String getThisJoinPattern(){
        return thisJoinPattern;
    }
    
    public String getRelationship(){
        return relationship;
    }
    
    public String getAttributeName(){
        return attributeName;
    }
    
    public String getSpecialAlias(){
        return specialAlias;
    }
    
    public boolean getIsNot(){
        return isNot;
    }
    
    public void setSubQuery(String subQuery){
        this.subQuery = subQuery;
    }
    
    public void setThisJoinPattern(String thisJoinPattern){
        this.thisJoinPattern = thisJoinPattern;
    }
    
    public void setRelationship(String relationship){
        this.relationship = relationship;
    }
    
    public void setAttributeName(String attributeName){
        this.attributeName = attributeName;
    }
    
    public void setSpecialAlias(String specialAlias){
        this.specialAlias = specialAlias;
    }
    
    public void setIsNot(boolean isNot){
        this.isNot = isNot;
    }
    @Override
    public String toString(){
        return "OmcOQLSubQueryJoin [subQuery=" + subQuery + ", thisJoinPattern=" + thisJoinPattern + ", relationship="
                + relationship + ", attributeName=" + attributeName + ", specialAlias=" + specialAlias + ", isNot="
                + isNot + "]";
    }
    
}
