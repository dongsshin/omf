/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaServiceUtils.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2017. 1. 31.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rap.omc.foundation.menu.model.CommonMenuSearchVO;
import com.rap.omc.foundation.user.model.CommonUserSearchVO;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
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
import com.rap.omc.schema.service.OmcSchemaUtilService;


/**
 * <pre>
 * Class : OmcSchemaServiceUtils
 * Description : TODO
 * </pre>
 *
 * @author s_dongsshin
 */
public class OmcSchemaServiceUtils {
    private OmcSchemaUtilService omcSchemaService;
    private static OmcSchemaServiceUtils cInstance;
    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static OmcSchemaServiceUtils getInstance(){
        if (cInstance == null) {
            cInstance = new OmcSchemaServiceUtils();
            cInstance.omcSchemaService = (OmcSchemaUtilService)SpringFactoryUtil.getBean("omcSchemaUtilService");
        }
        return cInstance;
    }
    public static void testPmsConnect(){
        getInstance().omcSchemaService.testPmsConnect();
    }
    public static void setBizClassProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        getInstance().omcSchemaService.setBizClassProperty(vo,propertyName,propertyValue);
    }
    public static void setRelClassProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        getInstance().omcSchemaService.setRelClassProperty(vo,propertyName,propertyValue);
    }
    public static void setLifeCycleProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        getInstance().omcSchemaService.setLifeCycleProperty(vo,propertyName,propertyValue);
    }
    public static void setStateProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        getInstance().omcSchemaService.setStateProperty(vo,propertyName,propertyValue);
    }
    public static void setConstantsProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        getInstance().omcSchemaService.setConstantsProperty(vo,propertyName,propertyValue);
    }
    public static void setUserProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        getInstance().omcSchemaService.setUserProperty(vo,propertyName,propertyValue);
    }
    public static void setMenuProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        getInstance().omcSchemaService.setMenuProperty(vo,propertyName,propertyValue);
    }
    public static void setSiteProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        getInstance().omcSchemaService.setSiteProperty(vo,propertyName,propertyValue);
    }
    public static void setAttributeProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        getInstance().omcSchemaService.setAttributeProperty(vo,propertyName,propertyValue);
    }
    public static void setUserProperty(OmcSchemaUserVO vo, String propertyName, String propertyValue ){
        getInstance().omcSchemaService.setUserProperty(vo,propertyName,propertyValue);
    }
    public static String getObid(int classKinds){
        return(getInstance().omcSchemaService.getObid(classKinds));
    }

    public static String[] getObid(int classKinds,int wantedCount){
        return(getInstance().omcSchemaService.getObid(classKinds,wantedCount));
    }

    
    public static void createSystemProperty(OmcSchemaPropertyVO parmVO){
        getInstance().omcSchemaService.createSystemProperty(parmVO);
    }

    public static void createSystemKeyTable(List<OmcSchemaSysKeyVO> parmListVO) {
        getInstance().omcSchemaService.createSystemKeyTable(parmListVO);
    }
    public static void createSystemKeyTable(OmcSchemaSysKeyVO parmVO) {
        getInstance().omcSchemaService.createSystemKeyTable(parmVO);
    }
    public static OmcSchemaSysKeyVO getSystemKeyTableByObid(String obid) {
        return(getInstance().omcSchemaService.getSystemKeyTableByObid(obid));
    }
    public static List<OmcSchemaSysKeyVO> getSystemKeyTableByObid(List<String> obid) {
        return(getInstance().omcSchemaService.getSystemKeyTableByObid(obid));
    }
  //---------------------------------------------------------------------------------------------
    public static void createSystemConstants(OmcSchemaSysConstantVO parmVO){
        getInstance().omcSchemaService.createSystemConstants(parmVO);
    }
    public static void modifySystemConstants(OmcSchemaSysConstantVO parmVO){
        getInstance().omcSchemaService.modifySystemConstants(parmVO);
    }
    public static void inactivateSystemConstants(OmcSchemaSysConstantVO parmVO){
        getInstance().omcSchemaService.inactivateSystemConstants(parmVO);
    }
    public static void deleteSystemConstants(OmcSchemaSysConstantVO parmVO){
        getInstance().omcSchemaService.deleteSystemConstants(parmVO);
    }
    public static OmcSchemaSysConstantVO getSystemConstantsWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemConstantsWithObid(obid));
    }
    public static OmcSchemaSysConstantVO getSystemConstantsWithNames(String namses){
        return(getInstance().omcSchemaService.getSystemConstantsWithNames(namses));
    }
    public static List<OmcSchemaSysConstantVO> getInactiveConstantsListForUpload(){
        return(getInstance().omcSchemaService.getInactiveConstantsListForUpload());
    }
    public static List<OmcSchemaSysConstantVO> getAllTemporaryListForUpload(){
        return(getInstance().omcSchemaService.getAllTemporaryListForUpload());
    }
    public static void uploadTemporaryConstants(List<OmcSchemaSysConstantVO> contList){
        getInstance().omcSchemaService.uploadTemporaryConstants(contList);
    }
    //---------------------------------------------------------------------------------------------
    public static void createSystemTimeZone(OmcSchemaSysTimeZoneVO parmVO){
        getInstance().omcSchemaService.createSystemTimeZone(parmVO);
    }
    public static void modifySystemTimeZone(OmcSchemaSysTimeZoneVO parmVO){
        getInstance().omcSchemaService.modifySystemTimeZone(parmVO);
    }
    public static void inactivateSystemTimeZone(OmcSchemaSysTimeZoneVO parmVO){
        getInstance().omcSchemaService.inactivateSystemTimeZone(parmVO);
    }
    public static void deleteSystemTimeZone(OmcSchemaSysTimeZoneVO parmVO){
        getInstance().omcSchemaService.deleteSystemTimeZone(parmVO);
    }
    public static OmcSchemaSysTimeZoneVO getSystemTimeZoneWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemTimeZoneWithObid(obid));
    }
    public static OmcSchemaSysTimeZoneVO getSystemTimeZoneWithNames(String namses){
        return(getInstance().omcSchemaService.getSystemTimeZoneWithNames(namses));
    }
  //---------------------------------------------------------------------------------------------
    public static void createSystemBizClass(OmcSchemaSysBizClassVO parmVO){
        getInstance().omcSchemaService.createSystemBizClass(parmVO);
    }
    public static void modifySystemBizClass(OmcSchemaSysBizClassVO parmVO){
        getInstance().omcSchemaService.modifySystemBizClass(parmVO);
    }
    public static void inactivateSystemBizClass(OmcSchemaSysBizClassVO parmVO){
        getInstance().omcSchemaService.inactivateSystemBizClass(parmVO);
    }
    public static void deleteSystemBizClass(OmcSchemaSysBizClassVO parmVO){
        getInstance().omcSchemaService.deleteSystemBizClass(parmVO);
    }
    public static OmcSchemaSysBizClassVO getSystemBizClassWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemBizClassWithObid(obid));
    }
    public static OmcSchemaSysBizClassVO getSystemBizClassWithNames(String namses){
        return(getInstance().omcSchemaService.getSystemBizClassWithNames(false,namses));
    }
    public static OmcSchemaSysBizClassVO getTemporarySystemBizClassWithNames(String namses){
        return(getInstance().omcSchemaService.getSystemBizClassWithNames(true,namses));
    }
    public static List<OmcSchemaSysBizClassVO> getFileBizClassListWithSeperatedList(List<OmcSchemaSeperatedClassVO> seperatedClassList){
        return(getInstance().omcSchemaService.getFileBizClassListWithSeperatedList(seperatedClassList));
    }
    public static List<OmcSchemaSysBizClassVO> getChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel){
        return(getInstance().omcSchemaService.getChildSystemBizClassList(firstInclude,rslt,parmVO,wantedLevel));
    }
    public static List<OmcSchemaSysBizClassVO> getParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel){
        return(getInstance().omcSchemaService.getParentSystemBizClassList(firstInclude, rslt,parmVO,wantedLevel));
    }
    public static List<OmcSchemaSysBizClassVO> getChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel){
        return(getInstance().omcSchemaService.getChildSystemBizClassList(firstInclude,rslt,className,wantedLevel));
    }
    public static List<OmcSchemaSysBizClassVO> getParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel){
        return(getInstance().omcSchemaService.getParentSystemBizClassList(firstInclude,rslt,className,wantedLevel));
    }
    public static List<OmcSchemaSysBizClassVO> getTemporaryChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel){
        return(getInstance().omcSchemaService.getTemporaryChildSystemBizClassList(firstInclude,rslt,parmVO,wantedLevel));
    }
    public static List<OmcSchemaSysBizClassVO> getTemporaryParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel){
        return(getInstance().omcSchemaService.getTemporaryParentSystemBizClassList(firstInclude, rslt,parmVO,wantedLevel));
    }
    public static List<OmcSchemaSysBizClassVO> getTemporaryChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className,int wantedLevel){
        return(getInstance().omcSchemaService.getTemporaryChildSystemBizClassList(firstInclude,rslt,className,wantedLevel));
    }
    public static List<OmcSchemaSysBizClassVO> getTemporaryParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className,int wantedLevel){
        return(getInstance().omcSchemaService.getTemporaryParentSystemBizClassList(firstInclude,rslt,className,wantedLevel));
    }
    public static void makeBizClassRevisible(String obid){
        getInstance().omcSchemaService.makeBizClassRevisible(obid);
    }
    public static void makeBizClassWorkflowable(String obid){
        getInstance().omcSchemaService.makeBizClassWorkflowable(obid);
    }
    public static HashMap<String, OmcSchemaSysBizClassVO> getWorkflowableList(){
        return(getInstance().omcSchemaService.getWorkflowableList());
    }
    public static List<OmcSchemaSysBizClassVO> getDuplicatedBizClassList(){
        return(getInstance().omcSchemaService.getDuplicatedBizClassList());
    }
    public static List<OmcSchemaSysBizClassVO> getNotFoundParntBizClassList(){
        return(getInstance().omcSchemaService.getNotFoundParntBizClassList());
    }
    public static List<OmcSchemaSysBizClassVO> getAllBizTemp(){
        return(getInstance().omcSchemaService.getAllBizTemp());
    }
    public static void refreshAllTempBizObid(){
        getInstance().omcSchemaService.refreshAllTempBizObid();
    }
    public static List<OmcSchemaSysBizClassVO> getInactiveBizListForUpload(){
        return(getInstance().omcSchemaService.getInactiveBizListForUpload());
    }
    public static List<OmcSchemaSysBizClassVO> getAllBiz(boolean activeOnly){
        return(getInstance().omcSchemaService.getAllBiz(activeOnly));
    }
    public static void uploadTemporaryBizClass(List<OmcSchemaSysBizClassVO> list){
        getInstance().omcSchemaService.uploadTemporaryBizClass(list);
    }
    //---------------------------------------------------------------------------------------------
    public static void createSystemRelClass(OmcSchemaSysRelClassVO parmVO){
        getInstance().omcSchemaService.createSystemRelClass(parmVO);
    }
    public static void modifySystemRelClass(OmcSchemaSysRelClassVO parmVO){
        getInstance().omcSchemaService.modifySystemRelClass(parmVO);
    }
    public static void inactivateSystemRelClass(OmcSchemaSysRelClassVO parmVO){
        getInstance().omcSchemaService.inactivateSystemRelClass(parmVO);
    }
    public static void deleteSystemRelClass(OmcSchemaSysRelClassVO parmVO){
        getInstance().omcSchemaService.deleteSystemRelClass(parmVO);
    }
    public static OmcSchemaSysRelClassVO getSystemRelClassWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemRelClassWithObid(obid));
    }

    public static OmcSchemaSysRelClassVO getSystemRelClassWithNames(String namses){
        return(getInstance().omcSchemaService.getSystemRelClassWithNames(false,namses));
    }
    public static OmcSchemaSysRelClassVO getTemporarySystemRelClassWithNames(String namses){
        return(getInstance().omcSchemaService.getSystemRelClassWithNames(true,namses));
    }

    public static List<OmcSchemaSysRelClassVO> getChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel){
        return(getInstance().omcSchemaService.getChildSystemRelClassList(firstInclude,rslt,parmVO,wantedLevel));
    }
    public static List<OmcSchemaSysRelClassVO> getParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel){
        return(getInstance().omcSchemaService.getParentSystemRelClassList(firstInclude, rslt,parmVO,wantedLevel));
    }
    public static List<OmcSchemaSysRelClassVO> getChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel){
        return(getInstance().omcSchemaService.getChildSystemRelClassList(firstInclude,rslt,className,wantedLevel));
    }
    public static List<OmcSchemaSysRelClassVO> getParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel){
        return(getInstance().omcSchemaService.getParentSystemRelClassList(firstInclude,rslt,className,wantedLevel));
    }
    public static List<OmcSchemaSysRelClassVO> getTemporaryChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel){
        return(getInstance().omcSchemaService.getTemporaryChildSystemRelClassList(firstInclude,rslt,parmVO,wantedLevel));
    }
    public static List<OmcSchemaSysRelClassVO> getTemporaryParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel){
        return(getInstance().omcSchemaService.getTemporaryParentSystemRelClassList(firstInclude, rslt,parmVO,wantedLevel));
    }
    public static List<OmcSchemaSysRelClassVO> getTemporaryChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className,int wantedLevel){
        return(getInstance().omcSchemaService.getTemporaryChildSystemRelClassList(firstInclude,rslt,className,wantedLevel));
    }
    public static List<OmcSchemaSysRelClassVO> getTemporaryParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className,int wantedLevel){
        return(getInstance().omcSchemaService.getTemporaryParentSystemRelClassList(firstInclude,rslt,className,wantedLevel));
    }

    public static List<OmcSchemaSysRelClassVO> getDuplicatedRelClassList(){
        return(getInstance().omcSchemaService.getDuplicatedRelClassList());
    }
    public static List<OmcSchemaSysRelClassVO> getNotFoundParntRelClassList(){
        return(getInstance().omcSchemaService.getNotFoundParntRelClassList());
    }
    public static List<OmcSchemaSysRelClassVO> getAllRelTemp(){
        return(getInstance().omcSchemaService.getAllRelTemp());
    }
    public static List<OmcSchemaSysRelClassVO> getAllRel(boolean activeOnly){
        return(getInstance().omcSchemaService.getAllRel(activeOnly));
    }
    public static void refreshAllTempRelObid(){
        getInstance().omcSchemaService.refreshAllTempRelObid();
    }
    public static List<OmcSchemaSysRelClassVO> getInactiveRelListForUpload(){
        return(getInstance().omcSchemaService.getInactiveRelListForUpload());
    }
    public static void refreshAllowedClassBatch(){
        getInstance().omcSchemaService.refreshAllowedClassBatch();
    }
    public static void refreshClassInfo(){
        getInstance().omcSchemaService.refreshClassInfo();
    }
    public static void setInstantiableFlagsForRelClass(){
        getInstance().omcSchemaService.setInstantiableFlagsForRelClass();
    }
    public static void setInstantiableFlagsForBizClass(){
        getInstance().omcSchemaService.setInstantiableFlagsForBizClass();
    }
    public static void uploadTemporaryRelClass(List<OmcSchemaSysRelClassVO> list){
        getInstance().omcSchemaService.uploadTemporaryRelClass(list);
    }

    public static List<OmcSchemaSysClassAttrInfoVO> getDuplicatedAttrTypeList(){
        return(getInstance().omcSchemaService.getDuplicatedAttrTypeList());
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getNotFefinedClassForAttr(){
        return(getInstance().omcSchemaService.getNotFefinedClassForAttr());
    }
    public static void initializeAttribute(){
        getInstance().omcSchemaService.initializeAttribute();;
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getAllAttribute(boolean isTemporary, boolean activeOnly){
        return(getInstance().omcSchemaService.getAllAttribute(isTemporary,activeOnly));
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getDuplicatdAttr(){
        return(getInstance().omcSchemaService.getDuplicatdAttr());
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getInactiveListForAttr(){
        return(getInstance().omcSchemaService.getInactiveListForAttr());
    }
    public static OmcSchemaSysClassAttrInfoVO getSystemAttrWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemAttrWithObid(obid));
    }
    public static OmcSchemaSysClassAttrInfoVO getTemporarySystemAttrWithObid(String obid){
        return(getInstance().omcSchemaService.getTemporarySystemAttrWithObid(obid));
    }
    public static void createSystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO){
        getInstance().omcSchemaService.createSystemClassAttr(parmVO);
    }
    public static void modifySystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO){
        getInstance().omcSchemaService.modifySystemClassAttr(parmVO);
    }
    public static void inactivateSystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO){
        getInstance().omcSchemaService.inactivateSystemClassAttr(parmVO);
    }
    public static void deleteSystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO){
        getInstance().omcSchemaService.deleteSystemClassAttr(parmVO);
    }
    public static List<OmcSchemaSysClassAttrInfoVO> getAttributeByClass(boolean isRel, String className){
        return(getInstance().omcSchemaService.getAttributeByClass(isRel,className));
    }
    public static void refreshClassAttrInfo(){
        getInstance().omcSchemaService.refreshClassAttrInfo();
    }
    public static void uploadTemporaryClassAttr(List<OmcSchemaSysClassAttrInfoVO> list){
        getInstance().omcSchemaService.uploadTemporaryClassAttr(list);
    }


    public static void uploadTemporaryAttr(List<OmcSchemaAttributeVO> list){
        getInstance().omcSchemaService.uploadTemporaryAttr(list);
    }
    public static void createSchemaAttribute(OmcSchemaAttributeVO vo){
        getInstance().omcSchemaService.createSchemaAttribute(vo);
    }
    public static void inactiviateSchemaLifeCycleBranch(OmcSchemaAttributeVO vo){
        getInstance().omcSchemaService.inactiviateSchemaAttribute(vo);
    }
    public static void deleteSchemaLifeCycleBranch(OmcSchemaAttributeVO vo){
        getInstance().omcSchemaService.deleteSchemaAttribute(vo);
    }
    public static void modifySchemaAttribute(OmcSchemaAttributeVO vo){
        getInstance().omcSchemaService.modifySchemaAttribute(vo);
    }

    public static List<OmcSchemaAttributeVO> getAttributeListForUpload(){
        return(getInstance().omcSchemaService.getAttributeListForUpload());
    }
    public static List<OmcSchemaAttributeVO> getInActiveAttributeListForUpload(){
        return(getInstance().omcSchemaService.getInActiveAttributeListForUpload());
    }
    public static List<OmcSchemaAttributeVO> getAllDefinedAttributeList(){
        return(getInstance().omcSchemaService.getAllDefinedAttributeList());
    }
    public static List<OmcSchemaRelationVO> getLifeCycleStoreRelationList(String lifeCycle, String store){
        return(getInstance().omcSchemaService.getLifeCycleStoreRelationList(lifeCycle,store));
    }
    //--------------------------------------Life Cycle------------------------------------------------------------
    public static void uploadTemporaryLifeCycle(List<OmcSchemaLifeCycleVO> list){
        getInstance().omcSchemaService.uploadTemporaryLifeCycle(list);
    }
    public static void uploadTemporaryStateInfo(List<OmcSchemaLifeCycleStateInfoVO> list){
        getInstance().omcSchemaService.uploadTemporaryStateInfo(list);
    }
    public static void uploadTemporaryLifeCycleBranch(List<OmcSchemaLifeCycleBranchVO> list){
        getInstance().omcSchemaService.uploadTemporaryLifeCycleBranch(list);
    }
    public static void uploadTemporaryTriggerParameter(List<OmcSchemaTriggerParameterVO> list){
        getInstance().omcSchemaService.uploadTemporaryTriggerParameter(list);
    }
    public static void uploadTemporaryStateTrigger(List<OmcSchemaStateTriggerVO> list){
        getInstance().omcSchemaService.uploadTemporaryStateTrigger(list);
    }
    public static List<OmcSchemaLifeCycleBranchVO> getLifeCycleTempBranchList(){
        return(getInstance().omcSchemaService.getLifeCycleTempBranchList());
    }
    public static void uploadTemporaryBranchList(List<OmcSchemaSeperatedBranchVO> branchList){
        getInstance().omcSchemaService.uploadTemporaryBranchList(branchList);
    }
    public static List<OmcSchemaLifeCycleVO> getLifeCycleListForUpload(){
        return(getInstance().omcSchemaService.getLifeCycleListForUpload());
    }
    public static List<OmcSchemaLifeCycleVO> getInActiveLifeCycleListForUpload(){
        return(getInstance().omcSchemaService.getInActiveLifeCycleListForUpload());
    }
    public static List<OmcSchemaBranchVO> getInactiveBranchList(){
        return(getInstance().omcSchemaService.getInactiveBranchList());
    }
    public static List<OmcSchemaBranchVO> getUploadBranchList(){
        return(getInstance().omcSchemaService.getUploadBranchList());
    }
    public static void inactivateSystemLifeCycle(OmcSchemaLifeCycleVO parmVO){
        getInstance().omcSchemaService.inactivateSystemLifeCycle(parmVO);
    }
    public static void createSystemLifeCycle(OmcSchemaLifeCycleVO parmVO){
        getInstance().omcSchemaService.createSystemLifeCycle(parmVO);
    }
    public static void deleteSystemLifeCycle(OmcSchemaLifeCycleVO parmVO){
        getInstance().omcSchemaService.deleteSystemLifeCycle(parmVO);
    }
    public static void modifySystemLifeCycle(OmcSchemaLifeCycleVO parmVO){
        getInstance().omcSchemaService.modifySystemLifeCycle(parmVO);
    }
    public static OmcSchemaLifeCycleVO getSystemLifeCycleWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemLifeCycleWithObid(obid));
    }
    public static OmcSchemaLifeCycleStateInfoVO getLifeCycleDefinitionForState(String lifeCycleNames, String stateNames){
        return(getInstance().omcSchemaService.getLifeCycleDefinitionForState(lifeCycleNames,stateNames));
    }
    public static List<OmcSchemaStatesVO> getSystemStateListWithLifeCycle(OmcSchemaLifeCycleVO lifeCycleVO){
        return(getInstance().omcSchemaService.getSystemStateListWithLifeCycle(lifeCycleVO));
    }
    public static List<OmcSchemaLifeCycleRelationVO> getSystemLifeCycleRelListWithLifeCycle(OmcSchemaLifeCycleVO lifeCycleVO, long kinds){
        return(getInstance().omcSchemaService.getSystemLifeCycleRelListWithLifeCycle(lifeCycleVO,kinds));
    }
    //--------------------------------------Menu------------------------------------------------------------
    
    public static List<OmcSchemaMenuVO> getMenuCommandEtcList(CommonMenuSearchVO searchVO){
        return(getInstance().omcSchemaService.getMenuCommandEtcList(searchVO));
    }
    public static void uploadTemporaryMenuList(List<OmcSchemaMenuVO> list){
        getInstance().omcSchemaService.uploadTemporaryMenuList(list);
    }
    public static void uploadTemporaryTabLayoutList(List<OmcSchemaTabLayoutVO> list){
        getInstance().omcSchemaService.uploadTemporaryTabLayoutList(list);
    }
    public static Map<String,Object> getErrorListForForUploadMenuList(){
        return(getInstance().omcSchemaService.getErrorListForForUploadMenuList());
    }
    public static List<OmcSchemaMenuVO> getInactiveMenuListForUpload(){
        return(getInstance().omcSchemaService.getInactiveMenuListForUpload());
    }
    public static void inactivateSystemMenu(OmcSchemaMenuVO parmVO){
        getInstance().omcSchemaService.inactivateSystemMenu(parmVO);
    }

    public static void createSystemMenu(OmcSchemaMenuVO parmVO){
        getInstance().omcSchemaService.createSystemMenu(parmVO);
    }
    public static void deleteSystemMenu(OmcSchemaMenuVO parmVO){
        getInstance().omcSchemaService.deleteSystemMenu(parmVO);
    }
    public static void modifySystemMenu(OmcSchemaMenuVO parmVO){
        getInstance().omcSchemaService.modifySystemMenu(parmVO);
    }
    public static List<OmcSchemaRelationVO> getSubMenuCreateListForUpload(){
        return(getInstance().omcSchemaService.getSubMenuCreateListForUpload());
    }
    public static List<OmcSchemaRelationVO> getSubMenuInactiveListForUpload(){
        return(getInstance().omcSchemaService.getSubMenuInactiveListForUpload());
    }
    public static List<OmcSchemaMenuVO> getMenuListForUpload(){
        return(getInstance().omcSchemaService.getMenuListForUpload());
    }
    public static OmcSchemaMenuVO getSystemMenuWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemMenuWithObid(obid));
    }
    public static void inactiviateSchemaRelation(OmcSchemaRelationVO vo){
        getInstance().omcSchemaService.inactiviateSchemaRelation(vo);
    }
    public static void modifySchemaRelation(OmcSchemaRelationVO vo){
        getInstance().omcSchemaService.modifySchemaRelation(vo);
    }
    public static void deleteSchemaRelation(OmcSchemaRelationVO vo){
        getInstance().omcSchemaService.deleteSchemaRelation(vo);
    }
    public static void createSchemaRelation(OmcSchemaRelationVO vo){
        getInstance().omcSchemaService.createSchemaRelation(vo);
    }

    public static OmcSchemaFileFormatVO getSystemFileFormatWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemFileFormatWithObid(obid));
    }
    public static OmcSchemaFileFormatVO getSystemFileFormatWithNames(String names){
        return(getInstance().omcSchemaService.getSystemFileFormatWithObid(names));
    }
    public static void createSystemFileFormat(OmcSchemaFileFormatVO parmVO){
        getInstance().omcSchemaService.createSystemFileFormat(parmVO);
    }
    public static void inactivateSystemFileFormat(OmcSchemaFileFormatVO parmVO){
        getInstance().omcSchemaService.inactivateSystemFileFormat(parmVO);
    }
    public static void deleteSystemFileFormat(OmcSchemaFileFormatVO parmVO){
        getInstance().omcSchemaService.deleteSystemFileFormat(parmVO);
    }
    public static void modifySystemFileFormat(OmcSchemaFileFormatVO parmVO){
        getInstance().omcSchemaService.modifySystemFileFormat(parmVO);
    }
    public static void uploadTemporaryFileFormat(List<OmcSchemaFileFormatVO> list){
        getInstance().omcSchemaService.uploadTemporaryFileFormat(list);
    }
    public static List<OmcSchemaFileFormatVO> getFileFormatListForUpload(){
        return(getInstance().omcSchemaService.getFileFormatListForUpload());
    }
    public static List<OmcSchemaFileFormatVO> getInActiveFileFormatListForUpload(){
        return(getInstance().omcSchemaService.getInActiveFileFormatListForUpload());
    }
    public static List<OmcSchemaFileFormatVO> getFileFormatListWithList(List<OmcSchemaFileFormatVO> list){
        return(getInstance().omcSchemaService.getFileFormatListWithList(list));
    }
    public static List<OmcSchemaFileFormatVO> getFileFormatListWithSeperatedList(List<OmcSchemaSeperatedFormatVO> seperatedFormatList){
        return(getInstance().omcSchemaService.getFileFormatListWithSeperatedList(seperatedFormatList));
    }

    public static void createSchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo){
        getInstance().omcSchemaService.createSchemaLifeCycleRelation(vo);
    }
    public static void inactiviateSchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo){
        getInstance().omcSchemaService.inactiviateSchemaLifeCycleRelation(vo);
    }
    public static void deleteSchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo){
        getInstance().omcSchemaService.deleteSchemaLifeCycleRelation(vo);
    }
    public static OmcSchemaLifeCycleRelationVO getSchemaLifeCycleRelationWithFromToObid(OmcSchemaLifeCycleRelationVO vo){
        return(getInstance().omcSchemaService.getSchemaLifeCycleRelationWithFromToObid(vo));
    }
    public static void modifySchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo){
        getInstance().omcSchemaService.modifySchemaLifeCycleRelation(vo);
    }

    //-------------------------------Life Cycle State--------------------------------
    public static OmcSchemaStatesVO getSystemStateWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemStateWithObid(obid));
    }
    public static void createSystemState(OmcSchemaStatesVO parmVO){
        getInstance().omcSchemaService.createSystemState(parmVO);
    }
    public static void deleteSystemState(OmcSchemaStatesVO parmVO){
        getInstance().omcSchemaService.deleteSystemState(parmVO);
    }
    public static void modifySystemState(OmcSchemaStatesVO parmVO){
        getInstance().omcSchemaService.modifySystemState(parmVO);
    }
    public static void inactivateSystemState(OmcSchemaStatesVO parmVO){
        getInstance().omcSchemaService.inactivateSystemState(parmVO);
    }
    public static void createSchemaLifeCycleBranch(OmcSchemaBranchVO vo){
        getInstance().omcSchemaService.createSchemaLifeCycleBranch(vo);
    }
    public static void inactiviateSchemaLifeCycleBranch(OmcSchemaBranchVO vo){
        getInstance().omcSchemaService.inactiviateSchemaLifeCycleBranch(vo);
    }
    public static void deleteSchemaLifeCycleBranch(OmcSchemaBranchVO vo){
        getInstance().omcSchemaService.deleteSchemaLifeCycleBranch(vo);
    }
    public static void modifySchemaLifeCycleBranch(OmcSchemaBranchVO vo){
        getInstance().omcSchemaService.modifySchemaLifeCycleBranch(vo);
    }
    public static OmcSchemaLifeCycleVO getSystemTriggerParameterWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemTriggerParameterWithObid(obid));
    }
    public static void createSystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO){
        getInstance().omcSchemaService.createSystemTriggerParameter(parmVO);
    }
    public static void inactivateSystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO){
        getInstance().omcSchemaService.inactivateSystemTriggerParameter(parmVO);
    }
    public static void deleteSystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO){
        getInstance().omcSchemaService.deleteSystemTriggerParameter(parmVO);
    }
    public static void modifySystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO){
        getInstance().omcSchemaService.modifySystemTriggerParameter(parmVO);
    }
    public static List<OmcSchemaTriggerParameterVO> getInactiveTriggerParameterList(){
        return(getInstance().omcSchemaService.getInactiveTriggerParameterList());
    }
    public static List<OmcSchemaTriggerParameterVO> getUploadTriggerParameterList(){
        return(getInstance().omcSchemaService.getUploadTriggerParameterList());
    }
    public static List<OmcSchemaStateTriggerVO> getInactiveStateTriggerList(){
        return(getInstance().omcSchemaService.getInactiveStateTriggerList());
    }
    public static List<OmcSchemaStateTriggerVO> getUploadStateTriggerList(){
        return(getInstance().omcSchemaService.getUploadStateTriggerList());
    }
    public static OmcSchemaTriggerParameterVO getSystemStateTriggerWithObid(String obid){
        return(getInstance().omcSchemaService.getSystemStateTriggerWithObid(obid));
    }
    public static void createSystemStateTrigger(OmcSchemaStateTriggerVO parmVO){
        getInstance().omcSchemaService.createSystemStateTrigger(parmVO);
    }
    public static void inactivateSystemStateTrigger(OmcSchemaStateTriggerVO parmVO){
        getInstance().omcSchemaService.inactivateSystemStateTrigger(parmVO);
    }
    public static void deleteSystemStateTrigger(OmcSchemaStateTriggerVO parmVO){
        getInstance().omcSchemaService.deleteSystemStateTrigger(parmVO);
    }
    public static void modifySystemStateTrigger(OmcSchemaStateTriggerVO parmVO){
        getInstance().omcSchemaService.modifySystemStateTrigger(parmVO);
    }
    // File Location
    public static void createSystemFileLocation(OmcSchemaFileServerVO parmVO){
        getInstance().omcSchemaService.createSystemFileLocation(parmVO);
    }
    public static void modifySystemFileLocation(OmcSchemaFileServerVO parmVO){
        getInstance().omcSchemaService.modifySystemFileLocation(parmVO);
    }
    public static void inactivateSystemFileLocation(OmcSchemaFileServerVO parmVO){
        getInstance().omcSchemaService.inactivateSystemFileLocation(parmVO);
    }
    public static void deleteSystemFileLocation(OmcSchemaFileServerVO parmVO){
        getInstance().omcSchemaService.deleteSystemFileLocation(parmVO);
    }
    public static List<OmcSchemaFileServerVO> getInActiveStoreListForUpload(){
        return(getInstance().omcSchemaService.getInActiveStoreListForUpload());
    }
    public static List<OmcSchemaFileServerVO> getStoreListForUpload(){
        return(getInstance().omcSchemaService.getStoreListForUpload());
    }
 // File Store
    public static void createSystemFileStore(OmcSchemaFileServerVO parmVO){
        getInstance().omcSchemaService.createSystemFileStore(parmVO);
    }
    public static void modifySystemFileStore(OmcSchemaFileServerVO parmVO){
        getInstance().omcSchemaService.modifySystemFileStore(parmVO);
    }
    public static void inactivateSystemFileStore(OmcSchemaFileServerVO parmVO){
        getInstance().omcSchemaService.inactivateSystemFileStore(parmVO);
    }
    public static void deleteSystemFileStore(OmcSchemaFileServerVO parmVO){
        getInstance().omcSchemaService.deleteSystemFileStore(parmVO);
    }
    public static void uploadTemporaryStoreAndLocation(List<OmcSchemaFileServerVO> list){
        getInstance().omcSchemaService.uploadTemporaryStoreAndLocation(list);
    }
    public static List<OmcSchemaFileServerVO> getInActiveLocationListForUpload(){
        return(getInstance().omcSchemaService.getInActiveLocationListForUpload());
    }
    public static List<OmcSchemaFileServerVO> getLocationListForUpload(){
        return(getInstance().omcSchemaService.getLocationListForUpload());
    }
       
    public static OmcSchemaFileServerVO getStoreWithNames(String names){
        return(getInstance().omcSchemaService.getStoreWithNames(names));
    }

    /**********************Dynamic Attribute ***************************/
    public static void uploadTemporaryDynamicAttribute(List<OmcSchemaDynamicAttrGrpVO> list){
        getInstance().omcSchemaService.uploadTemporaryDynamicAttribute(list);
    }
    /**********************Method Set ***************************/
    public static void uploadTemporaryMethodSet(List<OmcSchemaMethodSetVO> list){
        getInstance().omcSchemaService.uploadTemporaryMethodSet(list);
    }
    /**********************Assign ***************************/
    public static void uploadTemporaryAssign(List<OmcSchemaStoreLocationVO> list){
        getInstance().omcSchemaService.uploadTemporaryAssign(list);
    }
    /**********************Role & Group ***************************/
    public static void uploadTemporaryRoleNGroup(List<OmcSchemaRoleGroupVO> list){
        getInstance().omcSchemaService.uploadTemporaryRoleNGroup(list);
    }
    /**********************Site ***************************/
    public static void uploadTemporarySite(List<OmcSchemaSiteVO> list){
        getInstance().omcSchemaService.uploadTemporarySite(list);
    }
    public static void createSystemSite(OmcSchemaSiteVO parmVO){
        getInstance().omcSchemaService.createSystemSite(parmVO);
    }
    public static void deleteSystemSite(OmcSchemaSiteVO parmVO){
        getInstance().omcSchemaService.deleteSystemSite(parmVO);
    }
    public static void modifySystemSite(OmcSchemaSiteVO parmVO){
        getInstance().omcSchemaService.modifySystemSite(parmVO);
    }
    public static void inactivateSystemSite(OmcSchemaSiteVO parmVO){
        getInstance().omcSchemaService.inactivateSystemSite(parmVO);
    }
    public static List<OmcSchemaSiteVO> getSiteListForUpload(){
        return(getInstance().omcSchemaService.getSiteListForUpload());
    }
    public static List<OmcSchemaSiteVO> getInActiveSiteListForUpload(){
        return(getInstance().omcSchemaService.getInActiveSiteListForUpload());
    }
    public static OmcSchemaSiteVO getSiteWithObid(String obid){
        return(getInstance().omcSchemaService.getSiteWithObid(obid));
    }
    public static OmcSchemaSiteVO getSiteWithNames(String names){
        return(getInstance().omcSchemaService.getSiteWithNames(names));
    }
    public static List<OmcSchemaRelationVO> getTemporaryUploadSite2LocationRelationList(){
        return(getInstance().omcSchemaService.getTemporaryUploadSite2LocationRelationList());
    }
    public static List<OmcSchemaRelationVO> getTemporaryInactiveSite2LocationRelationList(){
        return(getInstance().omcSchemaService.getTemporaryInactiveSite2LocationRelationList());
    }
    public static List<OmcSchemaRelationVO> getTemporaryUploadStore2LocationRelationList(){
        return(getInstance().omcSchemaService.getTemporaryUploadStore2LocationRelationList());
    }
    public static List<OmcSchemaRelationVO> getTemporaryInactiveStore2LocationRelationList(){
        return(getInstance().omcSchemaService.getTemporaryInactiveStore2LocationRelationList());
    }
    /**********************User ***************************/
    public static List<OmcSchemaUserVO> getCommonUserForMenuList(List<String> obidList, Boolean isActiveOnly){
        return(getInstance().omcSchemaService.getCommonUserForMenuList(obidList,isActiveOnly));
    }
    public static List<OmcSchemaUserVO> getCommonUserForMenuList(List<String> obidList){
        return(getInstance().omcSchemaService.getCommonUserForMenuList(obidList,true));
    }
    public static List<OmcSchemaUserVO> getCommonUserList(CommonUserSearchVO searchVO){
        return(getInstance().omcSchemaService.getCommonUserList(searchVO));
    }
    public static OmcSchemaUserVO getUserCommonWithObid(String obid){
        return(getInstance().omcSchemaService.getUserCommonWithObid(obid));
    }
    public static OmcSchemaUserVO getUserCommonWithName(String namses){
        return(getInstance().omcSchemaService.getUserCommonWithName(namses));
    }
    public static void uploadTemporaryUserGroup(List<OmcSchemaUserVO> list){
        getInstance().omcSchemaService.uploadTemporaryUserGroup(list);
    }
    public static void createSystemUser(OmcSchemaUserVO parmVO){
        getInstance().omcSchemaService.createSystemUser(parmVO);
    }
    public static void deleteSystemUser(OmcSchemaUserVO parmVO){
        getInstance().omcSchemaService.deleteSystemUser(parmVO);
    }
    public static void modifySystemUser(OmcSchemaUserVO parmVO){
        getInstance().omcSchemaService.modifySystemUser(parmVO);
    }
    public static void setTimeStampSystemUser(OmcSchemaUserVO parmVO){
        getInstance().omcSchemaService.setTimeStampSystemUser(parmVO);
    }
    public static void inactivateSystemUser(OmcSchemaUserVO parmVO){
        getInstance().omcSchemaService.inactivateSystemUser(parmVO);
    }
    public static List<OmcSchemaUserVO> getUserListForUpload(){
        return(getInstance().omcSchemaService.getUserListForUpload());
    }
    public static List<OmcSchemaUserVO> getInActiveUserListForUpload(){
        return(getInstance().omcSchemaService.getInActiveUserListForUpload());
    }
    public static List<OmcSchemaUserVO> getRoleListForUpload(){
        return(getInstance().omcSchemaService.getRoleListForUpload());
    }
    public static List<OmcSchemaUserVO> getInActiveRoleListForUpload(){
        return(getInstance().omcSchemaService.getInActiveRoleListForUpload());
    }
    public static List<OmcSchemaUserVO> getRoleGroupListForUpload(){
        return(getInstance().omcSchemaService.getRoleGroupListForUpload());
    }
    public static List<OmcSchemaUserVO> getInActiveRoleGroupListForUpload(){
        return(getInstance().omcSchemaService.getInActiveRoleGroupListForUpload());
    }
    public static List<OmcSchemaUserVO> getGroupListForUpload(){
        return(getInstance().omcSchemaService.getGroupListForUpload());
    }
    public static List<OmcSchemaUserVO> getInActiveGroupListForUpload(){
        return(getInstance().omcSchemaService.getInActiveGroupListForUpload());
    }
    public static List<OmcSchemaUserVO> getAllActiveUserList(){
        return(getInstance().omcSchemaService.getAllActiveUserList());
    }
    public static List<OmcSchemaRelationVO> getInactiveUserCommonRelationList(long schemaKinds,long fromObjKinds, long toObjKinds, String kindStr){
        return(getInstance().omcSchemaService.getInactiveUserCommonRelationList(schemaKinds, fromObjKinds,toObjKinds,kindStr));
    }
    public static List<OmcSchemaRelationVO> getUploadUserCommonRelationList(long schemaKinds, String kindStr, long fromKinds, long toKinds){
        return(getInstance().omcSchemaService.getUploadUserCommonRelationList(schemaKinds,kindStr,fromKinds,toKinds));
    }
    public static List<OmcSchemaRelationVO> getUserCommonRelationList(long schemaKinds, String fromNames, String toNames){
        return(getInstance().omcSchemaService.getUserCommonRelationList(schemaKinds,fromNames,toNames));
    }
    
    /**********************Tab ***************************/
    public static void createSystemTab(OmcSchemaTabLayoutVO parmVO){
        getInstance().omcSchemaService.createSystemTab(parmVO);
    }
    public static void modifySystemTab(OmcSchemaTabLayoutVO parmVO){
        getInstance().omcSchemaService.modifySystemTab(parmVO);
    }
    public static List<OmcSchemaTabLayoutVO> getTabListForUpload(){
        return(getInstance().omcSchemaService.getTabListForUpload());
    }
    public static List<OmcSchemaTabLayoutVO> getInActiveTabListForUpload(){
        return(getInstance().omcSchemaService.getInActiveTabListForUpload());
    }
    public static void inactivateSystemTab(OmcSchemaTabLayoutVO parmVO){
        getInstance().omcSchemaService.inactivateSystemTab(parmVO);
    }
    public static void deleteSystemTab(OmcSchemaTabLayoutVO parmVO){
        getInstance().omcSchemaService.deleteSystemTab(parmVO);
    }
    public static OmcSchemaMenuVO getSystemMenuWithNames(String names){
        return(getInstance().omcSchemaService.getSystemMenuWithNames(names));
    }
    public static List<OmcSchemaMenuVO> getCommandListWithSeperatedList(List<String> seperatedList){
        return(getInstance().omcSchemaService.getCommandListWithSeperatedList(seperatedList));
    }
    /**********************Layout ***************************/
    public static void createSystemLayout(OmcSchemaTabLayoutVO parmVO){
        getInstance().omcSchemaService.createSystemLayout(parmVO);
    }
    public static void modifySystemLayout(OmcSchemaTabLayoutVO parmVO){
        getInstance().omcSchemaService.modifySystemLayout(parmVO);
    }
    public static List<OmcSchemaTabLayoutVO> getLayoutListForUpload(){
        return(getInstance().omcSchemaService.getLayoutListForUpload());
    }
    public static List<OmcSchemaTabLayoutVO> getInActiveLayoutListForUpload(){
        return(getInstance().omcSchemaService.getInActiveLayoutListForUpload());
    }
    public static void inactivateSystemLayout(OmcSchemaTabLayoutVO parmVO){
        getInstance().omcSchemaService.inactivateSystemLayout(parmVO);
    }
    public static void deleteSystemLayout(OmcSchemaTabLayoutVO parmVO){
        getInstance().omcSchemaService.deleteSystemLayout(parmVO);
    }
    public static List<OmcSchemaMenuVO> getCommandList(OmcSchemaTabLayoutVO parmVO){
        return(getInstance().omcSchemaService.getCommandList(parmVO));
    }
    public static List<OmcSchemaTabLayoutVO> getTabList(OmcSchemaTabLayoutVO parmVO){
        return(getInstance().omcSchemaService.getTabList(parmVO));
    }
    public static OmcSchemaTabLayoutVO getSystemTabWithNames(String names){
        return(getInstance().omcSchemaService.getSystemTabWithNames(names));
    }
    public static List<OmcSchemaTabLayoutVO> getTabListWithSeperatedList(List<String> seperatedList){
        return(getInstance().omcSchemaService.getTabListWithSeperatedList(seperatedList));
    }
    public static List<OmcSchemaRelationVO> getSchemaRelationList(OmcSchemaSysRootVO parmVo, long schemaKinds, boolean isActiveOnly){
        return(getInstance().omcSchemaService.getSchemaRelationList(parmVo,schemaKinds,isActiveOnly));
    }
    public static void initialSchemaSetupMain(String defaultSite){
        getInstance().omcSchemaService.initialSchemaSetupMain(defaultSite);
    }
    public static void testBatch(){
        getInstance().omcSchemaService.txnBatchInsertTest();
    }
    
    public static List<OmcSchemaRelationVO> getRelationList(long schemaKinds, String fromObid, String toObid){
        return(getInstance().omcSchemaService.getRelationList(schemaKinds,fromObid,toObid));
    }
    public static List<OmcSchemaMenuVO> getMenuListForCommonUserWithObid(List<String> obidList, boolean isActiveOnly){
        return(getInstance().omcSchemaService.getMenuListForCommonUserWithObid(obidList,isActiveOnly));
    }
    public static List<OmcSchemaMenuVO> getMenuListForCommonUserWithNames(List<String> nmmeList, boolean isActiveOnly){
        return(getInstance().omcSchemaService.getMenuListForCommonUserWithName(nmmeList,isActiveOnly));
    }
    public static List<OmcSchemaMenuVO> getMenuListForCommonUser(String obid, boolean isActiveOnly){
        return(getInstance().omcSchemaService.getMenuListForCommonUser(obid,isActiveOnly));
    }
    public static List<String> getMenuSetForCommonUserWithObid(List<String> obidList){
        return(getInstance().omcSchemaService.getMenuSetForCommonUserWithObid(obidList));
    }
    public static List<String> getMenuSetForCommonUserWithNames(List<String> nameList){
        return(getInstance().omcSchemaService.getMenuSetForCommonUserWithNames(nameList));
    }
    
    
    
}