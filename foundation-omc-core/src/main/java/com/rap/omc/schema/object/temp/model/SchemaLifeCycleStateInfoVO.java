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
public class SchemaLifeCycleStateInfoVO {
    private Integer zschemaSequence                    ;
    private String  zschemaComments                    ;
    private String  zschemaNames                       ;
    private String  zschemaRouteCompleteAction         ;
    private String  zschemaDefaultRoutePurpose         ;
    private String  zschemaAutoStartForReject          ;
    private String  zschemaStopForReject               ;
    private String  zschemaInboxAutoStop               ;
    private Integer zschemaDateOffset                  ;
    private String  zschemaParellelProceeRule          ;
    private String  obid                               ;
    private String  fromObid                           ;
    
    public Integer getZschemaSequence(){
        return zschemaSequence;
    }
    
    public String getZschemaComments(){
        return zschemaComments;
    }
    
    public String getZschemaNames(){
        return zschemaNames;
    }
    
    public String getZschemaRouteCompleteAction(){
        return zschemaRouteCompleteAction;
    }
    
    public String getZschemaDefaultRoutePurpose(){
        return zschemaDefaultRoutePurpose;
    }
    
    public String getZschemaAutoStartForReject(){
        return zschemaAutoStartForReject;
    }
    
    public String getZschemaStopForReject(){
        return zschemaStopForReject;
    }
    
    public String getZschemaInboxAutoStop(){
        return zschemaInboxAutoStop;
    }
    
    public Integer getZschemaDateOffset(){
        return zschemaDateOffset;
    }
    
    public String getZschemaParellelProceeRule(){
        return zschemaParellelProceeRule;
    }
    
    public String getObid(){
        return obid;
    }
    
    public String getFromObid(){
        return fromObid;
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
    
    public void setZschemaRouteCompleteAction(String zschemaRouteCompleteAction){
        this.zschemaRouteCompleteAction = zschemaRouteCompleteAction;
    }
    
    public void setZschemaDefaultRoutePurpose(String zschemaDefaultRoutePurpose){
        this.zschemaDefaultRoutePurpose = zschemaDefaultRoutePurpose;
    }
    
    public void setZschemaAutoStartForReject(String zschemaAutoStartForReject){
        this.zschemaAutoStartForReject = zschemaAutoStartForReject;
    }
    
    public void setZschemaStopForReject(String zschemaStopForReject){
        this.zschemaStopForReject = zschemaStopForReject;
    }
    
    public void setZschemaInboxAutoStop(String zschemaInboxAutoStop){
        this.zschemaInboxAutoStop = zschemaInboxAutoStop;
    }
    
    public void setZschemaDateOffset(Integer zschemaDateOffset){
        this.zschemaDateOffset = zschemaDateOffset;
    }
    
    public void setZschemaParellelProceeRule(String zschemaParellelProceeRule){
        this.zschemaParellelProceeRule = zschemaParellelProceeRule;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public void setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    
}
