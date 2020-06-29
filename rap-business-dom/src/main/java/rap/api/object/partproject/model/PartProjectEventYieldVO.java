/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectEventYieldVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectEventYieldVO extends BusinessObjectVO {
    private String        prodPhaseCode                                     ;
    private Integer       seq                                               ;
    private Float         targetYield                                       ;
    private Float         resultYield                                       ;
    private Date          resultEndDate                                     ;
    private String        shortfallContents                                 ;


    public void    setProdPhaseCode(String prodPhaseCode){
        this.prodPhaseCode = prodPhaseCode;
    }
    public void    setSeq(Integer seq){
        this.seq = seq;
    }
    public void    setTargetYield(Float targetYield){
        this.targetYield = targetYield;
    }
    public void    setResultYield(Float resultYield){
        this.resultYield = resultYield;
    }
    public void    setResultEndDate(Date resultEndDate){
        this.resultEndDate = resultEndDate;
    }
    public void    setResultEndDate(String    resultEndDate){
        this.resultEndDate = this.omcConvertStr2Date(resultEndDate);
    }
    public void    setShortfallContents(String shortfallContents){
        this.shortfallContents = shortfallContents;
    }
    public String getProdPhaseCode(){
        return prodPhaseCode;
    }
    public Integer getSeq(){
        return seq;
    }
    public Float getTargetYield(){
        return targetYield;
    }
    public Float getResultYield(){
        return resultYield;
    }
    public Date getResultEndDate(){
        return resultEndDate;
    }
    public String getShortfallContents(){
        return shortfallContents;
    }
}

