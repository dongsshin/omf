package com.rap.omc.api.oql.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLParsedWhereSQuery extends OmcOQLRoot{
    private String whereSelect;
    private String whereParameter;
    private String whereOperator;
    private String whereValue;
    
	public OmcOQLParsedWhereSQuery(String whereSelect, String whereParameter,
			String whereOperator, String whereValue) {
		super();
		this.whereSelect = whereSelect;
		this.whereParameter = whereParameter;
		this.whereOperator = whereOperator;
		this.whereValue = whereValue;
	}
	public String getWhereSelect() {
		return whereSelect;
	}
	public void setWhereSelect(String whereSelect) {
		this.whereSelect = whereSelect;
	}
	public String getWhereParameter() {
		return whereParameter;
	}
	public void setWhereParameter(String whereParameter) {
		this.whereParameter = whereParameter;
	}
	public String getWhereOperator() {
		return whereOperator;
	}
	public void setWhereOperator(String whereOperator) {
		this.whereOperator = whereOperator;
	}
	public String getWhereValue() {
		return whereValue;
	}
	public void setWhereValue(String whereValue) {
		this.whereValue = whereValue;
	}
    
}
