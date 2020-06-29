package com.rap.omc.framework.exception;

public class TriggerException extends BusinessException{
	private static final long serialVersionUID = -8250874954894572277L;
	private Object[] messageParameters;
    
    public TriggerException(String code){
        super(code, null, null, null, null);
    }
    
    public TriggerException(String code, Object[] messageParameters) {
        super(code, null, null, null, null);
        this.messageParameters = messageParameters;
    }
    
    public TriggerException(String code, Object[] messageParameters, Throwable cause) {
        super(code, null, cause);
        this.messageParameters = messageParameters;
    }
    
    public Object[] getMessageParameters() {
        return this.messageParameters;
    }
    
    public String getCode() {
        return super.getCode();
    }
}
