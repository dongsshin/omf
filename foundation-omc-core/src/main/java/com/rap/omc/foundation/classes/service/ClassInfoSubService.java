/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CacheInterface.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 18. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.service;

import com.rap.omc.foundation.classes.model.ClassInfo;

/**
 * <pre>
 * Class : ClassInfoService
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public interface ClassInfoSubService {
    public ClassInfo getClassInfo(String className);
    public ClassInfo getClassInfo(String sessionFactoryName, String className);
}
