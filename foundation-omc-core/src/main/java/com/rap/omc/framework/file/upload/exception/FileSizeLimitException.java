package com.rap.omc.framework.file.upload.exception;

public class FileSizeLimitException extends FileUploadException{
	private static final long serialVersionUID = 1L;
	public FileSizeLimitException(String message)
	{
	    super(message);
	}
	public FileSizeLimitException(String code, String message)
	{
	    super(code, message);
	}
	public FileSizeLimitException(Throwable cause)
	{
	    super(cause);
	}
	public FileSizeLimitException(String code, String message, Throwable cause)
	{
	    super(code, message, cause);
	}
	public FileSizeLimitException(String message, Throwable cause)
	{
	    super(message, cause);
	}
}