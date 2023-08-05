package com.markerhub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisPractice {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void RedisTest() {

    }


}
