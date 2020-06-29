/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SchemaBusinessObjectClassVO.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2016. 06. 27. dongsik.shin Initial
 * ===========================================
 */
package com.rap.omc.schema.object.temp.model;
/**
 * <pre>
 * Class : SchemaBusinessObjectClassVO
 * Description : BusinessObject관리를 위한 VO.
 * </pre>
 * 
 * @author dongsik.shin
 */
public class SchemaBusinessObjectVO extends SchemaClassRootVO {
    private String  zschemaDefaultPolicy               ;
    private String  zschemaDisplayedName               ;
    private String  zschemaDisplyedInCombo             ;
    private String  zschemaIsWorkflowItem              ;
    private String  zschemaUrlForWorkflow              ;
    private String  zschemaIcon                        ;
    private String  zschemaIconSmall                   ;
    
    public String getZschemaDefaultPolicy(){
        return zschemaDefaultPolicy;
    }
    
    public String getZschemaDisplayedName(){
        return zschemaDisplayedName;
    }
    
    public String getZschemaDisplyedInCombo(){
        return zschemaDisplyedInCombo;
    }
    
    public String getZschemaIsWorkflowItem(){
        return zschemaIsWorkflowItem;
    }
    
    public String getZschemaUrlForWorkflow(){
        return zschemaUrlForWorkflow;
    }
    
    public String getZschemaIcon(){
        return zschemaIcon;
    }
    
    public String getZschemaIconSmall(){
        return zschemaIconSmall;
    }
    
    public void setZschemaDefaultPolicy(String zschemaDefaultPolicy){
        this.zschemaDefaultPolicy = zschemaDefaultPolicy;
    }
    
    public void setZschemaDisplayedName(String zschemaDisplayedName){
        this.zschemaDisplayedName = zschemaDisplayedName;
    }
    
    public void setZschemaDisplyedInCombo(String zschemaDisplyedInCombo){
        this.zschemaDisplyedInCombo = zschemaDisplyedInCombo;
    }
    
    public void setZschemaIsWorkflowItem(String zschemaIsWorkflowItem){
        this.zschemaIsWorkflowItem = zschemaIsWorkflowItem;
    }
    
    public void setZschemaUrlForWorkflow(String zschemaUrlForWorkflow){
        this.zschemaUrlForWorkflow = zschemaUrlForWorkflow;
    }
    
    public void setZschemaIcon(String zschemaIcon){
        this.zschemaIcon = zschemaIcon;
    }
    
    public void setZschemaIconSmall(String zschemaIconSmall){
        this.zschemaIconSmall = zschemaIconSmall;
    }
    @Override
    public String toString(){
        return "SchemaBusinessObjectVO [zschemaDefaultPolicy=" + zschemaDefaultPolicy + ", zschemaDisplayedName="
                + zschemaDisplayedName + ", zschemaDisplyedInCombo=" + zschemaDisplyedInCombo
                + ", zschemaIsWorkflowItem=" + zschemaIsWorkflowItem + ", zschemaUrlForWorkflow="
                + zschemaUrlForWorkflow + ", zschemaIcon=" + zschemaIcon + ", zschemaIconSmall=" + zschemaIconSmall
                + "]" + super.toString();
    }
}
