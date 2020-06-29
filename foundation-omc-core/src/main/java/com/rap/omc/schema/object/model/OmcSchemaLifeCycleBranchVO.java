/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaLifeCycleBranchVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 3.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import com.rap.omc.api.object.model.BaseModel;


/**
 * <pre>
 * Class : OmcSchemaLifeCycleBranchVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaLifeCycleBranchVO extends BaseModel{
    private int     sequences                          ;
    private String  changeComments                     ;
    private String  lifeCycle                          ;
    private String  bracnhInfo                         ;
    
    public int getSequences(){
        return sequences;
    }
    
    public String getChangeComments(){
        return changeComments;
    }
    
    public String getLifeCycle(){
        return lifeCycle;
    }
    
    public String getBracnhInfo(){
        return bracnhInfo;
    }
    
    public void setSequences(int sequences){
        this.sequences = sequences;
    }
    
    public void setChangeComments(String changeComments){
        this.changeComments = changeComments;
    }
    
    public void setLifeCycle(String lifeCycle){
        this.lifeCycle = lifeCycle;
    }
    
    public void setBracnhInfo(String bracnhInfo){
        this.bracnhInfo = bracnhInfo;
    }
    
}
