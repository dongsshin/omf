package com.rap.omc.framework.mail.exception;

import com.rap.omc.framework.exception.FoundationBaseException;

public class MailException extends FoundationBaseException{
    private static final long serialVersionUID = 1L;
    public MailException(String message)
    {
    	super(message);
    }
    public MailException(String code, String message)
    {
        super(code, message);
    }
    public MailException(Throwable cause)
    {
        super(cause);
    }
    public MailException(String code, String message, Throwable cause)
    {
        super(code, message, cause);
    }
    public MailException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
