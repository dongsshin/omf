package com.rap.omc.api.object.model;
public class ObjectMasterVO {
    private String obid;
    private long flags;
    private String className;
	public String getObid() {
		return obid;
	}
	public void setObid(String obid) {
		this.obid = obid;
	}
	public long getFlags() {
		return flags;
	}
	public void setFlags(long flags) {
		this.flags = flags;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
