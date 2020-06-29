/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MoldScheduleVO.java
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
public class MoldScheduleVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        affiliateCode                                     ;
    private String        partNo                                            ;
    private Integer       seq                                               ;
    private String        toolStartResultDay                                ;
    private String        ts1ResultDay                                      ;
    private String        dqmsResultDay                                     ;
    private Date          transferDate                                      ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setSeq(Integer seq){
        this.seq = seq;
    }
    public void    setToolStartResultDay(String toolStartResultDay){
        this.toolStartResultDay = toolStartResultDay;
    }
    public void    setTs1ResultDay(String ts1ResultDay){
        this.ts1ResultDay = ts1ResultDay;
    }
    public void    setDqmsResultDay(String dqmsResultDay){
        this.dqmsResultDay = dqmsResultDay;
    }
    public void    setTransferDate(Date transferDate){
        this.transferDate = transferDate;
    }
    public void    setTransferDate(String    transferDate){
        this.transferDate = this.omcConvertStr2Date(transferDate);
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public String getPartNo(){
        return partNo;
    }
    public Integer getSeq(){
        return seq;
    }
    public String getToolStartResultDay(){
        return toolStartResultDay;
    }
    public String getTs1ResultDay(){
        return ts1ResultDay;
    }
    public String getDqmsResultDay(){
        return dqmsResultDay;
    }
    public Date getTransferDate(){
        return transferDate;
    }
}

