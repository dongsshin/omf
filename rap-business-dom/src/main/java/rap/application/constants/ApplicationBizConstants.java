package rap.application.constants;

import com.rap.omc.schema.util.OmcSystemConstants;

public class ApplicationBizConstants {

    /**
     * user session info
     */
//    public static String SESSION_USER_INFO = "loginUser";
    public static String SESSION_USER_INFO = "userSession";
    public static final String DEFAULT_LANG = OmcSystemConstants.OMC_LOCALE_LANG_EN;
    public static final String LANG_EN = OmcSystemConstants.OMC_LOCALE_LANG_EN;
    public static final String LANG_KO = OmcSystemConstants.OMC_LOCALE_LANG_KO;
    public static final String LANG_CH = OmcSystemConstants.OMC_LOCALE_LANG_CH;
    public static final String[] LANG_VALID_SET = {LANG_EN,LANG_KO,LANG_CH};

    //public static final String LANG_CH = "ch";

    public static final String BUSINESS_UNIT_VC = "VC";
    public static final String BUSINESS_UNIT_MC = "MC";
    public static final String BUSINESS_UNIT_HE = "HE";

    // business unit code
    public static final String BUSINESS_UNIT_CODE_HA = "DA";

    public static final String BUSINESS_UNIT_SYSTEM_PROP_VC = "vc";
    public static final String BUSINESS_UNIT_SYSTEM_PROP_MC = "mc";

    public static final String DIVISION_WWZ = "WWZ";
    public static final String DIVISION_IPZ = "IPZ";
    public static final String DIVISION_PCZ = "PCZ";

    public static final String DIVISION_PGZ = "PGZ";
    public static final String DIVISION_PLZ = "PLZ";
    public static final String DIVISION_VSZ = "VSZ";
    public static final String DIVISION_VLZ = "VLZ";
    public static final String DIVISION_EVZ = "EVZ";
    public static final String DIVISION_VBZ = "VBZ";

    public static final String[] DIVISION_SMARTS = {DIVISION_PGZ,DIVISION_PLZ,DIVISION_VSZ};
    
    public static final String DIVISION_PNZ = "PNZ";
    public static final String DIVISION_CVZ = "CVZ";
    public static final String DIVISION_CDZ = "CDZ";
    public static final String DIVISION_DVZ = "DVZ";
    public static final String DIVISION_PHZ = "PHZ";
    public static final String DIVISION_OMZ = "OMZ";
    public static final String DIVISION_CNZ = "CNZ";

    public static final String DIVISION_GMZ = "GMZ";
    public static final String DIVISION_GLZ = "GLZ";
    
    public static final String DIVISION_CWZ= "CWZ";

    public static final String DIVISION_GHS052 = "GHS052";
    public static final String DIVISION_GHO061 = "GHO061";

    public static final String DIVISION_GHO010 = "GHO010"; //생산기술원
    public static final String DIVISION_GHS053 = "GHS053"; //PRI 금형기술센터
    public static final String DIVISION_GHS105 = "GHS105"; //융복합    
    
    public static final String VARITYPE_MC = "MC";
    public static final String VARITYPE_HE = "HE";
    public static final String VARITYPE_SKU = "SKU";
    public static final String VARITYPE_COMPSKU = "CSKU";
    public static final String VARITYPE_VCSKU = "VSKU";
    
    //Module Name
    public static final String MODULE_NAME_PMS = "PMS";
    public static final String MODULE_NAME_PDR = "PDR";
    public static final String MODULE_NAME_PRODPLAN = "PRODPLAN";
    public static final String MODULE_NAME_PDMS = "Part DMS";

    public static final String AFFILIATE_EKHQ = "EKHQ";
    public static final String AFFILIATE_EEAT = "EEAT";

    public static final String ITF_DATA_SOURCE = "itfSqlSessionFactory";
    public static final String PMSITF_DATA_SOURCE = "pmsEaiSqlSessionFactory";
    public static final String PRMSITF_DATA_SOURCE = "prmsEaiSqlSessionFactory";
    public static final String ITF_MASS_DATA_SOURCE = "itfsqlMassSessionFactory";
    public static final String MASS_DATA_SOURCE = "sqlMassSessionFactory";
    public static final String PMSPROD_DATA_SOURCE = "pmsProdSqlSessionFactory";
    public static final String RMSPROD_DATA_SOURCE = "rmsProdSqlSessionFactory";
    public static final String MIGPROD_DATA_SOURCE = "prmsEaiSqlSessionFactory";
    //public static final String GERP_DATA_SOURCE = "gerpSqlSessionFactory";
    //public static final String CREO_DATA_SOURCE = "creoSqlSessionFactory";

    public static final String MODEL_ATTRIBUTEGROUP_PREFIX = "modelAttrGrp";

    public static final String BASIC_FONT = "맑은 고딕";

    public static final String DEFAULT_TIME_ZONE = "Asia/Seoul";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATETIME_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_DATETIME_STRING_FORMAT = "yyyyMMddHHmmss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_YYYYMMDD_FORMAT = "yyyyMMdd";

    public static final String DATE_TYPE_JAVA = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TYPE_JAVA_EXCLUDE_TIME = "yyyy-MM-dd";

    // Presentation Trace Point Name
    public static final String CHANNEL_START = "▶[CHI]LAYER_START";
    public static final String CHANNEL_END = "▶[CHI]LAYER_END";

    // Act/Operation Type
    public static final String ACT_TYPE_CREATE = "create";
    public static final String ACT_TYPE_UPDATE = "update";
    public static final String ACT_TYPE_DELETE = "delete";
    public static final String ACT_TYPE_EXCHANGE = "exchange";

    // Interface Type
    public static final String INF_TYPE_ERP = "ERP";
    public static final String INF_TYPE_ERP_BACK = "ERP_BACK";

    // Delimiter
    public static final String DELIM_1 = ":";
    public static final String DELIM_1_S = "\\:";
    public static final String DELIM_2 = ",";
    public static final String DELIM_2_S = "\\,";

    public static final String INF_TYPE_NONE = "NONE";

    // Company
    public static final String COMPANY_LGE = "LGE";

    // System
    public static final String TIME_ZONE_LIST = "TIME_ZONE_LIST";

    // Teamcenter
    public static final String TC_SITE_MASTER_LIST = "TC_SITE_MASTER_LIST";

    // Code Master
    public static final String MASTER_CODE_MC_CUSTOMER = "MC_CUSTOMER";
    public static final String MASTER_CODE_PROJECT_CLASS = "PROJECT_CLASS";

    public static final String WORKFLOW_ACTIVITY_MAP_KEY_projectObid        = "projectObid";
    public static final String WORKFLOW_ACTIVITY_MAP_KEY_projectName        = "projectName";
    public static final String WORKFLOW_ACTIVITY_MAP_KEY_scheduleObid       = "scheduleObid";
    public static final String WORKFLOW_ACTIVITY_MAP_KEY_activityObid       = "activityObid";
    public static final String WORKFLOW_ACTIVITY_MAP_KEY_phaseCode          = "phaseCode";
    public static final String WORKFLOW_ACTIVITY_MAP_KEY_phaseTitles        = "phaseTitles";
    public static final String WORKFLOW_ACTIVITY_MAP_KEY_phaseCodeSystem    = "phaseCodeSystem";
    public static final String WORKFLOW_ACTIVITY_MAP_KEY_phaseTitlesSystem  = "phaseTitlesSystem";

    /*
    public static final String MASTER_CODE_ECAD_DOCUMENT = "ECAD Document Type";
    public static final String MASTER_CODE_MCAD_DOCUMENT_REGULAR = "MCAD Document Type-Regular";
    public static final String MASTER_CODE_MCAD_DOCUMENT_TEMPORARY = "MCAD Document Type-Temporary";
    public static final String MASTER_CODE_SOFTWARE_DOCUMENT = "Software Document Type";
    public static final String MASTER_CODE_ETC_DOCUMENT = "Technical Document Type";
    public static final String MASTER_CODE_SW_TEST_TYPE_MAPPING = "SW Test Type Mapping";
    public static final String MASTER_CODE_CHANGE_APPROVER = "Change Approver";

    public static final String MASTER_CODE_CAE_SIMULATION_ITEM = "CAE_SIMULATION_ITEM";
    public static final String MASTER_CODE_CAE_FORM_FACTOR ="CAE_FORM_FACTOR";
    public static final String MASTER_CODE_CAE_TIER = "CAE_TIER";
    public static final String MASTER_CODE_CAE_EVENT = "CAE_EVENT";
    public static final String MASTER_CODE_CAE_MILESTONE = "CAE_MILESTONE";
    public static final String MASTER_CODE_CAE_MATERIAL_SPEC = "CAE_MATERIAL_SPEC";
    public static final String MASTER_CODE_CAE_MATERIAL_RESIN_GRADE_VALUES = "CAE_MATERIAL_RESIN_GRADE_VALUES";
    public static final String MASTER_CODE_CAE_COLOR_VALUES = "CAE_COLOR_VALUES";
    public static final String MASTER_CODE_CAE_PATTERN_VALUES = "CAE_PATTERN_VALUES";
    public static final String MASTER_CODE_CAE_POSTPRESS_VALUES = "CAE_POSTPRESS_VALUES";
    public static final String MASTER_CODE_CAE_RUNNER_VALUES = "CAE_RUNNER_VALUES";
    public static final String MASTER_CODE_CAE_COMMON_SPEC = "CAE_COMMON_SPEC";
    public static final String MASTER_CODE_CAE_HW_SPEC = "CAE_HW_SPEC";
    public static final String MASTER_CODE_CAE_HW_THERMAL_SPEC = "CAE_HW_THERMAL_SPEC";
    public static final String MASTER_CODE_CAE_FALL_SPECS = "CAE_FALL_SPEC";
    public static final String MASTER_CODE_CAE_FALL_SPEC_DETAIL_VALUES = "CAE_FALL_SPEC_DETAIL_VALUES";
    public static final String MASTER_CODE_CAE_SOLUTION_SPEC = "CAE_SOLUTION_SPEC";
    public static final String MASTER_CODE_CAE_HW_POWER_SPEC = "CAE_HW_POWER_SPEC";
    public static final String MASTER_CODE_CAE_BENDING_SPEC = "CAE_BENDING_SPEC";
    public static final String MASTER_CODE_CAE_RESULT_ITEM = "CAE_RESULT_ITEM";
    public static final String CAE_RESULT_ITEM_INPUT_TYPE = "INPUT";
    public static final String CAE_RESULT_ITEM_OUTPUT_TYPE = "OUTPUT";
    public static final String CAE_REPORT_FILE_MANDATORY_TYPE ="CAE_REPORT";
    public static final String CAE_REPORT_FILE_NORMAL_TYPE ="GPDM";
    public static final String CAE_REPORT_FILE_MESH_TYPE ="CAE_MESH";
    */

    // Common Code
    public static final String CODE_RANGE_LIST = "RangeList";
    public static final String CODE_COMPANY_LIST = "COMPANY_LIST";
    public static final String CODE_BUSINESS_LIST = "BUSINESS_LIST";
    public static final String CODE_DIVISION_LIST = "DIVISION_LIST";
    public static final String DIVISION_LIST_ALL = "DIVISION_LIST_ALL";
    public static final String CODE_AFFILIATE_LIST = "AFFILIATE_LIST";
    public static final String USER_MANAGEMENTGROUP_LIST = "USER_MANAGEMENTGROUP_LIST";
    public static final String CODE_RESEARCH_CENTER_LIST = "RESEARCH_CENTER_LIST";
    public static final String CODE_PLANT_LIST_ALL = "PLANT_LIST_ALL";
    public static final String CODE_PLANT_LIST = "PLANT_LIST";
    public static final String USER_PLANT_LIST = "USER_PLANT_LIST";
    public static final String USER_TC_SITE_URL = "USER_TC_SITE_URL";
    public static final String USER_DIVISION_LIST = "USER_DIVISION_LIST";
    public static final String USER_BUSINESS_UNIT_LIST = "USER_BUSINESS_UNIT_LIST";
    public static final String USER_COMPANY_LIST = "USER_COMPANY_LIST";
    public static final String USER_DIVISION_UNIT_LIST = "USER_DIVISION_UNIT_LIST";
    public static final String USER_PLANT_UNIT_LIST = "USER_PLANT_UNIT_LIST";
    public static final String USER_RESEARCH_CENTER_LIST = "USER_RESEARCH_CENTER_LIST";
    public static final String CODE_CLASS_NAME_LIST = "CLASS_NAME_LIST";
    public static final String CODE_POLICY_LIST = "POLICY_LIST";
    public static final String CODE_STATES_LIST = "STATES_LIST";
    public static final String CODE_OME_LIST = "CODE_OME_LIST";
    public static final String CODE_ANOTHER_LIST = "ANOTHER_LIST";

