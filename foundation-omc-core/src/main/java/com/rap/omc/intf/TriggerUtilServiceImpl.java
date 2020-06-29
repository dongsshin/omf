
package com.rap.omc.intf;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.FoundationDao;

@Service("triggerUtilService")
public class TriggerUtilServiceImpl implements TriggerUtilService {

	@Resource(name = "foundationDao")
	private FoundationDao foundationDao;

	@Override
    public void createDataByTrigger(IfPlmDataByTriggerVO triggerVO) {
	    Integer exists = foundationDao.select("Integration.dupicateDataByTrigger",triggerVO);
	    if(exists.equals(0)){
	    	foundationDao.insert("Integration.insertDataByTrigger",triggerVO);
	    }
   }
	
   @Override
   public void createDataByTriggerPjtCommonAll(IfPlmDataByTriggerVO triggerVO) {
        Integer exists = foundationDao.select("Integration.dupicateDataByTriggerPjtCommonAll",triggerVO);
        if(exists.equals(0)){
        	foundationDao.insert("Integration.insertDataByTrigger",triggerVO);
        }
   }
	   
   @Override
   public void createDataByTriggerPjtModel(IfPlmDataByTriggerVO triggerVO) {
        Integer exists = foundationDao.select("Integration.dupicateDataByTriggerPjtModel",triggerVO);
        if(exists.equals(0)){
        	foundationDao.insert("Integration.insertDataByTrigger",triggerVO);
        }
   }
}