package com.rap.omc.foundation.user.model;

import com.rap.omc.dataaccess.paging.model.PagingEntity;

public class CommonUserSearchVO extends PagingEntity{
    private String names;
    private Boolean userInclude = false;
    private Boolean roleInclude = false;
    private Boolean groupInclude = false;
    private Boolean roleGroupInclude = false;
    private Boolean activeOnly = true;
    
    public String getNames(){
        return names;
    }
    
    public void setNames(String names){
        this.names = names;
    }
    
    public Boolean getUserInclude(){
        return userInclude;
    }
    
    public void setUserInclude(Boolean userInclude){
        this.userInclude = userInclude;
    }
    
    public Boolean getRoleInclude(){
        return roleInclude;
    }
    
    public void setRoleInclude(Boolean roleInclude){
        this.roleInclude = roleInclude;
    }
    
    public Boolean getGroupInclude(){
        return groupInclude;
    }
    
    public void setGroupInclude(Boolean groupInclude){
        this.groupInclude = groupInclude;
    }
    
    public Boolean getRoleGroupInclude(){
        return roleGroupInclude;
    }
    
    public void setRoleGroupInclude(Boolean roleGroupInclude){
        this.roleGroupInclude = roleGroupInclude;
    }
    
    public Boolean getActiveOnly(){
        return activeOnly;
    }
    
    public void setActiveOnly(Boolean activeOnly){
        this.activeOnly = activeOnly;
    }
    
}
