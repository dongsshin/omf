package com.rap.omc.foundation.user.model;

import java.util.HashMap;
import java.util.Map;

public class UserInfoVO {
    private String userId;
    private String userNameEng;
    private String userNameKor;
    private String ssoid;
    private String email;
    private String departmentCode;
    private String departmentDesc;
    private String accountingDepartmentCode;
    private String pagerNumber;
    private String organizationCode;
    private String organizationDesc;
    private String organizationCodeUpper;
    private String organizationUpperDesc;
    private Map<String,Object> extraAttr = new HashMap<String,Object>();
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPagerNumber() {
		return pagerNumber;
	}
	public void setPagerNumber(String pagerNumber) {
		this.pagerNumber = pagerNumber;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public String getOrganizationDesc() {
		return organizationDesc;
	}
	public void setOrganizationDesc(String organizationDesc) {
		this.organizationDesc = organizationDesc;
	}
	public String getOrganizationCodeUpper() {
		return organizationCodeUpper;
	}
	public void setOrganizationCodeUpper(String organizationCodeUpper) {
		this.organizationCodeUpper = organizationCodeUpper;
	}
	public String getOrganizationUpperDesc() {
		return organizationUpperDesc;
	}
	public void setOrganizationUpperDesc(String organizationUpperDesc) {
		this.organizationUpperDesc = organizationUpperDesc;
	}
	public Map<String, Object> getExtraAttr() {
		return extraAttr;
	}
	public void setExtraAttr(Map<String, Object> extraAttr) {
		this.extraAttr = extraAttr;
	}
	
    
}
