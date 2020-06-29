/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectUserPlanMMVO.java
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
public class ProjectUserPlanMMVO extends BusinessRelationObjectVO {
    private String        planMonth                                         ;
    private Float         manMonth                                           = (float)0;
    private String        currencyCode                                      ;
    private BigDecimal    totLaborCost                                       = new BigDecimal(0);
    private String        deptCode                                          ;


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
    public void    setDeptCode(String deptCode){
        this.deptCode = deptCode;
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
    public String getDeptCode(){
        return deptCode;
    }
}

