/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BizPlanYearVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BizPlanYearVO extends BusinessObjectMasterVO {
    private Date          openDate                                          ;


    public void    setOpenDate(Date openDate){
        this.openDate = openDate;
    }
    public void    setOpenDate(String    openDate){
        this.openDate = this.omcConvertStr2Date(openDate);
    }
    public Date getOpenDate(){
        return openDate;
    }
}

