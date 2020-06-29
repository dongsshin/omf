/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaLifeCycleVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 3.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import java.util.List;


/**
 * <pre>
 * Class : OmcSchemaLifeCycleVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaLifeCycleVO extends OmcSchemaSysRootVO{
    private String  sequenceRule                       ;
    private String  defaultFormat                      ;
    private String  stateList                          ;
    private String  storeName                          ;
    private String  allStateFlag                       ;
    private String  appliedClassList                   ;
    private String  displayedName                      ;
    private String  appliedFormatList                  ;
    private List<OmcSchemaSeperatedStateVO> seperatedStateList ;
    private List<OmcSchemaSeperatedClassVO> seperatedClassList ;
    private List<OmcSchemaSeperatedFormatVO> seperatedFormatList ;
    private List<OmcSchemaLifeCycleStateInfoVO> stateInfoList ;
    
    public List<OmcSchemaLifeCycleStateInfoVO> getStateInfoList(){
        return stateInfoList;
    }




    
    public void setStateInfoList(List<OmcSchemaLifeCycleStateInfoVO> stateInfoList){
        this.stateInfoList = stateInfoList;
    }




    public List<OmcSchemaSeperatedFormatVO> getSeperatedFormatList(){
        return seperatedFormatList;
    }



    
    public void setSeperatedFormatList(List<OmcSchemaSeperatedFormatVO> seperatedFormatList){
        this.seperatedFormatList = seperatedFormatList;
    }



    public List<OmcSchemaSeperatedClassVO> getSeperatedClassList(){
        return seperatedClassList;
    }


    
    public void setSeperatedClassList(List<OmcSchemaSeperatedClassVO> seperatedClassList){
        this.seperatedClassList = seperatedClassList;
    }


    public List<OmcSchemaSeperatedStateVO> getSeperatedStateList(){
        return seperatedStateList;
    }

    
    public void setSeperatedStateList(List<OmcSchemaSeperatedStateVO> seperatedStateList){
        this.seperatedStateList = seperatedStateList;
    }

    public String getSequenceRule(){
        return sequenceRule;
    }
    
    public String getDefaultFormat(){
        return defaultFormat;
    }
    
    public String getStateList(){
        return stateList;
    }
    
    public String getStoreName(){
        return storeName;
    }
    
    public String getAllStateFlag(){
        return allStateFlag;
    }
    
    public String getAppliedClassList(){
        return appliedClassList;
    }
    
    public String getDisplayedName(){
        return displayedName;
    }
    
    public String getAppliedFormatList(){
        return appliedFormatList;
    }
    
    public void setSequenceRule(String sequenceRule){
        this.sequenceRule = sequenceRule;
    }
    
    public void setDefaultFormat(String defaultFormat){
        this.defaultFormat = defaultFormat;
    }
    
    public void setStateList(String stateList){
        this.stateList = stateList;
    }
    
    public void setStoreName(String storeName){
        this.storeName = storeName;
    }
    
    public void setAllStateFlag(String allStateFlag){
        this.allStateFlag = allStateFlag;
    }
    
    public void setAppliedClassList(String appliedClassList){
        this.appliedClassList = appliedClassList;
    }
    
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }
    
    public void setAppliedFormatList(String appliedFormatList){
        this.appliedFormatList = appliedFormatList;
    }   
}
