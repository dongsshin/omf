/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaProperty.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.object.model.OmcSchemaPropertyVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;



/**
 * <pre>
 * Class : OmcSchemaProperty
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaProperty extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObject.class);
    /**
     * @param vo
     */
    public OmcSchemaProperty(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    @Override
    public OmcSchemaPropertyVO getVo(){
        return (OmcSchemaPropertyVO)vo;
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create()
     */
    @Override
    protected void create(@SuppressWarnings("rawtypes") Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.createSystemProperty(this.getVo());
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate()
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete()
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify()
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
    }
    /**
     * 
     * @param Names
     * @return
     * @see omc.schema.object.dom.OmcSchemaSysRoot#getObjectInfoByName(java.lang.String)
     */
    @Override
    protected OmcSchemaSysRootVO getObjectInfoByName(String Names){
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setFlags()
     */
    @Override
    protected void setFlags(){
        OmcSchemaPropertyVO thisVO = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSPTY_FLAG_Default,OmcSystemConstants.SYSPTY_FLAG_Active);
        thisVO.setFlags(flags);
        this.setVo(thisVO);
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        OmcSchemaPropertyVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_Property);
            this.setVo(thisVO);
        
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append(                                      "select a.obid            obid               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags          flags              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psys_object     sys_object         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pkinds          kinds              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pproperty_name  property_name      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pproperty_value property_value     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator        creator            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated        created            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier       modifier           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified       modified           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysproperty a                         ");
    }
}
