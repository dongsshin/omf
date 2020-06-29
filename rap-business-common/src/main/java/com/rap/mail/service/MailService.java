/**
 * ===========================================
 * System Name : LGE GPLM Project
 * Program ID : MailService.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2017. 8. 28. youngmi.won Initial
 * ===========================================
 */
package com.rap.mail.service;

import com.rap.mail.model.MailVO;


/**
 * 
 * <pre>
 * Class : MailService
 * Description : TODO
 * </pre>
 * 
 * @author youngmi.won
 */
public interface MailService {
    
    public void sendMail( MailVO mailVO );
    public void sendMailByUser( MailVO mailVO, String mailType, String obid );
    public void sendMailBySystem( MailVO mailVO, String mailType, String obid );
    public void sendMailIncludeLog( MailVO mailVO, String mailType, String sendType, String obid );
    public void sendMailForDelayEco( String inboxTaskObid );
    public void txnCreateMailSendLog( String mailTypeName, String mailSendType, String toUserEmailAddress, String targetClassName, String targetObid, String targetNames );
    public String getMailContents( MailVO mailVO );
    public String getMobileApprovalDetail( String detailContents );
    
}
