/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CommonUtils.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 10. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.util;

import java.util.ArrayList;
import java.util.List;

import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.service.ClassInfoService;
import com.rap.omc.foundation.classes.service.FoundationService;
import com.rap.omc.foundation.common.model.KeyInfo;
import com.rap.omc.foundation.common.model.search.SearchTargetInfo;
import com.rap.omc.foundation.model.VariableAttribute;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.foundation.ClassInfoUtil;




/**
 * <pre>
 * Class : BaseFoundationUtil
 * Description : 공통 유틸리티
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class BaseFoundationUtil {

    private FoundationService foundationService;
    private ClassInfoService  classInfoService;
    private static BaseFoundationUtil fInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static BaseFoundationUtil getInstance(){
        if (fInstance == null) {
            fInstance = new BaseFoundationUtil();
            fInstance.foundationService = (FoundationService)SpringFactoryUtil.getBean("foundationService");
            fInstance.classInfoService = (ClassInfoService)SpringFactoryUtil.getBean("classInfoService");
        }
        return fInstance;
    }
    
    public static KeyInfo getKeyInfo(ObjectRootVO searchInfo){
        return getInstance().foundationService.getKeyInfo(searchInfo);
    }
    public static KeyInfo getKeyInfo(String obid){
        return getInstance().foundationService.getKeyInfo(obid);
    }
    public static String getStringInheritanceClassList(String className){
        ClassInfo classInfo = getInstance().classInfoService.getClassInfo(className);
        String rtnStr = "";
        if (!NullUtil.isNull(classInfo)) {
            List<String> strClassList = classInfo.getUpperClassList();
            if(!NullUtil.isNone(strClassList)){
                StringBuffer strBuf = new StringBuffer();
                for (String str : strClassList) {
                    strBuf.append(",").append(str);
                }
                rtnStr = strBuf.substring(1).toString();
            }

        }
        return rtnStr;
    }
    public static List<String> getInheritanceClassList(String className){
        ClassInfo classInfo = getInstance().classInfoService.getClassInfo(className);
        return(classInfo.getUpperClassList());
    }
    
    public static boolean isKindOf(String wantedClassName, String parentClass ){
        List<String> upperClassList = getInheritanceClassList(wantedClassName);
        if(upperClassList.contains(parentClass)) return true;
        return(false);
    }
    
    public static ClassInfo getClassInfoByName(String className){
        return(getInstance().classInfoService.getClassInfo(className));
    }
    public static <T> List<T> getRelatedObjectList(SearchTargetInfo searchInfo){
        return getInstance().foundationService.getRelatedObjectList(searchInfo);
    }

    public static List<BusinessRelationObjectVO> getRelationList(SearchTargetInfo searchInfo){
        return getInstance().foundationService.getRelationList(searchInfo);
    }

    public static List<VariableAttribute> getDynamicAttributeList(String dynamicAttributeGroup){
        return getInstance().foundationService.getDynamicAttributeList(dynamicAttributeGroup);
    }
    public static List<String> getBusinessObjectMasters(String className,String names){
        List<String> list = getInstance().foundationService.getBusinessObjectMasters(className,names);
        if(list == null) return new ArrayList<String>();
        return list;
    }
    public static List<String> getBusinessObjects(String className,String names, String revision){
        List<String> list = getInstance().foundationService.getBusinessObjects(className,names,revision);
        if(list == null) return new ArrayList<String>();
        return list;
    }
    public static List<String> getBusinessObjectMasterList(String className,List<String> nameList){
        return getInstance().foundationService.getBusinessObjectMasterList(className,nameList);
    }
    public static List<String> getBusinessLatestObjectList(String className,List<String> nameList){
        return getInstance().foundationService.getBusinessLatestObjectList(className,nameList);
    }
    
    
    /**
     * CamelCase 변환
     *
     * @param underScore
     * @return
     */
    public static String convert2CamelCase(String underScore){
        if ((underScore.indexOf(95) < 0) && (Character.isLowerCase(underScore.charAt(0)))) { return underScore; }
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();

        for (int i = 0; i < len; ++i) {
            char currentChar = underScore.charAt(i);
            if (currentChar == '_') {
                nextUpper = true;
            } else if (nextUpper) {
                result.append(Character.toUpperCase(currentChar));
                nextUpper = false;
            } else {
                result.append(Character.toLowerCase(currentChar));
            }
        }

        return result.toString();
    }
    /**
     * CamelCase 변환
     *
     * @param underScore
     * @return
     */
    public static String convert2SemiCamelCase(String s){
        s = s.toLowerCase();
        int underIdx = s.indexOf("_");
        if(underIdx < 0) return s;
        StringBuffer strBuf = new StringBuffer(s.substring(0, underIdx).toLowerCase());
        strBuf.append( s.substring(underIdx + 1, underIdx + 2).toUpperCase() );
        strBuf.append( s.substring(underIdx + 2) );
        return strBuf.toString();
    }
    
    /**
     * package 정보 반환
     * 
     * @param className
     * @return
     */
    public static String getPackageInfo(String className){
        String packageInfo = null;
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
        if (NullUtil.isNull(classInfo)) { throw new FoundationException("omc.error.classInfo.nodata"); }
        if (NullUtil.isNone(classInfo.getJavaPackage())) {
            throw new FoundationException("omc.error.pkgInfo.nodata");
        } else {
            packageInfo = classInfo.getJavaPackage() + ".";
        }
        return packageInfo;
    }
}
