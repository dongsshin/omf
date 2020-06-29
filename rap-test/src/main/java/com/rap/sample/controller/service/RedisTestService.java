package com.rap.sample.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.rap.config.redis.JsonRedisTemplate;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.sample.controller.service.model.DataData;

@Service("redisTestService")
public class RedisTestService {
	@Autowired
	UserSession userSession;
    @Autowired
    JsonRedisTemplate jsonRedisTemplate;
    
    @SuppressWarnings("unused")
	public void redisTest() {
        ValueOperations<String, Object> vop = jsonRedisTemplate.opsForValue();
        DataData setData = new DataData("jeong","pro");
        System.out.println(userSession.getUserId());
//        
//        for(int i = 0; i < 100; i++) {
//        	vop.set("key" + i, userSession);
//        }
//        
//        for(int i = 0; i < 100; i++) {
//        	Object obj = vop.get("key" + i);
//        	
//        	
//        }
        vop.set("key", setData);
        Object obj = vop.get("key");
        jsonRedisTemplate.convertAndSend("Event", "ProgDDDram Event");
    }
}
