package com.rap.omc.dataaccess.paging.exception;

import com.rap.omc.framework.exception.FoundationBaseException;

public class PagingException extends FoundationBaseException{
    private static final long serialVersionUID = -7816493263733809423L;
    public PagingException(String message)
    {
        super(message);
    }
    public PagingException(Throwable cause)
    {
        super(cause);
    }
    public PagingException(String message, Throwable cause)
    {
        super(message, cause);
    }
}