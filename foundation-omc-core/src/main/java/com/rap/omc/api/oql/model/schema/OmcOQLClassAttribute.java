package com.rap.omc.api.oql.model.schema;

import java.lang.reflect.Method;

public class OmcOQLClassAttribute extends OmcOQLRoot{
    private String className;
    private String dbmsTable;
    private String attributeName;
    private String dbmsColumnName;
    private String dbmsColumnAliasName;
    private int dataType;
    private long flags;
    private String valueSettingInfo;
    
	
    public String getValueSettingInfo(){
        return valueSettingInfo;
    }

    
    public void setValueSettingInfo(String valueSettingInfo){
        this.valueSettingInfo = valueSettingInfo;
    }

    /**
     * 
     */
    public OmcOQLClassAttribute() {
        super();
    }

	/**
     * @param className
     * @param dbmsTable
     * @param attributeName
     * @param dbmsColumnName
     * @param dbmsColumnAliasName
     * @param dataType
     */
    public OmcOQLClassAttribute(String className, String dbmsTable, String attributeName, String dbmsColumnName,
            String dbmsColumnAliasName, int dataType, long flags,String valueSettingInfo) {
        super();
        this.className = className;
        this.dbmsTable = dbmsTable;
        this.attributeName = attributeName;
        this.dbmsColumnName = dbmsColumnName;
        this.dbmsColumnAliasName = dbmsColumnAliasName;
        this.dataType = dataType;
        this.flags = flags;
        this.valueSettingInfo = valueSettingInfo;
    }

    
    public long getFlags(){
        return flags;
    }

    
    public void setFlags(long flags){
        this.flags = flags;
    }

    public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getDbmsTable() {
		return dbmsTable;
	}
	public void setDbmsTable(String dbmsTable) {
		this.dbmsTable = dbmsTable;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getDbmsColumnName() {
		return dbmsColumnName;
	}
	public void setDbmsColumnName(String dbmsColumnName) {
		this.dbmsColumnName = dbmsColumnName;
	}
	public String getDbmsColumnAliasName() {
		return dbmsColumnAliasName;
	}
	public void setDbmsColumnAliasName(String dbmsColumnAliasName) {
		this.dbmsColumnAliasName = dbmsColumnAliasName;
	}
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

    @Override
    public String toString(){
        return "OmcOQLClassAttribute [className=" + className + ", dbmsTable=" + dbmsTable + ", attributeName="
                + attributeName + ", dbmsColumnName=" + dbmsColumnName + ", dbmsColumnAliasName=" + dbmsColumnAliasName
                + ", dataType=" + dataType + "]";
    }
    
}
