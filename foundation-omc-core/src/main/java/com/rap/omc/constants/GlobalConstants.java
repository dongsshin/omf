/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ClassNameConstants.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 26. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.constants;

import java.util.Date;

import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.schema.util.OmcUniqueIDGenerator;

/**
 * <pre>
 * Class : GlobalConstants
 * Description : TODO
 * </pre>
 *
 * @author hyeyoung.park
 */
public class GlobalConstants {
	
	public static final String AJAX_VIEW = "ajaxView";
	public static String SESSION_USER_INFO = "userSession";
	
	public static String SYSTEM_USER_ID_SystemAgent = "System Agent";
	
	public static final String ROLE_NAME_SYSTEM_ADMIN = "System Administration Role";
	
	public static final String LANG_EN = OmcSystemConstants.OMC_LOCALE_LANG_EN;
    public static final String LANG_KO = OmcSystemConstants.OMC_LOCALE_LANG_KO;
    public static final String LANG_CH = OmcSystemConstants.OMC_LOCALE_LANG_CH;
    public static final String DEFAULT_LANG = OmcSystemConstants.OMC_LOCALE_LANG_EN;
    public static final String[] LANG_VALID_SET = {LANG_EN,LANG_KO,LANG_CH};
	
    

    
    /**
     * 사용자 ID 정보 없음
     */
	
    public static final String DEFAULT_TIME_ZONE = "Asia/Seoul";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATETIME_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_DATETIME_STRING_FORMAT = "yyyyMMddHHmmss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_YYYYMMDD_FORMAT = "yyyyMMdd";

    public static final String DATE_TYPE_JAVA = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TYPE_JAVA_EXCLUDE_TIME = "yyyy-MM-dd";
	
    public static final String NO_USER_ID = "noid";
    public static final String NO_REMOTE_ADDR = "no remote addr";
    public static final String NO_BUSINESS_UNIT = "none";
    public static final String DUMMY = "DUMMY";
    public static final String USER_CORPORATE = "Corporate";
    
    
    public static final String OMC_GROUP_BY_COUNT = "omcGroupByCount";
    
    //OTP Creator
    public static final String OTP_CREATOR  = "otpCreator";

    public static final String OTP_SESSION_INFO  = "sessionInfo";
    
    // Object TYPE
    public static final String FLAG_ALL = "ALL";

    public static final String OBJECT_CREATE_SET_BIZ = "bizObjList";
    public static final String OBJECT_CREATE_SET_BIZ_MSTR = "bizMasterObjList";
    public static final String OBJECT_CREATE_SET_REL = "relObjList";

    // From/to
    public static final String FLAG_TYPE_FROM = OmcFoundationConstant.OQL_DIRECTION_From;
    public static final String FLAG_TYPE_TO   = OmcFoundationConstant.OQL_DIRECTION_To;
    public static final String FLAG_TYPE_ALL  = "*";

    // Promote / Demote
    public static final String WORKFLOW_TYPE_PROMOTE = "Promote";
    public static final String WORKFLOW_TYPE_DEMOTE  = "Demote";

    // System Key
    public static final String SYSKEY_KIND_NAME_USER = "User";
    public static final String SYSKEY_KIND_NAME_ROLE = "Role";
    public static final String SYSKEY_KIND_NAME_GROUP = "Group";
    public static final String SYSKEY_KIND_NAME_ROLE_GROUP = "RoleGroup";
    public static final String SYSKEY_KIND_NAME_MENU  = "Menu";  

