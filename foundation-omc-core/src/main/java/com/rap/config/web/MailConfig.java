package com.rap.config.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rap.omc.framework.mail.OmcMailSenderFactory;
import com.rap.omc.framework.mail.policy.MailPolicy;
import com.rap.omc.framework.mail.policy.MailTemplate;

@Configuration
public class MailConfig {
	@Bean
	public OmcMailSenderFactory mailSenderFactory() {
		OmcMailSenderFactory mailSenderFactory = new OmcMailSenderFactory();
		mailSenderFactory.setGlobalMailPolicy(mailPolicy());
		Map<String,MailPolicy> optionalMailPolicyMap = new HashMap<String,MailPolicy>();
		optionalMailPolicyMap.put("mailPolicy1", mailPolicy1());
		mailSenderFactory.setOptionalMailPolicyMap(optionalMailPolicyMap);

		Map<String,MailTemplate> mailTemplateMap = new HashMap<String,MailTemplate>();
		mailTemplateMap.put("example", example());
		mailTemplateMap.put("commonMailTemplate", commonMailTemplate());
		mailTemplateMap.put("mobileApprovalDetailTemplate", mobileApprovalDetailTemplate());
		return mailSenderFactory;
	}
	@Bean
	public MailTemplate example() {
		MailTemplate example = new MailTemplate();
		example.setDirectory("classpath:/mail");
		example.setHtmlName("example.html");
		example.setSaveResolvedhtmlFlag(false);
		example.setSaveResolvedhtmlDir("classpath:/mail");
		example.setImgServerIp("#{crypto['server.ip']}");
		example.setImgServerPort("#{crypto['server.port']}");
		example.setImgServerContext("#{crypto['context.path']}");
		return example;
	}
	@Bean
	public MailTemplate commonMailTemplate() {
		MailTemplate commonMailTemplate = new MailTemplate();
		commonMailTemplate.setDirectory("classpath:/mail");
		commonMailTemplate.setHtmlName("commonMailTemplate.html");
		commonMailTemplate.setSaveResolvedhtmlFlag(false);
		commonMailTemplate.setSaveResolvedhtmlDir("");
		commonMailTemplate.setImgServerIp("#{crypto['server.ip']}");
		commonMailTemplate.setImgServerPort("#{crypto['server.port']}");
		commonMailTemplate.setImgServerContext("#{crypto['context.path']}");
		return commonMailTemplate;
	}
	@Bean
	public MailTemplate mobileApprovalDetailTemplate() {
		MailTemplate mobileApprovalDetailTemplate = new MailTemplate();
		mobileApprovalDetailTemplate.setDirectory("classpath:/mail");
		mobileApprovalDetailTemplate.setHtmlName("mobileApprovalDetailTemplate.html");
		mobileApprovalDetailTemplate.setSaveResolvedhtmlFlag(false);
		mobileApprovalDetailTemplate.setSaveResolvedhtmlDir("");
		mobileApprovalDetailTemplate.setImgServerIp("#{crypto['server.ip']}");
		mobileApprovalDetailTemplate.setImgServerPort("#{crypto['server.port']}");
		mobileApprovalDetailTemplate.setImgServerContext("#{crypto['context.path']}");
		return mobileApprovalDetailTemplate;
	}
	@Bean
	public MailPolicy mailPolicy() {
		MailPolicy mailPolicy = new MailPolicy();
		mailPolicy.setTestMode(true);
		mailPolicy.setTestReceivers("");
		mailPolicy.setMailHost("smtp.gmail.com");
		mailPolicy.setMailPort("465");
		mailPolicy.setAuthenticationEnabled(true);
		mailPolicy.setAuthenticationTlsEnabled(false);
		mailPolicy.setAuthenticationSslEnabled(true);

		mailPolicy.setAuthenticationId("devontestmail@gmail.com");
		mailPolicy.setAuthenticationPassword("devondevon");
		mailPolicy.setDefaultSenderMailAddress("devontestmail@gmail.com");
		mailPolicy.setDefaultSenderName("Admin");

		mailPolicy.setSessionDebugMessageFlag(false);
		mailPolicy.setDefaultSessionLoad(true);
		mailPolicy.setContentType("7bit");
		
		mailPolicy.setPlainContentType("text/plain");
		mailPolicy.setHtmlContentType("text/html");
		mailPolicy.setCharsetType("UTF-8");
		mailPolicy.setEncodingType("Q");
		
		return mailPolicy;
	}
	@Bean
	public MailPolicy mailPolicy1() {
		MailPolicy mailPolicy = new MailPolicy();
		mailPolicy.setTestMode(false);
		mailPolicy.setTestReceivers("");
		mailPolicy.setMailHost("lgekrhqmh01.lge.com");
		mailPolicy.setMailPort("25");
		mailPolicy.setAuthenticationEnabled(true);
		mailPolicy.setAuthenticationTlsEnabled(false);
		mailPolicy.setAuthenticationSslEnabled(true);

		mailPolicy.setAuthenticationId("gpdm");
		mailPolicy.setAuthenticationPassword("matrix0602");
		//<property name="defaultSenderMailAddress" value="#{crypto['mail.sender.id']}" />
		mailPolicy.setDefaultSenderMailAddress("devontestmail@gmail.com");
		mailPolicy.setDefaultSenderName("Admin");
//	     <property name="defaultSenderMailAddress" value="#{crypto['mail.sender.id']}" />
//	     <property name="defaultSenderName" value="#{crypto['mail.sender.name']}" />
		mailPolicy.setSessionDebugMessageFlag(false);
		mailPolicy.setDefaultSessionLoad(true);
		mailPolicy.setContentType("7bit");
		
		mailPolicy.setPlainContentType("text/plain");
		mailPolicy.setHtmlContentType("text/html");
		mailPolicy.setCharsetType("UTF-8");
		mailPolicy.setEncodingType("Q");
		
		return mailPolicy;
	}
}
