package com.bindu.apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@Service
public class RateLimiterService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterService.class);

    private static final int CAPACITY = 5;      // max tokens
    private static final int REFILL_TIME = 10;  // seconds

    public boolean allowRequest(String user) {
        String key = "bucket:" + user;

        String value = redisTemplate.opsForValue().get(key);
        int tokens = (value == null) ? CAPACITY : Integer.parseInt(value);

        if (tokens > 0) {
            int remaining = tokens - 1;

            redisTemplate.opsForValue().set(
                key,
                String.valueOf(remaining),
                Duration.ofSeconds(REFILL_TIME)
            );

            logger.info("✅ Allowed | User: {} | Tokens left: {}", user, remaining);
            return true;
        }

        logger.warn("❌ Blocked | User: {} | No tokens left", user);
        return false;
    }
}