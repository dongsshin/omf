/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaTriggerParameter.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 24.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.object.model.OmcSchemaTriggerParameterVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaTriggerParameter
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OmcSchemaTriggerParameter extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaTriggerParameter.class);
    /**
     * @param vo
     */
    @Override
    public OmcSchemaTriggerParameterVO getVo(){
        return (OmcSchemaTriggerParameterVO)vo;
    }
    public OmcSchemaTriggerParameter(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    public OmcSchemaTriggerParameter(String obid) {
        super(OmcSchemaServiceUtils.getSystemTriggerParameterWithObid(obid));
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        OmcSchemaServiceUtils.createSystemTriggerParameter(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactivateSystemTriggerParameter(this.getVo());
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSystemTriggerParameter(this.getVo());
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySystemTriggerParameter(this.getVo());
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
        OmcSchemaTriggerParameterVO vo = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSTRIGGERPGM_FLAG_Default,OmcSystemConstants.SYSTRIGGERPGM_FLAG_Active);
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
        OmcSchemaTriggerParameterVO vo = this.getVo();
        vo.setClassKinds(OmcSystemConstants.SYSKEY_KIND_ProgramParm);
        this.setVo(vo);
    }
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.psequences            as sequences          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pchange_comments      as change_comments    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pnames                as names              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pprogram_name         as program_name       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmethod_name          as method_name        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pprogram_constructor  as program_constructor");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcalled_sequence      as called_sequence    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.perror_type           as error_type         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.ptarget_states        as target_states      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument01           as argument01         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument02           as argument02         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument03           as argument03         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument04           as argument04         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument05           as argument05         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument06           as argument06         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument07           as argument07         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument08           as argument08         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument09           as argument09         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument10           as argument10         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc01      as argument_desc01    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc02      as argument_desc02    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc03      as argument_desc03    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc04      as argument_desc04    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc05      as argument_desc05    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc06      as argument_desc06    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc07      as argument_desc07    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc08      as argument_desc08    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc09      as argument_desc09    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc10      as argument_desc10    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pparm_description     as parm_description   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,(select x.obid from  psystriggerparameter x where a.pnames  = x.pnames and a.pprogram_name = x.pprogram_name and a.pmethod_name = x.pmethod_name and a.ptarget_states = x.ptarget_states)  as obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsystriggerparameter a");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid                as obid              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pflags              as flags             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pnames              as names             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pparm_description   as parm_description  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pprogram_name       as program_name      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcalled_sequence    as called_sequence   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmethod_name        as method_name       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.ptarget_states      as target_states     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.perror_type         as error_type        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument01         as argument01        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument02         as argument02        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument03         as argument03        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument04         as argument04        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument05         as argument05        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument06         as argument06        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument07         as argument07        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument08         as argument08        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument09         as argument09        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument10         as argument10        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc01    as argument_desc01   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc02    as argument_desc02   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc03    as argument_desc03   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc04    as argument_desc04   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc05    as argument_desc05   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc06    as argument_desc06   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc07    as argument_desc07   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc08    as argument_desc08   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc09    as argument_desc09   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pargument_desc10    as argument_desc10   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreator            as creator           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreated            as created           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodifier           as modifier          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodified           as modified          ");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("from psystriggerparameter a"); 
    }
    public static void uploadTemporaryList(List<OmcSchemaTriggerParameterVO> list){
        OmcSchemaServiceUtils.uploadTemporaryTriggerParameter(list);
    }
    public static List<OmcSchemaTriggerParameterVO> getInactiveList(){
        return(OmcSchemaServiceUtils.getInactiveTriggerParameterList());
    }
    public static List<OmcSchemaTriggerParameterVO> getUploadList(){
        return(OmcSchemaServiceUtils.getUploadTriggerParameterList());
    }
}
