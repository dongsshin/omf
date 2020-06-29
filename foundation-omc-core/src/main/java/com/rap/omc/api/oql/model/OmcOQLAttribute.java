package com.rap.omc.api.oql.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLAttribute extends OmcOQLRoot{
    private String attributeName;
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

    public long getFlags(){
        return flags;
    }
    
    public void setFlags(long flags){
        this.flags = flags;
    }
    /**
     * 
     */
    public OmcOQLAttribute() {
        super();
    }
    public OmcOQLAttribute(String attributeName, String dbmsColumnAliasName,
			int dataType, long flags,String valueSettingInfo) {
		super();
		this.attributeName = attributeName;
		this.dbmsColumnAliasName = dbmsColumnAliasName;
		this.dataType = dataType;
		this.flags = flags;
		this.valueSettingInfo = valueSettingInfo;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
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
        return "OmcOQLAttribute [attributeName=" + attributeName + ", dbmsColumnAliasName=" + dbmsColumnAliasName
                + ", dataType=" + dataType + "]";
    }
    
}
