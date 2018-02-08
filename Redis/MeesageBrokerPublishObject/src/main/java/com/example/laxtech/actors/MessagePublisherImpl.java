package com.example.laxtech.actors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class MessagePublisherImpl implements MessagePublisher {
    private static final String KEY = "Actor";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ChannelTopic topic;

    public MessagePublisherImpl() {
    }

    public MessagePublisherImpl(RedisTemplate redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(Actor actor) {
        System.out.println("Sending.............");
        redisTemplate.convertAndSend(topic.getTopic(), actor);
    }


}
