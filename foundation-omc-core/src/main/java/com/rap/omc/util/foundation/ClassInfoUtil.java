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
package com.rap.omc.util.foundation;

import java.util.List;

import com.rap.omc.foundation.classes.model.ClassDbmsTableVO;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ClassNameForDisplayVO;
import com.rap.omc.foundation.classes.service.ClassInfoService;
import com.rap.omc.foundation.classes.service.ClassInfoSubService;
import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;
import com.rap.omc.api.util.spring.SpringFactoryUtil;



/**
 * <pre>
 * Class : ClassInfoUtil
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class ClassInfoUtil {

    private ClassInfoService classInfoService;
    private ClassInfoSubService classInfoSubService;

    private static ClassInfoUtil sInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static ClassInfoUtil getInstance(){
        if (sInstance == null) {
            sInstance = new ClassInfoUtil();
            sInstance.classInfoService    = (ClassInfoService)SpringFactoryUtil.getBean("classInfoService");
            sInstance.classInfoSubService = (ClassInfoSubService)SpringFactoryUtil.getBean("classInfoSubService");
        }
        return sInstance;
    }
    public static ClassInfo getClassInfo(String sessionFactoryName, String className){
        return getInstance().classInfoSubService.getClassInfo(sessionFactoryName,className);
    }
    public static List<String> getChildClassList(String className){
        return getInstance().classInfoService.getChildClassList(className);
    }
    public static String getChildClassListStr(String className){
        return getInstance().classInfoService.getChildClassListStr(className);
    }
    public static List<String> getUpperClassList(String className){
        return getInstance().classInfoService.getUpperClassList(className);
    }
    public static String getUpperClassListStr(String className){
        return getInstance().classInfoService.getUpperClassListStr(className);
    }
    public static ClassInfo getClassInfo(String className){
        return getInstance().classInfoSubService.getClassInfo(className);
    }
    public static List<ClassInfo> getClassInfoList(String classNameList){
        return getInstance().classInfoService.getClassInfoList(classNameList);
    }
    public static List<String> getInstantiableChildList(String classNameList){
        return getInstance().classInfoService.getInstantiableChildList(classNameList);
    }
    public static List<String> getDbmsTableList(String classNameList){
        return getInstance().classInfoService.getDbmsTableList(classNameList);
    }
    public static List<ClassDbmsTableVO> getClassAndDbmsTableList(String classNameList){
        return getInstance().classInfoService.getClassAndDbmsTableList(classNameList);
    }
    
    public static List<String> getRanges(String className, String attribute){
        return getInstance().classInfoService.getRanges(className,attribute);
    }
    public static List<String> getRanges(String className, String attribute, boolean sort){
        return getInstance().classInfoService.getRanges(className,attribute, sort);
    }
    public static List<ClassNameForDisplayVO> getChildClassListForCombo(String className){
        return getInstance().classInfoService.getChildClassListForCombo(className);
    }
    public static List<LifeCycleInfo> getAllowedLifeCycleList(String className){
        return getInstance().classInfoService.getAllowedLifeCycleList(className);
    }
}
