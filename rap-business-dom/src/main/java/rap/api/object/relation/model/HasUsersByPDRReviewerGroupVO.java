/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasUsersByPDRReviewerGroupVO.java
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
public class HasUsersByPDRReviewerGroupVO extends BusinessRelationObjectVO {
    private String        reviewerType                                      ;
    private String        step                                              ;


    public void    setReviewerType(String reviewerType){
        this.reviewerType = reviewerType;
    }
    public void    setStep(String step){
        this.step = step;
    }
    public String getReviewerType(){
        return reviewerType;
    }
    public String getStep(){
        return step;
    }
}

