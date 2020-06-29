/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaStateTriggerVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 3.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaStateTriggerVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaStateTriggerVO extends OmcSchemaSysRootVO{
    private String  policyName                         ;
    private String  states                             ;
    private String  stateName                          ;
    private long    kinds                              ;
    private String  kindsStr                           ;
    private int     calledSequences                    ;
    private long    programKinds                       ;
    private String  programKindsStr                    ;
    private String  programName                        ;
    private String  triggerName                        ;

    public String getTriggerName(){
        return triggerName;
    }
    
    public void setTriggerName(String triggerName){
        this.triggerName = triggerName;
    }
    public String getPolicyName(){
        return policyName;
    }
    
    
    public String getStateName(){
        return stateName;
    }
    
    public long getKinds(){
        return kinds;
    }
    
    public String getKindsStr(){
        return kindsStr;
    }
    
    public int getCalledSequences(){
        return calledSequences;
    }
    
    public long getProgramKinds(){
        return programKinds;
    }
    
    public String getProgramKindsStr(){
        return programKindsStr;
    }
    
    public String getProgramName(){
        return programName;
    }
    
    public void setPolicyName(String policyName){
        this.policyName = policyName;
    }
    public void setStateName(String stateName){
        this.stateName = stateName;
    }
    
    public void setKinds(long kinds){
        this.kinds = kinds;
        if(Bit.isInclude(kinds, OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote)) this.kindsStr = "Promote";
        if(Bit.isInclude(kinds, OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote )) this.kindsStr = "Demote";
    }
    
    public void setKindsStr(String kindsStr){
        this.kindsStr = kindsStr;
        if("Promote".equals(kindsStr)) this.kinds = Bit.or(OmcSystemConstants.SYSSTATETRIGGER_KIND_Default,OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote);
        if("Demote".equals(kindsStr))  this.kinds = Bit.or(OmcSystemConstants.SYSSTATETRIGGER_KIND_Default,OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote);
    }
    
    public void setCalledSequences(int calledSequences){
        this.calledSequences = calledSequences;
    }
    public void setProgramKinds(long programKinds){
        this.programKinds = programKinds;
        if(Bit.isInclude(programKinds, OmcSystemConstants.SYSSTTRIG_PKIND_Check)) this.programKindsStr = "Check";
        if(Bit.isInclude(programKinds, OmcSystemConstants.SYSSTTRIG_PKIND_ActionPre)) this.programKindsStr = "ActionPre";
        if(Bit.isInclude(programKinds, OmcSystemConstants.SYSSTTRIG_PKIND_Action)) this.programKindsStr = "Action";
        if(Bit.isInclude(programKinds, OmcSystemConstants.SYSSTTRIG_PKIND_ActionPost)) this.programKindsStr = "ActionPost";
    }
    
    public void setProgramKindsStr(String programKindsStr){
        this.programKindsStr = programKindsStr;
        if("Check".equals(programKindsStr)) this.programKinds = Bit.or(OmcSystemConstants.SYSSTTRIG_PKIND_Default,OmcSystemConstants.SYSSTTRIG_PKIND_Check);
        if("ActionPre".equals(programKindsStr)) this.programKinds = Bit.or(OmcSystemConstants.SYSSTTRIG_PKIND_Default,OmcSystemConstants.SYSSTTRIG_PKIND_ActionPre);
        if("Action".equals(programKindsStr)) this.programKinds = Bit.or(OmcSystemConstants.SYSSTTRIG_PKIND_Default,OmcSystemConstants.SYSSTTRIG_PKIND_Action);
        if("ActionPost".equals(programKindsStr)) this.programKinds = Bit.or(OmcSystemConstants.SYSSTTRIG_PKIND_Default,OmcSystemConstants.SYSSTTRIG_PKIND_ActionPost);
    }
    public void setProgramName(String programName){
        this.programName = programName;
    }
    

}
