/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : GcrcCostVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class GcrcCostVO extends BusinessObjectMasterVO {
    private String        projectCode                                       ;
    private String        affiliateCode                                     ;
    private String        accountingYyyymm                                  ;
    private BigDecimal    totalAmount                                        = new BigDecimal(0);
    private BigDecimal    cipAmount                                          = new BigDecimal(0);


    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setAccountingYyyymm(String accountingYyyymm){
        this.accountingYyyymm = accountingYyyymm;
    }
    public void    setTotalAmount(BigDecimal totalAmount){
        this.totalAmount = totalAmount;
    }
    public void    setCipAmount(BigDecimal cipAmount){
        this.cipAmount = cipAmount;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public String getAccountingYyyymm(){
        return accountingYyyymm;
    }
    public BigDecimal getTotalAmount(){
        return totalAmount;
    }
    public BigDecimal getCipAmount(){
        return cipAmount;
    }
}

