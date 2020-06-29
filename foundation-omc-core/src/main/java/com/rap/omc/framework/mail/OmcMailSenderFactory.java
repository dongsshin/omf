/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcMailPolicy.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 3. 2. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.framework.mail;

import java.util.Map;
import com.rap.omc.framework.mail.exception.MailException;
import com.rap.omc.framework.mail.policy.MailPolicy;
import com.rap.omc.framework.mail.policy.MailTemplate;
import com.rap.omc.framework.refresh.BeanRefreshSupport;
public class OmcMailSenderFactory implements BeanRefreshSupport {

    private MailPolicy globalMailPolicy;
    private Map<String, MailPolicy> optionalMailPolicyMap;
    private Map<String, MailTemplate> mailTemplateMap;
    public MailPolicy getGlobalMailPolicy(){
        return globalMailPolicy;
    }
    public void setGlobalMailPolicy(MailPolicy globalMailPolicy){
        this.globalMailPolicy = globalMailPolicy;
    }
    public Map<String, MailPolicy> getOptionalMailPolicyMap(){
        return optionalMailPolicyMap;
    }
    public void setOptionalMailPolicyMap(Map<String, MailPolicy> optionalMailPolicyMap){
        this.optionalMailPolicyMap = optionalMailPolicyMap;
    }
    public Map<String, MailTemplate> getMailTemplateMap(){
        return mailTemplateMap;
    }
    public void setMailTemplateMap(Map<String, MailTemplate> mailTemplateMap){
        this.mailTemplateMap = mailTemplateMap;
    }
    public OmcMailSender createMailSender(){
        OmcMailSender mailSender = new OmcMailSender(globalMailPolicy, mailTemplateMap);
        return mailSender;
    }
    public OmcMailSender createMailSender(String mailPolicy){
        MailPolicy selectedMailPolicy = this.optionalMailPolicyMap.get(mailPolicy);
        if (selectedMailPolicy == null) { throw new MailException("DSOMAL001", "Can not found mail policy. check the key of 'optionalMailPolicyMap' properties in mailsender-context.xml file."); }
        OmcMailSender mailSender = new OmcMailSender(selectedMailPolicy, mailTemplateMap);
        return mailSender;
    }
}