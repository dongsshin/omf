/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : DefaultExceptionResolver.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 10. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.framework.resolver.exception;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.rap.omc.dataaccess.exception.DaoException;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.BaseException;
import com.rap.omc.framework.exception.BusinessException;
import com.rap.omc.framework.exception.NoSessionException;


/**
 * <pre>
 * Class : DefaultExceptionResolver
 * Description : 일반적인 HTTP Request에 대한 Exception Resolver
 * <p> Exception 발생 시, 에러 페이지로 이동함
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class DefaultExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionResolver.class);

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor messageSourceAccessor;

    /**
     * 디폴트 생성자
     */
    public DefaultExceptionResolver() {
        super();
    }

    /**
     * Exception 유형 별로 에러 로그 출력 유형 변경
     * 
     * @param ex
     * @param request
     * @see org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver#logException(java.lang.Exception, javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected void logException(Exception ex, HttpServletRequest request){
        
        if ( ex instanceof NoSessionException ) {
            String origin = request.getServletPath();
            String query = request.getQueryString();
            if ( origin != null && query != null && query.trim().length() > 0 ) {
                origin += "?" + query;
            }
            
            /*
             * sso 가 return 하는 parameter 문자열은 "/=?" 등 alphabet 이 아니면 앞에 "-" 를 붙이므로 Base64 로 encode 하여 전달한다.
             */
            if ( origin != null ) {
                LOGGER.info("origin before = " + origin);
                byte[] bytesEncoded = Base64.encode(origin.getBytes());
                origin = new String(bytesEncoded);
                LOGGER.info("origin after  = " + origin);
                
                request.setAttribute("origin", origin);
            }
        }
        LOGGER.info("ex = " + ex);
        String logMessage = buildLogMessage(ex, request);
        
        if (ex instanceof ApplicationException) {
            request.setAttribute("message", this.getCompleteExceptionMessage((BaseException)ex) );
        }

        if (ex instanceof DaoException || ex instanceof BusinessException) {
            LOGGER.warn(logMessage);
        } else if (ex instanceof BaseException) {
            LOGGER.warn(logMessage, ex);
        } else {
            LOGGER.warn(logMessage, ex);
        }
    }

    /**
     * 에러 메시지 구성 (에러 로그 남길 때, 로그가 남겨지는 형식을 통일하기 위함)
     * 
     * @param ex
     * @param request
     * @return
     * @see org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver#buildLogMessage(java.lang.Exception, javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected String buildLogMessage(Exception ex, HttpServletRequest request){
        StringBuilder sb = new StringBuilder("");
        sb.append("\n================================== Exception occurred! (default) ===================================\n");
        sb.append("# Exception Information\n");
        sb.append(" - Exception Type   : " + ex.getClass().getName() + "\n");
        sb.append("\n# Exception StackTrace");
        if (ex instanceof BaseException) {
            sb.append("\n Description=" + this.getCompleteExceptionMessage((BaseException)ex) + "\n");
        } else {
            sb.append("\n Description=\n");
        }
 
        return sb.toString();
    }
    
   
    /**
     * 예외 클래스의 메시지 코드, 메시지 파라미터를 가지고 완성된 메시지 문자열을 생성한다.
     *
     * @param be
     * @return
     */
    private String getCompleteExceptionMessage(BaseException be){
        String message = null;
        try {
            message = messageSourceAccessor.getMessage(be.getCode(), be.getMessage());
        } catch (NoSuchMessageException e) {
            message = be.getCode();
            LOGGER.debug(message);
        }
       
        return message;
    }

}
