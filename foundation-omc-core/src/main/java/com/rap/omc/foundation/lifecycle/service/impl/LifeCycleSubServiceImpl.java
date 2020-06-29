/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : LifeCycleServiceImpl.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 2. 16. kwanghyui.choi Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.foundation.file.model.FcsLocationVO;
import com.rap.omc.foundation.lifecycle.model.BranchInfo;
import com.rap.omc.foundation.lifecycle.model.FormatInfo;
import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;
import com.rap.omc.foundation.lifecycle.model.StateInfo;
import com.rap.omc.foundation.lifecycle.model.StateTriggerInfo;
import com.rap.omc.foundation.lifecycle.service.LifeCycleSubService;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : LifeCycleSubServiceImpl
 * Description : TODO
 * </pre>
 * 
 */
@Service("lifeCycleSubService")
public class LifeCycleSubServiceImpl implements LifeCycleSubService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObject.class);
    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;

    @Cacheable(value = "lifeCycleInfoCache", key = "#lifeCycleName")
    public LifeCycleInfo getLifeCycleInfo(String lifeCycleName){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        
        sqlStrBuff.append("select                                 a.obid              as    obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pflags            as    flags");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pnames            as    life_cycle_name");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.psequence_rule    as    sequence_rule");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pdefault_format   as    default_format");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pdisplayed_name   as    displayed_name");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pdisplayed_name   as    displayed_name_kr");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psyslifecycle a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames = #{funVariable_00001}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00002}","#{funVariable_00002}"));
        variableParameter.setFunVariable_00001(lifeCycleName);
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSLCYCLE_FLAG_Active));
        
        variableParameter.setSql(sqlStrBuff.toString());
        LifeCycleInfo lifeCycle = schemaDao.select("LifeCycle.dynamicRetrieveApiLifeCycle", variableParameter);        
        if (!NullUtil.isNull(lifeCycle)){
            variableParameter = new OmcSQLVariableParameter(); 
            sqlStrBuff.setLength(0);
            sqlStrBuff.append("select                                 a.obid               as    obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pflags             as    flags");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pnames             as    state_name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.plife_cycle        as    life_cycle_obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{funVariable_00001} as    life_cycle_name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.psequence          as    sequence");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysstate a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.plife_cycle = #{funVariable_00002}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00003}","#{funVariable_00003}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("order by a.psequence");
            variableParameter.setFunVariable_00001(lifeCycleName);
            variableParameter.setFunVariable_00002(lifeCycle.getObid());
            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSSTATE_FLAG_Active));
            variableParameter.setSql(sqlStrBuff.toString());
            List<StateInfo> stateList = schemaDao.selectList("LifeCycle.dynamicRetrieveApiState", variableParameter);
            
            variableParameter = new OmcSQLVariableParameter(); 
            sqlStrBuff.setLength(0);
            sqlStrBuff.append("select a.obid            as obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pflags          as flags");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pnames          as branch_Name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pstates         as states");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pkinds          as kinds");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pto_object      as to_object");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pcondition_rule as condition_expression");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.pnames          as states_name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysbranch a, psysstate b, psyslifecycle c");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pstates     = b.obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.plife_cycle = c.obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.obid        = #{funVariable_00001}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00002}","#{funVariable_00002}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags","#{funVariable_00003}","#{funVariable_00003}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("c.pflags","#{funVariable_00004}","#{funVariable_00004}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("order by a.pnames");

            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSBRANCH_FLAG_Active));
            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSSTATE_FLAG_Active));
            variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSLCYCLE_FLAG_Active));
            for(StateInfo stateInfo : stateList){
                variableParameter.setFunVariable_00001(stateInfo.getObid());
                variableParameter.setSql(sqlStrBuff.toString());
                List<BranchInfo> branchInfoList = schemaDao.selectList("LifeCycle.dynamicRetrieveBranch", variableParameter);
                stateInfo.setBranchList(branchInfoList);
            }
            lifeCycle.setStateList(stateList);
            
            variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.setLength(0);
            sqlStrBuff.append("select c.obid            as   obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pflags            as flags");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pnames            as store_name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pfile_server      as server");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pfile_path        as path");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pftp_user         as ftp_user");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pftp_password     as ftp_password");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pserver_protocol  as protocol");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pserver_port      as port");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pservice_domain   as service_domain");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pservice_port     as service_port");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pservice_url      as service_url");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",'Store'             as store_type");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from  psyslifecycle a, psysrelationinfo b, psysfilestore c");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid     = b.pfrom_obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.pto_obid = c.obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.pto_obid = c.obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pschema_kind","#{funVariable_00001}","#{funVariable_00001}"));
            variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSREL_KIND_PolicyStore));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00002}","#{funVariable_00002}"));
            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSLIFEINFO_FLAG_Active));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags","#{funVariable_00003}","#{funVariable_00003}"));
            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSREL_FLAG_Active));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("c.pflags","#{funVariable_00004}","#{funVariable_00004}"));
            variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSFILESTORE_FLAG_Active));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.obid = #{funVariable_00005}");
            variableParameter.setFunVariable_00005(lifeCycle.getObid());
            variableParameter.setSql(sqlStrBuff.toString());
            
            FcsLocationVO currentFileStore = schemaDao.select("LifeCycle.dynamicRetrieveStore", variableParameter);
            lifeCycle.setCurrentFileStore(currentFileStore);
            
            variableParameter = new OmcSQLVariableParameter(); 
            sqlStrBuff.setLength(0);
            sqlStrBuff.append("select b.obid      as obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.pflags             as    flags");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.pnames             as names");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.pdisplayed_names   as displayed_name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.pdisplayed_names   as displayed_name_kr");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.pallowed_suffix    as allowed_suffix");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psyslifecycleinfo a, psysformat b");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = #{funVariable_00001}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_obid = b.obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds","#{funVariable_00002}","#{funVariable_00002}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00003}","#{funVariable_00003}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags","#{funVariable_00004}","#{funVariable_00004}"));
            variableParameter.setFunVariable_00001(lifeCycle.getObid());
            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSLIFEINFO_KIND_Format));
            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSLIFEINFO_FLAG_Active));
            variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSFORMAT_FLAG_Active));
            variableParameter.setSql(sqlStrBuff.toString());
            List<FormatInfo> fromatList = schemaDao.selectList("LifeCycle.dynamicRetrieveApiFormat", variableParameter);
            lifeCycle.setFormatList(fromatList);
            
            variableParameter = new OmcSQLVariableParameter(); 
            sqlStrBuff.setLength(0);
            sqlStrBuff.append("select b.pnames      as class_name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psyslifecycleinfo a, psysbizobjectclassinfo b");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = #{funVariable_00001}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_obid = b.obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds","#{funVariable_00002}","#{funVariable_00002}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00003}","#{funVariable_00003}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags","#{funVariable_00004}","#{funVariable_00004}"));
            variableParameter.setFunVariable_00001(lifeCycle.getObid());
            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSLIFEINFO_KIND_Class));
            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSLIFEINFO_FLAG_Active));
            variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
            variableParameter.setSql(sqlStrBuff.toString());
            List<String> classList = schemaDao.selectList("LifeCycle.dynamicRetrieveApiString", variableParameter);
            lifeCycle.setClassList(classList);

            variableParameter = new OmcSQLVariableParameter(); 
            sqlStrBuff.setLength(0);
            sqlStrBuff.append("                                select a.obid                        as obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.obid                        as obid_state");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.obid                        as obid_life_cycle");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pnames                      as life_cycle");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.pnames                      as states");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pcalled_sequences           as called_sequences");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",case when " + OmcSchemaUtil.getBitAndStr("a.pkinds","#{funVariable_00001}","#{funVariable_00001}",false) + " then 'Promote'");
            variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when " + OmcSchemaUtil.getBitAndStr("a.pkinds","#{funVariable_00002}","#{funVariable_00002}",false) + " then 'Demote'");
            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(" else 'N/A' end        trigger_kinds");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",case when " + OmcSchemaUtil.getBitAndStr("a.pprogram_kinds","#{funVariable_00003}","#{funVariable_00003}",false) + " then 'Check'");
            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSSTTRIG_PKIND_Check));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when " + OmcSchemaUtil.getBitAndStr("a.pprogram_kinds","#{funVariable_00004}","#{funVariable_00004}",false) + " then 'ActionPre'");
            variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSSTTRIG_PKIND_ActionPre));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when " + OmcSchemaUtil.getBitAndStr("a.pprogram_kinds","#{funVariable_00005}","#{funVariable_00005}",false) + " then 'Action'");
            variableParameter.setFunVariable_00005(String.valueOf(OmcSystemConstants.SYSSTTRIG_PKIND_Action));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when " + OmcSchemaUtil.getBitAndStr("a.pprogram_kinds","#{funVariable_00006}","#{funVariable_00006}",false) + " then 'ActionPost'");
            variableParameter.setFunVariable_00006(String.valueOf(OmcSystemConstants.SYSSTTRIG_PKIND_ActionPost));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(" else 'N/A' end                as program_kinds");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pnames                      as triggerparameter_name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pparm_description           as descriptions          ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pprogram_name               as program_name          ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pcalled_sequence            as execution_sequence    ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pmethod_name                as method_name           ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.ptarget_states              as target_state          ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.perror_type                 as return_error_type     ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument01                 as argument01            ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument02                 as argument02            ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument03                 as argument03            ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument04                 as argument04            ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument05                 as argument05            ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument06                 as argument06            ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument07                 as argument07            ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument08                 as argument08            ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument09                 as argument09            ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument10                 as argument10            ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument_desc01            as argument_desc01       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument_desc02            as argument_desc02       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument_desc03            as argument_desc03       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument_desc04            as argument_desc04       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument_desc05            as argument_desc05       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument_desc06            as argument_desc06       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument_desc07            as argument_desc07       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument_desc08            as argument_desc08       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument_desc09            as argument_desc09       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",d.pargument_desc10            as argument_desc10       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysstatetrigger a, psysstate b, psyslifecycle c, psystriggerparameter d");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pstates      = b.obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.plife_cycle  = c.obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.ptrigger_name     = d.pnames");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   c.obid         = #{funVariable_00007}");
            variableParameter.setFunVariable_00007(lifeCycle.getObid());
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("d.pflags","#{funVariable_00008}","#{funVariable_00008}"));
            variableParameter.setFunVariable_00008(String.valueOf(OmcSystemConstants.SYSTRIGGERPGM_FLAG_Active));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("c.pflags","#{funVariable_00009}","#{funVariable_00009}"));
            variableParameter.setFunVariable_00009(String.valueOf(OmcSystemConstants.SYSLCYCLE_FLAG_Active));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags","#{funVariable_00010}","#{funVariable_00010}"));
            variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.SYSSTATE_FLAG_Active));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00011}","#{funVariable_00011}"));
            variableParameter.setFunVariable_00011(String.valueOf(OmcSystemConstants.SYSSTATETRIGGER_FLAG_Active));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("order by b.psequence,a.pcalled_sequences,d.pcalled_sequence");
            variableParameter.setSql(sqlStrBuff.toString());
            List<StateTriggerInfo> stateTriggerList = schemaDao.selectList("LifeCycle.dynamicRetrieveStateTrigger", variableParameter);
            lifeCycle.setStateTriggerList(stateTriggerList);
        }
        return lifeCycle;
    }
}
