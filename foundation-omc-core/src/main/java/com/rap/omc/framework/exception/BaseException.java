package com.rap.omc.framework.exception;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import org.springframework.context.MessageSource;

	public class BaseException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    private String message;
    private String code;
    public BaseException()
    {
        this.message = "";
        this.code = "";
    }
    public BaseException(String message)
    {
        super(message);
        this.message = "";
        this.code = "";
        this.message = message;
    }
    public BaseException(String code, String message)
    {
        super(message);
        this.message = "";
        this.code = "";
        this.message = message;
        this.code = code;
    }
    public BaseException(Throwable cause)
    {
        super(cause);
        this.message = "";
        this.code = "";
    }
    public BaseException(String message, Throwable cause)
    {
        super(message, cause);
        this.message = "";
        this.code = "";
        this.message = message;
    }
    public BaseException(String code, String message, Throwable cause)
    {
        super(message, cause);
        this.message = "";
        this.code = "";
        this.message = message;
        this.code = code;
    }
    public BaseException(String code, MessageSource messageSource)
    {
    	this(code, messageSource, null);
    }
    public BaseException(String code, Object[] messageParameters)
    {
        this(code, null, messageParameters);
    }
    public BaseException(String code, Object[] messageParameters, Throwable cause)
    {
        this(code, null, messageParameters, cause);
    }
    public BaseException(String code, MessageSource messageSource, Object[] messageParameters)
    {
        this(code, messageSource, messageParameters, Locale.getDefault());
    }
    public BaseException(String code, MessageSource messageSource, Object[] messageParameters,
           Throwable cause)
    {
        this(code, messageSource, messageParameters, Locale.getDefault(), cause);
    }
    public BaseException(String code, MessageSource messageSource, Object[] messageParameters, Locale locale)
    {
        this((messageSource != null) ? messageSource.getMessage(code, messageParameters, locale) : "");
        this.code = code;
    }
    public BaseException(String code, MessageSource messageSource, Object[] messageParameters, Locale locale,
            Throwable cause)
    {
        this((messageSource != null) ? messageSource.getMessage(code, messageParameters, locale) : "", cause);
        this.code = code;
    }
    public String getMessage()
    {
        return this.message;
    }
    protected void setMessage(String message)
    {
        this.message = message;
    }
    protected void setCode(String code)
    {
        this.code = code;
    }
    public String getCode()
    {
        return this.code;
    }
    public Throwable getRootCause()
    {
        Throwable tempCause = getCause();
        while (tempCause != null) {
            if (tempCause.getCause() == null) {
                 break;
            }
            tempCause = tempCause.getCause();
        }
        return tempCause;
    }
    public String getStackTraceString()
    {
        StringWriter writer = new StringWriter();
        super.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }
    public void printStackTrace(PrintWriter log)
    {
        log.println(getStackTraceString());
    }
}
