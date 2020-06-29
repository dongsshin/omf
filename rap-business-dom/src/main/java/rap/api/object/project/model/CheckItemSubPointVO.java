/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CheckItemSubPointVO.java
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
public class CheckItemSubPointVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private Integer       allottedPoint                                     ;
    private String        finalPoint                                        ;
    private Integer       seq                                               ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setAllottedPoint(Integer allottedPoint){
        this.allottedPoint = allottedPoint;
    }
    public void    setFinalPoint(String finalPoint){
        this.finalPoint = finalPoint;
    }
    public void    setSeq(Integer seq){
        this.seq = seq;
    }
    public String getFromObid(){
        return fromObid;
    }
    public Integer getAllottedPoint(){
        return allottedPoint;
    }
    public String getFinalPoint(){
        return finalPoint;
    }
    public Integer getSeq(){
        return seq;
    }
}

