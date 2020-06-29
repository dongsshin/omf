/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysTimeZone.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.Map;

import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.object.model.OmcSchemaSysTimeZoneVO;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaSysTimeZone
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSysTimeZone extends OmcSchemaSysRoot{
     
    /**
     * @param vo
     */
    public OmcSchemaSysTimeZone(OmcSchemaSysRootVO vo) {
        super(vo);
    }
    public OmcSchemaSysTimeZoneVO getVo(){
        return (OmcSchemaSysTimeZoneVO)this.getVo();
    }
    public OmcSchemaSysTimeZone(String obid) {
        super(OmcSchemaServiceUtils.getSystemTimeZoneWithObid(obid));
    }
    public void create(Map map){
        OmcSchemaServiceUtils.createSystemTimeZone((OmcSchemaSysTimeZoneVO)this.getVo());
    }
    public void delete(Map map){
        OmcSchemaServiceUtils.deleteSystemTimeZone((OmcSchemaSysTimeZoneVO)this.getVo());
    }
    public void modify(Map map){
        OmcSchemaServiceUtils.modifySystemTimeZone((OmcSchemaSysTimeZoneVO)this.getVo());
    }
    public void inActiviate(Map map){
        OmcSchemaServiceUtils.inactivateSystemTimeZone((OmcSchemaSysTimeZoneVO)this.getVo());
    }
    public OmcSchemaSysRootVO getObjectInfoByName(String Names){
        return(OmcSchemaServiceUtils.getSystemTimeZoneWithObid(Names));
    }
    protected void setFlags(){
        OmcSchemaSysTimeZoneVO thisVO = this.getVo();
        long flags = OmcSystemConstants.TIMEZONE_FLAG_Default | OmcSystemConstants.TIMEZONE_FLAG_Active;
        thisVO.setFlags(flags);
        this.setVo(thisVO);
    }
    protected void setClassKind(){
        OmcSchemaSysTimeZoneVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_TimeZone);
        this.setVo(thisVO);
    }
}
