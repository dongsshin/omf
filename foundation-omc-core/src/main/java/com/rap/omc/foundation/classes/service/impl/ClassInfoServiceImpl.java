/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CacheInterfaceImpl.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 18. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.foundation.classes.model.ClassDbmsTableVO;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ClassNameForDisplayVO;
import com.rap.omc.foundation.classes.model.ColumnInfo;
import com.rap.omc.foundation.classes.service.ClassInfoService;
import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.foundation.LifeCycleUtil;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : ClassInfoServiceImpl
 * Description : TODO
 * </pre>
 *
 * @author hyeyoung.park
 */ 
@Service("classInfoService")
public class ClassInfoServiceImpl implements ClassInfoService {

    public List<String> getChildClassList(String className){
        ClassInfo classInfo = getClassInfo(className);
        if(classInfo == null) return null;
        return(classInfo.getLowerClassList());
    }
    public String getChildClassListStr(String className){
        ClassInfo classInfo = getClassInfo(className);
        if(classInfo == null) return null;
        return(classInfo.getLowerClassListStr());
    }
    public List<String> getUpperClassList(String className){
        ClassInfo classInfo = getClassInfo(className);
        if(classInfo == null) return null;
        return(classInfo.getUpperClassList());
    }
    public String getUpperClassListStr(String className){
        ClassInfo classInfo = getClassInfo(className);
        if(classInfo == null) return null;
        return(classInfo.getUpperClassListStr());
    }
    public ClassInfo getClassInfo(String className){
        ClassInfo keyInfo = ClassInfoUtil.getClassInfo(className);
        return keyInfo;
    }
    public List<ClassInfo> getClassInfoList(String classNameList){
        List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
        String[] classList = classNameList.split(",");
        ClassInfo classInfo = new ClassInfo();
        for(int i = 0; i < classList.length; i++){
            if(!NullUtil.isNone(classList[i])){
                classInfo = getClassInfo(classList[i]);
                classInfoList.add(classInfo);                
            }
        }
        return classInfoList;
    }
//    public String getChildClassListStr(String classNameList){
//        List<ClassInfo> classInfoList = getClassInfoList(classNameList);
//        if(NullUtil.isNone(classInfoList)) return null;
//        StringBuffer strBuf = new StringBuffer();
//        for(ClassInfo classInfo : classInfoList){
//            strBuf.append(",").append(classInfo.getLowerClassListStr());
//        }
//        String[] strArray = strBuf.substring(1).toString().split(",");
//        strArray = StrUtil.uniquize(strArray);
//        strBuf.setLength(0);
//        for(int i = 0; i < strArray.length; i++){
//            strBuf.append(",").append(strArray[i]);
//        }
//        return(strBuf.substring(1).toString());
//    }
    public List<String> getInstantiableChildList(String classNameList){
        List<ClassInfo> classInfoList = getClassInfoList(classNameList);
        List<ClassDbmsTableVO> classDbmsTableList = new ArrayList<ClassDbmsTableVO>();
        List<String> result = new ArrayList<String>();
        for(ClassInfo classInfo : classInfoList){
            classDbmsTableList = classInfo.getInstantiableClassList();
            for(ClassDbmsTableVO vo : classDbmsTableList){
                if(!result.contains(vo.getClassName())){
                    result.add(vo.getClassName());
                }
            }
        }
        return result;
    }
    public List<String> getDbmsTableList(String classNameList){
        List<ClassInfo> classInfoList = getClassInfoList(classNameList);
        List<ClassDbmsTableVO> classDbmsTableList = new ArrayList<ClassDbmsTableVO>();
        List<String> result = new ArrayList<String>();
        for(ClassInfo classInfo : classInfoList){
            classDbmsTableList = classInfo.getInstantiableClassList();
            for(ClassDbmsTableVO vo : classDbmsTableList){
                if(!result.contains(vo.getDbmsTable())){
                    result.add(vo.getDbmsTable());
                }
            }
        }
        return result;
    }
    public List<ClassDbmsTableVO> getClassAndDbmsTableList(String classNameList){
        List<ClassInfo> classInfoList = getClassInfoList(classNameList);
        List<ClassDbmsTableVO> classDbmsTableList = new ArrayList<ClassDbmsTableVO>();
        List<ClassDbmsTableVO> result = new ArrayList<ClassDbmsTableVO>();
        for(ClassInfo classInfo : classInfoList){
            classDbmsTableList = classInfo.getInstantiableClassList();
            for(ClassDbmsTableVO vo : classDbmsTableList){
                if(!result.contains(vo)){
                    result.add(vo);
                }
            }
        }
        return result;
    }
    public List<String> getRanges(String className, String attribute){
        return getRanges(className,attribute,false);
    }
    public List<String> getRanges(String className, String attribute, boolean sort){
        ClassInfo classInfo = getClassInfo(className);
        List<ColumnInfo> columnInfoList = classInfo.getColumnList();
        List<String> list = new ArrayList<String>();
        for(ColumnInfo col : columnInfoList){
            if(col.getAttributeName().equals(attribute)){
                list = Arrays.asList(col.getValueSettingInfo().split(","));
                break;            
            }
        }
        
        if(sort) Collections.sort(list);
        return list;
    }
    public List<ClassNameForDisplayVO> getChildClassListForCombo(String className){
        ClassInfo classInfo = getClassInfo(className);
        return classInfo.getChildClassListForCombo();
    }
    public List<LifeCycleInfo> getAllowedLifeCycleList(String className){
        List<LifeCycleInfo> result = new ArrayList<LifeCycleInfo>();
        ClassInfo classInfo = getClassInfo(className);
        List<String> lifeCycleList = classInfo.getAllowedLifeCycleList();
        if(!NullUtil.isNone(lifeCycleList)){
            for(String lifeCycleStr : lifeCycleList){
                LifeCycleInfo vo = LifeCycleUtil.getLifeCycleInfo(lifeCycleStr);
                if(!NullUtil.isNull(vo)) result.add(vo);
            }
        }
        return(result);
    }

}