    public static final String CODE_NAME_EVENT = "Event";
    public static final String CODE_NAME_MODULE = "Module";
    public static final String CODE_NAME_ECO_TYPE = "ECO Type";
    public static final String CODE_NAME_CAUSE_DEPARTMENT = "Cause Department";
    public static final String CODE_NAME_PRODUCT_GROUP = "Product Div";
    public static final String CODE_NAME_ITEM_WORK_GROUP = "ITEM_WORK_GROUP";
    public static final String CODE_NAME_CHANGE_GRADE = "Change Grade";
    public static final String CODE_NAME_INVENTORY_GUIDE = "Inventory Guide";
    public static final String CODE_NAME_AUTH_TYPE = "ECO_CHANGE_AUTH_ROLE";
    public static final String CODE_SUB_CODE_MASTER = "subCodeMasterList";
    public static final String CODE_NAME_DISTRIBUTION_GROUP = "Distribution Group";
    public static final String CODE_NAME_CHANGE_REASON = "Change Reason";
    public static final String CODE_NAME_CHANGE_TARGET = "Change Target";
    public static final String CODE_NAME_ESSENTIAL_APPROVER_CHECK_TYPE = "ESSENTIAL_APPROVER_CHECK_TYPE";
    public static final String CODE_NAME_MODE = "Mode";
    public static final String CODE_NAME_IMPROVEMENTS_NOTIFICATION = "SW Improvements Notification";
    public static final String CODE_NAME_OS_VERSION = "OS Version";
    public static final String CODE_NAME_PC_OS_VERSION = "PC OS Version";
    public static final String CODE_NAME_APPLICATION_NAME = "Application Name";
    public static final String CODE_NAME_PC_SYNC_TYPE = "PC Sync Type";
    public static final String CODE_NAME_USB_DRIVER_TYPE = "USB Driver Type";
    public static final String CODE_NAME_ANDROID_FOR_WORK_TYPE = "Android For Work Type";
    public static final String CODE_NAME_ANDROID_FOR_WORK_METHODS = "Android For Work Methods";
    public static final String CODE_NAME_FOTA_CLIENT_ID_TYPE = "SW FOTA CLIENT ID TYPE";
    public static final String CODE_NAME_OSP_TYPE = "OSP Type";
    public static final String CODE_NAME_SW_CHANGE_GRADE = "SW Change Grade";
    public static final String CODE_NAME_SW_WEB_UPDATE_TOOL = "SW WEB UPDATE TOOL";
    public static final String CODE_NAME_SW_ACS_TEMPLATE = "SW_ACS_TEMPLATE";
    public static final String CODE_NAME_SW_ACS_BUYER_CODE = "SW_ACS_BUYER_CODE";
    public static final String CODE_NAME_MT_DEP_MANAGER = "MT Dep Manager";
    public static final String CODE_NAME_RUN_MODE = "RUN_MODE";
    public static final String CODE_NAME_SW_TEST_REQUEST_HELP = "SW Test Request Help";
    public static final String CODE_NAME_ECO_APPROVER_ROLE = "ECO_APPROVER_ROLE";
    public static final String CODE_NAME_INTERFACE_ERROR_MAIL_RECEIVER = "INTERFACE_ERROR_MAIL_RECEIVER";
    public static final String CODE_NAME_LONGTERM_DELAY_BASE_DAYS = "LONGTERM_DELAY_BASE_DAYS";     // 2016.07.07 youngmi.won [C20160429_51332] ECO 지연 메일링 기준 변경

    public static final String CODE_NAME_SW_APP_TYPE = "SW App Type";
    public static final String CODE_NAME_SW_APP_TOOL = "SW App Tool";
    public static final String CODE_NAME_MFG_APP_TYPE = "MFG App Type";
    public static final String CODE_NAME_RF_TEST = "RF Test";

    public static final String CODE_NAME_PCO_TYPE_GENERAL = "G";
    public static final String CODE_NAME_PCO_TYPE_MASS = "M";

    public static final String CODE_ITEM_WORK_GROUP_ADMIN = "A";
    public static final String CODE_ITEM_WORK_GROUP_HW = "C";
    public static final String CODE_ITEM_WORK_GROUP_MECHANICAL = "T";
    public static final String CODE_ITEM_WORK_GROUP_PACKAGE = "P";
    public static final String CODE_ITEM_WORK_GROUP_MANUAL = "V";
    public static final String CODE_CHANGE_REASON_DUALIZE_MATERIAL = "N41";

    public static final String CODE_ECO_PPT_COMMON_CODE_1 = "ECO_PPT_COMMON_CODE_1";
    public static final String CODE_ECO_PPT_COMMON_CODE_2 = "ECO_PPT_COMMON_CODE_2";
    public static final String CODE_ECO_PPT_COMMON_CODE_3 = "ECO_PPT_COMMON_CODE_3";
    public static final String CODE_ECO_PPT_ADJUST_COST = "ECO_PPT_ADJUST_COST";
    public static final String CODE_ECO_PPT_DIE_MODIFICATION = "ECO_PPT_DIE_MODIFICATION";
    public static final String CODE_ECO_PPT_DISPOSAL_METHOD = "ECO_PPT_DISPOSAL_METHOD";
    public static final String CODE_ECO_PPT_GLOBAL_ONE_PLATFORM = "ECO_PPT_GLOBAL_ONE_PLATFORM";
    public static final String CODE_ECO_PPT_TRIAL_PART_INSPECTION = "ECO_PPT_TRIAL_PART_INSPECTION";

    public static final String CODE_PCB_ASSY_TYPE = "PCBAssyType";
    public static final String CODE_PMS_EVENT = "PMS Event";
    public static final String CODE_ECAD_PCB_TYPE = "ECAD_PCB_TYPE";
    public static final String CODE_PRODUCT_LINE = "Product Line";
    public static final String CODE_CHIP_TYPE = "Chip Type";
    public static final String CODE_NEED_TEST_OR_COLLABORATION = "Need Test or Collaboration";
    public static final String CODE_TEST_FLAG_NEED_MANUAL_PROMOTE = "Y";
    public static final String CODE_TEST_FLAG_NEED_AUTO_PROMOTE = "Z";
    public static final String CODE_TEST_FLAG_NEEDLESS = "N";
    public static final String CODE_TEST_FLAG_COMPLETED = "C";
    public static final String CODE_ECAD_PCB_PURPOSE = "ECAD_PCB_PURPOSE";
    public static final String CODE_ECAD_PCB_EVENT = "PCB Event";
    public static final String CODE_ECAD_CAE_ANALYSIS_TYPE = "PDEV_CAE_ANALYSIS_TYPE";
    public static final String CODE_PDEV_PCB_MATERIAL = "PDEV_PCB_MATERIAL";
    public static final String CODE_PDEV_PCB_THICKNESS = "PDEV_PCB_THICKNESS";
    public static final String CODE_PDEV_LGEPCBLAYER = "PDEV_LGEPCBLAYER";
    public static final String CODE_PDEV_ARRAY_TYPE = "PDEV_ARRAY_TYPE";
    public static final String CODE_PDEV_STENCIL_DIVIDING = "PDEV_STENCIL_DIVIDING";
    public static final String CODE_PDEV_MAIN_SUB_DIVIDING = "PDEV_MAIN_SUB_DIVIDING";
    public static final String CODE_PDEV_LGEPCBSOLDERING = "PDEV_LGEPCBSOLDERING";
    public static final String CODE_PDEV_THROUGH_HOLE_TREATMENT = "PDEV_THROUGH_HOLE_TREATMENT";
    public static final String CODE_PDEV_CARBON_TREATMENT = "PDEV_CARBON_TREATMENT";
    public static final String CODE_PDEV_SR_COLOR = "PDEV_SR_COLOR";
    public static final String CODE_PDEV_MAKING_COLOR = "PDEV_MAKING_COLOR";
    public static final String CODE_PDEV_PCB_SURFACE_TREATMENT = "PDEV_PCB_SURFACE_TREATMENT";
    public static final String CODE_PDEV_LGESAMPLEWAREHOUSINGPLACE = "PDEV_LGESAMPLEWAREHOUSINGPLACE";
    public static final String CODE_PDEV_REQUEST_ITEM = "PDEV_REQUEST_ITEM";
    public static final String CODE_PDEV_ANALYSIS_TYPE = "PDEV_ANALYSIS_TYPE";
    public static final String CODE_PDEV_LGESTENCILTHICKNESS = "PDEV_LGESTENCILTHICKNESS";
    public static final String CODE_PDEV_PCB_LAYER = "PDEV_PCB_LAYER";
    public static final String PDEV_PCB_CPC_DOCUMENT = "PDEV_PCB_CPC_DOCUMENT";
    public static final String CODE_PDEV_MINIMUM_HOLE_SIZE = "PDEV_MINIMUM_HOLE_SIZE";
    public static final String CODE_PDEV_SAMPLE_QUANTITY_UNIT = "SampleQuantityUnit";
    public static final String CODE_PDEV_PCB_DEVELOPE_GRADE = "Development Grade";
    public static final String CODE_PDEV_PCB_REQUEST_TYPE = "PCB Design Request Type";
    public static final String CODE_PDEV_PCB_REQUEST_PRODUCT_LINE = "PCB Design Request Product Line";
    public static final String CODE_PDEV_PCB_PHONE_TYPE = "Phone Type";
    public static final String CODE_PDEV_PACKAGE_MODEL_TIER = "Package Model Tier";
    public static final String CODE_PDEV_PACKAGE_MODEL_DEV_GRADE = "Package Model Dev Grade";
    public static final String CODE_PDEV_PACKAGE_INBOX_ITEM = "Package Inbox Item";
    public static final String CODE_PDEV_PACKAGE_BOX_TYPE = "Package Box Type";
    public static final String CODE_PDEV_PACKAGE_REQUEST_REGION = "Package Request Region";
    public static final String CODE_PDEV_PLACEMENT = "Placement";
    public static final String CODE_PDEV_DEVELOPMENTLAB = "Development Lab";

    //2016.03.24 VDEV008 ECR 필수결재자 추가
    public static final String CODE_NAME_OEM_TYPE = "OEM Type";
    public static final String CODE_NAME_ISSUE_SOURCE_TYPE = "Issue Source Type";

    //Chang Auth Type
    public static final String CHANGE_AUTH_TYPE_MM_ADMIN = "MM_ADMIN";
    public static final String CHANGE_AUTH_TYPE_CODE_ADMIN = "CHANGE_AUTH_CODE_ADMIN";
    public static final String CHANGE_AUTH_TYPE_DIVISION_APPR = "DIVISIONAPPROVER";
    public static final String CHANGE_AUTH_TYPE_ORG_FILE_DOWNLOAD = "ORIGINAL_FILE_DOWNLOAD";
    public static final String CHANGE_AUTH_TYPE_PROJECT_ADMIN = "PROJECT_ADMIN";
    public static final String CHANGE_AUTH_TYPE_SKU_VARI_ADMIN = "SKU_VARI_ADMIN";
    public static final String  CHANGE_AUTH_TYPE_SETTLEMENT_ADMIN = "SETTLEMENT_ADMIN";
    public static final String  CHANGE_AUTH_TYPE_BOARD_ADMIN = "BOARD_ADMIN";
    public static final String  CHANGE_AUTH_TYPE_CP_DOCUMENT_VIEW = "CP_DOCUMENT_VIEW";
    /*
    public static final String CHANGE_AUTH_TYPE_DIST_DEPT = "CHANGE_AUTH_DDP";
    public static final String CHANGE_AUTH_TYPE_FAR = "CHANGE_AUTH_FAR";
    public static final String CHANGE_AUTH_TYPE_FARFR = "CHANGE_AUTH_FARFR";
    public static final String CHANGE_AUTH_TYPE_ECAD = "CHANGE_AUTH_ECAD";
    public static final String CHANGE_AUTH_TYPE_MCAD = "CHANGE_AUTH_MCAD";
    public static final String CHANGE_AUTH_TYPE_BOMA = "CHANGE_AUTH_BOMA";
    public static final String CHANGE_AUTH_TYPE_SW = "CHANGE_AUTH_SW";
    public static final String CHANGE_AUTH_TYPE_PDEV = "CHANGE_AUTH_PDEV";
    public static final String CHANGE_AUTH_TYPE_CCR = "CHANGE_AUTH_CCR";
    public static final String CHANGE_AUTH_TYPE_SCR = "CHANGE_AUTH_SCR";
    public static final String CHANGE_AUTH_TYPE_ERPS = "CHANGE_AUTH_ERPS";
    public static final String CHANGE_AUTH_TYPE_MBRPS = "CHANGE_AUTH_MBRPS";
    public static final String CHANGE_AUTH_TYPE_AEC = "CHANGE_AUTH_AEC";
    public static final String CHANGE_AUTH_TYPE_DOCAUTH = "CHANGE_AUTH_DOC_AUTH";
    public static final String CHANGE_AUTH_TYPE_ECAD_LIBRARIAN = "CHANGE_AUTH_ECAD_LIBRARIAN";
    public static final String CHANGE_AUTH_TYPE_MANUAL_2NDAPPROVER = "MANUAL_2NDAPPROVER";
    public static final String CHANGE_AUTH_SW_TEAM_LEADER = "SW_TEAM_LEADER";
    public static final String CHANGE_AUTH_TYPE_SCA = "CHANGE_AUTH_SCA";
    public static final String CHANGE_AUTH_APPROVER = "CHANGE_AUTH_APPROVER";
    public static final String CHANGE_AUTH_CHANGE_SELF_APPROVER = "CHANGE_AUTH_CSA";
    public static final String CHANGE_AUTH_SDM_ADMIN = "CHANGE_AUTH_SDMA";
    public static final String CHANGE_AUTH_SDM_USER = "CHANGE_AUTH_SDMU";
    public static final String CHANGE_AUTH_PRODUCT_REQ_APPROVER = "CHANGE_AUTH_PRODUCT_REQ_APPROVER";
    public static final String CHANGE_AUTH_TYPE_CKD = "CHANGE_AUTH_CKD";
    public static final String CHANGE_AUTH_MER = "CHANGE_AUTH_MER";
    public static final String CHANGE_AUTH_PDCMM = "CHANGE_AUTH_PDCMM";
    public static final String CHANGE_AUTH_NOTIFY_OEM_EVENT = "NOTIFY_OEM_EVENT";
    */
    //Change Auth Role
    //public static final String AUTH_ROLE_CHANGE_SELF_APPROVER = "Change Self Approver";
    //public static final String AUTH_ROLE_BOM_VALIDATION_ADMIN = "BOM_VALIDATION_ADMIN";
    //public static final String AUTH_ROLE_BOM_ADMIN = "BOM_ADMIN";
    //public static final String AUTH_ROLE_SDM_STAFF = "STAFF";

    //2016.03.24 VDEV008 ECR 필수결재자 추가
    //public static final String CHANGE_AUTH_ECR = "CHANGE_AUTH_ECR";

    /*
     * [C20150909_69402]PCB Design Request WorkFlow Review 추가 요청 건
     */
    //public static final String CHANGE_AUTH_TYPE_PDRA = "CHANGE_AUTH_PDRA";

    // Mail Type
    public static final String MAIL_TYPE_NAME_ECO_DELAY = "ECO Delay";
    public static final String MAIL_TYPE_NAME_DELEGATE = "Delegate Information";
    public static final String MAIL_TYPE_NAME_DELEGATE_FAIL = "Delegate Fail Information";
    public static final String MAIL_TYPE_NAME_DISTRIBUTION = "Distribution";
    public static final String MAIL_TYPE_NAME_SCO_WORK = "Site ECO Coordinator Work";
    public static final String MAIL_TYPE_NAME_MBRPS = "MODEL BOM Distribution";
    public static final String MAIL_TYPE_NAME_USER_LOGIN_ALERT = "User Login History Alert";
    public static final String MAIL_TYPE_NAME_PERMISSION_EXPIRATION = "Permission Expiration";
    public static final String MAIL_TYPE_NAME_PERMISSION_EXPIRATION_NOTIFIY = "Permission Expiration Notify";

