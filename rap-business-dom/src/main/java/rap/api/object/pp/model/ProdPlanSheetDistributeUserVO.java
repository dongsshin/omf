/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSheetDistributeUserVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanSheetDistributeUserVO extends BusinessObjectMasterVO {
    private String        sheetDistId                                       ;
    private String        receiver                                          ;
    private Date          confirmDate                                       ;


    public void    setSheetDistId(String sheetDistId){
        this.sheetDistId = sheetDistId;
    }
    public void    setReceiver(String receiver){
        this.receiver = receiver;
    }
    public void    setConfirmDate(Date confirmDate){
        this.confirmDate = confirmDate;
    }
    public void    setConfirmDate(String    confirmDate){
        this.confirmDate = this.omcConvertStr2Date(confirmDate);
    }
    public String getSheetDistId(){
        return sheetDistId;
    }
    public String getReceiver(){
        return receiver;
    }
    public Date getConfirmDate(){
        return confirmDate;
    }
}

