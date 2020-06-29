/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultFormFileTipVO.java
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
public class ConsultFormFileTipVO extends BusinessObjectMasterVO {
    private String        consultformTipObid                                ;


    public void    setConsultformTipObid(String consultformTipObid){
        this.consultformTipObid = consultformTipObid;
    }
    public String getConsultformTipObid(){
        return consultformTipObid;
    }
}

