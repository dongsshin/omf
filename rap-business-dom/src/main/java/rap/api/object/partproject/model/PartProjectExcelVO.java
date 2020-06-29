/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectExcelVO.java
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
public class PartProjectExcelVO extends BusinessObjectMasterVO {
    private String        prodProjectObid                                   ;
    private Integer       successCount                                      ;
    private Integer       failCount                                         ;
    private String        isFinalYn                                          = "N";
    private String        confirmUserId                                     ;
    private Date          confirmDate                                       ;
    private String        confirmStatus                                      = "N";


    public void    setProdProjectObid(String prodProjectObid){
        this.prodProjectObid = prodProjectObid;
    }
    public void    setSuccessCount(Integer successCount){
        this.successCount = successCount;
    }
    public void    setFailCount(Integer failCount){
        this.failCount = failCount;
    }
    public void    setIsFinalYn(String isFinalYn){
        this.isFinalYn = isFinalYn;
    }
    public void    setConfirmUserId(String confirmUserId){
        this.confirmUserId = confirmUserId;
    }
    public void    setConfirmDate(Date confirmDate){
        this.confirmDate = confirmDate;
    }
    public void    setConfirmDate(String    confirmDate){
        this.confirmDate = this.omcConvertStr2Date(confirmDate);
    }
    public void    setConfirmStatus(String confirmStatus){
        this.confirmStatus = confirmStatus;
    }
    public String getProdProjectObid(){
        return prodProjectObid;
    }
    public Integer getSuccessCount(){
        return successCount;
    }
    public Integer getFailCount(){
        return failCount;
    }
    public String getIsFinalYn(){
        return isFinalYn;
    }
    public String getConfirmUserId(){
        return confirmUserId;
    }
    public Date getConfirmDate(){
        return confirmDate;
    }
    public String getConfirmStatus(){
        return confirmStatus;
    }
}

