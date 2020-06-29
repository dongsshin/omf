package com.rap.omc.framework.file.upload.exception;

public class TotalFileSizeLimitException extends FileUploadException{
	private static final long serialVersionUID = 1L;
	public TotalFileSizeLimitException(String message)
	{
	    super(message);
	}
	public TotalFileSizeLimitException(String code, String message)
	{
	    super(code, message);
	}
	public TotalFileSizeLimitException(Throwable cause)
	{
	    super(cause);
	}
	public TotalFileSizeLimitException(String code, String message, Throwable cause)
	{
	    super(code, message, cause);
	}
	public TotalFileSizeLimitException(String message, Throwable cause)
	{
	    super(message, cause);
	}
}