package com.rap.omc.framework.resolver;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.general.UserManagementUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.UserPropertyConstants;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.foundation.user.model.UserSessionVO;

public class I18nLocaleSessionResolver extends SessionLocaleResolver {
    @Autowired
    private UserSession userSession;
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = (Locale) WebUtils.getSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME);
        if (null == locale) {
        	UserSessionVO userSessionVO = (UserSessionVO)WebUtils.getSessionAttribute(request, GlobalConstants.SESSION_USER_INFO);
            if(null != userSessionVO ) {
                if(StrUtil.isEmpty(userSessionVO.getUserLocale())) {
                    locale = new Locale.Builder().setLanguage(GlobalConstants.DEFAULT_LANG).build();
                    WebUtils.setSessionAttribute(request, GlobalConstants.SESSION_USER_INFO, userSessionVO);
                    userSession.setUserLocale(userSessionVO.getUserLocale());
                    UserManagementUtil.setUserProperty( userSession.getUserId(), UserPropertyConstants.USER_PROP_LOCALE, GlobalConstants.DEFAULT_LANG );
                }else{
                    locale = new Locale.Builder().setLanguage(userSession.getUserLocale()).build();
                }
                WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            }
        }
        return locale;
    }
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, locale);
        UserSessionVO userSessionVO = (UserSessionVO)WebUtils.getSessionAttribute(request, GlobalConstants.SESSION_USER_INFO);
        if(null != userSessionVO) {
        	userSessionVO.setUserLocale(locale.getLanguage());
            WebUtils.setSessionAttribute(request, GlobalConstants.SESSION_USER_INFO, userSessionVO);
            userSession.setUserLocale(locale.getLanguage());
            UserManagementUtil.setUserProperty( userSession.getUserId(), UserPropertyConstants.USER_PROP_LOCALE, locale.getLanguage());
            //LoginController.setThreadLocalFromSession(userSession);
        }
    }
}
