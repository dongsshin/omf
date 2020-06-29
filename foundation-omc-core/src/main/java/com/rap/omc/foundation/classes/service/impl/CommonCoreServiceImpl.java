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

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.foundation.classes.service.CommonCoreService;



/**
 * <pre>
 * Class : CommonCoreServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@Service("commonCoreService")
public class CommonCoreServiceImpl implements CommonCoreService {
    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;
    // @Cacheable(value = "classInfoCache", key = "#className")
    public String getClassNameWithObid(String obid){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("obid", obid);
         return (String)schemaDao.select("Common1.getKeyInfo1", map);
    }
}
