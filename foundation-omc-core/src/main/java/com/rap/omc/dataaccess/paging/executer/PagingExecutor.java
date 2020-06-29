package com.rap.omc.dataaccess.paging.executer;

import java.util.List;

public abstract interface PagingExecutor {

    public abstract <E> List<E> execute(Object paramObject1, String paramString, Object paramObject2);

    public abstract <E> List<E> execute(Object paramObject1, String paramString, Object paramObject2, boolean paramBoolean);
}
