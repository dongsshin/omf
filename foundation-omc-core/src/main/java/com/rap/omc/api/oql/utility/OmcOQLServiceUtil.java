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
package com.rap.omc.api.oql.utility;

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
import com.rap.omc.api.oql.service.OmcOQLService;
import com.rap.omc.api.util.spring.SpringFactoryUtil;

/**
 * 
 * <pre>
 * Class : OmcOQLServiceUtil
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLServiceUtil {

    private OmcOQLService omcOQLService;

    private static OmcOQLServiceUtil cInstance;
    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static OmcOQLServiceUtil getInstance(){
        if (cInstance == null) {
            cInstance = new OmcOQLServiceUtil();
            cInstance.omcOQLService = (OmcOQLService)SpringFactoryUtil.getBean("omcOQLService");
        }
        return cInstance;
    }
    public static List<String> getChildClassList(OmcSQLVariableParameter variableParameter){
        return getInstance().omcOQLService.getChildClassList(variableParameter);
    }
    public static List<String> getStringList(OmcSQLVariableParameter variableParameter){
            return getInstance().omcOQLService.getStringList(variableParameter);
        }
    public static List<OmcOQLTableAndClass> getTableAndClassList(OmcSQLVariableParameter variableParameter){
        return getInstance().omcOQLService.getTableAndClassList(variableParameter);
    }
    public static List<OmcOQLClassAttribute> getClassAttributeList(OmcSQLVariableParameter variableParameter){
        return getInstance().omcOQLService.getClassAttributeList(variableParameter);
    }
    public static List<OmcOQLApiLogVO> getAPILlogByKeyValue(OmcOQLApiLogVO apiLogVO){
        return getInstance().omcOQLService.getAPILlogByKeyValue(apiLogVO);
    }
    
    public static OmcOQLApiRelatedLogVO getAPIRelateLogByKeyValue(OmcOQLApiRelatedLogVO apiLogVO){
        return getInstance().omcOQLService.getAPIRelateLogByKeyValue(apiLogVO);
    }
    public static List<String> getClassNameList(List<String> obidList){
        return getInstance().omcOQLService.getClassNameList(obidList);
    }

    public static <T> List<T> getObjects(OmcSQLVariableParameter sqlVariableParameter,boolean isPaging){
        return getInstance().omcOQLService.getObjects(sqlVariableParameter,isPaging);
    }
    public static <T> List<T> getObjects(OmcSQLVariableParameter sqlVariableParameter, String countSql, String executingSql, boolean isPaging, RowBounds paramRowBounds){
        return getInstance().omcOQLService.getObjects(sqlVariableParameter,countSql,executingSql,isPaging,paramRowBounds);
    }
    public static <T> List<T> getObjects(OmcSQLVariableParameter sqlVariableParameter, String countSql, String executingSql, boolean isPaging){
        return getInstance().omcOQLService.getObjects(sqlVariableParameter,countSql,executingSql,isPaging);
    }
    
    public static List<OmcOQLBusinessClass> getBusinessClassList(OmcSQLVariableParameter sqlVariableParameter){
        return getInstance().omcOQLService.getBusinessClassList(sqlVariableParameter);
    }
    public static List<OmcOQLRelationClass> getRelationClassList(OmcSQLVariableParameter sqlVariableParameter){
        return getInstance().omcOQLService.getRelationClassList(sqlVariableParameter);
    }
    public static List<OmcOQLRelationShipInfo> getRelationShipInfoList(OmcSQLVariableParameter sqlVariableParameter){
        return getInstance().omcOQLService.getRelationShipInfoList(sqlVariableParameter);
    }
    public static List<OmcOQLRelatedClassInfo> getRelatedInfoList(OmcSQLVariableParameter sqlVariableParameter){
        return getInstance().omcOQLService.getRelatedInfoList(sqlVariableParameter);
    }
    
}
