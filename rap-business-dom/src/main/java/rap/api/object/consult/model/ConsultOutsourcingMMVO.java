/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultOutsourcingMMVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.consult.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ConsultOutsourcingMMVO extends BusinessObjectMasterVO {
    private String        grade                                             ;
    private String        classification                                    ;
    private String        function                                          ;
    private String        layout                                            ;


    public void    setGrade(String grade){
        this.grade = grade;
    }
    public void    setClassification(String classification){
        this.classification = classification;
    }
    public void    setFunction(String function){
        this.function = function;
    }
    public void    setLayout(String layout){
        this.layout = layout;
    }
    public String getGrade(){
        return grade;
    }
    public String getClassification(){
        return classification;
    }
    public String getFunction(){
        return function;
    }
    public String getLayout(){
        return layout;
    }
}