    // OQL Operator
    public static final String OQL_OPERATOR_EQUAL          = OmcFoundationConstant.OQL_OPERATOR_Equal;
    public static final String OQL_OPERATOR_NOT_EQUAL      = OmcFoundationConstant.OQL_OPERATOR_NotEqual;
    public static final String OQL_OPERATOR_LIKE           = OmcFoundationConstant.OQL_OPERATOR_Like;
    public static final String OQL_OPERATOR_NOT_LIKE       = OmcFoundationConstant.OQL_OPERATOR_NotLike;
    public static final String OQL_OPERATOR_GREATER_THAN   = OmcFoundationConstant.OQL_OPERATOR_GreaterThan;
    public static final String OQL_OPERATOR_LESS_THAN      = OmcFoundationConstant.OQL_OPERATOR_LessTan;
    public static final String OQL_OPERATOR_GREATER_EQTHAN = OmcFoundationConstant.OQL_OPERATOR_GreaterEqTan;
    public static final String OQL_OPERATOR_LESS_EQTHAN    = OmcFoundationConstant.OQL_OPERATOR_LessEqTan;
    public static final String OQL_OPERATOR_OR             = OmcFoundationConstant.OQL_OPERATOR_Or;
    public static final String OQL_OPERATOR_AND            = OmcFoundationConstant.OQL_OPERATOR_And;
    public static final String OQL_OPERATOR_IN             = OmcFoundationConstant.OQL_OPERATOR_In;
    public static final String OQL_OPERATOR_NOT_IN         = OmcFoundationConstant.OQL_OPERATOR_NotIn;
    public static final String OQL_DBMS_ORDERBY            = OmcFoundationConstant.OQL_ORDERBY_OrderBy;
    public static final String OQL_DBMS_GROUPBY            = OmcFoundationConstant.OQL_ORDERBY_GroupBy;
    
    public static final String[] OQL_OPERATOR_ALL_SET      = OmcFoundationConstant.OQL_OPERATOR_ALL_Set;
    
    // LOG - CHANGING_CATEGORY
    public static final String CHANGING_CATEGORY_API_CHAGNE_OWNER = "ChangeOwner";
    public static final String CHANGING_CATEGORY_API_CHECKOUT = "CheckOut";
    public static final String CHANGING_CATEGORY_API_CHECKIN_FILE = "CheckInFile";
    public static final String CHANGING_CATEGORY_API_CHECKOUT_FILE = "CheckOutFile";
    public static final String CHANGING_CATEGORY_API_CHECKOUT_FILE_FROM_SERVER = "CheckOutFileFromServer";


    /**
     * Name Generator (ID_KEY)
     */
    // Global Unique ID - name
    public static final String IDGEN_IDKEY_GLOBAL = "Global Unique Name";

    // obid
    public static final String IDGEN_IDKEY_OBID = "OBID";

    public static final String IDGEN_IDKEY_FILE = "File Name";

    public static final String IDGEN_IDKEY_WF_INBOXTASKNAME = "Workflow In Box Name";
    public static final String IDGEN_IDKEY_WF_ROUTENAME = "Workflow Route Name";
    public static final String IDGEN_IDKEY_WF_STEPNAME = "Workflow Step Name";

    
    public static final String IDGEN_IDKEY_PROJECT_NAME = "Project Name";
    public static final String IDGEN_IDKEY_WBS_ITEM_TEMPLATES = "WBS Item Templates";
    public static final String IDGEN_IDKEY_JOB_ACTIVITY_TEMPLATE = "Job Activity Template";
    public static final String IDGEN_IDKEY_ACTIVITY_TEMPLATE_MASTER_NAME = "Activity Template Master";
    public static final String IDGEN_IDKEY_WBS_TEMPLATE_MASTER_NAME = "WBS Template Master";
    public static final String IDGEN_IDKEY_PROJECT_ROLE = "Project Role";
    public static final String IDGEN_IDKEY_DEVELOPMENT_TYPE = "Development Type";
    public static final String IDGEN_IDKEY_WBS_PROJECT_PHASE = "WBS Project Phase";
    
    public static final String IDGEN_IDKEY_PROJECT_SCHEDULE = "Project Schedule";
    public static final String IDGEN_IDKEY_WBS_ITEMS = "WBS Items";
    public static final String IDGEN_IDKEY_WBS_JOB_ACTIVITY = "WBS Job Activity";
    
    public static final String IDGEN_IDKEY_PROJECT_DEFINED_ROLE = "Project Members";
    
    //
    public static final String IDGEN_IDKEY_PART_NAME = "Temporary Part Name";

    public static final String IDGEN_IDKEY_INTERFACE_ID = "Interface ID";
    
    
   
    
    /**
     * Variable Converting Flag
     */
    public static final String VARIABLE_CONVERTING_FLAG_STRING = "NO";

    public static final String VARIABLE_CONVERTING_FLAG_LONG = "C2L";

    public static final String VARIABLE_CONVERTING_FLAG_INTEGER = "C2I";

    public static final String SMARTCLIENT_FORM_KEY = "scData";

