package com.rap.sample.controller.service.model;

import java.io.Serializable;

public class DataData implements Serializable{
    private static final long serialVersionUID = 1L;
    private String sourceId;
    private String itemId;
    public DataData() {
		super();
	}
    public DataData(String sourceId, String itemId) {
		super();
		this.sourceId = sourceId;
		this.itemId = itemId;
	}
	public String getSourceId() {
        return sourceId;
    }
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    @Override
    public String toString() {
        return "DataType [sourceId=" + sourceId + ", itemId=" + itemId + "]";
    }
    
}
