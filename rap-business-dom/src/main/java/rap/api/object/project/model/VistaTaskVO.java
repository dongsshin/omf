/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : VistaTaskVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class VistaTaskVO extends BusinessObjectMasterVO {
    private String        andonGrade                                        ;
    private String        reportYn                                          ;
    private String        kpi                                               ;
    private String        divisionname                                      ;


    public void    setAndonGrade(String andonGrade){
        this.andonGrade = andonGrade;
    }
    public void    setReportYn(String reportYn){
        this.reportYn = reportYn;
    }
    public void    setKpi(String kpi){
        this.kpi = kpi;
    }
    public void    setDivisionname(String divisionname){
        this.divisionname = divisionname;
    }
    public String getAndonGrade(){
        return andonGrade;
    }
    public String getReportYn(){
        return reportYn;
    }
    public String getKpi(){
        return kpi;
    }
    public String getDivisionname(){
        return divisionname;
    }
}

