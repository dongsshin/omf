/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CommonService.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.schema.service;

import java.util.List;

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





/**
 * <pre>
 * Class : CommonService
 * Description : TODO
 * </pre>
 * 
 * @author dongsik.shin
 */
public interface OmcAppicationSchemaManagementService {
    public SchemaBusinessObjectVO getSchemaBusienssObjectClass(String obid);
    public SchemaRelationObjectVO getSchemaRelationObjectClass(String obid);
   
    public List<SchemaBusinessObjectVO> getSchemaBusienssObjectClassAll();   
    public List<SchemaSchemaKindVO> getSchemaKindList();
    public List<SchemaBizAbdRelObjectVO> getSchemaBizAndRelObjectClassAll();
    public List<SchemaClassAttributeVO> getSchemaAttrForClass(String obid);
    public SchemaBizAbdRelObjectVO getSchemaBusinessObjectClassDetail(String obid);
    public List<SchemaLifeCycleVO> getLifeCycleListForClass(String obid);
    public List<SchemaBizAbdRelObjectVO> getRelationListForClass(String obid);
    public void createBusinessObjectClass(SchemaBusinessObjectVO businessObjectVO);
    public void inactiveBusinessObjectClass(String obid);
    public SchemaKeyTableVO getSchemKeyTable(String obid);
    
    
    //Menu
    
    public List<SchemaMenuVO> getSchemaMenuAll();
    public SchemaMenuVO getSchemaMenuDetail(String obid);
    public List<SchemaMenuVO> getSubMenuListForMenu(String obid);
    public List<SchemaLayoutAndTabVO> getRelatedLayoutForCmd(String obid);
    //Layout
    public List<SchemaLayoutAndTabVO> getSchemaLayoutAndTabAll();
    public SchemaLayoutAndTabVO getSchemaLayoutDetail(String obid);
    public List<SchemaLayoutAndTabVO> getTabOrCmdListForLayout(String obid);
    public List<SchemaLayoutAndTabVO> getTabListForLayout(String obid);
    public List<SchemaMenuVO> getCommandListForTab(String obid);
    //LifeCycle
    public List<SchemaLifeCycleVO> getLifeCycleAll();
    public SchemaLifeCycleVO getLifeCycleDetail(String obid);
    public List<SchemaLifeCycleStateInfoVO> getStateInfoListForLifeCycle(String obid);
    public List<SchemaLifeCycleBranchVO> getBranchListForLifeCycle(String obid);
    public List<SchemaMenuVO> testddddd(OmcSQLVariableParameter schemmaParmMapVO);
       
}