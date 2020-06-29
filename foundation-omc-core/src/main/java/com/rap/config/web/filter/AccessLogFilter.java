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

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.rap.omc.api.util.HttpSessionUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.framework.log.LogKeyUtil;
import com.rap.omc.util.NullUtil;

public class AccessLogFilter implements Filter {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLogFilter.class);

    public void init(FilterConfig arg0) throws ServletException{
        // TODO Auto-generated method stub

    }

    public void destroy(){
        // TODO Auto-generated method stub

    }

    /**
     * HTTP Request에 대한 start(request uri 등) & end (elapsed time) 등을 로그에 남긴다.
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException{

        try {
            ThreadContext.put("logKey", LogKeyUtil.getLogKeyWithStart());

            long start = System.currentTimeMillis();

            HttpServletRequest httpReq = (HttpServletRequest)req;
            HttpSession session = httpReq.getSession();
            String uri = httpReq.getRequestURI();
            int index = uri.indexOf(";");
            if (index > 0) {
                uri = uri.substring(0, index);
            }
            String userIp = httpReq.getRemoteAddr();
            String method = httpReq.getMethod();

            MDC.put("client-ip", userIp );

            String userId = "";
            UserSession userSession = (UserSession)HttpSessionUtil.getAttribute(session, GlobalConstants.SESSION_USER_INFO);
            if (!NullUtil.isNull(userSession) && !NullUtil.isNone(userSession.getUserId())) {
                userId = userSession.getUserId();
            } else {
                userId = GlobalConstants.NO_USER_ID;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[start] ");
            sb.append(uri).append("\t");
            sb.append(userId).append("\t");
            sb.append(userIp).append("\t");
            sb.append(method);
            LOGGER.info(sb.toString());

            chain.doFilter(req, res);

            StringBuilder sb2 = new StringBuilder();
            long elapsed = System.currentTimeMillis() - start;
            sb2.append("[end::elapsed=").append(elapsed).append("]");
            sb2.append(uri);
            LOGGER.info(sb2.toString());

        } finally {
            MDC.remove("client-ip");
            LogKeyUtil.clearLogKey();
        }
    }
}
