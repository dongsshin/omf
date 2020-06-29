package com.rap.omc.framework.resolver;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.rap.omc.dataaccess.paging.exception.PagingException;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.dataaccess.paging.model.PagingMap;
import com.rap.omc.dataaccess.paging.policy.DefaultPagingPolicy;
import com.rap.omc.dataaccess.paging.policy.PagingPolicyResolver;
import com.rap.omc.dataaccess.paging.policy.innotation.PagingPolicy;
import com.rap.omc.util.NullUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PagingMethodArgumentResolver implements HandlerMethodArgumentResolver
{

    private PagingPolicyResolver pagingPolicyResolver;

    
    public void setPagingPolicyResolver(PagingPolicyResolver pagingPolicyResolver)
    {
        this.pagingPolicyResolver = pagingPolicyResolver;
        }

    
    public boolean supportsParameter(MethodParameter parameter)
    {
        return PagingEntity.class.isAssignableFrom(parameter.getParameterType());
        }

    
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory)/*     */ throws Exception
    {
        DefaultPagingPolicy pagingConfig = null;
        
        PagingPolicy policy = (PagingPolicy)parameter.getParameterAnnotation(PagingPolicy.class);
        
        if ((policy == null) || (policy.value().equals(""))) {
            pagingConfig = this.pagingPolicyResolver.getGlobalPolicy();
            } else {
            if (!(this.pagingPolicyResolver.getOptionalPolicyMap().containsKey(policy.value()))) {
                throw new PagingException("No Such Policy exists : " + policy.value());
                }
            pagingConfig = (DefaultPagingPolicy)this.pagingPolicyResolver.getOptionalPolicyMap()
                    .get(policy.value());
            }
        
        PagingEntity pagingEntity = (PagingEntity)parameter.getParameterType().newInstance();
        
        bindPagingEntity(webRequest, pagingConfig, pagingEntity);
        
        if (pagingEntity instanceof PagingMap) {
            ((PagingMap)pagingEntity).setMap(bindingPagingMap(webRequest));
            }
        else {
            new WebRequestDataBinder(pagingEntity).bind(webRequest);
            }
        
        return pagingEntity;
        }

    
    protected void bindPagingEntity(NativeWebRequest webRequest, DefaultPagingPolicy pagingConfig,
            PagingEntity pagingEntity)/*     */ throws Exception
    {
        String targetRow = webRequest.getParameter("targetRow");
        String orderBy = webRequest.getParameter("orderBy");
        String rowSize = webRequest.getParameter("rowSize");
        
        if (!(NullUtil.isNone(targetRow))) {
            pagingEntity.setTargetRow(Integer.parseInt(targetRow));
            }
        
        if (orderBy != null) {
            pagingEntity.setOrderBy(orderBy);
            }
        
        if (!(NullUtil.isNone(rowSize)))/* 132 */ pagingEntity.setRowSize(Integer.parseInt(rowSize.trim()));
        else {
            pagingEntity.setRowSize(pagingConfig.getRowSize());
            }
        
        pagingEntity.setDefaultRowSize(pagingConfig.getRowSize());
        pagingEntity.setPageSize(pagingConfig.getPageSize());
        pagingEntity.setCustomRowSize(pagingConfig.getCustomRowSize());
        }

    
    protected Map<String, Object> bindingPagingMap(NativeWebRequest webRequest)
    {
        Map paramMap = new HashMap();
        Iterator iterator = webRequest.getParameterNames();
        while (iterator.hasNext()) {
             String name = (String)iterator.next();
             if (("targetRow".equals(name)) || ("orderBy".equals(name))) continue;
            if ("rowSize".equals(name))
            {
                continue;
                }
            
            String value = webRequest.getParameter(name);
            paramMap.put(name, value);
            }
        
        return paramMap;
        }
    }