    /*
     * REST OR UI 통신시 사용되는 상수
     */
    public static final String M_STATUS_CODE = "statusCode";
    public static final String M_MESSAGE = "message";
    public static final String M_DATA = "data";
    public static final String M_RESULT = "cadResult";
    
    public static final String M_SYSTEM_ERROR_MESSAGE = "systemErrorMessage";

    public static final String D_FILES = "files";
    public static final String D_UPLOAD_ID = "uploadId";
    public static final String D_FILE_LOCATION_PATH = "fileLocationPath";

    /*
     * File 관련 상수
     */

    public static final String FILE_APPEND = "APPEND";
    public static final String FILE_REPLACE = "REPLACE";
    public static final String FILE_MODIFY = "MODIFY";
    public static final String FILE_DELETE = "DELETE";
    public static final String FILE_USERINPUT = "USERINPUT";
    public static final String[] FILE_MODE_LIST = {FILE_APPEND,FILE_REPLACE,FILE_MODIFY,FILE_DELETE,FILE_USERINPUT};

    public static final String FILE_RECOMODE_CREATE = "C";
    public static final String FILE_RECOMODE_UPDATE = "U";
    public static final String FILE_RECOMODE_DELETE = "D";
    public static final String FILE_RECOMODE_EQUAL  = "=";
    public static final String[] FILE_RECOMODE_LIST = {FILE_RECOMODE_CREATE,FILE_RECOMODE_UPDATE,FILE_RECOMODE_DELETE,FILE_RECOMODE_EQUAL};

    
    public static final boolean FILE_LOCK = true;
    public static final boolean FILE_UNLOCK = false;

    public static final String FILE_ALL_EXTENSION = "*.*";

    public static final String FILE_DELETE_ING_STATUS = "DELETE";
    public static final String FILE_DELETE_COMPLETE_STATUS = "COMPLETE";

    public static final String FILE_DELETED_DIR = "/delete";

    public static final String REVISE_OPTION_COPYFILE = "COPYFILE";


    /*
     * Multi-thread 관련 상수
     */
    public static final String THREAD_LOG_STARTED = "Started";

    public static final String THREAD_LOG_SUCCESS = "Success";

    public static final String THREAD_LOG_ERROR = "Error";

    public static final String THREAD_LOG_DONTCARE = "N/A";

    public static final String THREAD_EXECUTOR = "asyncExecutor";

    public static final String THREAD_EXECUTOR_PART = "partExecutor";

    public static final String THREAD_EXECUTOR_EBOM = "ebomExecutor";

    /*
     * login관련 상수
     */

    public static final String LOGIN_BACKDOOR_SUCCESS = "backdoorSuccess";

    public static final String LOGIN_BACKDOOR_FAIL = "backdoorFail";

    public static final String LOGIN_SSO_SUCCESS = "ssoSuccess";
    public static final String LOGIN_SSO_SUCCESS_TC = "ssoSuccessTc";
    public static final String LOGIN_SSO_SUCCESS_NO_TC = "ssoSuccessNoTc";
    public static final String LOGIN_SSO_SUCCESS_NO_TC_SITE = "ssoSuccessNoTcSite";

    public static final String LOGIN_SSO_FAIL = "ssoFail";

    public static final String LOGIN_WS_SUCCESS = "webServiceSuccess";
    public static final String LOGIN_WS_FAIL = "webServiceFail";


    /*
     * Spring Profile - 로컬 / 개발 / 운영 환경 구분
     */
    public static final String SPRING_PROFILE_LOCAL = "local";
    public static final String SPRING_PROFILE_EDU = "edu";
    public static final String SPRING_PROFILE_DEV = "dev";
    public static final String SPRING_PROFILE_PROD = "prod";
    
    /*
     * Workflow Constants
     */
    public static final Date nullDate = null;
    
    public static final String PROJECT_LIFECYCLE_NPI = "NPI"; // project 
    
    public static final String CREATE_RECORD_MODE = "C";
    public static final String UPDATE_RECORD_MODE = "U";
    public static final String DELETE_RECORD_MODE = "D";
    
    
    public static final String MAP_OUPDATA_OLD_OBJECT_KEY  = OmcUniqueIDGenerator.getObid();
    public static final String IDGEN_IDKEY_EXCEL_DOWNLOAD_LOG = "ExcelDownloadLog";
}
