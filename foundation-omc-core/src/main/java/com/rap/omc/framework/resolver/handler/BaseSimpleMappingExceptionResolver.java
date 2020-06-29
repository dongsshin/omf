package com.rap.omc.framework.resolver.handler;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.rap.omc.framework.exception.BaseException;


public class BaseSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver
{

    private String exceptionAttribute = "exception";

    
    private String exceptionCodeAttribute = "exceptionCode";

    private Log errorLogger;

    
    public void setExceptionAttribute(String exceptionAttribute)
    {
        this.exceptionAttribute = exceptionAttribute;
        }

    
    public void setExceptionCodeAttribute(String exceptionCodeAttribute)
    {
        this.exceptionCodeAttribute = exceptionCodeAttribute;
        }

    
    public void setErrorLogCategory(String loggerName)
    {
        this.errorLogger = LogFactory.getLog(loggerName);
        }

    
    protected ModelAndView getModelAndView(String viewName, Exception ex)
    {
        ModelAndView mv = new ModelAndView(viewName);
        if (this.exceptionAttribute != null) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Exposing Exception as model attribute '" + this.exceptionAttribute + "'");
                }
            mv.addObject(this.exceptionAttribute, ex);
            }
        
        if ((ex instanceof BaseException) && (this.exceptionCodeAttribute != null)) {
            if (this.logger.isDebugEnabled()) {
                this.logger
                        .debug("Exposing Exception Code as model attribute '" + this.exceptionCodeAttribute + "'");
                }
            mv.addObject(this.exceptionCodeAttribute, ((BaseException)ex).getCode());
            }
        
        return mv;
        }

    
    protected void logException(Exception ex, HttpServletRequest request)
    {
        super.logException(ex, request);
        
        if ((this.errorLogger != null) && (this.errorLogger.isErrorEnabled()))
            this.errorLogger.error(buildLogMessage(ex, request), ex);
        }
    }