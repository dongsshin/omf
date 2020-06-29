/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcUserPropertyExpression.java
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
 * Class : OmcUserPropertyExpression
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcUserPropertyExpression extends OmcEquationExpression{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSortUtil.class);
    private String  propertyName;
    private Map<String,String>     usrPropertyMap;
    
    public Map<String,String> getUsrPropertyMap(){
        return usrPropertyMap;
    }
    
    public void setUsrPropertyMap(Map<String,String> usrPropertyMap){
        this.usrPropertyMap = usrPropertyMap;
    }
    public OmcUserPropertyExpression(String originalExpression,BusinessObjectMasterVO currentUserObj, Map<String,String> usrPropertyMap) {
        super(originalExpression,currentUserObj);
        this.setPropertyName(this.getEquation().getAttributeName());
        this.setUsrPropertyMap(usrPropertyMap);
    }      
    public String getPropertyName(){
        return propertyName;
    }
    
    public void setPropertyName(String propertyName){
        this.propertyName = propertyName;
    }
    public void eval(boolean isNot){
        if(this.execute(isNot)) this.setResolvedExpression("TRUE");
    }
    protected boolean execute(boolean isNot){
        try {
            String strValue = (String)(this.getUsrPropertyMap().get(propertyName));
            return(OmcExpressionUtil.getResolveEquation(strValue, this.getValueList(), this.getOperator(),this.getCurrentUserObj().getNames()));
        } catch (Exception e) {
            LOGGER.warn("[OmcUserPropertyExpression]OMC Compare Error Occur({}).", e.getMessage());
            return false;
        }
    }
}
