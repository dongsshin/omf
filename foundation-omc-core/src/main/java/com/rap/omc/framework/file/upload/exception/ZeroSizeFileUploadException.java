package com.rap.omc.framework.file.upload.exception;

public class ZeroSizeFileUploadException extends FileUploadException{
	private static final long serialVersionUID = 1L;
	public ZeroSizeFileUploadException(String message)
	{
	    super(message);
	}
	public ZeroSizeFileUploadException(String code, String message)
	{
	    super(code, message);
	}
	public ZeroSizeFileUploadException(Throwable cause)
	{
	    super(cause);
	}
	public ZeroSizeFileUploadException(String code, String message, Throwable cause)
	{
	    super(code, message, cause);
	}
	public ZeroSizeFileUploadException(String message, Throwable cause)
	{
	    super(message, cause);
	}
}