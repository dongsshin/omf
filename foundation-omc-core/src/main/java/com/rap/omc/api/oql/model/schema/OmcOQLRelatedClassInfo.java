/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLRelatedClassInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 8. 10.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model.schema;


/**
 * <pre>
 * Class : OmcOQLRelatedClassInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLRelatedClassInfo extends OmcOQLRelationInfoRoot{
    
    /**
     * 
     */
    public OmcOQLRelatedClassInfo() {
        super();
    }
    public OmcOQLRelatedClassInfo(int flags, String relatedClass) {
        super(flags,relatedClass);
    }
}
