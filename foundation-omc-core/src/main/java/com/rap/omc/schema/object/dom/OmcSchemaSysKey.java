/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysKey.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 31.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import com.rap.omc.schema.object.model.OmcSchemaSysKeyVO;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSchemaUtil;


/**
 * <pre>
 * Class : OmcSchemaSysKey
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSysKey {
    /**
     * @param vo
     */
    
    OmcSchemaSysKeyVO vo;
    public OmcSchemaSysKey(int kinds) {
        super();
        this.vo = new OmcSchemaSysKeyVO(kinds);
    }
    public OmcSchemaSysKey(String obid) {
        super();
        this.vo = OmcSchemaServiceUtils.getSystemKeyTableByObid(obid);
    }
    public OmcSchemaSysKeyVO getVo(){
        return vo;
    }
    protected void create(){
        this.validateForCreate();
        this.preProcessForCreate();
        OmcSchemaServiceUtils.createSystemKeyTable(this.vo);
    }
    private void validateForCreate(){
        if (this.vo.getKinds() == 0 || this.vo.getKinds() < 0) ;;
    }
    private void preProcessForCreate(){
        OmcSchemaSysKeyVO sysKeyVO = null;
        do{
            this.vo.setObid(OmcSchemaUtil.getObjectId());
            sysKeyVO = OmcSchemaServiceUtils.getSystemKeyTableByObid(this.getVo().getObid());
        }
        while(sysKeyVO == null);
    }
    private void postProcessForCreate(){
    }
}
