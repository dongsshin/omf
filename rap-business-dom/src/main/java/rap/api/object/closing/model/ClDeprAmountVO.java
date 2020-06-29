/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClDeprAmountVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.closing.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ClDeprAmountVO extends BusinessObjectMasterVO {
    private String        closingYm                                         ;
    private String        managementGroup                                   ;
    private String        legalEntityName                                   ;
    private String        accountingUnitCode                                ;
    private String        departmentCode                                    ;
    private String        distMethMmYn                                      ;
    private BigDecimal    depreBasicAmt                                      = new BigDecimal(0);
    private BigDecimal    depreChngAmt                                       = new BigDecimal(0);


    public void    setClosingYm(String closingYm){
        this.closingYm = closingYm;
    }
    public void    setManagementGroup(String managementGroup){
        this.managementGroup = managementGroup;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setDistMethMmYn(String distMethMmYn){
        this.distMethMmYn = distMethMmYn;
    }
    public void    setDepreBasicAmt(BigDecimal depreBasicAmt){
        this.depreBasicAmt = depreBasicAmt;
    }
    public void    setDepreChngAmt(BigDecimal depreChngAmt){
        this.depreChngAmt = depreChngAmt;
    }
    public String getClosingYm(){
        return closingYm;
    }
    public String getManagementGroup(){
        return managementGroup;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getDistMethMmYn(){
        return distMethMmYn;
    }
    public BigDecimal getDepreBasicAmt(){
        return depreBasicAmt;
    }
    public BigDecimal getDepreChngAmt(){
        return depreChngAmt;
    }
}

