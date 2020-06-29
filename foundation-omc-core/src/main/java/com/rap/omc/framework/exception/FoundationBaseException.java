package com.rap.omc.framework.exception;

public class FoundationBaseException extends BaseException
{
    private static final long serialVersionUID = 1L;
    public FoundationBaseException(String message)
    {
        super(message);
    }
    public FoundationBaseException(String code, String message)
    {
        super(code, message);
    }
    public FoundationBaseException(Throwable cause)
    {
        super(cause);
    }
    public FoundationBaseException(String code, String message, Throwable cause)
    {
        super(code, message, cause);
    }
    public FoundationBaseException(String message, Throwable cause)
    {
        super(message, cause);
    }
    public String toString()
    {
        String str = super.getClass().getName();
        String message = super.getMessage();
        return ((message != null) ? str + ": " + message : str);
    }
}