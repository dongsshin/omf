package com.rap.omc.framework.file.upload.exception;

import com.rap.omc.framework.exception.BaseException;

public class FileUploadException extends BaseException
{
    private static final long serialVersionUID = 1L;
    public FileUploadException(String message)
    {
        super(message);
    }
    public FileUploadException(String code, String message)
    {
        super(code, message);
    }
    public FileUploadException(Throwable cause)
    {
        super(cause);
    }
    public FileUploadException(String code, String message, Throwable cause)
    {
        super(code, message, cause);
    }
    public FileUploadException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
