/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectDeptOperationPlanVO.java
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
public class ProjectDeptOperationPlanVO extends BusinessRelationObjectVO {
    private String        divisionCode                                      ;
    private String        version                                           ;
    private Date          mpRequestDate                                     ;
    private String        requestUser                                       ;
    private Date          confirmDate                                       ;
    private String        confirmUser                                       ;
    private String        status                                            ;
    private String        checkYn                                           ;
    private String        mailComment                                       ;
    private String        currentYn                                         ;
    private String        comments                                          ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setVersion(String version){
        this.version = version;
    }
    public void    setMpRequestDate(Date mpRequestDate){
        this.mpRequestDate = mpRequestDate;
    }
    public void    setMpRequestDate(String    mpRequestDate){
        this.mpRequestDate = this.omcConvertStr2Date(mpRequestDate);
    }
    public void    setRequestUser(String requestUser){
        this.requestUser = requestUser;
    }
    public void    setConfirmDate(Date confirmDate){
        this.confirmDate = confirmDate;
    }
    public void    setConfirmDate(String    confirmDate){
        this.confirmDate = this.omcConvertStr2Date(confirmDate);
    }
    public void    setConfirmUser(String confirmUser){
        this.confirmUser = confirmUser;
    }
    public void    setStatus(String status){
        this.status = status;
    }
    public void    setCheckYn(String checkYn){
        this.checkYn = checkYn;
    }
    public void    setMailComment(String mailComment){
        this.mailComment = mailComment;
    }
    public void    setCurrentYn(String currentYn){
        this.currentYn = currentYn;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getVersion(){
        return version;
    }
    public Date getMpRequestDate(){
        return mpRequestDate;
    }
    public String getRequestUser(){
        return requestUser;
    }
    public Date getConfirmDate(){
        return confirmDate;
    }
    public String getConfirmUser(){
        return confirmUser;
    }
    public String getStatus(){
        return status;
    }
    public String getCheckYn(){
        return checkYn;
    }
    public String getMailComment(){
        return mailComment;
    }
    public String getCurrentYn(){
        return currentYn;
    }
    public String getComments(){
        return comments;
    }
}

