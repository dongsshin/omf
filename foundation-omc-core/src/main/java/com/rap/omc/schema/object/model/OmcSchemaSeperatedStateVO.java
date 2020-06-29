/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSeperatedStateVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 28.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import com.rap.omc.api.object.model.BaseModel;


/**
 * <pre>
 * Class : OmcSchemaSeperatedStateVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSeperatedStateVO extends BaseModel{
    private String  lifeCycle                          ;
    private String  names                              ;
    private int     sequences                          ;
    private String  userInputed                        ;
    private String  userInputMandantory                ;
    
    /**
     * @param lifeCycle
     * @param names
     * @param sequences
     * @param userInputed
     * @param userInputMandantory
     */
    public OmcSchemaSeperatedStateVO(String lifeCycle, String names, int sequences, String userInputed,
            String userInputMandantory) {
        super();
        this.lifeCycle = lifeCycle;
        this.names = names;
        this.sequences = sequences;
        this.userInputed = userInputed;
        this.userInputMandantory = userInputMandantory;
    }

    public String getLifeCycle(){
        return lifeCycle;
    }
    
    public String getNames(){
        return names;
    }
    
    public int getSequences(){
        return sequences;
    }
    
    public String getUserInputed(){
        return userInputed;
    }
    
    public String getUserInputMandantory(){
        return userInputMandantory;
    }
    
    public void setLifeCycle(String lifeCycle){
        this.lifeCycle = lifeCycle;
    }
    
    public void setNames(String names){
        this.names = names;
    }
    
    public void setSequences(int sequences){
        this.sequences = sequences;
    }
    
    public void setUserInputed(String userInputed){
        this.userInputed = userInputed;
    }
    
    public void setUserInputMandantory(String userInputMandantory){
        this.userInputMandantory = userInputMandantory;
    }
    
}
