package com.rap.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rap.config.redis.service.RedisMessageListenerService;
import com.rap.omc.constants.RedisConstants;

@Configuration
public class RedisConfiguration {
	@Bean
	public RedisConnectionFactory jsonRedisConnectionFactory() {
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
		return lettuceConnectionFactory;
	}
//	@Bean
//	public RedisTemplate<String, Object> redisTemplateJson(){
//		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//		redisTemplate.setConnectionFactory(redisConnectionFactoryForJackson());
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
//		return redisTemplate;
//	}
	
	@Bean
	public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(new RedisMessageListenerService());
    }
    
    @Bean
    public RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer(); 
        container.setConnectionFactory(jsonRedisConnectionFactory()); 
        container.addMessageListener(messageListenerAdapter(), topic());
        return container; 
    }
    //사용자 정보가 변경되는 경우 
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic(RedisConstants.REDIS_USER_CHANGE_MESSAGE);
    }

    @Bean
    public ObjectMapper objectMapper(){
      return new ObjectMapper();
    }
	@Bean
    public JsonRedisTemplate jsonRedisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper){
      return new JsonRedisTemplate<>(jsonRedisConnectionFactory(),objectMapper(),Object.class);
    }
}
