package com.rap.omc.dataaccess.paging;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.dataaccess.paging.exception.PagingException;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.util.NullUtil;
@SuppressWarnings({ "unchecked", "rawtypes" })
public final class PagingHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PagingHelper.class);
    public static final String COUNT_DELIMETER = "-count-";
    public static final String INDEX_DELIMETER = "-index-";
    public static void checkValidSQL(String query, String sqlInjectionSieve){
	    String sqlSieve = sqlInjectionSieve;
	    if (NullUtil.isNone(sqlSieve)) {
	        sqlSieve = "'\";:#\\";     
	    }
	    boolean result = true;
	    StringBuffer violatedBy = new StringBuffer();

	    for (int i = 0; i < query.length(); ++i) {
	    	for (int j = 0; j < sqlSieve.length(); ++j) {
	    		if (query.charAt(i) == sqlSieve.charAt(j)) {
	    			violatedBy.append(sqlSieve.charAt(j));
	    		}
	    	}
	    }
	    if (violatedBy.length() > 0) {
	    	LOGGER.warn("checkValidSQL(String query, String sqlInjectionSieve)");
	    	result = false;
	    }
	    if ((!(result)) || (query.indexOf("--") > 0))throw new PagingException("checkValidSQL(String query, String sqlInjectionSieve)");
    }
    public static void checkOrderBySQL(String query)
    {
        if ((!(NullUtil.isNone(query))) && (!(query.trim().startsWith("order by"))))throw new PagingException("FRM_PAG_001 : checkOrderBySQL(String query)");
    }
    public static void checkSortingOrderBySQL(String query, String sqlInjectionSieve)
    {
        checkValidSQL(query, sqlInjectionSieve);
        checkOrderBySQL(query);
        
        Matcher sqlMatch = PagingConstants.SQL_PAGING_SORT_PATTERN.matcher(query);
        if ((NullUtil.notNone(query)) && (!(sqlMatch.find()))) throw new PagingException("checkSortingOrderBySQL(String query)");
    }
    public static String[] splitOrderBy(String query)
    {
        Matcher sqlMatch = PagingConstants.SQL_ORDER_BY_PATTERN.matcher(query);
        String withOutOrderBy = query;
        String orderBy = "";
        
        int lastOrderByMatchStartIndex = 0;
        while (sqlMatch.find()) {
            lastOrderByMatchStartIndex = sqlMatch.start();
        }
        String candidateForOrderByClause = query.substring(lastOrderByMatchStartIndex);
        
        if (lastOrderByMatchStartIndex > 0) {
            char[] candidateCharacters = candidateForOrderByClause.toCharArray();
            int size = candidateCharacters.length;
            int balance = 0;
            for (int inx = 0; inx < size; ++inx) {
                if (candidateCharacters[inx] == ')')/* 128 */ ++balance;
                else if (candidateCharacters[inx] == '(') {
                    --balance;
                }
            }
            if (balance == 0) {
                withOutOrderBy = query.substring(0, lastOrderByMatchStartIndex).trim();
                orderBy = query.substring(lastOrderByMatchStartIndex).trim();
            }
        }
        return new String[] { withOutOrderBy, orderBy };
    }
    public static String modifyOriginalQueryTag(String changeSql, String modifiedOriginalSql,PagingEntity pagingParameter)
    {
        String boundSql = changeSql;
        Pattern pattern = Pattern.compile("\\$[{]\\s*[oO][rR][iI][gG][iI][nN][aA][lL]_[qQ][uU][eE][rR][yY]\\s*(:\\s*[nN][oO][tT])?\\s*[}]");
        Matcher matcher = pattern.matcher(boundSql);
        while (matcher.find()) {
            String replacement = modifiedOriginalSql;
            if (replacement.indexOf("${") > -1) {
                replacement = replacement.replaceAll("\\$[{]", "#devon#");
                boundSql = boundSql.replaceFirst(
                        "\\$[{]\\s*[oO][rR][iI][gG][iI][nN][aA][lL]_[qQ][uU][eE][rR][yY]\\s*(:\\s*[nN][oO][tT])?\\s*[}]",replacement);
                boundSql = boundSql.replaceAll("#devon#", "\\${");
            } else {
                boundSql = boundSql.replaceFirst("\\$[{]\\s*[oO][rR][iI][gG][iI][nN][aA][lL]_[qQ][uU][eE][rR][yY]\\s*(:\\s*[nN][oO][tT])?\\s*[}]", replacement);
            }
        }
        return boundSql;
    }
    public static String modifyQueryWithOrderBy(String originalQuery, String orderBy)
    {
        String orderByClause = orderBy;
        if (NullUtil.notNone(orderByClause)) {
            if (existsOrderSiblingsBy(originalQuery)) {
                orderByClause = "order siblings by " + orderByClause.replaceAll("[oO][rR][dD][eE][rR]\\s*([sS][iI][bB][lL][iI][nN][gG][sS])?\\s*[bB][yY]\\s*", "").trim();
            }
            return modifyOrderByClause(originalQuery, orderByClause);
        }
        return originalQuery;
    }
    private static boolean existsOrderSiblingsBy(String sql)
    {
        Pattern sqlOrderSiblingsByPattern = Pattern.compile("\\s*[oO][rR][dD][eE][rR]\\s+[sS][iI][bB][lL][iI][nN][gG][sS]\\s+[bB][yY]\\s+", 2);
        Matcher sqlMatch = sqlOrderSiblingsByPattern.matcher(sql);
        return sqlMatch.find();
    }
    public static String fillUpOrderBy(String orderBy){
	    if (NullUtil.isNone(orderBy)) return "order by 1 asc";
	 
	    String pattern = "((?i)\\s*order\\s+(?:sibling\\s+)?by\\s+)(.*)";
	    Matcher matcher = Pattern.compile(pattern).matcher(orderBy);
	 
	    if (matcher.matches()) {
	      String orderByPart = matcher.group(1);
	      String columnPart  = matcher.group(2);
	      String[] columnList = columnPart.split(",");
	      int i = 0; 
	      while (true)
	      {
	          columnList[i] = columnList[i].trim().replaceAll("^\\n*([\\w\\.]+)\\n*$", "$1 asc");
	          ++i; 
	          if (i >= columnList.length)
	          {
	              return orderByPart + join(columnList, ", ");
	          }
	      }
	    }
	    return orderBy;
    }
    private static String join(String[] str, String concat)
    {
        String result = null;
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < str.length; ++i) {
            buf.append(str[i]);
            buf.append(concat);
        }
        result = buf.toString();
        return result.substring(0, result.length() - concat.length());
    }
	private static String modifyOrderByClause(String originalQuery, String orderBy)
    {
        String[] splitOrderBy = splitOrderBy(originalQuery);
        String withOutOrderBy = splitOrderBy[0];
        String orderByStatement = splitOrderBy[1];
        
        ArrayList changedOrderBy = new ArrayList();
        StringBuffer buffer = new StringBuffer();
        
        if (NullUtil.isNone(orderByStatement))
        {
            return originalQuery + "\n" + orderBy;
        }
        
        String temp = orderBy;
        String columnName = temp.replaceAll("(?i)(order by)", "").replaceAll("(?i)(asc)", "").replaceAll("(?i)(desc)", "").trim();
        String columnLow = orderByStatement;
        String previousColumns = columnLow.replaceAll("(?i)(order by)", "").trim();
        String[] array = previousColumns.split(",");
        
        for (int i = 0; i < array.length; ++i) {
            if (array[i].trim().split(" ")[0].equalsIgnoreCase(columnName)) continue;
                changedOrderBy.add(array[i]);
        }
        buffer.append(orderBy);
        for (int i = 0; i < changedOrderBy.size(); ++i) {
            buffer.append(", ").append(changedOrderBy.get(i));
        }
        return withOutOrderBy + "\n" + buffer.toString();
    }
	public static Map<String, String> splitCoreAndCountAndIndexQuery(String originalSql,PagingEntity pagingEntity){
        int countIndex = originalSql.indexOf("-count-");
        int indexIndex = originalSql.indexOf("-index-");
        
        String coreSql = originalSql;
        String countSql = "";
        String indexSql = "";
        if (countIndex == -1) {
            if (indexIndex != -1) {
                coreSql = originalSql.substring(0, indexIndex);
                indexSql = originalSql.substring(indexIndex + "-index-".length());
            }
        }else if (indexIndex == -1) {
        	coreSql = originalSql.substring(0, countIndex);
        	countSql = originalSql.substring(countIndex + "-count-".length());
        }else {
        	coreSql = originalSql.substring(0, countIndex);
        	countSql = originalSql.substring(countIndex + "-count-".length(), indexIndex);
        	indexSql = originalSql.substring(indexIndex + "-index-".length());
        }
        
        String modifiedCoreSql = modifyQueryWithOrderBy(coreSql, pagingEntity.getOrderBy());
        Map result = new HashMap();
        result.put("coreSql", modifiedCoreSql);
        result.put("countSql", countSql);
        result.put("indexSql", indexSql);
        return result;
    }
    public static boolean isImagePath(String path)
    {
        return path.trim().matches("\\S+\\.[_0-9a-zA-Z]+\\??");
    }
    public static int getPageOfRow(int row, int rowSizeFromConfig)
    {
        return (int)Math.ceil(row / rowSizeFromConfig);
    }
    public static int getFirstPageOfIndex(int pageIndex, int pageSizeFromCofig)
    {
        return ((pageIndex - 1) * pageSizeFromCofig + 1);
    }
    public static int getFirstRowIndex(int selectedPageIndex, int rowSizeFromConfig)
    {
        return ((selectedPageIndex - 1) * rowSizeFromConfig + 1);
    }
    public static int getIndexOfPage(int page, int numberOfPagesOfIndex)
    {
        return (int)Math.ceil(page / numberOfPagesOfIndex);
    }
}