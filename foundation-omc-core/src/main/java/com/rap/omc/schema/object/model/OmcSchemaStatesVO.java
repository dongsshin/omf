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
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaStates
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaStatesVO extends OmcSchemaSysRootVO{
    private String  lifeCycle                          ;
    private String  lifeCycleNames                     ;
    private int     sequence                           ;
    private String  userInputed                        ;
    private String  userInputMandantory                ;
    
    
    public String getLifeCycleNames(){
        return lifeCycleNames;
    }
    
    public void setLifeCycleNames(String lifeCycleNames){
        this.lifeCycleNames = lifeCycleNames;
    }

    public String getUserInputed(){
        return userInputed;
    }


    
    public String getUserInputMandantory(){
        return userInputMandantory;
    }


    
    public void setUserInputed(String userInputed){
        this.userInputed = userInputed;
    }


    
    public void setUserInputMandantory(String userInputMandantory){
        this.userInputMandantory = userInputMandantory;
    }

    private OmcSchemaLifeCycleStateInfoVO stateInfoVo;
    
    
    public OmcSchemaLifeCycleStateInfoVO getStateInfoVo(){
        return stateInfoVo;
    }

    
    public void setStateInfoVo(OmcSchemaLifeCycleStateInfoVO stateInfoVo){
        this.stateInfoVo = stateInfoVo;
    }

    public String getLifeCycle(){
        return lifeCycle;
    }
    
    public int getSequence(){
        return sequence;
    }
    
    public void setLifeCycle(String lifeCycle){
        this.lifeCycle = lifeCycle;
    }
    
    public void setSequence(int sequence){
        this.sequence = sequence;
    }
}