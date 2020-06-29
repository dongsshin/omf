/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CacheUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 3. 23. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util.omc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import com.rap.omc.api.util.model.EhCacheStatisticVO;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.api.util.spring.SpringFactoryUtil;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.statistics.StatisticsGateway;

/**
 * <pre>
 * Class : CacheUtil
 * Description : @Cachable을 사용하지 못하는 경우를 위해(Dom에서), ehcache에서 제공하는 기능을 util 형태로 제공함
                 # Cache에 대한 get / put / evict / clear 등의 기능을 제공함
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class CacheUtil {

    private CacheManager cacheManager;

    private static CacheUtil cInstance;

    /**
     * Singleton을 구현한 getInstance() method
     */
    private synchronized static CacheUtil getInstance(){
        if (cInstance == null) {
            cInstance = new CacheUtil();
            cInstance.cacheManager = (CacheManager)SpringFactoryUtil.getBean("cacheManager");
        }
        return cInstance;
    }

    /**
     * default cache 정보를 모두 삭제한다.
     */
    public static void clearCache(){
        getInstance().cacheManager.getCache("default").clear();
    }

    /**
     * cacheName에 해당하는 cache 정보를 모두 삭제한다.
     *
     * @param cacheName 캐시 이름
     */
    public static void clearCache(String cacheName){
        getInstance().cacheManager.getCache(cacheName).clear();
    }
    
    /**
     * cacheName에 해당하는 cache 정보에서 key에 해당하는 mapping 정보를 삭제한다.
     *
     * @param cacheName 캐시 이름
     * @param keyName
     */
    public static void evictCache(String cacheName, String keyName){
        getInstance().cacheManager.getCache(cacheName).evict(keyName);
    }
    
    /**
     * cacheName에 해당하는 cache 정보에서 key에 해당하는 mapping 정보(value)를 추가한다. 
     *
     * @param cacheName 캐시 이름
     * @param keyName key 이름
     * @param value 캐시 key에 저장할 데이터
     */
    public static void putCache(String cacheName, String keyName, Object value){
        Cache cache = getInstance().cacheManager.getCache(cacheName);
        if(cache == null) 
        {
            throw new FoundationException("Cash(" + cacheName + ") is not defined.");
        }
        getInstance().cacheManager.getCache(cacheName).put(keyName, value);
    }
    
    /**
     * cacheName에 해당하는 cache 정보에서 key에 해당하는 mapping 정보(value)를 조회하여 리턴한다. 
     *
     * @param cacheName 캐시 이름
     * @param keyName key 이름
     * @return
     */
    public static Object getCache(String cacheName, String keyName){
        
        return getInstance().cacheManager.getCache(cacheName).get(keyName);
    }
    public static Object getCacheValue(String cacheName, String keyName){
        SimpleValueWrapper obj = (SimpleValueWrapper)getCache(cacheName,keyName);
        if(obj==null) return null;
        return obj.get();
    }
    
    /**
    *
    * @param keyName
    * @param sourceValue
    */
    public static boolean isAvailableCache(String cacheName, String keyName, String sourceValue){
       //master
       boolean avaliableCacheFlag = false;
       Object objectCache = getInstance().cacheManager.getCache(cacheName).get(keyName);
       if (objectCache == null){
           avaliableCacheFlag = false;
           getInstance().cacheManager.getCache(cacheName).put(keyName, sourceValue);
           
           
       }else{
           String cacheValue = (String)((SimpleValueWrapper)objectCache).get(); 
           
           if(StringUtils.defaultString(cacheValue).equals(StringUtils.defaultString(sourceValue))){
               avaliableCacheFlag = true;    
           }else{
               avaliableCacheFlag = false;
               getInstance().cacheManager.getCache(cacheName).put(keyName, sourceValue);
           }
       }
       return avaliableCacheFlag;
    }
    public static List<EhCacheStatisticVO> getEhcacheStatistics(){
        List<EhCacheStatisticVO> list = new ArrayList<EhCacheStatisticVO>();
       SimpleDateFormat formtter = new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss");
        for (String name : getInstance().cacheManager.getCacheNames()) {
            Cache cache = getInstance().cacheManager.getCache(name);
            Ehcache ehcache = (Ehcache)cache.getNativeCache();
//            List<Object> keys = ehcache.getKeys();
//            StringBuffer stBuf = new StringBuffer();
//            for(Object key : keys){
//                stBuf.setLength(0);
//                Element element = ehcache.get(key);
//                if(!NullUtil.isNull(element)){
//                    stBuf.append("Key:").append(key);
//                    stBuf.append("/Count:").append(element.getHitCount());
//                    stBuf.append("/Creattion Time:").append(formtter.format(new Date(element.getCreationTime())));
//                    stBuf.append("/Last Access Time:").append(formtter.format(new Date(element.getLastAccessTime())));
//                    System.out.println(stBuf.toString());
//                }                    
//            }
            StatisticsGateway statistics = ehcache.getStatistics();
            EhCacheStatisticVO vo = new EhCacheStatisticVO(ehcache.getName(),
                                                           String.valueOf(statistics.getSize()),
                                                           String.valueOf(statistics.getLocalHeapSizeInBytes()/1024) + "(KB)",
                                                           String.valueOf(statistics.getLocalOffHeapSizeInBytes()/1024) + "(KB)",
                                                           String.valueOf(statistics.getLocalDiskSizeInBytes()/1024)+ "(KB)",
                                                           String.valueOf(statistics.cacheHitCount()),
                                                           String.valueOf(statistics.cacheMissCount()));
         
            list.add(vo);
        }
        return list;
    }
}