    // Mail Send Type
    public static final String MAIL_SEND_TYPE_SYSTEM = "System";
    public static final String MAIL_SEND_TYPE_MANUAL = "Manual";

    // Role Name
    public static final String ROLE_NAME_SYSTEM_ADMIN = "System Administration Role";
    public static final String ROLE_NAME_SYSTEM_BIZ_ADMIN = "G-0000000000174";
    
    //DQMS Attribute
    public static final String DQMS_PREFIX = "DQMS-";
    public static final String DQMS_USER_FLAG_TRUE = "TRUE";
    public static final String DQMS_USER_FLAG_FALSE = "FALSE";
    public static final String DQMS_USER_FLAG_Y = "Y";
    public static final String DQMS_USER_FLAG_N = "N";

    //Part Test Request
    public static final String DQMS_PART_REV_SYS_CODE_TYPE = "SYS_CODE_TYPE";
    public static final String DQMS_PART_REV_PDEV = "PDEV";
    public static final String DQMS_PART_REV_REV_PART_DOC = "PART_DOC";
    public static final String DQMS_PART_NAME_TEST_CATEGORY_INFO = "lgeDQMSTestCategoryInfo";
    public static final String DQMS_PART_NAME_NAME_PART0004 = "PART0004";
    public static final String DQMS_PART_NAME_NAME_PART0001 = "PART0001";

    //Product Approval Test Request
    public static final String DQMS_PROD_REV_DQMS = "DQMS";
    public static final String DQMS_PROD_REV_ETC = "ETC";
    public static final String DQMS_PROD_REV_DOC = "DOC";
    public static final String DQMS_PROD_EVENT_NAME = "lgeDQMSEvent";
    public static final String DQMS_PROD_GRADE_NAME = "lgeDQMSGrade";
    public static final String DQMS_PROD_PRODUCT_GROUP_NAME = "lgeDQMSProductGroup";
    public static final String DQMS_PROD_TEST_TYPE_NAME = "lgeTestType";
    public static final String DQMS_PROD_ESSN_DOC_NAME = "lgeDQMSStdDocInfo";

    //SW Test Request
    public static final String DQMS_SW_EVENT_NAME         = "DQMS-Event";
    public static final String DQMS_SW_GRADE_NAME         = "DQMS-Model Grade";
    public static final String DQMS_SW_PRODUCT_GROUP_NAME = "DQMS-Product Group";
    public static final String DQMS_SW_TEST_TYPE_NAME     = "DQMS-Test Type";

    //Part Development Review Request
    public static final String VALUE_COST_SHEET_ATTACHED  = "(Estimated cost of Parts)";

    // Request Code
    public static final String CODE_REQUEST_MODEL_TYPE = "Request Model Type";
    public static final String CODE_PDEV_LGECURRENCY = "PDEV_LGECURRENCY";
    public static final String CODE_KEY_PART_GRADE_TYPE = "KeyPartGradeType";
    public static final String CODE_KEY_PART_GRADE = "KeyPartGrade";
    public static final String CODE_MOLD_TYPE = "Mold Type";
    public static final String CODE_MOLD_REQUEST_TYPE = "Mold Request Type";
    public static final String CODE_MOLD_DOMETIC_FLAG = "Domestic Export Type";
    public static final String CODE_PREDICTED_QUANTITY_UNIT_TYPE = "Predicted Quantity Unit";

    // Model Code
    public static final String CODE_MC_MODEL_COLOR = "MC_MODEL_COLOR";
    public static final String CODE_MC_MODEL_CUSTOMER = "MC_MODEL_CUSTOMER";
    public static final String CODE_BATTERY_CLASS = "Battery Class";

    // Workflow
    public static final String[] INBOX_STATES_ALL = {ApplicationSchemaConstants.STATE_INBOX_TASK_COMPLETE,ApplicationSchemaConstants.STATE_INBOX_TASK_ASSIGNED,ApplicationSchemaConstants.STATE_INBOX_TASK_REVIEW};
    
    public static final String APPROVAL_STATUS_NONE = "None";
    public static final String APPROVAL_STATUS_APPROVE = "Approve";
    public static final String APPROVAL_STATUS_REJECT = "Reject";
    public static final String APPROVAL_STATUS_ACKNOWLEDGE = "Acknowledge";
    public static final String APPROVAL_STATUS_COMPLETE = "Complete";
    public static final String[] APPROVAL_STATUS_ALL = {APPROVAL_STATUS_NONE,APPROVAL_STATUS_APPROVE,APPROVAL_STATUS_REJECT,APPROVAL_STATUS_ACKNOWLEDGE,APPROVAL_STATUS_COMPLETE};
    public static final String[] APPROVAL_STATUS_SET_FORAPPROVED = {APPROVAL_STATUS_APPROVE,APPROVAL_STATUS_REJECT,APPROVAL_STATUS_ACKNOWLEDGE,APPROVAL_STATUS_COMPLETE};

    public static final String INBOX_TASK_TYPE_APPROVAL = "Approval";
    public static final String INBOX_TASK_TYPE_SAVEDRAFT = "SaveDraft";
    public static final String INBOX_TASK_TYPE_MOBILE_APPROVAL = "MobileApproval";
    public static final String INBOX_TASK_TYPE_DISTRIBUTION = "Distribution";
    public static final String INBOX_TASK_TYPE_REQUESTED = "Requested";
    public static final String INBOX_TASK_TYPE_PENDING_CHANGE = "PendingChange";
    public static final String INBOX_TASK_TYPE_PENDING_JOB_BY_RETIREMENT = "pendingJobByRetirementList";
    public static final String INBOX_TASK_TYPE_APPROVED = "Approved";
    public static final String INSTRUCTION_TYPE_STANDARD = "Standard";
    public static final String INSTRUCTION_TYPE_ORIGINATOR = "Originator";
    public static final String INSTRUCTION_TYPE_APPROVAL = "Approval";
    public static final String INSTRUCTION_TYPE_DISTRIBUTION = "Distribution";
    public static final String INSTRUCTION_TYPE_MANUAL_DISTRIBUTION = "ManualDistribution";
    public static final String INSTRUCTION_TYPE_SITE_ECO_SCOWORK = "SCOWork";
    public static final String INSTRUCTION_TYPE_MODEL_BOM_DISTRIBUTION = "MODEL BOM Distribution";
    public static final String ACTION_TYPE_END_WORKING = "EndWorking";
    public static final String STATES_TYPE_DEFINE = "Define";
    public static final String STATES_TYPE_ASSIGNED = "Assigned";
    public static final String STATES_TYPE_INPROCESS = "In Process";
    public static final String STATES_TYPE_COMPLETE = "Complete";
    public static final String ROUTESTATUS_TYPE_STOPPED = "Stopped";
    public static final String WORKFLOW_REASSIGN = "Reassign";
    
    public static final String ROUTE_ACTIONS_APPROVE = "Approve";
    public static final String ROUTE_ACTIONS_CONFIRM = "Confirm";
    public static final String ROUTE_ACTIONS_COMMENT = "Comment";
    public static final String ROUTE_ACTIONS_ENDWORKING = "EndWorking";
    public static final String[] ROUTE_ACTIONS_ALL = {ROUTE_ACTIONS_APPROVE,ROUTE_ACTIONS_CONFIRM,ROUTE_ACTIONS_COMMENT,ROUTE_ACTIONS_ENDWORKING};
    public static final String[] ROUTE_ACTIONS_NOTCOMMENT_ALL = {ROUTE_ACTIONS_APPROVE,ROUTE_ACTIONS_CONFIRM,ROUTE_ACTIONS_ENDWORKING};
    
    public static final String INBOX_TASK_TYPE_Workflow = "Workflow";
    public static final String INBOX_TASK_TYPE_WBSActivity = "WBS Activity";
    
    public static final String ACTION_COMMENTS_APPROVE = "Approve";
    public static final String ACTION_COMMENTS_CONFIRM = "Confirm";
    public static final String ACTION_COMMENTS_COMMENT = "Comment";
    public static final String ACTION_COMMENTS_ACKNOWLEDGE = "Acknowledge";
    public static final String ROUTE_PURPOSE_STANDARD = "Standard";
    public static final String ROUTE_PURPOSE_DISTRIBUTION = "Distribution";
    public static final String ROUTE_PURPOSE_CONFIRMATION = "Confirmation";
    public static final String ROUTE_PURPOSE_APPROVAL = "Approval";
    public static final String ROUTE_PURPOSE_REVIEW = "Review";
    
    public static final String ROUTE_INSTRUCTIONS_APPROVE = "A";
    
    public static final String AUTO_ACKNOWLEDGE = "Auto Acknowledge";
    public static final String AUTO_PROMOTE = "Auto Promote";

    public static final String ACTION_TYPE_PENDING = "Pending";
    public static final String ACTION_TYPE_APPROVED = "Approved";
    public static final String ACTION_TYPE_REJECTED = "Rejected";
    public static final String ACTION_TYPE_ACKNOWLEDGED = "Acknowledged";
    public static final String ACTION_TYPE_AWAITING_APPROVAL = "Awaiting Approval";
    public static final String ACTION_TYPE_AWAITING_ACKNOWLEDGE = "Awaiting Acknowledge";
    public static final String ACTION_TYPE_REASSIGNED = "Reassigned";



    public static final String ATTRIBUTE_FLAG_TRUE = "TRUE";
    public static final String ATTRIBUTE_FLAG_FALSE = "FALSE";
    public static final String ATTRIBUTE_TEST_RESULT_FAIL = "FAIL";
    public static final String ATTRIBUTE_TEST_RESULT_NG = "NG";
    public static final String ATTRIBUTE_TEST_RESULT_OK = "OK";
    public static final String ATTRIBUTE_TEST_RESULT_PASS = "PASS";
    public static final String ATTRIBUTE_TEST_STATUS_COMPLETE = "Complete";
    public static final String ATTRIBUTE_TEST_STATUS_COMPLETED = "Completed";
    //Change
    public static final String ATTRIBUTE_CHANGE_ACTION_TYPE_SITE = "Site Change";
    public static final String ATTRIBUTE_CHANGE_ACTION_TYPE_COMMON = "Common/Site Change";
    public static final String ATTRIBUTE_CHANGE_REDLINE_QUERY_TYPE_GENERAL = "General";

    //Part Technical Info
    public static final String ATTRIBUTE_TECH_BASE_MATERIAL = "lgeCSpec BASE MATERIAL";
    public static final String ATTRIBUTE_TECH_EXTRA = "lgeCSpec EXTRA";
    public static final String ATTRIBUTE_TECH_LAYER = "lgeCSpec Layer";
    public static final String ATTRIBUTE_TECH_MODEL_NAME = "lgeCSpec MODEL NAME";
    public static final String ATTRIBUTE_TECH_MANU_TYPE_PCB = "lgeCSpec Manufacture Type for PCB";
    public static final String ATTRIBUTE_TECH_REVISION = "lgeCSpec REVISION";
    public static final String ATTRIBUTE_TECH_THICKNESS = "lgeCSpec Thickness(mm)";

    //Partfamily
    public static final String PARTMAMILY_NAME_PHONE_SOFTWARE = "SAD";

    //Multi Part Site Copy
    public static final String BOM_SITE_COPY_DEFAULT_UIT = "G";

    // Mail
    public static final String MAIL_SUBJECT_PREFIX = "PLM";
    public static final String MAIL_TYPE_REJECT = "Reject";
    public static final String MAIL_TYPE_DISTRIBUTION = "Distribution";
    public static final String MAIL_TYPE_APPROVAL = "Approval";
    public static final String MAIL_TYPE_REASSIGN = "Reassign";

    public static final String SEND_UN_APPROVED_CHILD_PART_MAIL_GRID_TITLE = "Unapproved Child Part";
    public static final String SEND_UN_APPROVED_CHILD_PART_MAIL_CONTENTS = "This(These) is(are) a unapproved child part(s)"+
            " that is a development revision component in a assembly part’s BOM 1 level Structure.<br>"+
            "Please refer to the below part list and process a approval production process. <br><br><br><br>";

    // Approval Line Manage Type
    public static final String APPROVAL_LINE_MANAGE_TYPE_REQUEST = "Request";

    // State
    public static final String STATE_WORKING = "Working";
    public static final String STATE_PROCESSING = "Processing";
    public static final String STATE_RELEASE = "Release";


    public static final String STATE_INCOLLABORATION = "In Collaboration";
    public static final String STATE_IMPLEMENTED = "Implemented";
    public static final String MR_PART_STATUS_CODE_AVAILABLE = "0";
    public static final String MR_PART_STATUS_CODE_OBSOLETE = "1";
    public static final String MR_PART_STATUS_CODE_PROCESSING = "2";
    public static final String MR_PART_STATUS_CODE_APPROVED = "3";
    public static final String MR_PART_STATUS_CODE_SERVICE_ONLY = "4";
    public static final String MR_PART_STATUS_CODE_TO_BE_OBSOLETE = "5";

    // Range
    public static final String ATTRIBUTE_RANGE_Y = "Y";
    public static final String ATTRIBUTE_RANGE_N = "N";
    public static final String ATTRIBUTE_RANGE_Yes = "Yes";
    public static final String ATTRIBUTE_RANGE_No = "No";
    public static final String ATTRIBUTE_RANGE_Need = "Need";
    public static final String ATTRIBUTE_RANGE_Needless = "Needless";
    public static final String ATTRIBUTE_RANGE_All = "All";

    // Route Action
    public static final String ROUTE_ACTION_ALL = "All";
    public static final String ROUTE_ACTION_ANY = "Any";

    // User Property
    public static final String USER_PROP_BUSINESS_UNIT = "Business Unit Responsibility";
    public static final String USER_PROP_AFFILIATE_UNIT = "Affiliate Responsibility";
    public static final String USER_PROP_SITE = "Manufacturing Responsibility";
    public static final String USER_PROP_RESEARCH_CENTER = "Research Responsibility";
    public static final String USER_PROP_DIVISION = "Design Responsibility";
    public static final String USER_PROP_DAYLIGHT_SAVINGS = "Daylight Savings";
    public static final String USER_PROP_TIME_ZONE = "Time Zone";
    public static final String USER_PROP_LOCALE = "Locale";
    public static final String USER_PROP_MANAGEMENTGROUP = "Management Group";

