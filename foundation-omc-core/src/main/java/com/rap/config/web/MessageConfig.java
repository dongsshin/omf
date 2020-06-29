package com.rap.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.rap.omc.framework.message.file.FileMessageSource;

@Configuration
public class MessageConfig {
	
//	@Autowired
//	DataSource schemaDataSource;
//	@Bean
//	public DatabaseMessageSource messageSource() {
//		DatabaseMessageSource databaseMessageSource = new DatabaseMessageSource();
//		databaseMessageSource.setDataSource(schemaDataSource);
//		databaseMessageSource.setDefaultQuery("SELECT PMSG MESSAGE, PLOCALE LOCALE, PCODE CODE FROM PSYSMESSAGE");
//		databaseMessageSource.setLoadType("preLoad");
//		databaseMessageSource.setTableName("PSYSMESSAGE");
//		databaseMessageSource.setCodeColumn("CODE");
//		databaseMessageSource.setMessageColumn("MESSAGE");
//		databaseMessageSource.setLocaleColumn("LOCALE");
//		databaseMessageSource.setParentMessageSource(fileMessageSource());
//		return databaseMessageSource;
//		
//	}
	@Bean
	public MessageSource messageSource() {
		FileMessageSource fileMessageSource = new FileMessageSource();
		fileMessageSource.setBasenames("classpath:/messages/message-foundation",
				                       "classpath:/messages/message-project",
				                       "classpath:/messages/message-validator",
				                       "classpath:/messages/attribute");
		fileMessageSource.setCacheSeconds(60);
		fileMessageSource.setLoadType("preLoad");
		return fileMessageSource;
	}
	@Bean
	public LocalValidatorFactoryBean beanValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
	}
	@Bean
	public MessageSourceAccessor messageSourceAccessor(){
	    return new MessageSourceAccessor(messageSource());
	}
}
