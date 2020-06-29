/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SchemaSchemaKindVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.temp.model;


/**
 * <pre>
 * Class : SchemaSchemaKindVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class SchemaSchemaKindVO {
    private String  zschemaManagementType              ;
    private String  zschemaManagementDesc              ;
    public void setZschemaManagementType(String zschemaManagementType)
    {
        this.zschemaManagementType = zschemaManagementType;
    }
    public void setZschemaManagementDesc(String zschemaManagementDesc)
    {
        this.zschemaManagementDesc = zschemaManagementDesc;
    }
    public String getZschemaManagementType()
    {
        return this.zschemaManagementType;
    }
    public String getZschemaManagementDesc()
    {
        return this.zschemaManagementDesc;
    }
}
