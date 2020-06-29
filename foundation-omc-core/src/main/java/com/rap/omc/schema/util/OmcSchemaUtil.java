/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 31.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.util;

/**
 * <pre>
 * Class : OmcSchemaUtil
 * Description : OMC에서 사용되어지는 상수 중 DBMS Dependent관련 정보를 관리함.
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaUtil {

    public static String getObjectId(){
        return "getObjectId";
    }
    /**
     * 현재 정의되어진 DBMS Type를 Return함.
     *
     * @return
     */
    public static String getCurrentDBMS() {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return(OmcDBMSConstants.C_DBMS_ORACLE);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))//
            return(OmcDBMSConstants.C_DBMS_DB2);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return(OmcDBMSConstants.C_DBMS_MARIA);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return(OmcDBMSConstants.C_DBMS_MSSQL);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return(OmcDBMSConstants.C_DBMS_MYSQL);
        return null;
    }
    public static String getSystemDateStr() {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return(OmcSystemConstants.DBMS_SYSDATE_ORACLE);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))//
            return(OmcSystemConstants.DBMS_SYSDATE_DB2);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return(OmcSystemConstants.DBMS_SYSDATE_MARIA);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return(OmcSystemConstants.DBMS_SYSDATE_MSSQL);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return(OmcSystemConstants.DBMS_SYSDATE_MYSQL);
        return null;
    }
    public static String getConvertUtcToLocalDateStr() {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return("omcConvertUtcToLocal");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))//
            return("omcConvertUtcToLocal");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return("omcConvertUtcToLocal");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return("omcConvertUtcToLocal");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return("omcConvertUtcToLocal");
        return null;
    }
    public static String getConvertLocalToUtcDateStr() {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return("omcConvertLocalToUtc");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))//
            return("omcConvertLocalToUtc");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return("omcConvertLocalToUtc");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return("omcConvertLocalToUtc");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return("omcConvertLocalToUtc");
        return null;
    }
    public static String getConvertLocalCharToUtcDateStr(String parameterName) {
        
        StringBuffer strBuf = new StringBuffer();
        
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            strBuf.append("omcConvertLocalCharToUtc").append("(#{").append(parameterName).append( "},'").append(OmcSystemConstants.OMC_DBMS_DATE_FORMAT).append("')");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))//
            strBuf.append("omcConvertLocalCharToUtc").append("(#{").append(parameterName).append( "},'").append(OmcSystemConstants.OMC_DBMS_DATE_FORMAT).append("')");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            strBuf.append("CONVERT_TZ(STR_TO_DATE").append("(#{").append(parameterName).append( "},'").append(OmcSystemConstants.OMC_DBMS_DATE_FORMAT).append("')").append(" ,@@session.time_zone,'+00:00')");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            strBuf.append("omcConvertLocalCharToUtc").append("(#{").append(parameterName).append( "},'").append(OmcSystemConstants.OMC_DBMS_DATE_FORMAT).append("')");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            strBuf.append("CONVERT_TZ(STR_TO_DATE").append("(#{").append(parameterName).append( "},'").append(OmcSystemConstants.OMC_DBMS_DATE_FORMAT).append("')").append(" ,@@session.time_zone,'+00:00')");
        return strBuf.toString();
    }
    public static String getConvertDate2Char() {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return("TO_CHAR");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))//
            return("STR_TO_DATE");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return("STR_TO_DATE");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return("STR_TO_DATE");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return("STR_TO_DATE");
        return null;
    }
    public static String getConvertChar2Date() {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return("TO_CHAR");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))//
            return("STR_TO_DATE");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return("STR_TO_DATE");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return("STR_TO_DATE");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return("STR_TO_DATE");
        return null;
    }
    public static String getBitAndStr(String v1, String v2, String v3) {
        return(getBitAndStr(v1,v2,v3,true));
    }
    public static String getBitAndStr(String v1, Long v2, Long v3) {
        return(getBitAndStr(v1,v2,v3,true));
    }
    public static String getBitAndStr(String v1, int v2, int v3) {
        return(getBitAndStr(v1,v2,v3,true));
    }
    public static String getBitAndStr(String v1, String v2, String v3, boolean includeAnd) {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            if (includeAnd)return("and omcBitAnd(" + v1 + "," + v2 + ") = " + v3); else return(" omcBitAnd(" + v1 + "," + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))
            if (includeAnd)return("and omcBitAnd(" + v1 + "," + v2 + ") = " + v3); else return(" omcBitAnd(" + v1 + "," + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            if (includeAnd) return("and omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);else return(" omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            if (includeAnd) return("and omcBitAnd(" + v1 + " , " + v2 + ") = " + v3); else return(" omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            if (includeAnd) return("and omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);else return(" omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);
        return null;        
    }
    public static String getBitAndStr(String v1, Long v2, Long v3, boolean includeAnd) {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            if (includeAnd)return("and omcBitAnd(" + v1 + "," + v2 + ") = " + v3); else return(" omcBitAnd(" + v1 + "," + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))
            if (includeAnd)return("and omcBitAnd(" + v1 + "," + v2 + ") = " + v3); else return(" omcBitAnd(" + v1 + "," + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            if (includeAnd) return("and omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);else return(" omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            if (includeAnd) return("and omcBitAnd(" + v1 + " , " + v2 + ") = " + v3); else return(" omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            if (includeAnd) return("and omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);else return(" omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);
        return null;        
    }
    public static String getBitAndStr(String v1, int v2, int v3, boolean includeAnd) {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            if (includeAnd)return("and omcBitAnd(" + v1 + "," + v2 + ") = " + v3); else return(" omcBitAnd(" + v1 + "," + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))
            if (includeAnd)return("and omcBitAnd(" + v1 + "," + v2 + ") = " + v3); else return(" omcBitAnd(" + v1 + "," + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            if (includeAnd) return("and omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);else return(" omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            if (includeAnd) return("and omcBitAnd(" + v1 + " , " + v2 + ") = " + v3); else return(" omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            if (includeAnd) return("and omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);else return(" omcBitAnd(" + v1 + " , " + v2 + ") = " + v3);
        return null;        
    }
    
    public static String getBitOrStr(long v1, String v2) {
        return(getBitOrStr(String.valueOf(v1),String.valueOf(v2)));
    }
    public static String getBitOrStr(String v1, long v2) {
        return(getBitOrStr(String.valueOf(v1),String.valueOf(v2)));
    }
    public static String getBitOrStr(long v1, long v2) {
        return(getBitOrStr(String.valueOf(v1),String.valueOf(v2)));
    }
    public static String getBitOrStr(String v1, String v2) {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return("omcBitOr(" + v1 + "," + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))
            return("omcBitOr(" + v1 + "," + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return("omcBitOr(" + v1 + " , " + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return("omcBitOr(" + v1 + " , " + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return("omcBitOr(" + v1 + " , " + v2 + ")");
        return null;
    }
    
    public static String getNullFunction() {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return("nvl");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))
            return("coalesce");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return("ifnull");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return("isnull");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return("ifnull");
        return null;
    }
    public static String getBitXorStr(String v1, String v2) {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return("omcBitXor(" + v1 + "," + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))
            return("omcBitXor(" + v1 + "," + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        return null;
    }
    public static String getBitXorStr(long v1, String v2) {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return("omcBitXor(" + v1 + "," + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))
            return("omcBitXor(" + v1 + "," + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        return null;
    }
    public static String getBitXorStr(String v1, long v2) {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return("omcBitXor(" + v1 + "," + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))
            return("omcBitXor(" + v1 + "," + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        return null;
    }
    public static String getBitXorStr(long v1, long v2) {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return("omcBitXor(" + v1 + "," + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))
            return("omcBitXor(" + v1 + "," + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return("omcBitXor(" + v1 + " , " + v2 + ")");
        return null;
    }
    public static int getDbmsInMaxCount() {
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            return(OmcSystemConstants.DBMS_IN_MAX_COUNT_ORACLE);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))//
            return(OmcSystemConstants.DBMS_IN_MAX_COUNT_DB2);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            return(OmcSystemConstants.DBMS_IN_MAX_COUNT_MARIA);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            return(OmcSystemConstants.DBMS_IN_MAX_COUNT_MSSQL);
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            return(OmcSystemConstants.DBMS_IN_MAX_COUNT_MARIA);
        return 500;
    }
    public static String convertDbColumn2AttrName(String dbmsColumn) {
        char[] a = dbmsColumn.toCharArray();
        char[] r = new char[a.length];
        int ri = 0;
        boolean b = false;
        for(int i = 0; i < a.length; i++ ){
            if(a[i] == '_'){
                b = true;
            }
            else
            {
                if(b) {
                    r[ri++] = Character.toUpperCase(a[i]);
                    b = false;
                }else
                {
                    r[ri++] = Character.toLowerCase(a[i]);
                }
            }
        }
        return(String.valueOf(r).trim());
    }
}