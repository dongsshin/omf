/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CommonServiceImpl.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ObjectTableVO;
import com.rap.omc.foundation.classes.model.RelationTableInfo;
import com.rap.omc.foundation.classes.service.CommonService;
import com.rap.omc.foundation.common.model.KeyInfo;
import com.rap.omc.foundation.common.model.ObjectTableKeyVO;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.dom.OmcSchemaProperty;
import com.rap.omc.schema.object.model.OmcSchemaPropertyVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcDBMSConstants;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.foundation.FoundationValidationUtil;
import com.rap.omc.util.foundation.QueryStringUtil;

/**
 * <pre>
 * Class : CommonServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;
    /**
     * 
     * 
     * @param inputList 생성할 Object VO List
     * @return 생성되어진 Object List(Business Object/Business Object Master/Relation Object를 구분해서 Return함.
     * @see omc.foundation.classes.service.CommonService#createObjectSet(java.util.List)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> createObjectSet(List<? extends ObjectRootVO> inputList){

        List<ObjectRootVO> relObjList      = new ArrayList<ObjectRootVO>();
        List<ObjectRootVO> bizMasterObjList= new ArrayList<ObjectRootVO>();
        List<ObjectRootVO> bizObjList      = new ArrayList<ObjectRootVO>();
        Set<String> objectSet = new HashSet<String>();
        Map<String,Object> returnMap = new HashMap<String,Object>();
        
        injectTargetSet(inputList,relObjList,bizMasterObjList,bizObjList,objectSet);
        
        Map<String,Object> map = new HashMap<String,Object>();
        //Relation Type별 Flag Setting작업 필요
        if(!NullUtil.isNone(relObjList)) {
            Map<String, ObjectTableKeyVO> objectKeyMap = new HashMap<String, ObjectTableKeyVO>();

            Map<String,Object> parmMap = new HashMap<String,Object>();
            parmMap.put("obidList", objectSet);
            
            RowBounds unlimitedRowBounds = new RowBounds(OmcSystemConstants.NO_ROW_OFFSET, OmcSystemConstants.OMC_DBMS_UNLIIMITED_COUNT);
            List<ObjectTableKeyVO> objectTableKeyVOList = schemaDao.selectList("Common.getObjectTableKeys", parmMap);
            for(ObjectTableKeyVO keyVO : objectTableKeyVOList ){
                objectKeyMap.put(keyVO.getObid(), keyVO);
            }
            String fromObid = "";
            String toObid   = "";
            ObjectTableKeyVO fromKeyVO;
            ObjectTableKeyVO toKeyVO;

            long flags = 0;
            boolean isValid = false;
            Set<String> relationList = new HashSet<String>();
            Set<String> relationShipList = new HashSet<String>();
            for(ObjectRootVO vo : relObjList){
                isValid = false;
                fromObid  = ((BusinessRelationObjectVO)vo).getFromObid();
                if(StrUtil.isEmpty(fromObid)) throw new FoundationException("omc.error.keyInfo.fromobidempty");
                toObid    = ((BusinessRelationObjectVO)vo).getToObid();
                if(StrUtil.isEmpty(toObid)) throw new FoundationException("omc.error.keyInfo.toobidempty");
                
                fromKeyVO = objectKeyMap.get(fromObid);
                if(fromKeyVO == null) throw new FoundationException("omc.error.keyInfo.fromobjectnotfound");
                toKeyVO   = objectKeyMap.get(toObid);
                if(toKeyVO == null) throw new FoundationException("omc.error.keyInfo.toobjectnotfound");

                if(Bit.isInclude(fromKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO) && Bit.isInclude(toKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO)){
                    vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO,OmcSystemConstants.OBJROOT_FLAG_RO_B2B));
                    isValid = true;
                }
                if(Bit.isInclude(fromKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO) && Bit.isInclude(toKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO)){
                    vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO,OmcSystemConstants.OBJROOT_FLAG_RO_B2R));
                    isValid = true;
                }
                if(Bit.isInclude(fromKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO) && Bit.isInclude(toKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO)){
                    vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO,OmcSystemConstants.OBJROOT_FLAG_RO_R2B));
                    isValid = true;
                }
                if(Bit.isInclude(fromKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO) && Bit.isInclude(toKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO)){
                    vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO,OmcSystemConstants.OBJROOT_FLAG_RO_R2R));
                    isValid = true;
                }
                if(!isValid) throw new FoundationException("omc.error.keyInfo.objectkeyinfoinvalid");
                relationList.add(vo.getClassName() + "," + fromKeyVO.getClassName() + "," + toKeyVO.getClassName() + "," + vo.getFlags());
                relationShipList.add(vo.getClassName());
            }
            for(String relationName : relationShipList){
            	FoundationValidationUtil.checkInstantiable(relationName);
            }
            for(String str : relationList){
                String[] classArray = str.split(",");
                FoundationValidationUtil.checkValidationFromToType(classArray[0],classArray[1],classArray[2], Long.parseLong(classArray[3]));
            }
        }
        if(!NullUtil.isNone(relObjList)) {
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                createObjectSetSub(relObjList, GlobalConstants.OBJECT_CREATE_SET_REL,"Common.insertRelationObjectSet[MARIA]");
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                createObjectSetSub(relObjList, GlobalConstants.OBJECT_CREATE_SET_REL,"Common.insertRelationObjectSet[ORACLE]");
            }else{
                createObjectSetSub(relObjList, GlobalConstants.OBJECT_CREATE_SET_REL,"Common.insertRelationObjectSet[MARIA]");
            }
        }
        if(!NullUtil.isNone(bizMasterObjList)) {
            for(ObjectRootVO vo : bizMasterObjList){
                vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO));
            }
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                createObjectSetSub(bizMasterObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR,"Common.insertBusinessObjectMasterSet[MARIA]");
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                createObjectSetSub(bizMasterObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR,"Common.insertBusinessObjectMasterSet[ORACLE]");
            }else{
                createObjectSetSub(bizMasterObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR,"Common.insertBusinessObjectMasterSet[MARIA]");
            }
        }
        if(!NullUtil.isNone(bizObjList)) {
            for(ObjectRootVO vo : bizObjList){
                vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO,OmcSystemConstants.OBJROOT_FLAG_Revisible));
            }
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                createObjectSetSub(bizObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ,"Common.insertBusinessObjectSet[MARIA]");
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                createObjectSetSub(bizObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ,"Common.insertBusinessObjectSet[ORACLE]");
            }else{
                createObjectSetSub(bizObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ,"Common.insertBusinessObjectSet[MARIA]");
            }
        }
        returnMap.put(GlobalConstants.OBJECT_CREATE_SET_BIZ, bizObjList);
        returnMap.put(GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR, bizMasterObjList);
        returnMap.put(GlobalConstants.OBJECT_CREATE_SET_REL, relObjList);

        return returnMap;
    }
    /**
     * Object List에 대해서 일괄 Modify작업을 한다.
     *
     * @param inputList
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> modifyObjectSet(List<? extends ObjectRootVO> inputList){

        List<ObjectRootVO> relObjList      = new ArrayList<ObjectRootVO>();
        List<ObjectRootVO> bizMasterObjList= new ArrayList<ObjectRootVO>();
        List<ObjectRootVO> bizObjList      = new ArrayList<ObjectRootVO>();
        Set<String> objectSet = new HashSet<String>();
        Map<String,Object> returnMap = new HashMap<String,Object>();
        
        injectTargetSet(inputList,relObjList,bizMasterObjList,bizObjList,objectSet);
        
        Map<String,Object> map = new HashMap<String,Object>();
        //Relation Type별 Flag Setting작업 필요
        if(!NullUtil.isNone(relObjList)) {
            Map<String, ObjectTableKeyVO> objectKeyMap = new HashMap<String, ObjectTableKeyVO>();

            Map<String,Object> parmMap = new HashMap<String,Object>();
            parmMap.put("obidList", objectSet);
            
            List<ObjectTableKeyVO> objectTableKeyVOList = schemaDao.selectList("Common.getObjectTableKeys", parmMap);
            for(ObjectTableKeyVO keyVO : objectTableKeyVOList ){
                objectKeyMap.put(keyVO.getObid(), keyVO);
            }
            String fromObid = "";
            String toObid   = "";
            ObjectTableKeyVO fromKeyVO;
            ObjectTableKeyVO toKeyVO;

            long flags = 0;
            boolean isValid = false;
            Set<String> relationList = new HashSet<String>();
            Set<String> relationShipList = new HashSet<String>();
            for(ObjectRootVO vo : relObjList){
                isValid = false;
                fromObid  = ((BusinessRelationObjectVO)vo).getFromObid();
                if(StrUtil.isEmpty(fromObid)) throw new FoundationException("omc.error.keyInfo.fromobidempty");
                toObid    = ((BusinessRelationObjectVO)vo).getToObid();
                if(StrUtil.isEmpty(toObid)) throw new FoundationException("omc.error.keyInfo.toobidempty");
                
                fromKeyVO = objectKeyMap.get(fromObid);
                if(fromKeyVO == null) throw new FoundationException("omc.error.keyInfo.fromobjectnotfound");
                toKeyVO   = objectKeyMap.get(toObid);
                if(toKeyVO == null) throw new FoundationException("omc.error.keyInfo.toobjectnotfound");

                if(Bit.isInclude(fromKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO) && Bit.isInclude(toKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO)){
                    vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO,OmcSystemConstants.OBJROOT_FLAG_RO_B2B));
                    isValid = true;
                }
                if(Bit.isInclude(fromKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO) && Bit.isInclude(toKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO)){
                    vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO,OmcSystemConstants.OBJROOT_FLAG_RO_B2R));
                    isValid = true;
                }
                if(Bit.isInclude(fromKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO) && Bit.isInclude(toKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO)){
                    vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO,OmcSystemConstants.OBJROOT_FLAG_RO_R2B));
                    isValid = true;
                }
                if(Bit.isInclude(fromKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO) && Bit.isInclude(toKeyVO.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO)){
                    vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_RO,OmcSystemConstants.OBJROOT_FLAG_RO_R2R));
                    isValid = true;
                }
                if(!isValid) throw new FoundationException("omc.error.keyInfo.objectkeyinfoinvalid");
                relationList.add(vo.getClassName() + "," + fromKeyVO.getClassName() + "," + toKeyVO.getClassName() + "," + vo.getFlags());
                relationShipList.add(vo.getClassName());
            }
            for(String relationName : relationShipList){
            	FoundationValidationUtil.checkInstantiable(relationName);
            }
            for(String str : relationList){
                String[] classArray = str.split(",");
                FoundationValidationUtil.checkValidationFromToType(classArray[0],classArray[1],classArray[2], Long.parseLong(classArray[3]));
            }
        }
        if(!NullUtil.isNone(relObjList)) {
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                createObjectSetSub(relObjList, GlobalConstants.OBJECT_CREATE_SET_REL,"Common.insertRelationObjectSet[MARIA]");
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                createObjectSetSub(relObjList, GlobalConstants.OBJECT_CREATE_SET_REL,"Common.insertRelationObjectSet[ORACLE]");
            }else{
                createObjectSetSub(relObjList, GlobalConstants.OBJECT_CREATE_SET_REL,"Common.insertRelationObjectSet[MARIA]");
            }
        }
        if(!NullUtil.isNone(bizMasterObjList)) {
            for(ObjectRootVO vo : bizMasterObjList){
                vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO));
            }
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                createObjectSetSub(bizObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR,"Common.insertBusinessObjectMasterSet[MARIA]");
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                createObjectSetSub(bizObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR,"Common.insertBusinessObjectMasterSet[ORACLE]");
            }else{
                createObjectSetSub(bizObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR,"Common.insertBusinessObjectMasterSet[MARIA]");
            }
        }
        if(!NullUtil.isNone(bizObjList)) {
            for(ObjectRootVO vo : bizObjList){
                vo.setFlags(Bit.or(vo.getFlags(),OmcSystemConstants.OBJROOT_FLAG_BO,OmcSystemConstants.OBJROOT_FLAG_Revisible));
            }
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                createObjectSetSub(bizObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ,"Common.insertBusinessObjectSet[MARIA]");
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                createObjectSetSub(bizObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ,"Common.insertBusinessObjectSet[ORACLE]");
            }else{
                createObjectSetSub(bizObjList, GlobalConstants.OBJECT_CREATE_SET_BIZ,"Common.insertBusinessObjectSet[MARIA]");
            }
            
        }
        returnMap.put(GlobalConstants.OBJECT_CREATE_SET_BIZ, bizObjList);
        returnMap.put(GlobalConstants.OBJECT_CREATE_SET_BIZ_MSTR, bizMasterObjList);
        returnMap.put(GlobalConstants.OBJECT_CREATE_SET_REL, relObjList);

        return returnMap;
    }
    /**
     * Object List에 대해서 일괄 삭제
     * 
     * @param inputList
     * @see omc.foundation.classes.service.CommonService#deleteObjectSet(java.util.List)
     */
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteObjectSet(List<? extends ObjectRootVO> inputList){
        Map<String, ArrayList<ObjectRootVO>> inputListMap = classifyClassList(inputList);
        validateForDeleteObjectSet(inputListMap);
        for(String className : inputListMap.keySet()){
            ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
            if(NullUtil.isNull(classInfo)) throw new FoundationException("[Foundation]Critical Error. Not Found Class(" + className + ")");
            if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Relation)){
                
                if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                    deleteObjectSetSub(inputListMap.get(className),className,classInfo.getDbmsTable(),"Common.deleteRelationObjectSet[MARIA]");
                }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                    deleteObjectSetSub(inputListMap.get(className),className,classInfo.getDbmsTable(),"Common.deleteRelationObjectSet[ORACLE]");
                }else{
                    deleteObjectSetSub(inputListMap.get(className),className,classInfo.getDbmsTable(),"Common.deleteRelationObjectSet[MARIA]");
                }
            }else if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Business)){
                if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                    deleteObjectSetSub(inputListMap.get(className),className,classInfo.getDbmsTable(),"Common.deleteBusinessObjectSet[MARIA]");
                }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                    deleteObjectSetSub(inputListMap.get(className),className,classInfo.getDbmsTable(),"Common.deleteBusinessObjectSet[ORACLE]");
                }else{
                    deleteObjectSetSub(inputListMap.get(className),className,classInfo.getDbmsTable(),"Common.deleteBusinessObjectSet[MARIA]");
                }
            }else{
                throw new FoundationException("[Foundation]Schema Info Error. Class is '" + className + "'");
            }
        }
    }
    /**
     * Object List에 대한 삭제전Validation을 수행한다.
     *
     * @param inputListMap 삭제되어질 Object List
     */
    private void validateForDeleteObjectSet(Map<String, ArrayList<ObjectRootVO>> inputListMap){
        for(String className : inputListMap.keySet()){
            ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
            if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_IsReferenced)){
                
                List<ObjectRootVO> list = inputListMap.get(className);
                List<ObjectRootVO> newList = new ArrayList<ObjectRootVO>();
                if(list.size() > OmcSystemConstants.OMC_OBJECT_SQL_DML_UNIT){
                    int seq = 0;
                    for(ObjectRootVO vo : list){
                        if(seq > 0 && seq%OmcSystemConstants.OMC_OBJECT_SQL_DML_UNIT == 0){
                            Map<String,Object> map = new HashMap<String,Object>();
                            map.put("voList", newList);
                            Integer exists = schemaDao.select("Common.referencedObjectCheck", map);
                            if(exists > 0) throw new FoundationException("[Foundation]Referenced Object Exits.Cannot delete with 'DeleteObjectSet'. Class Name is '" + className + "'.");  
                            newList = new ArrayList<ObjectRootVO>();
                        }
                        newList.add(vo);
                        seq++;
                    }
                    if(!NullUtil.isNone(newList)){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("voList", newList);
                        Integer exists = schemaDao.select("Common.referencedObjectCheck", map);
                        if(exists > 0) throw new FoundationException("[Foundation]Referenced Object Exits.Cannot delete with 'DeleteObjectSet'. Class Name is '" + className + "'.");  
                    }
                }else{
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("voList", inputListMap.get(className));
                    Integer exists = schemaDao.select("Common.referencedObjectCheck", map);
                    if(exists > 0) throw new FoundationException("[Foundation]Referenced Object Exits.Cannot delete with 'DeleteObjectSet'. Class Name is '" + className + "'.");                    
                }
            }
        }
    }
    /**
     * 
     *
     * @param inputList
     * @return
     */
    private Map<String, ArrayList<ObjectRootVO>> classifyClassList(List<? extends ObjectRootVO> inputList){
        Map<String, ArrayList<ObjectRootVO>> inputListMap = new HashMap<String, ArrayList<ObjectRootVO>>();
        SortUtil.sort(inputList, "className", false);
        boolean isFirst = true;
        ArrayList<ObjectRootVO> tempList = new ArrayList<ObjectRootVO>();
        String classNameSaved = "";
        for(ObjectRootVO vo : inputList){
            if(isFirst){
                tempList.add(vo);
            }else{
                if(!classNameSaved.equals(vo.getClassName())){
                    inputListMap.put(classNameSaved, tempList);
                    tempList = new ArrayList<ObjectRootVO>();
                }
                tempList.add(vo);
            }
            classNameSaved = vo.getClassName();
            isFirst = false;
        }
        if(!NullUtil.isNone(tempList)) inputListMap.put(classNameSaved, tempList);
        return(inputListMap);
    }
    /**
     * 
     *
     * @param inputList
     * @param className
     * @param dbmsTable
     * @param queryId
     */
    private void deleteObjectSetSub(List<? extends ObjectRootVO> inputList, String className, String dbmsTable, String queryId){
        int i = 0;
        List<ObjectRootVO> newList = new ArrayList<ObjectRootVO>();
        Map<String,Object> map = new HashMap<String,Object>();
        for(ObjectRootVO vo : inputList){
            if(i > 0 && i%OmcSystemConstants.OMC_OBJECT_SQL_DML_UNIT == 0) {
                map.put("voList", newList);
                map.put("dbmsTable", dbmsTable);
                schemaDao.delete(queryId, map);
                newList = new ArrayList<ObjectRootVO>();
            }
            newList.add(vo);
            i++;
        }
        if(!NullUtil.isNone(newList)){
            map.put("voList", newList);
            map.put("dbmsTable", dbmsTable);
            schemaDao.delete(queryId, map);
        }
    }
    private void createObjectSetSub(List<? extends ObjectRootVO> inputList, String mapKey, String queryId){
        List<ObjectRootVO> newList = new ArrayList<ObjectRootVO>();
        List<ObjectRootVO> rtnList = new ArrayList<ObjectRootVO>();
        int i = 0;
        Map<String,Object> map = new HashMap<String,Object>();
        for(ObjectRootVO vo : inputList){
            if(i > 0 && i%OmcSystemConstants.OMC_OBJECT_SQL_DML_UNIT == 0) {
                map.put("voList", newList);
                schemaDao.insert(queryId, map);
                rtnList.addAll(newList);
                newList = new ArrayList<ObjectRootVO>();
            }
            newList.add(vo);
            i++;
        }
        if(!NullUtil.isNone(newList)){
            map.put("voList", newList);
            schemaDao.insert(queryId, map);
            rtnList.addAll(newList);
        }
    }
    
    public <T> List<T> getSimpleObjects(String className, List<? extends ObjectRootVO> inputList, Set<String> attributes){
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
        String selectStr = classInfo.getConvert2SelectString(true);
        String whereStr = classInfo.makeWhereString(attributes);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sql", selectStr);
        map.put("whereStr", whereStr);
        map.put("voList", inputList);
        map.put("dbmsTable", classInfo.getDbmsTable());
        List<T> result = schemaDao.selectList("Common.getSimpleObjects", map);
        return result;
    }
    
    public <T> T getObject(ObjectRootVO searchInfo){
        return getObject(searchInfo,true);
    }

    public <T> T getObject(ObjectRootVO searchInfo, boolean withOutData){
        injectTarget(searchInfo,withOutData);
        T result = schemaDao.select("Common.getObject", searchInfo);
        return result;
    }
    
    public <T> T getObject(String obid,boolean withOutData){
        ObjectRootVO searchInfo = new ObjectRootVO();
        searchInfo.setObid(obid);
        return(getObject(searchInfo,withOutData));
    }
    
    public <T> T getObject(String obid){
        return getObject(obid,true);
    }

    public <T> T getObject(String queryId, String obid){
        ObjectRootVO searchInfo = new ObjectRootVO();
        searchInfo.setObid(obid);

        injectTarget(searchInfo);
        T result = schemaDao.select(queryId, searchInfo);
        return result;
    }

    public <T> List<T> getRevisionObjects(String queryId, String className, String names){
        BusinessObjectVO searchInfo = new BusinessObjectVO();
        searchInfo.setClassName(className);
        searchInfo.setNames(names);
        injectTarget(searchInfo);
        List<T> result = schemaDao.selectList(queryId, searchInfo);
        return result;
    }
    
    public <T> T getObjectWithOutData(String obid){
        ObjectRootVO searchInfo = new ObjectRootVO();
        searchInfo.setObid(obid);
        this.injectTarget(searchInfo);
        searchInfo.setSql(QueryStringUtil.appendSelectValues(searchInfo));
        T result = schemaDao.select("Common.getObject", searchInfo);
        return result;
    }
    @SuppressWarnings({"unchecked","rawtypes"})
    public <T> List<T> getObjectsWithOutData(List<String> obidList, String className){
        ObjectRootVO searchInfo = new ObjectRootVO();
        searchInfo.setClassName(className);
        this.injectTarget(searchInfo);
        Map map = new HashMap();
        map.put("sql", QueryStringUtil.appendSelectValues(searchInfo));
        map.put("obidList", obidList);
        map.put("dbmsTable", searchInfo.getDbmsTable());
        List<T> result = schemaDao.selectList("Common.getObjects", map);
        return result;
    }
    public <T> List<T> getObjectList(String queryId, String obid){
        ObjectRootVO searchInfo = new ObjectRootVO();
        searchInfo.setObid(obid);

        injectTarget(searchInfo);
        List<T> result = schemaDao.selectList(queryId, searchInfo);
        return result;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<RelationTableInfo> getRelationTableObjList(String obid, String direction, String classFilter, String fromClassFilter, String toClassFilter){
        Map map = new HashMap<String,String>();
        map.put("obid", obid);
        map.put("direction", direction);
        if(!NullUtil.isNone(classFilter) && !classFilter.equals(GlobalConstants.FLAG_TYPE_ALL)){
            String classListStr = ClassInfoUtil.getChildClassListStr(classFilter);
            List<String> strList = StrUtil.convertArrayToList(classListStr.split(","));
            map.put("relationList", strList);
        }
        if(!NullUtil.isNone(fromClassFilter) && !fromClassFilter.equals(GlobalConstants.FLAG_TYPE_ALL)){
            String classListStr = ClassInfoUtil.getChildClassListStr(fromClassFilter);
            List<String> strList = StrUtil.convertArrayToList(classListStr.split(","));
            map.put("fromFilterList", strList);
        }
        if(!NullUtil.isNone(toClassFilter) && !toClassFilter.equals(GlobalConstants.FLAG_TYPE_ALL)){
            String classListStr = ClassInfoUtil.getChildClassListStr(toClassFilter);
            List<String> strList = StrUtil.convertArrayToList(classListStr.split(","));
            map.put("toFilterList", strList);
        }
        List<RelationTableInfo> result = schemaDao.selectList("SchemaNew.getRelationObjectTable", map);
        return(result);
    }
    public List<FilesVO> getFileObjectList(FilesVO file){

        List<FilesVO> result = schemaDao.selectList("file.getFileObject", file);
        return result;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void createObject(ObjectRootVO input){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        input.setModifier(userId);
        input.setCreator(userId);

        injectTarget(input);
        input.setSql(QueryStringUtil.convert2InsertValues(input));
        if (input instanceof BusinessRelationObjectVO) {
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                schemaDao.insert("Common.insertRelationObject[MARIA]", input);
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                schemaDao.insert("Common.insertRelationObject[ORACLE]", input);
            }else{
                schemaDao.insert("Common.insertRelationObject[MARIA]", input);
            }
        }else {
            if (input instanceof BusinessObjectVO) {
                if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                    schemaDao.insert("Common.insertBusinessObject[MARIA]", input);
                }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                    schemaDao.insert("Common.insertBusinessObject[ORACLE]", input);
                }else{
                    schemaDao.insert("Common.insertBusinessObject[MARIA]", input);
                }
            } else {
                if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                    schemaDao.insert("Common.insertBusinessObjectMaster[MARIA]", input);
                }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                    schemaDao.insert("Common.insertBusinessObjectMaster[ORACLE]", input);
                }else{
                    schemaDao.insert("Common.insertBusinessObjectMaster[MARIA]", input);
                }
                
            }
        }
    }
    /**
     * base 영역의 항목만 update
     * 
     * @param input
     * @see omc.foundation.common.service.CommonService#updateObject(omc.api.object.model.ObjectRootVO)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyObjectBatch(List<? extends ObjectRootVO> inputList,Set<String> attributes){
        if(NullUtil.isNone(inputList)) return;
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        for(ObjectRootVO vo : inputList){
            vo.setModifier(userId);
            if(StrUtil.isEmpty(vo.getObid())) throw new FoundationException("[Foundation]Critical There is null Ojbect ID.");
            if(StrUtil.isEmpty(vo.getClassName())) {
                KeyInfo keyInfo = BaseFoundationUtil.getKeyInfo(vo.getObid());
                if (NullUtil.isNull(keyInfo)) {
                    throw new FoundationException("omc.error.keyInfo.nodata");
                } else {
                    vo.setClassName(keyInfo.getClassName());
                }
            }
            ClassInfo classInfo = ClassInfoUtil.getClassInfo(vo.getClassName());
            if (NullUtil.isNull(classInfo)) { throw new FoundationException("omc.error.classInfo.nodata"); }
            vo.setDbmsTable(classInfo.getDbmsTable());
            vo.setModifier(userId);
            attributes.add("modified");attributes.add("modifier");
            vo.setSql(QueryStringUtil.convert2UpdateValues(classInfo,vo,attributes,true));
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("voList", inputList);
        if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
            schemaDao.update("Common.modifyObjectSet[MARIA]",map);
        }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
            schemaDao.update("Common.modifyObjectSet[ORACLE]",map);
        }else{
            schemaDao.update("Common.modifyObjectSet[MARIA]",map);
        }
    }
    /**
     * base 영역의 항목만 update
     * 
     * @param input
     * @see omc.foundation.common.service.CommonService#updateObject(omc.api.object.model.ObjectRootVO)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyObject(ObjectRootVO input){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        input.setModifier(userId);

        injectTarget(input);
        input.setSql(QueryStringUtil.convert2UpdateValues(input, false));

        schemaDao.update("Common.updateObject", input);
    }

    /**
     * foundation 영역의 항목만 update
     * 
     * @param input
     * @see omc.foundation.common.service.CommonService#updateObjectForFoundation(omc.api.object.model.ObjectRootVO)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateObject(ObjectRootVO input, boolean bIncludeAll){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        input.setModifier(userId);

        injectTarget(input);
        if (bIncludeAll) {
            input.setSql(QueryStringUtil.convert2UpdateValues(input, true));
        } else {
            input.setSql(QueryStringUtil.convert2UpdateSpecificValues(input));
        }
        schemaDao.update("Common.updateObject", input);
    }

    /**
     * change 대상 항목만 update
     * 
     * @param input
     * @see omc.foundation.common.service.CommonService#changeObject(omc.api.object.model.ObjectRootVO)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeObject(ObjectRootVO input, boolean isOwner){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        input.setModifier(userId);

        injectTarget(input);
        if (!isOwner) {
            input.setSql(QueryStringUtil.convert2ChangeValues(input, false));
        } else {
            input.setSql(QueryStringUtil.convert2ChangeValues(input, true));
        }
        schemaDao.update("Common.updateObject", input);
    }

    /**
     * change 대상 항목만 update
     * 
     * @param input
     * @see omc.foundation.common.service.CommonService#changeObjectWithKeyTable(omc.api.object.model.ObjectRootVO)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeObjectWithKeyTable(ObjectRootVO input){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        input.setModifier(userId);
        injectTarget(input);
        input.setSql(QueryStringUtil.convert2ChangeValues(input, false));
        schemaDao.update("Common.updateObject", input);
        updateKeyInfo(input);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeObjectWithKeyTableForDefloat(ObjectRootVO input){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        input.setModifier(userId);
        injectTarget(input);
        input.setSql(QueryStringUtil.convert2DefloatValues(input));
        schemaDao.update("Common.updateObject", input);
        updateKeyInfoForDefloat(input);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeRealtionObject(ObjectRootVO input){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        input.setModifier(userId);
        injectTarget(input);
        input.setSql(QueryStringUtil.convert2UpdateValues(input,true));
        if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
            schemaDao.update("Common.updateRelationObjectWithKeyTable[MARIA]", input);
        }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
            schemaDao.update("Common.updateRelationObjectWithKeyTable[ORACLE]", input);
        }else{
            schemaDao.update("Common.updateRelationObjectWithKeyTable[MARIA]", input);
        }
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeRelationObjectWithKeyTable(BusinessRelationObjectVO input){
        if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
            schemaDao.update("Common.updateRelationKeyInfoAll[MARIA]", input);
        }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
            schemaDao.update("Common.updateRelationKeyInfoAll[ORACLE]", input);
        }else{
            schemaDao.update("Common.updateRelationKeyInfoAll[MARIA]", input);
        }
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteObject(ObjectRootVO input){
        injectTarget(input);
        if (input instanceof BusinessRelationObjectVO) {
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                schemaDao.delete("Common.deleteRelationObject[MARIA]", input);
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                schemaDao.delete("Common.deleteRelationObject[ORACLE]", input);
            }else{
                schemaDao.delete("Common.deleteRelationObject[MARIA]", input);
            }
        } else {
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                schemaDao.delete("Common.deleteBusinessObject[MARIA]", input);
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                schemaDao.delete("Common.deleteBusinessObject[ORACLE]", input);
            }else{
                schemaDao.delete("Common.deleteBusinessObject[MARIA]", input);
            }
        }
    }
    public void deleteObject(String obid){
        ObjectRootVO input = new ObjectRootVO();
        input.setObid(obid);
        deleteObject(input);
    }
    private void injectTarget(ObjectRootVO bizObj){
        injectTarget(bizObj,true);
    }
    
    private void injectTarget(ObjectRootVO bizObj, boolean withOutData){
        if (NullUtil.isNone(bizObj.getClassName())) {
            KeyInfo keyInfo = BaseFoundationUtil.getKeyInfo(bizObj.getObid());
            if (NullUtil.isNull(keyInfo)) {
                throw new FoundationException("omc.error.keyInfo.nodata");
            } else {
                bizObj.setClassName(keyInfo.getClassName());
            }
        }
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(bizObj.getClassName());
        if (NullUtil.isNull(classInfo)) { throw new FoundationException("omc.error.classInfo.nodata"); }
        bizObj.setDbmsTable(classInfo.getDbmsTable());
        bizObj.setSql(classInfo.getConvert2SelectString(withOutData));
    }

    private void injectTargetSet(List<? extends ObjectRootVO> allObjList, List<ObjectRootVO> relObjList, List<ObjectRootVO> bizMasterObjList, List<ObjectRootVO> bizObjList,Set<String> objectSet){
        
        String[] obid = NameGeneratorUtil.generateUniqueNameSet(allObjList.size());
        if(obid.length != allObjList.size()) throw new FoundationException("[Foundation]Object Id Generator Error!");
        
        int i = 0;
        for(ObjectRootVO bizObj : allObjList){
            bizObj.setObid(obid[i++]);
            if (NullUtil.isNone(bizObj.getClassName())) {
                String tempClassName = bizObj.getClass().toString();
                tempClassName = tempClassName.substring(tempClassName.lastIndexOf('.') + 1);
                tempClassName = tempClassName.replace("VO", "");
                bizObj.setClassName(tempClassName);
            }
        }
        SortUtil.sort(allObjList, "className", false);
        int RELATION_CLASS         = 1;
        int BUSINESS_OBJECT_MASTER = 2;
        int BUSINESS_OBJECT        = 3;
        boolean isFirst       = true;
        String classNameSaved = "";
        String dbmsTable      = "";
        String insertValus    = "";
        String insertColumns  = "";

        int objectType = 0;
        
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        Date date = null;
        
        for(ObjectRootVO bizObj : allObjList){
            bizObj.setModifier(userId);
            bizObj.setCreator(userId);
            if(isFirst){
                classNameSaved = bizObj.getClassName();
                ClassInfo classInfo = ClassInfoUtil.getClassInfo(classNameSaved);
                if (NullUtil.isNull(classInfo)) { throw new FoundationException("omc.error.classInfo.nodata"); }
                if(!Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Instantiable))  { throw new FoundationException("[Foundation]Cannot instantiate. Class Name is '" + classNameSaved + "'"); }
                dbmsTable     = classInfo.getDbmsTable();
                insertValus   = QueryStringUtil.convert2InsertValues(bizObj,true);
                insertColumns = bizObj.getColumns();
                if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Relation)){
                    objectType = RELATION_CLASS;
                }else if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Business)){
                    if(Bit.isInclude(classInfo.getFlags(), OmcSystemConstants.BUSINESS_FLAG_Revisible)){
                        objectType = BUSINESS_OBJECT;
                    }else{
                        objectType = BUSINESS_OBJECT_MASTER;
                    }
                }else{
                    throw new FoundationException("omc.error.classInfo.nodata");
                }
            }
            if(classNameSaved.equals(bizObj.getClassName())){
                bizObj.setDbmsTable(dbmsTable);
                insertValus   = QueryStringUtil.convert2InsertValues(bizObj,true);
                bizObj.setSql(insertValus);
                bizObj.setColumns(insertColumns);
            }else{
                classNameSaved = bizObj.getClassName();
                ClassInfo classInfo = ClassInfoUtil.getClassInfo(classNameSaved);
                if (NullUtil.isNull(classInfo)) { throw new FoundationException("omc.error.classInfo.nodata"); }
                if(!Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Instantiable))  { throw new FoundationException("[Foundation]Cannot instantiate. Class Name is '" + classNameSaved + "'"); }
                if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Relation)){
                    objectType = RELATION_CLASS;
                }else if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Business)){
                    if(Bit.isInclude(classInfo.getFlags(), OmcSystemConstants.BUSINESS_FLAG_Revisible)){
                        objectType = BUSINESS_OBJECT;
                    }else{
                        objectType = BUSINESS_OBJECT_MASTER;
                    }
                }else{
                    throw new FoundationException("omc.error.classInfo.nodata");
                }
                dbmsTable     = classInfo.getDbmsTable();
                insertValus   = QueryStringUtil.convert2InsertValues(bizObj,true);
                insertColumns = bizObj.getColumns();
                bizObj.setDbmsTable(dbmsTable);
                bizObj.setSql(insertValus);
                bizObj.setColumns(insertColumns);
            }
            bizObj.setFlags(OmcSystemConstants.OBJROOT_FLAG_Default);
            bizObj.setLocker("1");
            bizObj.setCheckouter("1");
            bizObj.setCheckouted(date);
            bizObj.setOwner("1");
            
            isFirst = false;
            if(objectType == RELATION_CLASS){
                relObjList.add(bizObj);
                objectSet.add(bizObj.getObid());
                objectSet.add(((BusinessRelationObjectVO)bizObj).getFromObid());
                objectSet.add(((BusinessRelationObjectVO)bizObj).getToObid());
            }else if(objectType == BUSINESS_OBJECT_MASTER){
                bizMasterObjList.add(bizObj);
                objectSet.add(bizObj.getObid());
            }else if(objectType == BUSINESS_OBJECT){
                bizObjList.add(bizObj);
                objectSet.add(bizObj.getObid());
            }
        }
    }
    @Transactional(propagation = Propagation.REQUIRED)
    private void updateKeyInfo(ObjectRootVO input){
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setObid(input.getObid());
        if (!NullUtil.isNone(input.getClassName())) {
            keyInfo.setClassName(input.getClassName());
        }
        if (input instanceof BusinessObjectRootVO && !NullUtil.isNone(((BusinessObjectRootVO)input).getNames())) {
            keyInfo.setNames(((BusinessObjectRootVO)input).getNames());
        }
        if (input instanceof BusinessObjectVO && !NullUtil.isNone(((BusinessObjectVO)input).getRevision())) {
            keyInfo.setRevision(((BusinessObjectVO)input).getRevision());
        }
        if (input instanceof BusinessRelationObjectVO) {
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                schemaDao.update("Common.updateRelationObjetKeyInfo[MARIA]", keyInfo);
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                schemaDao.update("Common.updateRelationObjetKeyInfo[ORACLE]", keyInfo);
            }else{
                schemaDao.update("Common.updateRelationObjetKeyInfo[MARIA]", keyInfo);
            }
        } else {
            if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
                schemaDao.update("Common.updateBusinessObjectKeyInfo[MARIA]", keyInfo);
            }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
                schemaDao.update("Common.updateBusinessObjectKeyInfo[ORACLE]", keyInfo);
            }else{
                schemaDao.update("Common.updateBusinessObjectKeyInfo[MARIA]", keyInfo);
            }
        }
    }
    public List<KeyInfo> getClassNameWithObidList(List<String> obidList){
        return schemaDao.selectList("Common.getClassNameWithObidList", obidList);
        
    }
    public List<KeyInfo> getClassNameWithObidSet(Set<String> obidSet){
        List<String> obidList = new ArrayList<String>();
        for(String obid : obidSet) obidList.add(obid);
        return schemaDao.selectList("Common.getClassNameWithObidList", obidList);
        
    }
    private void updateKeyInfoForDefloat(ObjectRootVO input){
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setObid(input.getObid());
        if (!NullUtil.isNull(input.getFlags())) {
            keyInfo.setFlags(input.getFlags());
        }
        if (input instanceof BusinessRelationObjectVO) {
            schemaDao.update("Common.updateObjectFlagKeyInfo", keyInfo);            
        } 
    }
    public List<RelationTableInfo> getAllRelationShipForDeleteObject(String obid){
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setObid(obid);
        List<RelationTableInfo> result = schemaDao.selectList("Common.selectRelationShipAllList", keyInfo);
        return result;
    }
    
    public List<OmcSchemaPropertyVO> getVariableList(){
        List<OmcSchemaPropertyVO> allList = new ArrayList<OmcSchemaPropertyVO>();
        List<OmcSchemaPropertyVO> tempList = new ArrayList<OmcSchemaPropertyVO>();
        
        tempList = getSystemConstantsList();allList.addAll(tempList);
        tempList = getLifeCycleConstantsList();allList.addAll(tempList);
        tempList = getStateConstantsList();allList.addAll(tempList);
        tempList = getClassBizConstantsList();allList.addAll(tempList);
        tempList = getClassRelConstantsList();allList.addAll(tempList);
        return(allList); 
    }
    public List<OmcSchemaPropertyVO> getDefinedAttribute(){
        List<OmcSchemaPropertyVO> allList = new ArrayList<OmcSchemaPropertyVO>();
        List<OmcSchemaPropertyVO> tempList = new ArrayList<OmcSchemaPropertyVO>();
        tempList = getLifeCycleConstantsList();allList.addAll(tempList);
        tempList = getStateConstantsList();allList.addAll(tempList);
        tempList = getClassBizConstantsList();allList.addAll(tempList);
        tempList = getClassRelConstantsList();allList.addAll(tempList);
        return(allList); 
    }
    public List<OmcSchemaPropertyVO> getSystemConstantsList(){ 
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaProperty.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("inner join psysconstants b");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("on a.psys_object = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSPTY_KIND_Constants));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSPTY_FLAG_Active));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSCONSTANTS_FLAG_Active));    
        //System.out.println(sqlStrBuff.toString());
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaPropertyVO> propertyList = schemaDao.selectList("SchemaNew.dynamicRetrieveProperty", variableParameter);
        return(propertyList);
    }
    public List<OmcSchemaPropertyVO> getLifeCycleConstantsList(){ 
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaProperty.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("inner join psyslifecycle b");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("on a.psys_object = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSPTY_KIND_LifeCycle));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSPTY_FLAG_Active));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSLCYCLE_FLAG_Active));
        //System.out.print(sqlStrBuff.toString());
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaPropertyVO> propertyList = schemaDao.selectList("SchemaNew.dynamicRetrieveProperty", variableParameter);
        return(propertyList);
    }
    public List<OmcSchemaPropertyVO> getStateConstantsList(){ 
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaProperty.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("inner join psysstate b");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("inner join psyslifecycle c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("on  a.psys_object = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and b.plife_cycle = c.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSPTY_KIND_State));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSPTY_FLAG_Active));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSSTATE_FLAG_Active));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00004}", "#{funVariable_00004}"));
        variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSLCYCLE_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaPropertyVO> propertyList = schemaDao.selectList("SchemaNew.dynamicRetrieveProperty", variableParameter);
        return(propertyList);
    }
    public List<OmcSchemaPropertyVO> getClassBizConstantsList(){ 
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaProperty.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("inner join psysbizobjectclassinfo b");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("on a.psys_object = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSPTY_KIND_Class));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSPTY_FLAG_Active));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaPropertyVO> propertyList = schemaDao.selectList("SchemaNew.dynamicRetrieveProperty", variableParameter);
        return(propertyList);
    }
    public List<OmcSchemaPropertyVO> getClassRelConstantsList(){ 
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaProperty.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("inner join psysrelobjectclassinfo b");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("on a.psys_object = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSPTY_KIND_Class));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSPTY_FLAG_Active));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.RELATION_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaPropertyVO> propertyList = schemaDao.selectList("SchemaNew.dynamicRetrieveProperty", variableParameter);
        return(propertyList);
    }
    
    public List<FilesVO> getFileObjects(FilesVO vo){
        this.injectTarget(vo);
        vo.setSql(QueryStringUtil.appendSelectValues(vo));
        List<FilesVO> result = schemaDao.select("file.getFilesTemp", vo);
        return result;
    }
    public List<ObjectTableVO> getObjectTableForClassChange(BusinessObjectRootVO vo){
        List<ObjectTableVO> result = schemaDao.selectList("ClassInfo.getObjectTableForClassChange", vo);
        return result;
    }
    public void updateRelationForClassChangeFrom(Map<String,Object> map){
        schemaDao.update("ClassInfo.updateRelationFromClass", map);
    }
    public void updateRelationForClassChangeTo(Map<String,Object> map){
        schemaDao.update("ClassInfo.updateRelationToClass", map);
    }

}
