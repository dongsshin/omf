/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : LifeCycleVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 4.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.temp.model;

import java.util.Date;


/**
 * <pre>
 * Class : LifeCycleVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class SchemaLifeCycleVO {
    private Integer zschemaSequence                    ;
    private String  zschemaComments                    ;
    private String  zschemaNames                       ;
    private String  zschemaSequenceRule                ;
    private String  zschemaDefaultFormat               ;
    private String  zschemaStateList                   ;
    private String  zschemaStore                       ;
    private String  zschemaIsAllStateDefined           ;
    private String  zschemaAppliedClassList            ;
    private String  zschemaAppliedFormatList           ;
    private String  zschemaDisplayedName               ;
    private String  zschemaRemarks                     ;
    private String  zschemaOwner                       ;
    private String  obid                               ;
    
    public Integer getZschemaSequence(){
        return zschemaSequence;
    }
    
    public String getZschemaComments(){
        return zschemaComments;
    }
    
    public String getZschemaNames(){
        return zschemaNames;
    }
    
    public String getZschemaSequenceRule(){
        return zschemaSequenceRule;
    }
    
    public String getZschemaDefaultFormat(){
        return zschemaDefaultFormat;
    }
    
    public String getZschemaStateList(){
        return zschemaStateList;
    }
    
    public String getZschemaStore(){
        return zschemaStore;
    }
    
    public String getZschemaIsAllStateDefined(){
        return zschemaIsAllStateDefined;
    }
    
    public String getZschemaAppliedClassList(){
        return zschemaAppliedClassList;
    }
    
    public String getZschemaAppliedFormatList(){
        return zschemaAppliedFormatList;
    }
    
    public String getZschemaDisplayedName(){
        return zschemaDisplayedName;
    }
    
    public String getZschemaRemarks(){
        return zschemaRemarks;
    }
    
    public String getZschemaOwner(){
        return zschemaOwner;
    }
    
    public String getObid(){
        return obid;
    }
    
    public void setZschemaSequence(Integer zschemaSequence){
        this.zschemaSequence = zschemaSequence;
    }
    
    public void setZschemaComments(String zschemaComments){
        this.zschemaComments = zschemaComments;
    }
    
    public void setZschemaNames(String zschemaNames){
        this.zschemaNames = zschemaNames;
    }
    
    public void setZschemaSequenceRule(String zschemaSequenceRule){
        this.zschemaSequenceRule = zschemaSequenceRule;
    }
    
    public void setZschemaDefaultFormat(String zschemaDefaultFormat){
        this.zschemaDefaultFormat = zschemaDefaultFormat;
    }
    
    public void setZschemaStateList(String zschemaStateList){
        this.zschemaStateList = zschemaStateList;
    }
    
    public void setZschemaStore(String zschemaStore){
        this.zschemaStore = zschemaStore;
    }
    
    public void setZschemaIsAllStateDefined(String zschemaIsAllStateDefined){
        this.zschemaIsAllStateDefined = zschemaIsAllStateDefined;
    }
    
    public void setZschemaAppliedClassList(String zschemaAppliedClassList){
        this.zschemaAppliedClassList = zschemaAppliedClassList;
    }
    
    public void setZschemaAppliedFormatList(String zschemaAppliedFormatList){
        this.zschemaAppliedFormatList = zschemaAppliedFormatList;
    }
    
    public void setZschemaDisplayedName(String zschemaDisplayedName){
        this.zschemaDisplayedName = zschemaDisplayedName;
    }
    
    public void setZschemaRemarks(String zschemaRemarks){
        this.zschemaRemarks = zschemaRemarks;
    }
    
    public void setZschemaOwner(String zschemaOwner){
        this.zschemaOwner = zschemaOwner;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
}
