package com.rap.config.datasource.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rap.omc.dataaccess.paging.executer.MyBatisPagingExecutor;
@Repository
public class MasterDao extends GenericDao implements InitializingBean{
	@Autowired
	SqlSession masterSqlSession;
	@Autowired
	MyBatisPagingExecutor myBatisPagingExecutor;
	
	@Override
	public void afterPropertiesSet() throws Exception{
		if (super.genricSqlSession == null) super.genricSqlSession = masterSqlSession;
		if (super.pagingExecutor == null) super.pagingExecutor = myBatisPagingExecutor;
	}
}