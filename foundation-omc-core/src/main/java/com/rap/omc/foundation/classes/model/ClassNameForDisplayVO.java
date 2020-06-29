package com.rap.omc.foundation.classes.model;


public class ClassNameForDisplayVO {
    private String calssName;
    private String displayedName;
    private String displayedNameKr;
    private String defaultPolicy;
    
    
    public String getDisplayedNameKr(){
        return displayedNameKr;
    }

    
    public void setDisplayedNameKr(String displayedNameKr){
        this.displayedNameKr = displayedNameKr;
    }

    public String getCalssName(){
        return calssName;
    }
    
    public void setCalssName(String calssName){
        this.calssName = calssName;
    }
    
    public String getDisplayedName(){
        return displayedName;
    }
    
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }
    
    public String getDefaultPolicy(){
        return defaultPolicy;
    }
    
    public void setDefaultPolicy(String defaultPolicy){
        this.defaultPolicy = defaultPolicy;
    }
    
}
