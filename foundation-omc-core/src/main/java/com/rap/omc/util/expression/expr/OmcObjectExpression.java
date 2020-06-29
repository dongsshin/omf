/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcObjectExpression.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 26.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.oql.utility.OmcSortUtil;
import com.rap.omc.api.util.StrUtil;




/**
 * <pre>
 * Class : OmcObjectExpression
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcObjectExpression extends OmcEquationExpression{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSortUtil.class);
    private BusinessObjectRootVO vo;
    private String attributeName;
    
    public OmcObjectExpression(String originalExpression, BusinessObjectMasterVO currentUserObj,  BusinessObjectRootVO bizObj) {
        super(originalExpression,currentUserObj);
        this.vo = bizObj;
        this.attributeName = this.getEquation().getAttributeName();
    }    
    public BusinessObjectRootVO getVo(){
        return vo;
    }

    public void setVo(BusinessObjectRootVO vo){
        this.vo = vo;
    }

    public String getAttributeName(){
        return attributeName;
    }
    public void setAttributeName(String attributeName){
        this.attributeName = attributeName;
    }
    public void eval(boolean isNot){
        if(this.execute(isNot)) this.setResolvedExpression("TRUE");
    }
    protected boolean execute(boolean isNot){
        String methodString = "get" + StrUtil.subStr(this.getAttributeName(),1, 1).toUpperCase() + StrUtil.subStr(this.getAttributeName(),2);
        try {
            Method method = vo.getClass().getMethod(methodString);
            Object value = method.invoke(vo);
            String strValue = "";
            if (value != null) strValue = value.toString();
            return(OmcExpressionUtil.getResolveEquation(strValue, this.getValueList(), this.getOperator(),this.getCurrentUserObj().getNames()));
        } catch (Exception e) {
            LOGGER.warn("[OmcObjectExpression]OMC Compare Error Occur({}).", e.getMessage());
            return false;
        }
    }
}
