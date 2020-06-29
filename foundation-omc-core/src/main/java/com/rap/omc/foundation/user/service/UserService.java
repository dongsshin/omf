/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : UserPropertyService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 9.  hyeyoung.park   Initial
 * ===========================================
 */
package com.rap.omc.foundation.user.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.foundation.user.model.CommonUserSearchVO;
import com.rap.omc.foundation.user.model.SysUserVO;
import com.rap.omc.foundation.user.model.User2UserResultVO;
import com.rap.omc.foundation.user.model.UserPropertyVO;
import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;

/**
 * <pre>
 * Class : UserService
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public interface UserService {
    public List<OmcSchemaUserVO> getCommonUserList(CommonUserSearchVO searchVO);
    public void createUser(SysUserVO input);
    public void createUser(String userId,String userName, String site, String departmentCode, String departmentDesc, String departmentDescKor, String mailId);
    public void createUser(String userId,String userName, String site, String mailId);
    public void createUser(String userId,String userName);
    public void createRole(String role,String roleName);
    public void createGroup(String group,String groupName);
    public void createRoleGroup(String roleGroup,String roleGroupName);
    public void setTimeStampSystemUser(String userId, String timeStamp);
    
    public void activiate(String userId);
    public void inActiviate(String userId);
    public SysUserVO getUserInfo(String userId);
    public SysUserVO getCommonUserInfo(String userId);
    public SysUserVO getRoleInfo(String roleId);
    public SysUserVO getGroupInfo(String groupId);
    
    public Map<String,String> getPropertyList(String userId);
    public String getPropertyValue(String userId, String propertyName);
    public void setPropertyValue(String userId, String propertyName, String propertyValue);
    public void setPropertyValueList(String userId, Map<String,String> propertyList);
    
    public void addRoleToUser(String userId, String role);
    public void addRoleToUser(String userId, Set<String> roleList);
    public void removeRoleToUser(String userId, String role);
    public void removeRoleToUser(String userId, Set<String> roleList);
    
    public void addGroupToUser(String userId, String group);
    public void addGroupToUser(String userId, Set<String> groupList);
    public void removeGroupToUser(String userId, Set<String> groupList);
    public void removeGroupToUser(String userId, String group);
    
    public Set<String> getRoleList(String userId);
    public Set<String> getGroupList(String userId);
    public Set<String> getUserListForGroup(String group);
    public Set<String> getUserListForRole(String role);
    
    public List<User2UserResultVO> getUserVOListForRole(String groupId,int wantedLevel, int currentLevel,String uniqueStr);
    public List<User2UserResultVO> getUserVOListForGroup(String groupId,int wantedLevel, int currentLevel,String uniqueStr);
    
    public List<User2UserResultVO> getRoleVOListForUser(String userId,int wantedLevel, int currentLevel,String uniqueStr);
    public List<User2UserResultVO> getRoleVOListForRole(String roleId,int wantedLevel, int currentLevel,String uniqueStr);
    public List<User2UserResultVO> getRoleVOListForGroup(String groupId,int wantedLevel, int currentLevel,String uniqueStr);
    
    public List<User2UserResultVO> getGroupVOListForUser(String userId,int wantedLevel, int currentLevel,String uniqueStr);
    public List<User2UserResultVO> getGroupVOListForGroup(String  groupId,int wantedLevel, int currentLevel,String uniqueStr);
    public List<User2UserResultVO> getGroupVOListForRole(String  roleId,int wantedLevel, int currentLevel,String uniqueStr);
    
    public List<User2UserResultVO> getCommonUserListForUser(String  userId);
    public List<User2UserResultVO> getCommonUserListForRole(String  roleId);
    public List<User2UserResultVO> getCommonUserListForGroup(String  groupId);
    public List<User2UserResultVO> getCommonUserListForCommon(String  commonId);
    
    //public List<User2UserResultVO> getListToUserForUser(String userId, long fromKinds, long scemaKinds, long toKinds);
    //public List<User2UserResultVO> getListFromUserForUser(String userId, long fromKinds, long scemaKinds, long toKinds);
    //public List<User2UserResultVO> getListFromToUserForUser(String userId);
    public List<User2UserResultVO> getCommonUserAllListForUser(String commonUserId);
    
    public List<User2UserResultVO> getCommonToUserListForCommonUser(String commonUserName,long fromKinds,long scemaKinds,long toKinds, int wantedLevel);
    public List<User2UserResultVO> getCommonToUserListForCommonUser(String commonUserName,long fromKinds,long scemaKinds,long toKinds,int wantedLevel,int currentLevel,String uniqueStr);
    public List<User2UserResultVO> getCommonFromUserListForCommonUser(String commonUserName,long fromKinds,long scemaKinds,long toKinds,int wantedLevel);
    public List<User2UserResultVO> getCommonFromUserListForCommonUser(String commonUserName,long fromKinds,long scemaKinds,long toKinds,int wantedLevel,int currentLevel,String uniqueStr);
    
    public void addUserForGroup(String group, String userId);
    public void addUserForGroup(String group, Set<String> userList);
    public void removeUserForGroup(String group, String userId);
    public void removeUserForGroup(String group, Set<String> userList);
    
    public void addGroupForGroup(String group, String addedGroup);
    public void addGroupForGroup(String group, Set<String> addedGroupList);
    public void addRoleForGroup(String group, String role);
    public void addRoleForGroup(String group, Set<String> roleList);
    public void removeGroupForGroup(String group, String addedGroup);
    public void removeGroupForGroup(String group, Set<String> addedGroupList);
    public void removeRoleForGroup(String group, String role);
    public void removeRoleForGroup(String group, Set<String> roleList);
    
    public void addUserForRole(String role, String user);
    public void addUserForRole(String role, Set<String> userList);
    public void removeUserForRole(String role, String user);
    public void removeUserForRole(String role, Set<String> userList);
    
    public void addRoleForRole(String role, String addedRole);
    public void addRoleForRole(String role, Set<String> addedRoleList);
    public void addGroupForRole(String role, String group);
    public void addGroupForRole(String role, Set<String> groupList);
    public void removeRoleForRole(String role, String removeRole);
    public void removeRoleForRole(String role, Set<String> removedRoleList);
    public void removeGroupForRole(String role, String group);
    public void removeGroupForRole(String role, Set<String> groupList);
    
    public void addMenuForRole(String role, String menu,Map<String,String> map);
    public void addMenuForRole(String role, List<String> menuList,Map<String,String> map);
    public void addMenuForGroup(String group, String menu,Map<String,String> map);
    public void addMenuForGroup(String group, List<String> menuList,Map<String,String> map);
    public void addMenuForUser(String user, String menu,Map<String,String> map);
    public void addMenuForUser(String user, List<String> menuList,Map<String,String> map);
    
    public void addMenuForRole(List<String> roleList, String menu, Map<String,String> map);
    public void addMenuForRole(List<String> roleList, List<String> menuList, Map<String,String> map);
    public void addMenuForGroup(List<String> groupList, String menu, Map<String,String> map);
    public void addMenuForGroup(List<String> groupList, List<String> menuList, Map<String,String> map);
    public void addMenuForUser(List<String> userIdList, String menu, Map<String,String> map);
    public void addMenuForUser(List<String> userIdList, List<String> menuList, Map<String,String> map);
    
    public void removeMenuForRole(String role, String menu);
    public void removeMenuForRole(String role, List<String> menuList);
    public void removeMenuForGroup(String group, String menu);
    public void removeMenuForGroup(String group, List<String> menuList);
    public void removeMenuForUser(String userId, String menu);
    public void removeMenuForUser(String userId, List<String> menuList);
    
    public void removeMenuForRole(List<String> roleList, String menu);
    public void removeMenuForRole(List<String> roleList, List<String> menuList);
    public void removeMenuForGroup(List<String> groupList, String menu);
    public void removeMenuForGroup(List<String> groupList, List<String> menuList);
    public void removeMenuForUser(List<String> userIdList, String menu);
    public void removeMenuForUser(List<String> userIdList, List<String> menuList);
    
    public List<OmcSchemaMenuVO> getMenuListForRole(String role);
    public List<OmcSchemaMenuVO> getMenuListForGroup(String group);
    public List<OmcSchemaMenuVO> getMenuListForUser(String userId);
    
    public List<OmcSchemaMenuVO> getMenuListForRole(String role, boolean expand);
    public List<OmcSchemaMenuVO> getMenuListForGroup(String group,boolean expand);
    public List<OmcSchemaMenuVO> getMenuListForUser(String userId,boolean expand);
    public List<OmcSchemaMenuVO> getMenuListForCommonUserWithNames(List<String> nameList,boolean expand);
    public Set<String> getMenuSetForCommonUserWithNames(List<String> nameList,boolean expand);
    
    public void changePasserod(String userId, String password);
    public void changeSite(String userId, String site);
    public void setUserDescription(String userId, String descriptions);
    public void changeDepartment(String userId, String departmentCode,String departmentDesc, String departmentDescKr);
    public void changeMailId(String userId, String emailId);
    
    public Date getSessionUserLocalTimeForUser(UserPropertyVO searchInfo);

}
