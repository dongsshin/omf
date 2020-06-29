/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ReviewSubPointVO.java
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
public class ReviewSubPointVO extends BusinessObjectMasterVO {
    private String        importance                                        ;
    private String        subPointPoint                                     ;
    private String        finalPoint                                        ;
    private Integer       score                                             ;
    private String        fromObid                                          ;
    private Integer       seq                                               ;


    public void    setImportance(String importance){
        this.importance = importance;
    }
    public void    setSubPointPoint(String subPointPoint){
        this.subPointPoint = subPointPoint;
    }
    public void    setFinalPoint(String finalPoint){
        this.finalPoint = finalPoint;
    }
    public void    setScore(Integer score){
        this.score = score;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setSeq(Integer seq){
        this.seq = seq;
    }
    public String getImportance(){
        return importance;
    }
    public String getSubPointPoint(){
        return subPointPoint;
    }
    public String getFinalPoint(){
        return finalPoint;
    }
    public Integer getScore(){
        return score;
    }
    public String getFromObid(){
        return fromObid;
    }
    public Integer getSeq(){
        return seq;
    }
}

