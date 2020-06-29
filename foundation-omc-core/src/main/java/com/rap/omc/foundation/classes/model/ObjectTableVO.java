package com.rap.omc.foundation.classes.model;


public class ObjectTableVO {
    private String obid;
    private String dbmsTable;
    private int    isFrom;//1:From, 0:To
    private String className;
    
    
    public String getObid(){
        return obid;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public String getDbmsTable(){
        return dbmsTable;
    }
    
    public void setDbmsTable(String dbmsTable){
        this.dbmsTable = dbmsTable;
    }
    
    public int getIsFrom(){
        return isFrom;
    }
    
    public void setIsFrom(int isFrom){
        this.isFrom = isFrom;
    }

    
    public String getClassName(){
        return className;
    }

    
    public void setClassName(String className){
        this.className = className;
    }
    
}
