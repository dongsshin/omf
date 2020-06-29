/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CommonCheckItemVO.java
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
public class CommonCheckItemVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        processTypeCode                                   ;
    private String        reviewTypeCode                                    ;
    private Integer       seq                                               ;
    private String        importance                                        ;
    private String        fromObid                                          ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProcessTypeCode(String processTypeCode){
        this.processTypeCode = processTypeCode;
    }
    public void    setReviewTypeCode(String reviewTypeCode){
        this.reviewTypeCode = reviewTypeCode;
    }
    public void    setSeq(Integer seq){
        this.seq = seq;
    }
    public void    setImportance(String importance){
        this.importance = importance;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProcessTypeCode(){
        return processTypeCode;
    }
    public String getReviewTypeCode(){
        return reviewTypeCode;
    }
    public Integer getSeq(){
        return seq;
    }
    public String getImportance(){
        return importance;
    }
    public String getFromObid(){
        return fromObid;
    }
}

