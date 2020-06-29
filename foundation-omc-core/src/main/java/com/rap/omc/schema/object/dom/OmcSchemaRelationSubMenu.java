/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaRelationSubMenu.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 27.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;

import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.object.model.OmcSchemaTabLayoutVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaRelationSubMenu
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaRelationSubMenu extends OmcSchemaRelation{
    /**
     * @param vo
     */
    public OmcSchemaRelationSubMenu(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setFlags()
     */
    @Override
    protected void setFlags(){
        OmcSchemaRelationVO vo = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSREL_FLAG_Default,OmcSystemConstants.SYSREL_FLAG_Active);
        if("Y".equals(vo.getAttribute19())) flags = Bit.or(flags,OmcSystemConstants.SYSREL_FLAG_Hidden);
        if("Smart Client Function".equals(vo.getAttribute20())) flags = Bit.or(flags,OmcSystemConstants.SYSREL_FLAG_SCFunction);
        if("Java Script Function".equals(vo.getAttribute20())) flags = Bit.or(flags,OmcSystemConstants.SYSREL_FLAG_JSFunction);
        if("Contents Replace".equals(vo.getAttribute20())) flags = Bit.or(flags,OmcSystemConstants.SYSREL_FLAG_ContentsRepl);
        if("Smart Client Window Open".equals(vo.getAttribute20())) flags = Bit.or(flags,OmcSystemConstants.SYSREL_FLAG_SCWindow);
        if("Checkbox URL Call".equals(vo.getAttribute20())) flags = Bit.or(flags,OmcSystemConstants.SYSREL_FLAG_CheckGrpFunCall);
        if("Java Method Call".equals(vo.getAttribute20())) flags = Bit.or(flags,OmcSystemConstants.SYSREL_FLAG_CheckGrpMethod);
        if("PLSQL Function".equals(vo.getAttribute20())) flags = Bit.or(flags,OmcSystemConstants.SYSREL_FLAG_PLSQLFunction);
        long schemaKind = Bit.or(OmcSystemConstants.SYSREL_KIND_Default,OmcSystemConstants.SYSREL_KIND_SubMnuCmd);
        vo.setFlags(flags);
        vo.setSchemaKind(schemaKind);
        this.setVo(vo);
    }
    public static void uploadTemporaryList(List<OmcSchemaMenuVO> list){
        OmcSchemaServiceUtils.uploadTemporaryMenuList(list);
    }
    public static List<OmcSchemaMenuVO> getInactiveListForUpload(){
        return(OmcSchemaServiceUtils.getInactiveMenuListForUpload());
    }
}
