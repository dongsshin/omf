/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CommonServiceUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 27. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.util.foundation;

import com.rap.omc.foundation.classes.service.CommonCoreService;
import com.rap.omc.api.util.spring.SpringFactoryUtil;

/**
 * <pre>
 * Class : CommonServiceUtil
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class CommonCoreServiceUtil {

    private CommonCoreService commonCoreService;

    private static CommonCoreServiceUtil cInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static CommonCoreServiceUtil getInstance(){
        if (cInstance == null) {
            cInstance = new CommonCoreServiceUtil();
            cInstance.commonCoreService = (CommonCoreService)SpringFactoryUtil.getBean("commonCoreService");
        }
        return cInstance;
    }
    public static String getClassNameWithObid(String obid){
        return(getInstance().commonCoreService.getClassNameWithObid(obid));
    }
}
