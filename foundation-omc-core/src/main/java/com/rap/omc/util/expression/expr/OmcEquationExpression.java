/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcEquationExpression.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 26.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import java.util.List;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;


/**
 * <pre>
 * Class : OmcEquationExpression
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public abstract class OmcEquationExpression extends OmcExpression{
    private String       operator;
    private List<String> valueList;
    private OmcEquation  equation;
    
    public OmcEquationExpression(String originalExpression,BusinessObjectMasterVO currentUserObj) {
        super(originalExpression,currentUserObj);
        this.setEquation(new OmcEquation(this.getOriginalExpression()));
        this.setOperator(this.getEquation().getOperator());
        this.setValueList(this.getEquation().getValueList());
    }

    public String getOperator(){
        return operator;
    }

    public List<String> getValueList(){
        return valueList;
    }
    
    private void setOperator(String operator){
        this.operator = operator;
    }
    
    private void setValueList(List<String> valueList){
        this.valueList = valueList;
    }

    public OmcEquation getEquation(){
        return equation;
    }

    private void setEquation(OmcEquation equation){
        this.equation = equation;
    }
    
}
