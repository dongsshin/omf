/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SearchTargetRequestInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.common.model.search;


/**
 * <pre>
 * Class : SearchTargetRequestInfo
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class SearchTargetRequestInfo {

    private String className;

    private String relationName;

    private String filter;

    private String fromTo;

    /**
     * 
     * 
     * @return the className
     */
    public String getClassName(){
        return className;
    }

    /**
     * 
     * 
     * @param className the className to set
     */
    public void setClassName(String className){
        this.className = className;
    }

    /**
     * 
     * 
     * @return the filter
     */
    public String getFilter(){
        return filter;
    }

    /**
     * 
     * 
     * @param filter the filter to set
     */
    public void setFilter(String filter){
        this.filter = filter;
    }

    /**
     * 
     * 
     * @return the fromTo
     */
    public String getFromTo(){
        return fromTo;
    }

    /**
     * 
     * 
     * @param fromTo the fromTo to set
     */
    public void setFromTo(String fromTo){
        this.fromTo = fromTo;
    }

    /**
     * 
     * 
     * @return the relationName
     */
    public String getRelationName(){
        return relationName;
    }

    /**
     * 
     * 
     * @param relationName the relationName to set
     */
    public void setRelationName(String relationName){
        this.relationName = relationName;
    }

}
