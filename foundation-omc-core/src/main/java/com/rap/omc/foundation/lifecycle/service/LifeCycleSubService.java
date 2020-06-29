/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : LifecycleService.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 2. 16. kwanghyui.choi Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.service;

import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;

/**
 * <pre>
 * Class : LifecycleService
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public interface LifeCycleSubService {
    public LifeCycleInfo getLifeCycleInfo(String lifeCycleName);
}
