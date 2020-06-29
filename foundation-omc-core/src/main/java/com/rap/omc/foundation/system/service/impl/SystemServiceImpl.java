package com.rap.omc.foundation.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rap.config.datasource.dao.FoundationDao;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.system.model.ChangeLogVO;
import com.rap.omc.foundation.system.model.TimeZoneVO;
import com.rap.omc.foundation.system.service.SystemService;



/**
 * <pre>
 * Class : SystemServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author youngmi.won
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {

    @Resource(name = "foundationDao")
    private FoundationDao foundationDao;

    public List<TimeZoneVO> getTimeZoneList(){
        return foundationDao.selectList("System.getTimeZoneList");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertChangeLog(ChangeLogVO input){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        input.setCreator(userId);
        input.setModifier(userId);
        foundationDao.insert("System.insertChangeLog", input);
    }

    public List<ChangeLogVO> getChangeLogList(ChangeLogVO searchInfo){
        return foundationDao.selectList("System.getChangeLogList", searchInfo);
    }
}
