package com.rap.omc.dataaccess.exception;

import com.rap.omc.framework.exception.FoundationBaseException;

public class DaoException extends FoundationBaseException{

    private static final long serialVersionUID = 8809149034575126997L;
    public DaoException(String message)
    {
        super(message);
    }
    public DaoException(String code, String message)
    {
        super(code, message);
        }
    public DaoException(Throwable cause)
    {
        super(cause);
    }
    public DaoException(String message, Throwable cause)
    {
        super(message, cause);
    }
    public DaoException(String code, String message, Throwable cause)
    {
        super(code, message, cause);
    }
}