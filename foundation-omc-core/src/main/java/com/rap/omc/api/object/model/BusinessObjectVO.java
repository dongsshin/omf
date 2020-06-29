/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessObject.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 23. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.model;

/**
 * <pre>
 * Class : BusinessObject
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class BusinessObjectVO extends BusinessObjectRootVO {

    private String revision;

    private String previousObid = "1";

    private String nextObid = "1";

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
     * @return the previousObid
     */
    public String getPreviousObid(){
        return previousObid;
    }

    /**
     * 
     * 
     * @param previousObid the previousObid to set
     */
    public void setPreviousObid(String previousObid){
        this.previousObid = previousObid;
    }

    /**
     * 
     * 
     * @return the nextObid
     */
    public String getNextObid(){
        return nextObid;
    }

    /**
     * 
     * 
     * @param nextObid the nextObid to set
     */
    public void setNextObid(String nextObid){
        this.nextObid = nextObid;
    }

}
