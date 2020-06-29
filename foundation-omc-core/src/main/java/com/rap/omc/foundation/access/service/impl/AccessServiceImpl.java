package com.rap.omc.foundation.access.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.general.UserManagementUtil;
import com.rap.omc.foundation.access.service.AccessService;
import com.rap.omc.foundation.user.model.SysUserVO;
import com.rap.omc.foundation.user.model.User2UserResultVO;
import com.rap.omc.util.NullUtil;



/**
 * <pre>
 * Class : AccessServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author youngmi.won
 */
@Service("accessService")
public class AccessServiceImpl implements AccessService {
    @Override
    public Set<String> getRoleList(String userId){
        SysUserVO sysUserVO = UserManagementUtil.getUserInfo(userId);
        if(NullUtil.isNull(sysUserVO)) return Collections.emptySet();
        return(sysUserVO.getRoleSet());
    }

    /**
     * has role
     * 
     * @param role
     * @param userNames
     * @return
     * @see omc.foundation.access.service.AccessService#hasRole(java.lang.String, java.lang.String)
     */
    @Override
    public boolean hasRole(String role, String userId){
        Set<String> roleVOList = getRoleList(userId);
        for(String wrole : roleVOList){
            if (wrole.equals(role)) { return true; }
        }
        return false;
    }

    /**
     * IAM Role Update
     * @param userId
     * @param roles
     * @param basedRoles
     * @see omc.foundation.access.service.AccessService#txnUpdateIAMRole(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void txnUpdateIAMRole(String userId, String newRoles, String basedRoles) {
        Set<String> newRoleSet         = Collections.<String>emptySet();
        Set<String> willBeAddedRoleSet   = new HashSet<String>();
        Set<String> willBeRemovedRoleSet = new HashSet<String>();
        Set<String> basedRoleSet         = Collections.<String>emptySet();

        if(!StrUtil.isEmpty(newRoles))   newRoleSet   = StrUtil.convertArrayToSet(newRoles.split(","));
        if(!StrUtil.isEmpty(basedRoles)) basedRoleSet = StrUtil.convertArrayToSet(basedRoles.split(","));

        SysUserVO   sysUserVO      = UserManagementUtil.getUserInfo(userId);
        
        Set<String> existRoleSet = sysUserVO.getRoleSet();
        //User에 바로 붙어있는 Role를 가져온다.(Recusive로 가져오면 안됨)
        List<User2UserResultVO> list = UserManagementUtil.getRoleVOListForUser(userId, 1, 0, "00000");
        for(User2UserResultVO vo: list){
            existRoleSet.add(vo.getNames());
        }
        for(String newRole : newRoleSet){
            boolean idDefined = false;
            for(String basedRole : basedRoleSet){
                if(newRole.equals(basedRole)) {idDefined = true;break;}
            }
            if(idDefined){
                boolean alreadyExists = false;
                for(String existRole : existRoleSet){
                    if(newRole.equals(existRole)) {alreadyExists = true;break;}
                }
                if(!alreadyExists){
                    SysUserVO sysRoleVO = UserManagementUtil.getRoleInfo(newRole);
                    if(!NullUtil.isNull(sysRoleVO)) willBeAddedRoleSet.add(newRole);
                }
            }
        }
        for(String existRole : existRoleSet){
            boolean isExists = false;
            for(String role : newRoleSet){
                if(role.equals(existRole)) isExists = true;break;
            }
            if(!isExists) willBeRemovedRoleSet.add(existRole);
        }
        if(willBeAddedRoleSet != null && willBeAddedRoleSet.size()     > 0) UserManagementUtil.addRoleToUser(userId, willBeAddedRoleSet);
        if(willBeRemovedRoleSet != null && willBeRemovedRoleSet.size() > 0) UserManagementUtil.removeRoleToUser(userId, willBeRemovedRoleSet);
    }
}