    /*
     * Applet 호출시 사용되는 상수
     */
    public static final String COMMAND_TD_FILE_UPLOAD_GLOBAL_MC = "TD_FILE_UPLOAD_GLOBAL_MC";
    public static final String COMMAND_TD_FILE_UPLOAD_PART_MC = "TD_FILE_UPLOAD_PART_MC";
    public static final String COMMAND_TD_FILE_UPDATE_MC = "TD_FILE_UPDATE_MC";
    public static final String FORMAT_CAD = "CAD";
    public static final String FROM_ACTION_GLOBAL = "GLOBAL";
    public static final String FROM_ACTION_PART = "PART";

    // Interface - common
    public static final String INF_FLAG_SUCCESS = "Y";
    public static final String INF_FLAG_FAIL = "E";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND = "MailSend";
    public static final String INF_TARGET_SYSTEM_MAIL_ALERT = "MailAlert";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL001 = "MAIL001";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL002 = "MAIL002";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL003 = "MAIL003";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL004 = "MAIL004";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL005 = "MAIL005";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL006 = "MAIL006";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL007 = "MAIL007";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL008 = "MAIL008";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL009 = "MAIL009";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL010 = "MAIL010";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL011 = "MAIL011";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL012 = "MAIL012";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL013 = "MAIL013";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL014 = "MAIL014";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL015 = "MAIL015";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL016 = "MAIL016";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL018 = "MAIL018";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL019 = "MAIL019";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL020 = "MAIL020"; //사업계획 수립요청 메일링
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL021 = "MAIL021";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL022 = "MAIL022"; // Project 등록요청 메일발송
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL023 = "MAIL023";
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL025 = "MAIL025"; //S~B등급 DV/PV 품평회 완료시 알림
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL026 = "MAIL026"; // ProdPlan Distribution 알림 메일
    public static final String INF_TARGET_SYSTEM_MAIL_SEND_MAIL027 = "MAIL027"; // 주간 동향보고 메일 발송


    public static final String INF_TARGET_SYSTEM_PROJECT_COMMON_ALL = "PjtCommonAll";
    public static final String INF_TARGET_SYSTEM_PROJECT_WBS_ALL = "PjtWBSAll";
    public static final String INF_TARGET_SYSTEM_PROJECT_HEADER_EACH     = "PjtHeaderEach";
    public static final String INF_TARGET_SYSTEM_PROJECT_MEMBER_EACH     = "PjtMemberEach";
    public static final String INF_TARGET_SYSTEM_PROJECT_WBS_HEADER_EACH     = "PjtWBSHeaderEach";
    public static final String INF_TARGET_SYSTEM_PROJECT_WBS_DETAIL_EACH     = "PjtWBSDetailEach";
    public static final String INF_TARGET_SYSTEM_PROJECT_EXECUTER_EACH     = "PjtWBSExecuterEach";
    public static final String INF_TARGET_SYSTEM_PROJECT_MODEL_EACH     = "PjtModelEach";
    public static final String INF_TARGET_SYSTEM_PROJECT_GQIS_ALL     = "PjtGQISAll";
    public static final String INF_TARGET_SYSTEM_PROJECT_NPT_ALL     = "PjtNPTAll";
    public static final String INF_TARGET_SYSTEM_PROJECT_ARS_ALL     = "PjtARSAll";
    public static final String INF_TARGET_SYSTEM_PROJECT_WBS_GSCP = "PjtWBGSCP";

    public static final String INF_TARGET_SYSTEM_PROJECT = "PJTInfo";       // PJTComOut으로 합쳐짐.
    public static final String INF_TARGET_SYSTEM_PROJECT_PRODSITE = "PJTPrdsite";
    public static final String INF_TARGET_SYSTEM_PROJECT_EVENTQTY = "PJTEvntqty";
    public static final String INF_TARGET_SYSTEM_PROJECT_MODEL = "PJTModel";        // PJTComOut으로 합쳐짐.
    public static final String INF_TARGET_SYSTEM_PROJECT_MODEL_CHGINFO = "PJTModelCh";
    public static final String INF_TARGET_SYSTEM_PROJECT_MEMBER= "PJTMember";       // PJTComOut으로 합쳐짐.
    public static final String INF_TARGET_SYSTEM_PROJECT_SCHEDULE= "PJTSch";    // PJTComOut으로 합쳐짐.
    public static final String INF_TARGET_SYSTEM_PROJECT_SCHEDULE_001= "SCH-001";
    public static final String INF_TARGET_SYSTEM_PROJECT_SCHEDULE_CHN= "PJTSchChn";
    public static final String INF_TARGET_SYSTEM_PROJECT_SCHEDULE_D= "PJTSchD";
    public static final String INF_TARGET_SYSTEM_PROJECT_001 = "PJT001";
    public static final String INF_TARGET_SYSTEM_PROJECT_002 = "PJT002";
    public static final String INF_TARGET_SYSTEM_PROJECT_DOC = "PJTDoc";
    public static final String INF_TARGET_SYSTEM_PROJECT_DOC_001 = "DOC001";
    public static final String INF_TARGET_SYSTEM_PROJECT_DOC_002 = "DOC002";
    public static final String INF_TARGET_SYSTEM_GMAP = "GMAP";

    public static final String INF_TARGET_SYSTEM_PROJECT_IMC = "PJTImc";
    public static final String INF_TARGET_SYSTEM_PROJECT_IMC_001 = "IMC-OUT-001";
    public static final String INF_TARGET_SYSTEM_PROJECT_IMC_002 = "IMC-OUT-002";
    public static final String INF_TARGET_SYSTEM_PROJECT_IMC_003 = "IMC-OUT-003"; // TB_ITF_PLM_ER_COMPLETION_IMC
    public static final String INF_TARGET_SYSTEM_PROJECT_GCRC = "GCRCPjt";
    public static final String INF_TARGET_SYSTEM_PROJECT_GCRC_001 = "OUT-001";
    public static final String INF_TARGET_SYSTEM_PROJECT_GCRC_002 = "OUT-002";
    public static final String INF_TARGET_SYSTEM_PROJECT_GERP = "GERP";
    public static final String INF_TARGET_SYSTEM_PROJECT_GQIS = "GQIS";
    public static final String INF_TARGET_SYSTEM_PROJECT_SDMS = "SDMS";

    public static final String INF_TARGET_SYSTEM_PROJECT_BPM = "BPM";
    public static final String INF_TARGET_SYSTEM_WBS_DELAY = "WBSDelay";
    public static final String INF_TARGET_SYSTEM_MES_PROD = "MESProd";
    public static final String INF_TARGET_SYSTEM_B2B_USER = "B2BUser";
    public static final String INF_TARGET_SYSTEM_SDMS_001 = "SDMS001";
    public static final String INF_TARGET_SYSTEM_SDMS_002 = "SDMS002";
    public static final String INF_TARGET_SYSTEM_SDMS_003 = "SDMS003";
    public static final String INF_TARGET_SYSTEM_SDMS_004 = "SDMS004";
    public static final String INF_TARGET_SYSTEM_GMAP_001 = "GMAP001";
    
    // 부품르로젝트
    public static final String INF_TARGET_SYSTEM_PARTPROJECT = "PartProjectSendTo";
    public static final String INF_TARGET_SYSTEM_PARTPROJECT_DQMS = "SendToDQMS";


    // Interface - DQMS Table
    public static final String DQMS_IN_DQMS_PROD_STD_ESSENSE_INFO = "IN_DQMS_PROD_STD_ESSENSE_INFO";
    public static final String DQMS_IN_DQMS_PROD_STD_ETC_ITEM = "IN_DQMS_PROD_STD_ETC_ITEM";
    public static final String DQMS_IN_DQMS_PROD_BASE_INFO = "IN_DQMS_PROD_BASE_INFO";
    public static final String DQMS_IN_DQMS_PROD_STD_ESSENSE_DOC = "IN_DQMS_PROD_STD_ESSENSE_DOC";
    public static final String DQMS_IN_DQMS_PART_STD_DOC = "IN_DQMS_PART_STD_DOC";
    public static final String DQMS_IN_DQMS_SYS_CODE = "IN_DQMS_SYS_CODE";
    public static final String DQMS_NAME_CONNECTION = "-";

    // Interface - IAM Delegate info
    public static final String IAM_IN_DELEGATE_TYPE_SET = "NLI";
    public static final String IAM_IN_DELEGATE_TYPE_RESET = "RESET_NLI";
    public static final String IAM_IN_DELEGATE_TYPE_SET_APD = "APD";
    public static final String IAM_IN_DELEGATE_TYPE_RESET_APD = "RESET_APD";

    // Interface - IAM
    public static final String IAM_SYSTEM_CODE = "GPDM_VC";
    public static final String IAM_RESP_TYPE_TC_USE = "TC_Use";
    public static final String IAM_RESP_TYPE_ST_USE = "ST_Use";
    public static final String IAM_RESP_TYPE_SITE = "Site";
    public static final String IAM_RESP_TYPE_ROLE = "Role";
    public static final String IAM_RESP_TYPE_RESEARCH_CENETER = "Research Center";
    public static final String IAM_TC_USE_TYPE_Y = "TC_Y";
    public static final String IAM_ST_USE_TYPE_Y = "ST_Y";
    public static final String IAM_ROLE_GENERAL_USER = "General User";  // VC/MC 공통
    public static final String IAM_ROLE_HARDWARE_ENGINEER = "Hardware Engineer";  // VC/MC 공통
    public static final String IAM_ROLE_MECHANICAL_ENGINEER = "Mechanical Engineer";  // VC/MC 공통
    public static final String IAM_ROLE_PCB_ENGINEER = "PCB Engineer";  // VC/MC 공통
    public static final String IAM_ROLE_SW_ENGINEER = "Software Engineer";  // MC Only
    public static final String IAM_ROLE_PART_PURCHASE_DEV = "Part Purchase Dev";  // MC Only
    public static final String IAM_ROLE_MANUAL_PACKAGE_USER = "Manual/Package User";  // MC Only
    public static final String IAM_ROLE_3D_LIBRARIAN = "3D Librarian";  // MC Only

    public static final String[] IAM_ROLE_LIST = {ApplicationBizConstants.IAM_ROLE_GENERAL_USER,
            ApplicationBizConstants.IAM_ROLE_HARDWARE_ENGINEER,
            ApplicationBizConstants.IAM_ROLE_MECHANICAL_ENGINEER,
            ApplicationBizConstants.IAM_ROLE_PCB_ENGINEER,
            ApplicationBizConstants.IAM_ROLE_SW_ENGINEER,
            ApplicationBizConstants.IAM_ROLE_PART_PURCHASE_DEV,
            ApplicationBizConstants.IAM_ROLE_MECHANICAL_ENGINEER,
            ApplicationBizConstants.IAM_ROLE_MANUAL_PACKAGE_USER,
            ApplicationBizConstants.IAM_ROLE_3D_LIBRARIAN};

    // Interface - DQMS FUND TYPE
    public static final String FUND_TYPE_1 = "lgeTestType";
    public static final String FUND_TYPE_9 = "lgeDQMSProductGroup";
    public static final String FUND_TYPE_11 = "lgeDQMSEvent";
    public static final String FUND_TYPE_12 = "lgeDQMSGrade";
    public static final String FUND_TYPE_15 = "lgeElectricCode";
    public static final String FUND_TYPE_18 = "lgeSystem";
    public static final String FUND_TYPE_20 = "lgeEssentialDocument";
    public static final String FUND_TYPE_29 = "lgeFactor";

    // Interface - DQMS Prod Test Request Attribute
    public static final String DQMS_INF_PROD_ATTR_EVENT_FLAG = "EVENT_FLAG";
    public static final String DQMS_INF_PROD_ATTR_EXP_AREA_FLAG = "EXP_AREA_FLAG";
    public static final String DQMS_INF_PROD_ATTR_SAMPLE_QTY_FLAG = "SAMPLE_QTY_FLAG";
    public static final String DQMS_INF_PROD_ATTR_REFFERNCE_COMMENT_FLAG = "REFFERNCE_COMMENT_FLAG";
    public static final String DQMS_INF_PROD_ATTR_HW_VERSION_FLAG = "HW_VERSION_FLAG";
    public static final String DQMS_INF_PROD_ATTR_TEST_PURPOSE_FLAG = "TEST_PURPOSE_FLAG";
    public static final String DQMS_INF_PROD_ATTR_RATED_VOLATAGE_FLAG = "RATED_VOLATAGE_FLAG";
    public static final String DQMS_INF_PROD_ATTR_BUYER_FLAG = "BUYER_FLAG";
    public static final String DQMS_INF_PROD_ATTR_PRODUCT_GROUP_FLAG = "PRODUCT_GROUP_FLAG";
    public static final String DQMS_INF_PROD_ATTR_PROJECT_CHASSIS_FLAG = "PROJECT_CHASSIS_FLAG";
    public static final String DQMS_INF_PROD_ATTR_REQ_END_DATE_FLAG = "REQ_END_DATE_FLAG";
    public static final String DQMS_INF_PROD_ATTR_SW_VERSION_FLAG = "SW_VERSION_FLAG";
    public static final String DQMS_INF_PROD_ATTR_RECEIVE_USER_FLAG = "RECEIVE_USER_FLAG";
    public static final String DQMS_INF_PROD_ATTR_REQ_USER_FLAG = "REQ_USER_FLAG";
    public static final String DQMS_INF_PROD_ATTR_REQUEST_SEQ_FLAG = "REQUEST_SEQ_FLAG";
    public static final String DQMS_INF_PROD_ATTR_ELECTRIC_CODE_FLAG = "ELECTRIC_CODE_FLAG";
    public static final String DQMS_INF_PROD_ATTR_MODEL_GRADE_FLAG = "MODEL_GRADE_FLAG";
    public static final String DQMS_INF_PROD_ATTR_TEST_TYPE_FLAG = "TEST_TYPE_FLAG";
    public static final String DQMS_INF_PROD_ATTR_REQ_DATE_FLAG = "REQ_DATE_FLAG";
    public static final String DQMS_INF_PROD_ATTR_MODEL_FLAG = "MODEL_FLAG";
    public static final String DQMS_INF_PROD_ATTR_SYSTEM_FLAG = "SYSTEM_FLAG";

