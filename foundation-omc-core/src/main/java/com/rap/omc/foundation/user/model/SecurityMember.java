package com.rap.omc.foundation.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.rap.omc.foundation.user.model.SysUserVO;

public class SecurityMember extends User{
	private static final String ROLE_PREFIX = "ROLE_";
    private static final long serialVersionUID = 1L;
    
    private String ip;
    
    public SecurityMember(SysUserVO sysUserVO) {
    	super(sysUserVO.getUserId(), sysUserVO.getPassword(), (Collection<? extends GrantedAuthority>) makeGrantedAuthority(sysUserVO.getRoleSet()));
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    private static List<? extends GrantedAuthority> makeGrantedAuthority(Set<String> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role)));
        return list;
    }
}
