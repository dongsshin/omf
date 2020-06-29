package com.rap.config.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class InValidUrlCheckInterceptor extends UrlPatternInterceptorAdapter {

    static final Logger LOGGER = LoggerFactory.getLogger(InValidUrlCheckInterceptor.class);

    public boolean checkHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
    	LOGGER.info("*****************************************************************************************************");
    	LOGGER.info("*************************************InValidUrlCheckInterceptor.checkHandle**************************");
    	LOGGER.info("*****************************************************************************************************");
//        boolean isInvalid = true;        
//        if(isInvalid) throw new NoAuthorityException("frame.error.InvalidUrl");
        
        return false;
    }
}
