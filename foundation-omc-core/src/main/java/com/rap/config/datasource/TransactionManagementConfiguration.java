package com.rap.config.datasource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
@EnableTransactionManagement
public class TransactionManagementConfiguration 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionManagementConfiguration.class);
    private static final int TX_METHOD_TIMEOUT = 50000;  
    private static final String AOP_POINTCUT_EXPRESSION = "(execution(* *..*.service..*.*(..)) || execution(* *..*.services..*.*(..)))";
	
	@Autowired  
    private PlatformTransactionManager transactionManager;
	@Bean(name="userTransactionServiceImp", initMethod = "init", destroyMethod = "shutdownForce")
    public UserTransactionServiceImp userTransactionServiceImp()
    {
        Properties properties = new Properties();
        
        properties.setProperty("com.atomikos.icatch.max_timeout", "360000000000");
        properties.setProperty("com.atomikos.icatch.default_jta_timeout", "100000000000");
        properties.setProperty("com.atomikos.icatch.threaded_2pc", "true");
        UserTransactionServiceImp userTransactionServiceImp = new UserTransactionServiceImp(properties);
        return userTransactionServiceImp;
    }

    @Bean
    @DependsOn("userTransactionServiceImp")
    public UserTransaction userTransaction()
    {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        return userTransactionImp;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    @DependsOn("userTransactionServiceImp")
    public TransactionManager userTransactionManager()
    {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setStartupTransactionService(false);
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean
    @DependsOn("userTransactionServiceImp")
    public PlatformTransactionManager transactionManager()
    {
        return new JtaTransactionManager(userTransaction(), userTransactionManager());
    } 
    @Bean
    public TransactionInterceptor txAdvice() {
    	NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();  

    	RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();  
        readOnlyTx.setReadOnly(true);  
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);  
  
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();  
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));  
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);  
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);  
        Map<String, TransactionAttribute> txMap = new HashMap<String, TransactionAttribute>();
//        txMap.put("add*", requiredTx);  
//        txMap.put("save*", requiredTx);  
//        txMap.put("insert*", requiredTx);  
//        txMap.put("update*", requiredTx);  
//        txMap.put("delete*", requiredTx);  
        txMap.put("txn*", requiredTx);  
        txMap.put("get*", readOnlyTx);  
//        txMap.put("query*", readOnlyTx);  
//        txMap.put("list*", readOnlyTx);  
        txMap.put("find*", readOnlyTx);  
        source.setNameMap(txMap);  
        return new TransactionInterceptor(transactionManager, source);  
    }
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}