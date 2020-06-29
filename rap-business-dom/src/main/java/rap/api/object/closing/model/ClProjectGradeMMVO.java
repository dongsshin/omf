/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClProjectGradeMMVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.closing.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ClProjectGradeMMVO extends BusinessObjectMasterVO {
    private String        jobGrade                                          ;
    private Float         manMonth                                          ;


    public void    setJobGrade(String jobGrade){
        this.jobGrade = jobGrade;
    }
    public void    setManMonth(Float manMonth){
        this.manMonth = manMonth;
    }
    public String getJobGrade(){
        return jobGrade;
    }
    public Float getManMonth(){
        return manMonth;
    }
}

