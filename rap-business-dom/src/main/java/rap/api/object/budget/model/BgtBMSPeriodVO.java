/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BgtBMSPeriodVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BgtBMSPeriodVO extends BusinessObjectMasterVO {
    private String        closeYn                                           ;
    private Date          closeDate                                         ;


    public void    setCloseYn(String closeYn){
        this.closeYn = closeYn;
    }
    public void    setCloseDate(Date closeDate){
        this.closeDate = closeDate;
    }
    public void    setCloseDate(String    closeDate){
        this.closeDate = this.omcConvertStr2Date(closeDate);
    }
    public String getCloseYn(){
        return closeYn;
    }
    public Date getCloseDate(){
        return closeDate;
    }
}

