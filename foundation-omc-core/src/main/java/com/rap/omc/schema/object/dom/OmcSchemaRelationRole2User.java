/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaRelationRole2User.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 5.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;

import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaRelationRole2User
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaRelationRole2User  extends OmcSchemaRelation{

    /**
     * @param vo
     */
    public OmcSchemaRelationRole2User(OmcSchemaSysRootVO vo) {
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
        long schemaKind = Bit.or(OmcSystemConstants.SYSREL_KIND_Default,OmcSystemConstants.SYSREL_KIND_AssignGroup,OmcSystemConstants.SYSREL_KIND_RoleUser);
        vo.setFlags(flags);
        vo.setSchemaKind(schemaKind);
        this.setVo(vo);
    }
    public static List<OmcSchemaRelationVO> getInactiveRelationList(){
        return(OmcSchemaServiceUtils.getInactiveUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_RoleUser,OmcSystemConstants.SYSUSER_KIND_Role,OmcSystemConstants.SYSUSER_KIND_User,"Role2User"));
    }
    public static List<OmcSchemaRelationVO> getUploadRelationList(){
        return(OmcSchemaServiceUtils.getUploadUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_RoleUser,"Role2User",OmcSystemConstants.SYSUSER_KIND_Role,OmcSystemConstants.SYSUSER_KIND_User));
    }
}
