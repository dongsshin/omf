/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClDeprAllocAcctRateVO.java
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
public class ClDeprAllocAcctRateVO extends BusinessObjectMasterVO {
    private String        closingYm                                         ;
    private String        managementGroup                                   ;
    private String        accountCode                                       ;
    private BigDecimal    distRate                                           = new BigDecimal(0);


    public void    setClosingYm(String closingYm){
        this.closingYm = closingYm;
    }
    public void    setManagementGroup(String managementGroup){
        this.managementGroup = managementGroup;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setDistRate(BigDecimal distRate){
        this.distRate = distRate;
    }
    public String getClosingYm(){
        return closingYm;
    }
    public String getManagementGroup(){
        return managementGroup;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public BigDecimal getDistRate(){
        return distRate;
    }
}