    //Interface - GPDM Prod Test Request Attribute Key
    public static final String GPDM_PROD_REQ_ATTR_KEY_EVENT = "lgeDQMSEvent";
    public static final String GPDM_PROD_REQ_ATTR_KEY_EXP_AREA = "lgeExportRegion";
    public static final String GPDM_PROD_REQ_ATTR_KEY_SAMPLE_QTY = "lgeSampleQuantity";
    public static final String GPDM_PROD_REQ_ATTR_KEY_REFFERNCE_COMMENT = "Description";
    public static final String GPDM_PROD_REQ_ATTR_KEY_HW_VERSION = "lgeHardwareVersion";
    public static final String GPDM_PROD_REQ_ATTR_KEY_TEST_PURPOSE = "lgeTestObjective";
    public static final String GPDM_PROD_REQ_ATTR_KEY_RATED_VOLATAGE = "lgeVoltage";
    public static final String GPDM_PROD_REQ_ATTR_KEY_BUYER = "lgeBuyerName";
    public static final String GPDM_PROD_REQ_ATTR_KEY_PRODUCT_GROUP = "lgeDQMSProductGroup";
    public static final String GPDM_PROD_REQ_ATTR_KEY_PROJECT_CHASSIS = "lgeChassis";
    public static final String GPDM_PROD_REQ_ATTR_KEY_REQ_END_DATE = "lgeRequestEndDate";
    public static final String GPDM_PROD_REQ_ATTR_KEY_SW_VERSION = "lgeSoftwareVersion";
    public static final String GPDM_PROD_REQ_ATTR_KEY_RECEIVE_USER = "lgeTestReceiver";
    public static final String GPDM_PROD_REQ_ATTR_KEY_REQ_USER = "Originator";
    public static final String GPDM_PROD_REQ_ATTR_KEY_REQUEST_SEQ = "lgeEventSeq";
    public static final String GPDM_PROD_REQ_ATTR_KEY_ELECTRIC_CODE = "lgeElectricCode";
    public static final String GPDM_PROD_REQ_ATTR_KEY_MODEL_GRADE = "lgeDQMSGrade";
    public static final String GPDM_PROD_REQ_ATTR_KEY_TEST_TYPE = "lgeTestType";
    public static final String GPDM_PROD_REQ_ATTR_KEY_REQ_DATE = "lgeRequestDate";
    public static final String GPDM_PROD_REQ_ATTR_KEY_MODEL = "lgeRepresentativeModel";
    public static final String GPDM_PROD_REQ_ATTR_KEY_SYSTEM = "lgeSystem";

    //Interface - EP
    public static final Integer EP_SYSTEM_ID_FOR_VC = 130;
    public static final Integer EP_SYSTEM_ID_FOR_MC = 54;

    //Interface - GPDM Prod Test Request Attribute Name
    public static final String GPDM_PROD_REQ_ATTR_EVENT = "dqmsEvent";
    public static final String GPDM_PROD_REQ_ATTR_EXP_AREA = "exportRegion";
    public static final String GPDM_PROD_REQ_ATTR_SAMPLE_QTY = "sampleQuantity";
    public static final String GPDM_PROD_REQ_ATTR_REFFERNCE_COMMENT = "descriptions";
    public static final String GPDM_PROD_REQ_ATTR_HW_VERSION = "hwVersion";
    public static final String GPDM_PROD_REQ_ATTR_TEST_PURPOSE = "testPurpose";
    public static final String GPDM_PROD_REQ_ATTR_RATED_VOLATAGE = "voltage";
    public static final String GPDM_PROD_REQ_ATTR_BUYER = "buyerName";
    public static final String GPDM_PROD_REQ_ATTR_PRODUCT_GROUP = "dqmsProductGroup";
    public static final String GPDM_PROD_REQ_ATTR_PROJECT_CHASSIS = "chassis";
    public static final String GPDM_PROD_REQ_ATTR_REQ_END_DATE = "testCompletePlanDate";
    public static final String GPDM_PROD_REQ_ATTR_SW_VERSION = "swVersion";
    public static final String GPDM_PROD_REQ_ATTR_RECEIVE_USER = "testReceiverName";
    public static final String GPDM_PROD_REQ_ATTR_REQ_USER = "requesterName";
    public static final String GPDM_PROD_REQ_ATTR_REQUEST_SEQ = "Event Seq";
    public static final String GPDM_PROD_REQ_ATTR_ELECTRIC_CODE = "electricCode";
    public static final String GPDM_PROD_REQ_ATTR_MODEL_GRADE = "dqmsGrade";
    public static final String GPDM_PROD_REQ_ATTR_TEST_TYPE = "testType";
    public static final String GPDM_PROD_REQ_ATTR_REQ_DATE = "requestStartDate";
    public static final String GPDM_PROD_REQ_ATTR_MODEL = "Representative Model";
    public static final String GPDM_PROD_REQ_ATTR_SYSTEM = "system";
    public static final String GPDM_PROD_REQ_ATTR_FACTORY = "Factory";

    public static final String SOFTWARE_DOCUMENT_TYPE = "SW";

    //File Format
    public static final String FILE_FORMAT_GENERIC = "generic";
    public static final String FILE_FORMAT_PCBGERBER = "PCBGerber";
    public static final String FILE_FORMAT_PCBASCII = "PCBASCII";
    public static final String FILE_FORMAT_CPCFILES = "CPCFiles";
    public static final String FILE_FORMAT_ECOHTML = "ECOHtml";
    public static final String FILE_FORMAT_REQUESTHTML = "RequestHtml";
    public static final String FILE_FORMAT_ECADLIBREPORT = "ECADLibReport";
    public static final String FILE_FORMAT_CAEREPORT = "CAEReport";
    public static final String FILE_FORMAT_MC_DERIVED_OUTPUT = "MC_VIEW_SCHE,MC_VIEW_PCB,MC_PARTLIST,MC_GBR,MC_CAM,MC_PLACEMENT,MC_DFM,MC_IDF,MC_DXF,generic,MC_OLD_PDF_SCHE,MC_OLD_GBR,MC_OLD_SMT";

    //Interface - PUMDM STATUS
    public static final String PUMDM_STATUS_ACCEPT = "A";
    public static final String PUMDM_STATUS_REJECT = "R";
    public static final String PUMDM_STATUS_COMPLETE = "C";

    //  o-event project type
    public static final String PROJECT_TYPE_PMS = "PMS Project";
    public static final String PROJECT_TYPE_OEM = "OEM Project";
    public static final String PROJECT_TYPE_INTERNAL_PROJECT   = "Internal Project";
    public static final String PROJECT_TYPE_INTERNAL_WORKGROUP = "Internal Workgroup";

    // project event
    public static final String PROJECT_EVENT_CP = "PHASE1";
    public static final String PROJECT_EVENT_PP = "PHASE2";
    public static final String PROJECT_EVENT_DV = "PHASE3";
    public static final String PROJECT_EVENT_PV1 = "PHASE4";
    public static final String PROJECT_EVENT_PV2 = "PHASE5"; // VC only
    public static final String PROJECT_EVENT_PRE_MP = "PHASE6";

    public static final String ECOTYPE_GENERAL = "1";
    public static final String ECOTYPE_MASS = "2";
    public static final String ECOTYPE_WITHOUT_ITEM = "7";

    public static final String FILE_SEPERATOR = "/";

    // 게시판 유형
    public static final String BBS_TYPE_NOTICE = "NOTICE";
    public static final String BBS_TYPE_DIVNOTICE = "DIVNOTICE";
    public static final String BBS_TYPE_FAQ = "FAQ";
    public static final String BBS_TYPE_QNA = "QNA";
    public static final String BBS_TYPE_CONTACTUS = "CONTACTUS";
    public static final String BBS_TYPE_HELP = "HELP";

    // 게시물 유형
    public static final String BBS_ITEM_TYPE_POPUP = "POPUP";
    public static final String CODE_NAME_BBS_MODULE = "BBS_MODULE";
    public static final String CODE_NAME_BBS_ITEM_TYPE = "BBS_ITEM_TYPE";
    public static final String CODE_NAME_BBS_REPLY_TYPE = "BBS_REPLY_TYPE";

    public static final String INTEGRATION_USER = "Interface Agent";
    public static final String ADMIN_USER = "XP3866";
    public static final String PMS_ADMIN_USER = "XP75500";
    
