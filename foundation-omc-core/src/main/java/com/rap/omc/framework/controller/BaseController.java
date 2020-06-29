/**
 * ===========================================
 * System Name : LGE GPDM
 * Program ID : BaseController.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2014. 12. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.framework.controller;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.Validator;

/**
 * <pre>
 * Class : BaseController
 * Description : 모든 Controller가 상속받아야 하는 Controller
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class BaseController {
    
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
