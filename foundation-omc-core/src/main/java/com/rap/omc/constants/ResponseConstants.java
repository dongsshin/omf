/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ResponseConstants.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 16.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.constants;


/**
 * <pre>
 * Class : ResponseConstants
 * Description : TODO
 * </pre>
 * 주의)1000번 이상일 경우에만 client layer쪽으로 message code를 전달 함.
 * @author jongjung.kwon
 */
public class ResponseConstants {
    
    public static final String ID_UNKNOWN = "ID_UNKNOWN";

    public static final int STATUS_SUCCESS = 0;
    
    public static final int STATUS_FCS_FILE_COPY_ERROR   = 1021;
    public static final int STATUS_FCS_CONTROLLER   = 1020;
    public static final int STATUS_RECODE_MODE_NULL = 1019;
    public static final int STATUS_ASSIGN_TYPE_NULL = 1018;
    public static final int STATUS_DELETED_OBJECT = 1017;
    public static final int STATUS_LIFECYCLE_OBID_NULL = 1016;
    public static final int STATUS_NO_JSON_DATA = 1015;
    public static final int STATUS_JSON_CONVERT_ERROR = 1014;
    public static final int STATUS_NO_META = 1013;
    public static final int STATUS_CANNOT_FOUND_LOCATION = 1012;
    public static final int STATUS_CANNOT_FOUND_STORE = 1011;
    public static final int STATUS_FILE_EXISTS_NO_META = 1010;
    public static final int STATUS_NOFILE_RECODE_MODE_C = 1009;
    public static final int STATUS_FILE_EXIST_RECODE_MODE_UD = 1008;
    public static final int STATUS_NOFILE_RECODE_MODE_EXISTS = 1007;
    public static final int STATUS_NULL_RECODE_MODE = 1006;
    public static final int STATUS_FILE_VALIDATION_ERROR = 1005;
    public static final int STATUS_FILE_POLICY_NULL = 1004;
    public static final int STATUS_USER_ID_INVALID = 1003;
    public static final int STATUS_BUSINESS_UPLOAD_NO_FILE = 1002;
    public static final int STATUS_BUSINESS_UPLOAD_EXCEPTION = 1001;
    public static final int STATUS_BUSINESS_DEFAULT_ERROR = 1000;
    public static final int STATUS_SYSTEM_DEFAULT_ERROR = 999;
    
    //화면단에서 Error 처리를 위한 Constant
    public static final int STATUS_BUNINESS_SPECIFIC_ERROR = 1100;

    public static final int STATUS_FAILURE = -1;
    public static final int STATUS_LOGIN_REQUIRED = -7;  
    public static final int STATUS_AUTHORIZATION_FAIL = -8;
    public static final int STATUS_MAX_FILE_SIZE_EXCEEDED = -11;
    public static final int STATUS_MAX_POST_SIZE_EXCEEDED = -12;
    public static final int STATUS_TRANSACTION_FAILED = -10;
    public static final int STATUS_UNKNOWN_HOST_ERROR = -91;
    public static final int STATUS_VALIDATION_ERROR = -4;
    public static final int STATUS_TRIGGER_ERROR = 1100;

}
