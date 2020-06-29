/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaUtilService.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2017. 1. 31.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rap.omc.foundation.menu.model.CommonMenuSearchVO;
import com.rap.omc.foundation.user.model.CommonUserSearchVO;
import com.rap.omc.schema.object.model.OmcSchemaAttributeVO;
import com.rap.omc.schema.object.model.OmcSchemaBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaDynamicAttrGrpVO;
import com.rap.omc.schema.object.model.OmcSchemaFileFormatVO;
import com.rap.omc.schema.object.model.OmcSchemaFileServerVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleStateInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleVO;
import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaMethodSetVO;
import com.rap.omc.schema.object.model.OmcSchemaPropertyVO;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaRoleGroupVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedFormatVO;
import com.rap.omc.schema.object.model.OmcSchemaSiteVO;
import com.rap.omc.schema.object.model.OmcSchemaStateTriggerVO;
import com.rap.omc.schema.object.model.OmcSchemaStatesVO;
import com.rap.omc.schema.object.model.OmcSchemaStoreLocationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysBizClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSysClassAttrInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaSysConstantVO;
import com.rap.omc.schema.object.model.OmcSchemaSysKeyVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRelClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.object.model.OmcSchemaSysTimeZoneVO;
import com.rap.omc.schema.object.model.OmcSchemaTabLayoutVO;
import com.rap.omc.schema.object.model.OmcSchemaTriggerParameterVO;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;


/**
 * <pre>
 * Class : OmcSchemaUtilService
 * Description : TODO
 * </pre>
 *
 * @author s_dongsshin
 */
