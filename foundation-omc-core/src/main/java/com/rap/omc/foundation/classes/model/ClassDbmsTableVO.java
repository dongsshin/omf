package com.rap.omc.foundation.classes.model;

import com.rap.omc.foundation.common.model.ModelRootVO;

public class ClassDbmsTableVO extends ModelRootVO{
    private String className;
    private String dbmsTable;
    
    public String getClassName(){
        return className;
    }
    
    public void setClassName(String className){
        this.className = className;
    }
    
    public String getDbmsTable(){
        return dbmsTable;
    }
    
    public void setDbmsTable(String dbmsTable){
        this.dbmsTable = dbmsTable;
    }
    public boolean equals(ClassDbmsTableVO vo){
        if(vo.getClassName() == null && this.className == null && vo.getDbmsTable() == null && this.dbmsTable == null) return true; 
        try{
            if(this.className.equals(vo.getClassName()) && this.getDbmsTable().equals(vo.getDbmsTable())) return true;
        }
        catch (Exception e){
            return false;
        }
        return false;
    }
    
}
