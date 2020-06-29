package com.rap.omc.framework.resolver.handler;

import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.util.PatternMatchUtils;
/*    */ import org.springframework.web.servlet.ModelAndView;


public class SimpleHeaderMappingExceptionResolver extends AbstractHeaderMappingExceptionResolver
{

    private String headerName;

    private String[] headerValues;

    private String errorView;

    
    public void setHeaderName(String headerName)
    {
        this.headerName = headerName;
        }

    
    public void setHeaderValues(String[] headerValues)
    {
        this.headerValues = headerValues;
        }

    
    public void setErrorView(String errorView)
    {
        this.errorView = errorView;
        }

    
    protected boolean supportsHeader(HttpServletRequest request){
        if ((this.headerName != null) && (this.headerValues != null)) {
            String orginalHeaderValue = request.getHeader(this.headerName);
            if (orginalHeaderValue != null) {
                for (String headerValue : this.headerValues) {
                    if (PatternMatchUtils.simpleMatch(headerValue, orginalHeaderValue)) return true;
                    }
                }
            }
        return false;
        }

    
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex){
        String viewName = this.errorView;
        
        if (viewName != null) {
            return getModelAndView(viewName, ex);
            }
        return null;
        }
    }