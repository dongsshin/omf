/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasVistaTaskReportYearVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class HasVistaTaskReportYearVO extends BusinessRelationObjectVO {
    private String        yyyymm                                            ;
    private String        remark                                            ;
    private String        projectsignalremark                               ;
    private String        reportyn                                          ;


    public void    setYyyymm(String yyyymm){
        this.yyyymm = yyyymm;
    }
    public void    setRemark(String remark){
        this.remark = remark;
    }
    public void    setProjectsignalremark(String projectsignalremark){
        this.projectsignalremark = projectsignalremark;
    }
    public void    setReportyn(String reportyn){
        this.reportyn = reportyn;
    }
    public String getYyyymm(){
        return yyyymm;
    }
    public String getRemark(){
        return remark;
    }
    public String getProjectsignalremark(){
        return projectsignalremark;
    }
    public String getReportyn(){
        return reportyn;
    }
}

