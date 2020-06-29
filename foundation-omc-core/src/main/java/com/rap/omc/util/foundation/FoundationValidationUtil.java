/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ValidationUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 29. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.util.foundation;

import java.util.List;

import com.rap.config.datasource.dao.FoundationDao;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.util.EtcUtil;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.classes.model.AllowedClassInfo;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.common.model.request.ValidateRelationRuleRequestInfo;
import com.rap.omc.foundation.common.model.request.VariableRequestInfo;
import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;
import com.rap.omc.foundation.lifecycle.model.StateInfo;
import com.rap.omc.foundation.lifecycle.service.LifeCycleService;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.NullUtil;


/**
 * <pre>
 * Class : ValidationUtil
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class FoundationValidationUtil {

    private static FoundationValidationUtil vInstance;

    
    private FoundationDao foundationDao;

    private LifeCycleService lifeCycleService;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static FoundationValidationUtil getInstance(){
        if (vInstance == null) {
            vInstance = new FoundationValidationUtil();
            vInstance.foundationDao = (FoundationDao)SpringFactoryUtil.getBean("foundationDao");
            vInstance.lifeCycleService = (LifeCycleService)SpringFactoryUtil.getBean("lifeCycleService");
        }
        return vInstance;
    }
    public static void checkRelation(ValidateRelationRuleRequestInfo searchInfo){
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(searchInfo.getRelationship());
        if (!NullUtil.isNull(classInfo)) {
            searchInfo.setTargetDbmsTable(classInfo.getDbmsTable());
            // 1. Instantiable check
            if ((classInfo.getFlags() & OmcSystemConstants.RELATION_FLAG_Instantiable) != OmcSystemConstants.RELATION_FLAG_Instantiable) {
                throw new FoundationException("omc.error.validate.notInstantiable");
            }
            // 2. AllowDuplicate check
            if ((classInfo.getFlags() & OmcSystemConstants.RELATION_FLAG_AllowDuplicate) != OmcSystemConstants.RELATION_FLAG_AllowDuplicate) {
                int count = getInstance().foundationDao.select("Relation.checkDuplicate", searchInfo);
                if (count > 0) {
                    throw new FoundationException("omc.error.validate.notAllowDuplicate");
                }
            }
            // 3. Cardinality check
            checkRelationCardinality(searchInfo, classInfo);
        }
        checkValidationFromToType(searchInfo);
    }
    private static void checkValidationFromToType(ValidateRelationRuleRequestInfo searchInfo){
        long flags = searchInfo.getFlags();
        String fromClass = searchInfo.getFromClass();
        String toClass = searchInfo.getToClass();
        String relationship = searchInfo.getRelationship();
        
        ClassInfo relClassInfo = ClassInfoUtil.getClassInfo(relationship);
        if(NullUtil.isNull(relClassInfo)) throw new FoundationException("Not Found Class" + relationship);
        ClassInfo fromClassInfo = ClassInfoUtil.getClassInfo(fromClass);
        if(NullUtil.isNull(fromClassInfo)) throw new FoundationException("Not Found Class" + fromClass);
        ClassInfo toClassInfo = ClassInfoUtil.getClassInfo(toClass);
        if(NullUtil.isNull(toClassInfo)) throw new FoundationException("Not Found Class" + toClass);
        
        List<AllowedClassInfo> allowedClassInfoList = relClassInfo.getAllowedClassInfo();
        if(NullUtil.isNone(allowedClassInfoList)) throw new FoundationException("Not Support");
        
        if(Bit.isInclude(flags, OmcSystemConstants.OBJROOT_FLAG_RO_B2B)){
            checkAllowedClassSub(allowedClassInfoList,OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM,OmcSystemConstants.FLAG_ALLOWEDRELATION_BO,fromClassInfo.getObid());
            checkAllowedClassSub(allowedClassInfoList,OmcSystemConstants.FLAG_ALLOWEDRELATION_TO,OmcSystemConstants.FLAG_ALLOWEDRELATION_BO,toClassInfo.getObid());
        }else if(Bit.isInclude(flags, OmcSystemConstants.OBJROOT_FLAG_RO_R2B)){
            checkAllowedClassSub(allowedClassInfoList,OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM,OmcSystemConstants.FLAG_ALLOWEDRELATION_RO,fromClassInfo.getObid());
            checkAllowedClassSub(allowedClassInfoList,OmcSystemConstants.FLAG_ALLOWEDRELATION_TO,OmcSystemConstants.FLAG_ALLOWEDRELATION_BO,toClassInfo.getObid());
        }else if(Bit.isInclude(flags, OmcSystemConstants.OBJROOT_FLAG_RO_B2R)){
            checkAllowedClassSub(allowedClassInfoList,OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM,OmcSystemConstants.FLAG_ALLOWEDRELATION_BO,fromClassInfo.getObid());
            checkAllowedClassSub(allowedClassInfoList,OmcSystemConstants.FLAG_ALLOWEDRELATION_TO,OmcSystemConstants.FLAG_ALLOWEDRELATION_RO,toClassInfo.getObid());
        }else if(Bit.isInclude(flags, OmcSystemConstants.OBJROOT_FLAG_RO_R2R)){
            checkAllowedClassSub(allowedClassInfoList,OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM,OmcSystemConstants.FLAG_ALLOWEDRELATION_RO,fromClassInfo.getObid());
            checkAllowedClassSub(allowedClassInfoList,OmcSystemConstants.FLAG_ALLOWEDRELATION_TO,OmcSystemConstants.FLAG_ALLOWEDRELATION_RO,toClassInfo.getObid());
        }else{
            throw new FoundationException("omc.error.validate.invalidRelationFlags");
        }
    }
    public static void checkValidationFromToType(String relationship,String fromClass,String toClass, long flags){
        ValidateRelationRuleRequestInfo searchInfo = new ValidateRelationRuleRequestInfo(relationship,fromClass, toClass, flags);
        checkValidationFromToType(searchInfo);
    }
    private static void checkAllowedClassSub(List<AllowedClassInfo> allowedClassInfoList,long fromTo, long boRo, String classObid){
        boolean isOk = false;
        for(AllowedClassInfo allowedClassInfo : allowedClassInfoList){
            if(Bit.isInclude(allowedClassInfo.getFlags(), fromTo) && (Bit.isInclude(allowedClassInfo.getFlags(), boRo)) &&(allowedClassInfo.getClassObid().equals(classObid) || allowedClassInfo.getClassObid().equals("All")  )){
                isOk = true;break;
            }
        }
        if(!isOk) throw new FoundationException("omc.error.validate.invalidRelationFlags");
    }
    public static void checkRelationCardinality(BusinessRelationObjectVO targetVO, ClassInfo classInfo){
        ValidateRelationRuleRequestInfo searchInfo = new ValidateRelationRuleRequestInfo(targetVO.getClassName(),
                targetVO.getFromObid(), targetVO.getFromClass(), targetVO.getToObid(), targetVO.getToClass(), 0);
        searchInfo.setTargetDbmsTable(classInfo.getDbmsTable());
        checkRelationCardinality(searchInfo, classInfo);
    }
    
    public static void checkRelationCardinality(ValidateRelationRuleRequestInfo searchInfo, ClassInfo classInfo){
        // cardinality From check
        if ((classInfo.getCardinalityFrom() & OmcSystemConstants.RELATION_CARDINALITY_Many) == OmcSystemConstants.RELATION_CARDINALITY_Many) {
            ;
        } else if ((classInfo.getCardinalityFrom() & OmcSystemConstants.RELATION_CARDINALITY_One) == OmcSystemConstants.RELATION_CARDINALITY_One) {
            searchInfo.setStrFromTo(GlobalConstants.FLAG_TYPE_FROM);
            int count = getInstance().foundationDao.select("Relation.checkCardinality", searchInfo);
            if (count > 0) {
                throw new FoundationException("omc.error.validate.cardinality.from");
            }
        } else {
            ;
        }
        // cardinality To check
        if ((classInfo.getCardinalityTo() & OmcSystemConstants.RELATION_CARDINALITY_Many) == OmcSystemConstants.RELATION_CARDINALITY_Many) {
            ;
        } else if ((classInfo.getCardinalityTo() & OmcSystemConstants.RELATION_CARDINALITY_One) == OmcSystemConstants.RELATION_CARDINALITY_One) {
            searchInfo.setStrFromTo(GlobalConstants.FLAG_TYPE_TO);
            int count = getInstance().foundationDao.select("Relation.checkCardinality", searchInfo);
            if (count > 0) {
                throw new FoundationException("omc.error.validate.cardinality.to");
            }
        } else {
            ;
        }
    }
    public static boolean checkAllowedLifeCycle(String className, String lifecycle){
        LifeCycleInfo lifeCycleInfo = getInstance().lifeCycleService.getLifeCycleInfo(lifecycle);
        List<String> classList = lifeCycleInfo.getClassList();
        for(String strClass : classList){
            if(BaseFoundationUtil.isKindOf(className, strClass)) return true; 
        }
        return false;
    }
    public static boolean checkLifeCycleAndState(String lifecycle, String states){
        
        LifeCycleInfo lifeCycleInfo = getInstance().lifeCycleService.getLifeCycleInfo(lifecycle);
        List<StateInfo> stateList = lifeCycleInfo.getStateList();
        for(StateInfo state : stateList){
            if(state.getStateName().equals(states)) return true;
        }
        throw new FoundationException("omc.error.validate.instantiable", new Object[] { lifecycle + "/" + states });
    }
    public static boolean checkInstantiable(String className){
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
        if (!NullUtil.isNull(classInfo)) {
            if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Business)){
                if ((classInfo.getClassInfoFlags() & OmcSystemConstants.CLASSINFO_FLAG_Instantiable) != OmcSystemConstants.CLASSINFO_FLAG_Instantiable) {
                    throw new FoundationException("omc.error.validate.instantiable", new Object[] { className });
                }
            } else {
                if ((classInfo.getClassInfoFlags() & OmcSystemConstants.CLASSINFO_FLAG_Relation) != OmcSystemConstants.CLASSINFO_FLAG_Relation) {
                    throw new FoundationException("omc.error.validate.instantiable", new Object[] { className });
                }
            }
        } else {
            throw new FoundationException("omc.error.classInfo.nodata");
        }
        return true;
    }

    public static boolean checkAllowedDynamicAttrGrp(String dynamicAttributeGroup, String className){
        VariableRequestInfo requestInfo = new VariableRequestInfo();
        requestInfo.setClassName(EtcUtil.convertNullToString(className));
        requestInfo.setDynamicAttributeGroup(EtcUtil.convertNullToString(dynamicAttributeGroup));

        String result = getInstance().foundationDao.select("DynamicAttribute.checkAllowedDynamicAttrGrp", requestInfo);
        if (!result.startsWith("TRUE")) {
            throw new FoundationException("omc.error.validate.dynamicAttrGroup", new Object[] { dynamicAttributeGroup,
                    className });
        }
        return true;
    }
    public static boolean checkUserId(String userId){
        int checkUserCnt = getInstance().foundationDao.select("Com.Login.checkUserId", userId);
        if (checkUserCnt > 0) {
            return true;
        } else {
            return false;
        }
    }
}
