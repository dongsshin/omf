package com.rap.omc.foundation.menu.model;

import com.rap.omc.dataaccess.paging.model.PagingEntity;

public class CommonMenuSearchVO extends PagingEntity{
    private String  names;
    private Boolean menuInclude = true;
    private Boolean commandInclude= true;
    private Boolean toolbarInclude= true;
    private Boolean classMenuInclude= true;
    private Boolean comboInclude= false;
    private Boolean calendarInclude= false;
    private Boolean textInclude= false;
    private Boolean structureMenuInclude= true;
    private Boolean popupMenuMenuInclude= true;
    private Boolean classPopupMenuInclude= true;
    private Boolean checkBoxGroupInclude= false;
    private Boolean checkBoxInclude= false;
    private Boolean radioGroupInclude= false;
    private Boolean radioInclude= false;
    private Boolean filterGroupInclude= false;
    private Boolean filterInclude= false;
    private Boolean fabelInclude= false;
    private Boolean activeOnly = true;
    private Boolean isAccessControlObjectOnly = false;
    
    public Boolean getIsAccessControlObjectOnly(){
        return isAccessControlObjectOnly;
    }
    
    public void setIsAccessControlObjectOnly(Boolean isAccessControlObjectOnly){
        this.isAccessControlObjectOnly = isAccessControlObjectOnly;
    }



    public Boolean getPopupMenuMenuInclude(){
        return popupMenuMenuInclude;
    }


    
    public void setPopupMenuMenuInclude(Boolean popupMenuMenuInclude){
        this.popupMenuMenuInclude = popupMenuMenuInclude;
    }


    public Boolean getToolbarInclude(){
        return toolbarInclude;
    }

    
    public void setToolbarInclude(Boolean toolbarInclude){
        this.toolbarInclude = toolbarInclude;
    }

    public String getNames(){
        return names;
    }
    
    public void setNames(String names){
        this.names = names;
    }
    
    public Boolean getMenuInclude(){
        return menuInclude;
    }
    
    public void setMenuInclude(Boolean menuInclude){
        this.menuInclude = menuInclude;
    }
    
    public Boolean getCommandInclude(){
        return commandInclude;
    }
    
    public void setCommandInclude(Boolean commandInclude){
        this.commandInclude = commandInclude;
    }
    
    public Boolean getClassMenuInclude(){
        return classMenuInclude;
    }
    
    public void setClassMenuInclude(Boolean classMenuInclude){
        this.classMenuInclude = classMenuInclude;
    }
    
    public Boolean getComboInclude(){
        return comboInclude;
    }
    
    public void setComboInclude(Boolean comboInclude){
        this.comboInclude = comboInclude;
    }
    
    public Boolean getCalendarInclude(){
        return calendarInclude;
    }
    
    public void setCalendarInclude(Boolean calendarInclude){
        this.calendarInclude = calendarInclude;
    }
    
    public Boolean getTextInclude(){
        return textInclude;
    }
    
    public void setTextInclude(Boolean textInclude){
        this.textInclude = textInclude;
    }
    
    public Boolean getStructureMenuInclude(){
        return structureMenuInclude;
    }
    
    public void setStructureMenuInclude(Boolean structureMenuInclude){
        this.structureMenuInclude = structureMenuInclude;
    }
    
    public Boolean getClassPopupMenuInclude(){
        return classPopupMenuInclude;
    }
    
    public void setClassPopupMenuInclude(Boolean classPopupMenuInclude){
        this.classPopupMenuInclude = classPopupMenuInclude;
    }
    
    public Boolean getCheckBoxGroupInclude(){
        return checkBoxGroupInclude;
    }
    
    public void setCheckBoxGroupInclude(Boolean checkBoxGroupInclude){
        this.checkBoxGroupInclude = checkBoxGroupInclude;
    }
    
    public Boolean getCheckBoxInclude(){
        return checkBoxInclude;
    }
    
    public void setCheckBoxInclude(Boolean checkBoxInclude){
        this.checkBoxInclude = checkBoxInclude;
    }
    
    public Boolean getRadioGroupInclude(){
        return radioGroupInclude;
    }
    
    public void setRadioGroupInclude(Boolean radioGroupInclude){
        this.radioGroupInclude = radioGroupInclude;
    }
    
    public Boolean getRadioInclude(){
        return radioInclude;
    }
    
    public void setRadioInclude(Boolean radioInclude){
        this.radioInclude = radioInclude;
    }
    
    public Boolean getFilterGroupInclude(){
        return filterGroupInclude;
    }
    
    public void setFilterGroupInclude(Boolean filterGroupInclude){
        this.filterGroupInclude = filterGroupInclude;
    }
    
    public Boolean getFilterInclude(){
        return filterInclude;
    }
    
    public void setFilterInclude(Boolean filterInclude){
        this.filterInclude = filterInclude;
    }
    
    public Boolean getFabelInclude(){
        return fabelInclude;
    }
    
    public void setFabelInclude(Boolean fabelInclude){
        this.fabelInclude = fabelInclude;
    }
    
    public Boolean getActiveOnly(){
        return activeOnly;
    }
    
    public void setActiveOnly(Boolean activeOnly){
        this.activeOnly = activeOnly;
    }
    
    
}
