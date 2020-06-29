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
public class SchemaLifeCycleBranchVO {
    private Integer zschemaSequence                    ;
    private String  zschemaComments                    ;
    private String  zschemaLifeCycleName               ;
    private String  zschemaBranchName                  ;
    private String  zschemaBrach                       ;
    private String  zschemaOwner                       ;
    private String  obid                               ;
    
    public Integer getZschemaSequence(){
        return zschemaSequence;
    }
    
    public String getZschemaComments(){
        return zschemaComments;
    }
    
    public String getZschemaLifeCycleName(){
        return zschemaLifeCycleName;
    }
    
    public String getZschemaBranchName(){
        return zschemaBranchName;
    }
    
    public String getZschemaBrach(){
        return zschemaBrach;
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
    
    public void setZschemaLifeCycleName(String zschemaLifeCycleName){
        this.zschemaLifeCycleName = zschemaLifeCycleName;
    }
    
    public void setZschemaBranchName(String zschemaBranchName){
        this.zschemaBranchName = zschemaBranchName;
    }
    
    public void setZschemaBrach(String zschemaBrach){
        this.zschemaBrach = zschemaBrach;
    }
    
    public void setZschemaOwner(String zschemaOwner){
        this.zschemaOwner = zschemaOwner;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    
}
