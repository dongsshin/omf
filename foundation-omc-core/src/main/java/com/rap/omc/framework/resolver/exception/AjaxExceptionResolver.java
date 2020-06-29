/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : AjaxExceptionResolver.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 10. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.framework.resolver.exception;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;

import com.rap.omc.dataaccess.exception.DaoException;
import com.rap.omc.framework.exception.BaseException;
import com.rap.omc.framework.exception.BusinessException;
import com.rap.omc.framework.resolver.handler.SimpleHeaderMappingExceptionResolver;

/**
 * <pre>
 * Class : AjaxExceptionResolver
 * Description : Ajax Request에 대한 Exception Resolver (RUI에서의 호출은 ajax 호출임)
 * <p> Exception 발생 시, 에러 메시지를 alert창으로 표출함
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class AjaxExceptionResolver extends SimpleHeaderMappingExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxExceptionResolver.class);

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex){
        if (supportsHeader(request)) {
            logException(ex, request);
            return doResolveException(request, response, handler, ex);
        }
        return null;
    }

    /**
     * Exception 유형 별로 에러 로그 출력 유형 변경
     * 
     * @param ex
     * @param request
     * @see devonframe.exception.handler.AbstractHeaderMappingExceptionResolver#logException(java.lang.Exception, javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected void logException(Exception ex, HttpServletRequest request){
        String logMessage = buildLogMessage(ex, request);
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
     * @see devonframe.exception.handler.AbstractHeaderMappingExceptionResolver#buildLogMessage(java.lang.Exception, javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected String buildLogMessage(Exception ex, HttpServletRequest request){
        StringBuilder sb = new StringBuilder("");
        sb.append("\n================================== Exception occurred!(ajax) ===================================\n");
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
