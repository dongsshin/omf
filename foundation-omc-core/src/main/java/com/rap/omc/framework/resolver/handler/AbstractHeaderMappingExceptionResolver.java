package com.rap.omc.framework.resolver.handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.rap.omc.framework.exception.BaseException;


public abstract class AbstractHeaderMappingExceptionResolver
        implements HandlerExceptionResolver , Ordered
{

    protected final Logger logger = LoggerFactory.getLogger(super.getClass());

    
    private int order = 2147483647;

    
    private String exceptionAttribute = "exception";

    
    private String exceptionCodeAttribute = "exceptionCode";

    private Logger warnLogger;

    private Logger errorLogger;

    
    public void setOrder(int order)
    {
        this.order = order;
        }

    
    public int getOrder()
    {
        return this.order;
        }

    
    public void setWarnLogCategory(String loggerName)
    {
        this.warnLogger = LoggerFactory.getLogger(loggerName);
        }

    
    public void setErrorLogCategory(String loggerName)
    {
        this.errorLogger = LoggerFactory.getLogger(loggerName);
        }

    
    public void setExceptionAttribute(String exceptionAttribute)
    {
        this.exceptionAttribute = exceptionAttribute;
        }

    
     public void setExceptionCodeAttribute(String exceptionCodeAttribute)
     {
        this.exceptionCodeAttribute = exceptionCodeAttribute;
        }

    
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex)
    {
        if (supportsHeader(request))
        {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Resolving exception from handler [" + handler + "]: " + ex);
                }
            logException(ex, request);
            return doResolveException(request, response, handler, ex);
            }
        return null;
        }

    
    protected void logException(Exception ex, HttpServletRequest request)
    {
        if ((this.warnLogger != null) && (this.warnLogger.isWarnEnabled())) {
            this.warnLogger.warn(buildLogMessage(ex, request), ex);
            }
        
        if ((this.errorLogger != null) && (this.errorLogger.isErrorEnabled()))
            this.errorLogger.error(buildLogMessage(ex, request), ex);
        }

    
    protected String buildLogMessage(Exception ex, HttpServletRequest request)
    {
        return "Handler execution resulted in exception";
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

    
    protected abstract boolean supportsHeader(HttpServletRequest paramHttpServletRequest);

    
    protected abstract ModelAndView doResolveException(HttpServletRequest paramHttpServletRequest,
            HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException);
    }