/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcExpressionUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 26.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.rap.omc.api.util.StrUtil;




/**
 * <pre>
 * Class : OmcExpressionUtil
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OmcExpressionUtil {
    public static Set<String> getListForString(String strList){
        String argumentList = StrUtil.getStringForFromTo(strList,1,"{","}");
        argumentList = argumentList.replace("'", "");
        String[] strArray = argumentList.split(",");
        Set<String> argList = new HashSet<String>();
        for(String str : strArray){
            argList.add(str);
        }
        return(argList);
    }
    public static Set<String> getListForPrgramString(String strList){
        String argumentList = StrUtil.getStringForFromTo(strList,1,"{","}");
        argumentList = argumentList.replace("'", "");
        String[] strArray = argumentList.split(";");
        Set<String> argList = new HashSet<String>();
        for(String str : strArray){
            argList.add(str);
        }
        return(argList);
    }
    public static String getProgramNMethodName(String strList){
        String programNMethodName = StrUtil.getStringForFromTo(strList,1,"program[","]");
        return(programNMethodName);
    }
    public static Map getOperatorNValueList(String strList){
        Map rtnMap = new HashMap();
        String argumentList = StrUtil.getStringForFromTo(strList,1,"{","}");
        argumentList = StrUtil.replaceAll(argumentList, "'", "");
        String[] strArray = argumentList.split(",");
        List<String> argList = new ArrayList<String>();
        for(String str : strArray){
            argList.add(str);
        }
        return(rtnMap);
    }
    private static Map getEquationResolver(String strList){
        Map rtnMap = new HashMap();
        String argumentList = StrUtil.getStringForFromTo(strList,1,"{","}");
        argumentList = StrUtil.replaceAll(argumentList, "'", "");
        String[] strArray = argumentList.split(",");
        List<String> argList = new ArrayList<String>();
        for(String str : strArray){
            argList.add(str);
        }
        return(rtnMap);
    }
    static public ArrayList<String> getSplitLogicSentenceMenu(String expression) {
        ArrayList<String> exprArayList = new ArrayList<String>();
        String strexpression = expression;
        int strLen = expression.length();
        int idx = 1;
        String leftStr = "";
        while(idx <= strLen){
            leftStr = "";
            String first2Char = StrUtil.subStr(strexpression, idx, 2);
            if (isOperator(first2Char)){
                if(idx==1){
                    leftStr = StrUtil.subStr(strexpression,1,2);
                    strexpression = StrUtil.subStr(strexpression,3);
                    strLen = strexpression.length();
                    idx    = 1;
                }
                else{
                    leftStr = StrUtil.subStr(strexpression,1,idx-1);
                    strexpression = StrUtil.subStr(strexpression,idx);
                    strLen = strexpression.length();
                    idx    = 1;
                }
            }
            else if (isSmallBracket(first2Char.charAt(0))){
                if(idx==1){
                    leftStr = StrUtil.subStr(strexpression,1,1);
                    strexpression = StrUtil.subStr(strexpression,2);
                    strLen = strexpression.length();
                    idx    = 1;
                }
                else{
                    leftStr = StrUtil.subStr(strexpression,1,idx-1);
                    strexpression = StrUtil.subStr(strexpression,idx);
                    strLen = strexpression.length();
                    idx    = 1;
                }
            }
            else{
                idx++;
            }
            if(!StrUtil.isEmpty(leftStr.trim())){
                exprArayList.add(leftStr);
            }
        }
        if(!StrUtil.isEmpty(strexpression.trim())){
            exprArayList.add(strexpression);
        }
        return exprArayList;
    }
    static public boolean isOperator(String expression) {
        if(expression.equals("&&") || expression.equals("||")) return true;
        return false;
    }
    static public boolean isSmallBracket(String expression) {
        if(expression.equals("(") || expression.equals(")")) return true;
        return false;
    }
    static public boolean isSmallBracket(char expression) {
        if(expression== '(' || expression==')') return true;
        return false;
    }
    static public boolean isRoleSentence(String expression) {
        if (isUserSystemRoleSentence(expression)) return true;
        if (isUserManagementRoleSentence(expression)) return true;
        return false;
    }
    static public boolean isUserSystemRoleSentence(String expression) {
        if(expression.indexOf("currentUser[hasSystemRole]") == -1) return false;
        return true;
    }
    static public boolean isUserManagementRoleSentence(String expression) {
        if(expression.indexOf("currentUser[hasManagementRole]") == -1) return false;
        return true;
    }
    static public boolean isUserPropertySentence(String expression) {
        if(expression.indexOf("userProperty[") == -1) return false;
        return true;
    }
    static public boolean isUserObjectSentence(String expression) {
        if(expression.indexOf("userObject[") == -1) return false;
        return true;
    }
    static public boolean isAccessMethodSentence(String expression) {
        if(expression.indexOf("programMethod[") == -1) return false;
        return true;
    }
    static public boolean isBusinessObjectSentence(String expression) {
        if(expression.indexOf("object[") == -1) return false;
        return true;
    }
    static public boolean getResolveEquation(String strValue, List<String> valueList, String operator, String currentUser){
        boolean returnValue = false;
        if(!StrUtil.isEmpty(strValue)) {
            for(String str : valueList){
                if(strValue.equals(str)) {returnValue = true;break;}
            }
        }else{
            for(String str : valueList){
                if(StrUtil.isEmpty(str)) {returnValue = true;break;}
                if(str.equals("NULL")) {returnValue = true;break;}
            } 
        }
        if(operator.equals("==")) return returnValue;
        if(operator.equals("!=")) return !returnValue;
        return false;
    }
    static public boolean getResolveEquation(String strValue, Set<String> valueList, String operator, String currentUser){
        boolean returnValue = false;
        if(valueList == null) valueList = new HashSet<String>();
        if(!StrUtil.isEmpty(strValue)) {
            for(String str : valueList){
                if(strValue.equals(str)) {returnValue = true;break;}
            }
        }else{
            for(String str : valueList){
                if(StrUtil.isEmpty(str)) {returnValue = true;break;}
                if(str.equals("NULL")) {returnValue = true;break;}
            } 
        }
        if(operator.equals("==")) return returnValue;
        if(operator.equals("!=")) return !returnValue;
        return false;
    }
    static public boolean getResolveEquation(List<String> owningRoleList, List<String> roleList, String operator, String currentUser){
        boolean returnValue = false;
        if(owningRoleList == null) owningRoleList = new ArrayList<String>();
        if(roleList == null) roleList = new ArrayList<String>();
        for(String owningRole : owningRoleList){
            for(String role : roleList){
                if(owningRole.equals(role)) {returnValue = true; break;}
            }
            if( returnValue ) break;
        }
        if(operator.equals("==")) return returnValue;
        if(operator.equals("!=")) return !returnValue;
        return false;
    }
    static public boolean getResolveEquation(Set<String> owningRoleList, Set<String> roleList, String operator, String currentUser){
        boolean returnValue = false;
        if(owningRoleList == null) owningRoleList = new HashSet<String>();
        if(roleList == null) roleList = new HashSet<String>();
        for(String owningRole : owningRoleList){
            for(String role : roleList){
                if(owningRole.equals(role)) {returnValue = true; break;}
            }
            if( returnValue ) break;
        }
        if(operator.equals("==")) return returnValue;
        if(operator.equals("!=")) return !returnValue;
        return false;
    }
}