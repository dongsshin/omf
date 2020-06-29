package com.rap.config.datasource;


import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class AtomikosDataSourceBean extends AtomikosDataSourceGeneric implements BeanNameAware, InitializingBean, DisposableBean {

	private static final long serialVersionUID = -1114275766207391727L;
	private String prefix;
	public AtomikosDataSourceBean(String prefix) {
		super();
		this.prefix = prefix;
	}
	@Override
	public void setBeanName(String name) {
		beanName = name;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		super.setPropertiesSet(beanName, this.prefix);
	}
	@Override
	public void destroy() throws Exception {
		close();
	}
}