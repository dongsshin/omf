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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.lifecycle.branch.BranchExpressionParser;
import com.rap.omc.foundation.lifecycle.model.BranchInfo;
import com.rap.omc.foundation.lifecycle.model.FormatInfo;
import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;
import com.rap.omc.foundation.lifecycle.model.RoutingLogVO;
import com.rap.omc.foundation.lifecycle.model.StateInfo;
import com.rap.omc.foundation.lifecycle.service.LifeCycleService;
import com.rap.omc.framework.exception.BranchException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.foundation.LifeCycleUtil;
import com.rap.omc.util.NullUtil;



/**
 * <pre>
 * Class : LifeCycleServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
@Service("lifeCycleService")
public class LifeCycleServiceImpl implements LifeCycleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObject.class);
    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;
    
    public LifeCycleInfo getLifeCycleInfo(String lifeCycleName){
        return(LifeCycleUtil.getLifeCycleInfoSub(lifeCycleName));
    }
    public List<FormatInfo> getFormatListForLifeCycle(String lifeCycleName){
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(lifeCycleName);
        return(lifeCycleInfo.getFormatList());
    }
    public FormatInfo getDefaultFormatForLifeCycle(String lifeCycleName){
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(lifeCycleName);
        List<FormatInfo>  formatInfoList = lifeCycleInfo.getFormatList();
        if(NullUtil.isNone(formatInfoList)) return null;
        String defaultFormtStr = lifeCycleInfo.getDefaultFormat();
        for(FormatInfo formatInfo : formatInfoList){
            if(formatInfo.getNames().equals(defaultFormtStr)){
                return formatInfo;
            }
        }
        return null;
    }
    public List<StateInfo> getLifieCycleStateListByName(String lifeCycleName){
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(lifeCycleName);
        if(NullUtil.isNull(lifeCycleInfo)) return null;
        return(lifeCycleInfo.getStateList());
    }
    public StateInfo getLifieCycleStateByStateName(String lifeCycleName, String routeStates){
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(lifeCycleName);
        if(!NullUtil.isNull(lifeCycleInfo)){
            List<StateInfo>  stateList = lifeCycleInfo.getStateList();
            if(!NullUtil.isNone(stateList)){
                for (StateInfo state : stateList) {
                    if(routeStates.equals(state.getStateName())) return state;
                }
            }
        }
        return(null);
    }
    public StateInfo getLifieCycleStateByStateObid(String lifeCycleName, String stateObid){
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(lifeCycleName);
        if(!NullUtil.isNull(lifeCycleInfo)){
            List<StateInfo>  stateList = lifeCycleInfo.getStateList();
            if(!NullUtil.isNone(stateList)){
                for (StateInfo state : stateList) {
                    if(stateObid.equals(state.getObid())) return state;
                }
            }
        }
        return(null);
    }
    public List<String> getLifeCycleStateStringListByName(String lifeCycleName){
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(lifeCycleName);
        List<String> result = new ArrayList<String>();
        if(!NullUtil.isNull(lifeCycleInfo)){
            List<StateInfo>  stateList = lifeCycleInfo.getStateList();
            if(!NullUtil.isNone(stateList)){
                for (StateInfo state : stateList) {
                    result.add(state.getStateName());
                }
            }
        }
        return result;
    }
    public List<StateInfo> getUserInputLifieCycleStateListByName(String lifeCycleName){
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(lifeCycleName);
        List<StateInfo> result = new ArrayList<StateInfo>();
        if(!NullUtil.isNull(lifeCycleInfo)){
            List<StateInfo>  stateList = lifeCycleInfo.getStateList();
            if(!NullUtil.isNone(stateList)){
                for (StateInfo state : stateList) {
                    if(Bit.isInclude(state.getFlags(), OmcSystemConstants.SYSSTATE_FLAG_UserInput)) result.add(state);
                }
            }
        }
        return result;
    }
    public boolean checkAllowedFormat(String lifeCycleName, String format){
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(lifeCycleName);
        if(!NullUtil.isNull(lifeCycleInfo)){
            List<FormatInfo> formatList = lifeCycleInfo.getFormatList();
            if(!NullUtil.isNone(formatList)){
                for (FormatInfo formatInfo : formatList) {
                    if(format.equals(formatInfo.getNames())) return true;
                }
            }
        }
        return false;
    }
    public boolean checkAllowedFormatForObject(String obid, String format){
        BusinessObjectRoot obj = new BusinessObjectRoot(obid);
        return(checkAllowedFormatForObject(obj,format));
    }
    public boolean checkAllowedFormatForObject(BusinessObjectRoot obj, String format){
        return(checkAllowedFormat(obj.getVo().getLifeCycle(),format));
    }
    public StateInfo getStateInfo(String obid){
        BusinessObjectRoot obj = new BusinessObjectRoot(obid);
        return getLifieCycleStateByStateName(obj.getVo().getLifeCycle(),obj.getVo().getStates());
    }
    public StateInfo getStateInfo(BusinessObjectRootVO vo, String stateObid){
        return getLifieCycleStateByStateName(vo.getLifeCycle(),stateObid);
    }
    @Override
    public boolean checkWorkflowLifeCycleObject(BusinessObjectRootVO businessObjectRootVO){
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(businessObjectRootVO.getLifeCycle());
        String className = businessObjectRootVO.getClassName();
        List<String> classList = lifeCycleInfo.getClassList();
        if(NullUtil.isNone(classList)) return false;
        return(classList.contains(className));
    }
    public String getDefaultState(String lifeCycleName) {
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(lifeCycleName);
        if(!NullUtil.isNull(lifeCycleInfo)){
            return(lifeCycleInfo.getStateList().get(0).getStateName());
        }
        return null;
    }
    public String getWorkflowUrlForObject(String obid) {
        BusinessObjectRoot obj = new BusinessObjectRoot(obid);
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(obj.getVo().getClassName());
        return(classInfo.getWorkflowUrl());
    }
    
    public List<BranchInfo> getBranchListForState(String lifeCycleName, String states, String direction){
        LifeCycleInfo lifeCycleInfo = getLifeCycleInfo(lifeCycleName);
        List<StateInfo>  stateList = lifeCycleInfo.getStateList();
        StateInfo currentStateInfo = null;
        if("Promote".equals(direction)){
            currentStateInfo = getLifieCycleStateByStateName(lifeCycleName,states);
        }else{
            currentStateInfo = getPreviousState(stateList,states);
        }
        List<BranchInfo> branchList = currentStateInfo.getBranchList();
        List<BranchInfo> newBranchList = getBranchList(branchList,stateList);
        if("Promote".equals(direction)){
            StateInfo nextStateInfo = getNextState(stateList,states);
            if(!NullUtil.isNull(nextStateInfo)){
                if(NullUtil.isNone(newBranchList)) {
                    BranchInfo newBrInfo = new BranchInfo();
                    newBrInfo.setBranchName("GoTo Next State");
                    newBrInfo.setConditionExpression("attribute[branchTo] == 'None'");
                    newBrInfo.setToObject(nextStateInfo.getObid());
                    newBrInfo.setStates(nextStateInfo.getObid());
                    newBrInfo.setStatesName(nextStateInfo.getStateName());
                    newBranchList.add(newBrInfo);
                }else{
                    boolean isExists = false;
                    for(BranchInfo brInfo : branchList){
                        if(brInfo.getToObject().equals(nextStateInfo.getObid())) {isExists = true; break;}
                    }
                    if(!isExists){
                        BranchInfo newBrInfo = new BranchInfo();
                        newBrInfo.setBranchName("GoTo Next State");
                        newBrInfo.setConditionExpression("attribute[branchTo] == 'None'");
                        newBrInfo.setToObject(nextStateInfo.getObid());
                        newBrInfo.setStates(nextStateInfo.getObid());
                        newBrInfo.setStatesName(nextStateInfo.getStateName());
                        newBranchList.add(newBrInfo);
                    }
                }
            }
        }
        if("Demote".equals(direction)){
            if(NullUtil.isNone(newBranchList)){
                BranchInfo newBrInfo = new BranchInfo();
                newBrInfo.setBranchName("'GoTo Pre State");
                newBrInfo.setConditionExpression("1==1");
                newBrInfo.setToObject(currentStateInfo.getObid());
                newBrInfo.setStates(currentStateInfo.getObid());
                newBrInfo.setStatesName(currentStateInfo.getStateName());
                newBranchList.add(newBrInfo);
            }
        }
        return newBranchList;
    }
    private List<BranchInfo> getBranchList(List<BranchInfo> branchList,List<StateInfo>  stateList){
        List<BranchInfo> newList = new ArrayList<BranchInfo>();
        Set<String> branchStrSet = new HashSet<String>();
        for(BranchInfo vo : branchList){
            branchStrSet.add(vo.getBranchName());
        }
        for(String branchName : branchStrSet){
            BranchInfo hashBrInfo = new BranchInfo();
            boolean isFound = false; int cnt = 0;
            for(BranchInfo brInfo : branchList){
                if(brInfo.getBranchName().equals(branchName)){
                    if(!isFound) hashBrInfo = (BranchInfo)DomUtil.copy(brInfo);
                    if(Bit.isInclude(brInfo.getKinds(),OmcSystemConstants.SYSBRANCH_KIND_Condition)){
                        hashBrInfo.setConditionExpression(brInfo.getConditionExpression());
                        cnt++;
                    }
                    if(Bit.isInclude(brInfo.getKinds(),OmcSystemConstants.SYSBRANCH_KIND_State)){
                        StateInfo targetInfo  = getState(stateList,brInfo.getToObject());
                        hashBrInfo.setStates(targetInfo.getObid());
                        hashBrInfo.setStatesName(targetInfo.getStateName());
                        hashBrInfo.setToObject(targetInfo.getObid());
                        cnt++;
                    }
                    isFound = true;
                }
            }
            if(isFound){
                if(cnt != 2) throw new FoundationException("[Foundation] Branch info is invalid.Relation count is " + cnt + ". Must be 2. Error Branch Name is '" + hashBrInfo.getBranchName() + "'.");
                newList.add(hashBrInfo);
            }
        }
        return newList;
    }
    public List<BranchInfo> getBranchListForState(BusinessObjectRootVO businessObjectRootVO, String direction){
        return(getBranchListForState(businessObjectRootVO.getLifeCycle(),businessObjectRootVO.getStates(),direction));
    }
    public List<BranchInfo> getBranchListForState(String obid,String direction){
        BusinessObjectRoot obj = new BusinessObjectRoot(obid);
        return(getBranchListForState(obj.getVo().getLifeCycle(),obj.getVo().getStates(),direction));
    }
    /**
     * target State를 검색한다.
     *
     * @param vo
     * @param direction Promote / Demote / Withdraw
     * @return
     */
    public StateInfo getTargetState(BusinessObjectRootVO vo, String direction){
        StateInfo nextState = null;
        List<BranchInfo> branchList = getBranchListForState(vo.getLifeCycle(),vo.getStates(),direction);
        if (!NullUtil.isNone(branchList)) {
            int successCount = 0;
            String nextStateObid = null;
            StringBuffer sb = new StringBuffer();
            for (BranchInfo branchInfo : branchList) {
                try {
                    if (BranchExpressionParser.checkExpression(vo, branchInfo.getConditionExpression())) {
                        successCount++;
                        nextStateObid = branchInfo.getStates();

                        sb.append(",[").append(branchInfo.getBranchName()).append("] ")
                                .append(branchInfo.getStatesName());
                    }
                } catch (FoundationException e) {
                    LOGGER.error("[{}] {}", branchInfo.getBranchName(), e.getMessage());
                }
            }
            if (successCount == 1 && !NullUtil.isNone(nextStateObid)) {
                LOGGER.debug("Found Target State = {}", sb.toString().substring(1));
                nextState = getLifieCycleStateByStateObid(vo.getLifeCycle(),nextStateObid);
            } else if (successCount > 1) {
                throw new FoundationException("omc.error.workflow.targetState.duplicate", new  Object[] { sb.toString()
                        .substring(1) });
            } else {
                throw new FoundationException("omc.error.workflow.targetState.notExist");
            }
        } else {
            throw new BranchException("omc.error.workflow.targetState.notExist");
        }

        return nextState;
    }
    public RoutingLogVO getLatestPromoteRoutingLog(BusinessObjectRoot businessObjectRoot) {
        HashMap<String, Object> inputParams = new HashMap<String, Object>();
        inputParams.put("obid",         businessObjectRoot.getVo().getObid());
        inputParams.put("lifeCycle",    businessObjectRoot.getVo().getLifeCycle());
        inputParams.put("states",       businessObjectRoot.getVo().getStates());
        RoutingLogVO vo = schemaDao.select("LifeCycle.selectLatestPromoteRoutingLog", inputParams);
        return vo;
    }
    @Override
    public void createRoutingLog(Map<String, Object> inputParams) {
    	schemaDao.insert("LifeCycle.insertRoutingLog", inputParams);
    }
    private StateInfo getPreviousState(List<StateInfo>  stateList,String currentStates){
        StateInfo preStateInfo = null;
        boolean isFounded = false;
        for(StateInfo stateInfo : stateList){
            if(currentStates.equals(stateInfo.getStateName())) {isFounded = true;break;}
            preStateInfo = stateInfo;
        }
        if(!isFounded) preStateInfo = null;
        return preStateInfo;
    }
    private StateInfo getNextState(List<StateInfo>  stateList,String currentStates){
        StateInfo preStateInfo = null;
        boolean isFounded = false;
        int idx = 0;
        for(StateInfo stateInfo : stateList){
            if(currentStates.equals(stateInfo.getStateName())) {isFounded = true;break;}
            idx++;
        }
        if(!isFounded) {
            preStateInfo = null;
        }else{
            if(stateList.size() == (idx + 1)) {
                preStateInfo = null;
            }else{
                preStateInfo = stateList.get(idx + 1);
            }
        }
        return preStateInfo;
    }
    private StateInfo getState(List<StateInfo>  stateList,String stateObid){
        StateInfo preStateInfo = null;
        for(StateInfo stateInfo : stateList){
            if(stateObid.equals(stateInfo.getObid())) {preStateInfo = stateInfo;break;}
        }
        return preStateInfo;
    }
}
