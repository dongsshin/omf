package com.rap.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rap.omc.api.util.PropertyUtil;
import com.rap.omc.foundation.classes.service.CommonCoreService;
import com.rap.omc.foundation.user.service.FoundationUserDetailsService;
import com.rap.omc.test.service.TransactionTestService;

@RestController
public class CommonTestController {
	
	@Autowired
	private CommonCoreService commonCoreService ;
	@Autowired
	private TransactionTestService transactionTestService;


	@Autowired
	FoundationUserDetailsService foundationUserDetailsService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping("/common/getClassName")
	public void required_required() {
		String SYSTEM_DELIMINATOR_VALUE_FOR_JAVA = PropertyUtil.getProperty("SYSTEM_DELIMINATOR_VALUE_FOR_JAVA");
		String className = commonCoreService.getClassNameWithObid("obid");
		System.out.print(className);
		System.out.print(SYSTEM_DELIMINATOR_VALUE_FOR_JAVA);
		//transactionTestService.txnAllTest();
		try {
			transactionTestService.txnMasterTestError();
		}catch(Exception e) {
			;
		}
		transactionTestService.txnFoundationTest();
	}
    @RequestMapping("/common/redisTest")
    public String redisTest() {
    	//redisTestService.redisTest();
    	
    	 return "redisTest";
    }
    @RequestMapping("/common/helloAdmin")
    public String helloAdmin() {
        return "hello admin(SessionController)";
    }
	@RequestMapping("/common/")
    public String root_test() throws Exception{
        return "Hello Root(/)";
    }
 
    @RequestMapping("/common/demo")
    public String demo_test() throws Exception{
        return "Hello demo(/demo)";
    }
	@RequestMapping("/common/autoLogin")
    public String autoLogin(HttpServletRequest request) throws Exception{
    	UserDetails userDetails = foundationUserDetailsService.loadUserByUsername("XP3866");
    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword());
    	SecurityContext securityContext = SecurityContextHolder.getContext();
    	securityContext.setAuthentication(usernamePasswordAuthenticationToken);
    	HttpSession session = request.getSession(true);
    	session.setAttribute("SPRING_SECURITY_CONTEXY", securityContext);
    	return "Auto Login Success";
    }
}