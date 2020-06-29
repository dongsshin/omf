/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : TimeService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 5.  yongsik1.kim   Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.service;

import java.util.Date;
import java.util.List;

import com.rap.omc.foundation.system.model.TimeZoneVO;

/**
 * <pre>
 * Class : TimeService
 * Description : TODO
 * </pre>
 * 
 * @author yongsik1.kim
 */
public interface TimeService {
    
   public int getWeekOfYear();
   
   public Date getDBLocalTime();

   public String getDBLocalTimeStr();
   
   public List<TimeZoneVO> getTimeZoneList();
}
