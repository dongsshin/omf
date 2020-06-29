/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OQLException.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 27.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.framework.exception;

import org.springframework.context.MessageSource;

import com.rap.omc.api.util.MessageUtil;
import com.rap.omc.framework.exception.SystemException;


/**
 * <pre>
 * Class : OQLException
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("serial")
public class OQLException extends SystemException {

    private int statusCode=0;
    
    /**
     * 기본 생성자
     */
    public OQLException() {
        super();
    }

    
    /**
     * 생성자
     *
     * @param message 예외메시지
     */
    public OQLException(int statusCode, String code) {
        super();
        super.setCode(code);
        super.setMessage(MessageUtil.getMessage(code));
        setStatusCode(statusCode);
    }
    
    /**
     * 생성자
     *
     * @param message 예외메시지
     */
    public OQLException(String code) {
        super();
        super.setCode(code);
        super.setMessage(MessageUtil.getMessage(code));
    }
    
    /**
     * 생성자
     * 
     * @param code 예외코드
     * @param message 예외메시지
     */
    public OQLException(String code, String message) {
        super(MessageUtil.getMessage(code));
        super.setCode(code);
    }    

    /**
     * 생성자
     *
     * @param cause 최초 발생한 Exception
     */
    public OQLException(Throwable cause) {
        super(cause);
    }

    /**
     * 생성자
     *
     * @param code 예외코드
     * @param message 예외메시지
     * @param cause 최초 발생한 Exception
     */
    public OQLException(int statusCode, String code, Throwable cause) {
        super(code, MessageUtil.getMessage(code), cause);
        setStatusCode(statusCode);
    }
    
    
    /**
     * 생성자
     *
     * @param code 예외코드
     * @param message 예외메시지
     * @param cause 최초 발생한 Exception
     */
    public OQLException(String code, Throwable cause) {
        super(code, MessageUtil.getMessage(code), cause);
    }

    /**
     *  생성자
     *
     * @param code 예외메시지코드
     * @param messageSource 예외메시지를 가져오는 MessageSource
     */
    public OQLException(String code, MessageSource messageSource) {
        super(code, messageSource);
    }

    /**
     * 생성자
     *
     * @param code 예외메시지코드
     * @param messageSource 예외메시지를 가져오는 MessageSource
     * @param args 메시지를 구성하는 arguments
     */
    public OQLException(int statusCode, String code, Object[] messageParameters) {
        super();
        super.setCode(code);
        super.setMessage(MessageUtil.getMessage(code, messageParameters));
        setStatusCode(statusCode);
    }
    
    /**
     * 생성자
     *
     * @param code 예외메시지코드
     * @param messageSource 예외메시지를 가져오는 MessageSource
     * @param args 메시지를 구성하는 arguments
     */
    public OQLException(String code, Object[] messageParameters) {
        super();
        super.setCode(code);
        super.setMessage(MessageUtil.getMessage(code, messageParameters));
    }

    /**
     * 생성자
     *
     * @param code 예외메시지코드
     * @param messageSource 예외메시지를 가져오는 MessageSource
     * @param args 메시지를 구성하는 arguments
     */
    public OQLException(String code, Object[] messageParameters, Throwable cause) {
        //super();
        super(cause);
        super.setCode(code);
        super.setMessage(MessageUtil.getMessage(code, messageParameters));
       
     
    }

    /**
     * 
     * 
     * @return the statusCode
     */
    public int getStatusCode(){
        return statusCode;
    }


    /**
     * 
     * 
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode){
        this.statusCode = statusCode;
    }
}



