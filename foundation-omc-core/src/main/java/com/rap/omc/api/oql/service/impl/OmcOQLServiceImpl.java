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
package com.rap.omc.api.oql.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.oql.model.OmcOQLApiLogVO;
import com.rap.omc.api.oql.model.OmcOQLApiRelatedLogVO;
import com.rap.omc.api.oql.model.OmcOQLTableAndClass;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.model.schema.OmcOQLBusinessClass;
import com.rap.omc.api.oql.model.schema.OmcOQLClassAttribute;
import com.rap.omc.api.oql.model.schema.OmcOQLRelatedClassInfo;
import com.rap.omc.api.oql.model.schema.OmcOQLRelationClass;
import com.rap.omc.api.oql.model.schema.OmcOQLRelationShipInfo;
import com.rap.omc.api.oql.service.OmcOQLService;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.util.NullUtil;

/**
 * 
 * <pre>
 * Class : OmcOQLServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@Service("omcOQLService")
public class OmcOQLServiceImpl implements OmcOQLService {
    
	@Resource(name = "schemaDao")
    private SchemaDao schemaDao;

    public List<String> getChildClassList(OmcSQLVariableParameter variableParameter){
        List<String> result = schemaDao.selectList("OmcOql.omcOqlDynamicSelectForString", variableParameter);
        return result;
    }
    public List<String> getStringList(OmcSQLVariableParameter variableParameter){
        List<String> result = schemaDao.selectList("OmcOql.getStringList", variableParameter);
        return result;
    }
    public List<OmcOQLTableAndClass> getTableAndClassList(OmcSQLVariableParameter variableParameter){
        List<OmcOQLTableAndClass> result = schemaDao.selectList("OmcOql.getTableAndClassList", variableParameter);
        return result;
    }
    public List<OmcOQLClassAttribute> getClassAttributeList(OmcSQLVariableParameter variableParameter){
        List<OmcOQLClassAttribute> result = schemaDao.selectList("OmcOql.getClassAttributeList", variableParameter);
        return result;
    }
    public List<OmcOQLApiLogVO> getAPILlogByKeyValue(OmcOQLApiLogVO apiLogVO){
        List<OmcOQLApiLogVO> result = schemaDao.selectList("OmcOql.getAPILogByKeyValue", apiLogVO);
        return result;
    }
    
    public List<String> getClassNameList(List<String> obidList){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("obidList", obidList);
        List<String> result = schemaDao.selectList("OmcOql.omcOqlUniqueClassName", map);
        return result;
    }
    
    public OmcOQLApiRelatedLogVO getAPIRelateLogByKeyValue(OmcOQLApiRelatedLogVO apiLogVO){
        OmcOQLApiRelatedLogVO result = schemaDao.select("OmcOql.getAPIRelateLogByKeyValue", apiLogVO);
        return result;
    }
    public <T> List<T> getObjects(OmcSQLVariableParameter sqlVariableParameter, String countSql, String executingSql, boolean isPaging){
        return getObjects(sqlVariableParameter, countSql, executingSql, isPaging,null);
    }
    public <T> List<T> getObjects(OmcSQLVariableParameter sqlVariableParameter, String countSql, String executingSql, boolean isPaging, RowBounds paramRowBounds){
        List<T> result;
        Map<String,Object> attributeMap = sqlVariableParameter.getAttributeMap();
        if(isPaging){
            attributeMap.put("sql", countSql);
            Integer totalCount = schemaDao.select("OmcOql.getTotalCount", attributeMap);
            attributeMap.put("sql", executingSql);
            result = schemaDao.selectList("OmcOql.getObject", attributeMap);
            PagingList<T> pagedList = new PagingList<T>();
            pagedList.addAll(result);
            pagedList.setRowSize(sqlVariableParameter.getRowSize());
            pagedList.setTargetRow(sqlVariableParameter.getTargetRow());
            pagedList.setCurrentPage(sqlVariableParameter.getTargetRow(), sqlVariableParameter.getRowSize());
            pagedList.setRows(totalCount);
            result = pagedList;
        }else{
            attributeMap.put("sql", executingSql);
            if(NullUtil.isNull(paramRowBounds)){
                result = schemaDao.selectList("OmcOql.getObject", attributeMap);
            }else{
                result = schemaDao.selectList("OmcOql.getObject", attributeMap, paramRowBounds);
            }
        }
        return result;
    }
    public <T> List<T> getObjects(OmcSQLVariableParameter sqlVariableParameter, boolean isPaging){
        List<T> result;
        if(isPaging){
            result = schemaDao.selectPagedList("OmcOql.getObject", sqlVariableParameter);
        }else{
            result = schemaDao.selectList("OmcOql.getObject", sqlVariableParameter);
        }
        return result;
    }
    public List<OmcOQLBusinessClass> getBusinessClassList(OmcSQLVariableParameter variableParameter){
        List<OmcOQLBusinessClass> result = schemaDao.selectList("OmcOql.getBusinessClassList", variableParameter);
        return result;
    }

    public List<OmcOQLRelationClass> getRelationClassList(OmcSQLVariableParameter variableParameter){
        List<OmcOQLRelationClass> result = schemaDao.selectList("OmcOql.getRelationClassList", variableParameter);
        return result;
    }

    public List<OmcOQLRelationShipInfo> getRelationShipInfoList(OmcSQLVariableParameter variableParameter){
        List<OmcOQLRelationShipInfo> result = schemaDao.selectList("OmcOql.getRelationShipInfoList", variableParameter);
        return result;
    }
    public List<OmcOQLRelatedClassInfo> getRelatedInfoList(OmcSQLVariableParameter variableParameter){
        List<OmcOQLRelatedClassInfo> result = schemaDao.selectList("OmcOql.getRelatedInfoList", variableParameter);
        return result;
    }

}