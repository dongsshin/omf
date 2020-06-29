package com.rap.code.model;

public class CodeVO {

	private String id;

	private String scope;
	
	private String names;
	
	private String descriptions;
	
	private Boolean orderByDesc = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getOrderByDesc() {
        return orderByDesc;
    }

    public void setOrderByDesc(Boolean orderByDesc) {
        this.orderByDesc = orderByDesc;
    }

    @Override
	public String toString() {
		return "CodeVO [id=" + id + "]";
	}

}
