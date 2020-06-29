/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : FoundationUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 3. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util.general;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.foundation.user.model.CommonUserSearchVO;
import com.rap.omc.foundation.user.model.SysUserVO;
import com.rap.omc.foundation.user.model.User2UserResultVO;
import com.rap.omc.foundation.user.model.UserPropertyVO;
import com.rap.omc.foundation.user.service.UserService;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.omc.schema.util.OmcSystemConstants;

/**
 * 
 * <pre>
 * Class : UserManagementUtil
 * Description : 시스템 사용자 정보(psysuser, user property 등)와 관련한 CRUD 작업을 수행한다.
 * </pre>
 * 
 * @author s_dongsshin
 */
public class UserManagementUtil {

    private UserService userService;

    private static UserManagementUtil fInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static UserManagementUtil getInstance(){
        if (fInstance == null) {
            fInstance = new UserManagementUtil();
            fInstance.userService = (UserService)SpringFactoryUtil.getBean("userService");           
        }
        return fInstance;
    }

    /**
     * 사용자 Property 정보를 조회한다.
     *
     * @param userName user 정보의 name attribute
     * @param propertyName
     * @return User Property Value
     */
    public static String getUserProperty(String userName, String propertyName){
        return getInstance().userService.getPropertyValue(userName, propertyName);
    }

    /**
     * 사용자 Property 정보를 설정한다.
     *
     * @param userName user 정보의 name attribute
     * @param propertyName
     * @param propertyValue
     */
    public static void setUserProperty(String userName, String propertyName, String propertyValue){
        getInstance().userService.setPropertyValue(userName,propertyName,propertyValue);
    }

    /**
     * user의 Timezone을 적용하여, 전달인자로 넘어온 날짜를 LocalTime으로 변경하여 리턴함
     *
     * @param userName
     * @param searchDate
     * @return User Local Time
     */
    public static Date getSessionUserLocalTimeForUser(String userName, Date searchDate){
        Date localTime = null;
        UserPropertyVO searchInfo = new UserPropertyVO();
        searchInfo.setUserNames(userName);
        SimpleDateFormat dateFormat = new SimpleDateFormat(OmcSystemConstants.OMC_JAVA_DATE_FORMAT_DATE);
        searchInfo.setSearchDate(dateFormat.format(searchDate));
        localTime = getInstance().userService.getSessionUserLocalTimeForUser(searchInfo);
        return localTime;
    }
    /**
     * 사용자 정보(psysuser)를 생성한다.
     *
     * @param userId user ID/Name
     * @param kindsStr User / Role / Group / RoleGroup
     * @param password
     * @param description
     * @param site
     * @param roleList (","를 사용하여 여러 개 입력 가능)
     * @param propertyList
     */
    public static void createUser(String userId, String kindsStr, String password, String description, String site,
            String roleList, String propertyList){
        SysUserVO input = new SysUserVO();
        input.setUserId(userId);
        input.setKindsStr(kindsStr);
//임시로 막음
//      input.setPassword(DigestManager.digest(password, "SHA-512"));

        input.setPassword(password);
        input.setDescriptions(description);
        input.setSite(site);
        input.setRoleSet(roleList);
        input.setPropertyList(propertyList);
        getInstance().userService.createUser(input);
    }

