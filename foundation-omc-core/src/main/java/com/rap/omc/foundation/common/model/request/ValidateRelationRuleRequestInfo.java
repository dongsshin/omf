/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ValidRequestInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 13. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.common.model.request;

/**
 * <pre>
 * Class : ValidRequestInfo
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class ValidateRelationRuleRequestInfo {

    private String relationship;

    private String fromObid;

    private String fromClass;

    private String toObid;

    private String toClass;

    private long flags;

    private String targetDbmsTable;

    private String strFromTo;

    public ValidateRelationRuleRequestInfo() {

    }

    /**
     * @param relationship
     * @param fromClass
     * @param toClass
     * @param flags
     */
    public ValidateRelationRuleRequestInfo(String relationship, String fromClass, String toClass, long flags) {
        super();
        this.relationship = relationship;
        this.fromClass = fromClass;
        this.toClass = toClass;
        this.flags = flags;
    }

    public ValidateRelationRuleRequestInfo(String relationship, String fromObid, String fromClass, String toObid,
            String toClass, long flags) {
        super();
        this.relationship = relationship;
        this.fromObid = fromObid;
        this.fromClass = fromClass;
        this.toObid = toObid;
        this.toClass = toClass;
        this.flags = flags;
    }


    /**
     * 
     * 
     * @return the relationship
     */
    public String getRelationship(){
        return relationship;
    }

    /**
     * 
     * 
     * @param relationship the relationship to set
     */
    public void setRelationship(String relationship){
        this.relationship = relationship;
    }

    /**
     * 
     * 
     * @return the fromObid
     */
    public String getFromObid(){
        return fromObid;
    }

    /**
     * 
     * 
     * @param fromObid the fromObid to set
     */
    public void setFromObid(String fromObid){
        this.fromObid = fromObid;
    }

    /**
     * 
     * 
     * @return the toObid
     */
    public String getToObid(){
        return toObid;
    }

    /**
     * 
     * 
     * @param toObid the toObid to set
     */
    public void setToObid(String toObid){
        this.toObid = toObid;
    }

    /**
     * 
     * 
     * @return the fromClass
     */
    public String getFromClass(){
        return fromClass;
    }

    /**
     * 
     * 
     * @param fromClass the fromClass to set
     */
    public void setFromClass(String fromClass){
        this.fromClass = fromClass;
    }

    /**
     * 
     * 
     * @return the toClass
     */
    public String getToClass(){
        return toClass;
    }

    /**
     * 
     * 
     * @param toClass the toClass to set
     */
    public void setToClass(String toClass){
        this.toClass = toClass;
    }

    /**
     * 
     * 
     * @return the flags
     */
    public long getFlags(){
        return flags;
    }

    /**
     * 
     * 
     * @param flags the flags to set
     */
    public void setFlags(long flags){
        this.flags = flags;
    }

    /**
     * 
     * 
     * @return the targetDbmsTable
     */
    public String getTargetDbmsTable(){
        return targetDbmsTable;
    }

    /**
     * 
     * 
     * @param targetDbmsTable the targetDbmsTable to set
     */
    public void setTargetDbmsTable(String targetDbmsTable){
        this.targetDbmsTable = targetDbmsTable;
    }

    /**
     * 
     * 
     * @return the strFromTo
     */
    public String getStrFromTo(){
        return strFromTo;
    }

    /**
     * 
     * 
     * @param strFromTo the strFromTo to set
     */
    public void setStrFromTo(String strFromTo){
        this.strFromTo = strFromTo;
    }

}
