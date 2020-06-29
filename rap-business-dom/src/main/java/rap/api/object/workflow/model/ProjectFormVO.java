/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectFormVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectFormVO extends BusinessObjectMasterVO {
    private String        requestSubType                                    ;
    private String        projectType                                       ;
    private String        productGroupCode                                  ;
    private String        processType                                       ;
    private String        gradeCode                                         ;
    private String        devSiteCode                                       ;
    private String        activityDocument                                  ;


    public void    setRequestSubType(String requestSubType){
        this.requestSubType = requestSubType;
    }
    public void    setProjectType(String projectType){
        this.projectType = projectType;
    }
    public void    setProductGroupCode(String productGroupCode){
        this.productGroupCode = productGroupCode;
    }
    public void    setProcessType(String processType){
        this.processType = processType;
    }
    public void    setGradeCode(String gradeCode){
        this.gradeCode = gradeCode;
    }
    public void    setDevSiteCode(String devSiteCode){
        this.devSiteCode = devSiteCode;
    }
    public void    setActivityDocument(String activityDocument){
        this.activityDocument = activityDocument;
    }
    public String getRequestSubType(){
        return requestSubType;
    }
    public String getProjectType(){
        return projectType;
    }
    public String getProductGroupCode(){
        return productGroupCode;
    }
    public String getProcessType(){
        return processType;
    }
    public String getGradeCode(){
        return gradeCode;
    }
    public String getDevSiteCode(){
        return devSiteCode;
    }
    public String getActivityDocument(){
        return activityDocument;
    }
}

