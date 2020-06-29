/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLPatternAlias.java
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
 * Class : OmcOQLPatternAlias
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLPatternAlias extends OmcOQLRoot{
    private String classAlias;
    private String classAttribute;
    private int    aliasLenth;
    private int    dataType;
    
    /**
     * @param classAlias
     * @param classAttribute
     */
    public OmcOQLPatternAlias(String classAlias, String classAttribute, int dataType) {
        super();
        this.classAlias     = classAlias;
        this.classAttribute = classAttribute;
        this.aliasLenth     = classAlias.length();
        this.dataType       = dataType;
    }
    
    
    public int getDataType(){
        return dataType;
    }

    
    public void setDataType(int dataType){
        this.dataType = dataType;
    }

    public String getClassAlias(){
        return classAlias;
    }
    
    public String getClassAttribute(){
        return classAttribute;
    }
    
    public void setClassAlias(String classAlias){
        this.classAlias = classAlias;
        this.aliasLenth = classAlias.length();
    }
    
    public void setClassAttribute(String classAttribute){
        this.classAttribute = classAttribute;
    }
    public int getAliasLenth(){
        return aliasLenth;
    }
    
    @Override
    public String toString(){
        return "OmcOQLPatternAlias [classAlias=" + classAlias + ", classAttribute=" + classAttribute + "]";
    }
    
}
