package com.rap.config.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rap.omc.api.util.HttpSessionUtil;
import com.rap.omc.api.util.omc.UserSessionUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.foundation.user.model.UserSessionVO;
import com.rap.omc.framework.exception.NoSessionException;
import com.rap.omc.util.core.StopWatch;
@Component
public class LoginCheckInterceptor extends UrlPatternInterceptorAdapter {
	@Autowired
	UserSession userSession;
    static final Logger LOGGER = LoggerFactory.getLogger(LoginCheckInterceptor.class);
    public boolean checkHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
    	LOGGER.info("*****************************************************************************************************");
    	LOGGER.info("*************************************LoginCheckInterceptor.checkHandle*******************************");
    	LOGGER.info("*****************************************************************************************************");
        
    	StopWatch watch = new StopWatch();
    	HttpSession session = request.getSession();
    	LOGGER.info("\nLoginCheckInterceptor Lap Time-01 : " + watch.getElapsed() + " ms\n");
        boolean isLogin = false;
        if (session != null) {
        	UserSessionVO userSessionVO = (UserSessionVO)HttpSessionUtil.getAttribute(session, GlobalConstants.SESSION_USER_INFO);
        	
        	LOGGER.info("\nLoginCheckInterceptor Lap Time-02 : " + watch.getElapsed() + " ms\n");
        	BeanUtils.copyProperties(userSessionVO, userSession);
        	LOGGER.info("\nLoginCheckInterceptor(copyProperties) Lap Time-03 : " + watch.getElapsed() + " ms\n");
        	if(userSession.getTimeStamp().equals(userSessionVO.getTimeStamp())) {
            	userSessionVO = UserSessionUtil.getUserSeesionVOFromRedis(userSessionVO.getUserId());
            	LOGGER.info("\nLoginCheckInterceptor(From Redis) Lap Time-04 : " + watch.getElapsed() + " ms\n");
                session.setAttribute(GlobalConstants.SESSION_USER_INFO, userSessionVO);
                LOGGER.info("\nLoginCheckInterceptor Lap Time-05 : " + watch.getElapsed() + " ms\n");
                BeanUtils.copyProperties(userSessionVO, userSession);
                LOGGER.info("\nLoginCheckInterceptor Lap Time-06 : " + watch.getElapsed() + " ms\n");
        	}
            isLogin = (userSession != null);
        }
        if (!isLogin) { throw new NoSessionException("frame.error.session"); }
        LOGGER.info("\nLoginCheckInterceptor Lap Time-end : " + watch.getElapsed() + " ms\n");
        
        return true;
    }
}
