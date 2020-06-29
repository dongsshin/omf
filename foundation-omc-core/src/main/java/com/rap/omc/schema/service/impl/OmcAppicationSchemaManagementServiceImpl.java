/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CommonServiceImpl.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.schema.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.FoundationDao;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.schema.object.temp.model.SchemaBizAbdRelObjectVO;
import com.rap.omc.schema.object.temp.model.SchemaBusinessObjectVO;
import com.rap.omc.schema.object.temp.model.SchemaClassAttributeVO;
import com.rap.omc.schema.object.temp.model.SchemaKeyTableVO;
import com.rap.omc.schema.object.temp.model.SchemaLayoutAndTabVO;
import com.rap.omc.schema.object.temp.model.SchemaLifeCycleBranchVO;
import com.rap.omc.schema.object.temp.model.SchemaLifeCycleStateInfoVO;
import com.rap.omc.schema.object.temp.model.SchemaLifeCycleVO;
import com.rap.omc.schema.object.temp.model.SchemaMenuVO;
import com.rap.omc.schema.object.temp.model.SchemaRelationObjectVO;
import com.rap.omc.schema.object.temp.model.SchemaSchemaKindVO;
import com.rap.omc.schema.object.temp.model.SchemmaParmMapVO;
import com.rap.omc.schema.service.OmcAppicationSchemaManagementService;


