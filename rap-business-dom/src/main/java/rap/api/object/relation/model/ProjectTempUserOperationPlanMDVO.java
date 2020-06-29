/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectTempUserOperationPlanMDVO.java
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
public class ProjectTempUserOperationPlanMDVO extends BusinessRelationObjectVO {
    private String        yyyy                                              ;
    private String        yyyyMm                                            ;
    private Date          dd                                                ;
    private Float         mm                                                ;
    private String        mailComment                                       ;
    private String        mainCompetency                                    ;
    private String        middleCompetency                                  ;
    private String        subCompetency                                     ;
    private Float         evaluationGrade                                   ;


    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setYyyyMm(String yyyyMm){
        this.yyyyMm = yyyyMm;
    }
    public void    setDd(Date dd){
        this.dd = dd;
    }
    public void    setDd(String    dd){
        this.dd = this.omcConvertStr2Date(dd);
    }
    public void    setMm(Float mm){
        this.mm = mm;
    }
    public void    setMailComment(String mailComment){
        this.mailComment = mailComment;
    }
    public void    setMainCompetency(String mainCompetency){
        this.mainCompetency = mainCompetency;
    }
    public void    setMiddleCompetency(String middleCompetency){
        this.middleCompetency = middleCompetency;
    }
    public void    setSubCompetency(String subCompetency){
        this.subCompetency = subCompetency;
    }
    public void    setEvaluationGrade(Float evaluationGrade){
        this.evaluationGrade = evaluationGrade;
    }
    public String getYyyy(){
        return yyyy;
    }
    public String getYyyyMm(){
        return yyyyMm;
    }
    public Date getDd(){
        return dd;
    }
    public Float getMm(){
        return mm;
    }
    public String getMailComment(){
        return mailComment;
    }
    public String getMainCompetency(){
        return mainCompetency;
    }
    public String getMiddleCompetency(){
        return middleCompetency;
    }
    public String getSubCompetency(){
        return subCompetency;
    }
    public Float getEvaluationGrade(){
        return evaluationGrade;
    }
}

