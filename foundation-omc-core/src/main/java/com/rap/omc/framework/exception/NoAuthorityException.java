/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : NoAuthorityException.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 10. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.framework.exception;



import org.springframework.context.MessageSource;

import com.rap.omc.api.util.MessageUtil;


/**
 * <pre>
 * Class : NoAuthorityException
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@SuppressWarnings("serial")
public class NoAuthorityException extends BusinessException {

    private int statusCode=0;
    
    public NoAuthorityException(int statusCode, String code) {
        
        super(code, null, null, null, null);    
        super.setMessage(MessageUtil.getMessage(code));
        setStatusCode(statusCode);
    }
    
    
    /**
     * 생성자
     *
     * @param code 예외메시지코드
     */
    public NoAuthorityException(String code) {
        super(code, null, null, null, null);
        super.setMessage(MessageUtil.getMessage(code));
    }

    /**
     * 생성자
     *
     * @param cause 최초 발생한 Exception
     */
    public NoAuthorityException(Throwable cause) {
        super(cause);
    }

    /**
     * 생성자, Exception 발생 시 메시지 내용도 함께 보관한다.
     *
     * @param code 예외메시지코드
     * @param args 메시지를 구성하는 arguments
     */
    public NoAuthorityException(String code, Object[] messageParameters) {
        super(code, null, null, null, null);
        super.setMessage(MessageUtil.getMessage(code, messageParameters));
    }
    
    /**
     * 생성자, Exception 발생 시 메시지 내용도 함께 보관한다.
     * @param statusCode statusCode 
     * @param code 예외메시지코드
     * @param args 메시지를 구성하는 arguments
     */
    public NoAuthorityException(int statusCode, String code, Object[] messageParameters) {
        super(code, null, null, null, null);
        super.setMessage(MessageUtil.getMessage(code, messageParameters));
        setStatusCode(statusCode);
    }

    /**
     * 생성자
     *
     * @param code 예외메시지코드
     * @param messageSource 예외메시지를 가져오는 MessageSource
     */
    public NoAuthorityException(String code, MessageSource messageSource) {
        super(code, messageSource);
    }

    /**
     * 생성자, Exception 발생 시 메시지 내용도 함께 보관한다.
     *
     * @param code 예외메시지코드
     * @param messageSource 예외메시지를 가져오는 MessageSource
     * @param args 메시지를 구성하는 arguments
     */
    public NoAuthorityException(String code, MessageSource messageSource, Object[] messageParameters) {
        super(code, messageSource, messageParameters);
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
