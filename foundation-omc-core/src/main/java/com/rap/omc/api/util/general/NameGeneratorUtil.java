/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : NameGeneratorUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 22. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util.general;

import com.rap.omc.api.service.IdGenerateService;
import com.rap.omc.api.util.spring.SpringFactoryUtil;

/**
 * 
 * <pre>
 * Class : NameGenerator
 * Description : IdGenerateService를 injection/getBean() 없이 사용하기 위한 Utility
 * </pre>
 * 
 * @author s_dongsshin
 */
public class NameGeneratorUtil {

    private static NameGeneratorUtil nInstance;

    private IdGenerateService idGenerateService;

    /**
     * getInstance() method for Singleton
     */
    private synchronized static NameGeneratorUtil getInstance(){
        if (nInstance == null) {
            nInstance = new NameGeneratorUtil();
            nInstance.idGenerateService = (IdGenerateService)SpringFactoryUtil.getBean("idGenerateService");
        }
        return nInstance;
    }
    /**
     * Generate wanted unique names randomly
     *
     * @param wantedCount Count to generate unique name.
     * @return Generated unique names(One More)
     */
    public static String[] generateUniqueNameSet(int wantedCount){
        return getInstance().idGenerateService.generateUniqueNameSet(wantedCount);
    }
    /**
     * Generate Unique Name
     *
     * @param idKey
     * @return Generated unique name(One)
     */
    public static String generateUniqueName(String idKey){
        return getInstance().idGenerateService.generateUniqueName(idKey);
    }
    
}
