/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : LifecycleService.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 2. 16. kwanghyui.choi Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.service;

import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.foundation.lifecycle.model.BranchInfo;
import com.rap.omc.foundation.lifecycle.model.FormatInfo;
import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;
import com.rap.omc.foundation.lifecycle.model.RoutingLogVO;
import com.rap.omc.foundation.lifecycle.model.StateInfo;



/**
 * <pre>
 * Class : LifecycleService
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public interface LifeCycleService {
    public LifeCycleInfo getLifeCycleInfo(String lifeCycleName);
    
    public FormatInfo getDefaultFormatForLifeCycle(String lifeCycleName);
    
    public List<FormatInfo> getFormatListForLifeCycle(String lifeCycleName);

    public List<StateInfo> getLifieCycleStateListByName(String lifeCycleName);
    
    public List<StateInfo> getUserInputLifieCycleStateListByName(String lifeCycleName);
    
    public boolean checkAllowedFormat(String lifeCycleName, String format);
    
    public boolean checkAllowedFormatForObject(String obid, String format);
    
    public boolean checkAllowedFormatForObject(BusinessObjectRoot obj, String format);

    public StateInfo getStateInfo(String obid);
    
    public boolean checkWorkflowLifeCycleObject(BusinessObjectRootVO businessObjectRootVO);
    
    public void createRoutingLog(Map<String, Object> inputParams);
    
    public String getDefaultState(String lifeCycleName);
    
    public String getWorkflowUrlForObject(String obid);
    
    public RoutingLogVO getLatestPromoteRoutingLog(BusinessObjectRoot businessObjectRoot);
    
    public StateInfo getTargetState(BusinessObjectRootVO vo, String direction);
    
    public StateInfo getLifieCycleStateByStateName(String lifeCycleName, String routeStates);
    
    public List<String> getLifeCycleStateStringListByName(String lifeCycleName);
    
    public List<BranchInfo> getBranchListForState(String lifeCycleName, String states, String direction);
    
    public List<BranchInfo> getBranchListForState(BusinessObjectRootVO businessObjectRootVO, String direction);
    
    public List<BranchInfo> getBranchListForState(String obid,String direction);
}
