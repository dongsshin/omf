package com.rap.omc.framework.exception;

import java.util.Locale;
import org.springframework.context.MessageSource;

public class SystemException extends BaseException{
     private static final long serialVersionUID = 1L;
     public SystemException()
     {
     }
     public SystemException(String message)
     {
        super(message);
     }
     public SystemException(String code, String message)
     {
        super(message);
        super.setCode(code);
     }
     public SystemException(Throwable cause)
     {
        super(cause);
     }
     public SystemException(String message, Throwable cause)
     {
        super(message, cause);
     }
     public SystemException(String code, String message, Throwable cause)
     {
        super(code, message, cause);
     }
     public SystemException(String code, MessageSource messageSource)
     {
        super(code, messageSource);
     }
     public SystemException(String code, MessageSource messageSource, Object[] messageParameters)
     {
        super(code, messageSource, messageParameters);
     }
     public SystemException(String code, MessageSource messageSource, Object[] messageParameters, Locale locale)
     {
        super(code, messageSource, messageParameters, locale);
     }
     public String toString()
     {
        String str = super.getClass().getName();
        String message = super.getMessage();
        String code = super.getCode();
        
        StringBuilder strBuilder = new StringBuilder(str);
        strBuilder.append((message != null) ? ": " + message : "");
        strBuilder.append(((code != null) && (!("".equals(code)))) ? "(" + code + ")" : " ");
        return strBuilder.toString();
     }
}