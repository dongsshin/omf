/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SpringFactoryLoader.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 24. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util.spring;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : SpringFactoryLoader
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@SuppressWarnings("static-access")
@Service
public class SpringFactoryLoader implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getContext(){
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext paramApplicationContext) throws BeansException{
        this.context = paramApplicationContext; 
    }
}
