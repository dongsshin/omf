package com.rap.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rap.omc.api.util.PropertyUtil;
import com.rap.omc.foundation.classes.service.CommonCoreService;
import com.rap.omc.test.service.TransactionTestService;

@RestController
public class FileTestController {
	
	@Autowired
	private CommonCoreService commonCoreService ;
	@Autowired
	private TransactionTestService transactionTestService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping("/file/getClassName")
	public void required_required() {
		String SYSTEM_DELIMINATOR_VALUE_FOR_JAVA = PropertyUtil.getProperty("SYSTEM_DELIMINATOR_VALUE_FOR_JAVA");
		String className = commonCoreService.getClassNameWithObid("obid");
		System.out.print(className);
		System.out.print(SYSTEM_DELIMINATOR_VALUE_FOR_JAVA);
		transactionTestService.txnAllTest();
		try {
			transactionTestService.txnMasterTestError();
		}catch(Exception e) {
			e.printStackTrace();
		}
		transactionTestService.txnFoundationTest();
	}
	
    @RequestMapping("/file/helloAdmin")
    public String helloAdmin() {
        return "hello admin(SessionController)";
    }
	@RequestMapping("/file/")
    public String root_test() throws Exception{
        return "Hello Root(/)";
    }
 
    @RequestMapping("/file/demo")
    public String demo_test() throws Exception{
        return "Hello demo(/demo)";
    }
}