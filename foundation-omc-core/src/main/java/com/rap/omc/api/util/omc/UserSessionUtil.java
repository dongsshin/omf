package com.rap.omc.api.util.omc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rap.config.redis.JsonRedisTemplate;
import com.rap.config.web.security.UserSessionMappinKey;
import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.UserManagementUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.RedisConstants;
import com.rap.omc.foundation.user.ThreadLocalSessionUtil;
import com.rap.omc.foundation.user.model.SysUserVO;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.foundation.user.model.UserSessionVO;
import com.rap.omc.api.util.spring.SpringFactoryUtil;

public class UserSessionUtil {
	@Autowired
	private UserSession userSession;
	private static UserSessionUtil fInstance;
    
    @Autowired
    private JsonRedisTemplate jsonRedisTemplate;

    @SuppressWarnings("unchecked")
	private synchronized static UserSessionUtil getInstance(){
        if (fInstance == null) {
            fInstance = new UserSessionUtil();
            fInstance.userSession = (UserSession)SpringFactoryUtil.getBean(GlobalConstants.SESSION_USER_INFO);
            fInstance.jsonRedisTemplate = (JsonRedisTemplate)SpringFactoryUtil.getBean("jsonRedisTemplate");
        }
        return fInstance;
    }
	public static UserSessionVO refreshUserSession(String userId) {
		SysUserVO sysUserVO = UserManagementUtil.getUserInfo(userId);
		return refreshUserSession(sysUserVO);
	}
	@SuppressWarnings("unchecked")
	public static UserSessionVO refreshUserSession(SysUserVO sysUserVO) {
		getInstance().userSession.setUserId(sysUserVO.getUserId());
		getInstance().userSession.setUserNameEng(sysUserVO.getDescriptions());
		getInstance().userSession.setGroupSet(sysUserVO.getGroupSet());
		getInstance().userSession.setRoleSet(sysUserVO.getRoleSet());
		getInstance().userSession.getRoleSet().add("General User Role");
		getInstance().userSession.setUserLoginSite(sysUserVO.getSite());
		getInstance().userSession.setPropertyMap(sysUserVO.getPropertyList());
		
        BusinessObjectMasterVO VO  = BusinessObjectMaster.findBusinessObjectMaster("Users", sysUserVO.getUserId());
        BusinessObjectMaster dom = DomUtil.toDom(VO.getObid());
        Map<String,Object> map = new HashMap<String,Object>();
        try {
	        Method method = dom.getClass().getMethod(UserSessionMappinKey.getUserSessionInfo);
	        map = (Map<String,Object>)method.invoke(dom);
        }
        catch (NoSuchMethodException e) {
        	e.printStackTrace();
        }catch (SecurityException e) {
        	e.printStackTrace();
        }
        catch (IllegalAccessException e) {
        	e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        Set<String> managementRoleSet  = (Set<String>)map.get(UserSessionMappinKey.managementRoleSet);
        
        getInstance().userSession.setManagementRoleSet(managementRoleSet);
        getInstance().userSession.setWorkGroup((String)map.get(UserSessionMappinKey.workGroup));
        
        getInstance().userSession.setCompanyCode((String)map.get(UserSessionMappinKey.companyCode));
        getInstance().userSession.setCompanyCodeDesc((String)map.get(UserSessionMappinKey.companyCodeDesc));
        
        getInstance().userSession.setDivisionUnitCode((String)map.get(UserSessionMappinKey.divisionUnitCode));
        getInstance().userSession.setDivisionUnitCodeDesc((String)map.get(UserSessionMappinKey.divisionUnitCodeDesc));
        
        getInstance().userSession.setBusinessUnitCode((String)map.get(UserSessionMappinKey.businessUnitCode));
        getInstance().userSession.setBusinessUnitCodeDesc((String)map.get(UserSessionMappinKey.businessUnitCodeDesc));
        
        getInstance().userSession.setBusinessUnitCode((String)map.get(UserSessionMappinKey.businessUnitCode));
        getInstance().userSession.setBusinessUnitCodeDesc((String)map.get(UserSessionMappinKey.businessUnitCodeDesc));
        
        getInstance().userSession.setUserLocale((String)map.get(UserSessionMappinKey.userLocale));
        getInstance().userSession.setUserTimeZone((String)map.get(UserSessionMappinKey.userTimeZone));
        getInstance().userSession.setDaylightSavings((String)map.get(UserSessionMappinKey.daylightSavings));
        
        getInstance().userSession.setDefaultProject((String)map.get(UserSessionMappinKey.defaultProject));
        getInstance().userSession.setPrivateFolder((String)map.get(UserSessionMappinKey.privateFolder));
        getInstance().userSession.setDefaultPrivateFolder((String)map.get(UserSessionMappinKey.defaultPrivateFolder));
        getInstance().userSession.setDefaultProjectFolder((String)map.get(UserSessionMappinKey.defaultProjectFolder));
        
        
        getInstance().userSession.setEmail((String)map.get(UserSessionMappinKey.email));
        getInstance().userSession.setTelephone((String)map.get(UserSessionMappinKey.telephone));
        getInstance().userSession.setUserNameKor((String)map.get(UserSessionMappinKey.userNameKor));
        getInstance().userSession.setUserNameEng((String)map.get(UserSessionMappinKey.userNameEng));
        
        getInstance().userSession.setDepartmentCode((String)map.get(UserSessionMappinKey.departmentCode));
        getInstance().userSession.setDepartmentDesc((String)map.get(UserSessionMappinKey.departmentDesc));
        getInstance().userSession.setAccountingDepartmentCode((String)map.get(UserSessionMappinKey.accountingDepartmentCode));
        getInstance().userSession.setAccountingDepartmentCodeDesc((String)map.get(UserSessionMappinKey.accountingDepartmentCodeDesc));
        
        getInstance().userSession.setLeaderUserId((String)map.get(UserSessionMappinKey.leaderUserId));
        getInstance().userSession.setMainModule((String)map.get(UserSessionMappinKey.mainModule));
        getInstance().userSession.setTimeStamp((String)map.get(UserSessionMappinKey.timeStamp));
        
        getInstance().userSession.setLoginUrl((String)map.get(UserSessionMappinKey.loginUrl));
        getInstance().userSession.setExcutorId((String)map.get(sysUserVO.getUserId()));
        
        UserSessionVO userSessionVO = new UserSessionVO();
        BeanUtils.copyProperties(getInstance().userSession, userSessionVO);
        registerUserSessionToRedis(sysUserVO.getUserId(),userSessionVO);
        ThreadLocalSessionUtil.refreshThreadLocalFromSession(userSessionVO);
        return userSessionVO;
	}
	private static void registerUserSessionToRedis(String userId,  UserSessionVO userSessionVO) {
		ValueOperations<String, Object> vop = getInstance().jsonRedisTemplate.opsForValue();
		vop.set(getUserSessionVOKey(userId),userSessionVO);
	}
	public static UserSessionVO getUserSeesionVOFromRedis(String userId) {
		ValueOperations<String, Object> vop = getInstance().jsonRedisTemplate.opsForValue();
		Object obj = vop.get(getUserSessionVOKey(userId));
		ObjectMapper mapper = new ObjectMapper();
		UserSessionVO userSessionVO = null;
		try {
			userSessionVO = mapper.convertValue(obj, UserSessionVO.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return userSessionVO;
	}
	private static String getUserSessionVOKey(String userId) {
		return RedisConstants.REDIS_USER_SESSION_KEY_FROMAT.replace(RedisConstants.REDIS_USER_ID_PARAMETER, userId);
	}
}