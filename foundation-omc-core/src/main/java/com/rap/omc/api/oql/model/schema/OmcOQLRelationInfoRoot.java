/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLRelationInfoRoot.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 8. 11.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model.schema;

import com.rap.omc.schema.util.OmcSystemConstants;

/**
 * <pre>
 * Class : OmcOQLRelationInfoRoot
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLRelationInfoRoot {
    private int flags;
    private String relatedClass;
    
    /**
     * @param flags
     * @param relatedClass
     */
    public OmcOQLRelationInfoRoot(int flags, String relatedClass) {
        super();
        this.flags = flags;
        this.relatedClass = relatedClass;
    }

    /**
     * 
     */
    public OmcOQLRelationInfoRoot() {
        super();
    }
    public boolean isFrom() {
        if((this.getFlags() & OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM) == OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM) return(true);
        return(false);
    }
    public boolean isTo() {
        if((this.getFlags() & OmcSystemConstants.FLAG_ALLOWEDRELATION_TO) == OmcSystemConstants.FLAG_ALLOWEDRELATION_TO) return(true);
        return(false);
    }
    public boolean isBO() {
        if((this.getFlags() & OmcSystemConstants.FLAG_ALLOWEDRELATION_BO) == OmcSystemConstants.FLAG_ALLOWEDRELATION_BO) return(true);
        return(false);
    }
    public boolean isRO() {
        if((this.getFlags() & OmcSystemConstants.FLAG_ALLOWEDRELATION_RO) == OmcSystemConstants.FLAG_ALLOWEDRELATION_RO) return(true);
        return(false);
    }
    public int getFlags(){
        return flags;
    }
    
    public String getRelatedClass(){
        return relatedClass;
    }
    
    public void setFlags(int flags){
        this.flags = flags;
    }
    
    public void setRelatedClass(String relatedClass){
        this.relatedClass = relatedClass;
    }
    
}
