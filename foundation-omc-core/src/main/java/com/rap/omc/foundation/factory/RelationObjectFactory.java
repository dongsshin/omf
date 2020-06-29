/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : RelationObjectFactory.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 16. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.factory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.foundation.CommonServiceUtil;
import com.rap.omc.framework.exception.FoundationException;

/**
 * <pre>
 * Class : RelationObjectFactory
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class RelationObjectFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(RelationObjectFactory.class);

    public static BusinessRelationObjectVO createRelation(String className, String fromObid, String toObid, Map<String, Object> attributes){
        Map<String, Object> map = new HashMap<String, Object>();
        return createRelation(className,fromObid,toObid,attributes,map);
    }
    public static BusinessRelationObjectVO createRelation(String className, String fromObid, String toObid, Map<String, Object> attributes, Map<String, Object> map){
        ObjectRootVO fromObject = CommonServiceUtil.getObject(fromObid,false);
        ObjectRootVO toObject = CommonServiceUtil.getObject(toObid,false);
        if (NullUtil.isNull(fromObject)) throw new FoundationException("omc.error.validate.objNull", new Object[] {fromObid});
        if (NullUtil.isNull(toObject)) throw new FoundationException("omc.error.validate.objNull", new Object[] {toObid});
        BusinessRelationObjectVO result = null;
        result = createRelation(className, fromObject, toObject, attributes,map);
        return result;
    }
    public static BusinessRelationObjectVO createRelation(String className, ObjectRootVO fromObj, ObjectRootVO toObj, Map<String, Object> attributes){
        Map<String, Object> map = new HashMap<String, Object>();
        return createRelation(className,fromObj,toObj,attributes,map);
    }
    public static BusinessRelationObjectVO createRelation(String className, ObjectRootVO fromObj, ObjectRootVO toObj, Map<String, Object> attributes,Map<String, Object> map){
        BusinessRelationObjectVO result = create(className, fromObj, toObj, attributes,map);
        return result;
    }
    private static BusinessRelationObjectVO create(String className, ObjectRootVO fromObj, ObjectRootVO toObj, Map<String, Object> attributes, Map<String, Object> map){
        BusinessRelationObjectVO result = null;
        String fullClassName = BaseFoundationUtil.getPackageInfo(className) + className + "VO";
        try {
            Class<?> cls = Class.forName(fullClassName);
            Object newObject = cls.newInstance();

            if (newObject instanceof BusinessRelationObjectVO) {
                BusinessRelationObjectVO relObj = (BusinessRelationObjectVO)newObject;
                BusinessRelationObject   newDom = (BusinessRelationObject)DomUtil.toDom(relObj);
                newDom.initialize();
                relObj.setFlags(relObj.getFlags());
                relObj.setFromClass(fromObj.getClassName());
                relObj.setFromObid(fromObj.getObid());
                relObj.setToClass(toObj.getClassName());
                relObj.setToObid(toObj.getObid());
                convertMapToObject(attributes, relObj);
                result = relObj;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.warn(e.getMessage());
            throw new FoundationException("omc.error.class.newInstance", e);
        }
        if (!NullUtil.isNull(result)) {
            BusinessRelationObject relationDom = (BusinessRelationObject)DomUtil.toDom(result);
            relationDom.createObject(map);
        }
        return result;
    }
    private static Object convertMapToObject(Map<?, ?> map, Object objClass){
        String keyAttribute = null;
        String setMethodString = "set";
        String methodString = null;
        if (NullUtil.isNull(map)) { return null; }
        Iterator<?> itr = map.keySet().iterator();
        while (itr.hasNext()) {
            keyAttribute = (String)itr.next();
            methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
            try {
                Object objValue = map.get(keyAttribute);
                Method method = objClass.getClass().getMethod(methodString, objValue.getClass());
                method.invoke(objClass, objValue);
            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
            }
        }
        return objClass;
    }
}