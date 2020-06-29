/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcExpressionConstants.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 31.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;


/**
 * <pre>
 * Class : OmcExpressionConstants
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcExpressionConstants {
    public static final String OMC_EXPR_MANAGEMENT_ROLE       = "currentUser[hasManagementRole]";
    public static final String OMC_EXPR_SYSTEM_ROLE           = "currentUser[hasSystemRole]";
    public static final String OMC_EXPR_SYSTEM_GROUP          = "currentUser[hasSystemGroup]";
    public static final String OMC_EXPR_USER_OBJECT           = "userObject[";
    public static final String OMC_EXPR_USER_DEFINED_FUNCTION = "program[";
    public static final String OMC_EXPR_BUSINESS_OBJECT       = "object[";
    public static final String OMC_EXPR_CONTEXT_OBJECT        = "contextObject[";
    public static final String OMC_EXPR_USER_PROPERTY         = "userProperty[";
    public static final String OMC_EXPR_CONTEXT_NAME          = "context[";
}
