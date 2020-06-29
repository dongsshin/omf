/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaRelationLayout2Tab.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaRelationLayout2Tab
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaRelationLayout2Tab extends OmcSchemaRelation{

    /**
     * @param vo
     */
    public OmcSchemaRelationLayout2Tab(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setFlags()
     */
    @Override
    protected void setFlags(){
        // TODO Auto-generated method stub
        OmcSchemaRelationVO vo = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSREL_FLAG_Default,OmcSystemConstants.SYSREL_FLAG_Active);
        long schemaKind = Bit.or(OmcSystemConstants.SYSREL_KIND_Default,OmcSystemConstants.SYSREL_KIND_LayoutHasTab);
        vo.setFlags(flags);
        vo.setSchemaKind(schemaKind);
        this.setVo(vo);
    }
//    public static List<OmcSchemaRelationVO> getInactiveRelationList(){
//        return(OmcSchemaServiceUtils.getInactiveUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_RoleRole,OmcSystemConstants.SYSUSER_KIND_Role,OmcSystemConstants.SYSUSER_KIND_Role,"Group2User"));
//    }
//    public static List<OmcSchemaRelationVO> getUploadRelationList(){
//        return(OmcSchemaServiceUtils.getUploadUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_RoleRole,"Group2User"));
//    }
}