    /**
     * 사용자 정보(psysuser)를 조회한다.
     *
     * @param userId
     * @return System User Info
     */
    public static SysUserVO getSysUserInfo(String userId){
        SysUserVO result = getInstance().userService.getUserInfo(userId);
        return result;
    }
    /**
     * Key값에 대한 User/Group/Role를 Return함. 일반적인 곳에서는 사용하면 절대 안됨.
     * Foundation내부적으로 사용하기 위해 만들어진 것임 
     *
     * @param userId User/Group/Role ID
     * @return System User Info
     */
    public static SysUserVO getCommonUserInfo(String userId){
        SysUserVO result = getInstance().userService.getCommonUserInfo(userId);
        return result;
    }
    /**
     * User정보를 Return(psysuser)
     *
     * @param userId User Id
     * @return System User Info
     */
    public static SysUserVO getUserInfo(String userId){
        SysUserVO result = getInstance().userService.getUserInfo(userId);
        return result;
    }
    /**
     * Role정보를 Return(psysuser)
     *
     * @param roleId Role Id
     * @return System User Info
     */
    public static SysUserVO getRoleInfo(String roleId){
        SysUserVO result = getInstance().userService.getRoleInfo(roleId);
        return result;
    }
    /**
     * Group정보를 Return(psysuser)
     *
     * @param groupId Group Id
     * @return User Info(psysuser)
     */
    public static SysUserVO getGroupInfo(String groupId){
        SysUserVO result = getInstance().userService.getGroupInfo(groupId);
        return result;
    }
    