/**
 * <pre>
 * Class : CommonServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@Service("omcAppicationSchemaManagementService")
public class OmcAppicationSchemaManagementServiceImpl implements OmcAppicationSchemaManagementService {

    @Resource(name = "foundationDao")
    private FoundationDao foundationDao;

    public SchemaKeyTableVO getSchemKeyTable(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        SchemaKeyTableVO result = foundationDao.select("SchemManagementInfo.getSchemKeyTable", schemmaParmMapVO);
        return result;
    }
    public SchemaBusinessObjectVO getSchemaBusienssObjectClass(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        SchemaBusinessObjectVO result = foundationDao.select("SchemManagementInfo.getSchemaBizClassObject", schemmaParmMapVO);
        return result;
    }   
    public SchemaRelationObjectVO getSchemaRelationObjectClass(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        SchemaRelationObjectVO result = foundationDao.select("SchemManagementInfo.getRelationObjectClass", schemmaParmMapVO);
        return result;
    }   
    public List<SchemaBusinessObjectVO> getSchemaBusienssObjectClassAll(){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid("OBID");
        List<SchemaBusinessObjectVO> result = foundationDao.selectList("SchemManagementInfo.getSchemaBizClassObjectAll", schemmaParmMapVO);
        return result;
    }
    public List<SchemaSchemaKindVO> getSchemaKindList(){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid("OBID");
        List<SchemaSchemaKindVO> result = foundationDao.selectList("SchemManagementInfo.getSchemaKindList");
        return result;
    }
    public List<SchemaBizAbdRelObjectVO> getSchemaBizAndRelObjectClassAll(){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid("OBID");
        List<SchemaBizAbdRelObjectVO> result = foundationDao.selectList("SchemManagementInfo.getSchemaBizAndRelObjectClassAll");
        return result;
    }
    public List<SchemaClassAttributeVO> getSchemaAttrForClass(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        List<SchemaClassAttributeVO> result = foundationDao.selectList("SchemManagementInfo.getSchemaAttrForClass", schemmaParmMapVO);
        return result;
    }
    public SchemaBizAbdRelObjectVO getSchemaBusinessObjectClassDetail(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        SchemaBizAbdRelObjectVO result = foundationDao.select("SchemManagementInfo.getSchemaBusinessObjectClassDetail", schemmaParmMapVO);
        return result;
    }
    public List<SchemaLifeCycleVO> getLifeCycleListForClass(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        List<SchemaLifeCycleVO> result = foundationDao.selectList("SchemManagementInfo.getLifeCycleListForClass", schemmaParmMapVO);
        return result;
    }
    public List<SchemaBizAbdRelObjectVO> getRelationListForClass(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        List<SchemaBizAbdRelObjectVO> result = foundationDao.selectList("SchemManagementInfo.getRelationListForClass",schemmaParmMapVO);
        return result;
    }
    public void createBusinessObjectClass(SchemaBusinessObjectVO businessObjectVO){
        foundationDao.insert("SchemManagementInfo.createBusinessObjectClass",businessObjectVO);
    }
    public void inactiveBusinessObjectClass(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        foundationDao.insert("SchemManagementInfo.inactiveBusinessObjectClass",schemmaParmMapVO);
    }
    //Menu
    public List<SchemaMenuVO> getSchemaMenuAll(){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        List<SchemaMenuVO> result = foundationDao.selectList("SchemManagementInfo.getSchemaMenuAll", schemmaParmMapVO);
        return result;
    }
    public SchemaMenuVO getSchemaMenuDetail(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        SchemaMenuVO result = foundationDao.select("SchemManagementInfo.getSchemaMenuDetail", schemmaParmMapVO);
        return result;
    }
    public List<SchemaMenuVO> getSubMenuListForMenu(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        List<SchemaMenuVO> result = foundationDao.selectList("SchemManagementInfo.getSubMenuListForMenu", schemmaParmMapVO);
        return result;
    }
    public List<SchemaLayoutAndTabVO> getRelatedLayoutForCmd(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        List<SchemaLayoutAndTabVO> result = foundationDao.selectList("SchemManagementInfo.getRelatedLayoutForCmd", schemmaParmMapVO);
        return result;
    }
    //Layout
    public List<SchemaLayoutAndTabVO> getSchemaLayoutAndTabAll(){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        List<SchemaLayoutAndTabVO> result = foundationDao.selectList("SchemManagementInfo.getSchemaLayoutAndTabAll", schemmaParmMapVO);
        return result;
    }
    public SchemaLayoutAndTabVO getSchemaLayoutDetail(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        SchemaLayoutAndTabVO result = foundationDao.select("SchemManagementInfo.getSchemaLayoutDetail", schemmaParmMapVO);
        return result;
    }
    public List<SchemaLayoutAndTabVO> getTabOrCmdListForLayout(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        List<SchemaLayoutAndTabVO> result = foundationDao.selectList("SchemManagementInfo.getTabOrCmdListForLayout", schemmaParmMapVO);
        return result;
    }
    public List<SchemaLayoutAndTabVO> getTabListForLayout(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        List<SchemaLayoutAndTabVO> result = foundationDao.selectList("SchemManagementInfo.getTabListForLayout", schemmaParmMapVO);
        return result;
    }
    public List<SchemaMenuVO> getCommandListForTab(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        List<SchemaMenuVO> result = foundationDao.selectList("SchemManagementInfo.getCommandListForTab", schemmaParmMapVO);
        return result;
    }
    public List<SchemaLifeCycleVO> getLifeCycleAll(){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid("All");
        List<SchemaLifeCycleVO> result = foundationDao.selectList("SchemManagementInfo.getLifeCycle", schemmaParmMapVO);
        return result;
    }
    public SchemaLifeCycleVO getLifeCycleDetail(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        SchemaLifeCycleVO result = foundationDao.select("SchemManagementInfo.getLifeCycle", schemmaParmMapVO);
        return result;
    }
    public List<SchemaLifeCycleStateInfoVO> getStateInfoListForLifeCycle(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        List<SchemaLifeCycleStateInfoVO> result = foundationDao.selectList("SchemManagementInfo.getStateInfoListForLifeCycle", schemmaParmMapVO);
        return result;
    }
    public List<SchemaLifeCycleBranchVO> getBranchListForLifeCycle(String obid){
        SchemmaParmMapVO schemmaParmMapVO = new SchemmaParmMapVO();
        schemmaParmMapVO.setObid(obid);
        List<SchemaLifeCycleBranchVO> result = foundationDao.selectList("SchemManagementInfo.getBranchListForLifeCycle", schemmaParmMapVO);
        return result;
    }
    
    public List<SchemaMenuVO> testddddd(OmcSQLVariableParameter schemmaParmMapVO){
        List<SchemaMenuVO> result = foundationDao.selectList("SchemManagementInfo.testddddd", schemmaParmMapVO);
        return result;
    }
    
}
