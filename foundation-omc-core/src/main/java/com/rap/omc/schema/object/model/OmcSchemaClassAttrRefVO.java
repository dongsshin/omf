/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaClassAttrRefVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 15.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

/**
 * <pre>
 * Class : OmcSchemaClassAttrRefVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaClassAttrRefVO extends OmcSchemaSysRootVO{
    private String  classType                          ;
    private String  className                          ;
    private long    flagsClass                         ;
    private String  attributeName                      ;
    private long    flagsAttribute                     ;
    private String  isInherited                        ;
    private String  dbmsColumn                         ;
    private String  columnAlias                        ;
    private int     dataType                           ;
    private int     lengths                            ;
    private String  nullable                           ;
    private String  defaultValue                       ;
    private String  valueSettingInfo                   ;
    private int     sorting                            ;
    private String  definedClassName                   ;
    private int     attributeSequence                  ;
        
    
    private String  displayedName                      ;
    private boolean isLongString                       ;
    
    
    public String getDefinedClassName(){
        return definedClassName;
    }

    
    public int getAttributeSequence(){
        return attributeSequence;
    }

    
    public void setDefinedClassName(String definedClassName){
        this.definedClassName = definedClassName;
    }

    
    public void setAttributeSequence(int attributeSequence){
        this.attributeSequence = attributeSequence;
    }

    public String getClassType(){
        return classType;
    }
    
    public String getClassName(){
        return className;
    }
    public long getFlagsClass(){
        return flagsClass;
    }
    
    public String getAttributeName(){
        return attributeName;
    }
    
    public long getFlagsAttribute(){
        return flagsAttribute;
    }
    
    public String getIsInherited(){
        return isInherited;
    }
    
    public String getDbmsColumn(){
        return dbmsColumn;
    }
    
    public String getColumnAlias(){
        return columnAlias;
    }
    
    public int getDataType(){
        return dataType;
    }
    
    public int getLengths(){
        return lengths;
    }
    
    public String getNullable(){
        return nullable;
    }
    
    public String getDefaultValue(){
        return defaultValue;
    }
    
    public String getValueSettingInfo(){
        return valueSettingInfo;
    }
    
    public int getSorting(){
        return sorting;
    }
    
    public String getDisplayedName(){
        return displayedName;
    }
    
    public boolean isLongString(){
        return isLongString;
    }
    
    public void setClassType(String classType){
        this.classType = classType;
    }
    
    public void setClassName(String className){
        this.className = className;
    }
    public void setFlagsClass(long flagsClass){
        this.flagsClass = flagsClass;
    }
    
    public void setAttributeName(String attributeName){
        this.attributeName = attributeName;
    }
    
    public void setFlagsAttribute(long flagsAttribute){
        this.flagsAttribute = flagsAttribute;
    }
    
    public void setIsInherited(String isInherited){
        this.isInherited = isInherited;
    }
    
    public void setDbmsColumn(String dbmsColumn){
        this.dbmsColumn = dbmsColumn;
    }
    
    public void setColumnAlias(String columnAlias){
        this.columnAlias = columnAlias;
    }
    
    public void setDataType(int dataType){
        this.dataType = dataType;
    }
    
    public void setLengths(int lengths){
        this.lengths = lengths;
    }
    
    public void setNullable(String nullable){
        this.nullable = nullable;
    }
    
    public void setDefaultValue(String defaultValue){
        this.defaultValue = defaultValue;
    }
    
    public void setValueSettingInfo(String valueSettingInfo){
        this.valueSettingInfo = valueSettingInfo;
    }
    
    public void setSorting(int sorting){
        this.sorting = sorting;
    }
    
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }
    
    public void setLongString(boolean isLongString){
        this.isLongString = isLongString;
    }
    
}
