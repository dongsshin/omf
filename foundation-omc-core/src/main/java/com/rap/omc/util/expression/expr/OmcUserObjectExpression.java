/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcUserObjectExpression.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 26.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;


/**
 * <pre>
 * Class : OmcUserObjectExpression
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcUserObjectExpression extends OmcObjectExpression {
    public OmcUserObjectExpression(String originalExpression,BusinessObjectMasterVO currentUserObj) {
        super(originalExpression,currentUserObj,currentUserObj);
    }
}
