/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcDBMSConstants.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 29.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.util;

/**
 * <pre>
 * Class : OmcDBMSConstants
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcDBMSConstants {
    public static final String C_DBMS_ORACLE      = "ORACLE";
    public static final String C_DBMS_DB2         = "DB2";
    public static final String C_DBMS_MARIA       = "MARIA";
    public static final String C_DBMS_MSSQL       = "MSSQL";
    public static final String C_DBMS_MYSQL       = "MYSQL";
    
    public static final String DBMS_DEFAULT_TIME_ZONE = "+09:00";
    //ALTER SESSION SET TIME_ZONE
    public static final String DBMS_CHANGE_TIME_ZONE_SQL = "set time_zone = ";
    public static final String DBMS_CURRENT              = OmcSchemaUtil.getCurrentDBMS();
    public static final String DBMS_SYSTEM_DATE          = OmcSchemaUtil.getSystemDateStr();
    public static final String DBMS_LOCAL_DATE           = OmcSchemaUtil.getSystemDateStr();
    public static final String DBMS_NULL_FUNCTION        = OmcSchemaUtil.getNullFunction();
    public static final int    DBMS_IN_CLAUSE_MAX        = OmcSchemaUtil.getDbmsInMaxCount();
    public static final String DBMS_LOCAL2UTC_FUNCTION       = OmcSchemaUtil.getConvertLocalToUtcDateStr();
    public static final String DBMS_UTC2LOCAL_FUNCTION       = OmcSchemaUtil.getConvertUtcToLocalDateStr();
    public static final String DBMS_LOCAL2UTC_FUNCTION_CHAR  = OmcSchemaUtil.getConvertLocalToUtcDateStr() + "Char";
    public static final String DBMS_UTC2LOCAL_FUNCTION_CHAR  = OmcSchemaUtil.getConvertUtcToLocalDateStr() + "Char";
    public static final String DBMS_DATE2CHAR_FUNCTION       = OmcSchemaUtil.getConvertDate2Char();
    public static final String DBMS_CHAR2DATE_FUNCTION       = OmcSchemaUtil.getConvertChar2Date();
    
    public static final String OMC_USER_DBMS_COLUMN              = "#{omcDbColumn}";
    public static final String OMC_USER_DBMS_ALIAS               = "#{omcColumnAlias}";
    public static final String OMC_USER_TITLE_STR                = "case when " + OMC_USER_DBMS_COLUMN + " is null or " + OMC_USER_DBMS_COLUMN + " = '1' then " + OMC_USER_DBMS_COLUMN + " else " + DBMS_NULL_FUNCTION + "((select xxx.pdescriptions from psysuser xxx where xxx.pnames = " + OMC_USER_DBMS_COLUMN+ ")," + OMC_USER_DBMS_COLUMN+ ") end as \"" + OMC_USER_DBMS_ALIAS + "\"";
    public static final String OMC_USER_OBID_STR                 = "case when " + OMC_USER_DBMS_COLUMN + " is null or " + OMC_USER_DBMS_COLUMN + " = '1' then null else (select xxx.obid from ptuser xxx where xxx.pclass_name = 'Users' and xxx.pnames = " + OMC_USER_DBMS_COLUMN+ ") end as \"" + OMC_USER_DBMS_ALIAS + "\"";
}
