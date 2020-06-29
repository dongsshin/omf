/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : UserPropertyServiceImpl.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 3. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.user.model.CommonUserSearchVO;
import com.rap.omc.foundation.user.model.SysUserVO;
import com.rap.omc.foundation.user.model.User2UserResultVO;
import com.rap.omc.foundation.user.model.User2UserSearchVO;
import com.rap.omc.foundation.user.model.UserPropertyVO;
import com.rap.omc.foundation.user.service.UserService;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.dom.OmcSchemaUserCommon;
import com.rap.omc.schema.object.dom.OmcSchemaUserGroup;
import com.rap.omc.schema.object.dom.OmcSchemaUserRole;
import com.rap.omc.schema.object.dom.OmcSchemaUserRoleGroup;
import com.rap.omc.schema.object.dom.OmcSchemaUserUser;
import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaPropertyVO;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : UserServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;
    /**
     * 
     * 
     * @param searchVO
     * @return
     * @see omc.foundation.user.service.UserService#getCommonUserList(omc.foundation.user.model.CommonUserSearchVO)
     */
    public List<OmcSchemaUserVO> getCommonUserList(CommonUserSearchVO searchVO){
        return(OmcSchemaServiceUtils.getCommonUserList(searchVO));
    }
    /**
     * 
     * 
     * @param input
     * @see omc.foundation.user.service.UserService#createUser(omc.foundation.user.model.SysUserVO)
     */
    public void createUser(SysUserVO input){
        String creator = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        OmcSchemaUserVO omcUserVO = new OmcSchemaUserVO();
        omcUserVO.setKindsStr("User");
        omcUserVO.setNames(input.getUserId());
        omcUserVO.setParent("1");
        omcUserVO.setDescriptions(input.getDescriptions());
        omcUserVO.setPassword(input.getPassword());
        omcUserVO.setCreator(creator);
        omcUserVO.setModifier(creator);
        omcUserVO.setSite(input.getSite());
        OmcSchemaUserUser dom  = new OmcSchemaUserUser(omcUserVO);
        dom.createObject(new HashMap<String,Object>());
        setPropertyValueList(input.getUserId(),input.getPropertyList());
        addRoleToUser(input.getUserId(),input.getRoleSet());
    }
    /**
     * 
     * 
     * @param userId
     * @param userName
     * @param site
     * @param departmentCode
     * @param departmentDesc
     * @param departmentDescKor
     * @param mailId
     * @see omc.foundation.user.service.UserService#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void createUser(String userId,String userName, String site, String departmentCode, String departmentDesc, String departmentDescKor, String mailId){
        String creator = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        OmcSchemaUserVO omcUserVO = new OmcSchemaUserVO();
        omcUserVO.setNames(userId);
        omcUserVO.setParent("1");
        omcUserVO.setDescriptions(userName);
        omcUserVO.setPassword(userName);
        omcUserVO.setCreator(creator);
        omcUserVO.setModifier(creator);
        omcUserVO.setSite(site);
        OmcSchemaUserUser dom  = new OmcSchemaUserUser(omcUserVO);
        dom.createObject(new HashMap<String,Object>());
    }
    /**
     * 
     * 
     * @param userId
     * @param userName
     * @param site
     * @param mailId
     * @see omc.foundation.user.service.UserService#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void createUser(String userId,String userName, String site, String mailId){
        createUser(userId,userName,site,"-","-","-",mailId);
    }
    /**
     * 
     * 
     * @param userId
     * @param userName
     * @see omc.foundation.user.service.UserService#createUser(java.lang.String, java.lang.String)
     */
    public void createUser(String userId,String userName){
        createUser(userId,userName,"LG","-","-","-","-");
    }
    /**
     * 
     * 
     * @param role
     * @param roleName
     * @see omc.foundation.user.service.UserService#createRole(java.lang.String, java.lang.String)
     */
    public void createRole(String role,String roleName){
        String creator = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        OmcSchemaUserVO omcUserVO = new OmcSchemaUserVO();
        omcUserVO.setNames(role);
        omcUserVO.setParent("1");
        omcUserVO.setDescriptions(roleName);
        omcUserVO.setPassword("-");
        omcUserVO.setCreator(creator);
        omcUserVO.setModifier(creator);
        omcUserVO.setSite("-");
        OmcSchemaUserRole dom  = new OmcSchemaUserRole(omcUserVO);
        dom.createObject(new HashMap<String,Object>());
    }
    /**
     * 
     * 
     * @param group
     * @param groupName
     * @see omc.foundation.user.service.UserService#createGroup(java.lang.String, java.lang.String)
     */
    public void createGroup(String group,String groupName){
        String creator = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        OmcSchemaUserVO omcUserVO = new OmcSchemaUserVO();
        omcUserVO.setNames(group);
        omcUserVO.setParent("1");
        omcUserVO.setDescriptions(groupName);
        omcUserVO.setPassword("-");
        omcUserVO.setCreator(creator);
        omcUserVO.setModifier(creator);
        omcUserVO.setSite("-");
        OmcSchemaUserGroup dom  = new OmcSchemaUserGroup(omcUserVO);
        dom.createObject(new HashMap<String,Object>());
    }  
    /**
     * 
     * 
     * @param roleGroup
     * @param roleGroupName
     * @see omc.foundation.user.service.UserService#createRoleGroup(java.lang.String, java.lang.String)
     */
    public void createRoleGroup(String roleGroup,String roleGroupName){
        String creator = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        OmcSchemaUserVO omcUserVO = new OmcSchemaUserVO();
        omcUserVO.setNames(roleGroup);
        omcUserVO.setParent("1");
        omcUserVO.setDescriptions(roleGroupName);
        omcUserVO.setPassword("-");
        omcUserVO.setCreator(creator);
        omcUserVO.setModifier(creator);
        omcUserVO.setSite("-");
        OmcSchemaUserRoleGroup dom  = new OmcSchemaUserRoleGroup(omcUserVO);
        dom.createObject(new HashMap<String,Object>());
    }  
    /**
     * 
     * 
     * @param userId
     * @see omc.foundation.user.service.UserService#activiate(java.lang.String)
     */
    //------------------------------ Activiate User------------------------------------
    public void activiate(String userId){
        String modifier = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        schemaUserVO.setModifier(modifier);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.modifyObject(new HashMap<String,Object>());
    }
    /**
     * 
     * 
     * @param userId
     * @see omc.foundation.user.service.UserService#inActiviate(java.lang.String)
     */
    public void inActiviate(String userId){
        String modifier = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        schemaUserVO.setModifier(modifier);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.inActiviateObject(new HashMap<String,Object>());
    }
    /**
     * 
     * 
     * @param userId
     * @return
     * @see omc.foundation.user.service.UserService#getUserInfo(java.lang.String)
     */
    public SysUserVO getUserInfo(String userId){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        if(NullUtil.isNull(schemaUserVO)) return null;
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_User)) throw new FoundationException("{} is not user.",userId);
        SysUserVO susUserVO = new SysUserVO(schemaUserVO);
        susUserVO.setPropertyList(getPropertyList(userId));
        susUserVO.setRoleSet(getRoleList(userId));
        susUserVO.setGroupSet(getGroupList(userId));
        return susUserVO;
    }
    /**
     * 
     * 
     * @param roleId
     * @return
     * @see omc.foundation.user.service.UserService#getRoleInfo(java.lang.String)
     */
    public SysUserVO getRoleInfo(String roleId){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(roleId);
        if(NullUtil.isNull(schemaUserVO)) return null;
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("{} is not role.",roleId);
        SysUserVO susUserVO = new SysUserVO(schemaUserVO);
        return susUserVO;
    }
    /**
     * 
     * 
     * @param groupId
     * @return
     * @see omc.foundation.user.service.UserService#getGroupInfo(java.lang.String)
     */
    public SysUserVO getGroupInfo(String groupId){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(groupId);
        if(NullUtil.isNull(schemaUserVO)) return null;
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("{} is not group.",groupId);
        SysUserVO susUserVO = new SysUserVO(schemaUserVO);
        return susUserVO;
    }
    
    /**
     * 
     * 
     * @param userId
     * @return
     * @see omc.foundation.user.service.UserService#getCommonUserInfo(java.lang.String)
     */
    public SysUserVO getCommonUserInfo(String userId){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        if(NullUtil.isNull(schemaUserVO)) return null;
        SysUserVO susUserVO = new SysUserVO(schemaUserVO);
        susUserVO.setPropertyList(getPropertyList(userId));
        susUserVO.setRoleSet(getRoleList(userId));
        susUserVO.setGroupSet(getGroupList(userId));
        return susUserVO;
    }
    /**
     * 
     *
     * @param groupName
     * @return
     */
    private Set<String> getGroupListForGroup(String groupName){
        Set<String> groupSet = new HashSet<String>();
        List<User2UserResultVO> groupList = getCommonToUserListForCommonUser(groupName,
                                                                             OmcSystemConstants.SYSUSER_KIND_Group,
                                                                             OmcSystemConstants.SYSREL_KIND_GroupGroup,
                                                                             OmcSystemConstants.SYSUSER_KIND_Group,
                                                                             5,
                                                                             1,
                                                                             "00000");
        for(User2UserResultVO vo : groupList){
            groupSet.add(vo.getNames());
        }
        return(groupSet);
    }
    /**
     * 
     *
     * @param groupName
     * @return
     */
    private Set<String> getRoleListForRole(String groupName){
        Set<String> groupSet = new HashSet<String>();
        List<User2UserResultVO> groupList = getCommonToUserListForCommonUser(groupName,
                                                                             OmcSystemConstants.SYSUSER_KIND_Role,
                                                                             OmcSystemConstants.SYSREL_KIND_RoleRole,
                                                                             OmcSystemConstants.SYSUSER_KIND_Role,
                                                                             5,
                                                                             1,
                                                                             "00000");
        for(User2UserResultVO vo : groupList){
            groupSet.add(vo.getNames());
        }
        return(groupSet);
    }
    /**
     * 
     *
     * @param userId
     * @return
     */
    private Set<String> getRoleListAll(String userId){
        Set<String> roleSet = getRoleListForUser(userId);
        Set<String> rtnRoleSet = new HashSet<String>();
        for(String role : roleSet){
            Set<String> subRoleSet = getRoleListForRole(role);
            for(String subRole : subRoleSet){
                rtnRoleSet.add(subRole);
            }
            rtnRoleSet.add(role);
        }
        return rtnRoleSet;
    }
    /**
     * 
     *
     * @param userId
     * @return
     */
    private Set<String> getGroupListAll(String userId){
        Set<String> groupSet = getGroupListForUser(userId);
        Set<String> rtnGroupSet = new HashSet<String>();
        for(String group : groupSet){
            Set<String> subGroupSet = getGroupListForGroup(group);
            for(String subGroup : subGroupSet){
                rtnGroupSet.add(subGroup);
            }
            rtnGroupSet.add(group);
        }
        return rtnGroupSet;
    }
    
    /**
     * 
     * 
     * @param userId
     * @return
     * @see omc.foundation.user.service.UserService#getPropertyList(java.lang.String)
     */
    public Map<String,String> getPropertyList(String userId){
        List<OmcSchemaPropertyVO> voList = getUserPropertyList(userId);
        if(NullUtil.isNone(voList)) return null;
        Map<String,String> map = new HashMap<String,String>();
        for(OmcSchemaPropertyVO vo : voList){
            map.put(vo.getPropertyName(), vo.getPropertyValue());
        }
        return map;
    }
    /**
     * 
     * 
     * @param userId
     * @param propertyName
     * @return
     * @see omc.foundation.user.service.UserService#getPropertyValue(java.lang.String, java.lang.String)
     */
    public String getPropertyValue(String userId, String propertyName){
        OmcSchemaPropertyVO vo = getUserProperty(userId,propertyName);
        if(vo == null) return null;
        return(vo.getPropertyValue());
    }
    /**
     * 
     * 
     * @param userId
     * @param propertyName
     * @param propertyValue
     * @see omc.foundation.user.service.UserService#setPropertyValue(java.lang.String, java.lang.String, java.lang.String)
     */
    public void setPropertyValue(String userId, String propertyName, String propertyValue){
        createModifyDeleteUserProperty(userId,propertyName,propertyValue);
    }
    /**
     * 
     * 
     * @param userId
     * @param propertyList
     * @see omc.foundation.user.service.UserService#setPropertyValueList(java.lang.String, java.util.Map)
     */
    public void setPropertyValueList(String userId, Map<String,String> propertyList){
        createModifyDeleteUserPropertyList(userId,propertyList);
    }
    /********************************************************************************************/
    /*******************************   Role           ******************************************/
    /********************************************************************************************/
    
    /**
     * 
     * 
     * @param userId
     * @param role
     * @see omc.foundation.user.service.UserService#addRoleToUser(java.lang.String, java.lang.String)
     */
    public void addRoleToUser(String userId, String role){
        OmcSchemaUserVO schemaUserVO = getSysUserInfo(userId);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.addRoleToUser(role);
    }
    /**
     * 
     * 
     * @param userId
     * @param roleList
     * @see omc.foundation.user.service.UserService#addRoleToUser(java.lang.String, java.util.Set)
     */
    public void addRoleToUser(String userId, Set<String> roleList){
        OmcSchemaUserVO schemaUserVO = getSysUserInfo(userId);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.addRoleToUser(roleList);
    }
    /**
     * 
     * 
     * @param userId
     * @param role
     * @see omc.foundation.user.service.UserService#removeRoleToUser(java.lang.String, java.lang.String)
     */
    public void removeRoleToUser(String userId, String role){
        OmcSchemaUserVO schemaUserVO = getSysUserInfo(userId);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.removeRoleToUser(role);
    }
    /**
     * 
     * 
     * @param userId
     * @param roleList
     * @see omc.foundation.user.service.UserService#removeRoleToUser(java.lang.String, java.util.Set)
     */
    public void removeRoleToUser(String userId, Set<String> roleList){
        OmcSchemaUserVO schemaUserVO = getSysUserInfo(userId);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.removeRoleToUser(roleList);
    } 
    /**
     * 
     * 
     * @param userId
     * @return
     * @see omc.foundation.user.service.UserService#getRoleList(java.lang.String)
     */
    public Set<String> getRoleList(String userId){
        Set<String> groupSet = getGroupList(userId);
        Set<String> roleSet  = getRoleListAll(userId);
        for(String group : groupSet){
            Set<String> subRoleSet = getRoleListForGroup(group);
            for(String role : subRoleSet){
                roleSet.add(role);
            }
        }
        return roleSet;
    }
    /********************************************************************************************/
    /*******************************   Group           ******************************************/
    /********************************************************************************************/
    
    /**
     * 
     * 
     * @param userId
     * @param group
     * @see omc.foundation.user.service.UserService#addGroupToUser(java.lang.String, java.lang.String)
     */
    public void addGroupToUser(String userId, String group){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.addGroupToUser(group);
    }
    /**
     * 
     * 
     * @param userId
     * @param groupList
     * @see omc.foundation.user.service.UserService#addGroupToUser(java.lang.String, java.util.Set)
     */
    public void addGroupToUser(String userId, Set<String> groupList){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.addGroupToUser(groupList);
    }
    /**
     * 
     * 
     * @param userId
     * @param groupList
     * @see omc.foundation.user.service.UserService#removeGroupToUser(java.lang.String, java.util.Set)
     */
    public void removeGroupToUser(String userId, Set<String> groupList){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.removeGroupToUser(groupList);
    }
    /**
     * 
     * 
     * @param userId
     * @param group
     * @see omc.foundation.user.service.UserService#removeGroupToUser(java.lang.String, java.lang.String)
     */
    public void removeGroupToUser(String userId, String group){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.removeGroupToUser(group);
    }
    /**
     * 
     * 
     * @param userId
     * @return
     * @see omc.foundation.user.service.UserService#getGroupList(java.lang.String)
     */
    public Set<String> getGroupList(String userId){
        return(getGroupListAll(userId));
    }
    /********************************************************************************************/
    /*******************************   Change          ******************************************/
    /********************************************************************************************/
    /**
     * 
     * 
     * @param userId
     * @param password
     * @see omc.foundation.user.service.UserService#changePasserod(java.lang.String, java.lang.String)
     */
    public void changePasserod(String userId, String password){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        schemaUserVO.setPassword(password);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.modifyObject(new HashMap<String,Object>());
    }
    /**
     * 
     * 
     * @param userId
     * @param site
     * @see omc.foundation.user.service.UserService#changeSite(java.lang.String, java.lang.String)
     */
    public void changeSite(String userId, String site){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        schemaUserVO.setSite(site);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.modifyObject(new HashMap<String,Object>());
    }
    /**
     * 
     * 
     * @param userId
     * @param descriptions
     * @see omc.foundation.user.service.UserService#setUserDescription(java.lang.String, java.lang.String)
     */
    public void setUserDescription(String userId, String descriptions){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        schemaUserVO.setDescriptions(descriptions);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.modifyObject(new HashMap<String,Object>());
    }
    /**
     * 
     * 
     * @param userId
     * @param departmentCode
     * @param departmentDesc
     * @param departmentDescKr
     * @see omc.foundation.user.service.UserService#changeDepartment(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void changeDepartment(String userId, String departmentCode,String departmentDesc, String departmentDescKr){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        schemaUserVO.setDepartmentCode(departmentCode);
        schemaUserVO.setDepartmentDesc(departmentDesc);
        schemaUserVO.setDepartmentDescKor(departmentDescKr);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.modifyObject(new HashMap<String,Object>());
    }
    /**
     * 
     * 
     * @param userId
     * @param emailId
     * @see omc.foundation.user.service.UserService#changeMailId(java.lang.String, java.lang.String)
     */
    public void changeMailId(String userId, String emailId){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        schemaUserVO.setEmailId(emailId);
        OmcSchemaUserUser dom =  new OmcSchemaUserUser(schemaUserVO);
        dom.modifyObject(new HashMap<String,Object>());
    }
    /**
     * 
     * 
     * @param searchInfo
     * @return
     * @see omc.foundation.user.service.UserService#getSessionUserLocalTimeForUser(omc.foundation.user.model.UserPropertyVO)
     */
    public Date getSessionUserLocalTimeForUser(UserPropertyVO searchInfo) {
        Date result = schemaDao.select("Schema.getSessionUserLocalTimeForUser", searchInfo);
        return result;
    }
    /********************************************************************************************/
    /*******************************   Private         ******************************************/
    /********************************************************************************************/
    /**
     * 
     *
     * @param userId
     * @param propertyName
     * @return
     */
    private OmcSchemaPropertyVO getUserProperty(String userId, String propertyName){
        UserPropertyVO searchInfo = new UserPropertyVO();
        searchInfo.setPropertyName(propertyName);
        searchInfo.setUserNames(userId);
        OmcSchemaPropertyVO result = schemaDao.select("OmcUser.getUserProperty", searchInfo);
        return result;
    }
    /**
     * 
     *
     * @param userId
     * @return
     */
    private List<OmcSchemaPropertyVO> getUserPropertyList(String userId){
        UserPropertyVO searchInfo = new UserPropertyVO();
        searchInfo.setUserNames(userId);
        List<OmcSchemaPropertyVO> result = schemaDao.selectList("OmcUser.getUserProperty", searchInfo);
        return result;
    }
    /**
     * 
     *
     * @param userId
     * @param fromKinds
     * @param scemaKinds
     * @param toKinds
     * @return
     */
    private List<User2UserResultVO> getListToUserForUser(String userId, long fromKinds, long scemaKinds, long toKinds){
        User2UserSearchVO searchInfo = new User2UserSearchVO();
        searchInfo.setUserNames(userId);
        searchInfo.setSearchUserFromKind(fromKinds);
        searchInfo.setSearchUserRelSchemaKind(scemaKinds);
        searchInfo.setSearchUserToKind(toKinds);
        List<User2UserResultVO> result = schemaDao.selectList("OmcUser.getUserToUser", searchInfo);
        return result;
    }
    /**
     * 
     *
     * @param userId
     * @param fromKinds
     * @param scemaKinds
     * @param toKinds
     * @return
     */
    private List<User2UserResultVO> getListFromUserForUser(String userId, long fromKinds, long scemaKinds, long toKinds){
        User2UserSearchVO searchInfo = new User2UserSearchVO();
        searchInfo.setUserNames(userId);
        searchInfo.setSearchUserFromKind(fromKinds);
        searchInfo.setSearchUserRelSchemaKind(scemaKinds);
        searchInfo.setSearchUserToKind(toKinds);
        List<User2UserResultVO> result = schemaDao.selectList("OmcUser.getUserFromUser", searchInfo);
        return result;
    }
    /**
     * 
     *
     * @param userId
     * @return
     */
    @Override
    public List<User2UserResultVO> getCommonUserAllListForUser(String commonUserId){
        User2UserSearchVO searchInfo = new User2UserSearchVO();
        searchInfo.setUserNames(commonUserId);
        List<User2UserResultVO> result = schemaDao.selectList("OmcUser.getUserRelatedAllUser", searchInfo);
        return result;
    }
    /**
     * 
     *
     * @param userId
     * @param propertyList
     */
    private void createModifyDeleteUserPropertyList(String userId, Map<String,String> propertyList){
        for(String propertyName : propertyList.keySet()){
            createModifyDeleteUserProperty(userId,propertyName,propertyList.get(propertyName));
        }
    }
    /**
     * 
     *
     * @param userId
     * @param propertyName
     * @param propertyValue
     */
    private void createModifyDeleteUserProperty(String userId, String propertyName,  String propertyValue){
        UserPropertyVO input = new UserPropertyVO();
        input.setUserNames(userId);
        input.setPropertyName(propertyName);
        input.setPropertyValue(propertyValue);
        createModifyDeleteUserProperty(input);
    }
    /**
     * 
     *
     * @param input
     */
    private void createModifyDeleteUserProperty(UserPropertyVO input){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(input.getUserNames());
        OmcSchemaServiceUtils.setUserProperty(schemaUserVO, input.getPropertyName(), input.getPropertyValue());
    }
    /**
     * 
     *
     * @param userId
     * @return
     */
    private OmcSchemaUserVO getSysUserInfo(String userId){
        return(getSysUserInfo(userId,true,true));
    }
    /**
     * 
     *
     * @param userId
     * @param isNotFoundCheck
     * @param isInActiveCheck
     * @return
     */
    private OmcSchemaUserVO getSysUserInfo(String userId, boolean isNotFoundCheck, boolean isInActiveCheck){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        if(isNotFoundCheck && NullUtil.isNull(schemaUserVO)) throw new FoundationException("Sysuser Object(" + userId +") Not Found");
        if(isInActiveCheck && !NullUtil.isNull(schemaUserVO) && !Bit.isInclude(schemaUserVO.getFlags(), OmcSystemConstants.SYSUSER_FLAG_Active)) throw new FoundationException("Sysuser Object(" + userId +") is inactive");
        return schemaUserVO;
    }
    /**
     * 
     * 
     * @param group
     * @param userId
     * @see omc.foundation.user.service.UserService#addUserForGroup(java.lang.String, java.lang.String)
     */
    @Override
    public void addUserForGroup(String group, String userId){

        addGroupToUser(userId,group);
    }
    /**
     * 
     * 
     * @param group
     * @param userList
     * @see omc.foundation.user.service.UserService#addUserForGroup(java.lang.String, java.util.Set)
     */
    @Override
    public void addUserForGroup(String group, Set<String> userList){

        for(String userId : userList){
            addGroupToUser(userId,group);
        }
    }
    /**
     * 
     * 
     * @param group
     * @param userId
     * @see omc.foundation.user.service.UserService#removeUserForGroup(java.lang.String, java.lang.String)
     */
    @Override
    public void removeUserForGroup(String group, String userId){

        removeGroupToUser(userId,group);
    }
    /**
     * 
     * 
     * @param group
     * @param userList
     * @see omc.foundation.user.service.UserService#removeUserForGroup(java.lang.String, java.util.Set)
     */
    @Override
    public void removeUserForGroup(String group, Set<String> userList){

        for(String userId : userList){
            removeGroupToUser(userId,group);
        }
    }
    /**
     * 
     * 
     * @param group
     * @return
     * @see omc.foundation.user.service.UserService#getUserListForGroup(java.lang.String)
     */
    @Override
    public Set<String> getUserListForGroup(String group){
        return null;
               
    }
    /**
     * 
     * 
     * @param group
     * @param addedGroup
     * @see omc.foundation.user.service.UserService#addGroupForGroup(java.lang.String, java.lang.String)
     */
    @Override
    public void addGroupForGroup(String group, String addedGroup){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(group);
        if(NullUtil.isNull(schemaUserVO))  throw new FoundationException("Group Object(" + group +") Not Found");
        OmcSchemaUserVO addedSchemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(addedGroup);
        if(NullUtil.isNull(addedSchemaUserVO))  throw new FoundationException("Group Object(" + addedGroup +") Not Found");
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("Object(" + group +") is not Group");
        if(!Bit.isInclude(addedSchemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("Object(" + addedGroup +") is not Group");
        OmcSchemaUserGroup groupDom = new OmcSchemaUserGroup(schemaUserVO);
        groupDom.addGroup(addedGroup);
    }
    /**
     * 
     * 
     * @param group
     * @param addedGroupList
     * @see omc.foundation.user.service.UserService#addGroupForGroup(java.lang.String, java.util.Set)
     */
    @Override
    public void addGroupForGroup(String group, Set<String> addedGroupList){

        for(String addedGroup : addedGroupList){
            addGroupForGroup(group,addedGroup);
        }
    }
    /**
     * 
     * 
     * @param group
     * @param role
     * @see omc.foundation.user.service.UserService#addRoleForGroup(java.lang.String, java.lang.String)
     */
    @Override
    public void addRoleForGroup(String group, String role){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(group);
        if(NullUtil.isNull(schemaUserVO))  throw new FoundationException("Group Object(" + group +") Not Found");
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("Object(" + group +") is not Group");
        
        OmcSchemaUserVO addedRoleVo = OmcSchemaServiceUtils.getUserCommonWithName(role);
        if(NullUtil.isNull(addedRoleVo))  throw new FoundationException("Role Object(" + addedRoleVo +") Not Found");
        if(!Bit.isInclude(addedRoleVo.getKinds(), OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("Object(" + role +") is not Role");
        OmcSchemaUserGroup groupDom = new OmcSchemaUserGroup(schemaUserVO);
        groupDom.addRole(role);
    }
    /**
     * 
     * 
     * @param group
     * @param roleList
     * @see omc.foundation.user.service.UserService#addRoleForGroup(java.lang.String, java.util.Set)
     */
    @Override
    public void addRoleForGroup(String group, Set<String> roleList){

        for(String role : roleList){
            addRoleForGroup(group,role);
        }
    }
    /**
     * 
     * 
     * @param group
     * @param addedGroup
     * @see omc.foundation.user.service.UserService#removeGroupForGroup(java.lang.String, java.lang.String)
     */
    @Override
    public void removeGroupForGroup(String group, String addedGroup){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(group);
        if(NullUtil.isNull(schemaUserVO))  throw new FoundationException("Group Object(" + group +") Not Found");
        OmcSchemaUserVO addedSchemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(addedGroup);
        if(NullUtil.isNull(addedSchemaUserVO))  throw new FoundationException("Group Object(" + addedGroup +") Not Found");
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("Object(" + group +") is not Group");
        if(!Bit.isInclude(addedSchemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("Object(" + addedGroup +") is not Group");
        OmcSchemaUserGroup groupDom = new OmcSchemaUserGroup(schemaUserVO);
        groupDom.removeGroup(addedGroup);
    }
    /**
     * 
     * 
     * @param group
     * @param addedGroupList
     * @see omc.foundation.user.service.UserService#removeGroupForGroup(java.lang.String, java.util.Set)
     */
    @Override
    public void removeGroupForGroup(String group, Set<String> addedGroupList){

        for(String addedGroup : addedGroupList){
            removeGroupForGroup(group,addedGroup);
        }
    }
    /**
     * 
     * 
     * @param group
     * @param role
     * @see omc.foundation.user.service.UserService#removeRoleForGroup(java.lang.String, java.lang.String)
     */
    @Override
    public void removeRoleForGroup(String group, String role){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(group);
        if(NullUtil.isNull(schemaUserVO))  throw new FoundationException("Group Object(" + group +") Not Found");
        OmcSchemaUserVO addedRoleVo = OmcSchemaServiceUtils.getUserCommonWithName(role);
        if(NullUtil.isNull(addedRoleVo))  throw new FoundationException("Role Object(" + addedRoleVo +") Not Found");
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("Object(" + group +") is not Group");
        if(!Bit.isInclude(addedRoleVo.getKinds(), OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("Object(" + role +") is not Group");
        OmcSchemaUserGroup groupDom = new OmcSchemaUserGroup(schemaUserVO);
        groupDom.removeRole(role);
    }
    /**
     * 
     * 
     * @param group
     * @param roleList
     * @see omc.foundation.user.service.UserService#removeRoleForGroup(java.lang.String, java.util.Set)
     */
    @Override
    public void removeRoleForGroup(String group, Set<String> roleList){

        for(String role : roleList){
            removeRoleForGroup(group,role);
        }
    }
    /**
     * 
     * 
     * @param role
     * @param user
     * @see omc.foundation.user.service.UserService#addUserForRole(java.lang.String, java.lang.String)
     */
    @Override
    public void addUserForRole(String role, String user){

        addRoleToUser(user,role);
    }
    /**
     * 
     * 
     * @param role
     * @param userList
     * @see omc.foundation.user.service.UserService#addUserForRole(java.lang.String, java.util.Set)
     */
    @Override
    public void addUserForRole(String role, Set<String> userList){

        for(String user : userList){
            addRoleToUser(user,role);
        }
    }
    /**
     * 
     * 
     * @param role
     * @param user
     * @see omc.foundation.user.service.UserService#removeUserForRole(java.lang.String, java.lang.String)
     */
    @Override
    public void removeUserForRole(String role, String user){

        removeRoleToUser(user,role);
    }
    /**
     * 
     * 
     * @param role
     * @param userList
     * @see omc.foundation.user.service.UserService#removeUserForRole(java.lang.String, java.util.Set)
     */
    @Override
    public void removeUserForRole(String role, Set<String> userList){

        for(String user : userList){
            removeRoleToUser(user,role);
        }
    }
    /**
     * 
     * 
     * @param role
     * @return
     * @see omc.foundation.user.service.UserService#getUserListForRole(java.lang.String)
     */
    @Override
    public Set<String> getUserListForRole(String role){

        return null;
    }
    /**
     * 
     * 
     * @param role
     * @param addedRole
     * @see omc.foundation.user.service.UserService#addRoleForRole(java.lang.String, java.lang.String)
     */
    @Override
    public void addRoleForRole(String role, String addedRole){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(role);
        if(NullUtil.isNull(schemaUserVO))  throw new FoundationException("Role Object(" + role +") Not Found");
        OmcSchemaUserVO addedRoleVo = OmcSchemaServiceUtils.getUserCommonWithName(addedRole);
        if(NullUtil.isNull(addedRoleVo))  throw new FoundationException("Role Object(" + addedRole +") Not Found");
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("Object(" + role +") is not Role");
        if(!Bit.isInclude(addedRoleVo.getKinds(), OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("Object(" + addedRole +") is not Role");
        OmcSchemaUserRole dom = new OmcSchemaUserRole(schemaUserVO);
        dom.addRole(addedRole);
    }
    /**
     * 
     * 
     * @param role
     * @param addedRoleList
     * @see omc.foundation.user.service.UserService#addRoleForRole(java.lang.String, java.util.Set)
     */
    @Override
    public void addRoleForRole(String role, Set<String> addedRoleList){

        for(String addedRole : addedRoleList){
            addRoleForRole(role,addedRole);
        }
    }
    /**
     * 
     * 
     * @param role
     * @param group
     * @see omc.foundation.user.service.UserService#addGroupForRole(java.lang.String, java.lang.String)
     */
    @Override
    public void addGroupForRole(String role, String group){

        addRoleForGroup(group, role);
    }
    /**
     * 
     * 
     * @param role
     * @param groupList
     * @see omc.foundation.user.service.UserService#addGroupForRole(java.lang.String, java.util.Set)
     */
    @Override
    public void addGroupForRole(String role, Set<String> groupList){

        for(String group : groupList){
            addRoleForGroup(group, role);
        }
    }
    /**
     * 
     * 
     * @param role
     * @param removeRole
     * @see omc.foundation.user.service.UserService#removeRoleForRole(java.lang.String, java.lang.String)
     */
    @Override
    public void removeRoleForRole(String role, String removeRole){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(role);
        if(NullUtil.isNull(schemaUserVO))  throw new FoundationException("Role Object(" + role +") Not Found");
        OmcSchemaUserVO addedRoleVo = OmcSchemaServiceUtils.getUserCommonWithName(removeRole);
        if(NullUtil.isNull(addedRoleVo))  throw new FoundationException("Role Object(" + removeRole +") Not Found");
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("Object(" + role +") is not Role");
        if(!Bit.isInclude(addedRoleVo.getKinds(), OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("Object(" + removeRole +") is not Role");
        OmcSchemaUserRole dom = new OmcSchemaUserRole(schemaUserVO);
        dom.removeRole(removeRole);
    }
    /**
     * 
     * 
     * @param role
     * @param removedRoleList
     * @see omc.foundation.user.service.UserService#removeRoleForRole(java.lang.String, java.util.Set)
     */
    @Override
    public void removeRoleForRole(String role, Set<String> removedRoleList){

        for(String removeRole : removedRoleList){
            removeRoleForRole(role,removeRole);
        }
    }
    /**
     * 
     * 
     * @param role
     * @param group
     * @see omc.foundation.user.service.UserService#removeGroupForRole(java.lang.String, java.lang.String)
     */
    @Override
    public void removeGroupForRole(String role, String group){

        removeRoleForGroup(group, role);
    }
    @Override
    public void removeGroupForRole(String role, Set<String> groupList){

        for(String group : groupList){
            removeGroupForRole(role,group);
        }
    }
    private Set<String> getGroupListForUser(String userId){
        Set<String> strSet = new HashSet<String>();
        List<User2UserResultVO> relList = getGroupVOListForUser(userId,1,1,"00000");
        for(User2UserResultVO vo : relList){
            strSet.add(vo.getNames());
        }
        return(strSet);
    }
    @Override
    public List<User2UserResultVO> getGroupVOListForUser(String userId,int wantedLevel, int currentLevel,String uniqueStr){
        OmcSchemaUserVO schemaUserVO = getCommonUser(userId);
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_User)) throw new FoundationException("[Foundation]'" + userId + "' is not User.");
        return getCommonFromUserListForCommonUser(userId,
                                                  OmcSystemConstants.SYSUSER_KIND_Group,
                                                  OmcSystemConstants.SYSREL_KIND_GroupUser,
                                                  OmcSystemConstants.SYSUSER_KIND_User,
                                                  1,
                                                  1,
                                                  uniqueStr);
    }
    private Set<String> getRoleListForUser(String userId){
        Set<String> strSet = new HashSet<String>();
        List<User2UserResultVO> relList = getRoleVOListForUser(userId,1,1,"00000");
        for(User2UserResultVO vo : relList){
            strSet.add(vo.getNames());
        }
        return(strSet);
    }
    @Override
    public List<User2UserResultVO> getRoleVOListForUser(String userId,int wantedLevel, int currentLevel,String uniqueStr){
        OmcSchemaUserVO schemaUserVO = getCommonUser(userId);
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_User)) throw new FoundationException("[Foundation]'" + userId + "' is not User.");
        return getCommonFromUserListForCommonUser(userId,
                                                  OmcSystemConstants.SYSUSER_KIND_Role,
                                                  OmcSystemConstants.SYSREL_KIND_RoleUser,
                                                  OmcSystemConstants.SYSUSER_KIND_User,
                                                  wantedLevel,
                                                  currentLevel,
                                                  uniqueStr);
    }
    
    @Override
    public List<User2UserResultVO> getUserVOListForRole(String userId,int wantedLevel, int currentLevel,String uniqueStr){
        OmcSchemaUserVO schemaUserVO = getCommonUser(userId);
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_User)) throw new FoundationException("[Foundation]'" + userId + "' is not User.");
        return getCommonToUserListForCommonUser(userId,
                                                OmcSystemConstants.SYSUSER_KIND_Role,
                                                OmcSystemConstants.SYSREL_KIND_RoleUser,
                                                OmcSystemConstants.SYSUSER_KIND_User,
                                                wantedLevel,
                                                currentLevel,
                                                uniqueStr);
    }
    @Override
    public List<User2UserResultVO> getUserVOListForGroup(String groupId,int wantedLevel, int currentLevel,String uniqueStr){
        OmcSchemaUserVO schemaUserVO = getCommonUser(groupId);
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_RoleGroup)) return (new ArrayList<User2UserResultVO>());
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("[Foundation]'" + groupId + "' is not Group.");
        return getCommonToUserListForCommonUser(groupId,
                                                OmcSystemConstants.SYSUSER_KIND_Group,
                                                OmcSystemConstants.SYSREL_KIND_GroupUser,
                                                OmcSystemConstants.SYSUSER_KIND_User,
                                                wantedLevel,
                                                currentLevel,
                                                uniqueStr);
    }
    
    private Set<String> getRoleListForGroup(String groupId){
        Set<String> roleSet = new HashSet<String>();
        List<User2UserResultVO> roleList = getRoleVOListForGroup(groupId,1,1,"00000");
        for(User2UserResultVO vo : roleList){
            roleSet.add(vo.getNames());
        }
        return(roleSet);
    }
    @Override
    public List<User2UserResultVO> getRoleVOListForGroup(String groupId,int wantedLevel, int currentLevel,String uniqueStr){
        OmcSchemaUserVO schemaUserVO = getCommonUser(groupId);
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_RoleGroup)) return (new ArrayList<User2UserResultVO>());
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("[Foundation]'" + groupId + "' is not Group.");
        return this.getCommonToUserListForCommonUser(groupId,
                                                     OmcSystemConstants.SYSUSER_KIND_Group,
                                                     OmcSystemConstants.SYSREL_KIND_GroupRole,
                                                     OmcSystemConstants.SYSUSER_KIND_Role,
                                                     wantedLevel,
                                                     currentLevel,
                                                     uniqueStr);
    }
    @Override
    public List<User2UserResultVO> getRoleVOListForRole(String roleId, int wantedLevel, int currentLevel,String uniqueStr){
        OmcSchemaUserVO schemaUserVO = getCommonUser(roleId);
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_RoleGroup)) return (new ArrayList<User2UserResultVO>());
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("[Foundation]'" + roleId + "' is not Role.");
        return this.getCommonToUserListForCommonUser(roleId,
                                                     OmcSystemConstants.SYSUSER_KIND_Role,
                                                     OmcSystemConstants.SYSREL_KIND_RoleRole,
                                                     OmcSystemConstants.SYSUSER_KIND_Role,
                                                     wantedLevel,
                                                     currentLevel,
                                                     uniqueStr);
    }
    @Override
    public List<User2UserResultVO> getGroupVOListForGroup(String groupId, int wantedLevel, int currentLevel,String uniqueStr){
        OmcSchemaUserVO schemaUserVO = getCommonUser(groupId);
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_RoleGroup)) return (new ArrayList<User2UserResultVO>());
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("[Foundation]'" + groupId + "' is not Group.");
        return this.getCommonToUserListForCommonUser(groupId,
                                                     OmcSystemConstants.SYSUSER_KIND_Group,
                                                     OmcSystemConstants.SYSREL_KIND_GroupGroup,
                                                     OmcSystemConstants.SYSUSER_KIND_Group,
                                                     wantedLevel,
                                                     currentLevel,
                                                     uniqueStr);
    }
    @Override
    public List<User2UserResultVO> getGroupVOListForRole(String roleId, int wantedLevel, int currentLevel,String uniqueStr){
        
        OmcSchemaUserVO schemaUserVO = getCommonUser(roleId);
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_RoleGroup)) return (new ArrayList<User2UserResultVO>());
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("[Foundation]'" + roleId + "' is not Role.");
        
        return this.getCommonFromUserListForCommonUser(roleId,
                                                      OmcSystemConstants.SYSUSER_KIND_Group,
                                                      OmcSystemConstants.SYSREL_KIND_GroupRole,
                                                      OmcSystemConstants.SYSUSER_KIND_Role,
                                                      wantedLevel,
                                                      currentLevel,
                                                      uniqueStr);
    }
    @Override
    public List<User2UserResultVO> getCommonUserListForCommon(String commonId){
        // TODO Auto-generated method stub
        OmcSchemaUserVO schemaUserVO = getCommonUser(commonId);
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_RoleGroup)) return (new ArrayList<User2UserResultVO>());
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_User)) return getCommonUserListForUser(commonId);
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Role)) return getCommonUserListForRole(commonId);
        if(Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Group)) return getCommonUserListForGroup(commonId);
        return (new ArrayList<User2UserResultVO>());
    }
    @Override
    public List<User2UserResultVO> getCommonUserListForUser(String userId){
        List<User2UserResultVO> result = new ArrayList<User2UserResultVO>();
        
        List<User2UserResultVO> directList1 = this.getGroupVOListForUser(userId, 1, 1, "A");
        for(User2UserResultVO groupVO : directList1){
            result.add(groupVO);
            List<User2UserResultVO> tempList = this.getGroupVOListForGroup(groupVO.getNames(), 5, 2, groupVO.getUniqueStr());
            result.addAll(tempList);
        }
        List<User2UserResultVO> directList2 = this.getRoleVOListForUser(userId, 1, 0, "B");
        for(User2UserResultVO roleVO : directList2){
            List<User2UserResultVO> tempList = this.getRoleVOListForRole(roleVO.getNames(), 5, 2, roleVO.getUniqueStr());
            result.addAll(tempList);
        }
        return result;
    }
    @Override
    public List<User2UserResultVO> getCommonUserListForRole(String roleId){
        List<User2UserResultVO> result = new ArrayList<User2UserResultVO>();
        List<User2UserResultVO> directList1 = this.getRoleVOListForRole(roleId, 5, 0, "A");
        result.addAll(directList1);
        List<User2UserResultVO> directList2 = this.getGroupVOListForRole(roleId, 1, 0, "B");
        for(User2UserResultVO vo : directList2){
            List<User2UserResultVO> tempGroupList = this.getGroupVOListForGroup(vo.getNames(), 5, 0, vo.getUniqueStr());
            result.addAll(tempGroupList);
        }
        return result;
    }
    @Override
    public List<User2UserResultVO> getCommonUserListForGroup(String groupId){
        List<User2UserResultVO> result = new ArrayList<User2UserResultVO>();
        List<User2UserResultVO> directList1 = this.getGroupVOListForGroup(groupId, 5, 0, "A");
        result.addAll(directList1);
        List<User2UserResultVO> directList2 = this.getRoleVOListForGroup(groupId, 1, 0, "B");
        for(User2UserResultVO vo : directList2){
            List<User2UserResultVO> tempGroupList = this.getRoleVOListForRole(vo.getNames(), 5, 0, vo.getUniqueStr());
            result.addAll(tempGroupList);
        }
        return result;
    }
    private void getCommonFromUserListForCommonUserSub(String groupName,List<User2UserResultVO> groupList, long fromKinds, long scemaKinds, long toKinds,int wantedLevel, int currentLevel,String uniqueStrParent){
        if(currentLevel > wantedLevel) return;
        List<User2UserResultVO> subGroupList = getListFromUserForUser(groupName, fromKinds, scemaKinds, toKinds);
        int seq = 1;String uniqueStr = "";
        for(User2UserResultVO subGroup : subGroupList){
            uniqueStr =  uniqueStrParent + StrUtil.LPAD(seq, 5, "0");
            subGroup.setUniqueStr(uniqueStr);
            subGroup.setUniqueStrParent(uniqueStrParent);
            subGroup.setLevel(currentLevel);
            getCommonFromUserListForCommonUserSub(subGroup.getNames(),groupList,fromKinds, scemaKinds, toKinds,wantedLevel,currentLevel+1,uniqueStr);
            groupList.add(subGroup);
            seq++;
        }
    }
    private void getCommonToUserListForCommonUserSub(String groupName,List<User2UserResultVO> groupList, long fromKinds, long scemaKinds, long toKinds, int wantedLevel, int currentLevel, String uniqueStrParent){
        if(currentLevel > wantedLevel) return;
        List<User2UserResultVO> subGroupList = getListToUserForUser(groupName, fromKinds, scemaKinds, toKinds);
        int seq = 1;String uniqueStr = "";
        for(User2UserResultVO subGroup : subGroupList){
            uniqueStr =  uniqueStrParent + StrUtil.LPAD(seq, 5, "0");
            subGroup.setUniqueStr(uniqueStr);
            subGroup.setUniqueStrParent(uniqueStrParent);
            subGroup.setLevel(currentLevel);
            getCommonToUserListForCommonUserSub(subGroup.getNames(),groupList,fromKinds, scemaKinds, toKinds,wantedLevel,currentLevel+1,uniqueStr);
            groupList.add(subGroup);
            seq++;
        }
    }
    @Override
    public List<User2UserResultVO> getCommonToUserListForCommonUser(String commonUserName,long fromKinds, long scemaKinds, long toKinds, int wantedLevel){
            return this.getCommonToUserListForCommonUser(commonUserName,fromKinds, scemaKinds, toKinds,wantedLevel,1,"00000");
    }
    @Override
    public List<User2UserResultVO> getCommonToUserListForCommonUser(String commonUserName,long fromKinds, long scemaKinds, long toKinds, int wantedLevel, int currentLevel, String uniqueStr){
            List<User2UserResultVO> userList = new ArrayList<User2UserResultVO>();
            this.getCommonToUserListForCommonUserSub(commonUserName,userList,fromKinds, scemaKinds, toKinds,wantedLevel,currentLevel,uniqueStr);
            return userList;
    }
    @Override
    public List<User2UserResultVO> getCommonFromUserListForCommonUser( String commonUserName,long fromKinds, long scemaKinds, long toKinds,int wantedLevel){
            return this.getCommonFromUserListForCommonUser(commonUserName,fromKinds, scemaKinds, toKinds,wantedLevel,1,"00000");
    }
    @Override
    public List<User2UserResultVO> getCommonFromUserListForCommonUser(String commonUserName,long fromKinds,long scemaKinds,long toKinds,int wantedLevel,int currentLevel,String uniqueStr){
        List<User2UserResultVO> userList = new ArrayList<User2UserResultVO>();
        this.getCommonFromUserListForCommonUserSub(commonUserName,userList,fromKinds, scemaKinds, toKinds,wantedLevel,currentLevel,uniqueStr);
        return userList;
    }
    @Override
    public void addMenuForRole(String role, String menu, Map<String, String> map){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(role);
        if(NullUtil.isNull(schemaUserVO)) throw new FoundationException("[Foundation]Role(" + role + ") Not Found");
        
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("[Foundation]" + role + " is not Role");
        OmcSchemaUserRole roleDom = new OmcSchemaUserRole(schemaUserVO);
        roleDom.addMenu(menu, OmcSystemConstants.SYSREL_KIND_RoleMenu,map);
    }
    @Override
    public void addMenuForRole(String role, List<String> menuList, Map<String, String> map){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(role);
        if(NullUtil.isNull(schemaUserVO)) throw new FoundationException("[Foundation]Role(" + role + ") Not Found");
        
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("[Foundation]" + role + " is not Role");
        OmcSchemaUserRole roleDom = new OmcSchemaUserRole(schemaUserVO);
        for(String menu : menuList){
            roleDom.addMenu(menu, OmcSystemConstants.SYSREL_KIND_RoleMenu,map);
        }
    }
    @Override
    public void addMenuForGroup(String group, String menu, Map<String, String> map){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(group);
        if(NullUtil.isNull(schemaUserVO)) throw new FoundationException("[Foundation]Group(" + group + ") Not Found");
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("[Foundation]" + group + " is not Group");
        OmcSchemaUserGroup groupDom = new OmcSchemaUserGroup(schemaUserVO);
        groupDom.addMenu(menu, OmcSystemConstants.SYSREL_KIND_GroupMenu,map);
    }
    @Override
    public void addMenuForGroup(String group, List<String> menuList, Map<String, String> map){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(group);
        if(NullUtil.isNull(schemaUserVO)) throw new FoundationException("[Foundation]Group(" + group + ") Not Found");
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("[Foundation]" + group + " is not Group");
        OmcSchemaUserGroup groupDom = new OmcSchemaUserGroup(schemaUserVO);
        for(String menu : menuList){
            groupDom.addMenu(menu, OmcSystemConstants.SYSREL_KIND_GroupMenu,map);
        }
    }
    @Override
    public void addMenuForUser(String user, String menu, Map<String, String> map){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(user);
        if(NullUtil.isNull(schemaUserVO)) throw new FoundationException("[Foundation]User(" + user + ") Not Found");
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_User)) throw new FoundationException("[Foundation]" + user + " is not User");
        OmcSchemaUserUser userDom = new OmcSchemaUserUser(schemaUserVO);
        userDom.addMenu(menu, OmcSystemConstants.SYSREL_KIND_UserMenu,map);
    }
    @Override
    public void addMenuForUser(String user, List<String> menuList, Map<String, String> map){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(user);
        if(NullUtil.isNull(schemaUserVO)) throw new FoundationException("[Foundation]User(" + user + ") Not Found");
        if(!Bit.isInclude(schemaUserVO.getKinds(), OmcSystemConstants.SYSUSER_KIND_User)) throw new FoundationException("[Foundation]" + user + " is not User");
        OmcSchemaUserUser userDom = new OmcSchemaUserUser(schemaUserVO);
        for(String menu : menuList){
            userDom.addMenu(menu, OmcSystemConstants.SYSREL_KIND_UserMenu,map);
        }
    }
    @Override
    public void addMenuForRole(List<String> roleList, String menu, Map<String,String> map){
        for(String role : roleList){
            addMenuForRole(role, menu, map);
        }
    }
    @Override
    public void addMenuForRole(List<String> roleList, List<String> menuList, Map<String,String> map){

        for(String role : roleList){
            addMenuForRole(role, menuList, map);
        }
    }
    @Override
    public void addMenuForGroup(List<String> groupList, String menu, Map<String,String> map){

        for(String group : groupList){
            addMenuForGroup(group, menu, map);
        }
    }
    @Override
    public void addMenuForGroup(List<String> groupList, List<String> menuList, Map<String,String> map){

        for(String group : groupList){
            addMenuForGroup(group, menuList, map);
        }
    }
    @Override
    public void addMenuForUser(List<String> userIdList, String menu, Map<String,String> map){

        for(String userId : userIdList){
            addMenuForUser(userId, menu, map);
        }
    }
    @Override
    public void addMenuForUser(List<String> userIdList, List<String> menuList, Map<String,String> map){
        for(String userId : userIdList){
            addMenuForUser(userId, menuList, map);
        }
    }
    @Override
    public void removeMenuForRole(String role, String menu){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(role);
        OmcSchemaUserRole roleDom = new OmcSchemaUserRole(schemaUserVO);
        roleDom.removeMenu(menu, OmcSystemConstants.SYSREL_KIND_RoleMenu);
    }
    @Override
    public void removeMenuForRole(String role, List<String> menuList){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(role);
        OmcSchemaUserRole roleDom = new OmcSchemaUserRole(schemaUserVO);
        for(String menu : menuList){
            roleDom.removeMenu(menu, OmcSystemConstants.SYSREL_KIND_RoleMenu);
        }
    }
    @Override
    public void removeMenuForGroup(String group, String menu){
        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(group);
        OmcSchemaUserGroup roleDom = new OmcSchemaUserGroup(schemaUserVO);
        roleDom.removeMenu(menu, OmcSystemConstants.SYSREL_KIND_GroupMenu);
    }
    @Override
    public void removeMenuForGroup(String group, List<String> menuList){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(group);
        OmcSchemaUserGroup roleDom = new OmcSchemaUserGroup(schemaUserVO);
        for(String menu : menuList){
            roleDom.removeMenu(menu, OmcSystemConstants.SYSREL_KIND_GroupMenu);
        }
    }
    @Override
    public void removeMenuForUser(String userId, String menu){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        OmcSchemaUserUser roleDom = new OmcSchemaUserUser(schemaUserVO);
        roleDom.removeMenu(menu, OmcSystemConstants.SYSREL_KIND_UserMenu);
    }
    @Override
    public void removeMenuForUser(String userId, List<String> menuList){

        OmcSchemaUserVO schemaUserVO = OmcSchemaServiceUtils.getUserCommonWithName(userId);
        OmcSchemaUserUser roleDom = new OmcSchemaUserUser(schemaUserVO);
        for(String menu : menuList){
            roleDom.removeMenu(menu, OmcSystemConstants.SYSREL_KIND_UserMenu);
        }
    }
    @Override
    public void removeMenuForRole(List<String> roleList, String menu){

        for(String role : roleList){
            removeMenuForRole(role,menu);
        }
    }
    @Override
    public void removeMenuForRole(List<String> roleList, List<String> menuList){

        for(String menu : menuList){
            removeMenuForRole(roleList,menu);
        }
    }
    @Override
    public void removeMenuForGroup(List<String> groupList, String menu){

        for(String group : groupList){
            removeMenuForGroup(group,menu);
        }
    }
    @Override
    public void removeMenuForGroup(List<String> groupList, List<String> menuList){

        for(String menu : menuList){
            removeMenuForGroup(groupList,menu);
        }
    }
    @Override
    public void removeMenuForUser(List<String> userIdList, String menu){

        for(String userId : userIdList){
            removeMenuForUser(userId,menu);
        }
    }
    @Override
    public void removeMenuForUser(List<String> userIdList, List<String> menuList){

        for(String menu : menuList){
            removeMenuForUser(userIdList,menu);
        }
    }
    @Override
    public List<OmcSchemaMenuVO> getMenuListForRole(String role){

        return(getMenuListForRole(role,false));
    }
    @Override
    public List<OmcSchemaMenuVO> getMenuListForRole(String role, boolean expand){
        OmcSchemaUserVO schemaUserVO = getCommonUser(role);
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Role)) throw new FoundationException("[Foundation]'" + role + "' is not Role.");
        OmcSchemaUserGroup roleDom = new OmcSchemaUserGroup(schemaUserVO);
        List<OmcSchemaMenuVO> rsult = roleDom.getMenuList();
        return rsult;
    }
    @Override
    public List<OmcSchemaMenuVO> getMenuListForGroup(String group){

        return(getMenuListForGroup(group,false));
    }
    @Override
    public List<OmcSchemaMenuVO> getMenuListForGroup(String group, boolean expand){
        OmcSchemaUserVO schemaUserVO = getCommonUser(group);
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_Group)) throw new FoundationException("[Foundation]'" + group + "' is not Group.");
        OmcSchemaUserGroup roleDom = new OmcSchemaUserGroup(schemaUserVO);
        List<OmcSchemaMenuVO> rsult = roleDom.getMenuList();
        return rsult;
    }
    @Override
    public List<OmcSchemaMenuVO> getMenuListForUser(String userId){

        return(getMenuListForUser(userId,false));
    }
    @Override
    public List<OmcSchemaMenuVO> getMenuListForUser(String userId, boolean expand){
        OmcSchemaUserVO schemaUserVO = getCommonUser(userId);
        if(!Bit.isInclude(schemaUserVO.getKinds(),OmcSystemConstants.SYSUSER_KIND_User)) throw new FoundationException("[Foundation]'" + userId + "' is not User.");
        OmcSchemaUserGroup roleDom = new OmcSchemaUserGroup(schemaUserVO);
        List<OmcSchemaMenuVO> rsult = roleDom.getMenuList();
        return rsult;
    }
    public List<OmcSchemaMenuVO> getMenuListForCommonUserWithNames(List<String> nameList,boolean expand){
        return OmcSchemaUserCommon.getMenuListForSetWithNames(nameList,expand);
    }
    public Set<String> getMenuSetForCommonUserWithNames(List<String> nameList,boolean expand){
        List<String> result = OmcSchemaUserCommon.getMenuSetForSetWithNames(nameList);
        return StrUtil.convertListToSet(result);
    }
    private OmcSchemaUserVO getCommonUser(String userId){
        return(OmcSchemaServiceUtils.getUserCommonWithName(userId));
    }
	@Override
	public void setTimeStampSystemUser(String userId, String timeStamp) {
		OmcSchemaUserVO parmVO = new OmcSchemaUserVO();
		parmVO.setNames(userId);
		parmVO.setTimeStamp(timeStamp);
	    OmcSchemaServiceUtils.setTimeStampSystemUser(parmVO);
	}
}