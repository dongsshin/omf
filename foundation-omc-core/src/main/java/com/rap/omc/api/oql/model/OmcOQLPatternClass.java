package com.rap.omc.api.oql.model;

import java.util.List;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLPatternClass extends OmcOQLRoot{
    private List<String>    classNameList;
    private List<String>    dbmsTableList;
    private String          alias    ;
	public OmcOQLPatternClass() {
		super();
	}
	public OmcOQLPatternClass(List<String> classNameList,
			List<String> dbmsTableList, String alias) {
		super();
		this.classNameList = classNameList;
		this.dbmsTableList = dbmsTableList;
		this.alias = alias;
	}
	public List<String> getClassNameList() {
		return classNameList;
	}
	public void setClassNameList(List<String> classNameList) {
		this.classNameList = classNameList;
	}
	public List<String> getDbmsTableList() {
		return dbmsTableList;
	}
	public void setDbmsTableList(List<String> dbmsTableList) {
		this.dbmsTableList = dbmsTableList;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
    @Override
    public String toString(){
        return "OmcOQLPatternClass [classNameList=" + classNameList + ", dbmsTableList=" + dbmsTableList + ", alias="
                + alias + "]";
    }
    public boolean equals(OmcOQLPatternClass parm){
        if(this.getAlias().equals(parm.getAlias())) return true;
        return false;
    }
    
	
}
