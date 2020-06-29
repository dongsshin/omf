package com.rap.omc.framework.mail.policy;

public class MailTemplate{
    private String directory;
    private String htmlName;
    private String imgServerProtocol;
    private String imgServerIp;
    private String imgServerPort;
    private String imgServerContext;
    private boolean saveResolvedhtmlFlag = false;
    private String saveResolvedhtmlDir;
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getHtmlName() {
		return htmlName;
	}
	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
	}
	public String getImgServerProtocol() {
		return imgServerProtocol;
	}
	public void setImgServerProtocol(String imgServerProtocol) {
		this.imgServerProtocol = imgServerProtocol;
	}
	public String getImgServerIp() {
		return imgServerIp;
	}
	public void setImgServerIp(String imgServerIp) {
		this.imgServerIp = imgServerIp;
	}
	public String getImgServerPort() {
		return imgServerPort;
	}
	public void setImgServerPort(String imgServerPort) {
		this.imgServerPort = imgServerPort;
	}
	public String getImgServerContext() {
		return imgServerContext;
	}
	public void setImgServerContext(String imgServerContext) {
		this.imgServerContext = imgServerContext;
	}
	public boolean isSaveResolvedhtmlFlag() {
		return saveResolvedhtmlFlag;
	}
	public void setSaveResolvedhtmlFlag(boolean saveResolvedhtmlFlag) {
		this.saveResolvedhtmlFlag = saveResolvedhtmlFlag;
	}
	public String getSaveResolvedhtmlDir() {
		return saveResolvedhtmlDir;
	}
	public void setSaveResolvedhtmlDir(String saveResolvedhtmlDir) {
		this.saveResolvedhtmlDir = saveResolvedhtmlDir;
	}
    
}