public interface OmcSchemaUtilService {
    /**********************Common*********************************/
    public void testPmsConnect();
    public void setBizClassProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue );
    public void setRelClassProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue );
    public void setLifeCycleProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue );
    public void setStateProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue );
    public void setConstantsProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue );
    public void setUserProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue );
    public void setMenuProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue );
    public void setSiteProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue );
    public void setAttributeProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue );
    public void setUserProperty(OmcSchemaUserVO vo, String propertyName, String propertyValue );

    public void createSystemProperty(OmcSchemaPropertyVO propertyVO);

    public String getObid(int classKinds);
    public String[] getObid(int classKinds,int wantedCount);

    /**********************psyskeytable*********************************/
    public void createSystemKeyTable(OmcSchemaSysKeyVO systemKeyVO);
    public void createSystemKeyTable(List<OmcSchemaSysKeyVO> parmListVO);
    public OmcSchemaSysKeyVO getSystemKeyTableByObid(String obid);
    public List<OmcSchemaSysKeyVO> getSystemKeyTableByObid(List<String> obid);

    /**********************psysconstants********************************/
    public void createSystemConstants(OmcSchemaSysConstantVO parmVO);
    public void modifySystemConstants(OmcSchemaSysConstantVO parmVO);
    public void inactivateSystemConstants(OmcSchemaSysConstantVO parmVO);
    public void deleteSystemConstants(OmcSchemaSysConstantVO parmVO);
    public OmcSchemaSysConstantVO getSystemConstantsWithObid(String obid);
    public OmcSchemaSysConstantVO getSystemConstantsWithNames(String namses);
    public List<OmcSchemaSysConstantVO> getInactiveConstantsListForUpload();
    public List<OmcSchemaSysConstantVO> getAllTemporaryListForUpload();
    public void uploadTemporaryConstants(List<OmcSchemaSysConstantVO> constList);


    /**********************psystimezone********************************/
    public void createSystemTimeZone(OmcSchemaSysTimeZoneVO parmVO);
    public void modifySystemTimeZone(OmcSchemaSysTimeZoneVO parmVO);
    public void inactivateSystemTimeZone(OmcSchemaSysTimeZoneVO parmVO);
    public void deleteSystemTimeZone(OmcSchemaSysTimeZoneVO parmVO);
    public OmcSchemaSysTimeZoneVO getSystemTimeZoneWithObid(String obid);
    public OmcSchemaSysTimeZoneVO getSystemTimeZoneWithNames(String namses);
    /**********************psysbizobjectclassinfo********************************/
    public void createSystemBizClass(OmcSchemaSysBizClassVO parmVO);
    public void modifySystemBizClass(OmcSchemaSysBizClassVO parmVO);
    public void inactivateSystemBizClass(OmcSchemaSysBizClassVO parmVO);
    public void deleteSystemBizClass(OmcSchemaSysBizClassVO parmVO);
    public OmcSchemaSysBizClassVO getSystemBizClassWithObid(String obid);
    public OmcSchemaSysBizClassVO getSystemBizClassWithNames(boolean isTemporary,String namses);
    public List<OmcSchemaSysBizClassVO> getFileBizClassListWithSeperatedList(List<OmcSchemaSeperatedClassVO> seperatedClassList);
    public List<OmcSchemaSysBizClassVO> getChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel);
    public List<OmcSchemaSysBizClassVO> getParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel);
    public List<OmcSchemaSysBizClassVO> getChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel);
    public List<OmcSchemaSysBizClassVO> getParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel);

    public List<OmcSchemaSysBizClassVO> getTemporaryChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel);
    public List<OmcSchemaSysBizClassVO> getTemporaryParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel);
    public List<OmcSchemaSysBizClassVO> getTemporaryChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel);
    public List<OmcSchemaSysBizClassVO> getTemporaryParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel);

    public List<OmcSchemaSysBizClassVO> getDuplicatedBizClassList();
    public List<OmcSchemaSysBizClassVO> getNotFoundParntBizClassList();
    public void refreshAllTempBizObid();
    public List<OmcSchemaSysBizClassVO> getAllBizTemp();
    public List<OmcSchemaSysBizClassVO> getAllBiz(boolean activeOnly);
    public List<OmcSchemaSysBizClassVO> getInactiveBizListForUpload();

    public void makeBizClassRevisible(String obid);
    public void makeBizClassWorkflowable(String obid);
    public void makeBizClassInactive(String obid);

    public HashMap<String, OmcSchemaSysBizClassVO> getWorkflowableList();
    public void refreshClassInfo();
    public void setInstantiableFlagsForRelClass();
    public void setInstantiableFlagsForBizClass();
    
    public void uploadTemporaryBizClass(List<OmcSchemaSysBizClassVO> list);
    
    /**********************psysbizobjectclassinfo********************************/
    public void createSystemRelClass(OmcSchemaSysRelClassVO parmVO);
    public void modifySystemRelClass(OmcSchemaSysRelClassVO parmVO);
    public void inactivateSystemRelClass(OmcSchemaSysRelClassVO parmVO);
    public void deleteSystemRelClass(OmcSchemaSysRelClassVO parmVO);
    public OmcSchemaSysRelClassVO getSystemRelClassWithObid(String obid);
    public OmcSchemaSysRelClassVO getSystemRelClassWithNames(boolean isTemporary,String namses);

    public List<OmcSchemaSysRelClassVO> getChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel);
    public List<OmcSchemaSysRelClassVO> getParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel);
    public List<OmcSchemaSysRelClassVO> getChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel);
    public List<OmcSchemaSysRelClassVO> getParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel);

    public List<OmcSchemaSysRelClassVO> getTemporaryChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel);
    public List<OmcSchemaSysRelClassVO> getTemporaryParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel);
    public List<OmcSchemaSysRelClassVO> getTemporaryChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel);
    public List<OmcSchemaSysRelClassVO> getTemporaryParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel);


    public List<OmcSchemaSysRelClassVO> getDuplicatedRelClassList();
    public List<OmcSchemaSysRelClassVO> getNotFoundParntRelClassList();
    public void refreshAllTempRelObid();
    public List<OmcSchemaSysRelClassVO> getAllRelTemp();
    public List<OmcSchemaSysRelClassVO> getAllRel(boolean activeOnly);
    public List<OmcSchemaSysRelClassVO> getInactiveRelListForUpload();
    public void refreshAllowedClassBatch();
    public void uploadTemporaryRelClass(List<OmcSchemaSysRelClassVO> list);


    /**********************psysbizobjectclassinfo********************************/
    public List<OmcSchemaSysClassAttrInfoVO> getAllAttribute(boolean isTemporary, boolean activeOnly);
    public List<OmcSchemaSysClassAttrInfoVO> getDuplicatdAttr();
    public List<OmcSchemaSysClassAttrInfoVO> getDuplicatedAttrTypeList();
    public List<OmcSchemaSysClassAttrInfoVO> getNotFefinedClassForAttr();
    public List<OmcSchemaSysClassAttrInfoVO> getInactiveListForAttr();
    public void initializeAttribute();
    public OmcSchemaSysClassAttrInfoVO getTemporarySystemAttrWithObid(String obid);
    public OmcSchemaSysClassAttrInfoVO getSystemAttrWithObid(String obid);
    public void createSystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO);
    public void modifySystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO);
    public void inactivateSystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO);
    public void deleteSystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO);
    public List<OmcSchemaSysClassAttrInfoVO> getAttributeByClass(boolean isRel, String className);
    public void refreshClassAttrInfo();
    public void uploadTemporaryClassAttr(List<OmcSchemaSysClassAttrInfoVO> list);

    /**********************psysbizobjectclassinfo********************************/
    public void uploadTemporaryAttr(List<OmcSchemaAttributeVO> list);

    public void createSchemaAttribute(OmcSchemaAttributeVO vo);
    public void inactiviateSchemaAttribute(OmcSchemaAttributeVO vo);
    public void deleteSchemaAttribute(OmcSchemaAttributeVO vo);
    public void modifySchemaAttribute(OmcSchemaAttributeVO vo);

    /**********************Menu********************************/
    public void uploadTemporaryMenuList(List<OmcSchemaMenuVO> list);
    public void uploadTemporaryTabLayoutList(List<OmcSchemaTabLayoutVO> list);
    public Map<String,Object> getErrorListForForUploadMenuList();
    public List<OmcSchemaMenuVO> getInactiveMenuListForUpload();
    public void inactivateSystemMenu(OmcSchemaMenuVO parmVO);
    public void createSystemMenu(OmcSchemaMenuVO parmVO);
    public void deleteSystemMenu(OmcSchemaMenuVO parmVO);
    public void modifySystemMenu(OmcSchemaMenuVO parmVO);
    public List<OmcSchemaRelationVO> getSubMenuCreateListForUpload();
    public List<OmcSchemaRelationVO> getSubMenuInactiveListForUpload();
    public List<OmcSchemaMenuVO> getMenuListForUpload();
    public OmcSchemaMenuVO getSystemMenuWithObid(String obid);
    
    public List<OmcSchemaRelationVO> getRelationList(long schemaKinds, String fromObid, String toObid);
    public List<OmcSchemaMenuVO> getMenuCommandEtcList(CommonMenuSearchVO searchVO);

    /**********************Schema Relation********************************/
    public void inactiviateSchemaRelation(OmcSchemaRelationVO vo);
    public void modifySchemaRelation(OmcSchemaRelationVO vo);
    public void deleteSchemaRelation(OmcSchemaRelationVO vo);
    public void createSchemaRelation(OmcSchemaRelationVO vo);

    /**********************File Format********************************/
    public OmcSchemaFileFormatVO getSystemFileFormatWithObid(String obid);
    public OmcSchemaFileFormatVO getSystemFileFormatWithNames(String names);
    public void createSystemFileFormat(OmcSchemaFileFormatVO parmVO);
    public void inactivateSystemFileFormat(OmcSchemaFileFormatVO parmVO);
    public void deleteSystemFileFormat(OmcSchemaFileFormatVO parmVO);
    public void modifySystemFileFormat(OmcSchemaFileFormatVO parmVO);
    public void uploadTemporaryFileFormat(List<OmcSchemaFileFormatVO> list);
    public List<OmcSchemaFileFormatVO> getFileFormatListForUpload();
    public List<OmcSchemaFileFormatVO> getInActiveFileFormatListForUpload();
    public List<OmcSchemaFileFormatVO> getFileFormatListWithList(List<OmcSchemaFileFormatVO> list);
    public List<OmcSchemaFileFormatVO> getFileFormatListWithSeperatedList(List<OmcSchemaSeperatedFormatVO> seperatedFormatList);
    /**********************Life Cycle********************************/
    public void uploadTemporaryLifeCycle(List<OmcSchemaLifeCycleVO> list);
    public void uploadTemporaryStateInfo(List<OmcSchemaLifeCycleStateInfoVO> list);
    public void uploadTemporaryLifeCycleBranch(List<OmcSchemaLifeCycleBranchVO> list);
    public void uploadTemporaryTriggerParameter(List<OmcSchemaTriggerParameterVO> list);
    public void uploadTemporaryStateTrigger(List<OmcSchemaStateTriggerVO> list);
    public List<OmcSchemaLifeCycleBranchVO> getLifeCycleTempBranchList();
    public void uploadTemporaryBranchList(List<OmcSchemaSeperatedBranchVO> branchList);
    public List<OmcSchemaLifeCycleVO> getLifeCycleListForUpload();
    public List<OmcSchemaLifeCycleVO> getInActiveLifeCycleListForUpload();
    public List<OmcSchemaBranchVO> getInactiveBranchList();
    public List<OmcSchemaBranchVO> getUploadBranchList();
    public void inactivateSystemLifeCycle(OmcSchemaLifeCycleVO parmVO);
    public void createSystemLifeCycle(OmcSchemaLifeCycleVO parmVO);
    public void deleteSystemLifeCycle(OmcSchemaLifeCycleVO parmVO);
    public void modifySystemLifeCycle(OmcSchemaLifeCycleVO parmVO);
    public OmcSchemaLifeCycleVO getSystemLifeCycleWithObid(String obid);
    public OmcSchemaLifeCycleStateInfoVO getLifeCycleDefinitionForState(String lifeCycleNames, String stateNames);
    public List<OmcSchemaStatesVO> getSystemStateListWithLifeCycle(OmcSchemaLifeCycleVO lifeCycleVO);
    public List<OmcSchemaLifeCycleRelationVO> getSystemLifeCycleRelListWithLifeCycle(OmcSchemaLifeCycleVO lifeCycleVO, long kinds);

    /**********************Life Cycle Relation*************************/
    public void createSchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo);
    public void inactiviateSchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo);
    public void deleteSchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo);
    public void modifySchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo);
    public OmcSchemaLifeCycleRelationVO getSchemaLifeCycleRelationWithFromToObid(OmcSchemaLifeCycleRelationVO vo);
    /**********************Life Cycle State ***************************/
    public OmcSchemaStatesVO getSystemStateWithObid(String obid);
    public void createSystemState(OmcSchemaStatesVO parmVO);
    public void deleteSystemState(OmcSchemaStatesVO parmVO);
    public void modifySystemState(OmcSchemaStatesVO parmVO);
    public void inactivateSystemState(OmcSchemaStatesVO parmVO);
    
    public List<OmcSchemaRelationVO> getLifeCycleStoreRelationList(String lifeCycle, String store);
    
    /**********************Life Cycle Branch ***************************/
    public void createSchemaLifeCycleBranch(OmcSchemaBranchVO vo);
    public void inactiviateSchemaLifeCycleBranch(OmcSchemaBranchVO vo);
    public void deleteSchemaLifeCycleBranch(OmcSchemaBranchVO vo);
    public void modifySchemaLifeCycleBranch(OmcSchemaBranchVO vo);
    /**********************Trigger Paramete ***************************/
    public OmcSchemaLifeCycleVO getSystemTriggerParameterWithObid(String obid);
    public void createSystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO);
    public void inactivateSystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO);
    public void deleteSystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO);
    public void modifySystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO);
    public List<OmcSchemaTriggerParameterVO> getInactiveTriggerParameterList();
    public List<OmcSchemaTriggerParameterVO> getUploadTriggerParameterList();
    public List<OmcSchemaStateTriggerVO> getInactiveStateTriggerList();
    public List<OmcSchemaStateTriggerVO> getUploadStateTriggerList();
    public OmcSchemaTriggerParameterVO getSystemStateTriggerWithObid(String obid);
    public void createSystemStateTrigger(OmcSchemaStateTriggerVO parmVO);
    public void inactivateSystemStateTrigger(OmcSchemaStateTriggerVO parmVO);
    public void deleteSystemStateTrigger(OmcSchemaStateTriggerVO parmVO);
    public void modifySystemStateTrigger(OmcSchemaStateTriggerVO parmVO);

    /**********************File Location ***************************/
    public void createSystemFileLocation(OmcSchemaFileServerVO parmVO);
    public void modifySystemFileLocation(OmcSchemaFileServerVO parmVO);
    public void inactivateSystemFileLocation(OmcSchemaFileServerVO parmVO);
    public void deleteSystemFileLocation(OmcSchemaFileServerVO parmVO);
    public List<OmcSchemaFileServerVO> getInActiveLocationListForUpload();
    public List<OmcSchemaFileServerVO> getLocationListForUpload();
    /**********************File Store ***************************/

    public void createSystemFileStore(OmcSchemaFileServerVO parmVO);
    public void modifySystemFileStore(OmcSchemaFileServerVO parmVO);
    public void inactivateSystemFileStore(OmcSchemaFileServerVO parmVO);
    public void deleteSystemFileStore(OmcSchemaFileServerVO parmVO);
    public List<OmcSchemaFileServerVO> getInActiveStoreListForUpload();
    public List<OmcSchemaFileServerVO> getStoreListForUpload();

    public OmcSchemaFileServerVO getStoreWithNames(String names);
    
    public void uploadTemporaryStoreAndLocation(List<OmcSchemaFileServerVO> list);
    /**********************Dynamic Attribute ***************************/
    public void uploadTemporaryDynamicAttribute(List<OmcSchemaDynamicAttrGrpVO> list);
    /**********************Method Set ***************************/
    public void uploadTemporaryMethodSet(List<OmcSchemaMethodSetVO> list);
    /**********************Assign ***************************/
    public void uploadTemporaryAssign(List<OmcSchemaStoreLocationVO> list);
    /**********************Role & Group ***************************/
    public void uploadTemporaryRoleNGroup(List<OmcSchemaRoleGroupVO> list);


    /**********************Site ***************************/
    public void uploadTemporarySite(List<OmcSchemaSiteVO> list);
    public void createSystemSite(OmcSchemaSiteVO parmVO);
    public void deleteSystemSite(OmcSchemaSiteVO parmVO);
    public void modifySystemSite(OmcSchemaSiteVO parmVO);
    public void inactivateSystemSite(OmcSchemaSiteVO parmVO);
    public List<OmcSchemaSiteVO> getSiteListForUpload();
    public List<OmcSchemaSiteVO> getInActiveSiteListForUpload();
    public OmcSchemaSiteVO getSiteWithObid(String obid);
    public OmcSchemaSiteVO getSiteWithNames(String names);

    public List<OmcSchemaRelationVO> getTemporaryUploadSite2LocationRelationList();
    public List<OmcSchemaRelationVO> getTemporaryInactiveSite2LocationRelationList();
    public List<OmcSchemaRelationVO> getTemporaryUploadStore2LocationRelationList();
    public List<OmcSchemaRelationVO> getTemporaryInactiveStore2LocationRelationList();

    
    
    /**********************User ***************************/
    public List<OmcSchemaUserVO> getCommonUserForMenuList(List<String> obidList, Boolean isActiveOnly);
    public List<OmcSchemaUserVO> getCommonUserList(CommonUserSearchVO searchVO);
    public OmcSchemaUserVO getUserCommonWithObid(String obid);
    public OmcSchemaUserVO getUserCommonWithName(String namses);
    public void uploadTemporaryUserGroup(List<OmcSchemaUserVO> list);
    public void createSystemUser(OmcSchemaUserVO parmVO);
    public void deleteSystemUser(OmcSchemaUserVO parmVO);
    public void modifySystemUser(OmcSchemaUserVO parmVO);
    public void setTimeStampSystemUser(OmcSchemaUserVO parmVO);
    
    public void inactivateSystemUser(OmcSchemaUserVO parmVO);
    public List<OmcSchemaUserVO> getUserListForUpload();
    public List<OmcSchemaUserVO> getInActiveUserListForUpload();
    public List<OmcSchemaUserVO> getRoleListForUpload();
    public List<OmcSchemaUserVO> getInActiveRoleListForUpload();
    public List<OmcSchemaUserVO> getRoleGroupListForUpload();
    public List<OmcSchemaUserVO> getInActiveRoleGroupListForUpload();
    public List<OmcSchemaUserVO> getGroupListForUpload();
    public List<OmcSchemaUserVO> getInActiveGroupListForUpload();
    public List<OmcSchemaUserVO> getAllActiveUserList();
    public List<OmcSchemaRelationVO> getInactiveUserCommonRelationList(long schemaKinds,long fromObjKinds, long toObjKinds, String kindStr);
    public List<OmcSchemaRelationVO> getUploadUserCommonRelationList(long schemaKinds, String kindStr, long fromKinds, long toKinds);
    public List<OmcSchemaRelationVO> getUserCommonRelationList(long schemaKinds, String fromNames, String toNames);
    /**********************Tab ***************************/
    public void createSystemTab(OmcSchemaTabLayoutVO parmVO);
    public void modifySystemTab(OmcSchemaTabLayoutVO parmVO);
    public List<OmcSchemaTabLayoutVO> getTabListForUpload();
    public List<OmcSchemaTabLayoutVO> getInActiveTabListForUpload();
    public void inactivateSystemTab(OmcSchemaTabLayoutVO parmVO);
    public void deleteSystemTab(OmcSchemaTabLayoutVO parmVO);
    public List<OmcSchemaMenuVO> getCommandList(OmcSchemaTabLayoutVO parmVO);
    public OmcSchemaMenuVO getSystemMenuWithNames(String names);
    public List<OmcSchemaMenuVO> getCommandListWithSeperatedList(List<String> seperatedList);
    /**********************Layout ***************************/
    public void createSystemLayout(OmcSchemaTabLayoutVO parmVO);
    public void modifySystemLayout(OmcSchemaTabLayoutVO parmVO);
    public List<OmcSchemaTabLayoutVO> getLayoutListForUpload();
    public List<OmcSchemaTabLayoutVO> getInActiveLayoutListForUpload();
    public void inactivateSystemLayout(OmcSchemaTabLayoutVO parmVO);
    public void deleteSystemLayout(OmcSchemaTabLayoutVO parmVO);
    public List<OmcSchemaTabLayoutVO> getTabList(OmcSchemaTabLayoutVO parmVO);
    public OmcSchemaTabLayoutVO getSystemTabWithNames(String names);
    public List<OmcSchemaTabLayoutVO> getTabListWithSeperatedList(List<String> seperatedList);
    public List<OmcSchemaRelationVO> getSchemaRelationList(OmcSchemaSysRootVO parmVo, long schemaKinds, boolean isActiveOnly);
    public List<OmcSchemaAttributeVO> getAttributeListForUpload();
    public List<OmcSchemaAttributeVO> getInActiveAttributeListForUpload();
    public List<OmcSchemaAttributeVO> getAllDefinedAttributeList();
    public void initialSchemaSetupMain(String defaultSite);
    public List<OmcSchemaMenuVO> getMenuListForCommonUserWithObid(List<String> obidList, boolean isActiveOnly);
    public List<OmcSchemaMenuVO> getMenuListForCommonUserWithName(List<String> nameList, boolean isActiveOnly);
    public List<String> getMenuSetForCommonUserWithObid(List<String> obidList);
    public List<String> getMenuSetForCommonUserWithNames(List<String> nameList);
    
    public List<OmcSchemaMenuVO> getMenuListForCommonUser(String obid, boolean isActiveOnly);
    public void txnBatchInsertTest();
}
