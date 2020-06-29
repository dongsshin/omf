/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CommonDaoUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 5. 19. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.rap.config.datasource.dao.FoundationDao;
import com.rap.omc.api.util.spring.SpringFactoryUtil;


/**
 * <pre>
 * Class : CommonDaoUtil
 * Description : Dom에서 commonDao를 사용하여 직접 query를 수행하고자 할 때, 매번 getBean하지 않고 재사용성을 고려하여 util 형태로 제공함 
 *               # QueryID와 파라미터를 전달하면 해당 쿼리를 수행하여 결과 값을 리턴함
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class CommonDaoUtil {

    private static CommonDaoUtil cInstance;
	@Autowired
    private FoundationDao commonDao;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static CommonDaoUtil getInstance(){
        if (cInstance == null) {
            cInstance = new CommonDaoUtil();
            cInstance.commonDao = (FoundationDao)SpringFactoryUtil.getBean("foundationDao");
        }
        return cInstance;
    }

    public static <T> T select(String queryId, Object paramObject){
        return getInstance().commonDao.select(queryId, paramObject);
    }

    public static <E> List<E> selectList(String queryId, Object paramObject){
        return getInstance().commonDao.selectList(queryId, paramObject);
    }

    public static int insert(String queryId, Object paramObject){
        return getInstance().commonDao.insert(queryId, paramObject);
    }

    public static int update(String queryId, Object paramObject){
        return getInstance().commonDao.update(queryId, paramObject);
    }

    public static int delete(String queryId, Object paramObject){
        return getInstance().commonDao.delete(queryId, paramObject);
    }
    
    public static void callProcedure(String queryId, Object paramObject){
        getInstance().commonDao.select(queryId, paramObject);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> callProcedureAndReturnResult(String queryId, Map<String, Object> paramObject){
        getInstance().commonDao.select(queryId, paramObject);

        List<T> resultList = (List<T>)paramObject.get("sysRefcursor");
        return resultList;
    }
    
    public static void callProcedureOutClob(String queryId, Map<String, Object> paramObject){
        getInstance().commonDao.select(queryId, paramObject);
        
    }    
}
