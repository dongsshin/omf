/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaStates.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 17.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleStateInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaStatesVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;
/**
 * <pre>
 * Class : OmcSchemaStates
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes") 
public class OmcSchemaStates extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaStates.class);
    public OmcSchemaStates(String obid) {
        super(OmcSchemaServiceUtils.getSystemStateWithObid(obid));
    }
    @Override
    public OmcSchemaStatesVO getVo(){
        return (OmcSchemaStatesVO)vo;
    }
    /**
     * @param vo
     */
    public OmcSchemaStates(OmcSchemaSysRootVO vo) {
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
        OmcSchemaServiceUtils.createSystemState(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactivateSystemState(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSystemState(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySystemState(this.getVo());
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
        OmcSchemaStatesVO thisVo = this.getVo();
        OmcSchemaLifeCycleStateInfoVO defVo = thisVo.getStateInfoVo();
        long flags = Bit.or(OmcSystemConstants.SYSSTATE_FLAG_Default,OmcSystemConstants.SYSSTATE_FLAG_Active);
        if(defVo != null){
            flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_STATE_Defined);
            if(defVo.getRouteCompleteAction().equals(OmcApplicationConstants.WF_RCA_PROMOTE_CONNECTED_OBJECT)) flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_RCA_Promote);
            if(defVo.getRouteCompleteAction().equals(OmcApplicationConstants.WF_RCA_NOTIFY_ROUTE_OWNER))       flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_RCA_RONotify);
            if(defVo.getRouteCompleteAction().equals(OmcApplicationConstants.WF_RCA_NONE))                     flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_RCA_None);
            if(defVo.getRouteCompleteAction().equals(OmcApplicationConstants.WF_RCA_NOTIFY_OBJECT_OWNER))      flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_RCA_OONotify);
            
            if(defVo.getDefaultRoutePurpose().equals(OmcApplicationConstants.WF_ROUTE_PURPOSE_Standard))                 flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_DRP_Standard);
            if(defVo.getDefaultRoutePurpose().equals(OmcApplicationConstants.WF_ROUTE_PURPOSE_Distribution))             flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_DRP_Dist);
            if(defVo.getDefaultRoutePurpose().equals(OmcApplicationConstants.WF_ROUTE_PURPOSE_Approval))                 flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_DRP_Approval);
            if(defVo.getDefaultRoutePurpose().equals(OmcApplicationConstants.WF_ROUTE_PURPOSE_Review))                   flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_DRP_Review);
            if(defVo.getDefaultRoutePurpose().equals(OmcApplicationConstants.WF_ROUTE_PURPOSE_Confirmation))             flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_DRP_Confirm);
            
            if(defVo.getRouteAutoStartOnReject().toUpperCase().equals(OmcApplicationConstants.WF_ROUTE_AUTO_START_ON_REJECT_TRUE))                  flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_RAS_True);
            if(defVo.getRouteAutoStartOnReject().toUpperCase().equals(OmcApplicationConstants.WF_ROUTE_AUTO_START_ON_REJECT_FALSE))                 flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_RAS_False);
            
            if(defVo.getRouteHowToOnReject().equals(OmcApplicationConstants.WF_ROUTE_HOW_TO_ON_REJECT_Immediate))                 flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_AS_Immediate);
            if(defVo.getRouteHowToOnReject().equals(OmcApplicationConstants.WF_ROUTE_HOW_TO_ON_REJECT_Defererred))                flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_AS_Defererred);
            
            if(defVo.getInboxTaskAutoComplete().toUpperCase().equals(OmcApplicationConstants.WF_INBOX_TASK_AUTO_COMPLETE_TRUE))                   flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_RAC_True);
            if(defVo.getInboxTaskAutoComplete().toUpperCase().equals(OmcApplicationConstants.WF_INBOX_TASK_AUTO_COMPLETE_FALSE))                  flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_RAC_False);
            
            if(defVo.getParallelProcessRule().equals(OmcApplicationConstants.WF_PARALLEL_PROCESS_RULE_All))                      flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_PPR_All);
            if(defVo.getParallelProcessRule().equals(OmcApplicationConstants.WF_PARALLEL_PROCESS_RULE_Any))                      flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_PPR_Any);
        }
        if(!StrUtil.isEmpty(thisVo.getUserInputed())){
            if("Y".equals(thisVo.getUserInputed())) flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_UserInput);
        }
        if(!StrUtil.isEmpty(thisVo.getUserInputMandantory())){
            if("Y".equals(thisVo.getUserInputMandantory())) flags = Bit.or(flags,OmcSystemConstants.SYSSTATE_FLAG_UserInputMust);
        }
        thisVo.setFlags(flags);
        this.setVo(thisVo);
    }

    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        // TODO Auto-generated method stub
        OmcSchemaStatesVO thisVo = this.getVo();
        thisVo.setClassKinds(OmcSystemConstants.SYSKEY_KIND_State);
        this.setVo(thisVo);
    }
    @Override
    protected void validateForCreate(Map map){
        LOGGER.debug("OmcSchemaSysBizClass.validateForCreate Start");
        super.validateForCreate(map);
        this.setStateInfoForCreateAndModify();
    }
    
    private void setStateInfoForCreateAndModify(){
        OmcSchemaStatesVO thisVo = this.getVo();
        OmcSchemaLifeCycleStateInfoVO defVo = OmcSchemaStates.getLifeCycleDefinitionForState(thisVo.getLifeCycleNames(),thisVo.getNames());
        if(defVo != null){
            if (!StrUtil.in(defVo.getRouteCompleteAction(), OmcApplicationConstants.WF_RCA_LSITS)){
                LOGGER.debug("OmcSchemaSysBizClass.validateForCreate Error");
                //error('Route Completion Action(' || rec.proute_complete_action || ') is in valid'); end if;
            }
            if (!StrUtil.in(defVo.getDefaultRoutePurpose(), OmcApplicationConstants.WF_ROUTE_PURPOSE_LISTS)){
                LOGGER.debug("OmcSchemaSysBizClass.validateForCreate Error");
                //error('Default Route Purpose(' || rec.pdefault_route_purpose || ') is in valid'); end if;
            }
            if (!StrUtil.in(defVo.getRouteAutoStartOnReject(), OmcApplicationConstants.WF_ROUTE_AUTO_START_ON_REJECT_LISTS)){
                LOGGER.debug("OmcSchemaSysBizClass.validateForCreate Error");
                //error('Route Auto Start(' || rec.proute_auto_start_on_reject || ') is in valid'); end if;
            }
            if (!StrUtil.in(defVo.getRouteHowToOnReject(), OmcApplicationConstants.WF_ROUTE_HOW_TO_ON_REJECT_LISTS)){
                LOGGER.debug("OmcSchemaSysBizClass.validateForCreate Error");
                //error('How to on reject(' || rec.proute_how_to_on_reject || ') is in valid'); end if;
            }
            if (!StrUtil.in(defVo.getInboxTaskAutoComplete(), OmcApplicationConstants.WF_INBOX_TASK_AUTO_COMPLETE_LISTS)){
                LOGGER.debug("OmcSchemaSysBizClass.validateForCreate Error");
                //error('How to on reject(' || rec.proute_how_to_on_reject || ') is in valid'); end if;
            }
            if (!StrUtil.in(defVo.getParallelProcessRule(),OmcApplicationConstants.WF_PARALLEL_PROCESS_RULE_LISTS)){
                LOGGER.debug("OmcSchemaSysBizClass.validateForCreate Error");
                //error('Parallel Process Rule(' || rec.pparallel_process_rule || ') is in valid'); end if;
            }
        }
        thisVo.setStateInfoVo(defVo);
    }
    @Override
    protected void preProcessForCreate(Map map){
        super.preProcessForCreate(map);
    }
    @Override
    protected void postProcessForCreate(Map map){
        super.postProcessForCreate(map);
        setProperties(map);
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
        this.setStateInfoForCreateAndModify();
    }
    @Override
    protected void preProcessForModify(Map map){
        super.preProcessForModify(map);
        setProperties(map);
    }
    @Override
    protected void postProcessForModify(Map map){
        super.postProcessForModify(map);
    }
    
    private void setProperties(Map map){
        String lifeCycle = this.getVo().getLifeCycleNames();
        lifeCycle = StrUtil.replaceAll(lifeCycle, "Policy", "").trim();
        lifeCycle = StrUtil.replaceAll(lifeCycle, " ", "_").toUpperCase();
        lifeCycle = lifeCycle + "_" + StrUtil.replaceAll(this.getVo().getNames()," ","");
        lifeCycle = "STATE_" + lifeCycle;
        OmcSchemaServiceUtils.setStateProperty(this.getVo(), lifeCycle, this.getVo().getNames());
    }
    
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append(                                      "select a.obid            as obid        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pflags          as flags       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pnames          as names       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plife_cycle     as life_cycle  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,(select x.pnames from psyslifecycle x where x.obid = a.plife_cycle)     as life_cycle_names  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.psequence       as sequence    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreator        as creator     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreated        as created     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodifier       as modifier    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodified       as modified    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysstate a");
    }
    public static OmcSchemaLifeCycleStateInfoVO getLifeCycleDefinitionForState(String lifeCycleNames, String stateNames){
        return(OmcSchemaServiceUtils.getLifeCycleDefinitionForState(lifeCycleNames,stateNames));
    }
}