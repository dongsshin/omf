/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : FoundationServiceImpl.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 15. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.service.CommonService;
import com.rap.omc.foundation.classes.service.FoundationService;
import com.rap.omc.foundation.common.model.KeyInfo;
import com.rap.omc.foundation.common.model.search.SearchTargetInfo;
import com.rap.omc.foundation.model.VariableAttribute;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : FoundationServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@Service("foundationService")
public class FoundationServiceImpl implements FoundationService {

    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;

    @Resource(name = "commonService")
    private CommonService commonService;
    
    @Override
    public KeyInfo getKeyInfo(ObjectRootVO searchInfo){
        return(getKeyInfo(searchInfo.getObid()));
    }
    @Override
    @Cacheable(value = "objectKeyTable", key = "#obid")
    public KeyInfo getKeyInfo(String obid){
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setObid(obid);
        KeyInfo result = schemaDao.select("Common.getKeyDetailInfo", keyInfo);
        return result;
    }
    @Override
    public List<String> getBusinessLatestObjectList(String className,List<String> nameList){
        Map<String, Object> map = new HashMap<String, Object>(); 
        List<String> chileList = ClassInfoUtil.getInstantiableChildList(className);
        map.put("classNameList", chileList);
        map.put("nameList", nameList);
        List<String> result = schemaDao.selectList("Common.getLatestBusinessObjectObidList", map);
        return result;
    }
    @Override
    public List<String> getBusinessObjectMasters(String className,String names){
        return(getBusinessObjects(className,names,"-"));
    }
    @Override
    public List<String> getBusinessObjects(String className,String names, String revision){
        Map<String, Object> map = new HashMap<String, Object>(); 
        List<String> chileList = ClassInfoUtil.getInstantiableChildList(className);
        map.put("classNameList", chileList);
        map.put("revision", revision);
        map.put("names", names);
        List<String> result = new ArrayList<String>();
        //result = schemaDao.selectList("Common.getBusinessObjectObidList", map);
        if(chileList.size() == 1){
            map.put("className", chileList.get(0));
            result = schemaDao.selectList("Common.getBusinessObjectObid", map);
        }else{
            result = schemaDao.selectList("Common.getBusinessObjectObidList", map);
        }
        if(result.size() == 0) result = null;
        return result;
    }
    @Override
    public List<String> getBusinessObjectMasterList(String className,List<String> nameList){
        Map<String, Object> map = new HashMap<String, Object>(); 
        List<String> chileClassList = ClassInfoUtil.getInstantiableChildList(className);
        map.put("chileClassList", chileClassList);
        map.put("nameList", nameList);
        map.put("revision", "-");
        List<String> result = schemaDao.selectList("Common.getBusinessObjectMasterObidList", map);
        return result;
    }
    @Override
    public <T> List<T> getRelatedObjectList(SearchTargetInfo searchInfo){
        List<T> resultList = null;
        List<String> relatedObidList = schemaDao.selectList("Relation.getRelatedObjectList", searchInfo);
        if (!NullUtil.isNone(relatedObidList)) {
            resultList = new ArrayList<T>();
            for (String relatedObid : relatedObidList) {
                T result = commonService.getObject(relatedObid);
                resultList.add(result);
            }
        }
        return resultList;
    }
    @Override
    public List<BusinessRelationObjectVO> getRelationList(SearchTargetInfo searchInfo){
        injectTarget(searchInfo);
        List<BusinessRelationObjectVO> resultList = schemaDao.selectList("Relation.getRelationList", searchInfo);
        return resultList;
    }

    private void injectTarget(SearchTargetInfo searchInfo){
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(searchInfo.getClassName());
        if (NullUtil.isNull(classInfo)) { throw new FoundationException("omc.error.classInfo.nodata"); }
        searchInfo.setDbmsTable(classInfo.getDbmsTable());
        searchInfo.setSql(classInfo.getConvert2SelectString());
    }
    @Override
    public List<VariableAttribute> getDynamicAttributeList(String dynamicAttributeGroup){
        List<VariableAttribute> result = schemaDao.selectList("DynamicAttribute.getDynamicAttributeList",
                dynamicAttributeGroup);
        return result;
    }
}
