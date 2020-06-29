/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaManagementServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.dom.OmcSchemaAttribute;
import com.rap.omc.schema.object.dom.OmcSchemaBranch;
import com.rap.omc.schema.object.dom.OmcSchemaFileFormat;
import com.rap.omc.schema.object.dom.OmcSchemaFileLocation;
import com.rap.omc.schema.object.dom.OmcSchemaFileStore;
import com.rap.omc.schema.object.dom.OmcSchemaLayout;
import com.rap.omc.schema.object.dom.OmcSchemaLifeCycle;
import com.rap.omc.schema.object.dom.OmcSchemaMenu;
import com.rap.omc.schema.object.dom.OmcSchemaRelationGroup2Group;
import com.rap.omc.schema.object.dom.OmcSchemaRelationGroup2Role;
import com.rap.omc.schema.object.dom.OmcSchemaRelationGroup2User;
import com.rap.omc.schema.object.dom.OmcSchemaRelationRole2Role;
import com.rap.omc.schema.object.dom.OmcSchemaRelationRole2User;
import com.rap.omc.schema.object.dom.OmcSchemaRelationRoleGroup2User;
import com.rap.omc.schema.object.dom.OmcSchemaRelationSite2Location;
import com.rap.omc.schema.object.dom.OmcSchemaRelationStore2Location;
import com.rap.omc.schema.object.dom.OmcSchemaRelationSubMenu;
import com.rap.omc.schema.object.dom.OmcSchemaSite;
import com.rap.omc.schema.object.dom.OmcSchemaStateTrigger;
import com.rap.omc.schema.object.dom.OmcSchemaSysBizClass;
import com.rap.omc.schema.object.dom.OmcSchemaSysClassAttrInfo;
import com.rap.omc.schema.object.dom.OmcSchemaSysConstant;
import com.rap.omc.schema.object.dom.OmcSchemaSysRelClass;
import com.rap.omc.schema.object.dom.OmcSchemaTab;
import com.rap.omc.schema.object.dom.OmcSchemaTriggerParameter;
import com.rap.omc.schema.object.dom.OmcSchemaUserGroup;
import com.rap.omc.schema.object.dom.OmcSchemaUserRole;
import com.rap.omc.schema.object.dom.OmcSchemaUserRoleGroup;
import com.rap.omc.schema.object.dom.OmcSchemaUserUser;
import com.rap.omc.schema.object.model.OmcSchemaAttributeVO;
import com.rap.omc.schema.object.model.OmcSchemaBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaFileFormatVO;
import com.rap.omc.schema.object.model.OmcSchemaFileServerVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleVO;
import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaSiteVO;
import com.rap.omc.schema.object.model.OmcSchemaStateTriggerVO;
import com.rap.omc.schema.object.model.OmcSchemaSysBizClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSysClassAttrInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaSysConstantVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRelClassVO;
import com.rap.omc.schema.object.model.OmcSchemaTabLayoutVO;
import com.rap.omc.schema.object.model.OmcSchemaTriggerParameterVO;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.omc.schema.service.OmcSchemaCoreService;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaConstants;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;

