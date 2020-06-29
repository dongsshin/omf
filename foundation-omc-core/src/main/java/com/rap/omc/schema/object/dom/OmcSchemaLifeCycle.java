/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaLifeCycle.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 17.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.schema.object.model.OmcSchemaFileFormatVO;
import com.rap.omc.schema.object.model.OmcSchemaFileServerVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleStateInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleVO;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedFormatVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedStateVO;
import com.rap.omc.schema.object.model.OmcSchemaStateTriggerVO;
import com.rap.omc.schema.object.model.OmcSchemaStatesVO;
import com.rap.omc.schema.object.model.OmcSchemaSysBizClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;

/**
 * <pre>
 * Class : OmcSchemaLifeCycle
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes")
public class OmcSchemaLifeCycle extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaLifeCycle.class);
    /**
     * @param vo
     */
    @Override
    public OmcSchemaLifeCycleVO getVo(){
        return (OmcSchemaLifeCycleVO)vo;
    }
    public OmcSchemaLifeCycle(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    public OmcSchemaLifeCycle(String obid) {
        super(OmcSchemaServiceUtils.getSystemLifeCycleWithObid(obid));
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        OmcSchemaServiceUtils.createSystemLifeCycle(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactivateSystemLifeCycle(this.getVo());
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSystemLifeCycle(this.getVo());
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySystemLifeCycle(this.getVo());
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
        LOGGER.debug("OmcSchemaLifeCycle.postProcessForCreate Start");
        super.postProcessForCreate(map);
        
        this.postProcessForListData();
        this.setProperties(map);
    }

    private void postProcessForListData(){
        OmcSchemaLifeCycleVO thisVo = this.getVo();

        List<OmcSchemaSeperatedFormatVO> seperatedFormatList  = thisVo.getSeperatedFormatList();
        List<OmcSchemaSeperatedClassVO> seperatedClassList = thisVo.getSeperatedClassList();
        List<OmcSchemaSeperatedStateVO> seperatedStateList = thisVo.getSeperatedStateList();
        applyCurrentStore();
        this.applyStateList(thisVo,seperatedStateList);
        this.applyFormatRelationList(thisVo,seperatedFormatList);
        this.applyClassRelationList(thisVo,seperatedClassList);  
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
        this.postProcessForListData();
        this.setProperties(map);
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
    public void seperateListForUpload(){
        OmcSchemaLifeCycleVO thisVo = this.getVo();
        seperateStateInfo(thisVo);
        seperateFormatInfo(thisVo);
        seperateClassInfo(thisVo);
        this.vo = thisVo;
    }
    private void setProperties(Map map){
        String lifeCycle = this.getVo().getNames().toUpperCase();
        lifeCycle = StrUtil.replaceAll(lifeCycle, " POLICY", "");
        lifeCycle = StrUtil.replaceAll(lifeCycle, " ","_");
        OmcSchemaServiceUtils.setLifeCycleProperty(this.getVo(), "LIFECYCLE_" + lifeCycle, this.getVo().getNames());
    }
    private void  applyStateList(OmcSchemaLifeCycleVO thisVo, List<OmcSchemaSeperatedStateVO> seperatedStateList){
        List<OmcSchemaStatesVO> stateList = new ArrayList<OmcSchemaStatesVO>();
        for (OmcSchemaSeperatedStateVO vo : seperatedStateList){
            OmcSchemaStatesVO stateVO = new OmcSchemaStatesVO();
            stateVO.setNames(vo.getNames());
            stateVO.setSequence(vo.getSequences());
            stateVO.setLifeCycle(thisVo.getObid());
            stateVO.setLifeCycleNames(vo.getLifeCycle());
            stateVO.setUserInputed(vo.getUserInputed());
            stateVO.setUserInputMandantory(vo.getUserInputMandantory());
            stateList.add(stateVO);
        }
        List<OmcSchemaStatesVO> savedStateList = this.getSystemStateListWithLifeCycle(thisVo);
        OmcSchemaStates stateDom;
        for(OmcSchemaStatesVO savedVO : savedStateList){
            boolean isInactive = true;
            for(OmcSchemaStatesVO newVo : stateList){
                if(savedVO.getNames().equals(newVo.getNames()) && savedVO.getLifeCycle().equals(newVo.getLifeCycle())) {
                    isInactive = false;
                    savedVO.setSequence(newVo.getSequence());
                    savedVO.setUserInputed(newVo.getUserInputed());
                    savedVO.setUserInputMandantory(newVo.getUserInputMandantory());
                    savedVO.setLifeCycleNames(newVo.getLifeCycleNames());
                    stateDom = new OmcSchemaStates(savedVO);
                    Map<String, Object> map = new HashMap<String, Object>();
                    stateDom.modifyObject(map);
                }
            }
            if(isInactive) 
            {
                stateDom = new OmcSchemaStates(savedVO);
                Map<String, Object> map = new HashMap<String, Object>();
                stateDom.inActiviate(map);
            }
        }
        for(OmcSchemaStatesVO newVo : stateList){
            boolean isNotExists = true;
            for(OmcSchemaStatesVO savedVO : savedStateList){
                if(savedVO.getNames().equals(newVo.getNames()) && savedVO.getLifeCycle().equals(newVo.getLifeCycle())) {
                    isNotExists = false;break;
                }
            }
            if(isNotExists) 
            {
                stateDom = new OmcSchemaStates(newVo);
                Map<String, Object> map = new HashMap<String, Object>();
                stateDom.createObject(map);
            }
        }
    }
    private void  applyFormatRelationList(OmcSchemaLifeCycleVO thisVo, List<OmcSchemaSeperatedFormatVO> seperatedFormatList){
        List<OmcSchemaLifeCycleRelationVO> savedFormatList = this.getSystemLifeCycleRelListWithLifeCycle(thisVo,OmcSystemConstants.SYSLIFEINFO_KIND_Format);
        List<OmcSchemaFileFormatVO> newList = new ArrayList<OmcSchemaFileFormatVO>();
        if(!NullUtil.isNone(seperatedFormatList)) {
            newList = OmcSchemaFileFormat.getFileFormatListWithSeperatedList(seperatedFormatList);
//            for(OmcSchemaFileFormatVO vo : list){
//                OmcSchemaLifeCycleRelationVO relVo = new OmcSchemaLifeCycleRelationVO();
//                relVo.setFromObid(thisVo.getObid());
//                relVo.setToObid(vo.getObid());
//                relVo.setKinds(Bit.or(OmcSystemConstants.SYSLIFEINFO_KIND_Default, OmcSystemConstants.SYSLIFEINFO_KIND_Class));
//                OmcSchemaLifeCycleRelationFormat dom = new OmcSchemaLifeCycleRelationFormat(relVo);
//                Map<String, Object> map = new HashMap<String, Object>();
//                if(!dom.isExists()) dom.createObject(map);
//            }            
        }
        OmcSchemaLifeCycleRelationFormat dom;
        for(OmcSchemaLifeCycleRelationVO savedVO : savedFormatList){
            boolean isInactive = true;
            for(OmcSchemaFileFormatVO newVo : newList){
                if(savedVO.getToObid().equals(newVo.getObid())) {
                    isInactive = false;
                    if(!Bit.isInclude(savedVO.getFlags(), OmcSystemConstants.SYSLIFEINFO_FLAG_Active))
                    {
                        dom = new OmcSchemaLifeCycleRelationFormat(savedVO);
                        Map<String, Object> map = new HashMap<String, Object>();
                        dom.modifyObject(map);
                    }
                }
            }
            if(isInactive) 
            {
                dom = new OmcSchemaLifeCycleRelationFormat(savedVO);
                Map<String, Object> map = new HashMap<String, Object>();
                dom.inActiviate(map);
            }
        }
        for(OmcSchemaFileFormatVO newVo : newList){
            boolean isNotExists = true;
            for(OmcSchemaLifeCycleRelationVO savedVO : savedFormatList){
                if(savedVO.getToObid().equals(newVo.getObid())) {
                    isNotExists = false;break;
                }
            }
            if(isNotExists) 
            {
                OmcSchemaLifeCycleRelationVO relVo = new OmcSchemaLifeCycleRelationVO();
                relVo.setFromObid(thisVo.getObid());
                relVo.setToObid(newVo.getObid());
                dom = new OmcSchemaLifeCycleRelationFormat(relVo);
                Map<String, Object> map = new HashMap<String, Object>();
                dom.createObject(map);
            }
        }
    }
    private void  applyClassRelationList(OmcSchemaLifeCycleVO thisVo, List<OmcSchemaSeperatedClassVO> seperatedClassList){
        List<OmcSchemaLifeCycleRelationVO> savedClassList = this.getSystemLifeCycleRelListWithLifeCycle(thisVo,OmcSystemConstants.SYSLIFEINFO_KIND_Class);
        List<OmcSchemaSysBizClassVO> newList = new ArrayList<OmcSchemaSysBizClassVO>();
        
        if(!NullUtil.isNone(seperatedClassList)) {
            newList = OmcSchemaLifeCycle.getFileBizClassListWithSeperatedList(seperatedClassList);
//            for(OmcSchemaSysBizClassVO vo : list){
//                OmcSchemaLifeCycleRelationVO relVo = new OmcSchemaLifeCycleRelationVO();
//                relVo.setFromObid(thisVo.getObid());
//                relVo.setToObid(vo.getObid());
//                relVo.setKinds(Bit.or(OmcSystemConstants.SYSLIFEINFO_KIND_Default, OmcSystemConstants.SYSLIFEINFO_KIND_Format));
//                OmcSchemaLifeCycleRelationClass dom = new OmcSchemaLifeCycleRelationClass(relVo);
//                Map<String, Object> map = new HashMap<String, Object>();
//                if(!dom.isExists()) dom.createObject(map);
//            } 
        }
        OmcSchemaLifeCycleRelationClass dom;
        for(OmcSchemaLifeCycleRelationVO savedVO : savedClassList){
            boolean isInactive = true;
            for(OmcSchemaSysBizClassVO newVo : newList){
                if(savedVO.getToObid().equals(newVo.getObid())) {
                    isInactive = false;
                }else{
                    if(!Bit.isInclude(savedVO.getFlags(), OmcSystemConstants.SYSLIFEINFO_FLAG_Active))
                    {
                        dom = new OmcSchemaLifeCycleRelationClass(savedVO);
                        Map<String, Object> map = new HashMap<String, Object>();
                        dom.modifyObject(map);
                    }
                }
            }
            if(isInactive) 
            {
                dom = new OmcSchemaLifeCycleRelationClass(savedVO);
                Map<String, Object> map = new HashMap<String, Object>();
                dom.inActiviate(map);
            }
        }
        for(OmcSchemaSysBizClassVO newVo : newList){
            boolean isNotExists = true;
            for(OmcSchemaLifeCycleRelationVO savedVO : savedClassList){
                if(savedVO.getToObid().equals(newVo.getObid())) {
                    isNotExists = false;break;
                }
            }
            if(isNotExists) 
            {
                OmcSchemaLifeCycleRelationVO relVo = new OmcSchemaLifeCycleRelationVO();
                relVo.setFromObid(thisVo.getObid());
                relVo.setToObid(newVo.getObid());
                dom = new OmcSchemaLifeCycleRelationClass(relVo);
                Map<String, Object> map = new HashMap<String, Object>();
                dom.createObject(map);
            }
        }
    }
    private void  applyCurrentStore(){
        OmcSchemaLifeCycleVO thisVo = this.getVo();
        List<OmcSchemaRelationVO> relVoList = OmcSchemaRelationLifeCycle2Store.getLifeCycleStoreRelationList(thisVo.getNames(),thisVo.getStoreName());
        OmcSchemaFileServerVO fileServerVO = OmcSchemaServiceUtils.getStoreWithNames(thisVo.getStoreName());
        boolean isExist = false;
        if(!NullUtil.isNull(fileServerVO)){
            if(!NullUtil.isNone(relVoList)){
                for(OmcSchemaRelationVO relVo : relVoList){
                    if(relVo.getToObid().equals(fileServerVO.getObid())){
                        if(!Bit.isInclude(relVo.getFlags(),OmcSystemConstants.SYSREL_FLAG_Active)){
                            OmcSchemaRelationLifeCycle2Store dom = new OmcSchemaRelationLifeCycle2Store(relVo);
                            Map<String, Object> map = new HashMap<String, Object>();
                            dom.modifyObject(map);
                        }
                        isExist = true;
                    }else{
                        if(Bit.isInclude(relVo.getFlags(),OmcSystemConstants.SYSREL_FLAG_Active)){
                            OmcSchemaRelationLifeCycle2Store dom = new OmcSchemaRelationLifeCycle2Store(relVo);
                            Map<String, Object> map = new HashMap<String, Object>();
                            dom.inActiviateObject(map);
                        }
                    }
                }
            }
            if(!isExist){
                OmcSchemaRelationVO relVo = new OmcSchemaRelationVO();
                relVo.setFromObid(thisVo.getObid());
                relVo.setToObid(fileServerVO.getObid());
                relVo.setSorting(1);
                OmcSchemaRelationLifeCycle2Store dom = new OmcSchemaRelationLifeCycle2Store(relVo);
                Map<String, Object> map = new HashMap<String, Object>();
                dom.createObject(map);                
            }
        }
    }
    public static List<OmcSchemaSeperatedBranchVO>  seperateBranchInfo(){
        List<OmcSchemaLifeCycleBranchVO> list = OmcSchemaServiceUtils.getLifeCycleTempBranchList();
        List<OmcSchemaSeperatedBranchVO> branchList = new ArrayList<OmcSchemaSeperatedBranchVO>();
        String branchName = "";
        String branchFromTo = "";
        String filter = "";
        String branchFrom = "";
        String branchTo = "";
        for(OmcSchemaLifeCycleBranchVO vo : list){
            String[] str = vo.getBracnhInfo().split(Pattern.quote("}"));
            if(str.length == 3){
                branchName = str[0].substring(1);branchFromTo = str[1].substring(1);filter = str[2].substring(1);
                String[] fromTo = branchFromTo.split(Pattern.quote("."));
                branchFrom = fromTo[0];branchTo = fromTo[1];
                OmcSchemaSeperatedBranchVO branchVO1 = new OmcSchemaSeperatedBranchVO(   vo.getLifeCycle(), 
                                                                                         0, 
                                                                                         branchName, 
                                                                                         Bit.or((OmcSystemConstants.SYSBRANCH_KIND_Default),(OmcSystemConstants.SYSBRANCH_KIND_State)), 
                                                                                         branchFrom,
                                                                                         branchTo, 
                                                                                         branchTo);
                branchList.add(branchVO1);
                OmcSchemaSeperatedBranchVO branchVO2 = new OmcSchemaSeperatedBranchVO(  vo.getLifeCycle(), 
                                                                                        0, 
                                                                                        branchName, 
                                                                                        Bit.or((OmcSystemConstants.SYSBRANCH_KIND_Default),(OmcSystemConstants.SYSBRANCH_KIND_Condition)), 
                                                                                        branchFrom,
                                                                                        "1", 
                                                                                        filter);
                branchList.add(branchVO2);
            }
        }
        return(branchList);
    }
    private void seperateStateInfo(OmcSchemaLifeCycleVO thisVo){
        List<OmcSchemaSeperatedStateVO> list = new ArrayList<OmcSchemaSeperatedStateVO>();
        String stateList[] = thisVo.getStateList().split(Pattern.quote(","));
        for(int i = 0; i < stateList.length; i++){
            if(!StrUtil.isEmpty(stateList[i].trim())){
                String names = stateList[i];String userInputed = "N";String userInputMandantory = "N";
                if (names.indexOf('(') > 0){
                    String flag = names.substring(names.indexOf('('));
                    if("(YY)".equals(flag)){
                        userInputed = "Y";userInputMandantory = "Y";
                    }else if("(Y)".equals(flag)){
                        userInputed = "Y";userInputMandantory = "N";
                    }
                    names = names.substring(0,names.indexOf('('));
                }
                list.add(new OmcSchemaSeperatedStateVO( thisVo.getNames(),
                                                        names,
                                                        i,
                                                        userInputed,
                                                        userInputMandantory));
            }
        }
        thisVo.setSeperatedStateList(list);
    }
    private void seperateFormatInfo(OmcSchemaLifeCycleVO thisVo){
        StringBuffer strBuff = new StringBuffer(thisVo.getAppliedFormatList());
        if(!StrUtil.isEmpty(thisVo.getDefaultFormat()))
        {
            strBuff.append(",").append(thisVo.getDefaultFormat());
        }
        List<OmcSchemaSeperatedFormatVO> list = new ArrayList<OmcSchemaSeperatedFormatVO>();
        String formatList[] = strBuff.toString().split(Pattern.quote(","));
        formatList = StrUtil.uniquize(formatList);
        for(int i = 0; i < formatList.length; i++){
            if(formatList[i] != null){
                if(!StrUtil.isEmpty(formatList[i].trim())) list.add(new OmcSchemaSeperatedFormatVO(formatList[i].trim()));
            }
        }
        thisVo.setSeperatedFormatList(list);
    }
    private void seperateClassInfo(OmcSchemaLifeCycleVO thisVo){
        List<OmcSchemaSeperatedClassVO> list = new ArrayList<OmcSchemaSeperatedClassVO>();
        String classList[] = thisVo.getAppliedClassList().split(Pattern.quote(","));
        classList = StrUtil.uniquize(classList);
        for(int i = 0; i < classList.length; i++){
            if(classList[i] != null){
                if(!StrUtil.isEmpty(classList[i].trim())) list.add(new OmcSchemaSeperatedClassVO(classList[i].trim()));
            }
        }
        thisVo.setSeperatedClassList(list);
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setFlags()
     */
    @Override
    protected void setFlags(){
        OmcSchemaLifeCycleVO thisVO = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSLCYCLE_FLAG_Default,OmcSystemConstants.SYSLCYCLE_FLAG_Active);
        if(thisVO.getAllStateFlag().equals("Y")) flags = flags | OmcSystemConstants.SYSLCYCLE_FLAG_AllStateAcc;
        thisVO.setFlags(flags);
        this.setVo(thisVO);
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        OmcSchemaLifeCycleVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_LifeCycle);
        this.setVo(thisVO);
    }
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.psequences               as sequences          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments         as change_comments    ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames                   as names              ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequence_rule           as sequence_rule      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_format          as default_format     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pstate_list              as state_list         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pstore_name              as store_name         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pall_state_flag          as all_state_flag     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.papplied_class_list      as applied_class_list ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name          as displayed_name     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.papplied_format_list     as applied_format_list,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       (select x.obid from  psyslifecycle x where a.pnames  = x.pnames)  as obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsyslifecycle a");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid                       as obid                ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pflags                     as flags               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pnames                     as names               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.papplied_class_list        as applied_class_list  ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequence_rule             as sequence_rule       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdefault_format            as default_format      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pdisplayed_name            as displayed_name      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.papplied_format_list       as applied_format_list ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.psequences                 as sequences           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pchange_comments           as change_comments     ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreator                   as creator             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pcreated                   as created             ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodifier                  as modifier            ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       a.pmodified                  as modified            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psyslifecycle a ");
    }
    public static void uploadTemporaryList(List<OmcSchemaLifeCycleVO> list){
        OmcSchemaServiceUtils.uploadTemporaryLifeCycle(list);
    }
    public static void uploadTemporaryStateInfoList(List<OmcSchemaLifeCycleStateInfoVO> list){
        OmcSchemaServiceUtils.uploadTemporaryStateInfo(list);
    }
    public static void uploadTemporaryBranchList(List<OmcSchemaLifeCycleBranchVO> list){
        OmcSchemaServiceUtils.uploadTemporaryLifeCycleBranch(list);
    }
    public static void uploadTemporaryStateTriggerList(List<OmcSchemaStateTriggerVO> list){
        OmcSchemaServiceUtils.uploadTemporaryStateTrigger(list);
    }
    public static List<OmcSchemaLifeCycleVO> getLifeCycleListForUpload(){
        return(OmcSchemaServiceUtils.getLifeCycleListForUpload());
    }
    public static List<OmcSchemaLifeCycleVO> getInActiveLifeCycleListForUpload(){
        return(OmcSchemaServiceUtils.getInActiveLifeCycleListForUpload());
    }
    public static List<OmcSchemaSysBizClassVO> getFileBizClassListWithSeperatedList(List<OmcSchemaSeperatedClassVO> seperatedClassList){
        return(OmcSchemaServiceUtils.getFileBizClassListWithSeperatedList(seperatedClassList));
    }
    public List<OmcSchemaStatesVO> getSystemStateListWithLifeCycle(OmcSchemaLifeCycleVO lifeCycleVO){
        return(OmcSchemaServiceUtils.getSystemStateListWithLifeCycle(lifeCycleVO));
    }
    public List<OmcSchemaLifeCycleRelationVO> getSystemLifeCycleRelListWithLifeCycle(OmcSchemaLifeCycleVO lifeCycleVO, long kinds){
        return(OmcSchemaServiceUtils.getSystemLifeCycleRelListWithLifeCycle(lifeCycleVO,kinds));
    }
}
