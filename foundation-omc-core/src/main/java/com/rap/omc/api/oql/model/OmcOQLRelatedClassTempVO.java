package com.rap.omc.api.oql.model;

import java.util.Set;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLRelatedClassTempVO extends OmcOQLRoot{
    private String      relattion;
    private String      relattionTableName;
    private String      isTagetClassFrom;
    private String      targetClass;
    private String      targetTableName;
    

    public OmcOQLRelatedClassTempVO(String relattion, String relattionTableName, String isTagetClassFrom, String targetClass,
            String targetTableName) {
        super();
        this.relattion = relattion;
        this.relattionTableName = relattionTableName;
        this.isTagetClassFrom = isTagetClassFrom;
        this.targetClass = targetClass;
        this.targetTableName = targetTableName;
    }


    public String getRelattion(){
        return relattion;
    }

    
    public void setRelattion(String relattion){
        this.relattion = relattion;
    }

    
    public String getTargetClass(){
        return targetClass;
    }

    
    public void setTargetClass(String targetClass){
        this.targetClass = targetClass;
    }

    public String getRelattionTableName(){
        return relattionTableName;
    }
    
    public void setRelattionTableName(String relattionTableName){
        this.relattionTableName = relattionTableName;
    }
    
    
    
    public String getIsTagetClassFrom(){
        return isTagetClassFrom;
    }


    
    public void setIsTagetClassFrom(String isTagetClassFrom){
        this.isTagetClassFrom = isTagetClassFrom;
    }


    public String getTargetTableName(){
        return targetTableName;
    }
    
    public void setTargetTableName(String targetTableName){
        this.targetTableName = targetTableName;
    }

    
}
