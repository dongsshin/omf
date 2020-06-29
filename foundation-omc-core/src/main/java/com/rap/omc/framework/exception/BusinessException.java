package com.rap.omc.framework.exception;

import java.util.Locale;
import org.springframework.context.MessageSource;

public class BusinessException extends BaseException
{

    private static final long serialVersionUID = 1L;

    private ExceptionOptionalInfo optionInfo;

    private Object[] messageParameters;


    public BusinessException()
    {
    }


    public BusinessException(String message)
    {
        super(message);
    }


    public BusinessException(Throwable cause)
    {
        super(cause);
    }


    public BusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }


    public BusinessException(String code, MessageSource messageSource)
    {
        super(code, messageSource);
    }


    public BusinessException(String code, Object[] messageParameters)
    {
        super(code, messageParameters);
        this.messageParameters = messageParameters;
    }


    public BusinessException(String code, MessageSource messageSource, Object[] messageParameters)
    {
        super(code, messageSource, messageParameters);
        this.messageParameters = messageParameters;
    }


    public BusinessException(String code, Object[] messageParameters, Throwable cause)
    {
        super(code, messageParameters, cause);
        this.messageParameters = messageParameters;
    }


    public BusinessException(String code, MessageSource messageSource, Object[] messageParameters,
            Throwable cause)
    {
        super(code, messageSource, messageParameters, cause);
        this.messageParameters = messageParameters;
    }


    public BusinessException(String code, MessageSource messageSource, Object[] messageParameters,
            Locale locale)
    {
        super(code, messageSource, messageParameters, locale);
        this.messageParameters = messageParameters;
    }


    public BusinessException(String code, MessageSource messageSource, Object[] messageParameters,
            Locale locale, Throwable cause)
    {
        super(code, messageSource, messageParameters, locale, cause);
        this.messageParameters = messageParameters;
    }


    public BusinessException(String code, ExceptionOptionalInfo optionInfo)
    {
        super.setCode(code);
        setOptionalInfo(optionInfo);
    }


    public BusinessException(String code, Throwable cause, ExceptionOptionalInfo optionInfo)
    {
        super(cause);
        super.setCode(code);
        setOptionalInfo(optionInfo);
    }


    public BusinessException(String code, Object[] messageParameters, ExceptionOptionalInfo optionInfo)
    {
        super(code, messageParameters);
        this.messageParameters = messageParameters;
        setOptionalInfo(optionInfo);
    }


    public void setOptionalInfo(ExceptionOptionalInfo extraInfo)
    {
        this.optionInfo = extraInfo;
    }


    public ExceptionOptionalInfo getOptionalInfo()
    {
        return this.optionInfo;
    }


    public Object[] getMessageParameters()
    {
        return this.messageParameters;
    }


    public String toString()
    {
        String str = super.getClass().getName();
        String message = super.getMessage();
        String code = super.getCode();
    
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.append((message != null) ? ": " + message : "");
        stringBuilder.append(((code != null) && (!("".equals(code)))) ? "(" + code + ")" : " ");
        return stringBuilder.toString();
    }
}