package com.rap.config.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rap.omc.api.util.HttpSessionUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.user.ThreadLocalSessionUtil;
import com.rap.omc.foundation.user.model.UserSessionVO;
import com.rap.omc.util.NullUtil;

public class ThreadLocalFilter implements Filter {
	@Override
	public void init(FilterConfig arg0) throws ServletException{
    }
    @Override
    public void destroy(){
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
        try {
            HttpServletRequest httpReq = (HttpServletRequest)req;
            HttpSession session = httpReq.getSession();            
            String userIp = httpReq.getRemoteAddr();
            ThreadLocalUtil.init();
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.remoteAddr, userIp);
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.requestUri, httpReq.getRequestURI());
            
            UserSessionVO userSessionVO = (UserSessionVO)HttpSessionUtil.getAttribute(session, GlobalConstants.SESSION_USER_INFO);

            if (!NullUtil.isNull(userSessionVO)) {  
            	ThreadLocalSessionUtil.refreshThreadLocalFromSession(userSessionVO);
            }  
            chain.doFilter(req, res);           
        } finally {
            ThreadLocalUtil.destroy();
        }
    }
}
