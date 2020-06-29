/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysCclassAttrInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 10.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import java.util.Date;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaConstants;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaSysCclassAttrInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSysClassAttrInfoVO extends OmcSchemaSysRootVO {
    private long    kinds         = 0                  ;
    private String  classObid                          ;
    private String  attributeName                      ;
    private String  dbmsColumn                         ;
    private String  columnAlias                        ;
    private String  dataTypeStr                        ;
    private int     dataType     = 0                   ;
    private int     lengths      = 0                   ;
    private String  defaultValue                       ;
    private int     sortings      = 0                   ;
    private String  valueList                   ;
    private String  displayedName                      ;
    
    private String  classType                          ;
    private String  className                          ;
    
    private String  nullAbleStr                        ;
    private boolean nullAble = false                    ;
    private boolean isLongString = false               ;
    

    public String getValueList(){
        return valueList;
    }
    @Override
    public void setFlags(long flags){
        super.setFlags(flags);
        if(Bit.isInclude(flags, OmcSystemConstants.SYSCLASSATTR_FLAG_Nullable)) nullAble = true;
    }
    public void setValueList(String valueList){
        this.valueList = valueList;
    }

    public boolean isLongString(){
        return isLongString;
    }

    public boolean isNullAble(){
        return nullAble;
    }

    
    public void setKinds(long kinds){
        this.kinds = kinds;
    }

    
    public void setNullAble(boolean nullAble){
        this.nullAble = nullAble;
    }
    public void setNullAble(String nullAble){
        if(StrUtil.isEmpty(nullAble)) return;
        if("Y".equals(nullAble)){
            this.nullAble = true;
            return;
        }else if("N".equals(nullAble)){
            this.nullAble = false;
            return;
        }
        throw new FoundationException("Attribute Nullable Error(Data:" + nullAble + "). Valid Values is Y,N.");
    }

    public long getKinds(){
        return kinds;
    }
    
    public String getClassObid(){
        return classObid;
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
    
    public int getDataType(){
        return dataType;
    }
    
    public int getLengths(){
        return lengths;
    }
    
    public String getDefaultValue(){
        return defaultValue;
    }
    
    public int getSortings(){
        return sortings;
    }
    
    public String getDisplayedName(){
        return displayedName;
    }
    
    public void setKinds(Integer kinds){
        this.kinds = kinds;
    }
    
    public void setClassObid(String classObid){
        this.classObid = classObid;
    }
    
    
    public void setDbmsColumn(String dbmsColumn){
        this.dbmsColumn = dbmsColumn;
        
    }
    public void setAttributeName(String attributeName){
        this.attributeName = attributeName;
    }
    public void setColumnAlias(String columnAlias){
        this.columnAlias = columnAlias;
        this.attributeName = OmcSchemaUtil.convertDbColumn2AttrName(columnAlias);
    }
    
    public void setDataType(int dataType){
        this.dataType = dataType;
    }
    public void setDataType(String dataType){
        if(dataType.equals("String"             )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_STRING;return;}
        if(dataType.equals("LongString"         )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING;this.isLongString = true; return; }
        if(dataType.equals("Integer"            )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_INTEGER;return;}
        if(dataType.equals("Float"              )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_FLOAT;return;}
        if(dataType.equals("Date"               )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_DATE;return;}
        if(dataType.equals("Boolean"            )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN;return;}
        if(dataType.equals("Long"               )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_LONG;return;}
        if(dataType.equals("Double"             )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_DOUBLE;return;}
        if(dataType.equals("BigDecimal"         )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_BIGDECIMAL;return;}
        if(dataType.equals("Array"              )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_ARRAY;return;}
        if(dataType.equals("File"               )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_FILE;return;}
        if(dataType.equals("UserId"             )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_USERID;return;}
        if(dataType.equals("AttributeValueSet"  )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_ATTRIBUTESET;return;}
        
        throw new FoundationException("Data Type Error");
    }
    public void setLengths(int lengths){
        this.lengths = lengths;
    }
    
    public void setDefaultValue(String defaultValue){
        this.defaultValue = defaultValue;
    }
    
    public void setSortings(int sortings){
        this.sortings = sortings;
    }
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }

    
    public String getClassType(){
        return classType;
    }

    
    public String getClassName(){
        return className;
    }

    
    public void setClassType(String classType){
        this.classType = classType;
    }

    
    public void setClassName(String className){
        this.className = className;
    }

    
    public String getDataTypeStr(){
        return dataTypeStr;
    }

    
    public String getNullAbleStr(){
        return nullAbleStr;
    }
    public void setDataTypeStr(String dataTypeStr){
        this.dataTypeStr = dataTypeStr;
        if(dataTypeStr.equals("String"             )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_STRING;return;}
        if(dataTypeStr.equals("LongString"         )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING;this.isLongString = true; return; }
        if(dataTypeStr.equals("Integer"            )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_INTEGER;return;}
        if(dataTypeStr.equals("Float"              )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_FLOAT;return;}
        if(dataTypeStr.equals("Date"               )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_DATE;return;}
        if(dataTypeStr.equals("Boolean"            )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN;return;}
        if(dataTypeStr.equals("Long"               )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_LONG;return;}
        if(dataTypeStr.equals("Double"             )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_DOUBLE;return;}
        if(dataTypeStr.equals("BigDecimal"         )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_BIGDECIMAL;return;}
        if(dataTypeStr.equals("Array"              )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_ARRAY;return;}
        if(dataTypeStr.equals("File"               )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_FILE;return;}
        if(dataTypeStr.equals("UserId"             )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_USERID;return;}
        if(dataTypeStr.equals("AttributeValueSet"  )) {this.dataType = OmcSystemConstants.SCHEMA_DATA_TYPE_ATTRIBUTESET;return;}
        throw new FoundationException("Data Type(" + dataTypeStr + ") Error. Not Supported");
    }
    public void setNullAbleStr(String nullAbleStr){
        this.nullAbleStr = nullAbleStr;
        if(StrUtil.isEmpty(nullAbleStr)) return;
        if("Y".equals(nullAbleStr)){
            this.nullAble = true;
            return;
        }else if("N".equals(nullAbleStr)){
            this.nullAble = false;
            return;
        }
        throw new FoundationException("Attribute Nullable Error(Data:" + nullAbleStr + "). Valid Values is Y,N.");
    }
    @Override
    public String toString(){
        return "OmcSchemaSysClassAttrInfoVO ["
                + "className=" + this.getClassName() + ","
                + "attributeName=" + attributeName + ","
                + "sequences=" + this.getSequences() + ","
                + "changeComments=" + this.getChangeComments()+ ","
                + "classType=" + classType + ","
                + "dbmsColumn=" + dbmsColumn + ","
                + "columnAlias=" + columnAlias + ","
                + "sortings=" + sortings + ","
                + "dataTypeStr=" + dataTypeStr + ","
                + "dataType=" + dataType + ","
                + "lengths=" + lengths + ","
                + "nullAbleStr=" + nullAbleStr + ","
                + "nullAble=" + nullAble + ","
                + "defaultValue=" + defaultValue + ","
                + "valueSettingInfo=" + valueList + ","
                + "displayedName=" + displayedName + ","
                + "remarks=" + this.getRemarks() + ","
                + "owners=" + this.getOwners();
    }
}
