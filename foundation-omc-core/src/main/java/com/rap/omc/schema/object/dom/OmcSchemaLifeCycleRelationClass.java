/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaLifeCycleRelationClass.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 29.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import com.rap.omc.schema.object.model.OmcSchemaLifeCycleRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaLifeCycleRelationClass
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaLifeCycleRelationClass extends OmcSchemaLifeCycleRelation{
    /**
     * @param vo
     */
    public OmcSchemaLifeCycleRelationClass(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setFlags()
     */
    @Override
    protected void setClassKind(){
        super.setClassKind();
        OmcSchemaLifeCycleRelationVO thisVO = this.getVo();
        thisVO.setKinds(Bit.or(OmcSystemConstants.SYSLIFEINFO_KIND_Default, OmcSystemConstants.SYSLIFEINFO_KIND_Class));
        this.setVo(thisVO);
    }
}
