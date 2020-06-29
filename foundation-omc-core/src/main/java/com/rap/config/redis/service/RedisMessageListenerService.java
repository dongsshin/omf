package com.rap.config.redis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.connection.Message;

@Service
public class RedisMessageListenerService implements MessageListener{
    public static List<String> messageList = new ArrayList<String>();
    @Override
    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Message received: " + message.toString());
    }
}
