package com.rap.omc.dataaccess.paging.executer;

import java.util.List;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.rap.omc.dataaccess.paging.PagingHelper;
import com.rap.omc.dataaccess.paging.exception.PagingException;
import com.rap.omc.dataaccess.paging.handler.PagingCountHandler;
import com.rap.omc.dataaccess.paging.handler.PagingResultHandler;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.dataaccess.paging.model.PagingParameterUtil;
@Component("myBatisPagingExecutor")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MyBatisPagingExecutor implements PagingExecutor{

    private String sqlInjectionSieve = "'\";:#\\/*"; 
    public <E> List<E> execute(Object sqlSession, String queryId, Object parameter)
    {
        return execute(sqlSession, queryId, parameter, true);
    }
	public <E> List<E> execute(Object sqlSession, String queryId, Object parameter, boolean isExecuteCountQuery){
        SqlSession template = (SqlSession)sqlSession;
        PagingEntity pagingEntity = null;
        if (!(parameter instanceof PagingEntity)) {
            throw new PagingException("To use CommonDao.selectPagedList(String queryId, Object parameter), parameter must be type of '" + PagingEntity.class.getName() + "'.");
        }
        pagingEntity = (PagingEntity)parameter;
        
        PagingHelper.checkSortingOrderBySQL(pagingEntity.getOrderBy(), this.sqlInjectionSieve);
        String databaseUrl = DatabaseUrlHolder.getInstance().getDatabaseUrl(template.getConfiguration().getEnvironment().getDataSource());
        PagingParameterUtil.setPagingParameter(pagingEntity, "devonDatabaseUrl", databaseUrl);
        
        int totalRows = 0;
        if (isExecuteCountQuery) {
            
			ResultHandler countHandler = new PagingCountHandler();
            template.select(queryId, pagingEntity, countHandler);
            totalRows = ((PagingCountHandler)countHandler).getTotalCount();
            PagingParameterUtil.setPagingParameter(pagingEntity, "rows", Integer.valueOf(totalRows));
        }
		ResultHandler pagingResultHandler = new PagingResultHandler();
        template.select(queryId, pagingEntity, pagingResultHandler);
        PagingList pagedList = ((PagingResultHandler)pagingResultHandler).getResultList();
        if (isExecuteCountQuery){
      	  pagedList.setRows(totalRows);
        }
        else {
           pagedList.setRows(pagedList.size());
        }
        pagedList.setTargetRow(pagingEntity.getTargetRow());
        pagedList.setOrderBy(pagingEntity.getOrderBy());
        pagedList.setDefaultRowSize(pagingEntity.getDefaultRowSize());
        pagedList.setRowSize(pagingEntity.getRowSize());
        pagedList.setPageSize(pagingEntity.getPageSize());
        pagedList.setCustomRowSize(pagingEntity.getCustomRowSize());
        pagedList.setCurrentPage(pagingEntity.getTargetRow(), pagingEntity.getRowSize());
        return pagedList;
    }
    public void setSqlInjectionSieve(String sqlInjectionSieve)
    {
        this.sqlInjectionSieve = sqlInjectionSieve;
    }
}