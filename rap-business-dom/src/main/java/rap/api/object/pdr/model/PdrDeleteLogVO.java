/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PdrDeleteLogVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PdrDeleteLogVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        commentText                                       ;
    private String        referenceTypeCode                                 ;
    private Integer       referenceId                                       ;
    private String        referenceName                                     ;
    private String        statusCode                                        ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setCommentText(String commentText){
        this.commentText = commentText;
    }
    public void    setReferenceTypeCode(String referenceTypeCode){
        this.referenceTypeCode = referenceTypeCode;
    }
    public void    setReferenceId(Integer referenceId){
        this.referenceId = referenceId;
    }
    public void    setReferenceName(String referenceName){
        this.referenceName = referenceName;
    }
    public void    setStatusCode(String statusCode){
        this.statusCode = statusCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getCommentText(){
        return commentText;
    }
    public String getReferenceTypeCode(){
        return referenceTypeCode;
    }
    public Integer getReferenceId(){
        return referenceId;
    }
    public String getReferenceName(){
        return referenceName;
    }
    public String getStatusCode(){
        return statusCode;
    }
}

