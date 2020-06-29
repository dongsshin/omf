/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CommonServiceUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 27. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.util.foundation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.classes.model.ObjectTableVO;
import com.rap.omc.foundation.classes.model.RelationTableInfo;
import com.rap.omc.foundation.classes.service.CommonService;
import com.rap.omc.foundation.common.model.KeyInfo;
import com.rap.omc.api.util.spring.SpringFactoryUtil;



/**
 * <pre>
 * Class : CommonServiceUtil
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class CommonServiceUtil {

    private CommonService commonService;

    private static CommonServiceUtil cInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static CommonServiceUtil getInstance(){
        if (cInstance == null) {
            cInstance = new CommonServiceUtil();
            cInstance.commonService = (CommonService)SpringFactoryUtil.getBean("commonService");
        }
        return cInstance;
    }
    public static Map<String,Object> createObjectSet(List<? extends ObjectRootVO> inputList){
        return(getInstance().commonService.createObjectSet(inputList));
    }
    public static void deleteObjectSet(List<? extends ObjectRootVO> inputList){
        getInstance().commonService.deleteObjectSet(inputList);
    }
    public static void modifyObjectBatch(List<? extends ObjectRootVO> inputList,Set<String> attributes){
        getInstance().commonService.modifyObjectBatch(inputList,attributes);
    }
    public static <T> T getObject(ObjectRootVO searchInfo){
        return getInstance().commonService.getObject(searchInfo);
    }
    public static <T> List<T> getSimpleObjects(String className, List<? extends ObjectRootVO> inputList, Set<String> attributes){
        return getInstance().commonService.getSimpleObjects(className,inputList,attributes);
    }
    public static <T> T getObject(String obid){
        return getInstance().commonService.getObject(obid);
    }

    public static <T> T getObject(String obid,boolean withOutData){
        return getInstance().commonService.getObject(obid,withOutData);
    }
    
    public static <T> List<T> getRevisionObjects(String queryId, String className, String names){
        return getInstance().commonService.getRevisionObjects(queryId,className,names);
    }
    
    public static <T> T getObject(String queryId, String obid){
        return getInstance().commonService.getObject(queryId, obid);
    }
    public static List<RelationTableInfo> getAllRelationShipForDeleteObject(String obid){
        return getInstance().commonService.getAllRelationShipForDeleteObject(obid);
    }
    public static <T> T getObjectWithOutData(String obid) {
        return getInstance().commonService.getObjectWithOutData(obid);
    }
    public static <T> List<T> getObjectsWithOutData(List<String> obidList, String className){
        return getInstance().commonService.getObjectsWithOutData(obidList,className);
    }

    public static List<RelationTableInfo> getRelationTableObjList(String obid, String direction, String classFilter, String fromClassFilter, String toClassFilter){
        return getInstance().commonService.getRelationTableObjList(obid,direction,classFilter,fromClassFilter,toClassFilter);
    }
    public static List<RelationTableInfo> getRelationTableObjList(String obid){
        return getInstance().commonService.getRelationTableObjList(obid,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL);
    }
    public static List<RelationTableInfo> getRelationTableObjList(String obid, String direction, String classFilter){
        return getInstance().commonService.getRelationTableObjList(obid,direction,classFilter,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL);
    }
    public static List<RelationTableInfo> getRelationTableObjList(String obid, String direction){
        return getInstance().commonService.getRelationTableObjList(obid,direction,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL);
    }
    public static <T> List<T> getObjectList(String queryId, String obid) {
        return getInstance().commonService.getObjectList(queryId, obid);
    }
    
    public static List<FilesVO> getFileObjectList(FilesVO file){
        return getInstance().commonService.getFileObjectList(file);
    }

    public static void createObject(ObjectRootVO input){
        getInstance().commonService.createObject(input);
    }

    public static void modifyObject(ObjectRootVO input){
        getInstance().commonService.modifyObject(input);
    }

    public static void updateObject(ObjectRootVO input){
        getInstance().commonService.updateObject(input, true);
    }
    
    public static void updateObject(ObjectRootVO input, boolean bIncludeAll){
        getInstance().commonService.updateObject(input, bIncludeAll);
    }

    public static void changeObject(ObjectRootVO input, boolean isOwner){
        getInstance().commonService.changeObject(input, isOwner);
    }

    public static void changeObjectWithKeyTable(ObjectRootVO input){
        getInstance().commonService.changeObjectWithKeyTable(input);
    }
    
    public static void changeObjectWithKeyTableForDefloat(ObjectRootVO input){
        getInstance().commonService.changeObjectWithKeyTableForDefloat(input);
    }
    public static void changeRelationObjectWithKeyTable(BusinessRelationObjectVO input){
        getInstance().commonService.changeRelationObjectWithKeyTable(input);
    }
    public static void deleteObject(ObjectRootVO input){
        getInstance().commonService.deleteObject(input);
    }
    public void deleteObject(String obid){
        getInstance().commonService.deleteObject(obid);
    }
    public static List<FilesVO> getFileObjects(FilesVO vo) {
        return(getInstance().commonService.getFileObjects(vo));
    }
    public static List<ObjectTableVO> getObjectTableForClassChange(BusinessObjectRootVO vo) {
        return(getInstance().commonService.getObjectTableForClassChange(vo));
    }
    public static void updateRelationForClassChangeFrom(Map<String,Object> map){
        getInstance().commonService.updateRelationForClassChangeFrom(map);
    }
    public static void updateRelationForClassChangeTo(Map<String,Object> map){
        getInstance().commonService.updateRelationForClassChangeTo(map);
    }
    public static void changeRealtionObject(ObjectRootVO input){
        getInstance().commonService.changeRealtionObject(input);
    }
    public static List<KeyInfo> getClassNameWithObidList(List<String> obidList){
        return getInstance().commonService.getClassNameWithObidList(obidList);
    }
    public static List<KeyInfo> getClassNameWithObidSet(Set<String> obidSet){
        return getInstance().commonService.getClassNameWithObidSet(obidSet);
    }
}
