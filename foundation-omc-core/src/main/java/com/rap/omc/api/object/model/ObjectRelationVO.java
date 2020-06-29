package com.rap.omc.api.object.model;
public class ObjectRelationVO {

    private String obid;
    private long flags;
    private String className;
    private String fromClass;
    private String fromObid;
    private String toClass;
    private String toObid;
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
	public String getFromClass() {
		return fromClass;
	}
	public void setFromClass(String fromClass) {
		this.fromClass = fromClass;
	}
	public String getFromObid() {
		return fromObid;
	}
	public void setFromObid(String fromObid) {
		this.fromObid = fromObid;
	}
	public String getToClass() {
		return toClass;
	}
	public void setToClass(String toClass) {
		this.toClass = toClass;
	}
	public String getToObid() {
		return toObid;
	}
	public void setToObid(String toObid) {
		this.toObid = toObid;
	}


}
