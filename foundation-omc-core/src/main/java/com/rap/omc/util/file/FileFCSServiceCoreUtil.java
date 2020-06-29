/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CacheUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 24. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.util.file;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.foundation.file.model.FcsLocationVO;
import com.rap.omc.foundation.file.service.FcsService;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : ClassInfoUtil
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class FileFCSServiceCoreUtil {

    private FcsService fcsService;

    private static FileFCSServiceCoreUtil sInstance;
    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static FileFCSServiceCoreUtil getInstance(){
        if (sInstance == null) {
            sInstance = new FileFCSServiceCoreUtil();
            sInstance.fcsService    = (FcsService)SpringFactoryUtil.getBean("fcsService");
        }
        return sInstance;
    }
    public static FcsLocationVO getFileStore(String names){
        FcsLocationVO storeVO = getFileLocationOrStore(names);
        if(NullUtil.isNull(storeVO)) return null;
        if(!storeVO.isStore()) return null;
        return storeVO;
    }
    public static FcsLocationVO getFileLocation(String names){
        FcsLocationVO storeVO = getFileLocationOrStore(names);
        if(NullUtil.isNull(storeVO)) return null;
        if(!storeVO.isLocation()) return null;
        return storeVO;
    }
    public static FcsLocationVO getStoreUsingObject(String obid){
        BusinessObjectRoot dom = new BusinessObjectRoot(obid);
        return getInstance().fcsService.getCurrentStoreForLifeCycle(dom.getVo().getLifeCycle());
    }
    public static FcsLocationVO getCurrentStoreForLifeCycle(String lifeCycle){
        return getInstance().fcsService.getCurrentStoreForLifeCycle(lifeCycle);
    }
    public static FcsLocationVO getFcsLocationForUser1(String userId, String storeName){
        return getFcsLocationForSite(ThreadLocalUtil.getUserLoginSite(),storeName);
    }
    public static FcsLocationVO getFcsLocationForSite(String site, String storeName){
        return getInstance().fcsService.getFcsLocationForSite(site,storeName);
    }
    private static FcsLocationVO getFileLocationOrStore(String names){
        return getInstance().fcsService.getFileLocationOrStore(names);
    }

}
