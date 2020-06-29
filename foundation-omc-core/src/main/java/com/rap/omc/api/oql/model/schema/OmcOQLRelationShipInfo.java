/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLRelationShipInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 8. 10.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model.schema;


/**
 * <pre>
 * Class : OmcOQLRelationShipInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLRelationShipInfo extends OmcOQLRelationInfoRoot{
    private String relationClass;
    
    /**
     * 
     */
    public OmcOQLRelationShipInfo() {
        super();
    }

    /**
     * @param flags
     * @param relatedClass
     * @param relationClass
     */
    public OmcOQLRelationShipInfo(int flags, String relatedClass, String relationClass) {
        super(flags, relatedClass);
        this.relationClass = relationClass;
    }

    
    public String getRelationClass(){
        return relationClass;
    }

    
    public void setRelationClass(String relationClass){
        this.relationClass = relationClass;
    }


}