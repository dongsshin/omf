/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ObjectRoot.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 5. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.model;

import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * Group By Pattern의 API사용시 사용되어지는 Return VO
 * <pre>
 * Class : GroupByResultVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("serial")
public class GroupByResultVO extends ObjectRootVO{
    
    private Long groupBycount = 0L;
    
    public Long getGroupBycount(){
        return groupBycount;
    }
    public void setGroupBycount(Long groupBycount){
        this.groupBycount = groupBycount;
    }
    public <T> T getBasisOnAttributeValue(String attribute){
        return getOutDataAttributeValue(getBasisAttributeName(attribute));
    }
    public <T> T getMaxAttributeValue(String attribute){
        return getOutDataAttributeValue(getMaxAttributeName(attribute));
    }
    public <T> T getMinAttributeValue(String attribute){
        return getOutDataAttributeValue(getMinAttributeName(attribute));
    }
    public <T> T getSumAttributeValue(String attribute){
        return getOutDataAttributeValue(getSumAttributeName(attribute));
    }
    public <T> T getAvgAttributeValue(String attribute){
        return getOutDataAttributeValue(getAvgAttributeName(attribute));
    }
    public static String getBasisAttributeName(String attribute){
        return OmcSystemConstants.QUERY_GROUPBY_omcGroupByBasisOnPrefix + attribute;
    }
    public static String getMaxAttributeName(String attribute){
        return OmcSystemConstants.QUERY_GROUPBY_omcGroupByMaxPrefix + attribute;
    }
    public static String getMinAttributeName(String attribute){
        return OmcSystemConstants.QUERY_GROUPBY_omcGroupByMinPrefix + attribute;
    }
    public static String getSumAttributeName(String attribute){
        return OmcSystemConstants.QUERY_GROUPBY_omcGroupBySumPrefix + attribute;
    }
    public static String getAvgAttributeName(String attribute){
        return OmcSystemConstants.QUERY_GROUPBY_omcGroupByAvgPrefix + attribute;
    }
    public static String getBasisAttributeNameForSort(String attribute){
        return OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX + getBasisAttributeName(attribute);
    }
    public static String getMaxAttributeNameForSort(String attribute){
        return OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX + getMaxAttributeName(attribute);
    }
    public static String getMinAttributeNameForSort(String attribute){
        return OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX + getMinAttributeName(attribute);
    }
    public static String getSumAttributeNameForSort(String attribute){
        return OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX + getSumAttributeName(attribute);
    }
    public static String getAvgAttributeNameForSort(String attribute){
        return OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX + getAvgAttributeName(attribute);
    }
}