/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaConstants.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 2.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.util;

/**
 * <pre>
 * Class : OmcSchemaConstants
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaConstants {
    //Dummy Keytable Obid
    public static final String C_SCHEMA_DUMMY_KEY                      = "1";
    //Class Info 최상위용(Dummy)
    public static final String C_SCHEMA_00000000000000000000           = "00000000000000000000";
	
    public static final int    C_DBMS_BATCH_COUNT               = 500;
    public static final String C_SCHEMA_MAP_CONSTANTS           = "Constant List";
    public static final String C_SCHEMA_MAP_BIZ_CLASS           = "Business Class List";
    public static final String C_SCHEMA_MAP_REL_CLASS           = "Relation Class List";
    public static final String C_SCHEMA_MAP_CLASS_ATTR          = "Class Attribute List";
    public static final String C_SCHEMA_MAP_ATTR_DISPLYED       = "Attribute";
    
    public static final String C_SCHEMA_MAP_LIFE_CYCLE          = "Life Cycle List";
    public static final String C_SCHEMA_MAP_LIFE_STATE_INFO     = "Life Cycle State Info";
    public static final String C_SCHEMA_MAP_LIFE_BRANCH         = "Life Cycle Branch";
    public static final String C_SCHEMA_MAP_LIFE_PARAMETER      = "Life Cycle Parmeter";
    public static final String C_SCHEMA_MAP_LIFE_TRIGGER        = "Life Cycle Trigger";
    public static final String C_SCHEMA_MAP_LIFE_STATE_TRIGGER  = "Life State Trigger";
    public static final String C_SCHEMA_MAP_FILE_FORMAT         = "File Format";
    
    public static final String C_SCHEMA_MAP_MENU                = "Menu";
    public static final String C_SCHEMA_MAP_TAB_LAYOUT          = "Tab Layout";
    
    public static final String C_SCHEMA_MAP_SITE                = "Site";
    public static final String C_SCHEMA_MAP_STORE_LOCATION      = "Store Location";
    public static final String C_SCHEMA_MAP_ROLE_GROUP          = "Role Group";
    public static final String C_SCHEMA_MAP_ASSIGN              = "Assign";
    public static final String C_SCHEMA_MAP_METHOD_SET          = "Method Set";
    public static final String C_SCHEMA_MAP_DYNAMIC_ATTRIBUTE   = "Dynamic Attribute";
    
    public static final String C_SHEET_CONSTANTS                = "System Constants";
    public static final int    C_SHEET_CONSTANTS_ROW            = 2;
    public static final String C_SHEET_BUSINESS_CLASS           = "Business Object";
    public static final int    C_SHEET_BUSINESS_CLASS_SROW      = 1;
    public static final String C_SHEET_RELATION_CLASS           = "Relation Object";
    public static final int    C_SHEET_RELATION_CLASS_SROW      = 2;
    public static final String C_SHEET_CLASS_ATTRIBUTE          = "Class Attribute";
    public static final int    C_SHEET_CLASS_ATTRIBUTE_SROW     = 2;
    
    public static final String C_SHEET_CLASS_ATTR_DISPLAYED     = "Attribute Displayed Names";
    public static final int    C_SHEET_CLASS_ATTR_DISPLAYED_SROW= 3;
    
    public static final String C_SHEET_LIFE_CYCLE               = "Life Cycle";
    public static final int    C_SHEET_LIFE_CYCLE_ROW           = 1;
    public static final String C_SHEET_STATE                    = "State Info";
    public static final int    C_SHEET_STATE_ROW                = 3;
    public static final String C_SHEET_BRANCH                   = "Branch";
    public static final int    C_SHEET_BRANCH_ROW               = 1;
    public static final String C_SHEET_TRIGGER_PARAM            = "Trigger Parameter";
    public static final int    C_SHEET_TRIGGER_PARAM_ROW        = 1;
    public static final String C_SHEET_STATE_TRIGGER            = "State Trigger";
    public static final int    C_SHEET_STATE_TRIGGER_ROW        = 2;
    public static final String C_SHEET_MENU                     = "Menu";
    public static final int    C_SHEET_MENU_ROW                 = 2;
    public static final String C_SHEET_LAYOUT_TAB               = "PageLayout and Tab";
    public static final int    C_SHEET_LAYOUT_TAB_ROW           = 2;
    
    public static final String C_SHEET_SITE                     = "Site";
    public static final int    C_SHEET_SITE_ROW                 = 1;
    
    public static final String C_SHEET_FILE_FORMAT              = "File Format";
    public static final int    C_SHEET_FILE_FORMAT_ROW          = 2;
    public static final String C_SHEET_STORE_LOCATION           = "Store and Location";
    public static final int    C_SHEET_STORE_LOCATION_ROW       = 1;
    public static final String C_SHEET_ROLE_GROUP               = "Role & Group";
    public static final int    C_SHEET_ROLE_GROUP_ROW           = 2;
    public static final String C_SHEET_ASSIGN                   = "Assign";
    public static final int    C_SHEET_ASSIGN_ROW               = 2;
    public static final String C_SHEET_METHOD_SET               = "Access Method Set";
    public static final int    C_SHEET_METHOD_SET_ROW           = 2;
    public static final String C_SHEET_DYNAMIC_ATTRIBUTE        = "Dynamic Attribute";
    public static final int    C_SHEET_DYNAMIC_ATTRIBUTE_ROW    = 2;
    
    /************************************************************************************************/
    public static final int C_COLUMN_CONST_SEQUENCES            = 0;
    public static final int C_COLUMN_CONST_CHANGE_COMMENTS      = 1;
    public static final int C_COLUMN_CONST_KINDS                = 2;
    public static final int C_COLUMN_CONST_KIND_DESC            = 3;
    public static final int C_COLUMN_CONST_NAMES                = 4;
    public static final int C_COLUMN_CONST_VALUES               = 5;
    public static final int C_COLUMN_CONST_PHEX_CONVERTING_FLAG = 6;
    /************************************************************************************************/
    public static final int C_BIZ_COLUMN_SEQUENCES          = 0;
    public static final int C_BIZ_COLUMN_CHANGE_COMMENTS    = 1;
    public static final int C_BIZ_COLUMN_MODULE_NAME        = 2;
    public static final int C_BIZ_COLUMN_NAMES              = 3;
    public static final int C_BIZ_COLUMN_NAMES_PARENT       = 4;
    public static final int C_BIZ_COLUMN_DEFAULT_POLICY     = 5;
    public static final int C_BIZ_COLUMN_DBMS_TABLE         = 6;
    public static final int C_BIZ_COLUMN_IS_INSTANTIABLE    = 7;
    public static final int C_BIZ_COLUMN_JAVA_PACKAGE       = 8;
    public static final int C_BIZ_COLUMN_DISPLAYED_NAME     = 9;
    public static final int C_BIZ_COLUMN_DISPLAYED_NAME_KR  = 10;
    public static final int C_BIZ_COLUMN_COMBO_DISPLAY      = 11;
    public static final int C_BIZ_COLUMN_APPLY_WORKFLOW     = 12;
    public static final int C_BIZ_COLUMN_WORKFLOW_URL       = 13;
    public static final int C_BIZ_COLUMN_REMARKS            = 14;
    public static final int C_BIZ_COLUMN_CLASS_ICON         = 15;
    public static final int C_BIZ_COLUMN_CLASS_ICON_SMALL   = 16;
    public static final int C_BIZ_COLUMN_OWNERS             = 17;
    
    public static final int C_REL_COLUMN_SEQUENCES          = 0;
    public static final int C_REL_COLUMN_CHANGE_COMMENTS    = 1;
    public static final int C_REL_COLUMN_MODULE_NAME        = 2;
    public static final int C_REL_COLUMN_NAMES              = 3;
    public static final int C_REL_COLUMN_NAMES_PARENT       = 4;
    public static final int C_REL_COLUMN_DISPLAYED_NAME     = 5;
    public static final int C_REL_COLUMN_DISPLAYED_NAME_KR  = 6;
    public static final int C_REL_COLUMN_DBMS_TABLE         = 7;
    public static final int C_REL_COLUMN_ALLOW_DUPLICATE    = 8;
    public static final int C_REL_COLUMN_FROM_TYPE          = 9;
    public static final int C_REL_COLUMN_TO_TYPE            = 10;
    public static final int C_REL_COLUMN_FROM_RELATONSHIP   = 11;
    public static final int C_REL_COLUMN_TO_RELATONSHIP     = 12;
    public static final int C_REL_COLUMN_FROM_MEANING       = 13;
    public static final int C_REL_COLUMN_TO_MEANING         = 14;
    public static final int C_REL_COLUMN_CARDINALITY_FROM   = 15;
    public static final int C_REL_COLUMN_CARDINALITY_TO     = 16;
    public static final int C_REL_COLUMN_REVISION_RULE_FROM = 17;
    public static final int C_REL_COLUMN_REVISION_RULE_TO   = 18;
    public static final int C_REL_COLUMN_IS_INSTANTIABLE    = 19;
    public static final int C_REL_COLUMN_JAVA_PACKASGE      = 20;
    public static final int C_REL_COLUMN_REMARKS            = 21;
    public static final int C_REL_COLUMN_OWNERS             = 22;
    
    public static final int C_ATTR_COLUMN_SEQUENCES          = 0;
    public static final int C_ATTR_COLUMN_CHANGE_COMMENTS    = 1;
    public static final int C_ATTR_COLUMN_CLASS_TYPE         = 2;
    public static final int C_ATTR_COLUMN_CLASS_NAME         = 3;
    public static final int C_ATTR_COLUMN_ATTR_NAME          = 4;
    public static final int C_ATTR_COLUMN_DBMS_COLUMN        = 5;
    public static final int C_ATTR_COLUMN_DBMS_ALIAS         = 6;
    public static final int C_ATTR_COLUMN_ATTR_SORTING       = 7;
    public static final int C_ATTR_COLUMN_ATTR_TYPE          = 8;
    public static final int C_ATTR_COLUMN_ATTR_LENGTH        = 9;
    public static final int C_ATTR_COLUMN_NULLABLE           = 10;
    public static final int C_ATTR_COLUMN_DEFAULT_VALUE      = 11;
    public static final int C_ATTR_COLUMN_VALUE_LIST         = 12;
    public static final int C_ATTR_COLUMN_DISPLAYED_NAME     = 13;
    public static final int C_ATTR_COLUMN_REMARKS            = 14;
    public static final int C_ATTR_COLUMN_OWNERS             = 15;
    
    public static final int C_ATTR_DISPLAYED_SEQUENCES          = 0;
    public static final int C_ATTR_DISPLAYED_CHANGE_COMMENTS    = 1;
    public static final int C_ATTR_DISPLAYED_MODULE             = 2;
    public static final int C_ATTR_DISPLAYED_ATTR_NAME          = 3;
    public static final int C_ATTR_DISPLAYED_ATTR_DISPLAYED_NAME= 4;
    public static final int C_ATTR_DISPLAYED_ATTR_DISPLAYED_NAME_KR  = 5;
    public static final int C_ATTR_DISPLAYED_REMARKS            = 6;
    public static final int C_ATTR_DISPLAYED_OWNERS             = 7;
    
    public static final long C_SCHEMA_IS_TEMPORARY_ONLY             = Bit.h2d("000000000001");
    public static final long C_SCHEMA_CONSTANT_UPLOAD               = Bit.h2d("000000000002");
    public static final long C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS        = Bit.h2d("000000000020");
    public static final long C_SCHEMA_CLASS_UPLOAD_REL_CLASS        = Bit.h2d("000000000040");
    public static final long C_SCHEMA_CLASS_UPLOAD_ATTR             = Bit.h2d("000000000080");
    public static final long C_SCHEMA_ATTR_DISPLAYED                = Bit.h2d("000004000000");
    
    public static final long C_SCHEMA_LIFE_CYCLE_UPLOAD             = Bit.h2d("000000000200");
    public static final long C_SCHEMA_STATE_INFO_UPLOAD             = Bit.h2d("000000000400");
    public static final long C_SCHEMA_BRANCH_UPLOAD                 = Bit.h2d("000000000800");
    public static final long C_SCHEMA_PARAMETER_UPLOAD              = Bit.h2d("000000002000");
    public static final long C_SCHEMA_STATE_TRIGGER_UPLOAD          = Bit.h2d("000000004000");
    public static final long C_SCHEMA_FILE_FORMAT_UPLOAD            = Bit.h2d("000000040000");
    
    
    public static final long C_SCHEMA_MENU_UPLOAD                   = Bit.h2d("000000008000");
    public static final long C_SCHEMA_PAGE_LAYOUT_UPLOAD            = Bit.h2d("000000020000");
    
    public static final long C_SCHEMA_SITE_UPLOAD                   = Bit.h2d("000002000000");
    public static final long C_SCHEMA_STORE_LOCATION_UPLOAD         = Bit.h2d("000000080000");
    public static final long C_SCHEMA_ROLE_GROUP_UPLOAD             = Bit.h2d("000000200000");
    public static final long C_SCHEMA_ACCESS_METHOD_UPLOAD          = Bit.h2d("000000400000");
    public static final long C_SCHEMA_DYNAMIC_ATTR_UPLOAD           = Bit.h2d("000000800000");
    
    /*****************************************************************************************************************************/
    /*****************************************************************************************************************************/
    
    public static final int C_LIFECYCLE_COLUMN_SEQUENCES            = 0;
    public static final int C_LIFECYCLE_COLUMN_CHANGE_COMMENTS      = 1;
    public static final int C_LIFECYCLE_COLUMN_NAMES                = 2;
    public static final int C_LIFECYCLE_COLUMN_DISPLAYED_NAME       = 3;
    public static final int C_LIFECYCLE_COLUMN_SEQUENCE_RULE        = 4;
    public static final int C_LIFECYCLE_COLUMN_DEFAULT_FORMAT       = 5;
    public static final int C_LIFECYCLE_COLUMN_STATE_LIST           = 6;
    public static final int C_LIFECYCLE_COLUMN_STORE                = 7;
    public static final int C_LIFECYCLE_COLUMN_ALL_STATE_DEF        = 8;
    public static final int C_LIFECYCLE_COLUMN_APPLIED_CLASS_LIST   = 9;
    public static final int C_LIFECYCLE_COLUMN_ALLOWED_FORMAT_LIST  = 10;
    public static final int C_LIFECYCLE_COLUMN_OWNERS               = 11;
    
    public static final int C_LIFESTATEINFO_COLUMN_SEQUENCES                    = 0;
    public static final int C_LIFESTATEINFO_COLUMN_CHANGE_COMMENTS              = 1;
    public static final int C_LIFESTATEINFO_COLUMN_LIFE_CYCLE                   = 2;
    public static final int C_LIFESTATEINFO_COLUMN_STATE                        = 3;
    public static final int C_LIFESTATEINFO_COLUMN_ROUTE_COMPLETE_ACTION        = 4;
    public static final int C_LIFESTATEINFO_COLUMN_DEFAULT_ROUTE_PURPOSE        = 5;
    public static final int C_LIFESTATEINFO_COLUMN_ROUTE_AUTOSTART_ONREJECT     = 6;
    public static final int C_LIFESTATEINFO_COLUMN_ROUTE_HOWTO_ONREJECT         = 7;
    public static final int C_LIFESTATEINFO_COLUMN_INBOX_TASK_AUTO_COMPLETE     = 8;
    public static final int C_LIFESTATEINFO_COLUMN_DATE_OFFSET_DAY              = 9;
    public static final int C_LIFESTATEINFO_COLUMN_PARALLEL_PROCESS_RULE        = 10;
    
    public static final int C_LIFE_TRIGGER_COLUMN_SEQUENCES                     = 0;
    public static final int C_LIFE_TRIGGER_COLUMN_CHANGE_COMMENTS               = 1;
    public static final int C_LIFE_TRIGGER_COLUMN_NAMES                         = 2;
    public static final int C_LIFE_TRIGGER_COLUMN_DESCRIPTION                   = 3;
    public static final int C_LIFE_TRIGGER_COLUMN_PROGRAM_NAME                  = 4;
    public static final int C_LIFE_TRIGGER_COLUMN_METHOD_NAME                   = 5;
    public static final int C_LIFE_TRIGGER_COLUMN_CONSTRUCTOR                   = 6;
    public static final int C_LIFE_TRIGGER_COLUMN_CALLED_SEQUENCE               = 7;
    public static final int C_LIFE_TRIGGER_COLUMN_RETURN_TYPE                   = 8;
    public static final int C_LIFE_TRIGGER_COLUMN_TARGET_STATES                 = 9;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT01                    = 10;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT02                    = 11;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT03                    = 12;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT04                    = 13;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT05                    = 14;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT06                    = 15;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT07                    = 16;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT08                    = 17;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT09                    = 18;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT10                    = 19;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC01               = 20;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC02               = 21;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC03               = 22;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC04               = 23;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC05               = 24;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC06               = 25;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC07               = 26;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC08               = 27;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC09               = 28;
    public static final int C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC10               = 29;
    
    public static final int C_LIFE_STATE_TRIGGER_COLUMN_SEQUENCES               = 0;
    public static final int C_LIFE_STATE_TRIGGER_COLUMN_CHANGE_COMMENTS         = 1;
    public static final int C_LIFE_STATE_TRIGGER_COLUMN_POLICY_NAME             = 2;
    public static final int C_LIFE_STATE_TRIGGER_COLUMN_STATE_NAME              = 3;
    public static final int C_LIFE_STATE_TRIGGER_COLUMN_KIND_STR                = 4;
    public static final int C_LIFE_STATE_TRIGGER_COLUMN_PROGRAM_TYPE            = 5;
    public static final int C_LIFE_STATE_TRIGGER_COLUMN_CALLED_SEQUENCE         = 6;
    public static final int C_LIFE_STATE_TRIGGER_COLUMN_TRIGGER_MANAGER         = 7;
    public static final int C_LIFE_STATE_TRIGGER_COLUMN_TRIGGER_NAME            = 8;
    
    public static final int C_LIFEBRANCH_COLUMN_SEQUENCES                    = 0;
    public static final int C_LIFEBRANCH_COLUMN_CHANGE_COMMENTS              = 1;
    public static final int C_LIFEBRANCH_COLUMN_LIFE_CYCLE                   = 2;
    public static final int C_LIFEBRANCH_COLUMN_BRACNH_INFO                  = 3;
    
    public static final int C_LIFE_MENU_COLUMN_SEQUENCES                = 0;
    public static final int C_LIFE_MENU_COLUMN_CHANGE_COMMENTS          = 1;
    public static final int C_LIFE_MENU_COLUMN_IS_DUB                   = 2;
    public static final int C_LIFE_MENU_COLUMN_MODULE_NAME              = 3;
    public static final int C_LIFE_MENU_COLUMN_ACCESS_CONTROL           = 4;
    public static final int C_LIFE_MENU_COLUMN_NAMES                    = 5;
    public static final int C_LIFE_MENU_COLUMN_KINDS                    = 6;
    public static final int C_LIFE_MENU_COLUMN_SORT                     = 7;
    public static final int C_LIFE_MENU_COLUMN_SUB_NAMES                = 8;
    public static final int C_LIFE_MENU_COLUMN_IS_HIDDEN                = 9;
    public static final int C_LIFE_MENU_COLUMN_LABELS                   = 10;
    public static final int C_LIFE_MENU_COLUMN_LABELS_KR                = 11;
    public static final int C_LIFE_MENU_CALLING_TYPE                    = 12;
    public static final int C_LIFE_MENU_HERF                            = 13;
    public static final int C_LIFE_MENU_ALT                             = 14;
    public static final int C_LIFE_MENU_IMAGE                           = 15;
    public static final int C_LIFE_MENU_ACCESS_EXPRESSION               = 16;
    public static final int C_LIFE_MENU_REMARKS                         = 17;
    public static final int C_LIFE_MENU_OWNERS                          = 18;
    
    public static final int C_LIFE_LAYOUT_TAB_COLUMN_SEQUENCES                = 0;
    public static final int C_LIFE_LAYOUT_TAB_COLUMN_CHANGE_COMMENTS          = 1;
    public static final int C_LIFE_LAYOUT_TAB_COLUMN_USAGES                   = 2;
    public static final int C_LIFE_LAYOUT_TAB_COLUMN_KINDS                    = 3;
    public static final int C_LIFE_LAYOUT_TAB_COLUMN_NAMES                    = 4;
    public static final int C_LIFE_LAYOUT_TAB_COLUMN_DISPLAYED_NAMES          = 5;
    public static final int C_LIFE_LAYOUT_TAB_COLUMN_DISPLAYED_NAMES_KR       = 6;
    public static final int C_LIFE_LAYOUT_TAB_COLUMN_SUBOBJECT_LIST           = 7;
    public static final int C_LIFE_LAYOUT_TAB_COLUMN_LINK_HERF                = 8;
    public static final int C_LIFE_LAYOUT_TAB_COLUMN_LINK_ALT                 = 9;
    public static final int C_LIFE_LAYOUT_TAB_HEIGHTS                         = 10;
    public static final int C_LIFE_LAYOUT_TAB_OWNERS                          = 11;
    
    
    public static final int C_FILE_FORMAT_COLUMN_SEQUENCES            = 0;
    public static final int C_FILE_FORMAT_COLUMN_CHANGE_COMMENTS      = 1;
    public static final int C_FILE_FORMAT_COLUMN_NAMES                = 2;
    public static final int C_FILE_FORMAT_COLUMN_SUFFIX               = 3;
    public static final int C_FILE_FORMAT_COLUMN_DISPLAYED_NAME       = 4;
    public static final int C_FILE_FORMAT_COLUMN_OWNERS               = 5;
    
    
    public static final int C_SITE_COLUMN_NAMES                          = 0;
    public static final int C_SITE_COLUMN_DISPLAYED_NAMES                = 1;
    
    
    public static final int C_STORE_LOCATION_COLUMN_SEQUENCES            = 0;
    public static final int C_STORE_LOCATION_COLUMN_CHANGE_COMMENTS      = 1;
    public static final int C_STORE_LOCATION_COLUMN_KINDS                = 2;
    public static final int C_STORE_LOCATION_COLUMN_NAMES                = 3;
    public static final int C_STORE_LOCATION_COLUMN_SERVER               = 4;
    public static final int C_STORE_LOCATION_COLUMN_PATH                 = 5;
    public static final int C_STORE_LOCATION_COLUMN_FTP_USER             = 6;
    public static final int C_STORE_LOCATION_COLUMN_FTP_PASSWORD         = 7;
    public static final int C_STORE_LOCATION_COLUMN_FTP_PROTOCOL         = 8;
    public static final int C_STORE_LOCATION_COLUMN_FTP_PORT             = 9;
    public static final int C_STORE_LOCATION_COLUMN_SERVICE_DOMAIN       = 10;
    public static final int C_STORE_LOCATION_COLUMN_SERVICE_PORT         = 11;
    public static final int C_STORE_LOCATION_COLUMN_SERVICE_URL          = 12;
    
    
    public static final int C_ROLE_GROUP_COLUMN_SEQUENCES            = 0;
    public static final int C_ROLE_GROUP_COLUMN_CHANGE_COMMENTS      = 1;
    public static final int C_ROLE_GROUP_COLUMN_KINDS                = 2;
    public static final int C_ROLE_GROUP_COLUMN_NAMES                = 3;
    public static final int C_ROLE_GROUP_COLUMN_DESCRIPTIONS         = 4;
    public static final int C_ROLE_GROUP_COLUMN_OWNERS               = 5;
    public static final int C_ROLE_GROUP_COLUMN_REMARKS              = 6;
    
    public static final int C_DYNAMIC_ATTRGRP_COLUMN_KINDS           = 0;
    public static final int C_DYNAMIC_ATTRGRP_COLUMN_NAMES           = 1;
    public static final int C_DYNAMIC_ATTRGRP_COLUMN_DESCRIPTIONS    = 2;
    public static final int C_DYNAMIC_ATTRGRP_COLUMN_PARENT          = 3;
    public static final int C_DYNAMIC_ATTRGRP_COLUMN_DISPLAYED_NAME  = 4;

    public static final int C_METHODSET_COLUMN_NAMES                = 0;
    public static final int C_METHODSET_COLUMN_CONSTANT_VALUE       = 1;
    public static final int C_METHODSET_COLUMN_KINDS                = 2;
    public static final int C_METHODSET_COLUMN_OWNERS               = 3;
    
    public static final long C_SCHEMA_CLASS_EXPLOSION_FORWARD       = Bit.h2d("000000000020");
    public static final long C_SCHEMA_CLASS_EXPLOSION_BACKWARD      = Bit.h2d("000000000040");
    public static final String C_SCHEMA_CLASS_EXPLOSION_UNIQSTR     = "000";
    public static final int C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL     = 1;
    public static final int C_SCHEMA_CLASS_MAXLEVEL                 = 10;
    
    public static final String C_SCHEMA_DEFAULT_USER        = "XP3866";
    public static final String C_PTY_CLASS_ICON             = "CLASS_ICON";
    public static final String C_PTY_CLASS_ICON_SMALL       = "CLASS_ICON_SMALL";
    public static final String C_PTY_CLASS_MOUDLE           = "CLASS_MODULE";
    public static final String C_PTY_CLASS_OWNER            = "CLASS_OWNER";
    public static final String C_PTY_CLASS_REMARKS          = "CLASS_REMARKS";
    public static final String C_PTY_CLASS_CHANGE_COMMENTS  = "CLASS_CHANGE_COMMENTS";
    public static final String C_PTY_CLASS_SEQUENCE         = "CLASS_SEQUENCE";
    
    public static final String C_PTY_RELCLASS_MODULE        = "RELCLASS_MODULE";
    public static final String C_PTY_RELCLASS_OWNER         = "RELCLASS_OWNER";
    public static final String C_PTY_RELCLASS_REMARKS       = "RELCLASS_REMARKS";
    public static final String C_PTY_RELCLASS_CHANGE_COMMENTS = "RELCLASS_CHANGE_COMMENTS";
    public static final String C_PTY_RELCLASS_SEQUENCE        = "RELCLASS_SEQUENCE";
    
    public static final int C_DBMS_IN_MAX_COUNT_ORACLE       = 1000;
    public static final int C_DBMS_IN_MAX_COUNT_MSSQL        = 1000;
    public static final int C_DBMS_IN_MAX_COUNT_DB2          = 1000;
    public static final int C_DBMS_IN_MAX_COUNT_MYSQL        = 1000;
    public static final int C_DBMS_IN_MAX_COUNT_MARIA        = 1000;
    public static final String C_SYSTEM_MAC_ADDRESS          = OmcUniqueIDGenerator.getMACAddress();
}

