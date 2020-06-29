/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesignChangeDropVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.requests.model;


import rap.api.object.requests.model.DesignProcessVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesignChangeDropVO extends DesignProcessVO {
    private String        requestReason                                     ;
    private String        requestReasonNote                                 ;
    private String        requestAbst                                       ;


    public void    setRequestReason(String requestReason){
        this.requestReason = requestReason;
    }
    public void    setRequestReasonNote(String requestReasonNote){
        this.requestReasonNote = requestReasonNote;
    }
    public void    setRequestAbst(String requestAbst){
        this.requestAbst = requestAbst;
    }
    public String getRequestReason(){
        return requestReason;
    }
    public String getRequestReasonNote(){
        return requestReasonNote;
    }
    public String getRequestAbst(){
        return requestAbst;
    }
}

