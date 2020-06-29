/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : FunctionUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 2. 11. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.util.foundation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.lifecycle.model.BranchInfo;
import com.rap.omc.foundation.lifecycle.model.FormatInfo;
import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;
import com.rap.omc.foundation.lifecycle.model.RoutingLogVO;
import com.rap.omc.foundation.lifecycle.model.StateInfo;
import com.rap.omc.foundation.lifecycle.service.LifeCycleService;
import com.rap.omc.foundation.lifecycle.service.LifeCycleSubService;



/**
 * <pre>
 * Class : LifecycleUtil
 * Description : TODO
 * </pre>
 * 
 * @author Dongsshin
 */
public class LifeCycleUtil {

    private static LifeCycleUtil lInstance;

    private LifeCycleService    lifeCycleService;
    private LifeCycleSubService lifeCycleSubService;
    
    private synchronized static LifeCycleUtil getInstance(){
        if (lInstance == null) {
            lInstance = new LifeCycleUtil();
            lInstance.lifeCycleService = (LifeCycleService)SpringFactoryUtil.getBean("lifeCycleService");
            lInstance.lifeCycleSubService = (LifeCycleSubService)SpringFactoryUtil.getBean("lifeCycleSubService");
        }
        return lInstance;
    }
    public static LifeCycleInfo getLifeCycleInfo(String lifeCycleName){
        return getInstance().lifeCycleService.getLifeCycleInfo(lifeCycleName);
    }
    
    public static FormatInfo getDefaultFormatForLifeCycle(String lifeCycleName){
        return getInstance().lifeCycleService.getDefaultFormatForLifeCycle(lifeCycleName);
    }
    
    public static List<FormatInfo> getFormatListForLifeCycle(String lifeCycleName){
        return getInstance().lifeCycleService.getFormatListForLifeCycle(lifeCycleName);
    }
    
    public static List<StateInfo> getLifieCycleStateListByName(String lifeCycleName){
        return getInstance().lifeCycleService.getLifieCycleStateListByName(lifeCycleName);
    }
    public static List<StateInfo> getUserInputLifieCycleStateListByName(String lifeCycleName){
        return getInstance().lifeCycleService.getUserInputLifieCycleStateListByName(lifeCycleName);
    }
    public static boolean checkAllowedFormat(String lifeCycleName, String format){
        return getInstance().lifeCycleService.checkAllowedFormat(lifeCycleName,format);
    }
    public static boolean checkAllowedFormatForObject(String obid, String format){
        return getInstance().lifeCycleService.checkAllowedFormatForObject(obid,format);
    }
    public static boolean checkAllowedFormatForObject(BusinessObjectRoot obj, String format){
        return getInstance().lifeCycleService.checkAllowedFormatForObject(obj,format);
    }

    public static StateInfo getStateInfo(String obid){
        return getInstance().lifeCycleService.getStateInfo(obid);
    }
    
    public static boolean checkWorkflowLifeCycleObject(BusinessObjectRootVO businessObjectRootVO){
        return getInstance().lifeCycleService.checkWorkflowLifeCycleObject(businessObjectRootVO);
    }
    
    public static boolean checkWorkflowLifeCycleObject(BusinessObjectRoot businessObjectRoot) {
        return(checkWorkflowLifeCycleObject(businessObjectRoot.getVo()));
    }
    
    public static void createRoutingLog(Map<String, Object> inputParams){
        getInstance().lifeCycleService.createRoutingLog(inputParams);
    }
    
    public static String getDefaultState(String lifeCycleName){
        return getInstance().lifeCycleService.getDefaultState(lifeCycleName);
    }
    
    public static String getWorkflowUrlForObject(String obid){
        return getInstance().lifeCycleService.getWorkflowUrlForObject(obid);
    }
    
    public static void createRoutingLog(BusinessObjectRoot businessObjectRoot, String fromStates, String toStates, String direction) {
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        Map<String, Object> inputParams = new HashMap<String, Object>();
        inputParams.put("obid",         businessObjectRoot.getVo().getObid());
        inputParams.put("lifeCycle",    businessObjectRoot.getVo().getLifeCycle());
        inputParams.put("fromStates",   fromStates);
        inputParams.put("toStates",     toStates);
        inputParams.put("actionMethod", direction);
        inputParams.put("comments",     "");
        inputParams.put("actionedBy",   userId);
        inputParams.put("creator",      userId);
        inputParams.put("modifier",     userId);
        createRoutingLog(inputParams);
    }
    public static RoutingLogVO getLatestPromoteRoutingLog(BusinessObjectRoot businessObjectRoot){
        return getInstance().lifeCycleService.getLatestPromoteRoutingLog(businessObjectRoot);
    }
    public static StateInfo getTargetState(BusinessObjectRootVO vo, String direction){
        return getInstance().lifeCycleService.getTargetState(vo,direction);
    }
    public static StateInfo getLifieCycleStateByStateName(String lifeCycleName, String routeStates){
        return getInstance().lifeCycleService.getLifieCycleStateByStateName(lifeCycleName,routeStates);
    }
    public static List<String> getLifeCycleStateStringListByName(String lifeCycleName){
        return getInstance().lifeCycleService.getLifeCycleStateStringListByName(lifeCycleName);
    }
    public static List<BranchInfo> getBranchListForState(String obid,  String lifeCycleName, String states, String direction){
        return getInstance().lifeCycleService.getBranchListForState(lifeCycleName,states,direction);
    }
    public static List<BranchInfo> getBranchListForState(BusinessObjectRootVO businessObjectRootVO, String direction){
        return getInstance().lifeCycleService.getBranchListForState(businessObjectRootVO,direction);
    }
    public static List<BranchInfo> getBranchListForState(String obid,String direction){
        return getInstance().lifeCycleService.getBranchListForState(obid,direction);
    }
    public static LifeCycleInfo getLifeCycleInfoSub(String lifeCycleName){
        return getInstance().lifeCycleSubService.getLifeCycleInfo(lifeCycleName);
    }
    
    
}
