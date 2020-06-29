/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaRelationGroup2Group.java
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
 * Class : OmcSchemaRelationGroup2Group
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaRelationGroup2Group extends OmcSchemaRelation{

    /**
     * @param vo
     */
    public OmcSchemaRelationGroup2Group(OmcSchemaSysRootVO vo) {
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
        long schemaKind = Bit.or(OmcSystemConstants.SYSREL_KIND_Default,OmcSystemConstants.SYSREL_KIND_AssignGroup,OmcSystemConstants.SYSREL_KIND_GroupGroup);
        vo.setFlags(flags);
        vo.setSchemaKind(schemaKind);
        this.setVo(vo);
    }
    public static List<OmcSchemaRelationVO> getInactiveRelationList(){
        return(OmcSchemaServiceUtils.getInactiveUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_GroupGroup,OmcSystemConstants.SYSUSER_KIND_Group,OmcSystemConstants.SYSUSER_KIND_Group,"Group2Group"));
    }
    public static List<OmcSchemaRelationVO> getUploadRelationList(){
        return(OmcSchemaServiceUtils.getUploadUserCommonRelationList(OmcSystemConstants.SYSREL_KIND_GroupGroup,"Group2Group",OmcSystemConstants.SYSUSER_KIND_Group,OmcSystemConstants.SYSUSER_KIND_Group));
    }
}
