/**
 * ===========================================
\ * System Name : LGE GPDM Project
 * Program ID : ObjectRoot.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 5. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.dom;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.oql.OmcOQLCoreFindObjectsAPI;
import com.rap.omc.api.oql.OmcOQLCoreGetRelatedObjectsAPI;
import com.rap.omc.api.oql.OmcOQLCoreGetRelationShipAPI;
import com.rap.omc.api.oql.OmcOQLCoreSearchAPI;
import com.rap.omc.api.oql.model.OmcGroupByParamVO;
import com.rap.omc.api.oql.model.OmcOQLApiRelatedLogVO;
import com.rap.omc.api.oql.model.OmcOQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcSortUtil;
import com.rap.omc.api.oql.utility.OmcSortUtil.OmcOQLComparator;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.EtcUtil;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.api.util.general.ObjectChangeLogManagement;
import com.rap.omc.api.util.general.RelationShip;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.RelationTableInfo;
import com.rap.omc.foundation.common.model.KeyInfo;
import com.rap.omc.foundation.model.VariableAttribute;
import com.rap.omc.foundation.system.model.ChangeLogVO;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.foundation.CommonServiceUtil;
import com.rap.omc.util.foundation.FoundationValidationUtil;

/**
 * 
 * <pre>
 * Class : ObjectRoot
 * Description : Root Dom Object
 * </pre>
 * 
 * @author s_dongsshin
 */
