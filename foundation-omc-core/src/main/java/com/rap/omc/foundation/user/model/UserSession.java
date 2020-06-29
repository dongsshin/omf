/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : UserSession.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 10. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.user.model;

import java.util.Map;
import java.util.Set;
public interface UserSession {
	
	public String getUserBizObid();
	public void setUserBizObid(String userBizObid);
	public String getUserSysObid();
	public void setUserSysObid(String userSysObid);
	public String getTimeStamp();
	public void setTimeStamp(String timeStamp);
	public String getUserId();
	public void setUserId(String userId);
	public String getUserNameEng();
	public void setUserNameEng(String userNameEng);
	public String getUserNameKor();
	public void setUserNameKor(String userNameKor);
	public String getSsoid();
	public void setSsoid(String ssoid);
	public String getUserLoginSite();
	public void setUserLoginSite(String userLoginSite);
	public String getExecutor();
	public void setExecutor(String executor);
	public String getEmail();
	public void setEmail(String email);
	public String getTelephone();
	public void setTelephone(String telephone);
	public String getPagerNumber();
	public void setPagerNumber(String pagerNumber);
	public String getExcutorId() ;
	public void setExcutorId(String excutorId);
	public String getDepartmentCode() ;
	public void setDepartmentCode(String departmentCode);
	public String getDepartmentDesc();
	public void setDepartmentDesc(String departmentDesc);
	public String getAccountingDepartmentCode();
	public void setAccountingDepartmentCode(String accountingDepartmentCode);
	public String getAccountingDepartmentCodeDesc() ;
	public void setAccountingDepartmentCodeDesc(String accountingDepartmentCodeDesc);
	public String getPlantUnitCode();
	public void setPlantUnitCode(String plantUnitCode);
	public String getPlantUnitCodeDesc();
	public void setPlantUnitCodeDesc(String plantUnitCodeDesc);
	public String getDivisionUnitCode();
	public void setDivisionUnitCode(String divisionUnitCode);
	public String getDivisionUnitCodeDesc();
	public void setDivisionUnitCodeDesc(String divisionUnitCodeDesc);
	public String getBusinessUnitCode();
	public void setBusinessUnitCode(String businessUnitCode);
	public String getBusinessUnitCodeDesc();
	public void setBusinessUnitCodeDesc(String businessUnitCodeDesc);
	public String getCompanyCode();
	public void setCompanyCode(String companyCode);
	public String getCompanyCodeDesc();
	public void setCompanyCodeDesc(String companyCodeDesc);
	public String getLoginUrl();
	public void setLoginUrl(String loginUrl) ;
	public String getUserTimeZone();
	public void setUserTimeZone(String userTimeZone);
	public String getUserLocale();
	public void setUserLocale(String userLocale) ;
	public String getDaylightSavings();
	public void setDaylightSavings(String daylightSavings);
	public String getWorkGroup() ;
	public void setWorkGroup(String workGroup) ;
	public String getPrivateFolder() ;
	public void setPrivateFolder(String privateFolder);
	public String getDefaultPrivateFolder() ;
	public void setDefaultPrivateFolder(String defaultPrivateFolder);
	public String getDefaultProject() ;
	public void setDefaultProject(String defaultProject);
	public String getDefaultProjectFolder();
	public void setDefaultProjectFolder(String defaultProjectFolder) ;
	public Set<String> getRoleSet() ;
	public void setRoleSet(Set<String> roleSet);
	public Set<String> getGroupSet() ;
	public void setGroupSet(Set<String> groupSet) ;
	public Set<String> getAccessibleMenuSet();
	public void setAccessibleMenuSet(Set<String> accessibleMenuSet);
	public Map<String, String> getPropertyMap() ;
	public void setPropertyMap(Map<String, String> propertyMap);
	public Set<String> getManagementRoleSet();
	public void setManagementRoleSet(Set<String> managementRoleSet);
    public String getLoginIp();
	public void setLoginIp(String loginIp);
    public String getMainModule();
	public void setMainModule(String mainModule);
    public String getLeaderUserId();
	public void setLeaderUserId(String leaderUserId);
	public String toString();
	
}