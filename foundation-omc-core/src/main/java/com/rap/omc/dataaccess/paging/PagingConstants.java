package com.rap.omc.dataaccess.paging;

import java.util.regex.Pattern;

public class PagingConstants{
    public static final String TARGET_ROW = "targetRow";
    public static final String ROW_SIZE = "rowSize";
    public static final String ROWS = "rows";
    public static final String DATABASE_URL = "devonDatabaseUrl";
    public static final String ORDER_BY = "orderBy";
    public static final String ORIGINAL_QUERY_WITHOUT_ORDERBY = "ORIGINAL_QUERY_WITHOUT_ORDERBY";
    public static final String SQL_ORDER_BY_PATTERN_TAG = "[oO][rR][dD][eE][rR]\\s*([sS][iI][bB][lL][iI][nN][gG][sS])?\\s*[bB][yY]\\s*";
    public static final String SQL_PAGING_SORT_PATTERN_TAG = "[oO][rR][dD][eE][rR]\\s*([sS][iI][bB][lL][iI][nN][gG][sS])?\\s*[bB][yY]\\s*[\\w가-힣\\s.]*$";
    public static final Pattern SQL_ORDER_BY_PATTERN = Pattern.compile("[oO][rR][dD][eE][rR]\\s*([sS][iI][bB][lL][iI][nN][gG][sS])?\\s*[bB][yY]\\s*", 2);
    public static final Pattern SQL_PAGING_SORT_PATTERN = Pattern.compile("[oO][rR][dD][eE][rR]\\s*([sS][iI][bB][lL][iI][nN][gG][sS])?\\s*[bB][yY]\\s*[\\w가-힣\\s.]*$", 2);
    public static final String IMAGE_PATH = "\\S+\\.[_0-9a-zA-Z]+\\??";
    public static final String BLANK = "\\s*";
    public static final String LINE_BREAK_CHAR = "\n";
    public static final String ORACLE_KEY = ":oracle:";
    public static final String DB2_KEY = ":db2:";
    public static final String DERBY_KEY = ":derby:";
    public static final String MSSQL_KEY = ":sqlserver:";
    public static final String MYSQL_KEY = ":mysql:";
    public static final String MARIA_KEY = ":maria:";
    public static final String POSTGRES_KEY = ":postgres:";
    public static final String TIBERO_KEY = ":tibero:";
    public static final String EDB_KEY = ":edb:";
    public static final String HSQLDB_KEY = ":hsqldb:";
}