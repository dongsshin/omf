/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ActivitySimpleChildVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2018. 9. 7.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.workflow.model;


/**
 * <pre>
 * Class : ActivitySimpleChildVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class ActivitySimpleChildVO {
    private String activityObid;
    private String activityName;
    
    /**
     * @param activityObid
     * @param activityName
     */
    public ActivitySimpleChildVO(String activityObid, String activityName) {
        super();
        this.activityObid = activityObid;
        this.activityName = activityName;
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
     * @return the activityName
     */
    public String getActivityName(){
        return activityName;
    }
    
    /**
     * 
     * 
     * @param activityName the activityName to set
     */
    public void setActivityName(String activityName){
        this.activityName = activityName;
    }
    
}
