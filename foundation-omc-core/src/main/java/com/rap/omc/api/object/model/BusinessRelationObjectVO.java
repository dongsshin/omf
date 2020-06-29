/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessRelationObject.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 24. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.model;

/**
 * <pre>
 * Class : BusinessRelationObject
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class BusinessRelationObjectVO extends ObjectRootVO {

    private String fromClass;

    private String fromObid;

    private String toClass;

    private String toObid;

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
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "BusinessRelationObject [fromClass=" + fromClass + ", fromObid=" + fromObid + ", toClass=" + toClass
                + ", toObid=" + toObid + "]";
    }

}
