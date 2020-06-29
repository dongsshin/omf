package com.rap.omc.api.oql.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLTableAndClass extends OmcOQLRoot{
    private String className;
    private String dbmsTable;
    
	/**
     * 
     */
    public OmcOQLTableAndClass() {
        super();
    }

    /**
     * @param className
     * @param dbmsTable
     */
    public OmcOQLTableAndClass(String className, String dbmsTable) {
        super();
        this.className = className;
        this.dbmsTable = dbmsTable;
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
	
    
}
