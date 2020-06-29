package com.rap.omc.dataaccess.mybatis.interceptor;

import java.util.Map;

public class TraceContext
{
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static ThreadLocal<Map<String, String>> local = new ThreadLocal();
}
