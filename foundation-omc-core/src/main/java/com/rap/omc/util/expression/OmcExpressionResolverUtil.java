/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : OmcExpressionResolverUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 6. 15.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.foundation.menu.AccessConstants;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.expression.expr.OmcBizObjectExpression;
import com.rap.omc.util.expression.expr.OmcContextExpression;
import com.rap.omc.util.expression.expr.OmcContextObjectExpression;
import com.rap.omc.util.expression.expr.OmcExpression;
import com.rap.omc.util.expression.expr.OmcExpressionConstants;
import com.rap.omc.util.expression.expr.OmcExpressionEvaluator;
import com.rap.omc.util.expression.expr.OmcExpressionUtil;
import com.rap.omc.util.expression.expr.OmcManagementRoleExpression;
import com.rap.omc.util.expression.expr.OmcSystemGroupExpression;
import com.rap.omc.util.expression.expr.OmcSystemRoleExpression;
import com.rap.omc.util.expression.expr.OmcUserDefinedFunctionExpression;
import com.rap.omc.util.expression.expr.OmcUserObjectExpression;
import com.rap.omc.util.expression.expr.OmcUserPropertyExpression;

/**
 * <pre>
 * Class : OmcExpressionResolverUtil
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcExpressionResolverUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(OmcExpressionResolverUtil.class);
    @SuppressWarnings({ "unchecked"})
    public static boolean resolveAccessExpression(UserSession userSession, String expressionStr, BusinessObjectMasterVO currentUserObj, BusinessObjectRootVO bizObj, BusinessObjectRootVO contextObj, @SuppressWarnings("rawtypes") Map contextMap, String menuName,Map<String,Boolean> resolvedMap){
        
        String keySuffix = "null";
        if(!NullUtil.isNull(bizObj)) keySuffix = bizObj.getObid();
        
        if(StrUtil.isEmpty(expressionStr)) return true;
        expressionStr = expressionStr.trim();
        if(StrUtil.isEmpty(expressionStr)) return true;
        Boolean previousResolved = resolvedMap.get(keySuffix + expressionStr);
        if(previousResolved != null ){
            return previousResolved;
        }
        ArrayList<String> exprList     = getExpressionList(expressionStr);
        String rlstExpression = expressionStr;
        
        Map<String,String> propertyMap = (Map<String,String>)contextMap.get(AccessConstants.CONTEXT_USER_PROPERTY);
        Set<String> roleSet            = (Set<String>)contextMap.get(AccessConstants.CONTEXT_USER_ROLE_LIST);
        Set<String> groupSet           = (Set<String>)contextMap.get(AccessConstants.CONTEXT_USER_GROUP_LIST);
        Set<String> mgetRoleSet        = (Set<String>)contextMap.get(AccessConstants.CONTEXT_USER_MANAGEMENT_ROLE_LIST);
        LOGGER.debug("Expression                      :{}", expressionStr);
        LOGGER.debug("propertyMap                     :{}", propertyMap);
        LOGGER.debug("roleSet                         :{}", roleSet);
        LOGGER.debug("groupSet                        :{}", groupSet);
        LOGGER.debug("mgetRoleSet                     :{}", mgetRoleSet);
        boolean isEvaluated = false;
        for(String exprStr : exprList){
            exprStr = exprStr.trim();
            String keyValue = keySuffix + exprStr;
            String or = exprStr;
            Boolean resolvedEach = resolvedMap.get(keyValue);
            if(resolvedEach == null ){
                LOGGER.debug("Splited Not Resolved Expression :{}", exprStr);
                boolean isNot = false;
                if(!StrUtil.isEmpty(exprStr) && exprStr.substring(0,1).equals("!")) {
                    isNot = true;
                    exprStr = exprStr.substring(1);
                }
                OmcExpression expression = null;
                if(StrUtil.inStr(exprStr, OmcExpressionConstants.OMC_EXPR_BUSINESS_OBJECT) > 0){
                    expression = new OmcBizObjectExpression(exprStr,currentUserObj,bizObj);
                }else if(StrUtil.inStr(exprStr, OmcExpressionConstants.OMC_EXPR_USER_PROPERTY) > 0){
                    expression = new OmcUserPropertyExpression(exprStr,currentUserObj,propertyMap);
                }else if(StrUtil.inStr(exprStr, OmcExpressionConstants.OMC_EXPR_USER_OBJECT) > 0){
                    expression = new OmcUserObjectExpression(exprStr,currentUserObj);
                }else if(StrUtil.inStr(exprStr, OmcExpressionConstants.OMC_EXPR_MANAGEMENT_ROLE) > 0){
                    expression = new OmcManagementRoleExpression(exprStr,currentUserObj,mgetRoleSet);
                }else if(StrUtil.inStr(exprStr, OmcExpressionConstants.OMC_EXPR_SYSTEM_ROLE) > 0){
                    expression = new OmcSystemRoleExpression(exprStr,currentUserObj,roleSet);
                }else if(StrUtil.inStr(exprStr, OmcExpressionConstants.OMC_EXPR_SYSTEM_GROUP) > 0){
                    expression = new OmcSystemGroupExpression(exprStr,currentUserObj,groupSet);
                }else if(StrUtil.inStr(exprStr, OmcExpressionConstants.OMC_EXPR_CONTEXT_NAME) > 0){
                    expression = new OmcContextExpression(exprStr,currentUserObj,contextMap);
                }else if(StrUtil.inStr(exprStr, OmcExpressionConstants.OMC_EXPR_CONTEXT_OBJECT) > 0){
                    expression = new OmcContextObjectExpression(exprStr,currentUserObj,contextObj);
                }else if(StrUtil.inStr(exprStr, OmcExpressionConstants.OMC_EXPR_USER_DEFINED_FUNCTION) > 0){
                    expression = new OmcUserDefinedFunctionExpression(exprStr,currentUserObj,bizObj,contextObj,contextMap, menuName);
                }
                if (expression != null){
                    isEvaluated =true;
                    expression.eval(isNot);
                    if(expression.getResolvedExpression().equals("TRUE")){
                        resolvedMap.put(keyValue, true);
                    }else{
                        resolvedMap.put(keyValue, false);
                    }
                    rlstExpression = rlstExpression.replace(or, expression.getResolvedExpression());
                    LOGGER.debug("Splited Resolved Expression     :{}", expression.getResolvedExpression());
                }
            }else{
                isEvaluated =true;
                if(resolvedEach){
                    rlstExpression = rlstExpression.replace(or, "TRUE");
                }else{
                    rlstExpression = rlstExpression.replace(or, "FALSE");
                }
            }
        }
        if(isEvaluated){
            rlstExpression = rlstExpression.replace(" ", "");
            LOGGER.debug("rlstExpression                  :{}", rlstExpression);
            OmcExpressionEvaluator evaluator = new OmcExpressionEvaluator(rlstExpression);
            try{
                BigDecimal rtn = evaluator.eval();
                LOGGER.debug("BigDecimal rtn                  :{}", rtn);
                if(rtn.intValue() == 1) {
                    resolvedMap.put(keySuffix + expressionStr, true);
                    return true;
                }
            }catch(Exception e){
                LOGGER.error("e.getMessage()                  :{}", e.getMessage());
                resolvedMap.put(keySuffix + expressionStr, false);
                return false;
                //throw new FoundationException("omc.error.getexpression.OmcUserDefinedFunctionExpression", new Object[] {rlstExpression},e);
            }
        }
        resolvedMap.put(keySuffix + expressionStr, false);
        return false;
    }
    private static ArrayList<String> getExpressionList(String expressionStr){
        ArrayList<String> extprList        = new ArrayList<String>();
        ArrayList<String> exprArray       = OmcExpressionUtil.getSplitLogicSentenceMenu(expressionStr);
        for(String str : exprArray){
            if ((!OmcExpressionUtil.isSmallBracket(str) && !OmcExpressionUtil.isOperator(str))) extprList.add(str);
        }
        return(extprList);
    }
}
