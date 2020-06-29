/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : KeyInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 29. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.common.model;

/**
 * <pre>
 * Class : KeyInfo
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class KeyInfo {

    private String obid;

    private String className;

    private String objectType;

    private long flags;

    private String names;

    private String revision;

    private String fromClass;

    private String fromObid;

    private String toClass;

    private String toObid;

    /**
     * 
     * 
     * @return the obid
     */
    public String getObid(){
        return obid;
    }

    /**
     * 
     * 
     * @param obid the obid to set
     */
    public void setObid(String obid){
        this.obid = obid;
    }

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
     * @return the names
     */
    public String getNames(){
        return names;
    }

    /**
     * 
     * 
     * @param names the names to set
     */
    public void setNames(String names){
        this.names = names;
    }

    /**
     * 
     * 
     * @return the objectKind
     */
    public String getObjectType(){
        return objectType;
    }

    /**
     * 
     * 
     * @param objectKind the objectKind to set
     */
    public void setObjectType(String objectType){
        this.objectType = objectType;
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
     * @return the revision
     */
    public String getRevision(){
        return revision;
    }

    /**
     * 
     * 
     * @param revision the revision to set
     */
    public void setRevision(String revision){
        this.revision = revision;
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

}
