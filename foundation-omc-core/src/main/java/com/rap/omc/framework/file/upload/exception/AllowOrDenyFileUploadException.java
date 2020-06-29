package com.rap.omc.framework.file.upload.exception;

public class AllowOrDenyFileUploadException extends FileUploadException{
	private static final long serialVersionUID = 1L;
	public AllowOrDenyFileUploadException(String message)
	{
	    super(message);
	}
	public AllowOrDenyFileUploadException(String code, String message)
	{
	    super(code, message);
	}
	public AllowOrDenyFileUploadException(Throwable cause)
	{
	    super(cause);
	}
	public AllowOrDenyFileUploadException(String code, String message, Throwable cause)
	{
	    super(code, message, cause);
	}
	public AllowOrDenyFileUploadException(String message, Throwable cause)
	{
	    super(message, cause);
	}
}