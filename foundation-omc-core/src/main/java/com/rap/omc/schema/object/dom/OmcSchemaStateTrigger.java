/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaStateTrigger.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 30.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.object.model.OmcSchemaStateTriggerVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaStateTrigger
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes")
public class OmcSchemaStateTrigger extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaStateTrigger.class);
    public OmcSchemaStateTrigger(String obid) {
        super(OmcSchemaServiceUtils.getSystemStateTriggerWithObid(obid));
    }
    @Override
    public OmcSchemaStateTriggerVO getVo(){
        return (OmcSchemaStateTriggerVO)vo;
    }
    /**
     * @param vo
     */
    public OmcSchemaStateTrigger(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
   
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.createSystemStateTrigger(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactivateSystemStateTrigger(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSystemStateTrigger(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySystemStateTrigger(this.getVo());
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
        OmcSchemaStateTriggerVO thisVO = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSSTATETRIGGER_FLAG_Default,OmcSystemConstants.SYSSTATETRIGGER_FLAG_Active);
        thisVO.setFlags(flags);
        this.setVo(thisVO);
    }

    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        // TODO Auto-generated method stub
        OmcSchemaStateTriggerVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_Trigger);
        this.setVo(thisVO);
    }
    @Override
    protected void validateForCreate(Map map){
        LOGGER.debug("OmcSchemaLifeCycle.validateForCreate Start");
        super.validateForCreate(map);
    }
    @Override
    protected void preProcessForCreate(Map map){
        super.preProcessForCreate(map);
    }
    @Override
    protected void postProcessForCreate(Map map){
        super.postProcessForCreate(map);
    }
    @Override
    protected void validateForInActiviate(Map map){
        super.validateForInActiviate(map);
    }
    @Override
    protected void preProcessForInActiviate(Map map){
        super.preProcessForInActiviate(map);
    }
    @Override
    protected void postProcessForInActiviate(Map map){
        super.postProcessForInActiviate(map);
    }
    @Override
    protected void validateForDelete(Map map){
        super.validateForDelete(map);
    }
    @Override
    protected void preProcessForDelete(Map map){
        super.preProcessForDelete(map);
    }
    @Override
    protected void postProcessForDelete(Map map){
        super.postProcessForDelete(map);
    }
    @Override
    protected void validateForModify(Map map){
        super.validateForModify(map);
        this.setFlags();
    }
    @Override
    protected void preProcessForModify(Map map){
        super.preProcessForModify(map);
    }
    @Override
    protected void postProcessForModify(Map map){
        super.postProcessForModify(map);
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid              as obid             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pflags            as flags            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pstates           as states           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pkinds            as kinds            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcalled_sequences as called_sequences ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pprogram_kinds    as program_kinds    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pprogram_name     as program_name     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.ptrigger_name     as trigger_name     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreator          as creator          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreated          as created          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodifier         as modifier         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodified         as modified         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysstatetrigger a");
    }
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append(OmcFoundationConstant.newline).append("select  a.psequences         as sequences           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.pchange_comments   as change_comments     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.ppolicy_name       as policy_name         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.pstate_name        as state_name          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.pstates            as states              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.pkinds_str         as kinds_str           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.pprogram_kinds_str as program_kinds_str   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.pcalled_sequences  as called_sequences    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.pprogram_name      as program_name        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.ptrigger_name      as trigger_name        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.pkinds             as kinds               ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,a.pprogram_kinds     as program_kinds       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("        ,(select x.obid from psysstatetrigger x where a.pstates = x.pstates and a.pkinds=  x.pkinds and a.pprogram_kinds = x.pprogram_kinds and a.pcalled_sequences = x.pcalled_sequences) as obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from (select a.psequences         as psequences           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,a.pchange_comments   as pchange_comments     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,a.ppolicy_name       as ppolicy_name         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,a.pstate_name        as pstate_name          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,b.obid               as pstates              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,a.pkinds             as pkinds_str           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,a.pprogram_kinds     as pprogram_kinds_str   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,a.pcalled_sequences  as pcalled_sequences    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,a.pprogram_name      as pprogram_name        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,a.ptrigger_name      as ptrigger_name        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,case when a.pkinds = 'Promote' then " + Bit.or(OmcSystemConstants.SYSSTATETRIGGER_KIND_Default,OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                  when a.pkinds = 'Demote'  then " + Bit.or(OmcSystemConstants.SYSSTATETRIGGER_KIND_Default,OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("             else 0  end          as pkinds               ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("            ,case when a.pprogram_kinds = 'Check'      then " + Bit.or(OmcSystemConstants.SYSSTTRIG_PKIND_Default,OmcSystemConstants.SYSSTTRIG_PKIND_Check));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                  when a.pprogram_kinds = 'ActionPre'  then " + Bit.or(OmcSystemConstants.SYSSTTRIG_PKIND_Default,OmcSystemConstants.SYSSTTRIG_PKIND_ActionPre));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                  when a.pprogram_kinds = 'Action'     then " + Bit.or(OmcSystemConstants.SYSSTTRIG_PKIND_Default,OmcSystemConstants.SYSSTTRIG_PKIND_Action));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                  when a.pprogram_kinds = 'ActionPost' then " + Bit.or(OmcSystemConstants.SYSSTTRIG_PKIND_Default,OmcSystemConstants.SYSSTTRIG_PKIND_ActionPost));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("             else 0  end          as pprogram_kinds       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("     from zsysstatetrigger a, psysstate b, psyslifecycle c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("     where a.pstate_name     = b.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("     and   a.ppolicy_name    = c.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("     and   b.plife_cycle     = c.obid) a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where exists(select * from psystriggerparameter x where a.ptrigger_name = x.pnames)");
    }
    @SuppressWarnings("unused")
    private static String getObidStr(String kindStr, String progrmKindStr, long kinds, long programKinds){
        return("(select z.obid from psyslifecycle x, psysstate y, psysstatetrigger z " + 
               "where z.pcalled_sequences = a.pcalled_sequences and y.pnames = a.pstate_name and x.pnames = a.ppolicy_name and x.obid = y.plife_cycle and y.obid = z.pstates and z.pkinds  = '" + kindStr + "' and z.pprogram_kinds = '" + progrmKindStr + "'" + 
               " " + OmcSchemaUtil.getBitAndStr("z.pkinds", kinds, kinds) +
               " " + OmcSchemaUtil.getBitAndStr("z.pprogram_kinds", programKinds, programKinds)) + ")";
    }
    public static List<OmcSchemaStateTriggerVO> getInactiveList(){
        return(OmcSchemaServiceUtils.getInactiveStateTriggerList());
    }
    public static List<OmcSchemaStateTriggerVO> getUploadList(){
        return(OmcSchemaServiceUtils.getUploadStateTriggerList());
    }
    
}
