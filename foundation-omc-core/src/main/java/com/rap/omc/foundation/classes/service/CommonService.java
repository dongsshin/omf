/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CommonService.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.foundation.classes.model.ObjectTableVO;
import com.rap.omc.foundation.classes.model.RelationTableInfo;
import com.rap.omc.foundation.common.model.KeyInfo;
import com.rap.omc.schema.object.model.OmcSchemaPropertyVO;

/**
 * <pre>
 * Class : CommonService
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public interface CommonService {
    public Map<String,Object> createObjectSet(List<? extends ObjectRootVO> inputList);
    
    public void deleteObjectSet(List<? extends ObjectRootVO> inputList);
    
    public void modifyObjectBatch(List<? extends ObjectRootVO> inputList,Set<String> attributes);
    
    public <T> List<T> getSimpleObjects(String className, List<? extends ObjectRootVO> inputList, Set<String> attributes);
    
    public <T> T getObject(ObjectRootVO searchInfo);

    public <T> T getObject(String obid);
    
    public <T> T getObject(String obid,boolean withOutData);

    public <T> T getObject(String queryId, String obid);
    
    public <T> List<T> getRevisionObjects(String queryId, String className, String names);

    public <T> T getObjectWithOutData(String obid);
    
    public <T> List<T> getObjectsWithOutData(List<String> obidList, String className);
    
    public List<RelationTableInfo> getRelationTableObjList(String obid, String direction, String classFilter, String fromClassFilter, String toClassFilter);
    
    public List<RelationTableInfo> getAllRelationShipForDeleteObject(String obid);
        
    public <T> List<T> getObjectList(String queryId, String obid);

    public List<FilesVO> getFileObjectList(FilesVO file);

    public void createObject(ObjectRootVO input);

    public void modifyObject(ObjectRootVO input);

    public void updateObject(ObjectRootVO input, boolean bIncludeAll);

    public void changeObject(ObjectRootVO input, boolean isOwner);

    public void changeObjectWithKeyTable(ObjectRootVO input);
    
    public void changeObjectWithKeyTableForDefloat(ObjectRootVO input);
    
    public void changeRelationObjectWithKeyTable(BusinessRelationObjectVO input);
    
    public void deleteObject(ObjectRootVO input);

    public void deleteObject(String obid);

    public List<OmcSchemaPropertyVO> getVariableList();
    
    public List<FilesVO> getFileObjects(FilesVO vo);
    
    public List<ObjectTableVO> getObjectTableForClassChange(BusinessObjectRootVO vo);
    
    public void updateRelationForClassChangeFrom(Map<String,Object> map);

    public void updateRelationForClassChangeTo(Map<String,Object> map);
    
    public void changeRealtionObject(ObjectRootVO input);
    
    public List<KeyInfo> getClassNameWithObidList(List<String> obidList);

    public List<KeyInfo> getClassNameWithObidSet(Set<String> obidSet);
}
