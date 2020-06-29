/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectReviewVO.java
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
public class ProjectReviewVO extends BusinessRelationObjectVO {
    private String        reviewCode                                        ;
    private String        reviewStatusCode                                  ;
    private Integer       selfIndex                                         ;
    private String        selfOperatorEmpNo                                 ;
    private Date          selfAssessmentDate                                ;
    private Integer       finalIndex                                        ;
    private String        finalOperatorEmpNo                                ;
    private Date          finalAssessmentDate                               ;


    public void    setReviewCode(String reviewCode){
        this.reviewCode = reviewCode;
    }
    public void    setReviewStatusCode(String reviewStatusCode){
        this.reviewStatusCode = reviewStatusCode;
    }
    public void    setSelfIndex(Integer selfIndex){
        this.selfIndex = selfIndex;
    }
    public void    setSelfOperatorEmpNo(String selfOperatorEmpNo){
        this.selfOperatorEmpNo = selfOperatorEmpNo;
    }
    public void    setSelfAssessmentDate(Date selfAssessmentDate){
        this.selfAssessmentDate = selfAssessmentDate;
    }
    public void    setSelfAssessmentDate(String    selfAssessmentDate){
        this.selfAssessmentDate = this.omcConvertStr2Date(selfAssessmentDate);
    }
    public void    setFinalIndex(Integer finalIndex){
        this.finalIndex = finalIndex;
    }
    public void    setFinalOperatorEmpNo(String finalOperatorEmpNo){
        this.finalOperatorEmpNo = finalOperatorEmpNo;
    }
    public void    setFinalAssessmentDate(Date finalAssessmentDate){
        this.finalAssessmentDate = finalAssessmentDate;
    }
    public void    setFinalAssessmentDate(String    finalAssessmentDate){
        this.finalAssessmentDate = this.omcConvertStr2Date(finalAssessmentDate);
    }
    public String getReviewCode(){
        return reviewCode;
    }
    public String getReviewStatusCode(){
        return reviewStatusCode;
    }
    public Integer getSelfIndex(){
        return selfIndex;
    }
    public String getSelfOperatorEmpNo(){
        return selfOperatorEmpNo;
    }
    public Date getSelfAssessmentDate(){
        return selfAssessmentDate;
    }
    public Integer getFinalIndex(){
        return finalIndex;
    }
    public String getFinalOperatorEmpNo(){
        return finalOperatorEmpNo;
    }
    public Date getFinalAssessmentDate(){
        return finalAssessmentDate;
    }
}

