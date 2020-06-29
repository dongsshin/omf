/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ReviewTypeVO.java
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
public class ReviewTypeVO extends BusinessObjectMasterVO {
    private String        reviewCode                                        ;
    private String        eventCode                                         ;
    private String        reviewTypeCode                                    ;
    private String        useFlag                                           ;


    public void    setReviewCode(String reviewCode){
        this.reviewCode = reviewCode;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setReviewTypeCode(String reviewTypeCode){
        this.reviewTypeCode = reviewTypeCode;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public String getReviewCode(){
        return reviewCode;
    }
    public String getEventCode(){
        return eventCode;
    }
    public String getReviewTypeCode(){
        return reviewTypeCode;
    }
    public String getUseFlag(){
        return useFlag;
    }
}

