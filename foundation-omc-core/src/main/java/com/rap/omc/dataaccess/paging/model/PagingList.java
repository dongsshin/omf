package com.rap.omc.dataaccess.paging.model;

import java.util.ArrayList;

import com.rap.omc.dataaccess.paging.PagingHelper;

@SuppressWarnings("serial")
public class PagingList<E> extends ArrayList<E>{

    private PagingEntity pagingEntity = new PagingEntity();
    private int currentPage = 1;
    private int rows = 0;
    public int getTargetRow()
    {
        return this.pagingEntity.getTargetRow();
    }


    public void setTargetRow(int targetRow)
    {
        this.pagingEntity.setTargetRow(targetRow);
    }


    public String getOrderBy()
    {
        return this.pagingEntity.getOrderBy();
    }


    public void setOrderBy(String orderBy)
    {
        this.pagingEntity.setOrderBy(orderBy);
    }


    public int getDefaultRowSize()
    {
        return this.pagingEntity.getDefaultRowSize();
    }


    public void setDefaultRowSize(int defaultRowSize)
    {
        this.pagingEntity.setDefaultRowSize(defaultRowSize);
    }


    public int getRowSize()
    {
        return this.pagingEntity.getRowSize();
    }


    public void setRowSize(int rowSize)
    {
        this.pagingEntity.setRowSize(rowSize);
    }


    public int getPageSize()
    {
        return this.pagingEntity.getPageSize();
    }


    public void setPageSize(int pageSize)
    {
        this.pagingEntity.setPageSize(pageSize);
    }


    public int[] getCustomRowSize()
    {
        return this.pagingEntity.getCustomRowSize();
    }


    public void setCustomRowSize(int[] customRowSize)
    {
        this.pagingEntity.setCustomRowSize(customRowSize);
    }


    public int getCurrentPage()
    {
        return this.currentPage;
    }


    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }


    public void setCurrentPage(int targetRow, int rowSize)
    {
        setCurrentPage(PagingHelper.getPageOfRow(targetRow, rowSize));
    }


    public int getRows()
    {
        return this.rows;
    }


    public void setRows(int rows)
    {
        this.rows = rows;
    }


    public int getPages()
    {
        return PagingHelper.getPageOfRow(getRows(), getRowSize());
    }


    public int getLastRow()
    {
        return (getTargetRow() + getRowSize() - 1);
    }


    public String toString(){
        return super.toString();
    }
 }