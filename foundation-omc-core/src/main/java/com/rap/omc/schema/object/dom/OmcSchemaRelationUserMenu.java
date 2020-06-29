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
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.framework.exception.FoundationException;
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
@SuppressWarnings({ "rawtypes"})
public class OmcSchemaRelationUserMenu extends OmcSchemaRelation{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaRelationUserMenu.class);
    /**
     * @param vo
     */
    public OmcSchemaRelationUserMenu(OmcSchemaSysRootVO vo) {
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
        long schemaKind = Bit.or(OmcSystemConstants.SYSREL_KIND_Default,OmcSystemConstants.SYSREL_KIND_UserMenu);
        vo.setFlags(flags);
        vo.setSchemaKind(schemaKind);
        this.setVo(vo);
    }
    @Override
    protected void validateForCreate(Map map){
        LOGGER.debug("OmcSchemaRelationUserMenu.validateForCreate Start");
        super.validateForCreate(map);
        List<OmcSchemaRelationVO> relList = OmcSchemaServiceUtils.getRelationList(OmcSystemConstants.SYSREL_KIND_UserMenu, this.getVo().getFromObid(), this.getVo().getToObid());
        if(relList.size() > 0 ) throw new FoundationException("[Foundation]User has already authority.");
    }
}
