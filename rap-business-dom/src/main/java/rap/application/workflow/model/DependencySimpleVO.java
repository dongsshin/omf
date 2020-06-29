/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : DependencySimpleVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2018. 9. 7.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.workflow.model;

/**
 * <pre>
 * Class : DependencySimpleVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class DependencySimpleVO {
    private String dependencyType;
    private String obid;
    private String activityName;
    
    /**
     * @param dependencyType
     * @param obid
     * @param activityName
     */
    public DependencySimpleVO(String dependencyType, String obid, String activityName) {
        super();
        this.dependencyType = dependencyType;
        this.obid = obid;
        this.activityName = activityName;
    }

    /**
     * 
     * 
     * @return the dependencyType
     */
    public String getDependencyType(){
        return dependencyType;
    }
    
    /**
     * 
     * 
     * @param dependencyType the dependencyType to set
     */
    public void setDependencyType(String dependencyType){
        this.dependencyType = dependencyType;
    }
    
    /**
     * 
     * 
     * @return the obid
     */
    public String getObid(){
        return obid;
    }
    
    /**
     * 
     * 
     * @param obid the obid to set
     */
    public void setObid(String obid){
        this.obid = obid;
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
