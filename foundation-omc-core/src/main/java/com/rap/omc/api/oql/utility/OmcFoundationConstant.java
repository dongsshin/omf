package com.rap.omc.api.oql.utility;
/**
 * 
 * <pre>
 * Class : OmcFoundationConstant
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcFoundationConstant {

    public static final String OQL_OPERATOR_Equal            = "==";
    public static final String OQL_OPERATOR_NotEqual         = "!=";
    public static final String OQL_OPERATOR_Like             = "~~";
    public static final String OQL_OPERATOR_NotLike          = "!~";
    public static final String OQL_OPERATOR_GreaterThan      = ">>";
    public static final String OQL_OPERATOR_LessTan          = "<<";
    public static final String OQL_OPERATOR_GreaterEqTan     = ">=";
    public static final String OQL_OPERATOR_LessEqTan        = "<=";
    public static final String OQL_OPERATOR_Or               = "||";
    public static final String OQL_OPERATOR_And              = "&&";
    public static final String OQL_OPERATOR_In               = "!!";
    public static final String OQL_OPERATOR_NotIn            = "!-";
    public static final String[] OQL_OPERATOR_ALL_Set     =  new String[] {OQL_OPERATOR_Equal, 
                                                                           OQL_OPERATOR_NotEqual,
                                                                           OQL_OPERATOR_Like,
                                                                           OQL_OPERATOR_NotLike,
                                                                           OQL_OPERATOR_GreaterThan,
                                                                           OQL_OPERATOR_LessTan,
                                                                           OQL_OPERATOR_GreaterEqTan,
                                                                           OQL_OPERATOR_LessEqTan,
                                                                           OQL_OPERATOR_Or,
                                                                           OQL_OPERATOR_And,
                                                                           OQL_OPERATOR_In,
                                                                           OQL_OPERATOR_NotIn };
    public static final String OQL_ALL                        = "*" ;
    public static final String OQL_ALIAS2ATTR_Seperator       = "@" ;
    public static final String OQL_SELECTION_Seperator        = "+" ;
    public static final String OQL_PARAMETER_VALUE_LEFT       = "<%";
    public static final String OQL_PARAMETER_VALUE_RIGHT      = "%>";
        
    public static final String OQL_SUBQUERY_SEPERATOR         = ".";
    public static final String OQL_SUBQUERY_CON_LEFT          = "{";
    public static final String OQL_SUBQUERY_CON_RIGHT         = "}";
    public static final String OQL_SUBQUERY_CLS_LEFT          = "[";
    public static final String OQL_SUBQUERY_CLS_RIGHT         = "]";
    
    public static final String OQL_SELECT_ATTR_LEFT          = "[";
    public static final String OQL_SELECT_ATTR_RIGHT         = "]";
    
    public static final String OQL_SUBQUERY_CLSATTR_LEFT      = "<";
    public static final String OQL_SUBQUERY_CLSATTR_RIGHT     = ">";
    
    public static final String OQL_SUBQUERY_CVT_CHAR          = "^|^";
    
    public static final String OQL_SELECT_CLAUSE_SEPERATOR    = "^::^";
    
    public static final String OQL_TARGET_VO_ALIAS_this       = "this";
    public static final String OQL_TARGET_REL_ALIAS_REL       = "REL";

    public static final String OQL_INNER_SEPERATOR            = "+^+";
    
    public static final String OQL_FISRT_LAST_SQL_SEPERATOR   = "::";
    
    public static final String OQL_DBMS_COLUMN_INFO_SEPERATOR = "|";
    
    public static final String OQL_RECURSIVE_FIRST_STRING      = "0000000000";
    public static final String OQL_DIRECTION_To                = "To";
    public static final String OQL_DIRECTION_From              = "From";
    public static final String OQL_DIRECTION_All               = "*";
    public static final String OQL_ATTRIBUTE_All               = "*";
    public static final String OQL_DIRECTION_self              = "self";
    
    public static final String OQL_MAPPARM_PARSED_SPLITED_FROM = "splitedFromList";
    public static final String OQL_MAPPARM_PARSED_FROM         = "fromList";
    public static final String OQL_MAPPARM_PARSED_QUERY_PATH   = "queryPathList";
    
    public static final String OQL_MAPPARM_SQLTotalCount       = "SQLTotalCount";
    public static final String OQL_MAPPARM_SQLSelectTotal      = "SQLSelectTotal";
    public static final String OQL_MAPPARM_SQLOrderByClause    = "SQLOrderByClause";
    public static final String OQL_MAPPARM_IsReused            = "IsReused";
    public static final String OQL_MAPPARM_VaiableParameter    = "VaiableParameter";
    public static final String OQL_MAPPARM_FindObjectsLog      = "FindObjectsLog";
    
    public static final String OQL_FUNCTION_PARM_PREFIX        = "funVariable_";
                                                               
    public static final String OQL_MAPPARM_CONVERTED_STR       = "ConvertedStr";
    public static final String OQL_MAPPARM_SEQUENCE            = "Sequence";
    public static final String OQL_MAPPARM_PARMVALUE           = "ParameterValue";
    public static final String OQL_MAPPARM_VAR_LIST            = "FuncVariableParmList";
                                                               
    public static final String OQL_MAPPARM_SELECT_STR          = "PatternSelectAttribute";
    public static final String OQL_MAPPARM_SUBQUERY_STR        = "PatternSelectSubQuery";
                                                               
    public static final String OQL_OBID_VARIABLE               = "OMCObidVariable";
    public static final String OQL_RELATED_CLASS_FROM_STR      = "REL.pto_obid    = this.obid and REL.pfrom_obid in (<%" + OQL_OBID_VARIABLE + "%>)";
    public static final String OQL_RELATED_CLASS_TO_STR        = "REL.pfrom_obid  = this.obid and REL.pto_obid   in (<%" + OQL_OBID_VARIABLE + "%>)";

    public static final String OQL_RELATIONSHIP_CLASS_FROM_STR    = "this.pfrom_obid in (<%" + OQL_OBID_VARIABLE + "%>)";
    public static final String OQL_RELATIONSHIP_CLASS_TO_STR      = "this.pto_obid   in (<%" + OQL_OBID_VARIABLE + "%>)";
    
    public static final String OQL_RELATED_JOIN_PARM_FROM    = "@joinClauseFrom";
    public static final String OQL_RELATED_JOIN_PARM_TO      = "@joinClauseTo";
    
    public static final String OQL_RELATED_MAPPARM_PATTERN_REL      = "patternRelationOut";
    public static final String OQL_RELATED_MAPPARM_CLASS_FILTER     = "patternClassFilterOut";
    public static final String OQL_RELATED_MAPPARM_CLASS_LIST       = "selatedClassInfoListOut";
    
    public static final String ATTRIBUTE_DELIMINATOR_VALUE     = "^:^";
    public static final String ATTRIBUTE_DELIMINATOR_NAME      = "^~^";
    
    public static final String OQL_ORDERBY_OrderBy             = "SortBy";
    public static final String OQL_ORDERBY_GroupBy             = "yBtroS";
    
    public static final String OQL_KEY_VALUE_Seperator         = "*^*";
    
    
    public static final String OQL_SELPATTERN_Seperator        = "_";
    
    public static final String newline = System.getProperty("line.separator");
    
    public static final int OQL_DIRECTION_FROM_   = 1;
    public static final int OQL_DIRECTION_TO_     = 2;
    public static final int OQL_DIRECTION_ALL_    = 3;
    
}
