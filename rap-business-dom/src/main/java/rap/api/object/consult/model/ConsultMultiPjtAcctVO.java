/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultMultiPjtAcctVO.java
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
public class ConsultMultiPjtAcctVO extends BusinessObjectMasterVO {
    private Integer       itemSno                                           ;
    private String        departmentCode                                    ;
    private String        projectCode                                       ;
    private String        accountCode                                       ;
    private BigDecimal    wonAmt                                             = new BigDecimal(0);
    private String        comments                                          ;
    private String        bgtCtrYn                                          ;
    private BigDecimal    calcAmt                                            = new BigDecimal(0);
    private String        currency                                          ;
    private BigDecimal    exchRate                                           = new BigDecimal(0);
    private BigDecimal    frnReqAmt                                          = new BigDecimal(0);
    private String        calcYmd                                           ;


    public void    setItemSno(Integer itemSno){
        this.itemSno = itemSno;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setWonAmt(BigDecimal wonAmt){
        this.wonAmt = wonAmt;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public void    setBgtCtrYn(String bgtCtrYn){
        this.bgtCtrYn = bgtCtrYn;
    }
    public void    setCalcAmt(BigDecimal calcAmt){
        this.calcAmt = calcAmt;
    }
    public void    setCurrency(String currency){
        this.currency = currency;
    }
    public void    setExchRate(BigDecimal exchRate){
        this.exchRate = exchRate;
    }
    public void    setFrnReqAmt(BigDecimal frnReqAmt){
        this.frnReqAmt = frnReqAmt;
    }
    public void    setCalcYmd(String calcYmd){
        this.calcYmd = calcYmd;
    }
    public Integer getItemSno(){
        return itemSno;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public BigDecimal getWonAmt(){
        return wonAmt;
    }
    public String getComments(){
        return comments;
    }
    public String getBgtCtrYn(){
        return bgtCtrYn;
    }
    public BigDecimal getCalcAmt(){
        return calcAmt;
    }
    public String getCurrency(){
        return currency;
    }
    public BigDecimal getExchRate(){
        return exchRate;
    }
    public BigDecimal getFrnReqAmt(){
        return frnReqAmt;
    }
    public String getCalcYmd(){
        return calcYmd;
    }
}

