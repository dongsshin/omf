/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : FcsServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 4.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.foundation.file.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.foundation.file.model.FcsLocationVO;
import com.rap.omc.foundation.file.service.FcsService;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;




/**
 * <pre>
 * Class : FcsServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
@Service("fcsService")
public class FcsServiceImpl implements FcsService{

    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;

    @Cacheable(value = "locationInfoCache", key = "#names")
    public FcsLocationVO getFileLocationOrStore(String names){
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        variableParameter.setFunVariable_00001(names);
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSFILELOCATION_FLAG_Active));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSFILESTORE_FLAG_Active));
        return schemaDao.select("FcsInfo.getLocationOrStore", variableParameter);
    }
    @Cacheable(value = "locationInfoCache", key="T(java.lang.String).valueOf('L-').concat(#lifeCycle)")
    public FcsLocationVO getCurrentStoreForLifeCycle(String lifeCycle){
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        variableParameter.setFunVariable_00001(lifeCycle);
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSREL_KIND_PolicyStore));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSLIFEINFO_FLAG_Active));
        variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSREL_FLAG_Active));
        variableParameter.setFunVariable_00005(String.valueOf(OmcSystemConstants.SYSFILESTORE_FLAG_Active));
        FcsLocationVO fcsLocationVO = schemaDao.select("FcsInfo.getStoreByLifeCycle", variableParameter);
       return fcsLocationVO;
    }
    @Override
    @Cacheable(value = "valueCache", key="T(java.lang.String).valueOf('S-').concat(#site).concat(#storeName)")
    public FcsLocationVO getFcsLocationForSite(String site, String storeName){
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        variableParameter.setFunVariable_00001(site);
        variableParameter.setFunVariable_00002(storeName);
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSFILESTORE_FLAG_Active));
        variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSREL_FLAG_Active));
        variableParameter.setFunVariable_00005(String.valueOf(OmcSystemConstants.SYSREL_KIND_StoreIncludeLoc));
        variableParameter.setFunVariable_00006(String.valueOf(OmcSystemConstants.SYSFILELOCATION_FLAG_Active));
        variableParameter.setFunVariable_00007(String.valueOf(OmcSystemConstants.SYSREL_FLAG_Active));
        variableParameter.setFunVariable_00008(String.valueOf(OmcSystemConstants.SYSREL_KIND_SiteHasLoc));
        variableParameter.setFunVariable_00009(String.valueOf(OmcSystemConstants.SYSSITE_FLAG_Active));
        List<FcsLocationVO> list = schemaDao.selectList("FcsInfo.getStoreLocationBySite", variableParameter);
        if(!NullUtil.isNone(list)) return list.get(0);
        variableParameter = new OmcSQLVariableParameter(); 
        variableParameter.setFunVariable_00001(storeName);
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSFILESTORE_FLAG_Active));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSREL_FLAG_Active));
        variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSREL_KIND_StoreIncludeLoc));
        variableParameter.setFunVariable_00005(String.valueOf(OmcSystemConstants.SYSFILELOCATION_FLAG_Active));
        list = schemaDao.selectList("FcsInfo.getLocationWithStore", variableParameter);
        if(!NullUtil.isNone(list)) return list.get(0);
        
        variableParameter = new OmcSQLVariableParameter(); 
        variableParameter.setFunVariable_00001(storeName);
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSFILESTORE_FLAG_Active));
        
        list = schemaDao.selectList("FcsInfo.getStoreWithStore", variableParameter);
        if(!NullUtil.isNone(list)) return list.get(0);
        return null;
    }
}
