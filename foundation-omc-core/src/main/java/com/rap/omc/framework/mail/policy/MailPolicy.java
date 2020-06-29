package com.rap.omc.framework.mail.policy;

public class MailPolicy{

    private String mailHost = "localhost";
    private String mailPort = "25";
    private boolean authenticationEnabled = false;
    private boolean authenticationTlsEnabled = false;
    private boolean authenticationSslEnabled = false;
    private String authenticationId = "";
    private String authenticationPassword = "";
    private boolean sessionDebugMessageFlag = true;
    private boolean defaultSessionLoad = true;
    private String defaultSenderMailAddress;
    private String defaultSenderName = "Sender";
    private String plainContentType = "text/plain";
    private String htmlContentType = "text/html";
    private String charsetType = "UTF-8";
    private String encodingType = "Q";
    private boolean testMode = false;
    private String testReceivers = "";
    private String contentType = "7bit";
	public String getMailHost() {
		return mailHost;
	}
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}
	public String getMailPort() {
		return mailPort;
	}
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}
	public boolean isAuthenticationEnabled() {
		return authenticationEnabled;
	}
	public void setAuthenticationEnabled(boolean authenticationEnabled) {
		this.authenticationEnabled = authenticationEnabled;
	}
	public boolean isAuthenticationTlsEnabled() {
		return authenticationTlsEnabled;
	}
	public void setAuthenticationTlsEnabled(boolean authenticationTlsEnabled) {
		this.authenticationTlsEnabled = authenticationTlsEnabled;
	}
	public boolean isAuthenticationSslEnabled() {
		return authenticationSslEnabled;
	}
	public void setAuthenticationSslEnabled(boolean authenticationSslEnabled) {
		this.authenticationSslEnabled = authenticationSslEnabled;
	}
	public String getAuthenticationId() {
		return authenticationId;
	}
	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}
	public String getAuthenticationPassword() {
		return authenticationPassword;
	}
	public void setAuthenticationPassword(String authenticationPassword) {
		this.authenticationPassword = authenticationPassword;
	}
	public boolean isSessionDebugMessageFlag() {
		return sessionDebugMessageFlag;
	}
	public void setSessionDebugMessageFlag(boolean sessionDebugMessageFlag) {
		this.sessionDebugMessageFlag = sessionDebugMessageFlag;
	}
	public boolean isDefaultSessionLoad() {
		return defaultSessionLoad;
	}
	public void setDefaultSessionLoad(boolean defaultSessionLoad) {
		this.defaultSessionLoad = defaultSessionLoad;
	}
	public String getDefaultSenderMailAddress() {
		return defaultSenderMailAddress;
	}
	public void setDefaultSenderMailAddress(String defaultSenderMailAddress) {
		this.defaultSenderMailAddress = defaultSenderMailAddress;
	}
	public String getDefaultSenderName() {
		return defaultSenderName;
	}
	public void setDefaultSenderName(String defaultSenderName) {
		this.defaultSenderName = defaultSenderName;
	}
	public String getPlainContentType() {
		return plainContentType;
	}
	public void setPlainContentType(String plainContentType) {
		this.plainContentType = plainContentType;
	}
	public String getHtmlContentType() {
		return htmlContentType;
	}
	public void setHtmlContentType(String htmlContentType) {
		this.htmlContentType = htmlContentType;
	}
	public String getCharsetType() {
		return charsetType;
	}
	public void setCharsetType(String charsetType) {
		this.charsetType = charsetType;
	}
	public String getEncodingType() {
		return encodingType;
	}
	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}
	public boolean isTestMode() {
		return testMode;
	}
	public void setTestMode(boolean testMode) {
		this.testMode = testMode;
	}
	public String getTestReceivers() {
		return testReceivers;
	}
	public void setTestReceivers(String testReceivers) {
		this.testReceivers = testReceivers;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
    
}
