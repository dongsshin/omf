package com.rap.config.datasource.dao;


import java.util.List;


import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rap.omc.dataaccess.exception.DaoException;
import com.rap.omc.dataaccess.mybatis.LimitCountRowBounds;
import com.rap.omc.dataaccess.paging.executer.PagingExecutor;
@Repository
public class GenericDao {
	private static  Logger LOGGER = LoggerFactory.getLogger(GenericDao.class);
	protected SqlSession genricSqlSession;
	protected PagingExecutor pagingExecutor;
	
	private SqlSession getSqlSession() {
		return genricSqlSession;
	}
	public <T> int insert(String sqlId, T params)  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Excuted SQL ID :: {}", sqlId);
		}
		return getSqlSession().insert(sqlId, params);
	}
	public <T> int update(String sqlId, T params)  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Excuted SQL ID :: {}", sqlId);
		}
		return getSqlSession().update(sqlId, params);
	}
	public <T> int delete(String sqlId, T params)  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Excuted SQL ID :: {}", sqlId);
		}
		return getSqlSession().delete(sqlId, params);
	}
	public <T, V> T select(String sqlId, V param)  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Excuted SQL ID :: {}", sqlId);
		}
		return getSqlSession().selectOne(sqlId, param);
	}
	public <T, V> T select(String sqlId)  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Excuted SQL ID :: {}", sqlId);
		}
		return getSqlSession().selectOne(sqlId);
	}
	@SuppressWarnings("unchecked")
	public <T, V> T selectList(String sqlId, V param)  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Excuted SQL ID :: {}", sqlId);
		}
		return (T) getSqlSession().selectList(sqlId, param);
	}
	@SuppressWarnings("unchecked")
	public <T, V> T selectList(String sqlId)  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Excuted SQL ID :: {}", sqlId);
		}
		return (T) getSqlSession().selectList(sqlId);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <E> List<E> selectList(String queryId, Object parameter, String sqlSessionFactoryName,RowBounds rowBounds){
		RowBounds bounds = rowBounds;
        if ((rowBounds instanceof LimitCountRowBounds) && (bounds.getLimit() != 2147483647)) {
        	LimitCountRowBounds limitCountRowBounds = (LimitCountRowBounds)rowBounds;
            RowBounds plusOneRowBounds = new RowBounds(0, limitCountRowBounds.getLimit() + 1);
            List result = getSqlSession().selectList(queryId, parameter, plusOneRowBounds);
            checkRowBoundsException(result, queryId, limitCountRowBounds);
            return result;
        }
        List result = getSqlSession().selectList(queryId, parameter, rowBounds);
        return result;
    }
    public <E> List<E> selectList(String queryId, Object parameter, RowBounds rowBounds)
    {
    	return getSqlSession().selectList(queryId, parameter, rowBounds);
    }
    public <E> void checkRowBoundsException(List<E> result, String queryId, LimitCountRowBounds limitCountRowBounds){
		if (result.size() > limitCountRowBounds.getLimit())
			if (limitCountRowBounds.isDataCut()) {
			LOGGER.warn("Query ID[{}] has returned result data exceeding limit count : {}", queryId, Integer.valueOf(limitCountRowBounds.getLimit()));
			LOGGER.warn("But because 'dataCut' is 'true', first {} result data has returned.",Integer.valueOf(limitCountRowBounds.getLimit()));
			result.remove(result.size() - 1);
        }else {
        	throw new DaoException("DSODAO010", "Query ID[" + queryId + "] has returned result data exceeding limit count : " + limitCountRowBounds.getLimit());
        }
    }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <E> List<E> selectPagedList(String queryId, Object parameter){
    	Object param = parameter;
    	if (this.pagingExecutor != null) {
    		List result = this.pagingExecutor.execute(getSqlSession(),queryId, param, true);
    		return result;
    	}
    	throw new DaoException("DSODAO008", "To invoke selectPagedList() method, '" + PagingExecutor.class.getSimpleName() + "' must be configured before invocation.");
    }
    public int batchInsert(String queryId, List<?> parameterList)
    {
        int count = 0;
        Object parameter = null;
        if ((parameterList == null) || (parameterList.size() == 0)) return 0;
        for (int i = 0; i < parameterList.size(); ++i) {
            parameter = parameterList.get(i);
            count += getSqlSession().insert(queryId, parameter);
        }
        return count;
    }
    public int batchUpdate(String queryId, List<?> parameterList)
    {
        int count = 0;
        Object parameter = null;
        if ((parameterList == null) || (parameterList.size() == 0)) return 0;
        for (int i = 0; i < parameterList.size(); ++i) {
            parameter = parameterList.get(i);
            count += getSqlSession().insert(queryId, parameter);
        }
        return count;
    }
    public int batchDelete(String queryId, List<?> parameterList)
    {
        int count = 0;
        Object parameter = null;
        if ((parameterList == null) || (parameterList.size() == 0)) return 0;
        for (int i = 0; i < parameterList.size(); ++i) {
            parameter = parameterList.get(i);
            count += getSqlSession().delete(queryId, parameter);
        }
        return count;
    }
	public <T> void selectListWithHandler(String sqlId, Object job, ResultHandler<T> handler)  {
		getSqlSession().select(sqlId, job, handler);
	}
}