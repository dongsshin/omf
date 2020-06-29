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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.rap.config.PropertiesUtil;
import com.rap.omc.dataaccess.mybatis.interceptor.LimitCountInterceptor;
import com.rap.omc.dataaccess.mybatis.interceptor.MyBatisConvertMapInterceptor;
import com.rap.omc.dataaccess.mybatis.interceptor.MyBatisPagingInterceptor;
import com.rap.omc.dataaccess.mybatis.interceptor.QueryLoggingInterceptor;
@Configuration
public class SchemaSqlSessionConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SchemaSqlSessionConfig.class);
    
	@Bean(name = "schemaDataSource")
    public DataSource schemaDataSource() {
    	return new AtomikosDataSourceBean("schema");
    }
    
	@Bean(name = "schemaSqlSessionFactory")
    public SqlSessionFactory schemaSqlSessionFactory(DataSource schemaDataSource) throws Exception {
        
    	SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
       
        factoryBean.setDataSource(schemaDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/foundation/*.xml"));

        int sqlMaxCount = PropertiesUtil.getInt("schema.maxSqlCount");
        LOGGER.info("schema.maxSqlCount:" + sqlMaxCount);
        
        factoryBean.setPlugins(new Interceptor[]{
                new QueryLoggingInterceptor(),
                new LimitCountInterceptor(sqlMaxCount,false),
                new MyBatisPagingInterceptor(),
                new MyBatisConvertMapInterceptor()
        });
        factoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return factoryBean.getObject();
    }
    
	@Bean(name = "schemaSqlSession", destroyMethod = "clearCache")
    public SqlSession schemaSqlSession(SqlSessionFactory schemaSqlSessionFactory) {
        return new SqlSessionTemplate(schemaSqlSessionFactory);
    }
}