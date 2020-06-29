package com.rap.config.web.interceptor;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rap.omc.util.core.PatternUtil;

public abstract class UrlPatternInterceptorAdapter extends HandlerInterceptorAdapter
{
    private Pattern[] skipUrlPatterns;
    private Pattern[] applyUrlPatterns;
    
    public void setSkipUrls(List<String> skipUrls)
    {
        this.skipUrlPatterns = PatternUtil.compileWildcardPattern(skipUrls);
    }
    public void setApplyUrls(List<String> applyUrls){
        this.applyUrlPatterns = PatternUtil.compileWildcardPattern(applyUrls);
    }
    public final boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        if ((this.skipUrlPatterns != null) && (this.applyUrlPatterns != null)) throw new IllegalArgumentException("skipUrl and applyUrl in interceptor property cannot be used at the same time");
        if (((this.skipUrlPatterns != null)&& (PatternUtil.matches(this.skipUrlPatterns, request.getServletPath()))) || (
            (this.applyUrlPatterns != null)&& (!(PatternUtil.matches(this.applyUrlPatterns, request.getServletPath()))))) {
            return true;
        }
        return checkHandle(request, response, handler);
    }

    public abstract boolean checkHandle(HttpServletRequest paramHttpServletRequest,HttpServletResponse paramHttpServletResponse, Object paramObject) throws Exception;
}