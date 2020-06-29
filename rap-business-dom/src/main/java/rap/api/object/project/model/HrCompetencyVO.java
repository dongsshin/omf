/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HrCompetencyVO.java
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
public class HrCompetencyVO extends BusinessObjectMasterVO {
    private String        businessUnitCode                                  ;
    private String        competencyCode                                    ;
    private String        upperCompetencyCode                               ;
    private Integer       competencyLevel                                   ;
    private String        evalutionYn                                        = "N";
    private String        yyyy                                              ;
    private Integer       sorting                                           ;


    public void    setBusinessUnitCode(String businessUnitCode){
        this.businessUnitCode = businessUnitCode;
    }
    public void    setCompetencyCode(String competencyCode){
        this.competencyCode = competencyCode;
    }
    public void    setUpperCompetencyCode(String upperCompetencyCode){
        this.upperCompetencyCode = upperCompetencyCode;
    }
    public void    setCompetencyLevel(Integer competencyLevel){
        this.competencyLevel = competencyLevel;
    }
    public void    setEvalutionYn(String evalutionYn){
        this.evalutionYn = evalutionYn;
    }
    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setSorting(Integer sorting){
        this.sorting = sorting;
    }
    public String getBusinessUnitCode(){
        return businessUnitCode;
    }
    public String getCompetencyCode(){
        return competencyCode;
    }
    public String getUpperCompetencyCode(){
        return upperCompetencyCode;
    }
    public Integer getCompetencyLevel(){
        return competencyLevel;
    }
    public String getEvalutionYn(){
        return evalutionYn;
    }
    public String getYyyy(){
        return yyyy;
    }
    public Integer getSorting(){
        return sorting;
    }
}

