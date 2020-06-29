/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BizPlanVersionVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BizPlanVersionVO extends BusinessObjectVO {
    private Integer       versionNo                                         ;
    private Date          startDate                                         ;
    private Date          endDate                                           ;
    private String        basicPlanFlag                                     ;
    private String        operatePlanFlag                                   ;


    public void    setVersionNo(Integer versionNo){
        this.versionNo = versionNo;
    }
    public void    setStartDate(Date startDate){
        this.startDate = startDate;
    }
    public void    setStartDate(String    startDate){
        this.startDate = this.omcConvertStr2Date(startDate);
    }
    public void    setEndDate(Date endDate){
        this.endDate = endDate;
    }
    public void    setEndDate(String    endDate){
        this.endDate = this.omcConvertStr2Date(endDate);
    }
    public void    setBasicPlanFlag(String basicPlanFlag){
        this.basicPlanFlag = basicPlanFlag;
    }
    public void    setOperatePlanFlag(String operatePlanFlag){
        this.operatePlanFlag = operatePlanFlag;
    }
    public Integer getVersionNo(){
        return versionNo;
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public String getBasicPlanFlag(){
        return basicPlanFlag;
    }
    public String getOperatePlanFlag(){
        return operatePlanFlag;
    }
}

