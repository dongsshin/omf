/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysClassInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 9.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaSysClassInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSysClassInfoVO extends OmcSchemaSysRootVO{
    private long    flagsClass                         ;
    private String  dbmsTable                          ;
    private String  classObid                          ;
    private String  classParentObid                    ;
    private String  displayedName                      ;
    private String  displayedNameKr                    ;
    private String  javaPackage;
    
    
    
    public String getDisplayedNameKr(){
        return displayedNameKr;
    }


    
    public void setDisplayedNameKr(String displayedNameKr){
        this.displayedNameKr = displayedNameKr;
    }


    public String getJavaPackage(){
        return javaPackage;
    }

    
    public void setJavaPackage(String javaPackage){
        this.javaPackage = javaPackage;
    }

    public long getFlagsClass(){
        return flagsClass;
    }
    
    public String getDbmsTable(){
        return dbmsTable;
    }
    
    public String getClassObid(){
        return classObid;
    }
    
    public String getClassParentObid(){
        return classParentObid;
    }
    
    public String getDisplayedName(){
        return displayedName;
    }
    
    public void setFlagsClass(long flagsClass){
        this.flagsClass = flagsClass;
    }
    
    public void setDbmsTable(String dbmsTable){
        this.dbmsTable = dbmsTable;
    }
    
    public void setClassObid(String classObid){
        this.classObid = classObid;
    }
    
    public void setClassParentObid(String classParentObid){
        this.classParentObid = classParentObid;
    }
    
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }
    
}
