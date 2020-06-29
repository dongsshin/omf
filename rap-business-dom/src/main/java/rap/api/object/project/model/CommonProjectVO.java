/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CommonProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.ProjectsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class CommonProjectVO extends ProjectsVO {
    private String        commPrecedeGubun                                  ;
    private String        useYn                                              = "Y";
    private String        topDeptCode                                       ;
    private String        mcCategoryCode                                    ;
    private String        preCategoryCode                                   ;
    private String        preTaskGubunCode                                  ;
    private String        preTaskPeriodCode                                 ;
    private String        deptCode                                          ;
    private String        projectType                                       ;


    public void    setCommPrecedeGubun(String commPrecedeGubun){
        this.commPrecedeGubun = commPrecedeGubun;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setTopDeptCode(String topDeptCode){
        this.topDeptCode = topDeptCode;
    }
    public void    setMcCategoryCode(String mcCategoryCode){
        this.mcCategoryCode = mcCategoryCode;
    }
    public void    setPreCategoryCode(String preCategoryCode){
        this.preCategoryCode = preCategoryCode;
    }
    public void    setPreTaskGubunCode(String preTaskGubunCode){
        this.preTaskGubunCode = preTaskGubunCode;
    }
    public void    setPreTaskPeriodCode(String preTaskPeriodCode){
        this.preTaskPeriodCode = preTaskPeriodCode;
    }
    public void    setDeptCode(String deptCode){
        this.deptCode = deptCode;
    }
    public void    setProjectType(String projectType){
        this.projectType = projectType;
    }
    public String getCommPrecedeGubun(){
        return commPrecedeGubun;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getTopDeptCode(){
        return topDeptCode;
    }
    public String getMcCategoryCode(){
        return mcCategoryCode;
    }
    public String getPreCategoryCode(){
        return preCategoryCode;
    }
    public String getPreTaskGubunCode(){
        return preTaskGubunCode;
    }
    public String getPreTaskPeriodCode(){
        return preTaskPeriodCode;
    }
    public String getDeptCode(){
        return deptCode;
    }
    public String getProjectType(){
        return projectType;
    }
}

