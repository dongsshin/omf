package com.rap.omc.foundation.user.service;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rap.omc.api.util.omc.UserSessionUtil;
import com.rap.omc.api.util.general.UserManagementUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.user.model.SecurityMember;
import com.rap.omc.foundation.user.model.SysUserVO;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.foundation.user.model.UserSessionVO;
import com.rap.omc.util.NullUtil;

@Service("foundationUserDetailsService")
public class FoundationUserDetailsService implements UserDetailsService{
	static final Logger LOGGER = LoggerFactory.getLogger(FoundationUserDetailsService.class);
	@Autowired
	UserSession userSession;
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	SysUserVO sysUserVO = null;
    	try {
    		sysUserVO = UserManagementUtil.getUserInfo(username);
    	}catch(Exception e) {
    		throw new UsernameNotFoundException(username);
    	}
    	if(NullUtil.isNull(sysUserVO)) throw new UsernameNotFoundException(username);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
    	UserSessionVO userSessionVO = UserSessionUtil.refreshUserSession(sysUserVO);
        session.setAttribute(GlobalConstants.SESSION_USER_INFO, userSessionVO);
        LOGGER.info(userSessionVO.toString());
        LOGGER.info(userSession.toString());
        return new SecurityMember(sysUserVO);
    }
}