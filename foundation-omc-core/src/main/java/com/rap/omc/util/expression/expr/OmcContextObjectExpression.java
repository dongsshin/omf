/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcContextObjectExpression.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 26.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.object.model.BusinessObjectRootVO;

/**
 * <pre>
 * Class : OmcContextObjectExpression
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcContextObjectExpression extends OmcObjectExpression {
    public OmcContextObjectExpression(String originalExpression,BusinessObjectMasterVO currentUserObj,BusinessObjectRootVO bizObj) {
        super(originalExpression,currentUserObj,bizObj);
    }  
    
}
