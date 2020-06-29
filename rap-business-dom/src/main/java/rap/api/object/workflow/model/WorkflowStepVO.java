/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowStepVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class WorkflowStepVO extends BusinessObjectMasterVO {
    private Integer       sequences                                         ;


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public Integer getSequences(){
        return sequences;
    }
}

