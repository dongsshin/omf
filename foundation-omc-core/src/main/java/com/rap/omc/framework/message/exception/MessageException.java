package com.rap.omc.framework.message.exception;

import com.rap.omc.framework.exception.FoundationBaseException;
public class MessageException extends FoundationBaseException
{
	private static final long serialVersionUID = -6870044584432409361L;
	public MessageException(String message)
    {
        super(message);
    }
    public MessageException(Throwable cause)
    {
        super(cause);
    }
    public MessageException(String message, Throwable cause)
    {
        super(message, cause);
    }
}