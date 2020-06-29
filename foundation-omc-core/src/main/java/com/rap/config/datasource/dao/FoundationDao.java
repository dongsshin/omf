package com.rap.config.datasource.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rap.omc.dataaccess.paging.executer.MyBatisPagingExecutor;
@Repository
public class FoundationDao extends GenericDao implements InitializingBean{
	@Autowired
	SqlSession foundationSqlSession;
	@Autowired
	MyBatisPagingExecutor myBatisPagingExecutor;
	
	@Override
	public void afterPropertiesSet() throws Exception{
		if (super.genricSqlSession == null) super.genricSqlSession = foundationSqlSession;
		if (super.pagingExecutor == null) super.pagingExecutor = myBatisPagingExecutor;
	}
}