    //Menu Access Check
    public static final String cmdAdminApprovalPortal = "cmdAdminApprovalPortal";
    public static final String cmdECADDocumentCreate = "cmdECADDocumentCreate";
    public static final String cmdTCDocumentCreate = "cmdTCDocumentCreate";
    public static final String cmdCatiaDocumentCreate = "cmdCatiaDocumentCreate";
    public static final String cmdSupplyType = "cmdSupplyType";
    public static final String cmdChangeAuth = "cmdChangeAuth";
    public static final String cmdSWFileTemplate = "cmdSWFileTemplate"; //Apply
    public static final String cmdDrawingTemplate = "cmdDrawingTemplate";
    public static final String cmdBOMTemplate = "cmdBOMTemplate";
    public static final String cmdEssentialApprover = "cmdEssentialApprover";
    public static final String cmdOEMProjectTemplate = "cmdOEMProjectTemplate";
    public static final String cmdOEMMaster = "cmdOEMMaster";
    public static final String cmdOEMEvent = "cmdOEMEvent";
    public static final String cmdMakerGroup = "cmdMakerGroup";
    public static final String cmdPDEVAdminSetting = "cmdPDEVAdminSetting";
    public static final String cmdTCSiteMasterList = "cmdTCSiteMasterList";
    public static final String cmdSTSiteMasterList = "cmdSTSiteMasterList";
    public static final String cmdPptECOTemplateMgt = "cmdPptECOTemplateMgt";
    public static final String cmdIfSystem = "cmdIfSystem";
    public static final String cmdNotice = "cmdNotice";
    public static final String cmdTypeCATIADocRelDrawing = "cmdTypeCATIADocRelDrawing";
    public static final String cmdTypeAffectedPart = "cmdTypeAffectedPart";
    public static final String cmdTypeAffectedDocument = "cmdTypeAffectedDocument";
    public static final String cmdChangeRelationship = "cmdChangeRelationship"; // layout임 command 별 check 함.
    public static final String cmdChangeRelatedRequestLayout = "cmdChangeRelatedRequestLayout";
    public static final String cmdCodeMasterCreate = "cmdCodeMasterCreate";  // apply
    public static final String cmdCodeMasterUpdate = "cmdCodeMasterUpdate";  // apply
    public static final String cmdCodeMasterDelete = "cmdCodeMasterDelete";  // apply
    public static final String cmdCodeDetailExcelImport = "cmdCodeDetailExcelImport";  // apply
    public static final String cmdChangeAuthExcelImport = "cmdChangeAuthExcelImport";  // apply
    public static final String cmdCodeDetailCreate = "cmdCodeDetailCreate";  // apply
    public static final String cmdCodeDetailUpdate = "cmdCodeDetailUpdate";  // apply
    public static final String cmdCodeDetailActive = "cmdCodeDetailActive";  // apply
    public static final String cmdCodeDetailInactive = "cmdCodeDetailInactive";  // apply
    public static final String cmdCodeDetailDelete = "cmdCodeDetailDelete";  // apply
    public static final String cmdECOEditDetail = "cmdECOEditDetail"; // applied
    public static final String cmdECOChangeOwner = "cmdECOChangeOwner"; // applied
    public static final String cmdECOCancelECO = "cmdECOCancelECO";// applied
    public static final String cmdECOSaveAs = "cmdECOSaveAs"; // applied
    public static final String cmdChangeReGenerateReport = "cmdChangeReGenerateReport";// applied
    public static final String cmdBOMViewChangeMBomToEBom = "cmdBOMViewChangeMBomToEBom"; // not use
    public static final String cmdBomEditApproveChange = "cmdBomEditApproveChange"; // BOM Edit > End Working : approval action - Menu Access 적용불가
    public static final String cmdBomEditCopyModel = "cmdBomEditCopyModel"; // apply
    public static final String cmdRequestChangePart = "cmdRequestChangePart"; //Apply
    public static final String cmdRequestRegistratePart = "cmdRequestRegistratePart"; //Apply
    public static final String cmdRequestEdit = "cmdRequestEdit"; //Apply
    public static final String cmdRequestChangeOwner = "cmdRequestChangeOwner"; //Apply
    public static final String cmdECADRequestEdit = "cmdECADRequestEdit";
    public static final String cmdECADRequestSaveAs = "cmdECADRequestSaveAs";
    public static final String cmdECADRequestDelete = "cmdECADRequestDelete";
    public static final String cmdRequestDelete = "cmdRequestDelete";
    public static final String cmdECADRequestRegularPart = "cmdECADRequestRegularPart";
    public static final String cmdECADRequestModify = "cmdECADRequestModify";
    public static final String cmdECADCompleteRequestDelete = "cmdECADCompleteRequestDelete";
    public static final String cmdECADManuOrderRequestCreate = "cmdECADManuOrderRequestCreate";
    public static final String cmdECADStencilRequestCreate = "cmdECADStencilRequestCreate";
    public static final String cmdECADRequestCancel = "cmdECADRequestCancel";
    public static final String cmdECADRequestAssignAccept = "cmdECADRequestAssignAccept";
    public static final String cmdCreateCAEReport = "cmdCreateCAEReport";
    public static final String cmdDeleteCAEReport = "cmdDeleteCAEReport";
    public static final String cmdCreateLibrarianReport = "cmdCreateLibrarianReport";
    public static final String cmdDeleteLibrarianReport = "cmdDeleteLibrarianReport";
    public static final String cmdChangeValidation = "cmdChangeValidation";
    public static final String cmdAddExistingsGenericDoc = "cmdAddExistingsGenericDoc";
    public static final String cmdRemoveRelatedGenericDoc = "cmdRemoveRelatedGenericDoc";
    public static final String cmdAddExistingsCr = "cmdAddExistingsCr";
    public static final String cmdRemoveRelatedCr = "cmdRemoveRelatedCr";
    public static final String cmdAddExistingsReferenceGenericDoc = "cmdAddExistingsReferenceGenericDoc";
    public static final String cmdRemoveReferenceGenericDoc = "cmdRemoveReferenceGenericDoc";
    public static final String cmdCrEdit = "cmdCrEdit";
    public static final String cmdCrDelete = "cmdCrDelete";
    public static final String cmdCrCancel = "cmdCrCancel";
    public static final String cmdCrChangeOwner = "cmdCrChangeOwner";
    public static final String cmdCrUploadFile = "cmdCrUploadFile";
    public static final String cmdCrDeleteFile = "cmdCrDeleteFile";
    public static final String cmdGenericDocEdit = "cmdGenericDocEdit";
    public static final String cmdCreateRelease = "cmdCreateRelease";
    public static final String cmdCreateCr = "cmdCreateCr";
    public static final String cmdGenricDocAutoRelease = "cmdGenricDocAutoRelease";
    public static final String cmdGenericDocChangeOwner = "cmdGenericDocChangeOwner";
    public static final String cmdGenericDocDelete = "cmdGenericDocDelete";
    public static final String cmdGenericDocChangeFolder = "cmdGenericDocChangeFolder";
    public static final String cmdGenericDocUploadFile = "cmdGenericDocUploadFile";
    public static final String cmdGenericDocDeleteFile = "cmdGenericDocDeleteFile";
    public static final String cmdProjectEdit = "cmdProjectEdit";
    public static final String cmdProjectFolderEdit = "cmdProjectFolderEdit";
    public static final String cmdOemEventUpdate = "cmdOemEventUpdate";
    public static final String cmdDocByOemEventUpdate = "cmdDocByOemEventUpdate";
    public static final String cmdWrokGroupUpdate = "cmdWrokGroupUpdate";
    public static final String cmdEventByWorkGroupUpdate = "cmdEventByWorkGroupUpdate";
    public static final String cmdFileByWorkGroupEventUpdate = "cmdFileByWorkGroupEventUpdate";
    public static final String cmdCopyProjectSchedule = "cmdCopyProjectSchedule";
    public static final String cmdAddExistingsDoc = "cmdAddExistingsDoc";
    public static final String cmdRemoveRelatedDoc = "cmdRemoveRelatedDoc";
    public static final String cmdDocBaselineEdit = "cmdDocBaselineEdit";
    public static final String cmdDocBaselineDelete = "cmdDocBaselineDelete";
    public static final String cmdDocBaselineOwner = "cmdDocBaselineOwner";
    public static final String cmdDocBaselineUploadFile = "cmdDocBaselineUploadFile";
    public static final String cmdDocBaselineDeleteFile = "cmdDocBaselineDeleteFile";
    public static final String cmdAddExistingAffectedPart = "cmdAddExistingAffectedPart"; // applied
    public static final String cmdAffectedPartRelatedItemList = "cmdAffectedPartRelatedItemList"; // cmdAddExistingAffectedPart 와 동일 controller - service 에서 validation 함
    public static final String cmdRemoveAffectedPart = "cmdRemoveAffectedPart"; // applied
    public static final String cmdAffectedPartEditEffectiveDate = "cmdAffectedPartEditEffectiveDate"; // 삭제 된 기능.
    public static final String cmdAffectedPartCheckInPart = "cmdAffectedPartCheckInPart"; // applied
    public static final String cmdAffectedPartCheckOutPart = "cmdAffectedPartCheckOutPart"; // applied
    public static final String cmdAffectedPartMoveSelectedExistingECO = "cmdAffectedPartMoveSelectedExistingECO"; // applied
    public static final String cmdAffectedPartMoveSelectedNewECO = "cmdAffectedPartMoveSelectedNewECO"; // applied
    public static final String cmdAffectedPartDerivedItem = "cmdAffectedPartDerivedItem"; // cmdAddExistingAffectedPart 와 동일 controller - service 에서 validation 함
    public static final String cmdAffectedPartMassChange = "cmdAffectedPartMassChange";
    public static final String cmdAffectedPartBOMCompare = "cmdAffectedPartBOMCompare"; // action 기능 없음.
    public static final String cmdAffectedPartBOMValidation = "cmdAffectedPartBOMValidation"; // action 기능 없음.
    public static final String cmdModifyApprover = "cmdModifyApprover";
    public static final String cmdCheckMandantoryApprover = "cmdCheckMandantoryApprover";
    public static final String cmdRequestAddExistingPart = "cmdRequestAddExistingPart"; //Apply
    public static final String cmdRequestAddExistingChangePart = "cmdRequestAddExistingChangePart"; //Apply
    public static final String cmdRequestSavePart = "cmdRequestSavePart"; //Apply
    public static final String cmdRequestRemovePart = "cmdRequestRemovePart"; //Apply
    public static final String cmdRequestAddExistingDoc = "cmdRequestAddExistingDoc"; //Apply
    public static final String cmdRequestRemoveDoc = "cmdRequestRemoveDoc"; //Apply
    public static final String cmdAddExistingCADDocument = "cmdAddExistingCADDocument"; // cmdAddExistingAffectedPart 와 동일 controller - service 에서 validation 함
    public static final String cmdAddExistingGenericDocument = "cmdAddExistingGenericDocument"; // cmdAddExistingAffectedPart 와 동일 controller - service 에서 validation 함
    public static final String cmdRelatedItemList = "cmdRelatedItemList"; // cmdAddExistingAffectedPart 와 동일 controller - service 에서 validation 함
    public static final String cmdRemove = "cmdRemove"; // applied
    public static final String cmdRemoveDelete = "cmdRemoveDelete";// part delete 에서 validation
    public static final String cmdCreatePPTECO = "cmdCreatePPTECO";
    public static final String cmdEditPPTECO = "cmdEditPPTECO";
    public static final String cmdDeletePPTECO = "cmdDeletePPTECO";
    public static final String cmdDownloadPPTECO = "cmdDownloadPPTECO";
    public static final String cmdPartClone = "cmdPartClone";  //apply
    public static final String cmdPartCheckIn = "cmdPartCheckIn";  //apply
    public static final String cmdPartCheckOut = "cmdPartCheckOut";    //apply
    public static final String cmdPartChangeOwner = "cmdPartChangeOwner";  //apply
    public static final String cmdPartChangeBOMOwnerSite = "cmdPartChangeBOMOwnerSite";    //apply
    public static final String cmdPartDelete = "cmdPartDelete";    // action에 validation 있음
    public static final String mnuPartEdit = "mnuPartEdit";    // apply
    public static final String cmdModelEditDetail = "cmdModelEditDetail";  //apply
    public static final String cmdModelDelete = "cmdModelDelete";  // action에 validation 있음
    public static final String cmdRelatedECOList = "cmdRelatedECOList"; // applied
    public static final String cmdRelatedECRList = "cmdRelatedECRList"; // applied
    public static final String cmdDrawingTemplateMgmtCreate = "cmdDrawingTemplateMgmtCreate";
    public static final String cmdDrawingTemplateMgmtEdit = "cmdDrawingTemplateMgmtEdit";
    public static final String cmdDrawingTemplateMgmtDelete = "cmdDrawingTemplateMgmtDelete";
    public static final String cmdDrawingTemplateMgmtActive = "cmdDrawingTemplateMgmtActive";
    public static final String cmdDrawingTemplateMgmtInActive = "cmdDrawingTemplateMgmtInActive";
    public static final String cmdDrawingTemplateMgmtUploadParameter = "cmdDrawingTemplateMgmtUploadParameter";
    public static final String cmdDrawingTemplateMgmtParameterList = "cmdDrawingTemplateMgmtParameterList";
    public static final String cmdChangeAuthAdd = "cmdChangeAuthAdd";
    public static final String cmdChangeAuthEdit = "cmdChangeAuthEdit";
    public static final String cmdChangeAuthDelete = "cmdChangeAuthDelete";
    public static final String cmdReleaseEditDetails = "cmdReleaseEditDetails"; // applied
    public static final String cmdReleaseCancel = "cmdReleaseCancel"; // applied
    public static final String cmdReleaseSaveAs = "cmdReleaseSaveAs";
    public static final String cmdChangeCreateCPCDistribution = "cmdChangeCreateCPCDistribution"; // CPC 배포 생성 체크 위치 공유 필요.
    public static final String cmdPromoteToImplemented = "cmdPromoteToImplemented"; // applied
    public static final String cmdReGenerateReportAndSendMail = "cmdReGenerateReportAndSendMail"; // applied
    public static final String cmdECRUpdate = "cmdECRUpdate";  // apply
    public static final String cmdECRCancel = "cmdECRCancel";  // apply
    public static final String cmdECRSaveAsECO = "cmdECRSaveAsECO";    // object[states].value == {'Complete'} states 변경될 경우 없음.
    public static final String cmdDrawingFilesDGUpload = "cmdDrawingFilesDGUpload";
    public static final String cmdDrawingFilesBDUpload = "cmdDrawingFilesBDUpload";
    public static final String cmdDrawingFilesGenericUpload = "cmdDrawingFilesGenericUpload";
    public static final String cmdCADDocEditAttributes = "cmdCADDocEditAttributes";
    public static final String cmdMCADDocChangeOwner = "cmdMCADDocChangeOwner";
    public static final String cmdCADDocAssignRegularNo = "cmdCADDocAssignRegularNo";
    public static final String cmdCADDocDelete = "cmdCADDocDelete";
    public static final String cmdCADDocSaveAs = "cmdCADDocSaveAs";
    public static final String cmdCADDocPartList = "cmdCADDocPartList";
    public static final String cmdCADDocAddToExistingRelease = "cmdCADDocAddToExistingRelease";
    public static final String cmdCADDocAddToCreateRelease = "cmdCADDocAddToCreateRelease";
    public static final String cmdCADDocAddToExistingECO = "cmdCADDocAddToExistingECO";
    public static final String cmdCADDocAddToCreateECO = "cmdCADDocAddToCreateECO";
    public static final String cmdDocChangeOwner = "cmdDocChangeOwner"; // apply
    public static final String cmdEtcDocDetailActionsDelete = "cmdEtcDocDetailActionsDelete";
    public static final String cmdEtcDocFilesActionsUpload = "cmdEtcDocFilesActionsUpload"; // apply
    public static final String cmdEtcDocFilesActionsDelete = "cmdEtcDocFilesActionsDelete"; // apply
    public static final String cmdEtcDocAttachmentActionsUpload = "cmdEtcDocAttachmentActionsUpload"; // apply
    public static final String cmdEtcDocAttachmentActionsDelete = "cmdEtcDocAttachmentActionsDelete"; // apply
    public static final String cmdCADDocCreateDistributionActionsAddTechDoc = "cmdCADDocCreateDistributionActionsAddTechDoc";
    public static final String cmdCADDocCreateDistributionActionsAddSWDoc = "cmdCADDocCreateDistributionActionsAddSWDoc";
    public static final String cmdDrawingDetailEdit = "cmdDrawingDetailEdit";
    public static final String cmdDrawingDetailAssignRegularPartNo = "cmdDrawingDetailAssignRegularPartNo";
    public static final String cmdDrawingDetailUserChangeOwner = "cmdDrawingDetailUserChangeOwner"; // apply
    public static final String cmdDrawingDetailCancelCheckOut = "cmdDrawingDetailCancelCheckOut";
    public static final String cmdCreateCPCDistribution = "cmdCreateCPCDistribution";
    public static final String cmdVersionBaseLineEdit = "cmdVersionBaseLineEdit";
    public static final String cmdVersionBaseLineDelete = "cmdVersionBaseLineDelete";
    public static final String cmdECADDrawingDetailPartListCreate = "cmdECADDrawingDetailPartListCreate";
    public static final String cmdPartSpecificationAddExistingMCADDoc = "cmdPartSpecificationAddExistingMCADDoc";  // apply
    public static final String cmdPartSpecificationAddExisting2DCatia = "cmdPartSpecificationAddExisting2DCatia";  // apply
    public static final String cmdPartSpecificationAddExistingEtcDoc = "cmdPartSpecificationAddExistingEtcDoc";    // apply
    public static final String cmdPartSpecificationCreateMCADDrawing = "cmdPartSpecificationCreateMCADDrawing";    // action에 validation 있음
    public static final String cmdPartSpecificationCreateECADDrawing = "cmdPartSpecificationCreateECADDrawing";    // action에 validation 있음
    public static final String cmdPartSpecificationCreateSoftwareDoc = "cmdPartSpecificationCreateSoftwareDoc";    // action에 validation 있음
    public static final String cmdPartSpecificationAddExistingSWDoc = "cmdPartSpecificationAddExistingSWDoc";    // apply
    public static final String cmdPartSpecificationCreateEtcDocument = "cmdPartSpecificationCreateEtcDocument";    // action에 validation 있음
    public static final String cmdPartSpecificationRemove = "cmdPartSpecificationRemove";  // apply
    public static final String cmdRequestUploadFile = "cmdRequestUploadFile"; //Apply
    public static final String cmdRequestDeleteFile = "cmdRequestDeleteFile"; //UpladFile과 동일 메소드 호출
    public static final String cmdPartChangeSiteFilter = "cmdPartChangeSiteFilter";
    public static final String cmdPartRefDocumentAddExisting = "cmdPartRefDocumentAddExisting";    // apply
    public static final String cmdPartRefDocumentDelete = "cmdPartRefDocumentDelete";  // apply
    public static final String cmdDemote = "cmdDemote";
    public static final String cmdSWFileEdit = "cmdSWFileEdit"; //Apply
    public static final String cmdSWDocEditDetail = "cmdSWDocEditDetail";  //Apply
    public static final String cmdSWDocCheckout = "cmdSWDocCheckout"; //Apply
    public static final String cmdSWDocCheckIn = "cmdSWDocCheckIn";  //Apply
    public static final String cmdSWDocDelete = "cmdSWDocDelete"; // Part Delete 화면으로 이동
    public static final String cmdSWDocumentHistory = "cmdSWDocumentHistory";
    public static final String cmdRequestCPCSaveFile = "cmdRequestCPCSaveFile";
    public static final String cmdCadDocDistFiles = "cmdCadDocDistFiles";
    public static final String cmdAddExistingCPCContactPoint = "cmdAddExistingCPCContactPoint";
    public static final String cmdRemoveCPCContactPoint = "cmdRemoveCPCContactPoint"; // applied
    public static final String cmdtbarDocDistDetailEdit = "cmdtbarDocDistDetailEdit"; // applied
    public static final String mnuChangeAttachmentListActions = "mnuChangeAttachmentListActions"; // applied
    public static final String cmdBOMCheckListUpload = "cmdBOMCheckListUpload";  // applied
    public static final String cmdChangeRelatedRequest = "cmdChangeRelatedRequest";
    public static final String cmdSMTProdMgmtSearchActionsSchedule = "cmdSMTProdMgmtSearchActionsSchedule"; //applied
    public static final String cmdDocAuthUpdate = "cmdDocAuthUpdate";
    public static final String cmdDocAuthAffectedDocAdd = "cmdDocAuthAffectedDocAdd";
    public static final String cmdDocAuthAffectedDocRemove = "cmdDocAuthAffectedDocRemove";
    public static final String cmdRequestViewTestStatus = "cmdRequestViewTestStatus"; // 타 시스템 JSP 호출로 인해 적용불가
    public static final String cmdRequestViewMoldStatus = "cmdRequestViewMoldStatus"; // 타 시스템 JSP 호출로 인해 적용불가
    public static final String cmdApprovalSheetFileEdit = "cmdApprovalSheetFileEdit"; //Apply
    public static final String cmdCreatePartDevelopmentRequest = "cmdCreatePartDevelopmentRequest"; //Not Used
    public static final String cmdTemplateCreate = "cmdTemplateCreate"; // apply
    public static final String cmdTemplateDelete = "cmdTemplateDelete"; // apply
    public static final String cmdTemplateEditUpload = "cmdTemplateEditUpload"; // apply
    public static final String cmdCreateItem = "cmdCreateItem";
    public static final String cmdCopyItem = "cmdCopyItem";
    public static final String cmdUpdateItem = "cmdUpdateItem";
    public static final String cmdDeleteItem = "cmdDeleteItem";
    public static final String cmdSaveApproverInfo = "cmdSaveApproverInfo";
    public static final String cmdAdminExcelUpload = "cmdAdminExcelUpload";
    public static final String cmdDistributeForLifeCycle = "cmdDistributeForLifeCycle";
    public static final String cmdChangeParticipantUser = "cmdChangeParticipantUser";
    public static final String cmdSaveEssentialApprover = "cmdSaveEssentialApprover";
    public static final String cmdBBSItemDelete = "cmdBBSItemDelete";
    public static final String cmdPartEditPolicy = "cmdPartEditPolicy";
    public static final String cmdPartEditStatus = "cmdPartEditStatus";
    public static final String cmdPartActivate = "cmdPartActivate";
    public static final String cmdLegacyLinkAdd = "cmdLegacyLinkAdd";
    public static final String cmdLegacyLinkEdit = "cmdLegacyLinkEdit";
    public static final String cmdLegacyLinkDelete = "cmdLegacyLinkDelete";
    public static final String cmdEtcDocCheckIn = "cmdEtcDocCheckIn";  //Apply
    public static final String cmdEtcDocCheckOut = "cmdEtcDocCheckOut";  //Apply
    public static final String cmdEctDocDetailEdit = "cmdEctDocDetailEdit";  //Apply
    public static final String cmdUserLoginSearch = "cmdUserLoginSearch";   // Apply
    public static final String cmdFileDownloadSearch = "cmdFileDownloadSearch";   // Apply
    public static final String cmdDeleteUserAuth = "cmdDeleteUserAuth";     // Apply
    public static final String cmdDeleteMCRequestReport = "cmdDeleteMCRequestReport";     // Relation disconnect
    public static final String cmdAddMCRequestReport = "cmdAddMCRequestReport";     // Relation disconnect
    public static final String cmdUserPortalManagement = "cmdUserPortalManagement"; // Apply
    public static final String cmdMechanicalCAEResultReportMC = "cmdMechanicalCAEResultReportMC"; // Apply
    public static final String cmdMechanicalCAEResultDashBoardMC = "cmdMechanicalCAEResultDashBoardMC"; // Apply
    public static final String cmdPCBManufacturingCostReport = "cmdPCBManufacturingCostReport"; // Apply // Apply
    public static final String cmdToBeObsoletePartReport = "cmdToBeObsoletePartReport";     // Apply
    public static final String cmdECOApproverReplacePerson = "cmdECOApproverReplacePerson";    // 2016.07.12 youngmi.won [C20160630_04617] ECO Approver 관리 화면에서 필수결재자 일괄 Replace 기능 추가
    public static final String cmdChangeIssue = "cmdChangeIssue";

