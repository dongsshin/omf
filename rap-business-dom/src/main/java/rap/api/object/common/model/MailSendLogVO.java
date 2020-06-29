/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MailSendLogVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class MailSendLogVO extends BusinessObjectMasterVO {
    private String        mailTypeName                                      ;
    private String        mailSendType                                      ;
    private String        userEmailAddress                                  ;
    private String        targetClassName                                   ;
    private String        targetObid                                        ;
    private String        targetNames                                       ;


    public void    setMailTypeName(String mailTypeName){
        this.mailTypeName = mailTypeName;
    }
    public void    setMailSendType(String mailSendType){
        this.mailSendType = mailSendType;
    }
    public void    setUserEmailAddress(String userEmailAddress){
        this.userEmailAddress = userEmailAddress;
    }
    public void    setTargetClassName(String targetClassName){
        this.targetClassName = targetClassName;
    }
    public void    setTargetObid(String targetObid){
        this.targetObid = targetObid;
    }
    public void    setTargetNames(String targetNames){
        this.targetNames = targetNames;
    }
    public String getMailTypeName(){
        return mailTypeName;
    }
    public String getMailSendType(){
        return mailSendType;
    }
    public String getUserEmailAddress(){
        return userEmailAddress;
    }
    public String getTargetClassName(){
        return targetClassName;
    }
    public String getTargetObid(){
        return targetObid;
    }
    public String getTargetNames(){
        return targetNames;
    }
}

