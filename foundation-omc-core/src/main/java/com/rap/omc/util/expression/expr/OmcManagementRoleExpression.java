/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcExpressionRoleManagement.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 25.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.util.omc.ThreadLocalUtil;


/**
 * <pre>
 * Class : OmcExpressionRoleManagement
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcManagementRoleExpression extends OmcRoleExpression {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcManagementRoleExpression.class);
    
    public OmcManagementRoleExpression(String originalExpression,BusinessObjectMasterVO currentUserObj,Set<String> owningRoleList) {
        super(originalExpression,currentUserObj,owningRoleList);
    }
    @Override
    protected boolean execute(boolean isNot){
        try {
            Set<String> newRoleSet = new HashSet<String>();
            String divisionUit = (String)ThreadLocalUtil.get(ThreadLocalUtil.KEY.divisionUnit);
            for(String role : this.getRoleList()){
                newRoleSet.add(divisionUit + "." + role);
            }
            return(OmcExpressionUtil.getResolveEquation(this.getOwningRoleList(), newRoleSet, "==",this.getCurrentUserObj().getNames()));
        } catch (Exception e) {
            LOGGER.warn("[OmcRoleExpression]OMC Compare Error Occur({}).", e.getMessage());
            return false;
        }
    }
}
