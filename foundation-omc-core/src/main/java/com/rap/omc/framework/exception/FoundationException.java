package com.rap.omc.framework.exception;

import org.springframework.context.MessageSource;

import com.rap.omc.api.util.MessageUtil;


/**
 * <pre>
 * Class : FoundationException
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class FoundationException extends SystemException {
	private static final long serialVersionUID = 2217430042345028027L;
	private int statusCode=0;
    
    /**
     * 기본 생성자
     */
    public FoundationException() {
        super();
    }

    
    /**
     * 생성자
     *
     * @param message 예외메시지
     */
    public FoundationException(int statusCode, String code) {
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
    public FoundationException(String code) {
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
    public FoundationException(String code, String message) {
        super(MessageUtil.getMessage(code));
        super.setCode(code);
    }    

    /**
     * 생성자
     *
     * @param cause 최초 발생한 Exception
     */
    public FoundationException(Throwable cause) {
        super(cause);
    }

    /**
     * 생성자
     *
     * @param code 예외코드
     * @param message 예외메시지
     * @param cause 최초 발생한 Exception
     */
    public FoundationException(int statusCode, String code, Throwable cause) {
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
    public FoundationException(String code, Throwable cause) {
        super(code, MessageUtil.getMessage(code), cause);
    }

    /**
     *  생성자
     *
     * @param code 예외메시지코드
     * @param messageSource 예외메시지를 가져오는 MessageSource
     */
    public FoundationException(String code, MessageSource messageSource) {
        super(code, messageSource);
    }

    /**
     * 생성자
     *
     * @param code 예외메시지코드
     * @param messageSource 예외메시지를 가져오는 MessageSource
     * @param args 메시지를 구성하는 arguments
     */
    public FoundationException(int statusCode, String code, Object[] messageParameters) {
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
    public FoundationException(String code, Object... messageParameters) {
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
    public FoundationException(String code, Object[] messageParameters, Throwable cause) {
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
