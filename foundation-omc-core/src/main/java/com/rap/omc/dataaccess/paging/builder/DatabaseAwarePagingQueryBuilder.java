package com.rap.omc.dataaccess.paging.builder;

import java.util.Map;
import java.util.regex.Matcher;

import com.rap.omc.dataaccess.paging.PagingConstants;
import com.rap.omc.dataaccess.paging.PagingHelper;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.dataaccess.paging.model.PagingParameterUtil;
import com.rap.omc.util.NullUtil;
@SuppressWarnings("rawtypes")
public class DatabaseAwarePagingQueryBuilder/*     */ implements PagingQueryBuilder<PagingEntity>{
	public String getCountSql(String originalSql, PagingEntity pagingEntity, String databaseUrl){
	    
		Map parseSqlMap = PagingHelper.splitCoreAndCountAndIndexQuery(originalSql, pagingEntity);
	    if (!(NullUtil.isNone((String)parseSqlMap.get("countSql")))) {
	        return PagingHelper.modifyOriginalQueryTag((String)parseSqlMap.get("countSql"),(String)parseSqlMap.get("coreSql"), pagingEntity);
	     }
	
	    String[] splitOrderBy = PagingHelper.splitOrderBy((String)parseSqlMap.get("coreSql"));
	    String originalQueryWithOutOrderBy = splitOrderBy[0];
	    
	    String countSql = "";
	    if ((databaseUrl.indexOf(":oracle:") > 0) || (databaseUrl.indexOf(":sqlserver:") > 0) ||
	        (databaseUrl.indexOf(":tibero:") > 0) || (databaseUrl.indexOf(":hsqldb:") > 0) ||
	        (databaseUrl.indexOf(":mysql:") > 0) || (databaseUrl.indexOf(":maria:") > 0) ||
	        (databaseUrl.indexOf(":edb:") > 0) || (databaseUrl.indexOf(":postgres:") > 0)) {
	        countSql = "select count(1) from (" + originalQueryWithOutOrderBy + ") devonSubQuery";
	     }
	     else if (databaseUrl.indexOf(":db2:") > 0) {
	    	 countSql = "select count(1) from (" + originalQueryWithOutOrderBy + ") as devonSubQuery";
	     }else {
	    	 countSql = "select count(1) from (" + originalQueryWithOutOrderBy + ") devonSubQuery";
	     }
	     return countSql;
	}
	
	public String getPagingSql(String originalSql, PagingEntity pagingEntity, String databaseUrl){
	    Map parseSqlMap = PagingHelper.splitCoreAndCountAndIndexQuery(originalSql, pagingEntity);
	    if (!(NullUtil.isNone((String)parseSqlMap.get("indexSql")))) {
	        return PagingHelper.modifyOriginalQueryTag((String)parseSqlMap.get("indexSql"),(String)parseSqlMap.get("coreSql"), pagingEntity);
	    }
	    String originalQuery = (String)parseSqlMap.get("coreSql");
	    int firstRow = pagingEntity.getTargetRow();
	    int lastRow = pagingEntity.getTargetRow() + pagingEntity.getRowSize() - 1;
	    int limit = pagingEntity.getRowSize();
	    
	    String indexSql = originalQuery;
	    if ((databaseUrl.indexOf(":oracle:") > 0) || (databaseUrl.indexOf(":tibero:") > 0)) {
	    	indexSql = buildOracleStyleIndexSql(originalQuery, firstRow, lastRow, limit);
	    }
	    else if (databaseUrl.indexOf(":db2:") > 0) {
	    	indexSql = buildDB2StyleIndexSql(pagingEntity, originalQuery, firstRow, lastRow);
	    }
	    else if (databaseUrl.indexOf(":sqlserver:") > 0) {
	    	indexSql = buildMssqlStyleIndexSql(pagingEntity, originalQuery, firstRow, limit);
	    }
	    else if ((databaseUrl.indexOf(":mysql:") > 0) || (databaseUrl.indexOf(":maria:") > 0) ||
	             (databaseUrl.indexOf(":postgres:") > 0) || (databaseUrl.indexOf(":edb:") > 0)) {
	        indexSql = buildMysqlStyleIndexSql(originalQuery, firstRow, limit);
	    }
	    else if (databaseUrl.indexOf(":hsqldb:") > 0) {
	        indexSql = buildHsqldbStyleIndexSql(pagingEntity, originalQuery, firstRow, limit);
	    }
	    return indexSql;
	}
	
