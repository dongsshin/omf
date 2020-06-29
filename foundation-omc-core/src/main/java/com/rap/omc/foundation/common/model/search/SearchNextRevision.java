/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SearchNextRevision.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 2. 11. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.common.model.search;

/**
 * <pre>
 * Class : SearchNextRevision
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class SearchNextRevision {

    private String policy;

    private String revision;

    public SearchNextRevision() {
        super();
    }

    /**
     * @param policy
     * @param revision
     */
    public SearchNextRevision(String policy, String revision) {
        super();
        this.policy = policy;
        this.revision = revision;
    }

    /**
     * 
     * 
     * @return the policy
     */
    public String getPolicy(){
        return policy;
    }

    /**
     * 
     * 
     * @param policy the policy to set
     */
    public void setPolicy(String policy){
        this.policy = policy;
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

}
