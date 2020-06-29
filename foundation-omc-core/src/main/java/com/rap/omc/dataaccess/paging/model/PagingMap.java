package com.rap.omc.dataaccess.paging.model;

import java.util.HashMap;
import java.util.Map;

public class PagingMap extends PagingEntity
{
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, Object> map = new HashMap();
    public Map<String, Object> getMap(){
        return this.map;
    }
    public void setMap(Map<String, Object> map){
        this.map = map;
    }
}
