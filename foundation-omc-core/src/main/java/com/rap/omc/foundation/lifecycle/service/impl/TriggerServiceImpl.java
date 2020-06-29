/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : TriggerServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 4. 7.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;
import com.rap.omc.foundation.lifecycle.model.StateTriggerInfo;
import com.rap.omc.foundation.lifecycle.service.TriggerService;
import com.rap.omc.util.foundation.LifeCycleUtil;



/**
 * <pre>
 * Class : TriggerServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
@Service("triggerService")
public class TriggerServiceImpl implements TriggerService{
    public List<StateTriggerInfo> getStateTriggerList(String lifeCycle,String state,String triggerKinds, String programKinds){
        List<StateTriggerInfo> result = new ArrayList<StateTriggerInfo>();
        LifeCycleInfo lifeCycleInfo = LifeCycleUtil.getLifeCycleInfo(lifeCycle);
        List<StateTriggerInfo> wokWtateTriggerList = lifeCycleInfo.getStateTriggerList();
        for(StateTriggerInfo stateTrigger : wokWtateTriggerList){
            if(state.equals("*") || state.equals(stateTrigger.getStates())){
                if(triggerKinds.equals("*") || triggerKinds.equals(stateTrigger.getTriggerKinds())){
                    if(programKinds.equals("*") || programKinds.equals(stateTrigger.getProgramKinds())){
                        result.add(stateTrigger);
                    }
                }
            }
        }
        return result;
    }
}
