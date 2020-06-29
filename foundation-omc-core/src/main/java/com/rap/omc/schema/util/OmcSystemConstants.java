/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSystemConstants.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 17.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.util;

/**
 * <pre>
 * Class : OmcSystemConstants
 * Description : OMC에서 사용되어지는 시스템 Constants를 정의
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSystemConstants {
    /*
select case when a.phex_converting_flag = 'C' then rpad('public static final String ' || a.pnames,60,' ') || ' = "' || a.pconstant_values || '";'
            when a.phex_converting_flag = 'Y' then rpad('public static final long '   || a.pnames,60,' ') || ' = Bit.h2d("' || a.pconstant_values || '");'
            when a.phex_converting_flag = 'N' then rpad('public static final int '    || a.pnames,60,' ') || ' = ' || (a.pconstant_values + 0) || ';'
       end 
from psysconstants a
where a.pkinds = 'System'
order by a.psequences
*/
    public static final int OMC_DBMS_UNLIIMITED_COUNT  = 500000;
    public static final int NO_ROW_LIMIT               = Integer.MAX_VALUE-1;
    public static final int NO_ROW_OFFSET              = 0;
    public static final String ATTRIBUTE_DELIMINATOR_VALUE       = "^:^";
    public static final String ATTRIBUTE_DELIMINATOR_NAME        = "^~^";
    public static final String CONDITION_OR_DELIMINATOR          = "^|^";
    public static final String DELIMINATOR_VALUE_FOR_JAVA        = ":■:";
    public static final String DELIMINATOR_NAME_FOR_JAVA         = ":□:";
    public static final String OMC_DBMS_DATE_FORMAT              = "%Y-%m-%d %H:%i:%s";
    
    
    public static final String DELIMINATOR_VALUE_GENERAL         = "^:^";
    
    
    public static final String OMC_JAVA_DATE_FORMAT_YMD          = "yyyy-MM-dd";
    public static final String OMC_JAVA_DATE_FORMAT_YMD_SIMPLE   = "yyyyMMdd";
    public static final String OMC_JAVA_DATE_FORMAT_HOUR         = "HH:mm:ss";
    public static final String OMC_JAVA_DATE_FORMAT_DATE         = OMC_JAVA_DATE_FORMAT_YMD + " " + OMC_JAVA_DATE_FORMAT_HOUR;
    public static final String OMC_JAVA_DATE_LOG                 = "yyyy/MM/dd-HH:mm:ss.SSS";
    public static final String OMC_JAVA_DATE_FORMAT_SIMPLE       = "yyyyMMddHHmmss";
    public static final String OMC_JAVA_DATE_FORMAT_MAIL         = "yyyy-MM-dd HH:mm:ss";
    public static final String OMC_JAVA_DATE_FORMAT_GANTT         = "dd-MM-yyyy HH:mm";
    
    public static final long DBMS_TYPE_ORACLE                    = Bit.h2d("000000000020");
    public static final long DBMS_TYPE_MSSQL                     = Bit.h2d("000000000040");
    public static final long DBMS_TYPE_DB2                       = Bit.h2d("000000000080");
    public static final long DBMS_TYPE_MYSQL                     = Bit.h2d("000000000200");
    public static final long DBMS_TYPE_MARIA                     = Bit.h2d("000000000400");
    public static final long DBMS_CURRENT                        = DBMS_TYPE_MARIA;
    public static final String DBMS_SYSDATE_ORACLE               = "pg_tz.uf_get_utc()";
    public static final String DBMS_SYSDATE_MSSQL                = "pg_tz.uf_get_utc()";
    public static final String DBMS_SYSDATE_DB2                  = "pg_tz.uf_get_utc()";
    public static final String DBMS_SYSDATE_MYSQL                = "UTC_TIMESTAMP()";
    public static final String DBMS_SYSDATE_MARIA                = "UTC_TIMESTAMP()";
    public static final String OMC_DATE_CONVERT_PREFIX           = "TO_DATE:";

    public static final int DBMS_IN_MAX_COUNT_ORACLE             = 999;
    public static final int DBMS_IN_MAX_COUNT_MSSQL              = 999;
    public static final int DBMS_IN_MAX_COUNT_DB2                = 999;
    public static final int DBMS_IN_MAX_COUNT_MYSQL              = 999;
    public static final int DBMS_IN_MAX_COUNT_MARIA              = 999;
    
    public static final String OBJECT_ROOT                       = "ObjectRoot";
    public static final String BUSINESS_OBJECT_ROOT              = "BusinessObjectRoot";
    public static final String BUSINESS_OBJECT                   = "BusinessObject";
    public static final String BUSINESS_OBJECT_MASTER            = "BusinessObjectMaster";
    public static final String RELATION_OBJECT_ROOT              = "BusinessRelationObject";
    public static final String BUSINESS_FILES                    = "Files";
    public static final String BUSINESS_STRUCTURED_OBJECT        = "BusinessStructuredObject";
    public static final String BUSINESS_STRUCTURED_OBJECT_MASTER = "BusinessStructuredObjectMaster";
    
    public static final String[] FOUNDATION_CLASS_LIST = {OBJECT_ROOT,BUSINESS_OBJECT_ROOT,BUSINESS_OBJECT,BUSINESS_OBJECT_MASTER,RELATION_OBJECT_ROOT,BUSINESS_FILES,BUSINESS_STRUCTURED_OBJECT,BUSINESS_STRUCTURED_OBJECT_MASTER};
    
    public static final String OBJECT_ROOT_DUMMY_OBID            = "00000000000000000000";
    
    public static final int PARAMETER_LENGTH       = 20;
    public static final String PARAMETER_PREFIX    = "PaRm";
    public static final String PARAMETER_SUFFIX    = "pArM";
    public static final int PARAMETER_TOTAL_LENGTH = PARAMETER_LENGTH + PARAMETER_PREFIX.length() + PARAMETER_SUFFIX.length();
    public static final String PARAMETER_SPECIAL_STR = "zR$a$R$az";
    public static final char[] PARAMETER_CHARS     = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();   
    
    public static final int OBJECT_SET_CMD_UNIT     = 999;
    public static final int OMC_OBJECT_SQL_DML_UNIT     = 500;

    public static final String OMC_API_CACHE     = "apiQueryCache";  
    public static final String OMC_API_dataFindingDirection      = "dataFindingDirection";
    public static final String OMC_API_dataFindingDirection_mth  = "setDataFindingDirection";
    
    public static final String OMC_API_CLASS_DISPLYEDKR_MAP   = "this_displayedClassNameKr";
    public static final String OMC_API_CLASS_DISPLYED_MAP     = "this_displayedClassName";
    public static final String OMC_API_OUTDATA_NAME            = "outData";
    public static final String OMC_API_OUTDATA_MTH            = "setOutData";
    public static final String OMC_API_MYBATIS_DATE_FORMAT    = "yyyyMMddHHmmss";
    public static final String OMC_SORT_UTIL_ATTR_SUFFIX      = "out.";
    
    public static final String OMC_API_MYBATIS_CLASS_NAME_MAP   = "CLASS_NAME";
    public static final String OMC_API_MYBATIS_QUERY_GROUPBY       = "OMC_GROUPBY_SELECT";
    public static final String OMC_API_MYBATIS_QUERY_GROUPBY_VALUE = "omcGroupByValue";
    public static final String OMC_API_MYBATIS_QUERY_GROUPBY_COUNT = "omcGroupByCount";
    
    public static final String QUERY_GROUPBY_omcGroupByBasisOnPrefix = "omcGroupByBasisOn";
    public static final String QUERY_GROUPBY_omcGroupByMaxPrefix = "omcGroupByMax";
    public static final String QUERY_GROUPBY_omcGroupByMinPrefix = "omcGroupByMin";
    public static final String QUERY_GROUPBY_omcGroupBySumPrefix = "omcGroupBySum";
    public static final String QUERY_GROUPBY_omcGroupByAvgPrefix = "omcGroupByAvg";
    
    public static final String OMC_LOCALE_LANG_EN = "en";
    public static final String OMC_LOCALE_LANG_KO = "ko";
    public static final String OMC_LOCALE_LANG_CH = "ch";
    public static final String OMC_LOCALE_LANG_DEFAULT = OMC_LOCALE_LANG_EN;
    
    public static final String BIZCLASS_FILES                    = "Files";
    
    public static final int SCHEMA_DATA_TYPE_STRING              = 1;
    public static final int SCHEMA_DATA_TYPE_INTEGER             = 2;
    public static final int SCHEMA_DATA_TYPE_FLOAT               = 3;
    public static final int SCHEMA_DATA_TYPE_DATE                = 4;
    public static final int SCHEMA_DATA_TYPE_BOOLEAN             = 5;
    public static final int SCHEMA_DATA_TYPE_LONG                = 6;
    public static final int SCHEMA_DATA_TYPE_DOUBLE              = 7;
    public static final int SCHEMA_DATA_TYPE_BIGDECIMAL          = 8;
    public static final int SCHEMA_DATA_TYPE_ATTRIBUTESET        = 9;//지원하지 않음
    public static final int SCHEMA_DATA_TYPE_LONGSTRING          = 10;
    public static final int SCHEMA_DATA_TYPE_ARRAY               = 11;//지원하지 않음
    public static final int SCHEMA_DATA_TYPE_FILE                = 12;//지원하지 않음
    public static final int SCHEMA_DATA_TYPE_USERID              = 13;
    public static final int[] SCHEMA_DATA_TYPE_VALID_SET         = {SCHEMA_DATA_TYPE_STRING,
                                                                    SCHEMA_DATA_TYPE_INTEGER,
                                                                    SCHEMA_DATA_TYPE_FLOAT,
                                                                    SCHEMA_DATA_TYPE_DATE,
                                                                    SCHEMA_DATA_TYPE_BOOLEAN,
                                                                    SCHEMA_DATA_TYPE_LONG,
                                                                    SCHEMA_DATA_TYPE_DOUBLE,
                                                                    SCHEMA_DATA_TYPE_BIGDECIMAL,
                                                                    SCHEMA_DATA_TYPE_ATTRIBUTESET,
                                                                    SCHEMA_DATA_TYPE_LONGSTRING,
                                                                    SCHEMA_DATA_TYPE_ARRAY,
                                                                    SCHEMA_DATA_TYPE_FILE,
                                                                    SCHEMA_DATA_TYPE_USERID};
    public static final int SCHEMA_FLOATING_POINT_SCALE          = 5;//지원하지 않음
    
    //Exception Biz class
