/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPDRPropertyByPCRVO.java
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
public class HasPDRPropertyByPCRVO extends BusinessRelationObjectVO {
    private String        applyFlag                                         ;


    public void    setApplyFlag(String applyFlag){
        this.applyFlag = applyFlag;
    }
    public String getApplyFlag(){
        return applyFlag;
    }
}

