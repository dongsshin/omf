/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcExpressionFunction.java
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
 * Class : OmcExpressionFunction
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public abstract class OmcFunctionExpression extends OmcExpression{
    public OmcFunctionExpression(String originalExpression,BusinessObjectMasterVO currentUserObj) {
        super(originalExpression,currentUserObj);
    }
}
