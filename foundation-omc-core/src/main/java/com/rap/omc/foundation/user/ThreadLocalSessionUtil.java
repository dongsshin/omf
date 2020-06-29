package com.rap.omc.foundation.user;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.foundation.user.model.UserSessionVO;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.util.NullUtil;

public class ThreadLocalSessionUtil {
	static final String USER_SESSION = GlobalConstants.SESSION_USER_INFO;
	public static void pushExecutor(String userId){
        if (!NullUtil.isNone(userId) && !userId.equals(GlobalConstants.NO_USER_ID)) {
            UserSession userSession = (UserSession)SpringFactoryUtil.getBean(USER_SESSION);
            userSession.setExecutor(userId);
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.executor, userId);
        }
    }
    /**
     * 타 User의 권한을 반환한다.
     */
    public static void popExecutor(){
        UserSession userSession = (UserSession)SpringFactoryUtil.getBean(USER_SESSION);
        userSession.setExecutor(userSession.getUserId());
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.executor, userSession.getUserId());
    }

    /**
     * 타 UserId를 ThreadLocal의 userId로 설정한다.
     *
     * @param userId
     */
    public static void pushUserId(String userId){
        if (!NullUtil.isNone(userId) && !userId.equals(GlobalConstants.NO_USER_ID)) {
//            UserSession userSession = (UserSession)SpringFactoryUtil.getBean(GpdmConstants.SESSION_USER_INFO);
//            userSession.setUserId(userId);
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.userId, userId);
        }
    }

    /**
     * Session UserId를 ThreadLocal의 userId로 설정한다.
     */
    public static void popUserId(){
        UserSession userSession = (UserSession)SpringFactoryUtil.getBean(USER_SESSION);
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.userId, userSession.getUserId());
    }
    public static void refreshThreadLocalFromSession(UserSessionVO userSessionVO){
    	//UserSession userSession = (UserSession)SpringFactoryUtil.getBean(USER_SESSION);
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.userId, userSessionVO.getUserId());
        ThreadLocalUtil.set(ThreadLocalUtil.KEY.executor, userSessionVO.getUserId());
        
        if (!StrUtil.isEmpty(userSessionVO.getMainModule())) {
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.currentModule, userSessionVO.getMainModule());
        }
        if (!StrUtil.isEmpty(userSessionVO.getCompanyCode())) {
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.company, userSessionVO.getCompanyCode());
        }
        if (!StrUtil.isEmpty(userSessionVO.getBusinessUnitCode())) {
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.businessUnit, userSessionVO.getBusinessUnitCode());
        }
        if (!StrUtil.isEmpty(userSessionVO.getDivisionUnitCode())) {
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.divisionUnit, userSessionVO.getDivisionUnitCode());
        }
        if (!StrUtil.isEmpty(userSessionVO.getPlantUnitCode())) {
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.plantUnit, userSessionVO.getPlantUnitCode());
        }
        if (!StrUtil.isEmpty(userSessionVO.getUserTimeZone())) {
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.timeZone, userSessionVO.getUserTimeZone());
        }
        if (!StrUtil.isEmpty(userSessionVO.getPlantUnitCode())) {
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.plantUnit, userSessionVO.getPlantUnitCode());
        }
        if (!StrUtil.isEmpty(userSessionVO.getDivisionUnitCode())) {
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.divisionUnit, userSessionVO.getDivisionUnitCode());
        }
        if (!StrUtil.isEmpty(userSessionVO.getBusinessUnitCode())) {
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.businessUnit, userSessionVO.getBusinessUnitCode());
        }
        if (!StrUtil.isEmpty(userSessionVO.getUserLocale())) {
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.locale, userSessionVO.getUserLocale());
        }
    }
}