//    public static final String BIZCLASS_FILES                  = PropertyUtil.getProperty("BIZCLASS_FILES");
//    public static final String RELCLASS_WORKFLOWOBJECTROUTE    = PropertyUtil.getProperty("RELCLASS_WORKFLOWOBJECTROUTE");
//    public static final String BIZCLASS_WORKFLOWROUTE          = PropertyUtil.getProperty("BIZCLASS_WORKFLOWROUTE");
    
    public static final long SYSLAYOUT_FLAG_Default              = Bit.h2d("010000000000");
    public static final long SYSLAYOUT_FLAG_Active               = Bit.h2d("000000000001");
    public static final long SYSTAB_FLAG_Default                 = Bit.h2d("020000000000");
    public static final long SYSTAB_FLAG_Active                  = Bit.h2d("000000000001");
    public static final long SYSDATTRGROUP_FLAG_Default          = Bit.h2d("000080000000");
    public static final long SYSDATTRGROUP_FLAG_Active           = Bit.h2d("000000000001");
    public static final long SYSDATTRIBUTE_FLAG_Default          = Bit.h2d("000040000000");
    public static final long SYSDATTRIBUTE_FLAG_Active           = Bit.h2d("000000000001");
    public static final long SYSFILELOCATION_FLAG_Default        = Bit.h2d("000020000000");
    public static final long SYSFILELOCATION_FLAG_Active         = Bit.h2d("000000000001");
    public static final long SYSFILESTORE_FLAG_Default           = Bit.h2d("000010000000");
    public static final long SYSFILESTORE_FLAG_Active            = Bit.h2d("000000000001");
    public static final long SYSLCYCLE_FLAG_Default              = Bit.h2d("000010000000");
    public static final long SYSLCYCLE_FLAG_Active               = Bit.h2d("000000000001");
    public static final long SYSLCYCLE_FLAG_AllStateAcc          = Bit.h2d("000000000010");
    
    public static final long SYSSTATE_FLAG_Default               = Bit.h2d("000020000000");
    public static final long SYSSTATE_FLAG_Active                = Bit.h2d("000000000001");
    public static final long SYSSTATE_FLAG_UserInput             = Bit.h2d("000000000002");
    public static final long SYSSTATE_FLAG_UserInputMust         = Bit.h2d("000000000004");
    public static final long SYSSTATE_FLAG_RCA_Promote           = Bit.h2d("000000000008");
    public static final long SYSSTATE_FLAG_RCA_RONotify          = Bit.h2d("000000000010");
    public static final long SYSSTATE_FLAG_RCA_OONotify          = Bit.h2d("000000000020");
    public static final long SYSSTATE_FLAG_RCA_None              = Bit.h2d("000000000040");
    public static final long SYSSTATE_FLAG_DRP_Standard          = Bit.h2d("000000000080");
    public static final long SYSSTATE_FLAG_DRP_Dist              = Bit.h2d("000000000100");
    public static final long SYSSTATE_FLAG_DRP_Approval          = Bit.h2d("000000000200");
    public static final long SYSSTATE_FLAG_DRP_Review            = Bit.h2d("000000000400");
    public static final long SYSSTATE_FLAG_DRP_Confirm           = Bit.h2d("000000080000");
    public static final long SYSSTATE_FLAG_RAS_True              = Bit.h2d("000000000800");
    public static final long SYSSTATE_FLAG_RAS_False             = Bit.h2d("000000001000");
    public static final long SYSSTATE_FLAG_AS_Immediate          = Bit.h2d("000000002000");
    public static final long SYSSTATE_FLAG_AS_Defererred         = Bit.h2d("000000004000");
    public static final long SYSSTATE_FLAG_RAC_True              = Bit.h2d("000000008000");
    public static final long SYSSTATE_FLAG_RAC_False             = Bit.h2d("000000010000");
    public static final long SYSSTATE_FLAG_PPR_All               = Bit.h2d("000000020000");
    public static final long SYSSTATE_FLAG_PPR_Any               = Bit.h2d("000000040000");
    public static final long SYSSTATE_FLAG_STATE_Defined         = Bit.h2d("000000100000");
    
    public static final long SYSBRANCH_FLAG_Default              = Bit.h2d("000040000000");
    public static final long SYSBRANCH_FLAG_Active               = Bit.h2d("000000000001");
    public static final long SYSBRANCH_KIND_Default              = Bit.h2d("000040000000");
    public static final long SYSBRANCH_KIND_User                 = Bit.h2d("000001000000");
    public static final long SYSBRANCH_KIND_State                = Bit.h2d("000002000000");
    public static final long SYSBRANCH_KIND_Condition            = Bit.h2d("000004000000");
    public static final long SYSACCESS_FLAG_Default              = Bit.h2d("000040000000");
    public static final long SYSACCESS_FLAG_Active               = Bit.h2d("000000000001");
    public static final long SYSACCESS_KIND_Default              = Bit.h2d("000010000000");
    public static final long SYSACCESS_KIND_Policy               = Bit.h2d("000001000000");
    public static final long SYSACCESS_KIND_State                = Bit.h2d("000002000000");
    public static final long SYSFORMAT_FLAG_Default              = Bit.h2d("000020000000");
    public static final long SYSFORMAT_FLAG_Active               = Bit.h2d("000000000001");
    public static final long SYSTRIGGERPGM_FLAG_Default          = Bit.h2d("000080000000");
    public static final long SYSTRIGGERPGM_FLAG_Active           = Bit.h2d("000000000001");
    public static final long SYSSTATETRIGGER_FLAG_Default        = Bit.h2d("000080000000");
    public static final long SYSSTATETRIGGER_FLAG_Active         = Bit.h2d("000000000001");
    public static final long SYSSTATETRIGGER_KIND_Default        = Bit.h2d("000040000000");
    public static final long SYSSTATETRIGGER_KIND_Promote        = Bit.h2d("000000000010");
    public static final long SYSSTATETRIGGER_KIND_Demote         = Bit.h2d("000000000020");
    public static final long SYSSTTRIG_PKIND_Default             = Bit.h2d("000040000000");
    public static final long SYSSTTRIG_PKIND_Check               = Bit.h2d("000000000010");
    public static final long SYSSTTRIG_PKIND_ActionPre           = Bit.h2d("000000000020");
    public static final long SYSSTTRIG_PKIND_Action              = Bit.h2d("000000000040");
    public static final long SYSSTTRIG_PKIND_ActionPost          = Bit.h2d("000000000080");
    public static final long SYSLIFEINFO_FLAG_Default            = Bit.h2d("000080000000");
    public static final long SYSLIFEINFO_FLAG_Active             = Bit.h2d("000000000001");
    public static final long SYSLIFEINFO_KIND_Default            = Bit.h2d("000040000000");
    public static final long SYSLIFEINFO_KIND_Format             = Bit.h2d("000000100000");
    public static final long SYSLIFEINFO_KIND_Class              = Bit.h2d("000000200000");
    public static final long SYSCONSTANTS_FLAG_Default           = Bit.h2d("010000000000");
    public static final long SYSCONSTANTS_FLAG_Active            = Bit.h2d("000000000001");
    public static final long SYSPTY_FLAG_Default                 = Bit.h2d("000080000000");
    public static final long SYSPTY_FLAG_Active                  = Bit.h2d("000000000001");
    public static final long SYSPTY_KIND_Default                 = Bit.h2d("000060000000");
    public static final long SYSPTY_KIND_User                    = Bit.h2d("000000001000");
    public static final long SYSPTY_KIND_Menu                    = Bit.h2d("000000002000");
    public static final long SYSPTY_KIND_Site                    = Bit.h2d("000000004000");
    public static final long SYSPTY_KIND_Constants               = Bit.h2d("000000008000");
    public static final long SYSPTY_KIND_LifeCycle               = Bit.h2d("000000010000");
    public static final long SYSPTY_KIND_State                   = Bit.h2d("000000020000");
    public static final long SYSPTY_KIND_Class                   = Bit.h2d("000000040000");
    public static final long SYSPTY_KIND_Attribute               = Bit.h2d("000000080000");
    public static final long SYSREL_FLAG_Default                 = Bit.h2d("000010000000");
    public static final long SYSREL_FLAG_Active                  = Bit.h2d("000000000001");
    public static final long SYSREL_FLAG_Hidden                  = Bit.h2d("000000000002");
    public static final long SYSREL_FLAG_SCWindow                = Bit.h2d("000000000004");
    public static final long SYSREL_FLAG_SCFunction              = Bit.h2d("000000000008");
    public static final long SYSREL_FLAG_JSFunction              = Bit.h2d("000000000010");
    public static final long SYSREL_FLAG_ContentsRepl            = Bit.h2d("000000000020");
    public static final long SYSREL_FLAG_CheckGrpFunCall         = Bit.h2d("000000000040");
    public static final long SYSREL_FLAG_CheckGrpMethod          = Bit.h2d("000000000080");
    public static final long SYSREL_FLAG_PLSQLFunction           = Bit.h2d("000000000100");
    public static final long SYSREL_KIND_Default                 = Bit.h2d("800000000000");
    public static final long SYSREL_KIND_AssignGroup             = Bit.h2d("600000000000");
    public static final long SYSREL_KIND_GroupUsrs               = Bit.h2d("000000000010");
    public static final long SYSREL_KIND_GroupGroup              = Bit.h2d("000000000020");
    public static final long SYSREL_KIND_RoleRole                = Bit.h2d("000000000040");
    public static final long SYSREL_KIND_RoleUser                = Bit.h2d("000000000080");
    public static final long SYSREL_KIND_RoleGroupUser           = Bit.h2d("000000000100");
    public static final long SYSREL_KIND_GroupRole               = Bit.h2d("000000000200");
    public static final long SYSREL_KIND_GroupUser               = Bit.h2d("000000000400");
    public static final long SYSREL_KIND_SubMnuCmd               = Bit.h2d("010000000000");
    public static final long SYSREL_KIND_SiteHasLoc              = Bit.h2d("000000100000");
    public static final long SYSREL_KIND_StoreIncludeLoc         = Bit.h2d("000000200000");
    public static final long SYSREL_KIND_AttrGRpHasAttr          = Bit.h2d("000000400000");
    public static final long SYSREL_KIND_LayoutHasTab            = Bit.h2d("000000800000");
    public static final long SYSREL_KIND_TabHasCommand           = Bit.h2d("000001000000");
    public static final long SYSREL_KIND_PolicyStore             = Bit.h2d("000002000000");
    public static final long SYSREL_KIND_AllowedAttrGrp          = Bit.h2d("000004000000");
    
    public static final long SYSREL_KIND_GroupMenu               = Bit.h2d("000000010000");
    public static final long SYSREL_KIND_RoleMenu                = Bit.h2d("000000020000");
    public static final long SYSREL_KIND_UserMenu                = Bit.h2d("000000040000");
    
    public static final long SYSSITE_FLAG_Default                = Bit.h2d("000020000000");
    public static final long SYSSITE_FLAG_Active                 = Bit.h2d("000000000001");
    public static final long SYSMNU_KIND_Default                 = Bit.h2d("000010000000");
    public static final long SYSMNU_KIND_Menu                    = Bit.h2d("000000000100");
    public static final long SYSMNU_KIND_Command                 = Bit.h2d("000000000200");
    public static final long SYSMNU_KIND_Toolbar                 = Bit.h2d("000000000400");
    public static final long SYSMNU_KIND_ClassMenu               = Bit.h2d("000000000800");
    public static final long SYSMNU_KIND_Combo                   = Bit.h2d("000000001000");
    public static final long SYSMNU_KIND_Calendar                = Bit.h2d("000000002000");
    public static final long SYSMNU_KIND_Text                    = Bit.h2d("000000004000");
    public static final long SYSMNU_KIND_StructureMenu           = Bit.h2d("000000008000");
    public static final long SYSMNU_KIND_PopupMenu               = Bit.h2d("000000010000");
    public static final long SYSMNU_KIND_ClassPopupMenu          = Bit.h2d("000000020000");
    public static final long SYSMNU_KIND_CheckBoxGroup           = Bit.h2d("000000040000");
    public static final long SYSMNU_KIND_CheckBox                = Bit.h2d("000000080000");
    public static final long SYSMNU_KIND_RadioGroup              = Bit.h2d("000000100000");
    public static final long SYSMNU_KIND_Radio                   = Bit.h2d("000000200000");
    public static final long SYSMNU_KIND_FilterGroup             = Bit.h2d("000000400000");
    public static final long SYSMNU_KIND_Filter                  = Bit.h2d("000000800000");
    public static final long SYSMNU_KIND_Label                   = Bit.h2d("000001000000");
    public static final long SYSMNU_FLAG_Active                  = Bit.h2d("000000000010");
    public static final long SYSMNU_FLAG_Default                 = Bit.h2d("000060000000");
    public static final long SYSMNU_FLAG_Hidden                  = Bit.h2d("000000000001");
    public static final long SYSMNU_FLAG_HasParent               = Bit.h2d("000000000002");
    public static final long SYSMNU_FLAG_HasChild                = Bit.h2d("000000000004");
    public static final long SYSMNU_FLAG_SCWindow                = Bit.h2d("000000000008");
    public static final long SYSMNU_FLAG_SCFunction              = Bit.h2d("000000000020");
    public static final long SYSMNU_FLAG_JSFunction              = Bit.h2d("000000000040");
    public static final long SYSMNU_FLAG_ContentsRepl            = Bit.h2d("000000000080");
    public static final long SYSMNU_FLAG_CheckGrpFunCall         = Bit.h2d("000000000100");
    public static final long SYSMNU_FLAG_CheckGrpMethod          = Bit.h2d("000000000200");
    public static final long SYSMNU_FLAG_PLSQLFunction           = Bit.h2d("000000000400");
    public static final long SYSMNU_FLAG_IsAccessControlObject   = Bit.h2d("000000000800");
    public static final long SYSCLASSATTR_FLAG_Default           = Bit.h2d("000040000000");
    public static final long SYSCLASSATTR_FLAG_Nullable          = Bit.h2d("000000000001");
    public static final long SYSCLASSATTR_FLAG_Active            = Bit.h2d("000000000002");
    public static final long SYSCLASSATTR_FLAG_Clob              = Bit.h2d("000000000004");
    public static final long SYSCLASSATTR_FLAG_IsNameAttribute   = Bit.h2d("000000000008");
    public static final long SYSCLASSATTR_KIND_Default           = Bit.h2d("000080000000");
    public static final long SYSCLASSATTR_KIND_RO                = Bit.h2d("000001000000");
    public static final long SYSCLASSATTR_KIND_BO                = Bit.h2d("000002000000");
    public static final long SYSATTR_FLAG_Default                = Bit.h2d("100000000000");
    public static final long SYSATTR_FLAG_Active                 = Bit.h2d("000000000001");
    public static final long SYSUSER_FLAG_Default                = Bit.h2d("000080000000");
    public static final long SYSUSER_FLAG_Active                 = Bit.h2d("000000000001");
    public static final long SYSUSER_KIND_Default                = Bit.h2d("000060000000");
    public static final long SYSUSER_KIND_User                   = Bit.h2d("000000000100");
    public static final long SYSUSER_KIND_Role                   = Bit.h2d("000000000200");
    public static final long SYSUSER_KIND_Group                  = Bit.h2d("000000000400");
    public static final long SYSUSER_KIND_RoleGroup              = Bit.h2d("000000000600");//SYSUSER_KIND_Role/SYSUSER_KIND_Group
    public static final long OBJROOT_FLAG_Default                = Bit.h2d("000080000000");
    public static final long OBJROOT_FLAG_BO                     = Bit.h2d("000020000000");
    public static final long OBJROOT_FLAG_RO                     = Bit.h2d("000040000000");
    public static final long OBJROOT_FLAG_Revisible              = Bit.h2d("000000000001");
    public static final long OBJROOT_FLAG_RO_B2B                 = Bit.h2d("000000100000");
    public static final long OBJROOT_FLAG_RO_B2R                 = Bit.h2d("000000200000");
    public static final long OBJROOT_FLAG_RO_R2B                 = Bit.h2d("000000400000");
    public static final long OBJROOT_FLAG_RO_R2R                 = Bit.h2d("000000800000");
    public static final long OBJROOT_FLAG_RO_FromDefloat         = Bit.h2d("000001000000");
    public static final long OBJROOT_FLAG_RO_ToDefloat           = Bit.h2d("000002000000");
    public static final long BUSINESS_FLAG_Default               = Bit.h2d("000020000000");
    public static final long BUSINESS_FLAG_Active                = Bit.h2d("000000000010");
    public static final long BUSINESS_FLAG_Instantiable          = Bit.h2d("000000000001");
    public static final long BUSINESS_FLAG_Revisible             = Bit.h2d("000000000100");
    public static final long BUSINESS_FLAG_Workflow              = Bit.h2d("000000000200");
    public static final long BUSINESS_FLAG_ComboDisplay          = Bit.h2d("000000000400");
    public static final long FLAG_ALLOWEDRELATION_Default        = Bit.h2d("000010000000");
    public static final long FLAG_ALLOWEDRELATION_FROM           = Bit.h2d("000002000000");
    public static final long FLAG_ALLOWEDRELATION_TO             = Bit.h2d("000004000000");
    public static final long FLAG_ALLOWEDRELATION_BO             = Bit.h2d("000000020000");
    public static final long FLAG_ALLOWEDRELATION_RO             = Bit.h2d("000000040000");
    public static final long RELATION_RULE_Default               = Bit.h2d("000020000000");
    public static final long RELATION_RULE_RevNone               = Bit.h2d("000000000100");
    public static final long RELATION_RULE_RevFloat              = Bit.h2d("000000000200");
    public static final long RELATION_RULE_RevReplicate          = Bit.h2d("000000000400");
    public static final long RELATION_RULE_CloneNone             = Bit.h2d("000000010000");
    public static final long RELATION_RULE_CloneFloat            = Bit.h2d("000000020000");
    public static final long RELATION_RULE_CloneReplicate        = Bit.h2d("000000040000");
    public static final long RELATION_RULE_DeleteDefloat         = Bit.h2d("000000080000");
    public static final long RELATION_FLAG_Default               = Bit.h2d("000060000000");
    public static final long RELATION_FLAG_Active                = Bit.h2d("000000000004");
    public static final long RELATION_FLAG_Instantiable          = Bit.h2d("000000000001");
    public static final long RELATION_FLAG_AllowDuplicate        = Bit.h2d("000000000002");
    public static final long RELATION_CARDINALITY_Default        = Bit.h2d("000000001000");
    public static final long RELATION_CARDINALITY_One            = Bit.h2d("000000002000");
    public static final long RELATION_CARDINALITY_Many           = Bit.h2d("000000004000");
    public static final long CLASSINFO_FLAG_Default              = Bit.h2d("000020000000");
    public static final long CLASSINFO_FLAG_Active               = Bit.h2d("000000000001");
    public static final long CLASSINFO_FLAG_Relation             = Bit.h2d("000001000000");
    public static final long CLASSINFO_FLAG_Business             = Bit.h2d("000002000000");
    public static final long CLASSINFO_FLAG_Instantiable         = Bit.h2d("000004000000");
    public static final long CLASSINFO_FLAG_FromDefloat          = Bit.h2d("000008000000");
    public static final long CLASSINFO_FLAG_ToDefloat            = Bit.h2d("000000000010");
    public static final long CLASSINFO_FLAG_IsReferenced         = Bit.h2d("000000000020");
    public static final long DBCOLUMN_FLAG_Default               = Bit.h2d("000020000000");
    public static final long DBCOLUMN_FLAG_Active                = Bit.h2d("000000000001");
    public static final long ACCMTH_FLAG_Default                 = Bit.h2d("000080000000");
    public static final long ACCMTH_FLAG_Active                  = Bit.h2d("000000000001");
    public static final long ACCMTH_KIND_Default                 = Bit.h2d("000060000000");
    public static final long ACCMTH_KIND_Create                  = Bit.h2d("000000000010");
    public static final long ACCMTH_KIND_Update                  = Bit.h2d("000000000020");
    public static final long ACCMTH_KIND_Delete                  = Bit.h2d("000000000040");
    public static final long ACCMTH_KIND_Read                    = Bit.h2d("000000000080");
    public static final long ACCMTH_KIND_Authority               = Bit.h2d("000000000100");
    public static final long TIMEZONE_FLAG_Default               = Bit.h2d("000002000000");
    public static final long TIMEZONE_FLAG_Active                = Bit.h2d("000000000001");
    public static final int SYSKEY_KIND_BOClass                  = 1;
    public static final int SYSKEY_KIND_ROClass                  = 2;
    public static final int SYSKEY_KIND_ClassAttr                = 3;
    public static final int SYSKEY_KIND_Attr                     = 33;
    public static final int SYSKEY_KIND_User                     = 4;
    public static final int SYSKEY_KIND_Role                     = 5;
    public static final int SYSKEY_KIND_Group                    = 6;
    public static final int SYSKEY_KIND_RoleGroup                = 18;
    public static final int SYSKEY_KIND_Site                     = 7;
    public static final int SYSKEY_KIND_LifeCycle                = 8;
    public static final int SYSKEY_KIND_State                    = 9;
    public static final int SYSKEY_KIND_Branch                   = 10;
    public static final int SYSKEY_KIND_AllowedRel               = 11;
    public static final int SYSKEY_KIND_Menu                     = 12;
    public static final int SYSKEY_KIND_Command                  = 13;
    public static final int SYSKEY_KIND_ToolBar                  = 14;
    public static final int SYSKEY_KIND_ClassMenu                = 15;
    public static final int SYSKEY_KIND_SysRelation              = 16;
    public static final int SYSKEY_KIND_AccMthSet                = 17;
    public static final int SYSKEY_KIND_Access                   = 18;
    public static final int SYSKEY_KIND_Format                   = 19;
    public static final int SYSKEY_KIND_Trigger                  = 20;
    public static final int SYSKEY_KIND_ProgramParm              = 21;
    public static final int SYSKEY_KIND_PolicyInfo               = 22;
    public static final int SYSKEY_KIND_FileStore                = 23;
    public static final int SYSKEY_KIND_FileLocation             = 24;
    public static final int SYSKEY_KIND_AttrGroup                = 25;
    public static final int SYSKEY_KIND_Attribute                = 26;
    public static final int SYSKEY_KIND_Property                 = 27;
    public static final int SYSKEY_KIND_Layout                   = 28;
    public static final int SYSKEY_KIND_Tab                      = 29;
    public static final int SYSKEY_KIND_Class                    = 30;
    public static final int SYSKEY_KIND_DBColumn                 = 31;
    public static final int SYSKEY_KIND_TimeZone                 = 32;
    public static final int SYSKEY_KIND_SystemConstants          = 999;
}
