/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcEquation.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 26.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import java.util.ArrayList;
import java.util.List;

import com.rap.omc.api.oql.utility.OmcStringUtility;
import com.rap.omc.api.util.omc.ThreadLocalUtil;



/**
 * <pre>
 * Class : OmcEquation
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcEquation {
    private String methodType;
    private String attributeName;
    private String operator;
    private List<String> valueList;
    
    
    public String getMethodType(){
        return methodType;
    }

    
    public String getAttributeName(){
        return attributeName;
    }

    
    public String getOperator(){
        return operator;
    }

    
    public List<String> getValueList(){
        return valueList;
    }

    
    public void setMethodType(String methodType){
        this.methodType = methodType;
    }

    
    public void setAttributeName(String attributeName){
        this.attributeName = attributeName;
    }

    
    public void setOperator(String operator){
        this.operator = operator;
    }

    
    public void setValueList(List<String> valueList){
        this.valueList = valueList;
    }
    public OmcEquation(String exprStr){
        //userObject[tcUsingFlag].value == {'TRUE'}
        String attributeName = OmcStringUtility.getStringForFromTo(exprStr,1,"[","]");
        this.setAttributeName(attributeName);
        String argumentList = OmcStringUtility.getStringForFromTo(exprStr,1,"{","}");
        argumentList = OmcStringUtility.replaceAll(argumentList, "'", "");
        String[] strArray = argumentList.split(",");
        List<String> argList = new ArrayList<String>();
        for(String str : strArray){
            if(str.equals("CURRENT_USER")) str = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, "");
            argList.add(str);
        }
        this.setValueList(argList);
        this.setOperator((OmcStringUtility.getStringForFromTo(exprStr,1,".value","{")).trim());
        int pos = OmcStringUtility.inStr(exprStr, "[");
        this.setMethodType(OmcStringUtility.subStr(exprStr, 1, pos));
    }
}
