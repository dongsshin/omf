/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SpringFactoryUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 24. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util.spring;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

import com.rap.omc.framework.exception.FoundationException;

/**
 * <pre>
 * Class : SpringFactoryUtil
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class SpringFactoryUtil {

    private static SpringFactoryUtil sInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    public synchronized static SpringFactoryUtil getInstance(){
        if (sInstance == null) {
            sInstance = new SpringFactoryUtil();
        }
        return sInstance;
    }

    /**
     * 생성된 Factory에서 bean을 return해주는 method
     *
     * @param beanName bean ID
     * @return
     */
    public static Object getBean(String beanName){
        try {
            Object rtnBean = SpringFactoryLoader.getContext().getBean(beanName);
            return rtnBean;
        } catch (Exception e) {
            throw new FoundationException(e);
        }
    }

    /**
     * 생성된 Factory에서 bean을 return해주는 method
     *
     * @param paramClass bean class type
     * @return
     */
    public static <T> T getBean(Class<T> paramClass){
        try {
            T rtnBean = SpringFactoryLoader.getContext().getBean(paramClass);
            return rtnBean;
        } catch (NoUniqueBeanDefinitionException e) {
            try {
                String infName = paramClass.getSimpleName();
                String serviceBeanId = infName.substring(0, 1).toLowerCase() + infName.substring(1);
                T rtnBean = SpringFactoryLoader.getContext().getBean(serviceBeanId, paramClass);
                return rtnBean;
            } catch (Exception se) {
                throw new FoundationException(se);
            }
        } catch (NoSuchBeanDefinitionException e) {
            try {
                String infName = paramClass.getSimpleName().replace("Impl", "");
                String serviceBeanId = infName.substring(0, 1).toLowerCase() + infName.substring(1);
                Object rtnBean = SpringFactoryLoader.getContext().getBean(serviceBeanId);
                @SuppressWarnings("unchecked")
                T obj = (T)rtnBean;
                return obj;
            } catch (Exception se) {
                throw new FoundationException(se);
            }
        } catch (Exception e) {
            throw new FoundationException(e);
        }
    }

    /**
     * 생성된 Factory에서 bean을 return해주는 method
     *
     * @param beanName bean ID
     * @param paramClass bean class type
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> paramClass){
        try {
            T rtnBean = SpringFactoryLoader.getContext().getBean(beanName, paramClass);
            return rtnBean;
        } catch (Exception e) {
            throw new FoundationException(e);
        }
    }
}
