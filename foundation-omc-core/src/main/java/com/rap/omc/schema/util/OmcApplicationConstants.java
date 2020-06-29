/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcApplicationConstatns.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 27.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.util;

/**
 * <pre>
 * Class : OmcApplicationConstatns
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcApplicationConstants {
    
    
    public static final String MAP_API_REVISE_NEW_OBJECT        = "omcRevisedNewObjectVO";
    
    public static final String CONN_FROM_CONNECTED_FROM         = "FromConnectedWithFrom";
    public static final String CONN_FROM_CONNECTED_MASTER       = "FromConnectedWithMaster";
    public static final String CONN_FROM_CONNECTED_THIS         = "FromConnectedWithThis";
    public static final String CONN_FROM_CONNECTED_TO           = "FromConnectedWithTo";
    public static final String CONN_MASTER_CONNECTED_FROM       = "MasterConnectedWithFrom";
    public static final String CONN_MASTER_CONNECTED_THIS       = "MasterConnectedWithThis";
    public static final String CONN_MASTER_CONNECTED_TO         = "MasterConnectedWithTo";
    public static final String CONN_THIS_CONNECTED_FILE         = "ThisConnectedWithFile";
    public static final String CONN_THIS_CONNECTED_FROM         = "ThisConnectedWithFrom";
    public static final String CONN_THIS_CONNECTED_MASTER       = "ThisConnectedWithMaster";
    public static final String CONN_THIS_CONNECTED_TO           = "ThisConnectedWithTo";
    public static final String CONN_TO_CONNECTED_FROM           = "ToConnectedWithFrom";
    public static final String CONN_TO_CONNECTED_MASTER         = "ToConnectedWithMaster";
    public static final String CONN_TO_CONNECTED_THIS           = "ToConnectedWithThis";
    public static final String CONN_TO_CONNECTED_TO             = "ToConnectedWithTo";
    public static final String CONN_TO_THIS_ALIAS               = "this";
    public static final String[] OMC_SEARCH_CONN_LSITS = {CONN_FROM_CONNECTED_FROM      ,CONN_FROM_CONNECTED_MASTER ,CONN_FROM_CONNECTED_THIS,CONN_FROM_CONNECTED_TO,CONN_MASTER_CONNECTED_FROM,
                                                          CONN_MASTER_CONNECTED_THIS    ,CONN_MASTER_CONNECTED_TO   ,CONN_MASTER_CONNECTED_TO,CONN_THIS_CONNECTED_FILE,CONN_THIS_CONNECTED_FILE,CONN_THIS_CONNECTED_FROM,CONN_THIS_CONNECTED_FROM,CONN_THIS_CONNECTED_MASTER,
                                                          CONN_THIS_CONNECTED_TO        ,CONN_TO_CONNECTED_FROM     ,CONN_TO_CONNECTED_FROM,CONN_TO_CONNECTED_MASTER,CONN_TO_CONNECTED_MASTER,CONN_TO_CONNECTED_THIS,CONN_TO_CONNECTED_TO};
    
    public static final String WF_RCA_PROMOTE_CONNECTED_OBJECT  = "Promote Connected Object";
    public static final String WF_RCA_NOTIFY_ROUTE_OWNER        = "Notify Route Owner";
    public static final String WF_RCA_NONE                      = "None";
    public static final String WF_RCA_NOTIFY_OBJECT_OWNER       = "Notify Object Owner";
    public static final String[] WF_RCA_LSITS = {WF_RCA_PROMOTE_CONNECTED_OBJECT,WF_RCA_NOTIFY_ROUTE_OWNER,WF_RCA_NONE,WF_RCA_NOTIFY_OBJECT_OWNER};
    
    
    public static final String WF_ROUTE_PURPOSE_Standard        = "Standard";
    public static final String WF_ROUTE_PURPOSE_Distribution    = "Distribution";
    public static final String WF_ROUTE_PURPOSE_Approval        = "Approval";
    public static final String WF_ROUTE_PURPOSE_Review          = "Review";
    public static final String WF_ROUTE_PURPOSE_Confirmation    = "Confirmation";
    public static final String[] WF_ROUTE_PURPOSE_LISTS = {WF_ROUTE_PURPOSE_Standard,WF_ROUTE_PURPOSE_Distribution,WF_ROUTE_PURPOSE_Distribution,WF_ROUTE_PURPOSE_Approval,WF_ROUTE_PURPOSE_Review,WF_ROUTE_PURPOSE_Confirmation};
    
    public static final String WF_ROUTE_AUTO_START_ON_REJECT_TRUE        = "TRUE";
    public static final String WF_ROUTE_AUTO_START_ON_REJECT_FALSE       = "FALSE";
    public static final String[] WF_ROUTE_AUTO_START_ON_REJECT_LISTS = {WF_ROUTE_AUTO_START_ON_REJECT_TRUE,WF_ROUTE_AUTO_START_ON_REJECT_FALSE};
    
    public static final String WF_ROUTE_HOW_TO_ON_REJECT_Immediate        = "Immediate";
    public static final String WF_ROUTE_HOW_TO_ON_REJECT_Defererred       = "Defererred";
    public static final String[] WF_ROUTE_HOW_TO_ON_REJECT_LISTS = {WF_ROUTE_HOW_TO_ON_REJECT_Immediate,WF_ROUTE_HOW_TO_ON_REJECT_Defererred};
    
    public static final String WF_INBOX_TASK_AUTO_COMPLETE_TRUE        = "TRUE";
    public static final String WF_INBOX_TASK_AUTO_COMPLETE_FALSE       = "FALSE";
    public static final String[] WF_INBOX_TASK_AUTO_COMPLETE_LISTS = {WF_INBOX_TASK_AUTO_COMPLETE_TRUE,WF_INBOX_TASK_AUTO_COMPLETE_FALSE};
    
    public static final String WF_PARALLEL_PROCESS_RULE_All        = "All";
    public static final String WF_PARALLEL_PROCESS_RULE_Any        = "Any";
    public static final String[] WF_PARALLEL_PROCESS_RULE_LISTS = {WF_PARALLEL_PROCESS_RULE_All,WF_PARALLEL_PROCESS_RULE_Any};
    
    public static final String FILE_OUTATA_fileLocationPhysicalPath    = "fileLocationPhysicalPath";
    public static final String FILE_OUTATA_fileServiceUrl              = "fileServiceUrl";
    
    public static final String FILE_OUTATA_CHECKOUT_URL                  = "serviceCheckOutUrl";
    public static final String FILE_OUTATA_CHECKIN_URL                   = "serviceCheckInUrl";
    public static final String FILE_OUTATA_CHECKOUT_FROMFMSTOSERVER_URL  = "serviceCheckOutFromFmsToServerUrl";
    public static final String FILE_OUTATA_CHECKIN_FROMFMSTOSERVER_URL   = "serviceCheckInFromFmsToServerUrl";
    public static final String FILE_OUTATA_CHECKOUT_ALL_URL              = "serviceCheckOutAllUrl";
    public static final String FILE_OUTATA_DELETE_FILE_FROMFMSTOSERVERURL= "serviceDeleteFileFromFmsToServerUrl";
    public static final String FILE_STORE_LOCATION_LOCATION = "Location";
    public static final String FILE_STORE_LOCATION_STORE    = "Store";
    
    public static final String FILES_BIZ_CLASS  = "Files";
    public static final String FILES_LIFE_CYCLE = "File Policy";
    public static final String FILES_STATES     = "Exists";
    
    public static final String[] MODIFY_OBJECT_SET_NOT_ALLOWED_ATTRS = {"obid","flags","className","locker","checkouter",
                                                                       "checkouted","owner","creator","created","modifier",
                                                                       "modified","names","lifeCycle","states","branchTo",
                                                                       "revision","previousObid","nextObid","fromClass","fromObid","toClass","toObid"};
}
