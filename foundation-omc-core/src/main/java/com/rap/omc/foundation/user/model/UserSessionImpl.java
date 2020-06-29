package com.rap.omc.foundation.user.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
@Service("userSession")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionImpl implements UserSession, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5576164375209747874L;
	/**
	 * 
	 */
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
    @Override
    public String getLeaderUserId() {
		return leaderUserId;
	}
    @Override
	public void setLeaderUserId(String leaderUserId) {
		this.leaderUserId = leaderUserId;
	}
	@Override
    public String getMainModule() {
		return mainModule;
	}
    @Override
	public void setMainModule(String mainModule) {
		this.mainModule = mainModule;
	}
	@Override
    public String getLoginIp() {
		return loginIp;
	}
    @Override
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	@Override
    public String getUserBizObid() {
		return userBizObid;
	}
    @Override
	public void setUserBizObid(String userBizObid) {
		this.userBizObid = userBizObid;
	}
    @Override
	public String getUserSysObid() {
		return userSysObid;
	}
    @Override
	public void setUserSysObid(String userSysObid) {
		this.userSysObid = userSysObid;
	}
    @Override
	public String getTimeStamp() {
		return timeStamp;
	}
    @Override
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
    @Override
	public String getUserId() {
		return userId;
	}
    @Override
	public void setUserId(String userId) {
		this.userId = userId;
	}
    @Override
    public String getUserNameEng() {
		return userNameEng;
	}
    @Override
	public void setUserNameEng(String userNameEng) {
		this.userNameEng = userNameEng;
	}
    @Override
	public String getUserNameKor() {
		return userNameKor;
	}
    @Override
	public void setUserNameKor(String userNameKor) {
		this.userNameKor = userNameKor;
	}
    @Override
	public String getSsoid() {
		return ssoid;
	}
    @Override
	public void setSsoid(String ssoid) {
		this.ssoid = ssoid;
	}
    @Override
	public String getUserLoginSite() {
		return userLoginSite;
	}
    @Override
	public void setUserLoginSite(String userLoginSite) {
		this.userLoginSite = userLoginSite;
	}
    @Override
	public String getExecutor() {
		return executor;
	}
    @Override
	public void setExecutor(String executor) {
		this.executor = executor;
	}
    @Override
	public String getEmail() {
		return email;
	}
    @Override
	public void setEmail(String email) {
		this.email = email;
	}
    @Override
	public String getTelephone() {
		return telephone;
	}
    @Override
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
    @Override
	public String getPagerNumber() {
		return pagerNumber;
	}
    @Override
	public void setPagerNumber(String pagerNumber) {
		this.pagerNumber = pagerNumber;
	}
    @Override
	public String getExcutorId() {
		return excutorId;
	}
    @Override
	public void setExcutorId(String excutorId) {
		this.excutorId = excutorId;
	}
    @Override
	public String getDepartmentCode() {
		return departmentCode;
	}
    @Override
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
    @Override
	public String getDepartmentDesc() {
		return departmentDesc;
	}
    @Override
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
    @Override
	public String getAccountingDepartmentCode() {
		return accountingDepartmentCode;
	}
    @Override
	public void setAccountingDepartmentCode(String accountingDepartmentCode) {
		this.accountingDepartmentCode = accountingDepartmentCode;
	}
    @Override
	public String getAccountingDepartmentCodeDesc() {
		return accountingDepartmentCodeDesc;
	}
    @Override
	public void setAccountingDepartmentCodeDesc(String accountingDepartmentCodeDesc) {
		this.accountingDepartmentCodeDesc = accountingDepartmentCodeDesc;
	}
    @Override
	public String getPlantUnitCode() {
		return plantUnitCode;
	}
    @Override
	public void setPlantUnitCode(String plantUnitCode) {
		this.plantUnitCode = plantUnitCode;
	}
    @Override
	public String getPlantUnitCodeDesc() {
		return plantUnitCodeDesc;
	}
    @Override
	public void setPlantUnitCodeDesc(String plantUnitCodeDesc) {
		this.plantUnitCodeDesc = plantUnitCodeDesc;
	}
    @Override
	public String getDivisionUnitCode() {
		return divisionUnitCode;
	}
    @Override
	public void setDivisionUnitCode(String divisionUnitCode) {
		this.divisionUnitCode = divisionUnitCode;
	}
    @Override
	public String getDivisionUnitCodeDesc() {
		return divisionUnitCodeDesc;
	}
    @Override
	public void setDivisionUnitCodeDesc(String divisionUnitCodeDesc) {
		this.divisionUnitCodeDesc = divisionUnitCodeDesc;
	}
    @Override
	public String getBusinessUnitCode() {
		return businessUnitCode;
	}
    @Override
	public void setBusinessUnitCode(String businessUnitCode) {
		this.businessUnitCode = businessUnitCode;
	}
    @Override
	public String getBusinessUnitCodeDesc() {
		return businessUnitCodeDesc;
	}
	@Override
	public void setBusinessUnitCodeDesc(String businessUnitCodeDesc) {
		this.businessUnitCodeDesc = businessUnitCodeDesc;
	}
	@Override
	public String getCompanyCode() {
		return companyCode;
	}
	@Override
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	@Override
	public String getCompanyCodeDesc() {
		return companyCodeDesc;
	}
	@Override
	public void setCompanyCodeDesc(String companyCodeDesc) {
		this.companyCodeDesc = companyCodeDesc;
	}
	@Override
	public String getLoginUrl() {
		return loginUrl;
	}
	@Override
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
    @Override
	public String getUserTimeZone() {
		return userTimeZone;
	}

	public void setUserTimeZone(String userTimeZone) {
		this.userTimeZone = userTimeZone;
	}
	@Override
	public String getUserLocale() {
		return userLocale;
	}
	@Override
	public void setUserLocale(String userLocale) {
		this.userLocale = userLocale;
	}
	@Override
	public String getDaylightSavings() {
		return daylightSavings;
	}
	@Override
	public void setDaylightSavings(String daylightSavings) {
		this.daylightSavings = daylightSavings;
	}
	@Override
	public String getWorkGroup() {
		return workGroup;
	}
	@Override
	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}
	@Override
	public String getPrivateFolder() {
		return privateFolder;
	}
	@Override
	public void setPrivateFolder(String privateFolder) {
		this.privateFolder = privateFolder;
	}
	@Override
	public String getDefaultPrivateFolder() {
		return defaultPrivateFolder;
	}
	@Override
	public void setDefaultPrivateFolder(String defaultPrivateFolder) {
		this.defaultPrivateFolder = defaultPrivateFolder;
	}
	@Override
	public String getDefaultProject() {
		return defaultProject;
	}
	@Override
	public void setDefaultProject(String defaultProject) {
		this.defaultProject = defaultProject;
	}
	@Override
	public String getDefaultProjectFolder() {
		return defaultProjectFolder;
	}
	@Override
	public void setDefaultProjectFolder(String defaultProjectFolder) {
		this.defaultProjectFolder = defaultProjectFolder;
	}
	@Override
	public Set<String> getRoleSet() {
		return roleSet;
	}
	@Override
	public void setRoleSet(Set<String> roleSet) {
		this.roleSet = roleSet;
	}
	@Override
	public Set<String> getGroupSet() {
		return groupSet;
	}
	@Override
	public void setGroupSet(Set<String> groupSet) {
		this.groupSet = groupSet;
	}
	@Override
	public Set<String> getAccessibleMenuSet() {
		return accessibleMenuSet;
	}
	@Override
	public void setAccessibleMenuSet(Set<String> accessibleMenuSet) {
		this.accessibleMenuSet = accessibleMenuSet;
	}
	@Override
	public Map<String, String> getPropertyMap() {
		return propertyMap;
	}
	@Override
	public void setPropertyMap(Map<String, String> propertyMap) {
		this.propertyMap = propertyMap;
	}
	@Override
	public Set<String> getManagementRoleSet() {
		return managementRoleSet;
	}
	@Override
	public void setManagementRoleSet(Set<String> managementRoleSet) {
		this.managementRoleSet = managementRoleSet;
	}
	@Override
	public String toString() {
		return "UserSessionImpl [userId=" + userId + ", userBizObid=" + userBizObid + ", userSysObid=" + userSysObid
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