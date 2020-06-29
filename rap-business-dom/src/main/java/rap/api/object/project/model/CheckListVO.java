/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CheckListVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class CheckListVO extends BusinessObjectVO {
    private String        checklistTypeCode                                 ;
    private String        divisionCode                                      ;
    private String        processTypeCode                                   ;
    private String        reviewCode                                        ;
    private String        reviewTypeCode                                    ;
    private String        revisionComments                                  ;


    public void    setChecklistTypeCode(String checklistTypeCode){
        this.checklistTypeCode = checklistTypeCode;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProcessTypeCode(String processTypeCode){
        this.processTypeCode = processTypeCode;
    }
    public void    setReviewCode(String reviewCode){
        this.reviewCode = reviewCode;
    }
    public void    setReviewTypeCode(String reviewTypeCode){
        this.reviewTypeCode = reviewTypeCode;
    }
    public void    setRevisionComments(String revisionComments){
        this.revisionComments = revisionComments;
    }
    public String getChecklistTypeCode(){
        return checklistTypeCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProcessTypeCode(){
        return processTypeCode;
    }
    public String getReviewCode(){
        return reviewCode;
    }
    public String getReviewTypeCode(){
        return reviewTypeCode;
    }
    public String getRevisionComments(){
        return revisionComments;
    }
}

