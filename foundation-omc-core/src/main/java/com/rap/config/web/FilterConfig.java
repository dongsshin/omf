package com.rap.config.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.rap.config.web.filter.AccessLogFilter;
import com.rap.config.web.filter.ThreadLocalFilter;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> getCharacterEncodingFilteregistrationBean()
	{
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("utf-8");
		characterEncodingFilter.setForceEncoding(true);
		FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<CharacterEncodingFilter>(new CharacterEncodingFilter());
		registrationBean.addUrlPatterns("*.do");
		registrationBean.addUrlPatterns("/rs/");
		registrationBean.addUrlPatterns("/ws/");
		registrationBean.setOrder(1);
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean<AccessLogFilter> getAccessogFilterRegistrationBean()
	{
		FilterRegistrationBean<AccessLogFilter> registrationBean = new FilterRegistrationBean<AccessLogFilter>(new AccessLogFilter());
		registrationBean.addUrlPatterns("*.do");
		registrationBean.addUrlPatterns("/rs/");
		registrationBean.addUrlPatterns("/ws/");
		registrationBean.setOrder(2);
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean<ThreadLocalFilter> getThreadLocalFilterRegistrationBean()
	{
		FilterRegistrationBean<ThreadLocalFilter> registrationBean = new FilterRegistrationBean<ThreadLocalFilter>(new ThreadLocalFilter());
		registrationBean.addUrlPatterns("*.do");
		registrationBean.addUrlPatterns("/*");
		//registrationBean.addUrlPatterns("/ws/");
		registrationBean.setOrder(3);
		return registrationBean;
	}
}
