package com.rap.omc.dataaccess.paging.handler;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import com.rap.omc.dataaccess.paging.model.PagingList;

@SuppressWarnings({"rawtypes","unchecked"})
public class PagingResultHandler implements ResultHandler
{
	private final PagingList<Object> list;
	public PagingResultHandler()
    {
    	this.list = new PagingList();
    }
	public void handleResult(ResultContext context)
    {
    	this.list.add(context.getResultObject());
    }
    public PagingList<Object> getResultList()
    {
    	return this.list;
    }
}