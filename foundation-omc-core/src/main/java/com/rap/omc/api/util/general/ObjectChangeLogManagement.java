package com.rap.omc.api.util.general;

import java.util.Date;
import java.util.List;

import com.rap.omc.api.util.EtcUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.system.model.ChangeLogVO;
import com.rap.omc.foundation.system.model.TimeZoneVO;
import com.rap.omc.foundation.system.service.SystemService;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : ObjectChangeLogManagement
 * Description : Change Log관리를 위한 Class
 * </pre>
 * 
 * @author youngmi.won
 */
public class ObjectChangeLogManagement {

    private SystemService systemService;

    private static ObjectChangeLogManagement aInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static ObjectChangeLogManagement getInstance(){
        if (aInstance == null) {
            aInstance = new ObjectChangeLogManagement();
            aInstance.systemService = (SystemService)SpringFactoryUtil.getBean("systemService");
        }
        return aInstance;
    }
    /**
     * Get Time Zone List
     *
     * @return Time Zone List
     */
    public static List<TimeZoneVO> getTimeZoneList(){
        return getInstance().systemService.getTimeZoneList();
    }

    /**
     * changeLog를 기록한다.
     *
     * @param changingCategory ChangeOwner,CheckOut
     * @param targetObid
     * @param targetNames Target Names (Relation:'-')
     * @param targetClass 
     * @param changingUser change를 수행한 userID
     * @param attribute01
     * @param attribute02
     * @param attribute03
     * @param attribute04
     * @param attribute05
     */
    public static void createChangeLog(String changingCategory, String targetObid, String targetNames,
            String targetClass, String changingUser, String attribute01, String attribute02, String attribute03,
            String attribute04, String attribute05){
        ChangeLogVO input = new ChangeLogVO();
        input.setChangingCategory(changingCategory);
        input.setTargetObid(targetObid);
        input.setTargetNames(targetNames);
        input.setTargetClass(targetClass);
        if (NullUtil.isNone(changingUser)) {
            String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
            input.setChangingUser(userId);
        } else {
            input.setChangingUser(changingUser);
        }
        input.setChangedDate(new Date());
        input.setAttribute01(EtcUtil.convertNullToString(attribute01));
        input.setAttribute02(EtcUtil.convertNullToString(attribute02));
        input.setAttribute03(EtcUtil.convertNullToString(attribute03));
        input.setAttribute04(EtcUtil.convertNullToString(attribute04));
        input.setAttribute05(EtcUtil.convertNullToString(attribute05));

        getInstance().systemService.insertChangeLog(input);
    }

    /**
     * ChangeLog를 조회한다.
     *
     * @param changingCategory ChangeOwner,CheckOut
     * @param targetObid
     * @param targetNames Target Names (Relation:'-')
     * @param targetClass target class명 (','를 사용하여 여러 개 등록 가능)
     * @param changingUser change를 수행한 userID
     * @param startChangedDate 검색조건(변경날짜 - 시작날짜)
     * @param endChangedDate 검색조건(변경날짜 - 종료날짜)
     * @param attribute01
     * @param attribute02
     * @param attribute03
     * @param attribute04
     * @param attribute05
     * @return Change Log List
     */
    public static List<ChangeLogVO> getChangedHistorys(String changingCategory, String targetObid,
            String targetNames, String targetClass, String changingUser, Date startChangedDate, Date endChangedDate,
            String attribute01, String attribute02, String attribute03, String attribute04, String attribute05){

        ChangeLogVO searchInfo = new ChangeLogVO();
        searchInfo.setChangingCategory(changingCategory);
        searchInfo.setTargetObid(targetObid);
        searchInfo.setTargetNames(EtcUtil.convertStringToSqlLike(targetNames));
        if (!targetClass.equals(GlobalConstants.FLAG_TYPE_ALL)) {
            searchInfo.setTargetClass(BaseFoundationUtil.getStringInheritanceClassList(targetClass));
        }        
        searchInfo.setChangingUser(EtcUtil.convertStringToSqlLike(changingUser));
        searchInfo.setStartChangedDate(startChangedDate);
        searchInfo.setEndChangedDate(endChangedDate);
        searchInfo.setAttribute01(EtcUtil.convertStringToSqlLike(attribute01));
        searchInfo.setAttribute02(EtcUtil.convertStringToSqlLike(attribute02));
        searchInfo.setAttribute03(EtcUtil.convertStringToSqlLike(attribute03));
        searchInfo.setAttribute04(EtcUtil.convertStringToSqlLike(attribute04));
        searchInfo.setAttribute05(EtcUtil.convertStringToSqlLike(attribute05));

        return getInstance().systemService.getChangeLogList(searchInfo);
    }
}
