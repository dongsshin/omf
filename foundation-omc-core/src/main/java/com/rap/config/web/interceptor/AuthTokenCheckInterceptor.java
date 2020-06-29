/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : AuthTokenCheckInterceptor.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2015. 1. 22.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.config.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;


/**
 * <pre>
 * Class : AuthTokenCheckInterceptor
 * Description : TODO
 * </pre>
 *
 * @author jongjung.kwon
 */
@Component
public class AuthTokenCheckInterceptor extends UrlPatternInterceptorAdapter {
    static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenCheckInterceptor.class);

    private static final String  ACCESS_CONTROL_ALLOW_ORIGIN      = "Access-Control-Allow-Origin";
    private static final String  ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String  REQUEST_HEADER_ORIGIN            = "Origin";
    
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  throws Exception {
    	LOGGER.info("*****************************************************************************************************");
    	LOGGER.info("*************************************AuthTokenCheckInterceptor.postHandle****************************");
    	LOGGER.info("*****************************************************************************************************");
    	String origin = request.getHeader(REQUEST_HEADER_ORIGIN);
        LOGGER.debug(REQUEST_HEADER_ORIGIN + " : "+   origin );
        // CORS 가능하도록 응답 헤더 추가
        if (StringUtils.hasLength(origin))
        {
            // 요청한 도메인에 대해 CORS 를 허용한다. 제한이 필요하다면 필요한 값으로 설정한다.
            response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            // credentials 허용
            response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        }
    }
    @Override
    public boolean checkHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
    	LOGGER.info("*****************************************************************************************************");
    	LOGGER.info("*************************************AuthTokenCheckInterceptor.checkHandle***************************");
    	LOGGER.info("*****************************************************************************************************");
    	String oneTimePasswd = (String)request.getParameter("oneTimePasswd");
        String remoteAddr = request.getRemoteAddr();
        String requestUrl = request.getRequestURL().toString();
        LOGGER.debug( "requestUrl     : "+   requestUrl );
        LOGGER.debug( "remoteAddr     : "+   remoteAddr );
        LOGGER.debug( "oneTimePasswd  : "+   oneTimePasswd );
//        if(!FCSServerLocationUtil.checkURLForFcsIntercepter(requestUrl)) throw new NoAuthorityTokenException("frame.error.InvalidUrl"); 
//        try {                
//            OtpVO outputOtp = FCSServerLocationUtil.checkAndGetOtp(new OtpVO(oneTimePasswd,remoteAddr));
//            if(!NullUtil.isNull(outputOtp)){
//                request.setAttribute(GlobalConstants.OTP_CREATOR, outputOtp.getCreator());
//                request.setAttribute(GlobalConstants.OTP_SESSION_INFO, outputOtp.getSessionInfo());
//                return true;
//            }
//            throw new NoAuthorityTokenException("frame.error.otpToken");
//        }catch(RuntimeException e){
//            e.printStackTrace();
//            throw new NoAuthorityTokenException("frame.error.getToken");
//        }
        return true;
    }
}
