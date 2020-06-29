package com.rap.omc.api.util.omc;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
public class ThreadLocalUtil {

    /**
     * ThreadLocal.
     */
    private static ThreadLocal<ConcurrentHashMap<String, Object>> threadLocal = new ThreadLocal<ConcurrentHashMap<String, Object>>();

    /**
     * ThreadLocal 저장 대상 Key.
     */
    public static enum KEY {userId, userLoginSite, currentModule, remoteAddr, requestUri, 
    	                    contextBizObject, executor, locale,timeZone,
    	                    company, businessUnit, divisionUnit, plantUnit,isWBSAttrRefreshOff}
    
    /**
     * ThreadLocal 변수 초기화.
     */
    public static void init(){
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
        threadLocal.set(map);
    }

    /**
     * ThreadLocal 변수 삭제.
     */
    public static void destroy(){
        threadLocal.remove();
    }

    /**
     * ThreadLocal 초기화 여부.
     * 
     * @return isInitialized
     */
    public static boolean isInitialized(){
        return (threadLocal.get() != null);
    }

    /**
     * ThreadLocal에 정보 저장.
     * 
     * @param key
     * @param value
     */
    public static void set(KEY key, Object value){
        if (!ThreadLocalUtil.isInitialized()) {
            ThreadLocalUtil.init();
        }
        ConcurrentHashMap<String, Object> map = threadLocal.get();
        map.put(key.name(), value);
    }

    /**
     * ThreadLocal Value 취득.
     * 
     * @param key
     * @return
     */
    public static Object get(KEY key){
        ConcurrentHashMap<String, Object> map = threadLocal.get();
        if (map == null) {
            return null;
        }
        return map.get(key.name());
    }

    /**
     * ThreadLocal Value 취득.
     * 
     * @param key
     * @return
     */
    public static int getInt(KEY key, int defaultValue){
        ConcurrentHashMap<String, Object> map = threadLocal.get();
        if (map == null) {
            return defaultValue;
        }
        Object obj = map.get(key.name());
        if (obj instanceof Integer) {
            return (Integer)obj;
        } else {
            return defaultValue;
        }
    }

    /**
     * <pre>
     * ThreadLocal Value 취득.
     * Key 해당 항목이 존재하지 않는 경우 파라메타의 defaultStr 반환.
     * </pre>
     * 
     * @param key
     * @param defaultStr
     *            디폴트 문자열
     * @return
     */
    public static String getString(KEY key, String defaultStr){
        return StringUtils.defaultIfEmpty((String)ThreadLocalUtil.get(key), defaultStr);
    }
    /**
     * ThreadLocal의 User Id Return(없으면 GlobalConstants.NO_USER_ID Return)
     *
     * @param key
     * @param defaultStr 디폴트 문자열
     * @return
     */
    public static String getUserId(){
        return StringUtils.defaultIfEmpty((String)ThreadLocalUtil.get(KEY.userId), GlobalConstants.NO_USER_ID);
    }
    /**
     * ThreadLocal의 Site Return(없으면 LG Return)
     *
     * @param key
     * @param defaultStr 디폴트 문자열
     * @return
     */
    public static String getUserLoginSite(){
        return StringUtils.defaultIfEmpty((String)ThreadLocalUtil.get(KEY.userLoginSite), "");
    }
    /**
     * Locale 정보 Return
     *
     * @return
     */
    public static String getLocale(){
        return StringUtils.defaultIfEmpty((String)ThreadLocalUtil.get(KEY.locale), OmcSystemConstants.OMC_LOCALE_LANG_EN);
    }
}
