/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ActivityValidationResultVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 20.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.temp.model;


/**
 * <pre>
 * Class : ActivityValidationResultVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class ActivityValidationResultVO {
    
    public static enum RESULTCODE {
        success, warning, error, systemError
    }
    private RESULTCODE errorLevel;
    private String errorItem;
    private String errorContents;
    private String actionGuide;
    
    public ActivityValidationResultVO(RESULTCODE errorLevel, String errorItem, String errorContents,
            String actionGuide) {
        super();
        this.errorLevel = errorLevel;
        this.errorItem = errorItem;
        this.errorContents = errorContents;
        this.actionGuide = actionGuide;
    }
    
    public RESULTCODE getErrorLevel(){
        return errorLevel;
    }

    public String getErrorItem(){
        return errorItem;
    }
    
    public void setErrorItem(String errorItem){
        this.errorItem = errorItem;
    }
    
    public String getErrorContents(){
        return errorContents;
    }
    
    public void setErrorContents(String errorContents){
        this.errorContents = errorContents;
    }
    
    public String getActionGuide(){
        return actionGuide;
    }
    
    public void setActionGuide(String actionGuide){
        this.actionGuide = actionGuide;
    }
}
