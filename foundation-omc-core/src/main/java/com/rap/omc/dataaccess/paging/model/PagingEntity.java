package com.rap.omc.dataaccess.paging.model;

import java.util.HashMap;

import com.rap.omc.dataaccess.paging.policy.DefaultPagingPolicy;

public class PagingEntity {
	
    private int targetRow = 1;
    private String orderBy = "";
    private int defaultRowSize = 0;
    private DefaultPagingPolicy pagingConfig = new DefaultPagingPolicy();
    HashMap<String, Object> pagingParameter;
    public int getTargetRow()
    {
        return this.targetRow;
    }
    public void setTargetRow(int targetRow)
    {
        this.targetRow = targetRow;
    }
    public String getOrderBy()
    {
        return this.orderBy;
    }
    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }
    public int getDefaultRowSize()
    {
        return this.defaultRowSize;
    }
    public void setDefaultRowSize(int defaultRowSize)
    {
        this.defaultRowSize = defaultRowSize;
    }
    public int getRowSize()
    {
        return this.pagingConfig.getRowSize();
    }
    public void setRowSize(int rowSize)
    {
        this.pagingConfig.setRowSize(rowSize);
    }
    public int getPageSize()
    {
        return this.pagingConfig.getPageSize();
    }
    public void setPageSize(int pageSize)
    {
        this.pagingConfig.setPageSize(pageSize);
    }
    public int[] getCustomRowSize()
    {
        return this.pagingConfig.getCustomRowSize();
    }
    public void setCustomRowSize(int[] customRowSize)
    {
        this.pagingConfig.setCustomRowSize(customRowSize);
    }
}