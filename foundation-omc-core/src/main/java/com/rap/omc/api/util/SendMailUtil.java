/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MailUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 3. 4. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.foundation.model.MailInfo;
import com.rap.omc.framework.mail.OmcMailSender;
import com.rap.omc.framework.mail.OmcMailSenderFactory;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.util.NullUtil;



/**
 * <pre>
 * Class : SendMailUtil
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class SendMailUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailUtil.class);

    private OmcMailSenderFactory mailSenderFactory;

    private static SendMailUtil sInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static SendMailUtil getInstance(){
        if (sInstance == null) {
            sInstance = new SendMailUtil();
            sInstance.mailSenderFactory = (OmcMailSenderFactory)SpringFactoryUtil.getBean("mailSenderFactory");
        }
        return sInstance;
    }

    /**
     * 메일을 전송한다.
     *
     * @param mailInfo
     * @return
     */
    public static boolean sendMail(MailInfo mailInfo){

        boolean isSuccess = false;
        try {
            OmcMailSender mailSender = getInstance().mailSenderFactory.createMailSender("mailPolicy1");

            if (NullUtil.isNone(mailInfo.getFileNameList())) {
                if (!NullUtil.isNone(mailInfo.getHtmlTemplateName())) {
                    mailSender.setHtmlTemplate(mailInfo.getHtmlTemplateName(), mailInfo.getAttributeMap());
                } else if (mailInfo.isTextType() == true) {
                    mailSender.setText(mailInfo.getBody());
                } else {
                    mailSender.setHtml(mailInfo.getBody());
                }
            } else {
                if (!NullUtil.isNone(mailInfo.getHtmlTemplateName())) {
                    mailSender.setHtmlTemplateAndFile(mailInfo.getHtmlTemplateName(), mailInfo.getAttributeMap(), mailInfo.getFileNameArray());
                } else if (mailInfo.isTextType() == true) {
                    mailSender.setTextAndFile(mailInfo.getBody(), mailInfo.getFileNameArray());
                } else {
                    mailSender.setHtmlAndFile(mailInfo.getBody(), mailInfo.getFileNameArray());
                }
            }
            
            if (!NullUtil.isNone(mailInfo.getFromMailAddress())) {
                mailSender.setFromMailAddress(mailInfo.getFromMailAddress());
            }
            mailSender.setToMailAddress(mailInfo.getToMailAddressArray());
            mailSender.setSubject(mailInfo.getSubject());
            
            mailSender.send();
            
            writeLog(mailInfo);
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    
    /**
     * 메일 전송 이력을 로그로 남긴다.
     *
     * @param mailInfo
     */
    private static void writeLog(MailInfo mailInfo){
        StringBuffer sb = new StringBuffer();
        List<String> toMailAddressList = mailInfo.getToMailAddressList();
        for (String toMailAddress : toMailAddressList) {
            sb.append("FromMailAddress=").append(mailInfo.getFromMailAddress());
            sb.append(", ToMailAddress=").append(toMailAddress);
            sb.append(", Subject=").append(toMailAddress);
            sb.append("\n");
        }        
        LOGGER.info(sb.toString());
    }
}
