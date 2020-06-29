/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : PropertyUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 2. 26. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.foundation.classes.service.CommonService;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.schema.object.model.OmcSchemaPropertyVO;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : PropertyUtil
 * Description : DB에 저장된 property 정보를 조회하여 저장하고, Constants 클래스에서 사용 가능하도록 util 형태로 제공함
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class PropertyUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);

    private static Hashtable<String, Object> propertyHash = new Hashtable<String, Object>();

    private static boolean bCachePropertyLoaded = false;

    private static Object lock = new Object();

    public static String getProperty(String key){
        return getProperty(key, "");
    }

    public static String getProperty(String key, String defaultValue){
        String property = null;
        try {
            checkPropertyCache();
            property = (String)propertyHash.get(key);
        } catch (Exception e) {
            LOGGER.warn("Error in getProperty():" + e);
        }

        if (property == null) {
            property = defaultValue;
        }
        return property;
    }

    public static long getPropertyByLong(String key){
        long property = 0;
        try {
            checkPropertyCache();
            property = (long)propertyHash.get(key);
        } catch (Exception e) {
            LOGGER.warn("Error in getPropertyByLong():" + e);
        }
        return property;
    }

    public static int getPropertyByInt(String key){
        int property = 0;
        try {
            checkPropertyCache();
            property = (int)propertyHash.get(key);
        } catch (Exception e) {
            LOGGER.warn("Error in getPropertyByInt():" + e);
        }
        return property;
    }

    public static void checkPropertyCache(){
        if (!(bCachePropertyLoaded)) {
            synchronized (lock) {
                if (!(bCachePropertyLoaded)) {
                    loadPropertyCache();
                }
            }
        }
    }

    public static void loadPropertyCache(){
        if (!(bCachePropertyLoaded)) {
            cacheProperty();
        }
    }
    public static void cacheProperty(){
        synchronized (lock) {
            bCachePropertyLoaded = false;
            propertyHash.clear();
            CommonService commonService = (CommonService)SpringFactoryUtil.getBean("commonService");
            List<OmcSchemaPropertyVO> variableList = commonService.getVariableList();
            if (!NullUtil.isNone(variableList)) {
                for (OmcSchemaPropertyVO property : variableList) {
                    if (!NullUtil.isNull(property.getPropertyValue())) {
                        propertyHash.put(property.getPropertyName(), property.getPropertyValue());
                    }
                }
                bCachePropertyLoaded = true;
            }
        }
    }
}
