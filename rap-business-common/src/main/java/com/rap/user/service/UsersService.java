/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : UsersService.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 15. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.rap.omc.foundation.user.model.UserInfoVO;
import com.rap.user.model.UsersProfileVO;
import com.rap.user.model.UsersSearchVO;

import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.AbstractUsersVO;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.model.WBSActivitiesVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.relation.model.MembersVO;



/**
 * <pre>
 * Class : UsersService
 * Description : 
 * </pre>
 * 
 * @author hyeyoung.park
 */
public interface UsersService {
	public boolean hasRole(String roleCode);
	public boolean hasGroup(String group);
	public boolean hasAuth(String authName);
	public boolean hasAuth(String authName, String divisionCode);
	public boolean hasAuthExceptDivision(String authName);
	public Set<String> getRoleSet();
	public Set<String> getGroupSet();
	public Set<String> getAccessibleMenuSet();
    public Set<String> getManagementRoleSet();
    public String getUserId();
	public boolean isAssignedGroup(String groupCode);
    public List<UsersVO> retrieveUserList(UsersSearchVO searchInfo);
	public UsersVO getUserInfo(String userId);
	public Users txnUpdateUserInfo(UsersProfileVO usersProfileVO);
    public void txnUpdateSessionPlant(String userId, String preferredSite);
    public HashMap<String, Object> deleteInactiveUser(List<UserInfoVO> dataList);
    public HashMap<String, Object> deleteUserAuth(List<UsersVO> dataList);
    public void txnDeleteUserAuth(String userObid);
    public UsersVO retrieveUserInfoByMailId(String mailId);
    public UsersVO retrieveUserInfoByEmpNo(String empNo);
    public void txnInitializeUserPortal(List<UsersVO> userList, String targetModule);
    public void txnInitializeUserPortal(String userObid, String targetModule);
    public List<AbstractUsersVO> retrieveAbstractUserList(UsersSearchVO searchInfo);

    public List<WBSActivitiesVO> getMyActivities(Set<String> classFilter, boolean isDelayedOnly);
    public List<WBSActivitiesVO> getMyActivities(Set<String> classFilter, boolean isDelayedOnly, boolean onlyPrevActivityComplete);
    public List<WBSJobActivityVO> getMyJobActivities(boolean notCompletedOnly);
    public List<UsersVO> retriveUserDivisionMappingList(UsersSearchVO searchInfo);
    public void txnUpdateUserDivisionMappingList(List<MembersVO> createList, List<MembersVO> deleteList);
    public void txnCopyUserDivisionMappingList(String copyType, String fromObid, String toObid);
    
    public HashMap<String, Object> excelImportUsers( List<UsersVO> dataList );
    public List<UsersVO> getUserProperty(UsersSearchVO searchInfo);
    public void modifyUserProperty(UsersVO userVO, HashMap<String, Object> propertyParams);
    public HashMap<String, Object> excelImportUserProperty(UsersVO userVO, HashMap<String, Object> propertyParams);
}
