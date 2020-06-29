/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ProjectConstants.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 8. 9.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.constants;

import com.rap.omc.schema.util.OmcUniqueIDGenerator;



/**
 * <pre>
 * Class : ProjectConstants
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class ProjectConstants {
    public static final String PROJECT_WBSSTANDARD_SPEC_CLASS_NAME  = "com.rap.projectbase.wbs.util.StandardSpecForActivity";
    public static final String PROJECT_BASIS_COMMON_DIVISION = "ZZZ";
    
    public static final String PROJECT_LIFE_CYCLE_NAME_NPI = "NPI";
    
    public static final String ADD_PHASE_TO_LIFECYCLE_MAP_SEQUENCE = OmcUniqueIDGenerator.getObid();
    public static final String USING_PHASE_TO_LIFECYCLE_MAP_DISPLAY_CODE = OmcUniqueIDGenerator.getObid();
    public static final String USING_PHASE_TO_LIFECYCLE_MAP_DISPLAY_NAME = OmcUniqueIDGenerator.getObid();
    public static final String USING_PHASE_TO_LIFECYCLE_MAP_SEQUENCE = OmcUniqueIDGenerator.getObid();
    public static final String USING_PHASE_TO_LIFECYCLE_MAP_IS_ACTIVE = OmcUniqueIDGenerator.getObid();
    public static final String USING_PHASE_TO_LIFECYCLE_MAP_DIVISION = OmcUniqueIDGenerator.getObid();
    public static final String WILL_BE_DELETED_USING_PROJECTPHASE_MAP = OmcUniqueIDGenerator.getObid();
    
    
    
    public static final String CREATE_CREATE_MAP_PROJECT = OmcUniqueIDGenerator.getObid();
    public static final String CREATE_CREATE_MAP_USERS   = OmcUniqueIDGenerator.getObid();
    public static final String PROJECT_ROLE_TYPE_FROM_TEMPLATE = "From Template";
    public static final String PROJECT_ROLE_TYPE_FROM_PROJECT = "From Project";
    public static final String PROJECT_USER_TYPE_REGULAR = "Regular";
    
    public static final String REVISE_SCHEDULE_MAP_PROJECTVO = OmcUniqueIDGenerator.getObid();
    public static final String CREATE_WBSITEM_SCHEDULE_MAP_PROJECTVO = OmcUniqueIDGenerator.getObid();
    
    public static final String NONE = "None";
    public static final String NA = "N/A";
    public static final String ROLE_PROJECT_LEADER = "PROJECTLEADER";
    public static final String ROLE_SUB_PROJECT_LEADER = "SUBPJTLEADER";
    public static final String PROJECT_OUTDATA_ROLE_LIST = "projectUserRoleList";
    public static final String PROJECT_OUTDATA_ROLE_CODE_LIST = "projectUserRoleCodeList";
    public static final String PROJECT_OUTDATA_ROLE_IS_MAIN = "isMainMember";
    public static final String[] PROJECT_RUNNING_STATE_ARRAY = {"Startup","Running","Exists"};
    public static final String[] PROJECT_COMPLETE_STATE_ARRAY = {"Completed"};
    public static final String[] PROJECT_PLANNED_STATE_ARRAY = {"Save Draft"};
    public static final String PROJECT_OMAP_KEY_OEM = "oemMasterObidKey";
    public static final String PROJECT_OMAP_KEY_COMMON_REL = "commonRelationKey";
    public static final String PROJECT_OMAP_KEY_TEMPLATE_NASTER = "templateMaster";
    
    public static final String PROJECT_OMAP_KEY_TEMPLATE_NASTER_VO = OmcUniqueIDGenerator.getObid();
    public static final String PROJECT_OMAP_KEY_PROJECT_VO         = OmcUniqueIDGenerator.getObid();
    
    public static final String WBS_TEMPLATE_RecommendedDocumentTemplatesList = OmcUniqueIDGenerator.getObid();
    public static final String WBS_TEMPLATE_DependencyList                   = OmcUniqueIDGenerator.getObid();
    
    public static final int WBS_TEMPLATE_EXCEL_TEMPATE_OBID      = 0;
    public static final int WBS_TEMPLATE_EXCEL_ACTIVITY_OBID     = 1;
    public static final int WBS_TEMPLATE_EXCEL_ACTIVITY_CODE     = 2;
    public static final int WBS_TEMPLATE_EXCEL_ACTIVITY_TYPE     = 3;
    public static final int WBS_TEMPLATE_EXCEL_ACTIVITY_NAME     = 4;
    public static final int WBS_TEMPLATE_EXCEL_ACTIVITY_LEVEL    = 5;
    public static final int WBS_TEMPLATE_EXCEL_ACTIVITY_ID       = 6;
    public static final int WBS_TEMPLATE_EXCEL_ACTIVITY_PRE_LSIT = 7;
    public static final int WBS_TEMPLATE_EXCEL_ACTIVITY_DURATION = 8;
    public static final int WBS_TEMPLATE_EXCEL_ACTIVITY_ROLE     = 9;
    
    public static final int WBS_TEMPLATE_MAX_LEVEL               = 10;
    public static final int WBS_MAX_LEVEL                        = 10;
    public static final int WBS_MAX_DEPENDENCY_LEVEL             = 500;
    
    
    public static final int WBS_PARENT_START_LEVEL               = 6;
    
    public static final String GANTT_SORT_ORDER_KEY              = "GanttSortOrderKey";
    
    public static final String WBS_DEPENDENCY_TYPE_START_TO_START   = "SS";
    public static final String WBS_DEPENDENCY_TYPE_FINISH_TO_START  = "FS";
    public static final String WBS_DEPENDENCY_TYPE_FINISH_TO_FINISH = "FF";
    public static final String WBS_DEPENDENCY_TYPE_START_TO_FINISH  = "SF";
    
    public static final String[] WBS_DEPENDENCY_TYPE_VALID_SET        = {WBS_DEPENDENCY_TYPE_FINISH_TO_START,WBS_DEPENDENCY_TYPE_START_TO_START};
    
    public static final String INTF_PROJECT_RELEASE_HEADER        = OmcUniqueIDGenerator.getObid();
    public static final String INTF_PROJECT_RELEASE_WBS_HEADER    = OmcUniqueIDGenerator.getObid();
    public static final String INTF_PROJECT_RELEASE_WBS_DETAIL    = OmcUniqueIDGenerator.getObid();
    public static final String INTF_PROJECT_RELEASE_WBS_MEMBER    = OmcUniqueIDGenerator.getObid();
    public static final String INTF_PROJECT_RELEASE_WBS_EXECUTER  = OmcUniqueIDGenerator.getObid();
    public static final String[] INTF_PROJECT_RELEASE_VALID_ARRAY  = {INTF_PROJECT_RELEASE_HEADER,INTF_PROJECT_RELEASE_WBS_HEADER,INTF_PROJECT_RELEASE_WBS_DETAIL,INTF_PROJECT_RELEASE_WBS_MEMBER,INTF_PROJECT_RELEASE_WBS_EXECUTER};
    
    public static final String[] WBS_HEADER_RELEASED_SET  = {ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_ACTIVE,ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_STARTED,ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_COMPLETED};
    
    public static final String[] SCHEDULE_REVISIBLE_STATE_SET  = {ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_APPROVALPROCESSING,
                                                                  ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_ACTIVE,
                                                                  ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_STARTED};
    
    public static final String[] SCHEDULE_INTERFACE_STATE_SET  = {ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_STARTED,
                                                                  ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_ACTIVE};
    public static final String[] SCHEDULE_STARTABLE_STATE_SET  = {ApplicationSchemaConstants.STATE_PROJECT_SCHEDULE_ACTIVE};
    
    public static final String[] WBSITEM_REVISIBLE_STATE_SET_ALL  = {ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED,
                                                                     ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED,
                                                                     ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED,
                                                                     ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED};//ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED N李� �닔�뻾�뿉�꽌留� 媛��뒫
    
    public static final String[] WBSITEM_REVISIBLE_STATE_SET_GENERAL  = {ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED,
                                                                         ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SKIPPED,
                                                                         ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED};
                                                                
    public static final String[] WBSITEM_REVISIBLE_STATE_SET_N_EXEC  = {ApplicationSchemaConstants.STATE_WBS_ACTIVITY_COMPLETED};

    public static final String[] WBSITEM_RUNNING_STATE_SET  = {ApplicationSchemaConstants.STATE_WBS_ACTIVITY_SCHEDULED, ApplicationSchemaConstants.STATE_WBS_ACTIVITY_STARTED};
    
    
    public static final String WBS_REVISE_MAP_TARGET_LIST              = OmcUniqueIDGenerator.getObid();
    public static final String WBS_REVISE_MAP_TARGET_MAP               = OmcUniqueIDGenerator.getObid();
    public static final String WBS_REVISE_MAP_REVISED_LIST             = OmcUniqueIDGenerator.getObid();
    public static final String WBS_REVISE_MAP_REVISED_MAP              = OmcUniqueIDGenerator.getObid();
    public static final String WBS_REVISE_MAP_OBJECT_MAPPING_MAP       = OmcUniqueIDGenerator.getObid();
    public static final String WBS_REVISE_MAP_hasSubWBSItemsList       = OmcUniqueIDGenerator.getObid();
    public static final String WBS_REVISE_MAP_wbsDependencyList        = OmcUniqueIDGenerator.getObid();
    public static final String WBS_REVISE_MAP_assignedToActivityList   = OmcUniqueIDGenerator.getObid();
    public static final String WBS_REVISE_MAP_allocatedToMemberList    = OmcUniqueIDGenerator.getObid();
    public static final String WBS_REVISE_MAP_createdContextList       = OmcUniqueIDGenerator.getObid();
    public static final String WBS_REVISE_MAP_deletedContextList       = OmcUniqueIDGenerator.getObid();
    
    public static final String WBS_SCHEDULE_SIMULATION_MAP_forcastedStartDate     = "forcastedStartDate";
    public static final String WBS_SCHEDULE_SIMULATION_MAP_forcastedEndDate       = "forcastedEndDate";
    public static final String WBS_SCHEDULE_SIMULATION_MAP_delayedDays            = "delayedDays";
    public static final String WBS_SCHEDULE_SIMULATION_MAP_projectDuration        = "projectDuration";
    public static final String WBS_SCHEDULE_SIMULATION_MAP_originPlanStartDate    = "originPlanStartDate";
    public static final String WBS_SCHEDULE_SIMULATION_MAP_originPlanEndDate      = "originPlanEndDate";
    public static final String WBS_SCHEDULE_SIMULATION_MAP_isDateDifferent        = "isDateDifferent";
    public static final String WBS_SCHEDULE_SIMULATION_MAP_isRescheduled          = "isRescheduled";
    public static final String WBS_SCHEDULE_MEMBER_MAP_PersonList                 = "PersonList";
    public static final String WBS_SCHEDULE_MAP_IS_MY_ACTIVITY                    = "isMyActivity";
    public static final String WBS_SCHEDULE_START_MAP_isStarted                   = "isStarted";
    
    public static final String WBS_SCHEDULE_MAP_DB_DEPENDENCY_PREVIOUS_LIST       = "Dependency.PreviousList";
    public static final String WBS_SCHEDULE_MAP_DB_DEPENDENCY_NEXT_LIST            = "Dependency.NextList";
    public static final String WBS_SCHEDULE_MAP_DB_STRUCTURE_CHILD_LIST            = "Structure.ChildList";
    public static final String WBS_SCHEDULE_MAP_DB_STRUCTURE_PARENT                = "Structure.Parent";
    
    public static final String WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR             = "hasChildActivity";
    public static final String WBS_SCHEDULE_ACTIVITY_PREVIOUS_MAP_ATTR            = "previousActivityDisplayName";
    public static final String WBS_SCHEDULE_ACTIVITY_NAME_MAP_ATTR                = "activityDisplayName";
    public static final String WBS_SCHEDULE_ACTIVITY_ROLELIST_MAP_ATTR            = "roleListDisplayName";
    public static final String WBS_SCHEDULE_ACTIVITY_OWNERLIST_MAP_ATTR           = "ownerListDisplayName";
    
    public static final String WBS_GANTT_ACTIVITY_TYPE_MILESTONE                 = "milestone";
    public static final String WBS_GANTT_ACTIVITY_TYPE_PROJECT                   = "project";
    
    public static final String WBS_TEMPLATE2WBS_MAP_projectPhaseNameConverted       = OmcUniqueIDGenerator.getObid();
    public static final String WBS_TEMPLATE2WBS_MAP_setProjectPhaseNameTarget              = OmcUniqueIDGenerator.getObid();
    
    
    public static final String TEMPLATE_MAP_wbsTemplateMasterDom       = OmcUniqueIDGenerator.getObid();
    public static final String WBS_MAP_SCHEDULE_scheduleVO             = OmcUniqueIDGenerator.getObid();
    
    public static final String WBS_PROJECTPHASE_NAME_SUBPROJECT        = "Sub Project";
    public static final String WBS_DISPLAY_TYPE_WBS_PHASE              = "Event";
    public static final String WBS_DISPLAY_TYPE_WBS_SUBPROJECT         = "Sub Event";
    public static final String WBS_DISPLAY_TYPE_WBS_ACTIVITY           = "Activity";
    public static final String WBS_DISPLAY_TYPE_WBS_GROUP              = "(G)";
    public static final String WBS_DISPLAY_TYPE_WBS_MILESTONE          = "Milestone";
    public static final String WBS_DISPLAY_TYPE_WBS_JOB_ACTIVITY       = "Job";
    
    public static final String WBS_IF_COMPLETE_MAP_ATTR              = OmcUniqueIDGenerator.getObid();
    public static final String WBS_IF_COMPLETE_VALUE_START           = "Complete";
    public static final String WBS_IF_COMPLETE_VALUE_COMPLETE        = "Start";
    
    public static final String BIZ_PROJECT_TYPE_TECH        = "TECH";
    public static final String BIZ_PROJECT_TYPE_MODEL       = "MODEL";
    public static final String BIZ_PROJECT_TYPE_FACILITY    = "FACILITY";
    public static final String BIZ_PROJECT_TYPE_DESIGN      = "DESIGN";
    public static final String[] BIZ_PROJECT_TYPE_SET       = {BIZ_PROJECT_TYPE_TECH,BIZ_PROJECT_TYPE_MODEL,BIZ_PROJECT_TYPE_FACILITY,BIZ_PROJECT_TYPE_DESIGN};
    
    public static final String BIZ_PROJECT_TRANSFER_TYPE_FIRST      = "1";
    public static final String BIZ_PROJECT_TRANSFER_TYPE_RETRANSFER = "2";
    
    public static final String BIZ_PROJECT_BIZ_TRANSFER_TYPE_FIRST   = "0";
    public static final String BIZ_PROJECT_BIZ_TRANSFER_TYPE_OVER    = "1";
    
    public static final String BIZ_PROJECT_TYPE_CODE_STAFF           = "1";
    public static final String BIZ_PROJECT_TYPE_CODE_RNDTECH         = "2";
    public static final String BIZ_PROJECT_TYPE_CODE_COMMON          = "3";
    public static final String BIZ_PROJECT_TYPE_CODE_NATIONAL_RCMS   = "4";
    public static final String BIZ_PROJECT_TYPE_CODE_NATIONAL_GENERAL= "5";
    public static final String BIZ_PROJECT_TYPE_CODE_PRODUCT_DEV     = "6";
    public static final String[] BIZ_PROJECT_TYPE_CODE_SET = {BIZ_PROJECT_TYPE_CODE_STAFF,BIZ_PROJECT_TYPE_CODE_RNDTECH,BIZ_PROJECT_TYPE_CODE_COMMON,BIZ_PROJECT_TYPE_CODE_NATIONAL_RCMS,BIZ_PROJECT_TYPE_CODE_NATIONAL_GENERAL,BIZ_PROJECT_TYPE_CODE_PRODUCT_DEV};
    
    public static final String MAP_KEY_PROJECT_CREATE_SKIP_BMS_CODE     = OmcUniqueIDGenerator.getObid();
    public static final String MAP_KEY_PROJECT_REVISE_PROJECT_VO        = OmcUniqueIDGenerator.getObid();
    public static final String MAP_KEY_PROJECT_REVISE_CHANGE_VO         = OmcUniqueIDGenerator.getObid();
    
    public static final String[]  PROJECT_REVISE_ETC_RELATION_SET   = {ApplicationSchemaConstants.RELCLASS_HASREFERENCEPROJECT,
                                                                       ApplicationSchemaConstants.RELCLASS_HASRELATEDPROJECT,
                                                                       ApplicationSchemaConstants.RELCLASS_HASDESIGNREQUEST,
                                                                       ApplicationSchemaConstants.RELCLASS_HASPARENTPROJECT,
                                                                       ApplicationSchemaConstants.RELCLASS_HASFUND};
    
    public static final String[]  REVISED_PROJECTREVISE_ETC_RELATION_SET   = {ApplicationSchemaConstants.RELCLASS_HASREFERENCEPROJECTREV,
                                                                       ApplicationSchemaConstants.RELCLASS_HASRELATEDPROJECTREV,
                                                                       ApplicationSchemaConstants.RELCLASS_HASDESIGNREQUESTREV,
                                                                       ApplicationSchemaConstants.RELCLASS_HASPARENTPROJECTREV,
                                                                       ApplicationSchemaConstants.RELCLASS_HASFUNDREV};

    public static String OUT_KEY_MANAGEMENTGROUP_accountingGroupVO  = OmcUniqueIDGenerator.getObid();

    public static final String ITEM_CHECK_USERTYPE_ENG = "ENG";
    public static final String ITEM_CHECK_USERTYPE_QA = "QA";

    public static final String ITEM_CHECK_REG_TYPE_BULK = "Bulk";
    public static final String ITEM_CHECK_REG_TYPE_EACH = "Each";


}