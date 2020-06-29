package com.rap.omc.api.oql.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLPatternSQueryCondition extends OmcOQLRoot{
	private String whereConditionIn;
	private String whereParameterStrOut;
	private String whereConditionOut;
	private String whereResolved;
	public OmcOQLPatternSQueryCondition() {
		super();
	}
	public OmcOQLPatternSQueryCondition(String whereConditionIn,
			String whereParameterStrOut, String whereConditionOut,
			String whereResolved) {
		super();
		this.whereConditionIn = whereConditionIn;
		this.whereParameterStrOut = whereParameterStrOut;
		this.whereConditionOut = whereConditionOut;
		this.whereResolved = whereResolved;
	}
	public String getWhereConditionIn() {
		return whereConditionIn;
	}
	public void setWhereConditionIn(String whereConditionIn) {
		this.whereConditionIn = whereConditionIn;
	}
	public String getWhereParameterStrOut() {
		return whereParameterStrOut;
	}
	public void setWhereParameterStrOut(String whereParameterStrOut) {
		this.whereParameterStrOut = whereParameterStrOut;
	}
	public String getWhereConditionOut() {
		return whereConditionOut;
	}
	public void setWhereConditionOut(String whereConditionOut) {
		this.whereConditionOut = whereConditionOut;
	}
	public String getWhereResolved() {
		return whereResolved;
	}
	public void setWhereResolved(String whereResolved) {
		this.whereResolved = whereResolved;
	}
}
