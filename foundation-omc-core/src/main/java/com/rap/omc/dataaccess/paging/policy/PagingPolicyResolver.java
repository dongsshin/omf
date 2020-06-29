package com.rap.omc.dataaccess.paging.policy;

import java.util.HashMap;
import java.util.Map;

import com.rap.omc.framework.refresh.BeanRefreshSupport;


public class PagingPolicyResolver implements BeanRefreshSupport
{

    private Map<String, DefaultPagingPolicy> optionalPolicyMap = new HashMap();

    
    private DefaultPagingPolicy globalPolicy = new DefaultPagingPolicy();

    
    public Map<String, DefaultPagingPolicy> getOptionalPolicyMap()
    {
        return this.optionalPolicyMap;
    }

    
    public void setOptionalPolicyMap(Map<String, DefaultPagingPolicy> optionalPolicyMap)
    {
        this.optionalPolicyMap = optionalPolicyMap;
    }

    
    public DefaultPagingPolicy getGlobalPolicy()
    {
        return this.globalPolicy;
    }

    
    public void setGlobalPolicy(DefaultPagingPolicy globalPolicy)
    {
        this.globalPolicy = globalPolicy;
    }
}