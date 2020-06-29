/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaAttribute.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 10.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.object.model.OmcSchemaAttributeVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaAttribute
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaAttribute extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaAttribute.class);
    public OmcSchemaAttribute(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    public OmcSchemaAttributeVO getVo(){
        return (OmcSchemaAttributeVO)vo;
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.createSchemaAttribute(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactiviateSchemaLifeCycleBranch(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSchemaLifeCycleBranch(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySchemaAttribute(this.getVo());
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
        // TODO Auto-generated method stub
        OmcSchemaAttributeVO vo = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSATTR_FLAG_Default,OmcSystemConstants.SYSATTR_FLAG_Active);
        vo.setFlags(flags);
        this.setVo(vo);
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        // TODO Auto-generated method stub
        OmcSchemaAttributeVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_Attr);
        this.setVo(thisVO);
    }
    public static void uploadTemporaryList(List<OmcSchemaAttributeVO> list){
        OmcSchemaServiceUtils.uploadTemporaryAttr(list);
    }
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.pnames                   as names              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_names         as displayed_names    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_names_kr      as displayed_names_kr ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodule_name             as module_name        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks                 as remarks            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences               as sequences          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments         as change_comments    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.obid from  psysattribute x where a.pnames  = x.pnames)  as obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysattribute a");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid                       as obid                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags                     as flags               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames                     as names               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_names           as displayed_names     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_names_kr        as displayed_names_kr  ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodule_name               as module_name         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.premarks                   as remarks             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences                 as sequences           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments           as change_comments     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator                   as creator             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated                   as created             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier                  as modifier            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified                  as modified            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysattribute a ");
    }
    public static List<OmcSchemaAttributeVO> getInactiveListForAttr(){
        return(OmcSchemaServiceUtils.getInActiveAttributeListForUpload());
    }
    public static List<OmcSchemaAttributeVO> getAllTemporayAttribute(){
        return(OmcSchemaServiceUtils.getAttributeListForUpload());
    }
    public static List<OmcSchemaAttributeVO> getAllAttribute(){
        return(OmcSchemaServiceUtils.getAllDefinedAttributeList());
    }
}
