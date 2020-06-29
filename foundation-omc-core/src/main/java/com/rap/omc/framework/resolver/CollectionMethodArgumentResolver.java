package com.rap.omc.framework.resolver;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.MethodParameter;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class CollectionMethodArgumentResolver implements HandlerMethodArgumentResolver
{

    private Class[] excludeTypes = { ModelMap.class };

    
    public void setExcludeTypes(Class[] excludeTypes)
    {
        this.excludeTypes = excludeTypes;
        }

    
    public boolean supportsParameter(MethodParameter parameter)
    {
        Class paramType = parameter.getParameterType();
        
        if (List.class.isAssignableFrom(paramType)) {
            ParameterizedType genericSuperClass = (ParameterizedType)parameter.getGenericParameterType();
            Type type = genericSuperClass.getActualTypeArguments()[0];
            Class itemOfListType = null;
            
            if (type instanceof Class)/*  66 */ itemOfListType = (Class)type;
            else if (type instanceof ParameterizedType) {
                itemOfListType = (Class)((ParameterizedType)type).getRawType();
                }
            
            if (Map.class.isAssignableFrom(itemOfListType)) {
                return true;
                }
            if (!(isExcludedType(paramType))) {
                return true;
                }
            }
        return false;
        }

    
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory)/*     */ throws Exception
    {
        HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
        Class paramType = parameter.getParameterType();
        
        if (List.class.isAssignableFrom(paramType)) {
            ParameterizedType genericSuperClass = (ParameterizedType)parameter.getGenericParameterType();
            Type type = genericSuperClass.getActualTypeArguments()[0];
            Class itemOfListType = null;
            
            if (type instanceof Class)/*  96 */ itemOfListType = (Class)type;
            else if (type instanceof ParameterizedType) {
                itemOfListType = (Class)((ParameterizedType)type).getRawType();
                }
            
            if (Map.class.isAssignableFrom(itemOfListType)) {
                return convertRequestToMapList(request, itemOfListType);
                }
            if (!(isExcludedType(paramType))) {
                return convertRequestToObjectList(request, itemOfListType);
                }
            }
        
        return new Object();
        }

    
    private boolean isExcludedType(Class<?> paramType){
        for (int i = 0; i < this.excludeTypes.length; ++i) {
            if (this.excludeTypes[i].equals(paramType)) {
                return true;
                }
            }
        return false;
    }
    private List convertRequestToMapList(HttpServletRequest request, Class<?> itemOfListType){
        List list = new ArrayList();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = (String)enumeration.nextElement();
            String[] values = request.getParameterValues(name);
            for (int i = 0; i < values.length; ++i) {
                Map paramMap = null;
                if (i < list.size()) {
                    paramMap = (Map)list.get(i);
                    } else {
                    paramMap = newMapInstance(itemOfListType);
                    list.add(paramMap);
                    }
                paramMap.put(name, values[i]);
                }
            }
        return list;
        }

    
    
	private Object convertRequestToObjectList(HttpServletRequest request, Class<?> itemOfListType){
        List list = new ArrayList();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = (String)enumeration.nextElement();
            String[] values = request.getParameterValues(name);
            for (int i = 0; i < values.length; ++i) {
                Object paramObject = null;
                if (i < list.size()) {
                    paramObject = list.get(i);
                    } else {
                    paramObject = BeanUtils.instantiateClass(itemOfListType);
                    list.add(paramObject);
                    }
                BeanWrapperImpl wrapper = new BeanWrapperImpl(paramObject);
                if (wrapper.isWritableProperty(name)) {
                    wrapper.setPropertyValue(name, values[i]);
                    }
                }
            }
        return list;
        }

    
    private Map newMapInstance(Class<?> itemOfListType){
        if (itemOfListType.isInterface()) {
            return new HashMap();
            }
        return ((Map)BeanUtils.instantiateClass(itemOfListType));
        }
}