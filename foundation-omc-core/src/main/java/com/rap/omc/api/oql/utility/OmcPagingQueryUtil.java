package com.rap.omc.api.oql.utility;

import java.util.regex.Matcher;

import com.rap.omc.dataaccess.paging.PagingConstants;
import com.rap.omc.dataaccess.paging.PagingHelper;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.dataaccess.paging.model.PagingParameterUtil;
import com.rap.omc.schema.util.OmcSchemaUtil;


public class OmcPagingQueryUtil {
    public static String getCountSql(String originalSql){
        String countSql = "";
        String currentDbms = OmcSchemaUtil.getCurrentDBMS();

        if ((currentDbms.indexOf("ORACLE") >= 0) || (currentDbms.indexOf("MARIA") >= 0) || (currentDbms.indexOf("MSSQL") >= 0)
                || (currentDbms.indexOf("MYSQL") >= 0)) {
            countSql = "select count(*) from (" + originalSql + ") omcSubQuery";
        } else if (currentDbms.indexOf("DB2") >= 0)
            countSql = "select count(*) from (" + originalSql + ") as omcSubQuery";
        else {
            countSql = "select count(*) from (" + originalSql + ") omcSubQuery";
        }
        return countSql;
    }

    public static String getPagingSql(String originalSql, int firstRow, int lastRow, int limit){
        String currentDbms = OmcSchemaUtil.getCurrentDBMS();
        String indexSql = originalSql;
        PagingEntity pagingEntity = new PagingEntity();
        if (currentDbms.indexOf("ORACLE") >= 0) {
            indexSql = buildOracleStyleIndexSql(originalSql, firstRow, lastRow, limit);
        } else if (currentDbms.indexOf("DB2") >= 0) {
            indexSql = buildDB2StyleIndexSql(pagingEntity, originalSql, firstRow, lastRow);
        } else if (currentDbms.indexOf("MSSQL") >= 0) {
            indexSql = buildMssqlStyleIndexSql(pagingEntity, originalSql, firstRow, limit);
        } else if ((currentDbms.indexOf("MYSQL") >= 0) ||(currentDbms.indexOf("MARIA") >= 0)){
            indexSql = buildMysqlStyleIndexSql(originalSql, firstRow, limit);
        }

        return indexSql;
    }

    private static String buildOracleStyleIndexSql(String originalQuery, int firstRow, int lastRow, int limit){
        String indexSql = "select * from(\n select  inner_temp.*, rownum as omcindex from  (" + " " + originalQuery
                + "  ) inner_temp where rownum <= " + lastRow + "\n" + ") where omcindex >= " + firstRow;
        return indexSql;
    }

    private static String buildDB2StyleIndexSql(PagingEntity pagingEntity, String originalQuery, int firstRow, int lastRow){
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

        String indexSql = "select * from(\n select  devoninlineview1.* , row_number() over( " + orderBy
                + " ) as devonindex from (" + "  " + withOutOrderBy + " ) as devoninlineview1 " + "\n"
                + ") as devoninlineview2 where devoninlineview2.devonindex  between " + firstRow + " and " + lastRow;
        return indexSql;
    }

    private static String buildMssqlStyleIndexSql(PagingEntity pagingEntity, String originalQuery, int firstRow,
            int limitInt){
        int limit = limitInt;

        int totalRows = ((Integer)PagingParameterUtil.getPagingParameter(pagingEntity, "rows")).intValue();
        int userRowSize = totalRows - pagingEntity.getTargetRow() + 1;
        if (userRowSize < pagingEntity.getRowSize()) {
            limit = userRowSize;
        }

        String[] splitOrderBy = PagingHelper.splitOrderBy(originalQuery);
        String withOutOrderBy = splitOrderBy[0];
        String orderBy = PagingHelper.fillUpOrderBy(splitOrderBy[1]);

        String orderByDesc = orderBy.replaceAll("(\\s+(?i)(desc)(?=\\s+|\\,|$))", " #devon#asc")
                .replaceAll("(\\s+(?i)(asc)(?=\\s+|\\,|$))", " #devon#desc").replaceAll("#devon#", "");
        String mssqlQuery = "select top " + (limit + firstRow - 1) + " "
                + withOutOrderBy.replaceFirst("[sS][eE][lL][eE][cC][tT]", "").trim();

        String indexSql = "select * from(\n select top " + limit + " * from( " + "\n" + "  " + mssqlQuery + " "
                + orderBy + "\n" + " ) as devoninlineview1 "
                + orderByDesc.replaceAll("\\s*\\w+\\.", " devoninlineview1.") + "\n" + ") as devoninlineview2 "
                + orderBy.replaceAll("\\s*\\w+\\.", " devoninlineview2.");
        return indexSql;
    }

    private static String buildMysqlStyleIndexSql(String originalQuery, int firstRow, int limit){
        return originalQuery + " limit " + limit + " offset " + (firstRow - 1);
    }

    private static String buildHsqldbStyleIndexSql(PagingEntity pagingEntity, String originalQuery, int firstRow, int limit){
        String[] splitOrderBy = PagingHelper.splitOrderBy(originalQuery);
        String withOutOrderBy = splitOrderBy[0];
        String orderBy = PagingHelper.fillUpOrderBy(splitOrderBy[1]);

        String hsqlQuery = "select limit " + (firstRow - 1) + " " + limit + " "
                + withOutOrderBy.replaceFirst("[sS][eE][lL][eE][cC][tT]", "").trim();
        return hsqlQuery + " " + orderBy;
    }
}
