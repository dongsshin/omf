package com.rap.omc.api.oql.model;

import java.util.Set;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLRelatedClassVO extends OmcOQLRoot{
    private Set<String> relattionSet;
    private String      relattionTableName;
    private boolean     isFrom = false;
    private Set<String> targetClassSet;
    private String      targetTableName;
    
    public Set<String> getRelattionSet(){
        return relattionSet;
    }
    
    public void setRelattionSet(Set<String> relattionSet){
        this.relattionSet = relattionSet;
    }
    
    public String getRelattionTableName(){
        return relattionTableName;
    }
    
    public void setRelattionTableName(String relattionTableName){
        this.relattionTableName = relattionTableName;
    }
    
    public boolean isFrom(){
        return isFrom;
    }
    
    public void setFrom(boolean isFrom){
        this.isFrom = isFrom;
    }
    
    public Set<String> getTargetClassSet(){
        return targetClassSet;
    }
    
    public void setTargetClassSet(Set<String> targetClassSet){
        this.targetClassSet = targetClassSet;
    }
    
    public String getTargetTableName(){
        return targetTableName;
    }
    
    public void setTargetTableName(String targetTableName){
        this.targetTableName = targetTableName;
    }

    public OmcOQLRelatedClassVO(Set<String> relattionSet, String relattionTableName, String isFrom,
            Set<String> targetClassSet, String targetTableName) {
        super();
        this.relattionSet = relattionSet;
        this.relattionTableName = relattionTableName;
        if(isFrom.equals("Y")) this.isFrom = true; else this.isFrom = false;
        this.targetClassSet = targetClassSet;
        this.targetTableName = targetTableName;
    }
    
}
