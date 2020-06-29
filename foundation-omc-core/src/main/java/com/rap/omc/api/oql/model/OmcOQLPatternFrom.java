package com.rap.omc.api.oql.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLPatternFrom extends OmcOQLRoot{
	private OmcOQLPatternClass fromClassPattern;
	private String             connectedType;
	private OmcOQLPatternClass toClassPattern;
	public OmcOQLPatternFrom() {
		super();
	}
	public OmcOQLPatternFrom(OmcOQLPatternClass fromClassPattern,
			String connectedType, OmcOQLPatternClass toClassPattern) {
		super();
		this.fromClassPattern = fromClassPattern;
		this.connectedType = connectedType;
		this.toClassPattern = toClassPattern;
	}
	public OmcOQLPatternClass getFromClassPattern() {
		return fromClassPattern;
	}
	public void setFromClassPattern(OmcOQLPatternClass fromClassPattern) {
		this.fromClassPattern = fromClassPattern;
	}
	public String getConnectedType() {
		return connectedType;
	}
	public void setConnectedType(String connectedType) {
		this.connectedType = connectedType;
	}
	public OmcOQLPatternClass getToClassPattern() {
		return toClassPattern;
	}
	public void setToClassPattern(OmcOQLPatternClass toClassPattern) {
		this.toClassPattern = toClassPattern;
	}
    @Override
    public String toString(){
        return "OmcOQLPatternFrom [fromClassPattern=" + fromClassPattern + ", connectedType=" + connectedType
                + ", toClassPattern=" + toClassPattern + "]";
    }
	
}
