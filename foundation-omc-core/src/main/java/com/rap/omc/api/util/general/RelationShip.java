/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : RelationshipUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 16. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util.general;

import java.util.HashMap;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.foundation.factory.RelationObjectFactory;


/**
 * 
 * <pre>
 * Class : RelationShip
 * Description : RelationShip에 대한 생성 및 삭제를 위한 Utility 형식의 Class
 * connect시 내부적으로는 createObject가 call되어지고 
 * disconnect시에는 deleteObject가 call되어짐
 * </pre>
 * 
 * @author s_dongsshin
 */
public class RelationShip {

    /**
     * Create relationship
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObid from Object의 obid
     * @param toObid to Object의 obid
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @return Created relation object
     */
    public static BusinessRelationObjectVO connect(String className, String fromObid, String toObid, Map<String, Object> attributes){
        return connect(className, fromObid, toObid, attributes,new HashMap<String,Object>());
    }
    /**
     * Create relationship
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObid from Object의 obid
     * @param toObid to Object의 obid
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값\
     * @param map createObject의 parameter
     * @return Created relation object
     */
    public static BusinessRelationObjectVO connect(String className, String fromObid, String toObid, Map<String, Object> attributes, Map<String,Object> map){
        return RelationObjectFactory.createRelation(className, fromObid, toObid, attributes, map);
    }
    /**
     * Create B2B relationship
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObj from Business Object
     * @param toObj to Business Object
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @return Created relation object
     */
    public static BusinessRelationObjectVO connect(String className, BusinessObjectRootVO fromObj, BusinessObjectRootVO toObj, Map<String, Object> attributes){
        return connect(className,fromObj,toObj,attributes,new HashMap<String,Object>());
    }
    /**
     * B2B Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObj from Business Object
     * @param toObj to Business Object
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @param map createObject의 parameter
     * @return Created relation object
     */
    public static BusinessRelationObjectVO connect(String className, BusinessObjectRootVO fromObj, BusinessObjectRootVO toObj, Map<String, Object> attributes,Map<String,Object> map){
        BusinessRelationObjectVO result = RelationObjectFactory.createRelation(className, fromObj, toObj, attributes,map);
        return result;
    }
    /**
     * B2R Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObj from Business Object
     * @param toObj to Relation Object
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @return Created relation object
     */
    public static BusinessRelationObjectVO connect(String className, BusinessObjectRootVO fromObj, BusinessRelationObjectVO toObj, Map<String, Object> attributes){
        return connect(className,fromObj,toObj,attributes,new HashMap<String,Object>());
    }
    /**
     * B2R Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObj from Business Object
     * @param toObj to Relation Object
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @param map createObject의 parameter
     * @return Created relation object
     */
    public static BusinessRelationObjectVO connect(String className, BusinessObjectRootVO fromObj, BusinessRelationObjectVO toObj, Map<String, Object> attributes,Map<String,Object> map){
        BusinessRelationObjectVO result = RelationObjectFactory.createRelation(className, fromObj, toObj, attributes,map);
        return result;
    }
    /**
     * R2B Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObj from Relation Object
     * @param toObj to Business Object
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @return Created relation object
     */
    public static BusinessRelationObjectVO connect(String className, BusinessRelationObjectVO fromObj, BusinessObjectRootVO toObj, Map<String, Object> attributes){
        return connect(className,fromObj,toObj,attributes,new HashMap<String,Object>());
    }
    /**
     * R2B Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObj from Relation Object
     * @param toObj to Business Object
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @param map createObject의 parameter
     * @return Created relation object
     */
    public static BusinessRelationObjectVO connect(String className, BusinessRelationObjectVO fromObj, BusinessObjectRootVO toObj, Map<String, Object> attributes,Map<String,Object> map){
        BusinessRelationObjectVO result = RelationObjectFactory.createRelation(className, fromObj, toObj, attributes,map);
        return result;
    }
    /**
     * R2R Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObj from Relation Object
     * @param toObj to Relation Object
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @return Created relation object
     */
    public static BusinessRelationObjectVO connect(String className, BusinessRelationObjectVO fromObj, BusinessRelationObjectVO toObj, Map<String, Object> attributes){
        return connect(className,fromObj,toObj,attributes,new HashMap<String,Object>());
    }
    /**
     * R2R Relation을 생성한다.
     *
     * @param className 생성하고자 하는 Relation 이름
     * @param fromObj from Relation Object
     * @param toObj to Relation Object
     * @param attributes 생성하고자 하는 Relation에 설정하고자 하는 attribute 값
     * @return Created relation object
     */
    public static BusinessRelationObjectVO connect(String className, BusinessRelationObjectVO fromObj, BusinessRelationObjectVO toObj, Map<String, Object> attributes, Map<String,Object> map){
        BusinessRelationObjectVO result = RelationObjectFactory.createRelation(className, fromObj, toObj, attributes,map);
        return result;
    }

    /**
     * Disconnect RelationShip.
     *
     * @param targetObj Disconnected object
     */
    public static void disconnect(BusinessRelationObjectVO targetObj){
        disconnect(targetObj,new HashMap<String,Object>());
    }
    /**
     * Disconnect RelationShip.
     *
     * @param targetObj Disconnected object
     * @param map Parameter for deleteObject. 
     */
    public static void disconnect(BusinessRelationObjectVO targetObj, Map<String,Object> map){
        BusinessRelationObject targetDom = (BusinessRelationObject)DomUtil.toDom(targetObj);
        targetDom.deleteObject(map);
    }
}
