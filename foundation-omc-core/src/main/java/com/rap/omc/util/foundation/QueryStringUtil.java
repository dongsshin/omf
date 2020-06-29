/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : QueryStringUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 26. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.util.foundation;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.oql.utility.OmcUtility;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ColumnInfo;
import com.rap.omc.foundation.model.VariableAttribute;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcDBMSConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.NullUtil;


/**
 * <pre>
 * Class : QueryStringUtil
 * Description : TODO
 * </pre>
 *
 * @author hyeyoung.park
 */
public class QueryStringUtil {
    public static String appendSelectValues(ObjectRootVO searchInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("\n\t,");
        sb.append(OmcUtility.makeUserTitles("a.plocker","this_lockerName",false));
        sb.append(",");
        sb.append(OmcUtility.makeUserTitles("a.pcheckouter","this_checkouterName",false));
        sb.append(",");
        sb.append(OmcUtility.makeUserTitles("a.powner","this_ownerName",false));
        sb.append(",");
        sb.append(OmcUtility.makeUserTitles("a.pcreator","this_creatorName",false));
        sb.append(",");
        sb.append(OmcUtility.makeUserTitles("a.pmodifier","this_modifierName",false));
        sb.append(",");
        sb.append(OmcDBMSConstants.DBMS_NULL_FUNCTION).append("((select x.pdisplayed_name_kr from psysclassinfo x where x.pclass_name = a.pclass_name), a.pclass_name) as \"").append(OmcSystemConstants.OMC_API_CLASS_DISPLYEDKR_MAP).append("\",");
        sb.append(OmcDBMSConstants.DBMS_NULL_FUNCTION).append("((select x.pdisplayed_name    from psysclassinfo x where x.pclass_name = a.pclass_name), a.pclass_name) as \"").append(OmcSystemConstants.OMC_API_CLASS_DISPLYED_MAP).append("\"");
        String selectValues = searchInfo.getSql() + sb.toString();
        return selectValues;
    }
    public static String convert2InsertValues(ObjectRootVO input){
        return(convert2InsertValues(input,false));
    }
    public static String convert2InsertValues(ObjectRootVO input, boolean isForEachItem){
        String insertValues = null;
        String attachedStr = "";
        if(isForEachItem) attachedStr = "item.";
        try {
            String getMethodString = "get";
            String methodString = null;
            StringBuffer sb = new StringBuffer();
            StringBuffer sbCol = new StringBuffer();

            ClassInfo keyInfo = ClassInfoUtil.getClassInfo(input.getClassName());
            for (ColumnInfo column : keyInfo.getColumnList()) {
                String strCol = BaseFoundationUtil.convert2CamelCase(column.getColumnAlias());
                if (strCol.equals("created") || strCol.equals("modified")) {
                    sb.append(", ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
                    sbCol.append(", ").append(column.getDbmsColumn());
                } else {
                    methodString = getMethodString + strCol.substring(0, 1).toUpperCase() + strCol.substring(1);
                    Method[] methods = input.getClass().getMethods();
                    for (int i = 0; i <= methods.length - 1; i++) {
                        if (methodString.equals(methods[i].getName())) {
                            Object colValue = methods[i].invoke(input);
                            if (NullUtil.isNull(colValue)) {
                                sb.append(", ").append("null");
                                sbCol.append(", ").append(column.getDbmsColumn());
                            } else if (colValue instanceof Date) {
                                sb.append(", ").append(OmcDBMSConstants.DBMS_LOCAL2UTC_FUNCTION).append("(#{").append(attachedStr).append(column.getAttributeName())
                                        .append("})");
                                sbCol.append(", ").append(column.getDbmsColumn());
                            } else if (colValue instanceof Boolean) {
                                if ((boolean)colValue) {
                                    sb.append(", ").append("1");
                                } else {
                                    sb.append(", ").append("0");
                                }
                                sbCol.append(", ").append(column.getDbmsColumn());
                            } else {
                                sb.append(", #{").append(attachedStr).append(column.getAttributeName()).append("}");
                                sbCol.append(", ").append(column.getDbmsColumn());
                            }
                        }
                    }
                }
            }
            insertValues = sb.toString().substring(1);
            input.setColumns(sbCol.toString().substring(1));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | FoundationException e) {
            throw new FoundationException("omc.error.query.string", e);
        }
        return insertValues;
    }
    public static Map<String,MethodHandle> getMethodHandler(ObjectRootVO input){
        Map<String,MethodHandle>  handlerMap = new HashMap<String,MethodHandle>();
        ClassInfo keyInfo = ClassInfoUtil.getClassInfo(input.getClassName());
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        Method[] methods = input.getClass().getMethods();
        for (ColumnInfo column : keyInfo.getColumnList()) {
            String strCol = BaseFoundationUtil.convert2CamelCase(column.getColumnAlias());
            String methodString = "get" + strCol.substring(0, 1).toUpperCase() + strCol.substring(1);
            for (int i = 0; i <= methods.length - 1; i++) {
                if (methodString.equals(methods[i].getName())) {
                    MethodHandle mh = null;
                    try {
                        mh = lookup.unreflect(methods[i]);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    handlerMap.put(column.getColumnAlias(), mh);
                }
            }
        }
         return handlerMap;
    }
    public static String convert2InsertValuesForMigration(ObjectRootVO input, boolean isForEachItem){
        String insertValues = null;
        String attachedStr = "";
        if(isForEachItem) attachedStr = "item.";
        try {
            String getMethodString = "get";
            String methodString = null;
            StringBuffer sb = new StringBuffer();
            StringBuffer sbCol = new StringBuffer();

            ClassInfo keyInfo = ClassInfoUtil.getClassInfo(input.getClassName());
            for (ColumnInfo column : keyInfo.getColumnList()) {
                String strCol = BaseFoundationUtil.convert2CamelCase(column.getColumnAlias());
                methodString = getMethodString + strCol.substring(0, 1).toUpperCase() + strCol.substring(1);
                Method[] methods = input.getClass().getMethods();
                for (int i = 0; i <= methods.length - 1; i++) {
                    if (methodString.equals(methods[i].getName())) {
                        Object colValue = methods[i].invoke(input);
                        if (NullUtil.isNull(colValue)) {
                            sb.append(", ").append("null");
                            sbCol.append(", ").append(column.getDbmsColumn());
                        } else if (colValue instanceof Boolean) {
                            if ((boolean)colValue) {
                                sb.append(", ").append("1");
                            } else {
                                sb.append(", ").append("0");
                            }
                            sbCol.append(", ").append(column.getDbmsColumn());
                        } else {
                            sb.append(", #{").append(attachedStr).append(column.getAttributeName()).append("}");
                            sbCol.append(", ").append(column.getDbmsColumn());
                        }
                    }
                }
             }
            insertValues = sb.toString().substring(1);
            input.setColumns(sbCol.toString().substring(1));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | FoundationException e) {
            throw new FoundationException("omc.error.query.string", e);
        }
        return insertValues;
    }
    public static String convert2InsertValuesForMigration(ObjectRootVO input, ClassInfo keyInfo, Map<String,MethodHandle>  handlerMap, boolean isForEachItem){
        String insertValues = null;
        String attachedStr = "";
        if(isForEachItem) attachedStr = "item.";
        StringBuffer sb = new StringBuffer();
        StringBuffer sbCol = new StringBuffer();
        Object colValue = false;
        for (ColumnInfo column : keyInfo.getColumnList()){
            if(!column.getAttributeName().equals("descriptions") && column.getDataType() != OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN && column.getDataType() != OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING){
                String jdbcType = getOracleType(column.getDataType());
                sb.append(", #{").append(attachedStr).append(column.getAttributeName()).append(",").append(jdbcType).append("}");
                sbCol.append(", ").append(column.getDbmsColumn());                
            }
            MethodHandle mh = handlerMap.get(column.getColumnAlias());
         }
        insertValues = sb.toString().substring(1);
        input.setColumns(sbCol.toString().substring(1));
        return insertValues;
    }
    private static String getOracleType(int dataType){
        if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_STRING)  return "jdbcType=VARCHAR";
        if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_INTEGER) return "jdbcType=NUMERIC";
        if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_FLOAT) return "jdbcType=NUMERIC";
        if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_DATE) return "jdbcType=DATE";
        if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN) return "jdbcType=NUMERIC";
        if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_LONG) return "jdbcType=NUMERIC";
        if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_DOUBLE) return "jdbcType=NUMERIC";
        if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_BIGDECIMAL) return "jdbcType=NUMERIC";
        if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING) return "jdbcType=CLOB";
        if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_USERID) return "jdbcType=VARCHAR";
        return "jdbcType=VARCHAR";
    }
    public static String convert2UpdateValues(ObjectRootVO input, boolean bForFoundation){
        String updateValues = null;
        try {
            String methodString = null;
            StringBuffer sb = new StringBuffer();
            String getMethodString = "get";

            ClassInfo classInfo = ClassInfoUtil.getClassInfo(input.getClassName());
            for (ColumnInfo column : classInfo.getColumnList()) {
                String strCol = BaseFoundationUtil.convert2CamelCase(column.getColumnAlias());
                if (checkIncludeAttribute(strCol, bForFoundation, classInfo)) {
                    continue;
                }

                if (strCol.equals("modified")) {
                    sb.append(", ").append(column.getDbmsColumn()).append(" = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
                } else {
                    methodString = getMethodString + strCol.substring(0, 1).toUpperCase() + strCol.substring(1);
                    Method[] methods = input.getClass().getMethods();
                    for (int i = 0; i <= methods.length - 1; i++) {
                        if (methodString.equals(methods[i].getName())) {
                            Object colValue = methods[i].invoke(input);
                            if (!NullUtil.isNull(colValue)) {
                                if (colValue instanceof Boolean) {
                                    if ((boolean)colValue) {
                                        sb.append(", ").append(column.getDbmsColumn()).append(" = ").append("1");
                                    } else {
                                        sb.append(", ").append(column.getDbmsColumn()).append(" = ").append("0");
                                    }
                                } else if (colValue instanceof Date) {
                                    sb.append(", ").append(column.getDbmsColumn()).append(" = ").append(OmcDBMSConstants.DBMS_LOCAL2UTC_FUNCTION).append("(#{")
                                            .append(column.getAttributeName()).append("})");
                                } else if (strCol.equals("attributeList")) {
                                    List<?> varList = (List<?>)colValue;
                                    if (!NullUtil.isNone(varList)) {
                                        sb.append(", ").append(column.getDbmsColumn())
                                                .append(" = (SELECT ATTRIBUTEVALUESET(");
                                        for (Object var : varList) {
                                            VariableAttribute attr = (VariableAttribute)var;

                                            // SQL Injection Check
                                            // 2015.12.17 : yosikim - input 문자가 문자 column 으로 처리되므로 sql check 제거함
                                            // ValidationUtil.checkValidSQL(attr.getAttributeValue());

                                            sb.append("ATTRIBUTEVALUE('").append(attr.getAttributeName());
                                            String value = attr.getAttributeValue()!=null?attr.getAttributeValue().replace("'", "''"):attr.getAttributeValue();
                                            sb.append("','").append(value);
                                            sb.append("',").append(attr.getAttributeType()).append("),");
                                        }
                                        sb.delete(sb.length() - 1, sb.length());
                                        sb.append(") from dual)");
                                    }
                                } else {
                                    sb.append(", ").append(column.getDbmsColumn()).append(" = #{")
                                            .append(column.getAttributeName()).append("}");
                                }
                            } else {
                                if (!strCol.equals("titles")) {
                                    sb.append(", ").append(column.getDbmsColumn()).append(" = null");
                                }
                            }
                        }
                    }
                }
            }
            updateValues = sb.toString().substring(1);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | FoundationException e) {
            throw new FoundationException("omc.error.query.string", e);
        }
        return updateValues;
    }
    public static String convert2UpdateValues(ClassInfo classInfo, ObjectRootVO input,Set<String> attributes, boolean forBatch){
        String updateValues = null;
        try {
            String methodString = null;
            StringBuffer sb = new StringBuffer();
            String getMethodString = "get";
            for (ColumnInfo column : classInfo.getColumnList()) {
                if(attributes.contains(column.getAttributeName())){
                    if (column.getAttributeName().equals("modified")) {
                        sb.append(", ").append(column.getDbmsColumn()).append(" = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
                    } else {
                        methodString = getMethodString + column.getAttributeName().substring(0, 1).toUpperCase() + column.getAttributeName().substring(1);
                        Method[] methods = input.getClass().getMethods();
                        for (int i = 0; i <= methods.length - 1; i++) {
                            if (methodString.equals(methods[i].getName())) {
                                Object colValue = methods[i].invoke(input);
                                if (!NullUtil.isNull(colValue)) {
                                    if (colValue instanceof Boolean) {
                                        if ((boolean)colValue) {
                                            sb.append(", ").append(column.getDbmsColumn()).append(" = ").append("1");
                                        } else {
                                            sb.append(", ").append(column.getDbmsColumn()).append(" = ").append("0");
                                        }
                                    } else if (colValue instanceof Date) {
                                        if(forBatch){
                                            sb.append(", ").append(column.getDbmsColumn()).append(" = ").append(OmcDBMSConstants.DBMS_LOCAL2UTC_FUNCTION).append("(#{item.")
                                            .append(column.getAttributeName()).append("})");
                                        }else{
                                            sb.append(", ").append(column.getDbmsColumn()).append(" = ").append(OmcDBMSConstants.DBMS_LOCAL2UTC_FUNCTION).append("(#{")
                                            .append(column.getAttributeName()).append("})");
                                        }
                                    }else {
                                        if(forBatch){
                                            sb.append(", ").append(column.getDbmsColumn()).append(" = #{item.")
                                            .append(column.getAttributeName()).append("}");
                                        }else{
                                            sb.append(", ").append(column.getDbmsColumn()).append(" = #{")
                                            .append(column.getAttributeName()).append("}");
                                        }
                                    }
                                } else {
                                    if (!column.getAttributeName().equals("titles")) {
                                        sb.append(", ").append(column.getDbmsColumn()).append(" = null");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            updateValues = sb.toString().substring(1);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | FoundationException e) {
            throw new FoundationException("omc.error.query.string", e);
        }
        return updateValues;
    }

    public static String convert2UpdateSpecificValues(ObjectRootVO input){
        String updateValues = null;
        try {
            String methodString = null;
            StringBuffer sb = new StringBuffer();
            String getMethodString = "get";

            ClassInfo classInfo = ClassInfoUtil.getClassInfo(input.getClassName());
            for (ColumnInfo column : classInfo.getColumnList()) {
                String strCol = BaseFoundationUtil.convert2CamelCase(column.getColumnAlias());
                if (checkIncludeAttribute(strCol, true, classInfo)) {
                    continue;
                }
                if (strCol.equals("modified")) {
                    sb.append(", ").append(column.getDbmsColumn()).append(" = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
                } else {
                    methodString = getMethodString + strCol.substring(0, 1).toUpperCase() + strCol.substring(1);
                    Method[] methods = input.getClass().getMethods();
                    for (int i = 0; i <= methods.length - 1; i++) {
                        if (methodString.equals(methods[i].getName())) {
                            Object colValue = methods[i].invoke(input);
                            if (!NullUtil.isNull(colValue)) {
                                if (colValue instanceof Date) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat(OmcSystemConstants.OMC_JAVA_DATE_FORMAT_DATE);
                                    String strDate = dateFormat.format(colValue);
                                    sb.append(", ").append(column.getDbmsColumn())
                                            .append(" = ").append(OmcDBMSConstants.DBMS_LOCAL2UTC_FUNCTION).append("(").append(OmcDBMSConstants.DBMS_CHAR2DATE_FUNCTION).append("('").append(strDate)
                                            .append("', '").append(OmcSystemConstants.OMC_DBMS_DATE_FORMAT).append(")'))");
                                } else {
                                    sb.append(", ").append(column.getDbmsColumn()).append(" = #{")
                                            .append(column.getAttributeName()).append("}");

                                    if (strCol.equals("checkouter") && ((String)colValue).equals("1")) {
                                        sb.append(", ").append("pcheckouted").append(" = null");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            updateValues = sb.toString().substring(1);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new FoundationException("omc.error.query.string", e);
        }
        return updateValues;
    }

    public static String convert2ChangeValues(ObjectRootVO input, boolean isOwner){
        String changeValues = null;
        try {
            String getMethodString = "get";
            String methodString = null;
            StringBuffer sb = new StringBuffer();

            ClassInfo classInfo = ClassInfoUtil.getClassInfo(input.getClassName());
            for (ColumnInfo column : classInfo.getColumnList()) {
                String strCol = BaseFoundationUtil.convert2CamelCase(column.getColumnAlias());
                if (!isOwner) {
                    if (!checkChangeAttribute(strCol)) {
                        continue;
                    }
                } else {
                    if (!checkChangeAttributeOwner(strCol)) {
                        continue;
                    }
                }

                if (strCol.equals("modified")) {
                    sb.append(", ").append(column.getDbmsColumn()).append(" = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
                } else {
                    methodString = getMethodString + strCol.substring(0, 1).toUpperCase() + strCol.substring(1);
                    Method[] methods = input.getClass().getMethods();
                    for (int i = 0; i <= methods.length - 1; i++) {
                        if (methodString.equals(methods[i].getName())) {
                            Object colValue = methods[i].invoke(input);
                            if (!NullUtil.isNull(colValue)) {
                                sb.append(", ").append(column.getDbmsColumn()).append(" = '").append(colValue)
                                        .append("'");

                            }
                        }
                    }
                }

            }
            changeValues = sb.toString().substring(1);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new FoundationException("omc.error.query.string", e);
        }
        return changeValues;
    }
    

    public static String convert2DefloatValues(ObjectRootVO input){
        String changeValues = null;
        try {
            String getMethodString = "get";
            String methodString = null;
            StringBuffer sb = new StringBuffer();

            ClassInfo classInfo = ClassInfoUtil.getClassInfo(input.getClassName());
            for (ColumnInfo column : classInfo.getColumnList()) {
                String strCol = BaseFoundationUtil.convert2CamelCase(column.getColumnAlias());
                // 변경필요
                if (!checkDefloatAttribute(strCol)) {
                    continue;
                }            

                if (strCol.equals("modified")) {
                    sb.append(", ").append(column.getDbmsColumn()).append(" = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
                } else {
                    methodString = getMethodString + strCol.substring(0, 1).toUpperCase() + strCol.substring(1);
                    Method[] methods = input.getClass().getMethods();
                    for (int i = 0; i <= methods.length - 1; i++) {
                        if (methodString.equals(methods[i].getName())) {
                            Object colValue = methods[i].invoke(input);
                            if (!NullUtil.isNull(colValue)) {
                                sb.append(", ").append(column.getDbmsColumn()).append(" = '").append(colValue)
                                        .append("'");

                            }
                        }
                    }
                }

            }
            changeValues = sb.toString().substring(1);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new FoundationException("omc.error.query.string", e);
        }
        return changeValues;
    }

    
    

    private static boolean checkIncludeAttribute(String strCol, boolean bForFoundation, ClassInfo classInfo){
        if (bForFoundation) {
            if (strCol.equals("obid")                   // -- key
                    || strCol.equals("flags") || strCol.equals("className") || strCol.equals("owner")         // ObjectRoot -- member :: change
                    || strCol.equals("names") || strCol.equals("states") || strCol.equals("lifeCycle")       // BusinessObjectRoot -- memeber :: change
                    || strCol.equals("created") || strCol.equals("creator")) { return true; }
            return false;
        } else {
            if (strCol.equals("obid")                   // -- key
                    || strCol.equals("flags")
                    || strCol.equals("className")
                    || strCol.equals("owner")  // ObjectRoot -- member :: change
                    || strCol.equals("locker")
                    || strCol.equals("checkouter")
                    || strCol.equals("checkouted") // ObjectRoot -- memeber
                    || strCol.equals("names")
                    || strCol.equals("states")
                    || strCol.equals("lifeCycle")       // BusinessObjectRoot -- memeber :: change
                    || strCol.equals("revision")
                    || strCol.equals("previousObid")
                    || strCol.equals("nextObid")        // BusinessObject -- memeber
                    || strCol.equals("fromClass")
                    || (strCol.equals("fromObid") && (Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Relation)))
                    || strCol.equals("toClass") || strCol.equals("toObid")  // BusinessRelationObject -- memeber
                    || strCol.equals("created") || strCol.equals("creator")) { return true; }
            return false;
        }
    }

    private static boolean checkChangeAttribute(String strCol){
        if (strCol.equals("className")           // ObjectRoot -- member :: change
                || strCol.equals("names") || strCol.equals("states")
                || strCol.equals("lifeCycle")
                || strCol.equals("revision")    // BusinessObjectRoot -- memeber :: change
                || strCol.equals("modified") || strCol.equals("modifier")) { return true; }
        return false;

    }

    private static boolean checkChangeAttributeOwner(String strCol){
        if (strCol.equals("owner")           // ObjectRoot -- member :: change
                || strCol.equals("modified") || strCol.equals("modifier")) { return true; }
        return false;
    }
    
    private static boolean checkDefloatAttribute(String strCol){
        if ( strCol.equals("flags")    
                || strCol.equals("modified") || strCol.equals("modifier")) { return true; }
        return false;

    }
}
