package com.rap.omc.util.core;                                                                                         
                                                                                                                       
import java.lang.reflect.Constructor;                                                                                  
/*     */ import java.lang.reflect.InvocationTargetException;                                                          
/*     */ import org.slf4j.Logger;                                                                                     
/*     */ import org.slf4j.LoggerFactory;                                                                              
                                                                                                                       
@SuppressWarnings({"rawtypes","unchecked"})  
public class ClassUtil{                                                                                                
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);                                     
    public static ClassLoader getClassLoader()                                                                         
    {                                                                                                                  
        ClassLoader classLoader = null;                                                                      
        try                                                                                                  
        {                                                                                                    
            classLoader = Thread.currentThread().getContextClassLoader();                                    
    }                                                                                                
        catch (Throwable t) {                                                                                
            LOGGER.error(t.getMessage());                                                                    
    }                                                                                                
                                                                                                             
        if (classLoader == null) {                                                                           
            classLoader = ClassUtil.class.getClassLoader();                                                  
    }                                                                                                
                                                                                                             
        return classLoader;                                                                                  
    }                                                                                                    
	public static Class<?> getClass(String className)                                                                  
    {                                                                                                        
        ClassLoader classLoader = getClassLoader();                                                          
        Class clazz = null;                                                                                  
        try                                                                                                  
        {                                                                                                    
            clazz = classLoader.loadClass(className.trim());                                                 
        } catch (ClassNotFoundException e) {                                                             
            throw new IllegalStateException("The " + className + " class could not be found.", e);           
        }                                                                                                
        return clazz;                                                                                        
    }                                                                                                    
    public static Object getInstance(String className)                                                       
    {                                                                                                        
        return getInstance(getClass(className));                                                             
    }                                                                                                    
    public static <T> T getInstance(Class<T> dataClass)                                                      
    {                                                                                                        
        Object object = null;                                                                                
        try {                                                                                                
            object = dataClass.newInstance();                                                                
        } catch (InstantiationException e) {                                                             
            throw new IllegalStateException(                                                                 
                    "The " + dataClass.getName() + " class object cannot be instantiated.", e);                        
        } catch (IllegalAccessException e) {                                                             
            throw new IllegalStateException(                                                                 
                    "The currently executing method does not have access to the constructor of " + dataClass.getName() 
                            + ".",                                                                                     
                    e);                                                                                                
        }                                                                                                
        return (T) object;                                                                                   
    }                                                                                                    
	public static Object getInstance(String className, Class<?>[] classParam, Object[] params)               
    {                                                                                                        
        Object instance = null;                                                                              
        Class classInstance = null;                                                                          
        try                                                                                                  
        {                                                                                                    
            classInstance = getClass(className);                                                             
            Constructor classConstructor = classInstance.getConstructor(classParam);                         
            instance = classConstructor.newInstance(params);                                                 
        } catch (IllegalAccessException e) {                                                             
            throw new IllegalStateException(                                                                 
                    "The currently executing method does not have access to the constructor of " + className + ".", e);
        } catch (InstantiationException e) {                                                             
            throw new IllegalStateException("The " + className + " class object cannot be instantiated.", e);
        } catch (NoSuchMethodException e) {                                                              
            throw new IllegalStateException("The constructor of " + className + " cannot be found", e);      
        } catch (InvocationTargetException e) {                                                          
            throw new IllegalStateException(                                                                 
                    "Error occurrred while invoking constructor of " + className + ".", e);                            
        }                                                                                                
                                                                                                             
        return instance;                                                                                     
    }                                                                                                    
}                                                                                                        
                                                                                                                       