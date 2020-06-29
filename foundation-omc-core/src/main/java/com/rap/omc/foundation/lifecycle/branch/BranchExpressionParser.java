/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BranchExpressionParser.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 4. 7. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.branch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.framework.exception.BranchException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.util.NullUtil;


/**
 * <pre>
 * Class : BranchExpressionParser
 * Description : Branch 정보 별, Condition_Expression을 parser를 통해 수행하여 true/false를 리턴함
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class BranchExpressionParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(BranchExpressionParser.class);

    private static final String[] operators = { "!=", "==", "||", "&&" };

    public static boolean checkExpression(BusinessObjectRootVO vo, String expression){
        expression = expression.replace("'", "");
        return parseWithStrings(expression, vo);
    }

    private static boolean parseWithStrings(String s, BusinessObjectRootVO vo){
        int[] op = determineOperatorPrecedenceAndLocation(s);
        if (NullUtil.isNull(op)) {
            return Boolean.parseBoolean(s);
        } else {
            int start = op[0];
            String left = s.substring(0, start).trim();
            String right = s.substring(op[1]).trim();
            String oper = s.substring(start, op[1]).trim();
            int logType = logicalOperatorType(oper);
            // LOGGER.debug("PARSE: Left: \"" + left + "\" Right: \"" + right + "\" Operator: \"" + oper + "\"");
            if (logType == 0){ // encounters OR- recurse
                return parseWithStrings(left, vo) || parseWithStrings(right, vo);
            }else if (logType == 1){ // encounters AND- recurse
                return parseWithStrings(left, vo) && parseWithStrings(right, vo);
            }
            String leftSansParen = removeParens(left);
            String rightSansParen = removeParens(right);
            return evaluate(leftSansParen, oper, rightSansParen, vo); // assume they are strings
        }
    }

    private static int[] determineOperatorPrecedenceAndLocation(String s){
        s = s.trim();
        int minParens = Integer.MAX_VALUE;
        int[] currentMin = null;
        for (int sampSize = 1; sampSize <= 2; sampSize++) {
            for (int locInStr = 0; locInStr < (s.length() + 1) - sampSize; locInStr++) {
                int endIndex = locInStr + sampSize;
                String sub;
                if ((endIndex < s.length()) && s.charAt(endIndex) == '='){
                    sub = s.substring(locInStr, ++endIndex).trim();
                }else{
                    sub = s.substring(locInStr, endIndex).trim();
                }
                if (isOperator(sub)) {
                    // Idea here is to weight logical operators so that they will still be selected over other operators
                    // when no parens are present
                    int parens = (logicalOperatorType(sub) > -1) ? parens(s, locInStr) - 1 : parens(s, locInStr);
                    if (parens <= minParens) {
                        minParens = parens;
                        currentMin = new int[] { locInStr, endIndex, parens };
                    }
                }
            }
        }
        return currentMin;
    }

    private static int logicalOperatorType(String op){
        switch (op.trim()) {
            case "||":
                return 0;
            case "&&":
                return 1;
            default:
                return -1;
        }
    }

    private static int parens(String s, int loc){
        int parens = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' && i < loc){
                parens++;
            }
            if (s.charAt(i) == ')' && i >= loc){
                parens++;
            }
        }
        return parens;
    }

    private static String removeParens(String s){
        s = s.trim();
        StringBuilder keep = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!(c == '(') && !(c == ')')) {
                keep.append(c);
            }
        }
        return keep.toString().trim();
    }

    private static boolean isOperator(String op){
        op = op.trim();
        for (String s : operators) {
            if (s.equals(op)){
                return true;
            }
        }
        return false;
    }

    private static boolean evaluate(String left, String op, String right, BusinessObjectRootVO vo){
        if (left.contains("attribute")) {
            left = processAttribute(left, vo);
        } else if (left.contains("program")) {
            left = processProgram(left, vo);
        }

        if (right.contains("attribute")) {
            right = processAttribute(right, vo);
        } else if (right.contains("program")) {
            right = processProgram(right, vo);
        }

        switch (op) {
            case "==":
                return left.equals(right);
            case "!=":
                return !left.equals(right);
            default:
                LOGGER.debug("ERROR: Operator type not recognized.");
                return false;
        }
    }

    private static String processAttribute(String attribute, BusinessObjectRootVO vo){
        String attrName = attribute.substring(attribute.indexOf('[') + 1, attribute.indexOf(']'));
        String attrValue = getAttributeValue(attrName, vo);
        return attrValue;
    }

    private static String processProgram(String program, BusinessObjectRootVO vo){
        String classNames = program.substring(program.indexOf('[') + 1, program.indexOf("-method")).trim();
        String method = program.substring(program.indexOf("-method "), program.indexOf('$')).replace("-method ", "")
                .trim();

        Object runClass = getRunClass(classNames);
        boolean isResult = callMethod(runClass, method, vo);
        return Boolean.toString(isResult);
    }

    private static Object getRunClass(String classNames){
        Object runClass = null;
        try {
            Class<?> cls = Class.forName(classNames);
            if (classNames.contains("Service")) {
                runClass = SpringFactoryUtil.getBean(cls);
            } else {
                runClass = cls.newInstance();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            throw new FoundationException("omc.error.workflow.bean.created", new Object[] { classNames }, e);
        }
        return runClass;
    }

    private static boolean callMethod(Object runClass, String methodName, BusinessObjectRootVO vo){
        boolean isResult = false;
        Object returnValue = null;
        try {
            Method method = runClass.getClass().getDeclaredMethod(methodName, String.class);
            if (!NullUtil.isNull(vo)) {
                returnValue = method.invoke(runClass, vo.getObid());
            } else {
                returnValue = method.invoke(runClass, "");
            }
            isResult = (boolean)returnValue;
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException e) {
            LOGGER.error(e.getMessage());
            throw new FoundationException("omc.error.workflow.method.called", new Object[] { methodName },e);
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            throw new FoundationException("omc.error.workflow.method.return", new Object[] { returnValue },e);
        } catch (InvocationTargetException e) {
            LOGGER.error(e.getMessage());
            if (e.getTargetException() instanceof BranchException) {
                BranchException branchException = (BranchException)e.getTargetException();
                throw new FoundationException(branchException.getCode(), branchException.getMessageParameters(),e);
            }
            throw new FoundationException("omc.error.workflow.method.called", new Object[] { methodName },e);
        }
        return isResult;
    }

    private static String getAttributeValue(String attributeName, BusinessObjectRootVO vo){
        String attributeValue = null;
        Object returnValue = null;
        try {
            if (!NullUtil.isNull(vo)) {
                String getMethodName = "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
                Method method = vo.getClass().getMethod(getMethodName);
                returnValue = method.invoke(vo);
                attributeValue = (String)returnValue;

                if (NullUtil.isNone(attributeValue)) { throw new FoundationException("omc.error.workflow.attribute.notFound",
                        new Object[] { attributeName }); }
            } else {
                attributeValue = attributeName;
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            LOGGER.error(e.getMessage());
            throw new FoundationException("omc.error.workflow.method.called", new Object[] { attributeName },e);
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            throw new FoundationException("omc.error.workflow.method.return", new Object[] { returnValue },e);
        }
        return attributeValue;
    }
}
