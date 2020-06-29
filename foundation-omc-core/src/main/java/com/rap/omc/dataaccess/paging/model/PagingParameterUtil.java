package com.rap.omc.dataaccess.paging.model;

import java.util.HashMap;
public class PagingParameterUtil
{
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void setPagingParameter(PagingEntity pagingEntity, String name, Object value)
    {
        if (pagingEntity.pagingParameter == null) {
            pagingEntity.pagingParameter = new HashMap();
        }
        pagingEntity.pagingParameter.put(name, value);
        }
    public static Object getPagingParameter(PagingEntity pagingEntity, String name)
    {
        if (pagingEntity.pagingParameter == null) {
            return null;
        }
        return pagingEntity.pagingParameter.get(name);
    }
}