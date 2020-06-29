package com.rap.config.web.interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * <pre>
 * Class : SSOLocaleChangeInterceptor
 * Description : sso시 lang=kr로 전달됨
 * </pre>
 *
 * @author hyeyoung.park
 */
@Component
public class SSOLocaleChangeInterceptor extends HandlerInterceptorAdapter {

    static final Logger LOGGER = LoggerFactory.getLogger(SSOLocaleChangeInterceptor.class);
    public static final String DEFAULT_PARAM_NAME = "locale";

    private String paramName = DEFAULT_PARAM_NAME;
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    public String getParamName() {
        return this.paramName;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws ServletException {
    	LOGGER.info("*****************************************************************************************************");
    	LOGGER.info("*************************************SSOLocaleChangeInterceptor.preHandle****************************");
    	LOGGER.info("*****************************************************************************************************");
        String newLocale = request.getParameter(this.paramName);
        if (newLocale != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
            }
            LOGGER.debug("newLocale before : " + newLocale);
            if(newLocale.equalsIgnoreCase("kr")) newLocale = "ko";
            LOGGER.debug("newLocale after : " + newLocale);
            
            localeResolver.setLocale(request, response, StringUtils.parseLocaleString(newLocale));
        }
        // Proceed in any case.
        return true;
    }
}
