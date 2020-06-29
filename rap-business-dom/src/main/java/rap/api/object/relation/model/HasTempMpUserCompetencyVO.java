/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasTempMpUserCompetencyVO.java
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
public class HasTempMpUserCompetencyVO extends BusinessRelationObjectVO {
    private Integer       sorting                                           ;
    private String        mainYn                                            ;
    private Float         evaluationGrade                                   ;


    public void    setSorting(Integer sorting){
        this.sorting = sorting;
    }
    public void    setMainYn(String mainYn){
        this.mainYn = mainYn;
    }
    public void    setEvaluationGrade(Float evaluationGrade){
        this.evaluationGrade = evaluationGrade;
    }
    public Integer getSorting(){
        return sorting;
    }
    public String getMainYn(){
        return mainYn;
    }
    public Float getEvaluationGrade(){
        return evaluationGrade;
    }
}

