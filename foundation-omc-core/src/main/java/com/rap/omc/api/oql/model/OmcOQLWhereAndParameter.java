package com.rap.omc.api.oql.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLWhereAndParameter extends OmcOQLRoot{
    private String patternWhere;
    private String patternParameter;
	public OmcOQLWhereAndParameter() {
		super();
	}
	public OmcOQLWhereAndParameter(String patternWhere, String patternParameter) {
		super();
		this.patternWhere = patternWhere;
		this.patternParameter = patternParameter;
	}
	public String getPatternWhere() {
		return patternWhere;
	}
	public void setPatternWhere(String patternWhere) {
		this.patternWhere = patternWhere;
	}
	public String getPatternParameter() {
		return patternParameter;
	}
	public void setPatternParameter(String patternParameter) {
		this.patternParameter = patternParameter;
	}
    @Override
    public String toString(){
        return "OmcOQLWhereAndParameter [patternWhere=" + patternWhere + ", patternParameter=" + patternParameter + "]";
    }
}
