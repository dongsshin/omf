package com.rap.omc.dataaccess.mybatis.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rap.omc.util.NullUtil;
@Intercepts({
        @Signature(type = Executor.class, method = "update"  ,args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query"   ,args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }),
        @Signature(type = Executor.class, method = "query"   ,args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }) })
public class QueryLoggingInterceptor implements Interceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(QueryLoggingInterceptor.class);
	@SuppressWarnings("rawtypes")
	@Override
    public Object intercept(Invocation invocation) throws Exception{
		LOGGER.info("********************************QueryLoggingInterceptor intercepted*******************************");
        Object[] args = invocation.getArgs();
        Object param = args[1];
        MappedStatement mappedStatement = (MappedStatement)args[0];
        BoundSql boundSql = mappedStatement.getBoundSql(param);
        String sql = boundSql.getSql();
        StringBuffer sb = new StringBuffer();
        if (param == null) {
            sb.append("[  ]");
        } else {
            sb.append("[ ");
            if (param instanceof Integer || param instanceof Long || param instanceof Float || param instanceof Double) {
                sb.append(param.toString());
            } else if (param instanceof String) {
                sb.append(param);
            } else if (param instanceof Map) {
            	boolean isFirst = true;
                if (NullUtil.isNull(((Map<?, ?>)param).get("list"))) {
                    List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
                    for (int inx = 0; inx < paramMapping.size(); inx++) {
                    	if(!isFirst) sb.append(",");
                        ParameterMapping mapping = paramMapping.get(inx);
                        String propValue = mapping.getProperty();
                        if(propValue != null && propValue.startsWith("__frch")) {
                        	sb.append("{" + propValue.replace("__frch_item_", "") + "}");
                        }else {
                            Object value = ((Map<?, ?>)param).get(propValue);
                            if (NullUtil.isNull(value)) {
                            	sb.append("");
                            } else if (value instanceof String) {
                            	sb.append(value);
                            } else {
                            	sb.append(value.toString());
                            }
                        }
                        isFirst = false;
                    }
                } else {
                    try {
                        Gson gson = new Gson();
                        Object temp = ((Map<?, ?>)param).get("list");
                        List list = (ArrayList)temp;
                        for (int inx = 0; inx < list.size(); inx++) {
                            JsonElement jsonElement = gson.toJsonTree(list.get(inx));
                            JsonObject jsonObject = jsonElement.getAsJsonObject();
                            sb.append(jsonObject.toString());
                            if (list.size() != inx + 1) {
                                sb.append(", ");
                            }
                        }
                    } catch (java.lang.IllegalArgumentException e) {
                        sb.append("JSON Converting Error!! " + e.getMessage());
                    }
                }
                // 해당 파라미터가 사용자 정의 클래스인 경우
            } else {
                List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
                try {
                    Gson gson = new Gson();
                    JsonElement jsonElement = gson.toJsonTree(param);
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    for (int inx = 0; inx < paramMapping.size(); inx++) {
                        ParameterMapping mapping = paramMapping.get(inx);
                        String propValue = mapping.getProperty();
                        if (!NullUtil.isNull(jsonObject.get(propValue))) {
                            String paramValue = jsonObject.get(propValue).toString();
                            sb.append(paramValue.replaceAll("\"", ""));
                        } else {
                            sb.append("");
                        }
                        if (paramMapping.size() != inx + 1) {
                            sb.append(", ");
                        }
                    }
                } catch (java.lang.IllegalArgumentException e) {
                    sb.append("JSON Converting Error!! " + e.getMessage());
                }
            }
            sb.append(" ]");
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("sql", sql);
        map.put("paramInfo", sb.toString());
        TraceContext.local.set(map);
        return invocation.proceed();
    }

    /**
     * MyBatis의 Interceptor 를 implements할 때 override해야 하는 기본 메소드
     * 현재 Interceptor를 MyBatis Plugin으로 등록한다.
     *
     * @param target MyBatis의 Plugin Context
     */
    @Override
    public Object plugin(Object target){
        return Plugin.wrap(target, this);
    }

    /**
     * MyBatis의 Interceptor 를 implements할 때 override해야 하는 기본 메소드
     * Interceptor를 Configuration파일에 설정시 지정 가능한 property값을 처리하는 callback 메소드
     *
     * @param properties Intercepor를 Configuration파일에 설정시 지정 가능한 property값
     */
    @Override
    public void setProperties(Properties properties){
        // no properties required.
    }

}