    /**
     * 사용자 정보(psysuser) 목록을 조회한다.
     *
     * @param userId (","를 사용하여 여러 개 입력 가능)
     * @return System User Info List
     */
    public static List<SysUserVO> getSysUserInfoList(String userId){
        List<SysUserVO> userList = new ArrayList<SysUserVO>();
        String[] strList = userId.split(",");
        for(int i = 0; i < strList.length; i++){
            SysUserVO temVo = getInstance().userService.getUserInfo(strList[i]);
            if(temVo != null) userList.add(temVo);
        }
        return userList;
    }
    /**
     * Activiate User.
     *
     * @param userId User Id
     */
    public static void activiateUser(String userId){
        getInstance().userService.activiate(userId);
    }
    /**
     * Inactiviate User.
     *
     * @param userId User Id
     */
    public static void inActiviateUser(String userId){
        getInstance().userService.inActiviate(userId);
    }
    /**
     * 사용자에 대한 Property정보를 Return함.
     *
     * @param userId User Id
     * @return 해당 User에 대한 Property정보(PropertyName:Value)의 Map
     */
    public static Map<String,String> getPropertyList(String userId){
        return(getInstance().userService.getPropertyList(userId));
    }
    /**
     * 사용자에 대한 Property를 Set함.
     *
     * @param userId User Id
     * @param propertyList 반영할 Property List
     */
    public static void setPropertyList(String userId,Map<String,String> propertyList){
        getInstance().userService.setPropertyValueList(userId,propertyList);
    }
    /**
     * 
     *
     * @param userId
     * @param role
     */
    public static void addRoleToUser(String userId, String role){
        getInstance().userService.addRoleToUser(userId,role);
    }
    /**
     * 
     *
     * @param userId
     * @param roleList
     */
    public static void addRoleToUser(String userId, Set<String> roleList){
        getInstance().userService.addRoleToUser(userId,roleList);
    }
    /**
     * 
     *
     * @param userId
     * @param roleList
     */
    public static void removeRoleToUser(String userId, Set<String> roleList){
        getInstance().userService.removeRoleToUser(userId,roleList);
    }
    /**
     * 
     *
     * @param userId
     * @return Role Set
     */
    public static Set<String> getRoleList(String userId){
        return(getInstance().userService.getRoleList(userId));
    }
    /**
     * 
     *
     * @param userId
     * @param group
     */
    public static void addGroupToUser(String userId, String group){
        getInstance().userService.addGroupToUser(userId,group);
    }
    /**
     * 
     *
     * @param userId
     * @param groupList
     */
    public static void addGroupToUser(String userId, Set<String> groupList){
        getInstance().userService.addGroupToUser(userId,groupList);
    }
    /**
     * 
     *
     * @param userId
     * @param groupList
     */
    public static void removeGroupToUser(String userId, Set<String> groupList){
        getInstance().userService.removeGroupToUser(userId,groupList);
    }
    /**
     * 
     *
     * @param userId
     * @param group
     */
    public static void removeGroupToUser(String userId, String group){
        getInstance().userService.removeGroupToUser(userId,group);
    }
    /**
     * 
     *
     * @param userId
     * @return Group Set
     */
    public static Set<String> getGroupList(String userId){
        return(getInstance().userService.getGroupList(userId));
    }
    /**
     * 
     *
     * @param userId
     * @param site
     */
    public static void changeSite(String userId, String site){
        getInstance().userService.removeGroupToUser(userId,site);
    }
    /**
     * 
     *
     * @param userId
     * @param descriptions
     */
    public static void setUserDescription(String userId, String descriptions){
        getInstance().userService.setUserDescription(userId,descriptions);
    }
    /**
     * 
     *
     * @param userId
     * @param departmentCode
     * @param departmentDesc
     * @param departmentDescKr
     */
    public static void changeDepartment(String userId, String departmentCode,String departmentDesc, String departmentDescKr){
        getInstance().userService.changeDepartment(userId,departmentCode,departmentDesc,departmentDescKr);
    }
    /**
     * 
     *
     * @param userId
     * @param emailId
     */
    public static void changeMailId(String userId, String emailId){
        getInstance().userService.changeMailId(userId,emailId);
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
     */
    public static void createUserDefault(String userId,String userName, String site, String departmentCode, String departmentDesc, String departmentDescKor, String mailId){
        getInstance().userService.createUser(userId,userName, site, departmentCode, departmentDesc, departmentDescKor, mailId);
    }
    /**
     * 
     *
     * @param userId
     * @param userName
     * @param site
     * @param mailId
     */
    public static void createUser(String userId,String userName, String site, String mailId){
        getInstance().userService.createUser(userId,userName, site, mailId);
    }
    /**
     * 
     *
     * @param userId
     * @param userName
     */
    public static void createUser(String userId,String userName){
        getInstance().userService.createUser(userId,userName);
    }
    /**
     * 
     *
     * @param role
     * @param roleName
     */
    public static void createRole(String role,String roleName){
        getInstance().userService.createRole(role,roleName);
    }
    /**
     * 
     *
     * @param group
     * @param groupName
     */
    public static void createGroup(String group,String groupName){
        getInstance().userService.createGroup(group,groupName);
    }
    public static void setTimeStampSystemUser(String userId, String timeStamp){
        getInstance().userService.setTimeStampSystemUser(userId,timeStamp);
    }
    /**
     * 
     *
     * @param groupId
     * @param wantedLevel
     * @param currentLevel
     * @param uniqueStr
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getUserVOListForRole(String groupId,int wantedLevel, int currentLevel,String uniqueStr){
        return(getInstance().userService.getUserVOListForRole(groupId,wantedLevel,currentLevel,uniqueStr));
    }
    /**
     * 
     *
     * @param groupId
     * @param wantedLevel
     * @param currentLevel
     * @param uniqueStr
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getUserVOListForGroup(String groupId,int wantedLevel, int currentLevel,String uniqueStr){
        return(getInstance().userService.getUserVOListForGroup(groupId,wantedLevel,currentLevel,uniqueStr));
    }
    /**
     * 
     *
     * @param userId
     * @param wantedLevel
     * @param currentLevel
     * @param uniqueStr
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getRoleVOListForUser(String userId,int wantedLevel, int currentLevel,String uniqueStr){
        return(getInstance().userService.getRoleVOListForUser(userId,wantedLevel,currentLevel,uniqueStr));
    }
    /**
     * 
     *
     * @param roleId
     * @param wantedLevel
     * @param currentLevel
     * @param uniqueStr
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getRoleVOListForRole(String roleId,int wantedLevel, int currentLevel,String uniqueStr){
        return(getInstance().userService.getRoleVOListForRole(roleId,wantedLevel,currentLevel,uniqueStr));
    }
    /**
     * 
     *
     * @param groupId
     * @param wantedLevel
     * @param currentLevel
     * @param uniqueStr
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getRoleVOListForGroup(String groupId,int wantedLevel, int currentLevel,String uniqueStr){
        return(getInstance().userService.getRoleVOListForGroup(groupId,wantedLevel,currentLevel,uniqueStr));
    }
    /**
     * 
     *
     * @param userId
     * @param wantedLevel
     * @param currentLevel
     * @param uniqueStr
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getGroupVOListForUser(String userId,int wantedLevel, int currentLevel,String uniqueStr){
        return(getInstance().userService.getGroupVOListForUser(userId,wantedLevel,currentLevel,uniqueStr));
    }
    /**
     * 
     *
     * @param groupId
     * @param wantedLevel
     * @param currentLevel
     * @param uniqueStr
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getGroupVOListForGroup(String  groupId,int wantedLevel, int currentLevel,String uniqueStr){
        return(getInstance().userService.getGroupVOListForGroup(groupId,wantedLevel,currentLevel,uniqueStr));
    }
    /**
     * 
     *
     * @param roleId
     * @param wantedLevel
     * @param currentLevel
     * @param uniqueStr
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getGroupVOListForRole(String  roleId,int wantedLevel, int currentLevel,String uniqueStr){
        return(getInstance().userService.getGroupVOListForRole(roleId,wantedLevel,currentLevel,uniqueStr));
    }
    /**
     * 
     *
     * @param commonUserName
     * @param fromKinds
     * @param scemaKinds
     * @param toKinds
     * @param wantedLevel
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getCommonToUserListForCommonUser(String commonUserName,long fromKinds,long scemaKinds,long toKinds, int wantedLevel){
        return(getInstance().userService.getCommonToUserListForCommonUser(commonUserName,fromKinds,scemaKinds,toKinds, wantedLevel));
    }
    /**
     * 
     *
     * @param commonUserName
     * @param fromKinds
     * @param scemaKinds
     * @param toKinds
     * @param wantedLevel
     * @param currentLevel
     * @param uniqueStr
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getCommonToUserListForCommonUser(String commonUserName,long fromKinds,long scemaKinds,long toKinds,int wantedLevel,int currentLevel,String uniqueStr){
        return(getInstance().userService.getCommonToUserListForCommonUser(commonUserName,fromKinds,scemaKinds,toKinds, wantedLevel,currentLevel,uniqueStr));
    }
    /**
     * 
     *
     * @param commonUserName
     * @param fromKinds
     * @param scemaKinds
     * @param toKinds
     * @param wantedLevel
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getCommonFromUserListForCommonUser(String commonUserName,long fromKinds,long scemaKinds,long toKinds,int wantedLevel){
        return(getInstance().userService.getCommonFromUserListForCommonUser(commonUserName,fromKinds,scemaKinds,toKinds, wantedLevel));
    }
    /**
     * 
     *
     * @param commonUserName
     * @param fromKinds
     * @param scemaKinds
     * @param toKinds
     * @param wantedLevel
     * @param currentLevel
     * @param uniqueStr
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getCommonFromUserListForCommonUser(String commonUserName,long fromKinds,long scemaKinds,long toKinds,int wantedLevel,int currentLevel,String uniqueStr){
        return(getInstance().userService.getCommonFromUserListForCommonUser(commonUserName,fromKinds,scemaKinds,toKinds, wantedLevel,currentLevel,uniqueStr));
    }
    /**
     * 
     *
     * @param userId
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getCommonUserListForUser(String  userId){
        return(getInstance().userService.getCommonUserListForUser(userId));
    }
    /**
     * 
     *
     * @param roleId
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getCommonUserListForRole(String  roleId){
        return(getInstance().userService.getCommonUserListForRole(roleId));
    }
    /**
     * 
     *
     * @param groupId
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getCommonUserListForGroup(String  groupId){
        return(getInstance().userService.getCommonUserListForGroup(groupId));
    }
    /**
     * 
     *
     * @param commonId
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getCommonUserListForCommon(String  commonId){
        return(getInstance().userService.getCommonUserListForCommon(commonId));
    }
    /**
     * 
     *
     * @param searchVO
     * @return User Info
     */
    public static List<OmcSchemaUserVO> getCommonUserList(CommonUserSearchVO searchVO){
        return(getInstance().userService.getCommonUserList(searchVO));
    }
    /**
     * 
     *
     * @param commonUserId
     * @return Relation Info List
     */
    public static List<User2UserResultVO> getCommonUserAllListForUser(String commonUserId){
        return(getInstance().userService.getCommonUserAllListForUser(commonUserId));
    }
    /**
     * 
     *
     * @param input
     */
    public static void createUser(SysUserVO input){
        getInstance().userService.createUser(input);
    }
    /**
     * 
     *
     * @param roleGroup
     * @param roleGroupName
     */
    public static void createRoleGroup(String roleGroup,String roleGroupName){
        getInstance().userService.createRoleGroup(roleGroup,roleGroupName);
    }
    /**
     * 
     *
     * @param userId
     */
    public static void activiate(String userId){
        getInstance().userService.activiate(userId);
    }
    /**
     * 
     *
     * @param userId
     */
    public static void inActiviate(String userId){
        getInstance().userService.inActiviate(userId);
    }
    /**
     * 
     *
     * @param userId 
     * @param propertyName Property Name
     * @return Property Value
     */
    public static String getPropertyValue(String userId,String propertyName){
        return(getInstance().userService.getPropertyValue(userId,propertyName));
    }
    /**
     * 
     *
     * @param userId
     * @param propertyName
     * @param propertyValue
     */
    public static void setPropertyValue(String userId, String propertyName, String propertyValue){
        getInstance().userService.setPropertyValue(userId,propertyName,propertyValue);
    }
    /**
     * 
     *
     * @param userId
     * @param propertyList
     */
    public static void setPropertyValueList(String userId, Map<String,String> propertyList){
        getInstance().userService.setPropertyValueList(userId,propertyList);
    }
    /**
     * 
     *
     * @param userId
     * @param role
     */
    public static void removeRoleToUser(String userId, String role){
        getInstance().userService.removeRoleToUser(userId,role);
    }
    /**
     * 
     *
     * @param group
     * @param userId
     */
    public static void addUserForGroup(String group, String userId){
        getInstance().userService.addUserForGroup(group,userId);
    }
    /**
     * 
     *
     * @param group
     * @param userList
     */
    public static void addUserForGroup(String group, Set<String> userList){
        getInstance().userService.addUserForGroup(group,userList);
    }
    /**
     * 
     *
     * @param group
     * @param userId
     */
    public static void removeUserForGroup(String group, String userId){
        getInstance().userService.removeUserForGroup(group,userId);
    }
    /**
     * 
     *
     * @param group
     * @param userList
     */
    public static void removeUserForGroup(String group, Set<String> userList){
        getInstance().userService.removeUserForGroup(group,userList);
    }
    /**
     * 
     *
     * @param group
     * @return Group에 대한 User Set
     */
    public static Set<String> getUserListForGroup(String group){
        return(getInstance().userService.getUserListForGroup(group));
    }
    /**
     * 
     *
     * @param group
     * @param addedGroup
     */
    public static void addGroupForGroup(String group, String addedGroup){
        getInstance().userService.addGroupForGroup(group,addedGroup);
    }
    /**
     * 
     *
     * @param group
     * @param addedGroupList
     */
    public static void addGroupForGroup(String group, Set<String> addedGroupList){
        getInstance().userService.addGroupForGroup(group,addedGroupList);
    }
    /**
     * 
     *
     * @param group
     * @param role
     */
    public static void addRoleForGroup(String group, String role){
        getInstance().userService.addRoleForGroup(group,role);
    }
    /**
     * 
     *
     * @param group
     * @param roleList
     */
    public static void addRoleForGroup(String group, Set<String> roleList){
        getInstance().userService.addRoleForGroup(group,roleList);
    }
    /**
     * 
     *
     * @param group
     * @param addedGroup
     */
    public static void removeGroupForGroup(String group, String addedGroup){
        getInstance().userService.addRoleForGroup(group,addedGroup);
    }
    /**
     * 
     *
     * @param group
     * @param addedGroupList
     */
    public static void removeGroupForGroup(String group, Set<String> addedGroupList){
        getInstance().userService.removeGroupForGroup(group,addedGroupList);
    }
    /**
     * 
     *
     * @param group
     * @param role
     */
    public static void removeRoleForGroup(String group, String role){
        getInstance().userService.removeRoleForGroup(group,role);
    }
    /**
     * 
     *
     * @param group
     * @param roleList
     */
    public static void removeRoleForGroup(String group, Set<String> roleList){
        getInstance().userService.removeRoleForGroup(group,roleList);
    }
    /**
     * 
     *
     * @param role
     * @param user
     */
    public static void addUserForRole(String role, String user){
        getInstance().userService.addUserForRole(role,user);
    }
    /**
     * 
     *
     * @param role
     * @param userList
     */
    public static void addUserForRole(String role, Set<String> userList){
        getInstance().userService.addUserForRole(role,userList);
    }
    /**
     * 
     *
     * @param role
     * @param user
     */
    public static void removeUserForRole(String role, String user){
        getInstance().userService.removeUserForRole(role,user);
    }
    /**
     * 
     *
     * @param role
     * @param userList
     */
    public static void removeUserForRole(String role, Set<String> userList){
        getInstance().userService.removeUserForRole(role,userList);
    }
    /**
     * 
     *
     * @param role
     * @return 해당 Role를 가지고 있는 User List
     */
    public static Set<String> getUserListForRole(String role){
        return(getInstance().userService.getUserListForRole(role));
    }
    /**
     * 
     *
     * @param role
     * @param addedRole
     */
    public static void addRoleForRole(String role, String addedRole){
        getInstance().userService.addRoleForRole(role,addedRole);
    }
    /**
     * 
     *
     * @param role
     * @param addedRoleList
     */
    public static void addRoleForRole(String role, Set<String> addedRoleList){
        getInstance().userService.addRoleForRole(role,addedRoleList);
    }
    /**
     * 
     *
     * @param role
     * @param group
     */
    public static void addGroupForRole(String role, String group){
        getInstance().userService.addRoleForRole(role,group);
    }
    /**
     * 
     *
     * @param role
     * @param groupList
     */
    public static void addGroupForRole(String role, Set<String> groupList){
        getInstance().userService.addGroupForRole(role,groupList);
    }
    /**
     * 
     *
     * @param role
     * @param removeRole
     */
    public static void removeRoleForRole(String role, String removeRole){
        getInstance().userService.removeRoleForRole(role,removeRole);
    }
    /**
     * 
     *
     * @param role
     * @param removedRoleList
     */
    public static void removeRoleForRole(String role, Set<String> removedRoleList){
        getInstance().userService.removeRoleForRole(role,removedRoleList);
    }
    /**
     * 
     *
     * @param role
     * @param group
     */
    public static void removeGroupForRole(String role, String group){
        getInstance().userService.removeGroupForRole(role,group);
    }
    /**
     * 
     *
     * @param role
     * @param groupList
     */
    public static void removeGroupForRole(String role, Set<String> groupList){
        getInstance().userService.removeGroupForRole(role,groupList);
    }
    /**
     * 
     *
     * @param role
     * @param menu
     * @param map
     */
    public static void addMenuForRole(String role, String menu,Map<String,String> map){
        getInstance().userService.addMenuForRole(role,menu,map);
    }
    /**
     * 
     *
     * @param role
     * @param menuList
     * @param map
     */
    public static void addMenuForRole(String role, List<String> menuList,Map<String,String> map){
        getInstance().userService.addMenuForRole(role,menuList,map);
    }
    /**
     * 
     *
     * @param group
     * @param menu
     * @param map
     */
    public static void addMenuForGroup(String group, String menu,Map<String,String> map){
        getInstance().userService.addMenuForGroup(group,menu,map);
    }
    /**
     * 
     *
     * @param group
     * @param menuList
     * @param map
     */
    public static void addMenuForGroup(String group, List<String> menuList,Map<String,String> map){
        getInstance().userService.addMenuForGroup(group,menuList,map);
    }
    /**
     * 
     *
     * @param user
     * @param menu
     * @param map
     */
    public static void addMenuForUser(String user, String menu,Map<String,String> map){
        getInstance().userService.addMenuForUser(user,menu,map);
    }
    /**
     * 
     *
     * @param user
     * @param menuList
     * @param map
     */
    public static void addMenuForUser(String user, List<String> menuList,Map<String,String> map){
        getInstance().userService.addMenuForUser(user,menuList,map);
    }
    /**
     * 
     *
     * @param roleList
     * @param menu
     * @param map
     */
    public static void addMenuForRole(List<String> roleList, String menu, Map<String,String> map){
        getInstance().userService.addMenuForRole(roleList,menu,map);
    }
    /**
     * 
     *
     * @param roleList
     * @param menuList
     * @param map
     */
    public static void addMenuForRole(List<String> roleList, List<String> menuList, Map<String,String> map){
        getInstance().userService.addMenuForRole(roleList,menuList,map);
    }
    /**
     * 
     *
     * @param groupList
     * @param menu
     * @param map
     */
    public static void addMenuForGroup(List<String> groupList, String menu, Map<String,String> map){
        getInstance().userService.addMenuForGroup(groupList,menu,map);
    }
    /**
     * 
     *
     * @param groupList
     * @param menuList
     * @param map
     */
    public static void addMenuForGroup(List<String> groupList, List<String> menuList, Map<String,String> map){
        getInstance().userService.addMenuForGroup(groupList,menuList,map);
    }
    /**
     * 
     *
     * @param userIdList
     * @param menu
     * @param map
     */
    public static void addMenuForUser(List<String> userIdList, String menu, Map<String,String> map){
        getInstance().userService.addMenuForUser(userIdList,menu,map);
    }
    /**
     * 
     *
     * @param userIdList
     * @param menuList
     * @param map
     */
    public static void addMenuForUser(List<String> userIdList, List<String> menuList, Map<String,String> map){
        getInstance().userService.addMenuForUser(userIdList,menuList,map);
    }
    /**
     * 
     *
     * @param role
     * @param menu
     */
    public static void removeMenuForRole(String role, String menu){
        getInstance().userService.removeMenuForRole(role,menu);
    }
    /**
     * 
     *
     * @param role
     * @param menuList
     */
    public static void removeMenuForRole(String role, List<String> menuList){
        getInstance().userService.removeMenuForRole(role,menuList);
    }
    /**
     * 
     *
     * @param group
     * @param menu
     */
    public static void removeMenuForGroup(String group, String menu){
        getInstance().userService.removeMenuForGroup(group,menu);
    }
    /**
     * 
     *
     * @param group
     * @param menuList
     */
    public static void removeMenuForGroup(String group, List<String> menuList){
        getInstance().userService.removeMenuForGroup(group,menuList);
    }
    /**
     * 
     *
     * @param userId
     * @param menu
     */
    public static void removeMenuForUser(String userId, String menu){
        getInstance().userService.removeMenuForUser(userId,menu);
    }
    /**
     * 
     *
     * @param userId
     * @param menuList
     */
    public static void removeMenuForUser(String userId, List<String> menuList){
        getInstance().userService.removeMenuForUser(userId,menuList);
    }
    /**
     * 
     *
     * @param roleList
     * @param menu
     */
    public static void removeMenuForRole(List<String> roleList, String menu){
        getInstance().userService.removeMenuForRole(roleList,menu);
    }
    /**
     * 
     *
     * @param roleList
     * @param menuList
     */
    public static void removeMenuForRole(List<String> roleList, List<String> menuList){
        getInstance().userService.removeMenuForRole(roleList,menuList);
    }
    /**
     * 
     *
     * @param groupList
     * @param menu
     */
    public static void removeMenuForGroup(List<String> groupList, String menu){
        getInstance().userService.removeMenuForGroup(groupList,menu);
    }
    /**
     * 
     *
     * @param groupList
     * @param menuList
     */
    public static void removeMenuForGroup(List<String> groupList, List<String> menuList){
        getInstance().userService.removeMenuForGroup(groupList,menuList);
    }
    /**
     * 
     *
     * @param userIdList
     * @param menu
     */
    public static void removeMenuForUser(List<String> userIdList, String menu){
        getInstance().userService.removeMenuForUser(userIdList,menu);
    }
    /**
     * 
     *
     * @param userIdList
     * @param menuList
     */
    public static void removeMenuForUser(List<String> userIdList, List<String> menuList){
        getInstance().userService.removeMenuForUser(userIdList,menuList);
    }
    /**
     * 
     *
     * @param role Role
     * @return 해당 Role에 할당되어진 Menu List
     */
    public static List<OmcSchemaMenuVO> getMenuListForRole(String role){
        return getInstance().userService.getMenuListForRole(role);
    }
    /**
     * 
     *
     * @param group
     * @return 해당 Group에 할당되어진 Menu List
     */
    public static List<OmcSchemaMenuVO> getMenuListForGroup(String group){
        return getInstance().userService.getMenuListForGroup(group);
    }
    /**
     * 
     *
     * @param userId
     * @return 해당 User에 할당되어진 Menu List
     */
    public static List<OmcSchemaMenuVO> getMenuListForUser(String userId){
        return getInstance().userService.getMenuListForUser(userId);
    }
    /**
     * 
     *
     * @param role Role
     * @param expand 하위 전개 여부
     * @return 해당 Role에 할당되어진 Menu List
     */
    public static List<OmcSchemaMenuVO> getMenuListForRole(String role, boolean expand){
        return getInstance().userService.getMenuListForRole(role,expand);
    }
    /**
     * 
     *
     * @param group Group
     * @param expand 하위 전개여부
     * @return 해당 Group에 할당되어진 Menu List
     */
    public static List<OmcSchemaMenuVO> getMenuListForGroup(String group,boolean expand){
        return getInstance().userService.getMenuListForGroup(group,expand);
    }
    /**
     * 
     *
     * @param userId User Id
     * @param expand 하위 전개 여부
     * @return 해당 사용자에 할당되어진 Menu List
     */
    public static List<OmcSchemaMenuVO> getMenuListForUser(String userId,boolean expand){
        return getInstance().userService.getMenuListForUser(userId,expand);
    }
    /**
     * 
     *
     * @param comUserNameList User/Role/Group Id List
     * @return 사용자 List에 대해서 할당되어진 Menu List
     */
    public static List<OmcSchemaMenuVO> getMenuListForSetWithNames(List<String> comUserNameList){
        return getInstance().userService.getMenuListForCommonUserWithNames(comUserNameList,true);
    }
    /**
     * 
     *
     * @param comUserNameList
     * @return User/Role/Group 에 대해서할당되어진 Menu Set를 Return함.
     */
    public static Set<String> getMenuSetForCommonUserWithNames(List<String> comUserNameList){
        return getInstance().userService.getMenuSetForCommonUserWithNames(comUserNameList,true);
    }
    /**
     * 
     *
     * @param userId
     * @param roleSet
     * @param groupSet
     * @return userId/roleSet/groupSet에 할당되어진 Menu Set을 Return함.
     */
    public static Set<String> getMenuSet(String userId,Set<String> roleSet,Set<String> groupSet){
        List<String> allList = new ArrayList<String>();
        allList.add(userId);
        allList.addAll(roleSet);
        allList.addAll(groupSet);
        return getInstance().userService.getMenuSetForCommonUserWithNames(allList,true);
    }
    /**
     * 
     *
     * @param userId
     * @param password
     */
    public static void changePasserod(String userId, String password){
        getInstance().userService.changePasserod(userId,password);
    }
    /**
     * 
     *
     * @param searchInfo
     * @return 사용자에 대한 Local Time을 Return함.
     */
    public static Date getSessionUserLocalTimeForUser(UserPropertyVO searchInfo){
        return(getInstance().userService.getSessionUserLocalTimeForUser(searchInfo));
    }
}
