package com.rap.config.datasource.dao;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rap.omc.dataaccess.paging.executer.MyBatisPagingExecutor;
@Repository
public class SchemaDao extends GenericDao implements InitializingBean{
	private static  Logger LOGGER = LoggerFactory.getLogger(GenericDao.class);
	@Autowired
	SqlSession schemaSqlSession;
	@Autowired
	MyBatisPagingExecutor myBatisPagingExecutor;
	
	@Override
	public void afterPropertiesSet() throws Exception{
		if (super.genricSqlSession == null) super.genricSqlSession = schemaSqlSession;
		if (super.pagingExecutor == null) super.pagingExecutor = myBatisPagingExecutor;
	}
}