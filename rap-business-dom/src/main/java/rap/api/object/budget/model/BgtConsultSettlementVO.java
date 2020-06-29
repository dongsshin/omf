/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BgtConsultSettlementVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BgtConsultSettlementVO extends BusinessObjectMasterVO {
    private Integer       sno                                               ;
    private String        invoiceNo                                         ;
    private BigDecimal    settlementAmt                                      = new BigDecimal(0);


    public void    setSno(Integer sno){
        this.sno = sno;
    }
    public void    setInvoiceNo(String invoiceNo){
        this.invoiceNo = invoiceNo;
    }
    public void    setSettlementAmt(BigDecimal settlementAmt){
        this.settlementAmt = settlementAmt;
    }
    public Integer getSno(){
        return sno;
    }
    public String getInvoiceNo(){
        return invoiceNo;
    }
    public BigDecimal getSettlementAmt(){
        return settlementAmt;
    }
}

