/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : TimeServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 5.  yongsik1.kim   Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.FoundationDao;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.foundation.classes.service.TimeService;
import com.rap.omc.foundation.system.model.TimeZoneVO;
import com.rap.omc.schema.util.OmcDBMSConstants;


@Service("timeService")
public class TimeServiceImpl implements TimeService {
    
    @Resource(name = "foundationDao")
    private FoundationDao foundationDao;
    /**
     * DBMS의 년단위 주 차를 return함.
     * 
     * @return
     * @see omc.foundation.classes.service.TimeService#getWeekOfYear()
     */
    @Override
    public int getWeekOfYear(){
        ObjectRootVO searchInfo = new ObjectRootVO();
        if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_MARIA)){
            searchInfo.setSql("select weekofyear(omcGetUtcDate()) from dual");
        }else if(OmcDBMSConstants.DBMS_CURRENT.equals(OmcDBMSConstants.C_DBMS_ORACLE)){
            searchInfo.setSql("select to_char(omcGetUtcDate(),'iw') from dual");
        }else{
            searchInfo.setSql("select weekofyear(omcGetUtcDate()) from dual");
        }
        return foundationDao.select("Time.getDBWeekOfYear", searchInfo);
    }
    /**
     * 
     * @see omc.foundation.common.service.TimeService#getDBLocalTime()
     */
    @Override
    public Date getDBLocalTime(){
        ObjectRootVO searchInfo = new ObjectRootVO();
        searchInfo.setSql("select omcGetLocalDate() from dual");
        return foundationDao.select("Time.getDBLocalTime", searchInfo);
    }
    
    /**
     * 
     * @see omc.foundation.common.service.TimeService#getDBLocalTime()
     */
    @Override
    public String getDBLocalTimeStr(){
        ObjectRootVO searchInfo = new ObjectRootVO();
        searchInfo.setObid("select omcGetLocalDateChar('') from dual");
        return foundationDao.select("Time.getDBLocalTimeStr",searchInfo);
    }
    @Override
    public List<TimeZoneVO> getTimeZoneList(){
        return foundationDao.selectList("System.getTimeZoneList");
    }
}
