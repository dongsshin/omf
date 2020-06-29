package com.rap.omc.foundation.user.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class UserSessionVO implements Serializable{
	private static final long serialVersionUID = 3714222101462686539L;
	private String userId;
    private String userBizObid;
    private String userSysObid;
    private String userNameEng;
    private String userNameKor;
    private String ssoid;
    private String userLoginSite;
    private String executor;
    private String email;
    private String telephone;
    private String pagerNumber;
    private String excutorId;
    private String departmentCode;
    private String departmentDesc;
    private String accountingDepartmentCode;
    private String accountingDepartmentCodeDesc;
    private String plantUnitCode;
    private String plantUnitCodeDesc;
    private String divisionUnitCode;
    private String divisionUnitCodeDesc;
    private String businessUnitCode;
    private String businessUnitCodeDesc;
    private String companyCode;
    private String companyCodeDesc;
	private String loginUrl;
    private String userTimeZone;
    private String userLocale;
    private String daylightSavings;
    private String workGroup;
    private String privateFolder;
    private String defaultPrivateFolder;
    private String defaultProject;
    private String defaultProjectFolder;
    private Set<String> roleSet;
    private Set<String> groupSet;
    private Set<String> accessibleMenuSet;
    private Map<String,String> propertyMap;
    private Set<String> managementRoleSet;
    private String timeStamp;
    private String loginIp;
    private String mainModule;
    private String leaderUserId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserBizObid() {
		return userBizObid;
	}
	public void setUserBizObid(String userBizObid) {
		this.userBizObid = userBizObid;
	}
	public String getUserSysObid() {
		return userSysObid;
	}
	public void setUserSysObid(String userSysObid) {
		this.userSysObid = userSysObid;
	}
	public String getUserNameEng() {
		return userNameEng;
	}
	public void setUserNameEng(String userNameEng) {
		this.userNameEng = userNameEng;
	}
	public String getUserNameKor() {
		return userNameKor;
	}
	public void setUserNameKor(String userNameKor) {
		this.userNameKor = userNameKor;
	}
	public String getSsoid() {
		return ssoid;
	}
	public void setSsoid(String ssoid) {
		this.ssoid = ssoid;
	}
	public String getUserLoginSite() {
		return userLoginSite;
	}
	public void setUserLoginSite(String userLoginSite) {
		this.userLoginSite = userLoginSite;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPagerNumber() {
		return pagerNumber;
	}
	public void setPagerNumber(String pagerNumber) {
		this.pagerNumber = pagerNumber;
	}
	public String getExcutorId() {
		return excutorId;
	}
	public void setExcutorId(String excutorId) {
		this.excutorId = excutorId;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	public String getAccountingDepartmentCode() {
		return accountingDepartmentCode;
	}
	public void setAccountingDepartmentCode(String accountingDepartmentCode) {
		this.accountingDepartmentCode = accountingDepartmentCode;
	}
	public String getAccountingDepartmentCodeDesc() {
		return accountingDepartmentCodeDesc;
	}
	public void setAccountingDepartmentCodeDesc(String accountingDepartmentCodeDesc) {
		this.accountingDepartmentCodeDesc = accountingDepartmentCodeDesc;
	}
	public String getPlantUnitCode() {
		return plantUnitCode;
	}
	public void setPlantUnitCode(String plantUnitCode) {
		this.plantUnitCode = plantUnitCode;
	}
	public String getPlantUnitCodeDesc() {
		return plantUnitCodeDesc;
	}
	public void setPlantUnitCodeDesc(String plantUnitCodeDesc) {
		this.plantUnitCodeDesc = plantUnitCodeDesc;
	}
	public String getDivisionUnitCode() {
		return divisionUnitCode;
	}
	public void setDivisionUnitCode(String divisionUnitCode) {
		this.divisionUnitCode = divisionUnitCode;
	}
	public String getDivisionUnitCodeDesc() {
		return divisionUnitCodeDesc;
	}
	public void setDivisionUnitCodeDesc(String divisionUnitCodeDesc) {
		this.divisionUnitCodeDesc = divisionUnitCodeDesc;
	}
	public String getBusinessUnitCode() {
		return businessUnitCode;
	}
	public void setBusinessUnitCode(String businessUnitCode) {
		this.businessUnitCode = businessUnitCode;
	}
	public String getBusinessUnitCodeDesc() {
		return businessUnitCodeDesc;
	}
	public void setBusinessUnitCodeDesc(String businessUnitCodeDesc) {
		this.businessUnitCodeDesc = businessUnitCodeDesc;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyCodeDesc() {
		return companyCodeDesc;
	}
	public void setCompanyCodeDesc(String companyCodeDesc) {
		this.companyCodeDesc = companyCodeDesc;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getUserTimeZone() {
		return userTimeZone;
	}
	public void setUserTimeZone(String userTimeZone) {
		this.userTimeZone = userTimeZone;
	}
	public String getUserLocale() {
		return userLocale;
	}
	public void setUserLocale(String userLocale) {
		this.userLocale = userLocale;
	}
	public String getDaylightSavings() {
		return daylightSavings;
	}
	public void setDaylightSavings(String daylightSavings) {
		this.daylightSavings = daylightSavings;
	}
	public String getWorkGroup() {
		return workGroup;
	}
	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}
	public String getPrivateFolder() {
		return privateFolder;
	}
	public void setPrivateFolder(String privateFolder) {
		this.privateFolder = privateFolder;
	}
	public String getDefaultPrivateFolder() {
		return defaultPrivateFolder;
	}
	public void setDefaultPrivateFolder(String defaultPrivateFolder) {
		this.defaultPrivateFolder = defaultPrivateFolder;
	}
	public String getDefaultProject() {
		return defaultProject;
	}
	public void setDefaultProject(String defaultProject) {
		this.defaultProject = defaultProject;
	}
	public String getDefaultProjectFolder() {
		return defaultProjectFolder;
	}
	public void setDefaultProjectFolder(String defaultProjectFolder) {
		this.defaultProjectFolder = defaultProjectFolder;
	}
	public Set<String> getRoleSet() {
		return roleSet;
	}
	public void setRoleSet(Set<String> roleSet) {
		this.roleSet = roleSet;
	}
	public Set<String> getGroupSet() {
		return groupSet;
	}
	public void setGroupSet(Set<String> groupSet) {
		this.groupSet = groupSet;
	}
	public Set<String> getAccessibleMenuSet() {
		return accessibleMenuSet;
	}
	public void setAccessibleMenuSet(Set<String> accessibleMenuSet) {
		this.accessibleMenuSet = accessibleMenuSet;
	}
	public Map<String, String> getPropertyMap() {
		return propertyMap;
	}
	public void setPropertyMap(Map<String, String> propertyMap) {
		this.propertyMap = propertyMap;
	}
	public Set<String> getManagementRoleSet() {
		return managementRoleSet;
	}
	public void setManagementRoleSet(Set<String> managementRoleSet) {
		this.managementRoleSet = managementRoleSet;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getMainModule() {
		return mainModule;
	}
	public void setMainModule(String mainModule) {
		this.mainModule = mainModule;
	}
	public String getLeaderUserId() {
		return leaderUserId;
	}
	public void setLeaderUserId(String leaderUserId) {
		this.leaderUserId = leaderUserId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "UserSessionVO [userId=" + userId + ", userBizObid=" + userBizObid + ", userSysObid=" + userSysObid
				+ ", userNameEng=" + userNameEng + ", userNameKor=" + userNameKor + ", ssoid=" + ssoid
				+ ", userLoginSite=" + userLoginSite + ", executor=" + executor + ", email=" + email + ", telephone="
				+ telephone + ", pagerNumber=" + pagerNumber + ", excutorId=" + excutorId + ", departmentCode="
				+ departmentCode + ", departmentDesc=" + departmentDesc + ", accountingDepartmentCode="
				+ accountingDepartmentCode + ", accountingDepartmentCodeDesc=" + accountingDepartmentCodeDesc
				+ ", plantUnitCode=" + plantUnitCode + ", plantUnitCodeDesc=" + plantUnitCodeDesc
				+ ", divisionUnitCode=" + divisionUnitCode + ", divisionUnitCodeDesc=" + divisionUnitCodeDesc
				+ ", businessUnitCode=" + businessUnitCode + ", businessUnitCodeDesc=" + businessUnitCodeDesc
				+ ", companyCode=" + companyCode + ", companyCodeDesc=" + companyCodeDesc + ", loginUrl=" + loginUrl
				+ ", userTimeZone=" + userTimeZone + ", userLocale=" + userLocale + ", daylightSavings="
				+ daylightSavings + ", workGroup=" + workGroup + ", privateFolder=" + privateFolder
				+ ", defaultPrivateFolder=" + defaultPrivateFolder + ", defaultProject=" + defaultProject
				+ ", defaultProjectFolder=" + defaultProjectFolder + ", roleSet=" + roleSet + ", groupSet=" + groupSet
				+ ", accessibleMenuSet=" + accessibleMenuSet + ", propertyMap=" + propertyMap + ", managementRoleSet="
				+ managementRoleSet + ", timeStamp=" + timeStamp + ", loginIp=" + loginIp + ", mainModule=" + mainModule
				+ ", leaderUserId=" + leaderUserId + "]";
	}
}
