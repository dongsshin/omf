package com.rap.omc.dataaccess.mybatis.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.ResultHandler;

import com.rap.omc.dataaccess.paging.builder.DatabaseAwarePagingQueryBuilder;
import com.rap.omc.dataaccess.paging.builder.PagingQueryBuilder;
import com.rap.omc.dataaccess.paging.handler.PagingCountHandler;
import com.rap.omc.dataaccess.paging.handler.PagingResultHandler;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.dataaccess.paging.model.PagingMap;
import com.rap.omc.dataaccess.paging.model.PagingParameterUtil;
import com.rap.omc.util.core.ClassUtil;

@SuppressWarnings({ "unchecked", "rawtypes","unused"})
@Intercepts({@org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.Executor.class, method = "query", 
                 args = {MappedStatement.class, Object.class, org.apache.ibatis.session.RowBounds.class, ResultHandler.class }),
             @org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.Executor.class, method = "query", 
                 args = {MappedStatement.class, Object.class, org.apache.ibatis.session.RowBounds.class, ResultHandler.class, org.apache.ibatis.cache.CacheKey.class, BoundSql.class }) })
public class MyBatisPagingInterceptor implements Interceptor{
    private static final String QUERY_BUILDER = "queryBuilder";
    private static final int MAPPED_STATEMENT_INDEX = 0;
    private static final int PARAMETER_INDEX = 1;
	private static final int RESULT_HANDLER_INDEX = 3;
    private PagingQueryBuilder pagingQueryBuilder = new DatabaseAwarePagingQueryBuilder();


    public void setPagingQueryBuilder(PagingQueryBuilder pagingQueryBuilder){
    	this.pagingQueryBuilder = pagingQueryBuilder;
    }

    public Object intercept(Invocation invocation) throws Throwable{
        Object[] queryArgs = invocation.getArgs();
        Object parameter = queryArgs[1];
        MappedStatement ms = (MappedStatement)queryArgs[0];
        ResultHandler resultHandler = (ResultHandler)queryArgs[3];
        
        if ((this.pagingQueryBuilder != null) && (parameter instanceof PagingEntity))
        {
            PagingEntity pagingEntity = (PagingEntity)parameter;
            if (pagingEntity instanceof PagingMap) {
                parameter = ((PagingMap)parameter).getMap();
                queryArgs[1] = parameter;
            }
            BoundSql boundSql = ms.getBoundSql(parameter);
            String queryString = boundSql.getSql().trim();
            String databaseUrl = (String)PagingParameterUtil.getPagingParameter(pagingEntity,"devonDatabaseUrl");
            if (resultHandler instanceof PagingCountHandler) {
                String countSql = this.pagingQueryBuilder.getCountSql(queryString, pagingEntity, databaseUrl);
                List resultMaps = getResultMapsForCountSql(ms);
                queryArgs[0] = makeMappedStatement(ms.getId() + "_countSql", countSql, ms, boundSql,resultMaps);
            }
            else if (resultHandler instanceof PagingResultHandler) {
                String indexSql = this.pagingQueryBuilder.getPagingSql(queryString, pagingEntity,databaseUrl);
                List resultMaps = ms.getResultMaps();
                queryArgs[0] = makeMappedStatement(ms.getId() + "_indexSql", indexSql, ms, boundSql,resultMaps);
            }
        }
        Object result = null;
        result = invocation.proceed();
        return result;
    }

    public Object plugin(Object target){
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties){
        String queryBuilder = properties.getProperty("queryBuilder");
        if (queryBuilder != null) this.pagingQueryBuilder = ((PagingQueryBuilder)ClassUtil.getInstance(queryBuilder));
    }
    private MappedStatement makeMappedStatement(String queryId, String sql, MappedStatement ms, BoundSql boundSql, List<ResultMap> resultMaps){
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),boundSql.getParameterObject());
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), queryId, new BoundSqlSqlSource(newBoundSql), ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());

        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        
        return builder.build();
    }
	private List<ResultMap> getResultMapsForCountSql(MappedStatement ms){
        List resultMaps = new ArrayList();
        String id = "-inline";
        if (ms.getResultMaps() != null) {
            id = ((ResultMap)ms.getResultMaps().get(0)).getId() + "-inline";
        }
        ResultMap resultMap = new ResultMap.Builder(null, id, Integer.class, new ArrayList()).build();
        resultMaps.add(resultMap);
        return resultMaps;
    }
    public static class BoundSqlSqlSource implements SqlSource{
        BoundSql boundSql;
        public BoundSqlSqlSource(BoundSql boundSql){
            this.boundSql = boundSql;
        }
        public BoundSql getBoundSql(Object parameterObject){
            return this.boundSql;
        }
    }
}
