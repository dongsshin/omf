package com.rap.config.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.rap.config.web.interceptor.AuthCheckInterceptor;
import com.rap.config.web.interceptor.AuthTokenCheckInterceptor;
import com.rap.config.web.interceptor.InValidUrlCheckInterceptor;
import com.rap.config.web.interceptor.LoginCheckInterceptor;
import com.rap.config.web.interceptor.SSOLocaleChangeInterceptor;
import com.rap.config.web.interceptor.XssCheckInterceptor;
import com.rap.omc.dataaccess.paging.policy.PagingPolicyResolver;
import com.rap.omc.framework.resolver.CollectionMethodArgumentResolver;
import com.rap.omc.framework.resolver.I18nLocaleSessionResolver;
import com.rap.omc.framework.resolver.PagingMethodArgumentResolver;
import com.rap.omc.framework.resolver.SmartClientArgumentResolver;
import com.rap.omc.framework.resolver.exception.AjaxExceptionResolver;
import com.rap.omc.framework.resolver.exception.DefaultExceptionResolver;
import com.rap.omc.framework.util.HtmlTagValidator;
import com.rap.omc.framework.util.PdmXssValidator;
import com.rap.omc.framework.view.AjaxExceptionView;
import com.rap.omc.framework.view.FoundationMappingJacksonJsonView;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Autowired
	AuthCheckInterceptor authCheckInterceptor;
	@Autowired
	AuthTokenCheckInterceptor authTokenCheckInterceptor;
	@Autowired
	InValidUrlCheckInterceptor inValidUrlCheckInterceptor;	
	@Autowired
	LoginCheckInterceptor loginCheckInterceptor;
	@Autowired
	SSOLocaleChangeInterceptor ssoLocaleChangeInterceptor;

	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new SmartClientArgumentResolver());
		resolvers.add(new CollectionMethodArgumentResolver());
		
		PagingMethodArgumentResolver pagingMethodArgumentResolver = new PagingMethodArgumentResolver();
		pagingMethodArgumentResolver.setPagingPolicyResolver(new PagingPolicyResolver());
		
		resolvers.add(pagingMethodArgumentResolver);
	}
	
	@Bean
	public AjaxExceptionView ajaxErrorView() {
		return new AjaxExceptionView();
	}
	@Bean
	public FoundationMappingJacksonJsonView ajaxView() {
		return new FoundationMappingJacksonJsonView();
	}
	@Bean
    public I18nLocaleSessionResolver localeResolver(){
    	I18nLocaleSessionResolver r = new I18nLocaleSessionResolver();
        r.setDefaultLocale(Locale.US);
        return r;
    }
	@Bean
    public BeanNameViewResolver beanNameViewResolver(){
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(1);
        return beanNameViewResolver;
    }
	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setViewClass(JstlView.class);
		urlBasedViewResolver.setPrefix("/WEB-INF/jsp/");
		urlBasedViewResolver.setSuffix(".jsp");
		urlBasedViewResolver.setOrder(3);
		return urlBasedViewResolver;
	}
	
	@Bean
	public AjaxExceptionResolver ajaxExceptionResolver() {
		AjaxExceptionResolver ajaxExceptionResolver = new AjaxExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("headerName", "X-Requested-With");
		mappings.setProperty("headerValues", "XMLHttpRequest");
		mappings.setProperty("errorView", "ajaxErrorView");
		ajaxExceptionResolver.setOrder(1);
		return ajaxExceptionResolver;
	}
	
	@Bean
    public RestTemplate restTemplate() {
    	return new org.springframework.web.client.RestTemplate();
    }
	
	@Bean
	public DefaultExceptionResolver defaultExceptionResolver() {
		DefaultExceptionResolver defaultExceptionResolver = new DefaultExceptionResolver();
		defaultExceptionResolver.setWarnLogCategory("errorLogger");
		
		Properties mappings = new Properties();
		mappings.setProperty("omc.framework.exception.NoAuthorityTokenException", "forward:/system/error/noAuthorityTokenAjax.do");
		mappings.setProperty("omc.framework.exception.ApplicationException", "forward:/system/error/applicationErrorAjax.do");
		mappings.setProperty("omc.framework.exception.OmcException", "forward:/system/error/omcErrorAjax.do");
		mappings.setProperty("omc.framework.exception.NoSessionException", "forward:/system/error/noSession.do");
		mappings.setProperty("omc.framework.exception.NoAuthorityException", "system/error/alertAndBackError");
		
		defaultExceptionResolver.setDefaultErrorView("system/error/error");
		defaultExceptionResolver.setExceptionMappings(mappings);
		defaultExceptionResolver.setOrder(2);
		return defaultExceptionResolver;
	}
	
	
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//    	registry.addViewController("/login.html");
//        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/login").setViewName("login");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }
	