	protected String buildOracleStyleIndexSql(String originalQuery, int firstRow, int lastRow, int limit) {
		String indexSql = "select * from(select inner_temp.*, rownum as devonindex from  (" +  " " + originalQuery +  "  ) inner_temp where rownum <= " + lastRow + "\n" + ") where devonindex >= " + firstRow;
		return indexSql;
	}
	protected String buildDB2StyleIndexSql(PagingEntity pagingEntity, String originalQuery, int firstRow, int lastRow){
		String[] splitOrderBy = PagingHelper.splitOrderBy(originalQuery);
		String withOutOrderBy = splitOrderBy[0];
		String orgOrderBy = splitOrderBy[1];
	    
	    String orderBy = orgOrderBy;
	    Matcher sqlMatch = PagingConstants.SQL_ORDER_BY_PATTERN.matcher(orderBy);
	    if (sqlMatch.find()) {
	        int eIndex = sqlMatch.end();
	        orderBy = ", " + orderBy.substring(eIndex);
	        orderBy = orderBy.replaceAll("\\,\\s*\\w+\\.", ", devoninlineview1.");
	        orderBy = "order by " + orderBy.substring(2);
	    }
	    String indexSql = "select * from(\n select  devoninlineview1.* , row_number() over( " + orderBy + " ) as devonindex from (" + "  " + withOutOrderBy +" ) as devoninlineview1 " + "\n" + ") as devoninlineview2 where devoninlineview2.devonindex  between " + firstRow + " and " + lastRow;
	    return indexSql;
	}
	protected String buildMssqlStyleIndexSql(PagingEntity pagingEntity, String originalQuery, int firstRow, int limitInt){
	    int limit = limitInt;
	    
	    int totalRows = ((Integer)PagingParameterUtil.getPagingParameter(pagingEntity, "rows")).intValue();
	    int userRowSize = totalRows - pagingEntity.getTargetRow() + 1;
	    if (userRowSize < pagingEntity.getRowSize()) {
	        limit = userRowSize;
	    }
	    String[] splitOrderBy = PagingHelper.splitOrderBy(originalQuery);
	    String withOutOrderBy = splitOrderBy[0];
	    String orderBy = PagingHelper.fillUpOrderBy(splitOrderBy[1]);
	    
	    String orderByDesc = orderBy.replaceAll("(\\s+(?i)(desc)(?=\\s+|\\,|$))", " #devon#asc").replaceAll("(\\s+(?i)(asc)(?=\\s+|\\,|$))", " #devon#desc").replaceAll("#devon#", "");
	    String mssqlQuery = "select top " + (limit + firstRow - 1) + " " + withOutOrderBy.replaceFirst("[sS][eE][lL][eE][cC][tT]", "").trim();
	    String indexSql = "select * from(\n select top " + limit + " * from( " + "\n" + "  " + mssqlQuery + " " + orderBy + "\n" + " ) as devoninlineview1 " + orderByDesc.replaceAll("\\s*\\w+\\.", " devoninlineview1.") + "\n" + ") as devoninlineview2 " + orderBy.replaceAll("\\s*\\w+\\.", " devoninlineview2.");
	    return indexSql;
	}
	protected String buildMysqlStyleIndexSql(String originalQuery, int firstRow, int limit){
	    return originalQuery + " limit " + limit + " offset " + (firstRow - 1);
	}
	protected String buildHsqldbStyleIndexSql(PagingEntity pagingEntity, String originalQuery, int firstRow,int limit){
	    String[] splitOrderBy = PagingHelper.splitOrderBy(originalQuery);
	    String withOutOrderBy = splitOrderBy[0];
	    String orderBy = PagingHelper.fillUpOrderBy(splitOrderBy[1]);
	    
	    String hsqlQuery = "select limit " + (firstRow - 1) + " " + limit + " " + withOutOrderBy.replaceFirst("[sS][eE][lL][eE][cC][tT]", "").trim();
	    return hsqlQuery + " " + orderBy;
	}
}