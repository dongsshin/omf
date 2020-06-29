/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcPagingList.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 4. 20. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.model;

import java.util.ArrayList;

/**
 * <pre>
 * Class : OmcPagingList
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@SuppressWarnings("serial")
public class OmcPagingList<E> extends ArrayList<E> {

    /**
     * 전체 건수
     */
    private int totalCount = 0;

    private int targetRow = 1;

    private int rowSize = 0;

    private int currentPage = 1;

    /**
     * 
     * 
     * @return the totalCount
     */
    public int getTotalCount(){
        return totalCount;
    }

    /**
     * 
     * 
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }

    /**
     * 
     * 
     * @return the targetRow
     */
    public int getTargetRow(){
        return targetRow;
    }

    /**
     * 
     * 
     * @param targetRow the targetRow to set
     */
    public void setTargetRow(int targetRow){
        this.targetRow = targetRow;
    }

    /**
     * 
     * 
     * @return the rowSize
     */
    public int getRowSize(){
        return rowSize;
    }

    /**
     * 
     * 
     * @param rowSize the rowSize to set
     */
    public void setRowSize(int rowSize){
        this.rowSize = rowSize;
    }

    /**
     * 
     * 
     * @return the currentPage
     */
    public int getCurrentPage(){
        return currentPage;
    }

    /**
     * 
     * 
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage){
        this.currentPage = currentPage;
    }

    public void setCurrentPage(int targetRow, int rowSize){
        this.setCurrentPage((int)Math.ceil((float)targetRow / rowSize));
    }

}
