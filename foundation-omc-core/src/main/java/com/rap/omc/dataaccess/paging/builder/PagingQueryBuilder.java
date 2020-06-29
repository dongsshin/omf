package com.rap.omc.dataaccess.paging.builder;

public abstract interface PagingQueryBuilder<T> {
    public abstract String getCountSql(String paramString1, T paramT, String paramString2);
    public abstract String getPagingSql(String paramString1, T paramT, String paramString2);
}