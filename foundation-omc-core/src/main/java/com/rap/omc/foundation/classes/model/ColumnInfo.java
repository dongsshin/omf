
/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ColumnInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 23. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.model;

import java.lang.reflect.Method;

import com.rap.omc.framework.exception.FoundationException;

/**
 * <pre>
 * Class : ColumnInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin.shin
 */
@SuppressWarnings("rawtypes")
public class ColumnInfo implements Comparable{

    private String attributeName;

    private String dbmsColumn;

    private String columnAlias;

    private int    dataType;
    
    private String defaultValue;
    
    private String valueSettingInfo;
    
    private String isInherited = "N";
    
    private long   flags = 0l;
    
    
    public int getLengths(){
        return lengths;
    }



    
    public void setLengths(int lengths){
        this.lengths = lengths;
    }



    private int lengths;
    
    public long getFlags(){
        return flags;
    }


    
    public void setFlags(long flags){
        this.flags = flags;
    }


    public String getAttributeName(){
        return attributeName;
    }

    
    public String getDbmsColumn(){
        return dbmsColumn;
    }

    
    public String getColumnAlias(){
        return columnAlias;
    }

    
    
    public String getValueSettingInfo(){
        return valueSettingInfo;
    }


    
    public void setValueSettingInfo(String valueSettingInfo){
        this.valueSettingInfo = valueSettingInfo;
    }


    public int getDataType(){
        return dataType;
    }

    
    public String getDefaultValue(){
        return defaultValue;
    }

    public void setAttributeName(String attributeName){
        this.attributeName = attributeName;
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

    
    public void setDefaultValue(String defaultValue){
        this.defaultValue = defaultValue;
    }


    
    public String getIsInherited(){
        return isInherited;
    }


    
    public void setIsInherited(String isInherited){
        this.isInherited = isInherited;
    }
    @Override
    public int compareTo(Object obj){
        try {
            Method method = obj.getClass().getMethod("getAttributeName");
            String attributeName = (String)method.invoke(obj);
            return this.getAttributeName().compareTo(attributeName);
        } catch (Exception e) {
            throw new FoundationException("Cannot Compare Column");
        }
    }
}
