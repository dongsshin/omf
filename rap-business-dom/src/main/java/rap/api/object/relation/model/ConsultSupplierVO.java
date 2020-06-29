/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultSupplierVO.java
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
public class ConsultSupplierVO extends BusinessRelationObjectVO {
    private String        oscInstEvalAvgStr                                 ;
    private String        oscInstAmtAvgStr                                  ;


    public void    setOscInstEvalAvgStr(String oscInstEvalAvgStr){
        this.oscInstEvalAvgStr = oscInstEvalAvgStr;
    }
    public void    setOscInstAmtAvgStr(String oscInstAmtAvgStr){
        this.oscInstAmtAvgStr = oscInstAmtAvgStr;
    }
    public String getOscInstEvalAvgStr(){
        return oscInstEvalAvgStr;
    }
    public String getOscInstAmtAvgStr(){
        return oscInstAmtAvgStr;
    }
}

