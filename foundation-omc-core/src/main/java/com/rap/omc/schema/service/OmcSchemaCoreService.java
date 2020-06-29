/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaManagementService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.rap.omc.schema.util.OmcSchemaServiceUtils;

/**
 * <pre>
 * Class : OmcSchemaManagementService
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public interface OmcSchemaCoreService {
    public void txnUploadSystemConstants(long uploadOption) throws Exception;
    public void txnUploadClassAndAttr(long uploadOption) throws Exception;
    public void txnUploadMemusAndLayouts(long uploadOption) throws Exception;
    public void txnUploadLifeCycles(long uploadOption) throws Exception;
    public void txnUploadSchemaEtc(long uploadOption) throws Exception;
}