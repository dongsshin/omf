/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BranchException.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 4. 23. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.framework.exception;


/**
 * <pre>
 * Class : BranchException
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@SuppressWarnings("serial")
public class BranchException extends BusinessException {

    private Object[] messageParameters;

    public BranchException(String code){
        super(code, null, null, null, null);
    }

    public BranchException(String code, Object[] messageParameters){
        super(code, null, null, null, null);
        this.messageParameters = messageParameters;
    }

    public BranchException(String code, Object[] messageParameters, Throwable cause){
        super(code, null, cause);
        this.messageParameters = messageParameters;
    }

    public Object[] getMessageParameters(){
        return this.messageParameters;
    }
}
