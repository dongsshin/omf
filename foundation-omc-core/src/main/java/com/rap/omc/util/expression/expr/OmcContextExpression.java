/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcContextExpression.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 26.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.oql.utility.OmcSortUtil;

/**
 * <pre>
 * Class : OmcContextExpression
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({ "unused", "rawtypes" })
public class OmcContextExpression extends OmcEquationExpression{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSortUtil.class);
    private String contextName;
    
    private Map contextMap;
    
    public OmcContextExpression(String originalExpression,BusinessObjectMasterVO currentUserObj,Map contextMap) {
        super(originalExpression,currentUserObj);
        this.contextName = this.getEquation().getAttributeName();
        this.setContextMap(contextMap);
    }  
    
    
    public String getContextName(){
        return contextName;
    }
    
    public Map getContextMap(){
        return contextMap;
    }

    public void setContextMap(Map contextMap){
        this.contextMap = contextMap;
    }

    public void setContextName(String contextName){
        this.contextName = contextName;
    }
    public void eval(boolean isNot){
        if(this.execute(isNot)) this.setResolvedExpression("TRUE");
    }
    protected boolean execute(boolean isNot){
        try {
            String strValue = (String)(this.getContextMap().get(contextName));
            return(OmcExpressionUtil.getResolveEquation(strValue, this.getValueList(), this.getOperator(),this.getCurrentUserObj().getNames()));
        } catch (Exception e) {
            LOGGER.warn("[OmcContextExpression]OMC Compare Error Occur({}).", e.getMessage());
            return false;
        }
    }
}
