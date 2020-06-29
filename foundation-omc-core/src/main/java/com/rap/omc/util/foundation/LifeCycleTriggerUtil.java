/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : LifeCycleTriggerUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 4. 8.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.omc.util.foundation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.foundation.lifecycle.model.StateTriggerInfo;
import com.rap.omc.foundation.lifecycle.service.TriggerService;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.framework.exception.TriggerException;
import com.rap.omc.util.NullUtil;


/**
 * <pre>
 * Class : LifeCycleTriggerUtil
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class LifeCycleTriggerUtil {
    static final Logger LOGGER = LoggerFactory.getLogger(LifeCycleTriggerUtil.class);
    public enum Direction {
        PROMOTE {
            public String toString() {
                return "Promote";
            }
        },

        DEMOTE {
            public String toString() {
                return "Demote";
            }
        }
    }
    
    private static String CHECK         = "Check";
    private static String ACTION_PRE    = "ActionPre";
    private static String ACTION        = "Action";
    private static String ACTION_POST   = "ActionPost";
    
    private static String LIFE_CYCLE    = "LifeCycle";
    private static String STATE         = "State";
    private static String TRIGGER_KINDS = "Triggerkinds";
    private static String PROGRAM_KINDS = "ProgramKinds";
    
    private static LifeCycleTriggerUtil lInstance;

    private TriggerService triggerService;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static LifeCycleTriggerUtil getInstance(){
        if (lInstance == null) {
            lInstance = new LifeCycleTriggerUtil();
            lInstance.triggerService = (TriggerService)SpringFactoryUtil.getBean("triggerService");
        }
        return lInstance;
    }
    
    public static void executeCheckTrigger(BusinessObjectRoot businessObjectRoot, String targetStates, Direction direction) {
        Map<String, String> inputParam = new HashMap<String, String>();
        inputParam.put(LIFE_CYCLE, businessObjectRoot.getVo().getLifeCycle());
        inputParam.put(STATE, businessObjectRoot.getVo().getStates());
        inputParam.put(TRIGGER_KINDS, direction.toString());
        inputParam.put(PROGRAM_KINDS, CHECK);
        
        excuteTrigger(businessObjectRoot, targetStates, inputParam);
    }
    
    public static void executeActionPreTrigger(BusinessObjectRoot businessObjectRoot, String targetStates, Direction direction) {
        Map<String, String> inputParam = new HashMap<String, String>();
        inputParam.put(LIFE_CYCLE, businessObjectRoot.getVo().getLifeCycle());
        inputParam.put(STATE, businessObjectRoot.getVo().getStates());
        inputParam.put(TRIGGER_KINDS, direction.toString());
        inputParam.put(PROGRAM_KINDS, ACTION_PRE);
        
        excuteTrigger(businessObjectRoot, targetStates, inputParam);
    }
    
    public static void executeActionTrigger(BusinessObjectRoot businessObjectRoot, String targetStates, Direction direction) {
        Map<String, String> inputParam = new HashMap<String, String>();
        inputParam.put(LIFE_CYCLE, businessObjectRoot.getVo().getLifeCycle());
        inputParam.put(STATE, businessObjectRoot.getVo().getStates());
        inputParam.put(TRIGGER_KINDS, direction.toString());
        inputParam.put(PROGRAM_KINDS, ACTION);
        
        excuteTrigger(businessObjectRoot, targetStates, inputParam);
    }
    
    public static void executeActionPostTrigger(BusinessObjectRoot businessObjectRoot, String currentStates, String targetStates, Direction direction) {
        Map<String, String> inputParam = new HashMap<String, String>();
        inputParam.put(LIFE_CYCLE, businessObjectRoot.getVo().getLifeCycle());
        inputParam.put(STATE, currentStates);
        inputParam.put(TRIGGER_KINDS, direction.toString());
        inputParam.put(PROGRAM_KINDS, ACTION_POST);
        
        excuteTrigger(businessObjectRoot, targetStates, inputParam);
    }
    
    private static void excuteTrigger(BusinessObjectRoot businessObjectRoot, String targetStates, Map<String, String> inputParam) {
        String lifeCycle    = (String)inputParam.get(LIFE_CYCLE);
        String state        = (String)inputParam.get(STATE);
        String triggerKinds = (String)inputParam.get(TRIGGER_KINDS);
        String programKinds = (String)inputParam.get(PROGRAM_KINDS);
        
        List<StateTriggerInfo> rtnTriggerVOList = getInstance().triggerService.getStateTriggerList(lifeCycle, state, triggerKinds, programKinds);
        
        for(StateTriggerInfo triggerVO : rtnTriggerVOList) {
            List<String> targetStatesList = getTargetStatesList(triggerVO);
            if(NullUtil.isNone(targetStatesList)) {
                invokeTriggerClazz(businessObjectRoot, triggerVO, targetStates);
            }else{
                if(targetStatesList.indexOf(targetStates) > -1){
                    invokeTriggerClazz(businessObjectRoot, triggerVO, targetStates);
                }
            }
        }
    }
    
    private static void invokeTriggerClazz(BusinessObjectRoot businessObjectRoot, StateTriggerInfo triggerParameterVO, String targetStates){
        List<Object> argumentList = new ArrayList<Object>();
        if(!NullUtil.isNone(triggerParameterVO.getArgument01())) {
            if("{targetState}".equals(triggerParameterVO.getArgument01())) {
                argumentList.add(targetStates);
            }else{
                argumentList.add(getArgumentValue(businessObjectRoot, triggerParameterVO.getArgument01()));
            }
        }
        if(!NullUtil.isNone(triggerParameterVO.getArgument02())) {
            if("{targetState}".equals(triggerParameterVO.getArgument02())) {
                argumentList.add(targetStates);
            }else{
                argumentList.add(getArgumentValue(businessObjectRoot, triggerParameterVO.getArgument02()));
            }
        }
        if(!NullUtil.isNone(triggerParameterVO.getArgument03())) {
            if("{targetState}".equals(triggerParameterVO.getArgument03())) {
                argumentList.add(targetStates);
            }else{
                argumentList.add(getArgumentValue(businessObjectRoot, triggerParameterVO.getArgument03()));
            }
        }
        if(!NullUtil.isNone(triggerParameterVO.getArgument04())) {
            if("{targetState}".equals(triggerParameterVO.getArgument04())) {
                argumentList.add(targetStates);
            }else{
                argumentList.add(getArgumentValue(businessObjectRoot, triggerParameterVO.getArgument04()));
            }
        }
        if(!NullUtil.isNone(triggerParameterVO.getArgument05())) {
            if("{targetState}".equals(triggerParameterVO.getArgument05())) {
                argumentList.add(targetStates);
            }else{
                argumentList.add(getArgumentValue(businessObjectRoot, triggerParameterVO.getArgument05()));
            }
        }
        if(!NullUtil.isNone(triggerParameterVO.getArgument06())) {
            if("{targetState}".equals(triggerParameterVO.getArgument06())) {
                argumentList.add(targetStates);
            }else{
                argumentList.add(getArgumentValue(businessObjectRoot, triggerParameterVO.getArgument06()));
            }
        }
        if(!NullUtil.isNone(triggerParameterVO.getArgument07())) {
            if("{targetState}".equals(triggerParameterVO.getArgument07())) {
                argumentList.add(targetStates);
            }else{
                argumentList.add(getArgumentValue(businessObjectRoot, triggerParameterVO.getArgument07()));
            }
        }
        if(!NullUtil.isNone(triggerParameterVO.getArgument08())) {
            if("{targetState}".equals(triggerParameterVO.getArgument08())) {
                argumentList.add(targetStates);
            }else{
                argumentList.add(getArgumentValue(businessObjectRoot, triggerParameterVO.getArgument08()));
            }
        }
        if(!NullUtil.isNone(triggerParameterVO.getArgument09())) {
            if("{targetState}".equals(triggerParameterVO.getArgument09())) {
                argumentList.add(targetStates);
            }else{
                argumentList.add(getArgumentValue(businessObjectRoot, triggerParameterVO.getArgument09()));
            }
        }
        if(!NullUtil.isNone(triggerParameterVO.getArgument10())) {
            if("{targetState}".equals(triggerParameterVO.getArgument10())) {
                argumentList.add(targetStates);
            }else{
                argumentList.add(getArgumentValue(businessObjectRoot, triggerParameterVO.getArgument10()));
            }
        }
        try{
            Class<?>[] paramType = new Class<?>[argumentList.size()];
            Object[] sArguments = argumentList.toArray(new Object[argumentList.size()]);
            
            for(int idx = 0 ; idx < sArguments.length; idx++) {
                paramType[idx] = sArguments[idx].getClass();
            }
            Object objct = getRunClass(triggerParameterVO.getProgramName());
            Method method = objct.getClass().getMethod(triggerParameterVO.getMethodName(), paramType);
            method.invoke(objct, sArguments);
        } catch (SecurityException e) {
            throw new FoundationException("api.object.error.trigger.security",e);
        } catch (IllegalAccessException e) {
            throw new FoundationException("api.object.error.trigger.illegalAccess",e);
        } catch (IllegalArgumentException e) {
            throw new FoundationException("api.object.error.trigger.illegalArgument",e);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof TriggerException) {
                TriggerException triggerException = (TriggerException)e.getTargetException();
                throw new FoundationException(triggerException.getCode(), triggerException.getMessageParameters());
            } 
            throw new FoundationException("api.object.error.trigger.invocationTarget",e);
        } catch (NoSuchMethodException e) {
            throw new FoundationException("api.object.error.trigger.noSuchMethod",e);
        } 
    }
    
    private static Object getRunClass(String classNames){
        Object runClass = null;
        try {
            Class<?> cls = Class.forName(classNames);
            if (classNames.contains("Service")) {
                runClass = SpringFactoryUtil.getBean(cls);
            } else {
                runClass = cls.newInstance();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            throw new FoundationException("omc.error.workflow.bean.created", new Object[] {classNames},e);
        }
        return runClass;
    }
    
    private static Object getArgumentValue(BusinessObjectRoot businessObjectRoot, String argument) {
        BusinessObjectRootVO businessObjectRootVO = businessObjectRoot.getVo();
        Object rtnValue = null;
        if(argument.startsWith("${") && argument.endsWith("}")) {
                String variable = argument.substring(argument.indexOf("${")+2, argument.lastIndexOf('}'));
                try {
                    String methodString = "get" + variable.substring(0, 1).toUpperCase() + variable.substring(1);
                    Method method = businessObjectRootVO.getClass().getMethod(methodString, new Class<?>[0]);
                    rtnValue = method.invoke(businessObjectRootVO,  new Object[0]);
                } catch (SecurityException e) {
                    throw new FoundationException("frame.error.resolver.security",e);
                } catch (IllegalAccessException e) {
                    throw new FoundationException("frame.error.resolver.illegalAccess",e);
                } catch (IllegalArgumentException e) {
                    throw new FoundationException("frame.error.resolver.illegalArgument",e);
                } catch (InvocationTargetException e) {
                    throw new FoundationException("frame.error.resolver.invocationTarget",e);
                } catch (NoSuchMethodException e) {
                    throw new FoundationException("frame.error.resolver.noSuchMethod",e);
                } 
        }else{
            rtnValue = argument;
        }
        return rtnValue;
    }
    
    private static List<String> getTargetStatesList(StateTriggerInfo triggerParameterVO) {
        List<String> rtnTargetStatesList = new ArrayList<String>();
        if(!NullUtil.isNull(triggerParameterVO.getTargetState())) {
            String[] tempTargetStatesArray = triggerParameterVO.getTargetState().split(",");
            for(String targetStates : tempTargetStatesArray) {
                rtnTargetStatesList.add(targetStates.trim());
            }
        }
        return rtnTargetStatesList;
    }
}
