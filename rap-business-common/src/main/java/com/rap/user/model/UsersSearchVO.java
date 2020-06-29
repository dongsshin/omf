/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : UsersSearchVO.java
 * ===========================================
 * Modify Date Modifier Description
 * 2015. 4. 16. youngmi.won Initial
 * ===========================================
 */
package com.rap.user.model;

import rap.api.object.organization.model.UsersVO;

public class UsersSearchVO extends UsersVO {
    private String workGroup;
    private String workGroupName;
    private String targetPlant;
    private int rowSize;
    private int targetRow;
    private Boolean isExport;
    private Boolean isPaging;
    private Boolean isIncludeInactiveUser = false;

    public String getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(String workGroup) {
        this.workGroup = workGroup;
    }

    public String getWorkGroupName() {
        return workGroupName;
    }

    public void setWorkGroupName(String workGroupName) {
        this.workGroupName = workGroupName;
    }

    public String getTargetPlant() {
        return targetPlant;
    }

    public void setTargetPlant(String targetPlant) {
        this.targetPlant = targetPlant;
    }

    public int getRowSize() {
        return rowSize < 10 ? 20 : rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getTargetRow() {
        return targetRow;
    }

    public void setTargetRow(int targetRow) {
        this.targetRow = targetRow;
    }

    public Boolean getIsExport() {
        return isExport;
    }

    public void setIsExport(Boolean isExport) {
        this.isExport = isExport;
    }

    /**
     * 
     * 
     * @return the isPaging
     */
    public Boolean getIsPaging(){
        return isPaging;
    }

    /**
     * 
     * 
     * @param isPaging the isPaging to set
     */
    public void setIsPaging(Boolean isPaging){
        this.isPaging = isPaging;
    }

    /**
     * 
     * 
     * @return the isIncludeInactiveUser
     */
    public Boolean getIsIncludeInactiveUser(){
        return isIncludeInactiveUser;
    }

    /**
     * 
     * 
     * @param isIncludeInactiveUser the isIncludeInactiveUser to set
     */
    public void setIsIncludeInactiveUser(Boolean isIncludeInactiveUser){
        this.isIncludeInactiveUser = isIncludeInactiveUser;
    }
}