    public static final String cmdRequestManUploadFile = "cmdRequestManUploadFile"; //Apply
    public static final String cmdRequestManDeleteFile = "cmdRequestManDeleteFile"; //UpladFile과 동일 메소드 호출

    //ECAD Library Request Type
    public static final String ECAD_REQUEST_TYPE_DELETE = "Delete";
    public static final String cmdPartEditStandard = "cmdPartEditStandard";
    public static final String cmdPartEditMaker = "cmdPartEditMaker";
    public static final String cmdPartEditManagement = "cmdPartEditManagement";
    public static final String cmdPartEditTechSpec = "cmdPartEditTechSpec";

    //SW Admin Role
    public static final String cmdSWTemplateCreate = "cmdSWTemplateCreate";
    public static final String cmdSWTemplateUpdate = "cmdSWTemplateUpdate";
    public static final String cmdSWTemplateDelete = "cmdSWTemplateDelete";
    public static final String cmdSWFileTypeCreate = "cmdSWFileTypeCreate";
    public static final String cmdSWFileTypeUpdate = "cmdSWFileTypeUpdate";
    public static final String cmdSWFileTypeDelete = "cmdSWFileTypeDelete";


    //PDEV Admin Role
    public static final String cmdMakerGorupCreate = "cmdMakerGorupCreate";
    public static final String cmdMakerGorupUpdate = "cmdMakerGorupUpdate";
    public static final String cmdMakerGorupDelete = "cmdMakerGorupDelete";
    public static final String cmdMakerGorupActive = "cmdMakerGorupActive";
    public static final String cmdMakerGorupInActive = "cmdMakerGorupInActive";
    public static final String cmdSaveEssentialApproverForRequest = "cmdSaveEssentialApproverForRequest";

    // OPBOM Menu Access Check
    public static final String cmdOpbomOptionTableRemove = "cmdOpbomOptionTableRemove";

    // PLM Menu Access Check Start

    public static final String cmdTemplateGetInfo = "cmdTemplateGetInfo";
    public static final String cmdEditActivityTemplate = "cmdEditActivityTemplate";
    public static final String cmdWBSGetInfo = "cmdWBSGetInfo";
    public static final String cmdEditActivity = "cmdEditActivity";
    public static final String cmdWBSScheduleInitialLoad = "cmdWBSScheduleInitialLoad";
    public static final String cmdWBSScheduleMemberRefresh = "cmdWBSScheduleMemberRefresh";
    public static final String cmdDeleteAllActivityToDo = "cmdDeleteAllActivityToDo";
    public static final String cmdDeleteActivityToDo = "cmdDeleteActivityToDo";
    public static final String cmdSkipActivity = "cmdSkipActivity";
    public static final String cmdSaveActivity = "cmdSaveActivity";
    public static final String cmdSaveActivityAll = "cmdSaveActivityAll";
    public static final String cmdChangeProcessType = "cmdChangeProcessType";

    // PLM Menu Access Check End

    //Catia 결재 및 썸네일 조회 용 공통 사용자
    public static final String  SmarTeamCommonUser = "gpdmapprover";

    public static final String UX_REVIEW_MAIL_USER  =  "ux-review@lge.com";
    public static final String UX_EP_MAIL_USER      =  "uxgov@lge.com";
    public static final String UX_ADMIN_MAIL_USER   =  "gv-center@lge.com";

    public static final String PIS_NOT_IN_ORG = "EVZ:VBZ:VLZ:VSZ";
    public static final String PIS_NOT_IN_BOM_ORG = "CM6";

    public static final String CHANGE_AUTH_CCR_AUTO_CREATE = "EVZ.EKHQ:VBZ.EKHQ:VLZ.EKHQ:VSZ.EKHQ";


    public static final String WEBSERVICE_APPROVAL_FLAG_MOBILE = "Y";
    public static final String WEBSERVICE_APPROVAL_FLAG_EP = "EP";
    public static final String AUTH_TYPE            = "AUTH_TYPE";
    public static final String AUTH_ROLE            = "AUTH_ROLE";
    public static final String FLAG_Y               = "Y";
    public static final String FLAG_N               = "N";
    public static final String BBS_MENU             = "MENU";
    public static final String BBS_CONTENTS         = "CONTENTS";

    // Interface Type
    public static final String IAM_USER_OUT = "IAM-USER-OUT";
    public static final String IAM_USER_RESPONSIBILITY_OUT = "IAM-USER_RESPONSIBILITY-OUT";
    public static final String CPC_SYSTEM = "CPC";

    // ECO essential approver line
    public static final String ECO_ESSENTIAL_APPROVER_ROLE_BOM_ADMIN = "BOM Admin";

    public static final String SW_ATTACHED_TYPE_DOCTEMPLATE  = "Attached From Document Template";
    public static final String SW_ATTACHED_TYPE_REQTEMPLATE  = "Attached From Request Template";
    public static final String SW_ATTACHED_TYPE_MANNUALLY    = "Attached Manually";
    public static final String SW_ATTACHED_TYPE_SPECIALLY    = "Attached Specially";
    public static final String SW_ATTACHED_TYPE_FOR_HISTORY  = "Attached For History";

    public static final String SW_ATTACHED_INFO_PNAME        = "partName";
    public static final String SW_ATTACHED_INFO_POBID        = "partObid";
    public static final String SW_ATTACHED_INFO_PREV         = "partRevision";
    public static final String SW_ATTACHED_INFO_PDESC        = "partDescription";
    public static final String SW_ATTACHED_INFO_PSPEC        = "partSpecification";
    public static final String SW_ATTACHED_INFO_DOBID        = "docObid";
    public static final String SW_ATTACHED_INFO_DNAME        = "docName";
    public static final String SW_ATTACHED_INFO_DREV         = "docRevision";
    public static final String SW_ATTACHED_INFO_DDESC        = "docDescription";
    public static final String SW_ATTACHED_INFO_DSPEC        = "docSpecification";

    public static final String ECO_TYPE_GENERAL = "1";
    public static final String ECO_TYPE_SW_TEMPORARY = "9";

    public static final String CODE_DESIGN_REQUESTS_APERTURE_1 = "DR_APERTURE1";
    public static final String CODE_DESIGN_REQUESTS_APERTURE_2 = "DR_APERTURE2";
    public static final String CODE_DESIGN_REQUESTS_APERTURE_3 = "DR_APERTURE3";

    // Interface System Name
    public static final String SYSTEM_NAME_PLM = "PLM";
    public static final String SYSTEM_NAME_MDMS = "MDMS";
    public static final String SYSTEM_NAME_MDMSPDR = "MDMSPDR";
    public static final String SYSTEM_NAME_MDMSMODEL = "MDMSMODEL";
    public static final String SYSTEM_NAME_GPDM = "GPDM";
    public static final String SYSTEM_NAME_GPDMSCH = "GPDMSCH";
    public static final String SYSTEM_NAME_PUMDM = "PUMDM";
    public static final String SYSTEM_NAME_NPT   = "NPT";
    public static final String SYSTEM_NAME_DGMS = "DGMS";
    public static final String SYSTEM_NAME_GFIN = "GFIN";
    public static final String SYSTEM_NAME_GBMS = "GBMS";
    public static final String SYSTEM_NAME_GMFG = "GMFG";
    public static final String SYSTEM_NAME_GB2BI = "GB2BI";
    public static final String SYSTEM_NAME_PIMS = "PIMS";
    public static final String SYSTEM_NAME_SCM = "SCM";
    public static final String SYSTEM_NAME_PRM = "PRM";
    public static final String SYSTEM_NAME_GCIS = "GCIS";
    public static final String SYSTEM_NAME_GPIN = "GPIN";
    public static final String SYSTEM_NAME_GCRC = "GCRC";
    public static final String SYSTEM_NAME_PROJECT = "PRJECTINFO";
    public static final String SYSTEM_NAME_IAM2 = "IAM2";
    public static final String SYSTEM_NAME_GSCP = "GSCP";
    public static final String SYSTEM_NAME_GRIP = "GRIP";
    public static final String SYSTEM_NAME_GPRI = "GPRI";
    public static final String SYSTEM_NAME_MES = "MES";
    public static final String SYSTEM_NAME_MDC = "MDC";
    public static final String SYSTEM_NAME_DQMS = "DQMS";
    public static final String SYSTEM_NAME_GCUV = "GCUV";
    public static final String SYSTEM_NAME_ARS = "ARS";
    public static final String SYSTEM_NAME_B2BPS = "B2BPS";
    public static final String SYSTEM_NAME_SDMS = "SDMS";
    public static final String SYSTEM_NAME_PDRCOM = "PDRCom";
    public static final String SYSTEM_NAME_HROUT= "HROut";
    public static final String SYSTEM_NAME_PROJECTAPP= "ProjectApp";
    public static final String SYSTEM_NAME_CPC = "CPC";
    
    // Interface Out
    public static final String IF_TYPE_NPT_OUT01 = "NPT001";
    public static final String IF_TYPE_NPT_OUT02 = "NPT002";
    public static final String IF_TYPE_NPT_OUT03 = "NPT003";
//    public static final String IF_TYPE_SCM_OUT01 = "SCM001";
//    public static final String IF_TYPE_SCM_OUT02 = "SCM002";
    public static final String IF_TYPE_SCM_OUT03 = "SCM003";
    public static final String IF_TYPE_GPDM_OUT01 = "GPDM001";
//    public static final String IF_TYPE_GPDM_OUT02 = "GPDM002";
    public static final String IF_TYPE_GPDM_OUT03 = "GPDM003";
    public static final String IF_TYPE_GPDM_OUT04 = "GPDM004";
    public static final String IF_TYPE_GRIP_OUT01 = "GRIP001";
    public static final String IF_TYPE_BPM_OUT01 = "BPM001";
    public static final String IF_TYPE_BPM_OUT02 = "BPM002";
    public static final String IF_TYPE_BPM_OUT03 = "BPM003";
    public static final String IF_TYPE_GSCP_OUT01 = "GSCP001";
    public static final String IF_TYPE_HR_OUT01 = "HR001";
    public static final String IF_TYPE_HR_OUT02 = "HR002";
    public static final String IF_TYPE_HR_OUT03 = "HR003";
    public static final String IF_TYPE_B2BPS_OUT01 = "B2B001";
    public static final String IF_TYPE_B2BPS_OUT02 = "B2B002";
    public static final String IF_TYPE_PDRCOM = "OUT001";
    public static final String IF_TYPE_MDMS_OUT01 = "MDMS001";
    public static final String IF_TYPE_MES_OUT01 = "MES001";
    public static final String IF_TYPE_GPIN_OUT01 = "GPIN001";
    public static final String IF_TYPE_PROJECTAPP_OUT01 = "PJT001";
    public static final String IF_TYPE_PROJECTAPP_OUT02 = "PJT002";
    public static final String IF_TYPE_CPC_OUT01 = "CPC001";
    