@Service("omcSchemaCoreService")
@SuppressWarnings({"unused"})
public class OmcSchemaCoreServiceImpl implements OmcSchemaCoreService{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaCoreServiceImpl.class);
    /**
     *
     * @param uploadOption
     * @throws Exception
     * @see omc.schema.service.OmcSchemaService#txnUploadSystemConstants(long)
     */
    @Override
    public void txnUploadSystemConstants(long uploadOption) throws Exception{
        // TODO Auto-generated method stub
        this.initializeForMassSystemConstants(uploadOption);
        this.validataForMassSystemConstants(uploadOption);
        this.preProcessForMassSystemConstants(uploadOption);
        this.processForMassSystemConstants(uploadOption);
        this.postProcessForMassSystemConstants(uploadOption);
    }
    /**
     *
     * @param uploadOption
     * @throws Exception
     * @see omc.schema.service.OmcSchemaService#txnUploadClassAndAttr(long)
     */
    @Override
    public void txnUploadClassAndAttr(long uploadOption) throws Exception{
        // TODO Auto-generated method stub
        this.initializeForMassClassAndAttr(uploadOption);
        this.validataForMassClassAndAttr(uploadOption);
        this.preProcessForMassClassAndAttr(uploadOption);
        this.processForMassClassAndAttr(uploadOption);
        this.postProcessForMassClassAndAttr(uploadOption);
    }
    /**
     *
     * @param uploadOption
     * @throws Exception
     * @see omc.schema.service.OmcSchemaService#txnUploadMemusAndLayouts(long)
     */
    @Override
    public void txnUploadMemusAndLayouts(long uploadOption) throws Exception{
        this.initializeForMassMemusAndLayouts(uploadOption);
        this.validataForMassMemusAndLayouts(uploadOption);
        this.preProcessForMassMemusAndLayouts(uploadOption);
        this.processForMassMemusAndLayouts(uploadOption);
        this.postProcessForMassMemusAndLayouts(uploadOption);
    }
    /**
     *
     * @param uploadOption
     * @throws Exception
     * @see omc.schema.service.OmcSchemaService#txnUploadLifeCycles(long)
     */
    @Override
    public void txnUploadLifeCycles(long uploadOption) throws Exception{
        // TODO Auto-generated method stub
        this.initializeForMassLifeCycles(uploadOption);
        this.validataForMassLifeCycles(uploadOption);
        this.preProcessForMassLifeCycles(uploadOption);
        this.processForMassLifeCycles(uploadOption);
        this.postProcessForMassLifeCycles(uploadOption);
    }
    /**
     *
     * @param uploadOption
     * @throws Exception
     * @see omc.schema.service.OmcSchemaService#txnUploadSchemaEtc(long)
     */
    @Override
    public void txnUploadSchemaEtc(long uploadOption) throws Exception{
        // TODO Auto-generated method stub
        this.initializeForMassSchemaEtc(uploadOption);
        this.validataForMassSchemaEtc(uploadOption);
        this.preProcessForMassSchemaEtc(uploadOption);
        this.processForMassSchemaEtc(uploadOption);
        this.postProcessForMassSchemaEtc(uploadOption);
    }
    //---------------------------------------------------------------------------------------------------------
    //-------------------------------initializeForMass---------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------
    private void initializeForMassSystemConstants(long uploadOption){
         if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CONSTANT_UPLOAD)) this.initializeForConstantsUpload();
    }
    private void initializeForMassClassAndAttr(long uploadOption){
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS)) this.initializeForBizUpload();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS)) this.initializeForRelUpload();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_ATTR))this.initializeForAttrUpload();
    }
    private void initializeForMassMemusAndLayouts(long uploadOption){
        ;
    }
    private void initializeForMassLifeCycles(long uploadOption){
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_BRANCH_UPLOAD))){
            List<OmcSchemaSeperatedBranchVO> seperatedBranchList = OmcSchemaLifeCycle.seperateBranchInfo();
            OmcSchemaServiceUtils.uploadTemporaryBranchList(seperatedBranchList);
        }
    }
    private void initializeForMassSchemaEtc(long uploadOption){
        ;
    }
    //---------------------------------------------------------------------------------------------------------
    //-------------------------------validataForMass-----------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------
    private void validataForMassSystemConstants(long uploadOption){
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CONSTANT_UPLOAD)) this.validataForConstantsUpload();
    }
    private void validataForMassClassAndAttr(long uploadOption){
       if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS)) this.validataForBizClassUpload();
       if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS)) this.validataForRelClassUpload();
       if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_ATTR))this.validataForAttrUpload();
    }
    private void validataForMassMemusAndLayouts(long uploadOption){
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_MENU_UPLOAD)) this.validataForMassMemus();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_PAGE_LAYOUT_UPLOAD)) this.validataForMassLayoutNTab();
    }

    private void validataForMassMemus(){

        Map<String,Object> returnMap = OmcSchemaMenu.getErrorListForForUploadMenuList();
        String isSucess = (String)returnMap.get("isSucess");
        if(!"true".equals(isSucess)) throw new FoundationException("omc.schema.menu.validation");
    }
    private void validataForMassLayoutNTab(){

    }
    private void validataForMassLifeCycles(long uploadOption){
       ;
    }
    private void validataForMassSchemaEtc(long uploadOption){
       ;
    }
    //---------------------------------------------------------------------------------------------------------
    //-------------------------------preProcessForMass---------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------
    private void preProcessForMassSystemConstants(long uploadOption){
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CONSTANT_UPLOAD)) this.preProcessForConstantsUpload();
    }
    private void preProcessForMassClassAndAttr(long uploadOption){
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS)) this.preProcessForBizUpload();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS)) this.preProcessForRelUpload();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_ATTR))this.preProcessForAttrUpload();
    }
    private void preProcessForMassMemusAndLayouts(long uploadOption){
       ;
    }
    private void preProcessForMassLifeCycles(long uploadOption){
       ;
    }
    private void preProcessForMassSchemaEtc(long uploadOption){
       ;
    }
    //---------------------------------------------------------------------------------------------------------
    //-------------------------------processForMass------------------------------------------------------------
    //Description : process부분은 순서가 중요함.
    //---------------------------------------------------------------------------------------------------------
    private void processForMassSystemConstants(long uploadOption){
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CONSTANT_UPLOAD)) this.processForConstantUpload();
    }
    private void processForMassClassAndAttr(long uploadOption){
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS)) this.processForBizUpload();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS)) this.processForRelUpload();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_ATTR))this.processForAttrUpload();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ATTR_DISPLAYED))this.processForAttrDisplayedUpload();
    }
    private void processForMassMemusAndLayouts(long uploadOption){
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_MENU_UPLOAD)) this.processForMemuUpload();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_PAGE_LAYOUT_UPLOAD)) this.processForPageLayoutUpload();
    }
    private void processForMassLifeCycles(long uploadOption){
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_FILE_FORMAT_UPLOAD))) this.processForFileFormat();
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_LIFE_CYCLE_UPLOAD))) this.processForLifeCycles();
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_BRANCH_UPLOAD))) this.processForBranch();
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_PARAMETER_UPLOAD))) this.processForTriggerParameter();
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STATE_TRIGGER_UPLOAD))) this.processForStateTrigger();
    }
    private void processForMassSchemaEtc(long uploadOption){
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_SITE_UPLOAD))) this.processForSite();
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STORE_LOCATION_UPLOAD))) this.processForFileStoreAndLocation();
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ROLE_GROUP_UPLOAD))) this.processForRoleAndGroup();
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ACCESS_METHOD_UPLOAD))) this.processForMethodSet();
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_DYNAMIC_ATTR_UPLOAD))) this.processForDynamicAttribute();
    }
    //---------------------------------------------------------------------------------------------------------
    //-------------------------------postProcessForMass--------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------
    private void postProcessForMassSystemConstants(long uploadOption){
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CONSTANT_UPLOAD)) this.postProcessForConstantUpload();
    }
    private void postProcessForMassClassAndAttr(long uploadOption){
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS)) this.postprocessForBizUpload();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS)) this.postprocessForRelUpload();
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_ATTR))this.postProcessForAttrUpload();
        if((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS)) ||
            Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS)){
            OmcSchemaSysRelClass.refreshAllowedClassBatch();
            OmcSchemaSysBizClass.setInstantiableFlagsForBizClass();
            OmcSchemaSysRelClass.setInstantiableFlagsForRelClass();
            OmcSchemaSysBizClass.refreshClassInfo();
            }
            if((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS)) ||
                Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS)  ||
                Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_ATTR)){
                OmcSchemaSysClassAttrInfo.refreshClassAttrInfo();
            }
    }
    private void postProcessForMassMemusAndLayouts(long uploadOption){
       ;
    }
    private void postProcessForMassLifeCycles(long uploadOption){
       ;
    }
    private void postProcessForMassSchemaEtc(long uploadOption){
       ;
    }
    //---------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------
    private void processForMemuUpload(){
        List<OmcSchemaMenuVO> inActiveList = OmcSchemaMenu.getInactiveListForUpload();
        for(OmcSchemaMenuVO vo : inActiveList){
            OmcSchemaMenu bizDom = new OmcSchemaMenu(vo);
            bizDom.inActiviateObject(null);
        }
        List<OmcSchemaMenuVO>     menuList            = OmcSchemaMenu.getMenuListForUpload();
        for(OmcSchemaMenuVO vo : menuList){
            OmcSchemaMenu dom = new OmcSchemaMenu(vo);
            HashMap<String, OmcSchemaMenuVO> map = new HashMap<String, OmcSchemaMenuVO>();
            if(vo.getObid() == null)
            {
                dom.createObject(map);
            }else{
                OmcSchemaMenu savedDom    = new OmcSchemaMenu(vo.getObid());
                //if(!dom.isEqual(savedDom.getVo())){
                    map.put("dataBaseVO", savedDom.getVo());
                    dom.modifyObject(map);
                //}
            }
        }
        List<OmcSchemaRelationVO> inactiveSubMenuList = OmcSchemaMenu.getSubMenuInactiveListForUpload();
        for(OmcSchemaRelationVO vo : inactiveSubMenuList){
            OmcSchemaRelationSubMenu dom = new OmcSchemaRelationSubMenu(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            dom.inActiviateObject(map);
        }
        List<OmcSchemaRelationVO> subMenuList         = OmcSchemaMenu.getSubMenuCreateListForUpload();
        for(OmcSchemaRelationVO vo : subMenuList){
            OmcSchemaRelationSubMenu dom = new OmcSchemaRelationSubMenu(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    //---------------------------------------------------------------------------------------------------------
    private void processForPageLayoutUpload(){
        this.processForTabUpload();
        this.processForLayoutUpload();
    }
    private void processForLayoutUpload(){
        List<OmcSchemaTabLayoutVO> inactiveList = OmcSchemaLayout.getInactiveList();
        List<OmcSchemaTabLayoutVO> uploadList = OmcSchemaLayout.getUploadList();
        for(OmcSchemaTabLayoutVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaLayout dom = new OmcSchemaLayout(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaTabLayoutVO vo : uploadList){
            OmcSchemaLayout dom = new OmcSchemaLayout(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForTabUpload(){
        List<OmcSchemaTabLayoutVO> inactiveList = OmcSchemaTab.getInactiveList();
        List<OmcSchemaTabLayoutVO> uploadList = OmcSchemaTab.getUploadList();
        for(OmcSchemaTabLayoutVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaTab dom = new OmcSchemaTab(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaTabLayoutVO vo : uploadList){
            OmcSchemaTab dom = new OmcSchemaTab(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    //---------------------------------------------------------------------------------------------------------

    private void processForLifeCycles(){
        List<OmcSchemaLifeCycleVO> inactiveList = OmcSchemaLifeCycle.getInActiveLifeCycleListForUpload();
        List<OmcSchemaLifeCycleVO> uploadList = OmcSchemaLifeCycle.getLifeCycleListForUpload();
        for(OmcSchemaLifeCycleVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaLifeCycle dom = new OmcSchemaLifeCycle(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaLifeCycleVO vo : uploadList){
            OmcSchemaLifeCycle dom = new OmcSchemaLifeCycle(vo);
            dom.seperateListForUpload();
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    //---------------------------------------------------------------------------------------------------------
    private void processForBranch(){
        List<OmcSchemaBranchVO> inactiveList = OmcSchemaBranch.getInactiveBranchList();
        List<OmcSchemaBranchVO> uploadList = OmcSchemaBranch.getUploadBranchList();
        for(OmcSchemaBranchVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaBranch dom = new OmcSchemaBranch(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaBranchVO vo : uploadList){
            OmcSchemaBranch dom = new OmcSchemaBranch(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    //---------------------------------------------------------------------------------------------------------
    private void processForTriggerParameter(){
        List<OmcSchemaTriggerParameterVO> inactiveList = OmcSchemaTriggerParameter.getInactiveList();
        List<OmcSchemaTriggerParameterVO> uploadList = OmcSchemaTriggerParameter.getUploadList();

        for(OmcSchemaTriggerParameterVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaTriggerParameter dom = new OmcSchemaTriggerParameter(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaTriggerParameterVO vo : uploadList){
            OmcSchemaTriggerParameter dom = new OmcSchemaTriggerParameter(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    //---------------------------------------------------------------------------------------------------------
    private void processForFileFormat(){
        List<OmcSchemaFileFormatVO> inactiveList = OmcSchemaFileFormat.getInactiveList();
        List<OmcSchemaFileFormatVO> uploadList = OmcSchemaFileFormat.getUploadList();

        for(OmcSchemaFileFormatVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaFileFormat dom = new OmcSchemaFileFormat(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaFileFormatVO vo : uploadList){
            OmcSchemaFileFormat dom = new OmcSchemaFileFormat(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    //---------------------------------------------------------------------------------------------------------
    private void processForStateTrigger(){
        List<OmcSchemaStateTriggerVO> inactiveList = OmcSchemaStateTrigger.getInactiveList();
        List<OmcSchemaStateTriggerVO> uploadList = OmcSchemaStateTrigger.getUploadList();
        for(OmcSchemaStateTriggerVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaStateTrigger dom = new OmcSchemaStateTrigger(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaStateTriggerVO vo : uploadList){
            OmcSchemaStateTrigger dom = new OmcSchemaStateTrigger(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    //---------------------------------------------------------------------------------------------------------
    private void processForSite(){
        List<OmcSchemaSiteVO> inactiveList = OmcSchemaSite.getInactiveList();
        List<OmcSchemaSiteVO> uploadList = OmcSchemaSite.getUploadList();
        for(OmcSchemaSiteVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaSite dom = new OmcSchemaSite(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaSiteVO vo : uploadList){
            OmcSchemaSite dom = new OmcSchemaSite(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForFileStoreAndLocation(){
        this.processForFileStore();
        this.processForFileLocation();
        this.processForSite2Location();
        this.processForStore2Location();
    }
    private void processForFileStore(){
        List<OmcSchemaFileServerVO> inactiveList = OmcSchemaFileStore.getInactiveList();
        List<OmcSchemaFileServerVO> uploadList = OmcSchemaFileStore.getUploadList();

        for(OmcSchemaFileServerVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaFileStore dom = new OmcSchemaFileStore(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaFileServerVO vo : uploadList){
            OmcSchemaFileStore dom = new OmcSchemaFileStore(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForFileLocation(){
        List<OmcSchemaFileServerVO> inactiveList = OmcSchemaFileLocation.getInactiveList();
        List<OmcSchemaFileServerVO> uploadList = OmcSchemaFileLocation.getUploadList();

        for(OmcSchemaFileServerVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaFileLocation dom = new OmcSchemaFileLocation(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaFileServerVO vo : uploadList){
            OmcSchemaFileLocation dom = new OmcSchemaFileLocation(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForSite2Location(){
        List<OmcSchemaRelationVO> inactiveList = OmcSchemaRelationSite2Location.geInactiveRelationList();
        List<OmcSchemaRelationVO> uploadList = OmcSchemaRelationSite2Location.getTemporaryUploadRelationList();

        for(OmcSchemaRelationVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaRelationSite2Location dom = new OmcSchemaRelationSite2Location(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaRelationVO vo : uploadList){
            OmcSchemaRelationSite2Location dom = new OmcSchemaRelationSite2Location(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForStore2Location(){
        List<OmcSchemaRelationVO> inactiveList = OmcSchemaRelationStore2Location.getInactiveRelationList();
        List<OmcSchemaRelationVO> uploadList = OmcSchemaRelationStore2Location.getTemporaryUploadRelationList();

        for(OmcSchemaRelationVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaRelationStore2Location dom = new OmcSchemaRelationStore2Location(vo);
            dom.inActiviateObject(map);
        }
        for(OmcSchemaRelationVO vo : uploadList){
            OmcSchemaRelationStore2Location dom = new OmcSchemaRelationStore2Location(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    //---------------------------------------------------------------------------------------------------------
    private void processForRoleAndGroup(){
        this.processForUser();
        this.processForRole();
        this.processForGroup();
        this.processForRoleGroup();

        this.processForGroup2User();
        this.processForRole2User();
        this.processForRole2Role();
        this.processForGroup2Group();
        this.processForRoleGroup2User();
        this.processForGroup2Role();

    }
    private void processForUser(){
        List<OmcSchemaUserVO> inactiveList = OmcSchemaUserUser.getInactiveList();
        List<OmcSchemaUserVO> uploadList = OmcSchemaUserUser.getUploadList();

        for(OmcSchemaUserVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaUserUser dom = new OmcSchemaUserUser(vo);
            //dom.inActiviateObject(map);
        }
        for(OmcSchemaUserVO vo : uploadList){
            OmcSchemaUserUser dom = new OmcSchemaUserUser(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForRole(){
        List<OmcSchemaUserVO> inactiveList = OmcSchemaUserRole.getInactiveList();
        List<OmcSchemaUserVO> uploadList = OmcSchemaUserRole.getUploadList();

        for(OmcSchemaUserVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaUserRole dom = new OmcSchemaUserRole(vo);
            //dom.inActiviateObject(map);
        }
        for(OmcSchemaUserVO vo : uploadList){
            OmcSchemaUserRole dom = new OmcSchemaUserRole(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForGroup(){
        List<OmcSchemaUserVO> inactiveList = OmcSchemaUserGroup.getInactiveList();
        List<OmcSchemaUserVO> uploadList = OmcSchemaUserGroup.getUploadList();

        for(OmcSchemaUserVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaUserGroup dom = new OmcSchemaUserGroup(vo);
            //dom.inActiviateObject(map);
        }
        for(OmcSchemaUserVO vo : uploadList){
            OmcSchemaUserGroup dom = new OmcSchemaUserGroup(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForRoleGroup(){
        List<OmcSchemaUserVO> inactiveList = OmcSchemaUserRoleGroup.getInactiveList();
        List<OmcSchemaUserVO> uploadList = OmcSchemaUserRoleGroup.getUploadList();

        for(OmcSchemaUserVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaUserRoleGroup dom = new OmcSchemaUserRoleGroup(vo);
            //dom.inActiviateObject(map);
        }
        for(OmcSchemaUserVO vo : uploadList){
            OmcSchemaUserRoleGroup dom = new OmcSchemaUserRoleGroup(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForGroup2User(){
        List<OmcSchemaRelationVO> inactiveList = OmcSchemaRelationGroup2User.getInactiveRelationList();
        List<OmcSchemaRelationVO> uploadList = OmcSchemaRelationGroup2User.getUploadRelationList();
        for(OmcSchemaRelationVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaRelationGroup2User dom = new OmcSchemaRelationGroup2User(vo);
            //dom.inActiviateObject(map);
        }
        for(OmcSchemaRelationVO vo : uploadList){
            OmcSchemaRelationGroup2User dom = new OmcSchemaRelationGroup2User(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForGroup2Role(){
        List<OmcSchemaRelationVO> inactiveList = OmcSchemaRelationGroup2Role.getInactiveRelationList();
        List<OmcSchemaRelationVO> uploadList = OmcSchemaRelationGroup2Role.getUploadRelationList();
        for(OmcSchemaRelationVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaRelationGroup2Role dom = new OmcSchemaRelationGroup2Role(vo);
            //dom.inActiviateObject(map);
        }
        for(OmcSchemaRelationVO vo : uploadList){
            OmcSchemaRelationGroup2Role dom = new OmcSchemaRelationGroup2Role(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForRole2User(){
        List<OmcSchemaRelationVO> inactiveList = OmcSchemaRelationRole2User.getInactiveRelationList();
        List<OmcSchemaRelationVO> uploadList = OmcSchemaRelationRole2User.getUploadRelationList();
        for(OmcSchemaRelationVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaRelationRole2User dom = new OmcSchemaRelationRole2User(vo);
            //dom.inActiviateObject(map);
        }
        for(OmcSchemaRelationVO vo : uploadList){
            OmcSchemaRelationRole2User dom = new OmcSchemaRelationRole2User(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForRole2Role(){
        List<OmcSchemaRelationVO> inactiveList = OmcSchemaRelationRole2Role.getInactiveRelationList();
        List<OmcSchemaRelationVO> uploadList = OmcSchemaRelationRole2Role.getUploadRelationList();
        for(OmcSchemaRelationVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaRelationRole2Role dom = new OmcSchemaRelationRole2Role(vo);
            //dom.inActiviateObject(map);
        }
        for(OmcSchemaRelationVO vo : uploadList){
            OmcSchemaRelationRole2Role dom = new OmcSchemaRelationRole2Role(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForGroup2Group(){
        List<OmcSchemaRelationVO> inactiveList = OmcSchemaRelationGroup2Group.getInactiveRelationList();
        List<OmcSchemaRelationVO> uploadList = OmcSchemaRelationGroup2Group.getUploadRelationList();
        for(OmcSchemaRelationVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaRelationGroup2Group dom = new OmcSchemaRelationGroup2Group(vo);
            //dom.inActiviateObject(map);
        }
        for(OmcSchemaRelationVO vo : uploadList){
            OmcSchemaRelationGroup2Group dom = new OmcSchemaRelationGroup2Group(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForRoleGroup2User(){
        List<OmcSchemaRelationVO> inactiveList = OmcSchemaRelationRoleGroup2User.getInactiveRelationList();
        List<OmcSchemaRelationVO> uploadList = OmcSchemaRelationRoleGroup2User.getUploadRelationList();
        for(OmcSchemaRelationVO vo : inactiveList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            OmcSchemaRelationRoleGroup2User dom = new OmcSchemaRelationRoleGroup2User(vo);
            //dom.inActiviateObject(map);
        }
        for(OmcSchemaRelationVO vo : uploadList){
            OmcSchemaRelationRoleGroup2User dom = new OmcSchemaRelationRoleGroup2User(vo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(vo.getObid() == null){
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }
    private void processForMethodSet(){
//        List<OmcSchemaFileFormatVO> inactiveList = OmcSchemaFileFormat.getInactiveList();
//        List<OmcSchemaFileFormatVO> uploadList = OmcSchemaFileFormat.getUploadList();
//
//        for(OmcSchemaFileFormatVO vo : inactiveList){
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            OmcSchemaFileFormat dom = new OmcSchemaFileFormat(vo);
//            dom.inActiviateObject(map);
//        }
//        for(OmcSchemaFileFormatVO vo : uploadList){
//            OmcSchemaFileFormat dom = new OmcSchemaFileFormat(vo);
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            if(vo.getObid() == null){
//                dom.createObject(map);
//            }else{
//                dom.modifyObject(map);
//            }
//        }
    }
    private void processForDynamicAttribute(){
//        List<OmcSchemaFileFormatVO> inactiveList = OmcSchemaFileFormat.getInactiveList();
//        List<OmcSchemaFileFormatVO> uploadList = OmcSchemaFileFormat.getUploadList();
//
//        for(OmcSchemaFileFormatVO vo : inactiveList){
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            OmcSchemaFileFormat dom = new OmcSchemaFileFormat(vo);
//            dom.inActiviateObject(map);
//        }
//        for(OmcSchemaFileFormatVO vo : uploadList){
//            OmcSchemaFileFormat dom = new OmcSchemaFileFormat(vo);
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            if(vo.getObid() == null){
//                dom.createObject(map);
//            }else{
//                dom.modifyObject(map);
//            }
//        }
    }
    //---------------------------------------------------------------------------------------------------------
    private void initializeForConstantsUpload(){
        ;
    }
    private void initializeForBizUpload(){
        OmcSchemaSysBizClass.refreshAllTempBizObid();
    }
    private void initializeForRelUpload(){
        OmcSchemaSysRelClass.refreshAllTempRelObid();
    }
    private void initializeForAttrUpload(){
        OmcSchemaSysClassAttrInfo.initializeAttribute();
    }
    private void preProcessForConstantsUpload(){

    }
    private void preProcessForBizUpload(){

    }
    private void preProcessForRelUpload(){

    }
    private void preProcessForAttrUpload(){

    }
    private void processForAttrUpload(){
        List<OmcSchemaSysClassAttrInfoVO> inActiveList = OmcSchemaSysClassAttrInfo.getInactiveListForAttr();
        for(OmcSchemaSysClassAttrInfoVO vo : inActiveList){
            HashMap<String, OmcSchemaSysClassAttrInfoVO> map = new HashMap<String, OmcSchemaSysClassAttrInfoVO>();
            OmcSchemaSysClassAttrInfo dom = new OmcSchemaSysClassAttrInfo(vo);
            dom.deleteObject(map);
            //dom.inActiviateObject(map);
        }
        List<OmcSchemaSysClassAttrInfoVO> allTemporaryList = OmcSchemaSysClassAttrInfo.getAllTemporayAttribute();
        for(OmcSchemaSysClassAttrInfoVO vo : allTemporaryList){
            OmcSchemaSysClassAttrInfo dom = new OmcSchemaSysClassAttrInfo(vo);
            HashMap<String, OmcSchemaSysClassAttrInfoVO> map = new HashMap<String, OmcSchemaSysClassAttrInfoVO>();
            if(vo.getObid() == null)
            {
                dom.createObject(map);
            }else{
                OmcSchemaSysClassAttrInfo savedDom =new OmcSchemaSysClassAttrInfo(vo.getObid(),false);
//                if(!dom.isEqual(savedDom.getVo())){
                    map.put("dataBaseVO", savedDom.getVo());
                    dom.modifyObject(map);
//                }
            }
        }
    }
    private void processForAttrDisplayedUpload(){
        List<OmcSchemaAttributeVO> inActiveList = OmcSchemaAttribute.getInactiveListForAttr();
        for(OmcSchemaAttributeVO vo : inActiveList){
            HashMap<String, OmcSchemaAttributeVO> map = new HashMap<String, OmcSchemaAttributeVO>();
            OmcSchemaAttribute dom = new OmcSchemaAttribute(vo);
            dom.inActiviateObject(map);
        }
        List<OmcSchemaAttributeVO> allTemporaryList = OmcSchemaAttribute.getAllTemporayAttribute();
        for(OmcSchemaAttributeVO vo : allTemporaryList){
            OmcSchemaAttribute dom = new OmcSchemaAttribute(vo);
            HashMap<String, OmcSchemaAttributeVO> map = new HashMap<String, OmcSchemaAttributeVO>();
            if(vo.getObid() == null)
            {
                dom.createObject(map);
            }else{
                dom.modifyObject(map);
            }
        }
    }

    private void processForRelUpload(){
        List<OmcSchemaSysRelClassVO> inActiveRelClassList = OmcSchemaSysRelClass.getInactiveRelListForUpload();
        for(OmcSchemaSysRelClassVO classVO : inActiveRelClassList){
            OmcSchemaSysRelClass classDom = new OmcSchemaSysRelClass(classVO);
            classDom.inActiviateObject(null);
        }
        List<OmcSchemaSysRelClassVO> allChildTempRelClassList = OmcSchemaSysRelClass.getTemporaryChildSystemRelClassList(true, null, OmcSystemConstants.OBJECT_ROOT, 100);
        for(OmcSchemaSysRelClassVO classVO : allChildTempRelClassList){
            OmcSchemaSysRelClass classDom = new OmcSchemaSysRelClass(classVO);
            HashMap<String, OmcSchemaSysRelClassVO> map = new HashMap<String, OmcSchemaSysRelClassVO>();
            if(classVO.getObid() == null)
            {
                classDom.createObject(map);
            }else {
                OmcSchemaSysRelClass savedDom = new OmcSchemaSysRelClass(classVO.getObid());
                //if(!classDom.isEqual(savedDom.getVo())){
                    LOGGER.debug("Modify Process({})",savedDom.getVo().getNames());
                    map.put("dataBaseVO", savedDom.getVo());
                    classDom.modifyObject(map);
                //}
            }
        }
    }
    private void postProcessForConstantUpload(){
        ;
    }
    private void postprocessForBizUpload(){
        List<OmcSchemaSysBizClassVO> childList = OmcSchemaSysBizClass.getChildSystemBizClassList(false, null, OmcSystemConstants.BUSINESS_OBJECT, 100);
        OmcSchemaSysBizClass classDom = null;
        for(OmcSchemaSysBizClassVO classVO : childList){
            classDom = new OmcSchemaSysBizClass(classVO);
            classDom.makeRevisible();
        }
        HashMap<String, OmcSchemaSysBizClassVO> map = OmcSchemaSysBizClass.getWorkflowableList();
        for(String key : map.keySet()){
            classDom = new OmcSchemaSysBizClass((OmcSchemaSysBizClassVO)map.get(key));
            classDom.makeWorkflowable();
        }
    }
    private void postprocessForRelUpload(){

    }
    private void postProcessForAttrUpload(){

    }
    private void processForConstantUpload(){
        List<OmcSchemaSysConstantVO> inActiveList = OmcSchemaSysConstant.getInactiveListForUpload();
        for(OmcSchemaSysConstantVO vo : inActiveList){
            OmcSchemaSysConstant dom = new OmcSchemaSysConstant(vo);
            dom.inActiviateObject(null);
        }
        List<OmcSchemaSysConstantVO> allList = OmcSchemaSysConstant.getAllTemporaryListForUpload();
        for(OmcSchemaSysConstantVO vo : allList){
            OmcSchemaSysConstant dom = new OmcSchemaSysConstant(vo);
            if(vo.getObid() == null)
            {
                dom.createObject(null);
            }else{
                dom.modifyObject(null);
            }
        }
    }
    private void processForBizUpload(){
        List<OmcSchemaSysBizClassVO> inActiveBizClassList = OmcSchemaSysBizClass.getInactiveListForUpload();
        for(OmcSchemaSysBizClassVO bizClassVO : inActiveBizClassList){
            OmcSchemaSysBizClass bizClassDom = new OmcSchemaSysBizClass(bizClassVO);
            bizClassDom.inActiviateObject(null);
        }
        List<OmcSchemaSysBizClassVO> allChildTempBizClassList = OmcSchemaSysBizClass.getTemporaryChildSystemBizClassList(true, null, OmcSystemConstants.OBJECT_ROOT, 100);
        for(OmcSchemaSysBizClassVO bizClassVO : allChildTempBizClassList){
            OmcSchemaSysBizClass bizClassDom = new OmcSchemaSysBizClass(bizClassVO);
            HashMap<String, OmcSchemaSysBizClassVO> map = new HashMap<String, OmcSchemaSysBizClassVO>();
            if(bizClassVO.getObid() == null)
            {
                bizClassDom.createObject(map);
            }else{
                OmcSchemaSysBizClass savedDom    = new OmcSchemaSysBizClass(bizClassVO.getObid());
                //if(!bizClassDom.isEqual(savedDom.getVo())){
                    map.put("dataBaseVO", savedDom.getVo());
                    bizClassDom.modifyObject(map);
                //}
            }
        }
    }
    private void validataForConstantsUpload(){
        ;
    }
    private void validataForBizClassUpload(){
        List<OmcSchemaSysBizClassVO> duplicatedBixLlist = OmcSchemaSysBizClass.getDuplicatedBizClassList();
        if(duplicatedBixLlist.size() > 0) {
            StringBuffer strBuf = new StringBuffer();
            boolean isFirst = true;
            for(OmcSchemaSysBizClassVO bizClassVO : duplicatedBixLlist){
                if(isFirst){
                    strBuf.append("Duplicate Business Class List========>").append(OmcFoundationConstant.newline);
                }else{
                    strBuf.append(OmcFoundationConstant.newline);
                }
                strBuf.append("Excel Line Number:").append(bizClassVO.getSequences()).append("=>").append(bizClassVO.getNames());
                isFirst = false;
            }
            LOGGER.debug(strBuf.toString());
            throw new FoundationException("omc.schema.bizclass.duplicated");
        }
        List<OmcSchemaSysBizClassVO> notFoundParentList = OmcSchemaSysBizClass.getNotFoundParntBizClassList();
        if(notFoundParentList.size() > 0) {
            StringBuffer strBuf = new StringBuffer();
            boolean isFirst = true;
            for(OmcSchemaSysBizClassVO bizClassVO : notFoundParentList){
                if(isFirst){
                    strBuf.append("Not Found Parent Class List========>").append(OmcFoundationConstant.newline);
                }else{
                    strBuf.append(OmcFoundationConstant.newline);
                }
                strBuf.append("Excel Line Number:").append(bizClassVO.getSequences()).append("=>").append(bizClassVO.getNames());
                isFirst = false;
            }
            LOGGER.debug(strBuf.toString());
            throw new FoundationException("omc.schema.bizclass.parentnotfond");
        }
        if(!OmcSchemaSysBizClass.checkClassHeiarchy()) throw new FoundationException("omc.schema.bizclass.heiarchy");
    }
    private void validataForRelClassUpload(){
        List<OmcSchemaSysRelClassVO> duplicatedBixLlist = OmcSchemaSysRelClass.getDuplicatedRelClassList();
        if(duplicatedBixLlist.size() > 0) {
            StringBuffer strBuf = new StringBuffer();
            boolean isFirst = true;
            for(OmcSchemaSysRelClassVO relClassVO : duplicatedBixLlist){
                if(isFirst){
                    strBuf.append("Duplicate Relation Class List========>").append(OmcFoundationConstant.newline);
                }else{
                    strBuf.append(OmcFoundationConstant.newline);
                }
                strBuf.append("Excel Line Number:").append(relClassVO.getSequences()).append("=>").append(relClassVO.getNames());
                isFirst = false;
            }
            LOGGER.debug(strBuf.toString());
            throw new FoundationException("omc.schema.relclass.duplicated");
        }
        List<OmcSchemaSysRelClassVO> notFoundParentList = OmcSchemaSysRelClass.getNotFoundParntRelClassList();
        if(notFoundParentList.size() > 0) {
            StringBuffer strBuf = new StringBuffer();
            boolean isFirst = true;
            for(OmcSchemaSysRelClassVO relClassVO : notFoundParentList){
                if(isFirst){
                    strBuf.append("Not Found Parent Class List========>").append(OmcFoundationConstant.newline);
                }else{
                    strBuf.append(OmcFoundationConstant.newline);
                }
                strBuf.append("Excel Line Number:").append(relClassVO.getSequences()).append("=>").append(relClassVO.getNames());
                isFirst = false;
            }
            LOGGER.debug(strBuf.toString());
            throw new FoundationException("omc.schema.relclass.parentnotfond");
        }
        if(!OmcSchemaSysRelClass.checkClassHeiarchy()) {
            throw new FoundationException("omc.schema.relclass.heiarchy");
        }
    }
    private void validataForAttrUpload(){
        List<OmcSchemaSysClassAttrInfoVO> duplicatedTypeList = OmcSchemaSysClassAttrInfo.getDuplicatedAttrTypeList();
        if(duplicatedTypeList.size() > 0) {
            StringBuffer strBuf = new StringBuffer();
            boolean isFirst = true;
            for(OmcSchemaSysClassAttrInfoVO duplicatedType : duplicatedTypeList){
                if(isFirst){
                    strBuf.append("Duplicate Attribute List========>").append(OmcFoundationConstant.newline);
                }else{
                    strBuf.append(OmcFoundationConstant.newline);
                }
                strBuf.append("Excel Line Number:").append(duplicatedType.getSequences()).append("=>").append(duplicatedType.getClassType()).append(":").append(duplicatedType.getClassName()).append(":").append(duplicatedType.getAttributeName());
                isFirst = false;
            }
            LOGGER.debug(strBuf.toString());
            throw new FoundationException("omc.schema.attr.attrduplicated");
        }
        List<OmcSchemaSysClassAttrInfoVO> notfoundClassList = OmcSchemaSysClassAttrInfo.getNotFefinedClassForAttr();
        if(notfoundClassList.size() > 0) {
            StringBuffer strBuf = new StringBuffer();
            boolean isFirst = true;
            for(OmcSchemaSysClassAttrInfoVO duplicatedType : notfoundClassList){
                if(isFirst){
                    strBuf.append("Not Defined Class List========>").append(OmcFoundationConstant.newline);
                }else{
                    strBuf.append(OmcFoundationConstant.newline);
                }
                strBuf.append("Excel Line Number:").append(duplicatedType.getSequences()).append("=>").append(duplicatedType.getClassType()).append(":").append(duplicatedType.getClassName()).append(":").append(duplicatedType.getAttributeName());
                isFirst = false;
            }
            LOGGER.debug(strBuf.toString());
            throw new FoundationException("omc.schema.attr.notdefined");
        }
        List<OmcSchemaSysClassAttrInfoVO> duplicatedAttrList = OmcSchemaSysClassAttrInfo.getDuplicatdAttr();
        if(duplicatedAttrList.size() > 0) {
            StringBuffer strBuf = new StringBuffer();
            boolean isFirst = true;
            for(OmcSchemaSysClassAttrInfoVO duplicatedType : duplicatedAttrList){
                if(isFirst){
                    strBuf.append("Type Duplicate Attribute List========>").append(OmcFoundationConstant.newline);
                }else{
                    strBuf.append(OmcFoundationConstant.newline);
                }
                strBuf.append("Excel Line Number:").append(duplicatedType.getSequences()).append("=>").append(duplicatedType.getClassType()).append(":").append(duplicatedType.getClassName()).append(":").append(duplicatedType.getAttributeName());
                isFirst = false;
            }
            LOGGER.debug(strBuf.toString());
            throw new FoundationException("omc.schema.attr.attrduplicated");
        }
    }
}
