package com.rap.omc.api.object.model;
public class ObjectKeyVO {

    private String obid;
    private long flags;
    private String className;
    private String names;
    private String revision;
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
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getRevision() {
		return revision;
	}
	public void setRevision(String revision) {
		this.revision = revision;
	}
    
}
