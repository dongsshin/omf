/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OSCConsultRelationVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class OSCConsultRelationVO extends BusinessRelationObjectVO {
    private Integer       dgr                                               ;
    private String        alterReason                                       ;
    private String        stopReason                                        ;
    private String        finalConsultFlag                                  ;


    public void    setDgr(Integer dgr){
        this.dgr = dgr;
    }
    public void    setAlterReason(String alterReason){
        this.alterReason = alterReason;
    }
    public void    setStopReason(String stopReason){
        this.stopReason = stopReason;
    }
    public void    setFinalConsultFlag(String finalConsultFlag){
        this.finalConsultFlag = finalConsultFlag;
    }
    public Integer getDgr(){
        return dgr;
    }
    public String getAlterReason(){
        return alterReason;
    }
    public String getStopReason(){
        return stopReason;
    }
    public String getFinalConsultFlag(){
        return finalConsultFlag;
    }
}

