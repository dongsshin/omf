package com.rap.schema.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rap.omc.schema.service.OmcSchemaManagementService;
import com.rap.omc.schema.util.OmcEnvironment;
import com.rap.omc.schema.util.OmcSchemaExcelFileConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.schema.service.SchemaManagementService;

/**
 * <pre>
 * Class : OmcSchemaManagementServiceImpl
 * Description : TODO
 * </pre>
 *
 * @author s_dongsshin
 */
@Service("schemaManagementService")
public class SchemaManagementServiceImpl implements SchemaManagementService{
    static final Logger LOGGER = LoggerFactory.getLogger(SchemaManagementServiceImpl.class);
    
    @Autowired
    private OmcSchemaManagementService omcSchemaManagementService;
    
    static final String SCHEMA_FILE_DIR = "C:\\SpringBoot\\schema\\";
    
    private  Map<String,String> getSchemaExcelScheetMap(){
        Map<String,String> schemaExcelScheetMap = new HashMap<String,String>(); 
        schemaExcelScheetMap.put(OmcSchemaExcelFileConstants.C_FILE_NAME_CLASSATTR, "Schema_Class.xlsx");
        schemaExcelScheetMap.put(OmcSchemaExcelFileConstants.C_FILE_NAME_CONSTANTS, "Schema_Constants.xlsx");
        schemaExcelScheetMap.put(OmcSchemaExcelFileConstants.C_FILE_NAME_LIFECYCLE, "Schema_Life Cycle.xlsx");
        schemaExcelScheetMap.put(OmcSchemaExcelFileConstants.C_FILE_NAME_MENU, "Schema_Menu.xlsx");
        schemaExcelScheetMap.put(OmcSchemaExcelFileConstants.C_FILE_NAME_ETC, "Schema_Etc.xlsx");
        return schemaExcelScheetMap;
    }
	@Override
	public void loadSchemaExcel() {
		OmcEnvironment.Environment envionment = OmcEnvironment.Environment.DEVELOPMENT;
		boolean isClassAttrUpload = false;
        boolean isMenuUpload= false;
        boolean isLifeCycleUpload= false;
        boolean isEtcUpload= true;
        boolean isConstantUpload= false;
        boolean isTemporaryOnly= false;
        
        Map<String,String> schemaExcelScheetMap = getSchemaExcelScheetMap();
//        try {
//			omcSchemaManagementService.txnSchemaUpload(envionment,SCHEMA_FILE_DIR,isClassAttrUpload,isMenuUpload,isLifeCycleUpload,isEtcUpload,isConstantUpload,isTemporaryOnly,schemaExcelScheetMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        //if(isClassAttrUpload){
        	//omcSchemaManagementService.synchronizeClassProcess(envionment,OmcSchemaExcelFileConstants.C_PROPERTY_UTIL_CLASS,OmcSchemaExcelFileConstants.C_OBJECT_ROOT_VO_CLASS);
        	omcSchemaManagementService.dumpTableScripts(envionment,false,true,OmcSystemConstants.DBMS_TYPE_MARIA);
        //}
        System.out.println("Completed");
	}
	@Override
	public void schemaSetup() {
		OmcEnvironment.Environment envionment = OmcEnvironment.Environment.DEVELOPMENT;
		Map<String,String> schemaExcelScheetMap = this.getSchemaExcelScheetMap();
		
        omcSchemaManagementService.txnInitialSchemaSetupMain(envionment,"LG");
        //User Role Locaiton Strore
        omcSchemaManagementService.txnSchemaUpload(envionment,SCHEMA_FILE_DIR,false,false,false,true,false,false,schemaExcelScheetMap);
        //Constants
        omcSchemaManagementService.txnSchemaUpload(envionment,SCHEMA_FILE_DIR,false,false,false,false,true,false,schemaExcelScheetMap);
        //Class Relation
        omcSchemaManagementService.txnSchemaUpload(envionment,SCHEMA_FILE_DIR,true,false,false,false,false,false,schemaExcelScheetMap);
        //Life Cycle
        omcSchemaManagementService.txnSchemaUpload(envionment,SCHEMA_FILE_DIR,false,false,true,false,false,false,schemaExcelScheetMap);
        //Menu
        omcSchemaManagementService.txnSchemaUpload(envionment,SCHEMA_FILE_DIR,false,true,false,false,false,false,schemaExcelScheetMap);
	}
	
}