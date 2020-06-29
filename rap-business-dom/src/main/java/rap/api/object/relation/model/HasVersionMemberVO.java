/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasVersionMemberVO.java
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
public class HasVersionMemberVO extends BusinessRelationObjectVO {
    private String        titleCode                                         ;
    private String        titleName                                         ;
    private String        gradeCode                                         ;
    private String        gradeName                                         ;
    private Integer       gradeSeniority                                    ;
    private Date          hireDate                                          ;
    private Date          retiredDate                                       ;


    public void    setTitleCode(String titleCode){
        this.titleCode = titleCode;
    }
    public void    setTitleName(String titleName){
        this.titleName = titleName;
    }
    public void    setGradeCode(String gradeCode){
        this.gradeCode = gradeCode;
    }
    public void    setGradeName(String gradeName){
        this.gradeName = gradeName;
    }
    public void    setGradeSeniority(Integer gradeSeniority){
        this.gradeSeniority = gradeSeniority;
    }
    public void    setHireDate(Date hireDate){
        this.hireDate = hireDate;
    }
    public void    setHireDate(String    hireDate){
        this.hireDate = this.omcConvertStr2Date(hireDate);
    }
    public void    setRetiredDate(Date retiredDate){
        this.retiredDate = retiredDate;
    }
    public void    setRetiredDate(String    retiredDate){
        this.retiredDate = this.omcConvertStr2Date(retiredDate);
    }
    public String getTitleCode(){
        return titleCode;
    }
    public String getTitleName(){
        return titleName;
    }
    public String getGradeCode(){
        return gradeCode;
    }
    public String getGradeName(){
        return gradeName;
    }
    public Integer getGradeSeniority(){
        return gradeSeniority;
    }
    public Date getHireDate(){
        return hireDate;
    }
    public Date getRetiredDate(){
        return retiredDate;
    }
}

