/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcExpression.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 25.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;

/**
 * <pre>
 * Class : OmcExpression
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public abstract class OmcExpression {
    private String originalExpression;
    private String resolvedExpression;
    private BusinessObjectMasterVO currentUserObj;
    
    public BusinessObjectMasterVO getCurrentUserObj(){
        return currentUserObj;
    }

    
    public void setCurrentUserObj(BusinessObjectMasterVO currentUserObj){
        this.currentUserObj = currentUserObj;
    }

    /**
     * @param originalExpression
     */
    public OmcExpression(String originalExpression,BusinessObjectMasterVO currentUserObj) {
        super();
        this.originalExpression = originalExpression;
        this.setResolvedExpression("FALSE");
        this.setCurrentUserObj(currentUserObj);
    }
    
    public String getOriginalExpression(){
        return originalExpression;
    }
    
    public void setOriginalExpression(String originalExpression){
        this.originalExpression = originalExpression;
    }
    
    public String getResolvedExpression(){
        return resolvedExpression;
    }


    public void setResolvedExpression(String resolvedExpression){
        this.resolvedExpression = resolvedExpression;
    }
    public abstract void eval(boolean isNot);
    protected abstract boolean execute(boolean isNot);
}
