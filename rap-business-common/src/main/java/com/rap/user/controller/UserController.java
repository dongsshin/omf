package com.rap.user.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rap.omc.api.util.MessageUtil;

import rap.api.object.organization.dom.Users;
@RestController
public class UserController {
	@RequestMapping( "/userTest" )
    public String userTest(){
		
		
//		UsersVO userVo = new UsersVO();
//				
//		userVo.setNames("XP3866");
//		userVo.setDescriptions("XP3866");
//		userVo.setLifeCycle("Person Policy");
//		userVo.setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_PERSON);
//		userVo.setStates(ApplicationSchemaConstants.STATE_PERSON_ACTIVE);
//		userVo.setTitles("XP3866");
//		Users UsersDom = new Users(userVo);
//		UsersDom.createObject();
		
		Users UsersDom = new Users("5MjzYPlsdOfkN04d48ii");
		Map<String,Object> map = UsersDom.getUserSessionInfo();
		//String message = MessageUtil.getMessage("plm.admin.validation.maxlength", new Object[] { " Master Code Name"," : 256" });
		String message = MessageUtil.getMessage("com.error.login.inactive");
		
		return message;
		
	}
}
