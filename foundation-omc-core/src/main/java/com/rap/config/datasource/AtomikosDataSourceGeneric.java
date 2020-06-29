package com.rap.config.datasource;

import org.springframework.util.StringUtils;

import com.rap.config.PropertiesUtil;

public class AtomikosDataSourceGeneric extends com.atomikos.jdbc.AtomikosDataSourceBean{
	private static final long serialVersionUID = -6342022869060840962L;
	protected String beanName;
	protected void setPropertiesSet(String beanName, String prefix) throws Exception {
		this.setUniqueResourceName(PropertiesUtil.getString(prefix + ".dataSource"));
		this.setXaDataSourceClassName(PropertiesUtil.getString(prefix + ".dataSourceClassName"));
		this.getXaProperties().put("user", PropertiesUtil.getString(prefix + ".user"));
		this.getXaProperties().put("password", PropertiesUtil.getString(prefix + ".password"));
		this.getXaProperties().put("url", PropertiesUtil.getString(prefix + ".url"));
		this.beanName = beanName;
		if (!StringUtils.hasLength(getUniqueResourceName())) {
			setUniqueResourceName(this.beanName);
		}
		this.setMinPoolSize(PropertiesUtil.getInt(prefix + ".poolSizeMin"));
		this.setMaxPoolSize(PropertiesUtil.getInt(prefix + ".poolSizeMax"));
		init();
	}
}