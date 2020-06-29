package com.rap.omc.api.oql.model;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLUniquePathTable extends OmcOQLRoot{
    private String uniqueStr;
    private String dbmsTable;
    private String tableAlias;
    private  int   sort;
    
    public String getUniqueStr(){
        return uniqueStr;
    }
    
    public void setUniqueStr(String uniqueStr){
        this.uniqueStr = uniqueStr;
    }
    
    public String getDbmsTable(){
        return dbmsTable;
    }
    
    public void setDbmsTable(String dbmsTable){
        this.dbmsTable = dbmsTable;
    }
    
    public String getTableAlias(){
        return tableAlias;
    }
    
    public void setTableAlias(String tableAlias){
        this.tableAlias = tableAlias;
    }
    
    public int getSort(){
        return sort;
    }
    
    public void setSort(int sort){
        this.sort = sort;
    }

    public OmcOQLUniquePathTable(String uniqueStr, String dbmsTable, String tableAlias, int sort) {
        super();
        this.uniqueStr = uniqueStr;
        this.dbmsTable = dbmsTable;
        this.tableAlias = tableAlias;
        this.sort = sort;
    }
    public boolean equals(OmcOQLUniquePathTable vo){
        if(NullUtil.isNull(vo)) return false;
        if(!this.getDbmsTable().equals(vo.getDbmsTable())) return false;
        if(this.getSort() != vo.getSort()) return false;
        if(!this.getTableAlias().equals(vo.getTableAlias())) return false;
        if(!this.getUniqueStr().equals(vo.getUniqueStr())) return false;
        return true;
    }
        
}
