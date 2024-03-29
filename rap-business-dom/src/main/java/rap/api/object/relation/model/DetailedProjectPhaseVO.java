/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DetailedProjectPhaseVO.java
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
public class DetailedProjectPhaseVO extends BusinessRelationObjectVO {
    private Integer       sequences                                          = 100;


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public Integer getSequences(){
        return sequences;
    }
}

