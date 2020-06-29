/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BizPlanProjectMMVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BizPlanProjectMMVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        planMonth                                         ;
    private Float         manMonth                                           = (float)0;
    private String        currencyCode                                      ;
    private BigDecimal    totLaborCost                                       = new BigDecimal(0);


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setPlanMonth(String planMonth){
        this.planMonth = planMonth;
    }
    public void    setManMonth(Float manMonth){
        this.manMonth = manMonth;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setTotLaborCost(BigDecimal totLaborCost){
        this.totLaborCost = totLaborCost;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getPlanMonth(){
        return planMonth;
    }
    public Float getManMonth(){
        return manMonth;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public BigDecimal getTotLaborCost(){
        return totLaborCost;
    }
}

