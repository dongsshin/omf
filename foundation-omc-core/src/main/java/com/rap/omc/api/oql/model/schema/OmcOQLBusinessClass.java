/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLBusinessClass.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 8. 10.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model.schema;

import java.util.List;

import com.rap.omc.api.util.PropertyUtil;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcOQLBusinessClass
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLBusinessClass extends OmcOQLClassRoot {
    private String defaultLifeCycle;
    private String workflowUrl;
    private String iconUrl;
    private String iconUrlSmall;

    /**
     * @param defaultLifeCycle
     * @param workflowUrl
     * @param iconUrl
     * @param iconUrlSmall
     */
    public OmcOQLBusinessClass(String defaultLifeCycle, String workflowUrl, String iconUrl, String iconUrlSmall) {
        super();
        this.defaultLifeCycle = defaultLifeCycle;
        this.workflowUrl = workflowUrl;
        this.iconUrl = iconUrl;
        this.iconUrlSmall = iconUrlSmall;
    }

    /**
     * 
     */
    public OmcOQLBusinessClass() {
        super();
        // TODO Auto-generated constructor stub
    }
    public boolean isRevisible()
    {
        if((this.getClassInfoFlags() & OmcSystemConstants.BUSINESS_FLAG_Revisible) == OmcSystemConstants.BUSINESS_FLAG_Revisible) return(true);
        return(false);
    }
    public boolean isWorkflow()
    {
        if((this.getClassInfoFlags() & OmcSystemConstants.BUSINESS_FLAG_Workflow) == OmcSystemConstants.BUSINESS_FLAG_Workflow) return(true);
        return(false);
    }
    public boolean isComboDisplay()
    {
        if((this.getClassInfoFlags() & OmcSystemConstants.BUSINESS_FLAG_ComboDisplay) == OmcSystemConstants.BUSINESS_FLAG_ComboDisplay) return(true);
        return(false);
    }
    public String getDefaultLifeCycle(){
        return defaultLifeCycle;
    }

    public String getWorkflowUrl(){
        return workflowUrl;
    }
    
    public String getIconUrl(){
        return iconUrl;
    }
    
    public String getIconUrlSmall(){
        return iconUrlSmall;
    }
    
    public void setDefaultLifeCycle(String defaultLifeCycle){
        this.defaultLifeCycle = defaultLifeCycle;
    }
    
    public void setWorkflowUrl(String workflowUrl){
        this.workflowUrl = workflowUrl;
    }
    
    public void setIconUrl(String iconUrl){
        this.iconUrl = iconUrl;
    }
    
    public void setIconUrlSmall(String iconUrlSmall){
        this.iconUrlSmall = iconUrlSmall;
    }
}
