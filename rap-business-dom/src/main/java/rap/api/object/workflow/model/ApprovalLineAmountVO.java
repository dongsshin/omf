/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ApprovalLineAmountVO.java
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
public class ApprovalLineAmountVO extends BusinessObjectMasterVO {
    private BigDecimal    fromAmount                                         = new BigDecimal(0);
    private BigDecimal    toAmount                                           = new BigDecimal(0);


    public void    setFromAmount(BigDecimal fromAmount){
        this.fromAmount = fromAmount;
    }
    public void    setToAmount(BigDecimal toAmount){
        this.toAmount = toAmount;
    }
    public BigDecimal getFromAmount(){
        return fromAmount;
    }
    public BigDecimal getToAmount(){
        return toAmount;
    }
}

