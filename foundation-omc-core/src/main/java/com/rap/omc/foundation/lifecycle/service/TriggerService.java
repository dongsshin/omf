/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : TriggerService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 4. 7.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rap.omc.foundation.lifecycle.model.StateTriggerInfo;



/**
 * <pre>
 * Class : TriggerService
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
@Service("triggerService")
public interface TriggerService {
    public List<StateTriggerInfo>getStateTriggerList(String lifeCycle,String state,String triggerKinds, String programKinds);
}
