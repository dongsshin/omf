package com.rap.omc.foundation.system.service;

import java.util.List;

import com.rap.omc.foundation.system.model.ChangeLogVO;
import com.rap.omc.foundation.system.model.TimeZoneVO;



/**
 * <pre>
 * Class : SystemService
 * Description : TODO
 * </pre>
 * 
 * @author youngmi.won
 */
public interface SystemService {

    public List<TimeZoneVO> getTimeZoneList();

    public void insertChangeLog(ChangeLogVO input);

    public List<ChangeLogVO> getChangeLogList(ChangeLogVO searchInfo);

}
