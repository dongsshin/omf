package com.rap.config.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rap.omc.api.util.HttpSessionUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.user.model.UserSessionVO;
import com.rap.omc.framework.exception.NoAuthorityException;

@Component
public class AuthCheckInterceptor extends UrlPatternInterceptorAdapter {

    static final Logger LOGGER = LoggerFactory.getLogger(AuthCheckInterceptor.class);

    public boolean checkHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
    	LOGGER.info("*****************************************************************************************************");
    	LOGGER.info("*************************************AuthCheckInterceptor********************************************");
    	LOGGER.info("*****************************************************************************************************");
        HttpSession session = request.getSession(false);
        UserSessionVO userSessionVO = (UserSessionVO)HttpSessionUtil.getAttribute(session, GlobalConstants.SESSION_USER_INFO);
        if (userSessionVO != null && userSessionVO != null) {
            String url = request.getRequestURI();
            String contextPath = request.getContextPath();
            String action = url.substring(contextPath.length());
            action = action.substring(action.lastIndexOf("/") + 1,action.lastIndexOf("?") > 0 ? action.lastIndexOf("?") - 1 : action.length());
            if (action != null) {
                boolean isAuth = true;
                if (!isAuth) throw new NoAuthorityException("frame.error.notauthorized", new Object[] { request.getServletPath() });
            }
        }
        return true;
    }
}
