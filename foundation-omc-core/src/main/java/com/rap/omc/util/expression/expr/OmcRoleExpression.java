/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcExpressionRole.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 25.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;




/**
 * <pre>
 * Class : OmcExpressionRole
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcRoleExpression extends OmcFunctionExpression {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcRoleExpression.class);
    private Set<String> roleList;
    private Set<String> owningRoleList;
    public OmcRoleExpression(String originalExpression,BusinessObjectMasterVO currentUserObj, Set<String> owningRoleList) {
        super(originalExpression,currentUserObj);
        this.setRoleList(OmcExpressionUtil.getListForString(this.getOriginalExpression()));
        this.setOwningRoleList(owningRoleList);
    }
    public Set<String> getRoleList(){
        return roleList;
    }
    private void setRoleList(Set<String> roleList){
        this.roleList = roleList;
    }
    public Set<String> getOwningRoleList(){
        return owningRoleList;
    }
    public void setOwningRoleList(Set<String> owningRoleList){
        this.owningRoleList = owningRoleList;
    }
    public void eval(boolean isNot){
        if(this.execute(isNot)) this.setResolvedExpression("TRUE");
    }
    protected boolean execute(boolean isNot){
        try {
            return(OmcExpressionUtil.getResolveEquation(this.getOwningRoleList(), this.getRoleList(), "==",this.getCurrentUserObj().getNames()));
        } catch (Exception e) {
            LOGGER.warn("[OmcRoleExpression]OMC Compare Error Occur({}).", e.getMessage());
            return false;
        }
    }
}