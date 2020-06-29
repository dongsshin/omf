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
public class OmcGroupExpression extends OmcFunctionExpression {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcGroupExpression.class);
    private Set<String> groupList;
    private Set<String> owningGroupList;
    public OmcGroupExpression(String originalExpression,BusinessObjectMasterVO currentUserObj, Set<String> owningGroupList) {
        super(originalExpression,currentUserObj);
        this.setGroupList(OmcExpressionUtil.getListForString(this.getOriginalExpression()));
        this.setOwningGroupList(owningGroupList);
    }

    public Set<String> getGroupList(){
        return groupList;
    }

    
    public void setGroupList(Set<String> groupList){
        this.groupList = groupList;
    }

    
    public Set<String> getOwningGroupList(){
        return owningGroupList;
    }

    
    public void setOwningGroupList(Set<String> owningGroupList){
        this.owningGroupList = owningGroupList;
    }

    public void eval(boolean isNot){
        if(this.execute(isNot)) this.setResolvedExpression("TRUE");
    }
    protected boolean execute(boolean isNot){
        try {
            return(OmcExpressionUtil.getResolveEquation(this.getOwningGroupList(), this.getGroupList(), "==",this.getCurrentUserObj().getNames()));
        } catch (Exception e) {
            LOGGER.warn("[OmcRoleExpression]OMC Compare Error Occur({}).", e.getMessage());
            return false;
        }
    }
}