/* ★★★★★Java Doc내용 절대 삭제하지 마세요★★★★★ */
public class ObjectRoot {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectRoot.class);

    private ObjectRootVO vo;

    public ObjectRoot() {
    }
    /**
     * Constructor
     * @param vo Dom Object에 대한 VO(DomUtil를 사용과 동일한 기능을 함.)
     * @see DomUtil#toDom(ObjectRootVO vo)
     */
    public ObjectRoot(ObjectRootVO vo) {
        this.vo = vo;
    }
    /**
     * <pre>
     * Constructor
     * 
     * Object를 ID를 인자로 한 생성자. Database의 해당 Object에 대한 정보를 가지고 실제 Class를 Instance화 한다.
     * Space 혹은 Null값은 에러발생. 해당 Object ID에 대한 Object가 Database에 없으면 에러 발생.
     * </pre>
     * @param obid Object ID
     * @see ObjectRoot#ObjectRoot(String obid, boolean withOutData)
     */
    public ObjectRoot(String obid) {
        if (NullUtil.isNone(obid)) {
            throw new FoundationException("api.object.warn.obid");
        }
        try {
            ObjectRootVO result = CommonServiceUtil.getObjectWithOutData(obid);
            if (NullUtil.isNull(result)) {
                throw new FoundationException("api.object.warn.obid");
            }
            this.vo = result;
        } catch (ClassCastException e) {
            throw new FoundationException("api.object.error.class",e);
        }
    }
    /**
     * <pre>
     * Constructor
     * </pre>
     * @param obid Object ID
     * @param withOutData Database 정보 Query시 Dfault정보에 대한 Desc정보를 포함할지 여부
     * UI상으로 Object를 가지고 오지 않을 것이면 false로 하는 것이 좋음(성능면에서)
     * @see ObjectRoot#ObjectRoot(String obid)
     */
    public ObjectRoot(String obid, boolean withOutData) {
        if (NullUtil.isNone(obid)) {
            throw new FoundationException("api.object.warn.obid");
        }
        try {
            ObjectRootVO result = null;
            if(withOutData){
                result = CommonServiceUtil.getObjectWithOutData(obid);
            }else{
                result = CommonServiceUtil.getObject(obid,withOutData);
            }
            if (NullUtil.isNull(result)) {
                throw new FoundationException("api.object.warn.obid");
            }
            this.vo = result;
        } catch (ClassCastException e) {
            throw new FoundationException("api.object.error.class",e);
        }
    }
    /**
     * 해당 Value Object를 Return함.
     *
     * @return VO를 Return함.
     */
    public ObjectRootVO getVo(){
        return vo;
    }
    public void setVo(ObjectRootVO vo){
        this.vo = vo;
    }
    /**
     * outData를 Return함
     *
     * @return outData를 Return함
     */
    public HashMap<String, Object> getOutData(){
        return vo.getOutData();
    }
    @Override
    public String toString(){
        return getVo().toString();// + ":" + ReflectionToStringBuilder.toString(this);
    }
    /**
     * <pre>
     * Object생성시 최기화 작업를 수행.
     * 초기하 작업이 필요한 내용이 있으면 해당 상속 받은 Class에서 Override해서 적용함.
     * VO의 Attribute에 대한 Defult값 Setting은 반드시 Schema정의서에 정의해서 사용해야 함.
     * </pre>
     */
    public void initialize(){
        initializeObjectRoot();
    }
    private void initializeObjectRoot(){
        getVo().setObid(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_OBID));
        if (NullUtil.isNone(getVo().getClassName())) {
            String tempClassName = getVo().getClass().toString();
            tempClassName = tempClassName.substring(tempClassName.lastIndexOf('.') + 1);
            if(tempClassName.endsWith("VO")) tempClassName = tempClassName.substring(0, tempClassName.length()-2);
            ClassInfo classInfo = ClassInfoUtil.getClassInfo(tempClassName);
            if(NullUtil.isNull(classInfo)) throw new FoundationException("Cannot find Class(" + tempClassName + ").");
            if ((classInfo.getClassInfoFlags() & OmcSystemConstants.CLASSINFO_FLAG_Instantiable) != OmcSystemConstants.CLASSINFO_FLAG_Instantiable) {
                throw new FoundationException("omc.error.validate.instantiable", new Object[] { tempClassName });
            }
            getVo().setClassName(tempClassName);
        }
        initVariable();
        getVo().setFlags(OmcSystemConstants.OBJROOT_FLAG_Default);
    }
    /**
     * <pre>
     * Object Root Default 값 Setting
     * 하위 Business Logic을 구현하는 곳에서는 사용해서는 안됨.
     * </pre>
     */
    protected void initVariable(){
        getVo().setLocker("1");
        getVo().setCheckouter("1");
        Date date = null;
        getVo().setCheckouted(date);
        getVo().setOwner("1");
    }
    
    /**
     * VO 정보를 기준으로 Object를 생성한다. (DB 변경작업)
     */
    public void createObject(){
        createObject(new HashMap<String,Object>());
    }
    /**
     * VO 정보를 기준으로 Object를 생성한다. (DB 변경작업)
     *
     * @param map
     */
    public void createObject(Map<String, Object> map){
        if (getVo() instanceof BusinessRelationObjectVO) {
            BusinessRelationObjectVO newVo = (BusinessRelationObjectVO)getVo();
            BusinessRelationObject boDom = (BusinessRelationObject)DomUtil.toDom(newVo);
            boDom.createObject(map);
        } else if (getVo() instanceof BusinessObjectRootVO) {
            BusinessObjectRootVO newVo = (BusinessObjectRootVO)getVo();
            BusinessObjectRoot boDom = (BusinessObjectRoot)DomUtil.toDom(newVo);
            boDom.createObject(map);
        } else {
            throw new FoundationException("omc.error.validate.dummy", getVo().getClassName());
        }
    }
    /**
     * 일괄 생성하는 것으로 응용에서 적용되어진 validate,preProcess,postProcess가 적용되어짐.(Override되어진 Method수행되어짐)
     *
     * @param inputList Create할 VO List
     * @return 생성되어진 VO List
     * @see ObjectRoot#createObjectSetBatch(java.util.List)
     */
    static public final <T> List<T> createObjectSet(List<? extends ObjectRootVO> inputList){
        return createObjectSet(inputList,new HashMap<String,Object>());
    }
    /**
     * 일괄 생성하는 것으로 응용에서 적용되어진 validate,preProcess,postProcess가 적용되어짐.(Override되어진 Method수행되어짐)
     *
     * @param inputList Create할 VO List
     * @param map createObject에 대한 Parameter Map
     * @return 생성되어진 VO List
     * @see ObjectRoot#createObjectSetBatch(java.util.List)
     */
    @SuppressWarnings("unchecked")
    static public final <T> List<T> createObjectSet(List<? extends ObjectRootVO> inputList, HashMap<String,Object> map){
        List<T> result = new ArrayList<T>();
        if(!NullUtil.isNone(inputList)){
            for(ObjectRootVO vo : inputList){
                HashMap<String,Object> copiedMap = (HashMap<String,Object>)map.clone();
                ObjectRoot dom = DomUtil.toDom(vo);
                dom.createObject(copiedMap);
                result.add((T)dom.getVo());
            }
        }
        return result;
    }
    /**
     * <pre>
     * Object를 일괄 생성할 때 사용하는 것으로 각 Class에 대한 개별적으로 구현되어진 validate,preProcess,postProcess를 수행되어지지 않으므로
     * 생성되어진 Object에 대해서는 Business Logic에 맞게 되는지는 응용 Application에서 개별적으로 보장되게 구현되어져야 한다.
     * inputList에는 Relation/BusinessObjectMaster/BusinessObject를 같이 List화 해서 Call할 수 있음.
     * 생성되어진 Object를 Return받는 방법은 return Comment참조. 
     *  ★★★ Example ★★★
     * {@code
     *  relatedObjectList = ObjectRoot.getRelatedObjectSet(relatedObjectList, ApplicationSchemaConstants.RELCLASS_HASJOBACTIVITYTEMPLATE, ApplicationSchemaConstants.BIZCLASS_JOBACTIVITYTEMPLATE,GlobalConstants.FLAG_TYPE_TO, true, true);
     *  
     *  List<BusinessObjectRootVO> originalObjectList = new ArrayList<BusinessObjectRootVO>();
     *  for(BusinessObjectRootVO vo: relatedObjectList){
     *      BusinessObjectRootVO copiedVo = (BusinessObjectRootVO)DomUtil.copy(vo);
     *      originalObjectList.add(copiedVo);
     *  }
     *  convertNewWBSItemTemplatesList(relatedObjectList);
     *  Map<String,Object> createObjectsMap = ObjectRoot.createObjectSetBatch(relatedObjectList);
     *  
     *  List<BusinessObjectVO>         bizObjList       = (List<BusinessObjectVO>)createObjectsMap.get(GlobalConstants.OBJECT_CREATE_SET_BIZ);
     *  List<BusinessObjectMasterVO>   bizMasterObjList = (List<BusinessObjectMasterVO>)createObjectsMap.get(GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR);
     *  List<BusinessRelationObjectVO> relObjList       = (List<BusinessRelationObjectVO>)createObjectsMap.get(GlobalConstants.OBJECT_CREATE_SET_REL);
     * 
     * }
     * </pre>
     * @param inputList 생성할 Object List
     * 
     * @return {@code returnMap.get(GlobalConstants.OBJECT_CREATE_SET_BIZ): 생성되어전 Business Object
     *         returnMap.get(GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR)  : 생성되어전 Business Object Mater
     *         returnMap.get(GlobalConstants.OBJECT_CREATE_SET_REL)       : 생성되어전 Relation Object}
     * 
     * @see ObjectRoot#createObjectSet(java.util.List)
     */
    static public final Map<String,Object> createObjectSetBatch(List<? extends ObjectRootVO> inputList){
        return(CommonServiceUtil.createObjectSet(inputList));
    }
    /**
     * VO 정보를 기준으로 Object를 변경한다. (DB 변경작업)
     */
    public final void modifyObject(){
        modifyObject(new HashMap<String,Object>());
    }
    /**
     * VO 정보를 기준으로 Object를 변경한다. (DB 변경작업)
     *
     * @param map
     */
    public final void modifyObject(Map<String, Object> map){
        ObjectRoot tempDom = DomUtil.toDom(this.getVo());
        tempDom.modifyObjectSub(map);
    }
    /**
     * 일괄 수정하는 것으로 응용에서 적용되어진 validate,preProcess,postProcess가 적용되어짐.(Override되어진 Method수행되어짐)
     *
     * @param inputList
     * @see ObjectRoot#createObjectSetBatch(java.util.List)
     * 
     */
    static public final void modyfyObjectSet(List<? extends ObjectRootVO> inputList){
        for(ObjectRootVO vo : inputList){
            ObjectRoot dom = new ObjectRoot(vo);
            dom.modifyObject();
        }
    }
    /**
     * 일괄 수정하는 것으로 응용에서 적용되어진 validate,preProcess,postProcess가 적용되어짐.(Override되어진 Method수행되어짐)
     *
     * @param inputList 수정되어질 VO List
     * @param map modifyObject의 Parameter
     * @see ObjectRoot#createObjectSetBatch(java.util.List)
     * 
     */
    @SuppressWarnings("unchecked")
    static public final void modyfyObjectSet(List<? extends ObjectRootVO> inputList, HashMap<String,Object> map){
        if(!NullUtil.isNone(inputList)){
            for(ObjectRootVO vo : inputList){
                HashMap<String,Object> copiedMap = (HashMap<String,Object>)map.clone();
                ObjectRoot dom = DomUtil.toDom(vo);
                dom.modifyObject(copiedMap);
            }
        }
    }
    /**
     * <pre>
     * Object를 수정되어질 때 사용하는 것으로 각 Class에 대한 개별적으로 구현되어진 
     * validate,preProcess,postProcess를 수행되어지지 않으므로 수정에 의해 Business 
     * Logic에 맞게 되는지는 응용 Application에서 개별적으로 보장되게 구현되어져야 한다.
     * </pre>
     * @param inputList 수정되어진 VO List
     * @param attributes 수정되어질 Attribute
     * @see ObjectRoot#modyfyObjectSet(java.util.List)
     */
    static public final void modifyObjectSetBatch(List<? extends ObjectRootVO> inputList, Set<String> attributes){
        if(NullUtil.isNone(inputList)) return;
        if(NullUtil.isNone(attributes)) throw new FoundationException("[Foundation]Attribute cannot be empty.");
        validateForModifyObjectSetBatch(attributes);
        CommonServiceUtil.modifyObjectBatch(inputList,attributes);
    }
    /**
     * <pre>
     * Object를 수정되어질 때 사용하는 것으로 각 Class에 대한 개별적으로 구현되어진 
     * validate,preProcess,postProcess를 수행되어지지 않으므로 수정에 의해 Business 
     * Logic에 맞게 되는지는 응용 Application에서 개별적으로 보장되게 구현되어져야 한다.
     * ★★★ Example ★★★
     * {@code
     * ObjectRoot.modifyObjectSetBatch(modifiedhasSpecInfoByPDRAll, "categoryCode,categoryOrderValue,item1stValue,item2ndValue,categoryName,workDelimiterCode,itemCode,itemTypeCode,itemOrderValue,valueCode");
     * }
     * 
     * </pre>
     * @param inputList 수정되어진 VO List
     * @param attributeStr 수정되어질 Attribute
     * @see ObjectRoot#modifyObjectSetBatch(List inputList, Set attributes)
     */
    static public final void modifyObjectSetBatch(List<? extends ObjectRootVO> inputList, String attributeStr){
        if(StrUtil.isEmpty(attributeStr)) throw new FoundationException("[Foundation]Attribute cannot be empty.");
        Set<String> attributes = StrUtil.convertArrayToSet(attributeStr.split(","));
        modifyObjectSetBatch(inputList,attributes);
    }
    /**
     * <pre>
     * Object를 수정되어질 때 사용하는 것으로 각 Class에 대한 개별적으로 구현되어진 
     * validate,preProcess,postProcess를 수행되어지지 않으므로 수정에 의해 Business 
     * Logic에 맞게 되는지는 응용 Application에서 개별적으로 보장되게 구현되어져야 한다.
     * 내부적으로는 modifyObjectSetBatch()을 이용함.
     * Data수정만 할 경우 사용할 수 있는 Method(validate,preProcess,postProcess수행되어질 
     * 필요가 없는 경우 혹은 특정 Attribute만 수정해야 하는 경우 사용함.)
     * </pre>
     * @param input 수정되어진 VO
     * @param attributes 수정되어질 Attribute
     */
    static public final void modifyObjectSetBatch(ObjectRootVO input, Set<String> attributes){
        validateForModifyObjectSetBatch(attributes);
        List<ObjectRootVO> inputList = new ArrayList<ObjectRootVO>();
        inputList.add(input);
        CommonServiceUtil.modifyObjectBatch(inputList,attributes);
    }
    /**
     * VO 정보를 기준으로 Object를 삭제한다. (DB 변경작업)
     */
    public final void deleteObject(){
        HashMap<String,Object> map = new HashMap<String,Object>();
        deleteObject(map);
    }
    /**
     * VO 정보를 기준으로 Object를 삭제한다. (DB 변경작업)
     *
     * @param map
     */
    public final void deleteObject(Map<String, Object> map){
        ObjectRoot tempDom = DomUtil.toDom(this.getVo());
        tempDom.deleteObjectSub(map);
    }
    /**
     * 일괄 삭제하는 것으로 응용에서 적용되어진 validate,preProcess,postProcess가 적용되어짐.(Override되어진 Method수행되어짐)
     *
     * @param inputList 삭제될 VO List
     * @see ObjectRoot#deleteObjectSetBatch(java.util.List)
     */
    static public final void deleteObjectSet(List<? extends ObjectRootVO> inputList){
        if(!NullUtil.isNone(inputList)){
            for(ObjectRootVO vo : inputList){
                ObjectRoot dom = DomUtil.toDom(vo);
                dom.deleteObject();
            }
        }
    }
    /**
     * <pre>
     * Object를 Delete할 때 사용하는 것으로 각 Class에 대한 개별적으로 구현되어진 
     * validate,preProcess,postProcess를 수행되어지지 않으므로 Delete 되어지므로 
     * Business Logic에 맞게 되는지는 응용 Application에서 개별적으로 보장되게 구현되어져야 한다.
     * 성능과 무관한경우에는 사용하지 말것. deleteObjectSet() 은 개별적인 deleteObject가 됨
     * (validate,preProcess,postProcess수행되어짐)
     * </pre>
     * @param inputList 삭제되어질 VO List
     * @see ObjectRoot#deleteObjectSet(java.util.List)
     */
    static public final void deleteObjectSetBatch(List<? extends ObjectRootVO> inputList){
        CommonServiceUtil.deleteObjectSet(inputList);
    }
    /**
     * Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param paramObj 연결하고자 하는 Object(Business/Relation Object)
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @param bFromTo 연결하고자 하는 방향 (From:true, To:false) (ex, true인 경우 ParamObj를 FromObj로 간주함)
     * @return 생성되어진 Relation Object VO
     */
    public BusinessRelationObjectVO connect(String className, ObjectRootVO paramObj, Map<String, Object> attributes,boolean bFromTo){
        return this.addRelatedObject(className, paramObj, attributes, bFromTo);
    }
    /**
     * 전달인자로 받는 paramObj를 From 방향으로 간주하여, Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param paramObj 연결하고자 하는 Object(Business/Relation Object)
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @return 생성되어진 Relation Object VO
     */
    public BusinessRelationObjectVO connectFrom(String className, ObjectRootVO paramObj, Map<String, Object> attributes){
        return this.addFromObject(className, paramObj, attributes);
    }
    /**
     * 전달인자로 받는 paramObj를 To 방향으로 간주하여, Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param paramObj 연결하고자 하는 Object(Business/Relation Object)
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @return 생성되어진 Relation Object VO
     */
    public BusinessRelationObjectVO connectTo(String className, ObjectRootVO paramObj, Map<String, Object> attributes){
        return this.addToObject(className, paramObj, attributes);
    }

    /**
     * Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param paramObj 연결하고자 하는 Object(Business/Relation Object)
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @param bFromTo 연결하고자 하는 방향 (From:true, To:false) (ex, true인 경우 ParamObj를 FromObj로 간주함)
     * @return 생성되어진 Relation Object VO
     */
    public BusinessRelationObjectVO addRelatedObject(String className, ObjectRootVO paramObj, Map<String, Object> attributes, boolean bFromTo){
        if (bFromTo) {
            return this.addFromObject(className, paramObj, attributes);
        }
        return this.addToObject(className, paramObj, attributes);
    }
    /**
     * 전달인자로 받는 paramObj를 From 방향으로 간주하여, Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObj 연결하고자 하는 Object(Business/Relation Object)
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @return 생성되어진 Relation Object VO
     */
    public BusinessRelationObjectVO addFromObject(String className, ObjectRootVO fromObj, Map<String, Object> attributes){
        return addFromObject(className, fromObj, attributes, new HashMap<String, Object>());
    }
    /**
     * 전달인자로 받는 paramObj를 From 방향으로 간주하여, Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObj 연결하고자 하는 Object(Business/Relation Object)
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @param map createObject에 대한 Parameter
     * @return 생성되어진 Relation Object VO
     */
    public BusinessRelationObjectVO addFromObject(String className, ObjectRootVO fromObj, Map<String, Object> attributes,Map<String, Object> map){
        return RelationShip.connect(className, fromObj.getObid(), getVo().getObid(), attributes,map);
    }
    /**
     * 전달인자로 받는 paramObj를 To 방향으로 간주하여, Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param toObj 연결하고자 하는 Object(Business/Relation Object)
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @return 생성되어진 Relation Object VO
     */
    public BusinessRelationObjectVO addToObject(String className, ObjectRootVO toObj, Map<String, Object> attributes){
        return addToObject(className, toObj, attributes,new HashMap<String, Object>());
    }
    /**
     * 전달인자로 받는 paramObj를 To 방향으로 간주하여, Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param toObj 연결하고자 하는 Object(Business/Relation Object)
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @param map createObject에 대한 Parameter
     * @return 생성되어진 Relation Object VO
     */
    public BusinessRelationObjectVO addToObject(String className, ObjectRootVO toObj, Map<String, Object> attributes,Map<String, Object> map){
        return RelationShip.connect(className, getVo().getObid(), toObj.getObid(), attributes,map);
    }
    /**
     * lock 상태인지 리턴한다.
     *
     * @return lock 상태(lock되어진 상태이면 true)
     */
    public final boolean isLocked(){
        if (NullUtil.isNone(getVo().getLocker()) || getVo().getLocker().equals("1")) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * checkout 상태인지 리턴한다.
     *
     * @return checkout 상태(checkOut되어진 상태이면 true)
     */
    public final boolean isCheckouted(){
        if (NullUtil.isNone(getVo().getCheckouter()) || getVo().getCheckouter().equals("1")) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Lock되어진 Object를 Unlock함
     */
    public final void unlock(){
        ObjectRootVO input = new ObjectRootVO();
        input.setObid(getVo().getObid());
        input.setLocker("1");
        input.setCheckouter(null);
        update(input);
        getVo().setLocker("1");
    }
    /**
     * Locker UserId를 Parameter로 받아 locker Update한다.
     *
     * @param userId
     */
    public final void lock(String userId){
        if (NullUtil.isNone(userId)) {
            throw new FoundationException("omc.error.validate.nullUserId", new Object[] { "locker" });
        } else {
            if (!userId.equals("1") && !FoundationValidationUtil.checkUserId(userId)) {
                throw new FoundationException("omc.error.validate.invalidUserId", new Object[] { "locker" });
            }
        }

        ObjectRootVO input = new ObjectRootVO();
        input.setObid(getVo().getObid());
        input.setLocker(userId);
        input.setCheckouter(null);
        update(input);

        // 현 VO에 값 변경
        getVo().setLocker(userId);
    }
    /**
     * checkouter를 '1'로 Update한다.
     */
    public final void checkIn(){
        ObjectRootVO input = new ObjectRootVO();
        input.setObid(getVo().getObid());
        input.setCheckouter("1");
        input.setLocker(null);
        update(input);

        // 현 VO에 값 변경
        getVo().setCheckouter("1");
        Date temp = null;
        getVo().setCheckouted(temp);
    }
    /**
     * UserId를 Parameter로 받아 checkouter 정보를 Update한다.
     *
     * @param userId
     */
    public final void checkOut(String userId){
        checkOut(userId, null);
    }
    /**
     * UserId를 Parameter로 받아 checkouter 정보를 Update한다.
     *
     * @param userId
     */
    public final void checkOut(String userId, String comments){
        if (NullUtil.isNone(userId)) {
            throw new FoundationException("omc.error.validate.nullUserId", new Object[] { "checkouter" });
        } else {
            if (!userId.equals("1") && !FoundationValidationUtil.checkUserId(userId)) {
                throw new FoundationException("omc.error.validate.invalidUserId", new Object[] { "checkouter" });
            }
        }
        ObjectRootVO input = new ObjectRootVO();
        input.setObid(getVo().getObid());
        input.setCheckouter(userId);
        if (!userId.equals("1")) {
            input.setCheckouted(new Date());
        }
        input.setLocker(null);
        update(input);
        // change log
        ObjectChangeLogManagement.createChangeLog(GlobalConstants.CHANGING_CATEGORY_API_CHECKOUT, getVo().getObid(),
                EtcUtil.getNames(getVo()), getVo().getClassName(), null, userId, comments, null, null, null);
        // 현 VO에 값 변경
        getVo().setCheckouter(userId);
        getVo().setCheckouted(input.getCheckouted());
    }
    /**
     * owner 정보를 변경한다. (DB 업데이트까지 실행됨)
     *
     * @param newOwner
     */
    public final void changeOwner(String newOwner){
        changeOwner(newOwner, null);
    }
    /**
     * owner 정보를 변경한다. (DB 업데이트까지 실행됨)
     *
     * @param newOwner
     */
    public final void changeOwner(String newOwner, String comments){
        LOGGER.debug(">> #. changeOwner({}) -- {}", newOwner, getVo().getObid());
        if (NullUtil.isNone(newOwner)) {
            throw new FoundationException("omc.error.validate.nullUserId", new Object[] { "newOwner" });
        } else {
            if (!newOwner.equals("1") && !FoundationValidationUtil.checkUserId(newOwner)) {
                throw new FoundationException("omc.error.validate.invalidUserId", new Object[] { "newOwner" });
            }
        }
        ObjectRootVO searchVO = getVo();
        if (!NullUtil.isNone(searchVO.getObid())) {
            ObjectRootVO inputVO = new ObjectRootVO();
            inputVO.setObid(searchVO.getObid());
            inputVO.setClassName(searchVO.getClassName());
            inputVO.setOwner(newOwner);
            change(inputVO, true);
            ObjectChangeLogManagement.createChangeLog(GlobalConstants.CHANGING_CATEGORY_API_CHAGNE_OWNER, getVo().getObid(),
                    EtcUtil.getNames(getVo()), getVo().getClassName(), null, getVo().getOwner(), newOwner, comments,
                    null, null);
            getVo().setOwner(newOwner);
        }
    }
    /**
     * <pre>
     * 각각의 VO의 조건을 가지고 Object를 찾을때 사용하는 것을 각 VO에 조건별 OR관계로 데이터를 찾음. 
     *
     *  ★★★ Example ★★★
     *  {@code 
     *  
     *  List<ProjectUserPlanMMVO> inpuLIstAll = new ArrayList<ProjectUserPlanMMVO>();
     *  inpuLIstAll.addAll(createList);
     *  inpuLIstAll.addAll(updateList);
     *  inpuLIstAll.addAll(deleteList);
     *  //inpuLIstAll의 Attribute fromObid,toObid,planMonth의 값을  가지는 Object를 Return함.
     *  List<ProjectUserPlanMMVO> dbListAll = ObjectRoot.findObjects(ApplicationSchemaConstants.RELCLASS_PROJECTUSERPLANMM, 
     *                                                               inpuLIstAll, 
     *                                                               "fromObid,toObid,planMonth");
     *  
     *  }
     *  
     *  </pre>
     * @param className Class Name
     * @param inputList 조건 VO리스트
     * @param attributeStr 조건에 사용되어지는 Attribute 리스트
     * @return VO List
     */
    static public final <T> List<T> findObjects(String className, List<? extends ObjectRootVO> inputList, String attributeStr){
        if(NullUtil.isNone(inputList)) return new ArrayList<T>();
        Set<String> attributes = StrUtil.convertArrayToSet(attributeStr.split(","));
        return(CommonServiceUtil.getSimpleObjects(className, inputList, attributes));
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @param objectLimit 검색 건 수 ("0"인 경우 전체 목록 검색)
     * @return VO List
     */
    public static final <T> List<T> findObjects(String className, String namePattern, String revisionPattern,
                                                String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
                                                String ownerPattern, String checkouterPattern, String lockerPattern, boolean firstOnly, boolean latestOnly,
                                                boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String wherePattern,
                                                String paramPattern, boolean expandType, int objectLimit){
        RowBounds paramRowBounds = null;
        return findObjectsCore(className, namePattern, revisionPattern,lifeCylePattern, statePattern, creatorPattern, modifierPattern,ownerPattern, checkouterPattern, lockerPattern, firstOnly, latestOnly,lockedOnly, checkoutedOnly, selectPattern, wherePattern,paramPattern, expandType, objectLimit,null,paramRowBounds);
    }
    private static final <T> List<T> findObjects(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
            String ownerPattern, String checkouterPattern, String lockerPattern, boolean firstOnly, boolean latestOnly,
            boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String wherePattern,
            String paramPattern, boolean expandType, int objectLimit,RowBounds paramRowBounds){
            return findObjectsCore(className, namePattern, revisionPattern,lifeCylePattern, statePattern, creatorPattern, modifierPattern,ownerPattern, checkouterPattern, lockerPattern, firstOnly, latestOnly,lockedOnly, checkoutedOnly, selectPattern, wherePattern,paramPattern, expandType, objectLimit,null,paramRowBounds);
    }
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className
     * @param wherePattern
     * @param paramPattern
     * @param groupByParamList
     * @return VO List
     */
    public static final <T> List<T> findObjectsGroupBy(String className,String wherePattern,String paramPattern, List<OmcGroupByParamVO> groupByParamList){
        RowBounds paramRowBounds = null;
        return findObjectsAdvancedSub(className, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false, false,false, false, "", wherePattern,paramPattern, true, 0,groupByParamList,paramRowBounds);
    }
    /**
     * 
     *
     * @param className
     * @param namePattern
     * @param revisionPattern
     * @param lifeCylePattern
     * @param statePattern
     * @param creatorPattern
     * @param modifierPattern
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param expandType
     * @param objectLimit
     * @param groupByParamList
     * @returnVO List
     */
    private static final <T> List<T> findObjectsAdvancedSub(String className, String namePattern, String revisionPattern,
                                                            String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
                                                            String ownerPattern, String checkouterPattern, String lockerPattern, boolean firstOnly, boolean latestOnly,
                                                            boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String wherePattern,
                                                            String paramPattern, boolean expandType, int objectLimit,
                                                            List<OmcGroupByParamVO> groupByParamList,
                                                            RowBounds paramRowBounds){
        return findObjectsCore(className, namePattern, revisionPattern,lifeCylePattern, statePattern, creatorPattern, modifierPattern,ownerPattern, checkouterPattern, lockerPattern, firstOnly, latestOnly,lockedOnly, checkoutedOnly, selectPattern, wherePattern,paramPattern, expandType, objectLimit,groupByParamList,paramRowBounds);
    }
    /**
     * 
     *
     * @param className
     * @param namePattern
     * @param revisionPattern
     * @param lifeCylePattern
     * @param statePattern
     * @param creatorPattern
     * @param modifierPattern
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param expandType
     * @param objectLimit
     * @return VO List
     */
    private static final <T> List<T> findObjectsCore(String className, String namePattern, String revisionPattern,
                                                     String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
                                                     String ownerPattern, String checkouterPattern, String lockerPattern, boolean firstOnly, boolean latestOnly,
                                                     boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String wherePattern,
                                                     String paramPattern, boolean expandType, int objectLimit,List<OmcGroupByParamVO> groupByParamList,RowBounds paramRowBounds){
        return OmcOQLCoreFindObjectsAPI._findObjects(className,namePattern,revisionPattern,lifeCylePattern,statePattern,creatorPattern,modifierPattern,ownerPattern,checkouterPattern,lockerPattern,firstOnly,latestOnly,lockedOnly,checkoutedOnly,selectPattern,wherePattern,paramPattern,groupByParamList,expandType,paramRowBounds,objectLimit,1,false);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @param objectLimit 검색 건 수 ("0"인 경우 전체 목록 검색)
     * @return VO List
     */
    public static final <T> List<T> findObjects(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
            String ownerPattern, String checkouterPattern, String lockerPattern, String selectPattern,
            String wherePattern, String paramPattern, boolean expandType, int objectLimit){
        return ObjectRoot.findObjects(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                creatorPattern, modifierPattern, ownerPattern, checkouterPattern, lockerPattern, false, false, false,
                false, selectPattern, wherePattern, paramPattern, expandType, objectLimit);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @param objectLimit 검색 건 수 ("0"인 경우 전체 목록 검색)
     * @return
     */
    public static final <T> List<T> findObjects(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, boolean firstOnly, boolean latestOnly, boolean lockedOnly,
            boolean checkoutedOnly, String selectPattern, String wherePattern, String paramPattern,
            boolean expandType, int objectLimit){
        return ObjectRoot.findObjects(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, firstOnly, latestOnly, lockedOnly,
                checkoutedOnly, selectPattern, wherePattern, paramPattern, expandType, objectLimit);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param expandType (true/false)
     * @param objectLimit 검색 건 수 ("0"인 경우 전체 목록 검색)
     * @return
     */
    public static final <T> List<T> findObjects(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, boolean expandType, int objectLimit){
        return ObjectRoot.findObjects(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false, false, false, false, "", "", "",
                expandType, objectLimit);
    }
    /**
     * Where Pattern 을 이용해서 Object를 검색한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className
     * @param wherePattern
     * @param paramPattern
     * @return
     */
    public static final <T> List<T> findObjects(String className, String wherePattern, String paramPattern){
        return ObjectRoot.findObjects(className, "", wherePattern, paramPattern);
    }
    /**
     * Where Pattern 및 Select Pattern 을 이용해서 Object를 검색한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @return
     */
    public static final <T> List<T> findObjects(String className, String selectPattern, String wherePattern, String paramPattern){
        return ObjectRoot.findObjects(className, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
                false, false, false, selectPattern, wherePattern, paramPattern, false, 0);
    }
    public static final <T> List<T> findObjectsUnlimited(String className, String selectPattern, String wherePattern, String paramPattern){
        RowBounds unlimitedRowBounds = getOmcUnlimitedRowBounds();
        return ObjectRoot.findObjects(className, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
                false, false, false, selectPattern, wherePattern, paramPattern, false, 0,unlimitedRowBounds);
    }
    private static RowBounds getOmcUnlimitedRowBounds(){
        return new RowBounds(OmcSystemConstants.NO_ROW_OFFSET,OmcSystemConstants.OMC_DBMS_UNLIIMITED_COUNT);
    }
        /*
    select case when a.phex_converting_flag = 'C' then rpad('public static final String ' || a.pnames,60,' ') || ' = "' || a.pconstant_values || '";'
                when a.phex_converting_flag = 'Y' then rpad('public static final long '   || a.pnames,60,' ') || ' = Bit.h2d("' || a.pconstant_values || '");'
                when a.phex_converting_flag = 'N' then rpad('public static final int '    || a.pnames,60,' ') || ' = ' || (a.pconstant_values + 0) || ';'
           end 
    from psysconstants a
    where a.pkinds = 'System'
    order by a.psequences
    */

    
    
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @return
     */
    public static final int getCountForFindObjectsCount(String className, String selectPattern, String wherePattern, String paramPattern){
        PagingEntity pagingEntity = new PagingEntity();
        pagingEntity.setRowSize(1);
        List<ObjectRootVO> result = ObjectRoot.findObjectPagingList(className, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
                false, false, false, selectPattern, wherePattern, paramPattern, true, pagingEntity);
        return ((PagingList<ObjectRootVO>)result).getRows();
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @param objectLimit 검색 건 수 ("0"인 경우 전체 목록 검색)
     * @return
     */
    public static final <T> List<T> findObjects(String className, String namePattern, String revisionPattern,
            String selectPattern, String wherePattern, String paramPattern, boolean expandType, int objectLimit){
        return ObjectRoot.findObjects(className, namePattern, revisionPattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
                false, false, false, selectPattern, wherePattern, paramPattern, expandType, objectLimit);
    }
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className
     * @param namePattern
     * @return
     */
    public static final <T> List<T> findObjects(String className, String namePattern){
        return ObjectRoot.findObjects(className, namePattern, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false, false, false, false, "", "", "",
                true, 0);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> List<T> findObjectPagingList(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
            String ownerPattern, String checkouterPattern, String lockerPattern, boolean firstOnly, boolean latestOnly,
            boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String wherePattern,
            String paramPattern, boolean expandType, PagingEntity pagingEntity){
        RowBounds paramRowBounds = null;
        List<T> resultList = OmcOQLCoreFindObjectsAPI._findObjectPagingList(className            ,
                                                                            namePattern          ,
                                                                            revisionPattern      ,
                                                                            lifeCylePattern      ,
                                                                            statePattern         ,
                                                                            creatorPattern       ,
                                                                            modifierPattern      ,
                                                                            ownerPattern         ,
                                                                            checkouterPattern    ,
                                                                            lockerPattern        ,
                                                                            firstOnly            ,
                                                                            latestOnly           ,
                                                                            lockedOnly           ,
                                                                            checkoutedOnly       ,
                                                                            selectPattern        ,
                                                                            wherePattern         ,
                                                                            paramPattern         ,
                                                                            expandType           ,
                                                                            pagingEntity         ,
                                                                            paramRowBounds       ,
                                                                            0                    ,
                                                                            1                    ,
                                                                            false);               
        return resultList;
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> List<T> findObjectPagingList(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
            String ownerPattern, String checkouterPattern, String lockerPattern, String selectPattern,
            String wherePattern, String paramPattern, boolean expandType, PagingEntity pagingEntity){
        return ObjectRoot.findObjectPagingList(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                creatorPattern, modifierPattern, ownerPattern, checkouterPattern, lockerPattern, false, false, false,
                false, selectPattern, wherePattern, paramPattern, expandType, pagingEntity);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @return
     */
    public static final <T> List<T> findObjectFirstObject(String className, String namePattern, String revisionPattern,
                                                          String selectPattern, String wherePattern, String paramPattern){
        PagingEntity pagingEntity = new PagingEntity();
        pagingEntity.setDefaultRowSize(1);
        pagingEntity.setPageSize(1);
        pagingEntity.setRowSize(1);
        pagingEntity.setTargetRow(1);
                
        return ObjectRoot.findObjectPagingList(className, namePattern, revisionPattern, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false, false, false,
                false, selectPattern, wherePattern, paramPattern, false, pagingEntity);
    }    
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className
     * @param namePattern
     * @param revisionPattern
     * @param lifeCylePattern
     * @param statePattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param expandType
     * @param pagingEntity
     * @return
     */
    public static final <T> List<T> findObjectPagingList(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, boolean firstOnly, boolean latestOnly, boolean lockedOnly,
            boolean checkoutedOnly, String selectPattern, String wherePattern, String paramPattern,
            boolean expandType, PagingEntity pagingEntity){
        return ObjectRoot.findObjectPagingList(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, firstOnly, latestOnly, lockedOnly,
                checkoutedOnly, selectPattern, wherePattern, paramPattern, expandType, pagingEntity);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> List<T> findObjectPagingList(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, boolean expandType, PagingEntity pagingEntity){
        return ObjectRoot.findObjectPagingList(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false, false, false, false, "", "", "",
                expandType, pagingEntity);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> List<T> findObjectPagingList(String className, String namePattern, String revisionPattern,
            String selectPattern, String wherePattern, String paramPattern, boolean expandType, PagingEntity pagingEntity){
        return ObjectRoot.findObjectPagingList(className, namePattern, revisionPattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
                false, false, false, selectPattern, wherePattern, paramPattern, expandType, pagingEntity);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T findObject(String className, String namePattern, String revisionPattern,
            boolean expandType){
        T searchObject = ObjectRoot.findObject(className, namePattern, revisionPattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
                false, false, false, "", "", "", expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T findObject(String className, String namePattern, boolean expandType){
        T searchObject = ObjectRoot.findObject(className, namePattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, false, false, false, false, "", "", "", expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T findObject(String className, String namePattern, String revisionPattern,
            String selectPattern, String wherePattern, String paramPattern, boolean expandType){
        T searchObject = ObjectRoot.findObject(className, namePattern, revisionPattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
                false, false, false, selectPattern, wherePattern, paramPattern, expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param latestOnly
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T findObject(String className, String namePattern, boolean latestOnly,
            String selectPattern, String wherePattern, String paramPattern, boolean expandType){
        T searchObject = ObjectRoot.findObject(className, namePattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, false, latestOnly, false, false, selectPattern, wherePattern,
                paramPattern, expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다. (firstOnly가 true)
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T findFirstObject(String className, String namePattern, boolean expandType){
        T searchObject = ObjectRoot.findObject(className, namePattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, true, false, false, false, "", "", "", expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다. (latestOnly가 true)
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T findLatestObject(String className, String namePattern, boolean expandType){
        T searchObject = ObjectRoot.findObject(className, namePattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, false, true, false, false, "", "", "", expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, ","로 연결하여 한 컬럼에 표시된다.
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern select 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return VO List
     */
    private static final <T> T findObject(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
            String ownerPattern, String checkouterPattern, String lockerPattern, boolean firstOnly, boolean latestOnly,
            boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String wherePattern,
            String paramPattern, boolean expandType){

        List<T> resultList = findObjects(className          ,//String               className            ,
                                         namePattern        ,//String               namePattern          ,
                                         revisionPattern    ,//String               revisionPattern      ,
                                         lifeCylePattern    ,//String               lifeCyclePattern     ,
                                         statePattern       ,//String               statePattern         ,
                                         creatorPattern     ,//String               creatorPattern       ,
                                         modifierPattern    ,//String               modifierPattern      ,
                                         ownerPattern       ,//String               ownerPattern         ,
                                         checkouterPattern  ,//String               checkouterPattern    ,
                                         lockerPattern      ,//String               lockerPattern        ,
                                         firstOnly          ,//boolean              firstOnly            ,
                                         latestOnly         ,//boolean              latestOnly           ,
                                         lockedOnly         ,//boolean              lockedOnly           ,
                                         checkoutedOnly     ,//boolean              checkoutedOnly       ,
                                         selectPattern      ,//String               patternSelectIn      ,
                                         wherePattern       ,//String               patternWhereIn       ,
                                         paramPattern   ,//String               patternParameterIn   ,
                                         expandType         ,//boolean              expandType           ,
                                         0);                 //int objectLimit
                        
        if (!NullUtil.isNone(resultList) && resultList.size() > 1) { throw new FoundationException("omc.error.query.toomanyrows"); }
        T result = null;
        if (!NullUtil.isNone(resultList)) {
            result = resultList.get(0);
        }
        return result;
    }
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To Object (BusinessObject or RelationObject) 정보 조회
     * (recursive call)
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     * @param relationPattern Relation 클래스 이름
     * @param filterPattern From/To 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromToDirection From/To 검색 방향
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param bInclude 기준 Object를 result에 포함시킬지 여부 (true/false)
     * @param bResultUnique 한 번 조회 시, 결과의 중복을 허용할지 여부 (true/false)
     * @param objectLimit 한 번 조회 시 검색 건 수 (0이면 전체)
     * @param findDepth 재귀 호출하여 연관된 Object를 찾을 때, recursive call count (0이면 전체)
     * @return 검색된 Object의 List
     */
    public <T> List<T> getRelatedObjects(String relationPattern, String filterPattern, String fromToDirection,String selectPattern, String wherePattern, String paramPattern, boolean bInclude,boolean bResultUnique, int objectLimit, int findDepth){
        List<ObjectRootVO> objVoList = new ArrayList<ObjectRootVO>();
        objVoList.add(getVo());
        return ObjectRoot.getRelatedObjectSet(objVoList,relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,"",false,0,bInclude,bResultUnique, objectLimit, findDepth);
    }
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To Object (BusinessObject or RelationObject) 정보 조회
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param findDepth
     * @return VO List
     */
    public <T> List<T> getRelatedObjects(String relationPattern, String filterPattern, String fromToDirection,String selectPattern, String wherePattern, String paramPattern, int findDepth){
        List<ObjectRootVO> objVoList = new ArrayList<ObjectRootVO>();
        objVoList.add(getVo());
        return ObjectRoot.getRelatedObjectSet(objVoList,relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,"",false,0,false,false, 0, findDepth);
    }
    public <T> List<T> getRelatedObjectsUnlimited(String relationPattern, String filterPattern, String fromToDirection,String selectPattern, String wherePattern, String paramPattern){
        RowBounds unlimitedRowBounds = getOmcUnlimitedRowBounds();
        List<ObjectRootVO> objVoList = new ArrayList<ObjectRootVO>();
        objVoList.add(getVo());
        return getRelatedObjectSetSub(objVoList,relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,"",false,0,false,false, 0,0,null,unlimitedRowBounds);
    }
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To Object (BusinessObject or RelationObject) 정보 조회
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @return VO List
     */
    public static <T> List<T> getRelatedObjectSet(List<? extends ObjectRootVO> objVoList,String relationPattern, String filterPattern, String fromToDirection,String selectPattern, String wherePattern, String paramPattern){
        return getRelatedObjectSet( objVoList,relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,"",false,0,false,false,0, 1);
    }
    /**
     * 
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @return
     */
    public static <T> List<T> getRelatedObjectSetUnlimited(List<? extends ObjectRootVO> objVoList,String relationPattern, String filterPattern, String fromToDirection,String selectPattern, String wherePattern, String paramPattern){
        RowBounds unlimitedRowBounds = getOmcUnlimitedRowBounds();
        List<T> result = new ArrayList<T>();
        if(NullUtil.isNone(objVoList)) return result;
        List<ObjectRootVO> newVoList = new ArrayList<ObjectRootVO>();
        if(objVoList.size() > OmcSystemConstants.OBJECT_SET_CMD_UNIT){
            int seq = 0;
            for(ObjectRootVO vo : objVoList){
                if(seq > 0 && seq%OmcSystemConstants.OBJECT_SET_CMD_UNIT == 0){
                    List<T> tempResult = getRelatedObjectSetSub( newVoList,relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,"",false,0,false,false,0, 1,null,unlimitedRowBounds);
                    if(!NullUtil.isNone(tempResult)) result.addAll(tempResult);
                    newVoList = new ArrayList<ObjectRootVO>();
                }
                newVoList.add(vo);
                seq++;
            }
            if(!NullUtil.isNone(newVoList)){
                List<T> tempResult = getRelatedObjectSetSub( newVoList,relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,"",false,0,false,false,0, 1,null,unlimitedRowBounds);
                if(!NullUtil.isNone(tempResult)) result.addAll(tempResult);
            }            
        }else{
            result = getRelatedObjectSetSub( objVoList,relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,"",false,0,false,false,0, 1,null,unlimitedRowBounds);
        }
        return(result);
    }
    /**
     * getRelatedObjects()에 대한 Count를 Return하는 Method
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @return Count
     */
    public final int getCountForRelatedObjects(String relationPattern, String filterPattern, String fromToDirection,String selectPattern, String wherePattern, String paramPattern){
        PagingEntity pagingEntity = new PagingEntity();
        pagingEntity.setRowSize(1);
        List<ObjectRootVO> result = this.getRelatedObjectsPaging(relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,pagingEntity);
        return ((PagingList<ObjectRootVO>)result).getRows();
    }
    /**
     * getRelatedObjects()에 대한 Paging을 지원하는 Method
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param pagingEntity
     * @return VO List
     */
    public <T> List<T> getRelatedObjectsPaging(String relationPattern, String filterPattern, String fromToDirection,String selectPattern, String wherePattern, String paramPattern, PagingEntity pagingEntity){
        List<ObjectRootVO> objVoList = new ArrayList<ObjectRootVO>();
        objVoList.add(getVo());
        RowBounds paramRowBounds = null;
        return getRelatedObjectSetSub( objVoList,relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,"",false,0,false,false,0, 1,pagingEntity,paramRowBounds);
    }
    /**
     * Object List를 가지고 일괄 관련되어진 Object를 Get한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param parentUniqueString
     * @param currentExplodedDepth
     * @param bInclude
     * @param bResultUnique
     * @param objectLimit
     * @param findDepth
     * @return VO List
     */
    public static <T> List<T> getRelatedObjectSet(List<? extends ObjectRootVO> objVoList,String relationPattern, String filterPattern, String fromToDirection,String selectPattern, String wherePattern, String paramPattern,String parentUniqueString,boolean isUniqueStringAlreadyMade,int currentExplodedDepth,boolean bInclude,boolean bResultUnique, int objectLimit,int findDepth){
        RowBounds paramRowBounds = null;
        return getRelatedObjectSetSub(objVoList,relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,parentUniqueString,isUniqueStringAlreadyMade,currentExplodedDepth,bInclude,bResultUnique, objectLimit,findDepth,null,paramRowBounds);
    }
    /**
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param parentUniqueString
     * @param isUniqueStringAlreadyMade
     * @param currentExplodedDepth
     * @param bInclude
     * @param bResultUnique
     * @param objectLimit
     * @param findDepth
     * @param pagingEntity
     * @return VO List
     */
    private final static <T> List<T> getRelatedObjectSetSub(List<? extends ObjectRootVO> objVoList,String relationPattern, String filterPattern, String fromToDirection,String selectPattern, String wherePattern, String paramPattern,String parentUniqueString,boolean isUniqueStringAlreadyMade,int currentExplodedDepth,boolean bInclude,boolean bResultUnique, int objectLimit,int findDepth,PagingEntity pagingEntity,RowBounds paramRowBounds){
        return getRelatedObjectSetCore(objVoList,relationPattern, filterPattern, fromToDirection,selectPattern, wherePattern, paramPattern,null,parentUniqueString,isUniqueStringAlreadyMade,currentExplodedDepth,bInclude,bResultUnique, objectLimit,findDepth,pagingEntity,paramRowBounds);
    }
    /**
     * getRelatedObjects에 대한 Group By를 지원하는 Method
     * <pre>
     *  {@code
        UsersVO userVO = BusinessObjectMaster.findBusinessObjectMaster("Users", "XP3866");
        Users userDom = new Users(userVO);
        List<OmcGroupByParamVO> groupByParamList = StringUtil.makeGroubByMaxMinPattern("@this.[className] className","@this.[flags] flags","@this.[names] nameMaxAlias","@this.[titles] nameMinAlias","@this.[flags] flags");
        List<GroupByResultVO> findObjectsVOList =  userDom.getRelatedObjectsGroupBy("WorkflowProjectTask", "WorkflowInboxTask", "To", "", "", groupByParamList);
     *  }
     * </pre>
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param wherePattern
     * @param paramPattern
     * @param groupByParamList
     * @return GroupByResultVO List
     */
    public final <T> List<T> getRelatedObjectsGroupBy(String relationPattern, String filterPattern, String fromToDirection,String wherePattern, String paramPattern,List<OmcGroupByParamVO> groupByParamList){
        List<ObjectRootVO> objVoList = new ArrayList<ObjectRootVO>();
        objVoList.add(this.getVo());
        return getRelatedObjectsGroupBySet(objVoList,relationPattern, filterPattern, fromToDirection,wherePattern, paramPattern,groupByParamList);
    }
    /**
     * Object List(getRelatedObjects)에 대해서 Group By를 지원하는 Method
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param wherePattern
     * @param paramPattern
     * @param groupByParamList
     * @return GroupByResultVO List
     * @see ObjectRoot#getRelatedObjectsGroupBy(String, String, String, String, String, List)
     */
    public final static <T> List<T> getRelatedObjectsGroupBySet(List<? extends ObjectRootVO> objVoList,String relationPattern, String filterPattern, String fromToDirection,String wherePattern, String paramPattern,List<OmcGroupByParamVO> groupByParamList){
        RowBounds paramRowBounds = null;
        return getRelatedObjectSetAdvancedSub(objVoList,relationPattern, filterPattern, fromToDirection,wherePattern, paramPattern,groupByParamList,paramRowBounds);
    }
    public static <T> List<T> getRelatedObjectSet(List<? extends ObjectRootVO> objVoList,String relationPattern, String filterPattern, String fromToDirection,String selectPattern,String wherePattern, String paramPattern, boolean isUniqueStringAlreadyMade,boolean bInclude){
        return getRelatedObjectSet(objVoList,relationPattern, filterPattern,fromToDirection,selectPattern, wherePattern, paramPattern,"",isUniqueStringAlreadyMade,1,bInclude,false, 0, 1);
    }
    /**
     * Object 리스트에 대한 전체 Related Object를 Return함.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @return VO List
     */
    public static <T> List<T> getRelatedObjectSet(List<? extends ObjectRootVO> objVoList,String relationPattern, String filterPattern, String fromToDirection,boolean isUniqueStringAlreadyMade,boolean bInclude){
        return getRelatedObjectSet(objVoList,relationPattern, filterPattern, fromToDirection,"", "", "","",isUniqueStringAlreadyMade,1,bInclude,false, 0, 1);
    }
    /**
     * Object 리스트에 대한 전체 Related Object를 Return함.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @return VO List
     */
    public static <T> List<T> getRelatedObjectSet(List<? extends ObjectRootVO> objVoList,String relationPattern, String filterPattern, String fromToDirection){
        return getRelatedObjectSet(objVoList,relationPattern, filterPattern, fromToDirection,"","", "","",false,1,false,false, 0, 1);
    }
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To의 처음으로 나오는 Object 정보 조회
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationPattern Relation 클래스 이름
     * @param filterPattern From/To 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromToDirection From/To 검색 방향
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @return 검색된 Related Object
     */
    public final <T> T getRelatedFirstFetchedObject(   String relationPattern, String fromToDirection, String filterPattern, String selectPattern, String wherePattern, String paramPattern){
        PagingEntity pagingEntity = new PagingEntity();
        pagingEntity.setRowSize(1);
        List<T> list = this.getRelatedObjectsPaging(relationPattern, filterPattern, fromToDirection, selectPattern, wherePattern, paramPattern, pagingEntity);
        if(NullUtil.isNone(list)) return null;
        return list.get(0);
    }    
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To Object (BusinessObject or RelationObject) 정보 조회
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationName Relation 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param filter From/To 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromTo From/To 검색 방향
     * @return 검색된 Object의 List
     */
    public <T> List<T> getRelatedObjects(String relationName, String filter, String fromTo){
        return this.getRelatedObjects(relationName, filter, fromTo, null, null, null, false, false, 0, 1);
    }
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To Object (BusinessObject or RelationObject) 정보 조회
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationName Relation 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param filter From/To 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromTo fromTo From/To 검색 방향
     * @param findDepth 전개 Depth
     * @return 검색된 Object의 List
     */
    public <T> List<T> getRelatedObjects(String relationName, String filter, String fromTo, int findDepth){
        return this.getRelatedObjects(relationName, filter, fromTo, null, null, null, false, false, 0, findDepth);
    }
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To Object (BusinessObject or RelationObject) 정보 조회
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationName Relation 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param filter From/To 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromTo fromTo From/To 검색 방향
     * @param wherePattern Where Pattern
     * @param paramPattern Where Pattern에 사용되어지는 Parameter
     * @param findDepth 전개 Depth
     * @return 검색된 Object의 List
     */
    public <T> List<T> getRelatedObjects(String relationName, String filter, String fromTo, String wherePattern, String paramPattern, int findDepth){
        return this.getRelatedObjects(relationName, filter, fromTo, null, wherePattern, paramPattern, false, false, 0, findDepth);
    }
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To Object (BusinessObject or RelationObject) 정보 조회
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationName Relation 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromTo From/To 검색 방향
     * @return
     */
    public <T> List<T> getRelatedObjects(String relationName, String fromTo){
        return this.getRelatedObjects(relationName, GlobalConstants.FLAG_TYPE_ALL, fromTo, null, null, null, false,
                false, 0, 1);
    }
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To Object (BusinessObject or RelationObject) 정보 조회
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationName Relation 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @return
     */
    public <T> List<T> getRelatedObjects(String relationName){
        return this.getRelatedObjects(relationName, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, null,
                null, null, false, false, 0, 1);
    }
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To Object (BusinessObject or RelationObject) 정보 조회
     *
     * @return
     */
    public <T> List<T> getRelatedObjects(){
        return this.getRelatedObjects(GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, null, null, null, false, false, 0, 1);
    }
    /**
     * 기준 Object와 연결된 relation을 기반으로 From/To Object (BusinessObject or RelationObject) 정보 조회
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationName Relation 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param filter From/To 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromTo From/To 검색 방향
     * @return 검색된 Object
     */
    public <T> T getRelatedObject(String relationName, String filter, String fromTo){
        T relatedObject = null;
        List<T> relatedObjectList = this.getRelatedObjects(relationName, filter, fromTo, null, null, null, false,false, 1, 1);
        if (!NullUtil.isNone(relatedObjectList)) {
            relatedObject = relatedObjectList.get(0);
        }
        return relatedObject;
    }
    /**
     * 기준 Object와 연결된 Relation 정보 조회
     *
     * @return
     */
    public <T> List<T> getRelationship(){
        return this.getRelationship(GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL, null, null, null);
    }
    /**
     * 기준 Object와 연결된 Relation 정보 조회
     *
     * @param relationName Relation 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @return
     */
    public <T> List<T> getRelationship(String relationName){
        return this.getRelationship(relationName, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, null,null, null);
    }
    /**
     * 기준 Object와 연결된 Relation 정보 조회
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationName Relation 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromTo From/To 검색 방향
     * @return
     */
    public <T> List<T> getRelationship(String relationName, String fromTo){
        return this.getRelationship(relationName, GlobalConstants.FLAG_TYPE_ALL, fromTo, null, null, null);
    }
    /**
     * 기준 Object와 연결된 Relation 정보 조회
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationName Relation 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param filter From/To 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromTo From/To 검색 방향
     * @return
     */
    public <T> List<T> getRelationship(String relationName, String filter, String fromTo){
        return this.getRelationship(relationName, filter, fromTo, null, null, null);
    }
    /**
     * getRelationship()에 대한 Paging을 지원하는 Method
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param pagingEntity
     * @return
     */
    public <T> List<T> getRelationshipPaging(String relationPattern, String filterPattern,String fromToDirection, String selectPattern, String wherePattern, String paramPattern,PagingEntity pagingEntity){
        if(NullUtil.isNull(pagingEntity)) throw new FoundationException("[Foundation]PagingEntity cannot be empty.");
        return getRelationshipPagingSub(this.getVo(),relationPattern,filterPattern,fromToDirection,EtcUtil.convertNullToString(selectPattern),EtcUtil.convertNullToString(wherePattern),EtcUtil.convertNullToString(paramPattern),pagingEntity,null);
    } 
    /**
     * 기준 Object와 연결된 Relation 정보 조회
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationPattern Relation Pattern 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param filterPattern filter From/To 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromToDirection fromTo From/To 검색 방향
     * @param selectPattern Select Pattern
     * @param wherePattern Where Pattern
     * @param paramPattern Parameter Pattern
     * @return BussinessRelationObjectVO를 Return함.
     */
    public <T> List<T> getRelationship(String relationPattern, String filterPattern,String fromToDirection, String selectPattern, String wherePattern, String paramPattern){
        return getRelationshipSub(this.getVo(),relationPattern,filterPattern,fromToDirection,EtcUtil.convertNullToString(selectPattern),EtcUtil.convertNullToString(wherePattern),EtcUtil.convertNullToString(paramPattern),null);
    }
    public <T> List<T> getRelationshipUnlimited(String relationPattern, String filterPattern,String fromToDirection, String selectPattern, String wherePattern, String paramPattern){
        RowBounds unlimitedRowBounds = getOmcUnlimitedRowBounds();
        return getRelationshipSub(this.getVo(),relationPattern,filterPattern,fromToDirection,EtcUtil.convertNullToString(selectPattern),EtcUtil.convertNullToString(wherePattern),EtcUtil.convertNullToString(paramPattern),unlimitedRowBounds);
    }
    /**
     * <pre>
     * getRelatonShip()에 대한 GroupBY를 지원하는 것으로서 groupByParamList가 Select Pattern으로 
     * 자동 생성되어지고 Sort By를 지원하지 않는다. Result를가지고 Java에서 SortUtil을 이용. 
     * ★★★ Example ★★★
     * {@code
     *  UsersVO userVO = BusinessObjectMaster.findBusinessObjectMaster("Users", "XP3866");
     *  Users userDom = new Users(userVO);
     *  List<OmcGroupByParamVO> groupByParamList = StringUtil.makeGroubByMaxMinPattern("@this.[className] className","@this.[flags] flags","@this.[fromObid] nameMaxAlias","@this.[toObid] nameMinAlias","@this.[flags] flags");
     *  List<GroupByResultVO> findObjectsVOList =  userDom.getRelationshipGroupBy("WorkflowProjectTask", "WorkflowInboxTask", "To", "", "", groupByParamList);
     * }
     * System.out.println("creadtorAlias:" + vo.getBasisOnAttributeValue("creadtorAlias"));
     * System.out.println("flags:" + vo.getSumAttributeValue("flags"));
     * System.out.println("nameMaxAlias:" + vo.getMaxAttributeValue("nameMaxAlias"));
     * System.out.println("nameMinAlias:" + vo.getMinAttributeValue("nameMinAlias"));
     * System.out.println("flags:" + vo.getAvgAttributeValue("flags"));
     * System.out.println(vo.getGroupBycount());
     * </pre>
     * 
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationPattern Relation Pattern
     * @param filterPattern filter From/To 클래스 이름 (","를 사용하여 여러 개 입력가능)
     * @param fromToDirection fromTo From/To 검색 방향
     * @param wherePattern Where Pattern
     * @param paramPattern Parameter Pattern
     * @param groupByParamList Group By가 정의되어진 List(min,max,sum,avg 지원)
     * @return GroupByResultVO의 List를 Return함.
     * Return되어지는 Attribute Value는 GroupByResultVO.getXXXXAttributeValue()사용
     * 
     */
    public final <T> List<T> getRelationshipGroupBy(String relationPattern, String filterPattern,String fromToDirection, String wherePattern, String paramPattern,List<OmcGroupByParamVO> groupByParamList){
        List<ObjectRootVO> objVoList = new ArrayList<ObjectRootVO>();
        objVoList.add(this.getVo());
        return getRelationshipAdvanced(objVoList, relationPattern, filterPattern,fromToDirection, wherePattern, paramPattern,groupByParamList);
    }
    /**
     * getRelationshipGroupBy()에서 Muti Object에 대한 Group By를 지원하는 Method
     * 
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     * 
     * <pre>
     *  {@code
     *  List<OmcGroupByParamVO> groupByParamList = StringUtil.makeGroubByMaxMinPattern("@this.[className] className","@this.[flags] flags","@this.[fromObid] nameMaxAlias","@this.[toObid] nameMinAlias","@this.[flags] flags");
     *  UsersVO userVO = BusinessObjectMaster.findBusinessObjectMaster("Users", "XP3866");
     *  Users userDom = new Users(userVO);
     *  List<GroupByResultVO> findObjectsVOList =  userDom.getRelationshipGroupBy("WorkflowProjectTask", "WorkflowInboxTask", "To", "", "", groupByParamList);
     *  }
     * </pre>
     * 
     * @param objVoList VO List
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param wherePattern
     * @param paramPattern
     * @param groupByParamList
     * @return GroupByResultVO의 List를 Return함.
     * 
     * Return되어지는 Attribute Value는 GroupByResultVO.getXXXXAttributeValue()사용
     * @see ObjectRoot#getRelationshipGroupBy(String, String, String, String, String, List)
     */
    public static final <T> List<T> getRelationshipGroupBySet(List<? extends ObjectRootVO> objVoList, String relationPattern, String filterPattern,String fromToDirection, String wherePattern, String paramPattern,List<OmcGroupByParamVO> groupByParamList){
        RowBounds paramRowBounds = null;
        return OmcOQLCoreGetRelationShipAPI._getRelattionShip(objVoList,relationPattern,fromToDirection,filterPattern,"",EtcUtil.convertNullToString(wherePattern),paramPattern,groupByParamList,0,null,paramRowBounds,1,false);
    }
    /**
     * 
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getRelationshipSet(List<? extends ObjectRootVO> objVoList, String relationPattern, String filterPattern,String fromToDirection, String selectPattern, String wherePattern, String paramPattern){
        SortUtil.uniquized(objVoList, "obid");
        RowBounds paramRowBounds = null;
        List<T> result = new ArrayList<T>();
        if(NullUtil.isNone(objVoList)) return result;
        List<ObjectRootVO> newVoList = new ArrayList<ObjectRootVO>();
        if(objVoList.size() > OmcSystemConstants.OBJECT_SET_CMD_UNIT){
            int seq = 0;
            for(ObjectRootVO vo : objVoList){
                if(seq > 0 && seq%OmcSystemConstants.OBJECT_SET_CMD_UNIT == 0){
                    List<T> tempResult = OmcOQLCoreGetRelationShipAPI._getRelattionShip((List<ObjectRootVO>)newVoList,relationPattern,fromToDirection,filterPattern,selectPattern,wherePattern,paramPattern,null,0,null,paramRowBounds,1,false);
                    if(!NullUtil.isNone(tempResult)) result.addAll(tempResult);
                    newVoList = new ArrayList<ObjectRootVO>();
                }
                newVoList.add(vo);
                seq++;
            }
            if(!NullUtil.isNone(newVoList)){
                List<T> tempResult = OmcOQLCoreGetRelationShipAPI._getRelattionShip((List<ObjectRootVO>)newVoList,relationPattern,fromToDirection,filterPattern,selectPattern,wherePattern,paramPattern,null,0,null,paramRowBounds,1,false);
                if(!NullUtil.isNone(tempResult)) result.addAll(tempResult);
            }            
        }else{
            result = OmcOQLCoreGetRelationShipAPI._getRelattionShip((List<ObjectRootVO>)objVoList,relationPattern,fromToDirection,filterPattern,selectPattern,wherePattern,paramPattern,null,0,null,paramRowBounds,1,false);
        }
        return(result);
    }
//    public static <T> List<T> getRelationshipSetUnlimited(List<? extends ObjectRootVO> objVoList, String relationPattern, String filterPattern,String fromToDirection, String selectPattern, String wherePattern, String paramPattern){
//        RowBounds unlimitedRowBounds = getOmcUnlimitedRowBounds();
//        return OmcOQLCoreGetRelationShipAPI._getRelattionShip(objVoList,relationPattern,fromToDirection,filterPattern,selectPattern,wherePattern,paramPattern,null,0,null,unlimitedRowBounds,1,false);
//    }
    /**
     * 
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @return
     */
    public static <T> List<T> getRelationshipSet(List<? extends ObjectRootVO> objVoList, String relationPattern, String filterPattern,String fromToDirection){
        return(getRelationshipSet(objVoList,relationPattern,filterPattern,fromToDirection,"","",""));
    }
    /**
     * 
     *
     * @param direction
     * @param relationClassFilter
     * @param fromClassFilter
     * @param toClassFilter
     * @return
     */
    public final <T> List<T> getRelationShips(String direction, String relationClassFilter, String fromClassFilter, String toClassFilter){
        return(getRelationObjectListSub(direction,relationClassFilter,fromClassFilter,toClassFilter));
    }
    /**
     * 
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param direction
     * @return
     */
    public final <T> List<T> getRelationShips(String direction){
        return(getRelationObjectListSub(direction,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL));
    }
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param direction
     * @param relationClassFilter
     * @return
     */
    public final <T> List<T> getRelationShips(String direction, String relationClassFilter){
        return(getRelationObjectListSub(direction,relationClassFilter,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL));
    }
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationClassFilter
     * @return
     */
    public final <T> List<T> getFromRelationShips(String relationClassFilter){
        return(getRelationObjectListSub(GlobalConstants.FLAG_TYPE_FROM,relationClassFilter,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL));
    }
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationClassFilter
     * @param classFilter
     * @return
     */
    public final <T> List<T> getFromRelationShips(String relationClassFilter, String classFilter){
        return(getRelationObjectListSub(GlobalConstants.FLAG_TYPE_FROM,relationClassFilter,classFilter,GlobalConstants.FLAG_TYPE_ALL));
    }
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationClassFilter
     * @return
     */
    public final <T> List<T> getToRelationShips(String relationClassFilter){
        return(getRelationObjectListSub(GlobalConstants.FLAG_TYPE_TO,relationClassFilter,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL));
    }
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param relationClassFilter
     * @param classFilter
     * @return
     */
    public final <T> List<T> getToRelationShips(String relationClassFilter, String classFilter){
        return(getRelationObjectListSub(GlobalConstants.FLAG_TYPE_TO,relationClassFilter,GlobalConstants.FLAG_TYPE_ALL,classFilter));
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern owner 검색 조건
     * @param checkouterPattern checkouter 검색 조건
     * @param lockerPattern locker 검색 조건
     * @param firstOnly (true/false)
     * @param latestOnly (true/false)
     * @param lockedOnly (true/false)
     * @param checkoutedOnly (true/false)
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @param objectLimit 검색 건 수 ("0"인 경우 전체 목록 검색)
     * @return
     */
    public static final <T> List<T> searchObjects(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
            String ownerPattern, String checkouterPattern, String lockerPattern, boolean firstOnly, boolean latestOnly,
            boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String fromPattern, String wherePattern,
            String paramPattern, boolean expandType, int objectLimit){
        return ObjectRoot.searchObjectsWithHint(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                creatorPattern, modifierPattern, ownerPattern, checkouterPattern, lockerPattern, firstOnly, latestOnly,
                lockedOnly, checkoutedOnly, selectPattern, fromPattern, wherePattern, paramPattern, expandType,
                null, objectLimit);
    }
    /**
     * <pre>
     * Search Object에 대한 Group By를 지원하는 Method
     * 
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     * 
     * </pre>
     * @param className
     * @param fromPattern
     * @param wherePattern
     * @param paramPattern
     * @param groupByParamList
     * @return GroupByResultVO의 List형식으로 Retrun되어짐.
     * @see ObjectRoot#searchObjectsGroupBy(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, List, boolean, String, int)
     */
    public static final <T> List<T> searchObjectsGroupBy(String className, 
                                                         String fromPattern, 
                                                         String wherePattern, 
                                                         String paramPattern, 
                                                         List<OmcGroupByParamVO> groupByParamList){
        return searchObjectsGroupBy(className, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false, false,false, false, "", fromPattern, wherePattern, paramPattern, groupByParamList, true, "", 0);
    }
    /**
     * <pre>
     * Alias를 주는 방법은 "@this.[creator] creator"으로 Space이후에 Alias를 줌.
     * StringUtil.makeGroubByMaxMinPattern의 Parameter는 ":"로 여러개를 줄 수 있음.
     * Return되어지는 GroupByResultVO의 List에서 값을 가져오는 방법은 GroupByResultVO의 Static get Method를 이용해서 값을 찾음.
     * 
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     * 
     * {@code
        List<OmcGroupByParamVO> groupByParamList = StringUtil.makeGroubByMaxMinPattern("@this.[creator] creator","@this.[flags] flags","@this.[names] nameMaxAlias","@this.[names] nameMinAlias:@this.[titles] titles","@this.[flags] flags");
        fromPattern.append("<this>ThisConnectedWithTo<[ProjectMember]@PM>+");
        fromPattern.append("<[ProjectMember]@PM>FromConnectedWithThis<[Projects]@PR>+"); 
        fromPattern.append("<[Projects]@PR>ThisConnectedWithFrom<[HasProjectRevision]@P2R>+");  
        fromPattern.append("<[HasProjectRevision]@P2R>ToConnectedWithThis<[RevisedProjects]@RP>+");  
        StringUtil.constructWherePattern(wherePattern, paramPattern,   "@RP.[previousObid]", GlobalConstants.OQL_OPERATOR_EQUAL, "1");
        StringUtil.constructWherePattern(wherePattern, paramPattern,   "@this.[titles]", GlobalConstants.OQL_OPERATOR_EQUAL, "XP3866");
        List<GroupByResultVO> groupByResultVOList = ObjectRoot.searchObjectsGroupBy(ApplicationSchemaConstants.BIZCLASS_USERS, fromPattern.toString(), wherePattern.toString(), paramPattern.toString(), groupByParamList);
       }
     * @param className
     * @param namePattern
     * @param revisionPattern
     * @param lifeCylePattern
     * @param statePattern
     * @param creatorPattern
     * @param modifierPattern
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern
     * @param fromPattern
     * @param wherePattern
     * @param paramPattern
     * @param groupByParamList
     * @param expandType
     * @param searchHint
     * @param objectLimit
     * @return GroupByResultVO의 List형식으로 Return되어짐.
     */
    public static final <T> List<T> searchObjectsGroupBy(String className, 
                                                         String namePattern, 
                                                         String revisionPattern,
                                                         String lifeCylePattern, 
                                                         String statePattern, 
                                                         String creatorPattern, 
                                                         String modifierPattern,
                                                         String ownerPattern, 
                                                         String checkouterPattern, 
                                                         String lockerPattern, 
                                                         boolean firstOnly, 
                                                         boolean latestOnly,
                                                         boolean lockedOnly, 
                                                         boolean checkoutedOnly, 
                                                         String selectPattern, 
                                                         String fromPattern, 
                                                         String wherePattern,
                                                         String paramPattern, 
                                                         List<OmcGroupByParamVO> groupByParamList, 
                                                         boolean expandType, 
                                                         String searchHint, 
                                                         int objectLimit){
        return searchObjectsCore(className, namePattern, revisionPattern,lifeCylePattern, statePattern, creatorPattern, modifierPattern,ownerPattern, checkouterPattern, lockerPattern, firstOnly, latestOnly,lockedOnly, checkoutedOnly, selectPattern, fromPattern, wherePattern,paramPattern, groupByParamList,expandType, searchHint, objectLimit,null);
    }
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className
     * @param namePattern
     * @param revisionPattern
     * @param lifeCylePattern
     * @param statePattern
     * @param creatorPattern
     * @param modifierPattern
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern
     * @param fromPattern
     * @param wherePattern
     * @param paramPattern
     * @param expandType
     * @param searchHint
     * @param objectLimit
     * @return GroupByResultVO의 List형식으로 Retrun되어짐.
     */
    public static final <T> List<T> searchObjectsWithHint(String className, 
                                                          String namePattern, 
                                                          String revisionPattern,
                                                          String lifeCylePattern, 
                                                          String statePattern, 
                                                          String creatorPattern, 
                                                          String modifierPattern,
                                                          String ownerPattern, 
                                                          String checkouterPattern, 
                                                          String lockerPattern, 
                                                          boolean firstOnly, 
                                                          boolean latestOnly,
                                                          boolean lockedOnly, 
                                                          boolean checkoutedOnly, 
                                                          String selectPattern, 
                                                          String fromPattern, 
                                                          String wherePattern,
                                                          String paramPattern, 
                                                          boolean expandType, 
                                                          String searchHint, 
                                                          int objectLimit){
        return searchObjectsCore(className, namePattern, revisionPattern,lifeCylePattern, statePattern, creatorPattern, modifierPattern,ownerPattern, checkouterPattern, lockerPattern, firstOnly, latestOnly,lockedOnly, checkoutedOnly, selectPattern, fromPattern, wherePattern,paramPattern, null,expandType, searchHint, objectLimit,null);
    }
    /**
     * 
     *
     * @param className
     * @param namePattern
     * @param revisionPattern
     * @param lifeCylePattern
     * @param statePattern
     * @param creatorPattern
     * @param modifierPattern
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern
     * @param fromPattern
     * @param wherePattern
     * @param paramPattern
     * @param groupByParamList
     * @param expandType
     * @param searchHint
     * @param objectLimit
     * @return
     */
    private static final <T> List<T> searchObjectsCore(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
            String ownerPattern, String checkouterPattern, String lockerPattern, boolean firstOnly, boolean latestOnly,
            boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String fromPattern, String wherePattern,
            String paramPattern, List<OmcGroupByParamVO> groupByParamList, boolean expandType, String searchHint, int objectLimit,RowBounds paramRowBounds){
        return OmcOQLCoreSearchAPI._searchObjects(className,namePattern,revisionPattern,lifeCylePattern,statePattern,creatorPattern,modifierPattern,ownerPattern,checkouterPattern,lockerPattern,firstOnly,latestOnly,lockedOnly,checkoutedOnly,selectPattern,fromPattern,wherePattern,paramPattern,groupByParamList,expandType,searchHint,objectLimit,paramRowBounds,1,false);
    }
    
    public static final <T> List<T> searchObjects(String className, String selectPattern, String fromPattern, String wherePattern,String paramPattern){
    	return ObjectRoot.searchObjects(className,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,
 	           GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,
 	           GlobalConstants.FLAG_TYPE_ALL,false,false,false,false,selectPattern,fromPattern,wherePattern,paramPattern,true,0);
    }
    
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건 lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @param objectLimit 검색 건 수 ("0"인 경우 전체 목록 검색)
     * @return
     */
    public static final <T> List<T> searchObjects(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
            String ownerPattern, String checkouterPattern, String lockerPattern, String selectPattern,
            String fromPattern, String wherePattern, String paramPattern, boolean expandType, int objectLimit){
        return ObjectRoot.searchObjects(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                creatorPattern, modifierPattern, ownerPattern, checkouterPattern, lockerPattern, false, false, false,
                false, selectPattern, fromPattern, wherePattern, paramPattern, expandType, objectLimit);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @param objectLimit 검색 건 수 ("0"인 경우 전체 목록 검색)
     * @return
     */
    public static final <T> List<T> searchObjects(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, boolean firstOnly, boolean latestOnly, boolean lockedOnly,
            boolean checkoutedOnly, String selectPattern, String fromPattern, String wherePattern,
            String paramPattern, boolean expandType, int objectLimit){
        return ObjectRoot.searchObjects(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, firstOnly, latestOnly, lockedOnly,
                checkoutedOnly, selectPattern, fromPattern, wherePattern, paramPattern, expandType, objectLimit);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param expandType (true/false)
     * @param objectLimit 검색 건 수 ("0"인 경우 전체 목록 검색)
     * @return
     */
    public static final <T> List<T> searchObjects(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, boolean expandType, int objectLimit){
        return ObjectRoot.searchObjects(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false, false, false, false, "", "", "",
                "", expandType, objectLimit);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @param objectLimit 검색 건 수 ("0"인 경우 전체 목록 검색)
     * @return
     */
    public static final <T> List<T> searchObjects(String className, String namePattern, String revisionPattern,
            String selectPattern, String fromPattern, String wherePattern, String paramPattern, boolean expandType,
            int objectLimit){
        return ObjectRoot.searchObjects(className, namePattern, revisionPattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
                false, false, false, selectPattern, fromPattern, wherePattern, paramPattern, expandType,
                objectLimit);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern owner 검색 조건
     * @param checkouterPattern checkouter 검색 조건
     * @param lockerPattern locker 검색 조건
     * @param firstOnly (true/false)
     * @param latestOnly (true/false)
     * @param lockedOnly (true/false)
     * @param checkoutedOnly (true/false)
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @param searchHint
     * @return
     */
    public static final <T> List<T> searchObjectPagingListWithHint( String className, 
                                                                    String namePattern,
                                                                    String revisionPattern, 
                                                                    String lifeCylePattern, 
                                                                    String statePattern, 
                                                                    String creatorPattern,
                                                                    String modifierPattern, 
                                                                    String ownerPattern, 
                                                                    String checkouterPattern, 
                                                                    String lockerPattern,
                                                                    boolean firstOnly, 
                                                                    boolean latestOnly, 
                                                                    boolean lockedOnly, 
                                                                    boolean checkoutedOnly, 
                                                                    String selectPattern,
                                                                    String fromPattern, 
                                                                    String wherePattern, 
                                                                    String paramPattern, 
                                                                    boolean expandType,
                                                                    PagingEntity pagingEntity,
                                                                    int objectLimit,
                                                                    String searchHint){
        
        List<T> searchObjectList = OmcOQLCoreSearchAPI._searchObjectsPaging(className           , 
                                                                            namePattern         , 
                                                                            revisionPattern     ,
                                                                            lifeCylePattern     , 
                                                                            statePattern        , 
                                                                            creatorPattern      , 
                                                                            modifierPattern     ,
                                                                            ownerPattern        , 
                                                                            checkouterPattern   , 
                                                                            lockerPattern       , 
                                                                            firstOnly           , 
                                                                            latestOnly          ,
                                                                            lockedOnly          , 
                                                                            checkoutedOnly      , 
                                                                            selectPattern       , 
                                                                            fromPattern         , 
                                                                            wherePattern        ,
                                                                            paramPattern        , 
                                                                            expandType          , 
                                                                            searchHint          ,
                                                                            objectLimit         ,
                                                                            pagingEntity        ,
                                                                            null                ,
                                                                            1                   ,
                                                                            false               );
        return searchObjectList;
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern owner 검색 조건
     * @param checkouterPattern checkouter 검색 조건
     * @param lockerPattern locker 검색 조건
     * @param firstOnly (true/false)
     * @param latestOnly (true/false)
     * @param lockedOnly (true/false)
     * @param checkoutedOnly (true/false)
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> List<T> searchObjectPagingList(String  className         , 
                                                           String  namePattern       ,
                                                           String  revisionPattern   , 
                                                           String  lifeCylePattern   , 
                                                           String  statePattern      , 
                                                           String  creatorPattern    ,
                                                           String  modifierPattern   , 
                                                           String  ownerPattern      , 
                                                           String  checkouterPattern , 
                                                           String  lockerPattern     ,
                                                           boolean firstOnly         , 
                                                           boolean latestOnly        , 
                                                           boolean lockedOnly        , 
                                                           boolean checkoutedOnly    , 
                                                           String  selectPattern     ,
                                                           String  fromPattern       , 
                                                           String  wherePattern      , 
                                                           String  paramPattern  , 
                                                           boolean expandType        ,
                                                           PagingEntity pagingEntity    ){
        return ObjectRoot.searchObjectPagingListWithHint(className, namePattern, revisionPattern, lifeCylePattern,
                statePattern, creatorPattern, modifierPattern, ownerPattern, checkouterPattern, lockerPattern,
                firstOnly, latestOnly, lockedOnly, checkoutedOnly, selectPattern, fromPattern, wherePattern,
                paramPattern, expandType, pagingEntity, 0, null);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건 lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> List<T> searchObjectPagingList(String className, String namePattern,
            String revisionPattern, String lifeCylePattern, String statePattern, String creatorPattern,
            String modifierPattern, String ownerPattern, String checkouterPattern, String lockerPattern,
            String selectPattern, String fromPattern, String wherePattern, String paramPattern, boolean expandType,
            PagingEntity pagingEntity    ){
        return ObjectRoot.searchObjectPagingListWithHint(className, namePattern, revisionPattern, lifeCylePattern,
                statePattern, creatorPattern, modifierPattern, ownerPattern, checkouterPattern, lockerPattern, false,
                false, false, false, selectPattern, fromPattern, wherePattern, paramPattern, expandType, pagingEntity, 0, null);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> List<T> searchObjectPagingList(String className, String namePattern,
            String revisionPattern, String lifeCylePattern, String statePattern, boolean firstOnly, boolean latestOnly,
            boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String fromPattern, String wherePattern,
            String paramPattern, boolean expandType, PagingEntity pagingEntity){
        return ObjectRoot.searchObjectPagingListWithHint(className, namePattern, revisionPattern, lifeCylePattern,
                statePattern, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, firstOnly,
                latestOnly, lockedOnly, checkoutedOnly, selectPattern, fromPattern, wherePattern, paramPattern,
                expandType, pagingEntity, 0, null);
    }
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className
     * @param selectPattern
     * @param fromPattern
     * @param wherePattern
     * @param paramPattern
     * @return
     */
    public static final int getCountForSearchObjects(String className, String selectPattern, String fromPattern, String wherePattern,String paramPattern){
        return getCountForSearchObjects(className, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false, false,
            false, false, selectPattern, fromPattern, wherePattern,paramPattern);
    }
    /**
     * 
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className
     * @param namePattern
     * @param revisionPattern
     * @param lifeCylePattern
     * @param statePattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern
     * @param fromPattern
     * @param wherePattern
     * @param paramPattern
     * @return
     */
    public static final int getCountForSearchObjects(String className, String namePattern,String revisionPattern, String lifeCylePattern, String statePattern, boolean firstOnly, boolean latestOnly,boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String fromPattern, String wherePattern,String paramPattern){
        PagingEntity pagingEntity = new PagingEntity();
        pagingEntity.setRowSize(1);
        List<ObjectRootVO> result = ObjectRoot.searchObjectPagingListWithHint(className, namePattern, revisionPattern, lifeCylePattern,
                statePattern, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, firstOnly,
                latestOnly, lockedOnly, checkoutedOnly, selectPattern, fromPattern, wherePattern, paramPattern,
                true, pagingEntity, 0, null);
        return ((PagingList<ObjectRootVO>)result).getRows();
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> List<T> searchObjectPagingList(String className, String namePattern,
            String revisionPattern, String lifeCylePattern, String statePattern, boolean expandType, PagingEntity pagingEntity){
        return ObjectRoot.searchObjectPagingListWithHint(className, namePattern, revisionPattern, lifeCylePattern,
                statePattern, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
                false, false, false, "", "", "", "", expandType, pagingEntity, 0, null);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> List<T> searchObjectPagingList(String className, String namePattern,
            String revisionPattern, String selectPattern, String fromPattern, String wherePattern,
            String paramPattern, boolean expandType,PagingEntity pagingEntity){
        return ObjectRoot.searchObjectPagingListWithHint(className, namePattern, revisionPattern,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, false, false, false, false, selectPattern, fromPattern, wherePattern,
                paramPattern, expandType, pagingEntity, 0, null);
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T searchObject(String className, String namePattern, String revisionPattern,boolean expandType){
        T searchObject = ObjectRoot.searchObject(className, namePattern, revisionPattern,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, false, false, false, false, "", "", "", "", expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T searchObject(String className, String namePattern, boolean expandType){
        T searchObject = ObjectRoot.searchObject(className, namePattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, false, false, false, false, "", "", "", "", expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T searchObject(String className, String namePattern, String revisionPattern,
            String selectPattern, String fromPattern, String wherePattern, String paramPattern, boolean expandType){
        T searchObject = ObjectRoot.searchObject(className, namePattern, revisionPattern,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, false, false, false, false, selectPattern, fromPattern, wherePattern,
                paramPattern, expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param latestOnly
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T searchObject(String className, String namePattern, boolean latestOnly,
            String selectPattern, String fromPattern, String wherePattern, String paramPattern, boolean expandType){
        T searchObject = ObjectRoot.searchObject(className, namePattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, false, latestOnly, false, false, selectPattern, fromPattern, wherePattern,
                paramPattern, expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다. (firstOnly가 true)
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T searchFirstObject(String className, String namePattern, boolean expandType){
        T searchObject = ObjectRoot.searchObject(className, namePattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, true, false, false, false, "", "", "", "", expandType);
        return searchObject;
    }
    /**
     * Object를 검색한다. (latestOnly가 true)
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param expandType (true/false)
     * @return
     */
    public static final <T> T searchLatestObject(String className, String namePattern, boolean expandType){
        T searchObject = ObjectRoot.searchObject(className, namePattern, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, false, true, false, false, "", "", "", "", expandType);
        return searchObject;
    }
    /**
     * 수정 되어질 Attribute에 대한 Validation. Foundation에서 관리하는 Attribute는 수정할 수 없음.
     *
     * @param attributes Attribute Set
     */
    static private final void validateForModifyObjectSetBatch(Set<String> attributes){
        StringBuffer errAttr = new StringBuffer();
        boolean isError = false;
        if(attributes == null || attributes.size() == 0) throw new FoundationException("[Foundation]No Attribue to update!!!"); 
        for(String attr : attributes){
            if(StrUtil.in(attr, OmcApplicationConstants.MODIFY_OBJECT_SET_NOT_ALLOWED_ATTRS)){
                isError = true;
                errAttr.append(",").append(attr);
            }
        }
        if(isError) throw new FoundationException("[Foundation]Critical-Not supported Update attribute(" + errAttr.substring(1) + ")."); 
    }
    protected final void create(){
        CommonServiceUtil.createObject(getVo());
    }
    protected final void modify(){
        CommonServiceUtil.modifyObject(getVo());
    }
    protected final void update(){
        CommonServiceUtil.updateObject(getVo());
    }
    protected final void update(ObjectRootVO inputVO){
        CommonServiceUtil.updateObject(inputVO, false);
    }
    /**
     * policy, className, names, states, owner 용 update
     * -+ className, names 변경 시 key table update 필요함!!!
     *
     * @param bWithKeyTable
     */
    protected final void change(){
        CommonServiceUtil.changeObjectWithKeyTable(getVo());
    }
    protected final void change(ObjectRootVO inputVO, boolean isOwner){
        CommonServiceUtil.changeObject(inputVO, isOwner);
    }
    protected final void delete(Map<String, Object> map){
        this.deleteRelationShipForObjectDelete(this,map);
        CommonServiceUtil.deleteObject(getVo());
    }
    protected void validateForDelete(Map<String, Object> map){;}
    protected void preProcessForDelete(Map<String, Object> map){;}
    protected void postProcessForDelete(Map<String, Object> map){;}
    private final void deleteObjectSub(Map<String, Object> map){
        validateForDelete(map);
        preProcessForDelete(map);
        delete(map);
        postProcessForDelete(map);
    }
    protected void validateForModify(Map<String, Object> map){}
    protected void preProcessForModify(Map<String, Object> map){}
    protected void postProcessForModify(Map<String, Object> map){}
    /**
     * Modify에 대한 Sub Program
     *
     * @param map
     */
    private final void modifyObjectSub(Map<String, Object> map){
        validateForModify(map);
        preProcessForModify(map);
        modify();
        postProcessForModify(map);
    }
    /**
     * Group by를 위한 Sub Program
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param wherePattern
     * @param paramPattern
     * @param groupByParamList
     * @return GroupByResultVO List
     */
    private final static <T> List<T> getRelatedObjectSetAdvancedSub(List<? extends ObjectRootVO> objVoList,String relationPattern, String filterPattern, String fromToDirection,String wherePattern, String paramPattern,List<OmcGroupByParamVO> groupByParamList,RowBounds paramRowBounds){
        return getRelatedObjectSetCore(objVoList,relationPattern, filterPattern, fromToDirection,"", wherePattern, paramPattern,groupByParamList,"",false,0,false,false, 0,1,null,paramRowBounds);
    }
    /**
     * 
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param parentUniqueString
     * @param isUniqueStringAlreadyMade
     * @param currentExplodedDepth
     * @param bInclude
     * @param bResultUnique
     * @param objectLimit
     * @param findDepth
     * @param pagingEntity
     * @return VO List
     */
    @SuppressWarnings("unchecked")
    private final static <T> List<T> getRelatedObjectSetCore(List<? extends ObjectRootVO> objVoList,
                                                             String relationPattern, 
                                                             String filterPattern, 
                                                             String fromToDirection,
                                                             String selectPattern, 
                                                             String wherePattern, 
                                                             String paramPattern,
                                                             List<OmcGroupByParamVO> groupByParamList,
                                                             String parentUniqueString,
                                                             boolean isUniqueStringAlreadyMade,
                                                             int    currentExplodedDepth,
                                                             boolean bInclude,
                                                             boolean bResultUnique, 
                                                             int objectLimit, 
                                                             int findDepth,
                                                             PagingEntity pagingEntity,
                                                             RowBounds paramRowBounds){
        if(NullUtil.isNone(relationPattern)) relationPattern = GlobalConstants.FLAG_TYPE_ALL;
        if(NullUtil.isNone(filterPattern))   filterPattern   = GlobalConstants.FLAG_TYPE_ALL;
        if(NullUtil.isNone(fromToDirection)) fromToDirection = GlobalConstants.FLAG_TYPE_ALL;
        if(NullUtil.isNone(objVoList)) return new ArrayList<T>();
        
        if (findDepth > 1 && (filterPattern.equals(GlobalConstants.FLAG_TYPE_ALL))) throw new FoundationException("api.object.error.getrelated.parameter.recur");
        if (findDepth > 1 && (!NullUtil.isNull(pagingEntity))) throw new FoundationException("api.object.error.getrelated.parameter.paging.depth");
        if (!NullUtil.isNone(objVoList) && objVoList.size() > 1 && !NullUtil.isNull(pagingEntity) && pagingEntity.getRowSize() > 1) throw new FoundationException("api.object.error.getrelated.parameter.paging.objSet");
        
        List<T> resultList = new ArrayList<T>();
        if(!isUniqueStringAlreadyMade) setFirstUniqueStr(objVoList,parentUniqueString,currentExplodedDepth);
        if (bInclude) resultList.addAll((ArrayList<T>)objVoList);

        List<ObjectRootVO> parmObjVoList = new ArrayList<ObjectRootVO>();
        parmObjVoList.addAll((List<ObjectRootVO>)objVoList);
        OmcOQLApiRelatedLogVO apiLogVOIn = new OmcOQLApiRelatedLogVO();
        ArrayList<OmcOQLVariableParameter> variableParameterListIn = new ArrayList<OmcOQLVariableParameter>();
        
        List<T> relatedOjbectList = OmcOQLCoreGetRelatedObjectsAPI._getRelatedObjects(parmObjVoList,filterPattern,relationPattern,filterPattern,fromToDirection,selectPattern,wherePattern,paramPattern,groupByParamList,objectLimit,apiLogVOIn,variableParameterListIn,true,1,false,pagingEntity,paramRowBounds);
        // Advanced인 경우에는 바로 PagingList를 Return함.
        if(!NullUtil.isNone(groupByParamList)) return relatedOjbectList;
        setUniqueStrForFirstResult(parmObjVoList, (List<ObjectRootVO>)relatedOjbectList, ++currentExplodedDepth);
        // Paging 경우에는 Multi Level을 하지 않으므로 바로 PagingList를 Return함.
        if(!NullUtil.isNull(pagingEntity)) return relatedOjbectList;
        resultList.addAll(relatedOjbectList);
        List<ObjectRootVO> fromObjVoList = new ArrayList<ObjectRootVO>();
        List<ObjectRootVO> toObjVoList = new ArrayList<ObjectRootVO>();
        if(findDepth < 2) return resultList;
        seperateResult((List<ObjectRootVO>)relatedOjbectList,fromObjVoList,toObjVoList);
        int explodeCount = 1;
        while(explodeCount < findDepth){
            int fromSeq = 1;
            if(!NullUtil.isNone(fromObjVoList)){
                List<T> subFromOjbectList = OmcOQLCoreGetRelatedObjectsAPI._getRelatedObjects(fromObjVoList,filterPattern,relationPattern,filterPattern,GlobalConstants.FLAG_TYPE_FROM,selectPattern,wherePattern,paramPattern,null,objectLimit,apiLogVOIn,variableParameterListIn,true,1,false,pagingEntity,paramRowBounds);
                fromSeq = setUniqueStrForResultFrom(fromObjVoList,(List<ObjectRootVO>)subFromOjbectList,currentExplodedDepth+explodeCount,fromSeq);
                resultList.addAll(subFromOjbectList);
                fromObjVoList = new ArrayList<ObjectRootVO>();
                if(!NullUtil.isNone(subFromOjbectList)) fromObjVoList = (List<ObjectRootVO>)subFromOjbectList;
            }
            explodeCount++;
        }
        explodeCount = 1;
        while(explodeCount < findDepth){
            int fromSeq = 1;
            if(!NullUtil.isNone(toObjVoList)){
                List<T> subToOjbectList = OmcOQLCoreGetRelatedObjectsAPI._getRelatedObjects(toObjVoList,filterPattern,relationPattern,filterPattern,GlobalConstants.FLAG_TYPE_TO,selectPattern,wherePattern,paramPattern,null,objectLimit,apiLogVOIn,variableParameterListIn,true,1,false,pagingEntity,paramRowBounds);
                setUniqueStrForResultTo(toObjVoList,(List<ObjectRootVO>)subToOjbectList,currentExplodedDepth+explodeCount,fromSeq); 
                resultList.addAll(subToOjbectList);
                toObjVoList = new ArrayList<ObjectRootVO>();
                if(!NullUtil.isNone(subToOjbectList)) toObjVoList = (List<ObjectRootVO>)subToOjbectList;
            }
            explodeCount++;
        }
        return resultList;
    }
    /**
     * Object 리스트에 대한 전체 Related Object를 Return함.
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @return VO List
     */
    private static final <T> List<T> getRelationshipAdvanced(List<? extends ObjectRootVO> objVoList, String relationPattern, String filterPattern,String fromToDirection, String wherePattern, String paramPattern,List<OmcGroupByParamVO> groupByParamList){
        return OmcOQLCoreGetRelationShipAPI._getRelattionShip(objVoList,relationPattern,fromToDirection,filterPattern,"",EtcUtil.convertNullToString(wherePattern),paramPattern,groupByParamList,0,null,null,1,false);
    }
    /**
     * @param objVoList
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param pagingEntity
     * @return
     */
    private <T> List<T> getRelationshipPagingSub(ObjectRootVO objVo, String relationPattern, String filterPattern,String fromToDirection, String selectPattern, String wherePattern, String paramPattern, PagingEntity pagingEntity,RowBounds paramRowBounds){
        List<ObjectRootVO> objVoList = new ArrayList<ObjectRootVO>();
        objVoList.add(objVo);
        return OmcOQLCoreGetRelationShipAPI._getRelattionShip(objVoList,relationPattern,fromToDirection,filterPattern,EtcUtil.convertNullToString(selectPattern),EtcUtil.convertNullToString(wherePattern),paramPattern,null,0,pagingEntity,paramRowBounds,1,false);
    }
    /**
     * 
     *
     * @param objVo
     * @param relationPattern
     * @param filterPattern
     * @param fromToDirection
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @return
     */
    private <T> List<T> getRelationshipSub(ObjectRootVO objVo, String relationPattern, String filterPattern,String fromToDirection, String selectPattern, String wherePattern, String paramPattern,RowBounds paramRowBounds){
        return(getRelationshipPagingSub(objVo,relationPattern,filterPattern,fromToDirection,selectPattern,wherePattern,paramPattern,null,paramRowBounds));
    }
    /**
     * relation을 검색한다.
     * (relation 삭제 시, dom 내부적으로 사용함)
     *
     * @return
     */
    protected <T> List<T> getRelationList(String fromToDirection){
        RowBounds paramRowBounds = null;
        return(getRelationshipSub(this.getVo(),GlobalConstants.FLAG_TYPE_ALL,fromToDirection,GlobalConstants.FLAG_TYPE_ALL,null,null,null,paramRowBounds));
    }
    /**
     * 
     *
     * @return
     */
    protected <T> List<T> getRelationList(){
        return getRelationList(GlobalConstants.FLAG_TYPE_ALL);
    }
    /**
     * 
     *
     * Pattern사용법에 대한 것은 아래 PatternUsage Link참조
     * {@link ObjectRoot#dummyMethodForPattern(String, String, String, String, String, String, String, String, String, String, boolean, boolean, boolean, boolean, String, String, String, String, String, String, String, boolean, int) PatternUsage}
     *
     *
     * @return
     */
    private <T> List<T> getRelationShipSub(){
        return(getRelationObjectListSub(GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL));
    }
    /**
     * 
     *
     * @param sourceObj
     * @param map
     */
    @SuppressWarnings("unchecked")
    protected final void deleteRelationShipForObjectDelete(ObjectRoot sourceObj,Map<String, Object> map){

        Set<String> deleteObidSet = (HashSet<String>)map.get("deleteObidSet");
        if(deleteObidSet == null)  deleteObidSet = new HashSet<String>();
        deleteObidSet.add(sourceObj.getVo().getObid());
        List<BusinessRelationObjectVO> relObjVOList = sourceObj.getRelationShipSub();
        if (!NullUtil.isNone(relObjVOList)) {
            Map<String,Object> newMap = new HashMap<String,Object>();
            
            for(String key : map.keySet()){
                if(!key.equals("deleteObidSet")){
                    newMap.put(key, map.get(key));
                }
            }
            for (BusinessRelationObjectVO relObjVO : relObjVOList) {
                if(!StrUtil.in(relObjVO.getObid(), deleteObidSet)){
                    
                    newMap.put("deleteObidSet", deleteObidSet);
                    BusinessRelationObject deleteDom = (BusinessRelationObject)DomUtil.toDom(relObjVO);
                    deleteDom.deleteObject(newMap);       
                }
            }
        }
    }
    /**
     * Object를 검색한다.
     * 기준 VO + 타 object의 속성 검색이 가능하며, 기준 VO와 타 Object가 1:n 관계이면, 여러 개의 record(VO의 List)로 리턴한다.
     *
     * @param className 검색 기준이 되는 Class 명 (ex, PartFamily)
     * @param namePattern name 조건 (like 검색을 원하는 경우에는 "*"를 붙임)
     * @param revisionPattern revision 검색 조건
     * @param lifeCylePattern lifecycle 검색 조건
     * @param statePattern state 검색 조건
     * @param creatorPattern creator 검색 조건
     * @param modifierPattern modifier 검색 조건
     * @param ownerPattern
     * @param checkouterPattern
     * @param lockerPattern
     * @param firstOnly
     * @param latestOnly
     * @param lockedOnly
     * @param checkoutedOnly
     * @param selectPattern select 검색 조건
     * @param fromPattern from 검색 조건
     * @param wherePattern where 검색 조건
     * @param paramPattern parameter 조건
     * @param expandType (true/false)
     * @return VO List
     */
    private static final <T> T searchObject(String className, String namePattern, String revisionPattern,
            String lifeCylePattern, String statePattern, String creatorPattern, String modifierPattern,
            String ownerPattern, String checkouterPattern, String lockerPattern, boolean firstOnly, boolean latestOnly,
            boolean lockedOnly, boolean checkoutedOnly, String selectPattern, String fromPattern, String wherePattern,
            String paramPattern, boolean expandType){
        List<T> searchObjectList = ObjectRoot.searchObjectsWithHint(className, namePattern, revisionPattern, lifeCylePattern, statePattern,
                creatorPattern, modifierPattern, ownerPattern, checkouterPattern, lockerPattern, firstOnly, latestOnly,
                lockedOnly, checkoutedOnly, selectPattern, fromPattern, wherePattern, paramPattern, expandType,
                null, 0);
        if(searchObjectList.size() > 1) throw new FoundationException("");
        return(searchObjectList.get(0));
    }
    /**
     * 현재 VO가 Parameter로 전달된 parentClass를 상속받은 클래스인지 여부를 리턴한다.
     * - 전달된 parentClass가 상속관계에서 상위 Class이거나 자신의 Class이면 true
     * - 아니면 false
     *
     * @param parentClass
     * @return
     */
    public final boolean isKindOf(String parentClass){
        ObjectRootVO vo = getVo();
        if (!NullUtil.isNull(vo)) {
            if(vo.getClassName().equals(parentClass)) return true;
            ClassInfo classInfo = BaseFoundationUtil.getClassInfoByName(vo.getClassName());
            if (!NullUtil.isNull(classInfo)) {
                List<String> strList = classInfo.getUpperClassList();
                if(!NullUtil.isNone(strList)){
                    for (String str : strList) {
                        if (str.equals(parentClass)) {
                            return true;
                        }
                    }
                }else{
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }
    /**
     * String기준으로 Class의 종류인지를 Return한다. instanceof와 유사한 기능이라고 볼 수 있음.
     *
     * @param thisCalss
     * @param parentClass
     * @return
     */
    public static final boolean isKindOf(String thisCalss, String parentClass){
        if(thisCalss.equals(parentClass)) return true;
        ClassInfo classInfo = BaseFoundationUtil.getClassInfoByName(thisCalss);
        if (!NullUtil.isNull(classInfo)) {
            List<String> strList = classInfo.getUpperClassList();
            if(!NullUtil.isNone(strList)){
                for (String str : strList) {
                    if (str.equals(parentClass)) {
                        return true;
                    }
                }
            }else{
                return false;
            }
        } else {
            return false;
        }
        return false;
    }
    /**
     * dynamicAttributeGroup에 해당하는 DynamicAttribute 목록을 조회한다.
     *
     * @param dynamicAttributeGroup
     * @return
     */
    public final static List<VariableAttribute> getDynamicAttributeList(String dynamicAttributeGroup){
        List<VariableAttribute> result = BaseFoundationUtil.getDynamicAttributeList(dynamicAttributeGroup);
        return result;
    }
    /**
     * ChangeLog를 검색한다.
     *
     * @param changingCategory ChangeOwner,CheckOut
     * @param startChangedDate 검색조건(변경날짜 - 시작날짜)
     * @param endChangedDate 검색조건(변경날짜 - 종료날짜)
     * @return
     */
    public final List<ChangeLogVO> getChangedHistorys(String changingCategory, Date startChangedDate,
            Date endChangedDate){
        return getChangedHistorys(changingCategory, startChangedDate, endChangedDate, null, null, null, null, null);
    }
    /**
     * ChangeLog를 검색한다.
     *
     * @param changingCategory ChangeOwner,CheckOut
     * @param startChangedDate 검색조건(변경날짜 - 시작날짜)
     * @param endChangedDate 검색조건(변경날짜 - 종료날짜)
     * @param attribute01
     * @param attribute02
     * @param attribute03
     * @param attribute04
     * @param attribute05
     * @return
     */
    public final List<ChangeLogVO> getChangedHistorys(String changingCategory, Date startChangedDate,
            Date endChangedDate, String attribute01, String attribute02, String attribute03, String attribute04,
            String attribute05){
        List<ChangeLogVO> changeLogList = ObjectChangeLogManagement.getChangedHistorys(changingCategory, getVo().getObid(),
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
                startChangedDate, endChangedDate, attribute01, attribute02, attribute03, attribute04, attribute05);
        return changeLogList;
    }
    /**
     * ChangeLog를 검색한다.
     *
     * @param changingCategory ChangeOwner,CheckOut
     * @param targetNames Target Names (Relation:'-')
     * @param targetClass target class명 (','를 사용하여 여러 개 등록 가능)
     * @param changingUser change를 수행한 userID
     * @param startChangedDate 검색조건(변경날짜 - 시작날짜)
     * @param endChangedDate 검색조건(변경날짜 - 종료날짜)
     * @param attribute01
     * @param attribute02
     * @param attribute03
     * @param attribute04
     * @param attribute05
     * @return
     */
    public final static List<ChangeLogVO> searchChangedHistorys(String changingCategory, String targetNames,
            String targetClass, String changingUser, Date startChangedDate, Date endChangedDate, String attribute01,
            String attribute02, String attribute03, String attribute04, String attribute05){
        List<ChangeLogVO> changeLogList = ObjectChangeLogManagement.getChangedHistorys(changingCategory, null, targetNames,
                targetClass, changingUser, startChangedDate, endChangedDate, attribute01, attribute02, attribute03,
                attribute04, attribute05);
        return changeLogList;
    }
    /**
     * Relation Object에 대한 Sub Program
     *
     * @param direction
     * @param classFilter
     * @param fromClassFilter
     * @param toClassFilter
     * @return Relation Object VO List
     */
    @SuppressWarnings("unchecked")
    private <T> List<T> getRelationObjectListSub(String direction, String classFilter, String fromClassFilter, String toClassFilter){
        List<RelationTableInfo> relInfoList = CommonServiceUtil.getRelationTableObjList(this.getVo().getObid(),direction,classFilter,fromClassFilter,toClassFilter);
        HashMap<String, Object> map = new HashMap<String,Object>();
        
        ArrayList<OmcOQLComparator> comparator = new ArrayList<OmcOQLComparator>();
        comparator.add(new OmcOQLComparator("className",false));
        OmcSortUtil.sort(relInfoList, comparator);   

        if(!NullUtil.isNone(relInfoList)){
            String savedClass = "1";
            List<String> strList = new ArrayList<String>();
            for(RelationTableInfo info : relInfoList){
                if(!savedClass.equals(info.getClassName())){
                    if(!"1".equals(savedClass)){
                        map.put(savedClass, strList);
                        strList = new ArrayList<String>();
                    }
                }
                strList.add(info.getObid());
                savedClass = info.getClassName();
            }
            if(!"1".equals(savedClass)) map.put(savedClass, strList);
        }
        Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
        List<T> relList = new ArrayList<T>();
        while (iterator.hasNext()) {
            Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iterator.next();
            List<String> strList = (List<String>)entry.getValue();
            List<T> tempList = CommonServiceUtil.getObjectsWithOutData(strList,entry.getKey());
            relList.addAll(tempList);
        }
        return relList;
    }
    private static void setFirstUniqueStr(List<? extends ObjectRootVO> objVoList, String parentUniqueString, int currentExplodedDepth){
        int seq = 1;
        for(ObjectRootVO objVo : objVoList){
            String uniqueString = parentUniqueString + StrUtil.LPAD(seq++, 5, "0");
            objVo.setUniqueString(uniqueString);
            objVo.setUniqueStringParent(parentUniqueString);
            objVo.setDataFindingDirection("Self");
            objVo.setExplodedDepth(currentExplodedDepth+1);
        }
    }
    /**
     * obid List를 가지고 데이터를 찾음.
     *
     * @param obidList obid List
     * @param className Calss Name
     * @return VO List
     */
    public static <T> List<T> findObjectsWithObidList(List<String> obidList,String className){
        return CommonServiceUtil.getObjectsWithOutData(obidList,className);
    }
    /**
     * <pre>
     * obid List를 가지고 데이터를 찾음.
     * userList = BusinessObjectMaster.findObjectsWithObidList(StrUtil.convertSet2Str(userObidSet), ApplicationSchemaConstants.BIZCLASS_USERS);
     * </pre>
     * @param obidList obid List
     * @param className Calss Name
     * @return VO List
     */
    public static <T> List<T> findObjectsWithObidList(String obidList,String className){
        return CommonServiceUtil.getObjectsWithOutData(StrUtil.convertArrayToList(obidList.split(",")),className);
    }
    private static void setUniqueStrForFirstResult(List<ObjectRootVO> parmVoList, List<ObjectRootVO> resultVoList, int explodedDepth){
        int seq = 1;
        for(ObjectRootVO rsltVo : resultVoList){
            for(ObjectRootVO parmVo : parmVoList){
                if(rsltVo.getDataFindingDirection().equals(GlobalConstants.FLAG_TYPE_FROM)){
                    if(parmVo.getObid().equals(rsltVo.getOutData().get("rel_toObid"))){
                        rsltVo.setUniqueStringParent(parmVo.getUniqueString());
                        rsltVo.setUniqueString(parmVo.getUniqueString() + StrUtil.LPAD(seq++, 5, "0"));
                        rsltVo.setExplodedDepth(explodedDepth);
                    }

                }else{
                    if(parmVo.getObid().equals(rsltVo.getOutData().get("rel_fromObid"))){
                        rsltVo.setUniqueStringParent(parmVo.getUniqueString());
                        rsltVo.setUniqueString(parmVo.getUniqueString() + StrUtil.LPAD(seq++, 5, "0"));
                        rsltVo.setExplodedDepth(explodedDepth);
                    }
                }
            }
        }
    }
    private static int setUniqueStrForResultFrom(List<ObjectRootVO> parmVoList, List<ObjectRootVO> resultVoList,int explodedDepth, int fromSeq){
        for(ObjectRootVO rsltVo : resultVoList){
            for(ObjectRootVO parmVo : parmVoList){
                if(parmVo.getObid().equals(rsltVo.getOutData().get("rel_toObid"))){
                    rsltVo.setUniqueStringParent(parmVo.getUniqueString());
                    rsltVo.setUniqueString(parmVo.getUniqueString() + StrUtil.LPAD(fromSeq++, 5, "0"));
                    rsltVo.setExplodedDepth(explodedDepth);
                }
            }
        }
        return fromSeq;
    }
    private static int setUniqueStrForResultTo(List<ObjectRootVO> parmVoList, List<ObjectRootVO> resultVoList,int explodedDepth, int fromSeq){
        for(ObjectRootVO rsltVo : resultVoList){
            for(ObjectRootVO parmVo : parmVoList){
                if(parmVo.getObid().equals(rsltVo.getOutData().get("rel_fromObid"))){
                    rsltVo.setUniqueStringParent(parmVo.getUniqueString());
                    rsltVo.setUniqueString(parmVo.getUniqueString() + StrUtil.LPAD(fromSeq++, 5, "0"));
                    rsltVo.setExplodedDepth(explodedDepth);
                }
            }
        }
        return fromSeq;
    }
    private static void seperateResult(List<ObjectRootVO> resultVoList, List<ObjectRootVO> fromObjVoList,List<ObjectRootVO> toObjVoList){
        if (!NullUtil.isNone(resultVoList)) {
            for (ObjectRootVO obj : resultVoList) {
                String direction = obj.getDataFindingDirection();
                if(GlobalConstants.FLAG_TYPE_FROM.equals(direction)){
                    fromObjVoList.add(obj);
                }else{
                    toObjVoList.add(obj);
                }
            }
        }   
    }
    /**
     * 
     *
     * @param file
     */
    public static final void deleteTemporaryFile(File file){
        file.delete();
    }
    /**
     * 
     *
     * @return
     */
    public final String getObid(){
        return this.getVo().getObid();
    }
    /**
     * 
     *
     * @return
     */
    public final String getClassName(){
        return this.getVo().getClassName();
    }
    /**
     * 
     *
     * @return
     */
    public final Long getFlags(){
        return this.getVo().getFlags();
    }
    /**
     * 
     *
     * @return
     */
    public final String getOwner(){
        return this.getVo().getOwner();
    }
    /**
     * Locker를 Return
     *
     * @return
     */
    public final String getLocker(){
        return this.getVo().getLocker();
    }
    /**
     * 
     *
     * @return
     */
    public final String getCheckouter(){
        return this.getVo().getCheckouter();
    }
    /**
     * 
     *
     * @return
     */
    public final Date getCheckouted(){
        return this.getVo().getCheckouted();
    }
    /**
     * 
     *
     * @return
     */
    public final String getCreator(){
        return this.getVo().getCreator();
    }
    /**
     * 
     *
     * @return
     */
    public final Date getCreated(){
        return this.getVo().getCreated();
    }
    /**
     * 
     *
     * @return
     */
    public final String getModifier(){
        return this.getVo().getModifier();
    }
    /**
     * 
     *
     * @return
     */
    public final Date getModified(){
        return this.getVo().getModified();
    }
    /**
     * 
     *
     * @param attribute
     * @return
     */
    public final <T> T getAttributeValue(String attribute){
        return this.getVo().getAttribute(attribute);
    }
    /**
     * 
     *
     * @param attribute
     * @param value
     */
    public final void setAttributeValue(String attribute, Object value){
        this.getVo().setAttributeValue(attribute, value);
    }
    /**
     * Out Data의 Attribute 값을 Return함
     * 
     * @param attribute
     */
    public final <T> T getOutDataAttributeValue(String attribute){
        return this.getVo().getOutDataAttributeValue(attribute);
    }
    /**
     * Out Data에 값을 Put함.
     *
     * @param attribute
     * @param value
     */
    public final void setOutDataAttributeValue(String attribute, Object value){
        this.getVo().getOutData().put(attribute,value);
    }
    /**
     * VO List에서 attributePattern의 Distinct한 Value Set을 Return함.
     * Out Data인 경우 out.attribute형식을 attributePattern을 정의함.(fromObid,toObid,out.attribute)
     *
     * @param objectVOList
     * @param attributePattern
     * @param excludeEmpty
     */
    public static final Set<String> getDistinctValueSet(List<? extends ObjectRootVO> objectVOList, String attributePattern, boolean excludeEmpty){
        Set<String> strSet = StrUtil.convertArrayToSet(attributePattern.split(","));
        Set<String> rtnSet = new HashSet<String>();
        StringBuffer strBuf = new StringBuffer();
        String attr = "";
        for(ObjectRootVO vo : objectVOList){
            strBuf.setLength(0);
            boolean isFirst = true;
            for(String attribute : strSet){
                attr = attribute;
                if(!isFirst) strBuf.append(OmcSystemConstants.DELIMINATOR_VALUE_GENERAL);
                if(attr.startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                    attr = attr.substring(4);
                    strBuf.append((String)vo.getOutDataAttributeValue(attr));
                }else{
                    strBuf.append((String)vo.getAttribute(attr));
                }
                isFirst = false;
            }
            if(excludeEmpty){
                if(!StrUtil.isEmpty(strBuf.toString())) rtnSet.add(strBuf.toString());
            }else{
                rtnSet.add(strBuf.toString());
            }
        }
        return rtnSet;
    }
    /**
     * <pre>
     * attributePattern기준으로 Map DB를 Return함.
     *  {@code
        List<HrCompetencyVO> hrCompetencyVOList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_HRCOMPETENCY, StrUtil.convertSet2Str(subCompetencySet));
        Map<String, ObjectRootVO> hrCompetencyVOMapDB = ObjectRoot.makeVoDB(hrCompetencyVOList, "names");
        }
     * </pre>
     * @param objectVOList VO List
     * @param attributePattern Attribute Pattern
     * @return Map DB
     */
    public static final Map<String, ObjectRootVO> makeVoDB(List<? extends ObjectRootVO> objectVOList, String attributePattern){
        List<String> strSet = StrUtil.convertArrayToList(attributePattern.split(","));
        StringBuffer strBuf = new StringBuffer();
        String attr = "";
        Map<String,ObjectRootVO> mapDB = new HashMap<String,ObjectRootVO>();
        for(ObjectRootVO vo : objectVOList){
            strBuf.setLength(0);
            boolean isFirst = true;
            for(String attribute : strSet){
                attr = attribute;
                if(!isFirst) strBuf.append(OmcSystemConstants.DELIMINATOR_VALUE_GENERAL);
                if(attr.startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                    attr = attr.substring(4);
                    strBuf.append((String)vo.getOutDataAttributeValue(attr));
                }else{
                    strBuf.append((String)vo.getAttribute(attr));
                }
                isFirst = false;
            }
            mapDB.put(strBuf.toString(), vo);
        }
        return mapDB;
    }
    /**
     * <pre>
     * attributePattern기준의 Key값을 Return함.Map DB에서 값을 찾을때 사용되어짐.
     * RollingForcastVO savedDBVO = (RollingForcastVO)rollingForcastDB.get(ObjectRoot.makeVoDBKey("year,basicMonth,managementGroup,departmentCode,accountCode"));
     * </pre>
     * 
     * @param attributePattern
     * @return
     */
    public final String makeVoDBKey(String attributePattern){
        return this.getVo().makeVoDBKey(attributePattern);
    }
    /**
     * <pre>
     * keyAttribute를 Key값으로 하여 VO List를 만들어 Map을 만듬
     *  ★★★ Sample ★★★
     *  {@code
        List<AccountVO> accountVOList = ManagementGroup.getAccountListByManagement(managementGoupObidSet, true);
        Map<String, List<ObjectRootVO>> accoundDBForMgr = ObjectRoot.makeObjectListDB(accountVOList, "out.managementGroupObid");
     *  }
     * </pre>
     * @param objectVOList VO List
     * @param keyAttribute Key Attribute
     * @return Key Attribute값으로 기준의 Map
     */
    public static final  Map<String, List<? extends ObjectRootVO>> makeObjectListDB(List<? extends ObjectRootVO> objectVOList, String keyAttribute){
        String keyAttribute_w = keyAttribute;
        SortUtil.sort(objectVOList, keyAttribute_w,false);
        boolean isOutDate = false;
        if(keyAttribute_w.startsWith("out.")){
            keyAttribute_w = keyAttribute_w.substring(4);isOutDate = true;
        }
        Map<String, List<? extends ObjectRootVO>> returnMap = new HashMap<String, List<? extends ObjectRootVO>>();
        List<ObjectRootVO> tempList = new ArrayList<ObjectRootVO>();
        String keyValueSaved = "";String keyValueTemp = "";boolean isFirst = true;
        for(ObjectRootVO vo : objectVOList){
            if(isOutDate){
                keyValueTemp = (String)vo.getOutDataAttributeValue(keyAttribute_w);
            }else{
                keyValueTemp = (String)vo.getAttribute(keyAttribute_w);
            }
            if(StrUtil.isEmpty(keyValueTemp)) throw new FoundationException("[Foundation]Key value canot be Empty");
            if(!isFirst && !keyValueSaved.equals(keyValueTemp)){
                returnMap.put(keyValueSaved, tempList);
                tempList = new ArrayList<ObjectRootVO>();
            }
            tempList.add(vo);
            keyValueSaved = keyValueTemp;
            isFirst = false;
        }
        if(!isFirst && !NullUtil.isNone(tempList)) returnMap.put(keyValueSaved, tempList);
        return returnMap;
    }
    /**
     * objectVOList의 특정 Attribute(attrList)에 대해서 className으로 Setting함.
     *
     * @param objectVOList VO List
     * @param attrList Attribute List
     * @see ObjectRoot#setClassNameForBatch(List objectVOList, String attrList, String className)
     */
    public static final void setClassNameForBatch(List<? extends ObjectRootVO> objectVOList, String attrList){
        setClassNameForBatch(objectVOList,attrList,"");
    }
    /**
     * <pre>
     * 
     * objectVOList의 특정 Attribute(attrList)에 대해서 className으로 Setting함.
     * ★★★ Example ★★★
     * {@code
        List<ProjectUserPlanMMVO> pureDeletedMonthPerUserVOList = new ArrayList<ProjectUserPlanMMVO>();
        List<ProjectUserPlanMMVO> pureModifyMonthPerUserVOList = new ArrayList<ProjectUserPlanMMVO>();
        List<ProjectUserPlanMMVO> pureCreateMonthPerUserVOList = new ArrayList<ProjectUserPlanMMVO>();

        for(ProjectUserPlanMMVO vo : toBeMonthPerUserVOList){
            String mmCRUDFlag = (String)vo.getOutDataAttributeValue("mmCRUDFlag");
            if(!StrUtil.isEmpty(mmCRUDFlag)){
                if(mmCRUDFlag.equals("delete")) pureDeletedMonthPerUserVOList.add(vo);
                if(mmCRUDFlag.equals("modify")) pureModifyMonthPerUserVOList.add(vo);
                if(mmCRUDFlag.equals("create")) pureCreateMonthPerUserVOList.add(vo);
            }
        }
        if(!NullUtil.isNone(pureDeletedMonthPerUserVOList)) ObjectRoot.deleteObjectSetBatch(pureDeletedMonthPerUserVOList);
        if(!NullUtil.isNone(pureModifyMonthPerUserVOList)) {
            Set<String> attributes = new HashSet<String>();
            attributes.add("manMonth");
            ObjectRoot.modifyObjectSetBatch(pureDeletedMonthPerUserVOList, attributes);
        }
        ObjectRoot.setClassNameForBatch(pureCreateMonthPerUserVOList, "fromObid:fromClass,toObid:toClass",ApplicationSchemaConstants.RELCLASS_PROJECTUSERPLANMM);
        if(!NullUtil.isNone(pureCreateMonthPerUserVOList)) ObjectRoot.createObjectSetBatch(pureCreateMonthPerUserVOList);
     * }
     * 
     * </pre>
     * @param objectVOList
     * @param attrList
     * @param className
     */
    public static final void setClassNameForBatch(List<? extends ObjectRootVO> objectVOList, String attrList, String className){
        boolean isSetClassName = false;
        if(!StrUtil.isEmpty(className)) isSetClassName = true;
        String[] attrListArray = attrList.split(Pattern.quote(":"));
        Set<String> obidSet = new HashSet<String>();
        for(int i = 0; i < attrListArray.length ; i++){
            String[] attrArray = attrListArray[i].split(",");
            for(ObjectRootVO vo : objectVOList){
                obidSet.add((String)vo.getAttribute(attrArray[0]));
            }
        }
        List<KeyInfo> keyInfoVOList = CommonServiceUtil.getClassNameWithObidSet(obidSet);
        Map<String,KeyInfo> mapDB = new HashMap<String,KeyInfo>();
        for(KeyInfo vo : keyInfoVOList) mapDB.put(vo.getObid(), vo);
        boolean isFirst = true;
        for(int i = 0; i < attrListArray.length ; i++){
            String[] attrArray = attrListArray[i].split(",");
            for(ObjectRootVO vo : objectVOList){
                if(isFirst && isSetClassName) vo.setClassName(className);
                vo.setAttributeValue(attrArray[1], (mapDB.get((String)vo.getAttribute(attrArray[0]))).getClassName());
            }
            isFirst = false;
        }
    }
    /*★★★★★Java Doc내용 절대 삭제하지 마세요★★★★★*/
    /**
     * API에서 사용되어지는 Pattern에 대한 사용법 설명을 위한 Dummy Method
     *
     * 모든 Pattern에서 GlobalConstants.FLAG_TYPE_ALL(*)는 해당 모든 것을 의미함.
     *
     * @param className 추상 Class인 경우에는 하위 Class를 전부 포함하게 되어짐.
     * 여러 개의 Class를 인자로 넘길 경우에는 Class1,Class2....형식(콤마 구분자로 해서 여러개를 넘길 수 있음)
     *<p><p> 
     * @param namePattern name에 대한 Pattern은 다은것과는 좀 다르게 해석되어짐
     * <pre>
     * Class Name Pattern과 유사하게 여러개의 Name을 콤마 구분자로 넘길수 있음.(ex: name1,name2)
     * 이경우 SQL에서는 in연산자로 자동 해석되어짐. 즉 name에는 ","를 절대 사용하면 안됨.
     * 1)"*PJT" : like '%PJT'
     * 2)"*PJT%" : like '%PJT%' 
     * 3)"ABCPJT001,PJT*,HHHJT001" : (names = 'ABCPJT001' or names = 'HHHJT001' or names like 'PJT%')
     * 4)GlobalConstants.FLAG_TYPE_ALL을 넘기는 경우 조건절이 만들어지지 않는다.
     * </pre>
     * @param revisionPattern Class Name Pattern과 유사하게 여러개의 Name을 콤마 구분자로 넘길수 있음.(ex: 1.0,1.1)
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX
     * GlobalConstants.FLAG_TYPE_ALL인경우에는 where condition이 만들어지지 않음
     * 1)"1.*": revision like '1.%'
     * 2)"1.0": revision = '1.0'
     * 3)"1.0,1.1": revision in ('1.0','1.1')
     * 4)"1.0,2.*": revision in ('1.0','2.*') name처럼 equal과 or를 혼용할 수는 없음.
     * </pre>
     * @param lifeCylePattern revisionPattern Rule과 동일
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX
     * </pre>
     * @param statePattern revisionPattern Rule과 동일
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX
     * </pre>
     * @param creatorPattern revisionPattern Rule과 동일
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX
     * </pre>
     * @param modifierPattern revisionPattern Rule과 동일
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX
     * </pre>
     * @param ownerPattern revisionPattern Rule과 동일
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX
     * </pre>
     * @param checkouterPattern revisionPattern Rule과 동일
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX
     * </pre>
     * @param lockerPattern revisionPattern Rule과 동일
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX
     * </pre>
     * @param firstOnly revision이 있는 BusinessObject에 대해서는 적용되는 것으로서 
     * true인경우 nextObid = '1'로 적용되어짐.(First Revision Object)
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX에 적용
     * BusinessObject가 아닌 경우에는 무시함.
     * </pre>
     * @param latestOnly revision이 있는 BusinessObject에 대해서는 적용되는 것으로서
     * true인경우 previousObid = '1'로 적용되어짐.(Latest Revision Object)
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX에 적용
     * </pre>
     * @param lockedOnly lock되어진 Object Only. locker <> '1
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX에 적용'
     * </pre>
     * @param checkoutedOnly checkout되어진 Object Only. checkoutor <> '1'
     * <pre>
     * 적용 API: findObjectXXXXXX, searchObjectXXXXXX에 적용
     * </pre>
     * @param selectPattern Database에서 데이터를 select할 내용을 정의하는 Patttern
     * @param fromPattern searchObjects에서 사용되는 Pattern으로서 sql에서 from에 해당하는 Class를 정의하는 
     * Pattern
     * @param wherePattern sql에서 where에 해당하는 Class를 정의하는 Pattern
     * @param paramPattern where에 대한 parameter를 정의하는 Pattern
     * @param relationPattern className과 같은 개념으로 적용되어짐.즉 추상 뿐만 아니라 콤마로 여러개의 Class를 넘길 수 있음(Relation Class만 넘길수 있음)
     * <pre>
     * 적용 API: getRelXXXXXX
     * 1) "RelationClass01"
     * 2) GlobalConstants.FLAG_TYPE_ALL 가능
     * 3) "RelationClass01,RelationClass02"
     * 4) "RelationClass*" Like는 지원하지 않음
     * </pre>
     * @param filterPattern className과 같은 개념으로 적용되어짐.즉 추상 뿐만 아니라 콤마로 여러개의 Class를 넘길 수 있음(Relation/Business Class 둘가 넘길수 있음) 
     * <pre>
     * 적용 API: getRelXXXXXX
     * 1) "RelationClass01"
     * 2) GlobalConstants.FLAG_TYPE_ALL 가능
     * 3) "RelationClass01,RelationClass02,BizClass01"
     * 4) "RelationClass*" Like는 지원하지 않음
     * </pre>
     * @param fromToDirection 
     * 적용 API: getRelXXXXXX
     * GlobalConstants.FLAG_TYPE_FROM: 자신의 Object기준으로 From쪽에 있는 Object 정의
     * GlobalConstants.FLAG_TYPE_TO: 자신의 Object기준으로 To쪽에 있는 Object 정의
     * GlobalConstants.FLAG_TYPE_ALL: 자신의 Object기준으로 양쪽(From,To)에 있는 Object 정의
     * </pre>
     * @param expandType 항상 true로 적용되어져 있음.
     * @param objectLimit 미적용
     */
    public void dummyMethodForPattern(String className, 
                                      String namePattern, 
                                      String revisionPattern,
                                      String lifeCylePattern, 
                                      String statePattern, 
                                      String creatorPattern, 
                                      String modifierPattern,
                                      String ownerPattern, 
                                      String checkouterPattern, 
                                      String lockerPattern, 
                                      boolean firstOnly, 
                                      boolean latestOnly,
                                      boolean lockedOnly, 
                                      boolean checkoutedOnly, 
                                      String selectPattern, 
                                      String fromPattern, 
                                      String wherePattern,
                                      String paramPattern,
                                      String relationPattern, 
                                      String filterPattern, 
                                      String fromToDirection,
                                      boolean expandType, 
                                      int objectLimit){
    }
}
