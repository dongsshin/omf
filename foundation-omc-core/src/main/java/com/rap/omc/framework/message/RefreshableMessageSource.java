package com.rap.omc.framework.message;

import org.springframework.beans.factory.InitializingBean;

public abstract interface RefreshableMessageSource extends InitializingBean {

    public static final String PRE_LOAD = "preLoad";

    public static final String LAZY_LOAD = "lazyLoad";

    public abstract void refresh();

    public abstract void refreshIncludingAncestors();

    public abstract void clearCache();

    public abstract void clearCacheIncludingAncestors();

    public abstract void setLoadType(String paramString);
}