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

import java.util.List;

import com.rap.omc.foundation.classes.model.ClassDbmsTableVO;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ClassNameForDisplayVO;
import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;



/**
 * <pre>
 * Class : ClassInfoService
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public interface ClassInfoService {
    public List<String> getChildClassList(String className);
    public String getChildClassListStr(String className);
    public List<String> getUpperClassList(String className);
    public String getUpperClassListStr(String className);
    
    public ClassInfo getClassInfo(String className);
    public List<ClassInfo> getClassInfoList(String classNameList);

    public List<String> getInstantiableChildList(String classNameList);
    public List<String> getDbmsTableList(String classNameList);
    public List<ClassDbmsTableVO> getClassAndDbmsTableList(String classNameList);
    public List<String> getRanges(String className, String attribute);
    public List<String> getRanges(String className, String attribute, boolean sort);
    public List<ClassNameForDisplayVO> getChildClassListForCombo(String className);
    public List<LifeCycleInfo> getAllowedLifeCycleList(String className);
    
}
