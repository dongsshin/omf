/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : LimitCountInterceptor.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 4. 7.  hyeyoung.park   Initial
 * ===========================================
 */
package com.rap.omc.dataaccess.mybatis.interceptor;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.dataaccess.exception.DaoException;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : LimitCountInterceptor
 * Description : 개별 쿼리 별로 limitCount를 설정할때는 RowBounds 클래스를 사용하면 되는데,
 *               전역 설정을 통해 전체 쿼리의 limitCount를 설정하고 싶을때는 본 인터셉터를 사용하게 된다.
 *              @ Procedure call을 통해 cursor를 리턴받은 경우에도 Limit Count를 적용하기 위한 처리 추가
 *                (DevOnFrame에서 제공하는 LimitCountInterceptor에서 코드 추가)
 * </pre>
 * 
 * @author hyeyoung.park
 */
@Intercepts({
    @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }),
    @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class })
})
public class LimitCountInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LimitCountInterceptor.class);

    private static final String LIMIT_COUNT = "limitCount";
    private static final String DATA_CUT = "dataCut";

    private int limitCount = RowBounds.NO_ROW_LIMIT;

    private boolean dataCut;

    /**
     * LimitCountInterceptor Constructor
     */
    public LimitCountInterceptor() {}

    /**
     * LimitCountInterceptor Constructor
     *
     * @param limitCount 최대 조회 가능 건수
     * @param dataCut 최대 조회 제한 건수 초과시 예외를 발생시킬지 최대 건수만큼만 잘라서 반환할지 여부
     *            (false 이면 Exception발생, true이면 최대건수만큼 자름)
     */
    public LimitCountInterceptor(int limitCount, boolean dataCut) {
        if (limitCount <= 1) { throw new DaoException("DSOCDI001", "limitCount must be greater than 1."); }
        this.limitCount = limitCount;
        this.dataCut = dataCut;
    }

    /**
     * MyBatis 의 쿼리 실행 메소드가 호출될 때 시점을 intercept하는 메소드로 설정된 최대 제한 건수를
     * 초과 했는지 여부등을 체크하고 필요시 Exception을 발생시킨다.
     *
     * @param invocation Proxy 처리를 위한 Invocation 객체
     */
    @SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Exception {
        if (this.limitCount == RowBounds.NO_ROW_LIMIT) { return invocation.proceed(); }
        Object[] args = invocation.getArgs();
        //Default로  Query를 실행하는 경우에는 XML에 지정되어져 있는 configuration.xml에 설정되어져 있는 limitCount를 가지고 Limit가 체크 되어진다.
        RowBounds argRowBounds = (RowBounds)args[2];
        if(isUnlimited(invocation)){
            argRowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, limitCount + 1);
        }else{
            //checkLimitCount에서 Count Check가 정상적으로 되기 위해서는 limitCount의 값을 argument로 넘어온 것으로 Setting해주어야 함.
            this.limitCount = argRowBounds.getLimit();
            argRowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, limitCount + 1);
        }
        Object result = invocation.proceed();
        if (result instanceof List) {
            List<Object> list = (List<Object>)result;
            if (NullUtil.isNone(list)) {                
                if (args.length > 2) {
                    Object paramObj = args[1];
                    if (paramObj instanceof Map) {
                        Map<String, Object> parameter = (Map<String, Object>)args[1];
                        Object cursorResult = parameter.get("sysRefcursor");
                        if (cursorResult instanceof List) {
                            list = (List<Object>)cursorResult;
                        }
                    }
                }
            }
            checkLimitCount(args, list);
        }
        return result;
    }
    /**
     * 리턴된 조회 결과 List가 설정된 최대 제한 건수를 초과 했는지 여부등을 체크하고 필요시 Exception을 발생시킨다.
     *
     * @param args 쿼리 실행 메소드 호출시 사용된 파라미터 정보
     * @param result 쿼리 조회 결과 List
     */
    private void checkLimitCount(Object[] args, List<?> list) {
        if (list.size() > limitCount) {
            MappedStatement mappedStatement = (MappedStatement)args[0];
            if (dataCut) {
                LOGGER.warn("Query ID[{}] has returned result data exceeding limit count : {}", mappedStatement.getId(), limitCount);
                LOGGER.warn("But because 'dataCut' is 'true', first {} result data has returned.", limitCount);
                list.remove(list.size() - 1);

            } else {
                throw new DaoException("DSOCDI002", "Query ID[" + mappedStatement.getId() + "] has returned result data exceeding limit count : " + limitCount);
            }
        }
    }

    /**
     * 사용자가 Java Code 내에서 RowBounds 객체를 직접 사용했는지 여부를 반환한다.
     *
     * @param invocation Proxy 처리를 위한 Invocation 객체
     * @return 사용자가 Java Code 내에서 RowBounds 객체를 직접 사용했는지 여부
     */
    private boolean isUnlimited(Invocation invocation) {
        Object[] args = invocation.getArgs();
        RowBounds rowBounds = (RowBounds)args[2];
        //if (rowBounds.getOffset() != RowBounds.NO_ROW_OFFSET || rowBounds.getLimit() != RowBounds.NO_ROW_LIMIT) { return true; }
        if (rowBounds.getLimit() == RowBounds.NO_ROW_LIMIT) { return true; }
        return false;
    }

    /**
     * MyBatis의 Interceptor 를 implements할 때 override해야 하는 기본 메소드
     * 현재 Interceptor를 MyBatis Plugin으로 등록한다.
     *
     * @param target MyBatis의 Plugin Context
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * MyBatis의 Interceptor 를 implements할 때 override해야 하는 기본 메소드
     * Intercepor를 Configuration파일에 설정시 지정 가능한 property값을 처리하는 callback 메소드
     *
     * @param properties Intercepor를 Configuration파일에 설정시 지정 가능한 property값
     */
    public void setProperties(Properties properties) {
        String value = properties.getProperty(LIMIT_COUNT);
        if (value != null) {
            this.limitCount = Integer.parseInt(value.trim());
            if (limitCount <= 1) { throw new DaoException("DSOCDI001", "limitCount must be greater than 1."); }
        }
        value = properties.getProperty(DATA_CUT);
        if (value != null) {
            this.dataCut = Boolean.parseBoolean(value);
        }
    }
}