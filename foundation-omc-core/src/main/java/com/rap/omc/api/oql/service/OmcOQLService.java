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
package com.rap.omc.api.oql.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rap.omc.api.oql.model.OmcOQLApiLogVO;
import com.rap.omc.api.oql.model.OmcOQLApiRelatedLogVO;
import com.rap.omc.api.oql.model.OmcOQLTableAndClass;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.model.schema.OmcOQLBusinessClass;
import com.rap.omc.api.oql.model.schema.OmcOQLClassAttribute;
import com.rap.omc.api.oql.model.schema.OmcOQLRelatedClassInfo;
import com.rap.omc.api.oql.model.schema.OmcOQLRelationClass;
import com.rap.omc.api.oql.model.schema.OmcOQLRelationShipInfo;
/**
 * 
 * <pre>
 * Class : OmcOQLService
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public interface OmcOQLService{
    public List<String> getChildClassList(OmcSQLVariableParameter variableParameter);
    public List<String> getStringList(OmcSQLVariableParameter variableParameter);
    public List<OmcOQLTableAndClass> getTableAndClassList(OmcSQLVariableParameter variableParameter);
    public List<OmcOQLClassAttribute> getClassAttributeList(OmcSQLVariableParameter variableParameter);
    public List<OmcOQLApiLogVO> getAPILlogByKeyValue(OmcOQLApiLogVO apiLogVO);
    public OmcOQLApiRelatedLogVO getAPIRelateLogByKeyValue(OmcOQLApiRelatedLogVO apiLogVO);
    public <T> List<T> getObjects(OmcSQLVariableParameter sqlVariableParameter, boolean isPaging);
    public <T> List<T> getObjects(OmcSQLVariableParameter sqlVariableParameter, String countSql, String executingSql, boolean isPaging);
    public <T> List<T> getObjects(OmcSQLVariableParameter sqlVariableParameter, String countSql, String executingSql, boolean isPaging, RowBounds paramRowBounds);
    public List<String> getClassNameList(List<String> obidList);
    public List<OmcOQLBusinessClass> getBusinessClassList(OmcSQLVariableParameter variableParameter);
    public List<OmcOQLRelationClass> getRelationClassList(OmcSQLVariableParameter variableParameter);
    public List<OmcOQLRelationShipInfo> getRelationShipInfoList(OmcSQLVariableParameter variableParameter);
    public List<OmcOQLRelatedClassInfo> getRelatedInfoList(OmcSQLVariableParameter variableParameter);
}
