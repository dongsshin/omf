/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SchemaExcelImport.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 16.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rap.omc.schema.object.model.OmcSchemaSysBizClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSysClassAttrInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaSysConstantVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRelClassVO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public interface OmcSchemaExcelImportService {
    public void getSystemConstants( long uploadOption,String targetFile,Map<String,Object> map) throws IOException, InvalidFormatException;
    public void getClassAndAttr   ( long uploadOption,String targetFile,Map<String,Object> map) throws IOException, InvalidFormatException;
    public void getMemusAndLayouts( long uploadOption,String targetFile,Map<String,Object> map) throws IOException, InvalidFormatException;
    public void getLifeCycles     ( long uploadOption,String targetFile,Map<String,Object> map) throws IOException, InvalidFormatException;
    public void getEtcSchemas     ( long uploadOption,String targetFile,Map<String,Object> map) throws IOException, InvalidFormatException;
    
    //public void getSchemaData( long uploadOption,String targetFile,Map<String,Object> map) throws IOException, InvalidFormatException;
    //public void uploadSchemaData( long uploadOption,Map<String,Object> map) throws Exception;
    
    public void uploadSystemConstants( long uploadOption,Map<String,Object> map) throws Exception;
    public void uploadClassAndAttr   ( long uploadOption,Map<String,Object> map) throws Exception;
    public void uploadMemusAndLayouts( long uploadOption,Map<String,Object> map) throws Exception;
    public void uploadLifeCycles     ( long uploadOption,Map<String,Object> map) throws Exception;
    public void uploadEtcSchemas     ( long uploadOption,Map<String,Object> map) throws Exception;
}
