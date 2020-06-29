package com.rap.omc.dataaccess.paging.policy;

public class DefaultPagingPolicy{

    private int rowSize = 10;
    private int pageSize = 10;
    private int[] customRowSize = { 10, 20, 50 };
    public int getRowSize()
    {
        return this.rowSize;
    }
    public void setRowSize(int rowSize)
    {
        this.rowSize = rowSize;
    }
    public int[] getCustomRowSize()
    {
        return this.customRowSize;
    }
    public void setCustomRowSize(int[] customRowSize)
    {
        this.customRowSize = customRowSize;
    }
    public int getPageSize()
    {
        return this.pageSize;
    }
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
}