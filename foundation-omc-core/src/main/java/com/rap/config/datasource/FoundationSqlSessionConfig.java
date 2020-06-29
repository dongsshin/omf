package com.rap.config.datasource;


import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.rap.config.PropertiesUtil;
import com.rap.omc.dataaccess.mybatis.interceptor.LimitCountInterceptor;
import com.rap.omc.dataaccess.mybatis.interceptor.MyBatisConvertMapInterceptor;
import com.rap.omc.dataaccess.mybatis.interceptor.MyBatisPagingInterceptor;
import com.rap.omc.dataaccess.mybatis.interceptor.QueryLoggingInterceptor;



@Configuration
public class FoundationSqlSessionConfig {
private static final Logger LOGGER = LoggerFactory.getLogger(FoundationSqlSessionConfig.class);
    
	@Bean(name = "foundationDataSource")
    public DataSource foundationDataSource() {
    	return new AtomikosDataSourceBean("foundation");
    }
    
	@Bean(name = "foundationSqlSessionFactory")
    public SqlSessionFactory foundationSqlSessionFactory(DataSource foundationDataSource) throws Exception {
        
    	SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
       
        factoryBean.setDataSource(foundationDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/foundation/*.xml"));
        Resource[] foundationMapperLocations = resolver.getResources("classpath:mybatis/mapper/foundation/*.xml");
        Resource[] userMapperLocations = resolver.getResources("classpath:mybatis/mapper/foundation/*.xml");
        

        int sqlMaxCount = PropertiesUtil.getInt("foundation.maxSqlCount");
        LOGGER.info("foundation.maxSqlCount:" + sqlMaxCount);
        
        factoryBean.setPlugins(new Interceptor[]{
                new QueryLoggingInterceptor(),
                new LimitCountInterceptor(sqlMaxCount,false),
                new MyBatisPagingInterceptor(),
                new MyBatisConvertMapInterceptor()
        });
        factoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return factoryBean.getObject();
    }
    
	@Bean(name = "foundationSqlSession", destroyMethod = "clearCache")
    public SqlSession foundationSqlSession(SqlSessionFactory foundationSqlSessionFactory) {
        return new SqlSessionTemplate(foundationSqlSessionFactory);
    }
}