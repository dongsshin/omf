/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcMailSender.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 3. 2. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.framework.mail;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceEditor;

import com.rap.omc.framework.mail.exception.MailException;
import com.rap.omc.framework.mail.policy.MailPolicy;
import com.rap.omc.framework.mail.policy.MailTemplate;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;


/**
 * <pre>
 * Class : OmcMailSender
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class OmcMailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmcMailSender.class);

    /**
     * 전송 메시지
     * */
    private Message msg = null;

    private String[] testReceiverList;

    private MailPolicy mailPolicy;

    private Map<String, MailTemplate> mailTemplate = new HashMap<String, MailTemplate>();

    private String mailTemplateName = "";

    private String strTempleteResult = null;

    /**
     * 
     * 
     * @return the strTempleteResult
     */
    public String getStrTempleteResult(){
        return strTempleteResult;
    }

    /**
     * 
     * 
     * @param strTempleteResult the strTempleteResult to set
     */
    public void setStrTempleteResult(String strTempleteResult){
        this.strTempleteResult = strTempleteResult;
    }

    /**
     * MailSender 생성자
     *
     * @param mailPolicy 메일전송에 대한 정책을 담고 있는 객체
     * @param mailTemplate html Template에 대한 정보를 가지고 있는 Map 객체
     * @throws MailException
     * */
    public OmcMailSender(MailPolicy mailPolicy, Map<String, MailTemplate> mailTemplate) {
        this.mailPolicy = mailPolicy;
        this.mailTemplate = mailTemplate;

        checkTestOption();

        setMimeMessage(getSession(this.mailPolicy.isSessionDebugMessageFlag()));
        setDefaultFromAddress();
    }

    /**
     * 테스트 발송인지를 체크하여, mailPolicy에 지정된 수신자(복수선택가능)로 설정한다.
     * */
    private void checkTestOption(){
        if (this.mailPolicy.isTestMode()) {
            StringTokenizer str = new StringTokenizer(this.mailPolicy.getTestReceivers(), ",");
            this.testReceiverList = new String[str.countTokens()];
            int inx = 0;
            while (str.hasMoreTokens()) {
                this.testReceiverList[inx] = ((String)str.nextElement()).trim();
                inx++;
            }
        }
    }

    /**
     * 세션을 얻어온다.
     *
     * @param debugMode 세션에 디버그 설정여부
     * */
    private Session getSession(boolean debugMode){
        Properties properties = new Properties();
        Session session = null;
        Authenticator auth = null;
        // could use Session.getTransport() and Transport.connect()
        // assume we're using SMTP

        properties.put("mail.smtp.host", this.mailPolicy.getMailHost());
        properties.put("mail.smtp.port", this.mailPolicy.getMailPort());

        if (this.mailPolicy.isAuthenticationEnabled()) {
            properties.put("mail.smtp.auth", this.mailPolicy.isAuthenticationEnabled());

            if (this.mailPolicy.isAuthenticationTlsEnabled()) {
                properties.put("mail.smtp.starttls.enable", this.mailPolicy.isAuthenticationTlsEnabled());
            }
            if (this.mailPolicy.isAuthenticationSslEnabled()) {
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            }

            final String authenticationId = this.mailPolicy.getAuthenticationId();
            final String authenticationPassword = this.mailPolicy.getAuthenticationPassword();
            auth = new Authenticator() {

                public PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(authenticationId, authenticationPassword);
                }
            };

            if (this.mailPolicy.isDefaultSessionLoad()) {
                session = Session.getDefaultInstance(properties, auth);
            } else {
                session = Session.getInstance(properties, auth);
            }
        } else {
            if (this.mailPolicy.isDefaultSessionLoad()) {
                session = Session.getDefaultInstance(properties, null);
            } else {
                session = Session.getInstance(properties, null);
            }
        }

        session.setDebug(debugMode);
        return session;
    }

    private void setMimeMessage(Session session){
        try {
            msg = new MimeMessage(session);
            msg.setSentDate(new Date());
        } catch (MessagingException e) {
            throw new MailException("DSOMAL049",
                    "MailSender-setMimeMessage(Session session) ▶ Mail contents setting error happened.", e);
        }
    }

    /**
     * 설정파일에 저장된 기본 송신자 메일주소와 송신자명을 전송 메세지 객체에 저장한다.
     * */
    private void setDefaultFromAddress(){
        if (this.mailPolicy.getDefaultSenderMailAddress() == null) { throw new MailException("DSOMAL016",
                "MailSender-setDefaultFromAddress() ▶ DefaultSenderMailAddress cannot be null."); }
        setFromMailAddress(this.mailPolicy.getDefaultSenderMailAddress(), this.mailPolicy.getDefaultSenderName());
    }

    /**
     * 보내는 사람의 Mail 주소를 설정한다.
     *
     * @param senderMailAddress 보내는 사람의 이메일주소(aaa@bbb.com 형식)
     * */
    public void setFromMailAddress(String senderMailAddress){
        try {
            InternetAddress sender = new InternetAddress(senderMailAddress);
            msg.setFrom(sender);
        } catch (MessagingException e) {
            throw new MailException(
                    "DSOMAL034",
                    "MailSender-setFromMailAddress(String senderMailAddress) ▶ Mail address setting error happened, check your input values : MailAddress["
                            + senderMailAddress + "] name[] type[].", e);
        }
    }

    /**
     * 보내는 사람의 Mail 주소를 설정한다.
     *
     * @param senderMailAddress 보내는 사람의 이메일주소(aaa@bbb.com 형식)
     * @param senderName 보내는 사람 이름
     * */
    public void setFromMailAddress(String senderMailAddress, String senderName){
        try {
            InternetAddress sender = new InternetAddress(senderMailAddress, senderName,
                    this.mailPolicy.getCharsetType());
            msg.setFrom(sender);
        } catch (MessagingException e) {
            throw new MailException(
                    "DSOMAL032",
                    "MailSender-setFromMailAddress(String senderMailAddress, String senderName) ▶ Mail address setting error happened, check your input values : MailAddress["
                            + senderMailAddress + "] name[" + senderName + "] type[].", e);
        } catch (UnsupportedEncodingException e) {
            throw new MailException(
                    "DSOMAL033",
                    "MailSender-setFromMailAddress(String senderMailAddress, String senderName) ▶ Found encoding error, InternetAddress Constructor - MailAddress["
                            + senderMailAddress + "] name[" + senderName + "] type[]", e);
        }
    }

    /**
     * 받는 사람(To)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address
     * */
    public void setToMailAddress(String mailAddress){
        setMailAddress(mailAddress, Message.RecipientType.TO);
    }

    /**
     * 받는 사람(To)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address
     * @param name 받는 사람 이름
     * */
    public void setToMailAddress(String mailAddress, String name){
        setMailAddress(mailAddress, name, Message.RecipientType.TO);
    }

    /**
     * 받는 사람(To)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address String 배열
     * */
    public void setToMailAddress(String[] mailAddress){
        setMailAddress(mailAddress, Message.RecipientType.TO);
    }

    /**
     * 받는 사람(To)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address String 배열
     * @param name 받는 사람 이름 String 배열
     * */
    public void setToMailAddress(String[] mailAddress, String[] name){
        setMailAddress(mailAddress, name, Message.RecipientType.TO);
    }

    /**
     * 받는 사람(Cc-참조)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address
     * */
    public void setCcMailAddress(String mailAddress){
        setMailAddress(mailAddress, Message.RecipientType.CC);
    }

    /**
     * 받는 사람(Cc-참조)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address String 배열
     * */
    public void setCcMailAddress(String[] mailAddress){
        setMailAddress(mailAddress, Message.RecipientType.CC);
    }

    /**
     * 받는 사람(Cc-참조)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address
     * @param name 받는 사람 이름
     * */
    public void setCcMailAddress(String mailAddress, String name){
        setMailAddress(mailAddress, name, Message.RecipientType.CC);
    }

    /**
     * 받는 사람(Cc-참조)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address String 배열
     * @param name 받는 사람 이름 String 배열
     * */
    public void setCcMailAddress(String[] mailAddress, String[] name){
        setMailAddress(mailAddress, name, Message.RecipientType.CC);
    }

    /**
     * 받는 사람(Bcc-비밀참조)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address
     * @throws MailException
     * */
    public void setBccMailAddress(String mailAddress){
        setMailAddress(mailAddress, Message.RecipientType.BCC);
    }

    /**
     * 받는 사람(Bcc-비밀참조)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address String 배열
     * */
    public void setBccMailAddress(String[] mailAddress){
        setMailAddress(mailAddress, Message.RecipientType.BCC);
    }

    /**
     * 받는 사람(Bcc-비밀참조)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address
     * @param name 받는 사람 이름
     * */
    public void setBccMailAddress(String mailAddress, String name){
        setMailAddress(mailAddress, name, Message.RecipientType.BCC);
    }

    /**
     * 받는 사람(Bcc-비밀참조)의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address String 배열
     * @param name 받는 사람 이름 String 배열
     * */
    public void setBccMailAddress(String[] mailAddress, String[] name){
        setMailAddress(mailAddress, name, Message.RecipientType.BCC);
    }

    /**
     * 받는 사람의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address
     * @param type 받는 사람의 타입(TO, CC, BCC)
     * */
    private void setMailAddress(String mailAddress, Message.RecipientType type){
        setMailAddress(new String[] { mailAddress }, type);
    }

    /**
     * 받는 사람의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address
     * @param name 받는 사람 이름
     * @param type 받는 사람의 타입(TO, CC, BCC)
     * */
    private void setMailAddress(String mailAddress, String name, Message.RecipientType type){
        setMailAddress(new String[] { mailAddress }, new String[] { name }, type);
    }

    /**
     * 받는 사람의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address String 배열
     * @param type 받는 사람의 타입(TO, CC, BCC)
     * */
    private void setMailAddress(String[] mailAddress, Message.RecipientType type){
        int inx = 0;
        try {
            if (mailAddress.length == 1 && "".equals(mailAddress[0])) {
                msg.setRecipients(type, null);
            } else {
                InternetAddress[] recipients = new InternetAddress[mailAddress.length];
                for (inx = 0; inx < mailAddress.length; inx++) {
                    recipients[inx] = new InternetAddress(mailAddress[inx]);
                }

                msg.setRecipients(type, recipients);
            }
        } catch (MessagingException e) {
            throw new MailException(
                    "DSOMAL046",
                    "MailSender-setMailAddress(String[] mailAddress, Message.RecipientType type) ▶ Mail address setting error happened, check your input values : MailAddress["
                            + mailAddress[inx] + "] name[] type[" + type.toString() + "].", e);
        }
    }

    /**
     * 받는 사람의 이메일 주소를 설정한다.
     *
     * @param mailAddress aaa@bbb.com 형식의 mail address String 배열
     * @param name 받는 사람 이름
     * @param type 받는 사람의 타입(TO, CC, BCC)
     * */
    private void setMailAddress(String[] mailAddress, String[] name, Message.RecipientType type){
        int inx = 0;
        try {
            if (mailAddress.length == 1 && "".equals(mailAddress[0])) {
                msg.setRecipients(type, null);
            } else {
                InternetAddress[] recipients = new InternetAddress[mailAddress.length];
                for (inx = 0; inx < mailAddress.length; inx++) {
                    recipients[inx] = new InternetAddress(mailAddress[inx], name[inx], this.mailPolicy.getCharsetType());
                }
                msg.setRecipients(type, recipients);
            }
        } catch (MessagingException e) {
            throw new MailException(
                    "DSOMAL047",
                    "MailSender-setMailAddress(String[] mailAddress, String[] name, Message.RecipientType type) ▶ Mail address setting error happened, check your input values : MailAddress["
                            + mailAddress[inx] + "] name[" + name[inx] + "] type[" + type.toString() + "].", e);
        } catch (UnsupportedEncodingException e) {
            throw new MailException(
                    "DSOMAL048",
                    "MailSender-setMailAddress(String[] mailAddress, String[] name, Message.RecipientType type) ▶ Found encoding error, InternetAddress Constructor - MailAddress["
                            + mailAddress[inx] + "] name[" + name[inx] + "] type[" + type.toString() + "].", e);
        }
    }

    /**
     * 메일 제목을 설정한다.
     *
     * @param subject 메일 제목
     * */
    public void setSubject(String subject){
        try {
            ((MimeMessage)msg).setSubject(subject, this.mailPolicy.getCharsetType());
        } catch (MessagingException e) {
            throw new MailException("DSOMAL050",
                    "MailSender-setSubject(String subject) ▶ Mail subject setting error happened.", e);
        }
    }

    /**
     * text/plain의 메일 메시지 내용을 설정한다.
     *
     * @param textMessage 메일 내용
     * */
    public void setText(String textMessage){
        try {
            msg.setContent(textMessage,
                    this.mailPolicy.getPlainContentType() + ";charset=" + this.mailPolicy.getCharsetType());
            msg.setHeader("Content-Transfer-Encoding", this.mailPolicy.getContentType());
        } catch (MessagingException e) {
            throw new MailException("DSOMAL051",
                    "MailSender-setText(String textMessage) ▶ Mail contents setting error happened.", e);
        }
    }

    /**
     * text/plain의 메일 메시지 내용과 첨부파일을 설정한다.
     *
     * @param textMessage 메일 내용
     * @param fileName 첨부파일 이름
     * */
    public void setTextAndFile(String textMessage, String fileName){
        setTextAndFile(textMessage, new String[] { fileName });
    }

    /**
     * text/plain의 메일 메시지 내용과 첨부파일을 설정한다.
     *
     * @param textMessage 메일 내용
     * @param fileName 첨부파일 이름 String 배열
     * */
    public void setTextAndFile(String textMessage, String[] fileNames){
        try {
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(textMessage, this.mailPolicy.getCharsetType());

            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(textPart);

            attachFileSourceArray(multiPart, fileNames);

            multiPart.setSubType("mixed");
            msg.setContent(multiPart);
        } catch (MessagingException e) {
            throw new MailException(
                    "DSOMAL052",
                    "MailSender-setTextAndFile(String textMessage, String[] fileNames) ▶ Mail contents setting error happened.",
                    e);
        } catch (UnsupportedEncodingException e) {
            throw new MailException(
                    "DSOMAL053",
                    "MailSender-setTextAndFile(String textMessage, String[] fileNames) ▶ Found encoding error, MimeUtility.encodeText - Current CHARSET_TYPE["
                            + this.mailPolicy.getCharsetType()
                            + "] ENCODING_TYPE["
                            + this.mailPolicy.getEncodingType()
                            + "]", e);
        }
    }

    /**
     * text/html 의 메일 메시지 내용을 설정 한다.
     *
     * @param htmlMessage 메일 내용
     */
    public void setHtml(String htmlMessage){
        try {
            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(htmlMessage, this.mailPolicy
                    .getHtmlContentType() + ";charset=" + this.mailPolicy.getCharsetType())));
            msg.setHeader("Content-Transfer-Encoding", this.mailPolicy.getContentType());
        } catch (MessagingException e) {
            throw new MailException("DSOMAL035",
                    "MailSender-setHtml(String htmlMessage) ▶ Mail contents setting error happened.", e);
        } catch (IOException e) {
            throw new MailException("DSOMAL036", "MailSender-setHtml(String htmlMessage) ▶ UnsupportedEncoding. ", e);
        }
    }

    /**
     * text/html 의 메일 메시지 내용과 첨부파일을 설정한다.
     *
     * @param htmlMessage 메일 내용
     * @param fileName 첨부파일 이름
     */
    public void setHtmlAndFile(String htmlMessage, String fileName){
        setHtmlAndFile(htmlMessage, new String[] { fileName });
    }

    /**
     * text/html 의 메일 메시지 내용과 첨부파일을 설정한다.
     *
     * @param htmlMessage 메일 내용
     * @param fileName 첨부파일 이름 String 배열
     */
    public void setHtmlAndFile(String htmlMessage, String[] fileNames){
        try {

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setDataHandler(new DataHandler(new ByteArrayDataSource(htmlMessage, this.mailPolicy
                    .getHtmlContentType() + ";charset=" + this.mailPolicy.getCharsetType())));

            htmlPart.setHeader("Content-Transfer-Encoding", this.mailPolicy.getContentType());

            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(htmlPart);

            attachFileSourceArray(multiPart, fileNames);

            multiPart.setSubType("mixed");
            msg.setContent(multiPart);
        } catch (MessagingException e) {
            throw new MailException(
                    "DSOMAL037",
                    "MailSender-setHtmlAndFile(String htmlMessage, String[] fileNames) ▶ Mail contents setting error happened.",
                    e);
        } catch (UnsupportedEncodingException e) {
            throw new MailException("DSOMAL038",
                    "MailSender-setHtmlAndFile(String htmlMessage, String[] fileNames) ▶ UnsupportedEncoding.", e);
        } catch (IOException e) {
            throw new MailException(
                    "DSOMAL039",
                    "MailSender-setHtmlAndFile(String htmlMessage, String[] fileNames) ▶ Mail contents setting error happened.",
                    e);
        }
    }

    /**
     * template html을 읽어서 구성된 메일 메시지 내용을 설정 한다.
     *
     * @param mailTemplateName 사용할 mailTemplate의 bean id
     * @param replacement html template 파일 속의 변수에 셋팅되어야 하는 값
     */
    public void setHtmlTemplate(String mailTemplateName, Object replacement){
        StringBuffer htmlStr = new StringBuffer();
        String resultStr = "";
        BufferedReader bfr = null;

        this.mailTemplateName = mailTemplateName;
        MailTemplate selectedMailTemplate = this.mailTemplate.get(mailTemplateName);

        try {
            String htmlPathName = selectedMailTemplate.getDirectory() + "/" + selectedMailTemplate.getHtmlName();

            ResourceEditor propertyEditor = new ResourceEditor();
            propertyEditor.setAsText(htmlPathName);
            Resource resource = (Resource)propertyEditor.getValue();

            bfr = new BufferedReader(new InputStreamReader(resource.getInputStream(), this.mailPolicy.getCharsetType()));
            String str = null;
            while ((str = bfr.readLine()) != null) {
                htmlStr.append(str).append("\n");
            }

            if (htmlStr != null && htmlStr.length() > 0) {
                resultStr = replaceVariables(htmlStr.toString(), replacement, selectedMailTemplate);
                this.strTempleteResult = resultStr;
                
                this.setHtml(resultStr);

                if (selectedMailTemplate.isSaveResolvedhtmlFlag()) {
                    saveResolvedHtmlFile(selectedMailTemplate, resultStr);
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new MailException(
                    "DSOMAL040",
                    "MailSender-setHtmlTemplate(String mailTemplateName, Object var, String charset) ▶ UnsupportedEncoding.",
                    e);
        } catch (IOException e) {
            throw new MailException(
                    "DSOMAL041",
                    "MailSender-setHtmlTemplate(String mailTemplateName, Object var, String charset) ▶ Mail contents setting error happened.",
                    e);
        } finally {
            try {
                if (bfr != null) {
                    bfr.close();
                }
            } catch (IOException e) {
                throw new MailException(
                        "DSOMAL042",
                        "MailSender-setHtmlTemplate(String mailTemplateName, Object var, String charset) ▶ Mail contents setting error happened.",
                        e);
            }
        }
    }

    /**
     * template html을 읽어서 구성된 메일 메시지 내용과 첨부파일을 설정 한다.
     *
     * @param mailTemplateName 사용할 mailTemplate의 bean id
     * @param var html template 파일 속의 변수에 셋팅되어야 하는 값
     * @param fileName 첨부파일 이름
     */
    public void setHtmlTemplateAndFile(String mailTemplateName, Object var, String fileName){
        this.setHtmlTemplateAndFile(mailTemplateName, var, new String[] { fileName });
    }

    /**
     * template html을 읽어서 구성된 메일 메시지 내용과 첨부파일을 설정 한다.
     *
     * @param mailTemplateName 사용할 mailTemplate의 bean id
     * @param var html template 파일 속의 변수에 셋팅되어야 하는 값
     * @param fileName 첨부파일 이름 String 배열
     */
    public void setHtmlTemplateAndFile(String mailTemplateName, Object var, String[] fileNames){
        StringBuffer htmlStr = new StringBuffer();
        String resultStr = "";
        BufferedReader bfr = null;

        this.mailTemplateName = mailTemplateName;
        MailTemplate selectedMailTemplate = this.mailTemplate.get(mailTemplateName);

        try {
            String htmlPathName = selectedMailTemplate.getDirectory() + "/" + selectedMailTemplate.getHtmlName();

            ResourceEditor propertyEditor = new ResourceEditor();
            propertyEditor.setAsText(htmlPathName);
            Resource resource = (Resource)propertyEditor.getValue();

            bfr = new BufferedReader(new InputStreamReader(resource.getInputStream(), this.mailPolicy.getCharsetType()));
            String str = null;
            while ((str = bfr.readLine()) != null) {
                htmlStr.append(str).append("\n");
            }

            if (htmlStr != null && htmlStr.length() > 0) {
                resultStr = replaceVariables(htmlStr.toString(), var, selectedMailTemplate);
                this.strTempleteResult = resultStr;

                if (selectedMailTemplate.isSaveResolvedhtmlFlag()) {
                    saveResolvedHtmlFile(selectedMailTemplate, resultStr);
                }
            }

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setDataHandler(new DataHandler(new ByteArrayDataSource(resultStr, this.mailPolicy
                    .getHtmlContentType() + ";charset=" + this.mailPolicy.getCharsetType())));

            htmlPart.setHeader("Content-Transfer-Encoding", this.mailPolicy.getContentType());

            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(htmlPart);

            attachFileSourceArray(multiPart, fileNames);

            multiPart.setSubType("mixed");
            msg.setContent(multiPart);
        } catch (MessagingException e) {
            throw new MailException(
                    "DSOMAL043",
                    "MailSender-setHtmlTemplateAndFile(String mailTemplateName, Object var, String charset, String[] fileNames) ▶ Mail contents setting error happened.",
                    e);
        } catch (IOException e) {
            throw new MailException(
                    "DSOMAL044",
                    "MailSender-setHtmlTemplateAndFile(String mailTemplateName, Object var, String charset, String[] fileNames) ▶ IO Exception. ",
                    e);
        } finally {
            try {
                if (bfr != null) {
                    bfr.close();
                }
            } catch (IOException e) {
                throw new MailException(
                        "DSOMAL045",
                        "MailSender-setHtmlTemplateAndFile(String mailTemplateName, Object var, String charset, String[] fileNames) ▶ Mail contents setting error happened.",
                        e);
            }
        }
    }

    /**
     * ${name}과 같은 위치에 name의 값으로 치환한다.
     *
     * @param string html파일의 내용을 담은 문자열
     * @param var 치환할 값을 가지고 있는 VO객체
     * @param selectedMailTemplate 사용할 mailTemplate 객체
     * */
    private String replaceVariables(String string, Object var, MailTemplate selectedMailTemplate){
        String replaced = string;

        if (var != null) {
            if (selectedMailTemplate.getImgServerIp() != null) {
                replaced = replaced.replaceAll("\\$\\{imgServerIp\\}",
                        StringUtil.escapeDollarSign(selectedMailTemplate.getImgServerIp()));
            }
            if (selectedMailTemplate.getImgServerPort() != null) {
                replaced = replaced.replaceAll("\\$\\{imgServerPort\\}",
                        StringUtil.escapeDollarSign(selectedMailTemplate.getImgServerPort()));
            }
            if (selectedMailTemplate.getImgServerContext() != null) {
                replaced = replaced.replaceAll("\\$\\{imgServerContext\\}",
                        StringUtil.escapeDollarSign(selectedMailTemplate.getImgServerContext()));
            }

            if (var instanceof Map) {
                replaced = replaceVariablesByMap(replaced, (Map)var, selectedMailTemplate);
            } else {
                replaced = replaceVariablesByVo(replaced, var, selectedMailTemplate);
            }
        }

        return replaced;
    }

    private String replaceVariablesByVo(String string, Object var, MailTemplate selectedMailTemplate){
        String replaced = string;

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(var);
        PropertyDescriptor[] pdr = beanWrapper.getPropertyDescriptors();
        for (int inx = 0; inx < pdr.length; inx++) {
            if (!Class.class.getName().equals(pdr[inx].getPropertyType().getName())) {
                try {
                    if (!NullUtil.isNull(beanWrapper.getPropertyValue(pdr[inx].getName()))) {
                        Object varReplace = beanWrapper.getPropertyValue(pdr[inx].getName());
                        if (varReplace instanceof Date) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat(OmcSystemConstants.OMC_JAVA_DATE_FORMAT_MAIL);
                            String strDate = dateFormat.format(varReplace);
                            replaced = replaced.replaceAll("\\$\\{" + pdr[inx].getName() + "\\}",
                                    StringUtil.escapeDollarSign(strDate));
                        } else if (varReplace instanceof Long) {
                            replaced = replaced.replaceAll("\\$\\{" + pdr[inx].getName() + "\\}",
                                    StringUtil.escapeDollarSign(Long.toString((long)varReplace)));
                        } else if (varReplace instanceof Boolean) {
                            replaced = replaced.replaceAll("\\$\\{" + pdr[inx].getName() + "\\}",
                                    StringUtil.escapeDollarSign(Boolean.toString((boolean)varReplace)));
                        } else if (varReplace instanceof Integer) {
                            replaced = replaced.replaceAll("\\$\\{" + pdr[inx].getName() + "\\}",
                                    StringUtil.escapeDollarSign(Integer.toString((int)varReplace)));
                        } else {
                            replaced = replaced.replaceAll("\\$\\{" + pdr[inx].getName() + "\\}",
                                    StringUtil.escapeDollarSign((String)varReplace));
                        }
                    }
                } catch (NullPointerException e) {
                    LOGGER.warn("MailSender-replaceVariables(String string, Object var, MailTemplate selectedMailTemplate) ▶ "
                            + pdr[inx].getName() + " is null." + e.getMessage());
                } catch (ClassCastException e) {
                    LOGGER.warn("MailSender-replaceVariables(String string, Object var, MailTemplate selectedMailTemplate) ▶ "
                            + pdr[inx].getName() + " occurs an error." + e.getMessage());
                }
            }
        }

        return replaced;
    }

    private String replaceVariablesByMap(String string, Map map, MailTemplate selectedMailTemplate){
        String replaced = string;

        String keyAttribute = null;
        Iterator<?> itr = map.keySet().iterator();
        while (itr.hasNext()) {
            keyAttribute = (String)itr.next();
            try {
                Object varReplace = map.get(keyAttribute);

                if (varReplace instanceof Date) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(OmcSystemConstants.OMC_JAVA_DATE_FORMAT_MAIL);
                    String strDate = dateFormat.format(varReplace);
                    replaced = replaced.replaceAll("\\$\\{" + keyAttribute + "\\}",
                            StringUtil.escapeDollarSign(strDate));
                } else if (varReplace instanceof Long) {
                    replaced = replaced.replaceAll("\\$\\{" + keyAttribute + "\\}",
                            StringUtil.escapeDollarSign(Long.toString((long)varReplace)));
                } else if (varReplace instanceof Boolean) {
                    replaced = replaced.replaceAll("\\$\\{" + keyAttribute + "\\}",
                            StringUtil.escapeDollarSign(Boolean.toString((boolean)varReplace)));
                } else if (varReplace instanceof Integer) {
                    replaced = replaced.replaceAll("\\$\\{" + keyAttribute + "\\}",
                            StringUtil.escapeDollarSign(Integer.toString((int)varReplace)));
                } else {
                    replaced = replaced.replaceAll("\\$\\{" + keyAttribute + "\\}",
                            StringUtil.escapeDollarSign((String)varReplace));
                }

            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
            }
        }

        return replaced;
    }

    /**
     * 치환한 문자열을 파일로 저장하여 미리보기를 할 수 있도록 한다.
     *
     * @param selectedMailTemplate template 에 대한 설정 정보를 가지고 있는 객체
     * @param resultStr 치환된 문자열
     * */
    private void saveResolvedHtmlFile(MailTemplate selectedMailTemplate, String resultStr){
        if (resultStr != null) {
            File file = null;
            FileChannel foc = null;
            ByteBuffer buf = null;

            try {
                String filePath = selectedMailTemplate.getSaveResolvedhtmlDir() + "/" + System.currentTimeMillis();
                file = new File(filePath);
                if (!file.exists()) {
                    if (file.mkdirs()) {
                        String path = filePath + "/" + selectedMailTemplate.getHtmlName();
                        foc = new FileOutputStream(new File(path)).getChannel();
                        buf = ByteBuffer.allocate(resultStr.getBytes(this.mailPolicy.getCharsetType()).length);
                        buf.put(resultStr.getBytes(this.mailPolicy.getCharsetType()));
                        buf.clear();
                        foc.write(buf);
                    } else {
                        throw new MailException(
                                "DSOMAL015",
                                "MailSender-saveResolvedHtmlFile(MailTemplate selectedMailTemplate, String resultStr) ▶ Fail to make resloved html file directory.");
                    }
                }
            } catch (Exception e) {
                throw new MailException(
                        "DSOMAL030",
                        "MailSender-saveResolvedHtmlFile(MailTemplate selectedMailTemplate, String resultStr) ▶ Resolved html file save error.",
                        e);
            } finally {
                if (buf != null) {
                    buf.clear();
                }
                if (foc != null) {
                    try {
                        foc.close();
                    } catch (IOException ioe) {
                        throw new MailException(
                                "DSOMAL062",
                                "MailSender-saveResolvedHtmlFile(MailTemplate selectedMailTemplate, String resultStr) ▶ Resolved html file save error.",
                                ioe);
                    }
                }
            }
        }
    }

    /**
     * 전송할 파일을 담는다.
     *
     * @param multiPart 전송할 파일을 담을 객체
     * @param fileName 첨부파일 이름 String 배열
     * */
    private void attachFileSourceArray(MimeMultipart multiPart, String[] fileNames) throws MessagingException,
            UnsupportedEncodingException{
        MimeBodyPart[] fileBodyPartArray = null;

        if (fileNames == null) {
            fileBodyPartArray = new MimeBodyPart[0];
        } else {
            fileBodyPartArray = new MimeBodyPart[fileNames.length];
        }

        for (int i = 0; i < fileBodyPartArray.length; i++) {
            fileBodyPartArray[i] = new MimeBodyPart();
            FileDataSource fileSource = new FileDataSource(fileNames[i]);
            fileBodyPartArray[i].setDataHandler(new DataHandler(fileSource));
            fileBodyPartArray[i].setFileName(MimeUtility.encodeText(fileSource.getName(),
                    this.mailPolicy.getCharsetType(), this.mailPolicy.getEncodingType()));
            multiPart.addBodyPart(fileBodyPartArray[i]);
        }
    }

    /**
     * 설정된 메일을 전송한다.<br/>
     * 테스트 모드인 경우 testReceivers에 설정한 주소로 메일을 발송한다.(설정하지 않은 경우 로그 메세지만 남김)
     * */
    public void send(){
        try {
            if (this.mailPolicy.isTestMode()) {
                if (this.testReceiverList.length == 0) {
                    LOGGER.info("MailSender-send() ▶ Sending mail to TEST-RECEIVERS is replaced to print this message on devon log file.(TEST-RECEIVERS in mailsender-context.xml is empty.");
                    return;
                } else {
                    setToMailAddress(this.testReceiverList);
                    setCcMailAddress("");
                    setBccMailAddress("");
                }
            }
            Transport.send(msg);
        } catch (MessagingException e) {
            throw new MailException("DSOMAL031", "MailSender-send() ▶ Mail send error happend, Transport.send Message["
                    + msg.toString() + "].", e);
        }
    }

    /**
     * 디버깅을 위해 사용되는 Mail Policy와 Mail Tempalte에 대한 설정 정보를 출력한다.
     * */
    public void printCurrentConfigInfo(){
        StringBuilder buf = new StringBuilder();

        buf.append("-------------------------------------------------------------------" + "\n");
        buf.append(" *TEST_MODE                   " + this.mailPolicy.isTestMode() + "\n");
        if (testReceiverList != null) {
            buf.append(" *TEST_RECEIVERS              ");
            for (int i = 0; i < this.testReceiverList.length; i++) {
                buf.append(this.testReceiverList[i]).append(" ");
            }
        }
        buf.append("\n");
        buf.append(" *MAIL_HOST                   ").append(this.mailPolicy.getMailHost()).append("\n");
        buf.append(" *MAIL_PORT                   ").append(this.mailPolicy.getMailPort()).append("\n");
        buf.append(" *MAIL_AUTH                   ").append(this.mailPolicy.isAuthenticationEnabled()).append("\n");
        buf.append(" *MAIL_TLS                    ").append(this.mailPolicy.isAuthenticationTlsEnabled()).append("\n");
        buf.append(" *MAIL_SSL                    ").append(this.mailPolicy.isAuthenticationSslEnabled()).append("\n");
        buf.append(" *MAIL_AUTH_ID                ").append(this.mailPolicy.getAuthenticationId()).append("\n");
        buf.append(" *MAIL_AUTH_PASSWORD          ").append(this.mailPolicy.getAuthenticationPassword()).append("\n");
        buf.append(" *SESSION_DEBUG_MESSAGE_FLAG  ").append(this.mailPolicy.isSessionDebugMessageFlag()).append("\n");
        buf.append(" *DEFAULT_SESSION_LOAD        ").append(this.mailPolicy.isDefaultSessionLoad()).append("\n");
        buf.append(" *DEFAULT_SENDER_MAIL_ADDRESS ").append(this.mailPolicy.getDefaultSenderMailAddress()).append("\n");
        buf.append(" *DEFAULT_SENDER_NAME         ").append(this.mailPolicy.getDefaultSenderName()).append("\n");
        buf.append(" *CONTENT_TYPE                ").append(this.mailPolicy.getContentType()).append("\n");
        buf.append(" *CHARSET_TYPE                ").append(this.mailPolicy.getCharsetType()).append("\n");
        buf.append(" *PLAIN_CONTENT_TYPE          ").append(this.mailPolicy.getPlainContentType()).append("\n");
        buf.append(" *HTML_CONTENT_TYPE           ").append(this.mailPolicy.getHtmlContentType()).append("\n");
        buf.append(" *ENCODING_TYPE               ").append(this.mailPolicy.getEncodingType()).append("\n");

        MailTemplate selectedMailTemplate = this.mailTemplate.get(mailTemplateName);
        buf.append(" *BODY_TEMPLATE_DIRECTORY     ")
                .append((selectedMailTemplate != null ? selectedMailTemplate.getDirectory() : "")).append("\n");
        buf.append(" *BODY_TEMPLATE_HTML          ")
                .append((selectedMailTemplate != null ? selectedMailTemplate.getHtmlName() : "")).append("\n");
        buf.append(" *IMG_SERVER_IP               ")
                .append((selectedMailTemplate != null ? selectedMailTemplate.getImgServerIp() : "")).append("\n");
        buf.append(" *IMG_SERVER_PORT             ")
                .append((selectedMailTemplate != null ? selectedMailTemplate.getImgServerPort() : "")).append("\n");
        buf.append(" *IMG_SERVER_CONTEXT          ")
                .append((selectedMailTemplate != null ? selectedMailTemplate.getImgServerContext() : "")).append("\n");
        buf.append(" *SAVE_RESOLVEDHTML_FLAG      ")
                .append((selectedMailTemplate != null ? selectedMailTemplate.isSaveResolvedhtmlFlag() : ""))
                .append("\n");
        buf.append(" *SAVE_RESOLVEDHTML_DIR       ")
                .append((selectedMailTemplate != null ? selectedMailTemplate.getSaveResolvedhtmlDir() : ""))
                .append("\n");
        buf.append("-------------------------------------------------------------------");

        LOGGER.debug(this.getClass().getName() + ".printCurrentConfigInfo() \n" + buf.toString());
    }

}
