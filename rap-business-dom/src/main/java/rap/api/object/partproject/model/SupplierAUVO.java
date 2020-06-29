/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SupplierAUVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class SupplierAUVO extends BusinessObjectMasterVO {
    private String        supplierCode                                      ;
    private String        assignmentAffiliateCode                           ;
    private String        assgnAccountingUnitCode                           ;
    private String        assignmentAuStatusCode                            ;
    private String        durFromDate                                       ;
    private String        durToDate                                         ;
    private String        srmRegDate                                        ;
    private String        deliberateCode                                    ;
    private String        terminateDate                                     ;


    public void    setSupplierCode(String supplierCode){
        this.supplierCode = supplierCode;
    }
    public void    setAssignmentAffiliateCode(String assignmentAffiliateCode){
        this.assignmentAffiliateCode = assignmentAffiliateCode;
    }
    public void    setAssgnAccountingUnitCode(String assgnAccountingUnitCode){
        this.assgnAccountingUnitCode = assgnAccountingUnitCode;
    }
    public void    setAssignmentAuStatusCode(String assignmentAuStatusCode){
        this.assignmentAuStatusCode = assignmentAuStatusCode;
    }
    public void    setDurFromDate(String durFromDate){
        this.durFromDate = durFromDate;
    }
    public void    setDurToDate(String durToDate){
        this.durToDate = durToDate;
    }
    public void    setSrmRegDate(String srmRegDate){
        this.srmRegDate = srmRegDate;
    }
    public void    setDeliberateCode(String deliberateCode){
        this.deliberateCode = deliberateCode;
    }
    public void    setTerminateDate(String terminateDate){
        this.terminateDate = terminateDate;
    }
    public String getSupplierCode(){
        return supplierCode;
    }
    public String getAssignmentAffiliateCode(){
        return assignmentAffiliateCode;
    }
    public String getAssgnAccountingUnitCode(){
        return assgnAccountingUnitCode;
    }
    public String getAssignmentAuStatusCode(){
        return assignmentAuStatusCode;
    }
    public String getDurFromDate(){
        return durFromDate;
    }
    public String getDurToDate(){
        return durToDate;
    }
    public String getSrmRegDate(){
        return srmRegDate;
    }
    public String getDeliberateCode(){
        return deliberateCode;
    }
    public String getTerminateDate(){
        return terminateDate;
    }
}

