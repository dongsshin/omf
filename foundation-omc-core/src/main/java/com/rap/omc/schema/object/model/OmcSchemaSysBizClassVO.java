/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysBizClassVO.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import java.util.Date;

import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaSysBizClassVO
 * Description : TODO
 * </pre>
 *
 * @author s_dongsshin
 */
public class OmcSchemaSysBizClassVO extends OmcSchemaSysRootVO{
    private String displayedName;
    private String displayedNameKr;
    private String namesParent;

    private String parentObid;
    private String javaPackage;
    private int    classLevel;
    private String uniqueStr;
    private String uniqueStrParent;

    private String defaultPolicy;
    private String workflowUrl;
    private String classIcon;
    private String classIconSmall;

    private boolean isInstantiable = false;
    private boolean applyWorkflow  = false;
    private boolean comboDisplay   = false;

    private String isInstantiableStr;
    private String applyWorkflowStr;
    private String comboDisplayStr;
    
    private int isReferenced = 1;
    
    public int getIsReferenced(){
        return isReferenced;
    }
    
    public void setIsReferenced(int isReferenced){
        this.isReferenced = isReferenced;
    }

    public void setFlags(long flags){
        super.setFlags(flags);
        if(Bit.isInclude(flags, OmcSystemConstants.BUSINESS_FLAG_Instantiable  )) this.isInstantiable = true;
        if(Bit.isInclude(flags, OmcSystemConstants.BUSINESS_FLAG_Workflow)) this.applyWorkflow = true;
        if(Bit.isInclude(flags, OmcSystemConstants.BUSINESS_FLAG_ComboDisplay)) this.comboDisplay = true;
    }

    public String getDisplayedNameKr(){
        return displayedNameKr;
    }

    public void setDisplayedNameKr(String displayedNameKr){
        this.displayedNameKr = displayedNameKr;
    }


    public String getNamesParent(){
        return namesParent;
    }


    public void setNamesParent(String namesParent){
        this.namesParent = namesParent;
    }


    public String getDisplayedName(){
        return displayedName;
    }


    public String getParentObid(){
        return parentObid;
    }


    public String getJavaPackage(){
        return javaPackage;
    }



    public int getClassLevel(){
        return classLevel;
    }


    public String getUniqueStr(){
        return uniqueStr;
    }


    public String getUniqueStrParent(){
        return uniqueStrParent;
    }
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }


    public void setParentObid(String parentObid){
        this.parentObid = parentObid;
    }


    public void setJavaPackage(String javaPackage){
        this.javaPackage = javaPackage;
    }


    public void setClassLevel(int classLevel){
        this.classLevel = classLevel;
    }


    public void setUniqueStr(String uniqueStr){
        this.uniqueStr = uniqueStr;
    }


    public void setUniqueStrParent(String uniqueStrParent){
        this.uniqueStrParent = uniqueStrParent;
    }

    /**
     *
     */
    public String getDefaultPolicy(){
        return defaultPolicy;
    }

    public String getWorkflowUrl(){
        return workflowUrl;
    }

    public void setDefaultPolicy(String defaultPolicy){
        this.defaultPolicy = defaultPolicy;
    }

    public void setWorkflowUrl(String workflowUrl){
        this.workflowUrl = workflowUrl;
    }


    public String getClassIcon(){
        return classIcon;
    }


    public String getClassIconSmall(){
        return classIconSmall;
    }


    public boolean getApplyWorkflow(){
        return applyWorkflow;
    }


    public boolean getComboDisplay(){
        return comboDisplay;
    }


    public void setClassIcon(String classIcon){
        this.classIcon = classIcon;
    }
    public void setClassIconSmall(String classIconSmall){
        this.classIconSmall = classIconSmall;
    }
    public void setApplyWorkflow(boolean applyWorkflow){
        this.applyWorkflow = applyWorkflow;
    }
    public void setApplyWorkflow(String applyWorkflow){
        if(applyWorkflow.equals("Y")) {
            this.applyWorkflow = true;
        }else{
            this.applyWorkflow = false;
        }
    }
    public void setComboDisplay(boolean comboDisplay){
        this.comboDisplay = comboDisplay;
    }
    public void setComboDisplay(String comboDisplay){
        if(comboDisplay.equals("Y")) {
            this.comboDisplay = true;
        }else{
            this.comboDisplay = false;
        }
    }
    public boolean getIsInstantiable(){
        return isInstantiable;
    }
    public void setIsInstantiable(boolean isInstantiable){
        this.isInstantiable = isInstantiable;
    }
    public void setIsInstantiable(String isInstantiable){
        if("Y".equals(isInstantiable)) {
            this.isInstantiable = true;
        }else{
            this.isInstantiable = false;
        }
    }

    public String getIsInstantiableStr(){
        return isInstantiableStr;
    }


    public String getApplyWorkflowStr(){
        return applyWorkflowStr;
    }


    public String getComboDisplayStr(){
        return comboDisplayStr;
    }

    public void setIsInstantiableStr(String isInstantiableStr){
        this.isInstantiableStr = isInstantiableStr;
        this.setIsInstantiable(isInstantiableStr);
    }

    public void setApplyWorkflowStr(String applyWorkflowStr){
        this.applyWorkflowStr = applyWorkflowStr;
        this.setApplyWorkflow(applyWorkflowStr);
    }


    public void setComboDisplayStr(String comboDisplayStr){
        this.comboDisplayStr = comboDisplayStr;
        this.setComboDisplay(comboDisplayStr);
    }

    @Override
    public String toString(){
        return "OmcSchemaSysBizClassVO ["
                + "names = " + this.getNames() + ", "
                + "sequences = " + this.getSequences() + ", "
                + "changeComments = " + this.getChangeComments() + ", "
                + "namesParent = " + namesParent + ", "
                + "defaultPolicy = " + defaultPolicy + ", "
                + "isInstantiableStr = " + isInstantiableStr + ", "
                + "isInstantiable = " + isInstantiable + ", "
                + "javaPackage = " + javaPackage + ", "
                + "displayedName = " + displayedName + ", "
                + "comboDisplayStr = " + comboDisplayStr + ", "
                + "comboDisplay = " + comboDisplay + ", "
                + "applyWorkflowStr = " + applyWorkflowStr + ", "
                + "applyWorkflow = " + applyWorkflow + ", "
                + "workflowUrl = " + workflowUrl + ", "
                + "moduleName = " + this.getModuleName() + ", "
                + "remarks = " + this.getRemarks() + ", "
                + "classIcon = " + classIcon + ", "
                + "classIconSmall = " + classIconSmall + ", "
                + "owners = " + this.getOwners();
    }
}
