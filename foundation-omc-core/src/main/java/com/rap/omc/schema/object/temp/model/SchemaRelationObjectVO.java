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
public class SchemaRelationObjectVO extends SchemaClassRootVO{
    private String  zschemaAllowDuplicate              ;
    private String  zschemaFromType                    ;
    private String  zschemaToType                      ;
    private String  zschemaFromRelatonship             ;
    private String  zschemaToRelatonship               ;
    private String  zschemaRevisionRuleFrom            ;
    private String  zschemaRevisionRuleTo              ;
    private String  zschemaPcardinalityFrom            ;
    private String  zschemaPcardinalityTo              ;
    
    public String getZschemaAllowDuplicate(){
        return zschemaAllowDuplicate;
    }
    
    public String getZschemaFromType(){
        return zschemaFromType;
    }
    
    public String getZschemaToType(){
        return zschemaToType;
    }
    
    public String getZschemaFromRelatonship(){
        return zschemaFromRelatonship;
    }
    
    public String getZschemaToRelatonship(){
        return zschemaToRelatonship;
    }
    
    public String getZschemaRevisionRuleFrom(){
        return zschemaRevisionRuleFrom;
    }
    
    public String getZschemaRevisionRuleTo(){
        return zschemaRevisionRuleTo;
    }
    
    public String getZschemaPcardinalityFrom(){
        return zschemaPcardinalityFrom;
    }
    
    public String getZschemaPcardinalityTo(){
        return zschemaPcardinalityTo;
    }
    
    public void setZschemaAllowDuplicate(String zschemaAllowDuplicate){
        this.zschemaAllowDuplicate = zschemaAllowDuplicate;
    }
    
    public void setZschemaFromType(String zschemaFromType){
        this.zschemaFromType = zschemaFromType;
    }
    
    public void setZschemaToType(String zschemaToType){
        this.zschemaToType = zschemaToType;
    }
    
    public void setZschemaFromRelatonship(String zschemaFromRelatonship){
        this.zschemaFromRelatonship = zschemaFromRelatonship;
    }
    
    public void setZschemaToRelatonship(String zschemaToRelatonship){
        this.zschemaToRelatonship = zschemaToRelatonship;
    }
    
    public void setZschemaRevisionRuleFrom(String zschemaRevisionRuleFrom){
        this.zschemaRevisionRuleFrom = zschemaRevisionRuleFrom;
    }
    
    public void setZschemaRevisionRuleTo(String zschemaRevisionRuleTo){
        this.zschemaRevisionRuleTo = zschemaRevisionRuleTo;
    }
    
    public void setZschemaPcardinalityFrom(String zschemaPcardinalityFrom){
        this.zschemaPcardinalityFrom = zschemaPcardinalityFrom;
    }
    
    public void setZschemaPcardinalityTo(String zschemaPcardinalityTo){
        this.zschemaPcardinalityTo = zschemaPcardinalityTo;
    }
    @Override
    public String toString(){
        return "SchemaRelationObjectVO [zschemaAllowDuplicate=" + zschemaAllowDuplicate + ", zschemaFromType="
                + zschemaFromType + ", zschemaToType=" + zschemaToType + ", zschemaFromRelatonship="
                + zschemaFromRelatonship + ", zschemaToRelatonship=" + zschemaToRelatonship
                + ", zschemaRevisionRuleFrom=" + zschemaRevisionRuleFrom + ", zschemaRevisionRuleTo="
                + zschemaRevisionRuleTo + ", zschemaPcardinalityFrom=" + zschemaPcardinalityFrom
                + ", zschemaPcardinalityTo=" + zschemaPcardinalityTo + "]" + super.toString();
    }
    
}
