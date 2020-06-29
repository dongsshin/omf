/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessRelationObject.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 24. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.dom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.common.model.request.ValidateRelationRuleRequestInfo;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.foundation.CommonServiceUtil;
import com.rap.omc.util.foundation.FoundationValidationUtil;

/**
 * <pre>
 * Class : BusinessRelationObject
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class BusinessRelationObject extends ObjectRoot {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessRelationObject.class);

    public BusinessRelationObject(BusinessRelationObjectVO vo) {
        super(vo);
    }

    public BusinessRelationObject(String obid) {
        super(obid);
    }
    public BusinessRelationObject(String obid, boolean withOutData) {
        super(obid,withOutData);
    }
    @Override
    public String toString(){
        return "BusinessRelationObject [toString()=" + super.toString() + "]";
    }

    @Override
    public BusinessRelationObjectVO getVo(){
        return (BusinessRelationObjectVO)super.getVo();
    }

    @Override
    public void initialize(){
        super.initialize();
        initializeBusinessRelationObject();
    }

    public void initializeBusinessRelationObject(){
        LOGGER.debug("initVO() from BusinessRelationObject");

        getVo().setFlags(getVo().getFlags() | OmcSystemConstants.OBJROOT_FLAG_RO);
    }

    private long getRelationType(ObjectRootVO fromObject, ObjectRootVO toObject){
        long relationType = 0L;
        if (fromObject instanceof BusinessObjectRootVO && toObject instanceof BusinessObjectRootVO) {
            relationType = OmcSystemConstants.OBJROOT_FLAG_RO_B2B;
        } else if (fromObject instanceof BusinessObjectRootVO && toObject instanceof BusinessRelationObjectVO) {
            relationType = OmcSystemConstants.OBJROOT_FLAG_RO_B2R;
        } else if (fromObject instanceof BusinessRelationObjectVO && toObject instanceof BusinessObjectRootVO) {
            relationType = OmcSystemConstants.OBJROOT_FLAG_RO_R2B;
        } else {
            relationType = OmcSystemConstants.OBJROOT_FLAG_RO_R2R;
        }
        return relationType;
    }
    
    /**
     * Relation Object를 Find함
     *
     * @param className Relation Class Name
     * @param fromClass From Class Name
     * @param fromObid From Object ID
     * @param toClass To Class Name
     * @param toObid To Object ID
     * @return
     */
    public static final <T> List<T> findRelationObjects(String className, String fromClass, String fromObid,
            String toClass, String toObid){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern  = new StringBuffer();
        StringBuffer paramPattern  = new StringBuffer();
        if(!StrUtil.isEmpty(fromClass) && !GlobalConstants.FLAG_TYPE_ALL.equals(fromClass)){
            List<String> childList = ClassInfoUtil.getInstantiableChildList(fromClass);
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[fromClass]",GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertList2Str(childList));
        }
        if(!StrUtil.isEmpty(toClass) && !GlobalConstants.FLAG_TYPE_ALL.equals(toClass)){
            List<String> childList = ClassInfoUtil.getInstantiableChildList(toClass);
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[toClass]",GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertList2Str(childList));
        }
        if(!StrUtil.isEmpty(fromObid)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[fromObid]",GlobalConstants.OQL_OPERATOR_IN, fromObid);
        if(!StrUtil.isEmpty(toObid)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[toObid]",GlobalConstants.OQL_OPERATOR_IN, toObid);
        List<T> result = ObjectRoot.findObjects(className, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, selectPattern.toString(), wherePattern.toString(), paramPattern.toString(), false, 0);
        return result;
    }
    
    
    protected void validateForCreate(ObjectRootVO fromObject, ObjectRootVO toObject, Map<String,Object> map){
        LOGGER.debug(">> 1. validateForCreate -- {} & {}", fromObject.getObid(), toObject.getObid());
        
        initialize();
        
        FoundationValidationUtil.checkInstantiable(getVo().getClassName());
        
        long relationType = getRelationType(fromObject, toObject);
        ValidateRelationRuleRequestInfo searchInfo = new ValidateRelationRuleRequestInfo(getVo().getClassName(),
                fromObject.getObid(), fromObject.getClassName(), toObject.getObid(), toObject.getClassName(),
                relationType);
        FoundationValidationUtil.checkRelation(searchInfo);
    }

    protected void preProcessForCreate(ObjectRootVO fromObj, ObjectRootVO toObj, Map<String,Object> map){
        LOGGER.debug(">> 2. preProcessForCreate -- {}", getVo().getObid());

        BusinessRelationObjectVO newVO = getVo();
        
        long relationType = getRelationType(fromObj, toObj);
        newVO.setFlags(newVO.getFlags() | relationType);
        newVO.setFromClass(fromObj.getClassName());
        newVO.setFromObid(fromObj.getObid());
        newVO.setToClass(toObj.getClassName());
        newVO.setToObid(toObj.getObid());
        LOGGER.debug(">> 2. init member variables {}", getVo());
    }
    protected void postProcessForCreate(ObjectRootVO fromObj, ObjectRootVO toObj, Map<String,Object> map){
        LOGGER.debug(">> 4. postProcessForCreate -- {}", getVo().getObid());
    }
    
    /**
     * Relation을 생성한다.
     * (VO에 설정된 fromObid, toObid를 기준으로 relation을 생성하며, vo에 담긴 값을 사용하여 Realtion의 attribute를 설정한다.)
     * 
     * @see ObjectRoot#createObject(Map)
     */
    @Override
    public final void createObject(Map<String,Object> map){
        BusinessRelationObjectVO newVO = getVo();
        if (NullUtil.isNone(newVO.getFromObid())) { throw new FoundationException("api.object.error.relation.fromObid"); }
        if (NullUtil.isNone(newVO.getToObid())) { throw new FoundationException("api.object.error.relation.toObid"); }
        this.createObject(newVO.getFromObid(), newVO.getToObid(),map);
    }

    /**
     * Relation을 생성한다.
     * (전달인자로 받은 fromObid, toObid를 기준으로 relation을 생성하며, vo에 담긴 값을 사용하여 Realtion의 attribute를 설정한다.)
     *
     * @param fromObid from Object의 obid
     * @param toObid to Object의 obid
     * @see BusinessRelationObject#createObject(ObjectRootVO, ObjectRootVO, Map)
     */
    public final void createObject(String fromObid, String toObid,Map<String,Object> map){
        ObjectRootVO fromObject = CommonServiceUtil.getObject(fromObid);
        ObjectRootVO toObject = CommonServiceUtil.getObject(toObid);
        this.createObject(fromObject, toObject,map);
    }
    /**
     * Relation을 생성한다.
     *
     * @param fromObj From Object
     * @param toObj To Object
     * @see BusinessRelationObject#createObject(ObjectRootVO, ObjectRootVO, Map)
     */
    public final void createObject(ObjectRootVO fromObj, ObjectRootVO toObj){
        createObject(fromObj,toObj,new HashMap<String,Object>());
    }
    /**
     * Relation을 생성한다.
     * (전달인자로 받은 fromObj, toObj를 기준으로 relation을 생성하며, vo에 담긴 값을 사용하여 Realtion의 attribute를 설정한다.)
     *
     * @param fromObj from Object
     * @param toObj to Object
     */
    public final void createObject(ObjectRootVO fromObj, ObjectRootVO toObj, Map<String,Object> map){
        LOGGER.debug("<< createObject() from {} --> {}", this.getClass(), getVo().getObid());
        validateForCreate(fromObj, toObj, map);
        preProcessForCreate(fromObj, toObj,map);
        LOGGER.debug(">> 3. createRelation -- {}", getVo().getObid());
        super.create();
        postProcessForCreate(fromObj, toObj,map);
    }
    
    public final void setFromDefloat(){
        this.preProcessForDefloat(getVo().getFlags() | OmcSystemConstants.OBJROOT_FLAG_RO_FromDefloat);
        defloat();
    }
    
    public final void setDisableFromDefloat(){
        this.preProcessForDefloat(getVo().getFlags() & (~OmcSystemConstants.OBJROOT_FLAG_RO_FromDefloat));
        defloat();
    }
    
    public final void setToDefloat(){
        this.preProcessForDefloat(getVo().getFlags() | OmcSystemConstants.OBJROOT_FLAG_RO_ToDefloat);
        defloat();
    }
    
    public final void setDisableToDefloat(){
        this.preProcessForDefloat(getVo().getFlags() & (~OmcSystemConstants.OBJROOT_FLAG_RO_ToDefloat));
        defloat();
    }
    
    /**
    *
    * @param l
    */
    private void preProcessForDefloat(long toFlag){
       // TODO Auto-generated method stub
        BusinessRelationObjectVO vo = getVo();
        vo.setFlags(toFlag);
    }
    
    private void defloat(){
        LOGGER.debug("<< defloat() from {} --> {}", this.getClass(), getVo().getObid());
        CommonServiceUtil.changeObjectWithKeyTableForDefloat(getVo());
    }
    /**
     * Relation의 From Object를 신규 Object로 치환한다.
     *
     * @param toObjectVO
     * @see BusinessRelationObject#changeFromObject(ObjectRootVO, Map)
     */
    public final void changeFromObject(ObjectRootVO toObjectVO){
        LOGGER.debug("<< changeFromObject() from {} --> {}", this.getClass(), getVo().getObid());
        Map<String,Object> map = new HashMap<String,Object>();
        validateForChangeFromObject(toObjectVO,map);
        preProcessForChangeFromObject(toObjectVO,map);
        changeFromToObjectCore(toObjectVO,map,true);
        postProcessForChangeFromObject(toObjectVO,map);
    }
    /**
     * Relation의 From Object를 신규 Object로 치환한다.
     *
     * @param toObjectVO 치환될 Object
     * @param map Parameter
     */
    public final void changeFromObject(ObjectRootVO toObjectVO,Map<String,Object> map){
        LOGGER.debug("<< changeFromObject() from {} --> {}", this.getClass(), getVo().getObid());
        validateForChangeFromObject(toObjectVO,map);
        preProcessForChangeFromObject(toObjectVO,map);
        changeFromToObjectCore(toObjectVO,map,true);
        postProcessForChangeFromObject(toObjectVO,map);
    }
    /**
     * Relation의 To Object를 신규 Object로 치환한다.
     *
     * @param toObjectVO 치환될 Object
     * @see BusinessRelationObject#changeToObject(ObjectRootVO, Map)
     */
    public final void changeToObject(ObjectRootVO toObjectVO){
        LOGGER.debug("<< changeFromObject() from {} --> {}", this.getClass(), getVo().getObid());
        Map<String,Object> map = new HashMap<String,Object>();
        validateForChangeToObject(toObjectVO,map);
        preProcessForChangeToObject(toObjectVO,map);
        changeFromToObjectCore(toObjectVO,map,false);
        postProcessForChangeToObject(toObjectVO,map);
    }
    /**
     * Relation의 To Object를 신규 Object로 치환한다.
     *
     * @param toObjectVO 치환될 Object
     * @param map Parameter
     */
    public final void changeToObject(ObjectRootVO toObjectVO,Map<String,Object> map){
        LOGGER.debug("<< changeFromObject() from {} --> {}", this.getClass(), getVo().getObid());
        validateForChangeToObject(toObjectVO,map);
        preProcessForChangeToObject(toObjectVO,map);
        changeFromToObjectCore(toObjectVO,map,false);
        postProcessForChangeToObject(toObjectVO,map);
    }
    private void changeFromToObjectCore(ObjectRootVO toObjectVO, Map<String,Object> map, boolean isFrom){
        BusinessRelationObjectVO input = this.getVo();
        if(isFrom){
            if(!toObjectVO.getClassName().equals(input.getFromClass())) throw new FoundationException("[Foundation]Class is not same.");
            input.setFromClass(toObjectVO.getClassName());
            input.setFromObid(toObjectVO.getObid());
        }else{
            if(!toObjectVO.getClassName().equals(input.getToClass())) throw new FoundationException("[Foundation]Class is not same.");
            input.setToClass(toObjectVO.getClassName());
            input.setToObid(toObjectVO.getObid());
        }
        ObjectRoot fromObject = new ObjectRoot(input.getFromObid());
        ObjectRoot toObject = new ObjectRoot(input.getToObid());
        long relationType = getRelationType(fromObject.getVo(), toObject.getVo());
        FoundationValidationUtil.checkInstantiable(getVo().getClassName());
        ValidateRelationRuleRequestInfo searchInfo = new ValidateRelationRuleRequestInfo(getVo().getClassName(),
                input.getFromObid(), input.getFromClass(), input.getToObid(), input.getToClass(),
                relationType);
        FoundationValidationUtil.checkRelation(searchInfo);
        CommonServiceUtil.changeRealtionObject(input);
    }
    
    protected void validateForChangeFromObject(ObjectRootVO toObjectVO,Map<String,Object> map){
        LOGGER.debug(">> 1. validateForChangeFromObject -- {}", getVo().getObid());
    }
    protected void preProcessForChangeFromObject(ObjectRootVO toObjectVO,Map<String,Object> map){
        LOGGER.debug(">> 2. preProcessForChangeFromObject -- {}", getVo().getObid());
    }
    protected void postProcessForChangeFromObject(ObjectRootVO toObjectVO,Map<String,Object> map){
        LOGGER.debug(">> 3. postProcessForChangeFromObject -- {}", getVo().getObid());
    }
    
    protected void validateForChangeToObject(ObjectRootVO toObjectVO,Map<String,Object> map){
        LOGGER.debug(">> 1. validateForChangeTobject -- {}", getVo().getObid());
    }
    protected void preProcessForChangeToObject(ObjectRootVO toObjectVO,Map<String,Object> map){
        LOGGER.debug(">> 2. preProcessForChangeToObject -- {}", getVo().getObid());
    }
    protected void postProcessForChangeToObject(ObjectRootVO toObjectVO,Map<String,Object> map){
        LOGGER.debug(">> 3. postProcessForChangeToObject -- {}", getVo().getObid());
    }
    /**
     * 
     *
     * @return
     */
    public final String getFromClass(){
        return this.getVo().getFromClass();
    }
    /**
     * 
     *
     * @return
     */
    public final String getFromObid(){
        return this.getVo().getFromObid();
    }
    /**
     * 
     *
     * @return
     */
    public final String getToClass(){
        return this.getVo().getToClass();
    }
    /**
     * 
     *
     * @return
     */
    public final String getToObid(){
        return this.getVo().getToObid();
    }
}
