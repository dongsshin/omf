/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSTemplateMasterVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class WBSTemplateMasterVO extends BusinessObjectVO {
    private String        divisionUnit                                      ;
    private String        developmentType                                   ;
    private String        projectLifeCycle                                  ;
    private String        appliedGradeList                                   = "None";
    private String        isPublished                                        = "N";


    public void    setDivisionUnit(String divisionUnit){
        this.divisionUnit = divisionUnit;
    }
    public void    setDevelopmentType(String developmentType){
        this.developmentType = developmentType;
    }
    public void    setProjectLifeCycle(String projectLifeCycle){
        this.projectLifeCycle = projectLifeCycle;
    }
    public void    setAppliedGradeList(String appliedGradeList){
        this.appliedGradeList = appliedGradeList;
    }
    public void    setIsPublished(String isPublished){
        this.isPublished = isPublished;
    }
    public String getDivisionUnit(){
        return divisionUnit;
    }
    public String getDevelopmentType(){
        return developmentType;
    }
    public String getProjectLifeCycle(){
        return projectLifeCycle;
    }
    public String getAppliedGradeList(){
        return appliedGradeList;
    }
    public String getIsPublished(){
        return isPublished;
    }
}