    // Transaction
    public static final String TR_ID_NPT_OUT = "NPT-OUT";
//    public static final String TR_ID_SCM_OUT = "SCM-OUT";
    public static final String TR_ID_GPDM_OUT = "GPDM-OUT01";
    public static final String TR_ID_GPDM_OUT03 = "GPDM-OUT03";
    public static final String TR_ID_GPIN_OUT = "GPIN-OUT";
    public static final String TR_ID_GCUV_OUT = "GCUV-OUT";
    public static final String TR_ID_B2B_OUT01 = "B2B-OUT01";
    public static final String TR_ID_HR_OUT01 = "HR-OUT01";
    public static final String TR_ID_GSCP_OUT = "GSCP-OUT";
    public static final String TR_ID_GMES_OUT = "GMES-OUT";

    public static final String TR_ID_PROJECT_COM_OUT = "PJTComOut";
    public static final String TR_ID_PROJECT_GQIS_OUT = "PROJECT-GQIS-OUT";
    public static final String TR_ID_PROJECT_NPT_OUT = "PROJECT-NPT-OUT";
    public static final String TR_ID_PROJECT_ARS_OUT = "PROJECT-ARS-OUT";

    // PDR Common Type
    public static final String PDR_IF_TYPE_COM_OUT_001 = "OUT001";
    public static final String PDR_IF_TYPE_COM_OUT_002 = "OUT002";
    public static final String PDR_IF_TYPE_COM_OUT_003 = "OUT003";
    public static final String PDR_IF_TYPE_COM_OUT_004 = "OUT004";
    public static final String PDR_IF_TYPE_COM_OUT_005 = "OUT005";
    
    public static final String TR_ID_VCSW_IFID = "VCSW_IFID";

    // Table sequence
    public static final String TB_SEQ_CUST_SUFFIX = "CUST-SUFFIX";

    public static final String[][] PROJECT_CHANGE_TRIGGER_LIST = {
            {INF_TARGET_SYSTEM_PROJECT_GCRC,INF_TARGET_SYSTEM_PROJECT_001},
            {INF_TARGET_SYSTEM_PROJECT_GERP,INF_TARGET_SYSTEM_PROJECT_001},
            {INF_TARGET_SYSTEM_PROJECT_PRODSITE,INF_TARGET_SYSTEM_PROJECT_001},
            {INF_TARGET_SYSTEM_PROJECT_EVENTQTY,INF_TARGET_SYSTEM_PROJECT_001},
            // {INF_TARGET_SYSTEM_PROJECT_MODEL,INF_TARGET_SYSTEM_PROJECT_001},
            // {INF_TARGET_SYSTEM_PROJECT_MODEL_CHGINFO,INF_TARGET_SYSTEM_PROJECT_001},
            {INF_TARGET_SYSTEM_PROJECT_DOC,INF_TARGET_SYSTEM_PROJECT_DOC_001},
            {INF_TARGET_SYSTEM_PROJECT_SCHEDULE_CHN,INF_TARGET_SYSTEM_PROJECT_SCHEDULE_001},
            {INF_TARGET_SYSTEM_GMAP,INF_TARGET_SYSTEM_GMAP_001}
            //{INF_TARGET_SYSTEM_PROJECT_SCHEDULE_D,INF_TARGET_SYSTEM_PROJECT_SCHEDULE_001}
    };

    public static final String[][] DOCTEMP_CREATE_TRIGGER_LIST = {
            {INF_TARGET_SYSTEM_PROJECT_DOC,INF_TARGET_SYSTEM_PROJECT_DOC_002} //PTDOCTEMPLATEMASTER[ProjectActivityDocumentTemplate] 의 Obid 필요. HE:TV, 모니터, PC
    };

    // IMC OUT 인터페이스 목록.
    public static final String[][] IMC_OUT_TRIGGER_LIST = {
            {INF_TARGET_SYSTEM_PROJECT_IMC,INF_TARGET_SYSTEM_PROJECT_IMC_001}
    };

    public static final String[] PCZ_GRADE = {
        "S","A", 
        "B_MU", "B_HW","B_SW","B_ME","B_MO","B",
        "C_MU","C_HW",   "S_SW",       "C_SW","C_ME","C_MO", "C4",
        "D_MU","D_HW","D_SW","D_ME","D_MO", "D_VA",
        "D5a","D5b","D5c","D5d1","D5d2", "D5m", "D5",
        "Z_mr","Z_ba"
        // 기존 순서 발췌 "S_SW","C_SW","C_ME","D_HW","D_SW","D_ME","D_MU"
    };

    public static final String[] COMP_GRADE = {
            "S","A","B","C","C1","C2","T","D"
    };
    /*
ACTLGECP00012   수익성 분석  NPT
ACTLGEPP00041   수익성 분석  NPT
ACTLGEDV00061   수익성 분석  NPT
ACTLGEPV00083   수익성 분석  NPT
ACTLGEPQ00098   수익성 분석  NPT
     */

    public static final String[] NPT_ACTIVITY_CODE = {
            "ACTLGECP00012","ACTLGEPP00041","ACTLGEDV00061","ACTLGEPV00083","ACTLGEPQ00098",
            "ACTVAZPV21949","ACTVAZCP21945","ACTVAZPP21947","ACTVAZDV21948","ACTVAZCP21925",
            "ACT0000000008","ATM-00000001122"
        };

    public static final String[] NPT_CPUNIT_ACTIVITY_CODE = {
            "ACT0000000008", "ACT0000000702","ATM-00000000867"
        };

    public static final String[] GRIP_ACTIVITY_CODE = {
            "ATM-00000001268","ATM-00000001269","ATM-00000001270","ATM-00000001271"
        };

    
    public static final String GRIP_FDR_ACTIVITY_CODE = "ATM-00000001270";
    // WBS inbox task
    public static final String WBS_INBOX_TASK_TYPE = "WBS Activity";
    public static final String WBS_APPROVERS_RESPONSIBILITY = "Engineering";
    public static final String WBS_TASK_REQUIREMENT = "Optional";
    public static final String WBS_DATE_OFFSET_FROM = "Task Create Date";
    public static final String WBS_ACTION_COMMENTS = "Approve";
    public static final String INBOXTASK_TYPE_WORKFLOW = "Workflow";
    public static final String INBOXTASK_TYPE_WBSACTIVITY = "WBS Activity";


    // Design Change Drop reason
    public static final String DESIGN_CHANGE_DROP_RCD_01 = "시장/사업 환경변화";
    public static final String DESIGN_CHANGE_DROP_RCD_02 = "사업자 요청";
    public static final String DESIGN_CHANGE_DROP_RCD_03 = "수익성 제고";
    public static final String DESIGN_CHANGE_DROP_RCD_04 = "품평회 결과";
    public static final String DESIGN_CHANGE_DROP_RCD_05 = "기타";

    public static final String CP_UNIT_PROFIT_ACTIVITY = "ACT0000000008,ATM-00000000867,ATM-00000000868,ACT0000000702";
    public static final String CP_UNIT_PROFIT_ACTIVITY_VC = "ACT0000000804";

    public static final String CTO_GATE_REVIEW_ACTIVITY = "ACT0000000222";

    // Budget System, Interface ID
    public static final String INF_TARGET_SYSTEM_BUDGET = "GbmsBudget";     // Project 호출 Batch
    public static final String IF_TYPE_BGT_OUT01 = "BGT001";                // 결재시 호출
    public static final String IF_TYPE_BGT_OUT02 = "BGT002";                // 완료시 예산 환원
    public static final String IF_TYPE_BGT_OUT03 = "BGT003";                // 생기원 프로젝트 저장품 예산 추가/삭감

    public static final String INF_TARGET_SYSTEM_BUDGET_GL = "BudgetGLAmt"; // GL금액 예산테이블에 insert/update 하는 Batch
    public static final String INF_TARGET_SYSTEM_BUDGET_CARRIED = "BudgetCarried"; // 예산이월 Batch

    // GL Transfer
    public static final String INF_TARGET_SYSTEM_GLTRANSFER = "GLTransfer";
    public static final String IF_TYPE_GLTRANSFER01 = "GL001";

    // Excel Export 
    public static final int EXCEL_EXPORT_FROM_CLIENT = 1;
    public static final int EXCEL_EXPORT_FROM_SERVER = 2;
    public static final String EXPORT_TEXT_TYPE_PLAIN = "plain"; // default
    public static final String EXPORT_TEXT_TYPE_RTF   = "rtf";

    public static final String DUMMY_EVENT = "Dummy";

    public static final String ID_GEN_IF_ACTIVITY = "IF_ACTIVITY";

    public static final String DESIGN_ACTIVITY_CONCEPT = "Concept";
    public static final String DESIGN_ACTIVITY_RENDERING = "Rendering";
    public static final String DESIGN_ACTIVITY_MOCKUP = "Mock up";
    public static final String DESIGN_ACTIVITY_FOLLOWUP = "FollowUp";

    public static final String GLZ_SCREEN_SIZE_ITEM_ID = "MSPI00000000000001280";
    public static final String GMZ_SCREEN_SIZE_ITEM_ID = "MSPI00000000000002474";
    
 /** BRM 영역 정의 Start */

    public static final String IDGEN_IDKEY_BRM_SSCN = "SupplierServiceCostName";
    public static final String IDGEN_IDKEY_DEVELOPMENTCOSTDEVTYPE   = "DevelopmentCostDevType";
    public static final String IDGEN_IDKEY_DEVELOPMENTECMSLABERCOST = "ECMSLaberCost";
    //업체별 용역비
    public static final String IDGEN_IDKEY_DEVELOPMENTECMSRESEARCH  = "ECMSResearch";
    //ECMS 규격인증
    public static final String IDGEN_IDKEY_ECMSSTDAUTH              = "ECMSStdauth";
    //ECMS Sample비
    public static final String IDGEN_IDKEY_ECMSSAMPLECOST           = "ECMSSampleCost";
    //ECMS 출장비
    public static final String IDGEN_IDKEY_ECMSTRAVELEXPENSES       = "ECMSTravelExpenses";
    //ECMS 기타경비//
    public static final String IDGEN_IDKEY_ECMSOTHERCOST            = "ECMSOtherCost";
    //ECMS 장비투자
    public static final String IDGEN_IDKEY_ECMSEQUIPINVESTCOST      = "ECMSEquipInvestCost";
    //ECMS 개발비회수
    public static final String IDGEN_IDKEY_ECMSDEVCOSTRECOVERY      = "ECMSDevCostRecovery";
    //Summary 시나리오
    public static final String IDGEN_IDKEY_ECMSSUMMARYSCENARIO      = "ECMSSummaryScenario";
    //Summary 개발Type
    public static final String IDGEN_IDKEY_ECMSSUMMARYDEVTYPE       = "ECMSSummaryDevType";
    //scenario
    public static final String IDGEN_IDKEY_ECMSSCINARIO             = "ECMSScinario";
    //SupplierServiceCostDetail
    public static final String IDGEN_IDKEY_SUPPLIERSERVICECOSTDETAIL= "SupplierServiceCostDetail";

    //ProjectBudgetTempPlan Batch Seq
    public static final String IDGEN_IDKEY_PROJECTBUDGETTEMPPLAN    = "ProjectBudgetTempPlan";

    /** BRM 영역 정의 End */

    public static final String DOCUMENT_DOWNLOAD_AUTH_MANAGER       = "Manager";
    public static final String DOCUMENT_DOWNLOAD_AUTH_ADMIN       = "Admin";

    /** DRM **/
    public static final String PROCESS_FAIL = "PROCESS FAIL"; // 프로세스가 떠있지 않음
    public static final String NOT_EXIST = "NOT EXIST"; // 암호화 대상 파일이 존재하지 않음

    public static final String ENC_FAIL = "ENC FAIL"; // 암호화 실패
    public static final String ENC_ZERO = "ENC ZERO"; // 복호화 실패

    public static final String DEC_FAIL = "DEC FAIL"; // 복호화 실패
    public static final String DEC_ZERO = "DEC ZERO"; // 암호화 실패
    
    public static final String DOWN_OPTION_RAW = "Raw"; //원본파일
    public static final String DOWN_OPTION_ENCRYPTION = "Encryption"; //DRM 적용
    
    public static final String PDF_DIRECTION_LANDSCAPE = "landscape";
    public static final String PDF_DIRECTION_PORTRAIT  = "portrait";       
    
    
    /** OEM */
    public static final String IDGEN_IDKEY_OEMGROUPPROJECT = "GroupProject";
    
    
    /** 부서별 개발담당 관리 */
    public static final String IDGEN_IDKEY_DEVUNIT = "Dev Unit Name";
    public static final String IDGEN_IDKEY_DEVSTD_NAME = "DevCost Std Name";
    public static final String IDGEN_IDKEY_DEVSTD_OPTION_NAME = "DevCost Std Option";
    
    /** 용역 GP건 지불유형 */
    public static final String DOWN_PAYMENT = "ADV";
    public static final String MIDDLE_PAYMENT  = "MDI";
    public static final String BALANCE_PAYMENT = "BAL";
    public static final String ETC_PAYMENT = "AFA";
    public static final String DELIVERY_PAYMENT = "UPD";
    
    /** 정산화면 정산, 품의서 조정 Action */
    public static final String BUDGET_ADJUSTMENT_ACTION_CANCEL = "Cancel Settlement";
    public static final String BUDGET_ADJUSTMENT_ACTION_REJECT = "Reject Consult";
    public static final String BUDGET_ADJUSTMENT_ACTION_MOVE = "Move Month";
}
