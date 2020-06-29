/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : UsersVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.model;


import rap.api.object.organization.model.AbstractUsersVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class UsersVO extends AbstractUsersVO {
    private String        divisions                                         ;
    private String        payDivision                                       ;
    private String        workPhoneNumber                                   ;
    private String        emailAddress                                      ;
    private String        mailId                                            ;
    private Date          iamCreationDate                                   ;
    private Date          iamLastupdateDate                                 ;
    private Date          absenceStartDate                                  ;
    private Date          absenceEndDate                                    ;
    private String        absenceDelegate                                   ;
    private String        mmMngGubun                                        ;
    private String        commMmMngGubun                                    ;
    private Integer       loginFailCount                                     = 0;
    private String        loginCode                                         ;


    public void    setDivisions(String divisions){
        this.divisions = divisions;
    }
    public void    setPayDivision(String payDivision){
        this.payDivision = payDivision;
    }
    public void    setWorkPhoneNumber(String workPhoneNumber){
        this.workPhoneNumber = workPhoneNumber;
    }
    public void    setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
    public void    setMailId(String mailId){
        this.mailId = mailId;
    }
    public void    setIamCreationDate(Date iamCreationDate){
        this.iamCreationDate = iamCreationDate;
    }
    public void    setIamCreationDate(String    iamCreationDate){
        this.iamCreationDate = this.omcConvertStr2Date(iamCreationDate);
    }
    public void    setIamLastupdateDate(Date iamLastupdateDate){
        this.iamLastupdateDate = iamLastupdateDate;
    }
    public void    setIamLastupdateDate(String    iamLastupdateDate){
        this.iamLastupdateDate = this.omcConvertStr2Date(iamLastupdateDate);
    }
    public void    setAbsenceStartDate(Date absenceStartDate){
        this.absenceStartDate = absenceStartDate;
    }
    public void    setAbsenceStartDate(String    absenceStartDate){
        this.absenceStartDate = this.omcConvertStr2Date(absenceStartDate);
    }
    public void    setAbsenceEndDate(Date absenceEndDate){
        this.absenceEndDate = absenceEndDate;
    }
    public void    setAbsenceEndDate(String    absenceEndDate){
        this.absenceEndDate = this.omcConvertStr2Date(absenceEndDate);
    }
    public void    setAbsenceDelegate(String absenceDelegate){
        this.absenceDelegate = absenceDelegate;
    }
    public void    setMmMngGubun(String mmMngGubun){
        this.mmMngGubun = mmMngGubun;
    }
    public void    setCommMmMngGubun(String commMmMngGubun){
        this.commMmMngGubun = commMmMngGubun;
    }
    public void    setLoginFailCount(Integer loginFailCount){
        this.loginFailCount = loginFailCount;
    }
    public void    setLoginCode(String loginCode){
        this.loginCode = loginCode;
    }
    public String getDivisions(){
        return divisions;
    }
    public String getPayDivision(){
        return payDivision;
    }
    public String getWorkPhoneNumber(){
        return workPhoneNumber;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public String getMailId(){
        return mailId;
    }
    public Date getIamCreationDate(){
        return iamCreationDate;
    }
    public Date getIamLastupdateDate(){
        return iamLastupdateDate;
    }
    public Date getAbsenceStartDate(){
        return absenceStartDate;
    }
    public Date getAbsenceEndDate(){
        return absenceEndDate;
    }
    public String getAbsenceDelegate(){
        return absenceDelegate;
    }
    public String getMmMngGubun(){
        return mmMngGubun;
    }
    public String getCommMmMngGubun(){
        return commMmMngGubun;
    }
    public Integer getLoginFailCount(){
        return loginFailCount;
    }
    public String getLoginCode(){
        return loginCode;
    }
}

