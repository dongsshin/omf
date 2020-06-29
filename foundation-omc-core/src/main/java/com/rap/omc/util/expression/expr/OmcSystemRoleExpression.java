/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcExpressionRoleSystem.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 25.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import java.util.Set;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;


/**
 * <pre>
 * Class : OmcExpressionRoleSystem
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSystemRoleExpression extends OmcRoleExpression{
    public OmcSystemRoleExpression(String originalExpression,BusinessObjectMasterVO currentUserObj,Set<String> owningRoleList) {
        super(originalExpression,currentUserObj,owningRoleList);
    }
    public void eval(boolean isNot){
        if(this.execute(isNot)) this.setResolvedExpression("TRUE");
    }
    protected boolean execute(boolean isNot){
        return super.execute(isNot);
    }
}
