/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : RestBaseController.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 2.  hyeyoung.park   Initial
 * ===========================================
 */
package com.rap.omc.framework.controller;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.Validator;

/**
 * <pre>
 * Class : RestBaseController
 * Description : Rest W/S를 처리하는 모든 Controller가 상속받아야 하는 Controller
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class RestBaseController {
    
    /**
     * MessageSource
     */
    @Resource(name = "messageSource")
    protected MessageSource messageSource;

    /**
     * MessageSourceAccessor.
     */
    @Resource(name = "messageSourceAccessor")
    protected MessageSourceAccessor messageSourceAccessor;

//    /**
//     * Configuration
//     */
//    @Resource(name = "propertiesService")
//    protected ConfigService propertiesService;

    /**
     * Bean Validator
     */
    @Resource(name="beanValidator")
    protected Validator validator; 

}
