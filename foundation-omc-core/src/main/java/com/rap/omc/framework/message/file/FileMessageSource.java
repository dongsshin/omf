package com.rap.omc.framework.message.file;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.ReflectionUtils;

import com.rap.omc.framework.message.RefreshableMessageSource;
import com.rap.omc.framework.message.exception.MessageException;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class FileMessageSource extends ReloadableResourceBundleMessageSource implements RefreshableMessageSource{
  
	protected String loadType = "preLoad";
    public void setLoadType(String loadType)
    {
        if (("preLoad".equals(loadType)) || ("lazyLoad".equals(loadType))) {
        	this.loadType = loadType;
        }
        else {
        	throw new MessageException("Message load mode should be defined among preLoad,lazyLoad,firstCallLoad");
        }
    }
    public void afterPropertiesSet() throws Exception
    {
        setPrivateField("resourceLoader", new FileMessageResourceLoader());
        
        if (isPreLoad()) refresh();
    }
    protected String resolveCodeWithoutArguments(String code, Locale locale)
    {
        String message = super.resolveCodeWithoutArguments(code, locale);
        return message;
    }
    protected MessageFormat resolveCode(String code, Locale locale)
    {
        MessageFormat messageFormat = super.resolveCode(code, locale);
        return messageFormat;
    }
    
	public void refresh()
    {
        Map cachedProperties = (Map)getPrivateField("cachedProperties");
        Map cachedMergedProperties = (Map)getPrivateField("cachedMergedProperties");
        Map cachedFilenames = (Map)getPrivateField("cachedFilenames");
        Long cacheMillis = null;
        try {
        	cacheMillis = (Long)getPrivateField("cacheMillis");
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        Set<String> basenames = (Set<String>)getPrivateField("basenameSet");
        AbstractResourceBasedMessageSource AbstractResourceBasedMessageSource = null;
        
        if (isLazyLoad())
        {
            synchronized (cachedProperties) {
                cachedProperties.clear();
            }
            synchronized (cachedMergedProperties) {
                cachedMergedProperties.clear();
            }
            synchronized (cachedFilenames) {
                cachedFilenames.clear();
            }
        }
        else
        {
            boolean isSetCacheSeconds = cacheMillis.longValue() >= 0L;
            if (!(isSetCacheSeconds)) {
                setCacheSeconds(2);
            }
            
            if (cachedProperties.size() == 0) {
                for (String basename : basenames) {
                	List<String> filenames = calculateAllFilenames(basename, Locale.getDefault());
                    for (String filename : filenames) {
                        refreshProperties(filename, null);
                        }
                    }
                }
            else
            {
                Set<String> filenames = (Set<String>)cachedProperties.keySet();
                for (Iterator fileIter = filenames.iterator(); fileIter.hasNext();) {
                    String filename = (String)fileIter.next();
                    refreshProperties(filename,
                            (ReloadableResourceBundleMessageSource.PropertiesHolder)cachedProperties.get(filename));
                    }
            }
            if (!(isSetCacheSeconds)) {
                cachedMergedProperties.clear();
                setCacheSeconds(-1);
            }
        }
    }
    public void refreshIncludingAncestors()
    {
        refresh();
        MessageSource parentMessageSource = getParentMessageSource();
        if (parentMessageSource instanceof RefreshableMessageSource)
        ((RefreshableMessageSource)parentMessageSource).refreshIncludingAncestors();
    }
    public void clearCacheIncludingAncestors()
    {
        clearCache();
        MessageSource parentMessageSource = getParentMessageSource();
        if (parentMessageSource instanceof ReloadableResourceBundleMessageSource)
            ((ReloadableResourceBundleMessageSource)parentMessageSource).clearCacheIncludingAncestors();
        else if (parentMessageSource instanceof RefreshableMessageSource)
            ((RefreshableMessageSource)parentMessageSource).clearCacheIncludingAncestors();
        }
    private boolean isPreLoad()
    {
        return "preLoad".equals(this.loadType);
    }

    
    private boolean isLazyLoad()
    {
    	return "lazyLoad".equals(this.loadType);
    }

    
    private <T> T getPrivateField(String fieldName)
    {
    	Class targetClass = null;
    	if(fieldName.equals("cacheMillis") || fieldName.equals("basenameSet")){
    		targetClass = super.getClass().getSuperclass().getSuperclass();
    	}else {
    		targetClass = super.getClass().getSuperclass();
    	}
        Object result = null;
        try
        {
            Field field = targetClass.getDeclaredField(fieldName);
            ReflectionUtils.makeAccessible(field);
            result = field.get(this);
            } catch (SecurityException e) {
            	throw new MessageException(e);
            } catch (NoSuchFieldException e) {
            	throw new MessageException(e);
            } catch (IllegalArgumentException e) {
            	throw new MessageException(e);
            } catch (IllegalAccessException e) {
            	throw new MessageException(e);
            }
        
        return (T)result;
    }
    private void setPrivateField(String fieldName, Object value){
       Class targetClass = super.getClass().getSuperclass();
       try
       {
            Field field = targetClass.getDeclaredField(fieldName);
            ReflectionUtils.makeAccessible(field);
            
            field.set(this, value);
            } catch (SecurityException e) {
            throw new MessageException(e);
            } catch (NoSuchFieldException e) {
            throw new MessageException(e);
            } catch (IllegalArgumentException e) {
            throw new MessageException(e);
            } catch (IllegalAccessException e) {
            throw new MessageException(e);
        }
    }
}