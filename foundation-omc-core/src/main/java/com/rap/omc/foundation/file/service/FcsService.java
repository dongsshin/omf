/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : FcsService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 4.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.foundation.file.service;

import com.rap.omc.foundation.file.model.FcsLocationVO;

/**
 * <pre>
 * Class : FcsService
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
public interface FcsService {
    public FcsLocationVO getFcsLocationForSite(String site, String storeName);
    public FcsLocationVO getFileLocationOrStore(String names);
    public FcsLocationVO getCurrentStoreForLifeCycle(String lifeCycle);
}
