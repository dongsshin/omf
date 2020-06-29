/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSItemsParamtersVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 21.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import com.rap.omc.api.object.model.BaseModel;

/**
 * <pre>
 * Class : WBSItemsParamtersVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class WBSItemsParamtersVO extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 8721450668080720537L;
    
    private String projectObid; 
    private String projectName;
    private String scheduleObid;
    private String activityObid;
    private String phaseCode;
    private String phaseTitles;
    private String phaseCodeSystem;
    private String phaseTitlesSystem;
    private String inboxTaskObid;
    
    /**
     * 
     * 
     * @return the projectObid
     */
    public String getProjectObid(){
        return projectObid;
    }
    
    /**
     * 
     * 
     * @param projectObid the projectObid to set
     */
    public void setProjectObid(String projectObid){
        this.projectObid = projectObid;
    }
    
    /**
     * 
     * 
     * @return the projectName
     */
    public String getProjectName(){
        return projectName;
    }
    
    /**
     * 
     * 
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }
    
    /**
     * 
     * 
     * @return the scheduleObid
     */
    public String getScheduleObid(){
        return scheduleObid;
    }
    
    /**
     * 
     * 
     * @param scheduleObid the scheduleObid to set
     */
    public void setScheduleObid(String scheduleObid){
        this.scheduleObid = scheduleObid;
    }
    
    /**
     * 
     * 
     * @return the activityObid
     */
    public String getActivityObid(){
        return activityObid;
    }
    
    /**
     * 
     * 
     * @param activityObid the activityObid to set
     */
    public void setActivityObid(String activityObid){
        this.activityObid = activityObid;
    }
    
    /**
     * 
     * 
     * @return the phaseCode
     */
    public String getPhaseCode(){
        return phaseCode;
    }
    
    /**
     * 
     * 
     * @param phaseCode the phaseCode to set
     */
    public void setPhaseCode(String phaseCode){
        this.phaseCode = phaseCode;
    }
    
    /**
     * 
     * 
     * @return the phaseTitles
     */
    public String getPhaseTitles(){
        return phaseTitles;
    }
    
    /**
     * 
     * 
     * @param phaseTitles the phaseTitles to set
     */
    public void setPhaseTitles(String phaseTitles){
        this.phaseTitles = phaseTitles;
    }
    
    /**
     * 
     * 
     * @return the phaseCodeSystem
     */
    public String getPhaseCodeSystem(){
        return phaseCodeSystem;
    }
    
    /**
     * 
     * 
     * @param phaseCodeSystem the phaseCodeSystem to set
     */
    public void setPhaseCodeSystem(String phaseCodeSystem){
        this.phaseCodeSystem = phaseCodeSystem;
    }
    
    /**
     * 
     * 
     * @return the phaseTitlesSystem
     */
    public String getPhaseTitlesSystem(){
        return phaseTitlesSystem;
    }
    
    /**
     * 
     * 
     * @param phaseTitlesSystem the phaseTitlesSystem to set
     */
    public void setPhaseTitlesSystem(String phaseTitlesSystem){
        this.phaseTitlesSystem = phaseTitlesSystem;
    }
    
    /**
     * 
     * 
     * @return the inboxTaskObid
     */
    public String getInboxTaskObid(){
        return inboxTaskObid;
    }
    
    /**
     * 
     * 
     * @param inboxTaskObid the inboxTaskObid to set
     */
    public void setInboxTaskObid(String inboxTaskObid){
        this.inboxTaskObid = inboxTaskObid;
    }

    /**
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "WBSItemsParamtersVO [projectObid=" + projectObid + ", projectName=" + projectName + ", scheduleObid="
                + scheduleObid + ", activityObid=" + activityObid + ", phaseCode=" + phaseCode + ", phaseTitles="
                + phaseTitles + ", phaseCodeSystem=" + phaseCodeSystem + ", phaseTitlesSystem=" + phaseTitlesSystem
                + ", inboxTaskObid=" + inboxTaskObid + "]";
    }
}
