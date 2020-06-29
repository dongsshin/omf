/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : UsingProjectGradeVO.java
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
public class UsingProjectGradeVO extends BusinessRelationObjectVO {
    private Integer       sequences                                          = 100;
    private String        isActive                                           = "Y";


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setIsActive(String isActive){
        this.isActive = isActive;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getIsActive(){
        return isActive;
    }
}

