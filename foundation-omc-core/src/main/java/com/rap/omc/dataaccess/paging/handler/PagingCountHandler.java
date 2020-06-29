package com.rap.omc.dataaccess.paging.handler;


import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

@SuppressWarnings("rawtypes")
public class PagingCountHandler implements ResultHandler
{
	private Integer totalCount;
	public void handleResult(ResultContext context)
	{
		this.totalCount = ((Integer)context.getResultObject());
	}
	public int getTotalCount()
	{
	return this.totalCount.intValue();
	}
}