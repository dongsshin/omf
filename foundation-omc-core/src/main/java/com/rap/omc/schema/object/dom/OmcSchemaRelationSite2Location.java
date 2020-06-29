/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaRelationSite2Location.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 4.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;

import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaRelationSite2Location
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaRelationSite2Location extends OmcSchemaRelation{

    /**
     * @param vo
     */
    public OmcSchemaRelationSite2Location(OmcSchemaSysRootVO vo) {
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
        long schemaKind = Bit.or(OmcSystemConstants.SYSREL_KIND_Default,OmcSystemConstants.SYSREL_KIND_SiteHasLoc);
        vo.setFlags(flags);
        vo.setSchemaKind(schemaKind);
        this.setVo(vo);
    }

    public static List<OmcSchemaRelationVO> getTemporaryUploadRelationList(){
        return(OmcSchemaServiceUtils.getTemporaryUploadSite2LocationRelationList());
    }
    public static List<OmcSchemaRelationVO> geInactiveRelationList(){
        return(OmcSchemaServiceUtils.getTemporaryInactiveSite2LocationRelationList());
    }
}
