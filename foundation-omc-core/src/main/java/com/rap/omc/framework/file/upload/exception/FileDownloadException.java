package com.rap.omc.framework.file.upload.exception;

import com.rap.omc.framework.exception.BaseException;

public class FileDownloadException extends BaseException{
	private static final long serialVersionUID = 1L;
	public FileDownloadException(String message)
	{
	    super(message);
	}
	public FileDownloadException(String code, String message)
	{
	    super(code, message);
	}
	public FileDownloadException(Throwable cause)
	{
	    super(cause);
	}
	public FileDownloadException(String code, String message, Throwable cause)
	{
	    super(code, message, cause);
	}
	public FileDownloadException(String message, Throwable cause)
	{
	    super(message, cause);
	}
}