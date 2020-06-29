/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : lifeCycleInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 11.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.lifecycle.model;

import java.util.List;

import com.rap.omc.foundation.common.model.ModelRootVO;
import com.rap.omc.foundation.file.model.FcsLocationVO;
import com.rap.omc.util.NullUtil;


/**
 * <pre>
 * Class : lifeCycleInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class LifeCycleInfo extends ModelRootVO{
    private String   obid;
    private long     flags    ;
    private String   lifeCycleName;
    private String   sequenceRule;
    private String   defaultFormat;
    private String   displayedName;
    private String   displayedNameKr;
    List<FormatInfo> formatList;
    List<StateInfo>  stateList;
    List<String>     classList;
    List<StateTriggerInfo> stateTriggerList;
    FcsLocationVO    currentFileStore;
        
    public String getObid(){
        return obid;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public long getFlags(){
        return flags;
    }
    
    public void setFlags(long flags){
        this.flags = flags;
    }
    
    public String getLifeCycleName(){
        return lifeCycleName;
    }
    
    public void setLifeCycleName(String lifeCycleName){
        this.lifeCycleName = lifeCycleName;
    }
    
    public String getSequenceRule(){
        return sequenceRule;
    }
    
    public void setSequenceRule(String sequenceRule){
        this.sequenceRule = sequenceRule;
    }
    
    public String getDefaultFormat(){
        return defaultFormat;
    }
    
    public void setDefaultFormat(String defaultFormat){
        this.defaultFormat = defaultFormat;
    }
    
    public String getDisplayedName(){
        return displayedName;
    }
    
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }
    
    public String getDisplayedNameKr(){
        return displayedNameKr;
    }
    
    public void setDisplayedNameKr(String displayedNameKr){
        this.displayedNameKr = displayedNameKr;
    }
    
    public List<FormatInfo> getFormatList(){
        return formatList;
    }
    
    public void setFormatList(List<FormatInfo> formatList){
        this.formatList = formatList;
    }
    
    public List<StateInfo> getStateList(){
        return stateList;
    }
    
    public void setStateList(List<StateInfo> stateList){
        this.stateList = stateList;
    }
    
    public List<String> getClassList(){
        return classList;
    }
    
    public void setClassList(List<String> classList){
        this.classList = classList;
    }
    
    public List<StateTriggerInfo> getStateTriggerList(){
        return stateTriggerList;
    }
    
    public void setStateTriggerList(List<StateTriggerInfo> stateTriggerList){
        this.stateTriggerList = stateTriggerList;
    }

    public FcsLocationVO getCurrentFileStore(){
        return currentFileStore;
    }

    
    public void setCurrentFileStore(FcsLocationVO currentFileStore){
        this.currentFileStore = currentFileStore;
    }
    
    public StateInfo getFirstState(){
        if(NullUtil.isNone(this.stateList)) return null;
        return stateList.get(0);
    }
    public StateInfo getLastState(){
        if(NullUtil.isNone(this.stateList)) return null;
        return stateList.get(stateList.size()-1);
    }
}
