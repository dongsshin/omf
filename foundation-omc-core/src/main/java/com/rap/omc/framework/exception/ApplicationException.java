/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ApplicationException.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 2. 3. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.framework.exception;


import org.springframework.context.MessageSource;

import com.rap.omc.api.util.MessageUtil;




/**
 * <pre>
 * Class : ApplicationException
 * Description : 프로젝트에서 비즈니스 Rule 위반 시 발생시키는 Exception
 * </pre>
 * 
 * @author hyeyoung.park
 */
@SuppressWarnings("serial")
public class ApplicationException extends BusinessException {

    private int statusCode=0;
    
    
    public ApplicationException(int statusCode, String code) {
        
        super(code, null, null, null, null);    
        super.setMessage(MessageUtil.getMessage(code));
        setStatusCode(statusCode);
    }
    
    
    public ApplicationException(String code) { 
        super(code, null, null, null, null);    
        super.setMessage(MessageUtil.getMessage(code));
    }
    
    /**
     * 생성자
     *
     * @param cause 최초 발생한 Exception
     */
    public ApplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * 생성자
     *
     * @param code 예외메시지코드
     * @param messageSource 예외메시지를 가져오는 MessageSource
     */
    public ApplicationException(String code, MessageSource messageSource) {
        super(code, messageSource);
    }

    public ApplicationException(String code, Throwable cause) {
        super(code, null, cause);
        super.setMessage(MessageUtil.getMessage(code));
    }
    
    /**
     * 생성자, Exception 발생 시 메시지 내용도 함께 보관한다.
     * @param statusCode statusCode 
     * @param code 예외메시지코드
     * @param args 메시지를 구성하는 arguments
     */
    public ApplicationException(int statusCode, String code, Object[] messageParameters) {
        super(code, null, null, null, null);
        super.setMessage(MessageUtil.getMessage(code, messageParameters));
        setStatusCode(statusCode);
    }

    public ApplicationException(String code, Object[] messageParameters) {
        super(code, null, null, null, null);    
        super.setMessage(MessageUtil.getMessage(code, messageParameters));
    }

    public ApplicationException(String code, Object[] messageParameters, Throwable cause) {
        super(code, null, cause);
        super.setMessage(MessageUtil.getMessage(code, messageParameters));
    }

    /**
     * 생성자, Exception 발생 시 메시지 내용도 함께 보관한다.
     *
     * @param code 예외메시지코드
     * @param messageSource 예외메시지를 가져오는 MessageSource
     * @param args 메시지를 구성하는 arguments
     */
    public ApplicationException(String code, MessageSource messageSource, Object[] messageParameters) {
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
