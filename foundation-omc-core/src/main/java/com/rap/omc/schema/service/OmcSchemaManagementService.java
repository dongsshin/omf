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

import java.util.Map;

import com.rap.omc.schema.util.OmcEnvironment;

/**
 * <pre>
 * Class : OmcSchemaManagementService
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */

public interface OmcSchemaManagementService {
    public void txnSchemaUpload(
            OmcEnvironment.Environment envionment,
            String schemaDir,
            boolean isClassAttrUpload,
            boolean isMenuUpload,
            boolean isLifeCycleUpload,
            boolean isEtcUpload,
            boolean isConstantUpload,
            boolean isTemporaryOnly,
            Map<String,String> schemaExcelScheet);
    public void synchronizeClassProcess(OmcEnvironment.Environment envionment, String propertyUtilClass, String objectRootVOClass);
    public void dumpTableScripts(OmcEnvironment.Environment envionment,boolean isFull, boolean includeCreateIndex, long targetDBMSType);
    public void txnInitialSchemaSetupMain(OmcEnvironment.Environment envionment,String defaultSite);
    //public void secondSchemaSetupMain(OmcEnvironment.Environment envionment);
    public void dumpIndexScriptsAll(OmcEnvironment.Environment envionment, boolean isParallelOption);
}