//	
//    @Bean
//    public ViewResolver viewResolver() {
//        final InternalResourceViewResolver bean = new InternalResourceViewResolver();
//
//        bean.setViewClass(JstlView.class);
//        bean.setPrefix("/WEB-INF/view/");
//        return bean;
//    }
    
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		XssCheckInterceptor xssCheckInterceptor = new XssCheckInterceptor();
		xssCheckInterceptor.setPdmXssValidator(pdmXssValidator());
		registry.addInterceptor(xssCheckInterceptor);
		
		/********************************Login Check Interceptor*******************************************/
		loginCheckInterceptor.setSkipUrls(getLoginCheckSipUrls());
		//loginCheckInterceptor.setApplyUrls(getLoginCheckApplyUrls());
        registry.addInterceptor(loginCheckInterceptor);

        /********************************Auth Check Interceptor*******************************************/
//		authCheckInterceptor.setSkipUrls(getAuthCheckSipUrls());
//		//authCheckInterceptor.setApplyUrls(getAuthCheckApplyUrls());
//        registry.addInterceptor(authCheckInterceptor);
    
        /********************************Auth Token Interceptor*******************************************/
//        authTokenCheckInterceptor.setSkipUrls(getTokenCheckSipUrls());
//        authTokenCheckInterceptor.setApplyUrls(getTokenCheckApplyUrls());
//        registry.addInterceptor(authTokenCheckInterceptor);
//    
        /********************************Invalid URL Check Interceptor*******************************************/
//        inValidUrlCheckInterceptor.setSkipUrls(getInvalidURLCheckSipUrls());
//        //inValidUrlCheckInterceptor.setApplyUrls(getInvalidURLCheckApplyUrls());
//        registry.addInterceptor(inValidUrlCheckInterceptor);

        /********************************SSO Locale Change Interceptor*******************************************/
        registry.addInterceptor(ssoLocaleChangeInterceptor);
	}
	@Bean
	public HtmlTagValidator htmlTagValidator() {
		HtmlTagValidator htmlTagValidator = new HtmlTagValidator();
		Map<String, String> validAttrMap = new HashMap<String, String>();
		validAttrMap.put("img", "align, alt, border, crossorigin, height, hspace, ismap, longdesc, src, usemap, vspace, width");
		htmlTagValidator.setValidAttrMap(validAttrMap);
		Set<String> invalidTags = new HashSet<String>();
		invalidTags.add("script");
		invalidTags.add("applet");
		invalidTags.add("embed");
		htmlTagValidator.setInvalidTags(invalidTags);
		return htmlTagValidator;
	}
	@Bean
	public PdmXssValidator pdmXssValidator() {
		PdmXssValidator pdmXssValidator = new PdmXssValidator();
		pdmXssValidator.setHtmlTagValidator(htmlTagValidator());
		Map<String, String> skipImages = new HashMap<String, String>();
		skipImages.put("/requests/ecad/createECADLibRequestMC.do", "descriptions");
		skipImages.put("/requests/ecad/createElectronicCAERequestMC.do", "descriptions");
		skipImages.put("/common/desktop/saveDesktopMemoAjax.do", "memo");
		pdmXssValidator.setSkipImages(skipImages);
		return pdmXssValidator;
	}
	private List<String> getLoginCheckSipUrls() {
		List<String> ist = new ArrayList<String>();
		ist.add("/index*.do</");
		ist.add("/file/mgmt/healthCheck.do");
		ist.add("/healthCheck.do");
		ist.add("/item/mcad/updateStepTest.do");
		ist.add("/common/mail/sendMailToApprover.do");
		ist.add("/system/error/*.do");
		ist.add("/common/login*.do");
		ist.add("/wasCheck.do");
		ist.add("/common/login*.do");
	    return ist;
	}
	private List<String> getLoginCheckApplyUrls() {
		List<String> ist = new ArrayList<String>();
	    return ist;
	}
	private List<String> getAuthCheckSipUrls() {
		List<String> ist = new ArrayList<String>();
		ist.add("/index*.do</");
		ist.add("/file/mgmt/healthCheck.do");
		ist.add("/healthCheck.do");
		ist.add("/item/mcad/updateStepTest.do");
		ist.add("/common/mail/sendMailToApprover.do");
		ist.add("/system/error/*.do");
		ist.add("/common/login*.do");
		ist.add("/wasCheck.do");
		ist.add("/common/login*.do");
	    return ist;
	}
	private List<String> getAuthCheckApplyUrls() {
		List<String> ist = new ArrayList<String>();
	    return ist;
	}
	private List<String> getTokenCheckSipUrls() {
		List<String> ist = new ArrayList<String>();
	    return ist;
	}
	private List<String> getTokenCheckApplyUrls() {
		List<String> ist = new ArrayList<String>();
	    return ist;
	}
	private List<String> getInvalidURLCheckSipUrls() {
		List<String> ist = new ArrayList<String>();
	    return ist;
	}
	private List<String> getInvalidURLCheckApplyUrls() {
		List<String> ist = new ArrayList<String>();
	    return ist;
	}
}
