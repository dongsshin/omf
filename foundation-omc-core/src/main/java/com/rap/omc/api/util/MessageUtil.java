/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MessageUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 2. 4. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util;

import java.util.Locale;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;

import com.rap.omc.api.util.spring.SpringFactoryUtil;

/**
 * <pre>
 * Class : MessageUtil
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class MessageUtil {

    private MessageSourceAccessor messageSourceAccessor;
    
    private static MessageUtil mInstance;
    

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static MessageUtil getInstance(){
        if (mInstance == null) {
            mInstance = new MessageUtil();
            mInstance.messageSourceAccessor = (MessageSourceAccessor)SpringFactoryUtil.getBean("messageSourceAccessor");
        }
        return mInstance;
    }

    /**
     * 세션의 Locale에 해당되는 메시지 내용을 리턴한다.
     *
     * @param code 메시지 코드
     * @return
     */
    public static String getMessage(String code){
        String message = null;
        try {
            message = getInstance().messageSourceAccessor.getMessage(code);
        } catch (NoSuchMessageException e) {
            message = code;
        }
        return message;
    }

    /**
     * 세션의 Locale에 해당되는 메시지 코드 내용을 리턴한다.
     *
     * @param code 메시지 코드
     * @param args 메시지 Arguments
     * @return
     */
    public static String getMessage(String code, Object[] args){
        String message = null;
        try {
            message = getInstance().messageSourceAccessor.getMessage(code, args);
        } catch (NoSuchMessageException e) {
            message = code;
        }
        return message;
    }
    
    /**
     * 특정 Locale에 대한 메시지 코드 내용을 리턴한다. (en, ko)
     *
     * @param code
     * @param locale
     * @return
     */
    public static String getMessage(String code, Locale locale){
        String message = null;
        try {
            message = getInstance().messageSourceAccessor.getMessage(code, locale);
        } catch (NoSuchMessageException e) {
            message = code;
        }
        return message;
    }
    
    /**
     * 특정 Locale에 대한 메시지 코드 내용을 리턴한다. (en, ko)
     *
     * @param code
     * @param args
     * @param locale
     * @return
     */
    public static String getMessage(String code, Object[] args, Locale locale){
        String message = null;
        try {
            message = getInstance().messageSourceAccessor.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            message = code;
        }
        return message;
    }
}
