/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ActivityNameVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 8. 3.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.util.model;


/**
 * <pre>
 * Class : ActivityNameVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class ActivityNameVO {
    private String        activityNameEng;
    private String        activityNameKor;
    private String        activityNameChi;
    private String        dependencyType ;
    
    public ActivityNameVO(String activityNameEng, String activityNameKor, String activityNameChi,
            String dependencyType) {
        super();
        this.activityNameEng = activityNameEng;
        this.activityNameKor = activityNameKor;
        this.activityNameChi = activityNameChi;
        this.dependencyType = dependencyType;
    }

    public String getActivityNameEng(){
        return activityNameEng;
    }
    
    public void setActivityNameEng(String activityNameEng){
        this.activityNameEng = activityNameEng;
    }
    
    public String getActivityNameKor(){
        return activityNameKor;
    }
    
    public void setActivityNameKor(String activityNameKor){
        this.activityNameKor = activityNameKor;
    }
    
    public String getActivityNameChi(){
        return activityNameChi;
    }
    
    public void setActivityNameChi(String activityNameChi){
        this.activityNameChi = activityNameChi;
    }
    
    public String getDependencyType(){
        return dependencyType;
    }
    
    public void setDependencyType(String dependencyType){
        this.dependencyType = dependencyType;
    }
    
}
